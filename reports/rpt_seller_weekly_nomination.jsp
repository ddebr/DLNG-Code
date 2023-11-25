<%@page import="java.text.ParseException"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/tlu.css">
<link rel="stylesheet" href="../css/font-awesome.min.css">

<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>

<script type="text/javascript">
function compareDates(obj) {
	var from_date = document.forms[0].from_date.value;
	var to_date = document.forms[0].to_date.value;
	if(from_date!='' && to_date!='') {
		var c = compareDate(from_date,to_date);
		if(c == 1){
			alert("From Date Should Be Less Then To Date!!");
			obj.value = '';
		}
	}
}

function refreshPage()
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var from_date = document.forms[0].from_date.value;
	var to_date = document.forms[0].to_date.value;
	
	var msg = '';
	if(from_date=='') {
		msg = 'Select From Date!!\n';
	}
	if(to_date=='') {
		msg += 'Select   To Date!!\n';
	}
	if(msg=='') {
		var print_permission = document.forms[0].print_permission.value;
		var form_nm = document.forms[0].form_nm.value;
		location.replace("../reports/rpt_master.jsp?to_date="+to_date+"&from_date="+from_date+"&print_permission="+print_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl);
	} else {
		alert(msg);
	}
}

var tableToExcel = (function() {
	  var uri = 'data:application/vnd.ms-excel;base64,'
	    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
	    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
	    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	  return function(table, name) {
		  //console.logg
	    if (!table.nodeType) table = document.getElementById(table)
	    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
	    var link = document.createElement("a");
		link.href = uri + base64(format(template, ctx));
		link.download = name || 'Workbook.xls';
		link.target = '_blank';
		document.body.appendChild(link);
		link.click();
	  }
})()


</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Report_GenerationV2" id="contMgmt" scope="page"/>   
<%
	java.text.NumberFormat nf = new java.text.DecimalFormat("###0.00");
	java.text.NumberFormat nf1 = new java.text.DecimalFormat("###0");
	
	utilBean.init();
	String current_date = utilBean.getGen_dt();
	
	String temp_dt[] = current_date.split("/");
	String temp_mon = temp_dt[1];
	String temp_yr = temp_dt[2];
	String start_dt = "01/"+temp_mon+"/"+temp_yr;
	
	String time = utilBean.getTime_gen();
	SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
	Date date = format1.parse(current_date);
	
	String tomorrow_date = utilBean.getDate_tomorrow();
	String from_date = request.getParameter("from_date")==null?start_dt:request.getParameter("from_date"); //Modified
	String to_date = request.getParameter("to_date")==null?current_date:request.getParameter("to_date"); //Modified
	String delv_base = request.getParameter("delv_base")==null?"X":request.getParameter("delv_base"); //Modified
	String flsa_no = request.getParameter("flsa_no")==null?"0":request.getParameter("flsa_no"); //Modified
	String mapp_id = request.getParameter("mapp_id")==null?"":request.getParameter("mapp_id"); //Modified
	String contract_no = request.getParameter("contract_no")==null?"":request.getParameter("contract_no"); //Modified
	String plant_no = request.getParameter("plant_no")==null?"":request.getParameter("plant_no"); //Modified
	String rev_no = request.getParameter("rev_no")==null?"":request.getParameter("rev_no"); //Modified
	String sch_mapp_id = mapp_id+"-%-"+plant_no;//request.getParameter("sch_mapp_id")==null?"":request.getParameter("sch_mapp_id"); //Modified
	
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String formName = request.getParameter("FormName")==null?"":request.getParameter("FormName");
	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212
	
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	Calendar cal = Calendar.getInstance();
	cal.setTime(df.parse(from_date));
	cal.add(Calendar.DATE, 6);
	/* System.out.println(gas_date+"....."+df.format(cal.getTime()));  */
	Date startDate = null;
	Date endDate = null;
	
	try {
		   startDate = (Date)dateformat.parse(from_date);
		   endDate = (Date)dateformat.parse(df.format(cal.getTime()));
	} catch (ParseException e) {
		   e.printStackTrace();
	} 
	
	Vector nomModalDates = contMgmt.getNomModalDates();  
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
	
	contMgmt.setCallFlag("Seller_Weekly_Nom_Rpt");
	contMgmt.setFrom_date(from_date);
	contMgmt.setTo_date(to_date);
	contMgmt.setMappId(mapp_id);
	contMgmt.setSchMappId(sch_mapp_id);
	contMgmt.setNomModalDates(nomModalDates);
	contMgmt.init();
	
	Vector customer_name = contMgmt.getAllCustAbbr();
	Vector customer_id = contMgmt.getAllCustid();
	
	Vector rpt_sel_week_mmbtu = contMgmt.getRpt_sel_week_mmbtu();
	Vector rpt_sel_week_truck_nm = contMgmt.getRpt_sel_week_truck_nm();
	Vector rpt_sel_week_schedule_tm = contMgmt.getRpt_sel_week_schedule_tm();
	Vector rpt_sel_week_remarks = contMgmt.getRpt_sel_week_remarks(); 
	Vector rpt_sel_week_mt = contMgmt.getRpt_sel_week_mt();
	Vector rpt_sel_week_customer_nm = contMgmt.getRpt_sel_week_customer_nm();
	Vector rpt_sel_week_plant_nm = contMgmt.getRpt_sel_week_plant_nm();
	double totMMBTU = contMgmt.getTotMMBTU();
	double totMT = contMgmt.getTotMT();
	String TbackColor = "";
	String Contact_Person_Name = contMgmt.getContact_Person_Name();
	String Contact_Customer_Person_Address = contMgmt.getContact_Customer_Person_Address();
	String Contact_Customer_Person_City = contMgmt.getContact_Customer_Person_City();
	String Contact_Customer_Person_Pin = contMgmt.getContact_Customer_Person_Pin();
	String Contact_Customer_Person_State = contMgmt.getContact_Customer_Person_State();
	String Contact_Customer_Name = contMgmt.getContact_Customer_Name();
	
	String Contact_Suppl_Person_Address = contMgmt.getContact_Suppl_Person_Address();
	String Contact_Suppl_Person_City = contMgmt.getContact_Suppl_Person_City();
	String Contact_Suppl_Person_Pin = contMgmt.getContact_Suppl_Person_Pin();
	String Contact_Suppl_Person_State = contMgmt.getContact_Suppl_Person_State();
	String Contact_Suppl_Name = contMgmt.getContact_Suppl_Name();
	
%>
<div class="tab-content">

<div class="tab-pane active" id="hcasreport">
<!-- Default box -->
<div class="box mb-0">
<form >
<div class="box-header with-border main-header" >


<div class="col-lg-2 col-md-2 col-sm-3 col-xs-6">
	<div class="form-group mb-0 row">
		<label></label>
		<div class="form-group">
		<input type='hidden' class="form-control" id="d1" type="text"  name="from_date"  value="<%=from_date%>" onBlur="validateDate(this);" onchange="compareDates(this);refreshPage();"/>
		<input type='hidden' class="form-control" id="d1" type="text"  name="to_date"  value="<%=from_date%>" onBlur="validateDate(this);" onchange="compareDates(this);refreshPage();"/>
			<button type="button"  class="btn btn-primary"   name="viewList" value="View" onclick="refreshPage();" > View List </button>
		</div>
	</div>	
</div>

</div>

<%	try
	{ %>
		<div class="box-body table-responsive no-padding">
			<div class="table-responsive">
				<table id="example" class="table table-bordered table-hover">
					<thead>
					<tr><td colspan="9" align="center" style="font-size: 13px;font-weight: bold;"> <font color="Blue;">
					<%if(delv_base.equals("D")) { %>
							Delivered Sales
							<%} else {%>
							Ex-Terminal Sales
							<%} %>
					</font></td></tr>
					<tr>
						<td colspan="1" align="center" style="font-size: 13px;font-weight: bold;"> <font color="Blue;"><%//=custAbbr%></font>To</td>
						<td colspan="4" align="left" style="font-size: 13px;font-weight: bold;"> <font color="Blue;">Ms/Mr<BR></font>
						<BR><%=Contact_Person_Name%><BR><%=Contact_Customer_Name%><BR><%=Contact_Customer_Person_Address%><BR><%=Contact_Customer_Person_City%>-<%=Contact_Customer_Person_Pin%><BR><%=Contact_Customer_Person_State%>
						
						</td>
						<td colspan="1" align="center" style="font-size: 13px;font-weight: bold;"> <font color="Blue;"><%//=custAbbr%></font>Date Time Sent</td>
						<td colspan="3" align="center" style="font-size: 13px;font-weight: bold;"> <font color="Blue;"><%//=custAbbr%></font></td>
					</tr>
					<tr>
						<td colspan="1" align="center" style="font-size: 13px;font-weight: bold;"> <font color="Blue;"><%//=custAbbr%></font>From</td>
						<td colspan="4" align="center" style="font-size: 13px;font-weight: bold;"> <font color="Blue;"><%//=custAbbr%></font>Ms/Mr
						<BR><%//=Contact_Person_Name%><BR><%=Contact_Suppl_Name%><BR><%=Contact_Suppl_Person_Address%><BR><%=Contact_Suppl_Person_City%>-<%=Contact_Suppl_Person_Pin%><BR><%=Contact_Suppl_Person_State%>
						</td>
						<td colspan="1" align="center" style="font-size: 13px;font-weight: bold;"> <font color="Blue;"><%//=custAbbr%></font>Sequence No#</td>
						<td colspan="3" align="center" style="font-size: 13px;font-weight: bold;"> <font color="Blue;"><%//=custAbbr%></font></td>
					</tr>
					<BR><BR>
						<tr><td colspan="9" align="center" style="font-size: 13px;font-weight: bold;"> <font color="Blue;"><%//=custAbbr%></font>Seller Weekly Confirmation </td></tr>
						<tr><td colspan="9" align="left" style="font-size: 13px;font-weight: bold;"> <font color="Blue;">From Date: <%=from_date%>    To Date: <%=to_date%></td></tr>   
						<tr><td colspan="9" align="left" style="font-size: 13px;font-weight: bold;"> <font color="Blue;"><%//=custAbbr%></font>Dear Madam / Sir</td></tr>   
						<tr><td colspan="9" align="left" style="font-size: 13px;font-weight: bold;"> <font color="Blue;"><%//=custAbbr%></font>As per the requirements of our Framework LNG Sales Agreement FLSA-<%=flsa_no %> dated: Supply Notice <%=contract_no %> dated : we notify as follows:</td></tr>   
						<tr class="info">
							<th rowspan="2" class="text-center">Date</th>
							<th rowspan="2" class="text-center">Customer</th>
							<th rowspan="2" class="text-center">Plant Name</th>
							<th colspan="2" class="text-center">Nomination</th>
							
							<%if(delv_base.equals("D")) { %>
							<th colspan="2" class="text-center">Truck Nomination</th>
							<th rowspan="2" class="text-center">Delivery Point</th>
							<%} else {%>
							<th colspan="2" class="text-center">Truck Confirmation</th>
							<%} %>
							<th rowspan="2" class="text-center">Seller's Comments</th>
						</tr>
						<tr class="info">
							<th colspan="1" class="text-center">MMBTU</th>
							<th colspan="1" class="text-center">MT</th>
							<th colspan="1" class="text-center">Truck No.</th>
							<%if(delv_base.equals("D")) { %>
							<th colspan="1" class="text-center">Arrival Time</th>
							<%} else {%>
							<th colspan="1" class="text-center">Schedule Time</th>
							<%} %>
							
						</tr>
					</thead>
					<tbody> 
						<%if(rpt_sel_week_mmbtu.size() > 0){%>
							<%for (int i = 0 ; i < rpt_sel_week_mmbtu.size(); i++) 
							  {%>
							  	<%if(i%2==0){
									TbackColor = "#DBE9DC";
								  }else{
									 TbackColor = "#FDEFDD";
								  }%>
								<tr style="background-color: <%=TbackColor%>;">
									<td class="text-center"><%=nomModalDates.elementAt(i)%></td>
									<td class="text-center"><%=rpt_sel_week_customer_nm.elementAt(i)%></td>
									<td class="text-center"><%=rpt_sel_week_plant_nm.elementAt(i)%></td>
									<td class="text-right"><%=rpt_sel_week_mmbtu.elementAt(i)%>&nbsp; MMBTU</td>
									<td class="text-right"><%=rpt_sel_week_mt.elementAt(i)%>&nbsp; MT</td>
									<td class="text-center"><%=rpt_sel_week_truck_nm.elementAt(i)%></td>
									<td class="text-center"><%=rpt_sel_week_schedule_tm.elementAt(i)%></td>
									<%if(delv_base.equals("D")) { %>
									<th  class="text-center"><%=rpt_sel_week_plant_nm.elementAt(i)%></th>
									<%} %>
									<td class="text-center"><%=rpt_sel_week_remarks.elementAt(i)%></td>
								</tr>
							<%}%>
							<tr>
								<th colspan="3" class="text-center">TOTAL</th>
								<th class="text-right"><%=nf1.format(totMMBTU)%>&nbsp; MMBTU</th>
								<th class="text-right"><%=nf.format(totMT)%>&nbsp; MT</th>
								<th colspan="3"></th>
							</tr>
						<%}else{%>
							<tr><td colspan="9" align="center" style="font-size: 13px;font-weight: bold;color: red;">Data Not Available !</td></tr>
						<%}%>
					</tbody>
				</table>
			</div>
<%	}catch(Exception e)
	{	
		e.printStackTrace();
	} %>
</div>
<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
<input type="hidden" name="form_id" value="<%=formId%>">
<input type="hidden" name="form_nm" value="<%=formName%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<%-- <input type="hidden" name="index_count" value="<%=index%>"> --%>
</form>	

</div>
</div>
</div>
<!-- jQuery -->
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script>
$(function () {
$('#datetimepicker').datepicker({
changeMonth: true,
changeYear: true,
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
orientation: "bottom auto",
daysOfWeekDisabled: "0,2,3,4,5,6",
toggleActive:"1"
});
});
$(function () {
$('#datetimepicker1').datepicker({
changeMonth: true,
changeYear: true,
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
todayHighlight: true,
orientation: "bottom auto"
});
});
</script>
</body>
</html>