package com.is.bfreport.poireports;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XSSFHtmlHelper
  implements HtmlHelper
{
  private final XSSFWorkbook wb;
  private static final Map<Integer, HSSFColor> colors = new HashMap<Integer, HSSFColor>();
  public XSSFHtmlHelper(XSSFWorkbook wb)
  {
    this.wb = wb;
  }
  
  public void colorStyles(CellStyle style, Formatter out)
  {
    XSSFCellStyle cs = (XSSFCellStyle)style;
    styleColor(out, "background-color", cs.getFillForegroundXSSFColor());
    styleColor(out, "text-color", cs.getFont().getXSSFColor());
  }
  
  private void styleColor(Formatter out, String attr, XSSFColor color)
  {
    if ((color == null) || (color.isAuto())) {
      return;
    }
    byte[] rgb = color.getRgb();
    if (rgb == null) {
      return;
    }
    out.format("  %s: #%02x%02x%02x;%n", new Object[] { attr, Byte.valueOf(rgb[0]), Byte.valueOf(rgb[1]), Byte.valueOf(rgb[2]) });
    byte[] argb = color.getARgb();
    if (argb == null) {
      return;
    }
    out.format("  %s: rgba(0x%02x, 0x%02x, 0x%02x, 0x%02x);%n", new Object[] { attr, Byte.valueOf(argb[3]), Byte.valueOf(argb[0]), Byte.valueOf(argb[1]), Byte.valueOf(argb[2]) });
  }
}