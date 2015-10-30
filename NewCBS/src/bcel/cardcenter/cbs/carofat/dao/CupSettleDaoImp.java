package bcel.cardcenter.cbs.carofat.dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.sql.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bcel.cardcenter.cbs.carofat.entity.CupSettle;

public class CupSettleDaoImp implements CupSettleDao {
	private HibernateTemplate hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Override
	public List<CupSettle> getSettles(Date date, int page, int max) {
		DetachedCriteria criteria = DetachedCriteria.forClass(CupSettle.class);
		criteria.add(Restrictions.eq("importDate", date));
		return toList(hibernateTemplate.findByCriteria(criteria,page,max));
	}

	@Override
	public List<CupSettle> getSettles(Date date) {
		String hql = "from CupSettle cs where cs.importDate= :date";
		String params = "date";
		return toList(hibernateTemplate.findByNamedParam(hql,params,date));
	}
	
	@Override
	public List<CupSettle> getSettles(Date date, String card, String trace,
			int page, int max) {
		DetachedCriteria criteria = DetachedCriteria.forClass(CupSettle.class);
		criteria.add(Restrictions.and(Restrictions.eq("importDate",date), 
				Restrictions.and(Restrictions.like("card", card), Restrictions.like("trace", trace))));
		return toList(hibernateTemplate.findByCriteria(criteria,page,max));
	}

	@Override
	public int getSize(Date date){
		CupSettleSizeCallback action = new CupSettleSizeCallback(date);
		return (int )hibernateTemplate.execute(action);
	}
	private class CupSettleSizeCallback implements HibernateCallback{
		private Date date;
		public CupSettleSizeCallback(Date date){
			this.date = date;
		}
		@SuppressWarnings("deprecation")
		@Override
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			String sql = "SELECT COUNT(*) AS RESULT FROM CUPTXN_SETTLE WHERE IMPORTDATE = :date";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setDate("date", date);
			sqlQuery.addScalar("RESULT", Hibernate.INTEGER);
			return sqlQuery.uniqueResult();
		}
	}
	@Override
	public List<CupSettle> getReconSettles(int page, int max) {
		DetachedCriteria criteria = DetachedCriteria.forClass(CupSettle.class);
		criteria.add(Restrictions.eq("status", "N"));
		return toList(hibernateTemplate.findByCriteria(criteria, page, max));
	}
	private List<CupSettle> toList(final List<?> beans){
		if(beans == null) return null;
		if(beans.isEmpty()) return null;
		int size = beans.size();
		CupSettle[] list= new CupSettle[size];
		list = (CupSettle[])beans.toArray(list);
		return Arrays.asList(list);
	}
	@Override
	public void updateCupSettles(List<CupSettle> settles) {
		
	}
	@Override
	public int getSize() {
		CupSettleRowsCallback action = new CupSettleRowsCallback();
		return (int) hibernateTemplate.execute(action);
	}
	private class CupSettleRowsCallback implements HibernateCallback{

		@SuppressWarnings("deprecation")
		@Override
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			String sql = "SELECT COUNT(*) AS RESULT FROM CUPTXN_SETTLE WHERE STATUS = :status";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setString("status", "N");
			sqlQuery.addScalar("RESULT", Hibernate.INTEGER);
			return sqlQuery.uniqueResult();
		}
		
	}
	@Override
	public int getSize(Date date, String card, String trace) {
		CupSettleRowsCondCallback action = new CupSettleRowsCondCallback(date,card,trace);
		return (int) hibernateTemplate.execute(action);
	}
	private class CupSettleRowsCondCallback implements HibernateCallback{
		private Date date;
		private String card;
		private String trace;
		public CupSettleRowsCondCallback(Date date, String card, String trace){
			this.date = date;
			this.card = card;
			this.trace = trace;
		}
		@SuppressWarnings("deprecation")
		@Override
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			String sql = "SELECT COUNT(*) AS RESULT FROM CUPTXN_SETTLE WHERE IMPORTDATE = :date and CARD like :card and TRACE like :trace ";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.setDate("date", date);
			sqlQuery.setString("card", card);
			sqlQuery.setString("trace", trace);
			sqlQuery.addScalar("RESULT", Hibernate.INTEGER);
			return sqlQuery.uniqueResult();
		}
	}
	@Override
	public CupSettle getSettle(Date date, String card, String trace,
			String refer) {
		String hql = "from CupSettle s where s.date=:date and s.card = :card and s.trace = :trace and s.refer = :refer";
		String[] paramNames = {"date","card","trace","refer"};
		Object[] values = {date,card,trace,refer};
		return toList(hibernateTemplate.findByNamedParam(hql, paramNames, values)).get(0);
	}
	@Override
	public void updateSettles(CupSettle settle) {
		hibernateTemplate.update(settle);
	}
	@Override
	public List<CupSettle> getSettles() {
		String hql = "from CupSettle s where s.status= :status";
		String paramName="status";
		String value="N";
		return toList(hibernateTemplate.findByNamedParam(hql, paramName, value));
	}
}
