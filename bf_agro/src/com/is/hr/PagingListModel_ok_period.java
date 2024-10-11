package com.is.hr;
import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;
import org.zkoss.zkplus.databind.BindingListModel;

public class PagingListModel_ok_period extends AbstractPagingListModel<ok_period> implements BindingListModel {

    public PagingListModel_ok_period(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<ok_period> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    ok_periodFilter fc;
    if(fl !=null){
        fc = (ok_periodFilter)fl;
}else{
        fc = new ok_periodFilter();
}
    return ok_periodService.getok_periodsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    ok_periodFilter fc;
    if(fl !=null){
        fc = (ok_periodFilter)fl;
}else{
        fc = new ok_periodFilter();
}
    return ok_periodService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<ok_period> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}


}



