package com.is.customer_.search.searchBaseLocal;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.*;

import com.is.baseSearch.AbstractBaseSearchImplementation;
import com.is.baseSearch.AbstractSearchInput;
import com.is.baseSearch.AbstractSearchResult;

public class BaseLocalSearch extends AbstractBaseSearchImplementation {

	// Constants
	private static final String HEADER = "Расширенный Поиск";
	//private static final String SRC = "businesspartner.zul?action=searchBusinessPartner";
	private static final String SRC = "searchLocal.zul";
	// Components
	Tab header;
	Tabpanel searchForm;

	@Override
	public void initComponents() throws Exception {
		// TODO Auto-generated method stub
		header = new Tab(HEADER);
		header.setStyle("font-weight:bold;font-size:20px");
		searchForm = new Tabpanel();
		searchForm.appendChild(new Include(SRC));

		/*header = new Caption(HEADER);
		header.setStyle("font-size:20px");
		//searchForm = new Div();
		searchForm = new Include(SRC);*/
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
