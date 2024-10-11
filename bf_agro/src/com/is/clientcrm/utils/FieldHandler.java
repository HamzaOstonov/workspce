package com.is.clientcrm.utils;

import java.lang.reflect.Field;

public interface FieldHandler {
	void eval(Field field, Object obj1) throws IllegalAccessException;
}	
