package com.is.hr;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel_ok_relation extends AbstractPagingListModel<ok_relation> implements BindingListModel {

    public PagingListModel_ok_relation(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<ok_relation> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    ok_relationFilter fc;
    if(fl !=null){
        fc = (ok_relationFilter)fl;
}else{
        fc = new ok_relationFilter();
}
    return ok_relationService.getok_relationsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    ok_relationFilter fc;
    if(fl !=null){
        fc = (ok_relationFilter)fl;
}else{
        fc = new ok_relationFilter();
}
    return ok_relationService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<ok_relation> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
