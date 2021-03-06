package bcel.cardcenter.cbs.varofat.service;

import java.io.StringReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import bcel.cardcenter.cbs.varofat.dao.VisaAtmDao;
import bcel.cardcenter.cbs.varofat.entity.VisaSettle;

public class VisaAtmUtilityImp implements VisaAtmUtility {
	private final static Logger logger = Logger.getLogger(VisaAtmUtilityImp.class);
	private VisaAtmDao visaAtmDao;
	int len;
	@Override
	public List<VisaSettle> getVisaAtm(Date date, String type,
			String ref, String card, int page, int rowNum) {
		return visaAtmDao.getVisaAtm(date, type, ref, card, page, rowNum);
	}
	
	public void setVisaAtmDao(VisaAtmDao visaAtmDao) {
		this.visaAtmDao = visaAtmDao;
	}

	@Override
	public int recordCount(Date date, String type, String ref, String card) {
		return visaAtmDao.recordCount(date, type, ref, card);
	}
	
	@Override
	public int updateVisaAtm(String xmlData){
		try {
			List<VisaSettle> visas = xmlToVisaAtm(xmlData);
			List<VisaSettle> vs = getUpdateList(visas,len);
			visaAtmDao.bulkUpdate(vs);
			return len;
		} catch (Exception e) {
			return 0;
		}
	}
	@SuppressWarnings("unchecked")
	public List<VisaSettle> xmlToVisaAtm(String xmlData){
		try {
			StringReader reader = new StringReader( xmlData );
			InputSource inputSource = new InputSource( reader );
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(inputSource);
			List<Element> nodes = document.selectNodes("//visas/header");
			Iterator<Element> ite = nodes.iterator();
			while(ite.hasNext()){
				Element el = ite.next();
				String rec = el.element("records").getTextTrim();
				len = Integer.parseInt(rec.replaceAll(",", ""));
			}
			
			List<VisaSettle> visas = new ArrayList<VisaSettle>();
			List<Element> elements = document.selectNodes("//data/entity");
			for(Iterator<Element> iter = elements.iterator();iter.hasNext();){
				Element ele = iter.next();
				String batid    = ele.element("batid").getTextTrim();
				String txndate  = ele.element("txndate").getTextTrim();
				String txntime  = ele.element("txntime").getTextTrim();
				String card     = ele.element("card").getTextTrim();
				String trace    = ele.element("trace").getTextTrim();
				String retr     = ele.element("retr").getTextTrim();
				String issuerid = ele.element("issuer").getTextTrim();
				String type		= ele.element("type").getTextTrim();
				String atmid	= ele.element("atmid").getTextTrim();
				String status   = ele.element("status").getTextTrim();
				String casenum  = ele.element("casenum").getTextTrim();
				
				VisaSettle visa = new VisaSettle();
				
				visa.setBatId(batid);
				visa.setTxnDate(txndate);
				visa.setTxnTime(txntime);
				visa.setCard(card);
				visa.setTrace(trace);
				visa.setRetrieval(retr);
				visa.setIssuerId(issuerid);
				visa.setType(type);
				visa.setAtmId(atmid);
				visa.setStatus(status);
				visa.setCaseNumber(casenum);
				
				visas.add(visa);
			}
			if(len != visas.size()){
				logger.debug("Invalid XML data");
				return null;
			}
			return visas;
		}
		catch (Exception e) {
			logger.debug("invalid XML data",e);
			return null;	
		}
		
	}
	
	private List<VisaSettle> getUpdateList(List<VisaSettle> visas, int len) throws Exception {
		List<VisaSettle> list = new ArrayList<VisaSettle>();
		Iterator<VisaSettle> iter = visas.iterator();
		while(iter.hasNext()){
			VisaSettle visa = iter.next();
			VisaSettle v = visaAtmDao.getUniqueVisaAtm(visa);
			v.setStatus(visa.getStatus());
			v.setCaseNumber(visa.getCaseNumber());
			list.add(v);
		}
		if(len != list.size())
			return null;
		return list;
	}

}
