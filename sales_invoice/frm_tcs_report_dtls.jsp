<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<html>
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
<link rel="stylesheet" href="../responsive/css/style.css" >
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="../css/pt_sans.css">

<script type="text/javascript" src="../js/date-picker.js"></script>
<!-- <script type="text/javascript" src="../js/aris.js"></script> -->
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.sales_invoice.DataBean_Prepare_TLU_InvoiceV2" id="dta" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="page"/>
<%
String cust_cd=request.getParameter("cust_cd")==null?"":request.getParameter("cust_cd");
String fin_year=request.getParameter("fin_year")==null?"":request.getParameter("fin_year");
String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
String check_permission = request.getParameter("check_permission")==null?"N":request.getParameter("check_permission");
String authorize_permission = request.getParameter("authorize_permission")==null?"N":request.getParameter("authorize_permission");
String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
		util.init();
		String sysdate = util.getGen_dt();
		String curr_year = util.getGet_yr();

dta.setCustomer_cd(cust_cd);
dta.setFin_year(fin_year);
dta.setCallFlag("TCS_INVOICE_DETAILS");
dta.init();
 Vector inv_seq_no=dta.getInv_seq_no();
Vector Tcs_invdt=dta.getTcs_invdt();
Vector Tcs_fgsano=dta.getTcs_fgsano();
Vector Tcs_fgsarevno=dta.getTcs_fgsarevno();
Vector Tcs_snno=dta.getTcs_snno();
Vector Tcs_snrevno=dta.getTcs_snrevno();
String Total_tcs_amt=dta.getTotal_tcs_amt();
Vector tcs_net_amt=dta.getTcs_net_amt();
String cust_nm=dta.getCust_nm();
String cust_nm_rpt=dta.getCust_nm_rpt();
Vector flag_invoice=dta.getFlag_invoice();
Vector proj_typ = dta.getProj_typ();
Vector tot_inv_amt = dta.getTot_inv_amt();

Vector dlng_inv_seq_no= dta.getDlng_inv_seq_no();
Vector dlng_tcs_invdt=dta.getDlng_tcs_invdt();
Vector dlng_tcs_net_amt=dta.getDlng_tcs_net_amt();
Vector dlng_tcs_fgsano=dta.getDlng_tcs_fgsano();
Vector dlng_tcs_fgsarevno=dta.getDlng_tcs_fgsarevno();
Vector dlng_tcs_snno=dta.getDlng_tcs_snno();
Vector dlng_tcs_snrevno=dta.getDlng_tcs_snrevno(); 
Vector dlng_flag_invoice=dta.getDlng_flag_invoice();

String dlng_total_tcs_amt = dta.getDlng_total_tcs_amt();
String total_tcs_amt = dta.getTotal_tcs_amt();
String final_total_amt=dta.getFinal_total_amt();
System.out.println("proj_typ***"+proj_typ.size());
%>

<div class="tab-content">
	<div class="tab-pane active" id="hcasreport">
		<div class="box mb-0">
			<form >
			
			<input type="hidden" name="write_permission" value="<%=write_permission%>">
			<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
			<input type="hidden" name="print_permission" value="<%=print_permission%>">
			<input type="hidden" name="check_permission" value="<%=check_permission%>">
			<input type="hidden" name="authorize_permission" value="<%=authorize_permission%>">
			<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
			<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
			
				<div class="box-header with-border" style="background-color:#ffe57f;">
					<div class="form-group col-md-12" style="background-color:white;">
						<h2 class="text-center"><label for="">Invoices List</label></h2>
					</div>
					
					<div class="form-group col-md-6">
						<label for="">Customer Name : <%=cust_nm %>&nbsp;(<%=cust_nm_rpt %>)</label>	
						
				     		 
				     	
					</div>
					
					<div class="form-group col-md-6">
						<label for="">Financial Year :  <%=fin_year %></label>	
					</div>
				</div>
				<div class="box-body table-responsive no-padding">
					<table id="example" class="table table-bordered">
						<thead>
							<tr class="info text-center">
								<th colspan="">Sr No.</th>
					    		<th colspan="">Invoice No.</th>
					    		<th colspan="">Agreement No</th>
					    		<th colspan="">Agreement Rev No.</th>
					    		<th colspan="">Contract No.</th>
					    		<th colspan="">Contract Rev No.</th>
					    		<th colspan="">Invoice Date</th>
					    		<th colspan="">Invoice Amount</th>
							</tr>
						</thead>
						<tbody>
					<%
					for(int i=0;i<dlng_inv_seq_no.size();i++){%>
					<%if(i==0) {%>
					<tr>
						<th class="text-center" colspan="8" style="background-color: #9ff6b5">DLNG Invoice(s) List</th>
					</tr>
					<%} %>
					 <tr>
			    		<td colspan="1" align="center"><%=i+1 %></td>
			    		<td colspan="1" align="center"><%=dlng_inv_seq_no.elementAt(i) %></td>
			    		<td colspan="1" align="center"><%=dlng_tcs_fgsano.elementAt(i) %></td>
			    		<td colspan="1" align="center"><%=dlng_tcs_fgsarevno.elementAt(i) %></td>
			    		<td colspan="1" align="center"><%=dlng_tcs_snno.elementAt(i) %></td>
			    		<td colspan="1" align="center"><%=dlng_tcs_snrevno.elementAt(i) %></td>
			    		<td colspan="1" align="center"><%=dlng_tcs_invdt.elementAt(i) %></td>
			    		<td colspan="1" align="right"><%if(dlng_flag_invoice.elementAt(i).equals("C")){%>
			    		<font color="blue"><%=dlng_tcs_net_amt.elementAt(i) %></font><%}else{ %><%=dlng_tcs_net_amt.elementAt(i) %><%} %></td>
					</tr>
				 <%}%>
				 <tr class="info">
					 	<td colspan="7" align="right">DLNG Total Invoice Amount :</td> 
						<td colspan="1" align="right"><%=dlng_total_tcs_amt%></td> 
				</tr>
				 
				 <%for(int i=0;i<inv_seq_no.size();i++){%>
					<%if(i==0) {%>
					<tr>
						<th class="text-center" colspan="8" style="background-color: #9ff6b5">RLNG Invoice(s) List</th>
					</tr>
					<%} %>
					 <tr>
			    		<td colspan="1" align="center"><%=i+1 %></td>
			    		<td colspan="1" align="center"><%=inv_seq_no.elementAt(i) %></td>
			    		<td colspan="1" align="center"><%=Tcs_fgsano.elementAt(i) %></td>
			    		<td colspan="1" align="center"><%=Tcs_fgsarevno.elementAt(i) %></td>
			    		<td colspan="1" align="center"><%=Tcs_snno.elementAt(i) %></td>
			    		<td colspan="1" align="center"><%=Tcs_snrevno.elementAt(i) %></td>
			    		<td colspan="1" align="center"><%=Tcs_invdt.elementAt(i) %></td>
			    		<td colspan="1" align="right"><%if(flag_invoice.elementAt(i).equals("C")){%>
			    		<font color="blue"><%=tcs_net_amt.elementAt(i) %></font><%}else{ %><%=tcs_net_amt.elementAt(i) %><%} %></td>
					</tr>
					 <%} %>
					<tr class="info">
					 	<td colspan="7" align="right">RLNG Total Invoice Amount :</td> 
						<td colspan="1" align="right"><%=total_tcs_amt%></td> 
					</tr>
					
					<tr style="background-color:#ffe57f;">
					 	<td colspan="7" align="right">Total (DLNG + RLNG) Amount :</td> 
						<td colspan="1" align="right"><%=final_total_amt%></td> 
					</tr>
						
						</tbody>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>					







<%-- <body  >
<%@ include file="../home/header.jsp"%>

<form method="post"  action="../servlet/Frm_Sales_Invoice">


<div id="col-three"><br>
   <table width="100%" align="center">
	 <tr class="title1">
    		<td colspan="3"><div align="center"><font size="3" style="font-weight: bolder;"><b>Invoices List</b></font></div></td>
	</tr>
	<tr class="content1">
    		<td colspan="1">&nbsp;<b>Customer Name:</b> <%=cust_nm %> (<%=cust_nm_rpt %>)
    		&nbsp;</td>
    		<td colspan="2">&nbsp;<b>Financial Year:</b> <%=fin_year %></td>
	</tr>
	</table>
	 <table width="100%" align="center">
	<tr class="title2">
    		<td colspan="">Sr No.</td>
    		<td colspan="">Invoice No.</td>
    		<td colspan="">Agreement No</td>
    		<td colspan="">Agreement Rev No.</td>
    		<td colspan="">Contract No.</td>
    		<td colspan="">Contract Rev No.</td>
    		<td colspan="">Invoice Date</td>
    		<td colspan="">Invoice Amount</td>
	</tr>
	<%for(int i=0;i<inv_seq_no.size();i++){ %>
		 <tr class="content1">
    		<td colspan="1" align="center"><%=i+1 %></td>
    		<td colspan="1" align="center"><%=inv_seq_no.elementAt(i) %></td>
    		<td colspan="1" align="center"><%=Tcs_fgsano.elementAt(i) %></td>
    		<td colspan="1" align="center"><%=Tcs_fgsarevno.elementAt(i) %></td>
    		<td colspan="1" align="center"><%=Tcs_snno.elementAt(i) %></td>
    		<td colspan="1" align="center"><%=Tcs_snrevno.elementAt(i) %></td>
    		<td colspan="1" align="center"><%=Tcs_invdt.elementAt(i) %></td>
    		<td colspan="1" align="right"><%if(flag_invoice.elementAt(i).equals("C")){%>
    		<font color="blue"><%=tcs_net_amt.elementAt(i) %></font><%}else{ %><%=tcs_net_amt.elementAt(i) %><%} %></td>
		</tr>
	<%} %>
	 <tr class="title2">
	 	<td colspan="7" align="right">Total amount :</td>
	 	<td colspan="1" align="right"><%=Total_tcs_amt %></td>
	 </tr>
  </table>
</div>
	
<div id="col-three">
	

</div>

</form>
</body> --%>
</html>