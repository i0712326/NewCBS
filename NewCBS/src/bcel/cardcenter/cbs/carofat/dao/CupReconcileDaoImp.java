package bcel.cardcenter.cbs.carofat.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import jxl.common.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class CupReconcileDaoImp implements CupReconcileDao {
	private static final Logger logger = Logger.getLogger(CupReconcileDaoImp.class);
	private HibernateTemplate hibernateTemplate;
	public void setSessionFactory(SessionFactory sessionFactory){
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Override
	public void reconcile(Date date) throws Exception {
		try{
			CupReconcileCallback cupReconcileCallback = new CupReconcileCallback(date);
			hibernateTemplate.execute(cupReconcileCallback);
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to excute query",ex);
			throw ex;
		}
	}

	@Override
	public void confirmReconcile(Date date) throws Exception {
		try{
			CupReconcileConfirm cupReconcileConfirm = new CupReconcileConfirm(date);
			hibernateTemplate.execute(cupReconcileConfirm);
		}
		catch(Exception ex){
			logger.debug("Excepiton occur while try to confirm reconcile result",ex);
			throw ex;
		}
	}
	
	private class CupReconcileCallback implements HibernateCallback{
		private Date date;
		
		public CupReconcileCallback(Date date){
			this.date = date;
		}

		@Override
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			try{
				CupReconcileWork cupReconcileWork = new CupReconcileWork(date);
				session.doWork(cupReconcileWork);
				return null;
			}
			catch(Exception ex){
				logger.debug("Exception while try to execute callback",ex);
				throw ex;
			}
		}
		
		private class CupReconcileWork implements Work{
			private Date date;
			public CupReconcileWork(Date date){
				this.date = date;
			}
			@Override
			public void execute(Connection conn) throws SQLException {
				String sql = "{ call NEWCBS.RECONCILE_CUP_REMOTEONUS( :date )}";
				CallableStatement call = conn.prepareCall(sql);
				call.setDate("date", date);
				call.execute();
			}
		}
	}
	
	private class CupReconcileConfirm implements HibernateCallback{
		private Date date;
		public CupReconcileConfirm(Date date){
			this.date = date;
		}
		@Override
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			try{
				CupReconcileConfirmWork work = new CupReconcileConfirmWork(date);
				session.doWork(work);
				return null;
			}
			catch(Exception ex){
				logger.debug("Exception occur while try to excute callback", ex);
				throw ex;
			}
		}
		private class CupReconcileConfirmWork implements Work{
			private Date date;
			public CupReconcileConfirmWork(Date date){
				this.date = date;
			}
			@Override
			public void execute(Connection conn) throws SQLException {
				String sql = "{ call NEWCBS.RECONCILE_CUP_COMFIRM(:date) }";
				CallableStatement call = conn.prepareCall(sql);
				call.setDate("date", date);
				call.execute();
			}
		}
	}
	
	@Override
	public void cancelReconcile() throws Exception{
		try{
			CancelReconcileCallback action = new CancelReconcileCallback();
			hibernateTemplate.execute(action);
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to cancal reconcile",ex);
			throw ex;
		}
	}
	private class CancelReconcileCallback implements HibernateCallback{

		@Override
		public Object doInHibernate(Session session) throws HibernateException,
				SQLException {
			CancelReconcileWork work = new CancelReconcileWork();
			session.doWork(work);
			return null;
		}
		
		private class CancelReconcileWork implements Work{

			@Override
			public void execute(Connection conn) throws SQLException {
				String sql = "{ call NEWCBS.CANCELRECONCILE_CUP }";
				CallableStatement call = conn.prepareCall(sql);
				call.execute();
			}
			
		}
	}
	
}
