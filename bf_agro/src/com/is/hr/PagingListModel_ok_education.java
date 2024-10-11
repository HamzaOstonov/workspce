package com.is.hr;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;
import org.zkoss.zkplus.databind.BindingListModel;

public class PagingListModel_ok_education extends AbstractPagingListModel<ok_education> implements BindingListModel {

    public PagingListModel_ok_education(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<ok_education> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    ok_educationFilter fc;
    if(fl !=null){
        fc = (ok_educationFilter)fl;
}else{
        fc = new ok_educationFilter();
}
    return ok_educationService.getok_educationsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    ok_educationFilter fc;
    if(fl !=null){
        fc = (ok_educationFilter)fl;
}else{
        fc = new ok_educationFilter();
}
    return ok_educationService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<ok_education> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}


}


