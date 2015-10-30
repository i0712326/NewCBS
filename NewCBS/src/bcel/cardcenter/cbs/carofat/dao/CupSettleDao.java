package bcel.cardcenter.cbs.carofat.dao;

import java.sql.Date;
import java.util.List;

import bcel.cardcenter.cbs.carofat.entity.CupSettle;

public interface CupSettleDao {
	public List<CupSettle> getSettles(Date date, int page, int max);
	public List<CupSettle> getReconSettles(int page, int max);
	public List<CupSettle> getSettles(Date date);
	public List<CupSettle> getSettles(Date date,String card, String trace,int page, int max);
	public int getSize(Date date, String card, String trace);
	public int getSize(Date date);
	public int getSize();
	public void updateCupSettles(List<CupSettle> settles);
	public CupSettle getSettle(Date date, String card, String trace,String refer);
	public void updateSettles(CupSettle settle);
	public List<CupSettle> getSettles();
}
