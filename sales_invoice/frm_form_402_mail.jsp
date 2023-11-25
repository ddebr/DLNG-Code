
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

<title> DLNG | Form 402 Mail </title>

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
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	window.opener.refreshPageFromChild(msg,month,year,write_permission,delete_permission,check_permission,approve_permission,authorize_permission,print_permission);
	window.close();
}
</script>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.sales_invoice.DataBean_form_402_mail" id="dsc" scope="request"/>
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
//System.out.println("--inv_type--"+inv_type);
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
String form_cd = request.getParameter("form_cd")==null?"":request.getParameter("form_cd");
String form_name = request.getParameter("form_name")==null?"":request.getParameter("form_name");
String invNo = request.getParameter("invNo")==null?"":request.getParameter("invNo");
String truck_no = request.getParameter("truck_no")==null?"":request.getParameter("truck_no");
HttpServletRequest req=request;

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
dsc.setInvNo(invNo);
dsc.setTruck_no(truck_no);
dsc.setCallFlag("fetch_excel");
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

String xls_file = dsc.getXls_file();
String mail_bcc=dsc.getBcc_mail();

if(inv_type.equals("Form402")){
	inv_type="F";
}
	
String sub_dtl="Form-402";
String newyear="";
String invnm="";

/* below email id's hardcoded as per customer req. on 2020-12-19 */
// Finance_emailids = "GXHAZIRAShiftsuperintendent@shell.com,Ashish.N.Shah@Shell.com,Krunal.Pastagia@Shell.com,GX-HAZIRA-DLNGOPS@shell.com,Jagrut.Shah@shell.com,Shreeram.Deshpande@shell.com,girinthakore@hotmail.com,GXHAZIRAPFO@shell.com,Jayshri.Ghadge@shell.com";

%>

<body <%if(msg.length()>5){
	if(inv_flag.equalsIgnoreCase("LTCORA_") || inv_flag.equalsIgnoreCase("SALES_")|| inv_flag.equalsIgnoreCase("LOA_") || inv_flag.equalsIgnoreCase("CREDIT_") || inv_flag.equalsIgnoreCase("DEBIT_")){%> 
onLoad="doClose('<%=month%>','<%=year%>','<%=bill_cycle%>','<%=msg%>');" 
<%}} %>>
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
<%-- 			   <input type="text" class="form-control" id="email_to" name="email_to" size="100" readonly="readonly" style="background-color: white;" value="<%=Finance_emailids%>"> --%>
			   <input type="text" class="form-control" id="email_to" name="email_to" size="100" readonly="readonly" style="background-color: white;" value="<%=email_to%>">
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
			  <input type="text" class="form-control" id="cc" size="100" value="<%=email_cc%>" readonly="readonly" style="background-color: white;">
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
			  <input type="text" class="form-control" id="bcc" name="mail_bcc" value="<%=mail_bcc %>" size="100" readonly="readonly" style="background-color: white;">
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
			  <input type="text" class="form-control" id="attachment" title="Click to see invoice" size="100" name="attachment"
			  value="<%=xls_file %>" style="cursor: pointer;<%if(xls_file.contains("!!")){%>
					color: red;
				<%} %>"
			   <%if(!pdfsignedpath.equals("")){ %>onclick="view_pdf('<%=pdfsignedpath%>');" <%} %>>
			</div>
		</td>
	</tr>
	<tr>
		<td >
			<div class="form-group">
		 	 <label for="pwd">&nbsp;&nbsp;Email Body:</label>
		 	 </div>
		</td>
		
		<td style="padding-left: 10px">
			<div class="form-group">
			  <textarea class="form-control" rows="12" id="comment" style="overflow: auto;" name="email_body">
Dear Sir / Madam,			  

Please find enclosed Form-402.


NOTE : This is an auto-generated email from the system, please do not reply to this email.
 
With Best Regards,
<%=supplier_nm %>
<%=supplier_addr %>, <%=supplier_city %> - <%=supplier_pin_code %>
Email : GX-HAZIRA-DLNGOPS@shell.com
Ph: <%=supplier_phone_no %>
	  
			  </textarea>
			</div>
		</td>
	</tr>
	<tr align="center">
		<td align="center" colspan="2">
			<input type="button" <%=alw_submit %> <%if(!alw_submit.equalsIgnoreCase("")){ %> title = "Sorry...You have no rights."  class="" <%}else{ %> class="subbutton button4" <%} %> name="sub" value="Send Mail" onclick="confirm_send_mail();" <%if(from_mail.equals("") || email_to.equals("")){ %>  disabled="disabled" title="From or To email id not available.." <%} %>>
		</td>
	</tr>
</table>
<input type="hidden" name="option" value="Form_402_Send_Mail">
<input type="hidden" name="pdfpath" value="<%=pdfsignedpath%>">
<input type="hidden" name="pdf_nm" value="<%=inv_flag%><%=path%>">
<input type="hidden" name="inv_flag" value="<%=inv_flag%>">
<input type="hidden" name="pdf_fullnm" value="<%=path%>">
<input type="hidden" name="inv_type" value="<%=inv_type%>">
<input type="hidden" name="sub_dtl" value="<%=sub_dtl%>">
<input type="hidden" name="month" value="<%=month%>">
<input type="hidden" name="year" value="<%=year%>">
<input type="hidden" name="bill_cycle" value="<%=bill_cycle%>">
<input type="hidden" name="hlpl_inv_no" value="<%=xls_file%>">
<input type="hidden" name="temp_email" value="<%=temp_email%>">
<input type="hidden" name="email_finance" value="<%=email_to%>">
<input type="hidden" name="email_cc" value="<%=email_cc%>">
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