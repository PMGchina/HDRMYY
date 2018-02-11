package com.beamofsoul.springboot.management.repository;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.NonNull;

public abstract class BaseJpaRepositoryConfiguration {
	
	@Value("${spring.jpa.show-sql}")  
    String showSQL;
    
    @Value("${spring.jpa.properties.hibernate.dialect}")  
    String dialect;
    
    @Value("${spring.jpa.generate-ddl}")
    Boolean generateDDL;
    
    @Value("${spring.jpa.hibernate.use-new-id-generator-mappings}")
    String useNewIdGeneratorMappings;

	boolean getGeneratDdlStrategy() {
		return generateDDL.booleanValue(); 
	}

	HibernateJpaVendorAdapter getHibernateJpaVendorAdapter() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(getGeneratDdlStrategy());
		return vendorAdapter;
	}

	Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.show_sql", showSQL);
		properties.setProperty("hibernate.dialect", dialect);
		properties.setProperty("hibernate.id.new_generator_mappings", useNewIdGeneratorMappings);
		return properties;
	}
	
	LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource, String entityPackagePath2Scan, String persistenceUnitName) {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName(persistenceUnitName);
		factoryBean.setJpaVendorAdapter(getHibernateJpaVendorAdapter());
		factoryBean.setPackagesToScan(entityPackagePath2Scan);
		factoryBean.setDataSource(dataSource);
		factoryBean.setJpaProperties(getProperties());
		factoryBean.afterPropertiesSet();
		return factoryBean;
	}
	
	EntityManager getEntityManager(@NonNull LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		return entityManagerFactory.getObject().createEntityManager();
	}
	
	public abstract LocalContainerEntityManagerFactoryBean entityManagerFactory();
	
	PlatformTransactionManager getTransactionManager() {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}
}
