package bcel.cardcenter.cbs.utility.atm;

import java.util.List;

import bcel.cardcenter.cbs.aarofat.dao.AtmDao;
import bcel.cardcenter.cbs.aarofat.entity.Atm;

public class AtmUtilImp implements AtmUtil {
	private AtmDao atmDao;
	public void setAtmDao(AtmDao atmDao){
		this.atmDao=atmDao;
	}
	@Override
	public List<Atm> getAtmId(String atmId, String atmName,int page,int rp) {
		return atmDao.getAtmId(atmId, atmName,page,rp);
	}
	@Override
	public int getAtmTotal(String atmId, String atmName) {
		return atmDao.getAtmTotal(atmId, atmName);
	}
}
