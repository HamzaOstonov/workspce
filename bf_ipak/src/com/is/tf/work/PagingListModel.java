package com.is.tf.work;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Work> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Work> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    WorkFilter fc;
    if(fl !=null){
        fc = (WorkFilter)fl;
}else{
        fc = new WorkFilter();
}
    return WorkService.getWorksFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    WorkFilter fc;
    if(fl !=null){
        fc = (WorkFilter)fl;
}else{
        fc = new WorkFilter();
}
    return WorkService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Work> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
