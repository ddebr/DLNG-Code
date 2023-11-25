<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title> DLNG </title>

<!-- For accordion  -->
<link rel="stylesheet" href="../responsive/w3/w3.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css">
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="../css/pt_sans.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script src="../js/jquery.min.js"></script>
</head>
<style>
		.subbutton {
		  background-color: #4CAF50; /* Green */
		  border: none;
		  color: white;
		  padding: 10px;
		  text-align: center;
		  text-decoration: none;
		  display: inline-block;
		  font-size: 16px;
		  margin: 4px 2px;
		  cursor: pointer;
		}
		
		.button1 {border-radius: 2px;}
		.button2 {border-radius: 2px; background-color:blue; }
		.button3 {border-radius: 8px;}
		.button4 {border-radius: 12px;}
		.button5 {border-radius: 50%;}
		
	#loading {
	   width: 100%;
	   height: 100%;
	   top: 0;
	   left: 0;
	   position: fixed;
	   display: block;
	   opacity: 0.7;
	   background-color: #fff;
	   z-index: 99;
	   text-align: center;
	}
	
	#loading-image {
	  position: absolute;
	  top: 50%; /*window.innerHeight/2; /*300px;*/
	  left: 50%; /*window.innerWidth/2; /*240px;*/
	  z-index: 100;
	}

</style>
<script>
function confirm_send_mail(){
	var email_desc=document.forms[0].email_body.value;
	var a=confirm("Do You Want to Send Mail?");
	if(a){
		document.getElementById("loading").style.visibility = "visible";
		document.getElementById("loading-image").style.visibility = "visible";
		document.forms[0].sub.disabled=true;
		document.forms[0].email_body.value = email_desc.replace(/\r?\n/g, '<br />'); //RG20191231For allowing enter
		document.forms[0].submit();
	}
}
function view_pdf(pdfpath){
	//alert("in pdfpath");
	window.open(pdfpath,"actionReport","top=10,left=70,width=650,height=650,scrollbars=1,status=1,maximize=yes,resizable=1");
}
function doClose(month,year,bill_cycle,msg)
{
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
		
	window.opener.refreshPageFromChild2(msg, month, year, bill_cycle, write_permission, delete_permission, print_permission, approve_permission, audit_permission, check_permission, authorize_permission)
	window.close();
}
function doClose_sug(month,year,msg,index,cargo_no,mapping_id,selected_customer_id,selected_map_id){
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	window.opener.refreshPageFromChild2(selected_customer_id,selected_map_id,index,cargo_no,mapping_id,msg, month, year, write_permission, delete_permission, print_permission, approve_permission, audit_permission, check_permission, authorize_permission)
	window.close();
}
function doClose_other(month,year,msg,contract_type){
	//alert("in this func");
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	window.opener.refersh(year, month,contract_type,msg, write_permission, check_permission, authorize_permission,approve_permission,print_permission,audit_permission, delete_permission)
	window.close();
}
function doClose_adv(msg,cust_cd,mapping_id,fgsa_cd,cn_rev_no,cn_no,cust_name,fgsa_rev_no,advance_type){
// 	alert("--cust_cd--"+cust_cd);
// 	alert("--mapping_id--"+mapping_id);
// 	alert("--fgsa_cd--"+fgsa_cd);
// 	alert("--cn_rev_no--"+cn_rev_no);
// 	alert("-cn_no--"+cn_no);
// 	alert("-cust_name--"+cust_name);
// 	alert("-fgsa_rev_no--"+fgsa_rev_no);
// 	alert("-advance_type--"+advance_type);
// 	//alert("in func");
// 	var write_permission = document.forms[0].write_permission.value;
// 	var delete_permission = document.forms[0].delete_permission.value;
// 	var print_permission = document.forms[0].print_permission.value;
// 	var check_permission = document.forms[0].check_permission.value;
// 	var authorize_permission = document.forms[0].authorize_permission.value;
// 	var approve_permission = document.forms[0].approve_permission.value;
// 	var audit_permission = document.forms[0].audit_permission.value;
// 	//alert("in func");
	window.opener.refreshPageFromChild2(advance_type,msg,cust_cd,mapping_id,fgsa_cd,cn_rev_no,cn_no,cust_name,fgsa_rev_no);
	window.close();
	
} 
function doClose_latepay(msg,month,year){
	//alert(month+"----"+year);
	
	window.opener.refreshPageFromChild(msg,month,year);
	window.close();
}
</script>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.sales_invoice.Databean_digital_sign" id="dsc" scope="request"/>
<%
util.init();
String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
String path = request.getParameter("invoice_path")==null?"":request.getParameter("invoice_path");
String inv_flag=request.getParameter("inv_flag")==null?"":request.getParameter("inv_flag");
String inv_type=request.getParameter("inv_type")==null?"":request.getParameter("inv_type");
String contact_cd=request.getParameter("contact_cd")==null?"":request.getParameter("contact_cd");
String customer_cd=request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
String invoice_dt=request.getParameter("invoice_dt")==null?"":request.getParameter("invoice_dt");
String contract_type=request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
String month = request.getParameter("month")==null?"0":request.getParameter("month");
String year = request.getParameter("year")==null?"0":request.getParameter("year");
String bill_cycle = request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");
String hlpl_inv_no = request.getParameter("hlpl_inv_no")==null?"":request.getParameter("hlpl_inv_no");
String customer_plant_seq_no= request.getParameter("customer_plant_seq_no")==null?"":request.getParameter("customer_plant_seq_no");
String due_dt = request.getParameter("due_dt")==null?"":request.getParameter("due_dt");
String cr_dr_sign_flag= request.getParameter("cr_dr_sign_flag")==null?"":request.getParameter("cr_dr_sign_flag");
String dr_cr_dt=request.getParameter("dr_cr_dt")==null?"":request.getParameter("dr_cr_dt");
// System.out.println("--contact_cd--"+contact_cd);
//For sug
String mapping_id = request.getParameter("mapping_id")==null?"-1":request.getParameter("mapping_id"); 
String selected_map_id = request.getParameter("selected_map_id")==null?"":request.getParameter("selected_map_id");
String selected_customer_id = request.getParameter("selected_customer_id")==null?"0":request.getParameter("selected_customer_id");
String cargo_no = request.getParameter("cargo_no")==null?"":request.getParameter("cargo_no");
String index = request.getParameter("index")==null?"":request.getParameter("index");
//String new_seq_no=request.getParameter("new_seq_no")==null?"":request.getParameter("new_seq_no");
//For Advance
String acc_hid=request.getParameter("acc_hid")==null?"":request.getParameter("acc_hid");
String advance_type=request.getParameter("advance_type")==null?"":request.getParameter("advance_type");
String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
String cn_no = request.getParameter("cn_no")==null?"":request.getParameter("cn_no");
String cn_rev_no = request.getParameter("cn_rev_no")==null?"":request.getParameter("cn_rev_no");
String customer_abbr=request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
//
//For permissions//
/* String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
String check_permission = request.getParameter("check_permission")==null?"N":request.getParameter("check_permission");
String authorize_permission = request.getParameter("authorize_permission")==null?"N":request.getParameter("authorize_permission");
String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
 */
 
String write_permission = (String)session.getAttribute("write_permission") ;
String delete_permission = (String)session.getAttribute("delete_permission") ;
String print_permission = (String)session.getAttribute("print_permission") ;
String approve_permission = (String)session.getAttribute("approve_permission") ;
String audit_permission = (String)session.getAttribute("audit_permission") ;
String check_permission = (String)session.getAttribute("check_permission") ;
String authorize_permission = (String)session.getAttribute("update_permission") ;
String view_permission = (String)session.getAttribute("view_permission") ;

String alw_submit = "disabled = disabled";
if(delete_permission.equalsIgnoreCase("Y") || approve_permission.equalsIgnoreCase("Y") || audit_permission.equalsIgnoreCase("Y") ||
  check_permission.equalsIgnoreCase("Y") || authorize_permission.equalsIgnoreCase("Y") || write_permission.equalsIgnoreCase("Y")) {
	
	alw_submit = "";
}
// System.out.println("alw_submit---"+alw_submit);
	
String form_cd = request.getParameter("form_cd")==null?"":request.getParameter("form_cd");
String form_name = request.getParameter("form_name")==null?"":request.getParameter("form_name");
HttpServletRequest req=request;

if(inv_type.contains("ORIGINAL")){
	inv_type="O";
}
if(inv_type.equals("DUPLICATE")){
	inv_type="D";
}
if(inv_type.equals("TRIPLICATE")){
	inv_type="T";
}

dsc.setInv_type(inv_type);
dsc.setRequest(req);
dsc.setInv_path(path);
dsc.setInv_flag(inv_flag);
dsc.setContact_person_cd(contact_cd);
dsc.setCustomer_cd(customer_cd);
dsc.setInvoice_dt(invoice_dt);
dsc.setCont_type(contract_type);
dsc.setCustomer_plant_seq_no(customer_plant_seq_no);
dsc.setDue_dt(due_dt);
dsc.setDr_cr_dt(dr_cr_dt);
dsc.setCallFlag("fetch_signed_pdf_details");
dsc.init();
String pdfsignedpath=dsc.getPdfsignedpath();
String from_mail=dsc.getFrom_mail();
String email_to=dsc.getEmail_to();
String temp_email=dsc.getTemp();
String email_to_otherinv=dsc.getEmail_to_otherinv();
String sub_inv_dt=dsc.getSub_inv_dt();
Vector Email_for_email_body=dsc.getEmail_for_email_body();
String email_cc=dsc.getEmail_cc();
String cust_nm=dsc.getCust_nm(); 
String tempdue_dt=dsc.getTemp_due_dt();
String supplier_nm=dsc.getSupplier_nm();
String supplier_abbr=dsc.getSupplier_abbr();
String supplier_gst_no=dsc.getSupplier_gst_no();
String supplier_gst_dt=dsc.getSupplier_gst_dt();
String supplier_cst_no=dsc.getSupplier_cst_no();
String supplier_cst_dt=dsc.getSupplier_cst_dt();
String supplier_pan_no=dsc.getSupplier_pan_no();
String supplier_addr=dsc.getSupplier_addr();
String supplier_city=dsc.getSupplier_city();
String supplier_phone_no=dsc.getSupplier_phone_no();
String supplier_pin_code=dsc.getSupplier_pin_code();
String sub_dr_cr_dt=dsc.getSub_dr_cr_dt();
String Finance_emailids=dsc.getFinance_emailids();
String inv_mail_cc=dsc.getEmail_cc();
String inv_mail_bcc=dsc.getBcc_mail();
// System.out.println("contact_cd*****"+contact_cd);
// System.out.println("email_to*****"+email_to);
// System.out.println("customer_cd*****"+customer_cd);

// temp_email = "Rohit.malhotra@shell.com,Shaily.chauhan@shell.com,Paritosh.P.Shukla@shell.com,Vijayakumar.S@shell.com"; /* By Hiren As per customer req. 20210104 */

// System.out.println("cr_dr_sign_flag*******"+cr_dr_sign_flag);

/* if(contract_type.equals("S") || contract_type.equals("L")){
	if(!cr_dr_sign_flag.equals("")){
		
		if(cr_dr_sign_flag.equals("CO") || cr_dr_sign_flag.equals("SO") || cr_dr_sign_flag.equals("DEO")){
			email_to=dsc.getEmail_to();
			mail_cc=dsc.getEmail_cc();
			mail_bcc=temp_email;
		}else if(cr_dr_sign_flag.equals("CD") || cr_dr_sign_flag.equals("SD") || cr_dr_sign_flag.equals("DED")){
			email_to=dsc.getEmail_to();
			mail_cc=dsc.getEmail_cc();
			mail_bcc=temp_email;
		}
	}else{
		if(inv_type.equals("O")){
			email_to=dsc.getEmail_to();
			mail_cc=dsc.getEmail_cc();
			mail_bcc=temp_email;
		}else if(inv_type.equals("D")){
			email_to=dsc.getEmail_to();
			mail_cc=dsc.getEmail_cc();
			mail_bcc=temp_email;
		}else if(inv_type.equals("T")){
			//email_to=dsc.getEmail_to();
			//email_to=temp_email;
			email_to=Finance_emailids;
			mail_cc="";
			mail_bcc="";
		}
	}
} */
if(!path.equals("")){
	if(contract_type.equalsIgnoreCase("X") || contract_type.equalsIgnoreCase("Y") || contract_type.equalsIgnoreCase("Z") || contract_type.equals("1") || contract_type.equals("2") || contract_type.equalsIgnoreCase("N")){
		path=path;
	}else{
		path=path+".pdf";
	}
}
String sub_dtl="";
String newyear="";
String invnm="";
if(!hlpl_inv_no.equals("")){
// 	System.out.println("--hlpl_inv_no--"+hlpl_inv_no);
	String temp_no[]=hlpl_inv_no.split("/");
	String temp_yr[]=temp_no[1].toString().split("-");
	if(temp_yr[0].length()==2){
		newyear=temp_no[0]+"/20"+temp_yr[0]+"-"+temp_yr[1];
	}else{
		newyear=hlpl_inv_no;
	}
	if(inv_flag.equalsIgnoreCase("LTCORA_")){
		invnm="LTCORA";
	}else if(inv_flag.equalsIgnoreCase("SALES_")){
		invnm="SALES";
	}else if(inv_flag.equalsIgnoreCase("SUG_")){
		invnm="SUG";
	}else if(inv_flag.equalsIgnoreCase("Advance_")){
		invnm="Receipt Voucher";
	}else if(inv_flag.equalsIgnoreCase("LOA_")){
		invnm="LOA";
	}else if(inv_flag.equalsIgnoreCase("CREDIT_")){
		invnm="Credit Note";
	}else if(inv_flag.equalsIgnoreCase("DEBIT_")){
		invnm="Debit Note";
	}
	else if(inv_flag.equalsIgnoreCase("LATEPAY_")){
		invnm="Late Payment";
	}else if(inv_flag.equalsIgnoreCase("SERVICE_")){
		invnm="SERVICE";
	}
	else if(inv_flag.equalsIgnoreCase("OTHER_")){}
	
	if(inv_type.equalsIgnoreCase("O")){
		sub_dtl=cust_nm+"/"+invnm+"/Original/"+sub_inv_dt+"/"+newyear;
	}else if(inv_type.equalsIgnoreCase("D")){
		//sub_dtl="Duplicate Digitally Signed Pdf Invoice";
		sub_dtl=cust_nm+"/"+invnm+"/Duplicate/"+sub_inv_dt+"/"+newyear;
	}else if(inv_type.equalsIgnoreCase("T")){
		//sub_dtl="Triplicate Digitally Signed Pdf Invoice";
		sub_dtl=cust_nm+"/"+invnm+"/Triplicate/"+sub_inv_dt+"/"+newyear;
	}else if(inv_type.equalsIgnoreCase("DE_signO")){
		sub_dtl=cust_nm+"/"+invnm+"/Original/"+sub_dr_cr_dt+"/"+newyear;
	}else if(inv_type.equalsIgnoreCase("DE_signD")){
		sub_dtl=cust_nm+"/"+invnm+"/Duplicate/"+sub_dr_cr_dt+"/"+newyear;
	}else if(inv_type.equalsIgnoreCase("DE_signT")){
		sub_dtl=cust_nm+"/"+invnm+"/Triplicate/"+sub_dr_cr_dt+"/"+newyear;
	}else if(inv_type.equalsIgnoreCase("Sup_signO")){
		sub_dtl=cust_nm+"/"+invnm+"/Original/"+sub_dr_cr_dt+"/"+newyear;
	}else if(inv_type.equalsIgnoreCase("Sup_signD")){
		sub_dtl=cust_nm+"/"+invnm+"/Duplicate/"+sub_dr_cr_dt+"/"+newyear;
	}else if(inv_type.equalsIgnoreCase("CR_signO")){
		sub_dtl=cust_nm+"/"+invnm+"/Original/"+sub_dr_cr_dt+"/"+newyear;
	}else if(inv_type.equalsIgnoreCase("CR_signD")){
		sub_dtl=cust_nm+"/"+invnm+"/Duplicate/"+sub_dr_cr_dt+"/"+newyear;
	}else if(inv_type.equalsIgnoreCase("CR_signT")){
		sub_dtl=cust_nm+"/"+invnm+"/Triplicate/"+sub_dr_cr_dt+"/"+newyear;
	}
}
String tempEmail = "";
if(email_to.contains("GX-HAZIRA-Commercial@shell.com")){
// 	System.out.println("email_to*before***"+email_to);
	email_to = email_to.replace("GX-HAZIRA-Commercial@shell.com", "GX-HAZIRA-DLNGOPS@shell.com");
}
// System.out.println("inv_type***"+inv_type);
%>

<body <%if(msg.length()>5){
	if(inv_flag.equalsIgnoreCase("SERVICE_") || inv_flag.equalsIgnoreCase("LTCORA_") || inv_flag.equalsIgnoreCase("SALES_")|| inv_flag.equalsIgnoreCase("LOA_") || inv_flag.equalsIgnoreCase("CREDIT_") || inv_flag.equalsIgnoreCase("DEBIT_")){%> 
onLoad="doClose('<%=month%>','<%=year%>','<%=bill_cycle%>','<%=msg%>');" 
<%}else if(inv_flag.equalsIgnoreCase("SUG_")){ %>
onLoad="doClose_sug('<%=month%>','<%=year%>','<%=msg%>','<%=index%>','<%=cargo_no%>','<%=mapping_id%>','<%=selected_customer_id%>','<%=selected_map_id%>');" 
<%}else if(inv_flag.equalsIgnoreCase("OTHER_")){ %>
onLoad="doClose_other('<%=month%>','<%=year%>','<%=msg%>','<%=contract_type%>');" 
<%}else if(inv_flag.equalsIgnoreCase("Advance_")){ %>
onLoad="doClose_adv('<%=msg%>','<%=customer_cd %>','<%=mapping_id %>','<%=fgsa_no%>','<%=cn_rev_no%>','<%=cn_no%>','<%=customer_abbr%>','<%=fgsa_rev_no%>','<%=advance_type%>');" 
<%}else if(inv_flag.equalsIgnoreCase("LATEPAY_")){ %>
onLoad="doClose_latepay('<%=msg%>','<%=month%>','<%=year%>');" 
<%}}%>>
<form method="post" action="../servlet/Frm_Send_Mail" >
<%if(msg.length()>5){ %>
	<br>
	  <font color="blue" size="3"><b>&nbsp;<%=msg %></b></font>
<%} %>
<div class="container" align="top">
  <h3 align="center"><b><u>Send Mail</u></b></h3>
</div>
<br>
<table>
	<tr>
		<td >
			<div class="form-group">
		 	 <label for="pwd">&nbsp;&nbsp;From:</label>
		 	 </div>
		</td>
		<td style="padding-left: 10px">
			<div class="form-group">
			  <input type="text" class="form-control" id="usr" size="100" readonly="readonly" style="background-color: white;" value="<%=from_mail%>">
			</div>
		</td>
	</tr>
	<tr>
		<td >
			<div class="form-group">
		 	 <label for="pwd">&nbsp;&nbsp;To:</label>
		 	 </div>
		</td>
		<td style="padding-left: 10px">
			<div class="form-group">
			   <input  type="text" class="form-control" id="email_to" name="email_to" size="100" readonly="readonly" style="background-color: white;" value="<%=email_to%>">
			</div>
		</td>
	</tr>
	<tr>
		<td >
			<div class="form-group">
		 	 <label for="pwd">&nbsp;&nbsp;CC:</label>
		 	 </div>
		</td>
		<td style="padding-left: 10px">
			<div class="form-group">
			  <input type="text" class="form-control" id="cc" size="100" value="<%=inv_mail_cc%>" readonly="readonly" style="background-color: white;">
			</div>
		</td>
	</tr>
	<tr>
		<td >
			<div class="form-group">
		 	 <label for="pwd">&nbsp;&nbsp;BCC:</label>
		 	 </div>
		</td>
		<td style="padding-left: 10px">
			<div class="form-group">
			  <input type="text" class="form-control" id="bcc" value="<%=inv_mail_bcc %>" size="100" readonly="readonly" style="background-color: white;">
			</div>
		</td>
	</tr>
	<tr>
		<td >
			<div class="form-group">
		 	 <label for="pwd">&nbsp;&nbsp;Subject:</label>
		 	 </div>
		</td>
		<td style="padding-left: 10px">
			<div class="form-group">
			  <input type="text" class="form-control" id="subject" size="100" value="<%=sub_dtl%>" readonly="readonly" style="background-color: white;">
			</div>
		</td>
	</tr>
	<tr>
		<td >
			<div class="form-group">
		 	 <label for="pwd">&nbsp;&nbsp;Attachment:</label>
		 	 </div>
		</td>
		<td style="padding-left: 10px">
			<div class="form-group">
			  <input type="text" class="form-control" id="attachment" title="Click to see invoice" size="100" value="<%=inv_flag%><%=path %>" style="cursor: pointer;" <%if(!pdfsignedpath.equals("")){ %>onclick="view_pdf('<%=pdfsignedpath%>');" <%} %>>
			</div>
		</td>
	</tr>
	<tr>
		<td >
			<div class="form-group">
		 	 <label for="pwd">&nbsp;&nbsp;Email Body:</label>
		 	 </div>
		</td>
		
<%-- Please find enclosed the SEIPL Invoice Reference: <%=sub_dtl %> . You are requested to make the payment within the payment due date<%if(!tempdue_dt.equals("")){ %> <%=tempdue_dt%><%}else{ %> <%=due_dt%><%} %>. --%>

<!-- In case of any query, please contact us at GX-HAZIRA-DLNGOPS@shell.com or ; -->
<%-- <%for(int i=0;i<Email_for_email_body.size();i++){ %> --%>
<%-- <%=i+1 %>.	<%=Email_for_email_body.elementAt(i) %> --%>
<%-- <%} %> --%>

<!-- Thank You, -->
<!-- Shell Energy India Private Limited -->

<!-- This is an auto-generated email from the system, please do not reply to this email.  -->
		<td style="padding-left: 10px">
			<div class="form-group">
			  <textarea class="form-control" rows="12" id="comment" style="overflow: auto;" name="email_body"><%if(inv_flag.equalsIgnoreCase("OTHER_")){ %>Dear Sir,

Please find enclosed the <%=supplier_abbr %> Invoice Reference: <%=sub_dtl %> . You are requested to make the payment within the payment due date<%if(!tempdue_dt.equals("")){ %> <%=tempdue_dt%><%}else{ %> <%=due_dt%><%} %>.

In case of any query, please contact us at GXHAZIRAFIN@shell.com

Thank You,
Shell Energy India Private Limited

This is an auto-generated email from the system, please do not reply to this email. 
<%}else if(cr_dr_sign_flag.equals("SO") || cr_dr_sign_flag.equals("DEO") || cr_dr_sign_flag.equals("SD") || cr_dr_sign_flag.equals("DED") || cr_dr_sign_flag.equals("CO") || cr_dr_sign_flag.equals("CD")){%>Dear Sir / Madam,

Please find enclosed <%if(inv_flag.equalsIgnoreCase("Advance_")) {%>Receipt Voucher<%}else if(cr_dr_sign_flag.equals("SO") || cr_dr_sign_flag.equals("DEO") || cr_dr_sign_flag.equals("SD") || cr_dr_sign_flag.equals("DED") ){ %>Debit Note<%}else if(cr_dr_sign_flag.equals("CO") || cr_dr_sign_flag.equals("CD")){ %>Credit Note<%}else{ %>invoice<%} %> Number <%=newyear %> dated <%=sub_dr_cr_dt %>. 

In case of any query, please contact us at GX-HAZIRA-DLNGOPS@shell.com or ;
<%for(int i=0;i<Email_for_email_body.size();i++){ %>&nbsp;&nbsp;&nbsp;&nbsp;<%=i+1 %>.<%=Email_for_email_body.elementAt(i) %>
<%} %> 
NOTE : This is an auto-generated email from the system, please do not reply to this email.
 
With Best Regards,
<%=supplier_nm %>
<%=supplier_addr %>, <%=supplier_city %> - <%=supplier_pin_code %>
Email : GX-HAZIRA-DLNGOPS@shell.com
Ph: <%=supplier_phone_no %>
<%}else{ %>Dear Sir / Madam,

Please find enclosed <%if(inv_flag.equalsIgnoreCase("Advance_")) {%>Receipt Voucher<%}else if(cr_dr_sign_flag.equals("SO") || cr_dr_sign_flag.equals("DEO") || cr_dr_sign_flag.equals("SD") || cr_dr_sign_flag.equals("DED") ){ %>Debit Note<%}else if(cr_dr_sign_flag.equals("CO") || cr_dr_sign_flag.equals("CD")){ %>Credit Note<%}else{ %>invoice<%} %> Number <%=newyear %> dated <%=sub_inv_dt %>. 
<%if(inv_flag.equals("SALES_") || inv_flag.equals("LTCORA_") || inv_flag.equals("SUG_") || inv_flag.equals("LOA_") || inv_flag.equals("LATEPAY_")){ %>
You are requested to pay on or before the due date <%=tempdue_dt %>. If already paid or no amount is due kindly ignore this message.
<%} %>
In case of any query, please contact us at;
 &nbsp;&nbsp;&nbsp;1. GX-HAZIRA-DLNGOPS@shell.com
<%-- <%for(int i=0;i<Email_for_email_body.size();i++){ %>&nbsp;&nbsp;&nbsp;&nbsp;<%=i+2 %>. <%=Email_for_email_body.elementAt(i) %>
<%} %> --%> 
NOTE : This is an auto-generated email from the system, please do not reply to this email.
 
With Best Regards,
<%=supplier_nm %>
<%=supplier_addr %>, <%=supplier_city %> - <%=supplier_pin_code %>
Email : GX-HAZIRA-DLNGOPS@shell.com
Ph: <%=supplier_phone_no %>
<%-- GST TIN No : <%=supplier_gst_no %> DT. <%=supplier_gst_dt %> --%>
<%-- CST TIN No : <%=supplier_cst_no %> DT. <%=supplier_cst_dt %> --%>
<%-- PAN : <%=supplier_pan_no %> --%>
<%} %>	  
			  </textarea>
			</div>
		</td>
	</tr>
	<tr align="center">
		<td align="center" colspan="2">
			<input type="button" <%=alw_submit %> <%if(!alw_submit.equalsIgnoreCase("")){ %> title = "Sorry...You have no rights."  class="" <%}else{ %> class="subbutton button4" <%} %>  name="sub" value="Send Mail" onclick="confirm_send_mail();" <%if(from_mail.equals("") || email_to.equals("")){ %>  disabled="disabled" title="From or To email id not available.." <%} %>>
		</td>
	</tr>
</table>
<input type="hidden" name="option" value="Send_Mail">
<input type="hidden" name="pdfpath" value="<%=pdfsignedpath%>">
<input type="hidden" name="pdf_nm" value="<%=inv_flag%><%=path%>">
<input type="hidden" name="inv_flag" value="<%=inv_flag%>">
<input type="hidden" name="pdf_fullnm" value="<%=path%>">
<input type="hidden" name="inv_type" value="<%=inv_type%>">
<input type="hidden" name="sub_dtl" value="<%=sub_dtl%>">
<input type="hidden" name="month" value="<%=month%>">
<input type="hidden" name="year" value="<%=year%>">
<input type="hidden" name="bill_cycle" value="<%=bill_cycle%>">
<input type="hidden" name="hlpl_inv_no" value="<%=hlpl_inv_no%>">
<input type="hidden" name="temp_email" value="<%=inv_mail_bcc%>">
<%-- <input type="hidden" name="temp_email" value="<%=email_to%>"> --%>
<input type="hidden" name="email_finance" value="<%=Finance_emailids%>">
<input type="hidden" name="email_cc" value="<%=inv_mail_cc%>">
<input type="hidden" name="contract_type" value="<%=contract_type%>">
<input type="hidden" name="cr_dr_sign_flag" value="<%=cr_dr_sign_flag%>">
<!-- Fro sug -->
<input type="hidden" name="mapping_id" value="<%=mapping_id%>">
<input type="hidden" name="cargo_no" value="<%=cargo_no%>">
<input type="hidden" name="index" value="<%=index%>">
<input type="hidden" name="selected_map_id" value="<%=selected_map_id%>">
<input type="hidden" name="selected_customer_id" value="<%=selected_customer_id%>">
<!-- Fro Advance -->
<input type="hidden" name="acc_hid" value="<%=acc_hid%>">
<input type="hidden" name="advance_type" value="<%=advance_type%>">
<input type="hidden" name="customer_cd" value="<%=customer_cd%>">
<input type="hidden" name="fgsa_no" value="<%=fgsa_no%>">
<input type="hidden" name="fgsa_rev_no" value="<%=fgsa_rev_no%>">
<input type="hidden" name="cn_no" value="<%=cn_no%>">
<input type="hidden" name="cn_rev_no" value="<%=cn_rev_no%>">
<input type="hidden" name="customer_abbr" value="<%=customer_abbr%>">

<%-- <input type="text" name="new_seq_no" value="<%=new_seq_no%>"> --%>
<!--  -->
<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="check_permission" value="<%=check_permission%>">
<input type="hidden" name="authorize_permission" value="<%=authorize_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
<input type="hidden" name="form_id" value="<%=form_cd %>">
<input type="hidden" name="form_nm" value="<%=form_name %>">

<div id="loading" style="visibility: hidden;">
  <img id="loading-image" style="visibility: hidden;" src="../images/indicator.gif" alt="Loading..." width="30" height="30" />
</div>
</form>
</body>
</html>