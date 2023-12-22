package com.is.nibbd.reis.reishandlers;

import java.util.List;

import com.is.nibbd.models.Nibbd;
import com.is.nibbd.reis.reisServices.ExecutorService;

public class ReisExecutor implements ReisHandler{
	private ExecutorService service;
	private ReisExecutor(ExecutorService service){
		this.service = service;
	}
	public static ReisExecutor getInstance(ExecutorService service){
		return new ReisExecutor(service);
	}
	public ReisExecutor() {
		
	}
	@Override
	public void handle() {
		List<Nibbd> list = service.getList();
		try {
			service.initConnect();
			for(Nibbd item: list) {
				service.updateNibbdTo4(item);
			}
		} finally {
			service.closeConnect();
		}
		
 	}

}
