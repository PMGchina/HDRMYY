package com.beamofsoul.springboot.management.repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.beamofsoul.springboot.management.util.ConfigurationReader;

@Configuration
@EnableJpaRepositories(
		basePackages={"com.beamofsoul.springboot.other.repository"},
		transactionManagerRef="otherTransactionManager",
		entityManagerFactoryRef="otherEntityManagerFactory",
		repositoryFactoryBeanClass=CustomRepositoryFactoryBean.class)
@EnableSpringDataWebSupport
public class OtherJpaRepositoryConfiguration extends BaseJpaRepositoryConfiguration {
	
	private static final String OTHER_JPA_ENTITY_PACKAGE_PATH = "com.beamofsoul.springboot.other.entity";
	
	@Value("${project.base.jpa.generate-ddl}")  
    String generateDDL4Other;
	
	@Autowired
	@Qualifier("otherDataSource")
	private DataSource dataSource;
	
	@Bean("otherEntityManager")
	public EntityManager entityManager() {
		return getEntityManager(entityManagerFactory());
	}

	@Bean("otherEntityManagerFactory")
	@Override
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		return getEntityManagerFactory(dataSource,OTHER_JPA_ENTITY_PACKAGE_PATH, "otherPersistenceUnit");
	}
	
	@Bean("otherTransactionManager")
	PlatformTransactionManager transactionManager() {
		return getTransactionManager();
	}
	
	@Override
	boolean getGeneratDdlStrategy() {
		return ConfigurationReader.asBoolean(generateDDL4Other).booleanValue();
	}
}
