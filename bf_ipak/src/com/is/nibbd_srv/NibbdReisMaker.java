package com.is.nibbd_srv;

import com.is.base.LongConnection;
import com.is.nibbd.reis.reisServices.MakerService;
import com.is.nibbd.reis.reishandlers.ReisMaker;

public class NibbdReisMaker {
	public static void make() {
		LongConnection transactionManager = LongConnection.getInstance(null);
		MakerService makerService = MakerService.getInstance();
		ReisMaker reisMaker = ReisMaker.getInstance(makerService);
		try {
			reisMaker.handle();
		} finally {
			transactionManager.closeCon();
		}
	}
}
