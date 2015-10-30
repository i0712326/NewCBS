package bcel.cardcenter.cbs.varofat.service;

import java.util.List;

import bcel.cardcenter.cbs.varofat.entity.VisaTxn;

public interface StatementReaderService {
	public void readStatementFile() throws Exception;
	public List<VisaTxn> getVisaCards();
}
