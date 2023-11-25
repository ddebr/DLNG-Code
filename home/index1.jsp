<!DOCTYPE html>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ page import="java.util.*,java.lang.String" %>
<html>

<head>
	<title>TLU</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="../images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="../vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<!-- 	<link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css"> -->
<!--===============================================================================================-->	
<!-- 	<link rel="stylesheet" type="text/css" href="../vendor/daterangepicker/daterangepicker.css"> -->
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="../css/util.css">
	<link rel="stylesheet" type="text/css" href="../css/main.css">
<!--===============================================================================================-->
</head>



<script type="text/javascript" src="../js/fsmenu.js"></script>


<script>
function set()
{
	document.forms[0].password.value = "";
	document.forms[0].login.value = "";
}

function setCookies(login,password,chk,countval)
{
	if(countval==3)
  	{
  		alert("user has been locked");
  	}
  	if(chk=="0")
  	{
    	//document.forms[0].check.checked=false;
      	document.forms[0].login.value="";
	  	document.forms[0].password.value="";
  	}
  	else
  	{
	  	document.forms[0].login.value=login;
	  	document.forms[0].password.value=password;
	 	/*
	  	if(document.forms[0].login.value!=""||document.forms[0].login.value!=" ")
	  	{
	    	document.forms[0].check.checked=true;
	  	}
	  	else
	  	{
	      	document.forms[0].check.checked=false;
	  	}*/
  	}
  	document.forms[0].login.focus();
}


var counterVal=0;
function checkVal(counterVal) 
{
	var elem=document.forms[0].elements;
  	var flag=true;
  	var loginid=document.forms[0].login.value;
  	var val=loginid.toUpperCase();
  //	alert(val);
  	if(val=="GUEST"){
  	}else{
  
	  	for(i=0; i<elem.length;i++)
	  	{
	    	if(elem[i].type=='text'  ||  elem[i].type=='password')
	    	{
	       		if(elem[i].value=="" || elem[i].value==" ")
	       		{
	           		flag=false;       
	       		}
	    	}
	  	}
  	}

  	if(flag)
  	{
 			if(val=="GUEST"){
 				var valcount="1";
      		document.forms[0].action="index4.jsp?counterVal="+valcount+"&std="+valcount;
 			}else{
 				document.forms[0].action="index3.jsp?counterVal="+counterVal;
 			}
      	//document.forms[0].submit();
  	}
  	else
  	{
    	alert("Entering Login and Password is Mandatory.");
      	document.forms[0].login.value="";
      	document.forms[0].password.value="";
      	document.forms[0].login.focus();
      	return false;
  	}    
}

function checkEnter(e){ //e is event object passed from function invocation
		var characterCode; //literal character code will be stored in this variable
		
		if(e && e.which){ //if which property of event object is supported (NN4)
				e = e
				characterCode = e.which //character code is contained in NN4's which property
		}
		else{
				e = event
				characterCode = e.keyCode //character code is contained in IE's keyCode property
		}
		
		if(characterCode == 13){ //if generated character code is equal to ascii 13 (if enter key)
					//document.forms[0].submit() //submit the form
				var counterVal = document.forms[0].counterval.value;
				//checkVal(counterVal);
				return false; 
		}
			else{
			return true; 
		}
}

/* function checkstatus()
{
	//url ="../servlet/Check";
    document.forms[0].action="../servlet/Check";
	document.forms[0].submit();          
} */
function dsable_enable_pwd(val){
	if(val.toUpperCase()=="GUEST"){
		document.forms[0].password.value='';
		document.forms[0].password.disabled=true;
	}else{
		document.forms[0].password.disabled=false;
	}
}

var newWindow5;
function forgot() {
	
	  url="../home/fms9reset.jsp";
	if(!newWindow5 || newWindow5.closed)
		{
		newWindow5 = window.open(url,"popup","top=30,left=30,toolbars=no,maximize=yes,width=800,height=400,resize=no,location=no,directories=no,scrollbars=yes");
	}
	else
	{
		newWindow5.close();
		newWindow5 = window.open(url,"popup","top=30,left=30,toolbars=no,maximize=yes,width=800,height=400,resize=no,location=no,directories=no,scrollbars=yes");
	}
	
}
</script>

</head>

<jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_modulelock" id="modlock" scope="request"/>
<%
	String value=request.getParameter("val")==null?"0":request.getParameter("val");
	String counterVal =request.getParameter("counterVal")==null?"0":request.getParameter("counterVal");
    if(value.equalsIgnoreCase("1"))
    {
    	String ml_username=(String)session.getAttribute("username");
    	String ml_sessionId=session.getId();
    	String ml_temp="finish";
    	try
		{
	new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: 0@Logout Page~: Logout");
		}
		catch(Exception infoLogger)
		{
	System.out.println("Exception While Logout ... \n"+infoLogger.getMessage());
	infoLogger.printStackTrace();
		}
        modlock.setSet_username(ml_username);
    	modlock.setSessionId(ml_sessionId);
    	modlock.setCallFrom(ml_temp);
    	modlock.init();
    	session.invalidate(); 	
    }
    
    String userId = null;
	String password = "";
	String username ="";
	
    Cookie cks[]=request.getCookies();
    String chk="0";
    for(int i=0;i<cks.length;i++){
   	 if(cks[i].getName().equalsIgnoreCase("login")){
   		 userId=cks[i].getValue();
   		 if(userId==null||userId.equalsIgnoreCase("null")){
   			chk="0"; 
   		 }else{
   		     chk="1";
   		 }
   	 }
   	 if(cks[i].getName().equalsIgnoreCase("password")){
   		 password=cks[i].getValue();
   	 }
    }

    String comb=null; 
	
	String validity=request.getParameter("validity")==null?"":request.getParameter("validity");
	String sessmsg=request.getParameter("sessmsg")==null?"":request.getParameter("sessmsg");
	comb=request.getParameter("comb")==null?"":request.getParameter("comb");
	String status="";
	
	/* dta.setFlag("birthday_mail_pic_birthday_emp");
	dta.setRequest(request);
	dta.init();
	Vector emp_nm=dta.getEmp_nm();
	Vector emp_email_id=dta.getEmp_email_id();
	Vector emp_cd1=dta.getEmp_cd();
	Vector emp_image_cd=dta.getEmp_image_cd(); */
// 	System.out.println("in index 11111");
%>
<body onLoad="document.forms[0].login.focus();setCookies('<%=userId%>','<%=password%>','<%=chk%>','<%=counterVal%>');">
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form p-l-55 p-r-55 p-t-178"  style="background-color: #FEFCEF;" method="post" id="logIn"  >
<!-- 					<span class="login100-form-title" style="background-color: #4CA6A6;"> -->
					<span class="login100-form-title" style="background-color: #fbce07;font-size: 25px;">				
				         <img src="../images/logo.png" />		         
						DLNG - Truck Loading Unit
<!-- 				         <img src="../images/logo.png" /> -->
					</span>
					<%if(!sessmsg.equals("")) {%>
	                   <div class="control-group" style="text-align: right;">
	                   	<label style="color: red;"><%=sessmsg %></label>
	                  </div>
			          <%} %>
					<div class="wrap-input100 validate-input m-b-16" data-validate="Please enter username">
						<input class="input100" type="text" name="login" placeholder="Username" maxlength="20" onKeyPress="checkEnter(event);" onblur="dsable_enable_pwd(this.value);">
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "Please enter password">
						<input class="input100" type="password" name="password" placeholder="Password" maxlength="20" onKeyPress="checkEnter(event);" >
						<span class="focus-input100"></span>
					</div>
					
					 <div class="text-right p-t-13 p-b-23"></div>
					

					<div class="container-login100-form-btn">
						<button class="login100-form-btn" style="background-color: #fbce07;" onclick="checkVal('<%=counterVal%>');"  >
							Sign in
						</button>
					</div>
				<div class="flex-col-c p-t-50 p-b-40">
						 <span class="txt1 p-b-9">
							Forgot Password ?
						</span>

						<a href="#" onclick="forgot();" class="txt3">
							<u>Reset now</u>
						</a> 
					</div> 
<!-- 		 <div class="text-right p-t-50 p-b-100"></div> -->
		 <%if(comb != null)
			{%>
			  		<div class="control-group" style="text-align: right;">
	                   	<label style="color: red;"><%=comb %></label>
	                  </div>
		<%}%>
				
				
		
					
					<input type="hidden" name="status" value="<%=status%>" />
					<input type="hidden" name="loginhid" value="" />
					<input type="hidden" name="counterval" value="<%=counterVal%>" />					
				</form>
			</div>
		</div>
	</div>
	
	
<!--===============================================================================================-->
	<script src="../vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="../vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="../vendor/bootstrap/js/popper.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<!-- 	<script src="../vendor/select2/select2.min.js"></script> -->
<!--===============================================================================================-->
<!-- 	<script src="../vendor/daterangepicker/moment.min.js"></script> -->
<!-- 	<script src="../vendor/daterangepicker/daterangepicker.js"></script> -->
<!--===============================================================================================-->
<!-- 	<script src="../vendor/countdowntime/countdowntime.js"></script> -->
<!--===============================================================================================-->
	<script src="../js/main.js"></script>

</body>

</html>