package com.is.payments.spr.s_kaznacc;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.is.payments.spr.AbstractPagingListModel;
import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zul.ListModelExt;


public class PagingListModel extends AbstractPagingListModel<Kaznacc> implements BindingListModel, ListModelExt {
	private static final long serialVersionUID = 67776232344831719L;
	private List<Kaznacc> _items;
	
    public PagingListModel(int startPageNumber, int pageSize, Object fl) {
    	super(startPageNumber, pageSize,fl);
    }
    
    @Override
	protected List<Kaznacc> getPageData(int itemStartNumber, int pageSize, Object fl) {
    	KaznaccFilter fc;
	    if(fl !=null){
	        fc = (KaznaccFilter)fl;
	    }else{
	        fc = new KaznaccFilter();
	    }
	    _items = KaznaccService.getKaznaccsFl(itemStartNumber, pageSize,fc);
	    return _items;
	}

	@Override
	public int getTotalSize(Object fl)  {
		KaznaccFilter fc;
	    if(fl !=null){
	        fc = (KaznaccFilter)fl;
	    }else{
	        fc = new KaznaccFilter();
	    }
	    return KaznaccService.getCount(fc);
	}
	
	@Override
	public int indexOf(Object obj) {
		return 0;
	}

    @Override
    protected List<Kaznacc> getPageData(int itemStartNumber, int pageSize) {
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
	
	private List<Kaznacc> getItems(){
		return _items;
	}
}


