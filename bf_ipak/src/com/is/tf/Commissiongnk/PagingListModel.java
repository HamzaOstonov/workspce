package com.is.tf.Commissiongnk;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Commissiongnk> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Commissiongnk> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    CommissiongnkFilter fc;
    if(fl !=null){
        fc = (CommissiongnkFilter)fl;
}else{
        fc = new CommissiongnkFilter();
}
    return CommissiongnkService.getCommissiongnksFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    CommissiongnkFilter fc;
    if(fl !=null){
        fc = (CommissiongnkFilter)fl;
}else{
        fc = new CommissiongnkFilter();
}
    return CommissiongnkService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Commissiongnk> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
