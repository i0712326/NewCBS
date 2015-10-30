package bcel.cardcenter.cbs.varofat.dao.hibernate.callback;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import java.sql.Date;
public class VisaAtmHibernateCallback implements HibernateCallback {
	private Date date;
	private String option;
	private String retr;
	private String card;
	private int page;
	private int rowNum;
	
	public VisaAtmHibernateCallback(Date date, String option, String retr,
			String card, int page, int rowNum) {
		super();
		this.date = date;
		this.option = option;
		this.retr = retr;
		this.card = card;
		this.page = page;
		this.rowNum = rowNum;
	}

	@Override
	public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
		String hql = "from VisaSettle v where v.settleDate = :date and v.group = :group and v.retrieval like :ref and v.card like :card";
		Query query = session.createQuery(hql);
		query.setDate("date", date);
		query.setString("group", option);
		query.setString("ref",retr);
		query.setString("card", card);
		query.setFirstResult(page);
		query.setMaxResults(rowNum);
		return query.list();
	}

}
