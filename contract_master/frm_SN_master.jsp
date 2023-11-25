<%@ page buffer="128kb"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.util.Vector" %>
<%@ page language="java" import="java.util.*" errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>DLNG | SN Master</title>

<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css" >
<link rel="stylesheet" href="../css/pt_sans.css">
<link rel="stylesheet" href="../css/jquery-ui.css">
<link rel="stylesheet" href="../css/style.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js" ></script>

<style type="text/css">
table tr{
   color: black;
}
.select {
    width: 200px;
    height: 27px;
} 

input[readonly] {
    background-color: #d8d8d8;
}
</style>
<script type="text/javascript">
function checkNum(obj){
	
	var regex  = /^[+]?([0-9]+(?:[\.][0-9]*)?|\.[0-9]+)$/;
	var val = obj.value;
	if(val.match(regex)){
		
	}else{
// 		alert('Not Allowed');
		obj.value= "";
		
	}
}

var newWindow5;
function fetchBuyerList()
{
	var buyer_name = document.forms[0].buyer_name.value;
	var sign_dt = document.forms[0].sign_dt.value;
	var activity;
	if(document.forms[0].rd2[0].checked)
	{
		activity = "insert";
	}
	else if(document.forms[0].rd2[1].checked)
	{
		activity = "update";	
	}

	if(sign_dt==null || sign_dt=='' || sign_dt==' ' || sign_dt=='  ')
	{
		alert("Please Enter The Signing Date First !!!");
	}
	else
	{
		if(!newWindow5 || newWindow5.closed)
		{
			newWindow5=window.open("frm_buyer_list.jsp?buyer_name="+buyer_name+"&sign_dt="+sign_dt+"&activity="+activity,"BUYER_LIST","top=10,left=10,width=800,height=400,scrollbars=1");
		}
		else
		{
			newWindow5.close();
			newWindow5=window.open("frm_buyer_list.jsp?buyer_name="+buyer_name+"&sign_dt="+sign_dt+"&activity="+activity,"BUYER_LIST","top=10,left=10,width=800,height=400,scrollbars=1");
		}
	}
}


function openBillingDetail(cls_cd,cls_nm,fgsa_no,rev_no,bscode,SN_CD,SN_REV_NO)
{
	var flg = document.forms[0].flg.value;
	var St_dt=""+document.forms[0].st_dt.value;
	var rate=document.forms[0].salesPrice.value;
	
	var customer_nm = document.forms[0].buyer_name.value;
	var customer_abbr = document.forms[0].buyer_abr.value;
	var advAmtFlg = document.forms[0].advAmtFlg.value;
	
	var ld="N";
	var top="N";
	var mug="N";
// 	var form_id = document.forms[0].form_id.value;
// 	var form_nm = document.forms[0].form_nm.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	if(cls_nm=="Billing Details")
	{
		for (var i=0; i < document.forms[0].clause_nm.length; i++)
   		{
     		if(document.forms[0].clause_nm[i].checked)
     	 	{
     	    	if(document.forms[0].clause_nm[i].value==cls_cd)
    	 		{ 
    		  		if(!newWindow5 || newWindow5.closed)
	 				{
						newWindow5 = window.open("frm_SN_bill_clause.jsp?FGSA_No="+fgsa_no+"&Rev_No="+rev_no+"&bscode="+bscode+"&Start_Dt="+St_dt+"&SN_CD="+SN_CD+"&SN_REV_NO="+SN_REV_NO+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&advAmtFlg="+advAmtFlg,"SN_BILLING","top=10,left=10,width=900,height=600,scrollbars=1");
					}
					else
					{
						newWindow5.close();
						newWindow5 = window.open("frm_SN_bill_clause.jsp?FGSA_No="+fgsa_no+"&Rev_No="+rev_no+"&bscode="+bscode+"&Start_Dt="+St_dt+"&SN_CD="+SN_CD+"&SN_REV_NO="+SN_REV_NO+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&advAmtFlg="+advAmtFlg,"SN_BILLING","top=10,left=10,width=900,height=600,scrollbars=1");
					}
           		}
      		}
    	}
	}
	else if(cls_nm=="Liability")
	{
		for(var i=0; i < document.forms[0].clause_nm.length; i++)
   		{
  	 		if(document.forms[0].clause_nm[i].checked)
     	 	{
     			if(document.forms[0].clause_nm[i].value==cls_cd)
    		 	{ 		   
    		 		var dcq = document.forms[0].dcq.value;
    		 		var tcq = document.forms[0].tcq.value;
    		  		if(!newWindow5 || newWindow5.closed)
	 				{
	 					newWindow5 = window.open("frm_SN_liability_clause.jsp?FGSA_No="+fgsa_no+"&Rev_No="+rev_no+"&bscode="+bscode+"&Start_Dt="+St_dt+"&ld="+ld+"&top="+top+"&mug="+mug+"&SN_CD="+SN_CD+"&SN_REV_NO="+SN_REV_NO+"&rate="+rate+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&dcq="+dcq+"&tcq="+tcq,"SN_LIABILITY","top=10,left=10,width=900,height=600,scrollbars=1");
					}
					else
					{
						newWindow5.close();
						newWindow5 = window.open("frm_SN_liability_clause.jsp?FGSA_No="+fgsa_no+"&Rev_No="+rev_no+"&bscode="+bscode+"&Start_Dt="+St_dt+"&ld="+ld+"&top="+top+"&mug="+mug+"&SN_CD="+SN_CD+"&SN_REV_NO="+SN_REV_NO+"&rate="+rate+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&dcq="+dcq+"&tcq="+tcq,"SN_LIABILITY","top=10,left=10,width=900,height=600,scrollbars=1");
					}
           		}
    	 	}
   		}
	}
	else if(cls_nm=="Letter of Credit")
	{
		for (var i=0; i < document.forms[0].clause_nm.length; i++)
   		{
     		if(document.forms[0].clause_nm[i].checked)
     	 	{
     	    	if(document.forms[0].clause_nm[i].value==cls_cd)
    	 		{ 
    		  		if(!newWindow5 || newWindow5.closed)
	 				{
						newWindow5 = window.open("frm_FLSA_LC_monitoring.jsp?cont_type=S&fgsa_no="+fgsa_no+"&fgsa_rev_no="+rev_no+"&customer_cd="+bscode+"&customer_nm="+customer_nm+"&customer_abbr="+customer_abbr+"&sn_no="+SN_CD+"&sn_rev_no="+SN_REV_NO+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"SN_LC_DTL","top=10,left=10,width=900,height=500,scrollbars=1,status=1");
					}
					else
					{
						newWindow5.close();
						newWindow5 = window.open("frm_FLSA_LC_monitoring.jsp?cont_type=S&fgsa_no="+fgsa_no+"&fgsa_rev_no="+rev_no+"&customer_cd="+bscode+"&customer_nm="+customer_nm+"&customer_abbr="+customer_abbr+"&sn_no="+SN_CD+"&sn_rev_no="+SN_REV_NO+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"SN_LC_DTL","top=10,left=10,width=900,height=500,scrollbars=1,status=1");
					}
           		}
      		}
    	}
	}
}


function checkBoxValidation(obj)
{
	//obj.checked==true
	var msg="";
	if(obj.name=="buyer_nom")
	{
		msg="Buyer Nomination";
	}
	else if(obj.name=="seller_nom")
	{
		msg="Seller Nomination";
	}
	else if(obj.name=="day_def")
	{
		msg="Days Definition";
	}
	else if(obj.name=="chk_buyer_nom")
	{
		msg="Daily Buyer Nomination";
	}
	else if(obj.name=="chk_seller_nom")
	{
		msg="Daily Seller Nomination";
	}
	else
	{
		msg = obj.name;
	}
	
	if(obj.checked==false)
	{
		alert("You can not Uncheck "+msg);
		obj.checked = true;
	}
}


function checkData(submitFlag)
{
	var message = "Please Check the Following field(s).\n\n";
	var flag=true;
    document.forms[0].submitFlag.value=submitFlag;
    var agrtyp = document.forms[0].agrtyp.value;
    
	if(document.forms[0].flg.value=='update')
	{
		if(trim(document.forms[0].sn_no.value)=="")
		{
			message += "Please First Select SN For the List..\n";
		  	flag = false;
		}
		
		if(trim(document.forms[0].sign_dt.value)=="")
		{	
			message += "Please Enter The Signing Date...\n";
			flag=false;
		}
		if(trim(document.forms[0].dda_dt.value)=="") 
		{	
			message += "Please Enter The DDA Date...\n";
			flag=false;
		}
		if(trim(document.forms[0].dda_time.value)=="")
		{	
			message += "Please Enter The DDA Time...\n";
			flag=false;
		}
		if(document.forms[0].sign_dt.value!=null && trim(document.forms[0].sign_dt.value)!="")
		{
		   	fgsadt = document.forms[0].fgsa_sign_dt.value;
		   	signdt = document.forms[0].sign_dt.value;
		   	value = compareDate(signdt,fgsadt);
		 //   alert(value);
		   	if(value=="2")
		   	{
		   		message += "SN Signing Date must be greater equal to FLSA Signing Date..\nFLSA Signing date is "+fgsadt+"..\n";
		     	flag = false;
		   	}
		}
        
        if(trim(document.forms[0].salesPrice.value)=="") 		
        { 
        	message+="Please Insert Sales Gas Price..\n";
        	flag = false;
        }
        
        if(trim(document.forms[0].tcq.value)=="") 		
        { 
        	message+="Please Insert TCQ for the SN..\n";
         	flag = false;
        }
        
        if(trim(document.forms[0].dcq.value)==""  && document.forms[0].dcq_var.value=='N') 		
        { 
        	message+="Please Insert DCQ for the SN..\n";
        	flag = false;
        }
        
	   	if(trim(document.forms[0].st_dt.value)!="" && trim(document.forms[0].end_dt.value)!="")
	   	{
	    	stdt = document.forms[0].st_dt.value;
	      	enddt = document.forms[0].end_dt.value;
	      	fgstdt = document.forms[0].fgsa_st_dt.value;
	      	fgenddt = document.forms[0].fgsa_end_dt.value;
	     	var val = compareDate(enddt,stdt);
	      	
	      	if(val=="1" || val=="0")
	      	{
	        	val1 = compareDate(stdt,fgstdt);
	         	val2 = compareDate(stdt,fgenddt);
	         	
	         	if(val1=="2")
	         	{
	           		message+="Start date must be in the range of FLSA Start and End date..\nFLSA Period ("+fgstdt+" - "+fgenddt+")..\n";
	           		flag = false;
	         	}
	         	
	         	val1 = compareDate(enddt,fgenddt);
	         	if(val1=="1")
	         	{
	           		message+="End date must be less or equal to FLSA End date..\nFLSA Period ("+fgstdt+" - "+fgenddt+")..\n";
	           		flag = false;
	         	} 
	      	}
	      	else
	      	{
	        	message+="Start date must be Less than or equal to end date..\nFLSA Period ("+fgstdt+" - "+fgenddt+")..\n";
	        	flag = false;
	      	}
	   	}
	   	else
	   	{
	    	message += "Please Insert Start and End Date of SN..\n";
	     	flag = false; 
	   	}
	 
		var chkFlg=false;
		
		if(document.forms[0].chk_trans != null && document.forms[0].chk_trans.length==undefined)
		{
			if(document.forms[0].chk_trans.checked==true)
			{
				chkFlg=true;	
			}
		}
		else if(document.forms[0].chk_trans != null && document.forms[0].chk_trans.length!=undefined)
		{	
			for(var i=0;i<document.forms[0].chk_trans.length;i++)
			{
				if(document.forms[0].chk_trans[i].checked==true)
				{
					chkFlg=true;	
				}
			}
		}
		if(chkFlg==false)
		{
			message += "Please Select Atleast One Delivery Point - SEIPL ...\n";
			flag = false;
		}
		var plant_wise_charge_flag = true;
		
		if(document.forms[0].chk_delv!=null && document.forms[0].chk_delv.length==undefined)
		{
			chkFlg = false;
			if(document.forms[0].chk_delv.checked==true)
			{
				var lumpsumFlg = document.getElementById('lumpsumFlg0').value;
				chkFlg = true;
				if(agrtyp  == 'D'){
					if(parseFloat(document.getElementById('inr_mmbtu0').value) > 0 || parseFloat(document.getElementById('inr_km0').value) > 0  || lumpsumFlg == 'Y'){
						plant_wise_charge_flag = true;
					}else{
						plant_wise_charge_flag = false;
					}
				}
			}
		}
		else if(document.forms[0].chk_delv!=null && document.forms[0].chk_delv.length!=undefined)
		{
			chkFlg = false;
			for(var i=0;i<document.forms[0].chk_delv.length;i++)
			{
				if(document.forms[0].chk_delv[i].checked==true)
				{
					chkFlg = true;
					var lumpsumFlg = document.getElementById('lumpsumFlg'+i).value;
					
					if(agrtyp  == 'D'){
						if(parseFloat(document.getElementById('inr_mmbtu'+i).value) > 0 || parseFloat(document.getElementById('inr_km'+i).value) > 0 || lumpsumFlg == 'Y'){
							plant_wise_charge_flag = true;
						}else{
							plant_wise_charge_flag = false;
							break;
						}
					}
				}
			}
		}
		if(chkFlg==false)
		{
			message += "Please Select Atleast One Plant ...\n";
			flag=false;
		}
		
		if(plant_wise_charge_flag == false){
			message += "Please Enter Valid Plant Wise Transportation Charge in INR/MMBTU or INR/KM ...\n";
			flag=false;
		}
		
		if(document.forms[0].chk_trans_truck!=null && document.forms[0].chk_trans_truck.length==undefined)
		{
			chkFlg = false;
			if(document.forms[0].chk_trans_truck.checked==true)
			{
				chkFlg = true;	
			}
		}
		else if(document.forms[0].chk_trans_truck!=null && document.forms[0].chk_trans_truck.length!=undefined)
		{
			chkFlg = false;
			for(var i=0;i<document.forms[0].chk_trans_truck.length;i++)
			{
				if(document.forms[0].chk_trans_truck[i].checked==true)
				{
					chkFlg = true;	
				}
			}
		}
		
		if(chkFlg==false)
		{
			message += "Please Select Atleast One Transporter ...\n";
			flag=false;
		}	
// 		alert(document.forms[0].snBase.length)
		if(document.forms[0].snBase!=null && document.forms[0].snBase.length!=undefined)
		{
			chkFlg = false;
			for(var i=0;i<document.forms[0].snBase.length;i++)
			{
				if(document.forms[0].snBase[i].checked==true)
				{
					chkFlg = true;	
				}
			}
		}
		
		if(chkFlg==false)
		{
			message += "Please Select Atleast One Agreement Base ...\n";
			flag=false;
		}
		
		var buyer_nom = document.forms[0].buyer_nom;
		var chk_buyer_nom = document.forms[0].chk_buyer_nom;
		var byFlag  = false;
		
		if(buyer_nom.checked)
		{
			if(chk_buyer_nom!=null && chk_buyer_nom.length!=undefined) 
		    {
		    	for(var i=0;i<chk_buyer_nom.length;i++)
		       	{
		        	if(chk_buyer_nom[i].checked)
		         	{
		           		byFlag = true;
		         	}
		       	}
		   	}
		   	else
		   	{
		    	if(chk_buyer_nom.checked)
		        {
		        	byFlag = true;
		        }
		    }
		      
		    if(byFlag==false)
		    {
		    	message += "Please Select At least One Buyer Nomination Category...\n";
				flag=false;	      
		   	}
		}
		else
		{
            message += "Please Select Buyers Nomination ...\n";
			flag=false;	      
		}		
		    
		var seller_nom = document.forms[0].seller_nom;
		var chk_seller_nom = document.forms[0].chk_seller_nom;
		var slFlag  = false;
		
		if(seller_nom.checked)
		{
			if(chk_seller_nom!=null && chk_seller_nom.length!=undefined) 
		    {
		    	for(var i=0;i<chk_seller_nom.length;i++)
		       	{
		        	if(chk_seller_nom[i].checked)
		         	{
		           		slFlag = true;
		         	}
		       	}
		   	}
		   	else
		   	{
		    	if(chk_seller_nom.checked)
		        {
		        	slFlag = true;
		        }
		  	}
		      
		    if(slFlag==false)
		    {
		    	message += "Please Select At least One Seller Nomination Category...\n";
				flag=false;	      
		   	}
		}
		else
		{
            message += "Please Select Seller Nomination ...\n";
			flag=false;	      
		}		
		    
				 
		//////////////////MDCQ
		if(document.forms[0].mdcq!=null)
		{
			if(document.forms[0].mdcq.checked==false)
			{
				document.forms[0].mdcq_percent.value=""
			}
			
			if(document.forms[0].mdcq.checked==true && document.forms[0].mdcq_percent.value=="")
			{
				message += "Please Enter MDCQ Percentage...\n";
				flag=false;
			}
		}
			
		//Measurement
		if(document.forms[0].measurement!=null)
		{
			if(document.forms[0].measurement.checked==false)
			{
				document.forms[0].standard.value="";
				document.forms[0].temprature.value="";
				document.forms[0].rate_min_bar.value="";
				document.forms[0].rate_max_bar.value="";
			}
			
			if(document.forms[0].measurement.checked==true && (document.forms[0].standard.value=="" || document.forms[0].temprature.value=="" || document.forms[0].rate_min_bar.value=="" || document.forms[0].rate_max_bar.value==""))
			{
				message += "Please Enter Measurement's Standard,Temprature,Rate Min Bar and Rate Max Bar ...\n";
				flag=false;
			}
			else
			{
				if(document.forms[0].rate_min_bar.value!=null && document.forms[0].rate_max_bar.value!=null && document.forms[0].rate_min_bar.value!="" && document.forms[0].rate_max_bar.value!="" && document.forms[0].rate_min_bar.value!=" " && document.forms[0].rate_max_bar.value!=" ")
				{
					if(parseFloat(document.forms[0].rate_min_bar.value)>=parseFloat(document.forms[0].rate_max_bar.value))
					{
						message += "Rate Max Bar must be Greater than Rate Min Bar...\n";
						document.forms[0].rate_max_bar.focus();						
						flag=false;
					}	
				}
			}
		}
		 
			
		///////Off Spec
		if(document.forms[0].off_spec_gas_chk!=null)
		{
			if(document.forms[0].off_spec_gas_chk.checked==false)
			{
				document.forms[0].min_energy.value="";
				document.forms[0].max_energy.value="";
				document.forms[0].energy_off_spec.value="0";
			}
			
			if(document.forms[0].off_spec_gas_chk.checked==true && (document.forms[0].energy_off_spec.value=="0" || document.forms[0].max_energy.value=="" || document.forms[0].min_energy.value==""))
			{
				message += "Please Select Energy Base and Minimum and Maximum Value for Off Spec Gas...\n";
				flag=false;
			}
			else
			{
				if(document.forms[0].min_energy.value!=null && document.forms[0].max_energy.value!=null && document.forms[0].min_energy.value!="" && document.forms[0].max_energy.value!="" && document.forms[0].min_energy.value!=" " && document.forms[0].max_energy.value!=" ")
				{
					if(parseFloat(document.forms[0].min_energy.value)>=parseFloat(document.forms[0].max_energy.value))
					{
						message += "Maximum Value Of Off Spec Gas Must Be Greater Than Minimum Value ...\n";
						document.forms[0].max_energy.focus();						
						flag=false;
					}	
				}
			}
		}
		
		if(document.forms[0].securityFlag.value == 'N' && trim(document.forms[0].dcqFlag.value)=='' && trim(document.forms[0].startDtFlag.value)=='' && trim(document.forms[0].endDtFlag.value)=='')
		{
			message += "Please Enter Security!! \n";
			flag=false;
		}
		
		////ADVANCE ADJUSTMENT
		if(document.forms[0].advance!=undefined)
		{
			if(document.forms[0].advance.checked)
			{
				if(document.forms[0].advance_amount.value=='' || document.forms[0].advance_amount.value==' ')
				{
					message += "Enter Advance amount For Adjustment...\n";
					document.forms[0].advance_amount.focus();
					flag=false;
				}
			}
		}
		if(document.forms[0].discount!=undefined)
		{
			if(document.forms[0].discount.checked)
			{
				if(document.forms[0].discount_amount.value=='' || document.forms[0].discount_amount.value==' ')
				{
					message += "Enter Discount amount...\n";
					document.forms[0].discount_amount.focus();
					flag=false;
				}
			}
		}
		if(document.forms[0].tariff_inr!=undefined)
		{
			if(document.forms[0].tariff_inr.checked)
			{
				if(document.forms[0].tariff_inr_amount.value=='' || document.forms[0].tariff_inr_amount.value==' ')
				{
					message += "Enter Tariff amount...\n";
					document.forms[0].tariff_inr_amount.focus();
					flag=false;
				}
			}
		}
		
		if(document.forms[0].advance_collection.checked){
			document.forms[0].advAmtFlg.value="Y";	
			if(document.forms[0].adv_cur_flg.value == "0"){
				
				message += "Please Select Advance Amount Currency !!\n";
				document.forms[0].adv_cur_flg.focus();
				flag=false;
			}
		}else{
			document.forms[0].advAmtFlg.value="N";
		}
		if(agrtyp == 'D'){
			
			var trans_cont_st_dt = document.forms[0].trans_cont_st_dt.value;
			var trans_cont_end_dt = document.forms[0].trans_cont_end_dt.value;
			var trans_cont_sing_dt = document.forms[0].trans_cont_sing_dt.value;
			var trans_cont_no = document.forms[0].trans_cont_no.value;
			
			if(trans_cont_st_dt!='' && trans_cont_end_dt!='' && trans_cont_no!='' && trans_cont_sing_dt!=''){
				
			}else{
				
				message += "Please Enter All The Mandatory Details of The Transporter Service";
				flag=false;
			}
		}
		
		if(!flag)
		{
			alert(message);
			return flag;
		}
		else
		{
			var a;
			var msg="";
						
			if(document.forms[0].flg.value == 'update')
			{
		    	if(submitFlag=="1")
		    	{
		       		msg = "Do You Want To Submit Details ???";
			   	}
			   	else
			   	{
			    	msg = "Do You Want To Send SN for Approval ???";
			   	}
			   	
			   	if(document.forms[0].rev_chk.checked)
			   	{
			   		document.forms[0].rev_flag.value = "Y";
			   		document.forms[0].submitFlag.value="2";
			   		msg = "Do You Want To Revise The SN Details, With New Revision NO For Same SN NO ???";
			   	}
			   	else
			   	{
			   		document.forms[0].rev_flag.value = "N";
			   		document.forms[0].submitFlag.value="1";
			   		msg = "Do You Want To Modify The SN Details ???";
			   	}
			   	
			   	a=confirm(msg);
 
				if(a)
				{
					if(document.forms[0].billFlag.value=='false')
						alert("After Submitting SN, Please Submit Billing Details!!");
				//	document.forms[0].option.value="SNMaster";
					document.forms[0].submit();
				}
	 		}
  		}
	}    
}                


function submitTCQModificationRequest()
{
	var message = "Please Check the Following field(s).\n\n";
	var flag=true;
    document.forms[0].submitFlag.value="2";
	
	if(document.forms[0].flg.value=='update')
	{
		if(trim(document.forms[0].sn_no.value)=="")
		{
			message += "Please First Select SN For the List..\n";
		  	flag = false;
		}
		
		if(trim(document.forms[0].tcq_sign.value)=="")
		{
			message += "Please First Select TCQ Sign ('+'/'-') ..\n";
		  	flag = false;
		}
		
		if(trim(document.forms[0].var_tcq.value)=="" || trim(document.forms[0].var_tcq.value)=="0")
		{
			message += "Please Variable TCQ Cannot be Zero or Blank ..\n";
		  	flag = false;
		}
		
		if(trim(document.forms[0].sign_dt.value)=="")
		{	
			message += "Please Enter The Signing Date...\n";
			flag=false;
		}
		if(trim(document.forms[0].dda_dt.value)=="")
		{	
			message += "Please Enter The DDA Date...\n";
			flag=false;
		}
		if(trim(document.forms[0].dda_time.value)=="")
		{	
			message += "Please Enter The DDA Time...\n";
			flag=false;
		}
		if(document.forms[0].sign_dt.value!=null && trim(document.forms[0].sign_dt.value)!="")
		{
		   	fgsadt = document.forms[0].fgsa_sign_dt.value;
		   	signdt = document.forms[0].sign_dt.value;
		   	value = compareDate(signdt,fgsadt);
		    
		   	if(value=="2")
		   	{
		   		message += "SN Signing Date must be greater equal to FLSA Signing Date..\nFLSA Signing date is "+fgsadt+"..\n";
		     	flag = false;
		   	}
		}
        
        if(trim(document.forms[0].salesPrice.value)=="") 		
        { 
        	message+="Please Insert Sales Gas Price..\n";
        	flag = false;
        }
        
        if(trim(document.forms[0].tcq.value)=="") 		
        { 
        	message+="Please Insert TCQ for the SN..\n";
         	flag = false;
        }
        
        if(trim(document.forms[0].dcq.value)==""  && document.forms[0].dcq_var.value=='N') 		
        { 
        	message+="Please Insert DCQ for the SN..\n";
        	flag = false;
        }
        
	   	if(trim(document.forms[0].st_dt.value)!="" && trim(document.forms[0].end_dt.value)!="")
	   	{
	    	stdt = document.forms[0].st_dt.value;
	      	enddt = document.forms[0].end_dt.value;
	      	fgstdt = document.forms[0].fgsa_st_dt.value;
	      	fgenddt = document.forms[0].fgsa_end_dt.value;
	     	var val = compareDate(enddt,stdt);
	      	
	      	if(val=="1" || val=="0")
	      	{
	        	val1 = compareDate(stdt,fgstdt);
	         	val2 = compareDate(stdt,fgenddt);
	         	
	         	if(val1=="2")
	         	{
	           		message+="Start date must be in the range of FLSA Start and End date..\nFLSA Period ("+fgstdt+" - "+fgenddt+")..\n";
	           		flag = false;
	         	}
	         	
	         	val1 = compareDate(enddt,fgenddt);
	         	if(val1=="1")
	         	{
	           		message+="End date must be less or equal to FLSA End date..\nFLSA Period ("+fgstdt+" - "+fgenddt+")..\n";
	           		flag = false;
	         	} 
	      	}
	      	else
	      	{
	        	message+="Start date must greater equal to end date..\nFLSA Period ("+fgstdt+" - "+fgenddt+")..\n";
	        	flag = false;
	      	}
	   	}
	   	else
	   	{
	    	message += "Please Insert Start and End Date of SN..\n";
	     	flag = false; 
	   	}
	 
		var chkFlg=false;
		
		if(document.forms[0].chk_trans != null && document.forms[0].chk_trans.length==undefined)
		{
			if(document.forms[0].chk_trans.checked==true)
			{
				chkFlg=true;	
			}
		}
		else if(document.forms[0].chk_trans != null && document.forms[0].chk_trans.length!=undefined)
		{	
			for(var i=0;i<document.forms[0].chk_trans.length;i++)
			{
				if(document.forms[0].chk_trans[i].checked==true)
				{
					chkFlg=true;	
				}
			}
		}
			
		if(chkFlg==false)
		{
			message += "Please Select Atleast One Filling Station ...\n";
			flag = false;
		}
			
		if(document.forms[0].chk_delv!=null && document.forms[0].chk_delv==undefined)
		{
			chkFlg = false;
			if(document.forms[0].chk_delv.checked==true)
			{
				chkFlg = true;	
			}
		}
		else if(document.forms[0].chk_delv!=null && document.forms[0].chk_delv!=undefined)
		{
			for(var i=0;i<document.forms[0].chk_delv.length;i++)
			{
				if(document.forms[0].chk_delv[i].checked==true)
				{
					chkFlg = true;	
				}
			}
		}
		
		if(chkFlg==false)
		{
			message += "Please Select Atleast One Plant ...\n";
			flag=false;
		}
				
		var buyer_nom = document.forms[0].buyer_nom;
		var chk_buyer_nom = document.forms[0].chk_buyer_nom;
		var byFlag  = false;
		
		if(buyer_nom.checked)
		{
			if(chk_buyer_nom!=null && chk_buyer_nom.length!=undefined) 
		    {
		    	for(var i=0;i<chk_buyer_nom.length;i++)
		       	{
		        	if(chk_buyer_nom[i].checked)
		         	{
		           		byFlag = true;
		         	}
		       	}
		   	}
		   	else
		   	{
		    	if(chk_buyer_nom.checked)
		        {
		        	byFlag = true;
		        }
		    }
		      
		    if(byFlag==false)
		    {
		    	message += "Please Select At least One Buyer Nomination Category...\n";
				flag=false;	      
		   	}
		}
		else
		{
            message += "Please Select Buyers Nomination ...\n";
			flag=false;	      
		}		
		    
		var seller_nom = document.forms[0].seller_nom;
		var chk_seller_nom = document.forms[0].chk_seller_nom;
		var slFlag  = false;
		
		if(seller_nom.checked)
		{
			if(chk_seller_nom!=null && chk_seller_nom.length!=undefined) 
		    {
		    	for(var i=0;i<chk_seller_nom.length;i++)
		       	{
		        	if(chk_seller_nom[i].checked)
		         	{
		           		slFlag = true;
		         	}
		       	}
		   	}
		   	else
		   	{
		    	if(chk_seller_nom.checked)
		        {
		        	slFlag = true;
		        }
		  	}
		      
		    if(slFlag==false)
		    {
		    	message += "Please Select At least One Seller Nomination Category...\n";
				flag=false;	      
		   	}
		}
		else
		{
            message += "Please Select Seller Nomination ...\n";
			flag=false;	      
		}		
		    
				 
		//////////////////MDCQ
		if(document.forms[0].mdcq!=null)
		{
			if(document.forms[0].mdcq.checked==false)
			{
				document.forms[0].mdcq_percent.value=""
			}
			
			if(document.forms[0].mdcq.checked==true && document.forms[0].mdcq_percent.value=="")
			{
				message += "Please Enter MDCQ Percentage...\n";
				flag=false;
			}
		}
			
		//Measurement
		if(document.forms[0].measurement!=null)
		{
			if(document.forms[0].measurement.checked==false)
			{
				document.forms[0].standard.value="";
				document.forms[0].temprature.value="";
				document.forms[0].rate_min_bar.value="";
				document.forms[0].rate_max_bar.value="";
			}
			
			if(document.forms[0].measurement.checked==true && (document.forms[0].standard.value=="" || document.forms[0].temprature.value=="" || document.forms[0].rate_min_bar.value=="" || document.forms[0].rate_max_bar.value==""))
			{
				message += "Please Enter Measurement's Standard,Temprature,Rate Min Bar and Rate Max Bar ...\n";
				flag=false;
			}
			else
			{
				if(document.forms[0].rate_min_bar.value!=null && document.forms[0].rate_max_bar.value!=null && document.forms[0].rate_min_bar.value!="" && document.forms[0].rate_max_bar.value!="" && document.forms[0].rate_min_bar.value!=" " && document.forms[0].rate_max_bar.value!=" ")
				{
					if(parseFloat(document.forms[0].rate_min_bar.value)>=parseFloat(document.forms[0].rate_max_bar.value))
					{
						message += "Rate Max Bar must be Greater than Rate Min Bar...\n";
						document.forms[0].rate_max_bar.focus();						
						flag=false;
					}	
				}
			}
		}
		 
			
		///////Off Spec
		if(document.forms[0].off_spec_gas_chk!=null)
		{
			if(document.forms[0].off_spec_gas_chk.checked==false)
			{
				document.forms[0].min_energy.value="";
				document.forms[0].max_energy.value="";
				document.forms[0].energy_off_spec.value="0";
			}
			
			if(document.forms[0].off_spec_gas_chk.checked==true && (document.forms[0].energy_off_spec.value=="0" || document.forms[0].max_energy.value=="" || document.forms[0].min_energy.value==""))
			{
				message += "Please Select Energy Base and Minimum and Maximum Value for Off Spec Gas...\n";
				flag=false;
			}
			else
			{
				if(document.forms[0].min_energy.value!=null && document.forms[0].max_energy.value!=null && document.forms[0].min_energy.value!="" && document.forms[0].max_energy.value!="" && document.forms[0].min_energy.value!=" " && document.forms[0].max_energy.value!=" ")
				{
					if(parseFloat(document.forms[0].min_energy.value)>=parseFloat(document.forms[0].max_energy.value))
					{
						message += "Maximum Value Of Off Spec Gas Must Be Greater Than Minimum Value ...\n";
						document.forms[0].max_energy.focus();						
						flag=false;
					}	
				}
			}
		}
	
		////ADVANCE ADJUSTMENT
		if(document.forms[0].advance!=undefined)
		{
			if(document.forms[0].advance.checked)
			{
				if(document.forms[0].advance_amount.value=='' || document.forms[0].advance_amount.value==' ')
				{
					message += "Enter Advance amount For Adjustment...\n";
					document.forms[0].advance_amount.focus();
					flag=false;
				}
			}
		}
		if(document.forms[0].discount!=undefined)
		{
			if(document.forms[0].discount.checked)
			{
				if(document.forms[0].discount_amount.value=='' || document.forms[0].discount_amount.value==' ')
				{
					message += "Enter Discount amount...\n";
					document.forms[0].discount_amount.focus();
					flag=false;
				}
			}
		}
		if(document.forms[0].tariff_inr!=undefined)
		{
			if(document.forms[0].tariff_inr.checked)
			{
				if(document.forms[0].tariff_inr_amount.value=='' || document.forms[0].tariff_inr_amount.value==' ')
				{
					message += "Enter Tariff amount...\n";
					document.forms[0].tariff_inr_amount.focus();
					flag=false;
				}
			}
		}
		
		if(document.forms[0].Price_req_flag.value=="Y"){
			message += "Request Already Sent for Change Sales Gas Price, Please Approve first to further proceed!!";
			flag=false;
		}
	
		if(!flag)
		{
			alert(message);
			return flag;
		}
		else
		{
			var a;
			var msg="";
			
			if(document.forms[0].flg.value == 'update')
			{
		    	document.forms[0].rev_flag.value = "Y";
			   	document.forms[0].submitFlag.value="2";
			   	msg = "Do You Want To Revise The SN Details, With TCQ Modification Request ?";
			   				   	
			   	a=confirm(msg);
 
				if(a)
				{
					document.forms[0].option.value="Request_For_TCQ_Modification";
					document.forms[0].submit();
				}
	 		}
  		}
	}    
}



                  
function openSNList()
{
	var flg = document.forms[0].flg.value;
	var cust_nm = document.forms[0].cust_nm.value;	
	if(!newWindow5 || newWindow5.closed)
	{
		newWindow5 = window.open("frm_SN_list.jsp?flg="+flg+"&cust_nm="+cust_nm,"SN_LIST","top=10,left=10,width=900,height=600,scrollbars=1");
	}
	else
	{
		newWindow5.close();
		newWindow5 = window.open("frm_SN_list.jsp?flg="+flg+"&cust_nm="+cust_nm,"SN_LIST","top=10,left=10,width=900,height=600,scrollbars=1");
	}
}


function setFGSACode(cd,fgsa_cd,activity,fgsaRev,snNo,snRev)
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	location.replace("../contract_master/frm_SN_creation.jsp?activity="+activity+"&bscode="+cd+"&buyer_cd="+cd+"&FGSA_CD="+fgsa_cd+"&FGSA_REVNO="+fgsaRev+"&SN_CD="+snNo+"&SN_REVNO="+snRev+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl);
}


function MeasurementQreadOnly()
{
	if(document.forms[0].measurement.checked==false)
	{
		document.getElementById('std').readOnly=true;			
		document.getElementById('temp').readOnly=true;	
		document.getElementById('rate_max').readOnly=true;	
		document.getElementById('rate_min').readOnly=true;	
	}
	else
	{
		document.getElementById('std').readOnly=false;
		document.getElementById('temp').readOnly=false;	
		document.getElementById('rate_max').readOnly=false;	
		document.getElementById('rate_min').readOnly=false;	
	}
}


function MDCQreadOnly()
{
	if(document.forms[0].mdcq.checked==false)
	{
		document.getElementById('mdcqPer').readOnly=true;	
	}
	else
	{
		document.getElementById('mdcqPer').readOnly=false;
	}
}


function offSpecreadOnly()
{
	if(document.forms[0].off_spec_gas_chk.checked==false)
	{
		document.getElementById('off_spec_min_energy').readOnly=true;	
		document.getElementById('off_spec_max_energy').readOnly=true;	
	}
	else
	{
		document.getElementById('off_spec_min_energy').readOnly=false;
		document.getElementById('off_spec_max_energy').readOnly=false;			
	}
}

function checkDCQ(me)
{
	var tcq = document.forms[0].tcq.value;
  	if(trim(me.value)>parseFloat(tcq))
  	{
    	alert("You can not Declared DCQ more than TCQ");
    	document.getElementById("mtd").value="";me.value = "";
  	}
}

function setValues(snno,trans,clause,plant,CustTrans,variance,oblig,mdcq,specbase)
{
	if(snno!="")
	{
    	var chk_trans = document.forms[0].chk_trans;
    	var chk_delv = document.forms[0].chk_delv;
    	var clause_nm = document.forms[0].clause_nm;
       	var off_spec_gas_chk = document.forms[0].off_spec_gas_chk;
    	var mdcqb = document.forms[0].mdcq;
    	
    	var sepTrans = trans.split("@");
    	var sepPlant = plant.split("@");
    	var sepClause = clause.split("@");
    ///////////SB20200809///////////////////////
    	var chk_trans_truck = document.forms[0].chk_trans_truck; //SB
    	var sepCustTrans = CustTrans.split("@"); //SB
    	if(chk_trans_truck!=null && chk_trans_truck.length!=undefined)
    	{
     		for(var i=0;i<chk_trans_truck.length;i++)
     		{
       			for(var j=0;j<sepCustTrans.length;j++)
       			{
         			if(chk_trans_truck[i].value == sepCustTrans[j])
         			{
         				chk_trans_truck[i].checked = true;
         			}
       			} 
     		} 
    	}
    	else if(chk_trans_truck!=null)
    	{
      		for(var j=0;j<sepCustTrans.length;j++)
       		{
         		if(chk_trans_truck.value == sepCustTrans[j])
         		{
         			chk_trans_truck.checked = true;
         		}
       		} 
    	}
    /////^SB20200809///////////////////////
    	//Transporter (Grid) Selection ...
        if(chk_trans!=null && chk_trans.length!=undefined)
    	{
     		for(var i=0;i<chk_trans.length;i++)
     		{
       			for(var j=0;j<sepTrans.length;j++)
       			{
         			if(chk_trans[i].value == sepTrans[j])
         			{
           				chk_trans[i].checked = true;
         			}
       			} 
     		} 
    	}
    	else if(chk_trans!=null)
    	{
      		for(var j=0;j<sepTrans.length;j++)
       		{
         		if(chk_trans.value == sepTrans[j])
         		{
           			chk_trans.checked = true;
         		}
       		} 
    	}
    
    	//Plant (Delivery Point) Selection ...
    	if(chk_delv!=null && chk_delv.length!=undefined)
    	{
     		for(var i=0;i<chk_delv.length;i++)
     		{
       			for(var j=0;j<sepPlant.length;j++)
       			{
         			if(chk_delv[i].value == sepPlant[j])
         			{
           				chk_delv[i].checked = true;
           				document.forms[0].chk_flg[i].value="Y";
         			}
       			} 
     		} 
    	}
    	else if(chk_delv!=null)
    	{
      		for(var j=0;j<sepPlant.length;j++)
       		{
         		if(chk_delv.value == sepPlant[j])
         		{
           			chk_delv.checked = true;
           			document.forms[0].chk_flg.value="Y";
         		}
       		} 
    	}
    	   
    	//Clauses Selection ...
    	if(clause_nm!=null && clause_nm.length!=undefined)
    	{
     		for(var i=0;i<clause_nm.length;i++)
     		{
       			for(var j=0;j<sepClause.length;j++)
       			{
         			if(clause_nm[i].value == sepClause[j])
         			{
           				clause_nm[i].checked = true;
         			}
       			}	 
     		} 
    	}
    	else if(clause_nm!=null)
    	{
      		for(var j=0;j<sepClause.length;j++)
       		{
         		if(clause_nm.value == sepClause[j])
         		{
           			clause_nm.checked = true;
         		}
       		} 
    	}
     
   		if(off_spec_gas_chk!=null && off_spec_gas_chk.checked == true)
    	{
      		document.forms[0].energy_off_spec.value = trim(specbase);
    	}
    
    	if(mdcqb!=null && mdcqb.checked == true)
    	{
      		document.forms[0].mdcq_qty_unit.value = mdcq;
    	}  
    	
  	}
	
}

var varDCQWindow;
function defineVariableDCQforSN(buyer_cd,fgsa_cd,fgsa_revno,sn_cd,sn_revno,start_dt,end_dt,buyer_name)
{
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	start_dt = document.forms[0].st_dt.value;
	end_dt = document.forms[0].end_dt.value;
	
	if(!varDCQWindow || varDCQWindow.closed)
	{
		varDCQWindow = window.open("../contract_master/frm_sn_var_dcq.jsp?buyer_cd="+buyer_cd+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&buyer_name="+buyer_name+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"SN_DCQ","top=10,left=10,width=900,height=600,scrollbars=1");
	}
	else
	{
		varDCQWindow.close();
		varDCQWindow = window.open("../contract_master/frm_sn_var_dcq.jsp?buyer_cd="+buyer_cd+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&buyer_name="+buyer_name+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"SN_DCQ","top=10,left=10,width=900,height=600,scrollbars=1");
	}
}

function refreshPageFromChild(flg,activity,bscode,buyer_cd,FGSA_CD,FGSA_REVNO,SN_CD,SN_REVNO)
{
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	location.replace("frm_SN_master.jsp?flg="+flg+"&activity="+activity+"&bscode="+bscode+"&buyer_cd="+buyer_cd+"&FGSA_CD="+FGSA_CD+"&FGSA_REVNO="+FGSA_REVNO+"&SN_CD="+SN_CD+"&SN_REVNO="+SN_REVNO+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);
}

function checkSNClosure()
{
		if(document.forms[0].sn_close_chk.checked)
		{
			document.forms[0].var_tcq.disabled = false;
			document.forms[0].tcq_sign.disabled = false;
							
			document.forms[0].sn_close_flag.value = "Y";
			
			var allocated_qty = document.forms[0].allocated_qty.value;
			var tcq = document.forms[0].tcq.value;
			var alloc_qty = parseFloat("0");
			var tcq_qty = parseFloat("0");
			
			if(tcq!=null && trim(tcq)!='')
			{
				if(allocated_qty==null || trim(allocated_qty)=='')
				{
					allocated_qty='0';
				}
				alloc_qty = parseFloat(""+trim(allocated_qty));
				tcq_qty = parseFloat(""+trim(tcq));
				
				var var_tcq = round(tcq_qty,2) - round(alloc_qty,2);
				
				var_tcq = round(var_tcq,2);
				
				if(var_tcq<0)
				{
					var_tcq = -1*var_tcq;
					document.forms[0].tcq_sign.value = "+";
					document.forms[0].var_tcq.value = ""+var_tcq;
					document.forms[0].modify_tcq.disabled = true;
					document.forms[0].var_tcq.disabled = true;
					document.forms[0].tcq_sign.disabled = true;
				}
				else if(var_tcq>0)
				{
					document.forms[0].tcq_sign.value = "-";
					document.forms[0].var_tcq.value = ""+var_tcq;
					document.forms[0].modify_tcq.disabled = true;
					document.forms[0].var_tcq.disabled = true;
					document.forms[0].tcq_sign.disabled = true;
				}
				else
				{
					document.forms[0].sn_close_flag.value = "N";
					document.forms[0].tcq_sign.value = "+";
					document.forms[0].var_tcq.value = "";
					document.forms[0].sn_close_chk.checked = false;
					document.forms[0].modify_tcq.disabled = false;
					document.forms[0].var_tcq.disabled = false;
					document.forms[0].tcq_sign.disabled = false;
				}
			}
		}
		else
		{
			document.forms[0].sn_close_flag.value = "N";
			document.forms[0].tcq_sign.value = "+";
			document.forms[0].var_tcq.value = "";
			document.forms[0].sn_close_chk.checked = false;
			document.forms[0].modify_tcq.disabled = false;
			document.forms[0].var_tcq.disabled = false;
			document.forms[0].tcq_sign.disabled = false;
		}
}

function checkMdcq()
{
	var mdcqper=document.forms[0].mdcqPer.value;
	if(mdcqper!=null && trim(mdcqper)!='' )
	{
	    var mdcq_per = parseFloat(mdcqper);
		if(mdcq_per < 100)
		{
			alert("MDCQ percentage cannot be less than 100 percent !!! ");
			document.forms[0].mdcqPer.value='';
		}
		else
		{
			document.forms[0].mdcqPer.value=mdcqper;
		}
	}
}


function submitSNClosureRequest()
{
	
	var message = "Please Check the Following field(s).\n\n";
	var flag=true;
    document.forms[0].submitFlag.value="2";
	if(document.forms[0].flg.value=='update')
	{
		if(trim(document.forms[0].sn_no.value)=="")
		{
			
			message += "Please First Select SN For the List..\n";
		  	flag = false;
		}
		
		if(trim(document.forms[0].tcq_sign.value)=="")
		{
			
			message += "Please First Select TCQ Sign ('+'/'-') ..\n";
		  	flag = false;
		}
		
		if(trim(document.forms[0].var_tcq.value)=="" || trim(document.forms[0].var_tcq.value)=="0")
		{
			message += "Please Variable TCQ Cannot be Zero or Blank ..\n";
		  	flag = false;
		}
		
		if(trim(document.forms[0].sign_dt.value)=="")
		{	
			message += "Please Enter The Signing Date...\n";
			flag=false;
		}
		if(trim(document.forms[0].dda_dt.value)=="")
		{	
			message += "Please Enter The DDA Date...\n";
			flag=false;
		}
		if(trim(document.forms[0].dda_time.value)=="")
		{	
			message += "Please Enter The DDA Time...\n";
			flag=false;
		}
		if(document.forms[0].sign_dt.value!=null && trim(document.forms[0].sign_dt.value)!="")
		{
		   	fgsadt = document.forms[0].fgsa_sign_dt.value;
		   	signdt = document.forms[0].sign_dt.value;
		   	value = compareDate(signdt,fgsadt);
		   	if(value=="2")
		   	{
		   	
		   		message += "SN Signing Date must be greater equal to FLSA Signing Date..\nFLSA Signing date is "+fgsadt+"..\n";
		     	flag = false;
		   	}
		}
        if(trim(document.forms[0].salesPrice.value)=="") 		
        { 
        
        	message+="Please Insert Sales Gas Price..\n";
        	flag = false;
        }
        if(trim(document.forms[0].sn_closure_dt.value)=="") 		
        { 
       
        	message+="Please Insert Effective Date For SN Closure..\n";
        	flag = false;
        }
        if(trim(document.forms[0].tcq.value)=="") 		
        { 
       
        	message+="Please Insert TCQ for the SN..\n";
         	flag = false;
        }
        if(trim(document.forms[0].dcq.value)==""  && document.forms[0].dcq_var.value=='N') 		
        { 
        	message+="Please Insert DCQ for the SN..\n";
        	flag = false;
        }
	   	if(trim(document.forms[0].st_dt.value)!="" && trim(document.forms[0].end_dt.value)!="")
	   	{
	    	stdt = document.forms[0].st_dt.value;
	      	enddt = document.forms[0].end_dt.value;
	      	fgstdt = document.forms[0].fgsa_st_dt.value;
	      	fgenddt = document.forms[0].fgsa_end_dt.value;
	     	var val = compareDate(enddt,stdt);
	      	
	      	if(val=="1" || val=="0")
	      	{
	        	val1 = compareDate(stdt,fgstdt);
	         	val2 = compareDate(stdt,fgenddt);
	         	
	         	if(val1=="2")
	         	{
	           		message+="Start date must be in the range of FLSA Start and End date..\nFLSA Period ("+fgstdt+" - "+fgenddt+")..\n";
	           		flag = false;
	         	}
	         	val1 = compareDate(enddt,fgenddt);
	         	if(val1=="1")
	         	{
	           		message+="End date must be less or equal to FLSA End date..\nFLSA Period ("+fgstdt+" - "+fgenddt+")..\n";
	           		flag = false;
	         	} 
	      	}
	      	else
	      	{
	        	message+="Start date must greater equal to end date..\nFLSA Period ("+fgstdt+" - "+fgenddt+")..\n";
	        	flag = false;
	      	}
	   	}
	   	else
	   	{
	    	message += "Please Insert Start and End Date of SN..\n";
	     	flag = false; 
	   	}
	    ///////////SB20200809///////////////////////
    
		var chkFlg=false;
		
		if(document.forms[0].chk_trans_truck != null && document.forms[0].chk_trans_truck.length==undefined)
		{
			if(document.forms[0].chk_trans_truck.checked==true)
			{	
				chkFlg=true;	
			}
		}
		
		else if(document.forms[0].chk_trans_truck != null && document.forms[0].chk_trans_truck.length!=undefined)
		{	
			for(var i=0;i<document.forms[0].chk_trans_truck.length;i++)
			{
				if(document.forms[0].chk_trans_truck[i].checked==true)
				{
					chkFlg=true;	
				}
			}
		}
    /////^SB20200809///////////////////////
		var chkFlg=false;
		
		if(document.forms[0].chk_trans != null && document.forms[0].chk_trans.length==undefined)
		{
			if(document.forms[0].chk_trans.checked==true)
			{	
				chkFlg=true;	
			}
		}
		
		else if(document.forms[0].chk_trans != null && document.forms[0].chk_trans.length!=undefined)
		{	
			for(var i=0;i<document.forms[0].chk_trans.length;i++)
			{
				if(document.forms[0].chk_trans[i].checked==true)
				{
					chkFlg=true;	
				}
			}
		}
		if(chkFlg==false)
		{
			message += "Please Select Atleast One Filling Station ...\n";
			flag = false;
		}
		
		if(document.forms[0].chk_delv!=null && document.forms[0].chk_delv==undefined)
		{
			chkFlg = false;
			if(document.forms[0].chk_delv.checked==true)
			{
				chkFlg = true;	
			}
		}
		
		else if(document.forms[0].chk_delv!=null && document.forms[0].chk_delv!=undefined)
		{
			for(var i=0;i<document.forms[0].chk_delv.length;i++)
			{
				if(document.forms[0].chk_delv[i].checked==true)
				{
					chkFlg = true;	
				}
			}
		}
		if(chkFlg==false)
		{
			message += "Please Select Atleast One Plant ...\n";
			flag=false;
		}
		
		var buyer_nom = document.forms[0].buyer_nom;
		var chk_buyer_nom = document.forms[0].chk_buyer_nom;
		var byFlag  = false;
		if(buyer_nom.checked)
		{
			if(chk_buyer_nom!=null && chk_buyer_nom.length!=undefined) 
		    {
		    	for(var i=0;i<chk_buyer_nom.length;i++)
		       	{
		        	if(chk_buyer_nom[i].checked)
		         	{
		           		byFlag = true;
		         	}
		       	}
		   	}
		   	else
		   	{
		    	if(chk_buyer_nom.checked)
		        {
		        	byFlag = true;
		        }
		    }
		      
		    if(byFlag==false)
		    {
		    	message += "Please Select At least One Buyer Nomination Category...\n";
				flag=false;	      
		   	}
		}
		else
		{
            message += "Please Select Buyers Nomination ...\n";
			flag=false;	      
		}		
		var seller_nom = document.forms[0].seller_nom;
		var chk_seller_nom = document.forms[0].chk_seller_nom;
		var slFlag  = false;
		if(seller_nom.checked)
		{
			if(chk_seller_nom!=null && chk_seller_nom.length!=undefined) 
		    {
		    	for(var i=0;i<chk_seller_nom.length;i++)
		       	{
		        	if(chk_seller_nom[i].checked)
		         	{
		           		slFlag = true;
		         	}
		       	}
		   	}
		   	else
		   	{
		    	if(chk_seller_nom.checked)
		        {
		        	slFlag = true;
		        }
		  	}
		      
		    if(slFlag==false)
		    {
		    	message += "Please Select At least One Seller Nomination Category...\n";
				flag=false;	      
		   	}
		}
		else
		{
            message += "Please Select Seller Nomination ...\n";
			flag=false;	      
		}		
		    
		
		//////////////////MDCQ
		if(document.forms[0].mdcq!=null)
		{
			if(document.forms[0].mdcq.checked==false)
			{
				document.forms[0].mdcq_percent.value=""
			}
			
			if(document.forms[0].mdcq.checked==true && document.forms[0].mdcq_percent.value=="")
			{
				message += "Please Enter MDCQ Percentage...\n";
				flag=false;
			}
		}
		
		//Measurement
		if(document.forms[0].measurement!=null)
		{
			if(document.forms[0].measurement.checked==false)
			{
				document.forms[0].standard.value="";
				document.forms[0].temprature.value="";
				document.forms[0].rate_min_bar.value="";
				document.forms[0].rate_max_bar.value="";
			}
			
			if(document.forms[0].measurement.checked==true && (document.forms[0].standard.value=="" || document.forms[0].temprature.value=="" || document.forms[0].rate_min_bar.value=="" || document.forms[0].rate_max_bar.value==""))
			{
				message += "Please Enter Measurement's Standard,Temprature,Rate Min Bar and Rate Max Bar ...\n";
				flag=false;
			}
			else
			{
				if(document.forms[0].rate_min_bar.value!=null && document.forms[0].rate_max_bar.value!=null && document.forms[0].rate_min_bar.value!="" && document.forms[0].rate_max_bar.value!="" && document.forms[0].rate_min_bar.value!=" " && document.forms[0].rate_max_bar.value!=" ")
				{
					if(parseFloat(document.forms[0].rate_min_bar.value)>=parseFloat(document.forms[0].rate_max_bar.value))
					{
						message += "Rate Max Bar must be Greater than Rate Min Bar...\n";
						document.forms[0].rate_max_bar.focus();						
						flag=false;
					}	
				}
			}
		}
		 
		
		///////Off Spec
		if(document.forms[0].off_spec_gas_chk!=null)
		{
			if(document.forms[0].off_spec_gas_chk.checked==false)
			{//alert("if"+i++);
				document.forms[0].min_energy.value="";
				document.forms[0].max_energy.value="";
				document.forms[0].energy_off_spec.value="0";
			}
			
			if(document.forms[0].off_spec_gas_chk.checked==true && (document.forms[0].energy_off_spec.value=="0" || document.forms[0].max_energy.value=="" || document.forms[0].min_energy.value==""))
			{
				message += "Please Select Energy Base and Minimum and Maximum Value for Off Spec Gas...\n";
				flag=false;
			}
			else
			{
				if(document.forms[0].min_energy.value!=null && document.forms[0].max_energy.value!=null && document.forms[0].min_energy.value!="" && document.forms[0].max_energy.value!="" && document.forms[0].min_energy.value!=" " && document.forms[0].max_energy.value!=" ")
				{
					if(parseFloat(document.forms[0].min_energy.value)>=parseFloat(document.forms[0].max_energy.value))
					{
						message += "Maximum Value Of Off Spec Gas Must Be Greater Than Minimum Value ...\n";
						document.forms[0].max_energy.focus();						
						flag=false;
					}	
				}
			}
		}
		
		////ADVANCE ADJUSTMENT
		if(document.forms[0].advance!=undefined)
		{
			if(document.forms[0].advance.checked)
			{
				if(document.forms[0].advance_amount.value=='' || document.forms[0].advance_amount.value==' ')
				{
					message += "Enter Advance amount For Adjustment...\n";
					document.forms[0].advance_amount.focus();
					flag=false;
				}
			}
		}
		if(document.forms[0].discount!=undefined)
		{
			if(document.forms[0].discount.checked)
			{
				if(document.forms[0].discount_amount.value=='' || document.forms[0].discount_amount.value==' ')
				{
					message += "Enter Discount amount...\n";
					document.forms[0].discount_amount.focus();
					flag=false;
				}
			}
		}
		if(document.forms[0].tariff_inr!=undefined)
		{
			if(document.forms[0].tariff_inr.checked)
			{
				if(document.forms[0].tariff_inr_amount.value=='' || document.forms[0].tariff_inr_amount.value==' ')
				{
					message += "Enter Tariff amount...\n";
					document.forms[0].tariff_inr_amount.focus();
					flag=false;
				}
			}
		}	
	
		if(!flag)
		{
			alert(message);
			return flag;
		}
		else
		{
			var a;
			var msg="";
			
			if(document.forms[0].flg.value == 'update')
			{
		    	document.forms[0].rev_flag.value = "Y";
			   	document.forms[0].submitFlag.value="2";
			   	msg = "Do You Want To Revise The SN Details, With SN Closure Request ?";
			   				   	
			   	a=confirm(msg);
				if(a)
				{
					document.forms[0].tcq_sign.disabled=false;
					document.forms[0].var_tcq.disabled=false;
					document.forms[0].option.value="Request_For_SN_Closure";
					document.forms[0].submit();
				}
	 		}
  		}
	}    
}

function TCQModifyDisabled(SN_CD,activity)
{
	if(activity=='update' && SN_CD!=null && SN_CD!="" && SN_CD!=" " && SN_CD!="0")
	{
		var sn_closure_flag = document.forms[0].sn_closure_flag.value;
		if(trim(sn_closure_flag)=='Y')
		{
			document.forms[0].sn_close_chk.disabled = true;
			document.forms[0].sn_closure.disabled = true;
		}
	}
}
function link_contracts(transporter_cdsz,plant_sz,fgsa_rev_no,sn_rev_no){
	var cont_type="S";
	var buyer_cd=document.forms[0].buyer_cd.value;
	var sn_no_cd=document.forms[0].cd.value;
	var fgsa_no=document.forms[0].fgsa_no.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	//alert("---"+fgsa_no+"---"+sn_no_cd+"-fgsa_rev_no--"+fgsa_rev_no+"-sn_rev_no--"+sn_rev_no);
	var trans_cont='',plant_cont='';
	if(transporter_cdsz==1){
		if(document.forms[0].chk_trans.checked==true){
			trans_cont+=document.forms[0].chk_trans.value+",";
		}
	}else{
		for(var k=0;k<transporter_cdsz;k++){
			if(document.forms[0].chk_trans[k].checked==true){
				trans_cont+=document.forms[0].chk_trans[k].value+",";
			}
		}
	}
	
		if(plant_sz==1){
			if(document.forms[0].chk_delv.checked==true){
				plant_cont+=document.forms[0].chk_delv.value+",";
			}
		}else{
			for(var k=0;k<plant_sz;k++){
				if(document.forms[0].chk_delv[k].checked==true){
					plant_cont+=document.forms[0].chk_delv[k].value+",";
				}
			}
		}
	
	if(trans_cont!=''){
		trans_cont=trans_cont.substring(0,trans_cont.length-1);
	}
	if(plant_cont!=''){
		plant_cont=plant_cont.substring(0,plant_cont.length-1);
	}
	var newWindow;
	if(!newWindow || newWindow.closed)
	{
		newWindow = window.open("../contract_master/frm_transporter_contract_list.jsp?trans_cont="+trans_cont+"&plant_cont="+plant_cont+"&buyer_cd="+buyer_cd+"&agr_no="+fgsa_no+"&agr_rev_no="+fgsa_rev_no+"&cont_sn_loa_no="+sn_no_cd+"&cont_sn_loa_rev_no="+sn_rev_no+"&cont_type="+cont_type+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"Invoice_Details","top=10,left=10,width=960,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
	}
	else
	{
		newWindow.close();
	    newWindow = window.open("../contract_master/frm_transporter_contract_list.jsp?trans_cont="+trans_cont+"&plant_cont="+plant_cont+"&buyer_cd="+buyer_cd+"&agr_no="+fgsa_no+"&agr_rev_no="+fgsa_rev_no+"&cont_sn_loa_no="+sn_no_cd+"&cont_sn_loa_rev_no="+sn_rev_no+"&cont_type="+cont_type+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"Invoice_Details","top=10,left=10,width=960,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
	}
}
/* function enableView()
	{
		if(document.forms[0].cust_nm.value=="0")
		{
			document.forms[0].Search1.disabled=true;
		}
		else
		{
			document.forms[0].Search1.disabled=false;
		}
	} */

function refreshpage(){
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	location.replace("../contract_master/frm_SN_creation.jsp?&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl);
}
	
	function setAdvCollect(fgsa_cd,fgsa_revno,bscode,sn_cd,sn_revno,cont_typ){
		
		var write_permission = document.forms[0].write_permission.value;
		var delete_permission = document.forms[0].delete_permission.value;
		var print_permission = document.forms[0].print_permission.value;
		var approve_permission = document.forms[0].approve_permission.value;
		var audit_permission = document.forms[0].audit_permission.value;
		
		var advance_collection = document.forms[0].advance_collection.checked;
		var advance_collection_flag = document.forms[0].advance_collection_flag.value;
		var adv_cur_flg = document.forms[0].adv_cur_flg.value;
		var advance = document.forms[0].advance.checked;
		var fgsa_st_dt = document.forms[0].fgsa_st_dt.value;
		var fgsa_end_dt = document.forms[0].fgsa_end_dt.value;
		
		var selCurrency = "";
		
		if(adv_cur_flg == '1' || adv_cur_flg == '2'){
			selCurrency = adv_cur_flg;
		}else{
			msg+="Please select Currency first!";
		}
		
		var msg = '' ;
		if(advance_collection){
			if(advance_collection_flag == 'Y'){
				if(adv_cur_flg!='0'){
					if(advance){
						
					}else{
						msg+="Please Check Advance Collection Checkbox first!";
					}
					
				}else{
					msg+="Please select Currency first!";
				}
			}else{
				msg+="Please Select Advance Amount !";
			}
		}else{
			msg+="Please Check  Advance Amount Checkbox first!";	
		}
		if(msg!=''){
			alert(msg);
		}else{
			
			var newWindow;
			if(!newWindow || newWindow.closed)
			{
				newWindow = window.open("../contract_master/frm_adv_payment_entry.jsp?fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&bscode="+bscode+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&selCurrency="+selCurrency+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&fgsa_st_dt="+fgsa_st_dt+"&fgsa_end_dt="+fgsa_end_dt+"&cont_typ="+cont_typ,"Advance_Payment","top=10,left=10,width=1200,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
			}
			else
			{
				newWindow.close();
			    newWindow = window.open("../contract_master/frm_adv_payment_entry.jsp?fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&bscode="+bscode+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&selCurrency="+selCurrency+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&fgsa_st_dt="+fgsa_st_dt+"&fgsa_end_dt="+fgsa_end_dt+"&cont_typ="+cont_typ,"Advance_Payment","top=10,left=10,width=1200,height=650,scrollbars=1,status=1,titlebar=no,hotkeys=no");
			}
		}
	}
	
function fromAdvPayChild(fgsa_cd,fgsa_revno,sn_cd,sn_revno,bscode,msg,err_msg){
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	location.replace("../contract_master/frm_SN_creation.jsp?&bscode="+bscode+"&buyer_cd="+bscode+"&FGSA_CD="+fgsa_cd+"&FGSA_REVNO="+fgsa_revno+"&SN_CD="+sn_cd+"&SN_REVNO="+sn_revno+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&msg="+msg+"&err_msg="+err_msg+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl);
}

function hideShowTransCharge(agr_base,plant_sz){
	
	document.forms[0].agrtyp.value = agr_base;
	for(var i = 0 ; i < parseFloat(plant_sz) ; i++){
		if(agr_base == 'D'){
			document.getElementById('plant_trans_charge'+i).style.display = "block";
			
		}else{
			document.getElementById('plant_trans_charge'+i).style.display = "none";	
		}
	}
	
	if(agr_base == 'D'){
		document.getElementById('trans_cont_div').style.display = "block";
	}else{
		document.getElementById('trans_cont_div').style.display = "none";
	}
}

function setChkFlag(size){
// 	alert(size)
	for(var i = 0; i < parseFloat(size); i++){
		if(document.getElementById('chk_delv'+i).checked){
			
			document.getElementById('chk_flg'+i).value= "Y";
		}else{
			document.getElementById('chk_flg'+i).value= "N";
		}
	}
}

function toggleBtn(obj,i){
	if(obj.value == 'In-Active' ){
		
		obj.value = 'Active';
		obj.className = 'btn btn-success btn-xs';
		document.getElementById('lumpsumFlg'+i).value = 'Y';
		
	}else if(obj.value == 'Active'){
		obj.value = 'In-Active';
		obj.className = 'btn btn-danger btn-xs';
		document.getElementById('lumpsumFlg'+i).value = 'N';
	}
}

function checkTransDt(){
	
	var cont_st_dt = document.forms[0].trans_cont_st_dt.value;
	var cont_end_dt = document.forms[0].trans_cont_end_dt.value;
	var st_dt = document.forms[0].st_dt.value;
	var end_dt = document.forms[0].end_dt.value;
	var msg= "";
	
	if(cont_st_dt!='' && cont_end_dt!=''){
// 		alert(cont_st_dt+"---"+cont_st_dt)
		var value = compareDate(cont_st_dt,cont_end_dt);
	  	if(value=='1')
	  	{
	  		msg= "Transportation Service End Date Should Be Greater Than Start Date !!!";
	    	document.forms[0].trans_cont_end_dt.value ="";
// 	    	return false;
	  	}
	}
	/* if(cont_st_dt!=''){
  		var value = compareDate(cont_st_dt,st_dt);
//   		alert(value)
  		if(value=='2')
	  	{
  			
  			msg+= "Transportation Service Start Date Should Be Greater/Equal to Contract Start Date !!!";
	    	document.forms[0].trans_cont_st_dt.value ="";
// 	    	return false;
	  	}
	  	
  		var value = compareDate(cont_st_dt,end_dt);
// 		alert(value+"***"+end_dt)
		if(value=='1')
	  	{
				
			msg+= "Transportation Service Start Date Should Be Less/Equal to Contract End Date !!!";
	    	document.forms[0].trans_cont_st_dt.value ="";
	//	    	return false;
	  	}
	}
	
	if(cont_end_dt!=''){
  		var value = compareDate(cont_end_dt,st_dt);
//   		alert(value)
  		if(value=='2')
	  	{
  			
  			msg+= "Transportation Service End Date Should Be Greater/Equal to Contract Start Date !!!";
	    	document.forms[0].trans_cont_end_dt.value ="";
// 	    	return false;
	  	}
	  	
  		var value = compareDate(cont_end_dt,end_dt);
// 		alert(value+"***"+end_dt)
		if(value=='1')
	  	{
				
			msg+= "Transportation Service End Date Should Be Less/Equal to Contract End Date !!!";
	    	document.forms[0].trans_cont_end_dt.value ="";
	//	    	return false;
	  	}
	} */
	if(msg!=""){
		alert(msg)
	}
}

function setBif(obj){
	
	if(obj.value == 'truck' ){
		
		document.getElementById('truck_fm').readOnly = false;
		document.getElementById('truck_re').readOnly = false;
		document.getElementById('trans_cont_rad_flg').value = "T";
	}else{
		
		document.getElementById('truck_fm').readOnly = true;
		document.getElementById('truck_re').readOnly = true;
		document.getElementById('truck_fm').value = "";
		document.getElementById('truck_re').value = "";
		
	}
	if(obj.value == 'qty'){
		
		document.getElementById('qty_fm').readOnly = false;
		document.getElementById('qty_re').readOnly = false;
		document.getElementById('trans_cont_rad_flg').value = "Q";
	}else{
		
		document.getElementById('qty_fm').readOnly = true;
		document.getElementById('qty_re').readOnly = true;
		document.getElementById('qty_fm').value = "";
		document.getElementById('qty_re').value = "";
	}
}

function checkDt(cur_date,ent_dt,cont_end_dt,cont_st_dt){
	
	var val1 =  compareDate(ent_dt,cont_end_dt);
	var msg = "";
	if(val1 == "1"){ // not allowed (ent_dt > cont_end_dt)
		msg = "SN Closure Date must be less than Contract End Date :"+cont_end_dt;
	}
	var val2 =  compareDate(ent_dt,cont_st_dt);
	if(val2 == "2"){ // not allowed (ent_dt > cont_end_dt)
		msg = "SN Closure Date must be Greater or Equal to Contract Start Date :"+cont_st_dt;
	}
	
	if(msg !=""){
		alert(msg);
		document.forms[0].sn_closure_dt.value = "";
	}
}

//HARSH20220317
var varDCQWindow;
function defineVariablePriceforSN(ContType, buyer_cd,fgsa_cd,fgsa_revno,sn_cd,sn_revno,start_dt,end_dt,buyer_name)
{
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	var enddt=document.forms[0].end_dt.value;
	var stdt=document.forms[0].st_dt.value;
	
	if(stdt != "" & enddt != "") 
	{
		if(start_dt == "")
		{
			start_dt=stdt;
		}
		if(end_dt=="") 
		{
			end_dt=enddt;
		}
		if(!varDCQWindow || varDCQWindow.closed)
		{
			varDCQWindow = window.open("frm_MR_var_dcq.jsp?buyer_cd="+buyer_cd+"&cont_type="+ContType+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&buyer_name="+buyer_name+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"SN_DCQ","top=10,left=10,width=1200,height=600,scrollbars=1");
		}
		else
		{
			varDCQWindow.close();
			varDCQWindow = window.open("frm_MR_var_dcq.jsp?buyer_cd="+buyer_cd+"&cont_type="+ContType+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&buyer_name="+buyer_name+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"SN_DCQ","top=10,left=10,width=1200,height=600,scrollbars=1");
		}
	}
	else 
	{
		alert("Contract Start - End Date are missing!!");
	}
}

///FOLLOWING FUNCTION CREATED BY HARSH 20210216////
var securityWindow;
function securityPreReceipt(ContType, buyer_cd,cust_abbr,fgsa_cd,fgsa_revno,sn_cd,sn_revno,start_dt,end_dt,tcq,dcq,form_id,form_nm,buyer_name)
{
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;

	var map_id = ContType+fgsa_cd+"-"+fgsa_revno+"-"+sn_cd+"-"+sn_revno+"-DLNG";
	var btn_type = document.forms[0].InOut.value;
	var message="";
	var flag = true;
	
	var enddt=document.forms[0].end_dt.value;
	var stdt=document.forms[0].st_dt.value; //AS PER INCIDENT#216
	var currentDate=document.forms[0].currentDate.value;
	var val_dt = compareDate(currentDate,enddt);
	
	if(stdt != "" & enddt != "") //AS PER INCIDENT#216
	{
		if(parseInt(val_dt) == 1)
		{
			message +="Contract is expired!!";
			flag=false;
			
		}
		if(!flag)
		{
			alert(message);
			return flag;
		}
		else
		{
			if(start_dt == "") //AS PER INCIDENT#216
			{
				start_dt=stdt;
			}
			if(end_dt=="") //AS PER INCIDENT#216
			{
				end_dt=enddt;
			}
			
			if(!securityWindow || securityWindow.closed)
			{
				//LINK="frm_CR_Deal_Capturing.jsp?customer_cd="+CustCd+"&cust_abbr="+CustABBR+"&customer_nm="+CustCd+"&from_dt="+from_dt+"&to_dt="+to_dt+"&FGSA_CD="+fgca_no+"&rev_no="+fgca_rev_no+"&SN_NO="+sn_no+"&SN_REV_NO="+sn_rev_no+"&cont_type="+cont_type+"&tcq="+tcq+"&dcq="+dcq+"&btn_type="+btn_type+"&form_id="+form_id+"&form_nm="+form_nm;
				securityWindow = window.open("../mrcr/frm_CR_Deal_Capturing.jsp?customer_cd="+buyer_cd+"&cust_abbr="+cust_abbr+"&cont_type="+map_id+"&FGSA_CD="+fgsa_cd+"&rev_no="+fgsa_revno+"&SN_NO="+sn_cd+"&SN_REV_NO="+sn_revno+"&from_dt="+start_dt+"&to_dt="+end_dt+"&customer_nm="+buyer_name+"&tcq="+tcq+"&dcq="+dcq+"&form_id="+form_id+"&form_nm="+form_nm+"&btn_type="+btn_type+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"frm_CR_Deal_Capturing","top=10,left=100,width=1200,height=600,scrollbars=1");
			}
			else
			{
				securityWindow.close();
				securityWindow = window.open("../mrcr/frm_CR_Deal_Capturing.jsp?customer_cd="+buyer_cd+"&cust_abbr="+cust_abbr+"&cont_type="+map_id+"&FGSA_CD="+fgsa_cd+"&rev_no="+fgsa_revno+"&SN_NO="+sn_cd+"&SN_REV_NO="+sn_revno+"&from_dt="+start_dt+"&to_dt="+end_dt+"&customer_nm="+buyer_name+"&tcq="+tcq+"&dcq="+dcq+"&form_id="+form_id+"&form_nm="+form_nm+"&btn_type="+btn_type+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"frm_CR_Deal_Capturing","top=10,left=100,width=1200,height=600,scrollbars=1");
			}
		}
	}
	else //AS PER INCIDENT#216
	{
		alert("Contract Start - End Date are missing!!");
	}
}

function refershPar(msg,activity,bscode,customer_cd,FGSA_No,FGSA_Rev_No,snNo,snRevNo){
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	//location.replace("../contract_master/frm_SN_master.jsp?msg="+msg+"&activity="+activity+"&bscode="+bscode+"&buyer_cd="+customer_cd+"&FGSA_CD="+FGSA_No+"&FGSA_REVNO="+FGSA_Rev_No+"&SN_CD="+snNo+"&SN_REVNO="+snRevNo+"&securityFlag=Y&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);
	alert(msg);
	
	if(msg!="Data Not Submitted!!!")
	{
		document.forms[0].securityFlag.value = "Y"
	}
	else
	{
		document.forms[0].securityFlag.value = "N"
	}
}

//HARSH20220317
var PriceInfoWindow;
function openPriceInformation(ContType, buyer_cd,fgsa_cd,fgsa_revno,sn_cd,sn_revno,start_dt,end_dt,buyer_name)
{
	var enddt=document.forms[0].end_dt.value;
	var stdt=document.forms[0].st_dt.value;
	
	if(stdt != "" & enddt != "") 
	{
		if(start_dt == "") 
		{
			start_dt=stdt;
		}
		if(end_dt=="")	
		{
			end_dt=enddt;
		}
		
		if(!PriceInfoWindow || PriceInfoWindow.closed)
		{
			PriceInfoWindow = window.open("rpt_variable_price_information.jsp?buyer_cd="+buyer_cd+"&cont_type="+ContType+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&buyer_name="+buyer_name,"VariablePrice","top=10,left=10,width=800,height=800,scrollbars=1");
		}
		else
		{
			PriceInfoWindow.close();
			PriceInfoWindow = window.open("rpt_variable_price_information.jsp?buyer_cd="+buyer_cd+"&cont_type="+ContType+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&buyer_name="+buyer_name,"VariablePrice","top=10,left=10,width=800,height=800,scrollbars=1");
		}
	}
	else
	{
		alert("Contract Start - End Date are missing!!");
	}
}

</script>
</head>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.contract_master.DataBean_Contract_Master" id="masCont" scope="page"/>   
<%-- <jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_modulelock" id="modlock" scope="request"/> --%>
<%

	utilBean.init();
	String date = utilBean.getGen_dt();
	String current_time = utilBean.getTime_gen(); //HARSH20211028
    String username = (String)session.getAttribute("username");
    NumberFormat nf = new DecimalFormat("###########0.00"); 
    double convt_mmbtu_to_mt= (double) session.getAttribute("convt_mmbtu_to_mt");//HA20201229
    
	String activity = request.getParameter("activity")==null?"update":request.getParameter("activity");
	String bscode = request.getParameter("bscode")==null?"0":request.getParameter("bscode");
	String sign_date = request.getParameter("sign_dt")==null?date:request.getParameter("sign_dt");
	String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
	String buyer_abbr = request.getParameter("buyer_abbr")==null?"":request.getParameter("buyer_abbr");
	String buyer_cd = request.getParameter("buyer_cd")==null?"0":request.getParameter("buyer_cd");
	String plant_nm = request.getParameter("plant_nm")==null?"":request.getParameter("plant_nm");
	String plant_type = request.getParameter("plant_type")==null?"":request.getParameter("plant_type");
	String seq_no = request.getParameter("seq_no")==null?"0":request.getParameter("seq_no");
	String msg = request.getParameter("msg")== null?"":request.getParameter("msg");
	String err_msg = request.getParameter("err_msg")== null?"":request.getParameter("err_msg");
	String FGSA_REVNO = request.getParameter("FGSA_REVNO")==null?"0":request.getParameter("FGSA_REVNO");
// 	System.out.println("FGSA_REVNO--------"+FGSA_REVNO);
	String SN_CD = request.getParameter("SN_CD")== null?"":request.getParameter("SN_CD");
	String SN_REVNO = request.getParameter("SN_REVNO")==null?"0":request.getParameter("SN_REVNO");
	String FGSA_CD = request.getParameter("FGSA_CD")==null?"":request.getParameter("FGSA_CD");
	
	String write_permission = (String)session.getAttribute("write_permission") ==null?"":session.getAttribute("write_permission").toString();
	String delete_permission = (String)session.getAttribute("delete_permission")==null?"":session.getAttribute("delete_permission").toString();
	String print_permission = (String)session.getAttribute("print_permission") ==null?"":session.getAttribute("print_permission").toString();
	String approve_permission = (String)session.getAttribute("approve_permission") ==null?"":session.getAttribute("approve_permission").toString();
	String audit_permission = (String)session.getAttribute("audit_permission") ==null?"":session.getAttribute("audit_permission").toString();
	
	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200218
	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200218
	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200218
	String modUrl = session.getAttribute("modUrl")==null?"":session.getAttribute("modUrl").toString();//HA20200218
	String formName = session.getAttribute("form_name")==null?"":session.getAttribute("form_name").toString();//HA20200218
	
	String securityFlag = request.getParameter("securityFlag")==null?"N":request.getParameter("securityFlag");
	
	String user_cd=(String)session.getAttribute("user_cd"); //ADDED BY RG 16-10-2015 FOR CUSTOMER WISE ACCESS RIGHTS
	String customer_access_flag = "N"; //(String)session.getAttribute("customer_access_flag"); BY HA20200218
	masCont.setCallFlag("SN_MST");
	masCont.setBscode(bscode);
	masCont.setFGSA_cd(FGSA_CD);
	masCont.setSN_CD(SN_CD);
	masCont.setSN_REVNo(SN_REVNO);
	masCont.setFGSA_REVNo(FGSA_REVNO);
	masCont.setEmp_cd(user_cd);		//ADDED BY RG 16-10-2015 FOR CUSTOMER WISE ACCESS RIGHTS
	masCont.setCustomer_access_flag(customer_access_flag);
	
	masCont.init();
	
	buyer_name = masCont.getBName();
	
	buyer_abbr = masCont.getBAbbr();
	String sn_name = "";
	
	if(!SN_CD.equalsIgnoreCase(""))
	{
		//System.out.println("FGSA_REVNO11--------"+FGSA_REVNO);
	    sn_name = buyer_abbr+"-FL"+FGSA_CD+"-FLREV"+FGSA_REVNO+"-SN"+SN_CD+"-SNREV"+SN_REVNO;
	}
	else
	{
		sn_name = "";
	}
	 
	String SN_NAME = masCont.getSN_NAME();
	if(!SN_NAME.trim().equalsIgnoreCase(""))
	{
		sn_name = SN_NAME;
	}
	String DDA_TIME = masCont.getDDA_Dt_Tm();
	String DDA_DT = masCont.getDDA_Dt();
	String SIGNING_DT = masCont.getSIGNING_DT();
	String RENEWAL_DT = masCont.getRENEWAL_DT();
	String START_DT = masCont.getSTART_DT();
	String END_DT = masCont.getEND_DT();
	String REV_DT = masCont.getREV_DT();
	String TCQ = masCont.getTCQ();
	String DCQ = masCont.getDCQ();
	String MT = masCont.getMT();
	String DCQ_MT = masCont.getDCQ_MT();
	
	String VERIFY_FLAG = masCont.getVERIFY_FLAG();
	String APPROVE_FLAG = masCont.getAPPROVE_FLAG();
	String VARIATION_QTY = masCont.getVARIATION_QTY();
	String VARIATION_MODE = masCont.getVARIATION_MODE();
	
	//Following (4) String Getter Methods Has Been Introduced By Samik Shah On 28th September, 2010 ...
	String SN_REF_NO = masCont.getSN_REF_NO();
	String PRE_SN_REF_NO = masCont.getPRE_SN_REF_NO();
	String TRANSPORTATION_CHARGE = masCont.getTRANSPORTATION_CHARGE();
	String REMARK = masCont.getREMARK();
	String adv_amt = masCont.getAdv_amt();
	
	//All Transporter's details ...
	Vector transporter_cd = masCont.getTransporter_cd(); 
	Vector transporter_abbr = masCont.getTransporter_abbr();
	
	//FGSA Transporter's details ...
	Vector FGSA_transporter_cd = masCont.getFGSA_transporter_cd(); //Introduced By Samik Shah On 17th July, 2010 ...
	Vector FGSA_transporter_abbr = masCont.getFGSA_transporter_abbr(); //Introduced By Samik Shah On 17th July, 2010 ...
	
	//All Plant's Details Of Particular Buyer ...
	Vector buyer_plant_nm = masCont.getBuyer_plant_nm();
	Vector buyer_plant_type = masCont.getBuyer_plant_type();
	Vector buyer_plant_seq_no = masCont.getBuyer_plant_seq_no();
	
	//FGSA Plant's Details ...
	Vector vPLANT_SEQ_NO = masCont.getVPLANT_SEQ_NO();
	Vector vPLANT_NAME = masCont.getVPLANT_NAME();
	
	//All Clause's Details ...
	Vector clause_cd = masCont.getClause_cd();
	Vector clause_nm = masCont.getClause_nm();
	Vector clause_abbr = masCont.getClause_abbr();
		
	//FGSA Clause's Details ...
	Vector FGSA_clause_cd = masCont.getFGSA_clause_cd();
	Vector FGSA_clause_nm = masCont.getFGSA_clause_nm();
	Vector FGSA_clause_abbr = masCont.getFGSA_clause_abbr();
	
	//All Other FGSA details ...
	String fgSIGNING_DT = masCont.getFgSIGNING_DT();
	String fgSTART_DT = masCont.getFgSTART_DT();
	String fgEND_DT = masCont.getFgEND_DT();
	String fgRENEWAL_DT	= masCont.getFgRENEWAL_DT();
	String fgFGSA_BASE = masCont.getFgFGSA_BASE();
	String fgFGSA_TYPE = masCont.getFgFGSA_TYPE();
	String fgSTATUS	= masCont.getFgSTATUS();
	String fgBUYER_NOM = masCont.getFgBUYER_NOM();
	String fgBUYER_MONTH_NOM = masCont.getFgBUYER_MONTH_NOM();
	String fgBUYER_WEEK_NOM	= masCont.getFgBUYER_WEEK_NOM();
	String fgBUYER_DAILY_NOM = masCont.getFgBUYER_DAILY_NOM();
	String fgSELLER_NOM	= masCont.getFgSELLER_NOM();
	String fgSELLER_MONTH_NOM = masCont.getFgSELLER_MONTH_NOM();
	String fgSELLER_WEEK_NOM = masCont.getFgSELLER_WEEK_NOM();
	String fgSELLER_DAILY_NOM = masCont.getFgSELLER_DAILY_NOM();
	String fgDAY_DEF = masCont.getFgDAY_DEF();
	String fgDAY_START_TIME	= masCont.getFgDAY_START_TIME();
	String fgDAY_END_TIME = masCont.getFgDAY_END_TIME();
	String fgMDCQ = masCont.getFgMDCQ();//System.out.println("fgMDCQ: "+fgMDCQ);
// 	String fgMDCQ_PERCENTAGE = masCont.getFgMDCQ_PERCENTAGE(); //System.out.println("fgMDCQ_PERCENTAGE: "+fgMDCQ_PERCENTAGE);
	String fgMDCQ_PERCENTAGE = masCont.getMDCQ_PERCENTAGE();
	String fgMEASUREMENT =  masCont.getFgMEASUREMENT();
	String fgMEAS_STANDARD = masCont.getFgMEAS_STANDARD();
	String fgMEAS_TEMPERATURE = masCont.getFgMEAS_TEMPERATURE();
	String fgPRESSURE_MIN_BAR = masCont.getFgPRESSURE_MIN_BAR();
	String fgPRESSURE_MAX_BAR = masCont.getFgPRESSURE_MAX_BAR();
	String fgOFF_SPEC_GAS = masCont.getFgOFF_SPEC_GAS();
	String fgSPEC_GAS_ENERGY_BASE = masCont.getFgSPEC_GAS_ENERGY_BASE();
	String fgSPEC_GAS_MIN_ENERGY = masCont.getFgSPEC_GAS_MIN_ENERGY();
	String fgSPEC_GAS_MAX_ENERGY = masCont.getFgSPEC_GAS_MAX_ENERGY();
	
	//SN Transporter's Details ...
	Vector sn_transporter_cd = masCont.getSn_transporter_cd();
	Vector sn_transporter_abbr = masCont.getSn_transporter_abbr();
	
	//SN Plant's Details ...
	Vector sn_vPLANT_SEQ_NO = masCont.getSn_vPLANT_SEQ_NO();
	Vector sn_vPLANT_NAME = masCont.getSn_vPLANT_NAME();
	
	//SN Clause's Details ...
	Vector sn_clause_cd = masCont.getSn_clause_cd();

	//All Other SN Details ...
	String QUANTITY_UNIT = masCont.getQUANTITY_UNIT();
	String GCV = masCont.getGCV();
	String NCV = masCont.getNCV();
	String RATE	= masCont.getRATE();
	String RATE_UNIT = masCont.getRATE_UNIT();
	String STATUS = masCont.getSTATUS();
	String BUYER_NOM = masCont.getBUYER_NOM();
	String BUYER_MONTH_NOM = masCont.getBUYER_MONTH_NOM();
	String BUYER_WEEK_NOM = masCont.getBUYER_WEEK_NOM();
	String BUYER_DAILY_NOM = masCont.getBUYER_DAILY_NOM();
	String SELLER_NOM = masCont.getSELLER_NOM();
	String SELLER_MONTH_NOM = masCont.getSELLER_MONTH_NOM();
	String SELLER_WEEK_NOM = masCont.getSELLER_WEEK_NOM();
	String SELLER_DAILY_NOM = masCont.getSELLER_DAILY_NOM();
	String DAY_DEF = masCont.getDAY_DEF();
	String DAY_START_TIME = masCont.getDAY_START_TIME();
	String DAY_END_TIME	= masCont.getDAY_END_TIME();
	String MDCQ	= masCont.getMDCQ();
	String MDCQ_PERCENTAGE = masCont.getMDCQ_PERCENTAGE();
	String MEASUREMENT = masCont.getMEASUREMENT();
	String MEAS_STANDARD = masCont.getMEAS_STANDARD();
	String MEAS_TEMPERATURE	= masCont.getMEAS_TEMPERATURE();
	String PRESSURE_MIN_BAR	= masCont.getPRESSURE_MIN_BAR();
	String PRESSURE_MAX_BAR	= masCont.getPRESSURE_MAX_BAR();
	String OFF_SPEC_GAS	= masCont.getOFF_SPEC_GAS();
	String SPEC_GAS_ENERGY_BASE = masCont.getSPEC_GAS_ENERGY_BASE();
	String SPEC_GAS_MIN_ENERGY = masCont.getSPEC_GAS_MIN_ENERGY();
	String SPEC_GAS_MAX_ENERGY = masCont.getSPEC_GAS_MAX_ENERGY();
	String MDCQ_QTY_CD = masCont.getMDCQ_QTY_CD();  
	String OBLIGATION = masCont.getOBLIGATION();
	String OBLIGATION_PER = masCont.getOBLIG_PERCENTAGE();
	String OBLIGATION_QTY = masCont.getOBLIG_QTY_CD();
	
	String ALLOCATED_QTY = masCont.getALLOCATED_QTY(); //Introduced By Samik Shah On 7th August, 2010 ...
	
	//Following 4 String Getter Methods Has Been Introduced By Samik Shah On 10th August, 2010 ...
	String SN_CLOSURE_FLAG = masCont.getSN_CLOSURE_FLAG();
	String SN_CLOSURE_QTY = masCont.getSN_CLOSURE_QTY();
	String SN_CLOSURE_DT = masCont.getSN_CLOSURE_DT();
	String DCQ_FLAG = masCont.getDCQ_FLAG();

	if(SN_CLOSURE_DT==null || SN_CLOSURE_DT.trim().equals(""))
	{
		if(END_DT!=null && !END_DT.trim().equals(""))
		{
			SN_CLOSURE_DT = END_DT;
		}
	}		
	if(START_DT==null || START_DT.equals("") || START_DT.equals(" ") || END_DT==null || END_DT.equals("") || END_DT.equals(" "))
	{
		BUYER_NOM = fgBUYER_NOM;
		BUYER_MONTH_NOM = fgBUYER_MONTH_NOM;
		BUYER_WEEK_NOM = fgBUYER_WEEK_NOM;
		BUYER_DAILY_NOM = fgBUYER_DAILY_NOM;
		SELLER_NOM = fgSELLER_NOM;
		SELLER_MONTH_NOM = fgSELLER_MONTH_NOM;
		SELLER_WEEK_NOM = fgSELLER_WEEK_NOM;
		SELLER_DAILY_NOM = fgSELLER_DAILY_NOM;
		DAY_DEF = fgDAY_DEF;
		DAY_START_TIME = fgDAY_START_TIME;
		DAY_END_TIME = fgDAY_END_TIME;
		MDCQ = fgMDCQ;
		MDCQ_PERCENTAGE = fgMDCQ_PERCENTAGE;
		MEASUREMENT = fgMEASUREMENT;
		MEAS_STANDARD = fgMEAS_STANDARD;
		MEAS_TEMPERATURE = fgMEAS_TEMPERATURE;
		PRESSURE_MIN_BAR = fgPRESSURE_MIN_BAR;
		PRESSURE_MAX_BAR = fgPRESSURE_MAX_BAR;
		OFF_SPEC_GAS = fgOFF_SPEC_GAS;
		SPEC_GAS_ENERGY_BASE = fgSPEC_GAS_ENERGY_BASE;
		SPEC_GAS_MIN_ENERGY = fgSPEC_GAS_MIN_ENERGY;
		SPEC_GAS_MAX_ENERGY = fgSPEC_GAS_MAX_ENERGY;
		MDCQ_QTY_CD = "1";
	}
	
	String strTrans = "";
	String strClause = "";
	String strPlant = "";
	
	Vector CUST_CD = masCont.getCUST_CD();
	Vector CUST_NM = masCont.getCUST_NM();
	for(int i=0;i<sn_transporter_cd.size();i++)
	{
		strTrans = strTrans + sn_transporter_cd.elementAt(i)+"@";
	}
	for(int i=0;i<sn_clause_cd.size();i++)
	{
		strClause = strClause + sn_clause_cd.elementAt(i)+"@";
	}
	for(int i=0;i<sn_vPLANT_SEQ_NO.size();i++)
	{
		strPlant = strPlant + sn_vPLANT_SEQ_NO.elementAt(i)+"@";
	}
	
	
	if(sn_transporter_cd.size()==0)
	{
		for(int i=0;i<FGSA_transporter_cd.size();i++)
		{
			strTrans = strTrans + FGSA_transporter_cd.elementAt(i)+"@";
		}
	}
	if(sn_clause_cd.size()==0)
	{
		for(int i=0;i<FGSA_clause_cd.size();i++)
		{
			strClause = strClause + FGSA_clause_cd.elementAt(i)+"@";
		}
	}
	if(sn_vPLANT_SEQ_NO.size()==0)
	{
		for(int i=0;i<vPLANT_SEQ_NO.size();i++)
		{
			strPlant = strPlant + vPLANT_SEQ_NO.elementAt(i)+"@";
		}
	}
	
	//Following String Getter Method Has Been Defined By Samik Shah On 4th December, 2010 ...
	String FORMULA_REMARK = masCont.getFORMULA_REMARK();		
	String FCC_flag = masCont.getFCC_flag();
// 	System.out.println("FCC_flag----"+FCC_flag);
	//Following String Getter Method Has Been Defined By Achal On 26th March, 2010 ...
	String SN_CLOSURE_REQUEST = masCont.getSN_CLOSURE_REQUEST();
	String SN_CLOSURE_CLOSE = masCont.getSN_CLOSURE_CLOSE();	
	String fcc_flag = masCont.getFcc_flag();
	String fcc_by = masCont.getFcc_by();
	String fcc_date = masCont.getFcc_date();
	
    String SN_REMARK=masCont.getSN_REMARK();
	
	// ADDED BY RG20140923
	String Adjust_flag_SN=masCont.getADJUST_FLAG_SN();
	String adjust_flag_fgsa=masCont.getADJUST_FLAG_FGSA();
	String adjust_amt=masCont.getADJUST_AMT_SN();
	String adjust_cur=masCont.getADJUST_CUR_SN();
	String discount_flag=masCont.getDISCOUNT_FLAG_SN();
	String discount_price=masCont.getDISCOUNT_PRICE_SN();
	String tariff_flag=masCont.getTARIFF_FLAG_SN();
	String tariff_amt=masCont.getTARIFF_INR_SN();
	
	Vector component_cd=masCont.getComponent_cd();
	Vector component_nm=masCont.getComponent_nm();
	
	String advance_collection = masCont.getADVANCE_COLLECTION();
	String advance_collection_flag = masCont.getADVANCE_COLLECTION_FLAG();
	
	String firm_qty = masCont.getFirm_qty();
	String re_qty = masCont.getRe_qty();
	String split_tcq_flag = masCont.getSplit_tcq_flag();
	boolean billFlag = masCont.isBillFlag();
	boolean invoiceFlag = masCont.isInvoiceFlag();//RG20200129
	String agree_base=masCont.getAgreement_base();
	String sn_base=masCont.getSN_BASE();
	Vector VTruck_Trans_Cd=masCont.getVTruck_Trans_Cd();
	Vector VTruck_Trans_Abr=masCont.getVTruck_Trans_Abr();
	Vector VCust_Trans_Cd=masCont.getVCust_Trans_Cd();
	String strCustTrans = ""; //SB
	for(int i=0;i<VCust_Trans_Cd.size();i++)
	{
		strCustTrans = strCustTrans + VCust_Trans_Cd.elementAt(i)+"@";
	}

	String Price_req_flag=masCont.getPrice_req_flag();
	Integer count_price=masCont.getCount_price();
	Vector VNewPrice=masCont.getVNewPrice();
	Vector VNewPriceEffDt=masCont.getVNewPriceEffDt();
	Vector VNewPriceAprvFlag=masCont.getVNewPriceAprvFlag();
	String tcq_req_flag = masCont.getTcq_req_flag();
	String adv_cur_flg = masCont.getAdv_cur_flg();
	String fcc_act_flag = masCont.getFcc_flag();
	
	String SECURITY_FLAG_SN=masCont.getSECURITY_FLAG_SN();
	String  SECURITY_AMT_SN=masCont.getSECURITY_AMT_SN();
	String SECURITY_CUR_SN=masCont.getSECURITY_CUR_SN();
	
	String LUMPSUM_FLAG_SN=masCont.getLUMPSUM_FLAG_SN();
	String  LUMPSUM_AMT_SN=masCont.getLUMPSUM_AMT_SN();
	String LUMPSUM_CUR_SN=masCont.getLUMPSUM_CUR_SN();
	
	Vector vsn_plant_inr_mmbtu = masCont.getVsn_plant_inr_mmbtu();
	Vector vsn_plant_inr_km = masCont.getVsn_plant_inr_km();
	Vector vsn_plant_lumpsum_flg = masCont.getVsn_plant_lumpsum_flg();
	
	String trans_cont_st_dt = masCont.getTrans_cont_st_dt();
	String trans_cont_end_dt = masCont.getTrans_cont_end_dt();
	String trans_cont_no = masCont.getTrans_cont_no();
	String trans_trucks_cnt = masCont.getTrans_trucks_cnt();
	String trans_total_qty = masCont.getTrans_total_qty();
	String trans_cont_sign_dt = masCont.getTrans_cont_sign_dt();
	String truck_fm = masCont.getTruck_fm();
	String truck_re = masCont.getTruck_re();
	String qty_fm = masCont.getQty_fm();
	String qty_re = masCont.getQty_re();
	String trans_cont_rad_flg = masCont.getTrans_cont_rad_flg();
	
	//HARSH20211029//////////
	String DEAL_ENT_DT = masCont.getDEAL_ENT_DT();
	String DEAL_ENT_TIME = masCont.getDEAL_ENT_TIME();
	String DEAL_ENT_BY = masCont.getDEAL_ENT_BY();
 	if(DEAL_ENT_DT.equals("")){
		DEAL_ENT_DT = date;
	} 
	if(DEAL_ENT_TIME.equals("")){
		DEAL_ENT_TIME = current_time;
	}
	
	String SIGNING_TIME = masCont.getSIGNING_TIME();
	if(SIGNING_TIME.equals(""))
	{
		SIGNING_TIME = "00:00";
	}
	String tcq_req_close = masCont.getTcq_req_close();//Hiren_20220105
	String title = "";
	if(SN_CLOSURE_FLAG.equalsIgnoreCase("Y")  || SN_CLOSURE_CLOSE.equalsIgnoreCase("Y") || SN_CLOSURE_REQUEST.equalsIgnoreCase("Y")){
		if(FCC_flag.equalsIgnoreCase("Approved")){
			title="SN closed";
		}else{ 
			title="SN closure requested";
		}
	}
	/////////////////////////
%>

<body onload="setValues('<%=SN_CD%>','<%=strTrans%>','<%=strClause%>','<%=strPlant%>','<%=strCustTrans%>','<%=VARIATION_MODE%>','<%=OBLIGATION_QTY%>','<%=MDCQ_QTY_CD%>','<%=SPEC_GAS_ENERGY_BASE%>');TCQModifyDisabled('<%=SN_CD%>','<%=activity%>');">
<form  method="post" action="../servlet/Frm_Contract_MasterV2" id="myform">
<%	if(print_permission.trim().equalsIgnoreCase("N"))
	{%>
<!-- 	 <script language="javascript" src="../js/mouseclk.js"></script> -->
<%	}%>
  <%try{ %> 
<div class="tab-content">
	<div class="tab-pane active" >
		<div class="box mb-0">
			<div class="box-header with-border">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12" style="background-color: #fffff8;">
					<%if(msg.length()>5){%>
						<div class="col-xs-12" >
					  		<div class="table-responsive" >
					    		<table class="table table-striped" >
					    			<tr>
					    				<th class="text-center" colspan="12" style="color: blue;"> <%=msg%> </th>
					    			</tr>
					    		</table>
					    	</div>
					    </div>				
					<%} %>
					
					<%if(err_msg.length()>5){%>
						<div class="col-xs-12" >
					  		<div class="table-responsive" >
					    		<table class="table table-striped" >
					    			<tr>
					    				<th class="text-center" colspan="12" style="color: red;"> <%=err_msg%> </th>
					    			</tr>
					    		</table>
					    	</div>
					    </div>				
					<%} %>
			
					<div class="row">
						<div class="table-responsive col-lg-6">
							<table class="table table-striped" >
								<tr>
				    				<td class="info" >Select Customer </td>
				    				<td >
				    					<select name="cust_nm" class="select" >
									      <option value="0">--Select--</option>
									  <%
									  for(int i=0;i<CUST_CD.size();i++){%>
									  		<option value="<%=CUST_CD.elementAt(i)%>"><%=CUST_NM.elementAt(i)%></option>
									  <%}%>						
										</select>
										<script> document.forms[0].cust_nm.value = "<%=bscode%>";</script>
										<input name="cd" type="hidden" value="<%=SN_CD%>" style="height: 27px;">
				    					<input class="select" type="hidden" name="sn_no" maxlength="15" size="15" value="<%=SN_CD%>"  readonly="readonly" style="height: 27px;">
				    					<input type="hidden" name="fgsa_no" value="<%=FGSA_CD%>" >
			     						<input type="button" class="btn-primary" value="View List" name="Search1" onclick="openSNList();" >
				    				</td>
				    			</tr>
				    			<!-- DEAL ENTER DATE ADDED BY HARSH 28/10/2021 -->
				    			<tr>
				    				<td class="info">
						    			Deal Enter Date
					    			</td>
									<td colspan="" align="left">
										<input type="text" name="deal_enter_dt" id="deal_enter_dt" value="<%=DEAL_ENT_DT%>" maxlength="10" size="10" onBlur="validateDate(this);" title="dd/mm/yyyy Format" class="mkrdly" readonly="readonly">
								    	<input type="text" name="deal_enter_time" id="deal_enter_time" value="<%=DEAL_ENT_TIME%>" size="3" maxlength="5" onblur="validateTime(this);" class="mkrdly" readonly="readonly">&nbsp;Hrs:Mins
										<input type="hidden" name="deal_ent_by" value="<%=DEAL_ENT_BY %>">
									</td>
				    			</tr>
				    			<!-- ////////////////////////////////////// -->
				    			<tr>
				    				<td class="info">
						    			Signing Date<span class="s-red">*</span>
					    			</td>
					    			<td >
					    				<input type="hidden" name="fgsa_sign_dt" maxlength="10" size="10" value="<%=fgSIGNING_DT%>">
			      						<input type="text" class="select" autocomplete="off" name="sign_dt" id="datetimepicker1" maxlength="10" value="<%=SIGNING_DT%>" onBlur="validateDate(this);" title="dd/mm/yyyy Format">
					    				<!-- HARSH20211028 -->
										<input type="text" class="" name="sign_time" id="sign_time" value="<%=SIGNING_TIME%>" size="3" maxlength="5" onblur="validateTime(this);">&nbsp;Hrs:Mins
								    	<!-- /////////////// -->
					    			</td>
				    			</tr>
				    			
				    			
				    			<tr>
				    				<td class="info">SN Name<span class="s-red">*</span></td>
				    				<td>
				    					<input name="sn_name" type="text" size="50" maxlength="55" value="<%=sn_name%>" style="height: 27px;" readonly>
				    				</td>
				    			</tr>
				    			 <tr>
									 <td class="info">Start Date<span class="s-red">*</span></td>
									 <td> 
									 	<input type=hidden name=fgsa_st_dt  value="<%=fgSTART_DT%>" onBlur="validateDate(this);" title="dd/mm/yyyy Format">
									 	<input class="select"  autocomplete="off" type=text name=st_dt id="datetimepicker3" maxlength="10" size="10" value="<%=START_DT%>" onBlur="validateDate(this);" title="dd/mm/yyyy Format">
								     </td>
				     			</tr>
				     			<tr>
					    			<td class="info">End Date<span class="s-red">*</span></td>
									<td> 
									 	<input type=hidden name=fgsa_end_dt  value="<%=fgEND_DT%>" onBlur="validateDate(this);" title="dd/mm/yyyy Format">
									 	<input class="select" autocomplete="off" type=text name=end_dt id="datetimepicker4" maxlength="10" size="10" value="<%=END_DT%>" onBlur="validateDate(this);" title="dd/mm/yyyy Format">
								    </td>
					    		</tr>
				    			<tr>
				    				<td class="info">Sales Gas Price<span class="s-red">*</span></td>
						           	<td >			  
									  	<input type="text" class="text-right" name="salesPrice" size="5" value="<%=RATE%>" 
									  	<%if(RATE.equalsIgnoreCase("")){}
									  	else{
									  		if(FCC_flag.equalsIgnoreCase("Approved")){ %>
									  			readonly="readonly"
									  	<%}
									  		else
									  		{ %> 
									  			readonly="readonly"
									  	<%	}
									  	%>  
									  	<%}%>
									  	onblur="checkNumber1(this,6,4);" style="background:YELLOW; style="height: 27px;">&nbsp;($/MMBTU)
									  	&nbsp;&nbsp;
									  	<%if(!RATE.equalsIgnoreCase("")){ %>
<%-- 									  		<input type="button" name="reqPrice" value="Request To Change Price" onclick="reqChangePrice();" <%if(invoiceFlag) { %>disabled title="Invoice Generated"<% } %>> --%>
											<input type="button"  name="reqPrice" value="Request To Change Price" 
												<%if(Price_req_flag.equals("Y") ){ %>
													 disabled="disabled" title="Request already sent for price change" 
												<%}else if(SN_CLOSURE_REQUEST.equals("Y") ){ %>
												 	disabled="disabled" title = "<%=title %>"
												 	
												<%}else{ %> class="btn-primary" <%} %>
												 onclick="reqChangePrice();" <%if(count_price>0) { %> title="<%=count_price%> Nomination is done"<% } %>>
									  	<% } %>
									  	<!-- //HARSH20220317 -->
									  	&nbsp;&nbsp;<input type="button" style="color:RED;" value="Variable Pricing" title="Click to configure Price Curve" name="var_dcq" onclick="defineVariablePriceforSN('E','<%=buyer_cd%>','<%=FGSA_CD%>','<%=FGSA_REVNO%>','<%=SN_CD%>','<%=SN_REVNO%>','<%=START_DT%>','<%=END_DT%>',&quot;<%=buyer_name%>&quot;);">
									  	
									  	&nbsp;&nbsp;<img style="cursor: pointer;vertical-align: middle;" src="../images/information.png" title="Price Info from Configured Price Curve" width="20" height="20" onclick="openPriceInformation('E','<%=buyer_cd%>','<%=FGSA_CD%>','<%=FGSA_REVNO%>','<%=SN_CD%>','<%=SN_REVNO%>','<%=START_DT%>','<%=END_DT%>',&quot;<%=buyer_name%>&quot;)">
									  	<!-- /////////////// -->
									  	<BR>
									  	<%for(int i=0; i<VNewPrice.size(); i++) { %>
									  	<%if(VNewPriceAprvFlag.elementAt(i).equals("A")) {%>
									  		&nbsp;&nbsp;<B><%=VNewPrice.elementAt(i)%> ($/MMBTU)&nbsp;&nbsp;<FONT COLOR=BLACK><B>From <%=VNewPriceEffDt.elementAt(i)%></B></FONT><FONT COLOR=GREEN>   APPROVED</FONT></B><BR>
									  		<%} else { %>
									  		&nbsp;&nbsp;<B><%=VNewPrice.elementAt(i)%>($/MMBTU)&nbsp;&nbsp;<FONT COLOR=BLACK><B>From <%=VNewPriceEffDt.elementAt(i)%></B></FONT><FONT COLOR=RED> YET TO APPROVE</FONT></B><BR>
									  	<%} %>
									  	<%} %>
									  </td>	
				    			</tr>
				    			<tr>
									<td class="info">Quantity<span class="s-red">*</span></td>
									
									<td>
										<table>
											<tr>
												<td>TCQ:&nbsp;<input  class="text-right" type=text name="tcq" id="tcq" <%if(TCQ.equalsIgnoreCase("")){}else{%> readonly="readonly"<%}%>
									 						maxlength="12" size="10" value="<%=TCQ%>" onchange="convToMtTcq(this)" onBlur="checkNumber1(this,10,2);" style="background:YELLOW; style="height: 17px;"></td>
									 			<td>(MMBTU)&nbsp;</td>			
												<td><input class="text-right" type=text name="mtt" maxlength="12" id="mtt" size="5" onchange="convToTcq(this)" value="<%if(!TCQ.equals("") && !TCQ.equals("0") ) {%> <%=nf.format(Double.parseDouble(TCQ) / convt_mmbtu_to_mt )%><%} %> " onBlur="checkNumber1(this,9,2);checkForNegNumber(this);checkDCQ(this);" style="background:YELLOW; height: 27px;"></td>
												<td>(MT)&nbsp;</td>		
											</tr>
										</table>
								     </td>
								     
								    
								</tr>
								
								
								<tr >
								    <td class="info">Transportation Charge</td>
								    <td><input class="select"   name="transportation_charge" id="trans_charge" type="text" size="5" maxlength="7" value="<%=TRANSPORTATION_CHARGE%>" style="background:YELLOW;text-align: right;" onblur="checkNumber1(this,6,2);" readonly="readonly">&nbsp;(INR&nbsp;/&nbsp;MMBTU)</td>
						        </tr>
						        <tr>
							    <td colspan="1" class="info">&nbsp;Security<span class="s-red">*</span></td>
								    <td colspan="3"> 
										<!-- HARSH20210216 -->
										&nbsp;&nbsp;<select name="InOut" id="InOut" style="pointer-events: none; cursor: not-allowed;">
											<option value="Incoming">Incoming</option>
											<option value="Outgoing">Outgoing</option>
										</select>
										&nbsp;&nbsp;<input type="button" style="color:GREEN;" value="Security/Pre-Receipt " name="security" onclick="securityPreReceipt('S','<%=buyer_cd%>','<%=buyer_abbr%>','<%=FGSA_CD%>','<%=FGSA_REVNO%>','<%=SN_CD%>','<%=SN_REVNO%>','<%=START_DT%>','<%=END_DT%>','<%=TCQ%>','<%=DCQ%>','<%=formId%>','<%=formName%>',&quot;<%=buyer_name%>&quot;);">
										<input type="hidden" name="securityFlag" id="securityFlag" value="<%=securityFlag%>"><!-- HARSH20210218 -->
										<input type="hidden" name="dcqFlag" value="<%=DCQ%>"><!-- HARSH20210218 -->
										<input type="hidden" name="startDtFlag" value="<%=START_DT%>"><!-- HARSH20210218 -->
										<input type="hidden" name="endDtFlag" value="<%=END_DT%>"><!-- HARSH20210218 -->
								   </td>
						        </tr>
					    		<tr>
								     <td class="info">Variation</td>
								     <td>
									 <input class="mkRdly" type="text" name="variance_qty" size="5" value="<%=VARIATION_QTY%>" style="background:YELLOW;" onblur="checkNumber1(this,9,2);checkForNegNumber(this);">
									 Total (MMBTU) For Whole Supply Period
								     </td>
								</tr>
					    		<tr>
								     <td class="info">Formula Remark</td>
								     <td><input type="text" name="formula_remark" value="<%=FORMULA_REMARK%>" class="mkRdly" size="50" maxlength="200" style="background:YELLOW; style="height: 27px;">
									<input type="hidden" name="adv_amt" value="<%=adv_amt%>" size="10" maxlength="13" onBlur="checkNumber1(this,12,2);">&nbsp;</td>
								</tr>
								<tr>
								    <td class="info">Filling Station<span class="s-red">*</span></td>
								    <td>
									<%for(int i=0;i<transporter_cd.size();i++)
									  {%>
									  	<input type="checkbox" name="chk_trans" value="<%=transporter_cd.elementAt(i)%>" style=" width:20px;height: 15px;">&nbsp;&nbsp;<%=transporter_abbr.elementAt(i)%>&nbsp;&nbsp;
									<%}%>
								    </td>
								</tr>
							</table>
							</div>
							
						<div class="table-responsive col-lg-6">
							<table class="table table-striped" >									
								<tr>
									<td class="info">Customer's Name<span class="s-red">*</span></td>
								    <td> 
								    	<input name="buyer_abr" type="text" size="5" readOnly value="<%=buyer_abbr%>" style="height: 27px;">&nbsp;-
										<input class="select"  name="buyer_name" type="text"  readonly="readonly"  size="30"  value="<%=buyer_name%>">
										<input name="buyer_cd" type="hidden" value="<%=buyer_cd%>">
										<%	if(activity.equalsIgnoreCase("insert"))
											{%> <input type="button" class="btn-primary"  value="View List" name="Search2" onclick="fetchBuyerList();">
										<%	}%>
								   </td>
								</tr>
								<tr>
				    				<td class="info">Rev. Eff. Date</td>
				    				<td>
				    					<input name="rev_chk" type="checkbox" value="Y" title="Increment in Revision Number"  onclick="enableRevision();" >
				    					<label style="margin-left:5pt; font-size: 14px;">Apply Revision</label>
			     						<input class="select" name="rev_flag" type="hidden" value="N">
			     						<input class="select" type="text" name="rev_dt" id="datetimepicker2" maxlength="11" size="6" value="<%=REV_DT%>" onBlur="validateDate(this);" title="dd/mm/yyyy Format" style="height: 27px;" disabled>
				    				</td>
				    			</tr>
								<tr>
									<td colspan="1" class="info">Allocated QTY</td>
									<td colspan="5">
										<input class="select" type="text" name="allocated_qty" value="<%=ALLOCATED_QTY%>" size="10" maxlength="11" style="text-align:right" readonly="readonly">&nbsp;MMBTU
									</td>
								</tr>
								<tr>
									<td colspan="1" class="info">DDA Date<span class="s-red">*</span></td>
									<td colspan="5">
										<input type="text" name="dda_dt" autocomplete="off" id="datetimepicker9" value="<%=DDA_DT%>" maxlength="10" size="12" onblur="validateDate(this);" placeholder="DD/MM/YYYY" title="dd/mm/yyyy Format">
										<input type="text" name="dda_time" autocomplete="off" id="dda_time" value="<%=DDA_TIME%>" size="5" maxlength="5" onblur="validateTime(this);" placeholder="HH:MM"> HH:MM
									</td>
								</tr>
								<tr>
									<td class="info">DCQ:<span class="s-red">*</span></td>
									<td>
									 	<table>
											<tr>
												<td><input class="mkRdly" type=text name="dcq" id="dcq" maxlength="12" size="3" value="<%=DCQ%>" onchange="convToMT(this)" onBlur="checkNumber1(this,9,2);checkForNegNumber(this);checkDCQ(this);" style="background:YELLOW;height: 27px;text-align: right;" ></td>
												<td>(MMBTU)&nbsp;&nbsp;</td>													
												<td><input class="mkRdly" type=text name="mtd" id="mtd" maxlength="12" size="3" value="<%=DCQ_MT%>" onchange="convToDcq(this)" onBlur="checkNumber1(this,9,2);checkForNegNumber(this);checkDCQ(this);" style="background:YELLOW; height: 27px;text-align: right;"></td>
												<td>(MT)&nbsp;
												<%if(activity.equalsIgnoreCase("update") && SN_CD!=null && !SN_CD.equals("") && !SN_CD.equals(" ") && !SN_CD.equals("0"))
										  {%><input type="button" value="Variable DCQ" name="var_dcq" onclick="defineVariableDCQforSN('<%=buyer_cd%>','<%=FGSA_CD%>','<%=FGSA_REVNO%>','<%=SN_CD%>','<%=SN_REVNO%>','<%=START_DT%>','<%=END_DT%>',&quot;<%=buyer_name%>&quot;);" 
											  <%if(SN_CLOSURE_FLAG.equalsIgnoreCase("Y") || SN_CLOSURE_REQUEST.equalsIgnoreCase("Y")){%> disabled title = "<%=title%>"<%}%>>
										<%}%>
												</td>
											</tr>
										</table>
								     </td>
								</tr>
								<%if(activity.equalsIgnoreCase("update") && SN_CD!=null && !SN_CD.equals("") && !SN_CD.equals(" ") && !SN_CD.equals("0")) { %>
								<tr>
									<td class="info">SN Closure</td>
									<td>
										<table>
											<tr>
												<td>
													<input style="width:16px; height: 25px;" type="checkbox" name="sn_close_chk" value="Y" onClick="checkSNClosure();" <%if(SN_CLOSURE_FLAG.equalsIgnoreCase("Y") || SN_CLOSURE_CLOSE.equalsIgnoreCase("Y") || SN_CLOSURE_REQUEST.equalsIgnoreCase("Y") || FCC_flag.trim().equalsIgnoreCase("Disapproved") || FCC_flag.trim().equalsIgnoreCase("Pending")){%> disabled<%}%>>
													<input type="hidden" name="sn_close_flag" value="N">
												</td>
												<td>&nbsp;&nbsp;Eff. Dt.&nbsp;&nbsp;</td>
												<td><input type="text" name="sn_closure_dt" id="datetimepicker5" maxlength="10" size="10" value="<%=SN_CLOSURE_DT%>" onBlur="validateDate(this);checkDt('<%=date %>',this.value,'<%=SN_CLOSURE_DT%>','<%=START_DT %>');" title="dd/mm/yyyy Format" style="height: 27px;"></td>
												<td>
													<%
									     			if(SN_CLOSURE_FLAG.equalsIgnoreCase("Y")  || SN_CLOSURE_CLOSE.equalsIgnoreCase("Y") || SN_CLOSURE_REQUEST.equalsIgnoreCase("Y") || FCC_flag.trim().equalsIgnoreCase("Disapproved") || FCC_flag.trim().equalsIgnoreCase("Pending")){
									     			%> 
									     			<input type="button" name="sn_closure" value="SN Closure Request" disabled onClick="submitSNClosureRequest();"  title = "<%=title%>" >	     			
									     			<%
									     			}
									     			else
									     			{
									     			%>
									     			<input type="button" name="sn_closure" value="SN Closure Request" onClick="submitSNClosureRequest();" >	     			
									     			<%
									     			}
									     			%>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
					    			<td class="info">
					    				<input type="hidden" name="sn_closure_flag" value="<%=SN_CLOSURE_FLAG%>">
					     				<FONT COLOR="BLACK"><I>SN Closure Qty</I></FONT>
					     			</td>
					     			<td>	
					     				<input type="text" name="sn_closure_qty" readonly="readonly" size="5"  value="<%=SN_CLOSURE_QTY%>" style="height: 27px;">&nbsp;(MMBTU)
					    			</td>
						    	</tr>
								<tr>
									<td class="info"><table><tr><td>Quantity&nbsp;</td><td>Modification</td></tr></table></td>
									<td>
										<table>
									 		<tr>
										 		<td>TCQ:</td>
										 		<td><select name="tcq_sign" style="height: 27px;">
										 					<option value="+"> + </option>
										 					<option value="-"> - </option>
										 			</select>
										 		</td>
									 			<td><input type=text name="var_tcq" maxlength="11" size="5" value="" onBlur="checkNumber1(this,9,2);checkForNegNumber(this);" style="height: 27px;"></td>
									 			<td>&nbsp;(MMBTU)&nbsp;</td>
									 			<td><input type="button" <%if(tcq_req_flag.equalsIgnoreCase("Y") &&  tcq_req_close.equalsIgnoreCase("N")) {%>
									 					value="TCQ Modification Request"  name="modify_tcq" title="TCQ Modification Request Already Sent!!" 
									 				<%}else{ %>
									 					value="TCQ Modification Request" name="modify_tcq" onClick="submitTCQModificationRequest();" <%} %>
									 			<%
// 									 			System.out.println(SN_CLOSURE_FLAG+"---"+SN_CLOSURE_CLOSE+"---"+SN_CLOSURE_REQUEST+"----"+FCC_flag+"----");
									 			if(SN_CLOSURE_FLAG.equalsIgnoreCase("Y")  || SN_CLOSURE_CLOSE.equalsIgnoreCase("Y") || SN_CLOSURE_REQUEST.equalsIgnoreCase("Y")  || FCC_flag.trim().equalsIgnoreCase("Disapproved") || FCC_flag.trim().equalsIgnoreCase("Pending") || (tcq_req_flag.equalsIgnoreCase("Y") && tcq_req_close.equalsIgnoreCase("N"))){%> disabled title = "<%=title%>" <%}%> 
									 			></td>
									 		</tr>
									 	</table>
									</td>	
								</tr>
								<%} %>
								<tr>
									<td class="info">SN Ref. NO</td>
								    <td><input class="select" name="sn_ref_no" type="text" size="10" maxlength="10" value="<%=SN_REF_NO%>" style="background:YELLOW; style="height: 27px;">&nbsp;</td>
								</tr>
								<tr>
									<td class="info">Previous SN Ref. NO</td>
					    			<td><input class="select" name="pre_sn_ref_no" type="text" size="10" maxlength="10" value="<%=PRE_SN_REF_NO%>" class="mkrdly" readonly="readonly" style="height: 30px;"></td>						
								</tr>
								<tr>
								     <td class="info">Remarks</td>
								     <td><input type="text" name="remark" value="<%=REMARK%>" size="15" maxlength="400" style="background:YELLOW; style="height: 27px;">&nbsp;
								     	<select name="sn_remark" class="select">
										  <option value="0">--Select--</option>
										  <option value="1">Reasonable Endeavor</option>
										  <option value="2">Amendment in Contract</option>
										  <option value="3">Force Majeure</option>
										  <option value="4">Others</option>				
										</select>
										<script>document.forms[0].sn_remark.value = "<%=SN_REMARK%>";</script>	
									</td>
								</tr>
								<tr>
									<td class="info"><label>Split TCQ</label></td>
									<td>
										<table>
											<tr>
												<td><input  type="checkbox" value="<%=split_tcq_flag%>" name="split_tcq" title="Click to Enter FIRM & RE QTY" onclick="enableTextTCQ(this);" ></td>
													<script>
														function checkTCQ(obj,value)
														{
															var tcq_val = document.forms[0].tcq.value;
															if(obj.value > parseFloat(tcq_val))
															{
																alert("Value can not be greater than TCQ!!!!");
																obj.value = '';
																obj.focus();
															} 
															else 
															{	
																var firm_val = 0;
																var re_val = 0;
																if(value=='1')
																{
																	re_val = document.forms[0].re_qty.value;
																	firm_val = obj.value;
																	if(obj.value == '' || obj.value == ' ')
																	{
																		obj.value = 0;
																	} 
																		document.forms[0].re_qty.value = parseFloat(tcq_val) - parseFloat(obj.value);
																}
																else
																{
																	firm_val = document.forms[0].firm_qty.value;
																	re_val = obj.value;
																	if(obj.value == '' || obj.value == ' ')
																	{
																		obj.value = 0;
																	} 
																		document.forms[0].firm_qty.value = parseFloat(tcq_val) - parseFloat(obj.value);
																}
															}
														}
														function enableTextTCQ(obj)
														{
															if(obj.checked)
															{
																obj.value='Y';
																document.forms[0].firm_qty.disabled = false;
																document.forms[0].firm_qty.className = '';
																document.forms[0].re_qty.disabled = false;
																document.forms[0].re_qty.className = '';
															} else {
																obj.value='N';
																document.forms[0].firm_qty.disabled = true;
																document.forms[0].firm_qty.className = 'mkrdly';
																document.forms[0].re_qty.disabled = true;
																document.forms[0].re_qty.className = 'mkrdly';
																document.forms[0].firm_qty.value='';
																document.forms[0].re_qty.value='';
															}	
														}
												</script>														
													
															<td>&nbsp;Firm QTY<input onBlur="checkNumber1(this,10,2);checkForNegNumber(this);checkTCQ(this,'1');" style="text-align: right;" size="5" type="text" name="firm_qty" id="firm_qty" value="<%=firm_qty%>" class="mkrdly" disabled="disabled" ></input>(MMBTU)
															&nbsp;RE QTY<input type="text" onBlur="checkNumber1(this,10,2);checkForNegNumber(this);checkTCQ(this,'2');" size="5" style="text-align: right;" name="re_qty" id="re_qty" value="<%=re_qty%>" class="mkrdly" disabled="disabled" ></input>&nbsp;(MMBTU)</td>			
														
							
												<%if(split_tcq_flag.equals("Y")) { %>
													<script>
														document.forms[0].split_tcq.checked = true;
														document.forms[0].firm_qty.disabled = false;
														document.forms[0].firm_qty.className = '';
														document.forms[0].re_qty.disabled = false;
														document.forms[0].re_qty.className = '';
													</script>	
												<% } %>
											</tr>
										</table>
									</td>
								</tr>
								
							<tr>
								<td class="info" width="30%" >Agreement Base<span class="s-red">*</span></td>
			    				<td TITLE="FLSA based: <%=agree_base %>">
							     	<input type="radio" name="snBase" value="X" onclick="hideShowTransCharge('X','<%=buyer_plant_seq_no.size() %>');" <%if(sn_base.equalsIgnoreCase("X")){ %> checked="checked" <%} %> >&nbsp;Ex-Terminal
									&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="snBase" value="D" onclick="hideShowTransCharge('D','<%=buyer_plant_seq_no.size() %>');" <%if(sn_base.equalsIgnoreCase("D")){ %> checked="checked" <%} %> >&nbsp;Delivery
									<input type="hidden" name="agrtyp" value="<%=sn_base %>">
			    				</td>
							</tr>
							</table>
						</div>
						
						<div class="table-responsive col-lg-12">
								<table class="table table-striped" >
									<tr>
									    <td class="info">Plant(s)<span class="s-red">*</span></td>
									    <td >
										    <%for(int i=0;i<buyer_plant_seq_no.size();i++){%>
										    	<table>
											    	<tr>
											    		<td >
											    			<input  type="checkbox" name="chk_delv" id="chk_delv<%=i %>" value="<%=buyer_plant_seq_no.elementAt(i)%>" style=" width:20px;height: 15px;" onclick="setChkFlag('<%=buyer_plant_seq_no.size() %>');">&nbsp;&nbsp;<%=buyer_plant_nm.elementAt(i)%>
											    			<input type="hidden" name="chk_flg" id="chk_flg<%=i %>" value="">
											    			<input type="hidden" name="plant_seq_no" id="plant_seq_no<%=i %>" value="<%=buyer_plant_seq_no.elementAt(i)%>">
											    			
											    			<div <%if(!sn_base.equalsIgnoreCase("D")){ %>style="display: none;" <%} %> id="plant_trans_charge<%=i%>">
												    			<font size="2" color="blue">Transportation Charge: </font> 
												    			<font size="2" color="black">
												    				INR/MMBTU : <input type="text" size="3" name="inr_mmbtu"  id= "inr_mmbtu<%= i%>" value="<%=vsn_plant_inr_mmbtu.elementAt(i)%>">
												    				&nbsp;&nbsp;INR/KM : <input type="text" size="3" name="inr_km" id= "inr_km<%= i%>" value="<%=vsn_plant_inr_km.elementAt(i)%>">									
												    				</font>
												    				<br>
												    				<font size="2" color="blue">LumpSum: &nbsp;&nbsp;</font> 
													    			<font size="2" color="black">	
													    			<input type="button" 
													    			 
													    			<%if(vsn_plant_lumpsum_flg.elementAt(i).toString().equalsIgnoreCase("Y")){ %>
													    				value="Active"
													    				class="btn btn-success btn-xs"
													    			<%}else{ %>
													    				value="In-Active"
													    				class="btn  btn-danger btn-xs"
													    			<%} %>
													    			size="5"  id="lumpumBtn" onclick="toggleBtn(this,'<%=i%>');"></font>
													    			<input type="hidden" name="lumpsumFlg"
													    			 <%if(vsn_plant_lumpsum_flg.elementAt(i).toString().equalsIgnoreCase("Y")){ %>
													    				value="Y"
													    			<%}else{ %>
													    				value="N"
													    			<%} %>
													    			  id="lumpsumFlg<%=i %>">
											    			</div>	
											    		</td>
											    	</tr>
												</table> 
												
										    <%}%>
									    </td>
									</tr>
									<tr>
									    <td class="info">Transporter(s)<span class="s-red">*</span></td>
									    <td>
										    <%for(int i=0;i<VTruck_Trans_Cd.size();i++){%>
											<input  type="checkbox" name="chk_trans_truck" value="<%=VTruck_Trans_Cd.elementAt(i)%>" style=" width:20px;height: 15px;">&nbsp;&nbsp;<%=VTruck_Trans_Abr.elementAt(i)%> 
										    <%}%>
									    </td>
									</tr>
								</table>
							</div>
							
							<div class="table-responsive col-lg-12" id="trans_cont_div"
								<%if(sn_base.equalsIgnoreCase("D")){ %>
									style="display: block;" 
								<%}else{ %>
									style="display: none;"
								<%} %>
								>
								<table class="table table-striped" border="1">
									<tr><th align="center" class="info text-center" colspan="12"><b style="font-size: 17px;font-style: italic;">Transporter Service Details</b></th></tr>
									
									<tr class="info text-center" >
										<th colspan="3" rowspan="1" class="text-center">Service Contract</th>
										<th colspan="1" rowspan="2" style="vertical-align: middle;" class="text-center">Service  Contract No.<span class="s-red">*</span></th>
										<th colspan="2" rowspan="1" style="vertical-align: middle;" class="text-center" title="to be provided for delivery of LNG during completion of SN contract">
											<input type="radio" name="trans_cont_rad" value = "truck" onclick="setBif(this);"
											<%if(trans_cont_rad_flg.equals("T")){ %> checked="checked" <%} %> >		
											No. Of Truck(s)
										</th>
										<th colspan="2" rowspan="1" style="vertical-align: middle;" class="text-center" title="DLNG to be allowed for delivery using Tank Truck during service contract period">
											<input type="radio" name="trans_cont_rad" value = "qty" onclick="setBif(this);" <%if(trans_cont_rad_flg.equals("Q")){ %> checked="checked" <%} %>>	
											Total Quantity
										</th>
										<input type="hidden" name = "trans_cont_rad_flg" id  = "trans_cont_rad_flg" value = "">		
									</tr>
									<tr class="info text-center" style="vertical-align: middle;">
										<th class="text-center">Signing Date<span class="s-red">*</span></th>
										<th class="text-center">Start Date<span class="s-red">*</span></th>
										<th class="text-center">End Date<span class="s-red">*</span></th>
										<th class="text-center">Firm</th>
										<th class="text-center">RE</th>
										<th class="text-center">Firm</th>
										<th class="text-center">RE</th>
									</tr>
									
									<tr style="background-color: #d1dcdc;" class="text-center">
										<td><input class="text-center" size="15" autocomplete="off" type=text name="trans_cont_sing_dt"  id="datetimepicker8" maxlength="10"   onBlur="validateDate(this);" title="dd/mm/yyyy Format"  value="<%=trans_cont_sign_dt%>"></td>
										<td><input class="text-center" size="15" autocomplete="off" type=text name="trans_cont_st_dt"  id="datetimepicker6" maxlength="10"   onBlur="validateDate(this);" title="dd/mm/yyyy Format" onchange="checkTransDt();" value="<%=trans_cont_st_dt%>"></td>
										<td><input class="text-center" size="15" autocomplete="off" type=text name="trans_cont_end_dt"  id="datetimepicker7" maxlength="10"   onBlur="validateDate(this);" title="dd/mm/yyyy Format" onchange="checkTransDt();" value="<%=trans_cont_end_dt%>"></td>
										<td><input class="text-center" type="text" name="trans_cont_no" id="trans_cont_no" size="15" value="<%=trans_cont_no%>"></td>
										
										<td><input class="text-right" type="text" readonly="readonly" name="truck_fm" id="truck_fm" size="8" onkeyup="checkNum(this);" value="<%=truck_fm%>" ></td>
										<td><input class="text-right" type="text" readonly="readonly" name="truck_re" id="truck_re" size="8" onkeyup="checkNum(this);" value="<%=truck_re%>"></td>
										<td><input class="text-right" type="text" readonly="readonly" name="qty_fm" id="qty_fm" size="8" onkeyup="checkNum(this);" value="<%=qty_fm%>"></td>
										<td><input class="text-right" type="text" readonly="readonly" name="qty_re" id="qty_re" size="8" onkeyup="checkNum(this);" value="<%=qty_re%>"></td>
									</tr>
								</table>
							</div>		       
						</div>
			
					<%if(!SN_CD.equalsIgnoreCase("")){ %>
						<div class="row" >
							<div class="table-responsive col-lg-12">
								<table class="table table-striped" >
						    		<tr><td align="center" class="info"><b style="font-size: 17px;font-style: italic;">Governing Clauses</b></td></tr>
						    	</table>
						 	</div>
						 	
						 	<div class="table-responsive col-lg-7">
						 		<table class="table table-striped" >
						    		<tr>
						    			<td class="info"  colspan="2">
						    				<input  type="checkbox" name="buyer_nom" id="nom" value="Y" checked onclick="checkBoxValidation(this);" style="height: 15px;">&nbsp;&nbsp;Buyer Nomination
						    			</td>
						    			<td>
						    				<table>
						    					<tr>
						    						<td><input disabled type="checkbox" name="chk_buyer_nom" value="M" 
							     	   								<%if(BUYER_MONTH_NOM.equalsIgnoreCase("Y")){%>checked<%}%> style="height: 15px;">
				     	   							</td>
				     	   							<td>
				     	   								&nbsp;&nbsp; Monthly
				     	   							</td>
						    					</tr>
						    				</table>
						    			</td>
						    			<td>
						    				<table>
						    					<tr>
						    						<td><input  type="checkbox" name="chk_buyer_nom" value="W" 
							     	   						<%if(BUYER_WEEK_NOM.equalsIgnoreCase("Y")){%>checked<%}%> style="height: 15px;">
				     	   							</td>
				     	   							<td>&nbsp;&nbsp;Weekly</td>
						    					</tr>
						    				</table>
						    			</td>
						    			<td>
						    				<table>
						    					<tr>
						    						<td><input type="checkbox" name="chk_buyer_nom" value="D" 
							     							<%if(BUYER_DAILY_NOM.equalsIgnoreCase("Y")){%>checked <%}%> onclick="checkBoxValidation(this);" style="width:20px;height: 15px;">
				     	   							</td>
				     	   							<td>&nbsp;&nbsp;Daily</td>
						    					</tr>
						    				</table>
						    			</td>
						    		</tr>
						    		<tr>
							    		<td class="info"  colspan="2"><input  type="checkbox" name="day_def" value="Y" 
					                  		<%if(DAY_DEF.equalsIgnoreCase("Y")){%>checked <%}%> onclick="checkBoxValidation(this);" style="height: 15px;">
					                 		&nbsp;Day Definition
							     		</td>
							     		<td>
							     			<table>
						    					<tr>
						    						<td>From:&nbsp;<input type="text" name="day_time_from" value="<%=DAY_START_TIME %>" size="3" maxlength="5" onblur="validateTime(this);" style="height: 27px;">&nbsp;Hrs:Mins</td >
						    					</tr>
						    				</table>
							     		</td>
							     		<td>
							     			<table>
						    					<tr>
						    						<td>To:&nbsp;<input type="text" name="day_time_to" value="<%=DAY_END_TIME %>" size="3" maxlength="5" onblur="validateTime(this);" style="height: 27px;">&nbsp;Hrs:Mins</td>
						    					</tr>
						    				</table>
							     		</td>
							     		<td></td>
							     	</tr>
							     	<tr >
							    		<td class="info"  colspan="2"><input  type="checkbox" name="measurement" value="Y" 
							    	 		<%if(MEASUREMENT.equalsIgnoreCase("Y")){%>checked <%}%> onclick="MeasurementQreadOnly();" style="height: 15px;">&nbsp;&nbsp;Measurement
							    		</td>
							    		<td>
							    			<table>
													<tr>
														<td>Standard&nbsp;</td>
											    		<td><input type="text" id="std" name="standard" value="<%=MEAS_STANDARD%>" size="8" maxlength="15" 
											     			<%if(!MEASUREMENT.equalsIgnoreCase("Y")){%>readOnly<%}%>>
											    		</td>
													</tr>
											</table>
							    		</td>
							    		<td>
							    			<table>
													<tr>
														<td>Temperature&nbsp;</td>
											    		<td><input type="text" id="temp" name="temprature" value="<%=MEAS_TEMPERATURE%>" size="4" maxlength="5" 
											     			<%if(!MEASUREMENT.equalsIgnoreCase("Y")){%> readOnly<%}%>>
							    						</td>
													</tr>
											</table>
							    		</td>
							    		<td><table><tr><td>Degree</td><td>&nbsp;Centigrade</td></tr></table></td>
							    	</tr>
							    	
							    	<tr >
							     		<td class="info"  colspan="2"><strong> Delivery Pressure</strong></td>
							    		<td>
							    			<table>
							    				<tr>
							    					<td>
							    						<table><tr><td>Rate&nbsp;</td><td>Min&nbsp;</td><td>Bar&nbsp;</td></tr></table>
							    					</td>
							    					<td><input type="text" id="rate_min" name="rate_min_bar" value="<%=PRESSURE_MIN_BAR%>" size="3" maxlength="6" onblur="checkNumber1(this,5,2);" 
							     						  <%if(!MEASUREMENT.equalsIgnoreCase("Y")){%>readOnly<%}%>></td>
							    				</tr>
							    			</table>
							    		</td>
							    		<td>
							    			<table>
							    				<tr>
							    					<td><table><tr><td>Rate&nbsp;</td><td>Max&nbsp;</td><td>Bar&nbsp;</td></tr></table></td>
							    					<td><input type="text" id="rate_max" name="rate_max_bar" value="<%=PRESSURE_MAX_BAR%>" size="4" maxlength="6" onblur="checkNumber1(this,5,2);" 
				     	    							<%if(!MEASUREMENT.equalsIgnoreCase("Y")){%>readOnly<%}%>></td>
							    				</tr>
							    			</table>
							    		</td>
							    		<td></td>
							    		<td></td>
							     	</tr>
				    				<tr>
							    		<td class="info"  colspan="2"> 
							    		<input  type="checkbox" name="off_spec_gas_chk" value="Y" 
							    			<%if(OFF_SPEC_GAS.equalsIgnoreCase("Y")){%>checked <%}%> onclick="offSpecreadOnly();" style="height: 15px;">&nbsp;&nbsp;Off Spec Gas
							    		</td>
							    		<td>
							    			<table>
							    				<tr>
							    					<td><table><tr><td>Energy&nbsp;</td><td>Base</td></tr></table></td>
							    					<td>
							    						<select name="energy_off_spec" style="height: 27px;">
										     				<option value="0">--Select--</option>
										     				<option value="1">GCV</option>
										     				<option value="2">NCV</option>
										     			</select>
							    					</td>
							    				</tr>
							    			</table>
							     		</td>
							     		<td>
							     			<table>
							     				<tr>
							     					<td>Min</td>
							     					<td>
							     						<input type="text" id="off_spec_min_energy" name="min_energy" value="<%=SPEC_GAS_MIN_ENERGY%>" size="5" maxlength="8" onblur="checkNumber1(this,7,2);" 
				     										<%if(!OFF_SPEC_GAS.equalsIgnoreCase("Y")){%>readOnly<%}%> style="">
							     					</td>
							     					<td>(Kcal/SCM)</td>
							     				</tr>
							     			</table>
							     		</td>
							     		<td>
							     			<table>
							     				<tr>
							     					<td>Max</td>
							     					<td>
							     						<input type="text" id="off_spec_max_energy" name="max_energy" value="<%=SPEC_GAS_MAX_ENERGY%>" size="5" maxlength="8" onblur="checkNumber1(this,7,2);" 
				     										<%if(!OFF_SPEC_GAS.equalsIgnoreCase("Y")){%>readOnly<%}%>>
							     					</td>
							     					<td>(Kcal/SCM)</td>
							     				</tr>
							     			</table>
							     		</td>
							    	</tr>	
							    	<tr >
							 			<td  class="info" colspan="2">
				 								<input title="Click to Change the Clause value" type="checkbox" name="advance_collection" onclick="enableFlag();" >&nbsp;&nbsp;Advance Amount
				 								<input type="hidden" name="advAmtFlg" value="N">
			 							</td>	
			 							<td>
			 								<table>
			 									<tr>
			 										<td>
			 											<select name="advance_collection_flag" disabled="disabled" style="height: 30px;">
										    				<option value="Y">Yes</option>
										    				<option value="N">No</option>
										    			</select>
											    	</td>
			 									</tr>
			 								</table>
			 							</td>
						 				<td>
						 					<table>
			 									<tr>
			 										<td>Currency&nbsp;&nbsp;</td>
			 										<td>
			 											<select name="adv_cur_flg"  style="height: 30px;">
			 												<option value="0">-Select-</option>
											    			<option value="1">INR</option>
											    			<option value="2">USD</option>
											    		</select>
											    		
											    	</td>
<!-- 											    	<td><span><font color="red" style="font-style: italic;marg"><b>&nbsp;&nbsp;NEW!!</b></font></span></td> -->
			 									</tr>
			 								</table>
			 							</td>
						 			</tr> 					 		
							 		<%if(component_cd.contains("1")) { %>
							 			<tr >
							 				<td  class="info" colspan="2">
				 								<input   type="checkbox" name="advance" onclick="enableText();" style="height: 15px;" <%if(fcc_act_flag.equals("N")){%> disabled="disabled" title="FCC Pending.." <%}else{ %>title="Click to Change the Clause value" <%} %>>&nbsp;&nbsp;Advance Collection
				 							</td>
				 							
				 							<td colspan="4" >
				 								<table >
				 									<tr>
				 									
				 										<td><a href="#" style="color:#217C7E;" onclick="setAdvCollect('<%=FGSA_CD%>','<%=FGSA_REVNO%>','<%=bscode%>','<%=SN_CD%>','<%=SN_REVNO%>','S');" ><strong><u>Modify</u></strong></a></td>
				 										
				 										<td >
				 											 &nbsp;&nbsp;&nbsp;Advance Amt.<br>&nbsp;&nbsp;&nbsp;<input readonly="readonly" type="text" style="text-align: right;height: 30px;"  name="advance_amount" value="<%=adjust_amt%>" size="8" disabled="disabled" onBlur="checkNumber1(this,22,4),checkForNegNumber(this);">
				 										</td>
				 										<%if(SECURITY_FLAG_SN.equalsIgnoreCase("Y")) {%>
				 										<td>	
				 											 &nbsp;&nbsp;&nbsp;Security Amt.<br>&nbsp;&nbsp;&nbsp;<input readonly="readonly" type="text" style="text-align: right;height: 30px;"  name="security_amount" value="<%=SECURITY_AMT_SN%>" size="8"  onBlur="checkNumber1(this,22,4),checkForNegNumber(this);">&nbsp;&nbsp;&nbsp;
				 										</td>
				 										<%} %>
				 										<%if(LUMPSUM_FLAG_SN.equalsIgnoreCase("Y")) {%>
				 										<td>&nbsp;&nbsp;&nbsp;Lump-Sum Amt.<br>&nbsp;&nbsp;&nbsp;<input readonly="readonly" type="text" style="text-align: right;height: 30px;"  name="lumpsum_amount" value="<%=LUMPSUM_AMT_SN%>" size="8" onBlur="checkNumber1(this,22,4),checkForNegNumber(this);"></td>
				 										<%} %>
				 										<td>&nbsp;&nbsp;&nbsp;Currency<br>
				 											&nbsp;&nbsp;&nbsp;<select name="advance_cur"  style="height: 30px;">
												    			<option value="1">INR</option>
												    			<option value="2">USD</option>
												    		</select>
												    	</td>
				 									</tr>
				 								</table>
				 							</td>
							 			</tr>	
							 		<%}	%>
							 		<tr >
							 				<td  class="info" colspan="3">
				 								<b>Finance Checks &amp; Control</b>
				 							</td>	
				 							<td>
				 								<table>
				 									<tr>
				 										<td><font size="2" color="blue"><b>&nbsp;&nbsp;&nbsp;&nbsp;<%=FCC_flag%></b></font></td>
				 									</tr>
				 								</table>
				 							</td>
							 			</tr> 
				    			</table>
						 	</div>
				 	
						 	<div class="table-responsive col-lg-5">
						 		<table class="table table-striped" >
				    				<tr>
				    					<td class="info" width="35%">
				    						<input  type="checkbox" name="seller_nom" value="Y" checked onclick="checkBoxValidation(this);" style="width:20px;height: 15px;">&nbsp;&nbsp;Seller Nomination
				    					</td>
				    					<td>
							     		  	<input  type="checkbox" name="chk_seller_nom" value="M" 
							     	   		<%if(SELLER_MONTH_NOM.equalsIgnoreCase("Y")){%>checked<%}%> style="width:20px;height: 15px;">&nbsp;Monthly
							     		</td>
							     		<td>
							     	   	   	<input  type="checkbox" name="chk_seller_nom" value="W" 
							     	   		<%if(SELLER_WEEK_NOM.equalsIgnoreCase("Y")){%>checked<%}%> style="width:20px;height: 15px;">&nbsp;Weekly
							     		</td>
							     		<td>
							     	     	<input  type="checkbox" name="chk_seller_nom" value="D" 
							     			<%if(SELLER_DAILY_NOM.equalsIgnoreCase("Y")){%>checked <%}%> onclick="checkBoxValidation(this);"  style="width:20px;height: 15px;">&nbsp;Daily
							            </td>
				    				</tr>
				    				<tr>
							    		<td class="info"> 
							     			<input  type="checkbox" name="mdcq" value="Y" 
							     	  		<%if(MDCQ.equalsIgnoreCase("Y")){%>checked <%}%> onclick="MDCQreadOnly();" style="width:20px;height: 15px;">&nbsp;&nbsp;MDCQ
							     		</td>
							     		<td><input  type="text" name="mdcq_percent" id="mdcqPer" value="<%=fgMDCQ_PERCENTAGE%>" onblur="checkNumber1(this,5,2);checkMdcq();" size="3" maxlength="5" style="height: 30px;">&nbsp;%&nbsp;of</td>
							     		<td>
							     			<select name="mdcq_qty_unit" style="height: 30px;">
							     				<option value="1">DCQ</option>
							     			</select>
							     		</td>
							     		<td></td>
							     	</tr>
							     	
							     	<%for(int i=0;i<clause_cd.size();i++){ %> 
										<tr >
							   				<td class="info"><input  type="checkbox" name="clause_nm" value="<%=(String)clause_cd.elementAt(i)%>" style="width:20px;height: 15px;">&nbsp;&nbsp;<%=(String)clause_nm.elementAt(i)%></td>
							   				<td>
								   					<%if((""+clause_nm.elementAt(i)).trim().equalsIgnoreCase("Billing Details") || (""+clause_nm.elementAt(i)).trim().equalsIgnoreCase("Liability") || (""+clause_nm.elementAt(i)).trim().equalsIgnoreCase("Letter of Credit"))
			     			  						{%> <a href="#" style="color:#217C7E;" onclick="openBillingDetail('<%=(String)clause_cd.elementAt(i)%>','<%= (String)clause_nm.elementAt(i)%>','<%=FGSA_CD%>','<%=FGSA_REVNO%>','<%=bscode%>','<%=SN_CD%>','<%=SN_REVNO%>');"><strong><u>Modify</u></strong></a>
				     								<%}else{%>
				     									&nbsp;
				     								<%}%>
				     						</td>
										</tr>
							 		<%	} %>
							 		<%if(component_cd.contains("3")) { %>
							 		 <tr>
								 		<td title="Clause For Tariff" class="info">
									 		<input  title="Click to Change the Clause value" type="checkbox" name="tariff_inr" onclick="enableTextInrTariff();" style="width:20px;height: 15px;">&nbsp;&nbsp;Tariff in INR</td>
						 					
										<td><input type="text" style="text-align: right;" title="Click on Check Box to Enter Tariff!!" name="tariff_inr_amount" value="<%=tariff_amt%>" size="10" disabled="disabled" onBlur="checkNumber1(this,8,4),checkForNegNumber(this);"></td>
				    					<td>(INR/MMBTU)</td>
<!-- 				    					<td>&nbsp;&nbsp;<span><font color="red" style="font-style: italic;"><b>NEW!!</b></font></span></td>	 -->
				 					</tr>
				 					<%} %>
				 					
				 					<% 
// 				 					System.out.println("advance_collection-----"+advance_collection);
				 					if(Adjust_flag_SN.equalsIgnoreCase("Y") && (fcc_act_flag.equals("Y"))) { %>
										<script>
											document.forms[0].advance.disabled=false;
											document.forms[0].advance.value='Y';
											document.forms[0].advance.checked=true;
								 			document.forms[0].advance_amount.disabled=false;
								 			document.forms[0].security_amount.disabled=false;
								 			document.forms[0].lumpsum_amount.disabled=false;
								 			document.forms[0].advance_cur.disabled=false;
								 			document.forms[0].advance_cur.value='<%=adjust_cur%>';
								 			
										</script>
										<% }else{%>
											<script>
												document.forms[0].advance.disabled=true;
											</script>
										<%}
				 					if(discount_flag.equalsIgnoreCase("Y")) { %>
										<script>
											document.forms[0].discount.value='Y';
											document.forms[0].discount.checked=true;
								 			document.forms[0].discount_amount.disabled=false;
										</script>
										<% }if(tariff_flag.equalsIgnoreCase("Y")) { %>
										<script>
											document.forms[0].tariff_inr.value='Y';
								 			document.forms[0].tariff_inr_amount.disabled=false;
											document.forms[0].tariff_inr.checked=true;
										</script>
										<% } if(advance_collection.equalsIgnoreCase("Y")) { %>
										<script>
												document.forms[0].advance_collection.checked=true;
												document.forms[0].advAmtFlg.value='Y';
												document.forms[0].advance_collection.value='Y';
								 				document.forms[0].advance_collection_flag.disabled=false;
								 				document.forms[0].advance_collection_flag.value='<%=advance_collection_flag%>';
								 				document.forms[0].adv_cur_flg.value='<%=adv_cur_flg%>';
// 								 				document.forms[0].adv_cur_flg.disabled=true;
								 		</script>
									 		<%if(fcc_act_flag.equals("Y") && advance_collection_flag.equals("Y")){ %>
									 		<script>
												document.forms[0].advance.disabled=false;
											</script>
									 		<%} %>
										<% }else{ %>
										<script>
											document.forms[0].advance.disabled=true;
										</script>
										<%} %>
								 		<script>
								 		function enableTextInrTariff()
								 		{
								 			if(document.forms[0].tariff_inr.checked)
								 			{
								 				document.forms[0].tariff_inr.value='Y';
								 				document.forms[0].tariff_inr_amount.disabled=false;
								 			}
								 			else
								 			{
								 				document.forms[0].tariff_inr.value='N';
								 				document.forms[0].tariff_inr_amount.disabled=true;
								 			}
								 		}
								 		function enableTextDiscount()
								 		{
								 			if(document.forms[0].discount.checked)
								 			{
								 				document.forms[0].discount.value='Y';
								 				document.forms[0].discount_amount.disabled=false;
								 			}
								 			else
								 			{
								 				document.forms[0].discount.value='N';
								 				document.forms[0].discount_amount.disabled=true;
								 			}
								 		}
								 		function enableText()
								 		{
								 			if(document.forms[0].advance.checked)
								 			{
								 				document.forms[0].advance.value='Y';
								 				document.forms[0].advance_amount.disabled=false;
								 				document.forms[0].security_amount.disabled=false;
									 			document.forms[0].lumpsum_amount.disabled=false;
								 				document.forms[0].advance_cur.disabled=false;
								 			}
								 			else
								 			{
								 				document.forms[0].advance.value='N';
								 				document.forms[0].advance_amount.disabled=true;
								 				document.forms[0].security_amount.disabled=true;
									 			document.forms[0].lumpsum_amount.disabled=true;
								 				document.forms[0].advance_cur.disabled=true;
								 			}
								 		}
								 		function enableFlag()
								 		{
								 			if(document.forms[0].advance_collection.checked)
								 			{
								 				document.forms[0].advance_collection.value='Y';
								 				document.forms[0].advAmtFlg.value='Y';
								 				document.forms[0].advance_collection_flag.disabled=false;
								 			}
								 			else
								 			{
								 				document.forms[0].advance_collection.value='N';
								 				document.forms[0].advAmtFlg.value='N';
								 				document.forms[0].advance_collection_flag.disabled=true;
								 			}
								 		}
								 		function enableRevision()
								 		{
								 			if(document.forms[0].rev_chk.checked)
								 			{
								 				document.forms[0].rev_flag.value='Y';
								 				document.forms[0].rev_dt.disabled=false;
								 			}
								 			else
								 			{
								 				document.forms[0].rev_flag.value='N';
								 				document.forms[0].rev_dt.disabled=true;
								 			}
								 		}
								 		
								 		</script>	
				    			</table>
						 	</div>
						</div>
						<div class="col-xs-12">
						  	<div class="table-responsive">
						    	<table class="table table-striped">
						    		<tr>
							    		<td align="right"><span class="s-red" style="font-size: 14px;">*-Mandatory Information</span></td>
							    	</tr>
						    		<tr>
				    					<td align="right">
				    						<table >
				    							<tr>
				    								<td>
				    									<%if(activity.equals("update")){%>
								    						<button type="button" class="btn btn-warning" name="reset" value="Reset" onclick="refreshpage();"> Reset </button>&nbsp;&nbsp;&nbsp;&nbsp;
								    					<%} %>
				    								</td>
				    								<td>
								    					<%if(SN_CLOSURE_FLAG.equalsIgnoreCase("Y")  || SN_CLOSURE_CLOSE.equalsIgnoreCase("Y") || SN_CLOSURE_REQUEST.equalsIgnoreCase("Y")){%>
																<input  type="button" class="btn btn-success" name="save" value="Submit" disabled="disabled" 
																<%if(FCC_flag.equalsIgnoreCase("Approved")){ %>
																	title="SN closed">
																<%}else{ %>
																	title="SN closure requested">
																<%} %>	
														<%}else{%>
																<%if(write_permission.trim().equalsIgnoreCase("Y")) 
																  {%> 
																  <input type="button" name="save" class="btn btn-success" value="Submit" onClick="checkData('1');">
																<%}
						 										  else 
						 										  {%>
						 										  <input type="button" name="save" class="btn btn-success" value="Submit" disabled="disabled"> 
																<%}%>
														<%}%>	
								    				</td>
				    							</tr>
				    						</table>
					    				</td>	
				    				</tr>
				    			</table>
				    		</div>
				    	</div>
						
					 <%}else{%>
					 
						 <div class="col-xs-12">
						  	<div class="table-responsive">
						    	<table class="table table-striped" >
						    		<tr class="info">
				    					<th class="text-center">Please, First Select SN No From The View List PopUp Window ...</th>
				    				</tr>
				    			</table>
				    		</div>
					    </div>				
					<%} %>  
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<%}catch(Exception e){
	e.printStackTrace();
	
} %>
<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
<input type="hidden" name="formName" value="<%=formName%>">
<input name="option" type="hidden" value="SN_Master">
<input type="hidden" name="currentDate" value="<%=date%>">
<input type="hidden" name="flg" value="<%=activity%>">
<input type="hidden" name="submitFlag" value="">
<input type="hidden" name="rNewDt" value="">
<input type="hidden" name="fcc_flag" value="<%=fcc_flag%>">
<input type="hidden" name="fcc_by" value="<%=fcc_by%>">
<input type="hidden" name="fcc_date" value="<%=fcc_date%>">
<input type="hidden" name="FGSANO" value="<%=FGSA_CD%>">
<input type="hidden" name="FGSA_REVNO" value="<%=FGSA_REVNO%>">
<input type="hidden" name="SNNO" value="<%=SN_CD%>">
<input type="hidden" name="SN_REVNO" value="<%=SN_REVNO%>">
<input type="hidden" name="BUYER_NO" value="<%=buyer_cd %>">
<input type="hidden" name="dcq_var" value="<%=DCQ_FLAG%>">
<input type="hidden" name="billFlag" value="<%=billFlag%>">
<input type="hidden" name="Price_req_flag" value="<%=Price_req_flag%>">
<input type="hidden" name="tcq_req_flag" value="<%=tcq_req_flag%>">
<input type="hidden" name="tcq_req_close" value="<%=tcq_req_close%>">

<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
<input type="hidden" name="audit_permission" value="<%=audit_permission%>">

</form>
<!-- jQuery -->
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script>
$(function () {
var k= 9;
for(var i=1;i<=k;i++) {		
$('#datetimepicker'+i).datepicker({
changeMonth: true,
changeYear: true,
format: "dd/mm/yyyy",
language: "en",
orientation: "bottom auto",
autoclose: true,
todayHighlight: true
});
}
});

function reqChangePrice() {
	
	var tcq_req_flag = document.forms[0].tcq_req_flag.value;
	var tcq_req_close = document.forms[0].tcq_req_close.value;
	
	if(tcq_req_flag=='Y' && tcq_req_close == 'N'){
		alert('TCQ Modification Request Already Sent, Please Approve first to proceed further!!')
	}else{
		document.forms[0].option.value="requestChangePriceSN";
		var a= confirm("Do You Want to Make a Request to Change Price?");
		if(a) {
			document.forms[0].submit();
		}
	}
}

function convToDcq(obj){
var x = document.getElementById("dcq");
var y = document.getElementById("mtt");
if(parseFloat(y.value) >= parseFloat(obj.value)){
	x.value = Math.round(parseFloat(obj.value) * parseFloat(51.9));	
}else{
  alert("You can not Declared MT of DCQ more than MT of TCQ")
    obj.value="";x.value=""; 
}
}

function convToMT(obj){
var x = document.getElementById("mtd");
x.value = round(parseFloat(obj.value) / parseFloat(51.9) , 2);
}
function convToMtTcq(obj){
var x = document.getElementById("mtt");
x.value = round(parseFloat(obj.value) / parseFloat(51.9) , 2);
}
function convToTcq(obj){
var x = document.getElementById("tcq");
x.value = Math.round(parseFloat(obj.value) * parseFloat(51.9));
}
</script>
</body>
</html>