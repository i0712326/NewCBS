package bcel.cardcenter.cbs.varofat.service;

import java.util.List;
import bcel.cardcenter.cbs.varofat.entity.VisaHost;

public interface VisaStatementReconcile {
	public List<VisaHost> reconcile();
}
