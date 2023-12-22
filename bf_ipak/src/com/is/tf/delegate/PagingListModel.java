package com.is.tf.delegate;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Delegate> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Delegate> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    DelegateFilter fc;
    if(fl !=null){
        fc = (DelegateFilter)fl;
}else{
        fc = new DelegateFilter();
}
    return DelegateService.getDelegatesFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    DelegateFilter fc;
    if(fl !=null){
        fc = (DelegateFilter)fl;
}else{
        fc = new DelegateFilter();
}
    return DelegateService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Delegate> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
