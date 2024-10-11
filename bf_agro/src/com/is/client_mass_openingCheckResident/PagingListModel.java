package com.is.client_mass_openingCheckResident;
import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Client_mass_opening_resident> implements BindingListModel {

        public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
        super(startPageNumber, pageSize,fl,branch);
        }
    @Override
    protected List<Client_mass_opening_resident> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
        Client_mass_opening_residentFilter fc;
        if(fl !=null){
            fc = (Client_mass_opening_residentFilter)fl;
    }else{
            fc = new Client_mass_opening_residentFilter();
    }
        return Client_mass_opening_residentService.getClient_mass_opening_residentsFl(itemStartNumber, pageSize,fc);
    }

    @Override
    public int getTotalSize(Object fl, String branch)  {
        Client_mass_opening_residentFilter fc;
        if(fl !=null){
            fc = (Client_mass_opening_residentFilter)fl;
    }else{
            fc = new Client_mass_opening_residentFilter();
    }
        return Client_mass_opening_residentService.getCount(fc);
    }

    @Override
    public int indexOf(Object obj) {
            return 0;
    }
    

        @Override
        protected List<Client_mass_opening_resident> getPageData(int itemStartNumber, int pageSize) {
                // TODO Auto-generated method stub
                return null;
        }
		@Override
		public int getTotalSize() {
			// TODO Auto-generated method stub
			return 0;
		}
    

}