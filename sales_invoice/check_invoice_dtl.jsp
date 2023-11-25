<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title>Customer Invoice Details</title>

<style>
.decor tr td {
border-left: 2px solid black;
</style>

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
var newWindow3;

//Following Method Is Introduced By Samik Shah On 7th June, 2010 ...
function openInvoiceAtt2Page(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,hlpl_inv_no,customer_inv_no,print_permission,inv_approved_flag)
{
var mapping_id=document.forms[0].mapping_id.value;
//alert(flag);
/*	if(flag)
	{
		if(!newWindow2 || newWindow2.closed)
		{
			newWindow2 = window.open("../advance_payment/rpt_invoice_attachment2_adjust.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&inv_approved_flag="+inv_approved_flag+"&print_permission="+print_permission,"Rpt_InvoiceAtt2","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
		}
		else
		{
			newWindow2.close();
		    newWindow2 = window.open("../advance_payment/rpt_invoice_attachment2_adjust.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&inv_approved_flag="+inv_approved_flag+"&print_permission="+print_permission,"Rpt_InvoiceAtt2","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
		}
	}
	else
	{*/
		if(!newWindow2 || newWindow2.closed)
		{
			newWindow2 = window.open("../sales_invoice/rpt_invoice_att2_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&mapping_id="+mapping_id+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&inv_approved_flag="+inv_approved_flag+"&print_permission="+print_permission,"Rpt_InvoiceAtt2","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
		}
		else
		{
			newWindow2.close();
	 	   newWindow2 = window.open("../sales_invoice/rpt_invoice_att2_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&mapping_id="+mapping_id+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&inv_approved_flag="+inv_approved_flag+"&print_permission="+print_permission,"Rpt_InvoiceAtt2","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
		}
	//}
		
}

function openInvoiceAttAdvanceAdjustmentPage(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,hlpl_inv_no,customer_inv_no,print_permission,inv_approved_flag)
{
var mapping_id=document.forms[0].mapping_id.value;
//alert("HELLO");
	if(!newWindow3 || newWindow3.closed)
	{
		newWindow3 = window.open("../advance_payment/rpt_invoice_advance_adjustment_attachment.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&mapping_id="+mapping_id+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&inv_approved_flag="+inv_approved_flag+"&print_permission="+print_permission,"Rpt_InvoiceAtt_adjust","top=10,left=10,width=850,height=600,scrollbars=1,menubar=1");
	}
	else
	{
		newWindow3.close();
	    newWindow3 = window.open("../advance_payment/rpt_invoice_advance_adjustment_attachment.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&mapping_id="+mapping_id+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&inv_approved_flag="+inv_approved_flag+"&print_permission="+print_permission,"Rpt_InvoiceAtt_adjust","top=10,left=10,width=850,height=600,scrollbars=1,menubar=1");
	}
//alert("Bye");
}

function openInvoiceAttAdvanceAdjustmentPagewithTariff(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,hlpl_inv_no,customer_inv_no,print_permission,inv_approved_flag)
{
var mapping_id=document.forms[0].mapping_id.value;
//alert("HELLO");
	if(!newWindow3 || newWindow3.closed)
	{
		newWindow3 = window.open("../advance_payment/rpt_invoice_advance_adjustment_attachment_att2.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&mapping_id="+mapping_id+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&inv_approved_flag="+inv_approved_flag+"&print_permission="+print_permission,"Rpt_InvoiceAtt_adjust","top=10,left=10,width=850,height=600,scrollbars=1,menubar=1");
	}
	else
	{
		newWindow3.close();
	    newWindow3 = window.open("../advance_payment/rpt_invoice_advance_adjustment_attachment_att2.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&mapping_id="+mapping_id+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&inv_approved_flag="+inv_approved_flag+"&print_permission="+print_permission,"Rpt_InvoiceAtt_adjust","top=10,left=10,width=850,height=600,scrollbars=1,menubar=1");
	}
//alert("Bye");
}

//Following Method Is Introduced By Samik Shah On 7th June, 2010 ...
function openInvoiceAtt1Page(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,hlpl_inv_no,customer_inv_no,print_permission,inv_approved_flag)
{
var mapping_id=document.forms[0].mapping_id.value;
	if(!newWindow || newWindow.closed)
	{
		newWindow = window.open("../sales_invoice/rpt_invoice_att1_dtl.jsp?customer_abbr="+customer_abbr+"&mapping_id="+mapping_id+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&inv_approved_flag="+inv_approved_flag+"&print_permission="+print_permission,"Rpt_InvoiceAtt1","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
	}
	else
	{
		newWindow.close();
	    newWindow = window.open("../sales_invoice/rpt_invoice_att1_dtl.jsp?customer_abbr="+customer_abbr+"&mapping_id="+mapping_id+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&inv_approved_flag="+inv_approved_flag+"&print_permission="+print_permission,"Rpt_InvoiceAtt1","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
	}
}


//Following Function Has Been Introduced By Samik Shah On 21st January, 2011 ...
function doSubmit()
{
	var decission = '';
	
	
	if(document.forms[0].invadjflag.value=='Y')
	{
		var amt=document.forms[0].invadjustmentamt.value;
		var cur=document.forms[0].Currency.value;
		alert("Adjustment of "+amt+" "+cur+" is done.");
		
		var TAX_ADV_ADJ_AMT=document.forms[0].TAX_ADV_ADJ_AMT.value;
		var TAX_ADV_ADJ_FLAG=document.forms[0].TAX_ADV_ADJ_FLAG.value;
		
		if(TAX_ADV_ADJ_FLAG=='Y')
		{
			alert("Tax Adjustment of "+TAX_ADV_ADJ_AMT+" INR is done.");
		}
	}
	
	
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
	
	if(decission!=null && decission!='')
	{
		var a = confirm(""+decission);
		
		if(a)
		{
			document.forms[0].submit();
		}
	}
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

</script>

</head>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.sales_invoice.DataBean_Sales_InvoiceV2" id="salesInv" scope="page"/>   
<jsp:useBean class="com.seipl.hazira.dlng.advance_payment.DataBean_Advance_payment" id="adv" scope="page"/>     
<%
	util.init();

	System.out.println("in check_invoice_dtl");
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
	
	String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
	String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String check_permission = request.getParameter("check_permission")==null?"N":request.getParameter("check_permission");
	String authorize_permission = request.getParameter("authorize_permission")==null?"N":request.getParameter("authorize_permission");
	String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
	String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
	
	String inv_approved_flag = request.getParameter("inv_approved_flag")==null?"":request.getParameter("inv_approved_flag");
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	
	String mapping_id = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");//ADDED FOR LTCORA AND CN 
	System.out.println("mapping_id-->>"+mapping_id);
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
	salesInv.setCustomer_inv_mapping_id(mapping_id); //ADDED FOR LTCORA AND CN
	if(msg.length()<10)
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
	String customer_Invoice_Tax_Amt_INR = salesInv.getCustomer_Invoice_Tax_Amt_INR();
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
	
	//Following (6) String Getter Methods Has Been Defined By Samik Shah On 9th February, 2011 ...
	String total_Offspec_Qty = salesInv.getTotal_Offspec_Qty();
	String offspec_Sales_Rate = salesInv.getOffspec_Sales_Rate();
	String offspec_Flag = salesInv.getOffspec_Flag();
	String offspec_Amt_USD = salesInv.getOffspec_Amt_USD();
	String total_Gas_Delivered = salesInv.getTotal_Gas_Delivered();
	String gas_Delivered_Amt_USD = salesInv.getGas_Delivered_Amt_USD();
	
	//Following (4) Vector Getter Methods Have Been Introduced By Samik Shah On 2nd September, 2011 ...
	Vector vSTAT_CD = salesInv.getVSTAT_CD();
	Vector vSTAT_NM = salesInv.getVSTAT_NM();
	Vector vSTAT_NO = salesInv.getVSTAT_NO();
	Vector vSTAT_EFF_DT = salesInv.getVSTAT_EFF_DT();
	
	//Following String Getter Methods Have Been Defined By Samik Shah ... 
	String remark_1 = salesInv.getRemark_1();
	String remark_2 = salesInv.getRemark_2();
	String remark_3 = salesInv.getRemark_3();
	
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
	
	if(remark_3!=null)
	{
		if(!remark_3.trim().equals(""))
		{
			while((remark_3.indexOf("\n"))!=-1)
			{
				remark_3=(remark_3.substring(0,remark_3.indexOf("\n")-1))+"<br>"+(remark_3.substring(remark_3.indexOf("\n")+1));
			}
		}
	}
	
//	System.out.println("msg = "+msg);
	
	String invno = "";
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
	
	//Following (2) String Getter Methods Has Been Defined By Samik Shah On 15th April, 2011 ...
	String contact_addr_flag = salesInv.getContact_addr_flag();
	String sn_ref_no = salesInv.getSn_ref_no();
	
	//Following (2) String Getter Methods Has Been Defined By Samik Shah On 16th April, 2011 ...
	String contact_Customer_GVAT_NO = salesInv.getContact_Customer_GVAT_NO();
	String contact_Customer_GVAT_DT = salesInv.getContact_Customer_GVAT_DT();
	
	int cnt = 0;
	String adj_no="",tax_adj_no="",total_tax_no="",total_no="";
	
	
	adv.setCallFlag("FetchAdjustmentofInvoiceDetails");
	adv.setInvContractType(contract_type);
	adv.setInvHlplinvseqno(hlpl_inv_seq_no);
	adv.setInvFinancialYear(inv_financial_year);
	adv.setInvbill_period_end_dt(bill_period_end_dt);
	adv.setCustomer_inv_mapping_id(mapping_id); //ADDED FOR LTCORA AND CN
	if(msg.length()<10){
		adv.init();
	}
	
	String invadjustmentamt=adv.getInvadjustmentamt();
	String invgrossamt=adv.getInvgrossamt();
	String invadjremark=adv.getInvadjremark();
	String invadjustcur=adv.getInvadjustcur();
	String invadjflag=adv.getInvadjflag();
	String invexchngrt=adv.getInvexchngrt();
	String invtariffflag=adv.getInvtariff_flag();
	String invdiscountflag=adv.getInvdiscount_flag();
	
	String inv_discount_price=adv.getInvdiscount_price();
	String inv_display_rate=adv.getDisplay_rate();
	String invadjust_gross_usd=adv.getAdjust_gross_amt_usd();
	String invadjust_gross_inr=adv.getAdjust_gross_amt_inr();
	String invtariff_gross_usd=adv.getTariff_gross_amt_usd();
	String invtariff_gross_inr=adv.getTariff_gross_amt_inr();
	String invdiscount_gross_usd=adv.getDiscount_gross_amt_usd();
	String invdiscount_gross_inr=adv.getDiscount_gross_amt_inr();
	String Final_first_gross_amt=adv.getFinal_first_gross_amt();
	String Final_adjust_gross_amt=adv.getFinal_adjust_gross_amt();
	
	String TAX_ADV_ADJ_FLAG=adv.getTAX_ADV_ADJ_FLAG();
	String TAX_ADV_ADJ_AMT=adv.getTAX_ADV_ADJ_AMT();
	String TAX_ADV_ADJ_CUR=adv.getTAX_ADV_ADJ_CUR();
	String TAX_ADV_ADJ_SIGN=adv.getTAX_ADV_ADJ_SIGN();
	String TAX_ADV_ADJ_GROSS_INR=adv.getTAX_ADV_ADJ_GROSS_INR();
	String TAX_ADV_ADJ_GROSS_USD=adv.getTAX_ADV_ADJ_GROSS_USD();
	String TAX_ADV_ADJ_remark=adv.getTAX_ADV_ADJ_remark();
	
	String total_invoice_payable_usd=adv.getTotal_invoice_payable_usd();
	String total_invoice_payable_inr=adv.getTotal_invoice_payable_inr();
	String total_tax_payable=adv.getTotal_tax_payable();
	
	if(invadjremark!=null)
	{
		if(!invadjremark.trim().equals(""))
		{
			while((invadjremark.indexOf("\n"))!=-1)
			{
				invadjremark=(invadjremark.substring(0,invadjremark.indexOf("\n")-1))+"<br>"+(invadjremark.substring(invadjremark.indexOf("\n")+1));
			}
		}
	}
	
	
	
	
	
	int sr_no=0;
	boolean advance_payment_flag=false;
	boolean Tariff_flag=false;
	boolean Discount_flag=false;
	String Currency="USD";
	java.text.NumberFormat nf=new java.text.DecimalFormat("##0.00");
	java.text.NumberFormat nfa=new java.text.DecimalFormat("##0.0000");
	java.text.NumberFormat nf3 = new java.text.DecimalFormat("###,###,###,##0.00");
	String total_tariff_discount="";
	if(invadjflag.equalsIgnoreCase("Y"))
	{
		advance_payment_flag=true;
		gas_Delivered_Amt_USD=Final_first_gross_amt;
	}
	if(invtariffflag.equalsIgnoreCase("Y"))
	{
		Tariff_flag=true;
		Currency="Rupees";
		
	//	System.out.println("SIZE---EROFEB---------"+invexchngrt+"<--->"+gas_Delivered_Amt_USD);
		gas_Delivered_Amt_USD=Final_first_gross_amt;
	//	System.out.println("SIZE---EROFEB---------"+invexchngrt+"<--->"+gas_Delivered_Amt_USD);
	
	if(offspec_Flag.trim().equalsIgnoreCase("Y"))
		{
			offspec_Sales_Rate=""+(Double.parseDouble(offspec_Sales_Rate)*Double.parseDouble(invexchngrt));
			offspec_Amt_USD=""+(Double.parseDouble(offspec_Amt_USD)*Double.parseDouble(invexchngrt));
			offspec_Amt_USD=nf.format(Double.parseDouble(offspec_Amt_USD));
			offspec_Sales_Rate=nfa.format(Double.parseDouble(offspec_Sales_Rate));
		}
		if(invdiscountflag.equalsIgnoreCase("Y"))
		{
			 total_tariff_discount=""+(Double.parseDouble(inv_discount_price)+Double.parseDouble(inv_display_rate));
		}
	}
	if(invdiscountflag.equalsIgnoreCase("Y"))
	{
		Discount_flag=true;
		gas_Delivered_Amt_USD=Final_first_gross_amt;
		total_tariff_discount=""+Double.parseDouble(inv_display_rate);
	}
	String Final_first_gross_amt_inr="";
	String invadjustmentamt_inr="";
	if(!Tariff_flag && invadjustcur.equalsIgnoreCase("1"))
	{
		Final_first_gross_amt_inr=""+(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(Final_first_gross_amt))*Double.parseDouble(invexchngrt));
		Final_first_gross_amt_inr=	nf.format(Double.parseDouble(Final_first_gross_amt_inr));
	}
	if(TAX_ADV_ADJ_FLAG.equalsIgnoreCase("Y"))
	{
		Final_first_gross_amt_inr=""+(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(Final_first_gross_amt))*Double.parseDouble(invexchngrt));
		Final_first_gross_amt_inr=	nf3.format(Double.parseDouble(Final_first_gross_amt_inr));
		
		invadjustmentamt_inr=""+(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(invadjustmentamt))*Double.parseDouble(invexchngrt));
		invadjustmentamt_inr=	nf3.format(Double.parseDouble(invadjustmentamt_inr));
	}
	
		
	if(advance_payment_flag || Tariff_flag || Discount_flag)
	{	
		customer_Invoice_Tax_Amt.clear();
		customer_Invoice_Tax_Rate.clear();
		customer_Invoice_Tax_Amt = adv.getCustomer_Invoice_Tax_Amt();
		customer_Invoice_Tax_Rate = adv.getCustomer_Invoice_Tax_Rate();
		customer_Invoice_Gross_Amt_INR = adv.getCustomer_Invoice_Gross_Amt_INR();
		
	}
	
	if(!advance_payment_flag && !Tariff_flag && !Discount_flag)
	{
		sr_no = 4;
		
		if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		{
			sr_no = 5;
		}
		
		if(offspec_Flag.trim().equalsIgnoreCase("Y"))
		{
			++sr_no;
		}
		
	}
	
	String Adjust_cur="USD"; 
	if(invadjustcur.equalsIgnoreCase("1"))
	{Adjust_cur="INR";	
	}	
	
%>
<body <%if(msg.length()>10) {%> onLoad="doClose('<%=month%>','<%=year%>','<%=bill_cycle%>','<%=msg%>');" <%}%> <%if(inv_approved_flag.trim().equalsIgnoreCase("N") || inv_approved_flag.trim().equals("")){%> background="../images/draft_copy.JPG" <%}%>>

<%	if(print_permission.trim().equalsIgnoreCase("N"))
	{	%>
		<script language="javascript" src="../js/mouseclk.js"></script>
<%	}	%>
<form name="rpt_invoice_dtl" method="post" action="../servlet/Frm_Sales_Invoice">
<%if(!advance_payment_flag && !Tariff_flag && !Discount_flag){ %>
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
						else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
						{	%>
							<b>INVOICE</b>
					<%	}	%>
				</font>
			</div>
		</td>
	</tr>
	<%	if(contract_type.trim().equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		{	%>
			<tr valign="center">
				<td colspan="7">
					<div align="center">
						<font size="3" face="Arial"><b>Issued under Rule-4A of the Service Tax Rules, 1994</b></font>
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
	<%	if(contract_type.equalsIgnoreCase("S"))
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
	<%	}
		else if(contract_type.trim().equalsIgnoreCase("L"))
		{	%>
			<tr valign="center">
				<td colspan="7">
					<div align="center">
						<font size="1px" face="Arial">
							In respect of Letter of Agreement (LOA-<%if(sn_ref_no.trim().equals("")){%><%=sn_no%><%}else{%><%=sn_ref_no%><%}%>) executed on <%=customer_Invoice_SN_Dt%> pursuant to Tender executed on <%=customer_Invoice_FGSA_Dt%>
							<br>
							between <%=contact_Suppl_Name%> and <%=contact_Customer_Name%>
						</font>
					</div>
			    </td>
			</tr>
	<%	}
		else if(contract_type.trim().equalsIgnoreCase("R"))
		{	%>
			<tr valign="center">
				<td colspan="7">
					<div align="center">
						<font size="1px" face="Arial">
							In respect of Regassification Agreement executed on <%=customer_Invoice_FGSA_Dt%> and subsequent side letters 
							<br>
							between <%=contact_Customer_Name%> and <%=contact_Suppl_Name%>
						</font>
					</div>
			    </td>
			</tr>
	<%	}	
		else if(contract_type.trim().equalsIgnoreCase("T"))
		{	%>
			<tr valign="center">
				<td colspan="7">
					<div align="center">
						<font size="1px" face="Arial">
							In respect of LTCORA executed on <%=customer_Invoice_FGSA_Dt%>  
							<br>
							between <%=contact_Customer_Name%> and <%=contact_Suppl_Name%>
						</font>
					</div>
			    </td>
			</tr>
	<%	}	
	else if(contract_type.trim().equalsIgnoreCase("C"))
		{	%>
			<tr valign="center">
				<td colspan="7">
					<div align="center">
						<font size="1px" face="Arial">
							In respect of LTCORA executed on <%=customer_Invoice_FGSA_Dt%> 
							<%if(Double.parseDouble(fgsa_no)<9999) { %>
							& CN-<%=fgsa_no%> executed on <%=customer_Invoice_SN_Dt%>
							<%} %>
							<br>
							between <%=contact_Customer_Name%> and <%=contact_Suppl_Name%>
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
		else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
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
			else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
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
			else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
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
						<td><div align="right"><font size="1.5px" face="Arial"><b>HLPL <%if(contract_type.equalsIgnoreCase("R")){%>R-gas&nbsp;<%}%><%if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")  ){%>LTCORA&nbsp;<%}%>Invoice Seq No:</b></font></div></td>
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
					<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>1&nbsp;<br><br><%}%><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>2&nbsp;<br><br><%}else{%>1&nbsp;<br><br><%}%><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>3&nbsp;<%}else{%>2&nbsp;<%}%><%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>4&nbsp;<%}else if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>3&nbsp;<%}%></font></div></td>
					<td><div align="left"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>&nbsp;Gas&nbsp;Regasified<br><br><%}%><%if(contract_type.equalsIgnoreCase("R")){%>&nbsp;Regasification&nbsp;Tariff&nbsp;(USD/mmbtu)<%}else if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>&nbsp;LTCORA&nbsp;Tariff&nbsp;(USD/mmbtu)<%}else{%>&nbsp;Gas Delivered (USD)<%}%><%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>&nbsp;Offspec QTY<%}%><br><br>&nbsp;Gross Amount (USD)</font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%><a href="#" onClick="openInvoiceAtt1Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;1</a><br><br><%}%><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>&nbsp;<br><br><%}else{%><a href="#" onClick="openInvoiceAtt1Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;1</a><br><br><%}%>&nbsp;</font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>&nbsp;<br><br><%}%>USD<br><br>USD<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>USD<%}%></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%><%=total_Gas_Delivered%>&nbsp;<br><br><%}%><%=total_Gas_Delivered%>&nbsp;<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br><%=total_Offspec_Qty%>&nbsp;<%}%><br><br><%=total_Invoice_Qty%>&nbsp;</font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>&nbsp;<br><br><%}%><%=invoice_Sales_Rate%>&nbsp;<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br><%=offspec_Sales_Rate%>&nbsp;<%}%><br><br>&nbsp;</font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>&nbsp;<br><br><%}%><%=nf.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(gas_Delivered_Amt_USD)))%>&nbsp;<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br><%=offspec_Amt_USD%>&nbsp;<%}%><br><br><%=customer_Invoice_Gross_Amt_USD%>&nbsp;</font></div></td>
				</tr>
				<tr valign="center">
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>
								<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>5&nbsp;<br><br>6&nbsp;<%}else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>4&nbsp;<br><br>5&nbsp;<%}else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>4&nbsp;<br><br>5&nbsp;<%}else{%>3&nbsp;<br><br>4&nbsp;<%}%>
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	++cnt;%>
										<br><%if(i==0){%><br><%=(++sr_no)%><%}%>&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br><%}%><br><br><b><%=(++sr_no)%></b>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="left">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;Exchange Rate<br><br>&nbsp;Gross Amount (Rupees)
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
								<%	}	%>
								<%if(cnt>1){%><br>&nbsp;<b>Total Tax</b><%}%><br><br>&nbsp;<b>Invoice Amount (Rupees)</b><br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								&nbsp;<br><a href="#" onClick="openInvoiceAtt2Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;2</a><br><br>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%>&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<br><br>Rupees
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%>Rupees
								<%	}	%>
								<%if(cnt>1){%><br><b>Rupees</b><%}%><br><br><b>Rupees</b><br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<br><br><%=total_Invoice_Qty%>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%>&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br><%=customer_Invoice_Exchg_Rate%>&nbsp;<br><br>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<br><br><%=customer_Invoice_Gross_Amt_INR%>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%><br><br><b><%=customer_Invoice_Net_Amt_INR%></b>&nbsp;<br>&nbsp;
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
					<%	if(contract_type.trim().equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
						{	%>
							<br><%=remark_3%>
					<%	}	%>
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
<!--	<tr valign="center">-->
<!--				<td colspan="7">-->
<!--					<div align="left">-->
<!--						<font size="1px" face="Arial">-->
<!--							<%//invadjremark%>-->
<!--						</font>-->
<!--					</div>-->
<!--			    </td>-->
<!--			</tr>-->
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
				Checking OK:&nbsp;
				<input type="radio" name="rd" value="Y" onClick="doSubmit();">&nbsp;<b>Yes</b>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rd" value="N" onClick="doSubmit();">&nbsp;<b>No</b>
			</div>
	    </td>
	</tr>
	<tr valign="center">
		<td colspan="7">
			<div align="center">
				<input type="hidden" name="hlpl_inv_no" value="<%=hlpl_inv_no%>">
				<input type="hidden" name="month" value="<%=month%>">
				<input type="hidden" name="year" value="<%=year%>">
				<input type="hidden" name="bill_cycle" value="<%=bill_cycle%>">
				<input type="hidden" name="inv_financial_year" value="<%=inv_financial_year%>">
				<input type="hidden" name="hlpl_inv_seq_no" value="<%=hlpl_inv_seq_no%>">
				<input type="hidden" name="contract_type" value="<%=contract_type%>">
				<input type="hidden" name="option" value="Check_Invoice">
				<input type="hidden" name="check_flag" value="N">
				<input type="hidden" name="write_permission" value="<%=write_permission%>">
	    		<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
	    		<input type="hidden" name="print_permission" value="<%=print_permission%>">
	    		<input type="hidden" name="check_permission" value="<%=check_permission%>">
	    		<input type="hidden" name="authorize_permission" value="<%=authorize_permission%>">
	    		<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
	    		<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
			</div>
	    </td>
	</tr>	
</table>
<%
}else if(TAX_ADV_ADJ_FLAG.equalsIgnoreCase("Y")) {
	 %>

	 <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
	 	<tr valign="center">
	     	<td colspan="7">
	 			<div align="center">
	 				<font size="2" face="Arial">
	 					<b><%=invoice_title%></b><br>
	 				</font>
	 				<font size="4" face="Arial">
	 					<b>Hazira LNG Private Limited</b>
	 				</font>
	 				<br>
	 				<font size="3" face="Arial">
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
	 						else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
	 						{	%>
	 							<b>INVOICE</b>
	 					<%	}	%>
	 				</font>
	 			</div>
	 		</td>
	 	</tr>
	 	<%	if(contract_type.trim().equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
	 		{	%>
	 			<tr valign="center">
	 				<td colspan="7">
	 					<div align="center">
	 						<font size="1px" face="Arial"><b>Issued under Rule-4A of the Service Tax Rules, 1994</b></font>
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
	 	<%	if(contract_type.equalsIgnoreCase("S"))
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
	 	<%	}
	 		else if(contract_type.trim().equalsIgnoreCase("L"))
	 		{	%>
	 			<tr valign="center">
	 				<td colspan="7">
	 					<div align="center">
	 						<font size="1px" face="Arial">
	 							In respect of Letter of Agreement (LOA-<%if(sn_ref_no.trim().equals("")){%><%=sn_no%><%}else{%><%=sn_ref_no%><%}%>) executed on <%=customer_Invoice_SN_Dt%> pursuant to Tender executed on <%=customer_Invoice_FGSA_Dt%>
	 							<br>
	 							between <%=contact_Suppl_Name%> and <%=contact_Customer_Name%>
	 						</font>
	 					</div>
	 			    </td>
	 			</tr>
	 	<%	}
	 		else if(contract_type.trim().equalsIgnoreCase("R"))
	 		{	%>
	 			<tr valign="center">
	 				<td colspan="7">
	 					<div align="center">
	 						<font size="1px" face="Arial">
	 							In respect of Regassification Agreement executed on <%=customer_Invoice_FGSA_Dt%> and subsequent side letters 
	 							<br>
	 							between <%=contact_Customer_Name%> and <%=contact_Suppl_Name%>
	 						</font>
	 					</div>
	 			    </td>
	 			</tr>
	 	<%	}	
	 	else if(contract_type.trim().equalsIgnoreCase("T"))
	 		{	%>
	 			<tr valign="center">
	 				<td colspan="7">
	 					<div align="center">
	 						<font size="1px" face="Arial">
	 							In respect of LTCORA executed on <%=customer_Invoice_FGSA_Dt%>  
	 							<br>
	 							between <%=contact_Customer_Name%> and <%=contact_Suppl_Name%>
	 						</font>
	 					</div>
	 			    </td>
	 			</tr>
	 	<%	}	
	 	else if(contract_type.trim().equalsIgnoreCase("C"))
	 		{	%>
	 			<tr valign="center">
	 				<td colspan="7">
	 					<div align="center">
	 						<font size="1px" face="Arial">
	 							In respect of LTCORA executed on <%=customer_Invoice_FGSA_Dt%> 
	 							<%if(Double.parseDouble(fgsa_no)<9999) { %>
	 							& CN-<%=fgsa_no%> executed on <%=customer_Invoice_SN_Dt%>
	 							<%} %>
	 							<br>
	 							between <%=contact_Customer_Name%> and <%=contact_Suppl_Name%>
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
	 		else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
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
	 			else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
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
	 			else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
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
	 						<td><div align="right"><font size="1.5px" face="Arial"><b>HLPL <%if(contract_type.equalsIgnoreCase("R")){%>R-gas&nbsp;<%}%><%if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")  ){%>LTCORA&nbsp;<%}%>Invoice Seq No:</b></font></div></td>
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
	 			<table width="100%" style="border-right: 2px solid black; border-top: 2px solid black; border-bottom: 2px solid black;" class="decor" border="0" align="center" cellpadding="0" cellspacing="0">
	 				<tr valign="bottom">
	 					<td width="6%" style="border-bottom: 2px solid black;"><div align="center"><font size="1.5px" face="Arial"><b>Sr. No.</b></font></div></td>
	 					<td width="34%" style="border-bottom: 2px solid black;"><div align="center"><font size="1.5px" face="Arial"><b>Item</b></font></div></td>
	 					<td width="10%" style="border-bottom: 2px solid black;"><div align="center"><font size="1.5px" face="Arial"><b>Attachment<br>Reference</b></font></div></td>
	 					<td width="10%" style="border-bottom: 2px solid black;"><div align="center"><font size="1.5px" face="Arial"><b>Currency</b></font></div></td>
	 					<td width="15%" style="border-bottom: 2px solid black;"><div align="center"><font size="1.5px" face="Arial"><b>Quantity<br>(MMBTUS)</b></font></div></td>
	 					<td width="10%" style="border-bottom: 2px solid black;"><div align="center"><font size="1.5px" face="Arial"><b>Rate</b></font></div></td>
	 					<td width="15%" style="border-bottom: 2px solid black;"><div align="center"><font size="1.5px" face="Arial"><b>Amount</b></font></div></td>
	 				</tr>
	 				<tr valign="top">
	 					<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
	 																						<%= ++sr_no%>&nbsp;<br><br>
	 																			<%}%>
	 																			<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
	 																						<%= ++sr_no%>&nbsp;<br><br>
	 																			<%}
	 																			else{%><%= ++sr_no%>&nbsp;<br><br>
	 																			<%}%>
	 																			<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
	 																						<%= ++sr_no%>&nbsp;
	 																			<%}
	 																			else{%><%= ++sr_no%>&nbsp;
	 																			<%}%>
	 																			
	 																			<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	 																					<br><br><%= ++sr_no%>&nbsp;
	 																			<%}
	 																			else if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	 																					<br><br><%= ++sr_no%>&nbsp;
	 																			<%}%>
	 																			<%if(Discount_flag) {%>
	 																				<br><br><%= ++sr_no%>&nbsp;
	 																			<%}%>
	 																			</font></div></td>
	 					<td><div align="left"><font size="1.5px" face="Arial">
	 						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
	 							&nbsp;Gas&nbsp;Regasified<br><br>
	 						<%}%>
	 						<%if(contract_type.equalsIgnoreCase("R")){%>
	 							&nbsp;Regasification&nbsp;Tariff&nbsp;(<%=Currency %>/mmbtu)
	 						<%}
	 						else if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
	 							&nbsp;LTCORA&nbsp;Tariff&nbsp;(<%=Currency %>/mmbtu)
	 						<%}
	 						else{%>
	 							&nbsp;Gas Delivered (<%=Currency %>)
	 						<%}%>
	 						
	 						<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	 							<br><br>&nbsp;Offspec QTY
	 						<%}%>
	 						<!-- Added for Discount Line Item -->
	 						<%if(Discount_flag) {%>
	 						
	 							<br><br>&nbsp;Volume Discount on 
	 							<%if(contract_type.equalsIgnoreCase("R")){%>
	 							Reasification Tarif 
	 							<%}
	 							else if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
	 							&nbsp;LTCORA&nbsp;Tariff&nbsp;
	 							<%}
	 							else{%>
	 							Rate
	 							<%}%>
	 							(<%=Currency %>/mmbtu)					
	 						<%} %>
	 						<!-- End for Discount Line Item -->
	 							<br><br>&nbsp;Gross Amount (<%=Currency %>)</font></div></td>
	 					<td><div align="center"><font size="1.5px" face="Arial">
	 						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
	 							<a href="#" onClick="openInvoiceAtt1Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">
	 								Att&nbsp;1</a><br><br>
	 						<%}%>
	 						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
	 							&nbsp;<br><br>
	 						<%}else{%>
	 							<a href="#" onClick="openInvoiceAtt1Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">
	 								Att&nbsp;1</a><br><br>
	 						<%}%>&nbsp;</font></div>
	 					</td>
	 				
	 					<td><div align="center"><font size="1.5px" face="Arial">
	 						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
	 							&nbsp;<br><br><%}%><%=Currency %><br><br><%=Currency %>
	 						<%if(Discount_flag) {%>
	 							<br><br><%=Currency %>
	 						<%} %>
	 						<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	 							<br><br><%=Currency %>
	 						<%}%></font></div>
	 					</td>
	 						
	 					<td><div align="right"><font size="1.5px" face="Arial">
	 						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
	 							<%=total_Gas_Delivered%>&nbsp;<br><br>
	 						<%}%>
	 						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
	 							<%//total_Gas_Delivered%>&nbsp;
	 						<%} else {%>
	 						<%=total_Gas_Delivered%>&nbsp;
	 						<%} %>
	 						<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	 							<br><br><%=total_Offspec_Qty%>&nbsp;
	 						<%}%>
	 						<%if(Discount_flag) {%>
	 							<br><br>&nbsp;
	 						<%} %>
	 							<br><br><%=total_Invoice_Qty%>&nbsp;</font></div>
	 					</td>
	 								
	 					<td><div align="right"><font size="1.5px" face="Arial">
	 						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
	 							&nbsp;<br><br>
	 						<%}%>
	 						<%if(Discount_flag) {%>
	 						<%=total_tariff_discount%>&nbsp;
	 						<%} else{%>
	 						
	 							<%=inv_display_rate%>&nbsp;
	 						<%} %>
	 							
	 						<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	 							<br><br><%=offspec_Sales_Rate%>&nbsp;
	 						<%}%>
	 						<%if(Discount_flag) {%>
	 						<br><br><%=inv_discount_price %>&nbsp;
	 						<%} %>
	 						
	 						<br><br>&nbsp;
	 						</font></div>
	 						
	 					</td>
	 						
	 					<td><div align="right"><font size="1.5px" face="Arial">
	 						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
	 							&nbsp;<br><br>
	 						<%}%>
	 						<%if(!Discount_flag) {%>
	 						<%//gas_Delivered_Amt_USD%>&nbsp;
	 						<%} %>
	 						<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	 							<br><br><%=offspec_Amt_USD%>&nbsp;
	 						<%}%>
	 						 
	 						<%if(Discount_flag) {%>
	 						<br><br><% 
	 						if(offspec_Flag.trim().equalsIgnoreCase("Y")) {%>
	 						<%=nf.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(Final_first_gross_amt)))%>&nbsp;
	 						
	 						<%} }%>
	 						<br><br><%=nf.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(Final_first_gross_amt)))%>&nbsp;
	 						</font></div>
	 					</td>
	 				</tr>
	 				
	 				<%if(invadjustcur.startsWith("2") || invadjustcur.startsWith("USD")){ %>
	 					<tr valign="top">
	 						<td><div align="right"><font size="1.5px" face="Arial">
	 						<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	 									<%= ++sr_no%>&nbsp;<br>
	 						<%}
	 						else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
	 									<%= ++sr_no%>&nbsp;<br>
	 						<%}
	 						else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	 									<%= ++sr_no%>&nbsp;<br>
	 						<%}
	 						else{%>
	 									<%= ++sr_no%>&nbsp;<br>
	 						<%}%>
	 						
	 						</font></div></td>
	 						<td><div align="left"><font size="1.5px" face="Arial">&nbsp;<%=invadjremark%><br><br></font></div></td>
	 						<td><div align="center"><font size="1.5px" face="Arial"><a href="#" onClick="openInvoiceAttAdvanceAdjustmentPage('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;3</a></font></div></td>
	 						<td><div align="center"><font size="1.5px" face="Arial"><%if(invadjustcur.equalsIgnoreCase("1")){ %>Rupees<%}else{ %>USD <%} %></font></div></td>
	 						<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br></font></div></td>
	 						<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br></font></div></td>
	 						<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%=invadjustmentamt %>&nbsp;</font></div></td>
	 					</tr>
	 					<%} %>
	 					
	 			
	 					<tr valign="center">
	 					<td>
	 						<div align="right">
	 							<font size="1.5px" face="Arial">
	 								&nbsp;<br>
	 								<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	 											<%= ++sr_no%>&nbsp;<br><br>
	 											<%= ++sr_no%>&nbsp;
	 								<%}
	 								else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
	 											<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
	 								<%}
	 								else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	 											<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
	 								<%}
	 								else{%>
	 											<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
	 								<%}%>
	 				<% total_no = ""+sr_no; %>
	 							</font>
	 						</div>
	 					</td>
	 					
	 					<td>
	 						<div align="left">
	 							<font size="1.5px" face="Arial">
	 								&nbsp;<br>&nbsp;Exchange Rate<br><br>&nbsp;Gross Amount (Rupees)
	 							</font>
	 						</div>
	 					</td>
	 				
	 					<td>
	 						<div align="center">
	 							<font size="1.5px" face="Arial">
	 								&nbsp;<br><a href="#" onClick="openInvoiceAtt2Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;2</a><br><br>&nbsp;
	 							</font>
	 						</div>
	 					</td>
	 					<td>
	 						<div align="center">
	 							<font size="1.5px" face="Arial">
	 								&nbsp;<br>&nbsp;<br><br>Rupees
	 							</font>
	 						</div>
	 					</td>
	 					<td>
	 						<div align="right">
	 							<font size="1.5px" face="Arial">
	 								&nbsp;<br>&nbsp;<br><br><%=total_Invoice_Qty%>&nbsp;
	 							</font>
	 						</div>
	 					</td>
	 					<td>
	 						<div align="right">
	 							<font size="1.5px" face="Arial">
	 								&nbsp;<br><%=customer_Invoice_Exchg_Rate%>&nbsp;<br><br>&nbsp;
	 							</font>
	 						</div>
	 					</td>
	 					
	 					<td>
	 						<div align="right">
	 							<font size="1.5px" face="Arial">
	 								&nbsp;<br>&nbsp;<br><br>											
	 											<%=Final_first_gross_amt_inr%>&nbsp;
	 							</font>
	 						</div>
	 					</td>
	 				</tr>
	 				
	 				
	 					
	 					<tr valign="center">
	 						<td>
	 							<div align="right">
	 								<font size="1.5px" face="Arial">
	 									
	 									
	 									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
	 										{	++cnt;%>
	 											<br><%if(i==0){%><%=(++sr_no)%><%}%>&nbsp;
	 									<%	}	%>
	 									<%if(cnt>1){%><br><%}%><br>
	 								</font>
	 							</div>
	 						</td>
	 						<%
	 						total_tax_no = ""+sr_no;%>
	 						<td>
	 							<div align="left">
	 								<font size="1.5px" face="Arial">
	 								
	 								
	 									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
	 										{	
	 										//System.out.println("-----------EREH----------------------------"+customer_Invoice_Tax_Name.elementAt(i));
	 										%>
	 											<br><%if(i==0){%><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
	 									<%	}	%>
	 									<%if(cnt>1){%><br>&nbsp;<b>Total Tax</b><%}%><br>
	 								</font>
	 							</div>
	 						</td>
	 						
	 						
	 						
	 						<td>
	 							<div align="center">
	 								<font size="1.5px" face="Arial">
	 									
	 									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
	 										{	%>
	 											<br><%if(i==0){%><%}%>&nbsp;
	 									<%	}	%>
	 									<%if(cnt>1){%><br>&nbsp;<%}%><br>
	 								</font>
	 							</div>
	 						</td>
	 						<td>
	 							<div align="center">
	 								<font size="1.5px" face="Arial">
	 											
	 									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
	 										{	%>
	 											<br><%if(i==0){%><%}%>Rupees
	 									<%	}	%>
	 									<%if(cnt>1){%><br><b>Rupees</b><%}%><br>
	 								</font>
	 							</div>
	 						</td>
	 						<td>
	 							<div align="right">
	 								<font size="1.5px" face="Arial">
	 								
	 								
	 									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
	 										{	%>
	 											<br><%if(i==0){%><%}%>&nbsp;
	 									<%	}	%>
	 									<%if(cnt>1){%><br>&nbsp;<%}%><br>
	 								</font>
	 							</div>
	 						</td>
	 						<td>
	 							<div align="right">
	 								<font size="1.5px" face="Arial">
	 								
	 									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
	 										{	%>
	 											<br><%if(i==0){%><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
	 									<%	}	%>
	 									<%if(cnt>1){%><br>&nbsp;<%}%><br>
	 								</font>
	 							</div>
	 						</td>
	 						
	 						<td>
	 							<div align="right">
	 								<font size="1.5px" face="Arial">
	 									
	 		
	 									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
	 										{	%>
	 											<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
	 									<%	}	%>
	 									<%if(cnt>1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%><br>
	 								</font>
	 							</div>
	 						</td>
	 					</tr>
	 					
	 					<tr valign="top">
	 						<td><div align="right"><font size="1.5px" face="Arial">
	 						<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	 									<%= ++sr_no%>&nbsp;<br>
	 						<%}
	 						else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
	 									<%= ++sr_no%>&nbsp;<br>
	 						<%}
	 						else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	 									<%= ++sr_no%>&nbsp;<br>
	 						<%}
	 						else{%>
	 									<%= ++sr_no%>&nbsp;<br>
	 						<%}%>
	 						<%adj_no=""+sr_no;%>
	 						</font></div></td>
	 						<td><div align="left"><font size="1.5px" face="Arial">&nbsp;<%=invadjremark%><br><br></font></div></td>
	 						<td><div align="center"><font size="1.5px" face="Arial"><a href="#" onClick="openInvoiceAttAdvanceAdjustmentPage('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;3</a></font></div></td>
	 						<td><div align="center"><font size="1.5px" face="Arial">Rupees</font></div></td>
	 						<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br></font></div></td>
	 						<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br></font></div></td>
	 						<%if(invadjustcur.equalsIgnoreCase("2")) {%>
	 						<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%=invadjustmentamt_inr %>&nbsp;</font></div></td>
	 						<%}else{ %>
	 						<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%=invadjustmentamt %>&nbsp;</font></div></td>
	 						<%} %>
	 						
	 					</tr>
	 					
	 					<tr valign="top" >
	 						<td><div align="right"><font size="1.5px" face="Arial">
	 						<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	 									<%= ++sr_no%>&nbsp;<br>
	 									
	 						<%}
	 						else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
	 									<%= ++sr_no%>&nbsp;<br>
	 						<%}
	 						else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	 									<%= ++sr_no%>&nbsp;<br>
	 						<%}
	 						else{%>
	 									<%= ++sr_no%>&nbsp;<br>
	 						<%}%>
	 						<%tax_adj_no=""+sr_no;%>
	 						</font></div></td>
	 					<td><div align="left"><font size="1.5px" face="Arial">&nbsp;<%=TAX_ADV_ADJ_remark%><br><br></font></div></td>
	 					<td><div align="center"><font size="1.5px" face="Arial"><a href="#" onClick="openInvoiceAttAdvanceAdjustmentPage('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;3</a><br></font></div></td>
	 					<td><div align="center"><font size="1.5px" face="Arial">Rupees<br></font></div></td>
	 					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br></font></div></td>
	 					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br></font></div></td>
	 					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%=TAX_ADV_ADJ_AMT %>&nbsp;</font></div></td>
	 					
	 					</tr>
	 					
	 					
	 					<tr valign="top">
	 					<td>
	 					<div align="right">
	 							<font size="1.5px" face="Arial">
	 								<strong><%= ++sr_no%>&nbsp;</strong></font></div>
	 					</td>
	 					<td>
	 						<font size="1.5px" face="Arial">Total Invoice amount payable (<%=total_no%> - <%=adj_no%>)</font>
	 					</td>
	 					<td>
	 						&nbsp;
	 					</td>
	 					<td align="center">
	 						<font size="1.5px" face="Arial">Rupees</font>
	 					</td>
	 					<td>
	 						&nbsp;
	 					</td>
	 					<td>
	 						&nbsp;
	 					</td>
	 					<td>
	 						<div align="right">
	 								<font size="1.5px" face="Arial"><%=total_invoice_payable_inr%>&nbsp;</font></div>
	 					</td>
	 					</tr>
	 					
	 					<tr valign="top">
	 					<td><div align="right">
	 							<font size="1.5px" face="Arial">
	 								<strong>
	 					<%= ++sr_no%>&nbsp;</strong></font></div>
	 					</td>
	 					<td>
	 						<font size="1.5px" face="Arial">Total Tax (<%=total_tax_no%> - <%=tax_adj_no%>)</font>
	 					</td>
	 					<td>
	 						&nbsp;
	 					</td>
	 					<td  align="center">
	 						<font size="1.5px" face="Arial">Rupees</font>
	 					</td>
	 					<td>
	 						&nbsp;
	 					</td>
	 					<td>
	 						&nbsp;
	 					</td>
	 					<td>
	 						<div align="right">
	 								<font size="1.5px" face="Arial"><%=total_tax_payable %>&nbsp;</font></div>
	 					</td>
	 					</tr>
	 					
	 				
	 				

	 				
	 				
	 				<tr valign="center">
	 					<td>
	 						<div align="right">
	 							<font size="1.5px" face="Arial">
	 								<strong><%=(++sr_no)%></strong>&nbsp;
	 							</font>
	 						</div>
	 					</td>
	 					<td>
	 						<div align="left">
	 							<font size="1.5px" face="Arial">
	 							<strong>Invoice Amount (Rupees)</strong>
	 							</font>
	 						</div>
	 					</td>
	 					
	 					
	 					
	 					<td>
	 						<div align="center">
	 							<font size="1.5px" face="Arial">
	 								&nbsp;
	 							</font>
	 						</div>
	 					</td>
	 					<td>
	 						<div align="center">
	 							<font size="1.5px" face="Arial">
	 								<strong>Rupees</strong>
	 							</font>
	 						</div>
	 					</td>
	 					<td>
	 						<div align="right">
	 							<font size="1.5px" face="Arial">
	 							&nbsp;
	 							</font>
	 						</div>
	 					</td>
	 					<td>
	 						<div align="right">
	 							<font size="1.5px" face="Arial">
	 							&nbsp;
	 							</font>
	 						</div>
	 					</td>
	 					
	 					<td>
	 						<div align="right">
	 						<font size="1.5px" face="Arial">
	 							<strong><%=customer_Invoice_Net_Amt_INR%></strong>&nbsp;
	 							</font>
	 						</div>
	 					</td>
	 				</tr>
	 				
	 				<tr valign="top">
	 					<td style="border-top: 2px solid black;"><div align="right"><font size="1.5px" face="Arial"><b><%=(++sr_no)%>&nbsp;</b></font></div></td>
	 					<td style="border-top: 2px solid black;"><div align="left"><font size="1.5px" face="Arial"><b>&nbsp;Net Amount Payable</b></font></div></td>
	 					<td style="border-top: 2px solid black;"><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
	 					<td style="border-top: 2px solid black;"><div align="center"><font size="1.5px" face="Arial"><b>Rupees</b></font></div></td>
	 					<td style="border-top: 2px solid black;"><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
	 					<td style="border-top: 2px solid black;"><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
	 					<td style="border-top: 2px solid black;"><div align="right"><font size="1.5px" face="Arial"><b><%=customer_Invoice_Net_Amt_INR%>&nbsp;</b></font></div></td>
	 				</tr>
	 					
	 				
	
</table></td></tr>
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
					<%	if(contract_type.trim().equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
						{	%>
							<br><%=remark_3%>
					<%	}	%>
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
<!--	<tr valign="center">-->
<!--				<td colspan="7">-->
<!--					<div align="left">-->
<!--						<font size="1px" face="Arial">-->
<!--							<%//invadjremark%>-->
<!--						</font>-->
<!--					</div>-->
<!--			    </td>-->
<!--	</tr>-->
	
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
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="center">
		<td colspan="7">
			<div align="left">
				<font size="1.5px" face="Arial">
					<b>
					For <%=contact_Suppl_Name%>
					<br><br><br><br>
					Authorised Signatory
					</b>
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
	<tr valign="center">
		<td colspan="7">
			<div align="center">
				Checking OK:&nbsp;
				<input type="radio" name="rd" value="Y" onClick="doSubmit();">&nbsp;<b>Yes</b>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rd" value="N" onClick="doSubmit();">&nbsp;<b>No</b>
			</div>
	    </td>
	</tr>
	<tr valign="center">
		<td colspan="7">
			<div align="center">
				<input type="hidden" name="hlpl_inv_no" value="<%=hlpl_inv_no%>">
				<input type="hidden" name="month" value="<%=month%>">
				<input type="hidden" name="year" value="<%=year%>">
				<input type="hidden" name="bill_cycle" value="<%=bill_cycle%>">
				<input type="hidden" name="inv_financial_year" value="<%=inv_financial_year%>">
				<input type="hidden" name="hlpl_inv_seq_no" value="<%=hlpl_inv_seq_no%>">
				<input type="hidden" name="contract_type" value="<%=contract_type%>">
				<input type="hidden" name="option" value="Check_Invoice">
				<input type="hidden" name="check_flag" value="N">
				<input type="hidden" name="write_permission" value="<%=write_permission%>">
	    		<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
	    		<input type="hidden" name="print_permission" value="<%=print_permission%>">
	    		<input type="hidden" name="check_permission" value="<%=check_permission%>">
	    		<input type="hidden" name="authorize_permission" value="<%=authorize_permission%>">
	    		<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
	    		<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
			</div>
	    </td>
	</tr>	
	</table>	 	
	 <%
}
else
{
//ELSE FOR ADJUSTMENT FLAG	

%>
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
						else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
						{	%>
							<b>INVOICE</b>
					<%	}	%>
				</font>
			</div>
		</td>
	</tr>
	<%	if(contract_type.trim().equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		{	%>
			<tr valign="center">
				<td colspan="7">
					<div align="center">
						<font size="3" face="Arial"><b>Issued under Rule-4A of the Service Tax Rules, 1994</b></font>
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
	<%	if(contract_type.equalsIgnoreCase("S"))
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
	<%	}
		else if(contract_type.trim().equalsIgnoreCase("L"))
		{	%>
			<tr valign="center">
				<td colspan="7">
					<div align="center">
						<font size="1px" face="Arial">
							In respect of Letter of Agreement (LOA-<%if(sn_ref_no.trim().equals("")){%><%=sn_no%><%}else{%><%=sn_ref_no%><%}%>) executed on <%=customer_Invoice_SN_Dt%> pursuant to Tender executed on <%=customer_Invoice_FGSA_Dt%>
							<br>
							between <%=contact_Suppl_Name%> and <%=contact_Customer_Name%>
						</font>
					</div>
			    </td>
			</tr>
	<%	}
		else if(contract_type.trim().equalsIgnoreCase("R"))
		{	%>
			<tr valign="center">
				<td colspan="7">
					<div align="center">
						<font size="1px" face="Arial">
							In respect of Regassification Agreement executed on <%=customer_Invoice_FGSA_Dt%> and subsequent side letters 
							<br>
							between <%=contact_Customer_Name%> and <%=contact_Suppl_Name%>
						</font>
					</div>
			    </td>
			</tr>
	<%	}	else if(contract_type.trim().equalsIgnoreCase("T"))
		{	%>
			<tr valign="center">
				<td colspan="7">
					<div align="center">
						<font size="1px" face="Arial">
							In respect of LTCORA executed on <%=customer_Invoice_FGSA_Dt%>  
							<br>
							between <%=contact_Customer_Name%> and <%=contact_Suppl_Name%>
						</font>
					</div>
			    </td>
			</tr>
	<%	}	
	else if(contract_type.trim().equalsIgnoreCase("C"))
		{	%>
			<tr valign="center">
				<td colspan="7">
					<div align="center">
						<font size="1px" face="Arial">
							In respect of LTCORA executed on <%=customer_Invoice_FGSA_Dt%> 
							<%if(Double.parseDouble(fgsa_no)<9999) { %>
							& CN-<%=fgsa_no%> executed on <%=customer_Invoice_SN_Dt%>
							<%} %>
							<br>
							between <%=contact_Customer_Name%> and <%=contact_Suppl_Name%>
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
		else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
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
			else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
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
			else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
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
						<td><div align="right"><font size="1.5px" face="Arial"><b>HLPL <%if(contract_type.equalsIgnoreCase("R")){%>R-gas&nbsp;<%}%>Invoice Seq No:</b></font></div></td>
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
					<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
																						<%= ++sr_no%>&nbsp;<br><br>
																			<%}%>
																			<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
																						<%= ++sr_no%>&nbsp;<br><br>
																			<%}
																			else{%><%= ++sr_no%>&nbsp;<br><br>
																			<%}%>
																			<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
																						<%= ++sr_no%>&nbsp;
																			<%}
																			else{%><%= ++sr_no%>&nbsp;
																			<%}%>
																			
																			<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
																					<br><br><%= ++sr_no%>&nbsp;
																			<%}
																			else if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
																					<br><br><%= ++sr_no%>&nbsp;
																			<%}%>
																			<%if(Discount_flag) {%>
																				<br><br><%= ++sr_no%>&nbsp;
																			<%}%>
																			</font></div></td>
					<td><div align="left"><font size="1.5px" face="Arial">
						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
							&nbsp;Gas&nbsp;Regasified<br><br>
						<%}%>
						<%if(contract_type.equalsIgnoreCase("R")){%>
							&nbsp;Regasification&nbsp;Tariff&nbsp;(<%=Currency %>/mmbtu)
						<%}else if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
							&nbsp;LTCORA&nbsp;Tariff&nbsp;(<%=Currency %>/mmbtu)
						<%}
						else{%>
							&nbsp;Gas Delivered (<%=Currency %>)
						<%}%>
						
						<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
							<br><br>&nbsp;Offspec QTY
						<%}%>
						<!-- Added for Discount Line Item -->
						<%if(Discount_flag) {%>
						
							<br><br>&nbsp;Volume Discount on 
							<%if(contract_type.equalsIgnoreCase("R")){%>
							Reasification Tarif 
							<%}
							else if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
							&nbsp;LTCORA&nbsp;Tariff&nbsp;
							
							<%}
							else{%>
							Rate
							<%}%>
							(<%=Currency %>/mmbtu)					
						<%} %>
						<!-- End for Discount Line Item -->
							<br><br>&nbsp;Gross Amount (<%=Currency %>)</font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial">
						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
							<a href="#" onClick="openInvoiceAtt1Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">
								Att&nbsp;1</a><br><br>
						<%}%>
						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
							&nbsp;<br><br>
						<%}else{%>
							<a href="#" onClick="openInvoiceAtt1Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">
								Att&nbsp;1</a><br><br>
						<%}%>&nbsp;</font></div>
					</td>
				
					<td><div align="center"><font size="1.5px" face="Arial">
						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
							&nbsp;<br><br><%}%><%=Currency %><br><br><%=Currency %>
						<%if(Discount_flag) {%>
							<br><br><%=Currency %>
						<%} %>
						<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
							<br><br><%=Currency %>
						<%}%></font></div>
					</td>
						
					<td><div align="right"><font size="1.5px" face="Arial">
						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
							<%=total_Gas_Delivered%>&nbsp;<br><br>
						<%}%>
						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
							<%//total_Gas_Delivered%>&nbsp;
						<%} else {%>
						<%=total_Gas_Delivered%>&nbsp;
						<%} %>
						
						<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
							<br><br><%=total_Offspec_Qty%>&nbsp;
						<%}%>
						<%if(Discount_flag) {%>
							<br><br>&nbsp;
						<%} %>
							<br><br><%=total_Invoice_Qty%>&nbsp;</font></div>
					</td>
								
					<td><div align="right"><font size="1.5px" face="Arial">
						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
							&nbsp;<br><br>
						<%}%>
						<%if(Discount_flag) {%>
						<%=total_tariff_discount%>&nbsp;
						<%} else{%>
						
							<%=inv_display_rate%>&nbsp;
						<%} %>
							
						<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
							<br><br><%=offspec_Sales_Rate%>&nbsp;
						<%}%>
						<%if(Discount_flag) {%>
						<br><br><%=inv_discount_price %>&nbsp;
						<%} %>
						
						<br><br>&nbsp;
						</font></div>
						
					</td>
						
					<td><div align="right"><font size="1.5px" face="Arial">
						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
							&nbsp;<br><br>
						<%}%>
						<%if(!Discount_flag) {%>
						<%//gas_Delivered_Amt_USD%>&nbsp;
						<%} %>
						<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
							<br><br><%=offspec_Amt_USD%>&nbsp;
						<%}%>
						 
						<%if(Discount_flag) {%>
						<br><br><% 
						if(offspec_Flag.trim().equalsIgnoreCase("Y")) {%>
						<%=nf.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(Final_first_gross_amt)))%>&nbsp;
						
						<%} }%>
						<br><br><%=nf.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(Final_first_gross_amt)))%>&nbsp;
						</font></div>
					</td>
				</tr>
				
				<%if(!Tariff_flag && advance_payment_flag) {
				if(invadjustcur.startsWith("2")){
				%>
				<tr valign="top">
					<td><div align="right"><font size="1.5px" face="Arial">
					<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
								<%= ++sr_no%>&nbsp;<br><br><br>
								<%= ++sr_no%>&nbsp;
					<%}
					else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
								<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
					<%}
					else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
								<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
					<%}
					else{%>
								<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
					<%}
					
					System.out.println(".............EREH");
					%>
					
					</font></div></td>
					
					
					
					
					<td><div align="left"><font size="1.5px" face="Arial">&nbsp;<%=invadjremark%><br><br>&nbsp;Gross Amount (<%=Currency %>)<br><br></font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial"><a href="#" onClick="openInvoiceAttAdvanceAdjustmentPage('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;3</a><br><br><br>&nbsp;</font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial"><%if(invadjustcur.equalsIgnoreCase("U")){ %><%=Currency %><%}else{ %><%=Currency %> <%} %><br><br><br><%=Currency %></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br>&nbsp;<br><br><br></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br>&nbsp;<br><br><br></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%=invadjustmentamt %>&nbsp;<br><br><br><%=Final_adjust_gross_amt %>&nbsp;<br><br></font></div></td>
				</tr>
				<%}} %>
				
				
				<%if(Tariff_flag && advance_payment_flag) {%>
				
				
				<tr valign="center">
					<td><div align="right"><font size="1.5px" face="Arial">
					<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
								<%= ++sr_no%>&nbsp;<br><br><br>
								<%= ++sr_no%>&nbsp;
					<%}
					else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
								<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
					<%}
					else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
								<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
					<%}
					else{%>
								<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
					<%}%>
					
					</font></div></td>
					
					
					
					
					<td><div align="left"><font size="1.5px" face="Arial">&nbsp;<%=invadjremark%><br><br><br>&nbsp;Gross Amount (<%=Currency %>)<br><br></font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial"><a href="#" onClick="openInvoiceAttAdvanceAdjustmentPagewithTariff('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;2</a><br><br><br>&nbsp;</font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial"><%if(invadjustcur.equalsIgnoreCase("U")){ %><%=Currency %><%}else{ %><%=Currency %>  <%} %><br><br><br><%=Currency %></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br><br>&nbsp;<br><br></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br><br>&nbsp;<br><br></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial"><%=invadjustmentamt %>&nbsp;<br><br><br><%=Final_adjust_gross_amt %>&nbsp;</font></div></td>
				</tr>
				<tr valign="center">
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>
								<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
											
											<%= ++sr_no%>&nbsp;
								<%}
								else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
											<%= ++sr_no%>&nbsp;
								<%}
								else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
											<%= ++sr_no%>&nbsp;
								<%}
								else{%>
											<%= ++sr_no%>&nbsp;
								<%}%>
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	++cnt;%>
										<br><%if(i==0){%><br><%=(++sr_no)%><%}%>&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br><%}%><br><br><strong><%=(++sr_no)%></strong>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="left">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<strong>Gross Amount (Rupees)</strong>
								<%//System.out.println("-----------EREH----------------------------"+customer_Invoice_Tax_Name.size()); %>
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	
									//System.out.println("-----------EREH----------------------------"+customer_Invoice_Tax_Name.elementAt(i));
									%>
										<br><%if(i==0){%><br><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
								<%	}	%>
								<%if(cnt>1){%><br>&nbsp;<b>Total Tax</b><%}%><br><br>&nbsp;<strong>Invoice Amount (Rupees)</strong><br>&nbsp;
							</font>
						</div>
					</td>
					
					
					
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								<br>
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%>&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								&nbsp;<br>Rupees
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%>Rupees
								<%	}	%>
								<%if(cnt>1){%><br><b>Rupees</b><%}%><br><br><strong>Rupees</strong><br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br><%=total_Invoice_Qty%>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%>&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br><%=customer_Invoice_Gross_Amt_INR%>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%><br><br><strong><%=customer_Invoice_Net_Amt_INR%></strong>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
				</tr>
				<%}
					else if(!Tariff_flag && advance_payment_flag && invadjustcur.startsWith("1") ) {
					
				%>
					<tr valign="center">
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>
								<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
											<%= ++sr_no%>&nbsp;<br><br>
											<%= ++sr_no%>&nbsp;
								<%}
								else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
											<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
								<%}
								else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
											<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
								<%}
								else{%>
											<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
								<%}%>
								
							</font>
						</div>
					</td>
					
					<td>
						<div align="left">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;Exchange Rate<br><br>&nbsp;Gross Amount (Rupees)
							</font>
						</div>
					</td>
				
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								&nbsp;<br><a href="#" onClick="openInvoiceAtt2Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;2</a><br><br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<br><br>Rupees
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<br><br><%=total_Invoice_Qty%>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br><%=customer_Invoice_Exchg_Rate%>&nbsp;<br><br>&nbsp;
							</font>
						</div>
					</td>
					
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<br><br>											
											<%=Final_first_gross_amt_inr%>&nbsp;
							</font>
						</div>
					</td>
				</tr>
	
				<tr valign="top">
					<td><div align="right"><font size="1.5px" face="Arial">
					<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
								<%= ++sr_no%>&nbsp;<br><br><br>
								<%= ++sr_no%>&nbsp;
					<%}
					else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
								<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
					<%}
					else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
								<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
					<%}
					else{%>
								<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
					<%}%>
					
					</font></div></td>
					
					
					
					
					<td><div align="left"><font size="1.5px" face="Arial">&nbsp;<%=invadjremark%><br><br>&nbsp;Gross Amount (Rupees)<br><br></font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial"><a href="#" onClick="openInvoiceAttAdvanceAdjustmentPage('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;3</a><br><br><br>&nbsp;</font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial"><%if(invadjustcur.equalsIgnoreCase("2")){ %><%=Currency %><br><br><br><%=Currency %><%}else{ %>Rupees<br><br>Rupees <%} %></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br><br>&nbsp;<br><br></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br><br>&nbsp;<br><br></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%=invadjustmentamt %>&nbsp;<br><br><br><%=Final_adjust_gross_amt %>&nbsp;<br><br></font></div></td>
				</tr>
				
				<tr valign="center">
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								<br>
								<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
											
											<%= ++sr_no%>&nbsp;
								<%}
								else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
											<%= ++sr_no%>&nbsp;
								<%}
								else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
											<%= ++sr_no%>&nbsp;
								<%}
								else{%>
											<%= ++sr_no%>&nbsp;
								<%}%>
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	++cnt;%>
										<br><%if(i==0){%><br><%=(++sr_no)%><%}%>&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br><%}%><br><br><strong><%=(++sr_no)%></strong>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="left">
							<font size="1.5px" face="Arial">
							&nbsp;<br>&nbsp;<strong>Gross Amount (Rupees)</strong>
							
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	
									//System.out.println("-----------EREH----------------------------"+customer_Invoice_Tax_Name.elementAt(i));
									%>
										<br><%if(i==0){%><br><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
								<%	}	%>
								<%if(cnt>1){%><br>&nbsp;<b>Total Tax</b><%}%><br><br>&nbsp;<strong>Invoice Amount (Rupees)</strong><br>&nbsp;
							</font>
						</div>
					</td>
					
					
					
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								&nbsp;<br><br><br>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%>&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								&nbsp;<br>Rupees		
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%>Rupees
								<%	}	%>
								<%if(cnt>1){%><br><b>Rupees</b><%}%><br><br><strong>Rupees</strong><br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
							&nbsp;<br><%=total_Invoice_Qty%>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%>&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
							&nbsp;<br><br><br>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br><%=Final_adjust_gross_amt%>&nbsp;
	
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%><br><br><strong><%=customer_Invoice_Net_Amt_INR%></strong>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
				</tr>
				
				<%}
					else if(Tariff_flag && !advance_payment_flag){%>
					<tr valign="center">
						<td>
							<div align="right">
								<font size="1.5px" face="Arial">
									&nbsp;<br>
									<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
												
												<%= ++sr_no%>&nbsp;
									<%}
									else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
												<%= ++sr_no%>&nbsp;
									<%}
									else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
												<%= ++sr_no%>&nbsp;
									<%}
									else{%>
												<%= ++sr_no%>&nbsp;
									<%}%>
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
										{	++cnt;%>
											<br><%if(i==0){%><br><%=(++sr_no)%><%}%>&nbsp;
									<%	}	%>
									<%if(cnt>1){%><br><%}%><br><br><strong><%=(++sr_no)%></strong>&nbsp;<br>&nbsp;
								</font>
							</div>
						</td>
						<td>
							<div align="left">
								<font size="1.5px" face="Arial">
									&nbsp;<br>&nbsp;<strong>Gross Amount (Rupees)</strong>
									<%//System.out.println("-----------EREH----------------------------"+customer_Invoice_Tax_Name.size()); %>
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
										{	
										//System.out.println("-----------EREH----------------------------"+customer_Invoice_Tax_Name.elementAt(i));
										%>
											<br><%if(i==0){%><br><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
									<%	}	%>
									<%if(cnt>1){%><br>&nbsp;<b>Total Tax</b><%}%><br><br>&nbsp;<strong>Invoice Amount (Rupees)</strong><br>&nbsp;
								</font>
							</div>
						</td>
						
						
						
						<td>
							<div align="center">
								<font size="1.5px" face="Arial">
									&nbsp;<br>&nbsp;
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
										{	%>
											<br><%if(i==0){%><br><%}%>&nbsp;
									<%	}	%>
									<%if(cnt>1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
								</font>
							</div>
						</td>
						<td>
							<div align="center">
								<font size="1.5px" face="Arial">
									&nbsp;<br>Rupees
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
										{	%>
											<br><%if(i==0){%><br><%}%>Rupees
									<%	}	%>
									<%if(cnt>1){%><br><b>Rupees</b><%}%><br><br><strong>Rupees</strong><br>&nbsp;
								</font>
							</div>
						</td>
						<td>
							<div align="right">
								<font size="1.5px" face="Arial">
									&nbsp;<br><%=total_Invoice_Qty%>&nbsp;
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
										{	%>
											<br><%if(i==0){%><br><%}%>&nbsp;
									<%	}	%>
									<%if(cnt>1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
								</font>
							</div>
						</td>
						<td>
							<div align="right">
								<font size="1.5px" face="Arial">
									&nbsp;<br>&nbsp;
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
										{	%>
											<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
									<%	}	%>
									<%if(cnt>1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
								</font>
							</div>
						</td>
						
						<td>
							<div align="right">
								<font size="1.5px" face="Arial">
									&nbsp;<br><%=customer_Invoice_Gross_Amt_INR%>&nbsp;
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
										{	%>
											<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
									<%	}	%>
									<%if(cnt>1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%><br><br><strong><%=customer_Invoice_Net_Amt_INR%></strong>&nbsp;<br>&nbsp;
								</font>
							</div>
						</td>
					</tr>
					<%	}
				else{ %>
					
				<tr valign="center">
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>
								<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
											<%= ++sr_no%>&nbsp;<br><br>
											<%= ++sr_no%>&nbsp;
								<%}
								else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
											<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
								<%}
								else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
											<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
								<%}
								else{%>
											<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
								<%}%>
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	++cnt;%>
										<br><%if(i==0){%><br><%=(++sr_no)%><%}%>&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br><%}%><br><br><strong><%=(++sr_no)%></strong>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="left">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;Exchange Rate<br><br>&nbsp;<strong>Gross Amount (Rupees)</strong>
								<%//System.out.println("-----------EREH----------------------------"+customer_Invoice_Tax_Name.size()); %>
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	
									//System.out.println("-----------EREH----------------------------"+customer_Invoice_Tax_Name.elementAt(i));
									%>
										<br><%if(i==0){%><br><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
								<%	}	%>
								<%if(cnt>1){%><br>&nbsp;<b>Total Tax</b><%}%><br><br>&nbsp;<strong>Invoice Amount (Rupees)</strong><br>&nbsp;
							</font>
						</div>
					</td>
					
					
					
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								&nbsp;<br><a href="#" onClick="openInvoiceAtt2Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;2</a><br><br>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%>&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<br><br>Rupees
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%>Rupees
								<%	}	%>
								<%if(cnt>1){%><br><b>Rupees</b><%}%><br><br><strong>Rupees</strong><br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<br><br><%=total_Invoice_Qty%>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%>&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br><%=customer_Invoice_Exchg_Rate%>&nbsp;<br><br>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<br><br><%=customer_Invoice_Gross_Amt_INR%>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
								<%	}	%>
								<%if(cnt>1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%><br><br><strong><%=customer_Invoice_Net_Amt_INR%></strong>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
				</tr>
				<%	}	%>
				
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
					<%	if(contract_type.trim().equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
						{	%>
							<br><%=remark_3%>
					<%	}	%>
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
<!--	<tr valign="center">-->
<!--				<td colspan="7">-->
<!--					<div align="left">-->
<!--						<font size="1px" face="Arial">-->
<!--							<%//invadjremark%>-->
<!--						</font>-->
<!--					</div>-->
<!--			    </td>-->
<!--			</tr>-->
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
				Checking OK:&nbsp;
				<input type="radio" name="rd" value="Y" onClick="doSubmit();">&nbsp;<b>Yes</b>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="rd" value="N" onClick="doSubmit();">&nbsp;<b>No</b>
			</div>
	    </td>
	</tr>
	<tr valign="center">
		<td colspan="7">
			<div align="center">
				<input type="hidden" name="hlpl_inv_no" value="<%=hlpl_inv_no%>">
				<input type="hidden" name="month" value="<%=month%>">
				<input type="hidden" name="year" value="<%=year%>">
				<input type="hidden" name="bill_cycle" value="<%=bill_cycle%>">
				<input type="hidden" name="inv_financial_year" value="<%=inv_financial_year%>">
				<input type="hidden" name="hlpl_inv_seq_no" value="<%=hlpl_inv_seq_no%>">
				<input type="hidden" name="contract_type" value="<%=contract_type%>">
				<input type="hidden" name="option" value="Check_Invoice">
				<input type="hidden" name="check_flag" value="N">
				<input type="hidden" name="write_permission" value="<%=write_permission%>">
	    		<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
	    		<input type="hidden" name="print_permission" value="<%=print_permission%>">
	    		<input type="hidden" name="check_permission" value="<%=check_permission%>">
	    		<input type="hidden" name="authorize_permission" value="<%=authorize_permission%>">
	    		<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
	    		<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
	    		
	    		
			</div>
	    </td>
	</tr>	
</table>

<%} %>
<input type="hidden" name="invadjflag" value="<%=invadjflag%>">
<input type="hidden" name="invadjustmentamt" value="<%=invadjustmentamt%>">
<input type="hidden" name="Currency" value="<%=Adjust_cur%>">
<input type="hidden" name="adjustmentflag" value="<%=advance_payment_flag%>">
<input type="hidden" name="mapping_id" value="<%=mapping_id%>">
<input type="hidden" name="TAX_ADV_ADJ_AMT" value="<%=TAX_ADV_ADJ_AMT%>">
<input type="hidden" name="TAX_ADV_ADJ_FLAG" value="<%=TAX_ADV_ADJ_FLAG %>">
</form>
</body>
</html>