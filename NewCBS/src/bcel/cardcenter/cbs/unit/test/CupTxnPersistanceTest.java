package bcel.cardcenter.cbs.unit.test;

import static org.junit.Assert.fail;
import jxl.common.Logger;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bcel.cardcenter.cbs.carofat.utility.CupTxnUtil;

public class CupTxnPersistanceTest {
	private static final Logger logger = Logger.getLogger(CupTxnPersistanceTest.class);
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CupTxnUtil cupTxnUtil = (CupTxnUtil) context.getBean("cupTxnUtil");
		try {
			java.util.Date iDate = new java.util.Date();
			long time = iDate.getTime();
			java.sql.Date date = new java.sql.Date(time);
			cupTxnUtil.upload(15,"state", date);
		} catch (Exception e) {
			logger.debug("Exception occur while try to access file",e);
			fail("failed to access file");
		}
	}

}
