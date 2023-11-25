<!DOCTYPE html>
<%@ page import="java.util.Vector" %>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<title> ATS </title>
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

<!-- <link rel="stylesheet" href="responsive/css_new/main.css">
<link rel="stylesheet" href="responsive/css_new/responsive.css">
<link rel="stylesheet" href="responsive/css_new/admin.min.css">
<link rel="stylesheet" href="responsive/css_new/_all-skins.min.css">
 -->
<!-- <script src="../js/jquery-1.6.4.min.js"></script> -->
<!-- jQuery -->
<script src="../responsive/js/jquery.min.js"></script>

<!-- Bootstrap JS -->
<script src="../js/bootstrap.min.js"></script>
<script src="../js/actionJS.js"></script>

<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>

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

<script>

function openTab(mod_cd,formId,file_path,alw_add,alw_view,alw_upd,alw_del,alw_print){
// 	alert(mod_cd)
	var head_tab = document.forms[0].header_tab.value;
// 	if(alw_view == 'Y' && file_path!=''){
		var url =file_path+"?modCd="+mod_cd+"&formId="+formId+"&alw_add="+alw_add+"&alw_view="+alw_view+
				"&alw_upd="+alw_upd+"&alw_del="+alw_del+"&alw_print="+alw_print+"&head_tab="+head_tab;
// 		alert(url)
		location.replace(url);
	}else{
		alert('You have no access rights to view this module!');
	} 
}


</script>

</head>
<!-- <body onload=""> -->
<!-- <body onload="calc_task_risk('0');"> -->
<!--/header START-->
<jsp:useBean class="com.seipl.hazira.dlng.util.Menu_Bean" id="menu" scope="page" />

<%@ include file="../home/header.jsp"%>
<%

String header_tab=request.getParameter("head_tab")==null?"":request.getParameter("head_tab");
String msg=request.getParameter("msg")==null?"":request.getParameter("msg");
String selModule2=request.getParameter("modCd")==null?"":request.getParameter("modCd");
// String file_path=request.getParameter("file_path")==null?"":request.getParameter("file_path");
String selForm_id=request.getParameter("formId")==null?"":request.getParameter("formId");

// System.out.println("file_path****"+file_path+"***selModule2***"+selModule2);
Vector mform_id=new Vector();
Vector mform_name=new Vector();
Vector mform_type=new Vector();
Vector mform_seq_no=new Vector();
Vector mapplication_path=new Vector();
Vector alw_add=new Vector();
Vector alw_del=new Vector();
Vector alw_upd=new Vector();
Vector alw_print=new Vector();
Vector alw_view  = new Vector();
 //rena

   
// menu.setOption(mtemp2);
String mnm2="";
String mdn="";
int tval = 0;

String memp_name3 = "" + session.getAttribute("userid");
System.out.println("memp_name3****"+memp_name3);
if(!selModule2.equals("")){
	menu.setemp_nm(memp_name3);
	menu.setOption("SUBMOD");
	menu.setModule_cd(selModule2);
// 	menu.setFile_path(file_path);
	menu.setSelForm_id(selForm_id);
	
	
	menu.init();
	
	selModule2 = menu.getModule_cd();
	mform_id = menu.getFormId();
	mform_name = menu.getFormName();
	mapplication_path = menu.getApplicationPath();
	alw_add=menu.getALW_ADD();
	alw_del=menu.getALW_DEL();
	alw_upd=menu.getALW_UPD();
	alw_print=menu.getALW_PRINT();
	alw_view=menu.getALW_VIEW();
// 	file_path = menu.getFile_path();
	selForm_id = menu.getSelForm_id();
}
System.out.println("selForm_id****"+selForm_id);
%>

<div class="tab-content">

<!--Add new Action  TAB START-->
<div class="tab-pane active" id="add_new_action">
<!-- Default box -->
<div class="box mb-0">
<div class="box-body">

<!-- 	<form action="" id=""> -->
	<div class="form-group row" align="center">
		<%for(int i = 0; i< mform_id.size(); i++){ 
		%>
			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
			<div class="input-group">
			<div id="radioBtn" class="btn-group">
					<a <%if(selForm_id.equals(mform_id.elementAt(i))){ %> 
					  		class="btn btn-primary btn-sm btn-block pb-4 pt-4  active" 
					 	<%}
						if(selForm_id.equals("") && i==0){	%>
							class="btn btn-primary btn-sm btn-block pb-4 pt-4  active"
						<%}else{ %> class="btn btn-default btn-sm btn-block pb-4 pt-4  notActive" <%} %>
					  
					  data-toggle="fun" data-title="Y" name="oper_sel" value="R" > <%=mform_name.elementAt(i) %></a>
			</div>
			</div>
			</div>
		<%}%>
	</div>
	
	
	<input type="hidden" name="header_tab" value="<%=header_tab%>">
	

<%-- <%if(!file_path.equals("")){ %>
	<jsp:include page="<%=file_path %>" /> 

<%} %> --%>
<!-- </div></div></div></div></body> -->
</html>
