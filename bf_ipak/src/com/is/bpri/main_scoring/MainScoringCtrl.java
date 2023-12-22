package com.is.bpri.main_scoring;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;

import com.is.bpri.utils.MyCombobox;

public class MainScoringCtrl extends GenericForwardComposer{
	
	private static final long serialVersionUID = 1L;
	private MyCombobox scoring_type;
	private Div scoring_wnd;
	private Div scoring_inc;
	private int bpr_id = 0;
	private int state = -1;
	private int bpr_type = 0;

	public MainScoringCtrl() {
		super('$',false,false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		if (this.param.get("bpr_type") != null) {
			bpr_type = Integer.parseInt(((String[]) this.param.get("bpr_type"))[0]);
		}
		scoring_type.setModel(new ListModelList(MainScoringService.getScoringModel(bpr_type)));
		if (this.param.get("template") != null) {
			bpr_id = Integer.parseInt(((String[]) this.param.get("template"))[0]);
		}
		if (this.param.get("state") != null) {
			state = Integer.parseInt(((String[]) this.param.get("state"))[0]);
		}
	}
	
	public void onInitRenderLater$scoring_type(){
		String value = MainScoringService.getValue(bpr_id);
		if(!value.equals("")){
			scoring_type.setDisabled(true);
			scoring_type.setSelecteditem(value);
			onSelect$scoring_type();
		}
	}
	
	public void onSelect$scoring_type(){
		try {
			session.setAttribute("bpr_id", bpr_id);
			session.setAttribute("bpr_state", state);
			session.setAttribute("scoring_type", Integer.parseInt(scoring_type.getValue()));
//			scoring_inc.setSrc(scoring_type.getSelectedModel()+"?template="+bpr_id+"&state="+state);
			if(scoring_wnd!=null) {
				scoring_inc.removeChild(scoring_wnd);
				scoring_wnd = null;
			}
			if(scoring_wnd==null){
				scoring_wnd = (Div) Executions.createComponents(scoring_type.getSelectedModel(), scoring_inc, null);
			} 
//			scoring_wnd.setHeight("400px");
			scoring_inc.appendChild(scoring_wnd);
//			clientAViewCtrl = (ClientAViewCtrl) scoring_wnd.getAttribute("clientmain$composer");
//			clientAViewCtrl.initFromBpr(null,null,alias);
		} catch (UiException e) {
			e.printStackTrace();
			alert(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
