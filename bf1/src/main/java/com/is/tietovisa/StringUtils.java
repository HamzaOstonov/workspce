package com.is.tietovisa;

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
