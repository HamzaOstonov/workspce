package com.is.qr_online.sets;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Qr_lead_sets> implements BindingListModel {
	

        /**
	 * 
	 */
	private static final long serialVersionUID = 103844514947365247L;
		public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
        super(startPageNumber, pageSize,fl,branch);
        }
    @Override
    protected List<Qr_lead_sets> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
        Qr_lead_setsFilter fc;
        if(fl !=null){
            fc = (Qr_lead_setsFilter)fl;
    }else{
            fc = new Qr_lead_setsFilter();
    }
        return Qr_LeadService.getQr_lead_setsFl(itemStartNumber, pageSize,fc);
    }

    @Override
    public int getTotalSize(Object fl, String branch)  {
        Qr_lead_setsFilter fc;
        if(fl !=null){
            fc = (Qr_lead_setsFilter)fl;
    }else{
            fc = new Qr_lead_setsFilter();
    }
        return Qr_LeadService.getCount(fc);
    }

    @Override
    public int indexOf(Object obj) {
            return 0;
    }
    

        @Override
        protected List<Qr_lead_sets> getPageData(int itemStartNumber, int pageSize) {
                // TODO Auto-generated method stub
                return null;
        }
		@Override
		public int getTotalSize() {
			// TODO Auto-generated method stub
			return 0;
		}
    

}




