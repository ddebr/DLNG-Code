<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Customer Invoice Details</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <script src="../js/jquery.min.js"></script> -->
<!--  <script src="../bootstrap/dist/js/bootstrap.min.js"></script> -->

</head>
<style>
		.subbutton {
		  background-color: #4CAF50; /* Green */
		  border: none;
		  color: white;
		  padding: 10px;
		  text-align: center;
		  text-decoration: none;
		  display: inline-block;
		  font-size: 16px;
		  margin: 4px 2px;
		  cursor: pointer;
		}
		
		.retrbutton {
		  background-color: #e29f4b; /* jaadi */
		  border: none;
		  color: white;
		  padding: 10px;
		  text-align: center;
		  text-decoration: none;
		  display: inline-block;
		  font-size: 16px;
		  margin: 4px 2px;
		  cursor: pointer;
		}
		
		.button1 {border-radius: 2px;}
		.button2 {border-radius: 2px; background-color:blue; }
		.button3 {border-radius: 8px;}
		.button4 {border-radius: 12px;}
		.button5 {border-radius: 50%;}
		</style>
<script>
	function create_pdf(){
		var url="../PDF_signer.jar";
		window.open(url,"actionReport","top=10,left=70,width=950,height=900,scrollbars=1,status=1,maximize=yes,resizable=1");
		//location.replace(url);
		
	}
	function click_home_page(login,pwd){
// 		alert("in func");
// 		alert("--login-"+login);
		var std="1";
		var url="../home/index3.jsp?std="+std+"&login="+login+"&password="+pwd;
		location.replace(url);
	} 
</script>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<%
util.init();
String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");

%>
<body>
<div class="container" align="center">
  <h2 align="center"><b>Digital Signature</b></h2>
  <div class="well well-lg">
  	<table>
			<tr>
				<td colspan="2"><font size="4"><b>Continue To Digitally Sign Document , Click To Accept/Decline</b></font></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr align="center">
<!-- 				<td align="center"><input type="radio" name="rd" value="" onclick="create_pdf();"> <b> &nbsp;&nbsp;I Accept</b></td> -->
					<td align="center">
					<input type="button" class="subbutton button4" name="Submit" value="I Accept" onclick="create_pdf();">
					</td>
				<td align="center">
<!-- 				<input type="radio" name="rd" value="" ><b> &nbsp;&nbsp;Decline</b> -->
					<input type="button" class="retrbutton button4" name="btn_sign_pdf" value="Decline" onclick="click_home_page('<%//=login_userid%>','<%//=pwd%>');">
				</td>
			</tr>
		</table>
  </div>
</div>
	

</body>
</html>