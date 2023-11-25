<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>

<script type="text/javascript">
var newWindow;
var newWindow2;
var newWindow3;

//Following Method Is Introduced By Samik Shah On 7th June, 2010 ...
function openInvoiceAtt2Page(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,hlpl_inv_no,customer_inv_no,print_permission,inv_approved_flag)
{
var mapping_id=document.forms[0].mapping_id.value;
//alert(flag);
	/*if(flag)
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
			newWindow2 = window.open("rpt_invoice_att2_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&inv_approved_flag="+inv_approved_flag+"&mapping_id="+mapping_id+"&print_permission="+print_permission,"Rpt_InvoiceAtt2","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
		}
		else
		{
			newWindow2.close();
		    newWindow2 = window.open("rpt_invoice_att2_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&inv_approved_flag="+inv_approved_flag+"&mapping_id="+mapping_id+"&print_permission="+print_permission,"Rpt_InvoiceAtt2","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
		}
	//}
}

function openInvoiceAttAdvanceAdjustmentPage(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,hlpl_inv_no,customer_inv_no,print_permission,inv_approved_flag)
{
var mapping_id=document.forms[0].mapping_id.value;
//alert("HELLO");
	if(!newWindow3 || newWindow3.closed)
	{
		newWindow3 = window.open("../advance_payment/rpt_invoice_advance_adjustment_attachment.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&inv_approved_flag="+inv_approved_flag+"&mapping_id="+mapping_id+"&print_permission="+print_permission,"Rpt_InvoiceAtt_adjust","top=10,left=10,width=850,height=600,scrollbars=1,menubar=1");
	}
	else
	{
		newWindow3.close();
	    newWindow3 = window.open("../advance_payment/rpt_invoice_advance_adjustment_attachment.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&inv_approved_flag="+inv_approved_flag+"&mapping_id="+mapping_id+"&print_permission="+print_permission,"Rpt_InvoiceAtt_adjust","top=10,left=10,width=850,height=600,scrollbars=1,menubar=1");
	}
}

//Following Method Is Introduced By Samik Shah On 7th June, 2010 ...
function openInvoiceAtt1Page(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,hlpl_inv_no,customer_inv_no,print_permission,inv_approved_flag)
{
var mapping_id=document.forms[0].mapping_id.value;
	if(!newWindow || newWindow.closed)
	{
		newWindow = window.open("rpt_invoice_att1_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&inv_approved_flag="+inv_approved_flag+"&mapping_id="+mapping_id+"&print_permission="+print_permission,"Rpt_InvoiceAtt1","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
	}
	else
	{
		newWindow.close();
	    newWindow = window.open("rpt_invoice_att1_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&hlpl_inv_no="+hlpl_inv_no+"&customer_inv_no="+customer_inv_no+"&inv_approved_flag="+inv_approved_flag+"&mapping_id="+mapping_id+"&print_permission="+print_permission,"Rpt_InvoiceAtt1","top=10,left=10,width=1050,height=750,scrollbars=1,menubar=1");
	}
}

</script>

</head>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.sales_invoice.DataBean_Sales_InvoiceV2" id="salesInv" scope="page"/>   
<%-- <jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_modulelock" id="modlock" scope="request"/> --%>
<jsp:useBean class="com.seipl.hazira.dlng.advance_payment.DataBean_Advance_payment" id="adv" scope="page"/>   
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
	String contact_addr_flag=request.getParameter("contact_addr_flag")==null?"":request.getParameter("contact_addr_flag");
	String gross_amt_inr=request.getParameter("gross_amt_inr")==null?"":request.getParameter("gross_amt_inr");
	
	
	String inv_dt = request.getParameter("inv_dt")==null?"":request.getParameter("inv_dt");
	String hlpl_inv_no_prev= request.getParameter("hlpl_inv_no_prev")==null?"":request.getParameter("hlpl_inv_no_prev");
	
	//System.out.println("contract_type..."+contract_type);
	
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String inv_approved_flag = request.getParameter("inv_approved_flag")==null?"":request.getParameter("inv_approved_flag");
	
	String mapping_id = request.getParameter("mapping_id")==null?"-1":request.getParameter("mapping_id"); //ADDED FOR LTCORA AND CN
	
//	System.out.println("inv_approved_flag = "+inv_approved_flag);
	
	String inr_flag=request.getParameter("inr_flag")==null?"N":request.getParameter("inr_flag");
	String dis_flag=request.getParameter("dis_flag")==null?"N":request.getParameter("dis_flag");
	String adj_flag=request.getParameter("ad_flag")==null?"N":request.getParameter("ad_flag");
	
	String tcs_app_flag=request.getParameter("tcs_app_flag")==null?"":request.getParameter("tcs_app_flag");
	String tcs_nm=request.getParameter("tcs_nm")==null?"":request.getParameter("tcs_nm");
	String tcs_fact=request.getParameter("tcs_fact")==null?"0":request.getParameter("tcs_fact");
	String tax_tcs_amts=request.getParameter("tax_tcs_amts")==null?"0":request.getParameter("tax_tcs_amts");
// 	System.out.println("tcs_app_flag = "+tcs_app_flag);
	
	String invadjustcur=request.getParameter("invadjustcur")==null?"2":request.getParameter("invadjustcur");
	String tempcompnm = (String)session.getAttribute("tempcompnm")==null?"":(String)session.getAttribute("tempcompnm"); //Hiren_20201112
	
	if(invadjustcur.equalsIgnoreCase("0"))
	{
		invadjustcur="2";
	}
	
	String rbi_ref_flag = "";
	String sbi_tt_selling_flag = "";
	String sbi_tt_buying_flag = "";
	String sbi_avg_tt_selling_buying_flag = "";
	
	if(exchg_rate_cd.equals(rbi_ref_cd)){
		rbi_ref_flag = "checked";
	}else if(exchg_rate_cd.equals(sbi_tt_selling_cd)){
		sbi_tt_selling_flag = "checked";
	}else if(exchg_rate_cd.equals(sbi_tt_buying_cd)){
		sbi_tt_buying_flag = "checked";
	}else if(exchg_rate_cd.equals(sbi_avg_tt_selling_buying_cd)){
		sbi_avg_tt_selling_buying_flag = "checked";
	}
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	String financial_year = "";
	if(Integer.parseInt(month)>3){
		financial_year = ""+year+"-"+(Integer.parseInt(year)+1);		
	}else{
		financial_year = ""+(Integer.parseInt(year)-1)+"-"+year;
	}
// 	System.out.println("sn_no-----"+sn_no);
	
	salesInv.setCallFlag("SALES_INVOICE_REPORT_PREVIEW");
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
	salesInv.setContact_addr_flag(contact_addr_flag);
	salesInv.setCustomer_Invoice_Gross_Amt_INR(gross_amt_inr);
	if(!bill_cycle.equals("0"))
	{
		salesInv.init();
	}
	String contact_Suppl_GSTIN_NO = salesInv.getContact_Suppl_GSTIN_NO(); //RS01062017
	String contact_Suppl_GSTIN_DT = salesInv.getContact_Suppl_GSTIN_DT(); //RS01062017
	String contact_Suppl_State_Code = salesInv.getContact_Suppl_State_Code();
	String contact_Suppl_State = salesInv.getContact_Suppl_State();
	String Rule_remark = salesInv.getRule_remark();
	String sac_code = salesInv.getSac_code();
	String sac_name = salesInv.getSac_name();
	String service_desc = salesInv.getService_desc();
	String contact_Customer_Plant_State = salesInv.getContact_Customer_Plant_State();
	String contact_Customer_State_Code = salesInv.getContact_Customer_State_Code();
	
	//Following String Getter Methods Has Been Defined By Samik Shah On 4th June, 2010 ...
	String contact_Person_Name_And_Designation = salesInv.getContact_Person_Name_And_Designation();
	String contact_Customer_Name = salesInv.getContact_Customer_Name();
	String contact_Customer_Person_Address = salesInv.getContact_Customer_Person_Address();
	String contact_Customer_Person_City = salesInv.getContact_Customer_Person_City();
	String contact_Customer_Person_Pin = salesInv.getContact_Customer_Person_Pin();
	String contact_Customer_GST_NO = salesInv.getContact_Customer_GST_NO();
	String contact_Customer_CST_NO = salesInv.getContact_Customer_CST_NO();
	System.out.println("contact_Customer_CST_NO****"+contact_Customer_CST_NO);
	String contact_Customer_GST_DT = salesInv.getContact_Customer_GST_DT();
	String contact_Customer_CST_DT = salesInv.getContact_Customer_CST_DT();
     
	String contact_Suppl_Name = salesInv.getContact_Suppl_Name();
	String contact_Suppl_Person_Address = salesInv.getContact_Suppl_Person_Address();
	String contact_Suppl_Person_City = salesInv.getContact_Suppl_Person_City();
	String contact_Suppl_Person_Pin = salesInv.getContact_Suppl_Person_Pin();
	String contact_Suppl_GST_NO = salesInv.getContact_Suppl_GST_NO();
	String contact_Suppl_CST_NO = salesInv.getContact_Suppl_CST_NO();
// 	System.out.println("contact_Suppl_CST_NO****"+contact_Suppl_CST_NO);
	String contact_Suppl_GST_DT = salesInv.getContact_Suppl_GST_DT();
	String contact_Suppl_CST_DT = salesInv.getContact_Suppl_CST_DT();
	
	String contact_Suppl_PAN_NO = salesInv.getContact_Suppl_PAN_NO();	//BK20160211
	String contact_Suppl_PAN_DT = salesInv.getContact_Suppl_PAN_DT();	//BK20160211
	
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
	String customer_Invoice_Tax_Amt_INR = ""+salesInv.getCustomer_Invoice_Tax_Amt_INR1();
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
	
	//Following String Getter Methods Have Been Defined By Samik Shah ... 
	String remark_1 = salesInv.getRemark_1();
	String remark_2 = salesInv.getRemark_2();
	String remark_3 = salesInv.getRemark_3();
	
	//Following (4) Vector Getter Methods Have Been Introduced By Samik Shah On 2nd September, 2011 ...
	Vector vSTAT_CD = salesInv.getVSTAT_CD();
	Vector vSTAT_NM = salesInv.getVSTAT_NM();
	Vector vSTAT_NO = salesInv.getVSTAT_NO();
	Vector vSTAT_EFF_DT = salesInv.getVSTAT_EFF_DT();
	boolean tax_gst = salesInv.isTax_gst();
	
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
	
	
	String GST_INVOICE_SEQ_NO = request.getParameter("GST_INVOICE_SEQ_NO")==null?"":request.getParameter("GST_INVOICE_SEQ_NO");
	String invno = "";
	if(!GST_INVOICE_SEQ_NO.equals("")) {
		invno = GST_INVOICE_SEQ_NO;
	} else {
		if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		{
			if(hlpl_inv_no_prev.length()>13)
			{
				invno = hlpl_inv_no_prev.substring(0,10)+""+hlpl_inv_no_prev.substring(12);
			}
		}
		else
		{
			if(hlpl_inv_no_prev.length()>13)
			{
				invno = hlpl_inv_no_prev.substring(0,5)+""+hlpl_inv_no_prev.substring(7,10)+""+hlpl_inv_no_prev.substring(12);
			}
		}
	}
	
	//System.out.println("invno-->>>>"+invno);
	
	//Following (2) String Getter Methods Has Been Defined By Samik Shah On 15th April, 2011 ...
	contact_addr_flag = salesInv.getContact_addr_flag();
	String sn_ref_no = salesInv.getSn_ref_no();
	
	//Following (2) String Getter Methods Has Been Defined By Samik Shah On 16th April, 2011 ...
	String contact_Customer_GVAT_NO = salesInv.getContact_Customer_GVAT_NO();
	String contact_Customer_GVAT_DT = salesInv.getContact_Customer_GVAT_DT();
	
	int cnt = 0;
	
	
	
	adv.setCallFlag("FetchAdjustmentofInvoiceDetails");
	adv.setInvContractType(contract_type);
	adv.setInvHlplinvseqno(hlpl_inv_seq_no);
	adv.setInvFinancialYear(inv_financial_year);
	adv.setInvbill_period_end_dt(bill_period_end_dt);
	adv.setCustomer_inv_mapping_id(mapping_id); //ADDED FOR LTCORA AND CN
	//adv.init();
	
	
	String invadjustmentamt=adv.getInvadjustmentamt();
	String invgrossamt=adv.getInvgrossamt();
	String invadjremark=adv.getInvadjremark();
	//String invadjustcur=adv.getInvadjustcur();
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
	
	if(inr_flag.equals("Y"))
		Tariff_flag=true;
	else 
		Tariff_flag=false;
	if(dis_flag.equals("Y"))
		Discount_flag=true;
	else
		Discount_flag=false;
	if(adj_flag.equals("Y"))
		advance_payment_flag=true;
	else
		advance_payment_flag=false;
	
	String Currency="USD";
	java.text.NumberFormat nf=new java.text.DecimalFormat("##0.00");
	java.text.NumberFormat nfa=new java.text.DecimalFormat("##0.0000");
	java.text.NumberFormat nf3 = new java.text.DecimalFormat("###,###,###,##0.00");
	String total_tariff_discount="";
	//System.out.println("SIZE---EROFEB--11-------"+inv_discount_price+"<--->"+inv_display_rate);
	if(advance_payment_flag)
	{
		//advance_payment_flag=true;
		gas_Delivered_Amt_USD=Final_first_gross_amt;
	}
	if(Tariff_flag)
	{
		//Tariff_flag=true;
		Currency="Rupees";
		
	//	System.out.println("SIZE---EROFEB---------"+invexchngrt+"<--->"+gas_Delivered_Amt_USD);
		gas_Delivered_Amt_USD=Final_first_gross_amt;
		//System.out.println("SIZE---EROFEB---------"+inv_discount_price+"<--->"+inv_display_rate);
	
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
	if(Discount_flag)
	{
		//Discount_flag=true;
		gas_Delivered_Amt_USD=Final_first_gross_amt;
		total_tariff_discount=""+Double.parseDouble(inv_display_rate);
		//System.out.println("total_tariff_discount "+total_tariff_discount);
	}
	String Final_first_gross_amt_inr="";
	if(!Tariff_flag && invadjustcur.equalsIgnoreCase("1"))
	{
		//Final_first_gross_amt_inr=""+(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(Final_first_gross_amt))*Double.parseDouble(invexchngrt));
		//Final_first_gross_amt_inr=	nf3.format(Double.parseDouble(Final_first_gross_amt_inr));
	}
	if(advance_payment_flag || Tariff_flag || Discount_flag)
	{	
		/*customer_Invoice_Tax_Amt.clear();
		customer_Invoice_Tax_Rate.clear();
		customer_Invoice_Tax_Amt = adv.getCustomer_Invoice_Tax_Amt();
		customer_Invoice_Tax_Rate = adv.getCustomer_Invoice_Tax_Rate();
		customer_Invoice_Gross_Amt_INR = adv.getCustomer_Invoice_Gross_Amt_INR();*/
		 customer_Invoice_Tax_Amt_INR=""+salesInv.getCustomer_Invoice_Tax_Amt_INR1();
		 customer_Invoice_Tax_Amt_INR=nf3.format(Double.parseDouble(customer_Invoice_Tax_Amt_INR));
	}
	else{
		customer_Invoice_Tax_Amt_INR=nf3.format(Double.parseDouble(customer_Invoice_Tax_Amt_INR));
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

adv.setCallFlag("FetchForamtedDates");
adv.setInput_date(inv_dt);
adv.init();
inv_dt=adv.getFormatted_Date();

adv.setCallFlag("FetchForamtedDates");
adv.setInput_date(due_dt);
adv.init();
due_dt=adv.getFormatted_Date();

adv.setCallFlag("FetchForamtedDates");
adv.setInput_date(bill_period_start_dt);
adv.init();
bill_period_start_dt=adv.getFormatted_Date();

adv.setCallFlag("FetchForamtedDates");
adv.setInput_date(bill_period_end_dt);
adv.init();
bill_period_end_dt=adv.getFormatted_Date();

boolean date_flag  = salesInv.isDate_flag();
System.out.println("customer_Invoice_Tax_Flag*****"+customer_Invoice_Tax_Flag);
%>

<body <%if(inv_approved_flag.trim().equalsIgnoreCase("N") || inv_approved_flag.trim().equals("")){%>background="../images/draft_copy.JPG"<%}%>>
<%	if(print_permission.trim().equalsIgnoreCase("N"))
	{	%>
		<script type="text/javascript" src="../js/mouseclk.js"></script>
<%	}	%>
<script>
function addCommas(nStr)
{
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	if(x.length==1) {
		x2 = '.00'; 
	} else if(x.length>1) {
		x2 = '.'+x[1];
	} else {
		x2 = '';
	}
	//x2 = x.length > 1 ? '.' + x[1] : '';
	var t = '';var counter = 0;
	for(var j=(x1.length-1);j>=0;j--) {
		if(j==x1.length)
			t = x1[j];
		else {
			if(counter%3==0 && counter!=0) {
				t = x1[j] +','+ t;
			} else {
				t = x1[j] + t;
			}
		}
		counter = counter+1;
	}
	var val = t + x2;
	return val;
}
</script>
<form name="rpt_invoice_dtl" method="post" action="../servlet/Frm_Sales_Invoice">
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
	<tr valign="middle">
    	<td colspan="7">
			<div align="center">
				<font size="2" face="Arial">
					<b>
					<%if(contract_type.equals("C")) { %>
						<%if(tax_gst) { %>
							<%=invoice_title%> FOR RECIPIENT
						<% } else { %>
							<%=invoice_title%>
						<% } %>
					<% } else { %>
						<%=invoice_title%>
					<% } %></b><br>
				</font>
				<font size="4" face="Arial">
					<b><%=contact_Suppl_Name%></b>
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
							<b>TAX INVOICE</b>
					<%	}	%>
				</font>
			</div>
		</td>
	</tr>
	<%	if(contract_type.trim().equalsIgnoreCase("R") || contract_type.trim().equalsIgnoreCase("T") || contract_type.trim().equalsIgnoreCase("C"))
		{	%>
			<tr valign="middle">
				<td colspan="7">
					<div align="center">
						<font size="1px" face="Arial"><b><%=Rule_remark%></b></font>
					</div>
			    </td>
			</tr>
	<%	}	%>
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<%	if(contract_type.equalsIgnoreCase("S"))
		{	%>
			<tr valign="middle">
				<td colspan="7">
					<div align="center">
						<font size="1px" face="Arial">
							In respect of Supply Notice (SN-<%if(sn_ref_no.trim().equals("")){%><%=sn_no%><%}else{%><%=sn_ref_no%><%}%>) executed on <%=customer_Invoice_SN_Dt%> pursuant to Framework LNG Sales Agreement executed on <%=customer_Invoice_FGSA_Dt%>
							<br>
							between <%=contact_Suppl_Name%> and <%=contact_Customer_Name%>
						</font>
					</div>
			    </td>
			</tr>
	<%	}
		else if(contract_type.trim().equalsIgnoreCase("L"))
		{	%>
			<tr valign="middle">
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
			<tr valign="middle">
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
			<tr valign="middle">
				<td colspan="7">
					<div align="center">
						<font size="1px" face="Arial">
							In respect of LNG executed on <%=customer_Invoice_FGSA_Dt%>  
							<br>
							between <%=contact_Customer_Name%> and <%=contact_Suppl_Name%>
						</font>
					</div>
			    </td>
			</tr>
	<%	}	
	else if(contract_type.trim().equalsIgnoreCase("C"))
		{	%>
			<tr valign="middle">
				<td colspan="7">
					<div align="center">
						<font size="1px" face="Arial">
						In respect of LNG executed on <%=customer_Invoice_FGSA_Dt%> 
							<%if(Double.parseDouble(fgsa_no)<9999) { %>
							&amp; CN-<%=fgsa_no%> executed on <%=customer_Invoice_SN_Dt%>
							<%} %>
							<br>
							between <%=contact_Customer_Name%> and <%=contact_Suppl_Name%>
						</font>
					</div>
			    </td>
			</tr>
	<%	}	%> 
	<tr valign="middle">
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
					<span style="font-size: 8px;"><%=tempcompnm %></span> ,<br>
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
	
	<tr valign="top"><td colspan="3"><div><font size="1.5px" face="Arial">
<%if(contract_type.equals("C") || contract_type.equals("R")) {} else if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")) { %>
 <%if(!contact_Suppl_GST_NO.trim().equals("")) { %>
	GST TIN No. : <%=contact_Suppl_GST_NO%>
	<%if(!contact_Suppl_GST_DT.equals("")) {%>
		DT. <%=contact_Suppl_GST_DT%>
	<%} %>
 <br>
<%} if(!contact_Suppl_CST_NO.trim().equals("")) { %>
	CST TIN No. :  <%=contact_Suppl_CST_NO%>
	<%if(!contact_Suppl_CST_DT.equals("")) {%>
		DT. <%=contact_Suppl_CST_DT%>
	<%} %>
<br>
<%} if(!contact_Suppl_PAN_NO.trim().equals("")) { %>
PAN :  <%=contact_Suppl_PAN_NO%>
<br>
<%} } %>
</font></div></td>
 <td colspan="1">&nbsp;</td>
 
 <td colspan="3" ><div><font size="1.5px" face="Arial">
<%if(contract_type.equals("C") || contract_type.equalsIgnoreCase("R")) {} else if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")) { %>
<%if(vSTAT_CD.size()>0){ %>
	<%for(int i=0; i<vSTAT_CD.size(); i++){%>
		<font size="1.5px" face="Arial">
		<% if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) { %>
			PAN  : <%=(String)vSTAT_NO.elementAt(i)%> <br>
		<%}else {
			if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("TAN No.")) { %>  <!-- Hiren_20210204 As discussed with Mahesh Sir -->
				GSTIN No. : <%=(String)vSTAT_NO.elementAt(i)%>
					<%if(!vSTAT_EFF_DT.elementAt(i).equals("")) {%> DT. <%=(String)vSTAT_EFF_DT.elementAt(i)%> <%} %><br>
			<%}else{%>
				<%=(String)vSTAT_NM.elementAt(i)%> : <%=(String)vSTAT_NO.elementAt(i)%>
				<%if(!vSTAT_EFF_DT.elementAt(i).equals("")) {%>
					DT. <%=(String)vSTAT_EFF_DT.elementAt(i)%>
				<%} %>
				<br> 
		<% }} %>  
		</font>
	<%}%>
<%} else { %>
<%if(!contact_Customer_GST_NO.trim().equals("")){%>
	GST TIN No. : <%=contact_Customer_GST_NO%> 
	<%if(!contact_Customer_GST_DT.equals("")) {%>
		DT. <%=contact_Customer_GST_DT%>
	<%} %>
<br>
<%} if(!contact_Customer_CST_NO.trim().equals("")){%>
	CST TIN No. :  <%=contact_Customer_CST_NO%> 
	<%if(!contact_Customer_CST_DT.equals("")) {%>
		DT. <%=contact_Customer_CST_DT%>
	<%} %>
<br>
<%} if(!contact_Suppl_PAN_NO.trim().equals("")){%>
PAN :  <%=contact_Suppl_PAN_NO%> <br>
<%} if(!contact_Customer_GVAT_NO.trim().equals("")){%>
	GVAT TIN No. :  <%=contact_Customer_GVAT_NO%>
	<%if(!contact_Customer_GVAT_DT.equals("")) {%>
		DT. <%=contact_Customer_GVAT_DT%>
	<%} %>

<%} } } %>
</font></div></td>
</tr>
	
	<tr valign="top">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
<%if(!advance_payment_flag && !Tariff_flag && !Discount_flag){ %>

	<tr valign="middle">
		<td colspan="4">
			<div align="center">&nbsp;</div>
	    </td>
	    <td colspan="2" width="25%">
			<div align="center">
				<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b>Invoice Date &nbsp;</b></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b>Payment Due Date &nbsp;</b></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b> <%if(contract_type.equalsIgnoreCase("R")){%>R-gas&nbsp;<%}%><%if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")  ){%>Tax&nbsp;<%}%>Invoice Seq No&nbsp;</b></font></div></td>
					</tr>
					
				</table>
			</div>
	    </td>
	    <td colspan="1" width="15%">
			<div align="center">
				<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b><span id="invdt"></span><%=inv_dt %></b></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b><span id="duedt"></span><%=due_dt %></b></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b><span id="invno"></span><%=invno %></b></font></div></td>
					</tr>
					
				</table>
			</div>
	    </td>
	</tr>
	
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="middle">
		<td colspan="7">
			<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
				<tr valign="bottom">
					<td width="6%"><div align="center"><font size="1.5px" face="Arial"><b>Sr. No.</b></font></div></td>
					<td width="34%"><div align="center"><font size="1.5px" face="Arial"><b>Item</b></font></div></td>
<!-- 					<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Attachment<br>Reference</b></font></div></td> -->
					<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Currency</b></font></div></td>
					<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>Quantity<br>(MMBTUS)</b></font></div></td>
					<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Rate</b></font></div></td>
					<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>Amount</b></font></div></td>
				</tr>
				<tr valign="top">
					<td><div align="right"><font size="1.5px" face="Arial">
					<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>1&nbsp;<br><br><%}%><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
					2&nbsp;<br><br><%}else{%>1&nbsp;<br><br><%}%>
					<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
					3&nbsp;
					<%}else{%>2&nbsp;
					<%}%><%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>4&nbsp;<%}else if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>3&nbsp;<%}%></font></div></td>
					<td><div align="left"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>&nbsp;Natural Gas&nbsp;(Regasified)<br><br><%}%><%if(contract_type.equalsIgnoreCase("R")){%>&nbsp;Regasification&nbsp;Tariff&nbsp;(USD/mmbtu)<%}else if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>&nbsp;LNG&nbsp;Tariff&nbsp;(USD/mmbtu)<%}else{%>&nbsp;LNG delivered<%}%><%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>&nbsp;Offspec QTY<%}%><br><br>&nbsp;Gross Amount (USD)</font></div></td>
<%-- 					<td><div align="center"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>Att&nbsp;1<br><br><%}%><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>&nbsp;<br><br><%}else{%>Att&nbsp;1<br><br><%}%>&nbsp;</font></div></td> --%>
					<td><div align="center"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>&nbsp;<br><br><%}%>USD<br><br><%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>USD<%}%></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%><span id="total_gas"></span>&nbsp;<br><br><%}%><span id="total_gas"></span>&nbsp;<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br><span id="off_spec_qty"></span>&nbsp;<%}%><br><br><span id="qty_1"></span>&nbsp;</font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>&nbsp;<br><br><%}%><span id="salerate"></span>&nbsp;<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br><span id="off_spec_rate"></span>&nbsp;<%}%><br><br>&nbsp;</font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>&nbsp;<br><br><%}%><span id="gas_delivered_usd"></span>&nbsp;<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br><span id="offspec_usd"></span>&nbsp;<%}%><br><br><span id="gross_amt_usd"></span>&nbsp;</font></div></td>
				</tr>
				<script>
				//alert(window.opener.document.forms[0].total_qty.value);
				//document.getElementById("total_gas").innerHTML=window.opener.document.forms[0].total_qty.value;
				document.getElementById("total_gas").innerHTML=window.opener.document.forms[0].total_qty.value;
// 				document.getElementById("qty_1").innerHTML=window.opener.document.forms[0].total_qty.value;
				document.getElementById("gross_amt_usd").innerHTML=addCommas(window.opener.document.forms[0].gross_amt_usd.value);
				document.getElementById("gas_delivered_usd").innerHTML=""; //addCommas(window.opener.document.forms[0].gross_amt_usd.value);
			//	document.getElementById("off_spec_rate").innerHTML=window.opener.document.forms[0].offspec_rate.value;
			//	document.getElementById("offspec_usd").innerHTML=window.opener.document.forms[0].gross_amt_usd.value;
				document.getElementById("salerate").innerHTML=window.opener.document.forms[0].sale_price.value;
			//	document.getElementById("off_spec_qty").innerHTML=window.opener.document.forms[0].offspec_qty.value;
				</script>
				<tr valign="middle">
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>
								<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>5&nbsp;<br><br>6&nbsp;<%}else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>4&nbsp;<br><br>5&nbsp;<%}else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>4&nbsp;<br><br>5&nbsp;<%}else{%>3&nbsp;<br><br>4&nbsp;<%}%>
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	++cnt;%>
										<br><%if(i==0){%><br><%=(++sr_no)%><%}%>&nbsp;
								<%	}	%>
<%-- 								<%if(cnt>=1){%><br><%}%><br><br><strong><%=(++sr_no)%></strong>&nbsp;<br>&nbsp; --%>
							</font>
						
						<%if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){%>
						<%if(tcs_app_flag.equals("Y")){ %>
									<br><br><font size="1.5px" face="Arial"><%=(++sr_no)%>&nbsp;</font>
									
						<%} %>				
					<%}%>
				</div>
					</td>
					<td>
						<div align="left">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;Exchange Rate<br><br>&nbsp;<strong>Gross Amount (Rupees)</strong>
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
								<%	}	%>
<%-- 								<%if(cnt>=1){%><br>&nbsp;<b><%if(tax_gst) { %>Total GST<% } else { %>Total Tax<% } %></b><%}%><br><br>&nbsp;<strong>Invoice Amount</strong><br>&nbsp; --%>
							</font>
							
							
						<%if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){%>
						<%if(tcs_app_flag.equals("Y")){ %>
									<br><br><font size="1.5px" face="Arial">&nbsp;<%=tcs_nm %>&nbsp;</font>
									
						<%} %>				
					<%}%>
						</div>
					</td>
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								<br>Rupees<br><br>Rupees
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
									{	%>
										<br><%if(i==0){%><br><%}%>Rupees
								<%	}	%>
<%-- 								<%if(cnt>=1){%><br><b>Rupees</b><%}%><br><br><strong>Rupees</strong><br>&nbsp; --%>
							</font>
							
							
						<%if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){%>
						<%if(tcs_app_flag.equals("Y")){ %>
									<br><br><font size="1.5px" face="Arial">Rupees</font>
									
						<%} %>				
					<%}%>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<br><br><span id="qty"></span>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
										<br><%if(i==0){%><br><%}%>&nbsp;
								<%	}	%>
<%-- 								<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp; --%>
							</font>
							
						<%if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){%>
						<%if(tcs_app_flag.equals("Y")){ %>
									<br><br><font size="1.5px" face="Arial"><%%>&nbsp;</font>
									
						<%} %>				
					<%}%>
						</div>
					</td>
					
					
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								<br>&nbsp;<span id="exchrate"></span>&nbsp;<br><br>
								<%
								for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
										<%if(i==0){%><br><%}%><br>
										<%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
								<%	}	%>
<%-- 								<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp; --%>
							</font>
							
						<%if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){%>
						<%if(tcs_app_flag.equals("Y")){ %>
									<br><br><font size="1.5px" face="Arial"><%=tcs_fact%> %&nbsp;</font>
									
						<%} %>				
					<%}%>
						</div> 
					</td>
					
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								<br>&nbsp;<br><br><br><span id="gross_amt_inr"></span>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
										<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
								<%	}	%>
<%-- 								<%if(cnt>=1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%> --%>
								<br><br><strong><span id="gross_amt"></span></strong>
<%-- 								<%//=customer_Invoice_Net_Amt_INR%></strong>&nbsp;<br>&nbsp; --%>
							</font>
							
						<%if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){%>
						<%if(tcs_app_flag.equals("Y")){ %>
							<font size="1.5px" face="Arial"><%=tax_tcs_amts%>&nbsp;</font>
						<%} %>				
					<%}%>
							</div>
					</td>
				</tr>
				
						
				
				<script>
// 				document.getElementById("gross_amt").innerHTML=window.opener.document.forms[0].net_amt_inr.value;
				//document.getElementById("gross_amt1").innerHTML=window.opener.document.forms[0].double_final_gross_amt_inr.value;
				document.getElementById("gross_amt_inr").innerHTML=addCommas(window.opener.document.forms[0].double_final_gross_amt_inr.value);
				//alert(window.opener.document.forms[0].exch_rate_val.value);
			
				document.getElementById("exchrate").innerHTML=window.opener.document.forms[0].exch_rate_val.value;
				</script>
				<tr valign="top">
					<td><div align="right"><font size="1.5px" face="Arial"><b><%=(++sr_no)%>&nbsp;</b></font></div></td>
					<td><div align="left"><font size="1.5px" face="Arial"><b>&nbsp;Net Amount Payable</b></font></div></td>
<!-- 					<td><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td> -->
					<td><div align="center"><font size="1.5px" face="Arial"><b>Rupees</b></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial"><b><span id="net_amt_inr"></span><%=customer_Invoice_Net_Amt_INR%>&nbsp;</b></font></div></td>
				</tr>
				
				<script>
				document.getElementById("net_amt_inr").innerHTML=window.opener.document.forms[0].net_amt_inr.value;
				//document.getElementById("exchrate").innerHTML=window.opener.document.forms[0].total_qty.value;
				</script>
			</table>
		</td>
	</tr>
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="middle">
		<td colspan="7">
			<div align="left">
				<font size="1px" face="Arial">
					<%//remark_1%><span id="remark_1"></span>
					<%	if(contract_type.trim().equalsIgnoreCase("R") || contract_type.trim().equalsIgnoreCase("T") || contract_type.trim().equalsIgnoreCase("C"))
						{	%>
							<br><%//remark_3%><span id="remark_3"></span>
					<%	}	%>
				</font>
			</div>
	    </td>
	</tr>
	
	<%	if(contract_type.trim().equalsIgnoreCase("R") || contract_type.trim().equalsIgnoreCase("T") || contract_type.trim().equalsIgnoreCase("C")){	%>
							<script>
				document.getElementById("remark_3").innerHTML=window.opener.document.forms[0].remark_3.value;
							</script>
					<%	}	%>
	<%	//if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
		{	%>
			<tr valign="middle">
				<td colspan="7">
					<div align="center">
						&nbsp;
					</div>
			    </td>
			</tr>
			<tr valign="middle">
				<td colspan="7">
					<div align="left">
						<font size="1px" face="Arial">
							<span id="remark_2"></span>
						</font>
					</div>
			    </td>
			</tr>
	<%	}	%>
	<script>
				document.getElementById("remark_1").innerHTML=window.opener.document.forms[0].remark_1_1.value;
				document.getElementById("remark_2").innerHTML=window.opener.document.forms[0].remark_2.value;
				
	</script>
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="middle">
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
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	
</table>

<%
//ADJUSTMENT
}
else
{
%>
	<tr valign="middle">
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
						<td><div align="right"><font size="1.5px" face="Arial"><b>HLPL <%if(contract_type.equalsIgnoreCase("R")){%>R-gas&nbsp;<%}%><%if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")  ){%>Tax&nbsp;<%}%>Invoice Seq No:</b></font></div></td>
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
						<td><div align="right"><font size="1.5px" face="Arial"><b><span id="invdt"></span><%=inv_dt %></b></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b><span id="duedt"></span><%=due_dt %></b></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b><span id="invno"></span><%=invno%></b></font></div></td>
					</tr>
					<%--<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b><%=customer_inv_no%></b></font></div></td>
					</tr>--%>
				</table>
			</div>
	    </td>
	</tr>
	<%-- <tr valign="middle">
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
	</tr> --%>
	
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="middle">
		<td colspan="7">
			<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
				<tr valign="bottom">
					<td width="6%"><div align="center"><font size="1.5px" face="Arial"><b>Sr. No.</b></font></div></td>
					<td width="34%"><div align="center"><font size="1.5px" face="Arial"><b>Item</b></font></div></td>
<!-- 					<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Attachment<br>Reference</b></font></div></td> -->
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
							&nbsp;Natural Gas&nbsp;(Regasified)<br><br>
						<%}%>
						<%if(contract_type.equalsIgnoreCase("R")){%>
							&nbsp;Regasification&nbsp;Tariff&nbsp;(<%=Currency %>/mmbtu)
						<%}
						else if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
							&nbsp;LNG&nbsp;Tariff&nbsp;(<%=Currency %>/mmbtu)
						<%}
						else{%>
							&nbsp;Natural Gas (Delivered) (<%=Currency %>)
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
							&nbsp;LNG&nbsp;Tariff&nbsp;
							<%}
							else{%>
							Rate
							<%}%>
							(<%=Currency %>/mmbtu)					
						<%} %>
						<!-- End for Discount Line Item -->
							<br><br>&nbsp;Gross Amount (<%=Currency %>)</font></div></td>
					<%-- <td><div align="center"><font size="1.5px" face="Arial">
						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
						Att&nbsp;1<br><br>
						<%}%>
						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
							&nbsp;<br><br>
						<%}else{%>
							Att&nbsp;1<br><br>
						<%}%>&nbsp;</font></div>
					</td> --%>
				
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
							<span id="total_gas"></span><%//total_Gas_Delivered%>&nbsp;<br><br>
						<script>
							document.getElementById("total_gas").innerHTML=window.opener.document.forms[0].total_qty.value;
						</script>
						<%}%>
						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
							<%//total_Gas_Delivered%>&nbsp;
						<%} else {%>
						<span id="total_gas1"></span><%//total_Gas_Delivered%>&nbsp;
						<script>
							document.getElementById("total_gas1").innerHTML=""; //window.opener.document.forms[0].total_qty.value;
						</script>
						<%} %>
						<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
							<br><br><span id="offspec_rate"></span><%//total_Offspec_Qty%>&nbsp;
						<script>
							document.getElementById("offspec_rate").innerHTML=window.opener.document.forms[0].offspec_rate.value;
						</script>
						<%}%>
						<%if(Discount_flag) {%>
							<br><br>&nbsp;
						<%} %>
							<br><br><span id="qty_1"></span><%//total_Invoice_Qty%><span id="qty_1"></span>&nbsp;
								</font></div>
					</td>
								
					<td><div align="right"><font size="1.5px" face="Arial">
						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
							&nbsp;<br><br>
						<%}%>
						<%if(Discount_flag){%>
						<span id="sale_Rate3"></span>&nbsp;
						<script>
							document.getElementById("sale_Rate3").innerHTML=window.opener.document.forms[0].sale_price.value;
						</script>
						<%} else{%>
						<span id="salespriceINR"></span><%//inv_display_rate%>&nbsp;
						<%if(Tariff_flag){%>
						<script>
							document.getElementById("salespriceINR").innerHTML=window.opener.document.forms[0].total_tariff.value;
						</script>
						<%} else{%>
						<script>
							//alert("here1");
							document.getElementById("salespriceINR").innerHTML=window.opener.document.forms[0].sale_price.value;
						</script>
						<%} %>
						<%} %>
							
						<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
							<br><br><%=offspec_Sales_Rate%><span id="off_spec_rate"></span>&nbsp;
						<script>
							document.getElementById("off_spec_rate").innerHTML=window.opener.document.forms[0].offspec_rate.value;
						</script>
						<%}%>
						<%if(Discount_flag){%>
						<br><br><%//inv_discount_price %><span id="discount_value"></span>&nbsp;
						<script>
							document.getElementById("discount_value").innerHTML=round(parseFloat(window.opener.document.forms[0].discount_value.value),4);
						</script>
						<%} %>
						
						<br><br>&nbsp;
						</font></div>
						
					</td>
						<%//System.out.println("advance_payment_flag-"+advance_payment_flag+"..invadjustcur"+invadjustcur); %>
					<td><div align="right"><font size="1.5px" face="Arial">
						<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
							&nbsp;<br><br>
						<%}%>
						<%if(!Discount_flag){%>
						<%//gas_Delivered_Amt_USD%>&nbsp;
						<%} %>
						<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
							<br><br><%=offspec_Amt_USD%>&nbsp;
						<%}%>
						 
						<%if(Discount_flag){%>
						<br><br><% 
						if(offspec_Flag.trim().equalsIgnoreCase("Y")) {%>
						<%=Final_first_gross_amt%><span id="gross_amt_usd1"></span>&nbsp;
						<%if(Tariff_flag){%>
						<script>
							document.getElementById("gross_amt_usd1").innerHTML=""; //window.opener.document.forms[0].grosssalespriceINR.value;
						</script>
						<%} else{%>
						<script>
							document.getElementById("gross_amt_usd1").innerHTML=""; //window.opener.document.forms[0].gross_amt_inr.value;
						</script>
						<%} %>
						<%} }%>
						<br><br><%//Final_first_gross_amt%><span id="gross_amt_usd"></span>&nbsp;
						<%if(Tariff_flag){%>
						<script>
							document.getElementById("gross_amt_usd").innerHTML=addCommas(round(parseFloat(window.opener.document.forms[0].grosssalespriceINR.value),2));
						</script>
						<%} else{%>
						<script>
							document.getElementById("gross_amt_usd").innerHTML=addCommas(round(parseFloat(window.opener.document.forms[0].gross_amt_usd.value),2));
						</script>
						<%} %>
						</font></div>
					</td>
				</tr>
				<script>
				document.getElementById("qty_1").innerHTML=window.opener.document.forms[0].total_qty.value;
				</script>
				<%if(!Tariff_flag && advance_payment_flag) {
				if(invadjustcur.startsWith("2")){ %>
				<tr valign="top">
					<td><div align="right"><font size="1.5px" face="Arial">
					<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
								<%= ++sr_no%>&nbsp;<br><br><br>
								<%= ++sr_no%>&nbsp;
					<%}else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
								<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
					<%}else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
								<%= ++sr_no%>&nbsp;<br><br><br><br><%= ++sr_no%>&nbsp;
					<%}else{%>
								<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
					<%}%>
					</font></div></td>
					<td><div align="left"><font size="1.5px" face="Arial">&nbsp;<span id="adv_inv_no"></span>&nbsp;<%//invadjremark%><br><br>&nbsp;Gross Amount (<%=Currency %>)<br><br></font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial">
<!-- 					Att 3 -->
					<br><br><br>&nbsp;</font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial"><%if(invadjustcur.equalsIgnoreCase("U")){ %><%=Currency %><%}else{ %><%=Currency %> <%} %><br><br><br><%=Currency %></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br>&nbsp;<br><br><br></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br>&nbsp;<br><br><br></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%//invadjustmentamt %><span id="invadjustmentamt"></span>&nbsp;<br><br><br><%//Final_adjust_gross_amt %><span id="Final_adjust_gross_amt"></span>&nbsp;<br><br></font></div></td>
				</tr>
				<script>
				//alert(window.opener.document.forms[0].adjustamt.value);
				var original_inv_no = window.opener.document.forms[0].adv_inv_no.value;
					var dt = window.opener.document.forms[0].adv_inv_dt.value;
					var original_inv_dt = window.opener.document.forms[0].adv_inv_dt.value;
					var chk_mul_adv_inv = window.opener.document.forms[0].chk_mul_adv_inv;
					var adv_inv_val = window.opener.document.forms[0].adv_inv;
					//original_inv_no += " dated "+dt;
					var same_no=original_inv_no; var same_dt=original_inv_dt;
					var diff_no=''; 
					if(chk_mul_adv_inv != null)
					{
						if(chk_mul_adv_inv.value == 'Y'){
							if(adv_inv_val.length == undefined) {
								var data = window.opener.document.getElementById("adv_inv0").value;
								var date = window.opener.document.getElementById("adv_inv_date0").value;
								if(date == dt) { 
									same_no += ","+data;
								} else {
									diff_no = data +" dated "+date;
								}
								same_no += " dated "+same_dt;
								if(diff_no!='') {
									same_no += ", "+diff_no;
								}
							} else {
								for(var i=0;i<parseInt(adv_inv_val.length);i++) {
									var data = window.opener.document.getElementById("adv_inv"+i).value;
									var date = window.opener.document.getElementById("adv_inv_date"+i).value;
									if(date == dt) { 
										same_no += ","+data;
									} else {
										if(diff_no=='') {
											diff_no = data +" dated "+date;
										} else {
											diff_no += ' , '+data +" dated "+date;
										}
									}
								}
								same_no += " dated "+same_dt;
								if(diff_no!='') {
									same_no += ", "+diff_no;
								}
							}
						} else {
							same_no += " dated "+same_dt;
						}	
					} else {
						same_no += " dated "+same_dt;
					}
					original_inv_no = same_no;
					
					var text = 'Adjustment against';
					text += ' Advance Amount';
					text +=' received against Receipt Voucher No. ';
					var rec_remark = window.opener.document.forms[0].rec_remark.value;
					if(rec_remark=='') {
						document.getElementById("adv_inv_no").innerHTML=text+original_inv_no; //window.opener.document.forms[0].adv_inv_no.value;
					} else {
						document.getElementById("adv_inv_no").innerHTML=rec_remark;
					}
// 				document.getElementById("adv_inv_no").innerHTML=original_inv_no; //window.opener.document.forms[0].adv_inv_no.value;
// 				document.getElementById("adv_inv_dt").innerHTML=original_inv_dt; //window.opener.document.forms[0].adv_inv_dt.value;
				</script>
				<%if(contract_type.equalsIgnoreCase("C")) { %>
				<script>
				document.getElementById("invadjustmentamt").innerHTML=window.opener.document.forms[0].adjustamt.value;
				document.getElementById("Final_adjust_gross_amt").innerHTML=window.opener.document.forms[0].gross_amt_inr_adjusted.value;
				</script>
				<% } else { %>
				<script>
				document.getElementById("invadjustmentamt").innerHTML=addCommas(window.opener.document.forms[0].adjustamt.value);
				document.getElementById("Final_adjust_gross_amt").innerHTML=addCommas(window.opener.document.forms[0].gross_amt_inr_adjusted.value);
				</script>
				<% } %>
				
				<%}} %>
				<%if(Tariff_flag && advance_payment_flag) { %>
				<tr valign="middle">
					<td><div align="right"><font size="1.5px" face="Arial">
					<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
								<%= ++sr_no%>&nbsp;<br><br><br>
								<%= ++sr_no%>&nbsp;
					<%}else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
								<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
					<%}else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
								<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
					<%}else{%>
								<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
					<%}%>
					</font></div></td>
					<td><div align="left"><font size="1.5px" face="Arial">&nbsp;<span id="adv_inv_no"></span>&nbsp;<%//invadjremark%><br><br><br>&nbsp;Gross Amount (<%=Currency %>)<br><br></font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial">
<!-- 					Att&nbsp;2 -->
					<br><br><br>&nbsp;</font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial"><%if(invadjustcur.equalsIgnoreCase("U")){ %><%=Currency %><%}else{ %><%=Currency %>  <%} %><br><br><br><%=Currency %></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br>&nbsp;<br><br><br></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br>&nbsp;<br><br><br></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%//invadjustmentamt %><span id="invadjustmentamt"></span><br><br><br><%//Final_adjust_gross_amt %><span id="Final_adjust_gross_amt"></span><br><br></font></div></td>
				</tr>
				<script>
				var original_inv_no = window.opener.document.forms[0].adv_inv_no.value;
				var dt = window.opener.document.forms[0].adv_inv_dt.value;
				var original_inv_dt = window.opener.document.forms[0].adv_inv_dt.value;
				var chk_mul_adv_inv = window.opener.document.forms[0].chk_mul_adv_inv;
				var adv_inv_val = window.opener.document.forms[0].adv_inv;
				//original_inv_no += " dated "+dt;
				var same_no=original_inv_no; var same_dt=original_inv_dt;
				var diff_no=''; 
				if(chk_mul_adv_inv != null)
				{
					if(chk_mul_adv_inv.value == 'Y'){
						if(adv_inv_val.length == undefined) {
							var data = window.opener.document.getElementById("adv_inv0").value;
							var date = window.opener.document.getElementById("adv_inv_date0").value;
							if(date == dt) { 
								same_no += ","+data;
							} else {
								diff_no = data +" dated "+date;
							}
							same_no += " dated "+same_dt;
							if(diff_no!='') {
								same_no += ", "+diff_no;
							}
						} else {
							for(var i=0;i<parseInt(adv_inv_val.length);i++) {
								var data = window.opener.document.getElementById("adv_inv"+i).value;
								var date = window.opener.document.getElementById("adv_inv_date"+i).value;
								if(date == dt) { 
									same_no += ","+data;
								} else {
									if(diff_no=='') {
										diff_no = data +" dated "+date;
									} else {
										diff_no += ' , '+data +" dated "+date;
									}
								}
							}
							same_no += " dated "+same_dt;
							if(diff_no!='') {
								same_no += ", "+diff_no;
							}
						}
					} else {
						same_no += " dated "+same_dt;
					}	
				} else {
					same_no += " dated "+same_dt;
				}
				original_inv_no = same_no;
				
				var text = 'Adjustment against';
				text += ' Advance Amount';
				text +=' received against Receipt Voucher No. ';
				var rec_remark = window.opener.document.forms[0].rec_remark.value;
				if(rec_remark=='') {
					document.getElementById("adv_inv_no").innerHTML=text+original_inv_no; //window.opener.document.forms[0].adv_inv_no.value;
				} else {
					document.getElementById("adv_inv_no").innerHTML=rec_remark;
				}
				
				var a1=window.opener.document.forms[0].adjustamt.value;
				var b1=window.opener.document.forms[0].exch_rate_val.value;
// 				document.getElementById("adv_inv_no").innerHTML=original_inv_no; //window.opener.document.forms[0].adv_inv_no.value;
// 				document.getElementById("adv_inv_dt").innerHTML=original_inv_dt; //window.opener.document.forms[0].adv_inv_dt.value;
				<%if(invadjustcur.equalsIgnoreCase("U") || invadjustcur.equalsIgnoreCase("2")){ %>
				var rachana_usd = window.opener.document.forms[0].gross_amt_usd.value;
				if(rachana_usd == a1) {
					document.getElementById("invadjustmentamt").innerHTML=addCommas(window.opener.document.forms[0].gross_amt_inr.value);
				} else {
					document.getElementById("invadjustmentamt").innerHTML=addCommas(round((parseFloat(a1)*parseFloat(b1)),2));
				}
				<%}else{%>
				document.getElementById("invadjustmentamt").innerHTML=addCommas(round(parseFloat(window.opener.document.forms[0].adjustamt.value),2));
				<%}%>
				document.getElementById("Final_adjust_gross_amt").innerHTML=addCommas(window.opener.document.forms[0].priceINR2.value);
				</script>
				<tr valign="middle">
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>
								<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
											<%= ++sr_no%>&nbsp;
								<%}else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
											<%= ++sr_no%>&nbsp;
								<%}else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
											<%= ++sr_no%>&nbsp;
								<%}else{%>
											<%= ++sr_no%>&nbsp;
								<%}%>
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	++cnt;%>
										<br><%if(i==0){%><br><%=(++sr_no)%><%}%>&nbsp;
								<%}%>
								<%if(cnt>=1){%><br><%}%><br><br><strong><%=(++sr_no)%></strong>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="left">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<strong>Gross Amount (Rupees)</strong>
								<%//System.out.println("-----------EREH-----------"+customer_Invoice_Tax_Name.size()); %>
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
										<br><%if(i==0){%><br><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
								<%	}	%>
								<%if(cnt>=1){%><br>&nbsp;<b><%if(tax_gst) { %>Total GST<% } else { %>Total Tax<% } %></b><%}%><br><br>&nbsp;<strong>Invoice Amount (Rupees)</strong><br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								<br>
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
										<br><%if(i==0){%><br><%}%>&nbsp;
								<%}%>
								<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								&nbsp;<br>Rupees
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
										<br><%if(i==0){%><br><%}%>Rupees
								<%	}	%>
								<%if(cnt>=1){%><br><b>Rupees</b><%}%><br><br><strong>Rupees</strong><br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br><%//total_Invoice_Qty%><span id="qty_4"></span>&nbsp;
								<script>
									//alert(window.opener.document.forms[0].total_qty.value);
									document.getElementById("qty_4").innerHTML=''; //window.opener.document.forms[0].total_qty.value;
									//alert("end");
								</script>
								
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
										<br><%if(i==0){%><br><%}%>&nbsp;
								<%}%>
								<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">&nbsp;<br>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
										<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
								<%}%>
								<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br><%=nf3.format(Double.parseDouble(customer_Invoice_Gross_Amt_INR.replaceAll(",","")))%> &nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
										<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
								<%}%>
								<%if(cnt>=1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%><br><br><strong><%=customer_Invoice_Net_Amt_INR%><span id="gross_amt"></span></strong>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
				</tr>
				<script>
				document.getElementById("gross_amt").innerHTML=window.opener.document.forms[0].net_amt_inr.value;
				document.getElementById("gross_amt_inr1").innerHTML=window.opener.document.forms[0].double_final_gross_amt_inr.value;
				</script>
				<%}else if(!Tariff_flag && advance_payment_flag && invadjustcur.startsWith("1") ){ %>
						<tr valign="middle">
						<td>
							<div align="right">
								<font size="1.5px" face="Arial">
									&nbsp;<br>
									<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
												<%= ++sr_no%>&nbsp;<br><br>
												<%= ++sr_no%>&nbsp;
									<%}else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
												<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
									<%}else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
												<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
									<%}else{%>
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
						<!-- <td>
							<div align="center">
								<font size="1.5px" face="Arial">
									&nbsp;<br>Att&nbsp;2<br><br>&nbsp;
								</font>
							</div>
						</td> -->
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
									&nbsp;<br>&nbsp;<br><br><%//total_Invoice_Qty%><span id="qty_3"></span>&nbsp;
								<script>
									document.getElementById("qty_3").innerHTML=''; //window.opener.document.forms[0].total_qty.value;
								</script>
								</font>
							</div>
						</td>
						<td>
							<div align="right">
								<font size="1.5px" face="Arial">
									&nbsp;<br><%=customer_Invoice_Exchg_Rate%><span id="exchrate"></span>&nbsp;
									<script>
									document.getElementById("exchrate").innerHTML=window.opener.document.forms[0].exch_rate_val.value;
									</script>
							<br><br>&nbsp;
								</font>
							</div>
						</td>
						<td>
							<div align="right">
								<font size="1.5px" face="Arial">
									&nbsp;<br>&nbsp;<br><br>											
												<%//Final_first_gross_amt_inr%><span id="gross_amt_inr"></span>&nbsp;
						<%if(contract_type.equalsIgnoreCase("C")) { %>
						<script>
							document.getElementById("gross_amt_inr").innerHTML=addCommas(round(parseFloat(window.opener.document.forms[0].gross_amt_inr.value),0));
						</script>
						<% } else { %>
						<script>
							document.getElementById("gross_amt_inr").innerHTML=addCommas(round(parseFloat(window.opener.document.forms[0].gross_amt_inr.value),2));
						</script>
						<% } %>
						
								</font>
							</div>
						</td>
					</tr>
					<tr valign="top">
						<td><div align="right"><font size="1.5px" face="Arial">
						<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
									<%= ++sr_no%>&nbsp;<br><br><br>
									<%= ++sr_no%>&nbsp;
						<%}else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
									<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
						<%}else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
									<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
						<%}else{%>
									<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
						<%}%>
						</font></div></td>
						<td><div align="left"><font size="1.5px" face="Arial">&nbsp;<span id="adv_inv_no"></span>&nbsp;<%//invadjremark%><br><br>&nbsp;Gross Amount (Rupees)<br><br></font></div></td>
						<td><div align="center"><font size="1.5px" face="Arial">
<!-- 						Att&nbsp;3 -->
						<br><br><br>&nbsp;</font></div></td>
						<td><div align="center"><font size="1.5px" face="Arial"><%if(invadjustcur.equalsIgnoreCase("2")){ %><%=Currency %><br><br><br><%=Currency %><%}else{ %>Rupees<br><br>Rupees <%} %></font></div></td>
						<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br>&nbsp;<br><br><br></font></div></td>
						<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br>&nbsp;<br><br><br></font></div></td>
						<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%//invadjustmentamt %><span id="invadjustmentamt"></span>&nbsp;<br><br><br><%//Final_adjust_gross_amt %><span id="Final_adjust_gross_amt"></span>&nbsp;<br><br></font></div></td>
					</tr>
					<script>
					var original_inv_no = window.opener.document.forms[0].adv_inv_no.value;
					var dt = window.opener.document.forms[0].adv_inv_dt.value;
					var original_inv_dt = window.opener.document.forms[0].adv_inv_dt.value;
					var chk_mul_adv_inv = window.opener.document.forms[0].chk_mul_adv_inv;
					var adv_inv_val = window.opener.document.forms[0].adv_inv;
					//original_inv_no += " dated "+dt;
					var same_no=original_inv_no; var same_dt=original_inv_dt;
					var diff_no=''; 
					if(chk_mul_adv_inv != null)
					{
						if(chk_mul_adv_inv.value == 'Y'){
							if(adv_inv_val.length == undefined) {
								var data = window.opener.document.getElementById("adv_inv0").value;
								var date = window.opener.document.getElementById("adv_inv_date0").value;
								if(date == dt) { 
									same_no += ","+data;
								} else {
									diff_no = data +" dated "+date;
								}
								same_no += " dated "+same_dt;
								if(diff_no!='') {
									same_no += ", "+diff_no;
								}
							} else {
								for(var i=0;i<parseInt(adv_inv_val.length);i++) {
									var data = window.opener.document.getElementById("adv_inv"+i).value;
									var date = window.opener.document.getElementById("adv_inv_date"+i).value;
									if(date == dt) { 
										same_no += ","+data;
									} else {
										if(diff_no=='') {
											diff_no = data +" dated "+date;
										} else {
											diff_no += ' , '+data +" dated "+date;
										}
									}
								}
								same_no += " dated "+same_dt;
								if(diff_no!='') {
									same_no += ", "+diff_no;
								}
							}
						} else {
							same_no += " dated "+same_dt;
						}	
					} else {
						same_no += " dated "+same_dt;
					}
					original_inv_no = same_no;
					var text = 'Adjustment against';
					text += ' Advance Amount';
					text +=' received against Receipt Voucher No. ';
					var rec_remark = window.opener.document.forms[0].rec_remark.value;
					if(rec_remark=='') {
						document.getElementById("adv_inv_no").innerHTML=text+original_inv_no; //window.opener.document.forms[0].adv_inv_no.value;
					} else {
						document.getElementById("adv_inv_no").innerHTML=rec_remark;
					}
// 					document.getElementById("adv_inv_no").innerHTML=original_inv_no; //window.opener.document.forms[0].adv_inv_no.value;
// 					document.getElementById("adv_inv_dt").innerHTML=original_inv_dt; //window.opener.document.forms[0].adv_inv_dt.value;
					document.getElementById("invadjustmentamt").innerHTML=addCommas(round(parseFloat(window.opener.document.forms[0].adjustamt.value),2));
					</script>
					<%if(contract_type.equalsIgnoreCase("C")) { %>
					<script>
						
						document.getElementById("Final_adjust_gross_amt").innerHTML=addCommas(round(parseFloat(window.opener.document.forms[0].priceINR2.value),0));
					</script>
					<% } else { %>
					<script>
						document.getElementById("Final_adjust_gross_amt").innerHTML=addCommas(round(parseFloat(window.opener.document.forms[0].priceINR2.value),2));
					</script>
					<% } %>
					<tr valign="middle">
						<td>
							<div align="right">
								<font size="1.5px" face="Arial">
									<br>
									<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
												
												<%= ++sr_no%>&nbsp;
									<%}else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
												<%= ++sr_no%>&nbsp;
									<%}else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
												<%= ++sr_no%>&nbsp;
									<%}else{%>
												<%= ++sr_no%>&nbsp;
									<%}%>
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	++cnt;%>
											<br><%if(i==0){%><br><%=(++sr_no)%><%}%>&nbsp;
									<%	}	%>
									<%if(cnt>=1){%><br><%}%><br><br><strong><%=(++sr_no)%></strong>&nbsp;<br>&nbsp;
								</font>
							</div>
						</td>
						<td>
							<div align="left">
								<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<strong>Gross Amount (Rupees)</strong>
								
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
											<br><%if(i==0){%><br><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
									<%	}	%>
									<%if(cnt>=1){%><br>&nbsp;<b><%if(tax_gst) { %>Total GST<% } else { %>Total Tax<% } %></b><%}%><br><br>&nbsp;<strong>Invoice Amount (Rupees)</strong><br>&nbsp;
								</font>
							</div>
						</td>
						<td>
							<div align="center">
								<font size="1.5px" face="Arial">
									&nbsp;<br><br><br>&nbsp;
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
											<br><%if(i==0){%><br><%}%>&nbsp;
									<%	}	%>
									<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
								</font>
							</div>
						</td>
						<td>
							<div align="center">
								<font size="1.5px" face="Arial">
									&nbsp;<br>Rupees		
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
											<br><%if(i==0){%><br><%}%>Rupees
									<%	}	%>
									<%if(cnt>=1){%><br><b>Rupees</b><%}%><br><br><strong>Rupees</strong><br>&nbsp;
								</font>
							</div>
						</td>
						<td>
							<div align="right">
								<font size="1.5px" face="Arial">
								&nbsp;<br><%//total_Invoice_Qty%><span id="qty_2"></span>&nbsp;
								<script>
									//alert(window.opener.document.forms[0].total_qty.value);
									document.getElementById("qty_2").innerHTML=''; //window.opener.document.forms[0].total_qty.value;
									//alert("end");
								</script>
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
											<br><%if(i==0){%><br><%}%>&nbsp;
									<%	}	%>
									<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
								</font>
							</div>
						</td>
						<td>
							<div align="right">
								<font size="1.5px" face="Arial">
								&nbsp;<br><br><br>&nbsp;
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
											<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
									<%}%>
									<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
								</font>
							</div>
						</td>
						
						<td>
							<div align="right">
								<font size="1.5px" face="Arial">
									&nbsp;<br><%//Final_adjust_gross_amt%><span id="gross_amt_inr1"></span>&nbsp;
		
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
											<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
									<%}%>
									<%if(cnt>=1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%><br><br><strong><%=customer_Invoice_Net_Amt_INR%><span id="gross_amt"></span></strong>&nbsp;<br>&nbsp;
								</font>
							</div>
						</td>
					</tr>
					<%if(contract_type.equalsIgnoreCase("C")) { %>
					<script>
					document.getElementById("gross_amt").innerHTML=addCommas(round(parseFloat(window.opener.document.forms[0].net_amt_inr.value),0));
					document.getElementById("gross_amt_inr1").innerHTML=addCommas(round(parseFloat(window.opener.document.forms[0].double_final_gross_amt_inr.value),0));
					</script>
					<% } else { %>
					<script>
					document.getElementById("gross_amt").innerHTML=window.opener.document.forms[0].net_amt_inr.value;
					document.getElementById("gross_amt_inr1").innerHTML=addCommas(round(parseFloat(window.opener.document.forms[0].double_final_gross_amt_inr.value),2));
					</script>
					<% } %>
					
					<%}
				else if(Tariff_flag && !advance_payment_flag){//System.out.println("INSIDE ELSE");%>
				
					<tr valign="middle">
						<td>
							<div align="right">
								<font size="1.5px" face="Arial">
									&nbsp;
									<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
												<%= ++sr_no%>&nbsp;
									<%}else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>	
												<%= ++sr_no%>&nbsp;
									<%}else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
												<%= ++sr_no%>&nbsp;
									<%}else{%>
												<%= ++sr_no%>&nbsp;
									<%}%>
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	++cnt;%>
											<br><%if(i==0){%><br><%=(++sr_no)%><%}%>&nbsp;
									<%	}	%>
									<%if(cnt>=1){%><br><%}%><br><br><strong><%=(++sr_no)%></strong>&nbsp;<br>&nbsp;
								</font>
							</div>
						</td>
						<td>
							<div align="left">
								<font size="1.5px" face="Arial">
									&nbsp;&nbsp;<strong>Gross Amount (Rupees)</strong>
									<%//System.out.println("-----------EREH----------------------------"+customer_Invoice_Tax_Name.size()); %>
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
											<br><%if(i==0){%><br><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
									<%}%>
									<%if(cnt>=1){%><br>&nbsp;<b><%if(tax_gst) { %>Total GST<% } else { %>Total Tax<% } %></b><%}%><br><br>&nbsp;<strong>Invoice Amount (Rupees)</strong><br>&nbsp;
								</font>
							</div>
						</td>
						<td>
							<div align="center">
								<font size="1.5px" face="Arial">
									&nbsp;&nbsp;
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
											<br><%if(i==0){%><br><%}%>&nbsp;
									<%}%>
									<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
								</font>
							</div>
						</td>
						<td>
							<div align="center">
								<font size="1.5px" face="Arial">
									&nbsp;Rupees
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
											<br><%if(i==0){%><br><%}%>Rupees
									<%}%>
									<%if(cnt>=1){%><br><b>Rupees</b><%}%><br><br><strong>Rupees</strong><br>&nbsp;
								</font>
							</div>
						</td>
						
						<td>
							<div align="right">
								<font size="1.5px" face="Arial">
									&nbsp;<%//total_Invoice_Qty%><span id="qty_6"></span>&nbsp;
									<script>
										document.getElementById("qty_6").innerHTML=''; //window.opener.document.forms[0].total_qty.value;
									</script>
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
											<br><%if(i==0){%><br><%}%>&nbsp;
									<%}%>
									<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
								</font>
							</div>
						</td>
						<td>
							
							<div align="right">
								<font size="1.5px" face="Arial">&nbsp;
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
											<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
									<%	}	%>
									<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
								</font>
							</div>
						</td>
						<td>
							<div align="right">
								<font size="1.5px" face="Arial">
									<span id="gross_amt_inr1"></span>&nbsp;
									<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
											<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
									<%}%>
									<%if(cnt>=1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%><br><br><b><span id="gross_amt"></span></b>&nbsp;<br>&nbsp;
								</font>
							</div>
						</td>
					</tr>
					<script>
					document.getElementById("gross_amt").innerHTML=window.opener.document.forms[0].net_amt_inr.value;
					document.getElementById("gross_amt_inr1").innerHTML=addCommas(round(parseFloat(window.opener.document.forms[0].double_final_gross_amt_inr.value),0));
					</script>
					<%	}else{ %>
				<tr valign="middle">
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
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	++cnt;%>
										<br><%if(i==0){%><br><%=(++sr_no)%><%}%>&nbsp;
								<%	}	%>
								<%if(cnt>=1){%><br><%}%><br><br><strong><%=(++sr_no)%></strong>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					
					<td>
						<div align="left">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;Exchange Rate<br><br>&nbsp;<strong>Gross Amount (Rupees)</strong>
								<%//System.out.println("-----------EREH----------------------------"+customer_Invoice_Tax_Name.size()); %>
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	
									//System.out.println("-CBA----------EREH----------------------------"+customer_Invoice_Tax_Name.elementAt(i));
									%>
										<br><%if(i==0){%><br><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
								<%	}	%>
								<%if(cnt>=1){%><br>&nbsp;<b><%if(tax_gst) { %>Total GST<% } else { %>Total Tax<% } %></b><%}%><br><br>&nbsp;<strong>Invoice Amount (Rupees)</strong><br>&nbsp;
							</font>
						</div>
					</td>
					
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								&nbsp;<br>Att2 <br><br>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
										<br><%if(i==0){%><br><%}%>&nbsp;
								<%	}	%>
								<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					
					<td>
						<div align="center">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<br><br>Rupees
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
										<br><%if(i==0){%><br><%}%>Rupees
								<%	}	%>
								<%if(cnt>=1){%><br><b>Rupees</b><%}%><br><br><strong>Rupees</strong><br>&nbsp;
							</font>
						</div>
					</td>
					
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<br><br><%//total_Invoice_Qty%><span id="qty_6"></span>&nbsp;
								<script>
									//alert(window.opener.document.forms[0].total_qty.value);
									document.getElementById("qty_6").innerHTML=''; //window.opener.document.forms[0].total_qty.value;
									//alert("end");
								</script>
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
										<br><%if(i==0){%><br><%}%>&nbsp;
								<%	}	%>
								<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					<td>
						
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br><%=customer_Invoice_Exchg_Rate%><span id="exchrate"></span>&nbsp;
								<script>
								//alert(window.opener.document.forms[0].exch_rate_val.value);
								document.getElementById("exchrate").innerHTML=window.opener.document.forms[0].exch_rate_val.value;
								</script>
								<br><br>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
										<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
								<%	}	%>
								<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
					
					<td>
						<div align="right">
							<font size="1.5px" face="Arial">
								&nbsp;<br>&nbsp;<br><br><%//customer_Invoice_Gross_Amt_INR%><span id="gross_amt_inr1"></span>&nbsp;
								<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
										<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
								<%	}	%>
								<%if(cnt>=1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%><br><br><strong><%=customer_Invoice_Net_Amt_INR%><span id="gross_amt"></span></strong>&nbsp;<br>&nbsp;
							</font>
						</div>
					</td>
				</tr>
				<script>
					document.getElementById("gross_amt").innerHTML=window.opener.document.forms[0].net_amt_inr.value;
				</script>
				<%if(contract_type.equalsIgnoreCase("C")) { %>
				<script>
				document.getElementById("gross_amt_inr1").innerHTML=window.opener.document.forms[0].double_final_gross_amt_inr.value;
				</script>
				<% } else { %>
				<script>
				document.getElementById("gross_amt_inr1").innerHTML=addCommas(window.opener.document.forms[0].double_final_gross_amt_inr.value);
				</script>
				<% } %>
				
				
				
				<%	}	%>
				
				<tr valign="top">
					<td><div align="right"><font size="1.5px" face="Arial"><b><%=(++sr_no)%>&nbsp;</b></font></div></td>
					<td><div align="left"><font size="1.5px" face="Arial"><b>&nbsp;Net Amount Payable</b></font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial"><b>Rupees</b></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial"><b><%=customer_Invoice_Net_Amt_INR%><span id="net_amt_inr"></span>&nbsp;</b></font></div></td>
				</tr>
				
				<%if(contract_type.trim().equalsIgnoreCase("C")) { %>
				<script>
					document.getElementById("net_amt_inr").innerHTML=addCommas(round(parseFloat(window.opener.document.forms[0].net_amt_inr.value),0));
					</script>	
				<% } else { %>
				<script>
					document.getElementById("net_amt_inr").innerHTML=window.opener.document.forms[0].net_amt_inr.value;
					</script>
				<% } %>
			</table>
		</td>
	</tr>
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="middle">
		<td colspan="7">
			<div align="left">
				<font size="1px" face="Arial">
					<%//remark_1%><span id="remark_1"></span>
				</font>
			</div>
	    </td>
	</tr>
	<span id="val1"></span>
	<%	if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
		{	%>
			<tr valign="middle">
				<td colspan="7">
					<div align="center">
						&nbsp;
					</div>
			    </td>
			</tr>
			<tr valign="middle">
				<td colspan="7">
					<div align="left">
						<font size="1px" face="Arial">
							<%//remark_2%><span id="remark_2"></span>
							<script>
							document.getElementById("remark_2").innerHTML=window.opener.document.forms[0].remark_2.value;
							</script>
						</font>
					</div>
			    </td>
			</tr>
	<%	}	%>
				<script>
				document.getElementById("remark_1").innerHTML=window.opener.document.forms[0].remark_1_1.value;
				</script>		
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
				
	<tr valign="middle">
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
	<tr valign="middle">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
</table>


<%} %>
</form>
</body>
</html>