<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css" >
<link rel="stylesheet" href="../css/pt_sans.css">
<link rel="stylesheet" href="../css/jquery-ui.css">
<link rel="stylesheet" href="../css/style.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js" ></script>

<style type="text/css">
table tr{
   color: black;
}
.select {
    width: 200px;
    height: 27px;
} 
</style>
<head>
<title>DLNG | Transporter Daily Seller Nomination Details</title>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Seller_Report_Generation" id="contMgmt" scope="page"/>   
<%
	double convt_mmbtu_to_mt = (double) session.getAttribute("convt_mmbtu_to_mt");

	utilBean.init();
	String curr_dt = utilBean.getGen_dt();
	String next_dt = utilBean.getDate_tomorrow();
	String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
	String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");

	String gas_dt = request.getParameter("gas_date")==null?next_dt:request.getParameter("gas_date");
	String index = request.getParameter("index")==null?"":request.getParameter("index");
	String trans_cd = request.getParameter("trans_cd")==null?"":request.getParameter("trans_cd");
	String seq_no = request.getParameter("seq_no")==null?"":request.getParameter("seq_no");
	String supp_seq_no = request.getParameter("supp_seq_no")==null?"":request.getParameter("supp_seq_no");
// 	System.out.println("index---"+index);

	contMgmt.setGas_date(gas_dt);
	contMgmt.setTrans_cd(trans_cd);
	contMgmt.setSeq_no(seq_no);
	contMgmt.setSupp_seq_no(supp_seq_no);
	
	contMgmt.setCallFlag("PREVIEW_DAILY_SELLER_NOM_TRANSPORTER");
	contMgmt.init();
	
	String supp_con_desig = contMgmt.getSupp_con_desig();
	String to_fax_no =contMgmt.getTo_fax_no();
	String from_fax_no =contMgmt.getFrom_fax_no();
	
%>	
<div class="tab-content">
	<div class="tab-pane active" >
		<div class="box mb-0">
			<div class="box-header with-border">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12" style="background-color: #fffff8;">
						<div class="col-xs-12" >
					  		<div class="table-responsive" >
					    		<table class="table table-striped" >
					    			<tr>
					    				<th class="text-center" colspan="12" style="color: Black;font-size: 18px;" > Seller's Daily  Nomination for Date <%=gas_dt%><br>Electronic Transmission </th>
					    			</tr>
					    		</table>
					    	</div>
					    </div>
					    
					    <div class="col-xs-6" >
						    <div class="table-responsive"  >
					    		<table class="table table-striped"  >
					    			<tr>
					    				<td class="info" >To</td>
					    				<td id="to"></td>
					    			</tr>
					    			<tr>
					    				<td class="info" >Transporter Name</td>
					    				<td id="trans_nm"></td>
					    			</tr>
					    			
					    			<tr>
					    				<td class="info" >Fax No</td>
					    				<td><%=to_fax_no %></td>
					    			</tr>
					    			
					    		</table>
					    	</div>
				    	</div>
				    	<div class="col-xs-6" >
						    <div class="table-responsive"  >
					    		<table class="table table-striped"  >
					    			<tr>
					    				<td class="info" >Sent Date</td>
					    				<td><%//=gen_date%></td>
					    			</tr>
					    			<tr>
					    				<td class="info" >Sent Time</td>
					    				<td><%//=gen_time%></td>
					    			</tr>
					    			
					    			<tr>
					    				<td class="info" >Seq. No</td>
					    				<td><%//=nom_rev_no%></td>
					    			</tr>
					    		</table>
					    	</div>
				    	</div>
				    		
				    	<div class="col-xs-12" >
						    <div class="table-responsive"  >
					    		<table class="table table-striped"  >
					    			<tr>
					    				<td class="info" >&nbsp;</td>
					    			</tr>
					    		</table>
					    	</div>
				    	</div>
				    	<div class="col-xs-6" >
						    <div class="table-responsive"  >
					    		<table class="table table-striped"  >
					    			<tr>
					    				<td class="info" >From</td>
					    				<td><%=supp_con_desig%></td>
					    			</tr>
					    			<tr>
					    				<td class="info" >&nbsp;</td>
					    				<td>Shell Energy India Private Limited</td>
					    			</tr>
					    			<tr>
					    				<td class="info" >Fax No</td>
					    				<td><%=from_fax_no%></td>
					    			</tr>
					    		
					    			<tr>
					    				<td class="info" >Subject</td>
					    				<td>Seller's Daily Nomination</td>
					    			</tr>	
					    		</table>
					    	</div>
				    	</div>	
					  </div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
var ind = '<%=index%>';  
var e = window.opener.document.getElementById("to"+ind);
var text = e. options[e. selectedIndex]. text;

document.getElementById("to").innerHTML = text;
document.getElementById("trans_nm").innerHTML = window.opener.document.getElementById("trans_nm"+ind).value;

</script>
</html>
					    