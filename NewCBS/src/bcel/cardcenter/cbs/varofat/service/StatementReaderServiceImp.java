package bcel.cardcenter.cbs.varofat.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import bcel.cardcenter.cbs.varofat.entity.VisaTxn;

public class StatementReaderServiceImp implements StatementReaderService {
	private final static Logger logger = Logger.getLogger(StatementReaderServiceImp.class);
	private List<VisaTxn> visaCards;
	private String source;
	private String target;
	
	public void setSource(String source){
		this.source = source;
	}
	public String getSource(){
		return source;
	}
	public void setTarget(String target){
		this.target = target;
	}
	public String getTarget(){
		return target;
	}
	public void setVisaCards(List<VisaTxn> visaCards){
		this.visaCards = visaCards;
	}
	@Override
	public List<VisaTxn> getVisaCards(){
		return visaCards;
	}
	@Override
	public void readStatementFile(){
		try{
			File dir = new File(source);
			File[] files = dir.listFiles();
			for(int i=0;i<files.length;i++){
				File file = files[i];
				writeOutput(file);
			}
		}
		catch(Exception ex){
			logger.debug("Exceptin occur on main execution",ex);
		}
	}
	public List<VisaTxn> readStatement(File file) throws Exception {
		InputStream in = new FileInputStream(file);
		XSSFWorkbook wb = (XSSFWorkbook) WorkbookFactory.create(in);
		XSSFSheet sheet = wb.getSheetAt(0);
		
		int lenght = sheet.getLastRowNum();
		visaCards = new ArrayList<VisaTxn>();
		for(int i=16;i<lenght-5;i++){
		
			XSSFRow row = sheet.getRow(i);
			
			String teller = row.getCell(12).getStringCellValue().trim();
			if(teller == null)
				continue;
			if(!teller.equals("ATM"))
				continue;
			java.util.Date date = row.getCell(1).getDateCellValue();
			String desc 		= row.getCell(4).getStringCellValue();
			String[] token		= desc.split("\\|");
			int j = token.length;
			String time			= token[j-1].trim();
			String stan			= token[j-2].trim();
			String refer		= token[j-3].trim();
			String termId		= token[j-4].trim();
			String card			= token[j-5].trim();
			String termName		= token[j-6].trim();
			double amount		= row.getCell(8).getNumericCellValue();
			VisaTxn masterCard = new VisaTxn();
			masterCard.setDate(new java.sql.Date(date.getTime()));
			masterCard.setTermName(termName);
			masterCard.setTermId(termId);
			masterCard.setRefer(refer);
			masterCard.setCard(card);
			masterCard.setStan(stan);
			masterCard.setTime(time);
			masterCard.setAmount(amount);
			if(amount<0){
				masterCard.setType("REV");
			}
			visaCards.add(masterCard);
			logger.debug(masterCard.toString());
		}
		
		return visaCards;
	}
	
	public void writeOutput(File file){
		String name = file.getName()+".csv";
		String dest = target+"\\"+name;
		File out = new File(dest);
		try {
			setVisaCards(readStatement(file));
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter (new FileOutputStream(out)));
			String header = "NO,CARD,RRN,TIME,DATE,TERMI,AMT,STAN";
			logger.debug(header);
			printWriter.println(header);
			for(int i=0;i<visaCards.size();i++){
				VisaTxn visa = visaCards.get(i);
				int index = i+1;
				String line = index+","+visa.toString();
				printWriter.println(line);
				logger.debug(line);
			}
			printWriter.close();
		}
		catch(Exception ex){
			logger.debug("Exception Occured whiel try to write output file",ex);
		}
	}
	
}
