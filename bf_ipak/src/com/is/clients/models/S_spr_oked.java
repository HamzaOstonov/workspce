package com.is.clients.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class S_spr_oked {
	@Getter @Setter private String nci_id;
	@Getter @Setter private String section_gr;
	@Getter @Setter private String section_sy;
	@Getter @Setter private String sg_name_ru;
	@Getter @Setter private String sg_name_uz;
	@Getter @Setter private String section;
	@Getter @Setter private String s_name_ru;
	@Getter @Setter private String s_name_uz;
	@Getter @Setter private String groups;
	@Getter @Setter private String gr_name_ru;
	@Getter @Setter private String gr_name_uz;
	@Getter @Setter private String classId;
	@Getter @Setter private String cl_name_ru;
	@Getter @Setter private String cl_name_uz;
	@Getter @Setter private String code;
	@Getter @Setter private String name_ru;
	@Getter @Setter private String name_uz;
	@Getter @Setter private Date date_open;
	@Getter @Setter private Date date_close;
	@Getter @Setter private String active;
    
	public S_spr_oked() {
	}
}
