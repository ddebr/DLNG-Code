
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
input.larger {
        width: 15px;
        height: 15px;
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
	var subDtlFlg = "N";
	var modCd = document.forms[0].modCd.value;
	var formId = document.forms[0].formId.value;
	var subModUrl = document.forms[0].subModUrl.value;
	var chkSubTotal = document.getElementById('chkSubTotal').checked;
	if(chkSubTotal){
		subDtlFlg = "Y";
	}
	var from_date = document.forms[0].from_date.value;
	var to_date = document.forms[0].to_date.value;
	var cust_nm = document.forms[0].cust_nm.value;
	var cont_typ = document.forms[0].cont_typ.value;
	
	if(cust_nm == '' || cust_nm == ' '){
		flg=false;
		msg+='\nPlease select Customer Name!';
	}
	if(cont_typ == '' || cont_typ == ' '){
		flg=false;
		msg+='\nPlease select Contract Type!';
	}
	if(from_date == '' || from_date == ' '){
		flg=false;
		msg='Please select from date!';
	}
	if(to_date == '' || to_date == ' '){
		flg=false;
		msg+='\nPlease select to date!';
	}
	
	if(flg){
		location.replace("../reports/rpt_master.jsp?to_date="+to_date+"&from_date="+from_date+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&cust_nm="+cust_nm+"&cont_typ="+cont_typ+"&subDtlFlg="+subDtlFlg);
	}else{
		alert(msg)
	}
	
}

var tableToExcel = (function() {
	  var uri = 'data:application/vnd.ms-excel;base64,'
	    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
	    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
	    , format = function(s, c)
	    			{
	    				return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) 
	    			}
	  return function(table, name) 
	  {
		if (!table.nodeType) table = document.getElementById(table)
	    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
	    var link = document.createElement("a");
		link.href = uri + base64(format(template, ctx));
		link.download = name || 'Workbook.xls';
		link.target = '_blank';
		document.body.appendChild(link);
		link.click(); 
	  }
})()

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
    
function expToExcel(cont_typ)
{
	var cont_nm = "";
	if(cont_typ == 'S'){
		
		cont_nm = "SN";
	}else if (cont_typ == 'L'){
		
		cont_nm = "LoA";
	}
	
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById('example'); // id of table
    
    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
        //tab_text=tab_text+"</tr>";
    }

    tab_text=tab_text+"</table>";
    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
    {
       /*  txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"ActionDetailedRpt.xls"); */
       
        if (typeof Blob !== "undefined") {
        	//use blobs if we can
            tab_text = [tab_text];
            //convert to array
            var blob1 = new Blob(tab_text, { type: 'text/html' });
            window.navigator.msSaveBlob(blob1, 'DLNG.xls');
            return (true);
        } else {
            //otherwise use the iframe and save
            //requires a blank iframe on page called txtArea1
            txtArea1.document.open("text/html", "replace");
            txtArea1.document.write(tab_text);
            txtArea1.document.close();
            txtArea1.focus();
            sa = txtArea1.document.execCommand("SaveAs", true, "DLNG.xls");
        }
    }  
    else                 //other browser not tested on IE 11
       /*  sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

    return (sa); */
    
    	var str = encodeURIComponent(tab_text);
	    var uri = 'data:application/vnd.ms-excel,' + str;
// 	    var uri = 'data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,' + str;
	
	    var downloadLink = document.createElement("a");
	    downloadLink.href = uri;
	    downloadLink.download = "Invoice_Report_"+cont_nm+".xls";
	
	    document.body.appendChild(downloadLink);
	    downloadLink.click();
	    document.body.removeChild(downloadLink);
}
</script>
</head>
<jsp:useBean class="com.seipl.hazira.dlng.util.UtilBean" id="utilBean" scope="request"/>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Report_GenerationV2" id="drg" scope="page"/>  
<%
java.text.NumberFormat nf = new java.text.DecimalFormat("###0.00");
utilBean.init();
String current_date = utilBean.getGen_dt();
String temp_dt[] = current_date.split("/");
String temp_mon = temp_dt[1];
String temp_yr = temp_dt[2];
String start_dt = "01/"+temp_mon+"/"+temp_yr;

String time = utilBean.getTime_gen();
SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yy");
Date date = format1.parse(current_date);

String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212

String from_dt = request.getParameter("from_date")==null?start_dt:request.getParameter("from_date");
String to_dt = request.getParameter("to_date")==null?current_date:request.getParameter("to_date");
String cont_typ = request.getParameter("cont_typ")==null?"":request.getParameter("cont_typ");
String cust_cd = request.getParameter("cust_nm")==null?"":request.getParameter("cust_nm");
String subDtlFlg = request.getParameter("subDtlFlg")==null?"N":request.getParameter("subDtlFlg");

drg.setCallFlag("invoice_stmt");
if(!cust_cd.equals("")){
	drg.setCust_cd(cust_cd);
	drg.setCont_typ(cont_typ);
	drg.setFrom_date(from_dt);
	drg.setTo_date(to_dt);
	drg.setSubDtlFlg(subDtlFlg);
	
}

drg.init();

Vector Vcust_cd = drg.getVcust_cd();
Vector Vcust_nm = drg.getVcust_nm();
Vector Vcont_typ = drg.getVcont_typ();

Vector Vnew_inv_seq_no = drg.getVnew_inv_seq_no();
Vector Vinv_dt = drg.getVinv_dt();
Vector Vtot_qty = drg.getVtot_qty();
Vector Vgross_amt = drg.getVgross_amt();
Vector Vtax_amt = drg.getVtax_amt();
Vector Vnet_payble = drg.getVnet_payble();
Vector Vdue_dt = drg.getVdue_dt();

String custName = drg.getCustName();
String custAbbr = drg.getCust_nm();

String contracType = "";
// double convt_mmbtu_to_mt = 51.9;//HA20200403
double convt_mmbtu_to_mt= (double) session.getAttribute("convt_mmbtu_to_mt");//HA20201229
Vector all_cust = drg.getAll_cust();//Hiren_20210309
Vector Vcust_abbr = drg.getVcust_abbr();
Vector Vtcs_amt = drg.getVtcs_amt();
Vector VCont_no =  drg.getVCont_no();
Vector VDr_cr_gen_flg = drg.getVDr_cr_gen_flg();
Vector VDr_cr_title = drg.getVDr_cr_title();
Vector VDr_cr_bg = drg.getVDr_cr_bg();
Vector Vmt_qty = drg.getVmt_qty();
double total_gross = drg.getTotal_gross();
double total_tax = drg.getTotal_tax();
double total_net = drg.getTotal_net();
double total_tcs = drg.getTotal_tcs();
double total_qty = drg.getTotal_qty();
double total_mt_qty = drg.getTotal_mt_qty();
double sub_total_mt_qty = drg.getSub_total_mt_qty();
subDtlFlg = drg.getSubDtlFlg();

Vector Vqty_sign = drg.getVqty_sign();
Vector Vgross_sign = drg.getVgross_sign();
Vector Vtax_sign = drg.getVtax_sign();
Vector Vtcs_sign = drg.getVtcs_sign();
Vector Vnet_sign = drg.getVnet_sign();

Vector Vplant_name = drg.getVplant_name();
Vector serv_inv_title =drg.getServ_inv_title();

// System.out.println("VDr_cr_title****"+VDr_cr_title.size());
%>
	<div class="tab-content">
		<div class="tab-pane active" id="hcasreport">
		<!-- Default box -->
			<div class="box mb-0">
				<form >
					<div class="box-header with-border" style="background-color:#ffe57f;">
						<div class="form-group col-md-1">
						 	<label for="">Customer</label>	
							<select class="form-control" name="cust_nm" id="validationDefault22" >
								<option value="">--select--</option>
								<option  value="all" >-All-</option>
								<%for(int i = 0 ; i < Vcust_cd.size() ; i++){ %>
									<option value="<%=Vcust_cd.elementAt(i)%>"><%=Vcust_nm.elementAt(i) %></option>
								<%} %>	
							</select>
							<script type="text/javascript">
								document.forms[0].cust_nm.value='<%=cust_cd%>';
							</script>
						 </div>
						 
						 <div class="form-group col-md-2">
						 	<label for="">Contract Type</label>	
							<select class="form-control" name="cont_typ">
								<option value="">--select--</option>
								<%for(int i = 0 ; i < Vcont_typ.size() ; i++){
									String temp_cont="";
									if(Vcont_typ.elementAt(i).equals("S")){
										temp_cont = "Sales";
									}else if(Vcont_typ.elementAt(i).equals("L")){
										temp_cont = "LoA";
									}else{
										temp_cont = "Service";
									}
								%>
									<option value="<%=Vcont_typ.elementAt(i)%>"><%=temp_cont %></option>
								<%} %>	
							</select>
							
							<script type="text/javascript">
								document.forms[0].cont_typ.value='<%=cont_typ%>';
							</script>
						 </div>

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
								 <input type='text' class="form-control" id="d1" type="text" name="to_date"  value="<%=to_dt%>" onBlur="validateDate(this);" onchange="compareDates(this);"/>
								 <span class="input-group-addon">
								 	<i class="fa fa-calendar"></i>
								 </span>
							  </div>
						 </div>
						 
						 <div class="form-group col-md-1">
						 	 <label for="">Sub Total</label>
						 	 <div class='input-group'>
						 	 	<input type="checkbox" class="larger" name = "chkSubTotal" id="chkSubTotal"
						 	 	<%if(subDtlFlg.equals("Y")) {%>
						 	 		checked="checked"
						 	 	<%} %>
						 	 	 >
						 	 </div>
						 </div>
						
						<div class="form-group col-md-1">
						 	 <label for="">&nbsp;</label>
						 	  <div class='input-group' >
						     	<button type="button"  class="btn btn-primary"   name="viewList" value="View" onclick="fetchRpt();" >View List</button>
						     </div>
						 </div>
 
						 <div class="form-group col-md-2">
						 	 <label for="">&nbsp;</label>
						 	  <div class='input-group'>
						 	    <%if(cont_typ.equals("S")){%>
						 	    	<%contracType = "Sales";%>
						 	    <%}else{%>
						 	    	<%contracType = "Service";%>
						 	    <%}%>
						 	    
						     	<button type="button"  class="btn btn-warning" id="btn" onclick="expToExcel('<%=cont_typ %>');" value="export" >Export To Excel</button>
						     </div>
						 </div>
						 
						 <div class="form-group col-md-1">
						 	 <label for="">&nbsp;</label>
						 	  <div class='input-group'>
						     	<button type="button" class="btn btn-success" id="print">Print</button>
						     	<input type="hidden" name="custName" value="<%=custName%>">
						     	<input type="hidden" name="custAbbr" value="<%=custAbbr%>">
						     </div>
						 </div>
					</div>
			
				<div class="box-body table-responsive no-padding">
					<table id="example" class="table table-bordered">
						<thead>
							<tr><td colspan="13" align="center" style="font-size: 13px;font-weight: bold;"> <font color="Blue;"><%=all_cust%></font>  Invoice Statement Report </td></tr>   
							<tr class="info">
								<th class="text-center" rowspan="2">Sr No</th>
								<th class="text-center" rowspan="2">Customer Abbr.</th>
								<th class="text-center" rowspan="2">Plant Name.</th>
								<th class="text-center" rowspan="2">Contract Type/No</th>
								<th class="text-center" rowspan="2">Invoice No</th>
								<th class="text-center" rowspan="2"> Invoice Date</th>
								<th class="text-center" colspan="2">Quantity</th>
								<th class="text-center" rowspan="2">Gross Amount (INR)</th>
								<th class="text-center" rowspan="2">Tax Amount (INR)</th>
								<th class="text-center" rowspan="2">TCS Amount(INR)</th>
								<th class="text-center" rowspan="2">Net Payable (INR) </th>
								<th class="text-center" rowspan="2">Due Date</th>
							</tr>
							
							<tr class="info">
								<%if(cont_typ.equalsIgnoreCase("V")) {%>
									<th  class="text-center">Value</th>
									<th  class="text-center">Calculation Base</th>
								<%}else{ %>
									<th  class="text-center">MMBTU</th>
									<th  class="text-center" width="5%">MT</th>
								<%} %>
							</tr>
						</thead>
						<tbody>
							<%
							double total_mt = 0;
							for (int i = 0 ; i < Vnew_inv_seq_no.size() ; i++) {
								total_mt+= Double.parseDouble(Vmt_qty.elementAt(i)+"");
							%>
							<tr class="text-right" 
								title="<%=VDr_cr_title.elementAt(i)%>" 
								<%if(!VDr_cr_bg.elementAt(i).equals("")){ %>
									style="background-color: <%=VDr_cr_bg.elementAt(i)%>"
								<%} %>
								
								<%if(!VDr_cr_title.elementAt(i).equals("")){ %>
									style="background-color: #dbf1d0;"
								<%} %>
								<%if(Vnew_inv_seq_no.elementAt(i).equals("")) {%>
								 	style="font-weight: bold;"
								<%} %>
								>
							
							<%if(Vnew_inv_seq_no.elementAt(i).equals("")) {%>
								<td class="text-center" colspan="6" style="font: bold;">Sub Total</td>
							<%}else{ %>
								<td class="text-center" ><%=i+1 %></td>
								<td class="text-center"><%=Vcust_abbr.elementAt(i) %></td>
								<td class="text-left"><%=Vplant_name.elementAt(i) %></td>
								<td class="text-center" >
									<%if(cont_typ.equals("S")){ %>
										SN - <%=VCont_no.elementAt(i) %>
									<%}else if(cont_typ.equals("L")){ %>
										LoA - <%=VCont_no.elementAt(i) %> 
									<%}else if(cont_typ.equals("V")) { %>
										<%=VCont_no.elementAt(i) %>
									<%} %>
								</td>
								<td class="text-center" width="10%"><%=Vnew_inv_seq_no.elementAt(i) %></td>
								<td class="text-center" align="center"><%=Vinv_dt.elementAt(i) %></td>
							<%} %>
							<%if(!cont_typ.equals("V")){ %>
								<%if(Double.parseDouble(Vmt_qty.elementAt(i)+"") > 0) {%>
									<td ><%=Vqty_sign.elementAt(i) %> <%=Vtot_qty.elementAt(i) %></td>
									<td ><%=Vqty_sign.elementAt(i) %> <%=nf.format(Double.parseDouble(Vmt_qty.elementAt(i)+""))%></td>
								<%}else{ %>
									<td ><%=Vtot_qty.elementAt(i) %></td>
									<td ><%=nf.format(Double.parseDouble(Vmt_qty.elementAt(i)+""))%></td>
								<%} %>	
							<%}else{ %>
								<td ><%=Vqty_sign.elementAt(i) %> <%=Vtot_qty.elementAt(i) %></td>
								<td class="text-center" ><%=serv_inv_title.elementAt(i) %></td>
							<%} %>
								<td><%=Vgross_sign.elementAt(i) %> <%=nf.format(Double.parseDouble(Vgross_amt.elementAt(i)+"")) %></td>
								<td><%=Vtax_sign.elementAt(i) %> <%=nf.format(Double.parseDouble(Vtax_amt.elementAt(i)+"")) %></td>
								
								<%if(Double.parseDouble(Vtcs_amt.elementAt(i)+"") > 0){ %>
									<td><%=Vtcs_sign.elementAt(i) %> <%=nf.format(Double.parseDouble(Vtcs_amt.elementAt(i)+"")) %></td>
								<%} else{%>
									<td><%=nf.format(Double.parseDouble(Vtcs_amt.elementAt(i)+"")) %></td>
								<%} %>
								
								<td><%=Vnet_sign.elementAt(i) %> <%=nf.format(Double.parseDouble(Vnet_payble.elementAt(i)+"")) %></td>
								<td class="text-center" align="center"><%=Vdue_dt.elementAt(i) %></td>
							</tr>
							<%} %>
							
							<%if(Vnew_inv_seq_no.size()==0) {  %>
								<tr >
									<th class="text-center"  style="color: red;" colspan="12">No record found for the selected customer !</th>
								</tr>
							<%}else{%>
								<tr >
									<th class="text-center" colspan="6"> Grand TOTAL </th>
									<th class="text-right" colspan="1"> <%=nf.format(total_qty) %></th>
									<th class="text-right" colspan="1"> <%=nf.format(total_mt_qty) %></th>
									<th class="text-right" colspan="1"> <%=nf.format(total_gross) %></th>
									<th class="text-right" colspan="1"> <%=nf.format(total_tax) %></th>
									<th class="text-right" colspan="1"> <%=nf.format(total_tcs) %></th>
									<th class="text-right" colspan="1"> <%=nf.format(total_net) %></th>
									<th ></th>
								</tr> 
							<%} %>
						</tbody>
					</table>
				</div>	
				<input type="hidden" name="modCd" value="<%=modCd%>">
				<input type="hidden" name="formId" value="<%=formId%>">
				<input type="hidden" name="subModUrl" value="<%=subModUrl%>">
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