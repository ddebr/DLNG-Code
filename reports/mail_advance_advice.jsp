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
function doClose(from_dt,msg)
{
		
	window.opener.refreshPageFromChild2(from_dt,msg)
	window.close();
}
</script>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Advance_Advice" id="DAA" scope="request"/>
<%
util.init();
String curr_dt = util.getGen_dt();
String next_dt = util.getDate_tomorrow();

String from_date = request.getParameter("from_date")==null?curr_dt:request.getParameter("from_date");
String mapping_id = request.getParameter("mapping_id")==null?curr_dt:request.getParameter("mapping_id");
String plant_no = request.getParameter("plant_no")==null?curr_dt:request.getParameter("plant_no");
String msg = request.getParameter("msg")==null?"":request.getParameter("msg");

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
DAA.setCallFlag("Send_Mail_Advance_Advice");
DAA.setSelMapId(mapping_id);
DAA.setSysdate(from_date);
DAA.setPlant_no(plant_no);
DAA.setRequest(req);
DAA.init();

String pdfsignedpath=DAA.getPdfsignedpath();
String from_mail=DAA.getFrom_mail();
String email_to=DAA.getEmail_to();
String email_cc=DAA.getEmail_cc();
String cust_nm=DAA.getCust_nm(); 

String supplier_nm=DAA.getSupplier_nm();
String supplier_abbr=DAA.getSupplier_abbr();
String supplier_addr=DAA.getSupplier_addr();
String supplier_city=DAA.getSupplier_city();
String supplier_phone_no=DAA.getSupplier_phone_no();
String supplier_pin_code=DAA.getSupplier_pin_code();

String mail_cc="",mail_bcc="";
String xls_file = DAA.getXls_file();

email_to = DAA.getEmail_to();
mail_cc=DAA.getEmail_cc();
	
String sub_dtl="Advance Advice";

%>



<!--  -->

<body <%if(msg.length()>5){%>
	onLoad="doClose('<%=from_date %>','<%=msg %>');" 
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

Please find enclosed Advance Advice Report.


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
<input type="hidden" name="option" value="Advance_Advice_Rpt_Send_Mail">
<input type="hidden" name="pdfpath" value="<%=pdfsignedpath%>">
<input type="hidden" name="pdf_nm" value="<%=xls_file%>">
<input type="hidden" name="sub_dtl" value="<%=sub_dtl%>">
<input type="hidden" name="hlpl_inv_no" value="<%=xls_file%>">
<input type="hidden" name="email_cc" value="<%=email_cc%>">
<%-- <input type="hidden" name="contract_type" value="<%=cont_type%>"> --%>
<!-- Fro sug -->
<!-- Fro Advance -->
<%-- <input type="hidden" name="customer_cd" value="<%=customer_cd%>"> --%>
<%-- <input type="hidden" name="fgsa_no" value="<%=fgsa_no%>"> --%>
<input type="hidden" name="gas_date" value="<%=from_date%>">


<!--  -->

<div id="loading" style="visibility: hidden;">
  <img id="loading-image" style="visibility: hidden;" src="../images/indicator.gif" alt="Loading..." width="30" height="30" />
</div>
</form>
</body>
</html>