package com.is.tf.lease;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Lease> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Lease> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    LeaseFilter fc;
    if(fl !=null){
        fc = (LeaseFilter)fl;
}else{
        fc = new LeaseFilter();
}
    return LeaseService.getLeasesFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    LeaseFilter fc;
    if(fl !=null){
        fc = (LeaseFilter)fl;
}else{
        fc = new LeaseFilter();
}
    return LeaseService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Lease> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
