package com.is.clients.utils;

import java.lang.reflect.Field;

public interface FieldHandler {
	boolean eval(Field field, Object obj1, Object obj2) throws IllegalAccessException;
}	
