<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.util.*"%>
<%@ page import="java.net.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.seipl.hazira.dlng.contract_mgmt.DataBean_DailyBuyer_NominationV2"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../sess/Expire.jsp"%>
<title>TLU</title>
<link rel="stylesheet"
	href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/tlu.css">

<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
<script type="text/javascript" src="../js/sweetalert.min.js"></script>

<script type="text/javascript">
function calculateSCM_TOTAL(obj,ind,inner_ind)
{
	var qtyFlg = true;
	var tot_inv_mmbtu = document.getElementById('tot_inv_mmbtu').value;
	var truck_loaded_flag = document.getElementById('truck_loaded_flag').value;
	var loaded_qty = document.getElementById('loaded_qty').value;
	var oa_flag = document.getElementById('oa_flag').value;
	var bal_qty = "";
	
	if(document.forms[0].inv_flg.value == 'Y' ){
		if(parseFloat(obj.value) < parseFloat(tot_inv_mmbtu) ){
			alert('Entered Quantity shoul be greater than Invoiced Quantity: '+tot_inv_mmbtu+" MMBTU");
			qtyFlg = false;
			obj.value = "";
		}else{
			
			document.forms[0].actual_mmbtu.value = parseFloat(obj.value) - parseFloat(tot_inv_mmbtu);
			document.forms[0].balance_mmbtu.value = parseFloat(obj.value) - parseFloat(tot_inv_mmbtu);
		}
	}else if(truck_loaded_flag == 'Y'){
		if(parseFloat(obj.value) < parseFloat(loaded_qty) ){
					
			alert('Entered Quantity shoul be greater than Truck Loaded Quantity: '+loaded_qty+" MMBTU");
			qtyFlg = false;
			obj.value = "";
		}else{
			
			document.forms[0].actual_mmbtu.value = parseFloat(obj.value) - parseFloat(loaded_qty);
			document.forms[0].balance_mmbtu.value = parseFloat(obj.value) - parseFloat(loaded_qty);
		}
	}else{
		
		document.forms[0].actual_mmbtu.value = obj.value;//HA20200205
		document.forms[0].balance_mmbtu.value = obj.value;//HA20200205
	}
	/* checking for advance payment */
	var balance_adv_amt = document.forms[0].balance_adv_amt.value;
// 	alert(balance_adv_amt)
	var Sales_Rate = document.forms[0].Sales_Rate.value;
	var exg_rate = document.forms[0].exg_rate.value;
	var tax_rate = document.forms[0].tax_rate.value;
	var nominated_qty = document.getElementById('nominated_qty'+ind).value;
	var qty_mmbtu  = document.getElementById('qty_mmbtu'+ind).value;
	var fin_qty = "0";
	var allowed_nom = "N";
	var net_amt = "0";
	
// 	alert(nominated_qty+"*****"+qty_mmbtu)
	if(nominated_qty!='' && parseFloat(nominated_qty) > 0){
		if(qty_mmbtu!='' && parseFloat(qty_mmbtu) > 0){
			if(parseFloat(qty_mmbtu) > parseFloat(nominated_qty)){
			
				fin_qty = parseFloat(qty_mmbtu) - parseFloat(nominated_qty);
				var gross_amt = parseFloat(fin_qty) *  parseFloat(Sales_Rate) *  parseFloat(exg_rate);
				var tax_amt = (parseFloat(gross_amt) * parseFloat(tax_rate)) / 100 ;
				net_amt = parseFloat(gross_amt) + parseFloat(tax_amt); 
// 				alert(balance_adv_amt+"*****"+net_amt)
				
				if((parseFloat(balance_adv_amt) >= parseFloat(net_amt)) || oa_flag == "Y"){
					allowed_nom = "Y";
				}else{
					allowed_nom = "N";
				}
				
			}else if((parseFloat(qty_mmbtu) < parseFloat(nominated_qty)) || oa_flag == "Y"){
				
				allowed_nom = "Y";				
				
				/* fin_qty = parseFloat(nominated_qty) - parseFloat(qty_mmbtu);
				var gross_amt = parseFloat(fin_qty) *  parseFloat(Sales_Rate) *  parseFloat(exg_rate);
				var tax_amt = (parseFloat(gross_amt) * parseFloat(tax_rate)) / 100 ;
				var net_amt = parseFloat(gross_amt) + parseFloat(tax_amt); */                                         
			}
		}
	}else{
		if(qty_mmbtu!='' && parseFloat(qty_mmbtu) > 0){

			fin_qty = qty_mmbtu;
			
			var gross_amt = parseFloat(fin_qty) *  parseFloat(Sales_Rate) *  parseFloat(exg_rate);
			var tax_amt = (parseFloat(gross_amt) * parseFloat(tax_rate)) / 100 ;
			net_amt = parseFloat(gross_amt) + parseFloat(tax_amt);                                         
			
			if((parseFloat(balance_adv_amt) >= parseFloat(net_amt)) || oa_flag == "Y"){
				allowed_nom = "Y";
			}else{
				allowed_nom = "N";
			}
		}
	}
// 	alert(net_amt+"------"+allowed_nom+"----"+balance_adv_amt);
	if(allowed_nom == 'N'){
		
		var bal_qty = (parseFloat(balance_adv_amt)) / ((parseFloat(Sales_Rate) *  parseFloat(exg_rate) * (1+(tax_rate/100))));
		
		alert('As per balance advance amount you cannot exceed quantity more than :'+Math.round(bal_qty)+' MMBTU');
		document.getElementById('qty_mmbtu'+ind).value = '';
		document.getElementById('qty_scm'+ind).value = '';
		document.getElementById('qty_tons'+ind).value = '';
	}
	//////////////////////////////////////////
	if(qtyFlg && allowed_nom == 'Y'){
		
		var decision_flag = true;
		var decision = true;
		var qty_mmbtu = 0;
		var index = 0;
		var inner_index = 0;
		var final_total = 0;
		var i = 0;
		var j = 0;
		var z = 0;
		var qty = "";
		var qtyTon = "";
		var round_upto_digits = 2;
		
		var index_count = parseInt(""+document.forms[0].index_count.value);
		var percentage_of_dcq = parseFloat(""+document.forms[0].percentage_of_dcq.value);
		var MIN_mmbtu_with_dcq = 0;
		var MAX_mmbtu_with_dcq = 0;
		var dcq = 0;
		var mdcq = 0;
		var total_sn_qty = 0;
		var bal_qty = 0;
		var contract_type = "";
		var buyer_cd = "0";
		var buyer_abbr = "";
		var buyer_fgsa_cd = "0";
		var buyer_sn_cd = "0";
		var mdcqval="MDCQ";
		
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
		
		""+round(((parseFloat(""+qty)*multiplying_factor)/(gcv*deviding_factor)),0);
		
		/*  HA 20191221 MMBTU to Tons Conversion 
		var mmbtu_to_tons_convrt = document.forms[0].m3_to_tonMMbtu.value;
		var qtyTons = parseFloat(mmbtu_to_tons_convrt) * (parseFloat(obj.value) / parseFloat(document.forms[0].mmbtu_to_m3.value));
		document.getElementById('qty_tons'+ind).value = Math.round(qtyTons); */
		
		var convt_mmbtu_to_mt = document.forms[0].convt_mmbtu_to_mt.value;
		var qtyTons = round(parseFloat(obj.value) / parseFloat(convt_mmbtu_to_mt),2);
		document.getElementById('qty_tons'+ind).value = qtyTons;
		
		if(obj.value!=null && obj.value!='' && obj.value!=' ' && obj.value!='  ')
		{
			qty_mmbtu = parseFloat(obj.value);
			index = parseInt(ind);
			decision_flag = true;
			if(index_count==1)
			{
				dcq = parseFloat(""+document.forms[0].qty_dcq.value);
				mdcq = parseFloat(""+document.forms[0].qty_mdcq.value);
				total_sn_qty = parseFloat(""+document.forms[0].qty_mmbtu.value);
				bal_qty = parseFloat(""+document.forms[0].qty_bal.value);
				contract_type = document.forms[0].contract_type.value;
				buyer_cd = document.forms[0].buyer_cd.value;
				buyer_abbr = document.forms[0].buyer_abbr.value;
				buyer_fgsa_cd = document.forms[0].buyer_fgsa_no.value;
				buyer_sn_cd = document.forms[0].buyer_sn_no.value;
				
				var cont_type = "CN";
				cont_type = "CN";
				mdcqval = "MCSOC";
				
				if(total_sn_qty > bal_qty)
				{
					if(bal_qty<0)
					{
						alert("Buyer Nomination For --> "+buyer_abbr+" - "+cont_type+"("+buyer_sn_cd+") QTY: "+total_sn_qty+" MMBTU,\nCan Not Exceed (-Ve) Balance Qty: (-)"+(-1*bal_qty));				
					}
					else
					{
						alert("Buyer Nomination For --> "+buyer_abbr+" - "+cont_type+"("+buyer_sn_cd+") QTY: "+total_sn_qty+" MMBTU,\nCan Not Exceed Available Balance Qty: "+bal_qty);
					}
					decision_flag = false;
				}
				
				if(total_sn_qty>mdcq)
				{
					alert("Buyer Nomination For --> "+buyer_abbr+" - "+cont_type+"("+buyer_sn_cd+") QTY: "+total_sn_qty+" MMBTU, "+mdcqval+" Qty: "+mdcq+" MMBTU !!!");
				}
			}
			else if(index_count>1)
			{
				dcq = parseFloat(""+document.forms[0].qty_dcq[index].value);
				mdcq = parseFloat(""+document.forms[0].qty_mdcq[index].value);
				bal_qty = parseFloat(""+document.forms[0].qty_bal[index].value);
	// 			alert(bal_qty)
				total_sn_qty = 0;
				contract_type = document.forms[0].contract_type[index].value;
				buyer_cd = document.forms[0].buyer_cd[index].value;
				buyer_abbr = document.forms[0].buyer_abbr[index].value;
				buyer_fgsa_cd = document.forms[0].buyer_fgsa_no[index].value;
				buyer_sn_cd = document.forms[0].buyer_sn_no[index].value;
				
				var cont_type = "CN";
				mdcqval="MCSOC";
				
				for(z=0; z<index_count; z++)
				{
					if(document.forms[0].contract_type[z].value==contract_type && 
					document.forms[0].buyer_cd[z].value==buyer_cd && 
					document.forms[0].buyer_fgsa_no[z].value==buyer_fgsa_cd && 
					document.forms[0].buyer_sn_no[z].value==buyer_sn_cd)
					{
						var sn_qty = ""+document.forms[0].qty_mmbtu[z].value;
						if(sn_qty!=null && sn_qty!='' && sn_qty!=' ' && sn_qty!='  ' && sn_qty!='0' && sn_qty!='0.0' && sn_qty!='0.00')
						{
							total_sn_qty += parseFloat(""+sn_qty);
						}
					}
				}
				if(total_sn_qty>bal_qty)
				{
					if(bal_qty<0)
					{
						alert("Buyer Nomination For --> "+buyer_abbr+" - "+cont_type+"("+buyer_sn_cd+") QTY: "+total_sn_qty+" MMBTU,\nCan Not Exceed (-Ve) Balance Qty: (-)"+(-1*bal_qty));				
					}
					else
					{
						alert("Buyer Nomination For --> "+buyer_abbr+" - "+cont_type+"("+buyer_sn_cd+") QTY: "+total_sn_qty+" MMBTU,\nCan Not Exceed Available Balance Qty: "+bal_qty);
					}
					decision_flag = false;
				}
				if(total_sn_qty>mdcq)
				{
					alert("Buyer Nomination For --> "+buyer_abbr+" - "+cont_type+"("+buyer_sn_cd+") QTY: "+total_sn_qty+" MMBTU, "+mdcqval+" Qty: "+mdcq+" MMBTU !!!");
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
				document.forms[0].qty_tons.value = '';
			}
			else if(index_count>1)
			{
				document.forms[0].qty_tons[index].value = '';
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
			document.getElementById('qty_tons'+ind).value='';
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
				document.forms[0].qty_tons.value = '';
			}
			else if(index_count>1)
			{
				document.forms[0].qty_tons[index].value = '';
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
			document.getElementById('qty_tons'+ind).value='';
		}	
		
		if(obj.value!=null && obj.value!='' && obj.value!=' ' && obj.value!='  ')
		{
			qty_mmbtu = parseFloat(obj.value);
			index = parseInt(ind);
			
			if(parseInt(index_count)==1) {
				if(qty_mmbtu!=null && qty_mmbtu!='' && qty_mmbtu!=' ' && qty_mmbtu!='  ')
				{
					document.forms[0].total_mmbtu_qty.value = round(qty_mmbtu,round_upto_digits);
				}
			}
			else 
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
					document.forms[0].total_scm_qty.value = ""+round(((parseFloat(""+qty)*multiplying_factor)/(gcv*deviding_factor)),0);
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
				qtyTon = document.forms[0].qty_tons.value;
				if(qtyTon!=null && qtyTon!='' && qtyTon!=' ' && qtyTon!='  ')
				{
					document.forms[0].total_ton_qty.value = round(qtyTon,0);
				}
			}
			else if(index_count>1)
			{   final_total = 0;
				for(j=0;j<index_count;j++)
				{   
					qtyTon = document.forms[0].qty_tons[j].value;
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
		qtyTon = document.forms[0].qty_tons.value;
		if(qtyTon!=null && qtyTon!='' && qtyTon!=' ' && qtyTon!='  ')
		{
			document.forms[0].total_ton_qty.value = round(qtyTon,0);
		}
	}
	else if(index_count>1)
	{   final_total = 0;
		for(j=0;j<index_count;j++)
		{
			qtyTon = document.forms[0].qty_tons[j].value;
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

function check(cust_id,indx,nom_qty,custPlant_cd,DelvBase,weeklyNomFlag,contTyp){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	var daily_adv_mapping_id = document.getElementById("daily_adv_mapping_id"+indx).value;
	var gas_date = document.forms[0].gas_date.value;
	var gen_date = document.forms[0].gen_date.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var update_permission = document.forms[0].update_permission.value;
	
	var gcv = parseFloat(""+document.forms[0].gcv.value);
	var ncv = parseFloat(""+document.forms[0].ncv.value);
	var ncv_gcv = "0";
	var deviding_factor = parseFloat("1");
	
	if(document.forms[0].rd[0].checked)
	{
		ncv_gcv = parseFloat(""+document.forms[0].gcv.value);
		
	}
	else if(document.forms[0].rd[1].checked)
	{
		ncv_gcv = parseFloat(""+document.forms[0].ncv.value);
		
	}
	
	var temp = new Array();
	var nomDt='';
	var revNo='';
	var chkLen = document.forms[0].chk.length;
	var nomDay_wid_rev='';
	var cnf = false;
	nomDt = gas_date;
	if( chkLen == undefined || chkLen == 'undefined'){
		var buyer_mapping_id =  document.forms[0].buyer_mapping_id.value;
		var buyer_sn_no = document.forms[0].buyer_sn_no.value;
		var buyer_plant_seq_no = document.forms[0].buyer_plant_seq_no.value;
		var nomId =buyer_mapping_id+"-"+buyer_sn_no+"-"+buyer_plant_seq_no;
		nomDay_wid_rev = document.forms[0].nomDay_wid_rev.value; 
		
	}
	else if(chkLen > 1){
		var buyer_mapping_id =  document.forms[0].buyer_mapping_id[indx].value;
		var buyer_sn_no = document.forms[0].buyer_sn_no[indx].value;
		var buyer_plant_seq_no = document.forms[0].buyer_plant_seq_no[indx].value;
		var nomId =buyer_mapping_id+"-"+buyer_sn_no+"-"+buyer_plant_seq_no;
		nomDay_wid_rev = document.forms[0].nomDay_wid_rev[indx].value; 
		
	}
	
	if(nomDay_wid_rev!=''){
		var rmvPara = nomDay_wid_rev.substring(1,nomDay_wid_rev.length-1);
		 temp = rmvPara.split("-");
		 nomDt = gas_date;
		 revNo = temp[1];
	}
	
	/* alert("nomId : "+nomId)
	alert("temp : "+temp)
	alert("nomDt : "+nomDt)
	alert("revNo : "+revNo) */
	
	//alert(DelvBase);
	
	var url ="../master/frm_mst_buyerNomination.jsp?gas_date="+gas_date+"&gen_date="+gen_date+"&write_permission="+write_permission+
			"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&update_permission="+update_permission+ 
			"&cust_id="+cust_id+"&indx="+indx+"&nom_qty="+nom_qty+"&custPlant_cd="+custPlant_cd+"&delv_base="+DelvBase+"&buyer_mapping_id="+buyer_mapping_id+"&nomId="+nomId+"&nomDt="+nomDt+"&revNo="+revNo+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&contTyp="+contTyp+"&ncv_gcv="+ncv_gcv+"&daily_adv_mapping_id="+daily_adv_mapping_id;// alert(url);
	
	if(weeklyNomFlag == 'Y'){
		cnf = confirm("Weekly Buyer Nomination has done for :"+gas_date +", Still you want to continue ?");
	}else{
		cnf = true;
	}
	if(cnf){
		location.replace(url);		
	}else{
		document.getElementById('checkbox'+indx).checked= false;
	}
	
}

function setNominatedQty(indx,selCustAbr,contract_no,gas_date,plant){
	
	var flag = true;
	var msg = '';
	var hh_mm = document.getElementById('hh_mm'+indx).value;
	var qty_mmbtu = document.getElementById('qty_mmbtu'+indx).value;
	var gas_date = document.forms[0].gas_date.value;
	var gen_date = document.forms[0].gen_date.value;
	var gcv = document.forms[0].gcv.value;
	var ncv = document.forms[0].ncv.value;
	var firstStr = '';
	var firstWord = plant.split(" ")
	for(var i=0;i<firstWord.length;i++){
		firstStr = firstWord[0];
	}
	
	var truck_loaded_flag = document.getElementById('truck_loaded_flag').value;
	var loaded_qty = document.getElementById('loaded_qty').value;
	
	
	var nominated_qty_mmbtu = document.getElementById('qty_mmbtu'+indx).value;
	var conv_fact_m3 = document.getElementById('qty_scm'+indx).value;
	var conv_fact_m3_tonmmbbtu = document.forms[0].m3_to_tonMMbtu.value; //0.3531466672
	var conv_fact_m3 = document.forms[0].mmbtu_to_m3.value; //23.9
	var convt_mmbtu_to_mt = document.forms[0].convt_mmbtu_to_mt.value;
	
	qty_mmbtu = qty_mmbtu - 0;
	
	var cc = document.querySelector('#qty_mmbtu'+indx);
	cc.addEventListener('keyup',function(){
		this.value = this.value.replace(/[^0-9]/g,'');
	}); 

	
	  if(hh_mm==null || hh_mm=='00:00' || hh_mm=='' || hh_mm==' ' || hh_mm=='  ' || hh_mm=='  '){
	      flag = false;
	      msg+='- Please Enter HH:MM\n';
	  }
	  if(qty_mmbtu==null || qty_mmbtu=='' || qty_mmbtu=='0' || qty_mmbtu==' ' || qty_mmbtu=='  ' || qty_mmbtu=='  '){
	      flag = false;
	      msg+='- Please Enter Nominate Qty\n';
	  }
	  if(gas_date==null || gas_date=='0' || gas_date=='' || gas_date==' ' || gas_date=='  ' || gas_date=='  '){
		  msg += "- Please Enter The Nomination DATE For Nomination DAY\n";
		  flag = false;
	  }
	  if(gen_date==null || gen_date=='0' || gen_date=='' || gen_date==' ' || gen_date=='  ' || gen_date=='  '){
		  msg += "- Please Enter The GENERATION DATE For GEN DAY\n";
		  flag = false;
	  }
	  if(gcv==null || gcv=='0' || gcv=='' || gcv==' ' || gcv=='  ' || gcv=='   '){
		  msg += "- Please Enter The HEAT VALUE For GCV\n";
		  flag = false;
	  }
	  if(ncv==null || ncv=='0' || ncv=='' || ncv==' ' || ncv=='  ' || ncv=='   '){
		  msg += "- Please Enter The HEAT VALUE For NCV \n";
		  flag = false;
	  }
	  if(gas_date!=null && gas_date!='0' && gas_date!='' && gas_date!=' ' && gas_date!='  ' && gas_date!='  '){
		 if(gen_date!=null && gen_date!='0' && gen_date!='' && gen_date!=' ' && gen_date!='  ' && gen_date!='  '){
		 	var value = compareDate(gen_date,gas_date);
			if(value==1){
			   	msg += "- Generation Day Must Be Less Than Or Equal To Nomination Date\n";
			   	flag = false;
			}
		 }
	   }
      if(flag)
	  {
    	  if(document.forms[0].chk.length == undefined || document.forms[0].chk.length == 'undefined'){
				 document.getElementById('nominated_qty_mmbtu').value = document.forms[0].qty_mmbtu.value;
				 document.getElementById('nominated_qty_m3').value = document.forms[0].qty_scm.value;
				 document.getElementById('nominated_qty_ton').value = round(parseFloat(document.forms[0].qty_mmbtu.value) / parseFloat(convt_mmbtu_to_mt),2);
			 }else if(document.forms[0].chk.length > 1){
				 
				 document.getElementById('nominated_qty_mmbtu').value = document.forms[0].qty_mmbtu[indx].value;
				 document.getElementById('nominated_qty_m3').value = document.forms[0].qty_scm[indx].value;
  				 document.getElementById('nominated_qty_ton').value = round(parseFloat(document.forms[0].qty_mmbtu[indx].value) / parseFloat(convt_mmbtu_to_mt),2);
			 }
			 $('#myModal').modal('show');
			 	var space="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			 	document.getElementById('selCustAbr').innerHTML= "Gas Day : "+gas_date+space+"Truck selection for<span style='color:blue;'> "+selCustAbr+" "+"("+contract_no+")</span>&nbsp;&nbsp;"+firstStr;
			 	
			 	var tot_inv_mmbtu = document.getElementById('tot_inv_mmbtu').value;
				var bal_qty = "";
				var qtyFlg = true;
				
				if(document.forms[0].inv_flg.value == 'Y' ){ // if invoice generated
					
					document.forms[0].actual_mmbtu.value = parseFloat(nominated_qty_mmbtu) - parseFloat(tot_inv_mmbtu);
					document.forms[0].balance_mmbtu.value = parseFloat(nominated_qty_mmbtu) - parseFloat(tot_inv_mmbtu);
					
				}else if(truck_loaded_flag == "Y"){
// 					alert(nominated_qty_mmbtu)
					document.forms[0].actual_mmbtu.value = parseFloat(nominated_qty_mmbtu) - parseFloat(loaded_qty);
					document.forms[0].balance_mmbtu.value = parseFloat(nominated_qty_mmbtu) - parseFloat(loaded_qty);
				}else{	
					
				 	document.forms[0].actual_mmbtu.value = nominated_qty_mmbtu ;
					document.forms[0].balance_mmbtu.value = nominated_qty_mmbtu ;
				}
				
				var truck_size = document.forms[0].truck_size.value; 
				for(var i = 0 ; i < parseFloat(truck_size) ; i++){
					if(document.getElementById('loadChkBox'+i).checked){
						document.getElementById('loadChkBox'+i).checked=false;
						document.getElementById('chkLoadFlag'+i).value="N";
					}
				}
	  }else{
    	  alert("First Checks the Following Fileds ! \n\n"+msg)
    	  $('#myModal').modal('hide');
      }      
}

function loadQty(selIndx){
	
	var gcv = parseFloat(""+document.forms[0].gcv.value);
	var ncv = parseFloat(""+document.forms[0].ncv.value);
	var conv_fact_m3 = document.forms[0].mmbtu_to_m3.value;
	var conv_fact_ton = document.forms[0].mmbtu_to_tons.value;
	var convt_mmbtu_to_mt = document.forms[0].convt_mmbtu_to_mt.value;
	var loadChkBox = document.getElementById('loadChkBox'+selIndx).checked;
	var nominated_qty_mmbtu = document.getElementById('nominated_qty_mmbtu').value; 
	var nominated_qty_ton = document.getElementById('nominated_qty_ton').value; 
	
	if(nominated_qty_mmbtu !='' && nominated_qty_mmbtu != '0')
	{
		if(document.getElementById('loadChkBox'+selIndx).checked)
		{
			document.getElementById('chkLoadFlag'+selIndx).value='Y';
			var truckCap_m3 = document.getElementById('Tcap_in_m3'+selIndx).value; 
			var truckCap_ton = round(document.getElementById('Tcap_in_ton'+selIndx).value,2);
			var conv_to_mmbtu = Math.round(parseFloat(document.getElementById('Tcap_in_mmbtu'+selIndx).value)); // truck capacity in MMBTU
			var actual_mmbtu = document.forms[0].actual_mmbtu.value;	
			
			if(parseFloat(conv_to_mmbtu) >= parseFloat(nominated_qty_mmbtu)) // checking trcuk capacity
			{   
				//alert("if...conv_to_mmbtu("+conv_to_mmbtu+") >= nominated_qty_mmbtu("+nominated_qty_mmbtu+")")
				if(actual_mmbtu != '0'  && actual_mmbtu!='')
				{
					document.getElementById('load_mmbtu'+selIndx).value = actual_mmbtu;
					document.getElementById('chkbx_load_mmbtu'+selIndx).value = actual_mmbtu;
					document.getElementById('load_ton'+selIndx).value = round(parseFloat(document.getElementById('load_mmbtu'+selIndx).value) / parseFloat(convt_mmbtu_to_mt),2);
					document.forms[0].actual_mmbtu.value = '0';
					document.forms[0].balance_mmbtu.value = '0';	
				}
				else
				{
					if(document.getElementById('already_load_mmbtu'+selIndx).value != ''){ 
						document.getElementById('load_mmbtu'+selIndx).value = document.getElementById('already_load_mmbtu'+selIndx).value;
					    document.getElementById('load_ton'+selIndx).value = document.getElementById('allready_load_ton'+selIndx).value;
					}else{ 
						document.getElementById('load_mmbtu'+selIndx).value = '';
					    document.getElementById('load_ton'+selIndx).value = '';
					}
					document.getElementById('chkbx_load_mmbtu'+selIndx).value = '';
					document.getElementById('loadChkBox'+selIndx).checked=false;
					document.getElementById('chkLoadFlag'+selIndx).value="N";
					alert('Not enough quantity to load!');
				}
			}
			else if(parseFloat(conv_to_mmbtu) <= parseFloat(nominated_qty_mmbtu))
			{ 
				//alert("else..conv_to_mmbtu("+conv_to_mmbtu+") <= nominated_qty_mmbtu("+nominated_qty_mmbtu+")")
				if(actual_mmbtu != '0'  && actual_mmbtu!='')
				{
					if(parseFloat(actual_mmbtu) <= parseFloat(conv_to_mmbtu))
					{   
						//alert("else....if....actual_mmbtu("+actual_mmbtu+") <= conv_to_mmbtu("+conv_to_mmbtu+")");
						document.getElementById('load_mmbtu'+selIndx).value = Math.round(parseFloat(actual_mmbtu));
						document.getElementById('chkbx_load_mmbtu'+selIndx).value = Math.round(parseFloat(actual_mmbtu));
						document.forms[0].actual_mmbtu.value = '0';
						document.forms[0].balance_mmbtu.value = '0';
					}
					else{
						//alert("else...else...actual_mmbtu("+actual_mmbtu+") >= conv_to_mmbtu("+conv_to_mmbtu+")");
						document.getElementById('load_mmbtu'+selIndx).value = Math.round(parseFloat(conv_to_mmbtu));
						document.getElementById('chkbx_load_mmbtu'+selIndx).value = Math.round(parseFloat(conv_to_mmbtu));
						var balance_qty_mmbtu = parseFloat(actual_mmbtu) - parseFloat(conv_to_mmbtu);						
						document.forms[0].actual_mmbtu.value = Math.round(parseFloat(balance_qty_mmbtu));
						document.forms[0].balance_mmbtu.value = Math.round(parseFloat(balance_qty_mmbtu));
					}
				    document.getElementById('load_ton'+selIndx).value = round(parseFloat(document.getElementById('load_mmbtu'+selIndx).value) / parseFloat(convt_mmbtu_to_mt),2);
				}
				else
				{
					if(document.getElementById('already_load_mmbtu'+selIndx).value != ''){ 
						document.getElementById('load_mmbtu'+selIndx).value = document.getElementById('already_load_mmbtu'+selIndx).value;
					    document.getElementById('load_ton'+selIndx).value = document.getElementById('allready_load_ton'+selIndx).value;
					}else{ 
						document.getElementById('load_mmbtu'+selIndx).value = '';
					    document.getElementById('load_ton'+selIndx).value = '';
					}
					document.getElementById('chkbx_load_mmbtu'+selIndx).value = '';
					document.getElementById('loadChkBox'+selIndx).checked=false;
					document.getElementById('chkLoadFlag'+selIndx).value="N";
					alert('Not enough quantity to load!');
				}
			}
			else
			{
				document.getElementById('load_mmbtu'+selIndx).value = conv_to_mmbtu.toFixed(2);
				document.getElementById('chkbx_load_mmbtu'+selIndx).value = conv_to_mmbtu.toFixed(2);
				document.getElementById('load_ton'+selIndx).value = truckCap_ton;
			}
		}
		else
		{	
			document.getElementById('chkLoadFlag'+selIndx).value='N';
			var rem_qty = document.forms[0].actual_mmbtu.value;
			var conv_to_mmbtu = document.getElementById('load_mmbtu'+selIndx).value;
			if(conv_to_mmbtu == '' ){
				conv_to_mmbtu = 0;
			}
			var bal_qty = parseFloat(rem_qty) + parseFloat(conv_to_mmbtu);
			document.forms[0].actual_mmbtu.value = bal_qty; 		
			document.forms[0].balance_mmbtu.value = bal_qty;
			if(document.getElementById('already_load_mmbtu'+selIndx).value != ''){
				document.getElementById('load_mmbtu'+selIndx).value = document.getElementById('already_load_mmbtu'+selIndx).value;
			    document.getElementById('load_ton'+selIndx).value = document.getElementById('allready_load_ton'+selIndx).value;
			}else{ 
				document.getElementById('load_mmbtu'+selIndx).value = '';
			    document.getElementById('load_ton'+selIndx).value = '';
			}
			document.getElementById('load_ton'+selIndx).value = '';
			document.getElementById('curr_load_mmbtu'+selIndx).value = '';
			document.getElementById('chkbx_load_mmbtu'+selIndx).value = '';						
		}
	}
	else
	{	
		alert('Nominated quantity not available, please enter nominated quantity first !!');
		document.getElementById('loadChkBox'+selIndx).checked=false;
		document.getElementById('chkLoadFlag'+selIndx).value="N";
	}
}

function validateQty(sel_mmbtu,selIndx){
	var chkLen = document.forms[0].chkBox.length;
	var conv_fact_ton = document.forms[0].mmbtu_to_tons.value;
	var convt_mmbtu_to_mt = document.forms[0].convt_mmbtu_to_mt.value;
	var inv_flg = document.forms[0].inv_flg.value;
	var tot_inv_mmbtu = document.forms[0].tot_inv_mmbtu.value;
	
	var truck_loaded_flag = document.getElementById('truck_loaded_flag').value;
	var loaded_qty = document.getElementById('loaded_qty').value;
	
	var sum = 0;
	
	if(chkLen > 1){
		for(var i = 0 ; i < chkLen ; i++){
			if(document.getElementById('load_mmbtu'+i).value!=''){
				if(document.getElementById('loadChkBox'+i).checked == true)
				{
					sum+=parseFloat(document.getElementById('load_mmbtu'+i).value);
				}
			}
		}
	}
	if(chkLen=='undefined' || chkLen==undefined ){
		if(document.getElementById('load_mmbtu'+selIndx).value!=''){
			if(document.getElementById('loadChkBox'+selIndx).checked == true)
			{
				sum+=parseFloat(document.getElementById('load_mmbtu'+selIndx).value);
			}
		}
	}
	
	var loaded_Cheked_val = sum - document.getElementById('load_mmbtu'+selIndx).value;
	var notLoad_gretaer_val = document.getElementById('nominated_qty_mmbtu').value - loaded_Cheked_val;
	//alert(sum);alert(document.getElementById('nominated_qty_mmbtu').value);
	if(parseFloat(sum) > parseFloat(document.getElementById('nominated_qty_mmbtu').value))
	{ //alert(document.getElementById('Tcap_in_mmbtu'+selIndx).value); 
		if(parseFloat(sum) > parseFloat(document.getElementById('Tcap_in_mmbtu'+selIndx).value))
		{
			if(document.forms[0].balance_mmbtu.value == '0' && document.forms[0].actual_mmbtu.value)
			{
				alert('Do Not Load Qty Greater then the Truck Capacity or Not Enough Qty!!');
				if(document.getElementById('already_load_mmbtu'+selIndx).value != ''){
					document.getElementById('load_mmbtu'+selIndx).value = document.getElementById('already_load_mmbtu'+selIndx).value;
				    document.getElementById('load_ton'+selIndx).value = document.getElementById('allready_load_ton'+selIndx).value;
				}else{
					document.getElementById('load_mmbtu'+selIndx).value = '';
				    document.getElementById('load_ton'+selIndx).value = '';
				}
				document.forms[0].balance_mmbtu.value = parseFloat(notLoad_gretaer_val);
				document.forms[0].actual_mmbtu.value = document.forms[0].balance_mmbtu.value;
				document.getElementById('chkbx_load_mmbtu'+selIndx).value = '';
				document.getElementById('loadChkBox'+selIndx).checked = false;
				document.getElementById('chkLoadFlag'+selIndx).value="N";
			}
			else
			{
				alert('Do Not Load Qty Greater then the Truck Capacity or Not Enough Qty!!');
				document.forms[0].balance_mmbtu.value = parseFloat(notLoad_gretaer_val);
				document.forms[0].actual_mmbtu.value = document.forms[0].balance_mmbtu.value;
				if(document.getElementById('already_load_mmbtu'+selIndx).value != ''){
					document.getElementById('load_mmbtu'+selIndx).value = document.getElementById('already_load_mmbtu'+selIndx).value;
				    document.getElementById('load_ton'+selIndx).value = document.getElementById('allready_load_ton'+selIndx).value;
				}else{
					document.getElementById('load_mmbtu'+selIndx).value = '';
				    document.getElementById('load_ton'+selIndx).value = '';
				}
				document.getElementById('chkbx_load_mmbtu'+selIndx).value = '';
				document.getElementById('loadChkBox'+selIndx).checked = false;
				document.getElementById('chkLoadFlag'+selIndx).value="N";
			}
		}
		else
		{    
			alert('Do Not Load Qty Greater then the Truck Capacity or Not Enough Qty!!');
			document.forms[0].actual_mmbtu.value = parseFloat(notLoad_gretaer_val);
			document.forms[0].balance_mmbtu.value =  document.forms[0].actual_mmbtu.value;
			if(document.getElementById('already_load_mmbtu'+selIndx).value != ''){
				document.getElementById('load_mmbtu'+selIndx).value = document.getElementById('already_load_mmbtu'+selIndx).value;
			    document.getElementById('load_ton'+selIndx).value = document.getElementById('allready_load_ton'+selIndx).value;
			}else{ 
				document.getElementById('load_mmbtu'+selIndx).value = '';
			    document.getElementById('load_ton'+selIndx).value = '';
			}
			document.getElementById('loadChkBox'+selIndx).checked = false;
			document.getElementById('chkLoadFlag'+selIndx).value="N";
		}
	}
	else
	{
		if(parseFloat(sum) > parseFloat(document.getElementById('Tcap_in_mmbtu'+selIndx).value))
		{ 
			if(document.forms[0].balance_mmbtu.value == '0' && document.forms[0].actual_mmbtu.value)
			{			
				document.forms[0].balance_mmbtu.value = parseFloat(document.getElementById('nominated_qty_mmbtu').value) - parseFloat(sum);
				document.forms[0].actual_mmbtu.value = document.forms[0].balance_mmbtu.value;
				if(document.getElementById('load_mmbtu'+selIndx).value == '')
				{
					if(document.getElementById('already_load_mmbtu'+selIndx).value != ''){
						document.getElementById('load_mmbtu'+selIndx).value = document.getElementById('already_load_mmbtu'+selIndx).value;
					    document.getElementById('load_ton'+selIndx).value = document.getElementById('allready_load_ton'+selIndx).value;
					}else{
						document.getElementById('load_mmbtu'+selIndx).value = '';
					    document.getElementById('load_ton'+selIndx).value = '';
					}
					document.getElementById('loadChkBox'+selIndx).checked = false;
					document.getElementById('chkLoadFlag'+selIndx).value="N";
				}else{
					document.getElementById('load_ton'+selIndx).value = round(parseFloat(document.getElementById('load_mmbtu'+selIndx).value) / parseFloat(convt_mmbtu_to_mt),2);
				}
									
			}
			else
			{
				if(loaded_Cheked_val == '0'){   
					alert('Do Not Load Qty Greater then the Truck Capacity or Not Enough Qty!!');
					if(document.getElementById('already_load_mmbtu'+selIndx).value != ''){
						document.getElementById('load_mmbtu'+selIndx).value = document.getElementById('already_load_mmbtu'+selIndx).value;
					    document.getElementById('load_ton'+selIndx).value = document.getElementById('allready_load_ton'+selIndx).value;
					}else{
						document.getElementById('load_mmbtu'+selIndx).value = '';
					    document.getElementById('load_ton'+selIndx).value = '';
					}
					document.forms[0].balance_mmbtu.value = parseFloat(notLoad_gretaer_val);
					document.forms[0].actual_mmbtu.value = document.forms[0].balance_mmbtu.value ;
					document.getElementById('chkbx_load_mmbtu'+selIndx).value = '';
					document.getElementById('loadChkBox'+selIndx).checked = false;
					document.getElementById('chkLoadFlag'+selIndx).value="N";
				}
				else{
					document.forms[0].actual_mmbtu.value = parseFloat(document.getElementById('nominated_qty_mmbtu').value) - parseFloat(sum);
					document.forms[0].balance_mmbtu.value = document.forms[0].actual_mmbtu.value;
					if(parseFloat(document.getElementById('load_mmbtu'+selIndx).value) < parseFloat(document.getElementById('Tcap_in_mmbtu'+selIndx).value)){
						document.getElementById('load_ton'+selIndx).value = round(parseFloat(document.getElementById('load_mmbtu'+selIndx).value) / parseFloat(convt_mmbtu_to_mt),2);
					}
					else{			
						alert('Do Not Load Qty Greater then the Truck Capacity or Not Enough Qty!!');
						document.forms[0].actual_mmbtu.value = parseFloat(notLoad_gretaer_val);
						document.forms[0].balance_mmbtu.value = document.forms[0].actual_mmbtu.value;
						if(document.getElementById('already_load_mmbtu'+selIndx).value != ''){
							document.getElementById('load_mmbtu'+selIndx).value = document.getElementById('already_load_mmbtu'+selIndx).value;
						    document.getElementById('load_ton'+selIndx).value = document.getElementById('allready_load_ton'+selIndx).value;
						}else{
							document.getElementById('load_mmbtu'+selIndx).value = '';
						    document.getElementById('load_ton'+selIndx).value = '';
						}
						document.getElementById('loadChkBox'+selIndx).checked = false;
						document.getElementById('chkLoadFlag'+selIndx).value="N";
					}		
				}
			}
		}
		else
		{
			
			if(inv_flg=='Y'){
				
				document.forms[0].actual_mmbtu.value = (parseFloat(document.getElementById('nominated_qty_mmbtu').value) - parseFloat(tot_inv_mmbtu)) - parseFloat(sum);
				document.forms[0].balance_mmbtu.value =  (parseFloat(document.getElementById('nominated_qty_mmbtu').value) - parseFloat(tot_inv_mmbtu)) - parseFloat(sum);
			
			}else if(truck_loaded_flag == "Y"){
				
				document.forms[0].actual_mmbtu.value = (parseFloat(document.getElementById('nominated_qty_mmbtu').value) - parseFloat(loaded_qty)) - parseFloat(sum);
				document.forms[0].balance_mmbtu.value =  (parseFloat(document.getElementById('nominated_qty_mmbtu').value) - parseFloat(loaded_qty)) - parseFloat(sum);
				
			}else{
				document.forms[0].actual_mmbtu.value = parseFloat(document.getElementById('nominated_qty_mmbtu').value) - parseFloat(sum);
				document.forms[0].balance_mmbtu.value =  parseFloat(document.getElementById('nominated_qty_mmbtu').value) - parseFloat(sum);
			}
			if(document.getElementById('load_mmbtu'+selIndx).value == '')
			{
				if(document.getElementById('already_load_mmbtu'+selIndx).value != ''){
					document.getElementById('load_mmbtu'+selIndx).value = document.getElementById('already_load_mmbtu'+selIndx).value;
				    document.getElementById('load_ton'+selIndx).value = document.getElementById('allready_load_ton'+selIndx).value;
				}else{
					document.getElementById('load_mmbtu'+selIndx).value = '';
				    document.getElementById('load_ton'+selIndx).value = '';
				}
				document.getElementById('loadChkBox'+selIndx).checked = false;
				document.getElementById('chkLoadFlag'+selIndx).value="N";
			}
			else
			{
				document.getElementById('load_ton'+selIndx).value = round(parseFloat(document.getElementById('load_mmbtu'+selIndx).value) / parseFloat(convt_mmbtu_to_mt),2);	
			}
		}
	}	
}
function setCurrQty(curr_val,indx) {
	if(document.getElementById('loadChkBox'+indx).checked && document.getElementById('curr_load_mmbtu'+indx).value == ''){
		document.getElementById('curr_load_mmbtu'+indx).value = curr_val;
	}
}
function modaltruckLoad()
{
	var msg = "";
	var flag = true;
	var flagchekcs = 0;
	var i = 0;
    var chklen = document.forms[0].chkBox.length;
    var loadtrucknm = new Array();
    var load_mmbtu = new Array();
    var load_ton = new Array();
    var arrival_date = new Array();
    var arrival_time = new Array();
    if(chklen == 'undefined' || chklen == undefined){   	
    	if(document.forms[0].chkBox.checked){
    		flagchekcs = 1;
    		var loadtrucknm = document.forms[0].loadtrucknm.value;
    		var load_mmbtu = document.forms[0].load_mmbtu.value;
    		var load_ton = document.forms[0].load_ton.value;
    		var arrival_date = document.forms[0].arrival_date.value;
    		var arrival_time= document.forms[0].arrival_time.value; 
    		
    		if(load_mmbtu==null || load_mmbtu=='0' || load_mmbtu=='' || load_mmbtu==' ' || load_mmbtu=='  ' || load_mmbtu=='  ')
    		{
    			msg += "Please Enter The Correct Quantity of MMBTU to Load Trucks !!!\n";
    			flag = false;
    		}
    		if(load_ton==null || load_ton=='0' || load_ton=='' || load_ton==' ' || load_ton=='  ' || load_ton=='  ')
    		{
    			msg += "Please Enter The Correct Quantity of Ton to Load Trucks !!!\n";
    			flag = false;
    		}
    		if(arrival_date==null || arrival_date=='0' || arrival_date=='00/00/0000' || arrival_date=='' || arrival_date==' ' || arrival_date=='  ' || arrival_date=='  ')
    		{
    			msg += "Please Enter The Correct Arrival Date  !!!\n";
    			flag = false;
    		}
    	}
    }
    
    if(parseFloat(chklen) > 1){
	    for(var i = 0 ; i < parseFloat(chklen) ; i++){	
			if(document.forms[0].chkBox[i].checked){
				flagchekcs++;
				loadtrucknm[i] = document.forms[0].loadtrucknm[i].value;
	    		load_mmbtu[i] = document.forms[0].load_mmbtu[i].value;
	    		load_ton[i] = document.forms[0].load_ton[i].value;
	    		arrival_date[i] = document.forms[0].arrival_date[i].value;
	    		arrival_time[i] = document.forms[0].arrival_time[i].value;

	    		if(load_mmbtu[i]==null || load_mmbtu[i]=='0' || load_mmbtu[i]=='' || load_mmbtu[i]==' ' || load_mmbtu[i]=='  ' || load_mmbtu[i]=='  ')
	    		{
	    			msg += "Please Enter The Correct Quantity of MMBTU to Load Trucks !!!\n";
	    			flag = false;
	    		}
	    		if(load_ton[i]==null || load_ton[i]=='0' || load_ton[i]=='' || load_ton[i]==' ' || load_ton[i]=='  ' || load_ton[i]=='  ')
	    		{
	    			msg += "Please Enter The Correct Quantity of Ton to Load Trucks !!!\n";
	    			flag = false;
	    		}
	    		if(arrival_date[i]==null || arrival_date[i]=='0' || arrival_date[i]=='' || arrival_date[i]==' ' || arrival_date[i]=='  ' || arrival_date[i]=='  ')
	    		{
	    			msg += "Please Enter The Correct Arrival Date  !!!\n";
	    			flag = false;
	    		}	    
	    		if(arrival_time[i]==null || arrival_time[i]=='0' || arrival_time[i]=='' || arrival_time[i]==' ' || arrival_time[i]=='  ' || arrival_time[i]=='  ')
	    		{
	    			msg += "Please Enter The Correct Arrival Time  !!!\n";
	    			flag = false;
	    		}	
			}
	    }
    }
    if(flagchekcs > 0){}
    else
    {
    	flag = false;
    	msg += "Please Select Truck To Load!!!\n";
    }
    
    if(flag)
	{
    	var a = confirm("Are you sure want to Nominate Truck(s) ?");
    	if(a){
    		$('#myform').submit();
    	}
	}
	else
	{
		if(flagchekcs>0)
		{
			alert("First Checks the Following Fileds !\n\n"+msg)
		}
		else
		{
			swal({
				customClass: 'swal-height',
				title: msg,
				icon: "../images/coolcheckbox.gif", 
				timer: 2000
			});	
		}
	}
}

function sameToSame(Selindx) {
	  var x = document.getElementById("chkbx_load_mmbtu"+Selindx);
	  var y = document.getElementById("load_mmbtu"+Selindx);
	  x.value = y.value; 
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
	var cancel_truck_list = document.getElementById('cancel_truck_list').value;
	
	if(gas_date==null || gas_date=='0' || gas_date=='' || gas_date==' ' || gas_date=='  ' || gas_date=='  ')
	{
		msg += "Please Enter The Correct Nomination DATE For Nomination DAY Field !!!\n";
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
		    	msg += "Generation Day Must Be Less Than Or Equal To Nomination Date !!!\n";
		    	flag = false;
		  	}
		}
	}
	if(index_count==1)
	{
		if(document.forms[0].chk.checked)
		{
			document.forms[0].chk_flag.value = "Y";
			var hh_mm = document.forms[0].hh_mm.value;
			var buyer_plant_seq_no = document.forms[0].buyer_plant_seq_no.value;
			var qty_mmbtu = document.forms[0].qty_mmbtu.value;
			
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
				
				//alert("buyer_plant_seq_no = "+buyer_plant_seq_no+",  And  index_count = "+index_count);
			
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
	if(cancel_truck_list == '' || cancel_truck_list == ' '){
		msg+="No Truck(s) available for cancellation of Buyer Nomination, First cancel the Seller Nomination";
		flag = false;
	}
	if(flag)
	{
		var a = confirm("Do you want to Cancel the Nomination of following Truck(s) ?\n"+cancel_truck_list);
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
	var update_permission = document.forms[0].update_permission.value;
	
	location.replace("../master/frm_mst_buyerNomination.jsp?gas_date="+gas_date+"&gen_date="+gen_date+
			"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+
			"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&update_permission="+update_permission+
			"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl);
}

</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request" />
<jsp:useBean class="com.seipl.hazira.dlng.contract_mgmt.DataBean_MgmtV2" id="contMgmt" scope="page" />
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
	/* below variable getting from Frm_checkLogin */
	String today = request.getParameter("today") == null ? "" : request.getParameter("today");
	String todaytime = request.getParameter("todaytime") == null ? "" : request.getParameter("todaytime");
	String emp_nm = request.getParameter("emp_nm") == null ? "" : request.getParameter("emp_nm");
	String user_cd = request.getParameter("user_cd") == null ? "" : request.getParameter("user_cd");
	/* end */
	String truck_load_date = request.getParameter("gas_date")==null?tomorrow_date:request.getParameter("gas_date"); //SB20200725
	String gas_date = request.getParameter("gas_date") == null? tomorrow_date: request.getParameter("gas_date");
	String gen_date = request.getParameter("gen_date") == null
			? current_date
			: request.getParameter("gen_date");
	String SrvLoad = request.getParameter("SrvLoad") == null ? "N" : request.getParameter("SrvLoad");
	String msg = request.getParameter("msg") == null ? "" : request.getParameter("msg");

	String indx = request.getParameter("indx") == null ? "100000" : request.getParameter("indx");
	String selCust_id = request.getParameter("cust_id") == null ? "" : request.getParameter("cust_id");
	String selNom_qty = request.getParameter("nom_qty") == null ? "" : request.getParameter("nom_qty");
	String buyer_mapping_id = request.getParameter("buyer_mapping_id") == null
			? ""
			: request.getParameter("buyer_mapping_id");
	String nomId = request.getParameter("nomId") == null ? "" : request.getParameter("nomId");
	String nomDt = request.getParameter("nomDt") == null ? "" : request.getParameter("nomDt");
	String revNo = request.getParameter("revNo") == null ? "" : request.getParameter("revNo");
	String custPlant_cd = request.getParameter("custPlant_cd") == null?"":request.getParameter("custPlant_cd");
	String delv_base = request.getParameter("delv_base") == null ? "" : request.getParameter("delv_base");
	String contTyp = request.getParameter("contTyp") == null ? "" : request.getParameter("contTyp");
	String ncv_gcv = request.getParameter("ncv_gcv") == null ? "9802.80" : request.getParameter("ncv_gcv");
	
	String selMapId = request.getParameter("daily_adv_mapping_id") == null ? "" : request.getParameter("daily_adv_mapping_id");
	
	contMgmt.setSelCust_id(selCust_id);
	contMgmt.setBuyer_mapping_id(buyer_mapping_id);
	contMgmt.setNomDt(nomDt);
	contMgmt.setNomId(nomId);
	contMgmt.setRevNo(revNo);
	contMgmt.setIndx(indx);
	contMgmt.setCustPlant_cd(custPlant_cd);
	contMgmt.setselDelvBase(delv_base); //SB20200801
	contMgmt.setSelContType(contTyp);
	contMgmt.setNcv_gcv(ncv_gcv);
	contMgmt.setSelMapId(selMapId);
	contMgmt.setSysdate(current_date);
	
	if (!emp_nm.equals("")) {
		session.setAttribute("username", emp_nm);
		session.setAttribute("today", today);
		session.setAttribute("todaytime", todaytime);
		session.setAttribute("user_cd", user_cd);
		// 	session.setMaxInactiveInterval(30);
	}

	utilBean.init();

	String emp_cd = (String) session.getAttribute("user_cd") == null? "": (String) session.getAttribute("user_cd");
	String emp_name = (String) session.getAttribute("username") == null? "": (String) session.getAttribute("username");
	String comp_cd = (String) session.getAttribute("comp_cd") == null? "": (String) session.getAttribute("comp_cd");
	
	String write_permission = (String)session.getAttribute("write_permission") ==null?"N":session.getAttribute("write_permission").toString();
	String delete_permission = (String)session.getAttribute("delete_permission")==null?"N":session.getAttribute("delete_permission").toString();
	String print_permission = (String)session.getAttribute("print_permission") ==null?"N":session.getAttribute("print_permission").toString();
	String approve_permission = (String)session.getAttribute("approve_permission") ==null?"N":session.getAttribute("approve_permission").toString();
	String audit_permission = (String)session.getAttribute("audit_permission") ==null?"N":session.getAttribute("audit_permission").toString();
	String update_permission = (String)session.getAttribute("update_permission") ==null?"N":session.getAttribute("update_permission").toString();
	
	contMgmt.setCallFlag("GENERATE_GEN_DATE_FOR_NOMINATION");
	contMgmt.setGas_date(gas_date);
	contMgmt.init();

	if (!contMgmt.getGen_date().trim().equalsIgnoreCase("")) {
		gen_date = contMgmt.getGen_date().trim();
	}

	double gcv = Double.parseDouble(request.getParameter("gcv") == null ? "9802.80" : request.getParameter("gcv"));
	double ncv = Double.parseDouble(request.getParameter("ncv") == null ? "8831.35" : request.getParameter("ncv"));
	double percentage_of_dcq = 10;

	String modCd = session.getAttribute("module_cd") == null? "": session.getAttribute("module_cd").toString();//HA20200212
	String formId = session.getAttribute("selFormId") == null? "": session.getAttribute("selFormId").toString();//HA20200212
	String subModUrl = session.getAttribute("subModUrl") == null? "": session.getAttribute("subModUrl").toString();//HA20200212

	double convt_mmbtu_to_mt= (double) session.getAttribute("convt_mmbtu_to_mt");//HA20201229
	contMgmt.setCallFlag("CUSTOMER_NOM");
	contMgmt.setGas_date(gas_date);
	contMgmt.setConvt_mmbtu_to_mt(convt_mmbtu_to_mt);
	contMgmt.init();

	Vector daily_Buyer_Nom_Fgsa_No = contMgmt.getDaily_Buyer_Nom_Fgsa_No();
	Vector daily_Buyer_Nom_Fgsa_Rev_No = contMgmt.getDaily_Buyer_Nom_Fgsa_Rev_No();
	Vector daily_Buyer_Nom_Sn_No = contMgmt.getDaily_Buyer_Nom_Sn_No();
	Vector daily_Buyer_Nom_Sn_Rev_No = contMgmt.getDaily_Buyer_Nom_Sn_Rev_No();
// 	System.out.println("daily_Buyer_Nom_Sn_Rev_No***"+daily_Buyer_Nom_Sn_Rev_No);
	Vector daily_Buyer_Nom_Cd = contMgmt.getDaily_Buyer_Nom_Cd();
	Vector daily_Buyer_Nom_Abbr = contMgmt.getDaily_Buyer_Nom_Abbr();
	Vector daily_Buyer_Nom_Dcq = contMgmt.getDaily_Buyer_Nom_Dcq();
	Vector daily_Buyer_Nom_Plant_Cd = contMgmt.getDaily_Buyer_Nom_Plant_Cd();
	Vector daily_Buyer_Nom_Plant_Abbr = contMgmt.getDaily_Buyer_Nom_Plant_Abbr();
	
	Vector daily_Buyer_Nom_Balance_Qty = contMgmt.getDaily_Buyer_Nom_Balance_Qty();
	Vector Buyer_Allocated_Qty = contMgmt.getBuyer_Allocated_Qty();
	Vector Buyer_Contracted_Qty = contMgmt.getBuyer_Contracted_Qty();
	Vector Buyer_Nominated_Qty = contMgmt.getBuyer_Nominated_Qty();
	Vector daily_Buyer_Nom_Mdcq_Qty = contMgmt.getDaily_Buyer_Nom_Mdcq_Qty();
	String daily_Total_Dcq = contMgmt.getDaily_Total_Dcq();
	Vector daily_Buyer_boe_no = contMgmt.getDaily_Buyer_regas_cargo_boe_no();
	Vector daily_Buyer_boe_dt = contMgmt.getDaily_Buyer_regas_cargo_boe_dt();
	Vector daily_Buyer_Gen_Day_With_Rev_No = contMgmt.getDaily_Buyer_Gen_Day_With_Rev_No();
	Vector daily_Buyer_Gen_Day_Time = contMgmt.getDaily_Buyer_Gen_Day_Time();
	Vector daily_Buyer_Nom_Plant_Seq_No = contMgmt.getDaily_Buyer_Nom_Plant_Seq_No();
	Vector daily_Buyer_Nom_Qty_Mmbtu = contMgmt.getDaily_Buyer_Nom_Qty_Mmbtu();
	Vector daily_Buyer_Nom_Qty_Scm = contMgmt.getDaily_Buyer_Nom_Qty_Scm();
	String daily_Total_Mmbtu = contMgmt.getDaily_Total_Mmbtu();
	String daily_Total_Scm = contMgmt.getDaily_Total_Scm();
	Vector daily_Buyer_Nom_Type = contMgmt.getDaily_Buyer_Nom_Type();
	Vector daily_Buyer_Nom_Contract_Type = contMgmt.getDaily_Buyer_Nom_Contract_Type();
	Vector CUST_CD = contMgmt.getCUST_CD();
	Vector daily_Buyer_Nom_Dcq_Mt = contMgmt.getDaily_Buyer_Nom_Dcq_Mt();
	Vector VBuyer_Fcc_Flag = contMgmt.getVBuyer_Fcc_Flag(); //SB20200718
	Vector VBuyer_Inv_Flag = contMgmt.getVBuyer_Inv_Flag(); //SB20200718
	Vector VBuyer_Delv_Base = contMgmt.getVBuyer_Delv_Base(); //SB20200718

	int index = 0;
	int mindex = 0;
	int record_length = daily_Buyer_Nom_Plant_Seq_No.size();
	String plant_cd = "0";

	if (record_length > 1) {
		plant_cd = (String) daily_Buyer_Nom_Plant_Seq_No.elementAt(0);
	}
	Vector daily_Buyer_Nom_Mapping_Id = contMgmt.getDaily_Buyer_Nom_Mapping_Id();
	Vector qty_nomination = contMgmt.getQty_nomination();

	selCust_id = contMgmt.getSelCust_id();
	indx = contMgmt.getIndx();

	Vector custidAT = contMgmt.getCustIDAT();
	Vector truckidAT = contMgmt.getTruckIdAT();
	Vector trucknmAT = contMgmt.getTruckNameAT();
	Vector tankVolM3AT = contMgmt.getTankVolM3AT();
	Vector tankVolTonAT = contMgmt.getTankVolTonAT();

	Vector TloadedVol = contMgmt.getTloadedVol();
	Vector TloadedEne = contMgmt.getTloadedEne();
	Vector TloadedDt = contMgmt.getTloadedDt();
	Vector TloadedTm = contMgmt.getTloadedTm();
	Vector TloadedRemark = contMgmt.getTloadedRemark();
	Vector VLoadedNxt_avail_days =contMgmt.getVLoadedNxt_avail_days();

	Vector custPlant_cdFlag = contMgmt.getCustPlant_cdFlag();
	Vector OnlyLoadedTruck = contMgmt.getOnlyLoadedTruck();

	double conversion_factor_from_m3_to_mmbtu = 23.9; //HA20191223
	double converssion_factor_from_m3_tonmmbtu = 0.3531466672;
	//double convt_mmbtu_to_mt = 51.9;
	double totalTon = 0;
	int truckCount = 0;
	String textdisabled = "disabled='disabled'";
	for (int i = 0; i < TloadedVol.size(); i++) {
		if (!TloadedVol.elementAt(i).equals("")) {
			truckCount++;
		}
	}

	Vector VTruckTransCd = contMgmt.getVTruckTransCd();
// 	System.out.println("selCust_id : "+selCust_id);
	
	/* System.out.println("OnlyLoadedTruck : "+OnlyLoadedTruck.size());
	System.out.println("truckCount : "+truckCount);
	System.out.println("TloadedDt : "+TloadedDt.size());
	System.out.println("custidAT : "+custidAT.size()); */
	Vector VweeklyNomFlag = contMgmt.getVweeklyNomFlag();
	Vector truckLoadedFlag = contMgmt.getTruckLoadedFlag();
	Vector VLoadedTruckNm =  contMgmt.getVLoadedTruckNm();
	Vector prevCust_Nm = contMgmt.getPrevCust_Nm();
	Vector prevSn = contMgmt.getPrevSn();
	Vector prevPlant = contMgmt.getPrevPlant();
	Vector prevNomId = contMgmt.getPrevNomID();
	Vector prevContTyp = contMgmt.getPrevContTyp();
	Vector prevTruckNomDt =contMgmt.getPrevTruckNomDt();
	nomId = contMgmt.getNomId();
// 	System.out.println("nomId : "+nomId);
	String title1 = "";
	Vector Vtruck_Inv_Flag = contMgmt.getVtruck_Inv_Flag();
	Vector Vtruck_Inv_qty = contMgmt.getVtruck_Inv_qty();
	Vector Vtruck_Inv_qty_mt = contMgmt.getVtruck_Inv_qty_mt();
	String tot_inv_mmbtu = contMgmt.getTot_inv_mmbtu();
	String tot_inv_scm = contMgmt.getTot_inv_scm();
	String tot_inv_mt = contMgmt.getTot_inv_mt();
	Vector daily_Buyer_Rev_No = contMgmt.getDaily_Buyer_Rev_No();
	Vector total_inv_qty_mmbtu = contMgmt.getTotal_inv_qty_mmbtu();
	Vector total_inv_qty_scm = contMgmt.getTotal_inv_qty_scm();
	Vector total_inv_qty_mt = contMgmt.getTotal_inv_qty_mt();
	String inv_gen_flag = contMgmt.getInv_gen_flag();
	Vector all_inv_gen_flg  = contMgmt.getAll_inv_gen_flg();
	Vector Vtruck_alloc_flag  = contMgmt.getVtruck_alloc_flag();
	Vector Vtruck_alloc_mmbtu = contMgmt.getVtruck_alloc_mmbtu();
	Vector Vtruck_alloc_scm = contMgmt.getVtruck_alloc_scm();
	Vector Vtruck_alloc_mt = contMgmt.getVtruck_alloc_mt();
	Vector max_nom_rev_no =  contMgmt.getMax_nom_rev_no();
			
	String balance_adv_amt = contMgmt.getBalance_adv_amt();
	String Sales_Rate = contMgmt.getSales_Rate();
	String exg_rate = contMgmt.getExg_rate();
	String tax_rate = contMgmt.getTax_rate();
	
// 	System.out.println("balance_adv_amt : "+balance_adv_amt);
// 	System.out.println("tomorrow_date : "+tomorrow_date);
	String truck_loaded = "N";
	double loaded_qty = 0;
	
	Vector daily_adv_mapping_id = contMgmt.getDaily_adv_mapping_id();
	Vector cont_closure_close = contMgmt.getCont_closure_close(); 
	Vector cont_closure_request = contMgmt.getCont_closure_request();
	String cancel_truck_list = contMgmt.getCancel_truck_list();
	
	String oa_flag = contMgmt.getOa_flag();
	String security = contMgmt.getSecurity();
%>
<%try{ %>
<body>
<div class="tab-content">

	<!--Daily Buyer Nomination TAB START-->
	<div class="tab-pane active" id="daily_buyer_nomination">
		<!-- Default box -->
		<div class="box mb-0">
			<form method="post" action="../servlet/Frm_MgmtV2" id="myform">

				<%
					for (int i = 0; i < CUST_CD.size(); i++) {
				%>
				<input type="hidden" name="cust_cd"
					value="<%=(String) CUST_CD.elementAt(i)%>">
				<%
					}
				%>

				<input type="hidden" name="modCd" value="<%=modCd%>">
				<input type="hidden" name="formId" value="<%=formId%>"> 
				<input	type="hidden" name="subModUrl" value="<%=subModUrl%>" > 
				<input	type="hidden" name="subType" value=""> 
				<input type="hidden"	name="write_permission" value="<%=write_permission%>">
				<input type="hidden"	name="update_permission" value="<%=update_permission%>">
				<input	type="hidden" name="delete_permission" value="<%=delete_permission%>"> 
				<input type="hidden"	name="print_permission" value="<%=print_permission%>"> 
				<input	type="hidden" name="approve_permission" value="<%=approve_permission%>"> 
				<input type="hidden"	name="audit_permission" value="<%=audit_permission%>">
				<input	type="hidden" name="option" value="submitCustomerNom"> 
				<input	type="hidden" name="mmbtu_to_tons" value="0.025219021687207">
				<!-- HA20191221 -->
				<input type="hidden" name="mmbtu_to_m3" value="23.9">
				<!-- HA20191221 -->
				<input type="hidden" name="actual_mmbtu" id="actual_mmbtusss"	value="">
				<!-- HA20191223 -->
				<input type="hidden" name="m3_to_tonMMbtu" value="0.3531466672">
				<input type="hidden" name="convt_mmbtu_to_mt"
					value="<%=convt_mmbtu_to_mt%>"> <input type="hidden"
					name="truck_size" value="<%=trucknmAT.size()%>">
				<div class="box-header with-border">
				
				<%if(Vtruck_Inv_Flag.contains("Y")) {%>
					<input type="hidden" name="inv_flg"  id="inv_flg" value="Y">
					<input type="hidden" name="tot_inv_mmbtu"  id="tot_inv_mmbtu" value="<%=tot_inv_mmbtu%>">
					
				<%}else{ %>
					<input type="hidden" name="inv_flg"  id="inv_flg"  value="N">
					<input type="hidden" name="tot_inv_mmbtu"  id="tot_inv_mmbtu" value="0">
				<%} %>
				<input type="hidden" name="balance_adv_amt" value="<%=balance_adv_amt%>">
				<input type="hidden" name="Sales_Rate" value="<%=Sales_Rate%>">
				<input type="hidden" name="exg_rate" value="<%=exg_rate%>">
				<input type="hidden" name="tax_rate" value="<%=tax_rate%>">
				<input type="hidden" name="cancel_truck_list" id="cancel_truck_list" value="<%=cancel_truck_list%>">
				<input type="hidden" name="oa_flag" id = "oa_flag" value="<%=oa_flag%>">
					<%
						if (msg.length() > 5) {
					%>

					<div class="box-body table-responsive no-padding">
						<table class="table  table-bordered">
							<thead>
								<tr class="info">
									<th class="text-center" colspan="2" style="color: blue;">
										<%=msg%>
									</th>
								</tr>
							</thead>
						</table>
					</div>
					<%
						}
					%>
					<div class="box-header with-border main-header">

						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<div class="form-group mb-0 row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<label> Gas Day </label>
									<div class="form-group">
										<div class='input-group date' id='datetimepicker1'>
											<input type='text' class="form-control" id="d1"
												name="gas_date" maxlength="10" value="<%=gas_date%>"
												onchange="refreshPage();" /> <span class="input-group-addon">
												<i class="fa fa-calendar"></i>
											</span>
										</div>
									</div>
								</div>
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<label> Generation Day </label>
									<div class="form-group mb-0">
										<div class='input-group date' id='datetimepicker2'>
											<input type='text' class="form-control" id="d2"
												name="gen_date" maxlength="10" value="<%=truck_load_date%>"
												onBlur="validateDate(this);" /> <span
												class="input-group-addon"> <i class="fa fa-calendar"></i>
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
										<span class="input-group-addon"> <input type="radio"
											name="rd" onClick="calculateSCM();" checked="checked">
											GCV
										</span> <input type="text" class="form-control" name="gcv"
											value="<%=gcv%>" onBlur="checkNumber1(this,7,2);"> <span
											class="input-group-addon"> kcal/SCM </span>
									</div>
								</div>

								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<div class="form-group input-group mb-0">
										<span class="input-group-addon"> <input type="radio"
											name="rd" onClick="calculateSCM();"> NCV
										</span> <input type="text" class="form-control" name="ncv"
											value="<%=ncv%>" onBlur="checkNumber1(this,7,2);"> <span
											class="input-group-addon"> kcal/SCM </span>
									</div>
								</div>

							</div>
						</div>

					</div>


				</div>

				<div class="box-body table-responsive no-padding">
					<table class="table  table-bordered">

						<thead>
							<tr class="info">
								<!-- <th class="text-center"> Select </th> -->
								<th class="text-center" rowspan="2" width="15%">
									Select&nbsp;(Date - Seq NO)</th>
								<th class="text-center" rowspan="2">Time (HH:MM)</th>
								<th class="text-center" rowspan="2">Contract<br>No
								</th>
								<th class="text-center" rowspan="2">Plant Selection</th>
								<th class="text-center" colspan="2">DCQ QTY</th>
								<th class="text-center" rowspan="2">Nomination QTY<br>(MMBTU)
								</th>
								<th class="text-center" rowspan="2">Nomination QTY<br>(SCM)
								</th>
								<th class="text-center" rowspan="2">Nomination QTY<br>(MT)
								</th>
								<th class="text-center" rowspan="2"><img id="truckload"
									width="1" height="1" src="../images/truckload.gif"></th>
							</tr>
							<tr class="info ">
								<th class="text-center" rowspan="1" colspan="1">MMBTU</th>
								<th class="text-center" rowspan="1" colspan="1">MT</th>
							</tr>
						</thead>

						<!--GAIL: GAIL INDIA LIMITED Start-->
						<%
							String btnStatus = "disabled=disabled";
							String title = "Please select radio button first!";
							for (int j = 0; j < daily_Buyer_Nom_Contract_Type.size(); j++) {

								if (j == Integer.parseInt(indx)) {
									title = "";
									if (title.equals("") && truckidAT.size() != 0) {
										btnStatus = "";
									} else {
										btnStatus = "disabled=disabled";
										title1 = "No truck(s) linked/busy for the selected Customer.. Please link firts!!";
									}

								} else {
									btnStatus = "disabled=disabled";
									title = "Please select radio button first!";
								}
						%>
						<tr <%if (j == (Integer.parseInt(indx))) {%> style="background-color: #fcf1cb" <%} %>>
							<td>
								<div class="mt-5">
								<% if(cont_closure_request.elementAt(j).equals("Y")){
// 									System.out.println("cont_closure_close.elementAt(j)---"+cont_closure_close.elementAt(j));
// 									System.out.println("cont_closure_close.elementAt(j)---"+cont_closure_close.elementAt(j));
								%>
									<input disabled 
										<%if(cont_closure_close.elementAt(j).equals("Y")){ %>
											title= "<%=daily_Buyer_Nom_Contract_Type.elementAt(j) %> Closed"
										<%}else{ %>
											title="<%=daily_Buyer_Nom_Contract_Type.elementAt(j) %> Clouser Requested" 
										<%} %>
										type="radio" id="checkbox<%=j %>" <%if (j == (Integer.parseInt(indx))) {%> checked="checked" <%}%> name="chk"	>
								<%}else if(VBuyer_Fcc_Flag.elementAt(j).equals("Y") && all_inv_gen_flg.elementAt(j).equals("N")) { %>
									<input type="radio" id="checkbox<%=j %>" <%if (j == (Integer.parseInt(indx))) {%> checked="checked" <%}%> name="chk"	onclick="check('<%=(String) daily_Buyer_Nom_Cd.elementAt(j)%>','<%=j%>','<%=daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)%>','<%=(String) daily_Buyer_Nom_Plant_Cd.elementAt(j)%>','<%=(String) VBuyer_Delv_Base.elementAt(j)%>','<%=VweeklyNomFlag.elementAt(j)%>','<%=(String) daily_Buyer_Nom_Type.elementAt(j)%>');">
								<%} else if(all_inv_gen_flg.elementAt(j).equals("Y")) {
// 									if(update_permission.equalsIgnoreCase("Y") && current_date.equals(gas_date)){
									if(update_permission.equalsIgnoreCase("Y") ){
									%>
										<input title="Allowed on Special Permission" type="radio" id="checkbox<%=j %>" <%if (j == (Integer.parseInt(indx))) {%> checked="checked" <%}%> name="chk"	onclick="check('<%=(String) daily_Buyer_Nom_Cd.elementAt(j)%>','<%=j%>','<%=daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)%>','<%=(String) daily_Buyer_Nom_Plant_Cd.elementAt(j)%>','<%=(String) VBuyer_Delv_Base.elementAt(j)%>','<%=VweeklyNomFlag.elementAt(j)%>','<%=(String) daily_Buyer_Nom_Type.elementAt(j)%>');">
									<%}else{ %>
										<input disabled title="Invoice Generated" type="radio" id="checkbox<%=j %>" <%if (j == (Integer.parseInt(indx))) {%> checked="checked" <%}%> name="chk"	onclick="check('<%=(String) daily_Buyer_Nom_Cd.elementAt(j)%>','<%=j%>','<%=daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)%>','<%=(String) daily_Buyer_Nom_Plant_Cd.elementAt(j)%>','<%=(String) VBuyer_Delv_Base.elementAt(j)%>','<%=VweeklyNomFlag.elementAt(j)%>','<%=(String) daily_Buyer_Nom_Type.elementAt(j)%>');">
									<%} %>
								<%}else { %>
									<input disabled title="Approval Pending" type="radio" id="checkbox<%=j %>" <%if (j == (Integer.parseInt(indx))) {%> checked="checked" <%}%> name="chk"	onclick="check('<%=(String) daily_Buyer_Nom_Cd.elementAt(j)%>','<%=j%>','<%=daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)%>','<%=(String) daily_Buyer_Nom_Plant_Cd.elementAt(j)%>','<%=(String) VBuyer_Delv_Base.elementAt(j)%>','<%=VweeklyNomFlag.elementAt(j)%>','<%=(String) daily_Buyer_Nom_Type.elementAt(j)%>');">
								<%} %>
									<input type="hidden" name="chk_flag"
										<%if (j == (Integer.parseInt(indx))) {%> value="Y" <%} else {%>
										value="N" <%}%>> <input type="hidden"
										name="buyer_cd"
										value="<%=(String) daily_Buyer_Nom_Cd.elementAt(j)%>">
									<input type="hidden" name="buyer_abbr" value="<%=(String) daily_Buyer_Nom_Abbr.elementAt(j)%>">
									<input type="hidden" name="contract_type" id="contract_type<%=j %>" value="<%=(String) daily_Buyer_Nom_Type.elementAt(j)%>">
									<input type="hidden" name="qty_for_nomination" value="<%=qty_nomination.elementAt(j) + ""%>"> 
									<input type="hidden" name="nomDay_wid_rev" value="<%=(String) daily_Buyer_Gen_Day_With_Rev_No.elementAt(j)%>">
									
									<input type="hidden" name="daily_adv_mapping_id"  id="daily_adv_mapping_id<%=j %>"  value="<%=daily_adv_mapping_id.elementAt(j)%>">
									<label>
									<% if(VBuyer_Fcc_Flag.elementAt(j).equals("Y")) { %>
									<%=(String) daily_Buyer_Nom_Abbr.elementAt(j)%>&nbsp;&nbsp;<%=(String) daily_Buyer_Gen_Day_With_Rev_No.elementAt(j)%>
									<%}else { %>
									<font color="red" title="Approval Pending" ><%=(String) daily_Buyer_Nom_Abbr.elementAt(j)%>&nbsp;&nbsp;<%=(String) daily_Buyer_Gen_Day_With_Rev_No.elementAt(j)%></font>
									<%} %>
										<br> 
									<% if (daily_Buyer_Nom_Balance_Qty.elementAt(j).equals("0")) { %>
										<font color="red" size="1">Balance Qty : <%=daily_Buyer_Nom_Balance_Qty.elementAt(j)%>
									</font> <%} %>
									</label>
									
								</div>
							</td>

							<td class="text-center">
								<div class="form-group mb-0">
									<input style="text-align: center;" size="9" type="text"
										class="form-control" name="hh_mm" id="hh_mm<%=j%>"
										maxlength="6" onblur="checkTime(this);"
										<%if (daily_Buyer_Gen_Day_Time.elementAt(j).equals("")) {%>
										value="<%=formatter.format(time)%>" <%} else {%>
										value="<%=(String) daily_Buyer_Gen_Day_Time.elementAt(j)%>"
										<%}%> <%=title%>>
								</div>
							</td>

							<td>
								<div class="mt-5">
									<input type="hidden" name="buyer_fgsa_no" value="<%=(String) daily_Buyer_Nom_Fgsa_No.elementAt(j)%>">
									<input type="hidden" name="buyer_mapping_id"
										value="<%=(String) daily_Buyer_Nom_Mapping_Id.elementAt(j)%>">
									<input type="hidden" name="buyer_fgsa_rev_no"
										value="<%=(String) daily_Buyer_Nom_Fgsa_Rev_No.elementAt(j)%>">
									<input type="hidden" name="buyer_sn_no"
										value="<%=(String) daily_Buyer_Nom_Sn_No.elementAt(j)%>">
									<input type="hidden" name="buyer_sn_rev_no"
										value="<%=(String) daily_Buyer_Nom_Sn_Rev_No.elementAt(j)%>">
									<input type="hidden" name="boe_no"
										value="<%=daily_Buyer_boe_no.elementAt(j)%>"> <input
										type="hidden" name="boe_dt"
										value="<%=daily_Buyer_boe_dt.elementAt(j)%>">
									<%=(String) daily_Buyer_Nom_Contract_Type.elementAt(j)%>
									<%
										if (("" + daily_Buyer_Nom_Contract_Type.elementAt(j)).trim().equalsIgnoreCase("RE-GAS")
													|| ("" + daily_Buyer_Nom_Contract_Type.elementAt(j)).trim().equalsIgnoreCase("LTCORA")
													|| ("" + daily_Buyer_Nom_Contract_Type.elementAt(j)).trim().equalsIgnoreCase("CN")
													|| ("" + daily_Buyer_Nom_Contract_Type.elementAt(j)).trim().equalsIgnoreCase("SN")
													|| ("" + daily_Buyer_Nom_Contract_Type.elementAt(j)).trim().equalsIgnoreCase("LoA")) {
									%>
									-<%=(String) daily_Buyer_Nom_Sn_No.elementAt(j)%>
									<%
										}
									%>
								</div>
							</td>

							<td>
								<div class="mt-5"><%=(String) daily_Buyer_Nom_Plant_Abbr.elementAt(j)%>
									<input type="hidden" name="buyer_plant_seq_no"
										value="<%=(String) daily_Buyer_Nom_Plant_Cd.elementAt(j)%>">
								</div>
							</td>

							<td class="text-right">
								<div class="mt-5">
									<%=(String) daily_Buyer_Nom_Dcq.elementAt(j)%>&nbsp; <input
										type="hidden" name="qty_dcq"
										value="<%=(String) daily_Buyer_Nom_Dcq.elementAt(j)%>">
									<input type="hidden" name="qty_bal"
										value="<%=(String) daily_Buyer_Nom_Balance_Qty.elementAt(j)%>">
									<input type="hidden" name="qty_mdcq"
										value="<%=(String) daily_Buyer_Nom_Mdcq_Qty.elementAt(j)%>">
								</div>
							</td>
							<td class="text-right">
								<div class="mt-5">
									<%
										double dcq_mt = Double.parseDouble(daily_Buyer_Nom_Dcq_Mt.elementAt(j).toString());
											if (Double.parseDouble(daily_Buyer_Nom_Dcq_Mt.elementAt(j) + "") <= 0) {
												dcq_mt = Double.parseDouble(daily_Buyer_Nom_Dcq.elementAt(j) + "") / convt_mmbtu_to_mt;
											}
									%>
									<%=nf.format(dcq_mt)%>&nbsp; <input type="hidden"
										name="qty_dcq_mt"
										value="<%=(String) daily_Buyer_Nom_Dcq_Mt.elementAt(j)%>">
								</div>
							</td>
							<td>
						<%-- 	<%
							String nominated_qty = "0",total_mmbtu = "0";
							if(Integer.parseInt(daily_Buyer_Rev_No.elementAt(j)+"") > 1){
// 								System.out.println("max_nom_rev_no : "+max_nom_rev_no.elementAt(j)+"------"+daily_Buyer_Nom_Qty_Mmbtu.elementAt(j));
								if(max_nom_rev_no.elementAt(j).equals(daily_Buyer_Rev_No.elementAt(j))){
									total_mmbtu = nf2.format(Double.parseDouble(total_inv_qty_mmbtu.elementAt(j)+""));
								}else{
									total_mmbtu = nf2.format(Double.parseDouble(total_inv_qty_mmbtu.elementAt(j)+"") + Double.parseDouble(daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)+""));
								}
								
								nominated_qty = total_mmbtu ;
								
							}else{
								nominated_qty = daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)+"";
							} %> --%> 
							<%String nominated_qty = "0" ; 
							nominated_qty = daily_Buyer_Nom_Qty_Mmbtu.elementAt(j)+"";
							%>
								<div class="form-group mb-0">
									<input type="text" class="form-control" id="qty_mmbtu<%=j%>" 
										type="text" name="qty_mmbtu" style="background:WHITE;text-align: right" 
										value="<%=nominated_qty %>"
										maxlength="10"
										onBlur="calculateSCM_TOTAL(this,'<%=j%>','');" <%=title%>
										onkeyup="checkForNumberWithDot(this)">
										<input type="hidden" id = "nominated_qty<%=j %>" value="<%=nominated_qty%>">
									<%-- id="qty_val<%=daily_Buyer_Nom_Cd.elementAt(j)%>-<%=daily_Buyer_Nom_Contract_Type.elementAt(j)%>-<%=daily_Buyer_Nom_Fgsa_No.elementAt(j)%>-<%=daily_Buyer_Nom_Fgsa_Rev_No.elementAt(j)%>-<%=daily_Buyer_Nom_Sn_No.elementAt(j)%>-<%=daily_Buyer_Nom_Sn_Rev_No.elementAt(j)%>-<%=index%>" --%>
								</div>
								
							</td>
							<td>
								<div class="form-group mb-0">
									<input type="text" class="form-control" name="qty_scm"
										id="qty_scm<%=j%>"
										<%
										String total_scm = "0";
										if(Integer.parseInt(daily_Buyer_Rev_No.elementAt(j)+"") > 1){
											//System.out.println("total_inv_qty_scm.elementAt(j)----"+total_inv_qty_scm.elementAt(j));
// 											total_scm = nf2.format(Double.parseDouble(total_inv_qty_scm.elementAt(j)+"") + Double.parseDouble(daily_Buyer_Nom_Qty_Scm.elementAt(j)+""));
											%>
<%-- 											value="<%= total_scm %>" --%>
											
											<%if(max_nom_rev_no.elementAt(j).equals(daily_Buyer_Rev_No.elementAt(j))){
												total_scm = nf2.format(Double.parseDouble(total_inv_qty_scm.elementAt(j)+""));
											}else{
												total_scm = nf2.format(Double.parseDouble(total_inv_qty_scm.elementAt(j)+"") + Double.parseDouble(daily_Buyer_Nom_Qty_Scm.elementAt(j)+""));
											}%>
											value="<%= total_scm %>"
										<%}else{ %>
											value="<%=daily_Buyer_Nom_Qty_Scm.elementAt(j)%>"
										<%} %>
										style="text-align: right" readonly="readonly">
								</div>
							</td>
							<td>
								<div class="form-group mb-0">
									<%
										if (!daily_Buyer_Nom_Qty_Mmbtu.elementAt(j).equals("")) {
												double nomQtyTon = Double.parseDouble(daily_Buyer_Nom_Qty_Mmbtu.elementAt(j).toString())
														/ convt_mmbtu_to_mt;
												totalTon = totalTon + nomQtyTon;
									%>
									<input type="text" class="form-control" id="qty_tons<%=j%>"
										name="qty_tons" style="text-align: right"
										<%
										String total_mt = "0";
										if(Integer.parseInt(daily_Buyer_Rev_No.elementAt(j)+"") > 1){
// 											System.out.println("total_inv_qty_scm.elementAt(j)----"+total_inv_qty_mt.elementAt(j)+"*********"+nomQtyTon+"********"+daily_Buyer_Nom_Qty_Mmbtu.elementAt(j));
// 											total_mt = nf.format(Double.parseDouble(total_inv_qty_mt.elementAt(j)+"") + nomQtyTon);
											%>
<%-- 											value="<%= total_mt %>" --%>
											
											<%if(max_nom_rev_no.elementAt(j).equals(daily_Buyer_Rev_No.elementAt(j))){
												total_mt = nf.format(Double.parseDouble(total_inv_qty_mt.elementAt(j)+""));
											}else{
												total_mt = nf.format(Double.parseDouble(total_inv_qty_mt.elementAt(j)+"") + Double.parseDouble(nomQtyTon+""));
											}%>
											value="<%= total_mt %>"
										<%}else{ %>
											value="<%=nf.format(nomQtyTon)%>"
										<%} %>
										 readonly="readonly">
									<%
										} else {
									%>
									<input type="text" class="form-control" id="qty_tons<%=j%>"
										name="qty_tons" style="text-align: right" value=""
										readonly="readonly">
									<%
										}
									%>
								</div>
							</td>
							<td>
								<div class="form-group mb-0">
									<%-- <button type="button" Title="<%if(VBuyer_Delv_Base.elementAt(j).equals("D")) {%>Delivery Based<%} else {%>X-Terminal Based<%} %>" --%>
									<button type="button" Title="<%=title1 %>"
										onclick="setNominatedQty('<%=j%>','<%=(String) daily_Buyer_Nom_Abbr.elementAt(j)%>','<%=(String) daily_Buyer_Nom_Contract_Type.elementAt(j) + "-"
						+ (String) daily_Buyer_Nom_Sn_No.elementAt(j)%>','<%=gas_date%>','<%=(String) daily_Buyer_Nom_Plant_Abbr.elementAt(j)%>');"
										class="btn btn-info" <%=btnStatus%> <%=title%>>
										<img id="truckloadStatus" width="5" height="5" src="../images/checkmark.PNG">Select	Truck</button>
								</div>
								<script type="text/javascript">
								var ttle = '<%=title1%>';	
								var j = '<%=j%>';
								var indx = '<%=indx%>';
								if(ttle!='' && j == indx){
									alert(ttle)	
								}
								</script>
								
							</td>
						</tr>
						<%
							++index;
							}
						%>

						<input type="hidden" name="index_count" value="<%=index%>">
						<input type="hidden" name="percentage_of_dcq" value="<%=percentage_of_dcq%>">
						<%
							if (daily_Buyer_Nom_Contract_Type.size() > 0) {
						%>
						<tr class="success">


							<th class="text-center" colspan="5"><div class="mt-5">
									TOTAL NOMINATED QTY</div></th>

							<th>
								<div class="form-group mb-0">
									<%-- 			<input type="text" class="form-control" name="total_dcq_qty" value="<%=daily_Total_Dcq%>" readonly="readonly" style="text-align: right;"> --%>
								</div>
							</th>

							<th>
								<div class="form-group mb-0">
									<input type="text" class="form-control" name="total_mmbtu_qty"
										value="<%=daily_Total_Mmbtu%>" readonly="readonly"
										style="text-align: right;">
								</div>
							</th>

							<th>
								<div class="form-group mb-0">
									<input type="text" class="form-control" name="total_scm_qty"
										value="<%=daily_Total_Scm%>" readonly="readonly"
										style="text-align: right;">
								</div>
							</th>

							<th>
								<div class="form-groupmb-0 mobile-v">
									<input type="text" class="form-control w-100"
										name="total_ton_qty" <%if (totalTon == 0.0) {%> value=""
										<%} else {%> value="<%=nf.format(totalTon)%>" <%}%>
										readonly="readonly" style="text-align: right;">
								</div>
							</th>

							<th></th>
						</tr>
						<%
							} else {
						%>
						<tr>
							<th class="text-center" colspan="9"><div class="mt-5">
									No Contract Details Available!!</div></th>
							<%
								}
							%>
						
					</table>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<div class="row">
						<div class="col-sm-12 text-right">
							<button type="button" class="btn btn-warning" name="reSetPage"
								value="Reset" onClick="refreshPage();">Reset</button>
							<button type="button" class="btn btn-success" name="save" value="noTruck" onClick="doSubmit();">Submit</button>
						</div>
					</div>
				</div>
				<!-- /#page-wrapper -->

				<!-- Modal -->

				<%
					if (!selCust_id.equals("")) {
				%>
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg" role="document" style="width: 90%">
						<div class="modal-content">
							<div class="modal-header">

								<h2 class="modal-title" id="myModalLabel">
									<span id="selCustAbr"> </span>
								</h2>

								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<div class="box-body table-responsive no-padding">
									<table class="table" width="100%">

										<thead>
											<tr>
												<th colspan="1" style="vertical-align: middle;" width="10%">Total Qty. :</th>

												<th >
													<div class="form-group row">
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group input-group mb-0">
																<input type="text" class="form-control text-right"
																	id="nominated_qty_mmbtu" size="10" readonly="readonly" >
																<span class="input-group-addon">MMBTU </span>
															</div>
														</div>
													</div>
												</th>

												<th >
													<div class="form-group row">
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group input-group mb-0">
																<input type="text" class="form-control text-right"
																	id="nominated_qty_m3" size="10"  readonly="readonly"> <span
																	class="input-group-addon"> &#13221; </span>
															</div>
														</div>
													</div>
												</th>

												<th >
													<div class="form-group row">
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group input-group mb-0">
																<input type="text" class="form-control text-right"
																	id="nominated_qty_ton" size="5"  readonly="readonly"> <span
																	class="input-group-addon"> MT</span>
															</div>
														</div>
													</div>
												</th>
												
												<th colspan="1" style="vertical-align: middle;" width="10%">Balance Qty. :</th>

												<th >
													<div class="form-group row">
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="form-group input-group mb-0">
																<input type="text" class="form-control text-right" name="balance_mmbtu" id="" value="" size="10"  style="border: 0px;" readonly="readonly">
																<span class="input-group-addon">MMBTU </span>
															</div>
														</div>
													</div>
												</th>
											</tr>
										</thead>
									</table>	
								<table class="table  table-bordered">

										<thead>
											<tr>
												<th colspan="3" style="font-size: 16px; padding:">Total
													Trucks :<%=trucknmAT.size()%></th>
												<th colspan="3" style="font-size: 16px; padding:">Booked
													Trucks :<%=truckCount%></th>
												<th colspan="2" style="font-size: 16px; padding:">Available
													Trucks :<%=trucknmAT.size() - (truckCount + OnlyLoadedTruck.size())%></th>
												<th colspan="2"><input class="form-control"
													id="listSearch" maxlength="15" type="text"
													placeholder="Search For Trucks...."
													style="font-size: 16px; padding: 12px 20px 12px 40px; border-radius: 10px; border: 2px solid #ddd; height: 40px;">
												</th>
											</tr>
											<tr class="info" style="vertical-align: middle;">
												<th class="text-center" rowspan="2" colspan="2"
													style="vertical-align: middle;" width="14%">Truck No.
												</th>
												<th class="text-center" colspan="3">Truck Capacity</th>
												<th class="text-center" colspan="2">Truck Nomination
													Qty</th>
												<th class="text-center" colspan="2">Arrival</th>
												<th class="text-center" rowspan="2">Next Available (in Days)</th>
												<th class="text-center" rowspan="2"
													style="vertical-align: middle;">Remarks</th>
											</tr>
											<tr class="info ">
												<th class="text-center">MMBTU</th>
												<th class="text-center" width="5%">&#13221;</th>
												<th class="text-center">MT</th>
												<th class="text-center">MMBTU</th>
												<th class="text-center">MT</th>
												<th class="text-center">Date</th>
												<th class="text-center">Time</th>
											</tr>
										</thead>
										<tbody id="myList">
											<%
												for (int j = 0; j < custidAT.size(); j++) {
													
														double conv_m3_to_mmbtu = Double.parseDouble(tankVolM3AT.elementAt(j).toString())
																* conversion_factor_from_m3_to_mmbtu;
// 														System.out.println("OnlyLoadedTruck.size()***"+OnlyLoadedTruck.size());
// 														System.out.println("trucknmAT.elementAt(j)***"+trucknmAT.elementAt(j));
// 														System.out.println("truckLoadedFlag.elementAt(j)***"+truckLoadedFlag.elementAt(j));
														
														if (OnlyLoadedTruck.size() > 0) {
															if (OnlyLoadedTruck.contains((trucknmAT.elementAt(j).toString()))) {
																textdisabled = "readonly='readonly'";
															} else {
																textdisabled = "";
															}
														} else {
															textdisabled = "";
														}
														
														if(truckLoadedFlag.elementAt(j).equals("Y")){
															textdisabled = "readonly='readonly'";
														}
														
														String viewPrevData="";
											
											/* System.out.println("nomId***"+nomId+"***prevNomId**"+prevNomId.elementAt(j));
											System.out.println("nomId***"+contTyp+"****"+prevContTyp.elementAt(j)); */
// 											System.out.println("Vtruck_Inv_Flag***"+Vtruck_Inv_Flag.size()+"----"+custidAT.size());
											%>
											<%if(Vtruck_Inv_Flag.elementAt(j).toString().equalsIgnoreCase("Y")){
// 												loaded_qty+= Double.parseDouble(tot_inv_mmbtu+"");
												%>
												<tr style="background-color: #56B973;" title="Invoice Generted!!">
												
											<%}else if(Vtruck_Inv_Flag.elementAt(j).toString().equalsIgnoreCase("N") && Vtruck_alloc_flag.elementAt(j).toString().equalsIgnoreCase("Y") ){
												truck_loaded = "Y";
												loaded_qty+=Double.parseDouble(Vtruck_alloc_mmbtu.elementAt(j)+"");
											%>
												<tr style="background-color: #ffc966;" title="Truck Loading Done!!">
											<%}else if(!textdisabled.equals("")){%>
											
												<tr style="background-color: #A9DFD0;" title="Already Booked!!">
												
											<%}else{ %>
												<tr>
											<%} %>
												<td <%if(!textdisabled.equals("")){ %>
														<%if(nomId.equals(prevNomId.elementAt(j)) && contTyp.equals(prevContTyp.elementAt(j)) && nomDt.equals(prevTruckNomDt.elementAt(j))){ 
															textdisabled="";
														}else{ %>
															style="visibility: hidden;"
														<%} %>
													<%} %>
													
													<%if(Vtruck_Inv_Flag.elementAt(j).toString().equalsIgnoreCase("Y")){ %>
														style="visibility: hidden;"
													<%}if(Vtruck_alloc_flag.elementAt(j).toString().equalsIgnoreCase("Y")){ %>
														style="visibility: hidden;"
													<%} %>
													>
													
													<input type="checkbox" name="chkBox"
													onclick="loadQty('<%=j%>');" id="loadChkBox<%=j%>"
													<%=textdisabled%>> <input type="hidden"
													name="chkLoadFlag" value="N" id="chkLoadFlag<%=j%>">
												</td>

												<td>
													<div class="mt-5"><%=trucknmAT.elementAt(j)%>
														<input type="hidden" name="loadtrucknm"
															value="<%=trucknmAT.elementAt(j)%>" style="border: none;">
														<input type="hidden" name="Vtrans_cd"
															value="<%=VTruckTransCd.elementAt(j)%>">
															<br>
														<font size="1" color="red">	<%=prevCust_Nm.elementAt(j) %>
														&nbsp; <%=prevSn.elementAt(j)  %> &nbsp; <%=prevPlant.elementAt(j) %>
														</font>
														
													</div>
												</td>

												<td>
													<div class="mt-5 text-right">
														<%=Math.round(conv_m3_to_mmbtu)%></div> <input type="hidden"
													name="Tcap_in_mmbtu" id="Tcap_in_mmbtu<%=j%>"
													value="<%=conv_m3_to_mmbtu%>">
												</td>
												<td>
													<div class="mt-5 text-right"><%=tankVolM3AT.elementAt(j)%>
													</div> <input type="hidden" name="Tcap_in_m3"
													id="Tcap_in_m3<%=j%>"
													value="<%=tankVolM3AT.elementAt(j)%>">
												</td>
												<td>
													<div class="mt-5 text-right"><%=nf.format(Double.parseDouble(tankVolTonAT.elementAt(j).toString()))%></div>
													<input type="hidden" name="Tcap_in_ton"
													id="Tcap_in_ton<%=j%>"
													value="<%=Double.parseDouble(tankVolTonAT.elementAt(j).toString())%>">
												</td>
												<td>
													<div class="form-group mb-0">
														<input <%=textdisabled%> type="text" class="form-control text-right"
															name="load_mmbtu" id="load_mmbtu<%=j%>" style="background:YELLOW;"
															<%if(Vtruck_Inv_Flag.elementAt(j).toString().equalsIgnoreCase("Y")){ %>
																value="<%=Vtruck_Inv_qty.elementAt(j)%>"
															<%}else if(Vtruck_Inv_Flag.elementAt(j).toString().equalsIgnoreCase("N") && Vtruck_alloc_flag.elementAt(j).toString().equalsIgnoreCase("Y")){%>
																value="<%=Vtruck_alloc_mmbtu.elementAt(j) %>"
															<%}else{ %>
																value="<%=TloadedVol.elementAt(j)%>"
															<%} %>
															onmouseover="setCurrQty(this.value,'<%=j%>');"
															onchange="sameToSame('<%=j%>'); validateQty(this.value,'<%=j%>');"
															onkeyup="checkNumber1(this,9,2);checkForNumberWithDot(this)">
														
														<input type="hidden" name="chkbx_load_mmbtu"
															id="chkbx_load_mmbtu<%=j%>"> <input
															type="hidden" name="curr_load_mmbtu"
															id="curr_load_mmbtu<%=j%>" value=""> <input
															type="hidden" name="already_load_mmbtu"
															id="already_load_mmbtu<%=j%>"
															value="<%=TloadedVol.elementAt(j)%>">
													</div>
												</td>
												<td>
													<div class="form-group mb-0">
														<%
															if (!TloadedVol.elementAt(j).equals("")) {
																		double loadTon = Double.parseDouble(TloadedVol.elementAt(j).toString()) / convt_mmbtu_to_mt;
														%>
														<input <%=textdisabled%>  type="text" class="form-control text-right"
															name="load_ton" id="load_ton<%=j%>"
															<%if(Vtruck_Inv_Flag.elementAt(j).toString().equalsIgnoreCase("Y")){ %>
																value="<%=Vtruck_Inv_qty_mt.elementAt(j)%>"
															<%}else if(Vtruck_Inv_Flag.elementAt(j).toString().equalsIgnoreCase("N") && Vtruck_alloc_flag.elementAt(j).toString().equalsIgnoreCase("Y")){%>
																value="<%=Vtruck_alloc_mt.elementAt(j) %>"
															<%}else{ %>
																value="<%=Math.round(loadTon)%>"
															<%} %>
															onblur="checkNumber1(this,9,2);" readonly="readonly">
														<input type="hidden" class="form-control text-right"
															name="allready_load_ton" id="allready_load_ton<%=j%>"
															value="<%=Math.round(loadTon)%>">
														<%
															} else {
														%>
														<input <%=textdisabled%>  type="text" class="form-control text-right"
															name="load_ton" id="load_ton<%=j%>"
															onblur="checkNumber1(this,9,2);" 
															
															<%if(Vtruck_Inv_Flag.elementAt(j).toString().equalsIgnoreCase("Y")){ %>
																value="<%=Vtruck_Inv_qty_mt.elementAt(j)%>"
															<%}else if(Vtruck_Inv_Flag.elementAt(j).toString().equalsIgnoreCase("N") && Vtruck_alloc_flag.elementAt(j).toString().equalsIgnoreCase("Y")){%>
																value="<%=Vtruck_alloc_mt.elementAt(j) %>"
															<%}else{ %>
																value=""
															<%} %>
															readonly="readonly">
														<%
															}
														%>
													</div>
												</td>
												<td width="18%">
													<div class='input-group date <%if(textdisabled.equals("")) {%>datepick<%} %>' id='datetimepicker1<%=j%>'>
														<input <%=textdisabled%>  type='text' class="form-control datepick" id="d1"
															name="arrival_date" maxlength="11"  style="background:YELLOW;"
															<%if (TloadedDt.elementAt(j).equals("")) {%>
															value="<%=truck_load_date%><%//=formatters.format(date)%>" <%} else {%>
															value="<%=TloadedDt.elementAt(j)%>" <%}%> />
															
															<%if(textdisabled.equals("")) {%>
																<span class="input-group-addon"> <i class="fa fa-calendar"></i></span>
															<%} %>
													</div>
												</td>
												<td>
													<div class="form-group mb-0">
														<input <%=textdisabled%>  type="text" class="form-control text-center"
															name="arrival_time" id="arrival_time<%=j%>" maxlength="5"
															<%if (TloadedTm.elementAt(j).equals("")) {%>
															value="<%=formatter.format(time)%>" <%} else {%>
															value="<%=TloadedTm.elementAt(j)%>" <%}%>>
													</div>
												</td>
												<td>
													<div class="form-group mb-0">
														<select <%=textdisabled%>  class="form-control text-center" name="next_avail_days" id="next_avail_days<%=j%>">
															<option value="1" selected="selected"> 1</option>
															<option value="2"> 2</option>
															<option value="3"> 3</option>
															<option value="4"> 4 </option>
															<option value="5"> 5</option>
															<option value="6"> 6 </option>
															<option value="7"> 7</option>
															<option value="8"> 8</option>
															<option value="9"> 9</option>
															<option value="10"> 10</option>
															<option value="11"> 11</option>
															<option value="12"> 12</option>
															<option value="13"> 13</option>
															<option value="14"> 14</option>
															<option value="15"> 15</option>
														</select>
														<%if(!VLoadedNxt_avail_days.elementAt(j).equals("")) {%>
														<script type="text/javascript">
															document.getElementById('next_avail_days'+<%=j%>).value = '<%=VLoadedNxt_avail_days.elementAt(j)%>';
														</script>
														<%} %>
													</div>
												</td> 
												<td>
													<div class="form-group mb-3">
														<textarea <%=textdisabled%>  rows="2" cols="12" name="remark"
															id="remark<%=j%>"><%=TloadedRemark.elementAt(j)%></textarea>
													</div>
												</td>
											</tr>
											<%
												}
											%>
											<%
												if (trucknmAT.size() == 0) {
											%>
											<tr>
												<td colspan="10" class="text-center"
													style="color: red; font-weight: bold;">No Truck(s) available for the selected customer!!</td>
											</tr>
											<%
												}
											%>
										</tbody>
									</table>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-success"
									onclick="modaltruckLoad();">Submit</button>
							</div>
						</div>
					</div>
				</div>
				<%
					}
				%>
				<input type="hidden" name="truck_loaded_flag" id="truck_loaded_flag" value="<%=truck_loaded%>">
				<input type="hidden" name="loaded_qty" id="loaded_qty"  value="<%=loaded_qty%>">
			</form>

		</div>
		<!-- /.box -->
	</div>

	<!-- /.Allocation TAB START-->
	<div class="tab-pane" id="allocation">
		<h1>Allocation</h1>

	</div>
	<div class="tab-pane" id="invoicing">
		<h1>Invoicing</h1>
	</div>
</div>

<!-- jQuery -->
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript"
	src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script>

	$(function() {
		$('#datetimepicker1').datepicker({
			changeMonth : true,
			changeYear : true,
			format : "dd/mm/yyyy",
			language : "en",
			autoclose : true,
			todayHighlight : true,
			orientation : "bottom auto",
		});
	});

	$(function() {
		$('#datetimepicker2').datepicker({
			changeMonth : true,
			changeYear : true,
			format : "dd/mm/yyyy",
			language : "en",
			autoclose : true,
			todayHighlight : true,
			orientation : "bottom auto",
		});
	});

	$('.datepick').each(function() {
		$(this).datepicker({
			changeMonth : true,
			changeYear : true,
			format : "dd/mm/yyyy",
			language : "en",
			autoclose : true,
			todayHighlight : true,
			orientation : "bottom auto",
			startDate : "tommorow"
		});
	});

	$(document).ready(
			function() {
				$("#listSearch").on(
						"keyup",
						function() {
							var value = $(this).val().toLowerCase();
							$("#myList tr").filter(
									function() {
										$(this).toggle(
												$(this).text().toLowerCase()
														.indexOf(value) > -1)
									});
						});
			});
</script>
</body>
<%}catch(Exception e){ 
	e.printStackTrace();
} %>
</html>
