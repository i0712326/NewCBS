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

import bcel.cardcenter.cbs.carofat.entity.CupTxnSettle;

public class CupTxnSettleDaoImp implements CupTxnSettleDao {
	private HibernateTemplate hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Override
	public void saveOrUpdateAll(List<CupTxnSettle> cupTxns) {
		hibernateTemplate.saveOrUpdateAll(cupTxns);
	}

	@Override
	public List<CupTxnSettle> getCupTxnSettles(Date date, String card,
			String trace) {
		String hql = "from CupTxnSettle c where c.date = :date and c.card like :card and c.trace like :trace";
		String[] params = {"date","card","trace"};
		Object[] values = {date,card,trace};
		return toList(hibernateTemplate.findByNamedParam(hql, params, values));
	}

	@Override
	public boolean confirmCupTxnSettleBatch() {
		ConfirmCupTxnSettleCallback action = new ConfirmCupTxnSettleCallback();
		return (boolean) hibernateTemplate.execute(action);
	}
	
	private List<CupTxnSettle> toList(final List<?> objs){
		if(objs==null)
			return null;
		if(objs.isEmpty())
			return null;
		CupTxnSettle[] list = new CupTxnSettle[objs.size()];
		list = (CupTxnSettle[])objs.toArray(list);
		return Arrays.asList(list);
	}
	@Override
	public List<CupTxnSettle> getTxnSettles(Date date, int first, int max) {
		DetachedCriteria criteria = DetachedCriteria.forClass(CupTxnSettle.class).add(Restrictions.eq("importDate", date));
		List<CupTxnSettle> settles = toList(hibernateTemplate.findByCriteria(criteria , first, max));
		return settles;
	}
	@Override
	public int getRows() {
		CountSettles action = new CountSettles();
		return (int) hibernateTemplate.execute(action);
	}
	
	private class CountSettles implements HibernateCallback{
		@SuppressWarnings("deprecation")
		@Override
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			String sql = "SELECT COUNT(*) AS RESULT FROM TMPCUPSETTLE";
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			sqlQuery.addScalar("RESULT", Hibernate.INTEGER);
			int length = (int) sqlQuery.uniqueResult();
			return length;
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
			String hql = "delete from CupTxnSettle c where c.importDate = :date";
			Query query = session.createQuery(hql);
			query.setDate("date", date);
			int ret = query.executeUpdate();
			return ret;
		}
	}

	@Override
	public List<CupTxnSettle> getTmpSettles() {
		String hql = "from CupTxnSettle";
		return toList(hibernateTemplate.find(hql));
	}
	@Override
	public List<CupTxnSettle> getTmpSettles(int page, int rows) {
		DetachedCriteria criteria = DetachedCriteria.forClass(CupTxnSettle.class);
		List<CupTxnSettle> settles = toList(hibernateTemplate.findByCriteria(criteria, page, rows));
		return settles;
	}
}
