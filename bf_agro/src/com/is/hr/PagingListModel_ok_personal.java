package com.is.hr;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel_ok_personal extends AbstractPagingListModel<Ok_personal> implements BindingListModel {

    public PagingListModel_ok_personal(int startPageNumber, int pageSize, Object fl,String alias) {
    super(startPageNumber, pageSize,fl,alias);
    }

protected List<Ok_personal> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
    ok_personalFilter fc;
    if(fl !=null){
        fc = (ok_personalFilter)fl;
}else{
        fc = new ok_personalFilter();
}
    return ok_personalService.getok_personalsFl(itemStartNumber, pageSize,fc,alias);
}


public int getTotalSize(Object fl, String alias)  {
    ok_personalFilter fc;
    if(fl !=null){
        fc = (ok_personalFilter)fl;
}else{
        fc = new ok_personalFilter();
}
    return ok_personalService.getCount(fc, alias);
}


public int indexOf(Object obj) {
        return 0;
}


   
    protected List<Ok_personal> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }

	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}


}