package com.dariotek;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.dariotek.service.AmeritradeTransactionService;
import com.dariotek.service.AmeritradeTransactionServiceImpl;
import com.dariotek.service.HistoricalStockPriceService;
import com.dariotek.service.HistoricalStockPriceServiceImpl;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:persistence-mysql.properties" })
@ComponentScan({ "com.dariotek" })
public class TradingInvestingAppConfig {

	@Autowired
	private Environment env;

	@Bean
	public LocalSessionFactoryBean sessionFactory() throws Throwable{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		// sessionFactory.setDataSource(restDataSource());
		sessionFactory.setDataSource(pooledDataSource()); // uses C3P0 pooled data source
		sessionFactory.setPackagesToScan(
				new String[] { "com.dariotek.entity" }); //We don't need to register each entity objects one by one
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	
	@Bean
	public DataSource restDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}
	
	@Bean
	public DataSource pooledDataSource() throws Throwable{
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass(env.getProperty("jdbc.driverClassName")); //loads the jdbc driver            
		cpds.setJdbcUrl(env.getProperty("jdbc.url"));
		cpds.setUser(env.getProperty("jdbc.user"));                                  
		cpds.setPassword(env.getProperty("jdbc.password"));                                  
			
		// the settings below are optional -- c3p0 can work with defaults
		cpds.setMinPoolSize(Integer.parseInt(env.getProperty("c3p0.min_pool_size")));                                     
		cpds.setAcquireIncrement(Integer.parseInt(env.getProperty("c3p0.pool_size_increment")));
		cpds.setMaxPoolSize(Integer.parseInt(env.getProperty("c3p0.max_pool_size")));
		
		return cpds;
	}
		
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
	
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	@Bean
	Properties hibernateProperties() {
		Properties props = new Properties();
		//props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));		
		props.setProperty("hibernate.current_session_context_class", env.getProperty("hibernate.current_session_context_class"));		
		//props.setProperty("hibernate.globally_quoted_identifiers", "true");
		return props;
	}
	
	@Bean
	AmeritradeTransactionService ameritradeTransactionService() {
		return new AmeritradeTransactionServiceImpl(env.getProperty("ameritrade.filePath"));
	}
	
	@Bean
	HistoricalStockPriceService historicalStockPriceService() {
		return new HistoricalStockPriceServiceImpl(env.getProperty("yahooFinance.filePath"));
	}
	
}
