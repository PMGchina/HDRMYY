package com.beamofsoul.springboot.management.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class CustomRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable> extends JpaRepositoryFactoryBean<T, S, ID> {

   public CustomRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
      super(repositoryInterface);
   }

   @Override
   protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
      return new CustomRepositoryFactory(entityManager);
   }
}
