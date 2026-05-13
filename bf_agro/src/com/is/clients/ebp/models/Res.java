package com.is.clients.ebp.models;

/**
 * Created by DEN on 27.03.2017.
 */
public class Res {
    private int code;
    private String desc;
    private long id;

    public Res() {
    }

    public Res(int code, String desc, long id) {
        this.code = code;
        this.desc = desc;
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
