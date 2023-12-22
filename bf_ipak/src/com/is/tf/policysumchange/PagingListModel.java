package com.is.tf.policysumchange;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Policysumchange> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Policysumchange> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    PolicysumchangeFilter fc;
    if(fl !=null){
        fc = (PolicysumchangeFilter)fl;
}else{
        fc = new PolicysumchangeFilter();
}
    return PolicysumchangeService.getPolicysumchangesFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    PolicysumchangeFilter fc;
    if(fl !=null){
        fc = (PolicysumchangeFilter)fl;
}else{
        fc = new PolicysumchangeFilter();
}
    return PolicysumchangeService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Policysumchange> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
