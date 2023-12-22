package com.is.baseSearch;

import java.util.List;

/**
 * Interface for searching customers in external systems
 * @author root
 *
 * @param <T> Search input
 * @param <V> Search result
 */
public interface BaseSearchInterface<T,V> {
	/**
	 * 
	 * @param input
	 * @return
	 */
	List<V> initSearch(T input) throws Exception;
}