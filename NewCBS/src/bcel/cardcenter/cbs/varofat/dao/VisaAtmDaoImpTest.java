package bcel.cardcenter.cbs.varofat.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bcel.cardcenter.cbs.varofat.entity.VisaSettle;

public class VisaAtmDaoImpTest {

	@Test
	public void testGetUniqueVisaAtm() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		VisaAtmDao impl = (VisaAtmDao) context.getBean("visaAtmDao");
		VisaSettle visa = new VisaSettle();
		visa.setCard("4145800001894402");
		visa.setRetrieval("217100371400");
		visa.setTrace("341304");
		visa.setType("0200");
		VisaSettle ret = impl.getUniqueVisaAtm(visa);
		assertTrue(ret!=null);
	}
	public void testgetVisaAtm(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		VisaAtmDao impl = (VisaAtmDao) context.getBean("visaAtmDao");
		
	}
}
