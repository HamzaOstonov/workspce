package com.is.tf.shipment;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Shipment> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Shipment> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    ShipmentFilter fc;
    if(fl !=null){
        fc = (ShipmentFilter)fl;
}else{
        fc = new ShipmentFilter();
}
    return ShipmentService.getShipmentsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    ShipmentFilter fc;
    if(fl !=null){
        fc = (ShipmentFilter)fl;
}else{
        fc = new ShipmentFilter();
}
    return ShipmentService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Shipment> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
