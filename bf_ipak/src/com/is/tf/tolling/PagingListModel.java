package com.is.tf.tolling;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Tolling> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Tolling> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    TollingFilter fc;
    if(fl !=null){
        fc = (TollingFilter)fl;
}else{
        fc = new TollingFilter();
}
    return TollingService.getTollingsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    TollingFilter fc;
    if(fl !=null){
        fc = (TollingFilter)fl;
}else{
        fc = new TollingFilter();
}
    return TollingService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Tolling> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
