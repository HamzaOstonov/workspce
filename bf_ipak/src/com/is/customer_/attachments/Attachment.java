package com.is.customer_.attachments;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.google.common.collect.ComparisonChain;

import lombok.Data;

/**
 * Created by root on 11.05.2017.
 * 15:11
 */
@Data
public class Attachment implements Comparable<Attachment>{
    private byte[] data;
    private String description;
    private String fileName;
    private String doc_type;
    private String url;
    private Date doc_date;
    private String doc_number;
    private Date createdAt;
    private String id_client;
    private String branch;

    public Attachment(byte[] data, String description, String fileName, String doc_type, String url, Date doc_date, String doc_number) {
        this.data = data;
        this.description = description;
        this.fileName = fileName;
        this.doc_type = doc_type;
        this.url = url;
        this.doc_date = doc_date;
        this.doc_number = doc_number;
    }

    public Attachment(byte[] data, String description, String fileName, String doc_type, String url, Date doc_date, String doc_number, Date created_at, String id_client, String branch) {
        this.data = data;
        this.description = description;
        this.fileName = fileName;
        this.doc_type = doc_type;
        this.url = url;
        this.doc_date = doc_date;
        this.doc_number = doc_number;
        this.createdAt=created_at;
        this.id_client=id_client;
        this.branch=branch;
    }

    public Attachment(){}

    public void isURLValid(){
        if (this.url == null || this.url.trim().isEmpty()) {
            throw new InvalidAttachmentException("Заполните URL");
        }
        if (this.data == null) {
            throw new InvalidAttachmentException("Заполните URL");
        }
        if (this.description == null)
            throw new InvalidAttachmentException("Заполните Номер Документа / Описание");
    }

    /**
     * throws @NullPointerException
     */
    public void convertURLToBytes() throws UnsupportedEncodingException {
        if (this.url != null && !this.url.trim().isEmpty())
            this.data = this.url.getBytes("UTF-8");
    }

    @Override
    public int compareTo(Attachment that) {
        return ComparisonChain.start().compare(this.createdAt,that.createdAt).result();
    }

    private static class InvalidAttachmentException extends RuntimeException{
        public InvalidAttachmentException(String message){
            super(message);
        }

        @Override
        public  String toString(){
            return super.getMessage();
        }
    }
}
