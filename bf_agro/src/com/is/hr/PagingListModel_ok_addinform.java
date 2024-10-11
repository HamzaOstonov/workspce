package com.is.hr;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel_ok_addinform extends AbstractPagingListModel<ok_addinform> implements BindingListModel {

    public PagingListModel_ok_addinform(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<ok_addinform> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    ok_addinformFilter fc;
    if(fl !=null){
        fc = (ok_addinformFilter)fl;
}else{
        fc = new ok_addinformFilter();
}
    return ok_addinformService.getok_addinformsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    ok_addinformFilter fc;
    if(fl !=null){
        fc = (ok_addinformFilter)fl;
}else{
        fc = new ok_addinformFilter();
}
    return ok_addinformService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<ok_addinform> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}


}