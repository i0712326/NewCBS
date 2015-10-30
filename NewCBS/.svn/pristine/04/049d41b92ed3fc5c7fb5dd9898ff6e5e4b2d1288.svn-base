package bcel.cardcenter.cbs.carofat.dao;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ConfirmCupTxnStateCallback implements HibernateCallback {

	@Override
	public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
		ConfirmCupTxnStateWork work = new ConfirmCupTxnStateWork();
		session.doWork(work);
		return new Boolean(work.getRet());
	}

}
