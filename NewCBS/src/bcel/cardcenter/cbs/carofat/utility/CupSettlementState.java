package bcel.cardcenter.cbs.carofat.utility;

import java.sql.Date;
import java.util.List;

import bcel.cardcenter.cbs.carofat.dao.CupSettleDao;
import bcel.cardcenter.cbs.carofat.entity.CupSettle;
import bcel.cardcenter.cbs.carofat.entity.CupState;

public class CupSettlementState implements CupStateManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CupSettleDao cupSettleDao;
	private Xml2ObjectService xml2ObjectService;
	public void setCupSettleDao(CupSettleDao cupSettleDao){
		this.cupSettleDao = cupSettleDao;
	}
	public void setXml2ObjectService(Xml2ObjectService xml2ObjectService){
		this.xml2ObjectService = xml2ObjectService;
	}
	@Override
	public List<CupState> getStates(Date date, int page, int max) {
		return null;
	}
	@Override
	public List<CupSettle> getSettles(Date date, int page, int max) {
		return cupSettleDao.getSettles(date, page, max);
	}
	@Override
	public int getRows(Date date) {
		return cupSettleDao.getSize(date);
	}
	@Override
	public List<CupState> getStates(int page, int max) {
		return null;
	}
	@Override
	public List<CupSettle> getSettles(int page, int max) {
		return cupSettleDao.getReconSettles(page, max);
	}
	@Override
	public int getRows() {
		return cupSettleDao.getSize();
	}
	@Override
	public List<CupState> getStates(Date date, String card, String trace,
			int page, int max) {
		return null;
	}
	@Override
	public List<CupSettle> getSettles(Date date, String card, String trace,
			int page, int max) {
		return cupSettleDao.getSettles(date, card, trace, page, max);
	}
	@Override
	public int getRows(Date date, String card, String trace) {
		return cupSettleDao.getSize(date, card, trace);
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean update(String xmlData) throws Exception {
		List<CupSettle> settles = (List<CupSettle>) xml2ObjectService.xml2ObjList(xmlData);
		for(CupSettle s : settles){
			Date date    = s.getDate();
			String card  = s.getCard();
			String trace = s.getTrace();
			String refer = s.getRefer();
			CupSettle settle = cupSettleDao.getSettle(date, card, trace,refer);
			settle.setDate(s.getDate());
			settle.setCard(s.getCard());
			settle.setTrace(s.getTrace());
			settle.setAmount(s.getAmount());
			settle.setFee(s.getFee());
			settle.setNet(s.getNet());
			settle.setTerminalId(s.getTerminalId());
			settle.setRefer(s.getRefer());
			settle.setType(s.getType());
			settle.setStatus(s.getStatus());
			cupSettleDao.updateSettles(settle);
		}
		return true;
	}
	@Override
	public List<CupSettle> getSettles() {
		return cupSettleDao.getSettles();
	}
	@Override
	public List<CupState> getStates() {
		return null;
	}
	@Override
	public List<CupSettle> getSettles(Date sdate) {
		return cupSettleDao.getSettles(sdate);
	}
	@Override
	public List<CupState> getStates(Date sdate) {
		return null;
	}
}
