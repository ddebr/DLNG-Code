<%@ page buffer="128kb"%>
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

<title>DLNG | Framework LNG Sale Agreement(FLSA)</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css" >
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="../css/pt_sans.css">
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js" ></script>
<!-- <script type="text/javascript" src="../js/aris.js"></script> -->

<style type="text/css">
table tr{
   color: black;
}
.select {
    width: 200px;
    height: 27px;
} 
</style>

<script type="text/javascript">
$(document).ready(function()
{
	for(var k=1;k<=5;k++) 
	{
		var dtt = ".datepick"+k;
		$(dtt).datepicker({
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


var fgsaDeactivationWindow;
function openFgsaDeactivationWindow(buyer_cd,fgsa_cd,fgsa_revno,start_dt,end_dt,buyer_name)
{
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;

	/* var form_id = document.forms[0].form_id.value;
	var form_nm = document.forms[0].form_nm.value;
	 */
	if(!fgsaDeactivationWindow || fgsaDeactivationWindow.closed)
	{
		fgsaDeactivationWindow = window.open("frm_FLSA_active_deactive.jsp?buyer_cd="+buyer_cd+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&buyer_name="+buyer_name+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"FGSA_DEACTIVATION","top=10,left=10,width=1000,height=600,scrollbars=1,status=1");
	}
	else
	{
		fgsaDeactivationWindow.close();
		fgsaDeactivationWindow = window.open("frm_FLSA_active_deactive.jsp?buyer_cd="+buyer_cd+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&buyer_name="+buyer_name+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"FGSA_DEACTIVATION","top=10,left=10,width=1000,height=600,scrollbars=1,status=1");
	}
}


function setValue()
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	if(document.forms[0].rd2[0].checked)
	{
		document.forms[0].flg.value = "insert";
	}
	else if(document.forms[0].rd2[1].checked)
	{
		document.forms[0].flg.value = "update";
	}

	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	var activity = document.forms[0].flg.value;
	var bscode = "0";
	
	if(activity=='insert')
	{
		bscode = "0";
	}
	else if(activity=='update')
	{
		bscode = document.forms[0].cd.value;
	}
	
	location.replace("frm_mst_FLSA.jsp?activity="+activity+"&bscode="+bscode+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl); 
}

function checkBoxValidation(obj)
{
	//obj.checked==true
	var msg="";
	if(obj.name=="buyer_nom"){
		msg="Buyer Nomination";
	}else if(obj.name=="seller_nom"){
		msg="Seller Nomination";
	}else if(obj.name=="day_def"){
		msg="Days Definition";
	}else if(obj.name=="chk_buyer_nom"){
		msg="Daily Buyer Nomination";
	}else if(obj.name=="chk_seller_nom"){
		msg="Daily Seller Nomination";
	}else{
		msg = obj.name;
	}
	
	if(obj.checked==false){
		alert("You can not Uncheck "+msg);
		obj.checked = true;
	}
}


function optionalCheckBoxValidation(obj,i,j,parentObj,pNm,cNm)
{

	if(parentObj[i].checked==false)
	{
		//alert("Please select <<"+parentObj[i].value+">> then try to slect <"+obj.value+">");
		//alert("Please select <<"+pNm+">> then try to slect <"+cNm+">");
		//obj.checked=false;
	}	
}


function parentCheckBoxValidation(obj,i,size,clause)
{
	
}


function toggleCheckboxSelectionRenew()
{
	if(document.forms[0].renew_chk.checked)
	{
		document.forms[0].rev_chk.checked = false;
	}
}


function toggleCheckboxSelectionRevision()
{
	if(document.forms[0].rev_chk.checked)
	{
		document.forms[0].renew_chk.checked = false;
	}
}


function saveData(sz)
{
	var size = parseInt(sz);
	var message = "Please Check the Following field(s) ...\n\n";
	var flag=true;
	
	if(document.forms[0].flg.value=='insert')
	{
		if(document.forms[0].sign_dt.value=='' || document.forms[0].sign_dt.value==' ' || document.forms[0].sign_dt.value==null)
		{	
			message += "Please Enter The Signing Date...\n";
			flag=false;
		}
		//RG20200217
		if(document.forms[0].agrtype[0].checked==false && document.forms[0].agrtype[1].checked==false)
		{	
			message += "Please Select The Agreement Base...\n";
			flag=false;
		}
		//
		
		if(document.forms[0].buyer_name.value=='' || document.forms[0].buyer_name.value==' ' || document.forms[0].buyer_name.value==null)
		{	
			message += "Please Select Buyer from Buyer List...\n";
			flag=false;
		}
		if(document.forms[0].st_dt.value=='' || document.forms[0].st_dt.value==' ' || document.forms[0].st_dt.value==null)
		{	
			message += "Please Enter The Start Date...\n";
			flag=false;
		}
		if(document.forms[0].end_dt.value=='' || document.forms[0].end_dt.value==' ' || document.forms[0].end_dt.value==null)
		{	
			message += "Please Enter The End Date...\n";
			flag=false;
		}
		
				
		var chkFlg=false;
		if(document.forms[0].chk_trans.length==undefined)
		{
			if(document.forms[0].chk_trans.checked==true)
			{
				chkFlg=true;	
			}
		}
		else
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
			message += "Please Select atleast One Filling Station...\n";
			flag = false;
		}
			
		if(size!=0 && document.forms[0].chk_delv!=undefined)
		{
			chkFlg = false;
			if(document.forms[0].chk_delv.length==undefined)
			{
				if(document.forms[0].chk_delv.checked==true)
				{
					chkFlg = true;	
				}
			}
			else
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
				message += "Please Select Delivery Point ...\n";
				flag=false;
			}
		}
		//////////////////MDCQ
		
		if(document.forms[0].mdcq.checked==false)
		{
			document.forms[0].mdcq_percent.value=""
		}
		if(document.forms[0].mdcq.checked==true && document.forms[0].mdcq_percent.value=="")
		{
			message += "Please Enter MDCQ Percentage...\n";
			flag=false;
		}

		//Measurement
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
		
		///////Off Spec
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
	
	if(document.forms[0].flg.value=='update')
	{
		if(document.forms[0].sign_dt.value=='' || document.forms[0].sign_dt.value==' ' || document.forms[0].sign_dt.value==null)
		{	
			message += "Please Enter The Signing Date...\n";
			flag=false;
		}
		//RG20200217
		if(document.forms[0].agrtype[0].checked==false && document.forms[0].agrtype[1].checked==false)
		{	
			message += "Please Select The Agreement Base...\n";
			flag=false;
		}
		//
	
		if(document.forms[0].buyer_name.value=='' || document.forms[0].buyer_name.value==' ' || document.forms[0].buyer_name.value==null)
		{	
			message += "Please Select Buyer from Buyer List...\n";
			flag=false;
		}
		if(document.forms[0].st_dt.value=='' || document.forms[0].st_dt.value==' ' || document.forms[0].st_dt.value==null)
		{	
			message += "Please Enter The Start Date...\n";
			flag=false;
		}
		if(document.forms[0].end_dt.value=='' || document.forms[0].end_dt.value==' ' || document.forms[0].end_dt.value==null)
		{	
			message += "Please Enter The End Date...\n";
			flag=false;
		}
	
		var chkFlg=false;
		if(document.forms[0].chk_trans.length==undefined)
		{
			if(document.forms[0].chk_trans.checked==true)
			{
				chkFlg=true;	
			}
		}
		else
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
			message += "Please Select atleast One Filling Station...\n";
			flag = false;
		}
			
		if(size!=0 && document.forms[0].chk_delv!=undefined)
		{
			chkFlg = false;
			if(document.forms[0].chk_delv.length==undefined)
			{
				if(document.forms[0].chk_delv.checked==true)
				{
					chkFlg = true;	
				}
			}
			else
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
				message += "Please Select Delivery Point ...\n";
				flag=false;
			}
		}
		
		//////////////////MDCQ
		if(document.forms[0].mdcq.checked==false)
		{
			document.forms[0].mdcq_percent.value=""
		}
		
		if(document.forms[0].mdcq.checked==true && document.forms[0].mdcq_percent.value=="")
		{
			message += "Please Enter MDCQ Percentage...\n";
			flag=false;
		}

		//Measurement
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
					message += "Rate Max Bar must be Greater than Rate Min Bar ...\n";
					document.forms[0].rate_max_bar.focus();						
					flag=false;
				}	
			}
		}
		
		///////Off Spec
		if(document.forms[0].off_spec_gas_chk.checked==false)
		{
			document.forms[0].min_energy.value="";
			document.forms[0].max_energy.value="";
			document.forms[0].energy_off_spec.value="0";
		}
		
		if(document.forms[0].off_spec_gas_chk.checked==true && (document.forms[0].energy_off_spec.value=="0" || document.forms[0].max_energy.value=="" || document.forms[0].min_energy.value==""))
		{
			message += "Please Select Energy Base and Minimum and Maximum Value for Off Spec Gas ...\n";
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
			if(document.forms[0].rev_chk.checked)
			{
				document.forms[0].rev_flag.value = "Y";
				document.forms[0].renew_flag.value = "N";
				if(document.forms[0].agrtype[0].checked==true){
					document.forms[0].agrtyp.value='X';
				}else if(document.forms[0].agrtype[1].checked==true){
					document.forms[0].agrtyp.value='D';
				}
				msg ="Do You Want To Submit FLSA Details With New Revision NO Under Same FLSA Agreement NO ?\nAre You Sure That You Want To Revise This FLSA Agreement ?";
			}
			else if(document.forms[0].renew_chk.checked)
			{
				document.forms[0].rev_flag.value = "N";
				document.forms[0].renew_flag.value = "Y";
				if(document.forms[0].agrtype[0].checked==true){
					document.forms[0].agrtyp.value='X';
				}else if(document.forms[0].agrtype[1].checked==true){
					document.forms[0].agrtyp.value='D';
				}
				msg ="Do You Want To Submit FLSA Details With New Agreement NO ?\nAre You Sure That You Want To Renew This FLSA Agreement ?";
			}
			else
			{
				document.forms[0].rev_flag.value = "N";
				document.forms[0].renew_flag.value = "N";
				if(document.forms[0].agrtype[0].checked==true && document.forms[0].agrtype[1].checked==true){
					document.forms[0].agrtyp.value='B';
				}
				else if(document.forms[0].agrtype[0].checked==true){
					document.forms[0].agrtyp.value='X';
				}else if(document.forms[0].agrtype[1].checked==true){
					document.forms[0].agrtyp.value='D';
				}
				msg ="Do You Want To Modify Current FLSA Details ?";
			}
			
			a=confirm(msg);

			//alert("rev  :"+document.forms[0].revNo.value);
			//alert("pre RDat  : "+document.forms[0].rNewDt.value);
			//alert("curr RDat : "+document.forms[0].renewal_notice_dt.value);
		}
		else
		{
			document.forms[0].rev_flag.value = "N";
			document.forms[0].renew_flag.value = "N";
			if(document.forms[0].agrtype[0].checked==true){
				document.forms[0].agrtyp.value='X';
			}else if(document.forms[0].agrtype[1].checked==true){
				document.forms[0].agrtyp.value='D';
			}
			a=confirm("Do You Want To Insert New FLSA Details With New FLSA NO ?");
		}			
		if(a)
		{
			document.forms[0].submit();
		}
	}
}


function openBillingDetail(cls_cd,cls_nm,fgsa_no,rev_no,bscode)
{
	var flg = document.forms[0].flg.value;
	var St_dt = ""+document.forms[0].st_dt.value;
	//alert(fgsa_no);
	var customer_nm = document.forms[0].buyer_name.value;
	var customer_abbr = document.forms[0].buyer_abr.value;
	
	var ld="N";
	var top="N";
	var mug="N";
//	var form_id = document.forms[0].form_id.value;
//	var form_nm = document.forms[0].form_nm.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	//alert(cls_nm);
	if(cls_nm=="Billing Details")
	{
		if(fgsa_no!=null && fgsa_no!='' && fgsa_no!=' ' && fgsa_no!='  ' && fgsa_no!='0' 
		&& rev_no!=null && rev_no!='' && rev_no!=' ' && rev_no!='  ' 
		&& bscode!=null && bscode!='' && bscode!=' ' && bscode!='  ' && bscode!='0')
		{
			for(var i=0; i<document.forms[0].cls.length; i++)
	   		{
	  	 		if(document.forms[0].cls[i].checked)
	     	 	{
	     			//alert(document.forms[0].cls[i].value+"name"+cls_cd);
	     			if(document.forms[0].cls[i].value==cls_cd)
	    		 	{
	       	 			if(!newWindow5 || newWindow5.closed)
		 				{
						//SB	newWindow5 = window.open("frm_FLSA_bill_clause.jsp?FGSA_No="+fgsa_no+"&form_id="+form_id+"&form_nm="+form_nm+"&Rev_No="+rev_no+"&bscode="+bscode+"&Start_Dt="+St_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"FGSA_BILLING","top=10,left=10,width=900,height=600,scrollbars=1");
							newWindow5 = window.open("frm_FLSA_bill_clause.jsp?FGSA_No="+fgsa_no+"&Rev_No="+rev_no+"&bscode="+bscode+"&Start_Dt="+St_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"FGSA_BILLING","top=10,left=10,width=900,height=600,scrollbars=1");
						}
						else
						{
							newWindow5.close();
						//SB	newWindow5 = window.open("frm_FLSA_bill_clause.jsp?FGSA_No="+fgsa_no+"&form_id="+form_id+"&form_nm="+form_nm+"&Rev_No="+rev_no+"&bscode="+bscode+"&Start_Dt="+St_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"FGSA_BILLING","top=10,left=10,width=900,height=600,scrollbars=1");
							newWindow5 = window.open("frm_FLSA_bill_clause.jsp?FGSA_No="+fgsa_no+"&Rev_No="+rev_no+"&bscode="+bscode+"&Start_Dt="+St_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"FGSA_BILLING","top=10,left=10,width=900,height=600,scrollbars=1");
						}
	       			}
	    	 	}
	   		}
	   	}
	   	else
	   	{
	   		if (document.forms[0].cls[2].checked)
	     	{		     		 		    		 
		   		if(document.forms[0].cls1[2].checked)
		   		{
		   			//alert("Please, First Generate The Tender Agreement\nAfter Creation Of New Tender You Can Provide Billing Details !!!");
		   		}
		   		else
		   		{
		   			alert("Please, First Generate The FLSA Agreement\nAfter Creation Of New FLSA You Can Provide Billing Details !!!");
		   		}					   
			}		   		
	   	}
	}
	else if(cls_nm=="Liability")
	{
		if(fgsa_no!=null && fgsa_no!='' && fgsa_no!=' ' && fgsa_no!='  ' && fgsa_no!='0' 
		&& rev_no!=null && rev_no!='' && rev_no!=' ' && rev_no!='  ' 
		&& bscode!=null && bscode!='' && bscode!=' ' && bscode!='  ' && bscode!='0')
		{
			for(var i=0; i < document.forms[0].cls.length; i++)
	   		{
	  	 		if(document.forms[0].cls[i].checked)
	     	 	{
	     			//alert(document.forms[0].cls[i].value+"name"+cls_cd);
	    		 	if(document.forms[0].cls[i].value==cls_cd)
	    		 	{
	    		    	for(var i=0; i < document.forms[0].cls1.length; i++)
	  				 	{
	  		 				if(document.forms[0].cls1[i].checked)
	     				 	{
	     						if(document.forms[0].cls1[i].value=="Liquidated Damages")
	     						{
	     							ld="Y";
	     					 	}
	     						if(document.forms[0].cls1[i].value=="Take or Pay")
	     						{
	     							top="Y";
	     						}
	     						if(document.forms[0].cls1[i].value=="Make Up Gas")
	     						{
	     							mug="Y";
	     					 	}
	     				    }
	     				}
		       	 		
		       	 		if(!newWindow5 || newWindow5.closed)
		 				{
		 				//SB	newWindow5 = window.open("frm_FLSA_liability_clause.jsp?FGSA_No="+fgsa_no+"&Rev_No="+rev_no+"&bscode="+bscode+"&Start_Dt="+St_dt+"&ld="+ld+"&top="+top+"&mug="+mug+"&form_id="+form_id+"&form_nm="+form_nm+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"FGSA_LIABILITY","top=10,left=10,width=900,height=600,scrollbars=1");
		 					newWindow5 = window.open("frm_FLSA_liability_clause.jsp?FGSA_No="+fgsa_no+"&Rev_No="+rev_no+"&bscode="+bscode+"&Start_Dt="+St_dt+"&ld="+ld+"&top="+top+"&mug="+mug+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"FGSA_LIABILITY","top=10,left=10,width=900,height=600,scrollbars=1");
						}
						else
						{
							newWindow5.close();
						//SB	newWindow5 = window.open("frm_FLSA_liability_clause.jsp?FGSA_No="+fgsa_no+"&Rev_No="+rev_no+"&bscode="+bscode+"&Start_Dt="+St_dt+"&ld="+ld+"&top="+top+"&mug="+mug+"&form_id="+form_id+"&form_nm="+form_nm+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"FGSA_LIABILITY","top=10,left=10,width=900,height=600,scrollbars=1");
							newWindow5 = window.open("frm_FLSA_liability_clause.jsp?FGSA_No="+fgsa_no+"&Rev_No="+rev_no+"&bscode="+bscode+"&Start_Dt="+St_dt+"&ld="+ld+"&top="+top+"&mug="+mug+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"FGSA_LIABILITY","top=10,left=10,width=900,height=600,scrollbars=1");
						}
	          		}
	    	 	}
	   		}
	   	}
	   	else
	   	{
	   		 if (document.forms[0].cls[0].checked)
	     	 {		     		 		    		 
		   		if(document.forms[0].cls1[0].checked)
		   		{
		   			//alert("Please, First Generate The Tender Agreement\nAfter Creation Of New Tender You Can Provide Liability Details !!!");
		   		}
		   		else
		   		{
		   			alert("Please, First Generate The FLSA Agreement\nAfter Creation Of New FLSA You Can Provide Liability Details !!!");
		   		}					   
			 }	   		
	   	}
	}
	else if(cls_nm=="Letter of Credit")
	{
		if(fgsa_no!=null && fgsa_no!='' && fgsa_no!=' ' && fgsa_no!='  ' && fgsa_no!='0' 
		&& rev_no!=null && rev_no!='' && rev_no!=' ' && rev_no!='  ' 
		&& bscode!=null && bscode!='' && bscode!=' ' && bscode!='  ' && bscode!='0')
		{
			for(var i=0; i<document.forms[0].cls.length; i++)
	   		{
	  	 		if(document.forms[0].cls[i].checked)
	     	 	{
	     			//alert(document.forms[0].cls[i].value+"name"+cls_cd);
	     			if(document.forms[0].cls[i].value==cls_cd)
	    		 	{
	       	 			if(!newWindow5 || newWindow5.closed)
		 				{
						//SB	newWindow5 = window.open("frm_FLSA_LC_monitoring.jsp?cont_type=F&fgsa_no="+fgsa_no+"&fgsa_rev_no="+rev_no+"&customer_cd="+bscode+"&customer_nm="+customer_nm+"&customer_abbr="+customer_abbr+"&form_id="+form_id+"&form_nm="+form_nm+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"FGSA_LC_DTL","top=10,left=10,width=900,height=500,scrollbars=1,status=1");
							newWindow5 = window.open("frm_FLSA_LC_monitoring.jsp?cont_type=F&fgsa_no="+fgsa_no+"&fgsa_rev_no="+rev_no+"&customer_cd="+bscode+"&customer_nm="+customer_nm+"&customer_abbr="+customer_abbr+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"FGSA_LC_DTL","top=10,left=10,width=900,height=500,scrollbars=1,status=1");
						}
						else
						{
							newWindow5.close();
						//SB	newWindow5 = window.open("frm_FLSA_LC_monitoring.jsp?cont_type=F&fgsa_no="+fgsa_no+"&fgsa_rev_no="+rev_no+"&customer_cd="+bscode+"&customer_nm="+customer_nm+"&customer_abbr="+customer_abbr+"&form_id="+form_id+"&form_nm="+form_nm+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"FGSA_LC_DTL","top=10,left=10,width=900,height=500,scrollbars=1,status=1");
							newWindow5 = window.open("frm_FLSA_LC_monitoring.jsp?cont_type=F&fgsa_no="+fgsa_no+"&fgsa_rev_no="+rev_no+"&customer_cd="+bscode+"&customer_nm="+customer_nm+"&customer_abbr="+customer_abbr+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission,"FGSA_LC_DTL","top=10,left=10,width=900,height=500,scrollbars=1,status=1");
						}
	       			}
	    	 	}
	   		}
	   	}
	   	else
	   	{
	   		if (document.forms[0].cls[1].checked)
	     	{		     		 		    		 
		   		if(document.forms[0].cls1[1].checked)
		   		{
		   			//alert("Please, First Generate The Tender Agreement\nAfter Creation Of New Tender You Can Provide LC Details !!!");
		   		}
		   		else
		   		{
		   			alert("Please, First Generate The FLSA Agreement\nAfter Creation Of New FLSA You Can Provide LC Details !!!");
		   		}					   
			}		   		
	   	}
	}
}


function openFGSAList()
{
//	var bank_name = document.forms[0].name.value;
	var flg = document.forms[0].flg.value;
    var cust_nm = document.forms[0].cust_nm.value;	
	if(!newWindow5 || newWindow5.closed)
	{
	
		newWindow5 = window.open("frm_FLSA_list.jsp?flg="+flg+"&cust_nm="+cust_nm,"FLSA_LIST","top=10,left=10,width=900,height=600,scrollbars=1");
	}
	else
	{
		newWindow5.close();
		newWindow5 = window.open("frm_FLSA_list.jsp?flg="+flg+"&cust_nm="+cust_nm,"FLSA_LIST","top=10,left=10,width=900,height=600,scrollbars=1");
	}
}


function setFGSACode(cd,fgsa_cd,activity,rev)
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	location.replace("frm_mst_FLSA.jsp?activity="+activity+"&bscode="+cd+"&buyer_cd="+cd+"&FGSA_CD="+fgsa_cd+"&rev_no="+rev+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl);
}


function setBuyerValue(activity,cd,nm,abbr,plant_nm,plant_type,seq_no,sign_dt)
{
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	var fgsa_flg='';
	if(document.forms[0].agrtype[0].checked)
	{
		fgsa_flg="X";
	}
	
	if(document.forms[0].agrtype[1].checked)
	{
		fgsa_flg="D";
	}
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	location.replace("frm_mst_FLSA.jsp?activity="+activity+"&buyer_cd="+cd+"&buyer_abbr="+abbr+"&buyer_name="+nm+"&sign_dt="+sign_dt+"&plant_nm="+plant_nm+"&plant_type="+plant_type+"&seq_no="+seq_no+"&fgsa_flg="+fgsa_flg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl);
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


function compareSigningAndStartDate(obj)
{
	var st_dt = ""+document.forms[0].st_dt.value;
	var sign_dt = obj.value;
	//alert("sign_dt = "+sign_dt+", And  st_dt = "+st_dt);
	if(st_dt!='' && st_dt!=' ' && st_dt!=null && sign_dt!='' && sign_dt!=' ' && sign_dt!=null)
	{
	  	var value = compareDate(sign_dt,st_dt);
	  	if(value==1)
	  	{
	    	alert("Signing Date Must Be Less Than Or Equal To Contract Start Date !!!");
	    	obj.value = "";
	    	obj.select();
	    	return false;
	  	}
	}
}


function compareRenewalAndStartDate(obj)
{
	var st_dt = ""+document.forms[0].st_dt.value;
	var renewal_dt = obj.value;
	//alert("sign_dt = "+sign_dt+", And  st_dt = "+st_dt);
	if(st_dt!='' && st_dt!=' ' && st_dt!=null && renewal_dt!='' && renewal_dt!=' ' && renewal_dt!=null)
	{
	  	var value = compareDate(st_dt,renewal_dt);
	  	if(value==1 || value==0)
	  	{
	    	alert("Renewal Date Must Be Greater Than Contract Start Date !!!");
	    	obj.value = "";
	    	obj.select();
	    	return false;
	  	}
	}
}


function compareStartAndSigningDate(obj)
{
	var sign_dt = ""+document.forms[0].sign_dt.value;
	var st_dt = obj.value;
	//alert("sign_dt = "+sign_dt+", And  st_dt = "+st_dt);
	if(st_dt!='' && st_dt!=' ' && st_dt!=null && sign_dt!='' && sign_dt!=' ' && sign_dt!=null)
	{
	  	var value = compareDate(sign_dt,st_dt);
	  	if(value==1)
	  	{
	    	alert("Contract Start Date Must Be Greater Than Or Equal To Signing Date !!!");
	    	obj.value = "";
	    	obj.select();
	    	return false;
	  	}
	}
}


function compareEndAndStartDate(obj)
{
	var st_dt = ""+document.forms[0].st_dt.value;
	var end_dt = obj.value;
	//alert("st_dt = "+st_dt+", And  end_dt = "+end_dt);
	if(st_dt!='' && st_dt!=' ' && st_dt!=null && end_dt!='' && end_dt!=' ' && end_dt!=null)
	{
	  	var value = compareDate(st_dt,end_dt);
	  	if(value==1 || value==0)
	  	{
	    	alert("Contract End Date Must Be Greater Than Contract Start Date !!!");
	    	obj.value = "";
	    	obj.select();
	    	return false;
	  	}
	}
}

</script>

</head>

<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.contract_master.DataBean_Contract_MasterV2" id="masCont" scope="page"/>   
<jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_modulelock" id="modlock" scope="request"/>
<%
	utilBean.init();
	String date = utilBean.getGen_dt();
    String username=(String)session.getAttribute("username");
			 
	String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
	String bscode = request.getParameter("bscode")==null?"0":request.getParameter("bscode");
	String sign_date = request.getParameter("sign_dt")==null?date:request.getParameter("sign_dt");
	String buyer_name = request.getParameter("buyer_name")==null?"":request.getParameter("buyer_name");
	String buyer_abbr = request.getParameter("buyer_abbr")==null?"":request.getParameter("buyer_abbr");
	String buyer_cd = request.getParameter("buyer_cd")==null?"0":request.getParameter("buyer_cd");
	String plant_nm = request.getParameter("plant_nm")==null?"":request.getParameter("plant_nm");
	String plant_type = request.getParameter("plant_type")==null?"":request.getParameter("plant_type");
	String seq_no = request.getParameter("seq_no")==null?"0":request.getParameter("seq_no");
	String msg = request.getParameter("msg")== null?"":request.getParameter("msg");
	String fgsa_flg = request.getParameter("fgsa_flg")== null?"":request.getParameter("fgsa_flg"); //RG20200110
	
	String FGSA_CD = request.getParameter("FGSA_CD")==null?"":request.getParameter("FGSA_CD");
	String rev_no = request.getParameter("rev_no")==null?"":request.getParameter("rev_no");
	
	
	String write_permission = (String)session.getAttribute("write_permission")==null?"":(String)session.getAttribute("write_permission");
	String delete_permission = (String)session.getAttribute("delete_permission")==null?"":(String)session.getAttribute("delete_permission");
	String print_permission = (String)session.getAttribute("print_permission")==null?"":(String)session.getAttribute("print_permission");
	String approve_permission = (String)session.getAttribute("approve_permission")==null?"":(String)session.getAttribute("approve_permission");
	String audit_permission = (String)session.getAttribute("audit_permission")==null?"":(String)session.getAttribute("audit_permission");
	
	String user_cd=(String)session.getAttribute("user_cd"); //ADDED BY RG 16-10-2015 FOR CUSTOMER WISE ACCESS RIGHTS
	String customer_access_flag = (String)session.getAttribute("customer_access_flag");
	
	masCont.setCallFlag("FLSA_MST");
	masCont.setBscode(bscode);
	masCont.setFGSA_cd(FGSA_CD);
	masCont.setFGSA_REVNo(rev_no);
	masCont.setEmp_cd(user_cd);		//ADDED BY RG 16-10-2015 FOR CUSTOMER WISE ACCESS RIGHTS
	masCont.setCustomer_access_flag(customer_access_flag);
	masCont.init();
// 	System.out.println("Buyer Code => "+bscode);
//	System.out.println("FGSA Code => "+FGSA_CD);
	
	Vector transporter_cd = masCont.getTransporter_cd();
	Vector transporter_nm = masCont.getTransporter_nm();
	Vector transporter_abbr = masCont.getTransporter_abbr();
	
	Vector clause_cd = masCont.getClause_cd();
	Vector clause_nm = masCont.getClause_nm();
	Vector clause_abbr = masCont.getClause_abbr();
	Vector clause_mst_cd = masCont.getClause_mst_cd();
	
//	System.out.println("Plant_nm in FGSA Master Form = "+plant_nm);
	
	Vector vbuyer_plant_seq_no = masCont.getBuyer_plant_seq_no();
	Vector vbuyer_plant_nm = masCont.getBuyer_plant_nm();
	Vector vbuyer_plant_type = masCont.getBuyer_plant_type();
	
	String [] buyer_plant_nm = null;
	String [] buyer_plant_type = null;
	String [] buyer_plant_seq_no = null;
	if(activity.equalsIgnoreCase("insert"))
	{
		buyer_plant_nm = plant_nm.split("~~~");
		buyer_plant_type = plant_type.split("~~~");
		buyer_plant_seq_no = seq_no.split("~~~");
	}
	else if(activity.equalsIgnoreCase("update"))
	{
		if(vbuyer_plant_nm.size()!=0)
		{
			//System.out.println("*******Before***********  :"+vbuyer_plant_nm.size());
			buyer_plant_nm = new String [((Vector)vbuyer_plant_nm.elementAt(0)).size()];
			buyer_plant_type = new String [((Vector)vbuyer_plant_type.elementAt(0)).size()];
			buyer_plant_seq_no = new String [((Vector)vbuyer_plant_seq_no.elementAt(0)).size()];
			//System.out.println("Plant_nm Update = "+buyer_plant_nm.length + " " + vbuyer_plant_nm );
			
			for(int i=0;i<((Vector)vbuyer_plant_nm.elementAt(0)).size();i++)
			{
				 buyer_plant_nm[i]=((Vector)vbuyer_plant_nm.elementAt(0)).elementAt(i).toString();
				 buyer_plant_type[i]=((Vector)vbuyer_plant_type.elementAt(0)).elementAt(i).toString();
				 buyer_plant_seq_no[i]=((Vector)vbuyer_plant_seq_no.elementAt(0)).elementAt(i).toString();
			}
		}	
	}
	
	String SIGNING_DT=masCont.getSIGNING_DT();
	String START_DT=masCont.getSTART_DT();
	String END_DT=masCont.getEND_DT();
	String FGSA_BASE=masCont.getFGSA_BASE();
	String FGSA_TYPE=masCont.getFGSA_TYPE();
	String STATUS=masCont.getSTATUS(); 
	String BUYER_NOM=masCont.getBUYER_NOM();
	String BUYER_MONTH_NOM=masCont.getBUYER_MONTH_NOM();
	String BUYER_WEEK_NOM=masCont.getBUYER_WEEK_NOM();
	String BUYER_DAILY_NOM=masCont.getBUYER_DAILY_NOM();
	String SELLER_NOM=masCont.getSELLER_NOM();
	String SELLER_MONTH_NOM=masCont.getSELLER_MONTH_NOM();
	String SELLER_WEEK_NOM=masCont.getSELLER_WEEK_NOM();
	String SELLER_DAILY_NOM=masCont.getSELLER_DAILY_NOM();
	String DAY_DEF=masCont.getDAY_DEF();
	String DAY_START_TIME=masCont.getDAY_START_TIME();
	String DAY_END_TIME=masCont.getDAY_END_TIME();
	String MDCQ=masCont.getMDCQ();
	String MDCQ_PERCENTAGE=masCont.getMDCQ_PERCENTAGE();
	String MEASUREMENT=masCont.getMEASUREMENT();
	String MEAS_STANDARD=masCont.getMEAS_STANDARD();
	String MEAS_TEMPERATURE=masCont.getMEAS_TEMPERATURE();
	String PRESSURE_MIN_BAR=masCont.getPRESSURE_MIN_BAR();
	String PRESSURE_MAX_BAR=masCont.getPRESSURE_MAX_BAR();
	String OFF_SPEC_GAS=masCont.getOFF_SPEC_GAS();
	String SPEC_GAS_ENERGY_BASE=masCont.getSPEC_GAS_ENERGY_BASE();
	String SPEC_GAS_MIN_ENERGY=masCont.getSPEC_GAS_MIN_ENERGY();
	String SPEC_GAS_MAX_ENERGY=masCont.getSPEC_GAS_MAX_ENERGY();
	
	String BUYER_NOM_CLAUSE = masCont.getBUYER_NOM_CLAUSE() ;
	String SELLER_NOM_CLAUSE = masCont.getSELLER_NOM_CLAUSE();
	String LC_CLAUSE = masCont.getLC_CLAUSE();
	String BILLING_CLAUSE = masCont.getBILLING_CLAUSE();
	String LIABILITY_CLAUSE = masCont.getLIABILITY_CLAUSE();

	
	String REV_NO = masCont.getREV_NO();
	String RENEWAL_DT = masCont.getRENEWAL_DT();
	String REV_DT = masCont.getREV_DT();
	
	Vector vTRANSPORTER_CD = new Vector();
	Vector vPLANT_SEQ_NO = new Vector();
	Vector vCLAUSE_CD = new Vector();
	Vector vREMARK = new Vector();

	vTRANSPORTER_CD.clear();
	vPLANT_SEQ_NO = masCont.getVPLANT_SEQ_NO();
	vCLAUSE_CD = masCont.getVCLAUSE_CD();
	vREMARK = masCont.getVREMARK();	
	vTRANSPORTER_CD = masCont.getVTRANSPORTER_CD();
	
	String REMARK = masCont.getREMARK();
	Vector CUST_CD = masCont.getCUST_CD();
	Vector CUST_NM = masCont.getCUST_NM();
	if(buyer_name.equalsIgnoreCase(""))
	{
		buyer_name = masCont.getBName();
		buyer_abbr = masCont.getBAbbr();
	}
	
	if(!SIGNING_DT.equals(""))
	{
		sign_date = SIGNING_DT;		
	}	
	
	String Deactivation_from_dt = masCont.getDeactivation_from_dt();
	String Deactivation_to_dt = masCont.getDeactivation_to_dt();
	String FGSA_status = masCont.getFGSA_status();
	
	//System.out.println("FGSA_status  ==  "+FGSA_status);
	
	boolean contractEndFlag = masCont.isContractEndFlag();
	String REOPEN_REQUEST_FLAG = masCont.getREOPEN_REQUEST_FLAG();
	String REOPEN_APPROVAL_FLAG = masCont.getREOPEN_APPROVAL_FLAG();
%>  

<script>

function setData(SIGNING_DT,START_DT,END_DT ,FGSA_BASE,FGSA_TYPE,STATUS,BUYER_NOM,BUYER_MONTH_NOM,BUYER_WEEK_NOM,BUYER_DAILY_NOM ,
SELLER_NOM ,SELLER_MONTH_NOM,SELLER_WEEK_NOM ,SELLER_DAILY_NOM,DAY_DEF,DAY_START_TIME ,DAY_END_TIME ,
MDCQ,MDCQ_PERCENTAGE,MEASUREMENT ,MEAS_STANDARD ,MEAS_TEMPERATURE ,PRESSURE_MIN_BAR ,PRESSURE_MAX_BAR ,
OFF_SPEC_GAS,SPEC_GAS_ENERGY_BASE,SPEC_GAS_MIN_ENERGY ,SPEC_GAS_MAX_ENERGY,REV_NO,RENEWAL_DT,REV_DT)
{

	document.forms[0].renewal_notice_dt.value = RENEWAL_DT;
	document.forms[0].rev_dt.value = REV_DT;
	
	if(FGSA_BASE=="X")
	{
		document.forms[0].agrtype[0].checked=true;
	}
	if(FGSA_BASE=="D")
	{
		document.forms[0].agrtype[1].checked=true;
	}
	if(FGSA_BASE=="B")
	{
		document.forms[0].agrtype[0].checked=true;
		document.forms[0].agrtype[1].checked=true;
	}
	if(FGSA_TYPE=="T"){
		document.forms[0].termspottype[0].checked=true;
	}
	if(FGSA_TYPE=="S"){
		document.forms[0].termspottype[1].checked=true;
	}
	
	/*if(STATUS=="Y"){
		document.forms[0].status[0].checked=true;
	}
	if(STATUS=="N"){
		document.forms[0].status[1].checked=true;
	}*/
	
	//Daily Nom
	if(BUYER_NOM=="Y"){
		document.forms[0].buyer_nom.checked=true;
	}
	if(BUYER_MONTH_NOM=="Y"){
		document.forms[0].chk_buyer_nom[0].checked=true;
	}
	if(BUYER_WEEK_NOM=="Y"){
		document.forms[0].chk_buyer_nom[1].checked=true;
	}
	if(BUYER_DAILY_NOM=="Y"){
		document.forms[0].chk_buyer_nom[2].checked=true;
	}	
	
  	//Seller Nom
	if(SELLER_NOM=="Y"){
		document.forms[0].seller_nom.checked=true;
	}
	if(SELLER_MONTH_NOM=="Y"){
		document.forms[0].chk_seller_nom[0].checked=true;
	}
	if(SELLER_WEEK_NOM=="Y"){
		document.forms[0].chk_seller_nom[1].checked=true;
	}
	if(SELLER_DAILY_NOM=="Y"){
		document.forms[0].chk_seller_nom[2].checked=true;
	}	
	
	document.forms[0].day_def.checked=DAY_DEF;
	
	if(DAY_START_TIME==""){
		DAY_START_TIME = "06:00";
	}
	
	if(DAY_END_TIME==""){
		DAY_END_TIME = "06:00";
	}
	document.forms[0].day_time_from.value=DAY_START_TIME;
	document.forms[0].day_time_to.value=DAY_END_TIME; 	  
	  	
	  	
//	  alert("MDCQ	:"+MDCQ);	
  	if(MDCQ=="Y"){
		document.forms[0].mdcq.checked = true;
		document.forms[0].mdcq_percent.value = MDCQ_PERCENTAGE;
	}else{
		document.forms[0].mdcq.checked = false;
	}	
//	 alert("MEASUREMENT	:"+MEASUREMENT);	
	if(MEASUREMENT=="Y"){
		document.forms[0].measurement.checked = true;
		document.forms[0].standard.value = MEAS_STANDARD;
		document.forms[0].temprature.value = MEAS_TEMPERATURE;
		document.forms[0].rate_min_bar.value = PRESSURE_MIN_BAR;
		document.forms[0].rate_max_bar.value = PRESSURE_MAX_BAR;
	} else{
		document.forms[0].measurement.checked = false;
	}	
	  	
	if(OFF_SPEC_GAS=="Y")
	{
		document.forms[0].off_spec_gas_chk.checked = true;
		//alert("SPEC_GAS_ENERGY_BASE = "+SPEC_GAS_ENERGY_BASE);
		var temp="0";
		if(trim(SPEC_GAS_ENERGY_BASE) == "1")
		{
			temp="1";
		}
		else if(trim(SPEC_GAS_ENERGY_BASE) == "2")
		{
			temp="2";
		}
		else
		{
			temp="0";
		}		
		document.forms[0].energy_off_spec.value = temp;
		document.forms[0].min_energy.value = SPEC_GAS_MIN_ENERGY ;
		document.forms[0].max_energy.value = SPEC_GAS_MAX_ENERGY;
	}
	else
	{
		document.forms[0].off_spec_gas_chk.checked = false;
	} 

// Set Clause 
	objDelv = document.forms[0].cls;
	
	if(objDelv!=null && objDelv.length!=undefined){ 
		len = document.forms[0].cls.length;
		for(var i=0;i<len;i++){  
			<%
			for(int j=0;j<vCLAUSE_CD.size();j++){  
				%>
				if(document.forms[0].cls[i].value==<%=vCLAUSE_CD.elementAt(j)%>){
					document.forms[0].cls[i].checked = true;	
				}
				
		<%	} %>
		}	
	}else if(objDelv!=null){
		<%
		for(int j=0;j<vCLAUSE_CD.size();j++){  
		%>
			if(document.forms[0].cls.value==<%=vCLAUSE_CD.elementAt(j)%>){
				document.forms[0].cls.checked = true;	
			}
			
	<%	} %>
	}	
	
	
// Set Transporter 

	objDelv = document.forms[0].chk_trans;
	
	if(objDelv!=null && objDelv.length!=undefined){ 
		len = document.forms[0].chk_trans.length;
		
		for(var i=0;i<len;i++){  
			<%
			for(int j=0;j<vTRANSPORTER_CD.size();j++){  
				%>
				if(document.forms[0].chk_trans[i].value==<%=vTRANSPORTER_CD.elementAt(j)%>){
					document.forms[0].chk_trans[i].checked = true;	
				}
				
				<%	} %>
		}	
	}else if(objDelv!=null){
		<%
		for(int j=0;j<vTRANSPORTER_CD.size();j++){  
		%>
			if(document.forms[0].chk_trans.value==<%=vTRANSPORTER_CD.elementAt(j)%>){
				document.forms[0].chk_trans.checked = true;	
			}
			
	<%	} %>
	}	
	
//	Set Delivery Point
	
	objDelv = document.forms[0].chk_delv;
	
	if(objDelv!=null && objDelv.length!=undefined){ 
		len = document.forms[0].chk_delv.length;
		for(var i=0;i<len;i++){  
			<%
			for(int j=0;j<vPLANT_SEQ_NO.size();j++){  
				%>
				if(document.forms[0].chk_delv[i].value==<%=vPLANT_SEQ_NO.elementAt(j)%>){
					document.forms[0].chk_delv[i].checked = true;	
				}
				
				<%	} %>
		}	
	}else if(objDelv!=null){
		<%
		for(int j=0;j<vPLANT_SEQ_NO.size();j++){  
		%>
			if(document.forms[0].chk_delv.value==<%=vPLANT_SEQ_NO.elementAt(j)%>){
				document.forms[0].chk_delv.checked = true;	
			}
			
	<%	} %>
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
</script>
<%
String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200227
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200227
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200227

%>
<body onload="setData('<%=SIGNING_DT%>','<%=START_DT%>','<%=END_DT%>','<%=FGSA_BASE%>','<%=FGSA_TYPE%>','<%=STATUS%>','<%=BUYER_NOM%>','<%=BUYER_MONTH_NOM%>','<%=BUYER_WEEK_NOM%>','<%=BUYER_DAILY_NOM%>','<%=SELLER_NOM%>','<%=SELLER_MONTH_NOM%>','<%=SELLER_WEEK_NOM%>','<%=SELLER_DAILY_NOM%>','<%=DAY_DEF%>','<%=DAY_START_TIME%>','<%=DAY_END_TIME%>','<%=MDCQ%>','<%=MDCQ_PERCENTAGE%>','<%=MEASUREMENT%>','<%=MEAS_STANDARD%>','<%=MEAS_TEMPERATURE%>','<%=PRESSURE_MIN_BAR%>','<%=PRESSURE_MAX_BAR%>','<%=OFF_SPEC_GAS%>','<%=SPEC_GAS_ENERGY_BASE%>','<%=SPEC_GAS_MIN_ENERGY%>','<%=SPEC_GAS_MAX_ENERGY%>','<%=REV_NO%>','<%=RENEWAL_DT%>','<%=REV_DT%>');MDCQreadOnly();MeasurementQreadOnly();offSpecreadOnly();">

<div class="tab-content">
	<div class="tab-pane active" >
		<div class="box mb-0">
		
<%	if(print_permission.trim().equalsIgnoreCase("N"))
	{%>
<!-- 	 <script language="javascript" src="../js/mouseclk.js"></script> -->
<%	}%>
<form name="mainview" method="post" action="../servlet/Frm_Contract_MasterV2">

<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
<input type="hidden" name="form_Id" value="<%=formId%>">
<input type="hidden" name="form_nm" value="">

<input name="option" type="hidden" value="FLSA_Master">
<input type="hidden" name="currentDate" value="<%=date%>">
<input type="hidden" name="flg" value="<%=activity%>">
<input type="hidden" name="revNo" value="<%=REV_NO%>">
<input type="hidden" name="rNewDt" value="<%=RENEWAL_DT%>">
<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
		
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
					<%if(contractEndFlag) { %>
					<div class="col-xs-12" >
				  		<div class="table-responsive" >
				    		<table class="table table-striped" >
				    			<tr>
				    				<th class="text-center" colspan="12" style="color: red;">This Contract is Archived</th>
				    			</tr>
				    		</table>
				    	</div>
				    </div>	
					<%} %>
				</div>
			</div>
			<div class="row">
				<div class="table-responsive col-lg-12">
					<table class="table table-striped" >
			    		<tr>
			    			<td  class="text-left" colspan="11">
			    				<font size="3"><b>Entry</b></font> &nbsp;&nbsp;&nbsp;&nbsp;
			    				<input type="radio" name="rd2" onClick="setValue();" 
								<%if(activity.equals("insert")){%>checked<%}%>><b>&nbsp;New</b>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="rd2" onClick="setValue();" 
								<%if(activity.equals("update")){%>checked<%}%>><b>&nbsp;Modify</b>&nbsp;&nbsp;
			    			</td>
			    			<%if(contractEndFlag) { %>
				    			<td colspan="1" class="text-right">
				    			<%if(REOPEN_REQUEST_FLAG.equals("Y") && (REOPEN_APPROVAL_FLAG.equals("N") || REOPEN_APPROVAL_FLAG.equals(""))) { %>
				    				<input type="button" class="btn-primary" name='reOpen' <%if(REOPEN_APPROVAL_FLAG.equals("N")) { %>value='Re-Activate Request DisApproved'<% } else { %>value='Re-Activate Request Sent'<% } %> disabled="disabled">
				    			<% } else if(REOPEN_REQUEST_FLAG.equals("Y") && REOPEN_APPROVAL_FLAG.equals("Y")) { %>
						
								<% } else if(REOPEN_REQUEST_FLAG.equals("N") || REOPEN_REQUEST_FLAG.equals(""))  { %>
									<input type='button' class="btn-primary" name='reOpen' value='Re-Activate' onclick="reOpenContract();">
								<% } %>
				    			</td>
			    			<%} %>
			    		</tr>	
			    	</table>
			 	</div>
			 </div>	
			 
			 <div class="row">
				<div class="table-responsive col-lg-7">
					<table class="table table-striped" >
						<tr>
		    				<td class="info" width="30%">Select Customer </td>
		    				<td >
		    					<select name="cust_nm" class="select">
							      <option value="0">--Select--</option>
							  <%for(int i=0;i<CUST_CD.size();i++){%>
							  		<option value="<%=CUST_CD.elementAt(i)%>"><%=CUST_NM.elementAt(i)%></option>
							  <%}%>						
								</select>
								<script> document.forms[0].cust_nm.value = "<%=bscode%>";</script>
		    				</td>
		    			</tr>
		    			
		    			<tr>
		    				<td class="info">
				    			Signing Date<span class="s-red">*</span>
			    			</td>
			    			<td>
	      						<input type="text" class="select datepick1" name="sign_dt" id="d1" maxlength="11" size="10" value="<%=sign_date%>" onBlur="validateDate(this);compareSigningAndStartDate(this);" title="dd/mm/yyyy Format" autocomplete="off">
			    			</td>
		    			</tr>
		    			<tr>
		    				<td class="info">
				    			Effective Date
			    			</td>
			    			<td >
	      						<input type="text" class="select datepick2" id='rev_dt' name="rev_dt" maxlength="11" size="10"  readonly="readonly" value="<%=REV_DT%>" onBlur="validateDate(this);" title="dd/mm/yyyy Format" autocomplete="off"> 
			    			</td>
		    			</tr>
		    			<tr>
		    				<td class="info">
				    			Renewal Notice Date
			    			</td>
			    			<td >
	      						<input type="text" class="select datepick3" name="renewal_notice_dt" id='renewal_notice_dt' class="mkrdly" readonly="readonly" maxlength="10" size="10" value="" onBlur="validateDate(this);compareRenewalAndStartDate(this);" title="dd/mm/yyyy Format" autocomplete="off"> 
			    			</td>
		    			</tr>
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
			    			<td class="info">Status</td>
							<td> <%=FGSA_status %>
							<%System.out.println("activity****"+activity); %>
							 	<input type="button" class="btn-primary" value="Deactivate" name="Deactivate" <%if(activity.equals("update")){%>onClick="openFgsaDeactivationWindow('<%=bscode%>','<%=FGSA_CD%>','<%=REV_NO%>','<%=START_DT%>','<%=END_DT%>','<%=buyer_name%>');"<%}%>>
							  	<% if(!Deactivation_from_dt.trim().equals("") && Deactivation_from_dt!=null)  
						        { %> 
						          	  DeActivation Period: <%=Deactivation_from_dt%> - <%=Deactivation_to_dt%>
						     <% }%>	
						    </td>
			    		</tr>
			    		<tr>
			    			<td class="info">Remark</td>
							<td><input type="text" name="remark" value="<%=REMARK%>" readOnly size="50" maxlength="150">
							</td>
						</tr>
						<tr>
						    <td class="info">Filling Station<span class="s-red">*</span></td>
						    <td>
							  <%	for(int i=0;i<transporter_cd.size();i++)
							    	{%>		<input type="checkbox" name="chk_trans" value="<%=transporter_cd.elementAt(i)%>">&nbsp;<%=transporter_abbr.elementAt(i)%>&nbsp;&nbsp;
							    <%	}%> 
						    </td>
						</tr>
						
		    		</table>
		    	</div>
		    	<div class="table-responsive col-lg-5">
					<table class="table table-striped" >
						<tr>
							<td class="info" width="30%" >Agreement No.</td>
		    				<td >
						     	<input name="cd" type="hidden" value="<%=bscode%>" size="15">
						     	<input type="text" name="fgsa_no" maxlength="15" size="15" value="<%=FGSA_CD%>" readonly="readonly">
	     						<input type="button" class="btn-primary" value="View List" name="Search1" onclick="openFGSAList();">
		    				</td>
						</tr>
						<tr>
							<td class="info" width="30%" >Revision/Renewal</td>
		    				<td >
						     	<input name="rev_chk" type="checkbox" value="Y" onClick="toggleCheckboxSelectionRevision();enablerevisiondt(this);" title="Increment in Revision Number">&nbsp;Apply Revision
						     	<input name="rev_flag" type="hidden" value="N">&nbsp;&nbsp;
						     	<input name="renew_chk" type="checkbox" value="Y" onClick="toggleCheckboxSelectionRenew();enablerevisiondt(this);enablerevisiondt1(this);" title="Increment in FLSA Number">&nbsp;Apply Renewal		    	
						    	<input name="renew_flag" type="hidden" value="N">
		    				</td>
						</tr>
						<tr>
							<td class="info" width="30%" >Agreement Base<span class="s-red">*</span></td>
		    				<td ><%=fgsa_flg %>
						     	<input type="checkbox" name="agrtype" value="X" onclick="" <%if(fgsa_flg.equalsIgnoreCase("X")){ %> checked="checked" <%} %>>&nbsp;Ex-Terminal
								&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="agrtype" value="D" onclick="" <%if(fgsa_flg.equalsIgnoreCase("D")){ %> checked="checked" <%} %>>&nbsp;Delivery
								<input type="hidden" name="agrtyp" value="">
		    				</td>
						</tr>
						<tr>
							 <td class="info">Start Date<span class="s-red">*</span></td>
							 <td> 
							 	<input class="select datepick4" type="text" name="st_dt" id="d3" maxlength="10" size="10" value="<%=START_DT%>" onBlur="validateDate(this);compareStartAndSigningDate(this);" title="dd/mm/yyyy Format" autocomplete="off">
						     </td>
		     			</tr>
		     			<tr>
			    			<td class="info">End Date<span class="s-red">*</span></td>
							<td> 
							 	<input class="select datepick5" type="text" id="d4" name="end_dt" maxlength="10" size="10" value="<%=END_DT%>" onBlur="validateDate(this);compareEndAndStartDate(this);" title="dd/mm/yyyy Format" autocomplete="off">
						    </td>
			    		</tr>
			    		<tr>
							<td class="info" width="30%" >Agreement Type</td>
		    				<td >
						     	<input type="radio" name="termspottype" value="0" checked>&nbsp;Term
			 					&nbsp;&nbsp;&nbsp;<input type="radio" name="termspottype" value="1">&nbsp;Spot	
		    				</td>
						</tr>
			    		<tr>
						    <td class="info">Plant(s)<span class="s-red">*</span></td>
						    <td>
							     <%if(buyer_plant_nm != null ){
										if(!buyer_cd.equals("0"))
								     	{
								     		for(int i=0;i<buyer_plant_nm.length;i++)
								     		{	
								     		
								     		%>
								     			<input type="checkbox" name="chk_delv" value="<%=buyer_plant_seq_no[i]%>">&nbsp;<%=buyer_plant_nm[i]%><br>
								     <%		}	
								     	}
									}%>
						    </td>
						</tr>
					</table>
				</div>			
		    </div> <!-- row end -->			
			<div class="row" >
				<div class="table-responsive col-lg-12">
					<table class="table table-striped" >
			    		<tr><td align="center" class="info" colspan="12"><b style="font-size: 17px;font-style: italic;">Agreement Clauses</b></td></tr>
			 	
			    		<tr>
			    			<td class="info" colspan="2">
			    				<input  type="checkbox" name="buyer_nom" id="nom" value="Y" checked onclick="checkBoxValidation(this);" style="width:20px;height: 15px;">&nbsp;&nbsp;Buyer Nomination
			    			</td>
	    					<td colspan="2">
				     		  	<input  type="checkbox" name="chk_buyer_nom" value="M" style="width:20px;height: 15px;">&nbsp;Monthly
				     		</td>
				     		<td colspan="2">
				     	   	   	<input  type="checkbox" name="chk_buyer_nom" value="W"  style="width:20px;height: 15px;">&nbsp;Weekly
				     		</td>
				     		<td colspan="2">
				     	     	<input  type="checkbox" name="chk_buyer_nom" value="D" checked="checked" onclick="checkBoxValidation(this);"  style="width:20px;height: 15px;">&nbsp;Daily
				            </td>
				            <td class="info" colspan="2">Clause No.</td>
				            <td colspan="2">
				            	<input type="text" name="buyer_nom_clause" size="5" maxlength="10" value="<%=BUYER_NOM_CLAUSE%> ">
				            </td>
			    		</tr>
			    		
			    		<tr>
	    					<td class="info" colspan="2">
	    						<input  type="checkbox" name="seller_nom" value="Y" checked onclick="checkBoxValidation(this);" style="width:20px;height: 15px;">&nbsp;&nbsp;Seller Nomination
	    					</td>
	    					<td colspan="2">
				     		  	<input  type="checkbox" name="chk_seller_nom" value="M" style="width:20px;height: 15px;">&nbsp;Monthly
				     		</td>
				     		<td colspan="2">
				     	   	   	<input  type="checkbox" name="chk_seller_nom" value="W" style="width:20px;height: 15px;">&nbsp;Weekly
				     		</td>
				     		<td colspan="2">
				     	     	<input  type="checkbox" name="chk_seller_nom" value="D" checked="checked" onclick="checkBoxValidation(this);"  style="width:20px;height: 15px;">&nbsp;Daily
				            </td>
				            <td class="info" colspan="2">Clause No.</td>
				            <td colspan="2">
				            	<input type="text" name="seller_nom_clause" size="5" maxlength="10" value="<%=SELLER_NOM_CLAUSE%> ">
				            </td>
	    				</tr>
	    				<tr>
	    					<td class="info" colspan="2">
	    						<input  type="checkbox" name="day_def" value="Y" checked onclick="checkBoxValidation(this);">&nbsp;Day Definition
	    					</td>
	    					<td colspan="2">
						     	From &nbsp;
						     	<input type="text" name="day_time_from" value="06:00" size="3" maxlength="5" onblur="validateTime(this);">&nbsp;Hrs:Mins
						     </td>
						     <td colspan="2">	
						     	To&nbsp;
						     	<input type="text" name="day_time_to" value="06:00" size="3" maxlength="5" onblur="validateTime(this);">&nbsp;Hrs:Mins
						    </td>
						     <td colspan="2">&nbsp;</td>
						    <td class="info" colspan="2">
						    	<input type="checkbox" name="mdcq" value="Y" onclick="MDCQreadOnly();">&nbsp;MDCQ
						    </td>
						   
						    <td  align="left" colspan="2">
						     	<input type="text" name="mdcq_percent" id="mdcqPer" value="<%=MDCQ_PERCENTAGE%>" onblur="checkNumber1(this,5,2);checkMdcq();" size="3" maxlength="5" readonly="readonly">%&nbsp;of
						     	<select name="mdcq_qty_unit" class="select">
						     		<option value="1">DCQ</option>
						     	</select>
						     </td>
	    				</tr>
	    				<tr>
	    					<td class="info" colspan="2">
	    						<input  type="checkbox" name="measurement" value="Y" onclick="MeasurementQreadOnly();">&nbsp;Measurement
	    					</td>
	    					<td colspan="2">
	    						Standard:&nbsp;<input type="text" id="std" name="standard" value="" size="10" maxlength="15" readonly="readonly">
	    					</td>
	    					<td colspan="8">	
	    						Temperature:&nbsp;<input type="text" id="temp" name="temprature" value="" size="4" maxlength="6" readonly="readonly" onblur="checkNumber1(this,5,2);">&nbsp;Degree&nbsp;Centigrade
	    					</td>
	    				</tr>
	    				<tr>
	    					<td class="info" colspan="2">Delivery Pressure</td>
	    					<td colspan="2">
	    						Rate Min Bar:&nbsp;<input type="text" id="rate_min" name="rate_min_bar" value="" size="4" maxlength="6" readonly="readonly" onblur="checkNumber1(this,5,2);">
	    					</td>
	    					<td colspan="8">	
	    						Rate Max Bar:&nbsp;<input type="text" id="rate_max" name="rate_max_bar" value="" size="4" maxlength="6" readonly="readonly" onblur="checkNumber1(this,5,2);">
	    					</td>
	    				</tr>
	    				
	    				<tr>
	    					<td class="info" colspan="2"><input type="checkbox" name="off_spec_gas_chk" value="Y" checked onclick="offSpecreadOnly();">&nbsp;Off Spec Gas</td>
	    					<td colspan="2">
	    						Energy Base
	    						&nbsp;<select name="energy_off_spec" class="select">
						     		<option value="0">--Select--</option>
						     		<option value="1">GCV</option>
						     		<option value="2">NCV</option>
						     	</select>
	    					</td>
	    					<td colspan="2">
	    						Min&nbsp;<input type="text" id="off_spec_min_energy" name="min_energy" value="" size="5" maxlength="8" readonly="readonly" onblur="checkNumber1(this,7,2);">(Kcal/SCM)	    	
	    					</td>					
	    					<td colspan="6">
	    						Max&nbsp;<input type="text" id="off_spec_max_energy" name="max_energy" value="" size="5" maxlength="8" readonly="readonly" onblur="checkNumber1(this,7,2);">(Kcal/SCM)
	    					</td>	
	    				</tr>
	    				<%for(int i=0;i<clause_cd.size();i++)
							{	
								if(((String)clause_mst_cd.elementAt(i)).equals("0"))
								{%>
								<tr>
									<td class="info" colspan="2">
										<input type="checkbox" name="cls" value="<%=(String)clause_cd.elementAt(i)%>"  onclick="openBillingDetail('<%=(String)clause_cd.elementAt(i)%>','<%= (String)clause_nm.elementAt(i)%>','<%=FGSA_CD%>','<%=rev_no%>','<%=bscode%>')"/>&nbsp;<%=(String)clause_nm.elementAt(i)%>
									</td>
									
									
										<%int count = 0;
				    					for(int j=0;j<clause_cd.size();j++)
				    					{	
				    						if(((String)clause_cd.elementAt(i)).equals((String)clause_mst_cd.elementAt(j)))
				    						{	count++;%>
				    							<td colspan="2">
				     								<input type="checkbox" id="" name="cls1" value="<%=(String)clause_nm.elementAt(j)%>"  onclick="optionalCheckBoxValidation(this,'<%=i%>','<%=j%>',document.forms[0].cls,'<%=((String)clause_nm.elementAt(i))%>','<%=((String)clause_nm.elementAt(j))%>');">&nbsp;<%=(String)clause_nm.elementAt(j)%>
				     							</td>
				     				<%		}
				     					}
				     					if(count==0)
				     					{	%>
				     						<td colspan="6"></td>
				     				<%	}	
					     				if(count==2)
					     					{	%>
					     						<td colspan="2"></td>
					     				<%	}	%>	
									
				    			<%	if((""+clause_nm.elementAt(i)).trim().equalsIgnoreCase("Liability")) 
				    			    {	%>
				    			    	<td colspan="2" class="info">
				    			    		Clause No
				    			    	<td colspan="2">
				    			    		<input type="text" name="liability_clause" size="5" maxlength="10" value="<%=LIABILITY_CLAUSE%> ">
				    			    	</td>
				    			<%	}
				    				else if((""+clause_nm.elementAt(i)).trim().equalsIgnoreCase("Letter of Credit")) 
				    			    {	%>
				    			    	<td colspan="2" class="info">
				    			    		Clause No
				    			    	<td colspan="2">
				    			    		<input type="text" name="lc_clause" size="5" maxlength="10" value="<%=LC_CLAUSE %> ">
				    			    	</td>	
				    			<%	}
				    				else if((""+clause_nm.elementAt(i)).trim().equalsIgnoreCase("Billing Details")) 
				    			    {	%>
				    			    	<td colspan="2" class="info">
				    			    		Clause No
				    			    	<td colspan="2">
				    			    		<input type="text" name="billing_clause" size="5" maxlength="10" value="<%=BILLING_CLAUSE %> ">
				    			    	</td>	
				    			<%	}
				    				else
				    				{	%>
				    					<td colspan="4">&nbsp;</td>
				    			<%	}	%>
								</tr>
								<%} %>
						<%} %>	
						
						<tr>
							<td align="right" colspan="12"><span class="s-red" style="font-size: 14px;">*-Mandatory Information</span></td>
						</tr>
						<tr>
							<td align="right" colspan="12">
	    						<table >
	    							<tr>
	    								<td>
	    									<%if(activity.equals("update")){%>
					    						<button type="button" class="btn btn-warning" name="reset" value="Reset" onclick="setFGSACode('<%=bscode%>','<%=FGSA_CD%>','<%=activity%>')" > Reset </button>&nbsp;&nbsp;&nbsp;&nbsp;
					    					<%}else{ %>
												<button class="btn btn-warning" type="reset" name="reset" value="Reset">Reset </button>&nbsp;&nbsp;&nbsp;&nbsp;
					    					<%} %>
	    								</td>
	    								<td>
					    					<%if(contractEndFlag) { %>
												<%if(REOPEN_REQUEST_FLAG.equals("Y") && (REOPEN_APPROVAL_FLAG.equals("N") || REOPEN_APPROVAL_FLAG.equals(""))) { %>
													<input  type="button" class="btn btn-success" name='save' value='Submit' <%if(REOPEN_APPROVAL_FLAG.equals("N")) { %>title='Re-Activate Request DisApproved'<% } else { %>title='Re-Activate Request Sent'<% } %> disabled="disabled">
												<% } else if(REOPEN_REQUEST_FLAG.equals("Y") && REOPEN_APPROVAL_FLAG.equals("Y")) { 
													if(write_permission.trim().equalsIgnoreCase("Y")) {	%>
														<input type="button" class="btn btn-success" name="save" value="Submit" title="Click To Submit Data" onClick="<%if(buyer_plant_nm!=null){%>saveData('<%=buyer_plant_nm.length%>')<%}else{%>saveData('0');<%}%>">
												<%	} else {	%>
														<input type="button" class="btn btn-success" name="save" value="Submit" title="You Do Not Have Write Permission" style="font-size:15;font-weight:bold;" disabled>
												<%	} 
												} else if(REOPEN_REQUEST_FLAG.equals("N") || REOPEN_REQUEST_FLAG.equals(""))  { %>
													<input type="button" class="btn btn-success" name="save" value="Submit" title="Contract is Ended..Please Re-Activate to Submit" disabled>
												<% } 
											} else {
												if(write_permission.trim().equalsIgnoreCase("Y"))
												{	%>
													<input type="button" class="btn btn-success" name="save" value="Submit" onClick="<%if(buyer_plant_nm!=null){%>saveData('<%=buyer_plant_nm.length%>')<%}else{%>saveData('0');<%}%>">
											<%	}
												else
												{	%>
													<input type="button" class="btn btn-success" name="save" value="Submit" style="font-size:15;font-weight:bold;" disabled>
											<%	} }	%>
												
					    				</td>
	    							</tr>
	    						</table>
		    				</td>	
						</tr>		
			    	</table>
				</div>
			</div><!-- row end -->				
		</div>
</form>
</div>			
</div>
</div>
<!-- jQuery -->
<script src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script>
function reOpenContract() 
{
	var a = confirm("Do you want to Re-Activate Contract?");
	if(a) {
		document.forms[0].option.value = 'ReOpenFGSA';
		document.forms[0].submit();
	}
}

function enablerevisiondt(obj)
{//alert("here......."+document.getElementById("rev_dt").readOnly);
	if(obj.checked){
		document.getElementById("rev_dt").readOnly=false;
		document.getElementById("rev_dt").className="";
	}
	else
	{
		document.getElementById("rev_dt").readOnly=true;
		document.getElementById("rev_dt").className="mkrdly";
		document.getElementById("rev_dt").value="";
	}
}

function enablerevisiondt1(obj)
{//alert("here......."+document.getElementById("rev_dt").readOnly);
	if(obj.checked){
		document.getElementById("renewal_notice_dt").readOnly=false;
		document.getElementById("renewal_notice_dt").className="";
	}
	else
	{
		document.getElementById("renewal_notice_dt").readOnly=true;
		document.getElementById("renewal_notice_dt").className="mkrdly";
		document.getElementById("renewal_notice_dt").value="";
	}
}
</script> 
</body>
</html>
