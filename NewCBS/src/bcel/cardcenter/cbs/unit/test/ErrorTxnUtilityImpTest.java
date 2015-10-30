package bcel.cardcenter.cbs.unit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.dom4j.DocumentException;
import org.junit.Test;

import bcel.cardcenter.cbs.aarofat.entity.ErrorTxn;
import bcel.cardcenter.cbs.utility.atm.ErrorTxnUtility;
import bcel.cardcenter.cbs.utility.atm.ErrorTxnUtilityImp;

public class ErrorTxnUtilityImpTest {
	
	@Test
	public void test() {
		String mockXml = "<?xml version='1.0' encoding='utf-8'?>";
		mockXml+="<records>";
		mockXml+="<record>";
		mockXml+="<date>Jan 01, 2013</date>"; // 1
		mockXml+="<card>AAAAAAAA</card>"; // 2
		mockXml+="<tsq>123456</tsq>"; // 3
		mockXml+="<amount>10000.00</amount>"; // 4
		mockXml+="<status>PAID</status>"; // 5
		mockXml+="<account>123456789123456</account>"; // 6
		mockXml+="<notice>NOMAL</notice>"; // 7
		mockXml+="<atmId>000100</atmId>"; // 8
		mockXml+="<type>0001</type>"; // 8
		mockXml+="</record>";
		mockXml+="<record>";
		mockXml+="<date>Jan 01, 2013</date>"; // 1
		mockXml+="<card>AAAAAAAA</card>"; // 2
		mockXml+="<tsq>123456</tsq>"; // 3
		mockXml+="<amount>10000.00</amount>"; // 4
		mockXml+="<status>PAID</status>"; // 5
		mockXml+="<account>123456789123456</account>"; // 6
		mockXml+="<notice>NOMAL</notice>"; // 7
		mockXml+="<atmId>000100</atmId>"; // 8
		mockXml+="<type>0001</type>"; // 8
		mockXml+="</record>";
		mockXml+="</records>";
		ErrorTxnUtility errorTxnUtility = new ErrorTxnUtilityImp();
		try {
			List<ErrorTxn> errorTxns = errorTxnUtility.parseXml(mockXml);
			assertTrue(errorTxns.size()==2);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
