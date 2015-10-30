package bcel.cardcenter.cbs.unit.test;

import static org.junit.Assert.*;

import org.junit.Test;
import bcel.cardcenter.cbs.utility.Utility;
import bcel.cardcenter.cbs.utility.file.processor.FileProcessor;
import bcel.cardcenter.cbs.utility.file.processor.FileProcessorImp;

import java.io.File;
import java.sql.Date;
import java.util.List;

import bcel.cardcenter.cbs.aarofat.entity.Cwd;

public class FileProcessorTest {

	@Test
	public void testGetData() {
		
		//File file = new File("D:\\Server\\WWW\\NewCBS\\testData\\all-2013.LOG");
		File file = new File("D:\\Server\\WWW\\NewCBS\\testData\\GL007_GL_FC012003.xls");
		String atmId = "09400100";
		Date begin = Utility.str2Date("2012-12-23");
		Date end   = Utility.str2Date("2012-12-29");
		FileProcessor<List<Cwd>> fileProcessor = new FileProcessorImp();
		fileProcessor.setFile(file);
		fileProcessor.setAtmId(atmId);
		fileProcessor.setStart(begin);
		fileProcessor.setEnd(end);
		
		fileProcessor.process();
		
		assertEquals(0,fileProcessor.getData().size());
		//fail("Not yet implemented");
	}

}
