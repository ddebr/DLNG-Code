<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="<%response.setHeader("Content-Disposition","attachment;filename=Delivery_Report.xls");%>">

</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Report_GenerationV2" id="contMgmt" scope="page"/>  
<%
	java.text.NumberFormat nf = new java.text.DecimalFormat("###0.00");
	utilBean.init();
	String current_date = utilBean.getGen_dt();
	String temp_dt[] = current_date.split("/");
	String temp_mon = temp_dt[1];
	String temp_yr = temp_dt[2];
	String start_dt = "01/"+temp_mon+"/"+temp_yr;
	
// 	System.out.println("start_dt***"+start_dt);
	
	String tomorrow_date = utilBean.getDate_tomorrow();
	start_dt = request.getParameter("from_date")==null?start_dt:request.getParameter("from_date"); //Modified
	current_date = request.getParameter("to_date")==null?current_date:request.getParameter("to_date"); //Modified

	contMgmt.setCallFlag("customerWiseRpt");
	contMgmt.setFrom_date(start_dt);
	contMgmt.setTo_date(current_date);
	contMgmt.init();
	
	Vector daily_Seller_Nom_Sn_No = contMgmt.getVsn_cd();
	Vector daily_Seller_Nom_Abbr = contMgmt.getVcust_abbr();
	Vector daily_Seller_Nom_Plant_Seq_No = contMgmt.getDaily_Seller_Nom_Plant_Seq_No();
	Vector daily_Seller_Nom_Qty_Mmbtu = contMgmt.getDaily_Seller_Nom_Qty_Mmbtu();
	Vector daily_Buyer_Nom_Plant_Seq_No = contMgmt.getVplant_cd();
	
	Vector daily_Buyer_Nom_Plant_Seq_Abbr = contMgmt.getVplant_abbr();
	Vector daily_Buyer_Nom_Qty_Mmbtu = contMgmt.getDaily_Buyer_Nom_Qty_Mmbtu();
	
	int index = 0;
	Vector daily_Seller_Nom_Mapping_Id = contMgmt.getVdist_map_id();
	
	Vector Vnom_mmbtu_qty = contMgmt.getVnom_mmbtu_qty();
	Vector Vsch_mmbtu_qty = contMgmt.getVsch_mmbtu_qty();
	Vector Vnom_dt = contMgmt.getVnom_dt();
	Vector VSubRecCnt = contMgmt.getVSubRecCnt();
	Vector Vtruck_nm = contMgmt.getVtruck_nm();
	Vector Vload_start_tm = contMgmt.getVload_start_tm();
	Vector Vloaded_vol = contMgmt.getVloaded_vol();
	Vector Vload_end_tm = contMgmt.getVload_end_tm();
	Vector Vloaded_ene = contMgmt.getVloaded_ene();
	
	double convt_mmbtu_to_mt = (double) session.getAttribute("convt_mmbtu_to_mt");//HA20201229
%>

<%	try
	{ %>
		<table border="1">
			<thead>
			<tr >
				<td colspan="8" style="font-size: medium;font-weight: bold;" align="center">Delivery Report From <%=start_dt %> To <%=current_date %>  </td>
			</tr>
				<tr >
					<th rowspan="2" >Gas Date</th>
					<th rowspan="1" colspan="1" >Nom Qty</th>
					<th rowspan="1" colspan="1" >Sch Qty</th>	
					<th rowspan="2" colspan="1" >Truck Reg No.</th>
					<th rowspan="1" colspan="1"  >Load Start Date Time</th>
					<th rowspan="1" colspan="1"  >Load End Date Time</th>
					<th rowspan="1" colspan="2"  >Loaded Qty</th>
				</tr>
				
				 <tr>
				 	<th colspan="1" rowspan="1" >MMBTU </th>
				 	<th colspan="1" rowspan="1" >MMBTU </th>
				 	<th colspan="1" rowspan="1" >(DD/MM/YYYY HH:MM) </th>
				 	<th colspan="1" rowspan="1" >(DD/MM/YYYY HH:MM) </th>
					<th colspan="1" rowspan="1" >MMBTU </th>
					<th colspan="1" rowspan="1" >MT</th>
					
				</tr> 
			</thead>		
			<%
			int k =0;
			for(int j=0;j<daily_Buyer_Nom_Plant_Seq_No.size();j++){ %>
			<%if(Integer.parseInt(VSubRecCnt.elementAt(j)+"") > 0 ){ %>
			<tbody>
			<tr>
				<td colspan="8" style="background-color: #eee;vertical-align: middle;color: blue;font-weight: bold;font-family: inherit; ">
					<%=(String)daily_Seller_Nom_Abbr.elementAt(j)%>&nbsp;-&nbsp;<%=(String)daily_Buyer_Nom_Plant_Seq_Abbr.elementAt(j)%>&nbsp;(<%=daily_Seller_Nom_Sn_No.elementAt(j) %>)
				</td>
			</tr>
			<%
			for(int i = 0; i < Integer.parseInt(VSubRecCnt.elementAt(j)+""); i++){ %>
				<tr class="toggler"  id="tr<%=i%>">
					<td align="center"><%=Vnom_dt.elementAt(k) %></td>
					<td align="left"><%=Vnom_mmbtu_qty.elementAt(k) %></td>
					<td align="left"><%=Vsch_mmbtu_qty.elementAt(k) %></td>
					<td align="center"><%=Vtruck_nm.elementAt(k) %></td>
					<td align="center"><%=Vload_start_tm.elementAt(k) %> </td>
					<td align="center"><%=Vload_end_tm.elementAt(k) %> </td>
					<td align="left"><%=Vloaded_ene.elementAt(k) %> </td>
					<td align="left"><%=Vloaded_vol.elementAt(k) %> </td>
				</tr>
			<% k++;} %>
			</tbody>
			<%}}%>
			</table>
			
<%	if(daily_Buyer_Nom_Plant_Seq_Abbr.size()>0) { } else { %>
		<table>
			<thead>   
				<tr >
					<th   style="color: red;"> Data Not Available!!</th>
				</tr>
			</thead>
		</table>
	
<% } }	
	catch(Exception e)
	{	
		e.printStackTrace();
	} %>

</html>