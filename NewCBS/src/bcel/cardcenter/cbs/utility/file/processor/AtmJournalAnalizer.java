package bcel.cardcenter.cbs.utility.file.processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;


import bcel.cardcenter.cbs.aarofat.entity.Cwd;
import bcel.cardcenter.cbs.utility.Utility;

import java.io.File;

public class AtmJournalAnalizer {
	/**
	 * mapping regular expression
	 */
	private final static String delimeter ="- ([0-9]{2}-[0-9]{2}) ([0-9]{2}:[0-9]{2}) [[0-9]{16,19}-[0-9]{6}|[0-9]{16,19}]";
	private final static String accountPattern = "A/C # : ([0-9a-zA-Z]{13,19})";
	private final static String cwdPattern = "CWD ([0-9]{16,19})  ([0-9,]+)";
	private final static String tsqPattern = "TSQ: ([0-9]{1,})";
	private final static String cwdTimeAcceptedPattern = "[0-9]{2}:[0-9]{2} CWD ACCEPTED";
	private final static String transStatePattern = "TRANS STATE:[^0-9]{0,}([0-9])";
	private final static String cardEjectPattern = "====Card (Ejected)======";
	private final static String cashPresentPattern = "([0-9]{1,2})s.";
	private final static String casettePattern ="T1[\\s]{0,}:[\\s]{0,}([0-9]{1,2})[\\s]{1,}T2[\\s]{0,}:[\\s]{0,}([0-9]{1,2})[\\s]{1,}T3[\\s]{0,}:[\\s]{0,}([0-9]{1,2})[\\s]{1,}T4[\\s]{0,}:[\\s]{0,}([0-9]{1,2})";
	private final static String rejectCodePattern = "REJ CODE=([0-9]{4})";
	
	/**
	 * pattern for No account in core bank
	 * 
	 */
	private final static String accountCpPattern = "[C|U|V]P+\\d";
	
	/**
	 * pattern object
	 */
	
	private final static String errorPattern1 = "CASH STACKER FAILED";
	private final static String errorPattern2 = "NO RESPONSE FROM SWITCH";
	private final static String errorPattern3 = "[0-9]{2}:[0-9]{2} OUT OF SERVICE";
	private final static String errorPattern4 = "DISPENSER FAILURE";
	private final static String errorPattern5 = "CARD READER FAILURE";
	private final static String errorPattern6 = "CARD TRACK READER ERROR";
	private final static String errorPattern7 = "COMMUNICATION FAILURE";
	private final static String errorPattern8 = "[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2} ATM [0-9]{6} NORMAL START";
	private final static String errorPattern9 = "Cardholder may be taken some of bills";
	private final static String errorPattern10 = "Cash\\(s\\) Not Accessed Had Purged";
	
	/**
	 * 
	 * Pattern for catch refill
	 * 
	 */
	
	//private final static String refillPattern1 = "[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2} SUPERVISOR [^0-9]{0,} ENTERED";
	private final static String refillPattern2 = "Remained[^0-9]{1,}:[^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0";
	private final static String refillPattern3 = "Dispensed[^0-9]{1,}:[^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0";
	private final static String refillPattern4 = "Purged[^0-9]{1,}:[^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0";
	private final static String refillPattern5 = "Adjusted[^0-9]{1,}:[^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0";
	private final static String refillPattern6 = "Total[^0-9]{1,}:[^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0";
	//private final static String refillPattern7 = "[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2} SUPERVISOR[^0-9]{1,}EXIT";
	
	/**
	 * 
	 * pattern object
	 * 
	 */
	
	private Pattern pattern;
	private Pattern patternCwd;
	private Pattern patternAc;
	private Pattern patternTsq;
	private Pattern patternTransState;
	private Pattern patternRejectCode;
	private Pattern patternCasette;
	private Pattern patternCwdTimeAccepted;
	private Pattern patternCashPresent;
	private Pattern patternCardEject;
	
	private Pattern patternAccountCp;
	
	private Pattern patternError1;
	private Pattern patternError2;
	private Pattern patternError3;
	private Pattern patternError4;
	private Pattern patternError5;
	private Pattern patternError6;
	private Pattern patternError7;
	private Pattern patternError8;
	private Pattern patternError9;
	private Pattern patternError10;
	
	/**
	 * Refill Pattern detector
	 */
	//private Pattern patternRefill1;
	private Pattern patternRefill2;
	private Pattern patternRefill3;
	private Pattern patternRefill4;
	private Pattern patternRefill5;
	private Pattern patternRefill6;
	//private Pattern patternRefill7;
	
	/**
	 * matcher
	 */
	
	private Matcher matcher;
	
	/**
	 * internal field
	 */
	private static final Logger logger = Logger.getLogger(AtmJournalAnalizer.class);
	
	private File file;
	private String year;
	private String sessionId;
	private String atmId;
	private Date beginDate;
	private Date endDate;
	private int init;
	private int status;
	
	/**
	 * meta data
	 */
	private List<String> transBuffer;
	
	/**
	 * target object
	 *
	 */
	private List<Cwd> cwdEJMasterList;
	
	public AtmJournalAnalizer() {
		
		
		this.transBuffer  = new ArrayList<String>();
		this.init         = 0;
		this.status 	  = 0;
		
		pattern                = Pattern.compile(delimeter);
		patternCwd             = Pattern.compile(cwdPattern);
		patternAc              = Pattern.compile(accountPattern);
		patternTsq             = Pattern.compile(tsqPattern);
		patternTransState      = Pattern.compile(transStatePattern);
		patternRejectCode      = Pattern.compile(rejectCodePattern);
		patternCasette         = Pattern.compile(casettePattern);
		patternCwdTimeAccepted = Pattern.compile(cwdTimeAcceptedPattern);
		patternCashPresent     = Pattern.compile(cashPresentPattern);
		patternCardEject       = Pattern.compile(cardEjectPattern);
		
		patternAccountCp       = Pattern.compile(accountCpPattern);
		
		patternError1          = Pattern.compile(errorPattern1);
		patternError2          = Pattern.compile(errorPattern2);
		patternError3          = Pattern.compile(errorPattern3);
		patternError4          = Pattern.compile(errorPattern4);
		patternError5          = Pattern.compile(errorPattern5);
		patternError6          = Pattern.compile(errorPattern6);
		patternError7          = Pattern.compile(errorPattern7);
		patternError8          = Pattern.compile(errorPattern8);
		patternError9          = Pattern.compile(errorPattern9);
		patternError10         = Pattern.compile(errorPattern10);
		
		// refill pattern
		
		//patternRefill1         = Pattern.compile(refillPattern1);
		patternRefill2         = Pattern.compile(refillPattern2);
		patternRefill3         = Pattern.compile(refillPattern3);
		patternRefill4         = Pattern.compile(refillPattern4);
		patternRefill5		   = Pattern.compile(refillPattern5);
		patternRefill6         = Pattern.compile(refillPattern6);
		//patternRefill7         = Pattern.compile(refillPattern7);
		
		cwdEJMasterList = new ArrayList<Cwd>();
		
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAtmId() {
		return atmId;
	}

	public boolean analize(){
		
		try{
			
            FileReader f = new FileReader(file);
            BufferedReader b = new BufferedReader(f);
            String s;
            int count=0;
            
            while((s = b.readLine())!=null){
            	
                matcher = pattern.matcher(s);
                
                if(matcher.find()&&(count!=0))
                	processBuffer();
                
                transBuffer.add(s.replace("*", "").trim());
                
                count=1;
                
            }
            
            /**
             *  process last transaction
             */
            
            processBuffer();
			
        }catch(Exception e){
           logger.debug("Exception occur while try to E-Journal file",e);
           return false;
        }
        return true;
	}
	/**
	 * 
	 * @return
	 */
	public List<Cwd> getCwdEJMaster(){
		return this.cwdEJMasterList;
	}
	int number = 0;
	public void processBuffer(){
		
		if (!transBuffer.isEmpty()) {
			setYear(beginDate);
			int cwdIndex = findCwd(transBuffer);
			
			if(findRefillMark(transBuffer)){
				logger.debug("found refill mark status : "+status);
				number++;
				if(number==1){
					changeStatus();
					logger.debug("status go to"+status);
					cwdIndex  = -1;
				}
				
			}
			
			if (cwdIndex != -1&& status!=0 && number >0) {
				
				 Cwd cwdEJMaster = setDetailToCWDEJMaster(cwdIndex, transBuffer);
				
				if (cwdEJMaster.getTransId() != null
						&& beginDate.compareTo(cwdEJMaster.getDate()) <= 0
						&& endDate.compareTo(cwdEJMaster.getDate()) >= 0) {
					logger.debug("EJ add data :");
					logger.debug(cwdEJMaster.toString());
					cwdEJMasterList.add(cwdEJMaster);
				}
				
			}
			
			if(number == 2){
				logger.debug("status go to"+status);
				changeStatus();
				return;
			}
			
		}
		transBuffer.clear();
	}
	
	private void setYear(Date beginDate2) {
		try {
			String date = Utility.date2Str(beginDate);
			this.setYear(date.substring(0, 4));
		} catch (Exception e) {
			logger.debug("Exception occur at setYear of AtmJournalAnalizer", e);
		}
		
	}

	/**
	 * 
	 * @param cwdIndex
	 * @param transBuffer
	 * @param cwdEJMaster
	 */
	public Cwd setDetailToCWDEJMaster(int cwdIndex, List<String> transBuffer) {
		
		Cwd cwdEJMaster = new Cwd();
		
		String bufferAtFirst = transBuffer.get(0);
		String bufferAtCwd   = transBuffer.get(cwdIndex);
		
		String date = null;
		String time = null;
		String transId1 = null;
		
		matcher = pattern.matcher(bufferAtFirst);
		
		if(matcher.find()){
			date = matcher.group(1);
			time = matcher.group(2);
		}
		
		matcher = patternCwd.matcher(bufferAtCwd);
		
		if(matcher.find()){
			
			java.sql.Date date1 = Utility.str2Date(year+"-"+date);
			
			if(init==0)
			{
				sessionId = year+date.replace("-", "")+atmId;
				init = 1;
			}
			
			String cardNumber = matcher.group(1);
			transId1 = year+date.replace("-", "")+time.replace(":", "")+atmId;
			double amount = Double.parseDouble(matcher.group(2).replace(",",""));
			
			cwdEJMaster.setDate(date1);
			cwdEJMaster.setAtmId(atmId);
			cwdEJMaster.setSessionId(sessionId);
			cwdEJMaster.setCardNumber(cardNumber);
			cwdEJMaster.setAmount(amount);
			
		}
		
		for(int i = cwdIndex-1;i>=0;i--){
			String elementAt = transBuffer.get(i);

			matcher = this.patternAc.matcher(elementAt);

			if (matcher.find()) {
				String account = matcher.group(1);
				
				if(patternAccountCp.matcher(account).find()){
					account=account.substring(0, 2);
				}
				cwdEJMaster.setAccount(account);
				break;
			}
		}
		
		/**
		*
		*  insert others detail to object
		*  
		*/
		
		int index = 0;
		int flag = 0;
		
		for(int i=cwdIndex;i<transBuffer.size();i++){
			String s = transBuffer.get(i);
			matcher = patternTsq.matcher(s);
			
			if(matcher.find()){
				if(index == 0){
					String tsq = matcher.group(1);
					
					while(tsq.length()<6){
						tsq="0"+tsq;
					}
					
					cwdEJMaster.setTsq(tsq);
					String transId = transId1+tsq;
					cwdEJMaster.setTransId(transId);
					index = 1;
				}
			}
			
			matcher = this.patternTransState.matcher(s);
			
			if(matcher.find()){
				int state = Integer.parseInt(matcher.group(1));
				cwdEJMaster.setState(state);
			}
			
			// accept time
			
			matcher = this.patternCwdTimeAccepted.matcher(s);
			
			if(matcher.find()){
				int glWithdraw = 1;
				cwdEJMaster.setGlWithdraw(glWithdraw);
			}
			
			/**
			 *  reject code and error code
			 */
			matcher = this.patternRejectCode.matcher(s);

			if (matcher.find()) {
				if (flag == 0) {
					String rejectCode = matcher.group(1);
					cwdEJMaster.setStatus(rejectCode);
					flag = 1;
				}
			}
			
			matcher = this.patternError1.matcher(s);
			
			if(matcher.find()){
				if (flag == 0) {
					String rejectCode = "0001";
					cwdEJMaster.setStatus(rejectCode);
					flag = 1;
				}
			}
			
			matcher = this.patternError2.matcher(s);
			if(matcher.find()){
				if (flag == 0) {
					String rejectCode = "0002";
					cwdEJMaster.setStatus(rejectCode);
					flag = 1;
				}
			}
			
			matcher = this.patternError3.matcher(s);
			if(matcher.find()){
				if (flag == 0) {
					String rejectCode = "0003";
					cwdEJMaster.setStatus(rejectCode);
					flag = 1;
				}
			}
			
			matcher = this.patternError4.matcher(s);
			if(matcher.find()){
				if (flag == 0) {
					String rejectCode = "0004";
					cwdEJMaster.setStatus(rejectCode);
					flag = 1;
				}
			}
			
			matcher = this.patternError5.matcher(s);
			if(matcher.find()){
				if (flag == 0) {
					String rejectCode = "0005";
					cwdEJMaster.setStatus(rejectCode);
					flag = 1;
				}
			}
			
			matcher = this.patternError6.matcher(s);
			if(matcher.find()){
				if(flag==0){
					String rejectCode = "0006";
					cwdEJMaster.setStatus(rejectCode);
					flag = 1;
				}
			}
			
			matcher = this.patternError7.matcher(s);
			if(matcher.find()){
				if (flag == 0) {
					String rejectCode = "0007";
					cwdEJMaster.setStatus(rejectCode);
					flag = 1;
				}
			}
			
			matcher = this.patternError8.matcher(s);
			if(matcher.find()){
				if(flag==0){
					String rejectCode="0008";
					cwdEJMaster.setStatus(rejectCode);
					flag=1;
				}
			}
			
			matcher = this.patternError9.matcher(s);
			if(matcher.find()){
				
				if(flag==0){
					String rejectCode="0009";
					cwdEJMaster.setStatus(rejectCode);
					flag=1;
				}
			}
			
			matcher = this.patternError10.matcher(s);
			if(matcher.find()){
				
				if(flag==0){
					String rejectCode="0010";
					cwdEJMaster.setStatus(rejectCode);
					flag=1;
				}
			}
			
			/**
			 * more detail
			 */
			
			matcher = this.patternCardEject.matcher(s);
			
			if(matcher.find()){
				int cardEject = 1;
				cwdEJMaster.setCardEject(cardEject);
			}
			
			matcher = this.patternCashPresent.matcher(s);
			
			if(matcher.find()){
				int acceptTime = Integer.parseInt(matcher.group(1));
				cwdEJMaster.setAcceptTime(acceptTime);
			}
			
			matcher = this.patternCasette.matcher(s);
			
			if(matcher.find()){
				int t1=Integer.parseInt(matcher.group(1));
				int t2=Integer.parseInt(matcher.group(2));
				int t3=Integer.parseInt(matcher.group(3));
				int t4=Integer.parseInt(matcher.group(4));
				
				cwdEJMaster.setT1(t1);
				cwdEJMaster.setT2(t2);
				cwdEJMaster.setT3(t3);
				cwdEJMaster.setT4(t4);
			}
			
		}
		
		return cwdEJMaster;

	}
	
	/**
	 * find CWD index number
	 * @param transBuffer
	 * @return
	 */
	public int findCwd(List<String> transBuffer){
		
		Iterator<String> iter = transBuffer.iterator();
		int count = 0;
		
		while(iter.hasNext()){
			String s = iter.next();
			
			matcher = patternCwd.matcher(s);
			
			if(matcher.find())
				return count;
			
			count++;
		}
		
		return -1;
	}
	
	/**
	 * detect refill transaction
	 * @param transBuffer
	 * @return
	 */
	
	public boolean findRefillMark(List<String> transBuffer){
		boolean cond=false;
		
		int length = transBuffer.size();
		for(int i=0;i<length;i++){
			
			String buffer = transBuffer.get(i);
			
			// for [0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2} SUPERVISOR [^0-9]{0,} ENTERED
			/*
			matcher = this.patternRefill1.matcher(buffer);
			if(matcher.find())
				cond1=true;
			*/
			/**
			*	Remained [^0-9]{1,} : [^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0
			*	Dispensed [^0-9]{1,} : [^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0
			*	Purged [^0-9]{1,} : [^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0
			*	Adjusted [^0-9]{1,} : [^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0
			*	Total [^0-9]{1,} : [^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0[^0-9]{1,}0
			*/
			
			matcher = this.patternRefill2.matcher(buffer);
			if(matcher.find()){
				String buf2 = transBuffer.get(i+1);
				String buf3 = transBuffer.get(i+2);
				String buf4 = transBuffer.get(i+3);
				String buf5 = transBuffer.get(i+5);
				
				Matcher match01 = this.patternRefill3.matcher(buf2);
				Matcher match02 = this.patternRefill4.matcher(buf3);
				Matcher match03 = this.patternRefill5.matcher(buf4);
				Matcher match04 = this.patternRefill6.matcher(buf5);
				
				cond = match01.find()&&match02.find()&&match03.find()&&match04.find();
				
			}
			
			// [0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2} SUPERVISOR [^0-9]{0,} EXIT
			/*
			matcher = this.patternRefill7.matcher(buffer);
			if(matcher.find())
				cond3=true;
			*/
		}
		
		
		return cond;
	}
	
	public void changeStatus(){
		if(status==0)
			status=1;
		else
			status=0;
	}
	
	public void setYear(String year){
		this.year=year;
	}
	
	public void setAtmId(String atmId){
		this.atmId=atmId;
	}
}
