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

<title>DLNG Link Customer-Check Post </title>
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
<link rel="stylesheet" href="../responsive/toastr/toastr.min.css">
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

<%
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
String modUrl = session.getAttribute("modUrl")==null?"":session.getAttribute("modUrl").toString();//HA20200227



%>
<body>
	<div class="tab-content">
		<div class="tab-pane active" >
			<div class="box mb-0">
				<form method="post" id = "chkpost" >
				
					<input type="hidden" name="modCd" value="<%=modCd%>">
					<input type="hidden" name="formId" value="<%=formId%>">
					<input type="hidden" name="subModUrl" value="<%=subModUrl%>">	
					<input type="hidden" name="modUrl" value="<%=modUrl%>">	
					<input type="hidden" name="user_cd" value="<%=user_cd%>">
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
									<label> Effective Date</label>
									<div class="form-group">
										<div class='input-group date' id='datetimepicker1'>
											<input type='text' class="form-control" id="eff_dt" name="eff_dt" maxlength="10" value="" autocomplete="off" required="required"/> 
											<span class="input-group-addon">
												<i class="fa fa-calendar"></i>
											</span>
										</div>
									</div>
								</div>
								<!-- <div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
									<label>Source Location</label>
									<div class="form-group">
										<input type="text" class="form-control" name="name" id="name" required="required" onkeyup="checkSpecialChars(this.id);"> 
									</div>	
								</div> -->
								
								<div class="col-lg-3 col-md-2 col-sm-2 col-xs-12">
									<label>Customer</label>
									<div class="form-group">
										<select class="form-control" name="cust_id" id="cust_id" onchange="" required="required">
											<option value=""> -SELECT-</option>
										</select>  
									</div>
								</div>
								
								<div class="col-lg-3 col-md-2 col-sm-2 col-xs-12">
									<label>Checkpost</label>
									<div class="form-group">
										<select class="form-control" name="checkPost" id="checkPost" required="required">
											<option value=""> -SELECT-</option>
										</select>  
									</div>
								</div>
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
									<label>Status</label>
									<div class="form-group">
										<select class="form-control" name="status" id="status" required="required">
<!-- 											<option value=""> -SELECT-</option> -->
											<option value="Y"> LINK</option>
<!-- 											<option value="N"> UN-LINK</option> -->
										</select>  
									</div>
								</div>
								
								<div class="col-lg-1 col-md-1 col-sm-1 col-xs-12">
									<label>&nbsp;</label>
									<div class="form-group">
										<input type="submit" name="Submit" value="Submit" class="btn btn-success" > 
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-8">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover text-center">
								<thead>
									<tr>
										<th>SEQ</th>
										<th>EFFECTIVE DATE</th>
										<th>CHECKPOST NAME</th>
										<th>CURRENT STATUS </th>
										<th>EDIT STATUS</th>
									</tr>
								</thead>
								<tbody id="tbody_dtl">
								
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script src="../responsive/toastr/toastr.min.js"></script>
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(document).on("change", "#eff_dt", function() {
		var form = $(this);
		var mydata = $("form#chkpost").serialize();
		 $.ajax({
		        type : "POST",
		        url : "../Frm_CheckPost?option=fetchCust",
		        data: mydata,
		        cache : false,
		        success : function(responseJson) {
		        	
		        	var string1 = JSON.stringify(responseJson);
// 		        	alert(string1)
		        	var arr = JSON.parse(string1);
// 		        	alert(arr.custList)
		        	var $custSelect = $("#cust_id");
			    	$custSelect.find("option").remove(); 
			    	
			    	$.each(arr.custList, function(index, value) {
	            		if(index == 0){
	    	 				$("<option>").val("").text("-SELECT-").appendTo($custSelect);
	    	 			}
	    	 			$("<option>").val(value.mapping_id).text(value.cust_abbr+" "+value.cont_nm+"-"+value.cont_no+" "+value.plant_nm).appendTo($custSelect);
	            	});
			    	
			    	/* var $checkPostSelect = $("#checkPost");
			    	$checkPostSelect.find("option").remove();
			    	
			    	// for customer dropdown 
	            	$.each(arr.custList, function(index, value) {
	            		if(index == 0){
	    	 				$("<option>").val("").text("-SELECT-").appendTo($custSelect);
	    	 			}
	    	 			$("<option>").val(value.mapping_id).text(value.cust_abbr+" "+value.cont_nm+"-"+value.cont_no+" "+value.plant_nm).appendTo($custSelect);
	            	});
	            	
	            	// for checkpost dropdown 
	            	$.each(arr.checkpostList, function(index, value) {
	            		if(index == 0){
	    	 				$("<option>").val("").text("-SELECT-").appendTo($checkPostSelect);
	    	 			}
	    	 			$("<option>").val(value.chkpost_cd).text(value.chkpost_name).appendTo($checkPostSelect);
	            	}); */
		        }
		 });
	});
	
	$(document).on("change", "#cust_id", function() {
		var tbody_data = "";
		$("#tbody_dtl").empty();
		
		var form = $(this);
		var mydata = $("form#chkpost").serialize();
		 $.ajax({
		        type : "POST",
		        url : "../Frm_CheckPost?option=fetchCheckPost",
		        data: mydata,
		        cache : false,
		        success : function(responseJson) {
		        	
		        	var string1 = JSON.stringify(responseJson);
// 		        	alert(string1)
		        	var arr = JSON.parse(string1);
// 		        	alert(arr.viewChkPostJson)
		        	var $checkpostList = $("#checkPost");
			    	$checkpostList.find("option").remove(); 
			    	$.each(arr.checkPostJson, function(index, value) {
	            		if(index == 0){
	    	 				$("<option>").val("").text("-SELECT-").appendTo($checkpostList);
	    	 			}
	    	 			$("<option>").val(value.chkpost_cd).text(value.chkpost_name).appendTo($checkpostList);
	            	});
			    	var seq = 0;
			    	$.each(arr.viewChkPostJson, function(index, value) {
			    		seq++;
			    		var status = value.view_status;
			    		var status_desc = "", status_cls = "",btn_sts = "";
			    		if(status == 'Y'){
			    			status_desc = "LINKED";
			    			btn_sts = "UN-LINK";
			    			status_cls = "class='btn btn-danger btn-sm'";
			    		}else{
			    			status_desc = "UN-LINKED";
			    			btn_sts = "LINK";
			    			status_cls = "class='btn btn-success btn-sm'";
			    		}
			    		
			    		var map_id = value.view_mapping_id;
			    		tbody_data += '<tr>';
				 			tbody_data += '<td  colspan="1" align="center" style="font-size:14px;">'+seq+'</td>';
				 			tbody_data += '<td  colspan="1" align="center" style="font-size:14px;">'+value.view_eff_dt+'</td>';
				 			tbody_data += '<td  colspan="1" align="center" style="font-size:14px;">'+value.view_chkpost_name+'</td>';
				 			tbody_data += '<td  colspan="1" class="text-center" style="font-size:14px;font-weight: bold;color: green">'+status_desc+'</td>';
				 			tbody_data += '<td  colspan="1" align="center" style="font-size:14px;" >';
				 				tbody_data += '<a href="#"  onClick="modifySts('+index+');" '+status_cls+' ><span class="fa fa-edit">&nbsp;'+btn_sts+'</span> </a>';
				 				tbody_data += '<input type = "hidden" name="map_id"  id = "map_id'+index+'" value = "'+map_id+'">';
				 				tbody_data += '<input type = "hidden" name="chk_cd"  id = "chk_cd'+index+'" value = "'+value.view_chkpost_cd+'">';
				 				tbody_data += '<input type = "hidden" name="eff_dt"  id = "eff_dt'+index+'" value = "'+value.view_eff_dt+'">';
				 				tbody_data += '<input type = "hidden" name="status"  id = "status'+index+'" value = "'+value.view_status+'">';
				 				tbody_data += '<input type = "hidden" name="chk_nm"  id = "chk_nm'+index+'" value = "'+value.view_chkpost_name+'">';
				 			tbody_data += '</td>';
						tbody_data += '</tr>';
	            	});
			    	
			    	$('#tbody_dtl').append(tbody_data);
		        }
		 });
	});	
	
	$('#chkpost').submit(function(e){
		
		e.preventDefault(e); 
		var tbody_data = "";
		$("#tbody_dtl").empty();
		var form = $(this);
		var mydata = $("form#chkpost").serialize();
		 $.ajax({
		        type : "POST",
		        url : "../Frm_CheckPost?option=saveCheckPostDtl",
		        data: mydata,
		        cache : false,
		        success : function(responseJson) {
		        	
// 		        	var string1 = JSON.stringify(responseJson);
// 		        	alert(string1)
		        	var arr = JSON.parse(responseJson);
// 		        	alert(arr)
		        	var $checkpostList = $("#checkPost");
			    	$checkpostList.find("option").remove(); 
			    	$.each(arr.checkPostJson, function(index, value) {
	            		if(index == 0){
	    	 				$("<option>").val("").text("-SELECT-").appendTo($checkpostList);
	    	 			}
	    	 			$("<option>").val(value.chkpost_cd).text(value.chkpost_name).appendTo($checkpostList);
	            	});
			    	
			    	var seq = 0, msg = "";
			    	$.each(arr.viewChkPostJson, function(index, value) {
			    		seq++;
			    		var status = value.view_status;
			    		var status_desc = "", status_cls = "",btn_sts = "";
			    		if(status == 'Y'){
			    			status_desc = "LINKED";
			    			btn_sts = "UN-LINK";
			    			status_cls = "class='btn btn-danger btn-sm'";
			    		}else{
			    			status_desc = "UN-LINKED";
			    			btn_sts = "LINK";
			    			status_cls = "class='btn btn-success btn-sm'";
			    		}
			    		
			    		
			    		var map_id = value.view_mapping_id;
			    		tbody_data += '<tr>';
				 			tbody_data += '<td  colspan="1" align="center" style="font-size:14px;">'+seq+'</td>';
				 			tbody_data += '<td  colspan="1" align="center" style="font-size:14px;">'+value.view_eff_dt+'</td>';
				 			tbody_data += '<td  colspan="1" align="center" style="font-size:14px;">'+value.view_chkpost_name+'</td>';
				 			tbody_data += '<td  colspan="1" class="text-center" style="font-size:14px;font-weight: bold;color: green">'+status_desc+'</td>';
				 			tbody_data += '<td  colspan="1" align="center" style="font-size:14px;" >';
				 				tbody_data += '<a href="#"  onClick="modifySts('+index+');" '+status_cls+' ><span class="fa fa-edit">&nbsp;'+btn_sts+'</span> </a>';
				 				tbody_data += '<input type = "hidden" name="map_id"  id = "map_id'+index+'" value = "'+map_id+'">';
				 				tbody_data += '<input type = "hidden" name="chk_cd"  id = "chk_cd'+index+'" value = "'+value.view_chkpost_cd+'">';
				 				tbody_data += '<input type = "hidden" name="eff_dt"  id = "eff_dt'+index+'" value = "'+value.view_eff_dt+'">';
				 				tbody_data += '<input type = "hidden" name="status"  id = "status'+index+'" value = "'+value.view_status+'">';
				 				tbody_data += '<input type = "hidden" name="chk_nm"  id = "chk_nm'+index+'" value = "'+value.view_chkpost_name+'">';
				 			tbody_data += '</td>';
						tbody_data += '</tr>';
			    		
						if(index == 0){
			    			msg = value.msg;
			    		}
	            	});
			    	if(msg.includes("Success:")){
			   	 		toastr["success"](msg);
			   	 	}else if(msg.includes("Prevented:")){
			   	 		toastr["error"](msg);
			   	 	} 
			    	
			    	$('#tbody_dtl').append(tbody_data);
		        }
		 });
	});	
	
});	

// function modifySts(view_mapping_id,view_chkpost_cd,view_eff_dt,view_status,view_chkpost_name){
	function modifySts(ind){
		
		var map_id = document.getElementById('map_id'+ind).value;
		var chk_cd = document.getElementById('chk_cd'+ind).value;
		var eff_dt = document.getElementById('eff_dt'+ind).value;
		var status = document.getElementById('status'+ind).value;
		var chk_nm = document.getElementById('chk_nm'+ind).value;
// 		alert(map_id+"--"+chk_cd+"--"+eff_dt+"--"+status+"--"+chk_nm)
		var tbody_data = "";
		$("#tbody_dtl").empty();
		var form = $(this);
		var mydata = $("form#chkpost").serialize();
	 $.ajax({
	        type : "POST",
	        url : "../Frm_CheckPost?option=updateCheckPostDtl&cust_id="+map_id+"&view_chkpost_cd="+chk_cd+"&eff_dt="+eff_dt+"&view_status="+status+"&view_chkpost_name="+chk_nm,
	        data: mydata,
	        cache : false,
	        success : function(responseJson) {
// 	        	alert(responseJson)
	        	var arr = JSON.parse(responseJson);
		    	var seq = 0, msg = "";
		    	$.each(arr.viewChkPostJson, function(index, value) {
			    	seq++;
		    		var status = value.view_status;
		    		var status_desc = "", status_cls = "",btn_sts = "";
		    		if(status == 'Y'){
		    			status_desc = "LINKED";
		    			btn_sts = "UN-LINK";
		    			status_cls = "class='btn btn-danger btn-sm'";
		    		}else{
		    			status_desc = "UN-LINKED";
		    			btn_sts = "LINK";
		    			status_cls = "class='btn btn-success btn-sm'";
		    		}
		    		
		    		var map_id = value.view_mapping_id;
		    		tbody_data += '<tr>';
			 			tbody_data += '<td  colspan="1" align="center" style="font-size:14px;">'+seq+'</td>';
			 			tbody_data += '<td  colspan="1" align="center" style="font-size:14px;">'+value.view_eff_dt+'</td>';
			 			tbody_data += '<td  colspan="1" align="center" style="font-size:14px;">'+value.view_chkpost_name+'</td>';
			 			tbody_data += '<td  colspan="1" class="text-center" style="font-size:14px;font-weight: bold;color: green">'+status_desc+'</td>';
			 			tbody_data += '<td  colspan="1" align="center" style="font-size:14px;" >';
			 				tbody_data += '<a href="#"  onClick="modifySts('+index+');" '+status_cls+' ><span class="fa fa-edit">&nbsp;'+btn_sts+'</span> </a>';
			 				tbody_data += '<input type = "hidden" name="map_id"  id = "map_id'+index+'" value = "'+map_id+'">';
			 				tbody_data += '<input type = "hidden" name="chk_cd"  id = "chk_cd'+index+'" value = "'+value.view_chkpost_cd+'">';
			 				tbody_data += '<input type = "hidden" name="eff_dt"  id = "eff_dt'+index+'" value = "'+value.view_eff_dt+'">';
			 				tbody_data += '<input type = "hidden" name="status"  id = "status'+index+'" value = "'+value.view_status+'">';
			 				tbody_data += '<input type = "hidden" name="chk_nm"  id = "chk_nm'+index+'" value = "'+value.view_chkpost_name+'">';
			 			tbody_data += '</td>';
					tbody_data += '</tr>';
					if(index == 0){
		    			msg = value.msg;
		    		}
            	});
		    	
		    	var $checkpostList = $("#checkPost");
		    	$checkpostList.find("option").remove(); 
		    	$.each(arr.checkPostJson, function(index, value) {
            		if(index == 0){
    	 				$("<option>").val("").text("-SELECT-").appendTo($checkpostList);
    	 			}
    	 			$("<option>").val(value.chkpost_cd).text(value.chkpost_name).appendTo($checkpostList);
            	});
		    	
		    	if(msg.includes("Success:")){
		   	 		toastr["success"](msg);
		   	 	}else if(msg.includes("Prevented:")){
		   	 		toastr["error"](msg);
		   	 	} 
		    	
		    	$('#tbody_dtl').append(tbody_data);
	        }
	 	});
}

$(function() {
	$('#datetimepicker1').datepicker({
		changeMonth : true,
		changeYear : true,
		format : "dd/mm/yyyy",
		language : "en",
		autoclosze : true,
		orientation : "bottom auto",
		toggleActive:"1"
	});
});
</script>
</body>
</html>
