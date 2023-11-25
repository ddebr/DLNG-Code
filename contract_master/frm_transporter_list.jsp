<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*" errorPage="" %>
<%@ include file="../sess/Expire.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title>Transporter List</title>

<link rel="stylesheet" type="text/css" href="../css/guidefaultGeneral.css">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css">
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<script type="text/javascript" src="../js/bootstrap.min.js"></script>

<style type="text/css">
#loading 
{
	width: 200px;
	height: 100px;
	background-color: #c0c0c0;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-top: -50px;
	margin-left: -100px;
	text-align: center;
}
th,td {
    font-size:small;
}
</style>

<script type="text/javascript">

document.write('<div id="loading"><br><br>Loading, Please Wait...</div>');

window.onload=function()
{
	document.getElementById("loading").style.display="none";
}
//function setVal(nm,abr,tdt,tid,cty,pc,st,stus,pn,rn,bn,strt,loc,dist,ph,fx){
	function setVal(nm,abr,tdt,tid,cty,pc,st,stus,pn,rn,bn,strt,loc,dist,ph,fx,vfg,dflg,vn1,vn2,lrn,lrrdt,drnm,drad,lcn,lcst,dcd){
	window.opener.setTransValues(nm,abr,tdt,tid,cty,pc,st,stus,pn,rn,bn,strt,loc,dist,ph,fx);
	if((vfg=="Y")||(dflg=="Y")){
	window.opener.setVehDrive(vfg,dflg,vn1,vn2,lrn,lrrdt,drnm,drad,lcn,lcst,dcd);
	}
	window.close();
}

</script>
</head>




<jsp:useBean class="com.seipl.hazira.dlng.truck_master.DataBean_transporter_Veh_Driver" id="tran" scope="page"/>
<%
	//String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
	String emp_cd=session.getAttribute("emp_cd")==null?"":""+session.getAttribute("emp_cd");

	tran.setCall_Var("TransporterLst");
	tran.init();
	
	Vector transporter_id = tran.getTransporter_id();
	Vector transporter_name = tran.getTransporter_name();
	Vector transporter_abbr= tran.getTransporter_abbr();
	Vector transporter_eff_date = tran.getTransporter_eff_date();
	Vector status = tran.getStatus_flag();
	Vector city = tran.getCity();
	Vector state = tran.getState();
	Vector pincode = tran.getPincode();
	Vector partner_name = tran.getPartner_name();
	Vector room_no =tran.getRoom_no();
	Vector building_nm =tran.getBuilding_nm();
	Vector street_nm =tran.getStreet_nm();
	Vector locality =tran.getLocality();
	Vector district =tran.getDistrict();
	Vector veh_flg = tran.getVeh_flag();
	Vector driver_flg = tran.getVeh_flag();
	Vector phone = tran.getPhone();
	Vector fax = tran.getFax();
	Vector vehicle_no1 = tran.getVehicle_no1();
	Vector vehicle_no2= tran.getVehicle_no2();
	Vector lr_no= tran.getLr_no();
	Vector lr_rec_dt= tran.getLr_rec_dt();
	Vector driver_name= tran.getDriver_name();
	Vector driver_addr= tran.getDriver_addr();
	Vector licence_no= tran.getLicence_no();
	Vector licence_issue_st= tran.getLicence_issue_st();
	Vector driver_cd = tran.getDriver_cd();

	

%>
<body>
<div class="box mb-0">
<form name="trans_list" method="post">
	<div class="box-body table-responsive no-padding">
		<table class="table  table-bordered">
			<thead>   
				<tr><td colspan="3" class="text-center"><b>Transporters' List</b></td></tr>
				<tr class="info">
					<td class="title2" ><div align="center">Select</div></td>
					<td class="title2"><div align="left">Transporter Name</div></td>
					<td class="title2"><div align="left">Abbreviation</div></td>
			    </tr>
			    </thead>
   					<%	try { %>
							<%	for(int i=0;i<transporter_name.size();i++){ %>
							
								 	<tr  class=<%=(i%2)==0?"":"content1"%>>
										<td align="center"><input type="radio" name ="trnradio" onclick="setVal('<%=transporter_name.elementAt(i)%>','<%=transporter_abbr.elementAt(i)%>','<%=transporter_eff_date.elementAt(i)%>','<%=transporter_id.elementAt(i)%>','<%=city.elementAt(i) %>','<%=pincode.elementAt(i) %>','<%=state.elementAt(i) %>','<%=status.elementAt(i) %>','<%=partner_name.elementAt(i) %>','<%=room_no.elementAt(i) %>','<%=building_nm.elementAt(i) %>','<%=street_nm.elementAt(i) %>','<%=locality.elementAt(i) %>','<%=district.elementAt(i)%>','<%=phone.elementAt(i)%>','<%=fax.elementAt(i)%>','<%=veh_flg.elementAt(i)%>','<%=driver_flg.elementAt(i)%>','<%=vehicle_no1.elementAt(i)%>','<%=vehicle_no2.elementAt(i)%>','<%=lr_no.elementAt(i)%>','<%=lr_rec_dt.elementAt(i)%>','<%=driver_name.elementAt(i)%>','<%=driver_addr.elementAt(i)%>','<%=licence_no.elementAt(i)%>','<%=licence_issue_st.elementAt(i)%>','<%=driver_cd.elementAt(i)%>');"></td>																																			
								 		<td align="center"><%=transporter_name.elementAt(i) %></td>
								 		<td align="center"><%=transporter_abbr.elementAt(i) %></td>
 		
								 	</tr>
									
								<%	}  %> 
						 <%	 if(transporter_name.size()==0){%>
							 <tr><th class="text-center" colspan="8" style="color: red;"><div class="mt-5"> Transporter Data Not Available!!</div> </th>
						  <% } 		
						 }catch(Exception e){	
							e.printStackTrace();
						   } %>
	<tr>
		<td colspan="3" align="left">
			<input type="button" value="Close" name="close" onClick="window.close();">
			
		</td>
	</tr>
</table>
  </div>
  
</form>
</div>
</body>
</html>