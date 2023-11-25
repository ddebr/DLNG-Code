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

<title>DLNG Gen Debit/Credit Note</title>
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
<script type="text/javascript">
function doSubmit(sz)
{
		var size= parseInt(sz);
	 if(size>0)
	 {
		 var from_dt = document.forms[0].from_dt.value;
	     var to_dt = document.forms[0].to_dt.value;
		 var cr_dr = document.forms[0].cr_dr.value;
// 		alert("--dr----->"+cr_dr);
	   	 var bscode = document.forms[0].cust_nm.value;
	   	 var criteria = "";
	   	 var pan_no1=document.forms[0].pan_no.value;
	   	 var gst_tin_no1=document.forms[0].gst_tin_no.value;
	   	 var cst_tin_no1=document.forms[0].cst_tin_no1.value;
	   	 var cust_name=document.forms[0].customer_name.value;
	     var bill_dt = document.forms[0].bill_dt.value; 
	   	
	   	 var st_dt     ="";
	   	 var end_dt    ="";
	   	 var qty       ="";
	   	 var net_amt   ="";
	   	 var gross_amt ="";
	   	 var sale_rate ="";
	   	 var inv_dt    =""; 
	   	 var inv_no    ="";
	   	 var hlpl_no   ="";
	   	 var plant_no  ="";
	   	 var con_type  ="";
	   	 var fin_yr    ="";
	   	 var due_dt    ="";
	   	 var exg_rt    ="";
	   	 var sn_no     ="";
	   	 var sn_rev_no="";
	   	 var fgsa_no   ="";
	   	 var fgsa_rev_no="";
	   	 var gross_amt_usd="";
		 var tax_struc_cd="";
		 var mapping_id="";
	   	 var cnt=parseInt("0");
	   	 var cnt1=parseInt("0");
	   	 var tax_amt_inr =""; //SB20160401\
	   	 var rate_service = "";
	   	 var calc_base = "";
	   	 var dr_cr_seq_no = "";
	   	 var dr_cr_ref_inv_no = "";
	   	 //alert("bscode = "+bscode+", cr_dr= "+cr_dr+", criteria = "+criteria+", from_dt = "+from_dt+", to_dt = "+to_dt);
	   	if(from_dt!=null && to_dt!=null  && bscode!=null && cr_dr!=0 && trim(bscode)!="0" && trim(cr_dr)!="0")
	   	{//alert(size);
	   	
			 if(size>1)
			 {	 	
		  		//if(criteria=='1' || criteria=='3' )
		  		//{
			  		for(i=0;i<size;i++)
			  		{ 
			  			if(document.forms[0].chk[i].checked)
			  			{
			  				//alert("document.forms[0].chk["+i+"].checked = "+document.forms[0].chk[i].checked);
			  				st_dt     =document.forms[0].start_dt[i].value;
					   	    end_dt    =document.forms[0].end_dt[i].value;
					   	    qty       =document.forms[0].qty[i].value;
					   	    net_amt   =document.forms[0].net_amt[i].value;
					   	    gross_amt =document.forms[0].gross_amt[i].value;
					   	    sale_rate =document.forms[0].sale_rate[i].value;
					   	    inv_dt    =document.forms[0].inv_dt[i].value;
					   	    inv_no    =document.forms[0].inv_no[i].value;
	   	                    hlpl_no   =document.forms[0].hlpl_seq_no[i].value;
	   	                    plant_no  =document.forms[0].plant_seq_no[i].value;
	   	                    con_type  =document.forms[0].con_type[i].value;
	   	                    fin_yr	  =document.forms[0].fin_yr[i].value;
	   	                    due_dt    =document.forms[0].due_dt[i].value;
						   	exg_rt    =document.forms[0].exg_rt[i].value;
						   	sn_no     =document.forms[0].sn_no[i].value;
						   	sn_rev_no =document.forms[0].sn_rev_no[i].value;
						   	fgsa_no   =document.forms[0].fgsa_no[i].value;
						   	fgsa_rev_no	=document.forms[0].fgsa_rev_no[i].value;
						   	tax_struc_cd=document.forms[0].tax_struc_cd[i].value;
						   	remark_1=document.forms[0].remark_1[i].value;
						   	remark_2=document.forms[0].remark_2[i].value;
						   	remark_3=document.forms[0].remark_3[i].value;
						   	gross_amt_usd=document.forms[0].gross_amt_usd[i].value;
						   	tax_amt_inr =document.forms[0].tax_amt_inr[i].value;
						   	address=document.forms[0].address[i].value;
						   	city=document.forms[0].city[i].value;
						   	pin=document.forms[0].pin[i].value;
						    criteria = document.forms[0].criteria[i].value;
						    rate_service = document.forms[0].rate_service[i].value;
						    calc_base = document.forms[0].calc_base[i].value;
						    dr_cr_seq_no=document.forms[0].dr_cr_seq_no[i].value;
						    dr_cr_ref_inv_no = document.forms[0].dr_cr_ref_inv_no[i].value;
					   	    cnt++;
			  			}		  				  			
			  		}
			  //	}
			 }
			 else
			 {//alert("2: "+size);
			 	//if(criteria=='1' || criteria=='3' )
		  		//{
		  		 //alert("in else");
				    if(document.forms[0].chk.checked)
				  	{
				  	 //alert("in else if");
					 	 st_dt     =document.forms[0].start_dt.value;
				   	     end_dt    =document.forms[0].end_dt.value;
				   	     qty       =document.forms[0].qty.value;
				   	     net_amt   =document.forms[0].net_amt.value;
				   	     gross_amt =document.forms[0].gross_amt.value;
				   	     sale_rate =document.forms[0].sale_rate.value;
				   	     inv_dt    =document.forms[0].inv_dt.value;
				   	     inv_no    =document.forms[0].inv_no.value;
	                     hlpl_no   =document.forms[0].hlpl_seq_no.value;
		                 plant_no  =document.forms[0].plant_seq_no.value;
		                 con_type  =document.forms[0].con_type.value;
		                 fin_yr	   =document.forms[0].fin_yr.value;
		             //    alert(fin_yr);
		                 due_dt    =document.forms[0].due_dt.value;
					   	 exg_rt    =document.forms[0].exg_rt.value;
					   	 sn_no     =document.forms[0].sn_no.value;
					   	 sn_rev_no =document.forms[0].sn_rev_no.value;
					   	 fgsa_no   =document.forms[0].fgsa_no.value;
					   	 fgsa_rev_no=document.forms[0].fgsa_rev_no.value;
					   	 tax_struc_cd=document.forms[0].tax_struc_cd.value;//alert(tax_struc_cd);
				   	  	remark_1=document.forms[0].remark_1.value;
					   	remark_2=document.forms[0].remark_2.value;
					   	remark_3=document.forms[0].remark_3.value;
					   	 gross_amt_usd=document.forms[0].gross_amt_usd.value;
					   	 tax_amt_inr =document.forms[0].tax_amt_inr.value;
					   	 address=document.forms[0].address.value;
						 city=document.forms[0].city.value;
						 pin=document.forms[0].pin.value;
						 criteria = document.forms[0].criteria.value;
						 rate_service = document.forms[0].rate_service.value;
						 calc_base = document.forms[0].calc_base.value;
						 dr_cr_seq_no=document.forms[0].dr_cr_seq_no.value;	
						 dr_cr_ref_inv_no = document.forms[0].dr_cr_ref_inv_no.value;
					   	// alert("tax_amt-----"+tax_amt_inr);
						 
						 /*mapping_id=document.forms[0].mapping_id.value;
						 */ //alert(tax_amt_inr);//SB20160401
				   	     cnt++;
			   	    }		   	   
		   	   // }
		   	   
			 }
// 			 alert("MAPP_ID: "+mapping_id);
			if(cnt>0)
			{	
				 	if(!newWindow || newWindow.closed)
					{
				 		var url = "frm_dr_cr_note.jsp?from_dt="+from_dt+"&to_dt="+to_dt+"&mapping_id="+mapping_id+"&bscode="+bscode+
				 				"&criteria="+criteria+"&st_dt="+st_dt+"&end_dt="+end_dt+"&qty="+qty+"&net_amt="+net_amt+
				 				"&gross_amt="+gross_amt+"&sale_rate="+sale_rate+"&inv_dt="+inv_dt+"&inv_no="+inv_no+
				 				"&hlpl_no="+hlpl_no+"&plant_no="+plant_no+"&con_type="+con_type+"&fin_yr="+fin_yr+
				 				"&due_dt="+due_dt+"&exg_rt="+exg_rt+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+
				 				"&fgsa_rev_no="+fgsa_rev_no+"&fgsa_no="+fgsa_no+"&cnt1="+cnt1+"&cr_dr="+cr_dr+
				 				"&pan_no="+pan_no1+"&gst_tin_no="+gst_tin_no1+"&cst_tin_no="+cst_tin_no1+
				 				"&tax_struc_cd="+tax_struc_cd+
// 				 				"&remark_1="+remark_1+"&remark_2="+remark_2+"&remark_3="+remark_3+ //Commented by Hiren_20210122 due to special character found in URL which is not supported by IE11
				 				"&remark_2="+remark_2+"&remark_3="+remark_3+ //Commented by Hiren_20210122 due to special character found in URL which is not supported by IE11
				 				"&gross_amt_usd="+gross_amt_usd+"&tax_amt_inr="+tax_amt_inr+"&address1="+address+
				 				"&city1="+city+"&pin1="+pin+"&cust_name="+cust_name+"&bill_dt="+bill_dt+
				 				"&rate_service="+rate_service+"&calc_base="+calc_base+
				 				"&dr_cr_seq_no="+dr_cr_seq_no+"&dr_cr_ref_inv_no="+dr_cr_ref_inv_no;
				 		
// 						alert(url)
				 		newWindow= window.open(url,"Custom_Duty1","top=10,left=70,width=900,height=350,scrollbars=1,status=1,maximize=yes,resizable=1");
					}
					else
					{
						var url = "frm_dr_cr_note.jsp?from_dt="+from_dt+"&to_dt="+to_dt+"&mapping_id="+mapping_id+"&bscode="+bscode+
		 				"&criteria="+criteria+"&st_dt="+st_dt+"&end_dt="+end_dt+"&qty="+qty+"&net_amt="+net_amt+
		 				"&gross_amt="+gross_amt+"&sale_rate="+sale_rate+"&inv_dt="+inv_dt+"&inv_no="+inv_no+
		 				"&hlpl_no="+hlpl_no+"&plant_no="+plant_no+"&con_type="+con_type+"&fin_yr="+fin_yr+
		 				"&due_dt="+due_dt+"&exg_rt="+exg_rt+"&sn_no="+sn_no+"&sn_rev_no="+sn_rev_no+
		 				"&fgsa_rev_no="+fgsa_rev_no+"&fgsa_no="+fgsa_no+"&cnt1="+cnt1+"&cr_dr="+cr_dr+
		 				"&pan_no="+pan_no1+"&gst_tin_no="+gst_tin_no1+"&cst_tin_no="+cst_tin_no1+
		 				"&tax_struc_cd="+tax_struc_cd+
//			 				"&remark_1="+remark_1+"&remark_2="+remark_2+"&remark_3="+remark_3+ //Commented by Hiren_20210122 due to special character found in URL which is not supported by IE11
		 				"&remark_2="+remark_2+"&remark_3="+remark_3+ //Commented by Hiren_20210122 due to special character found in URL which is not supported by IE11
		 				"&gross_amt_usd="+gross_amt_usd+"&tax_amt_inr="+tax_amt_inr+"&address1="+address+
		 				"&city1="+city+"&pin1="+pin+"&cust_name="+cust_name+"&bill_dt="+bill_dt+
		 				"&rate_service="+rate_service+"&calc_base="+calc_base+
		 				"&dr_cr_seq_no="+dr_cr_seq_no+"&dr_cr_ref_inv_no="+dr_cr_ref_inv_no;
// 						alert(url)
						newWindow= window.open(url,"Custom_Duty1","top=10,left=70,width=900,height=350,scrollbars=1,status=1,maximize=yes,resizable=1");
					}
			}
			else
			{
				alert("Please Select any one record before Generate Debit/Credit Note ...");
			}
		}
		else
		{
			alert("Please Select All the Options to Generate Debit/Credit Note ...");
		}
	} 
}

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
	
   	if(from_dt!=null && to_dt!=null && bscode!=null && trim(bscode)!="0" )
   	{
	   	if(trim(from_dt)!="" && trim(to_dt)!="")
	   	{
			var value = compareDate(from_dt,to_dt);			
			if(value!=1)
			{
				var url ="../sales_invoice/frm_mst_prepareInvoice.jsp?fy="+fy+"&bill_dt="+bill_dt+"&from_dt="+from_dt+"&to_dt="+to_dt+"&bscode="+bscode+"&cust_name="+cust_name+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
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
	
   	if(fy!="" )
   	{ 
			var url ="../sales_invoice/frm_mst_prepareInvoice.jsp?fy="+fy+"&bill_dt="+bill_dt+"&from_dt="+from_dt+"&to_dt="+to_dt+"&bscode="+bscode+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
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
	
   	if(trim(from_dt)!="" && trim(to_dt)!="")
   	{
		var value = compareDate(from_dt,to_dt);
		if(value!=1)
		{
			var url ="../sales_invoice/frm_mst_prepareInvoice.jsp?fy="+fy+"&bill_dt="+bill_dt+"&from_dt="+from_dt+"&to_dt="+to_dt+"&bscode="+bscode+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
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

function apply(obj,sz,ind){
	
  var size= parseInt(sz);
  var index= parseInt(ind);
  
  if(size>1){
  	
	  if(obj.checked){
		for(i=0;i<size;i++)
		{
			document.forms[0].chk[i].checked = false; 
		}
		obj.checked=true;
  	}else{
	  		obj.checked=false;
  		} 
  	}
//   alert(document.forms[0].temp_criteria[ind].value)
  if(document.getElementById('temp_criteria'+ind).value == 'Others'){
	  
	  var select = document.getElementById("cr_dr");
	  var length = select.options.length;
	  for (i = length-1; i >= 0; i--) {
	    select.options[i] = null;
	  }

	  var option = document.createElement("option");
	  option.text = "Credit";
	  option.value = "cr";
	  select.add(option);
	  
  }else{
	  if(document.getElementById("cr_dr").value == '' || document.getElementById("cr_dr").value == 'cr'){
		  var select = document.getElementById("cr_dr");
		  var length = select.options.length;
		 
		  for (i = length-1; i >= 0; i--) {
		    select.options[i] = null;
		  }
		  
		  var options = ["--Select--","Debit", "Credit"];
		  var val = ["","dr", "cr"];

		  for(var i = 0; i < options.length; i++) {
		      var opt = options[i];
		      var el = document.createElement("option");
		      el.textContent = opt;
		      el.value = val[i];
		      select.appendChild(el);
		  }
	  }
  }
} 
  
function Change_dr_cr(sz)
{
	var cr_dr=document.forms[0].cr_dr.value;
	if(cr_dr=='cr'){
		if(sz>1){
		for(var i=0;i<sz;i++){
			var cflg=document.forms[0].cr_dr_flag[i].value;
			if(cflg=='dr')
			{
				document.forms[0].chk[i].disabled=true;
				document.forms[0].chk[i].checked=false;
			}else{
				document.forms[0].chk[i].disabled=false;
// 				document.forms[0].chk[i].checked=true;
			}
		}
		}else{
			var cflg=document.forms[0].cr_dr_flag.value;
			if(cflg=='dr')
			{
				document.forms[0].chk.disabled=true;
				document.forms[0].chk.checked=false;
			}else{
				document.forms[0].chk.disabled=false;
			}
		}
		
	}else if(cr_dr=='dr'){
		if(sz>1){
			for(var i=0;i<sz;i++){
				var cflg=document.forms[0].cr_dr_flag[i].value;
				if(cflg=='cr')
				{
					document.forms[0].chk[i].disabled=true;
					document.forms[0].chk[i].checked=false;
				}
				else{
					document.forms[0].chk[i].disabled=false;
				}
			}
			}else{
				var cflg=document.forms[0].cr_dr_flag.value;
				if(cflg=='cr')
				{
					document.forms[0].chk.disabled=true;
					document.forms[0].chk.checked=false;
				}else{
					document.forms[0].chk.disabled=false;
				}
			}
	}
}

function callFromChild(modCd,formId,subModUrl,fy_yr,bill_dt,msg,from_dt,to_dt,bscode,error_msg){
	
	var url ="../sales_invoice/frm_mst_prepareInvoice.jsp?fy="+fy_yr+"&bill_dt="+bill_dt+"&from_dt="+from_dt+"&to_dt="+to_dt+"&bscode="+bscode+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&msg="+msg+"&error_msg="+error_msg;
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

String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
String cust_name = request.getParameter("cust_name")==null?"":request.getParameter("cust_name");		
String bscode = request.getParameter("bscode")==null?"0":request.getParameter("bscode");		
String cr_dr = request.getParameter("cr_dr")==null?"":request.getParameter("cr_dr");
String criteria = request.getParameter("criteria")==null?"":request.getParameter("criteria");		
String from_dt = request.getParameter("from_dt")==null?sysdate:request.getParameter("from_dt");
String to_dt = request.getParameter("to_dt")==null?sysdate:request.getParameter("to_dt");
String bill_dt = request.getParameter("bill_dt")==null?"0":request.getParameter("bill_dt");//SB20160527 
String fy = request.getParameter("fy")==null?"0":request.getParameter("fy");//SB20160527
String reason = request.getParameter("reasonfrm")==null?"":request.getParameter("reasonfrm");
String chkval = request.getParameter("chkval")==null?"":request.getParameter("chkval");
//String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
//System.out.println("----fy---"+fy);

drcr.setCallFlag("InvAvailableFY");
drcr.setFY(fy);
drcr.init();
Vector VInv_FY = drcr.getVInv_FY();	
Vector VInv_Dt_FY = drcr.getVInv_Dt_FY();
if(bill_dt.equals("0"))
{
	from_dt=sysdate;
}

if(!bill_dt.equals("0"))
{
	drcr.setCallFlag("fetchINV_Details");
	drcr.setFrom_dt(from_dt);
	drcr.setTo_dt(to_dt);
	drcr.setDr_cr_year(fy);
	//drcr.setCriteria(criteria);
	//drcr.setCr_dr(cr_dr);
	drcr.setCustomer_cd(bscode);
	//drcr.setActivity(activity);
	drcr.init();
}
Vector CUST_CD = drcr.getCUST_CD();
Vector CUST_NM = drcr.getCUST_NM();
 
Vector SALE_PRICE_DR_CR = drcr.getSALE_PRICE_DR_CR();	
Vector GROSS_AMT_INR_DR_CR = drcr.getGROSS_AMT_INR_DR_CR();
Vector NET_AMT_INR_DR_CR = drcr.getNET_AMT_INR_DR_CR();
Vector TOTAL_QTY_DR_CR = drcr.getTOTAL_QTY_DR_CR();
Vector INVOICE_DT_DR_CR = drcr.getINVOICE_DT_DR_CR();
Vector PERIOD_START_DT_DR_CR = drcr.getPERIOD_START_DT_DR_CR();
Vector PERIOD_END_DT_DR_CR = drcr.getPERIOD_END_DT_DR_CR();

Vector PLANT_SEQ_NO_DR_CR = drcr.getPLANT_SEQ_NO_DR_CR();
Vector HLPL_INV_SEQ_NO_DR_CR = drcr.getHLPL_INV_SEQ_NO_DR_CR();
Vector CONTRACT_TYPE_DR_CR = drcr.getCONTRACT_TYPE_DR_CR();
Vector FINANCIAL_YEAR_DR_CR = drcr.getFINANCIAL_YEAR_DR_CR();
Vector INVOICE_NO_DR_CR = drcr.getINVOICE_NO_DR_CR();
Vector DUE_DT_DR_CR = drcr.getDUE_DT_DR_CR();
Vector EXCHG_RATE_VALUE_DR_CR = drcr.getEXCHG_RATE_VALUE_DR_CR();
Vector SN_NO_DR_CR = drcr.getSN_NO_DR_CR();
Vector SN_REV_NO_DR_CR = drcr.getSN_REV_NO_DR_CR();
Vector FGSA_NO_DR_CR = drcr.getFGSA_NO_DR_CR();
Vector FGSA_REV_NO_DR_CR = drcr.getFGSA_REV_NO_DR_CR();
Vector REMARK_DR_CR=drcr.getREMARK_DR_CR();
Vector GROSS_AMT_USD_DR_CR = drcr.getGROSS_AMT_USD_DR_CR();
Vector TAX_STRUCT_CD_DR_CR = drcr.getTAX_STRUCT_CD_DR_CR();
String pan_no=drcr.getPan_no();
String gst_tin_no=drcr.getGst_tin_no();
String cst_tin_no=drcr.getCst_tin_no();
Vector remark_1= drcr.getREMARK_1_DR_CR();
Vector remark_2= drcr.getREMARK_2_DR_CR();
Vector remark_3= drcr.getREMARK_3_DR_CR();
//Vector invoice_period_date=drcr.getInvoice_period_date();

Vector DR_CR_MAPPING_ID=drcr.getDR_CR_MAPPING_ID_DR_CR();
Vector TAX_AMT_INR_DR_CR=drcr.getTAX_AMT_INR_DR_CR(); //SB20160401
Vector dr_cr_flag=drcr.getFlag_dr_cr();
Vector address=drcr.getContact_Customer_Person_Address1();
Vector city=drcr.getContact_Customer_Person_City1();
Vector pin=drcr.getContact_Customer_Person_Pin1();
String customer_nm=drcr.getCust_nm();
Vector flag_dr_cr= drcr.getFLAG_DR_CR();
Vector dr_cr_no= drcr.getDr_cr_no2();
Vector DR_CR_NEW_INV_SEQ_NO = drcr.getDR_CR_NEW_INV_SEQ_NO(); 
Vector Vdr_cr_flag=drcr.getFlag_dr_cr();
Vector RATE_SERVICE = drcr.getRATE_SERVICE();
Vector CALC_BASE_SERVICE = drcr.getCALC_BASE_SERVICE();
Vector DR_CR_SEQ_NO = drcr.getDR_CR_SEQ_NO();
Vector DR_CR_REF_INV_NO = drcr.getDR_CR_REF_INV_NO();

// System.out.println("----RATE_SERVICE---"+RATE_SERVICE);
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
					<input type="hidden" name="user_cd" value="<%=user_cd%>">
					<input type="hidden" name="sysdate" value="<%=sysdate %>" >
					<input type="hidden" name="option" value="GenDrCrNote">
					<input type="hidden" name="bill_dt1" value="<%=bill_dt %>">
			    	<input type="hidden" name="from_dt1" value="<%=from_dt %>">
			    	<input type="hidden" name="to_dt1" value="<%=to_dt %>">
			    	<input type="hidden" name="fy1" value="<%=fy %>">
			    	<input type="hidden" name="customer_cd" value="<%=bscode%>">
					<input type="hidden" name="fy1" value="<%=fy %>">
			    	<input type="hidden" name="pan_no" value="<%=pan_no %>">
			    	<input type="hidden" name="gst_tin_no" value="<%=gst_tin_no %>">
			    	<input type="hidden" name="cst_tin_no1" value="<%=cst_tin_no %>">
			    	<input type="hidden" name="customer_name" value="<%=customer_nm %>">
			    	
			    	
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
										<label>Credit/Debit</label>
										<div class="form-group" >
											<select name="cr_dr" id="cr_dr" class="form-control" required="required" onchange="Change_dr_cr('<%=DR_CR_NEW_INV_SEQ_NO.size()%>');">
													  <option value="">--Select--</option>
													  <option value="dr">Debit</option>
													  <option value="cr">Credit</option>
											</select>
											<script type="text/javascript">
												document.forms[0].cr_dr.value="<%=cr_dr%>";
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
					<div class="box-body table-responsive no-padding" style="height: 300px;overflow: scroll;">
						<table id="example" class="table table-bordered">
							<thead>
								<tr><td colspan="12" align="center"> <font color="black"> <b style="font-size: 18px;">
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
									<th class="text-center">Dr/Cr/SI No.</th>
									<th class="text-center" colspan="1" >Criteria</th>
								</tr>
							</thead>
							<tbody>
								<% if(PERIOD_START_DT_DR_CR.size()!=0) { 
									for(int i=0;i<PERIOD_START_DT_DR_CR.size();i++){
										if(i%2==0){%>
										<tr bgcolor="CFF3D7" style="text-align:center; font-size: 13px; " >
										<%}else{ %>
										<tr bgcolor="#EDEDED" style="text-align:center; font-size: 13px; " >
										<%} %>
										<td><%=i+1 %></td>
										<td>
										<%if(!dr_cr_flag.elementAt(i).toString().equalsIgnoreCase("Y")){ %>
												<input type="checkbox" name="chk" id="check<%=i%>" TITLE="TYPE:PLANT:FGSA:Rev:SN:Rev" onclick="apply(this,'<%=PERIOD_START_DT_DR_CR.size()%>','<%=i%>');" >
												<%}else{ %>
													<FONT SIZE=2 COLOR=RED <%if(flag_dr_cr.elementAt(i).toString().equalsIgnoreCase("Y")) {%>TITLE="Debit Note Generated!!" <%}else{ %>TITLE="Credit Note Generated!!" <%} %>><B><%=flag_dr_cr.elementAt(i) %></B></FONT>
													<input type="checkbox" name="chk" id="check<%=i%>"  TITLE="TYPE:PLANT:FGSA:Rev:SN:Rev" style="background-color: yellow;" onclick="apply(this,'<%=PERIOD_START_DT_DR_CR.size()%>','<%=i%>');" >
													
												<%} %>
											<FONT SIZE=2 style="font-weight: bolder; "><B><%=CONTRACT_TYPE_DR_CR.elementAt(i)%>:<%=PLANT_SEQ_NO_DR_CR.elementAt(i)%>:<%=FGSA_NO_DR_CR.elementAt(i)%>:<%=FGSA_REV_NO_DR_CR.elementAt(i)%>:<%=SN_NO_DR_CR.elementAt(i)%>:<%=SN_REV_NO_DR_CR.elementAt(i)%></B></FONT>
										</td>
							
										<td ><%=DR_CR_NEW_INV_SEQ_NO.elementAt(i)%></td>
										<td ><%=INVOICE_DT_DR_CR.elementAt(i)%></td>
										<td class="text-right" ><%=TOTAL_QTY_DR_CR.elementAt(i)%></td>
										<td class="text-right" ><%=SALE_PRICE_DR_CR.elementAt(i)%></td>
										<td class="text-right" ><%=GROSS_AMT_INR_DR_CR.elementAt(i)%></td>
										<td class="text-right" ><%=NET_AMT_INR_DR_CR.elementAt(i)%></td>
										<td  title="Already Generated!!" >
											<input type="text"  readonly="readonly" class="form-control text-center"" style="background-color: #FFFFE0;" value="<%=dr_cr_no.elementAt(i)%>">
										</td>
										<td  class="text-left">
										<%String temp_criteria = "";
										
										 if(REMARK_DR_CR.elementAt(i).toString().trim().contains("--")) {
											 
											temp_criteria="";
											String criteria_arr []=  REMARK_DR_CR.elementAt(i).toString().split("--");
//								 			System.out.println("temp_criteria.length()--"+criteria_arr.length);
// 											criteria_len.add(criteria_arr.length);
											for(int j = 0 ; j < criteria_arr.length;j++){
												//temp_criteria+=criteria_arr[j]+"<br>";
												if(criteria_arr[j].equals("DIFF-PRICE")){
													temp_criteria+="Change in Rate <br>";
												}else if(criteria_arr[j].equals("DIFF-QTY")){
													temp_criteria+="Change in Quantity <br>";
												}else if(criteria_arr[j].equals("REV_INV")){
													temp_criteria+="Others";
												}else if(criteria_arr[j].equals("DIFF-EXG")){
													temp_criteria+="Difference in Exchange Rate<br>";
												}
												else if(criteria_arr[j].equals("DIFF-TAX")){
													temp_criteria+="Difference in Tax % (Split Qty.)<br>";
												}else if(criteria_arr[j].equals("DIFF-INRMMBTU")){
													temp_criteria+="Change in INR/MMBTU<br>";
												}else if(criteria_arr[j].equals("DIFF-INRKM")){
													temp_criteria+="Change in INR/KM<br>";
												}else if(criteria_arr[j].equals("DIFF-LUMPSUM")){
													temp_criteria+="Change in INR/LUMPSUM <br>";
												}else if(criteria_arr[j].equals("DIFF-KM")){
													temp_criteria+="Change in KM<br>";
												}
											}
										} %>
										<%=temp_criteria%>
										 
						 					<input type="hidden" name="inv_no" value="<%=DR_CR_NEW_INV_SEQ_NO.elementAt(i)%>"  >
								    		<input type="hidden" name="inv_dt" value="<%=INVOICE_DT_DR_CR.elementAt(i)%>"  >
								    		<input type="hidden" name="start_dt" value="<%=PERIOD_START_DT_DR_CR.elementAt(i)%>"  >
								    		<input type="hidden" name="end_dt" value="<%=PERIOD_END_DT_DR_CR.elementAt(i)%>"  >
								    		<input type="hidden" name="qty" value="<%=TOTAL_QTY_DR_CR.elementAt(i)%>"  >
								    		<input type="hidden" name="sale_rate" value="<%=SALE_PRICE_DR_CR.elementAt(i)%>" >
								    		<input type="hidden" name="gross_amt" value="<%=GROSS_AMT_INR_DR_CR.elementAt(i)%>"  >
								    		<input type="hidden" name="net_amt" value="<%=NET_AMT_INR_DR_CR.elementAt(i)%>" >
										
											<input type="hidden" name="mapping_id" value="<%=DR_CR_MAPPING_ID.elementAt(i)%>">
							    		    <input type="hidden" name="plant_seq_no" value="<%=PLANT_SEQ_NO_DR_CR.elementAt(i)%>">
							    			<input type="hidden" name="hlpl_seq_no" value="<%=HLPL_INV_SEQ_NO_DR_CR.elementAt(i)%>">
							    			<input type="hidden" name="con_type" value="<%=CONTRACT_TYPE_DR_CR.elementAt(i)%>">
							    			<input type="hidden" name="fin_yr" value="<%=FINANCIAL_YEAR_DR_CR.elementAt(i)%>">	    			
							    			<input type="hidden" name="due_dt" value="<%=DUE_DT_DR_CR.elementAt(i)%>">
							    			<input type="hidden" name="exg_rt" value="<%=EXCHG_RATE_VALUE_DR_CR.elementAt(i)%>">
							    			<input type="hidden" name="sn_no" value="<%=SN_NO_DR_CR.elementAt(i)%>">
							    			<input type="hidden" name="fgsa_no" value="<%=FGSA_NO_DR_CR.elementAt(i)%>">
							    			<input type="hidden" name="sn_rev_no" value="<%=SN_REV_NO_DR_CR.elementAt(i)%>">
							    			<input type="hidden" name="fgsa_rev_no" value="<%=FGSA_REV_NO_DR_CR.elementAt(i)%>">
							    			<input type="hidden" name="tax_struc_cd" value="<%=TAX_STRUCT_CD_DR_CR.elementAt(i)%>">
							    			<input type="hidden" name="remark_1" value="<%=remark_1.elementAt(i)%>">
							    			<input type="hidden" name="remark_2" value="<%=remark_2.elementAt(i)%>">
							    			<input type="hidden" name="remark_3" value="<%=remark_3.elementAt(i)%>">
							    			<input type="hidden" name="gross_amt_usd" value="<%=GROSS_AMT_USD_DR_CR.elementAt(i)%>">
							    			<input type="hidden" name="tax_amt_inr" value="<%=TAX_AMT_INR_DR_CR.elementAt(i)%>">
							    		   <input type="hidden" name="address" value="<%=address.elementAt(i)%>">
							    		   <input type="hidden" name="city" value="<%=city.elementAt(i)%>">
							    		   <input type="hidden" name="pin" value="<%=pin.elementAt(i)%>">
							    		   <input type="hidden" name="cr_dr_flag" value="<%=Vdr_cr_flag.elementAt(i)%>"  >
							    		   <input type="hidden" name="temp_criteria" id="temp_criteria<%=i %>"  value="<%=temp_criteria%>"  >
							    		   <input type="hidden" name="dr_cr_seq_no" id="dr_cr_seq_no<%=i %>"  value="<%=DR_CR_SEQ_NO.elementAt(i)%>"  >
							    		   
							    		   <input type="hidden" name="rate_service" id= "rate_service<%=i %>" value="<%=RATE_SERVICE.elementAt(i)%>">
							    		   <input type="hidden" name="calc_base" id= "calc_base<%=i %>" value="<%=CALC_BASE_SERVICE.elementAt(i)%>">
							    		   <input type="hidden" name="criteria" id="criteria<%=i %>"  value="<%=REMARK_DR_CR.elementAt(i)%>"  >
							    		   <input type="hidden" name="dr_cr_ref_inv_no" id="dr_cr_ref_inv_no<%=i %>"  value="<%=DR_CR_REF_INV_NO.elementAt(i)%>"  >
									</td>
								</tr>
							 	<%}
								}else{%>  
								   	<tr class="info" ><td colspan="10" align="center"><FONT SIZE=3 COLOR=RED><b> No Invoice Found </b></FONT>&nbsp;</td></tr>
								<%}%>
							</tbody>
						</table>
					</div>
					<div class="box-footer">
					<div class="row">
						<div class="col-sm-12 text-center">
<!-- 							<button type="button" class="btn btn-warning" name="reSetPage" value="Reset" onClick="refreshPage();">Reset</button> -->
							<button type="button" class="btn btn-warning" name="generate" value="Generate Debit/ Credit Note"onclick="doSubmit('<%=PERIOD_START_DT_DR_CR.size()%>');">Generate Debit/ Credit Note</button>
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
					