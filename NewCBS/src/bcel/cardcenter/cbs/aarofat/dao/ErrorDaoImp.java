package bcel.cardcenter.cbs.aarofat.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import bcel.cardcenter.cbs.aarofat.entity.Error;

public class ErrorDaoImp implements ErrorDao {
	private List<Error> errorList;
	
	@Override
	public List<Error> initErrorList() throws DocumentException{
		File file = new File(ErrorList);
		SAXReader saxReader = new SAXReader();
		errorList = new ArrayList<Error>();

		Document document = saxReader.read(file);
		
		// retrieve each row
		List<Element> elements = document.selectNodes("//RESULTS/ROW");
		Iterator<Element> iter = elements.iterator();
		while (iter.hasNext()) {
			Element element = iter.next();
			
			// retrieve each column
			List<Element> eles = element.elements();
			Iterator<Element> ite = eles.iterator();
			Error error = new Error();
			while (ite.hasNext()) {
				Element ele = ite.next();
				// checking attribute
				
				List<Attribute> attributes = ele.attributes();
				Iterator<Attribute> it = attributes.iterator();
				while (it.hasNext()) {
					Attribute attribute = it.next();
					
					// initialize error code and description
					String attributeName = attribute.getStringValue();
					if (attributeName.equals("CODE"))
						error.setCode(ele.getText());
					if (attributeName.equals("DESCRIPTION"))
						error.setDescription(ele.getText());
				}
				
			}
			errorList.add(error);
		}
		return errorList;
	}

}
