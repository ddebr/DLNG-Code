<%@ page import="java.util.Vector" %>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title> DLNG | LOA Master </title>

<!-- For accordion  -->
<link rel="stylesheet" href="../responsive/w3/w3.css">
<!-- Bootstrap CSS -->
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
<!-- <script type="text/javascript" src="../js/bootstrap.min.js"></script> -->
<script src="../js/actionJS.js"></script>

<style type="text/css">
.s-red{
color: red;
font-size: 18px;
}

.s-green{
color: green;
font-size: 14px;
}

.size{
font-size: 15px;
}

</style>
<%@include file="../master/frm_mst_formMaster.jsp" %>
<%@ include file="../home/header.jsp"%>
<body>
	<div class="form-group row" align="center">
		<%for(int i = 0; i< mform_id.size(); i++){ %>
			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
				<div class="input-group">
					<div id="radioBtn" class="btn-group">
						<a <%if(selForm_id.equals(mform_id.elementAt(i))){ %> 
					  		class="btn btn-primary btn-sm btn-block pb-4 pt-4  active" 
						 	<%}else{ %> 
						 		class="btn btn-default btn-sm btn-block pb-4 pt-4  notActive" 
						 	<%} %>
					 	 
					 		data-toggle="fun" data-title="Y" name="oper_sel" value="R" onclick="openSubMod('<%=mapplication_path.elementAt(i)%>','../contract_master/frm_mst_LoA.jsp','<%=selModule1%>','<%=mform_id.elementAt(i)%>');"> <%=mform_name.elementAt(i) %></a>
					 		
					 		<%
					 			session.setAttribute("write_permission", alw_add.elementAt(i));
					 			session.setAttribute("delete_permission", alw_del.elementAt(i));
					 			session.setAttribute("print_permission", alw_print.elementAt(i));
					 			session.setAttribute("approve_permission", alw_approve.elementAt(i));
					 			session.setAttribute("view_permission", alw_view.elementAt(i));
					 			session.setAttribute("update_permission", alw_upd.elementAt(i));
					 			session.setAttribute("audit_permission", alw_audit.elementAt(i));
					 			session.setAttribute("form_name", mform_name.elementAt(i));
					 		%>
					</div>
				</div>
			</div>
		<%}%>
	</div>
<jsp:include page="<%= subModUrl%>" ></jsp:include>
</body>
</head>
</html>