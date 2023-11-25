<%@page import="java.text.NumberFormat"%>
<html>
<head>
<script>

</script>

<title>TIMS : Hazira LNG Pvt. Ltd.</title>

<%@ page import ="java.util.Properties" %>
<%@ page import ="java.util.Vector" %>
<%@ page import ="java.net.*" %>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.DB_UserProfile" id="profile" scope="request"/>
<%
	String stemp="";
    String userId=request.getParameter("userId")==null?"":request.getParameter("userId");
    System.out.println("User CD: " +userId);
    String empImg=(String)session.getAttribute("user_cd");
  
    
    profile.setUserId(userId);
    profile.init();

    String Uan_No=profile.getUan_No();
    String Lic_No=profile.getLic_No();
    String Pf_No=profile.getPf_No();
    String Lic_Policy=profile.getLic_Policy();
    String Super_lic=profile.getSUPERANNUATION_LIC();
    String TAX_DEDUCTION=profile.getTAX_DEDUCTION();
    String TAX_RATE=profile.getTAX_RATE();
    String EXIT_LOAD=profile.getEXIT_LOAD();
    String NET_VAL=profile.getNET_VAL();
    String BANK_ACCOUNT_NO=profile.getBANK_ACCOUNT_NO();
    String BANK_NAME=profile.getBANK_NAME();
    String Beneficiary_nm=profile.getBeneficiary_nm();
    NumberFormat nf3 = new java.text.DecimalFormat("###,###,###,##0.00");
//  String Uan_No="";
//     String Lic_No="";
//     String Pf_No="";
//     String Lic_Policy="";
    empImg=empImg+".jpg";
    
%>
<body>
<form id="form1" method="post">
	<table  width="100%" cellspacing='1' cellpadding='2' border="1" style="border: 1px solid gray;font-size: xx-small;">
		<TR style="background:lightblue;">
			<TD align="left" WIDTH="40%" style="background:lightblue;"><FONT SIZE=2 COLOR=BLACK><B>Profile: </B></FONT>&nbsp;</TD><TD WIDTH="60%" align="left" TITLE="<%=session.getAttribute("user_cd") %>"><img src="../images/Employee_images/<%=empImg %>" height="64" width="64">&nbsp;<FONT SIZE=2 COLOR=BLACK><B><%=session.getAttribute("username") %></B></FONT></TD>
		</TR>
		 <TR>
                        <TD align="left" WIDTH="40%" style="background:lightgrey;"><FONT SIZE=2 COLOR=BLACK><B>PF No.: </B></FONT></TD><TD align="left">&nbsp;<FONT SIZE=2 COLOR=BLACK><B><%=Pf_No==null?"":Pf_No %></B></FONT></TD>
                </TR>
		<TR>
			<TD align="left" WIDTH="40%" style="background:lightgrey;"><FONT SIZE=2 COLOR=BLACK><B>UAN No.: </B></FONT></TD><TD align="left">&nbsp;<FONT SIZE=2 COLOR=BLACK><B><%=Uan_No==null?"":Uan_No %></B></FONT></TD>
		</TR>
		<TR>
			<TD align="left" WIDTH="40%" style="background:lightgrey;"><FONT SIZE=2 COLOR=BLACK><B>LIC Id: </B></FONT></TD><TD align="left">&nbsp;<FONT SIZE=2 COLOR=BLACK><B><%=Lic_No==null?"":Lic_No %></B></FONT></TD>
		</TR>
		<TR>
			<TD align="left" WIDTH="40%" style="background:lightgrey;"><FONT SIZE=2 COLOR=BLACK><B>LIC Policy: </B></FONT></TD><TD align="left">&nbsp;<FONT SIZE=2 COLOR=BLACK><B><%=Lic_Policy==null?"":Lic_Policy %></B></FONT></TD>
		</TR>
<!-- 		<TR> -->
<!-- 			<TD align="left" WIDTH="40%" style="background:lightgrey;" colspan="2"><FONT SIZE=2 COLOR=red><B>Details of your Superannuation Accumulations: </B></FONT></TD> -->
<!-- 		</TR> -->
<!-- 		<TR> -->
<%-- 			<TD align="left" WIDTH="40%" style="background:lightgrey;"><FONT SIZE=2 COLOR=BLACK><B>Superannuation Contribution received from LIC(includes Interest till Feb2018): </B></FONT></TD><TD align="left">&nbsp;<FONT SIZE=2 COLOR=BLACK><B><%=Super_lic %></B></FONT></TD> --%>
<!-- 		</TR> -->
<!-- 		<TR> -->
<%-- 			<TD align="left" WIDTH="40%" style="background:lightgrey;"><FONT SIZE=2 COLOR=BLACK><B>Tax Deduction: </B></FONT></TD><TD align="left">&nbsp;<FONT SIZE=2 COLOR=BLACK><B><%=TAX_DEDUCTION %></B></FONT></TD> --%>
<!-- 		</TR> -->
<!-- 			<TR> -->
<%-- 			<TD align="left" WIDTH="40%" style="background:lightgrey;"><FONT SIZE=2 COLOR=BLACK><B>Tax Rate: </B></FONT></TD><TD align="left">&nbsp;<FONT SIZE=2 COLOR=BLACK><B><%=TAX_RATE%></B></FONT></TD> --%>
<!-- 		</TR> -->
<!-- 		<TR> -->
<%-- 			<TD align="left" WIDTH="40%" style="background:lightgrey;"><FONT SIZE=2 COLOR=BLACK><B>Exit Load (if any): </B></FONT></TD><TD align="left">&nbsp;<FONT SIZE=2 COLOR=BLACK><B><%=EXIT_LOAD %></B></FONT></TD> --%>
<!-- 		</TR> -->
<!-- 		<TR> -->
<%-- 			<TD align="left" WIDTH="40%" style="background:lightgrey;"><FONT SIZE=2 COLOR=BLACK><B>Net Value transferred to your account: </B></FONT></TD><TD align="left">&nbsp;<FONT SIZE=2 COLOR=BLACK><B><%=NET_VAL %></B></FONT></TD> --%>
<!-- 		</TR> -->
<!-- 		<TR> -->
<%-- 			<TD align="left" WIDTH="40%" style="background:lightgrey;"><FONT SIZE=2 COLOR=BLACK><B>Bank Account: </B></FONT></TD><TD align="left">&nbsp;<FONT SIZE=2 COLOR=BLACK><B><%=BANK_ACCOUNT_NO %></B></FONT></TD> --%>
<!-- 		</TR> -->
<!-- 		<TR> -->
<%-- 			<TD align="left" WIDTH="40%" style="background:lightgrey;"><FONT SIZE=2 COLOR=BLACK><B>Bank Name: </B></FONT></TD><TD align="left">&nbsp;<FONT SIZE=2 COLOR=BLACK><B><%=BANK_NAME %></B></FONT></TD> --%>
<!-- 		</TR> -->
<!-- 		<TR> -->
<%-- 			<TD align="left" WIDTH="40%" style="background:lightgrey;"><FONT SIZE=2 COLOR=BLACK><B>Beneficiary Name: </B></FONT></TD><TD align="left">&nbsp;<FONT SIZE=2 COLOR=BLACK><B><%=Beneficiary_nm %></B></FONT></TD> --%>
<!-- 		</TR> -->
		
<!--		<TR>
			<TD align="left" WIDTH="20%" style="background:lightgrey;"><FONT SIZE=2 COLOR=BLACK><B>PF No.: </B></FONT></TD><TD align="left">&nbsp;<FONT SIZE=2 COLOR=BLACK><B><%=Pf_No %></B></FONT></TD>
		</TR> -->
		<TR style="background:lightblue;">
			<TD align="left"  colspan="2" style="background:lightblue;">&nbsp;</TD>
		</TR>
	</TABLE>
</form>
</body>
</html>
