package bcel.cardcenter.cbs.unit.test;

import static org.junit.Assert.*;
import jxl.common.Logger;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bcel.cardcenter.cbs.carofat.utility.CupTxnUtil;

public class ConfirmPersistenceTest {
	private static final Logger logger = Logger.getLogger(ConfirmPersistenceTest.class);
	@Test
	public void test() {
		try{
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			CupTxnUtil cupTxnUtil = (CupTxnUtil) context.getBean("cupTxnUtil");
			String option = "state";
			int len = cupTxnUtil.getSize(option);
			assertEquals(len,304);
		}
		catch(Exception ex){
			logger.debug("Exception occur while process",ex);
			fail("Not yet implemented");
		}
	}

}
