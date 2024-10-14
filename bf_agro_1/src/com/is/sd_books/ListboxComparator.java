package com.is.sd_books;

import java.util.Comparator;

import com.is.sd_books.model.Circulate;


public class ListboxComparator implements Comparator<Object> {
	private boolean asc = true;
	private int type;

	public ListboxComparator(boolean asc, int type) {
		this.asc = asc;
		this.type = type;
	}

	@Override
	public int compare(Object o1, Object o2) {
		Circulate circulate1 = (Circulate) o1;
		Circulate circulate2 = (Circulate) o2;
		switch (type) {
		// Compare date
		default:
			return circulate1.getOper_date().compareTo(
					circulate2.getOper_date())
					* (asc ? 1 : -1);
		}
	}
}
