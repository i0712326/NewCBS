package bcel.cardcenter.cbs.carofat.utility;

import java.sql.Date;
import java.util.List;

import bcel.cardcenter.cbs.carofat.dao.CupStateDao;
import bcel.cardcenter.cbs.carofat.entity.CupSettle;
import bcel.cardcenter.cbs.carofat.entity.CupState;

public class CupStatementState implements CupStateManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CupStateDao cupStateDao;
	private Xml2ObjectService xml2ObjectService;
	public void setXml2ObjectService(Xml2ObjectService xml2ObjectService){
		this.xml2ObjectService = xml2ObjectService;
	}
	public void setCupStateDao(CupStateDao cupStateDao){
		this.cupStateDao = cupStateDao;
	}
	@Override
	public List<CupState> getStates(Date date, int page, int max) {
		return cupStateDao.getStates(date, page, max);
	}

	@Override
	public List<CupSettle> getSettles(Date date, int page, int max) {
		return null;
	}

	@Override
	public int getRows(Date date) {
		return cupStateDao.getSize(date);
	}
	@Override
	public List<CupState> getStates(int page, int max) {
		return cupStateDao.getStates(page, max);
	}
	@Override
	public List<CupSettle> getSettles(int page, int max) {
		return null;
	}
	@Override
	public int getRows() {
		return cupStateDao.getSize();
	}
	@Override
	public List<CupState> getStates(Date date, String card, String trace,
			int page, int max) {
		return cupStateDao.getStates(date, card, trace, page, max);
	}
	@Override
	public List<CupSettle> getSettles(Date date, String card, String trace,
			int page, int max) {
		return null;
	}
	@Override
	public int getRows(Date date, String card, String trace) {
		return cupStateDao.getSize(date, card, trace);
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean update(String xmlData) throws Exception{
		List<CupState> states = (List<CupState>) xml2ObjectService.xml2ObjList(xmlData);
		for(CupState s : states){
			Date date    = s.getDate();
			String card  = s.getCard();
			String trace = s.getTrace();
			String refer = s.getRefer();
			CupState state = cupStateDao.getState(date,card,trace,refer);
			state.setDate(s.getDate());
			state.setCard(s.getCard());
			state.setTrace(s.getTrace());
			state.setTime(s.getTime());
			state.setAmount(s.getAmount());
			state.setFee(s.getFee());
			state.setNet(s.getNet());
			state.setTerminalId(s.getTerminalId());
			state.setRefer(s.getRefer());
			state.setType(s.getType());
			state.setStatus(s.getStatus());
			cupStateDao.update(state);
		}
		return false;
	}
	@Override
	public List<CupSettle> getSettles() {
		return null;
	}
	@Override
	public List<CupState> getStates() {
		return cupStateDao.getStates();
	}
	@Override
	public List<CupSettle> getSettles(Date sdate) {
		return null;
	}
	@Override
	public List<CupState> getStates(Date sdate) {
		return cupStateDao.getStates(sdate);
	}
	
}
