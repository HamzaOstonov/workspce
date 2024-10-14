package com.is.bfreport.poireports;

import java.util.Formatter;
import org.apache.poi.ss.usermodel.CellStyle;

public abstract interface HtmlHelper
{
  public abstract void colorStyles(CellStyle paramCellStyle, Formatter paramFormatter);
}