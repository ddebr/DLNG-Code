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
function selTruckDriver(selTrans){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	var url="../master/frm_mst_transportation.jsp?selTrans="+selTrans+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
	location.replace(url);
}

/* function modifyLinked(license_no,truck_id,status,truck_nm){
	
	$("#truck_id").empty();
// 	alert(truck_id)
	document.getElementById('license_no').value =license_no;
	document.getElementById('status_flag').value =status;
	var select = document.getElementById("truck_id");
	select.options[select.options.length] = new Option(truck_nm, truck_id);
	document.getElementById('submitType').value="modify";
	document.getElementById('unlink').style.visibility="visible";
} */
function saveUnlink(linked_lic_no,linked_truck_no){
	
	document.forms[0].linked_lic_no.value = linked_lic_no;
	document.forms[0].linked_truck_no.value = linked_truck_no;
	
	var a = confirm('Are you sure want to Unlink selected Driver-Transporter-Truck combination?');
	if(a){
		document.getElementById('submitType').value="modify";
		document.forms[0].submit();
	}
}
</script>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.truck_master.DataBean_truckMaster" id="truckMastr" scope="page"/> 
<jsp:useBean class="com.seipl.hazira.dlng.transporter.DataBean_transporter_Veh_Driver" id="transmst" scope="page"/> 

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
	
	String selTrans =  request.getParameter("selTrans")== null?"":request.getParameter("selTrans");	
	
	truckMastr.init();
	Vector Vtrans_cd = truckMastr.getVtrans_cd();
	Vector Vtrans_nam = truckMastr.getVtrans_name();
	
	if(!selTrans.equals("")){
		
		transmst.setCall_flag("fetchLinkDtl");
		transmst.setTrans_cd(selTrans);
		transmst.init();
	}
	
	selTrans  = transmst.getTrans_cd();
	Vector Vtruck_id = transmst.getVtruck_id();
	Vector Vtruck_nm = transmst.getVtruck_nm();
	
	Vector Vlicense_no = transmst.getVlicense_no();
	Vector Vdriver_nm = transmst.getVdriver_nm();
	
	Vector VLinked_driver_nm= transmst.getVLinked_driver_nm();
	Vector VLinked_truck_nm= transmst.getVLinked_truck_nm();
	Vector VLinked_license_no= transmst.getVLinked_license_no();
	Vector VLinked_status= transmst.getVLinked_status();
	Vector VLinked_date=transmst.getVLinked_date();
	Vector VLinked_truck_id=transmst.getVLinked_truck_id();			
%>	

<body>
	<div class="tab-content">
		<div class="tab-pane active" >
			<div class="box mb-0">
				<form name="trnsvehdri" id="transvehdriver" method="post" action="../servlet/Frm_Transporter?option=savelinkData"  >
					<input type="hidden" name="modCd" value="<%=modCd%>">
					<input type="hidden" name="formId" value="<%=formId%>">
					<input type="hidden" name="subModUrl" value="<%=subModUrl%>">	
					<input type="hidden" name="sysdate" value="<%=date%>">
					<input type="hidden" name="user_cd" value="<%=user_cd%>">
					
					<input type="hidden" name="submitType" id="submitType" value="save">
					
					<input type="hidden" name="linked_lic_no" value="">
					<input type="hidden" name="linked_truck_no" value="">
					<input type="hidden" name="linked_trans" value="<%=selTrans %>">
					
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
								<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
									<label>Select Transporter</label>
									<div class="form-group">
										<select class="form-control" name="trans_cd" id="trans_cd" onchange="selTruckDriver(this.value);" required="required">
											<option  value="" selected="selected">-All-</option>
											<%for(int i=0;i<Vtrans_cd.size();i++){ %>
												<option  value="<%=Vtrans_cd.elementAt(i) %>"><%= Vtrans_nam.elementAt(i)%></option>
											<%}%>
										</select> 
										<%if(!selTrans.equals("")) {%>
											<script type="text/javascript">document.forms[0].trans_cd.value='<%=selTrans%>'</script>
										<%} %>
									</div>	
								</div>
								
								<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
									<label>Available Truck(s)</label>
									<div class="form-group">
										<select class="form-control" name="truck_id" id="truck_id"  required="required">
											<option  value="" selected="selected">-Select-</option>
											<%for(int i=0;i<Vtruck_id.size();i++){ %>
												<option  value="<%=Vtruck_id.elementAt(i) %>"><%=Vtruck_nm.elementAt(i)%></option>
											<%}%>
										</select> 
									</div>
								</div>
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
									<label>Available Driver(s)</label>
									<div class="form-group">
										<select class="form-control" name="license_no" id="license_no" required="required">
											<option  value="" selected="selected">-Select-</option>
											<%for(int i=0;i<Vlicense_no.size();i++){ %>
												<option  value="<%=Vlicense_no.elementAt(i) %>"><%=Vdriver_nm.elementAt(i)%></option>
											<%}%>
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
										<input type="submit" name="Link" value="Link" class="btn btn-success" > 
									</div>
								</div>
								
								
								<div class="col-lg-1 col-md-1 col-sm-1 col-xs-12" id="unlink" style="visibility: hidden;">
									<label>&nbsp;</label>
									<div class="form-group">
										<input type="submit" name="Un-Link" value="Un-Link" class="btn btn-danger" > 
									</div>
								</div>
							</div>
						</div>
					</div>
					</div>
					
					<div class="box-body table-responsive no-padding" style="height: 400px;overflow: scroll;">
						<table id="example" class="table table-bordered">
							<thead>
								<tr><td colspan="9" align="center"> <font color="black"> <b style="font-size: 14px;"> Already Linked/Un-Linked Truck-Driver for the selected Transporter </b> </font></td></tr>   
								<tr class="info">
									<th class="text-center">Un-link</th>
									<th class="text-center" >Sr No</th>
									<th class="text-center" >Driver Name</th>
									<th class="text-center" >License No. </th>
									<th class="text-center" >Truck No.</th>
									<th class="text-center" >Status</th>
									<th class="text-center" >Linked Date</th>
								</tr>
							</thead>
							<tbody>
							<%
							for(int i = 0 ; i < VLinked_license_no.size() ; i++) {
							String temp_sts = "";
							%>
								<tr  class="text-center" style="color: black;">
									<td>
<%-- 										<i class="fa fa-pencil-square-o" style="font-size:20px;color:red" onclick="modifyLinked('<%=VLinked_license_no.elementAt(i) %>','<%=VLinked_truck_id.elementAt(i) %>','<%=VLinked_status.elementAt(i) %>','<%=VLinked_truck_nm.elementAt(i) %>');"></i> --%>
									<%if(VLinked_status.elementAt(i).equals("Y")) {
										temp_sts = "Linked";
									%>
										<a href="#" onclick="saveUnlink('<%=VLinked_license_no.elementAt(i) %>','<%=VLinked_truck_id.elementAt(i) %>');"><b><font color="red"><u>Un-link</u></font></b></a>
									<%}else{ 
										temp_sts = "Un-Linked";
									} %>
									</td>
									<td><%=i+1 %></td>
									<td><%=VLinked_driver_nm.elementAt(i) %></td>
									<td><%=VLinked_license_no.elementAt(i) %></td>
									<td><%=VLinked_truck_nm.elementAt(i) %></td>
									<td><%=temp_sts %></td>
									<td><%=VLinked_date.elementAt(i) %>
									</td>
								</tr>
							<% }%>
							<%if(VLinked_license_no.size() == 0) {%>
							<tr>
								<td colspan="8" align="center">
									<font color="red">No Linking found for selected Transporter!!</font>
								</td>
							</tr>
							<%} %>
							</tbody>
						</table>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<!-- jQuery -->
		
</html>

				
					
					
					