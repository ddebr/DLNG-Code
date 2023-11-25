<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Variable Price Information</title>

<script language="JavaScript" src="../js/date-picker.js"></script>
<script language="JavaScript" src="../js/util2.js"></script>
<script language="JavaScript" src="../js/util1.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>
<%--<script type="text/javascript" src="../js/fsmenu.js"></script>--%>
<link rel="stylesheet" type="text/css" href="../css/guidefaultGeneral.css">
<link rel="stylesheet" type="text/css" href="../css/utilities.css">

<link rel="stylesheet" href="../css/jquery-ui.css">
<link rel="stylesheet" href="../css/style.css">
<script src="../js/jquery-1.12.4.js"></script>
<script src="../js/jquery-ui.js" language="javascript"></script>

<script type="text/javascript">
function Do_close(price)
{
	window.opener.refershParice(price);
	window.close();
}

</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.market_risk.DB_VariablePricing" id="masCont" scope="page"/>
<%
utilBean.init();
String date = utilBean.getGen_dt();

String emp_cd=session.getAttribute("emp_cd")==null?"":""+session.getAttribute("emp_cd");
String cont_type = request.getParameter("cont_type")==null?"":request.getParameter("cont_type");
String buyer_cd = request.getParameter("buyer_cd")==null?"0":request.getParameter("buyer_cd");
String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
String fgsa_cd = request.getParameter("fgsa_cd")==null?"0":request.getParameter("fgsa_cd");
String fgsa_revno = request.getParameter("fgsa_revno")==null?"0":request.getParameter("fgsa_revno");
String sn_cd = request.getParameter("sn_cd")==null?"0":request.getParameter("sn_cd");
String sn_revno = request.getParameter("sn_revno")==null?"0":request.getParameter("sn_revno");
String start_dt = request.getParameter("start_dt")==null?"":request.getParameter("start_dt");
String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
String msg = request.getParameter("msg")==null?"":request.getParameter("msg");

String Price_MappId = buyer_cd+"-"+fgsa_cd+"-"+fgsa_revno+"-"+sn_cd+"-%";

masCont.setCallFlag("PriceInformation");
masCont.setPrice_MappId(Price_MappId);
masCont.setPrice_ContType(cont_type);
masCont.setPrice_DelvStartDt(start_dt);
masCont.setPrice_DelvEndDt(end_dt);
masCont.setPrice_GasDate(date);
masCont.setTo_date(date);
masCont.init();

Vector VPriceRange = masCont.getVPriceRange();
Vector VPriceRange_A = masCont.getVPriceRange_A();
Vector VPriceStartDt = masCont.getVPriceStartDt();
Vector VPriceEndDt = masCont.getVPriceEndDt();
Vector VFinContMth = masCont.getVFinContMth();
Vector VPriceDate = masCont.getVPriceDate();
Vector VPriceFormulaRemark = masCont.getVPriceFormulaRemark();
Vector VCOLOR = masCont.getVCOLOR();
Vector VSETT_FWD = masCont.getVSETT_FWD();

System.out.println(VPriceRange+"=="+VPriceRange_A+"=="+VPriceStartDt+"=="+VPriceEndDt+"=="+VFinContMth+"=="+VPriceDate+"=="+VPriceFormulaRemark);

%>  
<body>
<div id="col-new">
<fieldset style="width:98%" align="center">
	<table width="100%" align="center">
		<tr class="highlighttext" style="background-color: #d9edf7;">
    		<td colspan="3">
    			<div align="left">
    				<font size="4" face="Verdana, Arial, Helvetica, sans-serif"><b>Customer Name : <%=buyer_name%> </b></font>
    				<br>
    				FLSA : <%=fgsa_cd%>
    				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    				SN : <%=sn_cd%>
    			</div>
    		</td>
		</tr>
		<tr class="title2" style="background-color: #d9edf7;">
			<td>Settle /Forward<br>Month</td>
			<td>Price Type</td>
			<td>Price<br>($/MMBTU)</td>
		</tr>
		<%if(VPriceRange_A.size()>0){ %>
			<%for(int i=0; i<VPriceRange_A.size(); i++){ 
				String title="";
				if(VSETT_FWD.elementAt(i).equals("(Settle Price)"))
				{
					title=VPriceStartDt.elementAt(i)+"\n"+VPriceEndDt.elementAt(i)+"\n"+VFinContMth.elementAt(i);
				}
				else
				{
					title=VPriceEndDt.elementAt(i)+"\n"+VFinContMth.elementAt(i);
				}
			 
			%>
			<tr class="content1" style="background-color: white;">
				<td align="center">
					<%-- <input type="radio" name="price" onclick="Do_close('<%=VPriceRange_A.elementAt(i)%>')"> --%>
					<b><%=VFinContMth.elementAt(i) %></b>
				</td>
				<td align="center"><b><%=VPriceFormulaRemark.elementAt(i) %></b></td>
				<td align="center" title="<%=title%>">
					<b>
						<%=VPriceRange_A.elementAt(i) %>&nbsp;&nbsp;
						<br><font color="<%=VCOLOR.elementAt(i)%>"><%=VSETT_FWD.elementAt(i)%></font>&nbsp;&nbsp;
					</b>
				</td>
			</tr>
			<%} %>
		<%}else{ %>
			<tr class="content1" style="background-color: white;">
				<td align="center" colspan="3"><b>Price Data not Available!!</b></td>
			</tr>
		<%} %>
		<tr class="title2" style="background-color: #d9edf7;">
			<td colspan="3"><!-- Note :- click on radio button, the price will be set automatically in the main page  --></td>
		</tr>
	</table>
</fieldset>
</div>	
</body>
</html>