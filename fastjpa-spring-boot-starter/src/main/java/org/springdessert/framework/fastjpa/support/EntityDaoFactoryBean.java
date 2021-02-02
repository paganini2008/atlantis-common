package org.springdessert.framework.fastjpa.support;

import javax.persistence.EntityManager;

import org.springdessert.framework.fastjpa.support.hibernate.HibernateDaoSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * 
 * EntityDaoFactoryBean
 * 
 * @author Jimmy Hoff
 * @version 1.0
 */
@SuppressWarnings("all")
public class EntityDaoFactoryBean<R extends JpaRepository<E, ID>, E, ID> extends JpaRepositoryFactoryBean<R, E, ID> {

	public EntityDaoFactoryBean(Class<R> repositoryInterface) {
		super(repositoryInterface);
	}

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
		return new HibernateDaoFactory<E, ID>(em);
	}

	private static class HibernateDaoFactory<E, ID> extends JpaRepositoryFactory {

		public HibernateDaoFactory(EntityManager em) {
			super(em);
		}

		@Override
		protected SimpleJpaRepository<E, ID> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
			return new HibernateDaoSupport<E, ID>((Class<E>) information.getDomainType(), entityManager);
		}

		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return HibernateDaoSupport.class;
		}
	}

}
