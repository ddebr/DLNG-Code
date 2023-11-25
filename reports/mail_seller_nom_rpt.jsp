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
function doClose(gas_date,msg)
{
		
	window.opener.refreshPageFromChild2(gas_date,msg)
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
String curr_dt = util.getGen_dt();
String next_dt = util.getDate_tomorrow();
String tempcompnm = (String)session.getAttribute("tempcompnm")==null?"":(String)session.getAttribute("tempcompnm"); //RG20190327
String gas_date = request.getParameter("gas_date")==null?next_dt:request.getParameter("gas_date");		
String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
String cont_type = request.getParameter("cont_type")==null?"":request.getParameter("cont_type");
String from_cntct = request.getParameter("from_cntct")==null?next_dt:request.getParameter("from_cntct");
String to_cntct = request.getParameter("to_cntct")==null?curr_dt:request.getParameter("to_cntct");
String plant_seq_no = request.getParameter("plant_seq_no")==null?"0":request.getParameter("plant_seq_no");
String plant_nm = request.getParameter("plant_nm")==null?"":request.getParameter("plant_nm");
String remarks = request.getParameter("remarks")==null?"":request.getParameter("remarks");
String sn_ref_no = request.getParameter("sn_ref_no")==null?"":request.getParameter("sn_ref_no");
String mapping_id = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");//ADDDED FOR LTCORA AN CN
String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
String cust_abbr = request.getParameter("cust_abbr")==null?"":request.getParameter("cust_abbr");

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

HttpServletRequest req=request;

dsc.setRequest(req);
dsc.setGas_date(gas_date);
dsc.setFgsa_no(fgsa_no);
dsc.setCust_abbr(cust_abbr);
dsc.setPlant_nm(plant_nm);
dsc.setSn_ref_no(sn_ref_no);
dsc.setCont_type(cont_type);
dsc.setCustomer_cd(customer_cd);
dsc.setCustomer_plant_seq_no(plant_seq_no);
dsc.setTo_cntct(to_cntct);

dsc.setCallFlag("fetch_seller_pdf");

dsc.init();

String pdfsignedpath=dsc.getPdfsignedpath();
String from_mail=dsc.getFrom_mail();
String email_to=dsc.getEmail_to();
String email_to_otherinv=dsc.getEmail_to_otherinv();
String sub_inv_dt=dsc.getSub_inv_dt();
String email_cc=dsc.getEmail_cc();
String cust_nm=dsc.getCust_nm(); 

String supplier_nm=dsc.getSupplier_nm();
String supplier_abbr=dsc.getSupplier_abbr();
String supplier_addr=dsc.getSupplier_addr();
String supplier_city=dsc.getSupplier_city();
String supplier_phone_no=dsc.getSupplier_phone_no();
String supplier_pin_code=dsc.getSupplier_pin_code();

String mail_cc="",mail_bcc="";
String xls_file = dsc.getXls_file();

email_to=dsc.getEmail_to();
mail_cc=dsc.getEmail_cc();
	
String sub_dtl="Seller's Daily Nomination to Customer ("+cust_abbr+") - "+gas_date;

%>

<body <%if(msg.length()>5){%>
	onLoad="doClose('<%=gas_date %>','<%=msg %>');" 
<%} %>>
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
			  <input type="text" class="form-control" id="cc" size="100" value="<%=mail_cc%>" readonly="readonly" style="background-color: white;">
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
			  <input type="text" class="form-control" id="bcc" value="<%=mail_bcc %>" size="100" readonly="readonly" style="background-color: white;">
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

Please find enclosed Daily Seller Nomination Report.


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
			<input type="button" <%=alw_submit %> <%if(!alw_submit.equalsIgnoreCase("")){ %> title = "Sorry...You have no rights."  class="" <%}else{ %> class="subbutton button4" <%} %> name="sub" value="Send Mail" onclick="confirm_send_mail();" <%if(from_mail.equals("")){ %>   disabled="disabled" title="From or To email id not available.." <%} %>>
		</td>
	</tr>
</table>
<input type="hidden" name="option" value="Seller_Nom_Rpt_Send_Mail">
<input type="hidden" name="pdfpath" value="<%=pdfsignedpath%>">
<input type="hidden" name="pdf_nm" value="<%=xls_file%>">
<input type="hidden" name="sub_dtl" value="<%=sub_dtl%>">
<input type="hidden" name="hlpl_inv_no" value="<%=xls_file%>">
<input type="hidden" name="email_cc" value="<%=email_cc%>">
<input type="hidden" name="contract_type" value="<%=cont_type%>">
<!-- Fro sug -->
<!-- Fro Advance -->
<input type="hidden" name="customer_cd" value="<%=customer_cd%>">
<input type="hidden" name="fgsa_no" value="<%=fgsa_no%>">
<input type="hidden" name="gas_date" value="<%=gas_date%>">


<!--  -->

<div id="loading" style="visibility: hidden;">
  <img id="loading-image" style="visibility: hidden;" src="../images/indicator.gif" alt="Loading..." width="30" height="30" />
</div>
</form>
</body>
</html>