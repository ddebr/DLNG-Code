<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.*"%>
<%@ page errorPage="../home/ExceptionHandler.jsp"%>
<%@ include file="../sess/Expire.jsp"%>
<link rel="stylesheet" href="../css/datepicker/bootstrap-datepicker3.css">
<link rel="stylesheet" href="../css/sort/dataTables.bootstrap.min.css">

<!-- Bootstrap JS -->
<script type="text/javascript" src="../js/fms.js"></script>
<script  type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="../js/sweetalert.min.js"></script>
<link rel="stylesheet" href="../css/tlu.css">
<!-- rtk -->

<script type="text/javascript">
function setOption(){
	if(document.getElementById("updat").checked){
		document.forms[0].option.value="UploadData";
		document.getElementById("upholidaydat").checked=false;
		document.getElementById("upfordat").checked=false;
	}
 if(document.getElementById("upholidaydat").checked){
		document.forms[0].option.value="UploadHolidayData";
		document.getElementById("updat").checked=false;
		document.getElementById("upfordat").checked=false;
	}
 if(document.getElementById("upfordat").checked){
		document.forms[0].option.value="UploadForwardPrice";
		
		document.getElementById("upholidaydat").checked=false;
		document.getElementById("updat").checked=false;
	}
}

function doSubmit(event){
	var flag = true;
	var flnm = document.forms[0].filenm.value;
	var option = document.forms[0].option.value;
	var fordt = document.forms[0].forward_date.value;
	if(flnm=="" || flnm==null|| flnm==" "){
		flag=false;
		alert('Select XLS File to Upload...!!!');
	}
	if(option=="" || option==null|| option==" "){
		flag=false;
	
		alert('Click on appropriate checkbox...!!!');
	}
	if(document.getElementById("upfordat").checked){
		
		if(fordt=="" || fordt==null|| fordt==" "){
			flag=false;
			alert('Enter/Select date for forwarding Price...!!!');
		}else{
			option=option+" "+fordt;
		}
	}
	if(flag)
	{
   			var btnid = (event.target.id)[0].toUpperCase()+(event.target.id).slice(1, 6);
   			if(btnid=="SaveBt"){
   				btnid = btnid.slice(0,4);
   			}
   			var a = confirm("Do You Want To "+option+"?")
   			if(a)
   			{	
	           	$('#excelForm').submit();
   			}
	}
	
}

</script>


</head>
<jsp:useBean class="com.hazira.hlpl.tlu.util.UtilBean" id="utilBean" scope="request"/>

<%


String user_cd = (String)session.getAttribute("user_cd");
    String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();//HA20200212
    String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();//HA20200212
    String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();//HA20200212
    String msg = request.getParameter("msg")==null?"":request.getParameter("msg");
 	String error_msg = request.getParameter("error_msg")==null?"":request.getParameter("error_msg");
 	
     
 	utilBean.init();
	/* String current_date = utilBean.getGen_dt();
	String temp_dt[] = current_date.split("/");
	String temp_mon = temp_dt[1];
	String temp_yr = temp_dt[2];
	String start_dt = "01/"+temp_mon+"/"+temp_yr; */
 %>


<div class="tab-content">
<div class="tab-pane active" id="excelmaster">
	<!-- Default box -->
<div class="box mb-0">

<form  method="post" action="../servlet/Frm_excel" id="excelForm">
<div class="box-header with-border">

<div class="row">
	<div class="col-lg-1 col-md-2 col-sm-6 col-xs-12 hidden-xs hidden-sm"></div>
</div>
<div class="box-body table-responsive no-padding">
	<div class="col-lg-1 col-md-2 col-sm-6 col-xs-12 hidden-xs hidden-sm"></div>
	

	
	<div class="col-xs-8" >
						<h2 class="sub-header" align="center" id="head">Upload Excel File</h2><br>
						<div class="table-responsive"  >
				    		<table class="table  table-bordered"  >
				    			<%if(!msg.equals("")){ %>
							    	<tr>
							    		<td colspan="5" class="text-center" style="color: blue;font-weight: bold;font-size: 13px;"><%=msg %></td>
							    	</tr>
						    	<%} %>	
						    	<%if(!error_msg.equals("")){ %>
							    	<tr>
							    		<td colspan="5" class="text-center" style="color: red;font-weight: bold;font-size: 13px;"><%=error_msg %></td>
							    	</tr>
						    	<%} %>	
				    			<tr>
				    				<th class="success">Select File Name: </th>
					    				<td colspan="3">
											<input type="file" name="filenm" size="75"> 
											
								    	</td>
							    		<td>
											<input type="checkbox" name="updata" id="updat" onclick="setOption()" size="1"> Upload Curve Data&nbsp;&nbsp;&nbsp;&nbsp;
								    		<input type="checkbox" name="upholidaydata" id="upholidaydat" size="1" onclick="setOption()"> Upload Holiday Data&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="checkbox" name="upfordata" id="upfordat" onclick="setOption()" size="1"> Upload Forward Price Data
								    	</td>
							 </tr>
							 
							 <tr>
							 <td colspan="4">
							 <label>Forward Price Date</label>
											<div class="form-group">
												<div class='input-group date' id='datetimepicker'>
													<input id="df" type="text"  name="forward_date" value="" onBlur="validateDate(this);" size="10" maxlength="10" />
														<span class="input-group-addon">
														<i class="fa fa-calendar"></i>
														</span>
														</div>
													</div>
							 </td></tr>
							 
			    			</table>
				    	</div>	
					</div>
	
	<div class="box-footer">
	<div class="row">
	<div class="col-sm-12 text-center">
	
	<button onclick="doSubmit(event);" type="button" class="btn btn-success" id="saveBtn" name="save" value="Submit" >Submit</button>
	</div>
	</div>
	</div>


<input type="hidden" name="modCd" value="<%=modCd%>">
<input type="hidden" name="formId" value="<%=formId%>">
<input type="hidden" name="subModUrl" value="<%=subModUrl%>">

<input type="hidden" name="write_permission" value="<!--%=write_permission%-->">
<input type="hidden" name="delete_permission" value="<!--%=delete_permission%-->">
<input type="hidden" name="print_permission" value="<!--%=print_permission%-->">
<input type="hidden" name="approve_permission" value="<!--%=approve_permission%-->">
<input type="hidden" name="audit_permission" value="<!--%=audit_permission%-->">
<input type="hidden" name="index_count" value="<!--%=index%-->">
<input type="hidden" name="option" value="">
<input type="hidden" name="user_cd" value="<%=user_cd%>">
</div><!-- boxheader in form -->
</form>
</div>
<!-- /.box -->
</div>
</div>

<!-- /.tab-content -->

<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../js/datepicker/bootstrap-datepicker.es.min.js"></script>
<script >
//endDate: "today",
$(function () {
$('#datetimepicker').datepicker({
format: "dd/mm/yyyy",
language: "en",
autoclose: true,
todayHighlight: true,
orientation: "bottom auto"
});
});




function resetPage(){
	swal({
		title: "Are you Sure you want to Reset?",
	    icon: "img/question.png",
	    allowOutsideClick: false,
		closeOnClickOutside: false,
	    buttons:[
	        'Cancel',
	        'Submit'
	        ],
	        }).then(function(isConfirm){        
	            if(isConfirm){
	            		$('select').prop('selectedIndex',0);
	            		window.onload = $('#saveBtn').show( function() {});
	            } 
	    });
}


</script> 
</body>
</html>