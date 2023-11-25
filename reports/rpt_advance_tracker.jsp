<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Invoice Statement Report</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/tlu.css">

<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/util2.js"></script>
<script type="text/javascript" src="../js/util1.js"></script>
<script type="text/javascript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/fms.js"></script>
<style type="text/css">
.table-responsive {
    max-height:300px;
    width: 80%;
    margin: 0px auto;
    
}
</style>

<script type="text/javascript">
function compareDates(obj) {
	var from_date = document.forms[0].from_date.value;
	var to_date = document.forms[0].to_date.value;
	if(from_date!='' && to_date!='') {
		var c = compareDate(from_date,to_date);
		if(c == 1){
			alert("From Date Should Be Less Then To Date!!");
			obj.value = '';
			
		}
	}
}

function fetchRpt(){
	
	var flg=true;
	var msg='';
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	
	var from_date = document.forms[0].from_date.value;
	var to_date = document.forms[0].to_date.value;
	var mapping_id = document.forms[0].mapping_id.value;
	
// 	alert(mapping_id)
	if(from_date == '' || from_date == ' '){
		flg=false;
		msg='Please select from date!';
	}
	if(to_date == '' || to_date == ' '){
		flg=false;
		msg+='\nPlease select to date!';
	}
	
	if(flg){
		location.replace("../reports/rpt_master.jsp?to_date="+to_date+"&from_date="+from_date+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&mapping_id="+mapping_id);
	}else{
		alert(msg)
	}
}

$(function () {
        $('#print').click(function () {
        	var ctyp = "";
        	var cont_typ = document.forms[0].cont_typ.value;
        	var custName = document.forms[0].custName.value;
        	var from_date = document.forms[0].from_date.value;
        	var to_date = document.forms[0].to_date.value;
        	
        	if(cont_typ=="S"){
        		ctyp = "Sales";
        	}else{
        		ctyp = "Service";
			}
        	
            var pageTitle = 'Inovice of '+ctyp+" of "+custName+" From : "+from_date+" To "+to_date,
                win = window.open('', 'Print', 'width=500,height=300');
            	var code = '<html>'
            		+'<head>'
            		+'<title>'+pageTitle+'</title>'
            		+'<style type="text/css">'
            		+'th{background-color: #d9edf7;}'
            		+'table,tr,th,td{'
            		+'padding: 5px;'
            		+'border: 1px solid black;' 
            		+'border-collapse: collapse;'
            		+'}'
            		+'tr:nth-child(even){background-color: #f2f2f2}'
            		+'</style>'
            		+'</head>'	
            		+'<body>'+$('#example')[0].outerHTML
            		+'</body>'
            		+'</html>';	
            	win.document.write(code);
            		
            win.document.close();
            win.print();
            win.close();
            return false;
        });
    });
    
function fnExcel()
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById('example'); // id of table

    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
       
        //tab_text=tab_text+"</tr>";
    }

    tab_text=tab_text+"</table>";
//     tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
//     tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
//     tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params
//     tab_text = tab_text.replace(/[,'"]/g, '');
//     alert(tab_text)
    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
    {
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to Sumit.xls");
    }  
    else                 //other browser not tested on IE 11
    
    	var a = document.createElement('a');
    	var data_type = 'data:application/vnd.ms-excel';
    	a.href = data_type + ', '+ encodeURIComponent(tab_text);
    	a.download = 'Advance_Tracker.xls';
        //triggering the function
        a.click();
        
//         sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

//     return (sa);
} 
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Advacne_Tracker" id="dat" scope="page"/>  
<%
java.text.NumberFormat nf = new java.text.DecimalFormat("###0.00");
java.text.NumberFormat nf6 = new java.text.DecimalFormat("###,###,###,##0.00");
utilBean.init();
String current_date = utilBean.getGen_dt();

String time = utilBean.getTime_gen();
SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
Date date = format1.parse(current_date);

String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212

String from_dt = request.getParameter("from_date")==null?current_date:request.getParameter("from_date");
String to_dt = request.getParameter("to_date")==null?current_date:request.getParameter("to_date");
String cont_typ = request.getParameter("cont_typ")==null?"":request.getParameter("cont_typ");
String mapp_id = request.getParameter("mapping_id")==null?"0":request.getParameter("mapping_id");

// System.out.println("mapp_id---"+mapp_id);
dat.setCallFlag("fetchAdvanceTrackerDtl");
dat.setFrom_dt(from_dt);
dat.setTo_dt(to_dt);
dat.setSelMapId(mapp_id);
dat.setSysdate(current_date);
dat.init();

Vector mapping_id = dat.getMapping_id();
Vector Vcust_abbr = dat.getVcust_abbr();

Vector transDate = dat.getTransDate();
Vector remark = dat.getRemark();
Vector Vcredit = dat.getVcredit();
Vector Vdebit = dat.getVdebit();
Vector Vclosing_bal = dat.getVclosing_bal();
Vector bgColor = dat.getBgColor();

%>
	<div class="tab-content">
		<div class="tab-pane active" id="hcasreport">
		<!-- Default box -->
			<div class="box mb-0">
				<form >
				<iframe id="txtArea1" style="display:none"></iframe>
				<input type="hidden" name="modCd" value="<%=modCd%>">
				<input type="hidden" name="formId" value="<%=formId%>">
				<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
				
					<div class="box-header with-border" style="background-color:#ffe57f;">
						<div class="form-group col-md-2">
						 	 <label for="">From Date</label>
						      <div class='input-group date' id='datetimepicker'>
								<input type='text' class="form-control" id="d1" type="text"  name="from_date"  value="<%=from_dt%>" onBlur="validateDate(this);" onchange="compareDates(this);"/>
								<span class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</span>
							</div>
						 </div>
 
						 <div class="form-group col-md-2">
						 	 <label for="">To Date</label>
						     <div class='input-group date' id='datetimepicker1'>
								 <input type='text' class="form-control" id="d1" type="text" name="to_date"  value="<%=to_dt%>" onBlur="validateDate(this);" onchange="compareDates(this);fetchRpt();"/>
								 <span class="input-group-addon">
								 	<i class="fa fa-calendar"></i>
								 </span>
							  </div>
						 </div>
						<div class="form-group col-md-2">
						 	<label for="">Customer</label>	
							<select class="form-control" name="mapping_id" >
								<option value="0" selected="selected">--select--</option>
								<%for(int i = 0 ; i < mapping_id.size() ; i++){ %>
									<option value="<%=mapping_id.elementAt(i)%>"><%=Vcust_abbr.elementAt(i) %></option>
								<%} %>	
							</select>
							
							<script type="text/javascript">
								document.forms[0].mapping_id.value='<%=mapp_id%>';
							</script>
						 </div>
						
						<div class="form-group col-md-1">
						 	 <label for="">&nbsp;</label>
						 	  <div class='input-group'>
						     	<button type="button"  class="btn btn-primary"   name="viewList" value="View" onclick="fetchRpt();" >View List</button>
						     </div>
						 </div>
 
						 <div class="form-group col-md-2">
						 	 <label for="">&nbsp;</label>
						 	  <div class='input-group'>
						     	<button type="button"  class="btn btn-warning" id="btn" onclick="fnExcel();" value="export" >Export To Excel</button>
						     </div>
						 </div>
					</div>
					
					<div class="box-body table-responsive no-padding" >
						<table id="example" class="table table-bordered " >
						<thead>
							<tr><td colspan="6" class="text-center" align="center" style="font-weight: bold;" > <font color="Blue;" size="3"> Advance Tracker Report </font> </td></tr>   
							<tr class="info">
								<th class="text-center" >Sr No</th>
								<th class="text-center" >Date</th>
								<th class="text-center" >Remark</th>
								<th class="text-center" >Debit</th>
								<th class="text-center" >Credit</th>
								<th class="text-center" >Closing Balance</th>
							</tr>
						</thead>
						
						<tbody>
						<%for(int i = 0 ; i < transDate.size() ; i++){ %>
							<tr style="background-color: <%=bgColor.elementAt(i)+""%>">
								<td align="center"><%=i+1 %></td>
								<td align="center"><%=transDate.elementAt(i) %></td>
								<td><%=remark.elementAt(i) %></td>	
								<td align="right"><%=nf6.format(Double.parseDouble(Vdebit.elementAt(i)+"")) %></td>
								<td align="right"><%=nf6.format(Double.parseDouble(Vcredit.elementAt(i)+"")) %></td>
								<td align="right"><%=Vclosing_bal.elementAt(i) %></td>
							</tr>
						<%} %>	
						</tbody>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>
	
	<!-- jQuery -->
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script>
$(function () {
$('#datetimepicker').datepicker({
changeMonth: true,
changeYear: true,
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
todayHighlight: true,
orientation: "bottom auto"
});
});
$(function () {
$('#datetimepicker1').datepicker({
changeMonth: true,
changeYear: true,
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
todayHighlight: true,
orientation: "bottom auto"
});
});

</script>
</html>