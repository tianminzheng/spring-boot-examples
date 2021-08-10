package com.springboot.aop.proxy.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springboot.aop.proxy.config.CGLIBProxyAppConfig;
import com.springboot.aop.proxy.config.JDKProxyAppConfig;
import com.springboot.aop.proxy.model.Account;
import com.springboot.aop.proxy.service.AccountService;
import com.springboot.aop.proxy.service.Impl.AccountServiceImpl;

public class AopProxyTest {

	private static final Logger LOGGER = Logger.getLogger(AopProxyTest.class);

	@Test
	public void testAopProxyPerformance() {
		int countofObjects = 5000;
		AccountServiceImpl[] unproxiedClasses = new AccountServiceImpl[countofObjects];
		for (int i = 0; i < countofObjects; i++) {
			unproxiedClasses[i] = new AccountServiceImpl();
		}

		AccountService[] cglibProxyClasses = new AccountService[countofObjects];
		AccountService accountService = null;
		for (int i = 0; i < countofObjects; i++) {
			accountService = new AnnotationConfigApplicationContext(CGLIBProxyAppConfig.class)
					.getBean(AccountService.class);
			cglibProxyClasses[i] = accountService;
		}

		AccountService[] jdkProxyClasses = new AccountService[countofObjects];
		for (int i = 0; i < countofObjects; i++) {
			accountService = new AnnotationConfigApplicationContext(JDKProxyAppConfig.class)
					.getBean(AccountService.class);
			jdkProxyClasses[i] = accountService;
		}

		long timeTookForUnproxiedObjects = invokeTargetObjects(countofObjects, unproxiedClasses);
		displayResults("NOProxy", timeTookForUnproxiedObjects);

		long timeTookForJdkProxiedObjects = invokeTargetObjects(countofObjects, jdkProxyClasses);
		displayResults("JDKProxy", timeTookForJdkProxiedObjects);

		long timeTookForCglibProxiedObjects = invokeTargetObjects(countofObjects, cglibProxyClasses);
		displayResults("CGLIBProxy", timeTookForCglibProxiedObjects);

	}

	private void displayResults(String label, long timeTook) {
		LOGGER.info(label + ": " + timeTook + "(ns) " + (timeTook / 1000000) + "(ms)");
	}

	private long invokeTargetObjects(int countofObjects, AccountService[] classes) {
		long start = System.nanoTime();
		Account source = new Account(101, "Account1");
		Account dest = new Account(102, "Account2");
		for (int i = 0; i < countofObjects; i++) {
			classes[i].doAccountTransaction(source, dest, 100);
		}
		long end = System.nanoTime();
		long execution = end - start;
		return execution;
	}
}
