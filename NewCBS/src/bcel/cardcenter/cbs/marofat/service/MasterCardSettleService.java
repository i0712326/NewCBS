package bcel.cardcenter.cbs.marofat.service;

import java.sql.Date;
import java.util.List;
import java.io.File;
import bcel.cardcenter.cbs.marofat.entity.MasterCardSettle;

public interface MasterCardSettleService {
	public void saveOrUpdateAll(List<MasterCardSettle> masterCardSettles) throws Exception;
	public List<MasterCardSettle> getMasterCardSettles(Date date, String card, String trace, int page, int max) throws Exception;
	public List<MasterCardSettle> getMasterCardSettles(Date date, int page, int max) throws Exception;
	public List<MasterCardSettle> getMasterCardSettles(Date date) throws Exception;
	
	// read data from file
	
	public List<MasterCardSettle> readSettlement(File file) throws Exception;
	
}
