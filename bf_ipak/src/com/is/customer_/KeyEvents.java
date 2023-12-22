package com.is.customer_;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 16.06.2017.
 * 10:57
 */
public final class KeyEvents {
    private static Map<Integer,String> _keys;

    static{
        _keys = new HashMap<Integer, String>();
        _keys.put(48,"0");
        _keys.put(49,"1");
        _keys.put(50,"2");
        _keys.put(51,"3");
        _keys.put(52,"4");
        _keys.put(53,"5");
        _keys.put(54,"6");
        _keys.put(55,"7");
        _keys.put(56,"8");
        _keys.put(57,"9");
    }

    public static String getKey(int code){
        return null;
    }
}
