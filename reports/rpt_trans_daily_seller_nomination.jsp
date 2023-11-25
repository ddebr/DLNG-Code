<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css" >
<link rel="stylesheet" href="../css/pt_sans.css">
<link rel="stylesheet" href="../css/jquery-ui.css">
<link rel="stylesheet" href="../css/style.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js" ></script>

<style type="text/css">
table tr{
   color: black;
}
.select {
    width: 200px;
    height: 27px;
} 
</style>
<head>
<title>DLNG | Transporter Daily Seller Nomination Details</title>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Seller_Report_Generation" id="contMgmt" scope="page"/>   
<%
	NumberFormat nf = new DecimalFormat("###########0.00");
	double convt_mmbtu_to_mt = (double) session.getAttribute("convt_mmbtu_to_mt");

	utilBean.init();
	String curr_dt = utilBean.getGen_dt();
	String next_dt = utilBean.getDate_tomorrow();
	String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
	String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");

	String gas_dt = request.getParameter("gas_date")==null?next_dt:request.getParameter("gas_date");
	String index = request.getParameter("index")==null?"":request.getParameter("index");
	String trans_cd = request.getParameter("trans_cd")==null?"":request.getParameter("trans_cd");
	String seq_no = request.getParameter("seq_no")==null?"":request.getParameter("seq_no");
	String supp_seq_no = request.getParameter("supp_seq_no")==null?"":request.getParameter("supp_seq_no");
// 	System.out.println("index---"+index);

	contMgmt.setGas_date(gas_dt);
	contMgmt.setTrans_cd(trans_cd);
	contMgmt.setSeq_no(seq_no);
	contMgmt.setSupp_seq_no(supp_seq_no);
	contMgmt.setConvt_mmbtu_to_mt(convt_mmbtu_to_mt);
	
	contMgmt.setCallFlag("PREVIEW_DAILY_SELLER_NOM_TRANSPORTER");
	contMgmt.init();
	
	String supp_con_desig = contMgmt.getSupp_con_desig();
	String to_fax_no =contMgmt.getTo_fax_no();
	String from_fax_no =contMgmt.getFrom_fax_no();
	
	String gen_date = contMgmt.getGen_date();
	String gen_time =contMgmt.getGen_time();
	
	Vector SUPP_SEQ_NO = contMgmt.getSUPP_SEQ_NO();
	Vector SUPP_CONTACT_PERSON = contMgmt.getSUPP_CONTACT_PERSON();
	Vector MOBILE = contMgmt.getMOBILE();
	Vector PHONE = contMgmt.getPHONE();
	
	Vector TRUCK_NO = contMgmt.getTRUCK_NO();
	Vector TRUCK_VOL = contMgmt.getTRUCK_VOL();
	Vector TRUCK_SCH_TM = contMgmt.getTRUCK_SCH_TM();
	Vector TRUCK_SCH_RMK = contMgmt.getTRUCK_SCH_RMK();
	Vector PLANT_NM = contMgmt.getPLANT_NM();
	Vector CUSTOMER_ABBR = contMgmt.getCUSTOMER_ABBR();
	Vector CONTRACT_TYPE = contMgmt.getCONTRACT_TYPE();
	Vector SN_NO = contMgmt.getSN_NO();
	Vector DRIVER_NM = contMgmt.getDRIVER_NM();
	Vector TRUCK_LINKED_FLG = contMgmt.getTRUCK_LINKED_FLG();
	Vector NOM_REV_NO = contMgmt.getNOM_REV_NO();
	
	double tot_mmbtu = contMgmt.getTot_mmbtu();
	double tot_ton = contMgmt.getTot_ton();
	String trans_abbr = contMgmt.getTrans_abbr();
	Vector CHECKPOST_NM = contMgmt.getCHECKPOST_NM();
%>	
<div class="tab-content">
	<div class="tab-pane active" >
		<div class="box mb-0">
			<div class="box-header with-border">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12" style="background-color: #fffff8;">
						<div class="col-xs-12" >
					  		<div class="table-responsive" >
					    		<table class="table table-striped" >
					    			<tr>
					    				<th class="text-center" colspan="12" style="color: Black;font-size: 18px;" > Seller's Daily Nomination to Transporter (<%=trans_abbr %>) - <%=gas_dt%><br>Electronic Transmission </th>
					    			</tr>
					    		</table>
					    	</div>
					    </div>
					    
					    <div class="col-xs-6" >
						    <div class="table-responsive"  >
					    		<table class="table table-striped"  >
					    			<tr>
					    				<td class="info" >To</td>
					    				<td id="to"></td>
					    			</tr>
					    			<tr>
					    				<td class="info" >Transporter Name</td>
					    				<td id="trans_nm"></td>
					    			</tr>
					    			
					    			<tr>
					    				<td class="info" >Fax No</td>
					    				<td><%=to_fax_no %></td>
					    			</tr>
					    			
					    		</table>
					    	</div>
				    	</div>
				    	<div class="col-xs-6" >
						    <div class="table-responsive"  >
					    		<table class="table table-striped"  >
					    			<tr>
					    				<td class="info" >Sent Date</td>
					    				<td><%=gen_date%></td>
					    			</tr>
					    			<tr>
					    				<td class="info" >Sent Time</td>
					    				<td><%=gen_time%></td>
					    			</tr>
					    			
					    			<%-- <tr>
					    				<td class="info" >Seq. No</td>
					    				<td><%//=nom_rev_no%></td>
					    			</tr> --%>
					    		</table>
					    	</div>
				    	</div>
				    		
				    	<div class="col-xs-12" >
						    <div class="table-responsive"  >
					    		<table class="table table-striped"  >
					    			<tr>
					    				<td class="info" >&nbsp;</td>
					    			</tr>
					    		</table>
					    	</div>
				    	</div>
				    	<div class="col-xs-12" >
						    <div class="table-responsive"  >
					    		<table class="table table-striped"  >
					    			<tr>
					    				<td class="info" >From</td>
					    				<td><%=supp_con_desig%></td>
					    			</tr>
					    			<tr>
					    				<td class="info" >&nbsp;</td>
					    				<td>Shell Energy India Private Limited</td>
					    			</tr>
					    			<tr>
					    				<td class="info" >Fax No</td>
					    				<td><%=from_fax_no%></td>
					    			</tr>
					    		
					    			<tr>
					    				<td class="info" >Subject</td>
					    				<td>Seller's Daily Nomination to Transporter (<%=trans_abbr %>) - <%=gas_dt%></td>
					    			</tr>	
					    		</table>
					    	</div>
				    	</div>	
				    	
				    	<div class="col-xs-12" >
						    <div class="table-responsive"  >
					    		<table class="table table-striped"  >
					    			<tr>
<!-- 					    				<td class="info" >From</td> -->
					    				<td>Dear Madam / Sir,
										<P>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;As per the requirements of Clause&nbsp;
									     , we notify as follows:</P></td>
					    			</tr>
					    		</table>
					    	</div>
					    </div>
					    
					    <div class="col-xs-12" >
						    <div class="table-responsive"  >
					    		<table class="table table-striped"  >
					    			<tr>
					    				<td class="info" colspan="1">Contact Name</td>
									    <%if(SUPP_SEQ_NO.size()==1){%>
									    	<td><%=SUPP_CONTACT_PERSON.elementAt(0)%></td>
									    <%}%>
									    <%if(SUPP_SEQ_NO.size()>=2){%>
									    	<td ><%=SUPP_CONTACT_PERSON.elementAt(0)%></td>
									    	<td><%=SUPP_CONTACT_PERSON.elementAt(1)%></td>
									    <%}%>
					    			</tr>
					    			
					    			<tr>
					    				<td class="info" colspan="1">Telephone Number</td>
									    <%if(SUPP_SEQ_NO.size()==1){%>
									    	<td><%=PHONE.elementAt(0)%></td>
									    <%}%>
									    <%if(SUPP_SEQ_NO.size()>=2){%>
									    	<td ><%=PHONE.elementAt(0)%></td>
									    	<td><%=PHONE.elementAt(1)%></td>
									    <%}%>
					    			</tr>
					    			
					    			<tr>
					    				<td class="info" colspan="1">Mobile Number</td>
									    <%if(SUPP_SEQ_NO.size()==1){%>
									    	<td><%=MOBILE.elementAt(0)%></td>
									    <%}%>
									    <%if(SUPP_SEQ_NO.size()>=2){%>
									    	<td ><%=MOBILE.elementAt(0)%></td>
									    	<td><%=MOBILE.elementAt(1)%></td>
									    <%}%>
					    			</tr>
					    		</table>
					    	</div>
					    </div>
					    
					    <div class="col-xs-12" >
						    <div class="table-responsive"  >
					    		<table class="table table-striped text-center" border="1"  >
					    			<tr style="vertical-align: middle;">
					    				<td class="info" rowspan="2" colspan="1" width="10%" style="vertical-align: middle;">Customer ABBR</td>
					    				<td class="info" rowspan="2" colspan="1" width="10%" style="vertical-align: middle;">Plant ABBR</td>
					    				<td class="info" rowspan="2" colspan="1" width="10%" style="vertical-align: middle;">SN/LoA No.</td>
					    				<td class="info" colspan="3" style="vertical-align: middle;">Truck Confirmation</td>
					    				<td class="info" colspan="3" style="vertical-align: middle;">Nomination </td>
					    				<td class="info" rowspan="2" colspan="1" style="vertical-align: middle;">Check Post</td>
					    				<td class="info" rowspan="2" colspan="1" style="vertical-align: middle;">Seller's Comments</td>
					    			</tr>
					    			
					    			<tr>
					    				<td class="info" >No.</td>
					    				<td class="info" >Scheduled Time</td>
					    				<td class="info" >Driver Name</td>
					    				<td class="info" >MMBTU</td>
					    				<td class="info" >Tonne(s)</td>
					    				<td class="info" >Revision No.</td>
					    			</tr>
					    			
					    			<%for(int i = 0 ; i < TRUCK_NO.size(); i++) {%>
						    			<tr>
						    				<td><%=CUSTOMER_ABBR.elementAt(i) %></td>
						    				<td><%=PLANT_NM.elementAt(i) %></td>
						    				<td><%if(CONTRACT_TYPE.elementAt(i).equals("S")) {%>
													SN-<%= SN_NO.elementAt(i)%>
												<%}else{ %>
													LoA-<%= SN_NO.elementAt(i)%>
												<%} %>
											</td>
						    				<td><%=TRUCK_NO.elementAt(i) %></td>
						    				<td><%=TRUCK_SCH_TM.elementAt(i) %></td>
						    				<td><%=DRIVER_NM.elementAt(i) %></td>
						    				<td class="text-right"><%=TRUCK_VOL.elementAt(i) %></td>
						    				<td class="text-right"> <%=nf.format(Double.parseDouble(TRUCK_VOL.elementAt(i)+"") / convt_mmbtu_to_mt) %> </td>
						    				<td><%=NOM_REV_NO.elementAt(i) %></td>
						    				<td class="text-left">
												<%=CHECKPOST_NM.elementAt(i) %>
											</td>
						    				<td class="text-left">
												<input type="text"  readonly="readonly" value="<%=TRUCK_SCH_RMK.elementAt(i) %>">
											</td>
						    			</tr>
					    			<%} %>
					    			<tr class="info" style="font-weight: bold;" >
									 	<td colspan="6" class="text-center">Total </td>
									 	<td colspan="1" class="text-right"><%=nf.format(tot_mmbtu) %></td>
									 	<td colspan="1" class="text-right"><%=nf.format(tot_ton) %></td>
									 	<td colspan="2"></td>
									 </tr>
					    			<tr>
					    				<td colspan="12" class="text-left">
					    					<b>Thanking You,<br>For Shell Energy India Private Limited,&nbsp;
											<br><br>
												<%=supp_con_desig%>		
											<br><br>Authorised Signatory</b>
					    				</td>
					    			</tr> 
					    			<tr>
					    				<td colspan="12" class="text-left">
					    				Note: In case of late arrival of truck, kindly note that the Truck would be scheduled and accepted on the reasonable endeavour basis.	
					    				</td>
					    			</tr>
					    			
					    			<tr valign="center">
										<td colspan="12" class="content1"><div align="center"><font size="1.5px" face="Arial"><br><br><b> This is a computer generated Report.</b></font></div></td>
									</tr>
					    		</table>
					    	</div>
					    </div>			
					  </div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
var ind = '<%=index%>';  
var e = window.opener.document.getElementById("to"+ind);
var text = e. options[e. selectedIndex]. text;

document.getElementById("to").innerHTML = text;
document.getElementById("trans_nm").innerHTML = window.opener.document.getElementById("trans_nm"+ind).value;

</script>
</html>
					    