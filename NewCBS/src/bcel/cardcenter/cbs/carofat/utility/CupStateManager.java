package bcel.cardcenter.cbs.carofat.utility;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import bcel.cardcenter.cbs.carofat.entity.CupSettle;
import bcel.cardcenter.cbs.carofat.entity.CupState;

public interface CupStateManager extends Serializable {
	public List<CupState> getStates(Date date, int page, int max);
	public List<CupSettle> getSettles(Date date, int page, int max);
	public List<CupState> getStates(Date date, String card, String trace, int page, int max);
	public List<CupSettle> getSettles(Date date, String card, String trace, int page, int max);
	public int getRows(Date date, String card, String trace);
	public int getRows(Date date);
	public int getRows();
	public List<CupState> getStates(int page, int max);
	public List<CupSettle> getSettles(int page, int max);
	public boolean update(String xmlData) throws Exception;
	public List<CupSettle> getSettles();
	public List<CupState> getStates();
	public List<CupSettle> getSettles(Date sdate);
	public List<CupState> getStates(Date sdate);
	
}
