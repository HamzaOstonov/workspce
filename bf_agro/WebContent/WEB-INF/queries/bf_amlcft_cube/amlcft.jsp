<%@ page session="true" contentType="text/html; charset=utf-8"%>
<!-- Открыть для банка 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp"%>
 -->
<%@ page import="java.text.SimpleDateFormat,java.util.List"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.sql.*;"%>

<jp:mondrianQuery id="query01" jdbcDriver="oracle.jdbc.OracleDriver"
	jdbcUrl="jdbc:oracle:thin:@128.10.10.209:1521:agro"
	catalogUri="/WEB-INF/queries/amlcft.xml" jdbcUser="bank394"
	jdbcPassword="bank394" connectionPooling="false">	
	SELECT NON EMPTY Measures.OPERATION_ID ON COLUMNS,
        NON EMPTY {[BRANCHCODE.H_BRANCHCODE].[All_H]} ON ROWS 
        FROM [BF_AMLCFT_CUBE] 
</jp:mondrianQuery>
<jp:setParam query="query01" httpParam="param" mdxParam="Param01" />
<c:set var="CASH" scope="session">CASH Mondrian OLAP</c:set>
