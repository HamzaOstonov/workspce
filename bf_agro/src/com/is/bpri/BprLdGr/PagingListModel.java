package com.is.bpri.BprLdGr;


import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;


@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<BprGrTypeAdd> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
    	super(startPageNumber, pageSize,fl,  alias);
    }
    
    @Override
    protected List<BprGrTypeAdd> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
    	BprGrTypeFilterAdd fc;
    	if(fl !=null){
    		fc = (BprGrTypeFilterAdd)fl;
    	}else{
    		fc = new BprGrTypeFilterAdd();
    	}
    	return BprLdGrService.getLdGrTypeFlAdd(itemStartNumber, pageSize,fc,alias);
    }

    @Override
    public int getTotalSize(Object fl, String alias)  {
    	BprGrTypeFilterAdd fc;
    	if(fl !=null){
    		fc = (BprGrTypeFilterAdd)fl;
    	}else{
    		fc = new BprGrTypeFilterAdd();
    	}
    	return BprLdGrService.getCountGrTypeAdd(fc,alias);
    }

    @Override
    public int indexOf(Object obj) {
        return 0;
    }
    
    @Override
    protected List<BprGrTypeAdd> getPageData(int itemStartNumber, int pageSize) {
    	return null;
    }
    
	@Override
	public int getTotalSize() {
		return 0;
	}
}
