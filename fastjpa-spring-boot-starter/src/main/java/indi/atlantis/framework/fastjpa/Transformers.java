package indi.atlantis.framework.fastjpa;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.github.paganini2008.devtools.collection.Tuple;

import indi.atlantis.framework.fastjpa.support.BeanTransformer;

/**
 * 
 * Transformers
 *
 * @author Jimmy Hoff
 * @version 1.0
 */
public abstract class Transformers {

	public static <E> Transformer<E, Map<String, Object>> asMap() {
		return new MapTransformer<E>();
	}

	public static <E> Transformer<E, Tuple> asTuple() {
		return new TupleTransformer<E>();
	}

	public static <E> Transformer<E, List<Object>> asList() {
		return new ListTransformer<E>();
	}

	public static <E, T> Transformer<E, T> asBean(Class<T> resultClass, String... includedProperties) {
		return new BeanTransformer<E, T>(resultClass, includedProperties);
	}

	public static <E, T> Transformer<E, T> asBean(Class<T> resultClass, String[] includedProperties,
			TransformerPostHandler<T> postHandler) {
		return new BeanTransformer<E, T>(resultClass, includedProperties, postHandler);
	}

	/**
	 * 
	 * MapTransformer
	 *
	 * @author Jimmy Hoff
	 * 
	 * 
	 * @version 1.0
	 */
	public static class MapTransformer<E> extends SimpleTransformerSupport<E, Map<String, Object>> {

		MapTransformer() {
		}

		protected Map<String, Object> createObject(int columns) {
			return new LinkedHashMap<String, Object>(columns);
		}

		protected void setAsValue(String attributeName, Object attributeValue, Map<String, Object> data) {
			data.put(attributeName, attributeValue);
		}

	}

	/**
	 * 
	 * TupleTransformer
	 *
	 * @author Jimmy Hoff
	 * 
	 * 
	 * @version 1.0
	 */
	public static class TupleTransformer<E> extends SimpleTransformerSupport<E, Tuple> {

		TupleTransformer() {
		}

		protected Tuple createObject(int columns) {
			return Tuple.newTuple();
		}

		protected void setAsValue(String attributeName, Object attributeValue, Tuple data) {
			data.set(attributeName, attributeValue);
		}

	}

	/**
	 * 
	 * ListTransformer
	 *
	 * @author Jimmy Hoff
	 * 
	 * 
	 * @version 1.0
	 */
	public static class ListTransformer<E> extends SimpleTransformerSupport<E, List<Object>> {

		ListTransformer() {
		}

		protected List<Object> createObject(int columns) {
			return new ArrayList<Object>(columns);
		}

		protected void setAsValue(String attributeName, Object attributeValue, List<Object> data) {
			data.add(attributeValue);
		}

	}

}
