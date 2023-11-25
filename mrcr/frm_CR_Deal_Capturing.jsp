<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>


<html>
<head>

<title>LC Monitoring</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript" src="../js/date-picker.js"></script>
<script language="JavaScript" src="../js/aris.js"></script>
<script language="JavaScript" src="../js/util2.js"></script>
<script language="JavaScript" src="../js/util1.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>

<link href="../css/guidefaultGeneral.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../css/utilities.css">

<style>
.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
/*   padding: 8px 16px; */
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
/*   margin: 4px 2px; */
  transition-duration: 0.4s;
  cursor: pointer;
  height: 25px;
}
.button2 {
  background-color: white; 
  color: black; 
  border: 2px solid #008CBA;
}

.button2:hover {
  background-color: #008CBA;
  color: white;
}

.readonly {
  background-color: grey;
}
.tableFixHead {
        overflow-y: auto;
        max-height: 170px;
      }
      .tableFixHead thead th {
        position: sticky;
        top: 0;
      }
      
      
</style>
<script language="javascript">
window.onscroll = function() {myFunction()};

var header = document.getElementById("my");
//alert(header);
var sticky = header.offsetTop;

function myFunction() {
  if (window.pageYOffset > sticky) {
    header.classList.add("sticky");
  } else {
    header.classList.remove("sticky");
  }
}


var newWindow5;
var newWindow6;
var newWindow7;
var newWindow8;
var newWindow13;


function doSubmit()
{
	var flag=true;
	
	//HARSH20210212 var cust_nm = document.getElementById('buyer_cd').value;
	var cust_nm = document.getElementById('customer_abbr').value;
	//var lc_gen_dt = document.getElementById('lc_gen_dt').value;
	var secTyp = document.getElementById( 'secTyp').value;
	
	//var from_dt= document.getElementById('from_dt').value;
	//var to_dt= document.getElementById( 'to_dt').value;

	//var lc_from_dt= document.getElementById('lc_from_dt').value;
	//var lc_to_dt= document.getElementById( 'lc_to_dt').value;

	var final_lc_amount= document.getElementById('final_lc_amount').value;
	//var lc_amount= document.getElementById('lc_amount').value;
	
	//var cust_rate= document.getElementById('cust_rate').value;
	//var eff_dt3= document.getElementById('eff_dt3').value;
	//var sec_ref_no = document.getElementById('ref_no').value;
	
	var currency = document.getElementById('currency').value;
	
	var credit="";
	var eff_dt1="";

	var credit1="";
	var eff_dt2="";
	var msg="";
	if(cust_nm == '0' || cust_nm == '' || cust_nm == ' ')
	{
		flag=false;
		msg+='\nPlease Select Customer Name!!'
	}
	
	if(secTyp=='cust'){
		credit = document.getElementById('credit').value;	
		eff_dt1 = document.getElementById('eff_dt1').value;
		if(credit=='0' || credit==''){
			flag=false;
			msg+='\n Please select Customer Security Type!!';
		}
		if(credit != 'OA' && credit != 'ADV')
		{
			if(eff_dt1==' ' || eff_dt1==''){
				flag=false;
				msg+='\n Please Enter Expected Receipt(Due) Date!!';
			}
			if(final_lc_amount==''  || final_lc_amount ==' '){
				
				flag=false;
				msg+='\n Please Enter Security Amount (INR)!!';
			}
			if(currency=='')
			{
				flag=false;
				msg+='\n Please Select Currency!!';
			}
		}
				
	}
	if(secTyp=='issuance'){
		//var cust_nm = document.getElementById('trans_cd').value;
		credit1 = document.getElementById('credit1').value;	
		eff_dt2 = document.getElementById('eff_dt2').value;	

		/* if(cust_nm == '' || cust_nm == '0')
			{
			msg+='\n Please select Counterparty Name!!';
			} */
		if(credit1=='0' || credit1==''){
			flag=false;
			msg+='\n Please select Outgoing Issuance Security Type!!';
		}
		if(credit1 != 'OA' && credit1 != 'ADV')
		{
			if(eff_dt2==' ' || eff_dt2==''){
				flag=false;
				msg+='\n Please Enter Expected Issuance Date!!';
			}
			if(final_lc_amount==''  || final_lc_amount ==' '){
				
				flag=false;
				msg+='\n Please Enter Security Amount (INR)!!';
			}
			if(currency=='')
			{
				flag=false;
				msg+='\n Please Select Currency!!';
			}
		}
	}
	
	if(secTyp==''){
		flag=false;
		msg+='\n Please Select Security Type!!';
	}
	
	/* if(from_dt==''  || from_dt ==' '){
		
		flag=false;
		msg+='\n Please Enter From Date!!';
	}
	if(to_dt==''  || to_dt ==' '){
		
		flag=false;
		msg+='\n Please Enter To Date!!';
	} */
	
	/* if(lc_from_dt==''  || lc_from_dt ==' '){
		
		flag=false;
		msg+='\n Please Enter Security Duration From Date!!';
	}
	if(lc_to_dt==''  || lc_to_dt ==' '){
		
		flag=false;
		msg+='\n Please Enter Security Duration To Date!!';
	} */
	
	/* if(cust_rate==''  || cust_rate ==' ' || cust_rate =='0'){
			
			flag=false;
			msg+='\n Please Select Customer Rating!!';
		} */
	/* if(eff_dt3==''  || eff_dt3 ==' '){
		
		flag=false;
		msg+='\n Please Select Customer Rating Effective Date!!';
	} */
	/* if(sec_ref_no==''  || sec_ref_no ==' '){
		
		flag=false;
		msg+='\n Please Enter Security Reference No!!';
	} */
	
	
	if(flag)
	{
		var a = confirm("Do you want to Submit Security Details ???");
		
		if(a)
		{
			document.forms[0].submit();
		}		
	}else{
		alert(msg);
	}
}

/* function setSecurity(val){
	
	if(val == 'issuance'){
		document.getElementById( 'issuance').style.display = "table-row"; 
		
	}else{
		document.getElementById( 'issuance').style.display = "none";
	} 
	
	if(val == 'cust'){
		document.getElementById('cust').style.display = "table-row";
	}
	else{
		document.getElementById('cust').style.display = "none";	
	}
	
	document.getElementById('secTyp').value= val;
	
} */

function Do_close(msg,activity,bscode,customer_cd,FGSA_No,FGSA_Rev_No,snNo,snRevNo)
{
	window.opener.refershPar(msg,activity,bscode,customer_cd,FGSA_No,FGSA_Rev_No,snNo,snRevNo);
	window.close();
}

function modify(SelId,indx,customer_cd,gen_dt,Rating,RatingEffDt,SecurityType,flagSecurity,sec_dt,SecurityStartDt,SecurityEndDt,SecurityAmt,SecurityRmk,fin_yr,lc_seq_no,sec_ref_no,mod_flag,dealCD){
	
	document.getElementById('buyer_cd').value=customer_cd;
	var sysdate = document.getElementById('sysdate').value;
	
	if(flagSecurity=='Y' || flagSecurity=='N'){
		//document.getElementById('cust').style.display = "table-row";	
		//document.getElementById('issuance').style.display = "none"; 
		//document.getElementById('rad1').checked=true;
		document.getElementById('credit').value=SecurityType;
		document.getElementById('eff_dt1').value=sec_dt;
		//document.getElementById('secTyp').value="cust";
		
		if(mod_flag=='F'){
			//document.getElementById('rad2').disabled=true;
			document.getElementById('eff_dt1').readOnly=true;
			document.getElementById('dateImg1').style.visibility='hidden';
			document.getElementById('credit').disabled=true;
			document.getElementById('hid_credit').value=SecurityType;
			document.getElementById('existing_dealcd').value=dealCD;
			document.getElementById('ref_no').value=sec_ref_no;
		}else{
			//document.getElementById('rad2').disabled=false;
			document.getElementById('eff_dt1').readOnly=false;
			document.getElementById('dateImg1').style.visibility='visible';
			document.getElementById('credit').disabled=false;
			document.getElementById('existing_dealcd').value='';
			document.getElementById('ref_no').value='';
		}
	}
	
	if(flagSecurity=='O'){
		//document.getElementById('cust').style.display = "none";	
		//document.getElementById('issuance').style.display = "table-row"; 
		
		//document.getElementById('rad2').checked=true;
		document.getElementById('credit1').value=SecurityType;
		document.getElementById('eff_dt2').value=sec_dt;
		document.getElementById('secTyp').value="issuance";
		
		if(mod_flag=='F'){
			//document.getElementById('rad1').disabled=true;
			document.getElementById('eff_dt2').readOnly=true;
			document.getElementById('dateImg2').style.visibility='hidden';
			document.getElementById('credit1').disabled=true;
			document.getElementById('hid_credit1').value=SecurityType;
			
		}else{
			//document.getElementById('rad1').disabled=false;
			document.getElementById('eff_dt2').readOnly=false;
			document.getElementById('dateImg2').style.visibility='visible';
			document.getElementById('credit1').disabled=false;
		}
	}
	//document.getElementById('lc_from_dt').value=SecurityStartDt;
	//document.getElementById('lc_to_dt').value=SecurityEndDt;
	document.getElementById('final_lc_amount').value=SecurityAmt;
	
	//document.getElementById('ref_no').value=sec_ref_no;
	//document.getElementById('ref_no').readOnly=true;
	
	document.forms[0].fin_yr.value=fin_yr;
	document.forms[0].lc_seq_no.value=lc_seq_no;
	
	if(mod_flag=='F'){
		
		
		document.getElementById('lc_gen_dt').value=sysdate;
		//document.getElementById('eff_dt3').value="";
		//document.getElementById('cust_rate').value="0";
		//document.getElementById('remarks').value="";
		
		//document.getElementById('lc_gen_dt').style.backgroundColor='yellow';
		//document.getElementById('cust_rate').style.backgroundColor='yellow';
		//document.getElementById('eff_dt3').style.backgroundColor='yellow';
		//document.getElementById('remarks').style.backgroundColor='yellow';
		
		//document.getElementById('dateImg3').style.visibility='hidden';
		//document.getElementById('dateImg4').style.visibility='hidden';
// 		document.getElementById('credit1').disabled=true;
		
		document.getElementById('save').value='Submit';
		document.forms[0].operation.value='INSERT';
		
		document.getElementById('lc_gen_dt').readOnly=true;
		//document.getElementById('cust_rate').readOnly=true;
		//document.getElementById('eff_dt3').readOnly=true;
		//document.getElementById('lc_from_dt').readOnly=true;
		//document.getElementById('lc_to_dt').readOnly=true;
		document.getElementById('final_lc_amount').readOnly=true;
		//document.getElementById('final_lc_amount').classlist.add("mkrdly");
// 		document.getElementById('remarks').readOnly=true;
	}else{
		
		document.getElementById('lc_gen_dt').value=gen_dt;
		//document.getElementById('eff_dt3').value=RatingEffDt;
		//document.getElementById('cust_rate').value=Rating;
		
		//document.getElementById('eff_dt3').value=RatingEffDt;
		//document.getElementById('cust_rate').value=Rating;
		//document.getElementById('remarks').value=SecurityRmk;
		
	//	document.getElementById('dateImg3').style.visibility='visible';
		//document.getElementById('dateImg4').style.visibility='visible';
		
		
		document.getElementById('save').value='Modify';
		document.forms[0].operation.value='MODIFY';	
		
		document.getElementById('lc_gen_dt').readOnly=false;
		//document.getElementById('cust_rate').readOnly=false;
		//document.getElementById('eff_dt3').readOnly=false;
		//document.getElementById('lc_from_dt').readOnly=false;
		//document.getElementById('lc_to_dt').readOnly=false;
		document.getElementById('final_lc_amount').readOnly=false;
	}
	
}

function openExistSecurity(){
	
	//HARSH20210212 var buyer_cd = document.forms[0].buyer_cd.value;
	var customer_abbr = document.forms[0].customer_abbr.value; //HARSH20210212
	var from_dt = document.forms[0].from_dt.value;
	var to_dt = document.forms[0].to_dt.value;
	var btn = document.forms[0].btn.value;
	var btn1 = "";
	if(btn == "Incoming")
	{
		btn1="R"
	}
	if(btn == "Outgoing")
	{
		btn1="I"
	}
	var fgca_no = document.forms[0].fgca_no.value;
	var fgca_rev_no = document.forms[0].fgca_rev_no.value;
	var sn_no = document.forms[0].sn_no.value;
	var sn_rev_no = document.forms[0].sn_rev_no.value;
	var cont_type = document.forms[0].cont_type.value;
	var deal_no = document.forms[0].deal_no.value; //HARSH20201203
// 	alert(sn_rev_no+"***"+fgca_no+"**"+fgca_rev_no+"****"+sn_no)
	
	 /*HARSH20210212 if(buyer_cd!='' && from_dt!='' && to_dt!=''){
		 window.open("frm_Existing_Sec_List_Pre.jsp?buyer_cd="+buyer_cd+"&from_dt="+from_dt+"&to_dt="+to_dt+"&snNo="+sn_no+"&fgsaNo="+fgca_no+"&revNo="+fgca_rev_no+"&snRev="+sn_rev_no+"&btn="+btn1+"&cont_type="+cont_type+"&deal_no="+deal_no,"_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=500,width=900,height=300");
	}else{
		alert('Mandatory fields cannot be blank!!');
	} */ 

	//HARSH20210212
	//HARSH20210428 if(customer_abbr!='' && from_dt!='' && to_dt!=''){ AFTER ISSUE IN LIVE
	if(customer_abbr!=''){
		 window.open("frm_Existing_Sec_List_Pre.jsp?cust_abbr="+customer_abbr+"&from_dt="+from_dt+"&to_dt="+to_dt+"&snNo="+sn_no+"&fgsaNo="+fgca_no+"&revNo="+fgca_rev_no+"&snRev="+sn_rev_no+"&btn="+btn1+"&cont_type="+cont_type+"&deal_no="+deal_no,"_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=500,width=900,height=300");
	}else{
		alert('Mandatory fields cannot be blank!!');
	}
}

 function disabledDueDtSecAmt()
{
	var secTyp = document.getElementById( 'secTyp').value;
	
	if(secTyp=='cust'){
		credit = document.getElementById('credit').value;	
		if(credit=='OA' || credit=='ADV'){
			document.getElementById('dd_mand').style.display = "none";
			document.getElementById('sa_mand').style.display = "none";
			document.getElementById('cu_mand').style.display = "none";
			
		}
		else
		{
			document.getElementById('dd_mand').style.display = "inline";
			document.getElementById('sa_mand').style.display = "inline";
			document.getElementById('cu_mand').style.display = "inline";
		}	
	}
	if(secTyp=='issuance'){
		credit1 = document.getElementById('credit1').value;	
		if(credit1=='OA' || credit=='ADV'){
			document.getElementById('id_mand').style.display = "none";
			document.getElementById('sa_mand').style.display = "none";
			document.getElementById('cu_mand').style.display = "none";
		}
		else
		{
			document.getElementById('id_mand').style.display = "inline";
			document.getElementById('sa_mand').style.display = "inline";
			document.getElementById('cu_mand').style.display = "inline";
		}	
		
	}
	
}
function modify1(customer_abbr,sec_type,flagSec,StartDt,EndDt,Amt,Rmk,flag,DealCd,sec_due_dt,sec_ref_no,currency,deal_type,vari_value,value_fluc,iss_b_cd,adv_b_cd,adv_b_ref,con_b_cd,con_b_ref,tenor,seq_no,iss_b_ref)
{
	Rmk=Rmk.replaceAll("~~","'");
	//HARSH20210212 document.getElementById('buyer_cd').value=customer_cd;
	document.getElementById('customer_abbr').value=customer_abbr; //HARSH20210212
	//document.getElementById('credit').value=sec_type;
	if(flagSec=='R'){
		document.getElementById('credit').value=sec_type;
		document.getElementById('hid_credit').value=sec_type;
		document.getElementById('credit').disabled=true;
		document.forms[0].eff_dt1.value= sec_due_dt;
		document.getElementById('eff_dt1').readOnly=true;
		document.getElementById('eff_dt1').classList.add("mkRdly");;
		document.getElementById('dateImg1').style.visibility='hidden';
		
	}
	if(flagSec=='I'){
		document.getElementById('credit1').value=sec_type;
		document.getElementById('hid_credit1').value=sec_type;
		document.getElementById('credit1').disabled=true;
		document.forms[0].eff_dt2.value= sec_due_dt;
		document.getElementById('eff_dt2').readOnly=true;
		document.getElementById('eff_dt2').classList.add("mkRdly");
		document.getElementById('dateImg2').style.visibility='hidden';	
	}
	document.getElementById('ref_no').value=sec_ref_no;
	document.getElementById('existing_dealcd').value=DealCd;
	document.getElementById('final_lc_amount').value=Amt;
	document.getElementById('final_lc_amount').readOnly=true;
	document.getElementById('final_lc_amount').classList.add("mkRdly");
	document.getElementById('currency').value=currency;
	document.getElementById('currency').disabled=true;
	document.getElementById('i_dt').value=StartDt;
	document.getElementById('e_dt').value=EndDt;
	document.getElementById('hid_currency').value=currency;
	
	document.forms[0].deal_type.value=deal_type;
	document.forms[0].vari_value.value=vari_value;
	document.forms[0].value_fluc.value=value_fluc;
	document.forms[0].iss_b_cd.value=iss_b_cd;
	document.forms[0].iss_b_ref.value=iss_b_ref;
	document.forms[0].adv_b_cd.value=adv_b_cd;
	document.forms[0].adv_b_ref.value=adv_b_ref;
	document.forms[0].con_b_cd.value=con_b_cd;
	document.forms[0].con_b_ref.value=con_b_ref;
	document.forms[0].tenor.value=tenor;
	
	document.getElementById('save').value='Submit';
	document.forms[0].operation.value='INSERT';
}
function modify2(index,customer_abbr,SecurityDueDt,SecurityTypeDesc,flagSecurity,StartDt,EndDt,Amt,Rmk,seq_no,sec_ref_no,existing,currency,rmk,dealNo)
{
	Rmk=Rmk.replaceAll("~~","'"); //LIVE ISSUE // SINGLE QUOTE IN REMARKS HARSH20210430 
	rmk=rmk.replaceAll("~~","'"); //LIVE ISSUE // SINGLE QUOTE IN REMARKS HARSH20210430 
	//HARSH20210212 document.getElementById('buyer_cd').value=customer_cd;
	document.getElementById('customer_abbr').value=customer_abbr;
	if(flagSecurity == "R")
	{
		document.getElementById('credit').value=SecurityTypeDesc;
		document.getElementById('hid_credit').value=SecurityTypeDesc;
		document.forms[0].eff_dt1.value= SecurityDueDt;
		if(existing != "")
		{
			document.getElementById('credit').disabled=true;
			document.getElementById('eff_dt1').readOnly=true;
			document.getElementById('eff_dt1').classList.add("mkRdly");
			document.getElementById('dateImg1').style.visibility='hidden';
			document.getElementById('final_lc_amount').readOnly=true;
			document.getElementById('final_lc_amount').classList.add("mkRdly");
			document.getElementById('currency').disabled=true;
			document.getElementById('hid_currency').value=currency;
		}
		else
		{
			document.getElementById('credit').disabled=false;
			document.getElementById('eff_dt1').readOnly=false;
			document.getElementById('eff_dt1').classList.remove("mkRdly");
			document.getElementById('dateImg1').style.visibility='visible';
			document.getElementById('final_lc_amount').readOnly=false;
			document.getElementById('final_lc_amount').classList.remove("mkRdly");
			document.getElementById('currency').disabled=false;
		}
	}
	if(flagSecurity == "I")
	{
		document.getElementById('credit1').value=SecurityTypeDesc;
		document.getElementById('hid_credit1').value=SecurityTypeDesc;
		document.forms[0].eff_dt2.value= SecurityDueDt;
		if(existing != "")
		{
			document.getElementById('credit1').disabled=true;
			document.getElementById('eff_dt2').readOnly=true;
			document.getElementById('eff_dt2').classList.add("mkRdly");
			document.getElementById('dateImg2').style.visibility='hidden';
			document.getElementById('final_lc_amount').readOnly=true;
			document.getElementById('final_lc_amount').classList.add("mkRdly");
			document.getElementById('currency').disabled=true;
			document.getElementById('hid_currency').value=currency;
		}
		else
		{
			document.getElementById('credit1').disabled=false;
			document.getElementById('eff_dt2').readOnly=false;
			document.getElementById('eff_dt2').classList.remove("mkRdly");
			document.getElementById('dateImg2').style.visibility='visible';
			document.getElementById('final_lc_amount').readOnly=false;
			document.getElementById('final_lc_amount').classList.remove("mkRdly");
			document.getElementById('currency').disabled=false;
		}
	}
	document.getElementById('i_dt').value=StartDt;
	document.getElementById('e_dt').value=EndDt;
	document.getElementById('remarks').value=rmk;
	document.getElementById('final_lc_amount').value=Amt;
	document.getElementById('currency').value=currency;
	document.getElementById('ref_no').value=sec_ref_no;
	document.getElementById('existing_dealcd').value=existing;
	document.getElementById('lc_seq_no').value=seq_no;

	var old_value="CustABBR="+customer_abbr+"#SecRefNo="+sec_ref_no+"#DealNo="+dealNo+"#SecTy="+SecurityTypeDesc+"#DueDt="+SecurityDueDt+"#Currency="+currency+"#Amt="+Amt+"#Rmk="+rmk.replaceAll("'","''")+"#InOut="+flagSecurity;
	document.getElementById('old_value').value=old_value;
	
	//alert(old_value);
	document.getElementById('save').value='Modify';
	document.forms[0].operation.value='MODIFY';
	
	
}
</script>

</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="Util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.mrcr.DataBean_CR_Security_Report" id=" mst" scope="page"/>   
<jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_modulelock" id="modlock" scope="request"/>
<% 
Util.init();
String syadate = Util.getGen_dt();

//HARSH20210312
String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");

String transporter_cd = request.getParameter("transporter_cd")==null?"0":request.getParameter("transporter_cd");
String customer_cd = request.getParameter("customer_cd")==null?"0":request.getParameter("customer_cd");
String customer_abbr = request.getParameter("cust_abbr")==null?"0":request.getParameter("cust_abbr"); //HARSH20210212
String customer_nm = request.getParameter("customer_nm")==null?"":request.getParameter("customer_nm");
String from_dt = request.getParameter("from_dt")==null?"":request.getParameter("from_dt");
String to_dt = request.getParameter("to_dt")==null?"":request.getParameter("to_dt");
String btn_type = request.getParameter("btn_type")==null?"":request.getParameter("btn_type");


	String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
	String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
	String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");
	
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	String err_msg = request.getParameter("err_msg")==null?"":request.getParameter("err_msg");
	String activity = request.getParameter("activity")==null?"update":request.getParameter("activity");	
	
	String LC_SEQ_NO = request.getParameter("LC_SEQ_NO")==null?"0":request.getParameter("LC_SEQ_NO");
	String eff_dt1 = request.getParameter("eff_dt1")==null?"":request.getParameter("eff_dt1");
	String remarks = request.getParameter("remarks")==null?"":request.getParameter("remarks");
	
	String typ = request.getParameter("typ")==null?"1":request.getParameter("typ");
	String lc_gen_dt = request.getParameter("lc_gen_dt")==null?"":request.getParameter("lc_gen_dt");
	String lc_from_dt = request.getParameter("lc_from_dt")==null?"":request.getParameter("lc_from_dt");
	String lc_to_dt = request.getParameter("lc_to_dt")==null?"":request.getParameter("lc_to_dt");
///	String from_dt = request.getParameter("from_dt")==null?"":request.getParameter("from_dt");
///	String to_dt = request.getParameter("to_dt")==null?"":request.getParameter("to_dt");
	
	String bscode = request.getParameter("bscode")==null?"0":request.getParameter("bscode");
	String FGSA_No = request.getParameter("FGSA_CD")==null?"":request.getParameter("FGSA_CD");
// 	System.out.println("FGSA_No****"+FGSA_No);
	String Rev_No = request.getParameter("rev_no")==null?"":request.getParameter("rev_no");
	String customer = request.getParameter("customer")==null?"":request.getParameter("customer");
	String credit = request.getParameter("credit")==null?"0":request.getParameter("credit");
	String snNo = request.getParameter("SN_NO")==null?"":request.getParameter("SN_NO");
	String snRefNo = request.getParameter("SN_REF_NO")==null?"0":request.getParameter("SN_REF_NO");
	String snRev = request.getParameter("SN_REV_NO")==null?"":request.getParameter("SN_REV_NO");
	String tcq = request.getParameter("tcq")==null?"":request.getParameter("tcq");
	String dcq = request.getParameter("dcq")==null?"":request.getParameter("dcq");
	String original_dcq = request.getParameter("original_dcq")==null?"":request.getParameter("original_dcq");
	String datediff = request.getParameter("datediff")==null?"":request.getParameter("datediff");
	String rate = request.getParameter("rate")==null?"":request.getParameter("rate");
	String START_DT = request.getParameter("START_DT")==null?"":request.getParameter("START_DT");
	String END_DT = request.getParameter("END_DT")==null?"":request.getParameter("END_DT");
	String tax_type = request.getParameter("tax_type")==null?"":request.getParameter("tax_type");
	String map_id = request.getParameter("cont_type")==null?"":request.getParameter("cont_type");
	String cont_type="";
	if(map_id.contains("-")){
		String act_cont_type[]= map_id.split("-");
		cont_type = act_cont_type[0].charAt(0)+"";
		
	}else{
		cont_type = map_id;
	}
 	//System.out.println("customer****"+customer_abbr);
	
	String total_dcq_value = request.getParameter("total_dcq_value")==null?"0":request.getParameter("total_dcq_value");
	String total_tcq_value = request.getParameter("total_tcq_value")==null?"0":request.getParameter("total_tcq_value");
	
	String lc_manual_exchg_rate = request.getParameter("lc_manual_exchg_rate")==null?"":request.getParameter("lc_manual_exchg_rate");
	String lc_manual_exchg_rate_flag = request.getParameter("lc_manual_exchg_rate_flag")==null?"N":request.getParameter("lc_manual_exchg_rate_flag");
	String lc_method = request.getParameter("lc_method")==null?"AUTO":request.getParameter("lc_method");
	String user_defined_dcq = request.getParameter("user_defined_dcq")==null?"":request.getParameter("user_defined_dcq");
	
	String lc_exchg_rate = request.getParameter("lc_exchg_rate")==null?"":request.getParameter("lc_exchg_rate");
	String original_lc_exchg_rate = request.getParameter("original_lc_exchg_rate")==null?"":request.getParameter("original_lc_exchg_rate");
	String lc_base_remark = request.getParameter("lc_base_remark")==null?"":request.getParameter("lc_base_remark");
	String flag_lc_value = request.getParameter("flag_lc_value")==null?"":request.getParameter("flag_lc_value");
	String flag_dcq_tcq = request.getParameter("flag_dcq_tcq")==null?"":request.getParameter("flag_dcq_tcq");
	String dcqdays_tcqpercent_value = request.getParameter("dcqdays_tcqpercent_value")==null?"0":request.getParameter("dcqdays_tcqpercent_value");
	String tax_rate = request.getParameter("tax_rate")==null?"0":request.getParameter("tax_rate");
	String d_tcq = request.getParameter("tcq")==null?"0":request.getParameter("tcq");
	String d_dcq = request.getParameter("dcq")==null?"0":request.getParameter("dcq");
	String issued_type="";
	String received_type ="";
	if(btn_type.equals("Incoming"))
	{
		received_type="R";
		issued_type="";
	}
	else if(btn_type.equals("Outgoing"))
	{
		received_type="";
		issued_type="I";
	}
	
	/* if(btn_type.equalsIgnoreCase("Outgoing"))
		mst.setCallFlag("CR_CustList");
	else
		mst.setCallFlag("LC_SEQ_NO_LIST"); */
		
	mst.setCallFlag("POST_LIST");
	mst.setBuyer_cd(customer_cd);
	mst.setCustomer_ABBR(customer_abbr);
	mst.setFrom_date(from_dt);
	mst.setTo_date(to_dt);
	mst.setFgsaNo(FGSA_No);
	mst.setFgsaRevNo(Rev_No);
	mst.setSnNo(snNo);
	mst.setSnRevNo(snRev);
	mst.setCont_type(cont_type);
	mst.setIssued_type(issued_type);
	mst.setReceived_type(received_type);
	mst.setLC_Mapping_Id(map_id);//HARSH20201203
	//mst.setBtnType(btn_type);
	mst.init();

	//	mst.setCallFlag("CR_CustList");
	//	mst.setFrom_date(from_dt);
	//	mst.setTo_date(to_dt);
	//	mst.init();
	Vector CUSTOMER_CD  = mst.getCUSTOMER_CD();
	Vector CUSTOMER_NM  = mst.getCUSTOMER_NM();
	Vector CUSTOMER_ABBR  = mst.getCUSTOMER_ABBR();
	
	Vector VCust_Cd  = mst.getVCust_Cd();
	Vector VCust_Nm  = mst.getVCust_Nm();
	Vector VCust_Abbr  = mst.getVCust_Abbr();
	
	Vector buyer_cd = mst.getCUSTOMER_CD();
	Vector buyer_nm = mst.getCUSTOMER_NM();
///	Vector buyer_credit = mst.getbuyer_Credit_Rate();
 
	//Following String Getter Method Has Been Defined By Samik Shah On 26th November, 2010 ...
///	String total_lc_amount = masOthers.getTotal_lc_amount();
	
///	String final_lc_amount = request.getParameter("final_lc_amount")==null?total_lc_amount:request.getParameter("final_lc_amount");

    boolean flag = true;
    int sz = 0;
    
    Vector VSecuritySeqNo = mst.getVSecuritySeqNo();
    Vector VSecurityStartDt = mst.getVSecurityStartDt();
    Vector VSecurityEndDt = mst.getVSecurityEndDt();
    Vector VSecurityAmt = mst.getVSecurityAmt();
    Vector VSecurityStatus = mst.getVSecurityStatus();
    Vector VSecurityRating = mst.getVSecurityRating();
    Vector VSecurityRatingEffDt = mst.getVSecurityRatingEffDt();
    Vector VSecurityDueDt = mst.getVSecurityDueDt();
    Vector VSecurityRmk = mst.getVSecurityRmk();
    Vector VSecurityDealCd = mst.getVSecurityDealCd();
    Vector VSecurityType = mst.getVSecurityType();
    Vector VSecurityTransCd = mst.getVSecurityTransCd();
    String total_lc_amount = "";//mst.getTotalSecurityAmt();
    
    String final_lc_amount = request.getParameter("final_lc_amount")==null?total_lc_amount:request.getParameter("final_lc_amount");
	
    Vector VflagSecurity = mst.getVflagSecurity();
    Vector Vsec_dt = mst.getVsec_dt();
    Vector VSecurityTypeDesc = mst.getVSecurityTypeDesc();
    Vector Vfin_yr = mst.getVfin_yr();
    Vector VActual_seq_no  = mst.getVActual_seq_no();
    Vector Vsec_ref_no = mst.getVsec_ref_no();
    Vector Vexisting = mst.getVexisting_cont();
    Vector Vcurrency = mst.getVcurrency();
    Vector VSecStatus = mst.getVSecStatus();
  
    
   // System.out.println(VActual_seq_no.size());
%>

<body <%if(msg.length()>5){ %>onload="Do_close('<%=msg%>','<%=activity%>','<%=bscode%>','<%=customer_cd%>','<%=FGSA_No%>','<%=Rev_No%>','<%=snNo%>','<%=snRev%>');"<%}%>>
<%	if(msg.length()>5)
	{	%>
		<div id="col-three">
		<table width="92%" border="0" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF">
			<tr>
				<td width="100%" style="background:A4E666;">
					<div align="center"><font size="4"><b><%=msg%></b></font></div>
				</td>
			</tr>
		</table>
		</div>
		<br>
<%	}	%>
<form method="post" action="../servlet/Frm_LC_Monitoring">

<%
	String form_id = request.getParameter("form_id")==null?"0":request.getParameter("form_id");
	String form_nm = request.getParameter("form_nm")==null?"":request.getParameter("form_nm");
	/*for(int i=0; i<FORM_CD.size(); i++)
	{
		if((""+FORM_NAME.elementAt(i)).trim().equalsIgnoreCase("L C Monitoring"))
		{
			form_id = ""+FORM_CD.elementAt(i);
			form_nm = "L C Monitoring";
		}
	}*/
%>

<input type="hidden" name="form_id" value="<%=form_id%>">
<input type="hidden" name="form_nm" value="<%=form_nm%>">

<div id="col-three">

<fieldset style="width:98%" align="center">
<table width="100%" border="0">
     <tr class="highlighttext" style="background-color: #d9edf7;">
             <td colspan="12">
    			<div align="left">
					<font size="4" face="Verdana, Arial, Helvetica, sans-serif">
						<b><%= btn_type %> Security Details for <%=customer_abbr%> (<%=map_id%>)</b>
					</font>
				</div>
			</td>
         </tr>
</table>
<div class="tableFixHead">
<table width="100%" border="0" class="scroll" >
	<thead>
   <tr id="my" style="background-color: #d9edf7;">
		<th class="title2" style="background-color: #d9edf7; width="5%" colspan="2"><div align="center">Sr#</div></th>
		<th class="title2" style="background-color: #d9edf7; width="6%"><div align="center">Deal No.#</div></th>
		<!-- <th class="title2" width="6%"><div align="center">Linked</div></th> -->
		<th class="title2" style="background-color: #d9edf7; width="5%"><div align="center">Security Type</div></th>
		<th class="title2" style="background-color: #d9edf7; width="5%"><div align="center">Security Ref. No#</div></th>
		<th class="title2" style="background-color: #d9edf7; width="5%"><div align="center">Currency</div></th>
		<th class="title2" style="background-color: #d9edf7; width="5%"><div align="center">Amt</div></th>
		<th class="title2" style="background-color: #d9edf7; width="5%"><div align="center">Due<br>Date</div></th>
		<th class="title2" style="background-color: #d9edf7; width="5%"><div align="center">Issuing Date</div></th>
		<th class="title2" style="background-color: #d9edf7; width="5%"><div align="center">Expire Date</div></th>
		<th class="title2" style="background-color: #d9edf7; width="20%"><div align="center">Remark</div></th>
		<th class="title2" style="background-color: #d9edf7; width="7%"><div align="center">Status</div></th>
	</tr>
	</thead>
	<%-- <% if(btn_type.equalsIgnoreCase("Incoming")){ %> --%>
  	 <% 	for(int i=0;i<VActual_seq_no.size();i++) 
  		 {%>
		  	<tr class=<%=((i%2)==0?"content1A":"content1")%> style="background-color: white;">
		  		<td <%if(Vexisting.elementAt(i)!=""){%>style="color: blue;"<%}%>>
		  			<%if(VSecStatus.elementAt(i).equals("Pending")){%>
		  				<input type="radio" name="rd1" id="rd<%=i%>" onclick="modify2('<%=i%>','<%=customer_abbr%>','<%=VSecurityDueDt.elementAt(i)%>','<%=VSecurityTypeDesc.elementAt(i)%>','<%=VflagSecurity.elementAt(i)%>','<%=VSecurityStartDt.elementAt(i)%>','<%=VSecurityEndDt.elementAt(i)%>','<%=VSecurityAmt.elementAt(i)%>','<%=VSecurityRmk.elementAt(i).toString().replaceAll("'", "~~")%>','<%=VActual_seq_no.elementAt(i) %>','<%=Vsec_ref_no.elementAt(i) %>','<%=Vexisting.elementAt(i) %>','<%=Vcurrency.elementAt(i) %>','<%=VSecurityRmk.elementAt(i).toString().replaceAll("'", "~~")%>','<%=map_id%>')" > 
		  				<%} %>
		  		</td>
		 		 <td <%if(Vexisting.elementAt(i)!=""){%>style="color: blue;"<%}%>>
		 			<div align="center">
		   				&nbsp;<%=i+1%>
				  	</div>
				</td>
				<td  <%-- TITLE="<%=VSecurityRating.elementAt(i)%>" --%> <%if(Vexisting.elementAt(i)!=""){%>style="color: blue;"<%}%>>
		 			<div align="center">
		   				&nbsp;<%=VSecurityDealCd.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<%-- <td	<%if(Vexisting.elementAt(i)!=""){%>style="color: blue;"<%}%>>
		 			<div align="center">
		   				&nbsp;<%=Vexisting.elementAt(i)%>&nbsp;
				  	</div>
				</td> --%>
				<td <%if(Vexisting.elementAt(i)!=""){%>style="color: blue;"<%}%>>
		 			<div align="center">
		   				&nbsp;<%=VSecurityTypeDesc.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<td <%if(Vexisting.elementAt(i)!=""){%>style="color: blue;"<%}%>>
		 			<div align="center">
		   				&nbsp;<%=Vsec_ref_no.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<td <%if(Vexisting.elementAt(i)!=""){%>style="color: blue;"<%}%>>
		 			<div align="center">
		   				&nbsp;<%=Vcurrency.elementAt(i)%>&nbsp;
				  	</div>
				</td> 
				<td <%if(Vexisting.elementAt(i)!=""){%>style="color: blue;"<%}%>>
		 			<div align="right">
		   				&nbsp;<%=VSecurityAmt.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<td <%if(Vexisting.elementAt(i)!=""){%>style="color: blue;"<%}%>>
		 			<div align="center">
		   				&nbsp;<%=VSecurityDueDt.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<td <%if(Vexisting.elementAt(i)!=""){%>style="color: blue;"<%}%>>
		 			<div align="center">
		   				&nbsp;<%=VSecurityStartDt.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<td <%if(Vexisting.elementAt(i)!=""){%>style="color: blue;"<%}%>>
		 			<div align="center">
		   				&nbsp;<%=VSecurityEndDt.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				 
				 <td <%if(Vexisting.elementAt(i)!=""){%>style="color: blue;"<%}%>>
		 			<div align="right">
		   				<%-- &nbsp;<%=VSecurityTransCd.elementAt(i)%> --%> : <%=VSecurityRmk.elementAt(i)%>:<%if(Vexisting.size()!=0){if(Vexisting.elementAt(i)!=""){ %>Linked <%=Vsec_ref_no.elementAt(i)%><%} }%>&nbsp;
				  	</div>
				</td> 
				<td <%if(Vexisting.elementAt(i)!=""){%>style="color: blue;"<%}%>>
		 			<div align="right">
		   				&nbsp;<%-- <%=VSecurityStatus.elementAt(i)%> --%><%=VSecStatus.elementAt(i)%>&nbsp;
				  	</div>
				</td> 
		  	</tr>
	<%	}	%>
	<%	if(VActual_seq_no.size()==0)
		{	%>
			<tr class="content1" style="background-color: white;">
				<td colspan="12" align="center">
					<b>Security List Not Available  !!!</b>
				</td>
			</tr>
	<%	}%><%-- <%}else{%>
	<% 	for(int i=0;i<VSecuritySeqNo.size();i++) 
    	{	%>
		  	<tr class=<%=((i%2)==0?"content1A":"content1")%>>
		  		<td>
		  			<%if(VSecurityStatus.elementAt(i) == "Pending"){%><input type="radio" name="rd1" id="rd<%=i%>" onclick="modify(this.id,'<%=i%>','<%=customer_cd%>','<%=VSecurityDueDt.elementAt(i)%>','<%=VSecurityRating.elementAt(i)%>','<%=VSecurityRatingEffDt.elementAt(i)%>','<%=VSecurityType.elementAt(i)%>','<%=VflagSecurity.elementAt(i)%>','<%=Vsec_dt.elementAt(i)%>','<%=VSecurityStartDt.elementAt(i)%>','<%=VSecurityEndDt.elementAt(i)%>','<%=VSecurityAmt.elementAt(i)%>','<%=VSecurityRmk.elementAt(i)%>','<%=Vfin_yr.elementAt(i) %>','<%=VActual_seq_no.elementAt(i) %>','<%=Vsec_ref_no.elementAt(i) %>','T','')" > <%} %>
		  		</td>
		 		<td>
		 			<div align="center">
		   				&nbsp;<%=i+1%>
				  	</div>
				</td>
				<td TITLE="<%=VSecurityRating.elementAt(i)%>">
		 			<div align="center">
		   				&nbsp;<%=VSecurityDealCd.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<td>
		 			<div align="center">
		   				&nbsp;<%=VSecurityTypeDesc.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<td>
		 			<div align="center">
		   				&nbsp;<%=Vsec_dt.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				 <td>
		 			<div align="center">
		   				&nbsp;<%=Vsec_ref_no.elementAt(i)%>&nbsp;
				  	</div>
				</td> 
				<td>
		 			<div align="center">
		   				&nbsp;<%=VSecurityStartDt.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<td>
		 			<div align="center">
		   				&nbsp;<%=VSecurityEndDt.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<td>
		 			<div align="right">
		   				&nbsp;<%=VSecurityAmt.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				 <td>
		 			<div align="right">
		   				&nbsp;<%=VSecurityTransCd.elementAt(i)%> : <%=VSecurityRmk.elementAt(i)%>&nbsp;
				  	</div>
				</td> 
				<td>
		 			<div align="right">
		   				&nbsp;<%=VSecurityStatus.elementAt(i)%>&nbsp;
				  	</div>
				</td>
		  	</tr>
	<%	}	%>
	<%	if(VSecuritySeqNo.size()==0)
		{	%>
			<tr class="content1">
				<td colspan="11" align="center">
					<b>Security List Not Available  !!!</b>
				</td>
			</tr>
	<%} }%> --%>  
</table>
</div>
<table width="100%">
	<tr class="title2" style="background-color: #d9edf7;">
		<td colspan="12" align="center">&nbsp;</td>
	</tr>
</table>
</fieldset>
<fieldset style="width:98%" align="center">
	<table width="100%" align="center" style="border-spacing:0px;">
    	 <tr class="highlighttext" style="background-color: #d9edf7;">
             <td colspan="5">
    			<div align="left">
					<font size="5" face="Verdana, Arial, Helvetica, sans-serif">
						<b>Pre-Receipt Deal Capturing for <%=customer_abbr%> (<%=map_id%>)</b>
					</font>
				</div>
			</td>
         </tr>
         <tr class="content1">
             
		
			<td colspan="1" style="background-color: #d9edf7;">
    			<div align="left">
    				<b class="mand">*</b><strong>&nbsp;Customer Name:&nbsp;</strong>
    			</div>
			</td>
			<td colspan="1" style="background-color: white;">
				<div align="left">
					<%--HARSH20210212 <select name="buyer_cd" id="buyer_cd">
	    			  	<option value="0">--Select Customer--</option>
	    			  	<%	
	    			  		for(int i=0;i<CUSTOMER_CD.size();i++)
	    			  		{	%> 
	    			    		<option value="<%=(String)CUSTOMER_CD.elementAt(i)%>"><%=CUSTOMER_NM.elementAt(i)%></option>
	    				<% 	}	%>
	    			</select> --%>
	    			<select name="customer_abbr" id="customer_abbr">
	    			  	<option value="">--Select Customer--</option>
	    			  	<%	
	    			  		for(int i=0;i<CUSTOMER_ABBR.size();i++)
	    			  		{	%> 
	    			    		<option value="<%=CUSTOMER_ABBR.elementAt(i)%>"><%=CUSTOMER_NM.elementAt(i)%></option>
	    				<% 	}	%>
	    			</select>
	    		</div>
	    		 <script>
					<%-- document.forms[0].buyer_cd.value="<%=customer_cd%>"; --%>
					document.forms[0].customer_abbr.value="<%=customer_abbr%>";
				</script>
	    	</td>
	    	<td style="background-color: white;">	
	    		<input type="button" class="button button2" name="" value="Link Existing Security" onclick="openExistSecurity();">
    		</td>
    		<td colspan="2" style="background-color: white;"></td>
	    	<%-- <% if(btn_type.equalsIgnoreCase("Outgoing")){ %>
	    	<td colspan="1">
    			<div align="right">
    				<b class="mand">*</b><strong>&nbsp;Counter Party Name:&nbsp;</strong>
    			</div>
			</td>
			<td colspan="1">
				<div align="right">
	    			<select name="trans_cd" id="trans_cd">
	    			  	<option value="0">--Select Customer--</option>
	    			  	<%	for(int i=0;i<VCust_Cd.size();i++)
	    			  		{	%> 
	    			    		<option value="<%=VCust_Cd.elementAt(i)%>"><%=VCust_Nm.elementAt(i)%></option>
	    				<% 	}	%>
	    			</select>
	    		</div>
	    			<script>
						document.forms[0].trans_cd.value="<%=transporter_cd%>";
					</script>
	    	</td>
	    	<% } else {%>
	    	<td colspan="2">
    			<div align="right">
    				<!-- <b class="mand">*</b> --><strong>&nbsp;&nbsp;</strong>
    			</div>
			</td>
	    	<%} %> --%>
	    </tr>
        <tr class="content1">
		 <td colspan="8"></td>
		<!-- 	<td colspan="12" align="left">
				<b class="mand">*</b><strong>&nbsp;Security Type :</strong> 
				<input type="radio" name="rad" id="rad1" onclick="setSecurity('cust');" checked="checked">&nbsp;Customer&nbsp;&nbsp;
				<input type="radio" name="rad" id="rad2" onclick="setSecurity('issuance');">&nbsp;Outgoing Issuance
				<input type="hidden" name="secTyp" value="cust" id="secTyp">
			</td> -->
		</tr> 
		
	
               
		<tr class="content1">
             <td colspan="5" align="left" style="background-color: #d9edf7;">
    			<b>Duration of Contract</b>
			</td>
		</tr>	
		<tr class="content1">	
			<td align="right" colspan="1" style="background-color: #d9edf7;">
			 	<b class="mand"></b><strong>&nbsp;Start&nbsp;Date:</strong>
			 </td>
			 <td colspan="2" style="background-color: white;">	
			  	<input type="text" name="from_dt" id="from_dt" maxlength="11" size="10" value="<%=from_dt%>" onBlur="<!-- validateDate(this); -->" title="dd/mm/yyyy Format" class="mkrdly" readonly>
			    <!-- <a href="javascript:show_calendar('forms[0].from_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
			    	<img src="../images/calendar.gif" width="25" height="22" border="0">
			    </a> -->   
		     
			 	<b class="mand"></b><strong><strong>&nbsp;End&nbsp;Date:</strong>
			
			  	<input type="text" name="to_dt" id="to_dt" maxlength="11" size="10" value="<%=to_dt%>" onBlur="<!-- validateDate(this); -->" title="dd/mm/yyyy Format" class="mkrdly" readonly>
		     	<!-- <a href="javascript:show_calendar('forms[0].to_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;"> 
		       		<img src="../images/calendar.gif" width="25" height="22" border="0">
		     	</a>  -->    
		     </td>
		     <td align="right" colspan="1" style="background-color: #d9edf7;">
			 	<!-- <b class="mand">*</b> --><strong>&nbsp;TCQ:</strong>
			 </td>
			 <td colspan="1" style="background-color: white;">	
			  	<input type="text" name="tcq" id="tcq" maxlength="11" size="10" value="<%=d_tcq%>" onBlur="<!-- validateDate(this); -->"  class="mkrdly" readonly>
			  
			 	<!-- <b class="mand">*</b> --><strong><strong>&nbsp;DCQ:</strong>
			
			  	<input type="text" name="dcq" id="dcq" maxlength="11" size="10" value="<%=d_dcq%>" onBlur="<!-- validateDate(this); -->"  class="mkrdly" readonly>
		     </td>
		</tr>
		<% if(btn_type.equalsIgnoreCase("Outgoing")){ %>
   <input type="hidden" name="secTyp" value="issuance" id="secTyp">
   <%} else { %>
    <input type="hidden" name="secTyp" value="cust" id="secTyp">
   <%} %>
   
    <% if(btn_type.equalsIgnoreCase("Incoming")){ %>
            <tr class="content1"  id="cust">
            <td width="13%" style="background-color: #d9edf7;">
    			<div align="left">
    				<b class="mand">*</b><strong>&nbsp;Incoming Received Security Type:</strong>
    			</div>
			</td>
			<td width="28%" colspan="2" style="background-color: white;"> 
    			<div align="left">
	    			<select name="credit" id="credit" onchange="disabledDueDtSecAmt()">
	    			  <option value="0">--Select--</option>
	    			  <option value="LC">LC Letter of Credit</option>
	    			  <option value="BG">BG Bank Guarantee</option>
	    			  <option value="PCG">PCG Parent Corporate Guarantee</option>
	    			  <option value="OA">Open Account</option>
	    			  <option value="ADV">Advance Payment</option>
	    			</select>
	    		</div>
	    		<input type="hidden" name="hid_credit" id="hid_credit" value="">
    		</td>
    		<td width="29%" colspan="1" style="background-color: #d9edf7;">
			 	<div align="right">
				 	<b class="mand" id="dd_mand">*</b><strong>&nbsp;Expected Receipt(Due) Date:</strong>
				</div>   
		    </td>
			<td width="30%" align="left" style="background-color: white;">
    			 <div align="left">
    			 	<input type=text name="eff_dt1" id="eff_dt1" maxlength="10" size="10" value="<%=eff_dt1%>" onBlur="validateDate(this);" title="dd/mm/yyyy Format">
				    <a href="javascript:show_calendar('forms[0].eff_dt1');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
				    	<img src="../images/calendar.gif" width="25" height="22" border="0" id="dateImg1">
				    </a>
				 </div>
			</td>
        </tr>
        <%} %>
        <% if(btn_type.equalsIgnoreCase("Outgoing")){ %>
       <tr class="content1" id="issuance">
            <td width="13%" style="background-color: #d9edf7;">
    			<div align="left">
    				<b class="mand">*</b><strong>&nbsp;Outgoing Issuance Security Type:&nbsp;</strong>
    			</div>
			</td>
			<td width="28%" colspan="2" style="background-color: white;">
    			<div align="left">
	    			<select name="credit1" id="credit1" onchange="disabledDueDtSecAmt()">
	    			  <option value="0">--Select--</option>
	    			  <option value="LC">LC Letter of Credit</option>
	    			  <option value="BG">BG Bank Guarantee</option>
	    			  <option value="PCG">PCG Parent Corporate Guarantee</option>
	    			  <option value="OA">Open Account</option>
	    			  <option value="ADV">Advance Payment</option>
	    			</select>
	    		</div>
	    		<input type="hidden" name="hid_credit1" id="hid_credit1" value="">
    		</td>
    		
    		<td width="29%" colspan="1" style="background-color: #d9edf7;">
			 	<div align="right">
				 	 <b class="mand" id="id_mand">*</b><strong>&nbsp;Expected Issuance Date:</strong>
				</div>   
		    </td>
			<td width="30%" align="left" style="background-color: white;">
    			 <div align="left">
    			 	<input type=text name="eff_dt2" id="eff_dt2" maxlength="10" size="10" value="<%=eff_dt1%>" onBlur="validateDate(this);" title="dd/mm/yyyy Format">
				    <a href="javascript:show_calendar('forms[0].eff_dt2');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
				    	<img src="../images/calendar.gif" width="25" height="22" border="0" id="dateImg2">
				    </a>
    			 </div>
			</td>
			</tr>
			
    		<script>
			document.forms[0].credit1.value="<%=credit%>";
			</script>
			<% } %>
       
	
        <tr class="content1">
            <td style="background-color: #d9edf7;">
    			<b><b class="mand" id="cu_mand">*</b>&nbsp;Currency:</b>
			</td>
			<td colspan="2" style="background-color: white;">
    			<select name="currency" id="currency">
    				<option value="">----Select----</option>
    				<option value="INR">INR</option>
    				<option value="USD">USD</option>
    			</select>
    			<input type="hidden" name="hid_currency" id="hid_currency">
			</td>
			<td colspan="1" style="background-color: #d9edf7;">
			<div align="right">
    			<b><b class="mand" id="sa_mand">*</b>&nbsp;Security Amount:</b>
    		</div>
			</td>
			<td colspan="" style="background-color: white;">
    			<input type="text" name="final_lc_amount" id="final_lc_amount" value="<%=final_lc_amount%>" size="10" maxlength="14" onkeyup="checkNumber1(this,13,2);">&nbsp;
			</td>
			
	   			<input type="hidden" name="lc_amount" id="lc_amount" value="<%=total_lc_amount%>" size="10" maxlength="14" readOnly class="mkrdly">
    			<input type="hidden" name="manualExchangeRate" value="<%=lc_manual_exchg_rate%>" size="5" onBlur="checkNumber1(this,6,4);">
			
        </tr>  
        <tr class="content1" style="background-color: #d9edf7;">
			<td>
				<div align="left">
  						<!-- <b class="mand">*</b> --><strong>&nbsp;Remark:&nbsp;</strong>
  					</div>
  				</td>
			<td colspan="11" style="background-color: white;">
				<div align="left">
					&nbsp;<input type="text" name="remarks" id="remarks" value="" size="100" maxlength="500">&nbsp;
				</div>
			</td>
		</tr>
        <tr class="title2" style="background-color: #d9edf7;">
             <td colspan="3" align="left">
    			 &nbsp;&nbsp;
    			 <input type="hidden" name="lc_manual_exchg_rate_flag" value="<%=lc_manual_exchg_rate_flag%>">
    			 <input type="hidden" name="lc_method" value="<%=lc_method%>">
    			 <input type="hidden" name="activity" value="<%=activity%>">
    			 <input type="button" name="reset" value="Reset">
			</td>
			<td colspan="2" align="right">
			<%if(write_permission.trim().equalsIgnoreCase("Y")){ %>
    			 <input type="button" name="save" id="save" value="Submit" title="Click to Submit Data" onclick="doSubmit();">
    		<%}else{ %>
    			<input type="button" name="save" id="save" value="Submit" title="You Do Not Have Write Permission" disabled>
    		<%} %>
			</td>
		</tr>
    </table>
</fieldset>

</div>
		
<input name="option" type="hidden" value="DEAL_CAPTURING">
<input name="operation" type="hidden" value="INSERT">
<input type="hidden" name="fgca_no" value="<%=FGSA_No%>">
<input type="hidden" name="fgca_rev_no" value="<%=Rev_No%>">
<input type="hidden" name="sn_no" value="<%=snNo%>">
<input type="hidden" name="sn_rev_no" value="<%=snRev%>">
<input type="hidden" name="cont_type" value="<%=cont_type%>">
<input type="hidden" name="fin_yr" value="">
<input type="hidden" name="lc_seq_no" id="lc_seq_no" value="">
<input type="hidden" name="sysdate" id="sysdate" value="<%= syadate %>">
<input type="hidden" name="lc_gen_dt" id="lc_gen_dt" value="<%= syadate %>">
<input type="hidden" name="existing_dealcd" id="existing_dealcd" >
<input type="hidden" name="ref_no" id="ref_no" >
<input type="hidden" name="btn" id="btn" value="<%=btn_type%>" >
<input type="hidden" name="i_dt" id="i_dt" value="" >
<input type="hidden" name="e_dt" id="e_dt" value="" >

<input type="hidden" name="deal_no" id="deal_no" value="<%=map_id%>" >

<!-- Added for linking existing security -->
<input type="hidden" name="deal_type" id="deal_type">
<input type="hidden" name="vari_value" id="vari_value">
<input type="hidden" name="value_fluc" id="value_fluc">
<input type="hidden" name="iss_b_cd" id="iss_b_cd">
<input type="hidden" name="iss_b_ref" id="iss_b_ref">
<input type="hidden" name="adv_b_cd" id="adv_b_cd">
<input type="hidden" name="adv_b_ref" id="adv_b_ref">
<input type="hidden" name="con_b_cd" id="con_b_cd">
<input type="hidden" name="con_b_ref" id="con_b_ref">
<input type="hidden" name="tenor" id="tenor">
<input type="hidden" name="cust_abbr" id="cust_abbr" value="<%if(CUSTOMER_ABBR.size()!=0){%><%=CUSTOMER_ABBR.elementAt(0)%><%}%>">
<input type="hidden" name="buyer_cd" value="<%=customer_cd%>">
<!-- HARSH20210108 OLD VALUE -->
<input type="hidden" name="old_value" id="old_value">

<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
<input type="hidden" name="audit_permission" value="<%=audit_permission%>">

</form>
</body>
</html>