package com.piedpiper.platform.core.dao;

import com.piedpiper.platform.commons.utils.PojoUtil;
import com.piedpiper.platform.core.dao.datasource.DataSourceUtil;
import com.piedpiper.platform.core.exception.DaoException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;

public class PagingJDBC {
	public static final int NUMBERS_PER_PAGE = 10;
	private int numPerPage;
	private int totalRows;
	private int totalPages;
	private int currentPage;
	private int startIndex;
	private int lastIndex;
	private Collection<Object> resultList = new ArrayList();

	public PagingJDBC() {
	}

	public PagingJDBC(String sql, JdbcTemplate jdbcTemplate) throws Exception {
		if (jdbcTemplate == null)
			throw new IllegalArgumentException("jTemplate is null,please initial it first. ");
		if (sql.equals("")) {
			throw new IllegalArgumentException("sql is empty,please initial it first. ");
		}
		new PagingJDBC(sql, this.currentPage, 10, jdbcTemplate);
	}

	public PagingJDBC(String sql, int pageNow, int lineNumber, JdbcTemplate jdbcTemplate) throws DaoException {
		if (jdbcTemplate == null)
			throw new IllegalArgumentException("jdbcTemplate is null,please initial it first. ");
		if ((sql == null) || (sql.equals(""))) {
			throw new IllegalArgumentException("sql is empty,please initial it first. ");
		}
		setNumPerPage(pageNow);
		setCurrentPage(lineNumber);

		String countSql = "";
		String supply = DataSourceUtil.getInstance().getDataBaseType(jdbcTemplate);
		if ((supply.equals("sqlserver")) && (sql.toString().toUpperCase().indexOf("ORDER BY") != -1)) {
			countSql = "select count(*) as c from ("
					+ sql.toString().substring(0, sql.toString().toUpperCase().indexOf("ORDER BY")).trim() + ") t";
		} else {
			countSql = "select count(*) as c from (" + sql.toString() + ") t";
		}
		int lineCount = jdbcTemplate.queryForInt(countSql);
		setTotalRows(lineCount);
		setTotalPages();
		setStartIndex();
		setLastIndex();
		int start = (pageNow - 1) * lineNumber + 1;

		String statement = ViewSQLFactory.getViewSQL(jdbcTemplate, sql.toString(), start, lineNumber, lineCount);

		setResultList(jdbcTemplate.queryForList(statement));
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNumPerPage() {
		return this.numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getTotalPages() {
		return this.totalPages;
	}

	public void setTotalPages() {
		if (this.totalRows % this.numPerPage == 0) {
			this.totalPages = (this.totalRows / this.numPerPage);
		} else {
			this.totalPages = (this.totalRows / this.numPerPage + 1);
		}
	}

	public int getTotalRows() {
		return this.totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getStartIndex() {
		return this.startIndex;
	}

	public void setStartIndex() {
		this.startIndex = ((this.currentPage - 1) * this.numPerPage);
	}

	public int getLastIndex() {
		return this.lastIndex;
	}

	public void setLastIndex() {
		if (this.totalRows < this.numPerPage) {
			this.lastIndex = this.totalRows;
		} else if ((this.totalRows % this.numPerPage == 0)
				|| ((this.totalRows % this.numPerPage != 0) && (this.currentPage < this.totalPages))) {
			this.lastIndex = (this.currentPage * this.numPerPage);
		} else if ((this.totalRows % this.numPerPage != 0) && (this.currentPage == this.totalPages)) {
			this.lastIndex = this.totalRows;
		}
	}

	public Collection getResultList() {
		return this.resultList;
	}

	public void setResultList(List<Map<String, Object>> records) throws DaoException {
		for (Map<String, Object> map : records) {
			Map<String, Object> record = new HashMap();
			PojoUtil.Map2Pojo(map, record);
			this.resultList.add(record);
		}
	}
}
