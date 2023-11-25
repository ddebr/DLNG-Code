<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@ page buffer="128kb" %>
<%@ page errorPage="../home/ExceptionHandler.jsp" %>
<%@ include file="../sess/Expire.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>FMS | Master Law Reasons </title>

<!-- Font Awesome Icons -->
<link rel="stylesheet" href="../plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="../css/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../dist/css/adminlte.min.css">
<link rel="stylesheet" href="../plugins/toastr/toastr.min.css">

<script type="text/javascript" src="../js/mouseclk.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/nhv.js"></script>
<link href = "../plugins/jquery-ui/jquery-ui.css" rel = "stylesheet">

<style type="text/css">
.card-footer{
background-color: #fff;
}
.mandatory{
display:none;
}
.mand{
color: red;
}
.focusAdd{
background-color: #EDEDF8;
}
.largefont{
font-size: 22px;
}
</style>

<script type="text/javascript">
var oldVal=new Array();
var newVal=new Array();
var valUpd=new Array();
var mixVal=new Array();
var tcompadd1=new Array();
flag=true;
temp="";
tp="";
k=1;
var l=0;
var g=0;

function calcEnergy(hv,enunit,conttype)
{
   if(conttype=="GTA" || conttype=="GSTA")
   {
	   mdqA=document.forms[0].mdqA.value;
	   dcqA= document.forms[0].dcqA.value;
	   var energy="";
	   var energy1="";
	   if(enunit=='GCALS' || enunit == 'gcals'){
   			energy=round(convert_MDQ_TO_GCALS(hv,mdqA),3);
	   }else if(enunit=='mmbtu' || enunit == 'MMBTU'){
   			//energy=convert_MDQ_TO_MMBTU(hv,mdqA);
   			energy = round(convert_MMSCM_TO_MMBTU(dcqA,hv),3);
   			energy1= round(convert_MMSCM_TO_MMBTU(mdqA,hv),3);
	   }
	   document.forms[0].energyA.value=energy;
	   document.forms[0].energy1A.value=energy1;
    }
    else
    {
    	dcqA= document.forms[0].dcqA.value;
		var energy="";
		if(enunit=='GCALS' || enunit == 'gcals'){
			//energy=convert_MDQ_TO_GCALS(hv,dcqA);
		    energy = round(convert_MMSCM_TO_MMBTU(dcqA,hv),3);   
		}else if(enunit=='mmbtu' || enunit == 'MMBTU'){
		    //energy=convert_MDQ_TO_MMBTU(hv,dcqA);
		    energy = round(convert_MMSCM_TO_MMBTU(dcqA,hv),3);   
		}
		document.forms[0].energyA.value=energy;
    }
}
 
function calcMdq(hv,enunit,conttype)
{ 
   if(conttype=="GSA")
   {
	   energyA=document.forms[0].energyA.value;
	   var dcq="";
		     
	   if(enunit == 'GCALS' || enunit == 'gcals'){ 
	   		dcq=round(convert_GCALS_TO_MDQ(hv,energyA),2);
	   }else if(enunit == 'mmbtu' || enunit == 'MMBTU'){
		    //mdq=convert_MMBTU_TO_MDQ(hv,energyA);
		    dcq = round(convert_MMBTU_TO_MMSCM(energyA,hv),2);
	   }
	   document.forms[0].dcqA.value=dcq;
   }
   else
   {
	   energyA=document.forms[0].energyA.value;
	   energy1A = document.forms[0].energy1A.value;
	   var mdq="";
	   var dcq = "";
	   if(enunit == 'GCALS' || enunit == 'gcals'){ 
       		dcq=round(convert_GCALS_TO_MDQ(hv,energyA),2);
        	mdq=round(convert_GCALS_TO_MDQ(hv,energyA),2);
	   }else if(enunit == 'mmbtu' || enunit == 'MMBTU'){
		    dcq=round(convert_MMBTU_TO_MMSCM(energyA,hv),2);
		    mdq=round(convert_MMBTU_TO_MMSCM(energy1A,hv),2);
	   }
	   document.forms[0].dcqA.value=dcq;
	   document.forms[0].mdqA.value=mdq;
   }
} 

function calcEnergyUpdate(hv,enunit,k,tot ,conttype)
{
	var mdq="";
    var energy="";
    if(tot=='1')
    {
    	if(conttype=="GTA" || conttype=="GSTA")
    	{
			dcq=document.forms[0].dcq.value;
		    mdq=document.forms[0].mdq.value;
		    if(mdq==null || mdq=="0"){
		    	mdq=0;
		    }
		    if(dcq==null || dcq=="0"){
		        mdq=0; 
		    }
		}
    	else
    	{
		    dcq=document.forms[0].dcq.value;
		    if(dcq==null || dcq=="0"){
				mdq=0;
		    }
		} 
	}
	else
	{
		if(conttype=="GTA" || conttype=="GSTA")
		{
			dcq=document.forms[0].dcq[k].value;
		    mdq=document.forms[0].mdq[k].value;
		    if(dcq==null || dcq=="0"){
		          dcq=0;
		    }
		    if(mdq==null || mdq=="0"){
		          mdq=0;
		    }  
		}
		else
		{
		    dcq=document.forms[0].dcq[k].value;
		    if(dcq==null || dcq=="0"){
		          dcq=0;
		    }
		}
	} 
		     
	if(enunit=='GCALS' || enunit == 'gcals')
	{
		if(conttype=="GTA" || conttype=="GSTA")
		{
			if(dcq!="0" ){
				energy=round(convert_MDQ_TO_GCALS(hv,dcq),3);
		    }else{
		        energy="0";
		    }
			
		    if(mdq!="0" ){
		        energy1=round(convert_MDQ_TO_GCALS(hv,mdq),3);
		    }else{
		           energy1="0";
		    }
		        
		}
		else
		{
		    if(dcq!="0" ){
		           energy=round(convert_MDQ_TO_GCALS(hv,dcq),3);
		    }else{
		           energy="0";
		    }
		} 
	 }
	 else if(enunit=='mmbtu' || enunit == 'MMBTU')
	 {
		 if(conttype=="GTA" || conttype=="GSTA")
		 {
			 if(dcq!="0"){
		          //energy=convert_MDQ_TO_MMBTU(hv,dcq);
		          energy = round(convert_MMSCM_TO_MMBTU(dcq,hv),3);   
		     }else{
		          energy="0";
		     }
		       
		     if(mdq!="0"){
		          //energy1=convert_MDQ_TO_MMBTU(hv,mdq);
		          energy1 = round(convert_MMSCM_TO_MMBTU(mdq,hv),3);   
		     }else{
		          energy1="0";
		     }
		            
		 }
		 else
		 {
			 if(dcq!="0"){
		          //energy=convert_MDQ_TO_MMBTU(hv,dcq);
		          energy = round(convert_MMSCM_TO_MMBTU(dcq,hv),3);   
		     }else{
		          energy="0";
		     }
		 }
	 }
	 if(tot=='1')
	 {
		 if(conttype=="GTA" || conttype=="GSTA"){
		    document.forms[0].energy.value=energy;
		    document.forms[0].energy1.value=energy1;
		 }else{
		         document.forms[0].energy.value=energy;
		 }
	 }
	 else
	 {
		  if(conttype=="GTA" || conttype=="GSTA"){
		     document.forms[0].energy[k].value=energy;
		     document.forms[0].energy1[k].value=energy1;
		  }else{
		     document.forms[0].energy[k].value=energy;
		  }
	 }
}

function calcMdqUpdate(hv,enunit,k,tot)
{
	if(tot=='1')
    {
		energy=document.forms[0].energy.value;
	    if(energy==null || energy=="NaN"){
	         energy=0;
	    }  
    }
    else
    {
    	energy=document.forms[0].energy[k].value;
        if(energy==null || energy=="NaN"){
        	energy=0;
        }
   	} 
    
   	var mdq="";
   	if(enunit == 'GCALS' || enunit == 'gcals')
   	{
   		if(energy != "0"){
       		mdq=round(convert_GCALS_TO_MDQ(hv,energy),2);
     	}else{
       		mdq="0";
     	}
   	}
   	else if(enunit == 'mmbtu' || enunit == 'MMBTU')
   	{
   		if(energy!="0")
      	{
        	// mdq=convert_MMBTU_TO_MDQ(hv,energy);
          	mdq=round(convert_MMBTU_TO_MMSCM(energy,hv),2);
      	}
      	else
      	{
        	// mdq=convert_MMBTU_TO_MDQ(hv,energy);
         	mdq = round(convert_MMBTU_TO_MMSCM(energy,hv),2);
      	}
   	}
   	
   	if(tot=='1'){
     	document.forms[0].mdq.value=mdq;
   	}else{
     	document.forms[0].mdq[k].value=mdq;
   	}
}

function sv()
{
	elem=document.forms[0].elements;
  	oldVal[0]=document.forms[0].pty.value;
  
    for(i=0;i<elem.length;i++)
    {
         if(elem[i].type=='text' || elem[i].type=='hidden')
         {
            if(elem[i].value=="-1")
              break;
			oldVal[k++]=elem[i].value;
         }
    }
}

function checkEffDate(v,f,d)
{
	if(d!="")
	{
		if(f=="A"){
    		if(v != "true"){ 
            	alert("Effective Date is out side the Contract Period");
            	document.forms[0].effdtA.value="";
   			}
 		}
 	}
}



function trackCh()
{
	k=1;
  	elem=document.forms[0].elements;
  	newVal[0]=document.forms[0].pty.value;
  	for(i=0  ; i < elem.length ; i++)
  	{
     	if(elem[i].type=='text'  || (elem[i].type=='hidden'))
     	{
        	if(elem[i].value=="-1" || elem[i].value=='M')
        		break;
			newVal[k++]=elem[i].value;
     	}
  	}
  
  	for(i=0 ; i < k ; i++)
  	{
      	if(oldVal[i]!= newVal[i])
        	flag=false;
   	}
 
  	if(!flag)
  	{  
    	var a=confirm("Do you want to Modify?");
    	if(a)
    	{
       		document.forms[0].submit();
    	}
    	else
    	{
      		return false;
    	} 	
  	}  
  	else
  	{
    	alert("No Changes have been made");
  	}
}

function subm(conttype)
{
   if(conttype=="GSA")
   {
    	if(document.forms[0].effdtA.value=="" || document.forms[0].dcqA.value=="" || document.forms[0].energyA.value=="")
    	{
            alert("All Details are Mandatory!");
    	}
    	else
    	{
	       var a=confirm("Do you want to Add?");
	       if(a)
	       {
				document.forms[0].submit();
	       }
	       else
	       {
	           return false;
	       }
    	}
    }
    else
    {
    	if(document.forms[0].effdtA.value=="" || document.forms[0].mdqA.value=="" || document.forms[0].dcqA.value=="" || document.forms[0].energyA.value=="" || document.forms[0].energy1A.value=="")
    	{
            alert("All Details are Mandatory!");
    	}
    	else
    	{
	       var a=confirm("Do you want to Add?");
	       if(a)
	       {
	          document.forms[0].submit();
	       }
	       else
	       {
	           return false;
	       }
        }
    }
}

function enableA(conttype)
{
	if(conttype=="GTA" || conttype=="GSTA")
	{
		document.forms[0].dcqA.className="thdLine2";
		document.forms[0].energyA.className="thdLine2";
		document.forms[0].mdqA.className="thdLine2";
		document.forms[0].energy1A.className="thdLine2";
		document.forms[0].effdtA.className="thdLine2";
	}else{
		document.forms[0].dcqA.className="thdLine2";
		document.forms[0].energyA.className="thdLine2";
		document.forms[0].effdtA.className="thdLine2";
	}
}

function setFlag(ptyCd)
{
	var sbutton="Submit";
  	ptyname= document.forms[0].pty[document.forms[0].pty.selectedIndex].innerText;
  	var ptycd= document.forms[0].pty[document.forms[0].pty.selectedIndex].value;
  	var flag=false;
  	if(ptycd=='')
  	{
  		alert('Please Select Contract...');
  		flag=true;
  	}
  	if(!flag)
  	{
  		var url="frm_mas_compmdq.jsp?flag=A&opt=3&pyCd="+ptyCd+"&ptyName="+ptyname+"&btvalue="+sbutton;
  		location.replace(url);	
  	}
}

function setCombo()
{
	pyCd=document.forms[0].pty.value;
  	var btvalue="Update";
  	ptyname= document.forms[0].pty[document.forms[0].pty.selectedIndex].innerText;
   	var url="frm_mas_compmdq.jsp?flag=M&opt=3&pyCd="+pyCd+"&ptyName="+ptyname+"&btvalue="+btvalue;//SB20101116
  	//var url="frm_mas_others.jsp?flag=M&opt=3&pyCd="+pyCd+"&ptyName="+ptyname;
  	location.replace(url); 
}

function refresh1(ptyCd)
{
	effdtA=document.forms[0].effdtA.value;
  	supcd=document.forms[0].supcd.value;
    ptyname= document.forms[0].pty[document.forms[0].pty.selectedIndex].innerText;
  	var url="frm_mas_compmdq.jsp?flag=A&opt=3&effdtA="+effdtA+"&pyCd="+ptyCd+"&supcd="+supcd+"&ptyName="+ptyname;
  	location.replace(url);
}
</script>
</head>

<jsp:useBean class="com.Gsfc.Baroda.fms6.master.DataBean_master_others" id="masOthers" scope="page"/>
<jsp:useBean class="com.Gsfc.Baroda.fms6.util.UtilBean" id="util" scope="page"/>
<%
util.init();
String todayDate = util.getGen_dt();
String comp_cd=(String)session.getAttribute("comp_cd");//JHP
String ptyCd=request.getParameter("pyCd")==null?"":request.getParameter("pyCd");
String effdtA = request.getParameter("effdtA")==null?"":request.getParameter("effdtA");
String supcd1 = request.getParameter("supcd")==null?"":request.getParameter("supcd");
String ptyName = request.getParameter("ptyName")==null?"":request.getParameter("ptyName");
String opt=request.getParameter("opt")==null?"":request.getParameter("opt");
String flag=request.getParameter("flag")==null?"":request.getParameter("flag");
String status=request.getParameter("status")==null?"":request.getParameter("status");
String subbutton=request.getParameter("btvalue")==null?"Submit":request.getParameter("btvalue");
String today_dt=request.getParameter("today_dt")==null?todayDate:request.getParameter("today_dt");

masOthers.setCallFlag("MdqDetails");
masOthers.setCompany_cd(comp_cd);
masOthers.setSetPartyCd(ptyCd);
masOthers.setSetEffDate(effdtA);
masOthers.setCurrent_date(today_dt);
masOthers.init(); 
     
Vector ptyCDMas=masOthers.getVmappingid();
Vector ptyNmMas=masOthers.getVparty_abr();
Vector ptCd=masOthers.getVparty_cd();
Vector ptyEdt=masOthers.getEff_dt();
Vector ptyEngy=masOthers.getEnergy();
Vector ptyEngy1=masOthers.getEnergy1();
Vector ptyMdq=masOthers.getMdq();
Vector ptyDcq=masOthers.getDcq();
Vector ptyNhv=masOthers.getNhv();
Vector ptyGhv=masOthers.getGhv();
Vector ptyEnBase=masOthers.getEnergy_base();
Vector ptyEnUnit=masOthers.getEnergy_unit();
Vector cont_st_dt=masOthers.getCont_st_dt();
Vector cont_end_dt=masOthers.getCont_end_dt();
Vector contract_name=masOthers.getContract_name();
String conttype =masOthers.getConttype();
String V = masOthers.getValid();
String ptCodeTot="";
String nhv=masOthers.getNhval();
String ghv=masOthers.getGhval();
String en_unit=masOthers.getEn_unit();
String eBase=masOthers.getEn_base();
    
if(!ptyCd.equalsIgnoreCase(""))
  for(int i=0;i<ptyNmMas.size();i++){
    if(ptyCd.equalsIgnoreCase(ptyCDMas.elementAt(i)+""))
    {
  	  ptyName = (String)ptyNmMas.elementAt(i)+"-"+contract_name.elementAt(i);
    }
}
%>  

<body onLoad="checkEffDate('<%=V %>','<%=flag %>',<%= effdtA%>);">
<%@ include file="../home/header.jsp" %>
<form name="mainview" method="post" action="../servlet/Frm_master_others">
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
   		<div class="container-fluid">
     		<div class="row mb-2">
    				<div class="col-sm-8">
      				<h1>Master Entry : MDQ Details<%//=formName%></h1>
     			</div>
       			<div class="col-sm-4">
         			<ol class="breadcrumb float-sm-right">
           				<li class="breadcrumb-item"><a href="#">Home</a></li>
           				<li class="breadcrumb-item active">Master Maintenance > </li>
         			</ol>
       			</div>
     		</div>
   		</div><!-- /.container-fluid -->
    </section>
    	<section class="content">
	    	<div class="container-fluid">
	        	<div class="row">
	          		<div class="col-md-12">
	          			<div class="card">
	              			<div class="card-header"><!-- /.card-header -->
								<div class="card-title"><h4>MDQs</h4></div>
								<div class="card-tools" >
	                	 			<!--<b class="mandatory"><strong class="mand">*</strong>-Mandatory Information</b> -->  
	                			</div>
							</div>
							
							<div class="card-body table-responsive p-0">
			            		<table class="table table-head-fixed text-nowrap table-bordered">
			            			<thead>
			            				<tr>
			            					<th>Today's Date : </th>
			            					<th><%=today_dt %><input type="hidden" name="today_dt" value="<%=today_dt %>"></th>
			            					<th>Select Contract :</th>
			            					<th>
			            						<select name="pty" onChange="setCombo();">
											    	<option value="">-- Select --  </option>
											    	<% String st_dt1 = "";
											    	   String end_dt1 = "";
											      	   for(int i=0 ; i < ptyCDMas.size() ; i++)
											      	   {
											    	  		String pCd=(String)ptyCDMas.elementAt(i);
											    	  		String pNm=(String)ptyNmMas.elementAt(i)+"-"+contract_name.elementAt(i);
											    	  		String st_dt=(String)cont_st_dt.elementAt(i);
											    	  		String end_dt=(String)cont_end_dt.elementAt(i);
											    	  		if(ptyCd.equalsIgnoreCase((String)ptyCDMas.elementAt(i)))
											    	  		{
											    		  		st_dt1=st_dt;
											    		  		end_dt1=end_dt;%>
											    		  		<option value="<%=pCd%>" selected> <%=pNm%> </option>
											        	  <%}else{%>
											        	  		<option value="<%=pCd%>"> <%=pNm%> </option>
											        	  <%}
											    	  	}%>
											  	</select>
			            					</th>
			            					<%if(!st_dt1.equals("") && !end_dt1.equals("")){%>
			            						<th>Contract Period : </th>
			            						<th><%=st_dt1 %>&nbsp;&nbsp;<b class="largefont">To</b>&nbsp;&nbsp;<%=end_dt1 %></th>
			            					<%}%>
			            					<th><button type="button" class="btn btn-info" name="add" value="Add New Entry" onClick="setFlag('<%=ptyCd%>');">Add New</button></th>
			            				</tr>
			            			</thead>
								</table>
							</div>
							
							<div class="card-body table-responsive p-0" style="height: 400px;">
			            		<table class="table table-head-fixed text-nowrap table-bordered">
			            			<thead>
			            				<tr>
			            					<th class="text-center">Contract</th>
			            					<th class="text-center">&nbsp;&nbsp;Effective Date&nbsp;&nbsp;</th>
			            					<th class="text-center">NHV</th>
			            					<th class="text-center">GHV</th>
			            					<th>Entry Point MDQ(MMSCM)</th>
			            					<%if(conttype.equalsIgnoreCase("GTA") || conttype.equalsIgnoreCase("GSTA")){ %>
												<th class="text-center">Exit Point MDQ(MMSCM)</th>
											<%} %>
											<th class="text-center">Entry Point Energy</th>
										    <%if(conttype.equalsIgnoreCase("GTA") || conttype.equalsIgnoreCase("GSTA")){ %>
										    	<th class="text-center">Exit Point Energy</th>
										    <%} %>
										    <%--<th class="text-center">Receipt Point Name</th>--%>
			            				</tr>
			            			</thead>
			            			<tbody>
			            				   <%if(flag.equalsIgnoreCase("A"))
										     {
											    int k=0;
											    %>
											    <tr class="focusAdd"><td class="text-left" colspan="8"><b>Add New MDQ(Amendment)</b></td></tr>
											    <%-- <tr>
													<% if(conttype.equalsIgnoreCase("GTA") || conttype.equalsIgnoreCase("GSTA")){%>
													     <td colspan="2" align="center"></td>
													<%}%>
											    </tr> --%>
											   	 <tr class="focusAdd">
												     <td class="text-center">
												     	<b class="mand">*</b>&nbsp;&nbsp;
												        <select name="supcd" style="width: 12em;height: 1.8em;">
												        	<option value="<%=ptyCd%>"> <%= ptyName %></option>
												        </select>
												     </td>
												     <td class="text-left">
												     	  <!-- <b class="mand">*</b>&nbsp;&nbsp; -->
														  <input type="text" name="effdtA" id="effdtA" value="<%=effdtA%>" class="form-control datepick" onchange="validateDate(this);refresh1('<%=ptyCd %>');" title="dd/mm/yyyy Format" autocomplete="off">	
													 </td>
													 <td class="text-center">NHV : <%=nhv %></td>
													 <td class="text-center">GHV : <%=ghv %></td>
										             <%String hv="";
											           if(eBase.equalsIgnoreCase("nhv")){
											               	 hv=nhv;
											           }else if(eBase.equalsIgnoreCase("ghv")){
											            	 hv=ghv;
											           }%>
												     <td>
												          <b class="mand">*</b>&nbsp;&nbsp;
												          <input type="text" name="dcqA"  class="mkRdly" size=10 value="" maxlength=50 onClick="enableA('<%=conttype %>');" style="text-align:right" onBlur="negNumber(this);checkNumber(this,8,6);calcEnergy('<%=hv %>','<%=en_unit %>','<%=conttype %>');" title="Number(2,6)">
												     </td>
												     <%if(conttype.equalsIgnoreCase("GTA") || conttype.equalsIgnoreCase("GSTA")){ %>
												          <td>
												              <b class="mand">*</b>&nbsp;&nbsp;
												              <input type="text" name="mdqA" size=10 value="" maxlength=50  class="mkRdly" onClick="enableA('<%=conttype %>');" style="text-align:right" onBlur="negNumber(this);checkNumber(this,8,6);calcEnergy('<%=hv %>','<%=en_unit %>','<%=conttype %>');" title="Number(2,6)">
												          </td>
												     <%}%>
												     <td class="text-center"><%--  colspan="<%if(conttype.equalsIgnoreCase("GSA")){ %>3 <%} %>" > --%>
												         <b class="mand">*</b>&nbsp;&nbsp;
												         <input type="text" onClick="enableA('<%=conttype %>');"  class="mkRdly" name="energyA" value="" size=12 maxlength=50  style="text-align:right" onBlur="negNumber(this);checkNumber(this,12,4);" title="Number(8,4)">(<%=en_unit %>)
												     </td>
												     <%if(conttype.equalsIgnoreCase("GTA") || conttype.equalsIgnoreCase("GSTA")){ %>
												         <td class="text-center">
												         	<b class="mand">*</b>&nbsp;&nbsp;
												         	<input type="text" name="energy1A"  class="mkRdly"  value="" size=12 maxlength=50 onClick="enableA('<%=conttype %>');" style="text-align:right" onBlur="negNumber(this);checkNumber(this,12,4);" title="Number(8,4)">(<%=en_unit %>)
												         </td>
										 	         <%} %>
										 	     </tr>
									    <%}%>
			            			
			            				<%for(int k = 0; k < ptyNhv.size() ; k++)
			            				  {
										  	ptCodeTot=ptyNhv.size()+"";%>
												<tr>
												 	<%String hv="";
												     String enBase=(String)ptyEnBase.elementAt(k);
												     String enUnit=(String)ptyEnUnit.elementAt(k);
												          
												     if(enBase.equalsIgnoreCase("NHV")){
												       	  hv=(String)ptyNhv.elementAt(k);
												     }else if(enBase.equalsIgnoreCase("GHV")){
												       	  hv=(String)ptyGhv.elementAt(k);
												     }%>
												     
												     <td><input type="text" name="supname" size="25" maxlength="50" value="<%= ptyName %>"  readonly="readonly" class="mkRdly" ></td>
												     <td><input type="text" name="effdt" size="10" maxlength="20" value="<%= (String)ptyEdt.elementAt(k)==null?"":(String)ptyEdt.elementAt(k) %>" readonly="readonly" class="mkRdly"></td>
												     <td>
													      <%if(enBase.equalsIgnoreCase("NHV")){%> 
													       		<b class="mand">*</b>
													      <%}%>&nbsp;&nbsp;&nbsp;       
												           <input type="text" name="nhv" size="8" maxlength="20" value="<%= (String)ptyNhv.elementAt(k)==null?"":(String)ptyNhv.elementAt(k) %>"  readonly="readonly" onBlur="negNumber(this);checkNumber(this,6,1);">
												     </td>
												     <td>
												       	  <%if(enBase.equalsIgnoreCase("GHV")){%>
												       			<b class="mand">*</b>
												       	  <%} %>&nbsp;&nbsp;&nbsp;
												       	  <input type="text" name="ghv" size=8 maxlength=20 value="<%= (String)ptyGhv.elementAt(k)==null?"":(String)ptyGhv.elementAt(k) %>" onBlur="negNumber(this);checkNumber(this,6,1);">
												     </td>
												     <td>
												       	  <b class="mand">*</b>&nbsp;&nbsp;
												       	  <input type="text" name="dcq" size="10" maxlength="20" value="<%= (String)ptyDcq.elementAt(k)==null?"":(String)ptyDcq.elementAt(k) %>"  onBlur="negNumber(this);checkNumber(this,8,6);" title="Number(2,6)">
												     </td>
												     
												     <%if(conttype.equalsIgnoreCase("GTA") || conttype.equalsIgnoreCase("GSTA")){ %>
										             	<td>
										             		<b class="mand">*</b>&nbsp;&nbsp;
										             		<input type="text" name="mdq" size="10" maxlength="20" value="<%= (String)ptyMdq.elementAt(k)==null?"":(String)ptyMdq.elementAt(k) %>"  onBlur="negNumber(this);checkNumber(this,8,6);" title="Number(2,6)">
										             	</td>
										       		 <%} %>
										       		 <td>
										       	   		<b class="mand">*</b>&nbsp;&nbsp;
										       	   		<input type="text" name="energy" size="12" maxlength="20" value="<%= (String)ptyEngy.elementAt(k)==null?"":(String)ptyEngy.elementAt(k) %>" onBlur="negNumber(this);checkNumber(this,12,4);" title="Number(8,4)">&nbsp;&nbsp;(<%=enUnit%>)
										       		 </td>
										       		 <%if(conttype.equalsIgnoreCase("GTA") || conttype.equalsIgnoreCase("GSTA")){ %>
										       			<td>
										       				<b class="mand">*</b>&nbsp;&nbsp;
										       				<input type="text" name="energy1" size="12" maxlength="20" value="<%= (String)ptyEngy1.elementAt(k)==null?"":(String)ptyEngy1.elementAt(k) %>" onBlur="negNumber(this);checkNumber(this,12,4);" title="Number(8,4)">(<%=enUnit%>)
										       			</td>
										       		 <%} %>
										       		 <td style="display: none;"><input type="hidden" name="supcode" size=18 maxlength=50 value="<%=ptyCd%>"></td>          
												</tr>
										  <%}%>
			            			</tbody>
			            		</table>
			            	</div>
			            	
			            	<div class="card-footer">
								<div class="row">
					            	<div class="col-sm-6">
					            	</div>
					            	<div class="col-sm-6 text-right">
					            		<% if(flag.equalsIgnoreCase("M")){%>
					            			<button type="button" class="btn btn-success buttonfoot" name="button" value="<%=subbutton%>" onClick="trackCh();"><%=subbutton%></button>
				            			<%} if(flag.equalsIgnoreCase("A")){%>
				            				<button type="button" class="btn btn-success buttonfoot" name="button" value="Submit" onClick="subm('<%=conttype %>');">Submit</button>
				            			<%}%> 
					            	</div>
				            	</div>
			            	</div>		
			            	
						</div>
					</div>
				</div>
			</div>
		</section>
  
<%for(int i=0 ; i < (ptCd.size()*7+1) ; i++){%> 
	<input type="hidden" name="val" value="-1"> 
<%}%>  
<%for(int i=0 ; i < (ptCd.size()*7+1) ; i++){%>
	<input type="hidden" name="newval" value="-1"> 
<%}%>
<input type="hidden" name="flag" value="<%=flag %>">
<input type="hidden" name="opt" value="<%=opt %>">
<input type="hidden" name="type" value="MDQDetails">
<input type="hidden" name="cont_type" value="<%=conttype %>">
<input type="hidden" name="sbutton" value="<%=subbutton%>">
<input type="hidden" name="comp_cd" value="<%=comp_cd %>">
</div>
<footer class="main-footer">
    <strong>Copyright &copy;2020 <a href="https://barodainformatics.com/BIPL2015/">BIPL</a>.</strong>
    	All rights reserved.
    <div class="float-right d-none d-sm-inline-block">
    	<b>Version</b> 1.0.0
    </div>
</footer>
</form>
<!-- jQuery -->
<script src="../plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="../plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- SweetAlert2 -->
<script src="../plugins/sweetalert2/sweetalert2.min.js"></script>
<!-- Toastr -->
<script src="../plugins/toastr/toastr.min.js"></script>
<!-- Adminlte App -->
<script src="../dist/js/adminlte.min.js"></script>
<!-- Date picker -->
<script src = "../plugins/jquery/jquery.js"></script>
<script src = "../plugins/jquery-ui/jquery-ui.js"></script>
<!-- end date picker -->
<script>
$('.datepick').each(function() {
	$(this).datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat: 'dd/mm/yy'
	});
});

$(function() {
    const Toast = Swal.mixin({
      toast: true,
      position: 'top-end',
      showConfirmButton: false,
      timer: 3000
    });
    
	var msg = '<%=status%>';
	if(msg !='' && msg !=' ' ){
	  toastr.success(msg)
	}
});
</script>
</body>
</html>