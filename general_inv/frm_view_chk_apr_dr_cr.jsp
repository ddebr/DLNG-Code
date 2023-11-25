<%@ page import="java.util.*"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title> DLNG | View Service Invoice </title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<!-- <link rel="stylesheet" href="../responsive/css/style.css"> -->
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="../css/pt_sans.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script> 	
<script language="JavaScript"> 
function callParent()
{
	var modCd = document.getElementById('modCd').value;
	var formId = document.getElementById('formId').value;
	var subModUrl = document.getElementById('subModUrl').value;
	var modUrl = document.getElementById('modUrl').value;
	var year = document.getElementById('year').value;
	var month = document.getElementById('month').value;
	var bill_cycle = document.getElementById('bill_cycle').value;
	var msg = document.getElementById('msg').value;
	var error_msg  = document.getElementById('error_msg').value;
// 	alert(year+"**"+month+"**"+bill_cycle)
	window.opener.refreshPageFromChild3(year,month,bill_cycle,msg,error_msg);
	window.close();
	
}

function doSubmit(inv_flag){
	var decission = "";
	var dr_cr_flag = document.getElementById('dr_cr_flag').value;
	var title = "";
	if(dr_cr_flag == 'cr'){
		title = "Credit Note";
	}else if(dr_cr_flag == 'dr'){
		title = "Debit Note";
	}
	
	if(inv_flag == 'A'){
		if(document.forms[0].rd[0].checked)
		{
			document.forms[0].approve_flag.value = "Y";
			decission = "Do you want to 'CLEAR' the "+title+" from Approve Process ?";
		}
		else if(document.forms[0].rd[1].checked)
		{
			document.forms[0].approve_flag.value = "N";
			decission = "Do you want to 'STOP' the "+title+" from Approve Process ?";
		}
	}
	if(decission!=""){
		var a = confirm(""+decission);
		if(a)
		{
			document.forms[0].submit();
		}
	}
}
function showModel(){
	
	$('#myModal').modal('show');
}
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.service_inv.DataBean_Service_Invoice" id="serviceInv" scope="page"/>   
<%
	java.text.NumberFormat nf = new java.text.DecimalFormat("0.00");
	java.text.NumberFormat nf2 = new java.text.DecimalFormat("##0.0000");
	java.text.NumberFormat nf3 = new java.text.DecimalFormat("###,###,###,##0.00");
	java.text.NumberFormat nf5 = new java.text.DecimalFormat("##########0");

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
	String backFrm = request.getParameter("backFrm")==null?"N":request.getParameter("backFrm");
		System.out.println("dr_cr_flag****"+dr_cr_flag);
	
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
	
	if(backFrm.equals("N")){
		serviceInv.init();
	}
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
//		System.out.println("contact_Suppl_CST_NO****"+contact_Suppl_CST_NO);

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
//		System.out.println("contact_Customer_CST_NO****"+contact_Customer_CST_NO);
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
// 	Vector diff_tax_amt = serviceInv.getDiff_tax_amt();
	
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
	String formated_inv_dt = serviceInv.getFormated_inv_dt();
	
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

%>
<body <%if(backFrm.equalsIgnoreCase("Y")){ %>onload="callParent();" <%} %>>
	<form method="post" action="../servlet/Frm_Service_Invoice" >
	
		<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
			<tr valign="middle">
		    	<td colspan="7">
					<div align="center">
						<!-- <font size="2" face="Arial">
							<b>ORIGINAL FOR RECIPIENT</b><br>
						</font> -->
						<font size="4" face="Arial">
							<b><%=contact_Suppl_Name%></b>
						</font>
						<br>
						<font size="3" face="Arial">
							<%if(invoice_title.contains("DEBIT") || invoice_title.contains("DE_sign")){ %>
								<b>DEBIT NOTE</b>
							<%}else {%>
								<b>CREDIT NOTE</b>
							<%} %>
						</font>
					</div>
				</td>
			</tr>
			<tr valign="middle">
					<td colspan="7">
						<div align="center">
							<font size="1px" face="Arial"><b><%=rule_remark%></b></font>
						</div>
				    </td>
			</tr>
			<tr valign="middle">
				<td colspan="7">
					<div align="center">
						<font size="1px" face="Arial">
							In respect of Transport Management Service Agreement (<%=trans_cont_no %>) executed on <%=signing_dt%>
							<br>
							between <%=contact_Suppl_Name%> and <%=contact_Customer_Name%>
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
		<tr valign="top">
			<td colspan="3" width="50%">
				<div align="left">
					<font size="1.5px" face="Arial">
						Registered Office:<br>
						<%=contact_Suppl_Name%>,<br>
	<%-- 					<span style="font-size: 8px;"><%=tempcompnm %></span> ,<br> --%>
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
					
						<%=invoice_Customer_Contact_Nm%>,<br>
						<%=contact_Customer_Name%>,<br>
						<%=contact_Customer_Person_Address%>,<br>
						<%=contact_Customer_Person_City%>&nbsp;-&nbsp;<%=contact_Customer_Person_Pin%>
					</font>
				</div>
		    </td>
		</tr>
		
		<tr valign="top"><td colspan="3"><div><font size="1.5px" face="Arial">
			<br>
			
			State : <%=contact_Suppl_State %><br>
			State Code : <%=contact_Suppl_State_code %><br>
			GSTIN No. : <%=contact_Suppl_GSTIN_NO %> <br>
			PAN No. : <%=contact_Suppl_PAN_NO %><br>
			SAC No. : <%=sac_code %><br>
			Description of Service : <%=service_desc %><br>
			Place of Supply : <%=contact_Customer_Plant_State %>
			</font></div>
			
			<td colspan="1">&nbsp;</td>
		 
		 	<td colspan="3" >
		 	
		 		<div>
			 		<font size="1.5px" face="Arial">
			 		<br>
						State : <%=contact_Customer_Plant_State %><br>
						State Code : <%=contact_Customer_State_Code %><br>	
						<%if(vSTAT_CD.size()>0){ %>
							<%for(int i=0; i<vSTAT_CD.size(); i++){%>
								<font size="1.5px" face="Arial">
								<% if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) { %>
									PAN  : <%=(String)vSTAT_NO.elementAt(i)%> <br>
								<%}else {
									if(vSTAT_NM.elementAt(i).toString().contains("GSTIN")) { %>  <!-- Hiren_20210204 As discussed with Mahesh Sir -->
										GSTIN No. : <%=(String)vSTAT_NO.elementAt(i)%>
											<%if(!vSTAT_EFF_DT.elementAt(i).equals("")) {%> DT. <%=(String)vSTAT_EFF_DT.elementAt(i)%> <%} %><br>
									<%}else{%>
		<%-- 								<%=(String)vSTAT_NM.elementAt(i)%> : <%=(String)vSTAT_NO.elementAt(i)%> DT. <%=(String)vSTAT_EFF_DT.elementAt(i)%><br>  --%>
								<% }%>
									<%} %>  
								</font>
							<%}%>
						<%} %>
			 		</font>
		 		</div>
		 	</td>	
		 	</tr>
		 	<tr valign="top"><td colspan="7">&nbsp;</td></tr>
			<tr valign="middle"><td colspan="4"></td>
				<td colspan="2" width="25%"><div align="center">
				<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
				<%if((!invoice_title.contains("CREDIT") )  &&  (!invoice_title.contains("DEBIT") ) && !invoice_title.contains("DE_sign") && !invoice_title.contains("CR_sign")) { %>
				<tr><td><div align="right"><font size="1.5px" face="Arial"><b>Invoice Date &nbsp;</b></font></div></td></tr><% } %>
				<tr><td><div align="right"><font size="1.5px" face="Arial"><b>
				
				<% if(invoice_title.contains("DEBIT") || invoice_title.contains("DE_sign")){%>
					Debit Note Date:
				
				<%} else {%>
					Credit Note Date:
				<%} %></b></font></div></td></tr>
				
				<% if(invoice_title.contains("DEBIT") || invoice_title.contains("DE_sign")){%>
				<tr><td><div align="right"><font size="1.5px" face="Arial"><b>
					Payment Due Date:
				</b></font></div></td></tr> 
				<%}%>
				
				<tr><td><div align="right"><font size="1.5px" face="Arial"><b> 
				
				<%if(invoice_title.contains("DEBIT") || invoice_title.contains("DE_sign")) {%>
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
			<%=drcr_dt%></b></font></div></td></tr>
			
			<% if(invoice_title.contains("DEBIT") || invoice_title.contains("DE_sign")){%>
				<tr><td><div align="right"><font size="1.5px" face="Arial"><b>
				<%=drcr_due_dt%>
				</b></font></div></td></tr> 
			<%} %>
			
			<tr><td><div align="right"><font size="1.5px" face="Arial"><b>
			<%=dr_cr_doc_no%>
			</b></font></div></td></tr> </table></div></td>
			
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
							<td width="40%"><div align="center"><font size="1.5px" face="Arial"><b>Item</b></font></div></td>
							<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Currency</b></font></div></td>
							<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Quantity <br><%=qty_unit %></b></font></div></td>
							<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Rate<br><%=rate_unit %></b></font></div></td>
							<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>Amount</b></font></div></td>
						</tr>
						
						<%int sr_no = 0; %>
						<tr valign="top">
							<!-- 1st TD -->
							<td>
								<div align="right"><font size="1.5px" face="Arial">
									<br>
										<%=++sr_no %>&nbsp;
										<br><br>
									<%if(drcr_criteria.contains("DIFF-LUMPSUM")) {%>
										<%=++sr_no %>&nbsp; 
										<br><br>
										<%=++sr_no %>&nbsp; 
										<br><br>
										<%=++sr_no %>&nbsp; 
										<br><br>
									<%} %>	
									<%if(drcr_criteria.contains("DIFF-KM")) {%>
										<%=++sr_no %>&nbsp;
										<br><br>
										<b><%=++sr_no %>&nbsp;</b>
										<br><br>
									<%} %>
									
									<%if(drcr_criteria.contains("DIFF-INRKM")) {%>
										<%=++sr_no %>&nbsp;
										<br><br>
										<%=++sr_no %>&nbsp;
										<br><br>
										<b><%=++sr_no %>&nbsp;</b>
										<br><br>
									<%} %>
									
									<%if(drcr_criteria.contains("DIFF-QTY")) {%>
										<%=++sr_no %>&nbsp; 
										<br><br>
										<%=++sr_no %>&nbsp; 
										<br><br>
									<%} %>
									<%if(drcr_criteria.contains("DIFF-INRMMBTU")) {%>
										<%=++sr_no %>&nbsp; 
										<br><br>
										<%=++sr_no %>&nbsp; 
										<br><br>
										<%=++sr_no %>&nbsp; 
										<br><br>
									<%} %>
									<!-- for total GROSS AMOUNT -->
									<b><%=++sr_no %>&nbsp;</b>
									<br><br>
									<%if(drcr_criteria.contains("DIFF-TAX")) { %>
										
										<%=++sr_no %>&nbsp; <br>
										<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
											<%if( i  != (tax_nm.size()-1)){ %>
												<br>
											<%} %>
										<%} %>
										<br><br>
										<%=++sr_no %>&nbsp;<br> 
										<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
											<%if( i  != (tax_nm.size()-1)){ %>
												<br>
											<%} %>
										<%} %>
										<br><br>
										<%=++sr_no %>&nbsp;<br> 
										<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
											<%if( i  != (tax_nm.size()-1)){ %>
												<br>
											<%} %>
										<%} %>
										<br><br>
									<%}else{ %>
										
										<b><%=++sr_no %>&nbsp;</b>
										<br> 
										<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
											&nbsp;
											<%if( i  != (tax_nm.size()-1)){ %>
												<br>
											<%} %>
										<%} %>
										<br><br>
									<%} %>
										<b><%=++sr_no %>&nbsp;</b>
										<br> <br>
								</font></div>
							</td>
							
							<!-- 2nd TD -->
							<td>
								<div align="left"><font size="1.5px" face="Arial">
									<br>
<!-- 										&nbsp;Transport Management Services Charge -->
											&nbsp;Transport Management Services Charge As per Invoice ref. <%=new_inv_seq_no %> dated <%=formated_inv_dt %>
											<br><br>
										<%if(drcr_criteria.contains("DIFF-LUMPSUM")) {%>
											&nbsp;Invoice INR/Lumpsum
											<br><br>
											&nbsp;Applicable INR/Lumpsum
											<br><br>
											&nbsp;<b>Difference INR/Lumpsum</b>
											<br><br>
										<%} %>	
										<%if(drcr_criteria.contains("DIFF-KM")) {%>
											&nbsp;Applicable KM	
											<br><br>
											&nbsp;<b>Difference in KM</b>	
											<br><br>
										<%} %>
										<%if(drcr_criteria.contains("DIFF-INRKM")) {%>
											&nbsp;Invoice INR/KM
											<br><br>
											&nbsp;Applicable INR/KM
											<br><br>
											&nbsp;<b>Difference in INR/KM</b>
											<br><br>
										<%} %>
										
										<%if(drcr_criteria.contains("DIFF-QTY")) {%>
											&nbsp;Applicable Quantity
											<br><br>
											&nbsp;Difference Quantity
											<br><br>
										<%} %>
									
										<%if(drcr_criteria.contains("DIFF-INRMMBTU")) {%>
											&nbsp;Invoice INR/MMBtu
											<br><br>
											&nbsp;Applicable INR/MMBtu
											<br><br>
											&nbsp;<b>Difference INR/MMBtu</b>
											<br><br>
										<%} %>
										&nbsp;<b>Gross Amount</b>
										<br></br>
										<%if(drcr_criteria.contains("DIFF-TAX")) { %>
											&nbsp;Invoice TAX <br>
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
												&nbsp;<%=tax_nm.elementAt(i) %>
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
											&nbsp;Applicable TAX (%) <br> 
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
												&nbsp;<%=tax_nm.elementAt(i) %>
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
											<b>&nbsp;Difference TAX (%)</b>  <br> 
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
												&nbsp;<%=tax_nm.elementAt(i) %>
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
										<%}else{ %>
											<b>&nbsp;Invoice TAX (%)</b>  <br> 
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
												&nbsp;<%=tax_nm.elementAt(i) %>
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
										<%} %>
										<b>&nbsp;Total GST</b>
										<br> <br>
								</font></div>
							</td>
							
							<!-- 3rd TD -->
							<td>
								<div align="center"><font size="1.5px" face="Arial">
									<br>
										&nbsp;
										<br><br>
									<%if(drcr_criteria.contains("DIFF-LUMPSUM")) {%>
										Rupees
										<br><br>
										Rupees
										<br><br>
										<b>Rupees</b>
										<br><br>
									<%} %>
									<%if(drcr_criteria.contains("DIFF-KM")) {%>
										&nbsp;
										<br><br>
										&nbsp;
										<br><br>
									<%} %>
									<%if(drcr_criteria.contains("DIFF-INRKM")) {%>
										Rupees
										<br><br>
										Rupees
										<br><br>
										<b>Rupees</b>
										<br><br>
									<%} %>
									<%if(drcr_criteria.contains("DIFF-QTY")) {%>
										&nbsp; 
										<br><br>
										&nbsp; 
										<br><br>
									<%} %>
									<%if(drcr_criteria.contains("DIFF-INRMMBTU")) {%>
										Rupees
										<br><br>
										Rupees
										<br><br>
										<b>Rupees</b>
										<br><br>
									<%} %>
									<b>Rupees</b>
									<br></br>
									
									<%if(drcr_criteria.contains("DIFF-TAX")) { %>
											&nbsp;<br>
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
													Rupees
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
											&nbsp;<br> 
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
													Rupees
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
											&nbsp;<br> 
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
												Rupees
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
										<%}else{ %>
											<b>&nbsp;</b>  <br> 
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
												Rupees
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
										<%} %>
										<b>Rupees</b>
										<br> <br> 
										
								</font></div>
							</td>  
							<!-- 4th TD Quantity -->
							<td>
								<div align="right"><font size="1.5px" face="Arial">
									<br>
									<%if(drcr_criteria.contains("DIFF-LUMPSUM")){ %>
										<%=nf.format(View_truck_inv_no.size()) %>&nbsp;
										<br><br>
										&nbsp; 
										<br><br>
										&nbsp; 
										<br><br>
									<%}else{ %>
										<%=nf.format(tot_inv_qty)%>&nbsp;
									<%} %>
									<br><br>
									<%if(drcr_criteria.contains("DIFF-KM")) { %>
										<%= nf.format(tot_dr_cr_qty)%>&nbsp;
										<br><br>
										<b><%= nf.format(tot_diff_qty)%>&nbsp;</b>
										<br><br>
									<%} %>
									<%if(drcr_criteria.contains("DIFF-INRKM")) { %>
										<%//=nf.format(total_inv_km) %>&nbsp;
										<br><br>
										&nbsp;
										<br><br>
										&nbsp;
										<br><br>
									<%} %>
									<%if(drcr_criteria.contains("DIFF-QTY")) {%>
										<%= nf.format(tot_dr_cr_qty)%>&nbsp; 
										<br><br>
										<b><%= nf.format(tot_diff_qty)%>&nbsp;</b>
										<br><br>
									<%} %>
									<%if(drcr_criteria.contains("DIFF-INRMMBTU")) {%>
										&nbsp; 
										<br><br>
										&nbsp; 
										<br><br>
										&nbsp; 
										<br><br>
									<%} %>
									&nbsp;
									<br></br>
									
									<%if(drcr_criteria.contains("DIFF-TAX")) { %>
											&nbsp;<br>
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
													&nbsp;
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
											&nbsp;<br> 
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
													&nbsp;
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
											&nbsp;<br> 
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
												&nbsp;
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
										<%}else{ %>
											<b>&nbsp;</b>  <br> 
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
												&nbsp;
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
										<%} %>
										<b>&nbsp;</b>
										<br> <br>
								</font></div>
							</td> 
							
							<!-- 5th TD RATE -->
							<td>
								<div class="text-right"><font size="1.5px" face="Arial">
									<br>
									&nbsp;
									<%if(drcr_criteria.contains("DIFF-LUMPSUM")) {%>
										<span style="padding-right: 1.5cm">
											<a href="#" onclick="showModel();"><u>Att1</u></a>
										</span>
										<br><br>
										<%=nf.format(gross_amt_inr) %>&nbsp;
										<br><br>
										<%=nf.format(tot_dr_cr_qty) %>&nbsp;
										<br><br>
										<b><%=nf.format(tot_diff_qty) %>&nbsp;</b>
										<br><br>
									<%}else{ %>
										<br><br>
									<%} %>
									<%if(drcr_criteria.contains("DIFF-KM")) {%>
										&nbsp;
										<br><br>
										&nbsp;
										<br><br>
									<%} %>
									<%if(drcr_criteria.contains("DIFF-INRKM")) {%>
										<%=nf.format(inv_rate) %>&nbsp;
										<br><br>
										<%=nf.format(dr_cr_rate) %>&nbsp;
										<br><br>
										<b><%=nf.format(diff_rate) %>&nbsp;</b>
										<br><br>
									<%} %>
									<%if(drcr_criteria.contains("DIFF-QTY")) {%>
										&nbsp; 
										<br><br>
										&nbsp; 
										<br><br>
									<%} %>
									<%if(drcr_criteria.contains("DIFF-INRMMBTU")) {%>
										<%=nf.format(inv_rate) %>&nbsp;
										<br><br>
										<%=nf.format(dr_cr_rate) %>&nbsp;
										<br><br>
										<b><%=nf.format(diff_rate) %>&nbsp;</b>
										<br><br>
									<%} %>
									
									&nbsp;
									<br></br>
										<%if(drcr_criteria.contains("DIFF-TAX")) { %>
											&nbsp;<br>
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
													<%=inv_tax_perc.elementAt(i) %> %&nbsp;
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
											&nbsp;<br> 
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
													<%=drcr_tax_perc.elementAt(i) %> %&nbsp;
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
											&nbsp;<br> 
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
												<%=diff_tax_perc.elementAt(i) %> %&nbsp;
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
										<%}else{ %>
											<b>&nbsp;</b>  <br> 
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
												<%=inv_tax_perc.elementAt(i) %> %&nbsp;
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
										<%} %>
										<b>&nbsp;</b>
										<br> <br>
								</font></div>
							</td> 
							
							<!-- 6th TD -->
							<td>
								<div align="right"><font size="1.5px" face="Arial">
									<br>
									&nbsp;
									<br><br>
									<%if(drcr_criteria.contains("DIFF-LUMPSUM")) {%>
										&nbsp; 
										<br><br>
										&nbsp; 
										<br><br>
										&nbsp; 
										<br><br>
									<%} %>
									<%if(drcr_criteria.contains("DIFF-KM")) {%>
										&nbsp;
										<br><br>
										&nbsp;
										<br><br>
									<%} %>
									<%if(drcr_criteria.contains("DIFF-INRKM")) {%>
										&nbsp;
										<br><br>
										&nbsp;
										<br><br>
										&nbsp;
										<br><br>
									<%} %>
									<%if(drcr_criteria.contains("DIFF-QTY")) {%>
										&nbsp; 
										<br><br>
										&nbsp; 
										<br><br>
									<%} %>
									<%if(drcr_criteria.contains("DIFF-INRMMBTU")) {%>
										&nbsp; 
										<br><br>
										&nbsp; 
										<br><br>
										&nbsp; 
										<br><br>
									<%} %>
									
									<b><%=nf3.format(Double.parseDouble(drcr_gross_amt_inr+"")) %></b>&nbsp;
									<br></br>
										<%if(drcr_criteria.contains("DIFF-TAX")) { %>
											&nbsp;<br>
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
													<%//=inv_tax_amt.elementAt(i) %>&nbsp;
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
											&nbsp;<br> 
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
													&nbsp;<%//=drcr_tax_amt.elementAt(i) %>
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
											&nbsp;<br> 
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
												<%//=diff_tax_amt.elementAt(i) %>&nbsp;
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
											
										<%}else{ %>
											<b>&nbsp;</b>  <br> 
											<%for(int i = 0 ; i < tax_nm.size(); i++){ %>
													<%=drcr_tax_amt.elementAt(i) %>&nbsp;
												<%if( i  != (tax_nm.size()-1)){ %>
													<br>
												<%} %>
											<%} %>
											<br><br>
										<%} %>
										<b><%=nf.format(drcr_tax_samt) %>&nbsp;</b>
										<br> <br>
								</font></div>
							</td> 
							
							<tr valign="top">
								<td><div align="right"><font size="1.5px" face="Arial"><b><%=(++sr_no)%>&nbsp;</b></font></div></td>
								<td><div align="left"><font size="1.5px" face="Arial"><b>&nbsp;Net Amount Payable</b></font></div></td>
								<td><div align="center"><font size="1.5px" face="Arial"><b>Rupees</b></font></div></td>
								<td><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
								<td><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
								<td><div align="right"><font size="1.5px" face="Arial"><b><span id="net_amt_inr"><%=nf3.format(drcr_net_amt_inr) %></span>&nbsp;</b></font></div></td>
							</tr>	
					</table>
				</td>
			</tr>				
	 	</table>
	 	
	 	<table border="0" width="100%" align="center" cellpadding="2" cellspacing="0">
			<tr align="center"><td colspan="7">&nbsp;</td></tr>
			<%if(!invoice_title.equals("CREDIT")){ %>
			<tr valign="middle">
			<td colspan="7">
			<div align="left"><font size="1px" face="Arial">
			<%=remark_1%>
			</font></div></td></tr>
			
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
			<%if( ((invoice_title.contains("CREDIT")  || invoice_title.contains("DEBIT") ||invoice_title.contains("SUPPLEMENTARY INVOICE") ) && drcr_aprv_by.equalsIgnoreCase("0"))) { %>
			<div align="center">
			<%if(invoice_title.equals("SUPPLEMENTARY INVOICE")){%>DR Approval OK:&nbsp;<%}else if(invoice_title.equals("DEBIT")) {%>DR Approval OK:&nbsp; <%}else{%>
			CR Approval OK:&nbsp; <%}%>
			<input type="radio" name="rd" value="Y" onClick="doSubmit('A');" >&nbsp;<b>Yes</b>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="rd" value="N" onClick="doSubmit('C');" >&nbsp;<b>No</b>
			</div>
			<%} %>
			
			</td>
			</tr>
		</table>
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg" role="document" style="width: 100%">
					<div class="modal-content">
						<div class="modal-header" align="center">

							<h3 class="modal-title" id="myModalLabel">
								<span id="selCustAbr" style="text-align: center;"><%=drcr %> Attachment-1 Details </span>
							</h3>

							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
<!-- 								<div class="box-body table-responsive no-padding"> -->
							<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
								<tr valign="middle">
									<td colspan="4">
										<div align="center">&nbsp;</div>
								    </td>
								    <td colspan="2" width="25%">
										<div align="center">
											<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
												<tr style="height: 15px;">
													<td style="vertical-align: middle;"><div align="right" ><font size="1.5px" face="Arial"><b>Invoice Date &nbsp;</b></font></div></td>
												</tr>
												<% if(invoice_title.contains("DEBIT") || invoice_title.contains("DE_sign")){%>
													<tr style="height: 15px;">
														<td style="vertical-align: middle;"><div align="right" ><font size="1.5px" face="Arial"><b>Payment Due Date &nbsp;</b></font></div></td>
													</tr>
													
													<tr style="height: 15px;">
														<td style="vertical-align: middle;"><div align="right" ><font size="1.5px" face="Arial"><b>Debit Note No&nbsp;</b></font></div></td>
													</tr>
												<%}else{ %>
													<tr style="height: 15px;">
														<td style="vertical-align: middle;"><div align="right" ><font size="1.5px" face="Arial"><b>Credit Note No&nbsp;</b></font></div></td>
													</tr>
												<%} %>
											</table>
										</div>
								    </td>
								    <td colspan="1" width="15%">
										<div align="center">
											<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
												<tr style="height: 15px;">
													<td style="vertical-align: middle;"><div align="right" ><font size="1.5px" face="Arial"><b><%=drcr_dt %>&nbsp;</b></font></div></td>
												</tr>
												<% if(invoice_title.contains("DEBIT") || invoice_title.contains("DE_sign")){%>
													<tr style="height: 15px;">
														<td style="vertical-align: middle;"><div align="right" ><font size="1.5px" face="Arial"><b><%=drcr_due_dt %>&nbsp;</b></font></div></td>
													</tr>
												<%} %>
												<tr style="height: 15px;">
													<td style="vertical-align: middle;"><div align="right" ><font size="1.5px" face="Arial"><b><%=dr_cr_doc_no %>&nbsp;</b></font></div></td>
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
												<td colspan="1" ><div align="center"><font size="1.5px" face="Arial"><b>Sr. No.</b></font></div></td>
												<td><div align="center"><font size="1.5px" face="Arial"><b>Supply Date</b></font></div></td>
												<td ><div align="center"><font size="1.5px" face="Arial"><b>Truck Number</b></font></div></td>
												<td ><div align="center"><font size="1.5px" face="Arial"><b>Truck Quantity <br>(MMBtu) </b></font></div></td>
												<td colspan="1" ><div align="center"><font size="1.5px" face="Arial"><b>
													Rate <br><%if(View_calc_bs.contains("1")) {
														rate_unit = "(INR/MMBtu)";%>
														(INR/MMBtu)
													<%}else if(View_calc_bs.contains("2")){ 
														rate_unit = "(INR/KM)";%>
														(INR/KM)
													<%}else if(View_calc_bs.contains("3")){
														rate_unit = "(INR/TRUCK)";%>
														(INR/TRUCK)
													<%}%>
													</b></font></div></td>
												<%if(View_calc_bs.contains("2")){ %>
													<td colspan="1" ><div align="center"><font size="1.5px" face="Arial"><b>Kilometer(s)</b></font></div></td>
												<%} %>
												<td ><div align="center"><font size="1.5px" face="Arial"><b><%=drcr %> Rate<br><%=rateUnit %></b></font></div></td>
												<td ><div align="center"><font size="1.5px" face="Arial"><b>Diff. <%=drcr %> Rate<br><%=rateUnit %></b></font></div></td>
												<td colspan="1" ><div align="center"><font size="1.5px" face="Arial"><b>Amount (INR)</b></font></div></td>
											</tr>
											<tr>
											</tr>
											
											<%for(int i = 0 ; i < View_truck_inv_no.size() ; i++){ %>
											<tr>
												<td><div align="center"><font size="1.5px" face="Arial"><span id=""> <%=i+1 %></span> </font></div></td>
												<td><div align="center"><font size="1.5px" face="Arial"><span id="invDt<%=i%>"><%=View_truck_inv_dt.elementAt(i) %> </span></font></div></td>
												<td><div align="center"><font size="1.5px" face="Arial"><span id="truckNo<%=i%>"></span><%=View_truck_nm.elementAt(i)%></font></div></td>
												<td><div align="right"><font size="1.5px" face="Arial"><span id="invQty<%=i%>" style="text-align: right;"><%=View_invoice_qty.elementAt(i)%></span>&nbsp;</font></div></td>
												<td><div align="right"><font size="1.5px" face="Arial"><span id="rate<%=i%>" style="text-align: right;">
														<%if(View_calc_bs.elementAt(i).toString().equalsIgnoreCase("3")){ %>
															<%=View_amount.elementAt(i) %>&nbsp;
														<%}else{ %>
															<%=View_rate.elementAt(i) %>&nbsp;
														<%} %>
												</span>&nbsp;</font></div></td>
												<%if(View_calc_bs.elementAt(i).toString().equalsIgnoreCase("2")){ %>
													<td><div align="right"><font size="1.5px" face="Arial"><span id="km<%=i%>" style="text-align: right;"><%=View_km.elementAt(i) %></span>&nbsp;</font></div></td>
												<%} %>
												
												<td><div align="right"><font size="1.5px" face="Arial"><%=nf.format(Double.parseDouble(View_dr_cr_qty.elementAt(i)+"")) %>&nbsp;</font></div></td>
												<td><div align="right"><font size="1.5px" face="Arial"><%=nf.format(Double.parseDouble(View_diff_qty.elementAt(i)+"")) %>&nbsp;</font></div></td>
												<td><div align="right"><font size="1.5px" face="Arial"><span id="amt<%=i%>" style="text-align: right;">
													<%if(View_calc_bs.elementAt(i).toString().equalsIgnoreCase("3")){ %>
														<%=nf.format(Double.parseDouble(View_diff_qty.elementAt(i)+"")) %>&nbsp;
													<%}else{ %>
														<%=nf.format(Double.parseDouble(View_amount.elementAt(i)+"")) %>&nbsp;
													<%} %>
												</span>&nbsp;</font></div></td>
											</tr>
											<%} %> 
											<tr>
												<td colspan="7"><div align="center"><font size="1.5px" face="Arial"><b>Total</b></font></div></td>
												<%-- <td colspan="1">
													<div align="right"><font size="1.5px" face="Arial"><b><span id="totalQty" style="text-align: right;"><b><%=nf5.format(total_qty) %></b></span>&nbsp;</b></font></div>
												</td> --%>
												<%if(View_calc_bs.contains("2")){%>
													<td colspan="2"></td>
													<td colspan="1"><div align="right"><font size="1.5px" face="Arial"><b><span id="totalAmt" style="text-align: right;"><b><%=nf3.format(Math.round(total_amt)) %></b></span>&nbsp;</b></font></div></td>
												<%}else{%>
													<td colspan="1"><div align="right"><font size="1.5px" face="Arial"><b><span id="totalAmt" style="text-align: right;"><b><%=nf3.format(Math.round(total_amt)) %></b></span>&nbsp;</b></font></div></td>
												<%} %>
											</tr>	
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
										<div align="center">
											&nbsp;
										</div>
								    </td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" name="option" value="DR_CR_Approve">
			<input type="hidden" name="drcr_doc_no" value="<%=dr_cr_doc_no%>">
			<input type="hidden" name="invDt" value="<%=invDt%>">
			<input type="hidden" name="new_inv_seq_no" value="<%=new_inv_seq_no%>">
			<input type="hidden" name="modCd" id="modCd"  value="<%=modCd%>">
			<input type="hidden" name="formId" id="formId"   value="<%=formId%>">
			<input type="hidden" name="subModUrl" id="subModUrl"   value="<%=subModUrl%>">
			<input type="hidden" name="modUrl" id="modUrl"   value="<%=modUrl%>">
			<input type="hidden" name="msg" id="msg"  value="<%=msg%>">
			<input type="hidden" name="error_msg" id="error_msg"  value="<%=error_msg%>">
			<input type="hidden" name="year" id="year"  value="<%=year%>">
			<input type="hidden" name="month" id="month"  value="<%=month%>">
			<input type="hidden" name="bill_cycle" id="bill_cycle"  value="<%=bill_cycle%>">
			<input type="hidden" name="approve_flag" id="approve_flag"  value="">
			<input type="hidden" name="dr_cr_flag" id="dr_cr_flag"  value="<%=dr_cr_flag%>">
						
	</form>
</body>
</html>
