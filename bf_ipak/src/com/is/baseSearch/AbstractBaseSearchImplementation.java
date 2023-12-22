package com.is.baseSearch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.reflections.Reflections;


/**
 * Basic skeleton implementation of interfaces
 * 
 * @author root
 *
 */
public abstract class AbstractBaseSearchImplementation
		implements BaseFormInterface, BaseSearchInterface<AbstractSearchInput,AbstractSearchResult> {
	private static final Logger LOGGER = Logger.getLogger(AbstractBaseSearchImplementation.class);
	
	private static List<AbstractBaseSearchImplementation> _subtypes = null;

	static {
		_subtypes = new ArrayList<AbstractBaseSearchImplementation>();

		Reflections reflections = new Reflections("com.is.searchBase");
		Set<Class<? extends AbstractBaseSearchImplementation>> subTypes = reflections
				.getSubTypesOf(AbstractBaseSearchImplementation.class);
		Iterator<Class<? extends AbstractBaseSearchImplementation>> iterator = subTypes.iterator();
		while (iterator.hasNext()) {
			AbstractBaseSearchImplementation instance = null;
			try {
				instance = iterator.next().newInstance();
			} catch (InstantiationException e) {
				LOGGER.error(e.getMessage(),e);
			} catch (IllegalAccessException e) {
				LOGGER.error(e.getMessage(),e);
			}
			_subtypes.add(instance);
		}
	}

	public static List<AbstractBaseSearchImplementation> getImplementors() {
		return _subtypes;
	}

	public static int getSize() {
		return _subtypes.size();
	}
}
