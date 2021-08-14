package com.springboot.cache.authentication.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.cache.EhCacheBasedUserCache;

@Configuration
@EnableCaching
public class SpringAuthCacheConfig {

	@Bean
	public EhCacheFactoryBean ehCacheFactoryBean() {
		EhCacheFactoryBean ehCacheFactory = new EhCacheFactoryBean();
		ehCacheFactory.setCacheManager(cacheManagerFactoryBean().getObject());
		return ehCacheFactory;
	}

	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(cacheManagerFactoryBean().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean cacheManagerFactoryBean() {
		EhCacheManagerFactoryBean cacheManager = new EhCacheManagerFactoryBean();
		return cacheManager;
	}

	@Bean
	public UserCache userCache() {
		EhCacheBasedUserCache userCache = new EhCacheBasedUserCache();
		userCache.setCache(ehCacheFactoryBean().getObject());
		return userCache;
	}
}
