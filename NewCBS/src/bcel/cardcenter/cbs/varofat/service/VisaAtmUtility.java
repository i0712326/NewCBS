package bcel.cardcenter.cbs.varofat.service;

import java.sql.Date;
import java.util.List;

import bcel.cardcenter.cbs.varofat.entity.VisaSettle;

public interface VisaAtmUtility {
	public List<VisaSettle> getVisaAtm(Date date, String type, String ref, String card,int page,int rowNum);
	public int recordCount(Date date, String type, String ref, String card);
	public int updateVisaAtm(String xmlData);
}
