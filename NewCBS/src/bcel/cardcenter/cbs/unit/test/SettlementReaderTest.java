package bcel.cardcenter.cbs.unit.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.common.Logger;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bcel.cardcenter.cbs.marofat.entity.MasterCardSettle;
import bcel.cardcenter.cbs.marofat.service.SettlementReader;

public class SettlementReaderTest {
	private static final String FilePath = "C:\\Users\\phoud\\Desktop\\logfile\\TT464T0.2013-08-28-13-38-52.001";
	private static final Logger logger = Logger.getLogger(SettlementReaderTest.class);
	@Test
	public void testReadSettlment() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SettlementReader settlementReader = (SettlementReader) context.getBean("settlementReader");
		File file = new File(FilePath);
		try {
			List<MasterCardSettle> settles = settlementReader.readSettlment(file);
			assertTrue(settles.size()>0);
		} catch (IOException e) {
			logger.debug("Exception occur",e);
		}
	}

}
