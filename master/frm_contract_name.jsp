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
<title>FMS | Master Contract Names </title>

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
.card-header{
background-color: #ffc107;
}
.card-footer{
background-color: #fff;
}
</style>

<script type="text/javascript">
function Submitdata()
{	
	var p1=document.forms[0].contract_nm.value;	
  	a = confirm("Are You Sure You Want to Submit ???");
  	 if(a){
         document.forms[0].submit();
     }
    
 }

</script>
</head>

<jsp:useBean id="cont" class="com.Gsfc.Baroda.fms6.master.DataBean_master_others" scope="request" />
<jsp:useBean id="Util" class="com.Gsfc.Baroda.fms6.util.UtilBean"	scope="page" />
<%
	NumberFormat  nf1 = new DecimalFormat("#########.##");
	Util.init();
	String dt=Util.getGen_dt();
	String comp_cd=(String)session.getAttribute("comp_cd");//JHP
	String date = (String) request.getParameter("date") == null ?dt: (String) request.getParameter("date");
	String map=request.getParameter("contract")==null?"":request.getParameter("contract");
	String message=request.getParameter("message")==null?"":request.getParameter("message");
	String flag=request.getParameter("flag")==null?"":request.getParameter("flag");
	
	String msg=request.getParameter("msg")==null?"":request.getParameter("msg");

	cont.setCallFlag("Contract_Names");
	cont.setCurrent_date(date);
	cont.setCompany_cd(comp_cd);
	cont.init();
	
	Vector party_abr=cont.getVparty_abr();
	Vector party_cd=cont.getVparty_cd();
	Vector entryabr=cont.getVentryabr();
	Vector exitabr=cont.getVexistabr();
	Vector vtransabr=cont.getVtransabr();
	Vector vMappingID=cont.getVmappingid();
	Vector cont_st_dt = cont.getCont_st_dt();
	Vector cont_end_dt = cont.getCont_end_dt();
	Vector entry_ene = cont.getEntry_ene();
	Vector exit_ene = cont.getExit_ene();
	Vector entry_vol = cont.getEntry_vol();
	Vector exit_vol = cont.getExit_vol();
	Vector contract_name = cont.getContract_name();
%>
<body>
<form method="post" action="../servlet/Frm_master_others">
<%@ include file="../home/header.jsp"%>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    	<section class="content-header">
      		<div class="container-fluid">
        		<div class="row mb-2">
          			<div class="col-sm-8">
            			<h1>Master : Contract Name Details<%//=formName%></h1>
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
								<div class="card-title"><h4>Contracts</h4></div>
								<div class="card-tools" >
	                	 			<!-- <button type="button" id="btnAdd" class="btn btn-primary" name="AddNew" value="New Agreement" onClick="addNew();">New Agreement</button>
	                	 			<b class="mandatory"><strong class="mand">*</strong>-Mandatory Information</b> --> 
	                			</div>
							</div>
							<div class="card-body table-responsive p-0" style="height: 340px;">
				            	<table class="table table-head-fixed text-nowrap table-bordered">
				            		<thead>
				            			<tr>
				            				<th><b>Contract</b></th>
											<th><b>Start Date</b></th>
											<th><b>End Date</b></th>
											<th><b>Entry Point Energy<br>(MMBTU)</b></th>
											<th><b>Exit Point Energy<br>(MMBTU)</b></th>
											<th><b>Entry Point Volume<br>(MMSCM)</b></th>
											<th><b>Exit Point Volume<br>(MMSCM)</b></th>
											<th><b>Contract Name</b></th>
				            			</tr>
				            		</thead>
				            		<tbody>
				            			<% 
										 	for(int i=0;i<party_cd.size();i++)
								             {
										         String display=(String)party_abr.elementAt(i) +"-"+(String)vtransabr.elementAt(i)+"-"+(String)entryabr.elementAt(i)+"-"+(String)exitabr.elementAt(i);%>
							            			<tr>
							            				<td><%=display%><input type="hidden" name="contract"  value="<%=vMappingID.elementAt(i) %>" ></td>
							            				<td><%=(String) cont_st_dt.elementAt(i)%></td>
							            				<td><%=(String) cont_end_dt.elementAt(i)%></td>
									                    <td><%=(String) entry_ene.elementAt(i)%></td>
									                    <td><%=(String) exit_ene.elementAt(i)%></td>
									                    <td><%=(String) entry_vol.elementAt(i)%></td>
									                    <td><%=(String) exit_vol.elementAt(i)%></td>
									                    <td><input type="text" name="contract_nm" value="<%=contract_name.elementAt(i) %>"></td>
							            			</tr>
							              <% } %>
				            		</tbody>
				            	</table>
				            </div>
				            <div class="card-footer text-right">
				            	<button type="button" class="btn btn-success" name="subdata" value="Submit" onclick="Submitdata();">Submit</button>
				            </div>
				         </div>
				      </div>
				  </div>
			 </div>
		</section>
		
<input type="hidden" name="type" value="saveContractNames"> 
<input type="hidden" name="flg" value=""> 
<input type=hidden name="updateFlag" value="N">
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
    
	var msg = '<%=message%>';
	if(msg !='' && msg !=' ' ){
	  toastr.success(msg)
	}
});
</script>
</body>
</html>