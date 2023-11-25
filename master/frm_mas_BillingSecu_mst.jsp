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
<title>FMS | Master Interest and Exchange Rates</title>

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
<script type="text/javascript" src="../js/fms.js"></script>
<script type="text/javascript" src="../js/util2.js"></script>

<style type="text/css">
.card-footer{
background-color: #fff;
color: red;
}
.mand{
color: red;
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
    if(uniqueBillSecuDesc()) 
    {
	    var a=confirm("Do you want to Modify?");
	    if(a)
	    {
	       if(mandatory())
		   {
		       document.forms[0].submit();
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

function uniqueBillSecuDesc()
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
    if(uniqueBillSecuDesc())
     {
         var rate=document.forms[0].rate.value;
         var exgcdA=document.forms[0].exgcdA.value;
         var intcdA=document.forms[0].intcdA.value;
         
          var a=confirm("Do you want to Add?");
      if(a)
      {
          if(document.forms[0].rate.value=="Instrate"){
          
                if(document.forms[0].intcdA.value=="" || document.forms[0].intcdA.value==" " || document.forms[0].intdescA.value=='')
	          {
	              alert("Entering Description is Mandatory");
	          }
	          else
	          {
	            document.forms[0].submit();
	          }
	     }
	    else if(document.forms[0].rate.value=="Exgrate"){
     
             if(document.forms[0].exgcdA.value=="" || document.forms[0].exgcdA.value==" " || document.forms[0].exgdescA.value=='')
	          {
	              alert("Entering Description is Mandatory");
	          }
	          else
	          {
	            document.forms[0].submit();
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
  var rate=document.forms[0].rate.value;
  var flag=false;
  var flag1=false;
  var flag2=false;
  if(rate=='')
  {
   	flag=true;
  }
   
  if(flag)
  {
	   	alert("Please Select Rate Mode...");
	   	flag2=true;
  }
  else 
  {
      if(!flag2)
      {
        var url="frm_mas_BillingSecu_mst.jsp?opt=7&flag="+flg+"&rate="+rate ;        
        location.replace(url);
      }
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
           flag=false;
         }
      }
   }
   return flag;
}

function setStatus()
{
  var rate=document.forms[0].rate.value;
  var url="frm_mas_BillingSecu_mst.jsp?flag=M&opt=7&rate="+rate;
  location.replace(url);	
}
	
function setRateMode(rate,opt)
{
  	if(opt=="7"){
 		document.forms[0].rate.value=rate;
	}
}
</script>
</head>

<jsp:useBean class="com.Gsfc.Baroda.fms6.master.DataBean_master_others" id="masOthers" scope="page"/>
<%
    
String comp_cd=(String)session.getAttribute("comp_cd");//JHP
String rate=request.getParameter("rate")==null?"":request.getParameter("rate");
String opt=request.getParameter("opt")==null?"":request.getParameter("opt");
String flag=request.getParameter("flag")==null?"":request.getParameter("flag");
String status=request.getParameter("status")==null?"":request.getParameter("status");
  
masOthers.setCallFlag("BillSecuDetails");
masOthers.setCompany_cd(comp_cd);
masOthers.init();
  
Vector int_rate_cd=masOthers.getInt_rate_cd();
Vector int_rate_nm=masOthers.getInt_rate_nm();
Vector exg_rate_nm=masOthers.getExg_rate_nm();
Vector exg_rate_cd=masOthers.getExg_rate_cd();
String int_rate_flag=masOthers.getInt_rate_flag();
String exg_rate_flag=masOthers.getExg_rate_flag();
int tot=int_rate_cd.size()+1;
int tot1=exg_rate_cd.size()+1;
%>  

<%if(rate.equals("")){%>
<style type="text/css">
.card-header{
background-color: #ffc107;
}
</style>	
<%}%>

<body onload="setRateMode('<%=rate %>','<%=opt%>');">
<%@ include file="../home/header.jsp" %>
<form name=mainview method=post action="../servlet/Frm_master_others">

<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    	<section class="content-header">
      		<div class="container-fluid">
        		<div class="row mb-2">
          			<div class="col-sm-8">
            			<h3>Master Entry : Interest and Exchange Rate<%//=formName%></h3>
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
								<div class="card-title"><h4>Rates</h4></div>
								<div class="card-tools" >
									<%if(!rate.equals("")){%>
	                	 				<b class="mandatory"><strong class="mand">*</strong>-Mandatory Information</b>
	                	 			<%}%> 
	                			</div>
							</div>
							<div class="card-body table-responsive p-0" style="height: 340px;">
				            	<table class="table table-head-fixed text-nowrap table-bordered">
				            		<thead>
					            		<tr>
					            			<th>
					            				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					            				Rate Mode:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<select name="rate" onChange="setStatus();">
												    <option value="">--Select--</option>
												    <option value="Instrate">Interest Payment</option>
												    <option value="Exgrate">Exg Rate Reference</option>
											    </select>
					            			</th>
					            			<th>
					            				<button type="button" class="btn btn-primary" name="add" value="Add New" onClick="setFlag('A');">Add New</button>
					            			</th>
					            		</tr>
				            		</thead>
				            		<tbody>
				            			 <%if(rate.equalsIgnoreCase("Instrate") || rate.equalsIgnoreCase("Exgrate")){%>
				            			 	<tr><td colspan="2"><h5>Description</h5></td></tr>
				            			 <%}%>
				            			  
				            			 <%if(flag.equalsIgnoreCase("A"))
										 { 
								    	      int k=0;
								    	      %>
											    <tr>
											    	<td><b>Add New Entry</b></td>
											    	<td>
											    		<b class="mand">*</b>&nbsp;&nbsp;
												        <%if(rate.equalsIgnoreCase("Instrate")){%>
												        	<input type="text" name="intdescA" value="" size="45" maxlength="100" title="Max Size 100 chars">
												        <%}else if(rate.equalsIgnoreCase("Exgrate")){%>
												           <input type="text" name="exgdescA" value="" size="45" maxlength="100" title="Max Size 100 chars">
										 	            <%}%>
										 	        </td>
											    </tr>
											   <%
										 }%>
				            			<%if(rate.equalsIgnoreCase("Instrate"))
				            			  {  
									          for(int k =0; k < int_rate_nm.size() ; k++)
											  {
												    String totint=int_rate_nm.size()+"";
												    %>
										               <tr>
											                <td colspan="2">
											                 	<b class="mand">*</b>&nbsp;&nbsp;
											                	<input type="text" name="intdesc" size="45" maxlength="100" value="<%=(String)int_rate_nm.elementAt(k)==null?"":(String)int_rate_nm.elementAt(k) %>" title="Max Size 100 chars">
											                	<input type="hidden" name="intcd"  value="<%=(String)int_rate_cd.elementAt(k)==null?"":(String)int_rate_cd.elementAt(k) %>" >
											                </td>
												       </tr>
												    <%
											  }
									       }
 										   else if(rate.equalsIgnoreCase("Exgrate"))
 										   {
									  		  for(int k = 0; k < exg_rate_nm.size() ; k++)
									  		   {
									  			    String totexg=exg_rate_nm.size()+"";
									  			    %>
									  	               <tr>
									  	               		<td colspan="2">
									  	               			<b class="mand">*</b>&nbsp;&nbsp;
									  		              		<input type="text" name="exgdesc" size="45" maxlength="100" value="<%=(String)exg_rate_nm.elementAt(k)==null?"":(String)exg_rate_nm.elementAt(k) %>" title="Max Size 100 chars">
									  		              		<input type="hidden" name="exgcd"  value="<%= (String)exg_rate_cd.elementAt(k)==null?"":(String)exg_rate_cd.elementAt(k) %>" >
									  		              	</td>
									  			       </tr>
									  			    <%
									  		   }
									       }%>
				            		</tbody>
				            	</table>
					        </div>
					        <div class="card-footer">
								<div class="row">
					            	<div class="col-sm-6">
					            	</div>
					            	<div class="col-sm-6 text-right">
					            		<%if(flag.equalsIgnoreCase("M")){ %>
									        <button type="button" class="btn btn-success buttonfoot" name="button" value="Submit" onClick="modify();">Submit</button>
									    <%}%>
					            		<%if(flag.equalsIgnoreCase("A")){ %>
					            			<button type="button" class="btn btn-secondary buttonfoot" name="button" value="Refresh" onClick="setStatus();">Refresh</button>
				            				<button type="button" class="btn btn-success buttonfoot" name="button" value="Submit" onClick="subm();">Submit</button>
				            			<%}%> 
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
<input type="hidden" name="type" value="BillingSecuDetails">
<input type="hidden" name="intcdA"  value="<%=tot %>" >
<input type="hidden" name="exgcdA"  value="<%=tot1 %>" >
<input type="hidden" name="exgflag" value="<%=int_rate_flag %>" >
<input type="hidden" name="intflag" value="<%=exg_rate_flag%>" >
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