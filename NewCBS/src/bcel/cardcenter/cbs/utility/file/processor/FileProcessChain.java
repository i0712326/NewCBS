package bcel.cardcenter.cbs.utility.file.processor;

import java.io.File;
import java.sql.Date;

public interface FileProcessChain<T> {
	public void setNextProcess(FileProcessChain<T> fileProcessChain);
	public T fileProcess(File file, Date start, Date end, String atmId);
}
