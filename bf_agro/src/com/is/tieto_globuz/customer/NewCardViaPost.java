package com.is.tieto_globuz.customer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.tieto_globuz.Utils;
import com.is.utils.Res;

/**
 * Servlet implementation class NewCardViaPost
 */
@WebServlet("/newCardViaPost")
public class NewCardViaPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String HUMO_URL ="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewCardViaPost() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public void init(ServletConfig config) throws ServletException {
		Properties prop = new Properties();
	    try {
    	InputStream inputStream = 
	    	    getClass().getClassLoader().getResourceAsStream("sets.properties");
	    	prop.load(inputStream );

	    	HUMO_URL = prop.getProperty("humo_url");
	        inputStream.close();
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    } 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ObjectMapper objectMapper = new ObjectMapper();
		NewCardRequest requestInput = null;
		Res requestOutput = new Res();
		/*requestInput = objectMapper.readValue(request.getReader(), NewCardRequest.class);
		String branch = requestInput.getBranch();
		String bankC = requestInput.getSettings().get(requestInput.getBranch()).getBank_c();
		String groupC = requestInput.getSettings().get(requestInput.getBranch()).getGroup_c();
		String bin = requestInput.getSettings().get(requestInput.getBranch()).getBincod();
		String city = requestInput.getNew_customer().getR_CITY();
		String client = requestInput.getNew_customer().getTieto_customer_id();
		String country = requestInput.getNew_customer().getR_CNTRY();
		Calendar cal = Calendar.getInstance();
		String street = requestInput.getNew_customer().getR_STREET();*/
		String branch = request.getParameter("BRANCH");
		String bankC = request.getParameter("BANK_C");
		String groupC = request.getParameter("GROUP_C");
		String bin = request.getParameter("BINCODE");
		String city = request.getParameter("CITY");
		String client = request.getParameter("CLIENT");
		String country = request.getParameter("COUNTRY");
		Calendar cal = Calendar.getInstance();
		String street = request.getParameter("STREET");
		String soapmsg1 = "<soapenv:Envelope xmlns:xsi=" +
				"\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=" +
						"\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv="+
				"\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:bin="+
						"\"urn:IssuingWS/binding\" " +
						"xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\">"+
   "<soapenv:Header/>"+
   "<soapenv:Body>"+
      "<bin:newAgreement soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">"+
         "<ConnectionInfo xsi:type=\"urn:OperationConnectionInfo\" xmlns:urn=\"urn:issuing_v_01_02_xsd\">"+
            "<BANK_C xsi:type=\"xsd:string\">"+bankC+"</BANK_C>"+
            "<GROUPC xsi:type=\"xsd:string\">"+groupC+"</GROUPC>"+
            "<EXTERNAL_SESSION_ID xsi:type=\"xsd:string\">"+Utils.getExternalSession()+"</EXTERNAL_SESSION_ID>"+
         "</ConnectionInfo>"+
         "<AgreementInfo xsi:type=\"urn:RowType_Agreement\" xmlns:urn=\"urn:issuing_v_01_02_xsd\">"+
            "<BINCOD xsi:type=\"xsd:string\">"+bin+"</BINCOD>"+
            "<BANK_CODE xsi:type=\"xsd:string\">"+bankC+"</BANK_CODE>"+
            "<BRANCH xsi:type=\"xsd:string\">"+branch+"</BRANCH>"+
            "<CITY xsi:type=\"xsd:string\">"+city+"</CITY>"+
            "<CLIENT xsi:type=\"xsd:string\">"+client+"</CLIENT>"+
            "<COUNTRY xsi:type=\"xsd:string\">"+country+"</COUNTRY>"+
            "<ENROLLED xsi:type=\"xsd:dateTime\">"+cal+"</ENROLLED>"+
            "<PRODUCT xsi:type=\"xsd:string\">"+"01"+"</PRODUCT>"+
            "<REP_LANG xsi:type=\"xsd:string\">"+"1"+"</REP_LANG>"+
            "<STREET xsi:type=\"xsd:string\">"+street+"</STREET>"+
            "<STATUS xsi:type=\"xsd:string\">"+"10"+"</STATUS>"+
         "</AgreementInfo>"+
         "<AccountsListInfo xsi:type=\"urn:ListType_AccountInfo\" xmlns:urn=\"urn:issuing_v_01_02_xsd\">"+
            "<row xsi:type=\"urn:RowType_AccountInfo\">"+
               "<base_info xsi:type=\"urn:RowType_AccountInfo_Base\">"+
                  "<C_ACCNT_TYPE xsi:type=\"xsd:string\">"+"00"+"</C_ACCNT_TYPE>"+
                  "<CCY xsi:type=\"xsd:string\">"+"UZS"+"</CCY>"+
                  "<CYCLE xsi:type=\"xsd:string\">"+"001"+"</CYCLE>"+
                  "<MIN_BAL xsi:type=\"xsd:decimal\">"+BigDecimal.ZERO+"</MIN_BAL>"+
                  "<COND_SET xsi:type=\"xsd:string\">"+"001"+"</COND_SET>"+
                  "<STAT_CHANGE xsi:type=\"xsd:string\">"+"1"+"</STAT_CHANGE>"+
                  "<CRD xsi:type=\"xsd:decimal\">"+BigDecimal.ZERO+"</CRD>"+
                  "<NON_REDUCE_BAL xsi:type=\"xsd:decimal\">"+BigDecimal.ZERO+"</NON_REDUCE_BAL>"+
               "</base_info>"+
               "<additional_info xsi:type=\"urn:RowType_AccountInfo_Additional\">"+
                 
               "</additional_info>"+
            "</row>"+
         "</AccountsListInfo>"+
         "<CardsListInfo xsi:type=\"urn:ListType_CardInfo\" xmlns:urn=\"urn:issuing_v_01_02_xsd\">"+
            "<row xsi:type=\"urn:RowType_CardInfo\">"+
               "<LogicalCard xsi:type=\"urn:RowType_CardInfo_LogicalCard\">"+
                  "<BASE_SUPP xsi:type=\"xsd:string\">"+"1"+"</BASE_SUPP>"+
                  "<COND_SET xsi:type=\"xsd:string\">"+"001"+"</COND_SET>"+
                  "<RISK_LEVEL xsi:type=\"xsd:string\">"+"A"+"</RISK_LEVEL>"+
               "</LogicalCard>"+
               "<PhysicalCard xsi:type=\"urn:RowType_CardInfo_PhysicalCard\">"+
               "</PhysicalCard>"+
               "<EMV_Data xsi:type=\"urn:RowType_CardInfo_EMV_Data\">"+
               "</EMV_Data>"+
               "<AddServInfo xsi:type=\"urn1:ArrayOf_tns1_RowType_AddServData\" soapenc:arrayType=\"urn:RowType_AddServData[]\" xmlns:urn1=\"urn:IssuingWS\"/>"+
               "<TSM_Data xsi:type=\"urn:RowType_CardInfo_TSM_Data\">"+
               "</TSM_Data>"+
            "</row>"+
         "</CardsListInfo>"+
      "</bin:newAgreement>"+
   "</soapenv:Body>"+
"</soapenv:Envelope>";
		
		String respStr = postData(HUMO_URL ,soapmsg1);
		response.getWriter().append(respStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private String postData(String burl,String bstr){
		//System.out.println(burl);
		//System.out.println(bstr);
		//String errstr="";
		StringBuilder content = new StringBuilder();
		 try {


	            // URL and parameters for the connection, This particulary returns the information passed
	            URL url = new URL(burl);
	            HttpURLConnection httpConnection  = (HttpURLConnection) url.openConnection();
	            httpConnection.setDoOutput(true);
	            httpConnection.setRequestMethod("POST");
	            httpConnection.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
	            httpConnection.setRequestProperty("Accept", "application/xml");
	            // Not required
	            // urlConnection.setRequestProperty("Content-Length", String.valueOf(input.getBytes().length));

	            // Writes the JSON parsed as string to the connection
	            DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
	            //wr.write(data.toString().getBytes());
	            wr.write(bstr.getBytes());
	            Integer responseCode = httpConnection.getResponseCode();

	            BufferedReader bufferedReader;

	            // Creates a reader buffer
	            if (responseCode > 199 && responseCode < 300) {
	                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
	            } else {
	                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
	            }

	            // To receive the response
	            
	            String line;
	            while ((line = bufferedReader.readLine()) != null) {
	              //  content.append(line).append("\n");
	            	content.append(new String(line.getBytes("cp1251"),"utf-8"));
	            }
	            bufferedReader.close();

	            System.out.println("responce  "+content.toString());

	        } catch (Exception e) {
	        	content.append(e.getMessage());
	        	content.append("\n");
	        	content.append(burl);
	        	content.append("\n");
	        	content.append(bstr);
	            System.out.println("errror!!!  "+e.getMessage());
	        }
	    return content.toString();
	}

}
