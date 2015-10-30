package bcel.cardcenter.cbs.carofat.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import jxl.common.Logger;

import org.hibernate.jdbc.Work;

public class ConfirmCupTxnSettleWork implements Work {
	private static final Logger logger = Logger.getLogger(ConfirmCupTxnSettleWork.class);
	private boolean ret;
	public void setRet(boolean ret){
		this.ret = ret;
	}
	public boolean getRet(){
		return ret;
	}
	@Override
	public void execute(Connection conn) throws SQLException {
		try{
			String confirmSql = "{ call NEWCBS.CONFIRMCUPTXNSETTLE }";
			CallableStatement call = conn.prepareCall(confirmSql);
			setRet(call.execute());
		}
		catch(Exception e){
			logger.debug("Exception occur while try to execute SQL", e);
			throw new SQLException(e);
		}
	}

}
