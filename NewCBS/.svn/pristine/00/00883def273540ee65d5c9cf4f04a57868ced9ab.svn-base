package bcel.cardcenter.cbs.carofat.dao;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ConfirmCupTxnSettleCallback implements HibernateCallback {

	@Override
	public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
		ConfirmCupTxnSettleWork work = new ConfirmCupTxnSettleWork();
		session.doWork(work);
		return work.getRet();
	}

}
