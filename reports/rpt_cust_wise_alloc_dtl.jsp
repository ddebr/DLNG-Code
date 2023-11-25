<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/tlu.css">


<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
<style type="text/css">
.custom-table{border-collapse:collapse;width:100%;border:solid 1px #c0c0c0;}
            .custom-table th{text-align:center;padding:8px;border:solid 1px #c0c0c0;},.custom-table td{text-align:left;padding:8px;border:solid 1px #c0c0c0}
            .custom-table th{color:#000080}
            .custom-table tr:nth-child(odd){background-color:#f7f7ff}
            .custom-table>thead>tr{background-color:#dde8f7!important}
            .tbtn{border:0;outline:0;background-color:transparent;font-size:13px;cursor:pointer}
            .toggler{display:none}
            .toggler1{display:table-row;}
            .custom-table a{color: #0033cc;}
            .custom-table a:hover{color: #f00;}
            .page-header{background-color: #eee;}
            /* Define the hover highlight color for the table row */
    		.custom-table tr:hover {background-color: #ffff99;}
    		.custom-table tr:hover {background-color: #ffff99;}

</style>
<script type="text/javascript">

$(document).ready(function () {
    $(".tbtn").click(function () {
    	
        $(this).parents(".custom-table").find(".toggler1").removeClass("toggler1");
        $(this).parents("tbody").find(".toggler").addClass("toggler1");
        
        $(this).parents(".custom-table").find(".fa-minus-circle").removeClass("fa-minus-circle");
        $(this).parents("tbody").find(".fa-plus-circle").addClass("fa-minus-circle");
        
        
    });
});

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
		msg = 'Enter From Date!!\n';
	}
	if(to_date=='') {
		msg += 'Enter To Date!!\n';
	}
	if(msg=='') {
		var print_permission = document.forms[0].print_permission.value;
		var form_nm = document.forms[0].form_nm.value;
		location.replace("../reports/rpt_master.jsp?to_date="+to_date+"&from_date="+from_date+"&print_permission="+print_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl);
	} else {
		alert(msg);
	}
}

/*  function fnExcel()
{
    var tab_text="<table border='2px'><tr>";
    var textRange; var j=0;
    tab = document.getElementById('example'); // id of table
    
    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
        //tab_text=tab_text+"</tr>";
    }

    tab_text=tab_text+"</table>";
    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
    {
       /*  txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"ActionDetailedRpt.xls"); */
       
        /*if (typeof Blob !== "undefined") {
        	//use blobs if we can
            tab_text = [tab_text];
            //convert to array
            var blob1 = new Blob(tab_text, { type: 'text/html' });
            window.navigator.msSaveBlob(blob1, 'DLNG.xls');
            return (true);
        } else {
            //otherwise use the iframe and save
            //requires a blank iframe on page called txtArea1
            txtArea1.document.open("text/html", "replace");
            txtArea1.document.write(tab_text);
            txtArea1.document.close();
            txtArea1.focus();
            sa = txtArea1.document.execCommand("SaveAs", true, "DLNG.xls");
        }
    }  
    else{ 
    	var filename = "Delivery_Report"
    	var a = document.createElement('a');
    	var data_type = 'data:application/vnd.ms-excel';
    	a.href = data_type + ', ' + encodeURIComponent(tab_text);
    	a.download = filename + '.xls';
    	a.click();
    	//other browser not tested on IE 11
//         sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  
    }
//     return (sa);
}  */

function fnExcel(start_dt,end_dt)
{	
   	  		
	if(!newWindow || newWindow.closed)
	{
		newWindow= window.open("../reports/xls_cust_wise_alloc_dtl.jsp?from_date="+start_dt+"&to_date="+end_dt,"top=10,left=70,width=900,height=700,scrollbars=1");
	}
	else 
	{
		newWindow.close();
	    newWindow= window.open("../reports/xls_cust_wise_alloc_dtl.jsp?from_date="+start_dt+"&to_date="+end_dt,"Hiren","top=10,left=70,width=900,height=700,scrollbars=1");
	}			
}
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Report_GenerationV2" id="contMgmt" scope="page"/>  
<%
	java.text.NumberFormat nf = new java.text.DecimalFormat("###0.00");
	utilBean.init();
	String current_date = utilBean.getGen_dt();
	String temp_dt[] = current_date.split("/");
	String temp_mon = temp_dt[1];
	String temp_yr = temp_dt[2];
	String start_dt = "01/"+temp_mon+"/"+temp_yr;
	
// 	System.out.println("start_dt***"+start_dt);
	
	String tomorrow_date = utilBean.getDate_tomorrow();
	start_dt = request.getParameter("from_date")==null?start_dt:request.getParameter("from_date"); //Modified
	current_date = request.getParameter("to_date")==null?current_date:request.getParameter("to_date"); //Modified
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String formName = request.getParameter("FormName")==null?"":request.getParameter("FormName");
	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212
	
	contMgmt.setCallFlag("customerWiseRpt");
	contMgmt.setFrom_date(start_dt);
	contMgmt.setTo_date(current_date);
	contMgmt.init();
	
	Vector daily_Seller_Nom_Sn_No = contMgmt.getVsn_cd();
	Vector daily_Seller_Nom_Abbr = contMgmt.getVcust_abbr();
	Vector daily_Seller_Nom_Plant_Seq_No = contMgmt.getDaily_Seller_Nom_Plant_Seq_No();
	Vector daily_Seller_Nom_Qty_Mmbtu = contMgmt.getDaily_Seller_Nom_Qty_Mmbtu();
	Vector daily_Buyer_Nom_Plant_Seq_No = contMgmt.getVplant_cd();
	
	Vector daily_Buyer_Nom_Plant_Seq_Abbr = contMgmt.getVplant_abbr();
	Vector daily_Buyer_Nom_Qty_Mmbtu = contMgmt.getDaily_Buyer_Nom_Qty_Mmbtu();
	
	int index = 0;
	Vector daily_Seller_Nom_Mapping_Id = contMgmt.getVdist_map_id();
	
	Vector Vnom_mmbtu_qty = contMgmt.getVnom_mmbtu_qty();
	Vector Vsch_mmbtu_qty = contMgmt.getVsch_mmbtu_qty();
	Vector Vnom_dt = contMgmt.getVnom_dt();
	Vector VSubRecCnt = contMgmt.getVSubRecCnt();
	Vector Vtruck_nm = contMgmt.getVtruck_nm();
	Vector Vload_start_tm = contMgmt.getVload_start_tm();
	Vector Vloaded_vol = contMgmt.getVloaded_vol();
	Vector Vload_end_tm = contMgmt.getVload_end_tm();
	Vector Vloaded_ene = contMgmt.getVloaded_ene();
			
	double convt_mmbtu_to_mt = (double) session.getAttribute("convt_mmbtu_to_mt");//HA20201229
%>
<div class="tab-content">

<div class="tab-pane active" id="hcasreport">
<!-- Default box -->
<div class="box mb-0">
<form >
<div class="box-header with-border main-header" >

<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
	<div class="form-group mb-0 row">
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
			<label> From Date </label>
			<div class="form-group">
				<div class='input-group date' id='datetimepicker'>
					<input type='text' class="form-control" id="d1" type="text"  name="from_date"  value="<%=start_dt%>" onBlur="validateDate(this);" onchange="compareDates(this);"/>
					<span class="input-group-addon">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
	<div class="form-group mb-0 row">
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
			<label>To Date </label>
			<div class="form-group">
				<div class='input-group date' id='datetimepicker1'>
					<input type='text' class="form-control" id="d1" type="text" name="to_date"  value="<%=current_date%>" onBlur="validateDate(this);" onchange="compareDates(this);"/>
					<span class="input-group-addon">
						<i class="fa fa-calendar"></i>
					</span>
				</div>
			</div>
		</div>
	
	</div>
</div>

<div class="col-lg-2 col-md-2 col-sm-3 col-xs-6">
	<div class="form-group mb-0 row">
		<label></label>
		<div class="form-group">
			<button type="button"  class="btn btn-primary"   name="viewList" value="View" onclick="refreshPage();" > View List </button>
		</div>
	</div>	
</div>
<div class="col-lg-2 col-md-2 col-sm-3 col-xs-6">
	<div class="form-group mb-0 row">
		<label></label>
		<div class="form-group">
			<button type="button"  class="btn btn-warning" onclick="fnExcel('<%=start_dt %>','<%=current_date %>');" id="btn" value="export" >Export To Excel</button>
		</div>
	</div>	
</div>
</div>
<%	try
	{ %>
		
	<div class="box-body table-responsive no-padding">
		<table id="example" class="table table-bordered custom-table">
			<thead>
				<tr >
					<th rowspan="2" >Gas Date</th>
					<th rowspan="1" colspan="1" >Nom Qty</th>
					<th rowspan="1" colspan="1" >Sch Qty</th>	
					<th rowspan="2" colspan="1" >Truck Reg No.</th>
					<th rowspan="1" colspan="1"  >Load Start Date Time</th>
					<th rowspan="1" colspan="1"  >Load End Date Time</th>
					<th rowspan="1" colspan="2"  >Loaded Qty</th>
				</tr>
				
				 <tr>
				 	<th colspan="1" rowspan="1" >MMBTU </th>
				 	<th colspan="1" rowspan="1" >MMBTU </th>
				 	<th colspan="1" rowspan="1" >(DD/MM/YYYY HH:MM) </th>
				 	<th colspan="1" rowspan="1" >(DD/MM/YYYY HH:MM) </th>
					<th colspan="1" rowspan="1" >MMBTU </th>
					<th colspan="1" rowspan="1" >MT</th>
					
				</tr> 
			</thead>		
			<%
			int k = 0 ;
			for(int j=0;j<daily_Buyer_Nom_Plant_Seq_No.size();j++){ %>
			<%if(Integer.parseInt(VSubRecCnt.elementAt(j)+"") > 0 ){ %>
			<tbody>
			<tr>
				<td class="page-header">
					<button type="button" class="tbtn">
						<i class="fa fa-plus-circle"></i>
					</button>
				</td>
				
				<td colspan="7" style="background-color: #eee;vertical-align: middle;color: blue;font-weight: bold;font-family: inherit; ">
					<%=(String)daily_Seller_Nom_Abbr.elementAt(j)%>&nbsp;-&nbsp;<%=(String)daily_Buyer_Nom_Plant_Seq_Abbr.elementAt(j)%>&nbsp;(<%=daily_Seller_Nom_Sn_No.elementAt(j) %>)
				</td>
			</tr>
			
			<%
			for(int i = 0; i < Integer.parseInt(VSubRecCnt.elementAt(j)+""); i++){ %>
				<tr class="toggler"  id="tr<%=i%>">
					<td class = "text-center"><%=Vnom_dt.elementAt(k) %></td>
					<td class = "text-right"><%=Vnom_mmbtu_qty.elementAt(k) %></td>
					<td class = "text-right"><%=Vsch_mmbtu_qty.elementAt(k) %></td>
					<td class = "text-center"><%=Vtruck_nm.elementAt(k) %></td>
					<td class = "text-center"><%=Vload_start_tm.elementAt(k) %> </td>
					<td class = "text-center"><%=Vload_end_tm.elementAt(k) %> </td>
					<td class = "text-right"><%=Vloaded_ene.elementAt(k) %> </td>
					<td class = "text-right"><%=Vloaded_vol.elementAt(k) %> </td>
				</tr>
			<% k++;} %>
			</tbody>
			<%}}%>
			</table></div>
			
<%	if(daily_Buyer_Nom_Plant_Seq_Abbr.size()>0) { } else { %>
	<div class="box-body table-responsive no-padding">
		<table class="table  table-bordered">
			<thead>   
				<tr class="info">
					<th class="text-center"  style="color: red;"> Data Not Available!!</th>
				</tr>
			</thead>
		</table>
	</div>
<% } }	
	catch(Exception e)
	{	
		e.printStackTrace();
	} %>

<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
<%-- <input type="hidden" name="form_id" value="<%=formId%>"> --%>
<input type="hidden" name="form_nm" value="<%=formName%>">
	<table width="98%"  border="0" align="center">
		<tr>
			<td>
		    	<input type="hidden" name="print_permission" value="<%=print_permission%>">
		    	<input type="hidden" name="index_count" value="<%=index%>">
			</td>
		</tr>
	</table>
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