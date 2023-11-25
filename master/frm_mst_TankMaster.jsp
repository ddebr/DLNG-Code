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
<!-- rtk -->

<script type="text/javascript">
function doSubmit(event){
	var flag = true;
	var msg = "";
	var tank_nm = document.forms[0].tank_name.value;
	var tank_typ = document.forms[0].tank_type.value;
	var effect_date = document.forms[0].eff_date.value;
	var tank_vol = document.forms[0].tank_volume.value;
	var tank_vol_ava = document.forms[0].tank_volume_avail.value;
	var status = document.forms[0].status_flag.value;
	var tank_id = document.forms[0].tank_id.value;
	
	/* alert("tank_nm.."+tank_nm);
	alert("tank_typ.."+tank_typ);
	alert("effect_date.."+effect_date);
	alert("tank_vol.."+tank_vol);
	alert("tank_vol_ava.."+tank_vol_ava);
	alert("status.."+status); */
	
	if(tank_nm==null || tank_nm=='' || tank_nm==' ' || tank_nm=='  ' || tank_nm=='  '){
		msg += "Please Enter Proper Tank Name!!!\n";
		flag = false;
	}
	if(tank_typ==null || tank_typ=='' || tank_typ==' ' || tank_typ=='  ' || tank_typ=='  '){
		msg += "Please Enter Proper Tank Type!!!\n";
		flag = false;
	}
	if(effect_date==null || effect_date=='' || effect_date==' ' || effect_date=='  ' || effect_date=='  '){
		msg += "Please Enter Proper Effective DATE!!!\n";
		flag = false;
	}
	if(tank_vol==null || tank_vol=='' || tank_vol==' ' || tank_vol=='  ' || tank_vol=='  '){
		msg += "Please Enter Proper Tank Volume!!!\n";
		flag = false;
	}
	if(tank_vol_ava==null || tank_vol_ava=='' || tank_vol_ava==' ' || tank_vol_ava=='  ' || tank_vol_ava=='  '){
		msg += "Please Enter Proper Tank Volume Available!!!\n";
		flag = false;
	}
	if(status == "Select Status"){
		msg += "Please Select the Status!!!\n";
		flag = false;
	}

	
	if(flag)
	{
   			var btnid = (event.target.id)[0].toUpperCase()+(event.target.id).slice(1, 6);
   			if(btnid=="SaveBt"){
   				btnid = btnid.slice(0,4);
   			}
   			var a = confirm("Do You Want To "+btnid+" Tank Details?")
   			if(a)
   			{	
	           	$('#tankForm').submit();
   			}
	}
	else
	{
		alert("First Checks the Following Fileds !\n\n"+msg)
	}
}
	

function setVal(tnk_id,tank_nm,tank_typ,eff_dt,tnk_vol,tank_vol_ava,status,btnflg){
	//alert(tnk_id+"---"+tank_nm+"---"+tank_typ+"---"+eff_dt+"---"+tnk_vol+"---"+tank_vol_ava+"---"+status+"but.."+btnflg);
	document.forms[0].tank_id.value=tnk_id;
	document.forms[0].tank_name.value=tank_nm;
	document.forms[0].tank_type.value=tank_typ;
	document.forms[0].eff_date.value=eff_dt;
	document.forms[0].tank_volume.value=tnk_vol;
	document.forms[0].tank_volume_avail.value=tank_vol_ava;
	document.forms[0].status_flag.value=status;
	document.forms[0].btnflag.value=btnflg;
	
	var table = document.getElementById('tablexample');
	if(table){
		$('#saveBtn').hide('fast', function() {
			$('#updateBtn').show('fast', function() {
					$('#updateBtn').click(function() {
						document.getElementById('modify').value = document.getElementById('updateBtn').value;
					});				
				});
			$('#deleteBtn').show('fast',function(){
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
		"bInfo":false
	});
	$('.dataTables_length').addClass('bs-select');
	
});

/* function validateData(){
	
	var truck_nm = document.forms[0].tank_name.value;
//	var customer_id = document.forms[0].customer_name.value;
	var trans_cd = document.forms[0].trans_cd.value;
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	//SB if(customer_id!='' && truck_nm!=''){
	if(truck_nm!=''){
		if(truck_nm.length == 11 || truck_nm.length == 10)
		{ 
			
		   var url="../master/frm_mst_TankMaster.jsp?truck_name="+truck_nm+"&truckFlg=Y&formId="+formId+
				   "&modCd="+modCd+"&subModUrl="+subModUrl+"&trans_cd="+trans_cd;
		   location.replace(url);	
	 	}
	}else {
		  var url="../master/frm_mst_TankMaster.jsp?truck_name="+truck_nm+"&formId="+formId+
		   "&modCd="+modCd+"&subModUrl="+subModUrl+"&trans_cd="+trans_cd;
  			location.replace(url);
	} 
}
	 */

</script>


</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.truck_master.DataBean_TankMaster" id="tankMastr" scope="page"/>
<%
String erro_msg = request.getParameter("erro_msg")==null?"":request.getParameter("erro_msg");
String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
String user_cd = (String)session.getAttribute("user_cd");
	
	String servFlag = "N";
	String buttons = "";
	
	tankMastr.init();

	
    servFlag = request.getParameter("flag")==null?"":request.getParameter("flag");
    buttons = request.getParameter("modifiers")==null?"":request.getParameter("modifiers");
    
    String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
    String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
    String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212
  
    
     Vector Vtank_id = tankMastr.getVtank_id();//RT20200818
	 Vector Vtank_nm = tankMastr.getVtank_nm();//RT20200818
	 Vector Vtank_type = tankMastr.getVtank_type();//RT20200818
	 Vector Vtank_vol = tankMastr.getVtank_vol();//RT20200818
	 Vector Vtank_eff_dt =  tankMastr.getVtank_eff_dt();//RT20200821
	 Vector Vtank_status =tankMastr.getVtank_status();//RT20200821
	 Vector Vtank_vol_avl = tankMastr.getVtank_vol_avl();//RT20200821
	 
	 
 %>


<div class="tab-content">
<div class="tab-pane active" id="tankmaster">
	<!-- Default box -->
<div class="box mb-0">
<form  method="post" action="../servlet/Frm_TankMaster" id="tankForm">
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


  <input type="hidden" name="btnflag">	
  <input type="hidden" id="modify" name="modifiers" value="">		
   
<div class="box-body table-responsive no-padding">
	<div class="col-lg-1 col-md-2 col-sm-6 col-xs-12 hidden-xs hidden-sm"></div>
	<table  class="table table-striped table-bordered table-sm " cellspacing="0" width="100%">
		<thead>   
			<tr class="main-header">
			
				<th class="text-center">Tank Name</th>
				<th class="text-center">Tank_type</th>
				<th class="text-center"> Effective Date </th>
				<th class="text-center"> Tank Volume</th>
				<th class="text-center"> Tank Volume Available</th>
				<th class="text-center" >Tank Status</th>
			</tr>
			
		</thead>
		
		<tbody>
			<tr>
				<td>
					<input type='text' class="form-control" name="tank_name" id="tank_name" maxlength="11" size="12" onkeyup="regularExpression(this)" onchange="//validateData();"/>
			     	<input type='hidden' class="form-control" name="tank_id" id="tank_id" maxlength="11" size="12" />
				</td>
				<td>
				<input type='text' class="form-control" name="tank_type" id="tank_type" maxlength="11" size="12" onkeyup="regularExpression(this)" onchange="//validateData();"/>	
				</td>
				<td>
					<div class='input-group date' id='datetimepicker1'>
						<input type='text' class="form-control"  name="eff_date"  maxlength="10" size="10" value="" onBlur="validateDate(this);"/>
						<span class="input-group-addon">
							<i class="fa fa-calendar"></i>
						</span>
					</div>
				</td>
				<td ><input type='text'  class="form-control" size="5" id="tank_volume" name="tank_volume"  onkeyup="convertToTone()" /></td>
				<td ><input type='text'  class="form-control" size="5" id="tank_volume_avail" name="tank_volume_avail"  onkeyup="convertToTone()" /></td>
				<td>
					 <select class="form-control" name="status_flag">
						<option value="Select Status">Select</option>
						<option value="Y">Active</option>
						<option value="N">Inactive</option>
					</select>
				</td>
			</tr>
			
		</tbody>
	</table>
</div>
   


<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">

<input type="hidden" name="write_permission" value="<!--%=write_permission%-->">
<input type="hidden" name="delete_permission" value="<!--%=delete_permission%-->">
<input type="hidden" name="print_permission" value="<!--%=print_permission%-->">
<input type="hidden" name="approve_permission" value="<!--%=approve_permission%-->">
<input type="hidden" name="audit_permission" value="<!--%=audit_permission%-->">
<input type="hidden" name="index_count" value="<!--%=index%-->">
<input type="hidden" name="option" value="submitTank">
<input type="hidden" name="user_cd" value="<%=user_cd%>">
</form>

	<div class="box-footer">
	<div class="row">
	<div class="col-sm-12 text-right">
		<button type="button" class="btn btn-warning" name="reSetPage" value="Reset" onClick="resetPage();"> Reset </button>
		
		<button onclick="doSubmit(event);" type="button" class="btn btn-success" id="saveBtn" name="save" value="Submit" >Submit</button>
		
		<button onclick="doSubmit(event);" type="button" class="btn btn-success" id="updateBtn" value="update" >Update</button>
		

	</div>
	</div>
	</div>

<div class="box-body table-responsive no-padding">

	<div class="col-lg-1 col-md-2 col-sm-6 col-xs-12 hidden-xs hidden-sm"></div>
	<table id="tablexample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
		<thead>   
			<tr class="info">
				<th></th>
				<th>Tank Name <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>

				<th>Tank Type <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
				<th>Effective Date <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
				<th>Tank Volume M3 <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
				<th>Tank Volume Available<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
				<th>Status <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
			</tr>
		</thead>
		 <tbody>
			<%	try { %>
				<%	for(int i=0;i<Vtank_id.size();i++){ %>
					 	<tr>						
							<td onclick="setVal('<%=Vtank_id.elementAt(i)%>','<%=Vtank_nm.elementAt(i)%>','<%=Vtank_type.elementAt(i) %>','<%=Vtank_eff_dt.elementAt(i) %>','<%=Vtank_vol.elementAt(i) %>','<%=Vtank_vol_avl.elementAt(i) %>'
					 			,'<%=Vtank_status.elementAt(i) %>','Y');"><i class="fa fa-pencil-square-o" style="font-size:20px;color:red"></i></td>																																			
					 			<td align="center"><%=Vtank_nm.elementAt(i) %></td>
					 		<td align="center" ><%=Vtank_type.elementAt(i) %></td>
					 		<td align="center"><%=Vtank_eff_dt.elementAt(i) %></td>
					 		<td align="center"><%=Vtank_vol.elementAt(i) %></td>
					 		<td align="center"><%=Vtank_vol_avl.elementAt(i) %></td>
					 		<td align="center"><%=Vtank_status.elementAt(i) %></td>
					 	</tr>
						
					<%	}  %>
				
			<% }catch(Exception e){	
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


function convertToTone() {
	  var x = document.getElementById("m3");
	  var y = document.getElementById("ton");
	  y.value = round((x.value*23.9) / 51.9,2); // In Ton 
}

function convertToM3() {
	  var x = document.getElementById("m3");
	  var y = document.getElementById("ton");
	  x.value = Math.round((y.value*51.9) / 23.9); // In M3
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
	            		document.forms[0].tank_id.value="";
	            		document.forms[0].tank_name.value = "";
	            		document.forms[0].tank_type.value = "";
	            		document.forms[0].eff_date.value = "";
	            		document.forms[0].tank_volume.value = "";
	            		document.forms[0].tank_volume_avail.value = "";
	            		
	            		
	            		window.onload = $('#updateBtn').hide( function() {});
	            		window.onload = $('#deleteBtn').hide( function() {});
	            		window.onload = $('#saveBtn').show( function() {});
	            } 
	    });
}

window.onload = $('#updateBtn').hide( function() {});
window.onload = $('#deleteBtn').hide( function() {});
</script> 
</body>
</html>