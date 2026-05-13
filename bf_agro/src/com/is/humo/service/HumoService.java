// 
// Decompiled by Procyon v0.5.36
// 

package com.is.humo.service;

import java.util.Iterator;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import java.io.OutputStream;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import java.io.ByteArrayOutputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Arrays;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import com.is.ConnectionPool;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.JsonParseException;
import com.is.ISLogger;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.is.tieto_globuz.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import java.util.ArrayList;
import java.util.List;

public class HumoService
{
    private static String psql1;
    private static String psql2;
    private static String msql;
    String[] columnNamesList;
    
    static {
        HumoService.psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
        HumoService.psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
        HumoService.msql = "SELECT * FROM TF_humoservice ";
    }
    
    public HumoService() {
        this.columnNamesList = new String[] { "\u041d\u043e\u043c\u0435\u0440 \u043a\u0430\u0440\u0442.\u0441\u0447\u0435\u0442\u0430", "\u041d\u043e\u043c\u0435\u0440 \u043a\u0430\u0440\u0442\u044b", "\u0414\u0430\u0442\u0430 \u0438\u0441\u0442\u0435\u0447\u0435\u043d\u0438\u044f", "\u041a\u043e\u0434 \u0432\u0430\u043b\u044e\u0442\u044b \u0442\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u0438", "\u0422\u0438\u043f \u0442\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u0438", "\u0421\u0443\u043c\u043c\u0430 \u0442\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u0438", "\u041a\u043e\u0434 \u043a\u043e\u043c\u0438\u0441\u0441\u0438\u0438 \u0442\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u0438", "\u041a\u043e\u043c\u0438\u0441\u0441\u0438\u044f \u0442\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u0438", "\u0421\u0443\u043c\u043c\u0430 \u0432 \u0432\u0430\u043b\u044e\u0442\u0435 \u0441\u0447\u0435\u0442\u0430", "\u0414\u0430\u0442\u0430 \u0438 \u0432\u0440\u0435\u043c\u044f \u0442\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u0438", "ID \u0442\u0435\u0440\u043c\u0438\u043d\u0430\u043b\u0430", "\u041a\u043e\u0434 \u0442\u043e\u0440\u0433\u043e\u0432\u0446\u0430", "\u041d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u0435 \u043f\u0440\u043e\u0434\u0430\u0432\u0446\u0430", "\u0412\u043d\u0443\u0442\u0440.\u043d\u043e\u043c\u0435\u0440 \u0441\u0447\u0435\u0442\u0430", "\u041d\u043e\u043c\u0435\u0440 \u0433\u0440\u0443\u043f\u043f\u044b \u0432\u043d\u0443\u0442.\u0441\u0447\u0435\u0442\u0430", "\u041d\u043e\u043c\u0435\u0440 \u043a\u043b\u0438\u0435\u043d\u0442\u0430", "\u041a\u043e\u043b-\u0432\u043e \u0434\u0435\u0441\u044f\u0442\u043e\u043a \u0432 \u0432\u0430\u043b\u044e\u0442\u0435 \u0442\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u0438", "\u0414\u0430\u0442\u0430 \u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u0438", "\u0414\u0430\u0442\u0430 \u043f\u0443\u0431\u043b\u0438\u043a\u0430\u0446\u0438\u0438", "\u0412\u0430\u043b\u044e\u0442\u0430 \u0441\u0447\u0435\u0442\u0430", "\u041d\u043e\u043c\u0435\u0440 \u0441\u0441\u044b\u043b\u043a\u0438", "\u041a\u043e\u0434 \u0430\u0432\u0442\u043e\u0440\u0438\u0437\u0430\u0446\u0438\u0438", "\u0418\u0441\u0442\u043e\u0447\u043d\u0438\u043a \u043a\u043e\u0434\u0430 \u0430\u0432\u0442\u043e\u0440\u0438\u0437\u0430\u0446\u0438\u0438", "\u041d\u043e\u043c\u0435\u0440 \u0430\u0443\u0434\u0438\u0442\u0430 \u0442\u0440\u0430\u0441\u0441\u0438\u0440\u043e\u0432\u043a\u0438 \u0441\u0438\u0441\u0442\u0435\u043c\u044b", "\u041a\u043e\u0434 \u0442\u043e\u0447\u043a\u0438 \u043f\u0440\u043e\u0434\u0430\u0436\u0438", "\u041a\u043e\u0434 \u043a\u0430\u0442\u0435\u0433\u043e\u0440\u0438\u0438 \u043a\u043b\u0438\u0440\u0438\u043d\u0433\u0430 \u043f\u0440\u043e\u0434\u0430\u0432\u0446\u0430", "\u0413\u043e\u0440\u043e\u0434 \u043f\u0440\u043e\u0434\u0430\u0432\u0446\u0430", "\u0421\u0442\u0440\u0430\u043d\u0430 \u043f\u0440\u043e\u0434\u0430\u0432\u0446\u0430", "\u041e\u043f\u0438\u0441\u0430\u043d\u0438\u0435 \u0442\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u0438", "\u041a\u043e\u043d\u0442\u0440\u0430\u0433\u0435\u043d\u0442\u044b", "\u0412\u043d\u0443\u0442\u0440\u0435\u043d\u043d\u0438\u0439 \u043d\u043e\u043c\u0435\u0440 \u0441\u0442\u0440\u043e\u043a\u0438", "\u041d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u0435 \u043f\u0440\u0438\u043d\u0438\u043c\u0430\u0442\u0435\u043b\u044f", "\u041a\u043e\u0434 \u0431\u0430\u043d\u043a\u0430", "\u0413\u0440\u0443\u043f\u043f\u0430 \u043a\u0430\u0440\u0442\u044b", "\u041f\u043e\u0441\u043b\u0435\u0434\u043d\u044f\u044f \u0434\u0430\u0442\u0430 \u0432\u0441\u0442\u0430\u0432\u043a\u0438 \u0438\u043b\u0438 \u043e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u044f", "\u041a\u043e\u0434 \u043a\u043e\u043c\u0438\u0441\u0441\u0438\u0438 \u0442\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u04382", "\u041a\u043e\u043c\u0438\u0441\u0441\u0438\u044f \u0442\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u04382", "\u0424\u043b\u0430\u0433 \u0431\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u043a\u0438", "\u0414\u043e\u043f.\u0438\u043d\u0444\u043e.", "\u0423\u043d\u0438\u043a\u0430\u043b\u044c\u043d\u044b\u0439 \u043d\u043e\u043c\u0435\u0440 \u0442\u0440\u0430\u043d\u0437\u0430\u043a\u0446\u0438\u0438" };
    }
    
    public List<HumoServiceModel> gethumoservice() {
        final List<HumoServiceModel> list = new ArrayList<HumoServiceModel>();
        return list;
    }
    
    private String getCond(final List<FilterField> flfields) {
        if (flfields.size() > 0) {
            return " and ";
        }
        return " where ";
    }
    
    private List<FilterField> getFilterFields(final HumoServiceModelFilter filter) {
        final List<FilterField> flfields = new ArrayList<FilterField>();
        if (!CheckNull.isEmpty(filter.getId())) {
            flfields.add(new FilterField(String.valueOf(this.getCond(flfields)) + "id=?", (Object)filter.getId()));
        }
        if (!CheckNull.isEmpty(filter.getProts_name())) {
            flfields.add(new FilterField(String.valueOf(this.getCond(flfields)) + "prots_name=?", (Object)filter.getProts_name()));
        }
        if (!CheckNull.isEmpty(filter.getDescripsion())) {
            flfields.add(new FilterField(String.valueOf(this.getCond(flfields)) + "descripsion=?", (Object)filter.getDescripsion()));
        }
        if (!CheckNull.isEmpty(filter.getState())) {
            flfields.add(new FilterField(String.valueOf(this.getCond(flfields)) + "state=?", (Object)filter.getState()));
        }
        flfields.add(new FilterField(String.valueOf(this.getCond(flfields)) + "rownum<?", (Object)1001));
        return flfields;
    }
    
    public int getCount(final HumoServiceModelFilter filter) {
        final int n = 0;
        return n;
    }
    
    public List<HumoServiceModel> gethumoservicesFl(final int pageIndex, final int pageSize, final HumoServiceModelFilter filter) {
        final List<HumoServiceModel> list = new ArrayList<HumoServiceModel>();
        return list;
    }
    
    public HumoServiceModel gethumoservice(final int humoserviceId) {
        final HumoServiceModel humoservice = new HumoServiceModel();
        return humoservice;
    }
    
    public static HumoServiceModel create(final HumoServiceModel humoservice) {
        return humoservice;
    }
    
    public static void update(final HumoServiceModel humoservice) {
    }
    
    public static void remove(final HumoServiceModel humoservice) {
    }
    
    public List<RowsItem> getCardHistory(final String cardNumber, final String periodFrom, final String periodTo) throws JsonParseException, JsonMappingException, IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final StringBuilder content = new StringBuilder();
        HttpURLConnection httpConnection = null;
        try {
            final URL url = new URL(String.valueOf(Utils.getValue("HUMO_GATEWAY")) + "/transaction-history?pan=" + cardNumber + "&datefrom=" + periodFrom + "&dateto=" + periodTo);
            httpConnection = (HttpURLConnection)url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            httpConnection.setRequestProperty("Accept", "application/json");
            final Integer responseCode = httpConnection.getResponseCode();
            BufferedReader bufferedReader;
            if (responseCode > 199 && responseCode < 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            }
            else {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
            }
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(new String(line.getBytes("cp1251"), "utf-8"));
            }
            bufferedReader.close();
            final TransactionHistoryResponse transactionHistoryResponse = (TransactionHistoryResponse)objectMapper.readValue(content.toString(), (Class)TransactionHistoryResponse.class);
            if (transactionHistoryResponse.getError() == null) {
                return transactionHistoryResponse.getResult().getRows();
            }
            return new ArrayList<RowsItem>();
        }
        catch (Exception e) {
            ISLogger.getLogger().error((Object)e.getMessage(), (Throwable)e);
            return new ArrayList<RowsItem>();
        }
    }
    
    public int checkCurrentCard(final String cardNumber) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("SELECT COUNT(*) FROM humo_cards WHERE real_card = ?");
            ps.setString(1, cardNumber);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        }
        catch (Exception e) {
            ISLogger.getLogger().error((Object)e.getMessage(), (Throwable)e);
            return result;
        }
        finally {
            Utils.close(rs);
            Utils.close(ps);
            ConnectionPool.close(c);
        }
        Utils.close(rs);
        Utils.close(ps);
        ConnectionPool.close(c);
        return result;
    }
    
    public String getCardBalance(final String cardNumber) throws JsonParseException, JsonMappingException, IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final StringBuilder content = new StringBuilder();
        HttpURLConnection httpConnection = null;
        try {
            final URL url = new URL(String.valueOf(Utils.getValue("HUMO_GATEWAY")) + "/card?cardnumber=" + cardNumber);
            httpConnection = (HttpURLConnection)url.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            httpConnection.setRequestProperty("Accept", "application/json");
            final Integer responseCode = httpConnection.getResponseCode();
            BufferedReader bufferedReader;
            if (responseCode > 199 && responseCode < 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            }
            else {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
            }
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(new String(line.getBytes("cp1251"), "utf-8"));
            }
            bufferedReader.close();
            final GetBalanceResponse balanceResponse = (GetBalanceResponse)objectMapper.readValue(content.toString(), (Class)GetBalanceResponse.class);
            if (balanceResponse.getError() == null) {
                return balanceResponse.getResult().getBalance().getAvailableAmount().toString();
            }
            return "0";
        }
        catch (Exception e) {
            ISLogger.getLogger().error((Object)e.getMessage(), (Throwable)e);
            return "0";
        }
    }
    
    public byte[] getHistoryExcel(final List<RowsItem> items, final String cardNumber, final String dateFrom, final String dateTo) throws IOException {
        XSSFWorkbook workBook = null;
        try {
            final List<String> list = Arrays.asList(this.columnNamesList);
            workBook = new XSSFWorkbook();
            final Sheet workSheet = (Sheet)workBook.createSheet("Card History");
            final Row detailRow = workSheet.createRow(1);
            detailRow.createCell(0).setCellValue(String.format("\u0412\u044b\u043f\u0438\u0441\u043a\u0430 \u043f\u043e \u043a\u0430\u0440\u0442\u0435 %s \u0437\u0430 \u043f\u0435\u0440\u0438\u043e\u0434 \u0441 %s \u043f\u043e %s. Intelligent Solutions", cardNumber, dateFrom, dateTo));
            final Row initialRow = workSheet.createRow(3);
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            for (int column = 0; column < list.size(); ++column) {
                final Cell cell = initialRow.createCell(column);
                final XSSFCellStyle style = workBook.createCellStyle();
                final XSSFFont font = workBook.createFont();
                font.setBold(true);
                style.setFont((Font)font);
                cell.setCellStyle((CellStyle)style);
                cell.setCellValue((String)list.get(column));
            }
            int rowIdx = 4;
            for (final RowsItem rowsItem : items) {
                final Row row = workSheet.createRow(rowIdx++);
                row.createCell(0).setCellValue((rowsItem.getCardAcct() == null) ? "" : rowsItem.getCardAcct());
                row.createCell(1).setCellValue((rowsItem.getCard() == null) ? "" : rowsItem.getCard().toString());
                row.createCell(2).setCellValue((rowsItem.getExpDate() == null) ? "" : rowsItem.getExpDate().toString());
                row.createCell(3).setCellValue((rowsItem.getTranCcy() == null) ? "" : rowsItem.getTranCcy());
                row.createCell(4).setCellValue((rowsItem.getTranType() == null) ? "" : rowsItem.getTranType());
                row.createCell(5).setCellValue((rowsItem.getTranAmt() == null) ? "" : rowsItem.getTranAmt());
                row.createCell(6).setCellValue((rowsItem.getTrCode() == null) ? "" : rowsItem.getTrCode().toString());
                row.createCell(7).setCellValue((rowsItem.getTrFee() == null) ? "" : rowsItem.getTrFee().toString());
                row.createCell(8).setCellValue((rowsItem.getAmountNet() == null) ? "" : rowsItem.getAmountNet());
                row.createCell(9).setCellValue((rowsItem.getTranDateTime() == null) ? "" : rowsItem.getTranDateTime());
                row.createCell(10).setCellValue((rowsItem.getTermId() == null) ? "" : rowsItem.getTermId().toString());
                row.createCell(11).setCellValue((rowsItem.getMerchant() == null) ? "" : rowsItem.getMerchant().toString());
                row.createCell(12).setCellValue((rowsItem.getAbvrName() == null) ? "" : rowsItem.getAbvrName().toString());
                row.createCell(13).setCellValue((rowsItem.getAccountNo() == null) ? "" : rowsItem.getAccountNo());
                row.createCell(14).setCellValue((rowsItem.getClAcctKey() == null) ? "" : rowsItem.getClAcctKey());
                row.createCell(15).setCellValue((rowsItem.getClient() == null) ? "" : rowsItem.getClient());
                row.createCell(16).setCellValue((rowsItem.getCcyExp() == null) ? "" : rowsItem.getCcyExp());
                row.createCell(17).setCellValue((rowsItem.getRecDate() == null) ? "" : rowsItem.getRecDate());
                row.createCell(18).setCellValue((rowsItem.getPostDate() == null) ? "" : rowsItem.getPostDate());
                row.createCell(19).setCellValue((rowsItem.getAccntCcy() == null) ? "" : rowsItem.getAccntCcy());
                row.createCell(20).setCellValue((rowsItem.getAcqrefNr() == null) ? "" : rowsItem.getAcqrefNr().toString());
                row.createCell(21).setCellValue((rowsItem.getAprCode() == null) ? "" : rowsItem.getAprCode());
                row.createCell(22).setCellValue((rowsItem.getAprScr() == null) ? "" : rowsItem.getAprScr().toString());
                row.createCell(23).setCellValue((rowsItem.getStan() == null) ? "" : rowsItem.getStan().toString());
                row.createCell(24).setCellValue((rowsItem.getPointCode() == null) ? "" : rowsItem.getPointCode().toString());
                row.createCell(25).setCellValue((rowsItem.getMccCode() == null) ? "" : rowsItem.getMccCode().toString());
                row.createCell(26).setCellValue((rowsItem.getCity() == null) ? "" : rowsItem.getCity().toString());
                row.createCell(27).setCellValue((rowsItem.getCountry() == null) ? "" : rowsItem.getCountry().toString());
                row.createCell(28).setCellValue((rowsItem.getDealDesc() == null) ? "" : rowsItem.getDealDesc().toString());
                row.createCell(29).setCellValue((rowsItem.getCounterparty() == null) ? "" : rowsItem.getCounterparty().toString());
                row.createCell(30).setCellValue((rowsItem.getInternalNo() == null) ? "" : rowsItem.getInternalNo());
                row.createCell(31).setCellValue((rowsItem.getFld104() == null) ? "" : rowsItem.getFld104().toString());
                row.createCell(32).setCellValue((rowsItem.getBankC() == null) ? "" : rowsItem.getBankC());
                row.createCell(33).setCellValue((rowsItem.getGroupc() == null) ? "" : rowsItem.getGroupc());
                row.createCell(34).setCellValue((rowsItem.getCtime() == null) ? "" : rowsItem.getCtime());
                row.createCell(35).setCellValue((rowsItem.getTrCode2() == null) ? "" : rowsItem.getTrCode2().toString());
                row.createCell(36).setCellValue((rowsItem.getTrFee2() == null) ? "" : rowsItem.getTrFee2().toString());
                row.createCell(37).setCellValue((rowsItem.getLockingFlag() == null) ? "" : rowsItem.getLockingFlag().toString());
                row.createCell(38).setCellValue((rowsItem.getAddInfo() == null) ? "" : rowsItem.getAddInfo());
                row.createCell(39).setCellValue((rowsItem.getRefNumber() == null) ? "" : rowsItem.getRefNumber().toString());
            }
            workBook.write((OutputStream)out);
            return out.toByteArray();
        }
        catch (Exception e) {
            ISLogger.getLogger().error((Object)e.getMessage(), (Throwable)e);
            return null;
        }
        finally {
            workBook.close();
        }
    }
}
