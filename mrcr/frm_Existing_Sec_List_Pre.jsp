<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Existing Security Details</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript" src="../js/date-picker.js"></script>
<script language="JavaScript" src="../js/aris.js"></script>
<script language="JavaScript" src="../js/util2.js"></script>
<script language="JavaScript" src="../js/util1.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>

<link href="../css/guidefaultGeneral.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../css/utilities.css">
<script type="text/javascript">
function modify1(customer_abbr,sec_type,flagSec,StartDt,EndDt,Amt,Rmk,flag,DealCd,sec_due_dt,sec_ref_no,currency,deal_type,vari_value,value_fluc,iss_b_cd,adv_b_cd,adv_b_ref,con_b_cd,con_b_ref,tenor,seq_no,iss_b_ref)
{
	window.opener.modify1(customer_abbr,sec_type,flagSec,StartDt,EndDt,Amt,Rmk,flag,DealCd,sec_due_dt,sec_ref_no,currency,deal_type,vari_value,value_fluc,iss_b_cd,adv_b_cd,adv_b_ref,con_b_cd,con_b_ref,tenor,seq_no,iss_b_ref)
	window.close();
}
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.mrcr.DataBean_CR_Security_Report" id=" mst" scope="page"/>
<%
	String customer_cd = request.getParameter("buyer_cd")==null?"0":request.getParameter("buyer_cd");
	String customer_abbr = request.getParameter("cust_abbr")==null?"":request.getParameter("cust_abbr"); //HARSH20210212
	String customer_nm = request.getParameter("customer_nm")==null?"":request.getParameter("customer_nm");
	String from_dt = request.getParameter("from_dt")==null?"":request.getParameter("from_dt");
	String to_dt = request.getParameter("to_dt")==null?"":request.getParameter("to_dt");
	String btn = request.getParameter("btn")==null?"":request.getParameter("btn");
	
	String fgsaNo = request.getParameter("fgsaNo")==null?"":request.getParameter("fgsaNo");
	String revNo = request.getParameter("revNo")==null?"":request.getParameter("revNo");
	String snNo = request.getParameter("snNo")==null?"":request.getParameter("snNo");
	String snRev = request.getParameter("snRev")==null?"":request.getParameter("snRev");
	String cont_type = request.getParameter("cont_type")==null?"":request.getParameter("cont_type");
	String deal_no = request.getParameter("deal_no")==null?"":request.getParameter("deal_no");
	System.out.println(btn);
	
	mst.setCallFlag("EXISTING_SECURITY_LIST_PRE");
	mst.setContSt_dt(from_dt);
	mst.setContEnd_dt(to_dt);
	mst.setBuyer_cd(customer_cd);
	mst.setFrom_date(from_dt);//HARSH20211217 AS PER INCIDENT#216
	mst.setTo_date(to_dt);//HARSH20211217 AS PER INCIDENT#216
	mst.setCustomer_ABBR(customer_abbr); //HARSH20210212
	mst.setSnNo(snNo);
	mst.setSnRevNo(snRev);
	mst.setFgsaRevNo(revNo);
	mst.setFgsaNo(fgsaNo);
	mst.setCont_type(cont_type);
	mst.setLC_Mapping_Id(deal_no); //HARSH20201203
	mst.init();
	
	Vector VSecuritySeqNo = mst.getVSecuritySeqNo();
	Vector VSecurityStartDt = mst.getVSecurityStartDt();
	Vector VSecurityEndDt = mst.getVSecurityEndDt();
	Vector VSecurityAmt = mst.getVSecurityAmt();
	Vector VSecurityStatus = mst.getVSecurityStatus();
	Vector VSecurityRating = mst.getVSecurityRating();
	Vector VSecurityRatingEffDt = mst.getVSecurityRatingEffDt();
	Vector VSecurityDueDt = mst.getVSecurityDueDt();
	Vector VSecurityRmk = mst.getVSecurityRmk();
	Vector VSecurityDealCd = mst.getVSecurityDealCd();
	Vector VSecurityType = mst.getVSecurityType();
	String total_lc_amount = "";//mst.getTotalSecurityAmt();
	String final_lc_amount = request.getParameter("final_lc_amount")==null?total_lc_amount:request.getParameter("final_lc_amount");
	Vector VflagSecurity = mst.getVflagSecurity();
	Vector Vsec_dt = mst.getVsec_dt();
	Vector VSecurityTypeDesc = mst.getVSecurityTypeDesc();
	Vector Vfin_yr = mst.getVfin_yr();
	Vector VActual_seq_no  = mst.getVActual_seq_no();
	Vector Vsec_ref_no = mst.getVsec_ref_no();
	Vector Vexisting_cont = mst.getVexisting_cont();
	Vector Vsec_due_dt = mst.getVsec_due_dt();
	Vector Vcurrency = mst.getVcurrency();
	Vector Vdeal_type=mst.getVdeal_type();
	Vector Vvalue_vari=mst.getVvalue_vari();
	Vector Vvalue_fluc=mst.getVvalue_fluc();
	Vector Viss_bank_cd=mst.getViss_bank_cd();
	Vector Viss_bank_ref=mst.getViss_bank_ref();
	Vector Vadv_bank_cd=mst.getVadv_bank_cd();
	Vector Vadv_bank_ref=mst.getVadv_bank_ref();
	Vector Vcon_bank_cd=mst.getVcon_bank_cd();
	Vector Vcon_bank_ref=mst.getVcon_bank_ref();
	Vector Vtenor=mst.getVtenor();
	Vector VSecStatus = mst.getVSecStatus();
%>
<body>
	<form action="">
<div id="col-three">

<fieldset style="width:98%" align="center">
<table width="100%" border="0">
     <tr class="highlighttext" style="background-color: #d9edf7;">
             <td colspan="12">
    			<div align="left">
					<font size="4" face="Verdana, Arial, Helvetica, sans-serif">
						<b>Security Details</b>
					</font>
				</div>
			</td>
         </tr>
    <tr>
    	
		<td class="title2" style="background-color: #d9edf7;" width="12%" colspan="2"><div align="center">&nbsp;Sr#&nbsp;</div></td>
		<td class="title2" style="background-color: #d9edf7;" width="12%"><div align="center">&nbsp;Deal No.#&nbsp;</div></td>
		<td class="title2" style="background-color: #d9edf7;" width="12%"><div align="center">&nbsp;Security Type&nbsp;</div></td>
		<td class="title2" style="background-color: #d9edf7;" width="16%"><div align="center">&nbsp;Ref. No#&nbsp;</div></td>
		<td class="title2" style="background-color: #d9edf7;" width="5%"><div align="center">Currency</div></td> 
		<td class="title2" style="background-color: #d9edf7;" width="12%"><div align="center">&nbsp;Amt&nbsp;&nbsp;</div></td>
		<td class="title2" style="background-color: #d9edf7;" width="12%"><div align="center">&nbsp;Due&nbsp;Date&nbsp;</div></td>
		<td class="title2" style="background-color: #d9edf7;" width="12%"><div align="center">&nbsp;Issuing&nbsp;Date&nbsp;</div></td>
		<td class="title2" style="background-color: #d9edf7;" width="12%"><div align="center">&nbsp;Expire&nbsp;Date&nbsp;</div></td>
		<td class="title2" style="background-color: #d9edf7;" width="12%"><div align="center">&nbsp;Remark&nbsp;</div></td>
		<td class="title2" style="background-color: #d9edf7;" width="12%"><div align="center">&nbsp;Status&nbsp;</div></td>
	</tr>
	<% 	int k = 0; for(int i=0;i<VSecurityStatus.size();i++) 
    	{	if(VflagSecurity.elementAt(i).equals(btn)){%>
		  	<tr class=<%=((i%2)==0?"content1A":"content1")%> style="background-color: white;">
		  		<td>
		  			<input type="radio" name="rd1" id="rd<%=i%>" onclick="modify1('<%=customer_abbr%>','<%=VSecurityTypeDesc.elementAt(i)%>','<%=VflagSecurity.elementAt(i)%>','<%=VSecurityStartDt.elementAt(i)%>','<%=VSecurityEndDt.elementAt(i)%>','<%=VSecurityAmt.elementAt(i)%>','<%=VSecurityRmk.elementAt(i).toString().replaceAll("'", "~~")%>','F','<%=VSecurityDealCd.elementAt(i)%>','<%=Vsec_due_dt.elementAt(i)%>','<%=Vsec_ref_no.elementAt(i)%>','<%=Vcurrency.elementAt(i)%>','<%=Vdeal_type.elementAt(i)%>','<%=Vvalue_vari.elementAt(i)%>','<%=Vvalue_fluc.elementAt(i)%>','<%=Viss_bank_cd.elementAt(i)%>','<%=Vadv_bank_cd.elementAt(i)%>','<%=Vadv_bank_ref.elementAt(i)%>','<%=Vcon_bank_cd.elementAt(i)%>','<%=Vcon_bank_ref.elementAt(i)%>','<%=Vtenor.elementAt(i)%>','<%=VActual_seq_no.elementAt(i)%>','<%=Viss_bank_ref.elementAt(i)%>')">
		  		</td>
		 		<td>
		 			<div align="center">
		   				&nbsp;<%=k=k+1%>
				  	</div>
				</td>
				<td >
		 			<div align="center">
		   				&nbsp;<%=VSecurityDealCd.elementAt(i)%><%-- <%if(Vexisting_cont.get(i)!=""){ %> (<%=Vexisting_cont.elementAt(i)%>)<%} %> --%>&nbsp;
				  	</div>
				</td>
				<td>
		 			<div align="center">
		   				&nbsp;<%=VSecurityTypeDesc.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<td>
		 			<div align="center">
		   				&nbsp;<%=Vsec_ref_no.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<td>
		 			<div align="center">
		   				&nbsp;<%=Vcurrency.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<td>
		 			<div align="right">
		   				&nbsp;<%=VSecurityAmt.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<td>
		 			<div align="center">
		   				&nbsp;<%=Vsec_due_dt.elementAt(i)%>&nbsp;
				  	</div>
				</td> 
				<td>
		 			<div align="center">
		   				&nbsp;<%=VSecurityStartDt.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<td>
		 			<div align="center">
		   				&nbsp;<%=VSecurityEndDt.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<td>
		 			<div align="right">
		   				&nbsp;<%=VSecurityRmk.elementAt(i)%>&nbsp;
				  	</div>
				</td>
				<td>
		 			<div align="right">
		   				&nbsp;<%-- <%=VSecurityStatus.elementAt(i)%> --%><%=VSecStatus.elementAt(i)%>&nbsp;
				  	</div>
				</td>
		  	</tr>
	<%	}	} if(k==0){%><tr class="content1">
				<td colspan="16">
					<b>Security Note List Not Available  !!!</b>
				</td>
			</tr><%}%>
	<%	if(VSecurityStatus.size()==0)
		{	%>
			
	<%	}	%>
	
	
  </table>
  </fieldset>
  </div>
</form>
</body>
</html>