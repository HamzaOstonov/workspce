package com.is.customer_.mobile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.customer_.CustomerFactory;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.local.service.CustomerActionInterface;
import com.is.customer_.local.service.CustomerActionService;
import com.is.customer_.mobile.model.MobileAuthentication;
import com.is.customer_.mobile.model.MobileBankClient;
import com.is.customer_.mobile.model.MobileCustomerResponse;
import com.is.customer_.mobile.model.confirmation.RegisterConfirmation;
import com.is.customer_.mobile.model.confirmation.UpdateConfirmation;
import com.is.customer_.mobile.model.confirmation.UpdateStateConfirmation;
import com.is.customer_.mobile.model.customerData.MobileCustomerDataResponse;
import com.is.customer_.mobile.model.findByBind.MobileCustomerByBindRequest;
import com.is.customer_.mobile.model.customerData.InnerMobileCustomerResponse;
import com.is.customer_.mobile.model.confirmation.RegisterResponse;
import com.is.customer_.mobile.model.send.SendSMSRequest;
import org.apache.log4j.Logger;
import org.zkoss.zhtml.Messagebox;

import java.io.IOException;
import java.util.List;

/**
 * Created by Dev1 on 14.11.2018.
 */
public class MobileCustomerGatewayHandlerService {
    private MobileCustomerGateway _gateway = new MobileCustomerGateway();

    private CustomerActionInterface customerActionService = CustomerFactory.getInstance(null).getCustomerActionService();

    private static Logger logger = Logger.getLogger(MobileCustomerGateway.class);

    public MobileCustomer findByBind(String branch, String id) throws Exception {
    	//logger.error("mobile2021 f 1");
        MobileCustomerByBindRequest request = new MobileCustomerByBindRequest(branch, id);
        String content = _gateway.findByBind(request);
        MobileCustomerDataResponse response = new ObjectMapper().readValue(content, com.is.customer_.mobile.model.customerData.MobileCustomerDataResponse.class);

        if (response.getRes() != 0) {
            throw new Exception("An error occurred! " + response.getErr());
        }

        return mapToMobileCustomer(response);
    }

    public RegisterResponse register(MobileCustomer mobileCustomer, SessionAttributes sessionAttributes) throws IOException {
    	//logger.error("mobile2021 c 1");
        SendSMSRequest request = new SendSMSRequest();
        request.setEmp_branch(sessionAttributes.getBranch());
        request.setEmp_id(sessionAttributes.getUid());
        request.setPhone(mobileCustomer.getPhone());
        //logger.error("mobile2021 c 2");
        return new ObjectMapper().readValue(_gateway.register(request), RegisterResponse.class);
    }

    public MobileCustomerResponse update(MobileCustomer mobileCustomer, SessionAttributes sessionAttributes) throws IOException {
    	//logger.error("mobile2021 k 1");
        SendSMSRequest request = new SendSMSRequest();
        request.setEmp_branch(sessionAttributes.getBranch());
        request.setEmp_id(sessionAttributes.getUid());
        request.setPhone(mobileCustomer.getPhone());

        return new ObjectMapper().readValue(_gateway.update(request), MobileCustomerResponse.class);
    }

    public MobileCustomerResponse updateState(MobileCustomer customer, SessionAttributes sessionAttributes) throws Exception {
    	//logger.error("mobile2021 z 1");
        SendSMSRequest request = new SendSMSRequest();
        request.setEmp_branch(sessionAttributes.getBranch());
        request.setEmp_id(sessionAttributes.getUid());
        request.setPhone(customer.getPhone());
        request.setState(Integer.parseInt(customer.getState()));
        return new ObjectMapper().readValue(_gateway.updateState(request), MobileCustomerResponse.class);
    }

    public MobileCustomer confirmRegistration(MobileCustomer customer, List<Customer> customerList,
                                              SessionAttributes sessionAttributes, MobileAuthentication authentication) throws IOException, MobileException {
    	//logger.error("mobile2021 t 01");
        RegisterConfirmation request = new RegisterConfirmation();
        //logger.error("mobile2021 t 02");
        request.setEmp_branch(sessionAttributes.getBranch());
        request.setEmp_id(sessionAttributes.getUid());
        request.setPhone(customer.getPhone());
        request.setEmail(customer.getEmailAddress());
        request.setPassword(authentication.getOtp());
        //logger.error("mobile2021 t 03");
        for (Customer customer_ : customerList) {
            request.setCrmId(customer_.getIdSap());
            if (customer_.isCustomer()) {
                //customer_ = customerActionService.getCustomer(customer_.getBranch(), customer_.getId());
            	customer_ = customerActionService.getCustomer(customer_.getBranch().equals("00000")? sessionAttributes.getBranch() : customer_.getBranch(), customer_.getId());

                if (customer_.getIntState() == 2) {
                    MobileBankClient bankClient = new MobileBankClient();
                    //bankClient.setBranch(customer_.getBranch());
                    bankClient.setBranch(customer_.getBranch().equals("00000")? sessionAttributes.getBranch() : customer_.getBranch());
                    bankClient.setClient_id(customer_.getId());
                    bankClient.setName(customer_.getName());

                    request.getBinds().add(bankClient);
                }
            }
        }
        //logger.error("mobile2021 t 04");
        MobileCustomerDataResponse response = new ObjectMapper().readValue(
                _gateway.confirmRegistration(
                        new ObjectMapper().writeValueAsString(request)), MobileCustomerDataResponse.class);
        
        //logger.error("mobile2021 t 05");
        if (response.getRes() != 0) {
        	//logger.error("mobile2021 t 06");
            throw new MobileException(response.getErr());
        }
        //logger.error("mobile2021 t 07");
        return mapToMobileCustomer(response);
    }

    public MobileCustomer confirmUpdateState(MobileCustomer customer, SessionAttributes sessionAttributes, MobileAuthentication authentication) throws IOException, MobileException {

    	//logger.error("mobile2021 u 1");
        UpdateStateConfirmation request = new UpdateStateConfirmation();
        request.setId(customer.getId());
        request.setPhone(customer.getPhone());
        request.setEmp_branch(sessionAttributes.getBranch());
        request.setEmp_id(sessionAttributes.getUid());
        request.setState(customer.getState());
        request.setPassword(authentication.getOtp());

        MobileCustomerDataResponse response = new ObjectMapper().readValue(
                _gateway.confirmUpdateState(
                        new ObjectMapper().writeValueAsString(request)), MobileCustomerDataResponse.class);

        if (response.getRes() != 0) {
            throw new MobileException(response.getErr());
        }

        return mapToMobileCustomer(response);
    }

    public MobileCustomer confirmUpdate(MobileCustomer customer, List<Customer> customerList, SessionAttributes sessionAttributes, MobileAuthentication authentication) throws IOException, MobileException {
    	//logger.error("mobile2021 i 1");
    	UpdateConfirmation request = new UpdateConfirmation();
        request.setId(customer.getId());
        request.setPhone(customer.getPhone());
        request.setEmail(customer.getEmailAddress());
        request.setEmp_branch(sessionAttributes.getBranch());
        request.setEmp_id(sessionAttributes.getUid());
        request.setPassword(authentication.getOtp());

        for (Customer customer_ : customerList) {
            request.setCrmId(customer_.getIdSap());
            if (customer_.isCustomer()) {
                //customer_ = customerActionService.getCustomer(customer_.getBranch(), customer_.getId());
                customer_ = customerActionService.getCustomer(customer_.getBranch().equals("00000")? sessionAttributes.getBranch() : customer_.getBranch(), customer_.getId());

                if (customer_.getIntState() == 2) {
                    MobileBankClient bankClient = new MobileBankClient();
                    //bankClient.setBranch(customer_.getBranch());
                    bankClient.setBranch(customer_.getBranch().equals("00000")? sessionAttributes.getBranch() : customer_.getBranch());
                    bankClient.setClient_id(customer_.getId());
                    bankClient.setName(customer_.getName());

                    request.getBank_clients().add(bankClient);
                }
            }
        }

        MobileCustomerDataResponse response = new ObjectMapper().readValue(
                _gateway.confirmUpdate(
                        new ObjectMapper().writeValueAsString(request)), MobileCustomerDataResponse.class);

        if (response.getRes() != 0) {
            throw new MobileException(response.getErr());
        }

        return mapToMobileCustomer(response);
    }

    private MobileCustomer mapToMobileCustomer(MobileCustomerDataResponse mobileCustomerResponse) throws IOException {
        MobileCustomer mobileCustomer = new MobileCustomer();

        if (mobileCustomerResponse != null && mobileCustomerResponse.getData() != null) {
            InnerMobileCustomerResponse customer = mobileCustomerResponse.getData().getCustomer();
            MobileBankClient[] binds = mobileCustomerResponse.getData().getClients();

            if (customer == null) {
                return null;
            }

            mobileCustomer.setPhone(customer.getPhone());
            //mobileCustomer.setUsername(customer.getUsername());
            mobileCustomer.setEmailAddress(customer.getEmail());
            mobileCustomer.setId(customer.getId());
            mobileCustomer.setState(customer.getState());
            mobileCustomer.setRegistrationDate(customer.getRegistrationDate());

            for (int i = 0; i < binds.length; i++) {
                Customer customer_ = new Customer();
                customer_.setBranch(binds[i].getBranch());
                customer_.setId(binds[i].getClient_id());
                customer_.setName(binds[i].getName());

                mobileCustomer.getBinds().add(customer_);
            }

        }
        return mobileCustomer;
    }
}