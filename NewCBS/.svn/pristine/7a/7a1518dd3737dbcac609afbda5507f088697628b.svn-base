package bcel.cardcenter.cbs.aarofat.dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bcel.cardcenter.cbs.aarofat.entity.Atm;

public class AtmDaoImp implements AtmDao {
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Atm> getAtmId(String atmId, String atmName,int page,int rp) {
		return (List<Atm>)hibernateTemplate.execute(new MyHibernateCallback(atmId,atmName,page,rp,GETATMBYIDNAME));
	}
	
	@Override
	public int getAtmTotal(String atmId, String atmName){
		String[] params={"atmId","atmName"};
		String[] values={atmId,atmName};
		List<Atm> atmList = toList(hibernateTemplate.findByNamedQueryAndNamedParam(GETATMBYIDNAME,params,values));
		if(atmList == null)
			return 0;
		return atmList.size();
	}
	
	class MyHibernateCallback implements HibernateCallback{
		private String atmId;
		private String atmName;
		private int page;
		private int rp;
		private String hql;
		
		public MyHibernateCallback(){}
		
		public MyHibernateCallback(String atmId, String atmName, int page, int rp, String hql){
			this.atmId   = atmId;
			this.atmName = atmName;
			this.page    = page;
			this.rp      = rp;
			this.hql     = hql;
		}
		@Override
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			Query query = session.getNamedQuery(hql);
			query.setParameter("atmId", atmId);
			query.setParameter("atmName", atmName);
			query.setFirstResult((page-1)*rp);
			query.setMaxResults(rp);
			return query.list();
		}
	}
	// Convert Object list to ATM list
	private static List<Atm> toList(final List<?> objs){
		if(objs==null)
			return null;
		if(objs.isEmpty())
			return null;
		Atm[] list = new Atm[objs.size()];
		list = (Atm[])objs.toArray(list);
		return Arrays.asList(list);
	}
}
