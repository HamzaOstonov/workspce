package com.is.bfreport;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import java.awt.Color;
import java.sql.Connection;
import java.util.List;
import java.util.Locale;

import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import net.sf.dynamicreports.report.builder.HyperLinkBuilder;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.datatype.BigDecimalType;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;

/**
 * @author Dan
 */
public class ReportTemplates {
	public static final StyleBuilder rootStyle;
	public static final StyleBuilder boldStyle;
	public static final StyleBuilder italicStyle;
	public static final StyleBuilder boldCenteredStyle;
	public static final StyleBuilder bold10CenteredStyle;
	public static final StyleBuilder bold12CenteredStyle;
	public static final StyleBuilder bold18CenteredStyle;
	public static final StyleBuilder bold18Style;
	public static final StyleBuilder bold12Style;
	public static final StyleBuilder bold22CenteredStyle;
	public static final StyleBuilder columnStyle;
	public static final StyleBuilder columnTitleStyle;
	public static final StyleBuilder groupStyle;
	public static final StyleBuilder subtotalStyle;

	public static final ReportTemplateBuilder reportTemplate;
	public static final CurrencyType currencyType;
	//public static final ComponentBuilder<?, ?> pageHeaderComponent;
	public static final ComponentBuilder<?, ?> pageFooterComponent;

	static {
		rootStyle           = stl.style().setPadding(2).setFontName("CourierNewCyr");
		boldStyle           = stl.style(rootStyle).bold();
		italicStyle         = stl.style(rootStyle).italic();
		boldCenteredStyle   = stl.style(boldStyle)
		                         .setAlignment(HorizontalAlignment.CENTER, VerticalAlignment.MIDDLE);
		bold10CenteredStyle = stl.style(boldCenteredStyle)
                				 .setFontSize(12);
		bold12CenteredStyle = stl.style(boldCenteredStyle)
		                         .setFontSize(12);
		bold18CenteredStyle = stl.style(boldCenteredStyle)
		                         .setFontSize(18);
		bold22CenteredStyle = stl.style(boldCenteredStyle)
                             	 .setFontSize(22);
		bold18Style			= stl.style(boldStyle)
								 .setFontSize(18);
		bold12Style			= stl.style(boldStyle)
				 				 .setFontSize(12);
		columnStyle         = stl.style(rootStyle).setVerticalAlignment(VerticalAlignment.MIDDLE).setFontSize(10).setBorder(stl.penThin());
		columnTitleStyle    = stl.style(columnStyle)
		                         .setBorder(stl.pen1Point())
		                         .setHorizontalAlignment(HorizontalAlignment.CENTER)
		                         .setBackgroundColor(Color.LIGHT_GRAY)
		                         .bold();
		groupStyle          = stl.style(boldStyle)
		                         .setHorizontalAlignment(HorizontalAlignment.LEFT)
		                         .setBackgroundColor(Color.LIGHT_GRAY)
		                         .setBorder(stl.pen1Point());
		subtotalStyle       = stl.style(boldStyle)
		                         .setTopBorder(stl.pen1Point());

		StyleBuilder crosstabGroupStyle      = stl.style(columnTitleStyle);
		StyleBuilder crosstabGroupTotalStyle = stl.style(columnTitleStyle)
		                                          .setBackgroundColor(new Color(170, 170, 170));
		StyleBuilder crosstabGrandTotalStyle = stl.style(columnTitleStyle)
		                                          .setBackgroundColor(new Color(140, 140, 140));
		StyleBuilder crosstabCellStyle       = stl.style(columnStyle)
		                                          .setBorder(stl.pen1Point());

		TableOfContentsCustomizerBuilder tableOfContentsCustomizer = tableOfContentsCustomizer()
			.setHeadingStyle(0, stl.style(rootStyle).bold());

		Locale.setDefault(new Locale("ru", "RU"));

		reportTemplate = template()
						   .setLocale(new Locale("ru", "RU"))
			               //.setLocale(Locale.ENGLISH)
		                   .setColumnStyle(columnStyle)
		                   .setColumnTitleStyle(columnTitleStyle)
		                   .setGroupStyle(groupStyle)
		                   .setGroupTitleStyle(groupStyle)
		                   .setSubtotalStyle(subtotalStyle)
		                   .highlightDetailEvenRows()
		                   .crosstabHighlightEvenRows()
		                   .setCrosstabGroupStyle(crosstabGroupStyle)
		                   .setCrosstabGroupTotalStyle(crosstabGroupTotalStyle)
		                   .setCrosstabGrandTotalStyle(crosstabGrandTotalStyle)
		                   .setCrosstabCellStyle(crosstabCellStyle)
		                   .setTableOfContentsCustomizer(tableOfContentsCustomizer);

		currencyType = new CurrencyType();
		
		pageFooterComponent = cmp.pageXofY()
                .setStyle(
                	stl.style(boldCenteredStyle)
                	   .setTopBorder(stl.pen1Point()));

	}

	public static ComponentBuilder<?, ?> createTitleComponent(List<HeaderFooter> list) {
		HeaderFooter hf = null;
		//VerticalListBuilder vlb = cmp.verticalList();
		HorizontalListBuilder hlb = cmp.horizontalList();
		//hlb.add(cmp.line());
		if (!list.isEmpty()){
			for (int i=0; i< list.size();i++){
				hf = list.get(i);
				if (hf.getId().indexOf("REP_HL") != -1){
					hlb.add(cmp.text(hf.getValue()).setStyle(bold12Style).setHorizontalAlignment(HorizontalAlignment.LEFT))
					.newRow();
				} else if (hf.getId().indexOf("REP_HC") != -1){
					hlb.add(cmp.text(hf.getValue()).setStyle(bold18Style).setHorizontalAlignment(HorizontalAlignment.CENTER))
					.newRow();
				} else {
					hlb.add(cmp.text(hf.getValue()).setStyle(bold12Style).setHorizontalAlignment(HorizontalAlignment.RIGHT))
					.newRow();
				}
			}
		}
		hlb
		.newRow()
		.add(cmp.line())
		.newRow();
		return hlb;
	}
	
	public static ComponentBuilder<?, ?> createFooterComponent(List<HeaderFooter> list) {
		HeaderFooter hf = null;
		//VerticalListBuilder vlb = cmp.verticalList();
		HorizontalListBuilder hlb = cmp.horizontalList();
		//hlb.add(cmp.line());
		if (!list.isEmpty()){
			for (int i=0; i< list.size();i++){
				hf = list.get(i);
				if (hf.getId().indexOf("REP_FR") != -1){
					hlb.add(cmp.text(hf.getValue()).setStyle(bold12CenteredStyle).setHorizontalAlignment(HorizontalAlignment.RIGHT))
					.newRow();
				} else if (hf.getId().indexOf("REP_FC") != -1){
					hlb.add(cmp.text(hf.getValue()).setStyle(bold12CenteredStyle).setHorizontalAlignment(HorizontalAlignment.CENTER))
					.newRow();
				} else {
					hlb.add(cmp.text(hf.getValue()).setStyle(bold12CenteredStyle).setHorizontalAlignment(HorizontalAlignment.LEFT))
					.newRow();
				}
			}
		}
		hlb
		.add(cmp.line());
		return hlb;
	}

	public static CurrencyValueFormatter createCurrencyValueFormatter(String label) {
		return new CurrencyValueFormatter(label);
	}

	public static class CurrencyType extends BigDecimalType {
		private static final long serialVersionUID = 1L;

		@Override
		public String getPattern() {
			return "$ #,###.00";
		}
	}

	private static class CurrencyValueFormatter extends AbstractValueFormatter<String, Number> {
		private static final long serialVersionUID = 1L;

		private String label;

		public CurrencyValueFormatter(String label) {
			this.label = label;
		}

		@Override
		public String format(Number value, ReportParameters reportParameters) {
			return label + currencyType.valueToString(value, reportParameters.getLocale());
		}
	}
}