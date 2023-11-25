<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<%@ page import="java.io.File"%>
<!DOCTYPE html>
<html lang="en">
<head>

<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/tlu.css">

<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
</head>
<script type="text/javascript">
function refreshPage()
{
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	var gas_date = document.forms[0].gas_date.value;
	var to_cntct = "";
	var from_cntct = "";
	
	location.replace("../reports/rpt_master.jsp?gas_date="+gas_date+"&to_cntct="+to_cntct+"&from_cntct="+from_cntct+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl);
}

function checkUncheckAll()
{
	var count = parseInt(document.forms[0].count1.value);
	var chk = document.forms[0].chkUNchk.value;
	var i = 0;
	
	if(count==1)
	{
		if(chk=='Check All')
		{
			document.forms[0].chk_cont.checked = true;
		}
		else if(chk=='Un-Check All')
		{
			document.forms[0].chk_cont.checked = false;
		}
	}
	else if(count>1)
	{
		if(chk=='Check All')
		{
			for(i=0;i<count;i++)
			{
				document.forms[0].chk_cont[i].checked = true;
			}
		}
		else if(chk=='Un-Check All')
		{
			for(i=0;i<count;i++)
			{
				document.forms[0].chk_cont[i].checked = false;
			}
		}	
	}
	
	if(chk=='Check All')
	{
		document.forms[0].chkUNchk.value = 'Un-Check All';
	}
	else if(chk=='Un-Check All')
	{
		document.forms[0].chkUNchk.value = 'Check All';
	}
}

function setPoints(theDoc){
	  
    var tot_cont = document.forms[0].chk_cont;
    var size = parseInt(document.forms[0].count1.value);
    var gas_date = document.forms[0].gas_date.value;
	
	
	var customer_cd1 = "";
	var to_cntct1 = "";
	var from_cntct1 = "";
	var plant_seq_no1 = "";
	var plant_nm1 = "";
	var fgsa_no1 = "";
	var sn_no1 = "";
	var cont_type1 = "";
	var remarks1 ="";		
  	var sn_ref_no1="";
  	var mapping_id1="";
  	
	var	customer_cd = document.forms[0].customer_cd;
	var	to_cntct = document.forms[0].to_contact;
	var	from_cntct = document.forms[0].from_contact;
		
	var	plant_seq_no = document.forms[0].plant_seq_no;
	var	plant_nm = document.forms[0].plant_nm;
		
	var	fgsa_no = document.forms[0].fgsa_no;
	var	sn_no = document.forms[0].sn_no;
	var	cont_type = document.forms[0].cont_type;
	var	remarks =document.forms[0].remarks;
	var sn_ref_no=document.forms[0].sn_ref_no;
	var mapping_id=document.forms[0].mapping_id;


  var type="Print";
var flag=false;
  if(tot_cont!=undefined){
  //alert(tot_cont.length);
	if(tot_cont.length==undefined)
	{
		if(tot_cont.checked){ 
			         if(customer_cd1=="")
			         {
			             customer_cd1 = customer_cd.value;
			             to_cntct1 = to_cntct.value;
			             from_cntct1 = from_cntct.value;
			             plant_seq_no1 = plant_seq_no.value;
			             plant_nm1 = plant_nm.value;
			             fgsa_no1 = fgsa_no.value;
			             sn_no1 = sn_no.value;
			             cont_type1 = cont_type.value;
			             remarks1 = remarks.value;
			             sn_ref_no1= sn_ref_no.value;
			             mapping_id1= mapping_id.value;
			          }
			          else
			          {
			          	 customer_cd1 =customer_cd1+"@"+customer_cd.value;
			             to_cntct1 =to_cntct1+"@"+to_cntct.value;
			             from_cntct1 =from_cntct1+"@"+from_cntct.value;
			             plant_seq_no1 =plant_seq_no1+"@"+plant_seq_no.value;
			             plant_nm1 =plant_nm1+"@"+plant_nm.value;
			             fgsa_no1 =fgsa_no1+"@"+fgsa_no.value;
			             sn_no1 =sn_no1+"@"+sn_no.value;
			             cont_type1 = cont_type1+"@"+cont_type.value;
			             remarks1 = remarks1+"@"+remarks.value;
			             sn_ref_no1= sn_ref_no1+"@"+sn_ref_no.value;
			             mapping_id1= mapping_id1+"@"+ mapping_id.value;
			          }
			          flag=true;
			     }
	}
	else
	{
			  for(var i=0;i<tot_cont.length;i++){
			     if(tot_cont[i].checked){ 
			         if(customer_cd1=="")
			         {
			             customer_cd1 = customer_cd[i].value;
			             to_cntct1 = to_cntct[i].value;
			             from_cntct1 = from_cntct[i].value;
			             plant_seq_no1 = plant_seq_no[i].value;
			             plant_nm1 = plant_nm[i].value;
			             fgsa_no1 = fgsa_no[i].value;
			             sn_no1 = sn_no[i].value;
			             cont_type1 = cont_type[i].value;
			             remarks1 = remarks[i].value;
			             sn_ref_no1= sn_ref_no[i].value;
			             mapping_id1= mapping_id[i].value;
			          }
			          else
			          {
			          	 customer_cd1 =customer_cd1+"@"+customer_cd[i].value;
			             to_cntct1 =to_cntct1+"@"+to_cntct[i].value;
			             from_cntct1 =from_cntct1+"@"+from_cntct[i].value;
			             plant_seq_no1 =plant_seq_no1+"@"+plant_seq_no[i].value;
			             plant_nm1 =plant_nm1+"@"+plant_nm[i].value;
			             fgsa_no1 =fgsa_no1+"@"+fgsa_no[i].value;
			             sn_no1 =sn_no1+"@"+sn_no[i].value;
			             cont_type1 = cont_type1+"@"+cont_type[i].value;
			            
			             var temp_rmk = "";
			             if(remarks[i].value=='' || remarks[i].value==' '){
			            	 temp_rmk = "-";
			             }else{
			            	 temp_rmk = remarks[i].value;
			             }
			             
			             var temp_sn_ref_no = "";
			             if(sn_ref_no[i].value=='' || sn_ref_no[i].value==' '){
			            	 temp_sn_ref_no = "-";
			             }else{
			            	 temp_sn_ref_no = sn_ref_no[i].value;
			             }
			             
			             remarks1 = remarks1+"@"+temp_rmk;
			             sn_ref_no1= sn_ref_no1+"@"+temp_sn_ref_no;
			             mapping_id1= mapping_id1+"@"+ mapping_id[i].value;
			          }
			          flag=true;
			      }
			   }
	} 
   }
    if(flag)
    {
    	print(gas_date,customer_cd1,to_cntct1,from_cntct1,plant_seq_no1,plant_nm1,fgsa_no1,sn_no1,cont_type1,remarks1,type,sn_ref_no1,mapping_id1);
	}   
	else
	{
		alert("Please Select seller Nomination...")
	} 
}

function print(gas_date,customer_cd,to_cntct,from_cntct,plant_seq_no,plant_nm,fgsa_no,sn_no,cont_type,remarks,type,sn_ref_no,mapping_id)
{
// 	alert(remarks)
	
	if(!newWindow || newWindow.closed)
	{
		newWindow= window.open("../pdf_reports/daily_nomination/pdf_daily_seller_nomination_dtls_new.jsp?gas_date="+gas_date+"&mapping_id="+mapping_id+"&to_cntct="+to_cntct+"&from_cntct="+from_cntct+"&sn_ref_no="+sn_ref_no+"&customer_cd="+customer_cd+"&plant_seq_no="+plant_seq_no+"&plant_nm="+plant_nm+"&fgsa_no="+fgsa_no+"&sn_no="+sn_no+"&cont_type="+cont_type+"&remarks="+remarks,"rpt_Daily_Seller_Nomination","top=10,left=70,width=900,height=700,scrollbars=1");
	}
	else
	{
		newWindow.close();
	    newWindow= window.open("../pdf_reports/daily_nomination/pdf_daily_seller_nomination_dtls_new.jsp?gas_date="+gas_date+"&mapping_id="+mapping_id+"&to_cntct="+to_cntct+"&from_cntct="+from_cntct+"&sn_ref_no="+sn_ref_no+"&customer_cd="+customer_cd+"&plant_seq_no="+plant_seq_no+"&plant_nm="+plant_nm+"&fgsa_no="+fgsa_no+"&sn_no="+sn_no+"&cont_type="+cont_type+"&remarks="+remarks,"rpt_Daily_Seller_Nomination","top=10,left=70,width=900,height=700,scrollbars=1");
	}

}
function sendParent1(gas_date,gen_date,ind,sz,sn_ref_no)
{
	var index = parseInt(ind);
	var size = parseInt(sz);
	var gas_date = document.forms[0].gas_date.value;
	//var gen_date = document.forms[0].gen_date.value;
	
	var customer_cd = "0";
	var to_cntct = "0";
	var from_cntct = "0";
		
	var plant_seq_no = "0";
	var plant_nm = "";
	
	var fgsa_no = "0";
	var sn_no = "0";
	var cont_type = "";
	var remarks ="";	
	var mapping_id="";
	if(size==1)
	{
		customer_cd = document.forms[0].customer_cd.value;
		to_cntct = document.forms[0].to_contact.value;
		from_cntct = document.forms[0].from_contact.value;
		
		plant_seq_no = document.forms[0].plant_seq_no.value;
		plant_nm = document.forms[0].plant_nm.value;
		
		fgsa_no = document.forms[0].fgsa_no.value;
		sn_no = document.forms[0].sn_no.value;
		cont_type = document.forms[0].cont_type.value;
		remarks =document.forms[0].remarks.value;	
		mapping_id =document.forms[0].mapping_id.value;	
	}
	else if(size>1)
	{
		customer_cd = document.forms[0].customer_cd[index].value;
		to_cntct = document.forms[0].to_contact[index].value;
		from_cntct = document.forms[0].from_contact[index].value;

		plant_seq_no = document.forms[0].plant_seq_no[index].value;
		plant_nm = document.forms[0].plant_nm[index].value;		
		
		fgsa_no = document.forms[0].fgsa_no[index].value;
		sn_no = document.forms[0].sn_no[index].value;
		cont_type = document.forms[0].cont_type[index].value;
		remarks =document.forms[0].remarks[index].value;
		mapping_id =document.forms[0].mapping_id[index].value;			
	}
	
	
		
// 	alert("to_cntct = "+to_cntct+"from_cntct = "+from_cntct+" ,customer_cd = "+customer_cd);
	//alert("fgsa_no = "+fgsa_no+"sn_no = "+sn_no+" ,cont_type = "+cont_type);
	
	
	if(!newWindow || newWindow.closed)
	{
// 		alert(gas_date)
		newWindow= window.open("../reports/rpt_daily_seller_nomination.jsp?gas_date="+gas_date+"&mapping_id="+mapping_id+"&to_cntct="+to_cntct+"&from_cntct="+from_cntct+"&sn_ref_no="+sn_ref_no+"&customer_cd="+customer_cd+"&plant_seq_no="+plant_seq_no+"&plant_nm="+plant_nm+"&fgsa_no="+fgsa_no+"&sn_no="+sn_no+"&cont_type="+cont_type+"&remarks="+remarks,"rpt_Daily_Seller_Nomination","top=10,left=70,width=900,height=700,scrollbars=1");
	}
	else
	{
		newWindow.close();
	    newWindow= window.open("../reports/rpt_daily_seller_nomination.jsp?gas_date="+gas_date+"&mapping_id="+mapping_id+"&to_cntct="+to_cntct+"&from_cntct="+from_cntct+"&sn_ref_no="+sn_ref_no+"&customer_cd="+customer_cd+"&plant_seq_no="+plant_seq_no+"&plant_nm="+plant_nm+"&fgsa_no="+fgsa_no+"&sn_no="+sn_no+"&cont_type="+cont_type+"&remarks="+remarks,"rpt_Daily_Seller_Nomination","top=10,left=70,width=900,height=700,scrollbars=1");
	}
}

function sendParent2(gas_date,gen_date,ind,sz,sn_ref_no)
{
// 	alert('in')
	var index = parseInt(ind);
	var size = parseInt(sz);
	var gas_date = document.forms[0].gas_date.value;
	//var gen_date = document.forms[0].gen_date.value;
	
	var customer_cd = "0";
	var to_cntct = "0";
	var from_cntct = "0";
	
	var plant_seq_no = "0";
	var plant_nm = "";
	
	var fgsa_no = "0";
	var sn_no = "0";
	var cont_type = "";
	var remarks ="";	
	var mapping_id="";	
	if(size==1)
	{
		customer_cd = document.forms[0].customer_cd.value;
		to_cntct = document.forms[0].to_contact.value;
		from_cntct = document.forms[0].from_contact.value;
		
		plant_seq_no = document.forms[0].plant_seq_no.value;
		plant_nm = document.forms[0].plant_nm.value;
		
		fgsa_no = document.forms[0].fgsa_no.value;
		sn_no = document.forms[0].sn_no.value;
		cont_type = document.forms[0].cont_type.value;	
		remarks =document.forms[0].remarks.value;	
		mapping_id =document.forms[0].mapping_id.value;	
	}
	else if(size>1)
	{
		customer_cd = document.forms[0].customer_cd[index].value;
		to_cntct = document.forms[0].to_contact[index].value;
		from_cntct = document.forms[0].from_contact[index].value;
		
		plant_seq_no = document.forms[0].plant_seq_no[index].value;
		plant_nm = document.forms[0].plant_nm[index].value;
		
		fgsa_no = document.forms[0].fgsa_no[index].value;
		sn_no = document.forms[0].sn_no[index].value;
		cont_type = document.forms[0].cont_type[index].value;
		remarks =document.forms[0].remarks[index].value;
		mapping_id =document.forms[0].mapping_id[index].value;			
	}
	
	if(!newWindow || newWindow.closed)
	{
		newWindow= window.open("../pdf_reports/daily_nomination/pdf_daily_seller_nomination_dtls.jsp?gas_date="+gas_date+"&mapping_id="+mapping_id+"&to_cntct="+to_cntct+"&from_cntct="+from_cntct+"&sn_ref_no="+sn_ref_no+"&customer_cd="+customer_cd+"&plant_seq_no="+plant_seq_no+"&plant_nm="+plant_nm+"&fgsa_no="+fgsa_no+"&sn_no="+sn_no+"&cont_type="+cont_type+"&remarks="+remarks,"rpt_Daily_Seller_Nomination","top=10,left=70,width=900,height=700,scrollbars=1");
	}
	else
	{
		newWindow.close();
	    newWindow= window.open("../pdf_reports/daily_nomination/pdf_daily_seller_nomination_dtls.jsp?gas_date="+gas_date+"&mapping_id="+mapping_id+"&to_cntct="+to_cntct+"&from_cntct="+from_cntct+"&sn_ref_no="+sn_ref_no+"&customer_cd="+customer_cd+"&plant_seq_no="+plant_seq_no+"&plant_nm="+plant_nm+"&fgsa_no="+fgsa_no+"&sn_no="+sn_no+"&cont_type="+cont_type+"&remarks="+remarks,"rpt_Daily_Seller_Nomination","top=10,left=70,width=900,height=700,scrollbars=1");
	}
}

function openxlsheet(gas_date,gen_date,ind,sz,sn_ref_no)
{	
	
	var index = parseInt(ind);
	var size = parseInt(sz);
	var gas_date = document.forms[0].gas_date.value;
	var convt_mmbtu_to_mt = document.forms[0].convt_mmbtu_to_mt.value;
	
	var customer_cd = "0";
	var to_cntct = "0";
	var from_cntct = "0";
	
	var plant_seq_no = "0";
	var plant_nm = "";
	
	var fgsa_no = "0";
	var sn_no = "0";
	var cont_type = "";
	var remarks ="";	
	var mapping_id="";	
	if(size==1)
	{
		customer_cd = document.forms[0].customer_cd.value;
		to_cntct = document.forms[0].to_contact.value;
		from_cntct = document.forms[0].from_contact.value;
		
		plant_seq_no = document.forms[0].plant_seq_no.value;
		plant_nm = document.forms[0].plant_nm.value;
		
		fgsa_no = document.forms[0].fgsa_no.value;
		sn_no = document.forms[0].sn_no.value;
		cont_type = document.forms[0].cont_type.value;	
		remarks =document.forms[0].remarks.value;	
		mapping_id =document.forms[0].mapping_id.value;	
	}
	else if(size>1)
	{
		customer_cd = document.forms[0].customer_cd[index].value;
		to_cntct = document.forms[0].to_contact[index].value;
		from_cntct = document.forms[0].from_contact[index].value;
		
		plant_seq_no = document.forms[0].plant_seq_no[index].value;
		plant_nm = document.forms[0].plant_nm[index].value;
		
		fgsa_no = document.forms[0].fgsa_no[index].value;
		sn_no = document.forms[0].sn_no[index].value;
		cont_type = document.forms[0].cont_type[index].value;
		remarks =document.forms[0].remarks[index].value;
		mapping_id =document.forms[0].mapping_id[index].value;	
	}
		
	//alert("to_cntct = "+to_cntct+"from_cntct = "+from_cntct+" ,customer_cd = "+customer_cd);
	//alert("fgsa_no = "+fgsa_no+"sn_no = "+sn_no+" ,cont_type = "+cont_type);
	
   	var file_nm = "Seller_Nomination_To_Customer_21_04_2011.xls";
   	  		
		if(!newWindow || newWindow.closed)
		{
			newWindow= window.open("../reports/xls_daily_seller_nomination.jsp?gas_date="+gas_date+"&mapping_id="+mapping_id+"&to_cntct="+to_cntct+"&from_cntct="+from_cntct+"&sn_ref_no="+sn_ref_no+"&customer_cd="+customer_cd+"&plant_seq_no="+plant_seq_no+"&plant_nm="+plant_nm+"&fgsa_no="+fgsa_no+"&sn_no="+sn_no+"&cont_type="+cont_type+"&remarks="+remarks+"&convt_mmbtu_to_mt="+convt_mmbtu_to_mt,"rpt_Daily_Seller_Nomination","top=10,left=70,width=900,height=700,scrollbars=1");
		}
		else 
		{
			newWindow.close();
		    newWindow= window.open("../reports/xls_daily_seller_nomination.jsp?gas_date="+gas_date+"&mapping_id="+mapping_id+"&to_cntct="+to_cntct+"&from_cntct="+from_cntct+"&sn_ref_no="+sn_ref_no+"&customer_cd="+customer_cd+"&plant_seq_no="+plant_seq_no+"&plant_nm="+plant_nm+"&fgsa_no="+fgsa_no+"&sn_no="+sn_no+"&cont_type="+cont_type+"&remarks="+remarks+"&convt_mmbtu_to_mt="+convt_mmbtu_to_mt,"rpt_Daily_Seller_Nomination","top=10,left=70,width=900,height=700,scrollbars=1");
		}			
}
function doSubmit()
{
	var flag = true;
	var msg = "First Check The Following Fields :\n\n";	
	var gas_date = document.forms[0].gas_date.value;
	
	if(gas_date==null || gas_date=='' || gas_date==' ')
	{
		msg += "Please, First Select The Gas - Date !!!\n";
		flag = false;
	}
		
	if(flag)
	{
		var a = confirm("Do You Want To Submit Data Regarding This Daily Seller Nomination to Customer Report???");		
		if(a)
		{
			document.forms[0].submit();
		}
	}
	else
	{
		alert(msg);
	}
}
function sendEmail(gas_date,gen_date,ind,sz,sn_ref_no){
	
	var index = parseInt(ind);
	var size = parseInt(sz);
	var gas_date = document.forms[0].gas_date.value;
	//var gen_date = document.forms[0].gen_date.value;
	
	var customer_cd = "0";
	var to_cntct = "0";
	var from_cntct = "0";
	
	var plant_seq_no = "0";
	var plant_nm = "";
	
	var fgsa_no = "0";
	var sn_no = "0";
	var cont_type = "";
	var remarks ="";	
	var mapping_id="";
	var cust_abbr="";
	if(size==1)
	{
		customer_cd = document.forms[0].customer_cd.value;
		to_cntct = document.forms[0].to_contact.value;
		from_cntct = document.forms[0].from_contact.value;
		
		plant_seq_no = document.forms[0].plant_seq_no.value;
		plant_nm = document.forms[0].plant_nm.value;
		
		fgsa_no = document.forms[0].fgsa_no.value;
		sn_no = document.forms[0].sn_no.value;
		cont_type = document.forms[0].cont_type.value;	
		remarks =document.forms[0].remarks.value;	
		mapping_id =document.forms[0].mapping_id.value;	
		cust_abbr = document.forms[0].cust_abbr.value;
	}
	else if(size>1)
	{
		customer_cd = document.forms[0].customer_cd[index].value;
		to_cntct = document.forms[0].to_contact[index].value;
		from_cntct = document.forms[0].from_contact[index].value;
		
		plant_seq_no = document.forms[0].plant_seq_no[index].value;
		plant_nm = document.forms[0].plant_nm[index].value;
		
		fgsa_no = document.forms[0].fgsa_no[index].value;
		sn_no = document.forms[0].sn_no[index].value;
		cont_type = document.forms[0].cont_type[index].value;
		remarks =document.forms[0].remarks[index].value;
		mapping_id =document.forms[0].mapping_id[index].value;	
		cust_abbr = document.forms[0].cust_abbr[index].value;
	}
	
	
	if(!newWindow || newWindow.closed)
	{
		newWindow= window.open("../reports/mail_seller_nom_rpt.jsp?gas_date="+gas_date+"&mapping_id="+mapping_id+"&to_cntct="+to_cntct+"&from_cntct="+from_cntct+"&sn_ref_no="+sn_ref_no+"&customer_cd="+customer_cd+"&plant_seq_no="+plant_seq_no+"&plant_nm="+plant_nm+"&fgsa_no="+fgsa_no+"&sn_no="+sn_no+"&cont_type="+cont_type+"&remarks="+remarks+"&cust_abbr="+cust_abbr,"PDF_Seller_Nomination_To_Customer","top=10,left=70,width=900,height=700,scrollbars=1,menubar=1");
	}
	else 
	{
		newWindow.close();
	    newWindow= window.open("../reports/mail_seller_nom_rpt.jsp?gas_date="+gas_date+"&mapping_id="+mapping_id+"&to_cntct="+to_cntct+"&from_cntct="+from_cntct+"&sn_ref_no="+sn_ref_no+"&customer_cd="+customer_cd+"&plant_seq_no="+plant_seq_no+"&plant_nm="+plant_nm+"&fgsa_no="+fgsa_no+"&sn_no="+sn_no+"&cont_type="+cont_type+"&remarks="+remarks+"&cust_abbr="+cust_abbr,"PDF_Seller_Nomination_To_Customer","top=10,left=70,width=900,height=700,scrollbars=1,menubar=1");
	}	
}

function refreshPageFromChild2(gas_date,msg){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	location.replace("../reports/rpt_master.jsp?gas_date="+gas_date+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&msg="+msg);
}

</script>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Seller_Report_Generation" id="dsRpt"  scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<%
	double convt_mmbtu_to_mt = (double) session.getAttribute("convt_mmbtu_to_mt");
	utilBean.init();

	String curr_dt = utilBean.getGen_dt();
	String next_dt = utilBean.getDate_tomorrow();
	
	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();
	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();
	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();
	
	String gas_date = request.getParameter("gas_date")==null?next_dt:request.getParameter("gas_date");
	String gen_date = request.getParameter("gen_date")==null?curr_dt:request.getParameter("gen_date");
	String from_cntct = request.getParameter("from_cntct")==null?next_dt:request.getParameter("from_cntct");
	String to_cntct = request.getParameter("to_cntct")==null?curr_dt:request.getParameter("to_cntct");
	String customer_cd = request.getParameter("customer_cd")==null?"0":request.getParameter("customer_cd");
	String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
	String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
	String cont_type = request.getParameter("cont_type")==null?"":request.getParameter("cont_type");
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	dsRpt.setGen_date(gen_date);
	dsRpt.init();	
	if(!dsRpt.getGas_date().trim().equalsIgnoreCase(""))
	{
		gas_date = dsRpt.getGas_date().trim();
	}	
	dsRpt.setCallFlag("DAILY_SELLER_NOM_CUSTOMER");
	dsRpt.setGas_date(gas_date);
	dsRpt.setCustomer_cd(customer_cd);
	dsRpt.setCont_type(cont_type);
	dsRpt.setSn_no(sn_no);
	dsRpt.setFgsa_no(fgsa_no);
	dsRpt.init();		
	
	Vector CUSTOMER_CD = dsRpt.getCUSTOMER_CD();
	Vector PLANT_SEQ_NO = dsRpt.getPLANT_SEQ_NO();
	Vector PLANT_NM = dsRpt.getPLANT_NM();
	Vector FGSA_NO = dsRpt.getFGSA_NO();
	Vector SN_NO = dsRpt.getSN_NO();
	Vector CONTRACT_TYPE = dsRpt.getCONTRACT_TYPE();
	Vector CUSTOMER_ABBR = dsRpt.getCUSTOMER_ABBR();
	Vector SEQ_NO = dsRpt.getSEQ_NO();
	Vector CONTACT_PERSON =dsRpt.getCONTACT_PERSON();
	Vector SUPP_SEQ_NO = dsRpt.getSUPP_SEQ_NO();
	Vector SUPP_CONTACT_PERSON = dsRpt.getSUPP_CONTACT_PERSON();
	Vector REMARKS = dsRpt.getREMARKS();
	Vector NOM_REV_NO = dsRpt.getNOM_REV_NO();
	Vector MAPPING_ID=dsRpt.getMAPPING_ID();
	
	Vector sn_ref_no = dsRpt.getSn_ref_no();
// 	System.out.println("CUSTOMER_ABBR.size()  ==== "+CUSTOMER_ABBR.size());
	Vector file_nm=new Vector();
	for(int k=0;k<CUSTOMER_ABBR.size();k++)
	{
	String fileName = "";
	
		if(CONTRACT_TYPE.elementAt(k).toString().trim().equalsIgnoreCase("S"))
		{
			fileName = "CUSTOMER_SELLER_NOM-"+gas_date.trim().substring(0,2)+gas_date.trim().substring(3,5)+gas_date.trim().substring(6)+"-"+CUSTOMER_ABBR.elementAt(k)+"-"+PLANT_NM.elementAt(k)+"-FG"+FGSA_NO.elementAt(k)+"-SN"+sn_ref_no.elementAt(k)+".pdf";
		}
		else if(CONTRACT_TYPE.elementAt(k).toString().trim().equalsIgnoreCase("L"))
		{
			fileName = "CUSTOMER_SELLER_NOM-"+gas_date.trim().substring(0,2)+gas_date.trim().substring(3,5)+gas_date.trim().substring(6)+"-"+CUSTOMER_ABBR.elementAt(k)+"-"+PLANT_NM.elementAt(k)+"-TN"+FGSA_NO.elementAt(k)+"-LOA"+sn_ref_no.elementAt(k)+".pdf";
		}
		
	file_nm.add(fileName);
	}
	
	String e = request.getRealPath("/pdf_reports/daily_nomination/pdf_files");
// 	System.out.println("File Name to Path  :"+e);
	File directory = new File(e);
	Vector temp=new Vector();	
	String fNm="";
	String FILE_NAME="";
	String[] info = directory.list();
// 	System.out.println("info  :"+info.length);
	File n=new File(e);
	if(file_nm.size()!=0){
		String fileName1[] = new String[info.length];
		//File n1=new File(e);
		for (int l = 0; l < info.length; l++) {
			n = new File(e + directory.separator + info[l]);
			if (n.isFile()) {	
// 				System.out.println("n.getName():"+n.getName());
				temp.add(n.getName());
			}
		}
	}
	Vector avail_file=new Vector();
	for(int h=0;h<file_nm.size();h++)
	{
		if(temp.contains(file_nm.elementAt(h).toString()))
		{
			avail_file.add("Y");
		}
		else
		{
			avail_file.add("N");
		}
		
	}
// 	dsRpt.setCallFlag("Daily_Seller_Rpt");
	
	
	
	String contracType = "";
%>
<div class="tab-content">
<div class="tab-pane active" id="hcasreport">
<!-- Default box -->
<div class="box mb-0">
<form method="post" name="daily_seller_nom" action="../servlet/Frm_MgmtV2">

<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
<input type="hidden" name="count1" value="<%=CUSTOMER_CD.size() %>" >
<input type="hidden" name="convt_mmbtu_to_mt" value="<%=convt_mmbtu_to_mt %>" >

<input name="option" type="hidden" value="SELLER_CUSTOMER">
<%if(msg.length()>5){%>
	
<div class="box-body table-responsive no-padding">
	<table class="table  table-bordered">
		<thead>   
			<tr class="info">
				<th class="text-center"  style="color: blue;"><%=msg %></th>
			</tr>
		</thead>
	</table>
</div>
<%} %>
	<div class="box-header with-border main-header" >
		 <div class="form-group col-md-2">
		 	 <label for="">Gas Day</label>
		      <div class='input-group date' id='datetimepicker'>
				<input type='text' class="form-control" id="d1" type="text"  name="gas_date"  value="<%=gas_date%>" onBlur="validateDate(this);" />
				<span class="input-group-addon">
					<i class="fa fa-calendar"></i>
				</span>
			</div>
		 </div>
		
		<div class="form-group col-md-9">
		 	 <label for="">&nbsp;</label>
		 	  <div class='input-group'>
		     	<button type="button"  class="btn btn-primary"   name="viewList" value="View" onclick="refreshPage();" >View List</button>
		     </div>
		 </div>
		 
		 <div class="form-group col-md-1">
		 	 <label for="">&nbsp;</label>
		 	  <div class='input-group'>
		 	  <input type=button class="btn btn-primary" name=print value="Print All" onclick="setPoints(this);"
			<%if(CUSTOMER_CD.size()<=0) {%>
				disabled="disabled"
			<%} %>
			>
		     </div>
		 </div>
	</div>
	
	
<div class="box-body table-responsive no-padding">
	<table class="table  table-bordered" style="overflow-x:auto;" width="100%">	
	  <thead>   
		<tr class="info">
			<th class="text-center">Customer Name</th>
			<th class="text-center">To Contact<br>Person</th>
			<th class="text-center" >SN/LoA<br>No</th>
			<th class="text-center">Seq NO</th>
			<th class="text-center">Plant Name</th>
			<th class="text-center">From Contact<br>Person</th>
			<th class="text-center">Remarks</th>
			<th class="text-center" colspan="4">Generate Details</th>
			<th class="text-center"><input class="btn btn-primary" type=button name=chkUNchk value="Check All" onClick="checkUncheckAll();"
			<%if(CUSTOMER_CD.size()<=0){%>
				disabled="disabled"<%} %>>
			</th>
			<th class="text-center" ></th>
		</tr>
	</thead>
	<tbody>
		<%if(CUSTOMER_CD.size()<=0){%>
			<tr >
				<th class="text-center"  style="color: red;" colspan="13">Data not Present for Daily Seller Nomination</th>
			</tr>
		<%}else {
			for(int i=0; i<CUSTOMER_CD.size();i++){%>
			<tr>
				<td class="text-center"><%=CUSTOMER_ABBR.elementAt(i)%></td>
				<td class="text-center">
					<select name="to_contact"> 
					<%for(int j=0; j<((Vector)SEQ_NO.elementAt(i)).size(); j++){%>
						<option value="<%=((Vector)SEQ_NO.elementAt(i)).elementAt(j)%>"><%=((Vector)CONTACT_PERSON.elementAt(i)).elementAt(j)%></option>								
					<%}%>
					</select>
				</td>
				
				<td class="text-center">
			   <% if((""+CONTRACT_TYPE.elementAt(i)).equalsIgnoreCase("S")){ 
				  		if((""+sn_ref_no.elementAt(i)).trim().equals("")){ %>
			  			      SN - <%=SN_NO.elementAt(i)%>
			  		 <% }else{ %>
			  		    	  SN - <%=sn_ref_no.elementAt(i)%>
			  		 <% }			  		    
			      }else if((""+CONTRACT_TYPE.elementAt(i)).equalsIgnoreCase("L")){
			    	  if((""+sn_ref_no.elementAt(i)).trim().equals("")){ %>
		  			      LOA -<%=SN_NO.elementAt(i)%>
					 <% }else{ %>
		  		    		  LOA -<%=sn_ref_no.elementAt(i)%>
		  		 	 <% } %>
			   <% }%>			   
				</td>
				<td class="text-center">
			   		<%=NOM_REV_NO.elementAt(i)%>
				</td>
				<td class="text-center">
					<input type="hidden" name="plant_seq_no" value="<%=PLANT_SEQ_NO.elementAt(i)%>" maxlength="400" size="35">
					<input type="hidden" name="plant_nm" value="<%=PLANT_NM.elementAt(i)%>" maxlength="400" size="35">
					<%=PLANT_NM.elementAt(i)%>
				</td>					
			
				<td class="text-center">
					<select name="from_contact"> 
					<%for(int j=0; j<SUPP_SEQ_NO.size(); j++) {%>
						<option value="<%=SUPP_SEQ_NO.elementAt(j)%>"><%=SUPP_CONTACT_PERSON.elementAt(j)%></option>								
					<%}%>
					</select>
				</td>
				<td class="text-center"><input type="text" name="remarks" value="<%=REMARKS.elementAt(i)%>" maxlength="400" size="30">
					<input type="button" class="btn btn-success" name="remark" value="Submit" onclick="doSubmit();">
				</td>
				
				<td class="text-center">
					<input type="hidden" name="customer_cd" value="<%=CUSTOMER_CD.elementAt(i)%>" >
					<input type="hidden" name="fgsa_no" value="<%=FGSA_NO.elementAt(i)%>" >
					<input type="hidden" name="sn_no" value="<%=SN_NO.elementAt(i)%>" >
					<input type="hidden" name="cont_type" value="<%=CONTRACT_TYPE.elementAt(i)%>" >
					<input type="hidden" name="sn_ref_no" value="<%=sn_ref_no.elementAt(i)%>" >
					<input type="hidden" name="mapping_id" value="<%=MAPPING_ID.elementAt(i)%>" >
					<input type="hidden" name="cust_abbr" value="<%=CUSTOMER_ABBR.elementAt(i)%>" >
					<input type="button" name="dtls" class="btn btn-primary" value="Generate Details" onclick="sendParent1('<%=gas_date%>','<%=gen_date%>','<%=i%>','<%=CUSTOMER_CD.size()%>','<%=sn_ref_no.elementAt(i)%>');">												
				</td>
				<td class="text-center"><input type="button" class="btn btn-primary" name="dtls" value="View PDF" onclick="sendParent2('<%=gas_date%>','<%=gen_date%>','<%=i%>','<%=CUSTOMER_CD.size()%>','<%=sn_ref_no.elementAt(i)%>');"></td>
		  		<td class="text-center"><input type="button" class="btn btn-warning" name="open_excel" value="Export to Excel" onclick="openxlsheet('<%=gas_date%>','<%=gen_date%>','<%=i%>','<%=CUSTOMER_CD.size()%>','<%=sn_ref_no.elementAt(i)%>');"></td>
		  		<td class="text-center"><input type="button" class="btn btn-primary" name="send_email" value="Send Email" onclick="sendEmail('<%=gas_date%>','<%=gen_date%>','<%=i%>','<%=CUSTOMER_CD.size()%>','<%=sn_ref_no.elementAt(i)%>');"></td>
		  		<td class="text-center">
		  		<input type=checkbox name=chk_cont  ></td>
		  		<td class="text-center"><%if(avail_file.elementAt(i).toString().equalsIgnoreCase("Y")){ %>
		  			<font color="green"> Pdf Already Generated</font> <%}else{ %>&nbsp;<%} %></td>
			</tr>
						
			<%}
		}%>		
	
	</tbody>
	</table>
</div>
</form>
</div>
</div>
</div>
<!-- jQuery -->
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script>
$(function () {
$('#datetimepicker').datepicker({
changeMonth: true,
changeYear: true,
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
todayHighlight: true,
orientation: "bottom auto"
});
});
$(function () {
$('#datetimepicker1').datepicker({
changeMonth: true,
changeYear: true,
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
todayHighlight: true,
orientation: "bottom auto"
});
});
</script>
</html>
