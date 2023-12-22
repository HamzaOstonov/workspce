package com.is.client_p;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")

public class PagingListModel extends AbstractPagingListModel<Client_p> implements BindingListModel {

    public PagingListModel(int startPageNumber, int pageSize, Object fl, String branch) {
    super(startPageNumber, pageSize, fl, branch);
    }
@Override
protected List<Client_p> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    Client_pFilter fc;
    if(fl !=null){
        fc = (Client_pFilter)fl;
}else{
        fc = new Client_pFilter();
}
    return Client_pService.getclient_psFl(itemStartNumber, pageSize, fc);
}

@Override
public int getTotalSize(Object fl, String branch)  {
    Client_pFilter fc;
    if(fl !=null){
        fc = (Client_pFilter)fl;
}else{
        fc = new Client_pFilter();
}
    return Client_pService.getCount(fc);
}

@Override
public int indexOf(Object obj) {
        return 0;
}


    @Override
    protected List<Client_p> getPageData(int itemStartNumber, int pageSize) {
            // TODO Auto-generated method stub
            return null;
    }


@Override
public int getTotalSize()
{
	return 0;
}
}

