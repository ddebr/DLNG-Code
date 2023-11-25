<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*" errorPage="" %>
<%@ include file="../sess/Expire.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title>Buyer Selection List</title>

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

function setValue(cd,nm,abbr,plant_nm,plant_type,seq_no,sign_dt,indx)
{
	
	if(plant_nm=='~~~')
	{
		alert("Please create Plant for <<"+nm+">> then try to select it");
		document.forms[0].rd3[parseInt(""+indx)].checked = false;
	}
	else
	{
		var activity = document.forms[0].activity.value;
		window.opener.setBuyerValue(activity,cd,nm,abbr,plant_nm,plant_type,seq_no,sign_dt);
		window.close();	
	}
}

</script>

<jsp:useBean class="com.seipl.hazira.dlng.contract_master.DataBean_Contract_MasterV2" id="mst" scope="page"/>
<%
	String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
	String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
	String sign_dt = request.getParameter("sign_dt")==null?"":request.getParameter("sign_dt");
	String comp_cd=session.getAttribute("comp_cd")==null?"56":""+session.getAttribute("comp_cd");
	String emp_cd=session.getAttribute("emp_cd")==null?"":""+session.getAttribute("emp_cd");
	
	buyer_name = buyer_name.trim();
	//System.out.println("Buyer Name  : "+buyer_name);
	mst.setCallFlag("BUYER_LIST");
	mst.setBuyer_name(buyer_name);
	mst.setSign_date(sign_dt);
	mst.init();
	
	Vector buyer_nm = mst.getBuyer_nm();
	Vector buyer_cd = mst.getBuyer_cd();
	Vector buyer_abbr = mst.getBuyer_abbr();
	Vector buyer_plant_nm = mst.getBuyer_plant_nm();
	Vector buyer_plant_type = mst.getBuyer_plant_type();
	Vector buyer_plant_seq_no = mst.getBuyer_plant_seq_no();
	String plant_nm = "";
	String plant_type = "";
	String seq_no = "";
	
//	new com.hazira.hlpl.tlu.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: Selection of Buyer for FLSA Master !!!");
%>
<body>
<div class="box mb-0">
<form name="product_list" method="post">
	<div class="box-body table-responsive no-padding">
		<table class="table  table-bordered">
			<thead>   
				<tr><td colspan="3" class="text-center"><b>Existing Buyer List</b></td></tr>
				<tr class="info">
					<td class="title2" ><div align="center">Select</div></td>
					<td class="title2"><div align="left">Buyer Name</div></td>
					<td class="title2"><div align="left">Abbreviation</div></td>
			    </tr>
			    </thead>
    <% 	for(int i=0;i<buyer_cd.size();i++) 
    	{	
    		plant_nm = "";
    		plant_type = "";
    		seq_no = "";
    		for(int j=0;j<((Vector)buyer_plant_nm.elementAt(i)).size();j++)
    		{	
    			plant_nm += (String)((Vector)buyer_plant_nm.elementAt(i)).elementAt(j)+"~~~";
    			plant_type += (String)((Vector)buyer_plant_type.elementAt(i)).elementAt(j)+"~~~";
    			seq_no += (String)((Vector)buyer_plant_seq_no.elementAt(i)).elementAt(j)+"~~~";
    		}	
    //		System.out.println("plant_nm in Buyer List Page = "+plant_nm);%>
		    <tr class=<%=(i%2)==0?"content1A":"content1"%>>
		    	<td><input type="radio" name="rd3" onClick="setValue('<%=(String)buyer_cd.elementAt(i)%>',&quot;<%=(String)buyer_nm.elementAt(i)%>&quot;,&quot;<%=(String)buyer_abbr.elementAt(i)%>&quot;,&quot;<%=plant_nm%>&quot;,&quot;<%=plant_type%>&quot;,'<%=seq_no%>','<%=sign_dt%>','<%=i%>');"></td>
			  	<td><%=buyer_nm.elementAt(i)%></td>
			  	<td><%=buyer_abbr.elementAt(i)%></td>	  
		    </tr>
	<%	}	%>
	<%	if(buyer_cd.size()==0)
		{	%>
			<tr>
				<td colspan="3" class="content1">
					<%	if(buyer_name.equals(""))
						{	%>
							<b>Buyer List Not Available !!!</b>
					<%	}	
						else
						{	%>
							<b>Buyer List Starting From <%=buyer_name%>... Is Not Available !!!</b>
					<%	}	%>
				</td>
			</tr>
	<%	}	%>
	<tr>
		<td colspan="3" align="left">
			<input type="button" value="Close" name="close" onClick="window.close();">
			<input name="activity" type="hidden" value="<%=activity%>">
		</td>
	</tr>
</table>
  </div>
  
</form>
</div>
</body>
</html>