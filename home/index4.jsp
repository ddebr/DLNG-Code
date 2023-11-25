<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page errorPage="../home/ExceptionHandler.jsp" %>
<%@ page import ="java.util.Properties" %>
<%@ page import ="java.util.*" %>
<%@ page import ="java.net.*" %>

<%@ page import ="java.io.*" %>
<html>
<head>
<title>TIMS : Hazira LNG Pvt. Ltd.</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
input {
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 12px;
        text-align:right;
}                            
</style>

<%--<script language="JavaScript" src="../js/date-picker.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>
<script language="JavaScript" src="../js/nhv.js"></script>--%>

<script language="JavaScript" src="../js/backspace.js"></script>

<link rel="stylesheet" type="text/css" href="../css/guidefaultGeneral.css">
<!-- RG20190207  For voting system-->
<script src="../js/perp.js"></script>
<script language="JavaScript" src="../js/date-picker.js"></script>
<script language="JavaScript" src="../js/fms.js"></script> 
<SCRIPT LANGUAGE='JavaScript' src='../js/util.js'></SCRIPT>
<style>
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
<!--  -->
<script language="JavaScript">
function change_month()
{
	var year=document.forms[0].year.value;
//	var curyear=document.forms[0].curyr.value;
	var url="../home/index4.jsp?year="+year;
	location.replace(url);
}
function view_rpt()
{
	var year=document.forms[0].year.value;
	var from_dt=document.forms[0].from_dt.value;
	var to_dt=document.forms[0].to_dt.value;
	var flag=false;
	if(year=='0'){
		flag=true;
		msg="Please Select Year..";
	}if(from_dt==''){
		flag=true;
		msg="from Date field cannot be blank..";
	}if(to_dt==''){
		flag=true;
		msg="To Date field cannot be blank..";
	}
	if(!flag){
	var url="../home/index4.jsp?year="+year+"&st_dt="+from_dt+"&end_dt="+to_dt;
	location.replace(url);
	}else{
		alert(msg);
	}
}
function checkDate()
{
	var fr_dt = document.forms[0].from_dt.value;
	var to_dt = document.forms[0].to_dt.value;
	flag1=true;

	if(fr_dt !='' && to_dt !='')
	{
	   	p = compareDate(fr_dt,to_dt);
		if(p==0)
		{
			
		 }
		 if(p==1)
		{
			alert('From Date should be less than or equal to To Date ');
			document.forms[0].to_dt.value='';
			flag1=flase;
			return flag1;
		}
		if(p==2)
		{
		}
	}
}
function sugg_dtl(title_cd,flag){
	var url="../annexure/rpt_sugg_details.jsp?titcd="+title_cd+"&flag="+flag;
	window.open(url,"viewsugg","top=10,left=70,width=950,height=900,scrollbars=1,status=1,maximize=yes,resizable=1");
}

</script>
<script language="Javascript">
<% if( ((String)session.getAttribute("rem_opened"))==null) { %>
	<% if(((String)session.getAttribute("numberofreminders") !=null)&&(Integer.parseInt((String)session.getAttribute("numberofreminders"))>0)) { %>
		window.open("../rem/rem_main_vwnew.jsp","awin","height=470,width=600,top=100,left=100,scrollbars=1,status=1,resizable=true");
	<% } %>
<% } %>
</script>
</head>

<jsp:useBean class="com.seipl.hazira.dlng.util.LogicBean_LoginAlerter" id="loginalerter" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.admin.DataBean_Admin" id="admin" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_modulelock" id="modlock" scope="request"/>   
<jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_Annexure" id="GetData1" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>

<%
	boolean sessFlg=false;
	String userId = null;
	String user_cd = null;
	String jt_pdf_path = null;
	String invoice_pdf_path = null;
	String daily_allocation_pdf_path = null;
	String nom_transporter_pdf_path = null;
	String nom_customer_pdf_path = null;
	String control_room_seller_nom_pdf_path = null;
	String reconcilation_stmt_pdf_path = null;
	String prov_custom_pdf_path = null; 
	String final_custom_pdf_path = null; //By Achal Pathak ...
	String schedule10_pdf_path = null;
	String mail_path=null;
	String password = "";
	String username ="";
	String userEmail ="";
	String userContactNo ="";
	String val="";
	String passwd="";
	String flg="";
	String status="";
	boolean chk = false;
	String chkVal = "0";
	String tvals = "0";
	tvals = request.getParameter("headval")==null?"0":request.getParameter("headval");
	String counterVal  = request.getParameter("counterVal")==null?"0":request.getParameter("counterVal");
	
	//Fetching Real Path of the pdf_reports directory ...
	jt_pdf_path = request.getSession().getServletContext().getRealPath("/jointTicketPDFReport/pdf_files");//pdf_reports/joint_tickets/pdf_files");
	invoice_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/sales_invoice/pdf_files");
	daily_allocation_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/daily_allocation/pdf_files");
	nom_transporter_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/daily_nomination/pdf_files");
	nom_customer_pdf_path  = request.getSession().getServletContext().getRealPath("/pdf_reports/daily_nomination/pdf_files");
	control_room_seller_nom_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/daily_nomination/pdf_files");
	reconcilation_stmt_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/cargo_finance/pdf_files");
	prov_custom_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/cargo_finance/pdf_files");
	final_custom_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/cargo_finance/pdf_files");
	schedule10_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/mgmt_reports/pdf_files");
	mail_path= request.getSession().getServletContext().getRealPath("/mail");
	
//	System.out.println("jt_pdf_path = "+jt_pdf_path);
	//dmd.init();
	//homeent.setCallFlag("checkNomination");
	//homeent.init();
	
	chkVal=request.getParameter("std")==null?"0":request.getParameter("std");
	String ml_username=(String)session.getAttribute("username");
	String login=request.getParameter("login")==null?"0":request.getParameter("login");
	//System.out.println("LOGGED - ONLINE-USERNAME: " +chkVal+"--ml_username--"+ml_username+"-login--"+login);
	
	
	String ml_temp=request.getParameter("temp");
	String ml_sessionId=session.getId();
	ml_temp="start"; 
	modlock.setSet_username(ml_username);
	modlock.setSessionId(ml_sessionId);
	modlock.setCallFrom(ml_temp);
	modlock.init();
	
    String flag="Y";
    
	boolean userExist = false ;
	String msg = "";
	String msg2 = "";
	HttpServletRequest req = request;
    String server_port = "";
    String server_nm="";
	if(request.getParameter("login") != null)
	{
	   	userId = request.getParameter("login");
	   	password = request.getParameter("password");    	
		String session_id = request.getParameter("sessionID");
		//System.out.println("User Id  " + userId + " " + password +  " " + session_id);
		try
		{
	   	   flag=request.getParameter("check")==null?"":request.getParameter("check");
	   	  // System.out.println("FLAGA: "+flag);
		}
		catch(Exception e)
		{
	flag="N";
	//System.out.println("FLAGB: "+flag);
		}
		
		Cookie ckie[]=new Cookie[2];
	
	    if(flag.equalsIgnoreCase("Y"))
	    {
	ckie[0]=new Cookie("login",userId);
	ckie[1]=new Cookie("password",password);
	response.addCookie(ckie[0]);
	response.addCookie(ckie[1]);
	    }
	    else
	    {
		     ckie[0]=new Cookie("login",null);
		     ckie[1]=new Cookie("password",null);
	 response.addCookie(ckie[0]);
	 response.addCookie(ckie[1]);
	    }
		
		loginalerter.clear_variables();
		loginalerter.setUserId(userId);
		loginalerter.setPassword(password);
		loginalerter.setUname(userId);
		
		loginalerter.setReq(req);
		server_port=""+req.getServerPort();
		//System.out.println("server_port==========::"+server_port);
		
		//String  server_ip = (String)req.getAttribute("ip");
	    server_nm = ""+req.getServerName();
		//System.out.println("server_nm==========::"+server_nm);    
		
		loginalerter.setServer_name(server_nm);
		loginalerter.setServer_port(server_port);
		loginalerter.init();

		user_cd = loginalerter.getUser_CD();
		username = loginalerter.getUserName();
		userEmail = loginalerter.getUserEmail();
		userContactNo = loginalerter.getUserMobNo();
		
		chk = loginalerter.getValiduser();
		userExist = loginalerter.getUserExist();
		
		msg = loginalerter.getMsg();
		
		if(chk)
		{
	chkVal="1";
		}
		else
		{
		  	chkVal="0";
		}
		
		session.setAttribute("user_cd",user_cd);
		session.setAttribute("userid",userId);
		session.setAttribute("username",username);
		session.setAttribute("userContactNo",userContactNo);
		session.setAttribute("Email",userEmail);
		session.setAttribute("pswd", password);
		session.setAttribute("jt_pdf_path",jt_pdf_path);
		session.setAttribute("invoice_pdf_path",invoice_pdf_path);
		session.setAttribute("daily_allocation_pdf_path",daily_allocation_pdf_path);
		session.setAttribute("nom_transporter_pdf_path",nom_transporter_pdf_path);
		session.setAttribute("nom_customer_pdf_path",nom_customer_pdf_path);
		session.setAttribute("control_room_seller_nom_pdf_path",control_room_seller_nom_pdf_path);
		session.setAttribute("reconcilation_stmt_pdf_path",reconcilation_stmt_pdf_path);
		session.setAttribute("prov_custom_pdf_path",prov_custom_pdf_path);
		session.setAttribute("final_custom_pdf_path",final_custom_pdf_path);
		session.setAttribute("schedule10_pdf_path",schedule10_pdf_path);
		session.setAttribute("mail_path",mail_path);
		session.setAttribute("logindate",loginalerter.getToday());
		session.setAttribute("logintime",loginalerter.getTodaytime());
		session.setAttribute("reportListVec",loginalerter.getReportListVec());
		session.setAttribute("accessRightprop",loginalerter.getAccessRightprop());
		session.setAttribute("server_name",server_nm);
		session.setAttribute("server_port",server_port);
		session.setAttribute("loginiduser",login);//RG20190213
		session.setAttribute("passworduser",password);//RG20190213
		
		sessFlg=true;
		String sval = "0";
		session.setAttribute("numberofreminders",sval);
		session.setAttribute("ip",request.getRemoteAddr());
		
	//	System.out.println("SAMIK --> IP = "+request.getRemoteAddr());
		
		Vector temp_reportListVec = new Vector(5,2);
		Properties temp_reportListProp = new Properties();
		Vector reportId = new Vector();
		
		passwd = loginalerter.getNewString();
		flg = loginalerter.getLocked_flg();	
		
		
		InetAddress remoteAddress =null;
		InetAddress inetAddress = InetAddress.getByName(request.getRemoteAddr());
		
		temp_reportListVec=(Vector)loginalerter.getReportListVec();
		
		for(int i=0 ; i<temp_reportListVec.size() ; i++)
		{
	temp_reportListProp = (Properties)temp_reportListVec.elementAt(i);
	reportId.add((String)temp_reportListProp.getProperty("formId"));
		    val="0";
		}
	    
		if(msg.trim().equalsIgnoreCase("Login"))
		{
	msg2 = loginalerter.getMsg2();
	try
	{
		new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: 0@Login Page~: Login");
	}
	catch(Exception infoLogger)
	{
		System.out.println("Exception While Writing into InfoLogger From Login Page:\n"+infoLogger.getMessage());
		infoLogger.printStackTrace();
	}
		}
	}

   	request.getSession(true); 
   	String e = request.getRealPath("/mail");
 	File directory = new File(e);
 	if(!directory.exists())
 	{
 		System.out.println("Directory Created With name \"mail\"");
 		directory.mkdir();
 	}
 	session.removeAttribute("FormName");  //SS20100312
	session.removeAttribute("ModuleName");//SS20100312
	session.removeAttribute("GroupName");//SS20100312
	
	Vector activity_cd = admin.getV_activity_cd();
	String MailMsg = loginalerter.getMailMsg();
	//////////////////For Suggestion/////////////////////
	utilBean.init();
	String cur_year = utilBean.getGet_yr();
	String cur_dt = utilBean.getGen_dt1();
	//String cur_month=utilBean.getGet_mn();
	//String user_cd = "" + session.getAttribute("user_cd"); 
	String LoggedName= ""+session.getAttribute("username");
	//System.out.println("user_cd..."+user_cd+"....LoggedName...."+LoggedName);
	String message=request.getParameter("message")==null?"":request.getParameter("message");
	String year=request.getParameter("year")==null?cur_year:request.getParameter("year");
	String month=request.getParameter("month")==null?"":request.getParameter("month");
	String st_dt=request.getParameter("st_dt")==null?GetData1.getSt_dt():request.getParameter("st_dt");
	String end_dt=request.getParameter("end_dt")==null?GetData1.getEnd_dt():request.getParameter("end_dt");
	
	GetData1.setYear(year);
	GetData1.setDt(st_dt);
	GetData1.setEnddt(end_dt);
	//GetData1.setCur_month(cur_month);
	GetData1.setFlag("Suggestion_Dtls_Summary");
	GetData1.init();
	if(st_dt.equals("")){
		st_dt=GetData1.getSt_dt();
		end_dt=GetData1.getEnd_dt();
	}
	String minyear=GetData1.getMinyear();
	String maxyear=GetData1.getMaxyear();
	Vector Vtitle_cd=GetData1.getVtitle_cd();
	Vector sugg_by_nm=GetData1.getSugg_bynm_rpt();
	Vector sugg_by=GetData1.getSugg_by_rpt();
	Vector Vtitle_nm=GetData1.getVtitle_nm();
	Vector emp_cd_cnt=GetData1.getEmp_act_cnt();
	Map temp_msp=GetData1.getTemp_map();
	Vector VSUGG_BENEFIT=GetData1.getVSUGG_BENEFIT();
	Vector VCOST_REDUCTION  =GetData1.getVCOST_REDUCTION();
	Vector VESTIMATED_SAVING=GetData1.getVESTIMATED_SAVING();
	Vector VSUGG_BRIEF_BENEFIT =GetData1.getVSUGG_BRIEF_BENEFIT();
	Vector Vimpl_cost=GetData1.getVimpl_cost();
	Vector impl_by_days1=GetData1.getImpl_by_days1();
	Vector res_sup=GetData1.getRes_sup();
	Vector Vsugg_dt=GetData1.getVsugg_dt();
	NumberFormat nf1=new DecimalFormat("0.#");
	NumberFormat nf2 = new DecimalFormat("###########0.00");
	if(year.equals("0")){
		st_dt=cur_dt;
		//end_dt=cur_dt;
	}
%>

<!-- <body style="background-image:url('..\images\prototype1.gif')" onLoad="checkVal('<%=userExist%>','<%=chkVal%>','<%=flg%>','<%=counterVal%>','<%=msg2%>','<%=activity_cd.size()%>');">-->
<body style="background-image:" onLoad="">
<form name="mainview" method="post" action="../servlet/Frm_Grade">
<%@ include file="../home/header.jsp"%>

<div class="header">		
<div id="col-three">
<table width="100%"  align="center" >
<br>
		<tr  class="content1"  style="background-color: #FBCE07">
			<td width="55%" style="border-bottom: 3px solid #DD1D21;" align="left"><font color="#853F62" style="font-family: arial;" size="3">
				&nbsp;&nbsp;<b>Dear Guest, Welcome to Hazira Sahayog System</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b>SUGGESTION LIST</b></font></td>
<!-- 			<td width="10%" align="right" >&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="excel_butn" value="Export To Excel" onclick="To_Excel();"></td> -->
		</tr>
	</table>
	<table  width="100%"  align="center" >
		<tr class="title2" style="background-color: #FBCE07">
		<td width="7%"  style="border-bottom: 3px solid #DD1D21;"><font color="#853F62" style="font-family: arial;" ><b> &nbsp;Year :</b> </font>
						&nbsp;&nbsp;&nbsp;
						<select name="year" onchange="change_month();">
						<option value="0">--Select--</option>
							<%for(int i=Integer.parseInt(minyear);i<Integer.parseInt(cur_year)+1;i++){ %>
							<% %>
							<option value="<%=i %>"><%=i %></option>
							<%} %>	
							</select>
							<script>
									document.forms[0].year.value='<%=year%>';
							</script>
						</td>
<!-- 						<td  width="20%" align="left" style="padding-left:20px;border-bottom: 3px solid #DD1D21; "><font color="#853F62" style="font-family: arial;" > <b>From Date:</b></font> &nbsp; -->
<%-- 	 						<input type="text" size="10" maxlength="10" name="from_dt" value="<%=st_dt %>" border="0"  --%>
<!-- 	 						title="Please Insert Start Date In DD/MM/YYYY Format" > -->
<!-- 	   						<a name=""  href="javascript:show_calendar('forms[0].from_dt');" title="Date Picker" onblur="checkDate();" -->
<!-- 						onMouseOver="window.status='Date Picker';return true;"  -->
<!-- 						onMouseOut="window.status='';return true;"> -->
<!-- 	        			<img src="../images/icon_gen_calendar.gif" width="22" height="22" border="0" alt=""></a>  -->
<!-- 	       				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     				 -->
<!-- 	       				<font color="#853F62" style="font-family: arial;" > <b>To Date:</b></font> &nbsp; -->
<%-- 	 						<input type="text" size="10" maxlength="10" name="to_dt" value="<%=end_dt %>" border="0"  --%>
<!-- 	 						title="Please Insert Start Date In DD/MM/YYYY Format" > -->
<!-- 	   						<a name=""  href="javascript:show_calendar('forms[0].to_dt');" title="Date Picker" onblur="checkDate();" -->
<!-- 						onMouseOver="window.status='Date Picker';return true;"  -->
<!-- 						onMouseOut="window.status='';return true;"> -->
<!-- 	        			<img src="../images/icon_gen_calendar.gif" width="22" height="22" border="0" alt=""></a>  -->
<!-- 	       				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 	       				 <input type="button" name="btn1" value="View List" onclick="view_rpt();" style="font-family: sans-serif;font-weight: bold;"></td> -->
	       				 
	       				 <td width="20%" align="left" style="padding-left:20px;border-bottom: 3px solid #DD1D21; "><font color="#853F62" style="font-family: arial;" > <b>From Date:</b></font> &nbsp;
 						<input type="text" size="10" maxlength="10" name="from_dt" value="<%=st_dt %>" border="0" 
 						title="Please Insert Start Date In DD/MM/YYYY Format" onblur="if(validateDate(this)){}; checkDate();" >
   						<a name=""  href="javascript:show_calendar('forms[0].from_dt');" title="Date Picker" onblur="checkDate();"
					onMouseOver="window.status='Date Picker';return true;" 
					onMouseOut="window.status='';return true;">
        			<img src="../images/icon_gen_calendar.gif" width="22" height="22" border="0" alt=""></a> 
       				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     				
       				<font color="#853F62" style="font-family: arial;" > <b>To Date:</b></font> &nbsp;
 						<input type="text" size="10" maxlength="10" name="to_dt" value="<%=end_dt %>" border="0" 
 						title="Please Insert Start Date In DD/MM/YYYY Format" onblur="if(validateDate(this)){}; checkDate();" >
   						<a name=""  href="javascript:show_calendar('forms[0].to_dt');" title="Date Picker" onblur="checkDate();"
					onMouseOver="window.status='Date Picker';return true;" 
					onMouseOut="window.status='';return true;">
        			<img src="../images/icon_gen_calendar.gif" width="22" height="22" border="0" alt=""></a> 
       				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       				 <input type="button" name="btn1" value="View List" onclick="view_rpt();"></td>
		</tr>
		</table>
		<%if(Vtitle_cd.size()>0){ %>
		<table>
		
			<tr class="title2" style="background-color: #FBCE07">
				<td WIDTH="3%"><font color="maroon">SR No.</font></td>
				<td WIDTH="15%"><font color="maroon">Employee Name</font></td>
				<td><font color="maroon">Suggestion No.</font></td>
				<td><font color="maroon">Suggestion Date.</font></td>
				<td><font color="maroon">Suggestion<br>Title</font></td>
				<td><font color="maroon">Benefit related to</font></td>
				<td><font color="maroon">Likely cost of<br>implementation<br>[INR]</font></td>
				<td><font color="maroon">HOW LONG IT WILL TAKE TO IMPLEMENT<br>[MONTH(S)]</font></td>
				<td><font color="maroon">WILL COST<br>REDUCTION<br>BE ACHIEVED</font></td>
				<td><font color="maroon">ESTIMATED SAVING<br>[INR]</font> </td>
				<td><font color="maroon">FROM NOW, BENEFITS EXPECTED AFTER </font></td>
				<td><font color="maroon">RESPONSIBLE SUPERVISOR</font></td>
			</tr>
		<%int k=0,j=0;int sr_no=1; %>
		<%String temp_var=""; %>
		<%//System.out.println("sugg_by--"+sugg_by);
		for(int i=0;i<sugg_by.size();i++){
			String abc=temp_msp.get(sugg_by.elementAt(i))+"";%>
				<tr class="content1">
				<%if(!sugg_by.elementAt(i).equals(temp_var)){ %>
				<td rowspan="<%=abc %>"  align="center" title=""><font size="2px" >&nbsp;<%=j+1 %></font></td>
				<td rowspan="<%=abc %>"  align="left" title="Employee Name"><font size="2px" >&nbsp;<%=sugg_by_nm.elementAt(i) %></font></td>
				<%j++;} %>
				<td rowspan="1" colspan="1" title="Suggestion Code" align="center">
					<font size="2px" >&nbsp;<a href="#" title="Select To View" onclick="sugg_dtl('<%=Vtitle_cd.elementAt(i) %>','Y')"><%=Vtitle_cd.elementAt(i)%></a></font>
				</td>
				<td rowspan="1" colspan="1" title="Suggestion Date" align="center">
					<font size="2px" >&nbsp;<%=Vsugg_dt.elementAt(i)%></font>
				</td>
				<td rowspan="1" colspan="1" style="padding-left: 3px;" title="Title name" align="left">
					<font size="2px" ><%=Vtitle_nm.elementAt(i)%></font>
				</td>
				<td rowspan="1" colspan="1" style="padding-left: 3px;" title="benefits of suggestion" align="left">
					<font size="2px" ><%=VSUGG_BENEFIT.elementAt(i)%></font>
				</td>
				<td rowspan="1" colspan="1" title="likely Cost of implementing Suggestion" align="right">
					<%String vcost="";
					if(Vimpl_cost.elementAt(i).equals("")){
						 vcost="0.00";
					}else{
						vcost=nf2.format(Double.parseDouble(Vimpl_cost.elementAt(i)+""));
					}%>
					<font size="2px" ><%=vcost%>&nbsp;</font>
				</td>
				<td rowspan="1" colspan="1" title="Timeline for implementing suggestion" align="right">
					<%String vdays="";
					if(impl_by_days1.elementAt(i).equals("")){
						vdays="0.0";
					}else{
						vdays=nf1.format(Double.parseDouble(impl_by_days1.elementAt(i)+""));
					}%>
					<font size="2px" ><%=vdays%>&nbsp;</font>
				</td>
				<td rowspan="1" colspan="1" title="will cost reduction be achieved" align="center">
					<font size="2px" >&nbsp;<%=VCOST_REDUCTION.elementAt(i)%></font>
				</td>
				<td rowspan="1" colspan="1" title="Estimated Savings by implementing this suggestion" align="right">
				<%String vest_sv="";
					if(VESTIMATED_SAVING.elementAt(i).equals("")){
						vest_sv="0.00";
					}else{
						vest_sv=nf2.format(Double.parseDouble(VESTIMATED_SAVING.elementAt(i)+""));
					}%>
					<font size="2px" ><%=vest_sv%>&nbsp;</font>
				</td>
					<td rowspan="1" colspan="1" title="Benefits Expected post implementation" align="center">
					<font size="2px" >&nbsp;<%=VSUGG_BRIEF_BENEFIT.elementAt(i)%></font>
				</td>
				<td rowspan="1" colspan="1" title="Supervisor" align="left">
					<font size="2px" >&nbsp;<%=res_sup.elementAt(i)%></font>
				</td>
				 
				<%temp_var=sugg_by.elementAt(i)+"";%>
				
				 </tr>
				
			<%} %>
			 <tr style="background-color: #E0EEE0;"><td colspan="12" align="left">&nbsp;<font color="maroon" size="2"><b>For more information on the System, contact Ashish.N.Shah@Shell.com &nbsp;&nbsp;&nbsp;&nbsp;
			 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  Total No of Suggestions : <%=Vtitle_cd.size() %></b></font></td></tr>
		</table>
		<%}else{ %>
			<table width="100%"  align="center" >
				<tr  class="title2" style="background-color: #E0EEE0;">
				<td width="35%" align="center" ><font size="3px"><b>...Data Not Available...</b></font></td>
				</tr>
			</table>
		<%} %>	

				   <%//System.out.println("in here"); %>
<h5><%=MailMsg%></h5>
</div>	
</div>
<input type="hidden" name="option" value="Save_Sup_Grade">
<input type="hidden" name="username" value="<%=user_cd%>">
<input type="hidden" name="msg" value="<%=msg%>">

<div id="loading" style="visibility: hidden;">
  <img id="loading-image" style="visibility: hidden;" src="../images/indicator.gif" alt="Loading..." width="30" height="30" />
</div>
</form>
<%@ include file="../sess/Expire.jsp" %>
</body>
</html>
