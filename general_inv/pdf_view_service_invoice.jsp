<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page errorPage="../../home/ExceptionHandler.jsp"%>
<%-- <%@ include file="../../sess/Expire.jsp"%> --%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>Service Invoice View PDF</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.advance_payment.DataBean_Advance_payment" id="adv" scope="page"/>   
<%

	//System.out.println("VIEW-JSP-----");
	
	util.init();
	NumberFormat nf = new DecimalFormat("###########0.00");

	String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
	String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
	String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");
	
	String invoice_path = request.getParameter("invoice_path")==null?"":request.getParameter("invoice_path");
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String pdf_type = request.getParameter("pdf_type")==null?"":request.getParameter("pdf_type");
	String cont_type=request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
	String inv_title=request.getParameter("invoice_title")==null?"":request.getParameter("invoice_title");
	//Following All Setter Methods and Variables For PDF File Generation ... Defined By Samik Shah On 21st September, 2010 ...
	HttpServletRequest req = request;
	
	adv.setCallFlag("FETCH_INVOICE_PDF");
	
	adv.setRequest(req);
	String abs_path=request.getContextPath();
	adv.setContract_type(cont_type);
	System.out.println(cont_type+"----jsp");
	adv.setInvoice_title(invoice_path);
	adv.setPdf_type(pdf_type);
	adv.setInv_title(inv_title);
	adv.init();
	String message=adv.getMsg();
	String pdfpath = abs_path+"/"+adv.getInvoice_path().replace("\\", "/");
	System.out.println("pdfpath--------"+pdfpath);
	System.out.println("abs_path--------"+adv.getInvoice_path());
		
%>

<body bgcolor="white" onload="refreshopener();window.close();" >	<%-- window.opener.refreshPage11('<%=message%>'); --%>
<%if(!message.equalsIgnoreCase("")){ %>
	<table>
		<tr>
			<td align="center" style="padding-top: 20px"><font size="3px" color="blue" style="font-weight: bolder; font-family: monospace;"><%=message%></font>
			</td>
		</tr>
	</table>
		
<%} %>
<input type="hidden" id="path" value="<%=pdfpath%>">
<input type="hidden" id="msg" value="<%=message%>">
<script>
 function refreshopener()
{	//alert("hi");
	
	var invoice_file=document.getElementById('path').value;
	var msg=document.getElementById('msg').value;
	if(msg==''){
		window.open(invoice_file);
	}else{
		//alert(msg);
	}
	window.close();
	window.opener.refreshPage11(msg);
} 
</script>
</body>
</html>