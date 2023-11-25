<%@ page import="java.util.*,java.text.*" %>
<%@ page buffer="128kb" %>
<%@ page errorPage="../home/ExceptionHandler.jsp" %>
<%@ include file="../sess/Expire.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>FMS | Master SUG Report</title>

<!-- Font Awesome Icons -->
<link rel="stylesheet" href="../plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="../css/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../dist/css/adminlte.min.css">
<link rel="stylesheet" href="../plugins/toastr/toastr.min.css">
<link rel="stylesheet" href="../css/jquery-ui.css" />

<script type="text/javascript" src="../js/date-picker.js"></script>
<script type="text/javascript" src="../js/mouseclk.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/nhv.js"></script>
<script type="text/javascript" src="../js/validations.js"></script>

<script type="text/javascript">  

var newWindow;

function gotoprint()
{
	var date1=document.forms[0].contractdt.value;
	var party1=document.forms[0].partycd.value;
    var mapping1=document.forms[0].mappingcd.value;
	var year1=document.forms[0].curryear.value;
	
    var url="print_mas_contracts.jsp?date="+date1+"&party="+party1+"&mapping="+mapping1+"&year="+year1+"";
	if(!newWindow || newWindow.closed)
	{
		newWindow=window.open(url,"name","top=8,left=5,width=900,height=900,scrollbars=1");
	}else{
		newWindow.close();
        newWindow=window.open(url,"name","top=8,left=5,width=900,height=900,scrollbars=1");
	}
}

function To_Excel()
{
	year=document.forms[0].year.value;
	var flag1='B';
	var unit_nm;
	flg = document.forms[0].flag.value;
	if(flg=='Y')
	{
		comm_id = document.forms[0].comm.value;
		unit_nm = document.forms[0].comm[document.forms[0].comm.selectedIndex].innerText;
	}
	else
	{
		comm_id = document.forms[0].comp_cd1.value;
	}
	if(comm_id=='A')
	{
		flag1='A';
	}
	
    yr1=year.substring(2);
	var date="1/01/"+yr1;
	if(comm_id != '0')
	{
	 	var url="rpt_cont_comparision_xls.jsp?year="+year+"&date="+date+"&comm="+comm_id+"&unit_nm="+unit_nm;// &status="+sts+"
	    window.open(url);
	}
}

function refresh()
{
	year=document.forms[0].year.value;
	// map=document.forms[0].map.value;
	var flag1='B';
	flg = document.forms[0].flag.value;
	
	if(flg=='Y')
	{
		comm_id = document.forms[0].comm.value;
	}
	else
	{
		comm_id = document.forms[0].comp_cd1.value;
	}
	if(comm_id=='A')
	{
		flag1='A';
	}
	
    yr1=year.substring(2);
	var date="1/01/"+yr1;
	
	if(comm_id != '0')
	{
	 	var url="rpt_cont_comparision.jsp?year="+year+"&date="+date+"&comm="+comm_id;// &status="+sts+"
	    location.replace(url);
	}
}

function setData(date,mId,sts)
{
  var dte=new Array();
  
 /* if(mId=='' || mId==' ' ||mId==null ){
  	document.forms[0].map.value='0';
  }else{
  	 document.forms[0].map.value=mId;
  }*/
  
  flg1 = document.forms[0].flag_1.value;

  if(flg1=='A')
  { 
  	  //document.forms[0].comm.value='A';
  }
  
  if(date==null || date=='')
  {}
  else
  {
      dte=date.split("/");
	  year="20"+dte[2];
	  document.forms[0].year.value=year;
  }
 
  if(sts=="active")
  {
	  //document.forms[0].status[0].checked = true;
  }
  else
  {
	  //document.forms[0].status[1].checked = true;
  }
  if(sts=="")
  {
	  //document.forms[0].status[0].checked = true;
  }
   document.forms[0].year.value=document.forms[0].curryear.value;	
}

var tableToExcel = (function() {
	  var uri = 'data:application/vnd.ms-excel;base64,'
	    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
	    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
	    , format = function(s, c)
	    			{
	    				return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) 
	    			}
	  return function(table, name) 
	  {
		if (!table.nodeType) table = document.getElementById(table)
	    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
	    var link = document.createElement("a");
		link.href = uri + base64(format(template, ctx));
		link.download = name || 'Workbook.xls';
		link.target = '_blank';
		document.body.appendChild(link);
		link.click(); 
	  }
})()
</script>
</head>

<jsp:useBean class="com.Gsfc.Baroda.fms6.master.DataBean_master_rpt" id="masCont" scope="page"/>   
<jsp:useBean class="com.Gsfc.Baroda.fms6.util.UtilBean" id="utilBean" scope="request"/>
<%
utilBean.init();
String year=utilBean.getGet_yr();
String[] monthName=utilBean.monthname;
String comp_cd=(String)session.getAttribute("comp_cd");// BY SM 
String emp_cd =(String)session.getAttribute("user_cd");// BY SM 
String comm_id = request.getParameter("comm")==null?comp_cd:request.getParameter("comm"); // BY SM
String contDate=request.getParameter("date")==null?"":request.getParameter("date");
String party=request.getParameter("party")==null?"":request.getParameter("party");
String mapping =request.getParameter("mapping")==null?"":request.getParameter("mapping");
String Current_year =request.getParameter("year")==null?year:request.getParameter("year");
 
if(Current_year.equalsIgnoreCase(""))
{
	Current_year = year;
}

String mappingId =request.getParameter("map")==null?"0":request.getParameter("map");
String status =request.getParameter("status")==null?"active":request.getParameter("status");
String flag =request.getParameter("flag")==null?"":request.getParameter("flag");
String flag1 =request.getParameter("flag_1")==null?"B":request.getParameter("flag_1");

masCont.setCallFlag("ContReportCompar");
masCont.setParent_comp_cd(comp_cd); // BY SM 
masCont.setCompany_cd(comm_id); // BY SM
masCont.setEmp_cd(emp_cd);
masCont.setStatus(status);
masCont.setSet_year(Current_year);
masCont.setSet_date(contDate);
masCont.setSet_pty_cd(party);
masCont.setMapping_cd(mapping);
masCont.setMappingCd(mappingId);
masCont.init();
   
String comp_nm = masCont.getComp_nm();
   
// All internal company

Vector Vcomm_dtl_id = masCont.getVcomm_dtl_id();
Vector Vcomm_dtl_nm = masCont.getVcomm_dtl_nm();
   
if(Vcomm_dtl_id.size()!=1)
{
	flag="Y";
}
   		
int st_year=0;
int end_year=0;
String sty=	masCont.getSt_year();
String endy=masCont.getEnd_year();

if(!sty.equalsIgnoreCase("") && !endy.equalsIgnoreCase(""))
{
	st_year=Integer.parseInt(masCont.getSt_year());
   	end_year=Integer.parseInt(masCont.getEnd_year());
}
	 
Vector Vmapp_id=masCont.getVmapp_id();
Vector party_abr = masCont.getVparty_abr_tran();
Vector vcomp_cd = masCont.getVcomp_cd();
Vector quality_comp_cd = masCont.getVquality_comp_cd();
Vector quality_comp_nm =  masCont.getVquality_comp_nm();
Vector components =  masCont.getCompnants();
Vector compnant_value =   masCont.getCompnants_value();
Vector quality_comp_value [] = masCont.getVquality_comp_value();

System.out.println("components :"+components.size());
System.out.println("Vmapp_id :"+Vmapp_id.size());
%>  

<body onLoad="setData('<%=contDate %>','<%=mappingId%>','<%=status%>');" >
<form name=mainview method="POST" action="../servlet/Frm_master_contract">
<%@ include file="../home/header.jsp" %>

<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    	<section class="content-header">
      		<div class="container-fluid">
        		<div class="row mb-2">
          			<div class="col-sm-6">
            			<h1>Master Report : Contract Comparison<%//=formName%></h1>
          			</div>
          			<div class="col-sm-6">
            			<ol class="breadcrumb float-sm-right">
              				<li class="breadcrumb-item"><a href="#">Home</a></li>
              				<li class="breadcrumb-item active">Master Maintenance > </li>
            			</ol>
          			</div>
        		</div>
      		</div><!-- /.container-fluid -->
    	</section>
    
    	<section class="content">
	    	<div class="container-fluid">
	        	<div class="row">
	          		<div class="col-md-12">
	          			<div class="card">
	              			<div class="card-header"><!-- /.card-header -->
								<div class="card-title"><h4>Salient Features of Gas Transmission Agreement(s)</h4></div>
								<div class="card-tools" >
	                			</div>
							</div>
							<div class="card-body table-responsive p-0">
				            	<table class="table table-head-fixed text-nowrap table-bordered">
				            		<thead>
				            			<tr>
				            				<th>Select Year:</th>
										   	<th>
										   		<select name="year" onChange="refresh();">
										        	<%for(int i=st_year;i<=end_year;i++){ %>
											    		<option value="<%=i%>"><%=i%></option>
										  	   		<%}%>
										    	</select>
											    <script>
											    	document.forms[0].year.value = '<%=Current_year %>';
											    </script>
										 	</th>
										 	<th>Parent Unit :</th>
										 	<th><%=comp_nm %></th>  
										 	<!-- <th>
												<input type="radio" name="status" value="active" size="10" style="text-align:left;"  checked="checked" onclick="refresh1(0);">&nbsp;: Active
												<input type="radio" name="status" value="inactive"  size="10" style="text-align:left;" onclick="refresh1(1);">&nbsp;: Inactive
										 	</th> -->

										 	<% if(flag.equalsIgnoreCase("Y")){ %>
										  		<th>Select Unit:</th>
										  		<th>
											  		<select name="comm" onchange="refresh();">
														<option value="0">-- Select Unit --</option>
														<option value="A">All Unit</option>
														<%for (int i = 0; i < Vcomm_dtl_id.size(); i++) {%>
															<option value="<%=Vcomm_dtl_id.elementAt(i)%>"><%=Vcomm_dtl_nm.elementAt(i)%></option>
														<%}%>
													</select>
													<script>
													 		document.forms[0].comm.value ='<%=comm_id%>' ;		
													</script>
												</th>  
											<%}%>
											<!-- <th class="text-right"><button type="button" class="btn btn-info" id="addNBtn" name="xls" value="Export To Excel" onClick="javascript:To_Excel();">Export To Excel</button></th> -->
											<th class="text-right"><button type="button" name="xls" class="btn btn-info" id="btn" onclick="tableToExcel('example', 'Salient_Features_of_Gas_Transmission_Agreement');" value="export" >Export To Excel</button></th>
				            			<tr>
				            		</thead>
				            	</table>
				            </div>
				            
				            <div class="card-body table-responsive p-0" style="height:400px;">
				            	<table id="example" class="table table-head-fixed text-nowrap table-bordered">		
				            		<thead>
				            			<%if(!Vmapp_id.isEmpty()){ %>
					            			<tr>
					            				<th class="text-center">Sr No.</th>
					            				<th class="text-center"><%=components.firstElement()%></th>
					            				<%Vector cont_between =(Vector) compnant_value.elementAt(0);
	 											  for(int j=0;j<Vmapp_id.size();j++){  %>
													<th class="text-center"><%=cont_between.elementAt(j)%></th>
												  <%}%>
					            			</tr>
					            		<%}%>
				            		</thead>
				            		<tbody>
				            			<%if(!Vmapp_id.isEmpty()){ %>
											<tr>
												<td class="text-center">1</td>
												<td class="text-left"><%=components.elementAt(18) %></td>
												<%Vector cont_base =(Vector) compnant_value.elementAt(20);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
														<td><%=cont_base.elementAt(j) %></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">2</td>
												<td class="text-left"><%=components.elementAt(1) %></td>
												<%Vector dcq =(Vector) compnant_value.elementAt(3);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td><%=dcq.elementAt(j) %></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">3</td>
												<td class="text-left"><%=components.elementAt(2) %></td>
												<%Vector cont_end_dt = (Vector)compnant_value.elementAt(2);
												  //Vector st_dt =(Vector)  compnant_value.firstElement();
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td><%=cont_end_dt.elementAt(j) %></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">4</td>
												<td class="text-left"><%=components.elementAt(3) %></td>
												<%Vector cont_sign_dt =(Vector) compnant_value.elementAt(4);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td><%=cont_sign_dt.elementAt(j) %></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">5</td>
												<td class="text-left"><%=components.elementAt(4) %></td>
												<%Vector cont_abandmnt =(Vector) compnant_value.elementAt(6);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td><%=cont_abandmnt.elementAt(j) %></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">6</td>
												<td class="text-left"><%=components.elementAt(5) %></td>
												<%Vector cont_st_dt =(Vector) compnant_value.elementAt(1);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td><%=cont_st_dt.elementAt(j) %> - <%=cont_end_dt.elementAt(j) %></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">7</td>
												<td class="text-left"><%=components.elementAt(6) %></td>
												<%for(int j=0;j<Vmapp_id.size();j++){  %> 
													<td></td>
												<%}%>	
											</tr>
											<tr>
												<td class="text-center">8</td>
												<td class="text-left"><%=components.elementAt(17) %></td>
												<%Vector basic_price =(Vector)  compnant_value.elementAt(17);	
												   Vector currency  =(Vector)  compnant_value.elementAt(18);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td><%=basic_price.elementAt(j)%>&nbsp;&nbsp;<%=currency.elementAt(j) %></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">9</td>
												<td class="text-left">Marketing Margin (per MMBTU)</td>
												<%Vector market_charge =(Vector)  compnant_value.elementAt(25);	
												   Vector market_charge_cur  =(Vector)  compnant_value.elementAt(26);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td><%=market_charge.elementAt(j)%>&nbsp;&nbsp;<%=market_charge_cur.elementAt(j) %></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">10</td>
												<td class="text-left">Regasification Charge (per MMBTU)</td>
												<%Vector regas_charge =(Vector)  compnant_value.elementAt(27);	
												   Vector regas_cur  =(Vector)  compnant_value.elementAt(28);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td><%=regas_charge.elementAt(j)%>&nbsp;&nbsp;<%=regas_cur.elementAt(j) %></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">11</td>
												<td class="text-left">Service Charge (per MMBTU)</td>
												<%Vector service_cherge =(Vector)  compnant_value.elementAt(29);	
												  Vector  service_cherge_cur =(Vector)  compnant_value.elementAt(30);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td><%=service_cherge.elementAt(j)%>&nbsp;&nbsp;<%=service_cherge_cur.elementAt(j) %></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">12</td>
												<td class="text-left">Transmission Charge (per MMBTU)</td>
												<%Vector trnas_charge =(Vector)  compnant_value.elementAt(31);	
												  Vector trans_cherge_cur  =(Vector)  compnant_value.elementAt(32);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td><%=trnas_charge.elementAt(j)%>&nbsp;&nbsp;<%=trans_cherge_cur.elementAt(j) %></td>
													<%}%>	
											</tr>
											
											<tr>
												<td class="text-center">13</td>
												<td class="text-left"><%=components.elementAt(8) %></td>
												<%Vector st_dt =(Vector)  compnant_value.firstElement();
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">14</td>
												<td class="text-left"><%=components.elementAt(9)%></td>
												<%Vector over_drwl =(Vector)  compnant_value.elementAt(5);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td><%=over_drwl.elementAt(j) %></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">15</td>
												<td class="text-left"><%=components.elementAt(10)%></td>
												<%Vector mode_nm =(Vector)  compnant_value.elementAt(11);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td><%=mode_nm.elementAt(j) %></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">16</td>
												<td class="text-left"><%=components.elementAt(11)%></td>
												<%Vector pay_period =(Vector)  compnant_value.elementAt(12);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td><%=pay_period.elementAt(j)%></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">17</td>
												<td class="text-left"><%=components.elementAt(12)%></td>
												<%Vector deply_pay_rate =(Vector)  compnant_value.elementAt(8);	
												  Vector pay_mode =(Vector)  compnant_value.elementAt(9);	
												  Vector rate_nm =(Vector)  compnant_value.elementAt(10);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td><%=rate_nm.elementAt(j)%>&nbsp;&nbsp;<%=pay_mode.elementAt(j).toString().trim()%><%=deply_pay_rate.elementAt(j)%></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">18</td>
												<td class="text-left"><b><%=components.elementAt(13)%></b></td>
												<%for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center"></td>
												<td class="text-center">Pressure</td>
												<%Vector pressure =(Vector)  compnant_value.elementAt(13);	
												  Vector pressure_unit =(Vector)  compnant_value.elementAt(14);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td>
																<%if(!pressure.elementAt(j).toString().trim().equalsIgnoreCase("-")){  %> 
																	<%=pressure.elementAt(j) %>&nbsp; <%=pressure_unit.elementAt(j) %>
																<%}else{%>
																   -  
																<% } %>
															</td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center"></td>
												<td class="text-center">Volume</td>
												<%Vector volume =(Vector)  compnant_value.elementAt(21);	
												  Vector volume_unit =(Vector)  compnant_value.elementAt(22);	
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td>
																<%if(!volume.elementAt(j).toString().trim().equalsIgnoreCase("-")){  %> 
																	<%=volume.elementAt(j) %>&nbsp; <%=volume_unit.elementAt(j) %>
																<%}else{%>
																	   -  
																<% } %>
															</td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center"></td>
												<td class="text-center">Temperature</td>
												<%Vector tempre =(Vector)  compnant_value.elementAt(23);	
												  Vector tempre_unit =(Vector)  compnant_value.elementAt(24);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td>
																<%if(!tempre.elementAt(j).toString().trim().equalsIgnoreCase("-")){  %> 
																	<%=tempre.elementAt(j) %>&nbsp; <%=tempre_unit.elementAt(j) %>
																<%}else{%>
																	   -  
																<% } %>
															</td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">19</td>
												<td class="text-left">
													<b><%=components.elementAt(14) %>&nbsp;&nbsp;Year & (Frequency)</b>
												</td>
												<%Vector freq_year =(Vector)  compnant_value.elementAt(15);	
												  Vector days =(Vector)  compnant_value.elementAt(16);	
												  Vector frequency = (Vector)  compnant_value.elementAt(19);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td>
																<%if(!freq_year.elementAt(j).equals("-")){  %>
																	<%=freq_year.elementAt(j)  %>  &nbsp;(<%=frequency.elementAt(j) %>)
																<%}else{ %>
																			-
																<%} %>
															</td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center"></td>
												<td class="text-center">No of Days</td>
												<%
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td>
																<%if(!days.elementAt(j).equals("-")  ){  %>
													 				<%=days.elementAt(j)  %>
													 			<%}else{  %>  - 
													 			<% } %>	
															</td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">20</td>
												<td class="text-left"><%=components.elementAt(15) %></td>
												<%for(int j=0;j<Vmapp_id.size();j++){  %> 
													 <td></td>
												<%}%>	
											</tr>
											<tr>
												<td class="text-center">21</td>
												<td class="text-left"><%=components.elementAt(16)%></td>
												<%Vector cont_extension = (Vector)compnant_value.elementAt(7);
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td><%=cont_extension.elementAt(j)%></td>
													<%}%>	
											</tr>
											<tr>
												<td class="text-center">22</td>
												<td class="text-left"><b><%=components.elementAt(17)%></b></td>
												<%
													for(int j=0;j<Vmapp_id.size();j++){  %> 
															<td></td>
													<%}%>	
											</tr>
											<% for(int i=0;i<quality_comp_cd.size();i++){ %>
												<tr>
													<td class="text-center"></td>
													<td class="text-center"><%=quality_comp_nm.elementAt(i) %></td>
													<%for(int j=0;j<Vmapp_id.size();j++){%> 
																<td><%=quality_comp_value[i].elementAt(j) %></td>
														<%}%>	
												</tr>
											<%}%>			
										<%}else{%>
											<tr>
												<td class="text-center">Data is not Available</td>
											</tr>
										<%}%>
				            		</tbody>
				            	</table>
				            </div>	
				        </div>
			        </div>		
          		</div>
          	</div>
   		</section>    		
<input type="hidden" name="contractdt" value="<%=contDate %>">
<input type="hidden" name="partycd" value="<%=party %>">
<input type="hidden" name="mappingcd" value="<%=mappingId %>">
<input type="hidden" name="curryear" value="<%=Current_year %>">
<input type="hidden" name="comp_cd1" value="<%=comm_id %>">
<input type="hidden" name="flag" value="<%=flag %>">
<input type="hidden" name="flag_1" value="<%=flag1 %>">

<%if(flag.equalsIgnoreCase("Y")){
	for(int i=0;i<vcomp_cd.size();i++){ %> <input type="hidden" name="comp_no" value="<%=vcomp_cd.elementAt(i) %>" ><%} %>
<% }%>
<%-- <input type="hidden" name="comm" value="<%=comm_id %>"> --%>
</div>
</form>
<!-- jQuery -->
<script src="../plugins/jquery/jquery.min.js"></script>
<!-- AdminLTE App -->
<script src="../dist/js/adminlte.min.js"></script>
</body>
</html>