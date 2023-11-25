<%@page import="org.apache.poi.util.SystemOutLogger"%>
<%@ page buffer="128kb"%>
<%@ page language="java" import="java.util.*" errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.NumberFormat"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>DLNG Set Debit/Credit Note</title>
<!-- CSS -->
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/sort/dataTables.bootstrap.min.css">

<!-- Bootstrap JS -->
<script type="text/javascript" src="../js/fms.js"></script>
<script  type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="../js/sweetalert.min.js"></script>
<link rel="stylesheet" href="../css/tlu.css">
<style type="text/css">
.s-red{
color: red;
font-size: 18px;
}

input[readonly]{
/*   background-color:transparent; */
  border: 0;
  font-size: 1em;
  background-color: #DFE32D;
}
</style>
</head>


<script type="text/javascript">
function ViewAllCargoList()
{
	var fy = document.forms[0].fy.value; 
	var bill_dt = document.forms[0].bill_dt.value; 
	var from_dt = document.forms[0].from_dt.value;
   	var to_dt = document.forms[0].to_dt.value;
   	var bscode = document.forms[0].cust_nm.value;
   	var cust_name = document.forms[0].cust_name.value;
   	var msg="";
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	var inv_typ = document.forms[0].inv_typ.value;
	
   	if(from_dt!=null && to_dt!=null && bscode!=null && trim(bscode)!="0" )
   	{
	   	if(trim(from_dt)!="" && trim(to_dt)!="")
	   	{
			var value = compareDate(from_dt,to_dt);			
			if(value!=1)
			{
				var url =modUrl+"?fy="+fy+"&bill_dt="+bill_dt+"&from_dt="+from_dt+"&to_dt="+to_dt+"&bscode="+bscode+"&cust_name="+cust_name+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl+"&inv_typ="+inv_typ;
			   // alert(url);
			    location.replace(url);
			}
			else
			{
				alert("Expected From Date Should be Less Or Equal to To Date...");
			    return false;
			} 
	   	}
	   	else
	   	{ 
	    	alert("Please Select From & To Dates...");
	   	}
	}
	else
	{
		alert("Please Select All the options ...");
	}
}


function SetFromTo_Dt()
{
	var fy = document.forms[0].fy.value; 
	var bill_dt = document.forms[0].bill_dt.value; 
	document.forms[0].from_dt.value=bill_dt; 
	var from_dt = document.forms[0].from_dt.value;
   	var to_dt = document.forms[0].to_dt.value; 
   	var bscode = document.forms[0].cust_nm.value;
//    	alert(bscode)
   	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	var inv_typ = document.forms[0].inv_typ.value;
	
   	if(fy!="" )
   	{ 
			var url = modUrl+"?fy="+fy+"&bill_dt="+bill_dt+"&from_dt="+from_dt+"&to_dt="+to_dt+"&bscode="+bscode+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl+"&inv_typ="+inv_typ;
		    location.replace(url);
   	}
   	else
   	{ 
    	alert("Please Select From & To Dates...");
   	}	
}

function ViewAllCargoListCustomer()
{
	var fy = document.forms[0].fy.value; 
	var bill_dt = document.forms[0].bill_dt.value; 
	var from_dt = document.forms[0].from_dt.value;
   	var to_dt = document.forms[0].to_dt.value;
   	var bscode = document.forms[0].cust_nm.value;
   	
   	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	var inv_typ = document.forms[0].inv_typ.value;
	
   	if(trim(from_dt)!="" && trim(to_dt)!="")
   	{
		var value = compareDate(from_dt,to_dt);
		if(value!=1)
		{
			var url =modUrl+"?fy="+fy+"&bill_dt="+bill_dt+"&from_dt="+from_dt+"&to_dt="+to_dt+"&bscode="+bscode+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&inv_typ="+inv_typ+"&modUrl="+modUrl;
		    location.replace(url);
		}
		else
		{
			alert("Expected From Date Should be Less Or Equal to To Date...");
		    return false;
		} 
   	}
   	else
   	{ 
    	alert("Please Select From & To Dates...");
   	}	
}

function drcr_enable_check(obj,sz,k,sel_criteria){
	
	if(document.getElementById('drcr_chk'+k).checked){
		document.getElementById('drcr_chkval'+k).value = "Y";
		if(sel_criteria.indexOf('DIFF-EXG')!=-1){
			document.getElementById('drcr_exgChk'+k).disabled=false;
		}else{
			document.getElementById('drcr_exgChk'+k).disabled=true;
		}
		if(sel_criteria.indexOf('DIFF-QTY')!=-1){
			document.getElementById('drcr_qtyChk'+k).disabled=false;
		}else{
			document.getElementById('drcr_qtyChk'+k).disabled=true;
		}
		if(sel_criteria.indexOf('DIFF-PRICE')!=-1){
			document.getElementById('drcr_priceChk'+k).disabled=false;
		}else{
			document.getElementById('drcr_priceChk'+k).disabled=true;
		}
		if(sel_criteria.indexOf('DIFF-TAX')!=-1){
			document.getElementById('drcr_taxChk'+k).disabled=false;
		}else{
			document.getElementById('drcr_taxChk'+k).disabled=true;
		}
		/* if(sel_criteria.indexOf('REV_INV')!=-1){
			document.getElementById('drcr_othChk'+k).disabled=false;
		}else{
			document.getElementById('drcr_othChk'+k).disabled=true;
		} */
		document.getElementById('drcr_othChk'+k).disabled=false;
	}else{
		document.getElementById('drcr_exgChk'+k).disabled=true;
		document.getElementById('drcr_qtyChk'+k).disabled=true;
		document.getElementById('drcr_priceChk'+k).disabled=true;
		document.getElementById('drcr_taxChk'+k).disabled=true;
		document.getElementById('drcr_othChk'+k).disabled=true;
		document.getElementById('drcr_chkval'+k).value = "N";
	}
}
function enable_check(obj,sz,ind,sel_criteria){
	 var size= parseInt(sz);
	 
	 if(size>1){
		 for(i=0;i<size;i++)
		 {
			if(document.forms[0].chk[i].checked==true)
			{
				var contTyp = document.getElementById('con_type'+i).value;
				var calcBase = document.getElementById('calcBase'+i).value;
				
				document.getElementById('othChk'+i).disabled=false;
				document.getElementById('taxChk'+i).disabled=false;
				document.getElementById('qtyChk'+i).disabled=true;
				
				if (contTyp == 'V'){
// 					alert(calcBase)
					if(calcBase == '1'){
						document.getElementById('mmbtuChk'+i).disabled=false;
						document.getElementById('qtyChk'+i).disabled=false;
					}if(calcBase == '2'){
						document.getElementById('kmChk'+i).disabled=false;
						document.getElementById('kmQtyChk'+i).disabled=false;
					}
					if(calcBase == '3'){
						document.getElementById('lmsmChk'+i).disabled=false;
					}
				}else{
					if(sel_criteria.indexOf('DIFF-EXG')!=-1){
						document.getElementById('exgChk'+i).disabled=true;
					}else{
						document.getElementById('exgChk'+i).disabled=false;
					}
					if(sel_criteria.indexOf('DIFF-QTY')!=-1){
						document.getElementById('qtyChk'+i).disabled=true;
					}else{
						document.getElementById('qtyChk'+i).disabled=false;
					}
					if(sel_criteria.indexOf('DIFF-PRICE')!=-1){
						document.getElementById('priceChk'+i).disabled=true;
					}else{
						document.getElementById('priceChk'+i).disabled=false;
					}
					if(sel_criteria.indexOf('REV_INV')!=-1){
						document.getElementById('othChk'+i).disabled=false;
					}else{
						document.getElementById('othChk'+i).disabled=false;
					}
					if(sel_criteria.indexOf('DIFF-TAX')!=-1){
						document.getElementById('taxChk'+i).disabled=false;
					}else{
						document.getElementById('taxChk'+i).disabled=false;
					}
				}
				
			}else{
				document.getElementById('priceChk'+i).disabled=true;
				document.getElementById('othChk'+i).disabled=true;
				document.getElementById('exgChk'+i).disabled=true;
				document.getElementById('qtyChk'+i).disabled=true;
				document.getElementById('taxChk'+i).disabled=true;
				
				var contTyp = document.getElementById('con_type'+i).value;
				var calcBase = document.getElementById('calcBase'+i).value;
				
				if(calcBase == '1'){
					document.getElementById('mmbtuChk'+i).disabled=true;
				}if(calcBase == '2'){
					document.getElementById('kmChk'+i).disabled=true;
					document.getElementById('kmQtyChk'+i).disabled=true;
				}if(calcBase == '3'){
					document.getElementById('lmsmChk'+i).disabled=true;
				}
			}
		 }
	 }else {
			 if(document.forms[0].chk.checked==true){
				 
					document.getElementById('othChk'+ind).disabled=false;
					document.getElementById('taxChk'+ind).disabled=false;
					document.getElementById('qtyChk'+ind).disabled=true;
					
				 var contTyp = document.getElementById('con_type'+ind).value;
				 var calcBase = document.getElementById('calcBase'+ind).value;
// 				 alert(contTyp+"***"+calcBase)
				 if (contTyp == 'V'){
					 if(calcBase == '1'){
						document.getElementById('mmbtuChk'+ind).disabled=false;
						document.getElementById('qtyChk'+ind).disabled=false;
					}if(calcBase == '2'){
						document.getElementById('kmChk'+ind).disabled=false;
						document.getElementById('kmQtyChk'+ind).disabled=false;
					}if(calcBase == '3'){
						document.getElementById('lmsmChk'+ind).disabled=false;
					}
				}else{
					
					if(sel_criteria.indexOf('DIFF-EXG')!=-1){
						document.getElementById('exgChk'+ind).disabled=true;
					}else{
						document.getElementById('exgChk'+ind).disabled=false;
					}
					if(sel_criteria.indexOf('DIFF-QTY')!=-1){
						document.getElementById('qtyChk'+ind).disabled=true;
					}else{
						document.getElementById('qtyChk'+ind).disabled=false;
					}
					if(sel_criteria.indexOf('DIFF-PRICE')!=-1){
						document.getElementById('priceChk'+ind).disabled=true;
					}else{
						document.getElementById('priceChk'+ind).disabled=false;
					}
					if(sel_criteria.indexOf('REV_INV')!=-1){
						document.getElementById('othChk'+ind).disabled=false;
					}else{
						document.getElementById('othChk'+ind).disabled=false;
					}
					if(sel_criteria.indexOf('DIFF-TAX')!=-1){
						document.getElementById('taxChk'+ind).disabled=false;
					}else{
						document.getElementById('taxChk'+ind).disabled=false;
					}
				}
				
			}else{
				
				 document.getElementById('priceChk'+ind).disabled=true;
				 document.getElementById('othChk'+ind).disabled=true;
				 document.getElementById('exgChk'+ind).disabled=true;
				 document.getElementById('qtyChk'+ind).disabled=true;
				 document.getElementById('taxChk'+ind).disabled=true;
				 
				 var contTyp = document.getElementById('con_type'+ind).value;
				 var calcBase = document.getElementById('calcBase'+ind).value;
				 
				 if(calcBase == '1'){
					document.getElementById('mmbtuChk'+ind).disabled=true;
				}
				if(calcBase == '2'){
					document.getElementById('kmChk'+ind).disabled=true;
					document.getElementById('kmQtyChk'+ind).disabled=true;
				}
				if(calcBase == '3'){
					document.getElementById('lmsmChk'+ind).disabled=true;
				}
			}
	 }
}

function drcr_setCriteria(obj,sz,k,flag_id){
	var criteria="";
	
	if(document.getElementById('drcr_othChk'+k).checked && document.getElementById('drcr_othFlg'+k).value == 'N' ){
		
		document.getElementById('drcr_othFlg'+k).value='Y';
		document.getElementById('drcr_priceChk'+k).checked = false;
		document.getElementById('drcr_exgChk'+k).checked = false;
		document.getElementById('drcr_qtyChk'+k).checked = false;
		document.getElementById('drcr_taxChk'+k).checked = false;
		document.getElementById('drcr_mmbtuChk'+k).checked = false;
		document.getElementById('drcr_kmChk'+k).checked = false;
		document.getElementById('drcr_kmQtyChk'+k).checked = false;
		
		document.getElementById('drcr_priceFlg'+k).value='N';
		document.getElementById('drcr_exgFlg'+k).value='N';
		document.getElementById('drcr_qtyFlg'+k).value='N';
		document.getElementById('drcr_taxFlg'+k).value='N';
		document.getElementById('drcr_mmbtuFlg'+k).value='N';
		document.getElementById('drcr_kmFlg'+k).value='N';
		document.getElementById('drcr_kmQtyFlg'+k).value='N';

		criteria+= document.getElementById('drcr_othChk'+k).value+"--";
	}else {
		if(document.getElementById('drcr_mmbtuChk'+k).checked){
			document.getElementById('drcr_mmbtuFlg'+k).value='Y';
			criteria+= document.getElementById('drcr_mmbtuChk'+k).value+"--";
		}else{
			document.getElementById('drcr_mmbtuFlg'+k).value='N';
		}
		
		if(document.getElementById('drcr_kmChk'+k).checked){
			document.getElementById('drcr_kmFlg'+k).value='Y';
			criteria+= document.getElementById('drcr_kmChk'+k).value+"--";
		}else{
			document.getElementById('drcr_kmFlg'+k).value='N';
		}
		
		if(document.getElementById('drcr_kmQtyChk'+k).checked){
			document.getElementById('drcr_kmQtyFlg'+k).value='Y';
			criteria+= document.getElementById('drcr_kmQtyChk'+k).value+"--";
		}else{
			document.getElementById('drcr_kmQtyFlg'+k).value='N';
		}
		
		 if(document.getElementById('drcr_lmsmChk'+k).checked){
			document.getElementById('drcr_lmsmFlg'+k).value='Y';
			criteria+= document.getElementById('drcr_lmsmChk'+k).value+"--";
		}else{
			document.getElementById('drcr_lmsmFlg'+k).value='N';
		}
		
		if(document.getElementById('drcr_priceChk'+k).checked && !document.getElementById('drcr_priceChk'+k).disabled){
			document.getElementById('drcr_priceFlg'+k).value='Y';
			criteria+= document.getElementById('drcr_priceChk'+k).value+"--";
		}else{
			document.getElementById('drcr_priceFlg'+k).value='N';
		}
		
		if(document.getElementById('drcr_exgChk'+k).checked && !document.getElementById('drcr_exgChk'+k).disabled){
			document.getElementById('drcr_exgFlg'+k).value='Y';
			criteria+= document.getElementById('drcr_exgChk'+k).value+"--";
		}else{
			document.getElementById('drcr_exgFlg'+k).value='N';
		}
		if(document.getElementById('drcr_qtyChk'+k).checked && !document.getElementById('drcr_qtyChk'+k).disabled){
			document.getElementById('drcr_qtyFlg'+k).value='Y';
			criteria+= document.getElementById('drcr_qtyChk'+k).value+"--";
		}else{
			document.getElementById('drcr_qtyFlg'+k).value='N';
		}
		
		if(document.getElementById('drcr_taxChk'+k).checked && !document.getElementById('drcr_taxChk'+k).disabled){
			document.getElementById('drcr_taxFlg'+k).value='Y';
			criteria+= document.getElementById('drcr_taxChk'+k).value+"--";
		}else{
			document.getElementById('drcr_taxFlg'+k).value='N';
		}
		document.getElementById('drcr_othFlg'+k).value='N';
		document.getElementById('drcr_othChk'+k).checked = false;
	}
	document.getElementById('drcr_criteria'+k).value=criteria;
	 
}
function setCriteria(obj,sz,indx,flag_id){

	var criteria="";
	var size= parseInt(sz);
	if(size>1){
		 for(i=0;i<size;i++)
		 {
			if(document.forms[0].chk[i].checked==true && i == indx)
			{
				if(document.getElementById('othChk'+i).checked && !document.getElementById('othChk'+i).disabled){
					
					document.getElementById('othFlg'+i).value='Y';
					document.getElementById('priceChk'+i).checked = false;
					document.getElementById('exgChk'+i).checked = false;
					document.getElementById('qtyChk'+i).checked = false;
					document.getElementById('taxChk'+i).checked = false;
					document.getElementById('mmbtuChk'+i).checked = false;
					document.getElementById('kmChk'+i).checked = false;
					document.getElementById('kmQtyChk'+i).checked = false;
					
					document.getElementById('priceFlg'+i).value='N';
					document.getElementById('exgFlg'+i).value='N';
					document.getElementById('qtyFlg'+i).value='N';
					document.getElementById('taxFlg'+i).value='N';
					document.getElementById('mmbtuFlg'+i).value='N';
					document.getElementById('kmFlg'+i).value='N';
					document.getElementById('kmQtyFlg'+i).value='N';
			
					criteria+= document.getElementById('othChk'+i).value+"--";
					
				}else{
					
					if(document.getElementById('mmbtuChk'+i).checked){
						document.getElementById('mmbtuFlg'+i).value='Y';
						criteria+= document.getElementById('mmbtuChk'+i).value+"--";
					}else{
						document.getElementById('mmbtuFlg'+i).value='N';
					}
					
					if(document.getElementById('kmChk'+i).checked){
						document.getElementById('kmFlg'+i).value='Y';
						criteria+= document.getElementById('kmChk'+i).value+"--";
					}else{
						document.getElementById('kmFlg'+i).value='N';
					}
					
					if(document.getElementById('kmQtyChk'+i).checked){
						document.getElementById('kmQtyFlg'+i).value='Y';
						criteria+= document.getElementById('kmQtyChk'+i).value+"--";
					}else{
						document.getElementById('kmQtyFlg'+i).value='N';
					}
					
					 if(document.getElementById('lmsmChk'+i).checked){
						document.getElementById('lmsmFlg'+i).value='Y';
						criteria+= document.getElementById('lmsmChk'+i).value+"--";
					}else{
						document.getElementById('lmsmFlg'+i).value='N';
					}
					
					if(document.getElementById('priceChk'+i).checked && !document.getElementById('priceChk'+i).disabled){
						document.getElementById('priceFlg'+i).value='Y';
						criteria+= document.getElementById('priceChk'+i).value+"--";
					}else{
						document.getElementById('priceFlg'+i).value='N';
					}
					
					if(document.getElementById('exgChk'+i).checked && !document.getElementById('exgChk'+i).disabled){
						document.getElementById('exgFlg'+i).value='Y';
						criteria+= document.getElementById('exgChk'+i).value+"--";
					}else{
						document.getElementById('exgFlg'+i).value='N';
					}
					if(document.getElementById('qtyChk'+i).checked && !document.getElementById('qtyChk'+i).disabled){
						document.getElementById('qtyFlg'+i).value='Y';
						criteria+= document.getElementById('qtyChk'+i).value+"--";
					}else{
						document.getElementById('qtyFlg'+i).value='N';
					}
					
					if(document.getElementById('taxChk'+i).checked && !document.getElementById('taxChk'+i).disabled){
						document.getElementById('taxFlg'+i).value='Y';
						criteria+= document.getElementById('taxChk'+i).value+"--";
					}else{
						document.getElementById('taxFlg'+i).value='N';
					}
					
					document.getElementById('othFlg'+i).value='N';
					document.getElementById('othChk'+i).checked = false;
				}
			}else{
				
				/* document.getElementById('othChk'+i).checked = false;
				document.getElementById('priceChk'+i).checked = false;
				document.getElementById('exgChk'+i).checked = false;
				document.getElementById('qtyChk'+i).checked = false;
				
				document.getElementById('priceFlg'+i).value='N';
				document.getElementById('exgFlg'+i).value='N';
				document.getElementById('qtyFlg'+i).value='N';
				document.getElementById('othFlg'+i).value='N'; */
			}
		 }
// 		 alert(criteria)
		 document.getElementById('criteria'+indx).value=criteria;
		 criteria="";
		 
	}else{
		
		if(document.forms[0].chk.checked==true)
		{
			if(document.getElementById('othChk'+indx).checked  && !document.getElementById('othChk'+indx).disabled){
				
				document.getElementById('othFlg'+indx).value='Y';
				document.getElementById('priceChk'+indx).checked = false;
				document.getElementById('exgChk'+indx).checked = false;
				document.getElementById('qtyChk'+indx).checked = false;
				document.getElementById('taxChk'+indx).checked = false;
				document.getElementById('mmbtuChk'+indx).checked = false;
				document.getElementById('kmChk'+indx).checked = false;
				document.getElementById('kmQtyChk'+indx).checked = false;
				
				document.getElementById('priceFlg'+indx).value='N';
				document.getElementById('exgFlg'+indx).value='N';
				document.getElementById('qtyFlg'+indx).value='N';
				document.getElementById('taxFlg'+indx).value='N';
				document.getElementById('lmsmFlg'+indx).value='N';
				document.getElementById('mmbtuFlg'+indx).value='N';
				document.getElementById('kmFlg'+indx).value='N';
				document.getElementById('kmQtyFlg'+indx).value='N';
				
				criteria+= document.getElementById('othChk'+indx).value+"--";
			}else{
//	 			alert(obj.id)

				if(document.getElementById('mmbtuChk'+indx).checked){
					document.getElementById('mmbtuFlg'+indx).value='Y';
					criteria+= document.getElementById('mmbtuChk'+indx).value+"--";
				}else{
					document.getElementById('mmbtuFlg'+indx).value='N';
				}
				
				if(document.getElementById('kmChk'+indx).checked){
					document.getElementById('kmFlg'+indx).value='Y';
					criteria+= document.getElementById('kmChk'+indx).value+"--";
				}else{
					document.getElementById('kmFlg'+indx).value='N';
				}
				
				if(document.getElementById('kmQtyChk'+indx).checked){
					document.getElementById('kmQtyFlg'+indx).value='Y';
					criteria+= document.getElementById('kmQtyChk'+indx).value+"--";
				}else{
					document.getElementById('kmQtyFlg'+indx).value='N';
				}
				
				if(document.getElementById('lmsmChk'+indx).checked){
					document.getElementById('lmsmFlg'+indx).value='Y';
					criteria+= document.getElementById('lmsmChk'+indx).value+"--";
				}else{
					document.getElementById('lmsmFlg'+indx).value='N';
				} 
				
				if(document.getElementById('priceChk'+indx).checked && !document.getElementById('priceChk'+indx).disabled){
					document.getElementById('priceFlg'+indx).value='Y';
					criteria+= document.getElementById('priceChk'+indx).value+"--";
				}else{
					document.getElementById('priceFlg'+indx).value='N';
				}
				
				if(document.getElementById('exgChk'+indx).checked  && !document.getElementById('exgChk'+indx).disabled){
					document.getElementById('exgFlg'+indx).value='Y';
					criteria+= document.getElementById('exgChk'+indx).value+"--";
				}else{
					document.getElementById('exgFlg'+indx).value='N';
				}
				if(document.getElementById('qtyChk'+indx).checked && !document.getElementById('qtyChk'+indx).disabled){
					document.getElementById('qtyFlg'+indx).value='Y';
					criteria+= document.getElementById('qtyChk'+indx).value+"--";
				}else{
					document.getElementById('qtyFlg'+indx).value='N';
				}
				
				if(document.getElementById('taxChk'+indx).checked && !document.getElementById('taxChk'+indx).disabled){
					document.getElementById('taxFlg'+indx).value='Y';
					criteria+= document.getElementById('taxChk'+indx).value+"--";
				}else{
					document.getElementById('taxFlg'+indx).value='N';
				}
				
				document.getElementById('othFlg'+indx).value='N';
				document.getElementById('othChk'+indx).checked = false;
			}
			document.getElementById('criteria'+indx).value=criteria;
			criteria="";
		}else{
			
			/* document.getElementById('othChk'+indx).checked = false;
			document.getElementById('priceChk'+indx).checked = false;
			document.getElementById('exgChk'+indx).checked = false;
			document.getElementById('qtyChk'+indx).checked = false;
			
			document.getElementById('priceFlg'+indx).value='N';
			document.getElementById('exgFlg'+indx).value='N';
			document.getElementById('qtyFlg'+indx).value='N';
			document.getElementById('othFlg'+indx).value='N'; */
		}
	}
}	
	
function doSubmit(sz)
{
   var fy = document.forms[0].fy.value; //SB20160527
	var bill_dt = document.forms[0].bill_dt.value; //SB20160527
	var from_dt = document.forms[0].from_dt.value;
   	var to_dt = document.forms[0].to_dt.value;
   	var bscode = document.forms[0].cust_nm.value;
   	var cnt=0;
   	var rem_msg = '';
   	
   	if(sz == 1)
   	{
   		var k = 0 ; 
   		var dr_cr_cnt = document.forms[0].dr_cr_cnt.value;
   		if(document.forms[0].chk.checked==true)
   		{
   		  	document.forms[0].chkval.value="Y";
   		 	var rem = document.forms[0].criteria.value;
		 	if(rem=='' || rem==' ' || rem.length==0) {
		 		rem_msg += 'Select Criteria For Selected Invoice - '+document.forms[0].new_inv_no.value+'..!!!';
		 	}
   		  	cnt++;
   		}
   		else
   		{
   		 	document.forms[0].chkval.value="N";
   		}
   		
   		if(parseFloat(dr_cr_cnt) == 1 ){
   			
   			if(document.getElementById('drcr_chkval'+k).value == 'Y'){
	   			var drcr_criteria = document.getElementById('drcr_criteria'+k).value;
					if(drcr_criteria=='' || drcr_criteria==' ' || drcr_criteria.length==0) {
						rem_msg += 'Select Criteria For Selected DR/CR Note - '+ document.getElementById('dr_cr_doc_no'+k).value+'..!!!\n';
					}
				cnt++;
   			}
   			k++;
   		}else if(parseFloat(dr_cr_cnt) > 1 ){
   			
   			for(var j = 0 ; j < parseFloat(dr_cr_cnt) ; j++){
   				if(document.getElementById('drcr_chkval'+k).value == 'Y'){
	   				var drcr_criteria = document.getElementById('drcr_criteria'+k).value;
	   				if(drcr_criteria=='' || drcr_criteria==' ' || drcr_criteria.length==0) {
	   					rem_msg += 'Select Criteria For Selected DR/CR Note - '+ document.getElementById('dr_cr_doc_no'+k).value+'..!!!\n';
	   				}
	   				cnt++;
   				}
   				k++;
   			}
   		}
   	} else {
   		var k = 0;
   		for(i=0;i<sz;i++)
   		{
   			var dr_cr_cnt = document.forms[0].dr_cr_cnt[i].value;
   			
	   		if(document.forms[0].chk[i].checked==true)
	   		{
	   		 	document.forms[0].chkval[i].value="Y";
	   		 	var rem = document.forms[0].criteria[i].value;
	   		 	if(rem=='' || rem==' ' || rem.length==0) {
	   		 		rem_msg += 'Select Criteria For Selected Invoice - '+document.forms[0].new_inv_no[i].value+'..!!!\n';
	   		 	}
	   		  	cnt++;
	   		}
	   		else
	   		{
	   		 	document.forms[0].chkval[i].value="N";
	   		}
	   		
	   		if(parseFloat(dr_cr_cnt) == 1 ){
	   			if(document.getElementById('drcr_chkval'+k).value == 'Y'){
		   			var drcr_criteria = document.getElementById('drcr_criteria'+k).value;
	   				if(drcr_criteria=='' || drcr_criteria==' ' || drcr_criteria.length==0) {
	   					rem_msg += 'Select Criteria For Selected DR/CR Note - '+ document.getElementById('dr_cr_doc_no'+k).value+'..!!!\n';
	   				}
	   				cnt++;
	   			}
	   			k++;
	   		}else if(parseFloat(dr_cr_cnt) > 1 ){
	   			
	   			for(var j = 0 ; j < parseFloat(dr_cr_cnt) ; j++){
	   				if(document.getElementById('drcr_chkval'+k).value == 'Y'){
		   				var drcr_criteria = document.getElementById('drcr_criteria'+k).value;
		   				if(drcr_criteria=='' || drcr_criteria==' ' || drcr_criteria.length==0) {
		   					rem_msg += 'Select Criteria For Selected DR/CR Note - '+ document.getElementById('dr_cr_doc_no'+k).value+'..!!!\n';
		   				}
		   				cnt++;
	   				}
	   				k++;
	   			}
	   		}
   	 	}
   	}
   	
	 
	 if(bscode!="0" && fy!="0" && from_dt!="" && to_dt!="" && bill_dt!="0"){
	 if(cnt>0){
		 if(rem_msg=='') {
			 var a = confirm("Do You Want To Submit Data Regarding Dr./Cr. Note Details ???");
		    	if(a)
		    	{
		    		document.forms[0].submit();    
		    	}
		 } else {
			 alert(rem_msg);
		 }	
    	} else {
    		alert("Please Select Atleast One Record !!!");
    	}
    }
    else{
    	alert("Please Select All the Data!!!");
    }
}
</script>

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
String modUrl = session.getAttribute("modUrl")==null?"":session.getAttribute("modUrl").toString();//HA20200227

String cust_name = request.getParameter("cust_name")==null?"":request.getParameter("cust_name");		
String bscode = request.getParameter("bscode")==null?"0":request.getParameter("bscode");		
String cr_dr = request.getParameter("cr_dr")==null?"":request.getParameter("cr_dr");
String criteria = request.getParameter("criteria")==null?"":request.getParameter("criteria");		
String from_dt = request.getParameter("from_dt")==null?sysdate:request.getParameter("from_dt");
String to_dt = request.getParameter("to_dt")==null?sysdate:request.getParameter("to_dt");
// System.out.println("from_dt****"+from_dt);
String bill_dt = request.getParameter("bill_dt")==null?"0":request.getParameter("bill_dt");
String fy = request.getParameter("fy")==null?"0":request.getParameter("fy");
String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
String inv_typ = request.getParameter("inv_typ")==null?"":request.getParameter("inv_typ");

if(bill_dt.equals("0")){
	from_dt=sysdate;
}

drcr.setCallFlag("InvAvailableFY");
drcr.setFy(fy);

drcr.init();

Vector VInv_FY = drcr.getVInv_FY();	
Vector VInv_Dt_FY = drcr.getVInv_Dt_FY();

if(!bill_dt.equals("0"))
{
	drcr.setCallFlag("dr_cr_note");
	drcr.setFrom_dt(from_dt);
	drcr.setTo_dt(to_dt);
	drcr.setCriteria(criteria);
	drcr.setCr_dr(cr_dr);
	drcr.setCustomer_cd(bscode);
	drcr.setActivity(activity); 
	drcr.setCr_dr_yr(fy); 
	drcr.setInv_typ(inv_typ);
	drcr.init();
}
Vector CUST_CD = drcr.getCUST_CD();
Vector CUST_NM = drcr.getCUST_NM();

Vector SALE_PRICE = drcr.getSALE_PRICE();	
Vector GROSS_AMT_INR = drcr.getGROSS_AMT_INR();
Vector NET_AMT_INR = drcr.getNET_AMT_INR();
Vector TOTAL_QTY = drcr.getTOTAL_QTY();
Vector INVOICE_DT = drcr.getINVOICE_DT();
Vector PERIOD_START_DT = drcr.getPERIOD_START_DT();
Vector PERIOD_END_DT = drcr.getPERIOD_END_DT();
Vector FLAG=drcr.getFLAG();
Vector DR_CR_DTL_REM=drcr.getDR_CR_DTL_REM();

Vector PLANT_SEQ_NO = drcr.getPLANT_SEQ_NO();
Vector HLPL_INV_SEQ_NO = drcr.getHLPL_INV_SEQ_NO();
Vector CONTRACT_TYPE = drcr.getCONTRACT_TYPE();
Vector FINANCIAL_YEAR = drcr.getFINANCIAL_YEAR();
Vector INVOICE_NO = drcr.getINVOICE_NO();
Vector DUE_DT = drcr.getDUE_DT();
Vector EXCHG_RATE_VALUE = drcr.getEXCHG_RATE_VALUE();
Vector SN_NO = drcr.getSN_NO();
Vector SN_REV_NO = drcr.getSN_REV_NO();
Vector FGSA_NO = drcr.getFGSA_NO();
Vector FGSA_REV_NO = drcr.getFGSA_REV_NO();
Vector GROSS_AMT_USD = drcr.getGROSS_AMT_USD();
Vector TAX_STRUCT_CD = drcr.getTAX_STRUCT_CD();
Vector NEW_INV_SEQ_NO = drcr.getNEW_INV_SEQ_NO();
Vector DR_CR_MAPPING_ID=drcr.getDR_CR_MAPPING_ID();
Vector TAX_AMT_INR=drcr.getTAX_AMT_INR();
Vector CALC_BASE_SERVICE = drcr.getCALC_BASE_SERVICE();

Vector Vdr_cr_cnt = drcr.getVdr_cr_cnt();
Vector DR_CR_FIN_YEAR= drcr.getDR_CR_FIN_YEAR(); 
Vector DR_CR_NO= drcr.getDR_CR_NO();
Vector DR_CR_DOC_NO= drcr.getDR_CR_DOC_NO();
Vector DR_CR_SEQ_NO= drcr.getDR_CR_SEQ_NO();
Vector DR_CR_DT = drcr.getDR_CR_DT();
Vector DR_CR_QTY = drcr.getDR_CR_QTY();
Vector DR_CR_SALE_RATE = drcr.getDR_CR_SALE_RATE();
Vector DR_CR_GROSS_AMT_INR = drcr.getDR_CR_GROSS_AMT_INR();
Vector DR_CR_NET_AMT_INR = drcr.getDR_CR_NET_AMT_INR();
Vector DR_CR_CRITERIA = drcr.getDR_CR_CRITERIA(); 
// System.out.println("CALC_BASE_SERVICE****"+CALC_BASE_SERVICE);

%>

<body>
	<div class="tab-content">
		<div class="tab-pane active" >
			<div class="box mb-0">
				<form method="post" action="../servlet/Frm_dr_cr_note" >
					<input type="hidden" name="emp_cd" value="<%=user_cd%>">
<%-- 					<input type="hidden" name="comp_cd" value="<%=comp_cd%>"> --%>
					<input type="hidden" name="sysdate" value="<%=sysdate%>">
					<input type="hidden" name="modCd" value="<%=modCd%>">
					<input type="hidden" name="formId" value="<%=formId%>">
					<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
					<input type="hidden" name="modUrl" value="<%=modUrl%>">	
					<input type="hidden" name="user_cd" value="<%=user_cd%>">
					<input type="hidden" name="sysdate" value="<%=sysdate %>" >
					<input type="hidden" name="option" value="SetDrCrNote">
					<input type="hidden" name="bill_dt1" value="<%=bill_dt %>">
			    	<input type="hidden" name="from_dt1" value="<%=from_dt %>">
			    	<input type="hidden" name="to_dt1" value="<%=to_dt %>">
			    	<input type="hidden" name="fy1" value="<%=fy %>">
			    	<input type="hidden" name="customer_cd" value="<%=bscode%>">
			    	
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
				
				<% if(!erro_msg.equals("")) {%>
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
				<div class="box-header with-border" style="background-color:#ffe57f">
						<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="form-group">
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
									<label>Fin. Year</label>
									<div class="form-group">
										<select class="form-control" required="required" name="fy" onchange="SetFromTo_Dt();">
											<option value="0">--Select--</option>
											<%for(int i=0;i<VInv_FY.size();i++) {%>
												<option value="<%=VInv_FY.elementAt(i)%>"><%=VInv_FY.elementAt(i)%></option>
											<%}%>	
										</select> 
										<script type="text/javascript">
											document.forms[0].fy.value="<%=fy%>";
										</script>
									</div>	
								</div>
								
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
									<label>Billing Date</label>
									<div class="form-group">
										<select class="form-control" required="required" name="bill_dt" onchange="SetFromTo_Dt();" onblur="validateDate(this);" >
											<option value="0" selected="selected">--Select--</option>
											<%for(int i=0;i<VInv_Dt_FY.size();i++) {%>
												<option value="<%=VInv_Dt_FY.elementAt(i)%>"><%=VInv_Dt_FY.elementAt(i)%></option>
											<%}%>	
										</select> 
										<script type="text/javascript">
											document.forms[0].bill_dt.value="<%=bill_dt%>";
										</script>
									</div>	
								</div>
								<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
										<label>From Date</label>
										<div class="form-group ">
											<div class='input-group date'>
												<input type="text" value="<%=from_dt%>" placeholder="dd/mm/yyyy"  class="form-control datepick" name="from_dt"  required="required" autocomplete="off" onblur="validateDate(this);" >
												<span class="input-group-addon" >
													<i class="fa fa-calendar"></i>
												</span>
											</div>
										</div>	
									</div>
									
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
										<label>To Date</label>
										<div class="form-group ">
											<div class='input-group date'>
												<input type="text" value="<%=to_dt%>" placeholder="dd/mm/yyyy"  class="form-control datepick" id="to_dt" name="to_dt"  required="required" autocomplete="off" onblur="validateDate(this);" >
												<span class="input-group-addon" >
													<i class="fa fa-calendar"></i>
												</span>
											</div>
										</div>	
									</div>
									
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
										<label>Customer</label>
										<div class="form-group">
											<select class="form-control" required="required" name="cust_nm" onchange="ViewAllCargoListCustomer();">
												<option value="0" selected="selected">-Select-</option>
												<%for(int i=0;i<CUST_CD.size();i++){%>
												  <option value="<%=CUST_CD.elementAt(i)%>"><%=CUST_NM.elementAt(i)%></option>						
												  		<%  if((""+CUST_CD.elementAt(i)).trim().equals(bscode)) 
												  		{	
													  		cust_name=""+CUST_NM.elementAt(i);
													 	 }
												   	 }%>	
											</select> 
											<input type="hidden" name="cust_name">
											<script>
												document.forms[0].cust_nm.value="<%=bscode%>";
												document.forms[0].cust_name.value="<%=cust_name%>";
											</script>
										</div>	
									</div>
									
									<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
										<label>Invoice Type</label>
										<div class="form-group">
											<select class="form-control" name="inv_typ" id = "inv_typ" onchange="ViewAllCargoListCustomer();">
												<option value="" selected="selected">-All-</option>
												<option value="C">Commodity</option>
												<option value="S">Service</option>						
											</select> 
											<script>
												document.getElementById('inv_typ').value="<%=inv_typ%>";
											</script>
										</div>	
									</div>
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
<!-- 										<label>&nbsp;</label> -->
										<div class="form-group">
											<button type="button" class="btn btn-primary" name="view" value="View List" onclick="ViewAllCargoList();">View List</button>
										</div>
									</div>
								</div>	
							</div>
						</div>
					</div>
					
					<div class="box-body table-responsive no-padding" style="height: 400px;overflow: scroll;">
						<table id="example" class="table table-bordered">
							<thead>
								<tr><td colspan="9" align="center"> <font color="black"> <b style="font-size: 18px;">
								 		Invoice List
								 </b> </font></td></tr>   
								<tr class="info">
									<th class="text-center" colspan="1" >Seq.</th>
									<th class="text-center" colspan="1" >Select</th>
									<th class="text-center" colspan="1"  >Invoice No</th>
									<th class="text-center" colspan="1" >Invoice Date</th>
<!-- 									<th class="text-center" colspan="2">Invoice Duration </th> -->
									<th class="text-center" colspan="1" >Invoice Quantity<BR>(MMBTU)</th>
									<th class="text-center" colspan="1" >Sales Rate<BR>($/MMBTU)</th>
									<th class="text-center" colspan="1" >Gross Amount<BR>(INR)</th>
									<th class="text-center" colspan="1" >Total Amount(INR)<br>(Including Taxes)</th>
									<th class="text-center" colspan="1" >Criteria Selection</th>
								</tr>
								<!-- <tr class="info">
									<th class="text-center" colspan="1">From Date</th>
									<th class="text-center" colspan="1">To Date</th>
								</tr>	 -->
							</thead>
							<tbody>
							<% 
							int k = 0 ; 
							if(PERIOD_START_DT.size()!=0){ 
// 								System.out.println("PERIOD_START_DT.size()***"+PERIOD_START_DT.size());
// 								System.out.println("DR_CR_DTL_REM.size()***"+DR_CR_DTL_REM.size());
								for(int i=0;i<PERIOD_START_DT.size();i++){
									String selPrice = "",selOth="",selExg="",selQTY="",selTax="",selMmbtu = "",selKm = "", sellmsm = "",selKmQty = "";
									
									if(DR_CR_DTL_REM.elementAt(i).toString().contains("--")){
										
										String[] temp_res = DR_CR_DTL_REM.elementAt(i).toString().split("--");
										
										for(int j = 0 ; j < temp_res.length; j ++){
// 							 				System.out.println("temp_res[i]***"+temp_res[j]);
											
											if(temp_res[j].equals("DIFF-PRICE")){
												selPrice="checked='checked'";
											}
											if(temp_res[j].equals("DIFF-QTY")){
												selQTY="checked='checked'";
											}
											if(temp_res[j].equals("DIFF-EXG")){
												selExg="checked='checked'";
											}
											if(temp_res[j].equals("REV_INV")){
												selOth="checked='checked'";
											}
											if(temp_res[j].equals("DIFF-TAX")){
												selTax="checked='checked'";
											}
											if(temp_res[j].equals("DIFF-INRMMBTU")){
												selMmbtu="checked='checked'";
											}
											if(temp_res[j].equals("DIFF-INRKM")){
												selKm="checked='checked'";
											}
											if(temp_res[j].equals("DIFF-LUMPSUM")){
												sellmsm="checked='checked'";
											}
											if(temp_res[j].equals("DIFF-KM")){
												selKmQty="checked='checked'";
											}
										}
									}
									
									if(i%2==0){%>
									<tr bgcolor="CFF3D7" style="text-align:center; font-size: 13px; " >
									<%}else{ %>
									<tr bgcolor="#EDEDED" style="text-align:center; font-size: 13px; " >
									<%} %>
									<%if(!DR_CR_DTL_REM.elementAt(i).equals("")){ %>
									<tr style="font-size: 13px;text-align:center;">
									<%} %>
									<td><%=i+1 %></td>
									<td>
										<input type="hidden" name = "calcBase" id = "calcBase<%=i %>" value="<%=CALC_BASE_SERVICE.elementAt(i) %>">
										<%if(!FLAG.elementAt(i).equals("X")) { %>				
											<input type="checkbox" id="chk1<%=i %>" name="chk" TITLE="TYPE:PLANT:FGSA:Rev:SN:Rev" onclick="enable_check(this,'<%=NEW_INV_SEQ_NO.size()%>','<%=i%>','<%=DR_CR_DTL_REM.elementAt(i)%>');">
										<%} else { %>
											<input type="checkbox" id="chk1<%=i %>" name="chk" TITLE="TYPE:PLANT:FGSA:Rev:SN:Rev" onclick="enable_check(this,'<%=NEW_INV_SEQ_NO.size()%>','<%=i%>','<%=DR_CR_DTL_REM.elementAt(i)%>');">
											<FONT SIZE=2 COLOR=RED TITLE="Already CR Note Generated(Reversal of Invoice)" ><B><%=FLAG.elementAt(i)%></B></FONT>
										<%} %>
											<input type="hidden" name="chkval" value="N">
											<FONT SIZE=2 style="font-weight: bolder; ">&nbsp;<B><%=CONTRACT_TYPE.elementAt(i)%>:<%=PLANT_SEQ_NO.elementAt(i)%>:<%=FGSA_NO.elementAt(i)%>:<%=FGSA_REV_NO.elementAt(i)%>:<%=SN_NO.elementAt(i)%>:<%=SN_REV_NO.elementAt(i)%></B></FONT>
										</td>
										<td ><%=NEW_INV_SEQ_NO.elementAt(i)%></td>
										<td style="text-align:center; font-size: 13px; " ><%=INVOICE_DT.elementAt(i)%></td>
<%-- 										<td style="text-align:center; font-size: 13px; " ><%=PERIOD_START_DT.elementAt(i)%></td> --%>
<%-- 										<td style="text-align:center; font-size: 13px; " ><%=PERIOD_END_DT.elementAt(i)%></td> --%>
										<td  class="text-right" ><%=TOTAL_QTY.elementAt(i)%></td>
										<td  class="text-right"><%=SALE_PRICE.elementAt(i)%></td>
										<td  class="text-right"><%=GROSS_AMT_INR.elementAt(i)%></td>
										<td  class="text-right"><%=NET_AMT_INR.elementAt(i)%></td>
										<td class="text-left">
											<input type="checkbox" <%=selExg %> value="DIFF-EXG" disabled="disabled" id="exgChk<%=i%>"  name="exgChk<%=i%>" onclick="setCriteria(this,'<%=NEW_INV_SEQ_NO.size()%>','<%=i%>','exgFlg<%=i %>');"> Difference in Exchange Rate&nbsp;
											<input type="checkbox" <%=selPrice %> value="DIFF-PRICE" disabled="disabled" id="priceChk<%=i%>"  name="priceChk<%=i%>" onclick="setCriteria(this,'<%=NEW_INV_SEQ_NO.size()%>','<%=i%>','priceFlg<%=i %>');"> Change in Price/Tariff&nbsp;
											<br>
											<input type="checkbox"  <%=selQTY %> value="DIFF-QTY" disabled="disabled" id="qtyChk<%=i%>"  name="qtyChk<%=i%>" onclick="setCriteria(this,'<%=NEW_INV_SEQ_NO.size()%>','<%=i%>','qtyFlg<%=i %>');"> Change in Quantity&nbsp;
											<input type="checkbox"  <%=selTax %> value="DIFF-TAX" disabled="disabled" id="taxChk<%=i%>"  name="taxChk<%=i%>" onclick="setCriteria(this,'<%=NEW_INV_SEQ_NO.size()%>','<%=i%>','taxFlg<%=i %>');"> Difference in Tax (Split Qty.)&nbsp;
											<br>
											<input type="checkbox" <%=selMmbtu %> value="DIFF-INRMMBTU" disabled="disabled" id="mmbtuChk<%=i%>"  name="mmbtuChk<%=i%>" onclick="setCriteria(this,'<%=NEW_INV_SEQ_NO.size()%>','<%=i%>','mmbtuFlg<%=i %>');"> Change in INR/MMBTU&nbsp;
											<input type="checkbox" <%= sellmsm %>value="DIFF-LUMPSUM" disabled="disabled" id="lmsmChk<%=i%>"  name="lmsmChk<%=i%>" onclick="setCriteria(this,'<%=NEW_INV_SEQ_NO.size()%>','<%=i%>','lmsmFlg<%=i %>');"> Change in INR/LUMPSUM&nbsp;											
											<br>
											<input type="checkbox" <%= selKm %>value="DIFF-INRKM" disabled="disabled" id="kmChk<%=i%>"  name="kmChk<%=i%>" onclick="setCriteria(this,'<%=NEW_INV_SEQ_NO.size()%>','<%=i%>','kmFlg<%=i %>');"> Change in INR/KM&nbsp;
											<input type="checkbox" <%= selKmQty %>value="DIFF-KM" disabled="disabled" id="kmQtyChk<%=i%>"  name="kmQtyChk<%=i%>" onclick="setCriteria(this,'<%=NEW_INV_SEQ_NO.size()%>','<%=i%>','kmQtyFlg<%=i %>');"> Change in KM&nbsp;
											<br>
											<input type="checkbox" <%=selOth %> value="REV_INV" disabled="disabled" id="othChk<%=i%>"  name="othChk<%=i%>" onclick="setCriteria(this,'<%=NEW_INV_SEQ_NO.size()%>','<%=i%>','othFlg<%=i %>');"> Others
										
											<input type="hidden" name="priceFlg<%=i %>" id="priceFlg<%=i %>" value="N">
											<input type="hidden" name="othFlg<%=i %>" id="othFlg<%=i %>" value="N">
											<input type="hidden" name="exgFlg<%=i %>" id="exgFlg<%=i %>" value="N">
											<input type="hidden" name="qtyFlg<%=i %>" id="qtyFlg<%=i %>" value="N">
											<input type="hidden" name="taxFlg<%=i %>" id="taxFlg<%=i %>" value="N">
											<input type="hidden" name="mmbtuFlg<%=i %>" id="mmbtuFlg<%=i %>" value="N">
											<input type="hidden" name="kmFlg<%=i %>" id="kmFlg<%=i %>" value="N">
											<input type="hidden" name="lmsmFlg<%=i %>" id="lmsmFlg<%=i %>" value="N">
											<input type="hidden" name="kmQtyFlg<%=i %>" id="kmQtyFlg<%=i %>" value="N">
										  
										    <input type="hidden" name="criteria" value="" id="criteria<%=i%>">
										
<%-- 											<input type="text" size="45" name="reason" disabled="disabled" id="reason<%=i%>" onblur="reason111('<%=i%>');" value="<%=DR_CR_DTL_REM.elementAt(i) %>" > --%>
											<input type="hidden" name="reason_frm" value="">
											<input type="hidden" name="inv_no" value="<%=INVOICE_NO.elementAt(i)%>"  >
											<input type="hidden" name="new_inv_no" value="<%=NEW_INV_SEQ_NO.elementAt(i)%>"  >
								    		<input type="hidden" name="inv_dt" value="<%=INVOICE_DT.elementAt(i)%>"  >
								    		<input type="hidden" name="start_dt" value="<%=PERIOD_START_DT.elementAt(i)%>"  >
								    		<input type="hidden" name="end_dt" value="<%=PERIOD_END_DT.elementAt(i)%>"  >
								    		<input type="hidden" name="qty" value="<%=TOTAL_QTY.elementAt(i)%>"  >
								    		<input type="hidden" name="sale_rate" value="<%=SALE_PRICE.elementAt(i)%>" >
								    		<input type="hidden" name="gross_amt" value="<%=GROSS_AMT_INR.elementAt(i)%>"  >
								    		<input type="hidden" name="net_amt" value="<%=NET_AMT_INR.elementAt(i)%>" >
								    		
								    		<input type="hidden" name="mapping_id" value="<%=DR_CR_MAPPING_ID.elementAt(i)%>">
							    		    <input type="hidden" name="plant_seq_no" value="<%=PLANT_SEQ_NO.elementAt(i)%>">
							    			<input type="hidden" name="hlpl_seq_no" value="<%=HLPL_INV_SEQ_NO.elementAt(i)%>">
							    			<input type="hidden" name="con_type" id="con_type<%=i %>" value="<%=CONTRACT_TYPE.elementAt(i)%>">
							    			<input type="hidden" name="fin_yr" value="<%=FINANCIAL_YEAR.elementAt(i)%>">	    			
							    			<input type="hidden" name="due_dt" value="<%=DUE_DT.elementAt(i)%>">
							    			<input type="hidden" name="exg_rt" value="<%=EXCHG_RATE_VALUE.elementAt(i)%>">
							    			<input type="hidden" name="sn_no" value="<%=SN_NO.elementAt(i)%>">
							    			<input type="hidden" name="fgsa_no" value="<%=FGSA_NO.elementAt(i)%>">
							    			<input type="hidden" name="sn_rev_no" value="<%=SN_REV_NO.elementAt(i)%>">
							    			<input type="hidden" name="fgsa_rev_no" value="<%=FGSA_REV_NO.elementAt(i)%>">
							    			<input type="hidden" name="gross_amt_usd" value="<%=GROSS_AMT_USD.elementAt(i)%>">
							    			<input type="hidden" name="tax_struc_cd" value="<%=TAX_STRUCT_CD.elementAt(i)%>">
<%-- 							    			<input type="hidden" name="dr_cr_fin_year" value="<%//=DR_CR_FIN_YEAR.elementAt(i)%>"> --%>
<%-- 							    			<input type="hidden" name="dr_cr_no" value="<%//=DR_CR_NO.elementAt(i)%>">  --%>
							    			<input type="hidden" name="tax_amt_inr" value="<%=TAX_AMT_INR.elementAt(i)%>">
							    			<input type="hidden" name="dr_cr_cnt" value="<%=Vdr_cr_cnt.elementAt(i)%>">
								    	</td> 
								    	</tr>
								    	<%for(int j = 0 ; j < Integer.parseInt(Vdr_cr_cnt.elementAt(i)+""); j++) {%>
								    		
								    	<%String drcr_selPrice = "",drcr_selOth="",drcr_selExg="",drcr_selQTY="",drcr_selTax="",drcr_selMmbtu = "",drcr_selKm = "", drcr_sellmsm = "",drcr_selKmQty = "";
									
											if(DR_CR_DTL_REM.elementAt(i).toString().contains("--")){
												
												String[] temp_res = DR_CR_CRITERIA.elementAt(k).toString().split("--");
												
												for(int p = 0 ; p < temp_res.length; p ++){
		// 							 				System.out.println("temp_res[i]***"+temp_res[j]);
													
													if(temp_res[p].equals("DIFF-PRICE")){
														drcr_selPrice="checked='checked'";
													}
													if(temp_res[p].equals("DIFF-QTY")){
														drcr_selQTY="checked='checked'";
													}
													if(temp_res[p].equals("DIFF-EXG")){
														drcr_selExg="checked='checked'";
													}
													if(temp_res[p].equals("REV_INV")){
														drcr_selOth="checked='checked'";
													}
													if(temp_res[p].equals("DIFF-TAX")){
														drcr_selTax="checked='checked'";
													}
													if(temp_res[p].equals("DIFF-INRMMBTU")){
														drcr_selMmbtu="checked='checked'";
													}
													if(temp_res[p].equals("DIFF-INRKM")){
														drcr_selKm="checked='checked'";
													}
													if(temp_res[p].equals("DIFF-LUMPSUM")){
														drcr_sellmsm="checked='checked'";
													}
													if(temp_res[p].equals("DIFF-KM")){
														drcr_selKmQty="checked='checked'";
													}
												}
											}%>
								    		<tr style="text-align:center; font-size: 13px;background-color: #fdf9e5; " >
								    			<td colspan="1" align="center"> <%=i+1 %>.<%=j+1 %></td>
								    			<td>
								    				<input type="checkbox" id="drcr_chk<%=k %>" name="drcr_chk"  onclick="drcr_enable_check(this,'<%=Vdr_cr_cnt.elementAt(i)%>','<%=k%>','<%=DR_CR_CRITERIA.elementAt(k)%>');">
								    			</td>
								    			<td colspan="1"><%=DR_CR_DOC_NO.elementAt(k) %></td>
								    			<td> <%=DR_CR_DT.elementAt(k) %></td>
								    			<td align="right"> <%=DR_CR_QTY.elementAt(k) %></td>
								    			<td align="right"> <%=DR_CR_SALE_RATE.elementAt(k) %></td>
								    			<td align="right"> <%=DR_CR_GROSS_AMT_INR.elementAt(k) %></td>
								    			<td align="right"> <%=DR_CR_NET_AMT_INR.elementAt(k) %></td>
								    			<td class="text-left">
													<input type="checkbox" <%//=drcr_selExg %> value="DIFF-EXG" disabled="disabled" id="drcr_exgChk<%=k%>"  name="drcr_exgChk" onclick="drcr_setCriteria(this,'<%=Vdr_cr_cnt.elementAt(i)%>','<%=k%>','drcr_exgFlg<%=k %>');"> Difference in Exchange Rate&nbsp;
													<input type="checkbox" <%//=drcr_selPrice %> value="DIFF-PRICE" disabled="disabled" id="drcr_priceChk<%=k%>"  name="drcr_priceChk" onclick="drcr_setCriteria(this,'<%=Vdr_cr_cnt.elementAt(i)%>','<%=k%>','drcr_priceFlg<%=j %>');"> Change in Price/Tariff&nbsp;
													<br>
													<input type="checkbox"  <%//=drcr_selQTY %> value="DIFF-QTY" disabled="disabled" id="drcr_qtyChk<%=k%>"  name="drcr_qtyChk" onclick="drcr_setCriteria(this,'<%=Vdr_cr_cnt.elementAt(i)%>','<%=k%>','drcr_qtyFlg<%=k %>');"> Change in Quantity&nbsp;
													<input type="checkbox"  <%//=drcr_selTax %> value="DIFF-TAX" disabled="disabled" id="drcr_taxChk<%=k%>"  name="drcr_taxChk" onclick="drcr_setCriteria(this,'<%=Vdr_cr_cnt.elementAt(i)%>','<%=k%>','drcr_taxFlg<%=k %>');"> Difference in Tax (Split Qty.)&nbsp;
													<br>
													<input type="checkbox" <%//=drcr_selMmbtu %> value="DIFF-INRMMBTU" disabled="disabled" id="drcr_mmbtuChk<%=k%>"  name="drcr_mmbtuChk" onclick="drcr_setCriteria(this,'<%=Vdr_cr_cnt.elementAt(i)%>','<%=k%>','drcr_mmbtuFlg<%=k %>');"> Change in INR/MMBTU&nbsp;
													<input type="checkbox" <%//= drcr_sellmsm %>value="DIFF-LUMPSUM" disabled="disabled" id="drcr_lmsmChk<%=k%>"  name="drcr_lmsmChk" onclick="drcr_setCriteria(this,'<%=Vdr_cr_cnt.elementAt(i)%>','<%=k%>','drcr_lmsmFlg<%=k %>');"> Change in INR/LUMPSUM&nbsp;											
													<br>
													<input type="checkbox" <%//= drcr_selKm %>value="DIFF-INRKM" disabled="disabled" id="drcr_kmChk<%=k%>"  name="drcr_kmChk" onclick="drcr_setCriteria(this,'<%=Vdr_cr_cnt.elementAt(i)%>','<%=k%>','drcr_kmFlg<%=k %>');"> Change in INR/KM&nbsp;
													<input type="checkbox" <%//= drcr_selKmQty %>value="DIFF-KM" disabled="disabled" id="drcr_kmQtyChk<%=k%>"  name="drcr_kmQtyChk" onclick="drcr_setCriteria(this,'<%=Vdr_cr_cnt.elementAt(i)%>','<%=k%>','drcr_kmQtyFlg<%=k %>');"> Change in KM&nbsp;
													<br>
													<input type="checkbox" <%//=drcr_selOth %> value="REV_INV" disabled="disabled" id="drcr_othChk<%=k%>"  name="drcr_othChk" onclick="drcr_setCriteria(this,'<%=Vdr_cr_cnt.elementAt(i)%>','<%=k%>','drcr_othFlg<%=k%>');"> Others
								    			
								    				<input type="hidden" name="drcr_priceFlg<%=k%>" id="drcr_priceFlg<%=k%>" value="N">
													<input type="hidden" name="drcr_othFlg<%=k%>" id="drcr_othFlg<%=k%>" value="N">
													<input type="hidden" name="drcr_exgFlg<%=k%>" id="drcr_exgFlg<%=k%>" value="N">
													<input type="hidden" name="drcr_qtyFlg<%=k%>" id="drcr_qtyFlg<%=k%>" value="N">
													<input type="hidden" name="drcr_taxFlg<%=k%>" id="drcr_taxFlg<%=k%>" value="N">
													<input type="hidden" name="drcr_mmbtuFlg<%=k%>" id="drcr_mmbtuFlg<%=k%>" value="N">
													<input type="hidden" name="drcr_kmFlg<%=k%>" id="drcr_kmFlg<%=k%>" value="N">
													<input type="hidden" name="drcr_lmsmFlg<%=k%>" id="drcr_lmsmFlg<%=k%>" value="N">
													<input type="hidden" name="drcr_kmQtyFlg<%=k%>" id="drcr_kmQtyFlg<%=k%>" value="N">
												  
												    <input type="hidden" name="drcr_criteria<%=k %>" value="<%=DR_CR_CRITERIA.elementAt(k) %>" id="drcr_criteria<%=k%>">
												    <input type="hidden" name="drcr_chkval<%=k%>" id = "drcr_chkval<%=k %>" value="N">
												    <input type="hidden" name="dr_cr_doc_no<%=k %>" id = "dr_cr_doc_no<%=k %>" value="<%=DR_CR_DOC_NO.elementAt(k)%>">
												    <input type="hidden" name="dr_cr_no<%=k %>" id = "dr_cr_no<%=k %>" value="<%=DR_CR_NO.elementAt(k)%>">
												    <input type="hidden" name="dr_cr_fin_year<%=k %>" id = "dr_cr_fin_year<%=k %>" value="<%=DR_CR_FIN_YEAR.elementAt(k)%>">
												    <input type="hidden" name="dr_cr_dt<%=k %>" id = "dr_cr_dt<%=k %>" value="<%=DR_CR_DT.elementAt(k)%>">
												    
								    			</td>
								    		</tr>
								    	<%k++;} %>
								<%} %>
							<%}else{%>
								 <tr class="info" ><td colspan="10" align="center"><FONT SIZE=3 COLOR=RED><b> No Invoice Found </b></FONT>&nbsp;</td></tr>
							 <%}%>									
							</tbody>
					</table>
					</div>
					<div class="box-footer">
					<div class="row">
						<div class="col-sm-12 text-right">
<!-- 							<button type="button" class="btn btn-warning" name="reSetPage" value="Reset" onClick="refreshPage();">Reset</button> -->
							<button type="button" class="btn btn-success" name="sub" value="Submit" onclick="doSubmit('<%=PERIOD_START_DT.size()%>');">Submit</button>
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

</script>
</html>
								
