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

<script>
function showModel(){
	
	$('#myModal').modal('show');
}

function doSubmit(inv_flag){
	var decission = "";
	
	if(inv_flag == 'C'){
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
	}else if(inv_flag == 'A'){
		if(document.forms[0].rd[0].checked)
		{
			document.forms[0].approve_flag.value = "Y";
			decission = "Do you want to 'CLEAR' the Invoice from Approve Process ?";
		}
		else if(document.forms[0].rd[1].checked)
		{
			document.forms[0].approve_flag.value = "N";
			decission = "Do you want to 'STOP' the Invoice from Approve Process ?";
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

function callParent(){
	
	var modCd = document.getElementById('modCd').value;
	var formId = document.getElementById('formId').value;
	var subModUrl = document.getElementById('subModUrl').value;
	var modUrl = document.getElementById('modUrl').value;
	var year = document.getElementById('year').value;
	var month = document.getElementById('month').value;
	var bill_cycle = document.getElementById('bill_cycle').value;
	var msg = document.getElementById('msg').value;
	var error_msg  = document.getElementById('error_msg').value;
// 	alert('inn1');
	window.opener.refreshPageFromChild3(year,month,bill_cycle,msg,error_msg);
	window.close();
}
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.service_inv.DataBean_Service_Invoice" id="serviceInv" scope="page"/> 
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<%

String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
String approve_permission = (String)session.getAttribute("approve_permission")==null?"":(String)session.getAttribute("approve_permission");
String check_permission =(String)session.getAttribute("check_permission")==null?"":(String)session.getAttribute("check_permission");
String msg=request.getParameter("msg")==null?"":request.getParameter("msg");
String error_msg=request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212
String modUrl = session.getAttribute("modUrl")==null?"":session.getAttribute("modUrl").toString();//HA20210814
String year=request.getParameter("year")==null?"":request.getParameter("year");
String month=request.getParameter("month")==null?"":request.getParameter("month");
String bill_cycle=request.getParameter("bill_cycle")==null?"":request.getParameter("bill_cycle");
String backFrm = request.getParameter("backFrm")==null?"":request.getParameter("backFrm");

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
String tempcompnm = (String)session.getAttribute("tempcompnm")==null?"":(String)session.getAttribute("tempcompnm"); //Hiren_20201112
String cust_plant_cd = request.getParameter("cust_plant_cd")==null?"":request.getParameter("cust_plant_cd");
String period_start_dt = request.getParameter("period_start_dt")==null?"":request.getParameter("period_start_dt");
String period_end_dt = request.getParameter("period_end_dt")==null?"":request.getParameter("period_end_dt");
String new_inv_seq_no =  request.getParameter("new_inv_seq_no")==null?"":request.getParameter("new_inv_seq_no");
String fin_yr =   request.getParameter("fin_yr")==null?"":request.getParameter("fin_yr");
String inv_seq_no =   request.getParameter("inv_seq_no")==null?"":request.getParameter("inv_seq_no");
String inv_flag =   request.getParameter("inv_flag")==null?"":request.getParameter("inv_flag");

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
serviceInv.setInv_flag(inv_flag);
/* serviceInv.setTaxnm_str(taxnm_str);
serviceInv.setTaxAmt_str(taxAmt_str);
serviceInv.setCalcBase(calcBase);
serviceInv.setSacStr(sacStr);
serviceInv.setItemStr(itemStr);
serviceInv.setQtyStr(qtyStr);
serviceInv.setRateStr(rateStr);
serviceInv.setAmtStr(amtStr);
serviceInv.setRowno(rowno);
serviceInv.setTax_structure(tax_structure);
serviceInv.setTotalTax(totalTax);
serviceInv.setNetAmt(netAmt);
serviceInv.setGrossAmt(grossAmt); */
if(!backFrm.equals("Y")){
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
//	System.out.println("contact_Suppl_CST_NO****"+contact_Suppl_CST_NO);
String contact_Suppl_GST_DT = serviceInv.getContact_Suppl_GST_DT();
String contact_Suppl_CST_DT = serviceInv.getContact_Suppl_CST_DT();

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
// System.out.println("tax_size----"+tax_size.length);

String signing_dt = serviceInv.getSigning_dt();
String trans_cont_no = serviceInv.getTrans_cont_no();
%>
<body <%if(backFrm.equals("Y")) {%>onload="callParent();" <%} %>>
<form name="rpt_invoice_dtl" method="post" action="../servlet/Frm_Service_Invoice">
<input type="hidden" name="check_flag" value="">
<input type="hidden" name="approve_flag" value="">
<input type="hidden" name="inv_flag" value="<%=inv_flag%>">
<input type="hidden" name="option" value="Check_Approve">
<input type="hidden" name="modCd" id="modCd"  value="<%=modCd%>">
<input type="hidden" name="formId" id="formId"   value="<%=formId%>">
<input type="hidden" name="subModUrl" id="subModUrl"   value="<%=subModUrl%>">
<input type="hidden" name="modUrl" id="modUrl"   value="<%=modUrl%>">
<input type="hidden" name="emp_cd" id="emp_cd"  value="<%=emp_cd%>">
<input type="hidden" name="new_inv_seq_no" id="new_inv_seq_no"  value="<%=new_inv_seq_no%>">
<input type="hidden" name="invDt" id="invDt"  value="<%=invDt%>">
<input type="hidden" name="msg" id="msg"  value="<%=msg%>">
<input type="hidden" name="error_msg" id="error_msg"  value="<%=error_msg%>">
<input type="hidden" name="year" id="year"  value="<%=year%>">
<input type="hidden" name="month" id="month"  value="<%=month%>">
<input type="hidden" name="bill_cycle" id="bill_cycle"  value="<%=bill_cycle%>">

<%if(!backFrm.equals("Y")){%>
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
						<b>TAX INVOICE</b>
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
	 	
	 	<tr valign="middle" style="height: 15px;">
			<td colspan="4">
				<div align="center">&nbsp;</div>
		    </td>
		    <td colspan="2" width="25%">
				<div align="center">
					<table width="100%"  border="0" align="center" cellpadding="1" cellspacing="1">
						<tr >
							<td><div align="right" ><font size="1.5px" face="Arial"><b>Invoice Date &nbsp;</b></font></div></td>
						</tr>
						<tr style="height: 15px;">
							<td style="vertical-align: middle;"><div align="right" ><font size="1.5px" face="Arial"><b>Payment Due Date &nbsp;</b></font></div></td>
						</tr>
						<tr style="height: 15px;">
							<td style="vertical-align: middle;"><div align="right" ><font size="1.5px" face="Arial"><b>SEIPL Tax Invoice Seq No &nbsp;</b></font></div></td>
						</tr>
					</table>
				</div>
		    </td>
		    <td colspan="1" width="15%"  >
				<div align="center"  >
					<table width="100%"  border="1" align="center"  >
					<tbody>
						 <tr style="height: 15px;">
							<td style="vertical-align: middle;">
								<div align="right" ><font size="1.5px" face="Arial" ><b><span id="invdt"><%=formate_inv_dt %></span>&nbsp;</b></font></div>
							</td>
						</tr>
						<tr style="height: 15px;">	
							<td style="vertical-align: middle;">	
								<div align="right" ><font size="1.5px" face="Arial"><b><%=formate_due_dt %>&nbsp;</b></font></div>
							</td>
						</tr>
						<tr style="height: 15px;">	
							<td style="vertical-align: middle;">	
								<div align="right" ><font size="1.5px" face="Arial"><b><%=new_inv_seq_no %>&nbsp;</b></font></div>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
		    </td>
		</tr>
		<tr valign="middle" >
			<td colspan="1" style="height: 15px;vertical-align: middle;">
					<div align="right">
						&nbsp;
					</div>
		    </td>
			<td colspan="3" style="height: 15px;vertical-align: middle;">
				<div >
					<font size="1.5px" face="Arial" style="text-align: center;"><b>For the Billing Period</b></font>
				</div>
		    </td>
		    <td colspan="1" width="15%" style="height: 15px;vertical-align: middle;">
				<div align="center" style="height: 15px;vertical-align: middle;">
					<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
						<tr style="height: 15px;vertical-align: middle;">
							<td><div align="right"><font size="1.5px" face="Arial"><b><%=period_start_dt%></b></font></div></td>
						</tr>
					</table>
				</div>
		    </td>
		    <td colspan="1" width="10%" >
				<div align="center" style="height: 15px;vertical-align: middle;">
					<font size="1.5px" face="Arial"><b>to</b></font>
				</div>
		    </td>
		    <td colspan="1" width="15%" >
				<div align="center" >
					<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
						<tr style="height: 15px;">
							<td style="vertical-align: middle;">
							<div align="right" ><font size="1.5px" face="Arial"><b><%=period_end_dt%></b></font></div></td>
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
		<%String rateFlg= ""; %>
		 <tr valign="middle">
			<td colspan="7">
				<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
					<tr valign="bottom">
						<td width="6%"><div align="center"><font size="1.5px" face="Arial"><b>Sr. No.</b></font></div></td>
						<td width="34%"><div align="center"><font size="1.5px" face="Arial"><b>Item</b></font></div></td>
						<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Attachment<br>Reference</b></font></div></td>
						<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Currency</b></font></div></td>
						<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>Quantity<br>
							<%if(calcBase.equalsIgnoreCase("1")) {
								rateFlg = "(INR/MMBtu)";
							%>
								(MMBtu)
							<%}else if(calcBase.equalsIgnoreCase("2")){
								rateFlg = "(INR/KM)";
								%>
								(KM)
							<%}else if(calcBase.equalsIgnoreCase("3")){
								rateFlg = "(INR)";
							%>	
								(No. of Trucks)
							<%} %>
							</b></font></div></td>
						<td width="10%"><div align="center"><font size="1.5px" face="Arial"><b>Rate<br>
							<%=rateFlg %>
						</b></font></div></td>
						<td width="15%"><div align="center"><font size="1.5px" face="Arial"><b>Amount</b></font></div></td>
					</tr>
					
					<%int sr_no = 0; %>
				<tr valign="top">
					<!-- 1st TD -->
					<td>
						<div align="right"><font size="1.5px" face="Arial">
							<br>
							<%for (int j = 0; j < Vsac_cd.size() ; j++) {%>
							
							<%=++sr_no %>&nbsp;
							<br><br>
							<%} %>
							<%=++sr_no %>&nbsp;
							<br><br>
							<%for(int j = 0; j < tax_size.length ; j++) {%>
								<%=++sr_no %>&nbsp;
							<br>
							<%} %>&nbsp;
<!-- 							<br> -->
							<b><%=++sr_no %></b>&nbsp;
							<br><br>
							<b><%=++sr_no %>&nbsp;</b>
							<br><br>
						</font></div>
					</td>
					
					<!-- 2nd TD -->
					<td>
						<div align="left"><font size="1.5px" face="Arial">
							<br>
							<%for (int j = 0; j < Vsac_cd.size() ; j++) {%>
							
							&nbsp;<%=Vitem_desc.elementAt(j) %>&nbsp;
							<br><br>
							<%} %>
							&nbsp;Gross Amount
							<br><br>
							<%
							if(taxnm_str.contains("@@")){
								String taxArr[] = taxnm_str.split("@@");
								for(int j = 0; j < taxArr.length ; j++) {%>
									&nbsp;<%=taxArr[j] %>
									<br>
								<%}	
							}%>
<!-- 							<br> -->
							&nbsp;<b>Total GST</b>
							<br><br>
							&nbsp;<b>Invoice Amount</b>
							<br><br>
						</font></div>
					</td> 
					<!-- 3rd TD -->
					<td>
						<div align="center"><font size="1.5px" face="Arial">
							<br>
							<a href="#" onclick="showModel();"><u>Att1</u></a>
							
						</font></div>
					</td>
					
					<td>
						<div align="center"><font size="1.5px" face="Arial">
							<br>
							<%for (int j = 0; j < Vsac_cd.size() ; j++) {%>
							Rupees
							<br><br>
							<%} %>
							
							Rupees
							<br><br>
							<%for(int j = 0; j < tax_size.length ; j++) {%>
								Rupees
							<br>
							<%} %>
<!-- 							<br> -->
							<b>Rupees</b>
							<br><br>
							<b>Rupees</b>
							<br><br>
						</font></div>
					</td> 
					
					<!-- 4th TD -->
					<td>
						<div align="right"><font size="1.5px" face="Arial">
							<br>
							<%for (int j = 0; j < Vsac_cd.size() ; j++) {%>
								<%if(calcBase.equalsIgnoreCase("3") && j==0){%>
									<%=View_truck_inv_no.size()%>&nbsp;
								<%}else{%>
									<%=Vqty.elementAt(j) %>&nbsp;
								<%} %>
								<br><br>
							<%} %>	
							
						</font></div>
					</td>  
					
					<!-- 5th TD -->
					<td>
						<div align="right"><font size="1.5px" face="Arial">
							<br>
							<%for (int j = 0; j < Vsac_cd.size() ; j++) {%>
								<%if(calcBase.equalsIgnoreCase("3") && j==0){%>
									<a href="#" onclick="showModel();"><u>Att1</u></a>&nbsp;&nbsp;
								<%}else{%>
									<%=Vrate.elementAt(j) %>&nbsp;
								<%} %>
								<br><br>
							<%} %>
							<br><br>
							<%for(int j = 0; j < tax_size.length ; j++) {%>
								<%=tax_structure %> % &nbsp;
							<br>
							<%} %>
							
						</font></div>
					</td>  
					<!-- 6th TD -->
					<td>
						<div align="right"><font size="1.5px" face="Arial">
							<br>
							<%for (int j = 0; j < Vsac_cd.size() ; j++) {%>
								<%=nf7.format(Double.parseDouble(Vamt.elementAt(j)+"")) %>&nbsp;
								<br><br>
							<%} %>
							<%=nf7.format(Double.parseDouble(grossAmt+"")) %>&nbsp;
							<br><br>
							
							<%for(int j = 0; j < tax_size.length ; j++) {%>
								<%=nf7.format(Math.round(Double.parseDouble(grossAmt) * Double.parseDouble(tax_structure)/100)) %>&nbsp;
								<br>
							<%}%>
<!-- 							<br> -->
							<b><%=nf7.format(Double.parseDouble(totalTax+"")) %>&nbsp;</b>
							<br><br>
							<b><%=nf7.format(Double.parseDouble(netAmt+"")) %></b>&nbsp;
							<br><br>
						</font></div>
					</td>
				</tr>
				<tr valign="top">
					<td><div align="right"><font size="1.5px" face="Arial"><b><%=(++sr_no)%>&nbsp;</b></font></div></td>
					<td><div align="left"><font size="1.5px" face="Arial"><b>&nbsp;Net Amount Payable</b></font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial"><b></b></font></div></td>
					<td><div align="center"><font size="1.5px" face="Arial"><b>Rupees</b></font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial">&nbsp;</font></div></td>
					<td><div align="right"><font size="1.5px" face="Arial"><b><span id="net_amt_inr"></span><%=nf7.format(Double.parseDouble(netAmt+"")) %>&nbsp;</b></font></div></td>
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
				<div align="left">
					<font size="1px" face="Arial">
						<span id="remark_1"><%=remark1 %></span>
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
		<tr valign="middle">
			<td colspan="7">
				<div align="left">
					<font size="1px" face="Arial">
						<span id="remark_2"><%=remark2 %></span>
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
		<%if(!inv_flag.equals("V")){ %>
		 <tr valign="middle">
			<td colspan="7">
				<div align="center">
				<%if(inv_flag.equals("C")) {%>
					Checking OK:&nbsp;
				<%}else if(inv_flag.equals("A")){ %>
					Approve OK:&nbsp;
				<%} %>
					<input type="radio" name="rd" value="Y" onClick="doSubmit('<%=inv_flag%>');">&nbsp;<b>Yes</b>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="rd" value="N" onClick="doSubmit('<%=inv_flag%>');">&nbsp;<b>No</b>
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
		<%} %>
	 </table>
	 <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg" role="document" style="width: 100%">
						<div class="modal-content">
							<div class="modal-header" align="center">

								<h3 class="modal-title" id="myModalLabel">
									<span id="selCustAbr" style="text-align: center;">Service Invoice Attachment-1 Details </span>
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
													<tr style="height: 15px;">
														<td style="vertical-align: middle;"><div align="right" ><font size="1.5px" face="Arial"><b>Payment Due Date &nbsp;</b></font></div></td>
													</tr>
													<tr style="height: 15px;">
														<td style="vertical-align: middle;"><div align="right" ><font size="1.5px" face="Arial"><b>SEIPL Tax Invoice Seq No&nbsp;</b></font></div></td>
													</tr>
												</table>
											</div>
									    </td>
									    <td colspan="1" width="15%">
											<div align="center">
												<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
													<tr style="height: 15px;">
														<td style="vertical-align: middle;"><div align="right" ><font size="1.5px" face="Arial"><b><%=formate_inv_dt %></b></font></div></td>
													</tr>
													<tr style="height: 15px;">
														<td style="vertical-align: middle;"><div align="right" ><font size="1.5px" face="Arial"><b><%=formate_due_dt %></b></font></div></td>
													</tr>
													<tr style="height: 15px;">
														<td style="vertical-align: middle;"><div align="right" ><font size="1.5px" face="Arial"><b><%=new_inv_seq_no %></b></font></div></td>
													</tr>
													
												</table>
											</div>
									    </td>
									</tr>
									<!-- <tr valign="middle" >
									<td>
										<table border="0">
											<tr>
												<td>&nnsp;</td>
											</tr>
											
										</table>
									</td>
									
									</tr> -->
									<tr valign="middle" >
										<td colspan="3" style="height: 15px;vertical-align: middle;">
											<div align="right">
												<font size="1.5px" face="Arial"><b>For the Billing Period</b></font>
											</div>
									    </td>
									    <td colspan="1" style="height: 15px;vertical-align: middle;">
											<div align="right">
												&nbsp;
											</div>
									    </td>
									    <td colspan="1" width="15%" style="height: 15px;vertical-align: middle;">
											<div align="center" style="height: 15px;vertical-align: middle;">
												<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
													<tr style="height: 15px;vertical-align: middle;">
														<td><div align="right"><font size="1.5px" face="Arial"><b><%=period_start_dt%></b></font></div></td>
													</tr>
												</table>
											</div>
									    </td>
									    <td colspan="1" width="10%" >
											<div align="center" style="height: 15px;vertical-align: middle;">
												<font size="1.5px" face="Arial"><b>to</b></font>
											</div>
									    </td>
									    <td colspan="1" width="15%" >
											<div align="center" >
												<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
													<tr style="height: 15px;">
														<td style="vertical-align: middle;">
														<div align="right" ><font size="1.5px" face="Arial"><b><%=period_end_dt%></b></font></div></td>
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
														Rate <br><%if(calcBase.equalsIgnoreCase("1")) {%>
															(INR/MMBtu)
														<%}else if(calcBase.equalsIgnoreCase("2")){ %>
															(INR/KM)
														<%}else if(calcBase.equalsIgnoreCase("3")){ %>
															(INR/TRUCK)
														<%}%>
														</b></font></div></td>
													<%if(calcBase.equalsIgnoreCase("2")){ %>
														<td colspan="1" ><div align="center"><font size="1.5px" face="Arial"><b>Kilometer(s)</b></font></div></td>
													<%} %>	
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
															<%if(calcBase.equalsIgnoreCase("3")){ %>
																<%=View_amount.elementAt(i) %>
															<%}else{ %>
																<%=View_rate.elementAt(i) %>
															<%} %>
													</span>&nbsp;</font></div></td>
													<%if(calcBase.equalsIgnoreCase("2")){ %>
														<td><div align="right"><font size="1.5px" face="Arial"><span id="km<%=i%>" style="text-align: right;"><%=View_km.elementAt(i) %></span>&nbsp;</font></div></td>
													<%} %>
													<td><div align="right"><font size="1.5px" face="Arial"><span id="amt<%=i%>" style="text-align: right;"><%=View_amount.elementAt(i) %></span>&nbsp;</font></div></td>
												</tr>
												<%} %>
												<tr>
													<td colspan="3"><div align="center"><font size="1.5px" face="Arial"><b>Total</b></font></div></td>
													<td colspan="1">
														<div align="right"><font size="1.5px" face="Arial"><b><span id="totalQty" style="text-align: right;"><b><%=nf5.format(total_qty) %></b></span>&nbsp;</b></font></div>
													</td>
													<%if(calcBase.equalsIgnoreCase("2")){%>
														<td colspan="2"></td>
														<td colspan="1"><div align="right"><font size="1.5px" face="Arial"><b><span id="totalAmt" style="text-align: right;"><b><%=nf7.format(Math.round(total_amt)) %></b></span>&nbsp;</b></font></div></td>
													<%}else{%>
														<td colspan="1"></td>
														<td colspan="1"><div align="right"><font size="1.5px" face="Arial"><b><span id="totalAmt" style="text-align: right;"><b><%=nf7.format(Math.round(total_amt)) %></b></span>&nbsp;</b></font></div></td>
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
	 
	</form>
	<%} %>
	</body>
	<!-- jQuery -->
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script> 	