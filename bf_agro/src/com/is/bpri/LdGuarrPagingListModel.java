package com.is.bpri;
import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class LdGuarrPagingListModel extends AbstractPagingListModel<LdGuarr> implements BindingListModel{

	public LdGuarrPagingListModel(int startPageNumber, int pageSize, Object fl,String alias) {
		super(startPageNumber, pageSize,fl,alias);
	}
  
	@Override
	protected List<LdGuarr> getPageData(int itemStartNumber, int pageSize, Object fl, String aslias){
		LdGuarrFilter fc;
		if(fl !=null){
			fc = (LdGuarrFilter)fl;
		} else {
			fc = new LdGuarrFilter();
		}
		return LdGuarrService.getLdGuarrsFl(itemStartNumber, pageSize,fc);
	}

	@Override
	public int getTotalSize(Object fl, String branch){
		LdGuarrFilter fc;
		if(fl !=null){
			fc = (LdGuarrFilter)fl;
		} else {
			fc = new LdGuarrFilter();
		}
		return LdGuarrService.getCount(fc);
	}

	@Override
	public int indexOf(Object obj){
		return 0;
	}
  
	@Override
	protected List<LdGuarr> getPageData(int itemStartNumber, int pageSize){
		return null;
	}
  
	@Override
	public int getTotalSize(){
		return 0;
	}
}
