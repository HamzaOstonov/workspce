package com.is.bpri.ldhisrate;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class LdHisRateAddPagingListModel extends AbstractPagingListModel<LdHisRateAdd> implements BindingListModel{
	
	public LdHisRateAddPagingListModel(int startPageNumber, int pageSize, Object fl, String alias){
		super(startPageNumber, pageSize, fl, alias);
	}
	
	@Override
	protected List<LdHisRateAdd> getPageData(int itemStartNumber, int pageSize, Object fl, String alias){
		LdHisRateAddFilter fc;
		if (fl != null){
			fc = (LdHisRateAddFilter) fl;
		} else {
			fc = new LdHisRateAddFilter();
		}
		return LdHisRateService.getLdHisRatesFlAddRate(itemStartNumber, pageSize, fc, alias);
	}
	
	@Override
	public int getTotalSize(Object fl, String alias){
		LdHisRateAddFilter fc;
		if (fl != null){
			fc = (LdHisRateAddFilter) fl;
		} else {
			fc = new LdHisRateAddFilter();
		}
		return LdHisRateService.getCountAddRate(fc, alias);
	}
	
	@Override
	public int indexOf(Object obj){
		return 0;
	}
	
	@Override
	protected List<LdHisRateAdd> getPageData(int itemStartNumber, int pageSize){
		return null;
	}
	
	@Override
	public int getTotalSize(){
		return 0;
	}
}
