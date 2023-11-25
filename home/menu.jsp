<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<jsp:useBean class="com.seipl.hazira.dlng.util.Menu_Bean" id="menu1" scope="page"/>

<%
String selModule = request.getParameter("modCd")==null?"":request.getParameter("modCd");
String home_click = request.getParameter("home_click")==null?"N":request.getParameter("home_click");

	String sId="";
	
	if(session.getAttribute("username")==null||session.getAttribute("username")=="")
	{
	    sId="0";
	}  
	else
	{
	    sId="1";
	}  
			
    String memp_cd1 = "" + session.getAttribute("emp_cd");
    String memp_name2 = "" + session.getAttribute("userid");

	String mtemp1="MOD";
	String mtemp2="SUBMOD";
			
	menu1.setemp_nm(memp_name2);
	menu1.setOption(mtemp1);
	menu1.init();

	Vector mmodule_nm=menu1.getModuleName();
	Vector modUrl = menu1.getModUrl();
	Vector modCd = menu1.getModCd();
// 	System.out.println(" *******$******  "+mmodule_nm);
	Vector FORM_CD = menu1.getFORM_CD();
	Vector FORM_NAME = menu1.getFORM_NAME();
// 	System.out.println("FORM_NAME***"+FORM_NAME);			
   
	
	Vector compare_form_id = new Vector();
	int restrict = 0;
	
	String selModCd =""; 
	if(home_click.equals("Y")){
		session.setAttribute("module_cd","");
	}else{
		selModCd = session.getAttribute("module_cd")+"";
	}
	%>
	<body>

	<section id="home-wrapper" >

	<div class="container-fluid">


	<div class="row">
	<div class="col-md-12">
	<!-- Custom Tabs -->    
	
	<ul class="nav nav-tabs">	
	<%for(int i = 0; i < mmodule_nm.size(); i++){ %>
		<li <%if(selModCd.equals(modCd.elementAt(i))){%> class="active" <%} %>>
			<a style="text-transform: none;" data-toggle="tab" onclick="setTab('<%=i%>','<%=modUrl.elementAt(i)%>','<%=modCd.elementAt(i)%>');" > <%=mmodule_nm.elementAt(i) %> </a></li>
	<%} %>
	</ul>
	
