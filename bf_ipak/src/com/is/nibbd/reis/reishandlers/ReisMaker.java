package com.is.nibbd.reis.reishandlers;

import com.is.nibbd.models.Nibbd;
import com.is.nibbd.reis.reisServices.MakerService;

public class ReisMaker implements ReisHandler {
    private MakerService service;

    private ReisMaker(MakerService service) {
        this.service = service;
    }

    public static ReisMaker getInstance(MakerService service) {
        return new ReisMaker(service);
    }

    @Override
    public void handle() {
        String reis = service.getReis_(true);
        Nibbd nibbd = new Nibbd();
        nibbd.setReis_num(reis);
        //nibbd.setReis_num(null);
        service.updateToState2(nibbd);
//		}
    }
}
