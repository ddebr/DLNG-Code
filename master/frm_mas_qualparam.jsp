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
<title>FMS | Master Quality Parameters</title>

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
.addNewUnitA{
display:none;
}
.addNewUnitD{
display:none;
}
.addNewGroupA{
display:none;
}
.addNewGroupC{
display:none;
}
.addNewQuGroupA{
display:none;
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
  //var url="frm_mas_others.jsp?flag=M&opt=7";//location.replace(url);
  var url="frm_mas_others.jsp?flag=M&opt=5";
  location.replace(url);
}
function openForm(str)
{
   
   if(str=="U")
   {
	   /*  if(!newWindow || newWindow.closed)
	   {
	    	newWindow= window.open("frm_mas_qunit_dtl.jsp","loc","top=0,left=0,width=600,height=500,scrollbars=1");
 	   }
	   else
	   {
 	    	newWindow.close();
	    	newWindow= window.open("frm_mas_qunit_dtl.jsp","loc","top=0,left=0,width=600,height=500,scrollbars=1");
	   } */ 
	   $('#modal-Unit').modal('show');
   }
   
   if(str=="G")
   {
	    /* if(!newWindow || newWindow.closed)
		{
		     newWindow= window.open("frm_mas_groupdetails.jsp","loc","top=10,left=100,width=600,height=400,scrollbars=1");
	 	}
		else
		{
	 	    newWindow.close();
		    newWindow= window.open("frm_mas_groupdetails.jsp","loc","top=10,left=100,width=600,height=400,scrollbars=1");
		} */
	    $('#modal-QualityGroup').modal('show');
   }
   else if(str=="Q")
   {
	    /* if(!newWindow || newWindow.closed)
		{
		    newWindow= window.open("frm_mas_qualitydetails.jsp","loc","width=700,height=550,top=10,left=100,scrollbars=1 resizable=true");
	 	}
		else
		{
	 	    newWindow.close();
		    newWindow= window.open("frm_mas_qualitydetails.jsp","loc","width=700,height=550,top=10,left=100,scrollbars=1 resizable=true");
		} */
	    $('#modal-Component').modal('show');
   }
}

function setFlag(flg)
{
  	if(flg == "AU"){
  		document.getElementsByClassName("addNewUnitA")[0].style.display = "block";
  		document.getElementsByClassName("addNewUnitD")[0].style.display = "block";
  		document.forms[0].unitAbrA.style.visibility='visible';
  		document.forms[0].unitDescA.style.visibility='visible';
  	}
  	else if(flg == "AG"){
  		document.getElementsByClassName("addNewGroupA")[0].style.display = "block";
  		document.getElementsByClassName("addNewGroupC")[0].style.display = "block";
  		document.forms[0].groupnameA.style.visibility='visible';
  	}
  	else if(flg == "AC"){
  		document.getElementsByClassName("addNewQuGroupA")[0].style.display = "block";
  		document.forms[0].qual_nameA.style.visibility='visible';
  		document.forms[0].groupcodeA.style.visibility='visible';
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
         if(elem[i].type=='text'){
			newVal[k++]=elem[i].value;
         }
    }
      
    m=0;
    for(g=0 ; g < k ; g++)
    {
          if(oldVal[g] != newVal[g]){
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

function checkMinMax()
{
  minval=document.forms[0].min_val;
  maxval=document.forms[0].max_val;
  min_valA=document.forms[0].min_valA;
  max_valA=document.forms[0].max_valA;
  
  flag=true;
  if(minval[0]==null)
  {
      if(parseInt(maxval.value)<parseInt(minval.value)){
    	  flag=false;  
      }
  }
  else
  {
	  for(i=0;i<minval.length;i++)
      {
		  if(parseInt(maxval[i].value) < parseInt(minval[i].value) ){
          	flag=false;
          }
      }
  }
  if(parseInt(max_valA) < parseInt(min_valA)){
	  flag=false;
  }
    
  if(!flag){
    alert("Maximum Value cannot be less than Minimum Value");
  }  
  return flag;
}


function enabledelete(){
	document.forms[0].delbutton.disabled=false;
}

function delunit()
{	
	len=document.forms[0].deleteunit.length;	
	cnt=0;
	for(i=0;i<len;i++)
	{
		if(document.forms[0].deleteunit[i].checked==true){
			cnt++;
		}
	}
	
	if(cnt==0){
		alert('You have to select minimum one unit to delete.');
		return;		
	}	
	
	document.forms[0].deleteflag.value="yes";
	document.forms[0].submit();
}

function submits()
{
	var btnid = (event.target.id);	
	if(btnid !="")
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
		    	  if(btnid=="btnUnit"){
		    		  document.forms[0].type.value = "QUALITYUnitDetails";		
		    	  }
		    	  else if(btnid=="btnGroup"){
		    		  document.forms[0].type.value = "GroupDetails";
		    	  }
				  else if(btnid=="btnQualityGroup"){
					  if(!checkMinMax()){
					        return false;
					  }
					  document.forms[0].type.value = "GroupCodeDetails";
		    	  }
		    	  
		    	  if(document.forms[0].type.value !=""){
		    		  	alert(document.forms[0].type.value);
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
	else
	{
		var b=validate();
	    if(b)
	    {
	    	var a=confirm("Do you want to Submit?");
		    if(a)
		    {
		    	document.forms[0].type.value = "QualityDetails";
		        document.forms[0].submit();
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
}	    

function validate()
{
    var pty=document.forms[0].pty.value;
    var effdt=document.forms[0].effdt.value;
    var elem=document.forms[0].elements;
    var compo=document.forms[0].compo;
    var flag=true;
    
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
           if(elem[i].checked)
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
</script>
</head>

<jsp:useBean class="com.Gsfc.Baroda.fms6.master.DataBean_master_others" id="masOthers" scope="page"/>
<jsp:useBean id="Util" class="com.Gsfc.Baroda.fms6.util.UtilBean" scope="page"/>
<%
Util.init();
String today_dt = Util.getGen_dt();
String tom_dt = Util.getDate_tomorrow();
String comp_cd=(String)session.getAttribute("comp_cd");//JHP
String pcd=request.getParameter("pty")==null?"":request.getParameter("pty");
String edt=request.getParameter("edt")==null?today_dt:request.getParameter("edt");
String status=request.getParameter("status")==null?"":request.getParameter("status");

masOthers.setSetPartyCd(pcd);
masOthers.setSetEffDate(edt);
masOthers.setCompany_cd(comp_cd);
masOthers.setCallFlag("QualityDetails");
masOthers.init();
  
String[] hc_grp_name=masOthers.getHc_grp_name();
String[][] qua_cd=masOthers.getQua_cd();
String[][] qua_name=masOthers.getQua_name();
String[][] qua_tick=masOthers.getQua_tick();

masOthers.setCompany_cd(comp_cd);
masOthers.setCallFlag("UnitGroupDetails");
masOthers.init();
Vector qulunitcd=masOthers.getQul_unit_cd();
Vector qulunitnm=masOthers.getQul_unit_nm();
Vector qulunitabr=masOthers.getQul_unit_abr();
Vector qulunitgrpcd=masOthers.getQul_unit_grp_cd();


masOthers.setCompany_cd(comp_cd);
masOthers.setCallFlag("GroupDetails");
masOthers.init();
Vector Vgroup_cd=masOthers.getVgroup_cd();
Vector Vgroup_name=masOthers.getVgroup_name();


masOthers.setCompany_cd(comp_cd);
masOthers.setCallFlag("QualityCodeDetails");
masOthers.init();	
Vector VQualitygroup_cd=masOthers.getVgroup_cd();
Vector VQualitygroup_name=masOthers.getVgroup_name();
Vector vunitcd=masOthers.getVunitcd();
Vector Vqual_cd=masOthers.getVqual_cd();
Vector Vqual_name=masOthers.getVqual_name();
Vector Vmin_val=masOthers.getVmin_val();
Vector Vmax_val=masOthers.getVmax_val();
Vector Vhc_group_cd=masOthers.getVhc_group_cd();
Vector vqulunitcd=masOthers.getVqunitcd();
Vector vqulunitabr=masOthers.getQul_unit_abr();
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
            			<h3>Master Entry : Quality Components<%//=formName%></h3>
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
								<div class="card-title"><h4>Quality Components</h4></div>
								<div class="card-tools" > 
	                			</div>
							</div>
							<div class="card-body table-responsive p-0" style="height: 400px;">
				            	<table class="table table-head-fixed text-nowrap table-bordered">
				            		<thead>
				            			<tr>
				            				<th>Sr. No</th>
											<th>
												<div class="row">
													<div class="col-sm-6">Quality Parameters</div>
													<div class="col-sm-6 text-right">
														<button type="button" class="btn btn-info" name="button" value="Add Units" onClick="openForm('U');">Add Units</button>
														<button type="button" class="btn btn-info" name="button" value="Add/Modify Quality Group" onClick="openForm('G');">Add | Modify Quality Group</button>
														<button type="button" class="btn btn-info" name="button" value="Add/Modify Component" onClick="openForm('Q');">Add | Modify Component</button>
													</div>
												</div>
											</th>
				            			</tr>
				            		</thead>
				            		<tbody>
				            			<%int cnt=0;
							              for(int i=0; i < qua_cd.length ;i++)
							              {
							              		for(int j=0 ; j < qua_cd[i].length ; j++)
							              		{
							                		 if(qua_cd[i][j]!=null)
							                		 {
							                			 if(j==0)
							                			 {%>
							                				 <tr><td><%=hc_grp_name[i]%></td></tr>
							                		   <%}%>	
						                                           
							                          	<tr>
							                               <td> <b> <%=++cnt %> </b></td> 
							                               <td><b>&nbsp;&nbsp;&nbsp;<%=qua_name[i][j] %></b></td>
								                       	</tr>
						                          <%
							                		 }
							                	 }          
							              }%>
							              <!-- <tr id="but"><td colspan="4">  </td></tr> --> 
				            		</tbody>
				            	</table>
				            </div>
				       </div>
          		</div>
          	</div>
        </div>
   </section>
   
   <div class="modal fade" id="modal-Unit">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
            	<div class="modal-header">
            		<h4 class="modal-title">Add Units</h4>
	              	<button type="button" class="btn btn-info btn-sm" name="add" value="" onClick="setFlag('AU');">Add New</button>
            	</div>
            	<div class="modal-body">
            		<div class="table-responsive p-0">
		            	<table class="table table-head-fixed text-nowrap table-bordered">
		                	<thead>
		                    	<tr>
			                      	<th class="text-center">Unit Abr</th>
			                      	<th class="text-center">Unit Description</th>
		                    	</tr>
		                  	</thead>
		                  	<tbody>
		                  		<tr>
		                  			<td colspan="2">
		                  				<table class="table-borderless">
		                  					<tr>
		                  						<td style="padding-left: 10px;"><b class="mand addNewUnitA">*</b></td>
		                  						<td><input type="text" name="unitAbrA" value="" size="20" maxlength="50" title="Max Size 50 chars" style="visibility: hidden;"></td>
		                  						
												<td style="padding-left: 160px;"><b class="mand addNewUnitD">*</b>
												<td><input type="text" name="unitDescA" value="" size="20" maxlength="50" title="Max Size 50 chars" style="visibility: hidden;"></td>
	  										</tr>
	  									</table>
	  								</td>
		                  		</tr>
		                  		<%for(int k = 0; k < qulunitcd.size() ; k++){%>
			                  		<tr>
			                  			<td>
	               							 <input type="checkbox" name="deleteunit"  value="<%=qulunitcd.elementAt(k)%>" onclick="enabledelete();" title="Select to delete">
	               							 <b class="mand">*</b>&nbsp;&nbsp;
	               							 <input type="text" name="unitAbr" size="20" maxlength="50" value="<%=qulunitabr.elementAt(k)%>" title="Max Size 50 chars">
	               						</td>
	               						<td>
	               							 <b class="mand">*</b>&nbsp;&nbsp;
		    								 <input type="text" name="unitDesc" size="20" maxlength="50" value="<%=qulunitnm.elementAt(k)%>" title="Max Size 50 chars">
	       									 <input type="hidden" name="unit_cd" value="<%=qulunitcd.elementAt(k)%>" >
	               						</td>
			                  		</tr>
			                  	<%} %>
		                  	</tbody>
		                </table>
		            </div> 
            	</div>
            	<div class="modal-footer">
					<button type="button" class="btn btn-default btn-sm text-right"  onClick="refresh();" value="refresh">Refresh</button>
            	    <button type="button" class="btn btn-success btn-sm toastrDefaultSuccess" id="btnUnit" onClick="submits();" value="Submit">Submit</button>
            	</div>
          	</div>
        </div>
    </div>
    
    <div class="modal fade" id="modal-QualityGroup">
		<div class="modal-dialog modal-lg modal-dialog-scrollable">
			<div class="modal-content">
            	<div class="modal-header">
            		<h4 class="modal-title">Add Quality Groups</h4>
	              	<button type="button" class="btn btn-info btn-sm" name="add" value="" onClick="setFlag('AG');">Add New</button>
            	</div>
            	<div class="modal-body">
            		<div class="table-responsive p-0">
		            	<table class="table table-head-fixed text-nowrap table-bordered">
		                	<thead>
		                    	<tr>
			                      	<th class="text-center">Group Code</th>
			                      	<th class="text-center">Group Description</th>
		                    	</tr>
		                  	</thead>
		                  	<tbody>
		                  		<tr>
		                  			<td colspan="2">
		                  				<table class="table-borderless">
		                  					<tr>
		                  						<td class="addNewGroupC"><%=Vgroup_cd.size()+1%></td>
		                  						<td><input type="hidden" name="groupcdA" value="<%=Vgroup_cd.size()+1%>"></td>
		                  						
												<td style="padding-left: 120px;"><b class="mand addNewGroupA">*</b>
												<td><input type="text" name="groupnameA" value="" size="30" maxlength="50" title="Max Size 50 chars" style="visibility: hidden;"></td>
	  										</tr>
	  									</table>
	  								</td>
		                  		</tr>
		                  		<%for(int k = 0; k < Vgroup_cd.size() ; k++){%>
			                  		<tr>
			                  			<td><%=Vgroup_cd.elementAt(k)%><input type="hidden" name="groupcd" value="<%=Vgroup_cd.elementAt(k)%>"></td>
	               						<td>
	               							 <b class="mand">*</b>&nbsp;&nbsp;
	               							 <input type="text" name="groupname" size="35" maxlength="50" value="<%=Vgroup_name.elementAt(k)%>" title="Max Size 50 chars">
	               						</td>
			                  		</tr>
			                  	<%} %>
		                  	</tbody>
		                </table>
		            </div> 
            	</div>
            	<div class="modal-footer">
					<button type="button" class="btn btn-default btn-sm text-right"  onClick="refresh();" value="refresh">Refresh</button>
            	    <button type="button" class="btn btn-success btn-sm toastrDefaultSuccess" id="btnGroup" onClick="submits();" value="Submit">Submit</button>
            	</div>
          	</div>
        </div>
    </div>
    
    <div class="modal fade" id="modal-Component">
		<div class="modal-dialog modal-lg modal-dialog-scrollable">
			<div class="modal-content">
            	<div class="modal-header">
            		<h4 class="modal-title">Add Quality Details</h4>
	              	<button type="button" class="btn btn-info btn-sm" name="add" value="" onClick="setFlag('AC');">Add New</button>
            	</div>
            	<div class="modal-body">
            		<div class="table-responsive p-0">
		            	<table class="table table-head-fixed text-nowrap table-bordered">
		                	<thead>
		                    	<tr>
			                      	<th class="text-center">Quality Description</th>
			                      	<th class="text-center">Group Name</th>
		                    	</tr>
		                  	</thead>
		                  	<tbody>
		                  		<tr>
		                  			<td colspan="2">
		                  				<table class="table-borderless">
		                  					<tr>
		                  						<td class="text-center">
		                  							<input type="text" name="qual_nameA" size="30" maxlength="80" value="" title="Max Size 50 chars" style="visibility: hidden;"> 
    												<input type="hidden" name="min_valA" value="0"> 
    												<input type="hidden" name="max_valA" value="0">
		                  						</td>
		                  						
												<td class="text-center" style="padding-left: 70px;"><b class="mand addNewQuGroupA">*</b>
												<td>
													<select name="groupcodeA" style="visibility: hidden;">
											            <%for(int i=0 ; i < Vgroup_cd.size() ; i++){
											                   String grp_cd=(String)Vgroup_cd.elementAt(i);
											                   String grp_name=(String)Vgroup_name.elementAt(i);
											                    %><option value="<%=grp_cd %>"><%=grp_name %></option><%
												         }%>
	         										</select>
	         										<input type="hidden" name="qunitcodeA" value="1">
												</td>
	  										</tr>
	  									</table>
	  								</td>
		                  		</tr>
		                  		<%for(int k = 0; k < Vqual_cd.size() ; k++){
	    							String tqualcd=(String)Vqual_cd.elementAt(k);
								    String tqualname=(String)Vqual_name.elementAt(k);
								    String tmin_val=(String)Vmin_val.elementAt(k);
								    String tmax_val=(String)Vmax_val.elementAt(k);
								    String thc_group_cd=(String)Vhc_group_cd.elementAt(k);
								 %>
								 	<tr>
								 		<td class="text-center">
								 			<input type="text" name="qual_name" size="30" maxlength="80" value="<%=tqualname %>" title="Max Size 50 chars">
								 			<input type="hidden" name="qual_cd" value="<%=tqualcd%>">
								 			<input type="hidden" name="min_val" value="0"> 
							 	         	<input type="hidden" name="max_val" value="0"> 
								           	<input type="hidden" name="hc_group_cd" value="<%=thc_group_cd %>"> 
								 		</td>
								 		<td class="text-center">
								 			<select name="groupcode">
									            <%for(int i=0 ; i < Vgroup_cd.size() ; i++)
										          {
									                   String grp_cd=(String)Vgroup_cd.elementAt(i);
									                   String grp_name=(String)Vgroup_name.elementAt(i);
									                   if(thc_group_cd.equalsIgnoreCase(grp_cd)){%>
									                   		<option value="<%=grp_cd %>" selected="selected"><%=grp_name %></option>
									                 <%}else{%>
									                        <option value="<%=grp_cd %>"><%=grp_name %></option>
									                 <%}
										          }%>
									         </select>
									         <input type="hidden" name="qunitcode" value="1">
								 		</td>
								 	</tr>
	    						<%} %>
		                  	</tbody>
		                </table>
		            </div> 
            	</div>
            	<div class="modal-footer">
					<button type="button" class="btn btn-default btn-sm text-right"  onClick="refresh();" value="refresh">Refresh</button>
            	    <button type="button" class="btn btn-success btn-sm toastrDefaultSuccess" id="btnQualityGroup" onClick="submits();" value="Submit">Submit</button>
            	</div>
          	</div>
        </div>
    </div>

<input type="hidden" name="deleteflag" >
<input type="hidden" name="comp_cd" value="<%=comp_cd %>" >
<input type="hidden" name="type" value="">
<input type="hidden" name="opt" value="6">
</div>
<footer class="main-footer">
    <strong>Copyright &copy;2020 <a href="https://barodainformatics.com/BIPL2015/">BIPL</a>.</strong>
    	All rights reserved.
    <div class="float-right d-none d-sm-inline-block">
    	<b>Version</b> 1.0.0
    </div>
</footer>
</form>
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