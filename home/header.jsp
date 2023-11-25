<!DOCTYPE html>

<html>
<meta http-equiv="refresh" content="<%=session.getMaxInactiveInterval()%>; URL=../sess/Expire.jsp">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title> DLNG </title>

<!-- BS CSS -->
<link href="../responsive/css/bootstrap.min.css" rel="stylesheet">
<!-- <link href="responsive/css/datepicker/bootstrap-datepicker3.css" rel="stylesheet"> -->
<!-- CSS -->
<link href="../responsive/css/main.css" rel="stylesheet">
<link href="../responsive/css/style.css" rel="stylesheet">
<!-- Font Awesome -->
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">  
<link href="../css/pt_sans.css" rel="stylesheet">

 <!-- <link rel="stylesheet" href="../responsive/css_new/main.css">
<link rel="stylesheet" href="../responsive/css_new/responsive.css">
<link rel="stylesheet" href="../responsive/css_new/admin.min.css">
<link rel="stylesheet" href="../responsive/css_new/_all-skins.min.css">
  -->

<!-- jQuery -->
<script src="../responsive/js/jquery.min.js"></script>

<!-- Bootstrap JS -->
<script src="../responsive/js/bootstrap.min.js"></script>


</head>

<style type="text/css">

#loading {
   width: 100%;
   height: 100%;
   top: 0;
   left: 0;
   position: fixed;
   display: block;
   opacity: 0.7;
   background-color: #fff;
   z-index: 99;
   text-align: center;
}

#loading-image {
  position: absolute;
  top: 50%; /*window.innerHeight/2; /*300px;*/
  left: 50%; /*window.innerWidth/2; /*240px;*/
  z-index: 100;
}

</style>
<script language="javascript">

function openHelp()
{
   moduleDetail = document.forms[0].moduleDetail.value;
   alert("WILL BE AVAILABLE IN NEXT PHASE-III");
}
var newWindow = "";
var newWindow2 = "";
function OpenUserProfile(userCd)
{
	//alert(userCd);
   	if(!newWindow || newWindow.closed)
	{
		newWindow = window.open("../home/profile.jsp?userId="+userCd,"Profile","top=10,left=100,width=550,height=350,scrollbars=1,status=1");
	}
	else
	{
		 newWindow.close();
		newWindow= window.open("../home/profile.jsp?userId="+userCd,"Profile","top=10,left=100,width=550,height=350,scrollbars=1,status=1");
	}
}
function OpenUnitconv()
{
	//alert("NOT AVAILABLE");
   	if(!newWindow || newWindow.closed)
	{
		newWindow = window.open("../home/calculator.jsp","Calculator","top=10,left=100,width=350,height=500,scrollbars=1,status=1");
	}
	else
	{
		 newWindow.close();
		 newWindow= window.open("../home/calculator.jsp","Calculator","top=10,left=100,width=350,height=500,scrollbars=1,status=1");
	}
}

function OpenUserManual()
{
  	if(!newWindow2 || newWindow2.closed)
	{
		newWindow2 = window.open("../manual/DLNG-MANUAL.pdf","UserManual","top=10,left=10,width=1000,height=700,scrollbars=1,status=1");
	}
	else
	{
		 newWindow2.close();
		 newWindow2 = window.open("../manual/DLNG-MANUAL.pdf","UserManual","top=10,left=10,width=350,height=700,scrollbars=1,status=1");
	}
}
function home_click(stemp,login,pwd){
	var url="";
	if(stemp=="0"){
		url="../home/index3.jsp?std=0&home_click=Y";
		location.replace(url);
	}	else if(stemp=="1"){
		url="../home/index3.jsp?std=1&login="+login+"&password="+pwd+"&home_click=Y";
		location.replace(url);
	}
}
function home_click1(){
	url="../home/index4.jsp?std=1";
	location.replace(url);
}

function setTab(i,url,modCd){
// 	alert(url)
	location.replace(url+"?head_tab="+i+"&modCd="+modCd)
}

function goBack(tab,main_tab,selMod,formId){
	
	if(tab == 'action_entry'){
		location.replace("../master/frm_mst_action_entry.jsp?sel_tab=item_source&head_tab="+main_tab+"&modCd="+selMod+"&formId="+formId);
	}
}

function openSubMod(subModUrl,modUrl,modCd,selForm_id,alw_add,alw_del,alw_upd,alw_view,alw_print){
// 	alert(subModUrl+"**"+modUrl+"**"+modCd+"**"+selForm_id)
	
	if(modUrl!=''){
		location.replace(modUrl+"?modCd="+modCd+"&subModUrl="+subModUrl+"&formId="+selForm_id+"&alw_add="+alw_add+"&alw_del="+alw_del+"&alw_upd="+alw_upd+"&alw_view="+alw_view+"&alw_print="+alw_print+"&modUrl="+modUrl);	
	}
}

function show_loader(){
// 	alert('onload image ')
	document.getElementById("loading").style.width = "100%";
	document.getElementById("loading").style.height = "100%";
	document.getElementById("loading").style.visibility = "visible";
	document.getElementById("loading-image").style.visibility = "visible";
}

function fnExcelReport()
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById('example'); // id of table
    
    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
        //tab_text=tab_text+"</tr>";
    }

    tab_text=tab_text+"</table>";
    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
    {
       /*  txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"ActionDetailedRpt.xls"); */
       
        if (typeof Blob !== "undefined") {
        	//use blobs if we can
            tab_text = [tab_text];
            //convert to array
            var blob1 = new Blob(tab_text, { type: 'text/html' });
            window.navigator.msSaveBlob(blob1, 'DLNG.xls');
            return (true);
        } else {
            //otherwise use the iframe and save
            //requires a blank iframe on page called txtArea1
            txtArea1.document.open("text/html", "replace");
            txtArea1.document.write(tab_text);
            txtArea1.document.close();
            txtArea1.focus();
            sa = txtArea1.document.execCommand("SaveAs", true, "DLNG.xls");
        }
    }  
    else                 //other browser not tested on IE 11
        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

    return (sa);
}
function fnExcel(fileNm)
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById('example'); // id of table

    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
       
        //tab_text=tab_text+"</tr>";
    }

    tab_text=tab_text+"</table>";
//     tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
//     tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
//     tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params
//     tab_text = tab_text.replace(/[,'"]/g, '');
//     alert(tab_text)
    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
    {
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to Sumit.xls");
    }  
    else                 //other browser not tested on IE 11
    
    	var a = document.createElement('a');
    	var data_type = 'data:application/vnd.ms-excel';
    	a.href = data_type + ', '+ encodeURIComponent(tab_text);
    	a.download = fileNm+'.xls';
        //triggering the function
        a.click();
        
//         sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

//     return (sa);
} 

function logout(){
	location.replace("../home/index1.jsp?val=1");
}
</script>
</head>
<jsp:useBean class="com.hazira.hlpl.tlu.util.LogicBean_Alerter" id="alert" scope="request"/>
<%
	String stemp="";
    String formDetCd=request.getParameter("formId")==null?"":request.getParameter("formId");
    String loginuser="" + session.getAttribute("loginiduser"); 
    
    String passworduser="" + session.getAttribute("passworduser"); 
    
    alert.setData_for("findmod");
    alert.setSet_form_cd(formDetCd);
    alert.init();
    String moduleName=alert.getModule_name();
    
    String moduleCd="";
	if(session.getAttribute("username")==null||session.getAttribute("username")=="")
	{
	    stemp="0";
	}  
	else
	{
	    stemp="1";
	}  
	
// 	String action_id = ""+session.getAttribute("action_id");
// 	String dep_cd = ""+session.getAttribute("unit_cd");
// 	String assign_cd = ""+session.getAttribute("assign_cd");

	/* session.setAttribute("action_id", "");
	session.setAttribute("unit_cd", ""); */
	String userCd= ""+session.getAttribute("user_cd");
// 	System.out.println("session.getMaxInactiveInterval()---"+session.getMaxInactiveInterval());
%>


<header>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="background-color: white;">
<!-- Brand and toggle get grouped for better mobile display -->
<div class="navbar-header"  style="background-color: white;">
<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse" style="background-color: white;">
<span class="sr-only" style="background-color: white;">Toggle navigation</span>
<span class="icon-bar" ></span>
<span class="icon-bar" ></span>
<span class="icon-bar" ></span>
</button>
<a class="" onclick="home_click('<%=stemp%>','<%=loginuser%>','<%=passworduser%>');" >
<!-- <img src="../images/logo.png"  style="padding-top: 7px;padding-left: 4px;padding-right: 2px;"> -->
<img src="../images/shell.jpg"   style="padding-top: 3px;padding-left: 4px;padding-right: 2px;">
</a>
</div>

<!-- <form  method="POST" action="LogoutController" > -->
<!-- Top Menu Items -->
<ul class="nav navbar-right top-nav" style="background-color: white;">

<li class="dropdown" >
<a href="#" class="dropdown-toggle" data-toggle="dropdown">
<i class="fa fa-user-circle-o"></i> 
Welcome <%if(session.getAttribute("username").toString().equalsIgnoreCase("GUEST")){ %>
		    					<%=session.getAttribute("username").toString().toUpperCase() %>
		    			<%}else{ %>
		    				<%=session.getAttribute("username") %>
		    			<%} %> <small>Logged on:<%=session.getAttribute("logindate")%>&nbsp;<%=session.getAttribute("logintime")%></small>
<i class="fa fa-angle-down" aria-hidden="true"></i>
</a>
<ul class="dropdown-menu">
<!-- <li>
<a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
</li>
<li>
<a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
</li>
<li>
<a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
</li> -->
<!-- <li class="divider"></li> -->
<li>
<a onclick="logout();"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
</li>
</ul>
</li>

</ul>

<!-- </form> -->


<!-- Sidebar Menu Items -->
<div class="collapse navbar-collapse navbar-ex1-collapse" style="background-color: white;">
<ul class="nav navbar-nav side-na navbar-right" style="background-color: white;">
<li>
<a onclick="javascript: home_click('<%=stemp%>','<%=loginuser%>','<%=passworduser%>');" ><i class="fa fa-home"></i> Home </a>
</li>
<li>
<a onclick="javascript: OpenUnitconv();"><i class="fa fa-calculator"></i> Calc </a>
</li>
<li>
<!-- <a href="javascript:OpenUserManual();"> -->
<a onclick="javascript:OpenUserManual();">
<i class="fa fa-fw fa-table"></i> Manual </a>
</li>
</ul>
</div>
<!-- /.navbar-collapse -->

</nav>
</header>

<link rel="stylesheet" href="../ui/jquery-ui.css">
<script src="../js/jquery-1.12.4.js"></script>
<script src="../js/jquery-ui.js"></script>

<%@ include file="../home/menu.jsp"%>

</body >

</html><div style="height: 25px;"></div>

