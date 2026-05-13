//preload----------------------------------
function preload()
{
	var res = 0;
	var err_msg = 'ActiveX is ok';
	var sign = zk.Widget.$("$sign");
	try {
        var s= new ActiveXObject('TcsAX.Sign');
        res = 0;
        var preloadinfo = new Array (res, err_msg);
    	zAu.send(new zk.Event(sign, 'onActiveX', preloadinfo));
        return true;
    } catch (e) {
    	res = 1;
    	err_msg = 'Error with ActiveX: '+e.toString();
    	var preloadinfo = new Array (res, err_msg);
    	zAu.send(new zk.Event(sign, 'onActiveX', preloadinfo));
    	return true;
    }
}

function preloadAS()
{
	var res = 0;
	var err_msg = 'ActiveX is ok';
	var sign = zk.Widget.$("$sign");
	try {
    	var ASClient = new ActiveXObject("ASClient.ASClientControl");
    	res = 0;
    	var preloadinfo = new Array (res, err_msg);
    	zAu.send(new zk.Event(sign, 'onActiveX', preloadinfo));
    	return true;
    } catch (e) {
    	res = 1;
    	err_msg = 'Error with ActiveX: '+e.toString();
    	var preloadinfo = new Array (res, err_msg);
    	zAu.send(new zk.Event(sign, 'onActiveX', preloadinfo));
    	return false;
    }
}
//checkKeyCode----------------------------------
function checkKeyCode(page, branch, user_id, username, key_code, isLogin)
{
	var res = 0;
	var err_msg = 'KeyCode is ok';
	var sign = zk.Widget.$("$sign");
	try {
		var s = new ActiveXObject('TcsAX.Sign');
		var ccc ="test";
		s.GUI = 0;
		if (s.Protect(ccc, ccc.length)==0) {
			res = 0;
			sign.setValue(s.SignData);
			var keycodeinfo = new Array (page, branch, user_id, username, key_code, isLogin, res, err_msg);
			zAu.send(new zk.Event(sign, 'onCheckKeyCode', keycodeinfo));
		    return true;
		} else {
			res = 1;
			err_msg = 'Error with KeyCode: testcanceled';
			var keycodeinfo = new Array (page, branch, user_id, username, key_code, isLogin, res, err_msg);
			zAu.send(new zk.Event(sign, 'onCheckKeyCode', keycodeinfo));
		    return false;
		}
    } catch(e) {
    	res = 1;
		err_msg = 'Error with KeyCode: '+e.toString();
    	var keycodeinfo = new Array (page, branch, user_id, username, key_code, isLogin, res, err_msg);
		zAu.send(new zk.Event(sign, 'onCheckKeyCode', keycodeinfo));
    	return false;
    }
}

function checkKeyCodeAS(page, branch, user_id, username, key_sn, isLogin)
{
	var res = 0;
	var err_msg = 'KeyCode is ok';
	var sign = zk.Widget.$("$sign");
	var ccc ="test";
	sign.value="SignData";
	try {
		var ASClient = new ActiveXObject("ASClient.ASClientControl");
		ASClient.Init();
		ASClient.Silent=false;
		var ss = ASClient.SignMessageCertCodepage(ccc, key_sn, 1251);
		if (ss=='') {
			var ddd=ASClient.ErrorMsg;
			res = 1;
			err_msg = ddd;
			var keycodeinfo = new Array (page, branch, user_id, username, key_sn, isLogin, res, err_msg);
			zAu.send(new zk.Event(sign, 'onCheckKeyCode', keycodeinfo));
			return false;
		} else {
			res = 0;
			sign.value=ss;
			var keycodeinfo = new Array (page, branch, user_id, username, key_sn, isLogin, res, err_msg);
			zAu.send(new zk.Event(sign, 'onCheckKeyCode', keycodeinfo));
			return true;
		}
	} catch(err) {
		res = 1;
		err_msg = 'Error with KeyCode: '+e.toString();
		var keycodeinfo = new Array (page, branch, user_id, username, key_sn, isLogin, res, err_msg);
		zAu.send(new zk.Event(sign, 'onCheckKeyCode', keycodeinfo));
    	return false;
	}
}
//sign
function sign(key_type, key_code, ccc, addinfo)
{
	var res = 0;
	var err_msg = 'Object № '+addinfo[9]+' signed';
	var sign = zk.Widget.$("$sign");
	try {
		var s = new ActiveXObject('TcsAX.Sign');
		s.GUI = 0;
        s.Protect(ccc, ccc.length);
        sign.setValue(s.SignData);
        res = 0;
        var signinfo = new Array (key_type, key_code, s.SignData, ccc, addinfo, res, err_msg);
		zAu.send(new zk.Event(sign, 'onSign', signinfo));
	} catch (e) {
		res = 1;
        err_msg = 'Error in object № '+addinfo[9]+': '+e.toString();
        var signinfo = new Array (key_type, key_code, '', ccc, addinfo, res, err_msg);
		zAu.send(new zk.Event(sign, 'onSign', signinfo));
        return false;
	}
	return true;
}

function signAS(key_type, key_sn, ccc, addinfo)
{
	var res = 0;
	var err_msg = 'Object № '+addinfo[9]+' signed';
	var sign = zk.Widget.$("$sign");
	try {
	    var ASClient = new ActiveXObject("ASClient.ASClientControl");
	    ASClient.Init();
	    ASClient.Silent=false;
	    var ss = ASClient.SignMessageCertCodepage(ccc, key_sn, 1251);
	    if (ss=='') {
	        var ddd=ASClient.ErrorMsg;
	        res = 1;
	        err_msg = 'Error in object № '+addinfo[9]+': '+ddd;
	        var signinfo = new Array (key_type, key_sn, '', ccc, addinfo, res, err_msg);
			zAu.send(new zk.Event(sign, 'onSign', signinfo));
	        return false;
	    } else {
	    	res = 0;
	        var signinfo = new Array (key_type, key_sn, ss, ccc, addinfo, res, err_msg);
			zAu.send(new zk.Event(sign, 'onSign', signinfo));
	        return true;
	    }
	} catch(e) {
		res = 1;
        err_msg = 'Error in object № '+addinfo[9]+': '+e.toString();
        var signinfo = new Array (key_type, key_sn, '', ccc, addinfo, res, err_msg);
	    zAu.send(new zk.Event(sign, 'onSign', signinfo));
        return false;
    }
}

function signList(key_type, key_code, docs, addinfo)
{
	var res = 0;
	var rs = 0;
	var err_msg = '';
	var sign = zk.Widget.$("$sign");
	try {
		var s = new ActiveXObject('TcsAX.Sign');
		s.GUI = 0;
        for (var i = 0; i < docs.length; i++) {
        	if (docs[i][4] == 0) {
        		err_msg = 'Object № '+docs[i][1]+' signed';
	        	try {
					s.Protect(docs[i][2], docs[i][2].length);
					rs = 0;
	        		docs[i][3] = s.SignData;
					docs[i][4] = rs;
					docs[i][5] = err_msg;
					//sign.setValue(s.SignData);
				} catch (e) {
					rs = 1;
	        		err_msg = 'Error in document № '+docs[i][1]+': '+e.toString();
			        docs[i][3] = '';
			        docs[i][4] = rs;
					docs[i][5] = err_msg;
				}
        	}
		}
        res = 0;
        var signinfo = new Array (key_type, key_code, docs, addinfo, res, 'Ok');
		zAu.send(new zk.Event(sign, 'onSignList', signinfo));
	} catch (e) {
		res = 1;
        err_msg = 'No ActiveX found: '+e.toString();
        var signinfo = new Array (key_type, key_code, docs, addinfo, res, err_msg);
		zAu.send(new zk.Event(sign, 'onSignList', signinfo));
	}
	return true;
}

function signListAS(key_type, key_sn, docs, addinfo)
{
	var res = 0;
	var rs = 0;
	var err_msg = '';
	var sign = zk.Widget.$("$sign");
	try {
		var s = new ActiveXObject('TcsAX.Sign');
		s.GUI = 0;
        for (var i = 0; i < docs.length; i++) {
        	err_msg = 'Document № '+docs[i][1]+' signed';
        	try {
        		if (docs[i][4] == 0) {
	        		var ss = ASClient.SignMessageCertCodepage(docs[i][1], key_sn, 1251);
	        		if (ss=='') {
	        	        var ddd=ASClient.ErrorMsg;
	        	        rs = 1;
	            		err_msg = 'Error in document № '+docs[i][1]+': '+ddd;
	        	        docs[i][3] = '';
	        	        docs[i][4] = rs;
	    				docs[i][5] = err_msg;
	        	    } else {
	        	    	rs = 0;
	        	    	err_msg = 'Document № '+docs[i][1]+' signed';
	    		        docs[i][3] = ss;
	    		        docs[i][4] = rs;
	    				docs[i][5] = err_msg;
	    				sign.setValue(ss);
	        	    }
        		}
			} catch (e) {
				rs = 1;
        		err_msg = 'Error in document № '+docs[i][1]+': '+e.toString();
		        docs[i][3] = '';
		        docs[i][4] = rs;
				docs[i][5] = err_msg;
			}
		}
        res = 0;
        var signinfo = new Array (key_type, key_sn, docs, addinfo, res, 'Ok');
		zAu.send(new zk.Event(sign, 'onSignList', signinfo));
	} catch (e) {
		res = 1;
        err_msg = 'No ActiveX found: '+e.toString();
        var signinfo = new Array (key_type, key_sn, docs, addinfo, res, err_msg);
		zAu.send(new zk.Event(sign, 'onSignList', signinfo));
	}
	return true;
}

/*
function Signtest(docid, ccc, branch, username, client_id)
{
	//var sign = zk.Widget.$($(".sign"));
	alert(docid+', '+ccc+', '+branch+', '+username+', '+client_id);
	try {
		var sign = zk.Widget.$("$sign");
		//var s = new ActiveXObject('TcsAX.Sign');
		//s.GUI = 0;
        //s.Protect(ccc, ccc.length);
        sign.setValue('1234567890');
		//zAu.send(new zk.Event(sign, 'onSign', s.SignData));
        var docsigninfo = new Array (docid, '1234567890', ccc, branch, username, client_id);
		zAu.send(new zk.Event(sign, 'onSign', docsigninfo));
	} catch (e) {
		//txt='<@ww.text name="document.create.error.on.page"/>.\n\n';
        //txt+='<@ww.text name="document.create.error.dont.installed.activex"/>\n\n';
        //txt+='<@ww.text name="document.create.error.click.ok.to.continue"/>.\n\n';
        //alert(txt);
		alert('Error in document № '+docid+': '+e.toString());
        //zAu.send(new zk.Event(sign, 'onSign', false));
        return false;
	}
	return true;
}

function SignDoc(docid, ccc)
{
	//var sign = zk.Widget.$($(".sign"));
	try {
		var sign = zk.Widget.$("$sign");
		var s = new ActiveXObject('TcsAX.Sign');
		s.GUI = 0;
        s.Protect(ccc, ccc.length);
        sign.setValue(s.SignData);
        var documentSign = {
        		id: docid,
        		signBody: ccc,
        		sign: s.SignData
        	};
		zAu.send(new zk.Event(sign, 'onSign', documentSign));
	} catch (e) {
		//txt='<@ww.text name="document.create.error.on.page"/>.\n\n';
        //txt+='<@ww.text name="document.create.error.dont.installed.activex"/>\n\n';
        //txt+='<@ww.text name="document.create.error.click.ok.to.continue"/>.\n\n';
        //alert(txt);
        alert('Error in document № '+docid+': '+e.toString());
        //zAu.send(new zk.Event(sign, 'onSign', false));
        return false;
	}
	return true;
}

function showCashCode()
{
  document.getElementById("codePlat").value = "";
  document.getElementById("cashCode").value = "";
  var docTypeCode;
//���������� ������ ��� ����� ��������� �������
  docTypeCode= document.getElementById('docType').value;

  if(docTypeCode=='01'||docTypeCode=='06'||docTypeCode=='11') document.getElementById('trNaznachCode').style.display='';
  else document.getElementById('trNaznachCode').style.display='none';

  if(docTypeCode=='93'||docTypeCode=='03') document.getElementById('cashCodeBlock').style.display='';
  else document.getElementById('cashCodeBlock').style.display='none';

  if(docTypeCode=='03')
  {
  document.getElementById("Debit").innerHTML = document.getElementById("Credit").getAttribute("value");
  document.getElementById("Credit").innerHTML = document.getElementById("Debit").getAttribute("value");
  }
  else
  {
  document.getElementById("Debit").innerHTML = document.getElementById("Debit").getAttribute("value");
  document.getElementById("Credit").innerHTML = document.getElementById("Credit").getAttribute("value");
  }
}
function signForm()
{
	var summa = document.getElementById("summa").value;
    var clientAccount = document.getElementById("clientAccount").value;
    var corrAccount = document.getElementById("corrAccount").value;
    var clientAN = document.getElementById("clientAccountName").value;
    var ac1 = clientAccount.substring(5,6)+""+clientAccount.substring(6,7)+""+clientAccount.substring(7,8);
    var ac2 = corrAccount.substring(5,6)+""+corrAccount.substring(6,7)+""+corrAccount.substring(7,8);
    var purpose,t_purpose = document.getElementById("purpose").value;

    /* ����������������� ����� �� ��������� � ����� �� ������ "" � "0" *//*
    if(clientAccount != "" && corrAccount != "" && summa != "" && summa != "0")
    {
        if (clientAccount == corrAccount)/* ����� �� ����� ���� ����� *//*
        {
            alert('<@ww.text name="document.create.error.enter.accounts.equal"/>');
            return false;
        }
        if (ac2 != ac1) /* ������ ������ ��������� *//*
        {
            alert('<@ww.text name="document.create.error.currency.not.equal"/>');
            return false;
        }
        if ((clientAccount.substring(0,1) == "9" && corrAccount.substring(0,1) != "9") || ((clientAccount.substring(0,1) != "9" && corrAccount.substring(0,1) == "9")))
        {
            alert('<@ww.text name="document.create.error.impossible.construction"/>');
            return false;
        }
        if (clientAN == "Error! null")
        {
            alert('<@ww.text name="document.create.error.inaccessible.account"/>');
            return false;
        }

        /* ����������� � ������� ������� ���� *//*
        document.getElementById("purpose").value = "";
        document.getElementById("purpose").value += document.getElementById("cashCode").value+document.getElementById("codePlat").value+t_purpose;

        var str;
        var del = '@#$^';
        var signBody = documentForm.signBody;

        str=documentForm.docNum.value+del+documentForm.docDate.value;
        str+=del+documentForm.clientAccount.value+del+documentForm.corrBank.value;
        str+=del+documentForm.corrAccount.value+del+documentForm.corrName.value;
        str+=del+documentForm.summa.value*100+del+documentForm.purpose.value;
        str+=del+documentForm.docType.value+del+documentForm.cashCode.value;
        str+=del+documentForm.cashDesc.value;
        signBody.value=str;
        return Sign(signBody.value);
    }
    else
    {
        alert('<@ww.text theme="simple" name="document.create.error.enter.required.data"/>');
        return false;
    }
}

function prepareForm()
{
    var docType = "${docType}";
    if(docType!= null && (docType=="" || docType=="01" ||  docType=="06" || docType=="11"))
      {document.getElementById("trNaznachCode").style.display = '';}
    else
      {document.getElementById("trNaznachCode").style.display = 'none';}
}

function insertCashCode()
{

}

function getAccountCurrency()
{
    var clientAccount = document.getElementById("clientAccount").value;
    var ac1 = clientAccount.substring(5,6)+""+clientAccount.substring(6,7)+""+clientAccount.substring(7,8);
    if(ac1 != "000"){ac1 = "   ";}
    return ac1;
}

// check account key
function checkAccountKey(mfo, ac)
{
    var S, BAL, ISO, cl, CLIENT, NUM, Result;
    var N, K;
    var MFO = document.getElementById(mfo).value;
    var acc = document.getElementById(ac).value;
    K = 0;
    BAL = acc.substring(0,5);
    ISO = acc.substring(5,8);
    CL = acc.substring(9,17);
    CLIENT = CL;
    NUM = acc.substring(17,20);
    S = MFO + BAL + ISO + CLIENT + NUM;

    for(N=0; N<=S.length-1; N++)
    {
        if(N == S.length-1)
        {
            K = K + (S.substring(N,N+1)) * 9;
        }
        else
        {
            K = K + (S.substring(N,N+1)) * (S.substring(N+1,N+2));
        }
    }

    K = K % 11;
    if(K == 0)
    {
	    K = 9;
    }
    else if(K == 1)
    {
	    K = 0;
    }
    else
    {
	    K = 11 - K;
    }            
    Result = BAL + ISO + K + CL + NUM;
    // document.getElementById(ac).value = Result;
    if(document.getElementById(ac).value != Result)
    {
        alert('<@ww.text name="document.create.error.entered.wrond.account"/> '+ Result);
    }
    
    //showPlatPor();
    function showPlatPor()
    {
        document.getElementById("viewreport").submit();
                            /*
        var td = document.getElementById("btnTD");
        var rep = document.getElementById("btnReport");
        td.innerHTML += rep;  *//*
    }
}
*/