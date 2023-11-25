<%@ include file="../sess/Expire.jsp" %>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.HashMap"%>
<!DOCTYPE html>
<html lang="en">

<head>

<script src="../js/admin.js"></script>
<link rel="stylesheet" href="../css/tlu.css">
<style type="text/css">
.s-red{
color: red;
font-size: 18px;
}
.part1{
	background-color: #e9e8ea;
	
} 
</style>
<script>
function setChk(id){
	var nmId="hid_"+id;
	if(document.getElementById(id).checked){
		document.getElementById(nmId).value = 'Y';
	}else{
		document.getElementById(nmId).value = 'N';
	} 
}

function doSubmit (){
	
	var radFlg = document.forms[0].radFlg.value;
	var chkFlg = false;
	if(radFlg == 'cust' || radFlg == 'trans'){
		var len = document.forms[0].rad1.length;
// 		alert(len)
		if(parseFloat(len) > 0){
			for(var i = 0 ; i < parseFloat(len) ; i++ ){
				if(document.forms[0].rad1[i].checked){
					chkFlg = true;			
				}
			}
		}else{
			
			if(document.forms[0].rad1.checked){
				chkFlg = true;			
			}
		}
		
	}else{
		if(document.getElementById('emp_cd').value!=""){
			 if(!$('#email_setup input[type="checkbox"]').is(':checked')){
	// 		      alert("");
			      
			    }
			 chkFlg = true;
		}else{
			chkFlg = false;
		}
	}
	if(chkFlg){
	   	var alrt = "";
	   	if(document.getElementById('subBtn').value == "mod"  ){
	   		alrt = "Are you sure want to Modify ?";
	   	}else{
	   		alrt = "Are you sure want to save ?";
	   	}
	   	var a = confirm(alrt)
	   	if(a){
	   		document.forms[0].submit();	
	   	}
	}else{
		alert('Make sure you have done proper selection')
	}	
}

function fetchDtl(emp_cd){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var radFlg = document.forms[0].radFlg.value;
	var plant_cd = "";
	if(radFlg=='cust'){
		plant_cd = document.forms[0].plant_cd.value;
	}
	if(emp_cd!=""){
		location.replace("../admin/frm_mst_administrator.jsp?radFlg="+radFlg+"&emp_cd="+emp_cd+"&cust_cd="+emp_cd+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&plant_cd="+plant_cd);
	}
}
function setOption(radFlg){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	if(radFlg!=""){
		location.replace("../admin/frm_mst_administrator.jsp?radFlg="+radFlg+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl);
	}
}
function setPlant_cd(plant_cd,cust_cd){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var radFlg = document.forms[0].radFlg.value;
	
	
	if(radFlg!=""){
		location.replace("../admin/frm_mst_administrator.jsp?radFlg="+radFlg+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&cust_cd="+cust_cd+"&plant_cd="+plant_cd);
	}
}

function setCC(len,ind){
// 	alert(parseFloat(len))
	if(parseFloat(len) > 1 ){
		
		for(var i = 0 ; i < parseFloat(len) ; i++ ){
			if(i!=ind){
				
				document.getElementById('chk'+i).checked = true;
				document.getElementById('toFlg'+i).value = "N";
				document.getElementById('ccFlg'+i).value = "Y";
			}else{
				
				document.getElementById('chk'+i).checked = false;
				document.getElementById('toFlg'+i).value = "Y";
				document.getElementById('ccFlg'+i).value = "N";
			}
		}
	}else{
		
		document.getElementById('chk'+ind).checked = false;
		document.getElementById('toFlg'+ind).value = "Y";
		document.getElementById('ccFlg'+ind).value = "N";
	}
}

function setTransDtl(){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var radFlg = document.forms[0].radFlg.value;
	var trans_cd = document.getElementById('trans_cd').value;
	var addrs = document.getElementById('addrs').value;
	if(radFlg!=""){
		location.replace("../admin/frm_mst_administrator.jsp?radFlg="+radFlg+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&trans_cd="+trans_cd+"&addrs="+addrs);
	}
}
</script>

<jsp:useBean class="com.seipl.hazira.dlng.master.DataBean_Email_Setup" id="email_setup" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="page"/>
<%
	String user_cd=(String)session.getAttribute("user_cd");
  	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
  	String error_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
  	String emp_cd = request.getParameter("emp_cd")==null?"":request.getParameter("emp_cd");
  	String radFlg = request.getParameter("radFlg")==null?"sup":request.getParameter("radFlg");
  	String cust_cd = request.getParameter("cust_cd")==null?"":request.getParameter("cust_cd");
  	String plant_cd = request.getParameter("plant_cd")==null?"":request.getParameter("plant_cd");
  	
  	String write_permission = (String)session.getAttribute("write_permission") ==null?"N":session.getAttribute("write_permission").toString();
	String delete_permission = (String)session.getAttribute("delete_permission")==null?"N":session.getAttribute("delete_permission").toString();
	String print_permission = (String)session.getAttribute("print_permission") ==null?"N":session.getAttribute("print_permission").toString();
	String approve_permission = (String)session.getAttribute("approve_permission") ==null?"N":session.getAttribute("approve_permission").toString();
	String audit_permission = (String)session.getAttribute("audit_permission") ==null?"N":session.getAttribute("audit_permission").toString();
	String update_permission = (String)session.getAttribute("update_permission") ==null?"N":session.getAttribute("update_permission").toString();
	
	String trans_cd = request.getParameter("trans_cd")==null?"":request.getParameter("trans_cd");
	String addrs = request.getParameter("addrs")==null?"":request.getParameter("addrs");
// 	System.out.println("write_permission----"+write_permission);
  	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200421
	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200421
	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200421
	
	email_setup.setCallFlag("fetchUserDtl");
	email_setup.setEmp_cd(emp_cd);
	email_setup.setRadFlg(radFlg);
	email_setup.setCust_cd(cust_cd);
	email_setup.setPlant_cd(plant_cd);
	email_setup.setStrans_cd(trans_cd);
	email_setup.setAddrs(addrs);
	
	email_setup.init();
	
	Vector Vemp_nm = email_setup.getVemp_nm();
	Vector Vemp_cd = email_setup.getVemp_cd();
	
	String sup_email = email_setup.getEmail_id();
	String form402_bcc = email_setup.getForm402_bcc();
	String form402_cc = email_setup.getForm402_cc();
	String form402_to = email_setup.getForm402_to();
	String d_inv_bcc = email_setup.getD_inv_bcc();
	String d_inv_cc = email_setup.getD_inv_cc();

	
	String o_inv_bcc = email_setup.getO_inv_bcc();
	String o_inv_cc = email_setup.getO_inv_cc();
	
	String t_inv_bcc = email_setup.getT_inv_bcc();
	String t_inv_cc = email_setup.getT_inv_cc();
	String t_inv_to = email_setup.getT_inv_to();
	
	String avail_flg = email_setup.getAvail_flg();
	
	Vector Vd_inv_bcc =  email_setup.getVd_inv_bcc();
	Vector Vd_inv_cc =  email_setup.getVd_inv_cc();
	
	Vector Vform402_bcc =  email_setup.getVform402_bcc();
	Vector Vform402_cc =  email_setup.getVform402_cc();
	Vector Vform402_to =  email_setup.getVform402_to();
	Vector Vo_inv_bcc =  email_setup.getVo_inv_bcc();
	Vector Vo_inv_cc =  email_setup.getVo_inv_cc();
	
	Vector Vt_inv_to = email_setup.getVt_inv_to();
	Vector Vt_inv_bcc =  email_setup.getVt_inv_bcc();
	Vector Vt_inv_cc = email_setup.getVt_inv_cc();
	Vector VContact_pers = email_setup.getVContact_pers();
	/* for customer */
	Vector Vcust_cd = email_setup.getVcust_cd();
	Vector Vcust_nm = email_setup.getVcust_nm();
	
// 	Vector Vplant_seq_no = email_setup.getVplant_seq_no();
	Vector Vplant_type = email_setup.getVplant_type();
	Vector Vplant_name = email_setup.getVplant_name();	
	
	Vector Vseq_no = email_setup.getVseq_no();
	Vector Vcontact_person = email_setup.getVcontact_person();
	Vector Vemail =	email_setup.getVemail();
	
	Vector def_inv_to_flag = email_setup.getDef_inv_to_flag();
	Vector def_inv_cc_flag = email_setup.getDef_inv_cc_flag();
	int to_cnt = email_setup.getTo_cnt();
	
	Vector Vavail_seq_no = email_setup.getVavail_seq_no();
	Vector Vavail_cont_pers = email_setup.getVavail_cont_pers();
	Vector Vavail_plant_nm = email_setup.getVavail_plant_nm();
	
	Vector Vtrans_cd = email_setup.getTrans_cd();
	Vector Vtrans_nm = email_setup.getTrans_nm();
	Vector Vactive_flg = email_setup.getVactive_flg();
	
	trans_cd = email_setup.getStrans_cd();
	addrs = email_setup.getAddrs();
	
%>
<body>
<div class="tab-content">
<div class="tab-pane active" id="add_new_action">
<div class="box mb-0">
<!-- <div class="box-body"> -->

<!--  <div class="box-header with-border main-header"> -->
<!-- 		<div class="row "> -->
		<form class="form-horizontal"  action="../servlet/Frm_Email_Setup" method="post" id="email_setup">
					<input type="hidden" name="option" value="insertEmailSetup">
					<input type="hidden" name="user_cd" value="<%=user_cd%>">
					<input type="hidden" name="modCd" value="<%=modCd%>">
					<input type="hidden" name="formId" value="<%=formId%>">
					<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
					<input type="hidden" name="avail_flg" value="<%=avail_flg%>">
					<input type="hidden" name="radFlg" value = <%=radFlg%>>
					
					<div class="box-header with-border main-header">
						<div class="form-group row ">
							<label for="inputName" class="col-sm-2 col-form-label-sm"> <input type="radio" name="rad" <%if(radFlg.equalsIgnoreCase("sup")){ %>checked="checked"<%} %> onchange="setOption('sup');">&nbsp;Supplier </label>
			                <label for="inputName" class="col-sm-2 col-form-label-sm"> <input type="radio" name="rad" <%if(radFlg.equalsIgnoreCase("cust")){ %> checked="checked" <%} %> onchange="setOption('cust');">&nbsp;Customer</label>
      			            <label for="inputName" class="col-sm-2 col-form-label-sm"> <input type="radio" name="rad" <%if(radFlg.equalsIgnoreCase("trans")){ %> checked="checked" <%} %> onchange="setOption('trans');">&nbsp;Transporter</label>
      	 		         </div>
						
					<%if(radFlg.equalsIgnoreCase("sup")){ %>	
						<div class="form-group row ">
				            <label for="inputName" class="col-sm-2 col-form-label-sm"> User Name : </label>
				            <div class="col-sm-2">
									<select class="form-control form-control-sm" id="emp_cd" name="emp_cd" onchange="fetchDtl(this.value);">
									<option value="" >-Select-</option>
									<%for(int i = 0 ; i < Vemp_cd.size(); i++) {%>
										<option value="<%=Vemp_cd.elementAt(i)%>"><%=Vemp_nm.elementAt(i) %></option>
									<%} %>
			                 	</select>
			                 	<%if(!emp_cd.equalsIgnoreCase("")) {%>
			                 	<script type="text/javascript">
			                 		document.getElementById('emp_cd').value='<%=emp_cd%>'
			                 	</script>
			                 	<%} %>
			                </div>
			                
			                <label for="" class="col-sm-2 col-form-label text-right">Email ID:</label>
	                    	 <div class="col-sm-3">
	                     		<input type="text" class="form-control form-control-sm" name="email_id" value="<%=sup_email%>" readonly="readonly">
			            	</div> 
					</div>	
						               
					<div class="box-body table-responsive no-padding">
					<table class="table  table-bordered">
							<%if(!msg.equals("")){ %>	
								<tr>
									<td class="text-center" style="color: blue;font-weight: bold;font-size: 15px;" colspan="12"><%=msg %></td>
								</tr>
							<%} %>
							<%if(!error_msg.equals("")){ %>	
								<tr>
									<td class="text-center" style="color: red;font-weight: bold;font-size: 15px;" colspan="12"><%=error_msg %></td>
								</tr>
							<%} %>
							
							<tr style="font-size: 16px;" >
								<th class="info"></th>
								<th colspan="3" class="text-center part1">Invoice</th>
								<th colspan="3" class="text-center success">Form 402</th>
							</tr>
							
							<tr >
								<th class="text-center info">COPY</th>
								<th colspan="1" class="text-center part1">TO</th>
								<th colspan="1" class="text-center part1">CC</th>
								<th colspan="1" class="text-center part1">BCC</th>
								<th colspan="1" class="text-center success">TO</th>
								<th colspan="1" class="text-center success">CC</th>
								<th colspan="1" class="text-center success">BCC</th>
							</tr>
							
							<tr >
								<th colspan="1" class="info text-center">Original</th>
								<td class="text-center part1">  </td>
								<td class="text-center part1"> <input type="checkbox" name="o_inv_cc" id="o_inv_cc" onclick="setChk(this.id);" > <input type="hidden" name="hid_o_inv_cc" id="hid_o_inv_cc" value="N"> </td>
								<td class="text-center part1"><input type="checkbox" name="o_inv_bcc" id="o_inv_bcc" onclick="setChk(this.id);"> <input type="hidden" name="hid_o_inv_bcc" id="hid_o_inv_bcc" value="N"></td>
								<td rowspan="3" class="text-center success" style="vertical-align: middle;"><input type="checkbox" name="form_to" id="form_to" onclick="setChk(this.id);"> <input type="hidden" name="hid_form_to" id="hid_form_to" value="N"></td>
								<td rowspan="3" class="text-center success" style="vertical-align: middle;"><input type="checkbox" name="form_cc" id="form_cc" onclick="setChk(this.id);"> <input type="hidden" name="hid_form_cc" id="hid_form_cc" value="N"></td>
								<td rowspan="3" class="text-center success" style="vertical-align: middle;"><input type="checkbox" name="form_bcc" id="form_bcc" onclick="setChk(this.id);"> <input type="hidden" name="hid_form_bcc" id="hid_form_bcc"  value="N"></td>
							</tr>
							
							<tr >
								<th colspan="1" class="info text-center">Duplicate</th>
								<td class="text-center part1"></td>
								<td class="text-center part1"> <input type="checkbox" name="d_inv_cc" id="d_inv_cc" onclick="setChk(this.id);"> <input type="hidden" name="hid_d_inv_cc"  id="hid_d_inv_cc" value="N"> </td>
								<td class="text-center part1"><input type="checkbox" name="d_inv_bcc" id="d_inv_bcc" onclick="setChk(this.id);"> <input type="hidden" name="hid_d_inv_bcc" id="hid_d_inv_bcc" value="N"></td>
							</tr>
							
							<tr >
								<th colspan="1" class="info text-center">Triplicate</th>
								<td class="text-center part1"> <input type="checkbox" name="t_inv_to" id="t_inv_to" onclick="setChk(this.id);"> <input type="hidden" name="hid_t_inv_to"  id="hid_t_inv_to" value="N"> </td>
								<td class="text-center part1"> <input type="checkbox" name="t_inv_cc" id="t_inv_cc" onclick="setChk(this.id);"> <input type="hidden" name="hid_t_inv_cc" id="hid_t_inv_cc" value="N"> </td>
								<td class="text-center part1"><input type="checkbox" name="t_inv_bcc" id="t_inv_bcc" onclick="setChk(this.id);"> <input type="hidden" name="hid_t_inv_bcc"  id="hid_t_inv_bcc" value="N"></td>
							</tr>
							
							<tr>
							<%if(avail_flg.equalsIgnoreCase("Y")) {%>
								<th colspan="12" class="text-right"><button type="button"  class="btn btn-warning" onclick="doSubmit();" id="subBtn" value="mod" 
								<%if(!write_permission.equalsIgnoreCase("Y")){ %>
								disabled="disabled"
								<%} %>
								>Modify </button></th>
							<%}else{ %>
								<th colspan="12" class="text-right"><button type="button"  class="btn btn-success" onclick="doSubmit();" id="subBtn" value="sub" 
								<%if(!write_permission.equalsIgnoreCase("Y")){ %>
								disabled="disabled"
								<%} %>
								>Submit </button></th>
							<%} %>
							</tr>
							</table>
							<table class="table  table-bordered">
							<tr class="info">
								<th colspan="17" class = "text-center" style="font-size: 15px;">Already Configured Contact Person(s)</th>
							</tr>
							<tr >
								<th colspan="1" rowspan="3" class="text-center info" style="vertical-align: middle; ">Contact Person</th>
								<th colspan="7" rowspan="1" class="text-center part1" >Invoice</th>
								<th colspan="9" rowspan="1" class="text-center success">Form 402</th>
							</tr>
							<tr >
								<th colspan="1" rowspan="1" class="text-center part1">TO</th>
								<th colspan="3" rowspan="1" class="text-center part1">CC</th>
								<th colspan="3" rowspan="1" class="text-center part1">BCC</th>
								
								<th colspan="1" rowspan="2" style="vertical-align: middle;" class="text-center success">TO</th>
								<th colspan="1" rowspan="2" style="vertical-align: middle;" class="text-center success">CC</th>
								<th colspan="1" rowspan="2" style="vertical-align: middle;" class="text-center success">BCC</th>
							</tr>
							
							<tr>
<!-- 								<th colspan="1" rowspan="1" class="text-center part1">Dup</th> -->
								<th colspan="1" rowspan="1" class="text-center part1">Trip</th>
								
								<th colspan="1" rowspan="1" class="text-center part1">Org</th>
								<th colspan="1" rowspan="1" class="text-center part1">Dup</th>
								<th colspan="1" rowspan="1" class="text-center part1">Trip</th>
								
								<th colspan="1" rowspan="1" class="text-center part1">Org</th>
								<th colspan="1" rowspan="1" class="text-center part1">Dup</th>
								<th colspan="1" rowspan="1" class="text-center part1">Trip</th>
							</tr>
							
							<%
							if(VContact_pers.size() > 0){
								for(int i = 0 ; i < VContact_pers.size() ; i++ ) {
								%>
									<tr class="text-center">
										<td class="info">
										<%=VContact_pers.elementAt(i) %>
										
										<%if(Vactive_flg.elementAt(i).toString().equalsIgnoreCase("N")){ %>
										<button type="button" class="btn btn-danger" disabled="disabled" title="In-Activated from FMS">In-Active</button>
										<%} %>
										</td>
										<td class="part1">
										<%if(Vt_inv_to.elementAt(i).toString().equalsIgnoreCase("Y")) {%>
											<img alt="" src="../images/checkmark.png" height="20px;" width="20px;">
										<%}else{ %>
											<img alt="" src="../images/xmark.png" height="20px;" width="20px;">
										<%} %>
										</td>
										<td class="part1">
										<%if(Vo_inv_cc.elementAt(i).toString().equalsIgnoreCase("Y")) {%>
											<img alt="" src="../images/checkmark.png" height="20px;" width="20px;">
										<%}else{ %>
											<img alt="" src="../images/xmark.png" height="20px;" width="20px;">
										<%} %>
										</td>
										<td class="part1">
										<%if(Vd_inv_cc.elementAt(i).toString().equalsIgnoreCase("Y")) {%>
											<img alt="" src="../images/checkmark.png" height="20px;" width="20px;">
										<%}else{ %>
											<img alt="" src="../images/xmark.png" height="20px;" width="20px;">
										<%} %>
										
										<td class="part1">
										<%if(Vt_inv_cc.elementAt(i).toString().equalsIgnoreCase("Y")) {%>
											<img alt="" src="../images/checkmark.png" height="20px;" width="20px;">
										<%}else{ %>
											<img alt="" src="../images/xmark.png" height="20px;" width="20px;">
										<%} %>
										</td>
										
										<td class="part1">
										<%if(Vo_inv_bcc.elementAt(i).toString().equalsIgnoreCase("Y")) {%>
											<img alt="" src="../images/checkmark.png" height="20px;" width="20px;">
										<%}else{ %>
											<img alt="" src="../images/xmark.png" height="20px;" width="20px;">
										<%} %>
										</td>
										
										<td class="part1">
										<%if(Vd_inv_bcc.elementAt(i).toString().equalsIgnoreCase("Y")) {%>
											<img alt="" src="../images/checkmark.png" height="20px;" width="20px;">
										<%}else{ %>
											<img alt="" src="../images/xmark.png" height="20px;" width="20px;">
										<%} %>
										</td>
										
										<td class="part1">
										<%if(Vt_inv_bcc.elementAt(i).toString().equalsIgnoreCase("Y")) {%>
											<img alt="" src="../images/checkmark.png" height="20px;" width="20px;">
										<%}else{ %>
											<img alt="" src="../images/xmark.png" height="20px;" width="20px;">
										<%} %>
										</td>
										
										
										<td class="success">
										<%if(Vform402_to.elementAt(i).toString().equalsIgnoreCase("Y")) {%>
											<img alt="" src="../images/checkmark.png" height="20px;" width="20px;">
										<%}else{ %>
											<img alt="" src="../images/xmark.png" height="20px;" width="20px;">
										<%} %>
										</td>
										
										<td class="success">
										<%if(Vform402_cc.elementAt(i).toString().equalsIgnoreCase("Y")) {%>
											<img alt="" src="../images/checkmark.png" height="20px;" width="20px;">
										<%}else{ %>
											<img alt="" src="../images/xmark.png" height="20px;" width="20px;">
										<%} %>
										</td>
										
										<td class="success">
										<%if(Vform402_bcc.elementAt(i).toString().equalsIgnoreCase("Y")) {%>
											<img alt="" src="../images/checkmark.png" height="20px;" width="20px;">
										<%}else{ %>
											<img alt="" src="../images/xmark.png" height="20px;" width="20px;">
										<%} %>
										</td>
									</tr>
								<%} %>
								<tr>
									<td colspan="17">
										&nbsp;
									</td>
								</tr>
								
							<%}else{%>
								<tr class="text-center success">
									<th colspan="18" class="text-center"> <font color="red"> No Details available</font> </th>
								</tr>
							<%} %>	
							
							
						</table>
					</div>
					<%}else if(radFlg.equalsIgnoreCase("cust")){ %>
					
					
	                  <div class="form-group row">
	                  	<label for="" class="col-sm-2 col-form-label">Customer Name : </label>
	                    	<div class="col-sm-4">
	                      		<select class="form-control form-control-sm" id="cust_cd" name="cust_cd" onchange="fetchDtl(this.value);">
										<option value="" >-Select-</option>
										<%for(int i = 0 ; i < Vcust_cd.size(); i++) {%>
											<option value="<%=Vcust_cd.elementAt(i)%>"><%=Vcust_nm.elementAt(i) %></option>
										<%} %>
			                 	</select>
			                 	<%if(!cust_cd.equalsIgnoreCase("")) {%>
			                 	<script type="text/javascript">
			                 		document.getElementById('cust_cd').value='<%=cust_cd%>'
			                 	</script>
			                 	<%} %>
	                    	</div>
	                 		<label for="" class="col-sm-2 col-form-label text-right">Plant (Address) :</label>
	                    	<div class="col-sm-4">
	                     		<select class="form-control form-control-sm" id="plant_cd" name="plant_cd" onchange="setPlant_cd(this.value,'<%=cust_cd%>');">
									<option value="" >-Select-</option>
									<%for(int i = 0 ; i < Vplant_name.size(); i++) {%>
										<option value="<%=Vplant_type.elementAt(i)%>"><%=Vplant_name.elementAt(i) %></option>
									<%} %>
			                 	</select>
			                 	<%if(!cust_cd.equalsIgnoreCase("")) {%>
			                 	<script type="text/javascript">
			                 		document.getElementById('plant_cd').value='<%=plant_cd%>'
			                 	</script>
			                 	<%} %>
	                    </div>
	                  </div>
                  </div>
                   <div class="col-xs-2" >
                   </div>
                  <div class="col-xs-8" >
	                  <div class="box-body table-responsive no-padding">
						<table class="table  table-bordered">
							<thead>
							<%if(!msg.equals("")){ %>	
								<tr>
									<td class="text-center" style="color: blue;font-weight: bold;font-size: 15px;" colspan="12"><%=msg %></td>
								</tr>
							<%} %>
							<%if(!error_msg.equals("")){ %>	
								<tr>
									<td class="text-center" style="color: red;font-weight: bold;font-size: 15px;" colspan="12"><%=error_msg %></td>
								</tr>
							<%} %>
								<tr class="info">
									<th class="text-center" colspan="2">Seq No.</th>
									<th class="text-center" colspan="4">Contact Person</th>
									<th class="text-center" colspan="1">To</th>
									<th class="text-center" colspan="1">CC</th>
								</tr>
							</thead>
							
							<tbody>
								<%for(int i = 0 ; i < Vseq_no.size(); i++) { %>
									<tr class="text-center success">
										<td colspan="2"><%=Vseq_no.elementAt(i) %>
											<input type="hidden"  name="Vseq_no" value="<%=Vseq_no.elementAt(i) %>">
										</td>
										<td colspan="4" class="text-left"><%=Vcontact_person.elementAt(i) %></td>
										<td colspan="1"><input type="radio" name="rad1" id="rad1<%=i %>"
											<%if(def_inv_to_flag.elementAt(i).equals("Y")) {%>
												checked="checked"
											<%} %>
										 	onclick="setCC('<%=Vseq_no.size() %>','<%=i%>');">
											<input type="hidden" name="toFlg" id="toFlg<%=i%>"
											<%if(def_inv_to_flag.elementAt(i).equals("Y")) {%>
												value="Y"
											<%} %>
											>
										 </td>
										<td colspan="1"><input type="checkbox" id="chk<%=i%>" disabled="disabled"
											<%if(def_inv_cc_flag.elementAt(i).equals("Y") && to_cnt > 0){ %>
												checked="checked"
											<%} %>
										> 
											<input type="hidden" name="ccFlg" id="ccFlg<%=i%>">
										</td>
									</tr>
								<%} %>
								<%if(Vseq_no.size() == 0){ %>
									<tr class="text-center success">
										<th colspan="8" class="text-center"> <font color="red"> No Details available for the selected Plant</font> </th>
									</tr>
								<%} %>
								<tr>
							<%if(avail_flg.equalsIgnoreCase("Y")) {%>
								<th colspan="8" class="text-right"><button type="button"  class="btn btn-warning" onclick="doSubmit();" id="subBtn" value="mod" 
								<%if(!write_permission.equalsIgnoreCase("Y")){ %>
								disabled="disabled"
								<%} %>
								>Modify </button></th>
							<%}else{ %>
								<th colspan="8" class="text-center"><button type="button"  class="btn btn-success" onclick="doSubmit();" id="subBtn" value="sub" 
								<%if(!write_permission.equalsIgnoreCase("Y")){ %>
								disabled="disabled"
								<%} %>
								>Submit </button></th>
							<%} %>
							</tr>
							
							<tr class="info">
								<th colspan="8"><h3 class="sub-header" align="center" id="head">Already Configured Contact Person(s)</h3></th>
							</tr>
							<tr class="info">
								<th colspan="1" class="text-center">Sequence No.</th>
								<th colspan="3" class="text-center">Contact Person</th>
								<th colspan="4" class="text-center">Plant Name</th>
							</tr>
							
								<%for (int i = 0 ; i < Vavail_seq_no.size() ; i++) {%>
									<tr class="info">	
										<td colspan="1" class="text-center"><%=Vavail_seq_no.elementAt(i) %></td>
										<td  colspan="3" class="text-center"><%=Vavail_cont_pers.elementAt(i) %></td>
										<td colspan="4" class="text-center"><%=Vavail_plant_nm.elementAt(i) %></td>
									</tr>
								<%} %>	
								
							
							</tbody>
						</table>
					</div>	
				</div>
				<div class="col-xs-2" ></div>
				<%}else if(radFlg.equalsIgnoreCase("trans")){ %>
					
					
	                  <div class="form-group row">
	                  	<label for="" class="col-sm-2 col-form-label">Transporter Name : </label>
	                    	<div class="col-sm-4">
	                      		<select class="form-control form-control-sm" id="trans_cd" name="trans_cd" onchange="setTransDtl();">
										<option value="" >-Select-</option>
										<%for(int i = 0 ; i < Vtrans_cd.size(); i++) {%>
											<option value="<%=Vtrans_cd.elementAt(i)%>"><%=Vtrans_nm.elementAt(i) %></option>
										<%} %>
			                 	</select>
			                 	<%if(!trans_cd.equalsIgnoreCase("")) {%>
			                 	<script type="text/javascript">
			                 		document.getElementById('trans_cd').value='<%=trans_cd%>'
			                 	</script>
			                 	<%} %>
	                    	</div>
	                 		<label for="" class="col-sm-2 col-form-label text-right">Address :</label>
	                    	<div class="col-sm-4">
	                     		<select class="form-control form-control-sm" id="addrs" name="addrs" onchange="setTransDtl();">
									<option value="" >-Select-</option>
									<option value="R" >Registered</option>
									<option value="C" >Correspondence</option>
									<option value="B" >Billing</option>
									
			                 	</select>
			                 	<%if(!trans_cd.equalsIgnoreCase("")) {%>
				                 	<script type="text/javascript">
				                 		document.getElementById('addrs').value='<%=addrs%>'
				                 	</script>
			                 	<%} %>
	                    </div>
	                  </div>
                  </div>
                   <div class="col-xs-2" >
                   </div>
                  <div class="col-xs-8" >
	                  <div class="box-body table-responsive no-padding">
						<table class="table  table-bordered">
							<thead>
							<%if(!msg.equals("")){ %>	
								<tr>
									<td class="text-center" style="color: blue;font-weight: bold;font-size: 15px;" colspan="12"><%=msg %></td>
								</tr>
							<%} %>
							<%if(!error_msg.equals("")){ %>	
								<tr>
									<td class="text-center" style="color: red;font-weight: bold;font-size: 15px;" colspan="12"><%=error_msg %></td>
								</tr>
							<%} %>
								<tr class="info">
									<th class="text-center" colspan="2">Seq No.</th>
									<th class="text-center" colspan="4">Contact Person</th>
									<th class="text-center" colspan="1">To</th>
									<th class="text-center" colspan="1">CC</th>
								</tr>
							</thead>
							
							<tbody>
								<%for(int i = 0 ; i < Vseq_no.size(); i++) { %>
									<tr class="text-center success">
										<td colspan="2"><%=Vseq_no.elementAt(i) %>
											<input type="hidden" name="Vseq_no" value="<%=Vseq_no.elementAt(i) %>">
										</td>
										<td colspan="4" class="text-left"><%=Vcontact_person.elementAt(i) %></td>
										<td colspan="1"><input type="radio" name="rad1" id="rad1<%=i %>"
											<%if(def_inv_to_flag.elementAt(i).equals("Y")) {%>
												checked="checked"
											<%} %>
										 	onclick="setCC('<%=Vseq_no.size() %>','<%=i%>');">
											<input type="hidden" name="toFlg" id="toFlg<%=i%>"
											<%if(def_inv_to_flag.elementAt(i).equals("Y")) {%>
												value="Y"
											<%} %>
											>
										 </td>
										<td colspan="1"><input type="checkbox" id="chk<%=i%>" disabled="disabled"
											<%if(def_inv_cc_flag.elementAt(i).equals("Y") && to_cnt > 0){ %>
												checked="checked"
											<%} %>
										> 
											<input type="hidden" name="ccFlg" id="ccFlg<%=i%>">
										</td>
									</tr>
								<%} %>
								<%if(Vseq_no.size() == 0){ %>
									<tr class="text-center success">
										<th colspan="8" class="text-center"> <font color="red"> No Details available for the selected Plant</font> </th>
									</tr>
								<%} %>
								<tr>
							<%if(avail_flg.equalsIgnoreCase("Y")) {%>
								<th colspan="8" class="text-right"><button type="button"  class="btn btn-warning" onclick="doSubmit();" id="subBtn" value="mod" 
								<%if(!write_permission.equalsIgnoreCase("Y")){ %>
								disabled="disabled"
								<%} %>
								>Modify </button></th>
							<%}else{ %>
								<th colspan="8" class="text-center">
								<button type="button"  class="btn btn-success" onclick="doSubmit();" id="subBtn" value="sub" 
								<%if(!write_permission.equalsIgnoreCase("Y")){ %>
								disabled="disabled"
								<%} %>
								>Submit </button></th>
							<%} %>
							</tr>
							
							<%-- <tr class="info">
								<th colspan="8"><h3 class="sub-header" align="center" id="head">Already Configured Contact Person(s)</h3></th>
							</tr>
							<tr class="info">
								<th colspan="1" class="text-center">Sequence No.</th>
								<th colspan="3" class="text-center">Contact Person</th>
								<th colspan="4" class="text-center">Plant Name</th>
							</tr>
							
								<%for (int i = 0 ; i < Vavail_seq_no.size() ; i++) {%>
									<tr class="info">	
										<td colspan="1" class="text-center"><%=Vavail_seq_no.elementAt(i) %></td>
										<td  colspan="3" class="text-center"><%=Vavail_cont_pers.elementAt(i) %></td>
										<td colspan="4" class="text-center"><%=Vavail_plant_nm.elementAt(i) %></td>
									</tr>
								<%} %>	 --%>
							</tbody>
						</table>
					</div>	
				</div>
				<div class="col-xs-2" ></div>
					<%} %>	
		</form>
<!-- 	</div> -->
</div>
</div>
<!-- </div> -->
<!-- </div> -->
<!-- </div> -->
<%if(radFlg.equalsIgnoreCase("sup")){
	if(!emp_cd.equalsIgnoreCase("")){
		if(form402_bcc.equalsIgnoreCase("Y")){%>
		<script type="text/javascript">
			document.getElementById('form_bcc').checked = true; 
			document.getElementById('hid_form_bcc').value = "Y"; 
		</script>
		
		<%}if(form402_cc.equalsIgnoreCase("Y")){%>
		<script type="text/javascript">
			document.getElementById('form_cc').checked = true; 
			document.getElementById('hid_form_cc').value = "Y";
		</script>
	
		<%}if(form402_to.equalsIgnoreCase("Y")){%>
		<script type="text/javascript">
			document.getElementById('form_to').checked = true; 
			document.getElementById('hid_form_to').value = "Y";
		</script>
	
		<%}
		if(d_inv_bcc.equalsIgnoreCase("Y")){%>
		<script type="text/javascript">
			document.getElementById('d_inv_bcc').checked = true; 
			document.getElementById('hid_d_inv_bcc').value = "Y";
		</script>
		<%}if(d_inv_cc.equalsIgnoreCase("Y")){%>
		<script type="text/javascript">
			document.getElementById('d_inv_cc').checked = true; 
			document.getElementById('hid_d_inv_cc').value = "Y";
		</script>
		<%}
		
		if(o_inv_bcc.equalsIgnoreCase("Y")){%>
		<script type="text/javascript">
		document.getElementById('o_inv_bcc').checked = true;
		document.getElementById('hid_o_inv_bcc').value = "Y";
	</script>
	<%}
		if(o_inv_cc.equalsIgnoreCase("Y")){%>
		<script type="text/javascript">
		document.getElementById('o_inv_cc').checked = true;
		document.getElementById('hid_o_inv_cc').value = "Y";
	</script>
	<%}if(t_inv_bcc.equalsIgnoreCase("Y")){%>
		<script type="text/javascript">
		document.getElementById('t_inv_bcc').checked = true; 
		document.getElementById('hid_t_inv_bcc').value = "Y";
	</script>
	<%}
		if(t_inv_cc.equalsIgnoreCase("Y")){%>
		<script type="text/javascript">
		document.getElementById('t_inv_cc').checked = true; 
		document.getElementById('hid_t_inv_cc').value = "Y";
	</script>
	<%}
		if(t_inv_to.equalsIgnoreCase("Y")){%>
		<script type="text/javascript">
		document.getElementById('t_inv_to').checked = true; 
		document.getElementById('hid_t_inv_to').value = "Y";
	</script>
	<%}
} }
%>
</div>
</body>
</html>
