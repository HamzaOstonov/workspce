package com.is.globalTieto.tietoModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class WSConnectionInfo {
	
	@Getter
	@Setter
	private String host;
	@Getter
	@Setter
	private String login;
	@Getter
	@Setter
	private String password;
	@Getter
	@Setter
	private String bankC;
	@Getter
	@Setter
	private String groupC;

}
