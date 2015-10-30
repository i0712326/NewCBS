package bcel.cardcenter.cbs.carofat.utility;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import bcel.cardcenter.cbs.carofat.entity.CupState;
import bcel.cardcenter.cbs.utility.Utility;

public class Xml2ObjectStateServiceImp implements Xml2ObjectService {
	private static final Logger logger = Logger.getLogger(Xml2ObjectStateServiceImp.class);
	public List<CupState> xml2ObjListState(String xmlData) throws Exception{
		DocumentBuilder db;
		List<CupState> states = new ArrayList<CupState>();
		try {
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlData));

			Document doc = db.parse(is);
			NodeList nods = doc.getElementsByTagName("header");
			Element el = (Element) nods.item(0);
			NodeList recordsNode = el.getElementsByTagName("records");
			Element e = (Element) recordsNode.item(0);
			String rec = getCharacterDataFromElement(e);
			int records = Integer.parseInt(rec.replaceAll(",", ""));
			
			NodeList nodes = doc.getElementsByTagName("entity");
			for (int i = 0; i < nodes.getLength(); i++) {
			      Element element = (Element) nodes.item(i);

			      NodeList dateNode = element.getElementsByTagName("date");
			      Element line = (Element) dateNode.item(0);
			      String date = getCharacterDataFromElement(line);
			      
			      NodeList cardNode = element.getElementsByTagName("card");
			      line = (Element) cardNode.item(0);
			      String card =getCharacterDataFromElement(line);
			      
			      NodeList traceNode = element.getElementsByTagName("trace");
			      line = (Element) traceNode.item(0);
			      String trace = getCharacterDataFromElement(line);
			      
			      NodeList timeNode = element.getElementsByTagName("time");
			      line = (Element) timeNode.item(0);
			      String time = getCharacterDataFromElement(line);
			      
			      NodeList amountNode = element.getElementsByTagName("amount");
			      line = (Element) amountNode.item(0);
			      String amount = getCharacterDataFromElement(line);
			      
			      NodeList feeNode = element.getElementsByTagName("fee");
			      line = (Element) feeNode.item(0);
			      String fee = getCharacterDataFromElement(line);
			      
			      NodeList netNode = element.getElementsByTagName("net");
			      line = (Element) netNode.item(0);
			      String net = getCharacterDataFromElement(line);
			      
			      NodeList terminalNode = element.getElementsByTagName("terminal");
			      line = (Element) terminalNode.item(0);
			      String terminal=getCharacterDataFromElement(line);
			      
			      NodeList referNode = element.getElementsByTagName("refer");
			      line = (Element) referNode.item(0);
			      String refer = getCharacterDataFromElement(line);
			      
			      NodeList typeNode = element.getElementsByTagName("type");
			      line = (Element) typeNode.item(0);
			      String type = getCharacterDataFromElement(line);
			      
			      NodeList statusNode = element.getElementsByTagName("status");
			      line = (Element) statusNode.item(0);
			      String status = getCharacterDataFromElement(line);
			      
			      
			      CupState state = new CupState();
			      java.sql.Date sdate = Utility.str2Date01(date);
			      double amt = Double.parseDouble(amount.replaceAll(",", ""));
			      double feeAmt = Double.parseDouble(fee.replaceAll(",", ""));
			      double netAmt = Double.parseDouble(net.replaceAll(",", ""));
			      
			      state.setDate(sdate);
			      state.setCard(card);
			      state.setTrace(trace);
			      state.setTime(time);
			      state.setAmount(amt);
			      state.setFee(feeAmt);
			      state.setNet(netAmt);
			      state.setTerminalId(terminal);
			      state.setRefer(refer);
			      state.setType(type);
			      state.setStatus(status);
			      
			      states.add(state);
			    }
			if(records!=states.size())
				throw new Exception("Invalid XML Data");
			return states;
		} 
		catch (ParserConfigurationException e) {
			logger.debug("Exception occur for parser configuration",e);
			return states;
		} 
		catch (SAXException e) {
			logger.debug("Exception occur for Sax",e);
			return states;
		} 
		catch (IOException e) {
			logger.debug("Exception occur for IO",e);
			return states;
		}
	}
	public static String getCharacterDataFromElement(Element e) {
	    Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	      CharacterData cd = (CharacterData) child;
	      return cd.getData();
	    }
	    return "";
	  }
	@Override
	public Object xml2ObjList(String xmlData) throws Exception{
		return xml2ObjListState(xmlData);
	}

}
