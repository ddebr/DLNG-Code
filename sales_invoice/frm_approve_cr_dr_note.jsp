<%@ page import="java.util.*"%>
<html><head>
<title>Customer Invoice Details</title>
<style>
.decor tr td {
border-left: 2px solid black;
</style>
<script language="JavaScript" src="../js/util2.js"></script>
<script language="JavaScript" src="../js/util1.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>
<script language="JavaScript" src="../js/rptinvdtl.js"></script>
<script language="JavaScript"> 
function closeWin(month,year,bill_cycle,msg,error_msg,billing_dt)
{
	window.opener.refreshPageFromChild3(msg, month, year, bill_cycle,error_msg,billing_dt)
	window.close();
}
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.sales_invoice.DataBean_Sales_InvoiceV2" id="salesInv" scope="page"/>   
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

String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
String inv_approved_flag = request.getParameter("inv_approved_flag")==null?"":request.getParameter("inv_approved_flag");
String mapping_id = request.getParameter("mapping_id")==null?"-1":request.getParameter("mapping_id"); //ADDED FOR LTCORA AND CN
String invoicedt = request.getParameter("invdt")==null?"":request.getParameter("invdt");
String duedt = request.getParameter("duedt")==null?"":request.getParameter("duedt");
// String CR_aprv_by = request.getParameter("CR_aprv_by")==null?"0":request.getParameter("CR_aprv_by"); //SB20160602
String CR_Doc_No = request.getParameter("CR_Doc_No")==null?"0":request.getParameter("CR_Doc_No"); //SB20160602
String CR_Doc_Dt = request.getParameter("CR_Doc_Dt")==null?"0":request.getParameter("CR_Doc_Dt"); //SB20160602
String CrDrcriteria = request.getParameter("CrDrcriteria")==null?"":request.getParameter("CrDrcriteria");
String CR_aprv_by = request.getParameter("dr_cr_aprv_by")==null?"0":request.getParameter("dr_cr_aprv_by");
System.out.println("invoice_title******"+invoice_title);
if(invoice_title.contains("Sup") || invoice_title.contains("CREDIT") || invoice_title.contains("DEBIT")) { 
	invoice_title= invoice_title.substring(0,invoice_title.length()-1); }
if(invoice_title.equalsIgnoreCase("Sup")){ invoice_title="SUPPLEMENTARY INVOICE"; }

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
String error_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
String financial_year = "";
if(Integer.parseInt(month)>3){
financial_year = ""+year+"-"+(Integer.parseInt(year)+1);
}else{
financial_year = ""+(Integer.parseInt(year)-1)+"-"+year;
}
String flag="Y"; 
salesInv.setCallFlag("SALES_INVOICE_REPORT");
salesInv.setCustomer_cd(customer_cd);
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
salesInv.setDrcrcriteria(CrDrcriteria);
salesInv.setFlag1(flag);
if(msg.length()<10){ salesInv.init(); }
System.out.println("activity_name*****1");
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
String contact_Suppl_PAN_NO = salesInv.getContact_Suppl_PAN_NO();
String contact_Suppl_PAN_DT = salesInv.getContact_Suppl_PAN_DT();
String customer_Invoice_DT = salesInv.getCustomer_Invoice_DT();
String customer_Invoice_Due_DT = salesInv.getCustomer_Invoice_Due_DT();
String customer_Invoice_Start_DT = salesInv.getCustomer_Invoice_Start_DT();
String customer_Invoice_End_DT = salesInv.getCustomer_Invoice_End_DT();
String contact_Suppl_GSTIN_NO = salesInv.getContact_Suppl_GSTIN_NO(); //RS01062017
String contact_Suppl_GSTIN_DT = salesInv.getContact_Suppl_GSTIN_DT(); //RS01062017
String new_inv_seq_no = salesInv.getNew_inv_seq_no();
System.out.println("activity_name*****2");
boolean tax_gst = salesInv.isTax_gst();

if(customer_Invoice_DT.equalsIgnoreCase("")){
customer_Invoice_DT=invoicedt;
}
if(customer_Invoice_Due_DT.equalsIgnoreCase("")){
customer_Invoice_Due_DT=duedt;
}
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
String contact_Customer_Service_Tax_NO = salesInv.getContact_Customer_Service_Tax_NO();
String contact_Customer_Service_Tax_DT = salesInv.getContact_Customer_Service_Tax_DT();
String contact_Suppl_Service_Tax_NO = salesInv.getContact_Suppl_Service_Tax_NO();
String contact_Suppl_Service_Tax_DT = salesInv.getContact_Suppl_Service_Tax_DT();
String total_Offspec_Qty = salesInv.getTotal_Offspec_Qty();
String offspec_Sales_Rate = salesInv.getOffspec_Sales_Rate();
String offspec_Flag = salesInv.getOffspec_Flag();
String offspec_Amt_USD = salesInv.getOffspec_Amt_USD();
String total_Gas_Delivered = salesInv.getTotal_Gas_Delivered();
String gas_Delivered_Amt_USD = salesInv.getGas_Delivered_Amt_USD();
String remark_1 = salesInv.getRemark_1();
System.out.println("activity_name*****3");
// System.out.println("remark_1****"+remark_1);
String remark_2 = salesInv.getRemark_2();
String remark_3 = salesInv.getRemark_3();
Vector vSTAT_CD = salesInv.getVSTAT_CD();
Vector vSTAT_NM = salesInv.getVSTAT_NM();
Vector vSTAT_NO = salesInv.getVSTAT_NO();
Vector vSTAT_EFF_DT = salesInv.getVSTAT_EFF_DT();
String advFlg=salesInv.getAdvadjFlg();
String contact_Suppl_State_Code = salesInv.getContact_Suppl_State_Code();
String contact_Suppl_State = salesInv.getContact_Suppl_State();
String dr_cr_net_amt_inr1=salesInv.getDr_cr_net_amt_inr1();
String dr_cr_doc_no1=salesInv.getDr_cr_doc_no1();
String dr_cr_gross_amt1 = salesInv.getDr_cr_gross_amt1();
String tax_amt_inr1 =salesInv.getTax_amt_inr1();
String dr_cr_net_amt1  = salesInv.getDr_cr_net_amt1();		
String dr_cr_exg_rt1 = salesInv.getDr_cr_exg_rt1();
 String exg_rate1= salesInv.getExg_rt1();
String qty1 = salesInv.getQty1();
String sale_price1 = salesInv.getSale_price1();
String diff_exg_rt1=salesInv.getDiff_exg_rt1();
String dr_cr_dt1 = salesInv.getDr_cr_dt1();
 Double Tot_tax_amt1 = salesInv.getTot_tax_amt1();
 String dr_cr_sale_rate = salesInv.getDr_cr_sales_rate();
 String diff_sale_rate = salesInv.getDiff_sale_rt1();
 String criteria=salesInv.getCriteria1();
String gross_amt_usd1=salesInv.getGross_amt_usd1();
Vector customer_Invoice_Tax_Code1= salesInv.getCustomer_Invoice_Tax_Code1();
Vector customer_Invoice_Tax_Rate1= salesInv.getCustomer_Invoice_Tax_Rate1();
Vector customer_Invoice_Tax_Amt1= salesInv.getCustomer_Invoice_Tax_Amt1();
Double Tot_tax_amt2 = salesInv.getTot_tax_amt2();
String tot_invoice_qty = salesInv.getTotal_Invoice_Qty1();
 String diff_qty1 = salesInv.getDiff_qty1();
 String net_amt1=salesInv.getNet_amt_inr1();
String gross_amt1=salesInv.getGross_amt1();
Vector customer_Invoice_Tax_Code2= salesInv.getCustomer_Invoice_Tax_Code2();
Vector customer_Invoice_Tax_Rate2= salesInv.getCustomer_Invoice_Tax_Rate2();
Vector customer_Invoice_Tax_Amt2= salesInv.getCustomer_Invoice_Tax_Amt2();
Vector TAX_ON_TITLE2= salesInv.getTAX_ON_TITLE2();
Vector TAX_NAME2= salesInv.getTAX_NAME2();
Vector TAX_ON_TITLE1= salesInv.getTAX_ON_TITLE1();
Vector TAX_NAME1= salesInv.getTAX_NAME1();
System.out.println("activity_name*****4"+customer_Invoice_Tax_Amt1);
String invno = "";

/* if(remark_1!=null){
if(!remark_1.trim().equals("")){
while((remark_1.indexOf("\n"))!=-1){
// remark_1=(remark_1.substring(0,remark_1.indexOf("\n")-1))+"<br>"+(remark_1.substring(remark_1.indexOf("\n")+1));
}}}
if(remark_2!=null){
if(!remark_2.trim().equals("")){
while((remark_2.indexOf("\n"))!=-1){
remark_2=(remark_2.substring(0,remark_2.indexOf("\n")-1))+"<br>"+(remark_2.substring(remark_2.indexOf("\n")+1));
}}}
if(remark_3!=null){
if(!remark_3.trim().equals("")){
while((remark_3.indexOf("\n"))!=-1){
remark_3=(remark_3.substring(0,remark_3.indexOf("\n")-1))+"<br>"+(remark_3.substring(remark_3.indexOf("\n")+1));
}}} */

if(!new_inv_seq_no.equals("")) {
	invno = new_inv_seq_no;
} else {
if(contract_type.equalsIgnoreCase("C")){
if(hlpl_inv_no.length()>13){
invno = hlpl_inv_no.substring(0,10)+""+hlpl_inv_no.substring(12);
}}else{
if(hlpl_inv_no.length()>13){
invno = hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
}}} 
System.out.println("activity_name*****5");
String contact_addr_flag = salesInv.getContact_addr_flag();
String sn_ref_no = salesInv.getSn_ref_no();
String contact_Customer_GVAT_NO = salesInv.getContact_Customer_GVAT_NO();
String contact_Customer_GVAT_DT = salesInv.getContact_Customer_GVAT_DT();
int cnt = 0;
boolean date_flag = salesInv.isDate_flag();
int sr_no=0;
boolean advance_payment_flag=false;
boolean Tariff_flag=false;
boolean Discount_flag=false;
String Currency="USD";
java.text.NumberFormat nf=new java.text.DecimalFormat("##0.00");
java.text.NumberFormat nfa=new java.text.DecimalFormat("##0.0000");
java.text.NumberFormat nf3 = new java.text.DecimalFormat("###,###,###,##0.00");
String total_tariff_discount="";
String contact_Customer_Plant_State = salesInv.getContact_Customer_Plant_State();
String contact_Customer_State_Code = salesInv.getContact_Customer_State_Code();
int size = vSTAT_NO.size();
String Adjust_cur="USD"; 
/* if(invadjustcur.equalsIgnoreCase("1")){
	Adjust_cur="INR"; 
} */ 
System.out.println("activity_name*****6");
String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
String check_permission = request.getParameter("check_permission")==null?"N":request.getParameter("check_permission");
String authorize_permission = request.getParameter("authorize_permission")==null?"N":request.getParameter("authorize_permission");
String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
String  activity_name = request.getParameter("activity_name")==null?"VIEW":request.getParameter("activity_name");
String check_flag = request.getParameter("check_flag")==null?"":request.getParameter("check_flag");
String Rule_remark = salesInv.getRule_remark();
String sac_code = salesInv.getSac_code();
String sac_name = salesInv.getSac_name();
String service_desc = salesInv.getService_desc();
String tcs_app_flag=salesInv.getTcs_app_flag();
String tcs_amt=salesInv.getTcs_amt();
System.out.println("activity_name*****"+activity_name);
String tcs_nm=salesInv.getTcs_nm();
String fact=salesInv.getFact();
String dr_cr_due_dt  = salesInv.getDr_cr_due_dt();
System.out.println("dr_cr_tax_amt_inr*****"+dr_cr_due_dt);
String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212

String dr_cr_tcs_flag = salesInv.getDr_cr_tcs_flag();
String dr_cr_qty = salesInv.getDr_cr_qty();
String diff_qty = salesInv.getDiff_qty();
String diff_tax = salesInv.getDiff_tax();
String tax_amt_inr = salesInv.getTax_amt_inr();
String item_desc = salesInv.getItem_desc();
// System.out.println("dr_cr_tcs_flag*****"+dr_cr_tcs_flag);
%>
<body <%if(msg.length()>10){%>
 onLoad="closeWin('<%=month%>','<%=year%>','<%=bill_cycle%>','<%= msg%>','<%=error_msg %>','<%=bill_period_start_dt %>');" <%}%>
<%if(inv_approved_flag.trim().equalsIgnoreCase("N") || inv_approved_flag.trim().equals("")){%> background="../images/draft_copy.JPG" <%}%>>
<form method="post" action="../servlet/Frm_dr_cr_note">
<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
<input type="hidden" name="billing_dt" value="<%=bill_period_start_dt%>">

<input type="hidden" name="tax_size" value="<%=size%>">
<input name="contract_type_tax" type="hidden" value="<%=contract_type%>"> 
<input type="hidden" name="contact_Suppl_PAN_NO" value="<%=contact_Suppl_PAN_NO %>">
<input type="hidden" name="contact_Suppl_PAN_DT" value="<%=contact_Suppl_PAN_DT %>">
<% for(int i=0;i<vSTAT_NO.size();i++) { %>
<input type="hidden" name="vstat_nm" value="<%=vSTAT_NM.elementAt(i)%>">
<input type="hidden" name="vstat_no" value="<%=vSTAT_NO.elementAt(i)%>">
<% } %>
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
<tr valign="middle">
<td colspan="7">
<div align="center">
<font size="2" face="Arial">
<%-- <%if((!invoice_title.equals("CREDIT") ) &&  (!invoice_title.equals("SUPPLEMENTARY INVOICE"))  && (!invoice_title.equals("DEBIT") ) ) { %>
<b>
<%if(contract_type.equals("C")) { %>
	<%if(tax_gst) { %>
		<%=invoice_title%> FOR RECIPIENT
	<% } else { %>
		<%=invoice_title%>
	<% } %>
<% } else { %>
	<%=invoice_title%>
<% } %>
</b>
<%} %><br></font> --%>
<font size="4" face="Arial"><b><%=contact_Suppl_Name%></b><br>
<%-- <%if((!invoice_title.equals("CREDIT") ) &&  (!invoice_title.equals("SUPPLEMENTARY INVOICE"))  && (!invoice_title.equals("DEBIT") ) ) { --%>
<%if(invoice_title.equals("DEBIT") || invoice_title.contains("DE_sign")){ %>
	<b>DEBIT NOTE</b>
<%}else {%>
	<b>CREDIT NOTE</b>
<%} %>
</font></div></td></tr>
<%if(contract_type.trim().equalsIgnoreCase("R") || contract_type.trim().equalsIgnoreCase("C"))
{ %>
<tr valign="middle"><td colspan="7"><div align="center">
<font size="1px" face="Arial"><b>
<%if(invoice_title.equals("CREDIT") || invoice_title.contains("CR_sign") ) { %>
	Credit Note issued under Rule 53 of the Central Goods and Services Tax Rules, 2017
<% } else if(invoice_title.equals("SUPPLEMENTARY INVOICE") || invoice_title.equals("DEBIT") || invoice_title.contains("DE_sign")) { %>
	Debit Note issued under Rule 53 of the Central Goods and Services Tax Rules, 2017
<% } else { %>
	<%=Rule_remark%>
<% } %>
</b></font>
</div></td></tr>
<%}%>
<tr valign="middle"><td colspan="7"><div align="center">&nbsp;</div></td></tr>

 <%if(contract_type.equalsIgnoreCase("S")){ %>
<tr valign="middle"><td colspan="7"><div align="center"><font size="1px" face="Arial">
In respect of Supply Notice (SN-<%if(sn_ref_no.trim().equals("")){%><%=sn_no%><%}else{%><%=sn_ref_no%><%}%>) executed on <%=customer_Invoice_SN_Dt%> pursuant to Framework LNG Sales Agreement executed on <%=customer_Invoice_FGSA_Dt%>
<br>
between <%=contact_Suppl_Name%> and <%=contact_Customer_Name%>
</font></div></td></tr>
<%}
else if(contract_type.trim().equalsIgnoreCase("L"))
{%>
<tr valign="middle"><td colspan="7"><div align="center"><font size="1px" face="Arial">
In respect of Letter of Agreement (LOA-<%if(sn_ref_no.trim().equals("")){%><%=sn_no%><%}else{%><%=sn_ref_no%><%}%>) executed on <%=customer_Invoice_SN_Dt%> pursuant to Tender executed on <%=customer_Invoice_FGSA_Dt%>
<br>
between <%=contact_Suppl_Name%> and <%=contact_Customer_Name%>
</font></div></td></tr>
<%}%>




<tr valign="middle"><td colspan="7"><div align="center">&nbsp;</div>    </td></tr>
<tr valign="top"><td colspan="3" width="50%"><div align="left"><font size="1.5px" face="Arial">
Registered Office:<br>
<%=contact_Suppl_Name%>,<br>
(Formerly known as Hazira LNG Private Limited)<br>
<%=contact_Suppl_Person_Address%>,<br>
<%=contact_Suppl_Person_City%>&nbsp;-&nbsp;<%=contact_Suppl_Person_Pin%>
</font></div>    </td>
<td colspan="1" width="10%"><div align="left"><font size="1.5px" face="Arial"><b>To:</b></font></div></td>
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
<tr valign="top"><td colspan="7"><div align="center">&nbsp;</div></td></tr>
<tr valign="top"><td colspan="3"><div><font size="1.5px" face="Arial">
<%if(contract_type.equals("C") || contract_type.equals("R")) {} else if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")) { %>
 <%if(!contact_Suppl_GST_NO.trim().equals("")) { %>
GST TIN No. : <%=contact_Suppl_GST_NO%> DT. <%=contact_Suppl_GST_DT%><br>
<%} if(!contact_Suppl_CST_NO.trim().equals("")) { %>
CST TIN No. :  <%=contact_Suppl_CST_NO%> DT. <%=contact_Suppl_CST_DT%>
<br>
<%} if(!contact_Suppl_PAN_NO.trim().equals("")) { %>
PAN :  <%=contact_Suppl_PAN_NO%>
<br>
<%} } %>
</font></div></td>
 <td colspan="1">&nbsp;</td>
 <td colspan="3" ><div><font size="1.5px" face="Arial">
<%if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")) { %>
<%if(vSTAT_CD.size()>0){ %>
	<%for(int i=0; i<vSTAT_CD.size(); i++){%>
		<font size="1.5px" face="Arial">
		<% if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) { %>
			PAN  : <%=(String)vSTAT_NO.elementAt(i)%> <br>
		<%} else { %> 
			<%=(String)vSTAT_NM.elementAt(i)%> : <%=(String)vSTAT_NO.elementAt(i)%> DT. <%=(String)vSTAT_EFF_DT.elementAt(i)%><br> 
		<% } %>  
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
<%} } } %>
</font></div></td>
</tr>


<tr valign="top"><td colspan="7">&nbsp;</td></tr>
<tr valign="middle"><td colspan="4"></td>
<td colspan="2" width="25%"><div align="center">
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
<%if((!invoice_title.equals("CREDIT") )  &&  (!invoice_title.equals("SUPPLEMENTARY INVOICE"))  && (!invoice_title.equals("DEBIT") ) && !invoice_title.contains("DE_sign") && !invoice_title.contains("CR_sign")) { %>
<tr><td><div align="right"><font size="1.5px" face="Arial"><b>Invoice Date &nbsp;</b></font></div></td></tr><% } %>
<tr><td><div align="right"><font size="1.5px" face="Arial"><b>

<% if(invoice_title.equals("DEBIT") || invoice_title.contains("DE_sign")){%>
	Debit Note Date:

<%} else {%>
	Credit Note Date:
<%} %></b></font></div></td></tr>

<% if(invoice_title.equals("DEBIT") || invoice_title.contains("DE_sign")){%>
<tr><td><div align="right"><font size="1.5px" face="Arial"><b>
	Payment Due Date:
</b></font></div></td></tr> 
<%}%>

<tr><td><div align="right"><font size="1.5px" face="Arial"><b> 

<%if(invoice_title.equals("DEBIT") || invoice_title.contains("DE_sign")) {%>
	Debit Note No:
<%} else {%>
	Credit Note No: 
<%}%>
</b></font></div></td></tr> </table></div>
</td>


<td colspan="1" width="15%">
<div align="center">
<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">

<tr><td><div align="right"><font size="1.5px" face="Arial"><b>
<%=dr_cr_dt1%></b></font></div></td></tr>

<% if(invoice_title.equals("DEBIT") || invoice_title.contains("DE_sign")){%>
	<tr><td><div align="right"><font size="1.5px" face="Arial"><b>
	<%=dr_cr_due_dt%>
	</b></font></div></td></tr> 
<%} %>

<tr><td><div align="right"><font size="1.5px" face="Arial"><b>
<%=dr_cr_doc_no1%>
</b></font></div></td></tr> </table></div></td>

</tr>


<tr valign="middle"><td colspan="7">&nbsp;</td></tr>

<table width="100%"  border="1" align="center" cellpadding="2" cellspacing="0">
<tr valign="bottom">
<td width="6%"><div align="center"><font size="1.5px" face="Arial"><b>Sr. No.</b></font></div></td>
<td width="34%"><div align="center"><font size="1.5px" face="Arial"><b>Item</b></font></div></td>
<!-- <td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Attachment<br>Reference</b></font></div></td> -->
<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Currency</b></font></div></td>
<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>Quantity<br>(MMBTUS)</b></font></div></td>
<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Rate</b></font></div></td>
<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>Amount</b></font></div></td>
</tr>
<%String Inv_Ref_Dtl = "LNG delivered as per Invoice ref "+invno+" dated "+customer_Invoice_DT;
if(criteria.contains("REV_INV") && invoice_title.equalsIgnoreCase("CREDIT")) { %>
<tr valign="top">
<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>1&nbsp;<br><br><%}%><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>2&nbsp;<br><br><%}else{%>1&nbsp;<br><br><%}%><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>3&nbsp;<%}else{%>2&nbsp;<%}%><%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>4&nbsp;<%}else if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>3&nbsp;<%}%></font></div></td>
<td><div align="left"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>&nbsp;LNG&nbsp;(Regasified)<br><br><%}%><%if(contract_type.equalsIgnoreCase("R")){%>&nbsp;Regasification&nbsp;Tariff&nbsp;(USD/mmbtu)<%}else if(contract_type.equalsIgnoreCase("C")){%>&nbsp;LNG&nbsp;Tariff&nbsp;(USD/mmbtu)<%}else{%>
&nbsp;<%=Inv_Ref_Dtl %>
String Inv_Ref_Dtl = "LNG delivered as per Invoice ref "+invno+" dated "+customer_Invoice_DT;
<%}%>
<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>&nbsp;Offspec QTY<%}%><br><br>&nbsp;Gross Amount (USD)</font></div></td>
<!-- <td> -->
 <div align="center"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%><% if(!invoice_title.equalsIgnoreCase("CREDIT")){%><a href="#" onClick="openInvoiceAtt1Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;1</a><br><br><%}}%><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>&nbsp;<br><br><%}else{%> <%if(!invoice_title.equalsIgnoreCase("CREDIT")){%><a href="#" onClick="openInvoiceAtt1Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;1</a><br><br><%}}%>&nbsp;</font></div> 
<!-- </td> -->
<td><div align="center"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>&nbsp;<br><br><%}%>USD<br><br><%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>USD<%}%></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%><%=total_Gas_Delivered%>&nbsp;<br><br><%}%><%if(contract_type.equals("S")) { %><%=total_Gas_Delivered%><% } %>&nbsp;<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br><%=total_Offspec_Qty%>&nbsp;<%}%><br><br><%//=total_Invoice_Qty%>&nbsp;</font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">
	<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
		&nbsp;<br><br>
	<%}%>
	&nbsp;<%=invoice_Sales_Rate%>&nbsp;<br><br>
	<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
		<br><br><%=offspec_Sales_Rate%>&nbsp;<%}%>
	</font>
</div></td>

<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>&nbsp;<br><br><%}%><%//nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(gas_Delivered_Amt_USD)))%>&nbsp;<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br><%=offspec_Amt_USD%>&nbsp;<%}%><br><br><%=customer_Invoice_Gross_Amt_USD%>&nbsp;</font></div></td>
</tr>
<tr valign="middle">
	<td>
		<div align="right">
			<font size="1.5px" face="Arial">
				&nbsp;<br>
				3&nbsp;<br><br>4&nbsp;
				
				<%
				sr_no = 4;
				for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
					{	++cnt;%>
						<br><%if(i==0){%><br><%=(++sr_no)%><%}%>&nbsp;
				<%	}	%> 
					<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++) {
						sr_no++;%>
						<%=sr_no %>&nbsp;<br>
					<%}%>
				
				<%if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){%>
					<%if(tcs_app_flag.equals("Y") && dr_cr_tcs_flag.equals("Y")){ %>
								<br><br><font size="1.5px" face="Arial"><%=(++sr_no)%>&nbsp;</font>
								
					<%} %>
				<%} %>	
			</font>
		</div>
	</td>
	<td>
		<div align="left">
			<font size="1.5px" face="Arial">
				&nbsp;<br>&nbsp;Exchange Rate<br><br>&nbsp;<strong>Gross Amount</strong>
				<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
					{	%>
						<br><%if(i==0){%><br><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
				<%	}	%>
				<%if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){%>
						<%if(tcs_app_flag.equals("Y") && dr_cr_tcs_flag.equals("Y")){ %>
									<br><br><font size="1.5px" face="Arial">&nbsp;<%=tcs_nm %>&nbsp;</font>
									
						<%} %>				
					<%}%>
			</font>
		</div>
	</td>
	<td>
		<div align="center">
			<font size="1.5px" face="Arial">
				<br>Rupees&nbsp;<br><br>Rupees
				<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
					{	%>
						<br><%if(i==0){%><br><%}%>Rupees
				<%	}	%>
				<%if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){%>
						<%if(tcs_app_flag.equals("Y")  && dr_cr_tcs_flag.equals("Y")){ %>
									<br><br><font size="1.5px" face="Arial">Rupees&nbsp;</font>
									
						<%} %>				
					<%}%>
			</font>
		</div>
	</td>
	<td>
		<div align="right">
			<font size="1.5px" face="Arial">
				&nbsp;<br>&nbsp;<br><br><span id="qty"></span>&nbsp;
				<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){	%>
						<br><%if(i==0){%><br><%}%>&nbsp;
				<%	}	%>
				<%if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){%>
						<%if(tcs_app_flag.equals("Y")  && dr_cr_tcs_flag.equals("Y")){ %>
									<br><br><font size="1.5px" face="Arial"><%%>&nbsp;</font>
									
						<%} %>				
					<%}%>
			</font>
		</div>
	</td>
	<td>
		<div align="right"><font size="1.5px" face="Arial">
			<br>&nbsp;<%=customer_Invoice_Exchg_Rate%>&nbsp;<br><br>
			<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
				<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
			<%}%>
			
			<%if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){%>
				<%if(tcs_app_flag.equals("Y")  && dr_cr_tcs_flag.equals("Y")){ %>
					<br><br><font size="1.5px" face="Arial"><%=fact%> %&nbsp;</font>
				<%} %>				
			<%}%>
		</font>
		</div>
	</td>	

	<td>
		<div align="right"><font size="1.5px" face="Arial">
			&nbsp;<br>&nbsp;<br><br><%=customer_Invoice_Gross_Amt_INR%>&nbsp;
			<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
				<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
			<%}%>
			<%if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){%>
				<%if(tcs_app_flag.equals("Y")  && dr_cr_tcs_flag.equals("Y")){ %>
					<br><br><font size="1.5px" face="Arial"><%=tcs_amt%>&nbsp;</font>
				<%} %>				
			<%}%>
		</font>
		</div>
	</td>	
</tr>
<tr valign="top">
<td><div align="right"><font size="1.5px" face="Arial"><b><%=(++sr_no)%>&nbsp;</b></font></div></td>
<td><div align="left"><font size="1.5px" face="Arial"><b>&nbsp;Net Amount Payable</b></font></div></td>
<!-- <td><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td> -->
<td><div align="center"><font size="1.5px" face="Arial"><b>Rupees</b></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
<td><div align="right"><font size="1.5px" face="Arial"><b><%=customer_Invoice_Net_Amt_INR%>&nbsp;</b></font></div></td></tr>

<% } else {%>

<tr valign="top">
<td width="6%"><div align="right"><font size="1.5px" face="Arial"><b>1&nbsp;<br><br>2&nbsp;<br><br></b></font></div></td>
<td width="34%"><div align="left"><font size="1.5px" face="Arial"><b>
<%=Inv_Ref_Dtl %>

<br><br>Gross Amount (USD)<br><br></b></font></div></td>
<!-- <td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>&nbsp;<br><br></b></font></div></td> -->
<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>USD<br><br><br><br></b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>
<%if(!criteria.contains("DIFF-TAX")){ %>
	<%=qty1%>
<%}else{ %>
	<%=diff_qty %>
<%} %>	
<br><br>&nbsp;<br><br></b> </font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial" ><b><%=sale_price1%><br><br>&nbsp;<br><br></b></font></div></td>
<td width="15%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>

<%if(criteria.contains("DIFF-TAX")){ 
	String temp_gross_inr =dr_cr_gross_amt1;
	if(temp_gross_inr.contains(",")){
		temp_gross_inr = temp_gross_inr.replace(",", "");
	}
// 	System.out.println("temp_gross_inr--------"+temp_gross_inr);
	double gross_usd =  Double.parseDouble(temp_gross_inr+"") / Double.parseDouble(exg_rate1+""); %>
	<%=nf.format(gross_usd) %>
<%}else{ %>
	<%=gross_amt_usd1%>
<%} %>

<br><br></b></font></div></td>
</tr>
<tr valign="top">
<% int srNo = 3;%>

<!-- for sequences -->

<td width="6%">
	<div align="right"><font size="1.5px" face="Arial">
	<b><br>
	<%if(criteria.contains("DIFF-EXG") ) {%>
		<%=srNo %>&nbsp; <br><br><%srNo++;%> <%=srNo %>&nbsp;<br><br><%srNo++;%> <%=srNo %>&nbsp;<br><br><%srNo++;%>  
	<%}else{%>
		<%=srNo %>&nbsp; <br><br><%srNo++;%> 
	<%} %>
	
	<%if(criteria.contains("DIFF-PRICE")) {%>
		<%=srNo %>&nbsp; <br><br><%srNo++;%> <%=srNo %>&nbsp;<br><br><%srNo++;%> 
	<%}%>
	<%if(criteria.contains("DIFF-QTY")){ %>
		<%=srNo %>&nbsp; <br><br><%srNo++;%> <%=srNo %>&nbsp;<br><br><%srNo++;%> <%=srNo %>&nbsp;<br><br><%srNo++;%> 
	<%} %>
	
	<%=srNo %>&nbsp;<br><br>
	<%if(criteria.contains("DIFF-TAX")){
		srNo++;%>
			<%=srNo %>&nbsp;<br><br><br >
	<%}else{
		for(int i=0;i<customer_Invoice_Tax_Code1.size();i++) {
			srNo++;%>
			<%=srNo %>&nbsp;<br>
		<%}%>
	<%} %>
	<%if(tcs_app_flag.equals("Y")  && dr_cr_tcs_flag.equals("Y")){ 
		srNo++;
	%>
		<br><%=srNo %>&nbsp; <br>
	<%} %>
	<br>
	</b></font></div>
</td>

<!-- for line item -->
<td width="34%"><div align="left"><font size="1.5px" face="Arial">
<br>
	<%if(criteria.contains("DIFF-EXG")) {%>
		
		&nbsp;Exchange Rate  <br><br>&nbsp;Applicable Exchange Rate <br><br> &nbsp;Difference In Exchange Rate <br><br>
	<%}else{%>
		&nbsp;Exchange Rate <br><br>
	<% } %>	
	
	<%if(criteria.contains("DIFF-PRICE")){ %>
		&nbsp;Applicable Sales Rate <br><br> &nbsp;Difference In Sales Rate <br><br>
	<%}%>
	<%if(criteria.contains("DIFF-QTY")){ %>
		&nbsp;Invoice Quantity <br><br>&nbsp;Applicable Quantity<br><br>&nbsp;Difference In Quantity<br><br>
	<%} %>
	
	&nbsp;Gross Amount<br><br>
	
	<!-- for tax -->
	<%if(criteria.contains("DIFF-TAX")){ %>
		&nbsp;Tax Rate<br>
		&nbsp;<%=item_desc %>
	<%}else{ %>
		
		<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++){%>
			&nbsp;<%=(String)TAX_NAME1.elementAt(i)%>
		<%} %>
	<%} %>
	<br>
	<%if(tcs_app_flag.equals("Y")  && dr_cr_tcs_flag.equals("Y")){ %>
		<br><font size="1.5px" face="Arial">&nbsp;<%=tcs_nm %>&nbsp;</font>
	<%} %>	  
	<br>
	</font></div>
</td>

<!-- for currency -->
<td width="15%"><div align="center"><font size="1.5px" face="Arial">
<br>
	<%if(criteria.contains("DIFF-EXG")) {%>
		Rupees <br><br>Rupees <br><br> Rupees <br><br>
	<%}else{%>
		Rupees<br><br>
	<% } %>	
	
	<%if(criteria.contains("DIFF-PRICE")){ %>
		Rupees <br><br> Rupees <br><br>
	<%}%>
	<%if(criteria.contains("DIFF-QTY")){ %>
		<br><br>&nbsp;<br><br>&nbsp;<br><br> 
	<%} %>
	
	Rupees<br><br>
	
	Rupees<br>
	<%if(tcs_app_flag.equals("Y")  && dr_cr_tcs_flag.equals("Y")){ %>
	<%if(criteria.contains("DIFF-TAX")){ %>
		<br><br>
	<%} %>
		<br>Rupees
	<%} %>
		  
	<br></font></div>
</td>	
<!-- for quantity -->
<td>
<div align="right"><font size="1.5px" face="Arial">
<br>
	<%if(criteria.contains("DIFF-EXG")) {%>
		&nbsp; <br><br>&nbsp; <br><br> &nbsp; <br><br>
	<%}else{%>
		&nbsp;<br><br>
	<% } %>	
	
	<%if(criteria.contains("DIFF-PRICE")){ %>
		&nbsp; <br><br> &nbsp; <br><br>
	<%}%>
	<%if(criteria.contains("DIFF-QTY")){ %>
		<%=qty1 %>&nbsp;<br><br><%=dr_cr_qty %>&nbsp;<br><br><%=diff_qty %>&nbsp;<br><br> 
	<%} %>
	
	&nbsp;<br><br>
	
	&nbsp;<br>
	<%if(tcs_app_flag.equals("Y")  && dr_cr_tcs_flag.equals("Y")){ %>
		<%if(criteria.contains("DIFF-TAX")){ %>
			<br><br>
		<%} %>
		<br>&nbsp;
	<%} %>
		  
	<br></font></div>
</td>

<td width="15%"><div align="right"><font size="1.5px" face="Arial">
<br>
	<%if(criteria.contains("DIFF-EXG")) {%>
		<%=exg_rate1%>&nbsp;<br><br><%=dr_cr_exg_rt1%>&nbsp; <br><br> <%=diff_exg_rt1 %>&nbsp; <br><br>
	<%}else{%>
		<%=exg_rate1%>&nbsp;<br><br>
	<% } %>
	<%if(criteria.contains("DIFF-PRICE")){ %>
		<%=dr_cr_sale_rate %>&nbsp; <br><br> <%=diff_sale_rate%>&nbsp; <br><br>
	<%}%>
	<%if(criteria.contains("DIFF-QTY")){ %>
		<br><br>&nbsp;<br><br>&nbsp;<br><br> 
	<%} %>
	
	&nbsp;<br><br>
	<%if(criteria.contains("DIFF-TAX")){ %>
		<%=diff_tax %> %&nbsp;
	<%}else{ %>
		<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++){%>
			<%=customer_Invoice_Tax_Rate1.elementAt(i)%> %&nbsp;
		<%} %>
		
	<%} %>
	<br>
	<%if(tcs_app_flag.equals("Y")  && dr_cr_tcs_flag.equals("Y")){ %>
		<%if(criteria.contains("DIFF-TAX")){ %>
			<br><br>
		<%} %>
		<br><%=fact%> %&nbsp;
	<%} %>
	<br></font></div>
</td>	
<!-- for Amount -->
<td width="15%">
<div align="right"><font size="1.5px" face="Arial">
<br>
	<%if(criteria.contains("DIFF-EXG")) {%>
		&nbsp; <br><br>&nbsp; <br><br> &nbsp; <br><br>
	<%}else{%>
		&nbsp;<br><br>
	<% } %>	
	
	<%if(criteria.contains("DIFF-PRICE")){ %>
		&nbsp; <br><br> &nbsp; <br><br>
	<%}%>
	<%if(criteria.contains("DIFF-QTY")){ %>
		&nbsp;<br><br>&nbsp;<br><br>&nbsp;<br><br> 
	<%} %>
	
	<%=dr_cr_gross_amt1%>&nbsp;<br><br>
	<%if(criteria.contains("DIFF-TAX")){ %>
		<%= tax_amt_inr%>&nbsp;
	<%}else{ %>
		<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++){%>
			<%=customer_Invoice_Tax_Amt1.elementAt(i) %>&nbsp;
		<%} %>
		
	<%} %>
	<br>
	<%if(tcs_app_flag.equals("Y") && dr_cr_tcs_flag.equals("Y")){ %>
		<%if(criteria.contains("DIFF-TAX")){ %>
		<br><br>
		<%} %>
		<br><font size="1.5px" face="Arial"><%=tcs_amt%>&nbsp;</font>
	<%} %>
		  
	<br></font></div>
</td>	
 
</tr>
<tr valign="bottom">
	<td width="6%"><div align="right"><font size="1.5px" face="Arial"><b><% srNo++;%><%=srNo%>&nbsp;<br></b></font></div></td>
	<td width="34%"><div align="left"><font size="1.5px" face="Arial"><b>Net Amount Payable<br></b></font></div></td>
	<!-- <td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>&nbsp;<br><br></b></font></div></td> -->
	<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>Rupees<br></b></font></div></td>
	<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br></b> </font></div></td>
	<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br></b></font></div></td>
	<td width="15%"><div align="right"><font size="1.5px" face="Arial"><b>
		<%=dr_cr_net_amt1%> <br>
		
	</b></font></div></td>					
</tr>
<%}%>
</table></td></tr>

<table border="0" width="100%" align="center" cellpadding="2" cellspacing="0">
<tr align="center"><td colspan="7">&nbsp;</td></tr>
<%if(!invoice_title.equals("CREDIT")){ %>
<tr valign="middle">
<td colspan="7">
<div align="left"><font size="1px" face="Arial">
<%=remark_1%>
<%if(contract_type.trim().equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C"))
{%>
<br><%=remark_3%>
<%}%></font></div></td></tr>

<%//if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){%>
<tr valign="middle">
<td colspan="7">
<div align="left"><font size="1px" face="Arial"><%=remark_2%></font></div>
</td></tr>
<%}%>
<tr valign="middle">
<td colspan="7">
<div align="center">&nbsp;</div>
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
<div align="center">&nbsp;</div>
    </td>
</tr>
<tr valign="middle"><td colspan="7">
<%if( ((invoice_title.equals("CREDIT")  || invoice_title.equals("DEBIT") ||invoice_title.equals("SUPPLEMENTARY INVOICE") ) && CR_aprv_by.equalsIgnoreCase("0"))) { %>
<div align="center">
<%if(invoice_title.equals("SUPPLEMENTARY INVOICE")){%>DR Approval OK:&nbsp;<%}else if(invoice_title.equals("DEBIT")) {%>DR Approval OK:&nbsp; <%}else{%>
CR Approval OK:&nbsp; <%}%>
<input type="radio" name="rd" value="Y" onClick="doSubmit('B');" >&nbsp;<b>Yes</b>&nbsp;&nbsp;&nbsp;&nbsp;
<input type="radio" name="rd" value="N" onClick="doSubmit('B');" >&nbsp;<b>No</b>
</div>
<%} %>

</td>
</tr></table>
<!-- <button onclick="exportTableToCSV('members.csv')">Export HTML Table To CSV File</button> -->
<input type="hidden" name="hlpl_inv_no" value="<%=hlpl_inv_no%>">
<input type="hidden" name="month" value="<%=month%>">
<input type="hidden" name="year" value="<%=year%>">
<input type="hidden" name="bill_cycle" value="<%=bill_cycle%>">
<input type="hidden" name="hlpl_inv_seq_no" value="<%=hlpl_inv_seq_no%>">
<input type="hidden" name="inv_financial_year" value="<%=inv_financial_year%>">
<input type="hidden" name="contract_type" value="<%=contract_type%>">
<input type="hidden" name="option" value="">
<input type="hidden" name="approve_flag" value="N">
<input type="hidden" name="check_flag" value="N">
<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="check_permission" value="<%=check_permission%>">
<input type="hidden" name="authorize_permission" value="<%=authorize_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
<input type="hidden" name="adjustmentflag" value="<%=advance_payment_flag%>">
<input type="hidden" name="mapping_id" value="<%=mapping_id%>">
<input type="hidden" name="advFlg" value="<%=advFlg%>">
<input type="hidden" name="activity_name" value="<%=activity_name%>">
<input type="hidden" name="TAX_ADV_ADJ_AMT" value="<%//=TAX_ADV_ADJ_AMT%>">
<input type="hidden" name="TAX_ADV_ADJ_FLAG" value="<%//=TAX_ADV_ADJ_FLAG %>">
<input type="hidden" name="invadjflag" value="<%//=invadjflag%>">
<input type="hidden" name="invadjustmentamt" value="<%//=invadjustmentamt%>">
<input type="hidden" name="Currency" value="<%=Adjust_cur%>">
<input type="hidden" name="invno_disp" value="<%=invno%>">
</form>
</body>
</html>