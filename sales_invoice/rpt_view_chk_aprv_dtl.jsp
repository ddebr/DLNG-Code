<%@ page import="java.util.*"%>
<%-- <%@ page errorPage="../home/ExceptionHandler.jsp"%> --%>
<%-- <%@ include file="../sess/Expire.jsp"%> --%>
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
function downloadCSV(csv, filename) {
    var csvFile;
    var downloadLink;

    // CSV file
    csvFile = new Blob([csv], {type: "text/csv"});

    // Download link
    downloadLink = document.createElement("a");

    // File name
    downloadLink.download = filename;

    // Create a link to the file
    downloadLink.href = window.URL.createObjectURL(csvFile);

    // Hide download link
    downloadLink.style.display = "none";

    // Add the link to DOM
    document.body.appendChild(downloadLink);

    // Click download link
    downloadLink.click();
}
function exportTableToCSV(filename) {
    var csv = [];
    var rows = document.querySelectorAll("table tr");
    
    for (var i = 0; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll("td, th");
        
        for (var j = 0; j < cols.length; j++) 
            row.push(cols[j].innerText);
        
        csv.push(row.join(","));        
    }
    // Download CSV file
    downloadCSV(csv.join("\n"), filename);
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
// System.out.println("invoice_title******"+invoice_title);
String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
String inv_approved_flag = request.getParameter("inv_approved_flag")==null?"":request.getParameter("inv_approved_flag");
String mapping_id = request.getParameter("mapping_id")==null?"-1":request.getParameter("mapping_id"); //ADDED FOR LTCORA AND CN
String invoicedt = request.getParameter("invdt")==null?"":request.getParameter("invdt");
String duedt = request.getParameter("duedt")==null?"":request.getParameter("duedt");
String CR_aprv_by = request.getParameter("CR_aprv_by")==null?"0":request.getParameter("CR_aprv_by"); //SB20160602
String CR_Doc_No = request.getParameter("CR_Doc_No")==null?"0":request.getParameter("CR_Doc_No"); //SB20160602
String CR_Doc_Dt = request.getParameter("CR_Doc_Dt")==null?"0":request.getParameter("CR_Doc_Dt"); //SB20160602
String CrDrcriteria = request.getParameter("CrDrcriteria")==null?"":request.getParameter("CrDrcriteria");
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
String financial_year = "";
if(Integer.parseInt(month)>3){
financial_year = ""+year+"-"+(Integer.parseInt(year)+1);
}else{
financial_year = ""+(Integer.parseInt(year)-1)+"-"+year;
}
String flag="Y"; 
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
salesInv.setDrcrcriteria(CrDrcriteria);
salesInv.setFlag1(flag);
if(msg.length()<10){ salesInv.init(); }
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
System.out.println("customer_Invoice_Tax_Flag****"+customer_Invoice_Tax_Flag);
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
if(remark_1!=null){
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
}}}
String invno = "";
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
String contact_addr_flag = salesInv.getContact_addr_flag();
String sn_ref_no = salesInv.getSn_ref_no();
String contact_Customer_GVAT_NO = salesInv.getContact_Customer_GVAT_NO();
String contact_Customer_GVAT_DT = salesInv.getContact_Customer_GVAT_DT();
int cnt = 0;
boolean date_flag = salesInv.isDate_flag();
adv.setCallFlag("FetchAdjustmentofInvoiceDetails");
adv.setInvContractType(contract_type);
adv.setInvHlplinvseqno(hlpl_inv_seq_no);
adv.setInvFinancialYear(inv_financial_year);
adv.setInvbill_period_end_dt(bill_period_end_dt);
adv.setModifycontract_type(contract_type);
adv.setCustomer_inv_mapping_id(mapping_id); //ADDED FOR LTCORA AND CN
adv.setDate_flag(date_flag);
if(msg.length()<10){ adv.init(); }
String adj_no="",tax_adj_no="",total_tax_no="",sbc_adj_no="",kkc_adj_no="",total_no="",total_ser_tax_no="",
total_sbc_tax_no="",total_kkc_tax_no="";
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
String total_invoice_payable_usd=adv.getTotal_invoice_payable_usd();
String total_invoice_payable_inr=adv.getTotal_invoice_payable_inr();
String total_tax_payable=adv.getTotal_tax_payable();
Map TAX_ADV_ADJ_FLAG_GST = adv.getTAX_ADV_ADJ_FLAG_GST();
Map TAX_ADV_ADJ_AMT_GST = adv.getTAX_ADV_ADJ_AMT_GST();
Map TAX_ADV_ADJ_CUR_GST = adv.getTAX_ADV_ADJ_CUR_GST();
Map TAX_ADV_ADJ_GROSS_INR_GST = adv.getTAX_ADV_ADJ_GROSS_INR_GST();
Map TAX_ADV_ADJ_GROSS_USD_GST = adv.getTAX_ADV_ADJ_GROSS_USD_GST();
Map TAX_ADV_ADJ_SIGN_GST = adv.getTAX_ADV_ADJ_SIGN_GST();
Map TAX_ADV_ADJ_REMARK_GST = adv.getTAX_ADV_ADJ_REMARK_GST();
Vector TAX_ADV_ADJ_CODE_GST = adv.getTAX_ADV_ADJ_CODE_GST();
Map TAX_ADV_ADJ_ABBR_GST = adv.getTAX_ADV_ADJ_ABBR_GST();
Vector tax_nos = new Vector();
Vector total_compo_tax_payable = adv.getTotal_compo_tax_payable();
if(invadjremark!=null){
if(!invadjremark.trim().equals("")){
while((invadjremark.indexOf("\n"))!=-1){
invadjremark=(invadjremark.substring(0,invadjremark.indexOf("\n")-1))+"<br>"+(invadjremark.substring(invadjremark.indexOf("\n")+1));
}}}
int sr_no=0;
boolean advance_payment_flag=false;
boolean Tariff_flag=false;
boolean Discount_flag=false;
String Currency="USD";
java.text.NumberFormat nf=new java.text.DecimalFormat("##0.00");
java.text.NumberFormat nfa=new java.text.DecimalFormat("##0.0000");
java.text.NumberFormat nf3 = new java.text.DecimalFormat("###,###,###,##0.00");
String total_tariff_discount="";
if(invadjflag.equalsIgnoreCase("Y")) {
advance_payment_flag=true;
gas_Delivered_Amt_USD=Final_first_gross_amt; }
if(invtariffflag.equalsIgnoreCase("Y")) {
Tariff_flag=true;
Currency="Rupees";
gas_Delivered_Amt_USD=Final_first_gross_amt;
if(offspec_Flag.trim().equalsIgnoreCase("Y")){
offspec_Sales_Rate=""+(Double.parseDouble(offspec_Sales_Rate)*Double.parseDouble(invexchngrt));
offspec_Amt_USD=""+(Double.parseDouble(offspec_Amt_USD)*Double.parseDouble(invexchngrt));
offspec_Amt_USD=nf.format(Double.parseDouble(offspec_Amt_USD));
offspec_Sales_Rate=nfa.format(Double.parseDouble(offspec_Sales_Rate));
} if(invdiscountflag.equalsIgnoreCase("Y")) {
 total_tariff_discount=""+(Double.parseDouble(inv_discount_price)+Double.parseDouble(inv_display_rate));
} }
if(invdiscountflag.equalsIgnoreCase("Y")){
Discount_flag=true;
gas_Delivered_Amt_USD=Final_first_gross_amt;
total_tariff_discount=""+Double.parseDouble(inv_display_rate); }
String Final_first_gross_amt_inr="0";
String invadjustmentamt_inr="0";
if(!Tariff_flag && invadjustcur.equalsIgnoreCase("1")){
	Final_first_gross_amt_inr=nf3.format(Double.parseDouble(adv.getFirst_gross_amt_inr())); }
if(TAX_ADV_ADJ_FLAG.equalsIgnoreCase("Y") || TAX_ADV_ADJ_CODE_GST.size()>0){
	Final_first_gross_amt_inr=nf3.format(Double.parseDouble(adv.getFirst_gross_amt_inr()));
	double temp_amt_adj = Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(invadjustmentamt));
	double gross_usd = Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(Final_first_gross_amt));
	if(temp_amt_adj==gross_usd) {
		invadjustmentamt_inr = ""+(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(Final_first_gross_amt_inr)));
	} else {
		invadjustmentamt_inr=""+(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(invadjustmentamt))*Double.parseDouble(invexchngrt));
	}
	invadjustmentamt_inr=nf3.format(Double.parseDouble(invadjustmentamt_inr));
}
if(advance_payment_flag || Tariff_flag || Discount_flag){
customer_Invoice_Tax_Amt.clear();
customer_Invoice_Tax_Rate.clear();
customer_Invoice_Tax_Amt = adv.getCustomer_Invoice_Tax_Amt();
customer_Invoice_Tax_Rate = adv.getCustomer_Invoice_Tax_Rate();
customer_Invoice_Gross_Amt_INR = adv.getCustomer_Invoice_Gross_Amt_INR(); }
if(!advance_payment_flag && !Tariff_flag && !Discount_flag){
sr_no = 4;
if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C"))
{ sr_no = 5; }
if(offspec_Flag.trim().equalsIgnoreCase("Y")){
++sr_no;} }
String contact_Customer_Plant_State = salesInv.getContact_Customer_Plant_State();
String contact_Customer_State_Code = salesInv.getContact_Customer_State_Code();
int size = vSTAT_NO.size();
String Adjust_cur="USD"; 
if(invadjustcur.equalsIgnoreCase("1")){
Adjust_cur="INR"; }
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
// System.out.println("tcs_amt*****"+tcs_amt);
String tcs_nm=salesInv.getTcs_nm();
String fact=salesInv.getFact();

%>
<body <%if(msg.length()>10){%> onLoad="doClose('<%=month%>','<%=year%>','<%=bill_cycle%>','<%=msg%>');" <%}%> <%if(inv_approved_flag.trim().equalsIgnoreCase("N") || inv_approved_flag.trim().equals("")){%> background="../images/draft_copy.JPG" <%}%>>
<form method="post" action="../servlet/Frm_Sales_Invoice">
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
<%if((!invoice_title.equals("CREDIT") ) &&  (!invoice_title.equals("SUPPLEMENTARY INVOICE"))  && (!invoice_title.equals("DEBIT") ) ) { %>
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
<%} %><br></font>
<font size="4" face="Arial"><b><%=contact_Suppl_Name%></b><br>
<%if((!invoice_title.equals("CREDIT") ) &&  (!invoice_title.equals("SUPPLEMENTARY INVOICE"))  && (!invoice_title.equals("DEBIT") ) ) { %>
<%if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){%>
<%if(customer_Invoice_Tax_Flag.equalsIgnoreCase("V")){%>
<b>TAX INVOICE</b>
<%} else{%><b>RETAIL INVOICE</b>
<%}} else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
<b>TAX INVOICE</b>
<%}} else {%>
<%if(invoice_title.equals("SUPPLEMENTARY INVOICE") || invoice_title.equals("DEBIT")){ %><b>DEBIT NOTE</b>
<%} else {%><b>CREDIT NOTE</b>
<%} }%>
</font></div></td></tr>
<%if(contract_type.trim().equalsIgnoreCase("R") || contract_type.trim().equalsIgnoreCase("C"))
{ %>
<tr valign="middle"><td colspan="7"><div align="center">
<font size="1px" face="Arial"><b>
<%if(invoice_title.equals("CREDIT")) { %>
	Credit Note issued under Rule 53 of the Central Goods and Services Tax Rules, 2017
<% } else if(invoice_title.equals("SUPPLEMENTARY INVOICE") || invoice_title.equals("DEBIT")) { %>
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
<%}
else if(contract_type.trim().equalsIgnoreCase("R"))
{%>
<tr valign="middle"><td colspan="7"><div align="center"><font size="1px" face="Arial">
In respect of Regassification Agreement executed on <%=customer_Invoice_FGSA_Dt%> and subsequent side letters 
<br>
between <%=contact_Customer_Name%> and <%=contact_Suppl_Name%>
</font></div></td></tr>
<%}
else if(contract_type.trim().equalsIgnoreCase("C"))
{%>
<tr valign="middle"><td colspan="7"><div align="center"><font size="1px" face="Arial">
In respect of LNG executed on <%=customer_Invoice_FGSA_Dt%> 
<%if(Double.parseDouble(fgsa_no)<9999) { %>
&amp; CN-<%=fgsa_no%> executed on <%=customer_Invoice_SN_Dt%>
<%} %>
<br>
between <%=contact_Customer_Name%> and <%=contact_Suppl_Name%>
</font></div>    </td></tr>
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
<%if(contract_type.equals("C") || contract_type.equalsIgnoreCase("R")) { 
if(date_flag) { %>
State : <%=contact_Customer_Plant_State%><br>
State Code : <%=contact_Customer_State_Code%><br>
<%if(vSTAT_CD.size()>0){
for(int i=0; i<vSTAT_CD.size(); i++){ %>
<font size="1.5px" face="Arial">
	<%if(vSTAT_NM.elementAt(i).toString().contains("GSTIN")) { %>
		<%=vSTAT_NM.elementAt(i).toString()%> : <%=vSTAT_NO.elementAt(i)%><br>
	<% } else if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) { %>
		PAN : <%=(String)vSTAT_NO.elementAt(i)%><br>
	<% } %>
</font>
<% }} else { %>
<%if(!contact_Suppl_GSTIN_NO.trim().equals("")) { %>
GSTIN : <%=contact_Suppl_GSTIN_NO%><br>
<% } if(!contact_Suppl_PAN_NO.trim().equals(""))
{%>
PAN :  <%=contact_Suppl_PAN_NO%><br>
<%} } 
 }else { 
if(vSTAT_CD.size()>0){ 
				for(int i=0; i<vSTAT_CD.size(); i++)
							{	%>
								<font size="1.5px" face="Arial">
									<% if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) { %>
									PAN : <%=(String)vSTAT_NO.elementAt(i)%><br>
									<% } %>
									
								</font>
						<%	}} else {	%>
						<% if(vSTAT_NM.size()!=0) { %>
								<%	if(!contact_Suppl_PAN_NO.trim().equals(""))
									{	%>
										PAN :  <%=contact_Suppl_PAN_NO%>
										<br>	
								<%	}
									%>
							<% } else { %>
								&nbsp;
							<% } } 
 } } else if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")) { %>
<%if(vSTAT_CD.size()>0){ %>
	<%for(int i=0; i<vSTAT_CD.size(); i++){%>
		<font size="1.5px" face="Arial">
		<% if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) { %>
			PAN  : <%=(String)vSTAT_NO.elementAt(i)%> <br>
		<%} else {
				if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("TAN No.")) { %>  <!-- Hiren_20210204 As discussed with Mahesh Sir -->
		
				GSTIN No. : <%=(String)vSTAT_NO.elementAt(i)%>
					<%if(!vSTAT_EFF_DT.elementAt(i).equals("")) {%> DT. <%=(String)vSTAT_EFF_DT.elementAt(i)%> <%} %><br>
			<%}else{%>
			<%=(String)vSTAT_NM.elementAt(i)%> : <%=(String)vSTAT_NO.elementAt(i)%>
			<%if(!vSTAT_EFF_DT.elementAt(i).equals("")) {%>
				DT. <%=vSTAT_EFF_DT.elementAt(i)%>
			<%} %>
			 <br> 
		<% } }%>  
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
<tr valign="top"><td colspan="7">&nbsp;</td></tr>
<tr valign="middle"><td colspan="4"></td>
<td colspan="2" width="25%"><div align="center">
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
<%if((!invoice_title.equals("CREDIT") )  &&  (!invoice_title.equals("SUPPLEMENTARY INVOICE"))  && (!invoice_title.equals("DEBIT") ) ) { %>
<tr><td><div align="right"><font size="1.5px" face="Arial"><b>Invoice Date &nbsp;</b></font></div></td></tr><% } %>
<tr><td><div align="right"><font size="1.5px" face="Arial"><b>
<%if((!invoice_title.equals("CREDIT") )  &&  (!invoice_title.equals("SUPPLEMENTARY INVOICE"))  && (!invoice_title.equals("DEBIT") ) ) { %>
Payment Due Date&nbsp;
<%} else {%>
<%if(invoice_title.equals("SUPPLEMENTARY INVOICE")) {%>Debit Note Date:
<%} else if(invoice_title.equals("DEBIT")){%>Debit Note Date:
<%} else {%>Credit Note Date:
<%} }%></b></font></div></td></tr>
<tr><td><div align="right"><font size="1.5px" face="Arial"><b> 
<%if((!invoice_title.equals("CREDIT") )  &&  (!invoice_title.equals("SUPPLEMENTARY INVOICE"))  && (!invoice_title.equals("DEBIT") ) ) { %>
<%if(contract_type.equalsIgnoreCase("R")){%>R-gas&nbsp;<%}%><%if(contract_type.equalsIgnoreCase("C")  ){%>Tax&nbsp;<%}%>
SEIPL Invoice Seq No&nbsp;<%} else { %>
<%if(invoice_title.equals("SUPPLEMENTARY INVOICE") || invoice_title.equals("DEBIT")) {%>Debit Note No:
<%} else {%>Credit Note No: <%} }%>
</b></font></div></td></tr> </table></div>
</td>


<td colspan="1" width="15%">
<div align="center">
<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
<%if((!invoice_title.equals("CREDIT") )  &&  (!invoice_title.equals("SUPPLEMENTARY INVOICE"))  && (!invoice_title.equals("DEBIT") ) ) { %>
<tr><td><div align="right"><font size="1.5px" face="Arial"><b><%=customer_Invoice_DT%></b></font></div></td></tr><% } %>
<tr><td><div align="right"><font size="1.5px" face="Arial"><b>
<%if((!invoice_title.equals("CREDIT") )  &&  (!invoice_title.equals("SUPPLEMENTARY INVOICE"))  && (!invoice_title.equals("DEBIT") ) ) { %>
<%=customer_Invoice_Due_DT%><%} else {%><%=dr_cr_dt1%><%} %></b></font></div></td></tr>
<tr><td><div align="right"><font size="1.5px" face="Arial"><b>
<%if((!invoice_title.equals("CREDIT") )  &&  (!invoice_title.equals("SUPPLEMENTARY INVOICE"))  && (!invoice_title.equals("DEBIT") ) ) { %>
<%=invno%><%} else {%><%=dr_cr_doc_no1%><%} %>
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
<% if(!advance_payment_flag && !Tariff_flag && !Discount_flag) {  
%>
<%
if((criteria.equalsIgnoreCase("REV_INV") && invoice_title.equalsIgnoreCase("CREDIT")) || (invoice_title.equals("ORIGINAL") || invoice_title.equals("DUPLICATE") || invoice_title.equals("TRIPLICATE"))) { %>
<tr valign="top">
<td><div align="right"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>1&nbsp;<br><br><%}%><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>2&nbsp;<br><br><%}else{%>1&nbsp;<br><br><%}%><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>3&nbsp;<%}else{%>2&nbsp;<%}%><%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>4&nbsp;<%}else if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>3&nbsp;<%}%></font></div></td>
<td><div align="left"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>&nbsp;LNG&nbsp;(Regasified)<br><br><%}%><%if(contract_type.equalsIgnoreCase("R")){%>&nbsp;Regasification&nbsp;Tariff&nbsp;(USD/mmbtu)<%}else if(contract_type.equalsIgnoreCase("C")){%>&nbsp;LNG&nbsp;Tariff&nbsp;(USD/mmbtu)<%}else{%>&nbsp;LNG Delivered<%}%><%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>&nbsp;Offspec QTY<%}%><br><br>&nbsp;Gross Amount (USD)</font></div></td>
<!-- <td> -->
<%--  <div align="center"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%><% if(!invoice_title.equalsIgnoreCase("CREDIT")){%><a href="#" onClick="openInvoiceAtt1Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;1</a><br><br><%}}%><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>&nbsp;<br><br><%}else{%> <%if(!invoice_title.equalsIgnoreCase("CREDIT")){%><a href="#" onClick="openInvoiceAtt1Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;1</a><br><br><%}}%>&nbsp;</font></div>  --%>
<!-- </td> -->
<td><div align="center"><font size="1.5px" face="Arial"><%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>&nbsp;<br><br><%}%>USD<br><br><%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br>USD<%}%></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">
	<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
		<%=total_Gas_Delivered%>&nbsp;<br><br><%}%>
		<%if(contract_type.equals("S") || contract_type.equals("L")) { %><%=total_Gas_Delivered%><% } %>&nbsp;<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%><br><br><%=total_Offspec_Qty%>&nbsp;<%}%><br><br><%//=total_Invoice_Qty%>&nbsp;</font></div></td>
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
				<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>5&nbsp;<br><br>6&nbsp;<%}else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>4&nbsp;<br><br>5&nbsp;<%}else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("T") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>4&nbsp;<br><br>5&nbsp;<%}else{%>3&nbsp;<br><br>4&nbsp;<%}%>
				<%	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
					{	++cnt;%>
						<br><%if(i==0){%><br><%=(++sr_no)%><%}%>&nbsp;
				<%	}	%>
				<%if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L")){%>
					<%if(tcs_app_flag.equals("Y")){ %>
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
						<%if(tcs_app_flag.equals("Y")){ %>
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
						<%if(tcs_app_flag.equals("Y")){ %>
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
						<%if(tcs_app_flag.equals("Y")){ %>
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
						<%if(tcs_app_flag.equals("Y")){ %>
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
				<%if(tcs_app_flag.equals("Y")){ %>
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

<%} else { %>
<tr valign="top">
<td width="6%"><div align="right"><font size="1.5px" face="Arial"><b>1&nbsp;<br><br>2&nbsp;<br><br></b></font></div></td>
<td width="34%"><div align="left"><font size="1.5px" face="Arial"><b>LNG (Regasified) as per Invoice ref <%=invno%> dtd <%=customer_Invoice_DT%><br><br>Gross Amount (USD)<br><br></b></font></div></td>
<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>&nbsp;<br><br></b></font></div></td>
<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>USD<br><br>USD<br><br></b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b><%=qty1%><br><br>&nbsp;<br><br></b> </font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial" ><b><%=sale_price1%><br><br>&nbsp;<br><br></b></font></div></td>
<td width="15%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br><%=gross_amt_usd1%><br><br></b></font></div></td>
</tr>
<tr valign="top">
<% if(!criteria.equalsIgnoreCase("DIFF-QTY")){ %>
<td width="6%"><div align="right"><font size="1.5px" face="Arial"><b>3&nbsp;<br><br>4&nbsp;<br><br>5&nbsp;<br><br>6&nbsp;<br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++) 
{cnt=7+i;%><%=cnt%>&nbsp;<br><br><%}%><%=++cnt%>&nbsp;<br><br><%=++cnt%>&nbsp;<br><br></b></font></div></td>
<td width="34%"><div align="left"><font size="1.5px" face="Arial"><b>Exchange Rate in Invoice<br><br><% if(criteria.equalsIgnoreCase("DIFF-PRICE")) { %> Applicable Sales Rate	<%}else { %>Applicable Exchange Rate <% } %><br><br><% if(criteria.equalsIgnoreCase("DIFF-PRICE")) { %>Difference In Sales Rate <%}else {%>Difference in Exchange Rate <% } %> <br><br>Gross Amount <br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++){%>
<%=(String)TAX_NAME1.elementAt(i)%> <br><br><%}%><% if(tax_gst) { %>Total GST <%} else { %> Total Tax <% } %><br><br>Invoice Amount<br><br></b></font></div></td>
<%-- <td width="10%"><div align="center"><font size="1.5px" face="Arial"><a href="#"  onClick="openInvoiceAtt2Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>','<%=invoice_title%>','<%=dr_cr_dt1%>','<%=dr_cr_doc_no1%>');">Att&nbsp;1</a><br><br>&nbsp;</font></div></td> --%>
<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b><br><br><br><br><br>Rupees<br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++){%>
Rupees<br><br><%}%>Rupees<br><br>Rupees<br><br></b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>&nbsp;<br><br>&nbsp;<br><br>&nbsp;<br><br></b> </font></div></td>
<td width="15%"><div align="right"><font size="1.5px" face="Arial"><b><%=exg_rate1%>&nbsp;<br><br><% if(criteria.equalsIgnoreCase("DIFF-PRICE")){%><%=dr_cr_sale_rate%><%}else {%><%=dr_cr_exg_rt1%><%}%>&nbsp;<br><br><% if(criteria.equalsIgnoreCase("DIFF-PRICE")) { %><%=diff_sale_rate%><%}else {%><%=diff_exg_rt1%><%}%>&nbsp;<br><br><br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++){%>
<%=customer_Invoice_Tax_Rate1.elementAt(i)%> %<br><br><%}%>
</b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>&nbsp;<br><br>&nbsp;<br><br><%=dr_cr_gross_amt1%><br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++){%>
<%=(String)customer_Invoice_Tax_Amt1.elementAt(i)%><br><br><%}%><%=nf3.format(Double.parseDouble(""+Tot_tax_amt1))%><br><br><%=dr_cr_net_amt1%><br><br></b></font></div></td>
<%} else { %>
<td width="6%"><div align="right"><font size="1.5px" face="Arial"><b>3&nbsp;<br><br>4&nbsp;<br><br>5&nbsp;<br><br>6&nbsp;<br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code2.size();i++) 
{cnt=7+i;%><%=cnt%>&nbsp;<br><br><%}%><%=++cnt%>&nbsp;<br><br><%=++cnt%>&nbsp;<br><br></b></font></div></td>
<td width="34%"><div align="left"><font size="1.5px" face="Arial"><b>Exchange Rate in Invoice<br><br>Applicable Quantity <br><br> Difference In Quantity<br><br><% if(invoice_title.equalsIgnoreCase("SUPPLEMENTARY INVOICE")){%> DEBIT <% } else if(invoice_title.equalsIgnoreCase("DEBIT")){ %>DEBIT <%} %> Amount (Rupees) w/o VAT(Tax)<br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code2.size();i++){%>
<%=(String)TAX_NAME2.elementAt(i)%> <br><br><%}%><% if(tax_gst) { %>Total GST <%} else { %> Total Tax <% } %><br><br>Invoice Amount<br><br></b></font></div></td>
<%-- <td width="10%"><div align="center"><font size="1.5px" face="Arial"><a href="#" onClick="openInvoiceAtt2Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>','<%=invoice_title%>','<%=dr_cr_dt1%>','<%=dr_cr_doc_no1%>');">Att&nbsp;1</a><br><br>&nbsp;</font></div></td> --%>
<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b><br><br><br><br><br><br>Rupees<br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code2.size();i++){%>
Rupees<br><br><%}%>Rupees<br><br>Rupees<br><br></b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>&nbsp;<%=tot_invoice_qty%><br><br>&nbsp;<%=diff_qty1%>&nbsp;</b> </font></div></td>
<td width="15%"><div align="right"><font size="1.5px" face="Arial"><b><%=exg_rate1%>&nbsp;<br><br>&nbsp;<br><br><br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code2.size();i++){%>
<%=customer_Invoice_Tax_Rate2.elementAt(i) %> %<br><br><%}%>
</b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>&nbsp;<br><br>&nbsp;<br><br><%=gross_amt1%><br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code2.size();i++){%>
<%=(String)customer_Invoice_Tax_Amt2.elementAt(i)%><br><br><%}%><%=nf3.format(Double.parseDouble(""+Tot_tax_amt2))%><br><br><%=net_amt1%><br><br></b></font></div></td>
<%} %>
</tr>
<tr valign="bottom">
<td width="6%"><div align="right"><font size="1.5px" face="Arial"><b><%=++cnt%>&nbsp;<br><br></b></font></div></td>
<td width="34%"><div align="left"><font size="1.5px" face="Arial"><b>Net Amount Payable<br><br></b></font></div></td>
<!-- <td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>&nbsp;<br><br></b></font></div></td> -->
<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>Rupees<br><br></b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br></b> </font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br></b></font></div></td>
<td width="15%"><div align="right"><font size="1.5px" face="Arial"><b><%if(criteria.equalsIgnoreCase("DIFF-QTY")){%> <%=net_amt1%><%}else{%><%=dr_cr_net_amt1%> <%}%><br><br></b></font></div></td>					
</tr>
<%}%>
</table></td></tr>
<% }else if(TAX_ADV_ADJ_FLAG.equalsIgnoreCase("Y") || TAX_ADV_ADJ_CODE_GST.size()>0){

%>
<%if((criteria.equalsIgnoreCase("REV_INV") && invoice_title.equalsIgnoreCase("CREDIT")) || (invoice_title.equals("ORIGINAL") || invoice_title.equals("DUPLICATE") || invoice_title.equals("TRIPLICATE"))) {
%>
<tr valign="middle">
<td><div align="right"><font size="1.5px" face="Arial">
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
<%= ++sr_no%>&nbsp;<br><br>
<%}%>
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
<%= ++sr_no%>&nbsp;<br><br>
<%}
else{%><%= ++sr_no%>&nbsp;<br><br>
<%}%>
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
<%= ++sr_no%>&nbsp;
<%}
else{%><%= ++sr_no%>&nbsp;
<%}%>

<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
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
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
&nbsp;LNG&nbsp;(Regasified)<br><br>
<%}%>
<%if(contract_type.equalsIgnoreCase("R")){%>
&nbsp;Regasification&nbsp;Tariff&nbsp;(<%=Currency %>/mmbtu)
<%}
else if(contract_type.equalsIgnoreCase("C")){%>
&nbsp;LNG&nbsp;Tariff&nbsp;(<%=Currency %>/mmbtu)
<%}
else{%>&nbsp;LNG (Delivered) (<%=Currency %>)
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
else if(contract_type.equalsIgnoreCase("C")){%>
&nbsp;LNG&nbsp;Tariff&nbsp;
<%}
else{%>Rate
<%}%>(<%=Currency %>/mmbtu)
<%} %>
<!-- End for Discount Line Item -->
<br><br>&nbsp;Gross Amount (<%=Currency %>)</font></div></td>
<%-- <td><div align="center"><font size="1.5px" face="Arial">
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
 <a href="#" onClick="openInvoiceAtt1Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');"> 
 Att&nbsp;1</a><br><br> 
<%}%>
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
&nbsp;<br><br>
<%}else{%>
 <a href="#" onClick="openInvoiceAtt1Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">
Att&nbsp;1</a><br><br> 
<%}%>&nbsp;</font></div>
</td> --%>

<td><div align="center"><font size="1.5px" face="Arial">
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
&nbsp;<br><br><%}%><%=Currency %><br><br><%=Currency %>
<%if(Discount_flag) {%>
<br><br><%=Currency %>
<%} %>
<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<br><br><%=Currency %>
<%}%></font></div>
</td>

<td><div align="right"><font size="1.5px" face="Arial">
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
<%=total_Gas_Delivered%>&nbsp;<br><br>
<%}%>
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
<%//total_Gas_Delivered%>&nbsp;
<%} else {%>
<%//total_Gas_Delivered%>&nbsp;
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
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
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
<%} %><br><br>&nbsp;
</font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
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
<%//nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(Final_first_gross_amt)))%>&nbsp;
<%} }%>
<br><br><%=nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(Final_first_gross_amt)))%>&nbsp;
</font></div>
</td>
</tr>
<%if(invadjustcur.startsWith("2") || invadjustcur.startsWith("USD")){ %>
<tr valign="middle">
<td><div align="right"><font size="1.5px" face="Arial">
<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;<br>
<%}
else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>
<%= ++sr_no%>&nbsp;<br>
<%}
else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;<br>
<%}
else{%>
<%= ++sr_no%>&nbsp;<br>
<%}%>
</font></div></td>
<%String breakLine = "";
for(int i=0;i<invadjremark.length()/70;i++) { 
breakLine += "<br>";
 } %>
<td><div align="left"><font size="1.5px" face="Arial">&nbsp;<%=invadjremark%><br></font></div></td>
<td><div align="center"><font size="1.5px" face="Arial">
<%-- <a href="#" onClick="openInvoiceAttAdvanceAdjustmentPage('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;3</a> --%>
</font></div></td>
<td><div align="center"><font size="1.5px" face="Arial"><%if(invadjustcur.equalsIgnoreCase("1")){ %>Rupees<%}else{ %>USD <%} %></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%=invadjustmentamt %>&nbsp;</font></div></td>
</tr>
<%} %>
<tr valign="middle">
<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br>
<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;<br><br>
<%= ++sr_no%>&nbsp;
<%}
else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>
<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
<%}
else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
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
&nbsp;<br>&nbsp;Exchange Rate<br><br>&nbsp;Gross Amount
</font>
</div>
</td>
<%-- <td>
<div align="center">
<font size="1.5px" face="Arial">
&nbsp;<br><a href="#" onClick="openInvoiceAtt2Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;2</a><br><br>&nbsp;
</font>
</div>
</td> --%>
<td>
<div align="center"><font size="1.5px" face="Arial">&nbsp;<br>&nbsp;<br><br>Rupees</font></div>
</td>
<td>
<div align="right"><font size="1.5px" face="Arial">&nbsp;<br>&nbsp;<br><br><%//total_Invoice_Qty%>&nbsp;</font></div>
</td>
<td>
<div align="right"><font size="1.5px" face="Arial">&nbsp;<br><%=customer_Invoice_Exchg_Rate%>&nbsp;<br><br>&nbsp;</font>
</div>
</td>
<td>
<div align="right"><font size="1.5px" face="Arial">&nbsp;<br>&nbsp;<br><br><%=Final_first_gross_amt_inr%>&nbsp;</font>
</div>
</td>
</tr>
 <%if(!advFlg.equalsIgnoreCase("AA")){ %>
<tr valign="middle">
<td>
<div align="right">
<font size="1.5px" face="Arial">
<%
for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){++cnt;%><br>
<%if(i!=customer_Invoice_Tax_Code.size()){%><%=(++sr_no)%>&nbsp;
<% tax_nos.add(""+sr_no);
}%>
<%}%>
<%if(cnt>=1){%><br><%}%><br><br>
</font>
</div>
</td>
<%
total_tax_no = ""+sr_no;%>
<td>
<div align="left">
<font size="1.5px" face="Arial">
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
<br><%if(i==0){%><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
<%}%>
<%if(cnt>=1){%><br>&nbsp;<b><% if(tax_gst) { %>Total GST <%} else { %> Total Tax <% } %></b><%}%><br><br>
</font>
</div>
</td>
<td>
<div align="center">
<font size="1.5px" face="Arial">
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
<br><%if(i==0){%><%}%>&nbsp;
<%}%>
<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>
</font>
</div>
</td>
<td>
<div align="center">
<font size="1.5px" face="Arial">
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
<br><%if(i==0){%><%}%>Rupees
<%}%>
<%if(cnt>=1){%><br><b>Rupees</b><%}%><br><br>
</font>
</div>
</td>
<td>
<div align="right">
<font size="1.5px" face="Arial">
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
<br><%if(i==0){%><%}%>&nbsp;
<%}%>
<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>
</font>
</div>
</td>
<td>
<div align="right">
<font size="1.5px" face="Arial">
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
<br><%if(i==0){%><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
<%}%>
<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>
</font>
</div>
</td>
<td>
<div align="right">
<font size="1.5px" face="Arial">
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
<br><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
<%}%>
<%if(cnt>=1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%><br><br>
</font>
</div>
</td>
</tr>
<%} %>
<tr valign="middle">
<td><div align="right"><font size="1.5px" face="Arial">
<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;<br>
<%}
else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>
<%= ++sr_no%>&nbsp;<br>
<%}
else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;<br>
<%}
else{%>
<%= ++sr_no%>&nbsp;<br>
<%}%>
<%adj_no=""+sr_no;%>
</font></div></td>
<td><div align="left"><font size="1.5px" face="Arial">&nbsp;<%=invadjremark%><br></font></div></td>
<td><div align="center"><font size="1.5px" face="Arial">
<%-- <a href="#" onClick="openInvoiceAttAdvanceAdjustmentPage('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;3</a> --%>
</font></div></td>
<td><div align="center"><font size="1.5px" face="Arial">Rupees</font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br></font></div></td>
<%if(invadjustcur.equalsIgnoreCase("2")) {%>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%=invadjustmentamt_inr %>&nbsp;</font></div></td>
<%}else{ %>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%=invadjustmentamt %>&nbsp;</font></div></td>
<%} %>
</tr>
<%if(advFlg.equalsIgnoreCase("AA")){ %>
 <tr valign="middle">
 <td>
 <div align="right"><font size="1.5px" face="Arial"> <strong><%= ++sr_no%>&nbsp;</strong></font></div>
 </td>
 <td>
 <font size="1.5px" face="Arial">Total Invoice amount payable (<%=total_no%> - <%=adj_no%>)</font>
 </td>
 <td>
 &nbsp;
 </td>
 <td align="center"><font size="1.5px" face="Arial">Rupees</font>
 </td>
 <td>
 &nbsp;
 </td>
 <td>
 &nbsp;
 </td>
 <td>
 <div align="right"><font size="1.5px" face="Arial"><%=total_invoice_payable_inr%>&nbsp;</font></div>
 </td>
 </tr>
 <tr valign="middle">
 <td>
 <div align="right">
 <font size="1.5px" face="Arial">
 <%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++) {++cnt;%>
 <br><%if(i!=customer_Invoice_Tax_Code.size()){%><%=(++sr_no)%>&nbsp;
 <%tax_nos.add(""+sr_no); %>
<%}%>
 <%}%>
 <%if(cnt>=1){%><br><%}%><br>
 </font>
 </div>
 </td>
 <%
 total_tax_no = ""+sr_no;%>
 <td>
 <div align="left">
 <font size="1.5px" face="Arial">
 <%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){ %>
 <br><%if(i==0){%><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
 <%}%>
 <%if(cnt>=1){%><br>&nbsp;<b><% if(tax_gst) { %>Total GST <%} else { %> Total Tax <% } %></b><%}%><br>
 </font>
 </div>
 </td>
 <td>
 <div align="center">
 <font size="1.5px" face="Arial"> <%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++) {%>
 <br><%if(i==0){%><%}%>&nbsp;
 <%}%>
 <%if(cnt>=1){%><br>&nbsp;<%}%><br>
 </font>
 </div>
 </td>
 <td>
 <div align="center">
 <font size="1.5px" face="Arial">
 <%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++) {%>
 <br><%if(i==0){%><%}%>Rupees
 <%}%>
 <%if(cnt>=1){%><br><b>Rupees</b><%}%><br>
 </font>
 </div>
 </td>
 <td>
 <div align="right">
 <font size="1.5px" face="Arial">
 <%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++) {%>
 <br><%if(i==0){%><%}%>&nbsp;
 <%}%>
 <%if(cnt>=1){%><br>&nbsp;<%}%><br>
 </font>
 </div>
 </td>
 <td valign="middle">
 <div align="right">
 <font size="1.5px" face="Arial">
 <%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++) {%>
 <br><%if(i==0){%><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
 <%}%>
 <%if(cnt>=1){%><br>&nbsp;<%}%><br>
 </font>
 </div>
 </td>
 <td valign="middle">
 <div align="right">
 <font size="1.5px" face="Arial">
 <%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++) {%>
 <br><%if(i==0){%><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
 <%}%>
 <%if(cnt>=1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%><br>
 </font>
 </div>
 </td>
 </tr>
 <%} 
Vector adj_no_tax = new Vector();
for(int k=0;k<customer_Invoice_Tax_Code.size();k++) { 
	String abbr = ""+TAX_ADV_ADJ_ABBR_GST.get(customer_Invoice_Tax_Abbr.elementAt(k));
	String remark_ = ""+TAX_ADV_ADJ_REMARK_GST.get(abbr);
	String amt_ = ""+TAX_ADV_ADJ_AMT_GST.get(abbr);
	if(amt_.equals("") || amt_.equals(null) || amt_.equals("null")) {
		amt_ = "0.0"; 
	} else {
		amt_ = amt_.replace(",","");
	}
	if(!remark_.equals("") && Double.parseDouble(amt_)!=0.0)  { %>
	<tr valign="middle" >
	<td><div align="right"><font size="1.5px" face="Arial">
	<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	<%= ++sr_no%>&nbsp;<br>
	<%}
	else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>
	<%= ++sr_no%>&nbsp;<br>
	<%}
	else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
	<%= ++sr_no%>&nbsp;<br>
	<%}
	else{%>
	<%= ++sr_no%>&nbsp;<br>
	<%}%>
	<%
	adj_no_tax.add(""+sr_no);%>
	</font></div></td>
	<td style="vertical-align: middle;" ><div align="left"><font size="1.5px" face="Arial">&nbsp;<%=remark_%><br></font></div></td>
	<td><div align="center"><font size="1.5px" face="Arial">
<%-- 	<a href="#" onClick="openInvoiceAttAdvanceAdjustmentPage('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;3</a> --%>
	<br></font></div></td>
	<td><div align="center"><font size="1.5px" face="Arial">Rupees<br></font></div></td>
	<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br></font></div></td>
	<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br></font></div></td>
	<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%=nf3.format(Double.parseDouble(amt_))%>&nbsp;<br><br></font></div></td>
	</tr>
	<% } %>
<% } %>
 <%if(!advFlg.equalsIgnoreCase("AA")){ %>
<tr valign="middle">
<td>
<div align="right">
<font size="1.5px" face="Arial"><strong><%= ++sr_no%>&nbsp;</strong></font></div>
</td>
<td>
<font size="1.5px" face="Arial">&nbsp;Total Invoice amount payable (<%=total_no%> - <%=adj_no%>)</font>
</td>
<td>
&nbsp;
</td>
<td align="center"><font size="1.5px" face="Arial">Rupees</font>
</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td><div align="right">
<font size="1.5px" face="Arial"><%=total_invoice_payable_inr%>&nbsp;</font></div>
</td>
</tr><%} %>
<%if(adj_no_tax.size()>0) { %>
<tr valign="middle">
<td><div align="right"><font size="1.5px" face="Arial"><strong>
<%String pay = "";
for(int i=0; i<TAX_ADV_ADJ_CODE_GST.size(); i++){++cnt;%>
<%if(i!=TAX_ADV_ADJ_CODE_GST.size()){%><%=(++sr_no)%>&nbsp;<br>
<%
pay += sr_no +" + "; 
%>
<%}%>
<%}
if(pay.length()!=0 && TAX_ADV_ADJ_CODE_GST.size()>=1){
	pay = pay.substring(0, pay.length()-3);
}
%>
<%= ++sr_no%>&nbsp;</strong></font></div>
</td>
<td>
<font size="1.5px" face="Arial">
<%for(int i=0; i<TAX_ADV_ADJ_CODE_GST.size(); i++){ ++cnt;%>
<%if(i!=TAX_ADV_ADJ_CODE_GST.size()){ %>
&nbsp;<%=customer_Invoice_Tax_Name.elementAt(i)%> Payable<%}%>
&nbsp;(<%=""+tax_nos.elementAt(i)%><%=(""+adj_no_tax.elementAt(i)).equals("")?"":" - "+adj_no_tax.elementAt(i)%>)<br>
<% } %>
&nbsp;<% if(tax_gst) { %>Total GST <%} else { %>Total Tax <% } %> Payable (<%=pay%>)</font>
</td>
<td>
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){ ++cnt;%>
<%if(i!=customer_Invoice_Tax_Code.size()){%>&nbsp;<br><%}%>
<%}%>
&nbsp;
</td>
<td  align="center">
<font size="1.5px" face="Arial"><%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){++cnt;%>
&nbsp;<%if(i!=customer_Invoice_Tax_Code.size()){%>Rupees<br><%}%>
<%}%>
Rupees</font>
</td>
<td>
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){++cnt;%>
<%if(i!=customer_Invoice_Tax_Code.size()){%>&nbsp;<br><%}%>
<%}%>
&nbsp;
</td>
<td>
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){++cnt;%>
<%if(i!=customer_Invoice_Tax_Code.size()){%>&nbsp;<br><%}%>
<%}%>
&nbsp;
</td>
<td>
<div align="right">
<font size="1.5px" face="Arial">
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){++cnt;%>
<%if(i!=customer_Invoice_Tax_Code.size()){ %>
<%=total_compo_tax_payable.elementAt(i)%>&nbsp;<br>
<% }%>
<%}%>
<%=total_tax_payable%>&nbsp;</font></div>
</td>
</tr>
<%} %>
<tr valign="middle"><td><div align="right"><font size="1.5px" face="Arial"><strong><%=(++sr_no)%></strong>&nbsp;</font></div></td>
<td><div align="left"><font size="1.5px" face="Arial"><strong>&nbsp;Invoice Amount</strong></font></div></td>
<td>&nbsp;</td>
<td><div align="center"><font size="1.5px" face="Arial"><strong>Rupees</strong></font></div></td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td><div align="right"><font size="1.5px" face="Arial"><strong>
<%if(advFlg.equalsIgnoreCase("AA")){
 Double temp =  Double.parseDouble(total_tax_payable.replace(",", "")) + Double.parseDouble(total_invoice_payable_inr.replace(",", ""));%><%=nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(customer_Invoice_Net_Amt_INR)))%><%}else{%><%=nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(customer_Invoice_Net_Amt_INR)))%><%} %></strong>&nbsp;</font>
</div></td></tr>
<tr valign="middle">
<td style="border-top: 1px solid black;"><div align="right"><font size="1.5px" face="Arial"><b><%=(++sr_no)%>&nbsp;</b></font></div></td>
<td style="border-top: 1px solid black;"><div align="left"><font size="1.5px" face="Arial"><b>&nbsp;Net Amount Payable</b></font></div></td>
<!-- <td style="border-top: 1px solid black;"><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td> -->
<td style="border-top: 1px solid black;"><div align="center"><font size="1.5px" face="Arial"><b>Rupees</b></font></div></td>
<td style="border-top: 1px solid black;"><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
<td style="border-top: 1px solid black;"><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
<td style="border-top: 1px solid black;"><div align="right"><font size="1.5px" face="Arial"><b>
<%if(advFlg.equalsIgnoreCase("AA")){
 Double temp =  Double.parseDouble(total_tax_payable.replace(",", "")) + Double.parseDouble(total_invoice_payable_inr.replace(",", ""));%><%=nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(customer_Invoice_Net_Amt_INR)))%><%}else{%><%=nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(customer_Invoice_Net_Amt_INR)))%><%} %>&nbsp;</b></font></div></td>
</tr>
</table></td></tr>
<tr valign="middle">
<td colspan="7"><div align="center">&nbsp;</div>
    </td>
</tr>
<% }else { 
%>
<tr valign="top">
<td width="6%"><div align="right"><font size="1.5px" face="Arial"><b>1&nbsp;<br><br>2&nbsp;<br><br></b></font></div></td>
<td width="34%"><div align="left"><font size="1.5px" face="Arial"><b>LNG (Regasified) as per Invoice ref <%=invno%> dtd <%=customer_Invoice_DT%> <br><br>Gross Amount (USD)<br><br></b></font></div></td>
<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>&nbsp;<br><br></b></font></div></td>
<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>USD<br><br>USD<br><br></b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b><%=qty1%><br><br>&nbsp;<br><br></b> </font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial" ><b><%=sale_price1%><br><br>&nbsp;<br><br></b></font></div></td>
<td width="15%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br><%=gross_amt_usd1%><br><br></b></font></div></td>
</tr>
<tr valign="top">
<%if(!criteria.equalsIgnoreCase("DIFF-QTY")){ %>
<td width="6%"><div align="right"><font size="1.5px" face="Arial"><b>3&nbsp;<br><br>4&nbsp;<br><br>5&nbsp;<br><br>6&nbsp;<br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++) 
{cnt=7+i;%><%=cnt%>&nbsp;<br><br><%}%><%=++cnt%>&nbsp;<br><br><%=++cnt%>&nbsp;<br><br></b></font></div></td>
<td width="34%"><div align="left"><font size="1.5px" face="Arial"><b>Exchange Rate in Invoice<br><br><% if(criteria.equalsIgnoreCase("DIFF-PRICE")) { %> Applicable Sales Rate	<%}else { %>Applicable Exchange Rate <% } %><br><br><% if(criteria.equalsIgnoreCase("DIFF-PRICE")) { %>Difference In Sales Rate <%}else {%>Difference in Exchange Rate <% } %> <br><br>Gross Amount <br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++){%>
<%=(String)TAX_NAME1.elementAt(i)%> <br><br><%}%><% if(tax_gst) { %>Total GST <%} else { %>Total Tax <% } %><br><br> Invoice Amount<br><br></b></font></div></td>
<%-- <td width="10%"><div align="center"><font size="1.5px" face="Arial"><a href="#" onClick="openInvoiceAtt2Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>','<%=invoice_title%>','<%=dr_cr_dt1%>','<%=dr_cr_doc_no1%>');">Att&nbsp;1</a><br><br>&nbsp;</font></div></td> --%>
<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b><br><br><br><br><br><br>Rupees<br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++){%>
Rupees<br><br><%}%>Rupees<br><br>Rupees<br><br></b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>&nbsp;<br><br>&nbsp;<br><br>&nbsp;<br><br></b> </font></div></td>
<td width="15%"><div align="right"><font size="1.5px" face="Arial"><b><%=exg_rate1%>&nbsp;<br><br><% if(criteria.equalsIgnoreCase("DIFF-PRICE")){%><%=dr_cr_sale_rate%><%}else {%><%=dr_cr_exg_rt1%><%}%>&nbsp;<br><br><% if(criteria.equalsIgnoreCase("DIFF-PRICE")) { %><%=diff_sale_rate%><%}else {%><%=diff_exg_rt1%><%}%>&nbsp;<br><br><br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++){%>
<%=customer_Invoice_Tax_Rate1.elementAt(i)%> %<br><br><%}%>
</b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>&nbsp;<br><br>&nbsp;<br><br><%=dr_cr_gross_amt1%><br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++){%>
<%=(String)customer_Invoice_Tax_Amt1.elementAt(i)%><br><br><%}%><%=nf3.format(Double.parseDouble(""+Tot_tax_amt1))%><br><br><%=dr_cr_net_amt1%><br><br></b></font></div></td>
<%}else { %>
<td width="6%"><div align="right"><font size="1.5px" face="Arial"><b>3&nbsp;<br><br>4&nbsp;<br><br>5&nbsp;<br><br>6&nbsp;<br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code2.size();i++) 
{cnt=7+i;%><%=cnt%>&nbsp;<br><br><%}%><%=++cnt%>&nbsp;<br><br><%=++cnt%>&nbsp;<br><br></b></font></div></td>
<td width="34%"><div align="left"><font size="1.5px" face="Arial"><b>Exchange Rate in Invoice<br><br>Applicable Quantity <br><br> Difference In Quantity<br><br> Gross Amount<br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code2.size();i++){%>
<%=(String)TAX_NAME2.elementAt(i)%> (<%=(String)customer_Invoice_Tax_Rate2.elementAt(i)%>% &nbsp;<%=(String)TAX_ON_TITLE2.elementAt(i)%>)<br><br><%}%><% if(tax_gst) { %>Total GST <%} else { %> Total Tax <% } %><br><br> Invoice Amount<br><br></b></font></div></td>
<%-- <td width="10%"><div align="center"><font size="1.5px" face="Arial"><a href="#" onClick="openInvoiceAtt2Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>','<%=invoice_title%>','<%=dr_cr_dt1%>','<%=dr_cr_doc_no1%>');">Att&nbsp;1</a><br><br>&nbsp;</font></div></td> --%>
<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b><br><br><br><br><br><br>Rupees<br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code2.size();i++){%>
Rupees<br><br><%}%>Rupees<br><br>Rupees<br><br></b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>&nbsp;<%=tot_invoice_qty%><br><br>&nbsp;<%=diff_qty1%>&nbsp;</b> </font></div></td>
<td width="15%"><div align="right"><font size="1.5px" face="Arial"><b><%=exg_rate1%>&nbsp;<br><br>&nbsp;<br><br><br><br><br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code2.size();i++){%>
<%=customer_Invoice_Tax_Rate2.elementAt(i)%> %<br><br><%}%>
</b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>&nbsp;<br><br>&nbsp;<br><br><%=gross_amt1%><br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code2.size();i++){%>
<%=(String)customer_Invoice_Tax_Amt2.elementAt(i)%><br><br><%}%><%=nf3.format(Double.parseDouble(""+Tot_tax_amt2))%><br><br><%=net_amt1%><br><br></b></font></div></td>
<%} %>
</tr>
<tr valign="bottom">
<td width="6%"><div align="right"><font size="1.5px" face="Arial"><b><%=++cnt%>&nbsp;<br><br></b></font></div></td>
<td width="34%"><div align="left"><font size="1.5px" face="Arial"><b>Net Amount Payable<br><br></b></font></div></td>
<!-- <td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>&nbsp;<br><br></b></font></div></td> -->
<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>Rupees<br><br></b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br></b> </font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br></b></font></div></td>
<td width="15%"><div align="right"><font size="1.5px" face="Arial"><b><%if(criteria.equalsIgnoreCase("DIFF-QTY")){%> <%=net_amt1%><%}else{%><%=dr_cr_net_amt1%><% } %><br><br></b></font></div></td>
</tr>					
<% }%>
</table></td></tr>
<%}else{ 
// System.out.println(" in  laste else ");
%>
<%if((criteria.equalsIgnoreCase("REV_INV") && invoice_title.equalsIgnoreCase("CREDIT")) || (invoice_title.equals("ORIGINAL") || invoice_title.equals("DUPLICATE") || invoice_title.equals("TRIPLICATE"))) {
	%>
<tr valign="top">
<td><div align="right"><font size="1.5px" face="Arial">
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
<%= ++sr_no%>&nbsp;<br><br>
<%}%>
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
<%= ++sr_no%>&nbsp;<br><br>
<%} else{%><%= ++sr_no%>&nbsp;<br><br>
<%}%>
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
<%= ++sr_no%>&nbsp;
<%} else{%><%= ++sr_no%>&nbsp;
<%}%>
<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<br><br><%= ++sr_no%>&nbsp;
<%} else if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<br><br><%= ++sr_no%>&nbsp;
<%}%>
<%if(Discount_flag) {%>
<br><br><%= ++sr_no%>&nbsp;
<%}%>
</font></div></td>
<td><div align="left"><font size="1.5px" face="Arial">
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
&nbsp;LNG&nbsp;(Regasified)<br><br>
<%}%>
<%if(contract_type.equalsIgnoreCase("R")){%>
&nbsp;Regasification&nbsp;Tariff&nbsp;(<%=Currency %>/mmbtu)
<%}
else if(contract_type.equalsIgnoreCase("C")){%>
&nbsp;LNG&nbsp;Tariff&nbsp;(<%=Currency %>/mmbtu)
<%}
else{%>
&nbsp;LNG (Delivered) (<%=Currency %>)
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
else if(contract_type.equalsIgnoreCase("C")){%>
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
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
 <a href="#" onClick="openInvoiceAtt1Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">
Att&nbsp;1</a><br><br>  
<%}%>
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
&nbsp;<br><br>
<%}else{%>
<a href="#" onClick="openInvoiceAtt1Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">
Att&nbsp;1</a><br><br> 
<%}%>&nbsp;</font></div>
</td> --%>
<td><div align="center"><font size="1.5px" face="Arial">
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
&nbsp;<br><br><%}%><%=Currency %><br><br><%=Currency %>
<%if(Discount_flag) {%>
<br><br><%=Currency %>
<%} %>
<%if(offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<br><br><%=Currency %>
<%}%></font></div>
</td>
<td><div align="right"><font size="1.5px" face="Arial">
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
<%=total_Gas_Delivered%>&nbsp;<br><br>
<%}%>
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
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
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
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
<%if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")){%>
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
<%=nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(Final_first_gross_amt)))%>&nbsp;
<%} }%>
<br><br><%=nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(Final_first_gross_amt)))%>&nbsp;
</font></div>
</td>
</tr>
<%if(!Tariff_flag && advance_payment_flag) {
System.out.println("invadjustcur"+invadjustcur);	

if(invadjustcur.startsWith("2")){ %>
<%String breakLine = "<br><br>";
for(int i=0;i<invadjremark.length()/70;i++) { 
breakLine += "<br>";
 } %>
<tr valign="top">
<td><div align="right"><font size="1.5px" face="Arial">
<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
<%} else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>
<%= ++sr_no%>&nbsp;<%=breakLine%><%= ++sr_no%>&nbsp;
<%} else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;<br><br><br><br><%= ++sr_no%>&nbsp;
<%} else{%>
<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
<%}%>
</font></div></td>

<td><div align="left"><font size="1.5px" face="Arial">&nbsp;<%=invadjremark%><br><br>&nbsp;Gross Amount (<%=Currency %>)<br><br></font></div></td>
<td><div align="center"><font size="1.5px" face="Arial">
<%-- <a href="#" onClick="openInvoiceAttAdvanceAdjustmentPage('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;3</a> --%>
<br><br><br>&nbsp;</font></div></td>
<td><div align="center"><font size="1.5px" face="Arial"><%if(invadjustcur.equalsIgnoreCase("U")){ %><%=Currency %><%}else{ %><%=Currency %> <%} %><%=breakLine%><%=Currency %></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%=breakLine%>&nbsp;<br><br><br></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%=breakLine%>&nbsp;<br><br><br></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%=nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(invadjustmentamt))) %>&nbsp;<%=breakLine%><%=nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(Final_adjust_gross_amt)))%>&nbsp;<br><br></font></div></td>
</tr>
<%}} %>
<%if(Tariff_flag && advance_payment_flag) { %>
<tr valign="middle">
<td><div align="right"><font size="1.5px" face="Arial">
<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;<br><br><br>
<%= ++sr_no%>&nbsp;
<%} else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>
<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
<%} else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
<%} else{%>
<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
<%}%>
</font></div></td>
<td><div align="left"><font size="1.5px" face="Arial">&nbsp;<%=invadjremark%><br><br><br>&nbsp;Gross Amount (<%=Currency %>)<br><br></font></div></td>
<%-- <td><div align="center"><font size="1.5px" face="Arial"><a href="#" onClick="openInvoiceAttAdvanceAdjustmentPagewithTariff('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;2</a><br><br><br>&nbsp;</font></div></td> --%>
<td><div align="center"><font size="1.5px" face="Arial"><%if(invadjustcur.equalsIgnoreCase("U")){ %><%=Currency %><%}else{ %><%=Currency %>  <%} %><br><br><br><%=Currency %></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br>&nbsp;<br><br><br></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br>&nbsp;<br><br><br></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%=invadjustmentamt %><br><br><br><%=Final_adjust_gross_amt %></font></div></td>
</tr>
<tr valign="middle">
<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br>
<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;
<%} else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>
<%= ++sr_no%>&nbsp;
<%} else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;
<%} else{%>
<%= ++sr_no%>&nbsp;
<%}%>
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++) { ++cnt;%>
<br><%if(i==0){%><br><%=(++sr_no)%><%}%>&nbsp;
<%}System.out.println("cnt-------"+cnt);%>
<%if(cnt>=1){%><br><%}%><br><br><strong><%=(++sr_no)%></strong>&nbsp;<br>&nbsp;
</font>
</div>
</td>
<td>
<div align="left">
<font size="1.5px" face="Arial">
&nbsp;<br>&nbsp;<strong>Gross Amount</strong>
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++) {%>
<br><%if(i==0){%><br><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
<%}%>
<%if(cnt>=1){%><br>&nbsp;<b><% if(tax_gst) { %>Total GST <%} else { %> Total Tax <% } %></b><%}%><br><br>&nbsp;<strong>Invoice Amount</strong><br>&nbsp;
</font>
</div>
</td>
<td>
<div align="center">
<font size="1.5px" face="Arial">
<br>
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++) {%>
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
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++) {%>
<br><%if(i==0){%><br><%}%>Rupees
<%}%>
<%if(cnt>=1){%><br><b>Rupees</b><%}%><br><br><strong>Rupees</strong><br>&nbsp;
</font>
</div>
</td>
<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br><%//total_Invoice_Qty%>&nbsp;
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++) {%>
<br><%if(i==0){%><br><%}%>&nbsp;
<%}%>
<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
</font>
</div>
</td>
<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br>&nbsp;
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
<%}%>
<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
</font>
</div>
</td>
<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br><%=customer_Invoice_Gross_Amt_INR%>&nbsp;
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++) {%>
<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
<%}%>
<%if(cnt>=1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%><br><br><strong><%=nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(customer_Invoice_Net_Amt_INR)))%></strong>&nbsp;<br>&nbsp;
</font>
</div>
</td>
</tr>
<%} else if(!Tariff_flag && advance_payment_flag && invadjustcur.startsWith("1") ) { %>
<tr valign="middle">
<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br>
<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;<br><br>
<%= ++sr_no%>&nbsp;
<%}
else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>
<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
<%}
else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
<%}
else{%>
<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
<%}%>

</font>
</div>
</td>
<td><div align="left"><font size="1.5px" face="Arial">
&nbsp;<br>&nbsp;Exchange Rate<br><br>&nbsp;Gross Amount
</font>
</div>
</td>
<td><div align="center">
<font size="1.5px" face="Arial">
&nbsp;<br>
<%-- <a href="#" onClick="openInvoiceAtt2Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;2</a><br><br>&nbsp; --%>
</font>
</div>
</td>
<td><div align="center">
<font size="1.5px" face="Arial">
&nbsp;<br>&nbsp;<br><br>Rupees
</font>
</div>
</td>
<td><div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br>&nbsp;<br><br><%//total_Invoice_Qty%>&nbsp;
</font>
</div>
</td>
<td><div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br><%=customer_Invoice_Exchg_Rate%>&nbsp;<br><br>&nbsp;
</font>
</div>
</td>
<td><div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br>&nbsp;<br><br>
<%=Final_first_gross_amt_inr%>&nbsp;
</font>
</div>
</td>
</tr>

<tr valign="top">
<%String breakLine = "<br><br>";
for(int i=0;i<invadjremark.length()/70;i++) { 
breakLine += "<br>";
 } %>
<td><div align="right"><font size="1.5px" face="Arial">
<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;<br><br><br>
<%= ++sr_no%>&nbsp;
<%}
else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>
<%= ++sr_no%>&nbsp;<%=breakLine%><%= ++sr_no%>&nbsp;
<%}
else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
<%}
else{%>
<%= ++sr_no%>&nbsp;<br><br><br><%= ++sr_no%>&nbsp;
<%}%>
</font></div></td>
<td><div align="left"><font size="1.5px" face="Arial">&nbsp;<%=invadjremark%><br><br>&nbsp;Gross Amount<br><br></font></div></td>
<td><div align="center"><font size="1.5px" face="Arial">
<%-- <a href="#" onClick="openInvoiceAttAdvanceAdjustmentPage('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;3</a> --%>
<br><br><br>&nbsp;</font></div></td>
<td><div align="center"><font size="1.5px" face="Arial"><%if(invadjustcur.equalsIgnoreCase("2")){ %><%=Currency %><%=breakLine%><%=Currency %><%}else{ %>Rupees<%=breakLine%>Rupees <%} %></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br>&nbsp;<%=breakLine%></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<br><br>&nbsp;<%=breakLine%></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;<%=invadjustmentamt %>&nbsp;<%=breakLine%><%=nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(Final_adjust_gross_amt)))%>&nbsp;<br><br></font></div></td>
</tr>

<tr valign="middle">
<td>
<div align="right">
<font size="1.5px" face="Arial">
<br><%= ++sr_no%>&nbsp;
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
{ ++cnt;
%>
<br><%if(i==0){%><br><%}%>&nbsp;<%=(++sr_no)%>&nbsp;
<%}%>
<%if(cnt>=1){%><br><%}%><br><br><strong><%=(++sr_no)%></strong>&nbsp;<br>&nbsp;
</font>
</div>
</td>
<td>
<div align="left">
<font size="1.5px" face="Arial">
<br>&nbsp;<strong>Gross Amount</strong>

<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
{
%>
<br><%if(i==0){%><br><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
<%}%>
<%if(cnt>=1){%><br>&nbsp;<b><% if(tax_gst) { %>Total GST <%} else { %> Total Tax <% } %></b><%}%><br><br>&nbsp;<strong>Invoice Amount</strong><br>&nbsp;
</font>
</div>
</td>
<td>
<div align="center">
<font size="1.5px" face="Arial">
&nbsp;<br><br><br>&nbsp;
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
{%>
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
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
{%>
<br><%if(i==0){%><br><%}%>Rupees
<%}%>
<%if(cnt>=1){%><br><b>Rupees</b><%}%><br><br><strong>Rupees</strong><br>&nbsp;
</font>
</div>
</td>
<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br><%//total_Invoice_Qty%>&nbsp;
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
{%>
<br><%if(i==0){%><br><%}%>&nbsp;
<%}%>
<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
</font>
</div>
</td>
<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br>&nbsp;
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
{%>
<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
<%}%>
<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
</font>
</div>
</td>
<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br><%=nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(Final_adjust_gross_amt)))%>&nbsp;

<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
<%}%>
<%if(cnt>=1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%><br><br><strong><%=nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(customer_Invoice_Net_Amt_INR)))%></strong>&nbsp;<br>&nbsp;
</font>
</div>
</td>
</tr>

<%}else if(Tariff_flag && !advance_payment_flag){ %>
<tr valign="middle">
<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br>
<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>

<%= ++sr_no%>&nbsp;
<%}
else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>
<%= ++sr_no%>&nbsp;
<%}
else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;
<%}
else{%><%= ++sr_no%>&nbsp;<%}%>
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){++cnt;%>
<br><%if(i==0){%><br><%=(++sr_no)%><%}%>&nbsp;
<%}%>
<%if(cnt>=1){%><br><%}%><br><br><strong><%=(++sr_no)%></strong>&nbsp;<br>&nbsp;
</font>
</div>
</td>
<td>
<div align="left">
<font size="1.5px" face="Arial">
&nbsp;<br>&nbsp;<strong>Gross Amount</strong>
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
{ %>
<br><%if(i==0){%><br><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
<%}%>
<%if(cnt>=1){%><br>&nbsp;<b><% if(tax_gst) { %>Total GST <%} else { %> Total Tax <% } %></b><%}%><br><br>&nbsp;<strong>Invoice Amount</strong><br>&nbsp;
</font>
</div>
</td>
<td>
<div align="center">
<font size="1.5px" face="Arial">
&nbsp;<br>&nbsp;
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
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
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
{%>
<br><%if(i==0){%><br><%}%>Rupees
<%}%>
<%if(cnt>=1){%><br><b>Rupees</b><%}%><br><br><strong>Rupees</strong><br>&nbsp;
</font>
</div>
</td>
<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br><%//total_Invoice_Qty%>&nbsp;
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
<br><%if(i==0){%><br><%}%>&nbsp;
<%}%>
<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
</font>
</div>
</td>
<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br>&nbsp;
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
<%}%>
<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
</font>
</div>
</td>
<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br><%=customer_Invoice_Gross_Amt_INR%>&nbsp;
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
<%}%>
<%if(cnt>=1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%><br><br><strong><%=nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(customer_Invoice_Net_Amt_INR)))%></strong>&nbsp;<br>&nbsp;
</font>
</div>
</td>
</tr>
<%}else{%>
<tr valign="middle">
<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br>
<%if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;<br><br>
<%= ++sr_no%>&nbsp;
<%}
else if((contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("N")){%>
<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
<%}
else if((!contract_type.equalsIgnoreCase("R") && !contract_type.equalsIgnoreCase("C")) && offspec_Flag.trim().equalsIgnoreCase("Y")){%>
<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
<%}
else{%>
<%= ++sr_no%>&nbsp;<br><br><%= ++sr_no%>&nbsp;
<%}%>
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
{++cnt;%>
<br><%if(i==0){%><br><%=(++sr_no)%><%}%>&nbsp;
<%}%>
<%if(cnt>=1){%><br><%}%><br><br><strong><%=(++sr_no)%></strong>&nbsp;<br>&nbsp;
</font>
</div>
</td>
<td>
<div align="left">
<font size="1.5px" face="Arial">
&nbsp;<br>&nbsp;Exchange Rate<br><br>&nbsp;<strong>Gross Amount</strong>
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
{ %>
<br><%if(i==0){%><br><%}%>&nbsp;<%=(String)customer_Invoice_Tax_Name.elementAt(i)%>
<%}%>
<%if(cnt>=1){%><br>&nbsp;<b><% if(tax_gst) { %>Total GST <%} else { %> Total Tax <% } %></b><%}%><br><br>&nbsp;<strong>Invoice Amount</strong><br>&nbsp;
</font>
</div>
</td>

<td>
<div align="center">
<font size="1.5px" face="Arial">
&nbsp;<br>
<%-- <a href="#" onClick="openInvoiceAtt2Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>');">Att&nbsp;2</a><br><br>&nbsp; --%>
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
<br><%if(i==0){%><br><%}%>&nbsp;
<%}%>
<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
</font>
</div>
</td>
<td>
<div align="center">
<font size="1.5px" face="Arial">
&nbsp;<br>&nbsp;<br><br>Rupees
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
<br><%if(i==0){%><br><%}%>Rupees
<%}%>
<%if(cnt>=1){%><br><b>Rupees</b><%}%><br><br><strong>Rupees</strong><br>&nbsp;
</font>
</div>
</td>
<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br>&nbsp;<br><br><%//total_Invoice_Qty%>&nbsp;
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
<br><%if(i==0){%><br><%}%>&nbsp;
<%}%>
<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
</font>
</div>
</td>
<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br><%=customer_Invoice_Exchg_Rate%>&nbsp;<br><br>&nbsp;
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Rate.elementAt(i)%>&nbsp;%&nbsp;
<%}%>
<%if(cnt>=1){%><br>&nbsp;<%}%><br><br>&nbsp;<br>&nbsp;
</font>
</div>
</td>

<td>
<div align="right">
<font size="1.5px" face="Arial">
&nbsp;<br>&nbsp;<br><br><%=customer_Invoice_Gross_Amt_INR%>&nbsp;
<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++){%>
<br><%if(i==0){%><br><%}%><%=(String)customer_Invoice_Tax_Amt.elementAt(i)%>&nbsp;
<%}%>
<%if(cnt>=1){%><br><b><%=customer_Invoice_Tax_Amt_INR%></b>&nbsp;<%}%><br><br><strong><%=nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(customer_Invoice_Net_Amt_INR)))%></strong>&nbsp;<br>&nbsp;
</font>
</div>
</td>
</tr>
<%}%>
<tr valign="top">
<td><div align="right"><font size="1.5px" face="Arial"><b><%=(++sr_no)%>&nbsp;</b></font></div></td>
<td><div align="left"><font size="1.5px" face="Arial"><b>&nbsp;Net Amount Payable</b></font></div></td>
<!-- <td><div align="center"><font size="1.5px" face="Arial">&nbsp;</font></div></td> -->
<td><div align="center"><font size="1.5px" face="Arial"><b>Rupees</b></font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
<td><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
<td><div align="right"><font size="1.5px" face="Arial"><b><%=nf3.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(customer_Invoice_Net_Amt_INR)))%>&nbsp;</b></font></div></td>
</tr>
<%} else { 
%>
<tr valign="top">
<td width="6%"><div align="right"><font size="1.5px" face="Arial"><b>1&nbsp;<br><br>2&nbsp;<br><br></b></font></div></td>
<td width="34%"><div align="left"><font size="1.5px" face="Arial"><b>LNG (Regasified) as per Invoice ref <%=invno%> dtd <%=customer_Invoice_DT%> <br><br>Gross Amount (USD)<br><br></b></font></div></td>
<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>&nbsp;<br><br></b></font></div></td>
<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>USD<br><br>USD<br><br></b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b><%=qty1%><br><br>&nbsp;<br><br></b> </font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial" ><b><%=sale_price1%><br><br>&nbsp;<br><br></b></font></div></td>
<td width="15%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br><%=gross_amt_usd1%><br><br></b></font></div></td>
</tr>
<tr valign="top">
<%if(!criteria.equalsIgnoreCase("DIFF-QTY")){%>
<td width="6%"><div align="right"><font size="1.5px" face="Arial"><b>3&nbsp;<br><br>4&nbsp;<br><br>5&nbsp;<br><br>6&nbsp;<br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++) 
{cnt=7+i;%><%=cnt%>&nbsp;<br><br><%}%><%=++cnt%>&nbsp;<br><br><%=++cnt%>&nbsp;<br><br></b></font></div></td>
<td width="34%"><div align="left"><font size="1.5px" face="Arial"><b>Exchange Rate in Invoice<br><br><% if(criteria.equalsIgnoreCase("DIFF-PRICE")) { %> Applicable Sales Rate	<%}else { %>Applicable Exchange Rate <% } %><br><br><% if(criteria.equalsIgnoreCase("DIFF-PRICE")) { %>Difference In Sales Rate <%} else {%>Difference in Exchange Rate <% } %> <br><br>Gross Amount<br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++){%>
<%=(String)TAX_NAME1.elementAt(i)%> <br><br><%}%><% if(tax_gst) { %>Total GST <%} else { %> Total Tax <% } %><br><br> Invoice Amount<br><br></b></font></div></td>
<td width="10%"><div align="center"><font size="1.5px" face="Arial">
<%-- <a href="#" onClick="openInvoiceAtt2Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>','<%=invoice_title%>','<%=dr_cr_dt1%>','<%=dr_cr_doc_no1%>');">Att&nbsp;1</a><br><br>&nbsp;</font></div> --%>
</td>
<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b><br><br><br><br><br><br>Rupees<br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++){%>
Rupees<br><br><%}%>Rupees<br><br>Rupees<br><br></b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>&nbsp;<br><br>&nbsp;<br><br>&nbsp;<br><br></b> </font></div></td>
<td width="15%"><div align="right"><font size="1.5px" face="Arial"><b><%=exg_rate1%>&nbsp;<br><br><% if(criteria.equalsIgnoreCase("DIFF-PRICE")) { %><%=dr_cr_sale_rate%><%}  else {%><%=dr_cr_exg_rt1%><%}%>&nbsp;<br><br><% if(criteria.equalsIgnoreCase("DIFF-PRICE")) { %><%=diff_sale_rate%><%}else {%><%=diff_exg_rt1%><%}%>&nbsp;<br><br><br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++){%>
<%=(String)customer_Invoice_Tax_Rate1.elementAt(i)%> %
<% } %>
</b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>&nbsp;<br><br>&nbsp;<br><br><%if(criteria.equalsIgnoreCase("DIFF-QTY")){%><%=gross_amt1%><% } else {%><%=dr_cr_gross_amt1%> <%}%><br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code1.size();i++){%>
<%=(String)customer_Invoice_Tax_Amt1.elementAt(i)%><br><br><%}%><%=nf3.format(Double.parseDouble(""+Tot_tax_amt1))%><br><br><%=dr_cr_net_amt1%><br><br></b></font></div></td>
<%}else {%>
<td width="6%"><div align="right"><font size="1.5px" face="Arial"><b>3&nbsp;<br><br>4&nbsp;<br><br>5&nbsp;<br><br>6&nbsp;<br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code2.size();i++) 
{cnt=7+i;%><%=cnt%>&nbsp;<br><br><%}%><%=++cnt%>&nbsp;<br><br><%=++cnt%>&nbsp;<br><br></b></font></div></td>
<td width="34%"><div align="left"><font size="1.5px" face="Arial"><b>Exchange Rate in Invoice<br><br>Applicable Quantity <br><br> Difference In Quantity<br><br>Gross Amount <br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code2.size();i++){%>
<%=(String)TAX_NAME2.elementAt(i)%> <br><br><%}%><% if(tax_gst) { %>Total GST <%} else { %> Total Tax <% } %><br><br> Invoice Amount<br><br></b></font></div></td>
<%-- <td width="10%">
<div align="center"><font size="1.5px" face="Arial"><a href="#" onClick="openInvoiceAtt2Page('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=hlpl_inv_no%>','<%=customer_inv_no%>','<%=print_permission%>','<%=inv_approved_flag%>','<%=invoice_title%>','<%=dr_cr_dt1%>','<%=dr_cr_doc_no1%>');">Att&nbsp;1</a><br><br>&nbsp;</font></div></td> --%>
<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b><br><br><br><br><br><br>Rupees<br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code2.size();i++){%>
Rupees<br><br><%}%>Rupees<br><br>Rupees<br><br></b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>&nbsp;<%=tot_invoice_qty%><br><br>&nbsp;<%=diff_qty1%>&nbsp;</b> </font></div></td>
<td width="15%"><div align="right"><font size="1.5px" face="Arial"><b><%=exg_rate1%>&nbsp;<br><br>&nbsp;<br><br><br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code2.size();i++){%>
<%=(String)customer_Invoice_Tax_Rate2.elementAt(i)%> %
<% } %>
</b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br>&nbsp;<br><br>&nbsp;<br><br><%=gross_amt1%><br><br>
<%for(int i=0;i<customer_Invoice_Tax_Code2.size();i++){%>
<%=(String)customer_Invoice_Tax_Amt2.elementAt(i)%><br><br><%}%><%=nf3.format(Double.parseDouble(""+Tot_tax_amt2))%><br><br><%=net_amt1%><br><br></b></font></div></td>
<%} %>
</tr>
<tr valign="bottom">
<td width="6%"><div align="right"><font size="1.5px" face="Arial"><b><%=++cnt%>&nbsp;<br><br></b></font></div></td>
<td width="34%"><div align="left"><font size="1.5px" face="Arial"><b>Net Amount Payable<br><br></b></font></div></td>
<!-- <td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>&nbsp;<br><br></b></font></div></td> -->
<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>Rupees<br><br></b></font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br></b> </font></div></td>
<td width="10%"><div align="right"><font size="1.5px" face="Arial"><b>&nbsp;<br><br></b></font></div></td>
<td width="15%"><div align="right"><font size="1.5px" face="Arial"><b><%if(criteria.equalsIgnoreCase("DIFF-QTY")){%> <%=net_amt1%><%}else{%><%=dr_cr_net_amt1%><% } %><br><br></b></font></div></td>
</tr>					
<%}%>
</table>
</td>
</tr>
<%} %>
<table border="0" width="100%" align="center" cellpadding="2" cellspacing="0">
<tr align="center"><td colspan="7">&nbsp;</td></tr>
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
<%//}%>
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
<%if(!inv_approved_flag.equalsIgnoreCase("Y")  && activity_name.equalsIgnoreCase("APPROVE")){ %>
<div align="center">
Approval OK:&nbsp;
<input type="radio" name="rd" value="Y" onClick="doSubmit('A');" >&nbsp;<b>Yes</b>&nbsp;&nbsp;&nbsp;&nbsp;
<input type="radio" name="rd" value="N" onClick="doSubmit('A');" >&nbsp;<b>No</b>
</div>
<%} %>
<%if(activity_name.equalsIgnoreCase("APPROVE") && inv_approved_flag.equalsIgnoreCase("Y") &&  ((invoice_title.equals("CREDIT")  || invoice_title.equals("DEBIT") ||invoice_title.equals("SUPPLEMENTARY INVOICE") ) && CR_aprv_by.equalsIgnoreCase("0"))) { %>
<div align="center">
<%if(invoice_title.equals("SUPPLEMENTARY INVOICE")){%>DR Approval OK:&nbsp;<%}else if(invoice_title.equals("DEBIT")) {%>DR Approval OK:&nbsp; <%}else{%>
CR Approval OK:&nbsp; <%}%>
<input type="radio" name="rd" value="Y" onClick="doSubmit('B');" >&nbsp;<b>Yes</b>&nbsp;&nbsp;&nbsp;&nbsp;
<input type="radio" name="rd" value="N" onClick="doSubmit('B');" >&nbsp;<b>No</b>
</div>
<%} %>
<%if(activity_name.equalsIgnoreCase("CHECK")) { %>
<div align="center">
Checking OK:&nbsp;
<input type="radio" name="rd" value="Y" onClick="doSubmit('C');" <%if(check_flag.equals("Y")) { %>checked<% } %>>&nbsp;<b>Yes</b>&nbsp;&nbsp;&nbsp;&nbsp;
<input type="radio" name="rd" value="N" onClick="doSubmit('C');" <%if(check_flag.equals("N")) { %>checked<% } %>>&nbsp;<b>No</b></div>
<% } %>
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
<input type="hidden" name="TAX_ADV_ADJ_AMT" value="<%=TAX_ADV_ADJ_AMT%>">
<input type="hidden" name="TAX_ADV_ADJ_FLAG" value="<%=TAX_ADV_ADJ_FLAG %>">
<input type="hidden" name="invadjflag" value="<%=invadjflag%>">
<input type="hidden" name="invadjustmentamt" value="<%=invadjustmentamt%>">
<input type="hidden" name="Currency" value="<%=Adjust_cur%>">
<input type="hidden" name="invno_disp" value="<%=invno%>">
</form>
</body>
</html>