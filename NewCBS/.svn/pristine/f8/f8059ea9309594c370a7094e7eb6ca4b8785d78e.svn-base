package bcel.cardcenter.cbs.unit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.dom4j.DocumentException;
import org.junit.Test;

import bcel.cardcenter.cbs.aarofat.dao.ErrorDao;
import bcel.cardcenter.cbs.aarofat.dao.ErrorDaoImp;
import bcel.cardcenter.cbs.aarofat.entity.Error;

public class ErrorDaoImpTest {

	@Test
	public void testInitError() throws DocumentException {
		ErrorDao errorDao = new ErrorDaoImp();
		List<Error> errorList = errorDao.initErrorList();
		assertTrue(errorList.size()>0);
	}

}
