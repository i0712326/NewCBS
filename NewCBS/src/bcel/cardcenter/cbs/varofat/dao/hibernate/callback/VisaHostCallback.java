package bcel.cardcenter.cbs.varofat.dao.hibernate.callback;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import bcel.cardcenter.cbs.varofat.entity.VisaHost;

public class VisaHostCallback implements HibernateCallback {
	private Date date;
	public VisaHostCallback(Date date){
		this.date = date;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
		String sql = "SELECT DATETIME, TERMINALID, TRANXCODE, CARDNUMBER, AMOUNT, CURRCODE, REFNO, RESPONSECODE, APPROVALCODE, TRANXDATE, TRANXTIME FROM TRANXLOG"+ 
					  "WHERE TO_CHAR(DATETIME,'YYYY-MM-DD') = :date AND card_type_id='34' AND TRANXCODE='CASH'";
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.addEntity(VisaHost.class);
		sqlQuery.setString("date", date.toString());
		List<VisaHost> list = (List<VisaHost>)sqlQuery.list();
		return list;
	}

}
