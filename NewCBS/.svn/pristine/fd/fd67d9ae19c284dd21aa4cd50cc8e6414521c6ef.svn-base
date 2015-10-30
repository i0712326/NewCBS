package bcel.cardcenter.cbs.utility.file.processor;
import java.io.File;
import java.sql.Date;
import java.util.List;

import bcel.cardcenter.cbs.aarofat.entity.Cwd;

public class FileProcessorImp implements FileProcessor<List<Cwd>> {
	private File file;
	private Date start;
	private Date end;
	private String atmId;
	private List<Cwd> cwds;
	private FileProcessChain<List<Cwd>> atmGlProcess;
	private FileProcessChain<List<Cwd>> atmJournalProcess;
	
	public void setAtmGlProcess(FileProcessChain<List<Cwd>> atmGlProcess){
		this.atmGlProcess = atmGlProcess;
	}
	public void setAtmJournalProcess(FileProcessChain<List<Cwd>> atmJournalProcess){
		this.atmJournalProcess = atmJournalProcess;
	}
	
	@Override
	public void setFile(File file) {
		this.file = file;
	}
	@Override
	public void setStart(Date start) {
		this.start = start;
	}
	@Override
	public void setEnd(Date end) {
		this.end = end;
	}
	@Override
	public void setAtmId(String atmId) {
		this.atmId = atmId;
	}
	@Override
	public void process() {
		
		// build chain for process file
		atmGlProcess.setNextProcess(atmJournalProcess);
		
		// process file
		setCwds(atmGlProcess.fileProcess(file, start, end, atmId));
	}
	
	public void setCwds(List<Cwd> cwds){
		this.cwds = cwds;
	}
	
	public List<Cwd> getCwds(){
		return this.cwds;
	}

	@Override
	public List<Cwd> getData() {
		return this.getCwds();
	}
}
