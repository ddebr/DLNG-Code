<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date.*"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.*"%>

<%@ page import="java.time.*"%> 
<%@ page import="java.time.DayOfWeek"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.temporal.TemporalAdjusters"%>

<%@ page import="com.seipl.hazira.dlng.contract_mgmt.DataBean_DailyBuyer_NominationV2" %>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../sess/Expire.jsp"%>
<title>TLU</title>

<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/tlu.css">

<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
<script type="text/javascript" src="../js/actionJS.js"></script>
<script type="text/javascript" src="../js/sweetalert.min.js"></script>
<style type="text/css">
.modal-body{
    height: 250px;
    overflow-y: auto;
}
</style>
<script type="text/javascript">

function calculateSCM()
{
	
}

function refreshPage(){
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	var gas_date = document.forms[0].gas_date.value;
	var gen_date = document.forms[0].gen_date.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	location.replace("../reports/rpt_master.jsp?gas_date="+gas_date+"&gen_date="+gen_date+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl);
}

function check(indx,custid,DelBase,MappId,selCustAbr,contract_no,gas_date,plant,Flsa,flsa_dt,contSignDt,contract_type){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	var delv_base = DelBase;
	var from_date = gas_date;
	var to_date = document.forms[0].to_date.value;
	var cd = contract_no;
	var mapp_id =MappId;
	
	var gas_date = document.forms[0].gas_date.value;
	var gen_date = document.forms[0].gen_date.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var nomDt=document.forms[0].gas_date.value;
	var NomRevNo='';
	var SchRevNo='';
	var nomDay_wid_rev='';
	var schDt = '';
	var schId = '';
	schDt = gas_date;
	
	if(document.forms[0].chk.length == undefined || document.forms[0].chk.length == 'undefined'){
		var buyer_sn_no = document.forms[0].buyer_sn_no.value;
		var buyer_plant_seq_no = document.forms[0].buyer_plant_seq_no.value;
		var buyer_mapping_id =  document.forms[0].buyer_mapping_id.value;
		nomDay_wid_rev = document.forms[0].genDayWith_rev_no.value;
		SchRevNo =document.forms[0].dateWithRevNo.value;
		schId =buyer_mapping_id+"-"+buyer_sn_no+"-"+buyer_plant_seq_no;
	}	
	else if(document.forms[0].chk.length > 1){
		var buyer_sn_no = document.forms[0].buyer_sn_no[indx].value;
		var buyer_plant_seq_no = document.forms[0].buyer_plant_seq_no[indx].value;
		var buyer_mapping_id =  document.forms[0].buyer_mapping_id[indx].value;
		schId =buyer_mapping_id+"-"+buyer_sn_no+"-"+buyer_plant_seq_no;
		nomDay_wid_rev = document.forms[0].genDayWith_rev_no[indx].value;
		SchRevNo =document.forms[0].dateWithRevNo[indx].value;
	}
	if(nomDay_wid_rev!=''){// for nomination revision number
// 		NomRevNo = nomDay_wid_rev.substring(1,nomDay_wid_rev.length-1);
		NomRevNo = nomDay_wid_rev;
	}
// 	alert(SchRevNo);
	if(SchRevNo!=''){// for schedual revision number
		var rmvPara = SchRevNo.substring(1,SchRevNo.length-1);
		 temp = rmvPara.split("-");	 
		 SchRevNo = temp[1];
	}
//	alert("../reports/rpt_master.jsp?gas_date="+gas_date+"&gen_date="+gen_date+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&custid="+custid+"&buyer_mapping_id="+buyer_mapping_id+"&schId="+schId+"&nomDt="+nomDt+"&rd="+NomRevNo+"&indx="+indx+"&SchRevNo="+SchRevNo+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&from_date="+from_date+"&to_date="+to_date+"&delv_base="+delv_base+"&flsa_no="+Flsa+"&mapp_id="+mapp_id+"&plant_no="+plant+"&contract_no="+cd+"&sign_dt="+contSignDt+"&contract_type="+contract_type);
	location.replace("../reports/rpt_master.jsp?gas_date="+gas_date+"&gen_date="+gen_date+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&custid="+custid+"&buyer_mapping_id="+buyer_mapping_id+"&schId="+schId+"&nomDt="+nomDt+"&rd="+NomRevNo+"&indx="+indx+"&SchRevNo="+SchRevNo+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&from_date="+from_date+"&to_date="+to_date+"&delv_base="+delv_base+"&flsa_no="+Flsa+"&mapp_id="+mapp_id+"&plant_no="+plant+"&contract_no="+cd+"&sign_dt="+contSignDt+"&contract_type="+contract_type); 
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

$(document).ready(function()
{
	setInterval(function(){
		var seconds = new Date().getSeconds();
		$("#sec").html(( seconds < 10 ? "0" : "" ) + seconds);
	},1000);
	
	setInterval( function() {
		var minutes = new Date().getMinutes();
		var x= "AM"
		if(date.getHours() >12){
		    x= "PM"
		}
		$("#min").html(( minutes < 10 ? "0" : "" ) + minutes+"   "+x);
		$("#min2").html(( minutes < 10 ? "0" : "" ) + minutes+"   "+x);
    },1000);
	
	/* setInterval( function() {
		var hours = new Date().getHours();	
		$("#hours").html(( hours < 10 ? "0" : "" ) + hours);
    }, 1000); */	
	
	var date = new Date()
	$("#hours").html(date.getHours()%12);
	$("#hours2").html(date.getHours()%12);
});


function doSubmit(delv_base,Contact_Person_Name,Contact_Customer_Person_City,Contact_Customer_Person_Pin,Contact_Customer_Person_State,dateTimeSent,Contact_Suppl_Name,Contact_Suppl_Person_City,Contact_Suppl_Person_Pin,Contact_Suppl_Person_State,from_date,to_date,flsa_no,sign_dt,contract_no,mapp_id,plant_no,indx){
	//"&custid="+custid+"&buyer_mapping_id="+buyer_mapping_id+"&schId="+schId+"&nomDt="+nomDt+"&rd="+NomRevNo+"&indx="+indx+"&SchRevNo="+SchRevNo+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&from_date="+from_date+"&to_date="+to_date+"&delv_base="+delv_base+"&flsa_no="+Flsa+"&mapp_id="+mapp_id+"&plant_no="+plant+"&contract_no="+cd+"&sign_dt="+contSignDt+"&contract_type="+contract_type
	/* console.log("delv_base : "+delv_base)
	console.log("Contact_Person_Name : "+Contact_Person_Name)
	console.log("Contact_Customer_Person_City : "+Contact_Customer_Person_City)
	console.log("Contact_Customer_Person_Pin : "+Contact_Customer_Person_Pin)
	console.log("Contact_Customer_Person_State : "+Contact_Customer_Person_State)
	console.log("dateTimeSent : "+dateTimeSent)
	console.log("Contact_Suppl_Name : "+Contact_Suppl_Name)
	console.log("Contact_Suppl_Person_City : "+Contact_Suppl_Person_City)
	console.log("Contact_Suppl_Person_Pin : "+Contact_Suppl_Person_Pin)
	console.log("Contact_Suppl_Person_State : "+Contact_Suppl_Person_State)
	console.log("from_date : "+from_date)
	console.log("to_date : "+to_date)
	console.log("flsa_no : "+flsa_no)
	console.log("sign_dt : "+sign_dt)
	console.log("contract_no : "+contract_no) */
	
	var Contact_Customer_Person_Address = document.getElementById("Contact_Customer_Person_Address").value;
	var Contact_Suppl_Person_Address = document.getElementById("Contact_Suppl_Person_Address").value;
	
	/* console.log("Contact_Customer_Person_Address : "+Contact_Customer_Person_Address)
	console.log("Contact_Suppl_Person_Address : "+Contact_Suppl_Person_Address) */
	var url = "delv_base="+delv_base+"&Contact_Person_Name="+Contact_Person_Name+"&Contact_Customer_Person_Address="+Contact_Customer_Person_Address+"&Contact_Customer_Person_City="+Contact_Customer_Person_City+"&Contact_Customer_Person_Pin="+Contact_Customer_Person_Pin+"&Contact_Customer_Person_State="+Contact_Customer_Person_State+"&dateTimeSent="+dateTimeSent+"&Contact_Suppl_Name="+Contact_Suppl_Name+"&Contact_Suppl_Person_Address="+Contact_Suppl_Person_Address+"&Contact_Suppl_Person_City="+Contact_Suppl_Person_City+"&Contact_Suppl_Person_Pin="+Contact_Suppl_Person_Pin+"&Contact_Suppl_Person_State="+Contact_Suppl_Person_State+"&from_date="+from_date+"&to_date="+to_date+"&flsa_no="+flsa_no+"&sign_dt="+sign_dt+"&contract_no="+contract_no+"&mapp_id="+mapp_id+"&plant_no="+plant_no+"&indx="+indx;
	document.forms[0].action="../reports/mail_weekly_seller_report.jsp?"+url;
	document.forms[0].submit();
}
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.amc.DataBean_Mst_Query" id="imv" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.contract_mgmt.DataBean_MgmtV2" id="contMgmt" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Report_GenerationV2" id="contMgmtRpt"  scope="page"/>
<%
NumberFormat nf = new DecimalFormat("###########0.00");
NumberFormat nf2 = new java.text.DecimalFormat("###0.00");
NumberFormat nf1 = new java.text.DecimalFormat("###0");

utilBean.init();
String current_date = utilBean.getGen_dt();
String tomorrow_date = utilBean.getDate_tomorrow();

String time = utilBean.getTime_gen();
SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
Date date = format1.parse(current_date);

Date todayDate = new Date();

String temp_dt[] = current_date.split("/");
String temp_mon = temp_dt[1];
String temp_yr = temp_dt[2];
String start_dt = "01/"+temp_mon+"/"+temp_yr;

String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");
		 
/*---------------------Get Next Monday SUJIT10SEP2020----------------*/

DateTimeFormatter formats = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate localdt = LocalDate.parse(current_date, formats);
localdt = localdt.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
String nextMonday = localdt.format(formats);

System.out.println("Next Monday : "+nextMonday);

String truck_load_date = request.getParameter("gas_date")==null ? nextMonday : request.getParameter("gas_date"); //SB20200725
String gas_date = request.getParameter("gas_date") == null ? nextMonday : request.getParameter("gas_date");
String gen_date = request.getParameter("gen_date") == null ? nextMonday : request.getParameter("gen_date");
/*---------------------End Get Next Monday----------------*/

String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");

/*---------------------Generate Dates----------------*/

DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
Calendar cal = Calendar.getInstance();
cal.setTime(df.parse(gas_date));
cal.add(Calendar.DATE, 6);
/* System.out.println(gas_date+"....."+df.format(cal.getTime()));  */
Date startDate = null;
Date endDate = null;

try {
	   startDate = (Date)dateformat.parse(gas_date);
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

System.out.println("nomModalDates : "+nomModalDates);

String TbackColor = "";
/*---------------------End Generate Dates----------------*/
//String s  = ;
String from_date = request.getParameter("from_date")==null?start_dt:request.getParameter("from_date"); //Modified
String to_date = request.getParameter("to_date")==null?nomModalDates.lastElement().toString():request.getParameter("to_date"); //Modified
String delv_base = request.getParameter("delv_base")==null?"X":request.getParameter("delv_base"); //Modified
String flsa_no = request.getParameter("flsa_no")==null?"0":request.getParameter("flsa_no"); //Modified
String mapp_id = request.getParameter("mapp_id")==null?"":request.getParameter("mapp_id"); //Modified
String contract_no = request.getParameter("contract_no")==null?"":request.getParameter("contract_no"); //Modified
String plant_no = request.getParameter("plant_no")==null?"":request.getParameter("plant_no"); //Modified
String rev_no = request.getParameter("rev_no")==null?"":request.getParameter("rev_no"); //Modified
String sch_mapp_id = mapp_id+"-%-"+plant_no;//request.getParameter("sch_mapp_id")==null?"":request.getParameter("sch_mapp_id"); //Modified
String sign_dt = request.getParameter("sign_dt")==null?"":request.getParameter("sign_dt"); //Modified
String flsa_sign_dt = request.getParameter("flsa_sign_dt")==null?"":request.getParameter("flsa_sign_dt"); //Modified

contMgmt.setCallFlag("GENERATE_GEN_DATE_FOR_NOMINATION");
contMgmt.setGas_date(gas_date);
contMgmt.init();

if(!contMgmt.getGen_date().trim().equalsIgnoreCase(""))
{
	gen_date = contMgmt.getGen_date().trim();
}

double gcv = Double.parseDouble(request.getParameter("gcv")==null?"9802.80":request.getParameter("gcv"));
double ncv = Double.parseDouble(request.getParameter("ncv")==null?"8831.35":request.getParameter("ncv"));
double percentage_of_dcq = 10;

String selSchedulCust = request.getParameter("custid")==null?"":request.getParameter("custid");
String indx = request.getParameter("indx")==null?"100000":request.getParameter("indx");
String buyer_mapping_id = request.getParameter("buyer_mapping_id")==null?"":request.getParameter("buyer_mapping_id");
String schId = request.getParameter("schId")==null?"":request.getParameter("schId");
String nomDt = request.getParameter("nomDt")==null?"":request.getParameter("nomDt");
String revNo = request.getParameter("rd")==null?"":request.getParameter("rd");
String schRevNo = request.getParameter("SchRevNo")==null?"":request.getParameter("SchRevNo");//HA20200206

String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212
String contract_type = request.getParameter("contract_type") == null ? "S" : request.getParameter("contract_type");

contMgmt.setCallFlag("CUSTOMER_WEEKLY_SCH");
contMgmt.setGas_date(gas_date);
contMgmt.setSelSchedulCust(selSchedulCust);
contMgmt.setBuyer_mapping_id(buyer_mapping_id);
contMgmt.setSelContType(contract_type);
contMgmt.setSchId(schId);
contMgmt.setNomDt(nomDt);
contMgmt.setRevNo(revNo);
contMgmt.setSchRevNo(schRevNo);
contMgmt.setNomModalDates(nomModalDates);
contMgmt.init();

Vector daily_Seller_Nom_Fgsa_No = contMgmt.getDaily_Seller_Nom_Fgsa_No();
Vector daily_Seller_Nom_Fgsa_Rev_No = contMgmt.getDaily_Seller_Nom_Fgsa_Rev_No();
Vector daily_Seller_Nom_Sn_No = contMgmt.getDaily_Seller_Nom_Sn_No();
Vector daily_Seller_Nom_Sn_Rev_No = contMgmt.getDaily_Seller_Nom_Sn_Rev_No();
Vector daily_Seller_Nom_Cd = contMgmt.getDaily_Seller_Nom_Cd();
Vector daily_Seller_Nom_Abbr = contMgmt.getDaily_Seller_Nom_Abbr();
Vector daily_Seller_Nom_Dcq = contMgmt.getDaily_Seller_Nom_Dcq();
Vector daily_Seller_Nom_Plant_Cd = contMgmt.getDaily_Seller_Nom_Plant_Cd();
Vector daily_Seller_Nom_Plant_Abbr = contMgmt.getDaily_Seller_Nom_Plant_Abbr();
String daily_Total_Dcq_Seller_Nom = contMgmt.getDaily_Total_Dcq_Seller_Nom();
Vector daily_Seller_boe_no= contMgmt.getDaily_Seller_regas_cargo_boe_no();
Vector daily_Seller_boe_dt= contMgmt.getDaily_Seller_regas_cargo_boe_dt();
Vector daily_Seller_Gen_Day_With_Rev_No = contMgmt.getDaily_Seller_Gen_Day_With_Rev_No();
Vector daily_Seller_Gen_Day_Time = contMgmt.getDaily_Seller_Gen_Day_Time();
Vector daily_Seller_Nom_Plant_Seq_No = contMgmt.getDaily_Seller_Nom_Plant_Seq_No();
Vector daily_Seller_Nom_Qty_Mmbtu = contMgmt.getDaily_Seller_Nom_Qty_Mmbtu();
Vector daily_Seller_Nom_Qty_Scm = contMgmt.getDaily_Seller_Nom_Qty_Scm();
String daily_Total_Mmbtu_Seller_Nom = contMgmt.getDaily_Total_Mmbtu_Seller_Nom();
String daily_Total_Scm_Seller_Nom = contMgmt.getDaily_Total_Scm_Seller_Nom();
Vector daily_Seller_Nom_Type = contMgmt.getDaily_Seller_Nom_Type();
Vector daily_Seller_Nom_Contract_Type = contMgmt.getDaily_Seller_Nom_Contract_Type();
Vector daily_Buyer_Gen_Day_With_Rev_No = contMgmt.getDaily_Buyer_Gen_Day_With_Rev_No();
Vector daily_Buyer_Gen_Day_Time = contMgmt.getDaily_Buyer_Gen_Day_Time();
Vector daily_Buyer_Nom_Plant_Seq_No = contMgmt.getDaily_Buyer_Nom_Plant_Seq_No();
Vector daily_Buyer_Nom_Plant_Seq_Abbr = contMgmt.getDaily_Buyer_Nom_Plant_Seq_Abbr();
Vector daily_Buyer_Nom_Qty_Mmbtu = contMgmt.getDaily_Buyer_Nom_Qty_Mmbtu();
Vector daily_Buyer_Nom_Qty_Scm = contMgmt.getDaily_Buyer_Nom_Qty_Scm();
String daily_Total_Mmbtu = contMgmt.getDaily_Total_Mmbtu();
Vector daily_Seller_Nom_Dcq_Mt = contMgmt.getDaily_Seller_Nom_Dcq_Mt();//SUJIT05MARCH2020
Vector VBuyer_Inv_Flag = contMgmt.getVBuyer_Inv_Flag(); //SB20200718
Vector VBuyer_Fcc_Flag = contMgmt.getVBuyer_Fcc_Flag(); //SB20200718
Vector VBuyer_Delv_Base = contMgmt.getVBuyer_Delv_Base(); //SB20200718
Vector VContSignDt = contMgmt.getVContSignDt(); //SB20200718
Vector VContSequanceNo = contMgmt.getVContSequanceNo();

double fsru_tank_vol = contMgmt.getFsru_tank_vol();
double int_tank_vol = contMgmt.getInt_tank_vol();
String int_tankId = contMgmt.getInt_tankId();
double int_tank_capacity = contMgmt.getInt_tankCapacity();

int index = 0;
int record_length = daily_Buyer_Nom_Plant_Seq_No.size();
String plant_cd = "0";

if(record_length>1)
{
	plant_cd = (String)daily_Buyer_Nom_Plant_Seq_No.elementAt(0);
}

Vector daily_Seller_Nom_Mapping_Id = contMgmt.getDaily_Seller_Nom_Mapping_Id(); 

/*----------------SUJIT10SEP2020-------------*/
Vector VWeekly_Seller_Nom_ID = contMgmt.getVWeekly_Seller_Nom_ID(); 
Vector VWeekly_Sch_ID = contMgmt.getVWeekly_Sch_ID() ; 
Vector VWeekly_DCQ = contMgmt.getVWeekly_DCQ(); 
Vector VWeekly_Truck_ID = contMgmt.getVWeekly_Truck_ID(); 
Vector VWeekly_Truck_Nm = contMgmt.getVWeekly_Truck_Nm();  
Vector VWeekly_Truck_Cap = contMgmt.getVWeekly_Truck_Cap();
Vector VWeekly_Sch_VOL = contMgmt.getVWeekly_Sch_VOL(); 
Vector VWeekly_Sch_ENE = contMgmt.getVWeekly_Sch_ENE(); 
Vector VWeekly_Sch_MT = contMgmt.getVWeekly_Sch_MT();
Vector VWeekly_Truck_SchDt = contMgmt.getVWeekly_Truck_SchDt(); 
Vector VWeekly_Truck_SchTm = contMgmt.getVWeekly_Truck_SchTm(); 
Vector VWeekly_Truck_Remark = contMgmt.getVWeekly_Truck_Remark();
Vector VWeekly_Trans_cd = contMgmt.getVWeekly_Trans_cd(); 

Vector VWeekly_Date = contMgmt.getVWeekly_Date(); 
String weeklyDate = "";
int count = 0;
int recordCount = 0;

Vector VWeekly_Nom_VOL = contMgmt.getVWeekly_Nom_VOL();
Vector VWeekly_Nom_MT = contMgmt.getVWeekly_Nom_MT() ;

/*----------------END SUJIT10SEP2020-------------*/

double conversion_factor_from_m3_to_mmbtu = 23.9;
double converssion_factor_from_m3_tonmmbtu = 0.3531466672;
// double convt_mmbtu_to_mt = 51.9; //SUJIT05MARCH2020
double convt_mmbtu_to_mt= (double) session.getAttribute("convt_mmbtu_to_mt");//HA20201229
double totalTon = 0;//SUJIT15SEP2020
double totalVol = 0;//SUJIT15SEP2020

contMgmtRpt.setCallFlag("Seller_Weekly_Nom_Rpt");
contMgmtRpt.setFrom_date(from_date);
contMgmtRpt.setTo_date(to_date);
contMgmtRpt.setMappId(mapp_id);
contMgmtRpt.setSchMappId(sch_mapp_id);
contMgmtRpt.setNomModalDates(nomModalDates);
contMgmtRpt.init();

Vector rpt_sel_week_mmbtu = contMgmtRpt.getRpt_sel_week_mmbtu();
Vector rpt_sel_week_truck_nm = contMgmtRpt.getRpt_sel_week_truck_nm();
Vector rpt_sel_week_schedule_tm = contMgmtRpt.getRpt_sel_week_schedule_tm();
Vector rpt_sel_week_remarks = contMgmtRpt.getRpt_sel_week_remarks(); 
Vector rpt_sel_week_mt = contMgmtRpt.getRpt_sel_week_mt();
Vector rpt_sel_week_customer_nm = contMgmtRpt.getRpt_sel_week_customer_nm();
Vector rpt_sel_week_plant_nm = contMgmtRpt.getRpt_sel_week_plant_nm();
double totMMBTU = contMgmtRpt.getTotMMBTU();
double totMT = contMgmtRpt.getTotMT();
String Contact_Person_Name = contMgmtRpt.getContact_Person_Name();
String Contact_Customer_Person_Address = contMgmtRpt.getContact_Customer_Person_Address();
String Contact_Customer_Person_City = contMgmtRpt.getContact_Customer_Person_City();
String Contact_Customer_Person_Pin = contMgmtRpt.getContact_Customer_Person_Pin();
String Contact_Customer_Person_State = contMgmtRpt.getContact_Customer_Person_State();
String Contact_Customer_Name = contMgmtRpt.getContact_Customer_Name();

String Contact_Suppl_Person_Address = contMgmtRpt.getContact_Suppl_Person_Address();
String Contact_Suppl_Person_City = contMgmtRpt.getContact_Suppl_Person_City();
String Contact_Suppl_Person_Pin = contMgmtRpt.getContact_Suppl_Person_Pin();
String Contact_Suppl_Person_State = contMgmtRpt.getContact_Suppl_Person_State();
String Contact_Suppl_Name = contMgmtRpt.getContact_Suppl_Name();

System.out.println("Contact_Person_Name :"+Contact_Person_Name);
System.out.println("daily_Seller_Gen_Day_With_Rev_No :"+daily_Seller_Gen_Day_With_Rev_No);

%>
<div class="tab-content">

<!--Daily Buyer Nomination TAB START-->
<div class="tab-pane active" id="daily_buyer_nomination">
<!-- Default box -->
<div class="box mb-0">
<form  method="post" action="../servlet/Frm_MgmtV2" id="myform">

<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">

<input type="hidden" name="subType" value="">
<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
<input type="hidden" name="option" value="submitCustomerWeeklySch">
<input type="hidden" name="mmbtu_to_tons" value="0.025219021687207"> 
<input type="hidden" name="mmbtu_to_m3" value="23.9">
<input type="hidden" name="m3_to_tonMMbtu" value="0.3531466672">
<input type="hidden" name="convt_mmbtu_to_mt" value="<%=convt_mmbtu_to_mt%>">
<input type="hidden" name="actual_sch_qty_buyer_mmbtu" value="">
<input type="hidden" name="to_date" value="<%=to_date%>">

<div class="box-header with-border">

<%if(msg.length()>5){%>
		<div class="box-body table-responsive no-padding">
			<table class="table  table-bordered">
				<thead>   
					<tr class="info">
						<th class="text-center" colspan="2" style="color: blue;"> <%=msg%> </th>
					</tr>
				</thead>
			</table>
		</div>
	<%}%>

<div class="box-header with-border main-header" >
<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
	<div class="form-group mb-0 row">
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
			<label> Weekly start Date </label>
			<div class="form-group">
				<div class='input-group date' id='datetimepicker1'>
					<input type='text' class="form-control" id="d1" name="gas_date"  maxlength="10" value="<%=gas_date%>" onchange="refreshPage();"/>
					<span class="input-group-addon">
					<i class="fa fa-calendar"></i>
					</span>
				</div>
			</div>
		</div>
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
			<label> Generation Day </label>
			<div class="form-group mb-0">
				<div class='input-group date' id='datetimepicker2'>
					<input type='text' class="form-control" id="d2" name="gen_date"  maxlength="10" value="<%=truck_load_date%>" onBlur="validateDate(this);"/>
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
				<label> Delivery Base </label>
			</div>
		</div>
	
		<div class="form-group row">
			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
				<div class="form-group input-group mb-0">
					<span class="input-group-addon">
						<input type="radio" name="rd" onClick="calculateSCM();" checked="checked"> X-Terminal
					</span>
					
					<span class="input-group-addon"> () </span>
				</div>
			</div>	
		
			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
				<div class="form-group input-group mb-0">
					<span class="input-group-addon">
						<input type="radio" name="rd" onClick="calculateSCM();"> Delivery at Site
					</span>
				
					<span class="input-group-addon"> ()</span>
				</div>
			</div>
		</div>
	</div>
</div> <!-- row end -->
</div> 

<div class="box-body table-responsive no-padding">
<table class="table  table-bordered">	
  <thead>   
	<tr class="info">
		<th class="text-center" rowspan="3"  width="15%"> Customer <br> (Location of Delivery)  </th>
		<th class="text-center" rowspan="3"> SN/LOA <br> NO  </th>
		<th class="text-center" rowspan="3"> PLANT <br> NAME  </th>
		<th class="text-center" colspan="2"> DCQ QTY </th>
		<th class="text-center" colspan="2">WEEKLY QTY (MMBTU) </th>
		<th class="text-center" rowspan="3"></th>
	</tr>
	<tr class="info">
		<th class="text-center" rowspan="2" colspan ="1">MMBTU</th>
		<th class="text-center" rowspan="2" colspan ="1">MT</th>
		<th class="text-center" rowspan="1" colspan="1"> (MMBTU)  </th>
		<th class="text-center" rowspan="1" colspan="1"> (MT) </th>
	</tr>
</thead>

<%try{			
		String title="title = 'Please First Select Radio Buttton'";
		String btnStatus="disabled=disabled";
		for(int j=0;j<daily_Buyer_Nom_Plant_Seq_No.size();j++)
		{		
			if(!daily_Seller_Gen_Day_With_Rev_No.elementAt(j).toString().equals(""))/*--SUJIT12SEP2020--*/
			{  
				if(j==(Integer.parseInt(indx))){
					title = "";
					if(title.equals("") && VWeekly_Truck_ID.size() !=0){
						btnStatus = "";
					}else{
						btnStatus="disabled=disabled";
						title="title = 'No truck(s) available for the selected Customer!'";
					}
				}else{
					title = "title = 'Please First Select Radio Buttton'";
					btnStatus="disabled=disabled";
				}%>
				 <tr>
					<td> 
						<div class="mt-5"><%//=VBuyer_Fcc_Flag.elementAt(j) %><%//=VBuyer_Delv_Base.elementAt(j) %>
						<% if(VBuyer_Inv_Flag.elementAt(j).equals("N")) { %>
							<input type="radio" onclick="check('<%=j%>','<%=daily_Seller_Nom_Cd.elementAt(j)%>','<%=(String)VBuyer_Delv_Base.elementAt(j)%>','<%=(String)daily_Seller_Nom_Mapping_Id.elementAt(j)%>','<%=(String)daily_Seller_Nom_Abbr.elementAt(j)%>','<%=(String)daily_Seller_Nom_Contract_Type.elementAt(j)+"-"+(String)daily_Seller_Nom_Sn_No.elementAt(j)%>','<%=gas_date%>','<%=(String)daily_Buyer_Nom_Plant_Seq_No.elementAt(j)%>','<%=(String)daily_Seller_Nom_Fgsa_No.elementAt(j)%>','<%=(String)daily_Seller_Nom_Fgsa_No.elementAt(j)%>','<%=VContSignDt.elementAt(j)%>','<%=(String)daily_Seller_Nom_Type.elementAt(j)%>')" name="chk" <%if(j==(Integer.parseInt(indx))){%> checked="checked" <%}%>>
						<%} else if(VBuyer_Inv_Flag.elementAt(j).equals("Y")) { %>
							<input  disabled type="radio" onclick="check('<%=j%>','<%=daily_Seller_Nom_Cd.elementAt(j)%>','<%=(String)VBuyer_Delv_Base.elementAt(j)%>','<%=(String)daily_Seller_Nom_Mapping_Id.elementAt(j)%>','<%=(String)daily_Seller_Nom_Abbr.elementAt(j)%>','<%=(String)daily_Seller_Nom_Contract_Type.elementAt(j)+"-"+(String)daily_Seller_Nom_Sn_No.elementAt(j)%>','<%=gas_date%>','<%=(String)daily_Buyer_Nom_Plant_Seq_No.elementAt(j)%>','<%=(String)daily_Seller_Nom_Fgsa_No.elementAt(j)%>','<%=(String)daily_Seller_Nom_Fgsa_No.elementAt(j)%>','<%=VContSignDt.elementAt(j)%>','<%=(String)daily_Seller_Nom_Type.elementAt(j)%>')" name="chk" <%if(j==(Integer.parseInt(indx))){%> checked="checked" <%}%>> 
						<%} %>	
						 <%if(VBuyer_Delv_Base.elementAt(j).equals("X")){ %>
							<font color="GREEN" title="Invoice Generated" ><b><%=(String)daily_Seller_Nom_Abbr.elementAt(j)%>&nbsp; (X-Terminal)&nbsp;</b></font>
							<%} else {%>
							<font color="BLUE" title="Invoice Generated" ><b><%=(String)daily_Seller_Nom_Abbr.elementAt(j)%>&nbsp;&nbsp;( Delivery at Site )</b></font>
							<%} %> 
							<input type="hidden" name="dateWithRevNo" value="<%=(String)daily_Seller_Gen_Day_With_Rev_No.elementAt(j)%>">
							<input type="hidden" name="chk_flag" <%if(j==(Integer.parseInt(indx))){%> value="Y" <%}else{%> value="N" <%}%>>
							<input type="hidden" name="buyer_cd" value="<%=(String)daily_Seller_Nom_Cd.elementAt(j)%>">
							<input type="hidden" name="buyer_abbr" value="<%=(String)daily_Seller_Nom_Abbr.elementAt(j)%>">
							<input type="hidden" name="contract_type" value="<%=(String)daily_Seller_Nom_Type.elementAt(j)%>">
						</div>
					</td>
						
					<td>
						<div class="mt-5">
							<input type="hidden" name="buyer_mapping_id" value="<%=(String)daily_Seller_Nom_Mapping_Id.elementAt(j)%>">
							<input type="hidden" name="buyer_fgsa_no" value="<%=(String)daily_Seller_Nom_Fgsa_No.elementAt(j)%>">
							<input type="hidden" name="buyer_fgsa_rev_no" value="<%=(String)daily_Seller_Nom_Fgsa_Rev_No.elementAt(j)%>">
							<input type="hidden" name="buyer_sn_no" value="<%=(String)daily_Seller_Nom_Sn_No.elementAt(j)%>">
							<input type="hidden" name="buyer_sn_rev_no" value="<%=(String)daily_Seller_Nom_Sn_Rev_No.elementAt(j)%>">
							<input type="hidden" name="boe_no" value="<%=daily_Seller_boe_no.elementAt(j)%>">
							<input type="hidden" name="boe_dt" value="<%=daily_Seller_boe_dt.elementAt(j)%>">
							<%=(String)daily_Seller_Nom_Contract_Type.elementAt(j)%>-<%=(String)daily_Seller_Nom_Sn_No.elementAt(j)%>
						</div>
					</td>
					
					<td>
						<div class="mt-5"><%=(String)daily_Buyer_Nom_Plant_Seq_Abbr.elementAt(j)%>
							<input type="hidden" name="buyer_plant_seq_no" value="<%=(String)daily_Buyer_Nom_Plant_Seq_No.elementAt(j)%>">
						</div>
					</td>
					
					<td class="text-right">
						<div class="mt-5">	<%=(String)daily_Seller_Nom_Dcq.elementAt(j)%>
							<input type="hidden" name="qty_dcq" value="<%=(String)daily_Seller_Nom_Dcq.elementAt(j)%>" >
						</div>
					</td>
					<td class="text-right">
						<div class="mt-5" >	<%=(String)daily_Seller_Nom_Dcq_Mt.elementAt(j)%>
							<input type="hidden" name="qty_dcq_mt" value="<%=(String)daily_Seller_Nom_Dcq_Mt.elementAt(j)%>" >
						</div>
					</td>
					
							<input type="hidden" name="qty_buyer_mmbtu" id="qty_buyer_mmbtu<%=j%>" value="<%=(String)daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)%>" >
							<input type="hidden" name="genDayWith_rev_no" value="<%=(String)daily_Buyer_Gen_Day_With_Rev_No.elementAt(j)%>">
			
					<td>
					<div class="form-groupmb-0 mobile-v"><input type="text" class="form-control w-65" name="qty_mmbtu" id="qty_mmbtu<%=j%>" value="<%=(String)daily_Seller_Nom_Qty_Mmbtu.elementAt(j)%>"  maxlength="10" style="text-align:right" readonly="readonly"></div>
					</td>
					
					<td><%   totalVol = totalVol + Double.parseDouble(daily_Seller_Nom_Qty_Mmbtu.elementAt(j).toString());
							 double schdulTon = Double.parseDouble(daily_Seller_Nom_Qty_Mmbtu.elementAt(j).toString()) / convt_mmbtu_to_mt;
							 totalTon = totalTon + schdulTon;%>
							 <div class="form-groupmb-0 mobile-v"><input type="text" class="form-control w-65" id="qty_ton<%=j%>" name="qty_ton" value="<%=nf.format(schdulTon)%>" style="text-align:right" readonly="readonly" ></div>
					</td>
					<td><button type="button" class="btn btn-info"  <%=title%>  <%=btnStatus%> data-target="#myModal" data-toggle="modal" >View Report</button></td>
				 </tr>			
			   <%++index;
			 }
		} %>
	
	 <%if(daily_Buyer_Nom_Plant_Seq_Abbr.size()>0)
	   {%>
		   <tr class="success">
		   	  	<th class="text-center" colspan="3"><div class="mt-5"> TOTAL SCHEDULED QTY</div> </th>
	
				<th>
					<div class="form-groupmb-0 mobile-v">
						<%--<input type="text" class="form-control w-100" name="total_dcq_qty" value="<%=daily_Total_Dcq_Seller_Nom%>" readonly="readonly" style="text-align: right;"> --%>
					</div>
				</th>
		
				<th> 
					<div class="form-groupmb-0 mobile-v">
						<%--<input type="text" class="form-control w-100" name="total_buyer_mmbtu_qty" value="<%=daily_Total_Mmbtu%>"  readonly="readonly" style="text-align: right;"> --%>
					</div>
				</th>
				
				<th> 
					<div class="form-groupmb-0 mobile-v">
						<input type="text" class="form-control w-100" name="total_buyer_mmbtu_qty" value="<%=nf.format(totalVol)%>"  readonly="readonly" style="text-align: right;">
					</div>
				</th>
				
				<th> 
					<div class="form-groupmb-0 mobile-v">
						<input type="text" class="form-control w-100" name="total_mmbtu_qty" value="<%=nf.format(totalTon)%>" readonly="readonly" style="text-align: right;">
					</div>
				</th>
				<th>
					<input type="hidden" name="total_scm_qty" value="<%=daily_Total_Scm_Seller_Nom%>">
					<input type="hidden" name="index_count" value="<%=index%>">
					<input type="hidden" name="percentage_of_dcq" value="<%=percentage_of_dcq%>">
				</th>
		   </tr>	
<%}  else { %>
	<tr>
		<th class="text-center" colspan="8" style="color: red;">
			<div class="mt-5"> Nomination Data Not Available!!</div> 
		</th>
	</tr>	
<% } %>		
<%}catch(Exception e)
	{	
		e.printStackTrace();
	}%>

</table>
</div>
<%	try
	{ %>
	<%if (!selSchedulCust.equals("")) 
  	  {%> 
		<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg modal-dialog-scrollable" role="document" style="width: 90%">
				<div class="modal-content">
					<div class="modal-header">
						<h2 class="modal-title" id="myModalLabel">
							<%if(delv_base.equals("D")) {%>
								Delivered Sales
							<%} else {%> 
								Ex-Terminal Sales
							<%}%>
						</h2>
					</div>
					<div class="modal-body" style="height:600px;">
						<div class="box-body">
							<div class="row">
								<div class="col-md-6">
									<b>To,</b><br>
									&nbsp;&nbsp;<%=Contact_Person_Name%>,
									&nbsp;&nbsp;<%=Contact_Customer_Name%>,<br>
									&nbsp;&nbsp;<%=Contact_Customer_Person_Address%>,<br>
									&nbsp;&nbsp;<%=Contact_Customer_Person_City%>-<%=Contact_Customer_Person_Pin%>,<br>
									&nbsp;&nbsp;<%=Contact_Customer_Person_State%>
								</div>
								<div class="col-md-6 text-right">
									<b>Date Time Sent : </b> <b><%=format1.format(todayDate)%></b> <b id="hours"></b>: <b id="min"></b><!-- : <b id="sec"></b> --><!-- 24/08/2020 12:45 P.M  -->
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-md-6">
									<b>From,</b><br>
									&nbsp;&nbsp;<%=Contact_Suppl_Name%>,<br>
									&nbsp;&nbsp;<%=Contact_Suppl_Person_Address%>,<br>
									&nbsp;&nbsp;<%=Contact_Suppl_Person_City%>-<%=Contact_Suppl_Person_Pin%>,<br>
									&nbsp;&nbsp;<%=Contact_Suppl_Person_State%>
								</div>
								<div class="col-md-6 text-right">
									<b>Sequence No#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
									<br><br>
									<b>From Date :</b><%=from_date%><br>
									<b>To Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b><%=to_date%>
								</div>
							</div>
							<hr/>
							<div class="row">
								<div class="col-md-12 text-center"><h4>Seller Weekly Confirmation</h4></div>
							</div>
							<div class="row">
								<div class="col-md-12 text-left"><b>Dear Madam / Sir,</b></div>
							</div>
							<div class="row">
								<div class="col-md-12">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									As per the requirements of our Framework LNG Sales Agreement FLSA-<%=flsa_no %> dated <%=sign_dt %> Supply Notice <%=contract_no %> dated <%=sign_dt %> we notify as follows:
								</div>
							</div>
						</div>
						<div class="box-body table-responsive no-padding">
							<table class="table table-head-fixed text-nowrap table-bordered">
								<thead>
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
								<tbody style="height:300px;">	
									<%if(VWeekly_Seller_Nom_ID.size() > 0){%>
										<%for (int i = 0 ; i < rpt_sel_week_mmbtu.size(); i++) 
										  {%>
										  	  <%if(!weeklyDate.equals(VWeekly_Date.elementAt(i).toString().trim()))	
					    					    {%>
					   						  		<%count++;%>
					   						  		<%if(count==1){
					   						  			TbackColor = "#FFE5E5";//"#E6E4E4";
					   						  		  }else if(count==2){
					   						  			TbackColor = "#F7E4CC";//"#F2EEEE";
					   						  		  }else if(count==3){
					   						  			TbackColor = "#D2E9D2";//"#DAD7D7";
					   						  		  }else if(count==4){
					   						  			TbackColor = "#E7FFBE";
					   						  		  }else if(count==5){
					   						  			TbackColor = "#BFEAA3";
					   						  		  }else if(count==6){
					   						  			TbackColor = "#D0CFCF";
					   						  		  }else if(count==7){
					   						  			TbackColor = "#DAD1B8";
					   						  		  }%>
					    					   <%}%>
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
													<%if(!weeklyDate.equals(VWeekly_Date.elementAt(i).toString().trim())){
													   	 weeklyDate = VWeekly_Date.elementAt(i).toString().trim(); 
											   		 }else{}%>
												</tr>
										<%}%>
										<tr>
											<td colspan="3" class="text-center"><b>TOTAL</b></td>
											<td class="text-right"><b><%=nf1.format(totMMBTU)%>&nbsp; MMBTU</b></td>
											<td class="text-right"><b><%=nf.format(totMT)%>&nbsp; MT</b></td>
											<td colspan="3"></td>
										</tr>
									<%}else{%>
										<tr><td colspan="9" align="center" style="font-size: 13px;font-weight: bold;color: red;">Data Not Available !</td></tr>
									<%}%>
								</tbody>
							</table>
						</div>
					</div>
					
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-success" onclick="doSubmit('<%=delv_base%>','<%=Contact_Person_Name%>',
							'<%=Contact_Customer_Person_City%>','<%=Contact_Customer_Person_Pin%>','<%=Contact_Customer_Person_State%>',
							'<%=format1.format(todayDate)%>','<%=Contact_Suppl_Name%>','<%=Contact_Suppl_Person_City%>',
							'<%=Contact_Suppl_Person_Pin%>','<%=Contact_Suppl_Person_State%>',
							'<%=from_date%>','<%=to_date%>','<%=flsa_no%>','<%=sign_dt%>','<%=contract_no%>','<%=mapp_id%>','<%=plant_no%>','<%=indx%>');">Send Mail</button>
					</div>
				</div>
			</div>
		</div>
		
<input type="hidden" id="Contact_Customer_Person_Address" value="<%=Contact_Customer_Person_Address%>">
<input type="hidden" id="Contact_Suppl_Person_Address" value='<%=Contact_Suppl_Person_Address%>'>


	<%}%>
<%}catch(Exception e){	
	e.printStackTrace();
}%> 
</form>

</div>
<!-- /.box -->
</div>
</div>
<!-- /.tab-content -->

<!-- jQuery -->
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
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
daysOfWeekDisabled: "0,2,3,4,5,6",
toggleActive:"1"
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
    
$('.datepick').each(function(){
$(this).datepicker({
changeMonth: true,
changeYear: true,
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
todayHighlight: true,  
orientation: "bottom auto",
startDate:"tommorow"
});
});
</script>
</body>
</html>