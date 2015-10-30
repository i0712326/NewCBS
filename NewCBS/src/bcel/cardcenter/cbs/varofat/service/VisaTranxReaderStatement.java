package bcel.cardcenter.cbs.varofat.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.common.Logger;

import bcel.cardcenter.cbs.varofat.entity.VisaState;

public class VisaTranxReaderStatement extends VisaTranxReader<VisaState> {
	static final Logger logger = Logger.getLogger(VisaTranxReaderStatement.class);
	@Override
	public String readLine(File file) {
		try {
			InputStream in = new FileInputStream(file);
			XSSFWorkbook wb = (XSSFWorkbook) WorkbookFactory.create(in);
			XSSFSheet sheet = wb.getSheetAt(0);
			
			int lenght = sheet.getLastRowNum();
			
			for(int i=16;i<lenght-5;i++){
			
				XSSFRow row = sheet.getRow(i);
				Date date 		  = new Date(row.getCell(1).getDateCellValue().getTime());
				
				String cardNumber = null;
				String trace      = null;
				String txnType    = null;
				// read description
				String bin		  =  row.getCell(3).getStringCellValue();
				String description = row.getCell(5).getStringCellValue();
				
			}
			return null;
		}
		catch(Exception ex){
			logger.debug("Exception happen while try to read statementFile", ex);
			return null;
		}
	}

	@Override
	public VisaState separateToEntities(String line) {
		
		return null;
	}

	@Override
	public void saveList(VisaState t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<VisaState> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
