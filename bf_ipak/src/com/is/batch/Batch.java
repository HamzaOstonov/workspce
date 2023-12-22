package com.is.batch;

import lombok.Data;

import java.util.Date;

/**
 * Created by root on 11.05.2017.
 * 18:14
 */
@Data
public class Batch {
    private Long id;
    private String branch;
    private String type_card;
    private String organizationId;
    private String organizationName;
    private String acc_bal;
    private String acc_val;
    private String acc_num;
    private String surname;
    private String first_name;
    private String second_name;
    private Date birth_date;
    private String sex;
    private String residence;
    private String p_id_series;
    private String p_id_number;
    private String p_id_authority;
    private Date p_id_issue_date;
    private String address_line1;
    private String address_line2;
    private String region;
    private String primary_phone;
    private String mobile_phone;
    private String email;
    private String embossed_ch_name;
    private String budacc;
    private String budinn;
    private String code_distr;
    private String pension_sertif_serial;
    private String code_nation;
    private String code_birth_region;
    private String code_birth_distr;
    private Date passport_date_expiration;
    private String inps;
    private String id_client;
    private String state;
    private String id_acc;
    private String fio;
    private String type_document;
    private String birth_place;
    private String str;
    private String gni;
    private String inn;
    private String statusts;
    private String tsseria;
    private String tsnumber;
    private Date tsdate;
    private String tsmestovid;
    private String message;
}
