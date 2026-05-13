package com.is.bpri.bpr_authorized_acc;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<Bpr_authirizedacc> implements BindingListModel{

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
		Bpr_authirizedaccFilter fc;
	     if(fl !=null){
	    	 fc = (Bpr_authirizedaccFilter)fl;
	     }else{
	    	 fc = new Bpr_authirizedaccFilter();
	     }
	     return Bpr_authirizedaccService.getCount(fc);
	}

	@Override
	protected List<Bpr_authirizedacc> getPageData(int itemStartNumber, int pageSize) {
		return null;
	}

	@Override
	protected List<Bpr_authirizedacc> getPageData(int itemStartNumber,int pageSize, Object fl, String alias) {
		Bpr_authirizedaccFilter fc;
        if(fl !=null){
            fc = (Bpr_authirizedaccFilter)fl;
        }else{
            fc = new Bpr_authirizedaccFilter();
        }
        return Bpr_authirizedaccService.getbpr_specialfrmsFl(itemStartNumber, pageSize,fc);
	}
	
}
