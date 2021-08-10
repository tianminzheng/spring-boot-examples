package com.springboot.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.springboot.aop.exception.MinimumAmountException;
import com.springboot.aop.model.Account;

@Aspect
public class AccountServiceAspect {

	private static final Logger LOGGER = Logger.getLogger(AccountServiceAspect.class);

	@Pointcut("execution(* com.springboot.aop.service.AccountService.doAccountTransaction(..))")
	public void doAccountTransaction() {}

	@Before("doAccountTransaction()")
	public void beforeTransaction(JoinPoint joinPoint) {
		LOGGER.info("交易前");
	}

	@After("doAccountTransaction()")
	public void afterTransaction(JoinPoint joinPoint) {
		LOGGER.info("交易后");
	}

	@AfterReturning(pointcut = "doAccountTransaction() and args(source, dest, amount)", returning = "isTransactionSuccessful")
	public void afterTransactionReturns(JoinPoint joinPoint, Account source, Account dest, Double amount,
			boolean isTransactionSuccessful) {
		if (isTransactionSuccessful) {
			LOGGER.info("转账成功 ");
		}
	}
	
	@AfterThrowing(pointcut = "doAccountTransaction()", throwing = "minimumAmountException")
	public void exceptionFromTransaction(JoinPoint joinPoint, MinimumAmountException minimumAmountException) {
		LOGGER.info("抛出异常: " + minimumAmountException.getMessage());
	}
	
	@Around("doAccountTransaction()")
	public boolean aroundTransaction(ProceedingJoinPoint proceedingJoinPoint){
		LOGGER.info("调用方法前 ");
		
		boolean isTransactionSuccessful = false;
		try {
			isTransactionSuccessful = (Boolean)proceedingJoinPoint.proceed();
		} catch (Throwable e) {
		}
		
		LOGGER.info("调用方法后");
		return isTransactionSuccessful;
	}
}
