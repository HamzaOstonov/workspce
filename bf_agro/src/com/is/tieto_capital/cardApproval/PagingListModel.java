package com.is.tieto_capital.cardApproval;

import java.util.List;

import org.zkoss.zkplus.databind.BindingListModel;

import com.is.utils.AbstractPagingListModel;

@SuppressWarnings("serial")
public class PagingListModel extends AbstractPagingListModel<CardApproval> implements BindingListModel {

	public PagingListModel(int startPageNumber, int pageSize, Object fl,String branch) {
		super(startPageNumber, pageSize, fl, branch);
	}
        
    @Override
    protected List<CardApproval> getPageData(int itemStartNumber, int pageSize, Object fl, String branch) {
    	CardApprovalFilter fc;
		
		if(fl != null) {
			fc = (CardApprovalFilter) fl;
		}
		else {
			fc = new CardApprovalFilter();
		}
		
		return CardApprovalService.getCardApprovalsFl(itemStartNumber, pageSize, fc);
    }

    @Override
    public int getTotalSize(Object fl, String branch) {
        CardApprovalFilter fc;
        
        if(fl != null) {
            fc = (CardApprovalFilter) fl;
        }
        else {
            fc = new CardApprovalFilter();
        }
        
        return CardApprovalService.getCount(fc);
    }    
    
    public int indexOf(Object obj) {
            return 0;
    }
    

    @Override
    protected List<CardApproval> getPageData(int itemStartNumber, int pageSize) {
        // TODO Auto-generated method stub
        return null;
    }
    
	@Override
	public int getTotalSize() {
		// TODO Auto-generated method stub
		return 0;
	}
    

}
