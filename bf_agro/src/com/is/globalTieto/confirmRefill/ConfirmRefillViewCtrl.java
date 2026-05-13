package com.is.globalTieto.confirmRefill;

import com.is.globalTieto.Constants;
import com.is.globalTieto.utils.StringUtils;
import com.is.globalTieto.tietoModels.*;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.*;
import org.zkoss.zul.event.PagingEvent;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConfirmRefillViewCtrl extends GenericForwardComposer {

	private static final long serialVersionUID = 1L;

	private Listbox mainList;

    @Getter
	@Setter
	private ConfirmRefill current = new ConfirmRefill();
	private ConfirmRefillFilter filter;
	private final static int pageSize = 12;
	private ConfirmRefillPaging paging = new ConfirmRefillPaging(pageSize);
	private Paging confirmRefillPaging;
    private ConfirmRefillService service;

	private Map<Long, String> states;
	private Map<Long, ConfirmRefill> currents = new HashMap<Long, ConfirmRefill>();
	private String[] access;
	private String user, pass, alias;
	private int currentPageNumber = 0;

	public ConfirmRefillViewCtrl() {
		super('$', false, false);
		service = new ConfirmRefillService();
		states = service.getRefillStates();
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

        AnnotateDataBinder binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.loadAll();
		access = (String[]) this.param.get("access");
        alias = (String) session.getAttribute("alias");
		user = (String) session.getAttribute("un");
        pass = (String) session.getAttribute("pwd");
		filter = new ConfirmRefillFilter();
		if ((access[0].equals("approve"))) {
			filter.setState(BigDecimal.valueOf(Constants.REFILL_STATE_ADDED));
		} else if(access[0].equals("confirm")){
			filter.setState(BigDecimal.valueOf(Constants.REFILL_STATE_CONFIRMED));
		}

		mainList.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem row, Object data) {
				ConfirmRefill confirmRefill = (ConfirmRefill) data;
				row.setValue(confirmRefill);
				row.appendChild(new Listcell(StringUtils.secureNull(confirmRefill.getCard())));
				row.appendChild(new Listcell(StringUtils.secureNull(confirmRefill.getClient_name())));
				row.appendChild(new Listcell(StringUtils.secureNull(confirmRefill.getClient_surname())));
				row.appendChild(new Listcell(StringUtils.secureNull(confirmRefill.getTransaction_amnt())));
				row.appendChild(new Listcell(confirmRefill.getState() != null
						? states.get(confirmRefill.getState().longValue()) : ""));

				currents.put(confirmRefill.getId(), confirmRefill);

				Listcell listcell = new Listcell();
				//Кнопка утверждения
                Button confirmBtn = new Button();
                confirmBtn.setLabel(Constants.CONFIRM_BTN_LABEL);
                confirmBtn.addEventListener(Events.ON_CLICK, confirmAction);
                confirmBtn.setAttribute("id", confirmRefill.getId());
                //Кнопка удаления
                Button deleteBtn = new Button();
                deleteBtn.setLabel(Constants.DELETE_BTN_LABEL);
                deleteBtn.addEventListener(Events.ON_CLICK, deleteAction);
                deleteBtn.setAttribute("id", confirmRefill.getId());
                //Кнопка проводки
                Button postBtn = new Button();
                postBtn.setLabel(Constants.POST_BTN_LABEL);
                postBtn.addEventListener(Events.ON_CLICK, postAction);
                postBtn.setAttribute("id", confirmRefill.getId());

                if(access[0].equals("approve")) {
                    listcell.appendChild(confirmBtn);
                }else if(access[0].equals("confirm")){
                    listcell.appendChild(postBtn);
                }
				listcell.appendChild(deleteBtn);

				row.appendChild(listcell);
			}

		});
		
		refreshModel();
	}

    private EventListener confirmAction = new EventListener() {

        @Override
        public void onEvent(Event event) {
            long id = (Long) event.getTarget().getAttribute("id");
            service.setRefillState(id, Constants.REFILL_STATE_CONFIRMED);

            refreshModel();
        }

    };

    private EventListener deleteAction = new EventListener() {

        @Override
        public void onEvent(Event event) {
            long id = (Long) event.getTarget().getAttribute("id");
            service.setRefillState(id, Constants.REFILL_STATE_DELETED);

            refreshModel();
        }

    };

    private EventListener postAction = new EventListener() {

        @Override
        public void onEvent(Event event) {
            long id = (Long) event.getTarget().getAttribute("id");
            ResponseInfo response;

            ConfirmRefill current = currents.get(id);

            try {
                long operationId = service.getOperationId(current.getCard_acct(), current.getIs_cash_payment());
                String accName = service.getAccNameByOperationId(operationId);
                if(operationId == 1003 || operationId == 1004){
                    BigDecimal initialSum = current.getTransaction_amnt();
                    long sumValueEo = initialSum.longValue() * service.getCource("840", 4);
                    long sumValueCb = initialSum.longValue() * service.getCource("840", 1);
                    current.setTransaction_amnt(BigDecimal.valueOf(sumValueEo));
                    service.bankRefill(current, 1006, accName, user, pass, alias);
                    current.setTransaction_amnt(BigDecimal.valueOf(sumValueEo - sumValueCb));
                    service.bankRefill(current, 1007, accName, user, pass, alias);
                    current.setTransaction_amnt(initialSum);
                }
                service.bankRefill(current, operationId , accName, user, pass, alias);

                response = service.tietoRefill(current.getCard_acct(), current.getTransaction_amnt().longValue());
                if(response.getResponse_code().longValue() == 0){
                    alert("Пополнение прошло успешно");
                }else{
                    alert("Ошибка: " + response.getError_description());
                }
            } catch (SQLException e) {
                alert("Ошибка: " + e.getMessage());
            } catch (CloneNotSupportedException e) {
                alert("Ошибка: " + e.getMessage());
            }



            refreshModel();
        }

    };

	private void refreshModel() {
		confirmRefillPaging.setPageSize(pageSize);
		confirmRefillPaging.setTotalSize(paging.getTotalSize(filter));
		mainList.setModel(new BindingListModelList(paging.getPageData(currentPageNumber, filter), true));
	}

	public void onPaging$confirmRefillPaging(ForwardEvent event) {
		PagingEvent pagingEvent = (PagingEvent) event.getOrigin();
		currentPageNumber = pagingEvent.getActivePage();
		refreshModel();
	}
}
