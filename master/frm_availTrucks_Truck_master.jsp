<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/sort/dataTables.bootstrap.min.css">

<!-- Bootstrap JS -->
<script type="text/javascript" src="../js/fms.js"></script>
<script  type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="../js/sweetalert.min.js"></script>
<link rel="stylesheet" href="../css/tlu.css">
<script type="text/javascript">
function doSubmit(event){
	var flag = true;
	var msg = "";
	var truck_nm = document.forms[0].truck_name.value;
	var truck_typ = document.forms[0].truck_type.value;
	var effect_date = document.forms[0].eff_date.value;
	var tank_volume = document.forms[0].truck_volume3.value;
	var status = document.forms[0].status_flag.value;
//	var customer_name = document.forms[0].customer_name.value;
	var trans_cd = document.forms[0].trans_cd.value;
	
	if(truck_nm==null || truck_nm=='' || truck_nm==' ' || truck_nm=='  ' || truck_nm=='  '){
		msg += "Please Enter The Correct Truck Reg. No.!!!\n";
		flag = false;
	}
	if(truck_typ==null || truck_typ=='' || truck_typ==' ' || truck_typ=='  ' || truck_typ=='  '){
		msg += "Please Enter The Correct Truck Type!!!\n";
		flag = false;
	}
	if(effect_date==null || effect_date=='' || effect_date==' ' || effect_date=='  ' || effect_date=='  '){
		msg += "Please Enter The Correct Effective DATE!!!\n";
		flag = false;
	}
	if(tank_volume==null || tank_volume=='' || tank_volume==' ' || tank_volume=='  ' || tank_volume=='  '){
		msg += "Please Enter The Correct Tank Volume!!!\n";
		flag = false;
	}
	if(status == "Select Status"){
		msg += "Please Select the Status!!!\n";
		flag = false;
	}
/*SB	if(customer_name == "Select Customer" || customer_name == null || customer_name=='' || customer_name==' ' || customer_name=='' || customer_name=='  '){
		msg += "Please Select the Customer Name!!!\n";
		flag = false;
	}*/
	if(trans_cd == "" || trans_cd == null || trans_cd=='' || trans_cd==' ' || trans_cd=='  ' || trans_cd=='  '){
		msg += "Please Select the Transporter Name!!!\n";
		flag = false;
	}
	
	if(flag)
	{
   			var btnid = (event.target.id)[0].toUpperCase()+(event.target.id).slice(1, 6);
   			if(btnid=="SaveBt"){
   				btnid = btnid.slice(0,4);
   			}
   			var a = confirm("Do You Want To "+btnid+" Available Truck Details?")
   			if(a)
   			{	
	           	$('#truckForm').submit();
   			}
	}
	else
	{
		alert("First Checks the Following Fileds !\n\n"+msg)
	}
}
	
function setVal(cust_id,reg_no,truck_typ,eff_dt,m3,tons,status,truck_id,btnflag,trans_cd,truck_cap,next_load_day){
	
//	document.forms[0].customer_name.value=cust_id;
	document.forms[0].truck_name.value=reg_no;
	document.forms[0].truck_type.value=truck_typ;
	document.forms[0].eff_date.value=eff_dt;
	document.forms[0].truck_volume3.value=m3;
	document.forms[0].truck_volumeTone.value=tons;
	document.forms[0].truck_Loadfactor.value=truck_cap;
	document.forms[0].truck_nextLoadDays.value=next_load_day;
	document.forms[0].status_flag.value=status;
	document.forms[0].tid.value=truck_id;
	document.forms[0].btnflag.value=btnflag;
	document.forms[0].trans_cd.value=trans_cd;
	document.forms[0].tnm.value=reg_no;
	
	
	var table = document.getElementById('tablexample');
	if(table){
		$('#saveBtn').hide('fast', function() {
			$('#updateBtn').show('fast', function() {
				//document.forms[0].customer_name.disabled = true;
				document.forms[0].truck_name.disabled = true;
				document.forms[0].trans_cd.disabled = true;
				document.forms[0].hid_cust_cd.value=cust_id;
				document.forms[0].hid_trans_cd.value=trans_cd;
				
					$('#updateBtn').click(function() {
						document.getElementById('modify').value = document.getElementById('updateBtn').value;
					});				
				});
			$('#deleteBtn').show('fast',function(){
			//	document.forms[0].customer_name.disabled = true;
				document.forms[0].truck_name.disabled = true;
				document.forms[0].trans_cd.disabled = true;
				document.forms[0].hid_trans_cd.value=trans_cd;
				document.forms[0].hid_cust_cd.value=cust_id;
					$('#deleteBtn').click(function() {
						document.getElementById('modify').value = document.getElementById('deleteBtn').value;
					});
				});
			});
	}
}	
</script>
<style>
#updownSort {
  width: 20px;
  height: 23px;
  background: url(../images/updowns.png) 0 0;
}
</style>
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
		
		"scrollY": "350px",
		"scrollCollapse": true
	});
	$('.dataTables_length').addClass('bs-select');
	
});


function validateData(){
	
	var truck_nm = document.forms[0].truck_name.value;
//	var customer_id = document.forms[0].customer_name.value;
	var trans_cd = document.forms[0].trans_cd.value;
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	//SB if(customer_id!='' && truck_nm!=''){
		
	if(truck_nm!=''){
		if(truck_nm.length == 11 || truck_nm.length == 10)
		{ 
			
		   var url="../master/frm_mst_transportation.jsp?truck_name="+truck_nm+"&truckFlg=Y&formId="+formId+
				   "&modCd="+modCd+"&subModUrl="+subModUrl+"&trans_cd="+trans_cd;
		   location.replace(url);	
	 	}
	}else {
		  var url="../master/frm_mst_transportation.jsp?truck_name="+truck_nm+"&formId="+formId+
		   "&modCd="+modCd+"&subModUrl="+subModUrl+"&trans_cd="+trans_cd;
  			location.replace(url);
	} 
}
function setFormat(obj){
		
	document.getElementById('truck_name').value = "";
	if(obj.id == 'rad1'){
		
		document.getElementById('format').value = "1";
		document.getElementById("truck_name").placeholder = "XX12-XX1234";
		document.getElementById("truck_name").maxLength = "11";
		
	}else if(obj.id == 'rad2'){
		
		document.getElementById('format').value  = "2"; 
		document.getElementById("truck_name").placeholder = "XX12-X1234";
		document.getElementById("truck_name").maxLength = "10";
		
	}
}
</script>


</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.truck_master.DataBean_truckMaster" id="truckMastr" scope="request"/>
<%
	String truckFlg = request.getParameter("truckFlg")==null?"":request.getParameter("truckFlg");
	String cust_id = request.getParameter("customer_name")==null?"":request.getParameter("customer_name");
	String truck_nm = request.getParameter("truck_name")==null?"":request.getParameter("truck_name");
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	String erro_msg = request.getParameter("erro_msg")==null?"":request.getParameter("erro_msg");
	String trans_cd = request.getParameter("trans_cd")==null?"":request.getParameter("trans_cd");
	
	String servFlag = "N";
	String buttons = "";
	
	truckMastr.setTruckFlg(truckFlg);
	truckMastr.setCust_id(cust_id);
	truckMastr.setTruck_nm(truck_nm);
	truckMastr.setTrans_cd(trans_cd);
	
	truckMastr.init();

	cust_id = truckMastr.getCust_id();
	truck_nm = truckMastr.getTruck_nm();
	String truckAvali = truckMastr.getTruckAvail();
	
	Vector trucks_name = truckMastr.getTruck_name();
	Vector trucks_type = truckMastr.getTruck_type();
	Vector effective_date = truckMastr.getEffective_date();
	Vector tank_volume_M3 = truckMastr.getTank_volume_M3();
	Vector tank_volume_Tone = truckMastr.getTank_volume_Ton();
	Vector status = truckMastr.getStatus();
	Vector customer_id = truckMastr.getCustomer_id();
	Vector customer_name = truckMastr.getCustomer_abbr();
	
	Vector truck_customer_id = truckMastr.getTruck_customer_id();
	Vector truck_cust_abbr = truckMastr.getTruck_cust_abbr();	
	Vector truck_id = truckMastr.getTruck_id();	
    servFlag = request.getParameter("flag")==null?"":request.getParameter("flag");
    buttons = request.getParameter("modifiers")==null?"":request.getParameter("modifiers");
    
    String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
    String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
    String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212
    
    Vector Vtrans_cd = truckMastr.getVtrans_cd();
    Vector Vtrans_abr = truckMastr.getVtrans_abr();
    trans_cd = truckMastr.getTrans_cd();
    Vector Vtruck_trans_abr = truckMastr.getVTruck_trans_abr();
    Vector Vtruck_trans_cd = truckMastr.getVTruck_trans_cd();
    Vector VTruck_Load_Cap = truckMastr.getVTruck_Load_Cap();
    Vector VTruck_Next_Load_Day = truckMastr.getVTruck_Next_Load_Day();
 %>


<div class="tab-content">
<div class="tab-pane active" id="truckmaster">
	<!-- Default box -->
<div class="box mb-0">
<form  method="post" action="../servlet/Frm_TruckMasterV2" id="truckForm">
<div class="box-header with-border">
<div class="row">
	<div class="col-lg-1 col-md-2 col-sm-6 col-xs-12 hidden-xs hidden-sm"></div>
</div><!-- row wnd -->
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

  <input type="hidden" name="tid">
   <input type="hidden" name="tnm">
  <input type="hidden" name="btnflag">	
  <input type="hidden" id="modify" name="modifiers" value="">		
   
<div class="box-body table-responsive no-padding">
	<div class="col-lg-1 col-md-2 col-sm-6 col-xs-12 hidden-xs hidden-sm"></div>
	<table  class="table table-striped table-bordered table-sm " cellspacing="0" width="100%">
		<thead>   
			<tr class="main-header">
				<th class="text-center" rowspan="2" colspan="1">Transporter Name</th>
<!-- 				<th class="text-center" rowspan="2" colspan="1"> Customer Name</th> -->
				<th class="text-center" rowspan="2" colspan="1"> Truck Reg. No.</th>
				<th class="text-center" rowspan="2" colspan="1"> Truck Type </th>
				<th class="text-center" rowspan="2" colspan="1"> Effective Date </th>
				<th class="text-center" colspan="4"> Truck Capacity</th>
				<th class="text-center" rowspan="2" colspan="1" > Status</th>
			</tr>
			<tr class="main-header">
				<th class="text-center">&#13221;</th>
				<th class="text-center">MT</th>
				<th class="text-center">Load(%)</th>
				<th class="text-center">Next Load Days</th>
			</tr>
		</thead>
		
		<tbody>
			<tr>
				<td>
					<select class="form-control"  id="trans_cd" name="trans_cd" onchange="validateData();">
						<option  value="" selected="selected">-All-</option>
						<%for(int i=0;i<Vtrans_cd.size();i++){ %>
							<option  value="<%=Vtrans_cd.elementAt(i) %>"><%= Vtrans_abr.elementAt(i)%></option>
						<%}%>
					</select>
					<script type="text/javascript">
						document.forms[0].trans_cd.value='<%=trans_cd%>';
					</script>
					<input type="hidden" name="hid_cust_cd" value="0">
   					<input type="hidden" name="hid_trans_cd" value="">
   					<input type="hidden" name="customer_name" value="0">
				</td>
<!-- 				<td> -->
<!-- 					<select class="form-control"  id="custnm" name="customer_name" onchange="validateData();" > -->
<!-- 						<option  value="" selected="selected">-All-</option> -->
<%-- 						<%for(int i=0;i<customer_name.size();i++){ %> --%>
<%-- 							<option  value="<%=customer_id.elementAt(i) %>"><%= customer_name.elementAt(i)%></option> --%>
<%-- 						<%}%> --%>
<!-- 							<option value="100">Own (Delivery Based)</option> -->
<!-- 					</select> -->
<%-- 						 <%if(!cust_id.equals("")){%> --%>
<!-- 							<script> -->
<%-- 								document.forms[0].customer_name.value="<%=cust_id%>"; --%>
<!-- 							</script> -->
<%-- 						<%}%>  --%>
						
<!-- 				</td>	 -->
				<td>
					<input type='text' class="form-control invent" name="truck_name" id="truck_name" maxlength="11" size="12" onkeyup="regularExpression(this)" placeholder="XX12-XX1234"/><!-- validateData(); -->
			     	<%if(!truck_nm.equals("")){ 
							if(truckAvali.equals("false")){	%>
							<script>
								document.forms[0].truck_name.value="<%=truck_nm%>";
								
							</script>
							<%}else{%>
								<script>
									document.forms[0].truck_name.value = null;
								</script>
							<%}%>
						<%}%>
				</td>
				<td>
					<input type='text' class="form-control" name="truck_type" value="" size="12"/>
			     	<%if(!truck_nm.equals("")){ 
							if(truckAvali.equals("false")){	%>
							<script>
								document.forms[0].truck_name.value="<%=truck_nm%>";
								
							</script>
							<%}else{%>
								<script>
									document.forms[0].truck_name.value = null;
								</script>
							<%}%>
						<%}%>
				</td>
				<td>
					<div class='input-group date' id='datetimepicker1'>
						<input type='text' class="form-control"  name="eff_date"  maxlength="10" size="10" value="" onBlur="validateDate(this);"/>
						<span class="input-group-addon">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
				</td>
				<td rowspan="1" colspan="1"><input type='text'  class="form-control" size="5" id="m3" name="truck_volume3"  onkeyup="convertToTone()" /></td>
				<td rowspan="1" colspan="1"><input type='text' class="form-control" size="5" id="ton" name="truck_volumeTone"  onkeyup="convertToM3()" /></td>
				<td rowspan="1" colspan="1"><input type='text' class="form-control" size="5" id="Loadfactor" name="truck_Loadfactor"  onkeyup="convertToM3()" /></td>
				<td rowspan="1" colspan="1"><input type='text' class="form-control" size="5" id="nextLoadDays" name="truck_nextLoadDays"  onkeyup="convertToM3()" /></td>
				<td>
					 <select class="form-control" name="status_flag">
						<option value="Select Status">Select</option>
						<option value="Y">Active</option>
						<option value="N">Inactive</option>
					</select>
				</td>
			</tr>
			
			<tr><td></td>
				<td colspan="8">
					<span title="XX12-XX1234"><input type="radio" name="rad" id="rad1" checked="checked" onclick="setFormat(this);"> Format 1</span> &nbsp;&nbsp;&nbsp;
					<span title="XX12-X1234"><input type="radio" name="rad" id="rad2" onclick="setFormat(this);"> Format 2</span>
				</td> 
			</tr>
			
		</tbody>
	</table>
</div>
   
<div class="col-lg-1 col-md-2 col-sm-6 col-xs-12 hidden-xs hidden-sm"></div> 
<%if(truckAvali.equals("true")){%>
<p id="error" align="left" style="font-size:18px;color:red;">Selected truck already available! please select different one!...</p>
<%}%>
<p id="msg" align="left" style="font-size:18px;color:red;"></p>
</div> <!-- box-header with-border -->

<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
<input type="hidden" name="format" id="format" value="1">
<input type="hidden" name="write_permission" value="<!--%=write_permission%-->">
<input type="hidden" name="delete_permission" value="<!--%=delete_permission%-->">
<input type="hidden" name="print_permission" value="<!--%=print_permission%-->">
<input type="hidden" name="approve_permission" value="<!--%=approve_permission%-->">
<input type="hidden" name="audit_permission" value="<!--%=audit_permission%-->">
<input type="hidden" name="index_count" value="<!--%=index%-->">
<input type="hidden" name="option" value="submitTruckLoad">

</form>
<%-- <%if(daily_Buyer_Nom_Plant_Seq_Abbr.size()>0){ %> --%>
	<div class="box-footer">
	<div class="row">
	<div class="col-sm-12 text-right">
		<button type="button" class="btn btn-warning" name="reSetPage" value="Reset" onClick="resetPage();"> Reset </button>
		
		<button onclick="doSubmit(event);" type="button" class="btn btn-success" id="saveBtn" name="save" value="Submit" >Submit</button>
		
		<button onclick="doSubmit(event);" type="button" class="btn btn-success" id="updateBtn" value="update" >Update</button>
		
<!-- 		<button onclick="doSubmit(event);" type="button" class="btn btn-danger" id="deleteBtn" value="delete" >Delete</button> -->

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
<!-- 				<th>Customer Name <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th> -->
				<th>Truck Reg. No. <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
				<th>Truck Type <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
				<th>Effective Date <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
				<th>Truck Volume M3 <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
				<th>Truck Volume MT <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
				<th>Truck Load % <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
				<th>Next Load Days<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
				<th>Status <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
			</tr>
		</thead>
		 <tbody>
			<%	try { %>
				<%	for(int i=0;i<trucks_name.size();i++){ %>
					 	<tr >						
							<td  onclick="setVal('<%=truck_customer_id.elementAt(i)%>','<%=trucks_name.elementAt(i)%>','<%=trucks_type.elementAt(i) %>','<%=effective_date.elementAt(i) %>',
					 			'<%=tank_volume_M3.elementAt(i) %>','<%=tank_volume_Tone.elementAt(i) %>','<%=status.elementAt(i) %>','<%=truck_id.elementAt(i)%>','Y','<%=Vtruck_trans_cd.elementAt(i)%>','<%=VTruck_Load_Cap.elementAt(i) %>','<%=VTruck_Next_Load_Day.elementAt(i) %>');"
					 			>
					 			<i class="fa fa-pencil-square-o invent" style="font-size:20px;color:red" ></i>
					 		</td>																																			
					 		<td><%=Vtruck_trans_abr.elementAt(i) %></td>
<%-- 					 		<td align="center"><%=truck_cust_abbr.elementAt(i) %></td> --%>
					 		<td align="center"><%=trucks_name.elementAt(i) %></td>
					 		<td align="center" ><%=trucks_type.elementAt(i) %></td>
					 		<td align="center"><%=effective_date.elementAt(i) %></td>
					 		<td align="center"><%=tank_volume_M3.elementAt(i) %></td>
					 		<td align="center"><%=tank_volume_Tone.elementAt(i) %></td>
					 		<td align="center"><%=VTruck_Load_Cap.elementAt(i) %></td>
					 		<td align="center"><%=VTruck_Next_Load_Day.elementAt(i) %></td>
					 		<td align="center"><%=status.elementAt(i) %></td>
					 	</tr>
						
					<%	}  %>
			 <%	 if(trucks_name.size() == 0){%>
				 <tr><th class="text-center" colspan="9" style="color: red;"><div class="mt-5"> No record found for the selected Transporter & Customer!!</div> </th>
			  <% } 		
			 }catch(Exception e){	
				e.printStackTrace();
			   } %>
		</tbody> 
</table>
</div>
<!-- /.box-body -->
</div>
<!-- /.box -->
</div>
</div>

<!-- /.tab-content -->

<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script >
//endDate: "today",
$(function () {
$('#datetimepicker1').datepicker({
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
todayHighlight: true,
orientation: "bottom auto"
});
});

function regularExpression(obj){  //SUJIT06MARCH2020

	var format = document.getElementById('format').value;

	var dexp = /^[0-9]+$/;
// 	var sexp = /^[a-zA-Z]+$/;
	var sexp = /^[a-zA-Z-]+$/;
	var firstCh = obj.value.slice(0,2);
	var secondNum = obj.value.slice(2,4);
	var lastNum = "";
	var fifthCh = "";
	var alert_msg = "";
	if(format == '1'){
		fifthCh = obj.value.slice(5,7);
		lastNum = obj.value.slice(7,11);
		alert_msg = "Please Enter in Required Formate(XX12-XX1234)";
	}else if(format == '2'){
		fifthCh = obj.value.slice(5,6);
		lastNum = obj.value.slice(6,10);
		alert_msg = "Please Enter in Required Formate(XX12-X1234)";
	}
	
// 	var space = /^\s+$/;
// 	alert(obj.value)
	$('#truck_name').val($('#truck_name').val().toUpperCase());
	//$('#truck_name').val($('#truck_name').val().replace(/ +?/g, '')); // remove Space on keyup
	
	if(firstCh.match(sexp)){	
		if(secondNum.match(dexp)){
			 if(obj.value.length == 4){
				 obj.value = [obj.value.slice(0, 4),"-", obj.value.slice(4)].join('');
			 }
// 			 alert(fifthCh)
			if(!fifthCh.match(sexp)){
				
// 				alert(fifthCh.length)
				 if(fifthCh.length==1 || fifthCh.length==2){
					 alert(alert_msg)
					 obj.value='';
				 }
			 } 
			  
			 if(!lastNum.match(dexp)){
				if(lastNum.length==parseFloat(1) || lastNum.length==parseFloat(2) || lastNum.length==parseFloat(3) || lastNum.length==parseFloat(4)){
					alert(alert_msg)
					obj.value='';
				}
			}else{
				
				/* if(lastNum.length==parseFloat(2) || lastNum.length==parseFloat(3) || lastNum.length==parseFloat(4))
				{
					if(lastNum.slice(0,2) == parseFloat(0) || lastNum.slice(0,3) == parseFloat(0) || lastNum.slice(0,4) == parseFloat(0))
					{
						alert("Please Enter Valid Number Or It can't be "+lastNum)	
						obj.value = "";
					}
				} */
			}
		}
		else  
		{
		   if(secondNum.length==1 || secondNum.length==2){
			   alert(alert_msg)
			   obj.value='';
			}
		}
	}else{
		 alert(alert_msg)
		 obj.value='';
	}
}

function convertToTone() {
	  var x = document.getElementById("m3");
	  var y = document.getElementById("ton");
	  y.value = round((x.value*23.9) / 51.5,2); // In Ton 
}

function convertToM3() {
	  var x = document.getElementById("m3");
	  var y = document.getElementById("ton");
	  x.value = Math.round((y.value*51.5) / 23.9); // In M3
}


function resetPage(){
	swal({
		title: "Are you Sure you want to Reset?",
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
	            	//	document.forms[0].customer_name.disabled = false;
	            		document.forms[0].truck_name.disabled = false;
	            		document.forms[0].truck_name.value = " ";
	            		document.forms[0].truck_type.value = " ";
	            		document.forms[0].eff_date.value = " ";
	            		document.forms[0].truck_volume3.value = " ";
	            		document.forms[0].truck_volumeTone.value = " ";
	            		document.forms[0].truck_Loadfactor.value = " ";
	            		document.forms[0].truck_nextLoadDays.value = " ";
	            		window.onload = $('#updateBtn').hide( function() {});
	            		window.onload = $('#deleteBtn').hide( function() {});
	            		window.onload = $('#saveBtn').show( function() {});
	            } 
	    });
}

window.onload = $('#updateBtn').hide( function() {});
window.onload = $('#deleteBtn').hide( function() {});


// $("#invent").change(function() {
$('.invent').change(function () {
// alert('in')
    //get the selected value
     var selectedValue = this.value;
//     alert(selectedValue)
    //make the ajax call
     $.post("../servlet/Frm_TruckMasterV2?btnflag=checkTruckStatus&truck_nm="+selectedValue,function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
// 		alert(responseText)
		var trans_cd = document.getElementById('trans_cd').value;
//      alert(responseText.length)
     	if(responseText!='' && responseText.length < 10 ){
			if(trans_cd!=''){
// 				alert(responseText+"---"+trans_cd)
				if(responseText!=trans_cd){
	
					document.getElementById('truck_name').value="";
					alert('Please De-Activate Selected Truck First!!');	
				}else{
					document.getElementById('truck_name').value="";
					alert('Selected Truck Already Activate !!');		
				}
			}else{
				
				document.getElementById('truck_name').value="";
				alert('Please Select Transporter First!!')
			}
     	}		 
    }); 
});
</script> 
</body>
</html>