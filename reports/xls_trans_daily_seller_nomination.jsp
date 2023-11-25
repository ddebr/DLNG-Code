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
<meta http-equiv="Content-Type" content="<%response.setHeader("Content-Disposition","attachment;filename=Transporter_Daily_Seller_Nomination.xls");%>">

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
	
	double tot_mmbtu = contMgmt.getTot_mmbtu();
	double tot_ton = contMgmt.getTot_ton();

	String trans_name = contMgmt.getTrans_name();
	String temp_con_desig = contMgmt.getTemp_con_desig();
	String trans_abbr = contMgmt.getTrans_abbr();
	Vector NOM_REV_NO = contMgmt.getNOM_REV_NO();
	Vector CHECKPOST_NM = contMgmt.getCHECKPOST_NM();
%>	
		<table >
			<tr>
				<th  colspan="12" style="color: Black;font-size: 18px;" > Seller's Daily Nomination to Transporter (<%=trans_abbr %>) - <%=gas_dt%><br>Electronic Transmission </th>
			</tr>
		</table>

		<table >
			<tr>
				<td >To</td>
				<td id="to"><%=temp_con_desig %></td>
			</tr>
			<tr>
				<td >Transporter Name</td>
				<td id="trans_nm"> <%=trans_name %></td>
			</tr>
			<tr>
				<td >Fax No</td>
				<td><%=to_fax_no %></td>
			</tr>
			
		</table>
		<table >
			<tr>
				<td >Sent Date</td>
				<td align="left"><%=gen_date%></td>
			</tr>
			<tr>
				<td >Sent Time</td>
				<td align="left"><%=gen_time%></td>
			</tr>
		</table>
		<table >
			<tr>
				<td >&nbsp;</td>
			</tr>
		</table>
		<table >
			<tr>
				<td >From</td>
				<td><%=supp_con_desig%></td>
			</tr>
			<tr>
				<td >&nbsp;</td>
				<td>Shell Energy India Private Limited</td>
			</tr>
			<tr>
				<td >Fax No</td>
				<td align="left"><%=from_fax_no%></td>
 			</tr>
 		
 			<tr>
 				<td >Subject</td>
 				<td colspan="10">Seller's Daily Nomination to Transporter (<%=trans_abbr %>) - <%=gas_dt%></td>
 			</tr>	
 		</table>
 		<br>
	<table >
		<tr>
			<td colspan="4" ><font size="2" face="Verdana, Arial, Helvetica, sans-serif">Dear Madam / Sir,</font>
<P>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;As per the requirements of Clause, we notify as follows: 
		</tr>
	</table>
 
<table border="1">
	<tr>
		<td colspan="1">Contact Name</td>
    <%if(SUPP_SEQ_NO.size()==1){%>
   	<td><%=SUPP_CONTACT_PERSON.elementAt(0)%></td>
   <%}%>
   <%if(SUPP_SEQ_NO.size()>=2){%>
   	<td ><%=SUPP_CONTACT_PERSON.elementAt(0)%></td>
   	<td><%=SUPP_CONTACT_PERSON.elementAt(1)%></td>
   <%}%>
		</tr>
		
		<tr>
			<td colspan="1">Telephone Number</td>
   <%if(SUPP_SEQ_NO.size()==1){%>
   	<td align="left"><%=PHONE.elementAt(0)%></td>
   <%}%>
   <%if(SUPP_SEQ_NO.size()>=2){%>
   	<td align="left"><%=PHONE.elementAt(0)%></td>
   	<td align="left"><%=PHONE.elementAt(1)%></td>
   <%}%>
		</tr>
		
		<tr>
			<td colspan="1">Mobile Number</td>
   <%if(SUPP_SEQ_NO.size()==1){%>
   	<td align="left"><%=MOBILE.elementAt(0)%></td>
   <%}%>
   <%if(SUPP_SEQ_NO.size()>=2){%>
   	<td align="left"><%=MOBILE.elementAt(0)%></td>
   	<td align="left"><%=MOBILE.elementAt(1)%></td>
   <%}%>
		</tr>
	</table>

	<table border="1">
		<tr style="vertical-align: middle;">
			<th rowspan="2" colspan="1" width="10%" style="vertical-align: middle;">Customer ABBR</th>
			<th rowspan="2" colspan="1" width="10%" style="vertical-align: middle;">Plant ABBR</th>
			<th rowspan="2" colspan="1" width="10%" style="vertical-align: middle;">SN/LoA No.</th>
			<th colspan="3" style="vertical-align: middle;">Truck Confirmation</th>
			<th colspan="3" style="vertical-align: middle;">Nomination </th>
			<th rowspan="2" colspan="1" style="vertical-align: middle;">Check Post</th>
			<th rowspan="2" colspan="1" style="vertical-align: middle;">Seller's Comments</th>
		</tr>
		
		<tr>
			<th >No.</th>
			<th >Scheduled Time</th>
			<th >Driver Name</th>
			<th >MMBTU</th>
			<th >Tonne(s)</th>
			<th >Revision No.</th>
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
				<td align="center"> <%=NOM_REV_NO.elementAt(i) %></td>
				<td class="text-left"><%=CHECKPOST_NM.elementAt(i) %></td>
				<td class="text-left"><%=TRUCK_SCH_RMK.elementAt(i) %></td>
			</tr>
		<%} %>
		<tr style="font-weight: bold;" >
	<td colspan="6" >Total </td>
	<td colspan="1" class="text-right"><%=nf.format(tot_mmbtu) %></td>
	<td colspan="1" class="text-right"><%=nf.format(tot_ton) %></td>
	<td colspan="3"></td>
</tr>

<tr></tr><tr></tr>
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
</html>
					    