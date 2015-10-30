package bcel.cardcenter.cbs.varofat.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

import bcel.cardcenter.cbs.varofat.entity.VisaSettle;

public class VisaSettlementAnalyzer extends VisaAtmUtilityImp{
	private static final Logger logger             = Logger.getLogger(VisaSettlementAnalyzer.class);
	
	private static final String headerPattern      = "REPORT ID: ([A-Z0-9]{7})\\s{1,}VISANET INTEGRATED PAYMENT SYSTEM\\s{1,}PAGE NUMBER\\s{1,}:\\s{1,}\\d{1,}";
	private static final String subHeader01Pattern = "FUNDS XFR: ([0-9]{10}) BCEL TOP SRE\\s* SINGLECONNECT / [A-Z]+ \\s* ONLINE SETTLMNT DATE:  ([0-9A-Z]{7})";
	private static final String subHeader02Pattern = "PROCESSOR: ([0-9]{10}) BCEL ATM\\s* ACQUIRER .+ [A-Z]+\\s*REPORT DATE\\s*:  ([0-9A-Z]{7})";
	private static final String subHeader03Pattern = "AFFILIATE: ([0-9]{10}) BCEL ATM\\s{1,}BY CARDHOLDER NUMBER\\s{1,}REPORT TIME\\s{1,}: (\\d{2}:\\d{2}:\\d{2})";
	private static final String subHeader04Pattern = "SRE\\s{1,}: ([0-9]{10}) BCEL ATMACQ NW[0-9]{1}\\s{1,}VSS PROCESSING DATE :  ([0-9A-Z]{7})";
	
	private static final String title01Pattern = "^-+$";
	private static final String title02Pattern = "BAT XMIT(GMT)/LOCL                     RETRIEVAL    TRACE  ISSUER ID/  TRAN PROCSS ENT REAS CN/ RSP  --TRANSACTION--   SETTLEMENT";
	private static final String title03Pattern = "NUM DATE  TIME     CARD NUMBER         REF NUMBER   NUMBER TRMNL/NAME  TYPE CODE   MOD CODE STP CD        AMOUNT CUR   AMOUNT (USD)";
	// ATM withdraw successful transaction
	private static final String record01Pattern = "(\\d{2}) ([0-9A-Z]{5}) (\\d{2}:\\d{2}:\\d{2}) (\\d{16,19}) \\s*(\\d{12}) (\\d{6}) (\\d+)\\s+(\\d{4}) (\\d{6}) \\d{3}\\s+(\\d{0,4})\\s+(\\d{2})\\s+(\\d{2})\\s+(.+) ([A-Z]{3}) \\s+([0-9|,|\\.]{1,})([A-Z]{2})";
	private static final String record02Pattern = "CA ID: (\\d{8})  ([0-9A-Za-z]{1,}).+/LA  \\d{4}";
	
	private Pattern header      = Pattern.compile(headerPattern);
	private Pattern subHeader01 = Pattern.compile(subHeader01Pattern);
	private Pattern subHeader02 = Pattern.compile(subHeader02Pattern);
	private Pattern subHeader03 = Pattern.compile(subHeader03Pattern);
	private Pattern subHeader04 = Pattern.compile(subHeader04Pattern);
	
	private Pattern title01  = Pattern.compile(title01Pattern);
	
	private Pattern record01 = Pattern.compile(record01Pattern);
	private Pattern record02 = Pattern.compile(record02Pattern);
	private Matcher matcher;
	
	private List<String> transBuffer = new ArrayList<String>();
	private List<VisaSettle> reports     = new ArrayList<VisaSettle>();
	private File file;
	private Date importDate;
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Date getImportDate() {
		return importDate;
	}
	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}
	public List<VisaSettle> getReports() {
		return reports;
	}
	public void setReports(List<VisaSettle> reports) {
		this.reports = reports;
	}
	
	public void perform() {
		try{
			BufferedReader bufferReader = new BufferedReader(new FileReader(file));
			String s;
			while((s=bufferReader.readLine())!=null){
				// if transaction buffer is empty and get element to transaction list
				// until next delimiter come up
				String buffer=s.trim();
				
				matcher = header.matcher(buffer);
				if(transBuffer.isEmpty() || !matcher.find()){
					matcher = title01.matcher(buffer);
					boolean cond01 = matcher.find();
					boolean cond02 = buffer.equals(title02Pattern);
					boolean cond03 = buffer.equals(title03Pattern);
					boolean cond04 = buffer.equals("");
					
					boolean cond = cond01||cond02||cond03||cond04;
					
					if(!cond)
						transBuffer.add(buffer);
				}
				// if not process the transaction buffer to retrieve necessary data
				// after process clear the buffer
				else{
					analyze();
					transBuffer.add(buffer);
				}
			}
			analyze();
		}
		catch(IOException ex){
			logger.debug("Exception occur while try to read visa settlement file", ex);
			return;
		}
	}
	
	public void analyze() {
		// Check whether the report by card number or not
		boolean ret = false;
		Iterator<String> iter = transBuffer.iterator();
		while(iter.hasNext()){
			String s = iter.next();
			matcher = subHeader03.matcher(s);
			if(matcher.find()){
				ret = true ;
				break;
			}
		}
		// if it is report by card number then retrieve the data
		if(ret){
			iter = transBuffer.iterator();
			while(iter.hasNext()){
				// Retrieve data from header
				String s = iter.next();
				// Read Header detail
				readHeader(s);
				// If not report sort by card holder number return
				if(!reportId.equals("SMS601C")){
					transBuffer.clear();
					return;
				}
			}
			int index = findRecordIndex(transBuffer);
			List<String> buffers     = new ArrayList<String>();
			for(int i = index;i<transBuffer.size();i++){
				String s = transBuffer.get(i);
				// Read Record detail
				matcher = record01.matcher(s);
				if(!matcher.find()||buffers.isEmpty()){
					buffers.add(s);
				}
				else{
					subAnalyze(buffers);
					buffers.add(s);
				}
			}
			subAnalyze(buffers);
		}
		transBuffer.clear();
	}
	private void subAnalyze(List<String> buffers) {
		VisaSettle report = new VisaSettle();
		// Set header report detail
		setReportHeader(report);
		// Set Other detail
		readRecord(report,buffers);
		// Add Report to Report List
		if(report.getBatId()!=null&&report.getCategory()!=null)
			//matcher = recordId.matcher(report.getReportId());
			reports.add(report);
		buffers.clear();
	}
	private int findRecordIndex(List<String> transBuffer) {
		int index = 0;
		Iterator<String> iter = transBuffer.iterator();
		while(iter.hasNext()){
			String s = iter.next();
			matcher = record01.matcher(s);
			if(matcher.find())
				break;
			index++;
		}
		return index;
	}
	private void setReportHeader(VisaSettle report) {
		report.setReportId(reportId);
		//report.setFundsXfr(fundsXfr);
		report.setSettleDate(settle_date);
		report.setProcess(processor);
		//report.setReport_date(report_date);
		//report.setAffiliate(affiliate);
		//report.setReport_time(report_time);
		//report.setSre(sre);
		report.setProcessDate(process_date);
		report.setImportDate(importDate);
	}
	private void readRecord(VisaSettle report, List<String> buffers) {
		Iterator<String> iter = buffers.iterator();
		while(iter.hasNext()){
			String s=iter.next();
			// Retrieve data from the first record
			matcher = record01.matcher(s);
			if (matcher.find()) {
				String batId = matcher.group(1).trim();
				String txn_date = matcher.group(2).trim();
				String txn_time = matcher.group(3).trim();
				String cardNumber = matcher.group(4).trim();
				String retrieveNumber = matcher.group(5).trim();
				String trace = matcher.group(6).trim();
				String issuerId = matcher.group(7).trim();
				String txn_type = matcher.group(8).trim();
				String procssCode = matcher.group(9).trim();
				String reas_code = matcher.group(10).trim();
				String responseCode = matcher.group(12).trim();
				String txn_amt = matcher.group(13).trim();
				String curr_code = matcher.group(14).trim();
				String settle_amt = matcher.group(15).trim();
				String txn_category = matcher.group(16).trim();
				double txn_amount = Double.parseDouble(txn_amt.replace(",", ""));
				double settle_amount = Double.parseDouble(settle_amt.replace(",",
						""));
				String id = cardNumber+retrieveNumber+trace+txn_type;
				//report.setId(id);
				report.setBatId(batId);
				report.setTxnDate(txn_date);
				report.setTxnTime(txn_time);
				report.setCard(cardNumber);
				report.setRetrieval(retrieveNumber);
				report.setTrace(trace);
				report.setIssuerId(issuerId);
				report.setIssuerId(issuerId);
				report.setType(txn_type);
				report.setProcess(procssCode);
				report.setResponse(responseCode);
				report.setReason(reas_code);
				report.setTxnAmt(txn_amount);
				report.setCurrency(curr_code);
				report.setSettleAmt(settle_amount);
				report.setCategory(txn_category);
			}
			// Retrieve data from second record
			matcher = record02.matcher(s);
			if (matcher.find()) {
				String atmId = matcher.group(1).trim();
				String ref_number = matcher.group(2).trim();
				report.setAtmId(atmId);
				report.setRefer(ref_number);
				return;
			}
		}
	}
	// Header information
	private String reportId;
	private String fundsXfr;
	private Date settle_date;
	private String processor;
	private Date report_date;
	private String affiliate;
	private String report_time;
	private String sre;
	private Date process_date;
	// Read header information
	private void readHeader(String s) {
		matcher = header.matcher(s);
		if (matcher.find()){
			reportId = matcher.group(1).trim();
			return ;
		}
		// Retrieve data from first sub header
		matcher = subHeader01.matcher(s);
		if(matcher.find()){
			fundsXfr = matcher.group(1).trim();
			String settleDate = matcher.group(2).trim();
			//settle_date = CBSUtil.str2Date05(settleDate);
			return ;
		}
		
		// Retrieve data from second sub header
		matcher = subHeader02.matcher(s);
		if(matcher.find()){
			processor = matcher.group(1).trim();
			String reportDate = matcher.group(2).trim();
			//report_date = CBSUtil.str2Date05(reportDate);
			return ;
		}
		
		// Retrieve data from third sub header
		matcher = subHeader03.matcher(s);
		if(matcher.find()){
			affiliate = matcher.group(1).trim();
			report_time = matcher.group(2).trim();
			return;
			
		}
		
		// Retrieve data from forth sub header
		matcher = subHeader04.matcher(s);
		if(matcher.find()){
			sre = matcher.group(1).trim();
			String vss_date = matcher.group(2).trim();
			//process_date = CBSUtil.str2Date05(vss_date);
			return;
		}
	}

}
