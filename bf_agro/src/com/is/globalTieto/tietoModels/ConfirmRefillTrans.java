package com.is.globalTieto.tietoModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ConfirmRefillTrans {
	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	private Long initial_state_id;
	@Getter
	@Setter
	private Long action_id;
	@Getter
	@Setter
	private Long final_state_id;
	@Getter
	@Setter
	private Long action_result;
}
