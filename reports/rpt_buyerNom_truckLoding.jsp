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
	var customer_id = document.forms[0].customer_name.value; 
	
	var msg = '';
	if(from_date=='') {
		msg = 'Select From Date!!\n';
	}
	if(to_date=='') {
		msg += 'Select   To Date!!\n';
	}
	if(customer_id=='') {
		msg += 'Select Customer!!\n';
	}
	if(msg=='') {
		var print_permission = document.forms[0].print_permission.value;
		var form_nm = document.forms[0].form_nm.value;
		location.replace("../reports/rpt_master.jsp?to_date="+to_date+"&from_date="+from_date+"&cid="+customer_id+"&print_permission="+print_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl);
	} else {
		alert(msg);
	}
}

/* function exportTable(tableId , filename = '',rowSize){
var dataType = "data:application/vnd.ms-excel";
var tableSelect = document.getElementById(tableId);
var tableHTML = tableSelect.outerHTML.replace(/ /g,'%20');
var downloadlink = document.createElement("a");
downloadlink.style.border = '1px solid black';
filename = filename?filename+'.xls':'excel_data.xls';
document.body.appendChild(downloadlink);

	if(navigator.msSaveOrOpenBlob){
		var blob = new Blob(['\ufeff',tableHTML],{
			type : dataType
		});
		navigator.msSaveOrOpenBlob(blob,filename);
	}else{
		downloadlink.href = 'data:'+dataType+', '+tableHTML;
		downloadlink.download = filename;
		downloadlink.click();
	}
} */


var tableToExcel = (function() {
	  var uri = 'data:application/vnd.ms-excel;base64,'
	    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
	    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
	    , format = function(s, c)
	    			{
	    				return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) 
	    			}
	  return function(table, name) 
	  {
		if (!table.nodeType) table = document.getElementById(table)
	    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
	    var link = document.createElement("a");
		link.href = uri + base64(format(template, ctx));
		link.download = name || 'Workbook.xls';
		link.target = '_blank';
		document.body.appendChild(link);
		link.click(); 
	  }
})
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Report_GenerationV2" id="contMgmt" scope="page"/>   
<%
double convt_mmbtu_to_mt= (double) session.getAttribute("convt_mmbtu_to_mt");//HA20201229
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
String cust_id = request.getParameter("cid")==null?"":request.getParameter("cid"); 

String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
String formName = request.getParameter("FormName")==null?"":request.getParameter("FormName");
String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212

contMgmt.setCallFlag("buyerNomVsTruckLoadingRpt");
contMgmt.setFrom_date(from_date);
contMgmt.setTo_date(to_date);
contMgmt.setCust_id(cust_id);
contMgmt.setConvt_mmbtu_to_mt(convt_mmbtu_to_mt);
contMgmt.init();

Vector customer_name = contMgmt.getAllCustAbbr();
Vector customer_id = contMgmt.getAllCustid();
Vector contract_wise_gas_dt = contMgmt.getContract_wise_gas_dt();
Vector Vcnt = contMgmt.getVcnt();
Vector Vplant_nm = contMgmt.getVplant_nm();
Vector Vsn_no = contMgmt.getVsn_no();
Vector Vqty_mmbtu = contMgmt.getVqty_mmbtu();
Vector Vqty_scm = contMgmt.getVqty_scm();
Vector Vqty_mt = contMgmt.getVqty_mt();
String cust_nm = contMgmt.getCust_nm();
double totMMBTU = contMgmt.getTotMMBTU();
double totSCM = contMgmt.getTotSCM();
double totMT = contMgmt.getTotMT();
Vector Vcont_st_dt = contMgmt.getVcont_st_dt();
Vector Vcont_end_dt = contMgmt.getVcont_end_dt();
Vector alloc_mmbtu = contMgmt.getAlloc_mmbtu();
Vector alloc_mt = contMgmt.getAlloc_mt();
// double convt_mmbtu_to_mt = 51.9;//HA20200403

double alloc_totMMBTU = contMgmt.getAlloc_totMMBTU();
double alloc_totMT = contMgmt.getAlloc_totMT();
String custName = contMgmt.getCustName();
String custAbbr = contMgmt.getCust_nm();
Vector all_cust = contMgmt.getAll_cust();
Vector Vcust_nm = contMgmt.getVcust_nm();
%>
<div class="tab-content">

<div class="tab-pane active" id="hcasreport">
<!-- Default box -->
<div class="box mb-0">
<form >
<div class="box-header with-border" style="background-color:#ffe57f;">
 <div class="form-group col-md-2">
 	 <label for="">From Date</label>
      <div class='input-group date' id='datetimepicker'>
		<input type='text' class="form-control" id="d1" type="text"  name="from_date"  value="<%=from_date%>" onBlur="validateDate(this);" onchange="compareDates(this);"/>
		<span class="input-group-addon">
			<i class="fa fa-calendar"></i>
		</span>
	</div>
 </div>
 
 <div class="form-group col-md-2">
 	 <label for="">To Date</label>
      <div class='input-group date' id='datetimepicker1'>
			<input type='text' class="form-control" id="d1" type="text" name="to_date"  value="<%=to_date%>" onBlur="validateDate(this);" onchange="compareDates(this);"/>
			<span class="input-group-addon">
				<i class="fa fa-calendar"></i>
			</span>
		</div>
 </div>
 
 <div class="form-group col-md-2">
 	<label for="">Customer</label>
    <select class="form-control"  id="custnm" name="customer_name" onchange="refreshPage();">
	<option  value="" selected="selected">-Select-</option>
	<option  value="all" >-All-</option>
	<%for(int i=0;i<customer_name.size();i++){ %>
		<option  value="<%=customer_id.elementAt(i) %>"><%= customer_name.elementAt(i)%></option>
	<%}%>
	</select>
	<%if(!cust_id.equals("")){%>
		<script>
			document.forms[0].customer_name.value="<%=cust_id%>";
		</script>
	<%}%> 
 </div>
<div class="form-group col-md-2">
 	 <label for="">&nbsp;</label>
 	  <div class='input-group'>
     	<button type="button"  class="btn btn-primary"   name="viewList" value="View" onclick="refreshPage();" > View List </button>
     </div>
 </div>
 
 <div class="form-group col-md-2">
 	 <label for="">&nbsp;</label>
 	  <div class='input-group'>
     	<button type="button"  class="btn btn-warning" id="btn" onclick="fnExcel('Nomination_VS_Actual_Supply_Report');"  <%--  fnExcelReport() onclick="tableToExcel('example', 'Nom_Vs_Actual_Supply_of__<%=cust_nm%>___<%=from_date%>_To_<%=to_date%>___AT_<%=format2.format(date)%>_<%=time%>');" --%> value="export" >Export To Excel</button>
     </div>
 </div>
</div>

<%	try
	{ %>	
		<div class="box-body table-responsive no-padding">
			<div class="table-responsive">
				<table id="example" class="table table-bordered table-hover">
					<thead>
					<tr>
						<td colspan="8" align="center" style="font-size: 13px;font-weight: bold;"> <font color="Blue;"><%=all_cust%></font> Allocation vs Actual Supply Report </td></tr>
						<tr class="info">
							<th rowspan="2" class="text-center">Customer</th>
							<th rowspan="2" class="text-center">Gas Date</th>
							<th rowspan="2" class="text-center">Plant Name</th>
							<th rowspan="2" class="text-center">SN No.</th>
							<th colspan="2" class="text-center">Buyer Nomination</th>
							<th colspan="2" class="text-center">Actual Supply </th>
						</tr>
						<tr class="info" >
							<th class="text-center">MMBTU</th>
							<th class="text-center">MT</th>
							<th class="text-center">MMBTU</th>
							<th class="text-center">MT</th>
						</tr>
					</thead>
					<tbody>
						<%
					
					int k = 0 ;
					String prev_dt="";
					for (int i = 0 ; i < contract_wise_gas_dt.size(); i++) {
						
					%>
						<%for (int j = 0 ; j < Integer.parseInt(Vcnt.elementAt(i)+"") ; j++) {
						%>
						<tr>
							<td class="text-center">
								<%=Vcust_nm.elementAt(k) %>
							</td>
							<td class="text-center" >
							<%if(prev_dt.equals(contract_wise_gas_dt.elementAt(i))){ %>
							
							<%}else{ %>
								<%=contract_wise_gas_dt.elementAt(i) %>
							<%} %>
							</td>
							<td class="text-center" ><%=Vplant_nm.elementAt(k) %></td>
							<td class="text-center" >SN - <%=Vsn_no.elementAt(k) %></td>
<%-- 							<td class="text-center"><%=Vcont_st_dt.elementAt(k) %></td> --%>
<%-- 							<td class="text-center"><%=Vcont_end_dt.elementAt(k) %></td> --%>
							<td class="text-right" ><%=Vqty_mmbtu.elementAt(k) %></td>
							<td class="text-right"><%=nf.format(Vqty_mt.elementAt(k)) %></td>
							<td class="text-right"><%=alloc_mmbtu.elementAt(k) %></td>
							<td class="text-right"><%=nf.format(alloc_mt.elementAt(k)) %></td>
							
						</tr>				
						<%prev_dt = contract_wise_gas_dt.elementAt(i)+"";
						k++;} %>
					<%}%>
					
					<tr>
						<th colspan="4" class="text-center">TOTAL</th>
						<th class="text-right"><%=nf1.format(totMMBTU) %></th>
						<th class="text-right"><%=nf.format(totMT) %></th>
						<th class="text-right"><%=nf1.format(alloc_totMMBTU) %></th>
						<th class="text-right"><%=nf.format(alloc_totMT) %></th>
					</tr>
						
					</tbody>
				</table>
			</div>
<% } 
	catch(Exception e)
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
todayHighlight: true,
orientation: "bottom auto"
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