package com.is.delta_relations;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.event.PagingEvent;

import com.is.base.CommonDictionaries;
import com.is.base.Dao;
import com.is.base.utils.Util;
import com.is.base.utils.ZkUtils;
import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.model.PersonMap;
import com.is.client_sap.SapFactory;
import com.is.clients.dao.DaoFactory;
import com.is.utils.RefCBox;
import com.is.utils.RefData;

import lombok.Getter;
import lombok.Setter;

public class DeltaRelationController extends GenericForwardComposer {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(DeltaRelationController.class);
	private Listbox dataGrid;
	private Grid filter_grid;
//	private PagingListModel<DeltaRelation> model;
	private Dao<DeltaRelation> deltaRelationDao; 
	private RefCBox fperson_kind;
	private Paging deltaRelationPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	
	@Getter @Setter private DeltaRelation current = new DeltaRelation();
	@Getter @Setter private DeltaRelationFilter filter = new DeltaRelationFilter();
	private Map<String, String> personKindMap;
	private Map<Integer, String> actionsMap;
	
	private String alias;
	private String ses_branch;
	private DaoFactory daoFactory;
	
	private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy:HH-mm-ss");
	
	public DeltaRelationController() {
		super('$', false, false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		alias = (String) session.getAttribute("alias");
		ses_branch = (String) session.getAttribute("branch");
		daoFactory = DaoFactory.getInstance(alias);
		deltaRelationDao = daoFactory.getDeltaRelationDao();
		setModels();
		
		dataGrid.setItemRenderer(new ListitemRenderer() {
			
			@Override
			public void render(Listitem arg0, Object arg1) throws Exception {
				DeltaRelation rel = (DeltaRelation) arg1;
				arg0.appendChild(new Listcell(rel.getBranch()));
				arg0.appendChild(new Listcell(Integer.toString(rel.getAction())));
				arg0.appendChild(new Listcell(rel.getParentName()));
				arg0.appendChild(new Listcell(rel.getChildName()));
				arg0.appendChild(new Listcell(personKindMap.get(rel.getPersonKind())));
				arg0.appendChild(new Listcell(df.format(rel.getVDate())));
			}
		});
	}
	
	private void setModels() {
		List<RefData> personKinds = CommonDictionaries.getPersonKind(null, alias);
		personKindMap = Util.listToMap(personKinds);
		fperson_kind.setModel(new ListModelList(personKinds));
	}
	
	public void onPaging$deltaRelationPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		deltaRelationPaging.setPageSize(_pageSize);
		filter.setBranch(ses_branch);
		deltaRelationDao.setFilter(filter);
//		model = new PagingListModel<DeltaRelation>(activePage, _pageSize,deltaRelationDao);
		List<DeltaRelation> list = deltaRelationDao.getListWithPaging(activePage, _pageSize);
		if (_needsTotalSizeUpdate) {
			_totalSize = list.size();
			_needsTotalSizeUpdate = false;
		}

		deltaRelationPaging.setTotalSize(deltaRelationDao.getCount());
		logger.error(":::::::::::::::::::::::: refreshModel listr.size = "+ list.size());
		dataGrid.setModel(new BindingListModelList(list, true));
		if (list.size() > 0) {
			this.current = list.get(0);
//			sendSelEvt();
		}
	}
	public void onClick$btn_refresh() {
		refreshModel(_startPageNumber);
	}
	public void onClick$btn_filter() {
		refreshModel(_startPageNumber);
	}
	public void onClick$btn_filter_clear() {
		ZkUtils.clearForm(filter_grid);
		filter = new DeltaRelationFilter();
	}
	
	public void onClick$btn_send() {
		PersonMap personMap = daoFactory.getPersonMapDao().getItemByLongId(null,current.getIdPersonMap());
		if(personMap == null 
				|| (personMap.getPerson_type().equals(PersonMapUtil.PERSONTYPE_J) && personMap.getLegalEntity() == null)
				|| (personMap.getPerson_type().equals(PersonMapUtil.PERSONTYPE_P) && personMap.getPerson() == null)) {
			alert("Ошибка! Связанное лицо не найдено!");
			return;
		}
		try {
			if(current.getAction() == 1) {
				SapFactory.instance().getRelationService().createRelation(personMap);
			} else if(current.getAction() == 2) {
				SapFactory.instance().getRelationService().deleteRelation(personMap);
			}
			current.setState(2);
			daoFactory.getDeltaRelationDao().update(current);
			refreshModel(0);
		} catch(Exception e) {
			alert(e.getMessage());
		}
	}
	
	
	
}
