package bcel.cardcenter.cbs.varofat.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bcel.cardcenter.cbs.varofat.dao.hibernate.callback.VisaTranxSaveAll;
import bcel.cardcenter.cbs.varofat.entity.VisaTranx;

public class VisaTranxDaoImp implements VisaTranxDao {
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(SessionFactory sessionFactory){
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Override
	
	public void saveAll(List<VisaTranx> visaTranxs) {
		VisaTranxSaveAll action = new VisaTranxSaveAll(visaTranxs);
		hibernateTemplate.execute(action);
	}

}
