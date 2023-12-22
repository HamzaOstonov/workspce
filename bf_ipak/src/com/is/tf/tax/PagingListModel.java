package com.is.tf.tax;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Tax> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Tax> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    TaxFilter fc;
    if(fl !=null){
        fc = (TaxFilter)fl;
}else{
        fc = new TaxFilter();
}
    return TaxService.getTaxsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    TaxFilter fc;
    if(fl !=null){
        fc = (TaxFilter)fl;
}else{
        fc = new TaxFilter();
}
    return TaxService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Tax> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
