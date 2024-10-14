package com.is.client_mass_openingHistory;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;
import com.is.utils.AbstractPagingListModel;

public class PagingListModel extends AbstractPagingListModel<Client_mass_opening_file> implements BindingListModel {

        public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
        super(startPageNumber, pageSize,fl,branch);
        }
    @Override
    protected List<Client_mass_opening_file> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
        Client_mass_opening_fileFilter fc;
        if(fl !=null){
            fc = (Client_mass_opening_fileFilter)fl;
    }else{
            fc = new Client_mass_opening_fileFilter();
    }
        return Client_mass_opening_fileHistoryService.getClient_mass_opening_filesFl(itemStartNumber, pageSize,fc);
    }

    @Override
    public int getTotalSize(Object fl, String branch)  {
        Client_mass_opening_fileFilter fc;
        if(fl !=null){
            fc = (Client_mass_opening_fileFilter)fl;
    }else{
            fc = new Client_mass_opening_fileFilter();
    }
        return Client_mass_opening_fileHistoryService.getCount(fc);
    }

    @Override
    public int indexOf(Object obj) {
            return 0;
    }
    

        @Override
        protected List<Client_mass_opening_file> getPageData(int itemStartNumber, int pageSize) {
                // TODO Auto-generated method stub
                return null;
        }
		@Override
		public int getTotalSize() {
			// TODO Auto-generated method stub
			return 0;
		}
    

}



