package com.is.tieto_visae.tieto;

public class StringUtils {
    public static String secureNull(String inputString){
        if(inputString != null){
            return inputString;
        }else{
            return "";
        }
    }

    public static String secureNull(Object input){
        if(input != null){
            return input.toString();
        }else{
            return "";
        }
    }
}
