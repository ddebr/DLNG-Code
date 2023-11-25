<%@ page import="java.util.*,java.text.*" %>
<%@ page buffer="128kb" %>
<%@ page errorPage="../home/ExceptionHandler.jsp" %>
<%@ include file="../sess/Expire.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>FMS | Master MDQ Report</title>

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

<style type="text/css">
.card-header{
background-color: #ffc107;
}
</style>

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

function checkdigit()
{
	if (event.keyCode >= 96 && event.keyCode <= 105 || event.keyCode == 144 || event.keyCode == 110)
	{
		event.returnValue = true;
	}
	else
	{
		if ((event.keyCode < 45) && !(event.keyCode == 8)&& !(event.keyCode == 9)&& !(event.keyCode == 13)|| event.keyCode > 57 && event.keyCode != 190)
		{
			alert("Please make sure entries are Number only.")
			event.returnValue = false;
		}
		else if (event.keyCode == 8)
		{
			event.returnValue = true;
		}
		else if (event.keyCode == 9)
		{
			event.returnValue = true;
		}
		else if (event.keyCode == 13)
		{
			event.returnValue = true;
		}
		else if (event.keyCode == 110)
		{
			event.returnValue = true;
		}
		else
		{
			event.returnValue = true;
		}		
	}
}

function refresh()
{
	year=document.forms[0].year.value;
	month = document.forms[0].sel_month.value;
	// map=document.forms[0].map.value;
	var flag1='B';
	flg = document.forms(0).flag.value;
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
	 	var url="rpt_mdq_details.jsp?year="+year+"&sel_month="+month+"&date="+date+"&comm="+comm_id;// &status="+sts+"
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
	  
	  flg1 = document.forms(0).flag_1.value;
	  if(flg1=='A')
	  { //  alert(' %  onload');
	  	// document.forms[0].comm.value='A';
	  }
	  
	  if(date==null || date=='')
	  {}
	  else{
	      dte=date.split("/");
		  year="20"+dte[2];
		  document.forms[0].year.value=year;
	  }
	 
	  if(sts=="active")
	  {
			 //	document.forms[0].status[0].checked = true;
	  }
	  else
	  {
			 //	document.forms[0].status[1].checked = true;
	  }
	  if(sts=="")
	  {
		  //	document.forms[0].status[0].checked = true;
	  }
	  document.forms[0].year.value=document.forms[0].curryear.value;
}

function send_exchange_rate()
{
	year=document.forms[0].year.value;	
	var flag1='B';
	flg = document.forms(0).flag.value;
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
	var exch_rate = document.forms[0].exchnge_rate.value;

	if(comm_id != '0')
	{
	 	var url="rpt_mdq_details.jsp?year="+year+"&date="+date+"&comm="+comm_id+"&exchnge_rate="+exch_rate;// &status="+sts+"
	 	location.replace(url);
	}
}
</script>
</head>

<jsp:useBean class="com.Gsfc.Baroda.fms6.master.DataBean_master_rpt" id="masCont" scope="page"/>   
<jsp:useBean class="com.Gsfc.Baroda.fms6.util.UtilBean" id="utilBean" scope="request"/>
<%
java.text.NumberFormat nf = new DecimalFormat("#####0.00");
utilBean.init();
String year=utilBean.getGet_yr();
String gen_dt = utilBean.getGen_dt();
String cur_month = gen_dt.substring(3,5);
String[] monthName=utilBean.monthname;
String comp_cd=(String)session.getAttribute("comp_cd");// BY SM 
String emp_cd =(String)session.getAttribute("user_cd");// BY SM 
String comm_id = request.getParameter("comm")==null?comp_cd:request.getParameter("comm"); // BY SM
String contDate=request.getParameter("date")==null?"":request.getParameter("date");
String party=request.getParameter("party")==null?"":request.getParameter("party");
String mapping =request.getParameter("mapping")==null?"":request.getParameter("mapping");
String Current_year =request.getParameter("year")==null?year:request.getParameter("year");
String sel_month = request.getParameter("sel_month")==null?cur_month:request.getParameter("sel_month");

if(Current_year.equalsIgnoreCase(""))
{
	Current_year = year;
}

String mappingId =request.getParameter("map")==null?"0":request.getParameter("map");
String status =request.getParameter("status")==null?"active":request.getParameter("status");
String flag =request.getParameter("flag")==null?"":request.getParameter("flag");
String flag1 =request.getParameter("flag_1")==null?"B":request.getParameter("flag_1");

masCont.setCallFlag("MDQDetailsReport");
masCont.setParent_comp_cd(comp_cd); // BY SM 
masCont.setCompany_cd(comm_id); // BY SM
masCont.setEmp_cd(emp_cd);
masCont.setStatus(status);
masCont.setSet_year(Current_year);
masCont.setSet_month(sel_month);
masCont.setSet_pty_cd(party);
masCont.setMapping_cd(mapping);
masCont.setMappingCd(mappingId);
masCont.init();
   
String comp_nm = masCont.getComp_nm();
float exchange_rate = masCont.getExchange_rate();

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
Vector cont_st_dt =  masCont.getVcont_st_dt();
Vector cont_end_dt =  masCont.getVcont_end_dt();
Vector eff_dt = masCont.getVmdq_eff_dt();
Vector nhv = masCont.getVnhv();
Vector ghv = masCont.getVghv();
Vector entry_pt_mdq = masCont.getVentry_pt_mdq();
Vector exit_pt_mdq =  masCont.getVexit_pt_mdq();
Vector entry_pt_energy =  masCont.getVenegry();
Vector exit_pt_energy =   masCont.getVenegry1();	
Vector heat_unit = masCont.getVheat_unit();
Vector energy_unit = masCont.getVenergy_unit();
Vector avg_alloc_vol = masCont.getAvg_alloc_vol();
Vector rs_scm = masCont.getVrs_scm();			
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
            			<h1>Master Report : MDQ Details<%//=formName%></h1>
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
								<div class="card-title"><h4>MDQ Details</h4></div>
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
											    	document.forms(0).year.value = '<%=Current_year %>';
											    </script>
				            				</th>
				            				<th>Month:</th>
				            				<th>
												<select name="sel_month" onchange="refresh();">
													<!-- <option value="0">-Select Month-</option> -->
													<option value="01">January</option>
													<option value="02">February</option>
													<option value="03">March </option>
													<option value="04">April </option>
													<option value="05"> May</option>
													<option value="06"> June </option>
													<option value="07"> July </option>
													<option value="08"> August </option>
													<option value="09"> September</option>
													<option value="10"> October </option>   
													<option value="11"> November </option>
													<option value="12"> December</option>
												</select>
												<script>
														document.forms(0).sel_month.value='<%=sel_month%>';
												</script>
				            				</th>
				            				<th>Parent Unit :</th>
				            				<th><%=comp_nm %></th>
				            				<% if(flag.equalsIgnoreCase("Y")){ %>
				            					<th></th>
				            					<th>
				            						<select name="comm" onchange="refresh();">
														<option value="0">-- Select Unit --</option>
														<option value="A">All Unit</option>
														<%for (int i = 0; i < Vcomm_dtl_id.size(); i++) {%>
															<option value="<%=Vcomm_dtl_id.elementAt(i)%>"><%=Vcomm_dtl_nm.elementAt(i)%></option>
														<%}%>
													</select>
													<script>
													 		document.forms(0).comm.value ='<%=comm_id%>' ;		
													</script>
												</th>
				            				<%} %>
				            				<th>Exchange Rate : </th>
				            				<th>
				            					<input type="text" name="exchnge_rate" value="<%=exchange_rate %>" maxlength="7" size="4" readonly="readonly"> INR/USD
				            				</th>
				            			</tr>
				            		</thead>
				            	</table>
				            </div>
				             <div class="card-body table-responsive p-0" style="height: 340px;">
				            	<table class="table table-head-fixed text-nowrap table-bordered">
				            		<thead>
				            			<tr>
				            				<th class="text-center">Contract</th>
				            				<th class="text-center">Contract Period</th>
				            				<th class="text-center">MDQ<br>Effective<br>Date</th>
				            				<th class="text-center">NHV</th>
				            				<th class="text-center">GHV</th>
				            				<th class="text-center">NHV&nbsp;|&nbsp;GHV<br>Unit</th>
				            				<th class="text-center">Entry Point<br>MDQ<br>(MMSCM)</th>
				            				<th class="text-center">Exit Point<br>MDQ<br>(MMSCM)</th>
				            				<th class="text-center">Entry Point<br>Energy</th>
				            				<th class="text-center">Exit Point<br>Energy</th>
				            				<th class="text-center">Energy<br>Unit</th>
				            				<th class="text-center">
				            					<% String temp1="";String temp_A ="";
									 				if(Integer.parseInt(sel_month)>3){
									 					temp_A = (Integer.parseInt(Current_year) + 1)+"";
									 					temp1 = (Current_year)+ "-" + temp_A.substring(2,4)  ;
									 				}else{
									 					temp_A = (Current_year);
									 					temp1 =  (Integer.parseInt(Current_year) -1 ) + "-" + temp_A.substring(2,4)  ;
									 				}%>
				            					Average<br>Allocation<br>FY <%=temp1%><br>(SCM/Day)</th>
				            				<th class="text-center">Landed<br>Cost<br>(Rs/SCM)</th>
				            			</tr>
				            		</thead>
				            		<tbody>
				            			<%if(!Vmapp_id.isEmpty()){ %>
				            				<%for (int i = 0; i < Vmapp_id.size(); i++) 
				            				  {%> 
											 	  <% if(i%2==0){ %>
											 	     <tr>
											 	  <%}else{ %>	
											 	     <tr>
											 	  <%}%>
											 		<td class="text-center"><%=party_abr.elementAt(i).toString().trim()%></td>
											 		<td class="text-center"><%=cont_st_dt.elementAt(i).toString().trim()%> - <%=cont_end_dt.elementAt(i).toString().trim()%></td>
											 		<td class="text-center"><%=eff_dt.elementAt(i).toString().trim()%></td>
											 		<td class="text-center"><%=nhv.elementAt(i).toString().trim()%></td>
											 		<td class="text-center"><%=ghv.elementAt(i).toString().trim()%></td>
											 		<td class="text-center"><%=heat_unit.elementAt(i).toString().trim()%></td>
											 		<td class="text-center">
											 			<%String temp="";
											 			  if(entry_pt_mdq.elementAt(i).toString().trim().equalsIgnoreCase("-")){
											 					temp ="-";
											 			  }else{
											 					temp = nf.format(Double.parseDouble(entry_pt_mdq.elementAt(i).toString())) ;
											 			  }%>
											 		 	<%=temp%>
											 		</td>
											 		<td class="text-center">
											 			<%if(exit_pt_mdq.elementAt(i).toString().trim().equalsIgnoreCase("-")){
											 					temp ="-";
											 			  }else{
											 					temp = nf.format(Double.parseDouble(exit_pt_mdq.elementAt(i).toString())) ;
											 			  }%>
											 		   <%=temp%>											 		 
											 	    </td>
											 		<td class="text-center">
											 			<%if(entry_pt_energy.elementAt(i).toString().trim().equalsIgnoreCase("-")){
											 					temp ="-";
											 			  }else{
											 					temp = nf.format(Double.parseDouble(entry_pt_energy.elementAt(i).toString())) ;
											 			  }%>
											 			<%=temp%>
											 		 </td>
											 		<td class="text-center">
											 				<%if(exit_pt_energy.elementAt(i).toString().trim().equalsIgnoreCase("-")){
											 					temp ="-";
											 				}else{
											 					temp = nf.format(Double.parseDouble(exit_pt_energy.elementAt(i).toString())) ;
											 				}%>
											 			 <%=temp%>
											 		</td>
											 		<td class="text-center"><%=energy_unit.elementAt(i).toString().trim()%></td>
											 		<td class="text-center"><%=avg_alloc_vol.elementAt(i) %></td>
											 		<td class="text-center">
													 		<%if(rs_scm.elementAt(i).equals("-")){
																 	temp="-";
															 }else{
																 temp = nf.format(Double.parseDouble(rs_scm.elementAt(i)+""));
															 }%>
											 			<%=temp %>
											 		</td>
											 	</tr>
											 <%}%>
				            			<% } else{%>
				            				<tr>
				            					<td class="text-center">Data is not available</td>
				            				</tr> 
										<% } %>
				            		
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
<%-- <input type="hidden" name="exchnge_rate" value="<%=exchange_rate %>"> --%> 
<%if(flag.equalsIgnoreCase("Y")){
	for(int i=0;i<vcomp_cd.size();i++){ %> <input type="hidden" name="comp_no" value="<%=vcomp_cd.elementAt(i) %>" > <% } %>
<% } %>
<%-- <input type="hidden" name="comm" value="<%=comm_id %>"> --%>
</div>
<footer class="main-footer">
    <strong>Copyright &copy;2020 <a href="https://barodainformatics.com/BIPL2015/">BIPL</a>.</strong>
    	All rights reserved.
    <div class="float-right d-none d-sm-inline-block">
    	<b>Version</b> 1.0.0
    </div>
</footer>
</form>
<!-- jQuery -->
<script src="../plugins/jquery/jquery.min.js"></script>
<!-- AdminLTE App -->
<script src="../dist/js/adminlte.min.js"></script>
</body>
</html>