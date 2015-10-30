package bcel.cardcenter.cbs.utility.file.processor;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.regex.*;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.ss.usermodel.Row;

import bcel.cardcenter.cbs.aarofat.entity.Cwd;

public class AtmGlAnalizer {
	private static final Logger logger = Logger.getLogger(AtmGlAnalizer.class);
	private final static String cardNumberRegex = "^[0-9]{14,19}$";
	private final static String atmNumberRegex  = "^[0-9]{8}$";
	private final static String tsqNumberRegex  = "^[0-9]{6}$";
	private final static String reversalRegex   = "Reverse ATM Cash Withdrawal";
	
	private Pattern cardNumberPattern;
	private Pattern atmNumberPattern;
	private Pattern tsqNumberPattern;
	private Pattern reversalPattern;
	
	private Matcher cardNumberMatcher;
	private Matcher atmNumberMatcher;
	private Matcher tsqNumberMatcher;
	private Matcher reversalMatcher;
	
	private File file;
	private List<Cwd> sbTransList;
	private List<String> reversalTransList;
	private String sessionId = null;
	private String atmId = null;
	private Date beginDate;
	private Date endDate;
	
	public AtmGlAnalizer(){
		
		cardNumberPattern    = Pattern.compile(cardNumberRegex);
		atmNumberPattern     = Pattern.compile(atmNumberRegex);
		tsqNumberPattern     = Pattern.compile(tsqNumberRegex);
		reversalPattern      = Pattern.compile(reversalRegex);
		
		sbTransList = new ArrayList<Cwd>();
		reversalTransList = new ArrayList<String>();
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setAtmId(String atmId) {
		this.atmId = atmId;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Cwd> getSBTransList(){
		return sbTransList;
	}
	
	public List<String> getReversalTransList(){
		return reversalTransList;
	}
	
	public boolean analize(){
		
		try {
			
			POIFSFileSystem filein = new POIFSFileSystem(new FileInputStream(file));
			HSSFWorkbook wb = new HSSFWorkbook(filein);
			HSSFSheet sheet = wb.getSheetAt(0);
			
			int lastRow = sheet.getLastRowNum();
			
			HSSFRow row      = null;
			HSSFCell cell    = null;
			
			logger.debug("read data from excel file.");
			
			Iterator<Row> iter = sheet.rowIterator();
			
			int i=0;
			Cwd sbTrans = null;
			
			while(iter.hasNext()){
				row = (HSSFRow) iter.next();
				logger.debug("data row #  :: "+ i +" from excel file is being loaded.");
				
				if (i > 1  && i < lastRow - 1) {
					sbTrans = new Cwd();
					
					java.sql.Date date = null;
					String atmNumber   = null;
					String tsqNumber   = null;
					boolean reversal   = false;

					cell = row.getCell(1);

					/* 1 time */

					date = new Date(cell.getDateCellValue().getTime());

					sbTrans.setDate(date);

					cell = row.getCell(3);

					StringTokenizer stringTokenizer = new StringTokenizer(
							cell.getStringCellValue(), "|");

					while (stringTokenizer.hasMoreTokens()) {

						String info = stringTokenizer.nextToken().trim();

						// find reversal transaction

						if (this.findReversal(info)) {
							reversal = true;
						}

						/* 2 ATM Id */
						if (this.findAtmNumber(info)) {
							atmNumber = info;
							sbTrans.setAtmId(atmNumber);
						}

						/* 3 card number */
						if (this.findCardNumber(info)) {
							String cardNumber = info;
							sbTrans.setCardNumber(cardNumber);
						}

						/* 4 tsq number */
						if (this.findTsqNumber(info)) {
							tsqNumber = info;
							sbTrans.setTsq(tsqNumber);
						}

					}

					/* 6 */
					cell = row.getCell(4);

					/* 7 account number */
					sbTrans.setAccount(cell.getStringCellValue());

					/* 8 amount */
					cell = row.getCell(6);
					sbTrans.setAmount(cell.getNumericCellValue());

					/* 9 */
					if (checkDetial(row.getCell(3).getStringCellValue(),
							tsqNumber)) {
						if (sessionId == null)
							sessionId = getSessionId(date, atmNumber);

						sbTrans.setSessionId(sessionId);
						logger.debug(sbTrans.toString());
						
						double amt = sbTrans.getAmount(); 
						
						boolean bool  = beginDate.compareTo(date) <= 0;
						bool = bool&&endDate.compareTo(date) >= 0;
						bool = bool&&atmId.equals(atmNumber);
						bool = bool&&Math.abs(amt) > 0.001;
						if (bool)
							sbTransList.add(sbTrans);

						if (reversal)
							reversalTransList.add(sbTrans.getTsq());
					} else
						logger.debug("file format must be wrong, tsq or account number or cardnumber might be empty.");
				}
				
				i++;
			}
			
			logger.debug("there are "+sbTransList.size()+" records are read from excel file.");
			logger.debug("there are "+reversalTransList.size()+" reversal transactions includes in excel file.");
			
			this.removeRevTrans();
			logger.debug("reversal transactions have been remove. remain transaction number are "+sbTransList.size());
			
		} catch (Exception ex) {
			logger.debug("Exception occur while try to read data from excel file. please check insert file, format might be wrong.",ex);
			return false;
		}
		
		return true;
	}
	
	public void removeRevTrans(){
		
		Iterator<String> revIter = reversalTransList.iterator();
		
		while(revIter.hasNext()){
			String revTrans = revIter.next();
			for(int i=0;i<sbTransList.size();i++){
				Cwd sbTrans = sbTransList.get(i);
				if(revTrans.equals(sbTrans.getTsq()))
					sbTransList.remove(i);
				i++;
			}
		}
	}
	
	public boolean findReversal(String str){
		reversalMatcher = reversalPattern.matcher(str);
		if(reversalMatcher.find()){
			return true;
		}
		return false;
	}
	
	public boolean findCardNumber(String str){
		cardNumberMatcher = cardNumberPattern.matcher(str);
		
		if(cardNumberMatcher.find()){
			return true;
		}
		return false;
	}
	
	public boolean findAtmNumber(String str){
		atmNumberMatcher = atmNumberPattern.matcher(str);
		
		if(atmNumberMatcher.find()){
			return true;
		}
		return false;
	}
	
	public boolean findTsqNumber(String str){
		tsqNumberMatcher = tsqNumberPattern.matcher(str);
		
		if(tsqNumberMatcher.find()){
			return true;
		}
		
		return false;
	}
	
	public boolean checkDetial(String detial,String tsqNumber){
		int index = detial.indexOf("|");
		if(index < 0 || tsqNumber== null || tsqNumber.equals(""))
			return false;
		return true;
	}
	
	public String  getSessionId(Date date,String atmNumber){
		SimpleDateFormat dateformatMMDDYYYY = new SimpleDateFormat("yyyyMMdd");
		String yyyyMMdd = new String(dateformatMMDDYYYY.format(date));
		
		return yyyyMMdd+atmNumber;
	}
	
	public String getSessionId(){
		return this.sessionId;
	}
	
	public String getAtmId(){
		return this.atmId;
	}
	
	public Date getBeginDate(){
		return this.beginDate;
	}
	
	public Date getEndDate(){
		return this.endDate;
	}
}
