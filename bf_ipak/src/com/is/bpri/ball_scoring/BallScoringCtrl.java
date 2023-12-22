package com.is.bpri.ball_scoring;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;

import com.is.utils.RefCBox;
import com.is.utils.Res;

public class BallScoringCtrl extends GenericForwardComposer{

	private static final long serialVersionUID = 8506641736238675401L;
	private Div add_div,question_list_div,result_list_div;
	private Toolbar tb,tb_result;
	private Toolbarbutton btn_add;
	private Button btn_result_add,add_result_row;
	private int margin = 0;
	private final int MARGIN = 3;
	private Grid add_result_div;
	private Integer bpr_id = 0;
	private Listbox question_box,result_box;
	private Rows result_rows;
	private String action = "";
	private String result_action = "";
	private Paging paging,result_paging;
	private int _startPageNumber = 0,
				_startPageNumberResult = 0,
				_pageSize = 10,
				_pageSizeResult = 10,
				_totalSize = 0,
				_totalSizeResult = 0;
	private BallScoringModel filter = new BallScoringModel();
	private ResultScoringModel filterResult = new ResultScoringModel();
	private PagingListModel model = null;
	private ResultPagingListModel modelResult = null;
	private String alias;
	
	public BallScoringCtrl() {
		super('$',false,false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		if (session.getAttribute("bpr_id") != null) {
			bpr_id = Integer.parseInt(session.getAttribute("bpr_id")+"");
		}
		if(session.getAttribute("alias") != null) {
			alias = (String) session.getAttribute("alias");
		}
		question_box.setItemRenderer(new ListitemRenderer() {
			
			@Override
			public void render(Listitem li, Object data) throws Exception {
				BallScoringModel ball = (BallScoringModel) data;
				li.setValue(ball);
				li.appendChild(new Listcell(ball.getQ_order()+""));
				li.appendChild(new Listcell(ball.getQuestion_name()));
				li.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
					
					@Override
					public void onEvent(Event evt) throws Exception {
						Listitem li = (Listitem) evt.getTarget();
						action = "double";
						BallScoringModel ball = (BallScoringModel) li.getValue();
						removingGrids();
						divVisible(true);
						margin = 0;
						add_div.appendChild(addDivQuestion(margin, ball));
					}
				});
				Button btn = new Button();
				btn.setImage("/images/delete.png");
				btn.setAttribute("li", li);
				btn.addEventListener(Events.ON_CLICK, new EventListener() {
					
					@Override
					public void onEvent(Event evt) throws Exception {
						Listitem li = (Listitem) ((Button) evt.getTarget()).getAttribute("li");
						BallScoringModel ball = (BallScoringModel) li.getValue();
						Res res = new Res();
						BallScoringService.deleteScoringQuestion(ball, res);
						if(res.getCode()==1){
							alert(res.getName());
						} else {
							alert("Успешно");
						}
						refreshModel(_startPageNumber);
					}
				});
				Listcell lc = new Listcell();
				lc.appendChild(btn);
				li.appendChild(lc);
			}
		});
		result_box.setItemRenderer(new ListitemRenderer() {
			
			@Override
			public void render(Listitem li, Object data) throws Exception {
				ResultScoringModel result = (ResultScoringModel) data;
				li.setValue(result);
				li.appendChild(new Listcell(result.getName_result()));
				li.appendChild(new Listcell(result.getInterval_from()+""));
				li.appendChild(new Listcell(result.getInterval_before()+""));
				li.appendChild(new Listcell(BallScoringService.getIsCloseAnswer(result.getIs_close_credit())));
				Listcell lc = new Listcell();
				Button btn = new Button();
				btn.setImage("/images/delete.png");
				btn.setAttribute("li", li);
				btn.addEventListener(Events.ON_CLICK, new EventListener() {
					
					@Override
					public void onEvent(Event evt) throws Exception {
						BallScoringService.deleteResult((ResultScoringModel) ((Listitem) ((Button) evt.getTarget()).getAttribute("li")).getValue());
						refreshModelResult(_startPageNumberResult);
					}
				});
				lc.appendChild(btn);
				li.appendChild(lc);
				li.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
					
					@Override
					public void onEvent(Event evt) throws Exception {
						try {
							removingGridsResult();
							List<ResultScoringModel> list = BallScoringService.getResults(bpr_id);
							result_action = "double";
							for (int i = 0; i < list.size(); i++) {
								result_rows.appendChild(getResultRow(list.get(i)));
							}
							divVisibleResult(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		refreshModel(_startPageNumber);
		refreshModelResult(_startPageNumberResult);
	}
	
	public void onClick$btn_add(){
		removingGrids();
		divVisible(true);
		add_div.appendChild(addDivQuestion(margin,null));
		action = "add";
	}
	
	public void onClick$btn_result_add(){
		try {
			removingGridsResult();
			divVisibleResult(true);
			result_rows.appendChild(getResultRow(null));
			result_action = "add";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onClick$add_result_row(){
		result_rows.appendChild(getResultRow(null));
	}
	
	public void onClick$btn_cancel(){
		divVisible(false);
		removingGrids();
	}
	
	public void onClick$btn_cancel_result(){
		divVisibleResult(false);
		removingGridsResult();
		refreshModelResult(_startPageNumberResult);
	}
	
	public void onClick$btn_save(){
		Div div = (Div) add_div.getChildren().get(0);
		BallScoringModel question_obj = (BallScoringModel) div.getAttribute("question_obj");
		Res res = new Res();
		if(!action.equals("double")) BallScoringService.insertScoringForm(question_obj,res);
		else BallScoringService.updateScoringForm(question_obj, res);
		if(res.getCode()==1){
			alert(res.getName());
		} else {
			alert("Выполнено успешно");
			onClick$btn_cancel();
		}
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_save_result(){
		if(result_rows.getChildren()!=null&&!result_rows.getChildren().isEmpty()){
			List<ResultScoringModel> list = new ArrayList<ResultScoringModel>(result_rows.getChildren().size());
			Res res = new Res();
			for (int i = 0; i < result_rows.getChildren().size(); i++) {
				Row row = (Row) result_rows.getChildren().get(i);
				ResultScoringModel result = (ResultScoringModel) row.getAttribute("result");
				list.add(result);
			}
			if(result_action.equals("add")){
				BallScoringService.insertResults(list,bpr_id,res);
			}
			if(res.getCode()==1){
				alert(res.getName());
			} else {
				alert("Успешно");
				divVisibleResult(false);
				refreshModelResult(_startPageNumberResult);
			}
		}
	}
	
	
	public void onPaging$paging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	public void onPaging$result_paging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumberResult = pe.getActivePage();
		refreshModelResult(_startPageNumberResult);
	}
	
	private Div addDivQuestion(int margin,BallScoringModel question_obj){
		Div div = new Div();
		if(question_obj == null) question_obj = new BallScoringModel();
		question_obj.setBpr_id(bpr_id);
		div.setAttribute("question_obj", question_obj);
		div.setStyle("margin-left: "+margin+"%");
		div.appendChild(addQuestionGrid(div,question_obj));
		div.appendChild(addInfoAnswer());
		Div answerDiv = addDivAnswer();
		div.appendChild(answerDiv);
		div.appendChild(addBtnAnswer(answerDiv,question_obj));
		return div;
	}
	
	private Div addDivAnswer(){
		Div div = new Div();
		return div;
	}
	
	private Grid addQuestionGrid(Div div,BallScoringModel question_obj){
		Grid grid = new Grid();
		Columns columns = new Columns();
		Column numberQuestion = new Column();
		numberQuestion.setLabel("Порядковый № Вопроса");
		numberQuestion.setWidth("16%");
		numberQuestion.setAlign("center");
		Column question = new Column();
		question.setAlign("center");
		question.setLabel("Вопрос");
		question.setWidth("80%");
		Column delete = new Column();
		delete.setWidth("4%");
		columns.appendChild(numberQuestion);
		columns.appendChild(question);
		columns.appendChild(delete);
		grid.appendChild(columns);
		Rows rows = new Rows();
		Row row = new Row();
		row.setAlign("center");
		Longbox number = new Longbox();
		number.setAttribute("question_obj", question_obj);
		number.setMaxlength(3);
		number.setAttribute("isQuestion", true);
		number.setAttribute("q_id", ( (Long) div.getAttribute("q_id")));
		number.addEventListener(Events.ON_CHANGE, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				Longbox lbox = (Longbox) evt.getTarget();
				BallScoringModel question_obj = (BallScoringModel) lbox.getAttribute("question_obj");
				question_obj.setQ_order(lbox.getValue());
			}
		});
		if(div.getAttribute("a_id")!=null) number.setAttribute("a_id", ( (Long) div.getAttribute("a_id")));
		Textbox txt = new Textbox();
		txt.setAttribute("q_id", ( (Long) div.getAttribute("q_id")));
		txt.setAttribute("question_obj", question_obj);
		txt.addEventListener(Events.ON_CHANGE, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				Textbox txt = (Textbox) evt.getTarget();
				BallScoringModel question_obj = (BallScoringModel) txt.getAttribute("question_obj");
				question_obj.setQuestion_name(txt.getValue());
			}
		});
		if(div.getAttribute("a_id")!=null) txt.setAttribute("a_id", ( (Long) div.getAttribute("a_id")));
		txt.setAttribute("isQuestion", true);
		txt.setWidth("80%");
		Button btn = new Button();
		btn.setAttribute("div", div);
		btn.setImage("/images/delete.png");
		btn.setTooltiptext("Удаление вопроса");
		btn.setAttribute("question_obj", question_obj);
		btn.addEventListener(Events.ON_CLICK, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				try {
					Button btn = (Button) evt.getTarget();
					Div div = (Div) btn.getAttribute("div");
					BallScoringModel question_obj = (BallScoringModel) btn.getAttribute("question_obj");
					question_obj.setDeleted(true);
					Div parent = (Div) div.getParent();
					parent.removeChild(div);
					refreshModel(_startPageNumber);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		if(question_obj!=null){
			txt.setValue(question_obj.getQuestion_name());
			number.setValue(question_obj.getQ_order());
		}
		row.appendChild(number);
		row.appendChild(txt);
		row.appendChild(btn);
		rows.appendChild(row);
		grid.appendChild(rows);
		return grid;
	}
	
	private Grid addInfoAnswer(){
		Grid grid = new Grid();
		Columns columns = new Columns();
		Column type_answer = new Column("Тип ответа");
		type_answer.setWidth("32%");
		Column answer = new Column("Ответ");
		answer.setWidth("32%");
		Column btn_or_ball = new Column("Кнопка или бал");
		btn_or_ball.setWidth("32%");
		Column btn_delete = new Column();
		columns.appendChild(type_answer);
		columns.appendChild(answer);
		columns.appendChild(btn_or_ball);
		columns.appendChild(btn_delete);
		grid.appendChild(columns);
		return grid;
	}
	
	private Div addAnswer(Div parent,AnswerModel answer_obj){
		Div div = new Div();
		try {
			div.setAttribute("margin", margin);
			Grid grid = new Grid();
			Rows rows = new Rows();
			Columns columns = new Columns();
			Column type_answer = new Column();
			type_answer.setWidth("32%");
			Column answer = new Column();
			answer.setWidth("32%");
			Column ball_or_btn = new Column();
			ball_or_btn.setWidth("32%");
			Column delete = new Column();
			delete.setWidth("4%");
			columns.appendChild(type_answer);
			columns.appendChild(answer);
			columns.appendChild(ball_or_btn);
			columns.appendChild(delete);
			columns.setVisible(false);
			Row row = new Row();
			RefCBox rbox = new RefCBox();
			rbox.setModel(new ListModelList(BallScoringService.getQuestionType("")));
			rbox.setAttribute("answer_obj", answer_obj);
			rbox.addEventListener(Events.ON_CHANGE, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					RefCBox rbox = (RefCBox) evt.getTarget();
					AnswerModel answer_obj = (AnswerModel) rbox.getAttribute("answer_obj");
					answer_obj.setType_answer(Integer.parseInt(rbox.getValue()));
					Component component = (Component) rbox.getAttribute(rbox.getValue());
					List<?> list = ((Cell) rbox.getAttribute("cell")).getChildren();
					for (int i = 0; i < list.size(); i++) {
						((Component) list.get(i)).setVisible(false);
					}
					if(component instanceof Longbox) ((Longbox) component).setValue(null);
					component.setVisible(true);
				}
			});
			rbox.addEventListener("onInitRenderLater", new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					try {
						if(action.equals("double")){
							RefCBox rbox = (RefCBox) evt.getTarget();
							AnswerModel answer_obj = (AnswerModel) rbox.getAttribute("answer_obj");
							rbox.setSelecteditem(answer_obj.getType_answer()+"");
							Component component = (Component) rbox.getAttribute(rbox.getValue());
							List<?> list = ((Cell) rbox.getAttribute("cell")).getChildren();
							for (int i = 0; i < list.size(); i++) {
								((Component) list.get(i)).setVisible(false);
							}
							if(component!=null) component.setVisible(true);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			Textbox txt = new Textbox();
			txt.setAttribute("answer_obj", answer_obj);
			txt.addEventListener(Events.ON_CHANGE, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					try {
						Textbox txt = (Textbox) evt.getTarget();
						AnswerModel answer_obj = (AnswerModel) txt.getAttribute("answer_obj");
						answer_obj.setA_name(txt.getValue());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			Button btn = new Button("+");
			btn.setAttribute("div", div);
			btn.setAttribute("answer_obj", answer_obj);
			btn.addEventListener(Events.ON_CLICK, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					try {
						Button btn = (Button) evt.getTarget();
						sendQuestionEvt(btn,null);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			Longbox lbox = new Longbox();
			Cell cell = new Cell();
			cell.setColspan(1);
			cell.appendChild(btn);
			cell.appendChild(lbox);
			btn.setVisible(false);
			lbox.setVisible(false);
			lbox.setAttribute("answer_obj", answer_obj);
			lbox.addEventListener(Events.ON_CHANGE, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					try {
						Longbox lbox = (Longbox) evt.getTarget();
						AnswerModel answer_obj = (AnswerModel) lbox.getAttribute("answer_obj");
						answer_obj.setA_ball(lbox.getValue());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			rbox.setAttribute("2", btn);
			rbox.setAttribute("1", lbox);
			rbox.setAttribute("cell", cell);
			Button btn_delete = new Button();
			btn_delete.setImage("/images/delete.png");
			btn_delete.setTooltiptext("Удаление ответа");
			btn_delete.setAttribute("div", div);
			btn_delete.setAttribute("answer_obj", answer_obj);
			btn_delete.addEventListener(Events.ON_CLICK, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					try {
						Button btn = (Button) evt.getTarget();
						Div div = (Div) btn.getAttribute("div");
						AnswerModel answer_obj = (AnswerModel) btn.getAttribute("answer_obj");
						answer_obj.setDeleted(true);
						Component component = div.getParent();
						component.removeChild(div);
						refreshModel(_startPageNumber);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			if(answer_obj!=null){
				lbox.setValue(answer_obj.getA_ball());
				txt.setValue(answer_obj.getA_name());
			}
			row.appendChild(rbox);
			row.appendChild(txt);
			row.appendChild(cell);
			row.appendChild(btn_delete);
			rows.appendChild(row);
			grid.appendChild(columns);
			grid.appendChild(rows);
			div.appendChild(grid);
			if(action.equals("double")){
				if(answer_obj!=null&&answer_obj.getBallScoringModels()!=null){
					for (int j = 0; j < answer_obj.getBallScoringModels().size(); j++) {
						sendQuestionEvt(btn,answer_obj.getBallScoringModels().get(j));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return div;
	}
	
	private Grid addBtnAnswer(Div div,BallScoringModel question_obj){
		Grid grid = new Grid();
		try {
			Rows rows = new Rows();
			Row row = new Row();
			Button btn = new Button("Добавить ответ");
			btn.setAttribute("div", div);
			div.setAttribute("question_obj", question_obj);
			btn.setAttribute("question_obj", question_obj);
			btn.addEventListener(Events.ON_CLICK, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					try {
						Button btn = (Button) evt.getTarget();
						sendAnswerEvt(btn,null);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			row.appendChild(btn);
			rows.appendChild(row);
			grid.appendChild(rows);
			if(action.equals("double")){
				if(question_obj!=null){
					if(question_obj.getAnswers()!=null){
						for (int i = 0; i < question_obj.getAnswers().size(); i++) {
							sendAnswerEvt(btn,question_obj.getAnswers().get(i));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grid;
	}
	
	private Row getResultRow(ResultScoringModel result){
		Row row = new Row();
		if(result==null) result = new ResultScoringModel();
		row.setAttribute("result", result);
		try {
			Textbox name_result = new Textbox();
			name_result.setMaxlength(50);
			name_result.setAttribute("result", result);
			name_result.setValue(result.getName_result());
			name_result.addEventListener(Events.ON_CHANGE, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					try {
						Textbox txt = (Textbox) evt.getTarget();
						ResultScoringModel result = (ResultScoringModel) txt.getAttribute("result");
						result.setName_result(txt.getValue());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			Longbox interval_from = new Longbox();
			interval_from.setAttribute("result", result);
			interval_from.setValue(result.getInterval_from());
			interval_from.addEventListener(Events.ON_CHANGE, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					try {
						Longbox lbox = (Longbox) evt.getTarget();
						ResultScoringModel result = (ResultScoringModel) lbox.getAttribute("result");
						result.setInterval_from(lbox.getValue());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			Longbox interval_before = new Longbox();
			interval_before.setAttribute("result", result);
			interval_before.setValue(result.getInterval_before());
			interval_before.addEventListener(Events.ON_CHANGE, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					try {
						Longbox lbox = (Longbox) evt.getTarget();
						ResultScoringModel result = (ResultScoringModel) lbox.getAttribute("result");
						result.setInterval_before(lbox.getValue());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			RefCBox rbox = new RefCBox();
			rbox.setModel(new ListModelList(BallScoringService.getResultAnswer(alias)));
			rbox.setAttribute("result", result);
			rbox.addEventListener(Events.ON_CHANGE, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					try {
						RefCBox rbox = (RefCBox) evt.getTarget();
						ResultScoringModel result = (ResultScoringModel) rbox.getAttribute("result");
						result.setIs_close_credit(Integer.parseInt(rbox.getValue()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			rbox.addEventListener("onInitRenderLater", new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					if(result_action.equals("double")) ((RefCBox) evt.getTarget()).setSelecteditem(((ResultScoringModel)((RefCBox) evt.getTarget()).getAttribute("result")).getIs_close_credit()+"");
				}
			});
			Button btn = new Button();
			btn.setAttribute("row", row);
			btn.setImage("/images/delete.png");
			btn.addEventListener(Events.ON_CLICK, new EventListener() {
				
				@Override
				public void onEvent(Event evt) throws Exception {
					result_rows.removeChild((Component)((Button) evt.getTarget()).getAttribute("row"));
				}
			});
			row.appendChild(name_result);
			row.appendChild(interval_from);
			row.appendChild(interval_before);
			row.appendChild(rbox);
			row.appendChild(btn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
	
	private void removingGrids(){
		List<?> list = add_div.getChildren();
		int r_size = list.size()-1;
		for (int i = r_size; i >= 0; i--) {
			if(list.get(i) instanceof Component){
				add_div.removeChild((Component)list.get(i));
			}
		}
	}
	
	private void removingGridsResult(){
		try {
			List<?> list = result_rows.getChildren();
			int r_size = list.size()-1;
			for (int i = r_size; i >= 0; i--) {
				if(list.get(i) instanceof Component){
					result_rows.removeChild((Component)list.get(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendAnswerEvt(Button btn,AnswerModel model){
		try {
			BallScoringModel question_obj = (BallScoringModel) btn.getAttribute("question_obj");
			AnswerModel answer_obj = null;
			if(model==null) {
				if(question_obj.getAnswers()==null) question_obj.setAnswers(new ArrayList<AnswerModel>());
				answer_obj = new AnswerModel();
				answer_obj.setQ_id(question_obj.getQ_id());
				answer_obj.setNew(true);
				question_obj.getAnswers().add(answer_obj);
			} else {
				answer_obj = model;
			}
			Div div = (Div) btn.getAttribute("div");
			Div answer_div = addAnswer(div,answer_obj);
			div.appendChild(answer_div);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendQuestionEvt(Button btn,BallScoringModel ball){
		try {
			Div div = (Div) btn.getAttribute("div");
			AnswerModel answer_obj = (AnswerModel) btn.getAttribute("answer_obj");
			BallScoringModel question_obj = null;
			if(ball==null){
				if(answer_obj.getBallScoringModels()==null) answer_obj.setBallScoringModels(new ArrayList<BallScoringModel>());
				question_obj = new BallScoringModel();
				question_obj.setNew(true);
				question_obj.setMap_id(answer_obj.getA_id());
				answer_obj.getBallScoringModels().add(question_obj);
				answer_obj.setBallScoringModels(answer_obj.getBallScoringModels());
			} else {
				question_obj = ball;
			}
			int margin = (Integer) div.getAttribute("margin");
			margin += MARGIN;
			div.appendChild(addDivQuestion(margin,question_obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void refreshModel(int activePage) {
		try {
			if(filter==null) filter = new BallScoringModel();
			filter.setBpr_id(bpr_id);
			paging.setPageSize(_pageSize);
			model = new PagingListModel(activePage, _pageSize, filter, alias);
			_totalSize = model.getTotalSize(filter, alias);
			paging.setTotalSize(_totalSize);
			question_box.setModel((ListModel) model);
			if (model.getSize() > 0) {
				sendSelEvt();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void refreshModelResult(int activePage) {
		try {
			if(filterResult==null) filterResult = new ResultScoringModel();
			filterResult.setBpr_id(bpr_id);
			result_paging.setPageSize(_pageSizeResult);
			modelResult = new ResultPagingListModel(activePage, _pageSizeResult, filterResult, alias);
			_totalSizeResult = modelResult.getTotalSize(filterResult, alias);
			result_paging.setTotalSize(_totalSizeResult);
			result_box.setModel((ListModel) modelResult);
			if (modelResult.getSize() > 0) {
				sendSelEvtResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendSelEvt() {
		SelectEvent evt = new SelectEvent("onSelect", question_box,question_box.getSelectedItems());
		Events.sendEvent(evt);
	}
	
	private void sendSelEvtResult() {
		SelectEvent evt = new SelectEvent("onSelect", result_box,result_box.getSelectedItems());
		Events.sendEvent(evt);
	}
	
	private void divVisible(boolean bool){
		add_div.setVisible(bool);
		tb.setVisible(bool);
		btn_add.setVisible(!bool);
		question_list_div.setVisible(!bool);
	}
	
	private void divVisibleResult(boolean bool){
		try {
			add_result_div.setVisible(bool);
			tb_result.setVisible(bool);
			add_result_row.setVisible(bool);
			btn_result_add.setVisible(!bool);
			result_list_div.setVisible(!bool);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
