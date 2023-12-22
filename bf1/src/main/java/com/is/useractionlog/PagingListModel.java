package com.is.useractionlog;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zul.ListModelExt;

public class PagingListModel extends AbstractPagingListModel<UserActionLog> implements BindingListModel, ListModelExt  {
	private static final long serialVersionUID = 837445994831719L;
	private List<UserActionLog> _items;
	
    public PagingListModel(int startPageNumber, int pageSize, Object fl) {
    	super(startPageNumber, pageSize,fl);
    }
	
    @Override
	protected List<UserActionLog> getPageData(int itemStartNumber, int pageSize, Object fl) {
	    UserActionLogFilter fc;
	    if(fl !=null){
	        fc = (UserActionLogFilter)fl;
	    }else{
	        fc = new UserActionLogFilter();
	    }
	    return UserActionLogService.getUserActionLogsFl(itemStartNumber, pageSize,fc);
	}

	@Override
	public int getTotalSize(Object fl)  {
	    UserActionLogFilter fc;
	    if(fl !=null){
	        fc = (UserActionLogFilter)fl;
	    }else{
	        fc = new UserActionLogFilter();
	    }
	    return UserActionLogService.getCount(fc);
	}

	@Override
	public int indexOf(Object obj) {
		return 0;
	}

    @Override
    protected List<UserActionLog> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
    
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void sort(Comparator cmpr, boolean ascending) {
		// TODO Auto-generated method stub
		Collections.sort(getItems() , cmpr);
		fireEvent(org.zkoss.zul.event.ListDataEvent.CONTENTS_CHANGED, -1, -1);
	}
	
	private List<UserActionLog> getItems(){
		return _items;
	}

	@Override
	public String getSortDirection(Comparator cmpr) {
		// TODO Auto-generated method stub
		return null;
	}
}


