package bcel.cardcenter.cbs.varofat.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;

import bcel.cardcenter.cbs.varofat.entity.VisaTxn;

public interface VisaTxnDao {
	public void save(VisaTxn visaCard) throws SQLException, HibernateException;
	public void savaAll(List<VisaTxn> visaCards) throws SQLException, HibernateException;
}
