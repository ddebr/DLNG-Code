<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date.*"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.*"%>
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
<script type="text/javascript">
function calculateSCM_TOTAL(obj,ind,inner_ind)
{
	var qtyFlg = true;
	var tot_inv_mmbtu = document.getElementById('tot_inv_mmbtu').value;
	var bal_qty = "";
	
	if(document.forms[0].inv_flg.value == 'Y' ){
		if(parseFloat(obj.value) < parseFloat(tot_inv_mmbtu) ){
			alert('Entered Quantity shoul be greater than Invoiced Quantity: '+tot_inv_mmbtu);
			qtyFlg = false;
			obj.value = "";
		}else{
			
			document.forms[0].scheduleBal_mmbtu.value = parseFloat(obj.value) - parseFloat(tot_inv_mmbtu);
		}
	}else{
		
		document.forms[0].scheduleBal_mmbtu.value = obj.value;//HA20200205
	}
// 	document.forms[0].scheduleBal_mmbtu.value = obj.value;//HA20200205
	var decision_flag = true;
	var decision = true;
	var qty_buyer_mmbtu = 0;
	var qty_mmbtu = 0;
	var index = 0;
	var inner_index = 0;
	var final_total = 0;
	var i = 0;
	var j = 0;
	var qty = "";
	var qtyTon = "";
	var round_upto_digits = 2;
	
	var index_count = parseInt(""+document.forms[0].index_count.value);
	var percentage_of_dcq = parseFloat(""+document.forms[0].percentage_of_dcq.value);
	var MIN_mmbtu_with_dcq = 0;
	var MAX_mmbtu_with_dcq = 0;
	var dcq = 0;
	
	var contract_type = "";
	var buyer_cd = "0";
	var buyer_abbr = "";
	var buyer_fgsa_cd = "0";
	var buyer_sn_cd = "0";
	
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
	
	/* var mmbtu_to_tons_convrt = document.forms[0].m3_to_tonMMbtu.value;
	var qtyTons = parseFloat(mmbtu_to_tons_convrt) * (parseFloat(obj.value) / parseFloat(document.forms[0].mmbtu_to_m3.value));
	document.getElementById('qty_ton'+ind).value = Math.round(qtyTons); */
	
	
	var convt_mmbtu_to_mt = document.forms[0].convt_mmbtu_to_mt.value;
	var qtyTons = round(parseFloat(obj.value) / parseFloat(convt_mmbtu_to_mt),2);
// 	alert(qtyTons)
	document.getElementById('qty_ton'+ind).value =qtyTons;
	
	if(obj.value!=null && obj.value!='' && obj.value!=' ' && obj.value!='  ')
	{
		qty_mmbtu = parseFloat(obj.value);
		index = parseInt(ind);
		decision_flag = true;
						
		if(index_count==1)
		{
			dcq = parseFloat(""+document.forms[0].qty_dcq.value);
			qty_buyer_mmbtu = parseFloat(""+document.forms[0].qty_buyer_mmbtu.value);
			
			contract_type = document.forms[0].contract_type.value;
			buyer_cd = document.forms[0].buyer_cd.value;
			buyer_abbr = document.forms[0].buyer_abbr.value;
			buyer_fgsa_cd = document.forms[0].buyer_fgsa_no.value;
			buyer_sn_cd = document.forms[0].buyer_sn_no.value;
			
			var cont_type = "CN";
			
			if(qty_mmbtu>qty_buyer_mmbtu)
			{
				alert("Buyer Nomination For --> "+buyer_abbr+" - "+cont_type+"("+buyer_sn_cd+") QTY: "+qty_mmbtu+" MMBTU,\nCan Not Exceed Buyer Nominated Qty: "+qty_buyer_mmbtu);
				obj.value = qty_buyer_mmbtu;
				decision_flag = true;
			}
		}
		else if(index_count>1)
		{
			dcq = parseFloat(""+document.forms[0].qty_dcq[index].value);
			qty_buyer_mmbtu = parseFloat(""+document.forms[0].qty_buyer_mmbtu[index].value);
			
			contract_type = document.forms[0].contract_type[index].value;
			buyer_cd = document.forms[0].buyer_cd[index].value;
			buyer_abbr = document.forms[0].buyer_abbr[index].value;
			buyer_fgsa_cd = document.forms[0].buyer_fgsa_no[index].value;
			buyer_sn_cd = document.forms[0].buyer_sn_no[index].value;
			
			var cont_type = "CN";
			
			if(qty_mmbtu>qty_buyer_mmbtu)
			{
				alert("Buyer Nomination For --> "+buyer_abbr+" - "+cont_type+"("+buyer_sn_cd+") QTY: "+qty_mmbtu+" MMBTU,\nCan Not Exceed Buyer Nominated Qty: "+qty_buyer_mmbtu);
				obj.value = qty_buyer_mmbtu;
				decision_flag = true;
			}			
		}
		
		MIN_mmbtu_with_dcq = dcq - (dcq*percentage_of_dcq/100);
		MAX_mmbtu_with_dcq = dcq + (dcq*percentage_of_dcq/100);
		
		MIN_mmbtu_with_dcq = round(MIN_mmbtu_with_dcq,round_upto_digits);
		MAX_mmbtu_with_dcq = round(MAX_mmbtu_with_dcq,round_upto_digits);
	}
	else
	{
		obj.value = '';
		index = parseInt(ind);
		
		if(index_count==1)
		{
			document.forms[0].qty_scm.value = '';
		}
		else if(index_count>1)
		{
			document.forms[0].qty_scm[index].value = '';
		}
		
		if(index_count==1)
		{
			document.forms[0].qty_ton.value = '';
		}
		else if(index_count>1)
		{
			document.forms[0].qty_ton[index].value = '';
		}
		
		if(index_count==1)
		{
			document.forms[0].total_mmbtu_qty.value = '';
			document.forms[0].total_scm_qty.value = '';
			document.forms[0].total_ton_qty.value = '';
		}
		else if(index_count>1)
		{
			document.forms[0].total_mmbtu_qty.value = '';
			document.forms[0].total_scm_qty.value = '';
			document.forms[0].total_ton_qty.value = '';
		}
		calculateSCM();
	}
	
	if(!decision_flag)
	{
		obj.value = '';
		index = parseInt(ind);
		
		if(index_count==1)
		{
			document.forms[0].qty_scm.value = '';
		}
		else if(index_count>1)
		{
			document.forms[0].qty_scm[index].value = '';
		}
		
		if(index_count==1)
		{
			document.forms[0].qty_ton.value = '';
		}
		else if(index_count>1)
		{
			document.forms[0].qty_ton[index].value = '';
		}
		if(index_count==1)
		{   
			document.forms[0].total_mmbtu_qty.value = '';
			document.forms[0].total_scm_qty.value = '';
			document.forms[0].total_ton_qty.value = '';
		}
		else if(index_count>1)
		{	
			document.forms[0].total_mmbtu_qty.value = '';
			document.forms[0].total_scm_qty.value = '';
			document.forms[0].total_ton_qty.value = '';
		}
		calculateSCM();
	}	
	
	if(obj.value!=null && obj.value!='' && obj.value!=' ' && obj.value!='  ')
	{
		
		qty_mmbtu = parseFloat(obj.value);
		index = parseInt(ind);
		
		if(index_count==1)
		{
			qty = document.forms[0].qty_mmbtu.value;
			if(qty!=null && qty!='' && qty!=' ' && qty!='  ')
			{
				document.forms[0].total_mmbtu_qty.value = round(qty,round_upto_digits);
			}
		}
		else if(index_count>1)
		{
			for(j=0;j<index_count;j++)
			{
				qty = document.forms[0].qty_mmbtu[j].value;
				if(qty!=null && qty!='' && qty!=' ' && qty!='  ')
				{
					final_total += parseFloat(qty); 
				}
			}
			if(final_total>0)
			{
				document.forms[0].total_mmbtu_qty.value = ""+round(final_total,round_upto_digits);
			}
		}
		
		final_total = 0;
		if(index_count==1)
		{
			qty = document.forms[0].qty_mmbtu.value;
			if(qty!=null && qty!='' && qty!=' ' && qty!='  ')
			{
				document.forms[0].qty_scm.value = ""+round(((parseFloat(""+qty)*multiplying_factor)/(gcv*deviding_factor)),0);
			}
		}
		else if(index_count>1)
		{
			for(i=0;i<index_count;i++)
			{
				qty = document.forms[0].qty_mmbtu[i].value;
				if(qty!=null && qty!='' && qty!=' ' && qty!='  ')
				{
					document.forms[0].qty_scm[i].value = ""+round(((parseFloat(""+qty)*multiplying_factor)/(gcv*deviding_factor)),0);
				}
			}
		}
		
		if(index_count==1)
		{
			qty = document.forms[0].qty_scm.value;
			if(qty!=null && qty!='' && qty!=' ' && qty!='  ')
			{
				document.forms[0].total_scm_qty.value = round(qty,0);
			}
		}
		else if(index_count>1)
		{
			for(j=0;j<index_count;j++)
			{
				qty = document.forms[0].qty_scm[j].value;
				if(qty!=null && qty!='' && qty!=' ' && qty!='  ')
				{
					final_total += parseFloat(qty); 
				}
			}
			if(final_total>0)
			{
				document.forms[0].total_scm_qty.value = ""+round(final_total,0);
			}
		}
		
		if(index_count==1)
		{
			qtyTon = document.forms[0].qty_ton.value;
			if(qtyTon!=null && qtyTon!='' && qtyTon!=' ' && qtyTon!='  ')
			{
				document.forms[0].total_ton_qty.value = round(qtyTon,0);
			}
		}
		else if(index_count>1)
		{   final_total = 0;
			for(j=0;j<index_count;j++)
			{
				qtyTon = document.forms[0].qty_ton[j].value;
				if(qtyTon!=null && qtyTon!='' && qtyTon!=' ' && qtyTon!='  ')
				{
					final_total += parseFloat(qtyTon); 
				}
			}
			if(final_total>0)
			{
				document.forms[0].total_ton_qty.value = ""+round(final_total,0);
			}
		}
		
		var total_qty = document.forms[0].total_mmbtu_qty.value;
// 		var fsru_tank_vol = document.forms[0].fsru_tank_vol.value;
// 		if(parseFloat(total_qty)>parseFloat(fsru_tank_vol)) {
// 			alert("Can't Schedule Quantity More Then FSRU Tank Volume!!!");
// 			flag = false;
// 		} 
	}
}
function calculateSCM()
{
	var final_total = 0;
	var i = 0;
	var j = 0;
	var qty = "";
	var qtyTon = "";
	var round_upto_digits = 2;
	
	var index_count = parseInt(""+document.forms[0].index_count.value);
	
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
	
	
	if(index_count==1)
	{
		qty = document.forms[0].qty_mmbtu.value;
		if(qty!=null && qty!='' && qty!=' ' && qty!='  ')
		{
			document.forms[0].total_mmbtu_qty.value = round(qty,round_upto_digits);
		}
	}
	else if(index_count>1)
	{
		for(j=0;j<index_count;j++)
		{
			qty = document.forms[0].qty_mmbtu[j].value;
			if(qty!=null && qty!='' && qty!=' ' && qty!='  ')
			{
				final_total += parseFloat(qty); 
			}
		}
		if(final_total>0)
		{
			document.forms[0].total_mmbtu_qty.value = ""+round(final_total,round_upto_digits);
		}
	}
	
	
	final_total = 0;
	if(index_count==1)
	{
		qty = document.forms[0].qty_mmbtu.value;
		if(qty!=null && qty!='' && qty!=' ' && qty!='  ')
		{
			document.forms[0].qty_scm.value = ""+round(((parseFloat(""+qty)*multiplying_factor)/(gcv*deviding_factor)),0);
		}
	}
	else if(index_count>1)
	{
		for(i=0;i<index_count;i++)
		{
			qty = document.forms[0].qty_mmbtu[i].value;
			if(qty!=null && qty!='' && qty!=' ' && qty!='  ')
			{
				document.forms[0].qty_scm[i].value = ""+round(((parseFloat(""+qty)*multiplying_factor)/(gcv*deviding_factor)),0);
			}
		}
	}
		
	if(index_count==1)
	{
		qty = document.forms[0].qty_scm.value;
		if(qty!=null && qty!='' && qty!=' ' && qty!='  ')
		{
			document.forms[0].total_scm_qty.value = round(qty,0);
		}
	}
	else if(index_count>1)
	{
		for(j=0;j<index_count;j++)
		{
			qty = document.forms[0].qty_scm[j].value;
			if(qty!=null && qty!='' && qty!=' ' && qty!='  ')
			{
				final_total += parseFloat(qty); 
			}
		}
		if(final_total>0)
		{
			document.forms[0].total_scm_qty.value = ""+round(final_total,0);
		}
	}
	
	if(index_count==1)
	{
		qtyTon = document.forms[0].qty_ton.value;
		if(qtyTon!=null && qtyTon!='' && qtyTon!=' ' && qtyTon!='  ')
		{
			document.forms[0].total_ton_qty.value = round(qtyTon,0);
		}
	}
	else if(index_count>1)
	{   final_total = 0;
		for(j=0;j<index_count;j++)
		{
			qtyTon = document.forms[0].qty_ton[j].value;
			if(qtyTon!=null && qtyTon!='' && qtyTon!=' ' && qtyTon!='  ')
			{
				final_total += parseFloat(qtyTon); 
			}
		}
		if(final_total>0)
		{
			document.forms[0].total_ton_qty.value = ""+round(final_total,0);
		}
	}
}

function refreshPage(){
	
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
	var update_permission = document.forms[0].update_permission.value;
	
	location.replace(modUrl+"?gas_date="+gas_date+"&gen_date="+gen_date+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&update_permission="+update_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl);
}

function check(indx,custid,weeklyNomFlag){
	
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
	var update_permission = document.forms[0].update_permission.value;
	
	
	var nomDt=document.forms[0].gas_date.value;
	var NomRevNo='';
	var SchRevNo='';
	var nomDay_wid_rev='';
	var schDt = '';
	var schId = '';
	schDt = gas_date;
	var cnf="";
	
	var contract_type = document.getElementById('contract_type'+indx).value;
// 	alert(contract_type)
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
	
	var url=modUrl+"?gas_date="+gas_date+"&gen_date="+gen_date+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&update_permission="+update_permission+"&custid="+custid+"&buyer_mapping_id="+buyer_mapping_id+"&schId="+schId+"&nomDt="+nomDt+"&rd="+NomRevNo+"&indx="+indx+"&SchRevNo="+SchRevNo+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&contract_type="+contract_type+"&modUrl="+modUrl;
	
	if(weeklyNomFlag == 'Y'){
		cnf = confirm("Weekly Seller Nomination has done for :"+gas_date +", Still you want to continue ?");
	}else{
		cnf = true;
	}
	if(cnf){
		location.replace(url);		
	}else{
		document.getElementById('checkbox'+indx).checked= false;
	}
	

}

function setScheduledQty(indx,selCustAbr,contract_no,gas_date,plant,truck_loaded_qty){
	
	
	document.getElementById('selIndx').value = indx;
	var flag = true;
	var msg = '';
    var qty_mmbtu = document.getElementById('qty_mmbtu'+indx).value;
    var gas_date = document.forms[0].gas_date.value;
	var gen_date = document.forms[0].gen_date.value;
	var gcv = document.forms[0].gcv.value;
	var ncv = document.forms[0].ncv.value;
	var conv_fact_m3_tonmmbbtu = document.forms[0].m3_to_tonMMbtu.value; //0.3531466672
	var conv_fact_m3 = document.forms[0].mmbtu_to_m3.value; //23.9
	var convt_mmbtu_to_mt = document.forms[0].convt_mmbtu_to_mt.value;
	var firstStr = '';
	var firstWord = plant.split(" ")
	for(var i=0;i<firstWord.length;i++){
		firstStr = firstWord[0];
	}
	
	qty_mmbtu = qty_mmbtu - 0;
	
	var cc = document.querySelector('#qty_mmbtu'+indx);
	cc.addEventListener('keyup',function(){
		this.value = this.value.replace(/[^0-9]/g,'');
	}); 
	
	var tot_inv_mmbtu = document.getElementById('tot_inv_mmbtu').value;
	
	var scheduled_qty_mmbtu = document.getElementById('qty_mmbtu'+indx).value;
	
	  if(gas_date==null || gas_date=='0' || gas_date=='' || gas_date==' ' || gas_date=='  ' || gas_date=='  ')
	  {
		 msg += "Please Enter The Schedule DATE For Schedule DAY\n";
		 flag = false;
	  }
	  if(gen_date==null || gen_date=='0' || gen_date=='' || gen_date==' ' || gen_date=='  ' || gen_date=='  ')
	  {
		 msg += "Please Enter The GENERATION DATE For GEN DAY\n";
		 flag = false;
	  }
	  if(gcv==null || gcv=='0' || gcv=='' || gcv==' ' || gcv=='  ' || gcv=='   ')
	  {
		 msg += "Please Enter The HEAT VALUE For GCV\n";
		 flag = false;
	  }
	  if(ncv==null || ncv=='0' || ncv=='' || ncv==' ' || ncv=='  ' || ncv=='   ')
 	  {
		 msg += "Please Enter The HEAT VALUE For NCV\n";
		 flag = false;
	  }

      if(qty_mmbtu==null || qty_mmbtu=='' || qty_mmbtu=='0' || qty_mmbtu==' ' || qty_mmbtu=='  ' || qty_mmbtu=='  ')
      {
         flag = false;
         msg+='-Please Enter Schedule Qty\n';
      }
      if(gas_date!=null && gas_date!='0' && gas_date!='' && gas_date!=' ' && gas_date!='  ' && gas_date!='  ')
  	  {
  		 if(gen_date!=null && gen_date!='0' && gen_date!='' && gen_date!=' ' && gen_date!='  ' && gen_date!='  '){
  			var value = compareDate(gen_date,gas_date);
  		  	if(value==1){
  		    	msg += "Generation Day Must Be Less Than Or Equal To Schedule Date\n";
  		    	flag = false;
  		  	}
  		 }
  	  }
		if(flag)
		{
			if(document.forms[0].chk.length == undefined || document.forms[0].chk.length == 'undefined'){
				document.getElementById('schedul_qty_mmbtu').value = document.forms[0].qty_mmbtu.value;
				document.getElementById('schedul_qty_scm').value = document.forms[0].qty_scm.value;
				document.getElementById('schedul_qty_ton').value = round(parseFloat(document.forms[0].qty_mmbtu.value) / parseFloat(convt_mmbtu_to_mt) ,2);
			}	
			else if(document.forms[0].chk.length > 1){
				
				document.getElementById('schedul_qty_mmbtu').value = document.forms[0].qty_mmbtu[indx].value;
				document.getElementById('schedul_qty_scm').value = document.forms[0].qty_scm[indx].value;
				document.getElementById('schedul_qty_ton').value = round(parseFloat(document.forms[0].qty_mmbtu[indx].value) / parseFloat(convt_mmbtu_to_mt),2);
			}
			/* if(document.forms[0].scheduleBal_mmbtu.value == ''){
				document.forms[0].scheduleBal_mmbtu.value = scheduled_qty_mmbtu;
			} */
			var space="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			document.getElementById('selCustAbr').innerHTML= "Gas Day : "+gas_date+space+"Truck selection for<span style='color:blue;'> "+selCustAbr+" "+"("+contract_no+")</span>&nbsp;&nbsp;"+firstStr;
// 		 	document.forms[0].actual_mmbtu.value = nominated_qty_mmbtu ;
			if(document.forms[0].inv_flg.value == 'Y' ){ // if invoice generated
				
				document.forms[0].scheduleBal_mmbtu.value = parseFloat(scheduled_qty_mmbtu) - parseFloat(tot_inv_mmbtu);
			}else{
// 				alert(truck_loaded_qty)
				if(parseFloat(truck_loaded_qty) > 0){
					document.forms[0].scheduleBal_mmbtu.value = parseFloat(scheduled_qty_mmbtu) - parseFloat(truck_loaded_qty);
				}else{
					document.forms[0].scheduleBal_mmbtu.value = scheduled_qty_mmbtu ;	
				}
			}
			
			var truck_size = document.forms[0].truck_size.value; 
			for(var i = 0 ; i < parseFloat(truck_size) ; i++){
				if(document.getElementById('scheduleChecks'+i).checked){
					document.getElementById('scheduleChecks'+i).checked=false;
					document.getElementById('chkSchedulFlag'+i).value="N";					
				}
			}
			
			$('#myTrukSchedul').modal('show');
		}
		else
		{
			alert("First Checks the Following Fileds !\n\n"+msg)
        	$('#myTrukSchedul').modal('hide');
		}
}

function scheduleQty(indx)
{	
	var gcv = parseFloat(""+document.forms[0].gcv.value);
	var ncv = parseFloat(""+document.forms[0].ncv.value);
	var conv_fact_m3 = document.forms[0].mmbtu_to_m3.value; //23.9
	var conv_fact_ton = document.forms[0].mmbtu_to_tons.value; //0.025219021687207
	var conv_fact_m3_tonmmbbtu = document.forms[0].m3_to_tonMMbtu.value; //0.3531466672
	var convt_mmbtu_to_mt = document.forms[0].convt_mmbtu_to_mt.value;
	
	var schedul_qty_mmbtu = document.getElementById('schedul_qty_mmbtu').value;
	var schedul_qty_ton = document.getElementById('schedul_qty_ton').value;
	
	if(schedul_qty_mmbtu !='' && schedul_qty_mmbtu != '0')
	{   
		if(document.getElementById('scheduleChecks'+indx).checked)
		{   
			document.getElementById('chkSchedulFlag'+indx).value='Y';
			var truckCap_m3 = document.getElementById('TCapM3s'+indx).value;
			var truckCap_ton = round(document.getElementById('TCapTon'+indx).value,2);
			var truckCap_mmbtu = Math.round(parseFloat(document.getElementById('TCapMMbtus'+indx).value)); // truck capacity in MMBTU
			var actual_mmbtu = document.forms[0].scheduleBal_mmbtu.value;
			var loaded_mmbtu = Math.round(parseFloat(document.getElementById('loaded_mmbtu'+indx).value));
			var loaded_ton = round(parseFloat(loaded_mmbtu) / parseFloat(convt_mmbtu_to_mt) ,2);
			document.getElementById('loaded_ton'+indx).value = loaded_ton;
			
				if(parseFloat(truckCap_mmbtu) >= parseFloat(schedul_qty_mmbtu)) // checking trcuk capacity
				{   
					//alert("if...truckCap_mmbtu("+truckCap_mmbtu+") >= schedul_qty_mmbtu("+schedul_qty_mmbtu+")")
					if(actual_mmbtu != '0'  && actual_mmbtu!='')
					{	
						document.getElementById('schedule_mmbtu'+indx).value = actual_mmbtu;	
						document.getElementById('schedule_ton'+indx).value = round(parseFloat(document.getElementById('schedule_mmbtu'+indx).value) / parseFloat(convt_mmbtu_to_mt),2);
						document.forms[0].scheduleBal_mmbtu.value = '0';
						
					}
					else
					{  
						document.getElementById('scheduleChecks'+indx).checked=false;
						if(document.getElementById('already_schedule_mmbtu'+indx).value != ''){
							document.getElementById('schedule_mmbtu'+indx).value = document.getElementById('already_schedule_mmbtu'+indx).value;
							document.getElementById('schedule_ton'+indx).value = document.getElementById('allready_schedule_ton'+indx).value;
						}else{ 
							document.getElementById('schedule_mmbtu'+indx).value='';
							document.getElementById('schedule_ton'+indx).value = '';
						}
						document.getElementById('chkSchedulFlag'+indx).value="N";
						alert('Not enough quantity to schedule!');
					}
				}
				else if(parseFloat(truckCap_mmbtu) <= parseFloat(schedul_qty_mmbtu)) // checking trcuk capacity
				{   
					//alert("else...truckCap_mmbtu("+truckCap_mmbtu+") <= schedul_qty_mmbtu("+schedul_qty_mmbtu+")")
					if(actual_mmbtu != '0'  && actual_mmbtu!='')
					{   
						if( (parseFloat(actual_mmbtu) <= parseFloat(truckCap_mmbtu)))
						{    
							document.getElementById('schedule_mmbtu'+indx).value = Math.round(parseFloat(actual_mmbtu));
							document.getElementById('schedule_ton'+indx).value = round(parseFloat(document.getElementById('schedule_mmbtu'+indx).value) / parseFloat(convt_mmbtu_to_mt),2);
							document.forms[0].scheduleBal_mmbtu.value = '0';
						}
						else
						{   
							document.getElementById('schedule_mmbtu'+indx).value = Math.round(parseFloat(truckCap_mmbtu));
							document.getElementById('schedule_ton'+indx).value = round(parseFloat(truckCap_ton),2);//Math.round( parseFloat(conv_fact_m3_tonmmbbtu) * ( parseFloat(document.getElementById('schedule_mmbtu'+indx).value) / parseFloat(conv_fact_m3) ) );
							document.getElementById('loaded_ton'+indx).value = '0';
							var balance_qty_mmbtu = parseFloat(actual_mmbtu) - parseFloat(truckCap_mmbtu);
// 							document.forms[0].scheduleBal_mmbtu.value = Math.round(parseFloat(balance_qty_mmbtu));
						}
					}
					else
					{    
						if(document.getElementById('already_schedule_mmbtu'+indx).value != ''){
							document.getElementById('schedule_mmbtu'+indx).value = document.getElementById('already_schedule_mmbtu'+indx).value;
							document.getElementById('schedule_ton'+indx).value = document.getElementById('allready_schedule_ton'+indx).value;
						}else{ 
							document.getElementById('schedule_mmbtu'+indx).value='';
							document.getElementById('schedule_ton'+indx).value = '';
						}
						document.getElementById('scheduleChecks'+indx).checked=false;
						document.getElementById('chkSchedulFlag'+indx).value="N";
						alert('Not enough quantity to schedule!');
					}
				}
				else
				{  
					document.getElementById('schedule_mmbtu'+indx).value = truckCap_mmbtu.toFixed(2);
					document.getElementById('schedule_ton'+indx).value = round(parseFloat(truckCap_ton),2)
				}					
		}
		else
		{   
			document.getElementById('chkSchedulFlag'+indx).value='N';
			
			var rem_qty = document.forms[0].scheduleBal_mmbtu.value;
			var conv_to_mmbtu = document.getElementById('schedule_mmbtu'+indx).value;
			if(conv_to_mmbtu == ''){
				conv_to_mmbtu = 0;
			}
			var bal_qty = parseFloat(rem_qty) + parseFloat(conv_to_mmbtu);
			document.forms[0].scheduleBal_mmbtu.value = bal_qty;
			if(document.getElementById('already_schedule_mmbtu'+indx).value != ''){
				document.getElementById('schedule_mmbtu'+indx).value = document.getElementById('already_schedule_mmbtu'+indx).value;
				document.getElementById('schedule_ton'+indx).value = document.getElementById('allready_schedule_ton'+indx).value;
			}else{
				document.getElementById('schedule_mmbtu'+indx).value='';
				document.getElementById('schedule_ton'+indx).value = '';
			}
			document.getElementById('loaded_ton'+indx).value = '';
		}
	}
	else
	{   
		alert('Scheduled quantity not available, please enter scheduled quantity first !!');
		document.getElementById('scheduleChecks'+indx).checked=false;
		document.getElementById('chkSchedulFlag'+indx).value="N";
	}
}

function validateScheduleQty(indx){
	
	var chkLen = document.forms[0].chekboxs.length;
	var conv_fact_m3 = document.forms[0].mmbtu_to_m3.value; //23.9
	var conv_fact_ton = document.forms[0].mmbtu_to_tons.value; //0.025219021687207
	var conv_fact_m3_tonmmbbtu = document.forms[0].m3_to_tonMMbtu.value; //0.3531466672
	var convt_mmbtu_to_mt = document.forms[0].convt_mmbtu_to_mt.value;
	
	var inv_flg = document.forms[0].inv_flg.value;
	var tot_inv_mmbtu = document.forms[0].tot_inv_mmbtu.value;
	
	var sum = 0;
	var ind = document.getElementById('selIndx').value;
	var total_loaded_mmbtu = document.getElementById('total_loaded_mmbtu'+ind).value;
// 	alert(total_loaded_mmbtu)
	if(chkLen > 1){
		for(var i = 0 ; i < chkLen ; i++){
			if(document.getElementById('schedule_mmbtu'+i).value!=''){
				if(document.getElementById('scheduleChecks'+i).checked == true)
				{
					sum+=parseFloat(document.getElementById('schedule_mmbtu'+i).value);
				}
			}
		}
	}
	if(chkLen=='undefined' || chkLen==undefined ){
		if(document.getElementById('schedule_mmbtu'+indx).value!=''){
			if(document.getElementById('scheduleChecks'+indx).checked == true)
			{
				sum+=parseFloat(document.getElementById('schedule_mmbtu'+indx).value);
			}
		}
	}
	
	var schedule_Cheked_val = sum - document.getElementById('schedule_mmbtu'+indx).value;
	var notSchedul_gretaer_val = document.getElementById('schedul_qty_mmbtu').value - schedule_Cheked_val;
	
	if(parseFloat(sum) > parseFloat(document.getElementById('schedul_qty_mmbtu').value))
	{   
		if(parseFloat(sum) > parseFloat(document.getElementById('TCapMMbtus'+indx).value))
		{
			if(document.forms[0].scheduleBal_mmbtu.value == '0')
			{  
				alert('Do Not Scheduled Qty Greater then the Truck Capacity or Not Enough Qty!!');
				if(document.getElementById('already_schedule_mmbtu'+indx).value != ''){
					document.getElementById('schedule_mmbtu'+indx).value = document.getElementById('already_schedule_mmbtu'+indx).value;
					document.getElementById('schedule_ton'+indx).value = document.getElementById('allready_schedule_ton'+indx).value;
				}else{
					document.getElementById('schedule_mmbtu'+indx).value='';
					document.getElementById('schedule_ton'+indx).value = '';
				}
				document.forms[0].scheduleBal_mmbtu.value = parseFloat(notSchedul_gretaer_val);
				document.getElementById('scheduleChecks'+indx).checked = false;
				document.getElementById('chkSchedulFlag'+indx).value="N";
			}
			else
			{ 
				alert('Do Not Scheduled Qty Greater then the Truck Capacity or Not Enough Qty!!');
				document.forms[0].scheduleBal_mmbtu.value = parseFloat(notSchedul_gretaer_val);
				if(document.getElementById('already_schedule_mmbtu'+indx).value != ''){
					document.getElementById('schedule_mmbtu'+indx).value = document.getElementById('already_schedule_mmbtu'+indx).value;
					document.getElementById('schedule_ton'+indx).value = document.getElementById('allready_schedule_ton'+indx).value;
				}else{
					document.getElementById('schedule_mmbtu'+indx).value='';
					document.getElementById('schedule_ton'+indx).value = '';
				}
				document.getElementById('scheduleChecks'+indx).checked = false;
				document.getElementById('chkSchedulFlag'+indx).value="N";
			}
		}
		else
		{    
			alert('Do Not Scheduled Qty Greater then the Truck Capacity or Not Enough Qty!!');
			document.forms[0].scheduleBal_mmbtu.value = parseFloat(notSchedul_gretaer_val);
			if(document.getElementById('already_schedule_mmbtu'+indx).value != ''){
				document.getElementById('schedule_mmbtu'+indx).value = document.getElementById('already_schedule_mmbtu'+indx).value;
				document.getElementById('schedule_ton'+indx).value = document.getElementById('allready_schedule_ton'+indx).value;
			}else{
				document.getElementById('schedule_mmbtu'+indx).value='';
				document.getElementById('schedule_ton'+indx).value = '';
			}
			document.getElementById('scheduleChecks'+indx).checked = false;
			document.getElementById('chkSchedulFlag'+indx).value="N";
		}
	}
	else
	{   
		if(parseFloat(sum) > parseFloat(document.getElementById('TCapMMbtus'+indx).value))
		{
			if(document.forms[0].scheduleBal_mmbtu.value == '0')
			{  			
				//document.forms[0].scheduleBal_mmbtu.value = parseFloat(document.getElementById('schedul_qty_mmbtu').value) - parseFloat(sum);
				if(inv_flg=='Y'){
					document.forms[0].scheduleBal_mmbtu.value = (parseFloat(document.getElementById('schedul_qty_mmbtu').value) -parseFloat(tot_inv_mmbtu) )- parseFloat(sum);	
				}else{
					if(parseFloat(total_loaded_mmbtu) > 0){
						document.forms[0].scheduleBal_mmbtu.value = (parseFloat(document.getElementById('schedul_qty_mmbtu').value) -parseFloat(total_loaded_mmbtu) )- parseFloat(sum);
					}else{
						document.forms[0].scheduleBal_mmbtu.value = parseFloat(document.getElementById('schedul_qty_mmbtu').value) - parseFloat(sum);
					}
				}
				document.getElementById('schedule_ton'+indx).value = round(parseFloat(document.getElementById('schedule_mmbtu'+indx).value) / parseFloat(convt_mmbtu_to_mt),2);
			}
			else
			{
				if(schedule_Cheked_val == '0'){    
					alert('Do Not Scheduled Qty Greater then the Truck Capacity or Not Enough Qty!!');
					if(document.getElementById('already_schedule_mmbtu'+indx).value != ''){
						document.getElementById('schedule_mmbtu'+indx).value = document.getElementById('already_schedule_mmbtu'+indx).value;
						document.getElementById('schedule_ton'+indx).value = document.getElementById('allready_schedule_ton'+indx).value;
					}else{
						document.getElementById('schedule_mmbtu'+indx).value='';
						document.getElementById('schedule_ton'+indx).value = '';
					}
					document.forms[0].scheduleBal_mmbtu.value = parseFloat(notSchedul_gretaer_val);
					document.getElementById('scheduleChecks'+indx).checked = false;
					document.getElementById('chkSchedulFlag'+indx).value="N";
				}
				else
				{
					if(inv_flg=='Y'){
						document.forms[0].scheduleBal_mmbtu.value = (parseFloat(document.getElementById('schedul_qty_mmbtu').value) -parseFloat(tot_inv_mmbtu) )- parseFloat(sum);	
					}else{
						if(parseFloat(total_loaded_mmbtu) > 0){
							document.forms[0].scheduleBal_mmbtu.value = (parseFloat(document.getElementById('schedul_qty_mmbtu').value) -parseFloat(total_loaded_mmbtu) )- parseFloat(sum);
						}else{
							document.forms[0].scheduleBal_mmbtu.value = parseFloat(document.getElementById('schedul_qty_mmbtu').value) - parseFloat(sum);
						}
// 						document.forms[0].scheduleBal_mmbtu.value = parseFloat(document.getElementById('schedul_qty_mmbtu').value) - parseFloat(sum);
					}
					
					if(parseFloat(document.getElementById('schedule_mmbtu'+indx).value) < parseFloat(document.getElementById('TCapMMbtus'+indx).value)){
						document.getElementById('schedule_ton'+indx).value = round(parseFloat(document.getElementById('schedule_mmbtu'+indx).value) / parseFloat(convt_mmbtu_to_mt),2);
					}
					else{
						alert('Do Not Load Qty Greater then the Truck Capacity or Not Enough Qty!!');
						document.forms[0].scheduleBal_mmbtu.value = parseFloat(notSchedul_gretaer_val);
						if(document.getElementById('already_schedule_mmbtu'+indx).value != ''){
							document.getElementById('schedule_mmbtu'+indx).value = document.getElementById('already_schedule_mmbtu'+indx).value;
							document.getElementById('schedule_ton'+indx).value = document.getElementById('allready_schedule_ton'+indx).value;
						}else{
							document.getElementById('schedule_mmbtu'+indx).value='';
							document.getElementById('schedule_ton'+indx).value = '';
						}
						document.getElementById('scheduleChecks'+indx).checked = false;
						document.getElementById('chkSchedulFlag'+indx).value="N";
					}		
				}
			}
		}
		else
		{   
			if(document.getElementById('schedule_mmbtu'+indx).value == ''){
				if(document.getElementById('already_schedule_mmbtu'+indx).value != ''){
					document.getElementById('schedule_mmbtu'+indx).value = document.getElementById('already_schedule_mmbtu'+indx).value;
					document.getElementById('schedule_ton'+indx).value = document.getElementById('allready_schedule_ton'+indx).value;
				}else{
					document.getElementById('schedule_mmbtu'+indx).value='';
					document.getElementById('schedule_ton'+indx).value = '';
				}	
				document.getElementById('scheduleChecks'+indx).checked = false;
				document.getElementById('chkSchedulFlag'+indx).value="N";
			}else{
				document.getElementById('schedule_ton'+indx).value = round(parseFloat(document.getElementById('schedule_mmbtu'+indx).value) / parseFloat(convt_mmbtu_to_mt),2);	
			}
			if(inv_flg=='Y'){
				document.forms[0].scheduleBal_mmbtu.value = (parseFloat(document.getElementById('schedul_qty_mmbtu').value) -parseFloat(tot_inv_mmbtu) )- parseFloat(sum);	
			}else{
				if(parseFloat(total_loaded_mmbtu) > 0){
					document.forms[0].scheduleBal_mmbtu.value = (parseFloat(document.getElementById('schedul_qty_mmbtu').value) -parseFloat(total_loaded_mmbtu) )- parseFloat(sum);
				}else{
					document.forms[0].scheduleBal_mmbtu.value = parseFloat(document.getElementById('schedul_qty_mmbtu').value) - parseFloat(sum);
				}
// 				document.forms[0].scheduleBal_mmbtu.value = parseFloat(document.getElementById('schedul_qty_mmbtu').value) - parseFloat(sum);
			}
// 			document.forms[0].scheduleBal_mmbtu.value = parseFloat(document.getElementById('schedul_qty_mmbtu').value) - parseFloat(sum);
		}
	}
// 	alert(document.getElementById('schedul_qty_mmbtu').value)
}

function ScheduleTrucks(){
	var flag = true;
	var flagchekcs = 0;
	var i = 0;
	var msg ="";
	var chkLen = document.forms[0].chekboxs.length;
	var schedultrucknm = new Array();
    var schedul_mmbtu = new Array();
    var schedul_ton = new Array();
    var schedulArrival_date = new Array();
	
    if(chkLen == 'undefined' || chkLen == undefined){
    	if(document.forms[0].chekboxs.checked){
    	flagchekcs = 1;
		var schedultrucknm = document.forms[0].trucknm.value;
		var schedul_mmbtu = document.forms[0].schedule_mmbtu.value;
		var schedul_ton = document.forms[0].schedule_ton.value;
		var schedulArrival_date = document.forms[0].schedul_date.value;

			if(schedul_mmbtu==null || schedul_mmbtu=='0' || schedul_mmbtu=='' || schedul_mmbtu==' ' || schedul_mmbtu=='  ' || schedul_mmbtu=='  ')
    		{
    			msg += "Please Enter The Correct Quantity of MMBTU to Schedule Trucks !!!\n";
    			flag = false;
    		}
    		if(schedul_ton==null || schedul_ton=='0' || schedul_ton=='' || schedul_ton==' ' || schedul_ton=='  ' || schedul_ton=='  ')
    		{
    			msg += "Please Enter The Correct Quantity of Ton to Schedule Trucks !!!\n";
    			flag = false;
    		}
    		if(schedulArrival_date==null || schedulArrival_date=='0' || schedulArrival_date=='00/00/0000' || schedulArrival_date=='' || schedulArrival_date==' ' || schedulArrival_date=='  ' || schedulArrival_date=='  ')
    		{
    			msg += "Please Enter The Correct Schedule Date  !!!\n";
    			flag = false;
    		}
    	}
	}
    if(parseFloat(chkLen) > 1){
		for(var i = 0 ; i < parseFloat(chkLen) ; i++){	
			if(document.forms[0].chekboxs[i].checked){
				flagchekcs++;
				schedultrucknm[i] = document.forms[0].trucknm[i].value;
	    		schedul_mmbtu[i] = document.forms[0].schedule_mmbtu[i].value;
	    		schedul_ton[i] = document.forms[0].schedule_ton[i].value;
	    		schedulArrival_date[i] = document.forms[0].schedul_date[i].value;

	    		if(schedul_mmbtu[i]==null || schedul_mmbtu[i]=='0' || schedul_mmbtu[i]=='' || schedul_mmbtu[i]==' ' || schedul_mmbtu[i]=='  ' || schedul_mmbtu[i]=='  ')
	    		{
	    			msg += "Please Enter The Correct Quantity of MMBTU to Schedule Trucks !!!\n";
	    			flag = false;
	    		}
	    		if(schedul_ton[i]==null || schedul_ton[i]=='0' || schedul_ton[i]=='' || schedul_ton[i]==' ' || schedul_ton[i]=='  ' || schedul_ton[i]=='  ')
	    		{
	    			msg += "Please Enter The Correct Quantity of Ton to Schedule Trucks !!!\n";
	    			flag = false;
	    		}
	    		if(schedulArrival_date[i]==null || schedulArrival_date[i]=='0' || schedulArrival_date[i]=='' || schedulArrival_date[i]==' ' || schedulArrival_date[i]=='  ' || schedulArrival_date[i]=='  ')
	    		{
	    			msg += "Please Enter The Correct Schedule Date  !!!\n";
	    			flag = false;
	    		} 	
			}
	    }
	}
    
    if(flagchekcs > 0){}
    else{
    	flag = false;
    	msg += "Please Select One Truck To Schedule!!!\n";
    }
    if(flag)
	{
    	var a = confirm("Are you sure want to Schedule Truck ?");
    	if(a){
    		$('#myform').submit();
    	}	
	}
	else
	{
		if(flagchekcs>0){
			alert("First Checks the Following Fileds !\n\n"+msg)		
		}
		else{
			swal({
				customClass: 'swal-height',
				title: msg,
				icon: "../images/coolcheckbox.gif", 
				timer: 2000
			});	
		}
	}
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
		msg += "Please Enter The Correct Schedule DATE For Schedule DAY Field !!!\n";
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
		    	msg += "Generation Day Must Be Less Than Or Equal To Schedule Date !!!\n";
		    	flag = false;
		  	}
		}
	}
	
	var qty_buyer_mmbtu = 0 ;
	if(index_count==1)
	{
		if(document.forms[0].chk.checked)
		{
			document.forms[0].chk_flag.value = "Y";
			var hh_mm = document.forms[0].hh_mm.value;
			var buyer_plant_seq_no = document.forms[0].buyer_plant_seq_no.value;
			var qty_mmbtu = document.forms[0].qty_mmbtu.value;
			qty_buyer_mmbtu = document.forms[0].qty_buyer_mmbtu.value;
			
			if(hh_mm==null || hh_mm=='' || hh_mm==' ' || hh_mm=='  ' || hh_mm=='  ')
			{
				msg += "Please Enter The Correct Time Under HH:MM Column !!!\n";
				flag = false;
			}
			if(buyer_plant_seq_no==null || buyer_plant_seq_no=='0' || buyer_plant_seq_no=='' || buyer_plant_seq_no==' ')
			{
				msg += "Please Select The Correct Plant Under Plant Selection Column !!!\n";
				flag = false;
			}
			if(qty_mmbtu==null || qty_mmbtu=='' || qty_mmbtu==' ' || qty_mmbtu=='  ' || qty_mmbtu=='   ')
			{
				msg += "Please Enter The Correct QTY In MMBTU Under QTY(MMBTU) Column !!!\n";
				flag = false;
			}
			
		}
		else
		{
			document.forms[0].chk_flag.value = "N";
			msg += "Please Select The CheckBox Before Submitting The Data !!!\n";
			flag = false;
		}
	}
	else if(index_count>1)
	{
		var cnt = 0;
		var index = 0;
		for(i=0;i<index_count;i++)
		{
			if(document.forms[0].chk[i].checked)
			{
				index = i+1;
				document.forms[0].chk_flag[i].value = "Y";
				var hh_mm = document.forms[0].hh_mm[i].value;
				var buyer_plant_seq_no = document.forms[0].buyer_plant_seq_no[i].value;
				var qty_mmbtu = document.forms[0].qty_mmbtu[i].value;
				qty_buyer_mmbtu = document.forms[0].qty_buyer_mmbtu[i].value;
				
				if(hh_mm==null || hh_mm=='' || hh_mm==' ' || hh_mm=='  ' || hh_mm=='  ')
				{
					msg += "Please Enter The Correct Time Under HH:MM Column For Row NO : "+index+" !!!\n";
					flag = false;
				}
				if(buyer_plant_seq_no==null || buyer_plant_seq_no=='0' || buyer_plant_seq_no=='' || buyer_plant_seq_no==' ')
				{
					msg += "Please Select The Correct Plant Under Plant Selection Column For Row NO : "+index+" !!!\n";
					flag = false;
				}
				if(qty_mmbtu==null || qty_mmbtu=='' || qty_mmbtu==' ' || qty_mmbtu=='  ' || qty_mmbtu=='   ')
				{
					msg += "Please Enter The Correct QTY In MMBTU Under QTY(MMBTU) Column For Row NO : "+index+" !!!\n";
					flag = false;
				}				
				++cnt;
			}
			else
			{
				document.forms[0].chk_flag[i].value = "N";
			}
		}
		
		if(cnt==0)
		{
			msg += "Please Select Atleast One CheckBox Before Submitting The Data !!!\n";
			flag = false;
		}
	}
	else
	{
		alert("Index Count NOT Defined Properly !!!");
		flag = false;
	}
	
	if(flag) {
		var total_qty = document.forms[0].total_mmbtu_qty.value;
// 		var fsru_tank_vol = document.forms[0].fsru_tank_vol.value;
// 		if(parseFloat(total_qty)>parseFloat(fsru_tank_vol)) {
// // 			alert("Can't Schedule Quantity More Then FSRU Tank Volume!!!");
// // 			flag = false;
// 		} 
	}
		
	if(flag)
	{
		var a = "";
		if(parseFloat(qty_buyer_mmbtu) > 0){
			a = confirm("Buyer Nominated Quantity is :"+qty_buyer_mmbtu+", Still You want to Release the truck(s)");
		}else{
			a = confirm("Do You Want To Submit Customer Scheduling Details ???");
		}
		if(a)
		{
			document.forms[0].subType.value="noTruck";
			document.forms[0].submit();
		}
	}
	else
	{
		alert(msg);
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

utilBean.init();
String current_date = utilBean.getGen_dt();
String tomorrow_date = utilBean.getDate_tomorrow();
NumberFormat nf = new DecimalFormat("###########0.00");
NumberFormat nf2 = new DecimalFormat("############");

String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");
		 
String truck_load_date = request.getParameter("gas_date")==null?tomorrow_date:request.getParameter("gas_date"); //SB20200725
String gas_date = request.getParameter("gas_date")==null?tomorrow_date:request.getParameter("gas_date");
String gen_date = request.getParameter("gen_date")==null?current_date:request.getParameter("gen_date");
String msg = request.getParameter("msg")==null?"":request.getParameter("msg");

String write_permission = (String)session.getAttribute("write_permission") ==null?"N":session.getAttribute("write_permission").toString();
String delete_permission = (String)session.getAttribute("delete_permission")==null?"N":session.getAttribute("delete_permission").toString();
String print_permission = (String)session.getAttribute("print_permission") ==null?"N":session.getAttribute("print_permission").toString();
String approve_permission = (String)session.getAttribute("approve_permission") ==null?"N":session.getAttribute("approve_permission").toString();
String audit_permission = (String)session.getAttribute("audit_permission") ==null?"N":session.getAttribute("audit_permission").toString();
String update_permission = (String)session.getAttribute("update_permission") ==null?"N":session.getAttribute("update_permission").toString();

String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");

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
String modUrl = session.getAttribute("modUrl")==null?"":session.getAttribute("modUrl").toString();//HA20210607

contMgmt.setCallFlag("CUSTOMER_SCH");
contMgmt.setGas_date(gas_date);
contMgmt.setSelSchedulCust(selSchedulCust);
contMgmt.setBuyer_mapping_id(buyer_mapping_id);
contMgmt.setSchId(schId);
contMgmt.setNomDt(nomDt);
contMgmt.setRevNo(revNo);
contMgmt.setSchRevNo(schRevNo);
contMgmt.setContract_type(contract_type);

contMgmt.init();

contract_type = contMgmt.getContract_type();
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
// Vector custidDST = contMgmt.getCustidDST();
Vector truckidDST = contMgmt.getTruckidDST();
Vector trucknmDST = contMgmt.getTrucknmDST();
Vector truckvolM3DST = contMgmt.getTruckvolM3DST();
Vector truckvolTonDST = contMgmt.getTruckvolTonDST(); 

Vector TscheduleVol = contMgmt.getTscheduleVol();
Vector TscheduleEne = contMgmt.getTscheduleEne();
Vector TscheduleDt = contMgmt.getTscheduleDt();
Vector TscheduleTm = contMgmt.getTscheduleTm();
Vector TscheduleRemark = contMgmt.getTscheduleRemark();

Vector schedulVol = contMgmt.getSchedulVol();
Vector schedulEne = contMgmt.getSchedulEne();
Vector schedulDt = contMgmt.getSchedulDt();
Vector schedulTm = contMgmt.getSchedulTm();
Vector VLoadedNxt_avail_days =contMgmt.getVLoadedNxt_avail_days();
Vector schedulRemark = contMgmt.getSchedulRemark();

Vector TNominatedOrNt= contMgmt.getTNominatedOrNt();

int truckNomCnt = contMgmt.getTruckNomCnt();
double conversion_factor_from_m3_to_mmbtu = 23.9;
double converssion_factor_from_m3_tonmmbtu = 0.3531466672;
//double convt_mmbtu_to_mt = 51.9; //SUJIT05MARCH2020
double convt_mmbtu_to_mt= (double) session.getAttribute("convt_mmbtu_to_mt");//HA20201229
double totalTon = 0;//SUJIT05MARCH2020
int truckCount = 0;

for(int i=0;i<TscheduleVol.size();i++){
    if(!TscheduleVol.elementAt(i).equals("")){
	truckCount++;
    }
}
Vector VTruckTransCd = contMgmt.getVTruckTransCd(); 
Vector VweeklyNomFlag = contMgmt.getVweeklyNomFlag();
Vector Vtruck_Inv_Flag = contMgmt.getVtruck_Inv_Flag();

Vector daily_Seller_Rev_No = contMgmt.getDaily_Seller_Rev_No();
Vector total_inv_qty_mmbtu = contMgmt.getTotal_inv_qty_mmbtu();
Vector total_inv_qty_scm = contMgmt.getTotal_inv_qty_scm();
Vector total_inv_qty_mt = contMgmt.getTotal_inv_qty_mt();

String tot_inv_mmbtu = contMgmt.getTot_inv_mmbtu();
String tot_inv_scm = contMgmt.getTot_inv_scm();
String tot_inv_mt = contMgmt.getTot_inv_mt();

Vector daily_Buyer_Rev_No= contMgmt.getDaily_Buyer_Rev_No();
Vector all_inv_gen_flg  = contMgmt.getAll_inv_gen_flg();
Vector max_nom_rev_no =  contMgmt.getMax_nom_rev_no();
Vector Vtruck_alloc_flag  = contMgmt.getVtruck_alloc_flag();
Vector Vtruck_alloc_mmbtu = contMgmt.getVtruck_alloc_mmbtu();
Vector Vtruck_alloc_scm = contMgmt.getVtruck_alloc_scm();
Vector Vtruck_alloc_mt = contMgmt.getVtruck_alloc_mt();
String truck_loaded = "N";
double loaded_qty = 0;

// System.out.println("all_inv_gen_flg SCH: "+all_inv_gen_flg); 

// System.out.println("VTruckTransCd**"+VTruckTransCd);
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
<input type="hidden" name="modUrl" value="<%=modUrl%>">

<input type="hidden" name="subType" value="">
<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
<input type="hidden"	name="update_permission" value="<%=update_permission%>">
<input type="hidden" name="option" value="submitCustomerSch">
<input type="hidden" name="mmbtu_to_tons" value="0.025219021687207"> 
<input type="hidden" name="mmbtu_to_m3" value="23.9">
<input type="hidden" name="m3_to_tonMMbtu" value="0.3531466672">
<input type="hidden" name="convt_mmbtu_to_mt" value="<%=convt_mmbtu_to_mt%>">
<input type="hidden" name="truck_size" value="<%=truckidDST.size() %>">

	<%if(Vtruck_Inv_Flag.contains("Y")) {%>
			<input type="hidden" name="inv_flg"  id="inv_flg" value="Y">
			<input type="text" name="tot_inv_mmbtu"  id="tot_inv_mmbtu" value="<%=tot_inv_mmbtu%>">
			
		<%}else{ %>
			<input type="hidden" name="inv_flg"  id="inv_flg"  value="N">
			<input type="hidden" name="tot_inv_mmbtu"  id="tot_inv_mmbtu" value="0">
		<%} %>
				
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
<%} %>

<div class="box-header with-border main-header" >
<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
	<div class="form-group mb-0 row">
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
			<label> Gas Day </label>
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
				<label> Energy Base </label>
			</div>
		</div>
	
		<div class="form-group row">
			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
				<div class="form-group input-group mb-0">
					<span class="input-group-addon">
						<input type="radio" name="rd" onClick="calculateSCM();" checked="checked"> GCV
					</span>
					<input type="text" class="form-control" name="gcv" value="<%=gcv%>" onBlur="checkNumber1(this,7,2);">
					<span class="input-group-addon">  kcal/SCM </span>
				</div>
			</div>	
		
			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
				<div class="form-group input-group mb-0">
					<span class="input-group-addon">
						<input type="radio" name="rd" onClick="calculateSCM();"> NCV
					</span>
					<input type="text" class="form-control" name="ncv" value="<%=ncv%>" onBlur="checkNumber1(this,7,2);">
					<span class="input-group-addon">  kcal/SCM </span>
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
		<th class="text-center" rowspan="3"  width="15%"> SELECT <br> (DATE - SEQ NO)  </th>
		<th class="text-center" rowspan="3" width="7%"> TIME<br> HH:MM  </th>
		<th class="text-center" rowspan="3"> SN/LOA <br> NO  </th>
		<th class="text-center" rowspan="3"> PLANT <br> NAME  </th>
		<th class="text-center" colspan="2"> DCQ QTY </th>
		<th class="text-center" colspan="2">QTY (MMBTU) </th>
		<th class="text-center" rowspan="3"> QTY <br> (SCM) </th>
		<th class="text-center" rowspan="3"> QTY <br> (MT) </th>
		<th class="text-center" rowspan="3"> Schedule <br> Truck</th>
	</tr>
	<tr class="info">
		<th class="text-center" rowspan="2" colspan ="1">MMBTU</th>
		<th class="text-center" rowspan="2" colspan ="1">MT</th>
	</tr>
	<tr class="info">
		<th class="text-center" rowspan="1" colspan="1"> Nominated (REV)  </th>
		<th class="text-center" rowspan="1" colspan="1"> Schedule </th>
	</tr>
</thead>
<%try{			
		String title="title = 'Please First Select Radio Buttton'";
		String btnStatus="disabled=disabled";
// 		System.out.println("daily_Buyer_Nom_Plant_Seq_No.size()***"+daily_Buyer_Nom_Plant_Seq_No.size());
		for(int j=0;j<daily_Buyer_Nom_Plant_Seq_No.size();j++){	
			if(j==(Integer.parseInt(indx))){
				title = "";
				if(title.equals("") && truckidDST.size() !=0){
					btnStatus = "";
				}else{
					btnStatus="disabled=disabled";
					title="title = 'No truck(s) available for the selected Customer!'";
				}
			}else{
				title = "title = 'Please First Select Radio Buttton'";
				btnStatus="disabled=disabled";
			}
		%>
		<tr <%if (j == (Integer.parseInt(indx))) {%> style="background-color: #fcf1cb" <%} %>>
		<td> 
			<div class="mt-5"><%//=VBuyer_Inv_Flag.elementAt(j) %>
			 <% if(all_inv_gen_flg.elementAt(j).equals("N")) { %>
				<input type="radio" id= "checkbox<%=j%>" onclick="check('<%=j%>','<%=daily_Seller_Nom_Cd.elementAt(j)%>','<%=VweeklyNomFlag.elementAt(j)%>')" name="chk" <%if(j==(Integer.parseInt(indx))){%> checked="checked" <%}%>>
			<%} else if(all_inv_gen_flg.elementAt(j).equals("Y")) {
				if(update_permission.equalsIgnoreCase("Y") && current_date.equals(gas_date)){%>
					<input title="Allowed on Special Permission" title="Invoice Generated" id= "checkbox<%=j%>" type="radio" onclick="check('<%=j%>','<%=daily_Seller_Nom_Cd.elementAt(j)%>','<%=VweeklyNomFlag.elementAt(j)%>')" name="chk" <%if(j==(Integer.parseInt(indx))){%> checked="checked" <%}%>>
				<%}else{ %>
					<input disabled title="Invoice Generated" id= "checkbox<%=j%>" type="radio" onclick="check('<%=j%>','<%=daily_Seller_Nom_Cd.elementAt(j)%>','<%=VweeklyNomFlag.elementAt(j)%>')" name="chk" <%if(j==(Integer.parseInt(indx))){%> checked="checked" <%}%>>
				<%} %>
			
				
			<%} %>	 
			<%-- <%if(VBuyer_Inv_Flag.elementAt(j).equals("Y")){ %>
				<font color="GREEN" title="Invoice Generated" ><b><%=(String)daily_Seller_Nom_Abbr.elementAt(j)%>&nbsp;&nbsp;<%=(String)daily_Seller_Gen_Day_With_Rev_No.elementAt(j)%></b></font>
				<%} else {%>
				<b><%=(String)daily_Seller_Nom_Abbr.elementAt(j)%>&nbsp;&nbsp;<%=(String)daily_Seller_Gen_Day_With_Rev_No.elementAt(j)%></b>
				<%} %> --%>
<%-- 				<input type="radio" id= "checkbox<%=j%>" onclick="check('<%=j%>','<%=daily_Seller_Nom_Cd.elementAt(j)%>','<%=VweeklyNomFlag.elementAt(j)%>')" name="chk" <%if(j==(Integer.parseInt(indx))){%> checked="checked" <%}%>> --%>
				<b><%=(String)daily_Seller_Nom_Abbr.elementAt(j)%>&nbsp;&nbsp;<%=(String)daily_Seller_Gen_Day_With_Rev_No.elementAt(j)%></b>
				
				<input type="hidden" name="dateWithRevNo" value="<%=(String)daily_Seller_Gen_Day_With_Rev_No.elementAt(j)%>">
				<input type="hidden" name="chk_flag" <%if(j==(Integer.parseInt(indx))){%> value="Y" <%}else{%> value="N" <%}%>>
				<input type="hidden" name="buyer_cd" value="<%=(String)daily_Seller_Nom_Cd.elementAt(j)%>">
				<input type="hidden" name="buyer_abbr" value="<%=(String)daily_Seller_Nom_Abbr.elementAt(j)%>">
				<input type="hidden" name="contract_type" id="contract_type<%=j %>" value="<%=(String)daily_Seller_Nom_Type.elementAt(j)%>">
			</div>
		</td>
			
		<td class="text-center"> 
			<div class="form-groupmb-0 mobile-v">
				<input style="text-align: center;" size="9" type="text" class="form-control w-65" name="hh_mm" id="hh_mm<%=j%>" maxlength="6" onblur="checkTime(this);" <% if(daily_Seller_Gen_Day_Time.elementAt(j).equals("")){ %> value="<%=formatter.format(time)%>" <%}else{ %>  value="<%=(String)daily_Seller_Gen_Day_Time.elementAt(j)%>" <%}%>  <%=title%> >
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
			<div class="mt-5" >	<%=(String)daily_Seller_Nom_Dcq.elementAt(j)%>
				<input type="hidden" name="qty_dcq" value="<%=(String)daily_Seller_Nom_Dcq.elementAt(j)%>" >
			</div>
		</td>
		<td class="text-right">
			<div class="mt-5" >	<%=(String)daily_Seller_Nom_Dcq_Mt.elementAt(j)%>
				<input type="hidden" name="qty_dcq_mt" value="<%=(String)daily_Seller_Nom_Dcq_Mt.elementAt(j)%>" >
			</div>
		</td>
		<td class="text-right">
			<div class="mt-5" >
				<%-- <%
				String total_mmbtu = "0";
				if(Integer.parseInt(daily_Buyer_Rev_No.elementAt(j)+"") > 1){
// 					System.out.println("daily_Buyer_Rev_No : "+total_inv_qty_mmbtu.elementAt(j)+"------"+daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)); 
// 					total_mmbtu = nf2.format(Double.parseDouble(total_inv_qty_mmbtu.elementAt(j)+"") + Double.parseDouble(daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)+""));
					if(max_nom_rev_no.elementAt(j).equals(daily_Buyer_Rev_No.elementAt(j))){
						total_mmbtu = nf2.format(Double.parseDouble(total_inv_qty_mmbtu.elementAt(j)+""));
					}else{
						total_mmbtu = nf2.format(Double.parseDouble(total_inv_qty_mmbtu.elementAt(j)+"") + Double.parseDouble(daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)+""));
					}
					%>
				<%}else{ 
					total_mmbtu = daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)+"";
				} 
				%> 
				 --%>
				<%
				String total_mmbtu = daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)+"";
// 				String total_mmbtu = nf2.format(Double.parseDouble(total_inv_qty_mmbtu.elementAt(j)+"") + Double.parseDouble(daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)+""));
			%>
				
				<%=total_mmbtu %>&nbsp;(<%=(String)daily_Buyer_Gen_Day_With_Rev_No.elementAt(j)%>)
				<input type="hidden" name="qty_buyer_mmbtu" value="<%=total_mmbtu%>" >
				<input type="hidden" name="daily_buyer_rev_no" value="<%=daily_Buyer_Rev_No.elementAt(j)%>" >
				
<%-- 				<input type="hidden" name="qty_buyer_mmbtu" value="<%=(String)daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)%>" > --%>
				<input type="hidden" name="genDayWith_rev_no" value="<%=(String)daily_Buyer_Gen_Day_With_Rev_No.elementAt(j)%>">
			</div>
		</td>
		<%String tot_sch_mmbtu = "0",tot_sch_scm= "0";String tot_sch_mt = "0";
		/* Hiren_20230222
		if(Integer.parseInt(daily_Seller_Rev_No.elementAt(j)+"") > 1){
			
			if(max_nom_rev_no.elementAt(j).equals(daily_Buyer_Rev_No.elementAt(j))){
				
				tot_sch_mmbtu = nf2.format(Double.parseDouble(total_inv_qty_mmbtu.elementAt(j)+""));
				tot_sch_scm = nf2.format(Double.parseDouble(total_inv_qty_scm.elementAt(j)+""));
			}else{
				
				tot_sch_mmbtu = nf2.format(Double.parseDouble(total_inv_qty_mmbtu.elementAt(j)+"") + Double.parseDouble(daily_Seller_Nom_Qty_Mmbtu.elementAt(j)+""));
// 				tot_sch_mmbtu = nf2.format(Double.parseDouble(daily_Seller_Nom_Qty_Mmbtu.elementAt(j)+""));
				tot_sch_scm = nf2.format(Double.parseDouble(total_inv_qty_scm.elementAt(j)+"") + Double.parseDouble(daily_Seller_Nom_Qty_Scm.elementAt(j)+""));
			}
			
// 			tot_sch_mmbtu = nf2.format(Double.parseDouble(total_inv_qty_mmbtu.elementAt(j)+"") + Double.parseDouble(daily_Seller_Nom_Qty_Mmbtu.elementAt(j)+""));
// 			tot_sch_scm = nf2.format(Double.parseDouble(total_inv_qty_scm.elementAt(j)+"") + Double.parseDouble(daily_Seller_Nom_Qty_Scm.elementAt(j)+""));
		}else{
			tot_sch_mmbtu = nf2.format(Double.parseDouble(daily_Seller_Nom_Qty_Mmbtu.elementAt(j)+""));
			tot_sch_scm = nf2.format(Double.parseDouble(daily_Seller_Nom_Qty_Scm.elementAt(j)+""));
		} */
			tot_sch_mmbtu = nf2.format(Double.parseDouble(daily_Seller_Nom_Qty_Mmbtu.elementAt(j)+""));
			tot_sch_scm = nf2.format(Double.parseDouble(daily_Seller_Nom_Qty_Scm.elementAt(j)+""));
		%>
		<td><div class="form-groupmb-0 mobile-v"><input type="text" class="form-control w-65" name="qty_mmbtu" id="qty_mmbtu<%=j%>" value="<%=tot_sch_mmbtu%>"  maxlength="10" style="text-align:right" onblur="calculateSCM_TOTAL(this,'<%=j%>','');" <%=title %> onkeyup="checkNumber1(this,9,2);checkForNumberWithDot(this)"></div></td>
		<td><div class="form-groupmb-0 mobile-v"><input type="text" class="form-control w-65" name="qty_scm" value="<%=tot_sch_scm%>" style="text-align:right" readonly="readonly"></div></td>
		
		<td><%if(!daily_Seller_Nom_Qty_Mmbtu.elementAt(j).equals("0")){ //SUJIT05MARCH2020
				 double schdulTon = Double.parseDouble(daily_Seller_Nom_Qty_Mmbtu.elementAt(j).toString()) / convt_mmbtu_to_mt;
				 totalTon = totalTon + schdulTon;
				 if(Integer.parseInt(daily_Seller_Rev_No.elementAt(j)+"") > 1){
					 
					 if(max_nom_rev_no.elementAt(j).equals(daily_Buyer_Rev_No.elementAt(j))){
						 tot_sch_mt = nf.format(Double.parseDouble(total_inv_qty_mt.elementAt(j)+""));
					}else{
						tot_sch_mt = nf.format(Double.parseDouble(total_inv_qty_mt.elementAt(j)+"") + totalTon);
					}
				 	
				 }else{
					 
					 tot_sch_mt = nf.format(totalTon);
				 }	
				 %>
				 <div class="form-groupmb-0 mobile-v"><input type="text" class="form-control w-65" id="qty_ton<%=j%>" name="qty_ton" value="<%=tot_sch_mt%>" style="text-align:right" readonly="readonly" ></div>
			<%}else{%>
				 <div class="form-groupmb-0 mobile-v"><input type="text" class="form-control w-65" id="qty_ton<%=j%>" name="qty_ton" value="" style="text-align:right" readonly="readonly" ></div>
			<%}%>
			<input type="hidden" name="total_loaded_mmbtu" id="total_loaded_mmbtu<%=j %>" value="<%=total_inv_qty_mmbtu.elementAt(j)%>">
		</td>
		<td><button type="button" class="btn btn-info"  <%=title%>  <%=btnStatus%> onclick="setScheduledQty('<%=j%>','<%=(String)daily_Seller_Nom_Abbr.elementAt(j)%>','<%=(String)daily_Seller_Nom_Contract_Type.elementAt(j)+"-"+(String)daily_Seller_Nom_Sn_No.elementAt(j)%>','<%=gas_date%>','<%=(String)daily_Buyer_Nom_Plant_Seq_Abbr.elementAt(j)%>','<%=total_inv_qty_mmbtu.elementAt(j)%>')">Select Truck</button></td>
			<!-- data-target="#myTrukSchedul" data-toggle="modal" -->
		</tr>			
			<%	++index;
		} %>
	
	 <%	if(daily_Buyer_Nom_Plant_Seq_Abbr.size()>0){%>
	<tr class="success">
		<th class="text-center" colspan="4"><div class="mt-5"> TOTAL SCHEDULED QTY</div> </th>
	
		<th>
			<div class="form-groupmb-0 mobile-v">
<%-- 				<input type="text" class="form-control w-100" name="total_dcq_qty" value="<%=daily_Total_Dcq_Seller_Nom%>" readonly="readonly" style="text-align: right;"> --%>
			</div>
		</th>
		
		<th> 
			<div class="form-groupmb-0 mobile-v">
<%-- 				<input type="text" class="form-control w-100" name="total_buyer_mmbtu_qty" value="<%=daily_Total_Mmbtu%>"  readonly="readonly" style="text-align: right;"> --%>
			</div>
		</th>
		
		<th> 
			<div class="form-groupmb-0 mobile-v">
				<input type="text" class="form-control w-100" name="total_buyer_mmbtu_qty" value="<%=daily_Total_Mmbtu%>"  readonly="readonly" style="text-align: right;">
			</div>
		</th>
		
		<th> 
			<div class="form-groupmb-0 mobile-v">
				<input type="text" class="form-control w-100" name="total_mmbtu_qty" value="<%=daily_Total_Mmbtu_Seller_Nom%>" readonly="readonly" style="text-align: right;">
			</div>
		</th>
			
		<th> 
			<div class="form-groupmb-0 mobile-v">
				<input type="text" class="form-control w-100" name="total_scm_qty" value="<%=daily_Total_Scm_Seller_Nom%>" readonly="readonly" style="text-align: right;">
			</div>
			
			<input type="hidden" name="index_count" value="<%=index%>">
			<input type="hidden" name="percentage_of_dcq" value="<%=percentage_of_dcq%>">
		</th>
		<th> 
			<div class="form-groupmb-0 mobile-v"><!-- SUJIT05MARCH2020 -->
				<input type="text" class="form-control w-100" name="total_ton_qty" <%if(totalTon == 0.0){%> value="" <%}else{%> value="<%=nf.format(totalTon)%>" <%}%> readonly="readonly" style="text-align: right;">
			</div>
		</th>
		<th></th>
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

<!-- Modal -->
<%if(!selSchedulCust.equals("")){ %>
<div class="modal fade" id="myTrukSchedul" tab-index=-1 role="dialog" aria-labelledby="myTrukSchedulLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document" style="width:90%">
		<div class="modal-content">
			<div class="modal-header">
				<h4 id="myTrukSchedulLabel" style="font-size: large;"><span id="selCustAbr" >  </span> </h4>
				<button type="button" id="closeModalBtn" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>		
			</div>
			<div class="modal-body">
				<div class="box-body table-responsive no-padding">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th colspan="3" style="vertical-align:top; font-size: medium;">Total Qty:</th>
								
								<th colspan="3">
									<div class="form-group row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group input-group mb-0">
												<input type="text" class="form-control text-right" id="schedul_qty_mmbtu" disabled="disabled" />
												<span class="input-group-addon">MMBTU</span>
											</div>
										</div>
									</div>
								</th>
								<th colspan="3">
									<div class="form-group row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group input-group mb-0">
												<input type="text" class="form-control text-right" id="schedul_qty_scm" disabled="disabled"/>
												<span class="input-group-addon">SCM</span>
											</div>
										</div>
									</div>
								</th>
								<th colspan="3">
									<div class="form-group row">
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="form-group input-group mb-0">
												<input type="text" class="form-control text-right" id="schedul_qty_ton" disabled="disabled"/>
												<span class="input-group-addon">MT</span>
											</div>
										</div>
									</div>
								</th>
							</tr>
							<tr>
								<th colspan="9">
									Balance Qty : <input type="text" id="" name="scheduleBal_mmbtu" value="" style="border: 0px;" readonly="readonly"/> MMBTU
								</th>
							</tr>
							
							<tr>
								<th>
									<th colspan="3" style="font-size: 16px;padding:">Total Trucks :<%=trucknmDST.size()%></th>
									<th colspan="3" style="font-size: 16px;padding:">Booked Trucks :<%=truckCount%></th>
									<th colspan="2" style="font-size: 16px;padding:">Available Trucks :<%=trucknmDST.size() - truckCount %></th>
								</th>
							</tr>
							

							 <tr class="info" style="vertical-align:middle;">
								<th class="text-center" rowspan="2" colspan="2" style="vertical-align: middle;" width="13%">Truck No.</th>
								<th class="text-center" colspan="3">Truck Capacity</th>
								<th class="text-center" colspan="2">Nominated Qty.</th>
								<th class="text-center" colspan="2">Truck Schedule Qty.</th>
								<th class="text-center" colspan="2" >Schedule</th>
								<th class="text-center" rowspan="2">Next Available (in Days)</th>
								<th class="text-center" rowspan="2" style="vertical-align: middle;">Remarks</th>
							</tr>
							<tr class="info">
								<th class="text-center">MMBTU</th>
								<th class="text-center" width="5%">&#13221;</th>
								<th class="text-center">MT</th>
								<th class="text-center">MMBTU</th>
								<th class="text-center">MT</th>
								<th class="text-center">MMBTU</th>
								<th class="text-center">MT</th>
								<th class="text-center" >Date</th>
								<th class="text-center">Time</th>
							</tr> 
						</thead>
						<tbody>
						<%
						/* System.out.println("truckvolM3DST.size()*****"+truckvolM3DST.size());
						System.out.println("truckidDST.size()***"+truckidDST.size());
						System.out.println("VTruckTransCd.size()****"+VTruckTransCd.size());
						System.out.println("truckvolM3DST.size()****"+truckvolM3DST.size());
						System.out.println("TscheduleVol.size()****"+TscheduleVol.size());
						System.out.println("schedulVol.size()****"+schedulVol.size());
						System.out.println("schedulDt.size()****"+schedulDt.size());
						System.out.println("schedulRemark.size()****"+schedulRemark.size());
						 */
						for(int j=0;j<truckidDST.size();j++){
							
							String buyerComment="";
							String sellerComment="";
							
// 							System.out.println("buyerComment-------"+buyerComment);
// 							System.out.println("sellerComment-------"+sellerComment);
							
							double convTruckmmbtu = Double.parseDouble(truckvolM3DST.elementAt(j).toString()) * conversion_factor_from_m3_to_mmbtu;
						 %>
						 
						  
							<%
// 							System.out.println("Vtruck_alloc_flag.elementAt(j)-------"+Vtruck_alloc_flag.elementAt(j));
							if(Vtruck_Inv_Flag.elementAt(j).toString().equalsIgnoreCase("Y")){ %>
								<tr style="background-color: #56B973;" title="Invoice Generted!!">
							<%}else if(Vtruck_Inv_Flag.elementAt(j).toString().equalsIgnoreCase("N") && Vtruck_alloc_flag.elementAt(j).toString().equalsIgnoreCase("Y") ){
									truck_loaded = "Y";
									loaded_qty+=Double.parseDouble(Vtruck_alloc_mmbtu.elementAt(j)+"");
								%>
								<tr style="background-color: #ffc966;" title="Truck Loading Done!!">		
							<%}else{ %>
								<tr>
							<%} %>					
								<td <%if(Vtruck_Inv_Flag.elementAt(j).toString().equalsIgnoreCase("Y") || Vtruck_alloc_flag.elementAt(j).toString().equalsIgnoreCase("Y")) {%>
									style="visibility: hidden;"
								<%} %>
								>
									<input type="checkbox" name="chekboxs" id="scheduleChecks<%=j%>" onclick="scheduleQty('<%=j%>')">
									<input type="hidden" name="checkFlag" id="chkSchedulFlag<%=j%>" value="N">
									<input type="hidden" name="Vtrans_cd" value="<%=VTruckTransCd.elementAt(j)%>">
								</td>
								<td>
									<div class="mt-5"><%=trucknmDST.elementAt(j)%>
										<input type="hidden" name="trucknm" value="<%=trucknmDST.elementAt(j)%>" style="border: none;">
									</div>
								</td>
								<td>
									<div class="mt-5 text-right"><%=Math.round(convTruckmmbtu)%></div>
									<input type="hidden" name="TCapMMbtu" id="TCapMMbtus<%=j%>" value="<%=Math.round(convTruckmmbtu)%>" style="border: none;">
								</td>
								<td>
									<div class="mt-5 text-right"><%=truckvolM3DST.elementAt(j)%></div>
									<input type="hidden" name="TCapM3" id="TCapM3s<%=j%>" value="<%=truckvolM3DST.elementAt(j)%>" style="border: none;">
								</td>
								<td>
									<div class="mt-5 text-right"><%=nf.format( Double.parseDouble(truckvolTonDST.elementAt(j).toString()) ) %></div>
									<input type="hidden" name="TCapTon" id="TCapTon<%=j%>" value="<%=truckvolTonDST.elementAt(j)%>" style="border: none;">
								</td>
								<%
// 								System.out.println("TscheduleVol.elementAt(j)***"+TscheduleVol.elementAt(j));
								if(!TscheduleVol.elementAt(j).equals("")){ 
								     double loadedTon = Double.parseDouble(TscheduleVol.elementAt(j).toString()) / convt_mmbtu_to_mt;%>
									  <td>
										 <div class="mt-5 text-right">
											 <%=TscheduleVol.elementAt(j)%>
											 <input type="hidden" class="form-control text-right" name="loaded_mmbtu" id="loaded_mmbtu<%=j%>" value="<%=TscheduleVol.elementAt(j)%>">
										 </div>
									 </td>
									 <td>
										<div class="mt-5 text-right">
											<%=nf.format(loadedTon)%>							  
											<input type="hidden" class="form-control text-right" name="loaded_ton" id="loaded_ton<%=j%>" value="<%=nf.format(loadedTon)%>" style="border: none;">
										</div>
									 </td>
								<%}else{%>
								     <td>
									    <div class="mt-5 text-right">
											<input type="hidden" class="form-control text-right" name="loaded_mmbtu" id="loaded_mmbtu<%=j%>" value="0">
										</div>
								     </td>
									 <td>
									     <div class="mt-5 text-right">
										    <input type="hidden" class="form-control text-right" name="loaded_ton" id="loaded_ton<%=j%>" value="" style="border: none;">
									     </div>
								      </td>								    
								 <%}%>
								<td>
									<div class="form-group mb-0">
										<input type="text" class="form-control text-right" name="schedule_mmbtu" id="schedule_mmbtu<%=j%>" value="<%=schedulVol.elementAt(j)%>" onchange="validateScheduleQty('<%=j%>')" onkeyup="checkNumber1(this,9,2);checkForNumberWithDot(this)">
										<input type="hidden" class="form-control text-right" name="already_schedule_mmbtu" id="already_schedule_mmbtu<%=j%>" value="<%=schedulVol.elementAt(j)%>">
									</div>
								</td>
<%-- 								<%System.out.println("schedulVol.elementAt(j)&&&&"+schedulVol.elementAt(j)); %> --%>
								<td>
									<div class="form-group mb-0">
										<%if(!schedulVol.elementAt(j).equals("")){	
											double scheduleTon = Double.parseDouble(schedulVol.elementAt(j).toString()) / convt_mmbtu_to_mt;%>

											<input type="text" class="form-control text-right" name="schedule_ton" id="schedule_ton<%=j%>" value="<%=nf.format(scheduleTon)%>" onblur="checkNumber1(this,9,2);" readonly="readonly">
											<input type="hidden" class="form-control text-right" name="allready_schedule_ton" id="allready_schedule_ton<%=j%>" value="<%=nf.format(scheduleTon)%>">
										<%}else{%>
											<input type="text" class="form-control text-right" name="schedule_ton" id="schedule_ton<%=j%>" onblur="checkNumber1(this,9,2);" value="" onchange="converToMmbtu('<%=j%>')" readonly="readonly">
										<%}%>
									</div>
								</td>
								<td>
									<div class="input-group date datepick" id="datetimepicker3">
										<input type="text" class="form-control datepick" name="schedul_date" id="d3<%=j%>" maxlength="11"
										 	<%if(schedulDt.elementAt(j).equals("")){ %>
										 		value="<%=TscheduleDt.elementAt(j) %>" 
										 	<% }else{ %> 
										 		value="<%=schedulDt.elementAt(j) %>" 
										 	<% } %>/>
										<span class="input-group-addon">
											<i class="fa fa-calendar"></i>
										</span>
									</div>
								</td>
								<td>
									<div class="form-group mb-3">
										<input type="text" maxlength="5" class="form-control text-center" name="schedule_time" id="schedule_times<%=j%>" <% if(schedulTm.elementAt(j).equals("")){%>value="<%=formatter.format(time)%>" <%}else{ %> value="<%=schedulTm.elementAt(j)%>" <%} %> >
									</div>
								</td>
								<td>
									<div class="form-group mb-3">
										<input type="text" maxlength="2" size="3" class="form-control text-center" name="next_avail_days" id="next_avail_days<%=j %>" value="<%=VLoadedNxt_avail_days.elementAt(j)%>" readonly="readonly">
									</div>
								</td>
								<td>
									<div class="form-grou mb-3">
										<textarea class="form-control" name="remark" rows="3" cols="20" ><%=schedulRemark.elementAt(j) %></textarea>
									</div>
								</td>
								<%}%>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-success" onclick="ScheduleTrucks();" >Submit</button>
			</div>
		</div>
	</div>
</div>

<% }%>
<!-- Modal End -->

<input type="hidden" name="truck_loaded_flag" id="truck_loaded_flag" value="<%=truck_loaded%>">
<input type="hidden" name="loaded_qty" id="loaded_qty"  value="<%=loaded_qty%>">
<input type="hidden" name="selIndx" id="selIndx"  value="">
</form>

<div class="box-footer">
	<div class="row">
		<div class="col-sm-12 text-right">
			<button type="button" class="btn btn-warning" name="reSetPage" value="Reset" onClick="refreshPage();"> Reset </button>
				<%String dirSub="",title="";
				if(truckidDST.size() == 0 || truckNomCnt == 0) {%>
					
				<%}else{ 
					dirSub = "disabled=disabled";
					title="Submission allowed from truck selection!";
				}%>
			
			<button type="button" class="btn btn-success" <%//=dirSub %> title="<%=title %>" name="save" value="noTruck" onClick="doSubmit(this.value);"> Submit </button>
		</div>
	</div>
</div>
    
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
