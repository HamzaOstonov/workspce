package com.is.bpri;
import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class NiReqPagingListModel extends AbstractPagingListModel<NiReq> implements BindingListModel{

	public NiReqPagingListModel(int startPageNumber, int pageSize, Object fl, String alias){
		super(startPageNumber, pageSize,fl,alias);
	}
  
	@Override
	protected List<NiReq> getPageData(int itemStartNumber, int pageSize, Object fl, String alias){
		NiReqFilter fc;
		if(fl !=null){
			fc = (NiReqFilter)fl;
		} else {
			fc = new NiReqFilter();
		}
		return NiReqService.getNiReqsFl(itemStartNumber, pageSize,fc, alias);
	}

	@Override
	public int getTotalSize(Object fl, String alias){
		NiReqFilter fc;
		if(fl !=null){
			fc = (NiReqFilter)fl;
		} else {
			fc = new NiReqFilter();
		}
		return NiReqService.getCount(fc, alias);
	}

	@Override
	public int indexOf(Object obj){
		return 0;
	}
  
	@Override
	protected List<NiReq> getPageData(int itemStartNumber, int pageSize){
		return null;
	}
  
	@Override
	public int getTotalSize() {
		return 0;
	}
  
}

