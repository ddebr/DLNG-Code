<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title>Customer Invoice Attachment-2 Details</title>

<script type="text/javascript" src="../js/mouseclk.js"></script>
<script type="text/javascript" src="../js/date-picker.js"></script>
<script type="text/javascript" src="../js/aris.js"></script>
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.sales_invoice.DataBean_Sales_InvoiceV2 " id="salesInv" scope="page"/>   
<%-- <jsp:useBean class="com.henergy.bombay.util.DataBean_modulelock" id="modlock" scope="request"/> --%>
<%
	util.init();
	
	String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
	String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
	String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");
	
	String customer_abbr = request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
	String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
	String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
	String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
	String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
	String sn_rev_no = request.getParameter("sn_rev_no")==null?"":request.getParameter("sn_rev_no");
	String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
	String customer_plant_nm = request.getParameter("customer_plant_nm")==null?"":request.getParameter("customer_plant_nm");
	String customer_plant_seq_no = request.getParameter("customer_plant_seq_no")==null?"":request.getParameter("customer_plant_seq_no");
	String bill_period_start_dt = request.getParameter("bill_period_start_dt")==null?"":request.getParameter("bill_period_start_dt");
	String bill_period_end_dt = request.getParameter("bill_period_end_dt")==null?"":request.getParameter("bill_period_end_dt");
	String due_dt = request.getParameter("due_dt")==null?"":request.getParameter("due_dt");
	String contact_person = request.getParameter("contact_person")==null?"0":request.getParameter("contact_person");
	String month = request.getParameter("month")==null?"0":request.getParameter("month");
	String year = request.getParameter("year")==null?"0":request.getParameter("year");
	String bill_cycle = request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");
	String exchg_rate_cd = request.getParameter("exchg_rate_cd")==null?"0":request.getParameter("exchg_rate_cd");
	String exchg_rate_cal_method = request.getParameter("exchg_rate_cal_method")==null?"0":request.getParameter("exchg_rate_cal_method");
	String invoice_date = request.getParameter("invoice_date")==null?bill_period_end_dt:request.getParameter("invoice_date");
	String particular_date = request.getParameter("particular_date")==null?bill_period_end_dt:request.getParameter("particular_date");
	String rbi_ref_cd = request.getParameter("rbi_ref_cd")==null?"1":request.getParameter("rbi_ref_cd");
	String sbi_tt_selling_cd = request.getParameter("sbi_tt_selling_cd")==null?"2":request.getParameter("sbi_tt_selling_cd");
	String sbi_tt_buying_cd = request.getParameter("sbi_tt_buying_cd")==null?"3":request.getParameter("sbi_tt_buying_cd");
	String sbi_avg_tt_selling_buying_cd = request.getParameter("sbi_avg_tt_selling_buying_cd")==null?"6":request.getParameter("sbi_avg_tt_selling_buying_cd");
	String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"":request.getParameter("hlpl_inv_seq_no");
	String inv_financial_year = request.getParameter("inv_financial_year")==null?"":request.getParameter("inv_financial_year");
	String hlpl_inv_no = request.getParameter("hlpl_inv_no")==null?"":request.getParameter("hlpl_inv_no");
	String customer_inv_no = request.getParameter("customer_inv_no")==null?"":request.getParameter("customer_inv_no");
	
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String inv_approved_flag = request.getParameter("inv_approved_flag")==null?"":request.getParameter("inv_approved_flag");
	
	String mapping_id = request.getParameter("mapping_id")==null?"-1":request.getParameter("mapping_id");
	String inv_tit = request.getParameter("invoice_tit")==null?"":request.getParameter("invoice_tit"); //RG20161217
	String drcr_no = request.getParameter("drcr_no")==null?"":request.getParameter("drcr_no"); //RG20161217
	String dt_drcr = request.getParameter("drcr_dt")==null?"":request.getParameter("drcr_dt"); //RG20161217
	String rbi_ref_flag = "";
	String sbi_tt_selling_flag = "";
	String sbi_tt_buying_flag = "";
	String sbi_avg_tt_selling_buying_flag = "";
	
	if(exchg_rate_cd.equals(rbi_ref_cd))
	{
		rbi_ref_flag = "checked";
	}
	else if(exchg_rate_cd.equals(sbi_tt_selling_cd))
	{
		sbi_tt_selling_flag = "checked";
	}
	else if(exchg_rate_cd.equals(sbi_tt_buying_cd))
	{
		sbi_tt_buying_flag = "checked";
	}
	else if(exchg_rate_cd.equals(sbi_avg_tt_selling_buying_cd))
	{
		sbi_avg_tt_selling_buying_flag = "checked";
	}
	
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	
	String financial_year = "";
	
	if(Integer.parseInt(month)>3)
	{
		financial_year = ""+year+"-"+(Integer.parseInt(year)+1);		
	}
	else
	{
		financial_year = ""+(Integer.parseInt(year)-1)+"-"+year;
	}
	String flag="N"; //RG20161712
	salesInv.setCallFlag("SALES_INVOICE_REPORT");
	salesInv.setCustomerCd(customer_cd);
	salesInv.setFgsaNo(fgsa_no);
	salesInv.setFgsaRevNo(fgsa_rev_no);
	salesInv.setSnNo(sn_no);
	salesInv.setSnRevNo(sn_rev_no);
	salesInv.setContractType(contract_type);
	salesInv.setCustomerPlantSeqNo(customer_plant_seq_no);
	salesInv.setBillPeriodStartDt(bill_period_start_dt);
	salesInv.setBillPeriodEndDt(bill_period_end_dt);
	salesInv.setContactPersonCd(contact_person);
	salesInv.setHlplInvoiceNo(hlpl_inv_seq_no);
	salesInv.setInvFinancialYear(inv_financial_year);
	salesInv.setBillCycle(bill_cycle);
	salesInv.setCustomer_inv_mapping_id(mapping_id); //ADDED FOR LTCORA AND CN
	salesInv.setFlag1(flag);
	if(!bill_cycle.equals("0"))
	{
		salesInv.init();
	}
	
	//Following String Getter Methods Has Been Defined By Samik Shah On 4th June, 2010 ...
	String contact_Person_Name_And_Designation = salesInv.getContact_Person_Name_And_Designation();
	
	String contact_Customer_Name = salesInv.getContact_Customer_Name();
	String contact_Customer_Person_Address = salesInv.getContact_Customer_Person_Address();
	String contact_Customer_Person_City = salesInv.getContact_Customer_Person_City();
	String contact_Customer_Person_Pin = salesInv.getContact_Customer_Person_Pin();
	String contact_Customer_GST_NO = salesInv.getContact_Customer_GST_NO();
	String contact_Customer_CST_NO = salesInv.getContact_Customer_CST_NO();
	String contact_Customer_GST_DT = salesInv.getContact_Customer_GST_DT();
	String contact_Customer_CST_DT = salesInv.getContact_Customer_CST_DT();

	String contact_Suppl_Name = salesInv.getContact_Suppl_Name();
	String contact_Suppl_Person_Address = salesInv.getContact_Suppl_Person_Address();
	String contact_Suppl_Person_City = salesInv.getContact_Suppl_Person_City();
	String contact_Suppl_Person_Pin = salesInv.getContact_Suppl_Person_Pin();
	String contact_Suppl_GST_NO = salesInv.getContact_Suppl_GST_NO();
	String contact_Suppl_CST_NO = salesInv.getContact_Suppl_CST_NO();
	String contact_Suppl_GST_DT = salesInv.getContact_Suppl_GST_DT();
	String contact_Suppl_CST_DT = salesInv.getContact_Suppl_CST_DT();
	
	String customer_Invoice_DT = salesInv.getCustomer_Invoice_DT();
	String customer_Invoice_Due_DT = salesInv.getCustomer_Invoice_Due_DT();
	String customer_Invoice_Start_DT = salesInv.getCustomer_Invoice_Start_DT();
	String customer_Invoice_End_DT = salesInv.getCustomer_Invoice_End_DT();
	
	//Following String & Vector Getter Methods Has Been Defined By Samik Shah On 5th June, 2010 ...
	String customer_Invoice_Tax_Flag = salesInv.getCustomer_Invoice_Tax_Flag();
	String customer_Invoice_SN_Dt = salesInv.getCustomer_Invoice_SN_Dt();
	String customer_Invoice_FGSA_Dt = salesInv.getCustomer_Invoice_FGSA_Dt();
	String total_Invoice_Qty = salesInv.getTotal_Invoice_Qty();
	String invoice_Sales_Rate = salesInv.getInvoice_Sales_Rate();
	String customer_Invoice_Gross_Amt_USD = salesInv.getCustomer_Invoice_Gross_Amt_USD();
	String customer_Invoice_Gross_Amt_INR = salesInv.getCustomer_Invoice_Gross_Amt_INR();
	String customer_Invoice_Net_Amt_INR = salesInv.getCustomer_Invoice_Net_Amt_INR();
	String customer_Invoice_Exchg_Rate = salesInv.getCustomer_Invoice_Exchg_Rate();
	Vector customer_Invoice_Tax_Code = salesInv.getCustomer_Invoice_Tax_Code();
	Vector customer_Invoice_Tax_Abbr = salesInv.getCustomer_Invoice_Tax_Abbr();
	Vector customer_Invoice_Tax_Name = salesInv.getCustomer_Invoice_Tax_Name();
	Vector customer_Invoice_Tax_Amt = salesInv.getCustomer_Invoice_Tax_Amt();
	Vector customer_Invoice_Tax_Rate = salesInv.getCustomer_Invoice_Tax_Rate();
	
	Vector customer_Invoice_Exchg_Rate_Code = salesInv.getCustomer_Invoice_Exchg_Rate_Code();
	Vector customer_Invoice_Exchg_Rate_Name = salesInv.getCustomer_Invoice_Exchg_Rate_Name();
	Vector customer_Invoice_Exchg_Rate_Date = salesInv.getCustomer_Invoice_Exchg_Rate_Date();
	Vector customer_Invoice_Exchg_Rate_Value = salesInv.getCustomer_Invoice_Exchg_Rate_Value();	
	
	Vector customer_Invoice_Bank_Name = salesInv.getCustomer_Invoice_Bank_Name();
	
	String dr_cr_dt1 = salesInv.getDr_cr_dt1();
	String dr_cr_doc_no1=salesInv.getDr_cr_doc_no1();
	
	String invno = "";
	String inv_no1="";
	String new_inv_seq_no = salesInv.getNew_inv_seq_no();
	if(!new_inv_seq_no.equals("")) {
		invno = new_inv_seq_no;
		inv_no1 = new_inv_seq_no;
// 		if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) {
// 			if(hlpl_inv_no.length()>13)
// 			{
// 				inv_no1= hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
// 			}
// 		} else {
// 			if(hlpl_inv_no.length()>13)
// 			{
// 				inv_no1 = hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
// 			}
// 		}
	} else {
		if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		{
			if(hlpl_inv_no.length()>13)
			{
				invno = hlpl_inv_no.substring(0,10)+""+hlpl_inv_no.substring(12);
				inv_no1= hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
			}
		}
		else
		{
			if(hlpl_inv_no.length()>13)
			{
				invno = hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
				inv_no1 = hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
			}
		}
	}
%>

<body <%if(inv_approved_flag.trim().equalsIgnoreCase("N") || inv_approved_flag.trim().equals("")){%>background="../images/draft_copy.JPG"<%}%>>
<%	if(print_permission.trim().equalsIgnoreCase("N"))
	{	%>
		<script language="javascript" src="../js/mouseclk.js"></script>
<%	}	%>
<form name="rpt_invoice_dtl" method="post" action="../servlet/Frm_Sales_Invoice">

<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
	<tr valign="center">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="top">
		<td colspan="3" width="45%">
			<div align="left">
				<font size="1.5px" face="Arial">
					<b><%=contact_Suppl_Name%></b><br>
					Registered Office:<br>
					<%=contact_Suppl_Name%>,<br>
					<%=contact_Suppl_Person_Address%>,<br>
					<%=contact_Suppl_Person_City%>&nbsp;-&nbsp;<%=contact_Suppl_Person_Pin%>
				</font>
			</div>
	    </td>
		<td colspan="1" width="21%">
			<div align="left">
				<font size="1.5px" face="Arial">
					<b>&nbsp;</b>
				</font>
			</div>
	    </td>
	    <td colspan="3" width="34%">
			<div align="left">
				<font size="1.5px" face="Arial">
					<b>To:</b><br>
					<%	if(contact_Person_Name_And_Designation.trim().equalsIgnoreCase(contact_Customer_Name.trim()))
						{	%>
							<%=contact_Customer_Name%>,<br>
					<%	}
						else
						{	%>
							<%=contact_Person_Name_And_Designation%>,<br>
							<%=contact_Customer_Name%>,<br>
					<%	}	%>
					<%=contact_Customer_Person_Address%>,<br>
					<%=contact_Customer_Person_City%>&nbsp;-&nbsp;<%=contact_Customer_Person_Pin%>
				</font>
			</div>
	    </td>
	</tr>
	<tr valign="top">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="top">
		<td colspan="7">
			<div align="center">
			<%if(inv_tit.contains("CREDIT") || inv_tit.contains("DEBIT")|| inv_tit.contains("SUP")) {%>
				<font size="1.5px" face="Arial"><b>ATTACHMENT 1 - Exchange Rate Applicable</b></font>
				<%}else { %>
				<font size="1.5px" face="Arial"><b>ATTACHMENT 2 - Exchange Rate Applicable</b></font>
				<%} %>
			</div>
	    </td>
	</tr>
	<tr valign="center">
		<td colspan="3">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	    <td colspan="1" width="21%">
			<div align="center">
				<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
				<%if((!inv_tit.contains("SUP"))&& (!inv_tit.contains("DEBIT")) && (!inv_tit.contains("CREDIT"))){ %>
					<tr valign="center">
						<td><div align="right"><font size="1.5px" face="Arial"><b>Invoice Date:</b></font></div></td>
					</tr>
					<tr valign="center">
						<td><div align="right"><font size="1.5px" face="Arial"><b>Payment Due Date:</b></font></div></td>
					</tr>
					
					<tr valign="center">
						<td><div align="right"><font size="1.5px" face="Arial"><b> <%if(contract_type.equalsIgnoreCase("R")){%>R-gas&nbsp;<%}%><%if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>Tax&nbsp;<%}%>Invoice Seq No:</b></font></div></td>
					</tr>
					<%}else{ %>
					<tr valign="center">
						<td><div align="right"><font size="1.5px" face="Arial"><%if(inv_tit.contains("SUP")){ %><b>Debit Note Date:</b><%}else if(inv_tit.contains("DEBIT")){ %><b>Debit Note Date:</b><%}else{ %><b>Credit Note Date:</b><%} %></font></div></td>
					</tr>
					<tr valign="center">
						<td><div align="right"><font size="1.5px" face="Arial"><%if(inv_tit.contains("SUP")){ %><b>Debit Note No:</b><%}else if(inv_tit.contains("DEBIT")){ %><b>Debit Note No:</b><%}else{ %><b>Credit Note No:</b><%} %></font></div></td>
					</tr>
					<%} %>
					<%--<tr valign="center">
						<td><div align="right"><font size="1.5px" face="Arial"><b><%=customer_abbr%> Invoice No:</b></font></div></td>
					</tr>--%>
					<tr valign="center">
						<td><div align="right"><font size="1.5px" face="Arial"><b>For the Billing Period:</b></font></div></td>
					</tr>
				</table>
			</div>
	    </td>
	    <td colspan="1" width="13%">
			<div align="center">
				<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
					<%if((!inv_tit.contains("SUP"))&& (!inv_tit.contains("DEBIT")) && (!inv_tit.contains("CREDIT"))){ %>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><%=customer_Invoice_DT%></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><%=customer_Invoice_Due_DT%></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><%=invno%></font></div></td>
					</tr>
					<%}else{ %>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><%=dt_drcr%></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><%=drcr_no%></font></div></td>
					</tr>
					<%} %>
					<%--<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><%=customer_inv_no%></font></div></td>
					</tr>--%>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><%=customer_Invoice_Start_DT%></font></div></td>
					</tr>
				</table>
			</div>
	    </td>
	    <td colspan="1" width="8%">
			<div align="center">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<%if((!inv_tit.contains("SUP"))&& (!inv_tit.contains("DEBIT")) && (!inv_tit.contains("CREDIT"))){ %>
					<tr>
						<td><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					</tr>
					<tr>
						<td><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					</tr>
					<tr>
						<td><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					</tr>
				<% } else { %>	
					<tr>
						<td><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					</tr>
					<tr>
						<td><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					</tr>
				<% } %>
					<tr>
						<td><div align="center"><font size="1.5px" face="Arial"><b>to</b></font></div></td>
					</tr>
				</table>
			</div>
	    </td>
	    <td colspan="1" width="13%">
			<div align="center">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<%if((!inv_tit.contains("SUP"))&& (!inv_tit.contains("DEBIT")) && (!inv_tit.contains("CREDIT"))){ %>
					<tr>
						<td><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					</tr>
					<tr>
						<td><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					</tr>
					<tr>
						<td><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					</tr>
				<% } else { %>	
					<tr>
						<td><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					</tr>
					<tr>
						<td><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					</tr>
				<% } %>
					<tr>
						<td>
							<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<div align="right">
											<font size="1.5px" face="Arial"><%=customer_Invoice_End_DT%></font>
										</div>
									</td>
								</tr>
							</table>
						</td>		
					</tr>
				</table>
			</div>
	    </td>
	</tr>
	<tr valign="center">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="center">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
<%	for(int i=0; i<customer_Invoice_Exchg_Rate_Code.size(); i++)
	{	%>
	<tr valign="center">
		<td colspan="3">
			<div align="left">
				<font size="1.5px" face="Arial"><%=(String)customer_Invoice_Exchg_Rate_Name.elementAt(i)%>....&nbsp;On&nbsp;<%=(String)customer_Invoice_Exchg_Rate_Date.elementAt(i)%>&nbsp;(INR/USD)</font>
			</div>
	    </td>
	    <td colspan="1">
			<div align="right">
				<font size="1.5px" face="Arial"><%=(String)customer_Invoice_Exchg_Rate_Value.elementAt(i)%></font>
			</div>
	    </td>
	    <td colspan="3">
			<div align="left">
				&nbsp;
			</div>
	    </td>
	</tr>
<%	}	%>
	<tr valign="center">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="center">
		<td colspan="3">
			<div align="left">
			<%if((!inv_tit.contains("SUP"))&& (!inv_tit.contains("DEBIT")) && (!inv_tit.contains("CREDIT"))){ %>
				<font size="1.5px" face="Arial"><b>Exchange Rate Applicable (INR/USD)</b></font>
				<%}else{ %>
				<font size="1.5px" face="Arial"><b>Exchange Rate Applicable used in Invoice <%=inv_no1%> (INR/USD)</b></font><!-- RG20161229 -->
				<%} %>
			</div>
	    </td>
	    <td colspan="1">
			<div align="right">
				<font size="1.5px" face="Arial"><b><%=customer_Invoice_Exchg_Rate%></b></font>
			</div>
	    </td>
	    <td colspan="3">
			<div align="left">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="center">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="center">
		<td colspan="7">
			<div align="left">
				<font size="1.5px" face="Arial">
					Source:
					<br>
					<%=customer_Invoice_Bank_Name.elementAt(0)%>
				</font>
			</div>
	    </td>
	</tr>	
</table>

</form>
</body>
</html>