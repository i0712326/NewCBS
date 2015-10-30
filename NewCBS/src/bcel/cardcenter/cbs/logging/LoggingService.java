package bcel.cardcenter.cbs.logging;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public interface LoggingService extends MethodBeforeAdvice,
		AfterReturningAdvice, ThrowsAdvice {
	public void afterThrowing(Method m, Object[] args, Object target,
			Throwable ex) throws Throwable;
}
