<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@ page import ="java.util.Properties" %>
<%@ page import ="java.util.*" %>
<%@ page import ="java.net.*" %>
<%@ page import ="java.io.*" %>
<head>
<%@ include file="../sess/Expire.jsp"%>
<meta http-equiv="Content-Type" content="<%response.setContentType("application/vnd.ms-excel");%>">
<title> TLU </title>
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/tlu.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>

<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js" ></script>
<script type="text/javascript" src="../js/sweetalert.min.js"></script>

<script type="text/javascript">
var newWindow;
var newWindow2;
function refreshPage(){
	var gas_date = document.forms[0].gas_date.value;
	var gen_date = document.forms[0].gen_date.value;
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;

	location.replace("../master/frm_mst_buyerNomination.jsp?gas_date="+gas_date+"&gen_date="+gen_date+
			"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+
			"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+
			"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl);
}

function openJoinTicket(obj,customer,plantName,contractTyp,qtyMMBTU,sallerNomQty,sz,indx,customerAbbr,customerCD,plantCD){
	var customer_contact_cd = "0";
	var gas_date = document.forms[0].gas_date.value;
	var print_permission = document.forms[0].print_permission.value;
	var convt_mmbtu_to_mt = document.forms[0].convt_mmbtu_to_mt.value;
	if(obj.id == "jointickt"){
		var size = parseInt(sz);
		if(size == 1){
			customer_contact_cd =  document.forms[0].customer_cd.value;
		}
		else if(size>1){
			customer_contact_cd =  document.forms[0].customer_cd[indx].value;
		}
		if(!newWindow || newWindow.closed)
		{
			newWindow = window.open("../jointTicket/rpt_joint_ticket.jsp?gas_date="+gas_date+"&customer_cd="+customerCD
					+"&customer_nm="+customer+"&customer_abbr="+customerAbbr
					+"&customer_contact_cd="+customer_contact_cd
					+"&contract_type="+contractTyp+"&plant_cd="+plantCD+"&plant_nm="+plantName
					+"&qty_mmbtu="+qtyMMBTU
					+"&seller_nom_qty="+sallerNomQty+"&print_permission="+print_permission+"&convt_mmbtu_to_mt="+convt_mmbtu_to_mt,
					"JT_Report","top=10,left=10,width=950,height=750,scrollbars=1,menubar=1");
		}
		else
		{
			newWindow = window.open("../jointTicket/rpt_joint_ticket.jsp?gas_date="+gas_date+"&customer_cd="+customerCD
					+"&customer_nm="+customer+"&customer_abbr="+customerAbbr
					+"&customer_contact_cd="+customer_contact_cd
					+"&contract_type="+contractTyp+"&plant_cd="+plantCD+"&plant_nm="+plantName
					+"&qty_mmbtu="+qtyMMBTU
					+"&seller_nom_qty="+sallerNomQty+"&print_permission="+print_permission+"&convt_mmbtu_to_mt="+convt_mmbtu_to_mt,
					"JT_Report","top=10,left=10,width=950,height=750,scrollbars=1,menubar=1");
		}
	}
	
}

function genratePDF(obj,customer,plantName,contractTyp,qtyMMBTU,sallerNomQty,sz,indx,customerAbbr,customerCD,plantCD){
	var customer_contact_cd = "0";
	var gas_date = document.forms[0].gas_date.value;
	var convt_mmbtu_to_mt = document.forms[0].convt_mmbtu_to_mt.value;
	var print_permission = document.forms[0].print_permission.value;
	if(obj.id == "topdf"){
		var size = parseInt(sz);
		if(size == 1){
			customer_contact_cd =  document.forms[0].customer_cd.value;
		}
		else if(size>1){
			customer_contact_cd =  document.forms[0].customer_cd[indx].value;
		}
		if(!newWindow || newWindow.closed)
		{
			newWindow = window.open("../jointTicket/pdf_joint_ticket.jsp?gas_date="+gas_date+"&customer_cd="+customerCD
					+"&customer_nm="+customer+"&customer_abbr="+customerAbbr
					+"&customer_contact_cd="+customer_contact_cd
					+"&contract_type="+contractTyp+"&plant_cd="+plantCD+"&plant_nm="+plantName
					+"&qty_mmbtu="+qtyMMBTU
					+"&seller_nom_qty="+sallerNomQty+"&print_permission="+print_permission+"&convt_mmbtu_to_mt="+convt_mmbtu_to_mt,
					"JT_Report","top=10,left=10,width=950,height=750,scrollbars=1,menubar=1");
		}
		else
		{
			newWindow = window.open("../jointTicket/pdf_joint_ticket.jsp?gas_date="+gas_date+"&customer_cd="+customerCD
					+"&customer_nm="+customer+"&customer_abbr="+customerAbbr
					+"&customer_contact_cd="+customer_contact_cd
					+"&contract_type="+contractTyp+"&plant_cd="+plantCD+"&plant_nm="+plantName
					+"&qty_mmbtu="+qtyMMBTU
					+"&seller_nom_qty="+sallerNomQty+"&print_permission="+print_permission+"&convt_mmbtu_to_mt="+convt_mmbtu_to_mt,
					"JT_Report","top=10,left=10,width=950,height=750,scrollbars=1,menubar=1");
		}
		
	}
}

function exportExcel(obj,customer,plantName,contractTyp,qtyMMBTU,sallerNomQty,sz,indx,customerAbbr,customerCD,plantCD){
	var customer_contact_cd = "0";
	var gas_date = document.forms[0].gas_date.value;
	var convt_mmbtu_to_mt = document.forms[0].convt_mmbtu_to_mt.value;
	var print_permission = document.forms[0].print_permission.value;
	if(obj.id == "toexcel"){
		var size = parseInt(sz);
		if(size == 1){
			customer_contact_cd =  document.forms[0].customer_cd.value;
		}
		else if(size>1){
			customer_contact_cd =  document.forms[0].customer_cd[indx].value;
		}
		
		if(!newWindow || newWindow.closed)
		{
			newWindow = window.open("../jointTicket/xls_joint_ticket.jsp?gas_date="+gas_date+"&customer_cd="+customerCD
					+"&customer_nm="+customer+"&customer_abbr="+customerAbbr
					+"&customer_contact_cd="+customer_contact_cd
					+"&contract_type="+contractTyp+"&plant_cd="+plantCD+"&plant_nm="+plantName
					+"&qty_mmbtu="+qtyMMBTU
					+"&seller_nom_qty="+sallerNomQty+"&print_permission="+print_permission+"&convt_mmbtu_to_mt="+convt_mmbtu_to_mt,
					"JT_Report","top=10,left=10,width=950,height=750,scrollbars=1,menubar=1");
		}
		else
		{
			newWindow = window.open("../jointTicket/xls_joint_ticket.jsp?gas_date="+gas_date+"&customer_cd="+customerCD
					+"&customer_nm="+customer+"&customer_abbr="+customerAbbr
					+"&customer_contact_cd="+customer_contact_cd
					+"&contract_type="+contractTyp+"&plant_cd="+plantCD+"&plant_nm="+plantName
					+"&qty_mmbtu="+qtyMMBTU
					+"&seller_nom_qty="+sallerNomQty+"&print_permission="+print_permission+"&convt_mmbtu_to_mt="+convt_mmbtu_to_mt,
					"JT_Report","top=10,left=10,width=950,height=750,scrollbars=1,menubar=1");
		}
	}
}
</script>
</head>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.contract_mgmt.DataBean_Contract_Mgmt" id="dbContMgmt" scope="page"/>
<%
SimpleDateFormat formatter = new SimpleDateFormat("HH_mm_ss");
Date time = new Date();
SimpleDateFormat formatters = new SimpleDateFormat("dd_MMM_yy");
Date date = new Date();

utilBean.init();
String current_date = utilBean.getGen_dt();
String tomorrow_date = utilBean.getDate_tomorrow();

String genDate = request.getParameter("gen_date")==null?current_date:request.getParameter("gen_date");
String gasDate = request.getParameter("gas_date")==null?tomorrow_date:request.getParameter("gas_date");

/* below variable getting from Frm_checkLogin */
String today=request.getParameter("today")==null?"":request.getParameter("today");
String todaytime=request.getParameter("todaytime")==null?"":request.getParameter("todaytime");
String emp_nm=request.getParameter("emp_nm")==null?"":request.getParameter("emp_nm");
String user_cd=request.getParameter("user_cd")==null?"":request.getParameter("user_cd");
/* end */

String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();

String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");
String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");

dbContMgmt.setCallFlag("DAILY_JOINT_TICKET_LIST");
dbContMgmt.setGas_date(gasDate);
dbContMgmt.init();

if(!dbContMgmt.getGen_date().trim().equalsIgnoreCase(""))
{
	genDate = dbContMgmt.getGen_date().trim();
}

Vector customer = dbContMgmt.getDaily_JT_Customer_Abbr();
Vector plantName = dbContMgmt.getDaily_JT_Plant_Nm();
Vector contractTyp = dbContMgmt.getDaily_JT_Contract_Type();
Vector qtyMMBTU = dbContMgmt.getDaily_JT_Qty_Mmbtu();
//Vector qtySCM = dbContMgmt.getDaily_JT_Qty_Scm();
Vector sallerNomQty = dbContMgmt.getDaily_JT_Seller_Nom_Qty_Mmbtu();
Vector contactcd = dbContMgmt.getCustomer_Contact_Cd();
Vector contactPerson = dbContMgmt.getCustomer_Contact_Nm();
Vector contactPersonDesig = dbContMgmt.getCustomer_Contact_Desg();
Vector customerAbbr = dbContMgmt.getDaily_JT_Customer_Abbr();
Vector customerCD = dbContMgmt.getDaily_JT_Customer_Cd();
Vector plantCD = dbContMgmt.getDaily_JT_Plant_Seq_No();
String contactNm = "";
String contactDesg = "";
String href= "";
// String convt_mmbtu_to_mt = "51.9" ; //HA20200224 1 MT = 51.9 MMBTU (Assume)
double convt_mmbtu_to_mt= (double) session.getAttribute("convt_mmbtu_to_mt");//HA20201229
NumberFormat nf = new DecimalFormat("###########0.00"); 
%>
<body>
<form>
	<input type="hidden" name="modCd" value="<%=modCd%>">
	<input type="hidden" name="formId" value="<%=formId%>">
	<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
	<input type="hidden" name="write_permission" value="<%=write_permission%>">
   	<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
   	<input type="hidden" name="print_permission" value="<%=print_permission%>">
   	<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
   	<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
	<input type="hidden" name="convt_mmbtu_to_mt" value="<%=convt_mmbtu_to_mt%>">
	
	<div class="tab-content">
		<div class="tab-pane active" id="joint_ticket">
			<div class="box mb-0" >
				<table width="100%" align="center" cellpadding="1" cellspacing="1">
					<tr>
				    	<td colspan="10">
							<div align="center">
								<table width="100%" align="center" style="background-color: #d2d6de">
				  					<tr valign="top">
				  						<td width="100%" align="center">
											<font size="4" face="Verdana, Arial, Helvetica, sans-serif">
												<b>DAILY JOINT TICKET LIST</b>
											</font>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<div class="box-header with-border">
					<div class="form-group main-header">
						<table width="100%" align="center">	
							<tr>
								<td><label>Gas Day</label></td>
								<td class='input-group date' style="width:110pt;">
									<input type='text' class="form-control" id='datetimepicker1' name="gas_date"  maxlength="10" value="<%=gasDate%>"  onBlur="validateDate(this);"/>
									<span class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</span> 	
								</td>								
								<td><label>Generation Day</label></td>
								<td class='input-group date' style="width:110pt;">
									<input type='text' class="form-control" id='datetimepicker2' name="gen_date"  maxlength="10" value="<%=genDate%>"  onBlur="validateDate(this);"/>
									<span class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</span> 	
								</td>
								<td><button type="button" class="btn btn-primary" onclick="refreshPage()">View</button>  </td>
							</tr>
						</table>
					</div>	
					<div class="box-body table-responsive no-padding">
						<table class="table  table-bordered">
							<thead>   
								<tr class="info">
									<th class="text-center"> Customer </th>
									<th class="text-center"> Plant Name </th>
									<th class="text-center"> Contract<br>Type </th>
									<th class="text-center"> QTY<br>(MMBTU) </th>
									<!-- <th class="text-center"> QTY<br>(SCM) </th> -->
									<th class="text-center"> QTY<br>(MT) </th>
									<th class="text-center"> Seller Nom QTY<br>(MMBTU) </th>
									<th class="text-center"> Contact </th>
									<th class="text-center"> Activity-I </th>
									<th class="text-center"> Activity-II </th>
									<th class="text-center"> Activity-III </th> 
								</tr>
							</thead>
							<tbody>
								<%for(int i=0;i<customer.size();i++){%>
								<tr>
									<td><label><%=customer.elementAt(i)%></label></td>
									<td><label><%=plantName.elementAt(i)%></label></td>
									<td class="text-center"><label><%=contractTyp.elementAt(i)%></label></td>
									<td class="text-right"><label><%=qtyMMBTU.elementAt(i)%></label></td>
									<%-- <td class="text-right"><label><%=qtySCM.elementAt(i)%></label></td> --%>
									<td class="text-right"><label> <%=nf.format(Double.parseDouble(qtyMMBTU.elementAt(i).toString()) / convt_mmbtu_to_mt)%> </label>
										<input type="hidden" name="qty_MT" value="<%=nf.format(Double.parseDouble(qtyMMBTU.elementAt(i).toString()) / convt_mmbtu_to_mt)%>">
									</td>
									<td class="text-right"><label><%=sallerNomQty.elementAt(i)%></label></td>
									<td>
										<select name="customer_cd">
										<%for(int j=0;j<((Vector)contactPerson.elementAt(i)).size();j++){%>
											<% contactNm = ""+((Vector)contactPerson.elementAt(i)).elementAt(j);%>
											<% contactDesg = ""+((Vector)contactPersonDesig.elementAt(i)).elementAt(j); %>
											<option value="<%=((Vector)contactcd.elementAt(i)).elementAt(j)%>"><%=contactNm%>&nbsp;&nbsp;(&nbsp;<%=contactDesg%> )</option>
										
										<% href= "../jointTicket/xls_joint_ticket.jsp?requesttype=export&gas_date="+gasDate+"&customer_cd="+customerCD.elementAt(i)
													+"&customer_nm="+customer.elementAt(i)+"&customer_abbr="+customerAbbr.elementAt(i)
													+"&customer_contact_cd="+((Vector)contactcd.elementAt(i)).elementAt(j)
													+"&contract_type="+contractTyp.elementAt(i)+"&plant_cd="+plantCD.elementAt(i)+"&plant_nm="+plantName.elementAt(i)
													+"&qty_mmbtu="+qtyMMBTU.elementAt(i)+"&seller_nom_qty="+sallerNomQty.elementAt(i)
													+"&print_permission="+print_permission+"&convt_mmbtu_to_mt="+convt_mmbtu_to_mt+"&ext="+formatters.format(date)+"_"+formatter.format(time);  %>	
											
										<%}%>
										</select>
									</td>
									<td><button type="button" class="btn btn-primary" id="jointickt" onclick="openJoinTicket(this,'<%=customer.elementAt(i)%>','<%=plantName.elementAt(i)%>','<%=contractTyp.elementAt(i)%>','<%=qtyMMBTU.elementAt(i)%>','<%=sallerNomQty.elementAt(i)%>','<%=customer.size()%>','<%=i%>','<%=customerAbbr.elementAt(i)%>','<%=customerCD.elementAt(i)%>','<%=plantCD.elementAt(i)%>')">View Join Ticket</button></td>
									<td><button type="button" class="btn btn-primary" id="topdf" onclick="genratePDF(this,'<%=customer.elementAt(i)%>','<%=plantName.elementAt(i)%>','<%=contractTyp.elementAt(i)%>','<%=qtyMMBTU.elementAt(i)%>','<%=sallerNomQty.elementAt(i)%>','<%=customer.size()%>','<%=i%>','<%=customerAbbr.elementAt(i)%>','<%=customerCD.elementAt(i)%>','<%=plantCD.elementAt(i)%>')">To PDF</button></td>
									<td><a href="<%=href%>" class="btn btn-primary" id="toexcel">Export To Excel</a></td>
								</tr>
								<%}%>
								<%if(customer.size() == 0) {%>
									<tr >
										<th class="text-center" colspan="11" style="color: red;"> No data available for the selected date..!  </th>
									</tr>	
								<%} %>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>

<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script >
$(function () {
$('#datetimepicker1').datepicker({
changeMonth: true,
changeYear: true,
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
orientation: "bottom auto",
todayHighlight: true
});
});

$(function () {
$('#datetimepicker2').datepicker({
changeMonth: true,
changeYear: true,
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
orientation: "bottom auto",
todayHighlight: true
});
});
</script>
</body>