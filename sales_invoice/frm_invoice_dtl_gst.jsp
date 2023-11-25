<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title> DLNG </title>

<!-- For accordion  -->
<link rel="stylesheet" href="../responsive/w3/w3.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css" >
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="../css/pt_sans.css">

<script type="text/javascript" src="../js/date-picker.js"></script>
<!-- <script type="text/javascript" src="../js/aris.js"></script> -->
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
<link rel="stylesheet" href="../responsive/toastr/toastr.min.css">
<script type="text/javascript">

function checkWithInvoice(obj) {
	var invoice_dt = document.forms[0].invoice_date.value;
	var due_dt = document.forms[0].due_date.value;
	
	var value = compareDate(invoice_dt,due_dt);
	if(value==1) {
		alert("Due Date Can Not Be Less Then Invoice Date!!!");
		obj.value='';
		obj.focus();
	}
}
var newWindow;
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
document.forms[0].discount_val_deducted.value=deducted_disc;
document.forms[0].entrypoint.value=entry;
	if(obj1.checked)
	{	document.forms[0].discount_flag_apply.value='Y';
		setExchangeRate(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,hlpl_inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_mthd,sz,period_sz,flag);
	}
	else
	{	
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
var tariffflag=document.forms[0].tariffflag.value;
var modifyadjflag=document.forms[0].modifyadjflag.value;
if(modifyadjflag=='Y')
{ 
if(tariffflag=='Y') 
	{
		document.forms[0].call_on_load_hid.value="call";
	}
	displaydetailsmodify();
}
else if(tariffflag=='Y') 
	{
		displaysalepricedetails('L');
	}
	
}
function caluclate_tax_details_ajax(grossamt,taxcd)
{
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
							 	for(var i=0;i<len1;i++)
							 	{
					 					var temp="cust_tax_amt"+i;
					 					var temptax_amt=document.getElementById(temp).value;  
					 					var tmp="tax_amts"+i;
					 					document.getElementById(tmp).value=temptax_amt;
								 }
								var net_amt_inr=document.getElementById("invoice_inr_amt_adjusted").value;
								document.getElementById("net_amt_inr").value=net_amt_inr;
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
var obj=document.forms[0].sale_price_inr_check;
var contract_type=document.forms[0].contract_type.value;
var invoice_sales_price_inr=document.forms[0].invoice_sales_price_inr.value;
var exchrate=document.forms[0].exch_rate_val.value;
var sale_price=document.forms[0].sale_price.value;
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
var total_qty=document.forms[0].total_qty.value;
if(entry=='L' &&  document.forms[0].activity.value=='insert' && document.forms[0].page_refresh_flag.value=='N')
{	
}
else
{
	if(obj.checked){
	if(exchrate=='' || exchrate==" ")
	{
		alert("Please select the proper exchange rate..!!");
		obj.checked=false;
	} 
	else
	{
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
				  		  var url="../advance_payment/frm_invoice_saleprice_ajax.jsp?contract_type="+contract_type+"&invoice_sales_price_inr="+invoice_sales_price_inr+"&exchrate="+exchrate+"&sale_price="+sale_price+"&total_qty="+total_qty+"&offspec_flag="+offspec_flag+"&offspec_rate="+offspec_rate+"&offspec_qty="+offspec_qty;
				  		  request21.open("POST",url);
				  		  request21.onreadystatechange = function() 
				    	  {
				   			 if(request21.readyState==4)
				     		 {
					 				var a=document.getElementById("salepricediv").innerHTML=request21.responseText; 
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
						var taxcd=document.forms[0].taxstructcd.value;
						caluclate_tax_details_ajax(grossamt,taxcd);
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
function adjustmamountforadjust()
{
	var adjustamt=document.forms[0].adjustamt.value;
	var advanceamt=document.forms[0].availablebalance.value;
	var negflag=false;
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
				{	
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
						{ 
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
							var adjustamtinr=parseFloat(adjustamt)*parseFloat(exchrate);//added new
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
						var calculatedamtinr=parseFloat(calculatedamt)*parseFloat(exchrate);
						if(document.forms[0].contract_type_tax.value=='C') {
							document.forms[0].gross_amt_inr_adjusted.value=round(calculatedamt,0);
							document.forms[0].priceINR2.value=round(calculatedamtinrnew,0);
							document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].priceINR2.value,0);
						} else {
							document.forms[0].gross_amt_inr_adjusted.value=round(calculatedamt,2);
							document.forms[0].priceINR2.value=round(calculatedamtinrnew,2);
							document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].priceINR2.value,2);
						}
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
						if(document.forms[0].contract_type_tax.value=='C') {
							document.forms[0].gross_amt_inr_adjusted.value=round(calculatedamt,0);
							document.forms[0].priceINR2.value=round(calculatedamt,0);
							document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].priceINR2.value,0);
						} else {
							document.forms[0].gross_amt_inr_adjusted.value=round(calculatedamt,2);
							document.forms[0].priceINR2.value=round(calculatedamt,2);
							document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].priceINR2.value,2);
						}
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
						caluclate_tax_details_ajax(grossamt,taxcd);
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
				document.getElementById(tmp).value="";
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
				document.getElementById(tmp).value="";
			}
			document.getElementById("net_amt_inr").value="";
	}
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
	
// 	window.opener.refreshPageFromChild(month, year, bill_cycle, write_permission, delete_permission, print_permission, approve_permission, audit_permission, check_permission, authorize_permission);
	window.close();
}
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
	setExchangeRate(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,hlpl_inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,sz,period_sz,flag);
}
function resetPageContent(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,hlpl_inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,mapping_id,sn_start_dt,sn_end_dt)
{
	var invoice_date = document.forms[0].inv_dt.value;
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
	var date_flag = document.forms[0].date_flag.value;
	
	location.replace("frm_invoice_dtl_gst.jsp?date_flag="+date_flag+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&invoice_date="+invoice_date+"&page_refresh_flag=N&msg="+msg+"&activity="+activity+"&hlpl_invoice_sequence_no="+hlpl_invoice_sequence_no+"&hlpl_inv_seq_no2="+hlpl_inv_seq_no2+"&inv_financial_year="+inv_financial_year+"&gross_amt_usd2="+gross_amt_usd2+"&gross_amt_inr2="+gross_amt_inr2+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&mapping_id="+mapping_id+"&sn_start_dt="+sn_start_dt+"&sn_end_dt="+sn_end_dt);
}

function setExchangeRate(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,hlpl_inv_seq_no,month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_mthd,sz,period_sz,flag,sn_start_dt,sn_end_dt)
{
  
   sz=1;
   var check_flag=flag;
   var discount_flag_apply='N';
   var discount_deducted_val=0;
   var mapping_id=document.forms[0].mapping_id.value;
   // Applicable Discount
   if(document.forms[0].Discount_flag_Mst.value=='Y'){
	 discount_deducted_val=document.forms[0].discount_val_deducted.value;
	 discount_flag_apply=document.forms[0].discount_flag_apply.value;}
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
	var invoice_date = document.forms[0].inv_dt.value;
	var contact_person = document.forms[0].contact_person.value;
	var offspec_flag = document.forms[0].offspec_flag.value;
	var particular_date = '';
	var exchg_rate_cal_method = exchg_rate_cal_mthd;
	var exch_rate_cd = "0";
	var exchg_rate_index = "0";
	var activity = document.forms[0].activity.value;
	var hlpl_invoice_sequence_no = document.forms[0].hlpl_invoice_sequence_no.value;
	var hlpl_inv_seq_no2 = document.forms[0].hlpl_inv_seq_no2.value;
	var inv_financial_year = document.forms[0].inv_financial_year.value;
	var gross_amt_usd2 = document.forms[0].gross_amt_usd2.value;
	var gross_amt_inr2 = document.forms[0].gross_amt_inr2.value;
// 	alert('here1')
	if(exchg_rate_cal_method=='D')
	{
		particular_date = document.forms[0].particular_date.value;
	}
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
	var invoice_date = document.forms[0].invoice_date.value;
	var due_date = document.forms[0].due_date.value;
	var gross_amt_usd = parseFloat('0');
	var gross_amt_inr = parseFloat('0');
//	size = 2; //Hiren_20200321
	if(exchg_rate_cal_method=='D')
	{
		//for(i=0; i<size; i++)
		{//alert(size);alert(document.forms[0].exch_rate.length);
			if(document.forms[0].exch_rate.checked)
			{
				document.forms[0].exch_rate_cd.value = document.forms[0].exch_rate_code.value;
				document.forms[0].exch_rate_val.value = document.forms[0].exch_rate_value.value;
				
					document.forms[0].exch_rate_dt.value = document.forms[0].previous_dt.value;
					
			}
		}
	}
	//alert(document.forms[0].previous_dt.value);
	var contact_person = document.forms[0].contact_person.value;
	var contact_person_nm = "";
	if(contact_person==null || contact_person=='' || contact_person==' ' || contact_person=='0')
	{
	}
	else
	{
		contact_person_nm = document.forms[0].contact_person[document.forms[0].contact_person.selectedIndex].innerText;
		document.forms[0].contact_person_nm.value = contact_person_nm;
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
		document.forms[0].exch_rate_val.value = ""+round(avg_rate,4);
	}
	document.forms[0].gross_amt_usd.value = ""+round(gross_amt_usd,2);
	document.forms[0].raw_amt_usd.value=""+round(gross_amt_usd,2);
	gross_amt_usd = round(gross_amt_usd,2);
	if(contract_type=='C') {
		document.forms[0].gross_amt_inr.value = ""+round(gross_amt_inr,0);
		document.forms[0].raw_amt_inr.value=""+round(gross_amt_inr,0);
		gross_amt_inr = round(gross_amt_inr,0);	
	} else {
		document.forms[0].gross_amt_inr.value = ""+round(gross_amt_inr,2);
		document.forms[0].raw_amt_inr.value=""+round(gross_amt_inr,2);
		gross_amt_inr = round(gross_amt_inr,2);	
	}
	
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
	var date_flag = document.forms[0].date_flag.value;
	var truck_cd = document.forms[0].truck_cd.value;
	if(discount_flag_apply=='N')
	{
		discount_counte='0';
	}
	if(flag)
	{
		location.replace("frm_invoice_dtl_gst.jsp?date_flag="+date_flag+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&TariffFlag="+TariffFlag+"&Adv_submitted_flag="+Adv_submitted_flag+"&adv_inv_no="+adv_inv_no+"&adv_inv_dt="+adv_inv_dt+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exch_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&invoice_date="+invoice_date+"&contact_person="+contact_person+"&particular_date="+particular_date+"&gross_amt_usd="+gross_amt_usd+"&gross_amt_inr="+gross_amt_inr+"&exch_rate_val="+exch_rate_val+"&exchg_rate_index="+exchg_rate_index+"&page_refresh_flag=Y&msg="+msg2+"&activity="+activity+"&hlpl_invoice_sequence_no="+hlpl_invoice_sequence_no+"&hlpl_inv_seq_no2="+hlpl_inv_seq_no2+"&inv_financial_year="+inv_financial_year+"&gross_amt_usd2="+gross_amt_usd2+"&gross_amt_inr2="+gross_amt_inr2+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&offspec_rate="+offspec_rate+"&flag="+check_flag+"&discount_flag_apply="+discount_flag_apply+"&discount_count="+discount_count+"&entrypoint="+entrypoint+"&mapping_id="+mapping_id+"&truck_cd="+truck_cd+"&onload_flg=Y&sn_start_dt="+sn_start_dt+"&sn_end_dt="+sn_end_dt);
		
	}
	else
	{
		alert(msg);
		if(document.forms[0].discount_check!=undefined){
		if(document.forms[0].discount_check.checked)
		{
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
	}
	else
	{
		contact_person_nm = document.forms[0].contact_person[document.forms[0].contact_person.selectedIndex].innerText;
		document.forms[0].contact_person_nm.value = contact_person_nm;
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
	
	document.forms[0].gross_amt_usd.value = ""+round(gross_amt_usd,2);
	document.forms[0].raw_amt_usd.value=""+round(gross_amt_usd,2);
	if(contract_type=='C') {
		document.forms[0].gross_amt_inr.value = ""+round(gross_amt_inr,0);
		document.forms[0].raw_amt_inr.value=""+round(gross_amt_inr,0);
	} else {
		document.forms[0].gross_amt_inr.value = ""+round(gross_amt_inr,2);
		document.forms[0].raw_amt_inr.value=""+round(gross_amt_inr,2);
	}
	
}
function doSubmit(sz,exchg_rate_cal_mthd,period_sz,tax_sz)
{
	//Hiren_20201221 As per mahesh Sir Req. 
	var truck_driver = document.forms[0].truck_driver.value;
	var truck_nm = document.forms[0].truck_nm.value;
	// 
	var c_form_flg = document.forms[0].c_form_flg.value;
	var discount_deducted_val=0;
	var discount_flag_apply="N";
	// Applicable Discount
	if(document.forms[0].Discount_flag_Mst.value=='Y'){
	 discount_deducted_val=document.forms[0].discount_val_deducted.value;
	 discount_flag_apply=document.forms[0].discount_flag_apply.value;}
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
		if(document.forms[0].priceINR2.value=='' || document.forms[0].gross_amt_inr_adjusted.value=='' || document.forms[0].availablebalance.value=='' || document.forms[0].adjustamt.value=='')
		{
			flag=false;
			msg+="Enter proper advance ajustment amoumt or select No for adjust advance payment !! \n";
		}
		if(document.forms[0].adv_inv_no.value=='' || document.forms[0].adv_inv_no.value==' ')//added new
		{
			flag=false;
			msg+="Enter proper Advance Invoice No!!\n";
		}
		if(document.forms[0].adv_inv_dt.value=='' || document.forms[0].adv_inv_dt.value==' ')//added new
		{
			flag=false;
			msg+="Enter proper Advance Invoice Date!!!\n";
		}
		var tempbalsub=document.forms[0].balavailablecheckforsubmit.value;
		tempbalsub1=parseFloat(tempbalsub);
		if(tempbalsub1<=0)
		{flag=false;
			msg+="Please select No for adjust advance payment as there is zero available balance for this customer..!!\n";
		}
		alert("You are adjusting "+document.forms[0].adjustamt.value+" "+document.forms[0].currency_text.value+" !!!");
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
	var invoice_date = document.forms[0].invoice_date.value;
	var due_date = document.forms[0].due_date.value;
	var gross_amt_usd = parseFloat('0');
	var gross_amt_inr = parseFloat('0');
	if(exchg_rate_cal_method=='D')
	{
		for(i=0; i<size; i++)
		{
			if(document.forms[0].exch_rate.checked)
			{
				document.forms[0].exch_rate_cd.value = document.forms[0].exch_rate_code.value;
				document.forms[0].exch_rate_val.value = document.forms[0].exch_rate_value.value;
				
					document.forms[0].exch_rate_dt.value = document.forms[0].previous_dt.value;
				
			}
		}
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
	/* var contact_person = document.forms[0].contact_person.value;
	var contact_person_nm = "";
	if(contact_person==null || contact_person=='' || contact_person==' ' || contact_person=='0')
	{
		msg += "Please Select The Proper Contact Person !!!\n";
		flag = false;
	}
	else
	{
		contact_person_nm = document.forms[0].contact_person[document.forms[0].contact_person.selectedIndex].innerText;
		document.forms[0].contact_person_nm.value = contact_person_nm;
	} */
	if(invoice_date==null || invoice_date=='' || invoice_date=='0' || invoice_date==' ' || invoice_date=='  ')
	{
		msg += "Please Enter Correct Invoice Date, Then Submit The Billing Details !!!\n";
		flag = false;
	}
	if(due_date==null || due_date=='' || due_date=='0' || due_date==' ' || due_date=='  ')
	{
		msg += "Please Enter Correct Due Date, Then Submit The Billing Details !!!\n";
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
	
	document.forms[0].gross_amt_usd.value = ""+round(gross_amt_usd,2);
	document.forms[0].raw_amt_usd.value=""+round(gross_amt_usd,2);
	var contract_type = document.forms[0].contract_type_tax.value;
	if(contract_type=='C') {
		document.forms[0].gross_amt_inr.value = ""+round(gross_amt_inr,0);
		document.forms[0].raw_amt_inr.value=""+round(gross_amt_inr,0);
	} else {
		document.forms[0].gross_amt_inr.value = ""+round(gross_amt_inr,2);
		document.forms[0].raw_amt_inr.value=""+round(gross_amt_inr,2);
	}
	
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
	var flag_gstin = false;
	
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
		}
		if(tax_size>1)
		{
			for(var i=0;i<parseInt(tax_size);i++)
			{
				var no=document.forms[0].vstat_no[i].value;
				var cd = document.forms[0].vstat_cd[i].value;
				if(no==' ' || no=='')
				{
						msg+="Customer "+document.forms[0].vstat_nm[i].value+" is not Available!!! \n";
						flag_tax=true;
						var nm=document.forms[0].vstat_nm[i].value;
						if(nm=='PAN No.')
						{
							panflagc=true;
						}
						if(cd=='1008') {
							flag_gstin = true;
						}
				}
				else
				{
					count_t2++;
				}	
			}	
		}
		else
		{
			{
				var no=document.forms[0].vstat_no.value;
				var cd = document.forms[0].vstat_cd.value;
				if(no==' ' || no=='')
				{
						msg+="Customer "+document.forms[0].vstat_nm.value+" is not Available!!! \n";
						flag_tax=true;
						var nm=document.forms[0].vstat_nm.value;
						if(nm=='PAN No.')
						{
							panflagc=true;
						}
						if(cd=='1008') {
							flag_gstin = true;
						}
				}
				else
				{
					count_t2++;
				}	
			}
		}
	}
	else
	{
		flags=true;
		if(tax_size>1)
		{
			for(var i=0;i<parseInt(tax_size);i++)
			{
				var no=document.forms[0].vstat_no[i].value;
				if(no==' ' || no=='')
				{
						msg+="Customer "+document.forms[0].vstat_nm[i].value+" is not Available!!! \n";
						flag_tax=true;
						var nm=document.forms[0].vstat_nm[i].value;
						if(nm=='PAN No.')
						{
							panflagc=true;
						}
				}
				else
				{
					count_t2++;
				}	
			}	
		}
		else
		{
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
				}
				else
				{
					count_t2++;
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
		}	
		
		if(contact_Suppl_GST_DT=='' || contact_Suppl_GST_NO=='')
		{
			msg+="Supplier GST NO/DT is not Available!!! \n";
			flag_tax=true;
		}
		else
		{
			count_t1++;
		}
	}
	var taxsize=document.forms[0].taxsize.value;
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
// 	if(!flag_stop)
// 		{
// 			if(flag_tax)
// 			{
// 				alert(msg);
// 			}
// 		}

///
	
	var contact_Suppl_GSTIN_NO = document.forms[0].contact_Suppl_GSTIN_NO.value;
	var contact_Suppl_State_Code = document.forms[0].contact_Suppl_State_Code.value;
	var contact_Suppl_Sac_Code = document.forms[0].contact_Suppl_Sac_Code.value;
	var contact_Customer_State_Code = document.forms[0].contact_Customer_State_Code.value;
	var tax_gst = document.forms[0].tax_gst.value;
	if(tax_gst=="true") {
		if(contact_Suppl_GSTIN_NO=='') {
			flag_gstin = true; 
			msg += 'Supplier GSTIN No Is Not Available!!!\n';
		}
		if(contact_Suppl_State_Code=='') {
			flag_gstin = true;
			msg += 'Supplier State Code Is Not Available!!!\n';
		}
		if(contact_Suppl_Sac_Code=='') {
			flag_gstin = true;
			msg += 'Supplier Sac Code Is Not Available!!!\n';
		}
// 		if(contact_Customer_State_Code=='') {
// 			flag_gstin = true;
// 			msg += 'Customer State Code Is Not Available!!!\n';
// 		}
	}
	flag_stop=false;
	flag =true;
	if(flag_stop)
	{
		alert("TAX NO/TAX STRUCTURE ARE NOT AVAILABLE!!!!!!\n INVOICE CAN NOT BE GENERATED!!!");
	} else if(flag_gstin) {
		alert(msg);
	}
	else if(truck_driver=='' || truck_driver==' '){
		alert("No driver linked with the selected Truck !!!\n Please link driver first.");
	}
	else
	{
		var contact_person = document.forms[0].contact_person.value;
		var contact_person_nm = "";
		if(contact_person==null || contact_person=='' || contact_person==' ' || contact_person=='0')
		{
			alert("Please Select The Proper Contact Person !!!");
			flag = false;
		}
		else
		{
			contact_person_nm = document.forms[0].contact_person[document.forms[0].contact_person.selectedIndex].innerText;
			document.forms[0].contact_person_nm.value = contact_person_nm;
		}
		var activity = document.forms[0].activity.value;
		var conttyp = document.forms[0].contract_type.value;
		var tcs_flag = document.forms[0].contract_type.value;
		var tds_cnt = document.forms[0].tdsEntryCnt.value;
		
		if(conttyp=='S' || conttyp=='L'){
			if(tcs_flag=='Y' && activity=='insert'){
				
			}else{
				if(parseFloat(tds_cnt)==0 && activity=='insert'){
					flag = false;
					alert("Please Enter Customer's Turnover for  TCS/TDS applicability!!");	
				}
			}
		}
		 if(flag){
			 
			 if(panflags!=true && panflagc!=true)
			{  
				var a = "";
				if(c_form_flg == ""){
					
					a = confirm("C-Form Not Enabled, Still You Want To Generate Invoice ???");
					
				}else{
					a = confirm("Do You Want To Generate Invoice ???");
				}
				if(a)
				{
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
			} 
		}
			/*else if(flag_tax)
			{
				alert(msg);
			} else if(flag_gstin) {
				alert(msg);
			}
			else {
				alert(msg);
			} */
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
		cin1.size="10";
		cin1.setAttribute("onblur","validateDate(this)");
		cin1.value=""; 
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
			alert("Please select the proper exchange rate..!!");
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
		  		 var url="../advance_payment/frm_invoice_adjustment_ajax.jsp?date="+date+"&Adjust_amt_mst="+Adjust_amt_mst+"&Adjust_cur_mst="+Adjust_cur_mst+"&Adjust_total_avail_bal="+Adjust_total_avail_bal+"&payment_md="+pay_type1;
		  		 request22.open("POST",url);
		  		 request22.onreadystatechange = function() 
		    	 {
		    		 if(request22.readyState==4)
		     		 {
		     		 	var a=document.getElementById("adjustment").innerHTML=request22.responseText;    
						document.getElementById("adjustment").style.visibility="visible";
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
		var payment_md=document.forms[0].pay_type1.value; 
		var date_flag = document.forms[0].date_flag.value;
		
		var date=document.forms[0].exch_rate_dt.value;
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
		  		 var url="../advance_payment/frm_invoice_adjustment_modify_ajax.jsp?date_flag="+date_flag+"&mcontract_type="+mcontract_type+"&mhlpl_inv_seq_no2="+mhlpl_inv_seq_no2+"&minv_financial_year="+minv_financial_year+"&mcustomer_cd="+mcustomer_cd+"&mcustomer_plant_seq_no="+mcustomer_plant_seq_no+"&mbill_period_end_dt="+mbill_period_end_dt+"&date="+date+"&mexchangerateselected="+mexchangerateselected+"&mfgsa_no="+mfgsa_no+"&msn_no="+msn_no+"&mAdjust_amt_mst="+mAdjust_amt_mst+"&mAdjust_cur_mst="+mAdjust_cur_mst+"&fgsa_rev_no="+mfgsa_rev_no+"&mapping_id="+mapping_id+"&payment_md="+payment_md;
		  		 request22.open("POST",url);
		  		 request22.onreadystatechange = function() 
		    	 {
		    		 if(request22.readyState==4)
		     		 {
		     			var a=document.getElementById("adjustmentmodify").innerHTML=request22.responseText;    
						document.getElementById("adjustmentmodify").style.visibility="visible";
		    			 document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].priceINR2.value,2);
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
		    			 document.forms[0].adjustsign.value=document.forms[0].modifyadjsignhid.value;
		    			  //Added for tax calculation update on completion of ajax event
			    			 	var len=document.getElementById("cust_tax_size").value;
							 	for(var i=0;i<len;i++)
							 	{
					 					var temp="cust_tax_amt"+i;
					 					var temptax_amt=document.getElementById(temp).value;  
					 					var tmp="tax_amts"+i;
					 					document.getElementById(tmp).value=temptax_amt;
								 }
								var net_amt_inr=document.getElementById("invoice_inr_amt_adjusted").value;
								document.getElementById("net_amt_inr").value=net_amt_inr;
 									var flag=false;
 									
 									if(document.forms[0].call_on_load_hid.value=='call')
 									{	
 										flag=true;
 										displaysalepricedetails('L');
 										document.forms[0].call_on_load_hid.value='';
 									}
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
			    document.getElementById("adjustmentmodify").innerHTML="";
			    document.getElementById("adjustmentmodify").style.visibility="hidden";
			    alert("Your browser doesn't support AJax !"); 
			 }
		}
		else if(document.forms[0].activity.value=='insert' && document.forms[0].Adv_submitted_flag.value=='Y')
		{
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
		var date=document.forms[0].exch_rate_dt.value;
		var date_flag = document.forms[0].date_flag.value;
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
		  		 var url="../advance_payment/frm_invoice_adjustment_modify_ajax.jsp?date_flag="+date_flag+"&mcontract_type="+mcontract_type+"&activity="+'insert'+"&mhlpl_inv_seq_no2="+mhlpl_inv_seq_no2+"&minv_financial_year="+minv_financial_year+"&mcustomer_cd="+mcustomer_cd+"&mcustomer_plant_seq_no="+mcustomer_plant_seq_no+"&mbill_period_end_dt="+mbill_period_end_dt+"&date="+date+"&mexchangerateselected="+mexchangerateselected+"&mfgsa_no="+mfgsa_no+"&msn_no="+msn_no+"&mAdjust_amt_mst="+mAdjust_amt_mst+"&mAdjust_cur_mst="+mAdjust_cur_mst+"&fgsa_rev_no="+mfgsa_rev_no+"&mapping_id="+mapping_id;
		  		 request22.open("POST",url);
		  		 request22.onreadystatechange = function() 
		    	 {
		    		 if(request22.readyState==4)
		     		 {
		     			var a=document.getElementById("adjustment").innerHTML=request22.responseText;    
						document.getElementById("adjustment").style.visibility="visible";
		    			 document.forms[0].double_final_gross_amt_inr.value=round(document.forms[0].priceINR2.value,2);
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
		    			 document.forms[0].adjustsign.value=document.forms[0].modifyadjsignhid.value;
			    			 	var len=document.getElementById("cust_tax_size").value;
							 	for(var i=0;i<len;i++)
							 	{
					 					var temp="cust_tax_amt"+i;
					 					var temptax_amt=document.getElementById(temp).value;  
					 					var tmp="tax_amts"+i;
					 					document.getElementById(tmp).value=temptax_amt;
								 }
								var net_amt_inr=document.getElementById("invoice_inr_amt_adjusted").value;
								document.getElementById("net_amt_inr").value=net_amt_inr;
 								document.getElementById("modifytaxdetails").innerHTML='';
 									adjustmamountforadjust();
 									var flag=false;
 									if(document.forms[0].call_on_load_hid.value=='call')
 									{	
 										flag=true;
 										displaysalepricedetails('L');
 										document.forms[0].call_on_load_hid.value='';
 									}
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
		
		caluclate_tax_details_ajax(grossamt,taxcd);
		
		//tax calculation is done befor clear because it does not get modify tax details in calculation
		document.getElementById("adjustmentmodify").innerHTML="";
	}
	
	function hidedetails()
	{
		document.getElementById("adjustment").innerHTML="";
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
		
		caluclate_tax_details_ajax(grossamt,taxcd);
	}	
	
	function showtcslist(cust_cd,fin_year){
// 		alert("fin_year==="+fin_year);
		var url="../sales_invoice/frm_tcs_report_dtls.jsp?cust_cd="+cust_cd+"&fin_year="+fin_year;
		window.open(url,"actionReport","top=10,left=70,width=1000,height=500,scrollbars=1,status=1,maximize=yes,resizable=1")
	}
	
	function call_tds_load(tds_cnt,conttyp,tcs_flag,activity){
		if(conttyp=='S' || conttyp=='L'){
			if(tcs_flag=='Y' && activity=='insert'){
				
			}else{
				if(parseFloat(tds_cnt)==0 && activity=='insert'){
					alert("Please Enter Customer's Turnover for TCS/TDS applicability!!");	
				}
			}
		}
	}
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.sales_invoice.DataBean_Prepare_TLU_InvoiceV2" id="TLUsalesInv" scope="page"/>   
<jsp:useBean class="com.seipl.hazira.dlng.advance_payment.DataBean_Advance_payment" id="adv" scope="page"/>  
<%
	util.init();
	String sysdate = util.getGen_dt();
// 	System.out.println("sysdate----"+sysdate);
	String form_id = request.getParameter("form_id")==null?"":request.getParameter("form_id");
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
	String exchg_rate_cd = request.getParameter("exchg_rate_cd")==null?"1":request.getParameter("exchg_rate_cd");
	String exchg_rate_cal_method = "D";//request.getParameter("exchg_rate_cal_method")==null?"0":request.getParameter("exchg_rate_cal_method");
	String invoice_date = request.getParameter("invoice_date")==null?bill_period_end_dt:request.getParameter("invoice_date");
	String particular_date = request.getParameter("particular_date")==null?bill_period_end_dt:request.getParameter("particular_date");
	String rbi_ref_cd = request.getParameter("rbi_ref_cd")==null?"1":request.getParameter("rbi_ref_cd");
	String sbi_tt_selling_cd = request.getParameter("sbi_tt_selling_cd")==null?"2":request.getParameter("sbi_tt_selling_cd");
	String sbi_tt_buying_cd = request.getParameter("sbi_tt_buying_cd")==null?"3":request.getParameter("sbi_tt_buying_cd");
	String sbi_avg_tt_selling_buying_cd = request.getParameter("sbi_avg_tt_selling_buying_cd")==null?"6":request.getParameter("sbi_avg_tt_selling_buying_cd");
	String gross_amt_usd = request.getParameter("gross_amt_usd")==null?"":request.getParameter("gross_amt_usd");
	String gross_amt_inr = request.getParameter("gross_amt_inr")==null?"":request.getParameter("gross_amt_inr");
// 	System.out.println("exchg_rate_cd****"+exchg_rate_cd);
	String gross_amt_usd2 = request.getParameter("gross_amt_usd2")==null?"":request.getParameter("gross_amt_usd2");
	String gross_amt_inr2 = request.getParameter("gross_amt_inr2")==null?"":request.getParameter("gross_amt_inr2");
	String exch_rate_val = request.getParameter("exch_rate_val")==null?"":request.getParameter("exch_rate_val");
// 	System.out.println("gross_amt_inr2****"+gross_amt_inr2);
	String exchg_rate_index = request.getParameter("exchg_rate_index")==null?"0":request.getParameter("exchg_rate_index");
	String mapping_id = request.getParameter("mapping_id")==null?"-1":request.getParameter("mapping_id");
// 	System.out.println("mapping_id****"+mapping_id);
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
// 	System.out.println("hlpl_inv_seq_no****"+hlpl_inv_seq_no);
	
	String page_refresh_flag = request.getParameter("page_refresh_flag")==null?"N":request.getParameter("page_refresh_flag");
	String Adv_submitted_flag = request.getParameter("Adv_submitted_flag")==null?"N":request.getParameter("Adv_submitted_flag");
	String offspec_rate = request.getParameter("offspec_rate")==null?"":request.getParameter("offspec_rate");
	String flag = request.getParameter("flag")==null?"F":request.getParameter("flag");
	String truck_cd = request.getParameter("truck_cd")==null?"0":request.getParameter("truck_cd");//Hiren_20200316
// 	System.out.println("truck_cd****"+truck_cd);
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
	

	String raw_amt_usd = request.getParameter("raw_amt_usd")==null?gross_amt_usd:request.getParameter("raw_amt_usd");
	String raw_amt_inr = request.getParameter("raw_amt_inr")==null?gross_amt_inr:request.getParameter("raw_amt_inr");
	String onload_flg = request.getParameter("onload_flg")==null?"N":request.getParameter("onload_flg"); //Hiren_20200810
	
	String sn_start_dt = request.getParameter("sn_start_dt")==null?"":request.getParameter("sn_start_dt");
	String sn_end_dt = request.getParameter("sn_end_dt")==null?"":request.getParameter("sn_end_dt");
	
	int exchg_rate_ind = Integer.parseInt(exchg_rate_index);
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	if(page_refresh_flag.equals("N") && activity.equals("update"))
	{
		gross_amt_usd = gross_amt_usd2;
		gross_amt_inr = gross_amt_inr2;
		raw_amt_usd = gross_amt_usd2;
		raw_amt_inr = gross_amt_inr2;
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
	boolean date_flag = false;
	String d_flag = request.getParameter("date_flag")==null?"N":request.getParameter("date_flag");
	if(d_flag.equalsIgnoreCase("true") || d_flag.equalsIgnoreCase("Y")) {
		date_flag = true;
	}
	String fin_year="";
	if(hlpl_inv_seq_no.length()>=10){
		if(contract_type.equals("C")) {
			fin_year = hlpl_inv_seq_no.substring(6);
		} else {
			fin_year = hlpl_inv_seq_no.substring(5);	
		}
	}
// 	String advMapping_id = contract_type+"-"+customer_cd+"-"+fgsa_no+"-"+fgsa_rev_no+"-"+sn_no+"-"+sn_rev_no;//Hiren_20210707
// 	System.out.println("mapping_id*******"+mapping_id);
	TLUsalesInv.setCallFlag("SALES_INVOICE_DETAIL");
	TLUsalesInv.setCustomerCd(customer_cd);
	TLUsalesInv.setFgsaNo(fgsa_no);
	TLUsalesInv.setFgsaRevNo(fgsa_rev_no);
	TLUsalesInv.setSnNo(sn_no);
	TLUsalesInv.setSnRevNo(sn_rev_no);
	TLUsalesInv.setContractType(contract_type);
	TLUsalesInv.setCustomerPlantSeqNo(customer_plant_seq_no);
	TLUsalesInv.setBillPeriodStartDt(bill_period_start_dt);
	TLUsalesInv.setBillPeriodEndDt(bill_period_end_dt);
	TLUsalesInv.setExchgRateCd(exchg_rate_cd);
	TLUsalesInv.setInvoiceDate(invoice_date);
	TLUsalesInv.setParticularDate(particular_date);
	TLUsalesInv.setExchgRateCalMethod(exchg_rate_cal_method);
	TLUsalesInv.setRbiRefCd(rbi_ref_cd);
	TLUsalesInv.setSbiTtSellingCd(sbi_tt_selling_cd);
	TLUsalesInv.setSbiTtBuyingCd(sbi_tt_buying_cd);
	TLUsalesInv.setSbiAvgTtSellingBuyingCd(sbi_avg_tt_selling_buying_cd);
	TLUsalesInv.setCustomer_Invoice_Gross_Amt_USD(gross_amt_usd);
	TLUsalesInv.setCustomer_Invoice_Gross_Amt_INR(gross_amt_inr);
	TLUsalesInv.setHlplInvoiceNo(hlpl_inv_seq_no2);
	TLUsalesInv.setInvFinancialYear(inv_financial_year);
	TLUsalesInv.setActivity(activity);
	TLUsalesInv.setPage_refresh_flag(page_refresh_flag);
	TLUsalesInv.setCustomer_inv_mapping_id(mapping_id); //ADDED FOR LTCORA AND CN
	TLUsalesInv.setRefresh_flag(page_refresh_flag);
	TLUsalesInv.setDate_flag(date_flag);
	TLUsalesInv.setTruck_cd(truck_cd);//HA20200316
	TLUsalesInv.setOnload_flg(onload_flg);//Hiren_20200810
	TLUsalesInv.setFin_year(fin_year);
	TLUsalesInv.setSn_start_dt(sn_start_dt);
	TLUsalesInv.setSn_end_dt(sn_end_dt);
	TLUsalesInv.setSysdate(sysdate);
	TLUsalesInv.init();
	
	onload_flg = TLUsalesInv.getOnload_flg();//Hiren_20200810
	String GST_INVOICE_SEQ_NO = TLUsalesInv.getGST_INVOICE_SEQ_NO();
	Vector invoice_Customer_Contact_Cd = TLUsalesInv.getInvoice_Customer_Contact_Cd();
	Vector invoice_Customer_Contact_Nm = TLUsalesInv.getInvoice_Customer_Contact_Nm();
	String total_Invoice_Qty = TLUsalesInv.getTotal_Invoice_Qty();
	String invoice_Sales_Rate = TLUsalesInv.getInvoice_Sales_Rate();
	String Exchg_Rate_Applicable = TLUsalesInv.getExchg_Rate_Applicable(); //SB20200727
	String Exchg_Rate_Applicable_Nm = TLUsalesInv.getExchg_Rate_Applicable_Nm(); //SB20200727
	String Invoice_Previous_Exchg_Rate_Value = TLUsalesInv.getinvoice_Previous_Exchg_Rate_Value(); //SB20200727
// 	System.out.println("invoice_Sales_Rate****"+invoice_Sales_Rate);
	String Invoice_Exchg_Rate_Value = TLUsalesInv.getinvoice_Exchg_Rate_Value(); //SB20200727
	String invoice_Previous_Available_Exchg_Date = TLUsalesInv.getInvoice_Previous_Available_Exchg_Date();
	String invoice_Exchg_Rate_RBI_Ref = TLUsalesInv.getInvoice_Exchg_Rate_RBI_Ref();
	String invoice_Exchg_Rate_TT_Selling = TLUsalesInv.getInvoice_Exchg_Rate_TT_Selling();
	String invoice_Exchg_Rate_TT_Buying = TLUsalesInv.getInvoice_Exchg_Rate_TT_Buying();
	String invoice_Exchg_Rate_Avg_TT_Buy_Sell = TLUsalesInv.getInvoice_Exchg_Rate_Avg_TT_Buy_Sell();
	String invoice_Previous_Exchg_Rate_RBI_Ref = TLUsalesInv.getInvoice_Previous_Exchg_Rate_RBI_Ref();
	String invoice_Previous_Exchg_Rate_TT_Selling = TLUsalesInv.getInvoice_Previous_Exchg_Rate_TT_Selling();
	String invoice_Previous_Exchg_Rate_TT_Buying = TLUsalesInv.getInvoice_Previous_Exchg_Rate_TT_Buying();
	String invoice_Previous_Exchg_Rate_Avg_TT_Buy_Sell = TLUsalesInv.getInvoice_Previous_Exchg_Rate_Avg_TT_Buy_Sell();
	//invoice_Previous_Exchg_Rate_Avg_TT_Buy_Sell="66.275";
	String invoice_Particular_Day_Exchg_Rate_RBI_Ref = TLUsalesInv.getInvoice_Particular_Day_Exchg_Rate_RBI_Ref();
	String invoice_Particular_Day_Exchg_Rate_TT_Selling = TLUsalesInv.getInvoice_Particular_Day_Exchg_Rate_TT_Selling();
	String invoice_Particular_Day_Exchg_Rate_TT_Buying = TLUsalesInv.getInvoice_Particular_Day_Exchg_Rate_TT_Buying();
	String invoice_Particular_Day_Exchg_Rate_Avg_TT_Buy_Sell = TLUsalesInv.getInvoice_Particular_Day_Exchg_Rate_Avg_TT_Buy_Sell();
	String invoice_Exchg_Rate_Note = TLUsalesInv.getInvoice_Exchg_Rate_Note();
	Vector invoice_Period_Dates = TLUsalesInv.getInvoice_Period_Dates();
	Vector invoice_Period_Exchg_Rate_RBI_Ref = TLUsalesInv.getInvoice_Period_Exchg_Rate_RBI_Ref();
	Vector invoice_Period_Exchg_Rate_TT_Selling = TLUsalesInv.getInvoice_Period_Exchg_Rate_TT_Selling();
	Vector invoice_Period_Exchg_Rate_TT_Buying = TLUsalesInv.getInvoice_Period_Exchg_Rate_TT_Buying();
	Vector invoice_Period_Exchg_Rate_Avg_TT_Buy_Sell = TLUsalesInv.getInvoice_Period_Exchg_Rate_Avg_TT_Buy_Sell();
	Vector daily_Invoice_QTY = TLUsalesInv.getDaily_Invoice_QTY();
	String tax_Structure_Dtl = TLUsalesInv.getTax_Structure_Dtl();
	Vector customer_Invoice_Tax_Code = TLUsalesInv.getCustomer_Invoice_Tax_Code();
	Vector customer_Invoice_Tax_Abbr = TLUsalesInv.getCustomer_Invoice_Tax_Abbr();
	Vector customer_Invoice_Tax_Name = TLUsalesInv.getCustomer_Invoice_Tax_Name();
	Vector customer_Invoice_Tax_Amt = TLUsalesInv.getCustomer_Invoice_Tax_Amt();
	Vector customer_Invoice_Tax_Rate = TLUsalesInv.getCustomer_Invoice_Tax_Rate();
	String customer_Invoice_Net_Amt_INR = TLUsalesInv.getCustomer_Invoice_Net_Amt_INR();
	String accepted_Offspec_Qty = TLUsalesInv.getAccepted_Offspec_Qty();
	String accepted_FM_Qty = TLUsalesInv.getAccepted_FM_Qty();
	String boe_no = TLUsalesInv.getBoe_no();
	String boe_dt = TLUsalesInv.getBoe_dt();
	//Following fields for New requi
	String Tariff_flag_Mst=TLUsalesInv.getTARIFF_FLAG_MST();
	String invoice_sales_rate_inr=TLUsalesInv.getTARIFF_INR();
	//invoice_sales_rate_inr="0.8";
	String Discount_flag_Mst=TLUsalesInv.getDISCOUNT_FLAG_MST();
	String discount=TLUsalesInv.getDISCOUNT_PRICE();
	String AdjustFlagForMst=TLUsalesInv.getADJUST_FLAG_MST();
	String Adjust_amt_mst=TLUsalesInv.getADJUST_AMT();
	String Adjust_cur_mst=TLUsalesInv.getADJUST_CUR();
	String Adjust_total_avail_bal=TLUsalesInv.getAdjusttotalavailbalMst();
////Following added for payment type and adjustment flag
	String pay_mode=TLUsalesInv.getPayment_type()==null?"AP":TLUsalesInv.getPayment_type();
	String advanceAdjFlg=TLUsalesInv.getAdvancedFlg()==null?"NA":TLUsalesInv.getAdvancedFlg(); 
	
	if(page_refresh_flag.equals("N"))
	{
		adv_inv_no = TLUsalesInv.getCustomer_ADV_INV_NO();
		adv_inv_dt = TLUsalesInv.getCustomer_ADV_INV_DT();
	}
	
	String customer_inv_seq_no = "";
	if(msg.length()>10)
	{
		hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"":request.getParameter("hlpl_inv_seq_no");
		customer_inv_seq_no = request.getParameter("customer_inv_seq_no")==null?"":request.getParameter("customer_inv_seq_no");
	}
	else
	{
		if(activity.equals("insert"))
		{
			hlpl_inv_seq_no = TLUsalesInv.getHlpl_Invoice_Seq_No();
			customer_inv_seq_no = TLUsalesInv.getCustomer_Invoice_Seq_No();
			
		}
		else if(activity.equals("update"))
		{
			hlpl_inv_seq_no = hlpl_invoice_sequence_no;
			customer_inv_seq_no = TLUsalesInv.getCustomer_Invoice_Seq_No();
			inv_cur_flag=TLUsalesInv.getInv_cur_flag();
			if(page_refresh_flag.equals("N"))
			{				
				contact_person = TLUsalesInv.getContactPersonCd();
				exch_rate_val = TLUsalesInv.getCustomer_Invoice_Exchg_Rate2();
				exchg_rate_ind = TLUsalesInv.getExchg_rate_ind();
				invoice_date = TLUsalesInv.getCustomer_Invoice_DT();
				due_dt = TLUsalesInv.getCustomer_Invoice_Due_DT();
				particular_date = TLUsalesInv.getParticular_date();
				if(particular_date.equals(""))
				{
					particular_date=bill_period_end_dt;
				}
				//adv_inv_no = TLUsalesInv.getCustomer_ADV_INV_NO();
				//adv_inv_dt = TLUsalesInv.getCustomer_ADV_INV_DT();
			}	
		}
	}
	String hlpl_Inv_Seq_No = "";
	String customer_Inv_Seq_No = "";
	String financial_Year = "";
	
	if(hlpl_inv_seq_no.length()>=10)
	{
// 		hlpl_Inv_Seq_No = hlpl_inv_seq_no.substring(0,4);
// 		financial_Year = hlpl_inv_seq_no.substring(5);
		if(contract_type.equals("C")) {
			hlpl_Inv_Seq_No = hlpl_inv_seq_no.substring(0,5);
			financial_Year = hlpl_inv_seq_no.substring(6);
		} else {
			hlpl_Inv_Seq_No = hlpl_inv_seq_no.substring(0,4);
			financial_Year = hlpl_inv_seq_no.substring(5);	
		}
	}
	if(customer_inv_seq_no.length()>=10)
	{
		customer_Inv_Seq_No = customer_inv_seq_no.substring(0,3);
	}
	String customer_Invoice_SN_Dt = TLUsalesInv.getCustomer_Invoice_SN_Dt();
	String customer_Invoice_FGSA_Dt = TLUsalesInv.getCustomer_Invoice_FGSA_Dt();
	String contact_Customer_Name = TLUsalesInv.getContact_Customer_Name();
// 	System.out.println("contact_Customer_Name****"+contact_Customer_Name);
	String contact_Suppl_Name = TLUsalesInv.getContact_Suppl_Name();
// 	String BankNameAccountNo="State Bank of India(Powai Branch) - A/C No: 0221234567890";
	String modifyadjremark="";
// 	String remark_1 = "Please pay the invoiced amount by wire transfer at our Bank Account : "+BankNameAccountNo;
// 	String remark_2 = "Subject to the terms and conditions of the Framework Gas Sales Agreement executed on "+customer_Invoice_FGSA_Dt+" and Supply Notice executed on "+customer_Invoice_SN_Dt+"between "+contact_Suppl_Name+" and "+contact_Customer_Name+"";
//	String remark_1 = "Please pay the invoiced amount by wire transfer at our Bank Account : Standard Chartered Bank (Ahmedabad Branch) - A/C No: 233-0-525465-5. \n";
	String remark_1 = "Please pay the invoiced amount by wire transfer at our Bank Account : CitiBank N.A., Mumbai - A/C No: 522614033, IFSC code: CITI0100000. \n";
// 	String remark_2 = "Subject to the terms and conditions of the Framework LNG Sales Agreement executed on "+customer_Invoice_FGSA_Dt+" and Supply Notice executed on "+customer_Invoice_SN_Dt+" between "+contact_Suppl_Name+" and "+contact_Customer_Name+""; */
	
	/* for this JSP */
	String remark_1_1 ="";
	
	
	String remark_2 ="";
	/* for this Preview JSP */
	remark_1+=" \nNOTE : Bank Account Changes Should Not Be Made Without a Re-Confirmation With Your Usual SHELL Contact. \n \n"
			+"Unless Communicated By Your Usual Shell Contact/Authorized Representative, Bank Account Changes Should Not Be Made. Any Proposed Change Should Always Be Confirmed Initially By Phone And Then Electronically (Email) With Your Usual SHELL Contact. Most Importantly Check For Valid Domain Name (@SHELL.COM) in the E-mail Address.";
	
	remark_1_1 = remark_1;
	
	String remark_3 =""; 
	String rec_remark="";
	if(contract_type.equalsIgnoreCase("T") ||  contract_type.equalsIgnoreCase("C"))
	{
		 remark_3 = "LNG Tank Truck Services"; 
	}
	else
	{
		 remark_3 = "Re-gas services for the Cargo (BOE no. "+boe_no+" dated "+boe_dt+")";
	}
	
	String remark_4 = "Subject to the terms and conditions of the Regas Agreement executed on "+customer_Invoice_FGSA_Dt+" ";
	
	if(msg.length()>10)
	{
		remark_1 = request.getParameter("remark_1")==null?"":request.getParameter("remark_1");
		remark_2 = request.getParameter("remark_2")==null?"":request.getParameter("remark_2");
		remark_3 = request.getParameter("remark_3")==null?"":request.getParameter("remark_3");
		modifyadjremark= request.getParameter("remark_adj")==null?"":request.getParameter("remark_adj");
		rec_remark = request.getParameter("rec_remark")==null?"":request.getParameter("rec_remark");
	}
	else if(msg.length()<10 && activity.equals("update"))
	{
		if(!TLUsalesInv.getRemark_1().trim().equals(""))
		{
			remark_1 = TLUsalesInv.getRemark_1();
		}
		if(!TLUsalesInv.getRemark_2().trim().equals(""))
		{
			remark_2 = TLUsalesInv.getRemark_2();
		}
		if(!TLUsalesInv.getRemark_3().trim().equals(""))
		{
			remark_3 = TLUsalesInv.getRemark_3();
		}	
		if(!TLUsalesInv.getModifyadjremark().trim().equals(""))
		{
			modifyadjremark = TLUsalesInv.getModifyadjremark();
		}
		if(!TLUsalesInv.getRec_remark().trim().equals(""))
		{
			rec_remark = TLUsalesInv.getRec_remark();
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
	
	if(rec_remark!=null)
	{
		if(!rec_remark.trim().equals(""))
		{
			while((rec_remark.indexOf("\n"))!=-1)
			{
				rec_remark=(rec_remark.substring(0,rec_remark.indexOf("\n")-1))+"<br>"+(rec_remark.substring(rec_remark.indexOf("\n")+1));
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
	String contact_addr_flag = TLUsalesInv.getContact_addr_flag();
	String sn_ref_no = TLUsalesInv.getSn_ref_no();
	//adjustment
	String taxcd=TLUsalesInv.getTax_struc_cd();   	
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
	String TariffFlag=request.getParameter("TariffFlag")==null?"N":request.getParameter("TariffFlag");
	String DiscountFlag=request.getParameter("DiscountFlag")==null?"N":request.getParameter("DiscountFlag");
	if(activity.equals("update") && AdjustFlagForMst.equalsIgnoreCase("Y") )
	{}
	if(activity.equals("update") && (Tariff_flag_Mst.equalsIgnoreCase("Y") || Discount_flag_Mst.equalsIgnoreCase("Y")  ))
	{}
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
	contact_Suppl_GST_NO = TLUsalesInv.getContact_Suppl_GST_NO();
	contact_Suppl_CST_NO = TLUsalesInv.getContact_Suppl_CST_NO();
	contact_Suppl_GST_DT = TLUsalesInv.getContact_Suppl_GST_DT();
	contact_Suppl_CST_DT = TLUsalesInv.getContact_Suppl_CST_DT();
	contact_Suppl_PAN_NO = TLUsalesInv.getContact_Suppl_PAN_NO();	//BK20160211
	contact_Suppl_PAN_DT = TLUsalesInv.getContact_Suppl_PAN_DT();	//BK20160211
	String contact_Suppl_Service_Tax_NO = TLUsalesInv.getContact_Suppl_Service_Tax_NO();
	Vector vSTAT_CD = TLUsalesInv.getVSTAT_CD();
	Vector vSTAT_NO = TLUsalesInv.getVSTAT_NO();
	Vector vSTAT_NM = TLUsalesInv.getVSTAT_NM();
	int size = vSTAT_NO.size();
	
	String contact_Suppl_GSTIN_NO  = TLUsalesInv.getContact_Suppl_GSTIN_NO();
	String contact_Suppl_State_Code = TLUsalesInv.getContact_Suppl_State_Code();
	String contact_Suppl_Sac_Code = TLUsalesInv.getSac_code();
	String contact_Customer_State_Code = TLUsalesInv.getContact_Customer_State_Code();
	boolean tax_gst = TLUsalesInv.isTax_gst();
	
	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212
	
	String tcs_app_flag=TLUsalesInv.getTcs_app_flag();
	Double tcs_limit_amt=TLUsalesInv.getTcs_limit_amt();	
	String tcs_amt=TLUsalesInv.getTcs_amt();
	String tcs_nm=TLUsalesInv.getTcs_nm();
	String fact=TLUsalesInv.getFact();
	
	String truck_trans_nm=TLUsalesInv.getTruck_trans_nm();
	String truck_driver=TLUsalesInv.getTruck_driver();
	String truck_driver_addrs=TLUsalesInv.getTruck_driver_addrs();
	String truck_nm=TLUsalesInv.getTruck_nm();
	String truck_trans_addrs=TLUsalesInv.getTruck_trans_addrs();
	String truckLinkedFlg=TLUsalesInv.getTruckLinkedFlg();
	String truck_driver_lic_no=TLUsalesInv.getTruck_driver_lic_no();
	String truck_lic_state=TLUsalesInv.getTruck_lic_state();
	String truck_trans_cd = TLUsalesInv.getTruck_trans_cd();
// 	System.out.println("truck_driver*****"+truck_driver);
	Vector Vlicense_no = TLUsalesInv.getVlicense_no();
	Vector Vdriver_nm = TLUsalesInv.getVdriver_nm();
	Vector Vtrans_cd = TLUsalesInv.getVtrans_cd(); 
	
	//Hiren_20210625
	String tds_app_flag = TLUsalesInv.getTds_app_flag();
	String tds_amt = TLUsalesInv.getTds_amt();
	//
	sn_start_dt = TLUsalesInv.getSn_start_dt();
	sn_end_dt = TLUsalesInv.getSn_end_dt();
	String advAmt= TLUsalesInv.getAdvAmt();
	String c_form_flg = TLUsalesInv.getC_form_flg();
	
	Vector chkpost_cd = TLUsalesInv.getChkpost_cd();
	Vector chkpost_name = TLUsalesInv.getChkpost_name();
	int tdsEntryCnt=TLUsalesInv.getTdsEntryCnt();
	String linked_checkpost_flag = TLUsalesInv.getLinked_checkpost_flag();
	String due_dt_flag = TLUsalesInv.getDue_dt_flag();
// 	System.out.println("sn_start_dt*****"+sn_start_dt);
%>
<body  <%if(msg.length()>10){%>
			onLoad="doClose('<%=month%>','<%=year%>','<%=bill_cycle%>','<%=msg%>');"
		<%}else{ %>
		 	onLoad="call_on_load(); call_tds_load('<%=tdsEntryCnt%>','<%=contract_type%>','<%=tcs_app_flag%>','<%=activity%>');
		 		<%if(onload_flg.equals("N")){%>
		 			setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=Exchg_Rate_Applicable%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F','<%=sn_start_dt%>','<%=sn_end_dt%>');"
		 	<% }%>
		 <%} %>>
<%	if(print_permission.trim().equalsIgnoreCase("N"))
	{	%>
		<script language="javascript" src="../js/mouseclk.js"></script>
<%	}	%>

<section id="invoice_details">

<div class="container-fluid">
<div class="row">
<div class="col-md-12">

<form name="frm_invoice_dtl_gst" method="post" action="../servlet/Frm_Sales_InvoiceV2" id = "invGen">

<input type="hidden" name="truck_trans_nm" value="<%=truck_trans_nm %>">
<input type="hidden" name="truck_driver" value="<%=truck_driver %>">
<input type="hidden" name="truck_driver_addrs" value="<%=truck_driver_addrs %>">
<input type="hidden" name="truck_nm" value="<%=truck_nm %>">
<input type="hidden" name="truck_trans_addrs" value="<%=truck_trans_addrs %>">
<input type="hidden" name="truckLinkedFlg" value="<%=truckLinkedFlg %>">
<input type="hidden" name="truck_driver_lic_no" value="<%=truck_driver_lic_no %>">
<input type="hidden" name="truck_lic_state" value="<%=truck_lic_state %>">

<input type="hidden" name="tcs_app_flag" value="<%=tcs_app_flag%>">
<input type="hidden" name="contact_Suppl_GSTIN_NO" value="<%=contact_Suppl_GSTIN_NO%>">
<input type="hidden" name="contact_Suppl_State_Code" value="<%=contact_Suppl_State_Code%>">
<input type="hidden" name="contact_Suppl_Sac_Code" value="<%=contact_Suppl_Sac_Code%>">
<input type="hidden" name="contact_Customer_State_Code" value="<%=contact_Customer_State_Code%>">
<input type="hidden" name="tax_gst" value="<%=tax_gst%>">

<input type="hidden" name="contact_Suppl_GST_NO" value="<%=contact_Suppl_GST_NO %>">
<input type="hidden" name="contact_Suppl_CST_NO" value="<%=contact_Suppl_CST_NO %>">
<input type="hidden" name="contact_Suppl_GST_DT" value="<%=contact_Suppl_GST_DT %>">
<input type="hidden" name="contact_Suppl_CST_DT" value="<%=contact_Suppl_CST_DT %>">
<input type="hidden" name="contact_Suppl_Service_Tax_NO" value="<%=contact_Suppl_Service_Tax_NO%>">
<!-- //BK20160211 -->
<input type="hidden" name="contact_Suppl_PAN_NO" value="<%=contact_Suppl_PAN_NO %>">
<input type="hidden" name="contact_Suppl_PAN_DT" value="<%=contact_Suppl_PAN_DT %>">
<input type="hidden" name="tdsEntryCnt" value="<%=tdsEntryCnt %>">
<!--  -->
<%for(int i=0;i<vSTAT_NO.size();i++) { %>
	<input type="hidden" name="vstat_nm" value="<%=vSTAT_NM.elementAt(i)%>">
	<input type="hidden" name="vstat_no" value="<%=vSTAT_NO.elementAt(i)%>">
	<input type="hidden" name="vstat_cd" value="<%=vSTAT_CD.elementAt(i)%>">
<% } %>
<input type="hidden" name="tax_size" value="<%=size%>">
<input type="hidden" name="customer_cd_tax" value="<%=customer_cd %>">
<input type="hidden" name="customer_abbr_tax" value="<%=customer_abbr %>">
<input type="hidden" name="Adv_submitted_flag" value="<%=Adv_submitted_flag %>">
<input type="hidden" name="sn_start_dt" value="<%=sn_start_dt %>">
<input type="hidden" name="sn_end_dt" value="<%=sn_end_dt %>">
<input type="hidden" name="c_form_flg" value="<%=c_form_flg %>">

<!-- Default box -->
<div class="box">
	<%if(msg.length()>5){%>
	<div class="box-body table-responsive no-padding">
		<table class="table  table-bordered">
			<thead>   
				<tr class="info">
					<th class="text-center" style="color: blue;"> <%=msg%> </th>
				</tr>
			</thead>
		</table>
	</div>
	<%} %>

	<div class="box-header with-border">
	<%	if(contract_type.equalsIgnoreCase("S") || contract_type.equalsIgnoreCase("L"))
		{	%>
			<h3 style="color: #4e4e4e;" class="box-title weight-600"><strong> TAX INVOICE </strong></h3>
	<%	}	
		else if(contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		{	%>
			<h3 style="color: #4e4e4e;" class="box-title weight-600"><strong> INVOICE </strong></h3>
	<%	}	%>&nbsp;<h3 style="color: #4e4e4e;" class="box-title weight-600"><strong> DETAILS </strong></h3>
		
		<input type="hidden" name="msg" value="<%=msg%>">

		<div class="box-tools pull-right">
			<button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove" name="close_window" value="Close Window" onclick="closeWindow();">
			Close  <i class="fa fa-times"></i>
			</button>
		</div>
	</div>
<div class="box-body no-padding">

<div class="row">
<div class="col-lg-12">
<div class="table-responsive">
<table class="table table-striped table-bordered mb-0">
<thead>
<tr>
<th class="text-center"> CUSTOMER </th>
<th class="text-center"> SN/LOA/RE REF. NO </th>
<th class="text-center"> PLANT </th>
<th class="text-center"> QTY (MMBTU) </th>
<th class="text-center"> PRICE ($/MMBTU) </th>
<th class="text-center"> CONTACT PERSON </th>
<th>&nbsp;</th>
</tr>
</thead>

<tbody>
	<tr>
		<td class="text-center"><div class="mt-5"> <%=customer_abbr%> </div>
			<input type="hidden" name="customer_cd" id="customer_cd" value="<%=customer_cd%>">
			<input type="hidden" name="customer_abbr" value="<%=customer_abbr%>">
		</td>
		
		<td class="text-center"><div class="mt-5"> <%if(sn_ref_no.trim().equals("")){%><%=sn_no%><%}else{%><%=sn_ref_no%><%}%> </div>
			<input type="hidden" name="fgsa_no" id="fgsa_no" value="<%=fgsa_no%>">
			<input type="hidden" name="fgsa_rev_no" id="fgsa_rev_no" value="<%=fgsa_rev_no%>">
			<input type="hidden" name="sn_no" id="sn_no" value="<%=sn_no%>">
			<input type="hidden" name="sn_rev_no" id="sn_rev_no" value="<%=sn_rev_no%>">
			<input type="hidden" name="contract_type" id="contract_type" value="<%=contract_type%>">
		</td>
		
		<td class="text-center"><div class="mt-5"> <%=customer_plant_nm%> </div>
			<input type="hidden" name="customer_plant_seq_no" id="customer_plant_seq_no" value="<%=customer_plant_seq_no%>">
			<input type="hidden" name="customer_plant_nm" value="<%=customer_plant_nm%>">
		</td>
		
		<td class="text-center"><div class="mt-5"> <%=total_Invoice_Qty%></div>
			<input type="hidden" name="total_qty" value="<%=total_Invoice_Qty%>">
		</td>
			
		<td class="text-center"><div class="mt-5"> <%=invoice_Sales_Rate%> </div>
			<input type="hidden" name="sale_price" value="<%=invoice_Sales_Rate%>">
		</td>
		<td>
		<select class="form-control" name="contact_person">
<!-- 			<option value="0">--Select--</option> -->
			<%	for(int i=0; i<invoice_Customer_Contact_Cd.size(); i++)
				{%>	
					<option value="<%=(String)invoice_Customer_Contact_Cd.elementAt(i)%>"><%=(String)invoice_Customer_Contact_Nm.elementAt(i)%></option>
				<%	}	%>
				
		</select>
		
		<%if(!contact_person.equals("0") && !contact_person.equals("") ){ 
		%>
			<script>
				document.forms[0].contact_person.value="<%=contact_person%>";
			</script>
			<%} %>
		</td>
		<td>	
<!-- 		<span class="input-group-addon">  -->
			<%if(tds_app_flag.equalsIgnoreCase("Y")) {%>
					&nbsp;&nbsp;&nbsp;<img style="cursor: pointer;vertical-align: middle;" src="../images/greenInfo.gif" title="TDS applicable" width="25" height="25" >
				<%}else if(tcs_app_flag.equalsIgnoreCase("Y")){ %>
					&nbsp;&nbsp;&nbsp;<img style="cursor: pointer;vertical-align: middle;" src="../images/information.png" title="TCS applicable" width="25" height="25" >
				<%} %> 
<!-- 				</span> -->
		</td>
	</tr>
</tbody>


<!-----------------------------START T2------------------------------>
<thead>
	<tr>
		<th class="col-lg-2"> INVOICE DATE </th>
		<th class="col-lg-2" colspan="2"> DUE DATE &nbsp; 
			<font size="1">
			<%if(due_dt_flag.equalsIgnoreCase("B")) {%>
				(Business Days)
			<%}else{ %>
				(Calendar Days)
			<%} %>
			</font>
		</th>
		<th class="col-lg-2" colspan="2"> BILLING(Loading) DATE </th>
		<th colspan="2" class="text-center">INVOICE SEQUENCE NO </th>
	</tr>
</thead>
		
<tbody>
	<tr>
		<td>
			<div class="form-group  mb-0 input-group">
				<div class="input-group date" id="datetimepicker5">
					<%	if(exchg_rate_cal_method.equalsIgnoreCase("D")){	%>
						<input type="text" class="form-control" name="invoice_date" value="<%=invoice_date%>"  maxlength="11" onBlur="validateDate(this);checkWithInvoice(this);setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F');">
					
					<%	}else{	%>
						<input type="text" class="form-control" name="invoice_date" value="<%=invoice_date%>" maxlength="11" onblur="validateDate(this);checkWithInvoice(this);">
					<%} %>	
						<span class="input-group-addon">
						<i class="fa fa-calendar"></i>
						</span>
				</div>
			</div>
		</td>

		<td colspan="2">
			<div class="form-group  mb-0 input-group">
				<div class="input-group date" id="datetimepicker6">
					<input type="text" class="form-control"name="due_date" value="<%=due_dt%>"  maxlength="11" onblur="validateDate(this);checkWithInvoice(this);">
					<span class="input-group-addon">
					<i class="fa fa-calendar"></i>
					</span>
				</div>
			</div>
		</td>

		 <td colspan="2"> 
			<div class="form-group mb-0 row">
				<div class="col-lg-12"> 
					<input class="form-control" type="text"  name="bill_period_start_date" value="<%=bill_period_start_dt%>"  readonly="readonly" maxlength="11">
				</div>
			</div>
		</td> 
		<!-- <td> 
			<div class="form-group mb-0 row">
				<div class="col-lg-12"> -->
					<input type="hidden"  name="bill_period_end_date" value="<%=bill_period_end_dt%>" readonly="readonly" maxlength="11">
				<!-- </div>
			</div>
		</td> -->

		<td colspan="2" class="text-center"><div class="mt-5"><%
				//System.out.println("GST_INVOICE_SEQ_NO****"+GST_INVOICE_SEQ_NO);
				if(!GST_INVOICE_SEQ_NO.equals("")) { %>
					<%=GST_INVOICE_SEQ_NO%>
				<% } else { %>
					<%=invno%>
				<% } %> </div>
			<input type="hidden" name="hlpl_inv_no_prev" value="<%=invno %>">
			<input type="hidden" name="hlpl_inv_seq_no" value="<%=hlpl_inv_seq_no%>">
			<input type="hidden" name="customer_inv_seq_no" value="<%=customer_inv_seq_no%>">
			<input type="hidden" name="hlpl_Inv_Seq_No" value="<%=hlpl_Inv_Seq_No%>">
			<input type="hidden" name="customer_Inv_Seq_No" value="<%=customer_Inv_Seq_No%>">
			<input type="hidden" name="financial_Year" value="<%=financial_Year%>">
			<input type="hidden" name="GST_INVOICE_SEQ_NO" value="<%=GST_INVOICE_SEQ_NO%>">	
		</td>
	</tr>
	
	<%	if(offspec_flag.trim().equalsIgnoreCase("Y")){ 	%>
		<thead>
			<tr>
				<th class="col-lg-2" colspan="2"> OFFSPEC QTY :</th>
				<th class="col-lg-2"><input class="form-control" type="text" name="offspec_qty" value="<%=accepted_Offspec_Qty%>" readonly="readonly" maxlength="11"> </th>
				<th class="col-lg-2" colspan="2">OFFSPEC RATE : </th>
				<th class="col-lg-2"><input class="form-control" name="offspec_rate" value="<%=offspec_rate%>"  onBlur="checkNumber1(this,6,4);setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F');"> </th>
			</tr>
	</thead>
	<%} %>

	<tr class="active">
		<td colspan="2">
			<div class="mt-5 weight-600">
				EXCHANGE RATE CONSIDERATION ON: 
			</div>
		</td>
		
		<td colspan="5">
			<div class="mt-5 weight-600">
				<%=invoice_Exchg_Rate_Note%> 
			</div>
		</td>
	</tr>


</tbody>
<!-----------------------------END T2------------------------------>


<!--APPLICABLE EXCHANGE RATES START-->

<thead>
	<%	if(exchg_rate_cal_method.equalsIgnoreCase("D") || exchg_rate_cal_method.equalsIgnoreCase("A")){ %>
	<tr>
		<th colspan="7"> <div class="font-18"> APPLICABLE EXCHANGE RATES </div></th>
	</tr>
	<tr>
		<th> EXCHANGE RATE DAY </th>
		<th> DATE </th>
		<th colspan="5">(<%=Exchg_Rate_Applicable %>)<%=Exchg_Rate_Applicable_Nm %></th>
	</tr>
</thead>
<tfoot>

</tfoot>

<tbody>
	<tr>
		<th>  Previous Available Day </th>
		<td> <input type="hidden" name="previous_dt" value="<%=invoice_Previous_Available_Exchg_Date%>"><%=invoice_Previous_Available_Exchg_Date%> </td>
		
		
		<td colspan="5">
			<div class="checkbox mt-5 mb-0" >
				<input type="radio" checked="checked" id="" name="exch_rate" onclick="setExchangeRate('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=Exchg_Rate_Applicable%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F');">
				 <%=Invoice_Previous_Exchg_Rate_Value%>
				 <%if(Invoice_Previous_Exchg_Rate_Value.equals("") || Invoice_Previous_Exchg_Rate_Value.equals("0")) {%>
				 	<marquee style="color: red">Exchange Rate Not Available!!</marquee>
				 <%} %>
				 
			</div>
			<input type="hidden" name="exch_rate_code" value="<%=Exchg_Rate_Applicable%>"><input type="hidden" name="exch_rate_value" value="<%=Invoice_Previous_Exchg_Rate_Value%>">
			<input type="hidden" name="inv_date" value="<%=invoice_date%>">
		</td>

	</tr>


	<!-- <tr>
		<th><div class="mt-5"> User Defined Day </div></th>
		<td > 
			<div class="form-group  mb-0">
				<div class="input-group date" id="datetimepicker1"> -->
					<input type="hidden" class="form-control" name="particular_date" value="<%=particular_date%>" maxlength="10" onBlur="refreshPage('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=mapping_id%>','1','<%=invoice_Period_Dates.size()%>','F');">
					<!-- <span class="input-group-addon">
					<i class="fa fa-calendar"></i>
					</span>
				</div>
			</div>
		</td> -->
		
		
	
	<tr>
		<td colspan="7">
		<%	for(int i=0; i<invoice_Period_Dates.size(); i++)
			{	%>
				<input type="hidden" name="daily_inv_dt" size="10" value="<%=(String)invoice_Period_Dates.elementAt(i)%>">
				<input type="hidden" name="daily_inv_qty" style="text-align:right" size="10" value="<%=(String)daily_Invoice_QTY.elementAt(i)%>">
				<input type="hidden" name="daily_inv_amt_inr" value="0">
				<input type="hidden" name="daily_inv_amt_usd" value="0">
		<%	}	%>
		</td>
	</tr>
	<%} 
	else if(exchg_rate_cal_method.equalsIgnoreCase("A1"))
	{	%>

<%	}	%>

	<%if(Discount_flag_Mst.equalsIgnoreCase("Y")){ 
	
		int period_size=12;
		if(exchg_rate_cal_method.equalsIgnoreCase("A"))
		{
			period_size=invoice_Period_Dates.size();
		}
	%>
	<tr>
		<td colspan="7">
			<div class="checkbox mt-5 mb-0">
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
			<label for="discount_check">Applicable Discount : 
				<input type="text" class="form-control" name="discount_value"  value="<%=discount%>" style="text-align:right"  readonly="readonly" >($/MMBTU)</label>
			</div>
		</td>
		
	</tr>
	<%} %>

	<tr>
		<td colspan="7">
		<div class="row">
		
			<div class="col-lg-4">
				<div class="form-group input-group mb-0">
					<span class="input-group-addon weight-600"> Invoice AMT USD:  </span>
					<input type="text" class="form-control" name="gross_amt_usd"  value="<%=gross_amt_usd%>" style="text-align:right"  readonly="readonly">
					<input type="hidden" name="raw_amt_usd" value="<%=gross_amt_usd%>">
				</div>
			</div>
		
			<div class="col-lg-4">
				<div class="form-group input-group mb-0">
					<span class="input-group-addon weight-600"> Applied Exchange Rate: </span>
					<input type="text" class="form-control" name="exch_rate_val"  value="<%=exch_rate_val%>" style="text-align:right"  readonly="readonly">
				</div>
			</div>
			<div class="col-lg-4">
				<div class="form-group input-group mb-0">
					<span class="input-group-addon weight-600"> Invoice AMT INR:  </span>
					<input type="text"  class="form-control" name="gross_amt_inr"  value="<%=gross_amt_inr%>" style="text-align:right" readonly="readonly">
					<input type="hidden" name="raw_amt_inr" value="<%=gross_amt_inr%>">
				</div>
			</div>
		
		</div>
		
		</td>
	</tr>
	
	<%if(Tariff_flag_Mst.equalsIgnoreCase("Y")){ %>
	<tr>
		<td colspan="7">
			<div class="checkbox mt-5 mb-0">
				<%if(TariffFlag.equalsIgnoreCase("Y")){ %>
				<input type="checkbox" name="sale_price_inr_check" value="" id="sale_price_inr_check" onclick="displaysalepricedetails('N'),tariff_setter(this);" checked="checked">
				<%}else{ %>
				<input type="checkbox" name="sale_price_inr_check" value="" id="sale_price_inr_check" onclick="displaysalepricedetails('N'),tariff_setter(this);">
				<%} %>
				<label for="sale_price_inr_check">
				<%	if(contract_type.equalsIgnoreCase("R")){	%>Regasification Tariff <%} else if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
				{	%>LNG  Tariff <%}else{ %>&nbsp;Tariff <%} %> : INR </label>
			</div>	
		</td>
	</tr>
	
	<tr>
		<td colspan="7">
			<div id="salepricediv">
			</div>
		</td>
	</tr>
	<%} %>
	
	 <%if(!activity.equals("update")) { %>
	 <%if(AdjustFlagForMst.equalsIgnoreCase("Y")){ %>
	
	 <tr>
		<td colspan="3">
			<div class="mt-5 mb-0">
				Adjust <%if(pay_mode.equalsIgnoreCase("AP")){ %>
					 Advance Payment ?<%}else{ %>Special Payment ?<%} %>
					 <input type="radio" name="adjust" value="Y" onclick="displaydetails('AA');">
					 <label>Yes</label>
					 <input type="radio" name="adjust" value="N" checked="checked" onclick="hidedetails();">
					 <label>No</label>
			</div>
		</td>
		<td> 
			<div class="mt-5"> Advance Inv No &amp; Date: </div></td>
			<td><input type='text' class="form-control" name='adv_inv_no' style='text-align: center;' value='<%=adv_inv_no%>'></td>
			<td>
			 
			<div class="form-group  mb-0">
				<div class="input-group date" id="datetimepicker2">
					<input type="text" class="form-control" name='adv_inv_dt' maxlength='10' style='text-align: center;' value='<%=adv_inv_dt%>' onblur="validateDate(this);" >
					<span class="input-group-addon">
					<i class="fa fa-calendar"></i>
					</span>
				</div>
			</div>
		</td>
	</tr>		

	<tr>
		<td colspan="7">
			<div id="adjustment">
			</div>
		</td>
	</tr>

	<%}
 }else{ %>
 		<%if(AdjustFlagForMst.equalsIgnoreCase("Y")){ %>
 		 <tr>
		<td colspan="3">
			<div class="mt-5 mb-0">
				Adjust <%if(pay_mode.equalsIgnoreCase("AP")){ %>
					 Advance Payment ?<%}else{ %>Special Payment ?<%} %>
					 
					<%if(modifyadjflag.equalsIgnoreCase("Y")){ %> 
					 <input type="radio" name="adjust" value="Y" onclick="displaydetailsmodify();">
					 <label>Yes</label>
					 <input type="radio" name="adjust" value="N" checked="checked" onclick="hidedetailsmodify();">
					 <label>No</label>
					 <%}else{ %>
					 <input type="radio" name="adjust" value="Y" onclick="document.forms[0].advflg.value='AA';displaydetailsmodify();">
					 <label>Yes</label>
					 <input type="radio" name="adjust" value="N" checked="checked" onclick="hidedetailsmodify();">
					 <label>No</label>
					 <%} %>
			</div>
		</td>
		<td> 
			<div class="mt-5"> Advance Inv No &amp; Date: </div></td>
			<td><input type='text' class="form-control" name='adv_inv_no' style='text-align: center;' value='<%=adv_inv_no%>'></td>
			<td>
			 
			<div class="form-group  mb-0">
				<div class="input-group date" id="datetimepicker2">
					<input type="text" class="form-control" name='adv_inv_dt' maxlength='10' style='text-align: center;' value='<%=adv_inv_dt%>' onblur="validateDate(this);" >
					<span class="input-group-addon">
					<i class="fa fa-calendar"></i>
					</span>
				</div>
			</div>
		</td>
	</tr>		

	<tr>
		<td colspan="7">
			<div id="adjustmentmodify">
			</div>
		</td>
	</tr>
	<%}%>
<%}%>


	<input type="hidden" name="inv_cur_flag" value="<%=inv_cur_flag%>">
<!--INVOICE IN MULTIPLE CURRENCY END--> 

<!--TAX STRUCTURE START-->
	<tr>
		<th colspan="7"> <div class="font-18"> TAX STRUCTURE </div></th>
	</tr> 


	<tr>
		<td colspan="7">
			<div class="row">
			<div class="col-lg-4 col-md-4 hidden-sm hidden-xs"></div>
			<div class="col-lg-5 col-md-5 hidden-sm hidden-xs"></div>
			<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
			<div class="form-group input-group mb-0">
			<span class="input-group-addon weight-600"> Gross Invoice AMT INR :   </span>
			<input type="text"  class="form-control" name="double_final_gross_amt_inr"  value="<%=gross_amt_inr%>" style="text-align:right"  readonly="readonly">
			</div>
			</div>
			</div>
		</td>
	</tr>


	<tr class="active">
		<td colspan="7">
			<div class="mt-5 weight-600">
			Tax Structure Details : <%=tax_Structure_Dtl%>
			<input type="hidden" name="taxstructcd" value="<%=taxcd %>">
			</div>
		</td>
	</tr>
	
	<div id="adjustmenttax"></div>

	<%for(int i=0; i<customer_Invoice_Tax_Code.size(); i++)	{%>
	<tr>
		<td colspan="7">
			<div class="row">
				<div class="col-lg-4 col-md-4 hidden-sm hidden-xs"></div>
				<div class="col-lg-5 col-md-5 hidden-sm hidden-xs"></div>
			
				<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<div class="form-group input-group mb-0">
				<span class="input-group-addon weight-600"><%=customer_Invoice_Tax_Name.elementAt(i)%>&nbsp;(<%=customer_Invoice_Tax_Rate.elementAt(i)%>%):&nbsp;
				</span>	
				<input type="text" class="form-control" name="tax_amts" id="tax_amts<%=i %>" value="<%=customer_Invoice_Tax_Amt.elementAt(i)%>" style="text-align:right"  readonly="readonly">
				<input type="hidden" name="taxcodes" id="taxcodes" value="<%=customer_Invoice_Tax_Code.elementAt(i)%>">
				<input type="hidden" name="taxRate"  value="<%=customer_Invoice_Tax_Rate.elementAt(i)%>">
				</div>
				</div>
			
			</div>
		</td>
	</tr>
	<%	}	%>
	
	
	
	<%if(tcs_app_flag.equals("Y")){ %>
	
		<tr>
			<td colspan="7">
			
				<div class="row">
				
					<div class="col-lg-4 col-md-4 hidden-sm hidden-xs"></div>
					<div class="col-lg-3 col-md-3 hidden-sm hidden-xs"></div>
				
					<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
					<div class="form-group input-group mb-0">
					<span class="input-group-addon weight-600"><%=tcs_nm %> &nbsp;(<%=fact %>%):  </span>
						<input type="text" class="form-control" name="tax_tcs_amts" id="tax_tcs_amts" value="<%=tcs_amt %>" style="text-align:right"  readonly="readonly">
						<span class="input-group-addon">
							<img style="cursor: pointer;" src="../images/information.png" title="View Details" width="17" height="17" alt="Detail Analysis"  onclick="showtcslist('<%=customer_cd%>','<%=financial_Year%>');">
						</span>	
					</div>
					</div>
				</div>
				<input type="hidden" id="tcs_nm" value="<%=tcs_nm %>">
				<input type="hidden" id="tcs_fact" value="<%=fact %>">
			</td>
		</tr>
	<%} %>
	<tr>
		<td colspan="7">
			<div class="row">
			
				<div class="col-lg-4 col-md-4 hidden-sm hidden-xs"></div>
				<div class="col-lg-5 col-md-5 hidden-sm hidden-xs"></div>
			
				<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
				<div class="form-group input-group mb-0">
				<span class="input-group-addon weight-600">Net Invoice Amount INR:  </span>
				<input type="text" class="form-control" name="net_amt_inr" id="net_amt_inr" value="<%=customer_Invoice_Net_Amt_INR%>" style="text-align:right"  readonly="readonly">
				</div>
				</div>
			
			</div>
		</td>
	</tr>
    
	<tr>
		<td colspan="7">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="form-group input-group mb-0">
				<span class="input-group-addon weight-600"> REMARK-1:  </span>
					<textarea class="form-control" name="remark_1" cols="80" rows="3"><%=remark_1_1%></textarea>
					<input type="hidden" name="remark_1_1" value="<%=remark_1%>">
				</div>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td colspan="7">
			<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="form-group input-group mb-0">
			<span class="input-group-addon weight-600"> REMARK-2:  </span>
				<textarea class="form-control" name="remark_2" cols="80" rows="3"><%//=remark_2%></textarea>
			</div>
			</div>
			</div>
		</td>
	</tr>
	<%if(AdjustFlagForMst.equalsIgnoreCase("Y")){ %>
	
	<tr>
		<td colspan="7">
			<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="form-group input-group mb-0">
			<span class="input-group-addon weight-600"> REMARK-3:  </span>
				<textarea class="form-control" name="remark_adj" cols="80" rows="3"><%=modifyadjremark%></textarea>
			</div>
			</div>
			</div>
		</td>
	</tr>
	
	<tr>
		<td colspan="7">
			<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="form-group input-group mb-0">
			<span class="input-group-addon weight-600"> ADJUSTMENT REMARK :  </span>
				<textarea class="form-control" name="rec_remark" cols="80" rows="3"><%=rec_remark%></textarea>
			</div>
			</div>
			</div>
		</td>
	</tr>
    <%} %>
    
    <tr>
	<%//System.out.println("truck_driver_lic_no--------"+truck_driver_lic_no);
	if(!truckLinkedFlg.equalsIgnoreCase("N")) {%>
		<td colspan="4">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group input-group mb-0">
						<span class="input-group-addon weight-600"> Driver Name: </span>
						<span class=" weight-600 form-control text-left"><%=truck_driver %></span>
						<span class="input-group-addon weight-600"><a href="#" onclick="unlink_Driver('<%=truck_driver_lic_no %>','<%=truck_trans_cd %>','<%=truck_cd %>','<%=emp_cd %>');" >  <u>Un-Link</u> </a></span>
					</div>
				</div>
			</div>
		</td>
		
	<%}else{ %>
		<td colspan="4">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group input-group mb-0">
						<span class="input-group-addon weight-600">Driver Name: </span>
						<%if(Vdriver_nm.size()> 0) { %>
						<select class="form-control" name="selDriver" id="selDriver" >
							<option value="">-Select-</option>
							<%for(int i = 0 ; i < Vdriver_nm.size() ; i++){ %>
								<option value="<%=Vlicense_no.elementAt(i)%>@@<%=Vtrans_cd.elementAt(i)%>">
								 <%= Vdriver_nm.elementAt(i)%></option>
							<%} %>
						</select>
						
						<span class="input-group-addon weight-600 "><a href="#" onclick="link_Driver('<%=truck_cd %>','<%=emp_cd %>');" >  <u>Link</u> </a></span>
						
						<%}else{ %>
						<span class="form-control"><font color="red">No Driver available for Linking</font> </span>
						<%} %>
					</div>
				</div>
			</div>
		</td>
	<%} %>
	<td colspan="3">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group input-group mb-0">
						<span class="input-group-addon weight-600">Check Post:  </span>
						<select class="form-control" name="checkPost" id="checkPost">
							<%for(int i = 0; i < chkpost_name.size(); i++) {%>
								<option value="<%=chkpost_name.elementAt(i) %>"><%=chkpost_name.elementAt(i) %></option>
							<%} %>
						</select>
						<%if(chkpost_name.size() > 0 && linked_checkpost_flag.equals("Y")) {%> 
							<span class="input-group-addon weight-600 " id = "chkSpan"><a href="#" id="unlinkChkPost" >  <u>Un-Link All</u> </a></span>
						<%} %>	
					</div>
				</div>
			</div>
		</td>
			
			<!-- <td colspan="4">
				<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
					<div class="form-group input-group mb-0">
						<span class="input-group-addon weight-600">Check Post:  </span>
						
						<select class="form-control" name="checkPost" id="checkPost">
							<option value="">--Select--</option>	
							<option value="ALANG PORT">ALANG PORT </option>
							<option value="AHMEDPUR MANDVI"> AHMEDPUR MANDVI</option>
							<option value="AMBAJI">AMBAJI </option>
							<option value="AMIRGADH"> AMIRGADH</option>
							<option value="BEDI PORT"> BEDI PORT</option>
							<option value="BHAVNAGAR PORT">BHAVNAGAR PORT </option>
							<option value="BHILAD">BHILAD </option>
							<option value="BY AIR">BY AIR </option>
							<option value="BY ANGADIA">BY ANGADIA </option>
							<option value="BY COURIER"> BY COURIER</option>
							<option value="BY PIPELINE">BY PIPELINE </option>
							<option value="BY RAIL">BY RAIL </option>
							<option value="BY SEA">BY SEA </option>
							<option value="BY SELF">BY SELF </option>
							<option value="CHALA">CHALA </option>
							<option value="CHANOD">CHANOD </option>
							<option value="DAHEJ PORT">DAHEJ PORT </option>
							<option value="DAHOD">DAHOD </option>
							<option value="GUNDARI">GUNDARI </option>
							<option value="ICD ANKLESHWAR">ICD ANKLESHWAR </option>
							<option value="ICD DASHRATH">ICD DASHRATH </option>
							<option value="ICD KHODIYAR">ICD KHODIYAR </option>
							<option value="ICD SACHIN">ICD SACHIN </option>
							<option value="ICD SANAND">ICD SANAND </option>
							<option value="ICD VALSAD">ICD VALSAD </option>
							<option value="JAKHAU PORT">JAKHAU PORT </option>
							<option value="KANDLA PORT">KANDLA PORT </option>
							<option value="KAPARADA">KAPARADA </option>
							<option value="MAGDALLA PORT">MAGDALLA PORT </option>
							<option value="MUNDRA PORT">MUNDRA PORT </option>
							<option value="NAGPUR">NAGPUR </option>
							<option value="PIPAVAV PORT">PIPAVAV PORT </option>
							<option value="PORBANDAR PORT">PORBANDAR PORT </option>
							<option value="SAGBARA">SAGBARA </option>
							<option value="SAMAKHYALI">SAMAKHYALI </option>
							<option value="SAPUTARA"> SAPUTARA</option>
							<option value="SEZ GUJARAT">SEZ GUJARAT </option>
							<option value="SHAMLAJI">SHAMLAJI </option>
							<option value="SIKKA PORT">SIKKA PORT </option>
							<option value="SONGADH">SONGADH </option>
							<option value="SURAT HAJIRA PORT">SURAT HAJIRA PORT </option>
							<option value="THARAD"> THARAD</option>
							<option value="THAVAR">THAVAR </option>
							<option value="VERAVAL PORT">VERAVAL PORT </option>
							<option value="ZALOD">ZALOD </option>
						</select>
					</div>
				</div>
    	</td> -->
    </tr>
<!--TAX STRUCTURE END-->
</tbody>
<!--APPLICABLE EXCHANGE RATES END-->
</table>
</div>
</div>
</div>

</div>

<!-- /.box-body -->
			<input type="hidden" name="option" value="submitBillingDetails">
			<input type="hidden" name="modCd" value="<%=modCd%>">
			<input type="hidden" name="formId" value="<%=formId%>">
			<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
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
    		<input type="hidden" name="truck_cd" value="<%=truck_cd%>">
			<input type="hidden" name="tds_amt" value="<%=tds_amt%>">
			<input type="hidden" name="tds_app_flag" value="<%=tds_app_flag%>">
			<input type="hidden" name="advAmt" value="<%=advAmt%>">
	
	<script>
					function togglestatus(customer_abbr,customer_cd,fgsa_no,fgsa_rev_no,sn_no,sn_rev_no,contract_type,
							customer_plant_nm,customer_plant_seq_no,bill_period_start_dt,bill_period_end_dt,due_dt,
							month,year,bill_cycle,exchg_rate_cd,exchg_rate_cal_method,hlpl_inv_seq_no,inv_financial_year,
							mapping_id,print_permission,contact_addr_flag,GST_INVOICE_SEQ_NO,type)
					{
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
						
						var tcs_app_flag = document.forms[0].tcs_app_flag.value;
						var tcs_nm = '';
						var tcs_fact = '';
						var tax_tcs_amts = '';
// 						alert(tcs_app_flag)
						if(tcs_app_flag == 'Y'){
							
							tcs_nm = document.getElementById('tcs_nm').value;
							tcs_fact = document.getElementById('tcs_fact').value;
							tax_tcs_amts = document.getElementById('tax_tcs_amts').value;
						}
						
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
						if(ad!=undefined)
							{
							if(ad[0].checked){
								ad_flag="Y";
								}else
									{
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
							
							if(type=='vbs'){	
								document.getElementById("save").style.visibility="visible";
								{
									newWindow2 = window.open("rpt_preview_invoice_gst.jsp?GST_INVOICE_SEQ_NO="+GST_INVOICE_SEQ_NO+"&invadjustcur="+invadjustcur+"&hlpl_inv_no_prev="+hlpl_inv_no_prev+"&gross_amt_inr="+gross_amt_inr+"&taxstructcd="+taxstructcd+"&contact_person="+contact_person+"&contact_addr_flag="+contact_addr_flag+"&ad_flag="+ad_flag+"&dis_flag="+dis_flag+"&inr_flag="+inr_flag+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&inv_dt="+inv_dt+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&mapping_id="+mapping_id+"&print_permission="+print_permission+"&tcs_nm="+tcs_nm+"&tcs_fact="+tcs_fact+"&tax_tcs_amts="+tax_tcs_amts+"&tcs_app_flag="+tcs_app_flag,"Rpt_PreviewInvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,status=1,menubar=1");
								}
							}else{
								if(document.getElementById("checkPost").value==''){
									alert("Please select Check Post!!");
								}else{
									newWindow2 = window.open("frm_form_402.jsp?GST_INVOICE_SEQ_NO="+GST_INVOICE_SEQ_NO+"&invadjustcur="+invadjustcur+"&hlpl_inv_no_prev="+hlpl_inv_no_prev+"&gross_amt_inr="+gross_amt_inr+"&taxstructcd="+taxstructcd+"&contact_person="+contact_person+"&contact_addr_flag="+contact_addr_flag+"&ad_flag="+ad_flag+"&dis_flag="+dis_flag+"&inr_flag="+inr_flag+"&customer_abbr="+customer_abbr+"&customer_cd="+customer_cd+"&fgsa_no="+fgsa_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+"&contract_type="+contract_type+"&customer_plant_nm="+customer_plant_nm+"&customer_plant_seq_no="+customer_plant_seq_no+"&inv_dt="+inv_dt+"&bill_period_start_dt="+bill_period_start_dt+"&bill_period_end_dt="+bill_period_end_dt+"&due_dt="+due_dt+"&month="+month+"&year="+year+"&bill_cycle="+bill_cycle+"&exchg_rate_cd="+exchg_rate_cd+"&exchg_rate_cal_method="+exchg_rate_cal_method+"&hlpl_inv_seq_no="+hlpl_inv_seq_no+"&inv_financial_year="+inv_financial_year+"&mapping_id="+mapping_id+"&print_permission="+print_permission+"&tcs_nm="+tcs_nm+"&tcs_fact="+tcs_fact+"&tax_tcs_amts="+tax_tcs_amts+"&tcs_app_flag="+tcs_app_flag,"Rpt_PreviewInvoiceDtl","top=10,left=10,width=1050,height=750,scrollbars=1,status=1,menubar=1");
								}
							}
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
				<%} %>
<!-- /.box-footer-->
    
</div>
<!-- /.box -->

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
			else if(activity.equalsIgnoreCase("insert")){%>
			<input type="hidden" name="modifyadjsignhid" value="<%=modifyadjsign%>">
				<input type="hidden" name="modifyadjcurrencyhid" value="<%=modifyadjcurrency%>">
				<input type="hidden" name="modifyadjrecvflaghid" value="<%=modifyadjrecvflag%>">
			<%} %>
			<input type="hidden" name="taxsize" value="<%=customer_Invoice_Tax_Code.size()%>">
			<input type="hidden" name="invoice_sales_price_inr" value="<%=invoice_sales_rate_inr %>">
			<input type="hidden" name="call_flag_sale_price" value="N">
			<%if(discount_flag_apply.equalsIgnoreCase("Y") ){%>
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
			applydiscount(document.forms[0].discount_check,'<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','12','<%=invoice_Period_Dates.size()%>','F','load'),discount_setter(document.forms[0].discount_check);
			</script>
			<%}else if(exchg_rate_cal_method.equalsIgnoreCase("A")){%>
			<script>
			applydiscount(document.forms[0].discount_check,'<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=invoice_Period_Dates.size()%>','<%=invoice_Period_Dates.size()%>','F','load'),discount_setter(document.forms[0].discount_check);
			</script>
			<%} %>
			<%} %>
			<% 
			{%>
			 <%if(AdjustFlagForMst.equalsIgnoreCase("Y")){ %>
			<script>
			var	Adv_submitted_flag1=document.forms[0].Adv_submitted_flag.value;
			 if(Adv_submitted_flag1=='Y')
			 {
			 	document.forms[0].adjust[0].checked=true;
				displaydetailsmodify();
			 } 
			</script>
			<%} %>
			<%} %>
<input name="contract_type_tax" type="hidden" value="<%=contract_type%>"> 
<input type="hidden" name="pay_type1" value="<%=pay_mode%>">
<input type="hidden" name="advflg" value="<%=advanceAdjFlg%>">
<input type="hidden" name="date_flag" value="<%=date_flag%>">
<input type="hidden" name="contact_Customer_Name" value="<%=contact_Customer_Name%>">




<div class="box-footer">
<div class="row">
<div class="col-sm-2 text-left">
	<button type="button" class="btn btn-primary" onclick="togglestatus('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=mapping_id%>','<%=print_permission%>','<%=contact_addr_flag%>','<%=GST_INVOICE_SEQ_NO%>','form402');">View FORM 402</button>
</div>
	

<div class="col-sm-10 text-right">

	<button type="button" class="btn btn-warning" name="reSetPage" value="Reset" onclick="resetPageContent('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=hlpl_inv_seq_no%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=mapping_id %>','<%=sn_start_dt%>','<%=sn_end_dt%>');"> Reset </button>
<%	
	if(exchg_rate_cal_method.equalsIgnoreCase("D"))	{	%>
		<button type="button" class="btn btn-success" name="verify" id="verify" value="Verify Before Submit" onclick="togglestatus('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=mapping_id%>','<%=print_permission%>','<%=contact_addr_flag%>','<%=GST_INVOICE_SEQ_NO%>','vbs');"> Verify Before Submit </button>
		<button type="button" class="btn btn-primary" style="visibility: hidden;" name="save" id="save"  value="Submit" onclick="doSubmit('1','D','<%=invoice_Period_Dates.size()%>');">Submit</button>
<!-- 		style="visibility: hidden;"  -->
		
	<%	}
	else if(exchg_rate_cal_method.equalsIgnoreCase("A")){	%>	
		<button type="button" class="btn btn-success" name="verify" id="verify" value="Verify Before Submit" onclick="togglestatus('<%=customer_abbr%>','<%=customer_cd%>','<%=fgsa_no%>','<%=fgsa_rev_no%>','<%=sn_no%>','<%=sn_rev_no%>','<%=contract_type%>','<%=customer_plant_nm%>','<%=customer_plant_seq_no%>','<%=bill_period_start_dt%>','<%=bill_period_end_dt%>','<%=due_dt%>','<%=month%>','<%=year%>','<%=bill_cycle%>','<%=exchg_rate_cd%>','<%=exchg_rate_cal_method%>','<%=hlpl_inv_seq_no%>','<%=inv_financial_year%>','<%=mapping_id%>','<%=print_permission%>','<%=contact_addr_flag%>','<%=GST_INVOICE_SEQ_NO%>','vbs');"> Verify Before Submit </button>
		<button type="button" style="visibility: hidden;" class="btn btn-primary" name="save" id="save" value="Submit" onclick="doSubmit('<%=invoice_Period_Dates.size()%>','A','<%=invoice_Period_Dates.size()%>');">Submit</button>
<!-- 		 style="visibility: hidden;" -->
		  
	<%} %>
		
</div>
</div>
</div>	
	</form>
</div>
</div>
</div>

</section>

<!-- jQuery -->
<script src="../js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<script src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script src="../responsive/toastr/toastr.min.js"></script>
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


$(function () {
$('#datetimepicker5').datepicker({
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
todayHighlight: true
});
});



$(function () {
$('#datetimepicker6').datepicker({
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
todayHighlight: true
});
});


$(function () {
$('#datetimepicker7').datepicker({
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
todayHighlight: true
});
});

function unlink_Driver(licn_no,trans_cd,truck_cd,emp_cd){
	$.post("../servlet/Frm_Transporter?option=savelinkData&submitType=modify&ajaxReq=Y&linked_truck_no="+truck_cd+"&linked_trans="+trans_cd+"&linked_lic_no="+licn_no+"&user_cd="+emp_cd,function(responseText) {   
		
   	 	if(responseText == 'refresh'){
   	 		location.reload();
   	 	}
   	 
    });
}

function link_Driver(truck_cd,emp_cd){
	var selDriver = document.getElementById('selDriver').value;
	
	if(selDriver!=""){
		
		var temp_arr = selDriver.split("@@");
		var licn_no = temp_arr[0];
		var trans_cd = temp_arr[1];
		
		$.post("../servlet/Frm_Transporter?option=savelinkData&submitType=save&ajaxReq=Y&status_flag=Y&truck_id="+truck_cd+"&trans_cd="+trans_cd+"&license_no="+licn_no+"&user_cd="+emp_cd,function(responseText) {   
			
	   	 	if(responseText == 'refresh'){
	   	 		
	   	 		location.reload();
	
	   	 	}
    	});
	}else{
		alert('Please select Driver Name first!!')
	}
}

$(document).ready(function() {
	$(document).on("click", "#unlinkChkPost", function() {
		
	 var mydata = $("form#invGen").serialize();
	 var msg = "";
		$.ajax({
		        type : "POST",
		        url : "../Frm_CheckPost?option=unlinkFromInvoiceLevel",
		        cache : false,
		        data: mydata,
		        success : function(responseJson) {
		        	var string1 = JSON.stringify(responseJson);
		        	var arr = JSON.parse(string1);
		        	var $chkpostDrop = $("#checkPost");
			    	$chkpostDrop.find("option").remove(); 
			    	$.each(arr.checkpostList, function(index, value) {
	            		if(index == 0){
// 	    	 				$("<option>").val("").text("-SELECT-").appendTo($chkpostDrop);
	    	 				msg = value.msg;
	    	 			}
	    	 			$("<option>").val(value.view_chkpost_name).text(value.view_chkpost_name).appendTo($chkpostDrop);
	            	});
			    	
			    	if(msg.includes("Success:")){
			   	 		toastr["success"](msg);
			   	 		$("#chkSpan").css("display", "none");
			   	 	}else if(msg.includes("Prevented:")){
			   	 		toastr["error"](msg);
			   	 	} 
		        }
		 });
	});
});

</script>
</body>
</html>
