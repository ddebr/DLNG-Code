<!DOCTYPE html>
<html lang="en">
<%-- <%@ include file="../sess/Expire.jsp" %> --%>
<%@ page import="java.util.*" %>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title> ATS </title>

<!-- BS CSS -->
<link href="../responsive/css/bootstrap.min.css" rel="stylesheet">
<link href="../responsive/css/datepicker/bootstrap-datepicker3.css" rel="stylesheet">
<link rel="stylesheet" href="../responsive/w3/w3.css">  <!-- For accordion  -->

<!-- CSS -->
<link href="../responsive/css/main.css" rel="stylesheet">
<link href="../responsive/css/style.css" rel="stylesheet">


<!-- Font Awesome -->
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">  
<link href="../responsive/css/pt_sans.css" rel="stylesheet">

<script src="../js/draftJS.js"></script>
<!-- jQuery -->
<script src="../responsive/js/jquery.min.js"></script>

<!-- Bootstrap JS -->
<script src="../js/bootstrap.min.js"></script>
<script src="../js/actionJS.js"></script>



<style type="text/css">
.responsive {
  width: 100%;
  max-width: 550px;
  height: auto;
  align: center;
}

table {
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th {
  cursor: pointer;
}

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2
}
</style>

</head>

<script type="text/javascript">

function getActionDtl(map_id,item_desc,user,act_assign_to,target_dt,priority,act_desc,act_comp_stmt,unit_cd,item_cd,unit_nm,item_nm,assign_dt,created_dt){

	var url="../master/frm_mst_modify_action.jsp?map_id="+map_id+"&item_desc="+item_desc+"&user="+user+"&act_assign_to="+act_assign_to+
			"&target_dt="+target_dt+"&priority="+priority+"&act_desc="+act_desc+"&act_comp_stmt="+act_comp_stmt+"&unit_cd="+unit_cd+
			"&item_cd="+item_cd+"&unit_nm="+unit_nm+"&item_nm="+item_nm+"&assign_dt="+assign_dt+"&created_dt="+created_dt;
	window.open(url,"viewsugg2","top=10,left=70,width=650,height=500,scrollbars=1,status=1,maximize=yes,resizable=1");
} 

function hideshow(id1,id2){
	if(document.getElementById(id1).style.display=='none'){
		 document.getElementById(id1).style.display='table-row-group';
		 document.getElementById(id2).className='right-arrow pull-right glyphicon glyphicon-minus';
	}else{
		 document.getElementById(id1).style.display='none';
		 document.getElementById(id2).className='right-arrow pull-right glyphicon glyphicon-plus';
	}
//      document.getElementById('contDiv').style.display='block';
}  

function refresh_from_child(msg,error_msg,assign_nm){
	location.replace("../home/header.jsp?msg="+msg+"&error_msg="+error_msg+"&assign_nm="+assign_nm);
}
</script>

<jsp:useBean id="getActData" class="com.seipl.hazira.dlng.action.Databean_mst_Action"></jsp:useBean>
<jsp:useBean class="com.seipl.hazira.dlng.util.Menu_Bean" id="menu1" scope="page" />
<%
/* ****************menu- start**************** */
String selModule = request.getParameter("modCd")==null?"":request.getParameter("modCd");
String home_click = request.getParameter("home_click")==null?"N":request.getParameter("home_click");
String msg1 =  request.getParameter("msg")==null?"":request.getParameter("msg");
String error_msg1 =  request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
String assign_nm =  request.getParameter("assign_nm")==null?"":request.getParameter("assign_nm");

String sId="";
if(session.getAttribute("username")==null||session.getAttribute("username")=="")
{
    sId="0";
}  
else
{
    sId="1";
}  

String memp_cd1 = "" + session.getAttribute("emp_cd");
String memp_name2 = "" + session.getAttribute("userid");

String mtemp1="MOD";
String mtemp2="SUBMOD";

menu1.setemp_nm(memp_name2);
menu1.setOption(mtemp1);
menu1.init();

Vector mmodule_nm=menu1.getModuleName();
Vector modUrl = menu1.getModUrl();
Vector modCd = menu1.getModCd();
String alw_forward = menu1.getAlw_forward();

/* ****************menu- end**************** */

String flag1=request.getParameter("head_tab")==null?"999999999":request.getParameter("head_tab");
String ats_user_cd=""+session.getAttribute("user_cd");
String userName=""+session.getAttribute("username");

Vector map_id=new Vector();
Vector item_desc=new Vector();
Vector act_type=new Vector();
Vector cat=new Vector();
Vector priority=new Vector();
Vector assign_dt=new Vector();
Vector target_dt=new Vector();
Vector act_desc=new Vector();
Vector  act_status=new Vector();
Vector  act_complete_stmt=new Vector();
Vector act_assign_nm = new Vector();
Map <String, String> map_assign_to = new HashMap <String, String> ();
Vector act_assign_to_nm = new Vector();

Vector act_unit_cd = new Vector();
Vector act_item_cd = new Vector();
Vector act_unit_nm = new Vector();
Vector act_item_nm = new Vector();
Vector act_assign_dt = new Vector();//action assign date

Vector meet_unit_cd = new Vector();
Vector meet_seq_no = new Vector();
Vector meet_date = new Vector();
Vector meet_nm = new Vector();
Vector meet_attandee = new Vector();
Vector meet_mom = new Vector();
Vector meet_status = new Vector();
Vector meet_id_menu = new Vector();

Vector unitNm = new Vector();
Vector meet_source_nm = new Vector();
Vector sub_source_nm = new Vector();
Vector meet_map_id = new Vector();
Vector meet_item_cd = new Vector();
Vector meet_item_nm = new Vector();
Vector meet_source_cd = new Vector();
Vector  act_tilte=new Vector();
Vector  act_seq_no=new Vector();
Vector act_meet_id = new Vector();
Vector act_meet_seq_no = new Vector();

Vector get_unit_cd = new Vector();
Vector get_mapping_id = new Vector();
Vector get_seq_no = new Vector();
Vector get_act_typ = new Vector();
Vector get_priority = new Vector();
Vector get_assign_by = new Vector();
Vector get_assign_dt = new Vector();
Vector get_target_dt = new Vector();
Vector get_status = new Vector();
Vector get_act_desc = new Vector();
Vector get_meet_id = new Vector();
Vector get_title = new Vector();
Vector get_hse_risk = new Vector();
Vector get_re_risk = new Vector();
Vector get_unit_nm = new Vector();
Vector get_assign_nm = new Vector();
Vector get_item_cd = new Vector();
Vector get_item_nm = new Vector();
Vector get_comp_dt = new Vector();
Vector get_attach = new Vector();
Vector act_comp_dt = new Vector();
Vector act_attach = new Vector();
Vector act_rmks = new Vector();
Vector act_take = new Vector();
Vector get_source_nm = new Vector();
Vector get_source_cd = new Vector();
Vector act_source_nm = new Vector();
Vector act_source_cd = new Vector();
Vector get_forward = new Vector();
Vector get_frwd_nm = new Vector();
Vector Vcnt1 = new Vector();
Vector re_ass_cnt = new Vector();
Vector get_rasn_from = new Vector();
Vector get_rasn_to = new Vector();
Vector get_flag= new Vector();
Vector Vtaken_sts = new Vector();
Vector act_typSrc_nm = new Vector();

String form_id="";
String module_cd="";

String rec_form_id="";
String rec_module_cd="";

String temp_modCd =""; 
if(home_click.equals("Y")){
	session.setAttribute("module_cd","");
}else{
	temp_modCd = session.getAttribute("module_cd")+"";
}
// System.out.println("module_cd****"+temp_modCd);

Vector remDay_menu = new Vector();
Vector dueDay_menu= new Vector();
Vector myRemDay = new Vector();
Vector myDueDay= new Vector();

if(flag1.equals("999999999")){
// 	System.out.println("flag1&&&&"+flag1);
	getActData.setCallFlag("get_user_data");
	getActData.setUser_cd(ats_user_cd);
	
	getActData.init();	
	
	meet_unit_cd = getActData.getMeet_unit_cd();
	meet_seq_no =  getActData.getMeet_seq_no();
	meet_date = getActData.getMeet_dt();
	meet_nm = getActData.getMeet_nm();
	meet_attandee = getActData.getMeet_attandee();
	meet_mom = getActData.getMeet_mom();
	meet_status = getActData.getMeet_status();
	meet_id_menu = getActData.getMeet_id();
	meet_map_id = getActData.getMeet_map_id();
	
	unitNm = getActData.getUnitNm();
	meet_source_nm = getActData.getSource_nm();
	sub_source_nm = getActData.getSub_source_nm();
	meet_item_cd = getActData.getMeet_item_cd();
	meet_item_nm = getActData.getMeet_item_nm();
	meet_source_cd = getActData.getMeet_source_cd();
	
	map_id = getActData.getMap_id();
	item_desc = getActData.getItem_desc();
	act_desc = getActData.getAct_desc();
	act_type = getActData.getAct_type();
	cat = getActData.getCat();
	priority = getActData.getPriority();
	assign_dt = getActData.getAssign_dt();
	target_dt = getActData.getTarget_dt();
	act_status = getActData.getAct_status();
	act_complete_stmt = getActData.getAct_complete_stmt();
	map_assign_to = getActData.getMap_assign_to();
	act_assign_nm = getActData.getAct_assign_nm();
	act_assign_to_nm = getActData.getAct_assign_to_nm();
	
	act_unit_cd = getActData.getAct_unit_cd();
    act_item_cd = getActData.getAct_item_cd();
    act_unit_nm = getActData.getAct_unit_nm();
    act_item_nm = getActData.getAct_item_nm();
    act_assign_dt = getActData.getAct_assign_dt();
    act_tilte = getActData.getAct_title();	
    act_seq_no = getActData.getAct_seq_no();
    act_meet_id = getActData.getAct_meet_id();
    act_source_nm = getActData.getAct_source_nm();
	act_source_cd = getActData.getAct_source_cd();
	act_meet_seq_no = getActData.getAct_meet_seq_no();
	Vtaken_sts = getActData.getVTaken_sts();
    
	get_unit_cd = getActData.getGet_unit_cd();
	get_mapping_id = getActData.getGet_mapping_id();
	get_seq_no = getActData.getGet_seq_no();
	get_act_typ = getActData.getGet_act_typ();
	get_priority = getActData.getGet_priority();
	get_assign_by = getActData.getGet_assign_by();
	get_assign_dt = getActData.getGet_assign_dt();
	get_target_dt = getActData.getGet_target_dt() ;
	get_status = getActData.getGet_status();
	get_act_desc = getActData.getGet_act_desc();
	get_meet_id = getActData.getGet_meet_id();
	get_title = getActData.getGet_title();
	get_hse_risk = getActData.getGet_hse_risk();
	get_re_risk = getActData.getGet_re_risk();
	get_unit_nm = getActData.getGet_unit_nm();
	get_assign_nm = getActData.getGet_assign_nm();
	get_item_cd = getActData.getGet_item_cd();
	get_item_nm = getActData.getGet_item_nm();
	get_comp_dt = getActData.getGet_comp_dt();
	get_attach = getActData.getGet_attach();
	get_source_nm = getActData.getGet_source_nm();
	get_source_cd = getActData.getGet_source_cd();
	get_forward = getActData.getGet_forward();
	get_frwd_nm = getActData.getGet_frwd_nm();
	form_id = getActData.getForm_id();
	module_cd = getActData.getModule_cd();
	Vcnt1 = getActData.getVcnt();
	rec_form_id = getActData.getRec_form_id();
	rec_module_cd = getActData.getRec_module_cd();
	get_rasn_from = getActData.getGet_rasn();
	get_rasn_to  = getActData.getGet_rasn_to();
	re_ass_cnt = getActData.getRe_ass_cnt();
	get_flag= getActData.getGet_flag();
	act_typSrc_nm = getActData.getAct_typSrc_nm();
	remDay_menu = getActData.getRemCnt();
	dueDay_menu=getActData.getOverDueCnt();
	
	myRemDay = getActData.getMyRemCnt();
	myDueDay =getActData.getMyOverDueCnt();
}
// System.out.println(temp_modCd+"****");
%>
<body>

<section id="home-wrapper" >

<div class="container-fluid">


<div class="row">
<div class="col-md-12">
<!-- Custom Tabs -->    
<div class="nav-tabs-custom" >

<ul class="nav nav-tabs">

<%for(int i = 0; i < mmodule_nm.size(); i++){ %>
	<li <%if(i == Integer.parseInt(flag1) || temp_modCd.equals(modCd.elementAt(i))){%> class="active" <%} %>>
	<a style="text-transform: none;" href="" data-toggle="tab" onclick="setTab('<%=i%>','<%=modUrl.elementAt(i)%>','<%=modCd.elementAt(i)%>');"> <%=mmodule_nm.elementAt(i) %> </a></li>
<%} %>
</ul>

<%
// System.out.println("temp_modCd****"+temp_modCd);
if(flag1.equals("999999999") && temp_modCd.equals("") || temp_modCd.equalsIgnoreCase("null")){ %>
	 <h2 style="text-align: center;" style="background-color: #fffff;">DASHBOARD</h2>
	 
	 <div class="container" >
		<div class="well well-lg" >
			<div class="box-header with-border  text-left" style="height: 10px;">
				<h3 class="box-title lowercase-font font-18 weight-600">Meeting(s)</h3>
			</div>
		<%if(meet_id_menu.size() > 0){ %>	
			<div class="box-body" >
				<div class="box-body table-responsive no-padding">
					<table class="table table-hover table-bordered font-14" >
					<thead>
						<tr class="tr-heading">
							<th  class="text-center" >Meeting Id</th>
							<th  class="text-center">Meeting Date</th>
							<th  class="text-center">Meeting Title</th>
							<th  class="text-center">Dept.</th>
							<th  class="text-center">Source</th>
							<th  class="text-center">Sub Type</th>
<!-- 							<th  class="text-center">Status</th> -->
<!-- 							<th class="text-center" >Action</th> -->
						</tr>
					</thead>
					<tbody>
						<%for ( int i = 0; i < meet_id_menu.size() ; i ++){
							String sts ="";
							if(meet_status.elementAt(i).equals("D") ){
								sts = "Open";
							}
// 							System.out.println("***"+meet_source_nm.elementAt(i));
							%>
							<tr class='tr-content' style="background-color: white;">
								<td class="text-center"><u><a href="javascript: showDraft('<%=meet_id_menu.elementAt(i)%>','<%=meet_date.elementAt(i)%>','<%=meet_nm.elementAt(i)%>',
								'<%=meet_unit_cd.elementAt(i)%>','<%=meet_mom.elementAt(i)%>','<%=meet_seq_no.elementAt(i)%>','<%=meet_map_id.elementAt(i)%>',
								'<%=unitNm.elementAt(i) %>','<%=meet_item_cd.elementAt(i)%>','<%=meet_item_nm.elementAt(i)%>','<%=meet_source_cd.elementAt(i)%>','<%=meet_source_nm.elementAt(i)%>'
								 ,'<%=module_cd%>','<%=form_id%>','<%=flag1%>');">
								 <%=meet_id_menu.elementAt(i) %></a></u></td>
								<td class="text-center"><%=meet_date.elementAt(i) %></td>
								<td align="left"><%=meet_nm.elementAt(i) %></td>
								<td><%=unitNm.elementAt(i) %></td>
								<td ><%=meet_item_nm.elementAt(i) %></td> 
								<td><%=meet_source_nm.elementAt(i) %></td>
<%-- 								<td ><button type="button" onclick="showAccordian('tr<%=i %>')" class="w3-btn w3-block w3-grey w3-left-align"><font color="white;">+</font></button></td> --%>
							</tr>
							
						<%} %>
					</tbody>
				</table>
			</div>
		</div>
		<%}else{%>
			<div class="box-body" >
				<div class="box-body table-responsive no-padding">
					<table class="table table-hover table-bordered font-14" >
						<thead>
							<tr class="tr-alert">
								<th  class="text-center" >No Meeting(s) Details Available!</th>
							</tr>
						</thead>
					</table>
					</div>
				</div>
		<%} %>
		</div>	
	</div>
<form action="">
	
	
<!-- <div class="container" align="center"  id="contDiv">  -->
<!-- 		<div class="well well-lg" > -->
		<div class="box-header with-border  text-left" style="height: 10px;">
			<h3 class="box-title lowercase-font font-18 weight-600" id="head">Action(s)</h3>
		</div> 
		<%if(map_id.size() > 0 || get_mapping_id.size() > 0 ){ %>
		
		<div class="box-body" >
			<div class="box-body table-responsive no-padding">
				<table class="table table-hover table-bordered font-14" >
				<thead>
					<tr class="tr-heading">
						<th class="text-center" >Action Id</th>
						<th class="text-center" >Meeting Id</th>
						<th class="text-center">Dept.</th>
						<th class="text-center">Source</th>
						<th class="text-center">Sub Type</th>
						<th  class="text-center">Assign Dt.</th>
						<th  class="text-center">Title</th>
						<th  class="text-center">Action Required</th>
						<th  class="text-center">Target Dt.</th>
						<th  class="text-center">Overdue/<br>Remain<br> Day(s)</th>
						<th  class="text-center" >Priority</th>
						<th  class="text-left" colspan="2" >Status</th>
<!-- 						<th></th> -->
					</tr>
					<tr>
						<td colspan="13">
							<button type="button" onclick="hideshow('myActItm','arrow1');" id="bnt1" class="w3-btn w3-block w3-grey w3-left-align"><font color="white;">My Action Item <font color="green"> (<%=get_mapping_id.size() %>)</font></font>
								<div class="right-arrow pull-right glyphicon glyphicon-plus" id="arrow1"></div>
							</button>
						</td>
					</tr>
				</thead>
				
				<tbody  style="display: none;background-color: white;font-size: 13px;" id="myActItm" >
			<%int k=0;int p=0; %>
			 <%for( int i = 0 ; i< get_mapping_id.size(); i++){ 
// 				System.out.println("***get_mapping_id**"+get_mapping_id.elementAt(i));
				if(get_forward.elementAt(i).equals("Y")){ %>
				<tr class='tr-content' title="Assigned By : <%=get_assign_nm.elementAt(i)%>" >
				<td title="Forwarded Action">
					<%=get_mapping_id.elementAt(i) %>-<%=get_seq_no.elementAt(i) %>
				</td>
				<%}else{	
					if(!re_ass_cnt.elementAt(i).toString().trim().equals("0")){%>
					<tr class='tr-content' title="Assigned By : <%=get_assign_nm.elementAt(i)%>" >
					<td title="Re-Assigned Action">
						<%=get_mapping_id.elementAt(i) %>-<%=get_seq_no.elementAt(i) %>
					</td>
					<%}
				else{ %>
				<tr class='tr-content' title="Assigned By : <%=get_assign_nm.elementAt(i)%>" style="background-color: #d5ece8;">
				<td title="Click here to Take Action">
					<u><a href="javascript: recieveAct('<%=get_mapping_id.elementAt(i) %>','<%=get_seq_no.elementAt(i)%>',
					'<%=get_unit_cd.elementAt(i)%>','<%=get_unit_nm.elementAt(i)%>','<%=get_item_cd.elementAt(i)%>',
					'<%=get_item_nm.elementAt(i)%>','<%=get_act_desc.elementAt(i)%>','<%=get_meet_id.elementAt(i)%>',
					'<%=get_source_nm.elementAt(i)%>','<%=get_status.elementAt(i) %>','<%=get_assign_by.elementAt(i)%>');"><%=get_mapping_id.elementAt(i) %>-<%=get_seq_no.elementAt(i) %></a></u>
				</td>
				<%} }%>
					
				
					<td><%=get_meet_id.elementAt(i) %></td>
					<td><%=get_unit_nm.elementAt(i) %></td>
					<td><%=get_item_nm.elementAt(i)%></td>
					<td><%=get_source_nm.elementAt(i)%></td>
					<td><%=get_assign_dt.elementAt(i) %></td>
					<td align="left"><%=get_title.elementAt(i) %></td>
					<td align="left"><%=get_act_desc.elementAt(i) %></td>
					<td><%=get_target_dt.elementAt(i) %></td>
					<td>
						<%if(!get_status.elementAt(i).equals("Close")){ %>
								<%if(remDay_menu.elementAt(i).equals("0")){%>
									<%if(dueDay_menu.elementAt(i).equals("0")){ %>
										<span class="badge" style="background-color: green;"><%=remDay_menu.elementAt(i) %></span>
									<%}else{ %>
										<span class="badge" style="background-color: red;color: lime;"><%=dueDay_menu.elementAt(i) %></span>
										<%} %>		
								<%}else{%>
									<span class="badge" style="background-color: green;"><%=remDay_menu.elementAt(i) %></span>
								<%} %>
							<%}else{ %>
								<span class="label label-success">0</span> 
							<%} %>
					
					</td>
					<td><%=get_priority.elementAt(i) %></td>
					
					<%if(get_forward.elementAt(i).equals("Y")){ %>
					<td colspan=""><font color="red" >forwarded to </font>
					<%for (int j = 0 ; j < Integer.parseInt(Vcnt1.elementAt(i)+"") ; j++) {%> 
						<font color="blue" title="Forwarded to : "> <%=get_frwd_nm.elementAt(k)%><br></font>
					<%k++;} %>	
					</td>	
					<%}else{ %>
					
					<%if(!re_ass_cnt.elementAt(i).toString().trim().equals("0")){
// 						System.out.println("re_ass_cnt.elementAt(i)***"+re_ass_cnt.elementAt(i));
						%>
						
					<td colspan="2"><font color="red" ><%
					if(get_flag.elementAt(i).equals("Y")){ %>Re Assigned to </font>
					<%for (int j = 0 ; j < Integer.parseInt(re_ass_cnt.elementAt(i)+"") ; j++) {
					//	System.out.println("re_ass_cnt.elementAt(i)***"+p+"***"+j+"***"+get_rasn_to.elementAt(p)+"--re_ass_cnt.elementAt(i)--"+re_ass_cnt.elementAt(i));
					%> 
						<font color="blue" title="Re Assigned to : "> <%=get_rasn_to.elementAt(p)%><br></font>
					<%p++;} %>	
					<%} %>
					</td>	
					<%}
					else{ %>
					<td><%=get_status.elementAt(i) %></td>
					<td>
<!-- 					<i class="fa fa-forward" ></i> -->
						<img alt="" src="../images/forward.png" title="Click here to forward this Action.." onclick="forward('<%=get_mapping_id.elementAt(i)%>','<%=get_seq_no.elementAt(i)%>','<%=ats_user_cd%>','<%=alw_forward%>','<%=get_unit_cd.elementAt(i)%>','<%=get_assign_by.elementAt(i)%>');">
					</td>	
					<%} }%>	
				</tr>					
			<%} %>
			<%if(get_mapping_id.size() == 0){%>
				<tr class="tr-alert">
					<th  class="text-center" colspan="13">You have no any Action(s) Item!</th>
				</tr>
			<%} %>
			</tbody>
			<%if(!msg1.equals("")){ %>	
							<tr>
								<td style="color: blue" colspan="13" class="text-center"><%=msg1 %> to <%=assign_nm %></td>
							</tr>
						<%} %>
						<%if(!error_msg1.equals("")){ %>	
							<tr>
								<td style="color: red" colspan="13" class="text-center"><%=error_msg1 %></td>
							</tr>
						<%} %>	
			<tr>
				<td colspan="13">
					<button type="button" onclick="hideshow('myEntry','arrow2');" id="bnt2" class="w3-btn w3-block w3-grey w3-left-align"><font color="white;">My Entry <font color="green"> (<%=map_id.size() %>)</font></font>
						<div class="right-arrow pull-right glyphicon glyphicon-plus" id="arrow2"></div>
					</button>
				</td>
				</tr>
				<tbody  style="display: none;background-color: white;font-size: 13px;" id="myEntry"  >
				<%for(int i=0;i<map_id.size();i++){ %>
					<%if(act_meet_id.elementAt(i).equals("") || act_meet_id.elementAt(i).equals("N.A.")){ %>
				<tr class='tr-content' style="background-color: #f5f4ce;" title="Independent Action..">	
					<td title="Click here to show Action">
						<u><a href="javascript: showIndAct('<%=map_id.elementAt(i) %>','<%=act_seq_no.elementAt(i)%>',
						'<%=act_unit_cd.elementAt(i)%>','<%=act_unit_nm.elementAt(i)%>','<%=act_item_nm.elementAt(i)%>',
						'<%=act_item_cd.elementAt(i)%>','<%=act_source_nm.elementAt(i)%>','<%=act_source_cd.elementAt(i)%>',
						'<%=act_meet_id.elementAt(i) %>','<%=act_meet_seq_no.elementAt(i)%>','<%=module_cd%>','<%=form_id%>','<%=flag1%>','<%=act_typSrc_nm.elementAt(i)%>');"><%=map_id.elementAt(i) %>-<%=act_seq_no.elementAt(i)%></a></u>
 						</td>
 						<td>N.A.</td>
 						<td><%=act_unit_nm.elementAt(i)%></td>
 						<td><%=act_item_nm.elementAt(i)%></td>
 						<td><%=act_source_nm.elementAt(i)%></td>
 						<td><%=assign_dt.elementAt(i) %></td>
 						<td align="left"><%=act_tilte.elementAt(i) %></td>
 						<td align="left"><%=act_desc.elementAt(i) %></td>
					<td><%=target_dt.elementAt(i) %></td>
					<td>
						<%if(!Vtaken_sts.elementAt(i).equals("Close")){ %>
								<%if(myRemDay.elementAt(i).equals("0")){%>
									<%if(myDueDay.elementAt(i).equals("0")){ %>
										<span class="badge" style="background-color: green;"><%=myRemDay.elementAt(i) %></span>
									<%}else{ %>
										<span class="badge" style="background-color: red;color: lime;"><%=myDueDay.elementAt(i) %></span>
										<%} %>		
								<%}else{%>
									<span class="badge" style="background-color: green;"><%=myRemDay.elementAt(i) %></span>
								<%} %>
							<%}else{ %>
								<span class="label label-success">0</span> 
							<%} %>
					</td>
					<td><%=priority.elementAt(i) %></td>
					<td colspan="" title="Click here to check action taken status"> <u><a href="javascript: showActTaken('<%=act_tilte.elementAt(i) %>','<%=map_id.elementAt(i) %>','<%=act_unit_cd.elementAt(i) %>','<%=act_seq_no.elementAt(i) %>','<%=ats_user_cd%>');">
						<%=Vtaken_sts.elementAt(i) %>
						</a></u>
					</td>
				</tr>
			<%}}for(int i=0;i<map_id.size();i++){ //for meeting's action%> 
			
				<%if(!act_meet_id.elementAt(i).equals("") && !act_meet_id.elementAt(i).equals("N.A.")){ %>
				<tr class='tr-content' style="background-color: white;" title="Meeting's Action..">
					<td title="Click here to show Action">
					
						<u><a href="javascript: showIndAct('<%=map_id.elementAt(i) %>','<%=act_seq_no.elementAt(i)%>',
						'<%=act_unit_cd.elementAt(i)%>','<%=act_unit_nm.elementAt(i)%>','<%=act_item_nm.elementAt(i)%>',
						'<%=act_item_cd.elementAt(i)%>','<%=act_source_nm.elementAt(i)%>','<%=act_source_cd.elementAt(i)%>',
						'<%=act_meet_id.elementAt(i) %>','<%=act_meet_seq_no.elementAt(i)%>','<%=module_cd%>','<%=form_id%>','<%=flag1%>','<%=act_typSrc_nm.elementAt(i)%>');"><%=map_id.elementAt(i) %>-<%=act_seq_no.elementAt(i)%></a></u>
 						</td>						
					<td><%=act_meet_id.elementAt(i) %></td>
					<td><%=act_unit_nm.elementAt(i)%></td>
					<td><%=act_item_nm.elementAt(i)%></td>
					<td><%=act_source_nm.elementAt(i)%></td>
					<td><%=assign_dt.elementAt(i) %></td>
					<td align="left"><%=act_tilte.elementAt(i) %></td>
					<td align="left"><%=act_desc.elementAt(i) %></td>
					<td><%=target_dt.elementAt(i) %></td>
					<td>
						<%if(!Vtaken_sts.elementAt(i).equals("Close")){ %>
								<%if(myRemDay.elementAt(i).equals("0")){%>
									<%if(myDueDay.elementAt(i).equals("0")){ %>
										<span class="badge" style="background-color: green;"><%=myRemDay.elementAt(i) %></span>
									<%}else{ %>
										<span class="badge" style="background-color: red;color: lime;"><%=myDueDay.elementAt(i) %></span>
										<%} %>		
								<%}else{%>
									<span class="badge" style="background-color: green;"><%=myRemDay.elementAt(i) %></span>
								<%} %>
							<%}else{ %>
								<span class="label label-success">0</span> 
							<%} %>
					</td>
					<td><%=priority.elementAt(i) %></td>
					<td colspan="" title="Click here to check action taken status"> <u>
					<a href="javascript: showActTaken('<%=act_tilte.elementAt(i) %>','<%=map_id.elementAt(i) %>','<%=act_unit_cd.elementAt(i) %>','<%=act_seq_no.elementAt(i) %>','<%=ats_user_cd%>');">
						<%=act_status.elementAt(i) %>
						</a></u>
					</td>
				</tr>
			<%} %>
		<%}%>
			<%if(map_id.size() == 0){ %>
				<tr class="tr-alert">
					<th  class="text-center" colspan="13" align="center">You have not done any Action(s) Entry!</th>
				</tr>
			<%} %>	
			</tbody>
			
			</table>
			</div>
			</div>
			<%}else{ %>
				<div class="box-body" >
				<div class="box-body table-responsive no-padding">
					<table class="table table-hover table-bordered font-14" >
						<thead>
							<tr class="tr-alert">
								<th  class="text-center" >No Action(s) Details Available!</th>
							</tr>
						</thead>
					</table>
					</div>
				</div>
			<%} %>
<!-- 			</div> -->
<!-- 			</div> -->
	<input type="hidden" name="form_id" value="<%=rec_form_id%>">
    <input type="hidden" name="module_cd" value="<%=rec_module_cd%>">
    <input type="hidden" name="head_tab" value="999">
    
</form>
<%} %>
<!-- </div>
</div>
</div>
</section> -->

<!-- /.tab-content -->
<!-- </div>
nav-tabs-custom
</div>

/.col

</div>
/.row


</div>
/.container-fluid

</section> -->
<!-- /#page-wrapper -->
<!-- <!-- jQuery -->
<script src="../responsive/js/jquery.min.js"></script>

<script src="../js/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css"> -->

<script >
$(function () {
$('#datetimepicker1').datepicker({
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
todayHighlight: true
});
});

$(function () {
$('#datetimepicker2').datepicker({
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
todayHighlight: true
});
});
    
    
$(function () {
	$('#datetimepicker3').datepicker({
	format: "dd/mm/yyyy",
	language: "en",
	autoclose: true,
	todayHighlight: true
});
});
    
    
    $(function () {
$('#datetimepicker4').datepicker({
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
todayHighlight: true
});
});
    
 
</script>

</body>

</html>

