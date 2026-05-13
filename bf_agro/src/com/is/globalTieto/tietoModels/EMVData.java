package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author D
 * SEQUENCE_NR String(3) Реальный номер последовательности карты (по умолчанию 1).
 * LAST_SEQ_NR String(3) Предыдущий номер последовательности карты
 * DESIGN_ID Decimal(10,0) Временно не используется
 * CHIP_APP_ID Decimal(10,0) Идентификатор аппликации для карты
 * INDIVIDUALIZED_PREV String(1) Индивидуализировано или нет. (по умолчанию N)
 * DKI_0 String(3) DKI, когда карта находится в списке эмбоссирования 
 * DKI_1 String(3) Индекс комплекта криптографических ключей
 * DKI_2 String(3) Индекс комплекта криптографических ключей
 * TRACK2_EQUIV_DATA String(40) Track 2 эквивалентные данные для чип-данных 
 * INDIVIDUALIZED String(1) Данные карт индивидуализированы ил нет (Y/N). (по умолчанию N)
 */
@NoArgsConstructor
@AllArgsConstructor
public class EMVData {

	@Getter
	@Setter
	private String SEQUENCE_NR;
	@Getter
	@Setter
	private String LAST_SEQ_NR;
	@Getter
	@Setter
	private BigDecimal DESIGN_ID;
	@Getter
	@Setter
	private BigDecimal CHIP_APP_ID;
	@Getter
	@Setter
	private String INDIVIDUALIZED_PREV;
	@Getter
	@Setter
	private String DKI_0;
	@Getter
	@Setter
	private String DKI_1;
	@Getter
	@Setter
	private String DKI_2;
	@Getter
	@Setter
	private String TRACK2_EQUIV_DATA;
	@Getter
	@Setter
	private String INDIVIDUALIZED;
	
}
