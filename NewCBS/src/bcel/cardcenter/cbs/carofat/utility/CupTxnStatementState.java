package bcel.cardcenter.cbs.carofat.utility;

import java.io.File;
import java.sql.Date;
import java.util.List;

import bcel.cardcenter.cbs.carofat.dao.CupTxnStateDao;
import bcel.cardcenter.cbs.carofat.entity.CupTxnSettle;
import bcel.cardcenter.cbs.carofat.entity.CupTxnState;

import jxl.common.Logger;

public class CupTxnStatementState implements CupTxnStateManager {
	private static final Logger logger = Logger.getLogger(CupTxnStatementState.class);
	private CupTxnStatementReader cupTxnStatementReader;
	private CupTxnStateDao cupTxnStateDao;
	private FTPFileAccessor cupFtpFileAccessor;
	public void setCupTxnStatementReader(CupTxnStatementReader cupTxnStatementReader) {
		this.cupTxnStatementReader = cupTxnStatementReader;
	}
	public void setCupTxnStateDao(CupTxnStateDao cupTxnStateDao) {
		this.cupTxnStateDao = cupTxnStateDao;
	}
	public void setCupFtpFileAccessor(FTPFileAccessor cupFtpFileAccessor) {
		this.cupFtpFileAccessor = cupFtpFileAccessor;
	}
	@Override
	public String process(int record, Date date) throws Exception {
		try{
			List<String> fNames = cupFtpFileAccessor.access();
			for(int i=0;i<fNames.size();i++){
				String name = fNames.get(i);
				File file = new File(name);
				cupTxnStatementReader.setFile(file);
				cupTxnStatementReader.setImportDate(date);
				List<CupTxnState> states = cupTxnStatementReader.readState();
				if(record!=states.size())
					throw new Exception("Exception input transaction number of statement : "+states.size());
				cupTxnStateDao.saveOrUpdateAll(states);
				cupFtpFileAccessor.deleteSourceFiles(i);
			}
			return "0000";
		}
		catch(Exception e){
			logger.debug("Exception occur while try to process statement", e);
			throw new Exception(e);
		}
	}
	@Override
	public boolean confirmUpload() throws Exception {
		return cupTxnStateDao.confirmCupTxnStateBatch();
	}
	@Override
	public List<CupTxnState> getTxnStates(Date date, int first, int max) {
		return cupTxnStateDao.getTxnStates(date, first, max);
	}
	@Override
	public List<CupTxnSettle> getTxnSettles(Date date, int first, int max) {
		return null;
	}
	@Override
	public int getRows() {
		return cupTxnStateDao.getRows();
	}
	@Override
	public boolean deleteAll(Date date) {
		return cupTxnStateDao.deleteAll(date);
	}
	@Override
	public List<CupTxnState> getTmpStates() {
		return cupTxnStateDao.getTmpStates();
	}
	@Override
	public List<CupTxnSettle> getTmpSettles() {
		return null;
	}
	@Override
	public List<CupTxnState> getTmpStates(int page, int rows) {
		return cupTxnStateDao.getTmpStates(page,rows);
	}
	@Override
	public List<CupTxnSettle> getTmpSettles(int page, int rows) {
		return null;
	}

}
