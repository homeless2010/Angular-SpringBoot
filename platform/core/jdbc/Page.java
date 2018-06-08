package com.piedpiper.platform.core.jdbc;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Page<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private int page;
	private int rows;
	private int firstEntityIndex;
	private int lastEntityIndex;
	private Collection<T> entities;
	private long entityCount;
	private long pageCount;

	public Page() {
	}

	public Page(int page, int rows) {
		if ((rows > 1) && (page <= 0)) {
			throw new IllegalArgumentException(
					"Illegal paging arguments. [pageSize=" + page + ", pageIndex=" + rows + "]");
		}

		if (page < 0)
			page = 0;
		if (rows < 1) {
			rows = 1;
		}
		this.page = page;
		this.rows = rows;
		this.firstEntityIndex = ((rows - 1) * page);
		this.lastEntityIndex = (rows * page);
	}

	private void calculate() {
		this.firstEntityIndex = ((this.page - 1) * this.rows);
		this.lastEntityIndex = (this.rows * this.page);
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return this.rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getFirstEntityIndex() {
		calculate();
		return this.firstEntityIndex;
	}

	public long getLastEntityIndex() {
		calculate();
		return this.lastEntityIndex;
	}

	public void setEntities(Collection<T> entities) {
		this.entities = entities;
	}

	public Collection<T> getEntities() {
		return this.entities != null ? this.entities : Collections.EMPTY_LIST;
	}

	public long getEntityCount() {
		return this.entityCount;
	}

	public void setEntityCount(long entityCount) {
		if (entityCount < 0L) {
			throw new IllegalArgumentException("Illegal entityCount arguments. [entityCount=" + entityCount + "]");
		}

		this.entityCount = entityCount;
		this.pageCount = ((entityCount - 1L) / this.rows + 1L);
	}

	public long getPageCount() {
		return this.pageCount;
	}

	public Iterator<T> iterator() {
		if (this.entities != null) {
			return this.entities.iterator();
		}
		return null;
	}
}
