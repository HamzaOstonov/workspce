package com.is.ext_protocol;

import java.text.SimpleDateFormat;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

public class Ext_protocol_vc extends GenericForwardComposer
{
	private Listbox dataGrid;
	private long deal_group;
	private long deal_id;
	private long object_id;
	
	private static SimpleDateFormat sdf_ui = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	public void doAfterCompose(Component comp) throws Exception
	{
        super.doAfterCompose(comp);
        deal_group = Long.parseLong(((String[])param.get("deal_group"))[0]);
        deal_id = Long.parseLong(((String[])param.get("deal_id"))[0]);
        object_id = Long.parseLong(((String[])param.get("object_id"))[0]);
        
        fill_grid(deal_group, deal_id, object_id);
	}
	
	private void fill_grid(long deal_group_id, 
			long s_deal_id, long obj_id)
	{
		List<Ext_protocol> protocols = Ext_protocol_service.get_protocols(
				deal_group_id, s_deal_id, obj_id);
		for (Ext_protocol current_protocol : protocols)
		{
			Listitem cur_li = new Listitem();
			Listcell lc = new Listcell(
					sdf_ui.format(current_protocol.getV_date())
					);
			cur_li.appendChild(lc);
			lc = new Listcell(
					current_protocol.getText()
					);
			cur_li.appendChild(lc);
			dataGrid.appendChild(cur_li);
		}
	}
}
