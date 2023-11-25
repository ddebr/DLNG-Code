<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*" errorPage="" %>
<%@ include file="../sess/Expire.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title>Weekly Truck List</title>

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
var truk_nm="";
var index = "";
function sel_rec_pt(trucknm,ch,indx)
{
  if(ch.checked)
  {
	  truk_nm=trucknm;
	  index = indx;
   	//alert(truk_nm+"..."+index);
 }
 else
 {
	 truk_nm=truk_nm.replace("");
  }
}

function closeWindow()
{
	window.opener.document.forms[0].nametruck.value=truk_nm;
	window.opener.showTrucks(index,truk_nm);
	window.close();
}
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.contract_mgmt.DataBean_MgmtV2" id="contMgmt" scope="page" />
<%
	String indx = request.getParameter("indx")==null?"":request.getParameter("indx");
	String selCust_id = request.getParameter("selCust_id")==null?"":request.getParameter("selCust_id");
	String buyer_mapping_id = request.getParameter("buyer_mapping_id")==null?"":request.getParameter("buyer_mapping_id");
	String nomId = request.getParameter("nomId")==null?"":request.getParameter("nomId");
	String revNo = request.getParameter("revNo")==null?"":request.getParameter("revNo");
	String nomDt = request.getParameter("nomDt")==null?"":request.getParameter("nomDt");
	
	
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	Calendar cal = Calendar.getInstance();
	cal.setTime(df.parse(nomDt));
	cal.add(Calendar.DATE, 6);
	/* System.out.println(gas_date+"....."+df.format(cal.getTime()));  */
	Date startDate = null;
	Date endDate = null;
	
	try {
		   startDate = (Date)dateformat.parse(nomDt);
		   endDate = (Date)dateformat.parse(df.format(cal.getTime()));
	} catch (ParseException e) {
		   e.printStackTrace();
	} 
	
	Vector nomModalDates = new Vector(); 
	List<Date> dates = new ArrayList<Date>();
	long interval = 24*1000 * 60 * 60;
	long endTime =endDate.getTime() ; 
	long curTime = startDate.getTime();
	while (curTime <= endTime) {
	      dates.add(new Date(curTime));
	      curTime += interval;
	}
	for(int i=0;i<dates.size();i++){
	      Date lDate =(Date)dates.get(i);
	      String ds = dateformat.format(lDate);    
	      nomModalDates.add(ds);
	}

	String TbackColor = "";
	
	contMgmt.setSelCust_id(selCust_id);
	contMgmt.setBuyer_mapping_id(buyer_mapping_id);
	contMgmt.setNomDt(nomDt);
	contMgmt.setNomId(nomId);
	contMgmt.setRevNo(revNo);
	contMgmt.setNomModalDates(nomModalDates);
	
	contMgmt.setCallFlag("CUSTOMER_WEEKLY_NOM");
	contMgmt.init();
	
	Vector custidAT = contMgmt.getCustIDAT();
	Vector trucknmAT = contMgmt.getTruckNameAT();
	
	
%>
<body>

<div class="box mb-0">
<form name="product_list" method="post">
	<div class="box-body table-responsive no-padding">
		<table class="table  table-bordered">
			<thead>   
				<tr><td colspan="8" class="text-center"><b>Weekly Truck List</b></td></tr>
				<tr class="info">
					<th colspan="2">Date</th>
					<th>Truck Name</th>
				</tr>
			</thead>
			<tbody>
				<%for(int j = 0; j < nomModalDates.size(); j++) 
				  {%>
				  	 <%for(int c=0;c<custidAT.size();c++){%>
				  	 	<%if(j==0){
					   			TbackColor = "#FFE5E5";
					   		}else if(j==1){
					   			TbackColor = "#F7E4CC";
					   		}else if(j==2){
					   			TbackColor = "#D2E9D2";
					   		}else if(j==3){
					   			TbackColor = "#E7FFBE";
					   		}else if(j==4){
					   			TbackColor = "#BFEAA3";
					   		}else if(j==5){
					   			TbackColor = "#D0CFCF";
					   		}else if(j==6){
					   			TbackColor = "#DAD1B8";
					   		}%>
					   		<%-- <%if(!TloadedCheckIndx.equals(j+"-"+c))%> --%>
						  	<tr style="background-color: <%=TbackColor%>;">
						  	 	<td><input type="checkbox" name="truckCheck" id="truckCheck<%=j%>-<%=c%>" value="" style="width: 1.5em;height: 1.5em;" onClick="sel_rec_pt('<%=trucknmAT.elementAt(c)%>',this,'<%=j%>-<%=c%>')"></td>
						  	 	<td><label><%=nomModalDates.elementAt(j)%></label></td>
						  	 	<td>
						  	 		<label><%=trucknmAT.elementAt(c)%></label>
						  	 		<input type="hidden" name="nametruck" id="nametruck<%=j%>-<%=c%>" value="<%=trucknmAT.elementAt(c)%>">
						  	 	</td>
						  	</tr>
					  <%}%>
				<%}%>
				<tr>
					<td colspan="8" align="right">
						<input type="button" value="Close" name="close" onClick="closeWindow();">
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</form>
</div>
</body>
</html>