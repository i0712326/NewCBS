package bcel.cardcenter.cbs.utility.file.processor;

import java.io.File;

import bcel.cardcenter.cbs.aarofat.entity.Cwd;

import java.sql.Date;
import java.util.List;

public class AtmJournalProcess implements FileProcessChain<List<Cwd>>{
	private FileProcessChain<List<Cwd>> fileProcessChain;
	
	@Override
	public List<Cwd> fileProcess(File file, Date start, Date end, String atmId) {
		AtmJournalAnalizer atmJournalAnalizer = new AtmJournalAnalizer();
		atmJournalAnalizer.setFile(file);
		atmJournalAnalizer.setBeginDate(start);
		atmJournalAnalizer.setEndDate(end);
		atmJournalAnalizer.setAtmId(atmId);
		
		if(atmJournalAnalizer.analize()){
			return atmJournalAnalizer.getCwdEJMaster();
		}
		return fileProcessChain.fileProcess(file, start, end, atmId);
		
	}

	@Override
	public void setNextProcess(FileProcessChain<List<Cwd>> fileProcessChain) {
		this.fileProcessChain=fileProcessChain;
	}

}
