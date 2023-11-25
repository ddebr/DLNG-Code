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

<title>DLNG Transport Master</title>
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
<script>
//license number

/* function validateLicence(){
var strFilter = /^[0-9a-zA-Z]{4,9}$/;
var obj = document.getElementById("license");

if (!strFilter.test(obj.value)) {
    alert("Please enter valid 8-digit license number\r\n(Only digits)");
    obj.focus();
    obj.style.background = "#DFE32D";
    obj.value = "";
    return false;
}
} */

function validateLicence(val){
	var regexp = /^\S*$/; // a string consisting only of non-whitespaces
	var obj = document.getElementById("license");

	if (!regexp.test(val)) {
	    alert("Blank Space Not Allowed In This Field!!");
	    obj.focus();
	    obj.style.background = "#DFE32D";
	    obj.value = "";
	    return false;
	}else{
		obj.style.background = "";
	}
}

function validateDoB(sel_date,obj){
	
	var sysdate = document.forms[0].sysdate.value;
	
	var sel_dt = sel_date.split(/[/: ]/);
	var arrvl_dt = sysdate.split(/[/: ]/);
			
	var dt1 = new Date(sel_dt[2], sel_dt[1] - 1, sel_dt[0]);
	var dt2 = new Date(arrvl_dt[2], arrvl_dt[1] - 1, arrvl_dt[0]);
		
	if(dt2 < dt1){
		document.getElementById('dob').value='';
		alert('DoB date should not be less than Current date!!');
		
		obj.style.background = "#DFE32D";
	}else{
		obj.style.background = "";
	}
	
	if(document.getElementById('lic_from_dt').value!="" && document.getElementById('dob').value!=''){
// 		alert('in')
		var lic_from_dt = document.getElementById('lic_from_dt').value;	
		var dob = document.getElementById('dob').value;
		
		var sel_dt = lic_from_dt.split(/[/: ]/);
		var arrvl_dt = dob.split(/[/: ]/);
				
		var dt1 = new Date(sel_dt[2], sel_dt[1] - 1, sel_dt[0]);
		var dt2 = new Date(arrvl_dt[2], arrvl_dt[1] - 1, arrvl_dt[0]);
			
		if(dt2 > dt1){
			document.getElementById('dob').value='';
			alert('Please Enter Proper Combination of License From & DoB Date!!');
			obj.style.background = "#DFE32D";
		}else{
			obj.style.background = "";
		}
	}
	
}

function validateLicDt(obj){
	
	var lic_from_dt = document.getElementById('lic_from_dt').value;
	var lic_to_dt = document.getElementById('lic_to_dt').value;
	var sysdate = document.forms[0].sysdate.value;
	
	if(lic_from_dt!=''){
		
		var sel_dt = lic_from_dt.split(/[/: ]/);
		var arrvl_dt = sysdate.split(/[/: ]/);
				
		var dt1 = new Date(sel_dt[2], sel_dt[1] - 1, sel_dt[0]);
		var dt2 = new Date(arrvl_dt[2], arrvl_dt[1] - 1, arrvl_dt[0]);
			
		if(dt2 < dt1){
			document.getElementById('lic_from_dt').value='';
			alert('License From date should be less than Current date!!');
			obj.style.background = "#DFE32D";
		}else{
			obj.style.background = "";
		}
	}
	
	if(lic_to_dt!=''){
			
			var sel_dt = lic_to_dt.split(/[/: ]/);
			var arrvl_dt = sysdate.split(/[/: ]/);
					
			var dt1 = new Date(sel_dt[2], sel_dt[1] - 1, sel_dt[0]);
			var dt2 = new Date(arrvl_dt[2], arrvl_dt[1] - 1, arrvl_dt[0]);
				
			if(dt2 > dt1){
				document.getElementById('lic_to_dt').value='';
				alert('License To date should not be less than Current date!!');
				obj.style.background = "#DFE32D";
			}else{
				obj.style.background = "";
			}
		}
	
	if(document.getElementById('dob').value!=""){
// 		alert('in'+lic_to_dt)
		var dob = document.getElementById('dob').value;
		var sel_dt = lic_from_dt.split(/[/: ]/);
		var arrvl_dt = dob.split(/[/: ]/);
				
		var dt1 = new Date(sel_dt[2], sel_dt[1] - 1, sel_dt[0]);
		var dt2 = new Date(arrvl_dt[2], arrvl_dt[1] - 1, arrvl_dt[0]);
			
		if(dt2 > dt1){
			document.getElementById('lic_from_dt').value='';
			alert('Please Enter Proper Combination of From & DoB Date!!');
			obj.style.background = "#DFE32D";
		}else{
			obj.style.background = "";
		}
	}
	
	if(document.getElementById('lic_from_dt').value!="" && document.getElementById('dob').value!=''){
// 		alert('in')
		var lic_from_dt = document.getElementById('lic_from_dt').value;	
		var dob = document.getElementById('dob').value;
		
		var sel_dt = lic_from_dt.split(/[/: ]/);
		var arrvl_dt = dob.split(/[/: ]/);
				
		var dt1 = new Date(sel_dt[2], sel_dt[1] - 1, sel_dt[0]);
		var dt2 = new Date(arrvl_dt[2], arrvl_dt[1] - 1, arrvl_dt[0]);
			
		if(dt2 > dt1){
			document.getElementById('dob').value='';
			alert('Please Enter Proper Combination of License From & DoB Date!!');
			obj.style.background = "#DFE32D";
		}else{
			obj.style.background = "";
		}
	}
}

function checkNum(val){
	
	var regexp = /^(0|[1-9][0-9]*)$/; // a string consisting only of non-whitespaces
	var obj = document.getElementById("mob_no");

	if (!regexp.test(val)) {
	    alert("Allowed Only Numeric Values!!");
	    obj.focus();
	    obj.style.background = "#DFE32D";
	    obj.value = "";
	    return false;
	}else{
		obj.style.background = "";
	}
}

var newWindow1;
function openAllDriver(){
	
	var trans_cd = document.forms[0].trans_cd.value;
	
	if(trans_cd!=''){
		var url = "../transportation/frm_driver_list.jsp?trans_cd="+trans_cd;
		if(!newWindow1 || newWindow1.closed)
		{
			newWindow1 = window.open(url,"popup","top=30,left=30,toolbars=no,maximize=yes,width=400,height=400,resize=no,location=no,directories=no,scrollbars=yes");
		}
		else
		{
			newWindow1.close();
			newWindow1 = window.open(url,"popup","top=30,left=30,toolbars=no,maximize=yes,width=400,height=400,resize=no,location=no,directories=no,scrollbars=yes");
		}	
	}else{
		alert('Please Select Transporter First!!')
	}
	
	
}

function parantWin1(license_no,trans_cd){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	var url="../master/frm_mst_transportation.jsp?license_no="+license_no+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&trans_cd="+trans_cd;
	location.replace(url);

}

function reset_val(){
// 	alert('in')
	document.getElementById('subBtn').innerHTML="Submit";
	document.getElementById('subBtn').className  = "btn btn-success";
	document.getElementById('subBtn').value="save";
	document.getElementById('submitType').value="save";
	
	document.getElementById('driver_nm').value="";
	document.getElementById('dob').value="";
	document.getElementById('mob_no').value="";
	document.getElementById('address').innerHTML="";
	document.getElementById('eff_dt').value="";
	
	document.getElementById('license').value="";
	document.getElementById('license').readOnly=false;
	
	document.getElementById('lic_from_dt').value="";
	document.getElementById('lic_to_dt').value="";
	document.getElementById('driver_stcd').value="";
	
	document.getElementById('trans_cd').value="";
	document.getElementById('driver_stcd').value="";
	document.getElementById('lic_typ').value="";
}
</script>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.transporter.DataBean_transporter_Veh_Driver" id="transmst" scope="page"/> 
<jsp:useBean class="com.seipl.hazira.dlng.truck_master.DataBean_truckMaster" id="truckMastr" scope="page"/> 
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
	String license_no = request.getParameter("license_no")== null?"":request.getParameter("license_no");
	String trans_cd = request.getParameter("trans_cd")== null?"":request.getParameter("trans_cd");
	
	transmst.setLic_no(license_no);
	transmst.setTrans_cd(trans_cd);
	
	transmst.init();
	Vector statecode = transmst.getStatecode();
	Vector statename = transmst.getStatename();
	
	truckMastr.init();
	Vector Vtrans_cd = truckMastr.getVtrans_cd();
	Vector Vtrans_nam = truckMastr.getVtrans_name();
	
	String address=transmst.getAddress()  ;
	String contact_no=transmst.getContact_no();
	String dob=transmst.getDob();
	String driver_nm=transmst.getDriver_nm();
	String eff_dt=transmst.getEff_dt();
	String license_end_dt=transmst.getLicense_end_dt();
	String license_from_dt=transmst.getLicense_from_dt();
	String license_issue_st_cd=transmst.getLicense_issue_st_cd();
	String license_type=transmst.getLicense_type();
	String status=transmst.getStatus();
	
	
	%>
<body>
	<div class="tab-content">
		<div class="tab-pane active" >
			<div class="box mb-0">
				<form name="trnsvehdri" id="transvehdriver" method="post" action="../servlet/Frm_Transporter?option=saveDriverDtl" enctype="multipart/form-data" >
					
					<input type="hidden" name="modCd" value="<%=modCd%>">
					<input type="hidden" name="formId" value="<%=formId%>">
					<input type="hidden" name="subModUrl" value="<%=subModUrl%>">	
					<input type="hidden" name="sysdate" value="<%=date%>">
					<input type="hidden" name="user_cd" value="<%=user_cd%>">
					
					<input type="hidden" name="submitType" id="submitType" 
						<%if(license_no.equals("")){%> value="save"<%}else{ %>value="modify"<%} %>
					>
				
					<!-- <div class="box-header with-border" style="background-color:#ffe57f">
						<h2 class="text-center"><b>Driver Master Entry Form</b></h2>
					</div> -->
					
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
					<div class="box-body table-responsive no-padding">
					<div class="col-xs-6" >
					<table id="example" class="table table-bordered" >
						<thead>
						
							<tr class="info">
								<th class="col-md-5 text-right" >Transporter Name<span class="s-red">*</span></th>
								<td class="col-md-7" colspan="2">
									<select class="form-control"  id="trans_cd" name="trans_cd" onchange="validateData();" required="required">
										<option  value="" selected="selected">-All-</option>
										<%for(int i=0;i<Vtrans_cd.size();i++){ %>
											<option  value="<%=Vtrans_cd.elementAt(i) %>"><%= Vtrans_nam.elementAt(i)%></option>
										<%}%>
									</select>
									<%if(!trans_cd.equals("")) {%>
									<script type="text/javascript">document.forms[0].trans_cd.value='<%=trans_cd%>'</script>
									<%} %>
								</td>
							</tr>
							
							<tr class="info">
								<th class="col-md-5 text-right">Driver Name <span class="s-red">*</span></th>
								<td class="col-md-7"><input type="text" size="40" required="required" name="driver_nm" id="driver_nm" value="<%=driver_nm%>"> </td>
								<td >
									<i class="fa fa-pencil-square-o" style="font-size:20px;color:red" onclick="openAllDriver();">&nbsp;Modify</i>
								</td>
							</tr>
							
							<tr class="info">
								<th class="col-md-5 text-right">DoB<span class="s-red">*</span></th>
								<td class="col-md-7"  colspan="2"><input type="text" value="<%=dob%>" placeholder="dd/mm/yyyy" size="12"  class="datepick" id="dob" name="dob" onchange="validateDoB(this.value,this);"  required="required" > </td>
							</tr>
							
							<tr class="info">
								<th class="col-md-5 text-right">Contact No.</th>
								<td class="col-md-7" colspan="2"><input type="text" value="<%=contact_no%>" size="12" placeholder="Mobile Number" id="mob_no" name="mob_no" onkeyup="checkNum(this.value);" maxlength="10"   > </td>
							</tr>
							
							<tr class="info">
								<th class="col-md-5 text-right" style="vertical-align: middle;">Address</th>
								
								<td class="col-md-7" colspan="2">
									<textarea rows="3" cols="50" name="address" id="address"><%=address %> </textarea>
								</td>	
							</tr>
							<tr class="info">
								<th class="col-md-5 text-right">Effective Date <span class="s-red">*</span></th>
								<td class="col-md-7" colspan="2"><input type="text"  size="12" value="<%=eff_dt%>" class="datepick" id="eff_dt" name="eff_dt" placeholder="dd/mm/yyyy"  required="required" > </td>
							</tr>
						</thead>
					</table>
					</div>
					<div class="col-xs-6" >
					<table id="example" class="table table-bordered" >
						<thead>
							<tr class="info">
								<th class="col-md-5 text-right">License No.<span class="s-red">*</span></th>
								<td class="col-md-7"><input type="text" size="20" value="<%=license_no%>" onkeyup="validateLicence(this.value);" id="license" name="license_no" required="required" <%if(license_no.equals("")){%> <%}else{ %>readonly  <%} %>> </td>
							</tr>
							
							<tr class="info">
								<th class="col-md-5 text-right">License Valid From :<span class="s-red">*</span></th>
								<td class="col-md-7">
									<input type="text" size="12" placeholder="dd/mm/yyyy" value="<%=license_from_dt%>" class="datepick" id="lic_from_dt" name="lic_from_dt" onchange="validateLicDt(this);"  required="required">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<b>To : <span class="s-red">*</span>&nbsp;&nbsp;</b>
									<input type="text" size="12" placeholder="dd/mm/yyyy" value="<%=license_end_dt%>" class="datepick" id="lic_to_dt"  name="lic_to_dt" onchange="validateLicDt(this);"  required="required"> 									
								</td>
							</tr>
							
							<tr class="info">
								<th class="col-md-5 text-right">License Type<span class="s-red">*</span></th>
								<td class="col-md-7">
									<select class="form-control" required="required" name="lic_typ" id="lic_typ">
										<option value="" selected="selected">-select-</option>
										<option value="HMV">HMV</option>
										<option value="HPMV">HPMV</option>
										<option value="HTV">HTV</option>
										<option value="LMV-TR">LMV-TR</option>
										<option value="LMV">LMV</option>
										<option value="LDRXCV">LDRXCV</option>
										<option value="TRANS">TRANS</option>
									</select>
									<%if(!license_type.equals("")) {%>
									<script type="text/javascript">document.forms[0].lic_typ.value='<%=license_type%>'</script>
									<%} %>
								</td>
							</tr>
							
							
							<tr class="info">
								<th class="col-md-5 text-right">License Issue State<span class="s-red">*</span></th>
								<td class="col-md-7">
									<select class="form-control" name="driver_st_cd" id="driver_stcd" required="required" >
										<option value="Select State">Select State</option>
										<%for(int i=0;i<statecode.size();i++) {%>
											<option value="<%=statecode.elementAt(i)%>"><%=statename.elementAt(i).toString().trim()%></option>
										<%} %>
									</select>
									<%if(!license_issue_st_cd.equals("")) {%>
									<script type="text/javascript">document.forms[0].driver_st_cd.value='<%=license_issue_st_cd%>'</script>
									<%} %>
								 </td>
							</tr>
							
							 <tr class="info">
			                    <th class="col-md-5 text-right">Select License Image  </th>
			                   <td class="col-md-7"><input type="file"  name="license_photo" />
			                </tr>
						</thead>
					</table>
					</div>
					
					<div class="col-xs-12" >
						<table id="example" class="table table-bordered" >
							<thead>
								<tr>
									<td align="right">
										<button type="button"  class="btn btn-warning" onclick="reset_val();" id="rset">Reset</button>
									</td>
									
									<td  align="left">
										<button type="submit"  <%if(license_no.equals("")) {%>class="btn btn-success"  value="save" <%}else{%>class="btn btn-danger"  value="modify" <% }%> id="subBtn" name="subBtn" >
										<%if(license_no.equals("")){%>
											Submit
										<%}else{ %>
											Modify
										<%} %>	
										</button>
									</td>
								</tr>
							</thead>
						</table>		
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
<!-- jQuery -->
<!-- <script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script> -->
<script type="text/javascript"
	src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript"
	src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
		
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
			
					
