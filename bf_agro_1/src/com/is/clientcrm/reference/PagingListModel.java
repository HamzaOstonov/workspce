package com.is.clientcrm.reference;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zul.ListModelExt;

import com.is.utils.AbstractPagingListModel;

//import com.is.payments.spr.s_kaznacc.Kaznacc;
//import com.is.payments.spr.s_kaznacc.KaznaccFilter;
//import com.is.payments.spr.s_kaznacc.KaznaccService;

public class PagingListModel extends AbstractPagingListModel<Reference> implements BindingListModel, ListModelExt {
	private static final long serialVersionUID = 67776232344831719L;
	private List<Reference> _items;
	
    public PagingListModel(int startPageNumber, int pageSize, Object fl, String branch) {
    	super(startPageNumber, pageSize, fl, branch);
    }
    
    @Override
	protected List<Reference> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
    	Reference fc;
	    if(fl !=null ){
	        fc = (Reference)fl;
	    }else{
	        fc = new Reference("", "");
	    }
	    //_items = SoogunService.getSoogunFl(itemStartNumber, pageSize, fc);
	    return _items;
	}

	@Override
	public int getTotalSize(Object fl, String alias)  {
		//KaznaccFilter fc;
	    //if(fl !=null){
	    //    fc = (KaznaccFilter)fl;
	    //}else{
	    //    fc = new KaznaccFilter();
	    //}
	    //return KaznaccService.getCount(fc);
		return 0;
	}
	
	@Override
	public int indexOf(Object obj) {
		return 0;
	}

    @Override
    protected List<Reference> getPageData(int itemStartNumber, int pageSize) {
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
	
	private List<Reference> getItems(){
		return _items;
	}
}


