<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<%@ page import="java.io.File"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>DLNG | Transporter Contact Master</title>

<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/tlu.css">
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script language="JavaScript" src="../js/util1.js"></script>
</head>
<script type="text/javascript">
function echeck(str) {

	var at="@"
	var dot="."
	var lat=str.indexOf(at)
	var lstr=str.length
	var ldot=str.indexOf(dot)
	if (str.indexOf(at)==-1){
	   alert("Invalid E-mail ID")
	   return false
	}

	if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
	   alert("Invalid E-mail ID")
	   return false
	}

	if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
	    alert("Invalid E-mail ID")
	    return false
	}

	 if (str.indexOf(at,(lat+1))!=-1){
	    alert("Invalid E-mail ID")
	    return false
	 }

	 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
	    alert("Invalid E-mail ID")
	    return false
	 }

	 if (str.indexOf(dot,(lat+2))==-1){
	    alert("Invalid E-mail ID")
	    return false
	 }
	
	 if (str.indexOf(" ")!=-1){
	    alert("Invalid E-mail ID")
	    return false
	 }

		 return true					
}
function CheckEmailAddress(obj)
{
 if(trim(obj.value)!=""){
  if(echeck(obj.value)){
  }else{
     obj.value="";
     obj.focus();
  }
 } 
}
function checkForEffectiveDate()
{
   	var flag = false;
   	var effective_dt = document.forms[0].effective_dt;
   	
   	if(effective_dt!=null && effective_dt.length!=null && effective_dt.length!=undefined)
   	{
   		for(var j=0;j<effective_dt.length;j++)
   		{
        	if(trim(effective_dt[j].value)=="")
        	{
          		flag = true;
        	}
     	}
   	}
   	else if(effective_dt!=null && (effective_dt.length==null || effective_dt.length==undefined))
   	{
        if(trim(effective_dt.value)=="")
        {
        	flag = true;
        }
   	}
	return flag; 
}
function getInternetExplorerVersion()
//Returns the version of Internet Explorer or a -1
//(indicating the use of another browser).
{
var rv = -1; // Return value assumes failure.
if (navigator.appName == 'Microsoft Internet Explorer')
{
 var ua = navigator.userAgent;
 var re  = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
 if (re.exec(ua) != null)
   rv = parseFloat( RegExp.$1 );
}
else if (navigator.appName == 'Netscape')
{
  var ua = navigator.userAgent;
  var re  = new RegExp("Trident/.*rv:([0-9]{1,}[\.0-9]{0,})");
  if (re.exec(ua) != null)
  rv = parseFloat( RegExp.$1 );
}
return rv;
}
var global_count =0;
function addRow(todayDate)
{
var flag_dlng='Y';
var ver = getInternetExplorerVersion();

    if ( ver <= 8.0 && ver > -1) 
    {
    	alert('Not Supported : Very Old Browser')
    }
    else
    {  
	    indexVal = parseInt(document.forms[0].index.value);	
// 	    alert(indexVal)
	    effDt=String(document.forms[0].effDt.value);
	    ret = checkForEffectiveDate();
	    if(ret)
	    {
	      alert("Please First Insert Effective Date of above Contacts...");
	      return false;
	    }
	    var tableObj = document.getElementById("tableid").getElementsByTagName("tbody")[0];
        var text1="<tr class='title2'>";
         	text1+="<td align='center'>";
         	text1+="<input name='l1' type='text' size='5' value='Seq#' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;' >";
         	text1+="</td>";
         	text1+="<td align='center'>";
         	text1+="<input name='l1' type='text' size='20' value='Person Name' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;' >";
         	text1+="</td>";
         	text1+="<td align='center'>";
         	text1+="<input name='l1' type='text' size='15' value='Designation' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;'>";
         	text1+="</td>";
         	text1+="<td align='center'>";
         	text1+="<input name='l1' type='text' size='10' value='Phone' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;'>";
         	text1+="</td>";
         	text1+="<td align='center'>";
         	text1+="<input name='l1' type='text' size='10' value='Mobile' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;'>";
         	text1+="</td>";
         	text1+="<td align='center'>";
         	text1+="<input name='l1' type='text' size='10' value='Fax-1' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;'>";
         	text1+="</td>";
         	text1+="<td align='center'>";
         	text1+="<input name='l1' type='text' size='10' value='Fax-2' style='background-color:#E6E6FA;  color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;'>";
         	text1+="</td>";
         	text1+="<td align='center'>";
         	text1+="<input name='l1' type='text' size='5' value='NOM' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;' >";
         	text1+="</td>";
         	text1+="<td align='center'>";
         	text1+="<input name='l1' type='text' size='5' value='INV' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;' >";
         	text1+="</td>";
         	text1+="<td align='center'>";
         	text1+="<input name='l1' type='text' size='5' value='FM' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;' >";
         	text1+="</td>";
         	text1+="<td align='center'>";
         	text1+="<input name='l1' type='text' size='5' value='PM' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;' >";
         	text1+="</td>";
         	text1+="<td align='center'>";
         	text1+="<input name='l1' type='text' size='5' value='JT' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;'>";
         	text1+="</td>";
         	text1+="</tr>";
         	
         var text2="<tr class='content1'>";
         	text2+="<td align='center'>"; 
         	text2+="<input type='text' class = 'text-center' name='seq' value='"+(indexVal+1)+"' size='4' maxlength='2'  readOnly class='mkRdly'>"; 
         	text2+="</td>";   
         	text2+="<td align='center'>"; 
         	text2+="<input type='text' name='contact_nm' value='' size='20' maxlength='50' >"; 
         	text2+="</td>";  
         	text2+="<td align='center'>"; 
         	text2+="<input type='text' name='contact_desg' value='' size='9' maxlength='40'>"; 
         	text2+="</td>";  
         	text2+="<td align='center'>"; 
         	text2+="<input type='text' name='contact_phone' value='' size='9' maxlength='20' onblur='validatePhoneFax(this);'>"; 
         	text2+="</td>";  
         	text2+="<td align='center'>"; 
         	text2+="<input type='text' name='contact_mobile' value='' size='9' maxlength='20' onblur='validatePhoneFax(this);'>"; 
         	text2+="</td>"; 
         	
        	text2+="<td align='center'>"; 
         	text2+="<input type='text' name='contact_fax1' value='' size='9' maxlength='20' onblur='validatePhoneFax(this);'>"; 
         	text2+="</td>";  
         	text2+="<td align='center'>"; 
         	text2+="<input type='text' name='contact_fax2' value='' size='9' maxlength='20' onblur='validatePhoneFax(this);'>"; 
         	text2+="</td>"; 
         	
         	text2+="<td align='center'>"; 
         	text2+="<input type='checkbox' name='NOM' value='N'>";
         	text2+="<input type='hidden' name='NOMT' value='N' >"; 
         	text2+="</td>";  
         	text2+="<td align='center'>"; 
         	text2+="<input type='checkbox' name='INV' value='N'>"; 
         	text2+="<input type='hidden' name='INVT' value='N'>"; 
         	text2+="</td>";  
         	text2+="<td align='center'>"; 
         	text2+="<input type='checkbox' name='FM' value='N'>"; 
         	text2+="<input type='hidden' name='FMT' value='N'>"; 
         	text2+="</td>";  
         	text2+="<td align='center'>"; 
         	text2+="<input type='checkbox' name='PM' value='N'>"; 
         	text2+="<input type='hidden' name='PMT' value='N'>"; 
         	text2+="</td>";  
         	text2+="<td align='center'>"; 
         	text2+="<input type='checkbox' name='JT' value='N'>"; 
         	text2+="<input type='hidden' name='JTT' value='N'>"; 
         	text2+="</td>";  
         	text2+="</tr>";  
       
        var text3="<tr class='content1'>";
        	text3+="<td align='center'>"; 
//          	text3+="<input name='l1' type='text' size='2' value='' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;' >"; 
         	text3+="</td>";  
         	text3+="<td align='center'>"; 
         	text3+="<input name='l1' type='text' size='20' value='Address' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;'>"; 
         	text3+="</td>";  
         	text3+="<td align='center'>"; 
         	text3+="<input name='l1' type='text' size='15' value='Add.Address'  style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;'>"; 
         	text3+="</td>";  
         	text3+="<td align='center'>"; 
         	text3+="<input name='l1' type='text' size='10' value='E-Mail' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;'>"; 
         	text3+="</td>";  
         	text3+="<td align='center'>"; 
         	text3+="<input name='l1' type='text' size='10' value='Eff.Date' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;'>"; 
         	text3+="</td>";  
         	text3+="<td align='center'>"; 
         	text3+="<input name='l1' type='text' size='7' value='OTHER' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;'>"; 
         	text3+="</td>";  
         	text3+="<td align='center'>"; 
         	text3+="<input name='l1' type='text' size='7' value='ACTIVE' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;'>"; 
         	text3+="</td>";  
         	text3+="<td align='center' colspan='5'>"; 
//          	text3+="<input name='l1' type='text' size='5' value='' style='background-color:#E6E6FA; color: teal;text-transform: uppercase;height: 21px; letter-spacing:1px; padding: 10px 0 0 10px; font-size: 9px; font-weight:bolder; border:none;text-align:left;'>"; 
         	text3+="</td>";   
         	text3+="</tr>";  

         	var text4="<tr class='content1'>";
        	 text4+="<td align='center'>"; 
        	 text4+=""; 
        	 text4+="</td>"; 
        	 text4+="<td align='center'>"; 
        	 text4+="<select name='addr'  ></select>"; 
        	 text4+="</td>"; 
        	 text4+="<td align='center'>"; 
        	 text4+="<input type='text' name='user_add' value='' size='10' maxlength='100'>"; 
        	 text4+="</td>"; 
        	 text4+="<td align='center'>"; 
        	 text4+="<input type='text' name='contact_email' value='' size='9' maxlength='40' onblur='CheckEmailAddress(this)'>"; 
        	 text4+="</td>"; 
        	 text4+="<td align='center'>"; 
        if(indexVal=='0')
		{
        	 text4+="<input type='text' name='effective_dt' class='datepick' value='' size='9' maxlength='11' onblur=validateDate(this);>"; 
//         	 text4+="<img src='../images/calendar.gif' width='25' height='22' border='0' onclick=show_calendar('forms[0].effective_dt');>";
        }
        else
        {
        	 text4+="<input type='text' class='datepick' name='effective_dt'  value='' size='9' maxlength='11' onblur=validateDate(this);>";
//         	 text4+="<img src='../images/calendar.gif' width='25' height='22' border='0' onclick=show_calendar('forms[0].effective_dt["+indexVal+"]');>";
        }
        
        	 text4+="</td>"; 
        	 text4+="<td align='center'>"; 
        	 text4+="<input type='checkbox' name='Other' value='N'>";
        	 text4+="<input type='hidden' name='OtherT' value='N'>"; 
        	 text4+="</td>"; 
        	 text4+="<td align='center'>"; 
        	 text4+="<input type='checkbox' name='Active' checked value='Y'>"; 
        	 text4+="<input type='hidden' name='ActiveT' value='Y'>"; 
        	 text4+="</td>"; 
        	 text4+="<td colspan='5' align='center'>"; 
//         	 text4+="<input name='l1' type='text' size='5' value='' style='background-color:#E6E6FA ; border:none; font:bold; text-align: center;'>"; 
        	 text4+="</td>"; 
        	 text4+="</tr>"; 
		          
        	tableObj.innerHTML+=text1+text2+text3+text4;
		           
		           
    	document.forms[0].index.value = parseInt(indexVal)+1;
    	global_count = parseInt(global_count)+1;
    	
    	var PlantType = document.forms[0].PlantType.value;
    	var PlantName = document.forms[0].PlantName.value;
    	
    	var sepPlantType = PlantType.split("@");
    	var sepPlantName = PlantName.split("@");
    	 
    	if(indexVal==0)
    	{
	    	document.forms[0].addr.options[0]= new Option("--Select--","0");
	    	document.forms[0].addr.options[1]= new Option("Registered","R");
	    	document.forms[0].addr.options[2]= new Option("Correspondence","C");
	    	document.forms[0].addr.options[3]= new Option("Billing","B");
	    	var count = 4;
	    	for(var j=0;j< sepPlantType.length-1;j++){
	    	  document.forms[0].addr.options[count]= new Option(sepPlantName[j],sepPlantType[j]);
	    	  count++;
	    	}
    	}
    	else
    	{
	    	document.forms[0].addr[indexVal].options[0]= new Option("--Select--","0");
	    	document.forms[0].addr[indexVal].options[1]= new Option("Registered","R");
	    	document.forms[0].addr[indexVal].options[2]= new Option("Correspondence","C");
	    	document.forms[0].addr[indexVal].options[3]= new Option("Billing","B");
	    	var count = 4;
	    	
	    	for(var j=0;j<sepPlantType.length-1;j++){
	    	  document.forms[0].addr[indexVal].options[count]= new Option(sepPlantName[j],sepPlantType[j]);
	    	  count++;
	    }
       }
       
      /*  var seq = document.forms[0].seq; 
       var selAddress=document.forms[0].SelPlant.value; 
	  if(seq!=null && seq.length!=undefined){
	         var sep = selAddress.split("@");
	         for(var j=0;j<seq.length;j++){
	           document.forms[0].addr[j].value = sep[j];
	         }
	  }else if(seq!=null && seq.length==undefined){
	         var sep = selAddress.split("@");
	         document.forms[0].addr.value = sep[0];
	  } */
   }//end of else	
}
function checkForContactName()
{
   	var flag = false;
   	var contact_nm = document.forms[0].contact_nm;
   	
   	if(contact_nm!=null && contact_nm.length!=null && contact_nm.length!=undefined)
   	{
   		for(var j=0;j<contact_nm.length;j++)
   		{
        	if(trim(contact_nm[j].value)=="")
        	{
          		flag = true;
        	}
     	}
   	}
   	else if(contact_nm!=null && (contact_nm.length==null || contact_nm.length==undefined))
   	{
        if(trim(contact_nm.value)=="")
        {
        	flag = true;
        }
   	}
	return flag; 
}
function checkForAddress()
{
   	var flag = false;
   	var contact_nm = document.forms[0].contact_nm;
   	
   	var contact_nm_length = parseInt("0");
   	
   	if(contact_nm!=null)
   	{
   		if(contact_nm.length!=undefined)
   		{
   			contact_nm_length = contact_nm.length;
   		}
   		else
   		{
   			contact_nm_length = parseInt("1");
   		}
   	}
   	
   	//alert("contact_nm_length = "+contact_nm_length);
   	
   	if(contact_nm_length>1)
   	{
   		for(var j=0;j<contact_nm_length;j++)
   		{
   			var addr = document.forms[0].addr[j].value;
   			//alert("addr["+j+"] = "+addr);   			
        	if(trim(addr)=="" || trim(addr)=="0")
        	{
          		flag = true;
        	}
     	}
   	}
   	else if(contact_nm_length==1)
   	{
   		var addr = document.forms[0].addr.value;
   		//alert("addr = "+addr);
        if(trim(addr)=="" || trim(addr)=="0")
        {
        	flag = true;
        }
   	}
	return flag; 
}
function EnabledAll()
{
		var NOM = document.forms[0].NOM;
		if(NOM!=null && NOM.length!=undefined){
		for(var i=0;i<NOM.length;i++){
			document.forms[0].NOM[i].disabled = false;
			document.forms[0].INV[i].disabled = false;
			document.forms[0].FM[i].disabled = false;
			document.forms[0].PM[i].disabled = false;
			document.forms[0].Other[i].disabled = false;
			document.forms[0].Active[i].disabled = false;
			document.forms[0].addr[i].disabled  = false;
			document.forms[0].JT[i].disabled = false;
			document.forms[0].user_add[i].readOnly = false;
			document.forms[0].contact_nm[i].readOnly = false;
			document.forms[0].contact_desg[i].readOnly = false;
			document.forms[0].contact_phone[i].readOnly = false;
			document.forms[0].contact_mobile[i].readOnly = false;
			document.forms[0].contact_fax1[i].readOnly = false;
			document.forms[0].contact_fax2[i].readOnly = false;
			document.forms[0].contact_email[i].readOnly = false;
			document.forms[0].effective_dt[i].readOnly = false;
             
            /*document.forms[0].rnom_def[i].disabled = false;
			document.forms[0].rfm_def[i].disabled = false;
			document.forms[0].rinv_def[i].disabled = false;
			document.forms[0].rpm_def[i].disabled = false;
			document.forms[0].rjt_def[i].disabled = false;
            document.forms[0].roth_def[i].disabled = false;*/
		  }
	    }else if(NOM!=null && NOM.length==undefined){
           	document.forms[0].NOM.disabled = false;
			document.forms[0].INV.disabled = false;
			document.forms[0].FM.disabled = false;
			document.forms[0].PM.disabled = false;
			document.forms[0].Other.disabled = false;
			document.forms[0].Active.disabled = false;
			document.forms[0].addr.disabled  = false;
			document.forms[0].JT.disabled = false;
			document.forms[0].user_add.readOnly = false;
			document.forms[0].contact_nm.readOnly = false;
			document.forms[0].contact_desg.readOnly = false;
			document.forms[0].contact_phone.readOnly = false;
			document.forms[0].contact_mobile.readOnly = false;
			document.forms[0].contact_fax1.readOnly = false;
			document.forms[0].contact_fax2.readOnly = false;
			document.forms[0].contact_email.readOnly = false;
			document.forms[0].effective_dt.readOnly = false;
			
			/*document.forms[0].rnom_def.disabled = false;
			document.forms[0].rfm_def.disabled = false;
			document.forms[0].rinv_def.disabled = false;
			document.forms[0].rpm_def.disabled = false;
			document.forms[0].rjt_def.disabled = false;
            document.forms[0].roth_def.disabled = false;*/
	    }	
}
function doSubmit()
{
	var message = "Please Check the Following field(s).\n\n";
	var flag=true;
// 	var name = document.forms[0].name.value;
// 	var EntityFlag=document.forms[0].EntityFlag.value;
	var e = document. getElementById("trans_cd");
	var text=e. options[e. selectedIndex]. text;
	document.forms[0].transName.value = text;
	
	if(e.value == '')
	{
		alert("Please, Select Proper Transporter");
		return false;
	}
	else if(checkForContactName())
	{
		alert("Please, Input Contact Person Name for All Contacts ...");
	  	return false;
	}
	else if(checkForAddress())
	{
		alert("Please, Select appropriate Address from Drop-Down menu for All Contacts ...");
	  	return false;
	}
	else if(checkForEffectiveDate())
	{
		alert("Please, Input Effective Date for All Contacts ...");
	  	return false;
	}
	else
	{
	    //EnabledAll();	
	    var contact_nm = document.forms[0].contact_nm;
	    if(contact_nm!=null)
	    {
			var a;
			var NOM_C = document.forms[0].NOM;
			var INV_C = document.forms[0].INV;
			var FM_C = document.forms[0].FM;
			var PM_C = document.forms[0].PM;
			var OTH_C = document.forms[0].Other;
			var Active_C = document.forms[0].Active;
			var JT_C = document.forms[0].JT;
			
			var NOM = document.forms[0].NOMT;
			var INV = document.forms[0].INVT;
			var FM = document.forms[0].FMT;
			var PM = document.forms[0].PMT;
			var OTH = document.forms[0].OtherT;
			var Active = document.forms[0].ActiveT;
			var JT = document.forms[0].JTT;
			
			if(NOM_C!=null && NOM_C.length!=undefined)
			{
			   	for(var i=0;i<NOM_C.length;i++)
			   	{
			    	if(NOM_C[i].checked)
			    	{
			        	NOM[i].value = "Y";
			      	}
			      	else
			      	{
			       		NOM[i].value = "N";
			      	}
			    
			       	if(INV_C[i].checked)
			       	{
			        	INV[i].value = "Y";
			      	}
			      	else
			      	{
			        	INV[i].value = "N";
			      	}
			      
			        if(FM_C[i].checked)
			        {
			        	FM[i].value = "Y";
			      	}
			      	else
			      	{
			       		FM[i].value = "N";
			      	}
			      
			        if(PM_C[i].checked)
			        {
			        	PM[i].value = "Y";
			      	}
			      	else
			      	{
			       		PM[i].value = "N";
			      	}
			      
			      	if(Active_C[i].checked)
			      	{
			        	Active[i].value = "Y";
			      	}
			      	else
			      	{
			        	Active[i].value = "N";
			      	}
			      
			       	if(OTH_C[i].checked)
			       	{
			        	OTH[i].value = "Y";
			      	}
			      	else
			      	{
			        	OTH[i].value = "N";
			      	}
			      
			      	if(JT_C[i].checked)
			      	{
			        	JT[i].value = "Y";
			      	}
			      	else
			      	{
			        	JT[i].value = "N";
			      	}
			    }
			}
			else if(NOM!=null && NOM.length==undefined)	
			{
			    if(NOM_C.checked)
			    {
			    	NOM.value = "Y";
			    }
			    else
			    {
			    	NOM.value = "N";
			    }
			    
			    if(INV_C.checked)
			    {
			    	INV.value = "Y";
			    }
			    else
			    {
			    	INV.value = "N";
			    }
			      
			    if(FM_C.checked)
			    {
			    	FM.value = "Y";
			    }
			    else
			    {
			       	FM.value = "N";
			    }
			      
			    if(PM_C.checked)
			    {
			        PM.value = "Y";
			    }
			    else
			    {
			       	PM.value = "N";
			    }
			      
			    if(Active_C.checked)
			    {
			        Active.value = "Y";
			    }
			    else
			    {
			        Active.value = "N";
			    }
			      
			    if(OTH_C.checked)
			    {
			        OTH.value = "Y";
			    }
			    else
			    {
			        OTH.value = "N";
			    }
			      
			    if(JT_C.checked)
			    {
			        JT.value = "Y";
			    }
			    else
			    {
			        JT.value = "N";
			    }
			    //DLNG
			}
			
			if(document.forms[0].flg.value=='update')
			{
				a=confirm('Do You Want To Modify Entity Details ?');
			}
			else
			{
				a=confirm('Do You Want To Insert/Update Entity Details ?');
// 				btnflag
			}			
			if(a)
			{
			    EnabledAll();	
				document.forms[0].submit();
			}
		}
		else
		{
			alert("Please, Add atleast one new contact by clicking on 'Add New Contact' button ...");
	  		return false;
		}
	}
}
function getlist(obj){
	
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var modUrl = document.forms[0].modUrl.value;
	var trans_cd = obj.value;
	
	var url = modUrl+"?trans_cd="+trans_cd+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl+"&modUrl="+modUrl;
// 	alert(url)
	location.replace(url);
			
}
</script>
<jsp:useBean class="com.seipl.hazira.dlng.truck_master.DataBean_transporterMaster" id="transMst" scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="util" scope="page"/>
<%
	util.init();
	String todayDate = util.getGen_dt();
	
	String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();
	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();
	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();
	String modUrl = session.getAttribute("modUrl")==null?"":session.getAttribute("modUrl").toString();//HA20210814
	String write_permission = (String)session.getAttribute("write_permission")==null?"":(String)session.getAttribute("write_permission");
	String delete_permission = (String)session.getAttribute("delete_permission")==null?"":(String)session.getAttribute("delete_permission");
	String print_permission = (String)session.getAttribute("print_permission")==null?"":(String)session.getAttribute("print_permission");
	String approve_permission = (String)session.getAttribute("approve_permission")==null?"":(String)session.getAttribute("approve_permission");
	String audit_permission = (String)session.getAttribute("audit_permission")==null?"":(String)session.getAttribute("audit_permission");
	String check_permission =(String)session.getAttribute("check_permission")==null?"":(String)session.getAttribute("check_permission");
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	String trans_cd = request.getParameter("trans_cd")==null?"":request.getParameter("trans_cd");
	String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
	String effDt = request.getParameter("effDt")==null?"":request.getParameter("effDt");
	String Entity_Id = request.getParameter("Entity_Id")==null?"":request.getParameter("Entity_Id");
// 	System.out.println("trans_cd----JSP"+trans_cd);
	
	transMst.setTrans_cd(trans_cd);
	transMst.init();
	
	Vector transporter_id = transMst.getTransporter_id();
	Vector transporter_name = transMst.getTransporter_name();
	Vector effective_date = transMst.getEffective_date();
	Vector pan_no = transMst.getPan_no();
	Vector pan_issue_date = transMst.getPan_issue_date();
	Vector address = transMst.getAddress();
	Vector cst_tin_no = transMst.getCst_tin_no();
	Vector cst_tin_dt = transMst.getCst_tin_dt();
	Vector gst_tin_no = transMst.getGst_tin_no();
	Vector gst_tin_dt = transMst.getGst_tin_dt();
	Vector tan_no = transMst.getTan_no();
	Vector tan_dt = transMst.getTan_dt();
	Vector gstin_no = transMst.getGstin_no();
	Vector gstin_dt = transMst.getGstin_dt();
	Vector status = transMst.getStatus();
	Vector statecode = transMst.getStatecode();
	Vector statename = transMst.getStatename();
	Vector abbr = transMst.getAbbr();
	
	Vector  CSEQ_NO =transMst.getCSEQ_NO();
	Vector  CEFF_DT  =transMst.getCEFF_DT();
	Vector  CONTACT_PERSON  =transMst.getCONTACT_PERSON();
	Vector  CONTACT_DESIG  = transMst.getCONTACT_DESIG();
	Vector  CPHONE  =transMst.getCPHONE();
	Vector  CMOBILE  =transMst.getCMOBILE();
	Vector  CFAX_1  =transMst.getCFAX_1();
	Vector  CFAX_2   =transMst.getCFAX_2();
	Vector  CEMAIL  =transMst.getCEMAIL();
	Vector  ADDR_FLAG  =transMst.getADDR_FLAG();
	Vector  ADDL_ADDR_LINE  =transMst.getADDL_ADDR_LINE();
	Vector  NOM_FLAG   =transMst.getNOM_FLAG();
	Vector  INV_FLAG   =transMst.getINV_FLAG();
	Vector  FM_FLAG  =transMst.getFM_FLAG();
	Vector  PM_FLAG   =transMst.getPM_FLAG();
	Vector  OTHER_FLAG  =transMst.getOTHER_FLAG();
	Vector  ACTIVE_FLAG   =transMst.getACTIVE_FLAG();
	Vector  JT_FLAG   = transMst.getJT_FLAG(); 
	    
	Vector  RNOM_FLAG   =transMst.getDNOM_FLAG();
	Vector  RINV_FLAG   =transMst.getDINV_FLAG();
	Vector  RFM_FLAG  =transMst.getDFM_FLAG();
	Vector  RPM_FLAG   =transMst.getDPM_FLAG();
	Vector  ROTHER_FLAG  =transMst.getDOTHER_FLAG();
	Vector  RJT_FLAG   = transMst.getDJT_FLAG(); 
	Vector PLANT_SEQ_NO = transMst.getPLANT_SEQ_NO();
	Vector PLANT_TYPE = transMst.getPLANT_TYP();
	Vector PLANT_NM = transMst.getPLANT_NM();
	Vector PLANT_ABBR = transMst.getPLANT_ABR();
	
	String PlantType = "";
	String PlantName = "";
	String SelPlant = "";
%>
<div class="tab-content">
<div class="tab-pane active" id="hcasreport">
<!-- Default box -->
<div class="box mb-0">
	<form method="post" name="daily_seller_nom" action="../servlet/Frm_TransporterMasterV2" onSubmit="return false;">
	
		<input type="hidden" name="modCd" value="<%=modCd%>">
		<input type="hidden" name="formId" value="<%=formId%>">
		<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
		<input type="hidden" name="modUrl" value="<%=modUrl%>">
	
		<input type="hidden" name="index" value="<%=CSEQ_NO.size() %>"  >
		<input type="hidden" name="effDt" value="<%=effDt%>">
		<input type="hidden" name="PlantType" value="<%=PlantType %>">
		<input type="hidden" name="PlantName" value="<%=PlantName %>">
		<input type="hidden" name="SelPlant" value="<%=SelPlant%>">
		<input type="hidden" name="flg" value="<%=activity %>">
		<input type="hidden" name="btnflag" value="SAVE_TRANS_CONT_PERSON">
		<input type="hidden" name="EntityFlag" value="tran">
		<input type="hidden" name="emp_cd" value="<%=emp_cd%>">
		<input name="transName" type="hidden" value="" >
		
		<%if(msg.length()>5){%>
			<div class="box-body table-responsive no-padding">
				<table class="table  table-bordered">
					<thead>   
						<tr class="info">
							<th class="text-center"  
							<%if(msg.contains("Error:")){ %>style="color: red;" <%}else{ %>style="color: blue;" <%} %>><%=msg %></th>
						</tr>
					</thead>
				</table>
			</div>
		<%} %>
		
		<div class="box-header with-border main-header" >
			<div class="form-group col-md-2">
			 	<label for="">Transporter</label>	
				<select class="form-control" name="trans_cd" id="trans_cd" onchange="getlist(this);" >
					<option value="">--select--</option>
					<%for(int i = 0 ; i < transporter_id.size() ; i++){ %>
						<option value="<%=transporter_id.elementAt(i)%>"><%=abbr.elementAt(i) %></option>
					<%} %>	
				</select>
				<%if(!trans_cd.equals("")) {%>
					<script type="text/javascript">
						document.forms[0].trans_cd.value='<%=trans_cd%>';
					</script>
				<%} %>
			 </div>
		 </div>
		 <div class="box-body table-responsive no-padding" >
			<table id="tableid" class="table table-bordered">
				<thead>
				<%for(int i =0;i<CSEQ_NO.size();i++){ %>
					<tr class="info">
						<th class="text-center">  Seq# </th>
				        <th class="text-center"> Person Name  </th>
				        <th class="text-center"> Designation </th>
				        <th class="text-center"> Phone  </th>
				        <th class="text-center"> Mobile </th>
				        <th class="text-center"> Fax </th>
				        <th class="text-center">  </th>
				        <th class="text-center"> NOM </th>
				        <th class="text-center"> INV </th>
				        <th class="text-center"> FM </th>
				        <th class="text-center"> PM </th>
				        <th class="text-center"> JT </th>
					</tr>
					<tr >
				         <td class="text-center"> <input type="text" class="text-center" name="seq" value="<%=CSEQ_NO.elementAt(i)%>" size="4" maxlength="2" readOnly class="mkRdly"></td>
				         <td class="text-center"> <input type="text" name="contact_nm" value="<%=CONTACT_PERSON.elementAt(i)%>" size="20" maxlength="50" >  </td>
				         <td class="text-center"> <input type="text" name="contact_desg" value="<%=CONTACT_DESIG.elementAt(i)%>" size="9" maxlength="40"  > </td>
				         <td class="text-center"> <input type="text" name="contact_phone" value="<%=CPHONE.elementAt(i)%>" size="9" maxlength="20" onblur="validatePhoneFax(this);"  > </td>
				         <td class="text-center"> <input type="text" name="contact_mobile" value="<%=CMOBILE.elementAt(i)%>" size="9" maxlength="20" onblur="validatePhoneFax(this);"  > </td>
				         <td class="text-center"> <input type="text" name="contact_fax1" value="<%=CFAX_1.elementAt(i)%>" size="9" maxlength="20" onblur="validatePhoneFax(this);"  ></td>
				         <td class="text-center" > <input type="text" name="contact_fax2" value="<%=CFAX_2.elementAt(i)%>" size="9" maxlength="20" onblur="validatePhoneFax(this);"  ></td>
				        <td class="text-center">
				         	<input type="checkbox" name="NOM" <%if(RNOM_FLAG.elementAt(i).equals("Y")){%>checked value="Y"<%}else{%>value="N"<%}%>>
				         	<input type="hidden" name="NOMT" <%if(RNOM_FLAG.elementAt(i).equals("Y")){%>value="Y"<%}else{%>value="N"<%}%>>
				         </td>
				         <td class="text-center">
				         	<input type="checkbox" name="INV" <%if(RINV_FLAG.elementAt(i).equals("Y")){%> checked value="Y" <%}else{%> value="N" <%}%> >
				         	<input type="hidden" name="INVT" <%if(RINV_FLAG.elementAt(i).equals("Y")){%> value="Y" <%}else{%> value="N" <%}%>>
				         </td>
				         <td class="text-center">
				         	<input type="checkbox" name="FM" <%if(RFM_FLAG.elementAt(i).equals("Y")){%> checked value="Y" <%}else{%> value="N" <%}%> >
				         	<input type="hidden" name="FMT" <%if(RFM_FLAG.elementAt(i).equals("Y")){%> value="Y" <%}else{%> value="N" <%}%>>
				         </td>
				         <td class="text-center">
				         	<input type="checkbox" name="PM" <%if(RPM_FLAG.elementAt(i).equals("Y")){%> checked value="Y" <%}else{%> value="N" <%}%> >
				         	<input type="hidden" name="PMT" <%if(RPM_FLAG.elementAt(i).equals("Y")){%> value="Y" <%}else{%> value="N" <%}%>>
				         </td>
				         <td class="text-center">
				         	<input type="checkbox" name="JT" <%if(RJT_FLAG.elementAt(i).equals("Y")){%> checked value="Y" <%}else{%> value="N" <%}%> >
				         	<input type="hidden" name="JTT" <%if(RJT_FLAG.elementAt(i).equals("Y")){%> value="Y" <%}else{%> value="N" <%}%>>
				         </td>
				      </tr>
					<tr class="info">
			        	<th class="text-center">&nbsp;</th>
			         	<th class="text-center">Address</th>
			         	<th class="text-center">Add.Address</th>
			         	<th class="text-center">E-Mail</th>
			         	<th class="text-center">Eff.Date</th>
			         	<th class="text-center">OTHER</th>
			         	<th class="text-center">ACTIVE</th>
			         	<th class="text-center" colspan="5">&nbsp;</th>
			     	</tr>
			     	<tr >
				         <td class="text-center">&nbsp;</td>
				         <td class="text-center"> 
				         	<select name="addr" id="addr<%=i %>" >
				         		<option value="0">--Select--</option>
				         		<option value="R">Registered</option>
				         		<option value="C">Correspondence</option>
				         		<option value="B">Billing</option>
				         		<%	for(int j=0;j<PLANT_SEQ_NO.size();j++)
				         			{	%>
				           				<option value="<%=PLANT_TYPE.elementAt(j)%>"><%=PLANT_NM.elementAt(j)%></option>
				         		<%	}	%>
				         	</select>
				         	<script type="text/javascript">
				         		document.getElementById('addr'+<%=i %>).value = '<%=ADDR_FLAG.elementAt(i)%>';
				         	</script>
				         	
				         </td>
				         <td class="text-center">
				         	<input type="text" name="user_add" value="<%=ADDL_ADDR_LINE.elementAt(i)%>" size="10" maxlength="100" >
				         </td>
				         <td class="text-center">
				         	<input type="text" name="contact_email" value="<%=CEMAIL.elementAt(i)%>" size="9" maxlength="40" onblur="CheckEmailAddress(this);" >
				         </td>
				         <td class="text-center">
				         	<input type="text" name="effective_dt" class="datepick" value="<%=CEFF_DT.elementAt(i)%>" size="9" maxlength="11" onblur="validateDate(this);" >
<%-- 				           	<img src='../images/calendar.gif' width='25' height='22' border='0' onclick="show_calendar('forms[0].effective_dt[<%=i%>]')";> --%>
				         </td>
				         <td class="text-center">
				         	<input type="checkbox" name="Other" <%if(OTHER_FLAG.elementAt(i).equals("Y")){%>checked value="Y"<%}else{%> value="N"<%}%> >
				            <input type="hidden" name="OtherT"  <%if(OTHER_FLAG.elementAt(i).equals("Y")){%>value="Y"<%}else{%>value="N"<%}%>>
				         </td>
				         <td class="text-center">
				         	<input type="checkbox" name="Active" <%if(ACTIVE_FLAG.elementAt(i).equals("Y")){%>checked value="Y"<%}else{%>value="N"<%}%> >
				         	<input type="hidden" name="ActiveT" <%if(ACTIVE_FLAG.elementAt(i).equals("Y")){%>value="Y"<%}else{%>value="N"<%}%>>
				         </td>
				         <td class="text-center" colspan="5">
				         	&nbsp;
				         </td>
				      </tr>
				      <%} %>
				</thead>
				<tbody>
				</tbody>
				<tr>
					<td class="text-center" colspan="12">
						<input type="button" <%if(trans_cd.equals("")){ %> disabled="disabled" <%} %> name="add" value="Add New Contact" onclick="addRow('<%=todayDate%>');"  class="btn btn-primary btn-sm">
					</td>
			    </tr>
			</table>
		</div>
	</form>
	
	 <div class="box-body table-responsive no-padding" >
		<table id="tableid" class="table table-bordered">
			<tr>
			<td colspan="12" align="right">
				<%	if(write_permission.trim().equalsIgnoreCase("Y"))
					{	%>	
						<input type="button" <%if(trans_cd.equals("")){ %> disabled="disabled" <%} %> name="save" value="Submit" onClick="doSubmit();" class="btn btn-success">
				<%	}
					else
					{	%>
						<input type="button" name="save" class="btn btn-success" value="Submit" disabled="disabled">
				<%	}	%>
				</td>
			</tr>
		</table>
	</div>
				
</div>
</div>
</div>
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
