package bcel.cardcenter.cbs.varofat.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Arrays;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bcel.cardcenter.cbs.varofat.dao.hibernate.callback.VisaHostCallback;
import bcel.cardcenter.cbs.varofat.entity.VisaHost;

public class VisaHostDaoImp implements VisaHostDao {
	private HibernateTemplate hibernateTemplate;
	
	public void setHibernateTemplate(SessionFactory sessionFactory){
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<VisaHost> getVisaHosts(Date date) {
		VisaHostCallback action = new VisaHostCallback(date);
		return (List<VisaHost>) hibernateTemplate.execute(action);
	}
	
	@SuppressWarnings("unchecked")
	private List<VisaHost> toList(final List<?> beans){
		if(beans == null) return null;
		if(beans.isEmpty()) return null;
		int size = beans.size();
		VisaHost[] list = new VisaHost[size];
		list = (VisaHost[]) beans.toArray(list);
		return Arrays.asList(list);
	}
	@Override
	public void addVisas(List<VisaHost> visaHosts) {
		//hibernateTemplatel
	}
}
