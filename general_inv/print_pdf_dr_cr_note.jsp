<%@ page import="java.util.*"%>

<jsp:useBean class="com.seipl.hazira.dlng.service_inv.DataBean_Service_Invoice" id="serviceInv" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.service_inv.DataBean_Service_Invoice_Pdf" id="pdfFile" scope="page"/> 
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<%
String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212
String modUrl = session.getAttribute("modUrl")==null?"":session.getAttribute("modUrl").toString();//HA20210814
String year=request.getParameter("year")==null?"":request.getParameter("year");
String month=request.getParameter("month")==null?"":request.getParameter("month");
String bill_cycle=request.getParameter("bill_cycle")==null?"":request.getParameter("bill_cycle");
String msg=request.getParameter("msg")==null?"":request.getParameter("msg");
String error_msg=request.getParameter("error_msg")==null?"":request.getParameter("error_msg");

String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");

String mapping_id = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");
String cont_type = request.getParameter("cont_type")==null?"":request.getParameter("cont_type");
String invDt = request.getParameter("invDt")==null?"":request.getParameter("invDt");
String cust_cd = request.getParameter("cust_cd")==null?"":request.getParameter("cust_cd");
String cust_plant_cd = request.getParameter("cust_plant_cd")==null?"":request.getParameter("cust_plant_cd");
String new_inv_seq_no = request.getParameter("new_inv_seq_no")==null?"":request.getParameter("new_inv_seq_no");
String fin_yr = request.getParameter("fin_yr")==null?"":request.getParameter("fin_yr");
String dr_cr_flag = request.getParameter("dr_cr_flag")==null?"":request.getParameter("dr_cr_flag");
String invoice_title = request.getParameter("invoice_title")==null?"ORIGINAL":request.getParameter("invoice_title");
String period_start_dt = request.getParameter("period_start_dt")==null?"":request.getParameter("period_start_dt");
String dr_cr_doc_no = request.getParameter("dr_cr_doc_no")==null?"":request.getParameter("dr_cr_doc_no");
String inv_seq_no = request.getParameter("inv_seq_no")==null?"":request.getParameter("inv_seq_no");
String customer_abbr = request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
String customer_plant_nm = request.getParameter("customer_plant_nm")==null?"":request.getParameter("customer_plant_nm");


serviceInv.setCallFlag("PREVIEW_DR_CR_NOTE");
serviceInv.setContract_type(cont_type);
serviceInv.setInvDt(invDt);
serviceInv.setCust_cd(cust_cd);
serviceInv.setMapping_id(mapping_id);
serviceInv.setPlant_no(cust_plant_cd);
serviceInv.setPeriod_start_dt(period_start_dt);
serviceInv.setNew_inv_seq_no(new_inv_seq_no);
serviceInv.setFin_yr(fin_yr);
serviceInv.setInvFlag("VIEW");
serviceInv.setDrcr_doc_no(dr_cr_doc_no);
serviceInv.setInv_seq_no(inv_seq_no);

serviceInv.init();

String sac_code = serviceInv.getSac_code();
String sac_name = serviceInv.getSac_name();
String rule_remark = serviceInv.getRule_remark();
String service_desc = serviceInv.getService_desc();
String contact_Suppl_Name = serviceInv.getContact_Suppl_Name();
String contact_Suppl_Person_Address = serviceInv.getContact_Suppl_Person_Address();
String contact_Suppl_Person_City = serviceInv.getContact_Suppl_Person_City();
String contact_Suppl_Person_Pin = serviceInv.getContact_Suppl_Person_Pin();
String contact_Suppl_GST_NO = serviceInv.getContact_Suppl_GST_NO();
String contact_Suppl_CST_NO = serviceInv.getContact_Suppl_CST_NO();
//	System.out.println("contact_Suppl_CST_NO****"+contact_Suppl_CST_NO);

String contact_Suppl_PAN_NO = serviceInv.getContact_Suppl_PAN_NO();	//BK20160211
String contact_Suppl_PAN_DT = serviceInv.getContact_Suppl_PAN_DT();
String contact_Suppl_GSTIN_NO = serviceInv.getContact_Suppl_GSTIN_NO();
String contact_Suppl_State = serviceInv.getContact_Suppl_State();
String contact_Suppl_State_code = serviceInv.getContact_Suppl_State_Code();

String contact_Customer_Name = serviceInv.getContact_Customer_Name();
String customer_Invoice_FGSA_Dt = serviceInv.getCustomer_Invoice_FGSA_Dt();
String invoice_Customer_Contact_Cd = serviceInv.getInvoice_Customer_Contact_Cd();
String invoice_Customer_Contact_Nm = serviceInv.getInvoice_Customer_Contact_Nm();
String contact_Customer_Person_Address = serviceInv.getContact_Customer_Person_Address();
String contact_Customer_Person_City = serviceInv.getContact_Customer_Person_City();
String contact_Customer_Person_Pin = serviceInv.getContact_Customer_Person_Pin();
String contact_Customer_GST_NO = serviceInv.getContact_Customer_GST_NO();
String contact_Customer_CST_NO = serviceInv.getContact_Customer_CST_NO();
//	System.out.println("contact_Customer_CST_NO****"+contact_Customer_CST_NO);
String contact_Customer_GST_DT = serviceInv.getContact_Customer_GST_DT();
String contact_Customer_CST_DT = serviceInv.getContact_Customer_CST_DT();
String contact_Customer_Plant_State = serviceInv.getContact_Customer_Plant_State();
String contact_Customer_State_Code = serviceInv.getContact_Customer_State_Code();

Vector vSTAT_CD = serviceInv.getvSTAT_CD();
Vector vSTAT_NM = serviceInv.getvSTAT_NM();
Vector vSTAT_NO = serviceInv.getvSTAT_NO();
Vector vSTAT_EFF_DT = serviceInv.getvSTAT_EFF_DT();

Vector Vsac_cd = serviceInv.getVsac_cd();
Vector Vitem_desc = serviceInv.getVitem_desc();

Vector tax_nm = serviceInv.getTax_nm();
Vector inv_tax_perc = serviceInv.getInv_tax_perc();
Vector drcr_tax_perc = serviceInv.getDrcr_tax_perc();
Vector diff_tax_perc = serviceInv.getDiff_tax_perc();
Vector inv_tax_amt = serviceInv.getInv_tax_amt();
Vector drcr_tax_amt = serviceInv.getDrcr_tax_amt();
//	Vector diff_tax_amt = serviceInv.getDiff_tax_amt();

String signing_dt = serviceInv.getSigning_dt();
String trans_cont_no = serviceInv.getTrans_cont_no();
String drcr_dt = serviceInv.getDrcr_dt();
String drcr_due_dt = serviceInv.getDrcr_due_dt();
String drcr_criteria = serviceInv.getDrcr_criteria();
String drcr_inr_km = serviceInv.getDrcr_inr_km();
String qty_unit = serviceInv.getQty_unit();
String rate_unit = serviceInv.getRate_unit();
String drcr_aprv_by = serviceInv.getDrcr_aprv_by();
String remark_1 = serviceInv.getDrcr_remark1();
String remark_2 = serviceInv.getDrcr_remark2();

double tot_dr_cr_qty = serviceInv.getTot_dr_cr_qty();
double tot_inv_qty = serviceInv.getTot_inv_qty();
double tot_diff_qty = serviceInv.getTot_diff_qty();
double inv_rate = serviceInv.getInv_rate();
double dr_cr_rate = serviceInv.getDr_cr_rate();
double diff_rate = serviceInv.getDiff_rate();
double drcr_tax_samt = serviceInv.getDrcr_tax_samt();
double drcr_net_amt_inr = serviceInv.getDrcr_net_amt_inr();
double total_inv_km = serviceInv.getTotal_inv_km();
double gross_amt_inr = serviceInv.getGross_amt_inr();
String formated_inv_dt =serviceInv.getFormated_inv_dt();

String drcr_gross_amt_inr = serviceInv.getDrcr_gross_amt_inr();
Vector View_truck_inv_dt = serviceInv.getView_service_inv_dt();
Vector View_truck_inv_no = serviceInv.getView_truck_inv_no();
Vector View_amount  = serviceInv.getView_amount();
Vector View_rate = serviceInv.getView_rate();
Vector View_km = serviceInv.getView_km();
Vector View_truck_nm = serviceInv.getView_truck_nm();
Vector View_invoice_qty = serviceInv.getView_invoice_qty();
Vector View_calc_bs = serviceInv.getView_calc_bs(); 
Vector View_diff_qty =  serviceInv.getView_diff_qty();
Vector View_dr_cr_qty = serviceInv.getView_dr_cr_qty();

double total_qty = serviceInv.getTotal_qty(); 
double total_amt = serviceInv.getTotal_amt();

String drcr = "";
if(dr_cr_flag.equalsIgnoreCase("cr")){
	drcr = "Credit Note";
}else if(dr_cr_flag.equalsIgnoreCase("dr")){
	drcr = "Debit Note";
}
String rateUnit  = "";
String irn_no = serviceInv.getIrn_no();
String qr_code = serviceInv.getQr_code();

pdfFile.setCallFlag("Pdf_For_Service_Invoice_DR_CR");
HttpServletRequest req = request;	
pdfFile.setRequest(req);
pdfFile.setInvoice_title(invoice_title);
pdfFile.setInvoice_date(invDt);
pdfFile.setCustomer_abbr(customer_abbr);
pdfFile.setCustomer_plant_nm(customer_plant_nm);
pdfFile.setContract_type(cont_type);
pdfFile.setContact_Suppl_Name(contact_Suppl_Name);
pdfFile.setContact_Suppl_Person_Address(contact_Suppl_Person_Address);
pdfFile.setContact_Suppl_Person_City(contact_Suppl_Person_City);
pdfFile.setContact_Suppl_Person_Pin(contact_Suppl_Person_Pin);
pdfFile.setContact_Customer_Name(contact_Customer_Name);
pdfFile.setContact_Person_Name_And_Designation(invoice_Customer_Contact_Nm);
pdfFile.setContact_Customer_Person_Address(contact_Customer_Person_Address);
pdfFile.setContact_Customer_Person_City(contact_Customer_Person_City);
pdfFile.setContact_Customer_Person_Pin(contact_Customer_Person_Pin);
// pdfFile.setTempcompname(tempcompnm);
pdfFile.setvSTAT_CD(vSTAT_CD);
pdfFile.setvSTAT_NM(vSTAT_NM);
pdfFile.setvSTAT_NO(vSTAT_NO);
pdfFile.setvSTAT_EFF_DT(vSTAT_EFF_DT);
pdfFile.setContact_Customer_GST_NO(contact_Customer_GST_NO);
pdfFile.setContact_Customer_CST_NO(contact_Customer_CST_NO);
pdfFile.setContact_Customer_GST_DT(contact_Customer_GST_DT);
pdfFile.setContact_Customer_CST_DT(contact_Customer_CST_DT);
pdfFile.setNew_inv_seq_no(serviceInv.getNew_inv_seq_no());
pdfFile.setCustomer_Invoice_DT(drcr_dt);
pdfFile.setCustomer_Invoice_Due_DT(drcr_due_dt);
pdfFile.setHlpl_drcr_docNo(dr_cr_doc_no);
// pdfFile.setCustomer_Invoice_End_DT(period_end_dt);
pdfFile.setContact_Suppl_PAN_NO(contact_Suppl_PAN_NO);		
pdfFile.setContact_Suppl_PAN_DT(contact_Suppl_PAN_DT);
pdfFile.setContact_Suppl_State(contact_Suppl_State);
pdfFile.setContact_Suppl_State_Code(contact_Suppl_State_code);
pdfFile.setSac_code(sac_code);
pdfFile.setSac_desc(service_desc);
pdfFile.setContact_customer_State(contact_Customer_Plant_State);
pdfFile.setContact_customer_State_Code(contact_Customer_State_Code);
pdfFile.setHlpl_inv_seq_no(inv_seq_no);
pdfFile.setTrans_cont_no(trans_cont_no);
pdfFile.setDr_cr_due_dt(drcr_due_dt);
pdfFile.setQty_unit(qty_unit);
pdfFile.setRate_unit(rate_unit);
pdfFile.setDrcr_criteria(drcr_criteria);
pdfFile.setTax_nm(tax_nm);
pdfFile.setTot_inv_qty(tot_inv_qty);
pdfFile.setTot_dr_cr_qty(tot_dr_cr_qty);
pdfFile.setTot_diff_qty(tot_diff_qty);
pdfFile.setInv_rate(inv_rate);
pdfFile.setDr_cr_rate(dr_cr_rate);
pdfFile.setDiff_rate(diff_rate);
pdfFile.setInv_tax_perc(inv_tax_perc);
pdfFile.setDrcr_tax_perc(drcr_tax_perc);
pdfFile.setDiff_tax_perc(diff_tax_perc);
pdfFile.setDrcr_gross_amt_inr(drcr_gross_amt_inr);
pdfFile.setDrcr_tax_amt(drcr_tax_amt);
pdfFile.setDrcr_tax_samt(drcr_tax_samt);
pdfFile.setDrcr_net_amt_inr(drcr_net_amt_inr);
pdfFile.setRemark_1(remark_1);
pdfFile.setRemark_2(remark_2);
pdfFile.setInv_financial_year(fin_yr);
pdfFile.setView_calc_bs(View_calc_bs);
pdfFile.setDrcr(drcr);
pdfFile.setView_dr_cr_qty(View_dr_cr_qty);
pdfFile.setView_diff_qty(View_diff_qty);
pdfFile.setView_truck_inv_no(View_truck_inv_no);
pdfFile.setView_amount(View_amount);
pdfFile.setView_truck_inv_dt(View_truck_inv_dt);
pdfFile.setView_truck_nm(View_truck_nm);
pdfFile.setView_invoice_qty(View_invoice_qty);
pdfFile.setIrn_no(irn_no);
pdfFile.setQr_code(qr_code);
pdfFile.setGross_amt_inr(gross_amt_inr);
pdfFile.setFormated_inv_dt(formated_inv_dt);
pdfFile.init();

String pdfpath="";
String url_start ="";

pdfpath = pdfFile.getInvoice_pdf_path().trim();
url_start = pdfFile.getUrl_start().trim();
// System.out.println("pdfpath---"+pdfpath);
if(pdfpath==null || pdfpath.equals(""))
{
	
}
else
{
	pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
 	System.out.println("inner pdfPath = "+pdfpath);
 	pdfpath = pdfpath.substring(1);	
 	pdfpath = url_start+"/pdf_reports/pdf_files"+pdfpath;
//	 	
}
%>

<body bgcolor="white" onload="refreshopener('<%=year %>','<%=month %>','<%=bill_cycle %>');window.open('<%=pdfpath%>');" >
</body>
<script>
function refreshopener(year,month,bill_cycle)
{	
	
	var ind=window.opener.document.forms[0].index_hid.value;
	var sz=window.opener.document.forms[0].size_hid.value;
	
	var pdf_title="";
	
	if(sz==1)
	{
		pdf_title=window.opener.document.forms[0].invoice_title.value+"-";
	}
	else
	{ //alert("in else---"+sz);
		for(var i=0;i<parseInt(sz);i++)
		{
			pdf_title+=window.opener.document.forms[0].invoice_title[i].value+"-";
			//alert(pdf_title);
		}
	}
	
	window.opener.document.forms[0].inv_title_string.value=pdf_title;
	
	window.opener.document.forms[0].year.value = year;
	window.opener.document.forms[0].month.value = month;
	window.opener.document.forms[0].bill_cycle.value = bill_cycle;
// 	alert("before1"+pdf_title);
	window.opener.refreshPage1();
	window.close();
	/* alert("before");
	var url1=window.opener.document.URL;
	var url2=url1+"&inv_title_string="+inv_title_string;
	window.opener.location.replace(url2); */
}
</script>