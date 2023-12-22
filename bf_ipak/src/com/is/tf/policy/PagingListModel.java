package com.is.tf.policy;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Policy> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Policy> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    PolicyFilter fc;
    if(fl !=null){
        fc = (PolicyFilter)fl;
}else{
        fc = new PolicyFilter();
}
    return PolicyService.getPolicysFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    PolicyFilter fc;
    if(fl !=null){
        fc = (PolicyFilter)fl;
}else{
        fc = new PolicyFilter();
}
    return PolicyService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Policy> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
