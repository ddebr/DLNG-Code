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
.mand{
color: red;
}
.card-footer{
background-color: #fff;
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
	if(uniqueQuantityDesc()) 
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
	            alert("Entering Quantity Description is Mandatory");
	             return false;
	        }   
       }
       else
       {
           return false;
       } 	
    }  
}
  
function uniqueQuantityDesc()
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
        alert("Duplicate Quantity");
        flag=false;
        break;
      }  
   }
   return flag;
}

function subm()
{
	 if(uniqueQuantityDesc())
	 {
	    var a=confirm("Do you want to Add?");
	    if(a)
	    {
	       if(document.forms[0].quantcdA.value=="" || document.forms[0].quantcdA.value==" "  || document.forms[0].quantdescA.value=='')
	       {
	           alert("Entering Quantity Description is Mandatory");
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
	
function setFlag(flg)
{
	var type1=document.forms[0].type1.value;
  	var flag=false;
  	if(type1=='')
  	{
  		alert("Please Select Type....");
  		flag=true;
  	}
  	if(!flag)
  	{
  		obj = document.forms[0].conttype ;
  		var conttype ;
  		for(i=0;i< obj.length;i++)
  		{
  			if(obj[i].checked)
  			{
  				conttype = obj[i].value;
  			}	
  	    }
     var url="frm_mas_topquant_mst.jsp?flag="+flg+"&opt=6&type="+type1+"&conttype="+conttype;
     location.replace(url);
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
  obj = document.forms[0].conttype ;
  var conttype ;
  for(i=0;i< obj.length;i++){
  		if(obj[i].checked){
  			conttype = obj[i].value;
  		}	
  }
  var type1=document.forms[0].type1.value;
  var url="frm_mas_topquant_mst.jsp?flag=M&opt=6&type="+type1+"&conttype="+conttype;
  location.replace(url);      
}
	
function refresh(value)
{
	var basic = document.forms[0].type1.value;
	if(basic!=""){
		 var url="frm_mas_topquant_mst.jsp?flag=M&opt=6&type="+basic+"&conttype="+value;
		 location.replace(url);
	}
	/* else{
		alert("Please Select Type....");
	} */
}
function setvalue1(typeval,opt,conttype)
{
	document.forms[0].type1.value=typeval;
	if(conttype=="GSA"){
		document.forms[0].conttype[0].checked = "checked";
	}else{
		document.forms[0].conttype[1].checked = "checked";
	}
} 
</script>
</head>

<jsp:useBean class="com.Gsfc.Baroda.fms6.master.DataBean_master_others" id="masOthers" scope="page"/>
<%
    
    String comp_cd=(String)session.getAttribute("comp_cd");//JHP
    String type=request.getParameter("type")==null?"":request.getParameter("type");
    String opt=request.getParameter("opt")==null?"":request.getParameter("opt");
    String flag=request.getParameter("flag")==null?"":request.getParameter("flag");
    String status=request.getParameter("status")==null?"":request.getParameter("status");
    String ty=request.getParameter("t")==null?"":request.getParameter("t");
    String conttype=request.getParameter("conttype")==null?"GTA":request.getParameter("conttype");
    //System.out.println("In sub Type is : "+conttype);
    masOthers.setCallFlag("QuantityDetails");
    masOthers.setCompany_cd(comp_cd);
    masOthers.setSettype(type);
    masOthers.setConttype(conttype);
    masOthers.init();
    Vector quantity_cd=masOthers.getQuantity_cd();
    Vector quantity_description=masOthers.getQuantity_description();
    Vector quantity_type=masOthers.getQuantity_type();
    String Automax=masOthers.getAutomax();
     int t =0;
     int tot = 0;
    if(Automax==null){
    	
    }else{
    	t=Integer.parseInt(Automax);
    	tot=t+1;
    }
    
%>  

<%if(type.equals("")){%>
<style type="text/css">
.card-header{
background-color: #ffc107;
}
</style>	
<%}%>

<body onload="setvalue1('<%=type %>','<%=opt %>','<%= conttype %>');">
<%@ include file="../home/header.jsp" %>
<form name=mainview method=post  action="../servlet/Frm_master_others">

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
									<%if(!type.equals("")){%>
	                	 				<b class="mandatory"><strong class="mand">*</strong>-Mandatory Information</b>
	                	 			<%} %>
	                			</div>
							</div>
							<div class="card-body table-responsive p-0" style="height: 340px;">
				            	<table class="table table-head-fixed text-nowrap table-bordered">
				            		<thead>
				            			<tr>
				            				<th>
				            					<div class="row">
													<div class="col-sm-6">Quantity TYPE	:</div>
													<div class="col-sm-6">
														<select name="type1" onChange="setStatus();" >
														    <option value="">--Select Type--</option>
														    <option   value="Basic">Basic</option>
														    <option  value="Derived">Derived</option>
												      	</select>
													</div>
												</div>
				            				</th>
				            				<th><input type="radio" name="conttype" value="GSA" onclick="refresh(this.value);" checked="checked">&nbsp;&nbsp;GSA/GSTA</th>
				            				<th><input type="radio" name="conttype" value="GTA" onclick="refresh(this.value);">&nbsp;&nbsp;GTA</th>
				            				
			            					<%if(!type.equalsIgnoreCase("Basic")){ %>
				            					<th> 
										         	<button type="button" class="btn btn-info" name="add" value="Add New" onClick="setFlag('A');">Add New</button> 
										      	</th>
										    <%}else{%>
										    <%} %>
				            			</tr>
				            		</thead>
				            		<tbody>
				            			<%if(flag.equalsIgnoreCase("A")){
											 int k=0;
										%>
									   	     <tr>
									   	     	 <td colspan="2"><b>Add New Quantity</b></td>
										         <td colspan="2">
										         	 <b class="mand">*</b>&nbsp;&nbsp;
										         	 <input type="hidden" name="quantcdA"  value="<%=tot %>" >
										         	 <input type="text" name="quantdescA" value="" size="45" maxlength="100" title="Max Size 100 chars">
										         </td>
								 	         </tr>
										<%}%>
				            			<%for(int k = 0; k < quantity_description.size() ; k++){
											 String totQuant=quantity_description.size()+"";
										%>
									   		<tr>
										        <%if(quantity_description.elementAt(k).equals("Total")){ %>  
									                <td colspan="1">
									                	<b class="mand"></b>&nbsp;&nbsp;
									                	<input type="hidden" name="quantcd" value="<%= (String)quantity_cd.elementAt(k)==null?"":(String)quantity_cd.elementAt(k) %>">
									                	<input type="hidden" name="quantdesc" value="<%=(String)quantity_description.elementAt(k)==null?"":(String)quantity_description.elementAt(k) %>" size="45" maxlength="100" title="Max Size 100 chars">
									                </td>
										        <%}else{ %>
									                <td colspan="4">
									                	<b class="mand">*</b>&nbsp;&nbsp;
									                	<input type="hidden" name="quantcd"  value="<%= (String)quantity_cd.elementAt(k)==null?"":(String)quantity_cd.elementAt(k) %>" >
									                	<input type="text" name="quantdesc" value="<%=(String)quantity_description.elementAt(k)==null?"":(String)quantity_description.elementAt(k) %>" size="45" maxlength="100" title="Max Size 100 chars">
									                </td>
										        <%} %>
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
					            		<%if(flag.equalsIgnoreCase("M")){%>
				            				<button type="button" class="btn btn-success buttonfoot" name="button1" value="Submit" onClick="modify();" <%if(type.equalsIgnoreCase("Basic") && quantity_description.size()==0){%> disabled <%}%>>Submit</button>
				            			<%}%>
				            			<%if(flag.equalsIgnoreCase("A")){%>
				            				<button type="button" class="btn btn-secondary buttonfoot" name="button" value="Refresh" onClick="setStatus();">Refresh</button>
				            				<button type="button" class="btn btn-success buttonfoot" name="button1" value="Submit" onClick="subm();" <%if(type.equalsIgnoreCase("Basic") &&  quantity_description.size()==0){%> disabled <%}%>>Submit</button>
				            			<%}%>										 
					            	</div>
				            	</div>
			            	</div>
				            
				       </div>
          		</div>
          	</div>
        </div>
   </section>

<input type="hidden" name="flag" value="<%=flag %>" />
<input type="hidden" name="opt" value="<%=opt %>" />
<input type="hidden" name="type" value="TOPQuantityDetails"  />
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