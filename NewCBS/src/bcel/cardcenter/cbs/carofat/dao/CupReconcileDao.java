package bcel.cardcenter.cbs.carofat.dao;

import java.sql.Date;

public interface CupReconcileDao {
	public void reconcile(Date date) throws Exception;
	public void confirmReconcile(Date date) throws Exception;
	public void cancelReconcile() throws Exception;
}
