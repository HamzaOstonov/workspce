package com.is.customer_;

import org.zkoss.zk.ui.util.Clients;

/**
 * Created by root on 19.05.2017.
 * 14:12
 */
public final class NotificationUtils {
    public static void show(String message){
        Clients.evalJavaScript(String.format("$.notify('%s','info')",message));
    }

    public static void notify(String uuid,String message){
        Clients.evalJavaScript(String.format("$('#%s').notify('%s','info')",uuid,message));
    }

    public static void show(String uuid,String message,String position,Types notification){
        Clients.evalJavaScript(
                String.format("$('#%s').notify('%s','%s',{position:'%s'})",uuid,message,
                        notification.toString(),
                            position));
    }

    public static void show(String message,Types type){
        Clients.evalJavaScript(String.format("$.notify('%s','%s')",message,type.toString()));
    }

    public enum Types{
        SUCCESS("success"),
        INFO("info"),
        WARN("warn"),
        ERROR("error");

        private final String type;

        Types(String type){
            this.type = type;
        }

        @Override
        public String toString(){
            return type;
        }
    }
}
