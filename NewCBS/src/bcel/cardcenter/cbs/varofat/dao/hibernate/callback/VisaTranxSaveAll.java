package bcel.cardcenter.cbs.varofat.dao.hibernate.callback;

import java.sql.SQLException;
import java.util.List;

import jxl.common.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;

import bcel.cardcenter.cbs.varofat.entity.VisaTranx;

public class VisaTranxSaveAll implements HibernateCallback{
	final static Logger logger = Logger.getLogger(VisaTranxSaveAll.class);
	private List<VisaTranx> visaTranxs;
	
	public VisaTranxSaveAll(List<VisaTranx> visaTranxs){
		this.visaTranxs = visaTranxs;
	}
	@Override
	public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
		
			Transaction tx = session.beginTransaction();
			String hql = "insert into VisaTranx (date, terminalId,tranxCode, cardNumber, amount, currcode, traceNo, refNo, responseCode, approvalCode, tranxDate, tranxTime) values "
					+ "(:date, :terminalId,:tranxCode, :cardNumber, :amount, :currcode, :traceNo, :refNo, :responseCode, :approvalCode, :tranxDate, :tranxTime)";
			Query query = session.createQuery(hql);
		try {
			for (VisaTranx visaTranx : visaTranxs) {
				query.setDate("date", visaTranx.getDate());
				query.setString("terminalId", visaTranx.getTerminalId());
				query.setString("tranxCode", visaTranx.getTranxCode());
				query.setString("cardNumber", visaTranx.getCardNumber());
				query.setDouble("amount", visaTranx.getAmount());
				query.setString("currCode", visaTranx.getCurrCode());
				query.setString("traceNo", visaTranx.getTraceNo());
				query.setString("refNo", visaTranx.getRefNo());
				query.setString("responseCode", visaTranx.getResponseCode());
				query.setString("approvalCode", visaTranx.getApprovalCode());
				query.setString("tranxDate", visaTranx.getTranxDate());
				query.setString("tranxTime", visaTranx.getTranxTime());
				query.executeUpdate();
			}
			tx.commit();
			return new Boolean(true);
		} catch (Exception ex) {
			logger.debug(
					"Exception occur while try to persist transaction from visa log",
					ex);
			tx.rollback();
			return new Boolean(false);
		}
	}

}
