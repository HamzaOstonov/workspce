package com.is.bpri.bpr_ld_account;
import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<BprLdAccount> implements BindingListModel{

	public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch){
		super(startPageNumber, pageSize,fl,branch);
	}
  
	@Override
	protected List<BprLdAccount> getPageData(int itemStartNumber, int pageSize, Object fl, String branch){
		BprLdAccountFilter fc;
		if(fl !=null){
			fc = (BprLdAccountFilter)fl;
		} else {
			fc = new BprLdAccountFilter();
		}
		return BprLdAccountService.getBprLdAccountsFl(itemStartNumber, pageSize,fc, branch);
	}

	@Override
	public int getTotalSize(Object fl, String branch){
		BprLdAccountFilter fc;
		if(fl !=null){
			fc = (BprLdAccountFilter)fl;
		} else {
			fc = new BprLdAccountFilter();
		}
		return BprLdAccountService.getCount(fc, branch);
	}

	@Override
	public int indexOf(Object obj){
		return 0;
	}
  
	@Override
	protected List<BprLdAccount> getPageData(int itemStartNumber, int pageSize){
		return null;
	}

	@Override
	public int getTotalSize(){
		return 0;
	}
}
