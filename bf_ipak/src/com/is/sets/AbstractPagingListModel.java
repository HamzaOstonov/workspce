/* Decompiler 24ms, total 452ms, lines 56 */
package com.is.sets;

import java.util.ArrayList;
import java.util.List;
import org.zkoss.zul.AbstractListModel;

public abstract class AbstractPagingListModel<T> extends AbstractListModel {
   private static final long serialVersionUID = 6613208067174831719L;
   private int _startPageNumber;
   private int _pageSize;
   private int _itemStartNumber;
   private List<T> _items = new ArrayList();

   public AbstractPagingListModel(int startPageNumber, int pageSize) {
      this._startPageNumber = startPageNumber;
      this._pageSize = pageSize;
      this._itemStartNumber = startPageNumber * pageSize;
      this._items = this.getPageData(this._itemStartNumber, this._pageSize);
   }

   public AbstractPagingListModel(int startPageNumber, int pageSize, Object fl, String _table) {
      this._startPageNumber = startPageNumber;
      this._pageSize = pageSize;
      this._itemStartNumber = startPageNumber * pageSize;
      this._items = this.getPageData(this._itemStartNumber, this._pageSize, fl, _table);
   }

   public abstract int getTotalSize();

   public abstract int getTotalSize(Object var1, String var2);

   protected abstract List<T> getPageData(int var1, int var2);

   protected abstract List<T> getPageData(int var1, int var2, Object var3, String var4);

   public Object getElementAt(int index) {
      return this._items.get(index);
   }

   public int getSize() {
      return this._items.size();
   }

   public int getStartPageNumber() {
      return this._startPageNumber;
   }

   public int getPageSize() {
      return this._pageSize;
   }

   public int getItemStartNumber() {
      return this._itemStartNumber;
   }
}