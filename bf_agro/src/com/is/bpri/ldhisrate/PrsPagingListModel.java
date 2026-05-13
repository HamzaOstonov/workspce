package com.is.bpri.ldhisrate;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PrsPagingListModel extends AbstractPagingListModel<Params> implements BindingListModel{

	public PrsPagingListModel(int startPageNumber, int pageSize, Object fl, String alias){
		super(startPageNumber, pageSize, fl, alias);
	}
  
	@Override
	protected List<Params> getPageData(int itemStartNumber, int pageSize, Object fl, String alias){
		ParamsFilter fc;
		if(fl !=null){
			fc = (ParamsFilter)fl;
		} else {
			fc = new ParamsFilter();
		}
		return LdHisRateService.getLdHisRatesFlPrs(itemStartNumber, pageSize,fc, alias);
	}

	@Override
  	public int getTotalSize(Object fl, String alias){
		ParamsFilter fc;
		if(fl !=null){
			fc = (ParamsFilter)fl;
		} else {
			fc = new ParamsFilter();
		}
		return LdHisRateService.getCountPrs(fc, alias);
	}

	@Override
	public int indexOf(Object obj){
		return 0;
	}
  
	@Override
	protected List<Params> getPageData(int itemStartNumber, int pageSize){
		return null;
	}
  
	@Override
	public int getTotalSize() {
		return 0;
	}
}




