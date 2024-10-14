package com.is.dper_info.reports;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bfreport.poireports.PoiReport;
import com.is.dper_info.service.SprService;
import com.is.dper_info.service.dper_infoService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;

public class ReportsController extends GenericForwardComposer {
	private Window dper_reports;
	private RefCBox branches;
	private RefCBox regions;
	private Datebox big_date;
	private Datebox from_date;
	private Datebox to_date;

	private String alias;
	private String branch;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		alias = (String) session.getAttribute("alias");
		branch = (String) session.getAttribute("branch");
		dper_reports.addEventListener("onClose", new EventListener() {

			@Override
			public void onEvent(Event event) throws Exception {
				event.stopPropagation();
				dper_reports.setVisible(false);
			}
		});
		setModels();
	}
	private void setModels(){
		Connection c = null;
		try {
		c = ConnectionPool.getConnection(alias);
		Date operday = dper_infoService.info_getday(alias);
		big_date.setValue(operday);
		from_date.setValue(operday);
		to_date.setValue(operday);
		regions.setModel(new ListModelList(SprService.getRegions(alias)));
		} catch (SQLException e){
			
		} finally {
			ConnectionPool.close(c);
		}
	}
	public void init() {
		dper_reports.setVisible(true);
	}

	public void onClick$big_filial() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("date", big_date.getValue());
		getReport("com.is.dper_info.reports.DperBigReport",
				"/reports/bigrep.xls", "bigdper_report", map);
	}

	public void onClick$btn_p_branch() {
		setParams(3,false);
	}

	public void onClick$btn_p_branches_region() {
		setParams(3,true);
	}

	public void onClick$btn_np_branch() {
		setParams(2,false);
	}

	public void onClick$btn_np_branches_region() {
		setParams(2,true);
	}

	@SuppressWarnings("null")
	private void setParams(int state,boolean allBranches) {
		String selItem =  regions.getValue();
		if (allBranches && (selItem == null || selItem.length() == 0)) {
			alert("Выберите регион");
			return;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("allBranches", allBranches);
		map.put("region_id", selItem);
		map.put("schema", alias);
		map.put("branch", branch);
		map.put("from_date", from_date.getValue());
		map.put("to_date", to_date.getValue());
		map.put("state", state);
		getReport("com.is.dper_info.reports.DperReports",
				"/reports/dper_template.xls", "dper_report", map);
	}

	public void getReport(String rep_class, String template, String out_file,
			HashMap<String, Object> params) {
		Connection c = null;
		PoiReport poireport = null;
		AMedia repmd = null;
		try {
			c = ConnectionPool.getConnection(alias);
			poireport = (PoiReport) Class.forName(rep_class).newInstance();
			repmd = poireport.getRepmd(params, c, Executions.getCurrent()
					.getDesktop().getWebApp().getRealPath(template), out_file);
			Filedownload.save(repmd.getByteData(), "application/vnd.ms-excel",
					out_file + ".xls");
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert("Банк недоступен! Попробуйте позже. "
					+ (CheckNull.isEmpty(e.getMessage()) ? CheckNull.getPstr(e)
							: e.getMessage()));
		} finally {
			ConnectionPool.close(c);
		}
	}
}
