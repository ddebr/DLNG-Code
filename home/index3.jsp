<%-- <%@ page errorPage="../home/ExceptionHandler.jsp" %> --%>
<%@ page import ="java.util.Properties" %>
<%@ page import ="java.util.*" %>
<%@ page import ="java.net.*" %>
<%-- <%@ page import ="com.ats.hlpl.hazira.home.*" %> --%>
<%@ page import ="java.io.*" %>
<html>


<%--<script language="JavaScript" src="../js/date-picker.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>
<script language="JavaScript" src="../js/nhv.js"></script>--%>

<script language="JavaScript" src="../js/backspace.js"></script>

<!-- RG20190207  For voting system-->

<!-- <style>
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
</style> -->
<!--  -->
<script language="JavaScript">
$(document).ready(function()
		{
				var y = "#accordion1";
					$(y).accordion({
						collapsible: true
					});
					
		});
function checkVal(userExist,userValidity,flg,counterVal,msg2,sz)
{
	var msg = document.forms[0].msg.value;
// 	alert(msg)
	var size = parseInt(""+sz);
// 	alert("userExist = "+userExist+",  userValidity = "+userValidity+",  flg = "+flg+",  counterVal = "+counterVal+",  msg = "+msg);
	
	if((userValidity == "1") && (flg =="Y"))
	{ 
		var loginname = document.forms[0].username.value;
		location.replace("errorInLogin.jsp?login="+loginname);
	}
	else
	{
	    if(userValidity == "1")
	    {
	    	if(msg2!=null && msg2!='')
	    	{
	    		if(msg2.length>28)
	    		{
	    			var message = msg2.substr(0,28);
					if(message == "Your Password will Expire on")
					{
						alert(msg2);
					}
				}
			}  
		}
		else if(userExist == "true")
		{
			counterVal = parseInt(counterVal) + 1;
			var url = "";
			
			if(msg == "In-Active User Not Permitted to Log-In into the System !!!")
			{
				var loginname = document.forms[0].username.value;
				url = "errorInLogin.jsp?login="+loginname+"&msg="+msg;
			}
			else if(msg == "Your Login has been locked, Kindly contact your Administrator !!!")
			{
				var loginname = document.forms[0].username.value;
				url = "errorInLogin.jsp?login="+loginname+"&msg="+msg;
			}
			else if(msg == "You need to change your PASSWORD compulsary, As your password has been RESET ...")
			{
				var loginname = document.forms[0].username.value;
				url = "frm_admin_pass.jsp?status="+msg;
			}
			else if(msg == "You need to change your PASSWORD compulsary, As your password has EXPIRED ...")
			{
				var loginname = document.forms[0].username.value;
				url = "frm_admin_pass.jsp?status="+msg;
			}
			else
			{
				var val = "Invalid UserId/Password";
				url = "login.jsp?comb="+val+"&counterVal="+counterVal;
			}
			location.replace(url);
		}
		else
		{ 
	 		counterVal = parseInt(counterVal) + 1;
	 		var val = "Invalid UserId/Password";
            var url = "login.jsp?comb="+val+"&counterVal="+counterVal;
			location.replace(url); 
		}
		
		if((counterVal == 3) && (userExist == "true") && (userValidity =="0"))
		{
			var loginname = document.forms[0].username.value;
			location.replace("errorInLogin.jsp?login="+loginname+"&msg="+msg);
			//document.forms[0].b1.disabled=true
		}
	}
	
	if(size>0)
	{
		var obj = document.forms[0].activity_cd;
	 	var obj2 = document.forms[0].hideshow;
		
		var len = parseInt("0");
		var len2 = parseInt("0");
		
		len = obj.length;
		len2 = obj2.length;
		
		if(len==1)
	 	{
		  	var obj3 = document.getElementById(obj.value);
		  	
		  	if(document.forms[0].hideshow.value=="SHOW")
		  	{
		   		if(obj3 != null)
				{
			   		obj3.style.visibility = "visible";
			   		if (obj3.style.display == '')
			   		{
						obj3.style.display = 'none';
					}
					else 
						obj3.style.display = '';
				}	   
		  		document.forms[0].hideshow.value="HIDE";
		  	}
		  	else if(document.forms[0].hideshow.value=="HIDE")
		  	{
		  		if(obj3 != null)
				{
		    		if(obj3.style.display == '')
		    		{
						obj3.style.display = 'none';
					}
					else 
						obj3.style.display = '';
				}
		   		document.forms[0].hideshow.value="SHOW";
		 	}
		 }
		 else if(len>1)
		 {
		 	for(var i=0; i<len; i++)
		 	{
			 	var obj3 = document.getElementById(obj[i].value);
		  		//alert("obj["+i+"].value  =  "+obj[i].value);
		  		//alert("obj3  =  "+obj3);
			 	if(document.forms[0].hideshow[i].value=="SHOW")
				{
					if(obj3 != null)
				  	{
						obj3.style.visibility = "visible";
						if (obj3.style.display == '')
						{
							obj3.style.display = 'none';
						}
						else 
							obj3.style.display = '';
					}			   
				  	document.forms[0].hideshow[i].value="HIDE";
				}
				else if(document.forms[0].hideshow[i].value=="HIDE")
				{
					if(obj3 != null)
				  	{
					    if(obj3.style.display == '')
					    {
							obj3.style.display = 'none';
						}
						else 
							obj3.style.display = '';
					}
				   	document.forms[0].hideshow[i].value="SHOW";
				}
			}
		}
	}	
}
function applycolor(i)
{
	var len=document.forms[0].catcd_sz.value;
	if(document.getElementById("check"+i).checked){
		document.getElementById("row"+i).style.background="#8BCDEF";
		for(var k=0;k<len;k++){
			document.getElementById("check_cat"+i+k).disabled=false;
		}
	}else{
		document.getElementById("row"+i).style.background="#EDEDED";
		for(var k=0;k<len;k++){
			document.getElementById("check_cat"+i+k).disabled=true;
			document.getElementById("check_cat"+i+k).checked=false;
			document.getElementById("check_text"+i+k).value='';
			document.getElementById("check_text"+i+k).disabled=true;
		}
	}
}
function Reset_Grade(sup){
	document.forms[0].supcd_hid.value=sup;
	//alert(sup);
	var a = confirm("Do You Want To Remove?\n Note :- All your voting for this supervisor will be removed");
	if(a)
	{
		document.forms[0].option.value="Delete_grade";
		document.getElementById("loading").style.visibility = "visible";
		document.getElementById("loading-image").style.visibility = "visible";
		document.forms[0].submit();
	}
}
function Save_data(){
	var len=document.forms[0].check.length;
	var catlen=document.forms[0].catcd_sz.value;
	var cnt=0,cat_cnt=0,text_cnt=0,cnt3=0;
	var flag=true;
	var msg = "Following Fields To Be Filled Properly :\n";
	for(var i=0;i<len;i++){
		if(document.getElementById("check"+i).checked==true){
			document.getElementById("check"+i).value='Y';
			document.getElementById("supcd_flag"+i).value='Y';
		}else {
			document.getElementById("check"+i).value='';
			document.getElementById("supcd_flag"+i).value='N';
			cnt++;
		}
	}
	if(cnt==len){
		msg+="Please Select atleast one Supervisor\n";
		flag=false;
	}else{
		for(var i=0;i<len;i++){
			cat_cnt=0;
			if(document.getElementById("check"+i).checked==true){
				for(var k=0;k<catlen;k++){
					if(document.getElementById("check_cat"+i+k).checked==true){
						document.getElementById("check_cat"+i+k).value='Y';
						if(document.getElementById("check_text"+i+k).value==''){
							text_cnt++;
						}
					}else{
						document.getElementById("check_cat"+i+k).value='';
						cat_cnt++;
					}
				}
				if(cat_cnt==catlen){
					msg+="Check Atleast One Categeory for supervisor at Row - "+(i+1)+"\n"
					flag=false;
				}else{
					if(text_cnt>0){
						msg+="Enter Value For Selected Category for supervisor at Row - "+(i+1)+"\n";
						flag=false;
					}
				}
			}
		}
		
	}
	if(flag){
		var a = confirm("Do You Want To Submit?");
		if(a)
		{
			document.getElementById("loading").style.visibility = "visible";
			document.getElementById("loading-image").style.visibility = "visible";
			document.forms[0].save.disabled=true;
			document.forms[0].submit();
		}
	}else{
		alert(msg);
	}
}
function Check_Category(i,k){
	if(document.getElementById("check_cat"+i+k).checked==true){
		document.getElementById("check_text"+i+k).disabled=false;
	}else{
		document.getElementById("check_text"+i+k).value="";
		document.getElementById("check_text"+i+k).disabled=true;
	}
}
function displayData(activity_nm,ind)
{  
 	var obj = document.getElementById("supervisor_list");
 	
	  	if(document.forms[0].hideshow.value=="Click Here To Vote")
	  	{
	   		if(obj.style.visibility=="hidden")
			{
	   			obj.style.visibility = "visible";
	   			if(obj.style.display == '')
	   			{
					obj.style.display = 'block';
				}
				else 
					obj.style.display = '';
			}	   
	  		document.forms[0].hideshow.value="HIDE";
	  	}
	  	else if(document.forms[0].hideshow.value=="HIDE")
	  	{
	    	if(obj.style.visibility=="visible")
			{
		    	if (obj.style.display == 'block')
		    	{
					obj.style.display = 'none';
				}
				else 
					obj.style.display = '';
			}
	   		document.forms[0].hideshow.value="SHOW";
	 	}
	 
	 
}

	/*
function checkVal(userExist,userValidity,flg sts)
{

  if(sts=="1")
  {
 
  }
  else
  {
    var val="Invalid UserId/Password";
    var url="index1.jsp?comb="+val+"";
	location.replace(url);
  }

}*/
function if_tab(key_val) {
//	if (event.keyCode == 9) {
	//	alert("TAB was pressed");
	//}
//	alert(event.keyCode);
	if(event.keyCode==112)
	{
	  url="../UserManual/index2.htm";
      window.open(url,"popup","top=0,left=0,toolbars=no,maximize=yes,width=1100,height=1100,resize=no,location=no,directories=no,scrollbars=yes");
	}
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
<%-- <jsp:useBean class="com.ats.hlpl.hazira.grade.DataBean_grade" id="dta" scope="page"/> --%>
<%
	boolean sessFlg=false;
	String userId = null;
	String user_cd = null;
	String jt_pdf_path = null;
	String invoice_pdf_path = null;
	String advance_invoice_pdf_path=null;
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
	String advance_pdf_path = null; //Hiren_20211002
// 	System.out.println("index3 ");
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
// 	request request = request;
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
		
		loginalerter.setReq(request);
		server_port=""+request.getServerPort();
		//System.out.println("server_port==========::"+server_port);
		
		//String  server_ip = (String)request.getAttribute("ip");
	    server_nm = ""+request.getServerName();
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
		
		invoice_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/pdf_files");
    	//Fetching Real Path of the pdf_reports directory ...
    	jt_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/joint_tickets/pdf_files");//jointTicketPDFReport/pdf_files");
    	invoice_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/pdf_files");
    	advance_invoice_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/advance_invoice/pdf_files");
    	daily_allocation_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/daily_allocation/pdf_files");
    	nom_transporter_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/daily_nomination/pdf_files");
    	nom_customer_pdf_path  = request.getSession().getServletContext().getRealPath("/pdf_reports/daily_nomination/pdf_files");
    	control_room_seller_nom_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/daily_nomination/pdf_files");
    	reconcilation_stmt_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/cargo_finance/pdf_files");
    	prov_custom_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/cargo_finance/pdf_files");
    	final_custom_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/cargo_finance/pdf_files");
    	schedule10_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/mgmt_reports/pdf_files");
    	mail_path= request.getSession().getServletContext().getRealPath("/mail");
    	double convt_mmbtu_to_mt = 51.5; //Hiren_20201229 as customer req.
    	advance_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/advance_advice/pdf_files");
    	String late_payment_invoice_pdf_path = request.getSession().getServletContext().getRealPath("/pdf_reports/sales_invoice/late_pay_invoice");

//     	String comptempnm="(Formerly known as Hazira LNG Private Limited)";//Hiren_20201112
		String comptempnm="";//Hiren_20210901
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
		
		session.setAttribute("tempcompnm",comptempnm);//Hiren_20201112
		session.setAttribute("convt_mmbtu_to_mt",convt_mmbtu_to_mt);//Hiren_20201229
		session.setAttribute("advance_pdf_path",advance_pdf_path);
		session.setAttribute("late_payment_invoice_pdf_path",late_payment_invoice_pdf_path);

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
	
	//RG20190207////////////////// FOR VOTING SYSTEM///////
	String message_sub=request.getParameter("message_sub")==null?"":request.getParameter("message_sub");
	String form_cd = request.getParameter("form_cd")==null?"":request.getParameter("form_cd");
	String form_name = request.getParameter("form_name")==null?"":request.getParameter("form_name");
	/* dta.setUser_cd(user_cd);
	dta.setFlag("Fetch_Supervisor_Dtls");
	dta.init();
	Vector sup_cd=dta.getSupervisor_CD();
	Vector sup_nm=dta.getSupervisor_NM();
	Vector Vcat_cd=dta.getVcat_cd();
	Vector Vcat_nm=dta.getVcat_nm();
	String vote_sys_mnth=dta.getVote_sys_mnth();
	String month=dta.getMonth();
	Integer cur_yr=dta.getCurr_yr();
	Vector remarks=dta.getRemarkflag();
	Vector Sup_flag=dta.getSup_flag();
	Integer year=dta.getYr(); */
// 	System.out.println("remarks.."+remarks);
	
%>

<body  onload="checkVal('<%=userExist%>','<%=chkVal%>','<%=flg%>','<%=counterVal%>','<%=msg2%>','<%=activity_cd.size()%>');">
<%if(chkVal.equals("1") ){ %>

<%@ include file="../home/header.jsp"%>
<%} %>
<form name="mainview" method="post" >

<%-- <%
	 String form_id = "0";
	String form_nm = "AWESOME TL";
	for(int j=0; j<FORM_CD.size(); j++)
	{
		if((""+FORM_NAME.elementAt(j)).trim().equalsIgnoreCase("AWESOME TL"))
		{
			form_id = ""+FORM_CD.elementAt(j);
			form_nm = "AWESOME TL";
		}
	} 
// 	System.out.println("form_id = "+form_id);
// 	System.out.println("form_nm = "+form_nm);
%>
<input type="hidden" name="form_id" value="<%=form_id%>">
<input type="hidden" name="form_nm" value="<%=form_nm%>"> --%>



<input type="hidden" name="option" value="Save_Sup_Grade">
<input type="hidden" name="username" value="<%=user_cd%>">
<input type="hidden" name="msg" value="<%=msg%>">
<%-- <input type="hidden" name="catcd_sz" value="<%=Vcat_cd.size()%>"> --%>
<input type="hidden" name="loginhid" value="<%=login%>">
<input type="hidden" name="passwordhid" value="<%=password%>">
<%-- <input type="hidden" name="month_hid" value="<%=month%>"> --%>
<input type="hidden" name="supcd_hid" value="">
<%-- <input type="hidden" name="year_hid" value="<%=year%>"> --%>

<div id="loading" style="visibility: hidden;">
  <img id="loading-image" style="visibility: hidden;" src="../images/indicator.gif" alt="Loading..." width="30" height="30" />
</div>
</form>
<%-- <%@ include file="../sess/Expire.jsp" %> --%>
</body>
</html>
