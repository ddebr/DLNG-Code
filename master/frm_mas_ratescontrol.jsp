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
}
</style>
<script type="text/javascript">
var newWindow;
function showpro(){
if(document.forms[0].process.style.visibility='hidden')
document.forms[0].process.style.visibility='visible';
document.forms[0].process.value='Please Wait, Processing!!!';
}

function hidepro(){
if(document.forms[0].process.style.visibility='visible')
document.forms[0].process.style.visibility='hidden';
document.forms[0].process.value='';
}

function func_frm_AddPrice()
{
	var pcode="";
	var temp =  document.forms[0].updatePcode;
  	var type="";
  	var typeCatag = "";
  	var pricDesc="";
  	if(temp!=undefined)
  	{
  		if(temp.length!=undefined)
    	{
		  updatePcode=document.forms[0].updatePcode;
          ty =  document.forms[0].typeVal;
          tyC =  document.forms[0].typeCatCode;
          pDtxt = document.forms[0].priceDesctxt;
          
		  for(i=0;i<updatePcode.length;i++){
			 if(updatePcode[i].checked){
				pcode=updatePcode[i].value;
				type= ty[i].value;
				typeCatag= tyC[i].value;
				pricDesc= pDtxt[i].value;
			 }
		  }
		}
		else
		{
			if(document.forms[0].updatePcode.checked)
			{
				pcode=document.forms[0].updatePcode.value;
				type= document.forms[0].typeVal.value;
				typeCatag= document.forms[0].typeCatCode.value;
				pricDesc= document.forms[0].priceDesctxt.value;
			}
		}  	
	}
	
  	/* url="frm_AddPrice.jsp?pcode="+pcode+"&rateType="+type;
	if(!newWindow || newWindow.closed)
  	{
		newWindow= window.open(url,"loc","top=10,left=100,width=600,height=300,scrollbars=1");
  	}
  	else
  	{
		 newWindow.close();
		 newWindow= window.open(url,"loc","top=10,left=100,width=600,height=300,scrollbars=1");
  	} */
  	 
	document.forms[0].ptype.value = type;
	document.forms[0].pcat.value = typeCatag;
	document.forms[0].selCat.value = typeCatag;
	document.forms[0].pricedesc.value = pricDesc;
	document.forms[0].ptypeCode.value = type;
	document.forms[0].upPcode.value = pcode;
	document.forms[0].pcode.value = pcode;
	
	if(pcode == ""){
		document.getElementById("btnSaveUpdate").innerHTML = "Add";
		document.forms[0].selType.value = "Add";
	}else{
		document.getElementById("btnSaveUpdate").innerHTML = "Update";
		document.forms[0].selType.value = "Update";
	}
	
  	$('#modal-lg').modal('show');
}

function supplr(prccd,prcdesc,opt,tpe)
{
   var vcode;
   var vcode=document.forms[0].party.value;
   var supcode=document.forms[0].supplier.value;
   var effdt=document.forms[0].effdt.value;
   var url="frm_mas_ratescontrol.jsp?ptyCd="+vcode+"&pr_cd="+prccd+"&prc_desc="+prcdesc+"&opt="+opt+"&tp="+tpe+"&ptySupcd="+supcode+"&effdt="+effdt;
   location.replace(url);
}

function infoRate(opt,cd,desc,tpe,partyCd)
{
  var url="frm_mas_ratescontrol.jsp?pr_cd="+cd+"&prc_desc="+desc+"&opt="+opt+"&tp="+tpe+"&ptyCd="+partyCd;
  location.replace(url);
}

function validate()
{
  var elem=document.forms[0].elements;
  var flag=true;
  var msg=" ";
  for(i=0 ; i < elem.length ; i++)
  {
     if(elem[i].type == "text")
     {
        if(elem[i].value == " " || elem[i].value== "" || elem[i].value == null)     
        {
          alert("You cannot submit before filling all information.");
          flag=false;
          break;
        }
     }
  }
  return flag;
}

function infoEdit(option,prcd,ptysup,ptycd,effdt,tpe,prcdsc)
{
  	var url="frm_mas_ratescontrol.jsp?pr_cd="+prcd+"&ptySupcd="+ptysup+"&ptyCd="+ptycd+"&effdt="+effdt+"&opt="+option+"&tp="+tpe+"&prc_desc="+prcdsc;
  	location.replace(url);
}

function submits()
{      
	var btnid = (event.target.id);
	if(btnid != "")
	{
		if(btnid=="btnSaveUpdate" || btnid=="btndelete"){
			pricedesc=document.forms[0].pricedesc.value;
			ptype=document.forms[0].ptype.value;
			pcat=document.forms[0].pcat.value;
			
			if(btnid=="btndelete"){
				document.forms[0].selType.value="Delete";
			}
			
			if(pricedesc==""||pricedesc==" "||pricedesc==null){
				alert("Enter Price Description");
			}
			else if(ptype=="" || ptype==" "||ptype==null){
				alert("Select Price Type");
			}
			else if(pcat=="" || pcat==" "||pcat==null){
				alert("Select Basic/Drived Type");
			}
			else{
				  var a=confirm("Do you want to Submit?");
			      if(a){
			    	  document.forms[0].submit();  
			      } 
					
			}
		}	
	}
	else
	{
		check=validate();
	    tpe=document.forms[0].tpe.value;
	    if(tpe=='0')
	    {
		   rate=document.forms[0].rate.value;
	       if(check)
	       {
			   if(rate=='0')
			   {
				   var a=confirm("The Rate Entered is 0. Would you like to continue?")
				   if(a){
					    document.forms[0].submit();
				   }else{
					   return false;
				   }
			   }
			   else
			   {
				     var a=confirm("Would you like to continue?")
				     if(a){
					    document.forms[0].submit();
					 }else{
					   return false;
					 }
				}
			}
			else 
			{
			   return false;
		    }
	  	}
	    else if(tpe == '1')
	    {
	          if(check)
	          {
		          var b=confirm("Do you want to Submit?");
				  if(b){
			        document.forms[0].submit();
				  }else{
				    return false;
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
}
</script>
</head>

<jsp:useBean class="com.Gsfc.Baroda.fms6.master.DataBean_master" id="masCont" scope="page"/>   
<jsp:useBean class="com.Gsfc.Baroda.fms6.util.DataBean_modulelock" id="modlock" scope="request"/>
<%
String formId=request.getParameter("formId")==null?"":request.getParameter("formId");
String ml_username=(String)session.getAttribute("username");
String comp_cd=(String)session.getAttribute("comp_cd");//JHP
String ml_formId=request.getParameter("formId");
String ml_sessionId=session.getId();
String ml_temp="other"; 
modlock.setSet_username(ml_username);
modlock.setFormId(ml_formId);
modlock.setSessionId(ml_sessionId);
modlock.setCallFrom(ml_temp);
if(ml_formId!=null)
{
	modlock.init();
	
	boolean flg=modlock.isModLock();
	String uname=modlock.getUname();
	
	if(flg)
	{
%>
 	  <jsp:forward page="../home/fms_moduleerror.jsp" >
		 <jsp:param name="uname"  value="<%=uname%>" /> 
	  </jsp:forward>
<%
	} 		
}

String prc_cd=request.getParameter("pr_cd")==null?"":request.getParameter("pr_cd");    
String tpe=request.getParameter("tp")==null?"":request.getParameter("tp");
String prcDsc=request.getParameter("prc_desc")==null?"":request.getParameter("prc_desc");
String opt=request.getParameter("opt")==null?"":request.getParameter("opt");
String ptyCode=request.getParameter("ptyCd")==null?"1":request.getParameter("ptyCd");
String ptySupCode=request.getParameter("ptySupcd")==null?"":request.getParameter("ptySupcd");
String effdt=request.getParameter("effdt")==null?"":request.getParameter("effdt");
String status=request.getParameter("status")==null?"":request.getParameter("status");
       
masCont.setSet_price_code(prc_cd);
        
boolean chktransp=false;    
if(ptyCode.equalsIgnoreCase(ptySupCode)){}
else{
	chktransp=true;
}

String stt="";
masCont.setCompany_cd(comp_cd);
masCont.setCallFlag("PriceMaster");
masCont.init();

Vector Vprice_cd=masCont.getVprice_cd();
Vector Vprice_desc=masCont.getVprice_desc();
Vector Vprice_type=masCont.getVprice_type();
Vector Vprice_cat=masCont.getVprice_cat();	
%>  

<body>
<form name="mainview" method="POST" action="../servlet/Frm_master">
<%@ include file="../home/header.jsp" %>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    	<section class="content-header">
      		<div class="container-fluid">
        		<div class="row mb-2">
          			<div class="col-sm-8">
            			<h3>Master Entry : Price Components - Basic & Derived<%//=formName%></h3>
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
								<div class="card-title"><h4>Price Components</h4></div>
								<div class="card-tools" >
	                	 			<!--<b class="mandatory"><strong class="mand">*</strong>-Mandatory Information</b> -->
	                			</div>
							</div>
							<div class="card-body table-responsive p-0" style="height: 400px;">
				            	<table class="table table-head-fixed text-nowrap table-bordered">
				            		<tr>
				            			<td>
				            				<table>
					            				<thead>
							            			<tr>
							            				<th>Price&nbsp;Component/Name</th>
							            				<th>Type</th>
							            				<th>Price</th>
							            			</tr>
							            		</thead>
												<tbody>
													 <%int k=Vprice_cd.size();
													  int j;
													  if(k%2==1){
														j=(k/2)+1;
													  }
													  else
													  {
														j=(k/2);
													  }
													    
													  for (int l=0 ; l < j ; l++)
													  { 
												    	 String price_Code=(String)Vprice_cd.elementAt(l) ;
												    	 String price_Desc=(String)Vprice_desc.elementAt(l);
												    	 String price_Type=(String)Vprice_type.elementAt(l);
												    	 String price_cat=(String)Vprice_cat .elementAt(l); 
														 String type = price_Type;
														 String cattype=""; 
														
														 if(price_cat.equalsIgnoreCase("0"))
														 	 cattype="Basic";
														 if(price_cat.equalsIgnoreCase("1"))
														  	 cattype="Derived";
													   %>
													   <%if(l%2==0){%> 
													 	  <tr> 
													  <%}else{%>
													 	  <tr>
													  <%}%>
															   <td>
															   		<input type="radio" name="updatePcode" value="<%=price_Code %>">&nbsp;&nbsp;&nbsp;&nbsp;
															   		<%=price_Desc%><%-- <a href="#" onClick="infoRate('A','<%=price_Code%>','<%= price_Desc %>','<%= price_Type %>','<%=ptyCode %>')"></a> --%>
															   		<input type="hidden"  name="priceDesctxt" value="<%=price_Desc%>">
															   </td>
															   <td>
																    <%if(type.equalsIgnoreCase("R")){ %> 
																    	Rate
																    <%}else{ %>
																      	Tax
																    <%} %>
															    	<input type="hidden" name="typeVal" value="<%=type%>" >
														       </td>
															   <td>
																	<%=cattype%>
																    <input type="hidden" name="typeCat" value="<%=cattype %>" >
																    <input type="hidden" name="typeCatCode" value="<%=price_cat %>" >
																    
															   </td>
														 </tr>
												<%}%>
												
												</tbody>
											</table>
				            			</td>
				            			<td>
				            				<table>
					            				<thead>
							            			<tr>
							            				<th>Price&nbsp;Component/Name</th>
							            				<th>Type</th>
							            				<th>Price</th>
							            			</tr>
							            		</thead>
							            		<tbody>
				 									<%for(int l=j ; l < k ; l++)
				 									  { 
													       String price_Code=(String)Vprice_cd.elementAt(l) ;
													       String price_Desc=(String)Vprice_desc.elementAt(l);
													       String price_Type=(String)Vprice_type.elementAt(l);
													       String price_cat=(String)Vprice_cat .elementAt(l); 
														   String type = price_Type;
														   String cattype=""; 
													 	 
														   if(price_cat.equalsIgnoreCase("0")){
													 	      cattype="Basic";
														   }
													  	   if(price_cat.equalsIgnoreCase("1")){
													  	 	  cattype="Derived";
													  	   }
				
														   if(l%2==0){%> 
														   		<tr> 
														   <%}else{%>
														   		<tr>
														   <%}%>
														   
					 		    									<td><input type="radio" name="updatePcode" value="<%=price_Code %>">&nbsp;&nbsp;&nbsp;<%=price_Desc%>
					 		    										<%-- <a href="#" onClick="infoRate('A','<%=price_Code%>','<%= price_Desc %>','<%= price_Type %>','<%=ptyCode %>')"></a> --%>
					 		    										<input type="hidden"  name="priceDesctxt" value="<%=price_Desc%>">
					 		    									</td>
							    									<td>
							    										<%if(type.equalsIgnoreCase("R")){ %> 
							    											Rate
							    										<%}else{ %>
							      											Tax
							    										<%} %>
							      										<input type="hidden" name="typeVal" value="<%=type %>" >
							    									</td>
							    									<td><%=cattype%><input type="hidden" name="typeCat" value="<%=cattype %>"><input type="hidden" name="typeCatCode" value="<%=price_cat %>" ></td>
																</tr>
												  <% }%>	
												</tbody>
											</table>
				            			</td>
				            		</tr>	
								</table>
					        </div>
					        <div class="card-footer text-center">
								<button type="button" class="btn btn-info" value="Add/Update Price Name" onClick="func_frm_AddPrice();">Add | Update</button>
			            	</div>
          				</div>
          			</div>
          		</div>
        	</div>
   	</section>
   
   <div class="modal fade" id="modal-lg">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
            	<div class="modal-header">
            		<h4 class="modal-title">Add Price Components</h4>
	              	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
                		<span aria-hidden="true">&times;</span>
              		</button>
            	</div>
            	<div class="modal-body">
            		<div class="table-responsive p-0">
		            	<table class="table table-head-fixed text-nowrap table-bordered">
		                	<thead>
		                    	<tr>
			                      	<th class="text-center">Price Type</th>
			                      	<th class="text-center">Basic/Derived</th>
			                      	<th class="text-center">Price Description</th>
		                    	</tr>
		                  	</thead>
		                  	<tbody>
		                  		<tr>
		                  			<td>
               							<select name="ptype">
               								<option value="">Select</option>
							      			<option value="R">Rate</option>
							      			<option value="T">Tax</option>
							    		</select>
							    		<input type="hidden" name="ptypeCode" value="">
               						</td>
               						<td>
               							<select name="pcat">
               								<option value="">Select</option>
									      	<option value="0">Basic</option>
									      	<option value="1">Derived</option>
									    </select>
									    <input type="hidden" name="pcatCode" value="">
               						</td>
		                  			<td>
               							<input type="text" size="60" maxlength="60" name="pricedesc" value="">
               							<input type="hidden" name="upPcode" value="">
               						</td>
		                  		</tr>
		                  	</tbody>
		                </table>
		            </div> 
            	</div>
            	<div class="modal-footer justify-content-between">
              		<input type="hidden" name="button" value="Delete" id="btndelete" onClick="delete_rate();submits();">
              		<button type="button" class="btn btn-success toastrDefaultSuccess" id="btnSaveUpdate" onClick="submits();"  value=""></button>
            	</div>
          	</div>
        </div>
    </div>
 
<input type="hidden" name="tpe" value="<%=tpe %>">
<input type="hidden" name="prc_cd" value="<%=prc_cd %>">
<input type="hidden" name="prcDsc" value="<%=prcDsc %>">
<input type="hidden" name="comp_cd" value="<%=comp_cd %>">
<input type="hidden" name="pcode" value="">
<input type="hidden" name="selType" value="">
<input type="hidden" name="selCat" value="">
<input type="hidden" name="type" value="AddPrice">
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