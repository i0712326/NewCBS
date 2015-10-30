package bcel.cardcenter.cbs.aarofat.dao;

//import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import bcel.cardcenter.cbs.aarofat.entity.ErrorTxn;

public class ErrorTxnDaoImpTest {

	@Test
	public void testSave() {
		ApplicationContext  context =  new FileSystemXmlApplicationContext("D:\\Server\\WWW\\NewCBS\\WebContent\\WEB-INF\\applicationContext.xml");
		
		ErrorTxnDao errorTxnDaoImp = (ErrorTxnDaoImp) context.getBean("errorTxnDaoImp");
		ErrorTxn errorTxn = new ErrorTxn();
		errorTxn.setAccountNumber("01012000034512001");
		errorTxn.setAtmId("01000100");
		errorTxn.setAmount(100000.0);
		errorTxn.setCardNumber("6213545022015588");
		errorTxn.setNotice("NORMAL");
		errorTxn.setStatus("PAID");
		errorTxn.setTsq("256431");
		errorTxn.setType("0000");
		errorTxn.setDate(new java.sql.Date(new java.util.Date().getTime()));
		errorTxnDaoImp.save(errorTxn);
	}

}
