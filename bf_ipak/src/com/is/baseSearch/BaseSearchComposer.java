package com.is.baseSearch;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.*;

import com.is.searchBaseSAP.BaseSAPSearch;

public class BaseSearchComposer extends GenericForwardComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Wired components
	private Tabbox searchTab;
	//private Div main;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		drawTabs();
	}

	private void drawTabs() throws Exception {
		List<AbstractBaseSearchImplementation> list = AbstractBaseSearchImplementation.getImplementors();

		list = sortList(list);

		for (AbstractBaseSearchImplementation implementation : list) {
			implementation.initComponents();
			Component header = implementation.getHeader();
			Component searchForm = implementation.getSearchForm();
			/*Groupbox group = new Groupbox();

			group.appendChild(header);
			group.appendChild(searchForm);
			group.setParent(main);*/

			if (implementation instanceof BaseSAPSearch)
				((Tab)header).setSelected(true);

			searchTab.getFirstChild().appendChild(header);
			searchTab.getLastChild().appendChild(searchForm);
		}
	}

	private List<AbstractBaseSearchImplementation> sortList(List<AbstractBaseSearchImplementation> list) {
		List<AbstractBaseSearchImplementation> returnList = new ArrayList<AbstractBaseSearchImplementation>();
		AbstractBaseSearchImplementation baseSearch = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof BaseSAPSearch) {
				baseSearch = list.get(i);
				continue;
			} else
				returnList.add(list.get(i));
		}
		returnList.add(0, baseSearch);
		return returnList;
	}
}
