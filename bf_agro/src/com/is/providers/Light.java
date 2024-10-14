package com.is.providers;

//import java.net.HttpURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.is.ConnectionPool;
import com.is.utils.NilProvider;




public class Light extends BaseProvider{

        private NilProvider np = null;

        @Override
        public PayResp check(Credentials cr, Payment pay) {
                StringBuffer sb = new StringBuffer();
                HashMap< String,String> addInfo = new HashMap<String,String>();
                getRes().setCode(999);
                try {
                sb.append(this.getEndPoint());
                sb.append("?login="+cr.getUn());
                sb.append("&password="+cr.getPw());
                sb.append("&action=0");
                sb.append("&soato="+getSoato(pay.getDistrict()));
                sb.append("&customer_code="+pay.getP_number());
                sb.append("&payment_system_code=13");
                sb.append("&pay_type=00");
                //sb.append("&soato=26287");
                //sb.append("&soato=26283");
                
                sb.append("&customer_type=F");
               // sb.append("&customer_type=J");
                System.out.println(sb.toString());
                URL url = new URL(sb.toString());
                        //URL url = new URL("http://128.10.10.117:30001/beeline.aspx?ACT=0&PAY_AMOUNT=500&CURRENCY_CODE=1&MSISDN=901878539&PAY_ID=1&USERNAME=admin&PASSWORD=admin&BRANCH=1&SOURCE_TYPE=1&TRADE_POINT=1&RECEIPT_NUM=1");
                initNp();


                HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
                HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
                connection.setHostnameVerifier(allHostsValid);
                connection.setRequestMethod("GET");
                connection.connect();
                String strres = getResp(connection.getInputStream(),"utf-8");
/*
                System.out.println("resp : "+strres);
                System.out.println("state : "+getRespVal(strres, "state"));
                System.out.println("soato : "+getRespVal(strres, "soato"));
                System.out.println("customer_code : "+getRespVal(strres, "customer_code"));
                System.out.println("fio : "+getRespVal(strres, "fio"));
                System.out.println("address : "+getRespVal(strres, "address"));
                System.out.println("last_paid_date : "+getRespVal(strres, "last_paid_date"));
                System.out.println("last_paid : "+getRespVal(strres, "last_paid"));
                System.out.println("tarif_price : "+getRespVal(strres, "tarif_price"));
                System.out.println("saldo : "+getRespVal(strres, "saldo"));
                System.out.println("res_mfo : "+getRespVal(strres, "res_mfo"));
                System.out.println("res_ares_mfo_name : "+getRespVal(strres, "res_mfo_name"));
                System.out.println("res_account_name : "+getRespVal(strres, "res_account_name"));
                System.out.println("res_inn : "+getRespVal(strres, "res_inn"));
                System.out.println("avans : "+getRespVal(strres, "avans"));
                System.out.println("telefon : "+getRespVal(strres, "telefon"));
                System.out.println("msg : "+getRespVal(strres, "msg"));
                System.out.println("except : "+getRespVal(strres, "except"));
*/
                addInfo.put("state",getRespVal(strres, "state"));
                addInfo.put("soato",getRespVal(strres, "soato"));
                addInfo.put("customer_code",getRespVal(strres, "customer_code"));
                addInfo.put("fio",getRespVal(strres, "fio"));
                addInfo.put("address",getRespVal(strres, "address"));
                addInfo.put("last_paid_date",getRespVal(strres, "last_paid_date"));
                addInfo.put("last_paid",getRespVal(strres, "last_paid"));
                addInfo.put("tarif_price",getRespVal(strres, "tarif_price"));
                addInfo.put("saldo",getRespVal(strres, "saldo"));
                addInfo.put("res_mfo",getRespVal(strres, "res_mfo"));
                addInfo.put("res_mfo_name",getRespVal(strres, "res_mfo_name"));
                addInfo.put("res_account_name",getRespVal(strres, "res_account_name"));
                addInfo.put("res_inn",getRespVal(strres, "res_inn"));
                addInfo.put("avans",getRespVal(strres, "avans"));
                addInfo.put("telefon",getRespVal(strres, "telefon"));
                addInfo.put("msg",getRespVal(strres, "msg"));
                addInfo.put("except",getRespVal(strres, "except"));
                addInfo.put("res_account",getRespVal(strres, "res_account"));


                getRes().setCode(Integer.parseInt(getRespVal(strres, "state")));


                } catch (Exception e) {
                        com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
                }

                getPres().setPaym(pay);
                getPres().setRs(getRes());
                getPres().setAddInfo(addInfo);

                return getPres();
        }

        @Override
        public PayResp pay(Credentials cr, Payment pay, HashMap<String,String> addInfo) {
        	PayResp payresp  = check( cr,  pay);
        	
            StringBuffer sb = new StringBuffer();
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
            SimpleDateFormat rf = new SimpleDateFormat("ddMMyy");
            java.util.Date  ndt = new java.util.Date();
            	
            HashMap< String,String> raddInfo = new HashMap<String,String>();
           
            getRes().setCode(999);
            try {
            sb.append(this.getEndPoint());
            
            sb.append("?login="+cr.getUn());
            sb.append("&password="+cr.getPw());
            sb.append("&action=1");
            sb.append("&reestr_code=BK00444"+rf.format(ndt));
            sb.append("&transaction_no="+pay.getId());
            sb.append("&kassa=00001");
            sb.append("&soato="+getSoato(pay.getDistrict()));
            sb.append("&customer_code="+pay.getP_number());
            sb.append("&pay_type=00");
            sb.append("&pay_date="+df.format(ndt));
            sb.append("&pay_time="+tf.format(ndt));
            sb.append("&payment_system_code=13");
            sb.append("&pay_sum="+pay.getAmount());
            sb.append("&customer_type=F");
            System.out.println(sb.toString());
            URL url = new URL(sb.toString());
                    //URL url = new URL("http://128.10.10.117:30001/beeline.aspx?ACT=0&PAY_AMOUNT=500&CURRENCY_CODE=1&MSISDN=901878539&PAY_ID=1&USERNAME=admin&PASSWORD=admin&BRANCH=1&SOURCE_TYPE=1&TRADE_POINT=1&RECEIPT_NUM=1");
            initNp();


            HostnameVerifier allHostsValid = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setHostnameVerifier(allHostsValid);
            connection.setRequestMethod("GET");
            connection.connect();
            String strres = getResp(connection.getInputStream(),"utf-8");
/*
            raddInfo.put("state",getRespVal(strres, "state"));
            raddInfo.put("soato",getRespVal(strres, "soato"));
            raddInfo.put("customer_code",getRespVal(strres, "customer_code"));
            raddInfo.put("fio",getRespVal(strres, "fio"));
            raddInfo.put("address",getRespVal(strres, "address"));
            raddInfo.put("last_paid_date",getRespVal(strres, "last_paid_date"));
            raddInfo.put("last_paid",getRespVal(strres, "last_paid"));
            raddInfo.put("tarif_price",getRespVal(strres, "tarif_price"));
            raddInfo.put("saldo",getRespVal(strres, "saldo"));
            raddInfo.put("res_mfo",getRespVal(strres, "res_mfo"));
            raddInfo.put("res_mfo_name",getRespVal(strres, "res_mfo_name"));
            raddInfo.put("res_account_name",getRespVal(strres, "res_account_name"));
            raddInfo.put("res_inn",getRespVal(strres, "res_inn"));
            raddInfo.put("avans",getRespVal(strres, "avans"));
            raddInfo.put("telefon",getRespVal(strres, "telefon"));
            raddInfo.put("msg",getRespVal(strres, "msg"));
            raddInfo.put("except",getRespVal(strres, "except"));
            raddInfo.put("res_account",getRespVal(strres, "res_account"));
            raddInfo.put("pay_sum",getRespVal(strres, "pay_sum"));
*/            
            payresp.getAddInfo().remove("state");
            payresp.getAddInfo().put("state", getRespVal(strres, "state"));

            payresp.getAddInfo().remove("msg");
            payresp.getAddInfo().put("msg", getRespVal(strres, "msg"));

            
            payresp.getAddInfo().remove("pay_sum");
            payresp.getAddInfo().put("pay_sum", getRespVal(strres, "pay_sum"));
            pay.setP_name(payresp.getAddInfo().get("fio")+" "+payresp.getAddInfo().get("address"));
//            System.out.println("Pay "+pay.getP_name());
            getRes().setCode(Integer.parseInt(getRespVal(strres, "state")));


            } catch (Exception e) {
                    com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
            }

            getPres().setPaym(pay);
            getPres().setRs(getRes());
            getPres().setAddInfo(payresp.getAddInfo());

            return getPres();
        }

        @Override
        public PayResp checkTr(Credentials cr, long id) {
                // TODO Auto-generated method stub
                return null;
        }

        
        
        
        private void initNp(){
            if (np==null){
                  np = new NilProvider();
              np.init();
            }
        }
        private String getSoato(String distr){
                String res = "00000";
                if(distr.equals("016")){res = "03202";}
                else if(distr.equals("006")){res = "03203";}
                else if(distr.equals("008")){res = "03206";}
                else if(distr.equals("010")){res = "03210";}
                else if(distr.equals("011")){res = "03211";}
                else if(distr.equals("012")){res = "03214";}
                else if(distr.equals("210")){res = "03217";}
                else if(distr.equals("014")){res = "03220";}
                else if(distr.equals("007")){res = "03224";}
                else if(distr.equals("015")){res = "03227";}
                else if(distr.equals("214")){res = "03230";}
                else if(distr.equals("005")){res = "03231";}
                else if(distr.equals("017")){res = "03232";}
                else if(distr.equals("018")){res = "03236";}
                else if(distr.equals("002")){res = "03405";}
                else if(distr.equals("003")){res = "03408";}
                else if(distr.equals("019")){res = "06204";}
                else if(distr.equals("022")){res = "06207";}
                else if(distr.equals("020")){res = "06212";}
                else if(distr.equals("021")){res = "06215";}
                else if(distr.equals("029")){res = "06219";}
                else if(distr.equals("023")){res = "06230";}
                else if(distr.equals("082")){res = "06232";}
                else if(distr.equals("027")){res = "06240";}
                else if(distr.equals("024")){res = "06242";}
                else if(distr.equals("025")){res = "06246";}
                else if(distr.equals("026")){res = "06258";}
                else if(distr.equals("030")){res = "06401";}
                else if(distr.equals("041")){res = "08201";}
                else if(distr.equals("037")){res = "08204";}
                else if(distr.equals("033")){res = "08209";}
                else if(distr.equals("040")){res = "08212";}
                else if(distr.equals("034")){res = "08215";}
                else if(distr.equals("036")){res = "08218";}
                else if(distr.equals("042")){res = "08220";}
                else if(distr.equals("035")){res = "08223";}
                else if(distr.equals("039")){res = "08225";}
                else if(distr.equals("032")){res = "08228";}
                else if(distr.equals("038")){res = "08235";}
                else if(distr.equals("217")){res = "08237";}
                else if(distr.equals("031")){res = "08402";}
                else if(distr.equals("209")){res = "35204";}
                else if(distr.equals("191")){res = "35207";}
                else if(distr.equals("193")){res = "35211";}
                else if(distr.equals("182")){res = "35212";}
                else if(distr.equals("184")){res = "35215";}
                else if(distr.equals("192")){res = "35218";}
                else if(distr.equals("185")){res = "35222";}
                else if(distr.equals("216")){res = "35225";}
                else if(distr.equals("187")){res = "35230";}
                else if(distr.equals("188")){res = "35233";}
                else if(distr.equals("189")){res = "35236";}
                else if(distr.equals("190")){res = "35240";}
                else if(distr.equals("183")){res = "35243";}
                else if(distr.equals("194")){res = "35250";}
                else if(distr.equals("180")){res = "35401";}
                else if(distr.equals("181")){res = "35410";}
                else if(distr.equals("045")){res = "10207";}
                else if(distr.equals("046")){res = "10212";}
                else if(distr.equals("047")){res = "10220";}
                else if(distr.equals("053")){res = "10224";}
                else if(distr.equals("048")){res = "10229";}
                else if(distr.equals("057")){res = "10231";}
                else if(distr.equals("052")){res = "10232";}
                else if(distr.equals("221")){res = "10233";}
                else if(distr.equals("056")){res = "10234";}
                else if(distr.equals("054")){res = "10235";}
                else if(distr.equals("053")){res = "10237";}
                else if(distr.equals("050")){res = "10242";}
                else if(distr.equals("213")){res = "10245";}
                else if(distr.equals("049")){res = "10246";}
                else if(distr.equals("051")){res = "10250";}
                else if(distr.equals("043")){res = "10401";}
                else if(distr.equals("062")){res = "12211";}
                else if(distr.equals("063")){res = "12216";}
                else if(distr.equals("064")){res = "12230";}
                else if(distr.equals("062")){res = "12234";}
                else if(distr.equals("065")){res = "12238";}
                else if(distr.equals("067")){res = "12244";}
                else if(distr.equals("211")){res = "12248";}
                else if(distr.equals("066")){res = "12251";}
                else if(distr.equals("058")){res = "12401";}
                else if(distr.equals("059")){res = "12408";}
                else if(distr.equals("069")){res = "14204";}
                else if(distr.equals("070")){res = "14207";}
                else if(distr.equals("078")){res = "14212";}
                else if(distr.equals("071")){res = "14216";}
                else if(distr.equals("072")){res = "14219";}
                else if(distr.equals("073")){res = "14224";}
                else if(distr.equals("074")){res = "14229";}
                else if(distr.equals("075")){res = "14234";}
                else if(distr.equals("079")){res = "14236";}
                else if(distr.equals("076")){res = "14237";}
                else if(distr.equals("077")){res = "14242";}
                else if(distr.equals("068")){res = "14401";}
                else if(distr.equals("034")){res = "14402";}
                else if(distr.equals("092")){res = "18000";}
                else if(distr.equals("080")){res = "18203";}
                else if(distr.equals("081")){res = "18206";}
                else if(distr.equals("083")){res = "18209";}
                else if(distr.equals("084")){res = "18212";}
                else if(distr.equals("085")){res = "18215";}
                else if(distr.equals("086")){res = "18216";}
                else if(distr.equals("087")){res = "18218";}
                else if(distr.equals("091")){res = "18224";}
                else if(distr.equals("095")){res = "18225";}
                else if(distr.equals("089")){res = "18227";}
                else if(distr.equals("082")){res = "18228";}
                else if(distr.equals("090")){res = "18230";}
                else if(distr.equals("034")){res = "18233";}
                else if(distr.equals("088")){res = "18235";}
                else if(distr.equals("094")){res = "18236";}
                else if(distr.equals("046")){res = "18237";}
                else if(distr.equals("093")){res = "18238";}
                else if(distr.equals("215")){res = "18405";}
                else if(distr.equals("111")){res = "22201";}
                else if(distr.equals("107")){res = "22202";}
                else if(distr.equals("099")){res = "22204";}
                else if(distr.equals("112")){res = "22205";}
                else if(distr.equals("102")){res = "22207";}
                else if(distr.equals("100")){res = "22210";}
                else if(distr.equals("101")){res = "22212";}
                else if(distr.equals("109")){res = "22214";}
                else if(distr.equals("108")){res = "22215";}
                else if(distr.equals("106")){res = "22217";}
                else if(distr.equals("110")){res = "22220";}
                else if(distr.equals("105")){res = "22221";}
                else if(distr.equals("103")){res = "22223";}
                else if(distr.equals("104")){res = "22226";}
                else if(distr.equals("098")){res = "22401";}
                else if(distr.equals("118")){res = "24206";}
                else if(distr.equals("119")){res = "24212";}
                else if(distr.equals("123")){res = "24216";}
                else if(distr.equals("120")){res = "24220";}
                else if(distr.equals("121")){res = "24226";}
                else if(distr.equals("126")){res = "24228";}
                else if(distr.equals("122")){res = "24231";}
                else if(distr.equals("124")){res = "24235";}
                else if(distr.equals("114")){res = "24401";}
                else if(distr.equals("116")){res = "24410";}
                else if(distr.equals("115")){res = "24413";}
                else if(distr.equals("197")){res = "26000";}
                else if(distr.equals("198")){res = "26262";}
                else if(distr.equals("199")){res = "26264";}
                else if(distr.equals("200")){res = "26266";}
                else if(distr.equals("201")){res = "26269";}
                else if(distr.equals("202")){res = "26273";}
                else if(distr.equals("203")){res = "26277";}
                else if(distr.equals("204")){res = "26280";}
                else if(distr.equals("205")){res = "26283";}
                else if(distr.equals("206")){res = "26287";}
                else if(distr.equals("207")){res = "26290";}
                else if(distr.equals("208")){res = "26294";}
                else if(distr.equals("132")){res = "27206";}
                else if(distr.equals("128")){res = "27404";}
                else if(distr.equals("133")){res = "27212";}
                else if(distr.equals("137")){res = "27413";}
                else if(distr.equals("135")){res = "27228";}
                else if(distr.equals("131")){res = "27233";}
                else if(distr.equals("145")){res = "27239";}
                else if(distr.equals("143")){res = "27253";}
                else if(distr.equals("039")){res = "27220";}
                else if(distr.equals("136")){res = "27238";}
                else if(distr.equals("138")){res = "27248";}
                else if(distr.equals("139")){res = "27249";}
                else if(distr.equals("141")){res = "27250";}
                else if(distr.equals("142")){res = "27255";}
                else if(distr.equals("144")){res = "27256";}
                else if(distr.equals("129")){res = "27407";}
                else if(distr.equals("131")){res = "27419";}
                else if(distr.equals("147")){res = "27426";}
                else if(distr.equals("146")){res = "27259";}
                else if(distr.equals("158")){res = "30203";}
                else if(distr.equals("159")){res = "30206";}
                else if(distr.equals("153")){res = "30209";}
                else if(distr.equals("154")){res = "30212";}
                else if(distr.equals("152")){res = "30215";}
                else if(distr.equals("157")){res = "30218";}
                else if(distr.equals("164")){res = "30221";}
                else if(distr.equals("160")){res = "30224";}
                else if(distr.equals("161")){res = "30226";}
                else if(distr.equals("162")){res = "30227";}
                else if(distr.equals("163")){res = "30230";}
                else if(distr.equals("165")){res = "30233";}
                else if(distr.equals("152")){res = "30236";}
                else if(distr.equals("166")){res = "30238";}
                else if(distr.equals("150")){res = "30401";}
                else if(distr.equals("167")){res = "30402";}
                else if(distr.equals("151")){res = "30408";}
                else if(distr.equals("149")){res = "30412";}
                else if(distr.equals("172")){res = "33204";}
                else if(distr.equals("171")){res = "33208";}
                else if(distr.equals("176")){res = "33212";}
                else if(distr.equals("178")){res = "33217";}
                else if(distr.equals("173")){res = "33220";}
                else if(distr.equals("174")){res = "33223";}
                else if(distr.equals("212")){res = "33226";}
                else if(distr.equals("177")){res = "33230";}
                else if(distr.equals("175")){res = "33233";}
                else if(distr.equals("179")){res = "33236";}
                else if(distr.equals("168")){res = "33401";}
                else if(distr.equals("170")){res = "33402";}
                else {res = "00000";}
                return res;
        }
        
        public void sendReestr(String branch,String alias){
        	
        	String crlf = "\r\n";
        	String twoHyphens = "--";
        	String boundary =  "*****";
        	
        	String attachmentFileName = "BK"+branch+"281113";
        	String attachmentName = "xml";
        	
        	SimpleDateFormat df = new SimpleDateFormat("ddMMyy");
        	SimpleDateFormat df1 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        	Document doc =null;
        	Element rootElement = null;
        	Element head = null;
        	Element body = null;
        	Element payment = null;
        	Element el = null;
        	Connection c = null;
        	
        	try {
        		
        		
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement("select sum(p.amount)amount, count(*) count_payments from bf_com_jpayments p where exists( "+
                "select 'x' from bf_com_jpay_addinfo a where a.id=p.id and a.a_key='soato' and a.a_value is not null and id in(583, 584, 585))");

        		
        		
        	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
            rootElement = doc.createElement("container");
            doc.appendChild(rootElement);
            head = doc.createElement("head");
            rootElement.appendChild(head);

            el = doc.createElement("reestr_code");
           // el.appendChild(doc.createTextNode("BK"+branch+df.format(new Date())));
            el.appendChild(doc.createTextNode(attachmentFileName));
            head.appendChild(el);
            
            
            ResultSet rs = ps.executeQuery();
           if (rs.next()) {
            
            el = doc.createElement("amount");
            el.appendChild(doc.createTextNode(rs.getString("amount")));
            head.appendChild(el);
            
            el = doc.createElement("count_payments");
            el.appendChild(doc.createTextNode(rs.getString("count_payments")));
            head.appendChild(el);
           }
            
            el = doc.createElement("payment_system_code");
            el.appendChild(doc.createTextNode("13"));
            head.appendChild(el);
            
            
            body = doc.createElement("body");
            rootElement.appendChild(body);

             ps = c.prepareStatement("select p.subbranch_id kassa, p.amount summa,'00' pay_type , p.date_complete date_time, a.a_value soato, p.p_number customer_code,p.id transaction_id,c.a_value mfo,b.a_value current_account "+
"from bf_com_jpayments p, bf_com_jpay_addinfo a ,  bf_com_jpay_addinfo b,  bf_com_jpay_addinfo c "+
"where a.id=p.id and a.a_key='soato' and a.a_value is not null and b.id=p.id and b.a_key='res_account' and c.id=p.id and c.a_key='res_mfo' and p.id in(583, 584, 585)");
            
            
            rs = ps.executeQuery();

            while (rs.next()) {
                payment = doc.createElement("payment");

                el = doc.createElement("kassa");
                el.appendChild(doc.createTextNode(rs.getString("kassa")));
                payment.appendChild(el);;
 
                el = doc.createElement("pay_type");
                el.appendChild(doc.createTextNode(rs.getString("pay_type")));
                payment.appendChild(el);
                
                el = doc.createElement("summa");
                el.appendChild(doc.createTextNode(rs.getString("summa")));
                payment.appendChild(el);
                
                el = doc.createElement("date_time");
                el.appendChild(doc.createTextNode(df1.format(rs.getTimestamp("date_time"))));
                payment.appendChild(el);
                
                el = doc.createElement("soato");
                el.appendChild(doc.createTextNode(rs.getString("soato")));
                payment.appendChild(el);
                
                el = doc.createElement("customer_code");
                el.appendChild(doc.createTextNode(rs.getString("customer_code")));
                payment.appendChild(el);
                
                el = doc.createElement("transaction_id");
                el.appendChild(doc.createTextNode(rs.getString("transaction_id")));
                payment.appendChild(el);
                
                el = doc.createElement("mfo");
                el.appendChild(doc.createTextNode(rs.getString("mfo")));
                payment.appendChild(el);

                el = doc.createElement("current_account");
                el.appendChild(doc.createTextNode(rs.getString("current_account")));
                payment.appendChild(el);
                
                el = doc.createElement("customer_type");
                el.appendChild(doc.createTextNode("F"));
                payment.appendChild(el);


                
                
                body.appendChild(payment);
            }
 

            HostnameVerifier allHostsValid = new HostnameVerifier() {
            	public boolean verify(String hostname, SSLSession session) {
            	    return true;
            	}
            	};   
            	initNp();
            
            System.out.println(DocumentToString(doc));
           
			URL url = new URL("https://test.fido.uz:7004/fbpaynet/?login=test&password=test&action=2");
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setHostnameVerifier(allHostsValid);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			//connection.setUseCaches(false);
/*			
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Cache-Control", "no-cache");
			connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			
			DataOutputStream request = new DataOutputStream(connection.getOutputStream());

			request.writeBytes(twoHyphens + boundary + crlf);
			request.writeBytes("Content-Disposition: form-data; name=\"" + attachmentName + "\";filename=\"" + attachmentFileName + "\"" + crlf);
			request.writeBytes(crlf);
			request.write(DocumentToString(doc).getBytes());
*/			
			
			connection.getOutputStream().write(DocumentToString(doc).getBytes());
			
			
			
			
			
			
			String strres = getResp(connection.getInputStream(), "utf-8");
			System.out.println("Responce : " + strres);
			

            
            
			} catch (Exception e) {
				com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			}            
        	
        	
        }
 
        private static void ElementToStream(Element element, OutputStream out) {
            try {
              DOMSource source = new DOMSource(element);
              StreamResult result = new StreamResult(out);
              TransformerFactory transFactory = TransformerFactory.newInstance();
              Transformer transformer = transFactory.newTransformer();
              transformer.setOutputProperty(OutputKeys.INDENT, "yes");
              transformer.transform(source, result);
            } catch (Exception ex) {
            }
          }
        
        private static String DocumentToString(Document doc) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ElementToStream(doc.getDocumentElement(), baos);
            return new String(baos.toByteArray());
          }

		@Override
		public ListTrResp listTr(Credentials cr, Date startDate, Date endDate) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CheckPerResp checkPer(Credentials cr, Date startDate,
				Date endDate) {
			// TODO Auto-generated method stub
			return null;
		}

}