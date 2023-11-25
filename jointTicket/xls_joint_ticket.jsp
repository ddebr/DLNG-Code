<%@ page  language="java" import="java.util.*,java.text.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="<%response.setContentType("application/vnd.ms-excel");%>">
<meta http-equiv="Content-Type" content="<%response.setHeader("Content-Disposition","attachment;filename="+request.getParameter("plant_nm")+".xls");%>">

<!-- response.setContentType("application/vnd.ms-excel");
response.setHeader("Content-Disposition", "attachment; filename=sample.xls");
response.getWriter().write(data.toString());` -->

<title><%=request.getParameter("plant_nm")+"  "%> : Joint Ticket Report</title>
</head>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.contract_mgmt.DataBean_Contract_Mgmt" id="dbContMgmt" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_modulelock" id="modlock" scope="request"/>
<%
	utilBean.init();
	String current_date = utilBean.getGen_dt();
	String tomorrow_date = utilBean.getDate_tomorrow();
	String yesterday_date = utilBean.getYest_dt();
	
	NumberFormat nf = new DecimalFormat("###########0.00"); 
	
	String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
	String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
	String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");
			 
	String gas_date = request.getParameter("gas_date")==null?yesterday_date:request.getParameter("gas_date");
	String gen_date = request.getParameter("gen_date")==null?current_date:request.getParameter("gen_date");
	
	String seller_nom_qty = request.getParameter("seller_nom_qty")==null?"":request.getParameter("seller_nom_qty");
	String qty_mmbtu = request.getParameter("qty_mmbtu")==null?"":request.getParameter("qty_mmbtu");
	String qty_scm = request.getParameter("qty_scm")==null?"":request.getParameter("qty_scm");
	String customer_cd = request.getParameter("customer_cd")==null?"0":request.getParameter("customer_cd");
	String customer_nm = request.getParameter("customer_nm")==null?"":request.getParameter("customer_nm");
	String customer_abbr = request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
	String customer_contact_cd = request.getParameter("customer_contact_cd")==null?"0":request.getParameter("customer_contact_cd");
	String plant_cd = request.getParameter("plant_cd")==null?"0":request.getParameter("plant_cd");
	String plant_nm = request.getParameter("plant_nm")==null?"":request.getParameter("plant_nm");
	
	String gcv = request.getParameter("gcv")==null?"":request.getParameter("gcv");
	String ncv = request.getParameter("ncv")==null?"":request.getParameter("ncv");
	String transporter_cd = request.getParameter("transporter_cd")==null?"":request.getParameter("transporter_cd");
	String transporter_nm = request.getParameter("transporter_nm")==null?"":request.getParameter("transporter_nm");
	String transporter_abbr = request.getParameter("transporter_abbr")==null?"":request.getParameter("transporter_abbr");
	String transporter_qty = request.getParameter("transporter_qty")==null?"":request.getParameter("transporter_qty");
	
	String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
	String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
	String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
	String sn_ref_no = request.getParameter("sn_ref_no")==null?"":request.getParameter("sn_ref_no");
	String sn_rev_no = request.getParameter("sn_rev_no")==null?"":request.getParameter("sn_rev_no");
	String sn_qty = request.getParameter("sn_qty")==null?"":request.getParameter("sn_qty");
	String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
	String sn_offspec_qty = request.getParameter("sn_offspec_qty")==null?"":request.getParameter("sn_offspec_qty");//JHP
	String sn_offspec_flag = request.getParameter("sn_offspec_flag")==null?"":request.getParameter("sn_offspec_flag");//JHP

	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	double qty_MT= (double) session.getAttribute("convt_mmbtu_to_mt");//HA20201229
	
String[] splitdt=gas_date.split("/");
	
	int dt=20150201;
	int d=Integer.parseInt(""+splitdt[2]+splitdt[1]+splitdt[0]);
	if(d>=dt)
	{
		
	
	dbContMgmt.setCallFlag("JOINT_TICKET_REPORT");
	dbContMgmt.setGas_date(gas_date);
	dbContMgmt.setCustomer_cd(customer_cd);
	dbContMgmt.setCustomer_contact_cd(customer_contact_cd);
	dbContMgmt.setPlant_seq_no(plant_cd);
	dbContMgmt.setContract_type(contract_type);
	dbContMgmt.init();
	
	String dateForMail=gas_date.replace('/','-');
	
	Vector daily_JT_Transporter_Cd=dbContMgmt.getDaily_JT_Transporter_Cd();
	Vector daily_JT_Transporter_Nm=dbContMgmt.getDaily_JT_Transporter_Nm();
	Vector daily_JT_Transporter_Abbr=dbContMgmt.getDaily_JT_Transporter_Abbr();
	Vector daily_JT_Transporter_Qty_Mmbtu=dbContMgmt.getDaily_JT_Transporter_Qty_Mmbtu();
	Vector daily_JT_Ncv=dbContMgmt.getDaily_JT_Ncv();
	Vector daily_JT_Gcv=dbContMgmt.getDaily_JT_Gcv();
	Vector daily_JT_Seller_Nom_Qty_Mmbtu=dbContMgmt.getDaily_JT_Seller_Nom_Qty_Mmbtu();
	Vector daily_JT_Qty_Scm=dbContMgmt.getDaily_JT_Qty_Scm();
	Vector daily_JT_Qty_Mmbtu=dbContMgmt.getDaily_JT_Qty_Mmbtu();
	Vector daily_JT_Mapping_Id=dbContMgmt.getDaily_JT_Mapping_Id();
	Vector daily_JT_Fgsa_No = dbContMgmt.getDaily_JT_Fgsa_No();
	Vector daily_JT_Fgsa_Rev_No = dbContMgmt.getDaily_JT_Fgsa_Rev_No();
	Vector daily_JT_Sn_No = dbContMgmt.getDaily_JT_Sn_No();
	Vector daily_JT_Sn_Rev_No = dbContMgmt.getDaily_JT_Sn_Rev_No();
	Vector daily_JT_SN_Qty_Mmbtu = dbContMgmt.getDaily_JT_SN_Qty_Mmbtu();
	Vector daily_JT_SN_Ref_No = dbContMgmt.getDaily_JT_SN_Ref_No(); //Introduced By Samik Shah On 19th April, 2011 ...
	Vector daily_JT_offspec_qty=dbContMgmt.getDaily_JT_offspec_qty();//Introduced By Jaimin Patel On 27th Sep, 2011 ...
	Vector daily_JT_offspec_flag=dbContMgmt.getDaily_JT_offspec_flag();//Introduced By Jaimin Patel On 27th Sep, 2011 ...
	Vector daily_JT_Sn_Signing_Dt=dbContMgmt.getDaily_JT_Sn_Signing_Dt();
	
	
	
	//Following String Getter Methods Are Defined By Samik Shah For Joint Ticket Format Preperation On 21st June, 2010 ...
	String contact_Person_Name = dbContMgmt.getContact_Person_Name();
	String contact_Person_Desg = dbContMgmt.getContact_Person_Desg();
	String contact_Customer_Name = dbContMgmt.getContact_Customer_Name();
	String contact_Customer_Person_Address = dbContMgmt.getContact_Customer_Person_Address();
	String contact_Customer_Person_City = dbContMgmt.getContact_Customer_Person_City();
	String contact_Customer_Person_Pin = dbContMgmt.getContact_Customer_Person_Pin();
	String contact_Customer_Person_Phone = dbContMgmt.getContact_Customer_Person_Phone();
	String contact_Customer_Person_Fax = dbContMgmt.getContact_Customer_Person_Fax();
	String contact_Customer_Person_State = dbContMgmt.getContact_Customer_Person_State();
	String contact_Customer_Person_Country = dbContMgmt.getContact_Customer_Person_Country();
	String contact_Suppl_Name = dbContMgmt.getContact_Suppl_Name();
	String contact_Suppl_Person_Address = dbContMgmt.getContact_Suppl_Person_Address();
	String contact_Suppl_Person_City = dbContMgmt.getContact_Suppl_Person_City();
	String contact_Suppl_Person_Pin = dbContMgmt.getContact_Suppl_Person_Pin();
	String contact_Suppl_Person_Phone = dbContMgmt.getContact_Suppl_Person_Phone();
	String contact_Suppl_Person_Fax = dbContMgmt.getContact_Suppl_Person_Fax();
	String contact_Suppl_Person_State = dbContMgmt.getContact_Suppl_Person_State();
	String contact_Suppl_Person_Country = dbContMgmt.getContact_Suppl_Person_Country();
	%>
	<body onload="window.close();">
<form>

<table width="98%" border="1" align="center">
	<tr>
    	<td width="50%" colspan="3">
			<div align="left">
				<font size="2" face="Arial">
					To:<br>
					<%=contact_Person_Name%><br>
					<%if(!contact_Person_Desg.trim().equals("")){%><%=contact_Person_Desg%><br><%}%>
					<%=customer_nm%><br>
					<%=contact_Customer_Person_Address%><br>
					<%=contact_Customer_Person_City%><%if(!contact_Customer_Person_Pin.trim().equals("")){%>&nbsp;-&nbsp;<%=contact_Customer_Person_Pin.trim()%><%}%><%if(!contact_Customer_Person_City.trim().equals("")){%>,<%}%><br>
					<%=contact_Customer_Person_State%><%if(!contact_Customer_Person_Country.trim().equals("")){%>,&nbsp;<%=contact_Customer_Person_Country%><%}%><br>
					Tel:&nbsp;<%=contact_Customer_Person_Phone%><br>
					Fax:&nbsp;<%=contact_Customer_Person_Fax%>
				</font>
			</div>
		</td>
		<td width="50%" colspan="4">
			<div align="right">
				<font size="2" face="Arial">
					&nbsp;<br>
					&nbsp;<br>
					<%=contact_Suppl_Name%><br>
					<%=contact_Suppl_Person_Address%><br>
					<%=contact_Suppl_Person_City%><%if(!contact_Suppl_Person_Pin.trim().equals("")){%>&nbsp;-&nbsp;<%=contact_Suppl_Person_Pin%><%}%><%if(!contact_Suppl_Person_City.trim().equals("")){%>,<%}%><br>
					<%=contact_Suppl_Person_State%><%if(!contact_Suppl_Person_Country.trim().equals("")){%>,&nbsp;<%=contact_Suppl_Person_Country%><%}%><br>
					Tel:&nbsp;<%=contact_Suppl_Person_Phone%><br>
					Fax:&nbsp;<%=contact_Suppl_Person_Fax%>
				</font>
			</div>
		</td>
	</tr>
</table>
<br>
<table width="98%" border="1" align="center">
	<tr>
    	<td width="100%" colspan="7">
			<div align="center">
				<font size="2" face="Arial">
					<b>JOINT TICKET&nbsp;(SEIPL&nbsp;-&nbsp;<%=customer_abbr%>&nbsp;<%=plant_nm%>)</b>
				</font>
			</div>
		</td>
	</tr>
</table>
<br>
<table width="98%" border="1" align="center" cellpadding="0" cellspacing="0">
	<tr valign="bottom">
		<td colspan="1"><div align="center"><font size="2px"><b>Gas&nbsp;Day</b></font></div></td>
		<td colspan="1"><div align="center"><font size="2px"><b>Seller&nbsp;Nominated&nbsp;Qty</b></font></div></td>
		<td colspan="1"><div align="center"><font size="2px"><b>GCV</b></font></div></td>
		<td colspan="2"><div align="center"><font size="2px"><b>Gas Delivered&nbsp;Quantity</b></font></div></td>
		<td colspan="1"><div align="center"><font size="2px"><b>&nbsp;Transporter&nbsp;</b></font></div></td>
	</tr>
	<tr valign="bottom">
		<td width="20%"><div align="center"><font size="2px"><b>06:00&nbsp;hours starting&nbsp;on</b></font></div></td>
		<td width="20%"><div align="center"><font size="2px"><b>MMBTU</b></font></div></td>
		<td width="20%"><div align="center"><font size="2px"><b>Kcal/SCM</b></font></div></td>
		<td width="20%"><div align="center"><font size="2px"><b>MMBTU</b></font></div></td>
		<td width="16%"><div align="center"><font size="2px"><b>MT</b></font></div></td>
	</tr>
	<%	
	double seller_allocated=0,volume=0,deliver=0,volDelMT=0;
	for(int i=0; i<daily_JT_Transporter_Cd.size(); i++)
						{	%>	
											
	<tr valign="middle">
		<td colspan="1">
			<div align="center">
				<font size="2px">
					<%=gas_date%>
				</font>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<font size="2px">
					<%=daily_JT_Seller_Nom_Qty_Mmbtu.elementAt(i)%>
					<%seller_allocated+= Double.parseDouble(""+daily_JT_Seller_Nom_Qty_Mmbtu.elementAt(i));%>
				</font>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<font size="2px">
					<%=daily_JT_Gcv.elementAt(i)%>
				</font>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<font size="2px">
					<%=daily_JT_Transporter_Qty_Mmbtu.elementAt(i)%>
					<%//daily_JT_Qty_Mmbtu.elementAt(i) %>
						<%deliver+=Double.parseDouble(""+daily_JT_Transporter_Qty_Mmbtu.elementAt(i));%>
				</font>
			</div>
		</td>
		<%volDelMT += Double.parseDouble( daily_JT_Transporter_Qty_Mmbtu.elementAt(i).toString() ) / qty_MT  ;%>
		<td colspan="1">
			<div align="center">
				<font size="2px">
					<%=nf.format(Double.parseDouble( daily_JT_Transporter_Qty_Mmbtu.elementAt(i).toString() ) / qty_MT ) %>
				</font>
			</div>
		</td>
		
		<td colspan="1">
			<div align="center">
				<font size="2px">
					&nbsp;<%=daily_JT_Transporter_Abbr.elementAt(i)%>&nbsp;
				</font>
			</div>
		</td>
	</tr>
	<%	}	%>
	<tr>
	<td colspan="1">
			<div align="center">
				<font size="2px">
				<b>	Total :</b>
				</font>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<font size="2px">
				<b>	<%=nf.format(seller_allocated)%></b>
				</font>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<font size="2px">
				<b>	-</b>
				</font>
			</div>
		</td>
		
		<td colspan="1">
			<div align="center">
				<font size="2px">
				<b>	<%=nf.format(deliver)%></b>
				</font>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<font size="2px">
				<b>	<%=nf.format(volDelMT)%></b>
				</font>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<font size="2px">
				<b>	-</b>
				</font>
			</div>
		</td>
	</tr>
	
</table>
<br>
<br>
<table width="98%" border="1" align="center"1>
	<tr>
		<td width="100%">
			<div align="left">
				<table width="100%" border="1" align="center">
					<tr>
						<td colspan="1">
							<div align="right"><font size="2px"><b>Note&nbsp;:&nbsp;</b></font></div>
						</td>
						<td colspan="6">&nbsp;</td>
					</tr>
					<%
					int count=0;
					String map_id="";
					for(int i=0; i<daily_JT_Fgsa_No.size(); i++)
					{%>	
						<tr>
							<%if(contract_type.equals("S")) { %>
								<td width="5%">
									<div align="right"><font size="2px"><%if(i==0){%>&nbsp;<%}else{%>&nbsp;<%}%></font></div>
								</td>
								<td width="20%"><div align="right"><font size="2px">SN No.</font></div></td>
								<td width="10%">
									<div align="right"><font size="2px">
											<%if((""+daily_JT_SN_Ref_No.elementAt(i)).trim().equals("")){%>
												<%=daily_JT_Sn_No.elementAt(i)%>
											<%}else{%>
												<%=daily_JT_SN_Ref_No.elementAt(i)%>
											<%}%></font>
									</div>
								</td>
								<td width="22%"><div align="right"><font size="2px">Quantity : </font></div></td>
								<td width="30%"  colspan="3">
									<div align="left"><font size="2px">&nbsp;&nbsp;&nbsp;<%=daily_JT_SN_Qty_Mmbtu.elementAt(i)%>&nbsp;&nbsp;&nbsp;MMBTU</font></div>
								</td>
							<%}else if(contract_type.equals("L")) { %>
								<td width="5%">
									<div align="right"><font size="2px"><%if(i==0){%>&nbsp;<%}else{%>&nbsp;<%}%></font></div>
								</td>
								<td width="20%"><div align="right"><font size="2px">LOA No.</font></div></td>
								<td width="10%">
									<div align="right"><font size="2px">
									<%if(daily_JT_SN_Ref_No.elementAt(i).equals("")){%>
										<%=daily_JT_Sn_No.elementAt(i)%>
									<%}else{%>
										<%=daily_JT_SN_Ref_No.elementAt(i)%>
									<%}%>
										</font>
									</div>
								</td>
								<td width="22%"><div align="right"><font size="2px">Quantity : </font></div></td>
								<td width="30%" colspan="3">
									<div align="left" ><font size="2px">&nbsp;&nbsp;&nbsp;<%=daily_JT_SN_Qty_Mmbtu.elementAt(i)%>&nbsp;MMBTU</font></div>
								</td>
							<%}else if(contract_type.equals("R")) { %>
								<td width="5%">
									<div align="right"><font size="2px"><%if(i==0){%>&nbsp;<%}else{%>&nbsp;<%}%></font></div>
								</td>
								<td width="20%"><div align="right"><font size="2px">RE-GAS No.</font></div></td>
								<td width="10%"><div align="right"><font size="2px"><%=daily_JT_Fgsa_No.elementAt(i)%></font></div></td>
							<%}else if(contract_type.equals("C")) { %>
							
								<%if(!map_id.equals(""+daily_JT_Mapping_Id.elementAt(i))) { %>
									<%if(Double.parseDouble(""+daily_JT_Fgsa_No.elementAt(count))<=9999) { %>
										<td width="5%">
											<div align="right"><font size="2px"><%if(i==0){%>&nbsp;<%}else{%>&nbsp;<%}%></font></div>
										</td>
										<td width="20%"><div align="right"><font size="2px">CN No.</font></div></td>
										<td width="10%">
											<div align="right"><font size="2px"><%=daily_JT_Fgsa_No.elementAt(count)%></font></div>
										</td>
										<td width="10%">
											<div align="right"><font size="2px">dated </font></div>
										</td>
										<td width="10%" colspan="3">
											<div align="left"><font size="2px">&nbsp;&nbsp;&nbsp;<%=daily_JT_Sn_Signing_Dt.elementAt(count) %></font></div>
										</td>
									<%}else{%>
										<td width="5%">
											<div align="right"><font size="2px"><%if(i==0){%>&nbsp;<%}else{%>&nbsp;<%}%></font></div>
										</td>
										<td width="20%">
											<div align="right"><font size="2px">LTCORA</font></div>
										</td>
										<td width="10%">
											<div align="right"><font size="2px">dated </font></div>
										</td>
										<td width="10%"  colspan="4">
											<div align="left"><font size="2px">&nbsp;&nbsp;&nbsp; <%=daily_JT_Sn_Signing_Dt.elementAt(count) %></font></div>
										</td>
									<%}%>
								<%}%>
							<%}else if(contract_type.equals("T")) { %>
								<%if(!map_id.equals(""+daily_JT_Mapping_Id.elementAt(i))) { %>
									<td width="5%">
										<div align="right"><font size="2px"><%if(i==0){%>&nbsp;<%}else{%>&nbsp;<%}%></font></div>
									</td>
									<td width="20%">
										<div align="right"><font size="2px">LTCORA No.</font></div>
									</td>
									<td width="10%"  colspan="4">
										<div align="right"><font size="2px"><%=daily_JT_Fgsa_No.elementAt(count)%></font></div>
									</td>
								<%} %>
							<%} %>						
						</tr>
						<tr></tr>
						<%if(contract_type.equals("C") || contract_type.equals("T") || contract_type.equals("R")) { %>
							<tr>
								<td></td>
								<td width="10%">
									<div align="right"><font size="2px">Cargo No.</font></div>
								</td>
								<td width="10%">
									<div align="right">
										<font size="2px"><%=daily_JT_Sn_No.elementAt(count)%></font>
									</div>
								</td>
								<td width="10%">
									<div align="right"><font size="2px">Quantity : </font></div>
								</td>
								<td width="10%" colspan="2">
									<div align="right"><font size="2px"><%=daily_JT_SN_Qty_Mmbtu.elementAt(count)%></font></div>
								</td>
								<td width="10%"><div align="left"><font size="2px">&nbsp;&nbsp;MMBTU</font></div></td>
							</tr>
						<%} %>
						
					<%	map_id=""+daily_JT_Mapping_Id.elementAt(i);
						count++;
					}	%>
					<tr>
						<td colspan="2">
							<div align="right"><font size="2px"><b>Remarks&nbsp;:&nbsp;</b></font></div>
						</td>
						<td colspan="5">&nbsp;</td>
					</tr>
					<%	for(int i=0; i<daily_JT_Fgsa_No.size(); i++)
						{	%>	
						<%if(!(""+daily_JT_offspec_qty.elementAt(i)).equalsIgnoreCase("0.00") || !(""+daily_JT_offspec_flag.elementAt(i)).equalsIgnoreCase("0.00")){ %>
						<tr>
							<td width="5%">
								<div align="right"><font size="2px"><%if(i==0){%>&nbsp;<%}else{%>&nbsp;<%}%></font></div>
							</td>
							<td width="20%">
								<div align="right"><font size="2px"><%if(contract_type.equalsIgnoreCase("S")){%>Supply Notice<%}else if(contract_type.equalsIgnoreCase("L")){%>Letter Of Agreement<%}else if(contract_type.equalsIgnoreCase("R")){%>RE-GAS&nbsp;No.<%}else if(contract_type.equalsIgnoreCase("T")){%>LTCORA&nbsp;No.<%}else if(contract_type.equalsIgnoreCase("C")){%>CN&nbsp;No.<%}%>&nbsp;:&nbsp;</font></div>
							</td>
							<td width="10%">
								<div align="right"><font size="2px"><%if(contract_type.trim().equalsIgnoreCase("S") || contract_type.trim().equalsIgnoreCase("L")){%><%if((""+daily_JT_SN_Ref_No.elementAt(i)).trim().equals("")){%><%=(""+daily_JT_Sn_No.elementAt(i))%><%}else{%><%=(""+daily_JT_SN_Ref_No.elementAt(i)).trim()%><%}%><%}else{%><%=(""+daily_JT_Sn_No.elementAt(i))%><%}%></font></div>
							</td>
							<td width="22%">
								<div align="right"><font size="2px">OffSpec Quantity: 
									<%if(!(""+daily_JT_offspec_qty.elementAt(i)).equalsIgnoreCase("0.00")){ %>
									<br>
									<%} %>
									</font>
								</div>
							</td>
							<td width="30%" colspan="3">
								<%if(!(""+daily_JT_offspec_flag.elementAt(i)).equalsIgnoreCase("0.00") && !(""+daily_JT_offspec_qty.elementAt(i)).equalsIgnoreCase("0.00")){ %>
									<br><%}%>
								<%if(!(""+daily_JT_offspec_qty.elementAt(i)).equalsIgnoreCase("0.00")){ %>
									<div align="left">
										<font size="2px">
											&nbsp;&nbsp;&nbsp;<%=daily_JT_offspec_qty.elementAt(i)%> MMBTU (Accepted)
										</font>
									<%}%>
								<%if(!(""+daily_JT_offspec_qty.elementAt(i)).equalsIgnoreCase("0.00")){ %>
									<br>
								<%}%>
								<%if(!(""+daily_JT_offspec_flag.elementAt(i)).equalsIgnoreCase("0.00")){ %>
									<font size="2px">
										&nbsp;&nbsp;&nbsp;<%=daily_JT_offspec_flag.elementAt(i)%> MMBTU (Rejected)
									</font>
								<%}%>
							</td>
						</tr>
						<%} %>
					<%	}	%>
				</table>
			</div>
		</td>
	</tr>
</table>
<br>
<table width="98%" border="1" align="center">
	<tr valign="bottom">
		<td colspan="3" width="20%">
			<div align="center">
				<font size="2px">
					<br><br><br><br><%=emp_name%>
					<br>
					_________________________
					<br>
					Authorised Signatory
					<br>
					<%=contact_Suppl_Name%>
				</font>
			</div>
		</td>
		<td colspan="4" width="20%">
			<div align="center">
				<font size="2px">
					<br><br><br><br><br>&nbsp;
					_________________________
					<br>
					Authorised Signatory
					<br>
					<%=customer_nm%>
				</font>
			</div>
		</td>
	</tr>
	<tr valign="center">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<%if(contract_type.equalsIgnoreCase("C") || contract_type.equalsIgnoreCase("T")) { %>
	<tr valign="center">
		<td colspan="7">
			<div align="center">
				<font size="1.5px" face="Arial">
					<b>
					This is a computer generated report and valid without Signature.
					</b>
				</font>
			</div>
	    </td>
	</tr>
	<%} %>
	<tr valign="bottom">
		<td colspan="7">
			&nbsp;<br>&nbsp;
		</td>
	</tr>
</table>

<input type="hidden" name="fileName" value="JT_<%=dateForMail%>_<%=customer_abbr%>.html">
<input type="hidden" name="print_permission" value="<%=print_permission%>">

</form>
</body>
	
	<% }
	else
	{
		dbContMgmt.setCallFlag("JOINT_TICKET_REPORT_OLD");
		dbContMgmt.setGas_date(gas_date);
		dbContMgmt.setCustomer_cd(customer_cd);
		dbContMgmt.setCustomer_contact_cd(customer_contact_cd);
		dbContMgmt.setPlant_seq_no(plant_cd);
		dbContMgmt.init();
		
		String dateForMail=gas_date.replace('/','-');
		
		String [] gcv_arr = gcv.split("~~");
		String [] ncv_arr = ncv.split("~~");
		String [] transporter_cd_arr = transporter_cd.split("~~");
		String [] transporter_nm_arr = transporter_nm.split("~~");
		String [] transporter_abbr_arr = transporter_abbr.split("~~");
		String [] transporter_qty_arr = transporter_qty.split("~~");
		
		String [] fgsa_no_arr = fgsa_no.split("~~");
		String [] fgsa_rev_no_arr = fgsa_rev_no.split("~~");
		String [] sn_no_arr = sn_no.split("~~");
		String [] sn_ref_no_arr = sn_ref_no.split("~~");
		String [] sn_rev_no_arr = sn_rev_no.split("~~");
		String [] sn_qty_arr = sn_qty.split("~~");
		String [] contract_type_arr = contract_type.split("~~");
		String [] sn_offspec_qty_arr=sn_offspec_qty.split("~~");
		String [] sn_offspec_flag_arr=sn_offspec_flag.split("~~");
		String gcv_value = "";
		String ncv_value = "";
		
		double gcv_val = 0;
		double ncv_val = 0;
		int gcv_count = 0;
		int ncv_count = 0;
		
		for(int i=0; i<gcv_arr.length; i++)
		{
			if(!gcv_arr[i].equals("") && !gcv_arr[i].equals(" ") && !gcv_arr[i].equals("0") && !gcv_arr[i].equals("0.00"))
			{
				gcv_val += Double.parseDouble(gcv_arr[i]);
				++gcv_count;
			}
			
			if(!ncv_arr[i].equals("") && !ncv_arr[i].equals(" ") && !ncv_arr[i].equals("0") && !ncv_arr[i].equals("0.00"))
			{
				ncv_val += Double.parseDouble(ncv_arr[i]);
				++ncv_count;
			}
		}
		
		if(gcv_count>0)
		{
			gcv_val = gcv_val/gcv_count;
			gcv_value = nf.format(gcv_val);
		}
		
		if(ncv_count>0)
		{
			ncv_val = ncv_val/ncv_count;
			ncv_value = nf.format(ncv_val);
		}
		
		//Following String Getter Methods Are Defined By Samik Shah For Joint Ticket Format Preperation On 21st June, 2010 ...
		String contact_Person_Name = dbContMgmt.getContact_Person_Name();
		String contact_Person_Desg = dbContMgmt.getContact_Person_Desg();
		String contact_Customer_Name = dbContMgmt.getContact_Customer_Name();
		String contact_Customer_Person_Address = dbContMgmt.getContact_Customer_Person_Address();
		String contact_Customer_Person_City = dbContMgmt.getContact_Customer_Person_City();
		String contact_Customer_Person_Pin = dbContMgmt.getContact_Customer_Person_Pin();
		String contact_Customer_Person_Phone = dbContMgmt.getContact_Customer_Person_Phone();
		String contact_Customer_Person_Fax = dbContMgmt.getContact_Customer_Person_Fax();
		String contact_Customer_Person_State = dbContMgmt.getContact_Customer_Person_State();
		String contact_Customer_Person_Country = dbContMgmt.getContact_Customer_Person_Country();
		String contact_Suppl_Name = dbContMgmt.getContact_Suppl_Name();
		String contact_Suppl_Person_Address = dbContMgmt.getContact_Suppl_Person_Address();
		String contact_Suppl_Person_City = dbContMgmt.getContact_Suppl_Person_City();
		String contact_Suppl_Person_Pin = dbContMgmt.getContact_Suppl_Person_Pin();
		String contact_Suppl_Person_Phone = dbContMgmt.getContact_Suppl_Person_Phone();
		String contact_Suppl_Person_Fax = dbContMgmt.getContact_Suppl_Person_Fax();
		String contact_Suppl_Person_State = dbContMgmt.getContact_Suppl_Person_State();
		String contact_Suppl_Person_Country = dbContMgmt.getContact_Suppl_Person_Country();

	
%>
<body>
<form>

<table width="98%" border="1" align="center">
	<tr>
    	<td width="50%" colspan="2">
			<div align="left">
				<font size="2" face="Arial">
					To:<br>
					<%=contact_Person_Name%><br>
					<%if(!contact_Person_Desg.trim().equals("")){%><%=contact_Person_Desg%><br><%}%>
					<%=customer_nm%><br>
					<%=contact_Customer_Person_Address%><br>
					<%=contact_Customer_Person_City%><%if(!contact_Customer_Person_Pin.trim().equals("")){%>&nbsp;-&nbsp;<%=contact_Customer_Person_Pin.trim()%><%}%><%if(!contact_Customer_Person_City.trim().equals("")){%>,<%}%><br>
					<%=contact_Customer_Person_State%><%if(!contact_Customer_Person_Country.trim().equals("")){%>,&nbsp;<%=contact_Customer_Person_Country%><%}%><br>
					Tel:&nbsp;<%=contact_Customer_Person_Phone%><br>
					Fax:&nbsp;<%=contact_Customer_Person_Fax%>
				</font>
			</div>
		</td>
		<td width="50%" colspan="5">
			<div align="right">
				<font size="2" face="Arial">
					&nbsp;<br>
					&nbsp;<br>
					<%=contact_Suppl_Name%><br>
					<%=contact_Suppl_Person_Address%><br>
					<%=contact_Suppl_Person_City%><%if(!contact_Suppl_Person_Pin.trim().equals("")){%>&nbsp;-&nbsp;<%=contact_Suppl_Person_Pin%><%}%><%if(!contact_Suppl_Person_City.trim().equals("")){%>,<%}%><br>
					<%=contact_Suppl_Person_State%><%if(!contact_Suppl_Person_Country.trim().equals("")){%>,&nbsp;<%=contact_Suppl_Person_Country%><%}%><br>
					Tel:&nbsp;<%=contact_Suppl_Person_Phone%><br>
					Fax:&nbsp;<%=contact_Suppl_Person_Fax%>
				</font>
			</div>
		</td>
	</tr>
</table>
<br>
<table width="98%" border="1" align="center">
	<tr>
    	<td width="100%" colspan="6">
			<div align="center">
				<font size="2" face="Arial">
					<b>JOINT TICKET&nbsp;(SEIPL&nbsp;-&nbsp;<%=customer_abbr%>&nbsp;<%=plant_nm%>)</b>
				</font>
			</div>
		</td>
	</tr>
</table>
<br>
<table width="98%" border="1" align="center" cellpadding="0" cellspacing="0">
	<tr valign="bottom">
		<td colspan="1"><div align="center"><font size="2px"><b>Gas&nbsp;Day</b></font></div></td>
		<td colspan="1"><div align="center"><font size="2px"><b>Seller&nbsp;Nominated&nbsp;Qty</b></font></div></td>
		<td colspan="1"><div align="center"><font size="2px"><b>GCV</b></font></div></td>
		<td colspan="1"><div align="center"><font size="2px"><b>Volume</b></font></div></td>
		<td colspan="3"><div align="center"><font size="2px"><b>Gas Delivered&nbsp;Quantity</b></font></div></td>
		<td colspan="1"><div align="center"><font size="2px"><b>&nbsp;Customer&nbsp;</b></font></div></td>
	</tr>
	<tr valign="bottom">
		<td width="20%"><div align="center"><font size="2px"><b>06:00&nbsp;hours starting&nbsp;on</b></font></div></td>
		<td width="20%"><div align="center"><font size="2px"><b>MMBTU</b></font></div></td>
		<td width="20%"><div align="center"><font size="2px"><b>Kcal/SCM</b></font></div></td>
		<td width="20%"><div align="center"><font size="2px"><b>SCM</b></font></div></td>
		<td width="20%"><div align="center"><font size="2px"><b>MMBTU</b></font></div></td>
		<td width="20%"><div align="center"><font size="2px"><b>SCM</b></font></div></td>
		<td width="20%"><div align="center"><font size="2px"><b>MT</b></font></div></td>
		<td width="20%"><div align="center"><font size="2px"><b></b></font></div></td>
	</tr>	
	<tr valign="middle">
		<td colspan="1">
			<div align="center">
				<font size="2px">
					<%=gas_date%>
				</font>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<font size="2px">
					<%=seller_nom_qty%>
				</font>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<font size="2px">
					<%=gcv_value%>
				</font>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<font size="2px">
					<%=qty_scm%>
				</font>
			</div>
		</td>
		
		<td colspan="1">
			<div align="center">
				<font size="2px">
					<%=qty_mmbtu%>
				</font>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<font size="2px">
					<%=qty_scm%>
				</font>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<font size="2px">
					&nbsp;&nbsp;
				</font>
			</div>
		</td>
	</tr>
</table>
<br>
<br>
<table width="98%" border="1" align="center"1>
	<tr>
		<td width="100%">
			<div align="left">
				<table width="100%" border="1" align="center">
					<tr>
						<td colspan="2">
							<div align="right"><font size="2px"><b>Note&nbsp;:&nbsp;</b></font></div>
						</td>
						<td colspan="5">&nbsp;</td>
					</tr>
					<%	for(int i=0; i<fgsa_no_arr.length; i++)
						{	%>	
						<tr>
							<td width="5%">
								<div align="right"><font size="2px"><%if(i==0){%>1<%}else{%>&nbsp;<%}%></font></div>
							</td>
							<td width="20%">
								<div align="right"><font size="2px"><%if(contract_type_arr[i].equalsIgnoreCase("S")){%>Supply Notice<%}else if(contract_type_arr[i].equalsIgnoreCase("L")){%>Letter of Agreement<%}else if(contract_type_arr[i].equalsIgnoreCase("R")){%>RE-GAS&nbsp;No.<%}else if(contract_type_arr[i].equalsIgnoreCase("T")){%>LTCORA&nbsp;No.<%}else if(contract_type_arr[i].equalsIgnoreCase("C")){%>CN&nbsp;No.<%}%>&nbsp;:&nbsp;</font></div>
							</td>
							<td width="10%">
								<div align="right"><font size="2px"><%if(contract_type_arr[i].trim().equalsIgnoreCase("S") || contract_type_arr[i].trim().equalsIgnoreCase("L")){%><%if(sn_ref_no_arr[i].trim().equals("")){%><%=sn_no_arr[i].trim()%><%}else{%><%=sn_ref_no_arr[i].trim()%><%}%><%}else{%><%=fgsa_no_arr[i]%><%}%></font></div>
							</td>
							<td width="22%">
								<div align="right"><font size="2px">Quantity : </font></div>
							</td>
							<td width="18%">
								<div align="right"><font size="2px"><%=sn_qty_arr[i]%>&nbsp;MMBTU</font></div>
							</td>
						</tr>
					<%	}	%>
					<%	for(int i=0; i<transporter_cd_arr.length; i++)
						{	%>	
						<tr>
							<td width="5%">
								<div align="right"><font size="2px"><%if(i==0){%>2<%}else{%>&nbsp;<%}%></font></div>
							</td>
							<td width="20%">
								<div align="right"><font size="2px">Customer : </font></div>
							</td>
							<td width="10%">
								<div align="right"><font size="2px"><%=transporter_abbr_arr[i]%></font></div>
							</td>
							<td width="22%">
								<div align="right"><font size="2px">Quantity : </font></div>
							</td>
							<td width="18%">
								<div align="right"><font size="2px"><%=transporter_qty_arr[i]%>&nbsp;MMBTU</font></div>
							</td>
						</tr>
					<%	}	%>
					<tr>
						<td colspan="2">
							<div align="right"><font size="2px"><b>Remarks&nbsp;:&nbsp;</b></font></div>
						</td>
						<td colspan="5">&nbsp;</td>
					</tr>
					<%	for(int i=0; i<fgsa_no_arr.length; i++)
						{	%>	
						<%if(!sn_offspec_qty_arr[i].equalsIgnoreCase("0.00") || !sn_offspec_flag_arr[i].equalsIgnoreCase("0.00")){ %>
							<tr>
								<td width="5%">
									<div align="right"><font size="2px"><%if(i==0){%><%}else{%>&nbsp;<%}%></font></div>
								</td>
								<td width="20%">
								<!-- ADDED FOR LTCORA AND CN  20141101-->
								<%//System.out.println("contract_type_arr-->"+contract_type_arr[i]); %>
									<div align="right"><font size="2px"><%if(contract_type_arr[i].equalsIgnoreCase("S")){%>Supply Notice<%}else if(contract_type_arr[i].equalsIgnoreCase("L")){%>Letter of Agreement<%}else if(contract_type_arr[i].equalsIgnoreCase("R")){%>RE-GAS&nbsp;No.<%}else if(contract_type_arr[i].equalsIgnoreCase("T")){%>LTCORA&nbsp;No.<%}else if(contract_type_arr[i].equalsIgnoreCase("C")){%>CN&nbsp;No.<%}%>&nbsp;:&nbsp;</font></div>
								</td>
								<td width="10%">
									<div align="right"><font size="2px"><%if(contract_type_arr[i].trim().equalsIgnoreCase("S") || contract_type_arr[i].trim().equalsIgnoreCase("L")){%><%if(sn_ref_no_arr[i].trim().equals("")){%><%=sn_no_arr[i].trim()%><%}else{%><%=sn_ref_no_arr[i].trim()%><%}%><%}else{%><%=sn_no_arr[i]%><%}%></font></div>
								</td>
								<%if(!sn_offspec_qty_arr[i].equalsIgnoreCase("0.00") || !sn_offspec_flag_arr[i].equalsIgnoreCase("0.00")){ %>
									<td width="22%">
										<div align="justify"><font size="2px">OffSpec Quantity:&nbsp;</font></div>
									</td>
								<%}else{ %>
									<td width="22%">
										<div align="right"><font size="2px">
										</font></div>
									</td>
								 <%} %>
								<td width="6%" colsapn="2">
									<%if(!sn_offspec_qty_arr[i].equalsIgnoreCase("0.00")){ %>
										<div align="left">
											<font size="2px"><%=sn_offspec_qty_arr[i]%>  MMBTU (Accepted)</font>
										</div>
									<%} %>
									<%if(!sn_offspec_flag_arr[i].equalsIgnoreCase("0.00")){ %>
										<div align="left">
											<font size="2px"><%=sn_offspec_flag_arr[i]%>  MMBTU (Rejected)</font>
										</div>
									<%} %>	
								</td>
							</tr>
						<%}%>
					<%}%>
				</table>
			</div>
		</td>
	</tr>
</table>
<br>
<table width="98%" border="1" align="center">
	<tr valign="bottom">
		<td colspan="3" width="20%">
			<div align="center">
				<font size="2px">
					<br><br><br><br><%=emp_name%>
					<br>
					_________________________
					<br>
					Authorised Signatory
					<br>
					<%=contact_Suppl_Name%>
				</font>
			</div>
		</td>
		<td colspan="4" width="20%">
			<div align="center">
				<font size="2px">
					<br><br><br><br><br>&nbsp;
					_________________________
					<br>
					Authorised Signatory
					<br>
					<%=customer_nm%>
				</font>
			</div>
		</td>
	</tr>
	<tr valign="center">
		<td colspan="7">
			<div align="center">
				&nbsp;
			</div>
	    </td>
	</tr>
	<tr valign="center">
		<td colspan="7">
			<div align="center">
				<font size="1px" face="Arial">
					This is a computer generated report and valid without Signature.
				</font>
			</div>
	    </td>
	</tr>
	<tr valign="bottom">
		<td colspan="7">
			&nbsp;<br>&nbsp;
		</td>
	</tr>
</table>

<input type="hidden" name="fileName" value="JT_<%=dateForMail%>_<%=customer_abbr%>.html">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
</form>
</body>
<%} %>
</html>