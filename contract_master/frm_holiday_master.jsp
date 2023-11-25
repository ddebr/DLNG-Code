<%@ page buffer="128kb"%>
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

<title>DLNG Holiday Master</title>
<!-- CSS -->
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
.s-red{
color: red;
font-size: 18px;
}

input[readonly]{
/*   background-color:transparent; */
  border: 0;
  font-size: 1em;
  background-color: #DFE32D;
}
</style>

<script type="text/javascript">
function setType(selState){
// 	alert(selState)
	if(selState == "0"){
		
		document.getElementById('holi_typ').value='NH';
		document.getElementById('holiday_type').value='NH';
		
	}else{
		
		document.getElementById('holi_typ').value='SH';
		document.getElementById('holiday_type').value='SH';
	}
}

function modify(holiday_nm,holiday_dt,state_name,holiday_type,flag){
	
	var temp_state = state_name;
	
	document.getElementById('holi_nm').value = holiday_nm;
	document.getElementById('holi_dt').value = holiday_dt;
	document.getElementById('holi_dt').readOnly = true;
	document.getElementById("holi_dt").disabled = true;
// alert(document.getElementById("holi_dt").className)
	if(state_name == 'NA'){
		temp_state = "0";
	}
	
	document.getElementById('holi_state').value = temp_state;
	document.getElementById('holi_typ').value = holiday_type;
	document.getElementById('status_flag').value = flag;
	
	document.getElementById('holi_hid_dt').value =  document.getElementById('holi_dt').value;
	
	document.getElementById("save").innerHTML = "Modify";
	document.getElementById("save").value = "modify";
}

function doSubmit(event){
	var a = "";
	if(document.getElementById("save").value == "modify"){
		a = confirm('Are you sure want to Update Holiday?');
	}else{
		a = confirm('Are you sure want to Insert Holiday?');
	}
	
	if(a){
		 document.getElementById('holi_hid_dt').value =  document.getElementById('holi_dt').value;
		 return true;
	}else{
		 return false;
	}
}

function resetPage(){
	
	document.getElementById('holi_nm').value = "";
	document.getElementById('holi_dt').value = "";
	document.getElementById('holi_state').value = "0";
	document.getElementById('holi_typ').value = "NH";
	document.getElementById('status_flag').value = "Y";
	document.getElementById("holi_dt").disabled = false;
	document.getElementById('holi_hid_dt').value =  "";
	
	document.getElementById("save").innerHTML = "Submit";
	document.getElementById("save").value = "save";
}

function checkSpecialChars(id) {
    //Regex for Valid Characters i.e. Alphabets, Numbers and Space.
    var regex = /^[A-Za-z0-9 ]+$/

    //Validate TextBox value against the Regex.
    var isValid = regex.test(document.getElementById(id).value);
    if (!isValid) {
        alert("Special Characters Are Not Allowed!.");
        document.getElementById(id).value="";
    } else {
//         alert("Does not contain Special Characters.");
    }

    return isValid;
}
</script>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.contract_master.DataBean_Holiday_Master" id="hm" scope="page"/>
<%

utilBean.init();
String sysdate = utilBean.getGen_dt();

String msg = request.getParameter("msg")== null?"":request.getParameter("msg");	
String erro_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
// System.out.println("erro_msg****"+erro_msg);
String write_permission = (String)session.getAttribute("write_permission") ;
String delete_permission = (String)session.getAttribute("delete_permission") ;
String print_permission = (String)session.getAttribute("print_permission") ;
String approve_permission = (String)session.getAttribute("approve_permission") ;
String audit_permission = (String)session.getAttribute("audit_permission") ;
String user_cd=(String)session.getAttribute("user_cd"); 

String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200227
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200227
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200227


hm.setCallFlag("fetchHolidayDtl");

hm.init();

Vector state_nm = hm.getState_nm();
Vector state_cd = hm.getState_cd();

Vector holiday_nm = hm.getHoliday_nm();
Vector holiday_type = hm.getHoliday_type(); 
Vector holiday_dt = hm.getHoliday_dt();
Vector flag = hm.getFlag();
Vector ent_dt = hm.getEnt_dt(); 
Vector state_name = hm.getState_name();
Vector user_nm = hm.getUser_nm();
Vector state_code = hm.getState_code();

%>

<body>
	<div class="tab-content">
		<div class="tab-pane active" >
			<div class="box mb-0">
				<form method="post" action="../servlet/Frm_Holiday_Master?option=Insert_Holiday" >
				
					<input type="hidden" name="modCd" value="<%=modCd%>">
					<input type="hidden" name="formId" value="<%=formId%>">
					<input type="hidden" name="subModUrl" value="<%=subModUrl%>">	
					<input type="hidden" name="user_cd" value="<%=user_cd%>">
					<input type="hidden" name="holiday_type" value="NH" id="holiday_type">
					<input type="hidden" name="holi_hid_dt" value="" id="holi_hid_dt">
					<input type="hidden" name="sysdate" value="<%=sysdate %>" >
					
					<% if(!msg.equals("")) {%>
					<div class="box-body table-responsive no-padding">
						<table class="table  table-bordered">
							<thead>   
								<tr>
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
								<tr>
									<th class="text-center" colspan="2" style="color: red;"> <%=erro_msg %></th>
								</tr>
							</thead>
						</table>
					</div>
				<%} %>
				
					<div class="box-header with-border" style="background-color:#ffe57f">
						<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="form-group">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
									<label>Holiday Name</label>
									<div class="form-group">
										<input type="text" class="form-control" name="holi_nm" id="holi_nm" required="required" onkeyup="checkSpecialChars(this.id);"> 
									</div>	
								</div>
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
									<label>Holiday Date</label>
									<div class="form-group ">
										<div class='input-group date'>
											<input type="text" value="" placeholder="dd/mm/yyyy"  class="form-control datepick" id="holi_dt" name="holi_dt"  required="required" autocomplete="off">
											<span class="input-group-addon" >
												<i class="fa fa-calendar"></i>
											</span>
										</div>
									</div>	
								</div>
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
									<label>Holiday State</label>
									<div class="form-group">
										<select class="form-control" name="holi_state" id="holi_state" onchange="setType(this.value);">
											<option value="0">--All--</option>
											<%for (int i = 0 ; i < state_cd.size() ; i++) {%>
												<option value="<%=state_cd.elementAt(i)%>"><%=state_nm.elementAt(i) %></option>
											<%} %>
										</select> 
									</div>	
								</div>
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
									<label>Holiday Type</label>
									<div class="form-group">
										<select class="form-control" name="holi_typ" id="holi_typ" required="required" disabled="disabled">
											<option value="NH" selected="selected">National Holiday</option>
											<option value="SH" >State Holiday</option>
										</select> 
									</div>	
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
									<label>Status</label>
									<div class="form-group">
										<select class="form-control" name="status_flag" id="status_flag" required="required">
											<option value="Y" selected="selected">Active</option>
											<option value="N">Inactive</option>
										</select> 
									</div>	
								</div>
								
								<div class="col-lg-1 col-md-1 col-sm-1 col-xs-12">
									<label>&nbsp;</label>
									<div class="form-group">
											<button type="button" class="btn btn-warning" name="reSetPage" value="Reset" onclick="resetPage();">Reset</button>
									</div>
								</div>
								
								
								<div class="col-lg-1 col-md-1 col-sm-1 col-xs-12">
									<label>&nbsp;</label>
									<div class="form-group">
										<button type="submit" class="btn btn-success" name="save" id="save" value="save" onClick="return  doSubmit(event);">Submit</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="box-body table-responsive no-padding" style="height: 300px;overflow: scroll;">
						<table id="example" class="table table-bordered">
							<thead>
								<tr><td colspan="9" align="center"> <font color="black"> <b style="font-size: 18px;">
								 		Holiday List
								 </b> </font></td></tr>   
								<tr class="info">
									<th class="text-center">Modify</th>
									<th class="text-center" >Sr No</th>
									<th class="text-center" >Holiday Name</th>
									<th class="text-center" >Holiday Date </th>
									<th class="text-center" >Holiday State</th>
									<th class="text-center" >Holiday Type</th>
									<th class="text-center" >Status</th>
									<th class="text-center" >Entry Date</th>
									<th class="text-center" >Enter By</th>
								</tr>
							</thead>
							<tbody>
							<%for(int i = 0 ; i < holiday_dt.size(); i++) {%>
								<tr class="text-center" >
									<td >
									<i class="fa fa-pencil-square-o" style="font-size:20px;color:blue" onclick="modify('<%=holiday_nm.elementAt(i) %>','<%=holiday_dt.elementAt(i) %>','<%=state_code.elementAt(i) %>','<%=holiday_type.elementAt(i) %>','<%=flag.elementAt(i) %>');"> Modify</i>
<%-- 										<a href="" id="modify<%=i %>" onclick="modify('<%=holiday_nm.elementAt(i) %>','<%=holiday_dt.elementAt(i) %>','<%=state_code.elementAt(i) %>','<%=holiday_type.elementAt(i) %>','<%=flag.elementAt(i) %>');"><u>Modify</u></a> --%>
									</td>
									<td><%=i+1 %></td>
									<td class="text-left"><%=holiday_nm.elementAt(i) %></td>
									<td><%=holiday_dt.elementAt(i) %></td>
									<td><%=state_name.elementAt(i) %></td>
									<td>
									<%if(holiday_type.elementAt(i).equals("NH")){%>
										National Holiday
									<%}else{ %>
										State Holiday
									<%} %>
									</td>
									<td><%=flag.elementAt(i) %></td>
									<td><%=ent_dt.elementAt(i) %></td>
									<td><%=user_nm.elementAt(i) %></td>
								</tr>
								<script>
								var j = <%=i%>;
								document.getElementById("modify"+j).addEventListener("click", function(event){
									  event.preventDefault()
									});
								</script>
							<%} %>
							
							<%if(holiday_dt.size() == 0) {%>
								<tr class="text-center" >
									<td colspan="9"><font color="red">Holiday List is not available.. please make fresh entry!!</font></td>
								</tr>	
							<%} %>
							</tbody>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
		
<script type="text/javascript">
$('.datepick').each(function() {
	$(this).datepicker({
		changeMonth : true,
		changeYear : true,
		format : "dd/mm/yyyy",
		language : "en",
		autoclose : true,
		todayHighlight : true,
		orientation : "bottom auto"
// 		startDate : "tommorow"
	});
});


</script>	
</body>