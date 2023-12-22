/* Decompiler 29ms, total 693ms, lines 61 */
package com.is.sets;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zul.ListModelExt;

public class PagingListModel extends AbstractPagingListModel<Sets> implements BindingListModel, ListModelExt {
   private static final long serialVersionUID = 6613313502344831719L;
   private List<Sets> _items;

   public PagingListModel(int startPageNumber, int pageSize, Object fl, String _table) {
      super(startPageNumber, pageSize, fl, _table);
   }

   protected List<Sets> getPageData(int itemStartNumber, int pageSize, Object fl, String _table) {
      SetsFilter fc;
      if (fl != null) {
         fc = (SetsFilter)fl;
      } else {
         fc = new SetsFilter();
      }

      this._items = SetsService.getKlb_setssFl(itemStartNumber, pageSize, fc, _table);
      return this._items;
   }

   public int getTotalSize(Object fl, String _table) {
      SetsFilter fc;
      if (fl != null) {
         fc = (SetsFilter)fl;
      } else {
         fc = new SetsFilter();
      }

      return SetsService.getCount(fc, _table);
   }

   public int indexOf(Object obj) {
      return 0;
   }

   protected List<Sets> getPageData(int itemStartNumber, int pageSize) {
      return null;
   }

   public int getTotalSize() {
      return 0;
   }

   public void sort(Comparator cmpr, boolean ascending) {
      Collections.sort(this.getItems(), cmpr);
      this.fireEvent(0, -1, -1);
   }

   private List<Sets> getItems() {
      return this._items;
   }
}