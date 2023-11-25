<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title> DLNG | Driver Details Report </title>

<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css">
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="../css/pt_sans.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script src="../js/jquery.min.js"></script>
<link rel="stylesheet" href="../css/tlu.css">
<link rel="stylesheet" type="text/css" href="../css/jquery.dataTables.css">
<!-- <link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css"> -->
</head>

<script type="text/javascript">
function filtDriver(){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var state = document.forms[0].state.value;
	
	var url = "../reports/rpt_master.jsp?state="+state+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
// 	alert(url)
	location.replace(url);
}
</script>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Driver_Report" id="driver" scope="page"/>

<%
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String state = request.getParameter("state")==null?"all":request.getParameter("state");
	String formName = request.getParameter("FormName")==null?"":request.getParameter("FormName");
	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212

	
	driver.setCallFlag("Driver_Report");
	driver.setState(state);
	driver.init();

	Vector  state_code = driver.getState_code();
	Vector  state_nm =  driver.getState_nm();
			
	Vector address = driver.getAddress(); 
	Vector contact_no = driver.getContact_no(); 
	Vector dob = driver.getDob();
	Vector driver_name = driver.getDriver_name();
	Vector eff_dt = driver.getEff_dt();
	Vector 	ent_by = driver.getEnt_by();
	Vector 	ent_dt = driver.getEnt_dt();
	Vector 	license_end_dt = driver.getLicense_end_dt();
	Vector 	license_from_dt = driver.getLicense_from_dt();
	Vector 	license_img = driver.getLicense_img();
	Vector 	license_issue_st_cd = driver.getLicense_issue_st_cd();
	Vector 	license_no = driver.getLicense_no();
	Vector 	license_type = driver.getLicense_type();
	Vector 	status = driver.getStatus();
	Vector 	trans_cd = driver.getTrans_cd();
	Vector  driver_state_nm = driver.getDriver_state_nm();
	
%>
<div class="tab-content">
<div class="tab-pane active" id="hcasreport">
<!-- Default box -->
<div class="box mb-0">
<form >

<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
<input type="hidden" name="form_id" value="<%=formId%>">
<input type="hidden" name="form_nm" value="<%=formName%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">

	<div class="box-header with-border main-header" >
		 <div class="form-group col-md-2">
		 	 <label for="">State</label>
		      <select class="form-control"  name="state" onchange="filtDriver();">
		      	<option value="all">-All-</option>
		      	<%for(int i = 0 ; i < state_code.size() ; i++ ) {%>
		      		<option value="<%=state_code.elementAt(i)%>"><%=state_nm.elementAt(i) %></option>
		      	<%} %>
		      </select>
		      <script type="text/javascript">
		      	document.forms[0].state.value='<%=state%>';
		      </script>
		 </div>
		 
		 <div class="form-group col-md-2">
	 	 	<label for="">&nbsp;</label>
	 	  	<div class='input-group'>
	     		<button type="button"  class="btn btn-primary"   name="viewList" value="View" onclick="filtDriver();" > View List </button>
	     	</div>
	 	 </div>
	 	 
	 	 <div class="form-group col-md-2">
		 	 <label for="">&nbsp;</label>
		 	  	<div class='input-group'>
			  		<button type="button"  class="btn btn-warning" id="btn" onclick="fnExcel('Driver_Detailed_Report');" value="export" >Export To Excel</button>	
		      	</div>
		 </div>
 	</div>
 	
	<div class="box-body table-responsive no-padding">
		<div class="table-responsive">
			<table id="example" class="table table-bordered table-hover">
			    <thead>
			    <tr><td colspan="13" align="center" style="font-size: 15px;font-weight: bold;"> Driver Details Report </td></tr>
			      <tr class="info ">
			      	<th colspan="1" rowspan="2" class="text-center">Seq.</th>
			      	<th colspan="1" rowspan="2" class="text-center"> Driver Name</th>
			      	<th colspan="1" rowspan="2" class="text-center">DoB</th>
			      	<th colspan="1" rowspan="2" class="text-center">Address</th>
			      	<th colspan="1" rowspan="2" class="text-center">Contact No.</th>
			      	<th colspan="5" rowspan="1" class="text-center">License</th>
			      	<th colspan="1" rowspan="2" class="text-center">Effective Date</th>
			      	<th colspan="1" rowspan="2" class="text-center">Status</th>
			      	<th colspan="1" rowspan="2" class="text-center">Linked (Transporter)</th>
			      </tr>
			      
			      <tr class="info">
			      	<th class="text-center">No.</th>
			      	<th class="text-center">Type</th>
			      	<th class="text-center">Issue Dt.</th>
			      	<th class="text-center">End Dt.</th>
			      	<th class="text-center">State</th>
			      </tr>
			      
			    </thead>
			    <tbody>
			    <%for(int i = 0 ;  i < license_no.size(); i++) { %>
			    	<tr class="text-center">
			    		<td><%=i+1 %></td>
			    		<td><%=driver_name.elementAt(i) %></td>
			    		<td><%=dob.elementAt(i) %></td>
			    		<td><%=address.elementAt(i) %></td>
			    		<td><%=contact_no.elementAt(i) %></td>
			    		<td><%=license_no.elementAt(i) %></td>
			    		<td><%=license_type.elementAt(i) %></td>
			    		<td><%=license_from_dt.elementAt(i) %></td>
			    		<td><%=license_end_dt.elementAt(i)  %></td>
			    		<td><%=driver_state_nm.elementAt(i) %></td>
			    		<td><%=eff_dt.elementAt(i) %></td>
			    		<td><%=status.elementAt(i) %></td>
			    		<td><%=trans_cd.elementAt(i) %></td>
			    	</tr>
			    <%} %>	
			    </tbody>
  			</table> 	
		</div>
	</div> 
					
 </form>
 </div>
 </div>
 </div>
 
 <script src="../js/jquery-1.6.4.min.js" charset="utf8"></script>
 <script src="../js/jquery.dataTables.min.js" charset="utf8"></script>
<!--  <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script> -->
<!--   <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script> -->
  <script>
  $(function(){
    $("#example").dataTable({
    	"bPaginate": false
    });
  })
  </script>  
   
 </html>
