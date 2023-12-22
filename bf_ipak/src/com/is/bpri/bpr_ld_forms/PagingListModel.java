package com.is.bpri.bpr_ld_forms;
import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<BprLdForms> implements BindingListModel{

	public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch){
		super(startPageNumber, pageSize,fl,branch);
	}
  
	@Override
	protected List<BprLdForms> getPageData(int itemStartNumber, int pageSize, Object fl, String branch){
		BprLdFormsFilter fc;
		if(fl !=null){
			fc = (BprLdFormsFilter)fl;
		} else {
			fc = new BprLdFormsFilter();
		}
		return BprLdFormsService.getBprLdFormssFl(itemStartNumber, pageSize,fc, branch);
	}

	@Override
	public int getTotalSize(Object fl, String branch){
		BprLdFormsFilter fc;
		if(fl !=null){
			fc = (BprLdFormsFilter)fl;
		} else {
			fc = new BprLdFormsFilter();
		}
		return BprLdFormsService.getCount(fc, branch);
	}

	@Override
	public int indexOf(Object obj){
		return 0;
	}
  
	@Override
  	protected List<BprLdForms> getPageData(int itemStartNumber, int pageSize){
		return null;
	}

	@Override
	public int getTotalSize(){
		return 0;
	}
}
