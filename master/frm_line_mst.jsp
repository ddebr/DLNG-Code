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
<link rel="stylesheet" href="../dist/css/adminlte.min.css">
<link rel="stylesheet" href="../plugins/toastr/toastr.min.css">
<script type="text/javascript" src="../js/mouseclk.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/nhv.js"></script>

<style type="text/css">
.card-header{
background-color: #ffc107;
}
.card-footer{
background-color: #fff;
color: red;
}
.mand{
color: red;
}
.addnewgroup{
display: none;
}
.addnewgrptxt{
display: none;
}
.addnewlinetxt{
display: none;
}
.addnewline{
display: none;
}
.addnewlineselect{
display: none;
}
</style>

<script type="text/javascript">
  var oldVal=new Array();
  var newVal=new Array();
  var valUpd=new Array();
  var mixVal=new Array();
  var tcompadd1=new Array();
  var newWindow;
  
  flag=true;
   
function enableButton()
{
    document.forms[0].but.disabled=false;
}

function refresh1()
{
   //location.href("frm_mas_others.jsp?flag=M&opt=7");
   var url="frm_line_mst.jsp?flag=M&opt=5";
   location.replace(url);
}

function openForm(str)
{
   if(str=="G")
   {
   		/* if(!newWindow || newWindow.closed)
		{
     		newWindow= window.open("frm_line_grp_mst.jsp","loc","top=10,left=100,width=600,height=400,scrollbars=1");
		}
		else
		{
	    	newWindow.close();
    		newWindow= window.open("frm_line_grp_mst.jsp","loc","top=10,left=100,width=600,height=400,scrollbars=1");
		} */
   		$('#modal-Group').modal('show');
   }
   else if(str=="L")
   {
   		/* if(!newWindow || newWindow.closed)
		{
    		newWindow= window.open("frm_line_mst_add.jsp","loc","width=700,height=550,top=10,left=100,scrollbars=1,resizable=true");
		}
		else
		{
	    	newWindow.close();
    		newWindow= window.open("frm_line_mst_add.jsp","loc","width=700,height=550,top=10,left=100,scrollbars=1,resizable=true ");
		} */
   		$('#modal-line').modal('show');
   }
   else if(str=="C")
   {
   		/* if(!newWindow || newWindow.closed)
		{
    		newWindow= window.open("frm_line_contract_map_mst.jsp","loc","width=900,height=550,top=10,left=100,scrollbars=1 resizable=true");
		}
		else
		{
		    newWindow.close();
    		newWindow= window.open("frm_line_contract_map_mst.jsp","loc","width=900,height=550,top=10,left=100,scrollbars=1 resizable=true");
		} */
		var VConLine=document.forms[0].ConLine;
	    var Vline=document.forms[0].line;
	    // alert(VConLine.length);
	    for(var i=0;i<Vline.length;i++)
	    {
	       for(var j=0;j<VConLine.length;j++)
	       {
	          // alert(document.forms[0].ConLine[j].value);
	          if(document.forms[0].line[i].value==document.forms[0].ConLine[j].value){
	              document.forms[0].line[i].checked=true;
	          }
	       }
		}
		$('#modal-lineToContract').modal('show');
   }
 }
   
function subm()
{
  var b=validate();
  if(b)
  {
       var a=confirm("Do you want to Submit?");
       if(a)
       {
    	   document.forms[0].type.value = "QualityDetails";
 		   if(document.forms[0].type.value != ""){
    		  alert("type : "+document.forms[0].type.value)
    		  document.forms[0].submit();
		      window.opener.refresh1();
    	   }
       }
       else
       {
    	   
           return false;
       }
   }
   else
   {
       return false;
   }    
}	    
   
function validate()
{
    var pty=document.forms[0].pty.value;
    var effdt=document.forms[0].effdt.value;
    var elem=document.forms[0].elements;
    
    var flag=true;
    var compo=document.forms[0].compo;
	    
	    
	if(pty==" "||pty=="")
	{
	    alert("Please select the Party First");
	    return false;
	} 
	    
	if(effdt==" "||effdt=="")
	{
	    alert("Filling Effective Date is mandatory");
	    return false;
	}
	
	for(i=0;i<elem.length;i++)
	{
        if(elem[i].type=='checkbox')
        {
             if(elem[i].checked){
                flag=false;
             }	    
	    }
	    if(flag)
	    {
	       alert("You need to select minimum one Quality Paramter");
	       return false;
	    }
	    return true;
    }
}
   
function setStatus()
{
  pty=document.forms[0].pty.value;
  effdt=document.forms[0].effdt.value;
  var url="frm_mas_others.jsp?flag=M&opt=5&pty="+pty+"&edt="+effdt;
  location.replace(url);	
}
		
function setStatusDate()
{
  effdt=document.forms[0].effdt.value;
  var url="frm_mas_others.jsp?flag=M&opt=5&edt="+effdt;
  location.replace(url);	
}

function setFlag()
{  
	var btnid = (event.target.id);
	if(btnid=="groupBtn"){
		document.getElementsByClassName("addnewgrptxt")[0].style.display = "block";
		document.getElementsByClassName("addnewgroup")[0].style.display = "block";
		document.forms[0].b11.style.visibility='visible';
	}else if(btnid=="lineBtn"){
		document.getElementsByClassName("addnewlinetxt")[0].style.display = "block";
		document.getElementsByClassName("addnewline")[0].style.display = "block";
		document.getElementsByClassName("addnewlineselect")[0].style.display = "block";
		document.forms[0].b14.style.visibility='visible';
	}else if(btnid=="lineToContractBtn"){
		document.getElementsByClassName("addnewlinetxt")[0].style.display = "block";
		document.getElementsByClassName("addnewline")[0].style.display = "block";
		document.forms[0].b15.style.visibility='visible';
	}
}
function mandatory()
{
   var flag=true;
   var elem=document.forms[0].elements;
   
   for(i=0 ; i < elem.length ; i++)
   {
      if(elem[i].type == 'text')
      {
         if(elem[i].value == "" || elem[i].value == " ")
         {
            alert("No field can be left blank");
            flag=false;
         }
      }
   }
   return flag;
}
function trackCh()
{
	k=0;
    elem=document.forms[0].elements;
    flag=true;
    for(i=0 ; i<elem.length ; i++)
    {
       if(elem[i].type=='text')
       {
	newVal[k++]=elem[i].value;
       }
    }
     
    m=0;
    for(g=0 ; g < k ; g++)
    {
        if(oldVal[g] != newVal[g])
        {
             flag=false;
        }
    }
   
    if(!flag){}  
    else
    {
     	 alert("No Changes have been made");
    }    
    return flag;
}

function trackUnique()
{
	elem=document.forms[0].elements;
    for(i=0 ; i< elem.length ; i++)
    {
        if(elem[i].type=='text' && (elem[i].name=='groupname' || elem[i].name=='groupnameA' ))
        {
            for(j=i+1 ; j < elem.length ; j++)
            {
                 if(elem[j].type=='text' && (elem[j].name=='groupname'  || elem[j].name=='groupnameA'))
              	 {   
	                 if(elem[i].value == elem[j].value)
	                 {
	                      alert("Group Description has to be unique");
	                      return false;
	                 }
               	 }
            }
        }
   }
   return true;
}

function submits()
{
	var btnid = (event.target.id);
	if(btnid == "addnewlineToContractBtn")
    {
		var flag=false;
	    var Vline=document.forms[0].line;
	    for(var i=0;i<Vline.length;i++)
	    {
	       if(Vline[i].checked)
	       {
	          document.forms[0].line_Map[i].value=document.forms[0].line[i].value;
	          document.forms[0].line_Flag[i].value="Y";
	          flag=true;
	       }
	       else
	       {
	          document.forms[0].line_Map[i].value=document.forms[0].line[i].value;
	       }
	    }
	          
		if(flag)
		{
		      var a=confirm("Do you want to Submit?");
		      if(a)
		      {
		          document.forms[0].type.value = "Linetocontractlink";
		 		  if(document.forms[0].type.value != ""){
		    		  document.forms[0].action="../servlet/Frm_line_mst";
		    		  document.forms[0].submit();
				      window.opener.refresh1(); 
		    	  }
		         
		      }
		      else
		      {
		         return false;
		      }
		}
		else
		{
		  alert("Please Select Line")
		}
    }
	else
	{
		if(!trackUnique()){
	     	return false;
	  	}   
	  	else if(!mandatory()){
	    	return false;
	  	}
	  	else if(trackCh()){
	    	return false;
	  	}
	  	else
	  	{        
		      var a=confirm("Do you want to Submit?");
		      if(a)
		      {
		    	  if(btnid == "addnewgrpSubBtn" || btnid == "addnewgrpBtn"){
		    		  document.forms[0].type.value = "LineGroupDetails";
		  	      }
		  		  else if(btnid == "addnewlinSubBtn" || btnid == "addnewlineBtn"){
		  			  document.forms[0].type.value = "LineDetails";
		  	      }
		    	  
		    	  if(document.forms[0].type.value != ""){
		    		  document.forms[0].action="../servlet/Frm_master_others";
		    		  document.forms[0].submit();
				      window.opener.refresh1();
		    	  }
		      }
		      else
		      {
		         return false;
		      }
	  	}
	}
}
</script>
</head>

<jsp:useBean class="com.Gsfc.Baroda.fms6.master.DataBean_line_mst" id="masOthers" scope="page"/>
<jsp:useBean id="Util" class="com.Gsfc.Baroda.fms6.util.UtilBean" scope="page"/>
<%

String comp_cd=session.getAttribute("comp_cd").toString();
Util.init();
String today_dt = Util.getGen_dt();
String tom_dt = Util.getDate_tomorrow();
String pcd=request.getParameter("pty")==null?"":request.getParameter("pty");
String edt=request.getParameter("edt")==null?today_dt:request.getParameter("edt");
String status=request.getParameter("status")==null?"":request.getParameter("status");

String message=request.getParameter("message")==null?"":request.getParameter("message");
String errormessage=request.getParameter("errormessage")==null?"":request.getParameter("errormessage");

masOthers.setCompany_cd(comp_cd);
masOthers.setCallFlag("LineDetails");    
masOthers.init();

Vector VGline_id=masOthers.getVGline_id();
Vector ptyId = masOthers.getVparty_cd();
Vector ptyName = masOthers.getVparty_name();
Vector VGgroup_nm=masOthers.getVGgroup_nm();
Vector VCParty=masOthers.getVCParty();

Vector Vgroup_cd=masOthers.getVgroup_cd();
Vector Vgroup_name=masOthers.getVgroup_name();

Vector Vline_cd=masOthers.getVline_cd();
Vector Vline_name=masOthers.getVline_name();

masOthers.setCallFlag("Contract_Names");
masOthers.setCompany_cd(comp_cd);
masOthers.setCurrent_date(today_dt);
masOthers.init();

Vector CNparty_cd=masOthers.getCNparty_cd();
Vector Vparty_abr=masOthers.getVparty_abr();
Vector contract_name=masOthers.getContract_name();
Vector vmappingid=masOthers.getVmappingid();

masOthers.setCompany_cd(comp_cd);
masOthers.setCallFlag("LineDescription");
masOthers.init();
Vector CNline_cd=masOthers.getCNline_cd();
Vector CNline_name=masOthers.getCNline_name();

/* System.out.println(CNparty_cd.size()+"==="+Vparty_abr.size()+"==="+contract_name.size());
System.out.println(CNline_cd.size()+"=="+CNline_cd.size()+"=="+CNline_name.size());
System.out.println("CNline_cd : "+CNline_cd.size());
System.out.println("CNline_name : "+CNline_name.size()); */

masOthers.setCompany_cd(comp_cd);
masOthers.setCallFlag("LinetocontractDescription");
masOthers.init();
Vector Vlcine_cd=masOthers.getVlcine_cd();
Vector VlcMAPPING_ID=masOthers.getVlcMAPPING_ID();
%>  

<body>
<%@ include file="../home/header.jsp" %>
<form name="mainview" method="post" action="">
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    	<section class="content-header">
      		<div class="container-fluid">
        		<div class="row mb-2">
          			<div class="col-sm-8">
            			<h1>Master : Line Management<%//=formName%></h1>
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
								<div class="card-title"><h4>Lines</h4></div>
								<div class="card-tools" >
	                			</div>
							</div>
							<div class="card-body table-responsive p-0" style="height: 320px;">
				            	<table class="table table-head-fixed text-nowrap table-bordered">
				            		<thead>
				            			<tr>
				            				<th><b>Sr. No</b></th>
											<th><b>Group</b></th>
											<th><b>Line Details</b></th>
											<th><b>Contract Details</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<button type="button" class="btn btn-info" name="button" value="Add/Modify Group" onClick="openForm('G');">Add | Modify Group</button>
								  				<button type="button" class="btn btn-info" name="button" value="Add/Modify Line" onClick="openForm('L');">Add | Modify Line</button>
									  			<button type="button" class="btn btn-info" name="button" value="Line to Contract Allocation" onClick="openForm('C');">Line to Contract Allocation</button>
											</th>
				            			</tr> 
				            		</thead>
				            		<tbody>
				            			<%for(int k = 0; k < Vline_cd.size() ; k++){%>
											<tr>
												<td><%= k+1 %></td>
											    <td>&nbsp;&nbsp;<b><%= (String)VGgroup_nm.elementAt(k)==null?"":(String)VGgroup_nm.elementAt(k) %></b></td>
											    <td>&nbsp;&nbsp;<%=(String)Vline_name.elementAt(k)==null?"":(String)Vline_name.elementAt(k) %></td>
											    <td>&nbsp;&nbsp;<%=(String)VCParty.elementAt(k)==null?"":(String)VCParty.elementAt(k) %></td>
											</tr>
										<%}%>
				            		</tbody>
				            	</table>
				            </div>
				            	
			            	<div class="card-footer">
				            	Step 1 : Assign  Group. &nbsp;&nbsp;&nbsp;And&nbsp;&nbsp;&nbsp; Assign Line on the basis of assigned Group.<br>Step 2 : Allocate Line to the Active Contracts.
			            	</div>
				        </div>
				    </div>
				</div>
			</div>
	</section>
	
	<div class="modal fade" id="modal-Group">
       	<div class="modal-dialog modal-dialog-scrollable">
       		<div class="modal-content">
         		<div class="modal-header">
           			<h5 class="modal-title">Group Details</h5>
           			<button name="add" type="button" id="groupBtn" class="btn btn-info btn-sm text-right" value="Add New" onclick="setFlag();">Add New</button>
         		</div>
         		<div class="modal-body">
         			<div class="table-responsive">
            			<table class="table table-bordered">
                			<thead>
                    			<tr>
	                      			<th class="text-center">Sr No.</th>
	                      			<th class="text-center">Group Description</th>
                    			</tr>
                  			</thead>
                  			<tbody>
                    			<tr>
		                  			<td colspan="2">
		                  				<table class="table-borderless">
		                  					<tr>
		                  						<td><b class="mand addnewgroup">*</b></td>
		                  						<td>
		                  							<input type="hidden" name="groupcdA" value="<%=Vgroup_cd.size()+1%>">
		         									<input type="text" name="groupnameA" value="" size="30" maxlength="50" onClick="enableA();" title="Max Size 50 chars" class="addnewgrptxt">
		                  						</td>
		                  						<td>
		                  							<button name="b11" type="button" class="btn btn-success btn-sm" id="addnewgrpSubBtn" value="Submit" onclick="submits();" style="visibility: hidden;">Add</button>
		                  						</td>  
		                  					</tr>
		                  				</table>
		                  			</td>
                  				</tr>
                  				<%for(int i=0;i<Vgroup_cd.size();i++) {%>
		                  			<tr>
				                  		<td><%=Vgroup_cd.elementAt(i) %></td>
			                  			<td>
			                  				<b class="mand">*</b>&nbsp;&nbsp;
			                  				<input type="hidden" name="groupcd" value="<%=Vgroup_cd.elementAt(i)%>">
									        <input type="text" name="groupname" size="30" maxlength="50" value="<%=Vgroup_name.elementAt(i) %>" title="Max Size 50 chars">
			                  			</td>		
	                  				</tr>
								<%} %>		 
          					</tbody>
          				</table>
         			</div>
         		</div>
           		
       			<div class="modal-footer">
         			<button name="button" type="button" class="btn btn-defualt btn-sm" value="Refresh" onclick="refresh();">Refresh</button>
         			<button type="button" class="btn btn-success btn-sm toastrDefaultSuccess" id="addnewgrpBtn" onClick="submits();" value="Submit">Save</button>  
       			</div>
        	</div>
    	</div>
	</div>
	
	<div class="modal fade" id="modal-line">
       	<div class="modal-dialog modal-lg modal-dialog-scrollable">
       		<div class="modal-content">
         		<div class="modal-header">
           			<h5 class="modal-title">Line Details</h5>
           			<button name="add" type="button" id="lineBtn" class="btn btn-info btn-sm text-right" value="Add New" onclick="setFlag();">Add New</button>
         		</div>
         		<div class="modal-body">
         			<div class="table-responsive">
            			<table class="table table-bordered">
                			<thead>
                    			<tr>
	                      			<th class="text-center">Sr No.</th>
	                      			<th class="text-center">Group</th>
	                      			<th class="text-center">Line Description</th>
                    			</tr>
                  			</thead>
                  			<tbody>
                    			<tr>
              						<td class="text-right" colspan="3">
              						    <table class="table-borderless">
		                  					<tr>
		                  						<td></td><td></td><td></td><td></td><td></td>
		                  						<td>
		                  							<select name="groupA" class="addnewlineselect" onfocus="">
														<option value="0">--Select--</option>
														<%for(int j = 0; j < Vgroup_cd.size() ; j++){%>
															<option value="<%=Vgroup_cd.elementAt(j)%>"><%=Vgroup_name.elementAt(j)%></option>
														<%} %>
													</select>
		                  						</td>
		                  						<td></td><td></td>
		                  						<td><b class="mand addnewline">*</b></td>
		                  						<td>
		                  							<input type="hidden" name="linecdA" value="<%=Vgroup_cd.size()+1%>">
     												<input type="text" name="linenameA" value="" size="30" maxlength="50" title="Max Size 50 chars" class="addnewlinetxt">
		                  						</td>
		                  						<td><button name="b14" type="button" class="btn btn-success btn-sm" id="addnewlinSubBtn" value="Submit" onclick="submits();" style="visibility: hidden;">Add</button></td>
		                  					</tr>
		                  				</table>
              						</td>
                  				</tr>
                  				<%for(int i=0;i<Vline_cd.size();i++) {
                  					String gp_cd=VGline_id.elementAt(i)+"";
                  					%>
		                  			<tr>
				                  		<td><%=Vline_cd.elementAt(i) %></td>
				                  		<td>
								 			<select name="group">
												<option value="0">--Select--</option>
												<%for(int j= 0; j < Vgroup_cd.size() ; j++){%>
													<%if(gp_cd.equalsIgnoreCase(Vgroup_cd.elementAt(j).toString())){ %>
														<option selected="selected" value="<%=Vgroup_cd.elementAt(j)%>"><%=Vgroup_name.elementAt(j)%></option>
													<%}else{%>
														<option value="<%=Vgroup_cd.elementAt(j)%>"><%=Vgroup_name.elementAt(j)%></option>
													<%} %>
												<%} %>
											</select>
								 		</td>
			                  			<td>
			                  				<b class="mand">*</b>&nbsp;&nbsp;
			                  				<input type="hidden" name="linecd" value="<%=Vline_cd.elementAt(i)%>">
									        <input type="text" name="linename" size="30" maxlength="50" value="<%=Vline_name.elementAt(i) %>" title="Max Size 50 chars">
			                  			</td>		
	                  				</tr>
								<%} %>		 
          					</tbody>
          				</table>
         			</div>
         		</div>
           		
       			<div class="modal-footer">
         			<button name="button" type="button" class="btn btn-defualt btn-sm" value="Refresh" onclick="refresh();">Refresh</button>
         			<button type="button" class="btn btn-success btn-sm toastrDefaultSuccess" id="addnewlineBtn" onClick="submits();" value="Submit">Save</button>  
       			</div>
        	</div>
    	</div>
	</div>
	
	<div class="modal fade" id="modal-lineToContract">
       	<div class="modal-dialog modal-lg modal-dialog-scrollable">
       		<div class="modal-content">
         		<div class="modal-header">
           			<h5 class="modal-title">Line To Contract Details</h5>
         		</div>
         		<div class="modal-body">
         			<div class="table-responsive">
            			<table class="table table-bordered">
                			<thead>
                    			<tr>
                    				<th class="text-center">LINE</th>
	                      			<th class="text-center" colspan="<%=CNline_name.size()%>">Contract</th>
                    			</tr>
                  			</thead>
                  			<tbody>
                  				<%for(int i=0;i<CNparty_cd.size();i++) {%>
		                  			<tr style="background-color:#ECF8F8;">
				                  		<td><%=Vparty_abr.elementAt(i) %>-<%=contract_name.elementAt(i) %></td>
				                  		<td colspan="<%=CNline_name.size()%>"></td>
				                  	</tr>
				                  	<tr>
				                  	<td></td>
				                	<%for(int k = 0; k < CNline_cd.size() ; k++){ %>
				                  		<td title="<%=vmappingid.elementAt(i)+"="+CNline_cd.elementAt(k)%>">
    										<input type="checkbox" name="line" value="<%=vmappingid.elementAt(i)+"="+CNline_cd.elementAt(k)%>">&nbsp;&nbsp;&nbsp;<%=CNline_name.elementAt(k) %>
    										<input type="hidden" name="line_Map" value="0">
    										<input type="hidden" name="line_Flag" value="N">
								 		</td>		
									<%} %>
									</tr>
								<%}%> 
								<% for(int i=0;i<Vlcine_cd.size();i++){%><tr style="display: none;"><td><input type=hidden name="ConLine" value="<%= VlcMAPPING_ID.elementAt(i).toString()+"="+Vlcine_cd.elementAt(i).toString() %>"></td></tr><% }%>		 
          					</tbody>
          				</table>
         			</div>
         		</div>
           		
       			<div class="modal-footer ">
         			<button name="button" type="button" class="btn btn-defualt btn-sm" value="Refresh" onclick="refresh();">Refresh</button>
         			<button type="button" class="btn btn-success btn-sm toastrDefaultSuccess" id="addnewlineToContractBtn" onClick="submits();" value="Add/Modify">Add | Modify</button>  
       			</div>
        	</div>
    	</div>
	</div>
	
<input type="hidden" name="opt" value="6">
<input type="hidden" name="type" value="">
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
<script type="text/javascript">
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