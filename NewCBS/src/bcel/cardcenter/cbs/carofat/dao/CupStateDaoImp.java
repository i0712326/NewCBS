package bcel.cardcenter.cbs.carofat.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Arrays;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bcel.cardcenter.cbs.carofat.entity.CupState;

public class CupStateDaoImp implements CupStateDao {
	private HibernateTemplate hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Override
	public List<CupState> getStates(Date date, int page, int max) {
		DetachedCriteria criteria = DetachedCriteria.forClass(CupState.class);
		criteria.add(Restrictions.eq("importDate", date));
		return toList(hibernateTemplate.findByCriteria(criteria, page, max));
	}

	@Override
	public List<CupState> getStates(Date date) {
		String hql = "from CupState s where s.importDate = :date";
		String param = "date";
		return toList(hibernateTemplate.findByNamedParam(hql, param, date));
	}
	@Override
	public List<CupState> getStates(int page, int max) {
		DetachedCriteria criteria = DetachedCriteria.forClass(CupState.class);
		criteria.add(Restrictions.eq("status", "N"));
		return toList(hibernateTemplate.findByCriteria(criteria, page, max));
	}
	@Override
	public int getSize(Date date) {
		CupStateSizeCallback action = new CupStateSizeCallback(date);
		return (int) hibernateTemplate.execute(action);
	}
	private class CupStateSizeCallback implements HibernateCallback {
		private Date date;
		public CupStateSizeCallback(Date date){
			this.date = date;
		}
		@SuppressWarnings("deprecation")
		@Override
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			String sql = "SELECT COUNT(*) AS RESULT FROM CUPTXN_STATE WHERE IMPORTDATE = :date";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setDate("date", date);
			sqlQuery.addScalar("RESULT", Hibernate.INTEGER);
			int lenght = (int) sqlQuery.uniqueResult();
			return lenght;
		}
		
	}
	@SuppressWarnings("unchecked")
	private List<CupState> toList(final List<?> beans){
		if(beans == null) return null;
		if(beans.isEmpty()) return null;
		int size = beans.size();
		CupState[] list = new CupState[size];
		list = (CupState[])beans.toArray(list);
		return Arrays.asList(list);
	}
	@Override
	public int getSize() {
		CupStateRowsCallback action = new CupStateRowsCallback();
		return (int) hibernateTemplate.execute(action);
	}
	private class CupStateRowsCallback implements HibernateCallback{

		@SuppressWarnings("deprecation")
		@Override
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			String sql = "SELECT COUNT(*) AS RESULT FROM CUPTXN_STATE WHERE STATUS = :status";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setString("status", "N");
			sqlQuery.addScalar("RESULT", Hibernate.INTEGER);
			return sqlQuery.uniqueResult();
		}
		
	}
	@Override
	public List<CupState> getStates(Date date, String card, String trace,
			int page, int max) {
		DetachedCriteria criteria = DetachedCriteria.forClass(CupState.class);
		criteria.add(Restrictions.and(Restrictions.eq("importDate", date),
				Restrictions.and(Restrictions.like("card", card),Restrictions.like("trace", trace))));
		return toList(hibernateTemplate.findByCriteria(criteria,page,max));
	}
	@Override
	public int getSize(Date date, String card, String trace) {
		CupStateRowsCondCallback action = new CupStateRowsCondCallback(date,card,trace); 
		return (int) hibernateTemplate.execute(action);
	}
	private class CupStateRowsCondCallback implements HibernateCallback{
		private Date date;
		private String card;
		private String trace;
		public CupStateRowsCondCallback(Date date, String card, String trace){
			this.date = date;
			this.card = card;
			this.trace = trace;
		}
		@SuppressWarnings("deprecation")
		@Override
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			String sql = "SELECT COUNT(*) AS RESULT FROM CUPTXN_STATE WHERE IMPORTDATE = :date AND CARD like :card AND TRACE like :trace";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setDate("date", date);
			sqlQuery.setString("card", card);
			sqlQuery.setString("trace", trace);
			sqlQuery.addScalar("RESULT", Hibernate.INTEGER);
			return sqlQuery.uniqueResult();
		}
	}
	@Override
	public CupState getState(Date date, String card, String trace, String refer) {
		String hql = "from CupState s where s.date = :date and s.card = :card and s.trace = :trace and s.refer = :refer";
		String[] paramNames = {"date", "card", "trace", "refer"};
		Object[] values = {date,card,trace,refer};
		return toList(hibernateTemplate.findByNamedParam(hql, paramNames, values)).get(0);
	}
	@Override
	public void update(CupState state) {
		hibernateTemplate.update(state);
	}
	@Override
	public List<CupState> getStates() {
		String hql = "from CupState s where s.status = :status";
		String paramName = "status";
		String value = "N";
		return toList(hibernateTemplate.findByNamedParam(hql, paramName, value));
	}
}
