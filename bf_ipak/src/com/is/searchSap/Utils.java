package com.is.searchSap;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;

public class Utils {

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
}
