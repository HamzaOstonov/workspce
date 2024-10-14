package com.is.bpri.bproductDesc;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<bproduct_desc> implements BindingListModel{

	public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch){
		super(startPageNumber, pageSize,fl,branch);
	}
  
	@Override
	protected List<bproduct_desc> getPageData(int itemStartNumber, int pageSize, Object fl, String branch){
		bproduct_descFilter fc;
		if(fl !=null){
			fc = (bproduct_descFilter)fl;
		} else {
			fc = new bproduct_descFilter();
		}
		return bproduct_descService.getbproduct_descsFl(itemStartNumber, pageSize,fc);
	}

	@Override
	public int getTotalSize(Object fl, String branch){
		bproduct_descFilter fc;
		if(fl !=null){
			fc = (bproduct_descFilter)fl;
		} else {
			fc = new bproduct_descFilter();
		}
		return bproduct_descService.getCount(fc);
	}

	@Override
	public int indexOf(Object obj){
		return 0;
	}
  
	@Override
	protected List<bproduct_desc> getPageData(int itemStartNumber, int pageSize){
		return null;
	}

	@Override
	public int getTotalSize(){
		return 0;
	}
}
