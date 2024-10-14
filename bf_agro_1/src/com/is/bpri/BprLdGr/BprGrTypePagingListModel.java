package com.is.bpri.BprLdGr;


import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;


@SuppressWarnings("serial")
public class BprGrTypePagingListModel extends AbstractPagingListModel<BprGrType> implements BindingListModel {

    public BprGrTypePagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
    	super(startPageNumber, pageSize,fl,  alias);
    }

    @Override
    protected List<BprGrType> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
    	BprGrTypeFilter fc;
    	if(fl !=null){
    		fc = (BprGrTypeFilter)fl;
    	}else{
    		fc = new BprGrTypeFilter();
    	}
    	return BprLdGrService.getLdGrTypeFl(itemStartNumber, pageSize,fc,alias);
    }

    @Override
    public int getTotalSize(Object fl, String alias)  {
    	BprGrTypeFilter fc;
    	if(fl !=null){
        fc = (BprGrTypeFilter)fl;
    	}else{
    		fc = new BprGrTypeFilter();
    	}
    	return BprLdGrService.getCountGrType(fc,alias);
    }

    @Override
    public int indexOf(Object obj) {
    	return 0;
    }
    
    @Override
    protected List<BprGrType> getPageData(int itemStartNumber, int pageSize) {
            return null;
    }
	@Override
	public int getTotalSize() {
		return 0;
	}
}
