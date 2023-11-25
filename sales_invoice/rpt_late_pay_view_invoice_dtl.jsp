<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Late Payment Invoice Details</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!-- <script language="JavaScript" src="../js/date-picker.js"></script>
<script language="JavaScript" src="../js/util2.js"></script>
<script language="JavaScript" src="../js/util1.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="JavaScript" src="../js/fms.js"></script> -->
<style>
.decor tr td {
border-left: 2px solid black;
</style>
</head>

<jsp:useBean class="com.seipl.hazira.dlng.sales_invoice.DataBean_LatePayment" id="latePay" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="page"/>
<%
util.init();
String tempcompnm = (String)session.getAttribute("tempcompnm")==null?"":(String)session.getAttribute("tempcompnm"); //RG20190327
String late_pay_inv_dt=request.getParameter("late_pay_inv_dt")==null?"":request.getParameter("late_pay_inv_dt");
if(late_pay_inv_dt==null || late_pay_inv_dt.equals(""))
	late_pay_inv_dt = request.getParameter("invoice_dt")==null?util.getGen_dt():request.getParameter("invoice_dt");
String newDate = util.getGen_dt();
String late_pay_hlpl_inv_seq_no = request.getParameter("late_pay_hlpl_inv_seq_no")==null?"":request.getParameter("late_pay_hlpl_inv_seq_no");
String late_pay_customer_cd = request.getParameter("late_pay_customer_cd")==null?"":request.getParameter("late_pay_customer_cd");
String late_pay_contract_type = request.getParameter("late_pay_contract_type")==null?"":request.getParameter("late_pay_contract_type");
String late_pay_customer_abbr = request.getParameter("late_pay_customer_abbr")==null?"":request.getParameter("late_pay_customer_abbr");
String refreshFlag = request.getParameter("refreshFlag")==null?"N":request.getParameter("refreshFlag");
String late_pay_financial_year = request.getParameter("late_pay_financial_year")==null?"":request.getParameter("late_pay_financial_year");
String late_pay_new_inv_seq_no = request.getParameter("late_pay_new_inv_seq_no")==null?"":request.getParameter("late_pay_new_inv_seq_no");
String month = request.getParameter("month")==null?"0":request.getParameter("month");
String year = request.getParameter("year")==null?"0":request.getParameter("year");
String activity = request.getParameter("activity")==null?"V":request.getParameter("activity");
String modify_flag = request.getParameter("modify_flag")==null?"N":request.getParameter("modify_flag");
String form_id = request.getParameter("form_id")==null?"0":request.getParameter("form_id");
String form_nm = request.getParameter("form_nm")==null?"0":request.getParameter("form_nm");
String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
String int_rate = request.getParameter("int_rate")==null?"":request.getParameter("int_rate");
String contactPerson = request.getParameter("contactPerson")==null?"0":request.getParameter("contactPerson");
String invoice_date = request.getParameter("invoice_date")==null?newDate:request.getParameter("invoice_date");
//For Modify Invoice
String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200506
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200506
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200506
String modUrl = session.getAttribute("modUrl")==null?"":session.getAttribute("modUrl").toString();//HA20210814
String write_permission = (String)session.getAttribute("write_permission")==null?"":(String)session.getAttribute("write_permission");
String delete_permission = (String)session.getAttribute("delete_permission")==null?"":(String)session.getAttribute("delete_permission");
String print_permission = (String)session.getAttribute("print_permission")==null?"":(String)session.getAttribute("print_permission");
String approve_permission = (String)session.getAttribute("approve_permission")==null?"":(String)session.getAttribute("approve_permission");
String audit_permission = (String)session.getAttribute("audit_permission")==null?"":(String)session.getAttribute("audit_permission");
String check_permission =(String)session.getAttribute("check_permission")==null?"":(String)session.getAttribute("check_permission");


String new_inv_seq_no = request.getParameter("new_inv_seq_no")==null?"":request.getParameter("new_inv_seq_no");
String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"":request.getParameter("hlpl_inv_seq_no");
String invoice_dt = request.getParameter("invoice_dt")==null?"":request.getParameter("invoice_dt");
String invoice_title = request.getParameter("invoice_title")==null?"ORIGINAL":request.getParameter("invoice_title");
String flag = request.getParameter("flag")==null?"View":request.getParameter("flag");
String check_flag = request.getParameter("check_flag")==null?"N":request.getParameter("check_flag");
String aprv_flg = request.getParameter("aprv_flg")==null?"N":request.getParameter("aprv_flg");
String act_cont_type = request.getParameter("act_cont_type")==null?"":request.getParameter("act_cont_type");

latePay.setCallFlag("Prepare_Late_Payment_Invoice");
latePay.setpHlpl_inv_seq_no(late_pay_hlpl_inv_seq_no);
latePay.setpContract_type(late_pay_contract_type);
latePay.setpCustomer_cd(late_pay_customer_cd);
latePay.setpFinancial_year(late_pay_financial_year);
latePay.setpInvoice_date(late_pay_inv_dt);
latePay.setpNew_inv_seq_no(late_pay_new_inv_seq_no);
latePay.setnInvoice_date(invoice_date);
latePay.setActivity(activity);
latePay.setpCal_percentage(int_rate);
latePay.setRefresh_flag(refreshFlag);
latePay.setmNew_inv_seq_no(new_inv_seq_no);
latePay.setmHlpl_inv_seq_no(hlpl_inv_seq_no);
latePay.setmInvoice_dt(invoice_dt);
latePay.setAct_cont_type(act_cont_type);

if(msg.length()<5)
	latePay.init();

String pCustomer_abbr = latePay.getpCustomer_abbr();
String pContract_no = latePay.getpContract_no();
String pPlant_name = latePay.getpPlant_nm();
Vector pContact_person_cd = latePay.getpContact_person_cd();
Vector pContact_person_nm = latePay.getpContact_person_nm();
String pContract_start_dt = latePay.getpContract_start_dt();
String pContract_end_dt = latePay.getpContract_end_dt();
String pDue_date = latePay.getpDue_date();
String pPay_recv_date = latePay.getpPay_recv_date();
String pPay_recv_amt = latePay.getpPay_recv_amt();
String pNet_amt_inr = latePay.getpNet_amt_inr();
String pNo_days = latePay.getpNo_days();
String pCal_percentage = latePay.getpCal_percentage();
String nTax_Structure_dtl = latePay.getnTax_Structure_dtl();
String nTax_struct_cd = latePay.getnTax_struct_cd();
String nNet_amt = latePay.getnNet_amt();
Vector nTax_cd = latePay.getnTax_cd();
Vector nTax_abbr = latePay.getnTax_abbr();
Vector nTax_factor = latePay.getnTax_factor();
String nTotal_amount = latePay.getnTotal_amount();
String nTotal_tax_amount = latePay.getnTotal_tax_amount();
Map nTax_component_amt = latePay.getnTax_component_amt();
String nNew_inv_seq_no = latePay.getnNew_inv_seq_no();
String nInvoice_seq_no_actual = latePay.getnInvoice_seq_no_actual();
String nCust_inv_seq_no = latePay.getnCust_inv_seq_no();
String nRemark_1 = latePay.getnRemark_1();
contactPerson = latePay.getnContact_person_cd();
invoice_date = latePay.getmInvoice_dt();

String contact_Person_Name_And_Designation = latePay.getContact_Person_Name_And_Designation();
String contact_Customer_Name = latePay.getContact_Customer_Name();
String contact_Customer_Person_Address = latePay.getContact_Customer_Person_Address();
String contact_Customer_Person_City = latePay.getContact_Customer_Person_City();
String contact_Customer_Person_Pin = latePay.getContact_Customer_Person_Pin();
String contact_Suppl_Name = latePay.getContact_Suppl_Name();
String contact_Suppl_Person_Address = latePay.getContact_Suppl_Person_Address();
String contact_Suppl_Person_City = latePay.getContact_Suppl_Person_City();
String contact_Suppl_Person_Pin = latePay.getContact_Suppl_Person_Pin();
String contact_Suppl_GST_NO = latePay.getContact_Suppl_GST_NO();
String contact_Suppl_CST_NO = latePay.getContact_Suppl_CST_NO();
String contact_Suppl_GST_DT = latePay.getContact_Suppl_GST_DT();
String contact_Suppl_CST_DT = latePay.getContact_Suppl_CST_DT();
String contact_Suppl_PAN_NO = latePay.getContact_Suppl_PAN_NO();
String contact_Customer_GVAT_NO = latePay.getContact_Customer_GVAT_NO();
String contact_Customer_GVAT_DT = latePay.getContact_Customer_GVAT_DT();
String contact_Suppl_PAN_DT = latePay.getContact_Suppl_PAN_DT();
String contact_Customer_GST_NO = latePay.getContact_Customer_GST_NO();
String contact_Customer_CST_NO = latePay.getContact_Customer_CST_NO();
String contact_Customer_GST_DT = latePay.getContact_Customer_GST_DT();
String contact_Customer_CST_DT = latePay.getContact_Customer_CST_DT();
String Rule_remark = latePay.getRule_remark();
String sac_code = latePay.getSac_code();
String sac_name = latePay.getSac_name();
String service_desc = latePay.getService_desc();
String contact_Suppl_GSTIN_NO = latePay.getContact_Suppl_GSTIN_NO(); 
String contact_Suppl_GSTIN_DT = latePay.getContact_Suppl_GSTIN_DT(); 
String contact_Customer_Plant_State = latePay.getContact_Customer_Plant_State();
String contact_Customer_State_Code = latePay.getContact_Customer_State_Code();
String contact_Suppl_State_Code = latePay.getContact_Suppl_State_Code();
String contact_Suppl_State = latePay.getContact_Suppl_State();
Vector vSTAT_CD = latePay.getvSTAT_CD();
Vector vSTAT_NM = latePay.getvSTAT_NM();
Vector vSTAT_NO = latePay.getvSTAT_NO();
Vector vSTAT_EFF_DT = latePay.getvSTAT_EFF_DT();
String customer_Invoice_SN_Dt = latePay.getCustomer_Invoice_SN_Dt();
String customer_Invoice_FGSA_Dt = latePay.getCustomer_Invoice_FGSA_Dt();
String sn_no = latePay.getpSn_no();
String sn_ref_no = latePay.getpSn_ref_no();
String fgsa_no = latePay.getpFgsa_no();
String fgsa_rev_no = latePay.getpFgsa_rev_no();
String sn_rev_no = latePay.getpSn_rev_no();
String customer_Invoice_DT = latePay.getCustomer_Invoice_DT();

Vector pSr_no = latePay.getpSr_No();
Vector pItem = latePay.getpItem();
Vector pCurrency = latePay.getpCurrency();
Vector pAmount = latePay.getpAmount();
Vector pQuantity = latePay.getpQuantity();
Vector pRate = latePay.getpRate();
Vector pData = latePay.getpData();
String ndiff_days=latePay.getNdiff_days();
String disc_days="";
if(!ndiff_days.equals("")){
	disc_days=""+(Integer.parseInt(pNo_days)-Integer.parseInt(ndiff_days));
}
System.out.println("Msg.."+disc_days);
//RG20200402
if(!nRemark_1.equals("")){
	nRemark_1=nRemark_1.replace("%$%$%$%$","<br />");
}
//RG
%>

<body <%if(msg.length()>5){%> onload="doClose('<%=msg%>','<%=month%>','<%=year%>','<%=write_permission%>','<%=delete_permission%>','<%=check_permission%>','<%=approve_permission%>','<%//=authorize_permission%>','<%=print_permission%>');" <%}%> >
<form method="post" action="../servlet/Frm_Sales_InvoiceV2">
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				<font size="2" face="Arial"> <b>
				<%if(late_pay_contract_type.equals("C") || late_pay_contract_type.equals("B")) { %>
<%-- 					<%=invoice_title%> FOR RECIPIENT --%>
				<% } else { %>
					<%=invoice_title%>
				<% } %>
				</b><br>  </font>
				<font size="4" face="Arial"> <b><%=contact_Suppl_Name%></b> </font>
				<br>
				<font size="2" face="Arial"><b><%if(late_pay_contract_type.equals("S") || late_pay_contract_type.equals("L")){ %>DEBIT NOTE<%}else{ %>TAX INVOICE <%} %></b></font>
			</div>
		</td>
	</tr>
<%if(late_pay_contract_type.equals("C") || late_pay_contract_type.equals("B")) { %>
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				<font size="1px" face="Arial"><b><%=Rule_remark%></b></font>
			</div>
   		 </td>
	</tr>
<% } %>	
	<tr valign="middle">
		<td colspan="7">
			<div align="center"> &nbsp; </div>
    	</td>
	</tr>
		<tr valign="middle">
			<td colspan="7">
				<div align="center">
					<font size="1px" face="Arial">
						<%if(late_pay_contract_type.equalsIgnoreCase("S") || act_cont_type.equalsIgnoreCase("S")){ %>
						<tr valign="middle"><td colspan="7"><div align="center"><font size="1px" face="Arial">
						In respect of Supply Notice (SN-<%if(sn_ref_no.trim().equals("")){%><%=sn_no%><%}else{%><%=sn_ref_no%><%}%>) executed on <%=customer_Invoice_SN_Dt%> pursuant to Framework Gas Sales Agreement executed on <%=customer_Invoice_FGSA_Dt%>
						<br>
						between <%=contact_Suppl_Name%> and <%=contact_Customer_Name%>
						</font></div></td></tr>
						<%}
						else if(late_pay_contract_type.trim().equalsIgnoreCase("L") || act_cont_type.equalsIgnoreCase("L"))
						{ %>
						<tr valign="middle"><td colspan="7"><div align="center"><font size="1px" face="Arial">
						In respect of Letter of Agreement (LOA-<%if(sn_ref_no.trim().equals("")){%><%=sn_no%><%}else{%><%=sn_ref_no%><%}%>) executed on <%=customer_Invoice_SN_Dt%> pursuant to Tender executed on <%=customer_Invoice_FGSA_Dt%>
						<br>
						between <%=contact_Suppl_Name%> and <%=contact_Customer_Name%>
						</font></div></td></tr>
						<%}
						else if(late_pay_contract_type.trim().equalsIgnoreCase("R"))
						{ %>
						<tr valign="middle"><td colspan="7"><div align="center"><font size="1px" face="Arial">
						In respect of Regassification Agreement executed on <%=customer_Invoice_FGSA_Dt%> and subsequent side letters 
						<br>
						between <%=contact_Customer_Name%> and <%=contact_Suppl_Name%>
						</font></div></td></tr>
						<%}
						else if(late_pay_contract_type.trim().equalsIgnoreCase("C") || late_pay_contract_type.equals("B"))
						{ %>
						<tr valign="middle"><td colspan="7"><div align="center"><font size="1px" face="Arial">
						In respect of LTCORA executed on <%=customer_Invoice_FGSA_Dt%> 
						<%if(Double.parseDouble(fgsa_no)<9999) { %>
						&amp; CN-<%=fgsa_no%> executed on <%=customer_Invoice_SN_Dt%>
						<%} %>
						<br>
						between <%=contact_Customer_Name%> and <%=contact_Suppl_Name%>
						</font></div>    </td></tr>
						<%}%>
					</font>
				</div>
	    	</td>
		</tr>
	<tr valign="middle">
		<td colspan="7">
			<div align="center"> &nbsp; </div>
    	</td>
	</tr>
	<tr valign="top">
		<td colspan="3" width="50%">
			<div align="left">
				<font size="1.5px" face="Arial">
					Registered Office:<br>
					<%=contact_Suppl_Name%>,<br>
					<%if(!tempcompnm.equals("")){ %>
<font size="1px" face="Arial"><%=tempcompnm%><br></font>
<%} %>
					<%=contact_Suppl_Person_Address%>,<br>
					<%=contact_Suppl_Person_City%>&nbsp;-&nbsp;<%=contact_Suppl_Person_Pin%>
				</font>
			</div>
   		 </td>
		<td colspan="1" width="10%">
			<div align="left">
				<font size="1.5px" face="Arial">
				<b>To:</b>
				</font>
			</div>
   		 </td>
    	<td colspan="3" width="40%">
			<div align="left">
				<font size="1.5px" face="Arial">
				<%if(contact_Person_Name_And_Designation.trim().equalsIgnoreCase(contact_Customer_Name.trim())){%>
				<%=contact_Customer_Name%>,<br>
				<%}else{%>
				<%=contact_Person_Name_And_Designation%>,<br>
				<%=contact_Customer_Name%>,<br>
				<%}%>
				<%=contact_Customer_Person_Address%>,<br>
				<%=contact_Customer_Person_City%>&nbsp;-&nbsp;<%=contact_Customer_Person_Pin%>
				</font>
			</div>
    	</td>
	</tr>
	<tr valign="top">
		<td colspan="7">
			<div align="center"> &nbsp; </div>   </td>
	</tr>
	<tr valign="top">
		<td colspan="3">
			<div align="left">
				<font size="1.5px" face="Arial">
				<%if(late_pay_contract_type.equals("C") || late_pay_contract_type.equals("R") || late_pay_contract_type.equals("B")) { %>
					State : <%=contact_Suppl_State%><br>
					State Code : <%=contact_Suppl_State_Code%><br>
					<% if(!contact_Suppl_GSTIN_NO.trim().equals("")) { %>
					 	GSTIN : <%=contact_Suppl_GSTIN_NO%><br>
					 <% } %> 
					 <% if(!contact_Suppl_PAN_NO.trim().equals("")){ %>
					 	PAN : <%=contact_Suppl_PAN_NO%><br><% } %>
					 	<%=sac_name%> : <%=sac_code%><br>
						Description of Service : <%=service_desc%><br>
						Place Of Supply : <%=contact_Customer_Plant_State%><br>
				<% } else if(late_pay_contract_type.equals("S") || late_pay_contract_type.equals("L") || late_pay_contract_type.equals("V")) { %>
					<%if(!contact_Suppl_GST_NO.trim().equals("")) { %>
						GST TIN No. : <%=contact_Suppl_GST_NO%> DT. <%=contact_Suppl_GST_DT%><br>
						<%} if(!contact_Suppl_CST_NO.trim().equals("")) { %>
						CST TIN No. :  <%=contact_Suppl_CST_NO%> DT. <%=contact_Suppl_CST_DT%>
						<br>
						<%} if(!contact_Suppl_PAN_NO.trim().equals("")) { %>
						PAN :  <%=contact_Suppl_PAN_NO%>
						<br>
						<%}
				 } %>		
 				</font>
			</div>
		</td>
		<td colspan="1">
			<div align="left">
				<font size="1.5px">
					<b>&nbsp;</b>
				</font>
			</div>
    </td>
    <td colspan="3">
		<div align="left"><font size="1.5px" face="Arial">
		<%if(late_pay_contract_type.equals("C") || late_pay_contract_type.equals("R") || late_pay_contract_type.equals("B")) { %>
				State : <%=contact_Customer_Plant_State%><br>
				State Code : <%=contact_Customer_State_Code%><br>
	<%if(vSTAT_CD.size()>0){ %>
		<%for(int i=0; i<vSTAT_CD.size(); i++){ %>
				<%if(vSTAT_NM.elementAt(i).toString().contains("GSTIN")) { %>
					<%=vSTAT_NM.elementAt(i).toString()%> : <%=vSTAT_NO.elementAt(i)%><br>
				<% } else if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) { %>
					PAN : <%=(String)vSTAT_NO.elementAt(i)%><br>
				<% } %>
		<% } %>
	<%} else { %>
		<%if(!contact_Suppl_GSTIN_NO.trim().equals("")) { %>
			GSTIN : <%=contact_Suppl_GSTIN_NO%><br>
		<% } if(!contact_Suppl_PAN_NO.trim().equals("")) { %>
			PAN :  <%=contact_Suppl_PAN_NO%><br>
		<%} %>
	<% } %>
	<% }  else if(late_pay_contract_type.equals("S") || late_pay_contract_type.equals("L") || late_pay_contract_type.equals("V")) { 
		 %>
		 <%if(vSTAT_CD.size()>0){ %>
		 	<%for(int i=0; i<vSTAT_CD.size(); i++){%>
		 		<font size="1.5px" face="Arial">
		 		<% if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) { %>
		 			PAN  : <%=(String)vSTAT_NO.elementAt(i)%> <br>
		 		<%} else { 
		 			if(!vSTAT_NO.elementAt(i).equals("")){%> 
		 			<%=(String)vSTAT_NM.elementAt(i)%> : <%=(String)vSTAT_NO.elementAt(i)%> <%if(!vSTAT_EFF_DT.elementAt(i).equals("")){ %> DT. <%=(String)vSTAT_EFF_DT.elementAt(i)%><%} %><br> 
		 		<% }} %>  
		 		</font>
		 	<%}%>
		 <%} else { %>
			 <%if(!contact_Customer_GST_NO.trim().equals("")){%>
			 GST TIN No. : <%=contact_Customer_GST_NO%> DT. <%=contact_Customer_GST_DT%><br>
			 <%} if(!contact_Customer_CST_NO.trim().equals("")){%>
			 CST TIN No. :  <%=contact_Customer_CST_NO%> DT. <%=contact_Customer_CST_DT%><br>
			 <%} if(!contact_Suppl_PAN_NO.trim().equals("")){%>
			 PAN :  <%=contact_Suppl_PAN_NO%> <br>
			 <%} if(!contact_Customer_GVAT_NO.trim().equals("")){%>
			 GVAT TIN No. :  <%=contact_Customer_GVAT_NO%> DT. <%=contact_Customer_GVAT_DT%>
			 <%} 
			} 
	 } %>
</font></div></td>
</tr>
<tr valign="top">
	<td colspan="7">
		<div align="center"> &nbsp; </div>
  		 </td>
</tr>
<tr valign="middle">
	<td colspan="4"></td>
	<td colspan="2" width="25%">
		<div align="center">
			<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
				<tr><td><div align="right"><font size="1.5px" face="Arial"><b>Invoice Date:</b></font></div></td></tr>
				<tr><td><div align="right"><font size="1.5px" face="Arial"><b>SEIPL 
					<%if(late_pay_contract_type.equalsIgnoreCase("R")){%>R-gas&nbsp;<%}%><%if(late_pay_contract_type.equalsIgnoreCase("C") || late_pay_contract_type.equals("B")  ){%>Tax&nbsp;<%}%>
					Invoice Seq No: </b></font></div></td></tr> 
			</table>
		</div>
	</td>
	<td colspan="1" width="15%">
		<div align="center">
			<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
				<tr><td><div align="right"><font size="1.5px" face="Arial"><b><%=customer_Invoice_DT%></b></font></div></td></tr>
				<tr><td><div align="right"><font size="1.5px" face="Arial"><b><%=nNew_inv_seq_no%></b></font></div></td></tr> 
			</table>
		</div>
	</td>
</tr>
<tr valign="top">
	<td colspan="7"><div align="center"> &nbsp; </div></td>
</tr>
</table>
<table width="98%"  border="1" align="center" cellpadding="0" cellspacing="0">
	<tr align="center">
		<td width="6%"><div align="center"><font size="1.5px" face="Arial"><b>Sr. No.</b></font></div></td>
		<td width="34%"><div align="center"><font size="1.5px" face="Arial"><b>Item</b></font></div></td>
		<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Data</b></font></div></td>
		<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Currency</b></font></div></td>
<!-- 		<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>Quantity<br>(MMBTUS)</b></font></div></td> -->
		<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Rate</b></font></div></td>
		<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>Amount</b></font></div></td>
	</tr>
	<%if(pSr_no.size()>0) { %>
	<tr align="center" valign="bottom">
		<td ><font size="1.5px" face="Arial">
			<%for(int i=0;i<pSr_no.size()-1;i++) { %>
				<%=pSr_no.elementAt(i)%><br><br>
			<% } %>	
		</font></td>
		<td align="left"><font size="1.5px" face="Arial">
			<%for(int i=0;i<pSr_no.size()-1;i++) { %>
				&nbsp;<%=pItem.elementAt(i)%><br><br>
			<% } %>	
		</font></td>
		<td ><font size="1.5px" face="Arial">
			<%for(int i=0;i<pSr_no.size()-1;i++) { %>
				<%=pData.elementAt(i)%><br><br>
			<% } %>	
		</font></td>
		<td ><font size="1.5px" face="Arial">
			<%for(int i=0;i<pSr_no.size()-1;i++) { %>
				<%=pCurrency.elementAt(i)%><br><br>
			<% } %>	
		</font></td>
		<td align="right"><font size="1.5px" face="Arial">
			<%for(int i=0;i<pSr_no.size()-1;i++) { %>
				<%=pRate.elementAt(i)%>&nbsp;<br><br>
			<% } %>	
		</font></td>
		<td align="right"><font size="1.5px" face="Arial">
			<%for(int i=0;i<pSr_no.size()-1;i++) { %>
				<%=pAmount.elementAt(i)%>&nbsp;<br><br>
			<% } %>	
		</font></td>	
	</tr>
	<tr align="center" valign="bottom">
		<td ><font size="1.5px" face="Arial">
				<b><%=pSr_no.elementAt(pSr_no.size()-1)%></b>
		</font></td>
		<td align="left"><font size="1.5px" face="Arial">
				<b>&nbsp;<%=pItem.elementAt(pSr_no.size()-1)%></b>
		</font></td>
		<td ><font size="1.5px" face="Arial">
				<b><%=pData.elementAt(pSr_no.size()-1)%></b>
		</font></td>
		<td ><font size="1.5px" face="Arial">
				<b><%=pCurrency.elementAt(pSr_no.size()-1)%></b>
		</font></td>
		<td align="right"><font size="1.5px" face="Arial">
				<b><%=pRate.elementAt(pSr_no.size()-1)%>&nbsp;</b>
		</font></td>
		<td align="right"><font size="1.5px" face="Arial">
				<b><%=pAmount.elementAt(pSr_no.size()-1)%>&nbsp;</b>
		</font></td>	
	</tr>
	<% } %>
</table>
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td><font size="1.5px" face="Arial"><%=nRemark_1%></font></td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<%if(flag.equalsIgnoreCase("View")){ %>
	<tr valign="middle">
		<td>
			<div align="left">
				<font size="1.5px" face="Arial"> <b> For <%=contact_Suppl_Name%> <br><br><br><br><br>
						Authorised Signatory</b>
				</font>
			</div>
    	</td>
	</tr>
	<%}else if(flag.equalsIgnoreCase("Check") || flag.equalsIgnoreCase("Approve")){ %>
	<tr valign="middle">
		<td>
			<div align="center">
				<%if(flag.equalsIgnoreCase("Check")){ %>Checking <% } else { %>Approval <%} %>OK:&nbsp;
					<input type="radio" name="rd" value="Y"
						<%if((flag.equalsIgnoreCase("Check") && check_flag.equalsIgnoreCase("Y")) || (flag.equalsIgnoreCase("Approve") && aprv_flg.equalsIgnoreCase("Y"))) {%> checked="checked"<%} %>
						onClick="doSubmit('<%=flag%>');">&nbsp;<b>Yes</b>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rd" value="N"
						<%if((flag.equalsIgnoreCase("Check") && check_flag.equalsIgnoreCase("N")) || (flag.equalsIgnoreCase("Approve") && aprv_flg.equalsIgnoreCase("N"))) {%> checked="checked"<%} %>
						onClick="doSubmit('<%=flag%>');">&nbsp;<b>No</b>
			</div>
		</td>
	</tr>
	<%} %>
</table>
<input type='hidden' name='option' value=''>
<input type='hidden' name='check_flag' value='N'>
<input type='hidden' name='approve_flag' value='N'>
<input type='hidden' name='month' value='<%=month%>'>
<input type='hidden' name='year' value='<%=year%>'>
<input type='hidden' name='write_permission' value='<%=write_permission%>'>
<input type='hidden' name='approve_permission' value='<%=approve_permission%>'>
<input type='hidden' name='delete_permission' value='<%=delete_permission%>'>
<input type='hidden' name='print_permission' value='<%=print_permission%>'>
<input type='hidden' name='check_permission' value='<%=check_permission%>'>
<input type='hidden' name='authorize_permission' value='<%//=authorize_permission%>'>
<input type='hidden' name='customer_cd' value='<%=late_pay_customer_cd%>'>
<input type='hidden' name='hlpl_inv_seq_no' value='<%=nInvoice_seq_no_actual%>'>
<input type='hidden' name='financial_year' value='<%=late_pay_financial_year%>'>
<input type='hidden' name='new_inv_seq_no' value='<%=nNew_inv_seq_no%>'>
<input type='hidden' name='contract_type' value='<%=late_pay_contract_type%>'>
<input type='hidden' name='form_id' value='<%=form_id%>'>
<input type='hidden' name='form_nm' value='<%=form_nm%>'>
<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
<input type="hidden" name="modUrl" value="<%=modUrl%>" >
</form>
<script>
function doSubmit(value) {
	if(value=='Check') {
		document.forms[0].option.value = 'Check_Late_Invoice';
	} else if(value=='Approve') {
		document.forms[0].option.value = 'Approve_Late_Invoice';
	}
	var decission = '';
	if(value=='Check') {
		if(document.forms[0].rd[0].checked)
		{
			document.forms[0].check_flag.value = "Y";
			decission = "Do you want to 'CLEAR' the Invoice from Checking Process ?";
		}
		else if(document.forms[0].rd[1].checked)
		{
			document.forms[0].check_flag.value = "N";
			decission = "Do you want to 'STOP' the Invoice from Checking Process ?";
		}
	} else {
		if(document.forms[0].rd[0].checked)
		{
			document.forms[0].approve_flag.value = "Y";
			decission = 'Do you want to Approve the Invoice ?';
		}
		else if(document.forms[0].rd[1].checked)
		{
			document.forms[0].approve_flag.value = "N";
			decission = 'Do you want to Dis-Approve the Invoice ?';
		}
	}	
	if(decission!=null && decission!='')
	{
		var a = confirm(""+decission);
		if(a) {
			document.forms[0].submit();
		}
	}
}
function doClose(msg,month,year)
{
	window.opener.refreshPageFromChild(msg,month,year);
	window.close();
}
</script>
</body>
</html>	