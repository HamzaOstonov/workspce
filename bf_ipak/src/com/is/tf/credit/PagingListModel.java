package com.is.tf.credit;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Credit> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Credit> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    CreditFilter fc;
    if(fl !=null){
        fc = (CreditFilter)fl;
}else{
        fc = new CreditFilter();
}
    return CreditService.getCreditsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    CreditFilter fc;
    if(fl !=null){
        fc = (CreditFilter)fl;
}else{
        fc = new CreditFilter();
}
    return CreditService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Credit> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
