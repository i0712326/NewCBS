package bcel.cardcenter.cbs.utility.atm;

import java.util.List;
import java.sql.Date;
import java.io.File;

import bcel.cardcenter.cbs.aarofat.entity.Cwd;
import bcel.cardcenter.cbs.aarofat.entity.ErrorTxn;
import bcel.cardcenter.cbs.utility.file.processor.FileProcessor;
import bcel.cardcenter.cbs.utility.file.processor.CwdOperation;

public class CwdProcessUtilImp implements CwdProcessUtil {
	// return result
	private List<ErrorTxn> noGlDebit;
	private List<ErrorTxn> accountDebit;
	
	// set input parameter
	private List<File> files;
	private String atmId;
	private Date begin;
	private Date end;
	
	// business logic
	
	private FileProcessor<List<Cwd>> fileProcessor;
	private CwdOperation cwdOperation;
	
	// setter for input parameter
	@Override
	public void setFiles(List<File> files) {
		this.files = files;
	}
	@Override
	public void setAtmId(String atmId) {
		this.atmId = atmId;
	}
	@Override
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	@Override
	public void setEnd(Date end) {
		this.end = end;
	}
	public void setFileProcessor(FileProcessor<List<Cwd>> fileProcessor){
		this.fileProcessor = fileProcessor;
	}
	public void setCwdOperation(CwdOperation cwdOperation){
		this.cwdOperation = cwdOperation;
	}
	@Override
	public void cwdProcessUtil() {
		
		// set static parameter
		fileProcessor.setAtmId(atmId);
		fileProcessor.setEnd(end);
		fileProcessor.setStart(begin);
		
		// retrieve data from journal file
		File statementFile =  files.get(0);
		fileProcessor.setFile(statementFile);
		fileProcessor.process();
		
		List<Cwd> statementCwds = fileProcessor.getData();
		
		// retrieve data from ATM journal
		File ejFile = files.get(1);
		fileProcessor.setFile(ejFile);
		fileProcessor.process();
		List<Cwd> ejCwds = fileProcessor.getData();
		
		// perform data operation
		cwdOperation.setCwdEjmasterList(ejCwds);
		cwdOperation.setSbTransList(statementCwds);
		cwdOperation.processEntities();
		
		setAccountDebit(cwdOperation.getEjReport());
		setNoGlDebit(cwdOperation.getSbReport());
	}
	@Override
	public List<ErrorTxn> getNoGlDebit() {
		return this.noGlDebit;
	}
	private void setNoGlDebit(List<ErrorTxn> noGlDebit){
		this.noGlDebit=noGlDebit;
	}
	@Override
	public List<ErrorTxn> getAccountDebit() {
		return this.accountDebit;
	}
	private void setAccountDebit(List<ErrorTxn> accountDebit){
		this.accountDebit=accountDebit;
	}

}
