package com.is.sinov;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<sinov> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<sinov> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    sinovFilter fc;
    if(fl !=null){
        fc = (sinovFilter)fl;
}else{
        fc = new sinovFilter();
}
    return sinovService.getSinovsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    sinovFilter fc;
    if(fl !=null){
        fc = (sinovFilter)fl;
}else{
        fc = new sinovFilter();
}
    return sinovService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<sinov> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
