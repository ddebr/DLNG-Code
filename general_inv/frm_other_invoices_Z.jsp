<%-- <%@page import="com.sun.org.glassfish.external.arc.Taxonomy"%> --%>
<%-- <%@page import="org.apache.poi.hssf.record.VCenterRecord"%> --%>
<!DOCTYPE html>
<%@ page import="java.util.Vector" %>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title> TLU </title>

<!-- For accordion  -->
<link rel="stylesheet" href="../responsive/w3/w3.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css">
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="../css/pt_sans.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>

<script language="JavaScript" src="../js/util2.js"></script>
<script language="JavaScript" src="../js/util1.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>
<link rel="stylesheet" href="../css/tlu.css">
<title>Invoice </title>
<style>
#loading {
   width: 100%;
   height: 100%;
   top: 0;
   left: 0;
   position: fixed;
   display: block;
   opacity: 0.7;
   background-color: #fff;
   z-index: 99;
   text-align: center;
}

#loading-image {
  position: absolute;
  top: 50%; /*window.innerHeight/2; /*300px;*/
  left: 50%; /*window.innerWidth/2; /*240px;*/
  z-index: 100;
}
</style>

<style type="text/css">
table tr{
   height: 50px;
   color: black;
}
.select {
    width: 200px;
    height: 27px;
} 
</style>
<script>
function checkAlphanumeric_here(alphane)
{
  	var numaric = alphane.value;
	for(var j=0; j<numaric.length; j++)
	{
		var alphaa = numaric.charAt(j);
		var hh = alphaa.charCodeAt(0);
		//alert(hh);
		if((hh > 46 && hh<58) || (hh > 64 && hh<91) || (hh > 96 && hh<123) || (hh ==45))
		{
		
		}
		else
		{
        	alert(" Not An Alpha Numeric Value !!!");
            alphane.value="";
			return false;
		 }
 	}
}


function back_go()
{
	var yr=document.forms[0].year_hid.value;
	var month=document.forms[0].month_hid.value;
	var inv_type=document.forms[0].inv_type_hid.value;
	var Supp_Cd=document.forms[0].supp_cd.value;
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var form_cd=document.forms[0].form_id.value;
	var form_name=document.forms[0].form_nm.value;
	
	var url="../general_inv/frm_other_invoices_dtl.jsp?year="+yr+"&month="+month+"&inv_type="+inv_type+"&supplier_cd="+Supp_Cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&form_cd="+form_cd+"&form_name="+form_name;
	location.replace(url);
}
function sel_supplier()
{
	var month=document.forms[0].month_hid.value;
	var year=document.forms[0].year_hid.value;
	var inv_type=document.forms[0].inv_type_hid.value;
	var fin_yr_kk=document.forms[0].Fin_yr_hid.value;
// 	alert(fin_yr_kk);
// 	var supp_cd_hid=document.forms[0].supp_cd_hid.value;
	var flag="Sup";
	var url="../general_inv/frm_customerlist.jsp?month="+month+"&year="+year+"&flag="+flag+"&inv_type="+inv_type+"&fin_yr_kk="+fin_yr_kk;
	window.open(url,"actionReport","top=10,left=70,width=950,height=900,scrollbars=1,status=1,maximize=yes,resizable=1")	
}

function sel_radio(flag)
{
	if(flag=='S') {
		//alert("ehere--"+document.getElementById('hsnnn_cd'));
		document.getElementById('hsnnn_cd').innerHTML="SAC Code";
		document.forms[0].flag_radio_hid.value="S";
		document.getElementById('hsnnn_cd').style.fontWeight="bold";
		document.getElementById('hsnnn_cd').style.fontSize="14px";
		//document.forms[0].flag_radio_hid="SAC Code";
		
	}else{
		document.getElementById('hsnnn_cd').innerHTML="HSN Code";
		document.forms[0].flag_radio_hid.value="P";
		document.getElementById('hsnnn_cd').style.fontWeight="bold";
		document.getElementById('hsnnn_cd').style.fontSize="14px";
	}
}
function sel_typ()
{
	var yr=document.forms[0].year.value;
	var month=document.forms[0].month.value;
	var inv_type=document.forms[0].inv_type.value;
	var url="../general_inv/frm_other_invoices_X.jsp?year="+yr+"&month="+month+"&inv_type="+inv_type;
	location.replace(url);
}
function limitText1(limitField, limitCount, limitNum) {
	var limitField= document.forms[0].t2;
	var limitCount=document.forms[0].countdown1;
	var limitNum=2000;
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, 2000);
		alert("Can not allowed more than 2000 Characters!!!");
	} else {
		limitCount.value = limitNum - limitField.value.length;
	}
}

function breakline1()
{
	if(event.keyCode==13)
	{
		document.forms[0].t2.value=document.forms[0].t2.value;
		document.forms[0].t2.focus();
	}
}


function SetSAC_HSN(tax,selidx)
{
	var r = parseInt(document.forms[0].norows.value);
	
if(selidx=='0'){selidx='0-0';}
	var gst_per=selidx.split("-");
	document.getElementById("hsn_cd"+r).value=gst_per[1];
//SB	document.forms[0].sac_hsn_desc.value=gst_per[1];
	if(tax==1){
	 		document.getElementById("tax_str"+r+"0").value=gst_per[0];		
		}else{
			for(i=0;i<tax;i++){
				//document.getElementById("tax_str1"+i).value=gst_per[0]/2;
				document.getElementById("tax_str"+r+""+i).value=gst_per[0]/2;
			}
		}
	}


function addnewdesc()
{//alert("STEP-0");
	var flag=true;
	var r = parseInt(document.forms[0].norows.value);
	var r1 = parseInt(document.forms[0].rowno.value);
	var tax_sz=document.forms[0].tax_sz.value;
	var abcddsd = document.getElementById("abcd");
	
	var tr = document.createElement("tr");
	var sac_cd = document.getElementById("sac_hsn1");
	var txnm = document.forms[0].tx_nm;alert(txnm); 
	if(txnm==undefined || txnm.length==1){
		//txnm.length=1;alert(txnm.length);
	}else{
			for(i=0;i<txnm.length;i++){
			alert(txnm[i].value);
		
			}
		}
		//alert(flag);
	if(flag==true)
	{//alert(flag);
		//var div = document.getElementById("addingqueoption");
		var td0= document.createElement("td");
		td0.width="5%";
		var div0 = document.getElementById("hsncdd");
		
 		var inpt0 = document.createElement("input");
		inpt0.name="hsn_cd";
 		inpt0.type="hidden";
 		inpt0.value="";
 		inpt0.size="8";
 		
		//inpt1.setAttribute("onblur","calculate_amt(this,'"+tax_sz+"','"+r1+"')");
		//onblur=calculate_rate(this);
		
		inpt0.title="Insert hsncode";
		
				 
		var inpt01 = document.createElement("select");
		inpt01.name="sac_hsn";
		//inpt01.type="select-one";
		inpt01.title="select hsncode";
		for(i=0;i<sac_cd.length;i++){
			inpt01.options[i] =new Option(sac_cd.options[i].innerHTML,sac_cd.options[i].value);
		    	
		}
		
		//inpt01.setAttribute("onChange","SetSAC_HSN(txnm,document.forms[0].sac_hsn[document.forms[0].sac_hsn.selectedIndex])");
		inpt01.setAttribute("onBlur","SetSAC_HSN(document.forms[0].tx_nm.length,this.value)");
				//"SetSAC_HSN(txnm,document.forms[0].sac_hsn['"+r1+"'].[document.forms[0].sac_hsn['""'].selectedIndex])");
		inpt01.style.backgroundColor="lightblue";
		inpt01.style.color="white";
		inpt01.style.fontWeight="bold";
				
		var td1 = document.createElement("td");
		td1.width="46%";
		var inpt = document.createElement("input");
		inpt.name="queoption";
		inpt.size="90";
		inpt.type="text";
		inpt.value="";
		inpt.title="Insert Name";
				
		
		var td2 = document.createElement("td");
		var inpt1 = document.createElement("input");
		inpt1.name="rate";
		inpt1.type="text";
		inpt1.style.textAlign="right";
		inpt1.value="";
		inpt1.size="10";
		inpt1.setAttribute("onblur","calculate_here(this,'"+tax_sz+"','"+r1+"')");
		//onblur=calculate_rate(this);
		inpt1.title="Insert Name";
		
		
		var td3 = document.createElement("td");
		
		var inpt11 = document.createElement("input");
		inpt11.name="UOM";
		inpt11.type="text";
		inpt11.value="";
		inpt11.size="8";
		inpt11.title="Insert Name";


		
		var td4 = document.createElement("td");
		var inpt2 = document.createElement("input");
		inpt2.name="Qty";
		inpt2.type="text";
		inpt2.style.textAlign="right";
		inpt2.setAttribute("onblur","checkNumber1(this,'11','2')");
		inpt2.value="";
		inpt2.size="10";
		inpt2.title="Insert Name";
		
		
		var td5 = document.createElement("td");
		var inpt3 = document.createElement("input");
		inpt3.name="Amt";
		inpt3.type="text";
		inpt3.style.textAlign="right";
		inpt3.setAttribute("readonly","readonly");
		inpt3.setAttribute("class","mkRdly");
		inpt3.value="";
		inpt3.size="10";
		inpt3.title="Insert Name";
		
		alert("STEP-2");
			
		var rows = parseInt(document.forms[0].norows.value);
		rows=rows+1;
		document.forms[0].norows.value=rows;
		document.forms[0].rowno.value=rows;
		
//		var text2=document.createTextNode(""+rows+".");
		
 		var inptId0="hsn_cd"+rows;
 		inpt0.id=inptId0;
		
		var inptId01="sac_hsn"+rows;
		inpt01.id=inptId01;
		
		
		var inptId="queoption"+rows;
		inpt.id=inptId;
		
		var inptId1="rate"+rows;
		inpt1.id=inptId1;
		
		var inptId2="Qty"+rows;
		inpt2.id=inptId2;
		
		var inptId3="Amt"+rows;
		inpt3.id=inptId3;
		
		var inptId11="UOM"+rows;
		inpt11.id=inptId11;
		
		var td6 = document.createElement("td");
		td6.colspan="3";
		var j=0;alert("Step-3: "+txnm);
	//SB	if(txnm.length==2){
		if(txnm==2){
		//for two
		var inpt4 = document.createElement("input");
		inpt4.name="tax_str"+rows+""+j;
		inpt4.type="text";
		inpt4.style.textAlign="right";
		inpt4.value="";
		inpt4.size="1";
		inpt4.id="tax_str"+rows+""+j;
		
		
		var inpt5 = document.createElement("input");
		inpt5.name="tax_amts"+rows+""+j;
		inpt5.type="text";
		inpt5.style.textAlign="right";
		inpt5.value="";
		inpt5.size="6";
		inpt5.id="tax_amts"+rows+""+j;
		
		j=j+1;
		var inpt6 = document.createElement("input");
		inpt6.name="tax_str"+rows+""+j;
		inpt6.type="text";
		inpt6.style.textAlign="right";
		inpt6.value="";
		inpt6.size="1";
		inpt6.id="tax_str"+rows+""+j;
		
		var inpt7 = document.createElement("input");
		inpt7.name="tax_amts"+rows+""+j;
		inpt7.type="text";
		inpt7.style.textAlign="right";
		inpt7.value="";
		inpt7.size="5";
		inpt7.id="tax_amts"+rows+""+j;
		
		}else{
		
		alert("Step-4");
			var inpt4 = document.createElement("input");
			inpt4.name="tax_str"+rows+""+j;
			inpt4.type="text";
			inpt4.style.textAlign="right";
			inpt4.value="";
			inpt4.size="1";
			inpt4.id="tax_str"+rows+""+j;
			
			var inpt5 = document.createElement("input");
			inpt5.name="tax_amts"+rows+""+j;
			inpt5.type="text";
			inpt5.style.textAlign="right";
			inpt5.value="";
			inpt5.size="5";
			inpt5.id="tax_amts"+rows+""+j;
			
		}
		alert("Step-5");
				
		td2.width="8%";//rate
		td3.width="6%"; //UOM
		td4.width="6%"; //Qty
		td5.width="11%";//amount
		td6.width="15%";//tax
		
		td0.appendChild(inpt0);
		td0.appendChild(inpt01);
		td1.appendChild(inpt);
		
		td2.appendChild(inpt1);
		td4.appendChild(inpt2);
		td5.appendChild(inpt3);
		td3.appendChild(inpt11);
		
		alert("Step-5A");
	//SB	if(txnm.length==1){
		if(txnm==1){
			td6.appendChild(inpt4);
			td6.appendChild(inpt5);
		}else{alert("Step-5AElse");
			td6.appendChild(inpt4);
			td6.appendChild(inpt5);	
			td6.appendChild(inpt6);alert("Step-5AA");
			td6.appendChild(inpt7);alert("Step-5AAA");
		}
		alert("Step-6");
		
		tr.appendChild(td0);
		tr.appendChild(td1);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td2);
		tr.appendChild(td5);
		tr.appendChild(td6);
		alert("Step-6a");

		abcddsd.appendChild(tr);	
	}
	document.forms[0].addnew.disabled=true;
	document.forms[0].sac_hsn[rows-1].focus();alert("Step-7");
}

function change_st()
{
	    var yr=document.forms[0].year_hid.value;
		var month=document.forms[0].month_hid.value;
		var inv_type=document.forms[0].inv_type_hid.value;
		var state=document.forms[0].state.value;
		var inv_dt=document.forms[0].inv_dt.value;
		var t2=document.forms[0].t2.value;
		var pan_no=document.forms[0].pan_no.value;
 		
		var address=document.forms[0].address.value;
		var city=document.forms[0].city.value;
		var pin_code=document.forms[0].pin_code.value;
		var ST_NO=document.forms[0].ST_NO.value;
		//var hsn_cd=document.forms[0].hsn_cd.value;
		var sac_desc=document.forms[0].sac_desc.value;
		var sac_cd=document.forms[0].sac_cd.value;
		var flag=document.forms[0].flag.value;
		var hlpl_seq_no1=document.forms[0].hlpl_seq_no1.value;
		var supplier_cd=document.forms[0].supp_cd_hid.value;
		var write_permission = document.forms[0].write_permission.value;
		var delete_permission = document.forms[0].delete_permission.value;
		var print_permission = document.forms[0].print_permission.value;
		var check_permission = document.forms[0].check_permission.value;
		var authorize_permission = document.forms[0].authorize_permission.value;
		var approve_permission = document.forms[0].approve_permission.value;
		var audit_permission = document.forms[0].audit_permission.value;
		var form_cd=document.forms[0].form_id.value;
		var form_name=document.forms[0].form_nm.value;
// 		var curr=document.forms[0].curr.value;
// 		var cust_pan_no=document.forms[0].cust_pan_no.value;
// 		var amount=document.forms[0].amt.value;
// 		var state=document.forms[0].state.value;
// 		var t3=document.forms[0].t3.value;
	
		//var url="../general_inv/frm_other_invoices_X.jsp?year="+yr+"&month="+month+"&inv_type="+inv_type+"&t2="+t2+"&customer_pan_no="+pan_no+"&inv_dt="+inv_dt+"&pay_dt="+pay_dt+"&Contact_Suppl_Service_tax_no="+ST_NO+"&curr="+curr+"&cust_pan_no="+cust_pan_no+"&amt="+amount+"&state="+state+"&t3="+t3;
		var url="../general_inv/frm_other_invoices_Z.jsp?year="+yr+"&month="+month+"&inv_type="+inv_type+"&state="+state+"&inv_dt="+inv_dt+"&Contact_Suppl_PAN_NO="+pan_no+"&Contact_Suppl_gstin="+ST_NO+"&pin_code="+pin_code+"&t2="+t2+"&city="+city+"&address="+address+"&sac_desc="+sac_desc+"&sac_cd="+sac_cd+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&flag="+flag+"&supplier_cd="+supplier_cd+"&hlpl_seq_no="+hlpl_seq_no1+"&form_cd="+form_cd+"&form_name="+form_name;
		location.replace(url);
}
function viewlist()
{
	var month=document.forms[0].month_hid.value;
	var year=document.forms[0].year_hid.value;
	var supp_cd_hid=document.forms[0].supp_cd_hid.value;
	var flag="SAC";
	var url="../general_inv/frm_customerlist.jsp?month="+month+"&year="+year+"&flag="+flag+"&supp_cd_hid="+supp_cd_hid;
	window.open(url,"actionReport","top=10,left=70,width=950,height=900,scrollbars=1,status=1,maximize=yes,resizable=1")
	
}function check_pan(obj)
{
	var p=obj.value;
	if(p.length>10)
		{
			alert("YOU CANNOT ENTER PAN NO MORE THAN 10 CHARACTERS");
			document.forms[0].cust_pan_no.value='';
		}
}

function calculate_amt1(index,obj,sz)
{
	//alert("here");
	var fin_tax_amt=0;
	var tax_amt=0;
	var state=document.forms[0].state.value;
	if(state=='') {
		alert("Please Select State First");
	}else{
		var sup_stcd=document.forms[0].sup_stcd.value;
		var cust_stcd=document.forms[0].cust_stcd.value;
		if(sz==1){
			var amt=document.forms[0].gross_amt.value;
			//alert(amt);
			var new_rate=document.getElementById('tax_str0').value;
			var gross_amt=(parseFloat(amt)*parseFloat(new_rate))/100
			document.forms[0].tax_amt.value=round(parseFloat(gross_amt),0);
			document.getElementById('tax_amts0').value=round(gross_amt,0);
			//document.forms[0].tax_amt_hid.value=gross_amt;
			document.forms[0].net_inr.value=round((parseFloat(gross_amt) + parseFloat(amt)),0);
		}else {
			if(index==0) {
				var amt=document.forms[0].gross_amt.value;
				//alert(amt);
				var ind=parseFloat(index)+1;
				var new_rate=document.getElementById('tax_str'+index).value;
				var tax_amt1=document.getElementById('tax_amts'+ind).value;
				var gross_amt=(parseFloat(amt)*parseFloat(new_rate))/100;
				//alert("gross+_amt--"+gross_amt);
				//document.forms[0].(tax_amt_hid+index.value=gross_amt;
				document.getElementById('tax_amts'+index).value=round(parseFloat(gross_amt),0);
				tax_amt+=round(parseFloat(gross_amt),0);
// 				document.forms[0].tax_amt.value=round((parseFloat(gross_amt)+parseFloat(tax_amt1)),0);
// 				document.forms[0].net_inr.value.value=round((parseFloat(document.forms[0].tax_amt.value)+parseFloat(amt)),0);
			}else {
				var amt=document.forms[0].gross_amt.value;
				
				var ind=0;
				var new_rate=document.getElementById('tax_str'+index).value;
				var tax_amt1=document.getElementById('tax_amts'+ind).value;
				var gross_amt=(parseFloat(amt)*parseFloat(new_rate))/100;
				document.getElementById('tax_amts'+index).value=round(parseFloat(gross_amt),0);
				tax_amt+=round(parseFloat(gross_amt),0);
// 				document.forms[0].tax_amt.value=round((parseFloat(gross_amt)+parseFloat(tax_amt1)),0);
// 				document.forms[0].net_inr.value=round((parseFloat(document.forms[0].tax_amt.value)+parseFloat(amt)),0);
			}
			document.forms[0].tax_amt.value=round((parseFloat(gross_amt)+parseFloat(tax_amt1)),0);
			//alert('here');
			document.forms[0].net_inr.value=round((parseFloat(document.forms[0].tax_amt.value)+parseFloat(amt)),0);
			
		}
	}
}
function calculate_amt_modify(itmsz,taxsz,index)
{
	var fin_amt=0;var fin_amt=0;var tax_amt=0;var rest_amt=0
	if(itmsz ==1) {
		var qty=document.forms[0].Qty.value;
		var rate=document.forms[0].rate.value;
		var amt=parseFloat(qty) * parseFloat(rate);
		document.forms[0].Amt.value=round(parseFloat(amt),2);
		document.forms[0].gross_amt.value=parseFloat(amt);
		fin_amt+=parseFloat(amt);
	}else{
		for(var k=0;k<itmsz;k++) {
			if(k==index) {
				//alert(k);
				var qty=document.forms[0].Qty[index].value;
				//alert(qty);
				var rate=document.forms[0].rate[index].value;
				var amt=parseFloat(qty) * parseFloat(rate);
				document.forms[0].Amt[index].value=round(parseFloat(amt),2);
				//document.forms[0].gross_amt.value=parseFloat(amt);
				fin_amt+=parseFloat(amt);
				//alert("here---"+fin_amt);
			}else{
				//alert(k);
				fin_amt+=parseFloat(document.forms[0].Amt[k].value);
				//fin_amt+=parseFloat(rest_amt);
				//alert("RR--"+rest_amt);
			}
		}
// 		alert("fin_amt--"+fin_amt);
		
	}
	
		if(fin_amt!='' ){
			document.forms[0].gross_amt.value=round(parseFloat(fin_amt),0);
		if(taxsz==1){
			var tax_18=document.getElementById('tax_str0').value;
			var gross_amt=(parseFloat(fin_amt)*parseFloat(tax_18))/100
		//	alert();
			document.forms[0].tax_amt.value=round(parseFloat(gross_amt),0);
			document.getElementById('tax_amts0').value=round(gross_amt,0);
			document.forms[0].net_inr.value=round((parseFloat(gross_amt) + parseFloat(fin_amt)),0);
			document.forms[0].igst_tax_amt_hid.value=round(gross_amt,0);//alert(document.forms[0].cgst_tax_amt_hid.value);
		}else {
			for(var i=0;i<taxsz;i++){
					if(i==0){
						var tax_9=document.getElementById('tax_str'+i).value;
						var gross_amt=(parseFloat(fin_amt)*parseFloat(tax_9))/100;
						document.getElementById('tax_amts'+i).value=round((parseFloat(gross_amt)),0);	
					    tax_amt+=round(parseFloat(gross_amt),0);
					}	else{
						var tax_91=document.getElementById('tax_str'+i).value;
						var gross_amt=(parseFloat(fin_amt)*parseFloat(tax_91))/100;
						document.getElementById('tax_amts'+i).value=round((parseFloat(gross_amt)),0);	
					    tax_amt+=round(parseFloat(gross_amt),0);
					}			
					document.forms[0].cgst_tax_amt_hid.value=round(gross_amt,0);//alert(document.forms[0].cgst_tax_amt_hid.value);	
			}
			document.forms[0].tax_amt.value=round((parseFloat(tax_amt)),0);
			//alert('document.forms[0].tax_amt.value'+document.forms[0].tax_amt.value);
			document.forms[0].net_inr.value=round((parseFloat(document.forms[0].tax_amt.value)+parseFloat(fin_amt)),0);
		} ////main else
		}
	
}

function refrseh(Flag,vendor_cd,city,pan_no,country,pin,state_cd,write_permission,check_permission,authorize_permission,approve_permission,print_permission,audit_permission,delete_permission,yr,month,supp_cd_hid,gstin_no)

{
	var inv_type=document.forms[0].inv_type_hid.value;
	var Fin_yr=document.forms[0].Fin_yr_hid.value;
	var supp_nm=document.forms[0].supp_nm.value;
	var ST_NO=document.forms[0].ST_NO.value;
	//var pan_no=document.forms[0].cust_pan_no1.value;
	var flag_radio_hid=document.forms[0].flag_radio_hid.value;
	var sup_stcd=document.forms[0].supp_state_cd.value;
	var inv_no=document.forms[0].inv_no.value;
	var Inv_seq_no=document.forms[0].INVOICE_SEQ_NO.value;
	var cust_seq_no=document.forms[0].cust_seq_no1.value;
	 
	var NoLine=document.forms[0].form_nm.value;
	var PDF_Format=document.forms[0].pdf_format_type.value; //alert(document.forms[0].pdf_format_type.value);alert(PDF_Format);
	var noLine=document.forms[0].no_line_item.value;; //alert(noLine);
	
	var form_cd=document.forms[0].form_id.value;
	var form_name=document.forms[0].form_nm.value;
	var url="../general_inv/frm_other_invoices_Z.jsp?inv_type="+inv_type+"&year="+yr+"&month="+month+"&cust_type="+Flag+"&CUST_CD="+vendor_cd+"&cust_pan_no="+pan_no+"&pin_code="+pin+"&city="+city+"&state="+state_cd+"&supplier_cd="+supp_cd_hid+"&cust_gstin_no="+gstin_no+"&Contact_Suppl_PAN_NO="+pan_no+"&Contact_Suppl_gstin="+ST_NO+"&Supp_stat_cd="+sup_stcd+"&supp_nm="+supp_nm+"&flag_radio="+flag_radio_hid+"&no_line_item="+noLine+"&pdf_format_type="+PDF_Format+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&form_cd="+form_cd+"&form_name="+form_name+"&cust_seq_no="+cust_seq_no+"&Inv_seq_no="+Inv_seq_no+"&inv_no="+inv_no;
	location.replace(url);
}
function viewvendor(flag_vendor,inv_type)
{
	var month=document.forms[0].month_hid.value;
	var year=document.forms[0].year_hid.value;
	var supp_cd_hid=document.forms[0].supp_cd.value;//alert(supp_cd_hid);
	var custType=document.forms[0].cust_type.value;//alert(custType);
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var authorize_permission = document.forms[0].authorize_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	var url="../general_inv/frm_customerlist.jsp?month="+month+"&year="+year+"&flag="+custType+"&inv_type11="+inv_type+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&check_permission="+check_permission+"&authorize_permission="+authorize_permission+"&supp_cd_hid="+supp_cd_hid;
	window.open(url,"actionReport","top=10,left=70,width=950,height=900,scrollbars=1,status=1,maximize=yes,resizable=1")
}


	




function Do_close(SuplCd,year,month,inv_type,msg,write_perm,check_perm,approve_perm,print_perm,delete_perm)
{
	window.opener.refersh(SuplCd,year,month,inv_type,msg,write_perm,check_perm,approve_perm,print_perm,delete_perm,);
	window.close();
}
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.service_inv.Databean_Service_InvV2" id="serviceInv" scope="page"/> <!-- Hiren_20200508 -->
<jsp:useBean class="com.seipl.hazira.dlng.service_inv.DataBean_Other_InvoiceV2" id="dta" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="page"/>
<%
util.init();
String sysdate = util.getGen_dt();
String curr_year = util.getGet_yr();

String no_line_item1 = request.getParameter("no_line_item")==null?"1":request.getParameter("no_line_item");
String pdf_format_type1 = request.getParameter("pdf_format_type")==null?"0":request.getParameter("pdf_format_type");
String cust_type1 = request.getParameter("cust_type")==null?"V":request.getParameter("cust_type");
String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
//String Supp_Abr = request.getParameter("Supp_Abr")==null?"":request.getParameter("Supp_Abr");
String msg=request.getParameter("msg")==null?"":request.getParameter("msg");
String year=request.getParameter("year")==null?"":request.getParameter("year");
String month=request.getParameter("month")==null?"":request.getParameter("month");
String inv_type=request.getParameter("inv_type")==null?"":request.getParameter("inv_type");
String sac_desc=request.getParameter("sac_desc")==null?"":request.getParameter("sac_desc");
String sac_cd=request.getParameter("sac_cd")==null?"":request.getParameter("sac_cd");
String pin_code=request.getParameter("pin_code")==null?"":request.getParameter("pin_code");
String inv_dt=request.getParameter("inv_dt")==null?sysdate:request.getParameter("inv_dt");
String t2=request.getParameter("t2")==null?"":request.getParameter("t2");
String net_amt_inr=request.getParameter("net_amt_inr")==null?"":request.getParameter("net_amt_inr");
String CUST_CD=request.getParameter("CUST_CD")==null?"":request.getParameter("CUST_CD");
String gross_amt=request.getParameter("gross_amt")==null?"0":request.getParameter("gross_amt");
String supplier_cd=request.getParameter("supplier_cd")==null?"1":request.getParameter("supplier_cd");
String Supp_stat_cd1=request.getParameter("Supp_stat_cd")==null?"0":request.getParameter("Supp_stat_cd");
String sale_no=request.getParameter("sale_no")==null?"":request.getParameter("sale_no");
String uam_no=request.getParameter("uam_no")==null?"":request.getParameter("uam_no");

String supp_nm1=request.getParameter("supp_nm")==null?"":request.getParameter("supp_nm");
String flag_radio=request.getParameter("flag_radio")==null?"S":request.getParameter("flag_radio");
String Inv_seq_no=request.getParameter("Inv_seq_no")==null?"":request.getParameter("Inv_seq_no");
String cust_seq_no=request.getParameter("cust_seq_no")==null?"":request.getParameter("cust_seq_no");
String inv_no=request.getParameter("inv_no")==null?"":request.getParameter("inv_no");


String flag=request.getParameter("flag")==null?"INSERT":request.getParameter("flag");
String fin_yr=request.getParameter("fin_yr")==null?"":request.getParameter("fin_yr");
String seq_no=request.getParameter("seq_no")==null?"":request.getParameter("seq_no");
String hlpl_seq_no=request.getParameter("hlpl_seq_no")==null?"":request.getParameter("hlpl_seq_no");

String write_permission = request.getParameter("alw_add")==null?"":request.getParameter("alw_add");
String check_permission = request.getParameter("alw_view")==null?"":request.getParameter("alw_view");
String approve_permission = request.getParameter("alw_upd")==null?"":request.getParameter("alw_upd");
String delete_permission = request.getParameter("alw_del")==null?"":request.getParameter("alw_del");
String print_permission = request.getParameter("alw_print")==null?"":request.getParameter("alw_print");


String form_cd = request.getParameter("form_cd")==null?"":request.getParameter("form_cd");
String form_name = request.getParameter("form_name")==null?"":request.getParameter("form_name");

String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212

String Contact_Suppl_PAN_NO=request.getParameter("Contact_Suppl_PAN_NO")==null?"":request.getParameter("Contact_Suppl_PAN_NO");
String Contact_Suppl_gstin=request.getParameter("Contact_Suppl_gstin")==null?"":request.getParameter("Contact_Suppl_gstin");
String  trans_cd = request.getParameter("trans_cd")==null?"":request.getParameter("trans_cd");//Hiren_20200511
String gate_no = request.getParameter("gate_pass_no")==null?"":request.getParameter("gate_pass_no");//Hiren_20200511
// String tax_str="18";
// String tax_str1="9";
String tax_amt_inr="";Vector Vtax_amt_inr=new Vector();Vector Vsac_cd=new Vector();Vector vcrt=new Vector();Vector Vuam_no=new Vector();

serviceInv.setCallFlag("transporter_list");//HA20200511
serviceInv.setTrans_cd(trans_cd);
serviceInv.setMonth(month);
serviceInv.setYear(year);
serviceInv.setTruckSize(no_line_item1);
serviceInv.setSup_cd(supplier_cd);
serviceInv.init();

Vector Vtrans_cd = serviceInv.getVtrans_cd();
Vector Vtrans_nm = serviceInv.getVtrans_nm();
String Vtrans_gst_no = serviceInv.getVtrans_gst_no();
String Vtrans_state = serviceInv.getVtrans_state();
String Vtrans_abbr = serviceInv.getVtrans_abbr();
String Vtrans_pan = serviceInv.getVtrans_pan();
String Vtrans_addr = serviceInv.getVtrans_addr();
String Vtrans_city = serviceInv.getVtrans_city();

trans_cd = serviceInv.getTrans_cd();

dta.setLineItem(no_line_item1);
dta.setCustomerType(cust_type1);
dta.setInv_type(inv_type);
dta.setMonth(month);
dta.setYear(year);
dta.setFlag_act(flag);
dta.setSet_sac_cd(sac_cd);
dta.setSvendor_cd(CUST_CD);
dta.setSupp_cd_set(supplier_cd);
dta.setSet_SacHsn(flag_radio);

if(flag.equalsIgnoreCase("Modify")) {
	dta.setVFin_yr(fin_yr);
	dta.setHlpl_seq_no(hlpl_seq_no);
	dta.setSeq_no(seq_no);
	dta.setInv_type(inv_type);
}
dta.setCallFlag("INVOICE_DTL_Z");
dta.init();
Vector state_nm=dta.getState_nm();
Vector state_cd=dta.getState_cd();
String start_dt=dta.getDt();
String Fin_yr=dta.getFin_yr();
String end_dt=dta.getEnd_dt();
Vector tax_name=dta.getTax_nm();
Vector taxstr=dta.getTaxstr();
String Supp_stat_cd=dta.getSupp_stat_CD();
String supp_nm=dta.getSupplier_nm();
String Min_FY=dta.getMin_FY();
 ///////////////////SB20170811/////////////
  Contact_Suppl_PAN_NO=dta.getContact_Suppl_PAN_NO();
  Contact_Suppl_gstin=dta.getContact_Suppl_gstin_no();
  Vector VSupl_sac_cd=dta.getVsac_cd();
  Vector VSupl_desc=dta.getVdesc();
  Vector Vsac_hsn_perc=dta.getVsac_hsn_perc(); //SB20180215
  String new_GST_INVOICE_SEQ_NO=dta.getNewSeqNo(); //SB20170814
  String Supp_addr=dta.getSupp_addr(); //SB20170814
  String Supp_city=dta.getSupp_city(); //SB20170814
  String Supp_pin=dta.getSupp_pin(); //SB20170814
  String Supp_state=dta.getSupp_state(); //SB20170814
  String Supp_state_cd=dta.getSupp_state_cd(); //SB20170814
  String LastPDF_Template=dta.getLastPDF_Template(); //SB20170814
  String Supp_Abr=dta.getSuppAbr(); //SB20180316
  ///////////////////////////////////////
 sac_desc=dta.getSac_desc();
 t2=dta.getSVendor_nm();
String cust_nm="";
// System.out.println("inv_seq_no--"+Inv_seq_no);
if(flag.equalsIgnoreCase("Modify")) {
	inv_dt=dta.getSinv_dt();
//	hsn_cd=dta.getHasn_cd();
	sac_cd=dta.getSac_cd();
	t2=dta.getCust_nm();
	pin_code=dta.getCust_pin();
 	
	CUST_CD=dta.getCustom_cd();
	gate_no=dta.getGate_no();
// 	state=dta.getCust_state_cd1();
// 	 t3=dta.getItem_desc();
	 gross_amt=dta.getSgross_amt_inr();
		 tax_amt_inr=dta.getTax_amt_inr();
		 net_amt_inr=dta.getSnet_amt_inr();
		  Vtax_amt_inr=dta.getTtax_amt_inr();
	   sac_desc=dta.getSac_descr();
	   Inv_seq_no=hlpl_seq_no;  ///
	   Fin_yr=fin_yr;
	   supp_nm=dta.getSupplier_nm();
	   Supp_stat_cd=dta.getSupp_stat_CD();
	   Contact_Suppl_PAN_NO=dta.getContact_Suppl_PAN_NO();
	   Contact_Suppl_gstin=dta.getContact_Suppl_gstin_no();
	   supplier_cd=dta.getSupplier_cd();
	   flag_radio=dta.getFlag_sac();
	    Vsac_cd=dta.getVvsac_cd();
	    sale_no=dta.getSale_no();
	    Vuam_no=dta.getVuam_no();
	    inv_no=seq_no;
}
Vector vcargo_amt=dta.getVCamt();
Vector vcrate=dta.getCrt();
Vector vcqty=dta.getVCqty();
Vector vitm=dta.getVitm();
Vector VCrate=dta.getVCrate();

Vector VCGST_Perc = dta.getVCGST_Perc();
Vector VSGST_Perc = dta.getVSGST_Perc();
Vector VIGST_Perc = dta.getVIGST_Perc();
Vector VCESS_Perc = dta.getVCESS_Perc();
Vector VIGST_Amt = dta.getVIGST_Amt();
Vector VCGST_Amt = dta.getVCGST_Amt();
Vector VSGST_Amt = dta.getVSGST_Amt();
Vector VCESS_Amt = dta.getVCESS_Amt();
Vector VSAC_CD = dta.getVSAC_CD();
Vector VHSN_CD = dta.getVHSN_CD();
String cust_abr=dta.getSVendor_abr(); //SB20180406

int no_of_trucks = serviceInv.getNo_of_trucks();//Hiren_20200511
System.out.println("no_of_trucks: "+no_of_trucks);
Vector Vtruck_id = serviceInv.getVTruck_id();
Vector Vtruck_reg_no = serviceInv.getVTruck_reg_no();
Vector Valloc_dt = serviceInv.getValloc_dt();
Vector InvGenflag =serviceInv.getInvGenflag();
Vector loading_dt =serviceInv.getVLoading_dt();
System.out.println("InvGenflag****"+InvGenflag);
%>
<body <%if(msg.length()>6) { %>onload="Do_close('<%=supplier_cd%>','<%=year%>','<%=month%>','<%=inv_type%>','<%=msg%>','<%=write_permission%>','<%=check_permission%>','<%=approve_permission%>','<%=print_permission%>','<%=delete_permission%>');"<%}%>>
<div class="tab-content">
<!-- /.Invoicing TAB START-->
<div class="tab-pane active" id="invoicing">
<form  method="post" action="../servlet/Frm_General_InvoiceV2">

<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">

<div class="box mb-0">
	<div class="box-header with-border">
<div id="col-new">
<%if(msg.length()>5) { %>
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
	<%String nm="";
	if(inv_type.equalsIgnoreCase("A")){ nm="Receipt/Advance Voucher";%>
	<%}else if(inv_type.equalsIgnoreCase("2")) { nm="HPPL-HLPL"; %>
	<%}else if(inv_type.equalsIgnoreCase("X")) { nm="Cost Recharge";%>
	<%}else if(inv_type.equalsIgnoreCase("Z")) { nm="New Invoice";} %>
	<div class="box-header with-border main-header" >
		<div class="form-group col-md-12 text-center">
	 	 	<label for="" >
	 	 		<%=supp_nm %> [<%=Fin_yr %>] Prepare - (<%=nm %>)
			</label>
		</div>
		<div class="box-tools pull-right">
			<button type="button"  class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove" name="close_window" value="Close Window" onclick="closeWindow();">
			Close  <i class="fa fa-times"></i>
			</button>
		</div>
	</div>
<%//if(inv_type.equalsIgnoreCase("X"))
{ %>	
	<div class="row">
		<div class="table-responsive col-lg-6">
			<table class="table table-striped" >
				<tr>
					<th colspan="6">Supplier Details</th>
				</tr>
				<tr>
					<td class="info" colspan="2" >Supplier Name
						<input type="hidden" name="Supp_Abr" value="<%=Supp_Abr %>"> 
					</td>
					<td title="Supplier Name" colspan="3">
				    	<input type="hidden" name="supp_state_cd" value="<%=Supp_state_cd%>" TITLE="State Code:">
				    	<input type="hidden" name="supp_cd" value="<%=supplier_cd%>"  TITLE="SupplierCode:">
				    	<input type="text" class="form-control" readonly="readonly" class="mkRdly" name="supp_nm" value="<%=supp_nm %>" size="40">
				    </td>
				    <td>	
				    	<button type="button" class="btn btn-block btn-primary" <%if(flag.equalsIgnoreCase("Modify")) { %> disabled="disabled" <%} %> name="select_supp" style="" value="Select Supplier" size="10" onclick="sel_supplier();">Select Supplier</button>	
				    </td>
				</tr>
				<tr>
					<td class="info" colspan="2">Supplier Address</td>
					<td colspan="4">
		    			<input type="text" class="form-control" title="Supplier address"  maxlength="299" name="SupAddress" value="<%=Supp_addr %>" size="60" readonly="readonly">
		     		</td>
				</tr>
				<tr>
					<td class="info" colspan="2">GSTIN No.</td>
					<td colspan="4">
		    			<input type="text" class="form-control" readonly="readonly"  name="ST_NO" value="<%=Contact_Suppl_gstin %>" size="20">	
		     		</td>
				</tr>
				<tr>
					<td class="info" colspan="2">PAN No.</td>
					<td colspan="4">
		    			<input type="text" class="form-control" readonly="readonly"  name="pan_no" value="<%=Contact_Suppl_PAN_NO %>" size="20">	
		     		</td>
				</tr>	
				<tr>
					<td class="info" colspan="2">City</td>
					<td colspan="4">
		    			<%=Supp_city %>	
		     		</td>
				</tr>
				<tr>
					<td class="info" colspan="2">State</td>
				 	<td colspan="4" >
				 		<select name="supp_state" disabled="disabled" class="form-control">
			    				<option value="0">--Select--</option>
			    				  <%for(int i=0;i<state_cd.size();i++)
							    {%> <option value="<%=state_nm.elementAt(i)%>" ><%=state_nm.elementAt(i)%></option>
							  <%}%>			
			    		</select>
			    		<script>
			    			document.forms[0].supp_state.value = '<%=Supp_state%>';
			    		</script>
				 </td>
				</tr>	
			</table>
		</div>
		<div class="table-responsive col-lg-6">
			<table class="table table-striped" >
				<tr>
					<th colspan="6">Transporter Details
						<select name="cust_type"  >
		    				 <option value="V" >Transporter(Internal)</option>	
		    				 <option value="B" >Transporter(External)</option>		
		    			</select>
		    			<script>
		    				document.forms[0].cust_type.value = '<%=cust_type1 %>';
		    			</script>
					</th>
				</tr>
				<tr>
					<td class="info" colspan="2">Transporter Name</td>
					<td colspan="3" > 
						<input type="hidden" name="CUST_CD11" value="<%=CUST_CD%>">
		    		    <input type="hidden" name="cust_abr" value="<%=cust_abr%>">
		    			<select class="form-control" name="trans_cd" onchange="fetchTransDtl(this.value);">
		    				<option value="0">--Select--</option>
		    				  <%for(int i=0;i<Vtrans_cd.size();i++){%>
		    				  	<option value="<%=Vtrans_cd.elementAt(i)%>" ><%=Vtrans_nm.elementAt(i)%></option>
						  	<%}%>			
			    		</select>
			    		<%if(!trans_cd.equals("")) {%>
			    		<script>
			    			document.forms[0].trans_cd.value = '<%=trans_cd%>';
			    		</script>
			    		<%} %>
					</td>
					<td>	
						<%if(flag.equalsIgnoreCase("Modify")) { %>
<!-- 							<button type="button" class="btn btn-block btn-primary" name="view" disabled="disabled"  value="Select" onclick="viewvendor();">Select</button> -->
						<%}else{ %>
<!-- 							<button type="button" class="btn btn-block btn-primary" name="view"  value="Select" onclick="viewvendor('V','Z');">Select</button> -->
						<%} %>  		
		     		</td>
				</tr>
				<tr>
					<td class="info" colspan="2">Transporter Address</td>
					<td colspan="4">
		    			<input type="text" class="form-control" title="buyer address" readonly="readonly" maxlength="299" name="address" value="<%=Vtrans_addr %>" size="60">
		     		</td>
				</tr>
				<tr>
					<td class="info" colspan="2">GSTIN No.</td>
					<td colspan="4">
		    			<input type="text" class="form-control" readonly="readonly" maxlength="299" name="cust_gstin_no1" value="<%=Vtrans_gst_no %>" size="20">	
		     		</td>
				</tr>	
				<tr>
					<td class="info" colspan="2">PAN No.</td>
					<td colspan="4">
		    			<input type="text" class="form-control" readonly="readonly" maxlength="299" name="cust_pan_no1" value="<%=Vtrans_pan %>" size="20">	
		     		</td>
				</tr>
				<tr>
					<td class="info" colspan="2">City</td>
					<td colspan="4">
		    			<input type="text" class="form-control" readonly="readonly" name="city" value="<%=Vtrans_city %>" size="20"  maxlength="49">
		     		</td>
				</tr>
				
				<tr>
					<td class="info" colspan="2">State</td>
					<td colspan="4">
					<%if(!flag.equalsIgnoreCase("Modify")) { %>
		    			<select class="form-control" name="state" disabled="disabled" onchange="change_st();" >
		    				<option value="0">--Select--</option>
		    				  <%for(int i=0;i<state_cd.size();i++)  {%> 
		    				  <option value="<%=state_cd.elementAt(i)%>" ><%=state_cd.elementAt(i)%>-<%=state_nm.elementAt(i)%></option>
						 	 <%}%>		
		    			</select>
		    			<%if(!Vtrans_state.equals("")) {%>
		    			<script>
		    				document.forms[0].state.value = '<%=Vtrans_state%>';
		    			</script>
		    			<%} %>
		    			<%}else{ %>
		    			<td class="info" colspan="2">State</td>
						<td colspan="4">
			    			<select class="form-control" name="state" onchange=""  >
			    				<option value="0">--Select--</option>
			    				  <%for(int i=0;i<state_cd.size();i++)
							    {%> <option value="<%=state_cd.elementAt(i)%>" ><%=state_nm.elementAt(i)%></option>
							  <%}%>		
			    			</select>
			    			<%if(!Vtrans_state.equals("")) {%>
			    			<script>
			    				document.forms[0].state.value = '<%=Vtrans_state%>';
			    			</script>
			    			<%} %>
			    		<%} %>
		     		</td>
				</tr>	
			</table>
		</div>
		
		<div class="table-responsive col-lg-12">
			<table class="table table-striped" >
				<tr>
					<th colspan="12">Invoice Details</th>
				</tr>
				<tr>
					<td class="info" colspan="2">Invoice No.</td>
					<td colspan="2"> 
					<%if(seq_no.equals("")) { %>
			    		<input type="text" class="form-control" name="inv_no" value="<%//=seq_no%><%=new_GST_INVOICE_SEQ_NO %>" readonly="readonly" class="mkRdly">
			    		<%} else { %>
			    		<input type="text" class="form-control" name="inv_no" value="<%=seq_no%><%//=new_GST_INVOICE_SEQ_NO %>" readonly="readonly" class="mkRdly">
			    		<%} %>
					</td>
					<td class="info" colspan="1">FY</td>
					<td colspan="1">
						<input type="text" class="form-control" name="Fin_yr_hid" size="8" value="<%=Fin_yr%>" readonly>
					</td>
					<td class="info" colspan="1">Invoice Date</td>
					<td colspan="1" width="15%">
						<div class='input-group date' id='datetimepicker1' >
							<input type='text' class="form-control"  name="inv_dt"  maxlength="10" size="10" value="<%=inv_dt%>" onchange="validateDate(this);"/>
							<span class="input-group-addon">
								<i class="fa fa-calendar"></i>
							</span> 
						</div>
<%-- 						<input type="text" name="inv_dt" value="<%=inv_dt%>" --%>
<!--  							 onblur="validateDate(this);" size="10" maxlength="10"  style="text-align:center" > -->
					</td>
				</tr>
				<tr>
					<td class="info" colspan="2">Order/Agreement No.</td>
					<td colspan="2">
						<input type="text" class="form-control" title="Please Enter Sale Aggreement No" onblur="checkAlphanumeric_here(this);" maxlength="19" name="sale_no" value="<%=sale_no %>" >
					</td>
					<td class="info" colspan="1">Gate Pass No.</td>
					<td colspan="1">
						<input type="text" class="form-control" title="Please Enter Gatepass No" onblur="checkAlphanumeric_here(this);" maxlength="19" name="gate_pass_no" value="<%=gate_no %>" >
					</td>
				</tr>
				
				<tr>
					<td class="info" colspan="2">Invoice Type</td>
					<td colspan="2">
						<%-- <input type="radio" 
						<%if(flag.equalsIgnoreCase("Modify")) { %>
						 	disabled="disabled" 
						 <%} %>  
						 name="chkrad" value="P" 
						 <%if(flag_radio.equalsIgnoreCase("P")) { %>
						  	checked="checked" <%} %>
						    onclick="sel_radio('P');">Product
						&nbsp;&nbsp;&nbsp; --%>
						
						<input type="radio" <%if(flag.equalsIgnoreCase("Modify")) { %>
						 disabled="disabled" <%} %>
						 name="chkrad" value="S" 
<%-- 						 <%if(flag_radio.equalsIgnoreCase("S")) { %> --%>
						  checked="checked" 
<%-- 						  <%} %>  --%>
						   onclick="sel_radio('S');">Service
					</td>
					<td  class="info" colspan="1">
						No. of Trucks
					</td>
					<td>	
						<select class="form-control" name="no_line_item" onchange="setLineItem(this.value);" 
						<%if(flag.equalsIgnoreCase("Modify")) { %> disabled="disabled" <%} %> >
		    				  <%for(int i=1 ; i <= no_of_trucks ; i++) {%> 
		    				<option value="<%=i%>" ><%=i %></option>
						  <%}%>		  				
		    			</select>
		    			<script>
		    				document.forms[0].no_line_item.value = '<%=no_line_item1%>';
		    			</script>
					</td>
					
					<td class="info" colspan="1">PDF-Format</td>
					<td colspan="1">
						<select name="pdf_format_type" class="form-control" >
		    				<option value="0">Default</option>
		    				  <%for(int i=1;i<3;i++) {%> 
		    				<option value="<%=i%>" >Format-<%=i %></option>
						  <%}%>		  				
		    			</select>
		    			<script>
		    				document.forms[0].pdf_format_type.value = '<%=pdf_format_type1%>';
		    			</script>
					</td>
				</tr>
			</table>
		</div>
		
		<div class="table-responsive col-lg-12">
			<table class="table table-striped" id = "invTab">
				<tr>
					<%-- <%if(flag_radio.equalsIgnoreCase("P")) { %>
						<td class="info" colspan="2"><span id="hsnnn_cd"> HSN Code</span></td> 
					<%}else{%> --%>
    					<td class="info" colspan="2"><span id="hsnnn_cd"> SAC Code </span></td>
<%--     				<%} %> --%>
    				<td class="info">Line Item</td>
    				<td class="info">UOM</td>
    				<td class="info">Quantity</td>
    				<td class="info"><% if(inv_type.equalsIgnoreCase("A")){ %>Lumsum Amt.<% } else { %>Rate<% } %></td>
    				<td class="info">Amount</td>
    				<td class="info">
    					<%	for(int i=0; i<tax_name.size(); i++) {%>
   					 	<%for(int j=0;j<14-tax_name.elementAt(i).toString().length();j++){ %>&nbsp;<%}%>
   							<font size="2px;"><b><%=tax_name.elementAt(i)%></b></font>
 							<input type="hidden" name="tx_nm" id="tx_nm_id" value="<%=tax_name.elementAt(i)%>">
   						<%} %>
   					</td>
    				<td class="info" colspan="4">Total Amount</td>
				</tr>
				<% if(flag.equalsIgnoreCase("Modify")) {} else { %>
    					<%for(int k=0; k<Vtruck_reg_no.size(); k++){ %>
    						<tr <%if(InvGenflag.elementAt(k).equals("Y")) {%>  title="Invoice already generated..!" <%} %>>
					    		<td colspan="2">
					     			<input type="hidden"  name="hsn_cd" value="<%//=Vsac_cd.elementAt(k)%>" title="Please Enter Code" size="8" title="Insert Code" maxlength="500"> 
					    		 	<%if(InvGenflag.elementAt(k).equals("Y")) {%>
					    		 		<img alt="Invoice already generated..!" src="../images/logo/Yes-logo.png" height="20px;" width="20px;">
					    		 		<input type="checkbox" name="line_item_chk" value="N" style="visibility: hidden;" width="0%" >
					    		 	<%}else{ %>
					    		 		<input type="checkbox" name="line_item_chk" value="N" onclick="EnabledEntry('<%=k%>');" >
					    		 	<%} %>
					    		 	<input type="hidden" name="chk_flg" value="N" >
					    		 	
					    		 	<select name="sac_hsn" onchange="SetSAC_HSN2('<%=k %>','<%=tax_name.size() %>')" >
					    				<option value="0" SELECTED>-Select-</option>
					    				  <%for(int i=0;i<VSupl_sac_cd.size();i++)
									    {%> <option value="<%=VSupl_sac_cd.elementAt(i)%>-<%=Vsac_hsn_perc.elementAt(i)%>-<%=VSupl_desc.elementAt(i)%>" ><%=VSupl_sac_cd.elementAt(i)%></option>
									  <%}%>		   				
					    			</select>
					    			<script>
					    				document.forms[0].sac_hsn.value = '0';
					    			</script>	
					    		 </td>
					    		 <td><input type="text" title="Please Enter Description"  name="description" 
					    		 	value="<%=Vtruck_reg_no.elementAt(k)%> &nbsp;&nbsp;<%=Valloc_dt.elementAt(k) %>" size="50"  title="Insert Particular Line Item" maxlength="100">
					    		 	<input type="hidden" size="50" title="Please Enter Rate" style="text-align: right; font-size: 10px; border-right-color: blue " name="sac_hsn_desc"  value="">
					    		 </td>
								 <td><input type="text" title="Please Enter UOM"  name="unit" value="Km.<%//=Vuam_no.elementAt(k)%>" size="8" title="Insert Name" maxlength="500"></td>
								 <td><input type="text" title="Please Enter Quantity" style="text-align: right;" name="qty" value="<%=vcqty.elementAt(k)%>" size="5" title="Insert Name"  onblur="checkNumber1(this,11,2);" ></td>			
								 <td><input type="text" title="Please Enter Rate" style="text-align: right;" name="rate"  value="<%=VCrate.elementAt(k)%>" size="10" title="Insert Name" maxlength="" onblur="checkNumber1(this,11,4);calculate_here(this,'<%=tax_name.size()%>','<%=k %>')"></td>
								 <td><input type="text" title="Amount (quantity * rate)" style="text-align: right;" class="mkRdly" name="amt" value="<%=vcargo_amt.elementAt(k)%>" size="10" title="Insert Name" readonly="readonly" maxlength=""></td>
					  			 <td title="Tax Size: <%=tax_name.size()%>" colspan="<%=tax_name.size()+(3-tax_name.size()) %>">
					 				<%if(tax_name.size()==1) {	%>				
											<input type="text"  name="igst" size="1" value="0" style="text-align: right;" onblur="calculate_amt1('<%=k %>',this,'<%=tax_name.size()%>');">					
											<input type="text" size="5" name="igst_amt" value="0" style="text-align:right" class="mkRdly" readOnly>
											<input type="hidden"  name="cgst" size="1" value="0">	
											<input type="hidden"  name="sgst" size="1" value="0">						
									<% } else {	%>
											<input type="hidden"  name="igst" size="1" value="0">	
											<input type="hidden" size="4" name="igst_amt" value="0">		
											<input type="text"  name="cgst" size="1" value="" style="text-align: right;" onblur="calculate_amt1('<%=k %>',this,'<%=tax_name.size()%>');">					
											<input type="text" size="5" name="cgst_amt" value="" style="text-align:right" class="mkRdly" readOnly>						
											<input type="text"  name="sgst" size="1" value="" style="text-align: right;" onblur="calculate_amt1('<%=k %>',this,'<%=tax_name.size()%>');">					
											<input type="text" size="5" name="sgst_amt" value="" style="text-align:right" class="mkRdly" readOnly>						
									
									<%	} %>
								 </td>
<!-- 								 <td align="center">  -->
									<input type="hidden" size="6" name="igst_tax_amt_hid" value="0">
									<input type="hidden" size="6" name="cgst_tax_amt_hid" value="0">
<!-- 								 </td> -->
					  			</tr>
					  			<input type="hidden" name="loading_dt" value='<%=loading_dt.elementAt(k) %>'>
					  			<input type="hidden" name="truck_id" value='<%=Vtruck_id.elementAt(k) %>'>
    					<%} %>
    					<%if(Vtruck_reg_no.size() == 0) {%>
    					<tr class="info">
							<th class="text-center"  colspan="9" style="color: red;">No Data Available for the selected Transporter !!!</th>
						</tr>
    					<%} %>
    			<%} %>
    			
    			<tr>
    				<td colspan="1" align="left">&nbsp;</td>
<!-- 				 	<td colspan="1" align="left"><input type="button" name="addnew" value="Add Line  Item"  title=" New Line Item" onclick="addnewdesc();" >		 -->
			    	</td>
					<td colspan="3" style="vertical-align: middle;padding-left:5px;">
						<div align="right">
							<font size="2px;"><b>Total Charges:</b></font>
						</div>
					</td>
					<td colspan="1">
						<div align="left">
							&nbsp;<input type="text" size="10" name="gross_amt" id="gross_amt" value="<%=gross_amt%>" style="text-align:right" class="mkRdly" readOnly>
						</div>
					</td>
					<td colspan="1" style="vertical-align: middle;padding-left: 5px;">
						<div align="right">
							<font size="2px;"><b>Total GST:</b></font>
						</div>
					</td>
					<td colspan="1">
						<div align="left">
							&nbsp;<input type="text" size="10" name="tax_amt"  value="<%=tax_amt_inr%>" style="text-align:right" class="mkRdly" >
						</div>
					</td>
					<td colspan="1" style="vertical-align: middle;padding-left: 5px;">
						<div align="right">
							<font size="2px;"><b>Total(INR):</b></font>
						</div>
					</td>
					<td colspan="1">
						<div align="left">
							&nbsp;<input type="text" title="Net Amount in INR(Gross amount + Total tax Amount)" size="10" name="net_inr" value="<%=net_amt_inr%>" style="text-align:right" class="mkRdly" >
						</div>
					</td>
    			</tr>	
			</table>
		</div>					
		
		<div class="box-footer">
			<div class="row">
			<div class="col-sm-12 text-right">
				<button type="button" class="btn btn-success"  name="sub" <%if(flag.equalsIgnoreCase("Modify")){ %>value="Modify" <%}else{ %>value="Submit"<%} %> onclick="subm();"> Submit </button>
			</div>
			</div>
		</div>			
	</div>
</div>
</div>
</div>

<input type="hidden" name="option" value="Submit_Other_Invoice_Z">

<input type="hidden" name="flag_radio_hid" value="<%=flag_radio%>">
<input type="hidden" name="flag" value="<%=flag%>">
<input type="hidden" name="cust_stcd" value="<%=Vtrans_state%>">
<input type="hidden" name="tax_sz" value="<%=tax_name.size()%>">
<input type="hidden" name="hlpl_seq_no1" value="<%=hlpl_seq_no%>">

<%-- <input type="text" name="New_GST_INVOICE_SEQ_NO" value="<%=new_GST_INVOICE_SEQ_NO%>"> --%>
<input type="hidden" name="INVOICE_SEQ_NO" value="<%=Inv_seq_no%>"> 
<input type="hidden" name="cust_seq_no1" value="<%=cust_seq_no%>"> 
<input type="hidden" name="bill_start_dt" value="<%=start_dt%>">
<input type="hidden" name="bill_end_dt" value="<%=end_dt%>">
<input type="hidden" name="year_hid" value="<%=year%>">
<input type="hidden" name="month_hid" value="<%=month%>">

<input type="hidden" name="inv_type_hid" value="<%=inv_type%>">
<%-- <input type="text" name="seq_no1" value="<%=seq_no%>"> --%>
<input type="hidden" name="user_cd_hid" value="<%=emp_cd%>">

<input type="hidden" name="norows" value="1">
<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="check_permission" value="<%=check_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
<input type="hidden" name="rowno" value="1">
<%-- <input type="text" name="fin_yr_kk" value="<%=fin_yr%>"> --%>
<input type="hidden" name="form_id" value="<%=form_cd %>">
<input type="hidden" name="form_nm" value="<%=form_name %>">
</form>
</div>
				
<script type="text/javascript">
function fetchTransDtl(trans_cd) {
	var inv_type = document.forms[0].inv_type_hid.value;
	var month = document.forms[0].month_hid.value;
	var year = document.forms[0].year_hid.value;
	var flag = document.forms[0].flag.value;
	var supp_cd  = document.forms[0].supp_cd.value;
	var fin_yr  = document.forms[0].Fin_yr_hid.value;
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	
	var url = "../general_inv/frm_other_invoices_Z.jsp?trans_cd="+trans_cd+"&year="+year+"&month="+month+"&fin_yr="+fin_yr+"&inv_type="+inv_type+"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+"&alw_upd="+approve_permission+"&alw_view="+check_permission;
	location.replace(url);	
}
function calculate_here(obj,sz,index){
	var flag=checkNumber1(obj,11,4); //alert(flag); 
 	// alert("Tax-Size:  "+sz+"****"+flag);
	if(flag) {
		var len=document.forms[0].qty.length;    //alert(len);
		var fin_amt=0;var qty=0;var tax_amt=0;
		var state=document.forms[0].state.value;
		var flag1 = false;
	 	var msg = "Following Fields To Be Filled Properly :\n";
		
	 	if(state=='') {
				alert("Please Select Customer First");
				document.forms[0].rate.value='';
		}else{
			if(len==undefined) {
				if(document.forms[0].description.value==''||document.forms[0].description.value==' '||document.forms[0].description.value==null)
				{
				msg += "Please Enter Description !\n";
		 		flag1 = true;
				}
				if(document.forms[0].hsn_cd.value==''||document.forms[0].hsn_cd.value==' '||document.forms[0].hsn_cd.value==null)
				{
				msg += "Please Enter HSN/SAC Code !\n";
		 		flag1 = true;
				}
			
				if(document.forms[0].rate.value==''||document.forms[0].rate.value==' '||document.forms[0].rate.value==null)
				{
				msg += "Please Enter Rate !\n";
		 		flag1 = true;
			}
			if(!flag1) {
				 qty=document.forms[0].qty.value;
				 if(qty==""){qty=1;}
				 var rate=document.forms[0].rate.value;
					var tot_amt=parseFloat(rate)* parseFloat(qty);
					document.forms[0].amt.value=round(parseFloat(tot_amt),0);
					 fin_amt=parseFloat(tot_amt);
				}else{
				 alert(msg);
				 document.forms[0].rate.value='';
				 document.forms[0].Qty.value='';
			 }//!flag1
		}else{
			for(var i=0;i<parseFloat(len);i++){
				if(document.forms[0].line_item_chk[i].checked){			
					if(document.forms[0].description[i].value==''||document.forms[0].description[i].value==' '||document.forms[0].description[i].value==null)
					{
						msg += "Please Enter Description !\n";
				 		flag1 = true;
					}
					if(document.forms[0].hsn_cd[i].value==''|| document.forms[0].hsn_cd[i].value==' '||document.forms[0].hsn_cd[i].value==null)
					{
						msg += "Please Enter HSN/SAC Code111 !\n";
				 		flag1 = true;
					}
					
					if(document.forms[0].rate[i].value==''||document.forms[0].rate[i].value==' '||document.forms[0].rate[i].value==null)
					{
						msg += "Please Enter Rate !\n";
				 		flag1 = true;
					}
					//alert("Line-Index: "+index);
						if(!flag1) {
						  qty=document.forms[0].qty[i].value;//alert("QTY: "+qty);
							if(qty==""){qty=1;}
							var rate=document.forms[0].rate[i].value;//alert("RATE: "+rate);
							var tot_amt=parseFloat(rate)* parseFloat(qty);//alert("TOT: "+tot_amt);
								 document.forms[0].amt[i].value=round(parseFloat(tot_amt),0);
							 	fin_amt+=parseFloat(tot_amt);
						}else{
							 alert(msg);
						 	document.forms[0].rate[i].value='';
						 	document.forms[0].Qty[i].value='';
					 	} 
				}
			}
		}//lenundefinedelse
	//	alert("CALculate : ");
		document.forms[0].gross_amt.value=round(parseFloat(fin_amt),0);
		var tax_18=0;
		tax_amt=0;
		var rowamt=0;
		var tot_tax_amt=0;
		document.forms[0].tax_amt.value=tax_amt;
		if(document.forms[0].gross_amt.value!='') {
		if(sz==1){
			var nrow=parseInt(index)+1;
			if(len==undefined) {
			 tax_18=document.forms[0].igst.value;//alert(tax_18);
			 rowamt=parseFloat(document.forms[0].amt.value);//alert(index+" :Row amt: "+rowamt);
			 var gross_amt=(parseFloat(rowamt)*parseFloat(tax_18))/100; //alert("gross.."+gross_amt);
			 document.forms[0].igst_amt.value=round((parseFloat(gross_amt)),0);	
			 document.forms[0].igst_tax_amt_hid.value=round(gross_amt,0);
			 tot_tax_amt+=parseFloat(gross_amt);  //alert("tax_amt: "+tot_tax_amt);
			 document.forms[0].tax_amt.value=tot_tax_amt; //alert("Final tax_amt: "+document.forms[0].tax_amt.value);
			 document.forms[0].net_inr.value=round((parseFloat(document.forms[0].tax_amt.value)+parseFloat(document.forms[0].gross_amt.value)),0);
			}
			else
			{
			// tax_18=document.forms[0].igst[index].value;alert(len+" :tax18: "+tax_18);
			// rowamt=parseFloat(document.forms[0].amt[index].value); alert(index+" :Row amt: "+rowamt);
			// var gross_amt=(parseFloat(rowamt)*parseFloat(tax_18))/100; //alert("gross.."+gross_amt);
			// document.forms[0].igst_amt[index].value=round((parseFloat(gross_amt)),0);	
			 for(var i=0;i<parseFloat(len);i++)
			 {
				 tax_18=document.forms[0].igst[i].value;//alert(len+" :tax18: "+tax_18);
				 rowamt=parseFloat(document.forms[0].amt[i].value); //alert(index+" :Row amt: "+rowamt);
				 var gross_amt=(parseFloat(rowamt)*parseFloat(tax_18))/100; 
				 document.forms[0].igst_amt[i].value=round((parseFloat(gross_amt)),0);	
				 document.forms[0].igst_tax_amt_hid[i].value=round(gross_amt,0); //alert(document.forms[0].igst_tax_amt_hid[i].value);
				 tot_tax_amt+=parseFloat(document.forms[0].igst_amt[i].value);  //alert("tax_amt: "+tot_tax_amt);
			 }
			 document.forms[0].tax_amt.value=tot_tax_amt; //alert("Final tax_amt: "+document.forms[0].tax_amt.value);
			 document.forms[0].net_inr.value=round((parseFloat(document.forms[0].tax_amt.value)+parseFloat(document.forms[0].gross_amt.value)),0);
			}
		//	document.forms[0].tax_amt.value=tax_amt;
			var gross_amt=(parseFloat(rowamt)*parseFloat(tax_18))/100; //alert("gross.."+gross_amt);
			//alert(document.forms[0].igst_tax_amt_hid.value);
		
		}else {
			var n_row=parseInt(index)+1;
			
				//	var tax_9=document.forms[0].cgst.value;alert(tax_9);
					var row_amt="0.0";
					if(len==undefined) 
						{
							 tax_9=document.forms[0].cgst.value;//alert(len+" :tax18: "+tax_18);
							 rowamt=parseFloat(document.forms[0].amt.value); //alert(index+" :Row amt: "+rowamt);
							 var gross_amt=(parseFloat(rowamt)*parseFloat(tax_9))/100; //alert("gross.."+gross_amt);
							 document.forms[0].cgst_amt.value=round((parseFloat(gross_amt)),0);	//alert("IGST.."+document.forms[0].igst_amt[i].value);
							 document.forms[0].sgst_amt.value=round((parseFloat(gross_amt)),0);	
							 document.forms[0].cgst_tax_amt_hid.value=round(gross_amt,0);
							//SB20190217 tot_tax_amt+=parseFloat(gross_amt*2); // alert("tax_amt: "+tot_tax_amt);
							  tot_tax_amt+=round((parseFloat(gross_amt)),0)+round((parseFloat(gross_amt)),0); // alert("tax_amt: "+tot_tax_amt);
							  document.forms[0].tax_amt.value=tot_tax_amt; //alert("Final tax_amt: "+document.forms[0].tax_amt.value);
			 				 document.forms[0].net_inr.value=round((parseFloat(document.forms[0].tax_amt.value)+parseFloat(document.forms[0].gross_amt.value)),0);
						}
						else
						{
							for(var i=0;i<parseFloat(len);i++)
						 	{
								if(document.forms[0].line_item_chk[i].checked){	
						 	
							//row_amt=parseFloat(document.forms[0].amt[index].value);
									 tax_9=document.forms[0].cgst[i].value;  //alert(len+" :tax18: "+tax_9);
									 rowamt=parseFloat(document.forms[0].amt[i].value); //alert(index+" :Row amt: "+rowamt);
									 var gross_amt=(parseFloat(rowamt)*parseFloat(tax_9))/100; //alert("gross.."+gross_amt);
									 document.forms[0].cgst_amt[i].value=round((parseFloat(gross_amt)),0);	//alert("IGST.."+document.forms[0].igst_amt[i].value);
									 document.forms[0].sgst_amt[i].value=round((parseFloat(gross_amt)),0);	
									 document.forms[0].cgst_tax_amt_hid[i].value=round(gross_amt,0);
									 //SB20190217 tot_tax_amt+=parseFloat(gross_amt*2);  //alert("tax_amt: "+tot_tax_amt);
									 tot_tax_amt+=round((parseFloat(gross_amt)),0)+round((parseFloat(gross_amt)),0); // alert("tax_amt: "+tot_tax_amt);
							 	}
						 	}	
							 document.forms[0].tax_amt.value=tot_tax_amt; //alert("Final tax_amt: "+document.forms[0].tax_amt.value);
			 				 document.forms[0].net_inr.value=round((parseFloat(document.forms[0].tax_amt.value)+parseFloat(document.forms[0].gross_amt.value)),0);
						}
						var gross_amt=(parseFloat(row_amt)*parseFloat(tax_9))/100;
					///	document.forms[0].cgst_tax_amt_hid.value=round(gross_amt,0);//alert(document.forms[0].cgst_tax_amt_hid.value);
					///	document.forms[0].cgst_amt.value=round((parseFloat(gross_amt)),0);
					///	document.forms[0].sgst_amt.value=round((parseFloat(gross_amt)),0);	alert("1. TAX: "+tax_amt);
			//	for(var i=0;i<sz;i++){
						tax_amt+=round(parseFloat(gross_amt),0)+round(parseFloat(gross_amt),0); //alert(i+" :TAX: "+tax_amt);
			//	}//for
			var tot_tax_amt="0.0";
			if(!(document.forms[0].tax_amt.value=="")){tot_tax_amt=parseFloat(document.forms[0].tax_amt.value);}else{document.forms[0].tax_amt.value=tot_tax_amt;}
			document.forms[0].tax_amt.value=round(parseFloat(tot_tax_amt)+(parseFloat(tax_amt)),0);
			document.forms[0].net_inr.value=round((parseFloat(document.forms[0].tax_amt.value)+parseFloat(document.forms[0].gross_amt.value)),0);
			}  //elseofsz==1
		document.forms[0].addnew.disabled=false;
			}//grossnot"""
		}//state
	}//flagcheckno
}//func
function SetSAC_HSN2(index, taxSize)
{           
	var len=document.forms[0].qty.length; 
	if(len==undefined)
	{
	var LineCodeValue=document.forms[0].sac_hsn.value;
	var gst_per=LineCodeValue.split("-");
	document.forms[0].hsn_cd.value=gst_per[0];
		if(taxSize==1)
			{
				document.forms[0].igst.value=gst_per[1];  //alert(document.forms[0].sac_hsn.value);
			}
			else
			{
				document.forms[0].cgst.value=gst_per[1]/2;  //alert(document.forms[0].cgst.value);
				document.forms[0].sgst.value=gst_per[1]/2;
			}
			document.forms[0].sac_hsn_desc.value=gst_per[2];
			document.forms[0].description.focus();
	}
	else
	{    //alert(index); alert(taxSize);
	var LineCodeValue=document.forms[0].sac_hsn[index].value;
	//document.forms[0].hsn_cd[index].value=document.forms[0].sac_hsn[index].value; 
	var gst_per=LineCodeValue.split("-");   //alert(gst_per[0]);
	document.forms[0].hsn_cd[index].value=gst_per[0];
		if(taxSize==1)
			{
				document.forms[0].igst[index].value=gst_per[1];  //alert(document.forms[0].sac_hsn[index].value);
			}
			else
			{
				document.forms[0].cgst[index].value=gst_per[1]/2;  //alert(document.forms[0].cgst.value);
				document.forms[0].sgst[index].value=gst_per[1]/2;				
			}
			document.forms[0].sac_hsn_desc[index].value=gst_per[2];
			document.forms[0].description[index].focus();
	}
	alert("GST "+gst_per[1]+"%\n["+gst_per[0]+"] "+gst_per[2]);
}
function subm()
{
// 	alert('tosubmit');
 	var flag = false;
 	var msg = "Following Fields To Be Filled Properly :\n";
 	
 	var len = document.forms[0].line_item_chk.length;
 	if(len == 'undefined' || len == undefined){
	 	if(document.forms[0].line_item_chk.checked){
	 		if(document.forms[0].flag_radio_hid.value==''||document.forms[0].flag_radio_hid.value==' '||document.forms[0].flag_radio_hid.value==null)
	 		{
	 			msg += "Select SAC/HSN !!!\n";
	 	 		flag = true;
	 		}
	 		if(document.forms[0].inv_dt.value==''||document.forms[0].inv_dt.value==' '||document.forms[0].inv_dt.value==null)
	 		{
	 			msg += "Invoice Date field cannot be blank !\n";
	 	 		flag = true;
	 		}
	 	 	if(document.forms[0].trans_cd.value=='' ||document.forms[0].trans_cd.value==' '||document.forms[0].trans_cd.value==null)
	 		{
	 			msg += "Transporter Name field cannot be blank !\n";
	 	 		flag = true;
	 	 	}
	 	 	if(document.forms[0].address.value=='' ||document.forms[0].address.value==' '||document.forms[0].address.value==null)
	 		{
	 			msg += "Transporter Address field cannot be blank !\n";
	 	 		flag = true;
	 	 	}
	 	 	if(document.forms[0].city.value=='' ||document.forms[0].city.value==' '||document.forms[0].city.value==null)
	 		{
	 			msg += "City field cannot be blank !\n";
	 	 		flag = true;
	 	 	}
	 	 	if(document.forms[0].state.value=='0' ||document.forms[0].state.value==' '||document.forms[0].state.value==null)
	 		{
	 			msg += "State field cannot be blank !\n";
	 	 		flag = true;
	 	 	}
	 	 	if(document.forms[0].pan_no.value==''||document.forms[0].pan_no.value==' '||document.forms[0].pan_no.value=='0')
	 		{
	 			msg += "Supplier PAN field cannot be blank !\n";
	 	 		flag = true;
	 	 	}
	 	 	if(document.forms[0].ST_NO.value==''||document.forms[0].ST_NO.value==' '||document.forms[0].ST_NO.value=='0')
	 		{
	 			msg += "Supplier GSTIN field cannot be blank !\n";
	 	 		flag = true;
	 	 	}
	 	 	if(document.forms[0].net_inr.value==''||document.forms[0].net_inr.value==' '||document.forms[0].net_inr.value=='0')
	 		{
	 		if(document.forms[0].hsn_cd.value=='999999')
	 			{
	 				msg = "Total Amount in INR field is ZERO !\n\nThis Invoice will be DELETED from Record !!!!!!";
	 				alert(msg);
	 	 			flag = false;
	 			}
	 		else
	 			{
	 				msg += "Total Amount in INR field cannot be blank !\nPlease Enter Proper Values For Rate, Quantity";
	 	 			flag = true;
	 	 		}
	 	 	}		
	 	}else{
	 		msg+='Please select atleast one Line Item !!';
	 		flag = true;
	 	}
 	}else{
 		var chkFlg=false;
 		for(var i = 0 ; i < len ; i++){
	 		if(document.forms[0].line_item_chk[i].checked){
	 			chkFlg=true;	
		 		if(document.forms[0].flag_radio_hid.value==''||document.forms[0].flag_radio_hid.value==' '||document.forms[0].flag_radio_hid.value==null)
		 		{
		 			msg += "Select SAC/HSN !!!\n";
		 	 		flag = true;
		 		}
		 		if(document.forms[0].inv_dt.value==''||document.forms[0].inv_dt.value==' '||document.forms[0].inv_dt.value==null)
		 		{
		 			msg += "Invoice Date field cannot be blank !\n";
		 	 		flag = true;
		 		}
		 	 	if(document.forms[0].trans_cd.value=='' ||document.forms[0].trans_cd.value==' '||document.forms[0].trans_cd.value==null)
		 		{
		 			msg += "Transporter Name field cannot be blank !\n";
		 	 		flag = true;
		 	 	}
		 	/*SB 	if(document.forms[0].address.value=='' ||document.forms[0].address.value==' '||document.forms[0].address.value==null)
		 		{
		 			msg += "Transporter Address field cannot be blank !\n";
		 	 		flag = true;
		 	 	}
		 	 	if(document.forms[0].city.value=='' ||document.forms[0].city.value==' '||document.forms[0].city.value==null)
		 		{
		 			msg += "City field cannot be blank !\n";
		 	 		flag = true;
		 	 	}*/
		 	 	if(document.forms[0].state.value=='0' ||document.forms[0].state.value==' '||document.forms[0].state.value==null)
		 		{
		 			msg += "State field cannot be blank !\n";
		 	 		flag = true;
		 	 	}
		 	 	if(document.forms[0].pan_no.value==''||document.forms[0].pan_no.value==' '||document.forms[0].pan_no.value=='0')
		 		{
		 			msg += "Supplier PAN field cannot be blank !\n";
		 	 		flag = true;
		 	 	}
		 	 	if(document.forms[0].ST_NO.value==''||document.forms[0].ST_NO.value==' '||document.forms[0].ST_NO.value=='0')
		 		{
		 			msg += "Supplier GSTIN field cannot be blank !\n";
		 	 		flag = true;
		 	 	}
		 	 	if(document.forms[0].net_inr.value==''||document.forms[0].net_inr.value==' '||document.forms[0].net_inr.value=='0')
		 		{
		 		if(document.forms[0].hsn_cd[i].value=='999999')
		 			{
		 				msg = "Total Amount in INR field is ZERO !\n\nThis Invoice will be DELETED from Record !!!!!!";
		 				alert(msg);
		 	 			flag = false;
		 			}
		 		else
		 			{
		 				msg += "Total Amount in INR field cannot be blank !\nPlease Enter Proper Values For Rate, Quantity";
		 	 			flag = true;
		 	 		}
		 	 	}		
		 	}
 		}
 		if(chkFlg==false){
 			msg+='Please select atleast one Line Item !!';
	 		flag = true;
 		}
 	}
 	
 
 	
 	if(flag)
 	{
 		alert(msg);
 	}
 	else
 	{
	 	if(document.forms[0].sub.value=='Modify')
	 	{
	 			var len=document.forms[0].qty.length; 
	 			if(len==undefined) 
	 				document.forms[0].rate.focus();
	 			else
	 				document.forms[0].rate[0].focus();
	 		var a = confirm("Do You Want To Modify Data ?");
	 	}
	 	else
	 	{
 			var a = confirm("Do You Want To Submit Data ?");
 		}
 		if(a)
 		{

 			document.getElementById("loading").style.visibility = "visible";
    		document.getElementById("loading-image").style.visibility = "visible";
 			document.forms[0].sub.disabled=true;
 			document.forms[0].submit();
 		}
 	}
}
function setLineItem(rowCnt){
	
	var inv_type = document.forms[0].inv_type_hid.value;
	var month = document.forms[0].month_hid.value;
	var year = document.forms[0].year_hid.value;
	var flag = document.forms[0].flag.value;
	var supp_cd  = document.forms[0].supp_cd.value;
	var fin_yr  = document.forms[0].Fin_yr_hid.value;
	var trans_cd  = document.forms[0].trans_cd.value;
	var inv_dt = document.forms[0].inv_dt.value;
	var sale_no = document.forms[0].sale_no.value;
	var gate_pass_no =document.forms[0].gate_pass_no.value;
	var no_line_item = document.forms[0].no_line_item.value;
	var pdf_format_type = document.forms[0].pdf_format_type.value;
		
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var check_permission = document.forms[0].check_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	
	var url = "../general_inv/frm_other_invoices_Z.jsp?trans_cd="+trans_cd+"&year="+year+"&month="+month+"&fin_yr="+fin_yr+"&inv_type="+inv_type+
	"&trans_cd="+trans_cd+"&inv_dt="+inv_dt+"&sale_no="+sale_no+"&gate_pass_no="+gate_pass_no+"&no_line_item="+no_line_item+"&pdf_format_type="+pdf_format_type+"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+"&alw_upd="+approve_permission+"&alw_view="+check_permission;
	location.replace(url);		
}
function calculate_here(obj,sz,index){
	var flag=checkNumber1(obj,11,4); //alert(flag); 
 	// alert("Tax-Size:  "+sz+"****"+flag);
	if(flag) {
		var len=document.forms[0].qty.length;    //alert(len);
		var fin_amt=0;var qty=0;var tax_amt=0;
		var state=document.forms[0].state.value;
		var flag1 = false;
	 	var msg = "Following Fields To Be Filled Properly :\n";
		
	 	if(state=='') {
				alert("Please Select Customer First");
				document.forms[0].rate.value='';
		}else{
			if(len==undefined) {
				if(document.forms[0].description.value==''||document.forms[0].description.value==' '||document.forms[0].description.value==null)
				{
				msg += "Please Enter Description !\n";
		 		flag1 = true;
				}
				if(document.forms[0].hsn_cd.value==''||document.forms[0].hsn_cd.value==' '||document.forms[0].hsn_cd.value==null)
				{
				msg += "Please Enter HSN/SAC Code !\n";
		 		flag1 = true;
				}
			
				if(document.forms[0].rate.value==''||document.forms[0].rate.value==' '||document.forms[0].rate.value==null)
				{
				msg += "Please Enter Rate !\n";
		 		flag1 = true;
			}
			if(!flag1) {
				 qty=document.forms[0].qty.value;
				 if(qty==""){qty=1;}
				 var rate=document.forms[0].rate.value;
					var tot_amt=parseFloat(rate)* parseFloat(qty);
					document.forms[0].amt.value=round(parseFloat(tot_amt),0);
					 fin_amt=parseFloat(tot_amt);
				}else{
				 alert(msg);
				 document.forms[0].rate.value='';
				 document.forms[0].Qty.value='';
			 }//!flag1
		}else{
			for(var i=0;i<parseFloat(len);i++){
				if(document.forms[0].line_item_chk[i].checked){			
					if(document.forms[0].description[i].value==''||document.forms[0].description[i].value==' '||document.forms[0].description[i].value==null)
					{
						msg += "Please Enter Description !\n";
				 		flag1 = true;
					}
					if(document.forms[0].hsn_cd[i].value==''|| document.forms[0].hsn_cd[i].value==' '||document.forms[0].hsn_cd[i].value==null)
					{
						msg += "Please Enter HSN/SAC Code111 !\n";
				 		flag1 = true;
					}
					
					if(document.forms[0].rate[i].value==''||document.forms[0].rate[i].value==' '||document.forms[0].rate[i].value==null)
					{
						msg += "Please Enter Rate !\n";
				 		flag1 = true;
					}
					//alert("Line-Index: "+index);
						if(!flag1) {
						  qty=document.forms[0].qty[i].value;//alert("QTY: "+qty);
							if(qty==""){qty=1;}
							var rate=document.forms[0].rate[i].value;//alert("RATE: "+rate);
							var tot_amt=parseFloat(rate)* parseFloat(qty);//alert("TOT: "+tot_amt);
								 document.forms[0].amt[i].value=round(parseFloat(tot_amt),0);
							 	fin_amt+=parseFloat(tot_amt);
						}else{
							 alert(msg);
						 	document.forms[0].rate[i].value='';
						 	document.forms[0].Qty[i].value='';
					 	} 
				}
			}
		}//lenundefinedelse
	//	alert("CALculate : ");
		document.forms[0].gross_amt.value=round(parseFloat(fin_amt),0);
		var tax_18=0;
		tax_amt=0;
		var rowamt=0;
		var tot_tax_amt=0;
		document.forms[0].tax_amt.value=tax_amt;
		if(document.forms[0].gross_amt.value!='') {
		if(sz==1){
			var nrow=parseInt(index)+1;
			if(len==undefined) {
			 tax_18=document.forms[0].igst.value;//alert(tax_18);
			 rowamt=parseFloat(document.forms[0].amt.value);//alert(index+" :Row amt: "+rowamt);
			 var gross_amt=(parseFloat(rowamt)*parseFloat(tax_18))/100; //alert("gross.."+gross_amt);
			 document.forms[0].igst_amt.value=round((parseFloat(gross_amt)),0);	
			 document.forms[0].igst_tax_amt_hid.value=round(gross_amt,0);
			 tot_tax_amt+=parseFloat(gross_amt);  //alert("tax_amt: "+tot_tax_amt);
			 document.forms[0].tax_amt.value=tot_tax_amt; //alert("Final tax_amt: "+document.forms[0].tax_amt.value);
			 document.forms[0].net_inr.value=round((parseFloat(document.forms[0].tax_amt.value)+parseFloat(document.forms[0].gross_amt.value)),0);
			}
			else
			{
			// tax_18=document.forms[0].igst[index].value;alert(len+" :tax18: "+tax_18);
			// rowamt=parseFloat(document.forms[0].amt[index].value); alert(index+" :Row amt: "+rowamt);
			// var gross_amt=(parseFloat(rowamt)*parseFloat(tax_18))/100; //alert("gross.."+gross_amt);
			// document.forms[0].igst_amt[index].value=round((parseFloat(gross_amt)),0);	
			 for(var i=0;i<parseFloat(len);i++)
			 {
				 tax_18=document.forms[0].igst[i].value;//alert(len+" :tax18: "+tax_18);
				 rowamt=parseFloat(document.forms[0].amt[i].value); //alert(index+" :Row amt: "+rowamt);
				 var gross_amt=(parseFloat(rowamt)*parseFloat(tax_18))/100; 
				 document.forms[0].igst_amt[i].value=round((parseFloat(gross_amt)),0);	
				 document.forms[0].igst_tax_amt_hid[i].value=round(gross_amt,0); //alert(document.forms[0].igst_tax_amt_hid[i].value);
				 tot_tax_amt+=parseFloat(document.forms[0].igst_amt[i].value);  //alert("tax_amt: "+tot_tax_amt);
			 }
			 document.forms[0].tax_amt.value=tot_tax_amt; //alert("Final tax_amt: "+document.forms[0].tax_amt.value);
			 document.forms[0].net_inr.value=round((parseFloat(document.forms[0].tax_amt.value)+parseFloat(document.forms[0].gross_amt.value)),0);
			}
		//	document.forms[0].tax_amt.value=tax_amt;
			var gross_amt=(parseFloat(rowamt)*parseFloat(tax_18))/100; //alert("gross.."+gross_amt);
			//alert(document.forms[0].igst_tax_amt_hid.value);
		
		}else {
			var n_row=parseInt(index)+1;
			
				//	var tax_9=document.forms[0].cgst.value;//alert(tax_9);
					var row_amt="0.0";
					if(len==undefined) 
						{
							 tax_9=document.forms[0].cgst.value;//alert(len+" :tax18: "+tax_18);
							 rowamt=parseFloat(document.forms[0].amt.value); //alert(index+" :Row amt: "+rowamt);
							 var gross_amt=(parseFloat(rowamt)*parseFloat(tax_9))/100; alert("gross.."+gross_amt);
							 document.forms[0].cgst_amt.value=round((parseFloat(gross_amt)),0);	//alert("IGST.."+document.forms[0].igst_amt[i].value);
							 document.forms[0].sgst_amt.value=round((parseFloat(gross_amt)),0);	
							 document.forms[0].cgst_tax_amt_hid.value=round(gross_amt,0);
							//SB20190217 tot_tax_amt+=parseFloat(gross_amt*2); // alert("tax_amt: "+tot_tax_amt);
							  tot_tax_amt+=round((parseFloat(gross_amt)),0)+round((parseFloat(gross_amt)),0); // alert("tax_amt: "+tot_tax_amt);
							  document.forms[0].tax_amt.value=tot_tax_amt; //alert("Final tax_amt: "+document.forms[0].tax_amt.value);
			 				 document.forms[0].net_inr.value=round((parseFloat(document.forms[0].tax_amt.value)+parseFloat(document.forms[0].gross_amt.value)),0);
						}
						else
						{
							for(var i=0;i<parseFloat(len);i++)
						 	{
								if(document.forms[0].line_item_chk[i].checked){	
						 	
							//row_amt=parseFloat(document.forms[0].amt[index].value);
									 tax_9=document.forms[0].cgst[i].value;  //alert(len+" :tax18: "+tax_9);
									 rowamt=parseFloat(document.forms[0].amt[i].value); //alert(index+" :Row amt: "+rowamt);
									 var gross_amt=(parseFloat(rowamt)*parseFloat(tax_9))/100; //alert("gross.."+gross_amt);
									 document.forms[0].cgst_amt[i].value=round((parseFloat(gross_amt)),0);	//alert("IGST.."+document.forms[0].igst_amt[i].value);
									 document.forms[0].sgst_amt[i].value=round((parseFloat(gross_amt)),0);	
									 document.forms[0].cgst_tax_amt_hid[i].value=round(gross_amt,0);
									 //SB20190217 tot_tax_amt+=parseFloat(gross_amt*2);  //alert("tax_amt: "+tot_tax_amt);
									 tot_tax_amt+=round((parseFloat(gross_amt)),0)+round((parseFloat(gross_amt)),0); // alert("tax_amt: "+tot_tax_amt);
							 	}
						 	}	
							 document.forms[0].tax_amt.value=tot_tax_amt; //alert("Final tax_amt: "+document.forms[0].tax_amt.value);
			 				 document.forms[0].net_inr.value=round((parseFloat(document.forms[0].tax_amt.value)+parseFloat(document.forms[0].gross_amt.value)),0);
						}
						var gross_amt=(parseFloat(row_amt)*parseFloat(tax_9))/100;
					///	document.forms[0].cgst_tax_amt_hid.value=round(gross_amt,0);//alert(document.forms[0].cgst_tax_amt_hid.value);
					///	document.forms[0].cgst_amt.value=round((parseFloat(gross_amt)),0);
					///	document.forms[0].sgst_amt.value=round((parseFloat(gross_amt)),0);	alert("1. TAX: "+tax_amt);
			//	for(var i=0;i<sz;i++){
						tax_amt+=round(parseFloat(gross_amt),0)+round(parseFloat(gross_amt),0); //alert(i+" :TAX: "+tax_amt);
			//	}//for
			var tot_tax_amt="0.0";
			if(!(document.forms[0].tax_amt.value=="")){tot_tax_amt=parseFloat(document.forms[0].tax_amt.value);}else{document.forms[0].tax_amt.value=tot_tax_amt;}
			document.forms[0].tax_amt.value=round(parseFloat(tot_tax_amt)+(parseFloat(tax_amt)),0);
			document.forms[0].net_inr.value=round((parseFloat(document.forms[0].tax_amt.value)+parseFloat(document.forms[0].gross_amt.value)),0);
			}  //elseofsz==1
		document.forms[0].addnew.disabled=false;
			}//grossnot"""
		}//state
	}//flagcheckno
}//func
function EnabledEntry(i)
{//alert(i);
	var size = '2';//document.forms[0].plant_size.value; 
	//alert(document.forms[0].line_item_chk.length);
	if(document.forms[0].line_item_chk.length==undefined)
		{
		document.forms[0].line_item_chk.value= 'Y';
 		document.forms[0].chk_flg.value= 'Y';
		}
	else
		{
	var line_item = document.forms[0].line_item_chk[i].value; //alert(line_item);
 	if(document.forms[0].line_item_chk[i].checked)
 	{
 		
 		document.forms[0].line_item_chk[i].value= 'Y';
 		document.forms[0].chk_flg[i].value= 'Y';
 		
 		var flag = false;
      	
      	if(flag)
      	{
        	alert("Please First Fill up above Plant Details")
        	me.checked = false;
        	return false;
      	}
      	if(parseFloat(size)==1) {
      		document.forms[0].sac_hsn.readOnly = false;
	      
      	} else {
	       	document.forms[0].hsn_cd[i].readOnly = false; 
	       	document.forms[0].description[i].readOnly = false; 
	       	document.forms[0].unit[i].readOnly = false; 
	       	document.forms[0].qty[i].readOnly = false; 
	       	document.forms[0].rate[i].readOnly = false; 
	       document.forms[0].amt[i].readOnly = false; 
      	}
 	}
 	else
 	{
       	var flg = true;
       	document.forms[0].line_item_chk[i].value= 'N';
       	document.forms[0].chk_flg[i].value= 'N';
       	if(flg)
       	{
       		if(parseFloat(size)==1) {
       		//	document.forms[0].seq.readOnly = true;
 		      
       		} else {
		   	document.forms[0].hsn_cd[i].readOnly = true; 
	       	document.forms[0].description[i].readOnly = true; 
	       	document.forms[0].unit[i].readOnly = true; 
	       	document.forms[0].qty[i].readOnly = true; 
	       	document.forms[0].rate[i].readOnly = true; 
	       document.forms[0].amt[i].readOnly = true; 		       
       		}
		} 
		else
		{
			alert("Please First Remove Checks From All Other Descending Check Boxes !!!")
        	me.checked = true;
        	return true;
		}
 	}
		}
}
function closeWindow()
{
	window.close();
}
$(function () {
	$('#datetimepicker1').datepicker({
	changeMonth: true,
	changeYear: true,
	format: "dd/mm/yyyy",
	language: "en",
	autoclose: true,
	todayHighlight: true,
	orientation: "bottom auto",	
	});
	});
</script>    		 
	
	
 
	 
	<%-- <tr class="title1"><td colspan="10" align="center">
	<%if(write_permission.equalsIgnoreCase("N")) { %>
		<input type="button" disabled="disabled" name="sub" <%if(flag.equalsIgnoreCase("Modify")){ %>value="Modify" <%}else{ %>value="Submit1"<%} %> onclick="subm();">
	<%}else{ %>
		<input type="button"  name="sub" <%if(flag.equalsIgnoreCase("Modify")){ %>value="Modify" <%}else{ %>value="Submit"<%} %> onclick="subm();">
	<%} %>
	</td></tr> --%>
	

<%} %>


<div id="loading" style="visibility: hidden;">
  <img id="loading-image" style="visibility: hidden;" src="../images/indicator.gif" alt="Loading..." width="30" height="30" />
</div>
</html>