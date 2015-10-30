package bcel.cardcenter.cbs.aarofat.reconcile.action;

import java.util.List;

import bcel.cardcenter.cbs.aarofat.entity.Atm;
import bcel.cardcenter.cbs.utility.atm.AtmUtil;

import com.opensymphony.xwork2.ActionSupport;

public class AtmGetIdAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// form input parameter
	
	private String atmId;
	private String atmName;
	private int page;
	private int total;
	private int rp;
	
	// business logic instance
	private AtmUtil atmUtil;
	
	// output data
	private List<Atm> atmList;
	
	public String getAtmId() {
		return atmId;
	}
	public void setAtmId(String atmId) {
		this.atmId = atmId;
	}
	public String getAtmName() {
		return atmName;
	}
	public void setAtmName(String atmName) {
		this.atmName = atmName;
	}
	public void setAtmUtil(AtmUtil atmUtil){
		this.atmUtil=atmUtil;
	}
	
	// production object
	
	public List<Atm> getAtmList() {
		return atmList;
	}
	public void setAtmList(List<Atm> atmList) {
		this.atmList = atmList;
	}
	
	// main control action
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getRp() {
		return rp;
	}
	public void setRp(int rp) {
		this.rp = rp;
	}
	public String execute() throws Exception{
		setAtmList(atmUtil.getAtmId(getAtmId(), getAtmName(),getPage(),getRp()));
		setTotal(atmUtil.getAtmTotal(getAtmId(),getAtmName()));
		return SUCCESS;
	}

}
