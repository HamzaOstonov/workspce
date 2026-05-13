package com.is.tieto_visa.account;

import com.is.utils.AbstractPagingListModel;
import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;

public class PagingListModel extends AbstractPagingListModel<Account> implements BindingListModel {
   public PagingListModel(int startPageNumber, int pageSize, Object fl, String alias) {
      super(startPageNumber, pageSize, fl, alias);
   }

   protected List<Account> getPageData(int itemStartNumber, int pageSize, Object fl, String alias) {
      AccountFilter fc;
      if (fl != null) {
         fc = (AccountFilter)fl;
      } else {
         fc = new AccountFilter();
      }

      return AccountService.getAccountsFl(itemStartNumber, pageSize, fc, alias);
   }

   public int getTotalSize(Object fl, String alias) {
      AccountFilter fc;
      if (fl != null) {
         fc = (AccountFilter)fl;
      } else {
         fc = new AccountFilter();
      }

      return AccountService.getCount(fc, alias);
   }

   public int indexOf(Object obj) {
      return 0;
   }

   protected List<Account> getPageData(int itemStartNumber, int pageSize) {
      return null;
   }

   public int getTotalSize() {
      return 0;
   }
}
