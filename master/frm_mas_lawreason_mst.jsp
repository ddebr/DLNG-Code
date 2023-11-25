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

<style type="text/css">
.card-footer{
background-color: #fff;
}
.mand{
color: red;
}
.addnewcat{
display: none;
}
.addnewcatxt{
display: none;
}
.addNewEntrylbl{
visibility: hidden;
}
.addNewEntrytxtmark{
visibility: hidden;
}
.addNewEntrytxt{
visibility: hidden;
}
</style>

<script type="text/javascript">
var oldVal=new Array();
var newVal=new Array();
var valUpd=new Array();
var tcompadd1=new Array();

flag=true;
k=0;
var l=0;
var g=0;
  
function sv()
{
      //alert("SV");
   elem=document.forms[0].elements;
   for(i=0;i<elem.length;i++)
   {
       if(elem[i].type=='text')
       {
			oldVal[k++]=elem[i].value;
       }
   }
}
   
function modify()
{
	if(uniqueLawReasonDesc()) 
    {
        var a=confirm("Do you want to Modify?");
        if(a)
        {
          if(mandatory())
	        {
	        	 document.forms[0].type.value="Law&ReasonDetails"; 
        	  	 if(document.forms[0].type.value != ""){
	        	 	document.forms[0].submit();
        	  	 }
	        }
	       else
	        {
	            alert("Entering  Description is Mandatory");
	             return false;
	        }   
	    }
        else
        {
            return false;
        } 	
    }      
}

function uniqueLawReasonDesc()
{
   flag=true;
   elem=document.forms[0].elements;
          
   for(i=0 ; i < elem.length ; i++)
   {
      k=0;
      if(elem[i].type=='text')
      {
         for(j=0 ; j < elem.length; j++)
         {
            if(elem[j].type=='text')
            {
               if(elem[i].value==elem[j].value)
                 k++;
            }
         }
      }
      if(k>1)
      {
        alert("Duplicate Entry");
        flag=false;
        break;
      }  
   }
   return flag;
}

function subm()
{
   if(uniqueLawReasonDesc())
   {
       var mode=document.forms[0].mode.value;
       var rescdA=document.forms[0].rescdA.value;
       var lawcdA=document.forms[0].lawcdA.value;
       var flag=false;
             
       var a=confirm("Do you want to Add?");
       if(a)
       {
           if(document.forms[0].mode.value=="Law" )
           {
                 if(document.forms[0].lawcdA.value=="" || document.forms[0].lawdescA.value=='' || document.forms[0].category.value=="")
		         {
		              alert("Entering Description is Mandatory");
		         }
		         else
		         {
		        	 document.forms[0].type.value="Law&ReasonDetails"; 
	        	  	 if(document.forms[0].type.value != ""){
		        	 	document.forms[0].submit();
	        	  	 }
		         }
		   } 
           else if(document.forms[0].mode.value=="Reason" )
           {
	             if(document.forms[0].rescdA.value=="" || document.forms[0].resdescA.value=='' || document.forms[0].category.value=="")
		         {
		             alert("Entering Description is Mandatory");
		         }
		         else
		         {
		        	 document.forms[0].type.value="Law&ReasonDetails"; 
	        	  	 if(document.forms[0].type.value != ""){
		        	 	document.forms[0].submit();
	        	  	 }
		         }
            }
     }
     else
     {
       return false;
     }   
   }    
}

function setFlag(flg)
{
	var mode=document.forms[0].mode.value;
  	var cat =document.forms[0].category.value;
  	var flag=false;
  	var flag1=false;
  	var flag2=false;
  
  	if(cat==''){	flag=true; }
  
  	if(mode=='') { flag1=true; }
  
  	if(flag)
  	{
     	alert("Please Select Category...");
     	flag2=true;
  	}
  	else if(flag1)
  	{
     	alert("Please Select Mode..."); 
     	flag2=true;      
  	}
  	else 
  	{
  		var btnid = (event.target.id);
  	  	if(btnid=="addNewmodalbtn"){
  	  		document.getElementsByClassName("addnewcat")[0].style.display = "block";
  	  		document.getElementsByClassName("addnewcatxt")[0].style.display = "block";
  	  		document.forms[0].b11.style.visibility='visible';
  	  	}
  	  	else
  	  	{
  	  		  document.getElementsByClassName("addNewEntrylbl")[0].style.visibility = "visible";
  	  		  document.getElementsByClassName("addNewEntrytxtmark")[0].style.visibility = "visible";
	  	  	  if(mode=="Reason"){
	  	  		  document.forms[0].resdescA.style.visibility='visible';
	  	  	  }else{
	  	  		  document.forms[0].lawdescA.style.visibility='visible';	
	  	  	  }
  	  	}
  	}
}  

function setStatus()
{
	  var mode=document.forms[0].mode.value;
	  var cat = document.forms[0].category.value;
	  if(cat==""){
		  document.getElementsByClassName("addNewEntrylbl")[0].style.visibility = "visible";
	  	  document.getElementsByClassName("addNewEntrytxtmark")[0].style.visibility = "visible";
	  	  document.getElementsByClassName("addNewEntrytxt")[0].style.visibility = "hidden";
		  $('select').prop('selectedIndex',0);
		  var url="frm_mas_lawreason_mst.jsp?flag=&opt=&mode="+"&category=";
	  }else{
		  var url="frm_mas_lawreason_mst.jsp?flag=M&opt=4&mode="+mode+"&category="+cat;  
	  }
      location.replace(url);	
}

function setMode(mode,opt,cat)
{
    if(opt=="4"){
	  document.forms[0].mode.value=mode;
	  document.forms[0].category.value=cat;
	 }
}
	
function view()
{
	if(document.forms[0].category.value == "")
	{
		alert("Please Select Category");
	}
	else if(mode=document.forms[0].mode.value == "")
	{
		alert("Please Select Mode");
	}
	else
	{
		/* if(!newWindow || newWindow.closed)
		{
			 newWindow= window.open("frm_lawreson_category.jsp","loc","top=0,left=0,width=600,height=500,scrollbars=1");
		}
		else
		{
		 	 newWindow.close();
			 newWindow= window.open("frm_lawreson_category.jsp","loc","top=0,left=0,width=600,height=500,scrollbars=1");
		} */ 
		$('#modal-lg').modal('show');
	}
}



/*------------------ Law Modal------------------*/
/* function refresh()
{
    var url="frm_lawreson_category.jsp";
    location.href(url);    
} */

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
  
function subm1()
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
    	var btnid = (event.target.id);
      	if(btnid=="addnewcatBtn" || btnid=="updatecatBtn")
      	{	
      		document.forms[0].type.value="lawcategory";
      		if(document.forms[0].type.value != ""){
      			document.forms[0].submit();
                window.opener.refresh1();	 
          	}
      	}
     }
     else
     {
        return false;
     }
  }
}
  
function enable(k,tCodes)
{
    if(tCodes==1)
    {
       document.forms[0].groupcd.className="thdLine2";   
    }
    else
    {
	   document.forms[0].unitAbr[k].className="thdLine2";
	   document.forms[0].unitDesc[k].className="thdLine2";
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
	
function enabledelete(){
	document.forms(0).delbutton.disabled=false;
}

function delunit()
{
	
	len=document.forms(0).deleteunit.length;	
	cnt=0;
	for(i=0;i<len;i++)
	{
		if(document.forms(0).deleteunit[i].checked==true){
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

function check(){
   window.opener.location.reload();
   self.close();
}
/*------------------End Law Modal------------------*/
</script>
</head>

<jsp:useBean class="com.Gsfc.Baroda.fms6.master.DataBean_master_others" id="masOthers" scope="page"/>
<%
String mode=request.getParameter("mode")==null?"":request.getParameter("mode");
String opt=request.getParameter("opt")==null?"":request.getParameter("opt");
String flag=request.getParameter("flag")==null?"":request.getParameter("flag");
String status=request.getParameter("status")==null?"":request.getParameter("status");
String category=request.getParameter("category")==null?"":request.getParameter("category");
String comp_cd=(String)session.getAttribute("comp_cd");//JHP

String message=request.getParameter("message")==null?"":request.getParameter("message");
String errormessage=request.getParameter("errormessage")==null?"":request.getParameter("errormessage");

/*------------------Law Modal------------------*/
masOthers.setCallFlag("lawcategory");
masOthers.setCompany_cd(comp_cd);
/*------------------End Law Modal------------------*/

masOthers.setSet_anxr_cd(category);
masOthers.setCompany_cd(comp_cd);
masOthers.setCallFlag("ReasonLawDetails");
masOthers.init();

Vector Vlaw_cd=masOthers.getVlaw_cd();
Vector Vlaw_description=masOthers.getVlaw_description();
Vector Vreason_cd=masOthers.getVreason_cd();
Vector Vreason_description=masOthers.getVreason_description();

Vector anxr_nm=masOthers.getAnxr_nm();
Vector anxr_cd=masOthers.getAnxr_cd();
 
int tot=masOthers.getMaxLawNo();
int tot1=masOthers.getMaxReasonNo();      

/*------------------Law Modal------------------*/
Vector qulunitcd=masOthers.getVanxr_cd();
Vector qulunitnm=masOthers.getVanxr_nm();
/* String flag=request.getParameter("flag")==null?"":request.getParameter("flag");
String status=request.getParameter("status")==null?"":request.getParameter("status"); */

/*------------------End Law Modal------------------*/
%>  

<%if(mode.equals("")){%>
<style type="text/css">
.card-header{
background-color: #ffc107;
}
</style>	
<%}%>

<body onload="setMode('<%=mode %>','<%=opt %>','<%=category %>');">
<%@ include file="../home/header.jsp" %>
<form name="mainview" method="post" action="../servlet/Frm_master_others">
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    	<section class="content-header">
      		<div class="container-fluid">
        		<div class="row mb-2">
          			<div class="col-sm-8">
            			<h1>Master : Law & Reason Details<%//=formName%></h1>
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
								<div class="card-title"><h4>Law & Reason</h4></div>
								<div class="card-tools" >
	                	 			<!-- <button type="button" id="btnAdd" class="btn btn-primary" name="AddNew" value="New Agreement" onClick="addNew();">New Agreement</button>-->
	                	 			<%if(!mode.equals("")){%>
	                	 				<b class="mandatory"><strong class="mand">*</strong>-Mandatory Information</b>
	                	 			<%}%>  
	                			</div>
							</div>
							<div class="card-body table-responsive p-0" style="height: 340px;">
				            	<table class="table table-head-fixed text-nowrap table-bordered">
				            		<tbody>
				            			<tr>
				            				<td>Select Category</td>
				            				<td>
				            					<select name="category" onchange="setStatus();">
												     <option value="">--Select Category--</option>
												     <%for(int i=0;i<anxr_cd.size();i++) {%>
												           <option value="<%=anxr_cd.elementAt(i) %>" > <%=anxr_nm.elementAt(i) %></option>
												     <%} %>
												</select>
				            				</td>
				            				<td><button type="button" class="btn btn-info" onClick="view();">Add | Modify Category</button></td>
				            			</tr>
				            			<tr>
				            				<td>Mode:</td>
				            				<td>
				            					<select name="mode" onChange="setStatus();" style="width: 13.8em;">
												    <option value="">---------Select Mode---------</option>
												    <option value="Law">Law</option>
												    <option value="Reason">Reason</option>
											    </select>
				            				</td>
				            				<td><button type="button" class="btn btn-info" name="add" value="Add New" onClick="setFlag('A');">Add New</button></td>
				            			</tr>
				            			
				            			<%if(flag.equalsIgnoreCase("A") || mode.equalsIgnoreCase("Law") || mode.equalsIgnoreCase("Reason")){%>
				            				<tr><td colspan="3"><h5>Description</h5></td></tr>
				            			<%}%>
				            			
	   	     							<tr>
	   	     								<td class="addNewEntrylbl"><b>Add New Entry</b></td>
		         							<td class="addNewEntrytxt" colspan="2">
		         								<b class="mand addNewEntrytxtmark">*</b>&nbsp;&nbsp;
		         								<%if(mode.equalsIgnoreCase("Law")){%>
		           									<input type="text" name="lawdescA" value="" size="45" maxlength="100" title="Max Size 100 chars" style="visibility: hidden;">
		         								<%}else if(mode.equalsIgnoreCase("Reason")){%>
		           									<input type="text" name="resdescA" value="" size="45" maxlength="100" title="Max Size 100 chars" style="visibility: hidden;">
 	             								<%} %>
	             							</td>
 	         							</tr>
			   							 
				            			<%if(mode.equalsIgnoreCase("Law")){ %>
											  <%for(int k =0; k < Vlaw_description.size() ; k++)
											  {
												  String totlaw=Vlaw_description.size()+"";
										  %>
										          <tr>
										          		<td><b>Update Entry</b></td>
										          		<td colspan="2">
										          			<b class="mand">*</b>&nbsp;&nbsp;
										          			<input type="hidden" name="lawcd"  value="<%= (String)Vlaw_cd.elementAt(k)==null?"":(String)Vlaw_cd.elementAt(k) %>" >
											                <input type="text" name="lawdesc" value="<%=(String)Vlaw_description.elementAt(k)==null?"":(String)Vlaw_description.elementAt(k) %>" size=45 maxlength=100 title="Max Size 100 chars">
											            </td>
												  </tr>
										  <%
											   }
									       }
									       else if(mode.equalsIgnoreCase("Reason")){
									  		  for(int k = 0; k < Vreason_description.size() ; k++)
									  		   {
									  			    String totres=Vreason_description.size()+"";
									  	   %>
									  	               <tr>
									  		                <td><b>Update Entry</b></td>
									  		              	<td colspan="2">
									  		              		<b class="mand">*</b>&nbsp;&nbsp;
									  		              		<input type="hidden" name="rescd"  value="<%= (String)Vreason_cd.elementAt(k)==null?"":(String)Vreason_cd.elementAt(k) %>" >
									  		              		<input type="text" name="resdesc" value="<%=(String)Vreason_description.elementAt(k)==null?"":(String)Vreason_description.elementAt(k) %>" size="45" maxlength="100" title="Max Size 100 chars">
									  		              	</td>
									  			       </tr>
									  			    <%
									  		   }
									       } %>

				            		</tbody>
				            	</table>
				            </div>
				            
				            <div class="card-footer">
								<div class="row">
					            	<div class="col-sm-6">
										<!-- <label id="errlbl" style="color: red;"></label> -->
					            	</div>
					            	<div class="col-sm-6 text-right">
					            		<%if(flag.equalsIgnoreCase("M") && !mode.equals("")){%>
											<button type="button" class="btn btn-success" name="subdata" value="Submit" onClick="modify();">Submit</button>
										<%}%>
										<%if(flag.equalsIgnoreCase("A") && !mode.equals("")){%>
											<button type="button" class="btn btn-secondary" name="button" value="Refresh" onClick="setStatus();">Refresh</button></td>
											<button type="button" class="btn btn-success" name="subdata" value="Submit" onClick="subm();">Submit</button> 
										<%}%>
					            	</div>
					            </div>
					        </div>
				         </div>
				      </div>
				  </div>
			 </div>
		</section>
		

		<div class="modal fade" id="modal-lg">
        	<div class="modal-dialog modal-dialog-scrollable">
          		<div class="modal-content">
            		<div class="modal-header">
              			<h5 class="modal-title">Category Details</h5>
              			<button name="add" type="button" class="btn btn-info btn-sm text-right" id="addNewmodalbtn" value="Add New" onclick="setFlag('A');">Add New</button>
            		</div>
            		<div class="modal-body">
            			<div class="table-responsive">
			            	<table class="table table-bordered">
			                	<thead>
			                    	<tr>
				                      	<th class="text-center">Sr No.</th>
				                      	<th class="text-center">Category Description</th>
			                    	</tr>
			                  	</thead>
			                  	<tbody>
			                    	<tr>
			                  			<td colspan="2">
			                  				<table class="table-borderless">
			                  					<tr>
			                  						<td><b class="mand addnewcat">*</b></td>
			                  						<td>
			                  							<input type="text" name="unitDescA" value="" size="30" maxlength="100" onClick="enableA();" title="Max Size 100 chars" class="addnewcatxt" >
			                  							<input type="hidden" name="delbutton" value="Delete" onClick="delunit();" disabled="disabled">
			                  						</td>
			                  						<td><button name="b11" type="button" class="btn btn-success btn-sm" id="addnewcatBtn" value="Submit" onclick="subm1();" style="visibility: hidden;">Add</button></td>
			                  					</tr>
			                  				</table>
			                  			</td>
			                  		</tr>
			                  		<%for(int i=0;i<anxr_cd.size();i++) {%>
			                  			<tr>
					                  		<td><%=anxr_cd.elementAt(i) %></td>
				                  			<td>
				                  				<b class="mand">*</b>&nbsp;&nbsp;
			                  					<input type="text" name="unitDesc" size="30" maxlength="100" value="<%=anxr_nm.elementAt(i) %>" title="Max Size 50 chars">
			                  					<input type="hidden" name="unit_cd" value="<%=anxr_cd.elementAt(i) %>" >
				                  			</td>		
				                  		</tr>
									<%} %>
			                  		
			                  					 
	            				</tbody>
	            			</table>
            		     </div>
            		</div>
            		
            		<div class="modal-footer">
              			<button name="b1" type="button" class="btn btn-default btn-sm text-right" id="b12" value="Refresh" onclick="refresh();" >Refresh</button>
              			<button type="button" class="btn btn-success btn-sm toastrDefaultSuccess" id="updatecatBtn" onClick="subm1();" value="Submit">Save</button>  
            		</div>
          		</div>
        	</div>
      </div>

		
<%-- <input type="hidden" name="flag" value="<%=flag %>">
<input type="hidden" name="type" value="lawcategory">
<input type="hidden" name="comp_cd" value="<%=comp_cd %>"> --%>
	
<input type="hidden" name="deleteflag" >		
<input type="hidden" name="flag" value="<%=flag %>">
<input type="hidden" name="opt" value="<%=opt %>">
<input type="hidden" name="type" value="">
<input type="hidden" name="lawcdA"  value="<%=tot %>" >
<input type="hidden" name="rescdA"  value="<%=tot1 %>" >
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