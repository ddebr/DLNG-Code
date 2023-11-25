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


<link rel="stylesheet" href="../responsive/w3/w3.css">  <!-- For accordion  -->
<!-- BS CSS -->
<link href="../responsive/css/bootstrap.min.css" rel="stylesheet">
<!-- <link href="responsive/css/datepicker/bootstrap-datepicker3.css" rel="stylesheet"> -->
<!-- CSS -->
<link href="../responsive/css/main.css" rel="stylesheet">
<link href="responsive/css/style.css" rel="stylesheet">
<!-- Font Awesome -->
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">  
<link href="../css/pt_sans.css" rel="stylesheet">

<!-- jQuery -->
<script src="../responsive/js/jquery.min.js"></script>

<!-- Bootstrap JS -->
<script src="../js/bootstrap.min.js"></script>
<script src="../js/actionJS.js"></script>

<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script type="text/javascript">

function setDriver(license_no,trans_cd){
	
	if(license_no!=""){
	
		 window.opener.parantWin1(license_no,trans_cd);
		 window.close();	
		 
	}else{
		alert("Please select atleast one Driver")
	}

}
</script>
<jsp:useBean class="com.seipl.hazira.dlng.transporter.DataBean_transporter_Veh_Driver" id="transmst" scope="page"/> 

<%
String trans_cd = request.getParameter("trans_cd")== null?"":request.getParameter("trans_cd");	

transmst.setCall_flag("driverList");
transmst.setTrans_cd(trans_cd);

transmst.init();

Vector license_no  = transmst.getLicense_no();
Vector driver_name = transmst.getDriver_name();


%>
</head>

<body>
	<div class="tab-content">
		<div class="tab-pane active" >
			<div class="box mb-0">
				<form name="">
					<div class="box-body table-responsive no-padding">
						<div class="col-xs-12" >
							<table id="example" class="table table-bordered" >
							<thead>
							
								<tr class="info">
									<th class="col-md-5 text-center"> Select</th>
									<th class="col-md-5 text-center" >Driver Name</th>
									<th class="col-md-5 text-center" >License No.</th>
								</tr>
								<%for(int i = 0 ; i < license_no.size() ; i++){ %>
									<tr>
										<td class="text-center"><input type="radio" name="rad" onclick="setDriver('<%=license_no.elementAt(i) %>','<%=trans_cd%>');"> </td>
										<td class="text-center"><%=driver_name.elementAt(i) %></td>
										<td class="text-center"><%=license_no.elementAt(i) %></td>
									</tr>
								<%} %>
							</thead>
						</table>
					</div>
				</div>					
				</form>
			</div>
		</div>
	</div>
</body>
</html>
