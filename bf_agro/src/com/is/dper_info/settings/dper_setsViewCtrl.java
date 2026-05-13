package com.is.dper_info.settings;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Toolbarbutton;
import org.zkoss.zul.event.ListDataListener;

import com.is.dper_info.service.SprService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;

public class dper_setsViewCtrl extends GenericForwardComposer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Window sets_Window, sets_groupsWindow, sets_sprWindow,
			sets_scalesWindow, addRowWindow, scale_editWindow, sets_accWindow,
			sets_opersWindow;

	private Listbox sets_Window$avail, sets_Window$all,
			sets_groupsWindow$groupsBox, sets_groupsWindow$countrBox,
			sets_groupsWindow$exceptionsBox, sets_sprWindow$ss_dper,
			sets_sprWindow$ss_dper_dop, sets_scalesWindow$scales,
			sets_accWindow$account_desc, sets_opersWindow$opers;

	private RefCBox sets_scalesWindow$veoper_sc,//
			sets_scalesWindow$val_sc, // sets_scalesWindow
			sets_scalesWindow$kind_sc, //
			sets_scalesWindow$group_sc, //

			sets_accWindow$mbranch_acc,
			sets_accWindow$veoper_acc,
			sets_accWindow$val_acc, sets_accWindow$acctype,

			sets_opersWindow$veoper_opers,
			sets_opersWindow$val_opers,
			sets_opersWindow$kind_opers,
			sets_opersWindow$konvert_opers,
			sets_opersWindow$typeoper,
			sets_opersWindow$typedoc,
			sets_opersWindow$facc_d,
			sets_opersWindow$val_d,
			sets_opersWindow$cashsym,
			sets_opersWindow$facc_c,
			sets_opersWindow$val_c,
			sets_opersWindow$cashsymd,
			sets_opersWindow$fx_deal, sets_opersWindow$sumproc;

	private Textbox sets_groupsWindow$new_group,

	addRowWindow$value1_new, addRowWindow$value2_new, addRowWindow$coment_new,

	scale_editWindow$scale_edit, scale_editWindow$percent_edit,
			scale_editWindow$summa_edit, scale_editWindow$perc_perev_edit,
			scale_editWindow$summa_perev_edit,
			scale_editWindow$perc_kommis_edit,
			scale_editWindow$summa_kommis_edit,

			sets_accWindow$acc_tbox,

			sets_opersWindow$id_oper, sets_opersWindow$purpose,
			sets_opersWindow$code_nazn;
	private Label sets_opersWindow$naznLabel;
	private String alias;
	private String branch;
	private int[] spr_codes = { 3, 8, 9, 10, 11, 13, 15 };

	public dper_setsViewCtrl() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		alias = (String) session.getAttribute("alias");
		branch = (String) session.getAttribute("branch");
		sets_Window$avail.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) throws Exception {
				Ss_dper_dop avail = (Ss_dper_dop) data;
				row.setValue(avail);
				row.appendChild(new Listcell(Integer.toString(avail.getId())));
				row.appendChild(new Listcell(avail.getValue1()));
				row.appendChild(new Listcell(avail.getValue2()));
			}
		});
		sets_Window$all.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) throws Exception {
				RefData all = (RefData) data;
				row.setValue(all);
				row.appendChild(new Listcell(all.getData()));
				row.appendChild(new Listcell(all.getLabel()));
			}
		});
	}

	/****************************************************************************************
	 * 
	 * currencies & veoper
	 * 
	 *************************************************************************************** */
	public void onClick$sets_veoper() {
		sets_Window$avail.setModel(new ListModelList(dper_setsService.getSS_dper_dop("6",alias)));
		sets_Window$all.setModel(new ListModelList(dper_setsService.getAllVeoper(alias)));
		sets_Window.setTitle("Денеж. переводы");
		sets_Window.setVisible(true);
	}

	public void onClick$sets_currncy() {
		sets_Window$avail.setModel(new ListModelList(dper_setsService.getSS_dper_dop("7",alias)));
		sets_Window$all.setModel(new ListModelList(dper_setsService.getAllCurrency(alias)));
		sets_Window.setTitle("Валюты");
		sets_Window.setVisible(true);
	}
	public void onClick$add_item$sets_Window() {
		if(sets_Window$all.getSelectedItem() == null){
			alert("Выберите из списка");
			return;
		}
		String title = sets_Window.getTitle();
		RefData data = (RefData) sets_Window$all.getSelectedItem().getValue();
		if (data == null) {
			return;
		}
		try {
			Messagebox.show(
					"Добавить в списка " + title + " " + data.getLabel() + "?",
					"Question", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION, new EventListener() {
						@Override
						public void onEvent(Event e) throws Exception {
							if ("onCancel".equals(e.getName())) {
								return;
							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (title.equals("Денеж. переводы")) {
			dper_setsService.insert_dper_dop(6,data, alias);
			sets_Window$avail.setModel(new ListModelList(dper_setsService.getSS_dper_dop("6",alias)));
			sets_Window$all.setModel(new ListModelList(dper_setsService.getAllVeoper(alias)));
		} else if (title.equals("Валюты")) {
			dper_setsService.insert_dper_dop(7,data, alias);
			sets_Window$avail.setModel(new ListModelList(dper_setsService.getSS_dper_dop("7",alias)));
			sets_Window$all.setModel(new ListModelList(dper_setsService
					.getAllCurrency(alias)));
		}
	}

	public void onClick$remove_item$sets_Window() {
		if(sets_Window$avail.getSelectedItem() == null){
			alert("Выберите из списка");
			return;
		}
		String title = sets_Window.getTitle();
		Ss_dper_dop data = (Ss_dper_dop) sets_Window$avail.getSelectedItem().getValue();
		if (data == null) {
			return;
		}
		try {
			Messagebox.show(
					"Удалить из списка " + title + " " + data.getValue1() + "?",
					"Question", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION, new EventListener() {
						@Override
						public void onEvent(Event e) throws Exception {
							if ("onCancel".equals(e.getName())) {
								return;
							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (title.equals("Денеж. переводы")) {
			dper_setsService.remove_dper_dop(data, alias);
			sets_Window$avail.setModel(new ListModelList(dper_setsService.getSS_dper_dop("6",alias)));
			sets_Window$all.setModel(new ListModelList(dper_setsService.getAllVeoper(alias)));
		} else if (title.equals("Валюты")) {
			dper_setsService.remove_dper_dop(data, alias);
			sets_Window$avail.setModel(new ListModelList(SprService
					.getCurrency(alias)));
			sets_Window$all.setModel(new ListModelList(dper_setsService
					.getAllCurrency(alias)));
		}
	}

	/***********************************************************************************************************
	 * 
	 * Настройка групп
	 * 
	 ********************************************************************************************* 888888**************
	 */

	public void onClick$sets_groups() {
		sets_groupsWindow$groupsBox.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) throws Exception {
				RefData availCurncy = (RefData) data;
				row.setValue(availCurncy);
				row.appendChild(new Listcell(availCurncy.getData()));
				row.appendChild(new Listcell(availCurncy.getLabel()));
			}
		});
		sets_groupsWindow$countrBox.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) throws Exception {
				RefData availCurncy = (RefData) data;
				row.setValue(availCurncy);
				row.appendChild(new Listcell(availCurncy.getData()));
				row.appendChild(new Listcell(availCurncy.getLabel()));
			}
		});
		sets_groupsWindow$exceptionsBox.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) throws Exception {
				RefData availCurncy = (RefData) data;
				row.setValue(availCurncy);
				row.appendChild(new Listcell(availCurncy.getData()));
				row.appendChild(new Listcell(availCurncy.getLabel()));
			}
		});
		refreshGroups();
		sets_groupsWindow.setVisible(true);
	}

	public void onSelect$groupsBox$sets_groupsWindow() {
		String reg_id = sets_groupsWindow$groupsBox.getSelectedItem()
				.getValue().toString();

		sets_groupsWindow$countrBox.setModel(new ListModelList(dper_setsService
				.getGroupCountries(reg_id, alias)));
	}

	public void onClick$remove_cntr$sets_groupsWindow() {
		String str_id = sets_groupsWindow$countrBox.getSelectedItem()
				.getValue().toString();
		try {
			Messagebox.show("Удалить " + str_id + "?", "Question",
					Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
					new EventListener() {
						@Override
						public void onEvent(Event e) throws Exception {
							if ("onCancel".equals(e.getName())) {
								return;
							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dper_setsService.removeCountry(str_id, alias);
		refreshGroups();
	}

	public void onClick$add_cntr$sets_groupsWindow() {
		String str_id = sets_groupsWindow$countrBox.getSelectedItem()
				.getValue().toString();
		String reg_id = sets_groupsWindow$groupsBox.getSelectedItem()
				.getValue().toString();
		if (str_id.equals("")) {
			return;
		}
		try {
			Messagebox.show("Добавить " + str_id + "в группу " + reg_id + "?",
					"Question", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION, new EventListener() {
						@Override
						public void onEvent(Event e) throws Exception {
							if ("onCancel".equals(e.getName())) {
								return;
							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dper_setsService.insertCountry(reg_id, str_id, alias);
		refreshGroups();
	}

	public void onClisk$btn_addGroup$sets_groupsWindow() {
		String newGroup = sets_groupsWindow$new_group.getValue().trim();
		if (newGroup.length() == 0) {
			alert("Введите название новой группы");
			return;
		}
		List<RefData> groupsList = SprService.getGroups(alias);
		for (RefData gr : groupsList) {
			if (gr.getLabel().equals(newGroup)) {
				alert("Группа с таким названием есть в списке");
				return;
			}
		}
		dper_setsService.insertGroup(newGroup, alias);
		refreshGroups();
	}

	public void onClisk$btn_deleteGroup$sets_groupsWindow() {
		String group_id = sets_groupsWindow$groupsBox.getSelectedItem()
				.getValue().toString();
		List<RefData> group = dper_setsService.getGroupCountries(group_id,
				alias);

		try {
			Messagebox.show("Удалить группу " + group_id + "?", "Question",
					Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
					new EventListener() {
						@Override
						public void onEvent(Event e) throws Exception {
							if ("onCancel".equals(e.getName())) {
								return;
							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (!group.isEmpty()) {
			alert("Сеачала удалите страны из этой группы!");
			return;
		}
		dper_setsService.deleteGroup(group_id, alias);
		refreshGroups();
	}

	private void refreshGroups() {
		sets_groupsWindow$groupsBox.setModel(new ListModelList(SprService
				.getGroups(alias)));
		sets_groupsWindow$countrBox.setModel(new ListModelList(SprService
				.getCountries(alias)));
		sets_groupsWindow$exceptionsBox.setModel(new ListModelList(
				dper_setsService.getSortStr(alias)));
	}

	/************************************************************************************
	 * 
	 * Настройка справочников
	 * 
	 *********************************************************************************** */
	public void onClick$sets_spr() {
		sets_sprWindow$ss_dper.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) throws Exception {

				RefData ss_dper = (RefData) data;
				row.setValue(ss_dper);
				row.appendChild(new Listcell(ss_dper.getData()));
				row.appendChild(new Listcell(ss_dper.getLabel()));
			}
		});
		sets_sprWindow$ss_dper_dop.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) throws Exception {
				Ss_dper_dop ss_dper_dop = (Ss_dper_dop) data;
				row.setValue(ss_dper_dop);
				row.appendChild(new Listcell(Integer.toString(ss_dper_dop
						.getId())));
				row.appendChild(new Listcell(ss_dper_dop.getValue1()));
				row.appendChild(new Listcell(ss_dper_dop.getValue2()));
				row.appendChild(new Listcell(ss_dper_dop.getComent()));
				row.appendChild(new Listcell(ss_dper_dop.getState()));
			}
		});
		sets_sprWindow$ss_dper.setModel(new ListModelList(dper_setsService
				.getSS_dper(alias)));
		sets_sprWindow$ss_dper_dop.setModel(new ListModelList(dper_setsService
				.getSS_dper_dop("0", alias)));
		sets_sprWindow.setVisible(true);
	}

	private boolean canEdit(int data) {
		for (int i : spr_codes) {
			if (i == data)
				return true;
		}
		return false;
	}

	public void onSelect$ss_dper$sets_sprWindow() {
		RefData data = (RefData) sets_sprWindow$ss_dper.getSelectedItem()
				.getValue();
		sets_sprWindow$ss_dper_dop.setModel(new ListModelList(dper_setsService
				.getSS_dper_dop(data.getData(), alias)));

	}

	public void onClick$btn_addSpr$sets_sprWindow() {
		RefData refData = (RefData) sets_sprWindow$ss_dper.getSelectedItem()
				.getValue();
		if (!canEdit(Integer.parseInt(refData.getData()))) {
			alert("Этот справочник нельзя редактировать");
			return;
		}
		addRowWindow.setTitle("Добавить");
		addRowWindow.setVisible(true);
	}

	public void onClick$btn_editSpr$sets_sprWindow() {
		RefData refData = (RefData) sets_sprWindow$ss_dper.getSelectedItem()
				.getValue();
		if (!canEdit(Integer.parseInt(refData.getData()))) {
			alert("Этот справочник нельзя редактировать");
			return;
		}
		addRowWindow.setTitle("Редактировать");
		addRowWindow.setVisible(true);
	}

	public void onclick$btn_deleteSpr$sets_sprWindow() {
		RefData refData = (RefData) sets_sprWindow$ss_dper.getSelectedItem()
				.getValue();
		Ss_dper_dop dper_dop = (Ss_dper_dop) sets_sprWindow$ss_dper_dop
				.getSelectedItem().getValue();
		if (!canEdit(Integer.parseInt(refData.getData()))) {
			alert("Этот справочник нельзя редактировать");
			return;
		}
		try {
			Messagebox.show(
					"вы уверены, что хотите удалить запись из спавочника"
							+ refData.getLabel() + "?", "", Messagebox.OK
							| Messagebox.CANCEL, Messagebox.QUESTION,
					new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							if (event.getName().equals("onCancel")) {
								return;
							}
							return;
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dper_setsService.deletespr(dper_dop.getRowid(), alias);
		onSelect$ss_dper$sets_sprWindow();
	}

	public void onClick$btn_newRow$addRowWindow() {
		if (addRowWindow$value1_new.getValue() == null
				|| addRowWindow$value1_new.getValue().length() != 0
				|| addRowWindow$value2_new.getValue() == null
				|| addRowWindow$value2_new.getValue().length() != 0) {
			alert("пустые поля!");
			return;
		}
		Ss_dper_dop dper_dop = (Ss_dper_dop) sets_sprWindow$ss_dper_dop
				.getSelectedItem().getValue();
		if (addRowWindow.getTitle().equals("Добавить")) {
			dper_setsService.insertspr(dper_dop, alias);
		} else if (addRowWindow.getTitle().equals("Редактировать")) {
			dper_setsService.updatespr(dper_dop, alias);
		}
		addRowWindow.setVisible(false);
		onSelect$ss_dper$sets_sprWindow();
	}

	/**************************************************************************************
	 * 
	 * 
	 *************************************************************************************** */
	public void onClick$sets_scales() {
		sets_scalesWindow$veoper_sc.setModel(new ListModelList(SprService
				.getVeoper(alias)));
		sets_scalesWindow$val_sc.setModel(new ListModelList(SprService
				.getCurrency(alias)));
		sets_scalesWindow$kind_sc.setModel(new ListModelList(SprService
				.getKind(alias)));
		sets_scalesWindow$group_sc.setModel(new ListModelList(SprService
				.getGroups(alias)));
		sets_scalesWindow$scales.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem row, Object data) throws Exception {
				Scales scale = (Scales) data;
				row.setValue(scale);
				row.appendChild(new Listcell(Long.toString(scale.getScale())));
				row.appendChild(new Listcell(
						Double.toString(scale.getPercent())));
				row.appendChild(new Listcell(Long.toString(scale.getSumma())));
				row.appendChild(new Listcell(Long.toString(scale
						.getCommission_profit_percent())));
				row.appendChild(new Listcell(Long.toString(scale
						.getCommission_profit_summa())));
				row.appendChild(new Listcell(Long.toString(scale
						.getProfit_percent())));
				row.appendChild(new Listcell(Long.toString(scale
						.getProfit_summa())));
			}
		});
		sets_scalesWindow.setVisible(true);
	}

	public void onSelect$veoper_sc$sets_scalesWindow() {
		setModelScale();
	}

	public void onSelect$val_sc$sets_scalesWindow() {
		setModelScale();
	}

	public void onSelect$kind_sc$sets_scalesWindow() {
		setModelScale();
	}

	public void onSelect$group_sc$sets_scalesWindow() {
		setModelScale();
	}

	public Scales getComboScale() {
		Scales scale = new Scales();
		scale.setVeoper(sets_scalesWindow$veoper_sc.getValue());
		scale.setCurrency(sets_scalesWindow$val_sc.getValue());
		scale.setKind(Long.parseLong(sets_scalesWindow$kind_sc.getValue()));
		scale.setRegion(Long.parseLong(sets_scalesWindow$group_sc.getValue()));
		return scale;
	}

	public void setModelScale() {
		if (sets_scalesWindow$veoper_sc.getValue() != null
				&& sets_scalesWindow$veoper_sc.getValue().length() != 0
				&& sets_scalesWindow$val_sc.getValue() != null
				&& sets_scalesWindow$val_sc.getValue().length() != 0
				&& sets_scalesWindow$kind_sc.getValue() != null
				&& sets_scalesWindow$kind_sc.getValue().length() != 0
				&& sets_scalesWindow$group_sc.getValue() != null
				&& sets_scalesWindow$group_sc.getValue().length() != 0) {
			sets_scalesWindow$scales.setModel(new ListModelList(
					dper_setsService.getScales(getComboScale(), alias)));
		}
	}

	public void onClick$edit_scale$sets_scalesWindow() {
		Scales scale = (Scales) sets_scalesWindow$scales.getSelectedItem()
				.getValue();
		scale_editWindow$scale_edit.setValue(Long.toString(scale.getScale()));
		scale_editWindow$percent_edit.setValue(Double.toString(scale
				.getPercent()));
		scale_editWindow$summa_edit.setValue(Long.toString(scale.getSumma()));
		scale_editWindow$perc_perev_edit.setValue(Long.toString(scale
				.getProfit_percent()));
		scale_editWindow$summa_perev_edit.setValue(Long.toString(scale
				.getProfit_summa()));
		scale_editWindow$perc_kommis_edit.setValue(Long.toString(scale
				.getCommission_profit_percent()));
		scale_editWindow$summa_kommis_edit.setValue(Long.toString(scale
				.getCommission_profit_summa()));
		scale_editWindow.setTitle("Редактироавание шкалы");
		scale_editWindow.setVisible(true);
	}

	public void onClick$new_scale$sets_scalesWindow() {
		scale_editWindow$scale_edit.setValue("");
		scale_editWindow$percent_edit.setValue("");
		scale_editWindow$summa_edit.setValue("");
		scale_editWindow$perc_perev_edit.setValue("");
		scale_editWindow$summa_perev_edit.setValue("");
		scale_editWindow$perc_kommis_edit.setValue("");
		scale_editWindow$summa_kommis_edit.setValue("");
		scale_editWindow.setTitle("Новая шкала");
		scale_editWindow.setVisible(true);
	}

	public void onClick$btn_scale_edit$scale_editWindow() {
		Scales scale_edit = new Scales();
		if (scale_editWindow.getTitle().equals("Редактироавание шкалы")) { // edit
																			// scale
			Scales scale = (Scales) sets_scalesWindow$scales.getSelectedItem()
					.getValue();

			scale_edit.setRowid(scale.getRowid());
			scale_edit.setScale(Long.parseLong(scale_editWindow$scale_edit
					.getValue()));
			scale_edit.setSumma(Long.parseLong(scale_editWindow$summa_edit
					.getValue()));
			scale_edit.setPercent(Long.parseLong(scale_editWindow$percent_edit
					.getValue()));
			scale_edit.setProfit_percent(Long
					.parseLong(scale_editWindow$perc_perev_edit.getValue()));
			scale_edit.setProfit_summa(Long
					.parseLong(scale_editWindow$summa_perev_edit.getValue()));
			scale_edit.setCommission_profit_percent(Long
					.parseLong(scale_editWindow$perc_kommis_edit.getValue()));
			scale_edit.setCommission_profit_summa(Long
					.parseLong(scale_editWindow$summa_kommis_edit.getValue()));
			dper_setsService.updateScales(scale_edit, alias);
		} else if (scale_editWindow.getTitle().equals("Новая шкала")) {// new
																		// scale
			Scales tmp = getComboScale();
			scale_edit.setVeoper(tmp.getVeoper());
			scale_edit.setCurrency(tmp.getCurrency());
			scale_edit.setKind(tmp.getKind());
			scale_edit.setRegion(tmp.getRegion());
			scale_edit.setScale(Long.parseLong(scale_editWindow$scale_edit
					.getValue()));
			scale_edit.setSumma(Long.parseLong(scale_editWindow$summa_edit
					.getValue()));
			scale_edit.setPercent(Long.parseLong(scale_editWindow$percent_edit
					.getValue()));
			scale_edit.setProfit_percent(Long
					.parseLong(scale_editWindow$perc_perev_edit.getValue()));
			scale_edit.setProfit_summa(Long
					.parseLong(scale_editWindow$summa_perev_edit.getValue()));
			scale_edit.setCommission_profit_percent(Long
					.parseLong(scale_editWindow$perc_kommis_edit.getValue()));
			scale_edit.setCommission_profit_summa(Long
					.parseLong(scale_editWindow$summa_kommis_edit.getValue()));
			dper_setsService.insertScales(scale_edit, alias);
		}
		scale_editWindow.setVisible(false);
		setModelScale();
	}

	public void onClick$delete_scale$sets_scalesWindow() {
		final Scales scale = (Scales) sets_scalesWindow$scales
				.getSelectedItem().getValue();
		try {
			Messagebox.show(
					"вы уверены, что хотите удалить запись из таблицы шкал?",
					"", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
					new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							if (event.getName().equals("onOk")) {
								dper_setsService.deleteScales(scale, alias);
								setModelScale();
							}
							return;
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/***********************************************************************************
	 * 
	 * sets_acc
	 * 
	 ************************************************************************************* */
	public void onClick$sets_acc() {
		sets_accWindow$mbranch_acc.setModel(new ListModelList(dper_setsService
				.getMbranches(alias)));
		sets_accWindow$veoper_acc.setModel(new ListModelList(SprService
				.getVeoper(alias)));
		sets_accWindow$val_acc.setModel(new ListModelList(SprService
				.getCurrency(alias)));
		sets_accWindow$acctype.setModel(new ListModelList(dper_setsService
				.getAccountType(alias)));

		sets_accWindow$account_desc.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem row, Object data) throws Exception {
				AccountType accounts_desc = (AccountType) data;
				row.setValue(accounts_desc);
				row.appendChild(new Listcell(accounts_desc.getAcc()));
				row.appendChild(new Listcell(accounts_desc.getName()));
			}
		});
		sets_accWindow.setVisible(true);
	}

	public void onSelect$mbranch_acc$sets_accWindow() {
		setModelAcc();
	}

	public void onSelect$veoper_acc$sets_accWindow() {
		setModelAcc();
	}

	public void onSelect$val_acc$sets_accWindow() {
		setModelAcc();
	}

	private void setModelAcc() {
		if (sets_accWindow$mbranch_acc == null
				&& sets_accWindow$mbranch_acc.getValue().length() == 0
				&& sets_accWindow$veoper_acc == null
				&& sets_accWindow$veoper_acc.getValue().length() == 0
				&& sets_accWindow$val_acc == null
				&& sets_accWindow$val_acc.getValue().length() == 0) {
			return;
		}
		sets_accWindow$account_desc.setModel(new ListModelList(dper_setsService
				.getAccounts(branch, sets_accWindow$mbranch_acc.getValue(),
						sets_accWindow$veoper_acc.getValue(),
						sets_accWindow$val_acc.getValue(), alias)));
	}

	public void onSelect$account_desc$sets_accWindow() {
		AccountType selItem = (AccountType) sets_accWindow$account_desc
				.getSelectedItem().getValue();
		sets_accWindow$acc_tbox.setValue(selItem.getAcc());
		sets_accWindow$acctype
				.setSelecteditem(Integer.toString(selItem.getId()));
	}

	public void onClick$add_acc$sets_accWindow() {
		boolean isOk = true;
		if (sets_accWindow$acc_tbox.getValue().length() == 0
				|| sets_accWindow$acctype.getValue() == null
				|| sets_accWindow$acctype.getValue().length() == 0) {
			alert("Строки не должны быть пустыми!");
			return;
		}
		if (!dper_setsService.checkAcc(sets_accWindow$acc_tbox.getValue(),
				alias)) {
			alert("Счет " + sets_accWindow$acc_tbox.getValue()
					+ " не существует или не утвержден");
			return;
		}
		isOk = dper_setsService.insertDper_Acc(branch,
				sets_accWindow$mbranch_acc.getValue(),
				sets_accWindow$veoper_acc.getValue(),
				sets_accWindow$val_acc.getValue(),
				sets_accWindow$acc_tbox.getValue(),
				sets_accWindow$acctype.getValue(), alias);
		if (!isOk) {
			alert("Ошибка!Что-то пошло не так");
			return;
		}
		setModelAcc();
	}

	public void onClick$edit_acc$sets_accWindow() {
		AccountType selItem = (AccountType) sets_accWindow$account_desc
				.getSelectedItem().getValue();
		boolean isOk = true;
		if (!dper_setsService.checkAcc(selItem.getAcc(), alias)) {
			alert("Нет такого счета");
			return;
		}
		isOk = dper_setsService.updateDper_Acc(
				sets_accWindow$acc_tbox.getValue(),
				sets_accWindow$acctype.getValue(), selItem.getRowid(), alias);
		if (!isOk) {
			alert("Ошибка!Что-то пошло не так");
			return;
		}
		setModelAcc();
	}

	public void onClick$delete_acc$sets_accWindow() {
		boolean isOk = true;
		AccountType selItem = (AccountType) sets_accWindow$account_desc
				.getSelectedItem().getValue();
		final String rowid = selItem.getRowid();
		try {
			Messagebox.show(
					"вы уверены, что хотите удалить запись из таблицы шкал?",
					"", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
					new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							if (event.getName().equals("onCancel")) {
								return;
							}

						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isOk = dper_setsService.deleteDper_Acc(rowid, alias);
		if (!isOk) {
			alert("Ошибка!Что-то пошло не так");
			return;
		}
		setModelAcc();
	}

	/************************************************************************************
	 * Настройка операций
	 * 
	 ************************************************************************************** */
	public void onClick$sets_opers() {
		sets_opersWindow$naznLabel
				.setValue("#DP Ден.пер. #IO Отправка/Получеие #DATE Дата #FIO ФИО #KO Котрагент #MTSN MTSN #SNP Пасспорт (# и код англ. буквами)");
		sets_opersWindow$veoper_opers.setModel(new ListModelList(SprService
				.getVeoper(alias)));
		sets_opersWindow$val_opers.setModel(new ListModelList(SprService
				.getCurrency(alias)));
		sets_opersWindow$kind_opers.setModel(new ListModelList(SprService
				.getKind(alias)));
		sets_opersWindow$konvert_opers.setModel(new ListModelList(
				dper_setsService.getKonvert(alias)));

		sets_opersWindow$typeoper.setModel(new ListModelList(dper_setsService
				.getTypeOper(alias)));
		sets_opersWindow$typedoc.setModel(new ListModelList(dper_setsService
				.getTypeDoc(alias)));
		sets_opersWindow$facc_d.setModel(new ListModelList(dper_setsService
				.getTypeAcc(alias)));
		sets_opersWindow$val_d.setModel(new ListModelList(SprService
				.getCurrency(alias)));
		sets_opersWindow$cashsym.setModel(new ListModelList(dper_setsService
				.getCashsym(alias)));
		sets_opersWindow$facc_c.setModel(new ListModelList(dper_setsService
				.getTypeAcc(alias)));
		sets_opersWindow$val_c.setModel(new ListModelList(SprService
				.getCurrency(alias)));
		sets_opersWindow$sumproc.setModel(new ListModelList(dper_setsService
				.getSumproc(alias)));
		sets_opersWindow$fx_deal.setModel(new ListModelList(dper_setsService
				.getFxdeal(alias)));

		sets_opersWindow$opers.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem row, Object data) throws Exception {
				v_dper_oper oper = (v_dper_oper) data;
				row.setValue(oper);
				row.appendChild(new Listcell(Long.toString(oper.getId())));
				row.appendChild(new Listcell(oper.getFtypeoper()));
				row.appendChild(new Listcell(oper.getFacc_d()));
				row.appendChild(new Listcell(oper.getVal_d()));
				row.appendChild(new Listcell(oper.getFacc_c()));
				row.appendChild(new Listcell(oper.getVal_c()));
				row.appendChild(new Listcell(oper.getPurpose()));
				row.appendChild(new Listcell(oper.getPurpose()));
				row.appendChild(new Listcell(oper.getCashsym()));
				row.appendChild(new Listcell(oper.getCashsymd()));
				row.appendChild(new Listcell(Long.toString(oper.getFx_deal())));
			}
		});

		sets_opersWindow.setVisible(true);
	}

	public void onSelect$veoper_opers$sets_opersWindow() {
		setModelOpers();
	}

	public void onSelect$val_opers$sets_opersWindow() {
		setModelOpers();
	}

	public void onSelect$kind_opers$sets_opersWindow() {
		setModelOpers();
	}

	public void onSelect$konvert_opers$sets_opersWindow() {
		setModelOpers();
	}

	private void setModelOpers() {
		if (sets_opersWindow$veoper_opers.getValue() != null
				&& sets_opersWindow$veoper_opers.getValue().length() != 0
				&& sets_opersWindow$val_opers.getValue() != null
				&& sets_opersWindow$val_opers.getValue().length() != 0
				&& sets_opersWindow$kind_opers.getValue() != null
				&& sets_opersWindow$kind_opers.getValue().length() != 0
				&& sets_opersWindow$konvert_opers.getValue() != null
				&& sets_opersWindow$konvert_opers.getValue().length() != 0) {

			sets_opersWindow$opers.setModel(new ListModelList(dper_setsService
					.getOpers(sets_opersWindow$veoper_opers.getValue(),
							sets_opersWindow$val_opers.getValue(),
							sets_opersWindow$kind_opers.getValue(),
							sets_opersWindow$konvert_opers.getValue(), alias)));
		}
	}

	public void onSelect$opers$sets_opersWindow() {
		String val = null;
		List<RefData> cashsymd = null;
		v_dper_oper oper = (v_dper_oper) sets_opersWindow$opers
				.getSelectedItem().getValue();
		sets_opersWindow$id_oper.setValue(Long.toString(oper.getId()));
		sets_opersWindow$typeoper.setSelecteditem(Long.toString(oper
				.getTypeoper()));
		sets_opersWindow$typedoc.setSelecteditem(oper.getTypedoc());
		sets_opersWindow$facc_d.setSelecteditem(Long.toString(oper.getAcc_d()));
		sets_opersWindow$facc_c.setSelecteditem(Long.toString(oper.getAcc_c()));
		sets_opersWindow$val_d.setSelecteditem(oper.getVal_d());
		sets_opersWindow$val_c.setSelecteditem(oper.getVal_c());
		sets_opersWindow$cashsym.setSelecteditem(oper.getCashsym());
		sets_opersWindow$fx_deal.setSelecteditem(Long.toString(oper
				.getFx_deal()));
		sets_opersWindow$sumproc.setSelecteditem(Long.toString(oper
				.getSumproc()));
		sets_opersWindow$purpose.setValue(oper.getPurpose());
		sets_opersWindow$code_nazn.setValue(oper.getNazn());
		if ((oper.getAcc_c().equals("1") && oper.getVal_c().equals("000"))
				|| (oper.getAcc_d().equals("1") && oper.getVal_d()
						.equals("000"))) {
			val = "000";
		}
		if(oper.getCashsym()!=null){
			cashsymd = dper_setsService.getCashsymd(oper.getCashsym(), val, alias);
			sets_opersWindow$cashsymd.setModel(new ListModelList(cashsymd));
			sets_opersWindow$cashsymd.setValue(cashsymd.get(0).getLabel());
		}else {
			sets_opersWindow$cashsymd.setValue("");
		}
	}

	public void onClick$delete_oper$sets_opersWindow() {
		boolean isOk = true;
		v_dper_oper oper = (v_dper_oper) sets_opersWindow$opers
				.getSelectedItem().getValue();
		if (oper == null) {
			return;
		}
		try {
			Messagebox.show("вы уверены, что хотите операцию?", "",
					Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
					new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							if (event.getName().equals("onCancel")) {
								return;
							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isOk = dper_setsService.deleteOper(oper.getRowid(), alias);
		if (!isOk) {
			alert("Ошибка!Что-то пошло не так");
			return;
		}
		setModelOpers();
	}

	public void onClick$edit_oper$sets_opersWindow() {
		v_dper_oper oper = (v_dper_oper) sets_opersWindow$opers
				.getSelectedItem().getValue();
		boolean isOk = true;
		if (oper == null) {
			return;
		}
		isOk = dper_setsService.updateOper(getOperFromBoxes(), alias);
		if (!isOk) {
			alert("Ошибка!Что-то пошло не так");
			return;
		}
		setModelOpers();
	}

	public void onClick$add_oper$sets_opersWindow() {
		v_dper_oper oper = (v_dper_oper) sets_opersWindow$opers
				.getSelectedItem().getValue();
		boolean isOk = true;
		if (oper == null) {
			return;
		}
		isOk = dper_setsService.insertOper(getOperFromBoxes(), alias);
		if (!isOk) {
			alert("Ошибка!Что-то пошло не так");
			return;
		}
		setModelOpers();
	}

	private v_dper_oper getOperFromBoxes() {
		v_dper_oper oper = new v_dper_oper();
		oper.setVeoper(Long.parseLong(sets_opersWindow$veoper_opers.getValue()));
		oper.setCurrency(sets_opersWindow$val_opers.getValue());
		oper.setKind(Long.parseLong(sets_opersWindow$kind_opers.getValue()));
		oper.setKonvert(Long.parseLong(sets_opersWindow$konvert_opers
				.getValue()));
		oper.setId(Long.parseLong(sets_opersWindow$id_oper.getValue()));
		oper.setTypeoper(Long.parseLong(sets_opersWindow$typeoper.getValue()));
		oper.setTypedoc(sets_opersWindow$typedoc.getValue());
		oper.setAcc_c(Long.parseLong(sets_opersWindow$facc_c.getValue()));
		oper.setVal_c(sets_opersWindow$val_c.getValue());
		oper.setAcc_d(Long.parseLong(sets_opersWindow$facc_d.getValue()));
		oper.setVal_d(sets_opersWindow$val_d.getValue());
		oper.setCashsym(sets_opersWindow$cashsym.getValue());
		oper.setCashsymd(sets_opersWindow$cashsymd.getValue());
		oper.setPurpose(sets_opersWindow$purpose.getValue());
		oper.setFx_deal(Long.parseLong(sets_opersWindow$fx_deal.getValue()));
		oper.setSumproc(Long.parseLong(sets_opersWindow$sumproc.getValue()));
		oper.setNazn(sets_opersWindow$code_nazn.getValue());

		return oper;
	}
}
