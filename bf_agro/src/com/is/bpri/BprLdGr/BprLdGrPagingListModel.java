package com.is.bpri.BprLdGr;


import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;


@SuppressWarnings("serial")
public class BprLdGrPagingListModel extends AbstractPagingListModel<BprLdGr> implements BindingListModel {

    public BprLdGrPagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
    	super(startPageNumber, pageSize,fl,  alias);
    }

    @Override
    protected List<BprLdGr> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
    	BprLdGrFilter fc;
    	if(fl !=null){
    		fc = (BprLdGrFilter)fl;
    	}else{
    		fc = new BprLdGrFilter();
    	}
    	return BprLdGrService.getLdGrsFl(itemStartNumber, pageSize,fc,alias);
    }

    @Override
    public int getTotalSize(Object fl, String alias)  {
    	BprLdGrFilter fc;
    	if(fl !=null){
    		fc = (BprLdGrFilter)fl;
    	}else{
    		fc = new BprLdGrFilter();
    	}
    	return BprLdGrService.getCount(fc,alias);
    }

    @Override
    public int indexOf(Object obj) {
        return 0;
    }

    @Override
    protected List<BprLdGr> getPageData(int itemStartNumber, int pageSize) {
    	return null;
    }
    
	@Override
	public int getTotalSize() {
		return 0;
	}
}
