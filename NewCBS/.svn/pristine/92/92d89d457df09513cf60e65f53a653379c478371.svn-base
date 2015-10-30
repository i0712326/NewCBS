package bcel.cardcenter.cbs.utility.atm;

import java.io.StringReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import bcel.cardcenter.cbs.aarofat.dao.ErrorTxnDao;
import bcel.cardcenter.cbs.aarofat.entity.ErrorTxn;
import bcel.cardcenter.cbs.utility.Utility;

public class ErrorTxnUtilityImp implements ErrorTxnUtility {
	private ErrorTxnDao errorTxnDao;
	private List<ErrorTxn> errorTxns;
	public void setErrorTxnDao(ErrorTxnDao errorTxnDao){
		this.errorTxnDao=errorTxnDao;
	}
	@Override
	public int save(ErrorTxn errorTxn) {
		try{
			errorTxnDao.save(errorTxn);
			return 1;
		}
		catch(Exception ex){
			return 0;
		}
	}

	@Override
	public List<ErrorTxn> getErrorTxn(Date begin, Date end) {
		return errorTxnDao.getErrorTxns(begin, end);
	}

	@Override
	public void edit(ErrorTxn errorTxn) {
		errorTxnDao.update(errorTxn);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ErrorTxn> parseXml(String dataXml) throws DocumentException{
		StringReader reader = new StringReader( dataXml );
		InputSource inputSource = new InputSource( reader );
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(inputSource);
		errorTxns = new ArrayList<ErrorTxn>();
		List<Element> elements = document.selectNodes("//records/record");
		for(Iterator<Element> iter = elements.iterator();iter.hasNext();){
			Element ele = iter.next();
			String dat   = ele.element("date").getTextTrim();
			String card   = ele.element("card").getTextTrim();
			String tsq    = ele.element("tsq").getTextTrim();
			String amt    = ele.element("amount").getTextTrim().replaceAll(",", "");
			String status = ele.element("status").getTextTrim();
			String account= ele.element("account").getTextTrim();
			String notice = ele.element("notice").getTextTrim();
			String atmId  = ele.element("atmId").getTextTrim();
			String type   = ele.element("type").getTextTrim();
			
			Date date 	  = Utility.str2Date06(dat);
			double amount = Double.parseDouble(amt);
			
			ErrorTxn errorTxn = new ErrorTxn();
			errorTxn.setDate(date);
			errorTxn.setCardNumber(card);
			errorTxn.setTsq(tsq);
			errorTxn.setAmount(amount);
			errorTxn.setStatus(status);
			errorTxn.setAccountNumber(account);
			errorTxn.setNotice(notice);
			errorTxn.setAtmId(atmId);
			errorTxn.setType(type);
			errorTxns.add(errorTxn);
		}
		
		return errorTxns;
	}

}
