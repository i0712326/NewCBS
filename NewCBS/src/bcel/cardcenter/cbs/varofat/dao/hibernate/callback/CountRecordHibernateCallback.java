package bcel.cardcenter.cbs.varofat.dao.hibernate.callback;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.sql.Date;
public class CountRecordHibernateCallback implements HibernateCallback {
	private Date date;
	private String group;
	private String retr;
	private String card;
	
	public CountRecordHibernateCallback(Date date,String group, String retr, String card){
		this.date = date;
		this.group = group;
		this.retr = retr;
		this.card = card;
	}
	@Override
	public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
		String hql = "select count(*) from VisaAtm v where v.settleDate = :date and v.group = :group and v.retrieval like :retr and v.card like :card";
		Query query = session.createQuery(hql);
		query.setDate("date", date);
		query.setString("group", group);
		query.setString("retr", retr);
		query.setString("card",card);
		return ((Long) query.uniqueResult()).intValue();
	}

}
