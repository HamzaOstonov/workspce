package com.is.tf.contract;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.zkoss.zk.ui.Components;
import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelExt;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listgroup;
import org.zkoss.zul.event.ListDataListener;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Contract> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
    super(startPageNumber, pageSize,fl,branch);
    }
@Override
protected List<Contract> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    ContractFilter fc;
    if(fl !=null){
        fc = (ContractFilter)fl;
}else{
        fc = new ContractFilter();
}
    return ContractService.getContractsFl(itemStartNumber, pageSize,fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    ContractFilter fc;
    if(fl !=null){
        fc = (ContractFilter)fl;
}else{
        fc = new ContractFilter();
}
    return ContractService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Contract> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	class MyListModel implements ListModel, ListModelExt {
	    public void sort(Comparator cmpr, boolean ascending) {
	        //do the real sorting
	        //notify the listbox (or grid) that data is changed by use of ListDataEvent
	    }

		@Override
		public Object getElementAt(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getSize() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void addListDataListener(ListDataListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeListDataListener(ListDataListener l) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
}
