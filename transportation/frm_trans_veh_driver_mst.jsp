<%@ page language="java" import="java.util.*" errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>DLNG Transport Master</title>
<!-- CSS -->
<!-- <link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css" > -->
<!-- Font Awesome -->  
<!-- <link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css"> -->

<!-- jQuery -->
<!-- <script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js" ></script> -->

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/sort/dataTables.bootstrap.min.css">

<!-- Bootstrap JS -->
<script type="text/javascript" src="../js/fms.js"></script>
<script  type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="../js/sweetalert.min.js"></script>
<link rel="stylesheet" href="../css/tlu.css">

<style type="text/css">
table tr{
   color: black;
}
.select {
    width: 200px;
    height: 27px;
} 
.headColor{
color:blue;
}
.driv_dri{	
	display: none;
}
.veh_dri{
	display: none;
}

#updownSort {
  width: 20px;
  height: 23px;
  background: url(../images/updowns.png) 0 0;
}
</style>
<script type="text/javascript">
var nwin;
function transwin(){

if(!nwin || nwin.closed)
{
	nwin=window.open("../contract_master/frm_transporter_list.jsp","_blank","top=10,left=10,width=800,height=400,scrollbars=1,location=no");
}
else
{
	nwin.close();
	nwin=window.open("../contract_master/frm_transporter_list.jsp","_blank","top=10,left=10,width=800,height=400,scrollbars=1,location=no");
}
}
function doSubmit(event){
	
	var flag = true;
	var msg = "";

	var trans_nm = document.forms[0].transporter_name.value;
	var trans_abbr = document.forms[0].transporter_abbr.value;
	var trans_eff_dt = document.forms[0].transporter_eff_date.value;
	
	var room_n = document.forms[0].room_no.value;
	var building_n = document.forms[0].building_nm.value;
	var street_n = document.forms[0].street_nm.value;
	var locality = document.forms[0].locality.value;
	var city = document.forms[0].city.value;
	var district = document.forms[0].district.value;
	var state = document.forms[0].state.value;
	var phone = document.forms[0].phone_no.value;
	var pincode = document.forms[0].pincode.value;
	
	var trans_pt_nm = document.forms[0].transporter_partner_nm.value;
	var status = document.forms[0].status_flag.value;

	 if((trans_nm==null || trans_nm=='' || trans_nm==' ' || trans_nm=='  ' ) || (trans_abbr==null || trans_abbr=='' || trans_abbr==' ' || trans_abbr=='  ')
			|| (trans_eff_dt==null || trans_eff_dt=='' || trans_eff_dt==' ' || trans_eff_dt=='  ' || trans_eff_dt=='  ')){
		msg += "Please Enter Proper Transporter Name, Abbreviation & Eff. Date Details!!!\n";
		flag = false;
	}
	
	 if((room_n==null || room_n=='' || room_n==' ' || room_n=='  ') ||(building_n==null || building_n=='' || building_n==' ' )||
			(street_n==null || street_n=='' || street_n==' '|| street_n=='  ')||
			(locality==null || locality=='' || locality==' ' || locality=='  ' ) ||
			(city==null || city=='' || city==' ' || city=='  ' )||
			(state==null || state=='' || state==' ' || state=='  ')||
			(phone==null || phone=='' || phone==' ' || phone=='  ')||
			(pincode==null || pincode=='' || pincode==' ' || pincode=='  ')){
		msg += "Please Enter Proper Transporter RoomNo,Building,Street,Locality,City,State,Phone,Pincode Details!!!\n";
		flag = false;
	} 
	
	  if(document.forms[0].vehicle_chk.checked){
		 document.forms[0].vehicle_chkflg.value='Y';
		 var veh_no1 = document.forms[0].veh_no1.value;
			var veh_no2 = document.forms[0].veh_no2.value;//vehicle no2 compulsory or not check
			var lr_no = document.forms[0].lr_no.value;
			var lr_rec_dt = document.forms[0].lr_rec_date.value;
			//alert("veh1.."+veh_no1+"lr_no.."+lr_no+"lr_rec_dt.."+lr_rec_dt);
			 if((veh_no1==null || veh_no1=='' || veh_no1==' ' || veh_no1=='  ') || (lr_no==null || lr_no=='' || lr_no==' ' || lr_no=='  ')
						|| (lr_rec_dt==null || lr_rec_dt=='' || lr_rec_dt==' ' || lr_rec_dt=='  ')){
					msg += "Please Enter Proper Vehicle Details!!!\n";
					flag = false;
				}	
	 } 
	  if(document.forms[0].driver_chk.checked){
			 document.forms[0].driver_chkflg.value='Y';
			 var driver_nm = document.forms[0].driver_nm.value;
				var driver_addr = document.forms[0].driver_addr.value;
				var driver_lic_no = document.forms[0].driver_lic_no.value;
				var driver_st_cd = document.forms[0].driver_st_cd.value;
				//alert("driver_nm.."+driver_nm+"driver_lic_no.."+driver_lic_no+"driver_st_cd.."+driver_st_cd);
				 if((driver_nm==null || driver_nm=='' || driver_nm==' ' || driver_nm=='  ') || (driver_lic_no==null || driver_lic_no=='' || driver_lic_no==' ' || driver_lic_no=='  ')
							|| (driver_st_cd==null || driver_st_cd=='' || driver_st_cd==' ' || driver_st_cd=='  ')){
						msg += "Please Enter Proper Driver Details!!!\n";
						flag = false;
					}	
		 } 
	if(flag)
	{
   			var btnid = (event.target.id)[0].toUpperCase()+(event.target.id).slice(1, 6);
   			if(btnid=="SaveBt"){
   				btnid = btnid.slice(0,4);
   			}
   			var a = confirm("Do You Want To "+btnid+" Transporter Vehicle Driver Details?")
   			if(a)
   			{	
   				
	           	$('#transvehdriver').submit();
	           	
   			}
	}
	else
	{
		alert("First Checks the Following Fileds !\n\n"+msg)
	}
}
	
function setVal(transporter_name,effective_date,locality,city,state,phone,fax,partner_name,transporter_cd,transporter_abbr,room_no,building_nm,street_nm,vehicle_no1,vehicle_no2,lr_no,lr_rec_dt,driver_name,driver_cd,driver_addr,licence_no,license_issue_st,district,pincode,status){
	document.forms[0].transporter_name.value=transporter_name;
	document.forms[0].transporter_abbr.value=transporter_abbr;;
	document.forms[0].transporter_eff_date.value=effective_date;
	document.forms[0].transporter_cd.value=transporter_cd; 
	document.forms[0].room_no.value=room_no;
	document.forms[0].building_nm.value=building_nm;
	document.forms[0].street_nm.value=street_nm;
	document.forms[0].locality.value=locality;
	document.forms[0].city.value=city;
	document.forms[0].state.value=state;
	document.forms[0].status_flag.value=status;
	document.forms[0].phone_no.value=phone;
	document.forms[0].fax.value=fax;
	document.forms[0].district.value=district;
	document.forms[0].pincode.value=pincode;
	document.forms[0].transporter_partner_nm.value=partner_name;
	
	/* if(   (vehicle_no1==null || vehicle_no1=='' || vehicle_no1==' ' || vehicle_no1=='  ') 
	   || (vehicle_no2==null || vehicle_no2=='' || vehicle_no2==' ' || vehicle_no2=='  ')
	   || (lr_no==null || lr_no=='' || lr_no==' ' || lr_no=='  ')
	   || (lr_rec_dt==null || lr_rec_dt=='' || lr_rec_dt==' ' || lr_rec_dt=='  '))
	{  console.log("Data Not Available....");}
	else
	{ */
	
		document.forms[0].vehicle_chkflg.value="Y";
		document.forms[0].vehicle_chk.checked=true;
		document.forms[0].veh_no1.value=vehicle_no1;
		document.forms[0].veh_no2.value=vehicle_no2;
		document.forms[0].lr_no.value=lr_no;
		document.forms[0].lr_rec_date.value=lr_rec_dt;
		
		if(document.forms[0].vehicle_chk.checked){
			 $(".veh_dri").show(this.checked);
		}
		
	/* } */
	
	/* if(   (driver_name==null || driver_name=='' || driver_name==' ' || driver_name=='  ') 
			   || (driver_addr==null || driver_addr=='' || driver_addr==' ' || driver_addr=='  ')
			   || (licence_no==null || licence_no=='' || licence_no==' ' || licence_no=='  ')
			   || (license_issue_st==null || license_issue_st=='' || license_issue_st==' ' || license_issue_st=='  ')
			   || (driver_cd==null || driver_cd=='' || driver_cd==' ' || driver_cd=='  '))
	{  console.log("Driver Data Not Available....");}
	else
	{*/
			document.forms[0].driver_chkflg.value='Y';
			document.forms[0].driver_chk.checked=true;
			document.forms[0].driver_nm.value =driver_name;
			document.forms[0].driver_addr.value=driver_addr;
			document.forms[0].driver_lic_no.value=licence_no;
			document.forms[0].driver_st_cd.value=license_issue_st;
			if(driver_cd==null || driver_cd=='' || driver_cd==' ' || driver_cd=='  '){
				document.forms[0].driver_cd.value="0";	
			}else{
				document.forms[0].driver_cd.value=driver_cd;
			}
			
			if(document.forms[0].driver_chk.checked){
				$(".driv_dri").show(this.checked);
			}
	/* }   */
	
	document.forms[0].btnflag.value='Y';
	
	$('#saveBtn').hide('fast', function() {
		$('#updateBtn').show('fast', function() {
				$('#updateBtn').click(function() {
					document.getElementById('modify').value = document.getElementById('updateBtn').value;
				});				
			});
	});
}


function firstchar(obj){
	document.forms[0].transporter_abbr.value = obj.value.split(/\s/).reduce((response,word)=> response+=word.slice(0,1),'').toUpperCase();
}

function showDiv(obj){
	
	if(obj.id=="vehicle_chk"){
		 $(".veh_dri").toggle(this.checked);
	}
	if(obj.id=="driver_chk"){
		$(".driv_dri").toggle(this.checked);
	}
}
</script>
<style>
	table.dataTable thead .sorting:after,
	table.dataTable thead .sorting:before,
	table.dataTable thead .sorting_asc:after,
	table.dataTable thead .sorting_asc:before,
	table.dataTable thead .sorting_asc_disabled:after,
	table.dataTable thead .sorting_asc_disabled:before,
	table.dataTable thead .sorting_desc:after,
	table.dataTable thead .sorting_desc:before,
	table.dataTable thead .sorting_desc_disabled:after,
	table.dataTable thead .sorting_desc_disabled:before {
		bottom: .5em;
	}
	table.dataTable thead .sorting:after,
	table.dataTable thead .sorting_asc:after,
	table.dataTable thead .sorting_desc:after,
	table.dataTable thead .sorting:before,
	table.dataTable thead .sorting_asc:before,
	table.dataTable thead .sorting_desc:before {
    display: none;
	}
	table.dataTable thead > tr > th.sorting_asc,
	table.dataTable thead > tr > th.sorting_desc,
	table.dataTable thead > tr > th.sorting, 
	table.dataTable thead > tr > td.sorting_asc, 
	table.dataTable thead > tr > td.sorting_desc, 
	table.dataTable thead > tr > td.sorting{
    	padding-right: 0px;
	}
</style>
<script  type="text/javascript">
$(document).ready(function () {
	$('#tablexample').DataTable({
		"order": [[ 3, "desc" ]],
		"bFilter":false,
		"bPaginate":false,
		"bInfo":false,
		
		"scrollY": "200px",
		"scrollCollapse": true
	});
	$('.dataTables_length').addClass('bs-select');
	
});
</script>

</head>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.transporter.DataBean_transporter_Veh_Driver" id="transmst" scope="page"/>  
<jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_modulelock" id="modlock" scope="request"/>
<%  
NumberFormat nf = new DecimalFormat("###########0.00");
	utilBean.init();
	String date = utilBean.getGen_dt();
    String username=(String)session.getAttribute("username");
	String servFlag = "N";
	String buttons = "";		 
    
	String msg = request.getParameter("msg")== null?"":request.getParameter("msg");	
	String erro_msg = request.getParameter("erro_msg")==null?"":request.getParameter("erro_msg");
	String write_permission = (String)session.getAttribute("write_permission") ;
	String delete_permission = (String)session.getAttribute("delete_permission") ;
	String print_permission = (String)session.getAttribute("print_permission") ;
	String approve_permission = (String)session.getAttribute("approve_permission") ;
	String audit_permission = (String)session.getAttribute("audit_permission") ;
	String user_cd=(String)session.getAttribute("user_cd"); 
	
	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200227
	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200227
	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200227
	servFlag = request.getParameter("flag")==null?"":request.getParameter("flag");
	buttons = request.getParameter("modifiers")==null?"":request.getParameter("modifiers");
	
	transmst.init();
	Vector statecode = transmst.getStatecode();
	Vector statename = transmst.getStatename();
	
	Vector transporter_cd = transmst.getTransporter_id();
	Vector transporter_name = transmst.getTransporter_name();
	Vector transporter_abbr = transmst.getTransporter_abbr();
	Vector effective_date = transmst.getTransporter_eff_date();
	Vector partner_name = transmst.getPartner_name();
	Vector room_no = transmst.getRoom_no();
	Vector building_nm = transmst.getBuilding_nm();
	Vector street_nm = transmst.getStreet_nm();
	Vector locality = transmst.getLocality();
	Vector city = transmst.getCity();
	Vector district = transmst.getDistrict();
	Vector pincode = transmst.getPincode();
	Vector state = transmst.getState();
	Vector phone = transmst.getPhone();
	Vector fax = transmst.getFax();
	Vector status = transmst.getStatus_flag();
	Vector state_nm = transmst.getState_nm();//SUJIT17SEP2020
	
	Vector vehicle_no1 = transmst.getVehicle_no1();
	Vector vehicle_no2 = transmst.getVehicle_no2();
	Vector lr_no = transmst.getLr_no();
	Vector lr_rec_dt = transmst.getLr_rec_dt();
	Vector driver_name = transmst.getDriver_name();
	Vector driver_cd = transmst.getDriver_cd();
	Vector driver_addr = transmst.getDriver_addr();
	Vector licence_no = transmst.getLicence_no();
	Vector license_issue_st = transmst.getLicence_issue_st();
%>

<body >
<div class="tab-content">
	<div class="tab-pane active" >
		<div class="box mb-0">
			<form name="trnsvehdri" id="transvehdriver" method="post" action="../servlet/Frm_Transporter_Veh_Driver_Master">
				<div class="box-header with-border" style="background-color:#ffe57f">
					<div class="row">
						<div class="col-lg-1 col-md-2 col-sm-6 col-xs-12 hidden-xs hidden-sm"></div>
					</div>
<% if(!msg.equals("")) {%>
	<div class="box-body table-responsive no-padding">
		<table class="table  table-bordered">
			<thead>   
				<tr class="info">
					<th class="text-center" colspan="2" style="color: blue;"> <%=msg %></th>
				</tr>
			</thead>
		</table>
	</div>
<%} %>

<% if(!erro_msg.equals("")) {%>
	<div class="box-body table-responsive no-padding">
		<table class="table  table-bordered">
			<thead>   
				<tr class="info">
					<th class="text-center" colspan="2" style="color: red;"> <%=erro_msg %></th>
				</tr>
			</thead>
		</table>
	</div>
<%} %>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="form-group">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<input type="hidden" name="tid">
								    <input type="hidden" name="btnflag">	
								    <input type="hidden" id="modify" name="modifiers" value="">	
									<input type="hidden" name="transporter_cd" value="">
										
									<label>Transporter Name</label>
									<div class="form-group">
										<input type='text' class="form-control" name="transporter_name" id="transporter_name" maxlength="100" value="" autocomplete="off" onblur="firstchar(this);"/>		
									</div>	
								</div>
								
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<label>Transporter Abbreviation</label>
									<div class="form-group">
										<input type='text' class="form-control" name="transporter_abbr" id="transporter_abbr" value="" readonly autocomplete="off"/>		
									</div>	
								</div>
								
								<!-- <div class="col-lg-1 col-md-1 col-sm-1 col-xs-12">
									<label>&nbsp;</label>
									<button type="button" class="btn btn-primary" value="View List" name="ExistTrans" onclick="transwin();">View List</button>
								</div> -->
								
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<label>Transporter Effective Date<span class="s-red">*</span></label>
									<div class="form-group mb-0">
										<div class='input-group date' id='datetimepicker1'>
											<input type='text' class="form-control" name="transporter_eff_date" id="dt2" maxlength="10" value="" onBlur="validateDate(this);" autocomplete="off"/>
											<span class="input-group-addon">
												<i class="fa fa-calendar"></i>
											</span>
										</div>
									</div>
								</div>
							</div>
						</div>
						
					</div>
					
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<h4 class="headColor">Transporter Address Details</h4>
						</div>
					</div>
					
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="form-group">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<label>Room/Block/Flat No.<span class="s-red">*</span></label>
									<div class="form-group">
										<input type='text' class="form-control" name="room_no" id="rn"  maxlength="10" value="" autocomplete="off"/>		
									</div>	
								</div>
								
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<label>Building Name<span class="s-red">*</span></label>
									<div class="form-group">
										<input type='text' class="form-control" name="building_nm" id="bn" maxlength="30" value="" autocomplete="off"/>		
									</div>	
								</div>
								
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<label>Road/Street/Land<span class="s-red">*</span></label>
									<div class="form-group">
										<input type='text' class="form-control" name="street_nm" maxlength="55" value="" autocomplete="off"/>		
									</div>	
								</div>
							</div>
						</div>
						
					</div>
					
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="form-group">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<label>Area/Locality/Ward<span class="s-red">*</span></label>
									<div class="form-group">
										<input type='text' class="form-control" name="locality" id="ly" maxlength="55" value="" autocomplete="off"/>		
									</div>	
								</div>
								
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<label>Town/City<span class="s-red">*</span></label>
									<div class="form-group">
										<input type='text' class="form-control" name="city" value="<%//=city%>" maxlength="30" value="" autocomplete="off"/>		
									</div>	
								</div>
								
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<label>State<span class="s-red">*</span></label>
									<div class="form-group">
										<select  class="form-control" name="state" >
											<option value="Select State">Select State</option>
											<%for(int i=0;i<statecode.size();i++) {%>
												<option value="<%=statecode.elementAt(i)%>"><%=statename.elementAt(i).toString().trim()%></option>
											<%} %>
										</select> 		
									</div>	
								</div>
								
							</div>
						</div>
						
					</div>
					
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="form-group">
								<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
									<label>District<span class="s-red">*</span></label>
									<div class="form-group">
										<input type='text' class="form-control" name="district" id="dist"  maxlength="55" value="" autocomplete="off"/>		
									</div>	
								</div>
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
									<label>Pincode<span class="s-red">*</span></label>
									<div class="form-group">
										<input type='text' class="form-control" name="pincode" id="pe" maxlength="6" value="" autocomplete="off"/>		
									</div>	
								</div>
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
									<label>Phone No.<span class="s-red">*</span></label>
									<div class="form-group">
										<input type='text' class="form-control" name="phone_no" maxlength="7" value="" autocomplete="off"/>		
									</div>	
								</div>
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
									<label>Fax</label>
									<div class="form-group">
										<input type='text' class="form-control" name="fax" maxlength="7" value="" autocomplete="off"/>		
									</div>	
								</div>
								
								<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
									<label>Owner/Partner&nbsp;Name<span class="s-red">*</span></label>
									<div class="form-group">
										<input type='text' class="form-control" name="transporter_partner_nm" maxlength="100" value="" autocomplete="off"/>		
									</div>	
								</div>
								
							</div>
						</div>
						
					</div>
					
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
								<div class="form-group mb-0 row text-center">
									<h4 class="headColor">Vehicle Details</h4>
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
								<div class="form-group mb-0 row text-center">
									<input type="checkbox" name="vehicle_chk" id="vehicle_chk" value="" class="select" title="Click to enter Vehicle Details" onclick="showDiv(this);">
									<input name="vehicle_chkflg" type="hidden" value="N" maxlength="1">
								</div>
							</div>
						</div>
						
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
								<div class="form-group mb-0 row text-center">
									<h4 class="headColor">Driver Details</h4>
								</div>
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
								<div class="form-group mb-0 row text-center">
									<input type="checkbox" name="driver_chk" id="driver_chk" value="" class="select" title="Click to enter Driver Details" onclick="showDiv(this);">
									<input name="driver_chkflg" type="hidden" value="N" maxlength="1">
								</div>
							</div>
						</div>
						
					</div>
					
					<div class="row veh_dri">
						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
								<label>Vehicle No1</label>
								<div class="form-group">
									<input type='text' class="form-control" name="veh_no1" id="veh_no1" maxlength="11" value="" onkeyup="regularExpression(this)" autocomplete="off"/>		
								</div>	
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
								<label>Vehicle No2</label>
								<div class="form-group">
									<input type='text' class="form-control" name="veh_no2" id="veh_no2" maxlength="11" value="" onkeyup="regularExpression(this)" autocomplete="off"/>		
								</div>	
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
								<label>LR No.</label>
								<div class="form-group">
									<input type='text' class="form-control" name="lr_no" maxlength="10" value="" autocomplete="off"/>		
								</div>	
							</div>
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
								<label>LR Receipt Date</label>
								<div class="form-group mb-0">
									<div class='input-group date' id='datetimepicker2'>
										<input type='text' class="form-control" id="dt1" name="lr_rec_date"  maxlength="10" value="" onBlur="validateDate(this);" autocomplete="off"/>
										<span class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</span>
									</div>
								</div>
							</div>
						</div>	
					</div>
					
					<div class="row driv_dri">
						<hr/>
						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
								<label>Driver Name</label>
								<div class="form-group">
									<input type="text" class="form-control" name="driver_nm" maxlength="50" value="" autocomplete="off"/>
									<input type="hidden" name="driver_cd"  maxlength="50" value="">		
								</div>	
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
								<label>Driver Licence No</label>
								<div class="form-group">
									<input type='text' class="form-control" name="driver_lic_no" maxlength="15" value="" autocomplete="off"/>		
								</div>	
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
								<label>Driver Address</label>
								<div class="form-group">
									<input type='text' class="form-control" name="driver_addr" maxlength="100" value="" autocomplete="off"/>		
								</div>	
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
								<label>Licence Issuing State</label>
								<div class="form-group mb-0">
									<select class="form-control" name="driver_st_cd" id="driver_stcd" >
										<option value="Select State">Select State</option>
										<%for(int i=0;i<statecode.size();i++) {%>
											<option value="<%=statecode.elementAt(i)%>"><%=statename.elementAt(i).toString().trim()%></option>
										<%} %>
									</select> 
								</div>
							</div>
						</div>
					</div>
				</div>
			<input type="hidden" name="modCd" value="<%=modCd%>">
			<input type="hidden" name="formId" value="<%=formId%>">
			<input type="hidden" name="subModUrl" value="<%=subModUrl%>">			
			<input type="hidden" name="currentDate" value="<%=date%>">
			<input type="hidden" name="flg" value="">
			<input type="hidden" name="status_flag" value="Y">
			<input type="hidden" name="write_permission" value="<%=write_permission%>">
			<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
			<input type="hidden" name="print_permission" value="<%=print_permission%>">
			<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
			<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
			<input type="hidden" name="user_cd" value="<%=user_cd%>">
			
			</form>
			
			
			<div class="box-footer">
				<div class="row">
					<div class="col-sm-12 text-right">
						<button type="button" class="btn btn-warning" name="reSetPage" value="Reset" onClick="resetPage();"> Reset </button>
						<button onclick="doSubmit(event);" type="button" class="btn btn-success" id="saveBtn" name="save" value="Submit" >Submit</button>
						<button onclick="doSubmit(event);" type="button" class="btn btn-success" id="updateBtn" value="update" >Update</button>
						<!-- <button onclick="doSubmit(event);" type="button" class="btn btn-danger" id="deleteBtn" value="delete" >Delete</button> -->
					</div>
				</div>
			</div>
			
			<div class="box-body table-responsive no-padding">
				<div class="col-lg-1 col-md-2 col-sm-6 col-xs-12 hidden-xs hidden-sm"></div>
				<table id="tablexample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
					<thead>   
						<tr class="info">
							<th></th>
							<th>Transporter Name <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>Effective Date <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>Area<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>City<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>State<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>Phone No.<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>Fax<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>Owner Name<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>Status<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
						</tr>
					</thead>
					 <tbody>
					 	<%try{%>
								<%if(transporter_name.size()>0)
								  {%>
										<%for(int i=0;i<transporter_name.size();i++){ %>
										 	<tr>
												<td onclick="setVal('<%=transporter_name.elementAt(i)%>','<%=effective_date.elementAt(i)%>','<%=locality.elementAt(i)%>',
										 			'<%=city.elementAt(i) %>','<%=state.elementAt(i) %>','<%=phone.elementAt(i) %>','<%=fax.elementAt(i)%>','<%=partner_name.elementAt(i)%>',
										 			'<%=transporter_cd.elementAt(i)%>','<%=transporter_abbr.elementAt(i)%>','<%=room_no.elementAt(i)%>','<%=building_nm.elementAt(i)%>',
										 			'<%=street_nm.elementAt(i)%>','<%=vehicle_no1.elementAt(i)%>','<%=vehicle_no2.elementAt(i) %>','<%=lr_no.elementAt(i) %>',
										 			'<%=lr_rec_dt.elementAt(i) %>','<%=driver_name.elementAt(i) %>','<%=driver_cd.elementAt(i) %>','<%=driver_addr.elementAt(i) %>',
										 			'<%=licence_no.elementAt(i) %>','<%=license_issue_st.elementAt(i) %>','<%=district.elementAt(i)%>','<%=pincode.elementAt(i)%>','<%=status.elementAt(i) %>');"><i class="fa fa-pencil-square-o" style="font-size:20px;color:red"></i></td>																																			
										 		<td align="center"><%=transporter_name.elementAt(i) %></td>
										 		<td align="center"><%=effective_date.elementAt(i) %></td>
										 		<td align="center" ><%=locality.elementAt(i) %></td>
										 		<td align="center"><%=city.elementAt(i) %></td>
										 		<td align="center"><%=state_nm.elementAt(i) %></td>
										 		<td align="center"><%=phone.elementAt(i) %></td>
										 		<td align="center"><%=fax.elementAt(i) %></td>
										 		<td align="center"><%=partner_name.elementAt(i) %></td>
										 		<td align="center"><%=status.elementAt(i) %></td>
										 	</tr>
										<%}%> 
								<%}else{%>
							 		<tr><th class="text-center" colspan="8" style="color: red;"><div class="mt-5">Transporter Vehicle Driver Details Not Available!!</div> </th>
						  		<%}%> 		
						<%}catch(Exception e){	
							e.printStackTrace();
						  } %>
					 </tbody>
				</table>
			</div>
				
			
		</div>
	</div>
</div>
<!-- jQuery -->
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script type="text/javascript">
$(function () {
	$('#datetimepicker1').datepicker({
	format: "dd/mm/yyyy",
	language: "en",
	autoclose: true,
	todayHighlight: true,
	orientation: "bottom auto"
	});
	});
	
$(function () {
	$('#datetimepicker2').datepicker({
	format: "dd/mm/yyyy",
	language: "en",
	autoclose: true,
	todayHighlight: true,
	orientation: "bottom auto"
	});
	});
	
function resetPage(){
	swal({
		title: "Are you Sure you want to Reset??",
	    icon: "img/question.png",
	    allowOutsideClick: false,
		closeOnClickOutside: false,
	    buttons:[
	        'Cancel',
	        'Submit'
	        ],
	        }).then(function(isConfirm){        
	            if(isConfirm){
	            		$('select').prop('selectedIndex',0);
	            		$("input[type=text], textarea").val("");
	            		window.onload = $('#updateBtn').hide( function() {});
	            		window.onload = $('#saveBtn').show( function() {});
	            } 
	    });
}
window.onload = $('#updateBtn').hide( function() {});


function regularExpression(obj){  //SUJIT06MARCH2020
	var dexp = /^[0-9]+$/;
	var sexp = /^[a-zA-Z]+$/;
	var firstCh = obj.value.slice(0,2);
	var secondNum = obj.value.slice(2,4);
	var fifthCh = obj.value.slice(5,7);
	var lastNum = obj.value.slice(7,11);
	obj.value = obj.value.toUpperCase();
	//$('#truck_name').val($('#truck_name').val().toUpperCase());
	//$('#truck_name').val($('#truck_name').val().replace(/ +?/g, '')); // remove Space on keyup
	
	if(firstCh.match(sexp)){	
		if(secondNum.match(dexp)){
			 if(obj.value.length == 4){
				 obj.value = [obj.value.slice(0, 4),"-", obj.value.slice(4)].join('');
			 }
			 if(!fifthCh.match(sexp)){	 
				 if(fifthCh.length==1 || fifthCh.length==2){
					 alert("Please Enter in Required Formate(GJ06-AA1234) ")
					 obj.value='';
				 }
			 }
			  
			 if(!lastNum.match(dexp)){
				if(lastNum.length==parseFloat(1) || lastNum.length==parseFloat(2) || lastNum.length==parseFloat(3) || lastNum.length==parseFloat(4)){
					alert("Please Enter in Required Formate(GJ06-AB1234) ")
					obj.value='';
				}
			}else{
				
				if(lastNum.length==parseFloat(2) || lastNum.length==parseFloat(3) || lastNum.length==parseFloat(4))
				{
					if(lastNum.slice(0,2) == parseFloat(0) || lastNum.slice(0,3) == parseFloat(0) || lastNum.slice(0,4) == parseFloat(0))
					{
						alert("Please Enter Valid Number Or It can't be "+lastNum)	
						obj.value = "";
					}
				}
				
			}
			 
		}
		else  
		{
		   if(secondNum.length==1 || secondNum.length==2){
			   alert("Please Enter in Required Formate(GJ06-AA1234) ")
			   obj.value='';
			}
		}
	}else{
		 alert("Please Enter in Required Formate(GJ06-AA1234) ")
		 obj.value='';
	}
}
</script>
</body>
</html>