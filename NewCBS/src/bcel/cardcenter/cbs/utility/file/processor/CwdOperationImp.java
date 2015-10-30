package bcel.cardcenter.cbs.utility.file.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

import bcel.cardcenter.cbs.aarofat.dao.ErrorDao;
import bcel.cardcenter.cbs.aarofat.entity.Cwd;
import bcel.cardcenter.cbs.aarofat.entity.Error;
import bcel.cardcenter.cbs.aarofat.entity.ErrorTxn;

public class CwdOperationImp implements CwdOperation{
	final static private Logger logger = Logger.getLogger(CwdOperationImp.class);
	private List<Cwd> sbTransList;
	private List<Cwd> cwdEjmasterList;
	private List<ErrorTxn> ejReports;
	private List<ErrorTxn> sbReports;
	private List<Error> errorList;
	
	// business logic for download list for error
	
	private ErrorDao errorDao;
	
	public List<Cwd> getSbTransList() {
		return sbTransList;
	}
	@Override
	public void setSbTransList(List<Cwd> sbTransList) {
		this.sbTransList = sbTransList;
	}
	
	public List<Cwd> getCwdEjmasterList() {
		return cwdEjmasterList;
	}
	@Override
	public void setCwdEjmasterList(List<Cwd> cwdEjmasterList) {
		this.cwdEjmasterList = cwdEjmasterList;
	}
	@Override
	public List<ErrorTxn> getEjReport() {
		return ejReports;
	}
	@Override
	public List<ErrorTxn> getSbReport() {
		return sbReports;
	}
	
	public void setErrorDao(ErrorDao errorDao){
		this.errorDao = errorDao;
	}
	/**
	 * process transactions
	 * 
	 */
	@Override
	public void processEntities(){
		
		// initializer report
		this.ejReports = new ArrayList<ErrorTxn>();
		this.sbReports = new ArrayList<ErrorTxn>();
		
		// error list initiation
		
		initError();
		
		// get transaction of non-gl debit
		buildSbReport();
		
		// get transaction of customer doesn't get money
		
		buildEjReport();            // general report
		buildDuplicateTrans();      // duplication transaction
		buildCustNoTakeCash();      // Customer does not take money
		buildNotAccessPurge();      // cash not access purge
		buildNoExistInEj();         // Check for Uncompleted Ejournal
		
	}
	
	/**
	 * 
	 * initiate error code and responded description
	 */
	private void initError() {
		try {
			this.errorList = errorDao.initErrorList();
		} catch (DocumentException e) {
			logger.debug("exception occur while try to read xml", e);
		}
	}
	/**
	 * find error description which responded to code
	 * @param code : error code
	 * @return
	 */
	private String findDescription(String code) {
		Iterator<Error> iterError = errorList.iterator();
		while(iterError.hasNext()){
			Error error = iterError.next();
			
			if(error.getCode().equals(code)){
				return error.getDescription();
			}
		}
		return null;
	}
	
	/**
	 *  find cash not access purge transaction
	 * 
	 * SELECT EJDATE, CARDNUMBER, TSQ, AMOUNT, ACCOUNTNUMBER AS AC, DESCRIPTION AS NOTICE
 	 * FROM CWD_EJMASTER, REJECTCODE WHERE  SESSIONID = ? AND REJECTCODE='0010'
	 * AND CWD_EJMASTER.REJECTCODE = REJECTCODE.CODE 
	 */
	
	private void buildNotAccessPurge() {
		Iterator<Cwd> cwdEjIter = cwdEjmasterList.iterator();
		
		while(cwdEjIter.hasNext()){
			
			Cwd cwdEj = cwdEjIter.next();
			ErrorTxn report = new ErrorTxn();
			
			report.setTsq(cwdEj.getTsq());
			report.setAtmId(cwdEj.getAtmId());
			report.setAccountNumber(cwdEj.getAccount());
			report.setAmount(cwdEj.getAmount());
			report.setDate(cwdEj.getDate());
			report.setCardNumber(cwdEj.getCardNumber());
			
			String error = cwdEj.getRejectCode();
			
			if(error.equals("0010")){
				String notice = findDescription(error);
				report.setNotice(notice);
				report.setType("0001");
				ejReports.add(report);
			}
			
		}
	}
	
	/**
	 * 
	 * find customer may take some of bills
	 * 
	 * SELECT EJDATE, CARDNUMBER, TSQ, AMOUNT, ACCOUNTNUMBER AS AC, DESCRIPTION AS NOTICE
	 * FROM CWD_EJMASTER, REJECTCODE WHERE  SESSIONID = ? AND REJECTCODE='0009'
	 * AND CWD_EJMASTER.REJECTCODE = REJECTCODE.CODE
	 */
	private void buildCustNoTakeCash() {
		
		Iterator<Cwd> cwdEjIter = cwdEjmasterList.iterator();
		
		while(cwdEjIter.hasNext()){
			
			Cwd cwdEj = cwdEjIter.next();
			ErrorTxn report = new ErrorTxn();
			
			report.setTsq(cwdEj.getTsq());
			report.setAtmId(cwdEj.getAtmId());
			report.setAccountNumber(cwdEj.getAccount());
			report.setAmount(cwdEj.getAmount());
			report.setDate(cwdEj.getDate());
			report.setCardNumber(cwdEj.getCardNumber());
			
			String code = cwdEj.getRejectCode();
			
			if(code.equals("0009")){
				String notice = this.findDescription(code);
				report.setNotice(notice);
				report.setType("0001");
				ejReports.add(report);
			}
			
		}
	}
	
	
	/**
	 * 
	 * find duplicate transactions
	 * 
	 * SELECT EJDATE, CARDNUMBER, TSQ, AMOUNT, ACCOUNTNUMBER AS AC FROM CWD_EJMASTER WHERE  SESSIONID = ? AND 
	 * TSQ IN ( SELECT DISTINCT (TSQ) FROM SB_TRANS WHERE SESSIONID = ? GROUP BY TSQ HAVING COUNT(*) > 1 )
	 */
	
	private void buildDuplicateTrans() {
		
		List<String> tsqList = new ArrayList<String>();
		
		Iterator<Cwd> transIter = sbTransList.iterator();
		
		while(transIter.hasNext()){
			Cwd trans = transIter.next();
			if(!trans.getStatus().equals("0001"))
				tsqList.add(trans.getTsq());
		}
		
		List<String> dupliTsq = findDupliTsqList(tsqList);
		
		Iterator<Cwd> cwdEjIter = cwdEjmasterList.iterator();
		
		while(cwdEjIter.hasNext()){
			Cwd cwdEj = cwdEjIter.next();
			String ejTsq = cwdEj.getTsq();
			
			ErrorTxn report = new ErrorTxn();
			
			report.setTsq(cwdEj.getTsq());
			report.setAtmId(cwdEj.getAtmId());
			report.setAccountNumber(cwdEj.getAccount());
			report.setAmount(cwdEj.getAmount());
			report.setDate(cwdEj.getDate());
			report.setCardNumber(cwdEj.getCardNumber());
			
			Iterator<String> dupliTsqIter = dupliTsq.iterator();
			while(dupliTsqIter.hasNext()){
				String tsq = dupliTsqIter.next();
				
				if(ejTsq.equals(tsq)){
					report.setNotice("DUPLICATED TRANSACTION");
					report.setType("0001");
					ejReports.add(report);
				}
			}
			
			
		}
	}
	
	/**
	 * Checking duplicate TSQ
	 * @param buffers
	 * @return
	 */
	private List<String> findDupliTsqList(List<String> buffers){
		
		List<String> duplis = new ArrayList<String>();
		
		int length = buffers.size();
		
		Collections.sort(buffers);
		
		for(int i=0;i<length;i++){
			String buffer = buffers.get(i);
			
			for(int j=i+1;j<length;j++){
				
				String s = buffers.get(j);
				
				if(buffer.equals(s)){
						duplis.add(buffer);
				}
			}
			
		}
		
		return duplis;
	}
	
	/**
	 * Customer does not get money transaction
	 * 
	 * SELECT EJDATE, CARDNUMBER, TSQ, AMOUNT, ACCOUNTNUMBER AS AC,  DESCRIPTION AS NOTICE 
     * FROM CWD_EJMASTER, REJECTCODE WHERE SESSIONID = P_SESSIONID AND ACCEPTTIME = 0 
     * AND T1 = 0 AND T2 = 0 AND T3 = 0 AND T4 = 0 AND CWD_EJMASTER.REJECTCODE = REJECTCODE.CODE AND
     * ( TSQ IN (SELECT TSQ FROM SB_TRANS WHERE SESSIONID = P_SESSIONID AND STATUS !='0001'))
	 * 
	 * */
	private void buildEjReport() {
		
		List<Cwd> cwdEjBuffers = new ArrayList<Cwd>();
		Iterator<Cwd> cwdEjIter = cwdEjmasterList.iterator();
		
		while (cwdEjIter.hasNext()) {
			Cwd cwd = cwdEjIter.next();
			boolean cond = cwd.getT1() == 0 && cwd.getT2() == 0
					&& cwd.getT3() == 0 && cwd.getT4() == 0
					&& cwd.getAcceptTime() == 0;

			if (cond)
				cwdEjBuffers.add(cwd);
		}
		
		Iterator<Cwd> cwdEjBufferIter = cwdEjBuffers.iterator();
		
		while (cwdEjBufferIter.hasNext()) {
			Cwd cwdEj = cwdEjBufferIter.next();
			String ejTsq = cwdEj.getTsq();
			String ejSessionId = cwdEj.getSessionId();
			
			ErrorTxn report = new ErrorTxn();
			
			report.setTsq(cwdEj.getTsq());
			report.setAtmId(cwdEj.getAtmId());
			report.setAccountNumber(cwdEj.getAccount());
			report.setAmount(cwdEj.getAmount());
			report.setDate(cwdEj.getDate());
			report.setCardNumber(cwdEj.getCardNumber());

			Iterator<Cwd> sbTransIter = sbTransList.iterator();

			while (sbTransIter.hasNext()) {
				Cwd sbTrans = sbTransIter.next();
				String sbSessionId = sbTrans.getSessionId();
				if (ejTsq.equals(sbTrans.getTsq())
						&& !sbTrans.getStatus().equals("0001")
						&& ejSessionId.equals(sbSessionId)) {
					String code = cwdEj.getRejectCode();
					String notice = findDescription(code);
					report.setNotice(notice);
					report.setType("0001");
					ejReports.add(report);
					break;
				}
			}

		}
		
	}

	/**
	 * 
	 *  Check for Uncompleted E-journal transaction
	 *  But exist on core banking file
	 */
	
	private void buildNoExistInEj() {
		Iterator<Cwd> sbTransIter = sbTransList.iterator();
		while (sbTransIter.hasNext()) {
			Cwd sbTrans = sbTransIter.next();
			String sbTsq = sbTrans.getTsq();
			boolean check = false;
			Iterator<Cwd> cwdEjIter = cwdEjmasterList.iterator();
			while (cwdEjIter.hasNext()) {
				Cwd cwdEjMaster = cwdEjIter.next();
				String ejTsq = cwdEjMaster.getTsq();
				if (sbTsq.equals(ejTsq)) {
					check=true;
					break;
				}
			}

			if (!check) {
				
				ErrorTxn report = new ErrorTxn();
				
				report.setTsq(sbTrans.getTsq());
				report.setAmount(sbTrans.getAmount());
				report.setAccountNumber(sbTrans.getAccount().replace(".", ""));
				report.setCardNumber(sbTrans.getCardNumber());
				report.setAtmId(sbTrans.getAtmId());
				report.setDate(sbTrans.getDate());
				report.setNotice("NOT EXIST ON EJOURNAL");
				report.setType("0001");
				
				this.ejReports.add(report);
			}
		}
	}

	/**
	 *  No-GL Withdraw Transaction
	 * SELECT EJDATE, CARDNUMBER, TSQ, AMOUNT, ACCOUNTNUMBER as AC, DESCRIPTION AS NOTICE FROM CWD_EJMASTER, REJECTCODE 
     * WHERE SESSIONID = P_SESSIONID AND CWD_EJMASTER.REJECTCODE = REJECTCODE.CODE AND 
     * ACCEPTTIME !=0 AND ( T1 != 0 OR T2 != 0 OR T3 != 0 OR T4 != 0 )
     * AND ( TSQ NOT IN ( SELECT TSQ FROM SB_TRANS WHERE SESSIONID = P_SESSIONID AND STATUS !='0001'))
	 */
	
	private void buildSbReport(){
		
		List<Cwd> cwdEjBuffers = new ArrayList<Cwd>();
		Iterator<Cwd> cwdEjIter = cwdEjmasterList.iterator();
		
		while(cwdEjIter.hasNext()){
			Cwd cwd = cwdEjIter.next();
			
			boolean cond = (cwd.getT1() != 0 || cwd.getT2() != 0
					|| cwd.getT3() != 0 || cwd.getT4() != 0 ) &&
					cwd.getAcceptTime() != 0;
			
			if(cond)
				cwdEjBuffers.add(cwd);
		}
		
		Iterator<Cwd> bufferIter = cwdEjBuffers.iterator();
		while(bufferIter.hasNext()){
			Cwd cwdEj = bufferIter.next();
			String sessionId = cwdEj.getSessionId();
			String ejTsq = cwdEj.getTsq();
			
			ErrorTxn report = new ErrorTxn();
			
			report.setTsq(cwdEj.getTsq());
			report.setAtmId(cwdEj.getAtmId());
			report.setAccountNumber(cwdEj.getAccount());
			report.setAmount(cwdEj.getAmount());
			report.setDate(cwdEj.getDate());
			report.setCardNumber(cwdEj.getCardNumber());
			
			if(checkTsq(ejTsq,sessionId)){
				String code = cwdEj.getRejectCode();
				String notice = findDescription(code);
				report.setNotice(notice);
				report.setType("0000");
				sbReports.add(report);
			}
		}
		
	}

	private boolean checkTsq(String ejTsq,String sessionId) {
		
		Iterator<Cwd> sbTransIter = sbTransList.iterator();
		
		while (sbTransIter.hasNext()) {
			Cwd sbTrans = sbTransIter.next();
			//String sbSessionId = sbTrans.getSessionId();
			if (ejTsq.equals(sbTrans.getTsq())){//&&sessionId.equals(sbSessionId)) {
				return false;
			}
		}
		return true;
	}
}
