package com.is.bpri.bprspecialfrm;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<Bpr_specialfrm> implements BindingListModel{



	public PagingListModel(int startPageNumber, int pageSize, Object fl,String alias) {
		super(startPageNumber, pageSize, fl, alias);
	}

	@Override
	public int indexOf(Object obj) {
		return 0;
	}

	@Override
	public int getTotalSize() {
		return 0;
	}

	@Override
	public int getTotalSize(Object fl, String alias) {
		 Bpr_specialfrmFilter fc;
	     if(fl !=null){
	    	 fc = (Bpr_specialfrmFilter)fl;
	     }else{
	    	 fc = new Bpr_specialfrmFilter();
	     }
	     return Bpr_specialfrmService.getCount(fc);
	}

	@Override
	protected List<Bpr_specialfrm> getPageData(int itemStartNumber, int pageSize) {
		return null;
	}

	@Override
	protected List<Bpr_specialfrm> getPageData(int itemStartNumber,int pageSize, Object fl, String alias) {
		Bpr_specialfrmFilter fc;
        if(fl !=null){
            fc = (Bpr_specialfrmFilter)fl;
        }else{
            fc = new Bpr_specialfrmFilter();
        }
        return Bpr_specialfrmService.getbpr_specialfrmsFl(itemStartNumber, pageSize,fc);
	}
	
}
