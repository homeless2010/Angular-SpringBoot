package com.piedpiper.platform.core.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public class Paging<T> implements Serializable {
	private static final long serialVersionUID = 8719891527471634180L;
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	protected int pageNo = 1;
	protected int pageSize = -1;

	protected String orderBy;

	protected String order;
	protected boolean autoCount = true;

	protected int first;

	protected boolean orderBySetted;

	protected long totalPages;

	protected boolean hasPre;
	protected boolean hasNext;
	protected int nextPage;
	protected int prePage;
	protected List<T> result = new ArrayList();
	protected long totalCount = -1L;

	public Paging() {
	}

	public Paging(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public Paging<T> pageNo(int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Paging<T> pageSize(int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	public int getFirst() {
		return (this.pageNo - 1) * this.pageSize + 1;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Paging<T> orderBy(String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		if ((order != null) && (!order.equals(""))) {
			String lowcaseOrder = StringUtils.lowerCase(order);

			String[] orders = StringUtils.split(lowcaseOrder, ',');
			for (String orderStr : orders) {
				if ((!StringUtils.equals("desc", orderStr)) && (!StringUtils.equals("asc", orderStr))) {
					throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
				}
			}
			this.order = lowcaseOrder;
		} else {
			this.order = order;
		}
	}

	public Paging<T> order(String theOrder) {
		setOrder(theOrder);
		return this;
	}

	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(this.orderBy)) && (StringUtils.isNotBlank(this.order));
	}

	public void setOrderBySetted(boolean orderBySetted) {
		this.orderBySetted = orderBySetted;
	}

	public boolean isAutoCount() {
		return this.autoCount;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}

	public Paging<T> autoCount(boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	public List<T> getResult() {
		return this.result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public long getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getTotalPages() {
		if (this.totalCount < 0L) {
			return -1L;
		}

		long count = this.totalCount / this.pageSize;
		if (this.totalCount % this.pageSize > 0L) {
			count += 1L;
		}
		return count;
	}

	public void setTotalPages(long l) {
	}

	public boolean isHasNext() {
		return this.pageNo + 1 <= getTotalPages();
	}

	public void setHasNext(boolean b) {
	}

	public int getNextPage() {
		if (isHasNext()) {
			return this.pageNo + 1;
		}
		return this.pageNo;
	}

	public void setNextPage(int i) {
	}

	public boolean isHasPre() {
		return this.pageNo - 1 >= 1;
	}

	public void setHasPre(boolean b) {
	}

	public int getPrePage() {
		if (isHasPre()) {
			return this.pageNo - 1;
		}
		return this.pageNo;
	}

	public void setPrePage(int i) {
	}

	public static <T> Paging<T> fromRequest(HttpServletRequest request, Map<String, Object> parameter) {
		String pageStr = request.getParameter("page");
		String rowsStr = request.getParameter("rows");

		int pageNo = Integer.parseInt(pageStr);
		int pageSize = Integer.parseInt(rowsStr);

		Paging<T> page = new Paging();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setAutoCount(true);

		Enumeration names = request.getParameterNames();

		while (names.hasMoreElements()) {

			String key = (String) names.nextElement();

			if ((!key.equals("page")) && (!key.equals("rows"))) {
				parameter.put(key, request.getParameter(key));
			}
		}

		return page;
	}
}
