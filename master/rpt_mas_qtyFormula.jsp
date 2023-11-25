<%@ page import="java.util.*,java.text.*" %>
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
<title>FMS | Master Quality Components</title>

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
</style>

<script type="text/javascript">
function refresh()
{
 	year=document.forms[0].year.value;
	map=document.forms[0].map.value;
	sel_month=document.forms[0].sel_month.value;
	var flag1='B';
	flg = document.forms[0].flag.value;
	if(flg=='Y')
	{
		comm_id = document.forms[0].comm.value;
	}
	else
	{
		if(flg == "" ||flg == null ||flg == "null" || flg == " "){
			comm_id = document.forms[0].comp_cd1.value;
		}else{
			comm_id = document.forms[0].comp_cd1.value;
		}
	}
	if(comm_id=='A')
	{
		flag1='A';
	}
     yr1=year.substring(2);
	 var date="1/01/"+yr1;
	
	 if(comm_id != '0')
	 {
	 	url="rpt_mas_qtyFormula.jsp?year="+year+"&map="+map+"&sel_month="+sel_month;
	    location.replace(url);
	 }  
}

function setValue(mapp,sdt,edt)
{
	document.forms[0].map.value=mapp;
	document.forms[0].st_dt.value=sdt;
	document.forms[0].end_dt.value=edt;
}

function checkwithStdt(obj)
{
   cont_st_dt=document.forms[0].st_dt.value;
   cont_end_dt=document.forms[0].end_dt.value;
   var sdt="";
   var edt="";
   var mn="";
   var nm="";
   var contyr="";
   var contendyr="";
 
   if(cont_st_dt!="")
   {
       var mon = cont_st_dt.split("/");
       sdt=mon[0];
       mn=mon[1];
       contyr=mon[2]; 
   }
     
   if(cont_end_dt!="")
   {
      var nom=cont_end_dt.split("/");
      edt=nom[0];
      nm=nom[1];
      contendyr=nom[2];
   }
    
   if(contendyr>contyr){
        return true;
   }else{
        if(contendyr==contyr && nm>mn){
             return true;
         }else{
              if(nm==mn && edt>=sdt){
                 return true;
              }else{
	           	 alert("Contract Start Date must be less than or equal to End Date");
	           	 document.forms[0].st_dt.value="";
	           	 return false;
	          }
	      }
	  }
   return true; 
}

function checkwithEnddt(obj)
{
   cont_st_dt=document.forms[0].st_dt.value;
   cont_end_dt=document.forms[0].end_dt.value;  
   var sdt="";
   var edt="";
   var mn="";
   var nm="";
   var contyr="";
   var contendyr="";
 
   if(cont_st_dt!="")
   {
       var mon = cont_st_dt.split("/");
        sdt=mon[0];
        mn=mon[1];
        contyr=mon[2]; 
   }
     
   if(cont_end_dt!="")
   {
      var nom=cont_end_dt.split("/");
       edt=nom[0];
       nm=nom[1];
       contendyr=nom[2];
   }
    
   if(contendyr>contyr){
        return true;
   }else{
        if(contendyr==contyr && nm>mn){
             return true;
         }else{
              if(nm==mn && edt>=sdt){
                 return true;
              }else{
	           	 alert("Contract End Date must be greater than or equal to  Start Date");
	           	 document.forms[0].end_dt.value="";
	           	 return false;
	          }
	      }
   }
   return true; 
}

var newWindow;
function openPrint()
{
    var mapping=document.forms[0].contract.value;
    var stdt = document.forms[0].st_dt.value;
    var enddt = document.forms[0].end_dt.value;
    var date = document.forms[0].date.value;
     var cont_name =  document.forms[0].contract[document.forms[0].contract.selectedIndex].innerText;
	if(!newWindow || newWindow.closed)
	{
		newWindow= window.open("print_mas_qtyFormula.jsp?date="+date+"&mapping="+mapping+"&st_dt="+stdt+"&end_dt="+enddt+"&cont_name="+cont_name,"loc","top=10,left=100,width=700,height=600,scrollbars=1");
	}
	else
	{
		 newWindow.close();
		 newWindow= window.open("print_mas_qtyFormula.jsp?date="+date+"&mapping="+mapping+"&st_dt="+stdt+"&end_dt="+enddt+"&cont_name="+cont_name,"loc","top=10,left=100,width=700,height=600,scrollbars=1");
	}
}
</script>
</head>

<jsp:useBean class="com.Gsfc.Baroda.fms6.master.DataBean_master_rpt" id="masCont" scope="page"/>     
<jsp:useBean class="com.Gsfc.Baroda.fms6.util.UtilBean"  id="utilBean"  scope="request"/>
<%
NumberFormat formatter = new DecimalFormat("###########.###");
utilBean.init();
String gen_dt = utilBean.getGen_dt();
String cur_month = gen_dt.substring(3,5);
String dt = utilBean.getGen_dt();
String year=utilBean.getGet_yr();
String[] sdt=dt.split("/");	
String mon=sdt[1];
String yr=sdt[2];
String stdt="01"+"/"+mon+"/"+yr;

   
String comp_cd=(String)session.getAttribute("comp_cd");// BY SM 
String emp_cd =(String)session.getAttribute("user_cd");// BY SM 
String comm_id = request.getParameter("comm")==null?comp_cd:request.getParameter("comm"); // BY SM
String Current_year =request.getParameter("year")==null?year:request.getParameter("year");
String sel_month = request.getParameter("sel_month")==null?cur_month:request.getParameter("sel_month");
String st_dt=request.getParameter("st_dt")==null?stdt:request.getParameter("st_dt");    
String map=request.getParameter("map")==null?"Sel":request.getParameter("map"); 
//String mappingId =request.getParameter("map")==null?"0":request.getParameter("map");
String flag =request.getParameter("flag")==null?"":request.getParameter("flag");
	 
if(Current_year.equalsIgnoreCase(""))
{
  	Current_year = year;
}
  	  
masCont.setCallFlag("GTA_Quantities_Display");
masCont.setParent_comp_cd(comp_cd); // BY SM 
masCont.setCompany_cd(comm_id); // BY SM
masCont.setSet_year(Current_year);   
masCont.setEmp_cd(emp_cd);
masCont.setSet_date(dt);
masCont.setMappingCd(map);
masCont.setSet_month(sel_month);
masCont.init();
	  
String comp_nm = masCont.getComp_nm();
	   
// All internal company 
Vector Vcomm_dtl_id = masCont.getVcomm_dtl_id();
Vector Vcomm_dtl_nm = masCont.getVcomm_dtl_nm();
   
if(Vcomm_dtl_id.size()!=1)
{
  flag="Y";
}

int st_year=0;
int end_year=0;
String sty=	masCont.getSt_year();
String endy=masCont.getEnd_year();
if(!sty.equalsIgnoreCase("") && !endy.equalsIgnoreCase(""))
{
	st_year=Integer.parseInt(masCont.getSt_year());
   	end_year=Integer.parseInt(masCont.getEnd_year());
}
 
Vector Vmapping_abr=new Vector();
Vector Vparty_cd=masCont.getVparty_cd();
Vector Vparty_abr=masCont.getVparty_abr();
Vector Vmapp_id=masCont.getVmapp_id();
Vector party_abr = masCont.getVparty_abr_tran();
Vector vcomp_cd = masCont.getVcomp_cd();
String cont_typ = masCont.getCont_type()==null?"":masCont.getCont_type();  
double month_days=masCont.getMonth_days();
String cont_type = masCont.getCont_type();
Vector Vqty_code  =  masCont.getGsa_Vqty_code();
Vector Vqty_desc =  masCont.getGsa_Vqty_desc(); 
Vector Vqty_val =  masCont.getVqty_val();
Vector gsa_Vqty_code  =  masCont.getGsa_Vqty_code();
Vector gsa_Vqty_desc =  masCont.getGsa_Vqty_desc(); 
//Vector gsa_Vqty_val =  masCont.getGsa_qty_val_tot();
String tot_array_all[]=masCont.getTot_array_all();;	
%>  
  
<body onload="">
<%@ include file="../home/header.jsp" %>
<form>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    	<section class="content-header">
      		<div class="container-fluid">
        		<div class="row mb-2">
          			<div class="col-sm-6">
            			<h1>Master Entry : Basic and Derived Quantity <%//=formName%></h1>
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
	                			</div>
							</div>
							<div class="card-body table-responsive p-0">
				            	<table class="table table-head-fixed text-nowrap table-bordered">
				            		<thead>
				            			<tr>
				            				<th>Parent Unit :</th>
				            				<th><%=comp_nm %></th>
				            				<th>Select Year :</th>
				            				<th>
				            					<select name="year" onChange="refresh();">
											        <%for(int i=st_year;i<=end_year;i++){ %>
												    <option value="<%=i%>"><%=i%></option>
											  	   <%}%>
											    </select>
											    <script>
											    	document.forms[0].year.value = '<%=Current_year %>';
											    </script>
				            				</th>
				            				<th>Month:</th>
				            				<th>
												<select name="sel_month" onchange="refresh();">
													<option value="0">-Select Month-</option>
													<option value="01">January</option>
													<option value="02">February</option>
													<option value="03">March </option>
													<option value="04">April </option>
													<option value="05"> May</option>
													<option value="06"> June </option>
													<option value="07"> July </option>
													<option value="08"> August </option>
													<option value="09"> September</option>
													<option value="10"> October </option>   
													<option value="11"> November </option>
													<option value="12"> December</option>
												</select>
												<script>
														document.forms[0].sel_month.value='<%=sel_month%>';
												</script>
				            				</th>
				            				<% if(flag.equalsIgnoreCase("Y")){ %>
				            					<th>Select Unit:</th>
				            					<th>
											  		<select name="comm" onchange="refresh();">
														<option value="0">-- Select Unit --</option>
														<option value="A">All Unit</option>
														<%for (int i = 0; i < Vcomm_dtl_id.size(); i++) {%>
														<option value="<%=Vcomm_dtl_id.elementAt(i)%>"><%=Vcomm_dtl_nm.elementAt(i)%>
														</option>
														<%}%>
													</select>
													<script>
													 		document.forms[0].comm.value ='<%=comm_id%>' ;		
													</script>
												</th>
											<%} %>
											
											<th>Select Contract:</th>
											<th>
												<select name="map" onchange="refresh();">
													<option value="0">--Contract List--</option>
													<%for (int i = 0; i < Vmapp_id.size(); i++) {%>
													<option value="<%=Vmapp_id.elementAt(i)%>"><%=Vparty_abr.elementAt(i).toString().trim()%>
													</option>
													<%}%>
												</select>
												<script>
														document.forms[0].map.value = '<%=map%>'
														if(document.forms[0].map.value=='' || document.forms[0].map.value==' ')
														{
															document.forms[0].map.value = '0';
														}
												</script>
											</th>
											<th>Contract Type :</th>
											<th><%=cont_typ %></th>
				            			</tr>
				            		</thead>
				            	</table>
				            </div>
				            <div class="card-body table-responsive p-0" style="height: 310px;">
				            	<table class="table table-head-fixed text-nowrap table-bordered">
				            		<%if(Vmapp_id.size()>0 && !map.equalsIgnoreCase("Sel")) { %>
				            			<%if(cont_type.equalsIgnoreCase("GTA")){ %>
						            		<thead>
						            			<tr>
						            				<th class="text-center">NO</th>
													<th class="text-center">Quantity Name</th>
													<th class="text-center">Quantity</th>
						            			</tr>
						            		</thead>
						            		<tbody>
							            		<%if(Vqty_code.size()>0) { %>
							            			 <%for (int l=0 ; l < Vqty_code.size() ; l++)
													   { 	
													    	String code=(String)Vqty_code.elementAt(l) ;
													    	String desc=(String)Vqty_desc.elementAt(l);
													    	double  temp =Double.parseDouble((String)tot_array_all[l]);
													    	String val = formatter.format(temp);
															String type="";
													 %>	
														    <%if(l%2==0) 
														      {%>
																 <tr> 		
															<%}else{ %>
															     <tr> 		
															<%}%>		  
																	<td class="text-center"><%=l+1 %></td>
																	<td class="text-center"><%=desc %></td>
																	<%//double gcals = Double.parseDouble(val)* 0.252 ;//String gcals1 = formatter.format(gcals);%>
																	<td class="text-center"><%=val%></td>
																 </tr>			
														 <%} %> 
							            		<%}else{ %>
							            			<tr>
									             		<td class="text-center">Quantity is not calculated</td>
													</tr> 
							            		<%}%>	
						            		</tbody>
						            	<%}else{ %>
						            		<%if(tot_array_all.length>0) { %>
							            		<thead>
							            			<tr>
							            				<th class="text-center">NO</th>
														<th class="text-center">Quantity Name</th>
														<th class="text-center">Quantity</th>
							            			</tr>
							            		</thead>
							            		<tbody>
							            			<%for (int l=0 ; l < gsa_Vqty_code.size() ; l++)
													  { 
													    	String code=(String)gsa_Vqty_code.elementAt(l) ;
													    	String desc=(String)gsa_Vqty_desc.elementAt(l);
													    	double  temp =Double.parseDouble((String)tot_array_all[l]);
													    	String val = formatter.format(temp);
															String type="";
															//double  temp =0;
															if(gsa_Vqty_code.elementAt(l).toString().equalsIgnoreCase("11"))
															{	
																val=formatter.format(Double.parseDouble(val)/2)+"";
															}
															else if(gsa_Vqty_code.elementAt(l).toString().equalsIgnoreCase("12"))
															{
																val=formatter.format(Double.parseDouble(val)/2)+"";
															}
															else if(gsa_Vqty_code.elementAt(l).toString().equalsIgnoreCase("13"))
															{
																val=formatter.format(Double.parseDouble(val)/month_days)+"";
															}
															else if(gsa_Vqty_code.elementAt(l).toString().equalsIgnoreCase("14"))
															{
																val=formatter.format(Double.parseDouble(val)/month_days)+"";
															}
															else if(gsa_Vqty_code.elementAt(l).toString().equalsIgnoreCase("16"))
															{
																val=(Double.parseDouble(val)/month_days)+"";
															}
															else if(gsa_Vqty_code.elementAt(l).toString().equalsIgnoreCase("18"))
															{
																val=(Double.parseDouble(val)/month_days)+"";
															}
														 %>	
					            						 <tr> 		
															 <td class="text-center"><%=l+1 %></td>
															 <td class="text-center"><%=desc %></td>
															 <%//double gcals = Double.parseDouble(val)* 0.252 ;//String gcals1 = formatter.format(gcals);%>
															 <td class="text-center"><%=val %></td>
														 </tr>
													<%}%>
							            		
									            <%}else{ %>
									            			<tr>
											             		<td class="text-center">Quantity is not calculated</td>
															</tr> 
									            <%}%>
									     </tbody>
									<%} %>
				            	<%}else{ %>
			            			<tbody>
			            				<tr>
								             <td class="text-center">No Contracts Present</td>
								        </tr>
			            			</tbody>
								<%} %>
				            	</table>
				            </div>
				        </div>
			        </div>		
          		</div>
          	</div>
   		</section>
<input type="hidden" name="mappingcd" value="<%//=mappingId %>">
<input type="hidden" name="curryear" value="<%=Current_year %>">
<input type="hidden" name="comp_cd1" value="<%=comm_id %>">
<input type="hidden" name="flag" value="<%=flag %>">
<input type="hidden" name="flag_1" value="<%//=flag1 %>">
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
<!-- AdminLTE App -->
<script src="../dist/js/adminlte.min.js"></script>
</body>
</html>