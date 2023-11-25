<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title>Customer Invoice Attachment-1 Details</title>

<script type="text/javascript" src="../js/mouseclk.js"></script>
<script type="text/javascript" src="../js/date-picker.js"></script>
<script type="text/javascript" src="../js/aris.js"></script>
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.sales_invoice.DataBean_Sales_InvoiceV2" id="salesInv" scope="page"/>   
<%-- <jsp:useBean class="com.henergy.bombay.util.DataBean_modulelock" id="modlock" scope="request"/> --%>
<%
	util.init();
	NumberFormat nf = new DecimalFormat("###########0.00");
		
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
// 	System.out.println("mapping_id****"+mapping_id);
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
	salesInv.setBillCycle(bill_cycle);
	salesInv.setCustomer_inv_mapping_id(mapping_id); //ADDED FOR LTCORA AND CN
	if(!bill_cycle.equals("0"))
	{
		salesInv.init();
	}
	
	/////////SB20190103//////////
	Vector VGas_Day = salesInv.getVGas_Day();
	Vector VNom_Qty = salesInv.getVNom_Qty();
	Vector VSch_Qty = salesInv.getVSch_Qty();
	Vector VTruck_Nm = salesInv.getVTruck_Nm();
	Vector VLoaded_Vol = salesInv.getVLoaded_Vol();
	Vector VLoaded_Ene = salesInv.getVLoaded_Ene();
	Vector VLoaded_Dt = salesInv.getVLoaded_Dt();
	Vector VUnloaded_Vol = salesInv.getVUnloaded_Vol();
	Vector VUnloaded_Ene = salesInv.getVUnloaded_Ene();
	
	////////////////////////////
	String new_inv_seq_no = salesInv.getNew_inv_seq_no();
	//Following String Getter Methods Has Been Defined By Samik Shah On 4th June, 2010 ...
	String contact_Person_Name_And_Designation = salesInv.getContact_Person_Name_And_Designation();
	
	String contact_Customer_Name = salesInv.getContact_Customer_Name();
	String contact_Customer_Person_Address = salesInv.getContact_Customer_Person_Address();
	String contact_Customer_Person_City = salesInv.getContact_Customer_Person_City();
	String contact_Customer_Person_Pin = salesInv.getContact_Customer_Person_Pin();
	
	String contact_Suppl_Name = salesInv.getContact_Suppl_Name();
	String contact_Suppl_Person_Address = salesInv.getContact_Suppl_Person_Address();
	String contact_Suppl_Person_City = salesInv.getContact_Suppl_Person_City();
	String contact_Suppl_Person_Pin = salesInv.getContact_Suppl_Person_Pin();
		
	String customer_Invoice_DT = salesInv.getCustomer_Invoice_DT();
	String customer_Invoice_Due_DT = salesInv.getCustomer_Invoice_Due_DT();
	String customer_Invoice_Start_DT = salesInv.getCustomer_Invoice_Start_DT();
	String customer_Invoice_End_DT = salesInv.getCustomer_Invoice_End_DT();

	//Following Vector Getter Methods Has Been Defined By Samik Shah On 8th June, 2010 ...
	Vector invoice_Period_Dates = salesInv.getInvoice_Period_Dates();
	Vector invoice_Period_DCQ = salesInv.getInvoice_Period_DCQ();
	Vector invoice_Period_Buyer_Nom_Qty = salesInv.getInvoice_Period_Buyer_Nom_Qty();
	Vector invoice_Period_Seller_Nom_PNQ = salesInv.getInvoice_Period_Seller_Nom_PNQ();
	Vector invoice_Period_Seller_Nom_RE_Qty = salesInv.getInvoice_Period_Seller_Nom_RE_Qty();
	Vector invoice_Period_Gas_Delivered_PNQ = salesInv.getInvoice_Period_Gas_Delivered_PNQ();
	Vector invoice_Period_Gas_Delivered_Re_Qty = salesInv.getInvoice_Period_Gas_Delivered_Re_Qty();
	Vector invoice_Period_Gas_Delivered_Total_Qty = salesInv.getInvoice_Period_Gas_Delivered_Total_Qty();
	Vector invoice_Period_Cumulative_Qty = salesInv.getInvoice_Period_Cumulative_Qty();
	Vector invoice_Period_Cumulative_SN_Qty = salesInv.getInvoice_Period_Cumulative_SN_Qty();
	Vector invoice_Period_Buyer_Shortfall_Qty = salesInv.getInvoice_Period_Buyer_Shortfall_Qty();
	Vector invoice_Period_Buyer_Off_Spec_Qty = salesInv.getInvoice_Period_Buyer_Off_Spec_Qty();
	Vector invoice_Period_Buyer_Suspension_Qty = salesInv.getInvoice_Period_Buyer_Suspension_Qty();
	Vector invoice_Period_Delv_Failure_Qty = salesInv.getInvoice_Period_Delv_Failure_Qty();
	Vector invoice_Period_Total_Shortfall_Qty = salesInv.getInvoice_Period_Total_Shortfall_Qty();
	Vector invoice_Period_LD_Credit_Payable = salesInv.getInvoice_Period_LD_Credit_Payable();
	Vector invoice_Period_FM_Qty = salesInv.getInvoice_Period_FM_Qty();
	
	//Following (2) Vector Getter Methods Has Been Defined By Samik Shah On 8th February, 2011 ...
	Vector daily_Buyer_Allocation_Offspec_Rate = salesInv.getDaily_Buyer_Allocation_Offspec_Rate();
	Vector daily_Buyer_Allocation_Offspec_Flag = salesInv.getDaily_Buyer_Allocation_Offspec_Flag();
	
	//Following String Getter Method Has Been Defined By Samik Shah On 4th July, 2011 ...
	String liability_exist_flag = salesInv.getLiability_exist_flag();

	String DCQ_Total = "";
	String Buyer_Nom_Qty_Total = "";
	String Seller_Nom_PNQ_Total = "";
	String Seller_Nom_RE_Qty_Total = "";
	String Gas_Delivered_PNQ_Total = "";
	String Gas_Delivered_Re_Qty_Total = "";
	String Delivered_Total_Qty_Total = "";
	
	String Buyer_Shortfall_Qty_Total = "";
	String Buyer_Off_Spec_Qty_Total = "";
	String Buyer_Suspension_Qty_Total = "";
	String Delv_Failure_Qty_Total = "";
	String Total_Shortfall_Qty_Total = "";
	String LD_Credit_Payable_Total = "";
	String FM_Qty_Total = "";
	
	double dbl_DCQ_Total = 0;
	double dbl_Buyer_Nom_Qty_Total = 0;
	double dbl_Seller_Nom_PNQ_Total = 0;
	double dbl_Seller_Nom_RE_Qty_Total = 0;
	double dbl_Gas_Delivered_PNQ_Total = 0;
	double dbl_Gas_Delivered_Re_Qty_Total = 0;
	double dbl_Delivered_Total_Qty_Total = 0;
	
	double dbl_Buyer_Shortfall_Qty_Total = 0;
	double dbl_Buyer_Off_Spec_Qty_Total = 0;
	double dbl_Buyer_Suspension_Qty_Total = 0;
	double dbl_Delv_Failure_Qty_Total = 0;
	double dbl_Total_Shortfall_Qty_Total = 0;
	double dbl_LD_Credit_Payable_Total = 0;
	double dbl_FM_Qty_Total = 0;
	
	for(int i=0; i<invoice_Period_Dates.size(); i++)
	{
		DCQ_Total = ""+invoice_Period_DCQ.elementAt(i);
		Buyer_Nom_Qty_Total = ""+invoice_Period_Buyer_Nom_Qty.elementAt(i);
		Seller_Nom_PNQ_Total = ""+invoice_Period_Seller_Nom_PNQ.elementAt(i);
		Seller_Nom_RE_Qty_Total = ""+invoice_Period_Seller_Nom_RE_Qty.elementAt(i);
		Gas_Delivered_PNQ_Total = ""+invoice_Period_Gas_Delivered_PNQ.elementAt(i);
		Gas_Delivered_Re_Qty_Total = ""+invoice_Period_Gas_Delivered_Re_Qty.elementAt(i);
		Delivered_Total_Qty_Total = ""+invoice_Period_Gas_Delivered_Total_Qty.elementAt(i);
		
		Buyer_Shortfall_Qty_Total = ""+invoice_Period_Buyer_Shortfall_Qty.elementAt(i);
		Buyer_Off_Spec_Qty_Total = ""+invoice_Period_Buyer_Off_Spec_Qty.elementAt(i);
		Buyer_Suspension_Qty_Total = ""+invoice_Period_Buyer_Suspension_Qty.elementAt(i);
		Delv_Failure_Qty_Total = ""+invoice_Period_Delv_Failure_Qty.elementAt(i);
		Total_Shortfall_Qty_Total = ""+invoice_Period_Total_Shortfall_Qty.elementAt(i);
		LD_Credit_Payable_Total = ""+invoice_Period_LD_Credit_Payable.elementAt(i);
		FM_Qty_Total = ""+invoice_Period_FM_Qty.elementAt(i);
		
		if(!DCQ_Total.trim().equals(""))
		{
			dbl_DCQ_Total += Double.parseDouble(DCQ_Total);			
		}
		if(!Buyer_Nom_Qty_Total.trim().equals(""))
		{
			dbl_Buyer_Nom_Qty_Total += Double.parseDouble(Buyer_Nom_Qty_Total);
		}
		if(!Seller_Nom_PNQ_Total.trim().equals(""))
		{
			dbl_Seller_Nom_PNQ_Total += Double.parseDouble(Seller_Nom_PNQ_Total);
		}
		if(!Seller_Nom_RE_Qty_Total.trim().equals(""))
		{
			dbl_Seller_Nom_RE_Qty_Total += Double.parseDouble(Seller_Nom_RE_Qty_Total);
		}
		if(!Gas_Delivered_PNQ_Total.trim().equals(""))
		{
			dbl_Gas_Delivered_PNQ_Total += Double.parseDouble(Gas_Delivered_PNQ_Total);
		}
		if(!Gas_Delivered_Re_Qty_Total.trim().equals(""))
		{
			dbl_Gas_Delivered_Re_Qty_Total += Double.parseDouble(Gas_Delivered_Re_Qty_Total);
		}
		if(!Delivered_Total_Qty_Total.trim().equals(""))
		{
			dbl_Delivered_Total_Qty_Total += Double.parseDouble(Delivered_Total_Qty_Total);
		}
		
		if(!Buyer_Shortfall_Qty_Total.trim().equals(""))
		{
			dbl_Buyer_Shortfall_Qty_Total += Double.parseDouble(Buyer_Shortfall_Qty_Total);
		}
		if(!Buyer_Off_Spec_Qty_Total.trim().equals(""))
		{
			dbl_Buyer_Off_Spec_Qty_Total += Double.parseDouble(Buyer_Off_Spec_Qty_Total);
		}
		if(!Buyer_Suspension_Qty_Total.trim().equals(""))
		{
			dbl_Buyer_Suspension_Qty_Total += Double.parseDouble(Buyer_Suspension_Qty_Total);
		}
		if(!Delv_Failure_Qty_Total.trim().equals(""))
		{
			dbl_Delv_Failure_Qty_Total += Double.parseDouble(Delv_Failure_Qty_Total);
		}
		if(!Total_Shortfall_Qty_Total.trim().equals(""))
		{
			dbl_Total_Shortfall_Qty_Total += Double.parseDouble(Total_Shortfall_Qty_Total);
		}
		if(!LD_Credit_Payable_Total.trim().equals(""))
		{
			dbl_LD_Credit_Payable_Total += Double.parseDouble(LD_Credit_Payable_Total);
		}
		if(!FM_Qty_Total.trim().equals(""))
		{
			dbl_FM_Qty_Total += Double.parseDouble(FM_Qty_Total);
		}
	}
	
	DCQ_Total = " ";
	Buyer_Nom_Qty_Total = " ";
	Seller_Nom_PNQ_Total = " ";
	Seller_Nom_RE_Qty_Total = " ";
	Gas_Delivered_PNQ_Total = " ";
	Gas_Delivered_Re_Qty_Total = " ";
	Delivered_Total_Qty_Total = " ";
	
	Buyer_Shortfall_Qty_Total = " ";
	Buyer_Off_Spec_Qty_Total = " ";
	Buyer_Suspension_Qty_Total = " ";
	Delv_Failure_Qty_Total = " ";
	Total_Shortfall_Qty_Total = " ";
	LD_Credit_Payable_Total = " ";
	FM_Qty_Total = " ";
	
	if(dbl_DCQ_Total>0)
	{
		DCQ_Total = nf.format(dbl_DCQ_Total);
	}
	if(dbl_Buyer_Nom_Qty_Total>0)
	{
		Buyer_Nom_Qty_Total = nf.format(dbl_Buyer_Nom_Qty_Total);
	}
	if(dbl_Seller_Nom_PNQ_Total>0)
	{
		Seller_Nom_PNQ_Total = nf.format(dbl_Seller_Nom_PNQ_Total);
	}
	if(dbl_Seller_Nom_RE_Qty_Total>0)
	{
		Seller_Nom_RE_Qty_Total = nf.format(dbl_Seller_Nom_RE_Qty_Total);
	}
	if(dbl_Gas_Delivered_PNQ_Total>0)
	{
		Gas_Delivered_PNQ_Total = nf.format(dbl_Gas_Delivered_PNQ_Total);
		Gas_Delivered_Re_Qty_Total = nf.format(dbl_Gas_Delivered_Re_Qty_Total);
	}
	if(dbl_Delivered_Total_Qty_Total>0)
	{
		Delivered_Total_Qty_Total = nf.format(dbl_Delivered_Total_Qty_Total);
	}
	
	if(dbl_Buyer_Shortfall_Qty_Total>0)
	{
		Buyer_Shortfall_Qty_Total = nf.format(dbl_Buyer_Shortfall_Qty_Total);
	}
	if(dbl_Buyer_Off_Spec_Qty_Total>0)
	{
		Buyer_Off_Spec_Qty_Total = nf.format(dbl_Buyer_Off_Spec_Qty_Total);
	}
	if(dbl_Buyer_Suspension_Qty_Total>0)
	{
		Buyer_Suspension_Qty_Total = nf.format(dbl_Buyer_Suspension_Qty_Total);
	}
	if(dbl_Delv_Failure_Qty_Total>0)
	{
		Delv_Failure_Qty_Total = nf.format(dbl_Delv_Failure_Qty_Total);
	}
	if(dbl_Total_Shortfall_Qty_Total>0)
	{
		Total_Shortfall_Qty_Total = nf.format(dbl_Total_Shortfall_Qty_Total);
	}
	if(dbl_LD_Credit_Payable_Total>0)
	{
		LD_Credit_Payable_Total = nf.format(dbl_LD_Credit_Payable_Total);
	}
	if(dbl_FM_Qty_Total>0)
	{
		FM_Qty_Total = nf.format(dbl_FM_Qty_Total);
	}
	
	String invno = "";
	if(!new_inv_seq_no.equals("")) {
		invno = new_inv_seq_no;
	} else {
		if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		{
			if(hlpl_inv_no.length()>13)
			{
				invno = hlpl_inv_no.substring(0,10)+""+hlpl_inv_no.substring(12);
			}
		}
		else
		{
			if(hlpl_inv_no.length()>13)
			{
				invno = hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
			}
		}
	}
	
	Map dtMap=salesInv.getDtMap();
%>

<body <%if(inv_approved_flag.trim().equalsIgnoreCase("N") || inv_approved_flag.trim().equals("")){%>background="../images/draft_copy.JPG"<%}%>>
<%	if(print_permission.trim().equalsIgnoreCase("N"))
	{	%>
		<script language="javascript" src="../js/mouseclk.js"></script>
<%	}	%>
<form name="rpt_invoice_dtl" method="post" action="../servlet/Frm_Sales_Invoice">

<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
	<tr valign="center">
		<td colspan="18">
			<div align="center">
				&nbsp;
			</div>
	    </td>

	</tr>
	<tr valign="top">
		<td colspan="5" width="25%">
			<div align="left">
				<font size="1px" face="Arial">
					<b><%=contact_Suppl_Name%></b><br>
					Registered Office:<br>
					<%=contact_Suppl_Name%>,<br>
					<%=contact_Suppl_Person_Address%>,<br>
					<%=contact_Suppl_Person_City%>&nbsp;-&nbsp;<%=contact_Suppl_Person_Pin%>
				</font>
			</div>
	    </td>
		<td colspan="8" width="50%">
			<div align="left">
				<font size="2px" face="Arial">
					<b>&nbsp;</b>
				</font>
			</div>
	    </td>
	    <td colspan="5" width="25%">
			<div align="left">
				<font size="1px" face="Arial">
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
	<tr valign="middle">
		<td colspan="18">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="middle">
		<td colspan="18">
			<div align="center">
				<font size="1px" face="Arial"><b>ATTACHMENT 1 - <%if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){%>Properly&nbsp;<%}%>Nominated / Scheduled / Tank Truck  Quantities</b></font>
			</div>
	    </td>
	</tr>
	<tr valign="middle">
		<td colspan="13">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	    <td colspan="3" width="15%">
			<div align="center">
				<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
					<tr valign="center">
						<td><div align="right"><font size="1px" face="Arial"><b>Invoice Date:</b></font></div></td>
					</tr>
					<tr valign="center">
						<td><div align="right"><font size="1px" face="Arial"><b>Payment Due Date:</b></font></div></td>
					</tr>
					<tr valign="center">
						<td><div align="right"><font size="1px" face="Arial"><b> <%if(contract_type.equalsIgnoreCase("R")){%>R-gas&nbsp;<%}%><%if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>Tax&nbsp;<%}%>Invoice Seq No:</b></font></div></td>
					</tr>
					<%--<tr valign="center">
						<td><div align="right"><font size="1px" face="Arial"><b><%=customer_abbr%> Invoice No:</b></font></div></td>
					</tr>--%>
				</table>
			</div>
	    </td>
	    <td colspan="2" width="10%">
			<div align="center">
				<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td><div align="right"><font size="1px" face="Arial"><%=customer_Invoice_DT%></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1px" face="Arial"><%=customer_Invoice_Due_DT%></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1px" face="Arial"><%=invno%></font></div></td>
					</tr>
					<%--<tr>
						<td><div align="right"><font size="1px" face="Arial"><%=customer_inv_no%></font></div></td>
					</tr>--%>
				</table>
			</div>
	    </td>
	</tr>
	<tr valign="center">
<td colspan="8">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	    <td colspan="3"><div align="right"><font size="1.5px" face="Arial"><b>For the Billing Period</b></font>
</div></td>
<td colspan="1"><div align="right">&nbsp;</div></td>
<td colspan="3" ><div align="center">
<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0"><tr>
<td><div align="right"><font size="1.5px" face="Arial"><b><%=customer_Invoice_Start_DT%></b></font></div></td></tr></table>
</div></td><td colspan="1" width="10%">
<div align="center">
<font size="1.5px" face="Arial"><b>to</b></font>
</div></td>
<td colspan="2" width="15%">
<div align="center">
<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
<tr><td><div align="right"><font size="1.5px" face="Arial"><b><%=customer_Invoice_End_DT%></b></font></div></td>
</tr></table></div></td></tr>
	<tr valign="center">
		<td colspan="18">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
</table>
<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0">
			<tr valign="bottom" style="font-weight: bold;">
				<td colspan="1" width="4%">
					<div align="center">
						<font size="1px" face="Arial">Billing<br>Period<br>Day&nbsp;No.</font>
					</div>
			    </td>
			    <td colspan="1" width="9%">
					<div align="center">
						<font size="1px" face="Arial">Gas Day</font>
					</div>
			    </td>
			    <td colspan="1" width="9%">
					<div align="center">
						<font size="1px" face="Arial">Tank-Truck<br>No#</font>
					</div>
			    </td>
			     <td colspan="1" width="11%">
					<div align="center">
						<font size="1px" face="Arial">Loaded<br>Date</font>
					</div>
			    </td>
			    <td colspan="1" width="9%">
					<div align="center">
						<font size="1px" face="Arial">Nominated Qty. <br>(<b>MMBTU</b>)</font>
					</div>
			    </td>

			    <td colspan="1" width="9%">
					<div align="center">
						<font size="1px" face="Arial">Scheduled Qty. <br>(<b>MMBTU</b>)</font>
					</div>
			    </td>
			    <td colspan="1" width="9%">
					<div align="center">
						<font size="1px" face="Arial">Tank-Truck<br>Loaded LNG (<b>&#13221;</b>)</font>
					</div>
			    </td>
			    <td colspan="1" width="10%">
					<div align="center">
						<font size="1px" face="Arial">Tank-Truck<br>Loaded LNG (<b>MMBTU</b>)</font>
					</div>
			    </td>
			</tr>
			
	<%
	String preDt="";int indx=0;
	double nomQty=0,schQty=0,loadedM3=0,loadedMMBTU=0;
	for (int i=0; i<VGas_Day.size(); i++) { 
		String cnt=""+dtMap.get(VGas_Day.elementAt(i));
		
	%>
	<tr valign="center">
				<%if(!VGas_Day.elementAt(i).equals(preDt)){
					indx++;
				%>
					
				<td colspan="1" align="center" rowspan="<%=cnt%>">
					<div alig9="right">
						&nbsp;<font size="1px" face="Arial"><b><%=indx%></b></font>
					</div>
			  	</td>
				<td colspan="1" rowspan="<%=cnt%>" align="center">
					<div alig9="center">
						&nbsp;<font size="1px" face="Arial"><b><%=VGas_Day.elementAt(i)%></b></font>
					</div>
			  	</td>
			  	<%} %>
			  	<td colspan="1">
					<div align="center">
						&nbsp;<font size="1px" face="Arial"><b><%=VTruck_Nm.elementAt(i)%></b></font>
					</div>
			  	</td>
			  		<td colspan="1">
					<div align="center">
						&nbsp;<font size="1px" face="Arial"><b><%=VLoaded_Dt.elementAt(i)%></b></font>
					</div>
			  	</td>
			  	<%if(!VGas_Day.elementAt(i).equals(preDt)){
			  		nomQty+=Double.parseDouble(VNom_Qty.elementAt(i)+"");
			  		schQty+=Double.parseDouble(VSch_Qty.elementAt(i)+"");
				%>
				<td colspan="1" rowspan="<%=cnt%>">
					<div align="right">
						&nbsp;<font size="1px" face="Arial"><b><%=VNom_Qty.elementAt(i)%></b></font>&nbsp;
					</div>
			  	</td>
			  	<td colspan="1" rowspan="<%=cnt%>">
					<div align="right">
						&nbsp;<font size="1px" face="Arial"><b><%=VSch_Qty.elementAt(i)%></b></font>&nbsp;
					</div>
			  	</td>
			  	<%} %>
			  	<td colspan="1">
					<div align="right">
						&nbsp;<font size="1px" face="Arial"><b><%=VLoaded_Vol.elementAt(i)%></b></font>&nbsp;
					</div>
			  	</td>
			  	<td colspan="1">
					<div align="right">
						&nbsp;<font size="1px" face="Arial"><b><%=nf.format(Double.parseDouble(VLoaded_Ene.elementAt(i)+""))%></b></font>&nbsp;
					</div>
			  	</td>
			  	<%
			  		loadedM3+=Double.parseDouble(VLoaded_Vol.elementAt(i)+"");
			  		loadedMMBTU+=Double.parseDouble(VLoaded_Ene.elementAt(i)+"");	
			  	%>
	</TR>
	
	<%
	preDt=VGas_Day.elementAt(i)+"";
	} %>	
	<tr valign="bottom">
		<td colspan="4" align="center">
			<div align="center">
				<font size="1px" face="Arial"><b>TOTAL&nbsp;</b></font>
			</div>
	    </td>
	    <td colspan="1">
			<div align="right">
				<font size="1px" face="Arial"><b><%=nomQty%></b>&nbsp;</font>
			</div>
	    </td>
	    <td colspan="1">
			<div align="right">
				<font size="1px" face="Arial"><b><%=schQty %></b>&nbsp;</font>
			</div>
	    </td>
	    <td colspan="1">
			<div align="right">
				<font size="1px" face="Arial"><b><%=loadedM3 %></b>&nbsp;</font>
			</div>
	    </td>
	    <td colspan="1">
			<div align="right">
				<font size="1px" face="Arial"><b><%=loadedMMBTU %></b>&nbsp;</font>
			</div>
	    </td>
	    
	</tr>	
	
</table>
</form>
</body>
</html>