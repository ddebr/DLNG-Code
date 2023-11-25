<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<html>
<head>
<title>Interest/Exchange Rate Entry</title>
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/sort/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="../css/tlu.css">

<!-- Bootstrap JS -->
<script type="text/javascript" src="../js/fms.js"></script>
<script  type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="../js/sweetalert.min.js"></script>

<script type="text/javascript" src="../js/aris.js"></script>
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>

<style type="text/css">
.backColorMainHeader{
background-color: #ffe57f;
}
</style>

<script type="text/javascript">

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
</head>
<jsp:useBean class="com.seipl.hazira.dlng.mrcr.DataBean_Contract_Master2" id="masOthers" scope="page" />
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="page" />
<%
	util.init();
	String todayDate = util.getGen_dt();
	String curr_month = todayDate.substring(3, 5);
	String curr_year = todayDate.substring(6);
	String next_year = "" + (Integer.parseInt(curr_year) + 1);
	String pre_year = "" + (Integer.parseInt(curr_year) - 1);
	String pre_two_year = "" + (Integer.parseInt(curr_year) - 2);
	String pre_three_year = "" + (Integer.parseInt(curr_year) - 3);
	String pre_four_year = "" + (Integer.parseInt(curr_year) - 4);
	String pre_five_year = "" + (Integer.parseInt(curr_year) - 5);
	String pre_six_year = "" + (Integer.parseInt(curr_year) - 6);
	String pre_seven_year = "" + (Integer.parseInt(curr_year) - 7);
	String pre_eight_year = "" + (Integer.parseInt(curr_year) - 8);
	String pre_nine_year = "" + (Integer.parseInt(curr_year) - 9);

	String month = request.getParameter("month") == null ? curr_month : request.getParameter("month");
	String year = request.getParameter("year") == null ? curr_year : request.getParameter("year");
	String rate = request.getParameter("rate") == null ? "Commodity" : request.getParameter("rate");
	String index_type = request.getParameter("index_type") == null ? "JKM" : request.getParameter("index_type");

	String opt = request.getParameter("opt") == null ? "" : request.getParameter("opt");
	String flag = request.getParameter("flag") == null ? "" : request.getParameter("flag");
	String status = request.getParameter("message") == null ? "" : request.getParameter("message");
	String effDt = request.getParameter("effDt") == null ? todayDate : request.getParameter("effDt");
	String currency = request.getParameter("currency") == null ? "" : request.getParameter("currency");
	String currency_from = request.getParameter("currency_from") == null
			? ""
			: request.getParameter("currency_from");
	String flg = request.getParameter("flg") == null ? "N" : request.getParameter("flg");

	String write_permission = request.getParameter("write_permission") == null
			? "N"
			: request.getParameter("write_permission");
	String delete_permission = request.getParameter("delete_permission") == null
			? "N"
			: request.getParameter("delete_permission");
	String print_permission = request.getParameter("print_permission") == null
			? "N"
			: request.getParameter("print_permission");
	String approve_permission = request.getParameter("approve_permission") == null
			? "N"
			: request.getParameter("approve_permission");
	String audit_permission = request.getParameter("audit_permission") == null
			? "N"
			: request.getParameter("audit_permission");

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

	int tot = int_rate_cd.size() + 1;
	int tot1 = exg_rate_cd.size() + 1;

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

<body onload="setRateMode('<%=month%>','<%=year%>','<%=rate%>','<%=index_type%>','<%=sz%>');"  
	bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<%@ include file="../home/header.jsp"%>
	<%if (print_permission.trim().equalsIgnoreCase("N")) {%>
		<script type="text/javascript" src="../js/mouseclk.js"></script>
	<%}%>
	<form name=mainview method=post action="../servlet/Frm_Master">

		 <%String form_id = "0";
		  String form_nm = "";
		  for (int i = 0; i < FORM_CD.size(); i++) {
				if (("" + FORM_NAME.elementAt(i)).trim().equalsIgnoreCase("Commodity Rate Entry")) {
					form_id = "" + FORM_CD.elementAt(i);
					form_nm = "Commodity Rate Entry";
				}
		  }
		  System.out.println("form_id = " + form_id);
		  System.out.println("form_nm = " + form_nm);%>
		
		<input type="hidden" name="form_id" value="<%=form_id%>"> 
		<input type="hidden" name="form_nm" value="<%=form_nm%>">
		
		<div class="tab-content">
			<div class="tab-pane active" id="commodityRateEntry">
				<!-- Default box -->
				<div class="box mb-0">
					<div class="box-header with-border">
						<div id="headingPage">
							<div class="box-body table-responsive no-padding">
								<table class="table  table-bordered">
									<thead>   
										<tr class="info">
											<th class="text-center">Commodity Rate Entry</th>
										</tr>
									</thead>
								</table>
								<%if(rate.equalsIgnoreCase("Commodity")) 
					     		  {%>
					     		  	 <%if(status != null && !status.trim().equalsIgnoreCase("") && status.length() > 10)
				    		  		   {%> 
											<table class="table  table-bordered">
												<thead>   
													<tr>
														<th class="text-center"><font color="red"><%=status%></font></th>
													</tr>
												</thead>
											</table>
									 <%}%>
							   <%}%> 
							</div>
						</div>
						
						<div class="row backColorMainHeader">
							<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
								<div class="form-group mb-0 row">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
										<label>Rate Mode</label>
										<font color="red">*</font>
										<div class="form-group">
											<select name="rate" onChange="setStatus();">
												<option value="Commodity">Commodity Business Day</option>
											</select>
											<script type="text/javascript">
										   		document.forms[0].rate.value = '<%=rate%>';
										   	</script>
										</div>
									</div>
								</div>
							</div>	
							
							<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
								<div class="form-group mb-0 row">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
										<label>Delivery Month</label>
										<font color="red">*</font>
										<div class="form-group">
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
										</div>
									</div>
								</div>
							</div>	
							
							<div class="col-lg-2 col-md-2 col-sm-6 col-xs-12">
								<div class="form-group mb-0 row">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
										<label>Year</label>
										<font color="red">*</font>
										<div class="form-group">
											<select name="year" onChange="setStatus();">
												<option value="0">--Select--</option>
												<option value="2020">2020</option>
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
									</div>
								</div>
							</div>	
							
							<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
								<div class="form-group mb-0 row">
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
										<label>Index Mode</label>
										<font color="red">*</font>
										<div class="form-group">
											<select name="index_type" onChange="setStatus();">
												<option value="">--Select--</option>
												<option value="JKM">JKM</option>
												<option value="Brent">Brent</option>
											</select>
											<script type="text/javascript">
						   						document.forms[0].index_type.value = '<%=index_type%>';
											</script>
										</div>
									</div>
								</div>
							</div>		
						</div>
						
					</div>
					
					<%if(rate.equalsIgnoreCase("Commodity")) 
					     { %>
							<div class="box-body table-responsive no-padding">
								<table class="table  table-bordered">
									<thead>   
										<tr class="info">
											<th class="text-center" width="25%"><font color="red">*</font>Commodity BusinessDate</th>
											<th class="text-center" width="20%"><font color="red">*</font>JKM Value</th>
											<th class="text-center" width="55%"><font color="red">*</font>Remark</th>
										</tr>
									</thead>
									<tbody>
									  <%for (int i = 0; i < Veff_dt.size(); i++) 
									    {%>
											<tr class="content1">
												<td class="text-center" colspan="1">
													<input type="text" name="effdtA" size="8" maxlength="10"
													value="<%=Veff_dt.elementAt(i)%>" readOnly
													style="text-align: right" title="dd/mm/yyyy Format">
												</td>
												<td class="text-center">
													<input type="text" name="comp_val" size="4" maxlength="5" 
													value="<%=Vrate_value.elementAt(i)%>"  style="text-align: right;" 
													onBlur="negNumber(this);checkNumber1(this,6,4);">&nbsp;<b>($/MMBTU)</b>
												</td>
												<td align="left">
													<input type="text" size="60" maxlength="100"
													value="<%=Vremark.elementAt(i)%>" name="remark">
												</td>
											</tr>
									  <%}%> 
										<tr class="content1">
											<td class="text-center" colspan="1">Average JKM Value:</td>
											<td class="text-center" colspan="1">
												<input type="text" name="avgValue" size="4" maxlength="10" 
												 value="<%=AvgCommodityValue%>" readOnly style="text-align: right" 
												 title="Average Value">&nbsp;<b>($/MMBTU)</b>
											</td>
											<td class="text-right" colspan="1">
												<div><font color="red"> * Mandatory Information</font>&nbsp;&nbsp;</div>
											</td>
										</tr>
										<tr class="content1">
											<td class="text-center" colspan="1">Contract Price:</td>
											<td class="text-center" colspan="1">
												<input type="text" name="avgValue" size="4" maxlength="10"
												value="<%=ContCommodityBasedValue%>" readOnly style="text-align: right"
												title="Contract Price based on Average Value">&nbsp;<b>($/MMBTU)</b>
											</td>
											<td class="text-right" colspan="1">
												<div><font color="red"> </font>&nbsp;&nbsp;</div>
											</td>
										</tr>
										<tr class="title2">
											<td class="text-right" colspan="3">
												<button type="button" class="btn btn-success" name="button" value="Submit"
													<%if(write_permission.trim().equalsIgnoreCase("Y")){%> 
												               onClick="subm('<%=Veff_dt.size()%>');"
													<%}else{%>
														disabled
												    <%}%> > Submit
												</button>
											</td>
										</tr>
										
									</tbody>
								</table>
							</div>
					<% }%> 	

				</div>
			</div>
		</div>
		
<input type="hidden" name="flag" value="<%=flag%>"> 
<input type="hidden" name="opt" value="<%=opt%>"> 
<input type="hidden" name="option" value="commodity_entry">
<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
</form>
<!-- jQuery -->
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
</body>
</html>