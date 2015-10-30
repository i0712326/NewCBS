package bcel.cardcenter.cbs.varofat.service;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.log4j.Logger;

import bcel.cardcenter.cbs.varofat.entity.VisaSettle;

public class VisaTranxReaderSettlement extends VisaTranxReader<VisaSettle> {
	static final Logger logger = Logger.getLogger(VisaTranxReaderSettlement.class);
	private List<VisaSettle> visaSettles;
	private File file;
	@Override
	public String readLine(File file) {
		this.file = file;
		return null;
	}

	@Override
	public VisaSettle separateToEntities(String lin) {
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fileInputStream);
			BufferedReader readBuffer = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while((line = readBuffer.readLine())!=null){
				
			}
			
		} catch (FileNotFoundException e) {
			logger.debug("Exception occur while try to read date from settlement file", e);
			return null;
		} catch (IOException e) {
			logger.debug("Exception occur while try to read data from visa settlement file", e);
			return null;
		} 
		return null;
	}

	@Override
	public void saveList(VisaSettle t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<VisaSettle> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
