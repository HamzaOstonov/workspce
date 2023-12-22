package com.is.payments.ui;

import com.is.account.model.Account;
import com.is.customer_.core.model.CustomerBuilder;
import com.is.customer_.sap.EmergencyMode;
import com.is.customer_.search.SAPSearch.SAPSearchComposer;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.customer_.search.SAPSearch.SearchItemListener;
import com.is.customer_.search.SAPSearch.boundaries.SearchInteractor;
import com.is.customer_.search.SAPSearch.model.Response;
import com.is.customer_.ui.AbstractCustomerComponentFacade;
import com.is.customer_.ui.CustomerComposerInteractor;
import com.is.customer_.ui.CustomerWindowFacade;
import com.is.payments.PaymentUtils;
import com.is.payments.entity.BudgetAccount;
import com.is.payments.interactor.PaymentTransactionInteractor;
import com.is.payments.model.PaymentRequest;
import com.is.payments.service.CustomerForPaymentSearchStrategy;
import com.is.payments.service.PaymentService;
import com.is.payments.spr.s_budspr.S_budspr;
import com.is.payments.spr.s_budspr.S_budsprService;
import com.is.payments.spr.s_kaznacc.Kaznacc;
import com.is.payments.spr.s_kaznacc.KaznaccService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefDataService;
import general.General;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import java.sql.SQLException;
import java.util.List;

public class PaymentController extends GenericForwardComposer {
    private final Logger logger = Logger.getLogger(PaymentController.class);

    private static final String SEARCH_WND_PATH = "SAPSearch.zul";

    private static final java.lang.String SEARCH_WND_ATTRIBUTE = "SAPSearchWnd$composer";

    private Window treasuryWnd = null;
    private Window budgetWnd = null;
    private Window accountWnd = null;

    private Window filterWnd;
    private Listbox filterWnd$listbox;

    private Grid paymentGrid;
    private Row treasuryAccountRow, taxNumberRegistrationRow,
                budgetAccountRow,budgetAccountNameRow;

    private RefCBox cashSymbol, cashSymbolDescription,
            paymentTypes, branchReceive, branchSend,
            purposeCode;
    private Textbox cashSymbolText, senderAccount,
            branchReceiveText,
            cashSymbolDescriptionText,
            budgetAccount, taxNumberRegistration,
            connectedPerson,budgetAccountName;

    private Include searchInclude;
    private Label taxNumberRegistrationName;
    private Checkbox budgetCheckbox,treasuryCheckbox;

    private AnnotateDataBinder binder;
    private PaymentRequest current;
    private SessionAttributes sessionAttributes;
    private SAPSearchComposer composer;
    private PaymentService paymentService;

    public PaymentController() {
        super('$', false, false);
    }

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        bindBeans(comp);
        init();
    }

    private void init() throws Exception {
        initSessionAttributes();
        paymentService = PaymentService.getInstance(sessionAttributes);
        initModels();
        prepareSearchWindow();
        initPaymentAttributes();
    }

    private void initPaymentAttributes() {

    }

    private void prepareSearchWindow() {
        List children = searchInclude.getChildren();
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i) instanceof Window){
                composer = (SAPSearchComposer)
                        ((Window)children.get(i)).getAttribute(SEARCH_WND_ATTRIBUTE);
            }
        }
        //composer.getFacade().setInteractor(decorator);
        //composer.getFacade().detachComponent();
        final AbstractCustomerComponentFacade facade = CustomerWindowFacade.newInstance(
                self,
                "/customer/paymentCustomer.zul",
                "paymentCustomerWnd$composer");
        composer.setFacade(facade);
        if (EmergencyMode.isTrue){
            final SearchInteractor customerSearchStrategy = CustomerForPaymentSearchStrategy.getInstance(sessionAttributes);
            composer.setSearchInteractor(customerSearchStrategy);
        }
        composer.setItemListener(Events.ON_CLICK, new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                try {
                    Customer customer = composer.convertToCustomer(
                            (Response)
                                    event.getTarget()
                                            .getAttribute("searchResult"));
                    //logger.error("Customer " + customer);
                    facade.getProducedComponent().setVisible(true);
                    ((Window) facade.getProducedComponent()).doModal();
                    facade.getInteractor().show(customer);
                }
                catch (Exception e){
                    logger.error(CheckNull.getPstr(e));
                }
            }
        });
        composer.consumeMessages(true);
        facade.getProducedComponent().addEventListener(Events.ON_NOTIFY, new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                Customer customer = (Customer) event.getData();
                current.setPersonName(String.format("%s %s %s %s",
                        "",
                        customer.getFullName(),
                        customer.getP_passport_serial(),
                        customer.getP_passport_number()));
                binder.loadAll();
            }
        });
    }


    private void initModels() throws Exception {
        cashSymbol.setModel(new ListModelList(paymentService.getCashSymbols()));
        branchSend.setModel(new ListModelList(RefDataService.get_cur_bank_Mfo(sessionAttributes.getSchema())));
        branchReceive.setModel(new ListModelList(paymentService.getBranches()));
        paymentTypes.setModel(new ListModelList(PaymentUtils.getPaymentTypes()));
        purposeCode.setModel(new ListModelList(paymentService.getPurposeCodes()));
    }

    private void bindBeans(Component comp) {
        current = new PaymentRequest();
        binder = new AnnotateDataBinder(comp);
        binder.bindBean("current", this.current);
        binder.loadAll();
    }

    private void initSessionAttributes() {
        sessionAttributes = GeneralUtils.getSessionAttributes(session);
    }

    public void onClick$btnOk() throws InterruptedException {
        try {
            Customer customer = (Customer)
                    self.getDesktop().getAttribute("paymentCustomer");
            current.setCustomer(customer);
            if (current.getPaymentType() == null) {
                Messagebox.show("Тип платежа не задан!");
                return;
            }

            PaymentTransactionInteractor
                    interactor = PaymentTransactionInteractor.
                    getInstance(sessionAttributes);
            List<General> list = interactor.commitPayment(current);

            String message = "";
            if (list != null)
                for (General general : list)
                    message += String.format("Документ %d порожден \n", general.getId());

            Messagebox.show(message);

            self.getDesktop().setAttribute("paymentCustomer",null);
            current = new PaymentRequest();
            binder.loadAll();
        } catch (Exception e) {
            Messagebox.show(e.getMessage());
        }
    }

    public void onChange$paymentTypes() throws SQLException, InterruptedException {
        current.clearCurrent();

        if (paymentTypes.getValue() == null)
            return;

        int type_ = Integer.parseInt(paymentTypes.getValue());
        switch (type_) {
            case 5000:
                current.setBranchSend(sessionAttributes.getBranch());
                current.setAccountSend(paymentService.getSenderAccount());
                current.setAccountSendName(paymentService.getAccountName(current.getAccountSend(),
                        sessionAttributes.getBranch()));
                current.setBranchReceive(sessionAttributes.getBranch());
                cashSymbol.setDisabled(false);
                cashSymbolDescription.setDisabled(false);
                cashSymbolText.setDisabled(false);
                cashSymbolDescriptionText.setDisabled(false);
                purposeCodeText.setDisabled(true);
                purposeCode.setDisabled(true);
                budgetCheckbox.setDisabled(true);
                treasuryCheckbox.setDisabled(true);
                budgetCheckbox.setChecked(false);
                treasuryCheckbox.setChecked(false);
                hideAllAdditionalRows();
                break;
            case 5001:
                current.setBranchSend(sessionAttributes.getBranch());
                current.setAccountSend(paymentService.getSenderAccount());
                current.setAccountSendName(paymentService.getAccountName(
                        current.getAccountSend(), sessionAttributes.getBranch()));
                current.setBranchReceive(null);
                cashSymbol.setDisabled(false);
                cashSymbolDescription.setDisabled(false);
                cashSymbolText.setDisabled(false);
                cashSymbolDescriptionText.setDisabled(false);
                purposeCodeText.setDisabled(false);
                purposeCode.setDisabled(false);
                budgetCheckbox.setDisabled(false);
                treasuryCheckbox.setDisabled(false);
                budgetCheckbox.setChecked(false);
                treasuryCheckbox.setChecked(false);
                break;
            case 5002:
                current.setBranchSend(sessionAttributes.getBranch());
                current.setAccountSend(paymentService.getTransitionAccount(5003));
                current.setAccountSendName(paymentService.getAccountName(current.getAccountSend(),
                        sessionAttributes.getBranch()));
                //current.setBranchReceive(sessionAttributes.getBranch());
                cashSymbol.setDisabled(true);
                cashSymbolDescription.setDisabled(true);
                cashSymbolText.setDisabled(true);
                cashSymbolDescriptionText.setDisabled(true);
                purposeCodeText.setDisabled(false);
                purposeCode.setDisabled(false);
                budgetCheckbox.setDisabled(false);
                treasuryCheckbox.setDisabled(false);
                budgetCheckbox.setChecked(false);
                treasuryCheckbox.setChecked(false);
                break;
            case 5003:
                current.clearCurrent();
                current.setBranchSend(sessionAttributes.getBranch());
                current.setAccountSend(paymentService.getTransitionAccount(5004));
                current.setAccountSendName(paymentService.getAccountName(
                        current.getAccountSend(),
                        sessionAttributes.getBranch()));
                cashSymbol.setDisabled(true);
                cashSymbolDescription.setDisabled(true);
                cashSymbolText.setDisabled(true);
                cashSymbolDescriptionText.setDisabled(true);
                purposeCodeText.setDisabled(false);
                purposeCode.setDisabled(false);
                budgetCheckbox.setDisabled(true);
                treasuryCheckbox.setDisabled(true);
                budgetCheckbox.setChecked(false);
                treasuryCheckbox.setChecked(false);
                hideAllAdditionalRows();
                break;
        }

        binder.loadAll();
    }

    public void onChange$receiverAccount(InputEvent event) {
        String receiverAccount = event.getValue();
        current.setAccountSendName(
                paymentService.getAccountName(
                        current.getAccountSend(), current.getBranchSend()));
        current.setAccountReceiveName(
                paymentService.getAccountName(
                        current.getAccountReceive(), current.getBranchReceive()));
        // If
        if (receiverAccount != null
                && current.getBranchReceive()!=null
                && receiverAccount.startsWith("23402")
                && current.getBranchReceive().equals("00014")
                && !budgetCheckbox.isChecked()) {
            /*String accName = KaznaccService.getKaznaccName("00014",receiverAccount).getAccAndTaxNumber();
            current.setAccountReceiveName(accName);*/
            setCentralBankAccount();
            budgetAccountNameRow.setVisible(true);
            taxNumberRegistrationRow.setVisible(true);
            treasuryAccountRow.setVisible(true);
            binder.loadAll();
        }
    }

    public void onChange$cashSymbolText() {
        String cashSymbolVal = cashSymbolText.getValue();
        current.setCashSymbol(cashSymbolVal);
        cashSymbolText.setValue(cashSymbolVal);
        cashSymbolDescription.setModel(
                new ListModelList(
                        paymentService.getCashSymbolDescriptions(cashSymbolVal)));
    }

    public void onChange$cashSymbol() {
        String cashSymbolVal = cashSymbol.getValue();
        current.setCashSymbol(cashSymbolVal);
        cashSymbolText.setValue(cashSymbolVal);
        cashSymbolDescription.setModel(new ListModelList(
                paymentService.getCashSymbolDescriptions(
                        current.getCashSymbol())));
    }



    public void onCheck$budgetCheckbox(CheckEvent event){
        current.clearCurrent();
        hideAllAdditionalRows();
        if (event.isChecked()) {
            setCentralBankAccount();
            current.setBudget(true);
            budgetCheckbox.setChecked(true);
            budgetAccountRow.setVisible(true);
            taxNumberRegistrationRow.setVisible(true);
            budgetAccountNameRow.setVisible(true);
            treasuryAccountRow.setVisible(false);
        }
        else
            current.clearCurrent();

        binder.loadAll();
    }

    private void setCentralBankAccount() {
        BudgetAccount budgetAccount = PaymentUtils.getCentralBankAccount(sessionAttributes.getSchema());
        current.setBranchReceive(budgetAccount.getBranch());
        current.setAccountReceive(budgetAccount.getAccount());
        current.setAccountReceiveName(budgetAccount.getNameAndTaxNumber());
    }

    public void onCheck$treasuryCheckbox(CheckEvent checkEvent){
        hideAllAdditionalRows();
        current.clearCurrent();
        if (checkEvent.isChecked()){
            setCentralBankAccount();
            treasuryAccountRow.setVisible(true);
            budgetAccountNameRow.setVisible(true);
            treasuryCheckbox.setChecked(true);
        }
        else
        current.clearCurrent();
        binder.loadAll();
    }

    private void hideAllAdditionalRows(){
        budgetAccountRow.setVisible(false);
        budgetAccountNameRow.setVisible(false);
        taxNumberRegistrationRow.setVisible(false);
        treasuryAccountRow.setVisible(false);
        budgetCheckbox.setChecked(false);
        treasuryCheckbox.setChecked(false);
    }

    private Textbox purposeCodeText;
    public void onChange$purposeCodeText(){
        String purpose = StringUtils.leftPad(purposeCodeText.getValue(),5,"0");
        current.setPurposeCode(purpose);
        purposeCode.setValue(purpose);
        purposeCodeText.setValue(purpose);
    }

    public void onChange$cashSymbolDescriptionText(){
        String padded = StringUtils.leftPad(cashSymbolDescriptionText.getValue(),2,"0");
        current.setCashSymbolDescription(padded);
        cashSymbolDescriptionText.setValue(padded);
        cashSymbolDescription.setValue(padded);
    }

    public void onChange$branchReceiveText(InputEvent inputEvent){
        String value = inputEvent.getValue();
        if (value !=null && !value.isEmpty())
            value = StringUtils.leftPad(branchReceiveText.getValue(), 5, "0");

        branchReceiveText.setValue(value);
        current.setBranchReceive(value);
    }
    public void onChange$treasuryAccount(InputEvent event) throws InterruptedException {
        List<Kaznacc> list = KaznaccService.getKaznaccByBudget(event.getValue());
        for (Kaznacc kaznacc:list){
            current.setTaxNumberRegistration(kaznacc.getAccinn());
            current.setAccountReceive(kaznacc.getAcc());
            current.setAccountReceiveName(kaznacc.getAccAndTaxNumber());
            current.setBudgetAccountName(kaznacc.getNamebudget());
            current.setTreasuryAccount(kaznacc.getBudget());
        }
        binder.loadAll();
    }

    public void onChange$budgetAccount(InputEvent event){
        S_budspr budgetAccount = S_budsprService.getS_budspr(event.getValue());
        current.setBranchReceive(budgetAccount.getBank_id());
        current.setBudgetAccountName(budgetAccount.getName());
        current.setBudgetAccount(budgetAccount.getAccount());
        current.setAccountReceive(budgetAccount.getBankacc());
        current.setAccountReceiveName(budgetAccount.getAccAndTaxNumber());
        current.setTaxNumberRegistration(budgetAccount.getInn());
        binder.loadAll();
    }

    public void onCtrlKey$treasuryAccount(KeyEvent keyEvent){
        int keyCode = keyEvent.getKeyCode();
        // F9
        if (keyCode == 120)
            showTreasuryWnd();
    }

    public void onCtrlKey$budgetAccount(KeyEvent keyEvent){
        int keyCode = keyEvent.getKeyCode();
        // F9
        if (keyCode == 120)
            showBudgetWnd();
    }

    public void onCtrlKey$receiverAccount(KeyEvent keyEvent) throws InterruptedException {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 120)
            showAccountWnd();
    }

    private void showAccountWnd() throws InterruptedException {
        if (accountWnd == null)
            accountWnd = (Window) Executions.createComponents("/account.zul",self,null);
        accountWnd.setVisible(true);
        accountWnd.doModal();
        accountWnd.setClosable(true);
        accountWnd.setTitle("Счета");
        accountWnd.setMaximizable(true);
        accountWnd.addEventListener(Events.ON_CLOSE, new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                accountWnd.setVisible(false);
                event.stopPropagation();
            }
        });
        accountWnd.addEventListener("onNotifyAccount", new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                accountWnd.setVisible(false);
                Account account = (Account) event.getData();
                current.setAccountReceive(account.getId());
                current.setAccountReceiveName(
                        paymentService.
                                getAccountName(
                                        account.getId(),
                                        sessionAttributes.getBranch()));
                current.setBranchReceive(account.getBranch());
                binder.loadAll();
            }
        });
    }

    private void showTreasuryWnd() {
        if (treasuryWnd == null)
            treasuryWnd = (Window) Executions.createComponents("/kaznacc.zul",self,null);
        treasuryWnd.setVisible(true);
        treasuryWnd.addEventListener("onAddKaznacc", new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                Kaznacc kaznAccount = (Kaznacc) event.getData();
                current.setAccountReceive(kaznAccount.getAcc());
                current.setAccountReceiveName(kaznAccount.getAccAndTaxNumber());
                current.setTreasuryAccount(kaznAccount.getBudget());
                current.setBudgetAccountName(kaznAccount.getNamebudget());
                current.setTaxNumberRegistration(kaznAccount.getAccinn());
                current.setBranchReceive(kaznAccount.getAccbranch());
                binder.loadAll();
            }
        });
    }

    private void showBudgetWnd() {
        if (budgetWnd == null)
            budgetWnd = (Window) Executions.createComponents("/budget.zul",self,null);
        budgetWnd.setVisible(true);
        budgetWnd.addEventListener("onAddBudget", new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                S_budspr account = (S_budspr) event.getData();
                current.setBranchReceive(account.getBank_id());
                current.setAccountReceive(account.getBankacc());
                current.setAccountReceiveName(account.getAccAndTaxNumber());
                current.setBudgetAccount(account.getAccount());
                current.setTaxNumberRegistration(account.getInn());
                current.setBudgetAccountName(account.getName());
                binder.loadAll();
            }
        });
    }


    public PaymentRequest getCurrent() {
        return current;
    }

    public void setCurrent(PaymentRequest current) {
        this.current = current;
    }

}
