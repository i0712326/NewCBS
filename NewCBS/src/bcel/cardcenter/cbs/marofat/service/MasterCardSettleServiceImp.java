package bcel.cardcenter.cbs.marofat.service;

import java.io.File;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcel.cardcenter.cbs.marofat.dao.MasterCardSettleDao;
import bcel.cardcenter.cbs.marofat.entity.MasterCardSettle;

@Service("masterCardSettleService")
public class MasterCardSettleServiceImp implements MasterCardSettleService {
	private MasterCardSettleDao masterCardSettleDao;
	/**
	 * Data access service 
	 * @param masterCardSettleDao
	 */
	@Autowired
	public void setMasterCardSettleDao(MasterCardSettleDao masterCardSettleDao){
		this.masterCardSettleDao = masterCardSettleDao;
	}
	@Override
	public void saveOrUpdateAll(List<MasterCardSettle> masterCardSettles)
			throws Exception {
		masterCardSettleDao.saveOrUpdateAll(masterCardSettles);
	}
	@Override
	public List<MasterCardSettle> getMasterCardSettles(Date date, String card,
			String trace, int page, int max) throws Exception {
		return masterCardSettleDao.getMasterCard(date, card, trace, page, max);
	}
	@Override
	public List<MasterCardSettle> getMasterCardSettles(Date date, int page,
			int max) throws Exception {
		return masterCardSettleDao.getMasterCard(date, page, max);
	}
	@Override
	public List<MasterCardSettle> getMasterCardSettles(Date date)
			throws Exception {
		return getMasterCardSettles(date);
	}
	
	// file processing service
	private SettlementReader settlementReader;
	@Autowired
	public void setSettlementReader(SettlementReader settlementReader){
		this.settlementReader = settlementReader;
	}
	@Override
	public List<MasterCardSettle> readSettlement(File file) throws Exception{
		return settlementReader.readSettlment(file);
	}
}
