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
<link rel="stylesheet" href="../responsive/toastr/toastr.min.css">

<style>
.accordion {
  background-color: #eee;
  color: #444;
  cursor: pointer;
  padding: 10px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
  transition: 0.4s;
}

.active, .accordion:hover {
  background-color: #ccc;
}

 /* .accordion:after {
  content: '\002B';
  color: #777;
  font-weight: bold;
  float: right;
  margin-left: 5px;
} 
  */
/*  .active:after {
  content: "\2212";
}  
 */
.panel {
  padding: 0 18px;
  background-color: white;
  max-height: 0;
  overflow: hidden;
  transition: max-height 0.2s ease-out;
}
</style>

<!-- accordian -->
<!-- <script src="../js/bootstrap.min.js"></script>
<script src="../js/jquery.min.js"></script> -->
<!-- end -->

<script type="text/javascript">
function setAccordian(obj,id){
	var acc = document.getElementsByClassName("accordion");
	var i;
	for (i = 0; i < acc.length; i++) {
		var oth_id = 'acrdBtn'+i;
// 		alert(oth_id)
		if(id == oth_id){
// 			alert('in')
		   var newclass=''; 
		   var classNm = document.getElementById('plusmins'+i).className;
		   
		   if(classNm == 'fa fa-plus-circle'){
			   newclass = 'fa fa-minus-circle';
		   }else{
			   if(classNm == 'fa fa-minus-circle'){
				   newclass = 'fa fa-plus-circle';
			   }
		   }
		   
		   document.getElementById('plusmins'+i).className = newclass; 
		   
			obj.classList.toggle("active");
		    var panel = obj.nextElementSibling;
		    
		    if (panel.style.maxHeight) {
		      panel.style.maxHeight = null;
		    } else {
		      panel.style.maxHeight = panel.scrollHeight + "px";
		    }
		}
	}
}

$(document).ready(function()
{
var get = document.forms[0].index_count.value;
var k= 2;		
var k1 = document.forms[0].total_truck_avail.value;
var index = document.forms[0].index_count.value;

for(var k = 0;k<k1;k++) {
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
});

function calculateSCM(j){
	var qty = "";
	var round_upto_digits = 2;
	
	var gcv = parseFloat(""+document.forms[0].gcv.value);
	var ncv = parseFloat(""+document.forms[0].ncv.value);
	
	var deviding_factor = parseFloat("1");
	
	if(document.forms[0].rd[0].checked)
	{
		gcv = parseFloat(""+document.forms[0].gcv.value);
		deviding_factor = parseFloat("1");
	}
	else if(document.forms[0].rd[1].checked)
	{
		gcv = parseFloat(""+document.forms[0].ncv.value);
		deviding_factor = parseFloat("1");
	}
	
	var multiplying_factor_2 = 0.252; //For Converting MMBTU TO MMSCM ...
	var multiplying_factor = 0.252*1000000; //For Converting MMBTU TO SCM ...
	var conversion_factor_from_m3_to_mmbtu = 51.5; //For Converting M3 To MMBTU ...
	var conversion_factor_from_m3_to_tonmmbtu = 0.3531466672; //SUJIT11FEB2020
	var convt_mmbtu_to_mt = 51.5; //SUJIT05MARCH2020
	qty = document.getElementById("loaded_vol"+j).value; //alert(qty);
	//document.getElementById("unloaded_vol"+j).value = round(qty,2);
	if(qty!=''){ //HA20181229
		document.getElementById("loaded_vol_mmbtu"+j).value = ""+round(((parseFloat(""+qty)*conversion_factor_from_m3_to_mmbtu)),2);
		var calc_mmbtu = document.getElementById("loaded_vol_mmbtu"+j).value;
		document.getElementById("loaded_vol_scm"+j).value = ""+round(((parseFloat(""+calc_mmbtu)*multiplying_factor)/(gcv*deviding_factor)),2);
		//SUJIT05MARCH2020
		document.getElementById("loaded_vol_ton"+j).value = round(parseFloat(calc_mmbtu) / parseFloat(convt_mmbtu_to_mt) ,2 );
		
	}else{ //HA20181229
		document.getElementById("loaded_vol_mmbtu"+j).value='';
		document.getElementById("loaded_vol_scm"+j).value ='';
		//document.getElementById("unloaded_vol"+j).value = '';//SUJIT05MARCH2020
	}	
}

function refreshPage()
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	
	var gas_date = document.forms[0].gas_date.value;
	var gen_date = document.forms[0].gen_date.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	/*HA20181224 var form_id = document.forms[0].form_id.value;
	var form_nm = document.forms[0].form_nm.value; */
	
	/*HA20181224 location.replace("frm_tank_truck_load.jsp?gas_date="+gas_date+"&gen_date="+gen_date+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+form_id+"&FormName="+form_nm); */
	location.replace(modUrl+"?gas_date="+gas_date+"&gen_date="+gen_date+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+
					"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl);
}

function doSubmit()
{
	var flag = true;
	var msg = "First Check The Following Fields :\n\n";
	var index_count = parseInt(document.forms[0].index_count.value);
	var i = 0;
	
	var gas_date = document.forms[0].gas_date.value;
	var gen_date = document.forms[0].gen_date.value;
	var gcv = document.forms[0].gcv.value;
	var ncv = document.forms[0].ncv.value;
	
	if(gas_date==null || gas_date=='0' || gas_date=='' || gas_date==' ' || gas_date=='  ' || gas_date=='  ')
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
	}
	var total_truck = document.forms[0].total_truck_avail.value;
	var total_loaded_vol = 0;
	var count = 0;
// 	for(var i=0;i<index_count;i++) {
		for(var j=0;j<parseFloat(total_truck);j++) {
			var chk = "chk"+j;
			if(document.getElementById(chk)) {
				if(document.getElementById(chk).checked) {
					var load_st_day = document.getElementById("load_st_day"+j).value;
					var load_end_day = document.getElementById("load_end_day"+j).value;
					//var load_st_time = document.getElementById("load_st_time"+j).value;
					//var load_end_time = document.getElementById("load_end_time"+j).value;
					var load_vol = document.getElementById("loaded_vol"+j).value;
					var modifyFlag = document.getElementById("modifyFlag"+j).value;
					
					if(load_st_day=='') {
						msg += "Please Enter Load Start Day For Truck "+(j+1)+" Under Customer "+(j+1)+"!!\n";
						flag = false;
					} 
				   if(load_end_day=='') {//SUJIT05MARCH2020
						msg += "Please Enter Load End Day For Truck "+(j+1)+" Under Customer "+(i+1)+"!!\n";
						flag = false;
					} 
					/* if(load_st_time=='') {
						msg += "Please Enter Load Start Time For Truck "+(j+1)+" Under Customer "+(j+1)+"!!\n";
						flag = false;
					}
					if(load_end_time=='') {//SUJIT05MARCH2020
						msg += "Please Enter Load End Time For Truck "+(j+1)+" Under Customer "+(i+1)+"!!\n";
						flag = false;
					} */
					
					if(load_vol=='' || parseFloat(load_vol)==0) {
						msg += "Please Enter Loaded Volume For Truck "+(j+1)+" Under Customer "+(j+1)+"!!\n";
						flag = false;
					} else {
						if(modifyFlag=='Y') {
							
						} else {
							total_loaded_vol += parseFloat(load_vol);							
						}
					}
					count++;
				}
			}
		}
// 	}
	if(count==0) {
		flag = false;
		msg += "Please Load At Least One Truck to Submit!!!\n";
	}
	
	
		
	if(flag)
	{
		var a = confirm("Do You Want To Submit Truck Loading Details ???");
		if(a)
		{
			var b = confirm("Do You Want To Prepare Invoice ???");
			if(b){
				document.forms[0].prepareInv.value="Y";	
			}
			document.forms[0].submit();
		}
	}
	else
	{
		alert(msg);
	}
}
function validateLoadedQty(obj,i,j) { 
	var total_truck = document.forms[0].total_truck_avail.value;
	var truck_capacity = document.getElementById("truck_capacity"+i).value;
	var truck_vol = document.getElementById("truck_capacityM3"+i).value;
	var total_loaded = 0;
	var original_loaded_vol = parseFloat(document.getElementById("already_loaded_vol"+i).value); //alert("Already Loaded Vol: "+total_loaded);

	for(var i=0;i<total_truck;i++) {
		var loaded_vol = document.getElementById("loaded_vol"+i).value; //alert("Loaded Vol: "+total_loaded);
		var loaded_mmbtu = document.getElementById("loaded_vol_mmbtu"+i).value; //alert("Loaded MMBTU: "+loaded_mmbtu);
		if(loaded_vol!='') {
			total_loaded += parseFloat(loaded_vol); 
		}
	}
	//scheduled qty
	var sch_qty = document.getElementById("sch_qty"+j).value; //alert("Schedule Qty: "+sch_qty+"MMBtu");
	var sch_qty_max = parseFloat(sch_qty)+(parseFloat(sch_qty)*0.05); //SB20200718: Limit 5% more
	var sch_qty_min = parseFloat(sch_qty)-(parseFloat(sch_qty)*0.05); //SB20200718: Limit 5% more
	///SB20200718///////
//	 alert(sch_qty_max);  alert(sch_qty_min);
/*	if(parseFloat(sch_qty)>parseFloat(obj.value)) {
		alert("Can't Load Quantity More Than  Scheduled Quantity!!!");
		obj.value = '';
	}
	else if(parseFloat(sch_qty)<parseFloat(obj.value)) {
		alert("Can't Load Quantity Less Than  Scheduled Quantity!!!");
		obj.value = '';
	}*/
	/////////^SB20200718//////
	//SB if(parseFloat(obj.value)>parseFloat(truck_capacity)) {
	if(parseFloat(obj.value)>parseFloat(truck_vol)){ //SB20181219
		alert("Can't Load Quantity More Then Truck Capacity!! "+truck_vol+" M3");
	obj.value = '';
	}
	else if((parseFloat(obj.value)*51.5) > parseFloat(sch_qty)){
		alert("WARNING !!!\n\nLoaded Qty: "+(parseFloat(obj.value)*51.5)+" MMBtu\n"+"Schedule Qty: "+sch_qty+" MMBtu\n\n"+"Loaded Quantity More Than Scheduled Quantity!!");
	//	obj.value = '';
	}
	else if((parseFloat(obj.value)*51.5) < parseFloat(sch_qty)){
		alert("WARNING !!!\n\nLoaded Qty: "+(parseFloat(obj.value)*51.5)+" MMBtu\n"+"Schedule Qty: "+sch_qty+" MMBtu\n\n"+"Loaded Quantity Less Than Scheduled Quantity!!");
	//	obj.value = '';
	}
	var loaded_mmbtu_truck=(parseFloat(obj.value)*51.5);
	//alert(loaded_mmbtu_truck);
	//alert(document.getElementById("already_loaded_vol"+i).value);
	//var balance_mmbtu =document.getElementById("balance_qty"+j).value;//alert(balance_mmbtu);
	var schedule_qty =document.getElementById("schedule_qty"+j).value;//alert(schedule_qty);
	var balance_mmbtu=schedule_qty-loaded_mmbtu_truck;//alert(balance_mmbtu);
	document.getElementById("balance_qty"+j).value=balance_mmbtu;
	
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

function checkLoadVol(unLoadVol,indx){

	var loadVol=document.getElementById('loaded_vol'+indx).value;
	if(unLoadVol>loadVol){
		alert('Unloaded volume should be equal or less than loaded volume!!');
		//document.getElementById('unloaded_vol'+indx).value='';
	}
}
function setLoadDate(){
	
	var gen_date = document.forms[0].gen_date.value;
	var tot_truck = document.forms[0].total_truck_avail.value;
	
	for(var i = 0 ;  i < parseFloat(tot_truck) ; i++){
		document.getElementById('load_st_day'+i).value = gen_date;
	}
}

function convToMmbtu(k,j){
	
	var loaded_vol_ton = document.getElementById('loaded_vol_ton'+k).value;
	var gcv_per_mmbtu = document.getElementById('gcv_per_mmbtu'+k).value;
	
	if(loaded_vol_ton !='' && gcv_per_mmbtu !='' && loaded_vol_ton !=' ' && gcv_per_mmbtu !=' '){
		document.getElementById('loaded_vol_mmbtu'+k).value = parseFloat(loaded_vol_ton) * parseFloat(gcv_per_mmbtu);
		
	}else{
		document.getElementById('loaded_vol_mmbtu'+k).value = 0; 
	}
}
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.contract_mgmt.DataBean_MgmtV2" id="contMgmt" scope="page"/>
<%
SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
Date time = new Date();
SimpleDateFormat formatters = new SimpleDateFormat("dd/MM/yyyy");
Date date = new Date();
java.text.NumberFormat nf = new java.text.DecimalFormat("###0.00");
utilBean.init();
String current_date = utilBean.getGen_dt();
String tomorrow_date = utilBean.getDate_tomorrow();

String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");
		 
String gas_date = request.getParameter("gas_date")==null?current_date:request.getParameter("gas_date");
String gen_date = request.getParameter("gen_date")==null?current_date:request.getParameter("gen_date");
String msg = request.getParameter("msg")==null?"":request.getParameter("msg");

String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
String formName = request.getParameter("FormName")==null?"":request.getParameter("FormName");
String error_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");

contMgmt.setCallFlag("GENERATE_GEN_DATE_FOR_NOMINATION");
contMgmt.setGas_date(gas_date);
contMgmt.init();

if(!contMgmt.getGen_date().trim().equalsIgnoreCase(""))
{
	gen_date = contMgmt.getGen_date().trim();
}

double gcv = Double.parseDouble(request.getParameter("gcv")==null?"9802.80":request.getParameter("gcv"));
double ncv = Double.parseDouble(request.getParameter("ncv")==null?"8831.35":request.getParameter("ncv"));

String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212
String modUrl = session.getAttribute("modUrl")==null?"":session.getAttribute("modUrl").toString();//HA20230216

double percentage_of_dcq = 10;

contMgmt.setCallFlag("TRUCK_LOAD");
contMgmt.setGas_date(gas_date);
contMgmt.init();

String truck_load_date = contMgmt.getGas_date(); //SB20200725
Vector daily_Seller_Nom_Fgsa_No = contMgmt.getDaily_Seller_Nom_Fgsa_No();
Vector daily_Seller_Nom_Fgsa_Rev_No = contMgmt.getDaily_Seller_Nom_Fgsa_Rev_No();
Vector daily_Seller_Nom_Sn_No = contMgmt.getDaily_Seller_Nom_Sn_No();
Vector daily_Seller_Nom_Sn_Rev_No = contMgmt.getDaily_Seller_Nom_Sn_Rev_No();
Vector daily_Seller_Nom_Cd = contMgmt.getDaily_Seller_Nom_Cd();
Vector daily_Seller_Nom_Abbr = contMgmt.getDaily_Seller_Nom_Abbr();
Vector daily_Seller_Nom_Dcq = contMgmt.getDaily_Seller_Nom_Dcq();
Vector daily_Seller_Nom_Plant_Cd = contMgmt.getDaily_Seller_Nom_Plant_Cd();
// System.out.println(daily_Seller_Nom_Plant_Cd);
Vector daily_Seller_Nom_Plant_Abbr = contMgmt.getDaily_Seller_Nom_Plant_Abbr();
String daily_Total_Dcq_Seller_Nom = contMgmt.getDaily_Total_Dcq_Seller_Nom();
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

double fsru_tank_vol = contMgmt.getFsru_tank_vol();
double int_tank_vol = contMgmt.getInt_tank_vol();
double int_tank_ene = contMgmt.getInt_tank_ene(); //SB20181219
String int_tankId = contMgmt.getInt_tankId();
double int_tank_capacity = contMgmt.getInt_tankCapacity();
double int_tank_capacityM3 = contMgmt.getInt_tankCapacityM3(); //SB20181219
double Int_tankVolAvl = contMgmt.getInt_tankVolAvl(); //SB20181219
double Int_tankVolAvlM3 = contMgmt.getInt_tankVolAvlM3(); //SB20181219
int index = 0;
int record_length = daily_Buyer_Nom_Plant_Seq_No.size();
String plant_cd = "0";

if(record_length>1)
{
	plant_cd = daily_Buyer_Nom_Plant_Seq_No.elementAt(0)+"";
}

Vector daily_Seller_Nom_Mapping_Id = contMgmt.getDaily_Seller_Nom_Mapping_Id();
Vector daily_Sch_Mapping_Id = contMgmt.getDaily_Sch_Mapping_Id(); //SB20181229
Vector tank_truck_id = contMgmt.getTank_truck_id();
// System.out.println("tank_truck_id****"+tank_truck_id);
Vector tank_truck_nm = contMgmt.getTank_truck_nm();
Vector tank_truck_capacity = contMgmt.getTank_truck_capacity();
Vector tank_truck_capacityM3 = contMgmt.getTank_truck_capacityM3(); //SB20181219
Map no_truck_required = contMgmt.getNo_truck_required();
int total_truck_avail = contMgmt.getTotal_truck_avail();
double single_truck_capacity = contMgmt.getSingle_truck_capacity();
Vector tank_truck_trans_cd = contMgmt.getTank_truck_trans_cd();

Map truck_load_start_day = contMgmt.getTruck_load_start_day();
Map truck_load_start_tm = contMgmt.getTruck_load_start_tm();
Map truck_load_end_tm = contMgmt.getTruck_load_end_tm();
Map truck_load_end_day = contMgmt.getTruck_load_end_day();
Map truck_loaded_vol = contMgmt.getTruck_loaded_vol();
Map truck_loaded_ene = contMgmt.getTruck_loaded_ene(); 
Map truck_unloaded_vol = contMgmt.getTruck_unloaded_vol();
Map truck_unloaded_ene = contMgmt.getTruck_unloaded_ene();
Map truck_status = contMgmt.getTruck_status(); 
Map truck_lst_flag = contMgmt.getTruck_flag(); 
Map truck_lst_effDt = contMgmt.getLast_eff_dt();
Map truck_loaded_scm = contMgmt.getTruck_loaded_scm();
Map truck_unloaded_scm = contMgmt.getTruck_unloaded_scm();
Map gcv_per_mmbtu = contMgmt.getGcv_per_mmbtu();

Vector truckCnt = contMgmt.getTruckCnt();
Vector sch_truck_ene = contMgmt.getSch_truck_vol();
Vector sch_truck_scm = contMgmt.getSch_truck_scm();//Hiren_20200221
Vector allocated_qty_mmbtu= contMgmt.getAllocated_qty_mmbtu();//Hiren_20200304
Vector balance_qty_mmbtu= contMgmt.getBalance_qty_mmbtu();//SB20200723

double conversion_factor_from_m3_to_mmbtu = 51.5;
double converssion_factor_from_m3_tonmmbtu = 0.3531466672;
// double convt_mmbtu_to_mt = 51.9;//SUJIT05MARCH2020
double convt_mmbtu_to_mt= (double) session.getAttribute("convt_mmbtu_to_mt");//HA20201229
Vector balance_qty= new Vector();
Vector allocated_truck = contMgmt.getAllocated_truck();
Vector VLoadedNxt_avail_days =contMgmt.getVLoadedNxt_avail_days();
Vector vTotal_Sch_mmbtu = contMgmt.getvTotal_Sch_mmbtu();
Vector rev_bal_qty =  contMgmt.getRev_bal_qty();
Vector truck_inv_cnt = contMgmt.getTruck_inv_cnt();

// System.out.println("truck_inv_cnt---"+truck_inv_cnt);
%>
<body onload="setLoadDate();">
<div class="tab-content">
	<div class="tab-pane active" id="truckloading">
	
	<!-- Default box -->
<div class="box mb-0">
<form  method="post" action="../servlet/Frm_MgmtV2"  >
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
						<input type='text' class="form-control" id="d1" type="text" name="gas_date" value="<%=gas_date%>" onchange="refreshPage();" />
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
						<input type='text' class="form-control" id="d2" type="text" name="gen_date" value="<%=truck_load_date%>" onchange="setLoadDate();" />
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
					<input type="text" class="form-control"  name="gcv"  value="<%=gcv%>" style="text-align: right;" readonly="readonly">
					<span class="input-group-addon">  kcal/SCM </span>
				</div>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
				<div class="form-group input-group mb-0">
					<span class="input-group-addon">
						<input type="radio" name="rd"> NCV
					</span>
					<input type="text" class="form-control" name="ncv" value="<%=ncv%>" style="text-align: right;" readonly="readonly">
					<span class="input-group-addon">  kcal/SCM </span>
				</div>
			</div>
		</div>
	</div>
</div><!-- row wnd -->
<%	try
	{ %>
	<%if(daily_Buyer_Nom_Plant_Seq_No.size()>0) { %>	
		
		<% int  k = 0;
		for(int j=0;j<daily_Buyer_Nom_Plant_Seq_No.size();j++){ %>
			
			<input type="hidden" name="buyer_sch_mapping_id" value="<%=daily_Sch_Mapping_Id.elementAt(j)%>"><!--  SB20181229 -->
			<input type="hidden" name="chk_flag" value="N">
			<input type="hidden" name="buyer_cd" value="<%=daily_Seller_Nom_Cd.elementAt(j)%>">
			<input type="hidden" name="buyer_abbr" value="<%=daily_Seller_Nom_Abbr.elementAt(j)%>">
			<input type="hidden" name="contract_type" value="<%=daily_Seller_Nom_Type.elementAt(j)%>">
			<input type="hidden" name="buyer_mapping_id" value="<%=daily_Seller_Nom_Mapping_Id.elementAt(j)%>">
			<input type="hidden" name="buyer_fgsa_no" value="<%=daily_Seller_Nom_Fgsa_No.elementAt(j)%>">
			<input type="hidden" name="buyer_fgsa_rev_no" value="<%=daily_Seller_Nom_Fgsa_Rev_No.elementAt(j)%>">
			<input type="hidden" name="buyer_sn_no" value="<%=daily_Seller_Nom_Sn_No.elementAt(j)%>">
			<input type="hidden" name="buyer_sn_rev_no" value="<%=daily_Seller_Nom_Sn_Rev_No.elementAt(j)%>">
			<input type="hidden" name="buyer_plant_seq_no" value="<%=daily_Buyer_Nom_Plant_Seq_No.elementAt(j)%>">
			<input type="hidden" name="qty_dcq" value="<%=daily_Seller_Nom_Dcq.elementAt(j)%>" size="8">
			<input type="hidden" name="qty_buyer_mmbtu" value="<%=daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)%>" size="8">
			<input type="hidden" name="sch_qty" id="sch_qty<%=j%>" value="<%=daily_Seller_Nom_Qty_Mmbtu.elementAt(j)%>">
			<input type="hidden" name="nom_rev_no" id="nom_rev_no<%=j%>" value="<%=daily_Buyer_Gen_Day_With_Rev_No.elementAt(j)%>">
		
		<table width="100%">
			<tr>
				<td >
					<button class="accordion" type="button" onclick="setAccordian(this,this.id);" id="acrdBtn<%=j %>" >
						<table width="100%">
							<tr title="<%=daily_Sch_Mapping_Id.elementAt(j)%>: Customer Nominated Qty :<%=daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)%> MMBTU">
							<td width="95%">
							<font color="blue" style="font-weight: bold;"><%=daily_Seller_Nom_Abbr.elementAt(j)%>&nbsp;&nbsp;<%//=daily_Seller_Gen_Day_With_Rev_No.elementAt(j)%>&nbsp;-&nbsp;<%=daily_Buyer_Nom_Plant_Seq_Abbr.elementAt(j)%>&nbsp; (Scheduled <%=gas_date%> Qty : <%=vTotal_Sch_mmbtu.elementAt(j)%>&nbsp;MMBTU)</font>
							 &nbsp;&nbsp;&nbsp;&nbsp;
							 <%double balQty =0; 
							 if(allocated_qty_mmbtu.elementAt(j).equals("0")){ %>
							 	<%balQty = Double.parseDouble(daily_Seller_Nom_Qty_Mmbtu.elementAt(j)+"");%> 
							 <%}else{ 
								balQty = Double.parseDouble(rev_bal_qty.elementAt(j)+"") ;
							 }%>
							 <%if(balQty>0){ %>
							  <font color="green">
							  <%} else { %>
							  	<font color="red">
							  <%} %>
							  (Balance Qty : <%=rev_bal_qty.elementAt(j)%>&nbsp;MMBTU)
							
							 <%balance_qty.add(balQty); %>
							 
							 <input type="hidden" name="balance_qty" id="balance_qty<%=j%>" size="5" value="<%=rev_bal_qty.elementAt(j)%>" readonly>
							 <input type="hidden" name="schedule_qty" id="schedule_qty<%=j%>" size="5" value="<%=rev_bal_qty.elementAt(j)%>" readonly>
						</td>
						<td align="right" ><span id="plusmins<%=j%>" class="fa fa-plus-circle"></span></td>
					</tr>
				</table>
				</button>
					<%
						int no_of_truck_req = 0;
						String key = daily_Seller_Nom_Mapping_Id.elementAt(j)+"-"+daily_Seller_Nom_Sn_No.elementAt(j)+"-"+daily_Seller_Nom_Plant_Seq_No.elementAt(j);
						String no_of_truck_req_data = ""+no_truck_required.get(key);
// 						System.out.println("key**"+key);
						if(no_of_truck_req_data.equals(null) || no_of_truck_req_data.equals("null")) {
							no_of_truck_req = 0;
						} else {
							no_of_truck_req = Integer.parseInt(no_of_truck_req_data);
						}
							
// 						for(int i=0;i<tank_truck_id.size();i++) {
						for(int i = 0 ; i < Integer.parseInt(truckCnt.elementAt(j).toString()); i++){
// 						System.out.println(key+"-"+tank_truck_id.elementAt(k));	
						
							String start_day = ""+truck_load_start_day.get(key+"-"+tank_truck_id.elementAt(k));
// 							System.out.println("start_day****"+start_day);
							if(start_day.equals(null) || start_day.equals("null")) 
								start_day = "";
							String start_tm = ""+truck_load_start_tm.get(key+"-"+tank_truck_id.elementAt(k));
							if(start_tm.equals(null) || start_tm.equals("null")) 
								start_tm = "";
							String end_day = ""+truck_load_end_day.get(key+"-"+tank_truck_id.elementAt(k));
							if(end_day.equals(null) || end_day.equals("null")) 
								end_day = "";
							String end_tm = ""+truck_load_end_tm.get(key+"-"+tank_truck_id.elementAt(k));
							if(end_tm.equals(null) || end_tm.equals("null")) 
								end_tm = "";
							String load_vol = ""+truck_loaded_vol.get(key+"-"+tank_truck_id.elementAt(k));
							if(load_vol.equals(null) || load_vol.equals("null")) 
								load_vol = "";
							String load_ene = ""+truck_loaded_ene.get(key+"-"+tank_truck_id.elementAt(k));
							if(load_ene.equals(null) || load_ene.equals("null")) 
								load_ene = "";
							String unload_vol = ""+truck_unloaded_vol.get(key+"-"+tank_truck_id.elementAt(k)); //SB20181219
							if(unload_vol.equals(null) || unload_vol.equals("null")) 
								unload_vol = "";
							
							String loaded_scm = ""+truck_loaded_scm.get(key+"-"+tank_truck_id.elementAt(k));
							if(loaded_scm.equals(null) || loaded_scm.equals("null")) 
								loaded_scm = "";
							
							String unloaded_scm = ""+truck_unloaded_scm.get(key+"-"+tank_truck_id.elementAt(k));
							if(unloaded_scm.equals(null) || unloaded_scm.equals("null")) 
								unloaded_scm = "";
							
							/* String status = ""+truck_status.get(key+"-"+tank_truck_id.elementAt(k)); //SB20181219
							if(status.equals(null) || status.equals("null")) 
								status = ""; */
							String	status = "Loading";
							
							String truck_last_flag = ""+truck_lst_flag.get(key+"-"+tank_truck_id.elementAt(k)); //HA20190101
							if(truck_last_flag.equals(null) || truck_last_flag.equals("null")) 
								truck_last_flag = "";
							
							String truck_last_effDt = ""+truck_lst_effDt.get(key+"-"+tank_truck_id.elementAt(k)); //HA20190101
							if(truck_last_effDt.equals(null) || truck_last_effDt.equals("null")) 
								truck_last_effDt = "";
							
							boolean flag = false; String chkFlag = "N";
							if(!start_day.equals("")) {
								flag = true;
								chkFlag = "Y";
							}
							
							String truck_gcv_per_mmbtu = ""+gcv_per_mmbtu.get(key+"-"+tank_truck_id.elementAt(k)); //HA20190101
							if(truck_gcv_per_mmbtu.equals(null) || truck_gcv_per_mmbtu.equals("null")) 
								truck_gcv_per_mmbtu = "0";
							
							double sch_vol=0;
							if(load_ene.equals("") && (!sch_truck_ene.elementAt(k).toString().equals(""))){ //if no loading done, assign truck schedule data 
								load_ene = sch_truck_ene.elementAt(k).toString();
								sch_vol = Double.parseDouble(sch_truck_ene.elementAt(k).toString())/51.5;
								load_vol = nf.format(sch_vol)+"";
								loaded_scm = sch_truck_scm.elementAt(k).toString();
								%>
<!-- 								<script> -->
<%-- 									document.getElementById("loaded_vol"+<%=k%>).readOnly=true; --%>
<!-- 								</script> -->
								<%
							}
							
							%>
				<div class="panel">
					<table>
					<tr>
					<td> 
					
					<% if(allocated_truck.elementAt(k).equals("Y") && Integer.parseInt(truck_inv_cnt.elementAt(k)+"") > 0) {%>
<%-- 					<% if(allocated_truck.elementAt(k).equals("Y") ) {%> --%>
						<input disabled type="radio" name="chk<%=j%>" id="chk<%=k%>" value="" onclick="enableAll('<%=k%>',this,this.id,'<%=j%>');">
					<%}else{ %>
						<input  type="radio" name="chk<%=j%>" id="chk<%=k%>" value="" onclick="enableAll('<%=k%>',this,this.id,'<%=j%>');">
					<%} %>
					&nbsp;&nbsp;</TD>
					<TD width="15%">
					<label for="" TITLE="Truck Capacity: <%=tank_truck_capacityM3.elementAt(k) %>&nbsp;&#13221;&nbsp;&nbsp;<%=tank_truck_capacity.elementAt(k)%> MMBTU">
						<%=tank_truck_nm.elementAt(k)%>&nbsp;<BR>[<%=daily_Seller_Nom_Abbr.elementAt(j)%>&nbsp;-&nbsp;<%=daily_Buyer_Nom_Plant_Seq_Abbr.elementAt(j)%>]
							<br>
							<%
							if(allocated_truck.elementAt(k).equals("Y")) {%>
								<font color="red" size="1" id="sts<%=k%>">	Loading Done for : <%=truck_load_date %> </font>
							<%}else{ %>
								<font color="green" size="1" id="sts<%=k%>"> Click here to LOAD</font>
							<%}%>
					</label>
					
						<input type="hidden" name="plant_no<%=j%>" value="<%=daily_Seller_Nom_Mapping_Id.elementAt(j)+"-"+daily_Seller_Nom_Sn_No.elementAt(j)+"-"+daily_Seller_Nom_Plant_Seq_No.elementAt(j)%>">
						<input type="hidden" name="chkFlag<%=j%>" id="chkFlag<%=k%>" value="">
						<input type="hidden" name="truck_id<%=j%>" value="<%=tank_truck_id.elementAt(k)%>">
						<input type="hidden" name="truck_nm<%=j%>" id="truck_nm<%=k%>" value="<%=tank_truck_nm.elementAt(k)%>">
						<input type="hidden" name="truck_capacityM3<%=j%>" id="truck_capacityM3<%=k%>" value="<%=tank_truck_capacityM3.elementAt(k)%>">
						<input type="hidden" name="truck_trans_cd<%=j%>"  id="truck_trans_cd<%=k%>"  value="<%=tank_truck_trans_cd.elementAt(k)%>">
						<input type="hidden" name="next_avail_days<%=j%>"  id="next_avail_days<%=k%>"  value="<%=VLoadedNxt_avail_days.elementAt(k)%>">
					</td>
				    
<%-- 					<td> <%=tank_truck_capacity.elementAt(k) %> --%>
 						<input type="hidden" name="truck_capacity<%=j%>" id="truck_capacity<%=k%>" value="<%=tank_truck_capacity.elementAt(k)%>">
<!-- 					</td> -->
					
<%-- 					<td> <%=nf.format(Double.parseDouble(""+daily_Seller_Nom_Qty_Mmbtu.elementAt(j)))%>  </td> --%>
					
					<td > 
						<% if(allocated_truck.elementAt(k).equals("Y")) {%>
						<div class="col-lg-7 pr-5" >
							<input class="form-control datepick<%=k%>"  id="load_st_day<%=k%>"  type="text" name="load_st_day<%=j%>" value="<%=gen_date%>" onBlur="compareDt('<%=k%>');" readonly="readonly"  style="text-align: center;" autocomplete="off">
						</div>
						<div class="col-lg-5 pl-5">
							<input class="form-control" style="text-align: left;" type="text" name="load_st_time<%=j%>"  id="load_st_time<%=k%>"  readonly="readonly"  value="<%=start_tm %>" >
						</div>
						<%}else{ %>
						<div class="col-lg-7 pr-5" >
							<input class="form-control datepick<%=k%>"  id="load_st_day<%=k%>"  type="text" name="load_st_day<%=j%>" <%if(start_day.equals("")) { %>value="<%=formatters.format(date)%>" <%} else { %>  value="<%=gen_date%>" <%} %> onBlur="compareDt('<%=k%>');" readonly="readonly"  style="text-align: center;" autocomplete="off">
						</div>
						<div class="col-lg-5 pl-5">
							<input class="form-control" style="text-align: left;" type="text" name="load_st_time<%=j%>"  id="load_st_time<%=k%>"  readonly="readonly"  <%if(start_tm.equals("")) { %>value="<%=formatter.format(time)%>" <%} else { %> value="<%=start_tm %>" <%} %> >
						</div>
						<%} %>
						<font color="black" size="1" id="start<%=k%>" style="padding-left: 1cm;">Loading Start Dt/Time(HH:MM)</font>
					</td>
					<td > 
						<% if(allocated_truck.elementAt(k).equals("Y")) {%>
						<div class="col-lg-7 pr-5">
						<input class="form-control datepick<%=k%>" id="load_end_day<%=k%>" type="text" name="load_end_day<%=j%>"  value="<%=truck_load_date %>" onBlur="compareDt('<%=k%>');" readonly="readonly"  style="text-align: center;" autocomplete="off">
						</div>
						<div class="col-lg-5 pl-5">
						<input class="form-control" style="text-align: left;" type="text" name="load_end_time<%=j%>" id="load_end_time<%=k%>" readonly="readonly" value="<%=end_tm %>" >
						</div>
						<%}else{ %>
						<div class="col-lg-7 pr-5">
							<input class="form-control datepick<%=k%>" id="load_end_day<%=k%>" type="text" name="load_end_day<%=j%>"  value="<%=end_day%>" onBlur="compareDt('<%=k%>');" readonly="readonly"  style="text-align: center;" autocomplete="off">
						</div>
						<div class="col-lg-5 pl-5">
							<input class="form-control" style="text-align: left;" type="text" name="load_end_time<%=j%>" id="load_end_time<%=k%>" readonly="readonly" <%if(end_tm.equals("")) { %>value="<%=formatter.format(time)%>" <%} else { %> value="<%=end_tm %>" <%} %> >
						</div>
						<%} %>
						<font color="black" size="1" id="end<%=k%>" style="padding-left: 1cm;">Loading End Dt/Time(HH:MM)</font>
					</td>
					<%-- <td> 
						<div class="col-lg-12">
							<input class="form-control" style="text-align: right;" type="text" name="loaded_vol<%=j%>" id="loaded_vol<%=k%>" value="<%=load_vol%>" onchange="validateLoadedQty(this,'<%=k%>','<%=j %>'); calculateSCM('<%=k%>');" readonly="readonly"  style="text-align: center;">
							<input type="hidden" name="loaded_vol_scm<%=j%>" id="loaded_vol_scm<%=k%>" value="<%=loaded_scm%>">
							<input type="hidden" name="unloaded_vol_scm<%=j%>" id="unloaded_vol_scm<%=k%>" value="<%=unloaded_scm%>">
							<input type="hidden" name="modifyFlag<%=j%>" id="modifyFlag<%=k%>" value="<%=chkFlag%>">
							<input type="hidden" name="already_loaded_vol" id="already_loaded_vol<%=k%>" value="<%=load_vol%>">
						</div>
						<font color="black" size="1" id="loaded_vol<%=k%>" style="padding-left: 1cm;">Loaded Vol(<b>&#13221;)</font>
					</td> --%>
					<td > 
						<div class="col-lg-12">
							<div class="input-group" >
							<%if(!load_ene.equals("")){//SUJIT05MARCH2020
								double loadEneton = Double.parseDouble(load_ene) / convt_mmbtu_to_mt;
								%>
								<input class="form-control" type="text" name="loaded_vol_ton<%=j%>" id="loaded_vol_ton<%=k%>" value="<%=load_vol%>"  style="text-align: center;" onchange="convToMmbtu('<%=k%>','<%=j %>');" readonly="readonly">
							<%}else{%>
								<input class="form-control" type="text" name="loaded_vol_ton<%=j%>" id="loaded_vol_ton<%=k%>" value=""  style="text-align: center;" onchange="convToMmbtu('<%=k%>','<%=j %>');" readonly="readonly">
							<%}%>								
								 <span class="input-group-addon" title="Fetch MT value from PI" id = "mt_span<%=k%>" style="display: none;">
									<img alt="" src="../images/greenInfo.gif" style="height: 20px;width: 20px;" id="mt_val<%=k%>" onclick="fetchPIVal('<%=k %>','fetchMT','<%=j%>');" >
								</span>
							</div>
						</div>	
						<font color="black" size="1" id="loaded_mt<%=k%>" style="padding-left: 1cm;">Loaded Qty(MT)</font>
					</td>
					<td> 
						<div class="col-lg-12">
							<input class="form-control" style="text-align: right;" type="hidden" name="loaded_vol<%=j%>" id="loaded_vol<%=k%>" value="<%=load_vol%>" onchange="validateLoadedQty(this,'<%=k%>','<%=j %>'); calculateSCM('<%=k%>');" readonly="readonly"  style="text-align: center;">
							<input type="hidden" name="loaded_vol_scm<%=j%>" id="loaded_vol_scm<%=k%>" value="<%=loaded_scm%>">
							<input type="hidden" name="unloaded_vol_scm<%=j%>" id="unloaded_vol_scm<%=k%>" value="<%=unloaded_scm%>">
							<input type="hidden" name="modifyFlag<%=j%>" id="modifyFlag<%=k%>" value="<%=chkFlag%>">
							<input type="hidden" name="already_loaded_vol" id="already_loaded_vol<%=k%>" value="<%=load_vol%>"> 
							
							<div class="input-group" >
								<input class="form-control" style="text-align: right;" type="text" name="gcv_per_mmbtu<%=j%>" id="gcv_per_mmbtu<%=k%>" value="<%=truck_gcv_per_mmbtu %>"   style="text-align: center;" onchange="convToMmbtu('<%=k%>','<%=j %>');" readonly="readonly" val>
								 <span class="input-group-addon" title="Fetch Conv. GCV value from PI" id="gcv_span<%=k%>" style="display: none;">
									<img alt="" src="../images/greenInfo.gif" style="height: 20px;width: 20px;" id="gcv_val<%=k%>" onclick="fetchPIVal('<%=k %>','fetchGCV','<%=j%>');">
								</span>
							</div>
						</div>
						<font color="black" size="1" id="" style="padding-left: 1cm;" >GCV Per MMBTU</font>
					</td>
					<td> 
						<div class="col-lg-12">
							<input class="form-control" type="text" name="loaded_vol_mmbtu<%=j%>" id="loaded_vol_mmbtu<%=k%>" value="<%=load_ene%>"  style="text-align: center;" readonly="readonly">
						</div>
						<font color="black" size="1" id="loaded_eng<%=k%>" style="padding-left: 1cm;">Loaded Qty(MMBTU)</font>
					</td>
					<td> 
						<div class="col-lg-12">
						<% if(allocated_truck.elementAt(k).equals("Y")) {%>
						LOADED
						<%} else { %>
						<input class="form-control"  type="text" name="status<%=j%>" id="status<%=k%>" value="<%=status%>"  readonly="readonly" style="text-align: center;">
						<%} %>
						</div>
						<font color="red" size="1" id="start<%=k%>" style="padding-left: 0.5cm;">Current Status</font>
						<input type="hidden" name="no_req_truck<%=j%>" value="<%=no_of_truck_req%>" id="no_req_truck<%=k%>">
					</td>
				</tr>
				<% k++; }//inner for %>
					</table>  
					</div>
				</td>	
			</tr>
	</table>
		
		<%++index;
		}//for end%>
	<%}else { %>
	<div class="box-body table-responsive no-padding">
		<table class="table  table-bordered">
			<thead>   
				<tr class="info">
					<th class="text-center"  style="color: red;"> Schedule Data Not Available!!</th>
				</tr>
			</thead>
		</table>
	</div>
	<% }
	
	}catch(Exception e){
		e.printStackTrace();
// 		System.out.println("e.getMessage()---------"+e.getMessage());
	} %>

	<input type="hidden" name="modCd" value="<%=modCd%>">
	<input type="hidden" name="formId" value="<%=formId%>">
	<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
	<input type="hidden" name="modUrl" value="<%=modUrl%>">
	<input type="hidden" name="write_permission" value="<%=write_permission%>">
   	<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
   	<input type="hidden" name="print_permission" value="<%=print_permission%>">
   	<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
   	<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
   	<input type="hidden" name="index_count" value="<%=index%>">
	<input type="hidden" name="percentage_of_dcq" value="<%=percentage_of_dcq%>">
	<input type="hidden" name="total_truck_avail" value="<%=tank_truck_id.size()%>">
	<input type="hidden" name="option" value="submitTruckLoad">
	<input type="hidden" name="prepareInv" value="N">
		
</form>
<%-- <%if(daily_Buyer_Nom_Plant_Seq_Abbr.size()>0){ %> --%>
<div class="box-footer">
	<div class="row">
		<div class="col-sm-12 text-right">
		 	<button type="button" class="btn btn-warning" name="reSetPage" value="Reset" onClick="refreshPage();"> Reset </button>
			<button onclick="doSubmit();" type="button" class="btn btn-success" name="save" value="Submit" > Submit </button> 
		</div>
	</div>
</div>
<%--  <%}%>  --%>
<script src="../responsive/toastr/toastr.min.js"></script>
<script type="text/javascript">
setAll();
function enableAll(j,obj,selId,balIndx) 
{
// 	alert(balIndx)
	var balance_qty = document.getElementById("balance_qty"+balIndx).value;
	var schedule_qty = document.getElementById("schedule_qty"+balIndx).value;
// 	var chk_len = document.forms[0].buyer_plant_seq_no.length;
	if(balance_qty > 0){
		var matchFlg=false;
		var selTruck='';
		var no_req_truck = document.getElementById("no_req_truck"+j).value;
		var total_truck = document.forms[0].total_truck_avail.value;
		
		if(obj.checked)//true
		{ 
			var flag = true;
			if(!document.getElementById("load_st_day"+j).value=='')  
				document.getElementById("load_end_day"+j).value=document.forms[0].gen_date.value;
			else
				document.getElementById("load_st_day"+j).value=document.forms[0].gen_date.value;
			
			document.getElementById("load_st_day"+j).readOnly = false;
			document.getElementById("load_st_time"+j).readOnly = false;
			
			document.getElementById("load_end_day"+j).readOnly = false;
			document.getElementById("load_end_time"+j).readOnly = false;
			
			document.getElementById('loaded_vol_mmbtu'+j).readOnly = false;
			document.getElementById('gcv_per_mmbtu'+j).readOnly = false;
			document.getElementById('loaded_vol_ton'+j).readOnly = false;
			
			if(document.getElementById("modifyFlag"+j).value=='Y' ) {
				document.getElementById("loaded_vol"+j).readOnly = true;
				document.getElementById("loaded_vol"+j).title = "Can't Modify Loaded Qty!!";
			} else {
				if(document.getElementById("loaded_vol"+j).value!='' && document.getElementById("loaded_vol"+j).value!='0'){
					document.getElementById("loaded_vol"+j).readOnly = false;
				}else{
					document.getElementById("loaded_vol"+j).readOnly = false;
					document.getElementById("loaded_vol"+j).title = '';
					document.getElementById("loaded_vol"+j).value='0'; //SB20181219
				}
			}
			
			document.getElementById("chkFlag"+j).value = 'Y';
			
			//Disable selected truck for another customer with same truck number
			for(var  i = 0 ; i < total_truck ; i++){
				if(i == j){
					matchFlg = true;
					selTruck = document.getElementById("truck_nm"+j).value;
				}
				
				/* to hide PI image */
// 				alert(i+"***"+j)
				if(i!=j && (!document.getElementById("chk"+i).checked)){
					document.getElementById("gcv_span"+i).style.display = "none";
					document.getElementById("mt_span"+i).style.display = "none";
// 					alert(document.getElementById("gcv_span"+i).style.display)
				}
			}
			
			if(matchFlg){
				for(var  i = 0 ; i < total_truck ; i++){
					if(selTruck == document.getElementById("truck_nm"+i).value && i != j){
						document.getElementById("chk"+i).disabled=true;
						document.getElementById("chk"+i).title = 'Already selected!! please choose another one..';
					}
				}
			}//alert(document.getElementById("loaded_vol"+j).value);
			var loaded_mmbtu_truck=(parseFloat(document.getElementById("loaded_vol"+j).value)*51.5);//alert(loaded_mmbtu_truck);
			var schedule_qty =document.getElementById("schedule_qty"+balIndx).value;//alert(schedule_qty);
		//	alert(loaded_mmbtu_truck);
			//alert(document.getElementById("already_loaded_vol"+i).value);
		//	var balance_mmbtu =document.getElementById("balance_qty"+j).value;//alert(balance_mmbtu);
			var balance_mmbtu=schedule_qty-loaded_mmbtu_truck;//alert(balance_mmbtu);
			document.getElementById("balance_qty"+balIndx).value=balance_mmbtu;
			document.getElementById("gcv_span"+j).style.display = "table-cell";
			document.getElementById("mt_span"+j).style.display = "table-cell";
		/* ************************************************ */
		} else { //checkbox uncheked
		
			document.getElementById("load_st_day"+j).readOnly = true;
			document.getElementById("load_st_time"+j).readOnly = true;
			document.getElementById("load_end_day"+j).readOnly = true;
			document.getElementById("load_end_time"+j).readOnly = true;
			document.getElementById("loaded_vol"+j).readOnly = true;
			
			document.getElementById('loaded_vol_mmbtu'+j).readOnly = true;
			document.getElementById('gcv_per_mmbtu'+j).readOnly = true;
			document.getElementById('loaded_vol_ton'+j).readOnly = true;
			
			document.getElementById("load_end_day"+j).value = "";
			document.getElementById("chkFlag"+j).value = 'N';
			
			//SUJIT05MARCH2020
			if(document.getElementById("already_loaded_vol"+j).value == ''){
				document.getElementById("loaded_vol"+j).value = "";	
				document.getElementById("load_st_day"+j).value = "";
				document.getElementById("loaded_vol_ton"+j).value = "";
			}
			
			for(var  i = 0 ; i < total_truck ; i++){
				if(i == j){
					matchFlg = true;
					selTruck = document.getElementById("truck_nm"+j).value;	
				}
				/* to hide PI image */
				if(i!=j && (!document.getElementById("chk"+i).checked)){
					document.getElementById("gcv_span"+i).style.display = "none";
					document.getElementById("mt_span"+i).style.display = "none";
				}
			}
			if(matchFlg){
				for(var  i = 0 ; i < total_truck ; i++){
					if(selTruck == document.getElementById("truck_nm"+i).value && i != j){
						document.getElementById("chk"+i).disabled=false;
					}
				}
			}
			var schedule_qty =document.getElementById("schedule_qty"+balIndx).value;//alert(balance_mmbtu);
		//	balance_mmbtu=schedule_qty;//alert(balance_mmbtu);
			document.getElementById("balance_qty"+balIndx).value=schedule_qty;
			document.getElementById("gcv_span"+j).style.display = "none";
			document.getElementById("mt_span"+j).style.display = "none";
		}
	}else{
		//var schedule_qty =document.getElementById("schedule_qty"+j).value;//alert(balance_mmbtu);
		//balance_mmbtu=schedule_qty;//alert(balance_mmbtu);
		document.getElementById("balance_qty"+balIndx).value=schedule_qty;
		if(parseFloat(balance_qty) <= 0){
		alert('Not enough balance quantity to load!!');	
		}
		obj.checked=false;
	}
	
	//Hiren_20220716 to clear chkFlag value once unchecked...
	<%
	int k = 0 ;
	for(int j = 0 ; j < daily_Buyer_Nom_Plant_Seq_No.size() ; j++){ %>
		<%for(int i = 0 ; i < Integer.parseInt(truckCnt.elementAt(j).toString()); i++){ %>
			if(j == <%=k%> && document.getElementById("chk"+j).checked){
				document.getElementById("chkFlag"+<%=k%>).value = 'Y';
			}else{
				document.getElementById("chk"+<%=k%>).checked = false;
				document.getElementById("chkFlag"+<%=k%>).value = 'N';
				document.getElementById("load_st_day"+<%=k%>).readOnly = true;
				document.getElementById("load_st_time"+<%=k%>).readOnly = true;
				document.getElementById("load_end_day"+<%=k%>).readOnly = true;
				document.getElementById("load_end_time"+<%=k%>).readOnly = true;
				document.getElementById("loaded_vol"+<%=k%>).readOnly = true;
				
				document.getElementById('loaded_vol_mmbtu'+<%=k%>).readOnly = true;
				document.getElementById('gcv_per_mmbtu'+<%=k%>).readOnly = true;
				document.getElementById('loaded_vol_ton'+<%=k%>).readOnly = true;
				
				document.getElementById("load_end_day"+<%=k%>).value = ""; 
				document.getElementById("gcv_span"+<%=k%>).style.display = "none";
				document.getElementById("mt_span"+<%=k%>).style.display = "none";
			}
		<%k++;}%>
	<%}%>
}
function setAll() {
	var index_count = document.forms[0].index_count.value;
	var total_truck = document.forms[0].total_truck_avail.value;
	var selTruck="";
		for(var i=0;i<total_truck;i++) {
			var modifyFlag = document.getElementById("modifyFlag"+i).value;
			if(modifyFlag=='Y') {
				selTruck = document.getElementById("truck_nm"+i).value;				
				for(var j=0;j<total_truck;j++) {
					if(i!=j && selTruck == document.getElementById("truck_nm"+j).value) 
					{
						document.getElementById("chk"+j).disabled = true;
						document.getElementById("chk"+j).title = 'Can Not Load This Truck As It Is Loaded For Another Customer!!';
						document.getElementById("chkFlag"+j).value = 'N';
					}
				}
			}
		}
}
function fetchPIVal(selId,flag,subInd){
// 	alert(selId)
	 $.ajax({
		 
	        type : "POST",
	        url : "../Frm_Fetch_MT_GCV?option=fetchPIValues&flag="+flag,
	        cache : false,
	        success : function(responseJson) {
// 	        	alert(responseJson)
				var val = JSON.parse(responseJson);
				if(val.msg != ""){
					toastr["error"](val.msg);
				}else{
		        	if(val.flag == 'fetchMT'){
		        		
		        		document.getElementById('loaded_vol_ton'+selId).value = val.value;
			   	 		document.getElementById('loaded_vol_ton'+selId).style.background='#00FF00';
			        			
		        	}else if(val.flag == 'fetchGCV'){
		        		
		        		document.getElementById('gcv_per_mmbtu'+selId).value = val.value;
		        		document.getElementById('gcv_per_mmbtu'+selId).style.background='#00FF00';
		        	}
				}	
				convToMmbtu(selId,subInd);
				
	        }
	 });
}
</script>
<!-- /.box-body -->
</div>
<!-- /.box -->
</div>
</div>
<!-- includes function END-->      
<!-- jQuery -->
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script >

$(function () {
$('#datetimepicker1').datepicker({
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
orientation: "bottom auto",
todayHighlight: true
});
});
$(function () {
$('#datetimepicker2').datepicker({
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
orientation: "bottom auto",
todayHighlight: true
});
});
</script>

</body>
</html>