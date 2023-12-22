/**
 * 
 */
package com.is.openwayutils.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.InputElement;
import org.zkoss.util.media.AMedia;

import com.is.ConnectionPool;
import com.is.openwayutils.utils.RefCBox;
import com.is.openwayutils.utils.RefDataService;

/**
 * @author sergey_l
 *
 */
public class ReportwndViewCtrl extends GenericForwardComposer {

	private Div reportwnd,srep;
	private AMedia repmd;
	private Iframe iframe;
	private Listbox repGrid;
	private Window  repparwnd;
	private Report _rp;
	private Grid repparwnd$parGrid;
	private String alias;

	/**
	 *
	 *
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		// TODO Auto-generated method stub
/*		
		repGrid.setItemRenderer(new ListitemRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception {
          	    Report prep = (Report) data;
                  row.setValue(prep);
                  row.appendChild(new Listcell(prep.getRfname()));
                  row.appendChild(new Listcell(prep.getRname()));
            }});
		
		
		repGrid.setModel(new ListModelList(ReportService.getReport()));
*/
		
        String[] parameter = (String[]) param.get("id");
        alias = (String) session.getAttribute("alias");
        
        if (parameter!=null){

		_rp  = ReportService.getReport(Integer.parseInt(parameter[0]),alias);
		
        }
		
		repparwnd$parGrid.setRowRenderer(new RowRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Row row, Object data) throws Exception {
            	RepPar prp = (RepPar) data;
                    row.getChildren().add(new Label(prp.getPname() ));
                    if (!prp.getPsel().isEmpty()){
                    	RefCBox l = new RefCBox();
                        l.setModel((new ListModelList(RefDataService.getRefData(prp.getPsel(),alias ))));
                    	row.getChildren().add(l);
                        }else{
                            if (prp.getPartype().equals("S")){
                                row.getChildren().add(new Textbox( ));
                                }
                                if (prp.getPartype().equals("N")){
                                    row.getChildren().add(new Intbox( ));
                                    }
                                if (prp.getPartype().equals("D")){
                                    row.getChildren().add(new Datebox( ));
                                    }
                        	
                        }

                    //row.getChildren().add(new Label(prp.getContent()));
            }});
		
		repparwnd$parGrid.setModel((new ListModelList(ReportService.getRepPar(_rp.getId(),alias))));
		repparwnd.setVisible(true);



/*
		Connection c = null;
        //String source =application.getRealPath("/rept/report1.jasper");
        String source =application.getRealPath("/rept/calrep.jasper");
        java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
        try{
        	c = ConnectionPool.getConnection();
        	final byte[] buf = JasperRunManager.runReportToPdf(source, params, c);
        	final InputStream mediais = new ByteArrayInputStream(buf);
        	//JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
		    repmd = new AMedia("FirstReport.pdf", "pdf", "application/pdf", mediais);
		    iframe.setContent(repmd);
        }catch(Exception e) {
        	e.printStackTrace();
        }
*/
	}
	
	private void runRep(Report rp,Map<String, Object> params ){
		Connection c = null;
        String source =rp.getRclass();
        source	=application.getRealPath( source);
        try{
        	c = ConnectionPool.getConnection(alias);
        	final byte[] buf = JasperRunManager.runReportToPdf(source, params, c);
        	final InputStream mediais = new ByteArrayInputStream(buf);
		    repmd = new AMedia(rp.getRfname(), "pdf", "application/pdf", mediais);
		    iframe.setContent(repmd);
		    iframe.setVisible(true);
		   // srep.setVisible(false);
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
	}
	private void runRepW(Report rp,Map<String, Object> params){
		Connection c = null;
        String source =rp.getRclass();
        source	=application.getRealPath( source);
        String uri;
        try{
        	c = ConnectionPool.getConnection(alias);
        	//uri = JasperRunManager.runReportToHtmlFile(source, getParam(rp), c);
        	//System.out.println(uri);
        	//repmd = new AMedia(new File(uri),"html", "text/html");
        	JasperPrint jaspPrint = JasperFillManager.fillReport(source, params, c);
        	ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        	JRExporter exporter = new JRDocxExporter();
        	//exporter.setParameters(getParam(rp));
        	exporter.setParameter(JRExporterParameter.JASPER_PRINT ,jaspPrint);
        	exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,arrayOutputStream);
        	exporter.exportReport();
        	arrayOutputStream.close();
        	repmd = new AMedia("tst.docx", "docx", "application/docx", arrayOutputStream.toByteArray());
        	iframe.setContent(repmd);
        	//iframe.setSrc(uri);
		    iframe.setVisible(true);
		   // srep.setVisible(false);
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
	}
	
	private java.util.Map<String, Object> getParam(Report rp){
		java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
		return params;
	}
	public void onDoubleClick$repGrid(){
		if(repGrid.getSelectedIndex()>=0){
		this._rp = (Report)repGrid.getSelectedItem().getValue();
		repparwnd$parGrid.setModel((new ListModelList(ReportService.getRepPar(_rp.getId(),alias))));
		repparwnd.setVisible(true);
		}
	}
	public void onClick$btn_cn$repparwnd(){
		repparwnd.setVisible(false);
		
	}

	
	public void onClick$btn_run$repparwnd(){
		repparwnd.setVisible(false);
		Row r = null;
		java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
		for(int i=0;i<repparwnd$parGrid.getRows().getVisibleItemCount();i++){
			 r = (Row) repparwnd$parGrid.getRows().getChildren().get(i);
		//	System.out.println(r.getChildren().get(1));
			InputElement ie = (InputElement)r.getChildren().get(1);
				 
					
					
			   if (ie instanceof RefCBox){
					 if(ReportService.getRepPar( _rp.getId(),i+1,alias).equals("S")){
					     params.put(Integer.toString(i+1),((RefCBox)ie).getValue());
					 }else{
						// String str =  
						 params.put(Integer.toString(i+1),Integer.parseInt(( (RefCBox)ie).getValue()));
					 }
				}else if (ie instanceof Textbox)
				    params.put(Integer.toString(i+1),((Textbox)ie).getValue());
				else if (ie instanceof Intbox)
					params.put(Integer.toString(i+1),((Intbox)ie).getValue());
				else if (ie instanceof Datebox)
					params.put(Integer.toString(i+1),((Datebox)ie).getValue());


		}
//		System.out.println("params "+params);
		runRep(_rp,params);
	}
	public void onClick$btn_runw$repparwnd(){
		repparwnd.setVisible(false);
		Row r = null;
		java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
		for(int i=0;i<repparwnd$parGrid.getRows().getVisibleItemCount();i++){
			 r = (Row) repparwnd$parGrid.getRows().getChildren().get(i);
		//	System.out.println(r.getChildren().get(1));
			InputElement ie = (InputElement)r.getChildren().get(1);
				 
					
					
			   if (ie instanceof RefCBox){
					 if(ReportService.getRepPar( _rp.getId(),i+1,alias).equals("S")){
					     params.put(Integer.toString(i+1),((RefCBox)ie).getValue());
					 }else{
						// String str =  
						 params.put(Integer.toString(i+1),Integer.parseInt(( (RefCBox)ie).getValue()));
					 }
				}else if (ie instanceof Textbox)
				    params.put(Integer.toString(i+1),((Textbox)ie).getValue());
				else if (ie instanceof Intbox)
					params.put(Integer.toString(i+1),((Intbox)ie).getValue());
				else if (ie instanceof Datebox)
					params.put(Integer.toString(i+1),((Datebox)ie).getValue());


		}
//		System.out.println("params "+params);
		runRepW(_rp,params);
	}
	
}
