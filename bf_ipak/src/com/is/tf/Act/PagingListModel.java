package com.is.tf.Act;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Act> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Act> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    ActFilter fc;
    if(fl !=null){
        fc = (ActFilter)fl;
}else{
        fc = new ActFilter();
}
    return ActService.getActsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    ActFilter fc;
    if(fl !=null){
        fc = (ActFilter)fl;
}else{
        fc = new ActFilter();
}
    return ActService.getCount(fc); 
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Act> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
