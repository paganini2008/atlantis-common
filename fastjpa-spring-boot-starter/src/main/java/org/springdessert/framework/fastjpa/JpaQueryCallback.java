package org.springdessert.framework.fastjpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * 
 * JpaQueryCallback
 * 
 * @author Jimmy Hoff
 *
 * @since 1.0
 */
public interface JpaQueryCallback<T> {

	CriteriaQuery<T> doInJpa(CriteriaBuilder builder);

}
