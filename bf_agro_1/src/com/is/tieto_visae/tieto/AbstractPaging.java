package com.is.tieto_visae.tieto;


import java.util.List;

public abstract class AbstractPaging<T> {

	private int pageSize;	
	
	public AbstractPaging(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public abstract int getTotalSize(Object filter);
	
	public List<T> getPageData(int currentPageNumber, Object filter) {
		
		int startItemNumber = currentPageNumber * pageSize + 1;
		int lastItemNumber = startItemNumber + pageSize;
		
		return getOnePageData(startItemNumber, lastItemNumber, filter);
	}
	
	protected abstract List<T> getOnePageData(int startItemNumber, int lastItemNumber, Object filter);
}
