package bcel.cardcenter.cbs.varofat.service;

import java.util.List;

import bcel.cardcenter.cbs.varofat.entity.VisaHost;
import bcel.cardcenter.cbs.varofat.entity.VisaState;

public class VisaStatementReconcileImp implements VisaStatementReconcile {
	private List<VisaHost>  visaHosts;
	private List<VisaState> visaState;
	private List<VisaHost>  visaHostRets;
	
	// Business Logic inject 
	
	@Override
	public List<VisaHost> reconcile() {
		
		return null;
	}

}
