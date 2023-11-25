<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*" errorPage="" %>
<%@ include file="../sess/Expire.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>SN Selection List</title>

<link href="../css/guidefaultGeneral.css" rel="stylesheet" type="text/css">
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
#loading {
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
</head>

<script language="JavaScript">
function setValue(bCD,fCD,fgsa_rev,snNo,snRev)
{
	var activity = document.forms[0].activity.value;
	window.opener.setFGSACode(bCD,fCD,"update",fgsa_rev,snNo,snRev);
	window.close();	
}
function changefgsa(snno,snrevno,fgsano,fgsarevno,snrefno,buyercd,newrevno)
{
var activity=document.forms[0].activity.value;
var compcd=document.forms[0].comp_cd.value;
var flg=document.forms[0].flg.value;
var emp_cd=document.forms[0].emp_cd.value;
var cust_nm=document.forms[0].cust_nm.value;
var changeFlag='Y';
document.forms[0].changeFlag.value=changeFlag;

	var url="frm_SN_list.jsp?activity="+activity+"&comp_cd="+compcd+"&emp_cd="+emp_cd+"&cust_nm="+cust_nm+"&flg="+flg+"&changeFlag="+changeFlag+"&snno="+snno+"&snrevno="+snrevno+"&fgsano="+fgsano+"&fgsarevno="+fgsarevno+"&snrefno="+snrefno+"&buyercd="+buyercd+"&newrevno="+newrevno;
	var b=confirm("Do you want to Revise FLSA REV. No "+fgsarevno+" to "+newrevno+" for SN No "+snno+" ??");	
	if(b)
		location.replace(url);
}
</script>

<jsp:useBean class="com.seipl.hazira.dlng.contract_master.DataBean_Contract_Master" id="mst" scope="page"/>
<%
	String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
	String comp_cd=session.getAttribute("comp_cd")==null?"56":""+session.getAttribute("comp_cd");
	String emp_cd=session.getAttribute("emp_cd")==null?"":""+session.getAttribute("emp_cd");
	String flg = request.getParameter("flg")==null?"":request.getParameter("flg");
	String cust_nm = request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");
	String changeFlag=request.getParameter("changeFlag")==null?"N":request.getParameter("changeFlag");
	
	String snno=request.getParameter("snno")==null?"":request.getParameter("snno");
	String snrevno=request.getParameter("snrevno")==null?"":request.getParameter("snrevno");
	String fgsano=request.getParameter("fgsano")==null?"":request.getParameter("fgsano");
	String fgsarevno=request.getParameter("fgsarevno")==null?"":request.getParameter("fgsarevno");
	String snrefno=request.getParameter("snrefno")==null?"":request.getParameter("snrefno");
	String buyercd=request.getParameter("buyercd")==null?"":request.getParameter("buyercd");
	String newrevno=request.getParameter("newrevno")==null?"":request.getParameter("newrevno");
		
 	System.out.println("value of changeFlag...."+changeFlag);
	
	if(changeFlag.equalsIgnoreCase("Y"))
	{
	//	System.out.println("in function....");
		mst.setCallFlag("ChangeFGSARevNO");
		mst.setBscode(cust_nm);
		mst.setSnNo(snno);
		mst.setSnRevNo(snrevno);
		mst.setFgsaNo(fgsano);
		mst.setFgsaRevNo(fgsarevno);
		mst.setSnRefNo(snrefno);
		mst.setBuyerCd(buyercd);
		mst.setNewRevNo(newrevno);
		mst.init();
		changeFlag="N";
	}
	else
	{
		mst.setCallFlag("SN_LIST");
		mst.setBscode(cust_nm);
		mst.init();
	}
	
	
	
	Vector buyer_nm = mst.getBuyer_nm(); 
// 	System.out.println("buyer_nm...."+buyer_nm);
	Vector buyer_cd = mst.getBuyer_cd();    
	Vector FGSA_cd = mst.getFGSA_no();
	Vector vRev_No = mst.getVRev_No();
	Vector vSN_No = mst.getVSN_No();
	Vector vSN_rev_No = mst.getVSN_rev_No();
	Vector vSN_TCQ = mst.getVSN_TCQ();	
	Vector vSN_approve= mst.getVSN_approve();
	Vector vSN_Ref_No = mst.getVSN_Ref_No();
	Vector vFGSA_Rev_No = mst.getVFGSA_Rev_No(); //SB20140603
	Vector vStartDt = mst.getvStartDt(); //SB20140603
	Vector vEndDt = mst.getvEndDt(); //SB20140603
	
%>
<body>
<div class="box mb-0">
<form name="product_list" method="post">
	<div class="box-body table-responsive no-padding">
		<table class="table  table-bordered">
			<thead>   
				<tr><td colspan="9" class="text-center"><b>FLSA: SN List</b></td></tr>
<%-- 				<tr><td colspan="9" class="text-center">&nbsp;<b><%=buyer_nm.elementAt(0)%></td></tr> --%>
				<tr class="info">
					<th class="text-center" rowspan="2">Select</th>
					<th class="text-center" rowspan="2">SN&nbsp;No</th>
					<th class="text-center" rowspan="2">SN Rev&nbsp;No</th>
					<th class="text-center" rowspan="2">FLSA&nbsp;No</th>
					<th class="text-center" rowspan="2">FLSA Rev&nbsp;No</th>
					<th class="text-center" rowspan="2">Start Dt</th>
					<th class="text-center" rowspan="2">End Dt</th>
					<th class="text-center" rowspan="2">TCQ<br>(MMBTU)</th>
					<th class="text-center" rowspan="2">Approved</th>				
				</tr>
			</thead>
			<tbody>
				<%String prev_buyer_cd="";
				for(int i=0;i<FGSA_cd.size();i++){
					
					if(!prev_buyer_cd.equals(buyer_nm.elementAt(i)+"")){%>
						<tr>
						<td colspan="9" align="center"><b><font color="blue"><%=buyer_nm.elementAt(i) %></font></b> </td>
						</tr>	
					<%}%>
			    		<tr class=<%=((i%2)==0?"content1A":"content1")%>>
			    			<td>
			    				<div class="text-center">
			    					<input type="radio" name="rd3" <%if(flg.equals("insert")){%> disabled <%}%>  onClick="setValue('<%=(String)buyer_cd.elementAt(i)%>','<%=(String)FGSA_cd.elementAt(i)%>','<%=(String)vRev_No.elementAt(i)%>','<%=(String)vSN_No.elementAt(i)%>','<%=(String)vSN_rev_No.elementAt(i)%>');">
						  		</div>
						  	</td> 
						    <td><div class="text-center"><b>
						    <%if(vSN_TCQ.elementAt(i).equals("0")) {%>
						     <FONT COLOR=RED><%=vSN_No.elementAt(i)%> (New)</FONT>
						    <%} else { %>
						    <%=vSN_No.elementAt(i)%><%=vSN_Ref_No.elementAt(i)%>
						    <%} %>
						    </b></div></td>
							<td><div class="text-center"><b><%=vSN_rev_No.elementAt(i)%></b></div></td>
							<td><div class="text-center"><b><%=FGSA_cd.elementAt(i)%></b></div></td>
							<td><div class="text-center"><b><%=vRev_No.elementAt(i)%></b></div></td>
							<td><div class="text-center"><b><%=vStartDt.elementAt(i)%></b></div></td>
							<td><div class="text-center"><b><%=vEndDt.elementAt(i)%>
								  	<%if(vSN_approve.elementAt(i).toString().equalsIgnoreCase("Y")){%> 
					 			  	<%}else{
					 			  		if(!vRev_No.elementAt(i).equals(vFGSA_Rev_No.elementAt(i)))
					 			  		{ %>&nbsp;
								 			<input style="background : url('../images/Button_5.png') no-repeat center;width: 50px;height: 20px;" type="button" name="ChangeRevNo" title="Click to Revise FGSA Revision No. to Latest Revision No." onclick="changefgsa('<%=vSN_No.elementAt(i)%>','<%=vSN_rev_No.elementAt(i)%>','<%=FGSA_cd.elementAt(i)%>','<%=vRev_No.elementAt(i)%>','<%=vSN_Ref_No.elementAt(i)%>','<%=buyer_cd.elementAt(i)%>','<%=vFGSA_Rev_No.elementAt(i)%>');">	
											<!--<a href="#" name="ChangeRevNo" title="Change FGSA Rev No." onclick="changefgsa('<%=vSN_No.elementAt(i)%>','<%=vSN_rev_No.elementAt(i)%>','<%=FGSA_cd.elementAt(i)%>','<%=vRev_No.elementAt(i)%>','<%=vSN_Ref_No.elementAt(i)%>','<%=buyer_cd.elementAt(i)%>','<%=vFGSA_Rev_No.elementAt(i)%>');">Change</a> -->
					 			  	<%  }else{ %>
					 			  			
					 				  <%}
					 			  	  }%>
								  	</b>
							  	</div>
							</td>
							<td><div align="right"><b><%=vSN_TCQ.elementAt(i)%>&nbsp;</b></div></td>	  
							<td><div align="center">&nbsp;<b>
				 			  	<%if(vSN_approve.elementAt(i).toString().equalsIgnoreCase("Y")){%> 
				 			  	YES 
				 			  	<%}else{ %>
				 			  	NO
				 				  	<% } %>
				 			  	 </b></div></td>
					  </tr>
				<%	prev_buyer_cd = buyer_nm.elementAt(i)+""; }%>
				<%	if(FGSA_cd.size()==0)
					{%><tr> <td colspan="9" class="content1"><b>SN List Not Available !!!</b></td> </tr>
				<%	}%>
				<tr>
					<td colspan="9" align="right">
						<input type="button" value="Close" name="close" onClick="window.close();">
						<input name="activity" type="hidden" value="<%=activity%>">
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<input type="hidden" name="activity" value="<%=activity%>">
	<input type="hidden" name="comp_cd" value="<%=comp_cd%>">
	<input type="hidden" name="emp_cd" value="<%=emp_cd%>">
	<input type="hidden" name="flg" value="<%=flg%>">
	<input type="hidden" name="cust_nm" value="<%=cust_nm%>">
	<input type="hidden" name="changeFlag" value="<%=changeFlag%>">
</form>
</div>
</body>
</html>