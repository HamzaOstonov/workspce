package com.is.bpri.bpr_change_limit;
import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<BprChangeLimit> implements BindingListModel{

	public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch){
		super(startPageNumber, pageSize,fl,branch);
	}
  
	@Override
	protected List<BprChangeLimit> getPageData(int itemStartNumber, int pageSize, Object fl, String branch){
		BprChangeLimitFilter fc;
		if(fl !=null){
			fc = (BprChangeLimitFilter)fl;
		} else {
			fc = new BprChangeLimitFilter();
		}
		return BprChangeLimitService.getbpr_change_limitsFl(itemStartNumber, pageSize,fc);
	}

	@Override
  	public int getTotalSize(Object fl, String branch){
		BprChangeLimitFilter fc;
		if(fl !=null){
			fc = (BprChangeLimitFilter)fl;
		} else {
			fc = new BprChangeLimitFilter();
		}
		return BprChangeLimitService.getCount(fc);
  	}

	@Override
	public int indexOf(Object obj){
		return 0;
	}
  
	@Override
	protected List<BprChangeLimit> getPageData(int itemStartNumber, int pageSize){
		return null;
	}

	@Override
	public int getTotalSize(){
		return 0;
	}
}
