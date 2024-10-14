package com.is.hr;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel_ok_army extends AbstractPagingListModel<ok_army> implements BindingListModel {

    public PagingListModel_ok_army(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<ok_army> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    ok_armyFilter fc;
    if(fl !=null){
        fc = (ok_armyFilter)fl;
}else{
        fc = new ok_armyFilter();
}
    return ok_armyService.getok_armysFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    ok_armyFilter fc;
    if(fl !=null){
        fc = (ok_armyFilter)fl;
}else{
        fc = new ok_armyFilter();
}
    return ok_armyService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<ok_army> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}


}

