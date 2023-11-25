<%@ page buffer="128kb"%>
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*" errorPage="" %>

<html>
<head>
<title>DLNG | FLSA Liability Clause Details</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- For accordion  -->
<link rel="stylesheet" href="../responsive/w3/w3.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../responsive/css/bootstrap.min.css">
<!-- CSS -->
<link rel="stylesheet" href="../responsive/css/main.css">
<link rel="stylesheet" href="../responsive/css/style.css">
<!-- Font Awesome -->  
<link rel="stylesheet" href="../font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/tlu.css">
<link rel="stylesheet" href="../css/pt_sans.css">
<style type="text/css">
td {
    font-size:small;
}
.select {
    width: 200px;
    height: 27px;
} 

</style>
<!-- jQuery -->

<script language="JavaScript" src="../js/aris.js"></script>
<script language="JavaScript" src="../js/util2.js"></script>
<script language="JavaScript" src="../js/util1.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>

<script language="JavaScript">

function doSubmit()
{
	var percentage1 = document.forms[0].percentage1.value;	
	var percentage2 = document.forms[0].percentage2.value;	
	var percentage3 = document.forms[0].percentage3.value;
	var msg = "First Check The Following Fields :\n\n";	
	var flag1="true";	
	
	 for (var i=0; i < document.forms[0].lchk1.length; i++)
	 {
		if (document.forms[0].lchk1[i].checked)
		{     							
			if(document.forms[0].lchk1[i].value=="DCQ")
			{     							
			 	document.forms[0].DCQ_FLG.value="Y";     							     									
			}
			if(document.forms[0].lchk1[i].value=="PNDCQ")
			{
				document.forms[0].PNDCQ_FLG.value="Y";
			}
			if(document.forms[0].lchk1[i].value=="MDCQ")
			{
				document.forms[0].MDCQ_FLG.value="Y";
			}     							
		}
 	 }     				
	 if(document.forms[0].lchk[0].checked)
	{
	 	document.forms[0].ld_flg.value="Y";
	}
	else
	{
		document.forms[0].ld_flg.value="N";
	}
	
 	if(document.forms[0].lchk[1].checked)
 	{
 	 	document.forms[0].top_flg.value="Y";
 	}
 	else
 	{
 		document.forms[0].top_flg.value="N";
 	}
 	
 	if(document.forms[0].lchk[2].checked)
 	{
 	 	document.forms[0].mug_flg.value="Y";
 	}
 	else
 	{
 		document.forms[0].mug_flg.value="N";
 	}
 	
 	//alert("document.forms[0].mug_flg.value = "+document.forms[0].mug_flg.value+" percentage3 = "+percentage3);
 	if(document.forms[0].ld_flg.value=='Y')
 	{
	 	if(percentage1==null || percentage1=='' || percentage1==' ' || percentage1=='  ')
		{
				msg += "Please, First Select Price (% of Contract Price) Field  of Liquidated Damages !!!\n";
				flag1 = false;
		}
	}
	if(document.forms[0].top_flg.value=='Y')
 	{
		if(percentage2==null || percentage2=='' || percentage2==' ' || percentage2=='  ')
		{
				msg += "Please, First Select Price (% of Contract Price) Field of Take Or Pay  !!!\n";
				flag1 = false;
		}
	}
	
	if(document.forms[0].mug_flg.value=='Y')
 	{
		if(percentage3==null || percentage3=='' || percentage3==' ' || percentage3=='  ')
		{
				msg += "Please, First Select Price (% of Contract Price) Field of Make Up Gas  !!!\n";
				flag1 = false;
		}
	}  
	if(flag1)
	{
		var a = confirm("Do You Want To Submit Data Regarding This FLSA Liability Clause ???");	
		if(a)
		{
			document.forms[0].submit();
		}
	}
	else
	{
		alert(msg);
	}    				     					
}

function setvalues(price_per1,price1,promise_qty1,lower_per1,dcq,pndcq,mdcq,remarks1,price_per2,price2,top_per,actual_oblig,promise_qty2,remarks2,makeup_period ,rec_period,price_per3,price3,remarks3)
{
	document.forms[0].percentage1.value=price_per1;
	document.forms[0].price1.value=price1;
	document.forms[0].promiseOn1.value=promise_qty1;
	document.forms[0].low_percentage.value=lower_per1;
	if(dcq=="Y")
	{
		document.forms[0].lchk1[0].checked=true;
	}
	if(pndcq=="Y")
	{
		document.forms[0].lchk1[1].checked=true;
	}
	if(mdcq=="Y")
	{
		document.forms[0].lchk1[2].checked=true;
	}
	document.forms[0].remark.value=remarks1;
	document.forms[0].percentage2.value=price_per2;
	document.forms[0].price2.value=price2;
	document.forms[0].top_percentage.value=top_per;
	document.forms[0].obligation.value=actual_oblig;
	document.forms[0].remark2.value=remarks2;
	document.forms[0].mug_percentage.value=makeup_period;
	document.forms[0].rec_percentage.value=rec_period;
	document.forms[0].percentage3.value=price_per3;
	document.forms[0].price3.value=price3;
	document.forms[0].remark3.value=remarks3;
	document.forms[0].promiseOn2.value=promise_qty2;
}


function setComponent(ld,top,makeup)
{
	if(ld=="Y")
	{
		document.forms[0].lchk[0].checked=true;
	}
	if(top=="Y")
	{
		document.forms[0].lchk[1].checked=true;
	}
	if(makeup=="Y")
	{
		document.forms[0].lchk[2].checked=true;
	}

	if(ld=="N")
	{
		document.forms[0].percentage1.disabled=true;
		document.forms[0].price1.disabled=true;
		document.forms[0].promiseOn1.disabled=true;
		document.forms[0].low_percentage.disabled=true;
		
		for (var i=0; i < document.forms[0].lchk1.length; i++)
		{
			document.forms[0].lchk1[i].disabled=true;
		}
		document.forms[0].remark.disabled=true;
	}

	if(top=="N")
	{
		document.forms[0].percentage2.disabled=true;
		document.forms[0].price2.disabled=true;
		document.forms[0].top_percentage.disabled=true;
		document.forms[0].obligation.disabled=true;
		document.forms[0].remark2.disabled=true;
		document.forms[0].promiseOn2.disabled=true;
	}

	if(makeup=="N")
	{
		document.forms[0].mug_percentage.disabled=true;
		document.forms[0].rec_percentage.disabled=true;
		document.forms[0].percentage3.disabled=true;
		document.forms[0].price3.disabled=true;
		document.forms[0].remark3.disabled=true;
	}
}



function enableTOPDetails(flag)
{
	if(document.forms[0].lchk[1].checked)
	{
		document.forms[0].percentage2.disabled=false;
		document.forms[0].price2.disabled=false;
		document.forms[0].top_percentage.disabled=false;
		document.forms[0].obligation.disabled=false;
		document.forms[0].remark2.disabled=false;
		document.forms[0].promiseOn2.disabled=false;
	}
	else
	{
		document.forms[0].percentage2.disabled=true;
		document.forms[0].price2.disabled=true;
		document.forms[0].top_percentage.disabled=true;
		document.forms[0].obligation.disabled=true;
		document.forms[0].remark2.disabled=true;
		document.forms[0].promiseOn2.disabled=true;	
	}
}



function enableMUGDetails(flag)
{
	if(document.forms[0].lchk[2].checked)
	{
		document.forms[0].mug_percentage.disabled=false;
		document.forms[0].rec_percentage.disabled=false;
		document.forms[0].percentage3.disabled=false;
		document.forms[0].price3.disabled=false;
		document.forms[0].remark3.disabled=false;
	}
	else
	{
		document.forms[0].mug_percentage.disabled=true;
		document.forms[0].rec_percentage.disabled=true;
		document.forms[0].percentage3.disabled=true;
		document.forms[0].price3.disabled=true;
		document.forms[0].remark3.disabled=true;
	}
}



function enableLDDetails(flag)
{
	if(document.forms[0].lchk[0].checked)
	{
		document.forms[0].percentage1.disabled=false;
		document.forms[0].price1.disabled=false;
		document.forms[0].promiseOn1.disabled=false;
		document.forms[0].low_percentage.disabled=false;
		for (var i=0; i < document.forms[0].lchk1.length; i++)
		{
			document.forms[0].lchk1[i].disabled=false;
		}
		document.forms[0].remark.disabled=false;
	}
	else 
	{
		document.forms[0].percentage1.disabled=true;
		document.forms[0].price1.disabled=true;
		document.forms[0].promiseOn1.disabled=true;
		document.forms[0].low_percentage.disabled=true;
		for (var i=0; i < document.forms[0].lchk1.length; i++)
		{
			document.forms[0].lchk1[i].disabled=true;
		}
		document.forms[0].remark.disabled=true;
	}
}


function resetValues()
{
	if(document.forms[0].lchk[0].checked)
	{
		document.forms[0].percentage1.value="";
		document.forms[0].price1.value="";
		document.forms[0].promiseOn1.value="";
		document.forms[0].low_percentage.value="";
		document.forms[0].lchk1[0].checked=false;
		document.forms[0].lchk1[1].checked=false;
		document.forms[0].lchk1[2].checked=false;
		document.forms[0].remark.value="";
	}
	if(document.forms[0].lchk[1].checked)
	{
		document.forms[0].percentage2.value="";
		document.forms[0].price2.value="";
		document.forms[0].top_percentage.value="";
		document.forms[0].obligation.value="";
		document.forms[0].remark2.value="";
		document.forms[0].promiseOn2.value="";
	}
	if(document.forms[0].lchk[2].checked)
	{
		document.forms[0].mug_percentage.value="";
		document.forms[0].rec_percentage.value="";
		document.forms[0].percentage3.value="";
		document.forms[0].price3.value="";
		document.forms[0].remark3.value="";
	}
}

</script>

</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.contract_master.DataBean_Contract_Master" id="masCont" scope="page"/>   
<jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_modulelock" id="modlock" scope="request"/>
<%
	 utilBean.init();
	 //String date = utilBean.getGen_dt();
     //String formId=request.getParameter("formId")==null?"":request.getParameter("formId");
	 String ml_username=(String)session.getAttribute("username");
	 String ml_formId=request.getParameter("formId");
	 String ml_sessionId=session.getId();
	 String ml_temp="other"; 
	 modlock.setSet_username(ml_username);
	 modlock.setFormId(ml_formId);
	 modlock.setSessionId(ml_sessionId);
	 modlock.setCallFrom(ml_temp);
	 //modlock.init();
	 boolean flg=modlock.isModLock();
	 String uname=modlock.getUname();
	 if(flg)
	 {
%>			<jsp:forward page="../home/fms_moduleerror.jsp" >
				 <jsp:param name="uname"  value="<%=uname%>" /> 
			</jsp:forward>
<%	}
	 //String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");	 
	 String bscode=request.getParameter("bscode")==null?"":request.getParameter("bscode");//this will pass code
	 String FGSA_No=request.getParameter("FGSA_No")==null?"":request.getParameter("FGSA_No");//this will pass code
	 String FGSA_REVNo=request.getParameter("Rev_No")==null?"":request.getParameter("Rev_No");//this will pass code
	 String LD=request.getParameter("ld")==null?"":request.getParameter("ld");//this will pass code
	 String TOP=request.getParameter("top")==null?"":request.getParameter("top");//this will pass code
	 String MAKEUP=request.getParameter("mug")==null?"":request.getParameter("mug");//this will pass code
	 
	 String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
		String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
		String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
		String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
		String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
	 
	 //Obtained from values passed on from previous page     
	 masCont.setCallFlag("FGSA_LIABILITY_CLAUSE");
     masCont.setFGSA_cd(FGSA_No);
     masCont.setBscode(bscode);
     masCont.setFGSA_REVNo(FGSA_REVNo);
	 masCont.init();
	 
	 String price_per1=masCont.getprice_per1()==null?"":masCont.getprice_per1();
	 String price1=masCont.getprice1()==null?"":masCont.getprice1();
	 String promise_qty1=masCont.getpromise_qty1()==null?"":masCont.getpromise_qty1();
	 String lower_per1=masCont.getlower_per1()==null?"":masCont.getlower_per1();
	 String dcq=masCont.getdcq1()==null?"":masCont.getdcq1();
	 String pndcq=masCont.getpndcq1()==null?"":masCont.getpndcq1();
	 String mdcq=masCont.getmdcq1()==null?"":masCont.getmdcq1();
	 String remarks1=masCont.getremarks1()==null?"":masCont.getremarks1();
	 String price_per2=masCont.getprice_per2()==null?"":masCont.getprice_per2();
	 String price2=masCont.getprice2()==null?"":masCont.getprice2();
	 String top_per=masCont.gettop_per()==null?"":masCont.gettop_per();
	 String actual_oblig=masCont.getactual_oblig()==null?"":masCont.getactual_oblig();
	 String promise_qty2=masCont.getpromise_qty2()==null?"":masCont.getpromise_qty2();
	 String remarks2=masCont.getremarks2()==null?"":masCont.getremarks2();
	 String makeup_period=masCont.getmakeup_period_per()==null?"":masCont.getmakeup_period_per();
	 String rec_period=masCont.getrec_period_per()==null?"":masCont.getrec_period_per();
	 String price_per3=masCont.getprice_per3()==null?"":masCont.getprice_per3();
	 String price3=masCont.getprice3()==null?"":masCont.getprice3();
	 String remarks3=masCont.getremarks3()==null?"":masCont.getremarks3();
	 String msg = request.getParameter("msg")== null?"":request.getParameter("msg");
	 String form_id = request.getParameter("form_id")==null?"0":request.getParameter("form_id");
	 String form_nm = request.getParameter("form_nm")==null?"":request.getParameter("form_nm");
	 System.out.println("form_id = "+form_id);
	 System.out.println("form_nm = "+form_nm);
     utilBean.init();                       
%>  

<body onload="setvalues('<%=price_per1%>','<%=price1%>','<%=promise_qty1 %>','<%=lower_per1 %>','<%=dcq %>','<%=pndcq %>','<%=mdcq %>',&quot;<%=remarks1%>&quot;,'<%=price_per2%>','<%=price2%>','<%=top_per%>','<%=actual_oblig%>','<%=promise_qty2%>',&quot;<%=remarks2%>&quot;,'<%=makeup_period %>','<%=rec_period %>','<%=price_per3 %>','<%=price3%>',&quot;<%=remarks3%>&quot;);setComponent('<%=LD%>','<%=TOP%>','<%=MAKEUP%>');">
<%	if(print_permission.trim().equalsIgnoreCase("N"))
	{	%>
<!-- 		<script language="javascript" src="../js/mouseclk.js"></script> -->
<%	}	%>
<form name=mainview method=post action="../servlet/Frm_Contract_MasterV2">
<div class="tab-content">
	<div class="tab-pane active" id="">
		<div class="box mb-0">
		<%if (msg.length() > 5) {%>
			<div class="box-body table-responsive no-padding">
				<table class="table  table-bordered">
					<thead>
						<tr class="info">
							<th class="text-center" colspan="7" style="color: blue;">
								<%=msg%>
							</th>
						</tr>
					</thead>
				</table>
			</div>
		<%}%>
		<div class="box-body table-responsive no-padding">
		<div class="row">
			<div class="table-responsive col-lg-12">
				<table class="table table-striped" >
					<thead>   
						<tr><th colspan="12" class="text-center"><font size="4" face="Verdana, Arial, Helvetica, sans-serif">Liability Details</font></th></tr>
						<tr>
							<th colspan="12" class="info"><input type="checkbox" name="lchk" value="L" onclick="enableLDDetails('L');">&nbsp;Liquidated Damages</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="1" class="info" width="30%"><strong>Price (% of Contract Price)</strong></td>
							<td colspan="5">
								<input type=text name="percentage1" maxlength=8 size=8 value="" onblur="checkNumber1(this,5,2);effective_price1(this);"> <b>%</b>
	     					</td>
	     					<td class="info">Effective Price ($/MMBTU)</td>
	     					<td>
	     						<input type="text" name="price1" maxlength="8" size="8" value="" onblur="checkNumber1(this,4,2);" readonly="readonly">
	     					</td>
						</tr>
						<tr>   
							<td align="left" class="info"><strong>Promise Quantity On</strong></td>
						 	<td align="left" colspan="11"> 
						 		<select name="promiseOn1" class ="select">
		                        	<option value="">-Select-</option>
		                         	<option value="Daily">Daily</option>
		                          	<option value="Weekly">Weekly</option>
		                          	<option value="Fortnightly">Fortnightly </option>
		                          	<option value="Monthly">Monthly </option>
		                          	<option value="Invoice Cycle">Invoice Cycle</option>
		                          	<option value="TCQ">TCQ</option>
		                          	<option value="Defined Period">Defined Period</option>
		                          	<option value="Supply Period">Supply Period</option>
			                   	</select>&nbsp;Frequency
					     	</td>
						</tr>
						<tr>   
							 <td align="left" class="info"><strong>Liability At </strong><span class="s-red">*</span></td>
							 <td colspan="11">Lower Of
							 	   <input type=text name=low_percentage maxlength=8 size=5 value='' onblur="checkNumber1(this,5,2);">&nbsp;%&nbsp;&nbsp;Of			     
							       <input type="checkbox" name="lchk1" value="DCQ"><option value="DCQ">DCQ&nbsp;&nbsp;&nbsp;
				                   <input type="checkbox" name="lchk1" value="PNDCQ"><option value="PNDCQ">PNDCQ&nbsp;&nbsp;&nbsp; 
				                   <input type="checkbox" name="lchk1" value="MDCQ"><option value="MDCQ">MDCQ 			    
						     </td>		
				         </tr>
				         <tr >   
							<td align="left" class="info"><strong>Remarks</strong></td>
						 	<td align="left" colspan="11"><input type=text name=remark maxlength=100 size=80></td>
			         	</tr>
			         	<tr>
							<th colspan="12" class="info"><input type="checkbox" name="lchk" value="T" onclick="enableTOPDetails('T');">&nbsp;Take Or Pay</th>
						</tr>	
						<tr >   
					 		<td align="left" class="info"><strong>Price (% of Contract Price)</strong></td>
					 		<td align="left" colspan="5" > <input type=text name=percentage2 maxlength=8 size=8 value="" onblur="checkNumber1(this,5,2);effective_price2(this);"> <b>%</b></td>
						    <td align="left" class="info"><strong>Effective  Price ($/MMBTU)</strong></td>
							<td align="left" > <input type=text name=price2 maxlength=8 size=8 value="" onblur="checkNumber1(this,4,2);"  readonly="readonly"></td>
						</tr>
						<tr >   
							<td align="left" class="info"><strong>Take or Pay </strong></td>
						 	<td align="left" colspan="11"> <input type=text name=top_percentage maxlength=8 size=5 value="" onblur="checkNumber1(this,5,2);calcObligation();">&nbsp;<b>%&nbsp;&nbsp;of DCQ/TCQ</b></td>
			         	</tr>          
			         	<tr >   
						 	<td align="left" class="info"><strong>Actual obligation to take </strong></td>
						 	<td align="left" colspan="11"><input type=text name=obligation maxlength=11 size=8 value="" onblur="checkNumber1(this,10,2);"  readOnly class="mkrdly">&nbsp;(MMBTU)</td>	
			         	</tr> 
			         	
			         	<tr>   
							<td align="left" class="info"><strong>Promise Quantity On</strong></td>
						 	<td align="left" colspan="11"> 
						 		<select name="promiseOn2" class ="select">
		                        	<option value="">-Select-</option>
		                         	<option value="Daily">Daily</option>
		                          	<option value="Weekly">Weekly</option>
		                          	<option value="Fortnightly">Fortnightly </option>
		                          	<option value="Monthly">Monthly </option>
		                          	<option value="Invoice Cycle">Invoice Cycle</option>
		                          	<option value="TCQ">TCQ</option>
		                          	<option value="Defined Period">Defined Period</option>
		                          	<option value="Supply Period">Supply Period</option>
			                   	</select>&nbsp;Frequency
					     	</td>
					     </tr>	
						 <tr>   
							 <td align="left" class="info"><strong>Remarks</strong></td>
							 <td align="left" colspan="11"> <input type=text name=remark2 maxlength=100 size=80></td>
				         </tr> 
				         <tr>
							<th colspan="12" class="info"><input type="checkbox" name="lchk" value="M" onclick="enableMUGDetails('M');">&nbsp;Make Up Gas</th>
						</tr>
						<tr >   
							 <td align="left" class="info"><strong>Make Up Period </strong></td>
							 <td align="left" colspan="11"><input type=text name=mug_percentage maxlength=8 size=5 value="" onblur="checkNumber1(this,4,2);">&nbsp;<b>%</b>of Supply Period</td>
				         </tr> 
				         <tr >   
							 <td align="left" class="info"><strong>Recovery Period</strong></td>
							 <td align="left" colspan="11"><input type=text name=rec_percentage maxlength=4 size=5 value="" onblur="checkNumber1(this,3,0);">&nbsp;Days</td>
				         </tr>
				         <tr >   
							 <td align="left" class="info"><strong>Price (% of Contract Price)</strong></td>
							 <td align="left" colspan="5"><input type=text name=percentage3 maxlength=8 size=8 value="" onblur="checkNumber1(this,4,2);effective_price3(this);"> <b>%</b></td>
							 <td align="left" class="info"><strong>Effective Price ($/MMBTU)</strong></td>
							 <td align="left" ><input type=text name=price3 maxlength=8 size=8 value="" onblur="checkNumber1(this,4,2);" class="mkrdly" readonly></td>
						 </tr> 
						 <tr >   
							 <td align="left" class="info"><strong>Remarks</strong></td>
							 <td align="left" colspan="11"><input type=text name=remark3 maxlength=100 size=80></td>
						</tr>
						 <tr > 
				         	<td colspan="12" class="text-right">
				            	<button type="reset" class="btn btn-info" name="reset" value="Reset" onClick="resetValues();"> Reset  </button>
				              <%	if(write_permission.trim().equalsIgnoreCase("Y"))
									{	%>	
				              			<button type="button"  class="btn btn-success" name="save" value="Submit" onclick="doSubmit();">Submit </button>
				              <%	}
				              		else
				              		{	%>
				              			<button type="button"  class="btn btn-success" name="save" value="Submit" onclick="#" disabled="disabled">Submit </button>
				              <%	}	%>
				              </td>
				         </tr> 
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</div>
</div>

<input type=hidden name="option"  value="FLSA_LIABILITY">
<input name="FGSA_NO" type="hidden" value=<%=FGSA_No%>>
<input name="FGSA_REVNo" type="hidden" value=<%=FGSA_REVNo%>>
<input name="bscode" type="hidden" value=<%=bscode%>>
<input name="DCQ_FLG" type="hidden" value="N">
<input name="PNDCQ_FLG" type="hidden" value="N">
<input name="MDCQ_FLG" type="hidden" value="N">
<input name="ld" type="hidden" value=<%=LD%>>
<input name="top" type="hidden" value=<%=TOP%>>
<input name="mug" type="hidden" value=<%=MAKEUP%>>
<input name="ld_flg" type="hidden" value="">
<input name="top_flg" type="hidden" value="">
<input name="mug_flg" type="hidden" value="">
<input type="hidden" name="form_id" value="<%=form_id%>">
<input type="hidden" name="form_nm" value="<%=form_nm%>">
<input type="hidden" name="write_permission" value="<%=write_permission%>">
<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
<input type="hidden" name="print_permission" value="<%=print_permission%>">
<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
</form>
</body>
</html>