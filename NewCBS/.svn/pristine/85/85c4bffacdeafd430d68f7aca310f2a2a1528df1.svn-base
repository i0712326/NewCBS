package bcel.cardcenter.cbs.unit.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import bcel.cardcenter.cbs.utility.Utility;
import bcel.cardcenter.cbs.utility.atm.CwdProcessUtilImp;
import bcel.cardcenter.cbs.aarofat.entity.ErrorTxn;

import java.util.List;
import java.util.ArrayList;
public class CwdProcessUtilImpTest {

	@Test
	public void test() {
		File ejFile = new File("D:\\Server\\WWW\\NewCBS\\testData\\all-2013.LOG");
		File statementFile = new File("D:\\Server\\WWW\\NewCBS\\testData\\GL007_GL_FC012003.xls");
		List<File> files = new ArrayList<File>();
		files.add(ejFile);
		files.add(statementFile);
		
		CwdProcessUtilImp cwdProcessUtil = new CwdProcessUtilImp();
		cwdProcessUtil.setAtmId("09400100");
		cwdProcessUtil.setBegin(Utility.str2Date("2012-12-23"));
		cwdProcessUtil.setEnd(Utility.str2Date("2012-12-29"));
		cwdProcessUtil.setFiles(files);
		cwdProcessUtil.cwdProcessUtil();
		List<ErrorTxn> ejReport = cwdProcessUtil.getAccountDebit();
		List<ErrorTxn> smReport = cwdProcessUtil.getNoGlDebit();
		for(ErrorTxn report : smReport){
			System.out.println(report.toString());
			
		}
		//assertEquals(0,ejReport.size());
		assertEquals(smReport.size(),168);
	}

}
