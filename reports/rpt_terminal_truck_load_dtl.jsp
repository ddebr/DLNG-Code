<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/tlu.css">


<!-- <script type="text/javascript" src="../js/jquery-3.3.1.js"></script> -->
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>

<!-- accordian -->
<!-- <script src="../js/bootstrap.min.js"></script> -->
<script src="../js/jquery.min.js"></script>
<!-- end -->

<style>
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
 
 .table-responsive {
    max-height:400px;
}   		 
</style>
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

/* for accordian */
$(document).ready(function () {
    $(".tbtn").click(function () {
        $(this).parents(".custom-table").find(".toggler1").removeClass("toggler1");
        $(this).parents("tbody").find(".toggler").addClass("toggler1");
        $(this).parents(".custom-table").find(".fa-minus-circle").removeClass("fa-minus-circle");
        $(this).parents("tbody").find(".fa-plus-circle").addClass("fa-minus-circle");
    });
});
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
	double convt_mmbtu_to_mt= (double) session.getAttribute("convt_mmbtu_to_mt");//HA20201229
	
	contMgmt.setCallFlag("TerminalTruckWiseRpt");
	contMgmt.setFrom_date(start_dt);
	contMgmt.setTo_date(current_date);
	contMgmt.setConvt_mmbtu_to_mt(convt_mmbtu_to_mt);
	contMgmt.init();
	
	Vector daily_Seller_Nom_Sn_No = contMgmt.getDaily_Seller_Nom_Sn_No();
	Vector daily_Seller_Nom_Abbr = contMgmt.getDaily_Seller_Nom_Abbr();
	Vector daily_Seller_Nom_Plant_Seq_No = contMgmt.getDaily_Seller_Nom_Plant_Seq_No();
	Vector daily_Seller_Nom_Qty_Mmbtu = contMgmt.getDaily_Seller_Nom_Qty_Mmbtu();
	Vector daily_Buyer_Nom_Plant_Seq_No = contMgmt.getDaily_Buyer_Nom_Plant_Seq_No();
	
	Vector daily_Buyer_Nom_Plant_Seq_Abbr = contMgmt.getDaily_Buyer_Nom_Plant_Seq_Abbr();
	Vector daily_Buyer_Nom_Qty_Mmbtu = contMgmt.getDaily_Buyer_Nom_Qty_Mmbtu();
	
	double fsru_tank_vol = contMgmt.getFsru_tank_vol();
	double int_tank_vol = contMgmt.getInt_tank_vol();
	
	int index = 0;
	Vector daily_Seller_Nom_Mapping_Id = contMgmt.getDaily_Seller_Nom_Mapping_Id();
	Vector tank_truck_id = contMgmt.getTank_truck_id();
	Vector tank_truck_nm = contMgmt.getTank_truck_nm();
	
	Map truck_load_start_day = contMgmt.getTruck_load_start_day();
	Map truck_load_start_tm = contMgmt.getTruck_load_start_tm();
	Map truck_load_end_tm = contMgmt.getTruck_load_end_tm();
	Map truck_load_end_day = contMgmt.getTruck_load_end_day();
	Map truck_loaded_vol = contMgmt.getTruck_loaded_vol();
	Map truck_unloaded_vol = contMgmt.getTruck_unloaded_vol();
	Vector all_dates = contMgmt.getAll_dates();
	Vector tank_truck_capacityM3 = contMgmt.getTank_truck_capacityM3(); //HA20181228
	
	Map nomDt=contMgmt.getNomDt();
	Map nomQty=contMgmt.getNomQty(); 
	
	Map schDt=contMgmt.getSchDt();
	Map schQty=contMgmt.getSchQty(); 
// 	double convt_mmbtu_to_mt = 51.9;//HA20200403 
	Vector VTruck_Nm = contMgmt.getVTruck_Nm();
	Vector VTruck_Sch_Dt = contMgmt.getVTruck_Sch_Dt();
	Vector VTruck_Sch_Tm = contMgmt.getVTruck_Sch_Tm();
	Vector VTruck_Load_Dt = contMgmt.getVTruck_Load_Dt();
	Vector VTruck_Load_Tm = contMgmt.getVTruck_Load_Tm();
	Vector VTruck_Vol = contMgmt.getVTruck_Vol();
	Vector VTruck_Vol_Unit = contMgmt.getVTruck_Vol_Unit();
	Vector VTruck_Ene = contMgmt.getVTruck_Ene();
	Vector VTruck_Ene_Unit = contMgmt.getVTruck_Ene_Unit();
	Vector VTruck_Wt = contMgmt.getVTruck_Wt();
	Vector VTruck_Wt_Unit = contMgmt.getVTruck_Wt_Unit();
	Vector VMapp_Id = contMgmt.getVMapp_Id();
	Vector VSch_Id = contMgmt.getVSch_Id();
	Vector VTruck_Trans_Nm = contMgmt.getVTruck_Trans_Nm();
	Vector VCust_Nm = contMgmt.getVCust_Nm();
	Vector VCust_Plant_Nm = contMgmt.getVCust_Plant_Nm();
	Vector VTruck_Seller_Remarks = contMgmt.getVTruck_Seller_Remarks();
	Vector driver_nm = contMgmt.getDriver_nm();
// 	Vector VCnt = contMgmt.getVCnt();
	String TbackColor = "";
	Vector VTRev_no = contMgmt.getVTRev_no();
	
	Vector HTruck_Nm = contMgmt.getHTruck_Nm();
	Vector HCust_Plant_Nm = contMgmt.getHCust_Plant_Nm();
	Vector HCust_Nm = contMgmt.getHCust_Nm();
	Vector Hruck_Sch_Dt = contMgmt.getHTruck_Sch_Dt();
	Vector Vcnt = contMgmt.getVCnt();
	Vector HTruck_Vol = contMgmt.getHTruck_Vol();
	Vector HTruck_Wt = contMgmt.getHTruck_Wt();
	Vector HTruck_Wt_Unit = contMgmt.getHTruck_Wt_Unit();
	Vector HTruck_Trans_Nm = contMgmt.getHTruck_Trans_Nm();
	Vector Hdriver_nm = contMgmt.getHdriver_nm();
	Vector HTruck_Sch_Dt = contMgmt.getHTruck_Sch_Dt();
	Vector HTruck_Sch_Tm = contMgmt.getHTruck_Sch_Tm();
	Vector HTruck_Seller_Remarks = contMgmt.getHTruck_Seller_Remarks();
	Vector HRev_no = contMgmt.getHRev_no();
	Vector HcheckPost_nm = contMgmt.getHcheckPost_nm();
	Vector VCust_checkpost_nm = contMgmt.getVCust_checkpost_nm();
// 	System.out.println("HTruck_Nm*****"+HTruck_Nm.size());
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
			<button type="button"  class="btn btn-warning" onclick="fnExcel('Terminal_Report');" id="btn" value="export" >Export To Excel</button>
		</div>
	</div>	
</div>
</div>
<div class="table-responsive">
				<table id="example" class="table table-bordered custom-table">
				<thead>
			<tr class="info">
				<th rowspan="2" colspan="2" >Gas Date</th>
				<th rowspan="2" class="text-center">Customer<br>Name</th>	
				<th rowspan="2" class="text-center">Plant Location</th>	
				<th rowspan="2" class="text-center">Rev No. (max)</th>	
<!-- 				<th rowspan="2" class="text-center" >Load End Date Time<br>(DD/MM/YYYY HH:MM)</th> -->
				<th colspan="2" class="text-center" >Nomination Qty</th>
				
				<th rowspan="2" class="text-left">Transporter Name</th>
				<th rowspan="2" class="text-center">Truck Reg No.</th>
				<th rowspan="2" class="text-left">Driver Name</th>
				<th colspan="2" class="text-center" >Scheduled slot</th>
				<th rowspan="2" class="text-left" >Check post</th>
				<th rowspan="2" class="text-left" >Remarks</th>
			</tr>
			
			 <tr class="info">
				<th colspan="1" rowspan="1" class="text-center">MMBTU </th>
				<th colspan="1" rowspan="1" class="text-center">Tonne(s)</th> 
				<th colspan="1" rowspan="1" class="text-center">Date<br>(DD/MM/YYYY)</th> 
				<th colspan="1" rowspan="1" class="text-center">Time<br>(HH:MM)</th> 
			</tr> 
			</thead>
			<%String PrevCustNm=""; int seqNo=1;int m=0,p=1;%>
			<%int i = 0;
			for(int k = 0 ; k < HTruck_Nm.size() ; k++){ %>
			<tbody>
				<tr style="font-size: 13px;" 
					<%if(Integer.parseInt(Vcnt.elementAt(k)+"") == 0) {%>
						title="Single Revision" 
					<%}else{ %>
						title="<%=Vcnt.elementAt(k) %> : Revision(s)"
					<%} %>>
					
					<%if(Integer.parseInt(Vcnt.elementAt(k)+"") == 0) {%>
						<td class="page-header"></td>
					<%}else{ %>
						<td class="page-header">
							<button type="button" class="tbtn">
								<i class="fa fa-plus-circle"></i>
							</button>
						</td>
					<%} %>
						
						<td><%=Hruck_Sch_Dt.elementAt(k) %></td>
						<td  align="left">
						<%=HCust_Nm.elementAt(k) %>
						</td>
						<td  align="left"><%=HCust_Plant_Nm.elementAt(k) %></td>
	<%-- 								<td align="center"><%=VTruck_Nm.elementAt(i) %> [<font color="blue"> </font>&#13221; ]</td> --%>
						<td align="center"><%=HRev_no.elementAt(k) %></td>
	<%-- 								<td align="center"><%=VTruck_Load_Dt.elementAt(i) %>&nbsp;<%=VTruck_Load_Tm.elementAt(i) %></td> --%>
						<td align="right"><%=HTruck_Vol.elementAt(k) %>&nbsp;<%//=VTruck_Vol_Unit.elementAt(k) %></td>
	<%-- 								<td align="right"><%=VTruck_Ene.elementAt(k) %>&nbsp;<%=VTruck_Ene_Unit.elementAt(k) %></td> --%>
						<td align="right"><%=HTruck_Wt.elementAt(k) %>&nbsp;<%//=VTruck_Wt_Unit.elementAt(k) %></td>
						<td align="left"><%=HTruck_Trans_Nm.elementAt(k) %></td>
						<td align="center"><%=HTruck_Nm.elementAt(k) %></td>
						<td align="center"><%=Hdriver_nm.elementAt(k) %></td>
						<td align="center"><%=HTruck_Sch_Dt.elementAt(k) %></td>
						<td align="center"><%=HTruck_Sch_Tm.elementAt(k) %></td>
						<td align="center"><%=HcheckPost_nm.elementAt(k) %></td>
						<td align="center"><%=HTruck_Seller_Remarks.elementAt(k) %></td>
				</tr>
				<%
// 				System.out.println("Vcnt.elementAt(k)******"+Vcnt.elementAt(k));
				for(int j=0;j< Integer.parseInt(Vcnt.elementAt(k)+"");j++){ %>
					  
					<tr class="toggler" style="background-color: white;font-size: 12px;" id="tr<%=i%>">
						<td colspan="2" align="center"><%=VTruck_Sch_Dt.elementAt(i) %></td>
					
						<td  align="left">
						<%=VCust_Nm.elementAt(i) %>
						</td>
						<td  align="left"><%=VCust_Plant_Nm.elementAt(i) %></td>
<%-- 								<td align="center"><%=VTruck_Nm.elementAt(i) %> [<font color="blue"> </font>&#13221; ]</td> --%>
						<td align="center"><%=VTRev_no.elementAt(i) %></td>
<%-- 								<td align="center"><%=VTruck_Load_Dt.elementAt(i) %>&nbsp;<%=VTruck_Load_Tm.elementAt(i) %></td> --%>
						<td align="right"><%=VTruck_Vol.elementAt(i) %>&nbsp;<%//=VTruck_Vol_Unit.elementAt(i) %></td>
<%-- 								<td align="right"><%=VTruck_Ene.elementAt(i) %>&nbsp;<%=VTruck_Ene_Unit.elementAt(i) %></td> --%>
						<td align="right"><%=VTruck_Wt.elementAt(i) %>&nbsp;<%//=VTruck_Wt_Unit.elementAt(i) %></td>
						<td align="left"><%=VTruck_Trans_Nm.elementAt(i) %></td>
						<td align="center"><%=VTruck_Nm.elementAt(i) %></td>
						<td align="center"><%=driver_nm.elementAt(i) %></td>
						<td align="center"><%=VTruck_Sch_Dt.elementAt(i) %></td>
						<td align="center"><%=VTruck_Sch_Tm.elementAt(i) %></td>
						<td align="center"><%=VCust_checkpost_nm.elementAt(i) %></td>
						<td align="center"><%=VTruck_Seller_Remarks.elementAt(i) %></td>
					</tr>
				<% i++;} %>
				</tbody>
			<%} %>
</Table>
</div>
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