package com.is.clienta;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.zul.Rows;


public class ClientUtil {
	
	public static final String ACCOUNT_SRC = "account.zul";
	public static final String NIBBD_SRC = "nibbd.zul";
	public static final String CLIENT_P_SRC = "/customer/contactperson.zul";
	public static final String CLIENT_IP_SRC = "/customer/ip.zul";
    public static final String SPECCLT_SRC = "client_spec.zul";
    public static final String PERSON_MAP_SRC = "client_j_person_map.zul";
    public static final String PERSON_SRC = "client_j_person.zul";
    public static final String LEGAL_SRC = "client_j_founder_legal.zul";
    public static final String SUCCESS = "Операция прошда успешно";
    public static final int ACTION_OPEN = 1;
    public static final int ACTION_CONFIRM = 2;
    public static final int ACTION_CHANGE_OBJ = 4;
    public static final int ACTION_CHANGE = 19;
    public static final int ACTION_CONFIRM_CLOSED = 20;
    public static final int ACTION_CLOSE = 3;

    public static final String STATE_CONFIRMED = "2";

    public static final int DELTA_ACTION_OPEN = 1;
    public static final int DELTA_ACTION_CHANGE = 19;
    public static final int DELTA_ACTION_DELETE = 2;
    public static final int SIGN_REGISTR_PRIMARY = 1;
    public static final int SIGN_REGISTR_PRIMARY_NOT = 3;
    public static final int SIGN_REGISTR_FOREIGN_BANK = 4;
    public static final String COUNTRY_UZ = "860";
    public static final String CODE_RESIDENT = "1";
    public static final String NOT_RESIDENT = "2";
    public static final String CHECKBOX_Y = "Y";
    public static final String CHECKBOX_N = "N";
    public static final String CODE_TYPE_IP = "11";
    public static final String CODE_TYPE_BANK = "07";
    public static final String CODE_SUBJECT_J = "J";
    public static final String CODE_SUBJECT_I = "I";
    public static final String CODE_TYPE_NON_FINANCIAL = "04";
    public static final String CODE_TYPE_OTHER = "05";
    public static final String MODE_NIBBD = "nibbd";
    public static final String MODE_DELTA = "delta";


    public static final String SAP_CLIENT_ATTR = "sapClient";
    public static final String EBP_CLIENT_ATTR = "ebpClient";
    //	public static final String SAP_CHECKED = "sapChecked";
    //	public static final String EBP_CHECKED = "ebpChecked";
	public static final String COLUMN_ATTR = "columnAttr";
    public static final String SAP_COLUMN = "sapColumn";
    public static final String EBP_COLUMN = "ebpColumn";


    //ss_person_kinds
    public static final int KIND_DIR = 1;
    public static final int KIND_ACC = 2;
    public static final int KIND_FOUNDER = 3;

    public static final String TYPE_INDIVIDUAL = "P";
    public static final String TYPE_LEGAL_ENTITY = "J";

    public static final String FIELD_ATTR = "fieldName";


    private static SimpleDateFormat dfForLog = new SimpleDateFormat("(dd-MM-yyyy@HH-mm-ss)");
	private static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private static final String[] UNCHANGEABLE_FIELDS = {ClientFields.J_ACCOUNT.toString().toLowerCase()
														, ClientFields.ID.toString().toLowerCase()
														, ClientFields.BRANCH.toString().toLowerCase()
														, ClientFields.J_327.toString().toLowerCase()
														, ClientFields.J_PATENT_EXPIRATION.toString().toLowerCase()
														, ClientFields.ID_CLIENT.toString().toLowerCase()
														, ClientFields.CODE_SUBJECT.toString().toLowerCase().toLowerCase()
														, ClientFields.DATE_OPEN.toString().toLowerCase()
														, ClientFields.DATE_CLOSE.toString().toLowerCase()
														, ClientFields.STATE.toString().toLowerCase()
														, ClientFields.SIGN_REGISTR.toString().toLowerCase()
														, ClientFields.KOD_ERR.toString().toLowerCase()
														, ClientFields.FILE_NAME.toString().toLowerCase()
														, ClientFields.ID_CLIENT_IN_SAP.toString().toLowerCase()
														, ClientFields.ID_SAP.toString().toLowerCase()
														, ClientFields.J_CODE_BANK.toString().toLowerCase()};
	
	public static Map<String, String> clientFields = new HashMap<String, String>();
	
	private static final Map<Character, String> charMap = new HashMap<Character, String>();

    static {
        charMap.put('А', "A");
        charMap.put('Б', "B");
        charMap.put('В', "V");
        charMap.put('Г', "G");
        charMap.put('Д', "D");
        charMap.put('Е', "E");
        charMap.put('Ё', "E");
        charMap.put('Ж', "Zh");
        charMap.put('З', "Z");
        charMap.put('И', "I");
        charMap.put('Й', "I");
        charMap.put('К', "K");
        charMap.put('Л', "L");
        charMap.put('М', "M");
        charMap.put('Н', "N");
        charMap.put('О', "O");
        charMap.put('П', "P");
        charMap.put('Р', "R");
        charMap.put('С', "S");
        charMap.put('Т', "T");
        charMap.put('У', "U");
        charMap.put('Ф', "F");
        charMap.put('Х', "H");
        charMap.put('Ц', "C");
        charMap.put('Ч', "Ch");
        charMap.put('Ш', "Sh");
        charMap.put('Щ', "Sh");
        charMap.put('Ъ', "'");
        charMap.put('Ы', "Y");
        charMap.put('Ь', "'");
        charMap.put('Э', "E");
        charMap.put('Ю', "U");
        charMap.put('Я', "Ya");
        charMap.put('а', "a");
        charMap.put('б', "b");
        charMap.put('в', "v");
        charMap.put('г', "g");
        charMap.put('д', "d");
        charMap.put('е', "e");
        charMap.put('ё', "e");
        charMap.put('ж', "zh");
        charMap.put('з', "z");
        charMap.put('и', "i");
        charMap.put('й', "i");
        charMap.put('к', "k");
        charMap.put('л', "l");
        charMap.put('м', "m");
        charMap.put('н', "n");
        charMap.put('о', "o");
        charMap.put('п', "p");
        charMap.put('р', "r");
        charMap.put('с', "s");
        charMap.put('т', "t");
        charMap.put('у', "u");
        charMap.put('ф', "f");
        charMap.put('х', "h");
        charMap.put('ц', "c");
        charMap.put('ч', "ch");
        charMap.put('ш', "sh");
        charMap.put('щ', "sh");
        charMap.put('ъ', "'");
        charMap.put('ы', "y");
        charMap.put('ь', "'");
        charMap.put('э', "e");
        charMap.put('ю', "u");
        charMap.put('я', "ya");

    }
    
    public static String transliterate(String string) {
    	if(string == null) {
    		return "";
    	}
        StringBuilder transliteratedString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            Character ch = string.charAt(i);
            String charFromMap = charMap.get(ch);
            if (charFromMap == null) {
                transliteratedString.append(ch);
            } else {
                transliteratedString.append(charFromMap);
            }
        }
        return transliteratedString.toString();
    }
	
	static {
		Properties props = new Properties();
		InputStream io = null;
		try {//C:\\apache-tomcat-6.0.26\\webapps\\bf\\WEB-INF
			io = new FileInputStream(StringUtils.removeEnd(System.getProperty("user.dir"), "bin")+"webapps\\bf\\WEB-INF\\client-label.properties");
			props.load(new InputStreamReader(io, Charset.forName("utf-8")));
			for(Entry<Object, Object> entry: props.entrySet()) {
				clientFields.put((String)entry.getKey(), ((String)entry.getValue()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Map<String, String> getClientLabels() {
		return clientFields;
	}

	public static boolean isValidClientId(String clientId) {
		return clientId != null && clientId.length() == 8 /*&& Util.isDigit(clientId)*/;
	}
	
	
	private static boolean isChangeableField(String fieldName) {
		for(String str: UNCHANGEABLE_FIELDS) {
			if(fieldName.equals(str)) {
				return false;
			}
		}
		if( fieldName.indexOf("director") > 0
				|| fieldName.indexOf("accountant") > 0
				|| fieldName.indexOf("accounter") > 0 
				|| fieldName.startsWith("i_")) {
			return false;
		}
			
		return true;
	}

	public static boolean isClientConfirmed(Connection c, String idClient) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ps = c.prepareStatement("select state from client where id_client=?");
		ps.setString(1, idClient);
		rs = ps.executeQuery();
		int state = 0;
		if(rs.next()) {
			state = rs.getInt(1);
		}
		return state == 2;
	}
	
	
	private static boolean allDiffrent(Object curObject, Object sapObject, Object ebpObject) {
		return !(curObject != null && sapObject != null && ebpObject != null && 
				curObject.equals(sapObject) && curObject.equals(ebpObject) && sapObject.equals(ebpObject)
				|| (curObject == null && sapObject == null && ebpObject == null));
	}
	
	public static void applyChanges(ClientA local, final ClientA sap, final Object comp) {
		/*Util.interateThroughFields(ClientJ.class, local, new FieldHandler() {
			
			@Override
			public void eval(Field f, Object local) throws IllegalAccessException {
				Object curObject = f.get(local);
				Object sapObject = f.get(sap);
				
				curObject = f.get(local);
				sapObject = f.get(sap);
				if(Util.hasChanges(curObject, sapObject)){
					if(sapObject != null) {
						f.set(local, sapObject);
					}
					ZkUtils.highlightField(comp, f.getName());
				}
			}
		});*/
	}
//	
//	public static void fillRowsIfThereChanges(ClientJ local, final ClientJ sap, final Rows rows) {
//		iterateThroughFields(local, new FieldHandler() {
//			
//			@Override
//			public boolean eval(Field f, Object local)
//					throws IllegalAccessException {
//				Object curObject = f.get(local);
//				Object sapObject = f.get(sap);
//				
//				if(Util.hasChanges(curObject, sapObject) && 
//						!isChangeableField(f.getName())){//accounter
//					Row row = new Row();
//					String fieldName = clientFields.get("client."+f.getName());
//					row.appendChild(new Label(fieldName != null ? fieldName : f.getName()));
//					row.appendChild(makeInputElement(curObject));
//					row.appendChild(makeInputElement(sapObject));
//					row.appendChild(new Checkbox());
//					rows.appendChild(row);
//					row.setAttribute(FIELD_ATTR, f.getName());
//				}
//				return false;
//			}
//		});
//	}
	public static void fillRowsIfThereChanges(ClientA local, final ClientA sap,final ClientA ebpClient, final Rows rows) {
		/*Util.interateThroughFields(ClientJ.class, local, new FieldHandler() {
			
			@Override
			public void eval(Field f, Object local)
					throws IllegalAccessException {
				Object curObject = f.get(local);
				Object sapObject = null;
				if (sap != null) {
					sapObject = f.get(sap);
				}
				Object ebpObject = null;
				if (ebpClient != null) {
					ebpObject = f.get(ebpClient);
				}
				
				
				if(allDiffrent(curObject, sapObject, ebpObject) &&
						isChangeableField(f.getName())){//accounter
					Row row = new Row();
					Checkbox checkbox = null;
					String fieldName = clientFields.get("client."+f.getName());
					
					row.appendChild(new Label(fieldName != null ? fieldName : f.getName()));
					row.appendChild(ZkUtils.makeInputElement(curObject));
					
					row.appendChild(ZkUtils.makeInputElement(sapObject));
					checkbox = new Checkbox();
					checkbox.setAttribute(COLUMN_ATTR, SAP_COLUMN);
					row.appendChild(checkbox);
					
					row.appendChild(ZkUtils.makeInputElement(ebpObject));
					checkbox = new Checkbox();
					checkbox.setAttribute(COLUMN_ATTR, EBP_COLUMN);
					row.appendChild(checkbox);
					
					row.setAttribute(FIELD_ATTR, f.getName());
					rows.appendChild(row);
				}
			}
		});*/
	}
	
//	private static void addOnCheckListener(Checkbox checkbox, final String typeAttr) {
//		checkbox.addEventListener(Events.ON_CHECK, new EventListener() {
//			
//			@Override
//			public void onEvent(Event arg0) throws Exception {
//				Row row = (Row)(arg0.getTarget().getParent());
//				Rows rows = (Rows)(row).getParent();
//				ArrayList<String> list = (ArrayList<String>) rows.getAttribute(typeAttr);
//				if(list == null) {
//					list = new ArrayList<String>();
//				}
//				list.add((String)row.getAttribute(FIELD_ATTR));
//				rows.setAttribute(typeAttr, list);
//			}
//		});
//	}
	
	public static void setSapData(ClientA local, final ClientA sap, final List<String> changedFields) {
		/*Util.interateThroughFields(ClientJ.class, local, new FieldHandler() {
			
			@Override
			public void eval(Field field, Object local)
					throws IllegalAccessException {
				if(changedFields.contains(field.getName())){
					Object sapObject = field.get(sap);
					if(sapObject != null) {
						field.set(local, sapObject);
					}
				}
			}
		});*/
	}
	
	public static void setSapOrEbpData(ClientA local, final ClientA sap, final ClientA ebp, 
			final List<String> sapChangedFields, final List<String> ebpChangedFields) {
		/*Util.interateThroughFields(ClientJ.class, local, new FieldHandler() {
			
			@Override
			public void eval(Field field, Object local)
					throws IllegalAccessException {
				if((sapChangedFields.contains(field.getName()) && ebpChangedFields.contains(field.getName()))
						|| sapChangedFields.contains(field.getName())){
					Object sapObject = field.get(sap);
					if(sapObject != null) {
						field.set(local, sapObject);
					}
				} else if(ebpChangedFields.contains(field.getName())){
					Object ebpObject = field.get(ebp);
					if(ebpObject != null) {
						field.set(local, ebpObject);
					}
				}
			}
		});*/
	}
	
	public static void setSapDataIfNull(ClientA local, final ClientA sap, final Object comp) {
		/*Util.interateThroughFields(ClientA.class, local, new FieldHandler() {
			
			@Override
			public void eval(Field field, Object local)
					throws IllegalAccessException {
				try {
					Object curObject = field.get(local);
					Object sapObject = field.get(sap);
					if(sapObject != null && curObject == null) {
						field.set(local, sapObject);
						ZkUtils.highlightField(comp, field.getName());
					}
					
				} catch (Exception e) {
					ISLogger.getLogger().error(CheckNull.getPstr(e));
				}
			}
		});*/
	}
	
	public static String formatDateWithTime(Date date) {
		return dfForLog.format(date);
	}
	public static String formatDate(Date date) {
		return df.format(date);
	}
	
	public static Date parseDate(String stringDate) throws ParseException {
		return df.parse(stringDate);
	}
	
	public static void main(String[] args) {
		if(allDiffrent("208", "208", "208")){//accounter
			System.out.println("Ooops!");
		}
	}

}