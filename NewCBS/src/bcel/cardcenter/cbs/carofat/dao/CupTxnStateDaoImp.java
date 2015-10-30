package bcel.cardcenter.cbs.carofat.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bcel.cardcenter.cbs.carofat.entity.CupTxnState;

public class CupTxnStateDaoImp implements CupTxnStateDao {
	private HibernateTemplate hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Override
	public void saveOrUpdateAll(List<CupTxnState> cups) {
		this.hibernateTemplate.saveOrUpdateAll(cups);
	}
	@Override
	public List<CupTxnState> getCupTxnStates(Date date, String card,
			String trace) {
		String hql = "from CupTxnState c where c.date = :date and c.card like :card and c.trace like :trace";
		String[] params = {"date","card","trace"};
		Object[] values = {date, card, trace};
		return toList(hibernateTemplate.findByNamedParam(hql, params, values));
	}
	private final List<CupTxnState> toList(final List<?> beans){
		if(beans==null) return null;
		if(beans.isEmpty()) return null;
		CupTxnState[] list = new CupTxnState[beans.size()];
		list = (CupTxnState [])beans.toArray(list);
		return Arrays.asList(list);
	}
	@Override
	public boolean confirmCupTxnStateBatch() {
		ConfirmCupTxnStateCallback action = new ConfirmCupTxnStateCallback();
		return (boolean) hibernateTemplate.execute(action);
	}
	@Override
	public List<CupTxnState> getTxnStates(Date date, int first, int max) {
		DetachedCriteria criteria = DetachedCriteria.forClass(CupTxnState.class).add(Restrictions.eq("importDate", date));
		return toList(hibernateTemplate.findByCriteria(criteria, first, max));
	}
	@Override
	public int getRows() {
		CountStates countStates = new CountStates();
		return (int) hibernateTemplate.execute(countStates);
	}
	
	private class CountStates implements HibernateCallback{

		@SuppressWarnings("deprecation")
		@Override
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			String sql = "SELECT COUNT(*) AS RESULT FROM TMPCUPSTATE";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addScalar("RESULT", Hibernate.INTEGER);
			int len = (int) sqlQuery.uniqueResult();
			return len;
		}
		
	}

	@Override
	public boolean deleteAll(Date date) {
		DeleteCallback action = new DeleteCallback(date);
		int ret = (int) hibernateTemplate.execute(action);
		if(ret!=0)
			return true;
		return false;
	}
	private class DeleteCallback implements HibernateCallback{
		private Date date;
		public DeleteCallback(Date date){
			this.date = date;
		}
		@Override
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			String hql = "delete from CupTxnState c where c.importDate = :date";
			Query query = session.createQuery(hql);
			query.setDate("date", date);
			int ret = query.executeUpdate();
			return ret;
		}
		
	}

	@Override
	public List<CupTxnState> getTmpStates() {
		String hql = "from CupTxnState";
		return toList(hibernateTemplate.find(hql));
	}
	@Override
	public List<CupTxnState> getTmpStates(int page, int rows) {
		DetachedCriteria criteria = DetachedCriteria.forClass(CupTxnState.class);
		List<CupTxnState> states = toList(hibernateTemplate.findByCriteria(criteria, page, rows));
		return states;
	}
}
