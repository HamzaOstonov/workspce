package com.is.tf.fund;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Fund> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Fund> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    FundFilter fc;
    if(fl !=null){
        fc = (FundFilter)fl;
}else{
        fc = new FundFilter();
}
    return FundService.getFundsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    FundFilter fc;
    if(fl !=null){
        fc = (FundFilter)fl;
}else{
        fc = new FundFilter();
}
    return FundService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Fund> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
