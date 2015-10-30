package bcel.cardcenter.cbs.varofat.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import bcel.cardcenter.cbs.varofat.entity.VisaTxn;
@Repository("visaTxnDao")
public class VisaTxnDaoImp implements VisaTxnDao {
	private HibernateTemplate  hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Override
	public void save(VisaTxn visaCard) throws SQLException, HibernateException {
		hibernateTemplate.save(visaCard);
	}
	@Override
	public void savaAll(List<VisaTxn> visaCards) throws SQLException,
			HibernateException {
		hibernateTemplate.saveOrUpdateAll(visaCards);
	}
}
