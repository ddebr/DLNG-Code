<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>Customer Invoice Details</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script type="text/javascript" src="../js/mouseclk.js"></script>
<script type="text/javascript" src="../js/date-picker.js"></script>
<script type="text/javascript" src="../js/aris.js"></script>
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>

<script type="text/javascript">

var newWindow;
var newWindow2;

//Following Method Is Introduced By Samik Shah On 7th June, 2010 ...
function openInvoiceAtt2Page(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,hlpl_inv_no,customer_inv_no,print_permission)
{
	if(!newWindow2 || newWindow2.closed)
	{
		newWindow2 = window.open("rpt_invoice_att2_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&print_permission="+print_permission,"Rpt_InvoiceAtt2","top=10,left=10,width=950,height=750,scrollbars=1,menubar=1");
	}
	else
	{
		newWindow2.close();
	    newWindow2 = window.open("rpt_invoice_att2_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&print_permission="+print_permission,"Rpt_InvoiceAtt2","top=10,left=10,width=950,height=750,scrollbars=1,menubar=1");
	}
}

//Following Method Is Introduced By Samik Shah On 7th June, 2010 ...
function openInvoiceAtt1Page(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,hlpl_inv_no,customer_inv_no,print_permission)
{
	if(!newWindow || newWindow.closed)
	{
		newWindow = window.open("rpt_invoice_att1_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&print_permission="+print_permission,"Rpt_InvoiceAtt1","top=10,left=10,width=950,height=750,scrollbars=1,menubar=1");
	}
	else
	{
		newWindow.close();
	    newWindow = window.open("rpt_invoice_att1_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&print_permission="+print_permission,"Rpt_InvoiceAtt1","top=10,left=10,width=950,height=750,scrollbars=1,menubar=1");
	}
}

</script>

</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.sales_invoice.DataBean_Sales_InvoiceV2" id="salesInv" scope="page"/>   
<jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_modulelock" id="modlock" scope="request"/>
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
	String invoice_title = request.getParameter("invoice_title")==null?"ORIGINAL":request.getParameter("invoice_title");
	
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	
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
	salesInv.init();
	
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
	
	//Following (4) String Getter Methods Has Been Defined By Samik Shah On 26th August, 2010 ...
	String contact_Customer_Service_Tax_NO = salesInv.getContact_Customer_Service_Tax_NO();
	String contact_Customer_Service_Tax_DT = salesInv.getContact_Customer_Service_Tax_DT();
	String contact_Suppl_Service_Tax_NO = salesInv.getContact_Suppl_Service_Tax_NO();
	String contact_Suppl_Service_Tax_DT = salesInv.getContact_Suppl_Service_Tax_DT();
	
	//Following (3) String Getter Methods Has Been Defined By Samik Shah On 9th February, 2011 ...
	String total_Offspec_Qty = salesInv.getTotal_Offspec_Qty();
	String offspec_Sales_Rate = salesInv.getOffspec_Sales_Rate();
	String offspec_Flag = salesInv.getOffspec_Flag();
	String offspec_Amt_USD = salesInv.getOffspec_Amt_USD();
	
	//Following (4) Vector Getter Methods Have Been Introduced By Samik Shah On 2nd September, 2011 ...
	Vector vSTAT_CD = salesInv.getVSTAT_CD();
	Vector vSTAT_NM = salesInv.getVSTAT_NM();
	Vector vSTAT_NO = salesInv.getVSTAT_NO();
	Vector vSTAT_EFF_DT = salesInv.getVSTAT_EFF_DT();
	
	//Following String Getter Methods Have Been Defined By Samik Shah ... 
	String remark_1 = salesInv.getRemark_1();
	String remark_2 = salesInv.getRemark_2();
	
	if(remark_1!=null)
	{
		if(!remark_1.trim().equals(""))
		{
			while((remark_1.indexOf("\n"))!=-1)
			{
				remark_1=(remark_1.substring(0,remark_1.indexOf("\n")-1))+"<br>"+(remark_1.substring(remark_1.indexOf("\n")+1));
			}
		}
	}
	
	if(remark_2!=null)
	{
		if(!remark_2.trim().equals(""))
		{
			while((remark_2.indexOf("\n"))!=-1)
			{
				remark_2=(remark_2.substring(0,remark_2.indexOf("\n")-1))+"<br>"+(remark_2.substring(remark_2.indexOf("\n")+1));
			}
		}
	}
	
	int sr_no = 4;
	
	if(contract_type.equalsIgnoreCase("R"))
	{
		sr_no = 5;
	}
	
	if(offspec_Flag.trim().equalsIgnoreCase("Y"))
	{
		++sr_no;
	}
	
	String invno = "";
	if(hlpl_inv_no.length()>13)
	{
		invno = hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
	}
	
	//Following (2) String Getter Methods Has Been Defined By Samik Shah On 15th April, 2011 ...
	String contact_addr_flag = salesInv.getContact_addr_flag();
	String sn_ref_no = salesInv.getSn_ref_no();
	
	//Following (2) String Getter Methods Has Been Defined By Samik Shah On 16th April, 2011 ...
	String contact_Customer_GVAT_NO = salesInv.getContact_Customer_GVAT_NO();
	String contact_Customer_GVAT_DT = salesInv.getContact_Customer_GVAT_DT();
%>

<body>
<%	if(print_permission.trim().equalsIgnoreCase("N"))
	{	%>
		<script language="javascript" src="../js/mouseclk.js"></script>
<%	}	%>
<form name="rpt_invoice_dtl" method="post" action="../servlet/Frm_Sales_Invoice">

<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
	<tr valign="center">
    	<td colspan="7">
			<div align="center">
				<font size="2" face="Arial">
					<b><%=invoice_title%></b><br>
				</font>
				<font size="4" face="Arial">
					<b>Hazira LNG Private Limited</b><br>
					<%	if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
						{	%>
						<%	if(customer_Invoice_Tax_Flag.equalsIgnoreCase("V"))
							{	%>	
								<b>TAX INVOICE</b>
						<%	}
							else
							{	%>
								<b>RETAIL INVOICE</b>
						<%	}	%>
					<%	}	
						else if(contract_type.equalsIgnoreCase("R"))
						{	%>
							<b>SERVICE INVOICE</b>
					<%	}	%>
				</font>
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
	<%	if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
		{	%>
			<tr valign="center">
				<td colspan="7">
					<div align="center">
						<font size="1px" face="Arial">
							In respect of Supply Notice (SN-<%if(sn_ref_no.trim().equals("")){%><%=sn_no%><%}else{%><%=sn_ref_no%><%}%>) executed on <%=customer_Invoice_SN_Dt%> pursuant to Framework Gas Sales Agreement executed on <%=customer_Invoice_FGSA_Dt%>
							<br>
							between <%=contact_Suppl_Name%> and <%=contact_Customer_Name%>
						</font>
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
	<tr valign="top">
		<td colspan="3" width="50%">
			<div align="left">
				<font size="1.5px" face="Arial">
					Registered Office:<br>
					<%=contact_Suppl_Name%>,<br>
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
	<%	if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
		{	%>	
			<td colspan="3">
				<div align="left">
					<font size="1.5px" face="Arial">
					<%	if(!contact_Suppl_GST_NO.trim().equals(""))
						{	%>
							GST TIN No. : <%=contact_Suppl_GST_NO%> DT. <%=contact_Suppl_GST_DT%>
							<br>
					<%	}
						else
						{	%>
							&nbsp;
							<br>
					<%	}	%>
					<%	if(!contact_Suppl_CST_NO.trim().equals(""))
						{	%>
							CST TIN No. :  <%=contact_Suppl_CST_NO%> DT. <%=contact_Suppl_CST_DT%>
							<br>
					<%	}
						else
						{	%>
							&nbsp;
							<br>
					<%	}	%>
					<%	if(!contact_Customer_GVAT_NO.trim().equals(""))
						{	%>
							&nbsp;
					<%	}
						else
						{	%>
							&nbsp;
					<% 	}	%>
					</font>
				</div>
		    </td>
	<%	}
		else if(contract_type.equalsIgnoreCase("R"))
		{	%>
			<td colspan="3">
				<div align="left">
					<font size="1.5px" face="Arial">
						Service Tax Registration No. : <%=contact_Suppl_Service_Tax_NO%>
						<br>
						(Business Auxiliary Service)
					</font>
				</div>
		    </td>
	<%	}	%>
		<td colspan="1">
			<div align="left">
				<font size="1.5px">
					<b>&nbsp;</b>
				</font>
			</div>
	    </td>
	<%	if(vSTAT_CD.size()>0)
		{	
			if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
			{	%>
			    <td colspan="3">
					<div align="left">
						<%	for(int i=0; i<vSTAT_CD.size(); i++)
							{	%>
								<font size="1.5px" face="Arial">
									<%=(String)vSTAT_NM.elementAt(i)%> : <%=(String)vSTAT_NO.elementAt(i)%> DT. <%=(String)vSTAT_EFF_DT.elementAt(i)%>
									<br>
								</font>
						<%	}	%>
					</div>
				</td>
	<%		}
			else if(contract_type.equalsIgnoreCase("R"))
			{	%>
				<td colspan="3">
					<div align="left">
						<%	for(int i=0; i<vSTAT_CD.size(); i++)
							{	%>
								<font size="1.5px" face="Arial">
									&nbsp;
									<br>
								</font>
						<%	}	%>
					</div>
			    </td>
	<%		}	
		}
		else
		{	%>
		<%	if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
			{	%>
			    <td colspan="3">
					<div align="left">
						<font size="1.5px" face="Arial">
						<%	if(!contact_Customer_GST_NO.trim().equals(""))
							{	%>	
								GST TIN No. : <%=contact_Customer_GST_NO%> DT. <%=contact_Customer_GST_DT%>
								<br>
						<%	}
							else
							{	%>
								&nbsp;
								<br>
						<%	}	%>
						<%	if(!contact_Customer_CST_NO.trim().equals(""))
							{	%>
								CST TIN No. :  <%=contact_Customer_CST_NO%> DT. <%=contact_Customer_CST_DT%>
								<br>
						<%	}
							else
							{	%>
								&nbsp;
								<br>
						<%	}	%>
						<%	if(!contact_Customer_GVAT_NO.trim().equals(""))
							{	%>
								GVAT TIN No. :  <%=contact_Customer_GVAT_NO%> DT. <%=contact_Customer_GVAT_DT%>
						<%	}
							else
							{	%>
								&nbsp;
						<%	}	%>
						</font>
					</div>
			    </td>
		<%	}
			else if(contract_type.equalsIgnoreCase("R"))
			{	%>
				<td colspan="3">
					<div align="left">
						<font size="1.5px" face="Arial">
							&nbsp;
							<br>
							&nbsp;
						</font>
					</div>
			    </td>
	<%		}	
		}		%>
	</tr>
	<tr valign="top">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="center">
		<td colspan="4">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	    <td colspan="2" width="25%">
			<div align="center">
				<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b>Invoice Date:</b></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b>Payment Due Date:</b></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b>HLPL <%if(contract_type.equalsIgnoreCase("R")){%>Re-Gas&nbsp;<%}%>Invoice Seq No:</b></font></div></td>
					</tr>
					<%--<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b><%=customer_abbr%> Invoice No:</b></font></div></td>
					</tr>--%>
				</table>
			</div>
	    </td>
	    <td colspan="1" width="15%">
			<div align="center">
				<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b><%=customer_Invoice_DT%></b></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b><%=customer_Invoice_Due_DT%></b></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b><%=invno%></b></font></div></td>
					</tr>
					<%--<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b><%=customer_inv_no%></b></font></div></td>
					</tr>--%>
				</table>
			</div>
	    </td>
	</tr>
	<tr valign="center">
		<td colspan="3">
			<div align="right">
				<font size="1.5px" face="Arial"><b>For the Billing Period</b></font>
			</div>
	    </td>
	    <td colspan="1">
			<div align="right">
				&nbsp;
			</div>
	    </td>
	    <td colspan="1" width="15%">
			<div align="center">
				<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b><%=customer_Invoice_Start_DT%></b></font></div></td>
					</tr>
				</table>
			</div>
	    </td>
	    <td colspan="1" width="10%">
			<div align="center">
				<font size="1.5px" face="Arial"><b>to</b></font>
			</div>
	    </td>
	    <td colspan="1" width="15%">
			<div align="center">
				<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b><%=customer_Invoice_End_DT%></b></font></div></td>
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
			<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
				<tr valign="bottom">
					<td width="6%"><div align="center"><font size="1.5px" face="Arial"><b>Sr. No.</b></font></div></td>
					<td width="34%"><div align="center"><font size="1.5px" face="Arial"><b>Item</b></font></div></td>
					<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Attachment<br>Reference</b></font></div></td>
					<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Currency</b></font></div></td>
					<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>Quantity<br>(MMBTUS)</b></font></div></td>
					<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Rate</b></font></div></td>
					<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>Amount</b></font></div></td>
				</tr>
				<tr valign="top">
					<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R")){%>1&nbsp;<br><br><%}%><%if(contract_type.equalsIgnoreCase("R")){%>2&nbsp;<br><br><%}else{%>1&nbsp;<br><br><%}%><%if(contract_type.equalsIgnoreCase("R")){%>3&nbsp;<%}else{%>2&nbsp;<%}%><%if(contract_type.equalsIgnoreCase("R") && offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>4&nbsp;<%}else if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>3&nbsp;<%}%></font></div></td>
					<td><div align="left"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R")){%>&nbsp;Gas&nbsp;Regasified<br><br><%}%><%if(contract_type.equalsIgnoreCase("R")){%>&nbsp;Regasification&nbsp;Tariff&nbsp;(USD/mmbtu)<%}else{%>&nbsp;Gas Delivered (USD)<%}%><%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>&nbsp;Offspec QTY<%}%><br><br>&nbsp;Gross Amount (USD)</font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R")){%><a href="#" onClick="openInvoiceAtt1Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;1</a><br><br><%}%><%if(contract_type.equalsIgnoreCase("R")){%>&nbsp;<br><br><%}else{%><a href="#" onClick="openInvoiceAtt1Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;1</a><br><br><%}%>&nbsp;</font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R")){%>&nbsp;<br><br><%}%>USD<br><br>USD<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>USD<%}%></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R")){%><%=total_Invoice_Qty%>&nbsp;<br><br><%}%><%=total_Invoice_Qty%>&nbsp;<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br><%=total_Offspec_Qty%>&nbsp;<%}%><br><br><%=total_Invoice_Qty%>&nbsp;</font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R")){%>&nbsp;<br><br><%}%><%=invoice_Sales_Rate%>&nbsp;<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br><%=offspec_Sales_Rate%>&nbsp;<%}%><br><br>&nbsp;</font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R")){%>&nbsp;<br><br><%}%><%=customer_Invoice_Gross_Amt_USD%>&nbsp;<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br><%=offspec_Amt_USD%>&nbsp;<%}%><br><br><%=customer_Invoice_Gross_Amt_USD%>&nbsp;</font></div></td>
				</tr>
				<tr valign="center">
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>
								<%if(contract_type.equalsIgnoreCase("R") && offspec_Flag.trim().equalsIgnoreCase("Y")){%>5&nbsp;<br><br>6&nbsp;<%}else if(contract_type.equalsIgnoreCase("R") && offspec_Flag.trim().equalsIgnoreCase("N")){%>4&nbsp;<br><br>5&nbsp;<%}else if(!contract_type.equalsIgnoreCase("R") && offspec_Flag.trim().equalsIgnoreCase("Y")){%>4&nbsp;<br><br>5&nbsp;<%}else{%>3&nbsp;<br><br>4&nbsp;<%}%>
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><br><%=(++sr_no)%>&nbsp;
								<%	}	%>
								<br><br><%=(++sr_no)%>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="left">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;Exchange Rate<br><br>&nbsp;Gross Amount (Rupees)
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><br>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
								<%	}	%>
								<br><br>&nbsp;Invoice Amount (Rupees)<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								&nbsp;<br><a href="#" onClick="openInvoiceAtt2Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>');">Att&nbsp;2</a><br><br>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><br>&nbsp;
								<%	}	%>
								<br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<br><br>Rupees
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><br>Rupees
								<%	}	%>
								<br><br>Rupees<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<br><br><%=total_Invoice_Qty%>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><br>&nbsp;
								<%	}	%>
								<br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br><%=customer_Invoice_Exchg_Rate%>&nbsp;<br><br>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><br><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
								<%	}	%>
								<br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<br><br><%=customer_Invoice_Gross_Amt_INR%>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><br><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
								<%	}	%>
								<br><br><%=customer_Invoice_Net_Amt_INR%>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
				</tr>
				<tr valign="top">
					<td><div align="right"><font size="1.5px" face="Arial"><b><%=(++sr_no)%>&nbsp;</b></font></div></td>
					<td><div align="left"><font size="1.5px" face="Arial"><b>&nbsp;Net Amount Payable</b></font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial"><b>Rupees</b></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial"><b><%=customer_Invoice_Net_Amt_INR%>&nbsp;</b></font></div></td>
				</tr>
			</table>
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
				<font size="1px" face="Arial">
					<%=remark_1%>
				</font>
			</div>
	    </td>
	</tr>
	<%	if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
		{	%>
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
						<font size="1px" face="Arial">
							<%=remark_2%>
						</font>
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
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="center">
		<td colspan="7">
			<div align="center">
				Authorization OK:&nbsp;
				<input type="radio" name="rd" value="Y">&nbsp;<b>Yes</b>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rd" value="N">&nbsp;<b>No</b>
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
</table>

</form>
</body>
</html>