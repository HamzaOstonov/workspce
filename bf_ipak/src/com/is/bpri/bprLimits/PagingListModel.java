package com.is.bpri.bprLimits;
import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<bprLimits> implements BindingListModel{

	public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch){
		super(startPageNumber, pageSize,fl,branch);
	}
  
	@Override
	protected List<bprLimits> getPageData(int itemStartNumber, int pageSize, Object fl, String branch){
		bprLimitsFilter fc;
		if(fl !=null){
			fc = (bprLimitsFilter)fl;
		} else {
			fc = new bprLimitsFilter();
		}
		return bprLimitsService.getbprLimitssFl(itemStartNumber, pageSize,fc);
	}

	@Override
	public int getTotalSize(Object fl, String branch){
		bprLimitsFilter fc;
		if(fl !=null){
			fc = (bprLimitsFilter)fl;
		} else {
			fc = new bprLimitsFilter();
		}
		return bprLimitsService.getCount(fc);
	}

	@Override
	public int indexOf(Object obj){
		return 0;
	}
  
	@Override
	protected List<bprLimits> getPageData(int itemStartNumber, int pageSize){
		return null;
	}

	@Override
	public int getTotalSize(){
		return 0;
	}
}
