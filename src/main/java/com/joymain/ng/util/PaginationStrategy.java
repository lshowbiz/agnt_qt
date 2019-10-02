package com.joymain.ng.util;

public class PaginationStrategy {
	private int startRecord;
	private int maxRecord;
	public int getStartRecord() {
		return startRecord;
	}
	
	
	public int getMaxRecord() {
		return maxRecord;
	}


	


	public void setStrategy(int currentPage,int pageSize) {
		this.startRecord = (currentPage - 1) * pageSize;
		maxRecord = pageSize;
	}
	
}
