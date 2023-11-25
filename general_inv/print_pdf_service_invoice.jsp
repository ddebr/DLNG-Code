<%@ page import="java.util.*"%>

<jsp:useBean class="com.seipl.hazira.dlng.service_inv.DataBean_Service_Invoice" id="serviceInv" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.service_inv.DataBean_Service_Invoice_Pdf" id="pdfFile" scope="page"/> 
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<%
java.text.NumberFormat nf7 = new java.text.DecimalFormat("###,###,###,##0.00");
java.text.NumberFormat nf5 = new java.text.DecimalFormat("##########0");

String formate_inv_dt = "";
String formate_due_dt = "";
String contract_type = request.getParameter("cont_type")==null?"":request.getParameter("cont_type");

String invDt = request.getParameter("invDt")==null?"":request.getParameter("invDt");
//	System.out.println("invDt----"+invDt);
util.setInput_date(invDt);
util.init();
formate_inv_dt = util.getFormatted_Date();

String dueDt = request.getParameter("dueDt")==null?"":request.getParameter("dueDt");
util.setInput_date(dueDt);
util.init();
formate_due_dt = util.getFormatted_Date();

String mapping_id = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");
String cust_cd = request.getParameter("cust_cd")==null?"":request.getParameter("cust_cd");
// String tempcompnm = (String)session.getAttribute("tempcompnm")==null?"":(String)session.getAttribute("tempcompnm"); //Hiren_20201112
String cust_plant_cd = request.getParameter("cust_plant_cd")==null?"":request.getParameter("cust_plant_cd");
String period_start_dt = request.getParameter("period_start_dt")==null?"":request.getParameter("period_start_dt");
String period_end_dt = request.getParameter("period_end_dt")==null?"":request.getParameter("period_end_dt");
String new_inv_seq_no =  request.getParameter("new_inv_seq_no")==null?"":request.getParameter("new_inv_seq_no");
String fin_yr =   request.getParameter("fin_yr")==null?"":request.getParameter("fin_yr");
String inv_seq_no =   request.getParameter("inv_seq_no")==null?"":request.getParameter("inv_seq_no");

String invoice_title = request.getParameter("invoice_title")==null?"ORIGINAL":request.getParameter("invoice_title");
String customer_abbr = request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
String customer_plant_nm = request.getParameter("customer_plant_nm")==null?"":request.getParameter("customer_plant_nm");

String bill_cycle = request.getParameter("bill_cycle")==null?"":request.getParameter("bill_cycle");
String year = request.getParameter("year")==null?"":request.getParameter("year");
String month = request.getParameter("month")==null?"":request.getParameter("month");
String irn_flg = request.getParameter("irn_flg")==null?"":request.getParameter("irn_flg");

serviceInv.setCallFlag("FETCH_INVOICE_PREVIEW_DTL");
serviceInv.setContract_type(contract_type);
serviceInv.setInvDt(invDt);
serviceInv.setDueDt(dueDt);
serviceInv.setCust_cd(cust_cd);
serviceInv.setMapping_id(mapping_id);
serviceInv.setPlant_no(cust_plant_cd);
serviceInv.setPeriod_start_dt(period_start_dt);
serviceInv.setPeriod_end_dt(period_end_dt);
serviceInv.setNew_inv_seq_no(new_inv_seq_no);
serviceInv.setFin_yr(fin_yr);
serviceInv.setInvFlag("VIEW");
serviceInv.setInv_seq_no(inv_seq_no);
serviceInv.setMonth(month);
serviceInv.setYear(year);
serviceInv.setBill_cycle(bill_cycle);

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
String contact_Suppl_GST_DT = serviceInv.getContact_Suppl_GST_DT();
String contact_Suppl_CST_DT = serviceInv.getContact_Suppl_CST_DT();

String contact_Suppl_PAN_NO = serviceInv.getContact_Suppl_PAN_NO();	//BK20160211
String contact_Suppl_PAN_DT = serviceInv.getContact_Suppl_PAN_DT();
String contact_Suppl_GSTIN_NO = serviceInv.getContact_Suppl_GSTIN_NO();
String contact_Suppl_GSTIN_DT = serviceInv.getContact_Suppl_GSTIN_DT();
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
Vector Vqty = serviceInv.getVqty();
Vector Vrate = serviceInv.getVrate();
Vector Vamt = serviceInv.getVamt();

String calcBase = serviceInv.getCalcBase();
String grossAmt = serviceInv.getInvGrossAmt();
String netAmt = serviceInv.getInvNetAmt();
String totalTax = serviceInv.getInvTaxAmt();
String remark1 = serviceInv.getRemark1();
String remark2 = serviceInv.getRemark2();
String taxAmt_str = serviceInv.getTaxAmt_str();
String taxnm_str = serviceInv.getTaxnm_str();
String tax_size [] = null;
String tax_structure = serviceInv.getTax_structure();
if(taxAmt_str.contains("@@")){
	tax_size = taxAmt_str.split("@@");
}

Vector View_amount  = serviceInv.getView_amount();
Vector View_invoice_qty = serviceInv.getView_invoice_qty();
Vector View_km = serviceInv.getView_km();
Vector View_rate = serviceInv.getView_rate();
Vector View_service_inv_dt = serviceInv.getView_service_inv_dt();
Vector View_truck_inv_dt = serviceInv.getView_truck_inv_dt();
Vector View_truck_inv_no = serviceInv.getView_truck_inv_no();
Vector View_truck_nm = serviceInv.getView_truck_nm();
double total_qty = serviceInv.getTotal_qty(); 
double total_amt = serviceInv.getTotal_amt();
bill_cycle = serviceInv.getBill_cycle();
year = serviceInv.getYear();
month = serviceInv.getMonth();
String irn_no = serviceInv.getIrn_no();
String qr_code = serviceInv.getQr_code();
String signing_dt = serviceInv.getSigning_dt();
String trans_cont_no = serviceInv.getTrans_cont_no();
// System.out.println("irn_no---"+irn_no);
// System.out.println("qr_code---"+qr_code);

pdfFile.setCallFlag("Pdf_For_Service_Invoice_Dtl");
HttpServletRequest req = request;	
pdfFile.setRequest(req);
pdfFile.setInvoice_title(invoice_title);
pdfFile.setInvoice_date(invDt);
pdfFile.setCustomer_abbr(customer_abbr);
pdfFile.setCustomer_plant_nm(customer_plant_nm);
pdfFile.setContract_type(contract_type);
pdfFile.setHlpl_inv_seq_no(inv_seq_no);
pdfFile.setCustomer_Invoice_FGSA_Dt(customer_Invoice_FGSA_Dt);
pdfFile.setContact_Suppl_GSTIN_NO(contact_Suppl_GSTIN_NO);
// pdfFile.setContact_Suppl_CST_NO(contact_Suppl_CST_NO);
pdfFile.setContact_Suppl_GSTIN_DT(contact_Suppl_GSTIN_DT);

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
pdfFile.setCustomer_Invoice_DT(formate_inv_dt);
pdfFile.setCustomer_Invoice_Due_DT(formate_due_dt);
pdfFile.setCustomer_Invoice_End_DT(period_end_dt);
pdfFile.setContact_Suppl_PAN_NO(contact_Suppl_PAN_NO);		
pdfFile.setContact_Suppl_PAN_DT(contact_Suppl_PAN_DT);
pdfFile.setContact_Suppl_State(contact_Suppl_State);
pdfFile.setContact_Suppl_State_Code(contact_Suppl_State_code);
pdfFile.setSac_code(sac_code);
pdfFile.setSac_desc(service_desc);
pdfFile.setContact_customer_State(contact_Customer_Plant_State);
pdfFile.setContact_customer_State_Code(contact_Customer_State_Code);
pdfFile.setVsac_cd(Vsac_cd);
pdfFile.setVamt(Vamt);
pdfFile.setVrate(Vrate);
pdfFile.setVitem_desc(Vitem_desc);
pdfFile.setVqty(Vqty);
pdfFile.setTax_size(tax_size);
pdfFile.setTaxnm_str(taxnm_str);
pdfFile.setGrossAmt(grossAmt);
pdfFile.setTax_structure(tax_structure);
pdfFile.setTotalTax(totalTax);
pdfFile.setNetAmt(netAmt);
pdfFile.setRemark_1(remark1);
pdfFile.setRemark_2(remark2);
pdfFile.setCalcBase(calcBase);
pdfFile.setBill_period_start_dt(period_start_dt);
pdfFile.setBill_period_end_dt(period_end_dt);

pdfFile.setView_amount(View_amount);
pdfFile.setView_invoice_qty(View_invoice_qty);
pdfFile.setView_km(View_km);
pdfFile.setView_rate(View_rate);
pdfFile.setView_service_inv_dt(View_service_inv_dt);
pdfFile.setView_truck_inv_dt(View_truck_inv_dt);
pdfFile.setView_truck_inv_no(View_truck_inv_no);
pdfFile.setView_truck_nm(View_truck_nm);
pdfFile.setTotal_amt(total_amt);
pdfFile.setTotal_qty(total_qty);
pdfFile.setInv_financial_year(fin_yr);
pdfFile.setIrn_no(irn_no);
pdfFile.setQr_code(qr_code);
pdfFile.setTrans_cont_no(trans_cont_no);
pdfFile.setSigning_dt(signing_dt);

pdfFile.init();

String pdfpath="";
String url_start ="";

pdfpath = pdfFile.getInvoice_pdf_path().trim();
url_start = pdfFile.getUrl_start().trim();
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
System.out.println("pdfpath : "+pdfpath);
// System.out.println("year : "+year);
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
</html>