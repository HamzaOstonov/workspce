package com.is.tf.exchangepayment;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<ExchangePayment> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<ExchangePayment> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    ExchangePaymentFilter fc;
    if(fl !=null){
        fc = (ExchangePaymentFilter)fl;
}else{
        fc = new ExchangePaymentFilter();
}
    return ExchangePaymentService.getExchangePaymentsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    ExchangePaymentFilter fc;
    if(fl !=null){
        fc = (ExchangePaymentFilter)fl;
}else{
        fc = new ExchangePaymentFilter();
}
    return ExchangePaymentService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<ExchangePayment> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}


}
