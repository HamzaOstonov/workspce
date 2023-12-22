package com.is.tf.paymentrefsumchange;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Paymentrefsumchange> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Paymentrefsumchange> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    PaymentrefsumchangeFilter fc;
    if(fl !=null){
        fc = (PaymentrefsumchangeFilter)fl;
}else{
        fc = new PaymentrefsumchangeFilter();
}
    return PaymentrefsumchangeService.getPaymentrefsumchangesFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    PaymentrefsumchangeFilter fc;
    if(fl !=null){
        fc = (PaymentrefsumchangeFilter)fl;
}else{
        fc = new PaymentrefsumchangeFilter();
}
    return PaymentrefsumchangeService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Paymentrefsumchange> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}



}
