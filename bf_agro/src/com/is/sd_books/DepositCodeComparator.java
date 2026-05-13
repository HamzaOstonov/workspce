package com.is.sd_books;

import java.util.Comparator;

import com.is.sd_books.model.SD_Book;

public class DepositCodeComparator implements Comparator<Object> {
	private boolean asc = true;
	private int type;

	public DepositCodeComparator(boolean asc, int type) {
		this.asc = asc;
		this.type = type;
	}

	@Override
	public int compare(Object o1, Object o2) {
		SD_Book sd = (SD_Book) o1;
		SD_Book sd2 = (SD_Book) o2;

		switch (type) {
		// Compare deposit codes
		default:
			return sd.getDep().compareTo(sd2.getDep()) * (asc ? 1 : -1);
		}
	}

}
