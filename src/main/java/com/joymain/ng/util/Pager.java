package com.joymain.ng.util;

import java.util.ArrayList;
import java.util.List;

public class Pager<T> {
	private int currentPage;
	private int totalObjects;
	private int pageSize;

	private int totalPages;
	private List<T> pageList;

	private int firstResult;
	private int endResult;

	private PaginationStrategy paginationStrategy;
	List<Integer> pageNumberList;

	public List<Integer> getPageNumberList() {
		return pageNumberList;
	}

	/**
	 * @param totalObjects
	 *            总数据数
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            页面大小 注: 默认分页逻辑 startPoint & endPoint 是oracle实现</br>
	 *            如果需要使用到其他数据库请实现PaginationStrategy接口</br> 使用该类的
	 *            setPaginationStrategy 方法获得相应的实现</br>
	 */
	public Pager(int totalObjects, int currentPage) {
		this.pageSize = 10;
		this.totalObjects = totalObjects;
		this.currentPage = currentPage;
		totalPages = totalObjects % pageSize == 0 ? totalObjects / pageSize
				: totalObjects / pageSize + 1;
		paginationStrategy = new PaginationStrategy();
		setDataPoint(paginationStrategy);
		setPageNumberList();
	}

	private void setPageNumberList() {
		this.pageNumberList = new ArrayList<Integer>();
		// 1 2 3 4 5 6 7
		int pageNumber = currentPage - 3;
		while (pageNumber <= 0) {
			pageNumber++;
		}
		int count = 0;
		while (pageNumber <= totalPages - 1 && count < 7) {
			count++;
			this.pageNumberList.add(pageNumber);
			pageNumber++;
		}
	}

	/**
	 * @param dataCount
	 *            总数据数
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            页面大小 注: 默认分页逻辑 startPoint & endPoint 是oracle实现</br>
	 *            如果需要使用到其他数据库请实现PaginationStrategy接口</br> 使用该类的
	 *            setPaginationStrategy 方法获得相应的实现</br>
	 */
	public Pager(int totalObjects, int currentPage, int pageSize) {
		if (pageSize <= 0)
			throw new IllegalArgumentException(
					" 单页数据设置 [pageSize]不能为小于等于 0  当前[pageSize]的值是 : "
							+ pageSize);
		this.pageSize = pageSize;
		this.totalObjects = totalObjects;
		this.currentPage = currentPage;
		totalPages = totalObjects % pageSize == 0 ? totalObjects / pageSize
				: totalObjects / pageSize + 1;
		paginationStrategy = new PaginationStrategy();
		setDataPoint(paginationStrategy);
		setPageNumberList();
	}

	private void setDataPoint(PaginationStrategy paginationStrategy) {
		paginationStrategy.setStrategy(currentPage, pageSize);
		this.firstResult = paginationStrategy.getStartRecord();
		this.endResult = currentPage * pageSize;
		if (this.endResult > totalObjects)
			this.endResult = totalObjects;

	}

	/**
	 * 默认的实现是 PaginationStrategyForOracle 如果需要实现其他数据库的分页逻辑，请继承
	 * PaginationStrategy，传入当前页面和页面大小设置不同数据库的分页
	 * 
	 * @param paginationStrategy
	 */
	public void setPaginationStrategy(PaginationStrategy paginationStrategy) {
		this.paginationStrategy = paginationStrategy;
		setDataPoint(paginationStrategy);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalObjects() {
		return totalObjects;
	}

	public void setTotalObjects(int totalObjects) {
		this.totalObjects = totalObjects;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getPageList() {
		return pageList;
	}

	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getEndResult() {
		return endResult;
	}

	public void setEndResult(int endResult) {
		this.endResult = endResult;
	}

	public PaginationStrategy getPaginationStrategy() {
		return paginationStrategy;
	}

	public void setPageNumberList(List<Integer> pageNumberList) {
		this.pageNumberList = pageNumberList;
	}

	@Override
	public String toString() {
		StringBuilder PagerPrintBuilder = new StringBuilder();
		PagerPrintBuilder.append("  当前页码 ").append(currentPage)
				.append(" 总数据数 ").append(totalObjects);
		PagerPrintBuilder.append("  起始点 ").append(firstResult).append(" 结束点 ")
				.append(endResult);
		PagerPrintBuilder.append("  总页数 ").append(totalPages).append(" 单页数据数 ")
				.append(pageSize);
		return PagerPrintBuilder.toString();
	}
}
