package com.is.searchBaseSAP;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Include;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanel;

import com.is.baseSearch.AbstractBaseSearchImplementation;
import com.is.baseSearch.AbstractSearchInput;
import com.is.baseSearch.AbstractSearchResult;

public class BaseSAPSearch extends AbstractBaseSearchImplementation {
	Tabpanel searchForm;
	Tab header;

	@Override
	public void initComponents() throws Exception {
		// TODO Auto-generated method stub
		searchForm = new Tabpanel();
		searchForm.appendChild(new Include("searchSap.zul"));

		header = new Tab("SAP Поиск");
		header.setStyle("font-weight:bold;font-size:20px");
		/*header = new Caption("Поиск");
		header.setStyle("font-weight:bold;font-size:20px");
		searchForm = new Include("searchSap.zul");*/
	}

	@Override
	public Component getHeader() {
		return header;
	}

	@Override
	public Component getSearchForm() {
		return searchForm;
	}

	@Override
	public List<AbstractSearchResult> initSearch(AbstractSearchInput input) throws Exception {
		return null;
	}
}
