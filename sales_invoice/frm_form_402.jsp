<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.accordion {
  background-color: #eee;
  color: #444;
  cursor: pointer;
  padding: 18px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
  transition: 0.4s;
}

.active, .accordion:hover {
  background-color: #ccc; 
}

.panel {
  padding: 0 18px;
  display: none;
  background-color: white;
  overflow: hidden;
}
</style>
<style>
.readonly {
  background-color: yellow;
}
</style>
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
	String contact_Customer_Person_State = salesInv.getContact_Customer_Person_State();
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
	System.out.println("contact_Customer_CST_NO****"+contact_Customer_CST_NO);
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
	String NoT="";
	
	if(contact_Customer_Person_State.contains("Gujarat")){
		NoT = "Sale Within Gujarat";
	}else{
		NoT = "Inter State Sale";
	}
	
	String cst_no="",cst_dt="";
	String vat_no="",vat_dt="";
	String gstin_no="",gstin_dt="";
	
	if(vSTAT_CD.size()>0){ 
		for(int i=0; i<vSTAT_CD.size(); i++){
			 if(vSTAT_NM.elementAt(i).toString().contains("CST TIN No")) {
				 
				 cst_no = vSTAT_NO.elementAt(i)+"";
				 cst_dt = vSTAT_EFF_DT.elementAt(i)+"";
				 
			}else if(vSTAT_NM.elementAt(i).toString().contains("GVAT TIN No.")){
				vat_no = vSTAT_NO.elementAt(i)+"";
				vat_dt = vSTAT_EFF_DT.elementAt(i)+"";
			}else if(vSTAT_NM.elementAt(i).toString().contains("GSTIN")) {
				
				if(!vSTAT_NO.elementAt(i).equals("")) {
					
    				gstin_no = vSTAT_NO.elementAt(i)+"";
    				gstin_dt = vSTAT_EFF_DT.elementAt(i)+"";
				}
				
			}else if(vSTAT_NM.elementAt(i).toString().contains("TAN No.")) {
				if(gstin_no.equals("")) {
					gstin_no = vSTAT_NO.elementAt(i)+"";
					gstin_dt = vSTAT_EFF_DT.elementAt(i)+"";
				}
			}
		}
	}else{
		 cst_no = contact_Customer_CST_NO;
		 cst_dt = contact_Customer_CST_DT;
		
	}
%>

<body background="../images/draft_copy.JPG">
<form action="">
	<table width="100%">
		<tr>
			<td align="center" colspan="12">
				<h1>FORM-402</h1>
			</td>
		</tr>
		<tr>
			<td colspan="12">
				<h2><button type="button" class="accordion" style="font-size: 20px;">Basic Info</button></h2>
			</td>
		</tr>
		
		<tr>
			<td colspan="5">
				Check Post Name <font color="red">*</font>  
			</td>
			
			<td colspan="1">
				<input type="text" size="30" required="required"   id="checkPost" class="readonly" readonly="readonly">
				<script>
					document.getElementById("checkPost").value = window.opener.document.forms[0].checkPost.value;
				</script>   
			</td>
			
			<td colspan="5" align="right">
				Place from Which Goods are Dispatched <font color="red">*</font>  
			</td>
			
			<td colspan="1">
				<input type="text" size="30" value="Gujarat" class="readonly" readonly="readonly">  
			</td>
		</tr>
		
		<tr>
			<td colspan="5">
				District from Which Goods are Dispatched
			</td>
			
			<td colspan="1">
				<input type="text" size="30">  
			</td>
			
			<td colspan="5" align="right">
				Place to Which Goods are Dispatched <font color="red">*</font>  
			</td>
			
			<td colspan="1">
				<input type="text" size="30" value="<%=contact_Customer_Person_State%>" class="readonly" readonly="readonly">  
			</td>
		</tr>
		
		<tr>
			<td colspan="5">
				District to Which Goods are Dispatched
			</td>
			<td colspan="1">
				<input type="text" size="30">  
			</td>
		</tr>	
		
		
		<tr>
			<td colspan="12">
				<h2><button style="font-size: 20px;" type="button" class="accordion">Consignor Details</button></h2>
			</td>
		</tr>
		<tr>
		<td colspan="5">
				CST Certificate Number <font color="red">*</font>  
			</td>
			
			<td colspan="1">
				<input type="text" class="readonly" size="30" value="<%=contact_Suppl_CST_NO%>" readonly="readonly" style="text-align: right;">  
			</td>
			
			<td colspan="5" align="right">
				Nature of Transactions <font color="red">*</font>  
			</td>
			
			<td colspan="1">
				<input type="text" size="30" value="<%=NoT %>" class="readonly" readonly="readonly">  
			</td>
		</tr>
		
		<tr>
			<td colspan="12">
				<h2><button style="font-size: 20px;" type="button" class="accordion">Consignee Details</button></h2>
			</td>
		</tr>
		<tr>
			<td colspan="5">
				Name <font color="red">*</font>  
			</td>
			
			<td colspan="1">
				<input type="text" size="30" class="readonly"  value="<%=contact_Customer_Name%>" readonly="readonly" >  
			</td>
			
			<td colspan="5" align="right">
				Address <font color="red">*</font>  
			</td>
			
			<td colspan="1">
				
				<textarea rows="3" cols="30" style="text-align: left;" class="readonly" readonly="readonly" >
					<%=contact_Customer_Person_Address +","+contact_Customer_Person_City+" - "+contact_Customer_Person_Pin%>
				</textarea>  
			</td>
		</tr>
		
		<tr>
			<td colspan="5">
				VAT Registration Certificate No.   
			</td>
			
			<td colspan="1">
				<input type="text" size="30" value="<%=vat_no %>">  
			</td>
			
			<td colspan="5" align="right">
				VAT Registration Effective date
			</td>
			
			<td colspan="1">
				<input type="text" size="30" value="<%=vat_dt %>">  
			</td>
		</tr>
		
		<tr>
			<td colspan="5">
				CST Registration No.
			</td>
			
			<td colspan="1">
				<input type="text" size="30" value="<%=cst_no%>" class="readonly" readonly="readonly">  
			</td>
			
			<td colspan="5" align="right">
				CST Registration Effective date
			</td>
			
			<td colspan="1">
				<input type="text" size="30" value="<%=cst_dt%>" class="readonly" readonly="readonly">  
			</td>
		</tr>
		
		<tr>
			<td colspan="5">
				GSTIN Registration No.
			</td>
			
			<td colspan="1">
				<input type="text" size="30" value="<%=gstin_no%>" class="readonly" readonly="readonly">  
			</td>
			
			<td colspan="5" align="right">
				GSTIN Registration Effective date
			</td>
			
			<td colspan="1">
				<input type="text" size="30" value="<%=gstin_dt%>" class="readonly" readonly="readonly">  
			</td>
		</tr>
		
		<tr>
			<td colspan="5">
				Telephone
			</td>
			
			<td colspan="1">
				<input type="text" size="30">  
			</td>
			
			<td colspan="5" align="right">
				consignee Fax No.
			</td>
			
			<td colspan="1">
				<input type="text" size="30">  
			</td>
		</tr>
		
		<tr>
			<td colspan="5">
				Consigned Value Rs. <font color="red">*</font> (Total Value of Invoice)
			</td>
			
			<td colspan="1">
				<input type="text" size="30" style="text-align: right;" value="" class="readonly" readonly="readonly" id="net_amt_inr">
				<script>
					document.getElementById("net_amt_inr").value = window.opener.document.forms[0].net_amt_inr.value;
				</script>  
			</td>
		</tr>
		
		<tr>
			<td colspan="11">
				Whether Shipping Address is within the state of Gujarat and shipping is to dealer other than consignee, as declared above ?
			</td>
			
			<td colspan="1">
				<input type="text" size="30" >  
			</td>
		</tr>
		
		
		<tr>
			<td colspan="11">
				Whether this is final shipping to original buyer outside the state of Gujarat
			</td>
			
			<td colspan="1">
				<input type="text" size="30">  
			</td>
		</tr>
		
		<tr>
			<td colspan="12"><h2><button  style="font-size: 20px;" type="button" class="accordion">Commodity Details</button></h2></td>
		</tr>	
		
		<tr>
			<td colspan="2" >Invoice No. <font color="red">*</font></td>
			<td colspan="2"><input type="text" size="15" value="<%=invno %>" class="readonly" readonly="readonly"></td>
			
			<td colspan="2" align="right"> Invoice Date<font color="red">*</font></td>
			<td colspan="2"><input type="text" size="15" class="readonly" value="<%=inv_dt %>" readonly="readonly"></td>
		</tr>
		
		 <tr>
			<td colspan="2">Quantity <font color="red">*</font></td>
			<td colspan="2"><input type="text" size="15" class="readonly" readonly="readonly" id="total_gas">
			
				<script type="text/javascript">
					document.getElementById("total_gas").value=window.opener.document.forms[0].total_qty.value;
				</script>
			</td>
			
			<td colspan="2" align="right"> Rate of tax <font color="red">*</font></td>
			<td colspan="2"><input type="text" size="15" class="readonly" readonly="readonly" id="taxRate">
				<script type="text/javascript">
					document.getElementById("taxRate").value=window.opener.document.forms[0].taxRate.value;
				</script>
			
			</td>
			
			<td colspan="2" align="right">Amount <font color="red">*</font></td>
			<td colspan="2"><input type="text" size="15" class="readonly" readonly="readonly" id="amt">
			<script>
					document.getElementById("amt").value = window.opener.document.forms[0].net_amt_inr.value;
				</script>  
			
			</td>
			
		</tr> 
		<tr>
			<td colspan="2">Unit of Measurement <font color="red">*</font></td>
			<td colspan="2"><input type="text" size="15" class="readonly" readonly="readonly" value="Nos."></td>
			<td colspan="2" align="right"> Commodity Name<font color="red">*</font></td>
			<td colspan="2"><input type="text" size="30" class="readonly" readonly="readonly" value="Liquified Natural Gas (LNG)"></td>
		</tr>	
		<tr>
			<td colspan="12">
				<h2><button style="font-size: 20px;" type="button" class="accordion">Transporter Details 
						<span id="msg" style="visibility: hidden;color: red"> No Truck Linked with Transporter-Driver-Truck</span>
					</button>
				</h2>
			
			<script type="text/javascript">
				if(window.opener.document.forms[0].truckLinkedFlg.value=='N'){
					document.getElementById("msg").style.visibility='visible';
				}
				
				</script>
			</td>
		</tr>	
		
		<tr>
			<td colspan="2">Name <font color="red">*</font></td>
			<td colspan="2"><input type="text" size="30" id="truck_trans_nm" readonly="readonly" class="readonly">
				<script>
					document.getElementById("truck_trans_nm").value = window.opener.document.forms[0].truck_trans_nm.value;
				</script>  
			</td>
			
			<td colspan="2" align="right"> Address</td>
			<td colspan="2">
<!-- 			<input type="text" size="30" id="truck_trans_addrs"> -->
				<textarea rows="3" cols="30" id="truck_trans_addrs" class="readonly" readonly="readonly"></textarea>
			<script>
				document.getElementById("truck_trans_addrs").value = window.opener.document.forms[0].truck_trans_addrs.value;
			</script>
			</td>
			
			<td colspan="2" align="right"> Owner / Partner Name </td>
			<td colspan="2"><input type="text" size="30" ></td>
		</tr>
		
		<tr>
			<td colspan="2" align="left"> Vehicle No. <font color="red">*</font></td>
			<td colspan="2"><input type="text" size="30" id="truck_nm" class="readonly" readonly="readonly">
			<script>
				document.getElementById("truck_nm").value = window.opener.document.forms[0].truck_nm.value;
			</script>
			</td>
			
			<td colspan="2" align="right">LR No.</td>
			<td colspan="2"><input type="text" size="30"></td>
			
			<td colspan="2" align="right"> LR date</td>
			<td colspan="2"><input type="text" size="30"></td>
		</tr>
		
		
		<tr>
			<td colspan="12"><h2><button style="font-size: 20px;" type="button" class="accordion">Driver Details</button></h2></td>
		</tr>	
		
		<tr>
			<td colspan="2">Name <font color="red">*</font></td>
			<td colspan="2"><input type="text" size="30" id="truck_driver" class="readonly" readonly="readonly">
			<script>
				document.getElementById("truck_driver").value = window.opener.document.forms[0].truck_driver.value;
			</script>
			</td>
			
			<td colspan="2" align="right"> Driving Licence No. <font color="red">*</font> </td>
			<td colspan="2"><input type="text" size="20" id="truck_driver_lic_no" class="readonly" readonly="readonly">
			<script>
				document.getElementById("truck_driver_lic_no").value = window.opener.document.forms[0].truck_driver_lic_no.value;
			</script>
			</td>
			
			<td colspan="2" align="right"> Licence Issueing State  <font color="red">*</font> </td>
			<td colspan="2"><input type="text" size="20" id="truck_lic_state" class="readonly" readonly="readonly">
			<script>
				document.getElementById("truck_lic_state").value = window.opener.document.forms[0].truck_lic_state.value;
			</script>
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="left"> Address</td>
			<td colspan="10">
			<textarea rows="3" cols="30" id="truck_driver_addrs" class="readonly" readonly="readonly"></textarea>
			<script>
				document.getElementById("truck_driver_addrs").value = window.opener.document.forms[0].truck_driver_addrs.value;
			</script>
			</td>
		</tr>
		
	</table>
</form>


<script type="text/javascript">
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
  acc[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var panel = this.nextElementSibling;
    if (panel.style.display === "block") {
      panel.style.display = "none";
    } else {
      panel.style.display = "block";
    }
  });
}
</script>
</body>
