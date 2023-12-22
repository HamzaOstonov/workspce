package com.is.creditapp;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<CreditApp> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    	super(startPageNumber, pageSize,fl,branch);
    }

    @Override
    protected List<CreditApp> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    	CreditAppFilter fc;
    	if(fl !=null){
    		fc = (CreditAppFilter)fl;
    	}else{
    		fc = new CreditAppFilter();
    	}
    	return CreditAppService.getni_reqsFl(itemStartNumber, pageSize,fc,branch);
    }

    @Override
    public int getTotalSize(Object fl, String branch)  {
    	CreditAppFilter fc;
    	if(fl !=null){
    		fc = (CreditAppFilter)fl;
    	}else{
    		fc = new CreditAppFilter();
    	}
    	return CreditAppService.getCount(fc,branch);
    }

    @Override
    public int indexOf(Object obj) {
        return 0;
    }
    
    @Override
    protected List<CreditApp> getPageData(int itemStartNumber, int pageSize) {
            return null;
    }

	@Override
	public int getTotalSize() {
		return 0;
	}
}
