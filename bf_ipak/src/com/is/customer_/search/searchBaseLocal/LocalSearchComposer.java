package com.is.customer_.search.searchBaseLocal;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zul.impl.InputElement;

import com.is.customer_.core.composer.CustomerComposer;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.customer_.core.utils.ReferenceDecoder;
import com.is.customer_.local.LocalCustomerComposer;

public class LocalSearchComposer extends GenericForwardComposer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SessionAttributes sessionAttributes;

	private int _startPageNumber = 0;
	private int _pageSize = 15;
	private int activePage;
	//private boolean _needsTotalSizeUpdate = true;
	private int _totalSize;
	private Listbox basesearchListbox;
	private Paging searchPaging;
	private PagingListModel model;

	private Div main;
	private Div includeDiv;
	private Include searchInclude;
	private Include include;
	private CustomerComposer interactor;

	public void doAfterCompose(final Component comp) throws Exception {
		super.doAfterCompose(comp);

		initSessionAttributes();

		Component component = searchInclude.getLastChild();
		clearChildren(component);

		interactor = (CustomerComposer) component.getAttribute("_interactor");
		Customer customer = new Customer();
		customer.initBaseAttributes(sessionAttributes.getBranch());
		interactor.setCustomer(customer);
		interactor.getBinder().loadAll();
		/*comp.addEventListener(Events.ON_OK, new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				if (event.getTarget().isVisible()) {
					onClick$btnSearch();
				}
			}
		});*/
		/*searchInclude.addEventListener(Events.ON_OK, new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				if (event.getTarget().isVisible()) {
					onClick$btnSearch();
				}
			}
		});*/
		basesearchListbox.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem item, Object data) throws Exception {
				final Customer _data = (Customer) data;
				item.appendChild(new Listcell(_data.getId()));
				item.appendChild(new Listcell(ReferenceDecoder.getStateName(_data.getState())));
				/*item.appendChild(new Listcell(_data.getP_family_local()));
				item.appendChild(new Listcell(_data.getP_first_name_local()));
				item.appendChild(new Listcell(_data.getP_patronymic_local()));*/
				item.appendChild(new Listcell(_data.getP_family()));
				item.appendChild(new Listcell(_data.getP_first_name()));
				item.appendChild(new Listcell(_data.getP_patronymic()));
				item.appendChild(new Listcell(CustomerUtils.dateToString(_data.getP_birthday())));
				item.appendChild(new Listcell(_data.getP_passport_serial()));
				item.appendChild(new Listcell(_data.getP_passport_number()));
				item.appendChild(new Listcell(_data.getP_post_address()));
				item.addEventListener(Events.ON_CLICK, new EventListener() {

					@Override
					public void onEvent(Event event) throws Exception {
						main.setVisible(false);
						includeDiv.setVisible(true);
						LocalCustomerComposer composer = (LocalCustomerComposer) include.getFirstChild()
								.getAttribute("localcustomerWnd$composer");
						composer.setLocalCustomer(_data.getBranch(), _data.getId());
					}
				});
			}

		});
	}

	private void clearChildren(Component component) {
		List<Component> children = component.getChildren();
		if (children.size() == 0) {
			if (component instanceof InputElement) {
				clearInputElement((InputElement) component);
				return;
			}
		} else
			for (Component child : children)
				clearChildren(child);
	}

	private void clearInputElement(InputElement element) {
		element.setConstraint("");
		// element.setDisabled(false);
		element.setReadonly(false);
		if (element instanceof Combobox)
			((Combobox) element).setButtonVisible(true);
	}

	public void onClick$btnBack() {
		includeDiv.setVisible(false);
		main.setVisible(true);
	}

	public void onClick$btnClear() {
		Customer customer = new Customer();
		customer.initBaseAttributes(sessionAttributes.getBranch());
		interactor.refreshCustomer(customer);
		basesearchListbox.setModel(new ListModelList(new ArrayList<Object>()));
	}

	public void onPaging$searchPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		searchPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, interactor.getCustomer(),
				sessionAttributes.getSchema());
			_totalSize = model.getTotalSize(interactor.getCustomer(), sessionAttributes.getSchema());
		searchPaging.setTotalSize(_totalSize);

		basesearchListbox.setModel((ListModel) model);
		//if (model.getSize() > 0) {
			// this.current = (BusinessPartner) model.getElementAt(0);
			 //sendSelEvt();
		//}
	}

	private void sendSelEvt() {
		SelectEvent evt = new SelectEvent("onSelect", basesearchListbox, basesearchListbox.getSelectedItems());
		Events.sendEvent(evt);
	}

	private void initSessionAttributes() {
		sessionAttributes = GeneralUtils.getSessionAttributes(session);
	}

	public void onClick$btnSearch() throws InterruptedException {
		if (main.isVisible()) {
			if (interactor.getCustomer().getSign_date_open() == 2) {
				if (interactor.getCustomer().getDate_open() == null) {
					Messagebox.show("Введите начальная дата утверждения");
					return;
				}
				if (interactor.getCustomer().getDate_open1() == null) {
					Messagebox.show("Введите конечьная дата утверждения");
					return;
				}
			}
			if (interactor.getCustomer().getSign_date_close() == 2) {
				if (interactor.getCustomer().getDate_close() == null) {
					Messagebox.show("Введите начальная дата закрытия");
					return;
				}
				if (interactor.getCustomer().getDate_close1() == null) {
					Messagebox.show("Введите конечьная дата закрытия");
					return;
				}
			}

			//refreshModel(_startPageNumber); //commented 2017.12.12
			refreshModel(0);
			if (model.getSize() == 0)
				Messagebox.show("Деловой партнер не найден");
			else
			{
				//searchInclude.setVisible(false);
				basesearchListbox.setFocus(true);
			}
		}
	}
}
