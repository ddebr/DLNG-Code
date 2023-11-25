<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*" errorPage="" %>
<%@ include file="../sess/Expire.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title>LOA Selection List</title>

<link rel="stylesheet" type="text/css" href="../css/guidefaultGeneral.css">
<link rel="stylesheet" type="text/css" href="../css/utilities.css">


<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css">
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<script type="text/javascript" src="../js/bootstrap.min.js"></script>

<style type="text/css">
#loading 
{
	width: 200px;
	height: 100px;
	background-color: #c0c0c0;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-top: -50px;
	margin-left: -100px;
	text-align: center;
}
</style>

<script type="text/javascript">
document.write('<div id="loading"><br><br>Loading, Please Wait...</div>');
window.onload=function()
{
	document.getElementById("loading").style.display="none";
}
</script>

<script language="JavaScript">
function setValue(bCD,fCD,snNo,snRev)
{ 	
	var activity = document.forms[0].activity.value;
	window.opener.setTenderCode(bCD,fCD,"update",snNo,snRev);
	window.close();		
}
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.contract_master.DataBean_Contract_Master" id="mst" scope="page"/>
<%
	String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
//	String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
//	String comp_cd=session.getAttribute("comp_cd")==null?"56":""+session.getAttribute("comp_cd");
//	String emp_cd=session.getAttribute("emp_cd")==null?"":""+session.getAttribute("emp_cd");
	String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
	String cust_nm = request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");
	
	mst.setCallFlag("LOA_LIST");
	mst.setBscode(cust_nm);
	mst.init();
	
	Vector buyer_nm = mst.getBuyer_nm(); 
	Vector buyer_cd = mst.getBuyer_cd();    
	Vector Tender_cd = mst.getTender_no();
	Vector vLOA_No = mst.getLOA_NO();
	Vector vLOA_rev_No = mst.getLOA_Rev_NO();
	Vector vLOA_TCQ = mst.getLOA_TCQ();
	Vector vTCQ_UNIT = mst.getVTCQ_UNIT();
	Vector vLOA_verify = mst.getLOA_verify();
	Vector vLOA_approve= mst.getLOA_Approve();
	Vector vLOA_Ref_No = mst.getVLOA_Ref_No();
%>
<body>

<div class="box mb-0">
<form name="product_list" method="post">
	<div class="box-body table-responsive no-padding">
		<table class="table  table-bordered">
			<thead>   
				<tr><td colspan="8" class="text-center"><b>LOA List</b></td></tr>
				<tr class="info">
					<th class="text-center" rowspan="2">Select</th>
					<th class="text-center" rowspan="2">LOA&nbsp;No</th>
					<th class="text-center" rowspan="2">LOA Rev&nbsp;No</th>
					<th class="text-center" rowspan="2">Tender&nbsp;No</th>
					<th class="text-center" rowspan="2">TCQ</th>
					<th class="text-center" rowspan="2">Buyer&nbsp;Name</th>
					<th class="text-center" rowspan="2">Approved</th>
				</tr>
			</thead>
			<tbody>
					<%for(int i=0;i<Tender_cd.size();i++) { %>
						 <tr class=<%=((i%2)==0?"content1A":"content1")%>>
							<td>
								<div align="center">
									<input type="radio" name="rd3" onClick="setValue('<%=(String)buyer_cd.elementAt(i)%>','<%=(String)Tender_cd.elementAt(i)%>','<%=(String)vLOA_No.elementAt(i)%>','<%=(String)vLOA_rev_No.elementAt(i)%>');"
								  <%if(flg.equals("insert")){%>	disabled<%}%>>
								</div>
						    </td> 
						    <td><div class="text-center"><b>
						    <%if(vLOA_TCQ.elementAt(i).equals("0")) {%>
						     <FONT COLOR=RED> <%=vLOA_No.elementAt(i)%> (New)</FONT>
						    <%} else { %>
						    <%=vLOA_No.elementAt(i)%><%=vLOA_Ref_No.elementAt(i)%>
						    <%} %>
						    </b></div></td>		  	
						   
							<td><div class="text-center">&nbsp;<b><%=vLOA_rev_No.elementAt(i)%></b></div></td>
							<td><div class="text-center">&nbsp;<b><%=Tender_cd.elementAt(i)%></b></div></td>			
							<td><div class="text-center">&nbsp;<b><%=vLOA_TCQ.elementAt(i)%>&nbsp;&nbsp;<%=vTCQ_UNIT.elementAt(i) %></b></div></td>	  
							<td nowrap="nowrap"><div align="left">&nbsp;<b><%=buyer_nm.elementAt(i)%></div></td>		  
						    <td><div class="text-center">&nbsp;<b><%if(vLOA_approve.elementAt(i).toString().equalsIgnoreCase("Y")){%>YES<%}else{%>NO<%}%></b></div></td>
					     </tr>
					<%	}	
						if(Tender_cd.size()==0)
						{ %>	
							<tr><td class="text-center" colspan="8"><b>LOA List Not Available !!!</b></td></tr>
					  <%}%>
							<tr>
								<td colspan="8" align="right">
									<input type="button" value="Close" name="close" onClick="window.close();">
									<input name="activity" type="hidden" value="<%=activity%>">
								</td>
							</tr>
			</tbody>
		</table>
	</div>
</form>
</div>
</body>
</html>