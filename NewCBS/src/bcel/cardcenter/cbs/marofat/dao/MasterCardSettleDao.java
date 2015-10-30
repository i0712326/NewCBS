package bcel.cardcenter.cbs.marofat.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;

import bcel.cardcenter.cbs.marofat.entity.MasterCardSettle;

public interface MasterCardSettleDao {
	public void saveOrUpdateAll(List<MasterCardSettle> masterCardSettles) throws SQLException, HibernateException;
	public List<MasterCardSettle> getMasterCard(Date date, String card, String trace, int page, int max) throws SQLException, HibernateException;
	public List<MasterCardSettle> getMasterCard(Date date, int page, int max) throws SQLException, HibernateException;
	public List<MasterCardSettle> getMasterCard(Date date) throws SQLException, HibernateException;
}
