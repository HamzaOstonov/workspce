package com.is.tf.Goods;

import java.text.SimpleDateFormat;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;

import com.is.utils.CheckNull;

public class GoodsViewCtrl extends GenericForwardComposer{
    private Div frm;
    private Listbox dataGrid;
    private Paging contactPaging;
    private Div grd;
    private Grid addgrd,frmgrd,fgrd;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_add;
    private Toolbarbutton btn_search;
    private Toolbarbutton btn_back;
    private Toolbar tb;
    private Textbox id,p5t4,p7t4,p9t4,p11t4,p14t4,p15t4,p16t4,p17t4,p18t4,p19t4,p20t4,p22t4,p23t4;
    private Textbox aid,ap5t4,ap7t4,ap9t4,ap11t4,ap14t4,ap15t4,ap16t4,ap17t4,ap18t4,ap19t4,ap20t4,ap22t4,ap23t4 ;
    private Textbox fid,fp5t4,fp7t4,fp9t4,fp11t4,fp14t4,fp15t4,fp16t4,fp17t4,fp18t4,fp19t4,fp20t4,fp22t4,fp23t4 ;
    private Datebox p21t4,ap21t4,fp21t4;
    private Integer p0t4,ap0t4,fp0t4;
    private Decimalbox p8t4,p10t4,p12t4,p13t4,ap8t4,ap10t4,ap12t4,ap13t4,fp8t4,fp10t4,fp12t4,fp13t4;
    private Paging goodsPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;

    
    public GoodsFilter filter;

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private Goods current = new Goods();

    public GoodsViewCtrl() {
            super('$', false, false);
    }
/**
 *
 *
 */
@Override
public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        // TODO Auto-generated method stub
            binder = new AnnotateDataBinder(comp);
            binder.bindBean("current", this.current);
            binder.loadAll();
    String[] parameter = (String[]) param.get("ht");
    if (parameter!=null){
            _pageSize = Integer.parseInt( parameter[0])/36;
            dataGrid.setRows(Integer.parseInt( parameter[0])/36);
    }



        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                Goods pGoods = (Goods) data;

                row.setValue(pGoods);
                
                row.appendChild(new Listcell(pGoods.getId()+""));
                row.appendChild(new Listcell(pGoods.getP0t4()+""));
                row.appendChild(new Listcell(pGoods.getP5t4()));
                row.appendChild(new Listcell(pGoods.getP7t4()));
                row.appendChild(new Listcell(pGoods.getP8t4()+""));
                row.appendChild(new Listcell(pGoods.getP9t4()));
                row.appendChild(new Listcell(pGoods.getP10t4()+""));
                row.appendChild(new Listcell(pGoods.getP11t4()));
                row.appendChild(new Listcell(pGoods.getP12t4()+""));
                row.appendChild(new Listcell(pGoods.getP13t4()+""));
                row.appendChild(new Listcell(pGoods.getP14t4()));
                row.appendChild(new Listcell(pGoods.getP15t4()));
                row.appendChild(new Listcell(pGoods.getP16t4()));
                row.appendChild(new Listcell(pGoods.getP17t4()));
                row.appendChild(new Listcell(pGoods.getP18t4()));
                row.appendChild(new Listcell(pGoods.getP19t4()));
                row.appendChild(new Listcell(pGoods.getP20t4()));
                row.appendChild(new Listcell(pGoods.getP21t4()+""));
                row.appendChild(new Listcell(pGoods.getP22t4()));
                row.appendChild(new Listcell(pGoods.getP23t4()));


    }});

    refreshModel(_startPageNumber);

}

public void onPaging$goodsPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    goodsPaging.setPageSize(_pageSize);
model = new PagingListModel(activePage, _pageSize, filter, "");

if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize();
        _needsTotalSizeUpdate = false;
}

goodsPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(Goods) model.getElementAt(0);
sendSelEvt();
}
}



//Omitted...
public Goods getCurrent() {
return current;
}

public void setCurrent(Goods current) {
this.current = current;
}

public void onDoubleClick$dataGrid$grd() {
            grd.setVisible(false);
            frm.setVisible(true);
            frmgrd.setVisible(true);
            addgrd.setVisible(false);
            fgrd.setVisible(false);
            btn_back.setImage("/images/folder.png");
            btn_back.setLabel(Labels.getLabel("grid"));
}




public void onClick$btn_back() {
    if (frm.isVisible()){
        frm.setVisible(false);
        grd.setVisible(true);
        btn_back.setImage("/images/file.png");
        btn_back.setLabel(Labels.getLabel("back"));
    }else onDoubleClick$dataGrid$grd();
}

public void onClick$btn_first() {
    dataGrid.setSelectedIndex(0);
    sendSelEvt();
}
public void onClick$btn_last() {
    dataGrid.setSelectedIndex(model.getSize()-1);
    sendSelEvt();
}
public void onClick$btn_prev() {
    if (dataGrid.getSelectedIndex()!=0){
    dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()-1);
    sendSelEvt();
    }
}
public void onClick$btn_next() {
    if (dataGrid.getSelectedIndex()!=(model.getSize()-1)){
    dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()+1);
    sendSelEvt();
    }
}



private void sendSelEvt(){
    if (dataGrid.getSelectedIndex()==0){
            btn_first.setDisabled(true);
            btn_prev.setDisabled(true);
    }else{
            btn_first.setDisabled(false);
            btn_prev.setDisabled(false);
    }
    if(dataGrid.getSelectedIndex()==(model.getSize()-1)){
            btn_next.setDisabled(true);
            btn_last.setDisabled(true);
    }else{
            btn_next.setDisabled(false);
            btn_last.setDisabled(false);
    }
    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
    Events.sendEvent(evt);
}


public void onClick$btn_add() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(true);
    fgrd.setVisible(false);
}

public void onClick$btn_search() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(false);
    fgrd.setVisible(true);
}


public void onClick$btn_save() {
try{
    if(addgrd.isVisible()){
            GoodsService.create(new Goods(
            
            Long.parseLong(aid.getValue()),
            ap0t4.intValue(),
            ap5t4.getValue(),
            ap7t4.getValue(),
            ap8t4.doubleValue(),
            ap9t4.getValue(),
            ap10t4.doubleValue(),
            ap11t4.getValue(),
            ap12t4.doubleValue(),
            ap13t4.doubleValue(),
            ap14t4.getValue(),
            ap15t4.getValue(),
            ap16t4.getValue(),
            ap17t4.getValue(),
            ap18t4.getValue(),
            ap19t4.getValue(),
            ap20t4.getValue(),
            ap21t4.getValue(),
            ap22t4.getValue(),
            ap23t4.getValue()
            ));
        CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new GoodsFilter();
        
          Long.parseLong(fid.getValue());
          filter.setP0t4(fp0t4.intValue());
          filter.setP5t4(fp5t4.getValue());
          filter.setP7t4(fp7t4.getValue());
          filter.setP8t4(fp8t4.doubleValue());
          filter.setP9t4(fp9t4.getValue());
          filter.setP10t4(fp10t4.doubleValue());
          filter.setP11t4(fp11t4.getValue());
          filter.setP12t4(fp12t4.doubleValue());
          filter.setP13t4(fp13t4.doubleValue());
          filter.setP14t4(fp14t4.getValue());
          filter.setP15t4(fp15t4.getValue());
          filter.setP16t4(fp16t4.getValue());
          filter.setP17t4(fp17t4.getValue());
          filter.setP18t4(fp18t4.getValue());
          filter.setP19t4(fp19t4.getValue());
          filter.setP20t4(fp20t4.getValue());
          filter.setP21t4(fp21t4.getValue());
          filter.setP22t4(fp22t4.getValue());
          filter.setP23t4(fp23t4.getValue());

    }else{
        
          Long.parseLong(id.getValue());
          current.setP0t4(p0t4.intValue());
          current.setP5t4(p5t4.getValue());
          current.setP7t4(p7t4.getValue());
          current.setP8t4(p8t4.doubleValue());
          current.setP9t4(p9t4.getValue());
          current.setP10t4(p10t4.doubleValue());
          current.setP11t4(p11t4.getValue());
          current.setP12t4(p12t4.doubleValue());
          current.setP13t4(p13t4.doubleValue());
          current.setP14t4(p14t4.getValue());
          current.setP15t4(p15t4.getValue());
          current.setP16t4(p16t4.getValue());
          current.setP17t4(p17t4.getValue());
          current.setP18t4(p18t4.getValue());
          current.setP19t4(p19t4.getValue());
          current.setP20t4(p20t4.getValue());
          current.setP21t4(p21t4.getValue());
          current.setP22t4(p22t4.getValue());
          current.setP23t4(p23t4.getValue());
        GoodsService.update(current);
    }
onClick$btn_back();
refreshModel(_startPageNumber);
SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
Events.sendEvent(evt);
} catch (Exception e) {
    e.printStackTrace();
}

}
public void onClick$btn_cancel() {
    if(fgrd.isVisible()){
            filter = new GoodsFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
CheckNull.clearForm(addgrd);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}


}
