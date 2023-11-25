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
	var transporter_name = document.forms[0].transporter_name.value;
	var effect_date = document.forms[0].eff_date.value;
	var pan_no = document.forms[0].pan_no.value;
	var pan_issue_date = document.forms[0].pan_issue_date.value;
	var address = document.forms[0].address.value;
	var state = document.forms[0].state.value;
	var city = document.forms[0].city.value;
	var pincode = document.forms[0].pincode.value;
	var cst_tin_no = document.forms[0].cst_tin_no.value;
	var cst_tin_dt = document.forms[0].cst_tin_dt.value;
	var gst_tin_no = document.forms[0].gst_tin_no.value;
	var gst_tin_dt = document.forms[0].gst_tin_dt.value;
	var tan_no = document.forms[0].tan_no.value;
	var tan_dt = document.forms[0].tan_dt.value;
	var gstin_no = document.forms[0].gstin_no.value;
	var gstin_dt = document.forms[0].gstin_dt.value;
	var status = document.forms[0].status_flag.value;
	
	if(transporter_name==null || transporter_name=='' || transporter_name==' ' || transporter_name=='  ' || transporter_name=='  '){
		msg += "Please Enter The Correct Transporter Name !!!\n";
		flag = false;
	}
	if(effect_date==null || effect_date=='' || effect_date==' ' || effect_date=='  ' || effect_date=='  '){
		msg += "Please Enter The Correct Effective DATE!!!\n";
		flag = false;
	}
	if(status == "Select Status"){
		msg += "Please Select the Status!!!\n";
		flag = false;
	}
	/* var customer_name = document.forms[0].customer_name.value;
	if(customer_name == "Select Customer" || customer_name == null || customer_name=='' || customer_name==' ' || customer_name=='  ' || customer_name=='  '){
		msg += "Please Select the Customer Name!!!\n";
		flag = false;
	} 
	if(pan_no==null || pan_no=='' || pan_no==' ' || pan_no=='  ' || pan_no=='  '){
		msg += "Please Enter The Correct Pan No.!!!\n";
		flag = false;
	}
	if(pan_issue_date==null || pan_issue_date=='' || pan_issue_date==' ' || pan_issue_date=='  ' || pan_issue_date=='  '){
		msg += "Please Enter The Correct Pan Issue DATE!!!\n";
		flag = false;
	}
	if(address==null || address=='' || address==' ' || address=='  ' || address=='  '){
		msg += "Please Enter The Address.!!!\n";
		flag = false;
	}
	if(state==null || state=='' || state==' ' || state=='  ' || state=='  '){
		msg += "Please Enter The state!!!\n";
		flag = false;
	}	
	if(city==null || city=='' || city==' ' || city=='  ' || city=='  '){
		msg += "Please Enter The city.!!!\n";
		flag = false;
	}
	if(pincode==null || pincode=='' || pincode==' ' || pincode=='  ' || pincode=='  ' || pincode=='0'){
		msg += "Please Enter The pincode!!!\n";
		flag = false;
	}
	
	if(cst_tin_no==null || cst_tin_no=='' || cst_tin_no==' ' || cst_tin_no=='  ' || cst_tin_no=='  '){
		msg += "Please Enter The CST_TIN_No.!!!\n";
		flag = false;
	}
	if(cst_tin_dt==null || cst_tin_dt=='' || cst_tin_dt==' ' || cst_tin_dt=='  ' || cst_tin_dt=='  '){
		msg += "Please Enter The CST_TIN_DATE !!!\n";
		flag = false;
	}	
	if(gst_tin_no==null || gst_tin_no=='' || gst_tin_no==' ' || gst_tin_no=='  ' || gst_tin_no=='  '){
		msg += "Please Enter The GST_TIN_No.!!!\n";
		flag = false;
	}
	if(gst_tin_dt==null || gst_tin_dt=='' || gst_tin_dt==' ' || gst_tin_dt=='  ' || gst_tin_dt=='  '){
		msg += "Please Enter The GST_TIN_DATE!!!\n";
		flag = false;
	}
	
	if(tan_no==null || tan_no=='' || tan_no==' ' || tan_no=='  ' || tan_no=='  '){
		msg += "Please Enter The TAN_No.!!!\n";
		flag = false;
	}
	if(tan_dt==null || tan_dt=='' || tan_dt==' ' || tan_dt=='  ' || tan_dt=='  '){
		msg += "Please Enter The TAN_DATE !!!\n";
		flag = false;
	}	
	if(gstin_no==null || gstin_no=='' || gstin_no==' ' || gstin_no=='  ' || gstin_no=='  '){
		msg += "Please Enter The GSTIN_No.!!!\n";
		flag = false;
	}
	if(gstin_dt==null || gstin_dt=='' || gstin_dt==' ' || gstin_dt=='  ' || gstin_dt=='  '){
		msg += "Please Enter The GSTIN_DATE!!!\n";
		flag = false;
	} */
	if(flag)
	{
   			var btnid = (event.target.id)[0].toUpperCase()+(event.target.id).slice(1, 6);
   			if(btnid=="SaveBt"){
   				btnid = btnid.slice(0,4);
   			}
   			var a = confirm("Do You Want To "+btnid+" Transportater Details?")
   			if(a)
   			{	
	           	$('#transporterForm').submit();
   			}
	}
	else
	{
		alert("First Checks the Following Fileds !\n\n"+msg)
	}
}
	
function setVal(transporter_name,eff_dt,pan_no,pan_issue_date,address,cst_tin_no,cst_tin_dt,gst_tin_no,gst_tin_dt,tan_no,tan_dt,gstin_no,gstin_dt,btnflag,trans_cd,status,statecode,statename){

	document.forms[0].transporter_name.value=transporter_name;
	document.forms[0].eff_date.value=eff_dt;
	document.forms[0].pan_no.value=pan_no;
	document.forms[0].pan_issue_date.value=pan_issue_date;
	document.forms[0].address.value=address;
	document.forms[0].state.value=statecode;
	document.forms[0].cst_tin_no.value=cst_tin_no;
	document.forms[0].cst_tin_dt.value=cst_tin_dt;
	document.forms[0].gst_tin_no.value=gst_tin_no;
	document.forms[0].gst_tin_dt.value=gst_tin_dt;
	document.forms[0].tan_no.value=tan_no;
	document.forms[0].tan_dt.value=tan_dt;
	document.forms[0].gstin_no.value=gstin_no;
	document.forms[0].gstin_dt.value=gstin_dt;
	document.forms[0].btnflag.value=btnflag;
	document.forms[0].status_flag.value=status;

	var table = document.getElementById('tablexample');
	if(table){
		$('#saveBtn').hide('fast', function() {
			$('#updateBtn').show('fast', function() {
				document.forms[0].transporter_name.disabled = true;
				document.forms[0].hid_trans_cd.value=trans_cd;
					$('#updateBtn').click(function() {
						document.getElementById('modify').value = document.getElementById('updateBtn').value;
					});				
				});
			$('#deleteBtn').show('fast',function(){
				document.forms[0].transporter_name.disabled = true;
				document.forms[0].hid_trans_cd.value=trans_cd;
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
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.truck_master.DataBean_transporterMaster" id="transporterMastr" scope="page"/>
<%
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	String erro_msg = request.getParameter("erro_msg")==null?"":request.getParameter("erro_msg");
	
	String servFlag = "N";
	String buttons = "";
	
	transporterMastr.init();

	Vector transporter_id = transporterMastr.getTransporter_id();
	Vector transporter_name = transporterMastr.getTransporter_name();
	Vector effective_date = transporterMastr.getEffective_date();
	Vector pan_no = transporterMastr.getPan_no();
	Vector pan_issue_date = transporterMastr.getPan_issue_date();
	Vector address = transporterMastr.getAddress();
	Vector cst_tin_no = transporterMastr.getCst_tin_no();
	Vector cst_tin_dt = transporterMastr.getCst_tin_dt();
	Vector gst_tin_no = transporterMastr.getGst_tin_no();
	Vector gst_tin_dt = transporterMastr.getGst_tin_dt();
	Vector tan_no = transporterMastr.getTan_no();
	Vector tan_dt = transporterMastr.getTan_dt();
	Vector gstin_no = transporterMastr.getGstin_no();
	Vector gstin_dt = transporterMastr.getGstin_dt();
	Vector status = transporterMastr.getStatus();
	Vector statecode = transporterMastr.getStatecode();//11MAY2020
	Vector statename = transporterMastr.getStatename();//11MAY2020
	
    servFlag = request.getParameter("flag")==null?"":request.getParameter("flag");
    buttons = request.getParameter("modifiers")==null?"":request.getParameter("modifiers");
    
    String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
    String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
    String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212
 %>


<div class="tab-content">
	<div class="tab-pane active" id="transportermaster"><!-- Default box -->
		<div class="box mb-0">
		<form  method="post" action="../servlet/Frm_TransporterMasterV2" id="transporterForm">
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
					<div class="col-lg-7 col-md-7 col-sm-7 col-xs-12">
						<div class="form-group">
							<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
								<input type="hidden" name="tid">
							    <input type="hidden" name="btnflag">	
							    <input type="hidden" id="modify" name="modifiers" value="">	
								<input type="hidden" name="hid_trans_cd" value="">
									
								<label>Transporter Name</label>
								<div class="form-group">
									<input type='text' class="form-control" name="transporter_name" id="transporter_name" maxlength="100" onkeyup="regularExpressionForString(this)"  />		
								</div>	
							</div>
							
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
								<label> Effective Date </label>
								<div class="form-group mb-0">
									<div class='input-group date' id='datetimepicker1'>
										<input type='text' class="form-control"  name="eff_date" value="" onBlur="validateDate(this);" autocomplete="off"/>
										<span class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</span>
									</div>
								</div>
							</div>
							
							<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
								<label>Status</label>
								<div class="form-group">
									<select class="form-control" name="status_flag">
										<option value="Select Status">Select Status</option>
										<option value="Y">Active</option>
										<option value="N">Inactive</option>
									</select> 
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
						<div class="form-group">
						
							<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
								<label>CST_TIN NO.</label>
								<div class="form-group">			
									<input type='text' class="form-control" name="cst_tin_no" id="cst_tin_no" maxlength="20" onkeyup="regularExpressionForNum(this);"/>
								</div>
							</div>
							
							<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
								<label>CST_TIN Date</label>
								<div class="form-group mb-0">
									<div class='input-group date' id='datetimepicker2'>
										<input type='text' class="form-control"  name="cst_tin_dt"  maxlength="20" value="" onBlur="validateDate(this);" autocomplete="off"/>
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
					<div class="col-lg-7 col-md-7 col-sm-7 col-xs-12">
						<div class="form-group">
							<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
								<label>Pan No.</label>
								<div class="form-group">
									<input type='text' class="form-control" name="pan_no" id="pan_no" maxlength="10" onkeyup="regularExpressionForNum(this);"/> 
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
								<label>Pan Issue Date</label>
								<div class='input-group date' id='datetimepicker3'>
									<input type='text' class="form-control" name="pan_issue_date" id="pan_issue_date" maxlength="100" autocomplete="off"/>
									<span class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</span>
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12"></div>
						</div>
					</div>
					
					<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
						<div class="form-group">
							<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
								<label>GST_TIN NO.</label>
								<div class="form-group">			
									<input type='text' class="form-control" name="gst_tin_no" id="gst_tin_no" maxlength="20" onkeyup="regularExpressionForNum(this);"/>
								</div>
							</div>
						<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
								<label>GST_TIN Date</label>
								<div class="form-group mb-0">
									<div class='input-group date' id='datetimepicker4'>
										<input type='text' class="form-control"  name="gst_tin_dt"  maxlength="10" value="" onBlur="validateDate(this);" autocomplete="off"/>
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
					<div class="col-lg-7 col-md-7 col-sm-7 col-xs-12">
						<div class="form-group">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<label>Address</label>
								<div class="form-group">
									<input type='text' class="form-control" name="address" id="address" maxlength="100"  onkeyup="regularExpressionForAddress(this);"/> 
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
						<div class="form-group">
							<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
								<label>TAN NO.</label>
								<div class="form-group">			
									<input type='text' class="form-control" name="tan_no" id="tan_no" maxlength="10" onkeyup="regularExpressionForNum(this);"/>
								</div>
							</div>
							<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
								<label>TAN Date</label>
								<div class="form-group mb-0">
									<div class='input-group date' id='datetimepicker5'>
										<input type='text' class="form-control"  name="tan_dt"  maxlength="10" value="" onBlur="validateDate(this);" autocomplete="off"/>
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
					<div class="col-lg-7 col-md-7 col-sm-7 col-xs-12">
						<div class="form-group">
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
								<label>State</label>
								<div class="form-group">
									<select class="form-control" name="state">
										<option value="Select State">Select State</option>
										<%for(int i=0;i<statecode.size();i++) {%>
											<option value="<%=statecode.elementAt(i)%>"><%=statename.elementAt(i)%></option>
										<%} %>
									</select> 		
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
								<label>City</label>
								<div class="form-group">
									<input type='text' class="form-control" name="city" id="city" maxlength="100" onkeyup="regularExpressionForString(this)"  />		
								</div>
							</div>
							<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
								<label>Pin code</label>
								<div class="form-group">
									<input type='text' class="form-control" name="pincode" id="pincode" maxlength="6" onkeyup="regularExpressionForNumPin(this);"/>		
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
						<div class="form-group">
							<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
								<label>GSTIN NO.</label>
								<div class="form-group">			
									<input type='text' class="form-control" name="gstin_no" id="gstin_no" maxlength="15" onkeyup="regularExpressionForNum(this);"/>
								</div>
							</div>
							
							<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
								<label>GSTIN Date</label>
								<div class="form-group mb-0">
									<div class='input-group date' id='datetimepicker6'>
										<input type='text' class="form-control"  name="gstin_dt"  maxlength="10" value="" onBlur="validateDate(this);" autocomplete="off"/>
										<span class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-1 col-md-2 col-sm-6 col-xs-12 hidden-xs hidden-sm"></div>
				</div>	
	
			</div><!-- box-header with-border -->
			<input type="hidden" name="modCd" value="<%=modCd%>">
			<input type="hidden" name="formId" value="<%=formId%>">
			<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
			<input type="hidden" name="write_permission" value="<!--%=write_permission%-->">
			<input type="hidden" name="delete_permission" value="<!--%=delete_permission%-->">
			<input type="hidden" name="print_permission" value="<!--%=print_permission%-->">
			<input type="hidden" name="approve_permission" value="<!--%=approve_permission%-->">
			<input type="hidden" name="audit_permission" value="<!--%=audit_permission%-->">
			<input type="hidden" name="index_count" value="<!--%=index%-->">
			<input type="hidden" name="option" value="submitTruckLoad">
		</form>
		
		<div class="box-footer">
			<div class="row">
				<div class="col-sm-12 text-right">
					<button type="button" class="btn btn-warning" name="reSetPage" value="Reset" onClick="resetPage();"> Reset </button>
					<button onclick="doSubmit(event);" type="button" class="btn btn-success" id="saveBtn" name="save" value="Submit" >Submit</button>
					<button onclick="doSubmit(event);" type="button" class="btn btn-success" id="updateBtn" value="update" >Update</button>
					<button onclick="doSubmit(event);" type="button" class="btn btn-danger" id="deleteBtn" value="delete" >Delete</button>
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
							<th>Pan No<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>Pan Date <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>CST_TIN_No <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>CST_TIN_Date<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>GST_TIN_No <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>GST_TIN_Date<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>TAN_No <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>TAN_Date<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>GSTIN_No <img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>GSTIN_Date<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>STATUS<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<!-- <th>ADDRESS<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>STATE<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>CITY<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th>
							<th>PINCODE<img id="updownSort" align="right"  width="1" height="1" src="../images/updowns.png"></th> -->
						</tr>
					</thead>
					 <tbody>
						<%	try { %>
							<%	for(int i=0;i<transporter_name.size();i++){ %>
								 	<tr>
										<td onclick="setVal('<%=transporter_name.elementAt(i)%>','<%=effective_date.elementAt(i)%>','<%=pan_no.elementAt(i)%>',
								 			'<%=pan_issue_date.elementAt(i) %>','<%=address.elementAt(i) %>','<%=cst_tin_no.elementAt(i) %>','<%=cst_tin_dt.elementAt(i)%>','<%=gst_tin_no.elementAt(i)%>',
								 			'<%=gst_tin_dt.elementAt(i)%>','<%=tan_no.elementAt(i)%>','<%=tan_dt.elementAt(i)%>','<%=gstin_no.elementAt(i)%>','<%=gstin_dt.elementAt(i)%>','Y','<%=transporter_id.elementAt(i)%>','<%=status.elementAt(i) %>','<%=statecode.elementAt(i) %>','<%=statename.elementAt(i) %>');"><i class="fa fa-pencil-square-o" style="font-size:20px;color:red"></i></td>																																			
								 		<td align="left"><%=transporter_name.elementAt(i) %></td>
								 		<td align="center"><%=effective_date.elementAt(i) %></td>
								 		<td align="center" ><%=pan_no.elementAt(i) %></td>
								 		<td align="center"><%=pan_issue_date.elementAt(i) %></td>
								 		<td align="center"><%=cst_tin_no.elementAt(i) %></td>
								 		<td align="center"><%=cst_tin_dt.elementAt(i) %></td>
								 		<td align="center"><%=gst_tin_no.elementAt(i) %></td>
								 		<td align="center"><%=gst_tin_dt.elementAt(i) %></td>
								 		<td align="center"><%=tan_no.elementAt(i) %></td>
								 		<td align="center"><%=tan_dt.elementAt(i) %></td>
								 		<td align="center"><%=gstin_no.elementAt(i) %></td>
								 		<td align="center"><%=gstin_dt.elementAt(i) %></td>
								 		<td align="center"><%=status.elementAt(i) %></td>
								 		<%-- <td align="center"><%=address.elementAt(i) %></td>
								 		<td align="center"><%=state.elementAt(i) %></td>
								 		<td align="center"><%=city.elementAt(i) %></td>
								 		<td align="center"><%=pincode.elementAt(i) %></td> --%>
								 	</tr>
									
								<%	}  %> 
						 <%	 if(transporter_name.size()==0){%>
							 <tr><th class="text-center" colspan="8" style="color: red;"><div class="mt-5"> Transporter Data Not Available!!</div> </th>
						  <% } 		
						 }catch(Exception e){	
							e.printStackTrace();
						   } %>
					</tbody> 
				</table>
			</div>
		
		
		
		
		
		</div>
	</div>
</div>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script>
$(function () {
	for(var k=0;k<7;k++) {
		var datepic = "#datetimepicker"+k;
		$(datepic).datepicker({
			changeMonth: true,
			changeYear: true,
			format: "dd/mm/yyyy",
			language: "en",
			autoclose: true,
			todayHighlight: true,
			orientation: "bottom auto"
		}); 
	}
});

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
	            		document.forms[0].transporter_name.disabled = false;
	            		document.forms[0].transporter_name.value = " ";
	            		document.forms[0].eff_date.value = " ";
	            		document.forms[0].pan_no.value = " ";
	            		document.forms[0].pan_issue_date.value = " ";
	            		document.forms[0].address.value = " ";
	            		document.forms[0].state.value = " ";
	            		document.forms[0].city.value = " ";
	            		document.forms[0].pincode.value = " ";
	            		document.forms[0].cst_tin_no.value = " ";
	            		document.forms[0].cst_tin_dt.value = " ";
	            		document.forms[0].gst_tin_no.value = " ";
	            		document.forms[0].gst_tin_dt.value = " ";
	            		document.forms[0].tan_no.value = " ";
	            		document.forms[0].tan_dt.value = " ";
	            		document.forms[0].gstin_no.value = " ";
	            		document.forms[0].gstin_dt.value = " ";
	            		window.onload = $('#updateBtn').hide( function() {});
	            		window.onload = $('#deleteBtn').hide( function() {});
	            		window.onload = $('#saveBtn').show( function() {});
	            } 
	    });
}

window.onload = $('#updateBtn').hide( function() {});
window.onload = $('#deleteBtn').hide( function() {});

function regularExpressionForString(obj){
	var sexp = /^[a-zA-Z_ ]+$/;
	if(!obj.value.match(sexp)){
		obj.value = "";
		alert("Please Enter only Characters")
	}
}
function regularExpressionForNum(obj){
	var exp = /^[0-9a-zA-Z]+$/;
	if(!obj.value.match(exp))
	{
		obj.value = "";
		alert("Please Enter only Numbers & Characters")
	}
}

function regularExpressionForNumPin(obj){
	var exp = /^[0-9]+$/;
	if(!obj.value.match(exp))
	{
		obj.value = "";
		alert("Please Enter only Numbers")
	}
}

function regularExpressionForAddress(obj){
	var rx = /^[#.0-9a-zA-Z\s,-]+$/;
	if(!obj.value.match(rx)){
		obj.value = "";
		alert("Please Enter only Characters,Numbers,-,.");
	}
}
</script> 
</body>
</html>