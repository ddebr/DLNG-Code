<%@ page  language="java" import="java.util.*,java.text.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="<%response.setContentType("application/vnd.ms-excel");response.setHeader("Content-Disposition", "attachment; filename=Daily_Seller_Nomination.xls");%>">
<%--<meta http-equiv="Content-Type" content="<%response.setHeader("Content-Disposition","attachment;filename=reconcilation.xls");%>">--%>

<title>Daily Seller Nomination Details</title>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Seller_Report_Generation" id="dsRpt"  scope="page"/>
<%
	
	NumberFormat nf = new DecimalFormat("###########0.00");

	utilBean.init();
	String curr_dt = utilBean.getGen_dt();
	String next_dt = utilBean.getDate_tomorrow();
	String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
	String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
	
	
	String gas_date = request.getParameter("gas_date")==null?next_dt:request.getParameter("gas_date");	
	String from_cntct = request.getParameter("from_cntct")==null?next_dt:request.getParameter("from_cntct");
	String to_cntct = request.getParameter("to_cntct")==null?curr_dt:request.getParameter("to_cntct");
	String customer_cd = request.getParameter("customer_cd")==null?curr_dt:request.getParameter("customer_cd");
	String plant_seq_no = request.getParameter("plant_seq_no")==null?curr_dt:request.getParameter("plant_seq_no");
	String plant_nm = request.getParameter("plant_nm")==null?curr_dt:request.getParameter("plant_nm");
	
	String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
	String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
	String cont_type = request.getParameter("cont_type")==null?"":request.getParameter("cont_type");
	String remarks = request.getParameter("remarks")==null?"":request.getParameter("remarks");
	String sn_ref_no = request.getParameter("sn_ref_no")==null?"":request.getParameter("sn_ref_no");
	String mapping_id = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");//ADDDED FOR LTCORA AN CN	
	double convt_mmbtu_to_mt = Double.parseDouble(request.getParameter("convt_mmbtu_to_mt")==null?"":request.getParameter("convt_mmbtu_to_mt"));
	dsRpt.init();	
	if(!dsRpt.getGas_date().trim().equalsIgnoreCase(""))
	{
		gas_date = dsRpt.getGas_date().trim();
	}	
	dsRpt.setConvt_mmbtu_to_mt(convt_mmbtu_to_mt);
	dsRpt.setCallFlag("SELLER_NOM_CUSTOMER");
	dsRpt.setGas_date(gas_date);
	dsRpt.setSeq_no(to_cntct);
	dsRpt.setSupp_seq_no(from_cntct);
	dsRpt.setCustomer_cd(customer_cd);
	dsRpt.setPlant_seq_no(plant_seq_no);
	dsRpt.setCont_type(cont_type);
	dsRpt.setSn_no(sn_no);
	dsRpt.setFgsa_no(fgsa_no);
	dsRpt.setCont_mapping_id(mapping_id);//ADDDED FOR LTCORA AN CN
	dsRpt.init();
					
	String supp_con_desig = dsRpt.getSupp_con_desig();
	String temp_con_desig = dsRpt.getTemp_con_desig();
	String to_fax_no =dsRpt.getTo_fax_no();
	String from_fax_no =dsRpt.getFrom_fax_no();
	
	String gen_time =dsRpt.getGen_time();
	String gas_dt = dsRpt.getGas_dt();
	String gen_date = dsRpt.getGen_date();
	String contract_dt =dsRpt.getContract_dt();
	String fgsa_dt =dsRpt.getFgsa_dt();
	//remarks = dsRpt.getRemarks();
	String cust_nm	 =dsRpt.getCust_nm();
	String nom_rev_no = dsRpt.getNom_rev_no();
	//String cust_abbr  =dsRpt.getCust_abbr();
	
	String seller_nom_clause = dsRpt.getSeller_nom_clause();
	String tender_seller_nom_clause = dsRpt.getTender_seller_nom_clause();
	
	Vector TRANS_CD = dsRpt.getTRANS_CD();
	Vector TRANS_COUNT = dsRpt.getTRANS_COUNT();
	Vector TRANSPORTER_CD = dsRpt.getTRANSPORTER_CD();
	
	Vector QTY_MMBTU = dsRpt.getQTY_MMBTU();	
	Vector TRANS_ABBR = dsRpt.getTRANS_ABBR();	
	Vector PLANT_ABBR = dsRpt.getPLANT_ABBR();
	
	Vector SUPP_SEQ_NO = dsRpt.getSUPP_SEQ_NO();
	Vector SUPP_CONTACT_PERSON = dsRpt.getSUPP_CONTACT_PERSON();
	Vector MOBILE = dsRpt.getMOBILE();
	Vector PHONE = dsRpt.getPHONE();
	
	String ltcora_dt=dsRpt.getLtcora_dt();
	String ltcora_seller_nom_clause=dsRpt.getLtcora_seller_nom_clause();
	String ltcora_no=dsRpt.getLtcora_no();
	
	Vector TRUCK_NO = dsRpt.getTRUCK_NO();
	Vector TRUCK_VOL = dsRpt.getTRUCK_VOL();
	Vector TRUCK_SCH_TM = dsRpt.getTRUCK_SCH_TM();
	Vector TRUCK_SCH_DT = dsRpt.getTRUCK_SCH_DT();
	Vector TRUCK_SCH_RMK = dsRpt.getTRUCK_SCH_RMK();
	Vector CHECKPOST_NM = dsRpt.getCHECKPOST_NM();
	
	double tot_mmbtu = dsRpt.getTot_mmbtu();
	double tot_ton = dsRpt.getTot_ton();
%>
<body>
<form >
<table width="100%"  border="1" align="center">
	<tr valign="top"><td colspan="8" align="center"><font size="4" face="Verdana, Arial, Helvetica, sans-serif"><b>Seller's Daily  Nomination for Date <%=gas_dt%><br>Electronic Transmission </b></font></td></tr>	
	<tr valign="top">
		<td colspan="2"><div align="left"><font size="2px">&nbsp;To:</font></div></td>
		<td colspan="2"><div align="left"><font size="2px">&nbsp;<%=temp_con_desig%></font></div></td>
		<td colspan="2"><div align="left"><font size="2px">&nbsp;Sent Date:</font></div></td>		
		<td colspan="2"><div align="left"><font size="2px">&nbsp;<%=gen_date%></font></div></td>		
	</tr>
	
	<tr valign="top">
		<td colspan="2"><div align="left"><font size="2px">&nbsp;</font></div></td>
		<td colspan="2"><div align="left"><font size="2px">&nbsp;<%=cust_nm%></font></div></td>
		<td colspan="2"><div align="left"><font size="2px">&nbsp;Sent Time:</font></div></td>		
		<td colspan="2"><div align="left"><font size="2px">&nbsp;<%=gen_time%></font></div></td>		
	</tr>
	
	<tr valign="top">
		<td colspan="2"><div align="left"><font size="2px">&nbsp;Fax No:</font></div></td>
		<td colspan="2"><div align="left"><font size="2px">&nbsp;<%=to_fax_no%></font></div></td>
		<td colspan="2"><div align="left"><font size="2px">&nbsp;Seq. No.</font></div></td>		
		<td colspan="2"><div align="left"><font size="2px">&nbsp;<%=nom_rev_no%></font></div></td>				
	</tr>
	
	<tr valign="top" ><td  colspan="8"><div align="left"><font size="2px">&nbsp;</font></div></td></tr>
	
	<tr valign="top">
		<td width="10%" colspan="2"><div align="left"><font size="2px">&nbsp;From:</font></div></td>
		<td width="50%"  colspan="6"><div align="left"><font size="2px">&nbsp;<%=supp_con_desig%></font></div></td>		
	</tr>
	
	<tr valign="top">
		<td class="title2" colspan="2"><div align="left"><font size="2px">&nbsp;</font></div></td>
		<td  colspan="6"><div align="left"><font size="2px">&nbsp;Shell Energy India Private Limited</font></div></td>
	</tr>
	
	<tr valign="top">
		<td width="10%" class="title2" colspan="2"><div align="left"><font size="2px">&nbsp;Fax No:</font></div></td>
		<td width="90%" colspan="6" ><div align="left"><font size="2px">&nbsp;<%=from_fax_no%></font></div></td>		
	</tr>
	
	<tr valign="top">
		<td width="10%" class="title2" colspan="2"><div align="left"><font size="2px">Subject:</font></div></td>
		<td width="90%" colspan="6" ><div align="left"><font size="2px">&nbsp;Seller's Daily Nomination</font></div></td>				
	</tr>	
</table>

<table width="100%"  border="1" align="center">
	<tr>
		<td colspan="8" ><font size="2" face="Verdana, Arial, Helvetica, sans-serif">Dear Madam / Sir,</font>
			<P>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;As per the requirements of Clause&nbsp; 
			 <%if(cont_type.equalsIgnoreCase("S")) 
			   {%>
			   		<u><%=seller_nom_clause%></u>&nbsp;of our Framework LNG Sales Agreement No: <%=fgsa_no%> dated <u><%=fgsa_dt%></u> and Supply Notice No: <%if(sn_ref_no.trim().equalsIgnoreCase("")){%><%=sn_no%><%}else{%><%=sn_ref_no%><%}%> dated <u><%=contract_dt%></u> 
		     <%}
			   else if(cont_type.equalsIgnoreCase("L")) 
			   {%>
			   		<u><%=tender_seller_nom_clause%></u>&nbsp;of our Tender Number No: <%=fgsa_no%> dated <u><%=fgsa_dt%></u> and Letter of Agreement No: <%if(sn_ref_no.trim().equalsIgnoreCase("")){%><%=sn_no%><%}else{%><%=sn_ref_no%><%}%> dated <u><%=contract_dt%></u> 
		     <%}%>
		     
		     
		     , we notify as follows:</P>
		</td>
	</tr>
	
	<tr>
		<td class="title2" colspan="2">Contact Name : </td>
		<%if(SUPP_SEQ_NO.size()<=0)
		  {%>
			 <td  colspan="6">&nbsp;</td>
		<%}else if(SUPP_SEQ_NO.size()== 1){%>
		  	<td colspan="6">&nbsp;<%=SUPP_CONTACT_PERSON.elementAt(0)%></td>
		<%} else if(SUPP_SEQ_NO.size()>= 2){%>
		  	<td colspan="2">&nbsp;<%=SUPP_CONTACT_PERSON.elementAt(0)%></td>
			<td  colspan="4">&nbsp;<%=SUPP_CONTACT_PERSON.elementAt(1)%></td>
		<%}%>		
	</tr>	
	
	<tr>
		<td class="title2" colspan="2">Telephone Number : </td>
		<%if(SUPP_SEQ_NO.size()<=0)
		  {%>
			<td  colspan="6">&nbsp;</td>
		<%}
		  else if(SUPP_SEQ_NO.size()== 1)
		  {%>
		  	<td colspan="6">&nbsp;<%=PHONE.elementAt(0)%></td>
		<%}
		  else if(SUPP_SEQ_NO.size()>= 2)
		  {%>
		  	<td colspan="2">&nbsp;<%=PHONE.elementAt(0)%></td>
		  	<td  colspan="4">&nbsp;<%=PHONE.elementAt(1)%></td>
		<%}%>
	</tr>
	
	<tr>
		<td class="title2" colspan="2">Mobile Number : </td>
		<%if(SUPP_SEQ_NO.size()<=0)
		  {%>
			 <td  colspan="6">&nbsp;</td>
		<%}
		  else if(SUPP_SEQ_NO.size()== 1)
		  {%><td colspan="6">&nbsp;<%=MOBILE.elementAt(0)%></td>
		<%}
		  else if(SUPP_SEQ_NO.size()>= 2)
		  {%><td colspan="2">&nbsp;<%=MOBILE.elementAt(0)%></td>
		  	 <td  colspan="4">&nbsp;<%=MOBILE.elementAt(1)%></td>
		<%}%>
	</tr>
	
	<tr>
		<td rowspan="2" colspan="1" align="center">Date</td>
		<td colspan="2" align="center">Nomination </td>
		<td colspan="2" align="center">Truck Confirmation</td>
		<td rowspan="2" colspan="1" align="center">Buyer's Facility</td>
		<td rowspan="2" colspan="1" align="center">Check Post</td>
		<td rowspan="2" colspan="1" align="center">Seller's Comments</td>
	</tr>
	
	<tr>
		<td align="center">MMBTU</td>
		<td align="center">Tonne(s)</td>
		<td align="center">No.</td>
		<td align="center">Scheduled Time</td>
	</tr>
<%	if(TRANSPORTER_CD.size()>0)
  	{
		for(int i=0;i<TRANSPORTER_CD.size();i++)
		{%>
			 <tr>
				<td align="center"><%=TRUCK_SCH_DT.elementAt(i) %></td>
				<td align="right"> <%=TRUCK_VOL.elementAt(i)%> </td>
				<td align="right"> <%=nf.format(Double.parseDouble(TRUCK_VOL.elementAt(i)+"") / convt_mmbtu_to_mt) %> </td>
				<td align="center"><%=TRUCK_NO.elementAt(i) %></td>
				<td align="center"><%=TRUCK_SCH_TM.elementAt(i) %></td>
				<td align="left"><%=PLANT_ABBR.elementAt(i) %></td>	
				<td align="left"><%=CHECKPOST_NM.elementAt(i) %></td>	
				<td align="left">
					<%=TRUCK_SCH_RMK.elementAt(i) %>
				</td>											 
			 </tr>				  					
	 <% } %>
	 
	 <tr style="font-weight: bold;" >
	 	<td colspan="1" >Total </td>
	 	<td colspan="1" align="right"><%=nf.format(tot_mmbtu) %></td>
	 	<td colspan="1" align="right"><%=nf.format(tot_ton) %></td>
	 	<td colspan="5"></td>
	 </tr>
		<tr>
			<td colspan="8" ><P>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</P><font size="2" face="Verdana, Arial, Helvetica, sans-serif">Thanking You,
			<br>For Shell Energy India Private Limited,&nbsp;
				<br><br>
					<%=supp_con_desig%>		
				<br><br>Authorised Signatory</font>
		   </td>
		</tr>
		
		<tr>
			<td colspan="8">
			Note: In case of late arrival of truck, kindly note that the Truck would be scheduled and accepted on the reasonable endeavour basis.	
			</td>
  		</tr>
				    			
		<tr valign="center">
			<td colspan="8" ><div align="center"><font size="1.5px" face="Arial"><br><br> This is a computer generated Report. </font></div></td>
		</tr>
  <%}%>		
</table>

</form>
</body>
</html>