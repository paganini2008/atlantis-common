package indi.atlantis.framework.fastjpa;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * ColumnList
 *
 * @author Jimmy Hoff
 * @version 1.0
 */
public class ColumnList extends ArrayList<Column> {

	private static final long serialVersionUID = 1L;

	public ColumnList() {
	}

	public ColumnList(Column... columns) {
		addAll(Arrays.asList(columns));
	}

	public ColumnList addColumn(String attributeName) {
		add(Column.forName(attributeName));
		return this;
	}

	public ColumnList addColumns(String[] attributeNames) {
		if (attributeNames != null) {
			for (String attributeName : attributeNames) {
				add(Column.forName(attributeName));
			}
		}
		return this;
	}

	public ColumnList addColumn(String alias, String attributeName) {
		add(Column.forName(alias, attributeName));
		return this;
	}

	public ColumnList addColumns(String alias, String[] attributeNames) {
		if (attributeNames != null) {
			for (String attributeName : attributeNames) {
				add(Column.forName(alias, attributeName));
			}
		}
		return this;
	}

	public ColumnList addColumn(String attributeName, Class<?> requiredType) {
		add(Column.forName(attributeName, requiredType));
		return this;
	}

	public ColumnList addColumn(String alias, String attributeName, Class<?> requiredType) {
		add(Column.forName(alias, attributeName, requiredType));
		return this;
	}

	public ColumnList addColumns(Field<?>... fields) {
		if (fields != null) {
			for (Field<?> field : fields) {
				addColumn(field, field.toString());
			}
		}
		return this;
	}

	public ColumnList addColumn(Field<?> field, String alias) {
		add(field.as(alias));
		return this;
	}

}
