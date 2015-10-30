package bcel.cardcenter.cbs.utility.file.processor;

import java.io.File;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import bcel.cardcenter.cbs.aarofat.entity.Cwd;

public class AtmGlProcess implements FileProcessChain<List<Cwd>> {
	static final private String REVERSAL = "0001";
	private FileProcessChain<List<Cwd>> fileProcessChain;
	private List<String> reversals;
	
	@Override
	public void setNextProcess(FileProcessChain<List<Cwd>> fileProcessChain) {
		this.fileProcessChain=fileProcessChain;
	}

	@Override
	public List<Cwd> fileProcess(File file, Date start, Date end, String atmId) {
		AtmGlAnalizer atmGlAnalizer = new AtmGlAnalizer();
		atmGlAnalizer.setFile(file);
		atmGlAnalizer.setBeginDate(start);
		atmGlAnalizer.setEndDate(end);
		atmGlAnalizer.setAtmId(atmId);
		if(atmGlAnalizer.analize()){
			setReversals(atmGlAnalizer.getReversalTransList());
			setStatus(atmGlAnalizer.getSBTransList());
			return atmGlAnalizer.getSBTransList();
		}
		return fileProcessChain.fileProcess(file, start, end, atmId);
	}
	
	private void setReversals(List<String> reversals){
		this.reversals =  reversals;
	}
	
	private List<String> getReversals(){
		return this.reversals;
	}
	
	private void setStatus(List<Cwd> sbTransList) {
		Iterator<Cwd> iter = sbTransList.iterator();
		while(iter.hasNext()){
			Cwd sbTrans = iter.next();
			if(isReversal(sbTrans.getTsq())){
				sbTrans.setStatus(REVERSAL);
			}
		}
	}
	
	private boolean isReversal(String tsq) {
		Iterator<String> iter = getReversals().iterator();
		while(iter.hasNext()){
			String reversalTsq = iter.next();
			if(tsq.equals(reversalTsq))
				return true;
		}
		return false;
	}

}
