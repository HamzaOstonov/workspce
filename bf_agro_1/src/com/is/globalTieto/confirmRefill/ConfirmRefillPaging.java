package com.is.globalTieto.confirmRefill;

import com.is.globalTieto.utils.AbstractPaging;

import java.util.List;

public class ConfirmRefillPaging extends AbstractPaging<ConfirmRefill>{

	public ConfirmRefillPaging(int pageSize) {
		super(pageSize);
	}

	@Override
	public int getTotalSize(Object filter) {
		ConfirmRefillFilter confirmRefillFilter = null;
		
		if(filter != null){
			confirmRefillFilter = (ConfirmRefillFilter)filter;
		}else{
			confirmRefillFilter = new ConfirmRefillFilter();
		}
		
		return ConfirmRefillService.getTotalSize(confirmRefillFilter);
	}

	@Override
	protected List<ConfirmRefill> getOnePageData(int startItemNumber, int lastItemNumber, Object filter) {
		ConfirmRefillFilter confirmRefillFilter = null;
		
		if(filter != null){
			confirmRefillFilter = (ConfirmRefillFilter)filter;
		}else{
			confirmRefillFilter = new ConfirmRefillFilter();
		}
		
		return ConfirmRefillService.getOnePageData(startItemNumber, lastItemNumber, confirmRefillFilter);
	}
	
}
