<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date.*"%>
<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/tlu.css">

<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js" ></script>
<script type="text/javascript">
/* $(document).ready(function()
{
var k1 = document.forms[0].total_truck_avail.value;
for(var k = 0;k<k1;k++) 
{
	var dtt = ".datepick"+k;
	$(dtt).datepicker({
	changeMonth: true,
	changeYear: true,
	format: "dd/mm/yyyy",
	language: "en",
	autoclose: true,
	todayHighlight: true
	});
}
}); */
function refreshPage(obj)
{

	var api_url = $("#urllink").html();
	var modCd = document.getElementById("modCd").value;
	var formId = document.getElementById("formId").value; 
	var subModUrl = document.getElementById("subModUrl").value; 
	var gas_date = document.getElementById("d1").value;
	var gen_date = document.getElementById("d2").value;
	var write_permission = document.getElementById("write_permission").value; //document.forms[0].write_permission.value;
	var delete_permission = document.getElementById("delete_permission").value; //document.forms[0].delete_permission.value;
	var print_permission = document.getElementById("print_permission").value; //document.forms[0].print_permission.value;
	var approve_permission = document.getElementById("approve_permission").value; //document.forms[0].approve_permission.value;
	var audit_permission = document.getElementById("audit_permission").value; //document.forms[0].audit_permission.value;
	
	alert("../master/frm_mst_truckLoading.jsp?gas_date="+gas_date+"&gen_date="+gen_date+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+
			"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&api_url="+api_url);
	location.replace("../master/frm_mst_truckLoading.jsp?gas_date="+gas_date+"&gen_date="+gen_date+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+
					"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&api_url="+api_url);
}

function truckLoadAuto()
{
	var i = 0;
	var flag = true;
	var msg = "First Check The Following Fields :\n\n";
	var gcv = document.getElementById("gcv").value;
	var ncv = document.getElementById("ncv").value;
	var gas_date = document.getElementById("d1").value;
	var gen_date = document.getElementById("d2").value;

	/*if(gas_date==null || gas_date=='0' || gas_date=='' || gas_date==' ' || gas_date=='  ' || gas_date=='  ')
	{
		msg += "Please Enter The Correct Loading Date For Loading Day Field !!!\n";
		flag = false;
	}
	if(gen_date==null || gen_date=='0' || gen_date=='' || gen_date==' ' || gen_date=='  ' || gen_date=='  ')
	{
		msg += "Please Enter The Correct GENERATION DATE For GEN DAY Field !!!\n";
		flag = false;
	}
	if(gcv==null || gcv=='0' || gcv=='' || gcv==' ' || gcv=='  ' || gcv=='   ')
	{
		msg += "Please Enter The Correct HEAT VALUE For GCV Field !!!\n";
		flag = false;
	}
	if(ncv==null || ncv=='0' || ncv=='' || ncv==' ' || ncv=='  ' || ncv=='   ')
	{
		msg += "Please Enter The Correct HEAT VALUE For NCV Field !!!\n";
		flag = false;
	}
	if(gas_date!=null && gas_date!='0' && gas_date!='' && gas_date!=' ' && gas_date!='  ' && gas_date!='  ')
	{
		if(gen_date!=null && gen_date!='0' && gen_date!='' && gen_date!=' ' && gen_date!='  ' && gen_date!='  ')
		{
			var value = compareDate(gen_date,gas_date);
		if(value==1)
		  	{
		    	msg += "Generation Day Must Be Less Than Or Equal To Loading Date !!!\n";
		    	flag = false;
		  	}
		}
	} */
	
	var totalqty = $('#totalqty').val();
	var total_loaded_vol = 0;
	var count = 0;
		for(var j=0;j<parseFloat(totalqty);j++) {
			
			var nomQtyDate = document.getElementByID('nomQtyDate'+i).val();
			var nomQtyTime = document.getElementByID('nomQtyTime'+i).val();
			var nomQtyValue = document.getElementByID('nomQtyValue'+i).val();
			
			if(nomQtyTime=='') {
				msg += "Please Enter Load Time!!\n";
				flag = false;
			} 
		   if(nomQtyDate=='') {
				msg += "Please Enter Load Date";
				flag = false;
			} 			
			if(nomQtyValue=='' || parseFloat(nomQtyValue)==0) {
				msg += "Please Enter Loaded Volume";
				flag = false;
			} else {
				total_loaded_vol += parseFloat(nomQtyValue);
			}
			count++;
		}
	if(count==0) {
		flag = false;
		msg += "Please Load At Least One Truck to Submit!!!\n";
	}
		
	if(flag)
	{
		var a = confirm("Do You Want To Submit Truck Loading Details ???");
		if(a)
		{
			var b = event.target.id;
			if(b == "save"){
				//document.forms[0].option.value ="submitAutoLoadTruckData";
    			//document.forms[0].action = "../servlet/Frm_MgmtV2";
    			document.forms[0].submit();
			}
			
		}
	}
	else
	{
		alert(msg);
	}
}

function compareDt(indx){
	var msg='';
	var strt_date=document.getElementById('load_st_day'+indx).value;
	var end_date=document.getElementById('load_end_day'+indx).value;
	var sysdt = strt_date.split(/[/: ]/);
	var end_dt = end_date.split(/[/: ]/);
	var startDt = new Date(sysdt[2], sysdt[1] - 1, sysdt[0]);
	var endDt = new Date(end_dt[2], end_dt[1] - 1, end_dt[0]);
	
	if(startDt>endDt){
		msg+="Please Enter Valid Date!!";	
	}
	if(msg!=''){
		alert(msg);
		document.getElementById('load_st_day'+indx).value='';
		document.getElementById('load_end_day'+indx).value='';
		return false;
	}	
}

function setLoadData(){
	var tot_truck = document.forms[0].total_truck_avail.value;
	for(var i=0;i<tot_truck;i++) {
		document.getElementById("chk"+i).checked = true;
		document.getElementById("chkFlag"+i).value = 'Y';
	}
}
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.contract_mgmt.DataBean_URL_JSON_Data" id="tluDataCap" scope="request"/>
<%
java.text.NumberFormat nf = new java.text.DecimalFormat("###0.00");
utilBean.init();

String current_date = utilBean.getGen_dt();
String tomorrow_date = utilBean.getDate_tomorrow();
String gas_date = request.getParameter("gas_date") == null ? tomorrow_date : request.getParameter("gas_date");
String gen_date = request.getParameter("gen_date") == null ? current_date : request.getParameter("gen_date");

String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");
String formName = request.getParameter("FormName")==null?"":request.getParameter("FormName");
String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();

String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");

String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
String error_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
double gcv = Double.parseDouble(request.getParameter("gcv")==null?"9802.80":request.getParameter("gcv"));
double ncv = Double.parseDouble(request.getParameter("ncv")==null?"8831.35":request.getParameter("ncv"));

/* String api_url = request.getParameter("api_url")==null?"":request.getParameter("api_url");
System.out.println("JSP Before api_urls : "+api_url);		
tluDataCap.setURLRequest(api_url);

System.out.println("JSP after api_urls : "+api_url); */

tluDataCap.init(request, response);
///Vector cmroomkey = tluDataCap.getCM_ROOM_FY();
Vector nomQtyDate = tluDataCap.getNomQtyDate() ;
Vector nomQtyTime = tluDataCap.getNomQtyTime();
Vector nomQtyValue = tluDataCap.getNomQtyValue();
%>
<body>
<div class="tab-content">
	<div class="tab-pane active" id="truckloading">
	<!-- Default box -->
		<div class="box mb-0">
			<form  method="post" action="../servlet/Frm_MgmtV2" id="tluDataCapJson">
				<div class="box-header with-border">
					<%if(msg.length()>5){%>	
						<div class="box-body table-responsive no-padding">
							<table class="table  table-bordered">
								<thead>   
									<tr class="info">
											<th class="text-center"  style="color: blue;"><%=msg %></th>
									</tr>
								</thead>
							</table>
						</div>
					<%}%>
					<%if(!error_msg.equals("")){%>		
						<div class="box-body table-responsive no-padding">
							<table class="table  table-bordered">
								<thead>   
									<tr class="info">
											<th class="text-center"  style="color: red;"><%=error_msg %></th>
									</tr>
								</thead>
							</table>
						</div>
					<%}%>
					<div class="box-header with-border main-header" >
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<div class="form-group mb-0 row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<label> Gas Day </label>
									<div class="form-group">
										<div class='input-group date' id='datetimepicker1'>
											<input type='text' class="form-control" id="d1" type="text" name="gas_date" value="<%=gas_date%>" onchange="refreshPage('');" />
											<span class="input-group-addon">
												<i class="fa fa-calendar"></i>
											</span>
										</div>
									</div>
								</div>
							
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<label> Loading Date </label>
									<div class="form-group mb-0">
										<div class='input-group date' id='datetimepicker2' >
											<input type='text' class="form-control" id="d2" type="text" name="gen_date" value="<%=gas_date%>"/>
												<span class="input-group-addon">
													<i class="fa fa-calendar"></i>
												</span>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="col-lg-1 col-md-2 col-sm-6 col-xs-12 hidden-xs hidden-sm"></div>
						<div class="col-lg-7 col-md-7 col-sm-6 col-xs-12">
							<div class="row">
								<div class="col-lg-12 text-center">
									<label> Energy Base </label>
								</div>
							</div>
						
							<div class="form-group row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<div class="form-group input-group mb-0">
										<span class="input-group-addon">
											<input type="radio" name="rd" checked="checked" > GCV
										</span>
										<input type="text" class="form-control" id="gcv" name="gcv"  value="<%=gcv%>" style="text-align: right;" readonly="readonly">
										<span class="input-group-addon">  kcal/SCM </span>
									</div>
								</div>
							
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<div class="form-group input-group mb-0">
										<span class="input-group-addon">
											<input type="radio" name="rd"> NCV
										</span>
										<input type="text" class="form-control" id="ncv" name="ncv" value="<%=ncv%>" style="text-align: right;" readonly="readonly">
										<span class="input-group-addon">  kcal/SCM </span>
									</div>
								</div>
							</div>
						</div>
<!-- row wnd -->	</div>

				</div>
				<!-- <div class="box-body">
					<div class="row">
						<div class="col-md-12">
							<div class="for-group">
								<a href="" id="urllink" onclick="refreshPage(this);">
								 	https://pejjbt-s-8082.asia-pac.shell.com/piwebapi/streamsets/end?webid=F1DPjFOakldGMUu0YR_7zapK7wf1cIAARFNBUFBJQ09MTFxIQVo6SEFaTE5HLlBWSS4xMDBMSTEwMi5QVg&webid=F1DPjFOakldGMUu0YR_7zapK7wg1cIAARFNBUFBJQ09MTFxIQVo6SEFaTE5HLlBWSS4xMDBMSTIwMi5QVg 
								</a>
								<a href="" id="urllink" onclick="refreshPage(this);">http://billpower.in/BP10/CheckLogin.php?option=getListData&Param={%22usercd%22:%22300001%22,%22menuId%22:%220%22,%22selectedDropId%22:%221%22,%22selectedStatus%22:%220%22,%22extraSpinnerId%22:%228%22,%22selectedCompId%22:%220%22}</a>
							</div>
						</div>
					</div>
				</div> -->
				<div class="box-body table-responsive no-padding">
					<table id="mynomQtyList" class="table  table-bordered">	
				  		<thead>
							<tr class="info">
								<th class="text-center">Date</th>
								<th class="text-center">Time (HH:MM)</th>
								<th class="text-center">Schedule Qty</th>
							</tr>
						</thead>
						<tbody style="height: 200px;">
							<%if(nomQtyDate.size() > 0){%>
								<%for(int i=0;i<nomQtyDate.size();i++){%>
									<tr>
										<td class="text-center"><input type="text" class="form-control" id="nomQtyDate<%=i%>" name="nomQtyDate<%=i%>" value="<%=nomQtyDate.elementAt(i)%>"></td>
										<td class="text-center"><input type="text" class="form-control" id="nomQtyTime<%=i%>" name="nomQtyTime<%=i%>" value="<%=nomQtyTime.elementAt(i)%>"></td>
										<td class="text-center"><input type="text" class="form-control" id="nomQtyTime<%=i%>" name="nomQtyTime<%=i%>" value="<%=nomQtyValue.elementAt(i)%>"></td>
									</tr>
								<%}%>
							<%}else{%>
								<tr><td class="text-center" colspan="3"><b style="color: red;font-weight: bold;">Data Not Available. !</b></td></tr>
							<%}%>
							
							<%-- <%for(int i=0;i<cmroomkey.size();i++){%>
								<tr>
									<td class="text-center" colspan="3"><input type="text" class="form-control" id="nomQtyDate<%=i%>" name="nomQtyDate<%=i%>" value="<%=cmroomkey.elementAt(i)%>"></td>
								</tr>
							<%}%> --%>
								
							
						</tbody>
					</table>
				</div>
				
				
				<input type="hidden" id="modCd" name="modCd" value="<%=modCd%>">
				<input type="hidden" id="formId" name="formId" value="<%=formId%>">
				<input type="hidden" id="subModUrl" name="subModUrl" value="<%=subModUrl%>">
				<input type="hidden" id="write_permission" name="write_permission" value="<%=write_permission%>">
			   	<input type="hidden" id="delete_permission"  name="delete_permission" value="<%=delete_permission%>">
			   	<input type="hidden" id="print_permission"  name="print_permission" value="<%=print_permission%>">
			   	<input type="hidden" id="approve_permission"  name="approve_permission" value="<%=approve_permission%>">
			   	<input type="hidden" id="audit_permission"  name="audit_permission" value="<%=audit_permission%>">
			   	
			   	<input type="hidden" name="totalqty" value="<%//=nomQtyDate.size()%>">
				<input type="hidden" name="option" value="submitAutoLoadTruckData">
				<input type="hidden" name="index_count" value="">
			</form>
			
			<div class="box-footer">
				<div class="row">
					<div class="col-sm-12 text-right">
						<button type="button" class="btn btn-warning" name="reSetPage" value="Reset" onClick="refreshPage('');"> Reset </button>
						<button type="button" class="btn btn-success" id="save" name="save" value="noTruck" onClick="truckLoadAuto();"> Submit </button>
					</div>
				</div>
			</div>
			
			
		</div>
	</div>
</div>


</body>
</html>