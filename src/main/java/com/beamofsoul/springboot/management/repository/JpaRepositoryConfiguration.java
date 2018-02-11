package com.beamofsoul.springboot.management.repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
		basePackages={"com.beamofsoul.springboot.repository"},
		transactionManagerRef="transactionManager",
		repositoryFactoryBeanClass=CustomRepositoryFactoryBean.class)
@EnableSpringDataWebSupport
public class JpaRepositoryConfiguration extends BaseJpaRepositoryConfiguration {
	
	private static final String DEFAULT_JPA_ENTITY_PACKAGE_PATH = "com.beamofsoul.springboot.entity";

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	@Bean
	@Primary
	public EntityManager entityManager() {
		return getEntityManager(entityManagerFactory());
	}

	@Bean
	@Primary
	@Override
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {		
		return getEntityManagerFactory(dataSource, DEFAULT_JPA_ENTITY_PACKAGE_PATH, "defaultPersistenceUnit");
	}
	
	@Bean
	@Primary
	PlatformTransactionManager transactionManager() {
		return getTransactionManager();
	}
}
