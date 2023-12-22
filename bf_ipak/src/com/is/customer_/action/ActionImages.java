package com.is.customer_.action;

import com.is.clients.utils.ClientUtil;
import com.is.customer_.ActionsEnum;

import java.util.HashMap;

/**
 * Created by root on 11.06.2017.
 * 15:48
 */
public final class ActionImages {
    private static HashMap<Integer,String> _customerImages;
    private static HashMap<Integer,String> _organizationImages;

    static{
        _customerImages = new HashMap<Integer, String>();
        _customerImages.put(ActionsEnum.ACTION_CREATE_BUSINESS_PARTNER.action(),"/images/send.png");
        _customerImages.put(ActionsEnum.ACTION_CLOSE_BUSINESS_PARTNER.action(),"/images/stop.png");
        _customerImages.put(ActionsEnum.ACTION_CONFIRM_BUSINESS_PARTNER.action(),"/images/accept.png");
        _customerImages.put(ActionsEnum.ACTION_CONFIRM_CLOSED_BUSINESS_PARTNER.action(),"/images/approved.png");
        _customerImages.put(ActionsEnum.ACTION_CORRECT_BUSINESS_PARTNER.action(),"/images/edit.png");
        _customerImages.put(ActionsEnum.ACTION_CORRECT_PHONE.action(),"/images/contacts.png");
        _customerImages.put(ActionsEnum.ACTION_DELETE_BUSINESS_PARTNER.action(),"/images/delete.png");

        _organizationImages = new HashMap<Integer, String>();
        _organizationImages.put(ClientUtil.ACTION_CONFIRM_CLOSED,"/images/approved.png");
        _organizationImages.put(ClientUtil.ACTION_OPEN,"/images/send.png");
        _organizationImages.put(ClientUtil.ACTION_CONFIRM,"/images/accept.png");
        _organizationImages.put(ClientUtil.ACTION_CHANGE,"/images/edit.png");
        _organizationImages.put(ClientUtil.ACTION_CHANGE_OBJ,"/images/edit.png");
        _organizationImages.put(ClientUtil.ACTION_CLOSE,"/images/stop.png");
    }
    public static String getImageForCustomers(int action){
        return _customerImages.get(action);
    }

    public static String getImageForOrganizations(int action){
        return _organizationImages.get(action);
    }
}
