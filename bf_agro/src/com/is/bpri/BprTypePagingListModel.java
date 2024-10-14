package com.is.bpri;
import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class BprTypePagingListModel extends AbstractPagingListModel<BprType> implements BindingListModel{

	public BprTypePagingListModel(int startPageNumber, int pageSize, Object fl,String branch){
		super(startPageNumber, pageSize,fl,branch);
	}
  
	@Override
	protected List<BprType> getPageData(int itemStartNumber, int pageSize, Object fl, String alias){
		BprTypeFilter fc;
		if(fl !=null){
			fc = (BprTypeFilter)fl;
		} else {
			fc = new BprTypeFilter();
		}
		return BprTypeService.getBprTypesFl(itemStartNumber, pageSize,fc, alias);
	}

	@Override
	public int getTotalSize(Object fl, String alias){
		BprTypeFilter fc;
		if(fl !=null){
			fc = (BprTypeFilter)fl;
		} else {
			fc = new BprTypeFilter();
		}
		return BprTypeService.getCount(fc, alias);
	}

	@Override
	public int indexOf(Object obj){
		return 0;
	}
  
	@Override
	protected List<BprType> getPageData(int itemStartNumber, int pageSize){
		return null;
	}

	@Override
	public int getTotalSize(){
		return 0;
	}
}