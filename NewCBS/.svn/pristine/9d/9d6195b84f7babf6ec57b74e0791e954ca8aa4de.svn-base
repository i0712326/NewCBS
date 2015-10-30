package bcel.cardcenter.cbs.varofat.dao.hibernate.callback;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class UniqueVisaAtmHibernateCallback implements HibernateCallback {
	private String card;
	private String retr;
	private String trace;
	private String type;
	
	public UniqueVisaAtmHibernateCallback(String card, String retr,
			String trace, String type) {
		super();
		this.card = card;
		this.retr = retr;
		this.trace = trace;
		this.type = type;
	}

	@Override
	public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
		String hql = "from VisaAtm v where v.card = :card and v.retrieval = :retr and v.trace = :trace and v.type = :type";
		Query query = session.createQuery(hql);
		query.setString("card", card);
		query.setString("retr", retr);
		query.setString("trace", trace);
		query.setString("type", type);
		return query.uniqueResult();
	}

}
