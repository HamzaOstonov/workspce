package com.is.ti_terminal_accounts;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Listbox;

public class Terminal_account_view_controller  extends GenericForwardComposer
{
	private static final long	serialVersionUID	= -2576724607271208104L;
	
	private Listbox accounts;
	private Window edit_wnd;
	private Textbox edit_wnd$terminal_id, edit_wnd$branch, edit_wnd$account_id;
	private Boolean edit_wnd_in_edit_mode = true;
	private Terminal_account old_ta = null;
	
	@Override
    public void doAfterCompose(Component comp) throws Exception
    {
		super.doAfterCompose(comp);
		
		accounts.setItemRenderer(new ListitemRenderer()
		{
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception
            {
            	Terminal_account terminal_account = (Terminal_account)data;
            	row.appendChild(new Listcell(terminal_account.getTerminal_id()));
            	row.appendChild(new Listcell(terminal_account.getBranch()));
            	row.appendChild(new Listcell(terminal_account.getAccount()));
            	
            	row.setAttribute("terminal_account", terminal_account);
            	row.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener()
            	{
					@Override
					public void onEvent(Event event)
							throws Exception 
					{
						Terminal_account terminal_account = 
							(Terminal_account)event.getTarget().getAttribute(
									"terminal_account");
						edit_wnd$terminal_id.setValue(terminal_account.getTerminal_id());
						edit_wnd$branch.setValue(terminal_account.getBranch());
						edit_wnd$account_id.setValue(terminal_account.getAccount());
						edit_wnd_in_edit_mode = true;
						old_ta = terminal_account;
						edit_wnd.setVisible(true);
					}            		 
            	});
            }
		});
		
		accounts.setModel(new BindingListModelList(
						Terminal_accounts_service.get_terminal_accounts(),
						false
						));
    }
	
	public void onClick$add()
	{
		edit_wnd$terminal_id.setValue("");
		edit_wnd$branch.setValue("");
		edit_wnd$account_id.setValue("");
		edit_wnd_in_edit_mode = false;
		edit_wnd.setVisible(true);
	}
	
	public void onClick$save$edit_wnd()
	{
		if (edit_wnd_in_edit_mode)
		{
			try
			{
				Terminal_accounts_service.update_terminal_account(
						old_ta,
						new Terminal_account(
								edit_wnd$terminal_id.getValue(),
								edit_wnd$branch.getValue(),
								edit_wnd$account_id.getValue()
								));
				edit_wnd.setVisible(false);
				accounts.setModel(new BindingListModelList(
						Terminal_accounts_service.get_terminal_accounts(),
						false
						));
			}catch (Exception e)
			{
				alert(e.getMessage());
			}
		}
		else
		{
			try
			{
				Terminal_accounts_service.create_terminal_account(
						new Terminal_account(
								edit_wnd$terminal_id.getValue(),
								edit_wnd$branch.getValue(),
								edit_wnd$account_id.getValue()
								));
				edit_wnd.setVisible(false);
				accounts.setModel(new BindingListModelList(
						Terminal_accounts_service.get_terminal_accounts(),
						false
						));
			}catch (Exception e)
			{
				alert(e.getMessage());
			}
		}
	}
}
