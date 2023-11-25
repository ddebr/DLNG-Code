<!DOCTYPE html>
<%@ page import="java.util.Vector" %>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- For accordion  -->
<link rel="stylesheet" href="../responsive/w3/w3.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="responsive/css/style.css" >
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="../css/pt_sans.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/actionJS.js"></script>
<script src="../js/jquery.min.js"></script>

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

<script type="text/javascript">
/* if(obj.id="openModule1"){
$.getJSON(url, function(read_data) {
	 $.each(read_data.result, function(i, data) {
	      console.log("read_data : "+data)
	 });
});
} */

function fatchs(obj){
	var loc = window.location;
	var url = loc.protocol+"//"+loc.host+"/TLU/tlu_xml/tlu_new18-Sep-20.json";
	console.log("url  : "+url);

	/* if(obj.id="fatchbtn"){
		document.forms[0].action="../servlet/Frm_JSON_Data";
		document.forms[0].submit();
	} */
	
	if(obj.id="fatchbtn")
	{
		var datas = [];
	}
}
</script>

<%@include file="../master/frm_mst_formMaster.jsp" %>

<%@ include file="../home/header.jsp"%>

<body>
	<div class="form-group row" align="center">
		<%for(int i = 0; i< mform_id.size(); i++){ %>
			<%if(mform_name.elementAt(i).toString().equals("TLU Loading Data Capture JSON")){%>
				<form method="post" action="">
			<%}%>
			
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
					<div class="input-group">
						<div id="radioBtn" class="btn-group">
							<a <%if(selForm_id.equals(mform_id.elementAt(i))){ %> 
						  		class="btn btn-primary btn-sm btn-block pb-4 pt-4  active" 
							 	<%}else{ %> 
							 		class="btn btn-default btn-sm btn-block pb-4 pt-4  notActive" 
							 	<%} %>
						 		data-toggle="fun" data-title="Y" name="oper_sel" value="R" 
						 		onclick="<%if(mform_name.elementAt(i).toString().equals("TLU Loading Data Capture JSON")){%>
						 		     fatchs(this);openSubMod('<%=mapplication_path.elementAt(i)%>','../master/frm_mst_truckLoading.jsp','<%=selModule1%>','<%=mform_id.elementAt(i)%>');<%}else{%>openSubMod('<%=mapplication_path.elementAt(i)%>','../master/frm_mst_truckLoading.jsp','<%=selModule1%>','<%=mform_id.elementAt(i)%>');<%}%>" 
						 		
						 		<%if(mform_name.elementAt(i).toString().equals("TLU Loading Data Capture JSON")){%> id="fatchbtn" <%}%> > <%=mform_name.elementAt(i) %></a>
						 </div>
					</div>
				</div>
			<%if(mform_name.elementAt(i).toString().equals("TLU Loading Data Capture JSON")){%>
				</form>
			<%}%>
		<%}%>
	</div>
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>	
<jsp:include page="<%= subModUrl%>" ></jsp:include>	
</body>
</head>
</html>