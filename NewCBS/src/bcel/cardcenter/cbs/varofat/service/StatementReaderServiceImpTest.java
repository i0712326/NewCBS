package bcel.cardcenter.cbs.varofat.service;

import static org.junit.Assert.*;

import java.util.List;

import jxl.common.Logger;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bcel.cardcenter.cbs.varofat.entity.VisaTxn;

public class StatementReaderServiceImpTest extends StatementReaderServiceImp {
	private static final Logger logger = Logger.getLogger(StatementReaderServiceImpTest.class);
	@Test
	public void testReadStatementFile() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		StatementReaderService statementReaderService = (StatementReaderService) context.getBean("statementReaderService");
		try {
			statementReaderService.readStatementFile();
			List<VisaTxn> list = statementReaderService.getVisaCards();
			assertTrue(list.size()>0);
		} catch (Exception e) {
			logger.debug("Exception occur while testing", e);
		}
	}

}
