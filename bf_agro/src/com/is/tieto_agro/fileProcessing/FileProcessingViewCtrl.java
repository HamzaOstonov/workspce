package com.is.tieto_agro.fileProcessing;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;

import com.is.utils.RefCBox;

public class FileProcessingViewCtrl extends GenericForwardComposer {

	private static final long serialVersionUID = 1L;

	private int _startPageNumber = 0;
	private String alias = "";
	private String userName = "";
	private String password = "";
	private Listbox data_listbox;
	private Paging fileProcessingPaging;
	private Toolbarbutton btn_last, btn_next, btn_prev, btn_first, btn_details;
	private Div list_grid, main_form, details_div, search_div;

	private AnnotateDataBinder binder = null;
	private FileProcessing current = new FileProcessing();
	private PagingListModel model = null;
	private FileProcessingFilter filter = new FileProcessingFilter();
	private RefCBox fileId;
	private static String fileIdStatic;

	public FileProcessingViewCtrl() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		FileProcessingService fpService = new FileProcessingService();

		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.bindBean("filter", this.filter);
		binder.loadAll();

		userName = (String) session.getAttribute("un");
		password = (String) session.getAttribute("pwd");
		alias = (String) session.getAttribute("alias");

		ListModelList fileTypes = new ListModelList(fpService.getFileTypes(alias));
		fileId.setModel(fileTypes);

		data_listbox.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem row, Object data){
				FileProcessing fileProcessing = (FileProcessing) data;
				row.setValue(fileProcessing);
				row.appendChild(new Listcell(fileProcessing.getBranch()));
				row.appendChild(new Listcell(fileProcessing.getD_date().toString()));
				row.appendChild(new Listcell(fileProcessing.getAcc_cl()));
				row.appendChild(new Listcell(fileProcessing.getName_cl()));
				row.appendChild(new Listcell(fileProcessing.getSumma().toString()));
				if (fileProcessing.getState_id() == Constants.STATE_ERROR) {
					row.appendChild(new Listcell("Ошибка"));
				} else if (fileProcessing.getState_id() == Constants.STATE_ADDED) {
					row.appendChild(new Listcell("Введен"));
				}
			}
		});

		refreshModel(_startPageNumber);
	}

	public FileProcessingFilter getFilter() {
		return filter;
	}

	public void setFilter(FileProcessingFilter filter) {
		this.filter = filter;
	}

	public void onClick$btn_first() {
		data_listbox.setSelectedIndex(0);
		sendSelEvt();
	}

	public void onClick$btn_last() {
		data_listbox.setSelectedIndex(model.getSize() - 1);
		sendSelEvt();
	}

	public void onClick$btn_prev() {
		if (data_listbox.getSelectedIndex() != 0) {
			data_listbox.setSelectedIndex(data_listbox.getSelectedIndex() - 1);
			sendSelEvt();
		}
	}

	public void onClick$btn_next() {
		if (data_listbox.getSelectedIndex() != (model.getSize() - 1)) {
			data_listbox.setSelectedIndex(data_listbox.getSelectedIndex() + 1);
			sendSelEvt();
		}
	}

	private EventListener allFilesListener = new EventListener() {
		public void onEvent(Event e) {
			//if (Messagebox.ON_OK.equals(e.getName())) {
			int answer = (Integer) e.getData();
			  if (answer == Messagebox.OK) {
				FileProcessingService service = new FileProcessingService();
				service.executeTransactions(userName, password, alias, null);
				refreshModel(_startPageNumber);
			}
		}
	};

	public void onClick$btn_apply() {
		fileIdStatic = filter.getFileId();
		EventListener selectedFileListener = new EventListener() {
			public void onEvent(Event e) {
				//if (Messagebox.ON_OK.equals(e.getName())) {
				int answer = (Integer) e.getData();
					  if (answer == Messagebox.OK) {

					FileProcessingService service = new FileProcessingService();
					service.executeTransactions(userName, password, alias, Long.valueOf(fileIdStatic));
					refreshModel(_startPageNumber);
				}
			}
		};
		try {
			if (filter.getFileId() == null || filter.getFileId().equals("") || filter.getFileId().equals("0")) {
				Messagebox.show("Будут подтверждены проводки по всем файлам. Вы уверены?", "Question",
						Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, allFilesListener);
			} else {
				fileId.getText();
				Messagebox.show("Будут подтверждены проводки по файлу " + fileId.getText() + ". Вы уверены?",
						"Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, selectedFileListener);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sendSelEvt() {
		if (data_listbox.getSelectedIndex() == 0) {
			btn_first.setDisabled(true);
			btn_prev.setDisabled(true);
		} else {
			btn_first.setDisabled(false);
			btn_prev.setDisabled(false);
		}
		if (data_listbox.getSelectedIndex() == (model.getSize() - 1)) {
			btn_next.setDisabled(true);
			btn_last.setDisabled(true);
		} else {
			btn_next.setDisabled(false);
			btn_last.setDisabled(false);
		}
		SelectEvent evt = new SelectEvent("onSelect", data_listbox, data_listbox.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onDoubleClick$list_grid$data_listbox() {
		list_grid.setVisible(false);
		main_form.setVisible(true);
		details_div.setVisible(true);
		search_div.setVisible(false);
		btn_details.setImage("/images/folder.png");
		btn_details.setLabel(Labels.getLabel("grid"));
	}

	public void onClick$btn_details() {
		if (main_form.isVisible()) {
			main_form.setVisible(false);
			list_grid.setVisible(true);
			btn_details.setImage("/images/file.png");
			btn_details.setLabel(Labels.getLabel("back"));
		} else
			onDoubleClick$list_grid$data_listbox();
	}

	public void onClick$btn_search() {
		list_grid.setVisible(false);
		main_form.setVisible(true);
		details_div.setVisible(false);
		search_div.setVisible(true);
	}

	public void onClick$btn_cancel() {
		main_form.setVisible(false);
		list_grid.setVisible(true);
	}

	public void onClick$btn_search_accept() {
		onClick$btn_cancel();
		refreshModel(_startPageNumber);
	}

	public void onPaging$fileProcessingPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		int _pageSize = 10;
		fileProcessingPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");

		int _totalSize = model.getTotalSize(filter, alias);

		fileProcessingPaging.setTotalSize(_totalSize);

		data_listbox.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (FileProcessing) model.getElementAt(0);
			sendSelEvt();
		}
	}

	public FileProcessing getCurrent() {
		return current;
	}

	public void setCurrent(FileProcessing current) {
		this.current = current;
	}

}
