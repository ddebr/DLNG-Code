<%@page import="java.text.NumberFormat"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.seipl.hazira.dlng.amc.SendMail"%>
<html>
<title>Mail Status</title>

<script type="text/javascript">
function main_page(msg,audit_permission,approve_permission,print_permission,delete_permission,write_permission,subModUrl,formId,modCd,from_date,indx)
{		     
	location.replace("../reports/rpt_master.jsp?msg="+msg+"&write_permission="+write_permission
		     +"&delete_permission="+delete_permission+"&print_permission="+print_permission
		     +"&approve_permission="+approve_permission+"&audit_permission="+audit_permission
		     +"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&gas_date="+from_date
		     +"&gen_date="+from_date+"&indx="+indx);
} 
</script>

</head>
<jsp:useBean class="com.seipl.hazira.dlng.report.DataBean_Report_GenerationV2" id="contMgmtRpt"  scope="page"/>
<jsp:useBean class="com.seipl.hazira.dlng.amc.DataBean_Mst_Query" id="imv" scope="request"/>
<%
	NumberFormat nf = new java.text.DecimalFormat("###0.00");
	NumberFormat nf1 = new java.text.DecimalFormat("###0");
	String modCd = session.getAttribute("module_cd")==null?"":session.getAttribute("module_cd").toString();
	String formId = session.getAttribute("selFormId")==null?"":session.getAttribute("selFormId").toString();
	String subModUrl = session.getAttribute("subModUrl")==null?"":session.getAttribute("subModUrl").toString();
	String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
	String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
	String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
	String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
	
	String indx = request.getParameter("indx")==null?"100000":request.getParameter("indx");
	String delv_base= request.getParameter("delv_base")==null?"":request.getParameter("delv_base");
	String Contact_Person_Name= request.getParameter("Contact_Person_Name")==null?"":request.getParameter("Contact_Person_Name");
	String Contact_Customer_Person_City =  request.getParameter("Contact_Customer_Person_City")==null?"":request.getParameter("Contact_Customer_Person_City");
	String Contact_Customer_Person_Pin= request.getParameter("Contact_Customer_Person_Pin")==null?"":request.getParameter("Contact_Customer_Person_Pin");
	String Contact_Customer_Person_State= request.getParameter("Contact_Customer_Person_State")==null?"":request.getParameter("Contact_Customer_Person_State");
	String dateTimeSent =  request.getParameter("dateTimeSent")==null?"":request.getParameter("dateTimeSent");
	String Contact_Suppl_Name= request.getParameter("Contact_Suppl_Name")==null?"":request.getParameter("Contact_Suppl_Name");
	String Contact_Suppl_Person_City= request.getParameter("Contact_Suppl_Person_City")==null?"":request.getParameter("Contact_Suppl_Person_City");
	String Contact_Suppl_Person_Pin= request.getParameter("Contact_Suppl_Person_Pin")==null?"":request.getParameter("Contact_Suppl_Person_Pin");
	String Contact_Suppl_Person_State= request.getParameter("Contact_Suppl_Person_State")==null?"":request.getParameter("Contact_Suppl_Person_State");
	String from_date =  request.getParameter("from_date")==null?"":request.getParameter("from_date");
	String to_date= request.getParameter("to_date")==null?"":request.getParameter("to_date");
	String flsa_no= request.getParameter("flsa_no")==null?"":request.getParameter("flsa_no");
	String sign_dt =  request.getParameter("sign_dt")==null?"":request.getParameter("sign_dt");
	String contract_no= request.getParameter("contract_no")==null?"":request.getParameter("contract_no");
	String Contact_Customer_Person_Address= request.getParameter("Contact_Customer_Person_Address")==null?"":request.getParameter("Contact_Customer_Person_Address");
	String Contact_Suppl_Person_Address =  request.getParameter("Contact_Suppl_Person_Address")==null?"":request.getParameter("Contact_Suppl_Person_Address");
	
	String mapp_id =  request.getParameter("mapp_id")==null?"":request.getParameter("mapp_id");
	String plant_no =  request.getParameter("plant_no")==null?"":request.getParameter("plant_no");
	String sch_mapp_id = mapp_id+"-%-"+plant_no;
	String fontF = "Helvetica Neue";
	String mail_list_path = request.getRealPath("amc");
	mail_list_path = mail_list_path+"/";
	String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");

	imv.setSet_mail_path_list(mail_list_path);
	imv.setReq_object(request);
	imv.setCall_flag("getempnm");

	imv.setEmp_Vnm(user_cd);
	imv.init();
	
	contMgmtRpt.setCallFlag("Seller_Weekly_Nom_Rpt");
	contMgmtRpt.setFrom_date(from_date);
	contMgmtRpt.setTo_date(to_date);
	contMgmtRpt.setMappId(mapp_id);
	contMgmtRpt.setSchMappId(sch_mapp_id);
	contMgmtRpt.init();
	
	Vector rpt_sel_week_mmbtu = contMgmtRpt.getRpt_sel_week_mmbtu();
	Vector rpt_sel_week_truck_nm = contMgmtRpt.getRpt_sel_week_truck_nm();
	Vector rpt_sel_week_schedule_tm = contMgmtRpt.getRpt_sel_week_schedule_tm();
	Vector rpt_sel_week_remarks = contMgmtRpt.getRpt_sel_week_remarks(); 
	Vector rpt_sel_week_mt = contMgmtRpt.getRpt_sel_week_mt();
	Vector rpt_sel_week_customer_nm = contMgmtRpt.getRpt_sel_week_customer_nm();
	Vector rpt_sel_week_plant_nm = contMgmtRpt.getRpt_sel_week_plant_nm();
	Vector rpt_sel_week_schDate = contMgmtRpt.getRpt_sel_week_schDate();
	
	double totMMBTU = contMgmtRpt.getTotMMBTU();
	double totMT = contMgmtRpt.getTotMT();
	
	String weeklyDate = "";
	String TbackColor = "";
	int recordCount = 0;
	String msg = "";
	
	String Vemp_nm=imv.getEmp_nm();
	String email_id=imv.getEmail_id();
	String from_mail = imv.getFrom_mail();
	String host_mail = imv.getHost_mail();
	String to_mail = imv.getTo_mail();
	String cc_mail = imv.getCc_mail();
	
	boolean boolean_val;
	String emailFromAddress = from_mail;			
	String IP = session.getAttribute("ip").toString();
	String emailSubjectTxt = "Weekly Seller Nomination Report";
	
	Vector em = new Vector();
    StringTokenizer st = new StringTokenizer(to_mail, ",");
	    while (st.hasMoreElements())
    	{
        	String token = st.nextToken();
        	em.add(token);
        }
			// Add List of Email address to who email needs to be sent to
	String[] emailListTo =new String[em.size()];
		 
		for(int i=0;i<em.size();i++)
		{
			String string = (String)em.get(i);
		    emailListTo[i] = string;
		}
		
	em.clear();
	st = new StringTokenizer(cc_mail, ",");
    
		while (st.hasMoreElements())
        {
            String token = st.nextToken();
            em.add(token);
	    }
		
		// Add List of Email address to who email needs to be sent to
		String[] emailListCC =new String[em.size()];
		 
		for(int i=0;i<em.size();i++)
		{
			String string = (String)em.get(i);
			emailListCC[i] = string;
		}
		
	String USER_NAME = session.getAttribute("username").toString(); 
	String cn=request.getContextPath();
	cn=cn.substring(cn.indexOf("/")+1);
	
	String pws = "";
	String user="";	String password="";	String Query="";String General="";
	String path = request.getRealPath("/amc/SETUP.txt");
	FileInputStream fstream = new FileInputStream(path);
	DataInputStream in = new DataInputStream(fstream);
	BufferedReader br = new BufferedReader(new InputStreamReader(in));
	String strLine;
	
	while ((strLine = br.readLine()) != null) 
	{
	  if(strLine.startsWith("mail"))
	  {
		String  tmp[]=strLine.split(":");
		user=tmp[1];
	  }
	  if(strLine.startsWith("Pass"))
	  {
		String  tmp[]=strLine.split(":");
		password=tmp[1];
	  }
	  if(strLine.startsWith("Query"))
	  {
		String  tmp[]=strLine.split(":");
		Query=tmp[1];
	  }
	  if(strLine.startsWith("General"))
	  {
		String  tmp[]=strLine.split(":");
		General=tmp[1];
	  }
    }
		
	pws = password;
	SendMail smtpMailSender = new SendMail(from_mail,pws,cn);
    smtpMailSender.setHost(host_mail);
    
    
    String delv_baseTag = "";
    String delv_baseTag2 = "";
    String delv_baseTitle = "";
    String reportData = "";
    String delv_basePlantNm = "";


    if(delv_base.equals("D")) { 
    	delv_baseTag = "<th colspan='2' style'text-align:center;'>Truck Nomination</th>"+
        			   "<th rowspan='2' style'text-align:center;'>Delivery Point</th>";
        delv_baseTag2 = "<th colspan='1' style'text-align:center;'>Arrival Time</th>";
        delv_baseTitle = "Delivered Sales";
        
    } else {
    	delv_baseTag = "<th colspan='2' style'text-align:center;'>Truck Confirmation</th>";
    	delv_baseTag2 = "<th colspan='1' style'text-align:center;'>Schedule Time</th>";
    	delv_baseTitle = "Ex-Terminal Sales";
    } 
    
    List<String> list = new ArrayList<String>(); 
    
    for (int i = 0 ; i < rpt_sel_week_mmbtu.size(); i++) 
    {
    	if(!weeklyDate.equals(rpt_sel_week_schDate.elementAt(i).toString().trim()))	
    	{
    		recordCount++;
    		if(recordCount==1){
    			TbackColor = "#FFE5E5";//"#E6E4E4";
    		}else if(recordCount==2){
    			TbackColor = "#F7E4CC";//"#F2EEEE";
    		}else if(recordCount==3){
    			TbackColor = "#D2E9D2";//"#DAD7D7";
    		}else if(recordCount==4){
    			TbackColor = "#E7FFBE";
    		}else if(recordCount==5){
    			TbackColor = "#BFEAA3";
    		}else if(recordCount==6){
    			TbackColor = "#D0CFCF";
    		}else if(recordCount==7){
    			TbackColor = "#DAD1B8";
    		}
    	}
    	
    	if(!weeklyDate.equals(rpt_sel_week_schDate.elementAt(i).toString().trim())){
 			weeklyDate = rpt_sel_week_schDate.elementAt(i).toString().trim(); 
		}else{}
		
    	
    	if(delv_base.equals("D")) { 
			delv_basePlantNm = "<td  style'text-align:center;'>"+rpt_sel_week_plant_nm.elementAt(i).toString()+"</td>";
		}

	     list.add("<tr style='background-color: "+TbackColor+";'>");
		 list.add("<td style'text-align:center;'>"+rpt_sel_week_schDate.elementAt(i).toString()+"</td>");
		 list.add("<td style'text-align:center;'>"+rpt_sel_week_customer_nm.elementAt(i).toString()+"</td>");
		 list.add("<td style'text-align:center;'>"+rpt_sel_week_plant_nm.elementAt(i).toString()+"</td>");
		 list.add("<td style='text-align:right;'>"+rpt_sel_week_mmbtu.elementAt(i).toString()+"&nbsp; MMBTU</td>");
		 list.add("<td style='text-align:right;'>"+rpt_sel_week_mt.elementAt(i).toString()+"&nbsp; MT</td>");
		 list.add("<td style'text-align:center;'>"+rpt_sel_week_truck_nm.elementAt(i).toString()+"</td>");
		 list.add("<td style'text-align:center;'>"+rpt_sel_week_schedule_tm.elementAt(i).toString()+"</td>");
		 list.add(delv_basePlantNm);
		 list.add("<td style'text-align:center;'>"+rpt_sel_week_remarks.elementAt(i).toString()+"</td>");
		 list.add("</tr>");
    } 
    
    System.out.println("list : "+list);
    
    String emailMsgTxt =  "<html><head><meta name='viewport' content='width=device-width' /><meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /></head>"
    							+"<body style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; -webkit-font-smoothing: antialiased; -webkit-text-size-adjust: none; width: 100% !important; height: 100%; line-height: 1.6em; background-color: #ffffff; margin: 0;' bgcolor='#ffffff'>"
    								+"<table class='body-wrap' style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; width: 100%; background-color: #ffffff; margin: 0;' bgcolor='#ffffff'>"
    									+"<tr style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;'>"
    										+"<td style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; vertical-align: top; margin: 0;' valign='top'></td>"
    										+"<td class='container' width='900' style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; vertical-align: top; display: block !important; max-width: 900px !important; clear: both !important; margin: 0 auto;' valign='top'>"
    											+"<div class='content' style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; max-width: 900px; display: block; margin: 0 auto; padding: 20px;'>"
    												+"<table class='main' width='100%' cellpadding='0' cellspacing='0' style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px; box-sizing: border-box; border-radius: 3px; background-color: #fff; margin: 0; ' bgcolor='#fff'>"
    													+"<tr style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;'>"
    													    +"<td class='content-wrap aligncenter' style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; vertical-align: top; text-align: center; margin: 0; padding: 20px;' align='center' valign='top'>"
    													        +"<table width='100%' cellpadding='0' cellspacing='0' style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;'>"
    													        	+"<tr style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;'>"
    													        		+"<td class='content-block' style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; vertical-align: top; margin: 0; padding: 0 0 20px;padding-right: 70px;' valign='top'>"
    																	+"<h2 class='aligncenter' style='font-family: "+fontF+",Helvetica,Arial,'Lucida Grande',sans-serif; box-sizing: border-box; font-size: 24px; color: #000; line-height: 1.2em; font-weight: 400; text-align: center; margin: 40px 0 0;' align='center'><b>"+delv_baseTitle+"</b></h2>"
    													        		+"</td>"
    													        	+"</tr>"
    													        +"</table>"													        				
    													        +"<table width='100%' cellpadding='0' cellspacing='0' style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;'>"
    													        	+"<tr style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;'>"
    													        		+"<td colspan='9' class='content-block aligncenter' style='font-family: fontF,Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; vertical-align: top; text-align: center; margin: 0; padding: 0 0 20px;' align='center' valign='top'>"
    													        			+"<table class='emailFormate' style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px;'>"
    													        				+"<tr style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;'>"
    													        					+"<td colspan='3' style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; vertical-align: top; margin: 0;text-align: left; padding: 5px 0;' valign='top'>"
    													        						+"<b>To,</b><br style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'/>"
    													        						+"&nbsp;&nbsp;"+Contact_Person_Name+",&nbsp;"+Contact_Person_Name+",<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'/>"
    													        						+"&nbsp;&nbsp;"+Contact_Customer_Person_Address+",<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'/>"
    																					+"&nbsp;&nbsp;"+Contact_Customer_Person_City+"-"+Contact_Customer_Person_Pin+",<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'/>"
    																					+"&nbsp;&nbsp;"+Contact_Customer_Person_State+"<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'/>"
    																				+"</td>"
    																				+"<td colspan='3' style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;text-align: left;'>"
    																					+"<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'/>"
    																					+"<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'/><br/>"
    																					+"<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'/>"
    																				+"</td>"
    																				
    																				+"<td colspan='3' style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; vertical-align: top; margin: 0; padding: 5px 0;text-align:right;padding-left:250px;' valign='top'>"
    																					 +"<b>Date Time Sent :  "+dateTimeSent+"</b>&nbsp;<b id='hours2'></b>: <b id='min2'></b><br style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;' />"
    																					 +"<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;' />"
    																				+"</td>"
    																			+"</tr>"
    																			+"<tr style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;'>"
    																				+"<td colspan='3' style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; vertical-align: top; margin: 0;text-align: left; padding: 5px 0;' valign='top'>"
    																					+"<b>From,</b><br style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'/>"
    																					+"&nbsp;&nbsp;"+Contact_Suppl_Name+",<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'/>"
    																					+"&nbsp;&nbsp;"+Contact_Suppl_Person_Address+",<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'/>"
    																					+"&nbsp;&nbsp;"+Contact_Suppl_Person_City+"-"+Contact_Suppl_Person_Pin+",<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'/>"
    																					+"&nbsp;&nbsp;"+Contact_Suppl_Person_State+"<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'/>"
    																				+"</td>"
    																				+"<td colspan='3' style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;text-align: left;'>"
    																					+"<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'/>"
    																					+"<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'/><br/>"
    																					+"<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'/>"
    																				+"</td>"
    																				
    																				+"<td colspan='3' style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; vertical-align: top; margin: 0; padding: 5px 0;text-align:right;padding-left:250px;' valign='top'>"
    																					+"<b>Sequence No#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;</b>1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
    																					+"<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;' />"
    																					+"<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;' />"
    																					+"<b>From Date&nbsp;&nbsp;&nbsp;&nbsp;:</b>&nbsp;"+from_date
    																					+"<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;' />"
    																					+"<b>To Date&nbsp;&nbsp;&nbsp;&nbsp;:</b>&nbsp;"+to_date
    																					+"<br style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;' />"
    																				+"</td>"
    																			+"</tr>"
    																		+"</table>"
    																	+"</td>"
    																+"</tr>"
    																+"<tr>"
    																	+"<td colspan='9'><hr/></td>"
    																+"</tr>"
    																
    																+"<tr style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 16px; margin: 0;'>"
    																	+"<td colspan='9' style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 16px; vertical-align: top; margin: 0; padding: 5px 0;text-align: center;' valign='top'>"
    																		+"<b>Seller Weekly Confirmation</b>"
    																	+"</td>"
    																+"</tr>"
    																
    																+"<tr style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;'>"
    																	+"<td colspan='9' style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; vertical-align: top; margin: 0; padding: 5px 0;text-align: left;' valign='top'>"
    																		+"<b>Dear Sir/Madam,</b>"
    																	+"</td>"
    																+"</tr>"
    																
    																+"<tr style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 12px; margin: 0;'>"
    																	+"<td colspan='9' style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 12px; vertical-align: top; margin: 0; padding: 5px 0;' valign='top'>"
    																		+"<p style='font-family: "+fontF+",Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 12px;margin: 0;text-align: justify;'>"
    																		+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;As per the requirements of our Framework LNG Sales Agreement FLSA-"+flsa_no+" dated "+sign_dt+" Supply Notice "+contract_no+" dated "+sign_dt+" we notify as follows:"
    																		+"</p>"
    																	+"</td>"
    																+"</tr>"
    																+"<tr style='font-family: '"+fontF+"',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;'>"
    																	+"<td style='font-family: '"+fontF+"',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; vertical-align: top; margin: 0; padding: 5px 0;' valign='top'><br></td>"
    																+"</tr>"
    															+"</table>"
    															
    															+"<table class='table table-head-fixed text-nowrap table-bordered' width='100%' style='font-family: "+fontF+",Helvetica,Arial,sans-serif;font-size: 13px;'>"	
    																+"<thead>"
    																	+"<tr style='background-color:#d9edf7;'>"
    																		+"<th rowspan='2' style='text-align:center;'>Date</th>"
    																		+"<th rowspan='2' style='text-align:center;'>Customer</th>"
    																		+"<th rowspan='2' style='text-align:center;'>Plant Name</th>"
    																		+"<th colspan='2' style='text-align:center;'>Nomination</th>"
    																		+delv_baseTag
    																		+"<th rowspan='2' style='text-align:center;'>Seller's Comments</th>"
    																	+"</tr>"
    																	
    																	+"<tr style='background-color:#d9edf7;'>"
    																		+"<th colspan='1' style'text-align:center;'>MMBTU</th>"
    																		+"<th colspan='1' style'text-align:center;'>MT</th>"
    																		+"<th colspan='1' style'text-align:center;'>Truck No.</th>"
    																		+delv_baseTag2
    																	+"</tr>"
    																+"</thead>"
    																+"<tbody>";
    																	
    																
    																
    																
    											   String emailMsgTxt2 = "<tr>"
    														 				+"<td colspan='3' style='text-align:center;'><b>TOTAL</b></td>"
    																		+"<td style='text-align:right;'><b>"+nf1.format(totMMBTU)+"&nbsp; MMBTU</b></td>"
    																		+"<td style='text-align:right;'><b>"+nf.format(totMT)+"&nbsp; MT</b></td>"
    																		+"<td colspan='3'></td>"
    																	+"</tr>"
    																+"</tbody>"
    															
    																+"<tr style='font-family: '"+fontF+"',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; margin: 0;'>"
    																	+"<td style='font-family: '"+fontF+"',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 13px; vertical-align: top; margin: 0; padding: 5px 0;' valign='top'></td>"
    																+"</tr>"
    															+"</table>"

    														+"</td>"
    													+"</tr>"
    												+"</table>"
    											+"</div>"
    										+"</td>"
    								 	+"</tr>"
    								+"</table>"
    							+"</body>"
    						+"</html>";
    
    String formattedString = list.toString()
    	    .replace(",", "")  //remove the commas
    	    .replace("[", "")  //remove the right bracket
    	    .replace("]", "")  //remove the left bracket
    	    .trim();           //remove trailing spaces from partially initialized arrays
    
   	boolean_val = smtpMailSender.postMailReport(emailListTo, emailSubjectTxt,emailMsgTxt+formattedString+emailMsgTxt2, emailFromAddress,emailListCC,USER_NAME,IP);

   	if (boolean_val==true)
	{
    	msg = "Mail Send Successfully.";
	} 
    else
    {
    	msg = "Failed To Send Mail Please Check Your Internet Connection!";
    }
   	
	/* System.out.println("Vemp_nm :"+Vemp_nm);
	System.out.println("email_id :"+email_id);
	System.out.println("from_mail :"+from_mail);
	System.out.println("host_mail :"+host_mail);
	System.out.println("to_mail :"+to_mail);
	System.out.println("cc_mail :"+cc_mail);

	System.out.println("delv_base : "+delv_base);
	System.out.println("Contact_Person_Name : "+Contact_Person_Name);
	System.out.println("Contact_Customer_Person_City : "+Contact_Customer_Person_City);
	System.out.println("Contact_Customer_Person_Pin : "+Contact_Customer_Person_Pin);
	System.out.println("Contact_Customer_Person_State : "+Contact_Customer_Person_State);
	System.out.println("dateTimeSent : "+dateTimeSent);
	System.out.println("Contact_Suppl_Name : "+Contact_Suppl_Name);
	System.out.println("Contact_Suppl_Person_City : "+Contact_Suppl_Person_City);
	System.out.println("Contact_Suppl_Person_Pin : "+Contact_Suppl_Person_Pin);
	System.out.println("Contact_Suppl_Person_State : "+Contact_Suppl_Person_State);
	System.out.println("from_date : "+from_date);
	System.out.println("to_date : "+to_date);
	System.out.println("flsa_no : "+flsa_no);
	System.out.println("sign_dt : "+sign_dt);
	System.out.println("contract_no : "+contract_no);
	System.out.println("Contact_Customer_Person_Address : "+Contact_Customer_Person_Address);
	System.out.println("Contact_Suppl_Person_Address : "+Contact_Suppl_Person_Address); */

	System.out.println(formattedString);
	out.println(emailMsgTxt+formattedString+emailMsgTxt2);
    System.out.println(emailMsgTxt+formattedString+emailMsgTxt2);
%>
<body onload="main_page('<%=msg%>','<%=audit_permission%>','<%=approve_permission%>','<%=print_permission%>','<%=delete_permission%>','<%=write_permission%>','<%=subModUrl%>','<%=formId%>','<%=modCd%>','<%=from_date%>','<%=indx%>')"> 
<form action="">
</form>	
</body>
</html>