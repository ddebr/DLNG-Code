<%@ page import="java.util.*" %>
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
<title>FMS | Master Unit</title>

<!-- Font Awesome Icons -->
<link rel="stylesheet" href="../plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="../css/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../dist/css/adminlte.min.css">
<link rel="stylesheet" href="../plugins/toastr/toastr.min.css">
<link rel="stylesheet" href="../css/jquery-ui.css" />

<script type="text/javascript" src="../js/date-picker.js"></script>
<script type="text/javascript" src="../js/mouseclk.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/validations.js"></script>

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
.modifyBtn{
display: none;
}
.leftxtcenter{

}
.addText{
background-color: #EDEDF8;
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

k=0;
var l=0;
var g=0;
  
  
function sv()
{
   elem=document.forms[0].elements;
   for(i=0;i<elem.length;i++)
   {
      if(elem[i].type=='text')
      {
		oldVal[k++]=elem[i].value;
      }
   }
}
    
function trackCh()
{
	k=0;
    elem=document.forms[0].elements;
	              
    if(!trackUnique())
    	return false;
          	      

   for(i=0;i<elem.length;i++)
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

   if(!flag)
   {  
   		
      var a=confirm("Do you want to Modify?");
      if(mandatory())
      {
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
	      alert("All Fields Are Mandatory");
	   }    
   }  
   else
   {
    	alert("No Changes have been made");
   }
}
    
       	 
function trackUnique()
{
     elem=document.forms[0].elements;

   for(i=0;i<elem.length;i++)
   {
      if(elem[i].type=='text')
      {
          for(j=i+1 ; j< elem.length; j++)
          {
               if(elem[j].type=='text')
               {   
                  if(elem[i].value == elem[j].value)
                  {
                  	 alert(elem[i].value+"  "+elem[j].value);
                     if(i%2==0)
                     {
                       alert("Description has to be unique");
                       return false;
                     }  
                     if(i%2!=0)
                     {
                       alert("Abbreviation has to be unique");
                       return false;
                     }
                  }
              }
          }
      }
   }
   return true;
}
        
function subm()
{
  if(!trackUnique())
	   return false; 
   var a=confirm("Do you want to Add?");
   if(a)
   {
      if(document.forms[0].unitabrA=="" || document.forms[0].unitdescA.value==" ")
      {
        alert("Both fields are mandatory!");
      }
      else
      { 
        document.forms[0].submit();
      }
   }
}

function setFlag(flg)
{
     var unittype;
     if(document.forms[0].unittype[0].checked){
    	 unittype='O';
     }else if(document.forms[0].unittype[1].checked){
    	 unittype='C';
     }
     var url="frm_mas_compunit.jsp?flag="+flg+"&opt=2&unittype="+unittype;
     location.replace(url);	
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
            flag=false;
         }
      }
   }
   return flag;
}
	
function getunit(val,flg){
	var url="frm_mas_compunit.jsp?flag="+flg+"&opt=2&unittype="+val;
	location.replace(url);
}		
</script>
</head>

<jsp:useBean class="com.Gsfc.Baroda.fms6.master.DataBean_master_others" id="masOthers" scope="page"/>
<%
String opt=request.getParameter("opt")==null?"":request.getParameter("opt");
String flag=request.getParameter("flag")==null?"":request.getParameter("flag");
String status=request.getParameter("status")==null?"":request.getParameter("status");
String unittype=request.getParameter("unittype")==null?"":request.getParameter("unittype");
String comp_cd=(String)session.getAttribute("comp_cd");//JHP

masOthers.setCallFlag("UnitDetails");
masOthers.setCompany_cd(comp_cd);
if(unittype.equalsIgnoreCase("") || unittype.equalsIgnoreCase("O")){
	masOthers.setUnittype("O");
}else{
	masOthers.setUnittype("C");
}
	
masOthers.init();
	
Vector unitCd=masOthers.getUnit_cd();
Vector unitDesc=masOthers.getUnit_description();
Vector unitAbr=masOthers.getUnit_abr();
Vector currencycd=masOthers.getCurrency_cd();
Vector currencydesc=masOthers.getCurrency_description();
Vector currsymbol=masOthers.getVcurrsymbol();
boolean check=false; 
%>  

<body>
<form name="mainview" method="post" action="../servlet/Frm_master_others">
<%@ include file="../home/header.jsp" %>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    	<section class="content-header">
      		<div class="container-fluid">
        		<div class="row mb-2">
          			<div class="col-sm-6">
            			<h1>Master Entry : Unit | Currency <%//=formName%></h1>
          			</div>
          			<div class="col-sm-6">
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
								<div class="card-title"><h4>Units</h4></div>
								<div class="card-tools" >
	                	 			 <b class="mandatory"><strong class="mand">*</strong>-Mandatory Information</b> 
	                			</div>
							</div>
							<div class="card-body table-responsive p-0">
				            	<table class="table table-head-fixed text-nowrap table-bordered">
				            		<thead>
				            			<tr>
				            				<th class="text-center"><input type="radio" name="unittype" <%if(unittype.equalsIgnoreCase("") || unittype.equalsIgnoreCase("O")){%> checked="checked" <% } %> value="O" onclick="getunit(this.value,'<%=flag %>');">&nbsp;&nbsp;&nbsp;&nbsp;Unit  </th>
				            				<th class="text-center"><input type="radio" name="unittype" <%if(unittype.equalsIgnoreCase("C")){%> checked="checked" <% } %> value="C" onclick="getunit(this.value,'<%=flag %>');">&nbsp;&nbsp;Currency</th>
				            				<th class="text-center"><button type="button" class="btn btn-primary addNBtn" id="addNBtn" name="addNew" value="New Unit" onClick="setFlag('A');">New Unit</button></th>
				            			</tr>
				            		</thead>
				            	</table>
				            </div>
				            <div class="card-body table-responsive p-0" style="height: 320px;">
				            	<table class="table table-head-fixed text-nowrap table-bordered">
				            		<thead>
				            			<tr>
				            				<% if(unittype.equalsIgnoreCase("O")) {%>
												<th class="text-center">Unit Abbreviation</th>
												<th class="text-center">Unit Description</th>
											<% } %>
											<% if(unittype.equalsIgnoreCase("C")){ %>
												<th class="text-center">Symbol</th>
												<th class="text-center">Currency Description</th>
											<% } %>
				            			</tr>
				            		</thead>
				            		<tbody>
				            			<%if(flag.equalsIgnoreCase("A")){
										   check=false;
										   int k=0;
										   %>
	         									<tr>
	          										<td class="addText" colspan="3"> <b>To Add New Unit | Currency</b></td>
									        	</tr>
									   	     	<tr>
										         	<td class="text-center addText"><b class="mand">*</b>&nbsp;&nbsp;<input type="text" name="unitabrA" size="15" value="" maxlength="10" title="Max Size 10 chars"></td>
										         	<td class="text-center addText"><b class="mand">*</b>&nbsp;&nbsp;<input type="text" name="unitdescA" size="40" value="" maxlength="50" title="Max Size 50 chars"></td>
								 	            </tr>
									     <%}%>
									     
				            			<%if(unittype.equalsIgnoreCase("O") || unittype.equalsIgnoreCase(""))
				            			  {
				            				for(int k = 0; k < unitCd.size() ; k++)
											{
												String tCds=unitCd.size()+"";
												check=true;%>
												<tr>
												    <td class="text-center">
												    	<b class="mand">*</b>&nbsp;&nbsp;
												    	<input type="text" name="unitabr" size="15" maxlength="8" value="<%= (String)unitAbr.elementAt(k)==null?"":(String)unitAbr.elementAt(k) %>"  title="Max Size 8 chars">
												    </td>
												    <td class="text-center">
												    	<b class="mand">*</b>&nbsp;&nbsp;
												    	<input type="text" name="unitdesc" size="40" maxlength="50" value="<%=(String)unitDesc.elementAt(k)==null?"":(String)unitDesc.elementAt(k) %>" title="Max Size 50 chars">
												    	<input type="hidden" name="unitcd" value="<%=(String)unitCd.elementAt(k)==null?"":(String)unitCd.elementAt(k) %>" >
												    </td>
											    </tr>
										  <%}
										 }
									     else if(unittype.equalsIgnoreCase("C"))
									     {
									    	 for(int i=0;i<currencycd.size();i++)
									    	 {
												 String  tCds1=currencycd.size()+"";
												 check=true;
										 %>	
											  	<tr>
											        <td class="text-center">
											        	<b class="mand">*</b>&nbsp;&nbsp;
											        	<input type="text" name="currsymbol" size="15" maxlength="6" value="<%=(String)currsymbol.elementAt(i)==null?"":(String)currsymbol.elementAt(i) %>"  title="Max Size 6 chars">
											        </td>
											        <td class="text-center">
											        	<b class="mand">*</b>&nbsp;&nbsp;
											        	<input type="text" name="currdesc" size="40" maxlength="50" value="<%= (String)currencydesc.elementAt(i)==null?"":(String)currencydesc.elementAt(i) %>" title="Max Size 50 chars">
											        	<input type="hidden" name="currencycd" value="<%=(String)currencycd.elementAt(i)==null?"":(String)currencycd.elementAt(i) %>">
											        </td>
											      </tr>
										  <% }
										 }%>
				            		</tbody>
				            	</table>
				            </div>
				            <div class="card-footer">
								<div class="row">
					            	<div class="col-sm-6">
					            	</div>
					            	<div class="col-sm-6 text-right">
				            			<button type="button" class="btn btn-success modifyBtn" name="button" value="Modify" onClick="trackCh();">Modify</button>
				            			<button type="button" class="btn btn-success SubmitBtn" name="button" value="Submit" onClick="subm();">Submit</button>
					            	</div>
				            	</div>
			            	</div>	
				        </div>
			        </div>		
          		</div>
          	</div>
   		</section>
<input type="hidden" name="flag" value="<%=flag %>">
<input type="hidden" name="opt" value="<%=opt %>">
<input type="hidden" name="type" value="UnitDetails">
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