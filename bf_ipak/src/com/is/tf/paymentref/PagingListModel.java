package com.is.tf.paymentref;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Paymentref> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Paymentref> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    PaymentrefFilter fc;
    if(fl !=null){
        fc = (PaymentrefFilter)fl;
}else{
        fc = new PaymentrefFilter();
}
    return PaymentrefService.getPaymentrefsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    PaymentrefFilter fc;
    if(fl !=null){
        fc = (PaymentrefFilter)fl;
}else{
        fc = new PaymentrefFilter();
}
    return PaymentrefService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Paymentref> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
