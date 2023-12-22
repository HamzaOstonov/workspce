package com.is.tf.Commission;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Commission> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Commission> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    CommissionFilter fc;
    if(fl !=null){
        fc = (CommissionFilter)fl;
}else{
        fc = new CommissionFilter();
}
    return CommissionService.getCommissionsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    CommissionFilter fc;
    if(fl !=null){
        fc = (CommissionFilter)fl;
}else{
        fc = new CommissionFilter();
}
    return CommissionService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Commission> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
