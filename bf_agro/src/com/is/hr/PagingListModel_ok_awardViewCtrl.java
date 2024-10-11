package com.is.hr;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel_ok_awardViewCtrl extends AbstractPagingListModel<ok_award> implements BindingListModel {

    public PagingListModel_ok_awardViewCtrl(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<ok_award> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    ok_awardFilter fc;
    if(fl !=null){
        fc = (ok_awardFilter)fl;
}else{
        fc = new ok_awardFilter();
}
    return ok_awardService.getok_awardsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    ok_awardFilter fc;
    if(fl !=null){
        fc = (ok_awardFilter)fl;
}else{
        fc = new ok_awardFilter();
}
    return ok_awardService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<ok_award> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}


}
