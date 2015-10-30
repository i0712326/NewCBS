package bcel.cardcenter.cbs.utility.atm;

import java.sql.Date;
import java.util.List;

import org.dom4j.DocumentException;

import bcel.cardcenter.cbs.aarofat.entity.ErrorTxn;

public interface ErrorTxnUtility {
	public int save(ErrorTxn errorTxn);
	public List<ErrorTxn> getErrorTxn(Date begin, Date end);
	public void edit(ErrorTxn errorTxn);
	public List<ErrorTxn> parseXml(String dataXml) throws DocumentException;
}
