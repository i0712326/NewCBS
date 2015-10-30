package bcel.cardcenter.cbs.logging;

import java.lang.reflect.Method;
import org.apache.log4j.Logger;

public class LoggingServiceImp implements LoggingService {
	private static Logger logger = null;

	public void afterThrowing(Method method, Object[] args, Object target,
			Throwable ex) throws Throwable{
		logger = Logger.getLogger(target.getClass());
		logger.debug("Exception occur in method : "+method.getName());
		logger.debug("Method's arguments :");
		for(int i=0;i<args.length;i++){
			logger.debug("args["+i+"] : " + args[i].toString());
		}
		logger.debug(" Exception is \n"+ex.getMessage());
	}
	
	@Override
	public void afterReturning(Object ret, Method method, Object[] args,
			Object target) throws Throwable {
		logger = Logger.getLogger(target.getClass());
		logger.debug("Ending Method : "+method.getName());
		logger.debug("Method's arguments :");
		for(int i=0;i<args.length;i++){
			logger.debug("args["+i+"] : " + args[i].toString());
		}
		if(ret!=null)
			logger.debug("return "+ret.toString());
		else
			logger.debug("retunr void");
	}
	
	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		logger = Logger.getLogger(target.getClass());
		logger.debug("Begining Method : "+method.getName());
		logger.debug("Method's arguments :");
		for(int i=0;i<args.length;i++){
			logger.debug("args["+i+"] : " + args[i].toString());
		}
	}
}
