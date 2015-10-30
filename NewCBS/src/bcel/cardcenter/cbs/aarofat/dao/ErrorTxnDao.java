package bcel.cardcenter.cbs.aarofat.dao;

import java.sql.Date;
import java.util.List;

import bcel.cardcenter.cbs.aarofat.entity.ErrorTxn;

public interface ErrorTxnDao {
	public String errorQuery = "GETERRORTXN";
	public void save(ErrorTxn errorTxn);
	public List<ErrorTxn> getErrorTxns(Date begin, Date end);
	public void update(ErrorTxn errorTxn);
}
