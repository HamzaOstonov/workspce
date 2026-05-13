package com.is.bfreport.poireports;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.format.CellFormat;
import org.apache.poi.ss.format.CellFormatResult;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ToHtml
{
  private final Workbook wb;
  private final Appendable output;
  private boolean completeHTML;
  private Formatter out;
  private boolean gotBounds;
  private int firstColumn;
  private int endColumn;
  private HtmlHelper helper;
  private static final String DEFAULTS_CLASS = "excelDefaults";
  private static final String COL_HEAD_CLASS = "colHeader";
  private static final String ROW_HEAD_CLASS = "rowHeader";
  private static final Map<Short, String> ALIGN = mapFor(new Object[] { Short.valueOf("1"), "left", Short.valueOf("2"), "center", Short.valueOf("3"), "right", Short.valueOf("4"), "left", Short.valueOf("5"), "left", Short.valueOf("6"), "center" });
  private static final Map<Short, String> VERTICAL_ALIGN = mapFor(new Object[] { Short.valueOf("2"), "bottom", Short.valueOf("1"), "middle", Short.valueOf("0"), "top" });
  private static final Map<Short, String> BORDER = mapFor(new Object[] { Short.valueOf("9"), "dashed 1pt", Short.valueOf("11"), "dashed 1pt", Short.valueOf("3"), "dashed 1pt", Short.valueOf("4"), "dotted 1pt", Short.valueOf("6"), "double 3pt", Short.valueOf("7"), "solid 1px", Short.valueOf("2"), "solid 2pt", Short.valueOf("10"), "dashed 2pt", Short.valueOf("12"), "dashed 2pt", Short.valueOf("8"), "dashed 2pt", Short.valueOf("0"), "none", Short.valueOf("13"), "dashed 2pt", Short.valueOf("5"), "solid 3pt", Short.valueOf("1"), "dashed 1pt" });
  
  private static <K, V> Map<K, V> mapFor(Object... mapping)
  {
    Map<K, V> map = new HashMap();
    for (int i = 0; i < mapping.length; i += 2) {
      map.put((K)mapping[i], (V)mapping[(i + 1)]);
    }
    return map;
  }
  
  public static ToHtml create(Workbook wb, Appendable output)
  {
    return new ToHtml(wb, output);
  }
  
  public static ToHtml create(String path, Appendable output)
    throws IOException
  {
    return create(new FileInputStream(path), output);
  }
  
  public static ToHtml create(InputStream in, Appendable output)
    throws IOException
  {
    try
    {
      Workbook wb = WorkbookFactory.create(in);
      return create(wb, output);
    }
    catch (InvalidFormatException e)
    {
      throw new IllegalArgumentException("Cannot create workbook from stream", e);
    }
  }
  
  private ToHtml(Workbook wb, Appendable output)
  {
    if (wb == null) {
      throw new NullPointerException("wb");
    }
    if (output == null) {
      throw new NullPointerException("output");
    }
    this.wb = wb;
    this.output = output;
    setupColorMap();
  }
  
  private void setupColorMap()
  {
    if ((this.wb instanceof HSSFWorkbook)) {
      this.helper = new HSSFHtmlHelper((HSSFWorkbook)this.wb);
    } else if ((this.wb instanceof XSSFWorkbook)) {
      this.helper = new XSSFHtmlHelper((XSSFWorkbook)this.wb);
    } else {
      throw new IllegalArgumentException("unknown workbook type: " + this.wb.getClass().getSimpleName());
    }
  }
  
  public static void main(String[] args)
    throws Exception
  {
    if (args.length < 2)
    {
      System.err.println("usage: ToHtml inputWorkbook outputHtmlFile");
      return;
    }
    ToHtml toHtml = create(args[0], new PrintWriter(new FileWriter(args[1])));
    toHtml.setCompleteHTML(true);
    toHtml.printPage();
  }
  
  public void setCompleteHTML(boolean completeHTML)
  {
    this.completeHTML = completeHTML;
  }
  
  public void printPage()
    throws IOException
  {
    try
    {
      ensureOut();
      if (this.completeHTML)
      {
        this.out.format("<?xml version=\"1.0\" encoding=\"iso-8859-1\" ?>%n", new Object[0]);
        
        this.out.format("<html>%n", new Object[0]);
        this.out.format("<head>%n", new Object[0]);
        this.out.format("</head>%n", new Object[0]);
        this.out.format("<body>%n", new Object[0]);
      }
      print();
      if (this.completeHTML)
      {
        this.out.format("</body>%n", new Object[0]);
        this.out.format("</html>%n", new Object[0]);
      }
    }
    finally
    {
      Closeable closeable;
      if (this.out != null) {
        this.out.close();
      }
      if ((this.output instanceof Closeable))
      {
        closeable = (Closeable)this.output;
        closeable.close();
      }
    }
  }
  
  public void print()
  {
    printInlineStyle();
    printSheets();
  }
  
  private void printInlineStyle()
  {
    this.out.format("<style type=\"text/css\">%n", new Object[0]);
    printStyles();
    this.out.format("</style>%n", new Object[0]);
  }
  
  private void ensureOut()
  {
    if (this.out == null) {
      this.out = new Formatter(this.output);
    }
  }
  
  public void printStyles()
  {
    ensureOut();
    Set<CellStyle> seen = null;
    BufferedReader in = null;
    try
    {
      in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("excelStyle.css")));
      String line;
      while ((line = in.readLine()) != null) {
        this.out.format("%s%n", new Object[] { line });
      }
      if (in != null) {
        try
        {
          in.close();
        }
        catch (IOException e)
        {
          throw new IllegalStateException("Reading standard css", e);
        }
      }
      seen = new HashSet<CellStyle>();
    }
    catch (IOException e)
    {
      throw new IllegalStateException("Reading standard css", e);
    }
    finally
    {
      if (in != null) {
        try
        {
          in.close();
        }
        catch (IOException e)
        {
          throw new IllegalStateException("Reading standard css", e);
        }
      }
    }
    
    for (int i = 0; i < this.wb.getNumberOfSheets(); i++)
    {
      Sheet sheet = this.wb.getSheetAt(i);
      Iterator<Row> rows = sheet.rowIterator();
      while (rows.hasNext())
      {
        Row row = (Row)rows.next();
        for (Cell cell : row)
        {
          CellStyle style = cell.getCellStyle();
          if (!seen.contains(style))
          {
            printStyle(style);
            seen.add(style);
          }
        }
      }
    }
  }
  
  private void printStyle(CellStyle style)
  {
    this.out.format(".%s .%s {%n", new Object[] { "excelDefaults", styleName(style) });
    styleContents(style);
    this.out.format("}%n", new Object[0]);
  }
  
  private void styleContents(CellStyle style)
  {
    styleOut("text-align", Short.valueOf(style.getAlignment()), ALIGN);
    styleOut("vertical-align", Short.valueOf(style.getAlignment()), VERTICAL_ALIGN);
    fontStyle(style);
    borderStyles(style);
    this.helper.colorStyles(style, this.out);
  }
  
  private void borderStyles(CellStyle style)
  {
    styleOut("border-left", Short.valueOf(style.getBorderLeft()), BORDER);
    styleOut("border-right", Short.valueOf(style.getBorderRight()), BORDER);
    styleOut("border-top", Short.valueOf(style.getBorderTop()), BORDER);
    styleOut("border-bottom", Short.valueOf(style.getBorderBottom()), BORDER);
  }
  
  private void fontStyle(CellStyle style)
  {
    Font font = this.wb.getFontAt(style.getFontIndex());
    if (font.getBoldweight() >= 700) {
      this.out.format("  font-weight: bold;%n", new Object[0]);
    }
    if (font.getItalic()) {
      this.out.format("  font-style: italic;%n", new Object[0]);
    }
    int fontheight = font.getFontHeightInPoints();
    if (fontheight == 9) {
      fontheight = 10;
    }
    this.out.format("  font-size: %dpt;%n", new Object[] { Integer.valueOf(fontheight) });
  }
  
  private String styleName(CellStyle style)
  {
    if (style == null) {
      style = this.wb.getCellStyleAt((short)0);
    }
    StringBuilder sb = new StringBuilder();
    Formatter fmt = new Formatter(sb);
    try
    {
      fmt.format("style_%02x", new Object[] { Short.valueOf(style.getIndex()) });
      return fmt.toString();
    }
    finally
    {
      fmt.close();
    }
  }
  
  private <K> void styleOut(String attr, K key, Map<K, String> mapping)
  {
    String value = (String)mapping.get(key);
    if (value != null) {
      this.out.format("  %s: %s;%n", new Object[] { attr, value });
    }
  }
  
  private static int ultimateCellType(Cell c)
  {
    int type = c.getCellType();
    if (type == 2) {
      type = c.getCachedFormulaResultType();
    }
    return type;
  }
  
  private void printSheets()
  {
    ensureOut();
    Sheet sheet = this.wb.getSheetAt(0);
    printSheet(sheet);
  }
  
  public void printSheet(Sheet sheet)
  {
    ensureOut();
    this.out.format("<table class=%s>%n", new Object[] { "excelDefaults" });
    printCols(sheet);
    printSheetContent(sheet);
    this.out.format("</table>%n", new Object[0]);
  }
  
  private void printCols(Sheet sheet)
  {
    this.out.format("<col/>%n", new Object[0]);
    ensureColumnBounds(sheet);
    for (int i = this.firstColumn; i < this.endColumn; i++) {
      this.out.format("<col/>%n", new Object[0]);
    }
  }
  
  private void ensureColumnBounds(Sheet sheet)
  {
    if (this.gotBounds) {
      return;
    }
    Iterator<Row> iter = sheet.rowIterator();
    this.firstColumn = (iter.hasNext() ? Integer.MAX_VALUE : 0);
    this.endColumn = 0;
    while (iter.hasNext())
    {
      Row row = (Row)iter.next();
      short firstCell = row.getFirstCellNum();
      if (firstCell >= 0)
      {
        this.firstColumn = Math.min(this.firstColumn, firstCell);
        this.endColumn = Math.max(this.endColumn, row.getLastCellNum());
      }
    }
    this.gotBounds = true;
  }
  
  private void printColumnHeads()
  {
    this.out.format("<thead>%n", new Object[0]);
    this.out.format("  <tr class=%s>%n", new Object[] { "colHeader" });
    this.out.format("    <th class=%s>&#x25CA;</th>%n", new Object[] { "colHeader" });
    
    StringBuilder colName = new StringBuilder();
    for (int i = this.firstColumn; i < this.endColumn; i++)
    {
      colName.setLength(0);
      int cnum = i;
      do
      {
        colName.insert(0, (char)(65 + cnum % 26));
        cnum /= 26;
      } while (cnum > 0);
      this.out.format("    <th class=%s>%s</th>%n", new Object[] { "colHeader", colName });
    }
    this.out.format("  </tr>%n", new Object[0]);
    this.out.format("</thead>%n", new Object[0]);
  }
  
  private void printSheetContent(Sheet sheet)
  {
    printColumnHeads();
    
    this.out.format("<tbody>%n", new Object[0]);
    Iterator<Row> rows = sheet.rowIterator();
    while (rows.hasNext())
    {
      Row row = (Row)rows.next();
      
      this.out.format("  <tr>%n", new Object[0]);
      this.out.format("    <td class=%s>%d</td>%n", new Object[] { "rowHeader", Integer.valueOf(row.getRowNum() + 1) });
      for (int i = this.firstColumn; i < this.endColumn; i++)
      {
        String content = "&nbsp;";
        String attrs = "";
        CellStyle style = null;
        if ((i >= row.getFirstCellNum()) && (i < row.getLastCellNum()))
        {
          Cell cell = row.getCell(i);
          if (cell != null)
          {
            style = cell.getCellStyle();
            attrs = tagStyle(cell, style);
            
            CellFormat cf = CellFormat.getInstance(style.getDataFormatString());
            
            CellFormatResult result = cf.apply(cell);
            content = result.text;
            if (content.equals("")) {
              content = "&nbsp;";
            }
          }
        }
        this.out.format("    <td class=%s %s>%s</td>%n", new Object[] { styleName(style), attrs, content });
      }
      this.out.format("  </tr>%n", new Object[0]);
    }
    this.out.format("</tbody>%n", new Object[0]);
  }
  
  private String tagStyle(Cell cell, CellStyle style)
  {
    if (style.getAlignment() == 0) {
      switch (ultimateCellType(cell))
      {
      case 1: 
        return "style=\"text-align: left;\"";
      case 4: 
      case 5: 
        return "style=\"text-align: center;\"";
      }
    }
    return "";
  }
}
