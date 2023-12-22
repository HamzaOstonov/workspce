package com.is.nibbd.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@AllArgsConstructor
public class ArchiveItem {
	@Getter @Setter private String archiveName;
	@Getter @Setter private String archivePath;
	@Getter @Setter private String fileName;
	@Getter @Setter private String textData;
	@Getter @Setter private Date fileDate;
	public ArchiveItem() {
	}

}
