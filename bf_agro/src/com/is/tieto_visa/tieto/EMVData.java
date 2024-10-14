package com.is.tieto_visa.tieto;

import java.math.BigDecimal;


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

public class EMVData {

	
	
	private String SEQUENCE_NR;
	
	
	private String LAST_SEQ_NR;
	
	
	private BigDecimal DESIGN_ID;
	
	
	private BigDecimal CHIP_APP_ID;
	
	
	private String INDIVIDUALIZED_PREV;
	
	
	private String DKI_0;
	
	
	private String DKI_1;
	
	
	private String DKI_2;
	
	
	private String TRACK2_EQUIV_DATA;
	
	
	private String INDIVIDUALIZED;
	
}
