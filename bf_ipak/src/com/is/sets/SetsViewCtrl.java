/* Decompiler 126ms, total 888ms, lines 322 */
package com.is.sets;

import com.is.utils.CheckNull;
import java.text.SimpleDateFormat;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;

public class SetsViewCtrl extends GenericForwardComposer {
   private Div frm;
   private Paging contactPaging;
   private Div grd;
   private Listbox dataGrid;
   private Grid addgrd;
   private Grid frmgrd;
   private Grid fgrd;
   private Toolbarbutton btn_last;
   private Toolbarbutton btn_next;
   private Toolbarbutton btn_prev;
   private Toolbarbutton btn_first;
   private Toolbarbutton btn_add;
   private Toolbarbutton btn_search;
   private Toolbarbutton btn_back;
   private Toolbarbutton btn_save;
   private Toolbar tb;
   private Textbox branch;
   private Textbox id;
   private Textbox value;
   private Textbox name;
   private Longbox editable;
   private Textbox abranch;
   private Textbox aid;
   private Textbox avalue;
   private Textbox aname;
   private Longbox aeditable;
   private Textbox fbranch;
   private Textbox fid;
   private Textbox fvalue;
   private Textbox fname;
   private Longbox feditable;
   private Paging klb_setsPaging;
   private int _pageSize = 10;
   private int _startPageNumber = 0;
   private int _totalSize = 0;
   private boolean _needsTotalSizeUpdate = true;
   private Listheader lh_branch;
   private Row rowbranch;
   private String _table = "";
   public Sets current = new Sets();
   public SetsFilter filter = new SetsFilter();
   PagingListModel model = null;
   ListModelList lmodel = null;
   private AnnotateDataBinder binder;
   SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

   public SetsViewCtrl() {
      super('$', false, false);
   }

   public void doAfterCompose(Component comp) throws Exception {
      super.doAfterCompose(comp);
      this.binder = new AnnotateDataBinder(comp);
      this.binder.bindBean("current", this.current);
      this.binder.loadAll();
      String[] parameter = (String[])this.param.get("ht");
      if (parameter != null) {
         this._pageSize = Integer.parseInt(parameter[0]) / 36;
         this.dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
      }

      parameter = (String[])this.param.get("table");
      if (parameter != null) {
         this._table = parameter[0];
         if (this._table.equalsIgnoreCase("bf_sets")) {
            this.lh_branch.setWidth("0px");
            this.rowbranch.setVisible(false);
         } else {
            this.lh_branch.setWidth("100px");
            this.rowbranch.setVisible(true);
         }
      }

      this.dataGrid.setItemRenderer(new ListitemRenderer() {
         public void render(Listitem row, Object data) throws Exception {
            Sets pKlb_sets = (Sets)data;
            row.setValue(pKlb_sets);
            row.appendChild(new Listcell(pKlb_sets.getBranch()));
            row.appendChild(new Listcell(pKlb_sets.getId()));
            row.appendChild(new Listcell(pKlb_sets.getValue()));
            row.appendChild(new Listcell(pKlb_sets.getName()));
            row.appendChild(new Listcell(pKlb_sets.getEditable() == 1L ? "Разрешено редактирование" : "Запрещено редактирование"));
         }
      });
      this.refreshModel(this._startPageNumber);
   }

   public void onPaging$klb_setsPaging(ForwardEvent event) {
      PagingEvent pe = (PagingEvent)event.getOrigin();
      this._startPageNumber = pe.getActivePage();
      this.refreshModel(this._startPageNumber);
   }

   private void refreshModel(int activePage) {
      this.klb_setsPaging.setPageSize(this._pageSize);
      this.model = new PagingListModel(activePage, this._pageSize, this.filter, this._table);
      if (this._needsTotalSizeUpdate) {
         this._totalSize = this.model.getTotalSize(this.filter, this._table);
      }

      this.klb_setsPaging.setTotalSize(this._totalSize);
      this.dataGrid.setModel(this.model);
      this.sort();
      if (this.model.getSize() > 0) {
         this.dataGrid.setSelectedIndex(0);
         this.sendSelEvt(true);
         this.current = (Sets)this.model.getElementAt(0);
         this.sendSelEvt(true);
      }

   }

   public void onSelect$dataGrid$grd() {
      this.sendSelEvt(false);
   }

   public void sort() {
      for(int i = 0; i < this.dataGrid.getListhead().getChildren().size(); ++i) {
         Listheader listheader = (Listheader)this.dataGrid.getListhead().getChildren().get(i);
         if (!listheader.getSortDirection().equalsIgnoreCase("natural")) {
            listheader.sort(listheader.getSortDirection().equalsIgnoreCase("ascending"), true);
            return;
         }
      }

   }

   public Sets getCurrent() {
      return this.current;
   }

   public void setCurrent(Sets current) {
      this.current = current;
   }

   public void onDoubleClick$dataGrid$grd() {
      this.grd.setVisible(false);
      this.frm.setVisible(true);
      this.frmgrd.setVisible(true);
      this.addgrd.setVisible(false);
      this.fgrd.setVisible(false);
      this.btn_back.setImage("/images/folder.png");
      this.btn_back.setLabel(Labels.getLabel("grid"));
   }

   public void onClick$btn_back() {
      if (this.frm.isVisible()) {
         this.frm.setVisible(false);
         this.grd.setVisible(true);
         this.btn_back.setImage("/images/file.png");
         this.btn_back.setLabel(Labels.getLabel("back"));
      } else {
         this.onDoubleClick$dataGrid$grd();
      }

   }

   public void onClick$btn_first() {
      this.dataGrid.setSelectedIndex(0);
      this.sendSelEvt(true);
   }

   public void onClick$btn_last() {
      this.dataGrid.setSelectedIndex(this.model.getSize() - 1);
      this.sendSelEvt(true);
   }

   public void onClick$btn_prev() {
      if (this.dataGrid.getSelectedIndex() != 0) {
         this.dataGrid.setSelectedIndex(this.dataGrid.getSelectedIndex() - 1);
         this.sendSelEvt(true);
      }

   }

   public void onClick$btn_next() {
      if (this.dataGrid.getSelectedIndex() != this.model.getSize() - 1) {
         this.dataGrid.setSelectedIndex(this.dataGrid.getSelectedIndex() + 1);
         this.sendSelEvt(true);
      }

   }

   private void sendSelEvt(Boolean senEvt) {
      if (this.dataGrid.getSelectedIndex() == 0) {
         this.btn_first.setDisabled(true);
         this.btn_prev.setDisabled(true);
      } else {
         this.btn_first.setDisabled(false);
         this.btn_prev.setDisabled(false);
      }

      if (this.dataGrid.getSelectedIndex() == this.model.getSize() - 1) {
         this.btn_next.setDisabled(true);
         this.btn_last.setDisabled(true);
      } else {
         this.btn_next.setDisabled(false);
         this.btn_last.setDisabled(false);
      }

      if (senEvt) {
         SelectEvent evt = new SelectEvent("onSelect", this.dataGrid, this.dataGrid.getSelectedItems());
         Events.sendEvent(evt);
      }

      if (this.current != null) {
         this.btn_save.setVisible(this.current.getEditable() == 1L);
      }

   }

   public void onClick$btn_add() {
      this.onDoubleClick$dataGrid$grd();
      this.frmgrd.setVisible(false);
      this.addgrd.setVisible(true);
      this.fgrd.setVisible(false);
      this.btn_save.setVisible(true);
   }

   public void onClick$btn_search() {
      this.onDoubleClick$dataGrid$grd();
      this.frmgrd.setVisible(false);
      this.addgrd.setVisible(false);
      this.fgrd.setVisible(true);
      this.btn_save.setVisible(true);
   }

   public void onClick$btn_save() {
      try {
         if (this.addgrd.isVisible()) {
            SetsService.create(new Sets(this.abranch.getValue(), this.aid.getValue(), this.avalue.getValue(), this.aname.getValue(), this.aeditable.getValue()), this._table);
            CheckNull.clearForm(this.addgrd);
            this.frmgrd.setVisible(true);
            this.addgrd.setVisible(false);
            this.fgrd.setVisible(false);
         } else if (this.fgrd.isVisible()) {
            this.filter = new SetsFilter();
            if (!CheckNull.isEmpty(this.fbranch.getValue())) {
               this.filter.setBranch(this.fbranch.getValue());
            }

            if (!CheckNull.isEmpty(this.fid.getValue())) {
               this.filter.setId(this.fid.getValue());
            }

            if (!CheckNull.isEmpty(this.fvalue.getValue())) {
               this.filter.setValue(this.fvalue.getValue());
            }

            if (!CheckNull.isEmpty(this.fname.getValue())) {
               this.filter.setName(this.fname.getValue());
            }

            if (!CheckNull.isEmpty(this.feditable.getValue())) {
               this.filter.setEditable(this.feditable.getValue());
            }
         } else {
            this.current.setBranch(this.branch.getValue());
            this.current.setId(this.id.getValue());
            this.current.setValue(this.value.getValue());
            this.current.setName(this.name.getValue());
            this.current.setEditable(this.editable.getValue());
            SetsService.update(this.current, this._table);
         }

         this.onClick$btn_back();
         this.refreshModel(this._startPageNumber);
         SelectEvent evt = new SelectEvent("onSelect", this.dataGrid, this.dataGrid.getSelectedItems());
         Events.sendEvent(evt);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public void onClick$btn_cancel() {
      if (this.fgrd.isVisible()) {
         this.filter = new SetsFilter();
      }

      this.onClick$btn_back();
      this.frmgrd.setVisible(true);
      this.addgrd.setVisible(false);
      this.fgrd.setVisible(false);
      CheckNull.clearForm(this.addgrd);
      CheckNull.clearForm(this.fgrd);
      this.refreshModel(this._startPageNumber);
   }

   public void onClick$btn_refresh() {
      this.refreshModel(this._startPageNumber);
   }
}