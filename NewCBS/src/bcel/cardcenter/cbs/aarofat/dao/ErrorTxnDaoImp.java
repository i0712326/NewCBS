package bcel.cardcenter.cbs.aarofat.dao;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bcel.cardcenter.cbs.aarofat.entity.ErrorTxn;

public class ErrorTxnDaoImp implements ErrorTxnDao {
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@Override
	public void save(ErrorTxn errorTxn) {
		hibernateTemplate.save(errorTxn);
	}

	@Override
	public List<ErrorTxn> getErrorTxns(Date start, Date end) {
		String[] paramNames= {"start","end"};
		Date[] values = {start,end};
		return toList(hibernateTemplate.findByNamedQueryAndNamedParam(errorQuery, paramNames, values));
	}

	@Override
	public void update(ErrorTxn errorTxn) {
		hibernateTemplate.update(errorTxn);
	}
	
	private List<ErrorTxn> toList(final List<?> objs){
		if(objs==null)
			return null;
		if(objs.isEmpty())
			return null;
		ErrorTxn[] list = new ErrorTxn[objs.size()];
		list = (ErrorTxn[])objs.toArray(list);
		return Arrays.asList(list);
	}
}
