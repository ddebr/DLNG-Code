<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*" errorPage="" %>
<%@ include file="../sess/Expire.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title>FLSA Selection List</title>

<link rel="stylesheet" type="text/css" href="../css/guidefaultGeneral.css">

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
<!--
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
-->
th,td {
    font-size:small;
}
</style>

<script type="text/javascript">
document.write('<div id="loading"><br><br>Loading, Please Wait...</div>');
window.onload=function()
{
	document.getElementById("loading").style.display="none";
}
</script>
</head>

<script language="JavaScript">

function setValue(bCD,tCD)
{
	var activity = document.forms[0].activity.value;
	window.opener.setTenderCode(bCD,tCD,"update");
	window.close();	
}

</script>

<jsp:useBean class="com.seipl.hazira.dlng.contract_master.DataBean_Contract_MasterV2" id="mst" scope="page"/>
<%
	String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
//	String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
//	String sign_dt = request.getParameter("sign_dt")==null?"":request.getParameter("sign_dt");
	String comp_cd=session.getAttribute("comp_cd")==null?"56":""+session.getAttribute("comp_cd");
	String emp_cd=session.getAttribute("emp_cd")==null?"":""+session.getAttribute("emp_cd");
	String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
	String cust_nm = request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");
//	buyer_name = buyer_name.trim();
	
	String user_cd=(String)session.getAttribute("user_cd"); //ADDED BY RG 16-10-2015 FOR CUSTOMER WISE ACCESS RIGHTS
	String customer_access_flag = (String)session.getAttribute("customer_access_flag");
	
	mst.setCallFlag("Tender_LIST");
//	mst.setBuyer_name(buyer_name);
//	mst.setSign_date(sign_dt);
	mst.setBscode(cust_nm);
	mst.setEmp_cd(user_cd);
	mst.setCustomer_access_flag(customer_access_flag);
	mst.init();
	
	Vector buyer_nm = mst.getBuyer_nm(); 
	Vector buyer_cd = mst.getBuyer_cd();    
	Vector Tender_cd = mst.getTENDER_NO();
	Vector START_DT = mst.getVSTART_DT(); 
	Vector END_DT = mst.getVEND_DT();
	Vector Tender_BASE = mst.getVTENDER_BASE();
	Vector STATUS = mst.getVSTATUS();
	Vector START_DT2 = mst.getVSTART_DT2(); 
	Vector END_DT2 = mst.getVEND_DT2();
	
%>
<body>
<div class="box mb-0">
<form name="product_list" method="post">
	<div class="box-body table-responsive no-padding">
		<table class="table  table-bordered">
			<thead>   
				<tr><td colspan="9" class="text-center"><b>Existing Tender List</b></td></tr>
				<tr class="info">
					<th class="text-center">Select</th>
					<th class="text-center">Tender Code</th>
					<th class="text-center">Buyer Name</th>
					<th class="text-center">Agreement<br>Start<br>Date</th>
					<th class="text-center">Agreement<br>End<br>Date</th>
					<th class="text-center">Tender<br>Submission<br>Date</th>
					<th class="text-center">Tender<br>Closing<br>Date</th>
					<th class="text-center">Tender<br>Base</th>
					<th class="text-center">Status</th>
    		</tr>
<% 	for(int i=0;i<Tender_cd.size();i++) 
    {	   		
//     	System.out.println("Customer Name = "+buyer_cd.elementAt(i));
    	%>
		<tr class=<%=((i%2)==0?"content1A":"content1")%>>
		  	<td>
		  		<input type="radio" name="rd3" onClick="setValue('<%=(String)buyer_cd.elementAt(i)%>',&quot;<%=(String)Tender_cd.elementAt(i)%>&quot;);"
		  		<%	if(flg.equals("insert"))
					{	%>
						disabled
				<%	}	%>>
		  	</td> 
		  	<%
		  String col = "black";
		  if((""+STATUS.elementAt(i)).equalsIgnoreCase("Archived")) { 
		  	col = "red";
		   } %>
			<td class="text-center"><font color="<%=col%>"><%=Tender_cd.elementAt(i)%></font></td>
			<td nowrap="nowrap"><font color="<%=col%>"><%=buyer_nm.elementAt(i)%></font></td>
			<td><font color="<%=col%>"><%=START_DT2.elementAt(i)%></font></td>
			<td><font color="<%=col%>"><%=END_DT2.elementAt(i)%></font></td>
			<td><font color="<%=col%>"><%=START_DT.elementAt(i)%></font></td>	  
			<td><font color="<%=col%>"><%=END_DT.elementAt(i)%></font></td>	
			<td><font color="<%=col%>"><%=Tender_BASE.elementAt(i)%></font></td>	
 			<td><font color="<%=col%>"><%=STATUS.elementAt(i)%></font></td>	
		</tr>
	<%	}	%>
	<%	if(Tender_cd.size()==0)
		{	%>
			<tr>
				<td colspan="9" class="text-center">
					<b>Tender List Not Available !!!</b>
				</td>
			</tr>
	<%	}	%>
	<tr>
		<td colspan="9" align="left">
			<input type="button" value="Close" name="close" onClick="window.close();">
			<input name="activity" type="hidden" value="<%=activity%>">
		</td>
	</tr>
</table>
  
</form>
</div>
</body>
</html>