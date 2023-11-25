<%@ page import="java.util.*"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title> DLNG | Preview Service Invoice </title>
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
</script>

<jsp:useBean class="com.seipl.hazira.dlng.service_inv.DataBean_Service_Invoice" id="serviceInv" scope="page"/> 
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<%
	java.text.NumberFormat nf7 = new java.text.DecimalFormat("###,###,###,##0.00");

	String formate_inv_dt = "";
	String formate_due_dt = "";
	String contract_type = request.getParameter("cont_type")==null?"":request.getParameter("cont_type");
	
	String invDt = request.getParameter("invDt")==null?"":request.getParameter("invDt");
// 	System.out.println("invDt----"+invDt);
	util.setInput_date(invDt);
	util.init();
	formate_inv_dt = util.getFormatted_Date();
	
	String dueDt = request.getParameter("dueDt")==null?"":request.getParameter("dueDt");
	util.setInput_date(dueDt);
	util.init();
	formate_due_dt = util.getFormatted_Date();
	
	String mapping_id = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");
	String cust_cd = request.getParameter("cust_cd")==null?"":request.getParameter("cust_cd");
// 	String tempcompnm = (String)session.getAttribute("tempcompnm")==null?"":(String)session.getAttribute("tempcompnm"); //Hiren_20201112
	String cust_plant_cd = request.getParameter("cust_plant_cd")==null?"":request.getParameter("cust_plant_cd");
	String period_start_dt = request.getParameter("period_start_dt")==null?"":request.getParameter("period_start_dt");
	String period_end_dt = request.getParameter("period_end_dt")==null?"":request.getParameter("period_end_dt");
	String new_inv_seq_no =  request.getParameter("new_inv_seq_no")==null?"":request.getParameter("new_inv_seq_no");
	int tax_size = Integer.parseInt(request.getParameter("tax_size")==null?"0":request.getParameter("tax_size"));
	String taxnm_str = request.getParameter("taxnm_str")==null?"":request.getParameter("taxnm_str");
	String calcBase =   request.getParameter("calcBase")==null?"":request.getParameter("calcBase");
	String rowno = request.getParameter("rowno")==null?"1":request.getParameter("rowno");
	String sacStr = request.getParameter("sacStr")==null?"":request.getParameter("sacStr");
	String itemStr = request.getParameter("itemStr")==null?"":request.getParameter("itemStr");
	String qtyStr = request.getParameter("qtyStr")==null?"":request.getParameter("qtyStr");
	String rateStr = request.getParameter("rateStr")==null?"":request.getParameter("rateStr");
	String amtStr = request.getParameter("amtStr")==null?"":request.getParameter("amtStr");
	String tax_structure = request.getParameter("tax_structure")==null?"":request.getParameter("tax_structure");
	String totalTax = request.getParameter("totalTax")==null?"0":request.getParameter("totalTax");
	String netAmt = request.getParameter("netAmt")==null?"0":request.getParameter("netAmt");
	String taxAmt_str = request.getParameter("taxAmt_str")==null?"":request.getParameter("taxAmt_str");
	String grossAmt =  request.getParameter("grossAmt")==null?"0":request.getParameter("grossAmt");
	String truckInvSz =  request.getParameter("truckInvSz")==null?"0":request.getParameter("truckInvSz");
	String chkBoxFlgStr =  request.getParameter("chkBoxFlgStr")==null?"":request.getParameter("chkBoxFlgStr");
	
	System.out.println("chkBoxFlgStr==="+chkBoxFlgStr);
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
	serviceInv.setTaxnm_str(taxnm_str);
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
	serviceInv.setGrossAmt(grossAmt);
	serviceInv.setTruckInvSz(truckInvSz);
	serviceInv.setChkBoxFlgStr(chkBoxFlgStr);
	
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
// 	System.out.println("contact_Suppl_CST_NO****"+contact_Suppl_CST_NO);
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
// 	System.out.println("contact_Customer_CST_NO****"+contact_Customer_CST_NO);
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
	
	String signing_dt = serviceInv.getSigning_dt();
	String trans_cont_no = serviceInv.getTrans_cont_no();
%>

<form name="rpt_invoice_dtl" >
	<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
		<tr valign="middle">
	    	<td colspan="7">
				<div align="center">
					<!-- <font size="2" face="Arial">
						<b>ORIGIONAL FOR RECIPIENT</b><br>
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
	 	
	 	<%-- <tr valign="middle">
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
						<td><div align="right"><font size="1.5px" face="Arial"><b>SEIPL Tax Invoice Seq No&nbsp;</b></font></div></td>
					</tr>
					
				</table>
			</div>
	    </td>
	    <td colspan="1" width="15%">
			<div align="center">
				<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b><span id="invdt"></span><%=formate_inv_dt %></b></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b><span id="duedt"></span><%=formate_due_dt %></b></font></div></td>
					</tr>
					<tr>
						<td><div align="right"><font size="1.5px" face="Arial"><b><span id="invno"></span><%=new_inv_seq_no %></b></font></div></td>
					</tr>
					
				</table>
			</div>
	    </td>
	</tr> --%>
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
							<%for(int j = 0; j < tax_size ; j++) {%>
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
							<%for(int j = 0; j < tax_size ; j++) {%>
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
									<font id = "chk_truck_sz"> </font>&nbsp;
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
									<a href="#" onclick="showModel();"><u>Att1</u></a> &nbsp;&nbsp;
								<%}else{%>
									<%=Vrate.elementAt(j) %>&nbsp;
								<%} %>
								<br><br>
							<%} %>
							<br><br>
							<%for(int j = 0; j < tax_size ; j++) {%>
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
								<%=nf7.format(Math.round(Double.parseDouble(Vamt.elementAt(j)+""))) %>&nbsp;
								<br><br>
							<%} %>
							<%=nf7.format(Double.parseDouble(grossAmt+"")) %>&nbsp;
							<br><br>
							
							<%for(int j = 0; j < tax_size ; j++) {%>
								<%=nf7.format(Math.round(Double.parseDouble(grossAmt) * Double.parseDouble(tax_structure)/100)) %>&nbsp;
								<br>
							<%}%>
<!-- 							<br> -->
							<b><%=nf7.format(Math.round(Double.parseDouble(totalTax+""))) %>&nbsp;</b>
							<br><br>
							<b><%=nf7.format(Math.round(Double.parseDouble(netAmt+""))) %></b>&nbsp;
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
					<td><div align="right"><font size="1.5px" face="Arial"><b><span id="net_amt_inr"></span><%=nf7.format(Math.round(Double.parseDouble(netAmt+""))) %>&nbsp;</b></font></div></td>
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
						<span id="remark_1"></span>
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
						<span id="remark_2"></span>
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
									<%-- <tr valign="middle">
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
														<td><div align="right"><font size="1.5px" face="Arial"><b>SEIPL Tax Invoice Seq No&nbsp;</b></font></div></td>
													</tr>
												</table>
											</div>
									    </td>
									    <td colspan="1" width="15%">
											<div align="center">
												<table width="100%"  border="1" align="center" cellpadding="0" cellspacing="0">
													<tr>
														<td><div align="right"><font size="1.5px" face="Arial"><b><span id="invdt"></span><%=formate_inv_dt %></b></font></div></td>
													</tr>
													<tr>
														<td><div align="right"><font size="1.5px" face="Arial"><b><span id="duedt"></span><%=formate_due_dt %></b></font></div></td>
													</tr>
													<tr>
														<td><div align="right"><font size="1.5px" face="Arial"><b><span id="invno"></span><%=new_inv_seq_no %></b></font></div></td>
													</tr>
													
												</table>
											</div>
									    </td>
									</tr>
									
									<tr valign="middle">
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
														<td><div align="right"><font size="1.5px" face="Arial"><b><%=period_start_dt%></b></font></div></td>
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
														<td><div align="right"><font size="1.5px" face="Arial"><b><%=period_end_dt%></b></font></div></td>
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
													<td colspan="1" rowspan="2"><div align="center"><font size="1.5px" face="Arial"><b>Sr. No.</b></font></div></td>
													<td><div align="center"><font size="1.5px" face="Arial"><b>Supply Date</b></font></div></td>
<!-- 													<td><div align="center"><font size="1.5px" face="Arial"><b>Invoice Number</b></font></div></td> -->
													<td ><div align="center"><font size="1.5px" face="Arial"><b>Truck Number</b></font></div></td>
													<td ><div align="center"><font size="1.5px" face="Arial"><b>Quantity <br>(MMBtu)</b></font></div></td>
													<td colspan="1" rowspan="2"><div align="center"><font size="1.5px" face="Arial"><b>
														Rate <br><%if(calcBase.equalsIgnoreCase("1")) {%>
															(INR/MMBtu)
														<%}else if(calcBase.equalsIgnoreCase("2")){ %>
															(INR/KM)
														<%}else if(calcBase.equalsIgnoreCase("3")){ %>
															(INR/TRUCK)
														<%}%>
														</b></font></div></td>
													<%if(calcBase.equalsIgnoreCase("2")){ %>
														<td colspan="1" rowspan="2"><div align="center"><font size="1.5px" face="Arial"><b>Kilometer(s)</b></font></div></td>
													<%} %>	
													<td colspan="1" rowspan="2"><div align="center"><font size="1.5px" face="Arial"><b>Amount (INR)</b></font></div></td>
												</tr>
												<tr>
													
												</tr>
												
												<%int sr = 0;
												String chkBoxFlg [] = chkBoxFlgStr.split("@@");
// 												System.out.println("chkBoxFlg----"+chkBoxFlg.length);
												for(int i = 0 ; i < Integer.parseInt(truckInvSz+"") ; i++){
// 													if(i == 0){
// 												System.out.println("chkBoxFlg----"+chkBoxFlg [i]);
// 													}
												if(chkBoxFlg[i].equals("Y")){	
													sr++;
												%>	
<%-- 												<script>if(window.opener.document.getElementById("chkBoxFlg"+<%=i%>).value == "Y") </script> --%>
												<tr>
													<td><div align="center"><font size="1.5px" face="Arial"><span id=""> <%=sr %></span> </font></div></td>
													<td><div align="center"><font size="1.5px" face="Arial"><span id="invDt<%=i%>"></span></font></div></td>
<%-- 													<td><div align="center"><font size="1.5px" face="Arial"><span id="invNo<%=i%>"></span></font></div></td> --%>
													<td><div align="center"><font size="1.5px" face="Arial"><span id="truckNo<%=i%>"></span></font></div></td>
													<td><div align="right"><font size="1.5px" face="Arial"><span id="invQty<%=i%>" style="text-align: right;"></span>&nbsp;</font></div></td>
													<td><div align="right"><font size="1.5px" face="Arial"><span id="rate<%=i%>" style="text-align: right;"></span>&nbsp;</font></div></td>
													<%if(calcBase.equalsIgnoreCase("2")){ %>
														<td><div align="right"><font size="1.5px" face="Arial"><span id="km<%=i%>" style="text-align: right;"></span>&nbsp;</font></div></td>
													<%} %>
													<td><div align="right"><font size="1.5px" face="Arial"><span id="amt<%=i%>" style="text-align: right;"></span>&nbsp;</font></div></td>
												</tr>
												
												<script>
													var ind = '<%=i%>';
													document.getElementById("invDt"+ind).innerHTML=window.opener.document.getElementById("truckInvDt"+ind).value;
// 													document.getElementById("invNo"+ind).innerHTML=window.opener.document.getElementById("truckInvNo"+ind).value;
													document.getElementById("truckNo"+ind).innerHTML=window.opener.document.getElementById("truckNm"+ind).value;
													document.getElementById("invQty"+ind).innerHTML=window.opener.document.getElementById("truckInvQty"+ind).value;
													
													<%if(calcBase.equalsIgnoreCase("1")){ %>
														document.getElementById("rate"+ind).innerHTML=window.opener.document.getElementById("rate_mmbtu").value;
														document.getElementById("amt"+ind).innerHTML=window.opener.document.getElementById("servInvQtyAmt"+ind).value;
														
													<%} else if(calcBase.equalsIgnoreCase("2")){ %>
														document.getElementById("rate"+ind).innerHTML=window.opener.document.getElementById("rate_km").value;
														document.getElementById("amt"+ind).innerHTML= Math.round(window.opener.document.getElementById("inv_km_inr"+ind).value);
														document.getElementById("km"+ind).innerHTML=window.opener.document.getElementById("km"+ind).value;
													
													<%}else if(calcBase.equalsIgnoreCase("3")){ %>
														document.getElementById("rate"+ind).innerHTML= Math.round(window.opener.document.getElementById("lumpsum"+ind).value);
														document.getElementById("amt"+ind).innerHTML= Math.round(window.opener.document.getElementById("lumpsum"+ind).value);
													<%}%>
													</script>
												
												<%}} %>
												<tr>
													<td colspan="3"><div align="center"><font size="1.5px" face="Arial"><b>Total</b></font></div></td>
													<td colspan="1">
														<div align="right"><font size="1.5px" face="Arial"><b><span id="totalQty" style="text-align: right;"><b></b></span>&nbsp;</b></font></div>
													</td>
													<%if(calcBase.equalsIgnoreCase("2")){%>
														<td colspan="2"></td>
														<td colspan="1"><div align="right"><font size="1.5px" face="Arial"><b><span id="totalAmt" style="text-align: right;"><b></b></span>&nbsp;</b></font></div></td>
													<%}else{%>
														<td colspan="1"></td>
														<td colspan="1"><div align="right"><font size="1.5px" face="Arial"><b><span id="totalAmt" style="text-align: right;"><b></b></span>&nbsp;</b></font></div></td>
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
	<script type="text/javascript">
	document.getElementById("remark_1").innerHTML=window.opener.document.forms[0].remark_1.value;
	document.getElementById("remark_2").innerHTML=window.opener.document.forms[0].remark_2.value;
	
	<%if (calcBase.equalsIgnoreCase("2")){%>
		document.getElementById("totalAmt").innerHTML = window.opener.document.getElementById("total_km_inr").value;
	<%}else if(calcBase.equalsIgnoreCase("1")){%>
		document.getElementById("totalAmt").innerHTML = window.opener.document.getElementById("total_mmbtu_inr").value;
	<%}else if(calcBase.equalsIgnoreCase("3")){%>
		document.getElementById("totalAmt").innerHTML = window.opener.document.getElementById("total_ls_inr").value;
		document.getElementById("chk_truck_sz").innerHTML = '<%=sr%>';
	<%}%>
	document.getElementById("totalQty").innerHTML=window.opener.document.getElementById("totalInvQty").value;
	</script>
	
</form>	
<!-- jQuery -->
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>