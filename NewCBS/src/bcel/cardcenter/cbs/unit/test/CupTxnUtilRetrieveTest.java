package bcel.cardcenter.cbs.unit.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bcel.cardcenter.cbs.carofat.entity.CupTxnSettle;
import bcel.cardcenter.cbs.carofat.entity.CupTxnState;
import bcel.cardcenter.cbs.carofat.utility.CupTxnUtil;

public class CupTxnUtilRetrieveTest {

	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CupTxnUtil cupTxnUtil = (CupTxnUtil) context.getBean("cupTxnUtil");
		String option = "state";
		try {
			/*
			List<CupTxnState> cupTxns = cupTxnUtil.getTmpStates(option);
			assertTrue(cupTxns.size()>0);
			*/
			java.util.Date date = new java.util.Date();
			java.sql.Date sdate = new java.sql.Date(date.getTime());
			List<CupTxnState> cupTxns = cupTxnUtil.getTmpStates(option, sdate, 0, 10);
			assertTrue(cupTxns.size()==10);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
