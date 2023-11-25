<%@ page language="java" import="java.util.*" errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<html>
<head>
<title>DLNG | Master LC Monitoring Details</title>

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

<link rel="stylesheet" href="../css/pt_sans.css">

<!-- jQuery -->
<script type="text/javascript" src="../responsive/js/jquery.min.js"></script>
<!-- Bootstrap JS -->
<script type="text/javascript" src="../js/bootstrap.min.js"></script>

<script type="text/javascript" src="../js/date-picker.js"></script>
<script type="text/javascript" src="../js/aris.js"></script>
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>

<script type="text/javascript" src="../js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<style type="text/css">
td {
    font-size:small;
}
.select {
    width: 200px;
    height: 27px;
} 
</style>
<script type="text/javascript">
$( function() {
	var dt = "#lc_valid_date";
	$(dt).datepicker({
      changeMonth: true,
      changeYear: true
    });
});


var newWindow5;
var newWindow6;
var newWindow7;
var newWindow8;
var newWindow13;

function setCheckBoxes(ind1,ind2,ind3)
{
	var index_1 = parseInt(""+ind1);
	var index_2 = parseInt(""+ind2);
	var index_3 = parseInt(""+ind3);
	
	if(document.forms[0].chk_lc_value[index_1].checked)
	{
		document.forms[0].chk_lc_value[index_2].checked = false;
		document.forms[0].chk_lc_value[index_3].checked = false;
	}
	
	if(index_1==0)
	{
		if(document.forms[0].chk_lc_value[index_1].checked)
		{
			document.forms[0].chk_dcq_tcq[index_1].disabled = false;
			document.forms[0].chk_sn_dcq_tcq.disabled = false;
			
			document.forms[0].chk_dcq_tcq[index_2].disabled = false;
			document.forms[0].chk_dcq_tcq[index_2].checked = false;
			document.forms[0].chk_dcq_tcq[index_2].disabled = true;
			
			document.forms[0].chk_tax_dcq_tcq.disabled = false;
			document.forms[0].tax_days_chk[0].disabled = false;
			document.forms[0].tax_days_chk[1].disabled = false;
			document.forms[0].tax_days_chk[2].disabled = false;
			
			document.forms[0].tax_days.disabled = false;
			document.forms[0].tax_PerTcq.disabled = false;	
			
			document.forms[0].tax_days_chk[0].checked = false;
			document.forms[0].tax_days_chk[1].checked = false;
			document.forms[0].tax_days_chk[2].checked = false;
			document.forms[0].tax_days.value = "";
			document.forms[0].chk_tax_dcq_tcq.checked = false;
			document.forms[0].tax_PerTcq.value = "";
			
			document.forms[0].chk_tax_dcq_tcq.disabled = true;
			document.forms[0].tax_days_chk[0].disabled = true;
			document.forms[0].tax_days_chk[1].disabled = true;
			document.forms[0].tax_days_chk[2].disabled = true;
			
			document.forms[0].tax_days.disabled = true;
			document.forms[0].tax_PerTcq.disabled = true;
		}
		else
		{
			document.forms[0].chk_dcq_tcq[index_1].disabled = false;
			document.forms[0].chk_dcq_tcq[index_1].checked = false;
			document.forms[0].chk_sn_dcq_tcq.disabled = false;
			document.forms[0].chk_sn_dcq_tcq.checked = false;
			document.forms[0].sn_PerTcq.disabled = false;
			document.forms[0].sn_PerTcq.value = "";
			
			document.forms[0].sn_days_chk[0].disabled = false;
			document.forms[0].sn_days_chk[0].checked = false;
			document.forms[0].sn_days_chk[1].disabled = false;
			document.forms[0].sn_days_chk[1].checked = false;
			document.forms[0].sn_days_chk[2].disabled = false;
			document.forms[0].sn_days_chk[2].checked = false;
			document.forms[0].sn_days.disabled = false;
			document.forms[0].sn_days.value = "";
			
			document.forms[0].chk_dcq_tcq[index_1].disabled = true;
			document.forms[0].chk_sn_dcq_tcq.disabled = true;
			document.forms[0].sn_PerTcq.disabled = true;
						
			document.forms[0].sn_days_chk[0].disabled = true;
			document.forms[0].sn_days_chk[1].disabled = true;
			document.forms[0].sn_days_chk[2].disabled = true;
			document.forms[0].sn_days.disabled = true;
		}
	}
	else if(index_1==1)
	{
		if(document.forms[0].chk_lc_value[index_1].checked)
		{
			document.forms[0].chk_dcq_tcq[index_1].disabled = false;
			document.forms[0].chk_tax_dcq_tcq.disabled = false;
			
			document.forms[0].chk_dcq_tcq[index_2].disabled = false;
			document.forms[0].chk_dcq_tcq[index_2].checked = false;
			document.forms[0].chk_dcq_tcq[index_2].disabled = true;
			
			document.forms[0].sn_days_chk[0].disabled = false;
			document.forms[0].sn_days_chk[1].disabled = false;
			document.forms[0].sn_days_chk[2].disabled = false;
			document.forms[0].chk_sn_dcq_tcq.disabled = false;
			
			document.forms[0].sn_days.disabled = false;
			document.forms[0].sn_PerTcq.disabled = false;
			
			document.forms[0].sn_days_chk[0].checked = false;
			document.forms[0].sn_days_chk[1].checked = false;
			document.forms[0].sn_days_chk[2].checked = false;
			document.forms[0].sn_days.value = "";
			document.forms[0].chk_sn_dcq_tcq.checked = false;
			document.forms[0].sn_PerTcq.value = "";
			
			document.forms[0].sn_days_chk[0].disabled = true;
			document.forms[0].sn_days_chk[1].disabled = true;
			document.forms[0].sn_days_chk[2].disabled = true;
			document.forms[0].chk_sn_dcq_tcq.disabled = true;
			
			document.forms[0].sn_days.disabled = true;
			document.forms[0].sn_PerTcq.disabled = true;
		}
		else
		{
			document.forms[0].chk_dcq_tcq[index_1].disabled = false;
			document.forms[0].chk_dcq_tcq[index_1].checked = false;
			document.forms[0].chk_tax_dcq_tcq.disabled = false;
			document.forms[0].chk_tax_dcq_tcq.checked = false;
			document.forms[0].tax_PerTcq.disabled = false;
			document.forms[0].tax_PerTcq.value = "";
			
			document.forms[0].tax_days_chk[0].disabled = false;
			document.forms[0].tax_days_chk[0].checked = false;
			document.forms[0].tax_days_chk[1].disabled = false;
			document.forms[0].tax_days_chk[1].checked = false;
			document.forms[0].tax_days_chk[2].disabled = false;
			document.forms[0].tax_days_chk[2].checked = false;
			document.forms[0].tax_days.disabled = false;
			document.forms[0].tax_days.value = "";
			
			document.forms[0].chk_dcq_tcq[index_1].disabled = true;
			document.forms[0].chk_tax_dcq_tcq.disabled = true;
			document.forms[0].tax_PerTcq.disabled = true;
						
			document.forms[0].tax_days_chk[0].disabled = true;
			document.forms[0].tax_days_chk[1].disabled = true;
			document.forms[0].tax_days_chk[2].disabled = true;
			document.forms[0].tax_days.disabled = true;
		}
	}
	else if(index_1==2)
	{
		if(document.forms[0].chk_lc_value[index_1].checked)
		{
			document.forms[0].chk_dcq_tcq[index_2].disabled = false;
			document.forms[0].chk_dcq_tcq[index_3].disabled = false;
			
			document.forms[0].chk_dcq_tcq[index_2].checked = false;
			document.forms[0].chk_dcq_tcq[index_3].checked = false;
			
			document.forms[0].chk_dcq_tcq[index_2].disabled = true;
			document.forms[0].chk_dcq_tcq[index_3].disabled = true;
			
			document.forms[0].sn_days_chk[0].disabled = false;
			document.forms[0].sn_days_chk[1].disabled = false;
			document.forms[0].sn_days_chk[2].disabled = false;
			document.forms[0].chk_sn_dcq_tcq.disabled = false;
			
			document.forms[0].sn_days.disabled = false;
			document.forms[0].sn_PerTcq.disabled = false;
			
			document.forms[0].sn_days_chk[0].checked = false;
			document.forms[0].sn_days_chk[1].checked = false;
			document.forms[0].sn_days_chk[2].checked = false;
			document.forms[0].sn_days.value = "";
			document.forms[0].chk_sn_dcq_tcq.checked = false;
			document.forms[0].sn_PerTcq.value = "";
			
			document.forms[0].sn_days_chk[0].disabled = true;
			document.forms[0].sn_days_chk[1].disabled = true;
			document.forms[0].sn_days_chk[2].disabled = true;
			document.forms[0].chk_sn_dcq_tcq.disabled = true;
			
			document.forms[0].sn_days.disabled = true;
			document.forms[0].sn_PerTcq.disabled = true;
			
			
			document.forms[0].chk_tax_dcq_tcq.disabled = false;
			document.forms[0].tax_days_chk[0].disabled = false;
			document.forms[0].tax_days_chk[1].disabled = false;
			document.forms[0].tax_days_chk[2].disabled = false;
			
			document.forms[0].tax_days.disabled = false;
			document.forms[0].tax_PerTcq.disabled = false;
						
			document.forms[0].tax_days_chk[0].checked = false;
			document.forms[0].tax_days_chk[1].checked = false;
			document.forms[0].tax_days_chk[2].checked = false;
			document.forms[0].tax_days.value = "";
			document.forms[0].chk_tax_dcq_tcq.checked = false;
			document.forms[0].tax_PerTcq.value = "";
						
			document.forms[0].chk_tax_dcq_tcq.disabled = true;
			document.forms[0].tax_days_chk[0].disabled = true;
			document.forms[0].tax_days_chk[1].disabled = true;
			document.forms[0].tax_days_chk[2].disabled = true;
			
			document.forms[0].tax_days.disabled = true;
			document.forms[0].tax_PerTcq.disabled = true;
		}
	}
}


function setInternalCheckBoxes1(ind1,ind2)
{
	var index_1 = parseInt(""+ind1);
	var index_2 = parseInt(""+ind2);
	
	if(index_1==0)
	{
		if(document.forms[0].chk_dcq_tcq[index_1].checked)
		{
			document.forms[0].sn_days_chk[0].disabled = false;
			document.forms[0].sn_days_chk[1].disabled = false;
			document.forms[0].sn_days_chk[2].disabled = false;
			
			document.forms[0].chk_sn_dcq_tcq.checked = false;
			document.forms[0].sn_PerTcq.disabled = false;
			document.forms[0].sn_PerTcq.value = "";
			document.forms[0].sn_PerTcq.disabled = true;
		}
		else
		{
			document.forms[0].sn_days_chk[0].disabled = false;
			document.forms[0].sn_days_chk[1].disabled = false;
			document.forms[0].sn_days_chk[2].disabled = false;
			
			document.forms[0].sn_days_chk[0].checked = false;
			document.forms[0].sn_days_chk[1].checked = false;
			document.forms[0].sn_days_chk[2].checked = false;
			
			document.forms[0].sn_days_chk[0].disabled = true;
			document.forms[0].sn_days_chk[1].disabled = true;
			document.forms[0].sn_days_chk[2].disabled = true;
			
			document.forms[0].sn_days.disabled = false;
			document.forms[0].sn_days.value = "";
			document.forms[0].sn_days.disabled = true;
		}
	}
	else if(index_1==1)
	{
		if(document.forms[0].chk_dcq_tcq[index_1].checked)
		{
			document.forms[0].tax_days_chk[0].disabled = false;
			document.forms[0].tax_days_chk[1].disabled = false;
			document.forms[0].tax_days_chk[2].disabled = false;
			
			document.forms[0].chk_tax_dcq_tcq.checked = false;
			document.forms[0].tax_PerTcq.disabled = false;
			document.forms[0].tax_PerTcq.value = "";
			document.forms[0].tax_PerTcq.disabled = true;
		}
		else
		{
			document.forms[0].tax_days_chk[0].disabled = false;
			document.forms[0].tax_days_chk[1].disabled = false;
			document.forms[0].tax_days_chk[2].disabled = false;
			
			document.forms[0].tax_days_chk[0].checked = false;
			document.forms[0].tax_days_chk[1].checked = false;
			document.forms[0].tax_days_chk[2].checked = false;
			
			document.forms[0].tax_days_chk[0].disabled = true;
			document.forms[0].tax_days_chk[1].disabled = true;
			document.forms[0].tax_days_chk[2].disabled = true;
			
			document.forms[0].tax_days.disabled = false;
			document.forms[0].tax_days.value = "";
			document.forms[0].tax_days.disabled = true;
		}
	}
}


function setInternalCheckBoxes2(ind1,ind2)
{
	var index_1 = parseInt(""+ind1);
	var index_2 = parseInt(""+ind2);
	
	if(index_1==0)
	{
		if(document.forms[0].chk_sn_dcq_tcq.checked)
		{
			document.forms[0].sn_days_chk[0].disabled = false;
			document.forms[0].sn_days_chk[1].disabled = false;
			document.forms[0].sn_days_chk[2].disabled = false;
			document.forms[0].sn_days.disabled = false;
			
			document.forms[0].chk_dcq_tcq[index_1].checked = false;
			document.forms[0].sn_days_chk[0].checked = false;
			document.forms[0].sn_days_chk[1].checked = false;
			document.forms[0].sn_days_chk[2].checked = false;
			document.forms[0].sn_days.value = "";
			
			document.forms[0].sn_days_chk[0].disabled = true;
			document.forms[0].sn_days_chk[1].disabled = true;
			document.forms[0].sn_days_chk[2].disabled = true;
			document.forms[0].sn_days.disabled = true;
			
			document.forms[0].sn_PerTcq.disabled = false;
		}
		else
		{
			document.forms[0].sn_PerTcq.disabled = false;
			document.forms[0].sn_PerTcq.value = "";
			document.forms[0].sn_PerTcq.disabled = true;
		}
	}
	else if(index_1==1)
	{
		if(document.forms[0].chk_tax_dcq_tcq.checked)
		{
			document.forms[0].tax_days_chk[0].disabled = false;
			document.forms[0].tax_days_chk[1].disabled = false;
			document.forms[0].tax_days_chk[2].disabled = false;
			document.forms[0].tax_days.disabled = false;
			
			document.forms[0].chk_dcq_tcq[index_1].checked = false;
			document.forms[0].tax_days_chk[0].checked = false;
			document.forms[0].tax_days_chk[1].checked = false;
			document.forms[0].tax_days_chk[2].checked = false;
			document.forms[0].tax_days.value = "";
			
			document.forms[0].tax_days_chk[0].disabled = true;
			document.forms[0].tax_days_chk[1].disabled = true;
			document.forms[0].tax_days_chk[2].disabled = true;
			document.forms[0].tax_days.disabled = true;
			
			document.forms[0].tax_PerTcq.disabled = false;
		}
		else
		{
			document.forms[0].tax_PerTcq.disabled = false;
			document.forms[0].tax_PerTcq.value = "";
			document.forms[0].tax_PerTcq.disabled = true;
		}
	}
}


function setInternalCheckBoxes3(ind,flg)
{
	var index = parseInt(""+ind);
	var flag = ""+flg;
	
	if(flag=='SN' && index==0)
	{
		if(document.forms[0].sn_days_chk[index].checked)
		{
			document.forms[0].sn_days.disabled = false;
			document.forms[0].sn_days_chk[1].checked = false;
			document.forms[0].sn_days_chk[2].checked = false;
			document.forms[0].sn_days.value = "";
			document.forms[0].sn_days.disabled = true;
		}
	}
	else if(flag=='SN' && index==1)
	{
		if(document.forms[0].sn_days_chk[index].checked)
		{
			document.forms[0].sn_days.disabled = false;
			document.forms[0].sn_days_chk[0].checked = false;
			document.forms[0].sn_days_chk[2].checked = false;
			document.forms[0].sn_days.value = "";
			document.forms[0].sn_days.disabled = true;
		}
	}
	else if(flag=='SN' && index==2)
	{
		if(document.forms[0].sn_days_chk[index].checked)
		{
			document.forms[0].sn_days.disabled = false;
			document.forms[0].sn_days_chk[0].checked = false;
			document.forms[0].sn_days_chk[1].checked = false;
		}
		else
		{
			document.forms[0].sn_days.disabled = false;
			document.forms[0].sn_days.value = "";
			document.forms[0].sn_days.disabled = true;
		}
	}
	else if(flag=='TAX' && index==0)
	{
		if(document.forms[0].tax_days_chk[index].checked)
		{
			document.forms[0].tax_days.disabled = false;
			document.forms[0].tax_days_chk[1].checked = false;
			document.forms[0].tax_days_chk[2].checked = false;
			document.forms[0].tax_days.value = "";
			document.forms[0].tax_days.disabled = true;
		}
	}
	else if(flag=='TAX' && index==1)
	{
		if(document.forms[0].tax_days_chk[index].checked)
		{
			document.forms[0].tax_days.disabled = false;
			document.forms[0].tax_days_chk[0].checked = false;
			document.forms[0].tax_days_chk[2].checked = false;
			document.forms[0].tax_days.value = "";
			document.forms[0].tax_days.disabled = true;
		}
	}
	else if(flag=='TAX' && index==2)
	{
		if(document.forms[0].tax_days_chk[index].checked)
		{
			document.forms[0].tax_days.disabled = false;
			document.forms[0].tax_days_chk[0].checked = false;
			document.forms[0].tax_days_chk[1].checked = false;
		}
		else
		{
			document.forms[0].tax_days.disabled = false;
			document.forms[0].tax_days.value = "";
			document.forms[0].tax_days.disabled = true;
		}
	}
}


function fetchSetCheckBoxesValues()
{
	if(document.forms[0].chk_lc_value[0].checked)
	{
		document.forms[0].flag_lc_value.value = "SN";
		if(document.forms[0].chk_dcq_tcq[0].checked)
		{
			document.forms[0].flag_dcq_tcq.value = "DCQ";
			if(document.forms[0].sn_days_chk[0].checked)
			{
				document.forms[0].dcqdays_tcqpercent_value.value = "7";
			}
			else if(document.forms[0].sn_days_chk[1].checked)
			{
				document.forms[0].dcqdays_tcqpercent_value.value = "15";
			}
			else if(document.forms[0].sn_days_chk[2].checked)
			{
				document.forms[0].dcqdays_tcqpercent_value.value = document.forms[0].sn_days.value;
			}
		}
		else if(document.forms[0].chk_sn_dcq_tcq.checked)
		{
			document.forms[0].flag_dcq_tcq.value = "TCQ";
			document.forms[0].dcqdays_tcqpercent_value.value = document.forms[0].sn_PerTcq.value;
		}
	}
	else if(document.forms[0].chk_lc_value[1].checked)
	{
		document.forms[0].flag_lc_value.value = "TAX";
		if(document.forms[0].chk_dcq_tcq[1].checked)
		{
			document.forms[0].flag_dcq_tcq.value = "DCQ";
			if(document.forms[0].tax_days_chk[0].checked)
			{
				document.forms[0].dcqdays_tcqpercent_value.value = "7";
			}
			else if(document.forms[0].tax_days_chk[1].checked)
			{
				document.forms[0].dcqdays_tcqpercent_value.value = "15";
			}
			else if(document.forms[0].tax_days_chk[2].checked)
			{
				document.forms[0].dcqdays_tcqpercent_value.value = document.forms[0].tax_days.value;
			}
		}
		else if(document.forms[0].chk_tax_dcq_tcq.checked)
		{
			document.forms[0].flag_dcq_tcq.value = "TCQ";
			document.forms[0].dcqdays_tcqpercent_value.value = document.forms[0].tax_PerTcq.value;
		}
	}
	else if(document.forms[0].chk_lc_value[2].checked)
	{
		document.forms[0].flag_lc_value.value = "OPEN";
		document.forms[0].flag_dcq_tcq.value = "";
		document.forms[0].dcqdays_tcqpercent_value.value = "0";
	}	
}


function setCheckBoxValues(flag_lc_value,flag_dcq_tcq,dcqdays_tcqpercent_value)
{
	if(flag_lc_value=="OPEN")
	{
		document.forms[0].chk_lc_value[2].checked = true;
	}
	else if(flag_lc_value=="SN")
	{
		document.forms[0].chk_lc_value[0].checked = true;
		
		if(flag_dcq_tcq=="DCQ")
		{
			document.forms[0].chk_dcq_tcq[0].disabled = false;
			document.forms[0].chk_dcq_tcq[0].checked = true;
			
			if(dcqdays_tcqpercent_value=="" || dcqdays_tcqpercent_value=="0" || dcqdays_tcqpercent_value=="0.0" || dcqdays_tcqpercent_value=="0.00")
			{
				
			}
			else
			{
				if(dcqdays_tcqpercent_value=="7")
				{
					document.forms[0].sn_days_chk[0].disabled = false;
					document.forms[0].sn_days_chk[0].checked = true;
				}
				else if(dcqdays_tcqpercent_value=="15")
				{
					document.forms[0].sn_days_chk[1].disabled = false;
					document.forms[0].sn_days_chk[1].checked = true;
				}
				else
				{
					document.forms[0].sn_days_chk[2].disabled = false;
					document.forms[0].sn_days.disabled = false;
					
					document.forms[0].sn_days_chk[2].checked = true;					
					document.forms[0].sn_days.value = dcqdays_tcqpercent_value;
				}
			}
		}
		else if(flag_dcq_tcq=="TCQ")
		{
			document.forms[0].chk_sn_dcq_tcq.disabled = false;
			document.forms[0].chk_sn_dcq_tcq.checked = true;
			
			if(dcqdays_tcqpercent_value=="" || dcqdays_tcqpercent_value=="0" || dcqdays_tcqpercent_value=="0.0" || dcqdays_tcqpercent_value=="0.00")
			{
				
			}
			else
			{
				document.forms[0].sn_PerTcq.disabled = false;
				document.forms[0].sn_PerTcq.value = dcqdays_tcqpercent_value;
			}
		}
	}
	else if(flag_lc_value=="TAX")
	{
		document.forms[0].chk_lc_value[1].checked = true;
		
		if(flag_dcq_tcq=="DCQ")
		{
			document.forms[0].chk_dcq_tcq[1].disabled = false;
			document.forms[0].chk_dcq_tcq[1].checked = true;
			
			if(dcqdays_tcqpercent_value=="" || dcqdays_tcqpercent_value=="0" || dcqdays_tcqpercent_value=="0.0" || dcqdays_tcqpercent_value=="0.00")
			{
				
			}
			else
			{
				if(dcqdays_tcqpercent_value=="7")
				{
					document.forms[0].tax_days_chk[0].disabled = false;
					document.forms[0].tax_days_chk[0].checked = true;
				}
				else if(dcqdays_tcqpercent_value=="15")
				{
					document.forms[0].tax_days_chk[1].disabled = false;
					document.forms[0].tax_days_chk[1].checked = true;
				}
				else
				{
					document.forms[0].tax_days_chk[2].disabled = false;
					document.forms[0].tax_days.disabled = false;
					
					document.forms[0].tax_days_chk[2].checked = true;					
					document.forms[0].tax_days.value = dcqdays_tcqpercent_value;
				}
			}
		}
		else if(flag_dcq_tcq=="TCQ")
		{
			document.forms[0].chk_tax_dcq_tcq.disabled = false;
			document.forms[0].chk_tax_dcq_tcq.checked = true;
			
			if(dcqdays_tcqpercent_value=="" || dcqdays_tcqpercent_value=="0" || dcqdays_tcqpercent_value=="0.0" || dcqdays_tcqpercent_value=="0.00")
			{
				
			}
			else
			{
				document.forms[0].tax_PerTcq.disabled = false;
				document.forms[0].tax_PerTcq.value = dcqdays_tcqpercent_value;
			}
		}
	}
}


function doSubmit1()
{
	fetchSetCheckBoxesValues();
	
	var count = parseInt("1");
	var decision_flag = false;
	var decision_msg = "";
	var flag_lc_value = document.forms[0].flag_lc_value.value;	
	var flag_dcq_tcq = document.forms[0].flag_dcq_tcq.value;
	var dcqdays_tcqpercent_value = document.forms[0].dcqdays_tcqpercent_value.value;
		
	if(flag_lc_value=="OPEN")
	{
		decision_flag = true;
	}
	else if(flag_lc_value=="SN")
	{
		if(flag_dcq_tcq=="DCQ")
		{
			if(dcqdays_tcqpercent_value=="" || dcqdays_tcqpercent_value=="0" || dcqdays_tcqpercent_value=="0.0" || dcqdays_tcqpercent_value=="0.00")
			{
				decision_flag = false;
				decision_msg = "Please, select atleast One Option from:\n(1) 7,\n(2) 15, or\n(3) User Defined Days with Value\nfor Days & DCQ Option nunder Against SN Coverage !!!";
			}
			else
			{
				decision_flag = true;
			}
		}
		else if(flag_dcq_tcq=="TCQ")
		{
			if(dcqdays_tcqpercent_value=="" || dcqdays_tcqpercent_value=="0" || dcqdays_tcqpercent_value=="0.0" || dcqdays_tcqpercent_value=="0.00")
			{
				decision_flag = false;
				decision_msg = "Please, enter value against % of TCQ field ...\nunder Against SN Coverage !!!";
			}
			else
			{
				decision_flag = true;
			}
		}
		else
		{
			decision_flag = false;
			decision_msg = "Please, select atleast One Option from:\n(1)Days & DCQ, or \n(2)% of TCQ\nunder Against SN Coverage !!!";
		}
	}
	else if(flag_lc_value=="TAX")
	{
		if(flag_dcq_tcq=="DCQ")
		{
			if(dcqdays_tcqpercent_value=="" || dcqdays_tcqpercent_value=="0" || dcqdays_tcqpercent_value=="0.0" || dcqdays_tcqpercent_value=="0.00")
			{
				decision_flag = false;
				decision_msg = "Please, select atleast One Option from:\n(1) 7,\n(2) 15, or\n(3) User Defined Days with Value\nfor Days & DCQ Option under Against Tax & Advance !!!";
			}
			else
			{
				decision_flag = true;
			}
		}
		else if(flag_dcq_tcq=="TCQ")
		{
			if(dcqdays_tcqpercent_value=="" || dcqdays_tcqpercent_value=="0" || dcqdays_tcqpercent_value=="0.0" || dcqdays_tcqpercent_value=="0.00")
			{
				decision_flag = false;
				decision_msg = "Please, enter value against % of TCQ field ...\nunder Against Tax & Advance !!!";
			}
			else
			{
				decision_flag = true;
			}
		}
		else
		{
			decision_flag = false;
			decision_msg = "Please, select atleast One Option from:\n(1)Days & DCQ, or \n(2)% of TCQ\nunder Against Tax & Advance !!!";
		}
	}
	else
	{
		decision_flag = false;
		decision_msg = "Please, select atleast One Option from:\n(1)Against SN Coverage,\n(2)Against Tax & Advance, or\n(3)Open Account";
	}
		
	if(count>0)
	{
    	if(decision_flag)
    	{
    		var a = confirm("Do you want to submit LC Master Details ???");
    		if(a)
    		{
    			document.forms[0].submit();
    		}
    		else
    		{
    			return;
    		}	
    	}
    	else
    	{
    		alert(""+decision_msg);
    		return;
    	}	
    }
    else
    {
    	alert("Please, Select atleast ONE record for LC Monitoring data Insertion/Modification !!!");
    	return;
    }
}
function doSubmit() {
	var a = confirm("Do you want to submit LC Master Details ???");
	if(a)
	{
		document.forms[0].submit();
	}
	else
	{
		return;
	}
}
</script>

</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="Util" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.contract_master.DataBean_Contract_MasterV2" id="contMaster" scope="page"/>   
<jsp:useBean class="com.seipl.hazira.dlng.util.DataBean_modulelock" id="modlock" scope="request"/>
<% 
	String emp_cd = (String)session.getAttribute("user_cd")==null?"":(String)session.getAttribute("user_cd");
	String emp_name = (String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
	String comp_cd = (String)session.getAttribute("comp_cd")==null?"":(String)session.getAttribute("comp_cd");
	
	String typ = request.getParameter("typ")==null?"1":request.getParameter("typ");
	String activity = request.getParameter("activity")==null?"insert":request.getParameter("activity");
		
	String fgsa_no = request.getParameter("fgsa_no")==null?"0":request.getParameter("fgsa_no");
	String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"0":request.getParameter("fgsa_rev_no");
	String customer_cd = request.getParameter("customer_cd")==null?"0":request.getParameter("customer_cd");
	String customer_nm = request.getParameter("customer_nm")==null?"":request.getParameter("customer_nm");
	String customer_abbr = request.getParameter("customer_abbr")==null?"":request.getParameter("customer_abbr");
	String sn_no = request.getParameter("sn_no")==null?"0":request.getParameter("sn_no");
	String sn_rev_no = request.getParameter("sn_rev_no")==null?"0":request.getParameter("sn_rev_no");
	String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
	String cont_type = request.getParameter("cont_type")==null?"F":request.getParameter("cont_type");
	
	String form_id = request.getParameter("form_id")==null?"0":request.getParameter("form_id");
	String form_nm = request.getParameter("form_nm")==null?"":request.getParameter("form_nm");
	String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
	String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
	String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
	String flag = request.getParameter("flag")==null?"":request.getParameter("flag");
	
	contMaster.setFGSA_cd(fgsa_no);
	contMaster.setFGSA_REVNo(fgsa_rev_no);
	contMaster.setSN_CD(sn_no);
	contMaster.setSN_REVNo(sn_rev_no);
	contMaster.setBuyer_cd(customer_cd);
	contMaster.setCont_type(cont_type);
	contMaster.setCallFlag("Fetch_FGSA_Tender_LC_Dtl");
	contMaster.setRequest(request);
	contMaster.init();
	
	//Following (5) String Getter Methods Has Been Defined By Samik Shah On 22nd November, 2010 ...
	String flag_lc_value = contMaster.getFlag_lc_value();
	String flag_dcq_tcq = contMaster.getFlag_dcq_tcq();
	String dcqdays_tcqpercent_value = contMaster.getDcqdays_tcqpercent_value();
	String exchg_rate = contMaster.getExchg_rate();
	String lc_remarks = contMaster.getLc_remarks();
	
	String lc_amt = contMaster.getLc_amt();
	String lc_date = contMaster.getLc_date();
	String lc_bank_dtl = contMaster.getLc_bank_dtl();
	String lc_file_nm = contMaster.getLc_file_nm();
	String lc_remark = contMaster.getLc_remark();
	String lc_flag = contMaster.getLc_flag();
	customer_nm = contMaster.getLc_customer_nm();
	String fcc_apply = contMaster.getFcc_apply();
	String fcc_note = contMaster.getFcc_note();
	String filePath = contMaster.getFilePath();
%>

<body>
<div class="tab-content">
	<div class="tab-pane active" id="">
		<div class="box mb-0">
			<form method="post" action="../servlet/Frm_Contract_MasterV2" <%if(!flag.equals("R")) { %> enctype="multipart/form-data" <% } %>>
			
				<input type="hidden" name="customer_cd" value="<%=customer_cd%>">
    			<input type="hidden" name="customer_nm" value="<%=customer_nm%>">
    			<input type="hidden" name="customer_abbr" value="<%=customer_abbr%>">
    			<input type="hidden" name="cont_type" value="<%=cont_type%>">
    			<input type="hidden" name="filePath" value="<%=filePath%>">
    			<input name="option" type="hidden" value="LC_MANUAL_ENTRY"> <!-- LC_MASTER_DETAILS_ENTRY -->
				<input type="hidden" name="form_id" value="<%=form_id%>">
				<input type="hidden" name="form_nm" value="<%=form_nm%>">
				<input type="hidden" name="write_permission" value="<%=write_permission%>">
				<input type="hidden" name="delete_permission" value="<%=delete_permission%>">
				<input type="hidden" name="print_permission" value="<%=print_permission%>">
				<input type="hidden" name="approve_permission" value="<%=approve_permission%>">
				<input type="hidden" name="audit_permission" value="<%=audit_permission%>">
				
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
									<tr>
										<th colspan="12" class="info text-center"><b><%if(cont_type.equalsIgnoreCase("F")){%>FLSA<%}else if(cont_type.equalsIgnoreCase("T")){%>Tender<%}else if(cont_type.equalsIgnoreCase("S")){%>SN<%}else if(cont_type.equalsIgnoreCase("L")){%>LoA<%}%> - LC Monitoring Master Details</b></th>
									</tr>
									<tr>
										<td colspan="1" width="30%" class="info">
											<b>Customer</b>
										</td>
										<td colspan="5" class="text-left"><%=customer_nm%></td>
										<td class="info" colspan="3">
							    			<%if(cont_type.equalsIgnoreCase("F")){%><b>FLSA</b><%}else if(cont_type.equalsIgnoreCase("T")){%><b>Tender</b><%}else if(cont_type.equalsIgnoreCase("S")){%><b>SN</b><%}else if(cont_type.equalsIgnoreCase("L")){%><b>LoA</b><%}%>
							    		</td>
							    		<td colspan="3">	
							    			<%if(cont_type.equalsIgnoreCase("F") || cont_type.equalsIgnoreCase("T")){%><%=fgsa_no%><%}else if(cont_type.equalsIgnoreCase("S") || cont_type.equalsIgnoreCase("L")){%><%=sn_no%><%}%>
							    			<input type="hidden" name="fgsa_no" value="<%=fgsa_no%>">
							    			<input type="hidden" name="fgsa_rev_no" value="<%=fgsa_rev_no%>">
							    			<input type="hidden" name="sn_no" value="<%=sn_no%>">
							    			<input type="hidden" name="sn_rev_no" value="<%=sn_rev_no%>">
										</td>
									</tr>
									<!-- New LC Details... -->
									<tr>
							        	<td class="info" colspan="1" width="30%">
							        		<b>LC Amount (INR) </b>
							        	</td>
							        	<td colspan="5">
							        		<input type="text" value="<%=lc_amt%>" name="lc_amount" style="text-align: right;" size="10" maxlength="19" onblur="checkNumber1(this,18,2)" <%if(flag.equals("R")) { %> readonly="readonly" class="mkrdly"<% } %>>
							        	</td>
							        	<td class="info" colspan="3">
							        		<b>Valid Up To  </b>
							        	</td>	
							        	<td colspan="3">
							        		<input type="text" name="lc_valid_date" value="<%=lc_date %>" id="lc_valid_date" size="10" style="text-align: center;" <%if(flag.equals("R")) { %> readonly="readonly" class="mkrdly"<% } %>>
							        	</td>
							        </tr> 
									<tr >
							        	<td class="info" colspan="1" width="30%">
							        		<b>Issuing Bank Details</b> 
							        	</td>
							        	<td colspan="5">
							        		<input type="text" name="issue_bank" value="<%=lc_bank_dtl %>" size="30" maxlength="50" <%if(flag.equals("R")) { %> readonly="readonly" class="mkrdly"<% } %>>
							        	</td>
							        	<td class="info" colspan="3">
							        		<b>LC File Upload</b>
							        	</td>
							        	<td colspan="3">
							        		<%if(flag.equals("R")) { 
							        			if(!filePath.equals("")) { %>
							        			<a href="#" onclick="openDoc();">Click to Open File</a> <% } %>
							        		<% } else { %>
							        			<input type="file" name="lc_file" accept="application/pdf" >
							        		<% } %>
							        	</td>
							        </tr>
							        <tr>
							        	<td colspan="1" width="30%" class="info">
							        		<b>Remark</b>
							        	</td>
							        	<td colspan="11">
							        		<input type="text" name="remark" value="<%=lc_remark %>" size="100" maxlength="100" <%if(flag.equals("R")) { %> readonly="readonly" class="mkrdly"<% } %>>
							        	</td>
						        	</tr>
						        	
						        	<%
									if(flag.equals("R")) { %>
										<tr >
											<td colspan="1" width="30%" class="info">
												<b >Apply LC </b> 
											</td>
											<td colspan="11">
												<input type="radio" name="lc_apply0" value="Y" onclick="setNote(this,'Y');"> Yes &nbsp;
												<input type="radio" name="lc_apply1" value="N" onclick="setNote(this,'N');"> No &nbsp;
												<input type="hidden" name="lc_apply" value="<%=fcc_apply%>">
											</td>
										</tr>
										<tr >
											<td colspan="1" width="30%" class="info"><b><span id="astrick" style="visibility: hidden;"><font color="red">*</font></span>Notes </b></td>
											<td colspan="11"><input type="text" name="note" value="<%=fcc_note%>" size="100" maxlength="100"></td>
										</tr>
										<tr>
											<td colspan="12"  style="background-color: white;" class="text-right">
							    				<button type="reset" class="btn btn-info" name="reset" value="Reset" onClick="resetDataFCC();"> Reset  </button>
							    				<%	if(write_permission.trim().equalsIgnoreCase("Y")){	%>
							    					<button type="button"  class="btn btn-success" onClick="doSubmitFCC();">Submit </button>
							    					
							    				<%}else{ %>
							    					<button type="button"  class="btn btn-success" disabled="disabled" title="You Dont Have Write Permission">Submit </button>
							    				<%} %>	
								    		</td>
								    	</tr>	
										<%
										if(fcc_apply.equals("Y")) { %>
										<script>
											document.forms[0].lc_apply0.checked=true;
											document.getElementById("astrick").style.visibility = 'hidden';
											</script>
										<% } else if(fcc_apply.equals("N")) { %>
										<script>
											document.forms[0].lc_apply1.checked=true;
											document.getElementById("astrick").style.visibility = 'visible';
											</script>
											
										<% } %>
									<% } %>
									
									<%if(!flag.equals("R")) { %>
							        <tr>
							            <td colspan="12"  style="background-color: white;" class="text-right">
							    				<button type="reset" class="btn btn-info" name="reset" value="Reset" onClick="resetDataFCC();"> Reset  </button>
							    				<%	if(write_permission.trim().equalsIgnoreCase("Y")){	%>
							    					<button type="button"  class="btn btn-success" onClick="doSubmit();">Submit </button>
							    					
							    				<%}else{ %>
							    					<button type="button"  class="btn btn-success" disabled="disabled" title="You Dont Have Write Permission">Submit </button>
							    				<%} %>	
								    		</td>
									</tr>
									<% } %>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
  <script>
  	function setNote(obj,value) {
  		if(value=='Y') {
  			document.getElementById("astrick").style.visibility = "hidden";
  			document.forms[0].lc_apply1.checked = false;
  		} else {
  			document.getElementById("astrick").style.visibility = "visible";
  			document.forms[0].lc_apply0.checked = false;
  		}
  	}
  	function openDoc() {
  		var path = document.forms[0].filePath.value;
  		window.open(path,"actionReport","top=10,left=70,width=650,height=650,scrollbars=1,status=1,maximize=yes,resizable=1");
  	}
  </script>
<script>
	function resetData() {
		document.forms[0].lc_amount.value='';
		document.forms[0].lc_valid_date.value='';
		document.forms[0].issue_bank.value='';
		document.forms[0].lc_file.value='';
	}
	function resetDataFCC() {
		document.forms[0].lc_apply0.checked = false;
		document.forms[0].lc_apply1.checked = false;
		document.forms[0].note.value='';
		document.getElementById("astrick").style.visibility = "hidden";
	}
	function doSubmitFCC() {
		var flag = true;
		if(document.forms[0].lc_apply1.checked) {
			document.forms[0].lc_apply.value="N";
			var note = document.forms[0].note.value;
			if(note=='') {
				alert("Please Enter Notes For Not Applying LC!!");
				flag = false;
			} 
		} else if(document.forms[0].lc_apply0.checked) {
			document.forms[0].lc_apply.value="Y";
		} else {
			alert("Please Select LC Applicable Option!!");
			flag = false;
		}
		
		if(flag==true) {
			var a = confirm("Do You Want to Submit LC Details?");
			if(a) {
				document.forms[0].option.value='LcMasterDtlFCC';
				document.forms[0].submit();
			}
		}
	}
</script>


</body>
</html>