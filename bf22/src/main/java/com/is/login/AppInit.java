package com.is.login;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

public class AppInit implements Initiator{
    public void doInit(Page page, @SuppressWarnings("rawtypes")
                Map arg) throws Exception {

            if (!page.getDesktop().getRequestPath().equals("/index.zul")  ){
                      Executions.getCurrent().sendRedirect("/");
                }
        }


       
}