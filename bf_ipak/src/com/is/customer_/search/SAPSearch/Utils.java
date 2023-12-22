package com.is.customer_.search.SAPSearch;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;

import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class Utils {
	private static List<RefData> documents;
	@SuppressWarnings("unchecked")
	public static void clear(Grid grid) {
		Rows rows = grid.getRows();
		List<Row> list = rows.getChildren();
		for (Row row : list) {
			List<Component> compList = row.getChildren();
			for (Component comp : compList) {
				if (comp instanceof Textbox)
					((Textbox) comp).setValue(null);
				else if (comp instanceof Datebox)
					((Datebox) comp).setValue(null);
			}
		}
	}

	public static List<RefData> getDocuments_(String schema){
		//List<RefData> list = ReferenceDictionary.getDocumentTypes(schema);
		//list.add(new RefData("51", "»ÕÕ"));
		//list.add(new RefData("23", "»Õœ—"));
		//return list;
		
		/*if (documents==null)
		{
			documents = ReferenceDictionary.getDocumentTypes(schema);
			documents.add(new RefData("51", "»ÕÕ"));
			documents.add(new RefData("23", "»Õœ—"));
		}
		return documents;*/
		
		if (documents==null)
		{
			documents = RefDataService.getRefData(
					"select code_cert data, trim(' ' from name_cert) label from s_certificate order by code_cert",
					schema);
			documents.add(new RefData("51", "»ÕÕ"));
			documents.add(new RefData("23", "»Õœ—"));
		}
		return documents;
	}
}
