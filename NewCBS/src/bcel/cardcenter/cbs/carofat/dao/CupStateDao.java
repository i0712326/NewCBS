package bcel.cardcenter.cbs.carofat.dao;

import java.util.Date;
import java.util.List;

import bcel.cardcenter.cbs.carofat.entity.CupState;

public interface CupStateDao {
	public List<CupState> getStates(Date date, int page, int max);
	public List<CupState> getStates(int page, int max);
	public List<CupState> getStates(Date date);
	public List<CupState> getStates(Date date, String card, String trace, int page, int max);
	public int getSize(Date date, String card, String trace);
	public CupState getState(Date date, String card, String trace, String refer);
	public int getSize(Date date);
	public int getSize();
	public void update(CupState state);
	public List<CupState> getStates();
}
