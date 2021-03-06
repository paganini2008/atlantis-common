package indi.atlantis.framework.fastjpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

/**
 * 
 * NotFilter
 *
 * @author Jimmy Hoff
 * @version 1.0
 */
public class NotFilter extends LogicalFilter {

	private final Filter filter;

	public NotFilter(Filter filter) {
		this.filter = filter;
	}

	public Predicate toPredicate(Model<?> model, CriteriaBuilder builder) {
		return filter.toPredicate(model, builder).not();
	}

}
