<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp" %>
<%@ include file="../sess/Expire.jsp" %>
<html>
<head>
<title>Interest/Exchange Rate Entry</title>
<script language="JavaScript" src="../js/date-picker.js"></script>
<script language="JavaScript" src="../js/aris.js"></script>
<script language="JavaScript" src="../js/util2.js"></script>
<script language="JavaScript" src="../js/util1.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>

<script language="JavaScript">

var oldVal = new Array();
var newVal = new Array();
var valUpd = new Array();
var tcompadd1 = new Array();
  
var flag = true;
  
var k=0;
var l=0;
var g=0; 
  
function subm(sz)
{
	var message = "Please Check the Following field(s).\n\n";
	var flag = true;
	
	var size = parseInt(""+sz);
	var i = 0;
	
	var rate_type = document.forms[0].rate.value;
//	var intcd = document.forms[0].intcd.value;
    
 if(rate_type=='Commodity')
	{
		alert(size);
	}
	
	if(flag)
	{
	    a = confirm("Are You Sure You Want To Submit The Exchange/Interest Rate DATA\nFor The Selected Month & Year ???");
	    if(a)
	    {
	    	document.forms[0].submit();
	    }
	}
	else
	{
		alert(message);
	}
}


function setStatus()
{
	var month = document.forms[0].month.value;
	var year = document.forms[0].year.value;
	var rate = document.forms[0].rate.value;
	var index_type = document.forms[0].index_type.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	var url="frm_Commodity_Rate_Entry.jsp?rate="+rate+"&month="+month+"&year="+year+"&index_type="+index_type+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    location.replace(url);	
}


function setRateMode(month,year,rate,comp_cd,sz)
{
	var size = parseInt(""+sz);
	var i = 0;
	
    document.forms[0].month.value = month;
	document.forms[0].year.value = year;
	document.forms[0].rate.value = rate;
	document.forms[0].intcd.value = comp_cd;
	
	if(rate=='Exgrate')
	{
		if(size==1)
		{
			document.forms[0].currency_from.value = document.forms[0].curr_from.value;
			document.forms[0].currency.value = document.forms[0].curr_to.value;
		}
		else if(size>1)
		{
			for(i=0; i<size; i++)
			{
				document.forms[0].currency_from[i].value = document.forms[0].curr_from[i].value;
				document.forms[0].currency[i].value = document.forms[0].curr_to[i].value;
			}
		}
	}	  	
}

</script>

<link href="../css/guidefaultGeneral.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../css/utilities.css">

</head>
<jsp:useBean class="com.seipl.hazira.dlng.mrcr.DataBean_Contract_Master2" id="masOthers" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="page"/>
<%
    util.init();
	String todayDate = util.getGen_dt();
	String curr_month = todayDate.substring(3,5);
	String curr_year = todayDate.substring(6);
	String next_year = ""+(Integer.parseInt(curr_year)+1);
	String pre_year = ""+(Integer.parseInt(curr_year)-1);
	String pre_two_year = ""+(Integer.parseInt(curr_year)-2);
	String pre_three_year = ""+(Integer.parseInt(curr_year)-3);
	String pre_four_year = ""+(Integer.parseInt(curr_year)-4);
	String pre_five_year = ""+(Integer.parseInt(curr_year)-5);
	String pre_six_year = ""+(Integer.parseInt(curr_year)-6);
	String pre_seven_year = ""+(Integer.parseInt(curr_year)-7);
	String pre_eight_year = ""+(Integer.parseInt(curr_year)-8);
	String pre_nine_year = ""+(Integer.parseInt(curr_year)-9);
	
	String month = request.getParameter("month")==null?curr_month:request.getParameter("month");
    String year = request.getParameter("year")==null?curr_year:request.getParameter("year");
    String rate = request.getParameter("rate")==null?"Commodity":request.getParameter("rate");
    String index_type = request.getParameter("index_type")==null?"JKM":request.getParameter("index_type");
    
    String opt = request.getParameter("opt")==null?"":request.getParameter("opt");
    String flag = request.getParameter("flag")==null?"":request.getParameter("flag");
    String status = request.getParameter("message")==null?"":request.getParameter("message");
    String effDt = request.getParameter("effDt")==null?todayDate:request.getParameter("effDt");
    String currency = request.getParameter("currency")==null?"":request.getParameter("currency");
    String currency_from = request.getParameter("currency_from")==null?"":request.getParameter("currency_from");
    String flg = request.getParameter("flg")==null?"N":request.getParameter("flg");
    
    String write_permission = request.getParameter("write_permission")==null?"Y":request.getParameter("write_permission");
	String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
	String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
    
     masOthers.setCallFlag("commodity_rate_entry");
     masOthers.setMonth(month);
     masOthers.setYear(year);
     masOthers.setRate_flag(rate);
     masOthers.setIndex_Type(index_type);
     masOthers.init();
     
     Vector int_rate_cd = masOthers.getInt_rate_cd();
 	 Vector int_rate_nm = masOthers.getInt_rate_nm();
 	 Vector exg_rate_nm = masOthers.getExg_rate_nm();
 	 Vector exg_rate_cd = masOthers.getExg_rate_cd();
 	
     
 	 String int_rate_flag = masOthers.getInt_rate_flag();
     String exg_rate_flag = masOthers.getExg_rate_flag();
     
     int tot=int_rate_cd.size()+1;
     int tot1=exg_rate_cd.size()+1;
     
     Vector curr_cd = masOthers.getCurrency_cd();
     Vector curr_nm = masOthers.getCurrency_nm();
      
     String curren_cd = masOthers.getCurren_cd();
     String curren_cd_from = masOthers.getCurren_cd_from();
     String rate_value = masOthers.getRate_value();
     String remark = masOthers.getRemarks();
     
     Vector Veff_dt = masOthers.getVeff_dt();
     Vector Vcurren_cd = masOthers.getVcurren_cd();
     Vector Vcurren_cd_from = masOthers.getVcurren_cd_from();
     Vector Vrate_value = masOthers.getVrate_value();
     Vector Vremark = masOthers.getVremark();    
     
     Vector Vcomponent_cd = masOthers.getVcomponent_cd();
     Vector Vcomponent_nm = masOthers.getVcomponent_nm();
     
     int sz = Veff_dt.size();
     String AvgCommodityValue = masOthers.getAvgCommodityValue();
     String ContCommodityBasedValue = masOthers.getContPriceBasedCommodityValue();
%>

<body onload="setRateMode('<%=month%>','<%=year%>','<%=rate%>','<%=index_type%>','<%=sz%>');"  bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"   >
<%@ include file="../home/header.jsp" %>
<%	if(print_permission.trim().equalsIgnoreCase("N"))
	{%> <script language="javascript" src="../js/mouseclk.js"></script>
<%	}%>
<form name=mainview method=post action="../servlet/Frm_MrCrMaster">

<%
	String form_id = "0";
	String form_nm = "";
	for(int i=0; i<FORM_CD.size(); i++)
	{
		if((""+FORM_NAME.elementAt(i)).trim().equalsIgnoreCase("Commodity Rate Entry"))
		{
			form_id = ""+FORM_CD.elementAt(i);
			form_nm = "Commodity Rate Entry";
		}
	}
	System.out.println("form_id = "+form_id);
	System.out.println("form_nm = "+form_nm);
%>
<input type="hidden" name="form_id" value="<%=form_id%>">
<input type="hidden" name="form_nm" value="<%=form_nm%>">

<div id="headingPage">
<table class="title" width="80%" align="center">
  <tr>
      <td width="100%" align="center"> Commodity Rate Entry</td>
  </tr>
</table>
</div>

<div id="col-three">
<table width="80%" align="center">
  	<tr class="title2">
  	<td align="center">
    		<div align="center">Rate Mode<font color="red">*</font>
      		<select name="rate" onChange="setStatus();">
	    		<option value="Commodity">Commodity Business Day</option>
      		</select>
      		</div>
      		<script type="text/javascript">
		   		document.forms[0].rate.value = '<%=rate%>';
		   	</script>
    	</td>
  		<td align="left">
  			<div align="center">Delivery Month<font color="red">*</font>
      		<select name="month" onChange="setStatus();">
	    		<option value="0" label="select">--Select--</option>
				<option value="01" label="January">January</option>
				<option value="02" label="February">February</option>
				<option value="03" label="March">March</option>
				<option value="04" label="April">April</option>
				<option value="05" label="May">May</option>
				<option value="06" label="June">June</option>
				<option value="07" label="July">July</option>
				<option value="08" label="August">August</option>
				<option value="09" label="September">September</option>
				<option value="10" label="October">October</option>
				<option value="11" label="November">November</option>
				<option value="12" label="December">December</option>
      		</select>
      		<script type="text/javascript">
		   		document.forms[0].month.value = '<%=month%>';
		   	</script>
      		&nbsp;&nbsp;Year<font color="red">*</font>
      		<select name="year" onChange="setStatus();">
	    		<option value="0">--Select--</option>
	    		<option value="<%=next_year%>"><%=next_year%></option>
				<option value="<%=curr_year%>"><%=curr_year%></option>
				<option value="<%=pre_year%>"><%=pre_year%></option>
				<option value="<%=pre_two_year%>"><%=pre_two_year%></option>	
				<option value="<%=pre_three_year%>"><%=pre_three_year%></option>	
				<option value="<%=pre_four_year%>"><%=pre_four_year%></option>
				<option value="<%=pre_five_year%>"><%=pre_five_year%></option>
				<option value="<%=pre_six_year%>"><%=pre_six_year%></option>
				<option value="<%=pre_seven_year%>"><%=pre_seven_year%></option>
				<option value="<%=pre_eight_year%>"><%=pre_eight_year%></option>
				<option value="<%=pre_nine_year%>"><%=pre_nine_year%></option>
      		</select>
      		<script type="text/javascript">
		   		document.forms[0].year.value = '<%=year%>';
		   	</script>
      		</div>
    	</td>
    	<td align="center">
    		<div align="center">Index Mode<font color="red">*</font>
      		<select name="index_type" onChange="setStatus();">
	    		<option value="">--Select--</option>
	    		<option value="JKM">JKM</option>
	    		<option value="Brent">Brent</option>
      		</select>
      		<script type="text/javascript">
		   		document.forms[0].index_type.value = '<%=index_type%>';
		   	</script>
      		</div>
    	</td>
    	
  	</tr>
</table>

<table width="80%" align="center">

	<%	if(rate.equalsIgnoreCase("Commodity"))
		{	%>
		<%	if(status!=null && !status.trim().equalsIgnoreCase("") && status.length()>10)
			{	%>
  				<tr class="title2">
     				<td colspan="3" align="center"><font color="red"><%=status%></font></td>
  				</tr>
		<%	}	%>
			<tr class="title2">
    			<td width="20%"><font color="red">*</font> Commodity Business Date</td>
    			<%--<td> Component</td>--%>
    			<td width="25%"><font color="red">*</font> JKM Value</td>
    			<td width="55%">Remark</td>
  			</tr>
  	<%	for(int i=0; i<Veff_dt.size(); i++)
  		{	%>
   			<tr class="content1">
                <td align="center" colspan="1">
			         <input name="effdtA" type="text" size="8" maxlength="10" value="<%=Veff_dt.elementAt(i)%>" readOnly style="text-align:right" title="dd/mm/yyyy Format">
			    </td>
			    
			    <td align="center">
			        <input type="text" value="<%=Vrate_value.elementAt(i)%>" name="comp_val" maxlength="5" style="text-align:right;" size="4" onBlur="negNumber(this);checkNumber1(this,6,4);">&nbsp;<b>($/MMBTU)</b>
			    </td>
			    <td align="left">
			        <input type="text" value="<%=Vremark.elementAt(i)%>" name="remark" size="60" maxlength="100">
			    </td>		     
   			</tr>
   	<%	}	%>
   			<tr class="content1">
   			<td align="center" colspan="1">
			         Average JKM Value:
			    </td>
			    <td align="center" colspan="1">
			         <input name="avgValue" type="text" size="8" maxlength="10" value="<%=AvgCommodityValue%>" readOnly style="text-align:right" title="Average Value">&nbsp;<b>($/MMBTU)</b>
			    </td>
				<td colspan="1">
					<div align="right">
						<font color="red"> * Mandatory Information</font>&nbsp;&nbsp;
					</div>
				</td>
  			</tr>
  			<tr class="content1">
   			<td align="center" colspan="1">
			         Contract Price:
			    </td>
			    <td align="center" colspan="1">
			         <input name="avgValue" type="text" size="8" maxlength="10" value="<%=ContCommodityBasedValue%>" readOnly style="text-align:right" title="Contract Price based on Average Value">&nbsp;<b>($/MMBTU)</b>
			    </td>
				<td colspan="1">
					<div align="right">
						<font color="red"> </font>&nbsp;&nbsp;
					</div>
				</td>
  			</tr>
  			<tr class="title2">
     			<td colspan="3" align="center">
     			<%	if(write_permission.trim().equalsIgnoreCase("Y"))
					{	%>	
     					<input type="button" name="button" value="Submit" onClick="subm('<%=Veff_dt.size()%>');">
     			<%	}
     				else
     				{	%>
     					<input type="button" name="button" value="Submit" style="font-size:15;font-weight:bold;" disabled>
     			<%	}	%>
     			</td>
  			</tr>
	<%	}	%>
</table>

<input type="hidden" name="flag" value="<%=flag %>">
<input type="hidden" name="opt" value="<%=opt%>">
<input type="hidden" name="option" value="commodity_entry">

<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
<input type="hidden" name="audit_permission" value="<%=audit_permission%>">

</div>
</form>
</body>
</html>