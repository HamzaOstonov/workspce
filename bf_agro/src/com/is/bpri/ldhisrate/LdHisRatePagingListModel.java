package com.is.bpri.ldhisrate;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class LdHisRatePagingListModel extends AbstractPagingListModel<LdHisRate> implements BindingListModel{

	public LdHisRatePagingListModel(int startPageNumber, int pageSize, Object fl, String alias){
		super(startPageNumber, pageSize, fl, alias);
	}
  
	@Override
	protected List<LdHisRate> getPageData(int itemStartNumber, int pageSize, Object fl, String alias){
		LdHisRateFilter fc;
		if(fl !=null){
			fc = (LdHisRateFilter)fl;
		} else {
			fc = new LdHisRateFilter();
		}
		return LdHisRateService.getLdHisRatesFl(itemStartNumber, pageSize,fc, alias);
	}

	@Override
	public int getTotalSize(Object fl, String alias){
		LdHisRateFilter fc;
		if(fl !=null){
			fc = (LdHisRateFilter)fl;
		} else {
			fc = new LdHisRateFilter();
		}
		return LdHisRateService.getCount(fc, alias);
	}

	@Override
	public int indexOf(Object obj){
		return 0;
	}
  
	@Override
	protected List<LdHisRate> getPageData(int itemStartNumber, int pageSize){
		return null;
	}
  
	@Override
	public int getTotalSize() {
		return 0;
	}
}




