<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Customer Invoice Details</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link rel="stylesheet" href="../css/guidefaultGeneral.css">
<link rel="stylesheet" href="../css/utilities.css">

<script type="text/javascript" src="../js/date-picker.js"></script>
<script type="text/javascript" src="../js/aris.js"></script>
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
<script type="text/javascript">
function replaceString()
{
	var adv_inv_value = '';
	var obj_chk_adv = document.forms[0].chk_mul_adv_inv;
	if(obj_chk_adv != null ){
		var chk_mul_adv_inv = document.forms[0].chk_mul_adv_inv.value;
		if(chk_mul_adv_inv == "Y") {
			var adv_inv_len = document.forms[0].adv_inv.length;
			if(adv_inv_len == undefined) {
				var data = document.getElementById("adv_inv0").value;
				var new_data = '';
				if(data != '' && data != ' ') {
					var splited_var = data.split("@#@#");
					for(var j=0;j<splited_var.length;j++) {
						new_data += splited_var[j]+" ";
					}
					document.getElementById("adv_inv0").value = new_data;
				} 
			} else {
				for(var i = 0;i<adv_inv_len;i++){
					var data = document.getElementById("adv_inv"+i).value;
					var new_data = '';
					if(data != '' && data != ' ') {
						var splited_var = data.split("@#@#");
						for(var j=0;j<splited_var.length;j++) {
							new_data += splited_var[j]+" ";
						}
						document.getElementById("adv_inv"+i).value = new_data;
					} 
				}
			}
		} 
	}
}
var newWindow;
function saveadvancedata()
{
	document.forms[0].Adv_submitted_flag.value='Y';
	var adv_inv_no=document.forms[0].adv_inv_no.value;
	var adv_inv_dt=document.forms[0].adv_inv_dt.value;
	var currency=document.forms[0].currency.value;
	var recevial=document.forms[0].recevial.value;
	var adjustamt=document.forms[0].adjustamt.value;
	var priceINR2=document.forms[0].priceINR2.value;
	var adjustsign=document.forms[0].adjustsign.value;
	adjustamt=round(adjustamt,2);
	
	var customer_cd=document.forms[0].customer_cd.value;
	var raw_amt_usd=document.forms[0].raw_amt_usd.value;
	var raw_amt_inr=document.forms[0].raw_amt_inr.value;
	var fgsa_no=document.forms[0].fgsa_no.value;
	var fgsa_rev_no=document.forms[0].fgsa_rev_no.value;
	var sn_no=document.forms[0].sn_no.value;
	var sn_rev_no=document.forms[0].sn_rev_no.value;
	var contract_type=document.forms[0].contract_type.value;
	var customer_plant_seq_no=document.forms[0].customer_plant_seq_no.value;
	var bill_period_start_date=document.forms[0].bill_period_start_date.value;
	var hlpl_Inv_Seq_No=document.forms[0].hlpl_Inv_Seq_No.value;
	var financial_Year=document.forms[0].financial_Year.value;
	var bill_period_end_date=document.forms[0].bill_period_end_date.value;
	var invoice_date=document.forms[0].invoice_date.value;
	var mapping_id=document.forms[0].mapping_id.value;
	var	exch_rate_val=document.forms[0].exch_rate_val.value;
	var activity=document.forms[0].activity.value;
	var payment_md=document.forms[0].pay_type1.value; //HS20160722
	
	var flag=false;
	var msg="Please Check Following details:\n";
	if(adv_inv_no=='')
	{
		flag=true;
		msg+="Advance Invoice No. \n";
	}
	if(adv_inv_dt=='')
	{
		flag=true;
		msg+="Advance invoice Dt \n";
	}
	if(adjustamt=='')
	{
		flag=true;
		msg+="Amount to be Adjusted \n";
	}
	var adv_inv_value = ''; var adv_inv_date = '';
	var obj_chk_adv = document.forms[0].chk_mul_adv_inv;
	if(obj_chk_adv != null ){
	var chk_mul_adv_inv = document.forms[0].chk_mul_adv_inv.value;
	if(chk_mul_adv_inv == "Y") {
		var adv_inv_len = document.forms[0].adv_inv.length;
		if(adv_inv_len == undefined) {
			var data = document.getElementById("adv_inv0").value;
			var date = document.getElementById("adv_inv_date0").value;
			if(data == '' || data == ' ' || date == '' || date == ' ') {
				flag = true; msg += 'Advance Invoice No Or Date Is Not Available \n';
			} else { adv_inv_value = data; adv_inv_date = date; }
		} else {
			for(var i = 0;i<adv_inv_len;i++){
				var data = document.getElementById("adv_inv"+i).value;
				var date = document.getElementById("adv_inv_date"+i).value;
				if(data == '' || data == ' ' || date == '' || date == ' ') {
					flag = true; msg += 'Advance Invoice No Or Date Is Not Available \n';
				} else { adv_inv_value += data+"@@"; adv_inv_date += date+"@@"; }
			}
		}
	} }
	if(flag==false)
	{
				if (window.XMLHttpRequest)
				{
					request22 = new XMLHttpRequest();
				} 
				else if (window.ActiveXObject)
				{
					request22 = new ActiveXObject("Microsoft.XMLHTTP");
				}
				if(request22)
			   	{
			  		 var url="../advance_payment/frm_save_advance_data_ajax.jsp?adv_inv_date="+adv_inv_date+"&adv_inv_no="+adv_inv_no+"&chk_mul_adv_inv="+chk_mul_adv_inv+"&adv_inv_value="+adv_inv_value+"&customer_cd="+customer_cd+"&raw_amt_usd="+raw_amt_usd+"&raw_amt_inr="+raw_amt_inr+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_seq_no="+customer_plant_seq_no+"&hlpl_Inv_Seq_No="+hlpl_Inv_Seq_No+"&financial_Year="+financial_Year+"&invoice_date="+invoice_date+"&mapping_id="+mapping_id+"&bill_period_end_date="+bill_period_end_date+"&adv_inv_dt="+adv_inv_dt+"&currency="+currency+"&recevial="+recevial+"&adjustamt="+adjustamt+"&priceINR2="+priceINR2+"&adjustsign="+adjustsign+"&exch_rate_val="+exch_rate_val+"&activity="+activity+"&payment_md="+payment_md;
			  		
					 //alert(url);
			  		 request22.open("POST",url);
			  		 request22.onreadystatechange = function() 
			    	 {
			    		 if(request22.readyState==4)
			     		 {	
			     		 		document.getElementById("submited_text").innerHTML="<b>Submitted..</b>";
				  	  	 }
					 }
			  		 request22.send(null);
			     }
				 else 
				 {
				    alert("Your browser doesn't support AJax !"); 
				 }
	}
	else
	{
		alert(msg);
	}
	
}

function tariff_setter(o1)
{
if(o1!=undefined) {
	if(o1.checked)
	{	
		document.forms[0].Tariff_flag_Setter.value='Y';
	}
	else
	{
		document.forms[0].Tariff_flag_Setter.value='N';
	}}
	else
	{	
		document.forms[0].Tariff_flag_Setter.value='N';
	}
}

function discount_setter(o2)
{
if(o2!=undefined) {
	if(o2.checked)
	{	
		document.forms[0].Discount_flag_Setter.value='Y';
	}
	else
	{	
		document.forms[0].Discount_flag_Setter.value='N';
	}
	}else
	{	
		document.forms[0].Discount_flag_Setter.value='N';
	}
	
}

function applydiscount(obj1,customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,hlpl_inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_mthd,sz,period_sz,flag,entry)
{
if(document.forms[0].Discount_flag_Mst.value=='Y') {
var disc= document.forms[0].discount_value.value;
var deducted_disc=parseFloat(document.forms[0].original_rate.value)-parseFloat(disc);
//alert(deducted_disc);
document.forms[0].discount_val_deducted.value=deducted_disc;
document.forms[0].entrypoint.value=entry;

	if(obj1.checked)
	{	document.forms[0].discount_flag_apply.value='Y';
		//alert(document.forms[0].discount_flag_apply.value);
		setExchangeRate(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,hlpl_inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_mthd,sz,period_sz,flag);
	}
	else
	{	//alert("ELSE HERE");
		document.forms[0].discount_flag_apply.value='N';
		setExchangeRate(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,hlpl_inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_mthd,sz,period_sz,flag);
	}
}	
else		
{
document.forms[0].discount_flag_apply.value='N';
		setExchangeRate(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,hlpl_inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_mthd,sz,period_sz,flag);	
}
}


function call_on_load()
{
//alert("hi");
var tariffflag=document.forms[0].tariffflag.value;
var modifyadjflag=document.forms[0].modifyadjflag.value;
//alert(modifyadjflag);
if(modifyadjflag=='Y')
{
if(tariffflag=='Y') 
	{//alert("YES");
		document.forms[0].call_on_load_hid.value="call";
		//displaysalepricedetails('L');
	}
	displaydetailsmodify();
}
else if(tariffflag=='Y') 
	{//alert("YES");
		//document.forms[0].call_on_load_hid.value="call";
		displaysalepricedetails('L');
	}
}


function caluclate_tax_details_ajax(grossamt,taxcd)
{
	//alert(grossamt);
		var customer_cd=document.forms[0].customer_cd_tax.value;
		var customer_plant_seq_no=document.forms[0].customer_plant_seq_no.value;
		var contract_type=document.forms[0].contract_type.value;
		var mapping_id=document.forms[0].mapping_id.value;
		
		var enddt=document.forms[0].inv_dt.value;
				if (window.XMLHttpRequest)
				{
					request22 = new XMLHttpRequest();
				} 
				else if (window.ActiveXObject)
				{
					request22 = new ActiveXObject("Microsoft.XMLHTTP");
				}
				if(document.forms[0].activity.value=='update')
				{
					if(document.getElementById("modifytaxdetails")==undefined)
					{
					}
					else
					{
						document.getElementById("modifytaxdetails").innerHTML="";
					}
				}
				if(request22)
			   	{
			  		 var url="../advance_payment/frm_tax_calculate_ajax.jsp?taxcd="+taxcd+"&inv_dt="+enddt+"&grossamt="+grossamt+"&customer_plant_seq_no="+customer_plant_seq_no+"&customer_cd="+customer_cd+"&contract_type="+contract_type+"&mapping_id="+mapping_id;
			  		 request22.open("POST",url);
			  		 request22.onreadystatechange = function() 
			    	 {
			    		 if(request22.readyState==4)
			     		 {
			     			var a=document.getElementById("adjustmenttax").innerHTML=request22.responseText;    
							document.getElementById("adjustmenttax").style.visibility="visible";
			    			 	 //Added for tax calculation update on completion of ajax event
			    			 	 var len1=document.getElementById("cust_tax_size").value;
			    			 	 //var len1=document.forms[0].cust_tax_size.length;
			    			 	 
							 	for(var i=0;i<len1;i++)
							 	{
					 					var temp="cust_tax_amt"+i;
					 					var temptax_amt=document.getElementById(temp).value;  
					 					//alert("taxamt---"+temptax_amt);
					 					var tmp="tax_amts"+i;
					 					document.getElementById(tmp).value=temptax_amt;
								 }
								var net_amt_inr=document.getElementById("invoice_inr_amt_adjusted").value;
								//alert("--net--"+net_amt_inr);
								document.getElementById("net_amt_inr").value=net_amt_inr;
								//alert("after caluclate_tax_details_ajax: "+document.forms[0].net_amt_inr.value);
								
				  	  	 }
					 }
			  		 request22.send(null);
			     }
				 else 
				 {
				    document.getElementById("adjustmenttax").innerHTML="";
				    document.getElementById("adjustmenttax").style.visibility="hidden";
				    alert("Your browser doesn't support AJax !"); 
				 }
}

function displaysalepricedetails(entry)
{

//alert(".............");

var obj=document.forms[0].sale_price_inr_check;
var contract_type=document.forms[0].contract_type.value;
var invoice_sales_price_inr=document.forms[0].invoice_sales_price_inr.value;

var exchrate=document.forms[0].exch_rate_val.value;
var sale_price=document.forms[0].sale_price.value;
//alert(document.forms[0].discount_flag_apply.value);
if(document.forms[0].discount_flag_apply!=undefined){
if(document.forms[0].discount_flag_apply.value=='Y')
{
	sale_price=document.forms[0].discount_val_deducted.value;
}}
var offspec_flag = document.forms[0].offspec_flag.value;
	var offspec_rate = "0";
	var offspec_qty = "0";
	if(offspec_flag=='Y')
	{
		offspec_rate = document.forms[0].offspec_rate.value;
		offspec_qty = document.forms[0].offspec_qty.value;
	}
//alert(sale_price);

var total_qty=document.forms[0].total_qty.value;
//alert(entry);
//alert(obj);
if(entry=='L' &&  document.forms[0].activity.value=='insert' && document.forms[0].page_refresh_flag.value=='N')
{	
	//alert("NEGLECT");
}
else
{
	//alert("HELLO----->"+document.forms[0].sale_price_inr_check.checked);
	if(obj.checked){
	if(exchrate=='' || exchrate==" ")
	{
		alert("Please select the proper exchange rate..!!");
		obj.checked=false;
	} 
	else
	{
	
					//document.getElementById("salepricediv").style.visibility="visible";
					if (window.XMLHttpRequest)
					{
						request21 = new XMLHttpRequest();
					} 
					else if (window.ActiveXObject)
					{
						request21 = new ActiveXObject("Microsoft.XMLHTTP");
					}
					
					
					document.getElementById("salepricediv").innerHTML="";
					
					if(request21)
				   	{
				  		// document.getElementById("display").style.display='block';
				  		  var url="../advance_payment/frm_invoice_saleprice_ajax.jsp?contract_type="+contract_type+"&invoice_sales_price_inr="+invoice_sales_price_inr+"&exchrate="+exchrate+"&sale_price="+sale_price+"&total_qty="+total_qty+"&offspec_flag="+offspec_flag+"&offspec_rate="+offspec_rate+"&offspec_qty="+offspec_qty;
				  		  request21.open("POST",url);
				  		  request21.onreadystatechange = function() 
				    	  {
				   			 if(request21.readyState==4)
				     		 {
				     		 	//alert(request21.responseText);
				     		 	//
					 				var a=document.getElementById("salepricediv").innerHTML=request21.responseText; 
					 			 	//alert(document.getElementById("salepricediv").innerHTML); 
					 			  
					 			  	document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].grosssalespriceINR.value,2);
					 			  	var grossamt=document.forms[0].double_final_gross_amt_inr.value;
									var taxcd=document.forms[0].taxstructcd.value;
		
									
									var val;
									if(document.forms[0].adjust==undefined)
									{
										caluclate_tax_details_ajax(grossamt,taxcd);
									}
									else
									{
										for(var i=0;i<document.forms[0].adjust.length;i++)
										{
											if(document.forms[0].adjust[i].checked)
											{
												val=document.forms[0].adjust[i].value;
											}						
										}
										//alert(val);
										if(val=='Y')
										{
											document.forms[0].call_flag_sale_price.value='Y';
											adjustmamountforadjust();
										}
										else
										{
											caluclate_tax_details_ajax(grossamt,taxcd);
										}
									
									
					 			  document.forms[0].call_on_load_hid2.value="call";
					 			  
					 			  
								//document.getElementById("salepricediv").style.visibility="visible";
				    					 	 
				    			 	//**Added for tax calculation update on completion of ajax event
				    			 	//var len=document.getElementById("cust_tax_size").value;
									//alert("VALUE--"+document.forms[0].cust_tax_amt1.value);
								 	/*for(var i=0;i<len;i++)
								 	{
						 					var temp="cust_tax_amt"+i;
						 					var temptax_amt=document.getElementById(temp).value;  
						 					//alert("taxamt---"+temptax_amt);
						 					var tmp="tax_amts"+i;
						 					document.getElementById(tmp).value=temptax_amt;
									}
									var net_amt_inr=document.getElementById("invoice_inr_amt_adjusted").value;
									document.getElementById("net_amt_inr").value=net_amt_inr;*/
									//**end 
									}									
				     		 }
						 }
				  		 request21.send(null);
				     }
					 else 
					 {
					    document.getElementById("salepricediv").innerHTML="";
					    
					    alert("Your browser doesn't support AJax !"); 
					 }
	}
	}
	else
	{
						document.getElementById("salepricediv").innerHTML="";
						//alert(document.forms[0].adjust.length);
						var val='N';
						var grossamt;
						if(document.forms[0].adjust!=undefined){
						for(var i=0;i<document.forms[0].adjust.length;i++)
						{
							if(document.forms[0].adjust[i].checked)
							{
								val=document.forms[0].adjust[i].value;
							}						
						}}
						//alert(val);
						if(val=='Y')
						{
							document.forms[0].call_flag_sale_price.value='N';
							adjustmamountforadjust();
							document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].priceINR2.value,2);
							grossamt=document.forms[0].double_final_gross_amt_inr.value;
						}
						else
						{
							document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].gross_amt_inr.value,2);
							grossamt=document.forms[0].double_final_gross_amt_inr.value;
						}
						//alert(grossamt);
						var taxcd=document.forms[0].taxstructcd.value;
						caluclate_tax_details_ajax(grossamt,taxcd);
					    
	}

}	
}



function getadvancedetails()
{

var customer_cd=document.forms[0].customer_cd_tax.value;
var customer_abr=document.forms[0].customer_abbr_tax.value;
var currency=document.forms[0].currency.value;
var exchrate=document.forms[0].exch_rate_val.value;
var invdate=document.forms[0].exch_rate_dt.value;

var AdjustFlagForMst=document.forms[0].AdjustFlagForMst.value;
var modifyadjflag=document.forms[0].modifyadjflag.value;

		var mcontract_type=document.forms[0].contract_type.value;
		var mhlpl_inv_seq_no2=document.forms[0].hlpl_inv_seq_no2.value;
		var minv_financial_year=document.forms[0].inv_financial_year.value;
		var mcustomer_cd=document.forms[0].customer_cd_tax.value;
		var mcustomer_plant_seq_no=document.forms[0].customer_plant_seq_no.value;
		var mbill_period_end_dt=document.forms[0].inv_dt.value;
		var mexchangerateselected=document.forms[0].exch_rate_val.value;
		var activity = document.forms[0].activity.value;


//alert(customer_cd);
//alert(exchrate);
//alert(invdate);

	if(exchrate=='' || exchrate==" ")
	{
		alert("Please select the proper exchange rate..!!");
	} 
	else
	{
	if(!newWindow || newWindow.closed)
		{
			newWindow = window.open("../advance_payment/frm_invoice_adjustment_details.jsp?customer_cd="+customer_cd+
			"&exchrate="+exchrate+"&invdate="+invdate+"&customer_abr="+customer_abr+
			"&currency="+currency+"&mcontract_type="+mcontract_type+"&mhlpl_inv_seq_no2="+mhlpl_inv_seq_no2+"&minv_financial_year="+minv_financial_year+"&mcustomer_cd="+mcustomer_cd+"&mcustomer_plant_seq_no="+mcustomer_plant_seq_no+"&mbill_period_end_dt="+mbill_period_end_dt+"&activity="+activity+"&modifyadjflag="+modifyadjflag,"AdjustDetail","top=10,left=10,width=900,height=300,scrollbars=1,status=1");
		}
		else
		{
			newWindow.close();
		    newWindow = window.open("../advance_payment/frm_invoice_adjustment_details.jsp?customer_cd="+customer_cd+"&exchrate="+exchrate+"&invdate="+invdate+"&customer_abr="+customer_abr+
		    "&currency="+currency+"mcontract_type="+mcontract_type+"&mhlpl_inv_seq_no2="+mhlpl_inv_seq_no2+"&minv_financial_year="+minv_financial_year+"&mcustomer_cd="+mcustomer_cd+"&mcustomer_plant_seq_no="+mcustomer_plant_seq_no+"&mbill_period_end_dt="+mbill_period_end_dt+"&activity="+activity+"&modifyadjflag="+modifyadjflag,"AdjustDetail","top=10,left=10,width=900,height=300,scrollbars=1,status=1");
		}
	}
}


function adjustcurrency()
{
		
	document.forms[0].adjustamt.value="";
	document.forms[0].availablebalance.value="";
	document.forms[0].priceINR2.value="";
	document.forms[0].gross_amt_inr_adjusted.value="";
	
	document.getElementById("availablebaldiv").innerHTML="";
	document.getElementById("adjustamtdiv").innerHTML="";
	document.getElementById("gross_amt_inr_adjusteddiv").innerHTML="";
	
}


function adjustmamount(advamount,advcur,advadt,advadjustedamt,advseq_no)
{
	//alert("hi");
	//alert("hello-"+advamount[1]);
	//alert("hi....");
	var adjustamt=document.forms[0].adjustamt.value;
	var advanceamt=document.forms[0].availablebalance.value;
	
	
	// create hidden elements for storing advance adjustment details.....	
	document.forms[0].hiddenadvnclength.value=advamount.length;
	var divelem=document.getElementById("hiddenadvancedetails");
	divelem.innerHTML="";
	
	for(var i=0;i<=advamount.length;i++)
	{
		var nminput1=document.createElement("input");
					nminput1.type="hidden";
					nminput1.name="advamount"+i;
					nminput1.id="advamount"+i;
					nminput1.value=advamount[i];
			 
	 	var nminput2=document.createElement("input");
					nminput2.type="hidden";
					nminput2.name="advcur"+i;
					nminput2.id="advcur"+i;
					nminput2.value=advcur[i];
					
		var nminput3=document.createElement("input");
					nminput3.type="hidden";
					nminput3.name="advadt"+i;
					nminput3.id="advadt"+i;
					nminput3.value=advadt[i];
					
		var nminput4=document.createElement("input");
					nminput4.type="hidden";
					nminput4.name="advadjustedamt"+i;
					nminput4.id="advadjustedamt"+i;
					nminput4.value=advadjustedamt[i];
					
		var nminput5=document.createElement("input");
					nminput5.type="hidden";
					nminput5.name="advseq_no"+i;
					nminput5.id="advseq_no"+i;
					nminput5.value=advseq_no[i];
					
				divelem.appendChild(nminput1);	
				divelem.appendChild(nminput2);	
				divelem.appendChild(nminput3);	
				divelem.appendChild(nminput4);	
				divelem.appendChild(nminput5);	
				//alert("END");
	}
	
//	alert("VAL---"+document.forms[0].advadt0.value);
	
	var flag=checkadjustlimit();
	//alert("fl..ga"+flag);
	if(flag==true)
	{
		if(adjustamt=='' || adjustamt==" ")
		{
			alert('Enter proper adjust amount');
		}
		else
		{
			var adjustsign=document.forms[0].adjustsign.value;
			var cur=document.forms[0].currency.value;
			//alert("cur--"+cur);
			if(cur==0)
			{
				//alert("hello..");
				var grossusd=document.forms[0].gross_amt_usd.value;
				var exchrate=document.forms[0].exch_rate_val.value;
				
				var calculatedamt=0;
				if(adjustsign=='1')
				{
					if(parseFloat(grossusd)==parseFloat(adjustamt)) {
						calculatedamt=0;
					} else {
						calculatedamt=parseFloat(grossusd)-parseFloat(adjustamt);
					}
				}
				else if(adjustsign=='2')
				{
					calculatedamt=parseFloat(grossusd)+parseFloat(adjustamt);
				}
				
				document.forms[0].gross_amt_inr_adjusted.value=round(calculatedamt,2);
				var calculatedamtinr=parseFloat(calculatedamt)*parseFloat(exchrate)
				document.forms[0].priceINR2.value=round(calculatedamtinr,2);
				
			}
			else if(cur==1)
			{
				var grossinr=document.forms[0].gross_amt_inr.value;
				var calculatedamt=0;
				if(adjustsign=='1')
				{
					if(parseFloat(grossinr)==parseFloat(adjustamt)) {
						calculatedamt=0;	
					} else {
						calculatedamt=parseFloat(grossinr)-parseFloat(adjustamt);
					}
					
				}
				else if(adjustsign=='2')
				{
					calculatedamt=parseFloat(grossinr)+parseFloat(adjustamt);
				}
				
				document.forms[0].gross_amt_inr_adjusted.value=round(calculatedamt,2);
				document.forms[0].priceINR2.value=round(calculatedamt,2);
			}
			var grossamt=document.forms[0].priceINR2.value;
			var taxcd=document.forms[0].taxstructcd.value;
			caluclate_tax_details_ajax(grossamt,taxcd);
		}			 
	}
	else
	{
		alert("Amount to be adjusted is greater than the balance available..!!");
	}
	
}
function checkNumber1(obj,a,b)
{ 
	var c = parseInt(a)-parseInt(b);
	var flag=true;
	
	var fieldValue=obj.value;
    
    var len = 0;
    
    var str = fieldValue.substring(0,fieldValue.indexOf('.')).length;
	
	if(str == 0)
	{
		len = fieldValue.length;
	}
	else
	{
		len = str;
	}
    
    if(obj.value!="" && obj.value!=null && obj.value!=' ')
    {
		if((parseInt(len) > parseInt(c)))
		{
    		alert("Please, Enter In the Required  Format.."+'('+c+' ,'+b+' )');
			obj.value= "";
			obj.select();
			flag = false;
		}
		else
		{
			var decallowed = b;  // how many decimals are allowed?
        
        	if(isNaN(fieldValue) || fieldValue == "")
        	{
        		alert("Please, Enter In the Required  Format.."+'('+c+' ,'+b+' )');
 		    	obj.value="";
	 	    	obj.select();	 
				flag=false;
        	}
      		else
      		{
         		if(fieldValue.indexOf('.') == -1) 
		    	{
		    		fieldValue += ".";
         		}
         	
         		dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);
         	
         		if(parseInt(dectext.length) > parseInt(decallowed))
            	{
		    		alert("Please, Enter In the Required  Format.."+'('+c+' ,'+b+') !!!');
             		obj.value="";
             		obj.select();		
			 		flag=false;
            	}
         		else
         		{
              		flag=true;
            	}
        	}
		}
   	}
    
    return flag;
}


function chkno(no,obj)
{
	//alert(no);
	//var b= "adjustamt"+i;
	
	var barcode_length=no.length;
	//alert(barcode_length);
	var flg = true;
	var msg = "";
	
	if(no.value != "")
	{
	    	var chk = no;
	       	for(var i=0;i<barcode_length;i++)
	       	{
	        	var j=chk.substr(i,1);
	         	if(j=='0' || j=='1' || j=='2'|| j=='3' || j=='4' || j=='5' || j=="6"|| j=='7' || j=='8' || j=='9' || j=='.')
	         	{
	           		flg = true;
	           		document.forms[0].adjustamt.style.background='#FFFFFF';
	         	}
				else
		        {
		        	flg = false;
		            break;
		        }
	        
	       	}
	      // 	alert(flg);
	       	if(flg==true)
	       	{
	       		if(parseFloat(chk)<0)
	       		{	
	       			flg=false;
	       		}
	       	}
	       		//alert(flg);
		    if(flg==false)
			{
				msg += "Invalid No\n";
				obj.style.background='#FF6A6A';
				obj.value = '';
				obj.focus();
			}
			else
			{
			//alert("here");
				obj.style.background='#FFFFFF';
			}
			
	}
	if(!flg)
	{
		alert(msg);
		return flg;
	}
	else
	{
		return flg;
	}
		
}




function adjustmamountforadjust()
{ //alert(document.forms[0].currency.value);
	var adjustamt=document.forms[0].adjustamt.value;
	var advanceamt=document.forms[0].availablebalance.value;
	var negflag=false;
//	var flag1=chkno(adjustamt,document.forms[0].adjustamt);
	var flag1=checkNumber1(document.forms[0].adjustamt,14,2);
	if(flag1==true)
	{
		var flag=checkadjustlimit();
		if(flag==true)
		{
			if(adjustamt=='' || adjustamt==" ")
			{
				alert('Enter proper adjust amount');
			}
			else
			{
				var adjustsign=document.forms[0].adjustsign.value;
				var cur=document.forms[0].currency.value;
				if(cur==0)
				{	//alert(cur);
					var grossusd=document.forms[0].gross_amt_usd.value;
					var exchrate=document.forms[0].exch_rate_val.value;
					
					var grossinr=document.forms[0].gross_amt_inr.value;//added new
					
					if(document.forms[0].sale_price_inr_check==undefined)
					{
						grossusd=document.forms[0].gross_amt_usd.value;
						grossinr=document.forms[0].gross_amt_inr.value;//added new
					}
					else
					{
						if(document.forms[0].sale_price_inr_check.checked)
						{//alert(document.getElementById("salepricediv").innerHTML); 
						//alert(document.forms[0].gross_USD_salespriceINR);
						//alert("here in inr ariff");
							grossusd=document.forms[0].gross_USD_salespriceINR.value;
							grossinr=document.forms[0].grosssalespriceINR.value;//added new
						}
						else
						{
							grossusd=document.forms[0].gross_amt_usd.value;
							grossinr=document.forms[0].gross_amt_inr.value;//added new
						}
					}
					
					var calculatedamt=0;
					var calculatedamtinrnew=0;//added new
					if(adjustsign=='1')
					{
						if(parseFloat(grossusd)==parseFloat(adjustamt)) {
							calculatedamt=0;
							var adjustamtinr=grossinr;//added new
							calculatedamtinrnew=0;//added new
						} else {
							calculatedamt=parseFloat(grossusd)-parseFloat(adjustamt);
							var adjustamtinr=parseFloat(adjustamt)*parseFloat(exchrate);//added new
							calculatedamtinrnew=parseFloat(grossinr)-parseFloat(adjustamtinr);//added new
						}
						
					}
					else if(adjustsign=='2')
					{
						calculatedamt=parseFloat(grossusd)+parseFloat(adjustamt);
						var adjustamtinr=parseFloat(adjustamt)*parseFloat(exchrate);//added new
						calculatedamtinrnew=parseFloat(grossinr)+parseFloat(adjustamtinr);//added new
					}
					if(parseFloat(adjustamt)>parseFloat(grossusd))
					{
						negflag=true;
					}
					else
					{
						document.forms[0].gross_amt_inr_adjusted.value=round(calculatedamt,2);
						var calculatedamtinr=parseFloat(calculatedamt)*parseFloat(exchrate);;
						document.forms[0].priceINR2.value=round(calculatedamtinrnew,2);
						document.forms[0].inrAmt1.value=round(calculatedamtinrnew,2); //HS20160905
							//alert(calculatedamtinrnew);
						
							if(document.forms[0].adjustdetails[1].checked){ //HS20160903
								document.forms[0].double_final_gross_amt_inr.value=round(calculatedamtinrnew,2);
								var taxratesize=document.getElementById('taxratesize').value;
								var taxadjamt=0;
								for(l=0;l<taxratesize;l++){
									var taxrate=document.getElementById('taxrate'+l).value;
									document.forms[0].tax_amts[l].value=round((round(calculatedamtinrnew,2)*taxrate/100),2);
									taxadjamt+=parseFloat(document.forms[0].tax_amts[l].value);
								}
								//alert("---"+taxadjamt);
								document.forms[0].total_tax.value=round(taxadjamt,2);
								document.forms[0].net_amt_inr.value=round((parseFloat(calculatedamtinrnew)+parseFloat(taxadjamt)),2);
							}
							var grs_inr="0";
							if(document.forms[0].gross_amt_inr_adjusted_tax.value=="" || document.forms[0].gross_amt_inr_adjusted_tax.value=="0"){
								grs_inr=document.forms[0].total_tax.value;
								document.forms[0].gross_amt_inr_adjusted_tax.value=round(grs_inr,2);} 
							else { var amt = document.forms[0].adjustamt_tax.value;
							if(amt=='' || amt=='0') amt = 0;
								document.forms[0].gross_amt_inr_adjusted_tax.value = round((parseFloat(document.forms[0].total_tax.value)-parseFloat(amt)),2);
								grs_inr=document.forms[0].gross_amt_inr_adjusted_tax.value;} 
							if(document.forms[0].gross_amt_inr_adjusted_sbc.value=="" || document.forms[0].gross_amt_inr_adjusted_sbc.value=="0"){
								grs_inr=document.forms[0].total_tax.value;
								document.forms[0].gross_amt_inr_adjusted_sbc.value=round(grs_inr,2);
							} else{var amt = document.forms[0].adjustamt_sbc.value;
							if(amt=='' || amt=='0') amt = 0;
								document.forms[0].gross_amt_inr_adjusted_sbc.value = round((parseFloat(grs_inr)-parseFloat(amt)),2); 
								grs_inr=document.forms[0].gross_amt_inr_adjusted_sbc.value;} 
							if(document.forms[0].gross_amt_inr_adjusted_kkc.value=="" || document.forms[0].gross_amt_inr_adjusted_kkc.value=="0"){
								grs_inr=document.forms[0].total_tax.value;
								document.forms[0].gross_amt_inr_adjusted_kkc.value=round(grs_inr,2);
							}else{var amt = document.forms[0].adjustamt_kkc.value;
								if(amt=='' || amt=='0') amt = 0;
								document.forms[0].gross_amt_inr_adjusted_kkc.value = round((parseFloat(grs_inr)-parseFloat(amt)),2); 	
								grs_inr=document.forms[0].gross_amt_inr_adjusted_kkc.value; } 
							
						document.forms[0].net_amt_inr.value=round((parseFloat(grs_inr)+parseFloat(calculatedamtinrnew)),2);	
						//alert("after adjustmamountforadjust if cur 0: "+document.forms[0].net_amt_inr.value);
					}
				}
				else if(cur==1)
				{
					var grossinr;
					if(document.forms[0].sale_price_inr_check==undefined)
					{
						grossinr=document.forms[0].gross_amt_inr.value;
					}
					else
					{
						if(document.forms[0].sale_price_inr_check.checked)
						{
							grossinr=document.forms[0].grosssalespriceINR.value;
						}
						else
						{
							grossinr=document.forms[0].gross_amt_inr.value;
						}
					}

					var calculatedamt=0;
					if(adjustsign=='1')
					{
						if(parseFloat(grossinr)==parseFloat(adjustamt)) {
							calculatedamt=0;
						} else {
							calculatedamt=parseFloat(grossinr)-parseFloat(adjustamt);
						}
					}
					else if(adjustsign=='2')
					{
						calculatedamt=parseFloat(grossinr)+parseFloat(adjustamt);
					}
					
					if(parseFloat(adjustamt)>parseFloat(grossinr))
					{
						negflag=true;
					}
					else
					{
						//alert(document.forms[0].priceINR2.value);
						document.forms[0].gross_amt_inr_adjusted.value=round(calculatedamt,2);
						document.forms[0].priceINR2.value=round(calculatedamt,2);
						document.forms[0].inrAmt1.value=round(calculatedamt,2); //HS20160905
						if(document.forms[0].adjustdetails[1].checked){ //HS20160903
							document.forms[0].double_final_gross_amt_inr.value=round(calculatedamt,2);
							var taxratesize=document.getElementById('taxratesize').value;
							var taxadjamt=0;
							for(l=0;l<taxratesize;l++){
								var taxrate=document.getElementById('taxrate'+l).value;
								document.forms[0].tax_amts[l].value=round((round(calculatedamt,2)*taxrate/100),2);
								taxadjamt+=parseFloat(document.forms[0].tax_amts[l].value);
							}
							//alert("---"+taxadjamt);
							document.forms[0].total_tax.value=round(taxadjamt,2);
							document.forms[0].net_amt_inr.value=round(parseFloat(round(calculatedamt,2)+round(taxadjamt,2)),2);
						}
						//document.forms[0].double_final_gross_amt_inr.value=document.forms[0].priceINR2.value;
						var grs_inr="0";
						if(document.forms[0].gross_amt_inr_adjusted_tax.value=="" || document.forms[0].gross_amt_inr_adjusted_tax.value=="0"){
							grs_inr=document.forms[0].total_tax.value;
							document.forms[0].gross_amt_inr_adjusted_tax.value=round(grs_inr,2);} 
						else {document.forms[0].gross_amt_inr_adjusted_tax.value = round((parseFloat(document.forms[0].total_tax.value)-parseFloat(document.forms[0].adjustamt_tax.value)),2);
							grs_inr=document.forms[0].gross_amt_inr_adjusted_tax.value;} 
						if(document.forms[0].gross_amt_inr_adjusted_sbc.value=="" || document.forms[0].gross_amt_inr_adjusted_sbc.value=="0"){
							grs_inr=document.forms[0].total_sbc.value;
							document.forms[0].gross_amt_inr_adjusted_sbc.value=round(grs_inr,2);
						} else{document.forms[0].gross_amt_inr_adjusted_sbc.value = round((parseFloat(grs_inr)-parseFloat(document.forms[0].adjustamt_sbc.value)),2); 
							grs_inr=document.forms[0].gross_amt_inr_adjusted_sbc.value;} 
						if(document.forms[0].gross_amt_inr_adjusted_kkc.value=="" || document.forms[0].gross_amt_inr_adjusted_kkc.value=="0"){
							grs_inr=document.forms[0].total_kkc.value;
							document.forms[0].gross_amt_inr_adjusted_kkc.value=round(grs_inr,2);
						}else{ document.forms[0].gross_amt_inr_adjusted_kkc.value = round((parseFloat(grs_inr)-parseFloat(document.forms[0].adjustamt_kkc.value)),2); 	
							grs_inr=document.forms[0].gross_amt_inr_adjusted_kkc.value; } 
						
						//alert(document.forms[0].gross_amt_inr_adjusted_tax.value);
						document.forms[0].net_amt_inr.value=round((parseFloat(grs_inr)+parseFloat(document.forms[0].priceINR2.value)),2);
					}
				}
				
				if(negflag==true)
				{
						alert("Amount to be adjusted is greater than the Gross amount..!!");
						document.forms[0].adjustamt.value="";
						document.forms[0].gross_amt_inr_adjusted.value="";
						document.forms[0].priceINR2.value="";
						
				}
				else
				{
						var grossamt=document.forms[0].double_final_gross_amt_inr.value;
						var taxcd=document.forms[0].taxstructcd.value;
						
						if(document.getElementById("submited_text")==undefined)
						{
						}
						else
						{
						document.getElementById("submited_text").innerHTML="";
						}
						//caluclate_tax_details_ajax(grossamt,taxcd);
				}
			}			 
		}
		else
		{
			alert("Amount to be adjusted is greater than the advance available..!!");
			document.forms[0].adjustamt.value="";
			document.forms[0].gross_amt_inr_adjusted.value="";
			document.forms[0].priceINR2.value="";
			var len1=document.forms[0].taxsize.value;
			
			for(var i=0;i<len1;i++)
			{
				var tmp="tax_amts"+i;
			//	document.getElementById(tmp).value="";
			}
			
			document.getElementById("net_amt_inr").value="";
		}
	}
	else
	{
		document.forms[0].gross_amt_inr_adjusted.value="";
		document.forms[0].priceINR2.value="";
		var len1=document.forms[0].taxsize.value;
		
			for(var i=0;i<len1;i++)
			{
				var tmp="tax_amts"+i;
			//	document.getElementById(tmp).value="";
			}
			document.getElementById("net_amt_inr").value="";
	}
}

function adjustmamountforadjust_tax()
{//alert(document.forms[0].TAX_ADV_ADJUST_CUR.value);
	var adjustamt=document.forms[0].adjustamt_tax.value;
	var advanceamt=document.forms[0].availablebalance_tax.value;
	var negflag=false;
//	var flag1=chkno(adjustamt,document.forms[0].adjustamt_tax);
	var flag1=checkNumber1(document.forms[0].adjustamt_tax,12,2);
	if(flag1==true)
	{
		var flag=checkadjustlimit_tax();
		
		if(flag==true)
		{
			if(adjustamt=='' || adjustamt==" ")
			{
				alert('Enter proper adjust amount');
			}
			else
			{
				var adjustsign=document.forms[0].adjustsign_tax.value;
				var cur=document.forms[0].TAX_ADV_ADJUST_CUR.value;
				//alert(cur);
				 if(cur=='INR')
				{
					var grossinr=document.forms[0].total_tax.value;
					//alert(grossinr);
					
					var calculatedamt=0;
					if(adjustsign=='1')
					{
						calculatedamt=parseFloat(grossinr)-parseFloat(adjustamt);
					}
					else if(adjustsign=='2')
					{
						calculatedamt=parseFloat(grossinr)+parseFloat(adjustamt);
					}
					
					if(parseFloat(adjustamt)>parseFloat(grossinr))
					{
						negflag=true;
					}
					else
					{
						document.forms[0].gross_amt_inr_adjusted_tax.value=round(calculatedamt,2);
						var grs_inr="0";
						if(document.forms[0].priceINR2.value==""){}
						else{ grs_inr=document.forms[0].priceINR2.value;}
						
						var sbc_adjustment = document.forms[0].adjustamt_sbc.value;
						if(sbc_adjustment!='')
						{
							document.forms[0].gross_amt_inr_adjusted_sbc.value = round(calculatedamt,2) - round(sbc_adjustment,2);
							calculatedamt = document.forms[0].gross_amt_inr_adjusted_sbc.value;
						} else {
							document.forms[0].gross_amt_inr_adjusted_sbc.value = round(calculatedamt,2);
						}
						
						var kkc_adjustment = document.forms[0].adjustamt_kkc.value;
						if(kkc_adjustment!='')
						{
							document.forms[0].gross_amt_inr_adjusted_kkc.value = round(calculatedamt,2) - round(kkc_adjustment,2);
							calculatedamt = document.forms[0].gross_amt_inr_adjusted_kkc.value;
						} else {
							document.forms[0].gross_amt_inr_adjusted_kkc.value = round(calculatedamt,2);
						}
						
						document.forms[0].net_amt_inr.value=round((parseFloat(grs_inr)+round(parseFloat(calculatedamt),2)),2);
					}
				}
				
				if(negflag==true)
				{
						alert("Amount to be adjusted is greater than the Total Tax..!!");
						document.forms[0].adjustamt_tax.value="";
						document.forms[0].gross_amt_inr_adjusted_tax.value="";
						//document.forms[0].priceINR2.value="";
				}
			}			 
		}
		else
		{
			alert("Amount to be adjusted is greater than the advance available..!!");
						
			document.getElementById("net_amt_inr").value="";
			document.forms[0].adjustamt_tax.value="";
			document.forms[0].gross_amt_inr_adjusted_tax.value="";
		}
	}
	else
	{
			document.getElementById("net_amt_inr").value="";
			document.forms[0].adjustamt_tax.value="";
			document.forms[0].gross_amt_inr_adjusted_tax.value="";
	}
	
	/*if(document.forms[0].adjustamt_sbc.value!='')
		adjustmamountforadjust_sbc();*/
}

function adjustmamountforadjust_sbc()  //SB20160506
{ //alert(document.forms[0].SBC_ADV_ADJUST_CUR.value);

	var adjustamt=document.forms[0].adjustamt_sbc.value;
	var advanceamt=document.forms[0].availablebalance_sbc.value;
	var negflag=false;
//	var flag1=chkno(adjustamt,document.forms[0].adjustamt_tax);
	var flag1=checkNumber1(document.forms[0].adjustamt_sbc,12,2);
	if(flag1==true)
	{
		var flag=checkadjustlimit_sbc();
		
		if(flag==true)
		{
			if(adjustamt=='' || adjustamt==" ")
			{
				alert('Enter proper adjust amount');
			}
			else
			{
				var adjustsign=document.forms[0].adjustsign_sbc.value; 
				var cur=document.forms[0].SBC_ADV_ADJUST_CUR.value; //alert(cur);
				
				 if(cur=='INR')
				{
					var grossinr=document.forms[0].gross_amt_inr_adjusted_tax.value;  // on & noafter Adv ST deduction
					
					var calculatedamt=0;
					if(adjustsign=='1')
					{
						calculatedamt=parseFloat(grossinr)-parseFloat(adjustamt);
					}
					else if(adjustsign=='2')
					{
						calculatedamt=parseFloat(grossinr)+parseFloat(adjustamt);
					}
					//alert(calculatedamt);
					if(parseFloat(adjustamt)>parseFloat(grossinr))
					{
						negflag=true;
					}
					else
					{
						document.forms[0].gross_amt_inr_adjusted_sbc.value=round(calculatedamt,2);
						var grs_inr="0";
						if(document.forms[0].priceINR2.value==""){}
						else{ grs_inr=document.forms[0].priceINR2.value;}
						calculatedamt = parseFloat(document.forms[0].gross_amt_inr_adjusted_sbc.value);
						var kkc_adjustment = document.forms[0].adjustamt_kkc.value;
						if(kkc_adjustment!='')
						{
							document.forms[0].gross_amt_inr_adjusted_kkc.value = round(calculatedamt,2) - round(kkc_adjustment,2);
							calculatedamt = parseFloat(document.forms[0].gross_amt_inr_adjusted_kkc.value);
						} else {
							document.forms[0].gross_amt_inr_adjusted_kkc.value = round(calculatedamt,2);
						}
						document.forms[0].net_amt_inr.value=round((parseFloat(grs_inr)+round(parseFloat(calculatedamt),2)),2);
					}
				}
				
				if(negflag==true)
				{
						alert("Amount to be adjusted is greater than the Total Tax..!!");
						document.forms[0].adjustamt_sbc.value="";
						document.forms[0].gross_amt_inr_adjusted_sbc.value="";
						//document.forms[0].priceINR2.value="";
				}
			}			 
		}
		else
		{
			alert("Amount to be adjusted is greater than the advance available..!!");
		
			var len1=document.forms[0].taxsize.value;
			
			for(var i=0;i<len1;i++)
			{
				var tmp="tax_amts"+i;
			//	document.getElementById(tmp).value="";
			}
			
			document.getElementById("net_amt_inr").value="";
			document.forms[0].adjustamt_sbc.value="";
			document.forms[0].gross_amt_inr_adjusted_sbc.value="";
		}
	}
	else
	{
	//	document.forms[0].gross_amt_inr_adjusted.value="";
	//	document.forms[0].priceINR2.value="";
		var len1=document.forms[0].taxsize.value;
		
			for(var i=0;i<len1;i++)
			{
				var tmp="tax_amts"+i;
		//		document.getElementById(tmp).value="";
			}
			document.getElementById("net_amt_inr").value="";
			document.forms[0].adjustamt_sbc.value="";
			document.forms[0].gross_amt_inr_adjusted_sbc.value="";
	}
} //EoF SB20160506

//KKC//
function adjustmamountforadjust_kkc()  //SB20160506
{ //alert(document.forms[0].KKC_ADV_ADJUST_CUR.value);

	var adjustamt=document.forms[0].adjustamt_kkc.value;
	var advanceamt=document.forms[0].availablebalance_kkc.value;
	var negflag=false;
//	var flag1=chkno(adjustamt,document.forms[0].adjustamt_tax);
	var flag1=checkNumber1(document.forms[0].adjustamt_kkc,12,2);
	if(flag1==true)
	{
		var flag=checkadjustlimit_kkc();
		
		if(flag==true)
		{
			if(adjustamt=='' || adjustamt==" ")
			{
				alert('Enter proper adjust amount');
			}
			else
			{
				var adjustsign=document.forms[0].adjustsign_kkc.value; 
				var cur=document.forms[0].KKC_ADV_ADJUST_CUR.value; //alert(cur);
				
				 if(cur=='INR')
				{
					var grossinr=document.forms[0].gross_amt_inr_adjusted_sbc.value;  // on & noafter Adv ST deduction
					
					var calculatedamt=0;
					if(adjustsign=='1')
					{
						calculatedamt=parseFloat(grossinr)-parseFloat(adjustamt);
					}
					else if(adjustsign=='2')
					{
						calculatedamt=parseFloat(grossinr)+parseFloat(adjustamt);
					}
					//alert(calculatedamt);
					if(parseFloat(adjustamt)>parseFloat(grossinr))
					{
						negflag=true;
					}
					else
					{
						document.forms[0].gross_amt_inr_adjusted_kkc.value=round(calculatedamt,2);
						var grs_inr="0";
						if(document.forms[0].priceINR2.value==""){}
						else{ grs_inr=document.forms[0].priceINR2.value;}
						//alert(document.forms[0].priceINR2.value);
						document.forms[0].net_amt_inr.value=round((parseFloat(grs_inr)+parseFloat(document.forms[0].gross_amt_inr_adjusted_kkc.value)),2);
						//alert("after adjustmamountforadjust_kkc: "+document.forms[0].net_amt_inr.value);
					}
				}
				
				if(negflag==true)
				{
						alert("Amount to be adjusted is greater than the Total Tax..!!");
						document.forms[0].adjustamt_kkc.value="";
						document.forms[0].gross_amt_inr_adjusted_kkc.value="";
						//document.forms[0].priceINR2.value="";
						
				}
			}			 
		}
		else
		{
			alert("Amount to be adjusted is greater than the advance available..!!");
		
			var len1=document.forms[0].taxsize.value;
			
			for(var i=0;i<len1;i++)
			{
				var tmp="tax_amts"+i;
			//	document.getElementById(tmp).value="";
			}
			
			document.getElementById("net_amt_inr").value="";
			document.forms[0].adjustamt_kkc.value="";
			document.forms[0].gross_amt_inr_adjusted_kkc.value="";
		}
	}
	else
	{
	//	document.forms[0].gross_amt_inr_adjusted.value="";
	//	document.forms[0].priceINR2.value="";
		var len1=document.forms[0].taxsize.value;
		
			for(var i=0;i<len1;i++)
			{
				var tmp="tax_amts"+i;
		//		document.getElementById(tmp).value="";
			}
			document.getElementById("net_amt_inr").value="";
			document.forms[0].adjustamt_kkc.value="";
			document.forms[0].gross_amt_inr_adjusted_kkc.value="";
	}
}

//////
function checkadjustlimit_tax()
{
	var flag=true;
	var advanceamt=document.forms[0].availablebalance_tax.value;
	var adjustamt=document.forms[0].adjustamt_tax.value;
		
	var splitAmt=advanceamt.split(",");
	advanceamt='';
	
	for(var i=0;i<splitAmt.length;i++)
	{
		advanceamt=advanceamt+splitAmt[i];
	}
	
	if(parseFloat(advanceamt)<parseFloat(adjustamt))
	{
		flag=false;
	}
	else
	{
		flag=true;
	}
	return flag;
}
function checkadjustlimit_sbc()
{//alert("CHECK LIMIT");
	var flag=true;
	var advanceamt=document.forms[0].availablebalance_sbc.value;
	var adjustamt=document.forms[0].adjustamt_sbc.value;
		
	var splitAmt=advanceamt.split(",");
	advanceamt='';
	
	for(var i=0;i<splitAmt.length;i++)
	{
		advanceamt=advanceamt+splitAmt[i];
	}
	
	if(parseFloat(advanceamt)<parseFloat(adjustamt))
	{
		flag=false;
	}
	else
	{
		flag=true;
	}
	//alert("2. CHECK LIMIT");	
	return flag;
}
function checkadjustlimit_kkc()
{//alert("CHECK LIMIT");
	var flag=true;
	var advanceamt=document.forms[0].availablebalance_kkc.value;
	var adjustamt=document.forms[0].adjustamt_kkc.value;
		
	var splitAmt=advanceamt.split(",");
	advanceamt='';
	
	for(var i=0;i<splitAmt.length;i++)
	{
		advanceamt=advanceamt+splitAmt[i];
	}
	
	if(parseFloat(advanceamt)<parseFloat(adjustamt))
	{
		flag=false;
	}
	else
	{
		flag=true;
	}
	//alert("2. CHECK LIMIT");	
	return flag;
}
function checkadjustlimit()
{
	var flag=true;
	var advanceamt=document.forms[0].availablebalance.value;
	var adjustamt=document.forms[0].adjustamt.value;
		
	var splitAmt=advanceamt.split(",");
	advanceamt='';
	
	for(var i=0;i<splitAmt.length;i++)
	{
		advanceamt=advanceamt+splitAmt[i];
	}
	
	if(parseFloat(advanceamt)<parseFloat(adjustamt))
	{
		flag=false;
	}
	else
	{
		flag=true;
	}
	
	return flag;
}


//Following Method Is Introduced By Samik Shah On 21st May, 2010 ...
function closeWindow()
{
	var month = document.forms[0].month.value;
	var year = document.forms[0].year.value;
	var bill_cycle = document.forms[0].bill_cycle.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	window.opener.refreshPageFromChild(month, year, bill_cycle, write_permission, delete_permission, print_permission, approve_permission, audit_permission, check_permission, authorize_permission);
	window.close();
}

//Following Method Is Introduced By Samik Shah On 21st May, 2010 ...
function closeWindow2()
{
	var month = document.forms[0].month.value;
	var year = document.forms[0].year.value;
	var bill_cycle = document.forms[0].bill_cycle.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	window.opener.refreshPageFromChild(month, year, bill_cycle, write_permission, delete_permission, print_permission, approve_permission, audit_permission, check_permission, authorize_permission);
}

//Following Method Is Introduced By Samik Shah On 21st May, 2010 ...
function refreshPage(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,hlpl_inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,mapping_id,sz,period_sz,flag)
{

	var invoice_date = document.forms[0].invoice_date.value;
	var due_dt = document.forms[0].due_date.value;
	var contact_person = document.forms[0].contact_person.value;
	var particular_date = document.forms[0].particular_date.value;
	var msg = document.forms[0].msg.value;

	var activity = document.forms[0].activity.value;
	var hlpl_invoice_sequence_no = document.forms[0].hlpl_invoice_sequence_no.value;
	var hlpl_inv_seq_no2 = document.forms[0].hlpl_inv_seq_no2.value;
	var inv_financial_year = document.forms[0].inv_financial_year.value;
	var gross_amt_usd2 = document.forms[0].gross_amt_usd2.value;
	var gross_amt_inr2 = document.forms[0].gross_amt_inr2.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	
	var discount_flag_apply='N';
  	var discount_deducted_val=0;
   
   	var mapping_id=document.forms[0].mapping_id.value;
	
	//location.replace("frm_invoice_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&TariffFlag="+TariffFlag+"&Adv_submitted_flag="+Adv_submitted_flag+"&adv_inv_no="+adv_inv_no+"&adv_inv_dt="+adv_inv_dt+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exch_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&invoice_date="+invoice_date+"&contact_person="+contact_person+"&particular_date="+particular_date+"&gross_amt_usd="+gross_amt_usd+"&gross_amt_inr="+gross_amt_inr+"&exch_rate_val="+exch_rate_val+"&exchg_rate_index="+exchg_rate_index+"&page_refresh_flag=Y&msg="+msg2+"&activity="+activity+"&hlpl_invoice_sequence_no="+hlpl_invoice_sequence_no+"&hlpl_inv_seq_no2="+hlpl_inv_seq_no2+"&inv_financial_year="+inv_financial_year+"&gross_amt_usd2="+gross_amt_usd2+"&gross_amt_inr2="+gross_amt_inr2+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&offspec_rate="+offspec_rate+"&flag="+check_flag+"&discount_flag_apply="+discount_flag_apply+"&discount_count="+discount_count+"&entrypoint="+entrypoint+"&mapping_id="+mapping_id);
	
	//location.replace("frm_invoice_dtl.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&invoice_date="+invoice_date+"&contact_person="+contact_person+"&particular_date="+particular_date+"&page_refresh_flag=Y&msg="+msg+"&activity="+activity+"&hlpl_invoice_sequence_no="+hlpl_invoice_sequence_no+"&hlpl_inv_seq_no2="+hlpl_inv_seq_no2+"&inv_financial_year="+inv_financial_year+"&gross_amt_usd2="+gross_amt_usd2+"&gross_amt_inr2="+gross_amt_inr2+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&mapping_id="+mapping_id);

	setExchangeRate(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,hlpl_inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,sz,period_sz,flag);
}

//Following Method Is Introduced By Samik Shah On 27th May, 2010 ...
function resetPageContent(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,hlpl_inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,mapping_id)
{
	var invoice_date = document.forms[0].inv_dt.value;
	//var contact_person = document.forms[0].contact_person.value;
	//var particular_date = document.forms[0].particular_date.value;
	var msg = document.forms[0].msg.value;
	
	var activity = document.forms[0].activity.value;
	var hlpl_invoice_sequence_no = document.forms[0].hlpl_invoice_sequence_no.value;
	var hlpl_inv_seq_no2 = document.forms[0].hlpl_inv_seq_no2.value;
	var inv_financial_year = document.forms[0].inv_financial_year.value;
	var gross_amt_usd2 = document.forms[0].gross_amt_usd2.value;
	var gross_amt_inr2 = document.forms[0].gross_amt_inr2.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	location.replace("frm_invoice_dtl_tax_adjust.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&invoice_date="+invoice_date+"&page_refresh_flag=N&msg="+msg+"&activity="+activity+"&hlpl_invoice_sequence_no="+hlpl_invoice_sequence_no+"&hlpl_inv_seq_no2="+hlpl_inv_seq_no2+"&inv_financial_year="+inv_financial_year+"&gross_amt_usd2="+gross_amt_usd2+"&gross_amt_inr2="+gross_amt_inr2+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&mapping_id="+mapping_id);
}


//Following Method Is Introduced By Samik Shah On 19th January, 2011 ...
function setExchangeRate(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,hlpl_inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_mthd,sz,period_sz,flag)
{   var check_flag=flag;
   //  alert(document.forms[0].Discount_flag_Mst.value);
   var discount_flag_apply='N';
   var discount_deducted_val=0;
   
   var mapping_id=document.forms[0].mapping_id.value;
   // Applicable Discount
   if(document.forms[0].Discount_flag_Mst.value=='Y'){
	 discount_deducted_val=document.forms[0].discount_val_deducted.value;
	 discount_flag_apply=document.forms[0].discount_flag_apply.value;}
   //end
   var TariffFlag='N';
   if(document.forms[0].Tariff_flag_Mst.value=='Y')
   {
   		if(document.forms[0].sale_price_inr_check.checked==true)
   		{
   			TariffFlag='Y';
   		}
   		else
   		{
   			TariffFlag='N';
   		}
   }
   
   
   
    var due_dt = document.forms[0].due_date.value;//JHP20120223
    // alert(TariffFlag);
	var invoice_date = document.forms[0].inv_dt.value;
	var contact_person = document.forms[0].contact_person.value;
	var offspec_flag = document.forms[0].offspec_flag.value;
	var particular_date = '';
	var exchg_rate_cal_method = exchg_rate_cal_mthd;
	var exch_rate_cd = "0";
	var exchg_rate_index = "-1";
	
	var activity = document.forms[0].activity.value;
	var hlpl_invoice_sequence_no = document.forms[0].hlpl_invoice_sequence_no.value;
	var hlpl_inv_seq_no2 = document.forms[0].hlpl_inv_seq_no2.value;
	var inv_financial_year = document.forms[0].inv_financial_year.value;
	var gross_amt_usd2 = document.forms[0].gross_amt_usd2.value;
	var gross_amt_inr2 = document.forms[0].gross_amt_inr2.value;
	
	if(exchg_rate_cal_method=='D')
	{
		particular_date = document.forms[0].particular_date.value;
	}
	
	//alert("exchg_rate_cal_method = "+exchg_rate_cal_method+", particular_date = "+particular_date);
	
	var msg2 = document.forms[0].msg.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	var flag = true;
	var msg = "First Check The Following Fields :\n\n";
	
	var size = parseInt(sz);
	var period_size = parseInt(period_sz);
	var i = 0;
	var index = 0;
	var j = 0;
	//alert("exchg_rate_cal_method = "+exchg_rate_cal_method+",  size = "+size+",  &  period_size = "+period_size);
	
	var invoice_date = document.forms[0].invoice_date.value;
	var due_date = document.forms[0].due_date.value;
	
	var gross_amt_usd = parseFloat('0');
	var gross_amt_inr = parseFloat('0');
	
	if(exchg_rate_cal_method=='D')
	{
		for(i=0; i<size; i++)
		{
			//alert("++"+i+"---"+size);
			if(document.forms[0].exch_rate[i].checked)
			{
				document.forms[0].exch_rate_cd.value = document.forms[0].exch_rate_code[i].value;
				document.forms[0].exch_rate_val.value = document.forms[0].exch_rate_value[i].value;
				exchg_rate_index = ""+i;
				if(i<4)
				{
					document.forms[0].exch_rate_dt.value = document.forms[0].previous_dt.value;
				}
				else if(i<8)
				{
					document.forms[0].exch_rate_dt.value = document.forms[0].inv_date.value;
				}
				else
				{
					document.forms[0].exch_rate_dt.value = document.forms[0].particular_date.value;
				}
			}
		}
		//alert("exch_rate_cd = "+document.forms[0].exch_rate_cd.value+",  exch_rate_val = "+document.forms[0].exch_rate_val.value+",  &  document.forms[0].exch_rate_dt.value = "+document.forms[0].exch_rate_dt.value);
	}
	else if(exchg_rate_cal_method=='A')
	{
		index = size*4;
		for(i=0; i<4; i++)
		{
			if(document.forms[0].exch_rate[i].checked)
			{
				exchg_rate_index = ""+i;
				document.forms[0].exch_rate_cd.value = document.forms[0].exch_rate_code[i].value;
				document.forms[0].start_index.value = ""+i;
				document.forms[0].end_index.value = ""+index;
			}
		}
		
		document.forms[0].exch_rate_dt.value = document.forms[0].bill_period_end_date.value;
	}
	
	var contact_person = document.forms[0].contact_person.value;
	var contact_person_nm = "";
	if(contact_person==null || contact_person=='' || contact_person==' ' || contact_person=='0')
	{
		//msg += "Please Select The Proper Contact Person !!!\n\n";
		//flag = false;
	}
	else
	{
		contact_person_nm = document.forms[0].contact_person[document.forms[0].contact_person.selectedIndex].innerText;
		document.forms[0].contact_person_nm.value = contact_person_nm;
		//alert("document.forms[0].contact_person_nm.value = "+document.forms[0].contact_person_nm.value);
	}
	
	if(invoice_date==null || invoice_date=='' || invoice_date=='0' || invoice_date==' ' || invoice_date=='  ')
	{
		msg += "Please Enter Correct Invoice Date, Then Submit The Billing Details !!!\n\n";
		flag = false;
	}
	
	if(due_date==null || due_date=='' || due_date=='0' || due_date==' ' || due_date=='  ')
	{
		msg += "Please Enter Correct Due Date, Then Submit The Billing Details !!!\n\n";
		flag = false;
	}	
	
	var offspec_rate = "0";
	var offspec_qty = "0";
	if(offspec_flag=='Y')
	{
		offspec_rate = document.forms[0].offspec_rate.value;
		offspec_qty = document.forms[0].offspec_qty.value;
		
		if(offspec_rate==null || offspec_rate=='' || offspec_rate==' ' || offspec_rate=='0' || offspec_rate=='0.0' || offspec_rate=='0.00' || offspec_rate=='0.000' || offspec_rate=='0.0000')
		{
			msg += "Please Enter Correct Rate Of Accepted Offspec Gas !!!\n";
			flag = false;
		}
	}
	
	if(exchg_rate_cal_method=='D')
	{
		var rt_val = document.forms[0].exch_rate_val.value;
		if(rt_val==null || rt_val=='' || rt_val==' ' || rt_val=='N/A' || rt_val=='0' || rt_val=='0.00')
		{
			msg += "Please Select The Exchange Rate Which Is Available !!!\nOR\nEnter The Exchange Rate From Interest & Exchange Rate Entry Form For The Selected Date !!!\n";
			flag = false;
		}
		else
		{
			if(period_size==1)
			{
				var sale_price = document.forms[0].sale_price.value;
				if(discount_flag_apply=='Y')
				{
					sale_price=discount_deducted_val;
				}
				
				var inv_qty = document.forms[0].daily_inv_qty.value;
						
				if(sale_price!=null && sale_price!='' && sale_price!=' ' && inv_qty!=null && inv_qty!='' && inv_qty!=' ')
				{
					if(parseFloat(""+sale_price)>0 && parseFloat(""+inv_qty)>0)
					{
						document.forms[0].daily_inv_amt_usd.value = ""+round((parseFloat(""+sale_price)*parseFloat(""+inv_qty)),8);
						document.forms[0].daily_inv_amt_inr.value = ""+round((parseFloat(""+document.forms[0].daily_inv_amt_usd.value)*parseFloat(rt_val)),8);
						gross_amt_usd += parseFloat(""+document.forms[0].daily_inv_amt_usd.value);
						gross_amt_inr += parseFloat(""+document.forms[0].daily_inv_amt_inr.value);
					}
				}
			}
			else if(period_size>1)
			{
				for(i=0;i<period_size;i++)
				{
					var sale_price = document.forms[0].sale_price.value;
					if(discount_flag_apply=='Y')
					{
						sale_price=discount_deducted_val;
					}
					var inv_qty = document.forms[0].daily_inv_qty[i].value;
							
					if(sale_price!=null && sale_price!='' && sale_price!=' ' && inv_qty!=null && inv_qty!='' && inv_qty!=' ')
					{
						if(parseFloat(""+sale_price)>0 && parseFloat(""+inv_qty)>0)
						{
							document.forms[0].daily_inv_amt_usd[i].value = ""+round((parseFloat(""+sale_price)*parseFloat(""+inv_qty)),8);
							document.forms[0].daily_inv_amt_inr[i].value = ""+round((parseFloat(""+document.forms[0].daily_inv_amt_usd[i].value)*parseFloat(rt_val)),8);
							gross_amt_usd += parseFloat(""+document.forms[0].daily_inv_amt_usd[i].value);
							gross_amt_inr += parseFloat(""+document.forms[0].daily_inv_amt_inr[i].value);
						}
					}
				}
			}
			var gross_amount_usd = parseFloat(""+offspec_rate)*parseFloat(""+offspec_qty);
			gross_amt_usd += gross_amount_usd;
			gross_amt_inr += (gross_amount_usd*parseFloat(rt_val));
		}
	}
	else if(exchg_rate_cal_method=='A')
	{
		index = size*4;
		var avg_rate = parseFloat('0');
		var z = 0;
		var y = 0;
		for(i=0; i<4; i++)
		{
			if(document.forms[0].exch_rate[i].checked)
			{
				z = 0;
				//Following Logic Has Been Introduced By Samik Shah On 3rd June, 2010 ...
				document.forms[0].start_index.value = ""+i;
				document.forms[0].end_index.value = ""+index;
				
				for(j=i; j<index; j=j+4)
				{
					//alert("j = "+j);
					var rt_val = document.forms[0].exch_rate_value[j].value;
					
					if(rt_val==null || rt_val=='' || rt_val==' ' || rt_val=='N/A' || rt_val=='0' || rt_val=='0.00')
					{
						msg += "Please Select The Exchange Rate Which Is Available For All Dates !!!\nOR\nEnter The Exchange Rate From Interest & Exchange Rate Entry Form For All Dates For Selected Billing Period !!!\n";
						flag = false;
						break;
					}
					else
					{
						avg_rate += parseFloat(rt_val);
						++y;
						
						if(period_size==1)
						{
							var sale_price = document.forms[0].sale_price.value;
							if(discount_flag_apply=='Y')
							{
								sale_price=discount_deducted_val;
							}
							var inv_qty = document.forms[0].daily_inv_qty.value;
							
							if(sale_price!=null && sale_price!='' && sale_price!=' ' && inv_qty!=null && inv_qty!='' && inv_qty!=' ')
							{
								if(parseFloat(""+sale_price)>0 && parseFloat(""+inv_qty)>0)
								{
									document.forms[0].daily_inv_amt_usd.value = ""+round((parseFloat(""+sale_price)*parseFloat(""+inv_qty)),8);
									document.forms[0].daily_inv_amt_inr.value = ""+round((parseFloat(""+document.forms[0].daily_inv_amt_usd.value)*parseFloat(rt_val)),8);
									gross_amt_usd += parseFloat(""+document.forms[0].daily_inv_amt_usd.value);
									gross_amt_inr += parseFloat(""+document.forms[0].daily_inv_amt_inr.value);
								}
							}
						}
						else if(period_size>1)
						{
							var sale_price = document.forms[0].sale_price.value;
							if(discount_flag_apply=='Y')
							{
								sale_price=discount_deducted_val;
							}
							var inv_qty = document.forms[0].daily_inv_qty[z].value;
							
							if(sale_price!=null && sale_price!='' && sale_price!=' ' && inv_qty!=null && inv_qty!='' && inv_qty!=' ')
							{
								if(parseFloat(""+sale_price)>0 && parseFloat(""+inv_qty)>0)
								{
									document.forms[0].daily_inv_amt_usd[z].value = ""+round((parseFloat(""+sale_price)*parseFloat(""+inv_qty)),8);
									document.forms[0].daily_inv_amt_inr[z].value = ""+round((parseFloat(""+document.forms[0].daily_inv_amt_usd[z].value)*parseFloat(rt_val)),8);
									gross_amt_usd += parseFloat(""+document.forms[0].daily_inv_amt_usd[z].value);
									gross_amt_inr += parseFloat(""+document.forms[0].daily_inv_amt_inr[z].value);
								}
							}
						}
					}
					++z; 	
				}
			}
		}
		
		if(y>0)
		{
			avg_rate = avg_rate/y;
		}
		else
		{
			avg_rate = 0;
		}
		document.forms[0].exch_rate_val.value = ""+round(avg_rate,4);
	}
	
	document.forms[0].raw_amt_usd.value=""+round(gross_amt_usd,2);
	document.forms[0].raw_amt_inr.value=""+round(gross_amt_inr,2);
	document.forms[0].gross_amt_usd.value = ""+round(gross_amt_usd,2);
	document.forms[0].gross_amt_inr.value = ""+round(gross_amt_inr,2);
		
	gross_amt_usd = round(gross_amt_usd,2);
	gross_amt_inr = round(gross_amt_inr,2);	
	
	var exch_rate_val = document.forms[0].exch_rate_val.value;
	
	exch_rate_cd = document.forms[0].exch_rate_cd.value;

	var adv_inv_no=''; //document.forms[0].adv_inv_no.value;
	var adv_inv_dt=''; //document.forms[0].adv_inv_dt.value;
	
	if(document.forms[0].adv_inv_no!=undefined)
	{
		adv_inv_no=document.forms[0].adv_inv_no.value;
		adv_inv_dt=document.forms[0].adv_inv_dt.value;
	}
	
	var entrypoint=document.forms[0].entrypoint.value;
	var discount_count=document.forms[0].discount_count.value;
	var Adv_submitted_flag=document.forms[0].Adv_submitted_flag.value;
	var raw_amt_usd=document.forms[0].raw_amt_usd.value;
	var raw_amt_inr=document.forms[0].raw_amt_inr.value;
	
	if(discount_flag_apply=='N')
	{
		discount_counte='0';
	}
	if(flag)
	{
		location.replace("frm_invoice_dtl_tax_adjust.jsp?customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&TariffFlag="+TariffFlag+"&Adv_submitted_flag="+Adv_submitted_flag+"&adv_inv_no="+adv_inv_no+"&adv_inv_dt="+adv_inv_dt+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exch_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&invoice_date="+invoice_date+"&contact_person="+contact_person+"&particular_date="+particular_date+"&gross_amt_usd="+gross_amt_usd+"&gross_amt_inr="+gross_amt_inr+"&exch_rate_val="+exch_rate_val+"&exchg_rate_index="+exchg_rate_index+"&page_refresh_flag=Y&msg="+msg2+"&activity="+activity+"&hlpl_invoice_sequence_no="+hlpl_invoice_sequence_no+"&hlpl_inv_seq_no2="+hlpl_inv_seq_no2+"&inv_financial_year="+inv_financial_year+"&gross_amt_usd2="+gross_amt_usd2+"&gross_amt_inr2="+gross_amt_inr2+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&offspec_rate="+offspec_rate+"&flag="+check_flag+"&discount_flag_apply="+discount_flag_apply+"&discount_count="+discount_count+"&entrypoint="+entrypoint+"&mapping_id="+mapping_id+"&raw_amt_inr="+raw_amt_inr+"&raw_amt_usd="+raw_amt_usd);
	}
	else
	{
		alert(msg);
		if(document.forms[0].discount_check!=undefined){
		if(document.forms[0].discount_check.checked)
		{
		//	alert("IN ELSE");
			document.forms[0].discount_check.checked=false;
			document.forms[0].discount_flag_apply.value='N';
		}
		else
		{
			//document.forms[0].discount_check.checked=true;
			//document.forms[0].discount_flag_apply.value='Y';
		}
		}
	}
}


//Following Method Is Introduced By Samik Shah On 19th January, 2011 ...
function reSetExchangeRate(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,hlpl_inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_mthd,sz,period_sz)
{
	var invoice_date = document.forms[0].inv_dt.value;
	var offspec_flag = document.forms[0].offspec_flag.value;
	var contact_person = document.forms[0].contact_person.value;
	var particular_date = '';
	
	if(exchg_rate_cal_method=='D')
	{
		particular_date = document.forms[0].particular_date.value;
	}
	
//	alert("exchg_rate_cal_method = "+exchg_rate_cal_method+", particular_date = "+particular_date);
	
	var msg2 = document.forms[0].msg.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	var flag = true;
	var msg = "First Check The Following Fields :\n\n";
	
	var exchg_rate_cal_method = exchg_rate_cal_mthd;
	var size = parseInt(sz);
	var period_size = parseInt(period_sz);
	var i = 0;
	var index = 0;
	var j = 0;
	//alert("exchg_rate_cal_method = "+exchg_rate_cal_method+",  size = "+size+",  &  period_size = "+period_size);
	
	var invoice_date = document.forms[0].invoice_date.value;
	var due_date = document.forms[0].due_date.value;
	
	var gross_amt_usd = parseFloat('0');
	var gross_amt_inr = parseFloat('0');
	
	if(exchg_rate_cal_method=='D')
	{
		for(i=0; i<size; i++)
		{
			if(document.forms[0].exch_rate[i].checked)
			{
				document.forms[0].exch_rate_cd.value = document.forms[0].exch_rate_code[i].value;
				document.forms[0].exch_rate_val.value = document.forms[0].exch_rate_value[i].value;
				
				if(i<4)
				{
					document.forms[0].exch_rate_dt.value = document.forms[0].previous_dt.value;
				}
				else if(i<8)
				{
					document.forms[0].exch_rate_dt.value = document.forms[0].inv_date.value;
				}
				else
				{
					document.forms[0].exch_rate_dt.value = document.forms[0].particular_date.value;
				}
			}
		}
		//alert("exch_rate_cd = "+document.forms[0].exch_rate_cd.value+",  exch_rate_val = "+document.forms[0].exch_rate_val.value+",  &  document.forms[0].exch_rate_dt.value = "+document.forms[0].exch_rate_dt.value);
	}
	else if(exchg_rate_cal_method=='A')
	{
		index = size*4;
		for(i=0; i<4; i++)
		{
			if(document.forms[0].exch_rate[i].checked)
			{
				document.forms[0].exch_rate_cd.value = document.forms[0].exch_rate_code[i].value;
				document.forms[0].start_index.value = ""+i;
				document.forms[0].end_index.value = ""+index;
			}
		}
		
		document.forms[0].exch_rate_dt.value = document.forms[0].bill_period_end_date.value;
	}
	
	var contact_person = document.forms[0].contact_person.value;
	var contact_person_nm = "";
	if(contact_person==null || contact_person=='' || contact_person==' ' || contact_person=='0')
	{
		//msg += "Please Select The Proper Contact Person !!!\n\n";
		//flag = false;
	}
	else
	{
		contact_person_nm = document.forms[0].contact_person[document.forms[0].contact_person.selectedIndex].innerText;
		document.forms[0].contact_person_nm.value = contact_person_nm;
		//alert("document.forms[0].contact_person_nm.value = "+document.forms[0].contact_person_nm.value);
	}
	
	if(invoice_date==null || invoice_date=='' || invoice_date=='0' || invoice_date==' ' || invoice_date=='  ')
	{
		msg += "Please Enter Correct Invoice Date, Then Submit The Billing Details !!!\n\n";
		flag = false;
	}
	
	if(due_date==null || due_date=='' || due_date=='0' || due_date==' ' || due_date=='  ')
	{
		msg += "Please Enter Correct Due Date, Then Submit The Billing Details !!!\n\n";
		flag = false;
	}	
	
	if(exchg_rate_cal_method=='D')
	{
		var rt_val = document.forms[0].exch_rate_val.value;
		if(rt_val==null || rt_val=='' || rt_val==' ' || rt_val=='N/A' || rt_val=='0' || rt_val=='0.00')
		{
			msg += "Please Select The Exchange Rate Which Is Available !!!\nOR\nEnter The Exchange Rate From Interest & Exchange Rate Entry Form For The Selected Date !!!\n";
			flag = false;
		}
		else
		{
			if(period_size==1)
			{
				var sale_price = document.forms[0].sale_price.value;
				var inv_qty = document.forms[0].daily_inv_qty.value;
						
				if(sale_price!=null && sale_price!='' && sale_price!=' ' && inv_qty!=null && inv_qty!='' && inv_qty!=' ')
				{
					if(parseFloat(""+sale_price)>0 && parseFloat(""+inv_qty)>0)
					{
						document.forms[0].daily_inv_amt_usd.value = ""+round((parseFloat(""+sale_price)*parseFloat(""+inv_qty)),8);
						document.forms[0].daily_inv_amt_inr.value = ""+round((parseFloat(""+document.forms[0].daily_inv_amt_usd.value)*parseFloat(rt_val)),8);
						gross_amt_usd += parseFloat(""+document.forms[0].daily_inv_amt_usd.value);
						gross_amt_inr += parseFloat(""+document.forms[0].daily_inv_amt_inr.value);
					}
				}
			}
			else if(period_size>1)
			{
				for(i=0;i<period_size;i++)
				{
					var sale_price = document.forms[0].sale_price.value;
					var inv_qty = document.forms[0].daily_inv_qty[i].value;
							
					if(sale_price!=null && sale_price!='' && sale_price!=' ' && inv_qty!=null && inv_qty!='' && inv_qty!=' ')
					{
						if(parseFloat(""+sale_price)>0 && parseFloat(""+inv_qty)>0)
						{
							document.forms[0].daily_inv_amt_usd[i].value = ""+round((parseFloat(""+sale_price)*parseFloat(""+inv_qty)),8);
							document.forms[0].daily_inv_amt_inr[i].value = ""+round((parseFloat(""+document.forms[0].daily_inv_amt_usd[i].value)*parseFloat(rt_val)),8);
							gross_amt_usd += parseFloat(""+document.forms[0].daily_inv_amt_usd[i].value);
							gross_amt_inr += parseFloat(""+document.forms[0].daily_inv_amt_inr[i].value);
						}
					}
				}
			}
		}
	}
	else if(exchg_rate_cal_method=='A')
	{
		index = size*4;
		var avg_rate = parseFloat('0');
		var z = 0;
		var y = 0;
		for(i=0; i<4; i++)
		{
			if(document.forms[0].exch_rate[i].checked)
			{
				z = 0;
				//Following Logic Has Been Introduced By Samik Shah On 3rd June, 2010 ...
				document.forms[0].start_index.value = ""+i;
				document.forms[0].end_index.value = ""+index;
				
				for(j=i; j<index; j=j+4)
				{
					//alert("j = "+j);
					var rt_val = document.forms[0].exch_rate_value[j].value;
					if(rt_val==null || rt_val=='' || rt_val==' ' || rt_val=='N/A' || rt_val=='0' || rt_val=='0.00')
					{
						msg += "Please Select The Exchange Rate Which Is Available For All Dates !!!\nOR\nEnter The Exchange Rate From Interest & Exchange Rate Entry Form For All Dates For Selected Billing Period !!!\n";
						flag = false;
						break;
					}
					else
					{
						avg_rate += parseFloat(rt_val);
						++y;
						
						if(period_size==1)
						{
							var sale_price = document.forms[0].sale_price.value;
							var inv_qty = document.forms[0].daily_inv_qty.value;
							
							if(sale_price!=null && sale_price!='' && sale_price!=' ' && inv_qty!=null && inv_qty!='' && inv_qty!=' ')
							{
								if(parseFloat(""+sale_price)>0 && parseFloat(""+inv_qty)>0)
								{
									document.forms[0].daily_inv_amt_usd.value = ""+round((parseFloat(""+sale_price)*parseFloat(""+inv_qty)),8);
									document.forms[0].daily_inv_amt_inr.value = ""+round((parseFloat(""+document.forms[0].daily_inv_amt_usd.value)*parseFloat(rt_val)),8);
									gross_amt_usd += parseFloat(""+document.forms[0].daily_inv_amt_usd.value);
									gross_amt_inr += parseFloat(""+document.forms[0].daily_inv_amt_inr.value);
								}
							}
						}
						else if(period_size>1)
						{
							var sale_price = document.forms[0].sale_price.value;
							var inv_qty = document.forms[0].daily_inv_qty[z].value;
							
							if(sale_price!=null && sale_price!='' && sale_price!=' ' && inv_qty!=null && inv_qty!='' && inv_qty!=' ')
							{
								if(parseFloat(""+sale_price)>0 && parseFloat(""+inv_qty)>0)
								{
									document.forms[0].daily_inv_amt_usd[z].value = ""+round((parseFloat(""+sale_price)*parseFloat(""+inv_qty)),8);
									document.forms[0].daily_inv_amt_inr[z].value = ""+round((parseFloat(""+document.forms[0].daily_inv_amt_usd[z].value)*parseFloat(rt_val)),8);
									gross_amt_usd += parseFloat(""+document.forms[0].daily_inv_amt_usd[z].value);
									gross_amt_inr += parseFloat(""+document.forms[0].daily_inv_amt_inr[z].value);
								}
							}
						}
					}
					++z; 	
				}
			}
		}
		
		if(y>0)
		{
			avg_rate = avg_rate/y;
		}
		else
		{
			avg_rate = 0;
		}
		document.forms[0].exch_rate_val.value = ""+round(avg_rate,4);
	}
	document.forms[0].raw_amt_usd.value=""+round(gross_amt_usd,2);
	document.forms[0].raw_amt_inr.value=""+round(gross_amt_inr,2);
	document.forms[0].gross_amt_usd.value = ""+round(gross_amt_usd,2);
	document.forms[0].gross_amt_inr.value = ""+round(gross_amt_inr,2);
}


//Following Method Is Introduced By Samik Shah On 27th May, 2010 ...
//Following Method Is Modified By Samik Shah On 1st June, 2010 ...
function doSubmit(sz,exchg_rate_cal_mthd,period_sz,tax_sz)
{
var discount_deducted_val=0;
var discount_flag_apply="N";
	// Applicable Discount
	if(document.forms[0].Discount_flag_Mst.value=='Y'){
	 discount_deducted_val=document.forms[0].discount_val_deducted.value;
	 discount_flag_apply=document.forms[0].discount_flag_apply.value;}
   //end
	var obj = document.forms[0].adjust;
			var mode = "";
			if(obj!=undefined){
			for(var i = 0; i<obj.length; i++)
			{
				if(obj[i].checked)
				{
					var mode = obj[i].value;
					break;
				}
			}}
	
	//alert("adj--"+mode);
	
	
	
	var flag = true;
	var msg = "First Check The Following Fields :\n\n";
	
	if(document.forms[0].Discount_flag_Mst.value=='Y'){
	 if(document.forms[0].discount_check.checked==true){
		alert(document.forms[0].discount_value.value+" (USD/MMBTU) discount is applied !!!!");
	}
	else
	{
		alert("Discount Clause is Applicable..Do you want to continue without applying discount?");
	}
}
	
	
	if(mode=='Y')
	{
		if(document.forms[0].priceINR2.value=='' || document.forms[0].gross_amt_inr_adjusted.value=='' || document.forms[0].availablebalance.value=='' || document.forms[0].adjustamt.value=='' || document.forms[0].adjustamt_sbc.value=='' || document.forms[0].adjustamt_kkc.value=='' )
		{
			flag=false;
			msg+="Enter proper advance ajustment amoumt or select No for adjust advance payment !! ";
		}
		if(document.forms[0].adv_inv_no.value=='' || document.forms[0].adv_inv_no.value==' ')//added new
		{
			flag=false;
			msg+="Enter proper Advance Invoice No.";
		}
		
		if(document.forms[0].adv_inv_dt.value=='' || document.forms[0].adv_inv_dt.value==' ')//added new
		{
			flag=false;
			msg+="Enter proper Advance Invoice Date.";
		}
		var tempbalsub=document.forms[0].balavailablecheckforsubmit.value;
		tempbalsub1=parseFloat(tempbalsub);
	//	alert(tempbalsub1);
		if(tempbalsub1<=0)
		{flag=false;
		
		//	alert("INSDIE");
			msg+="Please select No for adjust advance payment as there is zero available balance for this customer..!!";
		}
		alert("You are adjusting "+document.forms[0].adjustamt.value+" "+document.forms[0].currency_text.value+" !!!");
		
		
		if(document.forms[0].adjustamt_tax.value=='' || document.forms[0].adjustamt_tax.value==' ')//added new
		{
			flag=false;
			msg+="Enter proper Adjustment amount for Tax adjustment";
		}
		
		alert("You are adjusting "+document.forms[0].adjustamt_tax.value+" INR From Total Tax!!!");
		
		if(document.forms[0].adjustamt_sbc.value=='' || document.forms[0].adjustamt_sbc.value==' ')//added new
		{
			flag=false;
			msg+="Enter proper Adjustment amount for SBC adjustment";
		}
		
		alert("You are adjusting "+document.forms[0].adjustamt_sbc.value+" INR From SBC!!!");
		
		if(document.forms[0].adjustamt_kkc.value=='' || document.forms[0].adjustamt_kkc.value==' ')//added new
		{
			flag=false;
			msg+="Enter proper Adjustment amount for KKC adjustment";
		}
		
		alert("You are adjusting "+document.forms[0].adjustamt_kkc.value+" INR From KKC!!!");
		///////////RS 20170412
		
		var chk_mul_adv_inv = document.forms[0].chk_mul_adv_inv.value;
		if(chk_mul_adv_inv == "Y") {
			var adv_inv_len = document.forms[0].adv_inv.length;
			if(adv_inv_len == undefined) {
				var data = document.getElementById("adv_inv0").value;
				var date = document.getElementById("adv_inv_date0").value;
				if(data == '' || data == ' ' || date == '' || date == ' ') {
					flag = false; msg += 'Advance Invoice No Or Date Is Not Available \n';
				} 
			} else {
				for(var i = 0;i<adv_inv_len;i++){
					var data = document.getElementById("adv_inv"+i).value;
					var date = document.getElementById("adv_inv_date"+i).value;
					if(data == '' || data == ' ' || date == '' || date == ' ') {
						flag = false; msg += 'Advance Invoice No Or Date Is Not Available \n';
					} 
				}
			}
		}
	}
	else
	{
		if(obj!=undefined){
		alert("Advance Adjustment Clause is Applicable..Do you want to continue without adjustment?");
		alert("Tax Adjustment Clause is Applicable..Do you want to continue without adjustment?");
		}
		
	}
	var exchg_rate_cal_method = exchg_rate_cal_mthd;
	var offspec_flag = document.forms[0].offspec_flag.value;
	var size = parseInt(sz);
	var period_size = parseInt(period_sz);
	var tax_size = parseInt(tax_sz);
	var i = 0;
	var index = 0;
	var j = 0;
	//alert("exchg_rate_cal_method = "+exchg_rate_cal_method+",  size = "+size+",  &  period_size = "+period_size);
	
	var invoice_date = document.forms[0].invoice_date.value;
	var due_date = document.forms[0].due_date.value;
	
	var gross_amt_usd = parseFloat('0');
	var gross_amt_inr = parseFloat('0');
	
	if(exchg_rate_cal_method=='D')
	{
		for(i=0; i<size; i++)
		{
			if(document.forms[0].exch_rate[i].checked)
			{
				document.forms[0].exch_rate_cd.value = document.forms[0].exch_rate_code[i].value;
				document.forms[0].exch_rate_val.value = document.forms[0].exch_rate_value[i].value;
				
				if(i<4)
				{
					document.forms[0].exch_rate_dt.value = document.forms[0].previous_dt.value;
				}
				else if(i<8)
				{
					document.forms[0].exch_rate_dt.value = document.forms[0].inv_date.value;
				}
				else
				{
					document.forms[0].exch_rate_dt.value = document.forms[0].particular_date.value;
				}
			}
		}
		//alert("exch_rate_cd = "+document.forms[0].exch_rate_cd.value+",  exch_rate_val = "+document.forms[0].exch_rate_val.value+",  &  document.forms[0].exch_rate_dt.value = "+document.forms[0].exch_rate_dt.value);
	}
	else if(exchg_rate_cal_method=='A')
	{
		index = size*4;
		for(i=0; i<4; i++)
		{
			if(document.forms[0].exch_rate[i].checked)
			{
				document.forms[0].exch_rate_cd.value = document.forms[0].exch_rate_code[i].value;
				document.forms[0].start_index.value = ""+i;
				document.forms[0].end_index.value = ""+index;
			}
		}
		
		document.forms[0].exch_rate_dt.value = document.forms[0].bill_period_end_date.value;
	}
	
	var contact_person = document.forms[0].contact_person.value;
	var contact_person_nm = "";
	if(contact_person==null || contact_person=='' || contact_person==' ' || contact_person=='0')
	{
		msg += "Please Select The Proper Contact Person !!!\n\n";
		flag = false;
	}
	else
	{
		contact_person_nm = document.forms[0].contact_person[document.forms[0].contact_person.selectedIndex].innerText;
		document.forms[0].contact_person_nm.value = contact_person_nm;
		//alert("document.forms[0].contact_person_nm.value = "+document.forms[0].contact_person_nm.value);
	}
	
	if(invoice_date==null || invoice_date=='' || invoice_date=='0' || invoice_date==' ' || invoice_date=='  ')
	{
		msg += "Please Enter Correct Invoice Date, Then Submit The Billing Details !!!\n\n";
		flag = false;
	}
	
	if(due_date==null || due_date=='' || due_date=='0' || due_date==' ' || due_date=='  ')
	{
		msg += "Please Enter Correct Due Date, Then Submit The Billing Details !!!\n\n";
		flag = false;
	}
	
	var offspec_rate = "0";
	var offspec_qty = "0";
	if(offspec_flag=='Y')
	{
		offspec_rate = document.forms[0].offspec_rate.value;
		offspec_qty = document.forms[0].offspec_qty.value;
		
		if(offspec_rate==null || offspec_rate=='' || offspec_rate==' ' || offspec_rate=='0' || offspec_rate=='0.0' || offspec_rate=='0.00' || offspec_rate=='0.000' || offspec_rate=='0.0000')
		{
			msg += "Please Enter Correct Rate Of Accepted Offspec Gas !!!\n";
			flag = false;
		}
	}	
	
	if(exchg_rate_cal_method=='D')
	{
		var rt_val = document.forms[0].exch_rate_val.value;
		if(rt_val==null || rt_val=='' || rt_val==' ' || rt_val=='N/A' || rt_val=='0' || rt_val=='0.00')
		{
			msg += "Please Select The Exchange Rate Which Is Available !!!\nOR\nEnter The Exchange Rate From Interest & Exchange Rate Entry Form For The Selected Date !!!\n";
			flag = false;
		}
		else
		{//alert(period_size);
			if(period_size==1)
			{
				var sale_price = document.forms[0].sale_price.value;
				if(discount_flag_apply=='Y')
				{
					sale_price=discount_deducted_val;
				}
				var inv_qty = document.forms[0].daily_inv_qty.value;
						
				if(sale_price!=null && sale_price!='' && sale_price!=' ' && inv_qty!=null && inv_qty!='' && inv_qty!=' ')
				{
					if(parseFloat(""+sale_price)>0 && parseFloat(""+inv_qty)>0)
					{
						document.forms[0].daily_inv_amt_usd.value = ""+round((parseFloat(""+sale_price)*parseFloat(""+inv_qty)),8);
						document.forms[0].daily_inv_amt_inr.value = ""+round((parseFloat(""+document.forms[0].daily_inv_amt_usd.value)*parseFloat(rt_val)),8);
						gross_amt_usd += parseFloat(""+document.forms[0].daily_inv_amt_usd.value);
						gross_amt_inr += parseFloat(""+document.forms[0].daily_inv_amt_inr.value);
					}
				}
			}
			else if(period_size>1)
			{
				for(i=0;i<period_size;i++)
				{
					var sale_price = document.forms[0].sale_price.value;
					if(discount_flag_apply=='Y')
				{
					sale_price=discount_deducted_val;
				}
					var inv_qty = document.forms[0].daily_inv_qty[i].value;
							
					if(sale_price!=null && sale_price!='' && sale_price!=' ' && inv_qty!=null && inv_qty!='' && inv_qty!=' ')
					{
						if(parseFloat(""+sale_price)>0 && parseFloat(""+inv_qty)>0)
						{
							document.forms[0].daily_inv_amt_usd[i].value = ""+round((parseFloat(""+sale_price)*parseFloat(""+inv_qty)),8);
							document.forms[0].daily_inv_amt_inr[i].value = ""+round((parseFloat(""+document.forms[0].daily_inv_amt_usd[i].value)*parseFloat(rt_val)),8);
							gross_amt_usd += parseFloat(""+document.forms[0].daily_inv_amt_usd[i].value);
							gross_amt_inr += parseFloat(""+document.forms[0].daily_inv_amt_inr[i].value);
							
						}
					}
				}
			}
				
			var gross_amount_usd = parseFloat(""+offspec_rate)*parseFloat(""+offspec_qty);

			gross_amt_usd += gross_amount_usd;
			gross_amt_inr += (gross_amount_usd*parseFloat(rt_val));
		}
	}
	else if(exchg_rate_cal_method=='A')
	{
		index = size*4;
		var avg_rate = parseFloat('0');
		var z = 0;
		var y = 0;
		for(i=0; i<4; i++)
		{
			if(document.forms[0].exch_rate[i].checked)
			{
				z = 0;
				//Following Logic Has Been Introduced By Samik Shah On 3rd June, 2010 ...
				document.forms[0].start_index.value = ""+i;
				document.forms[0].end_index.value = ""+index;
				
				for(j=i; j<index; j=j+4)
				{
					var rt_val = document.forms[0].exch_rate_value[j].value;
					if(rt_val==null || rt_val=='' || rt_val==' ' || rt_val=='N/A' || rt_val=='0' || rt_val=='0.00')
					{
						msg += "Please Select The Exchange Rate Which Is Available For All Dates !!!\nOR\nEnter The Exchange Rate From Interest & Exchange Rate Entry Form For All Dates For Selected Billing Period !!!\n";
						flag = false;
						break;
					}
					else
					{
						avg_rate += parseFloat(rt_val);
						++y;
						
						if(period_size==1)
						{
							var sale_price = document.forms[0].sale_price.value;
							if(discount_flag_apply=='Y')
				{
					sale_price=discount_deducted_val;
				}
							var inv_qty = document.forms[0].daily_inv_qty.value;
							
							if(sale_price!=null && sale_price!='' && sale_price!=' ' && inv_qty!=null && inv_qty!='' && inv_qty!=' ')
							{
								if(parseFloat(""+sale_price)>0 && parseFloat(""+inv_qty)>0)
								{
									document.forms[0].daily_inv_amt_usd.value = ""+round((parseFloat(""+sale_price)*parseFloat(""+inv_qty)),8);
									document.forms[0].daily_inv_amt_inr.value = ""+round((parseFloat(""+document.forms[0].daily_inv_amt_usd.value)*parseFloat(rt_val)),8);
									gross_amt_usd += parseFloat(""+document.forms[0].daily_inv_amt_usd.value);
									gross_amt_inr += parseFloat(""+document.forms[0].daily_inv_amt_inr.value);
								}
							}
						}
						else if(period_size>1)
						{
							var sale_price = document.forms[0].sale_price.value;
							if(discount_flag_apply=='Y')
				{
					sale_price=discount_deducted_val;
				}
							var inv_qty = document.forms[0].daily_inv_qty[z].value;
							
							if(sale_price!=null && sale_price!='' && sale_price!=' ' && inv_qty!=null && inv_qty!='' && inv_qty!=' ')
							{
								if(parseFloat(""+sale_price)>0 && parseFloat(""+inv_qty)>0)
								{
									document.forms[0].daily_inv_amt_usd[z].value = ""+round((parseFloat(""+sale_price)*parseFloat(""+inv_qty)),8);
									document.forms[0].daily_inv_amt_inr[z].value = ""+round((parseFloat(""+document.forms[0].daily_inv_amt_usd[z].value)*parseFloat(rt_val)),8);
									gross_amt_usd += parseFloat(""+document.forms[0].daily_inv_amt_usd[z].value);
									gross_amt_inr += parseFloat(""+document.forms[0].daily_inv_amt_inr[z].value);
								}
							}
						}
					}
					++z;	
				}
			}
		}
		
		if(y>0)
		{
			avg_rate = avg_rate/y;
		}
		else
		{
			avg_rate = 0;
		}
		document.forms[0].exch_rate_val.value = avg_rate;
	}
	
	var exch_rate_val = parseFloat(""+document.forms[0].exch_rate_val.value);
	
	document.forms[0].exch_rate_val.value = ""+round(exch_rate_val,4);
	
	
	document.forms[0].raw_amt_usd.value=""+round(gross_amt_usd,2);
	document.forms[0].raw_amt_inr.value=""+round(gross_amt_inr,2);
	document.forms[0].gross_amt_usd.value = ""+round(gross_amt_usd,2);
	document.forms[0].gross_amt_inr.value = ""+round(gross_amt_inr,2);
	
	/*added for alert of tax unavilability*/
	
	var contact_Suppl_GST_DT = document.forms[0].contact_Suppl_GST_DT.value;
	var contact_Suppl_GST_NO = document.forms[0].contact_Suppl_GST_NO.value;
	var contact_Suppl_CST_NO = document.forms[0].contact_Suppl_CST_NO.value;
	var contact_Suppl_CST_DT = document.forms[0].contact_Suppl_CST_DT.value;
	var contact_Suppl_Service_Tax_NO = document.forms[0].contact_Suppl_Service_Tax_NO.value;
	
	var contract_type_tax = document.forms[0].contract_type_tax.value;
	var tax_size = document.forms[0].tax_size.value;
	
	var contact_Suppl_PAN_NO = document.forms[0].contact_Suppl_PAN_NO.value;
	var contact_Suppl_PAN_DT = document.forms[0].contact_Suppl_PAN_DT.value;
	
	var flag_tax=false;
	var count_tax=0;
	var count_t1=0;
	var count_t2=0;
	var flags=false;
	var panflags=false;
	var panflagc=false;
	if(contract_type_tax=='C' || contract_type_tax=='R' || contract_type_tax=='T')
	{
		if(contact_Suppl_Service_Tax_NO=='')
		{
			msg+="Service Tax No Of Supplier is not Available!!! \n";
			flag_tax=true;
		}
		else
		{
			count_t1++;
			//alert("Service Tax count_t1: "+count_t1);
		}
		
		if(contact_Suppl_PAN_NO=='')
		{
			msg+="Supplier PAN is not Available!!! \n";
			flag_tax=true;
			panflags=true;
		}
		else
		{
			count_t1++;
			//alert("Service Tax count_t1: "+count_t1);
		}
		
		if(tax_size>1)
		{
			for(var i=0;i<parseInt(tax_size);i++)
			{
				var no=document.forms[0].vstat_no[i].value;
				
				//alert("document.forms[0].vstat_nm[i].value: "+document.forms[0].vstat_nm[i].value);
				if(no==' ' || no=='')
				{
						msg+="Customer "+document.forms[0].vstat_nm[i].value+" is not Available!!! \n";
						flag_tax=true;
						//	flags=true;
						var nm=document.forms[0].vstat_nm[i].value;
						if(nm=='PAN No.')
						{
							panflagc=true;
						}
				}
				else
				{
					count_t2++;
					//alert("Customer if count_t2: "+count_t2);
					//	flags=true;
				}	
			}	
		}
		else
		{
			//for(var i=0;i<parseInt(tax_size);i++)
			{
				var no=document.forms[0].vstat_no.value;
				if(no==' ' || no=='')
				{
						msg+="Customer "+document.forms[0].vstat_nm.value+" is not Available!!! \n";
						flag_tax=true;
						var nm=document.forms[0].vstat_nm.value;
						if(nm=='PAN No.')
						{
							panflagc=true;
						}
						//	flags=true;
				}
				else
				{
					count_t2++;
					//alert("Customer else count_t2: "+count_t2);
					//	flags=true;
				}	
			}
		}
		
	}
	else
	{
		flags=true;
		//alert("else contract_type_tax: "+contract_type_tax);
		if(tax_size>1)
		{
			for(var i=0;i<parseInt(tax_size);i++)
			{
				var no=document.forms[0].vstat_no[i].value;
				//alert("document.forms[0].vstat_nm[i].value: "+document.forms[0].vstat_nm[i].value);
				if(no==' ' || no=='')
				{
						msg+="Customer "+document.forms[0].vstat_nm[i].value+" is not Available!!! \n";
						flag_tax=true;
						var nm=document.forms[0].vstat_nm[i].value;
						if(nm=='PAN No.')
						{
							panflagc=true;
						}
						//	flags=true;
				}
				else
				{
					count_t2++;
					//alert("Customer if count_t2: "+count_t2);
					//	flags=true;
				}	
			}	
		}
		else
		{
			//for(var i=0;i<parseInt(tax_size);i++)
			{
				var no=document.forms[0].vstat_no.value;
				if(no==' ' || no=='')
				{
						msg+="Customer "+document.forms[0].vstat_nm.value+" is not Available!!! \n";
						flag_tax=true;
						var nm=document.forms[0].vstat_nm.value;
						if(nm=='PAN No.')
						{
							panflagc=true;
						}
						//	flags=true;
				}
				else
				{
					count_t2++;
					//alert("Customer else count_t2: "+count_t2);
					//	flags=true;
				}	
			}
		}
		
		if(contact_Suppl_CST_DT=='' || contact_Suppl_CST_NO=='')
		{
			msg+="Supplier CST NO/DT is not Available!!! \n";
			flag_tax=true;
		}
		else
		{
			count_t1++;
			//alert("Supplier CST count_t1: "+count_t1);
		}	
		
		if(contact_Suppl_PAN_NO=='')
		{
			msg+="Supplier PAN is not Available!!! \n";
			flag_tax=true;
			panflags=true;
		}
		else
		{
			count_t1++;
			//alert("Supplier CST count_t1: "+count_t1);
		}	
		
		if(contact_Suppl_GST_DT=='' || contact_Suppl_GST_NO=='')
		{
			msg+="Supplier GST NO/DT is not Available!!! \n";
			flag_tax=true;
		}
		else
		{
			count_t1++;
			//alert("Supplier GST count_t1: "+count_t1);
		}
	}
	
	var taxsize=document.forms[0].taxsize.value;
	//taxsize=0;
	var flag_stop=false;
	if(taxsize=='0')
	{
		msg+="Tax Structure is not Applicable!!!  \n";
		flag_tax=true;
		flag_stop=true;
	}
	if(count_t1==0 || (count_t2==0 && flags))
	{
		flag_stop=true;
	}	
	
	if(flag_stop)
	{
		alert("TAX NO/TAX STRUCTURE ARE NOT AVAILABLE!!!!!!\n INVOICE CAN NOT BE GENERATED!!!");
	}	
	else
	{
			if(flag)
			{
				if(panflags!=true && panflagc!=true)
				{
					var a = confirm("Do You Want To Generate Invoice ???");
					if(a)
					{
						if(mode=='Y') {
							if(parseInt(document.forms[0].Adjust_total_avail_bal_sbc.value)==0) {
								adjustmamountforadjust_sbc();	
							}
							if(parseInt(document.forms[0].Adjust_total_avail_bal_kkc.value)==0) {
								adjustmamountforadjust_kkc();	
							}
						}
						document.forms[0].submit();
					}
				}
				else
				{
					if(panflags==true && panflagc!=true)
					{
						alert("Please add Supplier PAN first!!!");	
					}
					else if(panflagc==true && panflags!=true)
					{
						alert("Please add Customer PAN first!!!");
					}
					else if(panflagc==true && panflags==true)
					{
						alert("Please add Supplier and Customer PAN first!!!");
					}
					else
					{}
				}
			}
			else if(flag_stop)
			{
				alert(msg);
			} else {
				alert(msg);
			}
	}
}


function doClose(month,year,bill_cycle,msg)
{
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	//alert("get.......");
	
	window.opener.refreshPageFromChild2(msg, month, year, bill_cycle, write_permission, delete_permission, print_permission, approve_permission, audit_permission, check_permission, authorize_permission)
	window.close();
}
function enableDiv()
{
	var obj = document.forms[0].adv_inv;
	if(obj != undefined) {
		var len = obj.length;
		if(document.forms[0].chk_mul_adv_inv.checked == true) {
			document.forms[0].chk_mul_adv_inv.value = "Y";
			if(len == undefined) {
				document.getElementById("adv_inv0").disabled = false;
				document.getElementById("adv_inv_date0").disabled = false;
			} else {
				for(var i=0;i<len;i++) {
					document.getElementById("adv_inv"+i).disabled = false;
					document.getElementById("adv_inv_date"+i).disabled = false;
				}
			}
			document.forms[0].add_Invoice.disabled = false;
		} else {
			document.forms[0].chk_mul_adv_inv.value = "N";
			if(len == undefined) {
				document.getElementById("adv_inv0").disabled = true;
				document.getElementById("adv_inv_date0").disabled = true;
			} else {
				for(var i=0;i<len;i++) {
					document.getElementById("adv_inv"+i).disabled = true;
					document.getElementById("adv_inv_date"+i).disabled = true;
				}
			}
			document.forms[0].add_Invoice.disabled = true;
		}
	} else {
		addInvoice();
	}
}
function addInvoice()
{
	var msg = ''; var flag = true;
	var obj = document.forms[0].adv_inv;
	var count = 1;
	if(obj != undefined) {
		var len = obj.length;
		if(len == undefined) {
			var data = document.getElementById("adv_inv0").value;
			var date = document.getElementById("adv_inv_date0").value;
			if(data == '' || data == ' ' || data == '0' || date == '' || date == ' ' || date == '0') {
				msg = 'Please Enter Advance Invoice No & Date For 1.';
				flag = false;
			}
		} else {
			for(var i=0;i<len;i++) {
				var idVal = "adv_inv"+i; var idDate = "adv_inv_date"+i;
				var data = document.getElementById(idVal).value;
				var date = document.getElementById(idDate).value;
				if(data == '' || data == ' ' || data == '0' || date == '' || date == ' ' || date == '0') {
					msg += "Please Enter Advance Invoice No & Date For "+(i+1)+".";
					flag = false;
				}
			}
			count = parseFloat(len);
		}
		count = count + 1;
	}
	if(flag == true) {
		if(count == 4) { msg += 'You Can Not Add More Than 3 Advance Invoices.'; alert(msg); } else { 
		var div = document.getElementById("addNewTextBox");
		var row = document.createElement("TR");
		var td0=document.createElement("TD"); 
		var td1=document.createElement("TD"); td1.align="left"; td1.colspan="5";
		var tdbold=document.createElement("B");
		var tab=document.createTextNode(count+". ");
		var cin=document.createElement("input");
			cin.type="text";
			cin.name="adv_inv";
			cin.id = "adv_inv"+(count-1);
			cin.size="25";
			cin.value=""; 
		var tab1=document.createTextNode(" - ");
		var cin1=document.createElement("input");
		cin1.type="text";
		cin1.name="adv_inv_date";
		cin1.id = "adv_inv_date"+(count-1);
		cin1.size="12"; cin1.maxlength="10";
		cin1.value=""; cin1.title = 'Enter Date in DD/MM/YYYY Format';
		cin1.placeholder="DD/MM/YYYY";
			td1.appendChild(tdbold);td1.appendChild(tab);
			td1.appendChild(cin); td1.appendChild(tab1); td1.appendChild(cin1);
			row.appendChild(td0);row.appendChild(td1);
			div.appendChild(row);
		}
	} else { alert(msg); }
}
function displaydetails(val)
	{
		var date=document.forms[0].exch_rate_dt.value;
		var exchrate=document.forms[0].exch_rate_val.value;
		var Adjust_amt_mst=document.forms[0].Adjust_amt_mst.value;
		var Adjust_cur_mst=document.forms[0].Adjust_cur_mst.value;
		var Adjust_total_avail_bal=document.forms[0].Adjust_total_avail_bal.value;
		
		var	Adv_submitted_flag1=document.forms[0].Adv_submitted_flag.value;
		var pay_type1=document.forms[0].pay_type1.value;
		document.forms[0].advflg.value=val;
		if(exchrate=='' || exchrate==" ")
		{
			document.forms[0].adjust[1].checked=true;
			document.getElementById('adjustmentdetails').style.display='none';
			alert("Please select the proper exchange rate..!!");
			/*for(var i=0;i<document.forms[0].adjust.length;i++)
			{
				if(document.forms[0].adjust[i].checked)
				{
				}
				else
				{document.forms[0].adjust[i].checked=true;
				}						
			}*/
		} 
		else
		{
		
			if (window.XMLHttpRequest)
			{
				request22 = new XMLHttpRequest();
			} 
			else if (window.ActiveXObject)
			{
				request22 = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			if(request22)
		   	{
// 				alert("../advance_payment/frm_invoice_adjustment_ajax.jsp?date="+date+"&Adjust_amt_mst="+Adjust_amt_mst+"&Adjust_cur_mst="+Adjust_cur_mst+"&Adjust_total_avail_bal="+Adjust_total_avail_bal);
		  		 var url="../advance_payment/frm_invoice_adjustment_ajax.jsp?date="+date+"&Adjust_amt_mst="+Adjust_amt_mst+"&Adjust_cur_mst="+Adjust_cur_mst+"&Adjust_total_avail_bal="+Adjust_total_avail_bal+"&payment_md="+pay_type1;
		  		 request22.open("POST",url);
		  		 request22.onreadystatechange = function() 
		    	 {
		    		 if(request22.readyState==4)
		     		 {
		    			if(val=='BA'){
		    				advId="adjustment";
		    				advId1="afteradjustment";
		    			}else{
		    				advId="afteradjustment";
		    				advId1="adjustment";
		    			}
	    				var a=document.getElementById(advId).innerHTML=request22.responseText;  
						document.getElementById(advId).style.visibility="visible";
						document.getElementById(advId1).innerHTML="";
						document.getElementById(advId1).style.visibility="hidden";
		     		 }
				 }
		  		 request22.send();
		  		if(document.forms[0].adjustdetails[0].checked){ //HS20160903
					actualadvamt=document.forms[0].gross_amt_inr.value;
					document.forms[0].double_final_gross_amt_inr.value=round(actualadvamt,2);
					var taxratesize=document.getElementById('taxratesize').value;
					var taxadjamt=0;
					for(l=0;l<taxratesize;l++){
						var taxrate=document.getElementById('taxrate'+l).value;
						document.forms[0].tax_amts[l].value=round((actualadvamt*taxrate/100),2);
						taxadjamt+=parseFloat(document.forms[0].tax_amts[l].value);
					}
					document.forms[0].total_tax.value=round(taxadjamt,2);
					document.forms[0].net_amt_inr.value=""+round(parseFloat(parseFloat(actualadvamt)+round(taxadjamt,2)),2);
				}
		     }
			 else 
			 {
			    document.getElementById("adjustment").innerHTML="";
			    document.getElementById("adjustment").style.visibility="hidden";
				document.getElementById("afteradjustment").innerHTML="";
				document.getElementById("afteradjustment").style.visibility="hidden";
				alert("Your browser doesn't support AJax !"); 
			 }
			 
			 if(document.forms[0].TAX_ADV_ADJUST_FLAG_MST.value=='Y')
			 {
				 document.forms[0].MODIFY_TAX_ADV_ADJUSTMENT.value="";
				 document.forms[0].MODIFY_TAX_INV_AMT_INR.value="";
				 displaytaxadjustmentdetails(); //alert("2");
				 document.getElementById("adjustamt_tax").disabled=false;
				 document.forms[0].adjustsign_tax.disabled=false;
				 document.getElementById("adjustamt_tax").className="";
			 }
			/////SB20160506: added for SBC
			 if(document.forms[0].SBC_Flag_Service.value=='N')
			 { 
				 document.forms[0].MODIFY_TAX_ADV_ADJUSTMENT.value="";
				 document.forms[0].MODIFY_TAX_INV_AMT_INR.value="";
				 displaySBCadjustmentdetails();
				 document.getElementById("adjustamt_tax").disabled=false;
				 document.forms[0].adjustsign_tax.disabled=false;
				 document.getElementById("adjustamt_tax").className="";
			 }
			 if(document.forms[0].KKC_Flag_Service.value=='N')
			 { 
				 document.forms[0].MODIFY_TAX_ADV_ADJUSTMENT.value="";
				 document.forms[0].MODIFY_TAX_INV_AMT_INR.value="";
				 displaySBCadjustmentdetails();
				 document.getElementById("adjustamt_tax").disabled=false;
				 document.forms[0].adjustsign_tax.disabled=false;
				 document.getElementById("adjustamt_tax").className="";
			 }
		}
}	
function displaytaxadjustmentdetails()
{
	var advFlag='';
	if(document.forms[0].activity.value=="update"){
		advFlag=document.forms[0].advFlag.value;
	}
	var advadjflg='';
	if(document.forms[0].adjustdetails[1].checked){  advadjflg=document.forms[0].adjustdetails[1].value;
	}else{  advadjflg=document.forms[0].adjustdetails[0].value;   }
		//document.forms[0].adjustdetails.value;
	var inv_date=document.forms[0].exch_rate_dt.value;
	
	var Adjust_total_avail_bal_tax=document.forms[0].Adjust_total_avail_bal_tax.value;
	var MODIFY_TAX_ADV_ADJUSTMENT=document.forms[0].MODIFY_TAX_ADV_ADJUSTMENT.value;
	var MODIFY_TAX_INV_AMT_INR=document.forms[0].MODIFY_TAX_INV_AMT_INR.value;
	
	var Adjust_total_avail_bal_sbc=document.forms[0].Adjust_total_avail_bal_sbc.value; //SB20160506
	var MODIFY_SBC_ADV_ADJUSTMENT=document.forms[0].MODIFY_SBC_ADV_ADJUSTMENT.value; //SB20160506
	var MODIFY_SBC_INV_AMT_INR=document.forms[0].MODIFY_SBC_INV_AMT_INR.value; //SB20160506
	
	var Adjust_total_avail_bal_kkc=document.forms[0].Adjust_total_avail_bal_kkc.value; //SB20160506
	var MODIFY_KKC_ADV_ADJUSTMENT=document.forms[0].MODIFY_KKC_ADV_ADJUSTMENT.value; //SB20160506
	var MODIFY_KKC_INV_AMT_INR=document.forms[0].MODIFY_KKC_INV_AMT_INR.value; //SB20160506
	//HS20160903
	if(document.forms[0].activity.value=="update"){
		if(advFlag==advadjflg){ 
		}else{
			MODIFY_TAX_ADV_ADJUSTMENT='';
			MODIFY_TAX_INV_AMT_INR='';
			
			if(parseInt(Adjust_total_avail_bal_sbc)==0) { MODIFY_SBC_ADV_ADJUSTMENT=0;
			}else{ MODIFY_SBC_ADV_ADJUSTMENT=''; }
			MODIFY_SBC_INV_AMT_INR='';
			
			if(parseInt(Adjust_total_avail_bal_kkc)==0) { MODIFY_KKC_ADV_ADJUSTMENT=0; 
			}else{ MODIFY_KKC_ADV_ADJUSTMENT=''; }
			
			MODIFY_KKC_INV_AMT_INR='';
		}
	}
	//HS20160903
	var text0="<table width='100%'>";
		text0+="<tr class='highlighttext' valign='top'>";
		text0+="<td colspan='6'>";
		text0+="	<div align='left'>";
		text0+="			<font size='2'><b>&nbsp;&nbsp;Tax Adjust Payment </b></font>";
		text0+="	&nbsp;&nbsp;";
		text0+="	</div>";
		text0+="	</td>";
		text0+="</tr>";
		text0+="<tr class='title2'>";
		text0+="<td width='25%'><font size='1.2px'><b>&nbsp;TAX Balance Available </b></font></div></td>";
		text0+="<td width='15%'><div align='center'><font size='1.2px'><b>Adjustment on Date</b></font></div></td>";
		text0+="<td width='15%'><div align='center'><font size='1.2px'><b>Amount To be Adjusted</b></font></div></td>";
		text0+="<td width='15%'><div align='center'><font size='1.2px'><b>Adjust</b></font></div></td>";
		text0+="<td width='15%' colspan='2'><div align='center'><font size='1.2px'><b> Tax Amount </b></font></div></td>";
		text0+="</tr>";
		text0+="<tr class='content1' valign='middle'>";
		text0+="<td><div align='center'><b>ST&nbsp;&nbsp;&nbsp;<input type='text' name='availablebalance_tax' size='10' value='"+Adjust_total_avail_bal_tax+"' style='text-align:right' class='mkRdly' > INR<span id=''></span></b></div></td>";
		text0+="<td><div align='center'><b>"+inv_date+"</b></div></td>";
		text0+="<td><div align='center'><b>";
			if(document.forms[0].activity.value=="update"){ 
			
		text0+="	<input type='text' name='adjustamt_tax' id='adjustamt_tax' size='10' style='text-align: right;' value='"+MODIFY_TAX_ADV_ADJUSTMENT+"'  onchange='adjustmamountforadjust_tax();'> INR";
			}else{
			
			if(document.forms[0].Adv_submitted_flag.value=="Y"){ 
		text0+="	<input type='text' name='adjustamt_tax' id='adjustamt_tax' size='10' style='text-align: right;' value='"+MODIFY_TAX_ADV_ADJUSTMENT+"' onchange='adjustmamountforadjust_tax();'> INR";
			}else{
		text0+="	<input type='text' name='adjustamt_tax' id='adjustamt_tax' size='10' style='text-align: right;' value='' class='mkRdly' onchange='adjustmamountforadjust_tax();'> INR";
		}} 
			
		text0+="	<span id=''></span>";
		text0+="</b></div></td>";
		text0+="<td><div align='center'><b>";
		if(document.forms[0].activity.value=="update"){ 
			text0+="<select name='adjustsign_tax' id='adjustsign_tax' onchange='adjustmamountforadjust_tax();'>";
			text0+="<option value='1'>-</option> <option value='2'>+</option></select>";
			
		}else{
		if(document.forms[0].Adv_submitted_flag.value=="Y"){ 
		text0+="	<select name='adjustsign_tax' id='adjustsign_tax' onchange='adjustmamountforadjust_tax();'>";
		text0+="	<option value='1'>-</option> <option value='2'>+</option></select>";
		}else{ 
		text0+="	<select name='adjustsign_tax' id='adjustsign_tax' disabled='disabled' onchange='adjustmamountforadjust_tax();'>";
		text0+="	<option value='1'>-</option> <option value='2'>+</option></select>";
		}} 
		text0+="</b></div></td>";
		text0+="<td colspan='2'><div align='center' ><b>";
		if(document.forms[0].activity.value=="update"){ 
		text0+="<input type='text' name='gross_amt_inr_adjusted_tax' size='10' value='"+MODIFY_TAX_INV_AMT_INR+"' style='text-align:right' class='mkRdly'> INR";
		}else{ 
		text0+="<input type='text' name='gross_amt_inr_adjusted_tax' size='10' value='' style='text-align:right' class='mkRdly'> INR";
		 }
			
		text0+="	<span id='gross_amt_inr_adjusteddiv_tax'></span>";
		text0+="</b></div></td>";
		text0+="</tr>";
		text0+="<tr class='title2' valign='middle'>";
		text0+="<td colspan='6'><div align='right' style='padding-right: 33px'><span id='submited_text_tax'></span><input type='button' name='advance_tax_submit' value='Save' onclick='saveadvance_tax_data();'></td>";
		text0+="</tr>";
		
		//////SB20160506///////////
	//alert("2 SBC Adj: ");
		text0+="<tr class='content1' valign='middle'>";
		text0+="<td><div align='center'><b>SBC&nbsp;&nbsp;&nbsp;<input type='text' name='availablebalance_sbc' size='10' value='"+Adjust_total_avail_bal_sbc+"' style='text-align:right' class='mkRdly' > INR<span id=''></span></b></div></td>";
		text0+="<td><div align='center'><b>"+inv_date+"</b></div></td>";
		text0+="<td><div align='center'><b>";//alert("3 SBC Adj: "+document.forms[0].activity.value);
			
			if(document.forms[0].activity.value=="update"){ //alert("4. SBC Adj: ");
				if(parseInt(Adjust_total_avail_bal_sbc)==0) {
					text0+="	<input type='text' name='adjustamt_sbc' id='adjustamt_sbc' size='10' style='text-align: right;' value='"+MODIFY_SBC_ADV_ADJUSTMENT+"' readonly onchange='adjustmamountforadjust_sbc();'> INR";
				} else {
					text0+="	<input type='text' name='adjustamt_sbc' id='adjustamt_sbc' size='10' style='text-align: right;' value='"+MODIFY_SBC_ADV_ADJUSTMENT+"'  onchange='adjustmamountforadjust_sbc();'> INR";
				}
			}else{
			
			if(document.forms[0].Adv_submitted_flag.value=="Y"){ //alert("5 SBC Adj: ");
				
					text0+="	<input type='text' name='adjustamt_sbc' id='adjustamt_sbc' size='10' style='text-align: right;' value='"+MODIFY_SBC_ADV_ADJUSTMENT+"' onchange='adjustmamountforadjust_sbc();'> INR";
				
			}else{
				if(parseInt(Adjust_total_avail_bal_sbc)==0) {
					text0+="	<input type='text' name='adjustamt_sbc' id='adjustamt_sbc' size='10' style='text-align: right;' value='0' readonly onchange='adjustmamountforadjust_sbc();'> INR";
					
				} else {
					text0+="	<input type='text' name='adjustamt_sbc' id='adjustamt_sbc' size='10' style='text-align: right;' value='' onchange='adjustmamountforadjust_sbc();'> INR";
				}
			}
		} 
			
		text0+="	<span id=''></span>";
		text0+="</b></div></td>";
		text0+="<td><div align='center'><b>";
		if(document.forms[0].activity.value=="update"){ 
			text0+="<select name='adjustsign_sbc' id='adjustsign_sbc' onchange='adjustmamountforadjust_sbc();'>";
			text0+="<option value='1'>-</option> <option value='2'>+</option></select>";
			
		}else{
		if(document.forms[0].Adv_submitted_flag.value=="Y"){ 
		text0+="	<select name='adjustsign_sbc' id='adjustsign_sbc' onchange='adjustmamountforadjust_sbc();'>";
		text0+="	<option value='1'>-</option> <option value='2'>+</option></select>";
		}else{ 
		text0+="	<select name='adjustsign_sbc' id='adjustsign_sbc'  onchange='adjustmamountforadjust_sbc();'>";
		text0+="	<option value='1'>-</option> <option value='2'>+</option></select>";
		}} 
		text0+="</b></div></td>";
		text0+="<td colspan='2'><div align='center' ><b>";
		if(document.forms[0].activity.value=="update"){ 
		text0+="<input type='text' name='gross_amt_inr_adjusted_sbc' size='10' value='"+MODIFY_SBC_INV_AMT_INR+"' style='text-align:right' class='mkRdly'> INR";
		}else{ 
		text0+="<input type='text' name='gross_amt_inr_adjusted_sbc' size='10' value='' style='text-align:right' class='mkRdly'> INR";
		 }
			
		text0+="	<span id='gross_amt_inr_adjusteddiv_sbc'></span>";
		text0+="</b></div></td>";
		text0+="</tr>";
		text0+="<tr class='title2' valign='middle'>";
		if(parseInt(Adjust_total_avail_bal_sbc)==0) {
			text0+="<td colspan='6'><div align='right' style='padding-right: 33px'><span id='submited_text_sbc'></span></td>";
		} else {
			text0+="<td colspan='6'><div align='right' style='padding-right: 33px'><span id='submited_text_sbc'></span><input type='button' name='advance_sbc_submit' value='Save' onclick='saveadvance_sbc_data();'></td>";
		}
		text0+="</tr>";
		//////^SB20160506/////////////
		
		
		//////KKC////////////////////
		text0+="<tr class='content1' valign='middle'>";
		text0+="<td><div align='center'><b>KKC&nbsp;&nbsp;&nbsp;<input type='text' name='availablebalance_kkc' size='10' value='"+Adjust_total_avail_bal_kkc+"' style='text-align:right' class='mkRdly' > INR<span id=''></span></b></div></td>";
		text0+="<td><div align='center'><b>"+inv_date+"</b></div></td>";
		text0+="<td><div align='center'><b>";//alert("3 KKC Adj: "+document.forms[0].activity.value);
			if(document.forms[0].activity.value=="update"){ //alert("4. KKC Adj: ");
			if(parseInt(Adjust_total_avail_bal_kkc)==0) {
				text0+="	<input type='text' name='adjustamt_kkc' id='adjustamt_kkc' size='10' style='text-align: right;' value='"+MODIFY_KKC_ADV_ADJUSTMENT+"' readonly  onchange='adjustmamountforadjust_kkc();'> INR";
			} else {
				text0+="	<input type='text' name='adjustamt_kkc' id='adjustamt_kkc' size='10' style='text-align: right;' value='"+MODIFY_KKC_ADV_ADJUSTMENT+"'  onchange='adjustmamountforadjust_kkc();'> INR";
			}
			}else{
			
			if(document.forms[0].Adv_submitted_flag.value=="Y"){ //alert("5 KKC Adj: ");
				text0+="	<input type='text' name='adjustamt_kkc' id='adjustamt_kkc' size='10' style='text-align: right;' value='"+MODIFY_KKC_ADV_ADJUSTMENT+"' onchange='adjustmamountforadjust_kkc();'> INR";
			}else{
				if(parseInt(Adjust_total_avail_bal_kkc)==0) {
					text0+="	<input type='text' name='adjustamt_kkc' id='adjustamt_kkc' size='10' style='text-align: right;' value='0' readonly onchange='adjustmamountforadjust_kkc();'> INR";
				} else {
					text0+="	<input type='text' name='adjustamt_kkc' id='adjustamt_kkc' size='10' style='text-align: right;' value='' onchange='adjustmamountforadjust_kkc();'> INR";
				}
		}} 
			
		text0+="	<span id=''></span>";
		text0+="</b></div></td>";
		text0+="<td><div align='center'><b>";
		if(document.forms[0].activity.value=="update"){ 
			text0+="<select name='adjustsign_kkc' id='adjustsign_kkc' onchange='adjustmamountforadjust_kkc();'>";
			text0+="<option value='1'>-</option> <option value='2'>+</option></select>";
			
		}else{
		if(document.forms[0].Adv_submitted_flag.value=="Y"){ 
		text0+="	<select name='adjustsign_kkc' id='adjustsign_kkc' onchange='adjustmamountforadjust_kkc();'>";
		text0+="	<option value='1'>-</option> <option value='2'>+</option></select>";
		}else{ 
		text0+="	<select name='adjustsign_kkc' id='adjustsign_kkc'  onchange='adjustmamountforadjust_kkc();'>";
		text0+="	<option value='1'>-</option> <option value='2'>+</option></select>";
		}} 
		text0+="</b></div></td>";
		text0+="<td colspan='2'><div align='center' ><b>";
		if(document.forms[0].activity.value=="update"){ 
		text0+="<input type='text' name='gross_amt_inr_adjusted_kkc' size='10' value='"+MODIFY_KKC_INV_AMT_INR+"' style='text-align:right' class='mkRdly'> INR";
		}else{ 
		text0+="<input type='text' name='gross_amt_inr_adjusted_kkc' size='10' value='' style='text-align:right' class='mkRdly'> INR";
		 }
			
		text0+="	<span id='gross_amt_inr_adjusteddiv_kkc'></span>";
		text0+="</b></div></td>";
		text0+="</tr>";
		text0+="<tr class='title2' valign='middle'>";
		if(parseInt(Adjust_total_avail_bal_kkc)==0) {
			text0+="<td colspan='6'><div align='right' style='padding-right: 33px'><span id='submited_text_kkc'></span></td>";
		} else {
			text0+="<td colspan='6'><div align='right' style='padding-right: 33px'><span id='submited_text_kkc'></span><input type='button' name='advance_kkc_submit' value='Save' onclick='saveadvance_kkc_data();'></td>";
		}
		text0+="</tr>";
		/////KKC/////////////////////	
		text0+="</table>";
	document.getElementById("tax_adv_div").innerHTML=text0;
	document.getElementById("tax_adv_div").style.visibility='visible';
}
 
function hideadjustmenttaxdetails()
{
	//document.getElementById("tax_adv_div").innerHTML="";
	//document.getElementById("tax_adv_div").style.visibility='hidden';
	document.getElementById("tax_adv_div").style.visibility='hidden';
	document.getElementById("tax_adv_div").innerHTML='';
}


function saveadvance_tax_data()
{
	//alert(document.forms[0].advance_tax_submit.value);
	
	
	document.forms[0].Adv_submitted_flag.value='Y';
	var adv_inv_no=document.forms[0].adv_inv_no.value;
	var adv_inv_dt=document.forms[0].adv_inv_dt.value;
	var currency=document.forms[0].TAX_ADV_ADJUST_CUR.value;
	//var recevial=document.forms[0].recevial.value;
	var adjustamt=document.forms[0].adjustamt_tax.value;
	var priceINR2=document.forms[0].gross_amt_inr_adjusted_tax.value;
	var adjustsign=document.forms[0].adjustsign_tax.value;
	//adjustamt=round(adjustamt,2);
	
	var customer_cd=document.forms[0].customer_cd.value;
	var raw_amt_usd=document.forms[0].raw_amt_usd.value;
	var raw_amt_inr=document.forms[0].raw_amt_inr.value;
	var fgsa_no=document.forms[0].fgsa_no.value;
	var fgsa_rev_no=document.forms[0].fgsa_rev_no.value;
	var sn_no=document.forms[0].sn_no.value;
	var sn_rev_no=document.forms[0].sn_rev_no.value;
	var contract_type=document.forms[0].contract_type.value;
	var customer_plant_seq_no=document.forms[0].customer_plant_seq_no.value;
	var bill_period_start_date=document.forms[0].bill_period_start_date.value;
	var hlpl_Inv_Seq_No=document.forms[0].hlpl_Inv_Seq_No.value;
	var financial_Year=document.forms[0].financial_Year.value;
	var bill_period_end_date=document.forms[0].bill_period_end_date.value;
	var invoice_date=document.forms[0].invoice_date.value;
	var mapping_id=document.forms[0].mapping_id.value;
	var	exch_rate_val=document.forms[0].exch_rate_val.value;
	var activity=document.forms[0].activity.value;
	var save_type="tax_adv";
	
	
	var flag=false;
	var msg="Please Check Following details:\n";
	if(adv_inv_no=='')
	{
		flag=true;
		msg+="Advance Invoice No. \n";
		
	}
	if(adv_inv_dt=='')
	{
		flag=true;
		msg+="Advance invoice Dt \n";
		
	}
	//alert("-----"+adjustamt);
	//alert(flag);
	if(adjustamt=='' || adjustamt==' ')
	{
		//alert("In eles");
		flag=true;
		msg+="Tax Amount to be Adjusted \n";
		
	}
	adjustamt=round(adjustamt,2);
	if(flag==false)
	{
				if (window.XMLHttpRequest)
				{
					request22 = new XMLHttpRequest();
				} 
				else if (window.ActiveXObject)
				{
					request22 = new ActiveXObject("Microsoft.XMLHTTP");
				}
				if(request22)
			   	{
			  		 var url="../advance_payment/frm_save_advance_data_ajax.jsp?customer_cd="+customer_cd+"&save_type="+save_type+"&raw_amt_usd="+raw_amt_usd+"&raw_amt_inr="+raw_amt_inr+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_seq_no="+customer_plant_seq_no+"&hlpl_Inv_Seq_No="+hlpl_Inv_Seq_No+"&financial_Year="+financial_Year+"&invoice_date="+invoice_date+"&mapping_id="+mapping_id+"&bill_period_end_date="+bill_period_end_date+"&adv_inv_dt="+adv_inv_dt+"&currency="+currency+"&adjustamt="+adjustamt+"&priceINR2="+priceINR2+"&adjustsign="+adjustsign+"&exch_rate_val="+exch_rate_val+"&activity="+activity;
			  		
					 //alert(url);
			  		 request22.open("POST",url);
			  		 request22.onreadystatechange = function() 
			    	 {
			    		 if(request22.readyState==4)
			     		 {	
			     		 		document.getElementById("submited_text_tax").innerHTML="<b>Advance Tax Adjustment Submitted..</b>";
				  	  	 }
					 }
			  		 request22.send(null);
			     }
				 else 
				 {
				    alert("Your browser doesn't support AJax !"); 
				 }
	}
	else
	{
		alert(msg);
	}	
}
function saveadvance_sbc_data()
{
	//alert(document.forms[0].advance_sbc_submit.value);	
	document.forms[0].Adv_submitted_flag.value='Y';
	var adv_inv_no=document.forms[0].adv_inv_no.value;
	var adv_inv_dt=document.forms[0].adv_inv_dt.value;
	var currency=document.forms[0].SBC_ADV_ADJUST_CUR.value;
	//var recevial=document.forms[0].recevial.value;
	var adjustamt=document.forms[0].adjustamt_sbc.value;
	var priceINR2=document.forms[0].gross_amt_inr_adjusted_sbc.value;
	var adjustsign=document.forms[0].adjustsign_sbc.value;
	//adjustamt=round(adjustamt,2);
	//alert(priceINR2);
	var customer_cd=document.forms[0].customer_cd.value;
	var raw_amt_usd=document.forms[0].raw_amt_usd.value;
	var raw_amt_inr=document.forms[0].raw_amt_inr.value;
	var fgsa_no=document.forms[0].fgsa_no.value;
	var fgsa_rev_no=document.forms[0].fgsa_rev_no.value;
	var sn_no=document.forms[0].sn_no.value;
	var sn_rev_no=document.forms[0].sn_rev_no.value;
	var contract_type=document.forms[0].contract_type.value;
	var customer_plant_seq_no=document.forms[0].customer_plant_seq_no.value;
	var bill_period_start_date=document.forms[0].bill_period_start_date.value;
	var hlpl_Inv_Seq_No=document.forms[0].hlpl_Inv_Seq_No.value;
	var financial_Year=document.forms[0].financial_Year.value;
	var bill_period_end_date=document.forms[0].bill_period_end_date.value;
	var invoice_date=document.forms[0].invoice_date.value;
	var mapping_id=document.forms[0].mapping_id.value;
	var	exch_rate_val=document.forms[0].exch_rate_val.value;
	var activity=document.forms[0].activity.value;
	var save_type="sbc_adv";
	
	
	var flag=false;
	var msg="Please Check Following details:\n";
	if(adv_inv_no=='')
	{
		flag=true;
		msg+="Advance Invoice No. \n";
		
	}
	if(adv_inv_dt=='')
	{
		flag=true;
		msg+="Advance invoice Dt \n";
		
	}
	//alert("-----"+adjustamt);
	//alert(flag);
	if(adjustamt=='' || adjustamt==' ')
	{
		//alert("In eles");
		flag=true;
		msg+="Tax Amount to be Adjusted \n";
		
	}
	adjustamt=round(adjustamt,2);
	if(flag==false)
	{
				if (window.XMLHttpRequest)
				{
					request22 = new XMLHttpRequest();
				} 
				else if (window.ActiveXObject)
				{
					request22 = new ActiveXObject("Microsoft.XMLHTTP");
				}
				if(request22)
			   	{
			  		 var url="../advance_payment/frm_save_advance_data_ajax.jsp?customer_cd="+customer_cd+"&save_type="+save_type+"&raw_amt_usd="+raw_amt_usd+"&raw_amt_inr="+raw_amt_inr+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_seq_no="+customer_plant_seq_no+"&hlpl_Inv_Seq_No="+hlpl_Inv_Seq_No+"&financial_Year="+financial_Year+"&invoice_date="+invoice_date+"&mapping_id="+mapping_id+"&bill_period_end_date="+bill_period_end_date+"&adv_inv_dt="+adv_inv_dt+"&currency="+currency+"&adjustamt="+adjustamt+"&priceINR2="+priceINR2+"&adjustsign="+adjustsign+"&exch_rate_val="+exch_rate_val+"&activity="+activity;
			  		
					 //alert(url);
			  		 request22.open("POST",url);
			  		 request22.onreadystatechange = function() 
			    	 {
			    		 if(request22.readyState==4)
			     		 {	
			     		 		document.getElementById("submited_text_sbc").innerHTML="<b>Advance SBC Adjustment Submitted..</b>";
				  	  	 }
					 }
			  		 request22.send(null);
			     }
				 else 
				 {
				    alert("Your browser doesn't support AJax !"); 
				 }
	}
	else
	{
		alert(msg);
	}	
}

function saveadvance_kkc_data()
{
	//alert(document.forms[0].advance_sbc_submit.value);	
	document.forms[0].Adv_submitted_flag.value='Y';
	var adv_inv_no=document.forms[0].adv_inv_no.value;
	var adv_inv_dt=document.forms[0].adv_inv_dt.value;
	var currency=document.forms[0].KKC_ADV_ADJUST_CUR.value;
	//var recevial=document.forms[0].recevial.value;
	var adjustamt=document.forms[0].adjustamt_kkc.value;
	var priceINR2=document.forms[0].gross_amt_inr_adjusted_kkc.value;
	var adjustsign=document.forms[0].adjustsign_kkc.value;
	//adjustamt=round(adjustamt,2);
	//alert(priceINR2);
	var customer_cd=document.forms[0].customer_cd.value;
	var raw_amt_usd=document.forms[0].raw_amt_usd.value;
	var raw_amt_inr=document.forms[0].raw_amt_inr.value;
	var fgsa_no=document.forms[0].fgsa_no.value;
	var fgsa_rev_no=document.forms[0].fgsa_rev_no.value;
	var sn_no=document.forms[0].sn_no.value;
	var sn_rev_no=document.forms[0].sn_rev_no.value;
	var contract_type=document.forms[0].contract_type.value;
	var customer_plant_seq_no=document.forms[0].customer_plant_seq_no.value;
	var bill_period_start_date=document.forms[0].bill_period_start_date.value;
	var hlpl_Inv_Seq_No=document.forms[0].hlpl_Inv_Seq_No.value;
	var financial_Year=document.forms[0].financial_Year.value;
	var bill_period_end_date=document.forms[0].bill_period_end_date.value;
	var invoice_date=document.forms[0].invoice_date.value;
	var mapping_id=document.forms[0].mapping_id.value;
	var	exch_rate_val=document.forms[0].exch_rate_val.value;
	var activity=document.forms[0].activity.value;
	var save_type="kkc_adv";
	
	
	var flag=false;
	var msg="Please Check Following details:\n";
	if(adv_inv_no=='')
	{
		flag=true;
		msg+="Advance Invoice No. \n";
		
	}
	if(adv_inv_dt=='')
	{
		flag=true;
		msg+="Advance invoice Dt \n";
		
	}
	//alert("-----"+adjustamt);
	//alert(flag);
	if(adjustamt=='' || adjustamt==' ')
	{
		//alert("In eles");
		flag=true;
		msg+="Tax Amount to be Adjusted \n";
		
	}
	adjustamt=round(adjustamt,2);
	if(flag==false)
	{
				if (window.XMLHttpRequest)
				{
					request22 = new XMLHttpRequest();
				} 
				else if (window.ActiveXObject)
				{
					request22 = new ActiveXObject("Microsoft.XMLHTTP");
				}
				if(request22)
			   	{
			  		 var url="../advance_payment/frm_save_advance_data_ajax.jsp?customer_cd="+customer_cd+"&save_type="+save_type+"&raw_amt_usd="+raw_amt_usd+"&raw_amt_inr="+raw_amt_inr+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_seq_no="+customer_plant_seq_no+"&hlpl_Inv_Seq_No="+hlpl_Inv_Seq_No+"&financial_Year="+financial_Year+"&invoice_date="+invoice_date+"&mapping_id="+mapping_id+"&bill_period_end_date="+bill_period_end_date+"&adv_inv_dt="+adv_inv_dt+"&currency="+currency+"&adjustamt="+adjustamt+"&priceINR2="+priceINR2+"&adjustsign="+adjustsign+"&exch_rate_val="+exch_rate_val+"&activity="+activity;
			  		
					 //alert(url);
			  		 request22.open("POST",url);
			  		 request22.onreadystatechange = function() 
			    	 {
			    		 if(request22.readyState==4)
			     		 {	
			     		 		document.getElementById("submited_text_kkc").innerHTML="<b>Advance KKC Adjustment Submitted..</b>";
				  	  	 }
					 }
			  		 request22.send(null);
			     }
				 else 
				 {
				    alert("Your browser doesn't support AJax !"); 
				 }
	}
	else
	{
		alert(msg);
	}	
}

function displaydetailsmodify()
	{
		
		if(document.forms[0].activity.value=='update')
		{
		val=document.forms[0].advflg.value;
		var mfgsa_no=document.forms[0].fgsa_no.value;
		var msn_no=document.forms[0].sn_no.value;
		var mAdjust_amt_mst=document.forms[0].Adjust_amt_mst.value;
		var mAdjust_cur_mst=document.forms[0].Adjust_cur_mst.value;
		
		var mcontract_type=document.forms[0].contract_type.value;
		var mhlpl_inv_seq_no2=document.forms[0].hlpl_inv_seq_no2.value;
		var minv_financial_year=document.forms[0].inv_financial_year.value;
		var mcustomer_cd=document.forms[0].customer_cd_tax.value;
		var mcustomer_plant_seq_no=document.forms[0].customer_plant_seq_no.value;
		var mbill_period_end_dt=document.forms[0].inv_dt.value;
		var mexchangerateselected=document.forms[0].exch_rate_val.value;
		var mfgsa_rev_no=document.forms[0].fgsa_rev_no.value;
		var mapping_id=document.forms[0].mapping_id.value;
		var payment_md=document.forms[0].pay_type1.value; //HS20160722
		
		var date=document.forms[0].exch_rate_dt.value;
	//	document.forms[0].advflg.value=val; //HS20160903
		//alert(document.forms[0].discount_flag_apply.value);
		
			if (window.XMLHttpRequest)
			{
				request22 = new XMLHttpRequest();
			} 
			else if (window.ActiveXObject)
			{
				request22 = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			if(request22)
		   	{
		  		 var url="../advance_payment/frm_invoice_adjustment_modify_ajax.jsp?mcontract_type="+mcontract_type+"&mhlpl_inv_seq_no2="+mhlpl_inv_seq_no2+"&minv_financial_year="+minv_financial_year+"&mcustomer_cd="+mcustomer_cd+"&mcustomer_plant_seq_no="+mcustomer_plant_seq_no+"&mbill_period_end_dt="+mbill_period_end_dt+"&date="+date+"&mexchangerateselected="+mexchangerateselected+"&mfgsa_no="+mfgsa_no+"&msn_no="+msn_no+"&mAdjust_amt_mst="+mAdjust_amt_mst+"&mAdjust_cur_mst="+mAdjust_cur_mst+"&fgsa_rev_no="+mfgsa_rev_no+"&mapping_id="+mapping_id+"&payment_md="+payment_md;
				//	alert(url);
		  		 request22.open("POST",url);
		  		 request22.onreadystatechange = function() 
		    	 {
		    		 if(request22.readyState==4)
		     		 { 
		    			 if(val=='BA'){
		    				advId="adjustmentmodify";
		    				advId1="afteradjustmentmodify";
		    			}else{
		    				advId="afteradjustmentmodify";
		    				advId1="adjustmentmodify";
		    			}
	    				var a=document.getElementById(advId).innerHTML=request22.responseText;  
						document.getElementById(advId).style.visibility="visible";
						document.getElementById(advId1).innerHTML="";
						document.getElementById(advId1).style.visibility="hidden";
		    			 
		    			 if(document.forms[0].modifyadjrecvflaghid.value=='Y')
		    			 {
		    			 	document.forms[0].recevial.value='0';
		    			 }
		    			 else
		    			 {
		    			 	document.forms[0].recevial.value='1';
		    			 }
		    			 
		    			 if(document.forms[0].modifyadjcurrencyhid.value=='U')
		    			 {
		    			 	document.forms[0].currency.value='0';
		    			 }
		    			 else if(document.forms[0].modifyadjcurrencyhid.value=='I')
		    			 {
		    			 	document.forms[0].currency.value='1';
		    			 }
		    			 //alert(document.forms[0].modifyadjsignhid.value);
		    			 document.forms[0].adjustsign.value=document.forms[0].modifyadjsignhid.value;
		    			 
		    			  var taxcd=document.forms[0].taxstructcd.value;
		    			   var net_amt_inr1=document.getElementById("invoice_inr_amt_adjusted").value;
		    			 
		    			  
		    			  //alert("HERE0");
		    			  //Added for tax calculation update on completion of ajax event
			    			 	var len=document.getElementById("cust_tax_size").value;
								//alert("VALUE--"+document.forms[0].cust_tax_amt1.value);
// 								alert("len.. "+len);
							 	for(var i=0;i<len;i++)
							 	{
					 					var temp="cust_tax_amt"+i;
					 					var temptax_amt=document.getElementById(temp).value;  
// 					 					alert("taxamt---"+temptax_amt);
					 					var tmp="tax_amts"+i;
// 					 					document.getElementById(tmp).value=temptax_amt;
								 }
								//alert("here"+net_amt_inr1);
								 if(document.forms[0].TAX_ADV_ADJUST_FLAG_MST.value=='Y')
								 {
					    			 document.forms[0].MODIFY_TAX_ADV_ADJUSTMENT.value=round(document.forms[0].MODIFY_TAX_ADV_ADJUSTMENT1.value,2);
									 document.forms[0].MODIFY_TAX_INV_AMT_INR.value=round(document.forms[0].MODIFY_TAX_INV_AMT_INR1.value,2);									 
									 document.forms[0].Adjust_total_avail_bal_tax.value=round(document.forms[0].Adjust_total_avail_bal_tax1.value,2);
		 
					    			 displaytaxadjustmentdetails(); //alert("1");
// 					    			 adjustmamountforadjust_tax(); //alert("2");
// 					    			 adjustmamountforadjust_sbc();//alert("3");
// 					    			 adjustmamountforadjust_kkc();//alert("4");
									 document.getElementById("adjustamt_tax").disabled=false;
									 document.forms[0].adjustsign_tax.disabled=false;
									 document.getElementById("adjustamt_tax").className="";
						 		}
								 //caluclate_tax_details_ajax(document.forms[0].double_final_gross_amt_inr.value,taxcd);
								document.getElementById("net_amt_inr").value=net_amt_inr1;
 									var flag=false;
 									if(document.forms[0].call_on_load_hid.value=='call')
 									{	//alert("INSIDE");
 										flag=true;
 										displaysalepricedetails('L');
 										document.forms[0].call_on_load_hid.value='';
 									}
 									//alert(document.forms[0].discount_flag_apply.value);
 									if(flag==true)
 									{
 										if( document.forms[0].call_on_load_hid2.value=="call"){
 										if(document.forms[0].discount_flag_apply!=undefined){ 
	 									if(document.forms[0].discount_flag_apply.value=='Y'){
	 									if(document.forms[0].modifyadjflag.value=='Y'){
	 										adjustmamountforadjust();
	 									}}}
	 									document.forms[0].call_on_load_hid2.value='';
	 									}
 									}
 									else
 									{	if(document.forms[0].discount_flag_apply!=undefined){ 
 										if(document.forms[0].discount_flag_apply.value=='Y'){
 										if(document.forms[0].modifyadjflag.value=='Y'){
	 										adjustmamountforadjust();}
	 									}
	 									else
	 									{
	 									if(document.forms[0].modifyadjflag.value=='Y'){
	 									adjustmamountforadjust();
	 									}}}
	 									else
	 									{
	 									if(document.forms[0].modifyadjflag.value=='Y'){
	 									adjustmamountforadjust();}}
 									}
 									replaceString();	
		     		 }
				 }
		  		 request22.send();
		  		if(document.forms[0].adjustdetails[0].checked){ //HS20160903
					actualadvamt=document.forms[0].gross_amt_inr.value;
					document.forms[0].double_final_gross_amt_inr.value=round(actualadvamt,2);
					var taxratesize=document.getElementById('taxratesize').value;
					var taxadjamt=0;
					for(l=0;l<taxratesize;l++){
						var taxrate=document.getElementById('taxrate'+l).value;
						document.forms[0].tax_amts[l].value=round((actualadvamt*taxrate/100),2);
						taxadjamt+=parseFloat(document.forms[0].tax_amts[l].value);
					}
					//alert("---"+taxadjamt);
					document.forms[0].total_tax.value=round(taxadjamt,2);
					document.forms[0].net_amt_inr.value=""+round(parseFloat(parseFloat(actualadvamt)+round(taxadjamt,2)),2);
				}
		     }
			 else 
			 {
			    document.getElementById("adjustmentmodify").innerHTML="";
			    document.getElementById("adjustmentmodify").style.visibility="hidden";
			    document.getElementById("afteradjustmentmodify").innerHTML="";
			    document.getElementById("afteradjustmentmodify").style.visibility="hidden";
			    alert("Your browser doesn't support AJax !"); 
			 }
			
			 
			 
		}
		else if(document.forms[0].activity.value=='insert' && document.forms[0].Adv_submitted_flag.value=='Y')
		{//alert();
		  		var mfgsa_no=document.forms[0].fgsa_no.value;
		var msn_no=document.forms[0].sn_no.value;
		var mAdjust_amt_mst=document.forms[0].Adjust_amt_mst.value;
		var mAdjust_cur_mst=document.forms[0].Adjust_cur_mst.value;
		
		var mcontract_type=document.forms[0].contract_type.value;
		var mhlpl_inv_seq_no2=document.forms[0].hlpl_inv_seq_no.value;
		var minv_financial_year=document.forms[0].financial_Year.value;
		var mcustomer_cd=document.forms[0].customer_cd_tax.value;
		var mcustomer_plant_seq_no=document.forms[0].customer_plant_seq_no.value;
		var mbill_period_end_dt=document.forms[0].inv_dt.value;
		var mexchangerateselected=document.forms[0].exch_rate_val.value;
		var mfgsa_rev_no=document.forms[0].fgsa_rev_no.value;
		var mapping_id=document.forms[0].mapping_id.value;
		
		//alert(minv_financial_year);
		var date=document.forms[0].exch_rate_dt.value;
		//alert(document.forms[0].discount_flag_apply.value);
		
			if (window.XMLHttpRequest)
			{
				request22 = new XMLHttpRequest();
			} 
			else if (window.ActiveXObject)
			{
				request22 = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			if(request22)
		   	{
		  		 var url="../advance_payment/frm_invoice_adjustment_modify_ajax.jsp?mcontract_type="+mcontract_type+"&activity="+'insert'+"&mhlpl_inv_seq_no2="+mhlpl_inv_seq_no2+"&minv_financial_year="+minv_financial_year+"&mcustomer_cd="+mcustomer_cd+"&mcustomer_plant_seq_no="+mcustomer_plant_seq_no+"&mbill_period_end_dt="+mbill_period_end_dt+"&date="+date+"&mexchangerateselected="+mexchangerateselected+"&mfgsa_no="+mfgsa_no+"&msn_no="+msn_no+"&mAdjust_amt_mst="+mAdjust_amt_mst+"&mAdjust_cur_mst="+mAdjust_cur_mst+"&fgsa_rev_no="+mfgsa_rev_no+"&mapping_id="+mapping_id;
		  		 request22.open("POST",url);
		  		 request22.onreadystatechange = function() 
		    	 {
		    		 if(request22.readyState==4)
		     		 {
		     			var a=document.getElementById("adjustment").innerHTML=request22.responseText;    
						document.getElementById("adjustment").style.visibility="visible";
		    			
		    			if(document.forms[0].TAX_ADV_ADJUST_FLAG_MST.value=='Y'){
		    			var inv_date=document.forms[0].exch_rate_dt.value;
	
						document.forms[0].MODIFY_TAX_ADV_ADJUSTMENT.value=round(document.forms[0].MODIFY_TAX_ADV_ADJUSTMENT1.value,2);
						document.forms[0].MODIFY_TAX_INV_AMT_INR.value=round(document.forms[0].MODIFY_TAX_INV_AMT_INR1.value,2);
						document.forms[0].Adjust_total_avail_bal_tax.value=round(document.forms[0].Adjust_total_avail_bal_tax1.value,2);
		    			
		    			
						 displaytaxadjustmentdetails();
						 adjustmamountforadjust_tax();
						 document.getElementById("adjustamt_tax").disabled=false;
						 document.forms[0].adjustsign_tax.disabled=false;
						 document.getElementById("adjustamt_tax").className="";
						 }
		    			
		    			// alert(a);
		    			 document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].gross_amt_inr.value,2);
		    			 if(document.forms[0].modifyadjrecvflaghid.value=='Y')
		    			 {
		    			 	document.forms[0].recevial.value='0';
		    			 }
		    			 else
		    			 {
		    			 	document.forms[0].recevial.value='1';
		    			 }
		    			 
		    			 if(document.forms[0].modifyadjcurrencyhid.value=='U')
		    			 {
		    			 	document.forms[0].currency.value='0';
		    			 }
		    			 else if(document.forms[0].modifyadjcurrencyhid.value=='I')
		    			 {
		    			 	document.forms[0].currency.value='1';
		    			 }
		    			 //alert(document.forms[0].modifyadjsignhid.value);
		    			 document.forms[0].adjustsign.value=document.forms[0].modifyadjsignhid.value;
		    			  
		    			  //Added for tax calculation update on completion of ajax event
			    			 	var len=document.getElementById("cust_tax_size").value;
								//alert("VALUE--"+document.forms[0].cust_tax_amt1.value);
							 	for(var i=0;i<len;i++)
							 	{
					 					var temp="cust_tax_amt"+i;
					 					var temptax_amt=document.getElementById(temp).value;  
					 				//	alert("taxamt---"+temptax_amt);
					 					var tmp="tax_amts"+i;
					 					document.getElementById(tmp).value=temptax_amt;
								 }
								var net_amt_inr=document.getElementById("invoice_inr_amt_adjusted").value;
								document.getElementById("net_amt_inr").value=net_amt_inr;
 								document.getElementById("modifytaxdetails").innerHTML='';
 									adjustmamountforadjust();
 								
 								
 									var flag=false;
 									if(document.forms[0].call_on_load_hid.value=='call')
 									{	//alert("INSIDE");
 										flag=true;
 										displaysalepricedetails('L');
 										document.forms[0].call_on_load_hid.value='';
 									}
 									//alert(document.forms[0].discount_flag_apply.value);
 									if(flag==true)
 									{
 										if( document.forms[0].call_on_load_hid2.value=="call"){
 										if(document.forms[0].discount_flag_apply!=undefined){ 
	 									if(document.forms[0].discount_flag_apply.value=='Y'){
	 									if(document.forms[0].modifyadjflag.value=='Y'){
	 										adjustmamountforadjust();
	 									}}}
	 									document.forms[0].call_on_load_hid2.value='';
	 									}
 									}
 									else
 									{	if(document.forms[0].discount_flag_apply!=undefined){ 
 										if(document.forms[0].discount_flag_apply.value=='Y'){
 										if(document.forms[0].modifyadjflag.value=='Y'){
	 										adjustmamountforadjust();}
	 									}
	 									else
	 									{
	 									if(document.forms[0].modifyadjflag.value=='Y'){
	 									adjustmamountforadjust();
	 									}}}
	 									else
	 									{
	 									if(document.forms[0].modifyadjflag.value=='Y'){
	 									adjustmamountforadjust();}}
 									}
 									replaceString();	
		     		 }
				 }
		  		 request22.send();
		     }
			 else 
			 {
			    document.getElementById("adjustment").innerHTML="";
			    document.getElementById("adjustment").style.visibility="hidden";
			    alert("Your browser doesn't support AJax !"); 
			 }
			 		
		}
		
	}
	
	
function hidedetailsmodify()
	{
		document.getElementById("adjustmentmodify").innerHTML="";
		document.getElementById("afteradjustmentmodify").innerHTML="";
		document.forms[0].advflg.value="NA";
		if(document.forms[0].sale_price_inr_check==undefined)
		{
			document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].gross_amt_inr.value,2);
		}
		else
		{
			if(document.forms[0].sale_price_inr_check.checked)
			{
				document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].grosssalespriceINR.value,2);
			}
			else
			{
				document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].gross_amt_inr.value,2);
			}
		}
		
		document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].double_final_gross_amt_inr.value,2);
			
		var grossamt=document.forms[0].gross_amt_inr.value;
		var taxcd=document.forms[0].taxstructcd.value;
		
		if(document.forms[0].TAX_ADV_ADJUST_FLAG_MST.value=='Y'){
		
		document.getElementById("adjustamt_tax").disabled=true;
		//document.getElementById("adjustamt_tax").value='';
		document.forms[0].adjustsign_tax.disabled=true;
		document.getElementById("adjustamt_tax").className="mkrdly";
		//document.forms[0].gross_amt_inr_adjusted_tax.value='';
		hideadjustmenttaxdetails();
		}
		//alert(grossamt);
		caluclate_tax_details_ajax(grossamt,taxcd);
		//alert(document.getElementById("net_amt_inr").value);
		//tax calculation is done befor clear because it does not get modify tax details in calculation
		document.getElementById("adjustmentmodify").innerHTML="";
	}
	
	function hidedetails()
	{
		document.getElementById("adjustment").innerHTML="";
		document.getElementById("afteradjustment").innerHTML="";
		document.forms[0].advflg.value="NA";
		
		if(document.forms[0].sale_price_inr_check==undefined)
		{
			document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].gross_amt_inr.value,2);
		}
		else
		{
			if(document.forms[0].sale_price_inr_check.checked)
			{
				document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].grosssalespriceINR.value,2);
			}
			else
			{
				document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].gross_amt_inr.value,2);
			}
		}
		
		var grossamt=document.forms[0].double_final_gross_amt_inr.value;
		var taxcd=document.forms[0].taxstructcd.value;
		if(document.forms[0].TAX_ADV_ADJUST_FLAG_MST.value=='Y'){
		document.getElementById("adjustamt_tax").disabled=true;
		document.getElementById("adjustamt_tax").value='';
		document.forms[0].adjustsign_tax.disabled=true;
		document.getElementById("adjustamt_tax").className="mkrdly";
		document.forms[0].gross_amt_inr_adjusted_tax.value='';
		hideadjustmenttaxdetails();
		}
		caluclate_tax_details_ajax(grossamt,taxcd);

	}	

</script>
</head>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.sales_invoice.DataBean_Sales_InvoiceV2" id="salesInv" scope="page"/>   
<jsp:useBean class="com.seipl.hazira.dlng.advance_payment.DataBean_Advance_payment" id="adv" scope="page"/>   
<%	util.init();
		
	String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
	String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
	String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");
	
	String customer_abbr = request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
	String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
	String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
	String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
	String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
	String sn_rev_no = request.getParameter("sn_rev_no")==null?"":request.getParameter("sn_rev_no");
	String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
	String customer_plant_nm = request.getParameter("customer_plant_nm")==null?"":request.getParameter("customer_plant_nm");
	String customer_plant_seq_no = request.getParameter("customer_plant_seq_no")==null?"":request.getParameter("customer_plant_seq_no");
	String bill_period_start_dt = request.getParameter("bill_period_start_dt")==null?"":request.getParameter("bill_period_start_dt");
	String bill_period_end_dt = request.getParameter("bill_period_end_dt")==null?"":request.getParameter("bill_period_end_dt");
	String due_dt = request.getParameter("due_dt")==null?"":request.getParameter("due_dt");
	String contact_person = request.getParameter("contact_person")==null?"0":request.getParameter("contact_person");
	String month = request.getParameter("month")==null?"0":request.getParameter("month");
	String year = request.getParameter("year")==null?"0":request.getParameter("year");
	String bill_cycle = request.getParameter("bill_cycle")==null?"0":request.getParameter("bill_cycle");
	String exchg_rate_cd = request.getParameter("exchg_rate_cd")==null?"0":request.getParameter("exchg_rate_cd");
	String exchg_rate_cal_method = request.getParameter("exchg_rate_cal_method")==null?"0":request.getParameter("exchg_rate_cal_method");
	String invoice_date = request.getParameter("invoice_date")==null?bill_period_end_dt:request.getParameter("invoice_date");
	String particular_date = request.getParameter("particular_date")==null?bill_period_end_dt:request.getParameter("particular_date");
	String rbi_ref_cd = request.getParameter("rbi_ref_cd")==null?"1":request.getParameter("rbi_ref_cd");
	String sbi_tt_selling_cd = request.getParameter("sbi_tt_selling_cd")==null?"2":request.getParameter("sbi_tt_selling_cd");
	String sbi_tt_buying_cd = request.getParameter("sbi_tt_buying_cd")==null?"3":request.getParameter("sbi_tt_buying_cd");
	String sbi_avg_tt_selling_buying_cd = request.getParameter("sbi_avg_tt_selling_buying_cd")==null?"6":request.getParameter("sbi_avg_tt_selling_buying_cd");
	
	String gross_amt_usd = request.getParameter("gross_amt_usd")==null?"":request.getParameter("gross_amt_usd");
	String gross_amt_inr = request.getParameter("gross_amt_inr")==null?"":request.getParameter("gross_amt_inr");
	String gross_amt_usd2 = request.getParameter("gross_amt_usd2")==null?"":request.getParameter("gross_amt_usd2");
	String gross_amt_inr2 = request.getParameter("gross_amt_inr2")==null?"":request.getParameter("gross_amt_inr2");
	
	String raw_amt_usd = request.getParameter("raw_amt_usd")==null?gross_amt_usd:request.getParameter("raw_amt_usd");
	String raw_amt_inr = request.getParameter("raw_amt_inr")==null?gross_amt_inr:request.getParameter("raw_amt_inr");
	
	String exch_rate_val = request.getParameter("exch_rate_val")==null?"":request.getParameter("exch_rate_val");
	String exchg_rate_index = request.getParameter("exchg_rate_index")==null?"-1":request.getParameter("exchg_rate_index");
	String mapping_id = request.getParameter("mapping_id")==null?"-1":request.getParameter("mapping_id");
		
	String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
	String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String check_permission = request.getParameter("check_permission")==null?"N":request.getParameter("check_permission");
	String authorize_permission = request.getParameter("authorize_permission")==null?"N":request.getParameter("authorize_permission");
	String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
	String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
	
	String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
	String hlpl_invoice_sequence_no = request.getParameter("hlpl_invoice_sequence_no")==null?"":request.getParameter("hlpl_invoice_sequence_no");
	String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"":request.getParameter("hlpl_inv_seq_no");
	String hlpl_inv_seq_no2 = request.getParameter("hlpl_inv_seq_no2")==null?"":request.getParameter("hlpl_inv_seq_no2");
	String inv_financial_year = request.getParameter("inv_financial_year")==null?"":request.getParameter("inv_financial_year");
	String page_refresh_flag = request.getParameter("page_refresh_flag")==null?"N":request.getParameter("page_refresh_flag");
	String Adv_submitted_flag = request.getParameter("Adv_submitted_flag")==null?"N":request.getParameter("Adv_submitted_flag");
	
	String offspec_rate = request.getParameter("offspec_rate")==null?"":request.getParameter("offspec_rate");
	String flag = request.getParameter("flag")==null?"F":request.getParameter("flag");
	String rbi_ref_flag = "";
	String sbi_tt_selling_flag = "";
	String sbi_tt_buying_flag = "";
	String sbi_avg_tt_selling_buying_flag = "";
	
	String adjust_flag=request.getParameter("adjust_flag")==null?"N":request.getParameter("adjust_flag");
	String discount_flag_apply=request.getParameter("discount_flag_apply")==null?"N":request.getParameter("discount_flag_apply");
	String enrtypoint=request.getParameter("enrtypoint")==null?"load":request.getParameter("enrtypoint");
	String adv_inv_no=request.getParameter("adv_inv_no")==null?"":request.getParameter("adv_inv_no");
	String adv_inv_dt=request.getParameter("adv_inv_dt")==null?"":request.getParameter("adv_inv_dt");
	String inv_cur_flag="N";
	
	if(exchg_rate_index.equals("-1"))
	{		
		if(exchg_rate_cd.equals(rbi_ref_cd))
		{
			rbi_ref_flag = "checked";
			exchg_rate_index = "5";
			sbi_tt_selling_flag = "";
			sbi_tt_buying_flag = "";
			sbi_avg_tt_selling_buying_flag = "";
		}
		else if(exchg_rate_cd.equals(sbi_tt_selling_cd))
		{
			sbi_tt_selling_flag = "checked";
			exchg_rate_index = "6";
			rbi_ref_flag = "";
			sbi_tt_buying_flag = "";
			sbi_avg_tt_selling_buying_flag = "";
		}
		else if(exchg_rate_cd.equals(sbi_tt_buying_cd))
		{
			sbi_tt_buying_flag = "checked";
			exchg_rate_index = "7";
			rbi_ref_flag = "";
			sbi_tt_selling_flag = "";
			sbi_avg_tt_selling_buying_flag = "";
		}
		else if(exchg_rate_cd.equals(sbi_avg_tt_selling_buying_cd))
		{
			sbi_avg_tt_selling_buying_flag = "checked";
			exchg_rate_index = "4";
			rbi_ref_flag = "";
			sbi_tt_selling_flag = "";
			sbi_tt_buying_flag = "";
		}
		else
		{
			rbi_ref_flag = "";
			sbi_tt_selling_flag = "";
			sbi_tt_buying_flag = "";
			sbi_avg_tt_selling_buying_flag = "";
		}
	}
	else
	{
		rbi_ref_flag = "";
		sbi_tt_selling_flag = "";
		sbi_tt_buying_flag = "";
		sbi_avg_tt_selling_buying_flag = "";
	}
	
	int exchg_rate_ind = Integer.parseInt(exchg_rate_index);
	
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	
	if(page_refresh_flag.equals("N") && activity.equals("update"))
	{
		gross_amt_usd = gross_amt_usd2;
		gross_amt_inr = gross_amt_inr2;
		raw_amt_inr=gross_amt_inr2;
		raw_amt_usd=gross_amt_usd2;
	}
	
	String financial_year = "";
	if(Integer.parseInt(month)>3)
	{
		financial_year = ""+year+"-"+(Integer.parseInt(year)+1);		
	}
	else
	{
		financial_year = ""+(Integer.parseInt(year)-1)+"-"+year;
	}
	salesInv.setCallFlag("SALES_INVOICE_DETAIL");
	salesInv.setCustomerCd(customer_cd);
	salesInv.setFgsaNo(fgsa_no);
	salesInv.setFgsaRevNo(fgsa_rev_no);
	salesInv.setSnNo(sn_no);
	salesInv.setSnRevNo(sn_rev_no);
	salesInv.setContractType(contract_type);
	salesInv.setCustomerPlantSeqNo(customer_plant_seq_no);
	salesInv.setBillPeriodStartDt(bill_period_start_dt);
	salesInv.setBillPeriodEndDt(bill_period_end_dt);
	salesInv.setExchgRateCd(exchg_rate_cd);
	salesInv.setInvoiceDate(invoice_date);
	salesInv.setParticularDate(particular_date);
	salesInv.setExchgRateCalMethod(exchg_rate_cal_method);
	salesInv.setRbiRefCd(rbi_ref_cd);
	salesInv.setSbiTtSellingCd(sbi_tt_selling_cd);
	salesInv.setSbiTtBuyingCd(sbi_tt_buying_cd);
	salesInv.setSbiAvgTtSellingBuyingCd(sbi_avg_tt_selling_buying_cd);
	salesInv.setCustomer_Invoice_Gross_Amt_USD(gross_amt_usd);
	salesInv.setCustomer_Invoice_Gross_Amt_INR(gross_amt_inr);
	salesInv.setHlplInvoiceNo(hlpl_inv_seq_no2);
	salesInv.setInvFinancialYear(inv_financial_year);
	salesInv.setActivity(activity);
	salesInv.setPage_refresh_flag(page_refresh_flag);
	salesInv.setCustomer_inv_mapping_id(mapping_id); //ADDED FOR LTCORA AND CN
	salesInv.setRefresh_flag(page_refresh_flag);
	salesInv.init();
	
	String GST_INVOICE_SEQ_NO = salesInv.getGST_INVOICE_SEQ_NO();
	Vector invoice_Customer_Contact_Cd = salesInv.getInvoice_Customer_Contact_Cd();
	Vector invoice_Customer_Contact_Nm = salesInv.getInvoice_Customer_Contact_Nm();
	String total_Invoice_Qty = salesInv.getTotal_Invoice_Qty();
	String invoice_Sales_Rate = salesInv.getInvoice_Sales_Rate();
	invoice_Sales_Rate="0.8";
	String invoice_Previous_Available_Exchg_Date = salesInv.getInvoice_Previous_Available_Exchg_Date();
	String invoice_Exchg_Rate_RBI_Ref = salesInv.getInvoice_Exchg_Rate_RBI_Ref();
	String invoice_Exchg_Rate_TT_Selling = salesInv.getInvoice_Exchg_Rate_TT_Selling();
	String invoice_Exchg_Rate_TT_Buying = salesInv.getInvoice_Exchg_Rate_TT_Buying();
	String invoice_Exchg_Rate_Avg_TT_Buy_Sell = salesInv.getInvoice_Exchg_Rate_Avg_TT_Buy_Sell();
	String invoice_Previous_Exchg_Rate_RBI_Ref = salesInv.getInvoice_Previous_Exchg_Rate_RBI_Ref();
	String invoice_Previous_Exchg_Rate_TT_Selling = salesInv.getInvoice_Previous_Exchg_Rate_TT_Selling();
	String invoice_Previous_Exchg_Rate_TT_Buying = salesInv.getInvoice_Previous_Exchg_Rate_TT_Buying();
	String invoice_Previous_Exchg_Rate_Avg_TT_Buy_Sell = salesInv.getInvoice_Previous_Exchg_Rate_Avg_TT_Buy_Sell();
	invoice_Previous_Exchg_Rate_Avg_TT_Buy_Sell="66.275";
	String invoice_Particular_Day_Exchg_Rate_RBI_Ref = salesInv.getInvoice_Particular_Day_Exchg_Rate_RBI_Ref();
	String invoice_Particular_Day_Exchg_Rate_TT_Selling = salesInv.getInvoice_Particular_Day_Exchg_Rate_TT_Selling();
	String invoice_Particular_Day_Exchg_Rate_TT_Buying = salesInv.getInvoice_Particular_Day_Exchg_Rate_TT_Buying();
	String invoice_Particular_Day_Exchg_Rate_Avg_TT_Buy_Sell = salesInv.getInvoice_Particular_Day_Exchg_Rate_Avg_TT_Buy_Sell();
	String invoice_Exchg_Rate_Note = salesInv.getInvoice_Exchg_Rate_Note();
	
	Vector invoice_Period_Dates = salesInv.getInvoice_Period_Dates();
	Vector invoice_Period_Exchg_Rate_RBI_Ref = salesInv.getInvoice_Period_Exchg_Rate_RBI_Ref();
	Vector invoice_Period_Exchg_Rate_TT_Selling = salesInv.getInvoice_Period_Exchg_Rate_TT_Selling();
	Vector invoice_Period_Exchg_Rate_TT_Buying = salesInv.getInvoice_Period_Exchg_Rate_TT_Buying();
	Vector invoice_Period_Exchg_Rate_Avg_TT_Buy_Sell = salesInv.getInvoice_Period_Exchg_Rate_Avg_TT_Buy_Sell();
	Vector daily_Invoice_QTY = salesInv.getDaily_Invoice_QTY();
	String tax_Structure_Dtl = salesInv.getTax_Structure_Dtl();
	String pay_mode=salesInv.getPayment_type()==null?"AP":salesInv.getPayment_type();
	String advanceAdjFlg=salesInv.getAdvancedFlg()==null?"NA":salesInv.getAdvancedFlg(); //HS20160903
	//Following (6) Getter Methods Has Been Introduced By Samik Shah On 20th January, 2011 ...  
	Vector customer_Invoice_Tax_Code = salesInv.getCustomer_Invoice_Tax_Code();
	Vector customer_Invoice_Tax_Abbr = salesInv.getCustomer_Invoice_Tax_Abbr();
	Vector customer_Invoice_Tax_Name = salesInv.getCustomer_Invoice_Tax_Name();
	Vector customer_Invoice_Tax_Amt = salesInv.getCustomer_Invoice_Tax_Amt();
	Vector customer_Invoice_Tax_Rate = salesInv.getCustomer_Invoice_Tax_Rate();
	String customer_Invoice_Net_Amt_INR = salesInv.getCustomer_Invoice_Net_Amt_INR();
	String invoice_total_tax=salesInv.getInvoice_total_tax();
	//Following (2) String Getter Methods Has Been Defined By Samik Shah On 8th February, 2011 ...
	String accepted_Offspec_Qty = salesInv.getAccepted_Offspec_Qty();
	String accepted_FM_Qty = salesInv.getAccepted_FM_Qty();
	//Following (2) String Getter Methods Has Been Defined By Samik Shah On 12th May, 2011 ...
	String boe_no = salesInv.getBoe_no();
	String boe_dt = salesInv.getBoe_dt();
	//Following fields for New requi
	String Tariff_flag_Mst=salesInv.getTARIFF_FLAG_MST();
	String invoice_sales_rate_inr=salesInv.getTARIFF_INR();
	String Discount_flag_Mst=salesInv.getDISCOUNT_FLAG_MST();
	String discount=salesInv.getDISCOUNT_PRICE();
	String AdjustFlagForMst=salesInv.getADJUST_FLAG_MST();
	String Adjust_amt_mst=salesInv.getADJUST_AMT();
	String Adjust_cur_mst=salesInv.getADJUST_CUR();
	String Adjust_total_avail_bal=salesInv.getAdjusttotalavailbalMst();
	String TAX_ADV_ADJUST_FLAG_MST=salesInv.getTAX_ADV_ADJUST_FLAG_MST();
	String TAX_ADV_ADJUST_AMT=salesInv.getTAX_ADV_ADJUST_AMT();
	String TAX_ADV_ADJUST_CUR=salesInv.getTAX_ADV_ADJUST_CUR();
	String Adjust_total_avail_bal_tax=salesInv.getAdjust_total_avail_bal_tax();
	String MODIFY_TAX_ADV_CURR=salesInv.getMODIFY_TAX_ADV_CURR();
	String MODIFY_TAX_INV_AMT_INR=salesInv.getMODIFY_TAX_INV_AMT_INR(); //SB
	String MODIFY_TAX_ADV_ADJUSTMENT=salesInv.getMODIFY_TAX_ADV_ADJUSTMENT();
	String MODIFY_TAX_ADV_FLAG =salesInv.getMODIFY_TAX_ADV_FLAG();
	String SBC_Flag_Service=salesInv.getSBC_Flag_Service(); //SB20160506 
	String SBC_AMT_Service=salesInv.getSBC_AMT_Service(); //SB20160506
	String SBC_CUR_Service=salesInv.getSBC_CUR_Service(); //SB20160506
	String Adjust_total_avail_bal_sbc=salesInv.getAdjust_total_avail_bal_sbc();
	String MODIFY_SBC_ADV_ADJUSTMENT=salesInv.getMODIFY_SBC_ADV_ADJUSTMENT();
	String MODIFY_SBC_INV_AMT_INR=salesInv.getMODIFY_SBC_INV_AMT_INR();
	String KKC_Flag_Service=salesInv.getKKC_Flag_Service(); //SB20160506 
	String KKC_AMT_Service=salesInv.getKKC_AMT_Service(); //SB20160506
	String KKC_CUR_Service=salesInv.getKKC_CUR_Service(); //SB20160506
	String Adjust_total_avail_bal_kkc=salesInv.getAdjust_total_avail_bal_kkc();
	String MODIFY_KKC_ADV_ADJUSTMENT=salesInv.getMODIFY_KKC_ADV_ADJUSTMENT();
	String MODIFY_KKC_INV_AMT_INR=salesInv.getMODIFY_KKC_INV_AMT_INR();
	Vector multiple_adv_inv_no = salesInv.getMultiple_adv_inv_no();
	Vector multiple_adv_inv_dt = salesInv.getMultiple_adv_inv_dt();
	
	if(page_refresh_flag.equals("N"))  
	{
		adv_inv_no = salesInv.getCustomer_ADV_INV_NO();
		adv_inv_dt = salesInv.getCustomer_ADV_INV_DT();
		multiple_adv_inv_no = salesInv.getMultiple_adv_inv_no();
		multiple_adv_inv_dt = salesInv.getMultiple_adv_inv_dt();
	}
	String customer_inv_seq_no = "";
//	System.out.println("page_refresh_flag::"+page_refresh_flag);
	if(msg.length()>10)
	{
		hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"":request.getParameter("hlpl_inv_seq_no");
		customer_inv_seq_no = request.getParameter("customer_inv_seq_no")==null?"":request.getParameter("customer_inv_seq_no");
	}
	else
	{
		if(activity.equals("insert"))
		{
			hlpl_inv_seq_no = salesInv.getHlpl_Invoice_Seq_No();
			customer_inv_seq_no = salesInv.getCustomer_Invoice_Seq_No();
			
		}
		else if(activity.equals("update"))
		{
			hlpl_inv_seq_no = hlpl_invoice_sequence_no;
			customer_inv_seq_no = salesInv.getCustomer_Invoice_Seq_No();
			inv_cur_flag=salesInv.getInv_cur_flag();
			if(page_refresh_flag.equals("N"))
			{				
				contact_person = salesInv.getContactPersonCd();
				exch_rate_val = salesInv.getCustomer_Invoice_Exchg_Rate2();
				exchg_rate_ind = salesInv.getExchg_rate_ind();
				invoice_date = salesInv.getCustomer_Invoice_DT();
				due_dt = salesInv.getCustomer_Invoice_Due_DT();
				particular_date = salesInv.getParticular_date();
				if(particular_date.equals(""))
				{
					particular_date=bill_period_end_dt;
				}
				//adv_inv_no = salesInv.getCustomer_ADV_INV_NO();
				//adv_inv_dt = salesInv.getCustomer_ADV_INV_DT();
				
			}	
		}
	}
	
	//System.out.println("customer_ADV_INV_DT...."+adv_inv_dt+salesInv.getCustomer_ADV_INV_DT()+page_refresh_flag);
	String hlpl_Inv_Seq_No = "";
	String customer_Inv_Seq_No = "";
	String financial_Year = "";
	
	if(hlpl_inv_seq_no.length()>=10)
	{
		hlpl_Inv_Seq_No = hlpl_inv_seq_no.substring(0,4);
		financial_Year = hlpl_inv_seq_no.substring(5);
	}
	if(customer_inv_seq_no.length()>=10)
	{
		customer_Inv_Seq_No = customer_inv_seq_no.substring(0,3);
	}
	
	String customer_Invoice_SN_Dt = salesInv.getCustomer_Invoice_SN_Dt();
	String customer_Invoice_FGSA_Dt = salesInv.getCustomer_Invoice_FGSA_Dt();
	String contact_Customer_Name = salesInv.getContact_Customer_Name();
	String contact_Suppl_Name = salesInv.getContact_Suppl_Name();
	String modifyadjremark="";
	
	String remark_1 = "Please pay the invoiced amount by wire transfer at our Bank Account : Standard Chartered Bank (Ahmedabad Branch) - A/C No: 233-0-505333-1";
	String remark_2 = "Subject to the terms and conditions of the Framework Gas Sales Agreement executed on "+customer_Invoice_FGSA_Dt+" and Supply Notice executed on "+customer_Invoice_SN_Dt+"between "+contact_Suppl_Name+" and "+contact_Customer_Name+"";
	String remark_3 ="";  
	if(contract_type.equalsIgnoreCase("T") ||  contract_type.equalsIgnoreCase("C"))
	{
		 remark_3 = "LTCORA services for the Cargo (BOE no. "+boe_no+" dated "+boe_dt+")";
	}
	else
	{
		 remark_3 = "Re-gas services for the Cargo (BOE no. "+boe_no+" dated "+boe_dt+")";
	}
	
	String remark_4 = "Subject to the terms and conditions of the Regas Agreement executed on "+customer_Invoice_FGSA_Dt+" ";
	
	if(contract_type.equalsIgnoreCase("T") ||  contract_type.equalsIgnoreCase("C"))
	{
		remark_4="Subject to the terms and conditions of the LTCORA Agreement executed on "+customer_Invoice_FGSA_Dt+" ";
	}
	if(msg.length()>10)
	{
		remark_1 = request.getParameter("remark_1")==null?"":request.getParameter("remark_1");
		remark_2 = request.getParameter("remark_2")==null?"":request.getParameter("remark_2");
		remark_3 = request.getParameter("remark_3")==null?"":request.getParameter("remark_3");
		modifyadjremark= request.getParameter("remark_adj")==null?"":request.getParameter("remark_adj");
	}
	else if(msg.length()<10 && activity.equals("update"))
	{
		if(!salesInv.getRemark_1().trim().equals(""))
		{
			remark_1 = salesInv.getRemark_1();
		}
		if(!salesInv.getRemark_2().trim().equals(""))
		{
			remark_2 = salesInv.getRemark_2();
		}
		if(!salesInv.getRemark_3().trim().equals(""))
		{
			remark_3 = salesInv.getRemark_3();
		}	
		if(!salesInv.getModifyadjremark().trim().equals(""))
		{
			modifyadjremark = salesInv.getModifyadjremark();
		}
	}
	
	if(remark_1!=null)
	{
		if(!remark_1.trim().equals(""))
		{
			while((remark_1.indexOf("\n"))!=-1)
			{
				remark_1=(remark_1.substring(0,remark_1.indexOf("\n")-1))+"<br>"+(remark_1.substring(remark_1.indexOf("\n")+1));
			}
		}
	}
	
	if(remark_2!=null)
	{
		if(!remark_2.trim().equals(""))
		{
			while((remark_2.indexOf("\n"))!=-1)
			{
				remark_2=(remark_2.substring(0,remark_2.indexOf("\n")-1))+"<br>"+(remark_2.substring(remark_2.indexOf("\n")+1));
			}
		}
	}
	
	if(remark_3!=null)
	{
		if(!remark_3.trim().equals(""))
		{
			while((remark_3.indexOf("\n"))!=-1)
			{
				remark_3=(remark_3.substring(0,remark_3.indexOf("\n")-1))+"<br>"+(remark_3.substring(remark_3.indexOf("\n")+1));
			}
		}
	}
	
	if(modifyadjremark!=null)
	{
		if(!modifyadjremark.trim().equals(""))
		{
			while((modifyadjremark.indexOf("\n"))!=-1)
			{
				modifyadjremark=(modifyadjremark.substring(0,modifyadjremark.indexOf("\n")-1))+"<br>"+(modifyadjremark.substring(modifyadjremark.indexOf("\n")+1));
			}
		}
	}
	
	String invno = "";
	if(hlpl_inv_seq_no.length()>13)
	{
		invno = hlpl_inv_seq_no.substring(0,5)+""+hlpl_inv_seq_no.substring(7,10)+""+hlpl_inv_seq_no.substring(12);
	}
	
	String offspec_flag = "N";
	
	if(!accepted_Offspec_Qty.trim().equals(""))
	{
		if(Double.parseDouble(accepted_Offspec_Qty)>=0.01)
		{
			offspec_flag = "Y";
		}
	}
	
	//Following (2) String Getter Methods Has Been Defined By Samik Shah On 15th April, 2011 ...
	String contact_addr_flag = salesInv.getContact_addr_flag();
	String sn_ref_no = salesInv.getSn_ref_no();
	String taxcd=salesInv.getTax_struc_cd();   	
	
	//Adjustment
	//adv.setCallFlag("adjustmentforsnloaregas");
	//adv.setMstcustomer_cd(customer_cd);
	//adv.setMstfgsa_no(fgsa_no);
	//adv.setMstfgsa_rev_no(fgsa_rev_no);
	//adv.setMstsn_no(sn_no);
	//adv.setMstsn_rev_no(sn_rev_no);
	//adv.setMstcontract_type(contract_type);
	//adv.init();
	//String AdjustFlagForMst=adv.getMstadjust_flag();
	
		String modifyadjflag="";
		String modifyadjrecvflag="";
		String modifyadjsign="1";
		
		String modifyadjcurrency="";
		String modifyadjamt="";
		String modifygrossamtadjusted="";
		String modifygrossamontinradjusted="";
//		System.out.println("activi--yt-------"+activity);
		
	String TariffFlag=request.getParameter("TariffFlag")==null?"N":request.getParameter("TariffFlag");
	String DiscountFlag=request.getParameter("DiscountFlag")==null?"N":request.getParameter("DiscountFlag");
	if(activity.equals("update") && AdjustFlagForMst.equalsIgnoreCase("Y") )
	{
		
	//	System.out.println("activi--yt-------"+mapping_id);
		adv.setCallFlag("updategetadvancedetails");
		adv.setModifyfgsano(fgsa_no);
		adv.setModifyadjustamtmst(Adjust_amt_mst);
		adv.setModifysnno(sn_no);
		adv.setModifycontract_type(contract_type);
		adv.setModifyhlplInvoiceNo(hlpl_inv_seq_no2);
		adv.setModifyFinancialYear(inv_financial_year);
		adv.setModifycustomercd(customer_cd);
		adv.setModifyplantseqno(customer_plant_seq_no);
		adv.setModifybillperiodenddate(bill_period_end_dt);
		adv.setModifyexchangerateselected(exch_rate_val);
		adv.setModifyfgsarevno(fgsa_rev_no);
		adv.setCustomer_inv_mapping_id(mapping_id); //ADDED FOR LTCORA AND CN
		adv.init();
		
		 modifyadjflag=adv.getModifyadjflag();
		 modifyadjrecvflag=adv.getModifyadjrecv();
		 modifyadjsign=adv.getModifyadjsign();
		 modifyadjremark=adv.getModifyadjremark();
		 modifyadjcurrency=adv.getModifyadjcur();
		 modifyadjamt=adv.getModifyadjamt();
		 modifygrossamtadjusted=adv.getModifygrossamtadjusted();
		 modifygrossamontinradjusted=adv.getModifygrossamtinradjusted();
		 String modifyadvinvno=adv.getModifyadvinvno();
	}
	
	if(activity.equals("update") && (Tariff_flag_Mst.equalsIgnoreCase("Y") || Discount_flag_Mst.equalsIgnoreCase("Y")  ))
	{
		adv.setCallFlag("updategettariffanddiscountdetails");
		adv.setModifycontract_type(contract_type);
		adv.setModifyhlplInvoiceNo(hlpl_inv_seq_no2);
		adv.setModifyFinancialYear(inv_financial_year);
		adv.setModifycustomercd(customer_cd);
		adv.setModifyplantseqno(customer_plant_seq_no);
		adv.setModifybillperiodenddate(bill_period_end_dt);
		adv.setModifyexchangerateselected(exch_rate_val);
		adv.setCustomer_inv_mapping_id(mapping_id); //ADDED FOR LTCORA AND CN
		adv.init();
		if(page_refresh_flag.equals("N"))
		{
		if(Tariff_flag_Mst.equalsIgnoreCase("Y"))
			TariffFlag=adv.getModifytariff_flag();
		}
		if(Discount_flag_Mst.equalsIgnoreCase("Y"))
			DiscountFlag=adv.getModifydiscount_flag();
	}
	String entrypoint=request.getParameter("entrypoint")==null?"load":request.getParameter("entrypoint");
	String discount_count=request.getParameter("discount_count")==null?"0":request.getParameter("discount_count");
	discount_count=""+(Integer.parseInt(discount_count)+1);
	if(activity.equals("update")){
	if(entrypoint.equalsIgnoreCase("load") && !discount_count.equalsIgnoreCase("1")){
		DiscountFlag="N";
	}
	}
	String discount_val_deducted=""+((Double.parseDouble(invoice_Sales_Rate))-(Double.parseDouble(discount)));
	String contact_Suppl_GST_NO="";
	String contact_Suppl_CST_NO="";
	String contact_Suppl_GST_DT="";
	String contact_Suppl_CST_DT="";
	String contact_Suppl_PAN_NO="";		//BK20160211
	String contact_Suppl_PAN_DT="";		//BK20160211
	contact_Suppl_GST_NO = salesInv.getContact_Suppl_GST_NO();
	contact_Suppl_CST_NO = salesInv.getContact_Suppl_CST_NO();
	contact_Suppl_GST_DT = salesInv.getContact_Suppl_GST_DT();
	contact_Suppl_CST_DT = salesInv.getContact_Suppl_CST_DT();
	contact_Suppl_PAN_NO = salesInv.getContact_Suppl_PAN_NO();	//BK20160211
	contact_Suppl_PAN_DT = salesInv.getContact_Suppl_PAN_DT();	//BK20160211
	String contact_Suppl_Service_Tax_NO = salesInv.getContact_Suppl_Service_Tax_NO();
	Vector vSTAT_NO = salesInv.getVSTAT_NO();
	Vector vSTAT_NM = salesInv.getVSTAT_NM();
	int size = vSTAT_NO.size();
	java.text.NumberFormat nf = new java.text.DecimalFormat("0.00");
%>

<body  <%if(msg.length()>10){%>onLoad="doClose('<%=month%>','<%=year%>','<%=bill_cycle%>','<%=msg%>');"
<%}else{ %> onLoad="call_on_load();"<%  }%>>

<%	if(print_permission.trim().equalsIgnoreCase("N"))
	{	%>
		<script language="javascript" src="../js/mouseclk.js"></script>
<%	}	%>
<form name="frm_invoice_dtl" method="post" action="../servlet/Frm_Sales_Invoice">
<input type="hidden" name="contact_Suppl_GST_NO" value="<%=contact_Suppl_GST_NO %>">
<input type="hidden" name="contact_Suppl_CST_NO" value="<%=contact_Suppl_CST_NO %>">
<input type="hidden" name="contact_Suppl_GST_DT" value="<%=contact_Suppl_GST_DT %>">
<input type="hidden" name="contact_Suppl_CST_DT" value="<%=contact_Suppl_CST_DT %>">
<input type="hidden" name="contact_Suppl_Service_Tax_NO" value="<%=contact_Suppl_Service_Tax_NO%>">
<!-- //BK20160211 -->
<input type="hidden" name="contact_Suppl_PAN_NO" value="<%=contact_Suppl_PAN_NO %>">
<input type="hidden" name="contact_Suppl_PAN_DT" value="<%=contact_Suppl_PAN_DT %>">
<!--  -->

<%for(int i=0;i<vSTAT_NO.size();i++) { %>
<input type="hidden" name="vstat_nm" value="<%=vSTAT_NM.elementAt(i)%>">
<input type="hidden" name="vstat_no" value="<%=vSTAT_NO.elementAt(i)%>">
<%} %>
<input type="hidden" name="tax_size" value="<%=size%>">
<input type="hidden" name="customer_cd_tax" value="<%=customer_cd %>">
<input type="hidden" name="customer_abbr_tax" value="<%=customer_abbr %>">
<input type="hidden" name="Adv_submitted_flag" value="<%=Adv_submitted_flag %>">
<div id="col-new">
<table width="98%"  border="0" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF">
<%	if(msg.length()>10)	
	{	%>
	<tr valign="middle" class="message">
    	<td colspan="6">
			<div align="center">
				<font size="4" face="Verdana, Arial, Helvetica, sans-serif">
					<b><%=msg%></b>
				</font>
			</div>
		</td>
	</tr>	
<%	}	%>	
	<tr valign="top" class="highlighttext">
    	<td colspan="5">
			<div align="center">
				<font size="4" face="Verdana, Arial, Helvetica, sans-serif">
					<%	if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
						{	%>
							<b>TAX INVOICE</b>
					<%	}	
						else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
						{	%>
							<b>INVOICE</b>
					<%	}	%>&nbsp;<b>DETAILS</b>
					<input type="hidden" name="msg" value="<%=msg%>">
				</font>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<input type="button" name="close_window" value="Close Window" onClick="closeWindow();">
			</div>
		</td>
	</tr>
	<tr class="title2" valign="top">
		<td colspan="1">
			<div align="center">
				<font size="1.2px"><b>Customer</b></font>
			</div>
	    </td>
		<td colspan="1">
			<div align="center">
				<font size="1.2px"><b>SN/LOA/RE&nbsp;Ref.&nbsp;NO</b></font>
			</div>
	    </td>
	    <td colspan="1">
			<div align="center">
				<font size="1.2px"><b>Plant</b></font>
			</div>
	    </td>
	    <td colspan="1">
			<div align="center">
				<font size="1.2px"><b>QTY&nbsp;(MMBTU)</b></font>
			</div>
	    </td>
	    <td colspan="1">
			<div align="center">
				<font size="1.2px"><b>Price&nbsp;($/MMBTU)</b></font>
			</div>
	    </td>
	    <td colspan="1">
			<div align="center">
				<font size="1.2px"><b>Contact&nbsp;Person</b></font>
			</div>
	    </td>
	</tr>
	<tr class="content1" valign="top">
		<td colspan="1">
			<div align="center">
				<font size="2"><b><%=customer_abbr%></b></font>
				<input type="hidden" name="customer_cd" value="<%=customer_cd%>">
				<input type="hidden" name="customer_abbr" value="<%=customer_abbr%>">
			</div>
	    </td>
		<td colspan="1">
			<div align="center">
				<font size="2"><b><%if(sn_ref_no.trim().equals("")){%><%=sn_no%><%}else{%><%=sn_ref_no%><%}%></b></font>
				<input type="hidden" name="fgsa_no" value="<%=fgsa_no%>">
				<input type="hidden" name="fgsa_rev_no" value="<%=fgsa_rev_no%>">
				<input type="hidden" name="sn_no" value="<%=sn_no%>">
				<input type="hidden" name="sn_rev_no" value="<%=sn_rev_no%>">
				<input type="hidden" name="contract_type" value="<%=contract_type%>">
			</div>
	    </td>
	    <td colspan="1">
			<div align="center">
				<font size="2"><b><%=customer_plant_nm%></b></font>
				<input type="hidden" name="customer_plant_seq_no" value="<%=customer_plant_seq_no%>">
				<input type="hidden" name="customer_plant_nm" value="<%=customer_plant_nm%>">
			</div>
	    </td>
	    <td colspan="1">
			<div align="center">
				<font size="2"><b><%=total_Invoice_Qty%></b></font>
				<input type="hidden" name="total_qty" value="<%=total_Invoice_Qty%>">
			</div>
	    </td>
	    <td colspan="1">
			<div align="center">
				<font size="2"><b><%=invoice_Sales_Rate%></b></font>
				<input type="hidden" name="sale_price" value="<%=invoice_Sales_Rate%>">
			</div>
	    </td>
	    <td colspan="1">
			<div align="center">
				<select name="contact_person">
					<option value="0">--Select--</option>
				<%	for(int i=0; i<invoice_Customer_Contact_Cd.size(); i++)
					{ //System.out.println("---------------------------------------------"+invoice_Customer_Contact_Cd.elementAt(i)+" and "+invoice_Customer_Contact_Nm.elementAt(i));
					
					%>	
						<option value="<%=(String)invoice_Customer_Contact_Cd.elementAt(i)%>"><%=(String)invoice_Customer_Contact_Nm.elementAt(i)%></option>
				<%	}	%>
				</select>
			</div>
	    </td>
	</tr>
	<script>
		document.forms[0].contact_person.value="<%=contact_person%>";
	</script>
	<tr class="title2" valign="top">
		<td colspan="1">
			<div align="center">
				<font size="1.2px"><b>Invoice&nbsp;Date</b></font>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<font size="1.2px"><b>Due&nbsp;Date</b></font>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<font size="1.2px"><b>Bill&nbsp;Period<br>Start&nbsp;Date</b></font>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<font size="1.2px"><b>Bill&nbsp;Period<br>End&nbsp;Date</b></font>
			</div>
		</td>
		<td colspan="2">
			<div align="center">
				<font size="1.2px"><b>HLPL&nbsp;Invoice<br>Sequence&nbsp;NO</b></font>
			</div>
		</td>
	</tr>
	<tr class="content1" valign="top">
		<td colspan="1">
			<div align="center">
			<%	if(exchg_rate_cal_method.equalsIgnoreCase("D"))
				{	%>
					<input type="text" name="invoice_date" value="<%=invoice_date%>" size="8" maxlength="11" onBlur="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F');">
					<%--onBlur="refreshPage('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>');">--%>
			<%	}
				else
				{	%>
					<input type="text" name="invoice_date" value="<%=invoice_date%>" size="8" maxlength="11">
			<%	}	%>
				<a href="javascript:show_calendar('forms[0].invoice_date');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
					<img src="../images/calendar.gif" width="25" height="22" border="0">
				</a>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<input type="text" name="due_date" value="<%=due_dt%>" size="8" maxlength="11">
				<a href="javascript:show_calendar('forms[0].due_date');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
					<img src="../images/calendar.gif" width="25" height="22" border="0">
				</a>
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<input type="text" name="bill_period_start_date" value="<%=bill_period_start_dt%>" size="8" class="mkRdly" readOnly maxlength="11">
			</div>
		</td>
		<td colspan="1">
			<div align="center">
				<input type="text" name="bill_period_end_date" value="<%=bill_period_end_dt%>" size="8" class="mkRdly" readOnly maxlength="11">
			</div>
		</td>
		<td colspan="2">
			<div align="center">
				<font size="2"><b>
				<%
				if(!GST_INVOICE_SEQ_NO.equals("")) { %>
					<%=GST_INVOICE_SEQ_NO%>
				<% } else { %>
					<%=invno%>
				<% } %>
				</b></font>
				<input type="hidden" name="hlpl_inv_no_prev" value="<%=invno %>">
				<input type="hidden" name="hlpl_inv_seq_no" value="<%=hlpl_inv_seq_no%>">
				<input type="hidden" name="customer_inv_seq_no" value="<%=customer_inv_seq_no%>">
				<input type="hidden" name="hlpl_Inv_Seq_No" value="<%=hlpl_Inv_Seq_No%>">
				<input type="hidden" name="customer_Inv_Seq_No" value="<%=customer_Inv_Seq_No%>">
				<input type="hidden" name="financial_Year" value="<%=financial_Year%>">
				<input type="hidden" name="GST_INVOICE_SEQ_NO" value="<%=GST_INVOICE_SEQ_NO%>">
				
			</div>
	    </td>
	</tr>
	<%	if(offspec_flag.trim().equalsIgnoreCase("Y"))
		{ 	%>
			<tr valign="middle">
				<td class="title2" colspan="2">
					<div align="left">
						Offspec QTY:&nbsp;
					</div>
				</td>
				<td class="content1" colspan="1">
					<div align="left">
						&nbsp;<input type="text" name="offspec_qty" value="<%=accepted_Offspec_Qty%>" size="8" class="mkRdly" readOnly maxlength="11">
					</div>
				</td>
				<td class="title2" colspan="2">
					<div align="left">
						Offspec Rate:&nbsp;
					</div>
				</td>
				<td class="content1" colspan="1">
					<div align="left">
						&nbsp;<input type="text" name="offspec_rate" value="<%=offspec_rate%>" size="8" onBlur="checkNumber1(this,6,4);setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F');">
					</div>
				</td>
			</tr>
	<%	}	%>
	<tr valign="top">
		<td class="title2" colspan="2">
			<div align="left">
				Exchange Rate Consideration On:
			</div>
		</td>
		<td class="content1" colspan="4">
			<div align="left">
				&nbsp;<b><%=invoice_Exchg_Rate_Note%></b>
			</div>
		</td>
	</tr>
	
	<tr class="highlighttext" valign="top">
		<td colspan="6">
			<div align="center">
				<font size="3"><b>Applicable Exchange Rates</b></font>
			</div>
		</td>
	</tr>
	
<%	
	if(exchg_rate_cal_method.equalsIgnoreCase("D"))
	{	
	%>
	<tr class="title2" valign="middle">
		<td width="25%"><div align="left"><font size="1.2px"><b>&nbsp;EXCHANGE&nbsp;RATE&nbsp;DAY</b></font></div></td>
		<td width="15%"><div align="center"><font size="1.2px"><b>DATE</b></font></div></td>
		<td width="15%"><div align="center"><font size="1.2px"><b>AVERAGE&nbsp;OF<br>SBI&nbsp;TT&nbsp;SELLING<br>&amp;<br>SBI&nbsp;TT&nbsp;BUYING</b></font></div></td>
		<td width="15%"><div align="center"><font size="1.2px"><b>RBI&nbsp;REFERENCE</b></font></div></td>
		<td width="15%"><div align="center"><font size="1.2px"><b>SBI&nbsp;TT&nbsp;SELLING</b></font></div></td>
		<td width="15%"><div align="center"><font size="1.2px"><b>SBI&nbsp;TT&nbsp;BUYING</b></font></div></td>
	</tr>
	<tr class="content1" valign="middle">
		<td colspan="1" bgcolor="silver"><div align="left"><font size="2px"><b>&nbsp;Previous&nbsp;Available&nbsp;Day</b></font></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="previous_dt" value="<%=invoice_Previous_Available_Exchg_Date%>"><%=invoice_Previous_Available_Exchg_Date%></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="exch_rate_code" value="<%=sbi_avg_tt_selling_buying_cd%>"><input type="hidden" name="exch_rate_value" value="<%=invoice_Previous_Exchg_Rate_Avg_TT_Buy_Sell%>"><input type="radio" name="exch_rate" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F');"><%=invoice_Previous_Exchg_Rate_Avg_TT_Buy_Sell%></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="exch_rate_code" value="<%=rbi_ref_cd%>"><input type="hidden" name="exch_rate_value" value="<%=invoice_Previous_Exchg_Rate_RBI_Ref%>"><input type="radio" name="exch_rate" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F');"><%=invoice_Previous_Exchg_Rate_RBI_Ref%></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="exch_rate_code" value="<%=sbi_tt_selling_cd%>"><input type="hidden" name="exch_rate_value" value="<%=invoice_Previous_Exchg_Rate_TT_Selling%>"><input type="radio" name="exch_rate" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','f');"><%=invoice_Previous_Exchg_Rate_TT_Selling%></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="exch_rate_code" value="<%=sbi_tt_buying_cd%>"><input type="hidden" name="exch_rate_value" value="<%=invoice_Previous_Exchg_Rate_TT_Buying%>"><input type="radio" name="exch_rate" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F');"><%=invoice_Previous_Exchg_Rate_TT_Buying%></div></td>
	</tr>
	<tr class="content1" valign="middle">
		<td colspan="1" bgcolor="silver"><div align="left"><font size="2px"><b>&nbsp;Bill&nbsp;Generation&nbsp;Day</b></font></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="inv_date" value="<%=invoice_date%>"><%=invoice_date%></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="exch_rate_code" value="<%=sbi_avg_tt_selling_buying_cd%>"><input type="hidden" name="exch_rate_value" value="<%=invoice_Exchg_Rate_Avg_TT_Buy_Sell%>"><input type="radio" name="exch_rate" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F');" <%=sbi_avg_tt_selling_buying_flag%>><%=invoice_Exchg_Rate_Avg_TT_Buy_Sell%></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="exch_rate_code" value="<%=rbi_ref_cd%>"><input type="hidden" name="exch_rate_value" value="<%=invoice_Exchg_Rate_RBI_Ref%>"><input type="radio" name="exch_rate" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F');" <%=rbi_ref_flag%>><%=invoice_Exchg_Rate_RBI_Ref%></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="exch_rate_code" value="<%=sbi_tt_selling_cd%>"><input type="hidden" name="exch_rate_value" value="<%=invoice_Exchg_Rate_TT_Selling%>"><input type="radio" name="exch_rate" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F');" <%=sbi_tt_selling_flag%>><%=invoice_Exchg_Rate_TT_Selling%></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="exch_rate_code" value="<%=sbi_tt_buying_cd%>"><input type="hidden" name="exch_rate_value" value="<%=invoice_Exchg_Rate_TT_Buying%>"><input type="radio" name="exch_rate" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F');" <%=sbi_tt_buying_flag%>><%=invoice_Exchg_Rate_TT_Buying%></div></td>
	</tr>
	<%
	int period_size=12;
	if(exchg_rate_cal_method.equalsIgnoreCase("A"))
	{
		period_size=invoice_Period_Dates.size();
	}
	%>
	<tr class="content1" valign="middle">
		<td colspan="1" bgcolor="silver"><div align="left"><font size="2px"><b>&nbsp;User&nbsp;Defined&nbsp;Day</b></font></div></td>
		<td colspan="1">
			<div align="center">
				<input type="text" name="particular_date" value="<%=particular_date%>" size="8" maxlength="10" onBlur="refreshPage('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=mapping_id%>','<%=period_size%>','<%=invoice_Period_Dates.size()%>','F');">
				<a href="javascript:show_calendar('forms[0].particular_date');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
					<img src="../images/calendar.gif" width="25" height="22" border="0">
				</a>
			</div>
		</td>
		<td colspan="1"><div align="center"><input type="hidden" name="exch_rate_code" value="<%=sbi_avg_tt_selling_buying_cd%>"><input type="hidden" name="exch_rate_value" value="<%=invoice_Particular_Day_Exchg_Rate_Avg_TT_Buy_Sell%>"><input type="radio" name="exch_rate" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F');"><%=invoice_Particular_Day_Exchg_Rate_Avg_TT_Buy_Sell%></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="exch_rate_code" value="<%=rbi_ref_cd%>"><input type="hidden" name="exch_rate_value" value="<%=invoice_Particular_Day_Exchg_Rate_RBI_Ref%>"><input type="radio" name="exch_rate" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F');"><%=invoice_Particular_Day_Exchg_Rate_RBI_Ref%></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="exch_rate_code" value="<%=sbi_tt_selling_cd%>"><input type="hidden" name="exch_rate_value" value="<%=invoice_Particular_Day_Exchg_Rate_TT_Selling%>"><input type="radio" name="exch_rate" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F');"><%=invoice_Particular_Day_Exchg_Rate_TT_Selling%></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="exch_rate_code" value="<%=sbi_tt_buying_cd%>"><input type="hidden" name="exch_rate_value" value="<%=invoice_Particular_Day_Exchg_Rate_TT_Buying%>"><input type="radio" name="exch_rate" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F');"><%=invoice_Particular_Day_Exchg_Rate_TT_Buying%></div></td>
	</tr>
	<%
	if(!exchg_rate_index.equals("-1"))
		{	%>
			<script>
				document.forms[0].exch_rate[<%=exchg_rate_ind%>].checked = true;
			</script>
	<%	}	%>
	<tr class="content1" valign="middle">
		<td colspan="6">
		<%	for(int i=0; i<invoice_Period_Dates.size(); i++)
			{	%>
				<input type="hidden" name="daily_inv_dt" size="10" value="<%=(String)invoice_Period_Dates.elementAt(i)%>">
				<input type="hidden" name="daily_inv_qty" style="text-align:right" size="10" value="<%=(String)daily_Invoice_QTY.elementAt(i)%>">
				<input type="hidden" name="daily_inv_amt_inr" value="0">
				<input type="hidden" name="daily_inv_amt_usd" value="0">
		<%	}	%>
		</td>
	</tr>
<%	}
	else if(exchg_rate_cal_method.equalsIgnoreCase("A"))
	{	%>
	<tr class="title2" valign="middle">
		<td width="18%"><div align="center"><font size="1.2px"><b>DATE</b></font></div></td>
		<td width="18%"><div align="center"><font size="1.2px"><input type="hidden" name="exch_rate_code" value="<%=sbi_avg_tt_selling_buying_cd%>"><input type="radio" name="exch_rate" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=invoice_Period_Dates.size()%>','<%=invoice_Period_Dates.size()%>','F');" <%=sbi_avg_tt_selling_buying_flag%>><b>AVERAGE&nbsp;OF<br>SBI&nbsp;TT&nbsp;SELLING<br>&amp;<br>SBI&nbsp;TT&nbsp;BUYING</b></font></div></td>
		<td width="18%"><div align="center"><font size="1.2px"><input type="hidden" name="exch_rate_code" value="<%=rbi_ref_cd%>"><input type="radio" name="exch_rate" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=invoice_Period_Dates.size()%>','<%=invoice_Period_Dates.size()%>','F');" <%=rbi_ref_flag%>><b>RBI&nbsp;REFERENCE</b></font></div></td>
		<td width="18%"><div align="center"><font size="1.2px"><input type="hidden" name="exch_rate_code" value="<%=sbi_tt_selling_cd%>"><input type="radio" name="exch_rate" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=invoice_Period_Dates.size()%>','<%=invoice_Period_Dates.size()%>','F');" <%=sbi_tt_selling_flag%>><b>SBI&nbsp;TT&nbsp;SELLING</b></font></div></td>
		<td width="18%"><div align="center"><font size="1.2px"><input type="hidden" name="exch_rate_code" value="<%=sbi_tt_buying_cd%>">
		<input type="radio" name="exch_rate" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=invoice_Period_Dates.size()%>','<%=invoice_Period_Dates.size()%>','F');" <%=sbi_tt_buying_flag%>><b>SBI&nbsp;TT&nbsp;BUYING</b></font></div></td>
		<td width="10%"><div align="right"><font size="1.2px"><b>DAILY&nbsp;QTY&nbsp;(MMBTU)</b></font></div></td>
	</tr>
<%	for(int i=0; i<invoice_Period_Dates.size(); i++)
	{	%>	
	<tr class="content1" valign="middle">	
		<td colspan="1"><div align="center"><input type="hidden" name="daily_inv_dt" value="<%=(String)invoice_Period_Dates.elementAt(i)%>"><%=(String)invoice_Period_Dates.elementAt(i)%></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="exch_rate_value" value="<%=(String)invoice_Period_Exchg_Rate_Avg_TT_Buy_Sell.elementAt(i)%>"><%=(String)invoice_Period_Exchg_Rate_Avg_TT_Buy_Sell.elementAt(i)%></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="exch_rate_value" value="<%=(String)invoice_Period_Exchg_Rate_RBI_Ref.elementAt(i)%>"><%=(String)invoice_Period_Exchg_Rate_RBI_Ref.elementAt(i)%></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="exch_rate_value" value="<%=(String)invoice_Period_Exchg_Rate_TT_Selling.elementAt(i)%>"><%=(String)invoice_Period_Exchg_Rate_TT_Selling.elementAt(i)%></div></td>
		<td colspan="1"><div align="center"><input type="hidden" name="exch_rate_value" value="<%=(String)invoice_Period_Exchg_Rate_TT_Buying.elementAt(i)%>"><%=(String)invoice_Period_Exchg_Rate_TT_Buying.elementAt(i)%></div></td>
		<td colspan="1">
			<div align="right">
				<input type="text" name="daily_inv_qty" style="text-align:right" size="12" value="<%=(String)daily_Invoice_QTY.elementAt(i)%>">
				<input type="hidden" name="daily_inv_amt_inr" value="0">
				<input type="hidden" name="daily_inv_amt_usd" value="0">
			</div>
		</td>
	</tr>
<%	}	%>
	<%	if(!exchg_rate_index.equals("-1"))
		{	%>
			<script>
				document.forms[0].exch_rate[<%=exchg_rate_ind%>].checked = true;
			</script>
	<%	}	%>
<%	}	%>
	
	<tr class="content1">
		<td colspan="6">
			
		</td>
	</tr>
	<%
	if(Discount_flag_Mst.equalsIgnoreCase("Y")){ 
	
		int period_size=12;
		if(exchg_rate_cal_method.equalsIgnoreCase("A"))
		{
			period_size=invoice_Period_Dates.size();
		}
	%>
	<tr class="title2">
		<td colspan="6">
		<div align="left">
		<%if(DiscountFlag.equalsIgnoreCase("Y") || discount_flag_apply.equalsIgnoreCase("Y")){ 
			
			if(DiscountFlag.equalsIgnoreCase("N") && discount_flag_apply.equalsIgnoreCase("N")){ 
			%>
		<input type="checkbox" name="discount_check" value="" id="discount_check" onclick="applydiscount(this,'<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=period_size%>','<%=invoice_Period_Dates.size()%>','F','click'),discount_setter(this);" >
		<%}
			else if(DiscountFlag.equalsIgnoreCase("Y") && discount_flag_apply.equalsIgnoreCase("N") && discount_count.equalsIgnoreCase("1")){%>
			<input type="checkbox" name="discount_check" value="" id="discount_check" onclick="applydiscount(this,'<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=period_size%>','<%=invoice_Period_Dates.size()%>','F','click'),discount_setter(this);" checked="checked" >
		<%}
			else if(DiscountFlag.equalsIgnoreCase("Y") && discount_flag_apply.equalsIgnoreCase("N")){%>
			<input type="checkbox" name="discount_check" value="" id="discount_check" onclick="applydiscount(this,'<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=period_size%>','<%=invoice_Period_Dates.size()%>','F','click'),discount_setter(this);" >
		<%}
			
			else{%>
		<input type="checkbox" name="discount_check" value="" id="discount_check" onclick="applydiscount(this,'<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=period_size%>','<%=invoice_Period_Dates.size()%>','F','click'),discount_setter(this);" checked="checked">
		<%} %>
		
		<%}else{ %>
		<input type="checkbox" name="discount_check" value="" id="discount_check" onclick="applydiscount(this,'<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=period_size%>','<%=invoice_Period_Dates.size()%>','F','click'),discount_setter(this);">
		<%} %>
			<font size="1"><b>Applicable Discount : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="text" name="discount_value" size="10" value="<%=discount%>" style="text-align:right" class="mkRdly" readOnly >
			($/MMBTU)
			</b></font>
		</div>
		
		
		</td>
	</tr>
	<%} %>
			
	<tr class="content1" valign="top">
		<td colspan="2">
			<div align="center"><b>
				 Invoice AMT USD : &nbsp;<input type="text" name="gross_amt_usd" size="10" value="<%=gross_amt_usd%>" style="text-align:right" class="mkRdly" readOnly>
			</b></div>
			<input type="hidden" name="raw_amt_usd" value="<%=raw_amt_usd%>">
		</td>
		<td colspan="2">
			<div align="center"><b>
				Applied Exchange Rate : <input type="text" name="exch_rate_val" size="10" value="<%=exch_rate_val%>" style="text-align:right" class="mkRdly" readOnly>
			</b></div>
		</td>
		<td colspan="2">
			<div align="center"><b>
				 Invoice AMT INR : &nbsp;<input type="text" name="gross_amt_inr" size="10" value="<%=gross_amt_inr%>" style="text-align:right" class="mkRdly" readOnly>
			</b></div>
			<input type="hidden" name="raw_amt_inr" value="<%=raw_amt_inr%>">
		</td>
	</tr>
	<tr class="content1">
		<td colspan="6">
			
		</td>
	</tr>
	
	<%if(Tariff_flag_Mst.equalsIgnoreCase("Y")){ %>
	<!-- INR  PRICE -->
	 <tr class="highlighttext" valign="top">
		<td colspan="6">
		
		
		<div align="left">
		<%if(TariffFlag.equalsIgnoreCase("Y")){ %>
		<input type="checkbox" name="sale_price_inr_check" value="" id="sale_price_inr_check" onclick="displaysalepricedetails('N'),tariff_setter(this);" checked="checked">
		<%}else{ %>
		<input type="checkbox" name="sale_price_inr_check" value="" id="sale_price_inr_check" onclick="displaysalepricedetails('N'),tariff_setter(this);">
		<%} %>
			<font size="3"><b><%	if(contract_type.equalsIgnoreCase("R"))
		{	%>Regasification Tariff <%} else if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		{	%>LTCORA Tariff <%}else{ %>&nbsp;Tariff <%} %> : INR</b></font>
			</div>
		</td>
	
	</tr>
	<tr>
	<td colspan="6">
	<div id="salepricediv">
	</div>
	</td>
	</tr>
	
	<%} %>
	<tr class="content1">
	<td colspan="6"></td>
	</tr>
	
	<tr class="highlighttext">
		<td colspan="6" align="left"  style=" padding-left: 10px">Invoice In Multiple Currency&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<select name="inv_cur_flag">
			<option value="N">No</option>
			<option value="Y">Yes</option>
			</select>
		</td>
		<script>
		document.forms[0].inv_cur_flag.value='<%=inv_cur_flag%>';
		</script>
	</tr>
	
	<%if(!activity.equals("update")) { %>
	 <%if(AdjustFlagForMst.equalsIgnoreCase("Y")){ %>
	 	<tr class="title2" valign="top">
	 		<td colspan="6">
				<div align="left">
					<font size="2"><b>&nbsp;&nbsp;Adjust
					<%if(pay_mode.equalsIgnoreCase("AP")){ %>
					 Advance Payment ?<%}else{ %>Special Payment ?<%} %></b></font><!-- HS20160722 -->
					&nbsp;&nbsp;
					<font size="1"><b><input type="radio" name="adjust" value="Y" onclick="document.getElementById('adjustmentdetails').style.display='table-row';displaydetails('AA');">Yes</b></font>&nbsp;&nbsp;
					<font size="1"><b><input type="radio" name="adjust" value="N" checked="checked" onclick="document.getElementById('adjustmentdetails').style.display='none';hidedetails();document.getElementById('afadj').checked=true;">No</b></font>
				</div>
			</td>
		</tr>
	 	<tr id="adjustmentdetails" class="highlighttext" style="font-size: 13px;display: none;">
			<td colspan="3" align="left">
<!-- 			<input type="radio" name="adjust" value="befadj" onclick="displaydetails();">Before Adjustment&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 			<input type="radio" name="adjust" value="aftadj" checked="checked" onclick="hidedetails();">After Adjustment -->
				<input type="radio" name="adjustdetails" id="bfadj" value="BA" onclick="displaydetails(this.value);">Before Adjustment&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="adjustdetails" id="afadj" value="AA" checked="checked" onclick="displaydetails(this.value);">After Adjustment
			</td>
			<td colspan="3">
				&nbsp;&nbsp;<%if(pay_mode.equalsIgnoreCase("AP")){ %>Advance<%}else{ %>Special<%} %>  Inv No & Date:&nbsp;<!-- HS20160722 -->
				<input type='text' name='adv_inv_no' size='12' maxlength='20' style='text-align: center;' value='<%=adv_inv_no%>' >
				&nbsp;&nbsp;&nbsp;
				<input type='text' name='adv_inv_dt' size='10' maxlength='10' style='text-align: center;' value='<%=adv_inv_dt%>' >
				<a href="javascript:show_calendar('forms[0].adv_inv_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
					<img src="../images/calendar.gif" width="25" height="22" border="0">
				</a>
			</td>
		</tr>
		
		<tr >
			<td colspan="6">
			<div id="afteradjustment">
			</div>
			</td>
		</tr>
<%} 
 }else{ %>
 		<%if(AdjustFlagForMst.equalsIgnoreCase("Y")){ %>
 		<tr class="title2" valign="top">
	 		<td colspan="6">
				<div align="left">
					<font size="2"><b>&nbsp;&nbsp;Adjust
					<%if(pay_mode.equalsIgnoreCase("AP")){ %>
					 Advance Payment ?<%}else{ %>Special Payment ?<%} %></b></font><!-- HS20160722 -->
					&nbsp;&nbsp;
						<font size="1"><b><input type="radio" name="adjust" value="Y" 
							<%if(modifyadjflag.equalsIgnoreCase("Y")){ %> checked="checked" <%} %>onclick="document.getElementById('adjustmentdetails').style.display='table-row';document.forms[0].advflg.value='AA';displaydetailsmodify();">Yes</b></font>&nbsp;&nbsp;
						<font size="1"><b><input type="radio" name="adjust" value="N"
							<%if(!modifyadjflag.equalsIgnoreCase("Y")){ %>  checked="checked" <%} %>  onclick="document.getElementById('adjustmentdetails').style.display='none';hidedetailsmodify();document.getElementById('afadj').checked=true;">No</b></font>
				</div>
			</td>
		</tr>
	 		<tr id="adjustmentdetails" class="highlighttext" style="font-size: 13px; <%if(!modifyadjflag.equalsIgnoreCase("Y")){ %>display: none;<% }%>">
			<td colspan="3" align="left">
				<input type="radio" name="adjustdetails" id="bfadj" value="BA"  <%if(advanceAdjFlg.equalsIgnoreCase("BA")){ %> checked="checked" <%} %>  onclick="document.forms[0].advflg.value='BA';displaydetailsmodify();">Before Adjustment&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="adjustdetails" id="afadj" value="AA" <%if(advanceAdjFlg.equalsIgnoreCase("AA") || advanceAdjFlg.equalsIgnoreCase("NA")){ %> checked="checked" <%} %> onclick="document.forms[0].advflg.value='AA';displaydetailsmodify();">After Adjustment
			</td>
			<td colspan="3">
				&nbsp;&nbsp;<%if(pay_mode.equalsIgnoreCase("AP")){ %>Advance<%}else{ %>Special<%} %>  Inv No & Date:&nbsp;<!-- HS20160722 -->
				<input type='text' name='adv_inv_no' size='12' maxlength='20' style='text-align: center;' value='<%=adv_inv_no%>' >
				&nbsp;&nbsp;&nbsp;
				<input type='text' name='adv_inv_dt' size='10' maxlength='10' style='text-align: center;' value='<%=adv_inv_dt%>' >
				<a href="javascript:show_calendar('forms[0].adv_inv_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
					<img src="../images/calendar.gif" width="25" height="22" border="0">
				</a>
			</td>
		</tr>
		<tr>
		<td colspan="6">
		<div id="afteradjustmentmodify">
		</div>
		</td>
		</tr>
		<%}%><% } %>
	<tr class="highlighttext">
		<td colspan="6" align="center">Tax Structure</td>
	</tr>
	
	<tr class="content1" valign="top">
		<td colspan="4">
			<div align="center">
				&nbsp;
			</div>
		</td>
		<td colspan="2">
			 <div align="center"><b>
				Gross Invoice AMT INR : &nbsp;<input type="text" name="double_final_gross_amt_inr" size="10" value="<%=gross_amt_inr%>" style="text-align:right" class="mkRdly" readOnly>
			</b></div>
		</td>
	</tr>
	
	<tr class="content1" valign="top">
		<td colspan="6">
			<div align="left">
				<font size="2"><b>&nbsp;Tax Structure Details&nbsp;:&nbsp;<%=tax_Structure_Dtl%></b></font>
				<input type="hidden" name="taxstructcd" value="<%=taxcd %>">
			</div>
		</td>
	</tr>

	<div id="adjustmenttax">
	</div>
 
	<%//System.out.println("---------------------BELOW-------------------------");
	//System.out.println("here---------------------------"+customer_Invoice_Tax_Code.size());
	double total_tax=0.0;
	for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)
		{	
		total_tax+=Double.parseDouble(""+(java.text.NumberFormat.getInstance().parse(""+customer_Invoice_Tax_Amt.elementAt(i))));
		%>
			<tr class="content1" valign="top">
				<td colspan="4">
					<div align="right">
						<font size="2"><b><%=customer_Invoice_Tax_Name.elementAt(i)%>&nbsp;(<%=customer_Invoice_Tax_Rate.elementAt(i)%>%):&nbsp;</b></font>
					</div>
					<input type="hidden" name="taxrate" id="taxrate<%=i %>" value="<%=customer_Invoice_Tax_Rate.elementAt(i)%>">
					<input type="hidden" name="taxratesize" id="taxratesize" value="<%=customer_Invoice_Tax_Code.size()%>">
					<input type="hidden" name="taxcodes" id="taxcodes" value="<%=customer_Invoice_Tax_Code.elementAt(i)%>">
				</td>
				<td colspan="2">
					<div align="center">
						&nbsp;<input type="text" size="10" name="tax_amts" id="tax_amts<%=i %>" value="<%=customer_Invoice_Tax_Amt.elementAt(i)%>" style="text-align:right" class="mkRdly" readOnly>
					</div>
				</td>
			</tr>
	<%	}%>
	
	<!-- ADDED FOR SERVICE TAX ADJUSTMENT -->
	<%if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){ %>
	
	<tr class='content1' valign='middle'>
	<td colspan="4" align="right">
	<%if(tax_Structure_Dtl.contains("GST")) { %>
		<b>Total GST:</b>
	<% } else { %>
		<b>Total Tax:</b>
	<% } %>
	</td>
	<td colspan="2"><div align="center"><input type="text" style="text-align:right" size="10" name="total_tax" value="<%=nf.format(total_tax) %>" class="mkrdly" readonly="readonly"></div>
	</td>
	</tr>
		<tr >
		<td colspan="6">
			<%if(!activity.equals("update")) { %>
				<div id="adjustment"></div>
			<%}else{ %>
				<div id="adjustmentmodify"></div>
			<%} %>
		</td>
		</tr>
<!-- xx	 -->
	<%if(!activity.equals("update")) { %>
	<tr>
		<td colspan="6">
			<div id="tax_adv_div">
			</div>
		</td>
	</tr>
	<% } else{ %>
		<%if(MODIFY_TAX_ADV_FLAG.equalsIgnoreCase("Y")){ %>
		<tr>
		<td colspan="6" id="tax_adv_div">
		<table>
		<tr class="highlighttext" valign="top">
		<td colspan="6">
				<div align="left">
					<font size="2"><b>&nbsp;&nbsp;Tax Adjust Payment</b></font>
				&nbsp;&nbsp;
				</div>
			</td>
		</tr>
		<tr class='title2'>
		<td width='25%'><div><font size='1.2px'><b>&nbsp;Balance Available </b></font></div></td>
		<td width='15%'><div align='center'><font size='1.2px'><b>Adjustment on Date</b></font></div></td>
		<td width='15%'><div align='center'><font size='1.2px'><b>Amount To be Adjusted</b></font></div></td>
		<td width='15%'><div align='center'><font size='1.2px'><b>Adjust</b></font></div></td>
		<td width='15%' colspan='2'><div align='center'><font size='1.2px'><b> Tax Amount </b></font></div></td>
		</tr>
		<tr class='content1' valign='middle'>
		<td ><div align='center'><b><input type='text' name='availablebalance_sbc' size='10' value='<%=Adjust_total_avail_bal_sbc%>' style='text-align:right' class='mkRdly' > INR<span id=''></span></b></div></td>
		<td ><div align='center'><b><%=invoice_date%> </b></div></td>
		<td ><div align='center'><b>
			<%if(activity.equalsIgnoreCase("update")){ %>
			<input type='text' name='adjustamt_sbc' id="adjustamt_sbc" size='10' style='text-align: right;' value='<%=MODIFY_SBC_ADV_ADJUSTMENT %>'  onchange='adjustmamountforadjust_sbc();'> INR
			<%}else{ %>
			<%
			if(Adv_submitted_flag.equalsIgnoreCase("Y")){ %>
			<input type='text' name='adjustamt_sbc' id="adjustamt_sbc" size='10' style='text-align: right;' value='0' onchange='adjustmamountforadjust_sbc();'> INR
			<%}else{ %>
			<input type='text' name='adjustamt_sbc' id="adjustamt_sbc" size='10' style='text-align: right;' value='0' class='mkRdly' onchange='adjustmamountforadjust_sbc();'> INR
			<%}} %>
			
			<span id=''></span>
		</b></div></td>
		<td ><div align='center'><b>
		<%if(activity.equalsIgnoreCase("update")){ %>
			<select name='adjustsign_sbc' id='adjustsign_sbc' onchange='adjustmamountforadjust_sbc();'>
			<option value='1'>-</option> <option value='2'>+</option></select>
			
		<%}else{ %>
		<%if(Adv_submitted_flag.equalsIgnoreCase("Y")){ %>
			<select name='adjustsign_sbc' id='adjustsign_sbc' onchange='adjustmamountforadjust_sbc();'>
			<option value='1'>-</option> <option value='2'>+</option></select>
		<%}else{ %>
			<select name='adjustsign_sbc' id='adjustsign_sbc' disabled="disabled" onchange='adjustmamountforadjust_sbc();'>
			<option value='1'>-</option> <option value='2'>+</option></select>
		<%}} %>
		</b></div></td>
		<td  colspan='2'><div align='center' ><b>
		<%if(activity.equalsIgnoreCase("update")){ %>
			<input type='text' name='gross_amt_inr_adjusted_sbc' size='10' value='<%=MODIFY_SBC_INV_AMT_INR%>' style='text-align:right' class='mkRdly'> INR
		<%}else{ %>
			<input type='text' name='gross_amt_inr_adjusted_sbc' size='10' value='' style='text-align:right' class='mkRdly'> INR
		<% }%>
		
		<span id='gross_amt_inr_adjusteddiv_sbc'></span>
		</b></div></td>
		</tr>		
		<tr class='content1' valign='middle'>
			<td colspan='6'><div align='right' style='padding-right: 33px'><span id='submited_text_sbc'></span><input type='button' name='advance_sbc_submit' value='Save' onclick='saveadvance_sbc_data();'></td>
		</tr>
		</table>
		</td>
		</tr>	
		<%} else{%>
			<tr>
			<td colspan="6" id="sbc_adv_div">
			</td>
			</tr>
			<tr>
		<td colspan="6">
			<div id="tax_adv_div">
			</div>
		</td>
	</tr>
		<%} %>
		<tr class='content1' valign='middle'>
		<td colspan="6">
		&nbsp;
		</td>
		</tr>
	<% } %>
	<% } %>
	<!-- END -->
	
	<tr class="highlighttext" style="height: 10px">
		<td colspan="6">
			<input type="hidden" name="Adjust_total_avail_bal_tax" value="<%=Adjust_total_avail_bal_tax %>"/>
			<input type="hidden" name="MODIFY_TAX_ADV_ADJUSTMENT" value="<%=MODIFY_TAX_ADV_ADJUSTMENT %>"/>
			<input type="hidden" name="MODIFY_TAX_INV_AMT_INR" value="<%=MODIFY_TAX_INV_AMT_INR %>"/>
			
			<input type="hidden" name="Adjust_total_avail_bal_sbc" value="<%=Adjust_total_avail_bal_sbc %>"/>
			<input type="hidden" name="MODIFY_SBC_ADV_ADJUSTMENT" value="<%=MODIFY_SBC_ADV_ADJUSTMENT %>"/>
			<input type="hidden" name="MODIFY_SBC_INV_AMT_INR" value="<%=MODIFY_SBC_INV_AMT_INR %>"/>
			
			<input type="hidden" name="Adjust_total_avail_bal_kkc" value="<%=Adjust_total_avail_bal_kkc %>"/>
			<input type="hidden" name="MODIFY_KKC_ADV_ADJUSTMENT" value="<%=MODIFY_KKC_ADV_ADJUSTMENT %>"/>
			<input type="hidden" name="MODIFY_KKC_INV_AMT_INR" value="<%=MODIFY_KKC_INV_AMT_INR %>"/>
		</td>
	</tr>
	
	 <!-- END -->
	<tr class="content1" valign="top">
		<td colspan="4">
			<div align="right">
				<font size="2"><b>Net Invoice Amount INR:&nbsp;</b></font>
			</div>
		</td>
		<td colspan="2">
			<div align="center">
				&nbsp;<input type="text" size="10" name="net_amt_inr" id="net_amt_inr" value="<%=customer_Invoice_Net_Amt_INR%>" style="text-align:right" class="mkRdly" readOnly>
			</div>
		</td>
	</tr>
	<tr class="content1" valign="top">
		<td colspan="6">
			<div align="center">
				&nbsp;
			</div>
		</td>
	</tr>
	<tr valign="top">
		<td class="title2" colspan="1">
			<div align="left">
				&nbsp;Remark-1&nbsp;:
			</div>
		</td>
		<td class="content1" colspan="5">
			<div align="left">
				<textarea name="remark_1" cols="80" rows="3"><%=remark_1%></textarea>
			</div>
		</td>
	</tr>
	<%	if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		{	%>
			<tr valign="top">
				<td class="title2" colspan="1">
					<div align="left">
						<% if(contract_type.equalsIgnoreCase("R")){%> 
							&nbsp;R-gas
						<%}else if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C")){%>
							&nbsp;LTCORA-gas	 
						<%}%>
						&nbsp;Remark&nbsp;:
					</div>
				</td>
				<td class="content1" colspan="5">
					<div align="left">
						<textarea name="remark_3" cols="80" rows="3"><%=remark_3%></textarea>
					</div>
				</td>
			</tr>
	<%	}	%>	
	<%	if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		{	%>
	<tr valign="top">
		<td class="title2" colspan="1">
			<div align="left">
				&nbsp;Remark-2&nbsp;:
			</div>
		</td>
		<td class="content1" colspan="5">
			<div align="left">
				<textarea name="remark_2" cols="80" rows="3"><%=remark_4%></textarea>
			</div>
		</td>
	</tr>
	<%}else{ %>
	<tr valign="top">
		<td class="title2" colspan="1">
			<div align="left">
				&nbsp;Remark-2&nbsp;:
			</div>
		</td>
		<td class="content1" colspan="5">
			<div align="left">
				<textarea name="remark_2" cols="80" rows="3"><%=remark_2%></textarea>
			</div>
		</td>
	</tr>
	<%} %>
	<%if(AdjustFlagForMst.equalsIgnoreCase("Y")){ %>
	<tr valign="top">
		<td class="title2" colspan="1">
			<div align="left">
				&nbsp;Remark-3&nbsp;:
			</div>
		</td>
		<td class="content1" colspan="5">
			<div align="left">
				<textarea name="remark_adj" cols="80" rows="3"><%=modifyadjremark%></textarea>
			</div>
		</td>
	</tr>
	<%} %>
	<tr class="title2" valign="top">
		<td colspan="3">
			<div align="left">
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="reSetPage" value="Reset" onClick="resetPageContent('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=mapping_id %>');">
			</div>
		</td>
		<td colspan="3">
			<div align="right">
				<input type="hidden" name="option" value="submitBillingDetails_TAX_ADJ">
				<input type="hidden" name="exch_rate_cd" value="<%=exchg_rate_cd%>">
				<input type="hidden" name="exch_rate_dt" value="<%=invoice_date%>">
				<input type="hidden" name="start_index" value="0">
				<input type="hidden" name="end_index" value="0">
				<input type="hidden" name="exchg_rate_cal_method" value="<%=exchg_rate_cal_method%>">
				<input type="hidden" name="inv_dt" value="<%=bill_period_end_dt%>">
				<input type="hidden" name="contact_person_nm" value="">
				<input type="hidden" name="bill_cycle" value="<%=bill_cycle%>">
				<input type="hidden" name="year" value="<%=year%>">
				<input type="hidden" name="month" value="<%=month%>">
				<input type="hidden" name="write_permission" value="<%=write_permission%>">
	    		<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
	    		<input type="hidden" name="print_permission" value="<%=print_permission%>">
	    		<input type="hidden" name="check_permission" value="<%=check_permission%>">
	    		<input type="hidden" name="authorize_permission" value="<%=authorize_permission%>">
	    		<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
	    		<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
			<%	
				if(exchg_rate_cal_method.equalsIgnoreCase("D"))
				{	%>
					<%	//if(flag.trim().equalsIgnoreCase("F"))
						//{	%>
						<!-- 	<input type="button" name="verify" value="Verify Before Submit" onClick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','T');"> -->
<!--					<input type="button" name="verify" id="verify" value="Verify Before Submit" onClick="togglestatus();">-->
				<input type="button" name="verify" id="verify" value="Verify Before Submit" onClick="togglestatus('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=mapping_id%>','<%=print_permission%>','<%=contact_addr_flag%>','<%=GST_INVOICE_SEQ_NO%>');">
					<%//	}
						//else
						//{	%>
							<input type="button" name="save" id="save"  value="Submit" onClick="doSubmit('12','D','<%=invoice_Period_Dates.size()%>');">
					<%	//}	%>			
			<%	}
				else if(exchg_rate_cal_method.equalsIgnoreCase("A"))
				{	%>
							<input type="button" name="verify" id="verify" value="Verify Before Submit" onClick="togglestatus('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=mapping_id%>','<%=print_permission%>','<%=contact_addr_flag%>','<%=GST_INVOICE_SEQ_NO%>');">
							<input type="button" name="save" id="save"  value="Submit" onClick="doSubmit('<%=invoice_Period_Dates.size()%>','A','<%=invoice_Period_Dates.size()%>');">
			<%	}	%>
				&nbsp;&nbsp;&nbsp;&nbsp;
				
				<script>
					function togglestatus(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,
							customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,
							month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,
							mapping_id,print_permission,contact_addr_flag,GST_INVOICE_SEQ_NO)
					{
						//alert(contract_type);
						var flag=true;
						var msg="";
						var ad=document.forms[0].adjust;
						var ad_flag,dis_flag,inr_flag;
						var dis=document.forms[0].discount_check;
						var inr=document.forms[0].sale_price_inr_check;
						var contact_person=document.forms[0].contact_person.value;
						var taxstructcd=document.forms[0].taxstructcd.value;
						var gross_amt_inr=document.forms[0].double_final_gross_amt_inr.value;
						var invadjustcur="";
						var exchrate=document.forms[0].exch_rate_val.value;
						var inv_dt=document.forms[0].invoice_date.value
						var due_dt=document.forms[0].due_date.value;
						var hlpl_inv_no_prev=hlpl_inv_seq_no;
						//alert(hlpl_inv_no_prev);
						//document.forms[0].hlpl_inv_no_prev.value;
						if(exchrate=='' || exchrate==" ")
						{
							flag=false;
							msg+="Select proper Exchange Rate.. \n";
							
						}
						if(contact_person=='0')
						{
							flag=false;
							msg+="Select proper Contact Person.. \n";
						}
						if(ad!=undefined){
							if(ad[0].checked){
								ad_flag="Y";
							}else{
								ad_flag="N";
							}
						}
							if(ad_flag=="Y")
							{
								 invadjustcur=document.forms[0].currency.value;
								 if(document.forms[0].adjustamt.value=='')
								 {
									flag=false;
									msg+="Enter proper Amount.. \n";
								 }
								 if(document.forms[0].adv_inv_no.value=='')
								 {
									flag=false;
									msg+="Enter proper ADV INV No.. \n";
								 }
								 if(document.forms[0].adv_inv_dt.value=='')
								 {
									flag=false;
									msg+="Enter proper ADV INV Dt.. \n";
								 }
							}
							
							//alert(contact_person);
						if(dis!=undefined)
							{
							if(dis.checked){
								dis_flag="Y";}else{
									dis_flag="N";
								}
							}
						if(inr!=undefined)
							{
							if(inr.checked){
								inr_flag="Y";}else {
									inr_flag="N";
								}
							}
						
						if(flag==true){
							document.getElementById("save").style.visibility="visible";
							var pay_mm=document.forms[0].pay_type1.value;
							var advadjval='';
							if(document.forms[0].adjustdetails[1].checked){  
								advadjval=document.forms[0].adjustdetails[1].value;
							}else{  
								advadjval=document.forms[0].adjustdetails[0].value;  
							}
								newWindow2 = window.open("rpt_preview_invoice_tax_adj.jsp?GST_INVOICE_SEQ_NO="+GST_INVOICE_SEQ_NO+"&invadjustcur="+invadjustcur+"&hlpl_inv_no_prev="+hlpl_inv_no_prev+"&gross_amt_inr="+gross_amt_inr+"&taxstructcd="+taxstructcd+"&contact_person="+contact_person+"&contact_addr_flag="+contact_addr_flag+"&ad_flag="+ad_flag+"&dis_flag="+dis_flag+"&inr_flag="+inr_flag+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&inv_dt="+inv_dt+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&mapping_id="+mapping_id+"&print_permission="+print_permission+"&pay_mm="+pay_mm+"&advadjval="+advadjval,"Rpt_PreviewInvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,status=1,menubar=1");
						}
						else
						{
							alert(msg);
						}
					}
				</script>
			<%	if(!contract_type.trim().equalsIgnoreCase("R") && !contract_type.trim().equalsIgnoreCase("T") && !contract_type.trim().equalsIgnoreCase("C"))
				{	%>
					<input type="hidden" name="remark_3" value="">
			<%	}	%>	
			</div>
		</td>
	</tr>
</table>
</div>
<table>
	<tr>
		<td>
			<input type="hidden" name="SBC_Flag_Service" value="<%=SBC_Flag_Service %>">
			<input type="hidden" name="SBC_ADV_ADJUST_CUR" value="<%=SBC_CUR_Service %>">
			<input type="hidden" name="SBC_AMT_Service" value="<%=SBC_AMT_Service %>">
			<input type="hidden" name="KKC_Flag_Service" value="<%=KKC_Flag_Service %>">
			<input type="hidden" name="KKC_ADV_ADJUST_CUR" value="<%=KKC_CUR_Service %>">
			<input type="hidden" name="KKC_AMT_Service" value="<%=KKC_AMT_Service %>">
			<input type="hidden" name="TAX_ADV_ADJUST_FLAG_MST" value="<%=TAX_ADV_ADJUST_FLAG_MST %>">
			<input type="hidden" name="TAX_ADV_ADJUST_CUR" value="<%=TAX_ADV_ADJUST_CUR %>">
			<input type="hidden" name="activity" value="<%=activity%>">
			<input type="hidden" name="hlpl_invoice_sequence_no" value="<%=hlpl_invoice_sequence_no%>">
			<input type="hidden" name="hlpl_inv_seq_no2" value="<%=hlpl_inv_seq_no2%>">
			<input type="hidden" name="inv_financial_year" value="<%=inv_financial_year%>">
			<input type="hidden" name="gross_amt_usd2" value="<%=gross_amt_usd2%>">
			<input type="hidden" name="gross_amt_inr2" value="<%=gross_amt_inr2%>">
			<input type="hidden" name="exchg_rate_ind" value="<%=exchg_rate_ind%>">
			<input type="hidden" name="offspec_flag" value="<%=offspec_flag%>">
			<input type="hidden" name="flag" value="<%=flag %>">
			<input type="hidden" name="mapping_id" value="<%=mapping_id%>">
			<input type="hidden" name="hiddenadvnclength" value="0">
			<div id="hiddenadvancedetails">
			</div>
			<input type="hidden" name="AdjustFlagForMst" value="<%=AdjustFlagForMst%>">
			<input type="hidden" name="modifyadjflag" value="<%=modifyadjflag%>">
			<input type="hidden" name="tariffflag" value="<%=TariffFlag%>">
			<input type="hidden" name="balavailablecheckforsubmit" value="">
			<input type="hidden" name="Adjust_amt_mst" value="<%=Adjust_amt_mst %>">
			<input type="hidden" name="Adjust_cur_mst" value="<%=Adjust_cur_mst %>">
			<input type="hidden" name="Adjust_total_avail_bal" value="<%=Adjust_total_avail_bal %>">
			
			<%if(activity.equalsIgnoreCase("update")) {%>
				<input type="hidden" name="modifyadjsignhid" value="<%=modifyadjsign%>">
				<input type="hidden" name="modifyadjcurrencyhid" value="<%=modifyadjcurrency%>">
				<input type="hidden" name="modifyadjrecvflaghid" value="<%=modifyadjrecvflag%>">
			<%} 
			//System.out.println("here----------22-----------------"+customer_Invoice_Tax_Code.size());
			else if(activity.equalsIgnoreCase("insert")){%>
			<input type="hidden" name="modifyadjsignhid" value="<%=modifyadjsign%>">
				<input type="hidden" name="modifyadjcurrencyhid" value="<%=modifyadjcurrency%>">
				<input type="hidden" name="modifyadjrecvflaghid" value="<%=modifyadjrecvflag%>">
			<%} %>
			<input type="hidden" name="taxsize" value="<%=customer_Invoice_Tax_Code.size()%>">
			<input type="hidden" name="invoice_sales_price_inr" value="<%=invoice_sales_rate_inr %>">
			<input type="hidden" name="call_flag_sale_price" value="N">
			<%//System.out.println("-----------tnuocsid-------"+discount_flag_apply);
			if(discount_flag_apply.equalsIgnoreCase("Y") ){%>
			<input type="hidden" name="discount_flag_apply" value="Y">
			<input type="hidden" name="Discount_flag_Setter" value="Y">
			<%}else{ %>
			<input type="hidden" name="discount_flag_apply" value="N">
			<input type="hidden" name="Discount_flag_Setter" value="N">
			<%} %>
			<%if(TariffFlag.equalsIgnoreCase("Y") ){%>
			<input type="hidden" name="Tariff_flag_Setter" value="Y">
			<%}else{ %>
			<input type="hidden" name="Tariff_flag_Setter" value="N">
			<%} %>
			<input type="hidden" name="discount_val_deducted" value="<%=discount_val_deducted %>">
			<input type="hidden" name="original_rate" value="<%=invoice_Sales_Rate %>">
			<input type="hidden" name="discount_count" value="<%=discount_count %>">
			<input type="hidden" name="entrypoint" value="<%=entrypoint %>">
			<input type="hidden" name="Tariff_flag_Mst" value="<%=Tariff_flag_Mst %>">
			<input type="hidden" name="Discount_flag_Mst" value="<%=Discount_flag_Mst %>">
			<input type="hidden" name="page_refresh_flag" value="<%=page_refresh_flag %>">
			<input type="hidden" name="call_on_load_hid" value="">
			<input type="hidden" name="call_on_load_hid2" value="">
			<%
			if(Integer.parseInt(discount_count)==1 && activity.equals("update")){
			%>
			<% if(exchg_rate_cal_method.equalsIgnoreCase("D"))
			{%>
			<script>
			//alert("hi");
			applydiscount(document.forms[0].discount_check,'<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F','load'),discount_setter(document.forms[0].discount_check);
			</script>
			<%}else if(exchg_rate_cal_method.equalsIgnoreCase("A")){%>
			<script>
			applydiscount(document.forms[0].discount_check,'<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=invoice_Period_Dates.size()%>','<%=invoice_Period_Dates.size()%>','F','load'),discount_setter(document.forms[0].discount_check);
			</script>
			<%} %>
			<%} %>
			<%//if(!activity.equals("update")) 
			{%>
			 <%if(AdjustFlagForMst.equalsIgnoreCase("Y")){ %>
			<script>
			//alert("here");
			//alert(document.forms[0].Adv_submitted_flag);
			var	Adv_submitted_flag1=document.forms[0].Adv_submitted_flag.value;
			
			 if(Adv_submitted_flag1=='Y')
			 {
			 	document.forms[0].adjust[0].checked=true;
				displaydetailsmodify();
			 } 
			
			</script>
			<%} %>
			<%} %>
		</td>
	</tr>
</table>	
<input name="contract_type_tax" type="hidden" value="<%=contract_type%>"> 
<input type="hidden" name="pay_type1" value="<%=pay_mode%>">
<input type="hidden" name="advflg" value="<%=advanceAdjFlg%>">
<input type="hidden" name="inrAmt1" value="">
</form>
</body>
</html>