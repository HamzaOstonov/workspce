package com.is.tf.endoperation;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Endoperation> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Endoperation> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    EndoperationFilter fc;
    if(fl !=null){
        fc = (EndoperationFilter)fl;
}else{
        fc = new EndoperationFilter();
}
    return EndoperationService.getEndoperationsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    EndoperationFilter fc;
    if(fl !=null){
        fc = (EndoperationFilter)fl;
}else{
        fc = new EndoperationFilter();
}
    return EndoperationService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Endoperation> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
