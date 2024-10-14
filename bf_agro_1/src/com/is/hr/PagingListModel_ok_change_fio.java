package com.is.hr;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel_ok_change_fio extends AbstractPagingListModel<ok_change_fio> implements BindingListModel {

    public PagingListModel_ok_change_fio(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<ok_change_fio> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    ok_change_fioFilter fc;
    if(fl !=null){
        fc = (ok_change_fioFilter)fl;
}else{
        fc = new ok_change_fioFilter();
}
    return ok_change_fioService.getok_change_fiosFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    ok_change_fioFilter fc;
    if(fl !=null){
        fc = (ok_change_fioFilter)fl;
}else{
        fc = new ok_change_fioFilter();
}
    return ok_change_fioService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<ok_change_fio> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}


}
