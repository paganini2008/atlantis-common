package indi.atlantis.framework.fastjpa.support;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.github.paganini2008.devtools.converter.ConvertUtils;
import com.github.paganini2008.devtools.jdbc.ResultSetSlice;

import indi.atlantis.framework.fastjpa.Fields;
import indi.atlantis.framework.fastjpa.Filter;
import indi.atlantis.framework.fastjpa.JpaDaoSupport;
import indi.atlantis.framework.fastjpa.JpaDelete;
import indi.atlantis.framework.fastjpa.JpaQuery;
import indi.atlantis.framework.fastjpa.JpaUpdate;
import indi.atlantis.framework.fastjpa.Model;
import indi.atlantis.framework.fastjpa.Property;
import indi.atlantis.framework.fastjpa.ResultSetExtractor;

/**
 * 
 * EntityDaoSupport
 * 
 * @author Jimmy Hoff
 * 
 * 
 */
public abstract class EntityDaoSupport<E, ID> extends JpaDaoSupport<E, ID> implements EntityDao<E, ID> {

	public EntityDaoSupport(Class<E> entityClass, EntityManager em) {
		super(entityClass, em);
		this.entityClass = entityClass;
	}

	protected final Class<E> entityClass;

	public ResultSetSlice<E> select(String sql, Object[] arguments) {
		return new NativeQueryResultSetSlice<E>(sql, arguments, em, entityClass);
	}

	public <T> T getSingleResult(String sql, Object[] arguments, Class<T> requiredType) {
		return execute(sql, arguments, query -> {
			Object result = query.getSingleResult();
			try {
				return result != null ? requiredType.cast(result) : null;
			} catch (ClassCastException e) {
				return (T) ConvertUtils.convertValue(result, requiredType);
			}
		});
	}

	public int executeUpdate(String sql, Object[] arguments) {
		return execute(sql, arguments, query -> {
			return query.executeUpdate();
		});
	}

	public <T> T execute(String sql, Object[] arguments, ResultSetExtractor<T> extractor) {
		Query query = em.createNativeQuery(sql);
		if (arguments != null && arguments.length > 0) {
			int index = 1;
			for (Object arg : arguments) {
				query.setParameter(index++, arg);
			}
		}
		return extractor.extractData(query);
	}

	public boolean exists(final Filter filter) {
		return count(filter) > 0;
	}

	public long count(final Filter filter) {
		return count((root, query, builder) -> {
			return filter.toPredicate(Model.forRoot(root), builder);
		});
	}

	public List<E> findAll(Filter filter) {
		return findAll((root, query, builder) -> {
			return filter.toPredicate(Model.forRoot(root), builder);
		});
	}

	public List<E> findAll(Filter filter, Sort sort) {
		return findAll((root, query, builder) -> {
			return filter.toPredicate(Model.forRoot(root), builder);
		}, sort);
	}

	public Page<E> findAll(Filter filter, Pageable pageable) {
		return findAll((root, query, builder) -> {
			return filter.toPredicate(Model.forRoot(root), builder);
		}, pageable);
	}

	public Optional<E> findOne(Filter filter) {
		return findOne((root, query, builder) -> {
			return filter.toPredicate(Model.forRoot(root), builder);
		});
	}

	public <T extends Number> T sum(String attributeName, Filter filter, Class<T> requiredType) {
		Property<T> property = Property.forName(attributeName, requiredType);
		return select().filter(filter).select(Fields.sum(property)).getResult(requiredType);
	}

	public <T extends Number> T avg(String attributeName, Filter filter, Class<T> requiredType) {
		Property<T> property = Property.forName(attributeName, requiredType);
		return select().filter(filter).select(Fields.avg(property)).getResult(requiredType);
	}

	public <T extends Comparable<T>> T min(String attributeName, Filter filter, Class<T> requiredType) {
		Property<T> property = Property.forName(attributeName, requiredType);
		return select().filter(filter).select(Fields.min(property)).getResult(requiredType);
	}

	public <T extends Comparable<T>> T max(String attributeName, Filter filter, Class<T> requiredType) {
		Property<T> property = Property.forName(attributeName, requiredType);
		return select().filter(filter).select(Fields.max(property)).getResult(requiredType);
	}

	public JpaDelete<E> delete() {
		return delete(entityClass);
	}

	public JpaUpdate<E> update() {
		return update(entityClass);
	}

	public JpaQuery<E> select() {
		return select(entityClass);
	}

	public Class<E> getEntityClass() {
		return entityClass;
	}

}
