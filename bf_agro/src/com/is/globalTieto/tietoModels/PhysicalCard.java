package com.is.globalTieto.tietoModels;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author D
 * STATUS1 String(1) Статус карты. Тег STATUS1 не влияет на статус карты. При исполнении addInfo4Agreement.
 * STATUS2 String(1) Статус карты – возобновление
 * STOP_CAUSE String(1) Причина остановки карты
 * EXPIRY1 dateTime Дата прекращения срока действия карты
 * EXPIRITY2 dateTime Дата прекращения срока действия возобновленной карты
 * RENEW String(1) Признак обновления карты (по умолчанию 0)
 * CARD_NAME String(27) Имя держателя карты на карте
 * MC_NAME String(26) Имя держателя карты закодированное на магнитной дорожке
 * RENEWED_CARD String(19) Предыдущий номер карты
 * DESIGN_ID Decimal(10,0) Идентификатор дизайна карт
 * INSTANT String(10) Код профиля Instant продукта.
 */
@NoArgsConstructor
@AllArgsConstructor
public class PhysicalCard {
	
	@Getter
	@Setter
	private String STATUS1;
	@Getter
	@Setter
	private String STATUS2;
	@Getter
	@Setter
	private String STOP_CAUSE;
	@Getter
	@Setter
	private Date EXPIRY1;
	@Getter
	@Setter
	private Date EXPIRITY2;
	@Getter
	@Setter
	private String RENEW;
	@Getter
	@Setter
	private String CARD_NAME;
	@Getter
	@Setter
	private String MC_NAME;
	@Getter
	@Setter
	private String RENEWED_CARD;
	@Getter
	@Setter
	private BigDecimal DESIGN_ID;
	@Getter
	@Setter
	private String INSTANT;
	
}
