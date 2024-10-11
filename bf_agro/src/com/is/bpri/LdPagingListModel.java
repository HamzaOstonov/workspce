package com.is.bpri;

import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")

public class LdPagingListModel extends AbstractPagingListModel<LdProduct> implements BindingListModel {


	public LdPagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
      super(startPageNumber, pageSize,fl , alias);
    }
@Override
protected List<LdProduct> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
    LdProductFilter fc;
    if(fl !=null){
        fc = (LdProductFilter)fl;
}else{
        fc = new LdProductFilter();
}
    return LdProductService.getLdProductsFl(itemStartNumber, pageSize,fc, alias);
}

@Override
public int getTotalSize(Object fl, String alias)  {
    LdProductFilter fc;
    if(fl !=null){
        fc = (LdProductFilter)fl;
}else{
        fc = new LdProductFilter();
}
    return LdProductService.getCount(fc, alias);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<LdProduct> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}


}
