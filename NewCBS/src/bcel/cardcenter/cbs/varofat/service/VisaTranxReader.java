package bcel.cardcenter.cbs.varofat.service;

import java.io.File;
import java.util.List;

public abstract class VisaTranxReader <T>{
	public List<T> readFileToEntities(File file){
		String line = readLine(file);
		T t = separateToEntities(line);
		saveList(t);
		return getList();
	}
	
	public abstract String readLine(File file);
	public abstract T separateToEntities(String line);
	public abstract void saveList(T t);
	public abstract List<T> getList();
}
