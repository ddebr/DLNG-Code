<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*" errorPage="" %>
<%@ include file="../sess/Expire.jsp"%>
<html>
<head>
<title>DLNG Gen Debit/Credit Note</title>

<link href="../css/guidefaultGeneral.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../css/utilities.css">


<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css">
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>

<style type="text/css">
.select {
    height: 27px;
} 
</style>
<script type="text/javascript">
function checkNum(obj){
	var reg = /^[0-9.,]+$/; 
	var val = obj.value; 
	if(reg.test(val)){
// 		alert('match')
	}else{
		obj.value="";
		alert('Please enter valid number!!')
	} 
}
function validateDate(obj)
{
	
	//This function takes date in dd/MM/yyyy format and validate for
	//valid month,no of days depending on month and year.
	//like checks for 30/31/28-29 days for valid months i.e. months >0 and 
	//	months <=12
	dateutilFlag = true;
	dateValue= obj.value;
	dateValue = trim(dateValue);
	len = dateValue.length;
    if(len!=0){
	var monthname = new Array("","January", "February", "March", "April","May","June","July",
				   "August", "September","October","November","December" );
	fullyear = "";
	msg = "Incorrect Date Format. \r\n\r\nPlease use dd/mm/yyyy format";
	//if(len != 8)
	//	dateutilFlag = false;


	firstIndex = dateValue.indexOf('/');
	lastIndex = dateValue.lastIndexOf('/');
		
	
		if(firstIndex ==0  || firstIndex > 2){
		dateutilFlag = false;
		}
	
		if(lastIndex <3 || lastIndex > 5){
		dateutilFlag =false;
		}
    if(dateutilFlag){
		mydd = dateValue.substring(0,firstIndex);
       
		
		if(mydd.length == 2){
		 x_x = mydd.substring(0,1);
		 y_y = mydd.substring(1);
		if (!(x_x == '0')) {
		if(!isDigit(x_x)) 	dateutilFlag = false;
		}
		if (!(y_y == '0')) {
		if(!isDigit(y_y)) 	dateutilFlag = false;
		}
		}

		if(mydd.length == 1){
			if(!isDigit(mydd)) 	dateutilFlag = false;
		        mydd = "0" + mydd;
		}
		mymm = dateValue.substring(firstIndex+1,lastIndex);
	     if(mymm.length == 2){
		 x_x_x = mymm.substring(0,1);
		 y_y_y = mymm.substring(1);
		if (!(x_x_x == '0')) {
		if(!isDigit(x_x_x)) 	dateutilFlag = false;
		}

		if (!(y_y_y == '0')) {
		if(!isDigit(y_y_y)) 	dateutilFlag = false;
		}
		}

		if(mymm.length == 1) mymm = "0" + mymm;
		


		myyy = dateValue.substring(lastIndex+1);
		if(myyy.length > 4) dateutilFlag=false;
		if(myyy.length < 4)
		dateutilFlag =false;
		if(myyy.length == 2)
		    myyy = "20" + myyy;
		fullyear = myyy;

		if(!isDigit(fullyear)){
		dateutilFlag = false;
		}
		dateValue = mydd + "/" + mymm + "/" + myyy;
	}
	if(dateutilFlag)
     {
		day = getDay(dateValue);
		mon = getMon(dateValue);
		maxDay = 31;
	       
		if(mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12)
			maxDay = 31;
		if(mon == 4 || mon == 6 || mon == 9 || mon == 11)
			maxDay = 30;

		if(mon == 2)
                    {
 			year = getYear(dateValue)
			yearStr = year / 4 +"";
			if(yearStr.indexOf('.') == -1)
				maxDay = 29;
			else
				maxDay = 28;
		    }
	
		if(mon<=0 || mon > 12)
				dateutilFlag = false;
		if(day > maxDay){
				dateutilFlag = false;
				msg = monthname[mon] + " of " +fullyear + " does not have " + day + " days.";
				}
	        }
		
	   if(!dateutilFlag){
		   alert('in')
	   alert(msg);
	   obj.value="";
	 //obj.focus();
	   return false;
	   }
	   else{
// 		   alert('in1')
	   dateValue=dateValue.substring(0,6) + dateValue.substring(6);
	   obj.value=dateValue;
	   return true;
	   }
      }else{
//     	  alert('in2')
        return false;
      }    
	} 
function doSubmit()
{
	var customer_cd = document.forms[0].customer_cd.value;   
    var fin_yr =document.forms[0].fin_yr.value;	
	var con_type =document.forms[0].con_type.value;
	var hlpl_no =document.forms[0].hlpl_no.value;
	var plant_no =document.forms[0].plant_no.value;
	var fgsa_no =document.forms[0].fgsa_no.value;
	var sn_no =document.forms[0].sn_no.value;
	var criteria =document.forms[0].criteria.value;
	var fgsa_rev_no =document.forms[0].fgsa_rev_no.value;
	var dr_cr_dt =document.forms[0].dr_cr_dt.value;
	var remark =document.forms[0].remark.value;
	var cr_dr = document.forms[0].cr_dr.value;
	var con_type = document.forms[0].con_type.value;
	var paydue='';
	
	if(cr_dr=='dr'){
		paydue=document.forms[0].payduedt.value;
	}
	
    if(customer_cd!="" && fin_yr!="" && criteria!="" && con_type!="" && hlpl_no!="" && plant_no!="" && fgsa_no!="" && sn_no!="" && dr_cr_dt!="")
    {
    	if(paydue=='' && cr_dr=='dr'){
			alert("Please Enter Payment Due Date");
		}else{
			if(criteria.indexOf('DIFF-TAX')!=-1 && con_type!='V'){
				
				var split_qty=document.forms[0].split_qty.value;
				var taxlen=document.forms[0].hid_tax_str.value;
				var cnt=0;
				var tax_str='',msg='',flag_rt='Y';
				for(var i=0;i<taxlen;i++){
					 if(document.getElementById("tax_"+i).value!=''){
						tax_str+=document.getElementById('hid_tax_cd'+i).value+"@"+document.getElementById("tax_"+i).value+",";
					}else{
						cnt++;
					} 
				}
				if(cnt>0){
					msg+="Please enter Tax Rate For all Tax";
					flag_rt='N';
				}
				if(split_qty==''){
					msg+="Please enter Split Quantity";
					flag_rt='N';
				}
				if(flag_rt=='Y'){
					document.forms[0].Stax_str.value=tax_str;
					var a = confirm("Do You Want To Submit Data Regarding Dr./Cr. Note Details ???");
			    	if(a)
			    	{
			    		document.forms[0].submit();    
			    	}
				} else{
					alert(msg);
				}
			}else{
		    	var a = confirm("Do You Want To Submit Data Regarding Dr./Cr. Note Details ???");
		    	if(a)
		    	{
		    		document.forms[0].submit();    
		    	}
			}
    	}
    }
    else
    {
     	alert("First, Please Enter The Debit/Credit Note Date !!!");
    }
}
function change_inv_dt()
{
		if(document.forms[0].chng_inv_dt.checked==true){
		document.getElementById('chng').style.display="block";	
		}
		else{
		document.getElementById('chng').style.display="none";	
		}
}
function chnge_dt()
{
		if(document.forms[0].chng_pay_dt.checked==true){
		document.getElementById('pay_dt').style.display="block";	
		}
		else{
		document.getElementById('pay_dt').style.display="none";	
		}
}
function chng_tax()
{
		if(document.forms[0].chng_tax_struc.checked==true){
			document.getElementById('tax_structure').style.display="block";	
		}
		else{
			document.getElementById('tax_structure').style.display="none";	
		}
}
function change_pin()
{
		if(document.forms[0].chng_pin.checked==true){
			document.getElementById('pan_tin_no').style.display="block";
			document.getElementById('cst_pan_no').style.display="block";
			document.getElementById('add').style.display="block";
		}
		else{
			document.getElementById('pan_tin_no').style.display="none";	
			document.getElementById('cst_pan_no').style.display="none";	
			document.getElementById('add').style.display="none";
		}
}
function change_line_item()
{
if(document.forms[0].chng_desc_items.checked==true){
			document.getElementById('remarks1').style.display="block";
			document.getElementById('remarks2').style.display="block";
			document.getElementById('remarks3').style.display="block";
		}
		else{
			document.getElementById('remarks2').style.display="none";	
			document.getElementById('remarks1').style.display="none";
			document.getElementById('remarks3').style.display="none";		
		}
}
function inv_qty_dt1(sz){
	var temp_dates="";
	var tempdates="";
	var quantity="Dates for which quantity is to be changed are :"
	document.forms[0].remark.value='';
	
	for(var i=0;i<sz;i++){
		if(document.getElementById('inv_qty_dt'+i).checked){
		 temp_dates+=','+document.getElementById('dates'+i).value;
		}
	}
	
	tempdates=temp_dates.substring(1,temp_dates.length);
	document.forms[0].chkval.value=temp_dates;
	document.forms[0].remark.value=quantity+"["+temp_dates.replace(',','')+"]";
}
function change_gstno(oldval,newval,val)
{
	if(!newval==""){
		document.forms[0].remark.value+=" [change in "+val+" : "+oldval+"->"+newval+"] ";
	}
}
function calcExgRateAmount()
{
	var qty = document.forms[0].qty.value;  
	var exg_rt = document.forms[0].exg_rt.value;
	var dr_cr_exg_rt = document.forms[0].dr_cr_exg_rt.value;
	var temp_exg_rate="Change in Exchange rate"; 
	var unit="Rs/$";
	var exg_rate_dr_cr=document.forms[0].remark.value+"--"+temp_exg_rate+" - "+dr_cr_exg_rt+" "+unit; 
	document.forms[0].remark.value=exg_rate_dr_cr;
    var dr_cr_gross_amt1 = parseFloat("0");		
    var criteria = document.forms[0].criteria.value;
    var sale_rate =document.forms[0].sale_rate.value;
// 	alert(criteria)
    var dr_cr_sale_rate = "0";
	var temp_sale_rate = sale_rate ;
	var cr_dr = document.forms[0].cr_dr.value;	
	var tax_amt_inr1=document.forms[0].tax_amt_inr.value;
	var gross_amt_usd1=document.forms[0].gross_amt_usd.value;
	var flag = true;
	var date_flag = document.forms[0].date_flag.value;
	var cust_name = document.forms[0].cust_name.value;
	var diff_exg="";
	
	var diff_rate = 0;
	var dr_cr_qty = 0;
	var dr_cr_qty = "";
	var diff_qty = "0";
	var act_qty = "0";
	  
    if(criteria.indexOf('DIFF-QTY')!=-1){
	  
		dr_cr_qty = document.forms[0].dr_cr_qty.value;
		diff_qty = document.forms[0].diff_qty.value;
		act_qty = document.forms[0].diff_qty.value;
		
	}else{
		act_qty = qty;
	}
	  
	if(parseFloat(dr_cr_exg_rt)>0)
	{		
		if(cr_dr=='dr'){
				if(parseFloat(dr_cr_exg_rt)>parseFloat(exg_rt)){
					diff_exg = parseFloat(dr_cr_exg_rt) - parseFloat(exg_rt);
					if(criteria.indexOf('DIFF-PRICE')!=-1 && document.forms[0].nw_sale_rate.value!='' && document.forms[0].nw_sale_rate.value!='0'){
						diff_rate = document.forms[0].diff_rate.value;
						dr_cr_sale_rate = document.forms[0].nw_sale_rate.value;	
						temp_sale_rate = parseFloat(dr_cr_sale_rate) - parseFloat(temp_sale_rate); 
					}
					if(criteria.indexOf('DIFF-QTY')!=-1){
						temp_gross_amt_1 = parseFloat(diff_qty); 
						temp_gross_amt_2 = parseFloat(qty);
					
						if(criteria.indexOf('DIFF-PRICE')!=-1){
							temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(dr_cr_sale_rate);
							temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(diff_rate);
						}else{
							temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(sale_rate);
							temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(sale_rate);
						}
						if(criteria.indexOf('DIFF-EXG')!=-1){
							temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(dr_cr_exg_rt);
							temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(diff_exg);
						}
						dr_cr_gross_amt1 = (parseFloat(temp_gross_amt_1) + parseFloat(temp_gross_amt_2)).toFixed(2);
					
					}else if(criteria.indexOf('DIFF-PRICE')!=-1){
						dr_cr_gross_amt1 = parseFloat(qty) * parseFloat(diff_exg) * parseFloat(diff_rate);
					}else{
						dr_cr_gross_amt1 = parseFloat(qty) * parseFloat(diff_exg) * parseFloat(sale_rate);
					}
// 					alert(temp_gross_amt_1 +"****"+ temp_gross_amt_2 )
					
				}else{
					dr_cr_exg_rt='';
					alert("Debit Note Exchange Rate Should be greater than Invoice Exchange Rate !!!\n Else Select Credit option !!! ");
					//window.close(); //RS25052017.
					document.forms[0].dr_cr_exg_rt.focus();
					document.forms[0].dr_cr_exg_rt.value='';
					flag = false;
				}
		}else if(cr_dr=='cr'){
			if(parseFloat(dr_cr_exg_rt)<parseFloat(exg_rt)){
				diff_exg = parseFloat(exg_rt) - parseFloat(dr_cr_exg_rt);
				if(criteria.indexOf('DIFF-PRICE')!=-1 && document.forms[0].nw_sale_rate.value!='' && document.forms[0].nw_sale_rate.value!='0'){
					diff_rate = document.forms[0].diff_rate.value;
					dr_cr_sale_rate = document.forms[0].nw_sale_rate.value;	
//						alert(dr_cr_sale_rate+"*****"+temp_sale_rate)
					temp_sale_rate = parseFloat(temp_sale_rate) - parseFloat(dr_cr_sale_rate); 
				}
				if(criteria.indexOf('DIFF-QTY')!=-1){
					temp_gross_amt_1 = parseFloat(diff_qty); 
					temp_gross_amt_2 = parseFloat(qty);
				
					if(criteria.indexOf('DIFF-PRICE')!=-1){
						temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(dr_cr_sale_rate);
						temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(diff_rate);
					}else{
						temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(sale_rate);
						temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(sale_rate);
					}
					if(criteria.indexOf('DIFF-EXG')!=-1){
						temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(dr_cr_exg_rt);
						temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(diff_exg);
					}
					dr_cr_gross_amt1 = (parseFloat(temp_gross_amt_1) + parseFloat(temp_gross_amt_2)).toFixed(2);
				
				}else if(criteria.indexOf('DIFF-PRICE')!=-1){
					dr_cr_gross_amt1 = parseFloat(qty) * parseFloat(diff_exg) * parseFloat(diff_rate);
				}else{
					dr_cr_gross_amt1 = parseFloat(qty) * parseFloat(diff_exg) * parseFloat(sale_rate);
				}
				
			}else {
				alert("Credit Note Exchange Rate Should be less than Invoice Exchange Rate !!!\n Else Select Debit option  ");
				dr_cr_exg_rt='';	
				document.forms[0].dr_cr_exg_rt.focus();
				document.forms[0].dr_cr_exg_rt.value='';
				//window.close();//RS25052017
				flag = false;
			}
		}
	}
	var customer_cd = document.forms[0].customer_cd.value;	
	var st_dt = document.forms[0].st_dt.value;
	var end_dt = document.forms[0].end_dt.value;
	var fin_yr = document.forms[0].fin_yr.value;
	var con_type = document.forms[0].con_type.value;
	var hlpl_no = document.forms[0].hlpl_no.value;
	var plant_no = document.forms[0].plant_no.value;
	var fgsa_no = document.forms[0].fgsa_no.value;
	var sn_no = document.forms[0].sn_no.value;
	var fgsa_rev_no = document.forms[0].fgsa_rev_no.value;
	var sn_rev_no = document.forms[0].sn_rev_no.value;
	var tax_struc_cd = document.forms[0].tax_struc_cd.value;
	
	/* var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var form_id = document.forms[0].form_id.value;
	var form_nm = document.forms[0].form_nm.value;	
	 */
	var inv_dt =  document.forms[0].inv_dt.value;
	var inv_no =  document.forms[0].inv_no.value;	
	var inv_dt =  document.forms[0].inv_dt.value;
	var qty =  document.forms[0].qty.value;	
	var gross_amt =  document.forms[0].gross_amt.value;
	var net_amt =  document.forms[0].net_amt.value;
	var dr_cr_dt =  document.forms[0].dr_cr_dt.value;
	var bill_dt = document.forms[0].bill_dt.value;
	var from_dt = document.forms[0].from_dt.value;
	var to_dt = document.forms[0].to_dt.value;
	var dr_cr_seq_no = document.forms[0].dr_cr_seq_no.value;
	var dr_cr_ref_inv_no = document.forms[0].dr_cr_ref_inv_no.value;
	
	var url ="frm_dr_cr_note.jsp?dr_cr_exg_rt="+dr_cr_exg_rt+"&exg_rt="+exg_rt+"&sale_rate="+sale_rate+
			"&inv_dt="+inv_dt+"&inv_no="+inv_no+"&qty="+qty+"&gross_amt="+gross_amt+"&net_amt="+net_amt+
			"&st_dt="+st_dt+"&end_dt="+end_dt+"&fin_yr="+fin_yr+"&bscode="+customer_cd+"&cr_dr="+cr_dr+
			"&criteria="+criteria+"&con_type="+con_type+"&hlpl_no="+hlpl_no+"&plant_no="+plant_no+
			"&fgsa_no="+fgsa_no+"&sn_no="+sn_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_rev_no="+sn_rev_no+
			"&tax_struc_cd="+tax_struc_cd+"&dr_cr_gross_amt2="+dr_cr_gross_amt1+"&remark="+exg_rate_dr_cr+
			"&tax_amt_inr="+tax_amt_inr1+"&gross_amt_usd="+gross_amt_usd1+"&dr_cr_dt="+dr_cr_dt+
			"&dr_cr_sale_rate="+dr_cr_sale_rate+"&cust_name="+cust_name+"&bill_dt="+bill_dt+"&from_dt="+from_dt+
			"&to_dt="+to_dt+"&diff_exg="+diff_exg+"&diff_rate="+diff_rate+"&dr_cr_qty="+dr_cr_qty+"&diff_qty="+diff_qty+
			"&dr_cr_seq_no="+dr_cr_seq_no+"&dr_cr_ref_inv_no="+dr_cr_ref_inv_no;
// 	alert(url)
	if(flag) {
		location.replace(url);	
	}
}
function change_rate()
{
 	var qty = document.forms[0].qty.value;  
	var exg_rt = document.forms[0].exg_rt.value;
	var	dr_cr_sale_rate=document.forms[0].nw_sale_rate.value;
	var temp_sale_rate="Change in Price/Tariff"; 
	var unit="$/MMBTU";
	var sale_rate_dr_cr=document.forms[0].remark.value+"--"+temp_sale_rate+" - "+dr_cr_sale_rate+" "+unit; 
	document.forms[0].remark.value=sale_rate_dr_cr;
    var dr_cr_gross_amt1 = parseFloat("0");		
	var sale_rate =document.forms[0].sale_rate.value;
	var criteria = document.forms[0].criteria.value;
	var cr_dr = document.forms[0].cr_dr.value;
	var customer_cd = document.forms[0].customer_cd.value;	
	var st_dt = document.forms[0].st_dt.value;
	var end_dt = document.forms[0].end_dt.value;
	var fin_yr = document.forms[0].fin_yr.value;
	var con_type = document.forms[0].con_type.value;
	var hlpl_no = document.forms[0].hlpl_no.value;
	var plant_no = document.forms[0].plant_no.value;
	var fgsa_no = document.forms[0].fgsa_no.value;
	var sn_no = document.forms[0].sn_no.value;
	var fgsa_rev_no = document.forms[0].fgsa_rev_no.value;
	var sn_rev_no = document.forms[0].sn_rev_no.value;
	var tax_struc_cd = document.forms[0].tax_struc_cd.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var form_id = document.forms[0].form_id.value;
	var form_nm = document.forms[0].form_nm.value;	
	var inv_dt =  document.forms[0].inv_dt.value;
	var inv_no =  document.forms[0].inv_no.value;	
	var inv_dt =  document.forms[0].inv_dt.value;
	var qty =  document.forms[0].qty.value;	
	var gross_amt =  document.forms[0].gross_amt.value;
	var net_amt =  document.forms[0].net_amt.value;
	var tax_amt_inr1=document.forms[0].tax_amt_inr.value;
	var gross_amt_usd1=document.forms[0].gross_amt_usd.value;
	var dr_cr_dt =  document.forms[0].dr_cr_dt.value;
	var date_flag = document.forms[0].date_flag.value;
	var cust_name = document.forms[0].cust_name.value;
	var bill_dt = document.forms[0].bill_dt.value;
	var from_dt = document.forms[0].from_dt.value;
	var to_dt = document.forms[0].to_dt.value;
	var dr_cr_seq_no = document.forms[0].dr_cr_seq_no.value;
	var dr_cr_ref_inv_no = document.forms[0].dr_cr_ref_inv_no.value;
	

	  var dr_cr_exg = "0";
	  var temp_exg = "0" ;
	  var diff_rate = "0";
	  var diff_exg = "0";
	  var dr_cr_qty = "0";
	  var diff_qty = "0";
	  var act_qty = "0";
	  var temp_gross_amt_1 = 0 ;
	  var temp_gross_amt_2 = 0 ;
	
	if(dr_cr_sale_rate == ''){
		dr_cr_sale_rate = 0 ; 
	}		
	  if(criteria.indexOf('DIFF-QTY')!=-1){
			dr_cr_qty = document.forms[0].dr_cr_qty.value;
			diff_qty = document.forms[0].diff_qty.value;
			act_qty = document.forms[0].diff_qty.value;
		}else{
			act_qty = qty;
		}
	  
		if(cr_dr=='dr'){
			if(parseFloat(dr_cr_sale_rate)>parseFloat(sale_rate)){
				diff_rate = parseFloat(dr_cr_sale_rate) - parseFloat(sale_rate);
// 				alert(criteria.indexOf('DIFF-EXG'))
				if(criteria.indexOf('DIFF-EXG')!=-1 && document.forms[0].dr_cr_exg_rt.value!='' && document.forms[0].dr_cr_exg_rt.value!='0'){
					diff_exg = document.forms[0].diff_exg.value;
					dr_cr_exg = document.forms[0].dr_cr_exg_rt.value;	
// 						alert(dr_cr_sale_rate+"*****"+temp_sale_rate)
					temp_exg = parseFloat(dr_cr_exg) - parseFloat(temp_exg); 
				}
				
				if(criteria.indexOf('DIFF-QTY')!=-1){
					temp_gross_amt_1 = parseFloat(diff_qty); 
					temp_gross_amt_2 = parseFloat(qty);
				
					if(criteria.indexOf('DIFF-PRICE')!=-1){
						temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(dr_cr_sale_rate);
						temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(diff_rate);
					}else{
						temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(sale_rate);
						temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(sale_rate);
					}
					if(criteria.indexOf('DIFF-EXG')!=-1){
						temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(dr_cr_exg);
						temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(diff_exg);
					}else{
						temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(exg_rt);
						temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(exg_rt);
					}
					dr_cr_gross_amt1 = (parseFloat(temp_gross_amt_1) + parseFloat(temp_gross_amt_2)).toFixed(2);
				
				}else if(criteria.indexOf('DIFF-EXG')!=-1){
					dr_cr_gross_amt1 = parseFloat(qty) * parseFloat(diff_exg) * parseFloat(diff_rate);
				}else{
					dr_cr_gross_amt1 = parseFloat(qty) * parseFloat(exg_rt) * parseFloat(diff_rate);
				}
				
			}else{
				dr_cr_sale_rate='';
				alert("Debit Note Sales Rate Should be greater than Invoice Sales Rate !!!");
			}
			
		}else if(cr_dr=='cr'){
			if(parseFloat(dr_cr_sale_rate)<parseFloat(sale_rate)){
				
				diff_rate = parseFloat(sale_rate) - parseFloat(dr_cr_sale_rate);
				if(criteria.indexOf('DIFF-EXG')!=-1 && document.forms[0].dr_cr_exg_rt.value!='' && document.forms[0].dr_cr_exg_rt.value!='0'){
					diff_exg = document.forms[0].diff_exg.value;
					dr_cr_exg = document.forms[0].dr_cr_exg_rt.value;	
//						alert(dr_cr_sale_rate+"*****"+temp_sale_rate)
					temp_exg = parseFloat(temp_exg)-parseFloat(dr_cr_exg); 
				}
				
				if(criteria.indexOf('DIFF-QTY')!=-1){
					temp_gross_amt_1 = parseFloat(diff_qty); 
					temp_gross_amt_2 = parseFloat(qty);
				
					if(criteria.indexOf('DIFF-PRICE')!=-1){
						temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(dr_cr_sale_rate);
						temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(diff_rate);
					}else{
						temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(sale_rate);
						temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(sale_rate);
					}
					if(criteria.indexOf('DIFF-EXG')!=-1){
						temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(dr_cr_exg);
						temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(diff_exg);
					}else{
						temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(exg_rt);
						temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(exg_rt);
					}
					dr_cr_gross_amt1 = (parseFloat(temp_gross_amt_1) + parseFloat(temp_gross_amt_2)).toFixed(2);
				
				}else if(criteria.indexOf('DIFF-EXG')!=-1){
					dr_cr_gross_amt1 = parseFloat(qty) * parseFloat(diff_exg) * parseFloat(diff_rate);
				}else{
					dr_cr_gross_amt1 = parseFloat(qty) * parseFloat(exg_rt) * parseFloat(diff_rate);
				}
				
			}else{
				dr_cr_sale_rate='';
				alert("Credit Note Sales Rate Should be less than Invoice Sales Rate !!!");
			}
		}
// 		alert(diff_rate)
		var url ="frm_dr_cr_note.jsp?exg_rt="+exg_rt+"&sale_rate="+sale_rate+"&inv_dt="+inv_dt+"&inv_no="+inv_no+
				"&qty="+qty+"&gross_amt="+gross_amt+"&net_amt="+net_amt+"&st_dt="+st_dt+"&end_dt="+end_dt+
				"&fin_yr="+fin_yr+"&bscode="+customer_cd+"&cr_dr="+cr_dr+"&criteria="+criteria+
				"&con_type="+con_type+"&hlpl_no="+hlpl_no+"&plant_no="+plant_no+"&fgsa_no="+fgsa_no+
				"&sn_no="+sn_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_rev_no="+sn_rev_no+"&tax_struc_cd="+tax_struc_cd+
				"&form_nm="+form_nm+"&form_id="+form_id+"&write_permission="+write_permission+
				"&delete_permission="+delete_permission+"&print_permission="+print_permission+
				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+
				"&remark="+sale_rate_dr_cr+"&dr_cr_sale_rate="+dr_cr_sale_rate+"&dr_cr_gross_amt2="+dr_cr_gross_amt1+
				"&tax_amt_inr="+tax_amt_inr1+"&gross_amt_usd="+gross_amt_usd1+"&dr_cr_dt="+dr_cr_dt+
				"&dr_cr_exg_rt="+dr_cr_exg+"&cust_name="+cust_name+"&bill_dt="+bill_dt+"&from_dt="+from_dt+
				"&to_dt="+to_dt+"&diff_rate="+diff_rate+"&diff_exg="+diff_exg+"&dr_cr_qty="+dr_cr_qty+"&diff_qty="+diff_qty+
				"&dr_cr_seq_no="+dr_cr_seq_no+"&dr_cr_ref_inv_no="+dr_cr_ref_inv_no;
	    location.replace(url);
}

function calculate_diff_qtytax(tax_str_nm){
	var dr_cr_dt =  document.forms[0].dr_cr_dt.value;
	var cr_dr = document.forms[0].cr_dr.value;
	var dr_cr_seq_no = document.forms[0].dr_cr_seq_no.value;
	var dr_cr_ref_inv_no = document.forms[0].dr_cr_ref_inv_no.value;
	
	if(dr_cr_dt!=''){
		
		var split_qty=document.forms[0].split_qty.value;
		var qty =  document.forms[0].qty.value;	
		
		if(parseFloat(split_qty)<=parseFloat(qty)){
			var taxlen=document.forms[0].hid_tax_str.value;
			//alert("taxlen--"+taxlen);
			var str='',cnt=0,taxnm='',ori_tax_rt='',cnt1=0;
			for(var i=0;i<taxlen;i++){
				if(document.getElementById("tax_"+i).value!=''){
					var ori_tax_rt=tax_str_nm.split(",");
					var new_tax=document.getElementById("tax_"+i).value;
					if(cr_dr=='dr'){
						//if(parseFloat(new_tax)<parseFloat(ori_tax_rt)){
							if(parseFloat(new_tax)==0){
							//alert("New Tax Rate Cannot be more than Original Tax Rate");
							document.getElementById("tax_"+i).value='';
							cnt1++;
						}
					}else if(cr_dr=='cr'){
						//if(parseFloat(new_tax)>parseFloat(ori_tax_rt)){
							if(parseFloat(new_tax)==0){
							//alert("New Tax Rate Cannot be Less than Original Tax Rate");
							document.getElementById("tax_"+i).value='';
							cnt1++;
						}
					}
					if(cnt1==0){
						str+=document.getElementById("tax_"+i).value+",";
						//alert("taxlen--"+taxlen);
						taxnm+=document.getElementById("hid_tax_nm"+i).value+",";
						//alert("taxlen--"+taxlen);
						cnt++;
					}
				}
			}
			if(cnt1>0){
				if(cr_dr=='dr'){
						//alert("New Tax Rate Cannot be more than Original Tax Rate");
					alert("New Tax Rate Cannot be Zero");
				}else if(cr_dr=='cr'){
						//alert("New Tax Rate Cannot be Less than Original Tax Rate");
					alert("New Tax Rate Cannot be Zero");
				}
			}
			if(str!=''){
				str=str.substring(0,str.length-1);
			}
		}else{
			alert("Split Quantity cannot be more than Invoice Quantity");
			document.forms[0].split_qty.value='';
		}
// 		alert("dr_cr_gross_amt1--"+cnt+"--"+split_qty);
		if(parseFloat(cnt)>0 && split_qty!=''){
				//document.getElementById("remarks1").style.display="block";
				var customer_cd = document.forms[0].customer_cd.value;	
				var cust_name=document.forms[0].cust_name.value;
				var st_dt = document.forms[0].st_dt.value;
				var exg_rt = document.forms[0].exg_rt.value;
				var end_dt = document.forms[0].end_dt.value;
				var fin_yr = document.forms[0].fin_yr.value;
				var hlpl_no = document.forms[0].hlpl_no.value;
				var plant_no = document.forms[0].plant_no.value;
				var fgsa_no = document.forms[0].fgsa_no.value;
				var sn_no = document.forms[0].sn_no.value;
				var fgsa_rev_no = document.forms[0].fgsa_rev_no.value;
				var sn_rev_no = document.forms[0].sn_rev_no.value;
				var tax_struc_cd = document.forms[0].tax_struc_cd.value;
				var write_permission = document.forms[0].write_permission.value;
				var delete_permission = document.forms[0].delete_permission.value;
				var print_permission = document.forms[0].print_permission.value;
				var approve_permission = document.forms[0].approve_permission.value;
				var audit_permission = document.forms[0].audit_permission.value;
				var form_id = document.forms[0].form_id.value;
				var form_nm = document.forms[0].form_nm.value;	
				var inv_dt =  document.forms[0].inv_dt.value;
				var inv_no =  document.forms[0].inv_no.value;	
				var con_type = document.forms[0].con_type.value;
				var gross_amt =  document.forms[0].gross_amt.value;
				var net_amt =  document.forms[0].net_amt.value;
				var sale_rate =document.forms[0].sale_rate.value;
				var criteria = document.forms[0].criteria.value;
				//alert("dr_cr_gross_amt1--after"+inv_no);
					
				var tax_amt_inr1=document.forms[0].tax_amt_inr.value;
				var gross_amt_usd1=document.forms[0].gross_amt_usd.value;
				var paydue='';
				if(cr_dr=='dr'){
					paydue=document.forms[0].payduedt.value;
				}
				var dr_cr_gross_amt1='';
				if(exg_rt!=''){
					dr_cr_gross_amt1 = parseFloat(exg_rt) * parseFloat(""+sale_rate) * parseFloat(""+split_qty);
				}else{
					dr_cr_gross_amt1 =  parseFloat(""+sale_rate) * parseFloat(""+split_qty);
				}
				var url ="frm_dr_cr_note.jsp?exg_rt="+exg_rt+"&sale_rate="+sale_rate+"&inv_dt="+inv_dt+"&inv_no="+inv_no+"&qty="+qty+"&gross_amt="+gross_amt+"&net_amt="+net_amt+"&st_dt="+st_dt+"&end_dt="+end_dt+"&fin_yr="+fin_yr+"&bscode="+customer_cd+"&cr_dr="+cr_dr+"&criteria="+criteria+"&con_type="+con_type+"&hlpl_no="+hlpl_no+"&plant_no="+plant_no+"&fgsa_no="+fgsa_no+"&sn_no="+sn_no+"&fgsa_rev_no="+fgsa_rev_no+"&sn_rev_no="+sn_rev_no+"&tax_struc_cd="+tax_struc_cd+"&dr_cr_gross_amt2="+dr_cr_gross_amt1+"&form_nm="+form_nm+"&form_id="+form_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&tax_amt_inr="+tax_amt_inr1+"&gross_amt_usd="+gross_amt_usd1+"&dr_cr_dt="+dr_cr_dt+"&paydue="+paydue+"&split_qty="+split_qty+"&cust_name="+cust_name+"&tax_str="+str+"&taxnm="+taxnm+"&dr_cr_seq_no="+dr_cr_seq_no+"&dr_cr_ref_inv_no="+dr_cr_ref_inv_no;
				location.replace(url);	
		}
	}else{
		alert("First, Enter Debit/Credit Note Date");
		document.forms[0].split_qty.value='';
	}
}
function Doclose(msg)
{
	window.opener.location.reload();
	window.close();
}
function chk_dt() 
{
	var invdt=document.forms[0].inv_dt.value;
	var drcr_dt=document.forms[0].dr_cr_dt.value;
// 	alert(invdt+"****"+drcr_dt)
	var value = compareDate(invdt,drcr_dt);
// 	alert(value)
  	if(value==1)
  	{
    	alert("Dr/Cr Date Should Be Greater Than Invoice Date !!!");
    	document.forms[0].dr_cr_dt.value ="";
    	return false;
  	} 
}

function callParant(fy_yr,bill_dt,msg,from_dt,to_dt,bscode,error_msg){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	window.opener.callFromChild(modCd,formId,subModUrl,fy_yr,bill_dt,msg,from_dt,to_dt,bscode,error_msg);
	window.close();
}
function chkdue_dt1()  
{
	var payduedt=document.forms[0].payduedt.value;
	var drcr_dt=document.forms[0].dr_cr_dt.value;
// 	alert(payduedt+"***"+drcr_dt)
	if(drcr_dt!=''){
		var value = compareDate(drcr_dt,payduedt);
// 		alert(value)
	  	if(value==1)
	  	{
	    	alert("Payment due Date Should Be Greater Than Dr/Cr Date !!!");
	    	document.forms[0].payduedt.value ="";
	    	return false;
	  	}
	}else{
		alert("First enter Dr/Cr Date !!!");
    	document.forms[0].payduedt.value ="";
    	return false;
	}
}
function tcsCalc(){
	
	var tcs = document.forms[0].tax_tcs.value;
	var dr_cr_net_amt = document.forms[0].dr_cr_net_amt.value;

	if(document.getElementById('chk').checked == true){
		
		if(parseFloat(tcs) > 0 && dr_cr_net_amt!=''){
			document.forms[0].dr_cr_net_amt.value = (parseFloat(tcs) + parseFloat(dr_cr_net_amt)).toFixed(2);
		}
		document.forms[0].chkTcs.value ='Y';
	}else{
		
		if(parseFloat(tcs) > 0 && dr_cr_net_amt!=''){
			document.forms[0].dr_cr_net_amt.value = (parseFloat(dr_cr_net_amt) - parseFloat(tcs)).toFixed(2);
		}	
		document.forms[0].chkTcs.value ='N';
	}
}

function setDrCrQty(){
	
	var qty = document.forms[0].qty.value;  
	var exg_rt = document.forms[0].exg_rt.value;
	var dr_cr_qty = document.forms[0].dr_cr_qty.value;
    var dr_cr_gross_amt1 = parseFloat("0");	
    
	var sale_rate =document.forms[0].sale_rate.value;
	var criteria = document.forms[0].criteria.value;
	var cr_dr = document.forms[0].cr_dr.value;
	var customer_cd = document.forms[0].customer_cd.value;	
	var st_dt = document.forms[0].st_dt.value;
	var end_dt = document.forms[0].end_dt.value;
	var fin_yr = document.forms[0].fin_yr.value;
	var con_type = document.forms[0].con_type.value;
	var hlpl_no = document.forms[0].hlpl_no.value;
	var plant_no = document.forms[0].plant_no.value;
	var fgsa_no = document.forms[0].fgsa_no.value;
	var sn_no = document.forms[0].sn_no.value;
	var fgsa_rev_no = document.forms[0].fgsa_rev_no.value;
	var sn_rev_no = document.forms[0].sn_rev_no.value;
	var tax_struc_cd = document.forms[0].tax_struc_cd.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var form_id = document.forms[0].form_id.value;
	var form_nm = document.forms[0].form_nm.value;	
	var inv_dt =  document.forms[0].inv_dt.value;
	var inv_no =  document.forms[0].inv_no.value;	
	var qty =  document.forms[0].qty.value;	
	var gross_amt =  document.forms[0].gross_amt.value;
	var net_amt =  document.forms[0].net_amt.value;
	var tax_amt_inr1=document.forms[0].tax_amt_inr.value;
	var gross_amt_usd1=document.forms[0].gross_amt_usd.value;
	var dr_cr_dt =  document.forms[0].dr_cr_dt.value;
	var date_flag = document.forms[0].date_flag.value;
	var cust_name = document.forms[0].cust_name.value;
	var bill_dt = document.forms[0].bill_dt.value;
	var from_dt = document.forms[0].from_dt.value;
	var to_dt = document.forms[0].to_dt.value;
	var dr_cr_seq_no = document.forms[0].dr_cr_seq_no.value;
	var dr_cr_ref_inv_no = document.forms[0].dr_cr_ref_inv_no.value;
	
	var diff_rate = "0";
	var diff_exg = "0";
	var dr_cr_exg =  "0";
	var diff_qty = "0";
	var dr_cr_rate = "0";
	var act_rate = "0";
	var dr_cr_sale_rate = 0;
	var temp_gross_amt_1 = 0 ;
	var temp_gross_amt_2 = 0 ;
// 	alert(criteria)
	if(criteria.indexOf('DIFF-PRICE')!=-1){
		  
		  dr_cr_sale_rate = document.forms[0].nw_sale_rate.value;
		  diff_rate = document.forms[0].diff_rate.value;
		  
		  if(diff_rate == ''){
			  diff_rate = "0";
		  }
		  if(dr_cr_sale_rate == ''){
			  dr_cr_sale_rate = "0" ;
		  }
		  act_rate = diff_rate;
		  
	}else{
// 		act_rate = sale_rate;
	}
		
 	if(criteria.indexOf('DIFF-EXG')!=-1){
		  
 		diff_exg = document.forms[0].diff_exg.value;
		dr_cr_exg = document.forms[0].dr_cr_exg_rt.value;	
		  
	  	if(diff_exg == ''){
			diff_exg = "0";
	  	}
	  	if(dr_cr_exg == ''){
	  		dr_cr_exg = "0";
	  	}
	}else{
	}
	  
		if(cr_dr=='dr'){
			if(parseFloat(dr_cr_qty)>parseFloat(qty)){
				
				diff_qty = parseFloat(dr_cr_qty) - parseFloat(qty);
// 				dr_cr_gross_amt1 = (parseFloat(dr_cr_qty) - parseFloat(qty)) * parseFloat(""+exg_rt) * parseFloat(""+act_rate);
				temp_gross_amt_1 = parseFloat(diff_qty); //* parseFloat(dr_cr_sale_rate) * parseFloat(dr_cr_exg);
				temp_gross_amt_2 = parseFloat(qty); //* parseFloat(diff_rate) * parseFloat(diff_exg);
// 				dr_cr_gross_amt1 = (parseFloat(temp_gross_amt_1) + parseFloat(temp_gross_amt_2)).toFixed(2);

			if(criteria.indexOf('DIFF-PRICE')!=-1 || criteria.indexOf('DIFF-EXG')!=-1){
				if(criteria.indexOf('DIFF-PRICE')!=-1){
					temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(dr_cr_sale_rate);
					temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(diff_rate);
				}else{
					temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(sale_rate);
					temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(sale_rate);
				}
				if(criteria.indexOf('DIFF-EXG')!=-1){
					temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(dr_cr_exg);
					temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(diff_exg);
				}else{
					temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(exg_rt);
					temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(exg_rt);
				}
				dr_cr_gross_amt1 = (parseFloat(temp_gross_amt_1) + parseFloat(temp_gross_amt_2)).toFixed(2);
			}else{
				dr_cr_gross_amt1 = parseFloat(diff_qty) * parseFloat(exg_rt) * parseFloat(sale_rate);
			}
		}else{
			dr_cr_qty='';
			diff_qty = '';
			alert("Debit Note Quantity Should be greater than Invoice Quantity !!!");
		}
			
		}else if(cr_dr=='cr'){
			if(parseFloat(dr_cr_qty)<parseFloat(qty)){
				
				diff_qty = parseFloat(qty) - parseFloat(dr_cr_qty);
				temp_gross_amt_1 = parseFloat(diff_qty); //* parseFloat(dr_cr_sale_rate) * parseFloat(dr_cr_exg);
				temp_gross_amt_2 = parseFloat(qty); //* parseFloat(diff_rate) * parseFloat(diff_exg);
// 				dr_cr_gross_amt1 = (parseFloat(temp_gross_amt_1) + parseFloat(temp_gross_amt_2)).toFixed(2);

				if(criteria.indexOf('DIFF-PRICE')!=-1 || criteria.indexOf('DIFF-EXG')!=-1){
					if(criteria.indexOf('DIFF-PRICE')!=-1){
						temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(dr_cr_sale_rate);
						temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(diff_rate);
					}else{
						temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(sale_rate);
						temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(sale_rate);
					}
					if(criteria.indexOf('DIFF-EXG')!=-1){
						temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(dr_cr_exg);
						temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(diff_exg);
					}else{
						temp_gross_amt_1 = parseFloat(temp_gross_amt_1) * parseFloat(exg_rt);
						temp_gross_amt_2 = parseFloat(temp_gross_amt_2) * parseFloat(exg_rt);
					}
					dr_cr_gross_amt1 = (parseFloat(temp_gross_amt_1) + parseFloat(temp_gross_amt_2)).toFixed(2);
				}else{
					dr_cr_gross_amt1 = parseFloat(diff_qty) * parseFloat(exg_rt) * parseFloat(sale_rate);
				}
				
			}else{
				dr_cr_qty='';
				diff_qty = '';
				alert("Credit Note Quantity Should be Less than Invoice Quantity !!!");
			}
		}
		var url ="frm_dr_cr_note.jsp?exg_rt="+exg_rt+"&sale_rate="+sale_rate+"&inv_dt="+inv_dt+"&inv_no="+inv_no+
				"&qty="+qty+"&gross_amt="+gross_amt+"&net_amt="+net_amt+"&st_dt="+st_dt+"&end_dt="+end_dt+
				"&fin_yr="+fin_yr+"&bscode="+customer_cd+"&cr_dr="+cr_dr+"&criteria="+criteria+"&con_type="+con_type+
				"&hlpl_no="+hlpl_no+"&plant_no="+plant_no+"&fgsa_no="+fgsa_no+"&sn_no="+sn_no+
				"&fgsa_rev_no="+fgsa_rev_no+"&sn_rev_no="+sn_rev_no+"&tax_struc_cd="+tax_struc_cd+
				"&form_nm="+form_nm+"&form_id="+form_id+"&write_permission="+write_permission+
				"&delete_permission="+delete_permission+"&print_permission="+print_permission+
				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+
				"&dr_cr_gross_amt2="+dr_cr_gross_amt1+"&tax_amt_inr="+tax_amt_inr1+"&gross_amt_usd="+gross_amt_usd1+
				"&dr_cr_dt="+dr_cr_dt+"&cust_name="+cust_name+"&bill_dt="+bill_dt+
				"&from_dt="+from_dt+"&to_dt="+to_dt+"&dr_cr_qty="+dr_cr_qty+"&diff_qty="+diff_qty+
				"&dr_cr_sale_rate="+dr_cr_sale_rate+"&diff_rate="+diff_rate+"&dr_cr_exg_rt="+dr_cr_exg+"&diff_exg="+diff_exg+"&dr_cr_seq_no="+dr_cr_seq_no+"&dr_cr_ref_inv_no="+dr_cr_ref_inv_no;
// 		alert(url)
	    location.replace(url);
}
</script>

</head>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.sales_invoice.DataBean_dr_cr_note" id="drcr" scope="page"/>
<%

utilBean.init();
String sysdate = utilBean.getGen_dt();

String msg = request.getParameter("msg")== null?"":request.getParameter("msg");	
String erro_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
String write_permission = (String)session.getAttribute("write_permission") ;
String delete_permission = (String)session.getAttribute("delete_permission") ;
String print_permission = (String)session.getAttribute("print_permission") ;
String approve_permission = (String)session.getAttribute("approve_permission") ;
String audit_permission = (String)session.getAttribute("audit_permission") ;
String user_cd=(String)session.getAttribute("user_cd"); 

String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200227
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200227
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200227

String year = request.getParameter("year")==null?"":request.getParameter("year");
String bscode = request.getParameter("bscode")==null?"0":request.getParameter("bscode");
String cr_dr = request.getParameter("cr_dr")==null?"":request.getParameter("cr_dr");
String criteria = request.getParameter("criteria")==null?"":request.getParameter("criteria");	
String from_dt = request.getParameter("from_dt")==null?sysdate:request.getParameter("from_dt");
// System.out.println("from_dt---"+from_dt);
String to_dt = request.getParameter("to_dt")==null?sysdate:request.getParameter("to_dt");		
String st_dt     =request.getParameter("st_dt")==null?"":request.getParameter("st_dt");
String end_dt    =request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
String qty       =request.getParameter("qty")==null?"":request.getParameter("qty");
String net_amt   =request.getParameter("net_amt")==null?"":request.getParameter("net_amt");
String gross_amt =request.getParameter("gross_amt")==null?"":request.getParameter("gross_amt");
String sale_rate =request.getParameter("sale_rate")==null?"":request.getParameter("sale_rate");
String inv_dt    =request.getParameter("inv_dt")==null?"":request.getParameter("inv_dt");
String tax_amt_inr =request.getParameter("tax_amt_inr")==null?"":request.getParameter("tax_amt_inr"); //SB20160401
String fin_yr    =request.getParameter("fin_yr")==null?"":request.getParameter("fin_yr");
String con_type  =request.getParameter("con_type")==null?"":request.getParameter("con_type");
String hlpl_no   =request.getParameter("hlpl_no")==null?"":request.getParameter("hlpl_no");
String plant_no  =request.getParameter("plant_no")==null?"":request.getParameter("plant_no");
String inv_no    =request.getParameter("inv_no")==null?"":request.getParameter("inv_no");
String due_dt    =request.getParameter("due_dt")==null?"":request.getParameter("due_dt");
//System.out.println("due_dt---"+due_dt);
String exg_rt    =request.getParameter("exg_rt")==null?"":request.getParameter("exg_rt");
String sn_no     =request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
String sn_rev_no =request.getParameter("sn_rev_no")==null?"":request.getParameter("sn_rev_no");
String fgsa_no   =request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
String fgsa_rev_no=request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
String gross_amt_usd=request.getParameter("gross_amt_usd")==null?"":request.getParameter("gross_amt_usd");
String tax_struc_cd=request.getParameter("tax_struc_cd")==null?"":request.getParameter("tax_struc_cd");
String dr_cr_fin_year= request.getParameter("dr_cr_fin_year")==null?"":request.getParameter("dr_cr_fin_year");
String dr_cr_gross_amt_val = request.getParameter("dr_cr_gross_amt2")==null?"":request.getParameter("dr_cr_gross_amt2");
String dr_cr_dt = request.getParameter("dr_cr_dt")==null?"":request.getParameter("dr_cr_dt");	
String diff_qty = request.getParameter("diff_qty")==null?"":request.getParameter("diff_qty");
String dr_cr_gross_usd = request.getParameter("dr_cr_gross_usd")==null?"":request.getParameter("dr_cr_gross_usd");
String dr_cr_exg_rt = request.getParameter("dr_cr_exg_rt")==null?"":request.getParameter("dr_cr_exg_rt");
/*String day_diff = request.getParameter("day_diff")==null?"":request.getParameter("day_diff");
String int_type = request.getParameter("int_type")==null?"":request.getParameter("int_type");
String int_rate = request.getParameter("int_rate")==null?"":request.getParameter("int_rate");
String int_sign =request.getParameter("int_sign")==null?"":request.getParameter("int_sign");
String int_amt = request.getParameter("int_amt")==null?"":request.getParameter("int_amt");
String int_cd = request.getParameter("int_cd")==null?"":request.getParameter("int_cd");
String int_rate_cal = request.getParameter("int_rate_cal")==null?"":request.getParameter("int_rate_cal");*/
String pan_no = request.getParameter("pan_no")==null?"":request.getParameter("pan_no");
String gst_tin_no = request.getParameter("gst_tin_no")==null?"":request.getParameter("gst_tin_no");
String cst_tin_no = request.getParameter("cst_tin_no")==null?"":request.getParameter("cst_tin_no");
String mapping_id=request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");
//String int_rate1 = ""; //request.getParameter("int_rate1")==null?"":request.getParameter("int_rate1");
String form_id = request.getParameter("form_id")==null?"":request.getParameter("form_id");
String form_nm = request.getParameter("form_nm")==null?"":request.getParameter("form_nm");
String remark_1 = request.getParameter("remark_1")==null?"":request.getParameter("remark_1");
String remark_2 = request.getParameter("remark_2")==null?"":request.getParameter("remark_2");
String remark_3 = request.getParameter("remark_3")==null?"":request.getParameter("remark_3");
String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
String dr_cr_sale_rate = request.getParameter("dr_cr_sale_rate")==null?"":request.getParameter("dr_cr_sale_rate");
String dr_cr_no= request.getParameter("dr_cr_no")==null?"":request.getParameter("dr_cr_no");
String dr_cr_doc_no= request.getParameter("dr_cr_doc_no")==null?"":request.getParameter("dr_cr_doc_no");
String address1= request.getParameter("address1")==null?"":request.getParameter("address1");
String city1= request.getParameter("city1")==null?"":request.getParameter("city1");
String pin1= request.getParameter("pin1")==null?"":request.getParameter("pin1");
String cust_name= request.getParameter("cust_name")==null?"":request.getParameter("cust_name");
// System.out.println("cust_name******"+cust_name);
String chkval= request.getParameter("chkval")==null?"":request.getParameter("chkval");
String flag= request.getParameter("flag")==null?"N":request.getParameter("flag");
String view_flag = request.getParameter("view_flag")==null?"N":request.getParameter("view_flag"); //from invoice
String new_inv_seq_no = request.getParameter("new_inv_seq_no")==null?"":request.getParameter("new_inv_seq_no");
String hlpl_inv_seq_no = request.getParameter("hlpl_inv_seq_no")==null?"":request.getParameter("hlpl_inv_seq_no");
String customer_abbr = request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
String customer_plant_nm = request.getParameter("customer_plant_nm")==null?"":request.getParameter("customer_plant_nm");
String bill_dt = request.getParameter("bill_dt")==null?"":request.getParameter("bill_dt");
String paydue = request.getParameter("paydue")==null?"":request.getParameter("paydue");
String diff_exg = request.getParameter("diff_exg")==null?"":request.getParameter("diff_exg");
String diff_rate = request.getParameter("diff_rate")==null?"":request.getParameter("diff_rate");
String dr_cr_qty = request.getParameter("dr_cr_qty")==null?"":request.getParameter("dr_cr_qty");
String split_qty=request.getParameter("split_qty")==null?"":request.getParameter("split_qty");
String tax_str=request.getParameter("tax_str")==null?"":request.getParameter("tax_str");
String taxnm=request.getParameter("taxnm")==null?"":request.getParameter("taxnm");
String rate_service= request.getParameter("rate_service")==null?"":request.getParameter("rate_service");
String calc_base=request.getParameter("calc_base")==null?"":request.getParameter("calc_base");
String dr_cr_seq_no = request.getParameter("dr_cr_seq_no")==null?"":request.getParameter("dr_cr_seq_no");
String dr_cr_ref_inv_no = request.getParameter("dr_cr_ref_inv_no")==null?"":request.getParameter("dr_cr_ref_inv_no");

Vector tax_strrate=new Vector();

drcr.setCallFlag("Inv_dates");
drcr.setSt_dt(st_dt);
drcr.setEnd_dt(end_dt);
drcr.setCriteria(criteria);  //
drcr.setPlant_no(plant_no);
drcr.setFin_year(fin_yr);
drcr.setHlplInvoiceNo(hlpl_no);
drcr.setFgsa_no(fgsa_no);
drcr.setSn_no(sn_no);
drcr.setContract_type(con_type);
drcr.setYear(year);
drcr.setFrom_dt(from_dt);
drcr.setTo_dt(to_dt);
drcr.setCustomer_cd(bscode);
drcr.setDr_cr_fin_year(dr_cr_fin_year);
drcr.setTax_struc_cd(tax_struc_cd); 
drcr.setCr_dr1(cr_dr);
drcr.setDrcrgrossamt(dr_cr_gross_amt_val);
drcr.setInv_dt(inv_dt);
drcr.setView_flag(view_flag);
drcr.setCust_name(cust_name);
drcr.setBill_dt(bill_dt);
drcr.setMdr_cr_dt(dr_cr_dt);
drcr.setDiff_exg(diff_exg);
drcr.setExg_rt(exg_rt);
drcr.setDr_cr_DUEDT(paydue);
drcr.setDiff_rate(diff_rate);
drcr.setSale_rate(sale_rate);
drcr.setDr_cr_qty(dr_cr_qty);
drcr.setDiff_qty(diff_qty);
drcr.setTax_str(tax_str);
drcr.setTaxnm(taxnm);
drcr.setInv_no(inv_no);
drcr.setFgsa_rev_no(fgsa_rev_no);
drcr.setSn_rev_no(sn_rev_no);
drcr.setCalc_base(calc_base);
drcr.setDr_cr_seq_no(dr_cr_seq_no);
drcr.setSDr_cr_ref_inv_no(dr_cr_ref_inv_no);


drcr.init();

cust_name = drcr.getCust_name();
bill_dt = drcr.getBill_dt();
Vector inv_period_date=drcr.getInvoice_period_date1();
String dr_cr_net_amt_val = drcr.getDr_cr_net_amt();
// System.out.println("dr_cr_net_amt_val*****"+dr_cr_net_amt_val);
String cust_nm=drcr.getCust_nm();
dr_cr_doc_no=drcr.getDr_cr_doc_no();
if(dr_cr_gross_amt_val.trim().equals(""))
{
	dr_cr_gross_amt_val = drcr.getDr_cr_gross_amt();		
}	
java.text.NumberFormat nf = new java.text.DecimalFormat("0.00");
java.text.NumberFormat nf2 = new java.text.DecimalFormat("##0.0000");
if(!dr_cr_gross_amt_val.equals("")) {
	dr_cr_gross_amt_val = nf.format(Double.parseDouble(""+java.text.NumberFormat.getInstance().parse(dr_cr_gross_amt_val)));
}
if(dr_cr_dt.trim().equals(""))
{
	dr_cr_dt = drcr.getDr_cr_dt();
}
if(dr_cr_exg_rt.trim().equals(""))
{
	dr_cr_exg_rt = drcr.getDr_cr_exg_rt();
}
 if(remark.trim().equals(""))
{
	remark = drcr.getRemark();		
}
 if(dr_cr_sale_rate.equals("")) {
	 dr_cr_sale_rate = drcr.getDr_cr_sales_rate();
 }
 boolean date_flag = drcr.isDate_flag();
 if(view_flag.equals("Y")) {
	 sale_rate = drcr.getSale_rate();
	 qty = drcr.getTot_qty();
	 net_amt = drcr.getNet_amt_inr();
	 tax_amt_inr = drcr.getTax_amt_inr();
	 exg_rt = drcr.getExch_rate();
	 gross_amt = drcr.getGross_amt_inr();
	 inv_dt = drcr.getInvoice_dt();
	 inv_no = new_inv_seq_no;
	 if(inv_no.equals("")) {
		 inv_no = hlpl_inv_seq_no;
	 }
	 dr_cr_gross_amt_val = drcr.getDr_cr_gross_amt();
 }
 
//  System.out.println("view_flag****"+view_flag);
 String tcs_app_flag=drcr.getTcs_app_flag();
//  System.out.println("tcs_app_flag****"+tcs_app_flag);
 String tcs_amt=drcr.getTcs_amt();
 String tcs_nm=drcr.getTcs_nm();
 String fact=drcr.getFact();
 String dr_cr_tax_amt_inr = drcr.getTax_amt_inr();
 diff_exg = drcr.getDiff_exg();
 paydue = drcr.getDr_cr_DUEDT();
 diff_rate = drcr.getDiff_rate();
 int dr_cr_aprv = drcr.getDr_cr_aprv();
 
 if(!diff_exg.equals("")){
	 diff_exg = nf2.format(Double.parseDouble(diff_exg+""));
 }

 if(!diff_rate.equals("")){
	 diff_rate = nf2.format(Double.parseDouble(diff_rate+""));
 }
 String tax_fact = drcr.getTax_str_nm();//Hiren_20210127
 String dr_cr_tcs_flag = drcr.getDr_cr_tcs_flag();
 dr_cr_qty = drcr.getDr_cr_qty();
 diff_qty = drcr.getDiff_qty();
Vector customer_inv_tax_nm = drcr.getCustomer_Invoice_Tax_Name();
Vector customer_inv_tax_cd = drcr.getCustomer_Invoice_Tax_Code();
Vector customer_inv_tax_rate = drcr.getCustomer_Invoice_Tax_Rate();
String tax_str_nm = drcr.getTax_str_nm();
Vector Mtaxrt=drcr.getMtaxrt();
String rmk_desc=drcr.getRmk_desc();	
String dr_cr_tax_rt = drcr.getDr_cr_tax_rt();
String tds_app_flag = drcr.getTds_app_flag();
String tds_amt = drcr.getTds_amt();
fgsa_rev_no = drcr.getFgsa_rev_no();
sn_rev_no = drcr.getSn_rev_no();
double serv_total_tax_perc = drcr.getServ_total_tax_perc();
Vector View_amount  = drcr.getView_amount();
Vector View_invoice_qty = drcr.getView_invoice_qty();
Vector View_km = drcr.getView_km();
Vector View_rate = drcr.getView_rate();
Vector View_service_inv_dt = drcr.getView_service_inv_dt();
Vector View_truck_inv_dt = drcr.getView_truck_inv_dt();
Vector View_truck_inv_no = drcr.getView_truck_inv_no();
Vector View_truck_nm = drcr.getView_truck_nm();
Vector view_truck_inv_dr_cr_qty = drcr.getView_truck_inv_dr_cr_qty();
Vector view_truck_inv_dr_cr_diff_qty = drcr.getView_truck_inv_dr_cr_diff_qty();
double total_diff_qty = drcr.getTotal_diff_qty();
double total_dr_cr_qty = drcr.getTotal_dr_cr_qty();
String drcrStr = "";
if(cr_dr.equalsIgnoreCase("dr")){
	drcrStr = "Debit Note";  
}else if(cr_dr.equalsIgnoreCase("cr")){
	drcrStr = "Credit Note"; 
} 
String diff_inr_km = drcr.getDiff_inr_km();
String diff_inr_mmbtu = drcr.getDiff_inr_mmbtu();
String dr_cr_inr_km =  drcr.getDr_cr_inr_km();
String dr_cr_inr_mmbtu =  drcr.getDr_cr_inr_mmbtu();
Vector serv_dr_cr_qty = drcr.getServ_dr_cr_qty();
Vector serv_diff_qty = drcr.getServ_diff_qty();
Vector serv_tax_desc = drcr.getServ_tax_desc();
Vector serv_tax_perc = drcr.getServ_tax_perc();
String dr_cr_tax_rate = drcr.getDr_cr_tax_rate();
// System.out.println("serv_tax_perc***"+serv_tax_perc);
String diff_tax_str = drcr.getDiff_tax_str();
String tax_td = drcr.getTax_td();
Vector view_truck_inv_cnt = drcr.getView_truck_inv_cnt();

String qty_unit = "",inv_qty_unit = "";

%>
<body 
<%if(msg.length() > 5 || erro_msg.length() > 5){ %>
	onload="callParant('<%=dr_cr_fin_year %>','<%=bill_dt %>','<%=msg %>','<%=from_dt %>','<%=to_dt %>','<%=bscode%>','<%=erro_msg %>');" 
<%}else if(criteria.equalsIgnoreCase("DIFF-QTY--") && con_type.equalsIgnoreCase("V") ){ %>
	onload="calcAmt('<%=total_diff_qty %>','<%=rate_service %>',<%= serv_total_tax_perc%>);"
	<%} %> >
	<div class="tab-content">
		<div class="tab-pane active" >
			<div class="box mb-0">
				<form method="post" action="../servlet/Frm_dr_cr_note" >
					<input type="hidden" name="dr_cr_seq_no" id="dr_cr_seq_no" value="<%=dr_cr_seq_no%>">
					<input type="hidden" name="dr_cr_ref_inv_no" id="dr_cr_ref_inv_no" value="<%=dr_cr_ref_inv_no%>">
					<input type="hidden" name="emp_cd" value="<%=user_cd%>">
					<input type="hidden" name="sysdate" value="<%=sysdate%>">
					<input type="hidden" name="modCd" value="<%=modCd%>">
					<input type="hidden" name="formId" value="<%=formId%>">
					<input type="hidden" name="subModUrl" value="<%=subModUrl%>">	
					<input type="hidden" name="user_cd" value="<%=user_cd%>">
					<input type="hidden" name="sysdate" value="<%=sysdate %>" >
					<input type="hidden" name="date_flag" value="<%=date_flag%>">

					<input type="hidden" name="chkval" value="<%=chkval%>">
					<input type="hidden" name="cust_name" value="<%=cust_name%>">
					<input type="hidden" name="bill_dt" value="<%=bill_dt%>">
					<input type="hidden" name="from_dt" value="<%=from_dt%>">
					<input type="hidden" name="to_dt" value="<%=to_dt%>">
					<input type="hidden" name="bscode" value="<%=bscode%>">
					<input type="hidden" name="tds_amt" value="<%=tds_amt%>">
					<input type="hidden" name="tds_app_flag" value="<%=tds_app_flag%>">
					<input type="hidden" name="serv_total_tax_perc" id="serv_total_tax_perc" value="<%=serv_total_tax_perc%>">
					<input type="hidden" name="calc_base" id="calc_base" value="<%=calc_base%>">
					<input type="hidden" name="tax_td" id="tax_td" value="<%=tax_td%>">
					<input type="hidden" name="hid_dr_cr_tax" id="hid_dr_cr_tax" value="<%=serv_total_tax_perc%>">
					<input type="hidden" name="hid_tot_qty" id="hid_tot_qty" value="<%=total_dr_cr_qty%>">
					<% if(!msg.equals("")) {%>
					<div class="box-body table-responsive no-padding">
						<table class="table  table-bordered">
							<thead>   
								<tr>
									<th class="text-center" colspan="2" style="color: blue;"> <%=msg %></th>
								</tr>
							</thead>
						</table>
					</div>
				<%} %>
				
				<% if(!erro_msg.equals("")){ %>
					<div class="box-body table-responsive no-padding">
						<table class="table  table-bordered">
							<thead>   
								<tr>
									<th class="text-center" colspan="2" style="color: red;"> <%=erro_msg %></th>
								</tr>
							</thead>
						</table>
					</div>
				<%} %>
				
					<div class="box-body table-responsive no-padding">
						<table class="table  table-bordered">
							<thead>   
								<tr><td colspan="12" class="text-center"><b>
									<%if(view_flag.equals("Y")) { %>
										<%=customer_abbr%> : <%=customer_plant_nm%>
									<%}else{ %>
										<%=cust_name%>
									<%} %>
									</b></td>
								</tr>
								</thead>
								<tbody>
								<%if(criteria.contains("REV_INV")){ %>
								
								<tr class="text-center" style="font-size: 12px;" >
									<td colspan="2" class="info"><input type="checkbox" name="chng_pin"  onclick="change_pin();"> Change in PAN/TIN No./Address</td>
								    <td colspan="2" class="info"><input type="checkbox" name="chng_tax_struc" onclick="chng_tax();"> Change in Tax Structure</td>
  								    <td colspan="2" class="info"><input type="checkbox" name="chng_inv_dt" onclick="change_inv_dt();"> Invoice Date</td>
 								    <td colspan="2" class="info"><input type="checkbox" name="chng_desc_items" onclick="change_line_item();"> Description of line items</td>
								    <td colspan="2" class="info"><input type="checkbox" name="chng_err" onclick=""> Data Error</td>
								  	<td colspan="2" class="info"><input type="checkbox" name="chng_pay_dt"  onclick="chnge_dt();"> Payment Due Date</td>
							   </tr>
								<%}else{ %>
								  <tr>
								  	<td colspan="12">
								  		<div align="center"><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
									  		<%
									  		if(criteria.contains("DIFF-EXG")){ %>
									  				<u>Difference in Exchange Rate </u>&nbsp;&nbsp;&nbsp; 
									  		<%}if(criteria.contains("DIFF-PRICE")){%>
									  			<u>Change in Price/tariff</u> &nbsp;&nbsp;&nbsp;
									  		<%}if(criteria.contains("DIFF-QTY")){
									  			if(calc_base.equals("1")){
									  			
									  				qty_unit = "INR/MMBtu";
									  				inv_qty_unit = "MMBtu";
									  				
									  			}else if(calc_base.equals("2")){
									  				
									  				qty_unit = "INR/KM";
									  				inv_qty_unit = "KM";
									  				
									  			}else if(calc_base.equals("3")){
									  				qty_unit = "INR/TRUCK";
									  				inv_qty_unit = "";
									  			}
									  		%>
									  		 	<u>Change in Quantity</u> &nbsp;&nbsp;&nbsp;
									  		 <%}if(criteria.contains("DIFF-TAX")){%>
									  		 	<u>Difference in Tax % (Split Qty.)</u>&nbsp;&nbsp;&nbsp; 
									  		 <%}if(criteria.contains("DIFF-INRMMBTU")){
									  			qty_unit = "MMBTU";
									  			
									  			%>
												<u>Change in INR/MMBTU</u>&nbsp;&nbsp;&nbsp;
											 <%}if(criteria.contains("DIFF-INRKM")){
												 qty_unit = "KM"; %>
												<u>Change in INR/KM</u>
											 <%}if(criteria.contains("DIFF-LUMPSUM")){
												 qty_unit = "LUMPSUM";%>
												<u>Change in INR/LUMPSUM</u>
											  <%}if(criteria.contains("DIFF-KM")){
												 qty_unit = "KM";%>
												&nbsp;&nbsp;&nbsp;<u>Change in KM</u>
											  <%}%>
									  		</b></font>
								  		</div>
								  	</td>
								  </tr>		  
								  <%} %>
								<tr class="text-left" style="font-size: 12px;"  >		    		    
								    <td colspan="2" class="info">Invoice No.</td>
								    <td colspan="2">
								    	<input type="text" name="inv_no" value="<%=inv_no%>" style="text-align:right" size="16" readonly class="mkrdly">
								    </td> 
								    <td colspan="2" class="info">Invoice Date</td>
								    <td colspan="2">
								    	<input type="text" autocomplete="off" name="inv_dt" value="<%=inv_dt%>" style="text-align:right" size="10" readonly class="mkrdly">
								    </td>  
								    <td colspan="2">
								    	<input type="text" autocomplete="off" name="chng_inv_dt1" class="datepick" id="chng" value="" style="display: none;" size="10" onblur="change_gstno('<%=inv_dt%>',this.value,'Inv dt');" >
								    </td>
								</tr>
			  
			  					<tr class="text-left" style="font-size: 12px;"  >		    		    
								    <td colspan="2" class="info"><%if(cr_dr.equalsIgnoreCase("dr")){ if(con_type.equalsIgnoreCase("C")){%> Debit&nbsp; <%}else{ %> Debit&nbsp; <%}}else if(cr_dr.equalsIgnoreCase("cr")){%> Credit&nbsp; <%}else if(cr_dr.equalsIgnoreCase("sp")){%>Debit&nbsp;<%} %>Note No.</td>
								    <td colspan="2">
								    	<input type="text" name="dr_cr_no_disp" value="<%=dr_cr_doc_no%>" style="text-align:right" size="16" readOnly class="mkrdly"><input type="hidden" name="dr_cr_no" value="<%//=dr_cr_no%>"><input type="hidden" name="dr_cr_doc_no" value="<%//=dr_cr_doc_no%>" maxlength="20" style="text-align:left" size="8">
								    </td> 
								    <td colspan="2" class="info"><%if(cr_dr.equalsIgnoreCase("dr")){ if(con_type.equalsIgnoreCase("C")){%> Debit&nbsp; <%}else{ %> Debit&nbsp; <%}}else if(cr_dr.equalsIgnoreCase("cr")){%> Credit&nbsp; <%}else if(cr_dr.equalsIgnoreCase("sp")){%>Debit&nbsp;<%} %>Note Date&nbsp;<font color="red"><b>*</b></font></td>
								    <td colspan="2">
								    	<input type="text" autocomplete="off" name="dr_cr_dt" class="datepick" value="<%=dr_cr_dt%>" style="text-align:right;background:yellow" size="10" onchange="chk_dt();" >
								    </td>  
								</tr>
								 <%if(con_type.equalsIgnoreCase("V")) { %>
								 
								 <tr class="text-left" style="font-size: 12px;"  >		    		    
								    <td colspan="2" class="info">Invoice Quantity</td>
								    <td colspan="2">
								    	<input type="text" name="qty" id="qty" value="<%=qty%>" style="text-align:right" size="8" readonly="readonly" class="mkrdly" onblur="">&nbsp;<b><%=qty_unit %></b>
								    </td> 
								</tr>
									    
								<%if(calc_base.equals("1")){ %>	 
									 	 <tr class="text-left" style="font-size: 12px;">   
										    <td colspan="2" class="info">Invoice INR/MMBTU</td>
										    <td colspan="2">
										    	<input type="text" name="inr_mmbtu" id="inr_mmbtu" value="<%=rate_service %>" style="text-align:right" size="8" readonly class="mkrdly">&nbsp;<b>INR/MMBTU</b>
										    </td> 
								<%} %>		    
								<%if(criteria.contains("DIFF-INRMMBTU")){ %>    
									     <td colspan="2" class="info"><%if(cr_dr.equalsIgnoreCase("dr")){ if(con_type.equalsIgnoreCase("C")){%> Debit&nbsp; <%}else{ %> Debit&nbsp; <%}}else if(cr_dr.equalsIgnoreCase("cr")){%> Credit&nbsp; <%}else if(cr_dr.equalsIgnoreCase("sp")){%>Debit&nbsp;<%} %> Note INR/MMBTU</td>
									     <td colspan="2">
									    	<input type="number" onkeyup="if(this.value<0){this.value= this.value * -1}"  name="dr_cr_inr_mmbtu" id="dr_cr_inr_mmbtu" value="<%= dr_cr_inr_mmbtu%>"  style="background:yellow;text-align: right;width: 15%;" class="mkrdly" onchange="calcDiff(this);">&nbsp;INR/MMBTU
									    	&nbsp;&nbsp;<b >Diff. INR/MMBTU : <input type="text" name="diff_inr_mmbtu" id="diff_inr_mmbtu" value="<%=diff_inr_mmbtu %>" readonly="readonly" style="text-align:right;" size="8"></b>&nbsp;INR/MMBTU
									     </td>
							    	</tr>
								 <%} %>
								<%if(calc_base.equals("2")){ %>	 	 
									 	 <tr class="text-left" style="font-size: 12px;">   
										    <td colspan="2" class="info">Invoice INR/KM</td>
										    <td colspan="2">
										    	<input type="text" name="inr_km" id="inr_km" value="<%=rate_service %>" style="text-align:right" size="8" readonly class="mkrdly">&nbsp;<b>INR/KM</b>
										    </td> 
								<%} %>		    
								 <%if(criteria.contains("DIFF-INRKM")){ %>	    
									     <td colspan="2" class="info"><%if(cr_dr.equalsIgnoreCase("dr")){ if(con_type.equalsIgnoreCase("C")){%> Debit&nbsp; <%}else{ %> Debit&nbsp; <%}}else if(cr_dr.equalsIgnoreCase("cr")){%> Credit&nbsp; <%}else if(cr_dr.equalsIgnoreCase("sp")){%>Debit&nbsp;<%} %> Note INR/KM</td>
									     <td colspan="2">
									    	<input type="number" onkeyup="if(this.value<0){this.value= this.value * -1}"  name="dr_cr_inr_km" id="dr_cr_inr_km" value="<%=dr_cr_inr_km %>"  style="background:yellow;text-align: right;width: 15%;" class="mkrdly" onchange="calcDiff(this);">&nbsp;INR/KM
									    	&nbsp;&nbsp;<b >Diff. INR/KM  <input type="text" name="diff_inr_km" id="diff_inr_km" value="<%=diff_inr_km %>" readonly="readonly" style="text-align:right;" size="8"></b>&nbsp;INR/KM
									     </td>
								    </tr>
								 <%} %>
									 
									 <%if(criteria.contains("DIFF-KM")){ %> 
									 	<tr>
									 	<td colspan="7" align="left">
											<table width="60%"  border="1" align="left" cellpadding="0" cellspacing="0">
												<tr valign="bottom">
													<td colspan="1" ><div align="center"><font size="1.5px" face="Arial"><b>Sr. No.</b></font></div></td>
													<td><div align="center"><font size="1.5px" face="Arial"><b>Supply Date</b></font></div></td>
													<td ><div align="center"><font size="1.5px" face="Arial"><b>Truck Number</b></font></div></td>
													<td colspan="1" ><div align="center"><font size="1.5px" face="Arial"><b>Rate <br>(<%=qty_unit %>)</b></font></div></td>
													<td ><div align="center"><font size="1.5px" face="Arial"><b>Invoice KM</b> </font></div></td>
													<td colspan="1" ><div align="center"><font size="1.5px" face="Arial"><b>
														<%=drcrStr %> KM</b></font></div>
													</td>
													<td colspan="1" ><div align="center"><font size="1.5px" face="Arial"><b>
														Diff. <%=drcrStr %> KM </b></font></div>
													</td>
												</tr>
									 		<%for(int i = 0 ; i < View_truck_inv_no.size() ; i++){ %>
										 		<tr>
													<td width="5%"><div align="center"><font size="1.5px" face="Arial"><span id=""> <%=i+1 %></span> </font></div></td>
													<td width="10%"><div align="center"><font size="1.5px" face="Arial"><span id="invDt<%=i%>"><%=View_truck_inv_dt.elementAt(i) %> </span></font></div>
														<input type="hidden" name = "supply_dt" value="<%=View_truck_inv_dt.elementAt(i) %>">
													</td>
													<td width="10%"><div align="center"><font size="1.5px" face="Arial"><span id="truckNo<%=i%>"></span><%=View_truck_nm.elementAt(i)%></font></div>
														<input type="hidden" name = "truck_no" value="<%=View_truck_nm.elementAt(i)%>">
													</td>
													<td width="10%"><div align="right"><font size="1.5px" face="Arial"><span id="rate<%=i%>" style="text-align: right;"><%=View_rate.elementAt(i) %></span>&nbsp;</font></div></td>
													<td width="10%"><div align="right"><font size="1.5px" face="Arial"><span id="invQty<%=i%>" style="text-align: right;"><%=View_km.elementAt(i)%></span>&nbsp;</font></div></td>
													<td width="10%"><div align="center"><font size="1.5px" face="Arial"><input type="number" onkeyup="if(this.value<0){this.value= this.value * -1}"  name="dr_cr_qty" id= "dr_cr_qty<%=i %>" style="text-align: right;background:yellow;width: 50%"
													 	onchange="calcDrCrQty(this,'<%=View_truck_inv_no.size()%>');" 
													 	<%if(!dr_cr_doc_no.equalsIgnoreCase("")) {%>
														 	value="<%=serv_dr_cr_qty.elementAt(i)%>"
														 <%} %>
														 ></font></div> </td>
													<td width="10%"><div align="center"><font size="1.5px" face="Arial"><input type="number"   name="diff_qty" id= "diff_qty<%=i %>" style="text-align: right;width: 50%" readonly="readonly"
														<%if(!dr_cr_doc_no.equalsIgnoreCase("")) {%>
														 	value="<%=serv_diff_qty.elementAt(i)%>"
														 <%} %>
													 ></font></div> </td>
												</tr>
									 		<%} %>
									 	</table></td></tr>
									 <%} %>
									 
									 <%if(criteria.contains("DIFF-LUMPSUM")){ %> 
									 	<tr>
									 	<td colspan="7" align="left">
											<table width="60%"  border="1" align="left" cellpadding="0" cellspacing="0">
												<tr valign="bottom">
													<td colspan="1" ><div align="center"><font size="1.5px" face="Arial"><b>Sr. No.</b></font></div></td>
													<td><div align="center"><font size="1.5px" face="Arial"><b>Supply Date</b></font></div></td>
													<td ><div align="center"><font size="1.5px" face="Arial"><b>Truck Number</b></font></div></td>
													<td ><div align="center"><font size="1.5px" face="Arial"><b>Truck Quantity <br>(MMBtu) </b></font></div></td>
													<td colspan="1" ><div align="center"><font size="1.5px" face="Arial"><b>Rate <br>(INR/TRUCK)</b></font></div></td>
													<td colspan="1" ><div align="center"><font size="1.5px" face="Arial"><b>
														<%=drcrStr %> Rate <br>(INR/TRUCK)</b></font></div>
													</td>
													<td colspan="1" ><div align="center"><font size="1.5px" face="Arial"><b>
														Diff. <%=drcrStr %> Rate <br>(INR/TRUCK)</b></font></div>
													</td>
												</tr>
									 		<%for(int i = 0 ; i < View_truck_inv_no.size() ; i++){ %>
										 		<tr>
													<td width="5%"><div align="center"><font size="1.5px" face="Arial"><span id=""> <%=i+1 %></span> </font></div></td>
													<td width="10%"><div align="center"><font size="1.5px" face="Arial"><span id="invDt<%=i%>"><%=View_truck_inv_dt.elementAt(i) %> </span></font></div>
														<input type="hidden" name = "supply_dt" value="<%=View_truck_inv_dt.elementAt(i) %>">
													</td>
													<td width="10%"><div align="center"><font size="1.5px" face="Arial"><span id="truckNo<%=i%>"></span><%=View_truck_nm.elementAt(i)%></font></div>
														<input type="hidden" name = "truck_no" value="<%=View_truck_nm.elementAt(i)%>">
													</td>
													<td width="10%"><div align="right"><font size="1.5px" face="Arial"><span id="invQty<%=i%>" style="text-align: right;"><%=View_invoice_qty.elementAt(i)%></span>&nbsp;</font></div></td>
													<td width="10%"><div align="right"><font size="1.5px" face="Arial"><span id="rate<%=i%>" style="text-align: right;"><%=View_amount.elementAt(i) %></span>&nbsp;</font></div></td>
													<td width="10%"><div align="center"><font size="1.5px" face="Arial"><input type="number" onkeyup="if(this.value<0){this.value= this.value * -1}"  name="dr_cr_qty" id= "dr_cr_qty<%=i %>" style="text-align: right;background:yellow;width: 50%" onchange="calcDrCrGrossAmt(this,'<%=View_truck_inv_no.size()%>');" 
														<%if(!dr_cr_doc_no.equalsIgnoreCase("")) {%>
														 	value="<%=serv_dr_cr_qty.elementAt(i)%>"
														 <%} %>
													></font></div> </td>
													<td width="10%"><div align="center"><font size="1.5px" face="Arial"><input type="number"   name="diff_qty" id= "diff_qty<%=i %>" style="text-align: right;width: 50%" readonly="readonly" 
														<%if(!dr_cr_doc_no.equalsIgnoreCase("")) {%>
														 	value="<%=serv_diff_qty.elementAt(i)%>"
														 <%} %>
													></font></div> </td>
												</tr>
									 		<%} %>
									 	</table></td></tr>
									 <%} %>
									  <%if(criteria.contains("DIFF-QTY")){ %> 
									 	<tr>
									 	<td colspan="7" align="left">
											<table width="60%"  border="1" align="left" cellpadding="0" cellspacing="0">
												<tr valign="bottom">
													<td colspan="1" ><div align="center"><font size="1.5px" face="Arial"><b>Sr. No.</b></font></div></td>
													<td><div align="center"><font size="1.5px" face="Arial"><b>Supply Date</b></font></div></td>
													<td ><div align="center"><font size="1.5px" face="Arial"><b>Truck Number</b></font></div></td>
													<td ><div align="center"><font size="1.5px" face="Arial"><b>Invoice Quantity <br>(MMBtu) </b></font></div></td>
													<td ><div align="center"><font size="1.5px" face="Arial"><b><%=drcrStr %> Quantity <br>(MMBtu) </b></font></div></td>
													<td colspan="1" ><div align="center"><font size="1.5px" face="Arial"><b>Rate <br>(INR/MMBtu)</b></font></div></td>
													<td colspan="1" ><div align="center"><font size="1.5px" face="Arial"><b>
														Diff. <%=drcrStr %> Quantity <br>(MMBtu)</b></font></div>
													</td>
												</tr>
									 		<%for(int i = 0 ; i < View_truck_inv_no.size() ; i++){ %>
										 		<tr>
													<td width="5%"><div align="center"><font size="1.5px" face="Arial"><span id=""> <%=i+1 %></span> </font></div></td>
													<td width="10%"><div align="center"><font size="1.5px" face="Arial"><span id="invDt<%=i%>"><%=View_truck_inv_dt.elementAt(i) %> </span></font></div>
														<input type="hidden" name = "supply_dt" value="<%=View_truck_inv_dt.elementAt(i) %>">
													</td>
													<td width="10%"><div align="center"><font size="1.5px" face="Arial"><span id="truckNo<%=i%>"></span><%=View_truck_nm.elementAt(i)%></font></div>
														<input type="hidden" name = "truck_no" value="<%=View_truck_nm.elementAt(i)%>">
													</td>
													<td width="10%"><div align="right"><font size="1.5px" face="Arial"><span id="invQty<%=i%>" style="text-align: right;"><%=View_invoice_qty.elementAt(i)%></span>&nbsp;</font></div></td>
													<td width="10%"><div align="center"><font size="1.5px" face="Arial"><span id="rate<%=i%>" style="text-align: right;">
														<input type="number" onkeyup="if(this.value<0){this.value= this.value * -1}"  name="dr_cr_qty" id= "dr_cr_qty<%=i %>" 
														<%if(Integer.parseInt(view_truck_inv_cnt.elementAt(i)+"") > 0){%>
															style="text-align: right;width: 50%;"
															<%
															String rate = "";
															if(criteria.contains("DIFF-INRMMBTU")){ 
																rate = "";
															}else{ 
																rate = View_rate.elementAt(i)+"";
															} %>
															onchange="setDiffQty(this,'<%=rate%>','<%=View_truck_inv_no.size() %>');"
															readonly="readonly"
														<%}else{ %>
															style="text-align: right;width: 50%;"
															readonly="readonly"
															title="No Credit/Debit note Generated against to this truck invoice!"
														<%} %>
														<%if(criteria.contains("DIFF-QTY")){%>
															value="<%=view_truck_inv_dr_cr_qty.elementAt(i)%>"
														<%}else if(!dr_cr_doc_no.equalsIgnoreCase("")) {%>
													 		value="<%=serv_dr_cr_qty.elementAt(i)%>"
													 	<%} %>
														>
													</span>&nbsp;</font></div></td>
													<td width="10%"><div align="right"><font size="1.5px" face="Arial"><%=View_rate.elementAt(i) %>&nbsp;</font></div> </td>
													<td width="10%"><div align="center"><font size="1.5px" face="Arial"><input type="number"   name="diff_qty" id= "diff_qty<%=i %>" style="text-align: right;width: 50%" readonly="readonly" 
														<%if(criteria.contains("DIFF-QTY")){%>
																value="<%=view_truck_inv_dr_cr_diff_qty.elementAt(i)%>"
														<%}else if(!dr_cr_doc_no.equalsIgnoreCase("")) {%>
													 		value="<%=serv_diff_qty.elementAt(i)%>"
													 	<%} %>
													></font></div> </td>
												</tr>
									 		<%} %>
									 	</table></td></tr>
									 <%} %>
								 <%}else{ %>
								 	<tr class="text-left" style="font-size: 12px;"  >		    		    
									    <td colspan="2" class="info">Invoice Quantity</td>
									    <td colspan="2">
									    	<input type="text" name="qty" id="qty" value="<%=qty%>" style="text-align:right" size="8" readonly="readonly" class="mkrdly" onblur="">&nbsp;<b>MMBTU</b>
									    </td> 
									    <%
									    if(criteria.contains("DIFF-QTY")){ %>
										   <td colspan="2" class="info"><%if(cr_dr.equalsIgnoreCase("dr")){ if(con_type.equalsIgnoreCase("C")){%> Debit&nbsp; <%}else{ %> Debit&nbsp; <%}}else if(cr_dr.equalsIgnoreCase("cr")){%> Credit&nbsp; <%}else if(cr_dr.equalsIgnoreCase("sp")){%>Debit&nbsp;<%} %>Note Quantity&nbsp;<font color="red"><b>*</b></font></td>
										    <td colspan="2">
										    	<input type="text" name="dr_cr_qty" value="<%=dr_cr_qty %>" style="background:yellow;text-align: right;" size="8"   onchange="setDrCrQty();">&nbsp;<b> MMBTU</b>
										    	&nbsp;&nbsp;<b>Diff. Qty.  <input type="text" name="diff_qty" value="<%=diff_qty %>" readonly="readonly" style="text-align:right;" size="8"> MMBTU</b>
										    </td> 
									    <%} %>
									 </tr>
								 
									 <tr class="text-left" style="font-size: 12px;">   
									    <td colspan="2" class="info">Sales Rate</td>
									    <td colspan="2">
									    	<input type="text" name="sale_rate" value="<%=sale_rate%>" style="text-align:right" size="8" readonly class="mkrdly">&nbsp;<b>$/MMBTU</b>
									    </td>  
									    <%if(criteria.contains("DIFF-PRICE")){ %> 
										     <td colspan="2" class="info"><%if(cr_dr.equalsIgnoreCase("dr")){ if(con_type.equalsIgnoreCase("C")){%> Debit&nbsp; <%}else{ %> Debit&nbsp; <%}}else if(cr_dr.equalsIgnoreCase("cr")){%> Credit&nbsp; <%}else if(cr_dr.equalsIgnoreCase("sp")){%>Debit&nbsp;<%} %> Note Sales Rate</td>
										     <td colspan="2">
										    	<input type="text" name="nw_sale_rate" value="<%=dr_cr_sale_rate %>"  style="background:yellow;text-align: right;" size="8"  class="mkrdly" onblur="change_rate();">&nbsp;<b>$/MMBTU</b>
										    	&nbsp;&nbsp;<b >Diff. Sales Rate  <input type="text" name="diff_rate" value="<%=diff_rate %>" readonly="readonly" style="text-align:right;" size="8">$/MMBTU</b>
										     </td>
									     <%} %>
									</tr>
				  	
				  					<tr class="text-left" style="font-size: 12px;"  >
				  					<%if(criteria.contains("DIFF-EXG")){ %>
				  							    		    
									    <td colspan="2" class="info">Invoice Exchange Rate</td>
									    <td colspan="2">
									    	<input type="text" name="exg_rt" value="<%=exg_rt%>" style="text-align:right" size="8" readonly class="mkrdly">&nbsp;<b>Rs./$</b>
									    </td> 
									    <td colspan="2" class="info"><%if(cr_dr.equalsIgnoreCase("dr")){ if(con_type.equalsIgnoreCase("C")){%> Debit&nbsp; <%}else{ %> Debit&nbsp; <%}}else if(cr_dr.equalsIgnoreCase("cr")){%> Credit&nbsp; <%}else if(cr_dr.equalsIgnoreCase("sp")){%>Debit&nbsp;<%} %>Note Exchange Rate<font color="red"><b>*</b></font></td>
									    <td colspan="2">
									    	<input type="text" name="dr_cr_exg_rt" value="<%=dr_cr_exg_rt%>" style="text-align:right;background:yellow" size="8" onBlur="calcExgRateAmount();checkNumber1(this,7,4);" <%if(!criteria.contains("DIFF-EXG")){%>readonly="readonly" <%} %>>&nbsp;<b>Rs./$</b>
									    	&nbsp;&nbsp;<b >Diff. Exg  <input type="text" name="diff_exg" value="<%=diff_exg %>" readonly="readonly" style="text-align:right;" size="8">Rs./$</b>
									    </td>  
									 <%}else{ %>
									  <td colspan="2" class="info">Invoice Exchange Rate</td>
									    <td colspan="2">
									    	<input type="text" name="exg_rt" value="<%=exg_rt%>" style="text-align:right" size="8" readonly class="mkrdly">&nbsp;<b>Rs./$</b>
									    </td> 
									 <%} %>
									</tr>
								<%} %>
			  					<%if(criteria.contains("DIFF-TAX") && !con_type.equals("V")){ %>
			  					 <tr class="text-left" style="font-size: 12px;">	
			  					  	<td colspan="2" class="info" align="left">Split Quantity<font color="red"><b>*</b></font></td>
								    <td colspan="2">
								    	<input type="number" min="0" onkeyup="checkNum(this);" name="split_qty" <%if(view_flag.equals("Y")){ %> readonly="readonly" class="mkRdly" <%} %> 
								    		<%if(split_qty.equals("")){ %>
								    			value="<%=diff_qty%>"
								    		<%}else{ %>
								    			value="<%=split_qty%>" 
								    		<%} %> 
								    	 style="text-align:right;background:yellow" size="10" onblur="calculate_diff_qtytax('<%=tax_fact%>');">&nbsp;<b>MMBTU</b>
								    </td>
								      
								<%if(!tax_str.equals("")){ 
								  	String tax[]=tax_str.split(",");
								  	for(int kk=0;kk<tax.length;kk++){
								  		tax_strrate.add(tax[kk]);
								  	}
								  } %>
								
								  <%for(int k=0;k<customer_inv_tax_cd.size();k++){ %>	    		    
								    <td colspan="2" class="info">Tax Rate (Enter difference of tax %) <font color="red"><b>*</b></font></td>
								    <td colspan="2"><input type="number" min="0" onkeyup="checkNum(this);" name="tax_" id="tax_<%=k%>" 
								    <%if(!tax_str.equals("")){  %> 
								    	value="<%=tax_strrate.elementAt(k)%>" 
								    <%}else if(!dr_cr_tax_rt.equals("")){ %>
								    	 value="<%=dr_cr_tax_rt%>"
								   	<%} %> 
								   	style="text-align:right;background:yellow" size="10" onblur="calculate_diff_qtytax('<%=tax_fact%>');">&nbsp;<b>%</b><%=customer_inv_tax_nm.elementAt(k)%>
									    <input type="hidden" name="hid_tax_str" value="<%=customer_inv_tax_nm.size()%>">
									    <input type="hidden" name="hid_tax_nm" value="<%=customer_inv_tax_nm.elementAt(k)%>" id="hid_tax_nm<%=k%>">
									    <input type="hidden" name="hid_tax_cd" value="<%=customer_inv_tax_cd.elementAt(k)%>" id="hid_tax_cd<%=k%>">
									    <input type="hidden" name="hid_tax_rt" value="<%=customer_inv_tax_rate.elementAt(k)%>" id="hid_tax_rt<%=k%>">
								    </td>  
								    <%} %>
			  					 </tr>
			  					<%}else if(criteria.contains("DIFF-TAX") && con_type.equals("V")){ %>
			  					
				  					<tr class="text-left" style="font-size: 12px;">	
				  					  	<td colspan="2" class="info" align="left">Invoice Tax Structure </td>
									    <td colspan="2">
									    	<input type="text" name="inv_tax" size="15" value="<%=tax_fact%>" style="text-align:left" readonly class="mkrdly">&nbsp;<b></b>
									    </td>
									    
									    <td colspan="2" class="info" align="left"><%=drcrStr %> Tax Structure</td>
									    <td colspan="2" >
									    	<select class = "select" onchange="calcDiffTax();" id="dr_cr_tax" name="dr_cr_tax">
									    		<option value=""> -Select -</option>
									    		<%for(int i = 0 ; i < serv_tax_desc.size(); i ++){ %>
									    			<option value="<%=serv_tax_perc.elementAt(i)%>"><%=serv_tax_desc.elementAt(i) %></option>
									    		<%} %>
									    	</select>
									    	<script type="text/javascript">
									    		document.getElementById('dr_cr_tax').value = '<%=dr_cr_tax_rate%>';
									    	</script>
									    	&nbsp;&nbsp;<b>Diff. Tax  <input type="text" name="diff_tax_str" id="diff_tax_str" value="<%=diff_tax_str %>" readonly="readonly" style="text-align:left;" size="10"> %</b>
									    </td>
									</tr>    
								    
			  					<%} %>
			  					
			  					<tr id="" class="text-left" style="font-size: 12px;"  >		    		    
								    <td colspan="2" class="info">Invoice Gross Amount</td>
								    <td colspan="2">
								    	<input type="text" name="gross_amt" id="gross_amt" value="<%=gross_amt%>" style="text-align:right" size="10" readonly class="mkrdly">&nbsp;<b>INR</b>
								    </td>
								    
								<%if(criteria.contains("DIFF-EXG") || criteria.contains("DIFF-PRICE") || criteria.contains("DIFF-QTY") || criteria.contains("DIFF-TAX") || criteria.contains("DIFF-INRMMBTU") || criteria.contains("DIFF-INRKM") || criteria.contains("DIFF-LUMPSUM") || criteria.contains("DIFF-KM")){ %>
								    <td colspan="2" class="info"><%if(cr_dr.equalsIgnoreCase("dr")){ if(con_type.equalsIgnoreCase("C")){%> Debit&nbsp;<%}else{ %> Debit&nbsp; <%}}else if(cr_dr.equalsIgnoreCase("cr")){%> Credit <%}else if(cr_dr.equalsIgnoreCase("sp")){%>Debit&nbsp;<%} %> Note Gross Amount</td>
								    <td colspan="2">
								    	<input type="text" name="dr_cr_gross_amt" id="dr_cr_gross_amt" value="<%=dr_cr_gross_amt_val%>" style="text-align:right" size="10" readonly class="mkrdly">&nbsp;<b>INR</b>
								    </td>
								<%} %>
								</tr> 
								
								<tr id="" class="text-left" style="font-size: 12px;"  >		    		    
								    <td colspan="2" class="info">Invoice Tax Amount (<%=tax_fact %>) </td>
								    <td colspan="2">
								    	<input type="text" name="tax_amt" id="tax_amt" value="<%=tax_amt_inr%>" style="text-align:right" size="10" readonly >&nbsp;<b>INR</b>
<!-- 								    	&nbsp;&nbsp;&nbsp;<b>Tax (%) :</b>  -->
								    </td>
								    
								<%if(criteria.contains("DIFF-EXG") || criteria.contains("DIFF-PRICE") || criteria.contains("DIFF-QTY") || criteria.contains("DIFF-TAX") || criteria.contains("DIFF-INRMMBTU") || criteria.contains("DIFF-INRKM") || criteria.contains("DIFF-LUMPSUM") || criteria.contains("DIFF-KM")){ %>
								    <td colspan="2" class="info">
								    	<%if(cr_dr.equalsIgnoreCase("dr")){ if(con_type.equalsIgnoreCase("C")){%> Debit&nbsp;<%}else{ %> Debit&nbsp; <%}}else if(cr_dr.equalsIgnoreCase("cr")){%> Credit <%}else if(cr_dr.equalsIgnoreCase("sp")){%>Debit&nbsp;<%} %>
								    		 Note Tax Amount <span> <font  id = "inv_tax_fact"> 
								    		 <%if(!diff_tax_str.equals("")) {%>
								    			 <%=diff_tax_str %>
								    		 <%}else{ %>
								    		 	(<%=tax_fact %>)
								    		 <%} %>	
								    		 </font></span></td>
								    <td colspan="2">
								    	<input type="text" name="dr_cr_tax_amt" id="dr_cr_tax_amt"  value="<%=dr_cr_tax_amt_inr%>" style="text-align:right" size="10" readonly class="mkrdly">&nbsp;<b>INR</b>
								    </td>
								<%} %>
								</tr> 
								
								<tr class="text-left" style="font-size: 12px;">    
									<td colspan="2" class="info">Invoice Net Amount</td>
								    <td colspan="2">
								    	<input type="text" name="net_amt" value="<%=net_amt%>" style="text-align:right" size="10" readonly class="mkrdly">&nbsp;<b>INR</b>
								    </td> 
								    <%if(tcs_app_flag.equals("Y")){ %>
								   		    		    
								    <td <%if(cr_dr.equalsIgnoreCase("cr")){ %>colspan="1"<%}else{%>colspan="2"<%} %> class="info" ><%=tcs_nm %>  </td>
								    <%if(cr_dr.equalsIgnoreCase("cr")){ %>
								     <td colspan="1" class="info" >
								    	<input type="checkbox"  name="chk" id="chk" onclick="tcsCalc();">&nbsp;&nbsp;Apply
								    	<input type="hidden" name="chkTcs" value="N">
								    </td>
								    <%if(dr_cr_tcs_flag.equals("Y")) {%> 
								    <script>
								    	document.forms[0].chk.checked=true;
								    	document.forms[0].chkTcs.value="Y";
								    </script>
								    <%} %>
								    
								    <%}else{%>
								    	<input type="hidden" name="chkTcs" value="Y">
								   <% } %>
								    
								    <td ><input type="text" name="tax_tcs" value="<%=tcs_amt%>" style="text-align:right;" size="10" maxlength="" readonly="readonly" class="mkRdly">&nbsp;<b>INR</b></td>     
								  </tr>
								  <tr class="text-left" style="font-size: 12px;"> <td colspan="4"></td>
								   <%}else{ %>
								  	 <input type="hidden" name="chkTcs" value="N">
								   <%} %>
								   
								    <%if(criteria.contains("DIFF-EXG") || criteria.contains("DIFF-PRICE") || criteria.contains("DIFF-QTY") || criteria.contains("DIFF-TAX") || criteria.contains("DIFF-INRMMBTU") || criteria.contains("DIFF-INRKM") || criteria.contains("DIFF-LUMPSUM") || criteria.contains("DIFF-KM")){ %>
								    <td colspan="2" class="info"><%if(cr_dr.equalsIgnoreCase("dr")){if(con_type.equalsIgnoreCase("C")){%> Debit&nbsp;<%}else{ %> Debit&nbsp; <%}}else if(cr_dr.equalsIgnoreCase("cr")){%> Credit <%}else if(cr_dr.equalsIgnoreCase("sp")){%>Debit&nbsp;<%} %> Note Net Amount</td>
								    <td colspan="2">
								    	<input type="text" name="dr_cr_net_amt" id = "dr_cr_net_amt" value="<%=dr_cr_net_amt_val%>" style="text-align:right" size="10" readonly class="mkrdly">&nbsp;<b>INR</b>
								    </td>     
								<%} %>    
								</tr>
								 <%if(cr_dr.equalsIgnoreCase("dr")){ %>
									   <tr class="text-left" style="font-size: 12px;">		    		    
										    <td colspan="2" class="info" align="left">Payment Due Date </td>
										    <td align="left" <%if(criteria.equalsIgnoreCase("Imbalance")){ %>colspan="1"<%}else{ %>colspan="3"<%} %>>
										    	<input autocomplete="off" class="datepick" type="text" <%if(view_flag.equals("Y")){ %> readonly="readonly" class="mkRdly" <%} %> name="payduedt" value="<%=paydue%>" style="text-align:right;background:yellow" size="10" onchange="chkdue_dt1();" ></td> 
									  	</tr>
									   <%} %>
			  				<%if(criteria.contains("DIFF-TAX") || criteria.contains("Imbalance")){ %>
							  <tr class="text-left" style="font-size: 12px;" id="item_desc" >		    		    
							    <td colspan="2" class="info" align="left">Line Item Description </td>
							    <td colspan="4">
									<textarea name="item_desc" cols="68" rows="4" <%if(view_flag.equals("Y")){ %> readonly="readonly" class="mkRdly" <%} %> value="<%=rmk_desc %>" style="text-align:left;background:yellow" size="80" maxlength="500" ></textarea>
							    </td> 
							    <script>
							    	document.forms[0].item_desc.value='<%=rmk_desc%>';
							    </script>    
							  </tr>
							  <%} %>
								<tr id="pay_dt" class="text-left" style="font-size: 12px;display: none;"  >		    		    
								    <td colspan="2" class="info">Payment Due Date</td>
								    <td colspan="4">
								    	<input type="text" autocomplete="off" name="pay_due_dt" id="pay_dt" value="<%=due_dt%>" style="text-align:left;background:yellow" size="10" maxlength="" readonly="readonly">
								    	<input type="text" name="pay_due_dt1" class="datepick" id="pay_dt1" value="" style="text-align:left;background:yellow" size="10" maxlength="12" onblur="change_gstno('<%=due_dt%>',this.value,'Payment dt');" autocomplete="off">
								    </td>     
								</tr>
								
								<tr id="tax_structure" class="text-left" style="font-size: 12px;display: none;">		    		    
								    <td colspan="2" class="info">Tax Structure</td>
								    <td colspan="4" class="text-left">
								    	<input type="text" name="tax_struc" id="" value="<%=tax_struc_cd%>" style="text-align:left;background:yellow" size="10" maxlength="" readonly="readonly">
								    	<input type="text" name="tax_struc1" id="" value="" style="text-align:left;background:yellow" size="10" maxlength="" onblur="change_gstno('<%=tax_struc_cd%>',this.value,'Tax structure');">
								    </td>     
								</tr>
								<tr id="pan_tin_no" class="text-left" style="font-size: 12px;display: none;">		    		    
								    <td colspan="2" class="info">PAN NO.</td>
								    <td colspan="2" class="text-left">
								    	<input type="text" name="pan_no1" id="" value="<%=pan_no%>" style="text-align:left;background:yellow" size="10" maxlength="" readonly="readonly">
								    	<input type="text" name="pan_no2" id="" value="" style="text-align:left;background:yellow" size="10" maxlength="" onblur="change_gstno('<%=pan_no%>',this.value,'pan');">
								    </td>  
								    
								    <td colspan="2" class="info">GST TIN NO.</td>
								    <td colspan="4" class="text-left">
								    	<input type="text" name="gst_tin_no1" id="" value="<%=gst_tin_no%>" style="text-align:left;background:yellow" size="12" maxlength="" readonly="readonly">
								    	<input type="text" name="gst_tin_no2" id="" value="" style="text-align:left;background:yellow" size="12" maxlength="" onblur="change_gstno('<%=gst_tin_no%>',this.value,'gst');">
								    </td>   
								</tr>
								<tr id="cst_pan_no" class="text-left" style="font-size: 12px;display: none;" >		    		    
								    <td colspan="2" class="info">CST TIN NO.</td>
								    <td colspan="4" class="text-left">
								    	<input type="text" name="cst_tin_no1" id="" value="<%=cst_tin_no%>" style="text-align:left;background:yellow" size="12" maxlength="" readonly="readonly">
								    	<input type="text" name="cst_tin_no2" id="" value="" style="text-align:left;background:yellow" size="12" maxlength="" onblur="change_gstno('<%=cst_tin_no%>',this.value,'cst');">
								    </td>     
								</tr>
								
								<tr id="cst_pan_no" class="text-left" style="font-size: 12px;display: none;">		    		    
								    <td colspan="2" class="info">Address</td>
								    <td colspan="4" class="text-left">
								    <%String addr=address1+" "+city1+" "+pin1;%>
								    	<input type="text" name="address2" id="" value="<%=addr%>" style="text-align:left;background:yellow" size="70" maxlength="" readonly="readonly"><br>
								     </td>
								     <td colspan="6" class="text-left">	
								    	<input type="text" name="address3" id="" value="" style="text-align:left;background:yellow" size="70" maxlength="" onblur="change_gstno('<%=addr%>',this.value,'Address');">
								    </td>     
								</tr>
								<%if(criteria.contains("DIFF-QTY") && !con_type.equals("V")){ %>
								<tr id="" class="text-left" style="font-size: 12px;" >		    		    
								    <td colspan="2" class="info">Invoice date &nbsp;<font color="red"><b>*</b></font></td>
								    <td colspan="2" class="text-left">
								    	<% for(int i=0;i<inv_period_date.size();i++){ %>
									    	<%if(i!=0 && i%6==0){ %><br><%} %>
										  		<input type="checkbox" name="inv_qty_dt" id="inv_qty_dt<%=i %>" value="" style="text-align:left;background:yellow" onclick="inv_qty_dt1('<%=inv_period_date.size() %>');">
									   			<%=inv_period_date.elementAt(i) %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									   			<input type="hidden" name="dt" id="dates<%=i %>" value="<%=inv_period_date.elementAt(i) %>">
									   		<%} %>
								    </td>     
								</tr>
								<%} %>
								
								<tr id="remarks1" class="text-left" style="font-size: 12px;display: none;">		    		    
								    <td colspan="2" class="info">Remark 1</td>
								    <td colspan="10" class="text-left">
								    	<input type="text" name="remark_11" value="<%=remark_1%>" style="text-align:left;background:yellow" size="120" maxlength="" readonly="readonly">
								    </td>     
								</tr>
								
								<tr id="remarks2" class="text-left" style="font-size: 12px;display: none;">		    		    
								    <td colspan="2" class="info">Remark 2</td>
								    <td colspan="10" class="text-left">
								    	<input type="text" name="remark_12" value="<%=remark_2%>" style="text-align:left;background:yellow" size="120" maxlength="" readonly="readonly">
								    </td>     
								</tr>
								
								<tr id="remarks3" class="text-left" style="font-size: 12px;display: none;">		    		    
								    <td colspan="2" class="info">Remark 3</td>
								    <td colspan="10" class="text-left">
								    	<input type="text" name="remark_13" value="<%=remark_3%>" style="text-align:left;background:yellow" size="120" maxlength="" readonly="readonly">
								    </td>     
								</tr>
								
								<tr id="remarks3" class="text-left" style="font-size: 12px;" >		    		    
								    <td colspan="2" class="info">Remark</td>
								    <td colspan="10" class="text-left">
								    	 <%if(!criteria.contains("REV_INV")){ %>
								    	 	<input type="text" name="remark" value="<%=remark %>" style="text-align:left;background:yellow" size="80" maxlength="500">
								    	 <%}else{ %>
											<textarea name="remark" cols="68" rows="4" value="<%=remark %>" style="text-align:left;background:yellow" size="80" maxlength="500" readonly="readonly"></textarea>	    	 
								    		<script>document.forms[0].remark.value = '<%=remark%>';</script>
								    	 <%} %>
								    </td>     
								</tr>
								
								<tr class="info" style="font-size: 12px;">  
							  		<td class="text-center" colspan="8">
								  		<%if(tcs_app_flag.equals("Y")){ %>
								  			Note : TCS is applicable for this 
								  		<%if(cr_dr.equalsIgnoreCase("dr")){%>
								  			Debit <%}else if(cr_dr.equalsIgnoreCase("cr")){ %>Credit<%} %> note..    
								  		<%}else if(tds_app_flag.equals("Y")){ %>
								   	 		Note : TDS is applicable for this 
								   		<%if(cr_dr.equalsIgnoreCase("dr")){%> Debit <%}else if(cr_dr.equalsIgnoreCase("cr")){ %>Credit<%} %>
								   			note..  
								   	 <%} %>
								  		
							  		</td>
							  	</tr>
							</tbody>
						</table>
	<script>
	  <%if(view_flag.equals("Y")) { %>
		  
		  	document.forms[0].sub.disabled = true;
		  	document.forms[0].close.disabled = true;
		  	document.forms[0].remark.disabled = true;
		  	document.forms[0].dr_cr_dt.disabled = true;
		  	if(document.forms[0].nw_sale_rate) {
		  		document.forms[0].nw_sale_rate.disabled = true;
		  	}
		  	if(document.forms[0].dr_cr_exg_rt) {
		  		document.forms[0].dr_cr_exg_rt.disabled = true;
		  	}
		  	if(document.forms[0].pay_dt1) {
		  		document.forms[0].pay_dt1.disabled = true;
		  	}
		  	if(document.forms[0].tax_struc1) {
		  		document.forms[0].tax_struc1.disabled = true;
		  	}
		  	if(document.forms[0].pan_no2) {
		  		document.forms[0].pan_no2.disabled = true;
		  	}
		  	if(document.forms[0].gst_tin_no2) {
		  		document.forms[0].gst_tin_no2.disabled = true;
		  	}
		  	if(document.forms[0].cst_tin_no2) {
		  		document.forms[0].cst_tin_no2.disabled = true;
		  	}
		  	if(document.forms[0].address3) {
		  		document.forms[0].address3.disabled = true;
		  	}
		  	if(document.forms[0].inv_qty_dt) {
		  		var sz = document.forms[0].dt.length;
		  		if(sz==undefined) {
		  			document.forms[0].inv_qty_dt.disabled = true;
		  		} else {
		  			for(var i=0;i<parseInt(sz);i++) {
		  				document.forms[0].inv_qty_dt[i].disabled = true;
		  			}
		  		}
		  	}
		  	if(document.forms[0].remark_11) {
		  		document.forms[0].remark_11.disabled = true;
		  	}
		  	if(document.forms[0].remark_12) {
		  		document.forms[0].remark_12.disabled = true;
		  	}
		  	if(document.forms[0].remark_13) {
		  		document.forms[0].remark_13.disabled = true;
		  	}
		  	if(document.forms[0].chng_pin) {
		  		document.forms[0].chng_pin.disabled = true;
		  	}
		  	if(document.forms[0].chng_tax_struc) {
		  		document.forms[0].chng_tax_struc.disabled = true;
		  	}
		  	if(document.forms[0].chng_inv_dt) {
		  		document.forms[0].chng_inv_dt.disabled = true;
		  	}
		  	if(document.forms[0].chng_desc_items) {
		  		document.forms[0].chng_desc_items.disabled = true;
		  	}
		  	if(document.forms[0].chng_err) {
		  		document.forms[0].chng_err.disabled = true;
		  	}
		  	if(document.forms[0].chng_pay_dt) {
		  		document.forms[0].chng_pay_dt.disabled = true;
		  	}
	  <% } %>
	  </script>
	</div>	
		<input type="hidden" name="option" value="submitDebitCreditNote_gen">		
		<input type="hidden" name="flg" value="modify">
		<input type="hidden" name="customer_cd" value="<%=bscode%>">
		<input type="hidden" name="cr_dr" id="cr_dr" value="<%=cr_dr%>">
		<input type="hidden" name="st_dt" value="<%=st_dt%>">
		<input type="hidden" name="end_dt" value="<%=end_dt%>">
		<input type="hidden" name="fin_yr" value="<%=fin_yr%>">
		<input type="hidden" name="dr_cr_fin_yr" value="<%=dr_cr_fin_year%>">
		<input type="hidden" name="criteria" value="<%=criteria%>">
		<input type="hidden" name="total_diff_qty" id="total_diff_qty"  value="<%=total_diff_qty%>">
		<input type="hidden" name="con_type" id="con_type" value="<%=con_type%>">
		<input type="hidden" name="hlpl_no" value="<%=hlpl_no%>">
		<input type="hidden" name="plant_no" value="<%=plant_no%>">
		<input type="hidden" name="fgsa_no" value="<%=fgsa_no%>">
		<input type="hidden" name="sn_no" value="<%=sn_no%>">
		<input type="hidden" name="fgsa_rev_no" value="<%=fgsa_rev_no%>">
		<input type="hidden" name="sn_rev_no" value="<%=sn_rev_no%>">
		<input type="hidden" name="cust_nm" value="<%=cust_nm%>">
		<input type="hidden" name="gross_amt_usd" value="<%=gross_amt_usd%>">
		<input type="hidden" name="tax_struc_cd" value="<%=tax_struc_cd%>">
		<input type="hidden" name="tax_amt_inr" value="<%=tax_amt_inr%>">
		<input type="hidden" name="write_permission" value="<%=write_permission%>">
		<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
		<input type="hidden" name="print_permission" value="<%=print_permission%>">
		<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
		<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
		<input type="hidden" name="form_id" value="<%=form_id%>">
		<input type="hidden" name="form_nm" value="<%=form_nm%>">
		<input type="hidden" name="mapping_id" value="<%=mapping_id%>">
		<input type="hidden" name="gross_amt_usd1" value="<%=gross_amt_usd%>">
		<input type="hidden" name="dr_cr_sale_rate1" value="<%=dr_cr_sale_rate%>">
		<input type="hidden" name="dr_cr_gross_amt_val" value="<%=dr_cr_gross_amt_val%>">
		<input type="hidden" name="fin_yr_format" value="2016-2017">
		
		<input type="hidden" name="tcs_app_flag" value="<%=tcs_app_flag%>">
		<input type="hidden" name="tcs_amt" value="<%=tcs_amt%>">
		<input type="hidden" name="Stax_str" value="">
		
					<div class="box-footer">
					<div class="row">
						<div class="col-sm-12 text-center">
						<button type="button" class="btn btn-warning"  name="close"  onclick="window.close();">Close</button>
<!-- 							<button type="button" class="btn btn-warning" name="reSetPage" value="Reset" onClick="refreshPage();">Reset</button> -->
							<button type="button" class="btn btn-success"  name="sub" <% if(dr_cr_aprv > 0){%>disabled="disabled" title="DR/CR Note Approved..!" <%}%>
								<%if(dr_cr_doc_no.equalsIgnoreCase("")){ %>
									Value="Submit" 
									<%}else{ %>
									Value="Modify"
								<%} %> onclick="doSubmit();">
							<%if(dr_cr_doc_no.equalsIgnoreCase("")){ %>
								Submit
							<%}else{ %>
								Modify
							<%} %>
							</button>
							
						</div>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
		
<script type="text/javascript">
$('.datepick').each(function() {
	$(this).datepicker({
		changeMonth : true,
		changeYear : true,
		format : "dd/mm/yyyy",
		language : "en",
		autoclose : true,
		todayHighlight : true,
		orientation : "bottom auto"
// 		startDate : "tommorow"
	});
});

function calcDiff(obj){
	
	var cr_dr = document.forms[0].cr_dr.value;
	var calc_base = document.getElementById('calc_base').value;
	var criteria = document.forms[0].criteria.value;
	var inr_rate = "";
	var qty = "";
	
	if(criteria.indexOf('DIFF-QTY')!=-1){
		qty = document.getElementById('total_diff_qty').value;
	}else{
		qty = document.getElementById('qty').value;
	} 
	var dr_cr_tax_perc = document.getElementById('hid_dr_cr_tax').value;
	var msg = "";
	var diff_inr_mmbtu = "0";
	var rate_id = "";
	var msgContent = "";
	
	if(calc_base == '1'){
		
		rate_id = "diff_inr_mmbtu";
		inr_rate = document.forms[0].inr_mmbtu.value;
		msgContent = "INR/MMBTU";
		
	}else if(calc_base == '2'){
		
		rate_id = "diff_inr_km";
		inr_rate = document.forms[0].inr_km.value;
		msgContent = "INR/KM";
		
	}
	
// 	alert(rate_id+"***"+rate_id+"***"+rate_id+"****"+calc_base)
	if(cr_dr == 'dr'){
		
		if(parseFloat(inr_rate) < parseFloat(obj.value)){
			
			diff_inr_mmbtu = (parseFloat(obj.value) - parseFloat(inr_rate));
			document.getElementById(rate_id).value = diff_inr_mmbtu.toFixed(2);
			
			
			document.getElementById('dr_cr_gross_amt').value = (parseFloat(diff_inr_mmbtu) * parseFloat(qty)).toFixed(2);
// 			alert((parseFloat(document.getElementById('dr_cr_gross_amt').value) * parseFloat(dr_cr_tax_perc))/100)
			document.getElementById('dr_cr_tax_amt').value = ((parseFloat(document.getElementById('dr_cr_gross_amt').value) * parseFloat(dr_cr_tax_perc))/100).toFixed(2);
			document.getElementById('dr_cr_net_amt').value = (parseFloat(document.getElementById('dr_cr_gross_amt').value) + parseFloat(document.getElementById('dr_cr_tax_amt').value)).toFixed(2);
			
			if(criteria.indexOf('DIFF-TAX')!=-1){
				calcDiffTax();
			}
		}else{
			msg+="Debit Note "+msgContent+" should be greater than Invoice INR/MMBTU";	
			document.getElementById(rate_id).value = "";
			document.getElementById('dr_cr_gross_amt').value = "";
			document.getElementById('dr_cr_tax_amt').value = "";
			document.getElementById('dr_cr_net_amt').value = "";
		}
	}else{
		
		if(parseFloat(inr_rate) > parseFloat(obj.value)){
			
			diff_inr_mmbtu = parseFloat(inr_rate) - parseFloat(obj.value);
			document.getElementById(rate_id).value = diff_inr_mmbtu.toFixed(2);
			document.getElementById('dr_cr_gross_amt').value = (parseFloat(diff_inr_mmbtu) * parseFloat(qty)).toFixed(2);
			document.getElementById('dr_cr_tax_amt').value = ((parseFloat(document.getElementById('dr_cr_gross_amt').value) * parseFloat(dr_cr_tax_perc))/100).toFixed(2);
			document.getElementById('dr_cr_net_amt').value = (parseFloat(document.getElementById('dr_cr_gross_amt').value) + parseFloat(document.getElementById('dr_cr_tax_amt').value)).toFixed(2);
			
			if(criteria.indexOf('DIFF-TAX')!=-1){
				calcDiffTax();
			}
		}else{
			msg+="Credit Note "+msgContent+" should be less than Invoice INR/MMBTU";
			document.getElementById(rate_id).value = "";
			document.getElementById('dr_cr_gross_amt').value = "";
			document.getElementById('dr_cr_tax_amt').value = "";
			document.getElementById('dr_cr_net_amt').value = "";
		}
	}
	if(msg!=""){
		alert(msg);
		obj.value = "";
	}
}

function calcDrCrGrossAmt(obj,invSz){
	
	var dr_cr_tax_perc = document.getElementById('hid_dr_cr_tax').value;
	var cr_dr = document.forms[0].cr_dr.value;
	var criteria = document.forms[0].criteria.value;
	var msg = "";
	var total_gross = 0 ;
	var temp_gross = 0;
	for(var i = 0 ; i < parseInt(invSz) ; i++){
		
		var rate = document.getElementById('rate'+i).innerHTML;
		var diff_truck_rt = 0;
		var obj_1 = document.getElementById('dr_cr_qty'+i);
		var inr_truck = document.getElementById('dr_cr_qty'+i).value;
		var diff_qty = 0;
		
		if(obj_1.id == obj.id){		
			if(cr_dr == 'dr'){
				if(parseFloat(rate) < parseFloat(inr_truck)){	
					if(inr_truck == ''){
						inr_truck = 0 ;
					}
					diff_truck_rt = parseFloat(inr_truck) - parseFloat(rate);
					
				}else{
					obj.value = "";
					if(inr_truck == ''){
						inr_truck = 0 ;
					}
					msg="Debit Note INR/Truck should be greater than Invoice INR/Truck";
				}
			}else if(cr_dr == 'cr'){
				
				if(parseFloat(rate) > parseFloat(inr_truck)){	
					if(inr_truck == ''){
						inr_truck = 0 ;
					}
					diff_truck_rt = parseFloat(rate) - parseFloat(inr_truck);
				}else{
					
					obj.value = "";
					if(inr_truck == ''){
						inr_truck = 0 ;
					}
					msg="Credit Note INR/Truck should be less than Invoice INR/Truck";	
				}
			}
		}else{
			if(cr_dr == 'dr'){
				if(parseFloat(rate) < parseFloat(inr_truck) ){	
					if(inr_truck == ''){
						inr_truck = 0 ;
					}
					diff_truck_rt = parseFloat(inr_truck) - parseFloat(rate);
				}
			}else if(cr_dr == 'cr'){
				if(parseFloat(rate) > parseFloat(inr_truck)){	
					if(inr_truck == ''){
						inr_truck = 0 ;
					}
					diff_truck_rt = parseFloat(rate) - parseFloat(inr_truck);
				}
			}
		}

// 		alert(obj_1.id)
		if(obj.id == obj_1.id){
			document.getElementById('diff_qty'+i).value = diff_truck_rt.toFixed(2);
		}
// 		var tot_diff = 0 ;
		var diff_truck_rate = document.getElementById('diff_qty'+i).value;
		
		if( diff_truck_rate == '' || diff_truck_rate == ' '){
			diff_truck_rate = 0 ;
		}
		total_gross+=parseFloat(diff_truck_rate); 
		if(inr_truck == '' || inr_truck == ' '){
			inr_truck = 0 ;
		}
		temp_gross+=parseFloat(inr_truck);
	}
	if(msg!=""){
		alert(msg);
		obj.value = "";
	}
	
	document.getElementById('hid_tot_qty').value = parseFloat(temp_gross).toFixed(2);
	document.getElementById('dr_cr_gross_amt').value = total_gross.toFixed(2);
	document.getElementById('dr_cr_tax_amt').value = ((parseFloat(total_gross) * parseFloat(dr_cr_tax_perc))/100).toFixed(2);
	document.getElementById('dr_cr_net_amt').value = (parseFloat(total_gross) + parseFloat(document.getElementById('dr_cr_tax_amt').value)).toFixed(2);
	
	if(criteria.indexOf('DIFF-TAX')!=-1){
		calcDiffTax();
	}
}


function calcDrCrQty(obj,invSz){
	
	var dr_cr_tax_perc = document.getElementById('hid_dr_cr_tax').value;
	var cr_dr = document.forms[0].cr_dr.value;
	var gross_amt = document.getElementById('gross_amt').value;
	
	var msg = "";
	var total_gross = 0 ;
	var criteria = document.forms[0].criteria.value;
	var diff_inr_km = 0; 
	var multiFlag = "N";
	var drcr = "";
	var temp_qty = 0;
	var total_diff = 0 ;
	var dr_cr_inr_km = 0 ;
	var inv_qty = 0 ;
	
	if(cr_dr == 'dr'){
		drcr = "Debit Note ";
	}else if(cr_dr == 'dr'){
		drcr = "Credit Note ";
	}
	
	if(criteria.indexOf('DIFF-INRKM')!=-1){
		diff_inr_km = document.getElementById('diff_inr_km').value;
		dr_cr_inr_km = document.getElementById('dr_cr_inr_km').value;
		inv_qty = document.getElementById('inr_km').value;
		multiFlag = "Y";
	}else{
		dr_cr_inr_km = document.getElementById('inr_km').value;
	}
	
	if(parseFloat(diff_inr_km) > 0 || multiFlag == "N"){
		
		for(var i = 0 ; i < parseInt(invSz) ; i++){
			
			var invQty = document.getElementById('invQty'+i).innerHTML;
			var dr_cr_qty = document.getElementById('dr_cr_qty'+i).value;
			var obj_1 = document.getElementById('dr_cr_qty'+i);
			var rate = document.getElementById('rate'+i).innerHTML;

			if(parseFloat(diff_inr_km) > 0){
				rate = diff_inr_km;
			}
			var diff_qty = 0;
			if(obj_1.id == obj.id){
				if(cr_dr == 'dr'){
					if(parseFloat(invQty) < parseFloat(dr_cr_qty) ){	
						if(dr_cr_qty == ''){
							dr_cr_qty = 0 ;
						}
						diff_qty = parseFloat(dr_cr_qty) - parseFloat(invQty);
					}else{
						if(dr_cr_qty == ''){
							dr_cr_qty = 0 ;
						}
						msg="Debit Note KM should be greater than Invoice KM";
					}
				}else if(cr_dr == 'cr'){
					if(parseFloat(invQty) > parseFloat(dr_cr_qty)){	
						if(dr_cr_qty == ''){
							dr_cr_qty = 0 ;
						}
						diff_qty = parseFloat(invQty) - parseFloat(dr_cr_qty);
					}else{
						obj.value = "";
						if(dr_cr_qty == ''){
							dr_cr_qty = 0 ;
						}
						msg="Credit Note KM should be less than Invoice KM";
					}
				}
			}else{
				if(cr_dr == 'dr'){
					if(parseFloat(invQty) < parseFloat(dr_cr_qty) ){	
						if(dr_cr_qty == ''){
							dr_cr_qty = 0 ;
						}
						diff_qty = parseFloat(dr_cr_qty) - parseFloat(invQty);
					}
				}else if(cr_dr == 'cr'){
					if(parseFloat(invQty) > parseFloat(dr_cr_qty)){	
						if(dr_cr_qty == ''){
							dr_cr_qty = 0 ;
						}
						diff_qty = parseFloat(invQty) - parseFloat(dr_cr_qty);
					}
				}	
			}
	// 		alert(obj_1.id+"****"+obj.id)
			if(obj.id == obj_1.id){
				document.getElementById('diff_qty'+i).value = diff_qty.toFixed(2);
			}
	// 		var tot_diff = 0 ;
			var diff_quantity = document.getElementById('diff_qty'+i).value;
			
			if( diff_quantity == '' || diff_quantity == ' '){
				diff_quantity = 0 ;
			}
			total_gross+=parseFloat(diff_quantity) * parseFloat(rate); 
			if(dr_cr_qty == ''){
				dr_cr_qty = 0 ;
			}
// 			alert(dr_cr_qty)
			temp_qty+=parseFloat(dr_cr_qty);
			total_diff+=parseFloat(diff_qty);
		}
	}else{
		obj.value = "";
		alert("Please enter "+drcr+ "INR/KM first");
	}
	if(msg!=""){
		alert(msg);
		obj.value = "";
	}
	
// 	alert(diff_inr_km+"***"+inv_qty)
	var temp_gross1 = parseFloat(total_diff) * parseFloat(dr_cr_inr_km);
	var temp_gross2 = parseFloat(diff_inr_km) * parseFloat(inv_qty);
// 	alert(gross_amt+"****"+temp_qty+"***"+dr_cr_inr_km)
	if(cr_dr == 'dr' && criteria.indexOf('DIFF-INRKM')!=-1){
// 		total_gross = parseFloat(temp_gross1) + parseFloat(temp_gross2);
		total_gross = (parseFloat(temp_qty) * parseFloat(dr_cr_inr_km)) - parseFloat(gross_amt);
	}else if(cr_dr == 'cr'){
		total_gross = parseFloat(gross_amt) - (parseFloat(temp_qty) * parseFloat(dr_cr_inr_km));
	}
// 	alert(total_gross)
	document.getElementById('hid_tot_qty').value = parseFloat(dr_cr_qty).toFixed(2);
	document.getElementById('dr_cr_gross_amt').value = total_gross.toFixed(2);
	document.getElementById('dr_cr_tax_amt').value = ((parseFloat(total_gross) * parseFloat(dr_cr_tax_perc))/100).toFixed(2);
	document.getElementById('dr_cr_net_amt').value = (parseFloat(total_gross) + parseFloat(document.getElementById('dr_cr_tax_amt').value)).toFixed(2);

	if(criteria.indexOf('DIFF-TAX')!=-1){
		calcDiffTax();
	}
}

function calcDiffTax(){
	
	var dr_cr_tax = document.getElementById('dr_cr_tax').value;
	if(dr_cr_tax !=""){
		
		var diff_tax = 0 ;
		var diff_tax_str = "";
		var tax_amt = 0 ;
		
		var cr_dr = document.getElementById('cr_dr').value;
		var tax_td = document.getElementById('tax_td').value;
		var inv_tax_perc = document.getElementById('serv_total_tax_perc').value;
		var dr_cr_gross_amt = document.getElementById('dr_cr_gross_amt').value;
		var qty =  document.getElementById('qty').value;
		var criteria = document.forms[0].criteria.value;
		var inv_tax_amt = document.forms[0].tax_amt.value;
		var calc_base = document.getElementById('calc_base').value;
		
		var dr_cr_rate = "";
		var temp_gross = "";
		var temp_tax = "";
		var temp_dr_cr_tax = "";
		
		if(criteria == 'DIFF-TAX--'){
			if(calc_base == '1'){
				dr_cr_rate = document.getElementById('inr_mmbtu').value;
			}else if(calc_base == '2'){
				dr_cr_rate = document.getElementById('inr_km').value;
			}else{
				dr_cr_rate = "1";
			}
		}
		if(criteria.indexOf('DIFF-INRMMBTU')!=-1){
			dr_cr_rate = document.getElementById('dr_cr_inr_mmbtu').value;
		}
		if(criteria.indexOf('DIFF-LUMPSUM')!=-1){
			dr_cr_rate = "1";
			qty = document.getElementById('hid_tot_qty').value;
		}
		if(criteria.indexOf('DIFF-INRKM')!=-1){
			dr_cr_rate = document.getElementById('dr_cr_inr_km').value;
		}
		if(criteria.indexOf('DIFF-KM')!=-1){
			qty = document.getElementById('hid_tot_qty').value;
		}
		if(criteria.indexOf('DIFF-QTY')!=-1){
			qty = document.getElementById('hid_tot_qty').value;
		}
// 		alert(dr_cr_rate+"***"+qty+"***"+dr_cr_tax)
		temp_gross = parseFloat(qty) * parseFloat(dr_cr_rate);
		temp_tax = (parseFloat(temp_gross) * parseFloat(dr_cr_tax)) / 100 ;
		
		if(parseFloat(dr_cr_gross_amt) > 0){

		}else{
			dr_cr_gross_amt = document.getElementById('gross_amt').value;
// 			document.getElementById('dr_cr_gross_amt').value = dr_cr_gross_amt ;
		}
// 		alert(inv_tax_amt+"***"+temp_tax)
		if(cr_dr == 'dr'){
			
			diff_tax = parseFloat(dr_cr_tax) - parseFloat(inv_tax_perc);
			temp_dr_cr_tax = parseFloat(temp_tax) - parseFloat(inv_tax_amt);
		
		}else if(cr_dr == 'cr'){
			diff_tax = parseFloat(inv_tax_perc) - parseFloat(dr_cr_tax);
			temp_dr_cr_tax = parseFloat(inv_tax_amt) - parseFloat(temp_tax);
		}
		
		if(tax_td == 'I'){
			diff_tax_str = "(IGST- "+diff_tax.toFixed(1)+")";
		}else{
			diff_tax_str = "(CGST,SGST- "+(parseFloat(diff_tax)/2).toFixed(1)+")";
		}
		document.getElementById('diff_tax_str').value = diff_tax_str; 
		document.getElementById("inv_tax_fact").innerHTML = diff_tax_str;
		
// 		for amount calc
		tax_amt = ((parseFloat(dr_cr_gross_amt) * parseFloat(diff_tax))/100).toFixed(2);
		if(criteria == 'DIFF-TAX--'){ //for this criteria, only differential tax should be shown in total
			document.getElementById('dr_cr_tax_amt').value = tax_amt;
			document.getElementById('dr_cr_net_amt').value = (parseFloat(tax_amt)).toFixed(2);
		}else{
			document.getElementById('dr_cr_tax_amt').value = temp_dr_cr_tax;
			document.getElementById('dr_cr_net_amt').value = (parseFloat(dr_cr_gross_amt) + parseFloat(temp_dr_cr_tax)).toFixed(2);
		}
		document.getElementById('hid_dr_cr_tax').value = diff_tax ; 
	
	}else{
		
		document.getElementById('diff_tax_str').value = "";
		document.getElementById('dr_cr_tax_amt').value = "";
// 		document.getElementById('dr_cr_gross_amt').value = "";
		document.getElementById("inv_tax_fact").innerHTML = "";
		document.getElementById('dr_cr_net_amt').value = "";
		document.getElementById('hid_dr_cr_tax').value = inv_tax_perc;
	}
	
}

function setDiffQty(obj,rate,invSz){
	
	if(rate == ""){
		rate = document.getElementById('diff_inr_mmbtu').value;
	}
// 	if(obj.value !=""){
		
		var cr_dr = document.getElementById('cr_dr').value;
		var total_diff_qty = 0 ;
		var dr_cr_tax_perc = document.getElementById('hid_dr_cr_tax').value;
		var total_gross = 0 ;
		var msg = "";
		var temp_qty = 0 ;
		
		for(var i = 0 ; i < parseInt(invSz) ; i++){
			
			var diff_qty = 0 ;
			var dr_cr_qty = document.getElementById('dr_cr_qty'+i).value;
			var invQty = document.getElementById('invQty'+i).innerHTML;
			var obj_1 = document.getElementById('dr_cr_qty'+i);
			
			if(obj_1.id == obj.id){
				if(cr_dr == 'dr'){
					if(parseFloat(invQty) < parseFloat(dr_cr_qty) ){	
						if(dr_cr_qty == ''){
							dr_cr_qty = 0 ;
						}
						diff_qty = parseFloat(dr_cr_qty) - parseFloat(invQty);
					}else{
						if(dr_cr_qty == ''){
							dr_cr_qty = 0 ;
						}
						msg="Debit Note Quantity should be greater than Invoice Quantity";
					}
				}else if(cr_dr == 'cr'){
					
					if(parseFloat(invQty) > parseFloat(dr_cr_qty)){	
						if(dr_cr_qty == ''){
							dr_cr_qty = 0 ;
						}
						diff_qty = parseFloat(invQty) - parseFloat(dr_cr_qty);
					}else{
						obj.value = "";
						if(dr_cr_qty == ''){
							dr_cr_qty = 0 ;
						}
						msg="Credit Note Quantity should be less than Invoice Quantity";
					}
				}
			}else{
				if(cr_dr == 'dr'){
					if(parseFloat(invQty) < parseFloat(dr_cr_qty) ){	
						if(dr_cr_qty == ''){
							dr_cr_qty = 0 ;
						}
						diff_qty = parseFloat(dr_cr_qty) - parseFloat(invQty);
					}
				}else if(cr_dr == 'cr'){
					if(parseFloat(invQty) > parseFloat(dr_cr_qty)){	
						if(dr_cr_qty == ''){
							dr_cr_qty = 0 ;
						}
						diff_qty = parseFloat(invQty) - parseFloat(dr_cr_qty);
					}
				}	
			}
			
			if(obj.id == obj_1.id){
				document.getElementById('diff_qty'+i).value = diff_qty.toFixed(2);
			}
	// 		var tot_diff = 0 ;
			var diff_quantity = document.getElementById('diff_qty'+i).value;
			
			if( diff_quantity == '' || diff_quantity == ' '){
				diff_quantity = 0 ;
			}
			total_gross+=parseFloat(diff_quantity) * parseFloat(rate); 
			if(dr_cr_qty == ''){
				dr_cr_qty = 0 ;
			}
			temp_qty+=parseFloat(dr_cr_qty);
		}
		
		if(msg!=""){
			alert(msg);
			obj.value = "";
		}
		document.getElementById('hid_tot_qty').value = temp_qty.toFixed(2);
		document.getElementById('dr_cr_gross_amt').value = total_gross.toFixed(2);
		document.getElementById('dr_cr_tax_amt').value = ((parseFloat(total_gross) * parseFloat(dr_cr_tax_perc))/100).toFixed(2);
		document.getElementById('dr_cr_net_amt').value = (parseFloat(total_gross) + parseFloat(document.getElementById('dr_cr_tax_amt').value)).toFixed(2);
}

function calcAmt(total_diff_qty,rate_service,hid_dr_cr_tax){
	
	var total_gross = (parseFloat(total_diff_qty) * parseFloat(rate_service));
	document.getElementById('dr_cr_gross_amt').value = total_gross.toFixed(2);
	document.getElementById('dr_cr_tax_amt').value = ((parseFloat(total_gross) * parseFloat(dr_cr_tax_perc))/100).toFixed(2);
	document.getElementById('dr_cr_net_amt').value = (parseFloat(total_gross) + parseFloat(document.getElementById('dr_cr_tax_amt').value)).toFixed(2);
}
</script>
</html>		
					