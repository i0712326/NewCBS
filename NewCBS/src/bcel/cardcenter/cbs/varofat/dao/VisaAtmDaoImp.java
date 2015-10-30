package bcel.cardcenter.cbs.varofat.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Arrays;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bcel.cardcenter.cbs.varofat.dao.hibernate.callback.CountRecordHibernateCallback;
import bcel.cardcenter.cbs.varofat.dao.hibernate.callback.UniqueVisaAtmHibernateCallback;
import bcel.cardcenter.cbs.varofat.dao.hibernate.callback.VisaAtmHibernateCallback;
import bcel.cardcenter.cbs.varofat.entity.VisaSettle;

public class VisaAtmDaoImp implements VisaAtmDao {
	private HibernateTemplate hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Override
	public List<VisaSettle> getVisaAtm(Date date, String type,
			String ref, String card ,int page, int rowNum) {
		return toList(hibernateTemplate.executeFind(new VisaAtmHibernateCallback(date, type, ref, card, page, rowNum)));
	}
	@Override
	public void updateVisaAtm(VisaSettle visaAtm){
		VisaSettle visa = getUniqueVisaAtm(visaAtm);
		visa.setStatus(visaAtm.getStatus());
		visa.setCaseNumber(visaAtm.getCaseNumber());
		hibernateTemplate.update(visa);
	}
	@Override
	public int recordCount(Date date, String group, String retr, String card){
		return (int) hibernateTemplate.execute(new CountRecordHibernateCallback(date,group,retr,card));
	}
	public VisaSettle getUniqueVisaAtm(VisaSettle visaAtm){
		UniqueVisaAtmHibernateCallback action = new UniqueVisaAtmHibernateCallback(visaAtm.getCard(),visaAtm.getRetrieval(),visaAtm.getTrace(),visaAtm.getType());
		return (VisaSettle) hibernateTemplate.execute(action);
	}
	@Override
	public void bulkUpdate(List<VisaSettle> updateList) {
		hibernateTemplate.saveOrUpdateAll(updateList);
	}
	@SuppressWarnings("unchecked")
	private List<VisaSettle> toList(final List<?> beans){
		if(beans == null) return null;
		if(beans.isEmpty()) return null;
		int size = beans.size();
		VisaSettle[] list = new VisaSettle[size];
		list = (VisaSettle[]) beans.toArray(list);
		return Arrays.asList(list);
	}
	
}
