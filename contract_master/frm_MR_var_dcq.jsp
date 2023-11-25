<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*" errorPage="" %>
<%@ include file="../sess/Expire.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>DCQ Specification For SN</title>
<link href="../css/guidefaultGeneral.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../css/utilities.css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="../js/date-picker.js"></script>
<script language="JavaScript" src="../js/aris.js"></script>
<script language="JavaScript" src="../js/util2.js"></script>
<script language="JavaScript" src="../js/util1.js"></script>
<script language="javascript" src="../js/util.js"></script>
<script language="JavaScript" src="../js/fms.js"></script>
<script language="javascript" src="../js/validations.js"></script>
<%--<script language="javascript" src="../js/mouseclk.js"></script>--%>
<style type="text/css">
<!--
#loading {

	width: 200px;

	height: 100px;

	background-color: #c0c0c0;

	position: absolute;

	left: 50%;

	top: 50%;

	margin-top: -50px;

	margin-left: -100px;

	text-align: center;

}
-->
</style>

<script type="text/javascript">

document.write('<div id="loading"><br><br>Loading, Please Wait...</div>');

window.onload=function()
{
	document.getElementById("loading").style.display="none";
   var flag1=document.forms(0).flag.value;
   var index=document.forms[0].index.value
  // alert(flag1)
   if(flag1=='F')
   {
       document.getElementById("dcqline").style.display="none";
       document.forms[0].index.value = '1';
   }
   else
   {
        document.getElementById("dcqline").style.display="table-row";
        document.forms[0].index.value = parseInt(index)+1;
   }
}

function refreshPage()
{
	var buyer_cd = document.forms[0].buyer_cd.value;
	var buyer_name = document.forms[0].buyer_name.value;
	var ContType = document.forms[0].cont_type.value;
	var fgsa_cd = document.forms[0].fgsa_cd.value;
	var fgsa_revno = document.forms[0].fgsa_revno.value;
	var sn_cd = document.forms[0].sn_cd.value;
	var sn_revno = document.forms[0].sn_revno.value;
	var start_dt = document.forms[0].start_dt.value;
	var end_dt = document.forms[0].end_dt.value;
	var price_type = document.forms[0].price_type.value;
	var index = document.forms[0].index.value;
	var flag1 = 'T';
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	//alert(index)
	if(index!=null)
	{	
		if(index!='' && index!=' ' && index!='  ')
		{
			if(parseInt(index)>=1)
			{
			    
				location.replace("frm_MR_var_dcq.jsp?buyer_cd="+buyer_cd+"&buyer_name="+buyer_name+"&cont_type="+ContType+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&price_type="+price_type+"&index="+index+"&flag1="+flag1+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);
			}
			else
			{
				document.forms[0].index.value = "1";
			}
		}
		else
		{
			document.forms[0].index.value = "1";
		}
	}
	else
	{
		document.forms[0].index.value = "1";
	}
}

function adddcq()
{


}

function resetPage()
{
	var buyer_cd = document.forms[0].buyer_cd.value;
	var fgsa_cd = document.forms[0].fgsa_cd.value;
	var fgsa_revno = document.forms[0].fgsa_revno.value;
	var sn_cd = document.forms[0].sn_cd.value;
	var sn_revno = document.forms[0].sn_revno.value;
	var start_dt = document.forms[0].start_dt.value;
	var end_dt = document.forms[0].end_dt.value;
	var index = "1";
	
	var write_permission = document.forms[0].write_permission.value;
	var delete_permission = document.forms[0].delete_permission.value;
	var print_permission = document.forms[0].print_permission.value;
	var approve_permission = document.forms[0].approve_permission.value;
	var audit_permission = document.forms[0].audit_permission.value;
	
	location.replace("frm_MR_var_dcq.jsp?buyer_cd="+buyer_cd+"&fgsa_cd="+fgsa_cd+"&fgsa_revno="+fgsa_revno+"&sn_cd="+sn_cd+"&sn_revno="+sn_revno+"&start_dt="+start_dt+"&end_dt="+end_dt+"&index="+index+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission);
}


function checkForDateValidity(obj,start_date,end_date)
{
	var start_dt = ""+start_date;
	var end_dt = ""+end_date;
	var dcq_dt = obj.value;
	//alert("dcq_dt = "+dcq_dt+",  start_dt = "+start_dt+",  And  end_dt = "+end_dt);
  	var value_1 = compareDate(dcq_dt,start_dt);
  	//alert("Value_1 = "+value_1);
  	if(value_1 == 2)
  	{
    	alert("Duration Date ("+dcq_dt+") Must Be Greater Than Or Equal To Start Date ("+start_dt+") !!!");
    	obj.value = "";
    	//obj.select();
    	return false;
  	}
  	
  	var value_2 = compareDate(dcq_dt,end_dt);
  	//alert("Value_2 = "+value_2);
  	if(value_2 == 1)
  	{
    	alert("Duration Date ("+dcq_dt+") Must Be Less Than Or Equal To End Date ("+end_dt+") !!!");
    	obj.value = "";
    	//obj.select();
    	return false;
  	}
}
//JHP START
function checkForDateValidityall(obj,index,st_dt1,end_dt1)
{
	//alert("hiii"+index);
	var st_dt1 = ""+st_dt1;
	var end_dt1 = ""+end_dt1;
	var start_dt = document.forms(0).from_dt;
	var end_dt = document.forms(0).to_dt;
	var dcq_dt = obj.value;
	var size=document.forms(0).size.value;
	//alert(size);
	
	var value_11 = compareDate(dcq_dt,st_dt1);
	var value_21 = compareDate(dcq_dt,end_dt1);
	//alert("value_11:"+value_11+"value_21:"+value_21);
	for(var i=0;i<size;i++)
	{
	//alert(start_dt[i].value+"-----"+end_dt[i].value+"-----"+dcq_dt);
	var start_dt1=start_dt[i].value;
	var value_1 = compareDate(dcq_dt,start_dt1);
	if(i!=index)
	{
	if(value_1 == 0)
  	{
  	alert("Duration Date ("+dcq_dt+") is already Present.");
  	obj.value = "";
  	return false;
  	}
  	}
	var	end_dt1=end_dt[i].value;
  	var value_2 = compareDate(dcq_dt,end_dt1);
  	
	if(i!=index)
	{
  	if(value_2 == 0)
  	{
  	alert("Duration Date ("+dcq_dt+") is already Present.");
  	obj.value = "";
  	return false;
  	}
  	}
  //	alert("value_1:"+value_1);
  //	alert("value_2:"+value_2);
  	if(value_1 == 1 &&  value_2 == 2 )
  	{
  	alert("Duration Date ("+dcq_dt+") is already Present in Period ("+start_dt1+") to ("+end_dt1+")");
  	obj.value = "";
  	return false;
  	
	}
	if(value_1 == 2 &&  value_2 == 1 )
  	{
  	alert(" Duration Date ("+dcq_dt+") is already Present in Period ("+start_dt1+") to ("+end_dt1+")");
  	obj.value = "";
  	return false;
  	
	}
	
	}
	
}
/*
function checkForDateValidityall1(obj,index,end_date)
{
	//alert("hiii"+index);
	var start_dt = document.forms(0).from_dt;
	var end_dt = document.forms(0).to_dt;
	var dcq_dt = obj.value;
	var size=document.forms(0).size.value;
	//alert(size);
	for(var i=0;i<size;i++)
	{
	//alert(start_dt[i].value+" "+end_dt[i].value+" "+dcq_dt);
	var start_dt1=start_dt[i].value;
	var value_1 = compareDate(dcq_dt,start_dt1);
	if(i!=index)
	{
	if(value_1 == 0)
  	{
  	alert("Date is already Present....");
  	obj.value = "";
  	return false;
  	}
  	}
	var	end_dt1=end_dt[i].value;
  	var value_2 = compareDate(dcq_dt,end_dt1);
  	
	if(i!=index)
	{
  	if(value_2 == 0)
  	{
  	alert("Date is already Present....");
  	obj.value = "";
  	return false;
  	}
  	}
  	if(value_1 == 1 &&  value_2 == 2 )
  	{
  	alert("Date Priod is already Present....");
  	obj.value = "";
  	return false;
  	
	}
	if(value_1 == 2 &&  value_2 == 1 )
  	{
  	alert("Date Priod is already Present....");
  	obj.value = "";
  	return false;
  	
	}
	}
	
}
*/
//JHP END
function doSubmit()
{
	var msg = "Please Check the Following Field(s):\n\n";
	var flag = true;
	
	var i = 0;
	var index = 0;
	//var size = parseInt(sz);//alert(size);
	
	/* if(size<=1)
	{ */
		var from_dt = document.forms[0].from_dt.value;
		var to_dt = document.forms[0].to_dt.value;
		var dcq = document.forms[0].dcq.value;
		var slope = document.forms[0].slope.value;
		var constant = document.forms[0].constant.value;
		var phy_curve = document.forms[0].phys_curve_nm.value;
		var price_type = document.forms[0].price_type.value;
		
		//SB document.forms[0].dcq.value=1;//alert(document.forms[0].dcq.value);
		
		if(from_dt==null || from_dt=='' || from_dt==' ' || from_dt=='0')
		{
			msg += "Please Enter The From Date Field Properly !!!\n";
			flag = false;
		}
		
		if(to_dt==null || to_dt=='' || to_dt==' ' || to_dt=='0')
		{
			msg += "Please Enter The To Date Field Properly !!!\n";
			flag = false;
		}
		if(slope==null || slope=='' || slope==' ')
		{
			msg += "Please Enter The Slope, Constant Field Properly !!!\n";
			flag = false;
		}
		if(slope==null || slope=='' || slope==' ')
		{
			msg += "Please Enter The Slope Field Properly !!!\n";
			flag = false;
		}
		if(constant==null || constant=='' || constant==' ')
		{
			msg += "Please Enter The Constant Field Properly !!!\n";
			flag = false;
		}
		
		if(phy_curve==null || phy_curve=='' || phy_curve== ' ' || phy_curve=='0') //HARSH20210729
		{
			msg += "Please Select Physical Curve !!!\n";
			flag = false;
		}
		
		if(price_type=='Float' || price_type=='Float(Complex)')
		{
			var opal_min = document.forms[0].opal_min.value;
			var formula = document.forms[0].formula.value;
			var formula1 = document.forms[0].formula1.value;
			var curve_nm = document.forms[0].curve_nm.value;
			
			if(curve_nm=="0" || curve_nm=="" || curve_nm==" ")
			{
				msg += "Please Select Financial Curve !!!\n";
				flag = false;
			}
			
			if(price_type=='Float(Complex)')
			{
				//if(opal_min == "OPAL")
				if(opal_min == "MULTI_LEG")
				{
					if(formula == "" || formula == " ")
					{
						msg += "Please Select Formula(Leg 1) !!!\n";
						flag = false;
					}
					if(formula1 == "" || formula1 == " ")
					{
						msg += "Please Select Formula(Leg 2) !!!\n";
						flag = false;
					}
				}
				else if(opal_min == "MIN")
				{
					var count="0";
					for (var option of document.getElementById('curve_nm').options)
				    {
				        if (option.selected) {
				            count+=1;
				        }	
				    }
					
					if(count <= 0)
					{
						msg += "Please Select atlest one Financial Curve !!!\n";
						flag = false;
					}
				}
			}
			else
			{
				var curve = document.getElementById('curve_nm').value;
				if(curve == "")
				{
					msg += "Please Select Financial Curve !!!\n";
					flag = false;
				}
			}
		}
	/*HARSH20201201 }
	else if(size>1)
	{
		for(i=0; i<(size-1); i++)
		{
			index = i+1;
			
			var from_dt = document.forms[0].from_dt[i].value;
			var to_dt = document.forms[0].to_dt[i].value;
			var dcq = document.forms[0].dcq[i].value;
			var slope = document.forms[0].slope[i].value;
			var constant = document.forms[0].constant[i].value;
			//SB document.forms[0].dcq[size-1].value=1;///alert(document.forms[0].dcq[size-1].value);
			
			if(from_dt==null || from_dt=='' || from_dt==' ' || from_dt=='0')
			{
				msg += "Please Enter The From Date Field Properly For Record NO: "+index+" !!!\n";
				flag = false;
			}
			
			if(to_dt==null || to_dt=='' || to_dt==' ' || to_dt=='0')
			{
				msg += "Please Enter The To Date Field Properly For Record NO: "+index+" !!!\n";
				flag = false;
			}
			
			if(slope==null || slope=='' || slope==' ')
			{
				msg += "Please Enter The Slope, Constant Field Properly For Record NO: "+index+" !!!\n";
				flag = false;
			}	
			if(slope==null || slope=='' || slope==' ')
			{
				msg += "Please Enter The Slope Field Properly !!!\n";
				flag = false;
			}
			if(constant==null || constant=='' || constant==' ')
			{
				msg += "Please Enter The Constant Field Properly !!!\n";
				flag = false;
			}
		}
	} */
	
	if(flag)
	{
		var a = confirm("Do You Want To Submit Price Specification Details For Selected Deal ???");
		
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

function openDealDtl(index, size,seq_no,from_dt,to_dt,dcq,price_type,curve_nm,slope,constant,remark,phys_curve_nm,PPAC_price,PPAC_Validity_dt,price_range,price_start_dt,price_end_dt,delete_permission,MIN_price_dt) //HARSH20210820 MIN_price_dt //HARSH20210426 //HARSH20210803 WRITE PERMISSION ADDED
{
	//alert(index); alert(size);
//	alert(document.forms[0].constant[index].value);
	/*HARSH20201201 document.forms[0].from_dt[size-1].value=document.forms[0].from_dt[index].value;
	document.forms[0].to_dt[size-1].value=document.forms[0].to_dt[index].value;
	document.forms[0].dcq[size-1].value=document.forms[0].dcq[index].value;
	document.forms[0].slope[size-1].value=document.forms[0].slope[index].value;
	document.forms[0].constant[size-1].value=document.forms[0].constant[index].value;
	document.forms[0].remark[size-1].value=document.forms[0].remark[index].value; */

	//HARSH20201201
	document.forms[0].from_dt.value=from_dt;
	document.forms[0].to_dt.value=to_dt;
	document.forms[0].dcq.value=dcq;
	document.forms[0].price_type.value=price_type;
	document.forms[0].seq_no.value=seq_no;
	document.forms[0].phys_curve_nm.value=phys_curve_nm; //SB20210310
	
	if(price_type=="Fixed")
	{
		document.getElementById('tr1').style.display="none";
		document.getElementById('tr2').style.display="none";
		document.getElementById('tr3').style.display="none";
		document.getElementById('tr4').style.display="none";
		document.forms[0].opal_min.value="";
		document.getElementById('op1').style.display="none";
		document.getElementById('op2').style.display="none";
		document.getElementById('op3').style.display="none";
		document.getElementById('curve_nm').multiple=false;
		document.getElementById('inf').style.display="none";
		
		var ele = document.getElementsByClassName("om");
		 for (var i = 0; i < ele.length; i++){
		        ele[i].style.display = "none";
		    }
		
		document.forms[0].remark.value=remark;
		document.forms[0].remark.readOnly=false;
	}
	else if(price_type=="Float")
	{		
		if(remark!="")
		{
			var split = remark.split("@");
			
			var opal_min=split[0];
			if(opal_min=="MIN")
			{
				var ele = document.getElementsByClassName("om");
				 for (var i = 0; i < ele.length; i++){
				        ele[i].style.display = "none";
				    }
				 
				document.getElementById('tr1').style.display="table-row";
				document.getElementById('tr2').style.display="table-row";
				document.getElementById('tr3').style.display="table-row";
				document.getElementById('tr4').style.display="table-row";
				
				document.forms[0].price_type.value="Float(Complex)"; //HARSH20210809
				document.forms[0].opal_min.value="MIN"
				
				document.forms[0].remark.readOnly=true;
				document.getElementById('o1').disabled=true;
				document.getElementById('o1').selected=false;
				document.getElementById('curve_nm').multiple=true;
				document.getElementById('inf').style.display="inline";
				document.getElementById('tr3').style.display="none";
				
				document.getElementById('op1').style.display="none";
				document.getElementById('op2').style.display="none";
				document.getElementById('op3').style.display="none";
				
				document.forms[0].remark.readOnly=true;
				document.forms[0].curve_nm.value="";
				if(curve_nm!="")
				{
					var cu_split = curve_nm.split("<br>");
					var slope_split = slope.split("<br>");
					var con_split = constant.split("<br>");
					var price_range_split = price_range.split("<br>");
					var price_date_split = MIN_price_dt.split("<br>");
					for(var i=0; i<cu_split.length; i++)
					{
						for (var j=0; j< document.forms[0].curve_nm.length; j++)
					    {						
					        if (document.forms[0].curve_nm.options[j].value == cu_split[i]) 
					        {
					        	document.forms[0].curve_nm.options[j].selected=true;
					        	
					        	var price_ind = "0";
					        	if(j == 0)
					        	{
					        		price_ind==0;
					        	}
					        	else
					        	{
					        		price_ind = parseInt(j)-1;	
					        	}
							    
							    var curve=cu_split[i];
							    var slop = slope_split[i];
							    var con = con_split[i];
							    var priceRange = price_range_split[i]; 
							    var priceDate = price_date_split[i];
							    
					        	document.getElementById(curve).style.display="table-row";
				        		document.getElementById(curve+"_slope").disabled=false;
				        		document.getElementById(curve+"_constant").disabled=false;
				        		document.getElementById(curve+"_slope").value=slop;
				        		document.getElementById(curve+"_constant").value=con;
				        		
				        		//HARSH20210820
				        		if(priceRange != undefined && priceRange !="" && priceRange !=" ")
				        		{
					        		if(priceRange.length>1)
					        		{
					        			var othAvg = priceRange.substring(1,priceRange.length)
					        			document.getElementById(curve+"_price_range").value="O";
					        			document.getElementById(curve+"_price_range").disabled=false;
						        		document.getElementById(curve+"_days").value=othAvg;
						        		document.getElementById(curve+"_days").disabled=false;
						        		document.getElementById(curve+"_price_start_dt").disabled=false;
						        		document.getElementById(curve+"_price_end_dt").disabled=false;
					        			document.getElementById('MIN_0_'+price_ind).style.display="inline";
					        			document.getElementById('MIN_1_'+price_ind).style.display="none";
					        			
					        		}
					        		else if(priceDate!="" && priceDate!="-")
					        		{
					        			var dtsplit = priceDate.split("-");
					        			document.getElementById(curve+"_price_range").value="D";
					        			document.getElementById(curve+'_price_start_dt').value=dtsplit[0];
					        			document.getElementById(curve+'_price_end_dt').value=dtsplit[1];
					        			document.getElementById('MIN_1_'+price_ind).style.display="inline";
					        			document.getElementById('MIN_0_'+price_ind).style.display="none";
					        			document.getElementById(curve+"_price_range").disabled=false;
						        		document.getElementById(curve+"_price_start_dt").disabled=false;
						        		document.getElementById(curve+"_price_end_dt").disabled=false;
						        		document.getElementById(curve+"_days").disabled=false;
					        			
					        		}
					        		else
					        		{
					        			document.getElementById(curve+"_price_range").value=priceRange;
					        			document.getElementById('MIN_0_'+price_ind).style.display="none";
					        			document.getElementById('MIN_1_'+price_ind).style.display="none";
					        			document.getElementById(curve+"_price_range").disabled=false;
						        		document.getElementById(curve+"_price_start_dt").disabled=false;
						        		document.getElementById(curve+"_price_end_dt").disabled=false;
						        		document.getElementById(curve+"_days").disabled=false;
					        			
					        		}
				        		}
				        		else
				        		{
				        			document.getElementById(curve+"_price_range").value="";
				        			document.getElementById('MIN_0_'+price_ind).style.display="none";
				        			document.getElementById('MIN_1_'+price_ind).style.display="none";
				        			document.getElementById(curve+"_price_range").disabled=false;
					        		document.getElementById(curve+"_price_start_dt").disabled=false;
					        		document.getElementById(curve+"_price_end_dt").disabled=false;
					        		document.getElementById(curve+"_days").disabled=false;
				        			
				        		}
				        		///////////////
					        }
					    } 
					}
				}
				
				document.forms[0].remark.value=remark;
			}
			else if(opal_min=="MULTI_LEG")
			{
				document.getElementById('tr1').style.display="table-row";
				document.getElementById('tr2').style.display="table-row";
				document.getElementById('tr3').style.display="table-row";
				document.getElementById('tr4').style.display="table-row";
				
				document.forms[0].price_type.value="Float(Complex)"; //HARSH20210809
				document.forms[0].opal_min.value="MULTI_LEG"
				
				var ele = document.getElementsByClassName("om");
				 for (var i = 0; i < ele.length; i++){
				        ele[i].style.display = "none";
				    }
				 
				document.forms[0].remark.readOnly=true;
				document.getElementById('o1').disabled=false;
				document.getElementById('curve_nm').multiple=false;
				document.getElementById('inf').style.display="none";
				document.getElementById('op1').style.display="inline";
				document.getElementById('op2').style.display="inline";
				document.getElementById('op3').style.display="inline";
				
				if(remark!="")
				{
					var split = remark.split("@");
					if(split.length==3)
					{
						document.forms[0].formula.value=split[1];
						document.forms[0].formula1.value=split[2];
					}
					else if(split.length==2)
					{
						document.forms[0].formula.value=split[1];
					}
				}
				
				document.forms[0].remark.value=remark;
				document.forms[0].curve_nm.value=curve_nm;
				document.forms[0].slope.value=slope;
				document.forms[0].constant.value=constant;
				
				if(price_range.length>1)
				{
					var othAvg = price_range.substring(1,price_range.length)
					document.forms[0].price_range.value="O";
					document.forms[0].days.style.display="inline";
					document.getElementById('days1').style.display="inline";
					document.forms[0].days.disabled=false;
					document.forms[0].days.value=othAvg;
					
					document.forms[0].price_start_dt.style.display="none";
					document.forms[0].price_start_dt.disabled=true;
					document.forms[0].price_end_dt.style.display="none";
					document.forms[0].price_end_dt.disabled=true;
					//document.getElementById('avg1').style.display="none";
					document.getElementById('avg2').style.display="none";
					document.getElementById('avg3').style.display="none";
					document.getElementById('avg4').style.display="none";
					document.getElementById('avg5').style.display="none";
					
				}
				else if(price_start_dt != "" && price_end_dt != "")
				{
					document.forms[0].price_range.value="D";
					
					document.forms[0].price_start_dt.style.display="inline";
					document.forms[0].price_start_dt.disabled=false;
					document.forms[0].price_end_dt.style.display="inline";
					document.forms[0].price_end_dt.disabled=false;
					
					//document.getElementById('avg1').style.display="inline";
					document.getElementById('avg2').style.display="inline";
					document.getElementById('avg3').style.display="inline";
					document.getElementById('avg4').style.display="inline";
					document.getElementById('avg5').style.display="inline";
					
					document.forms[0].days.style.display="none";
					document.getElementById('days1').style.display="none";
					document.forms[0].days.disabled=true;
					
					document.forms[0].price_start_dt.value=price_start_dt; //SB20210511
					document.forms[0].price_end_dt.value=price_end_dt; //SB20210511
				}
				else
				{
					document.forms[0].price_range.value=price_range; //SB20210507
					document.forms[0].days.style.display="none";
					document.getElementById('days1').style.display="none";
					document.forms[0].days.disabled=true;
					
					document.forms[0].price_start_dt.style.display="none";
					document.forms[0].price_start_dt.disabled=true;
					document.forms[0].price_end_dt.style.display="none";
					document.forms[0].price_end_dt.disabled=true;
					//document.getElementById('avg1').style.display="none";
					document.getElementById('avg2').style.display="none";
					document.getElementById('avg3').style.display="none";
					document.getElementById('avg4').style.display="none";
					document.getElementById('avg5').style.display="none";
				}
			}
			else
			{
				document.getElementById('tr1').style.display="none";
				document.getElementById('tr2').style.display="table-row";
				document.getElementById('tr3').style.display="table-row";
				document.getElementById('tr4').style.display="table-row";
				document.forms[0].remark.readOnly=false;
				document.getElementById('curve_nm').multiple=false;
				document.getElementById('inf').style.display="none";
				
				var ele = document.getElementsByClassName("om");
				 for (var i = 0; i < ele.length; i++){
				        ele[i].style.display = "none";
				    }
				
				document.forms[0].remark.value=remark;
				document.forms[0].curve_nm.value=curve_nm;
				document.forms[0].slope.value=slope;
				document.forms[0].constant.value=constant;
				
				document.forms[0].price_range.value=price_range; //SB20210507
				document.forms[0].days.style.display="none";
				document.getElementById('days1').style.display="none";
				document.forms[0].days.disabled=true;
				
				document.forms[0].price_start_dt.style.display="none";
				document.forms[0].price_start_dt.disabled=true;
				document.forms[0].price_end_dt.style.display="none";
				document.forms[0].price_end_dt.disabled=true;
				//document.getElementById('avg1').style.display="none";
				document.getElementById('avg2').style.display="none";
				document.getElementById('avg3').style.display="none";
				document.getElementById('avg4').style.display="none";
				document.getElementById('avg5').style.display="none";
			}
		}
		else
		{
			document.getElementById('tr1').style.display="none";
			document.getElementById('tr2').style.display="table-row";
			document.getElementById('tr3').style.display="table-row";
			document.getElementById('tr4').style.display="table-row";
			document.forms[0].remark.readOnly=false;
			document.getElementById('curve_nm').multiple=false;
			document.getElementById('inf').style.display="none";
			
			var ele = document.getElementsByClassName("om");
			 for (var i = 0; i < ele.length; i++){
			        ele[i].style.display = "none";
			    }
			
			document.forms[0].remark.value=remark;
			document.forms[0].curve_nm.value=curve_nm;
			document.forms[0].slope.value=slope;
			document.forms[0].constant.value=constant;
			
			if(price_range.length>1)
			{
				var othAvg = price_range.substring(1,price_range.length)
				document.forms[0].price_range.value="O";
				document.forms[0].days.style.display="inline";
				document.getElementById('days1').style.display="inline";
				document.forms[0].days.disabled=false;
				document.forms[0].days.value=othAvg;
				
				document.forms[0].price_start_dt.style.display="none";
				document.forms[0].price_start_dt.disabled=true;
				document.forms[0].price_end_dt.style.display="none";
				document.forms[0].price_end_dt.disabled=true;
				//document.getElementById('avg1').style.display="none";
				document.getElementById('avg2').style.display="none";
				document.getElementById('avg3').style.display="none";
				document.getElementById('avg4').style.display="none";
				document.getElementById('avg5').style.display="none";
				
			}
			else if(price_start_dt != "" && price_end_dt != "")
			{
				document.forms[0].price_range.value="D";
				
				document.forms[0].price_start_dt.style.display="inline";
				document.forms[0].price_start_dt.disabled=false;
				document.forms[0].price_end_dt.style.display="inline";
				document.forms[0].price_end_dt.disabled=false;
				
				//document.getElementById('avg1').style.display="inline";
				document.getElementById('avg2').style.display="inline";
				document.getElementById('avg3').style.display="inline";
				document.getElementById('avg4').style.display="inline";
				document.getElementById('avg5').style.display="inline";
				
				document.forms[0].days.style.display="none";
				document.getElementById('days1').style.display="none";
				document.forms[0].days.disabled=true;
				
				document.forms[0].price_start_dt.value=price_start_dt; //SB20210511
				document.forms[0].price_end_dt.value=price_end_dt; //SB20210511
			}
			else
			{
				document.forms[0].price_range.value=price_range; //SB20210507
				document.forms[0].days.style.display="none";
				document.getElementById('days1').style.display="none";
				document.forms[0].days.disabled=true;
				
				document.forms[0].price_start_dt.style.display="none";
				document.forms[0].price_start_dt.disabled=true;
				document.forms[0].price_end_dt.style.display="none";
				document.forms[0].price_end_dt.disabled=true;
				//document.getElementById('avg1').style.display="none";
				document.getElementById('avg2').style.display="none";
				document.getElementById('avg3').style.display="none";
				document.getElementById('avg4').style.display="none";
				document.getElementById('avg5').style.display="none";
			}
		}
		
		document.forms[0].ppac_price.value=PPAC_price; //HARSH20210426
		document.forms[0].ppac_validity_dt.value=PPAC_Validity_dt; //HARSH20210426
		//HARSH20210729 document.forms[0].price_range.value=price_range; //SB20210507
	}
	
	//HARSH20210803
	if(delete_permission == 'Y')
	{
		if(document.forms[0].delete.value!=null)
		{
			if(document.forms[0].delete.length != undefined)
			{
				for(var i=0;i<document.forms[0].delete.length; i++)
				{
					if(i == index)
					{
						document.forms[0].delete[i].disabled=false;
					}
					else
					{	
						document.forms[0].delete[i].disabled=true;
					}
				}
			}
			else
			{
				document.forms[0].delete.disabled=false;
			}
		}
	}
	document.forms[0].optional.value="MODIFY";
	document.forms[0].save.value="Modify";
}

function deleteExistingDCQ(seq_no)
{
	//alert(seq_no);
	document.forms[0].seq_no.value=seq_no;
	document.forms[0].optional.value="DELETE";
	var a = confirm("Do You Want To Delete???");
	
	if(a)
	{
		document.forms[0].submit();
	}
	else
	{
		document.forms[0].seq_no.value="";
		document.forms[0].optional.value="INSERT";
	}
}

//HARSH20210728 NEW FUNCTION
function setFormula()
{
	var opal_min = document.forms[0].opal_min.value;
	var formula = document.forms[0].formula.value;
	var formula1 = document.forms[0].formula1.value;
	
	if(opal_min=="MULTI_LEG")
	{
		document.getElementById('op1').style.display="inline";
		document.getElementById('op2').style.display="inline";
		document.getElementById('op3').style.display="inline";
		//document.getElementById('op4').style.display="inline";
	}
	else
	{
		document.getElementById('op1').style.display="none";
		document.getElementById('op2').style.display="none";
		document.getElementById('op3').style.display="none";
		//document.getElementById('op4').style.display="none";
	}
	
	if(opal_min=='MULTI_LEG')
	{	
		//document.getElementById('o1').style.display="inline";
		document.getElementById('o1').disabled=false;
		document.getElementById('curve_nm').multiple=false;
		//document.getElementById('o1').selected=true;
		document.getElementById('inf').style.display="none";
		document.getElementById('tr3').style.display="table-row";
		
		document.forms[0].remark.readOnly=true;
		
		var ele = document.getElementsByClassName("om");
		 for (var i = 0; i < ele.length; i++){
		        ele[i].style.display = "none";
		    }
		
		var selected = "";
		if(formula!="")
		{
			selected=opal_min+"@"+formula;
			if(formula1!="")
			{
				selected+="@"+formula1;
			}
		}
		
		document.forms[0].remark.value=selected;
	}
	else if(opal_min=='MIN')
	{
		//document.getElementById('o1').style.display="none";
		document.getElementById('o1').disabled=true;
		document.getElementById('o1').selected=false;
		document.getElementById('curve_nm').multiple=true;
		document.getElementById('inf').style.display="inline";
		document.getElementById('op3').style.display="none";
		document.getElementById('tr3').style.display="none";
		document.forms[0].remark.readOnly=true;
		
		var selected = "";
		var sel=[];
		for (var option of document.getElementById('curve_nm').options)
	    {
	        if (option.selected) {
	        	var id= option.value;
	        	if(id!="0")
	        	{
	        		document.getElementById(option.value).style.display="table-row";
	        		document.getElementById(option.value+"_slope").disabled=false;
	        		document.getElementById(option.value+"_constant").disabled=false;
	        		document.getElementById(option.value+"_price_range").disabled=false;
	        		document.getElementById(option.value+"_days").disabled=false;
	    			document.getElementById(option.value+"_price_start_dt").disabled=false;
	        		document.getElementById(option.value+"_price_end_dt").disabled=false;
	        	}
	        	if(selected=="")
	        	{
	        		if(id !="0")
	        		{
	        			selected=opal_min+"@"+option.value;
	        		}
	        	}
	        	else
	        	{
	        		selected+="@"+option.value;
	        	}
	            sel.push(option.value);
	        }
	        else
			{
	        	var id= option.value;
	        	if(id!="0")
	        	{
	        		document.getElementById(id).style.display="none";
	        		document.getElementById(option.value+"_slope").disabled=true;
	        		document.getElementById(option.value+"_constant").disabled=true;
	        		document.getElementById(option.value+"_price_range").disabled=true;
	        		document.getElementById(option.value+"_days").disabled=true;
	    			document.getElementById(option.value+"_price_start_dt").disabled=true;
	        		document.getElementById(option.value+"_price_end_dt").disabled=true;
	        	}
			}
	    }
		
		document.forms[0].remark.value=selected;
	}
	else
	{
		document.getElementById('o1').disabled=false;
		document.getElementById('curve_nm').multiple=false;
		document.getElementById('inf').style.display="none";
		document.getElementById('tr3').style.display="table-row";
		
		document.forms[0].remark.readOnly=false;
		document.forms[0].remark.value="";
		
		var ele = document.getElementsByClassName("om");
		 for (var i = 0; i < ele.length; i++){
		        ele[i].style.display = "none";
		    }	
	}
}
//HARSH20210728 NEW FUNCTION
function selectMultiple()
{
	document.getElementById('curve_nm').onclick = function() {
	    var selected = [];
	    for (var option of document.getElementById('curve_nm').options)
	    {
	        if (option.selected) {
	            selected.push(option.value);
	        }
	    }
	    //alert(selected);
	}
}

//HARSH20210728 NEW FUNCTION
function setDiaplay()
{
	var price_type = document.forms[0].price_type.value;
	
	if(price_type == "Fixed")
	{
		var ele = document.getElementsByClassName("om");
		 for (var i = 0; i < ele.length; i++){
		        ele[i].style.display = "none";
		    }
		 
		document.getElementById('tr1').style.display="none";
		document.getElementById('tr2').style.display="none";
		document.getElementById('tr3').style.display="none";
		document.getElementById('tr4').style.display="none";
		
		document.forms[0].remark.value="";
		document.forms[0].opal_min.value="";
		document.forms[0].formula.value="";
		document.forms[0].formula1.value="";
	}
	else if(price_type == "Float(Complex)")
	{
		document.getElementById('tr1').style.display="table-row";
		document.getElementById('tr2').style.display="table-row";
		document.getElementById('tr3').style.display="table-row";
		document.getElementById('tr4').style.display="table-row";
		document.getElementById('curve_nm').multiple=false;
		document.getElementById('inf').style.display="nonr";
	}
	else if(price_type == "Float")
	{
		var ele = document.getElementsByClassName("om");
		 for (var i = 0; i < ele.length; i++){
		        ele[i].style.display = "none";
		    }
		document.getElementById('tr1').style.display="none";
		document.getElementById('tr2').style.display="table-row";
		document.getElementById('tr3').style.display="table-row";
		document.getElementById('tr4').style.display="table-row";
		document.getElementById('curve_nm').multiple=false;
		document.getElementById('inf').style.display="none";
		document.forms[0].remark.value="";
		document.forms[0].opal_min.value="";
		document.forms[0].formula.value="";
		document.forms[0].formula1.value="";
		
	}
	else
	{
		document.getElementById('tr1').style.display="none";
		document.getElementById('tr2').style.display="none";
		document.getElementById('tr3').style.display="none";
		document.getElementById('tr4').style.display="none";
	}
}

//HARSH20210729 NEW FUNCATION 
function openEditBox(index,curve)
{
	if(index == '' && curve == '')
	{
		var price_range = document.forms[0].price_range.value;
		
		if(price_range == "O")
		{
			document.forms[0].days.style.display="inline";
			document.getElementById('days1').style.display="inline";
			document.forms[0].days.disabled=false;
			
			document.forms[0].price_start_dt.style.display="none";
			document.forms[0].price_start_dt.disabled=true;
			document.forms[0].price_end_dt.style.display="none";
			document.forms[0].price_end_dt.disabled=true;
			//document.getElementById('avg1').style.display="none";
			document.getElementById('avg2').style.display="none";
			document.getElementById('avg3').style.display="none";
			document.getElementById('avg4').style.display="none";
			document.getElementById('avg5').style.display="none";
		}
		else if(price_range == "D")
		{
			document.forms[0].price_start_dt.style.display="inline";
			document.forms[0].price_start_dt.disabled=false;
			document.forms[0].price_end_dt.style.display="inline";
			document.forms[0].price_end_dt.disabled=false;
			
			//document.getElementById('avg1').style.display="inline";
			document.getElementById('avg2').style.display="inline";
			document.getElementById('avg3').style.display="inline";
			document.getElementById('avg4').style.display="inline";
			document.getElementById('avg5').style.display="inline";
			
			document.forms[0].days.style.display="none";
			document.getElementById('days1').style.display="none";
			document.forms[0].days.disabled=true;
		}
		else
		{
			document.forms[0].days.style.display="none";
			document.getElementById('days1').style.display="none";
			document.forms[0].days.disabled=true;
			
			document.forms[0].price_start_dt.style.display="none";
			document.forms[0].price_start_dt.disabled=true;
			document.forms[0].price_end_dt.style.display="none";
			document.forms[0].price_end_dt.disabled=true;
			//document.getElementById('avg1').style.display="none";
			document.getElementById('avg2').style.display="none";
			document.getElementById('avg3').style.display="none";
			document.getElementById('avg4').style.display="none";
			document.getElementById('avg5').style.display="none";
		}
	}
	else if(index != '' && curve != '')
	{
		var price_range = document.getElementById(curve+'_price_range').value;
		if(price_range == "O")
		{
			document.getElementById('MIN_0_'+index).style.display="inline";
			document.getElementById('MIN_1_'+index).style.display="none";
			document.getElementById(curve+"_price_start_dt").value="";
			document.getElementById(curve+"_price_end_dt").value="";
			document.getElementById(curve+"_days").disabled=false;
			document.getElementById(curve+"_price_range").disabled=false;
			document.getElementById(curve+"_price_start_dt").disabled=false;
    		document.getElementById(curve+"_price_end_dt").disabled=false;
    		
		}
		else if(price_range == "D")
		{
			document.getElementById('MIN_1_'+index).style.display="inline";
			document.getElementById('MIN_0_'+index).style.display="none";
			document.getElementById(curve+"_price_range").disabled=false;
    		document.getElementById(curve+"_price_start_dt").disabled=false;
    		document.getElementById(curve+"_price_end_dt").disabled=false;
    		document.getElementById(curve+"_days").disabled=false;
		}
		else
		{
			document.getElementById('MIN_1_'+index).style.display="none";
			document.getElementById('MIN_0_'+index).style.display="none";
			document.getElementById(curve+"_price_start_dt").value="";
			document.getElementById(curve+"_price_end_dt").value="";
			document.getElementById(curve+"_days").disabled=false;
			document.getElementById(curve+"_price_range").disabled=false;
			document.getElementById(curve+"_price_start_dt").disabled=false;
    		document.getElementById(curve+"_price_end_dt").disabled=false;
		}
	}
}

//HARSH20210803 NEW FUNCTION
function checkFromDate()
{
	var form_dt = document.forms[0].from_dt.value;
	var to_dt = document.forms[0].to_dt.value;
	var val = compareDate(to_dt,form_dt);
	
	if(form_dt != "" && to_dt != "")
	{
		
		if(val=="1" || val=="0")
	  	{
	  	}
		else
		{
			var to_dt = document.forms[0].from_dt.value="";
			alert("From date must be Less than or equal to To date..");
			return false;
		}
	}
}
//HARSH20210803 NEW FUNCTION
function checkToDate()
{
	var form_dt = document.forms[0].from_dt.value;
	var to_dt = document.forms[0].to_dt.value;
	var val = compareDate(to_dt,form_dt);
	
	if(form_dt != "" && to_dt != "")
	{
		
		if(val=="1" || val=="0")
	  	{
	  	}
		else
		{
			document.forms[0].to_dt.value="";
			alert("To date must be Greater or equal to Form date..");
			return false;
		}
	}
}

//HARSH20210803 NEW FUNCTION
function closeWindow()
{
	window.close();
}

//HARSH20210803 NEW FUNCTION
function checkZero(obj)
{
	var days = obj.value;
	var number = /^[0-9]+$/;
	if(days.match(number))
	{
		if(days > 0)
		{
		}
		else
		{
			alert("Please Enter the number of days greater or equal to 1");
			obj.value="";
			return false;
		}
	}
	else
	{
		alert("Please Enter Only Numeric Value !!");
		obj.value="";
		return false;
	}
}
</script>

</head>

<jsp:useBean class="com.seipl.hazira.dlng.contract_master.DataBean_MRCR_Contract_MasterReportV2" id="mst" scope="page"/>
<%
	String comp_cd=session.getAttribute("comp_cd")==null?"56":""+session.getAttribute("comp_cd");
	String emp_cd=session.getAttribute("emp_cd")==null?"":""+session.getAttribute("emp_cd");
	String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
	String cont_type = request.getParameter("cont_type")==null?"":request.getParameter("cont_type");
	String buyer_cd = request.getParameter("buyer_cd")==null?"0":request.getParameter("buyer_cd");
	String buyer_name = request.getParameter("buyer_name")==null?"0":request.getParameter("buyer_name");
	String fgsa_cd = request.getParameter("fgsa_cd")==null?"0":request.getParameter("fgsa_cd");
	String fgsa_revno = request.getParameter("fgsa_revno")==null?"0":request.getParameter("fgsa_revno");
	String sn_cd = request.getParameter("sn_cd")==null?"0":request.getParameter("sn_cd");
	String sn_revno = request.getParameter("sn_revno")==null?"0":request.getParameter("sn_revno");
	String start_dt = request.getParameter("start_dt")==null?"":request.getParameter("start_dt");
	String end_dt = request.getParameter("end_dt")==null?"":request.getParameter("end_dt");
	String price_type = request.getParameter("price_type")==null?"":request.getParameter("price_type");
	String index = request.getParameter("index")==null?"1":request.getParameter("index");
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	String flag1= request.getParameter("flag1")==null?"F":request.getParameter("flag1");
	int ind = Integer.parseInt(index);
	
	String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
	String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
	String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
	
	mst.setCallFlag("SN_DCQ_LIST");
	mst.setBuyer_cd(buyer_cd);
	mst.setFgsaNo(fgsa_cd);
	mst.setFgsaRevNo(fgsa_revno);
	mst.setSnNo(sn_cd);
	mst.setSnRevNo(sn_revno);
	mst.setContType(cont_type);//HARSH20201201
	mst.init();
	
	//Following 4 Vector Getter Methods Has Been Introduced By Samik Shah On 13th July, 2010 ...
	Vector sn_Dcq_From_Dt = mst.getSn_Dcq_From_Dt();
	Vector sn_Dcq_To_Dt = mst.getSn_Dcq_To_Dt();
	Vector sn_Dcq_Value = mst.getSn_Dcq_Value();
	Vector sn_Dcq_Remark = mst.getSn_Dcq_Remark();
	Vector VSlope = mst.getVSlope();
	Vector VConst = mst.getVConst();
	Vector VPrice_Type = mst.getVPrice_Type();
	Vector VCurve_Nm = mst.getVCurve_Nm();
	Vector VRate = mst.getVRate();
	Vector Rate_Unit = mst.getVRate_Unit();
	
	Vector sn_DCQ_seq_no = mst.getVsn_DCQ_seq_no(); //HARSH20201201
	Vector VPhys_Curve_Nm = mst.getVPhys_Curve_Nm(); //SB20210310
	int ind2 = sn_Dcq_From_Dt.size();
	
	Vector VPhysCurveNm = mst.getVPhysCurveNm();
	Vector VFinCurveNm = mst.getVFinCurveNm();
	
	Vector VPPAC_Price = mst.getVPPAC_Price();//HARSH20210421
	Vector VPPAC_Validity_Dt = mst.getVPPAC_Validity_Dt();//HARSH20210421
	Vector VPrice_Start_Dt = mst.getVPrice_Start_Dt();//HARSH20210421
	Vector VPrice_End_Dt = mst.getVPrice_End_Dt();//HARSH20210421
	Vector VPrice_Range = mst.getVPrice_Range();//SB20210507
	
	Vector VMIN_Price_St_End_dt = mst.getVMIN_Price_St_End_dt(); //HARSH20210820
	
%>
<body>

<%	if(print_permission.trim().equalsIgnoreCase("N"))
	{	%>
		<script language="javascript" src="../js/mouseclk.js"></script>
<%	}	%>

<div align="center" id="col-three">
<form name="product_list" method="post" action="../servlet/Frm_MRCR_Contract_Master">
<%	if(msg.length()>10)
    {	%>
    	<table align="center" width="90%">
    		<tr>
				<td class="message">
					<div align="left"><font size="3"><b><%=msg%></b></font></div>
				</td>
			</tr>
		</table>
<%	}	%>
<div id="col-new">
<fieldset  style="width:90%" align="center">
    <table width="100%" border="0">
    <tr>
    <%if(!fgsa_cd.equals("") && !start_dt.equals("") && !end_dt.equals("")) { %>
		<td colspan="12" class="highlighttext" style="background-color: #d9edf7; font-weight: bold; color: black;">
		<%} else { %>
		<td colspan="12" class="highlighttext" style="background-color: #d9edf7;">
		<%} %>
			<div align="center"><font size="3"><b>[<%=buyer_cd%><%=buyer_name%>] Deal NO#: <%if(cont_type.equals("E")){%>S<%}else{%>L<%}%><%=fgsa_cd%>-<%=fgsa_revno%>-<%=sn_cd%>-<%=sn_revno%> <BR>Price Specification From: <%=start_dt%> To: <%=end_dt%></b></font></div>		
		</td>
	</tr>
	<tr style="background-color: #d9edf7;">
		<td colspan="12" class="title2" style="background-color: #d9edf7;">
			<div align="left"><b>Existing Specification</b></div>
		</td>
	</tr>
	<tr class="title2" style="background-color: #d9edf7;">
		<td width=""><div align="center"><font color="red">*</font>From&nbsp;Date</div></td>
		<td width=""><div align="center"><font color="red">*</font>To&nbsp;Date</div></td>
		<!--HARSH20210427 <td width="15%"><div align="center"><font color="red"></font>&nbsp;</div></td> -->
		<td width=""><div align="center"><font color="red">*</font>Price Type&nbsp;</div></td>
		<td width=""><div align="center"><font color="red">*</font>Physical Curve&nbsp;</div></td>
		<td width=""><div align="center"><font color="green">*</font>Financial Curve&nbsp;</div></td>
		<td width=""><div align="center"><font color="red">*</font>Slope&nbsp;</div></td>
		<td width=""><div align="center"><font color="red">*</font>Constant&nbsp;</div></td>
		<td width=""><div align="center">PPAC Price&nbsp;</div></td>
		<td width=""><div align="center">Settle Price/Mth&nbsp;</div></td>
		<td width=""><div align="center">Settle Date&nbsp;</div></td>
		<td width="30%"><div align="center">Remark | Price Formula</div></td>
		<td></td>
	</tr>
	<%-- <input type=hidden name="size" value="<%=ind2 %>" > --%>
	<%String CurveName=""; %>
<%	if(sn_Dcq_From_Dt.size()>0){
	
	for(int i=0; i<ind2; i++)
	{	%>
		<tr class="content1" style="background-color: white;">
			<td>
				<div align="center">
				<input type='radio' name='chk' value='N' onclick="openDealDtl('<%=i%>','<%=(ind+ind2)%>','<%=sn_DCQ_seq_no.elementAt(i)%>','<%=sn_Dcq_From_Dt.elementAt(i)%>','<%=sn_Dcq_To_Dt.elementAt(i)%>','<%=sn_Dcq_Value.elementAt(i)%>','<%=VPrice_Type.elementAt(i)%>','<%=VCurve_Nm.elementAt(i)%>','<%=VSlope.elementAt(i)%>','<%=VConst.elementAt(i)%>','<%=sn_Dcq_Remark.elementAt(i)%>','<%=VPhys_Curve_Nm.elementAt(i)%>','<%=VPPAC_Price.elementAt(i)%>','<%=VPPAC_Validity_Dt.elementAt(i)%>','<%=VPrice_Range.elementAt(i)%>','<%=VPrice_Start_Dt.elementAt(i)%>','<%=VPrice_End_Dt.elementAt(i)%>','<%=delete_permission%>','<%=VMIN_Price_St_End_dt.elementAt(i)%>');">
					<%-- <input type="text" name="from_dt" value="<%=(String)sn_Dcq_From_Dt.elementAt(i)%>" size="10" maxlength="11" onBlur="validateDate(this);checkForDateValidity(this,'<%=start_dt%>','<%=end_dt%>');checkForDateValidityall(this,'<%=i%>','<%=start_dt%>','<%=end_dt%>');">
					
				<%	if((ind+ind2)>1)
					{	%>
						<a href="javascript:show_calendar('forms[0].from_dt[<%=i%>]');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
							<img src="../images/calendar.gif" width="25" height="22" border="0">
						</a>
				<%	}
					else
					{	%>
						<a href="javascript:show_calendar('forms[0].from_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
							<img src="../images/calendar.gif" width="25" height="22" border="0">
						</a>
				<%	}	%> --%>
				<%=sn_Dcq_From_Dt.elementAt(i) %>
				</div>
			</td>
			<td>
				<div align="center">
				<%-- 	<input type="text" name="to_dt" value="<%=(String)sn_Dcq_To_Dt.elementAt(i)%>" size="10" maxlength="11" onBlur="validateDate(this);checkForDateValidity(this,'<%=start_dt%>','<%=end_dt%>');checkForDateValidityall(this,'<%=i%>','<%=start_dt%>','<%=end_dt%>');">
				<%	if((ind+ind2)>1)
					{	%>
						<a href="javascript:show_calendar('forms[0].to_dt[<%=i%>]');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
							<img src="../images/calendar.gif" width="25" height="22" border="0">
						</a>
				<%	}
					else
					{	%>
						<a href="javascript:show_calendar('forms[0].to_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
							<img src="../images/calendar.gif" width="25" height="22" border="0">
						</a>
				<%	}	%> --%>
				<%=sn_Dcq_To_Dt.elementAt(i)%>
				</div>
			</td>
			<%--HARSH20210427 <td>
				<div align="center">
					<input type="hidden" name="dcq" value="<%=(String)sn_Dcq_Value.elementAt(i)%>" size="10" maxlength="10" onBlur="checkNumber1(this,9,2);" readonly>
					<%//=sn_Dcq_Value.elementAt(i)%>
				</div>
			</td> --%>
			<td>
				<div align="center">
					<%-- <input type="text" name="price_type" value="<%=(String)VPrice_Type.elementAt(i)%>" size="5" maxlength="10" onBlur="" > --%>
					<%=VPrice_Type.elementAt(i)%>
				</div>
			</td>
			<td>
				<div align="center">
					<%-- <input type="text" name="curve_nm" value="<%=(String)VCurve_Nm.elementAt(i)%>" size="20" maxlength="30" onBlur="" > --%>
					<%=VPhys_Curve_Nm.elementAt(i)%><%CurveName=""+VCurve_Nm.elementAt(0); %>
				</div>
			</td>
			<td>
				<div align="center">
					<%-- <input type="text" name="curve_nm" value="<%=(String)VCurve_Nm.elementAt(i)%>" size="20" maxlength="30" onBlur="" > --%>
					<%if(!VCurve_Nm.elementAt(i).equals("0")) {%><%=VCurve_Nm.elementAt(i)%><% }%>
				</div>
			</td>
			<td>
				<div align="center">
					<%-- <input type="text" name="slope" value="<%=(String)VSlope.elementAt(i)%>" size="5" maxlength="10" onBlur="checkNumber1(this,9,6);"> --%>
					<%=VSlope.elementAt(i)%>
				</div>
			</td>
			<td>
				<div align="center">
					<%-- <input type="text" name="constant" value="<%=(String)VConst.elementAt(i)%>" size="5" maxlength="10" onBlur="checkNumber1(this,9,6);"> --%>
					<%=VConst.elementAt(i)%>
				</div>
			</td>
			<td TITLE="<%=VPPAC_Price.elementAt(i)%>">
				<div align="center">
					<%-- <input type="text" name="constant" value="<%=(String)VConst.elementAt(i)%>" size="5" maxlength="10" onBlur="checkNumber1(this,9,6);"> --%>
				<%if(VPPAC_Price.elementAt(i).equals("1")) {%>YES<% }%>
					<%//=VPPAC_Price.elementAt(i)%>
				</div>
			</td>
			
			<td TITLE="<%=VPrice_Range.elementAt(i)%>">
				<div align="center">
					<%-- <input type="text" name="constant" value="<%=(String)VConst.elementAt(i)%>" size="5" maxlength="10" onBlur="checkNumber1(this,9,6);"> --%>
					<%if(VPrice_Type.elementAt(i).equals("Float")) {%><%=VPrice_Range.elementAt(i)%><% } %>
				</div>
			</td>
			<td>
				<div align="center">
				<%if(!sn_Dcq_Remark.elementAt(i).equals("")){
					String rmk_min[] = sn_Dcq_Remark.elementAt(i).toString().split("@");
					if(rmk_min[0].equals("MIN"))
					{%>
						<%=VMIN_Price_St_End_dt.elementAt(i) %>
					<%}
					else
					{%>
						<%=VPrice_Start_Dt.elementAt(i)%>-<%=VPrice_End_Dt.elementAt(i)%>
					<%}			
				}
				else
				{%>
					<%=VPrice_Start_Dt.elementAt(i)%>-<%=VPrice_End_Dt.elementAt(i)%>
				<%}%>	
				</div>
			</td> 
			<td>
				<div align="center">
					<%-- <input type="text" name="remark" value="<%=(String)sn_Dcq_Remark.elementAt(i)%>" size="30" maxlength="500"> --%>
					<%=sn_Dcq_Remark.elementAt(i)%>
				</div>
			</td>
			<td>
				<div align="center">
				<%//HARSH20210803
				if(delete_permission.equalsIgnoreCase("Y")){%>
					<input type="button" name="delete" id="delete<%=i%>" style="background-color: LIGHTGREY; font-weight: bold; color: red;" value="Delete" onclick="deleteExistingDCQ('<%=sn_DCQ_seq_no.elementAt(i)%>')" disabled>
				<%}else{ %>
					<input type="button" value="Delete"  disabled title="Do not have Delete Permission!!">
				<%} %>
				</div>
			</td>
		</tr>
<%	}	}%>
<%	if(ind2>0)
	{	%>
		<tr class="title2" style="background-color: #d9edf7;">
			<td colspan="12">
				<div align="left">
					&nbsp;<FONT SIZE=1 align="right">Version: 9.2.0</FONT>
				</div>
			</td>
		</tr>
<%	}
	else
	{	%>
		<tr class="content1">
			<td colspan="11">
				<div align="center">
					<b>Curve Pricing Specification does not Exist ........</b>
				</div>
			</td>
		</tr>
<%	}	%>
	

</table>
</fieldset>
<fieldset  style="width:90%" align="center">
	<table width="100%" align="center">
		
		 <tr class="content1" >
		 	<td style="background-color: #d9edf7;"><div align='left'><b><font color="red">*</font>From Date</b></div></td>
		 	<td  style="background: white;">
		 	<div align='left'>
		 		<input type="text" name="from_dt" value="" size="10" maxlength="11" onBlur="validateDate(this);checkForDateValidity(this,'<%=start_dt%>','<%=end_dt%>');checkFromDate();checkForDateValidityall(this,'<%//=i%>','<%=start_dt%>','<%=end_dt%>');">
		 		<a href="javascript:show_calendar('forms[0].from_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
					<img src="../images/calendar.gif" width="25" height="22" border="0">
				</a>
			</div>
		 	</td>
		 	<td style="background-color: #d9edf7;"><div align='left'><b><font color="red">*</font>To Date</b></div></td>
		 	<td colspan='6'  style="background: white;">
		 	<div align='left'>
		 		<input type="text" name="to_dt" value="" size="10" maxlength="11" onBlur="validateDate(this);checkForDateValidity(this,'<%=start_dt%>','<%=end_dt%>');checkToDate();checkForDateValidityall(this,'<%//=i%>','<%=start_dt%>','<%=end_dt%>');">
		 		<a href="javascript:show_calendar('forms[0].to_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
					<img src="../images/calendar.gif" width="25" height="22" border="0">
				</a>
			</div>
		 	</td>
		 </tr>
		 <input type="hidden" name="dcq" value="" size="10" maxlength="10" value="0" onBlur="checkNumber1(this,9,2);">
		 <tr class="content1">
		 	<td style="background-color: #d9edf7;"><div align='left'><b><font color="red">*</font>Price Type</b></div></td>
		 	<td  style="background: white;">
		 	<div align="left">
				<select class="form-control" name="price_type" id="price_type" onchange="setDiaplay();" >
					<!-- <option value="F">-Select-</option> -->
					<option value="Fixed">Fixed</option>	
					<option value="Float">Float</option>
					<option value="Float(Complex)">Float(Complex)</option>	<!-- HARSH20210809 -->
				</select>
			</div>
			</td>
		 	<td style="background-color: #d9edf7;"><div align='left'><b><font color="red">*</font>Physical Curve</b></div></td>
		 	<td colspan="6"  style="background: white;">
		 		<div align="left">
					<select class="form-control" name="phys_curve_nm" id="phys_curve_nm" onchange="">
 						<option value="0">-Select-</option>
						<!-- <option value="RLNG_PHYS_INDIA">RLNG_PHYS_INDIA</option>	
						<option value="LNG_PHYS_INDIA">LNG_PHYS_INDIA</option>	
 						<option value="Gas_KGD6">Gas_KGD6</option> -->
 						<% for(int j=0; j<VPhysCurveNm.size(); j++){ %>
 						<option value="<%=VPhysCurveNm.elementAt(j)%>"><%=VPhysCurveNm.elementAt(j)%></option>
 						<%} %>
					</select>
				</div>
		 	</td>
		 </tr>
		 <!-- HARSH20210728 -->
		 <tr id="tr1" class="content1" style="display:none;">
		 	<td style="background-color: #d9edf7;"><div align='left'><b>MULTI_LEG / MIN</b></div></td>
		 	<td colspan=""  style="background: white;">
		 		<div align="left">
		 			<select name="opal_min" onchange="setFormula();">
		 				<option value="">--Select--</option>
		 				<option value="MULTI_LEG">MULTI_LEG</option>
		 				<option value="MIN">MIN</option>
		 			</select>
		 		</div>
		 	</td>
		 	<td align="right" style="background-color: #d9edf7;">
		 		<div id="op1" style="display:none;"><b>Multi Period&nbsp;</b></div>
		 	</td>
		 	<td colspan="6" align="left"  style="background: white;">
		 		<div id="op2" style="display:none;">
		 			<select name="formula" onchange="setFormula();">
		 				<option value="">--Leg 1--</option>
		 				<%//HARSH20210916
		 				for(int i=0; i<=36; i++){ %>
		 				<option value="<%=i%>">M<%=i%></option>
		 				<%} %>
		 				<!--HARSH20210916 <option value="0">M0</option>
		 				<option value="1">M1</option>
		 				<option value="2">M2</option>
		 				<option value="3">M3</option>
		 				<option value="4">M4</option>
		 				<option value="5">M5</option>
		 				<option value="6">M6</option>
		 				<option value="7">M7</option>
		 				<option value="8">M8</option>
		 				<option value="9">M9</option> -->
		 			</select>
		 		</div>
		 		&nbsp;
		 		<div id="op3" style="display:none;">
		 			<select name="formula1" onchange="setFormula();">
		 				<option value="">--Leg 2--</option>
		 				
		 				<%//HARSH20210916
		 				for(int i=0; i<=36; i++){ %>
		 				<option value="<%=i%>">M<%=i%></option>
		 				<%} %>
		 				<!--HARSH20210916 <option value="0">M0</option>
		 				<option value="1">M1</option>
		 				<option value="2">M2</option>
		 				<option value="3">M3</option>
		 				<option value="4">M4</option>
		 				<option value="5">M5</option>
		 				<option value="6">M6</option>
		 				<option value="7">M7</option>
		 				<option value="8">M8</option>
		 				<option value="9">M9</option> -->
		 			</select>
		 		</div>
		 	</td>
		 </tr>
		 <!-- /////////////////////////////////////////////////// -->
		 <tr id="tr2" class="content1" style="display:none;">
		 	<td style="background-color: #d9edf7;"><div align='left'><b><font color="red">*</font>Financial Curve</b></div></td>
		 	<td colspan="8"  style="background: white;">
		 		<div align="left">
					<select class="form-control" name="curve_nm" id="curve_nm" onchange="setFormula()">
 						<option value="0" id="o1">-Select-</option>
<!-- 						<option value="LNG_ICE_JKM">LNG_ICE_JKM</option>	 -->
<!-- 						<option value="CR_DATED_BRENT">ICE_BRENT</option> -->
						<% for(int j=0; j<VFinCurveNm.size(); j++){ %>
 						<option value="<%=VFinCurveNm.elementAt(j)%>"><%=VFinCurveNm.elementAt(j)%></option>
 						<%} %>
					</select>
					<br>
					<span id="inf" style="display:none;"><font color="green" size="2"><b>PRESS CTRL/SHIFT FOR MULTIPLE SELECTION</b></font></span>
				</div>
		 	</td>
		 </tr>
		 <tr id="tr3" class="content1" style="display:none;">
		 	<td style="background-color: #d9edf7;"><div align='left'><b><font color="red">*</font>Slope</b></div></td>
		 	<td  style="background: white;">
				<div align="left">
					<input type="text" name="slope" value="1" size="10" maxlength="10" onBlur="checkNumber1(this,9,6);">
				</div>
			</td>
		 	<td style="background-color: #d9edf7;"><div align='left'><b><font color="red">*</font>Constant</b></div></td>
		 	<td colspan=''  style="background: white;">
				<div align="left">
					<input type="text" name="constant" value="0" size="10" maxlength="10" onBlur="checkNumber1(this,9,6);">
				</div>
			</td>
			<td style="background-color: #d9edf7;"><div align='left'><b>Settle Price/Mth</b></div></td>
			<td colspan=''  style="background: white;">
		 		<div align="left">
						<select class="form-control" name="price_range" id="price_range" onchange="openEditBox('','');" >
							<option value="">-Select-</option>
							<option value="A">(A) Avg.</option>
							<option value="F">(F) Final Settle Date</option>
							<option value="O">(A) Other (Avg.)</option>
							<option value="D">(A) Avg. Settle Date</option>
<!-- 							<option value="1">M1</option> -->
<!-- 							<option value="2">M2</option> -->
<!-- 							<option value="3">M3</option>	 -->
						</select>
				</div>
			</td>
			<input type="hidden" name="ppac_validity_dt" value="" size="10" maxlength="11" onBlur="validateDate(this);" readonly>
			<td colspan="3" style="background-color: #d9edf7;">
				<div align="left">
					<span style="display:none;" id="days1"><b>No.of Days: </b></span>
					 <input type="text" name="days" style="display: none;width:50px;" onblur="checkZero(this);" disabled>
					
					<span style="display:none;" id="avg4"><b>From:</b></span> <input type="text" name="price_start_dt" value="" style="display: none;" size="10" maxlength="11" onBlur="validateDate(this);" >
						<a href="javascript:show_calendar('forms[0].price_start_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
							<img src="../images/calendar.gif" width="25" height="22" border="0" id="avg2" style="display:none;"></a>
					<span style="display:none;" id="avg5"><b>To:</b></span> <input type="text" name="price_end_dt"  style="display: none;" value="" size="10" maxlength="11" onBlur="validateDate(this);" >
						<a href="javascript:show_calendar('forms[0].price_end_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
							<img src="../images/calendar.gif" width="25" height="22" border="0" id="avg3" style="display:none;"></a>
				
				</div>
			</td>
		 </tr>
		 <tr id="LNG_ICE_JKM" class="content1 om" style="display:none;">
		 	<td style="background-color: #d9edf7;"><div align='left'><b><font color="red">*</font>Slope</b></div></td>
		 	<td  style="background: white;">
				<div align="left">
					<input type="text" name="MIN_slope" id="LNG_ICE_JKM_slope" value="1" size="10" maxlength="10" onBlur="checkNumber1(this,9,6);" disabled>
				</div>
			</td>
		 	<td style="background-color: #d9edf7;"><div align='left'><b><font color="red">*</font>Constant</b></div></td>
		 	<td colspan=''  style="background: white;">
				<div align="left">
					<input type="text" name="MIN_constant" id="LNG_ICE_JKM_constant" value="0" size="10" maxlength="10" onBlur="checkNumber1(this,9,6);" disabled>
				</div>
			</td>
			<td style="background-color: #d9edf7;"><div align='left'><b>Settle Price/Mth</b></div></td>
			<td colspan=''  style="background: white;">
		 		<div align="left">
					<select class="form-control" name="MIN_price_range" id="LNG_ICE_JKM_price_range" onchange="openEditBox('0','LNG_ICE_JKM');" disabled>
						<option value="">-Select-</option>
						<option value="A">(A) Avg.</option>
						<option value="F">(F) Final Settle Date</option>
						<option value="O">(A) Other (Avg.)</option>
						<option value="D">(A) Avg. Settle Date</option>
<!-- 							<option value="1">M1</option> -->
<!-- 							<option value="2">M2</option> -->
<!-- 							<option value="3">M3</option>	 -->
					</select>
				</div>
			</td>
			<td colspan="2" style="background-color: #d9edf7;">
				<div align="left">
					<div id="MIN_0_0" style="display:none">
					<b>No.of Days: </b>
					 <input type="text" name="MIN_days" id="LNG_ICE_JKM_days" onblur="checkZero(this);" style="width:50px;" disabled>
					</div>
					<div id="MIN_1_0" style="display:none">
					<b>From:</b><input type="text" name="MIN_price_start_dt" id="LNG_ICE_JKM_price_start_dt" value="" size="10" maxlength="11" onBlur="validateDate(this);" disabled>
						<a href="javascript:show_calendar('forms[0].MIN_price_start_dt[0]');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
							<img src="../images/calendar.gif" width="25" height="22" border="0"></a><br>
					<b>To:</b><input type="text" name="MIN_price_end_dt" value="" id="LNG_ICE_JKM_price_end_dt" size="10" maxlength="11" onBlur="validateDate(this);" disabled>
						<a href="javascript:show_calendar('forms[0].MIN_price_end_dt[0]');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
							<img src="../images/calendar.gif" width="25" height="22" border="0"></a>
					</div>
				</div>
			</td>
			<td colspan="">
				<div align="left"><b>For LNG_ICE_JKM</b></div>
			</td>
		 </tr>
		 <% for(int j=0; j<VFinCurveNm.size(); j++){ %>
		 <tr id="<%=VFinCurveNm.elementAt(j)%>" class="content1 om" style="display:none;background-color: #d9edf7;">
		 	<td><div align='left'><b><font color="red">*</font>Slope</b></div></td>
		 	<td  style="background: white;">
				<div align="left">
					<input type="text" name="MIN_slope" id="<%=VFinCurveNm.elementAt(j)%>_slope" value="1" size="10" maxlength="10" onBlur="checkNumber1(this,9,6);" disabled>
				</div>
			</td>
		 	<td style="background-color: #d9edf7;"><div align='left'><b><font color="red">*</font>Constant</b></div></td>
		 	<td colspan=''  style="background: white;">
				<div align="left">
					<input type="text" name="MIN_constant" id="<%=VFinCurveNm.elementAt(j)%>_constant" value="0" size="10" maxlength="10" onBlur="checkNumber1(this,9,6);" disabled>
				</div>
			</td>
			<td style="background-color: #d9edf7;"><div align='left'><b>Settle Price/Mth</b></div></td>
			<td colspan=''  style="background: white;">
		 		<div align="left">
						<select class="form-control" name="MIN_price_range" id="<%=VFinCurveNm.elementAt(j)%>_price_range" onchange="openEditBox('<%=j+1%>','<%=VFinCurveNm.elementAt(j)%>');" disabled>
							<option value="">-Select-</option>
							<option value="A">(A) Avg.</option>
							<option value="F">(F) Final Settle Date</option>
							<option value="O">(A) Other (Avg.)</option>
							<option value="D">(A) Avg. Settle Date</option>
<!-- 							<option value="1">M1</option> -->
<!-- 							<option value="2">M2</option> -->
<!-- 							<option value="3">M3</option>	 -->
						</select>
				</div>
			</td>
			<td colspan="2" style="background-color: #d9edf7;">
				<div align="left">
					<div id="MIN_0_<%=j+1%>" style="display:none">
					<b>No.of Days: </b>
					 <input type="text" name="MIN_days" id="<%=VFinCurveNm.elementAt(j)%>_days" style="width:50px;" onblur="checkZero(this);" disabled>
					</div>
					<div id="MIN_1_<%=j+1%>" style="display:none">
					<b>From:</b><input type="text" name="MIN_price_start_dt" value="" id="<%=VFinCurveNm.elementAt(j)%>_price_start_dt" size="10" maxlength="11" onBlur="validateDate(this);" disabled>
						<a href="javascript:show_calendar('forms[0].MIN_price_start_dt[<%=j+1%>]');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
							<img src="../images/calendar.gif" width="25" height="22" border="0" ></a><br>
					<b>To:</b><input type="text" name="MIN_price_end_dt" value="" id="<%=VFinCurveNm.elementAt(j)%>_price_end_dt" size="10" maxlength="11" onBlur="validateDate(this);" disabled>
						<a href="javascript:show_calendar('forms[0].MIN_price_end_dt[<%=j+1%>]');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
							<img src="../images/calendar.gif" width="25" height="22" border="0"></a>
					</div>
				</div>
			</td>
			<td colspan="">
				<div align="left"><b>For <%=VFinCurveNm.elementAt(j)%></b></div>
			</td>
		 </tr>
		 <%} %>
		 <tr id="tr4" class="content1" style="display:none;">
		 	<td style="background-color: #d9edf7;"><div align='left'><b>PPAC Price</b></div></td>
		 	<td colspan='8'  style="background: white;">
				<div align="left">
				<select class="form-control" name="ppac_price" id="ppac_price" onchange="" >
					<option value="0">Not Applicable</option>
					<option value="1" TITLE="Less than Financial Curve (<%=CurveName%>) Price">Applicable</option>	
<!-- 					<option value="1">Less than Financial Curve (<%=CurveName%>) Price</option>		 -->
				</select>
<!-- 					<input type="text" name="ppac_price" value="" size="10" maxlength="10" value="" onBlur="checkNumber1(this,9,6);"> -->
				</div>
			</td>
		 	 <!-- <td><div align='left'><span style="display: none;" id="avg1"><b>Avg. Settle Date</b></span></div></td>
		 	<td colspan='4'>
		 		<div align="left">
					<span style="display:none;" id="avg4">From:</span> <input type="text" name="price_start_dt" value="" style="display: none;" size="10" maxlength="11" onBlur="validateDate(this);" >
						<a href="javascript:show_calendar('forms[0].price_start_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
							<img src="../images/calendar.gif" width="25" height="22" border="0" id="avg2" style="display:none;"></a>
					<span style="display:none;" id="avg5">To:</span> <input type="text" name="price_end_dt"  style="display: none;" value="" size="10" maxlength="11" onBlur="validateDate(this);" >
						<a href="javascript:show_calendar('forms[0].price_end_dt');" title="Date Picker" onMouseOver="window.status='Date Picker';return true;" onMouseOut="window.status='';return true;">
							<img src="../images/calendar.gif" width="25" height="22" border="0" id="avg3" style="display:none;"></a>
				</div>
			</td> -->
		 </tr>
		 <tr class="content1" style="background-color: #d9edf7;">
		 	<td><div align='left'><b>Remark | Price Formula</b></div></td>
		 	<td colspan='8'  style="background: white;">
				<div align="left">
					<input type="text" name="remark" value="" size="100" maxlength="500">
				</div>
			</td>
		 </tr>
		 <tr class="title2" style="background-color: #d9edf7;">
			<td colspan="9">
				<div align="right">
					&nbsp;<font color="red">* Mandatory Information</font>
				</div>
			</td>
		</tr>
		<tr class="title2" style="background-color: #d9edf7;">
			<td colspan="4">
				<div align="left">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 						
					&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="reload" value="CLOSE" onclick="closeWindow()"><!--HARSH20210803 resetPage(); -->
				</font>
				</div>
			</td>
			<td colspan="5">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<%//HARSH20210803
				if(write_permission.equalsIgnoreCase("Y")){%>
					<%if(!fgsa_cd.equals("") && !fgsa_revno.equals("") && !sn_cd.equals("") && !sn_revno.equals("") && !start_dt.equals("") && !end_dt.equals("")){//HARSH20210930 %>						
						<input type="button" name="save" value="Submit" onClick="doSubmit();">&nbsp;&nbsp;&nbsp;&nbsp;
					<%}else{ %>
					<FONT SIZE=1 COLOR=black>NOTE: Please Sumbit the Contract Details in main Page</FONT>
						<input type="button" name="save" value="Submit" title="Contract Details Yet to be Submitted!!" disabled>&nbsp;&nbsp;&nbsp;&nbsp;
					<%} %>
				<%}else{%>
				<input type="button" name="save" value="Submit" title="Do not have Write Permission!!" disabled>&nbsp;&nbsp;&nbsp;&nbsp;
				<%} %>
				</font>
				</div>
			</td>
		</tr>
	</table>
</fieldset>
<table>
	<tr>
		<td>
			<input type="hidden" name="option" value="insertModifyMRPriceCurveDetails">
			<input type="hidden" name="buyer_name" value="<%=buyer_name%>">
			<input type="hidden" name="buyer_cd" value="<%=buyer_cd%>">
			<input type="hidden" name="cont_type" value="<%=cont_type%>">
			<input type="hidden" name="fgsa_cd" value="<%=fgsa_cd%>">
			<input type="hidden" name="fgsa_revno" value="<%=fgsa_revno%>">
			<input type="hidden" name="sn_cd" value="<%=sn_cd%>">
			<input type="hidden" name="sn_revno" value="<%=sn_revno%>">
			<input type="hidden" name="start_dt" value="<%=start_dt%>">
			<input type="hidden" name="end_dt" value="<%=end_dt%>">
			<input type="hidden" name="write_permission" value="<%=write_permission%>">
			<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
			<input type="hidden" name="print_permission" value="<%=print_permission%>">
			<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
			<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
			<input type="hidden" name="flag" value="<%=flag1%>">
			
			<input type="hidden" name="optional" value="INSERT"><!-- HARSH20201201 -->
			<input type="hidden" name="seq_no" value="">
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>