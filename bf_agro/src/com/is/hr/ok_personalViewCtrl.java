package com.is.hr;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
//import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Include;
//import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.accounts.AccountService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class ok_personalViewCtrl extends GenericForwardComposer {
	private Div frm;
	private Listbox dataGrid;
	private Paging contactPaging;
	private Div grd;
	private Grid frmgrd, frmgrd2, frmgrd3, frmgrd4, frmgrd5, frmgrd6, frmgrd7;
	private Grid addgrd, addgrd2, addgrd3, addgrd4, addgrd5, addgrd6, addgrd7;
	private Grid fgrd, fgrd2, fgrd3, fgrd4, fgrd5, fgrd6, fgrd7;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add, btn_additional, btn_delete, btn_cancel;
	private Toolbarbutton btn_save, btn_fsearch;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back;
	private Toolbarbutton btn_rollback;
	private Toolbarbutton btn_print;
	private Toolbarbutton btn_excel;
	private Toolbarbutton btn_addition;
	private Toolbarbutton btn_salary;
//    private Toolbarbutton
//    private Toolbarbutton
	private Toolbar tb;
	private Toolbar tb1;
	private Toolbar cl_tabs;
	private Include pay_calc_LOG;
	private Include ok_relation;
	private Include ok_period;
	private Include ok_education;
	private Include ok_academic;
	private Include ok_army;
	private Include ok_addinform;
	private Include ok_award;
	private Include ok_change_fio;
	private DecimalFormat dmf = new DecimalFormat("###############");

	private RefCBox branch;

	private RefCBox status_code, gender_code, region_id, distr, nationality_code, family_status_code, reg_type_code,
			emp_code;
	private RefCBox astatus_code, agender_code, afamily_status_code, areg_type_code, aregion_id, adistr,
			anationality_code, aemp_code;
	private RefCBox fstatus_code, fgender_code, ffamily_status_code, freg_type_code, fregion_id, fdistr,
			fnationality_code, femp_code;

	private List<RefData> s_mfoall = new ArrayList<RefData>();
	private List<RefData> ss_ok_status = new ArrayList<RefData>();
	private List<RefData> ss_ok_gender = new ArrayList<RefData>();
	private List<RefData> s_region = new ArrayList<RefData>();
	private List<RefData> s_distr = new ArrayList<RefData>();
	private List<RefData> ss_ok_nationality = new ArrayList<RefData>();
	private List<RefData> ss_ok_family_status = new ArrayList<RefData>();
	private List<RefData> ss_ok_reg_type = new ArrayList<RefData>();
	private List<RefData> users = new ArrayList<RefData>();

	private String alias, un, pw;

	private Tabbox Details_TabBox;
	private Textbox pass_reg, family, first_name, patronymic, home_address, home_addressfact, pass_seriya, pass_num,
			record_book_number, record_book_series, telefon, birthplace, motive_out, basis_num, cod_distr_uvd,
			cod_distr_prim, cod_str_birth, cod_obl_prim, cod_place_birth_prim, cod_sitizent, cod_str_sitizent,
			cod_str_live, cod_obl_live, cod_obl_live_prim, cod_distr_live, cod_distr_live_prim, code_naci_prim,
			check_kfs, is_boss, bank_spec, notice, prich_id, name_pr, home_addressfact_region_id,
			home_addressfact_distr, home_address_region_id, home_address_distr, live_place, birthplace_point,
			maiden_family, user_name, family_lat, first_name_lat, patronymic_lat, comments, emp_code_name, id_client,
			gph_order_num, email, profession_personal, bxm;

	private Textbox apass_reg, abranch, afamily, afirst_name, apatronymic, ahome_address, ahome_addressfact,
			apass_seriya, apass_num, arecord_book_number, arecord_book_series, atelefon, abirthplace, amotive_out,
			abasis_num, acod_distr_uvd, acod_distr_prim, acod_str_birth, acod_obl_prim, acod_place_birth_prim,
			acod_sitizent, acod_str_sitizent, acod_str_live, acod_obl_live, acod_obl_live_prim, acod_distr_live,
			acod_distr_live_prim, acode_naci_prim, acheck_kfs, ais_boss, abank_spec, anotice, aprich_id, aname_pr,
			ahome_addressfact_region_id, ahome_addressfact_distr, ahome_address_region_id, ahome_address_distr,
			alive_place, abirthplace_point, amaiden_family, auser_name, afamily_lat, afirst_name_lat, apatronymic_lat,
			acomments, aemp_code_name, aid_client, agph_order_num, aemail, aprofession_personal, abxm;

	private Textbox fpass_reg, fbranch, ffamily, ffirst_name, fpatronymic, fhome_address, fhome_addressfact,
			fpass_seriya, fpass_num, frecord_book_number, frecord_book_series, ftelefon, fbirthplace, fmotive_out,
			fbasis_num, fcod_distr_uvd, fcod_distr_prim, fcod_str_birth, fcod_obl_prim, fcod_place_birth_prim,
			fcod_sitizent, fcod_str_sitizent, fcod_str_live, fcod_obl_live, fcod_obl_live_prim, fcod_distr_live,
			fcod_distr_live_prim, fcode_naci_prim, fcheck_kfs, fis_boss, fbank_spec, fnotice, fprich_id, fname_pr,
			fhome_addressfact_region_id, fhome_addressfact_distr, fhome_address_region_id, fhome_address_distr,
			flive_place, fbirthplace_point, fmaiden_family, fuser_name, ffamily_lat, ffirst_name_lat, fpatronymic_lat,
			fcomments, femp_code_name, fid_client, fgph_order_num, femail, fprofession_personal, fbxm;

	public  ok_personalFilter filter;

	private Textbox id, personal_code, profmember, trud_sogl, is_party, is_academic, is_degree, is_award, is_scientific,
			is_voyage, is_convictions, is_language, is_rise, is_election, is_premium, is_army, gph_rezident;
	private Textbox aid, apersonal_code, aprofmember, atrud_sogl, ais_party, ais_academic, ais_degree, ais_award,
			ais_scientific, ais_voyage, ais_convictions, ais_language, ais_rise, ais_election, ais_premium, ais_army,
			agph_rezident;
	private Textbox fid, fpersonal_code, fprofmember, ftrud_sogl, fis_party, fis_academic, fis_degree, fis_award,
			fis_scientific, fis_voyage, fis_convictions, fis_language, fis_rise, fis_election, fis_premium, fis_army,
			fgph_rezident;

	private Textbox gosubmit_code, tabel_line, gph_summa;
	private Textbox agosubmit_code, atabel_line, agph_summa;
	private Textbox fgosubmit_code, ftabel_line, fgph_summa;

	private Textbox colleague_code, salary_code, tabno, education_title_code, leave_code, department_code, post_code,
			nn, motive_dismissial_code, nps_id, special_code, inn, passport_type_code, regplace_code,
			department_code_old, post_code_old, special_code_old, spec_ikki, department_code_new, post_code_new,
			special_code_new, maidenstag, zp_rate_code, user_id, gph_post_code, gph_department_code, zp_rate_code_old,
			zp_rate_code_new, institution_code, institution_end_year;
	private Textbox acolleague_code, asalary_code, atabno, aeducation_title_code, aleave_code, adepartment_code,
			apost_code, ann, amotive_dismissial_code, anps_id, aspecial_code, ainn, apassport_type_code, aregplace_code,
			adepartment_code_old, apost_code_old, aspecial_code_old, aspec_ikki, adepartment_code_new, apost_code_new,
			aspecial_code_new, amaidenstag, azp_rate_code, auser_id, agph_post_code, agph_department_code,
			azp_rate_code_old, azp_rate_code_new, ainstitution_code, ainstitution_end_year;
	private Textbox fcolleague_code, fsalary_code, ftabno, feducation_title_code, fleave_code, fdepartment_code,
			fpost_code, fnn, fmotive_dismissial_code, fnps_id, fspecial_code, finn, fpassport_type_code, fregplace_code,
			fdepartment_code_old, fpost_code_old, fspecial_code_old, fspec_ikki, fdepartment_code_new, fpost_code_new,
			fspecial_code_new, fmaidenstag, fzp_rate_code, fuser_id, fgph_post_code, fgph_department_code,
			fzp_rate_code_old, fzp_rate_code_new, finstitution_code, finstitution_end_year;

	private Datebox birthday, pass_date, ins_date, basis_date, srok_date, pass_date_end, reg_date_end, application_date,
			reply_date, gph_order_date, gph_date_end, gph_date_begin;
	private Datebox abirthday, apass_date, ains_date, abasis_date, asrok_date, apass_date_end, areg_date_end,
			aapplication_date, areply_date, agph_order_date, agph_date_end, agph_date_begin;
	private Datebox fbirthday, fpass_date, fins_date, fbasis_date, fsrok_date, fpass_date_end, freg_date_end,
			fapplication_date, freply_date, fgph_order_date, fgph_date_end, fgph_date_begin;

	private Paging ok_personalPaging;
	private int _pageSize = 10;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private Ok_personal current2 = new Ok_personal();

	PagingListModel_ok_personal model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	private Ok_personal current0 = new Ok_personal();
	private pay_calc_LOG cur = new pay_calc_LOG();

	public ok_personalViewCtrl() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		// TODO Auto-generated method stub
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current0);
		binder.loadAll();
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null) {
			_pageSize = Integer.parseInt(parameter[0]) / 36;
			dataGrid.setRows((Integer.parseInt(parameter[0]) - 210) / 40);
			parameter = (String[]) param.get("pid");
		}
		un = (String) session.getAttribute("un");
		pw = (String) session.getAttribute("pwd");
		alias = (String) session.getAttribute("alias");
		init();

		dataGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				Ok_personal pok_personal = (Ok_personal) data;

				row.setValue(pok_personal);

				row.appendChild(new Listcell(pok_personal.getId() + ""));
				row.appendChild(new Listcell(pok_personal.getBranch()));
				row.appendChild(new Listcell(pok_personal.getPersonal_code() + ""));
				row.appendChild(new Listcell(pok_personal.getColleague_code() + ""));
				row.appendChild(new Listcell(pok_personal.getStatus_code() + ""));
				row.appendChild(new Listcell(pok_personal.getSalary_code() + ""));
				row.appendChild(new Listcell(pok_personal.getFamily()));
				row.appendChild(new Listcell(pok_personal.getFirst_name()));
				row.appendChild(new Listcell(pok_personal.getPatronymic()));
				row.appendChild(new Listcell(pok_personal.getGender_code() + ""));				
				row.appendChild(new Listcell(pok_personal.getBirthday() != null ? df.format(pok_personal.getBirthday()) : ""));		//				
//				row.appendChild(new Listcell(pok_personal.getBirthday() + ""));
				row.appendChild(new Listcell(pok_personal.getRegion_id()));
				row.appendChild(new Listcell(pok_personal.getDistr()));
				row.appendChild(new Listcell(pok_personal.getNationality_code() + ""));
				row.appendChild(new Listcell(pok_personal.getFamily_status_code() + ""));
				row.appendChild(new Listcell(pok_personal.getReg_type_code() + ""));
				row.appendChild(new Listcell(pok_personal.getHome_address()));
				row.appendChild(new Listcell(pok_personal.getHome_addressfact()));
				row.appendChild(new Listcell(pok_personal.getPass_seriya()));
				row.appendChild(new Listcell(pok_personal.getPass_num()));				
				row.appendChild(new Listcell(pok_personal.getPass_date() != null ? df.format(pok_personal.getPass_date()) : ""));  		//				
//				row.appendChild(new Listcell(pok_personal.getPass_date() + ""));
				row.appendChild(new Listcell(pok_personal.getPass_reg()));
				row.appendChild(new Listcell(pok_personal.getRecord_book_number()));
				row.appendChild(new Listcell(pok_personal.getRecord_book_series()));
				row.appendChild(new Listcell(pok_personal.getTelefon()));
				row.appendChild(new Listcell(pok_personal.getEmp_code() + ""));			
				row.appendChild(new Listcell(pok_personal.getIns_date() != null ? df.format(pok_personal.getIns_date()) : "")); 		//				
				row.appendChild(new Listcell(pok_personal.getIns_date() + ""));
				row.appendChild(new Listcell(pok_personal.getProfmember() + ""));
				row.appendChild(new Listcell(pok_personal.getTabno() + ""));
				row.appendChild(new Listcell(pok_personal.getEducation_title_code() + ""));
				row.appendChild(new Listcell(pok_personal.getBirthplace()));
				row.appendChild(new Listcell(pok_personal.getMotive_out()));
				row.appendChild(new Listcell(pok_personal.getBasis_num()));			
				row.appendChild(new Listcell(pok_personal.getBasis_date() != null ? df.format(pok_personal.getBasis_date()) : "")); //				
//				row.appendChild(new Listcell(pok_personal.getBasis_date() + ""));
				row.appendChild(new Listcell(pok_personal.getLeave_code() + ""));
				row.appendChild(new Listcell(pok_personal.getDepartment_code() + ""));
				row.appendChild(new Listcell(pok_personal.getPost_code() + ""));
				row.appendChild(new Listcell(pok_personal.getNn() + ""));
				row.appendChild(new Listcell(pok_personal.getMotive_dismissial_code() + ""));
				row.appendChild(new Listcell(pok_personal.getNps_id() + ""));            //		row.appendChild(new Listcell(!CheckNull.isEmpty(String.valueOf(pok_personal.getNps_id()))? dmf.format(pok_personal.getNps_id())	: "--//--"));
				row.appendChild(new Listcell(pok_personal.getSpecial_code() + ""));
				row.appendChild(new Listcell(pok_personal.getInn() + ""));				//		row.appendChild(new Listcell(!CheckNull.isEmpty(String.valueOf(pok_personal.getInn())) ? dmf.format(pok_personal.getInn()): "--//--"));
				row.appendChild(new Listcell(pok_personal.getCod_distr_uvd()));
				row.appendChild(new Listcell(pok_personal.getCod_distr_prim()));
				row.appendChild(new Listcell(pok_personal.getCod_str_birth()));
				row.appendChild(new Listcell(pok_personal.getCod_obl_prim()));
				row.appendChild(new Listcell(pok_personal.getCod_place_birth_prim()));
				row.appendChild(new Listcell(pok_personal.getCod_sitizent()));
				row.appendChild(new Listcell(pok_personal.getCod_str_sitizent()));
				row.appendChild(new Listcell(pok_personal.getCod_str_live()));
				row.appendChild(new Listcell(pok_personal.getCod_obl_live()));
				row.appendChild(new Listcell(pok_personal.getCod_obl_live_prim()));
				row.appendChild(new Listcell(pok_personal.getCod_distr_live()));
				row.appendChild(new Listcell(pok_personal.getCod_distr_live_prim()));
				row.appendChild(new Listcell(pok_personal.getCode_naci_prim()));
				row.appendChild(new Listcell(pok_personal.getCheck_kfs()));
				row.appendChild(new Listcell(pok_personal.getIs_boss()));
				row.appendChild(new Listcell(pok_personal.getBank_spec()));
				row.appendChild(new Listcell(pok_personal.getNotice()));
				row.appendChild(new Listcell(pok_personal.getPrich_id()));
				row.appendChild(new Listcell(pok_personal.getName_pr()));
				row.appendChild(new Listcell(pok_personal.getSrok_date() + ""));
				row.appendChild(new Listcell(pok_personal.getHome_addressfact_region_id()));
				row.appendChild(new Listcell(pok_personal.getHome_addressfact_distr()));
				row.appendChild(new Listcell(pok_personal.getHome_address_region_id()));
				row.appendChild(new Listcell(pok_personal.getHome_address_distr()));
				row.appendChild(new Listcell(pok_personal.getPassport_type_code() + ""));
				row.appendChild(new Listcell(pok_personal.getRegplace_code() + ""));
				row.appendChild(new Listcell(pok_personal.getPass_date_end() != null ? df.format(pok_personal.getPass_date_end()) : "")); 				//				
//				row.appendChild(new Listcell(pok_personal.getPass_date_end() + ""));
				row.appendChild(new Listcell(pok_personal.getGosubmit_code() + ""));
				row.appendChild(new Listcell(pok_personal.getLive_place()));
				row.appendChild(new Listcell(pok_personal.getBirthplace_point()));
				row.appendChild(new Listcell(pok_personal.getMaiden_family()));
				row.appendChild(new Listcell(pok_personal.getDepartment_code_old() + ""));
				row.appendChild(new Listcell(pok_personal.getPost_code_old() + ""));
				row.appendChild(new Listcell(pok_personal.getSpecial_code_old() + ""));
				row.appendChild(new Listcell(pok_personal.getSpec_ikki() + ""));
				row.appendChild(new Listcell(pok_personal.getDepartment_code_new() + ""));
				row.appendChild(new Listcell(pok_personal.getPost_code_new() + ""));
				row.appendChild(new Listcell(pok_personal.getSpecial_code_new() + ""));				
				row.appendChild(new Listcell(pok_personal.getReg_date_end() != null ? df.format(pok_personal.getReg_date_end()) : ""));    //				
//				row.appendChild(new Listcell(pok_personal.getReg_date_end() + ""));
				row.appendChild(new Listcell(pok_personal.getTrud_sogl() + ""));				
				row.appendChild(new Listcell(pok_personal.getApplication_date() != null ? df.format(pok_personal.getApplication_date()) : "")); //				
//				row.appendChild(new Listcell(pok_personal.getApplication_date() + ""));//				
				row.appendChild(new Listcell(pok_personal.getReply_date() != null ? df.format(pok_personal.getReply_date()) : ""));			//				
//				row.appendChild(new Listcell(pok_personal.getReply_date() + ""));
				row.appendChild(new Listcell(pok_personal.getIs_party() + ""));
				row.appendChild(new Listcell(pok_personal.getIs_academic() + ""));
				row.appendChild(new Listcell(pok_personal.getIs_degree() + ""));
				row.appendChild(new Listcell(pok_personal.getIs_award() + ""));
				row.appendChild(new Listcell(pok_personal.getIs_scientific() + ""));
				row.appendChild(new Listcell(pok_personal.getIs_voyage() + ""));
				row.appendChild(new Listcell(pok_personal.getIs_convictions() + ""));
				row.appendChild(new Listcell(pok_personal.getIs_language() + ""));
				row.appendChild(new Listcell(pok_personal.getIs_rise() + ""));
				row.appendChild(new Listcell(pok_personal.getIs_election() + ""));
				row.appendChild(new Listcell(pok_personal.getIs_premium() + ""));
				row.appendChild(new Listcell(pok_personal.getIs_army() + ""));
				row.appendChild(new Listcell(pok_personal.getUser_name()));
				row.appendChild(new Listcell(pok_personal.getMaidenstag() + ""));
				row.appendChild(new Listcell(pok_personal.getZp_rate_code() + ""));
				row.appendChild(new Listcell(pok_personal.getFamily_lat()));
				row.appendChild(new Listcell(pok_personal.getFirst_name_lat()));
				row.appendChild(new Listcell(pok_personal.getPatronymic_lat()));
				row.appendChild(new Listcell(pok_personal.getComments()));
				row.appendChild(new Listcell(pok_personal.getEmp_code_name()));
				row.appendChild(new Listcell(pok_personal.getTabel_line() + ""));
				row.appendChild(new Listcell(pok_personal.getId_client()));
				row.appendChild(new Listcell(pok_personal.getUser_id() + ""));
				row.appendChild(new Listcell(pok_personal.getGph_date_begin() != null ? df.format(pok_personal.getGph_date_begin()) : ""));
//				row.appendChild(new Listcell(pok_personal.getGph_date_begin() + ""));
				row.appendChild(new Listcell(pok_personal.getGph_date_end() != null ? df.format(pok_personal.getGph_date_end()) : ""));
//				row.appendChild(new Listcell(pok_personal.getGph_date_end() + ""));
				row.appendChild(new Listcell(pok_personal.getGph_summa() + ""));
				row.appendChild(new Listcell(pok_personal.getGph_post_code() + ""));
				row.appendChild(new Listcell(pok_personal.getGph_department_code() + ""));
				row.appendChild(new Listcell(pok_personal.getGph_order_date() != null ? df.format(pok_personal.getGph_order_date()) : ""));
//				row.appendChild(new Listcell(pok_personal.getGph_order_date() + ""));
				row.appendChild(new Listcell(pok_personal.getGph_order_num()));
				row.appendChild(new Listcell(pok_personal.getGph_rezident() + ""));
				row.appendChild(new Listcell(pok_personal.getEmail()));
				row.appendChild(new Listcell(pok_personal.getZp_rate_code_old() + ""));
				row.appendChild(new Listcell(pok_personal.getZp_rate_code_new() + ""));
				row.appendChild(new Listcell(pok_personal.getProfession_personal()));
				row.appendChild(new Listcell(pok_personal.getBxm()));
				row.appendChild(new Listcell(pok_personal.getInstitution_code() + ""));
				row.appendChild(new Listcell(pok_personal.getInstitution_end_year() + ""));
			}
		});
		refreshModel_1(_startPageNumber);
	}

	private void init() {
		s_mfoall = ok_personalService.getS_MfoAll(alias);
		ss_ok_status = ok_personalService.getSS_ok_status(alias);
		ss_ok_gender = ok_personalService.getSS_ok_gender(alias);
		s_region = ok_personalService.getS_region(alias);
		s_distr = ok_personalService.getS_distr(alias);
		ss_ok_nationality = ok_personalService.getSS_ok_nationality(alias);
		ss_ok_family_status = ok_personalService.getSS_ok_family_status(alias);
		ss_ok_reg_type = ok_personalService.getSS_ok_reg_type(alias);
//	users = ok_personalService.getUsers(alias);

		branch.setModel(new ListModelList(s_mfoall));

		status_code.setModel(new ListModelList(ss_ok_status));
		gender_code.setModel(new ListModelList(ss_ok_gender));
		region_id.setModel(new ListModelList(s_region));
		distr.setModel(new ListModelList(s_distr));
		nationality_code.setModel(new ListModelList(ss_ok_nationality));
		family_status_code.setModel(new ListModelList(ss_ok_family_status));
		reg_type_code.setModel(new ListModelList(ss_ok_reg_type));
		emp_code.setModel(new ListModelList(users));

		astatus_code.setModel(new ListModelList(ss_ok_status));
		agender_code.setModel(new ListModelList(ss_ok_gender));
		aregion_id.setModel(new ListModelList(s_region));
		adistr.setModel(new ListModelList(s_distr));
		anationality_code.setModel(new ListModelList(ss_ok_nationality));
		afamily_status_code.setModel(new ListModelList(ss_ok_family_status));
		areg_type_code.setModel(new ListModelList(ss_ok_reg_type));
		aemp_code.setModel(new ListModelList(users));

		fstatus_code.setModel(new ListModelList(ss_ok_status));
		fgender_code.setModel(new ListModelList(ss_ok_gender));
		fregion_id.setModel(new ListModelList(s_region));
		fdistr.setModel(new ListModelList(s_distr));
		fnationality_code.setModel(new ListModelList(ss_ok_nationality));
		ffamily_status_code.setModel(new ListModelList(ss_ok_family_status));
		freg_type_code.setModel(new ListModelList(ss_ok_reg_type));
		femp_code.setModel(new ListModelList(users));
	}

	public void onPaging$ok_personalPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {

		ok_personalPaging.setPageSize(_pageSize);
		model = new PagingListModel_ok_personal(activePage, _pageSize, filter, alias);

		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize(filter, alias);
//        _needsTotalSizeUpdate = false;
		}

		ok_personalPaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current0 = (Ok_personal) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	private void refreshModel_1(int activePage){
        
        ok_personalPaging.setPageSize(_pageSize);
    model = new PagingListModel_ok_personal(activePage, _pageSize, filter, alias );

    if(_needsTotalSizeUpdate) {
            _totalSize = model.getTotalSize(filter, alias);
            _needsTotalSizeUpdate = false;
    }

    ok_personalPaging.setTotalSize(_totalSize);

    dataGrid.setModel((ListModel) model);
    if (model.getSize()>0){
    this.current0 =(Ok_personal) model.getElementAt(0);
    sendSelEvt();
        }
    }

	public Ok_personal getCurrent() {
		return current0;
	}

	public void setCurrent(Ok_personal current) {
		this.current0 = current;
	}

	public void onDoubleClick$dataGrid$grd() {

		Details_TabBox.setVisible(true);
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		frmgrd2.setVisible(true);
		frmgrd3.setVisible(true);
		frmgrd4.setVisible(true);
		frmgrd5.setVisible(true);
		frmgrd6.setVisible(true);
		frmgrd7.setVisible(true);

		btn_additional.setVisible(false);
		btn_save.setVisible(true);
		btn_delete.setVisible(true);
		btn_cancel.setVisible(true);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
//		Ok_personal item = (Ok_personal) dataGrid.getSelectedItem().getValue();
		Ok_personal current = (Ok_personal) dataGrid.getSelectedItem().getValue();
		System.out.println("ok_relation " + current.getId());
		fgrd.setVisible(false);
		fgrd2.setVisible(false);
		fgrd3.setVisible(false);
		fgrd4.setVisible(false);
		fgrd5.setVisible(false);
		fgrd6.setVisible(false);
		fgrd7.setVisible(false);
		
		
//		ok_relation.setSrc("ok_relation.zul?ppid=" + current0.getPersonal_code());
		System.out.println("ok_relation " + current0.getPersonal_code());
//		ok_period.setSrc("ok_period.zul?ppid=" + current0.getPersonal_code());
		System.out.println("ok_period " + current0.getPersonal_code());
//		ok_education.setSrc("ok_education.zul?ppid=" + current0.getPersonal_code());
		System.out.println("ok_education " + current0.getPersonal_code());
//		ok_academic.setSrc("ok_academic.zul?ppid=" + current0.getPersonal_code());
		System.out.println("ok_academic " + current0.getPersonal_code());
//		ok_army.setSrc("ok_army.zul?ppid=" + current0.getPersonal_code());
		System.out.println("ok_academic " + current0.getPersonal_code());
//		ok_addinform.setSrc("ok_addinform.zul?ppid=" + current0.getPersonal_code());
		System.out.println("ok_addinform " + current0.getPersonal_code());
//		ok_award.setSrc("ok_award.zul?ppid=" + current0.getPersonal_code());
		System.out.println("ok_award " + current0.getPersonal_code());

		System.out.println("ok_change_fio " + current0.getPersonal_code());
	}

	public void onClick$btn_back() {
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
		} else
			onDoubleClick$dataGrid$grd();
	}

	public void onClick$btn_first() {
		dataGrid.setSelectedIndex(0);
		sendSelEvt();
	}

	public void onClick$btn_last() {
		dataGrid.setSelectedIndex(model.getSize() - 1);
		sendSelEvt();
	}

	public void onClick$btn_prev() {
		if (dataGrid.getSelectedIndex() != 0) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() - 1);
			sendSelEvt();
		}
	}

	public void onClick$btn_next() {
		if (dataGrid.getSelectedIndex() != (model.getSize() - 1)) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() + 1);
			sendSelEvt();
		}
	}

//public void onClick$btn_rollback() {
//	alert("В работе ");
//}
//public void onClick$btn_print(){
//	alert("В работе ");
//}
//public void onClick$btn_excel(){
//	alert("В работе ");
//}
//public void onClick$btn_addition() {
//	alert("В работе ");
//}

	public void onClick$btn_salary() {
		ok_personalService.salary(un, pw, alias, cur);
	}

	private void sendSelEvt() {
		if (dataGrid.getSelectedIndex() == 0) {
			btn_first.setDisabled(true);
			btn_prev.setDisabled(true);
		} else {
			btn_first.setDisabled(false);
			btn_prev.setDisabled(false);
		}
		if (dataGrid.getSelectedIndex() == (model.getSize() - 1)) {
			btn_next.setDisabled(true);
			btn_last.setDisabled(true);
		} else {
			btn_next.setDisabled(false);
			btn_last.setDisabled(false);
		}
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_add() throws ParseException {
//		onDoubleClick$dataGrid$grd();//yangi qo'shildi
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(false);
		frmgrd2.setVisible(false);
		frmgrd3.setVisible(false);
		frmgrd4.setVisible(false);
		frmgrd5.setVisible(false);
		frmgrd6.setVisible(false);
		frmgrd7.setVisible(false);

		addgrd.setVisible(true);
		addgrd2.setVisible(true);
		addgrd3.setVisible(true);
		addgrd4.setVisible(true);
		addgrd5.setVisible(true);
		addgrd6.setVisible(true);
		addgrd7.setVisible(true);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

		String dateInString = "28-Nov-2003";
		Date date = null;
		date = formatter.parse(dateInString);
		
		
		abranch.setValue("00395");
		afirst_name.setValue("Habibulloh");
		apass_seriya.setValue("AC");
		apass_num.setValue("2512586");
		aprofmember.setValue("1");
		ainn.setValue("1256483");
		anps_id.setValue("528110325260047");
		atrud_sogl.setValue("1");
		ais_academic.setValue("1");
		ais_army.setValue("1");
		ais_award.setValue("1");
		ais_boss.setValue("1");
		ais_convictions.setValue("1");
		ais_degree.setValue("1");
		ais_election.setValue("1");
		ais_language.setValue("1");
		ais_party.setValue("1");
		ais_premium.setValue("1");
		ais_rise.setValue("1");
		ais_scientific.setValue("1");
		agph_summa.setValue("123");
		ainstitution_end_year.setValue("2023");
		abirthday.setValue(date);
		apass_date.setValue(date);
		ains_date.setValue(date);
		abasis_date.setValue(date);
		asrok_date.setValue(date);
		apass_date_end.setValue(date);
		areg_date_end.setValue(date);
		aapplication_date.setValue(date);
		areply_date.setValue(date);
		agph_date_begin.setValue(date);
		agph_date_end.setValue(date);
		agph_order_date.setValue(date);
		
		fgrd.setVisible(false);
		fgrd2.setVisible(false);
		fgrd3.setVisible(false);
		fgrd4.setVisible(false);
		fgrd5.setVisible(false);
		fgrd6.setVisible(false);
		fgrd7.setVisible(false);

		btn_additional.setVisible(true);
		btn_cancel.setVisible(true);
		btn_fsearch.setVisible(false);
		btn_save.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(addgrd2);
		CheckNull.clearForm(addgrd3);
		CheckNull.clearForm(addgrd4);
		CheckNull.clearForm(addgrd5);
		CheckNull.clearForm(addgrd6);
		CheckNull.clearForm(addgrd7);
	}

	public void onClick$btn_additional() {
		try {
			System.out.println("<--- btn_additional dan keyin yozildi --->");
			current0 = new Ok_personal();
			current0.setId(aid.getValue());
			current0.setBranch(abranch.getValue());
			current0.setPersonal_code(apersonal_code.getValue());
			current0.setColleague_code(acolleague_code.getValue());
			current0.setStatus_code(astatus_code.getValue());
			current0.setSalary_code(asalary_code.getValue());
			current0.setFamily(afamily.getValue());
			current0.setFirst_name(afirst_name.getValue());
			current0.setPatronymic(apatronymic.getValue());
			current0.setGender_code(agender_code.getValue());
			current0.setBirthday(abirthday.getValue());
			current0.setRegion_id(aregion_id.getValue());
			current0.setDistr(adistr.getValue());
			current0.setNationality_code(anationality_code.getValue());
			current0.setFamily_status_code(afamily_status_code.getValue());
			current0.setReg_type_code(areg_type_code.getValue());
			current0.setHome_address(ahome_address.getValue());
			current0.setHome_addressfact(ahome_addressfact.getValue());
			current0.setPass_seriya(apass_seriya.getValue());
			current0.setPass_num(apass_num.getValue());
			current0.setPass_date(apass_date.getValue());
			current0.setPass_reg(apass_reg.getValue());
			current0.setRecord_book_number(arecord_book_number.getValue());
			current0.setRecord_book_series(arecord_book_series.getValue());
			current0.setTelefon(atelefon.getValue());
			current0.setEmp_code(aemp_code.getValue());
			current0.setIns_date(ains_date.getValue());
			current0.setProfmember(aprofmember.getValue());
			current0.setTabno(atabno.getValue());
			current0.setEducation_title_code(aeducation_title_code.getValue());
			current0.setBirthplace(abirthplace.getValue());
			current0.setMotive_out(amotive_out.getValue());
			current0.setBasis_num(abasis_num.getValue());
			current0.setBasis_date(abasis_date.getValue());
			current0.setLeave_code(aleave_code.getValue());
			current0.setDepartment_code(adepartment_code.getValue());
			current0.setPost_code(apost_code.getValue());
			current0.setNn(ann.getValue());
			current0.setMotive_dismissial_code(amotive_dismissial_code.getValue());
			current0.setNps_id(anps_id.getValue());
			current0.setSpecial_code(aspecial_code.getValue());
			current0.setInn(ainn.getValue());
			current0.setCod_distr_uvd(acod_distr_uvd.getValue());
			current0.setCod_distr_prim(acod_distr_prim.getValue());
			current0.setCod_str_birth(acod_str_birth.getValue());
			current0.setCod_obl_prim(acod_obl_prim.getValue());
			current0.setCod_place_birth_prim(acod_place_birth_prim.getValue());
			current0.setCod_sitizent(acod_sitizent.getValue());
			current0.setCod_str_sitizent(acod_str_sitizent.getValue());
			current0.setCod_str_live(acod_str_live.getValue());
			current0.setCod_obl_live(acod_obl_live.getValue());
			current0.setCod_obl_live_prim(acod_obl_live_prim.getValue());
			current0.setCod_distr_live(acod_distr_live.getValue());
			current0.setCod_distr_live_prim(acod_distr_live_prim.getValue());
			current0.setCode_naci_prim(acode_naci_prim.getValue());
			current0.setCheck_kfs(acheck_kfs.getValue());
			current0.setIs_boss(ais_boss.getValue());
			current0.setBank_spec(abank_spec.getValue());
			current0.setNotice(anotice.getValue());
			current0.setPrich_id(aprich_id.getValue());
			current0.setName_pr(aname_pr.getValue());
			current0.setSrok_date(asrok_date.getValue());
			current0.setHome_addressfact_region_id(ahome_addressfact_region_id.getValue());
			current0.setHome_addressfact_distr(ahome_addressfact_distr.getValue());
			current0.setHome_address_region_id(ahome_address_region_id.getValue());
			current0.setHome_address_distr(ahome_address_distr.getValue());
			current0.setPassport_type_code(apassport_type_code.getValue());
			current0.setRegplace_code(aregplace_code.getValue());
			current0.setPass_date_end(apass_date_end.getValue());
			current0.setGosubmit_code(agosubmit_code.getValue());
			current0.setLive_place(alive_place.getValue());
			current0.setBirthplace_point(abirthplace_point.getValue());
			current0.setMaiden_family(amaiden_family.getValue());
			current0.setDepartment_code_old(adepartment_code_old.getValue());
			current0.setPost_code_old(apost_code_old.getValue());
			current0.setSpecial_code_old(aspecial_code_old.getValue());
			current0.setSpec_ikki(aspec_ikki.getValue());
			current0.setDepartment_code_new(adepartment_code_new.getValue());
			current0.setPost_code_new(apost_code_new.getValue());
			current0.setSpecial_code_new(aspecial_code_new.getValue());
			current0.setReg_date_end(areg_date_end.getValue());
			current0.setTrud_sogl(atrud_sogl.getValue());
			current0.setApplication_date(aapplication_date.getValue());
			current0.setReply_date(areply_date.getValue());
			current0.setIs_party(ais_party.getValue());
			current0.setIs_academic(ais_academic.getValue());
			current0.setIs_degree(ais_degree.getValue());
			current0.setIs_award(ais_award.getValue());
			current0.setIs_scientific(ais_scientific.getValue());
			current0.setIs_voyage(ais_voyage.getValue());
			current0.setIs_convictions(ais_convictions.getValue());
			current0.setIs_language(ais_language.getValue());
			current0.setIs_rise(ais_rise.getValue());
			current0.setIs_election(ais_election.getValue());
			current0.setIs_premium(ais_premium.getValue());
			current0.setIs_army(ais_army.getValue());
			current0.setUser_name(auser_name.getValue());
			current0.setMaidenstag(amaidenstag.getValue());
			current0.setZp_rate_code(azp_rate_code.getValue());
			current0.setFamily_lat(afamily_lat.getValue());
			current0.setFirst_name_lat(afirst_name_lat.getValue());
			current0.setPatronymic_lat(apatronymic_lat.getValue());
			current0.setComments(acomments.getValue());
			current0.setEmp_code_name(aemp_code_name.getValue());
			current0.setTabel_line(atabel_line.getValue());
			current0.setId_client(aid_client.getValue());
			current0.setUser_id(auser_id.getValue());
			current0.setGph_date_begin(agph_date_begin.getValue());
			current0.setGph_date_end(agph_date_end.getValue());
			current0.setGph_summa(agph_summa.getValue());
			current0.setGph_post_code(agph_post_code.getValue());
			current0.setGph_department_code(agph_department_code.getValue());
			current0.setGph_order_date(agph_order_date.getValue());
			current0.setGph_order_num(agph_order_num.getValue());
			current0.setGph_rezident(agph_rezident.getValue());
			current0.setEmail(aemail.getValue());
			current0.setZp_rate_code_old(azp_rate_code_old.getValue());
			current0.setZp_rate_code_new(azp_rate_code_new.getValue());
			current0.setProfession_personal(aprofession_personal.getValue());
			current0.setBxm(abxm.getValue());
			current0.setInstitution_code(ainstitution_code.getValue());
			current0.setInstitution_end_year(ainstitution_end_year.getValue());
			ok_personalService.create(current0);

		} catch (Exception e) {
			e.printStackTrace();
		}

		refreshModel(_startPageNumber);
	}

	public void onClick$btn_search() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		frmgrd2.setVisible(false);
		frmgrd3.setVisible(false);
		frmgrd4.setVisible(false);
		frmgrd5.setVisible(false);
		frmgrd6.setVisible(false);
		frmgrd7.setVisible(false);

		addgrd.setVisible(false);
		addgrd2.setVisible(false);
		addgrd3.setVisible(false);
		addgrd4.setVisible(false);
		addgrd5.setVisible(false);
		addgrd6.setVisible(false);
		addgrd7.setVisible(false);

		fgrd.setVisible(true);
		fgrd2.setVisible(true);
		fgrd3.setVisible(true);
		fgrd4.setVisible(true);
		fgrd5.setVisible(true);
		fgrd6.setVisible(true);
		fgrd7.setVisible(true);

		grd.setVisible(false);
		frm.setVisible(true);
		btn_fsearch.setVisible(true);
		btn_cancel.setVisible(true);
		btn_save.setVisible(true);
		
	}

	public void onClick$btn_delete() {
		current0.setId(id.getValue());
		ok_personalService.delete(current0);
	}

	public void onClick$btn_fsearch() {
		
		
		try {
			System.out.println("ishlayapti: ok_personal btn_fsearch");
			filter = new ok_personalFilter();
			filter.setId(fid.getValue());
			filter.setBranch(fbranch.getValue());
			filter.setPersonal_code(fpersonal_code.getValue());
			filter.setColleague_code(fcolleague_code.getValue());
			filter.setStatus_code(fstatus_code.getValue());
			filter.setSalary_code(fsalary_code.getValue());
			filter.setFamily(ffamily.getValue());
			filter.setFirst_name(ffirst_name.getValue());
			filter.setPatronymic(fpatronymic.getValue());
			filter.setGender_code(fgender_code.getValue());
			filter.setBirthday(fbirthday.getValue());
			filter.setRegion_id(fregion_id.getValue());
			filter.setDistr(fdistr.getValue());
			filter.setNationality_code(fnationality_code.getValue());
			filter.setFamily_status_code(ffamily_status_code.getValue());
			filter.setReg_type_code(freg_type_code.getValue());
			filter.setHome_address(fhome_address.getValue());
			filter.setHome_addressfact(fhome_addressfact.getValue());
			filter.setPass_seriya(fpass_seriya.getValue());
			filter.setPass_num(fpass_num.getValue());
			filter.setPass_date(fpass_date.getValue());
			filter.setPass_reg(fpass_reg.getValue());
			filter.setRecord_book_number(frecord_book_number.getValue());
			filter.setRecord_book_series(frecord_book_series.getValue());
			filter.setTelefon(ftelefon.getValue());
			filter.setEmp_code(femp_code.getValue());
			filter.setIns_date(fins_date.getValue());
			filter.setProfmember(fprofmember.getValue());
			filter.setTabno(ftabno.getValue());
			filter.setEducation_title_code(feducation_title_code.getValue());
			filter.setBirthplace(fbirthplace.getValue());
			filter.setMotive_out(fmotive_out.getValue());
			filter.setBasis_num(fbasis_num.getValue());
			filter.setBasis_date(fbasis_date.getValue());
			filter.setLeave_code(fleave_code.getValue());
			filter.setDepartment_code(fdepartment_code.getValue());
			filter.setPost_code(fpost_code.getValue());
			filter.setNn(fnn.getValue());
			filter.setMotive_dismissial_code(fmotive_dismissial_code.getValue());
			filter.setNps_id(fnps_id.getValue());
			filter.setSpecial_code(fspecial_code.getValue());
			filter.setInn(finn.getValue());
			filter.setCod_distr_uvd(fcod_distr_uvd.getValue());
			filter.setCod_distr_prim(fcod_distr_prim.getValue());
			filter.setCod_str_birth(fcod_str_birth.getValue());
			filter.setCod_obl_prim(fcod_obl_prim.getValue());
			filter.setCod_place_birth_prim(fcod_place_birth_prim.getValue());
			filter.setCod_sitizent(fcod_sitizent.getValue());
			filter.setCod_str_sitizent(fcod_str_sitizent.getValue());
			filter.setCod_str_live(fcod_str_live.getValue());
			filter.setCod_obl_live(fcod_obl_live.getValue());
			filter.setCod_obl_live_prim(fcod_obl_live_prim.getValue());
			filter.setCod_distr_live(fcod_distr_live.getValue());
			filter.setCod_distr_live_prim(fcod_distr_live_prim.getValue());
			filter.setCode_naci_prim(fcode_naci_prim.getValue());
			filter.setCheck_kfs(fcheck_kfs.getValue());
			filter.setIs_boss(fis_boss.getValue());
			filter.setBank_spec(fbank_spec.getValue());
			filter.setNotice(fnotice.getValue());
			filter.setPrich_id(fprich_id.getValue());
			filter.setName_pr(fname_pr.getValue());
			filter.setSrok_date(fsrok_date.getValue());
			filter.setHome_addressfact_region_id(fhome_addressfact_region_id.getValue());
			filter.setHome_addressfact_distr(fhome_addressfact_distr.getValue());
			filter.setHome_address_region_id(fhome_address_region_id.getValue());
			filter.setHome_address_distr(fhome_address_distr.getValue());
			filter.setPassport_type_code(fpassport_type_code.getValue());
			filter.setRegplace_code(fregplace_code.getValue());
			filter.setPass_date_end(fpass_date_end.getValue());
			filter.setGosubmit_code(fgosubmit_code.getValue());
			filter.setLive_place(flive_place.getValue());
			filter.setBirthplace_point(fbirthplace_point.getValue());
			filter.setMaiden_family(fmaiden_family.getValue());
			filter.setDepartment_code_old(fdepartment_code_old.getValue());
			filter.setPost_code_old(fpost_code_old.getValue());
			filter.setSpecial_code_old(fspecial_code_old.getValue());
			filter.setSpec_ikki(fspec_ikki.getValue());
			filter.setDepartment_code_new(fdepartment_code_new.getValue());
			filter.setPost_code_new(fpost_code_new.getValue());
			filter.setSpecial_code_new(fspecial_code_new.getValue());
			filter.setReg_date_end(freg_date_end.getValue());
			filter.setTrud_sogl(ftrud_sogl.getValue());
			filter.setApplication_date(fapplication_date.getValue());
			filter.setReply_date(freply_date.getValue());
			filter.setIs_party(fis_party.getValue());
			filter.setIs_academic(fis_academic.getValue());
			filter.setIs_degree(fis_degree.getValue());
			filter.setIs_award(fis_award.getValue());
			filter.setIs_scientific(fis_scientific.getValue());
			filter.setIs_voyage(fis_voyage.getValue());
			filter.setIs_convictions(fis_convictions.getValue());
			filter.setIs_language(fis_language.getValue());
			filter.setIs_rise(fis_rise.getValue());
			filter.setIs_election(fis_election.getValue());
			filter.setIs_premium(fis_premium.getValue());
			filter.setIs_army(fis_army.getValue());
			filter.setUser_name(fuser_name.getValue());
			filter.setMaidenstag(fmaidenstag.getValue());
			filter.setZp_rate_code(fzp_rate_code.getValue());
			filter.setFamily_lat(ffamily_lat.getValue());
			filter.setFirst_name_lat(ffirst_name_lat.getValue());
			filter.setPatronymic_lat(fpatronymic_lat.getValue());
			filter.setComments(fcomments.getValue());
			filter.setEmp_code_name(femp_code_name.getValue());
			filter.setTabel_line(ftabel_line.getValue());
			filter.setId_client(fid_client.getValue());
			filter.setUser_id(fuser_id.getValue());
			filter.setGph_date_begin(fgph_date_begin.getValue());
			filter.setGph_date_end(fgph_date_end.getValue());
			filter.setGph_summa(fgph_summa.getValue());
			filter.setGph_post_code(fgph_post_code.getValue());
			filter.setGph_department_code(fgph_department_code.getValue());
			filter.setGph_order_date(fgph_order_date.getValue());
			filter.setGph_order_num(fgph_order_num.getValue());
			filter.setGph_rezident(fgph_rezident.getValue());
			filter.setEmail(femail.getValue());
			filter.setZp_rate_code_old(fzp_rate_code_old.getValue());
			filter.setZp_rate_code_new(fzp_rate_code_new.getValue());
			filter.setProfession_personal(fprofession_personal.getValue());
			filter.setBxm(fbxm.getValue());
			filter.setInstitution_code(finstitution_code.getValue());
			filter.setInstitution_end_year(finstitution_end_year.getValue());
			ok_personalService.find(filter);
			System.out.println("<---- find dan keyingi yozildi ---->");
		} catch (Exception e) {
			e.printStackTrace();
		}
//		refreshModel(_startPageNumber);
		frm.setVisible(false);
		fgrd.setVisible(false);
		fgrd2.setVisible(false);
		fgrd3.setVisible(false);
		fgrd4.setVisible(false);
		fgrd5.setVisible(false);
		fgrd6.setVisible(false);
		fgrd7.setVisible(false);
		grd.setVisible(true);
		
	}

	public void onClick$btn_save() throws Exception {
		try {
			System.out.println("ishlayapti: ok_personal");
			current0 = new Ok_personal();
			current0.setId(id.getValue());
			current0.setBranch(branch.getValue());
			current0.setPersonal_code(personal_code.getValue());
			current0.setColleague_code(colleague_code.getValue());
			current0.setStatus_code(status_code.getValue());
			current0.setSalary_code(salary_code.getValue());
			current0.setFamily(family.getValue());
			current0.setFirst_name(first_name.getValue());
			current0.setPatronymic(patronymic.getValue());
			current0.setGender_code(gender_code.getValue());
			current0.setBirthday(birthday.getValue());
			current0.setRegion_id(region_id.getValue());
			current0.setDistr(distr.getValue());
			current0.setNationality_code(nationality_code.getValue());
			current0.setFamily_status_code(family_status_code.getValue());
			current0.setReg_type_code(reg_type_code.getValue());
			current0.setHome_address(home_address.getValue());
			current0.setHome_addressfact(home_addressfact.getValue());
			current0.setPass_seriya(pass_seriya.getValue());
			current0.setPass_num(pass_num.getValue());
			current0.setPass_date(pass_date.getValue());
			current0.setPass_reg(pass_reg.getValue());
			current0.setRecord_book_number(record_book_number.getValue());
			current0.setRecord_book_series(record_book_series.getValue());
			current0.setTelefon(telefon.getValue());
			current0.setEmp_code(emp_code.getValue());
			current0.setIns_date(ins_date.getValue());
			current0.setProfmember(profmember.getValue());
			current0.setTabno(tabno.getValue());
			current0.setEducation_title_code(education_title_code.getValue());
			current0.setBirthplace(birthplace.getValue());
			current0.setMotive_out(motive_out.getValue());
			current0.setBasis_num(basis_num.getValue());
			current0.setBasis_date(basis_date.getValue());
			current0.setLeave_code(leave_code.getValue());
			current0.setDepartment_code(department_code.getValue());
			current0.setPost_code(post_code.getValue());
			current0.setNn(nn.getValue());
			current0.setMotive_dismissial_code(motive_dismissial_code.getValue());
			current0.setNps_id(nps_id.getValue());
			current0.setSpecial_code(special_code.getValue());
			current0.setInn(inn.getValue());
			current0.setCod_distr_uvd(cod_distr_uvd.getValue());
			current0.setCod_distr_prim(cod_distr_prim.getValue());
			current0.setCod_str_birth(cod_str_birth.getValue());
			current0.setCod_obl_prim(cod_obl_prim.getValue());
			current0.setCod_place_birth_prim(cod_place_birth_prim.getValue());
			current0.setCod_sitizent(cod_sitizent.getValue());
			current0.setCod_str_sitizent(cod_str_sitizent.getValue());
			current0.setCod_str_live(cod_str_live.getValue());
			current0.setCod_obl_live(cod_obl_live.getValue());
			current0.setCod_obl_live_prim(cod_obl_live_prim.getValue());
			current0.setCod_distr_live(cod_distr_live.getValue());
			current0.setCod_distr_live_prim(cod_distr_live_prim.getValue());
			current0.setCode_naci_prim(code_naci_prim.getValue());
			current0.setCheck_kfs(check_kfs.getValue());
			current0.setIs_boss(is_boss.getValue());
			current0.setBank_spec(bank_spec.getValue());
			current0.setNotice(notice.getValue());
			current0.setPrich_id(prich_id.getValue());
			current0.setName_pr(name_pr.getValue());
			current0.setSrok_date(srok_date.getValue());
			current0.setHome_addressfact_region_id(home_addressfact_region_id.getValue());
			current0.setHome_addressfact_distr(home_addressfact_distr.getValue());
			current0.setHome_address_region_id(home_address_region_id.getValue());
			current0.setHome_address_distr(home_address_distr.getValue());
			current0.setPassport_type_code(passport_type_code.getValue());
			current0.setRegplace_code(regplace_code.getValue());
			current0.setPass_date_end(pass_date_end.getValue());
			current0.setGosubmit_code(gosubmit_code.getValue());
			current0.setLive_place(live_place.getValue());
			current0.setBirthplace_point(birthplace_point.getValue());
			current0.setMaiden_family(maiden_family.getValue());
			current0.setDepartment_code_old(department_code_old.getValue());
			current0.setPost_code_old(post_code_old.getValue());
			current0.setSpecial_code_old(special_code_old.getValue());
			current0.setSpec_ikki(spec_ikki.getValue());
			current0.setDepartment_code_new(department_code_new.getValue());
			current0.setPost_code_new(post_code_new.getValue());
			current0.setSpecial_code_new(special_code_new.getValue());
			current0.setReg_date_end(reg_date_end.getValue());
			current0.setTrud_sogl(trud_sogl.getValue());
			current0.setApplication_date(application_date.getValue());
			current0.setReply_date(reply_date.getValue());
			current0.setIs_party(is_party.getValue());
			current0.setIs_academic(is_academic.getValue());
			current0.setIs_degree(is_degree.getValue());
			current0.setIs_award(is_award.getValue());
			current0.setIs_scientific(is_scientific.getValue());
			current0.setIs_voyage(is_voyage.getValue());
			current0.setIs_convictions(is_convictions.getValue());
			current0.setIs_language(is_language.getValue());
			current0.setIs_rise(is_rise.getValue());
			current0.setIs_election(is_election.getValue());
			current0.setIs_premium(is_premium.getValue());
			current0.setIs_army(is_army.getValue());
			current0.setUser_name(user_name.getValue());
			current0.setMaidenstag(maidenstag.getValue());
			current0.setZp_rate_code(zp_rate_code.getValue());
			current0.setFamily_lat(family_lat.getValue());
			current0.setFirst_name_lat(first_name_lat.getValue());
			current0.setPatronymic_lat(patronymic_lat.getValue());
			current0.setComments(comments.getValue());
			current0.setEmp_code_name(emp_code_name.getValue());
			current0.setTabel_line(tabel_line.getValue());
			current0.setId_client(id_client.getValue());
			current0.setUser_id(user_id.getValue());
			current0.setGph_date_begin(gph_date_begin.getValue());
			current0.setGph_date_end(gph_date_end.getValue());
			current0.setGph_summa(gph_summa.getValue());
			current0.setGph_post_code(gph_post_code.getValue());
			current0.setGph_department_code(gph_department_code.getValue());
			current0.setGph_order_date(gph_order_date.getValue());
			current0.setGph_order_num(gph_order_num.getValue());
			current0.setGph_rezident(gph_rezident.getValue());
			current0.setEmail(email.getValue());
			current0.setZp_rate_code_old(zp_rate_code_old.getValue());
			current0.setZp_rate_code_new(zp_rate_code_new.getValue());
			current0.setProfession_personal(profession_personal.getValue());
			current0.setBxm(bxm.getValue());
			current0.setInstitution_code(institution_code.getValue());
			current0.setInstitution_end_year(institution_end_year.getValue());
			ok_personalService.update(current0);
			System.out.println("<---- update dan keyingi yozildi ---->");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_cancel() {
		frmgrd.setVisible(false);
		frmgrd2.setVisible(false);
		frmgrd3.setVisible(false);
		frmgrd4.setVisible(false);
		frmgrd5.setVisible(false);
		frmgrd6.setVisible(false);
		frmgrd7.setVisible(false);

		addgrd.setVisible(false);
		addgrd2.setVisible(false);
		addgrd3.setVisible(false);
		addgrd4.setVisible(false);
		addgrd5.setVisible(false);
		addgrd6.setVisible(false);
		addgrd7.setVisible(false);

		fgrd.setVisible(false);
		fgrd2.setVisible(false);
		fgrd3.setVisible(false);
		fgrd4.setVisible(false);
		fgrd5.setVisible(false);
		fgrd6.setVisible(false);
		fgrd7.setVisible(false);

		frm.setVisible(false);
		grd.setVisible(true);
	}

}