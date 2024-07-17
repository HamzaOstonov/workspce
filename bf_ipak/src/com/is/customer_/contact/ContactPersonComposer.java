package com.is.customer_.contact;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menuseparator;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Window;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ISLogger;
import com.is.customer_.action.Action;
import com.is.customer_.action.ActionUtils;
import com.is.customer_.contact.service.ContactActionService;
import com.is.customer_.contact.service.RelationshipActionService;
import com.is.customer_.core.ReferenceDictionary;
import com.is.customer_.core.composer.CustomerComposer;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.customer_.local.DiffUtils;
import com.is.customer_.mapping.MappingResolver;
import com.is.customer_.sap.service.BusinessPartnerInterface;
import com.is.customer_.sap.service.BusinessPartnerMappingInterface;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.customer_.sap.service.exception.SAPDuplicationException;
import com.is.customer_.sap.service.exception.SAPException;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;

/**
 * Created by root on 11.05.2017. 11:03
 */
public class ContactPersonComposer extends GenericForwardComposer {
    private final static Logger logger = Logger.getLogger(ContactPersonComposer.class);

    private Menubar contact_menu;
    private RefCBox contact_types;
    private Checkbox isAccountantCheckbox;
    private Include include_;
    private Menuitem btnCreateContactPerson, btnConfirmContactPerson, btnCorrectContactPerson, btnCreateRelationship,
            btnConfirmRelationship, btnDeleteRelationship;
    private Div appsDiv;
    private Window appsWnd;

    private SessionAttributes sessionAttributes;
    private CustomerComposer composer;
    private ContactPersonContext context;

    private BusinessPartnerInterface sapService;
    private BusinessPartnerMappingInterface mappingService;

    private ContactActionService actionService;
    private RelationshipActionService relationsService;

    private Relationship relationship = new Relationship();
    private Menubar actions;
    private Menubar rel_actions;
    private Menubar general_menu;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        init();
        composer = (CustomerComposer) include_.getLastChild().getAttribute("customerWnd$composer");
        composer.state.setModel(new ListModelList(PersonMapDbUtils.getStates(sessionAttributes.getSchema())));
        composer.sendOnInitToAllComboboxes(composer.getSelf());
        composer.set_visible_pdl_rows(true);
        actionService = ContactActionService.getInstance(sessionAttributes);
        relationsService = RelationshipActionService.getInstance(sessionAttributes);
        String searchBranches[] = (String[]) param.get("searchBranch");
        String searchCodes[] = (String[]) param.get("searchCode");
        if (searchBranches != null && searchCodes != null) {
            //logger.info("Person from -> DB");
            //logger.error("Person from db -> " + searchBranches[0] + " " + searchCodes[0]);
            Customer customer = actionService.getCustomer(searchBranches[0], searchCodes[0]);
            if (customer == null)
                throw new RuntimeException("Customer Is Null");
            composer.refreshCustomer(customer);
            drawActions(customer);
        } else {
            parseParams();
            initRelationship();
            drawActions(composer.getCustomer());
            //this.relationship.setState(1);
        }
        onSelect$contact_types();

			/*self.addEventListener("onClose", new EventListener() {
                @Override
				public void onEvent(Event e) throws Exception {
					null;
				}
			});*/

        // Вызывается из CustomerComposer
        composer.setEventListener(new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                if (event.getName().equalsIgnoreCase(Events.ON_NOTIFY)){
                    Customer customer = composer.getCustomer();
                    Integer action = (Integer) event.getData();
                    try {
                        customer = actionService.doAction(1, customer, action);
                        composer.refreshCustomer(customer);
                        drawActions(customer);
                    }
                    catch (Exception e){
                        alert(e.getMessage());
                        customer.setIdSap(null);
                        customer.setForceCreated(false);
                    }
                }
            }
        });
    }

    private void drawRelationshipActions(final Relationship relationship) {
        rel_actions.getChildren().clear();
        //logger.info("State -> " + relationship.getState());
        List<Action> list = ActionUtils.getRelationshipActions(relationship.getState(), sessionAttributes.getUsername(),
                sessionAttributes.getPassword(), sessionAttributes.getSchema());
        //logger.info("Available Actions " + list);
        for (Action action : list) {
            if (action.getAction_id() == 3 && action.getAction_id() == 1)
                continue;

            Menuitem menuitem = new Menuitem();
            menuitem.setLabel(String.format("%s Отношение", action.getName()));
            menuitem.setAttribute("action", action.getAction_id());
            menuitem.setAttribute("deal_id", action.getDeal_id());
            menuitem.setParent(rel_actions);
            menuitem.addEventListener(Events.ON_CLICK, new EventListener() {
                @Override
                public void onEvent(Event event) throws Exception {
                    try {
                        int action = (Integer) event.getTarget().getAttribute("action");
                        int dealId = (Integer) event.getTarget().getAttribute("deal_id");
                        ContactPersonComposer.this.relationship.setPosition(contact_types.getValue());
                        if (ContactPersonComposer.this.relationship.getPosition() == null
                                || ContactPersonComposer.this.relationship.getPosition().isEmpty())
                            throw new RuntimeException("Выберите должность");

                        logger.info("Action -> " + action);
                        logger.info("Relationship -> " + relationship);

                        Relationship copyOfRelationship = ContactPersonComposer.this.relationship;

                        /*if (copyOfRelationship.getState() == 0)
                            action = 6;*/

                        ContactPersonComposer.this.relationship = relationsService.doAction(composer.getCustomer(),
                                ContactPersonComposer.this.relationship, dealId, action);

                        /*if (action == 6 && contact_types.getSelectedIndex() == 0 &&
                                !isAccountantCheckbox.isDisabled() && isAccountantCheckbox.isChecked()) //
                        {
                            copyOfRelationship.setPosition("2");
                            copyOfRelationship.setMapId(0);
                            relationsService.doAction(composer.getCustomer(),
                                    copyOfRelationship, dealId, action);
                        }*/

                        Messagebox.show("Успешно");
                        drawRelationshipActions(ContactPersonComposer.this.relationship);
                    } catch (Exception e) {
                        //logger.error(CheckNull.getPstr(e));
                        throw new RuntimeException(e.getMessage());
                    }
                }
            });

            if (rel_actions.getChildren().size() > 0) {
                Menuseparator menuseparator = new Menuseparator();
                menuseparator.setParent(rel_actions);
            }
            isAccountantCheckbox.setVisible(relationship.getState() == 0);
        }
    }

    private void drawActions(Customer customer) {
        actions.getChildren().clear();
        rel_actions.getChildren().clear();
        // If customer's state is confirmed
        //if (customer.getIntState() == 3) {
        drawRelationshipActions(ContactPersonComposer.this.relationship);
        //} //else {
        //rel_actions.getChildren().clear();
        //}
       
        Menuseparator separ = new Menuseparator();
        separ.setParent(actions);
        //logger.error(customer + "");
  
        /*String str1 = "";
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			str1 = objectMapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(customer);
		} catch (Exception e22) {
			str1 = "str1=error customer1 ";
		} finally {
		}
		ISLogger.getLogger().error(
				"** not err customer1  ************** " + str1);
		
		
		try {
			str1 = objectMapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(sessionAttributes);
		} catch (Exception e22) {
			str1 = "str1=error sessionAttributes ";
		} finally {
		}
		ISLogger.getLogger().error(
				"** not err sessionAttributes  ************** " + str1);
		
		ISLogger.getLogger().error(
				"** not err customer.getIntState()=" + customer.getIntState());
		*/
        
   
        
        //List<Action> list = ActionUtils.getContactActions(customer.getIntState(), sessionAttributes.getUsername(),
        //        sessionAttributes.getPassword(), sessionAttributes.getSchema());
        List<Action> list = new ArrayList<Action>(); ;
        try {
          list = ActionUtils.getContactActions(customer.getIntState(), sessionAttributes.getUsername(),
                sessionAttributes.getPassword(), sessionAttributes.getSchema());
        } catch(Exception e11) {
        	
        	String str1 = "";
    		ObjectMapper objectMapper = new ObjectMapper();

    		try {
    			str1 = objectMapper.writerWithDefaultPrettyPrinter()
    					.writeValueAsString(customer);
    		} catch (Exception e22) {
    			str1 = "str1=error customer1 ";
    		} finally {
    		}
    		ISLogger.getLogger().error(
    				"** not err customer1  ************** " + str1);
    		
    		
    		try {
    			str1 = objectMapper.writerWithDefaultPrettyPrinter()
    					.writeValueAsString(sessionAttributes);
    		} catch (Exception e22) {
    			str1 = "str1=error sessionAttributes ";
    		} finally {
    		}
    		ISLogger.getLogger().error(
    				"** not err sessionAttributes  ************** " + str1);
    		
    		ISLogger.getLogger().error(
    				"** not err customer.getState()=" + customer.getState()+".");
    		
    		ISLogger.getLogger().error(
    				"** not err customer.getIntState()=" + customer.getIntState()+".");
    		
        	alert("Ошибка при получении список доступных действий. ("+customer.getState()+":"+customer.getIntState() +"). "+e11.getMessage()+". "+e11.getCause() );
        } 
        for (Action action_ : list) {
            if (action_.getAction_id() == 2)
                continue;

            Menuitem item = new Menuitem();
            item.setLabel(action_.getName());
            item.setAttribute("action", action_.getAction_id());
            item.setAttribute("dealId", action_.getDeal_id());
            item.setParent(actions);
            item.addEventListener(Events.ON_CLICK, new EventListener() {
                @Override
                public void onEvent(Event event) throws Exception {
                    Integer action = (Integer) event.getTarget().getAttribute("action");
                    Integer dealId = (Integer) event.getTarget().getAttribute("dealId");
                    try {
                        synchronized (this) {
                            Customer currentCustomer = composer.getCustomer();
                            // Correction
                            if (action == 3) {
                                Customer toCompare = actionService.getCustomer(currentCustomer.getBranch(),
                                        currentCustomer.getId());
                                if (!DiffUtils.hasChanges(toCompare, currentCustomer)) {
                                    throw new RuntimeException("Нет измененных данных");
                                }
                            }
                            currentCustomer.setParent_id_client_j(context.getContactJId()); //2022.11.16
                            currentCustomer.setPerson_role(contact_types.getValue());
                            currentCustomer.setEmp_id(""+sessionAttributes.getUid());
                            //logger.error("Before " + ContactPersonComposer.this.relationship);
                            Customer customer = actionService.doAction(dealId, composer.getCustomer(), action);
                            composer.refreshCustomer(customer);

                            Messagebox.show("Успешно");
                            drawActions(customer);

                            /*if (action == 3 && customer.getState().equalsIgnoreCase("3")) {
                                // If correction, correct its relationship to be
                                // compatible with old version
                                relationsService.doAction(customer, relationship, 3, 3);
                            }*/
                            //logger.info("After " + ContactPersonComposer.this.relationship);
                        }
                    } catch (Exception e) {
                        if (e instanceof SAPDuplicationException) {
                            composer.showMatches(((SAPDuplicationException) e).getList(), action);
                        } else {
                            logger.error(CheckNull.getPstr(e));
                            throw new RuntimeException(e.getMessage());
                        }
                    }
                }
            });
        }
        if (actions.getChildren().size() > 1) {
            separ = new Menuseparator();
            separ.setParent(actions);
        }

    }

    private void init() {
        initSessionAttributes();
        initModels();
        initServices();
    }

    private void initServices() {
        sapService = SAPServiceFactory.getInstance().getBusinessPartnerService();
        mappingService = SAPServiceFactory.getInstance().getMappingService();
    }

    private void parseParams() throws InterruptedException {
        context = ContactPersonContext.getInstance(param);
        String log = "";
        Iterator it = context.getParams().entrySet().iterator();
        while (it.hasNext()) {
            String value = "";
            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getValue() != null) {
                String[] pairs = (String[]) pair.getValue();
                if (pairs != null && pairs.length > 0)
                    value = pairs[0];
            }
            log += "key=" + pair.getKey() + " value=" + value + " \n";
        }
        //logger.info("Person from -> SAP ... isCreateAction " + context.isCreateAction());
        if (context.isCreateAction())
            showCreationForm(context);
        else
            showContactPerson();
    }

    private void showCreationForm(ContactPersonContext context) throws InterruptedException {
        try {
            if (this.context.isSAPCustomer())
                setSAPCustomer(context);
            else {
                Customer customer = new Customer();
                customer.initCreationAttributes(sessionAttributes.getBranch());
                composer.refreshCustomer(customer);
            }
        } catch (Exception e) {
            Messagebox.show(e.getMessage());
        }
    }

    private void setSAPCustomer(ContactPersonContext context) throws SAPException, RemoteException {
        //logger.error("setSapCustomer Method " + context.getIDSap());
        Customer contactPerson = sapService.get(null, null, context.getIDSap());

        //logger.error("setSapCustomer Method from SAP " + contactPerson);
        contactPerson.setId(null);

        List<Customer> list = mappingService.getMapping(null, null, contactPerson.getIdSap());

        for (Customer customer_ : list) {
            if (customer_.isContactCustomer()) {
                String id = customer_.getId().replace("A", "");
                Customer customerDb = actionService.getCustomerByUnionId(customer_.getBranch(), id);
                if (customerDb != null && customerDb.getUnion_id() != null) {
                    contactPerson = customerDb;
                    if (!customerDb.getBranch().equalsIgnoreCase(sessionAttributes.getBranch())) {
                        contactPerson.setState("0");
                        contactPerson.setId(null);
                        contactPerson.setUnion_id(null);
                    }
                    break;
                }
            }
        }

        //logger.error("Customer list size -> " + list.size());

        //logger.error("Union id -> " + contactPerson.getUnion_id());
        //contactPerson.setState("0");
        //contactPerson.setId(null);
        //contactPerson.setUnion_id(null);

        if (contactPerson.getUnion_id() == null) {
            Customer result = actionService.searchByDocument(sessionAttributes.getBranch(), contactPerson);
            //logger.error("Customer result");
            if (result != null && result.getId() != null) {
                contactPerson = result;
                if (!result.getBranch().equalsIgnoreCase(sessionAttributes.getBranch())) {
                    contactPerson.setState("0");
                    contactPerson.setId(null);
                }
            }
        }

        contactPerson.setIdSap(context.getIDSap());
        contactPerson.initBaseAttributes(sessionAttributes.getBranch());

        //logger.error("ID SAP " + context.getIDSap());
        //logger.error("Final Contact Person -> " + contactPerson);
        composer.refreshCustomer(contactPerson);
    }

    private void showContactPerson() {
        if (context.getIDSap() == null || StringUtils.isEmpty(context.getIDSap().replace("null", ""))) {
            showLocalContactPerson();
        } else {
            try {
                setSAPCustomer(context);
            } catch (SAPException e) {

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void showLocalContactPerson() {
        String branch = context.getContactPersonBranch();
        String id = context.getContactPersonId();

        Customer localCustomer = actionService.getCustomer(branch, id);
        //2024.07.17
        if (localCustomer==null) 
        	logger.error("not err. localCustomer is null. branch="+branch+", id="+id);
        if (localCustomer.getUnion_id()==null) 
        	logger.error("not err. localCustomer.getUnion_id() is null. branch="+branch+", id="+id);

        String idSAP = null;
        try {
            idSAP = sapService.get(branch, "A" + localCustomer.getUnion_id(), null).getIdSap();
            localCustomer.setIdSap(idSAP);
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        }

        //logger.info("Customer from DB By Id and Branch -> \n" + localCustomer);

        composer.refreshCustomer(localCustomer);
    }

    private void initSessionAttributes() {
        sessionAttributes = GeneralUtils.getSessionAttributes(session);
    }

    private void initModels() {
        contact_types.setModel(new ListModelList(ReferenceDictionary.getContactTypes(sessionAttributes.getSchema())));
    }

    public void onClick$btnSynchronize() throws Exception {
        Customer customer = actionService.synchronize(composer.getCustomer());
        composer.refreshCustomer(customer);
        Messagebox.show("Cинхронизирован");
    }

    public void onClick$btnRefresh() {
        Customer customer = composer.getCustomer();
        String idSAP = customer.getIdSap();
        if (composer.getCustomer() != null && customer.getId() != null && customer.getBranch() != null) {
            customer = actionService.getCustomer(customer.getBranch(), customer.getId());
            if (customer == null || !customer.isExist())
                throw new RuntimeException("No such customer");
            customer.setIdSap(idSAP);
            composer.refreshCustomer(customer);
            // composer.getBinder().loadAll();
        }
    }

    public void onClick$btnSourceData() throws InterruptedException {
        composer.showDifferences();
    }

    public void initRelationship() {
        Events.sendEvent(this.contact_types, new Event("onInitRender"));
        this.contact_types.setSelecteditem(context.getPosition());
        this.relationship = PersonMapDbUtils.getRelationship(sessionAttributes.getBranch(),
                context.getContactPersonId(), context.getContactJId(), context.getPosition(),
                sessionAttributes.getSchema());
        this.relationship.setInnJ(context.getInnJ());
        this.relationship.setClientJId(context.getContactJId());
        this.relationship.setPosition(context.getPosition());
        //logger.info("Relationship from DB -> " + this.relationship);
        //logger.info("Relationship params -> " + " Person Id -> " + context.getContactPersonId() + " ContactJId ->" + context.getContactJId() + " Position -> " + context.getPosition());
    }

    public void onSelect$contactTabbox(SelectEvent event) throws CloneNotSupportedException {
        Tab selectedComponent = (Tab) event.getTarget();
        if (selectedComponent.getId().equalsIgnoreCase("appsTab")) {
            if (appsWnd == null)
                appsWnd = (Window) Executions.createComponents("/customer/apps.zul", appsDiv, null);
            appsWnd.setVisible(true);
            Customer copy = (Customer) composer.getCustomer().clone();
            copy.setId("A" + composer.getCustomer().getUnion_id());
            Events.sendEvent("onUploadApps", appsWnd, copy);
        }
    }

    public void onSelect$contact_types() {
        if (contact_types.getSelectedIndex() > -1) {
            if (contact_types.getSelectedIndex() == 1) {
                isAccountantCheckbox.setDisabled(true);
                isAccountantCheckbox.setChecked(false);
            } else {
                isAccountantCheckbox.setDisabled(true);
            }
        } else {
            isAccountantCheckbox.setDisabled(false);
            isAccountantCheckbox.setChecked(false);
        }
    }

}
