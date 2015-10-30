package bcel.cardcenter.cbs.marofat.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bcel.cardcenter.cbs.marofat.entity.MasterCardSettle;

@Repository("MasterCardSettleDao")
public class MasterCardSettleDaoImp implements MasterCardSettleDao {
	private HibernateTemplate hibernateTemplate;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Transactional
	@Override
	public void saveOrUpdateAll(List<MasterCardSettle> masterCardSettles)
			throws SQLException, HibernateException {
		hibernateTemplate.saveOrUpdateAll(masterCardSettles);
	}

	@Override
	public List<MasterCardSettle> getMasterCard(Date date, String card,
			String trace, int page, int max) throws SQLException,
			HibernateException {
		DetachedCriteria criteria = DetachedCriteria.forClass(MasterCardSettle.class);
		criteria.add(Restrictions.and(Restrictions.eq("date", date), Restrictions.and(Restrictions.like("card", card), Restrictions.like("trace", trace))));
		return toList(hibernateTemplate.findByCriteria(criteria, page, max));
	}
	@Override
	public List<MasterCardSettle> getMasterCard(Date date, int page, int max) 
			throws SQLException, HibernateException{
		DetachedCriteria criteria = DetachedCriteria.forClass(MasterCardSettle.class);
		criteria.add(Restrictions.eq("settleDate", date));
		return toList(hibernateTemplate.findByCriteria(criteria, page, max));
	}
	@Override
	public List<MasterCardSettle> getMasterCard(Date date) throws SQLException,
			HibernateException {
		String hql = "from MasterCardSettle s where s.settleDate = :date";
		String paramName = "date";
		return toList(hibernateTemplate.findByNamedParam(hql, paramName, date));
	}
	
	// internal use method
	@SuppressWarnings("unchecked")
	private List<MasterCardSettle> toList(final List<?> beans){
		if(beans == null) return null;
		if(beans.isEmpty()) return null;
		int size = beans.size();
		MasterCardSettle[] list = new MasterCardSettle[size];
		list = (MasterCardSettle[])beans.toArray(list);
		return Arrays.asList(list);
	}
}
