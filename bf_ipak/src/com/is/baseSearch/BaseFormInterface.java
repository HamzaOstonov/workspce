package com.is.baseSearch;

import org.zkoss.zk.ui.Component;

/**
 * Interface designed to draw custom search form
 * 
 * @author root
 *
 */
public interface BaseFormInterface {
	void initComponents() throws Exception;
	/**
	 * Return header for search
	 * @return
	 */
	Component getHeader();
	/**
	 * Return form for search
	 * @return
	 */
	Component getSearchForm();
}
