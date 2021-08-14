package com.springboot.cache.authentication.config;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class SpringDatabaseConfig {

    @Bean
    public NamedParameterJdbcTemplate myJdbcTemplate() throws PropertyVetoException, SQLException {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() throws PropertyVetoException, SQLException {
    	EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    	EmbeddedDatabase db = builder
    		.setType(EmbeddedDatabaseType.H2)
    		.addScript("schema.sql")
    		.addScript("users.sql")
    		.addScript("authorities.sql")
    		.build();
    	
        return db;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException, SQLException {
        return new DataSourceTransactionManager(dataSource());
    }
    
}