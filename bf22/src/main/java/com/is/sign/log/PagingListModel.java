package com.is.sign.log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zul.ListModelExt;

public class PagingListModel extends AbstractPagingListModel<Signlog> implements BindingListModel, ListModelExt {
	private static final long serialVersionUID = 67776232344831720L;
	private List<Signlog> _items;
	
    public PagingListModel(int startPageNumber, int pageSize, Object fl) {
    	super(startPageNumber, pageSize,fl);
    }
    
    @Override
	protected List<Signlog> getPageData(int itemStartNumber, int pageSize, Object fl) {
	    SignlogFilter fc;
	    if(fl !=null){
	        fc = (SignlogFilter)fl;
	    }else{
	        fc = new SignlogFilter();
	    }
	    _items = SignlogService.getSignlogsFl(itemStartNumber, pageSize,fc);
	    return _items;
	}

	@Override
	public int getTotalSize(Object fl)  {
	    SignlogFilter fc;
	    if(fl !=null){
	        fc = (SignlogFilter)fl;
	    }else{
	        fc = new SignlogFilter();
	    }
	    return SignlogService.getCount(fc);
	}
	
	@Override
	public int indexOf(Object obj) {
		return 0;
	}

    @Override
    protected List<Signlog> getPageData(int itemStartNumber, int pageSize) {
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
	
	private List<Signlog> getItems(){
		return _items;
	}

	@Override
	public String getSortDirection(Comparator cmpr) {
		// TODO Auto-generated method stub
		return null;
	}
}


