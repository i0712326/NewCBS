package bcel.cardcenter.cbs.carofat.utility;

import java.io.File;
import java.sql.Date;
import java.util.List;

import jxl.common.Logger;

import bcel.cardcenter.cbs.carofat.dao.CupTxnSettleDao;
import bcel.cardcenter.cbs.carofat.entity.CupTxnSettle;
import bcel.cardcenter.cbs.carofat.entity.CupTxnState;

public class CupTxnSettlementState implements CupTxnStateManager {
	private static final Logger logger = Logger.getLogger(CupTxnSettlementState.class);
	private CupTxnSettleDao cupTxnSettleDao;
	private CupTxnSettlementReader cupTxnSettlementReader;
	private FTPFileAccessor cupFtpFileAccessor;
	public void setCupTxnSettleDao(CupTxnSettleDao cupTxnSettleDao){
		this.cupTxnSettleDao = cupTxnSettleDao;
	}
	public void setCupTxnSettlementReader(CupTxnSettlementReader cupTxnSettlementReader){
		this.cupTxnSettlementReader = cupTxnSettlementReader;
	}
	public void setCupFtpFileAccessor(FTPFileAccessor cupFtpFileAccessor){
		this.cupFtpFileAccessor = cupFtpFileAccessor;
	}
	@Override
	public String process(int record, Date date) throws Exception{
		try{
			List<String> fNames = cupFtpFileAccessor.access();
			for(int i=0;i<fNames.size();i++){
				String name = fNames.get(i);
				File file = new File(name);
				cupTxnSettlementReader.setFile(file);
				cupTxnSettlementReader.setImportDate(date);
				List<CupTxnSettle> settles = cupTxnSettlementReader.readSettle();
				if(record!=settles.size())
					throw new Exception("invalid transaction number");
				cupTxnSettleDao.saveOrUpdateAll(settles);
				cupFtpFileAccessor.deleteSourceFiles(i);
			}
			return "0000";
		}
		catch(Exception e){
			logger.debug("Exception occur while try to process settlement", e);
			throw new Exception(e);
		}
		
	}
	@Override
	public boolean confirmUpload() throws Exception {
		return cupTxnSettleDao.confirmCupTxnSettleBatch();
	}
	@Override
	public List<CupTxnState> getTxnStates(Date date, int first, int max) {
		return null;
	}
	@Override
	public List<CupTxnSettle> getTxnSettles(Date date, int first, int max) {
		return cupTxnSettleDao.getTxnSettles(date, first, max);
	}
	@Override
	public int getRows() {
		return cupTxnSettleDao.getRows();
	}
	@Override
	public boolean deleteAll(Date date) {
		return cupTxnSettleDao.deleteAll(date);
	}
	@Override
	public List<CupTxnState> getTmpStates() {
		return null;
	}
	@Override
	public List<CupTxnSettle> getTmpSettles() {
		return cupTxnSettleDao.getTmpSettles();
	}
	@Override
	public List<CupTxnState> getTmpStates(int page, int rows) {
		return null;
	}
	@Override
	public List<CupTxnSettle> getTmpSettles(int page, int rows) {
		return cupTxnSettleDao.getTmpSettles(page,rows);
	}
	
}
