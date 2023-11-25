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

<title>DLNG</title>

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
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
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
<title>Daily Seller Nomination Details</title>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Seller_Report_Generation" id="contMgmt" scope="page"/>   
<jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_modulelock" id="modlock" scope="request"/>
<%
	double convt_mmbtu_to_mt = (double) session.getAttribute("convt_mmbtu_to_mt");

	utilBean.init();
	String curr_dt = utilBean.getGen_dt();
	String next_dt = utilBean.getDate_tomorrow();
	String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
	String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");

	String gas_date = request.getParameter("gas_date")==null?next_dt:request.getParameter("gas_date");	
	String from_cntct = request.getParameter("from_cntct")==null?next_dt:request.getParameter("from_cntct");
	String to_cntct = request.getParameter("to_cntct")==null?curr_dt:request.getParameter("to_cntct");
	String customer_cd = request.getParameter("customer_cd")==null?curr_dt:request.getParameter("customer_cd");
	String plant_seq_no = request.getParameter("plant_seq_no")==null?curr_dt:request.getParameter("plant_seq_no");
	String plant_nm = request.getParameter("plant_nm")==null?curr_dt:request.getParameter("plant_nm");
	
	String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
	String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
	String cont_type = request.getParameter("cont_type")==null?"":request.getParameter("cont_type");
	String remarks = request.getParameter("remarks")==null?"":request.getParameter("remarks");
	String sn_ref_no = request.getParameter("sn_ref_no")==null?"":request.getParameter("sn_ref_no");
	String mapping_id = request.getParameter("mapping_id")==null?"":request.getParameter("mapping_id");//ADDDED FOR LTCORA AN CN
// 	System.out.println("gas_date****"+gas_date);
	
	contMgmt.init();	
	if(!contMgmt.getGas_date().trim().equalsIgnoreCase(""))
	{
		gas_date = contMgmt.getGas_date().trim();
	}	
	contMgmt.setCallFlag("SELLER_NOM_CUSTOMER");
	contMgmt.setConvt_mmbtu_to_mt(convt_mmbtu_to_mt);
	contMgmt.setGas_date(gas_date);
	contMgmt.setSeq_no(to_cntct);
	contMgmt.setSupp_seq_no(from_cntct);
	contMgmt.setCustomer_cd(customer_cd);
	contMgmt.setPlant_seq_no(plant_seq_no);
	contMgmt.setCont_type(cont_type);
	contMgmt.setSn_no(sn_no);
	contMgmt.setFgsa_no(fgsa_no);
	contMgmt.setCont_mapping_id(mapping_id);//ADDDED FOR LTCORA AN CN
	contMgmt.init();
					
	String supp_con_desig = contMgmt.getSupp_con_desig();
	String temp_con_desig = contMgmt.getTemp_con_desig();
	String to_fax_no =contMgmt.getTo_fax_no();
	String from_fax_no =contMgmt.getFrom_fax_no();
	
	String gen_time =contMgmt.getGen_time();
	String gas_dt = contMgmt.getGas_dt();
	String gen_date = contMgmt.getGen_date();
	String contract_dt =contMgmt.getContract_dt();
	String fgsa_dt =contMgmt.getFgsa_dt();
	//remarks = contMgmt.getRemarks();
	String cust_nm	 =contMgmt.getCust_nm();
	String nom_rev_no = contMgmt.getNom_rev_no();
	//String cust_abbr  =contMgmt.getCust_abbr();
	
	String seller_nom_clause = contMgmt.getSeller_nom_clause();
	String tender_seller_nom_clause = contMgmt.getTender_seller_nom_clause();
	
// 	Vector TRANS_CD = contMgmt.getTRANS_CD();
// 	Vector TRANS_COUNT = contMgmt.getTRANS_COUNT();
	Vector TRANSPORTER_CD = contMgmt.getTRANSPORTER_CD();
	Vector TRUCK_NO = contMgmt.getTRUCK_NO();
	Vector TRUCK_VOL = contMgmt.getTRUCK_VOL();
	Vector TRUCK_SCH_TM = contMgmt.getTRUCK_SCH_TM();
	Vector TRUCK_SCH_DT = contMgmt.getTRUCK_SCH_DT();
	Vector TRUCK_SCH_RMK = contMgmt.getTRUCK_SCH_RMK();
	Vector QTY_MMBTU = contMgmt.getQTY_MMBTU();	
	Vector TRANS_ABBR = contMgmt.getTRANS_ABBR();	
	Vector PLANT_ABBR = contMgmt.getPLANT_ABBR();
	
	Vector SUPP_SEQ_NO = contMgmt.getSUPP_SEQ_NO();
	Vector SUPP_CONTACT_PERSON = contMgmt.getSUPP_CONTACT_PERSON();
	Vector MOBILE = contMgmt.getMOBILE();
	Vector PHONE = contMgmt.getPHONE();
	
	String ltcora_dt=contMgmt.getLtcora_dt();
	String ltcora_seller_nom_clause=contMgmt.getLtcora_seller_nom_clause();
	String ltcora_no=contMgmt.getLtcora_no();
	//System.out.println("ltcora_dt "+ltcora_dt);
	//System.out.println("ltcora_seller_nom_clause "+ltcora_seller_nom_clause);
	NumberFormat nf = new DecimalFormat("###########0.00");
	double tot_mmbtu = contMgmt.getTot_mmbtu();
	double tot_ton = contMgmt.getTot_ton();
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
					    				<th class="text-center" colspan="12" style="color: Black;font-size: 18px;" > Seller's Daily  Nomination for Date <%=gas_dt%><br>Electronic Transmission </th>
					    			</tr>
					    		</table>
					    	</div>
					    </div>	
					    <div class="col-xs-6" >
						    <div class="table-responsive"  >
					    		<table class="table table-striped"  >
					    			<tr>
					    				<td class="info" >To</td>
					    				<td><%=temp_con_desig%></td>
					    			</tr>
					    			<tr>
					    				<td class="info" >Customer Name</td>
					    				<td><%=cust_nm%></td>
					    			</tr>
					    			
					    			<tr>
					    				<td class="info" >Fax No</td>
					    				<td><%=to_fax_no%></td>
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
					    			
					    			<tr>
					    				<td class="info" >Seq. No</td>
					    				<td><%=nom_rev_no%></td>
					    			</tr>
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
				    	<div class="col-xs-6" >
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
					    				<td>Seller's Daily Nomination</td>
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
										 <%if(cont_type.equalsIgnoreCase("S")) 
										   {%>
										   		<u><%=seller_nom_clause%></u>&nbsp;of our Framework LNG Sales Agreement No: <%=fgsa_no%> dated <u><%=fgsa_dt%></u> and Supply Notice No: <%if(sn_ref_no.trim().equalsIgnoreCase("")){%><%=sn_no%><%}else{%><%=sn_ref_no%><%}%> dated <u><%=contract_dt%></u> 
									     <%}
										   else if(cont_type.equalsIgnoreCase("L")) 
										   {%>
										   		<u><%=tender_seller_nom_clause%></u>&nbsp;of our Tender Number No: <%=fgsa_no%> dated <u><%=fgsa_dt%></u> and Letter of Agreement No: <%if(sn_ref_no.trim().equalsIgnoreCase("")){%><%=sn_no%><%}else{%><%=sn_ref_no%><%}%> dated <u><%=contract_dt%></u> 
									     <%}%>
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
					    		<table class="table table-striped text-center"  >
					    			<tr>
					    				<td class="info" rowspan="2" colspan="1" width="10%">Date</td>
					    				<td class="info" colspan="2" >Nomination </td>
					    				<td class="info" colspan="2" >Truck Confirmation</td>
					    				<td class="info" rowspan="2" colspan="1">Buyer's Facility<br>(Plant Name) </td>
					    				<td class="info" rowspan="2" >Check Post</td> 
					    				<td class="info" rowspan="2" colspan="1">Seller's Comments</td>
					    			</tr>
					    			
					    			<tr>
					    				<td class="info" >MMBTU</td>
					    				<td class="info" >Tonne(s)</td>
					    				<td class="info" >No.</td>
					    				<td class="info" >Scheduled Time</td>
					    			</tr>
					    			
					    			
					    			<%	if(TRANSPORTER_CD.size()>0){
											for(int i=0;i<TRANSPORTER_CD.size();i++){%>
											 <tr>
												<td><%=TRUCK_SCH_DT.elementAt(i) %></td>
												<td class="text-right"> <%=TRUCK_VOL.elementAt(i)%> </td>
												<td class="text-right"> <%=nf.format(Double.parseDouble(TRUCK_VOL.elementAt(i)+"") / convt_mmbtu_to_mt) %> </td>
												<td class="text-center"><%=TRUCK_NO.elementAt(i) %></td>
												<td class="text-center"><%=TRUCK_SCH_TM.elementAt(i) %></td>
												<td class="text-left"><%=PLANT_ABBR.elementAt(i) %></td>
												<td class="text-left"><%=CHECKPOST_NM.elementAt(i) %></td>
												<td class="text-left">
													<textarea rows="3" cols="30" readonly="readonly"><%=TRUCK_SCH_RMK.elementAt(i) %></textarea>
												</td>											 
											 </tr>
									 <% } %>
									 <tr class="info" style="font-weight: bold;" >
									 	<td colspan="1" class="text-left">Total </td>
									 	<td colspan="1" class="text-right"><%=nf.format(tot_mmbtu) %></td>
									 	<td colspan="1" class="text-right"><%=nf.format(tot_ton) %></td>
									 	<td colspan="9"></td>
									 </tr>
									 <%} %> 
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


<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>



</html>