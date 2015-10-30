package bcel.cardcenter.cbs.varofat.dao;

import java.sql.Date;
import java.util.List;

import bcel.cardcenter.cbs.varofat.entity.VisaSettle;

public interface VisaAtmDao {
	public List<VisaSettle> getVisaAtm(Date date, String type, String ref, String card, int page, int rowNum);
	public void updateVisaAtm(VisaSettle visaAtm);
	public VisaSettle getUniqueVisaAtm(VisaSettle visaAtm);
	public int recordCount(Date date, String group, String retr, String card);
	public void bulkUpdate(List<VisaSettle> updateList);
}
