package com.seipl.hazira.dlng.contract_mgmt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.escapeSingleQuotes;

@WebServlet("/servlet/Frm_MgmtV2")
public class Frm_MgmtV2 extends HttpServlet {

private static final long serialVersionUID = 1L;

public static Connection dbcon;
	
	public escapeSingleQuotes obj = new escapeSingleQuotes();
	
	public String servletName = "Frm_Mgmt";
	public String methodName = "";
	public String option = "";
	public String url = "";
	public String form_name = "";
	public String msg = "",error_msg="";
	public String msg1="";
	int count = 0;
	
	NumberFormat nf = new DecimalFormat("###########0.00");
	
	private static String queryString = null;
	private static String queryString1 = null;
	private static Statement stmt = null;
	private static Statement stmt1 = null;
	private static Statement stmt2 = null;
	private static Statement stmt3 = null;
	private static Statement stmt4 = null;
	private static Statement stmt5 = null;
	private ResultSet rset = null;
	private ResultSet rset1 = null;
	private ResultSet rset2 = null;
	private ResultSet rset3 = null;
	private ResultSet rset4 = null;
	
	public String write_permission = "N";
	public String delete_permission = "N";
	public String print_permission = "N";
	public String approve_permission = "N";
	public String audit_permission = "N";
	
	public String form_id = "0";
	public String form_nm = "";
	private String modCd = "";
	private String formId = "";
	private String subModUrl = "";
	private String modUrl = "";
	
	@SuppressWarnings("unused")
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		try
		{
			Context Context = new InitialContext();
			if(Context == null) 
			{
				throw new Exception("Boom - No Context");
			}
			
			Context envContext  = (Context)Context.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup(RuntimeConf.security_database);
			
			if(ds != null)
			{
				dbcon = ds.getConnection();
			}
			else
			{
				//System.out.println("Data Source Not Found - Error In "+servletName+" Servlet !!!");
			}
			
			if(dbcon != null)
			{
				dbcon.setAutoCommit(false);
				stmt = dbcon.createStatement(); 
				stmt1 = dbcon.createStatement();
				stmt2 = dbcon.createStatement(); 
				stmt3 = dbcon.createStatement();
				stmt4 = dbcon.createStatement();
				stmt5 = dbcon.createStatement();
			}
			
			option=req.getParameter("option")==null?"":req.getParameter("option");
			
			write_permission = req.getParameter("write_permission")==null?"N":req.getParameter("write_permission");
			delete_permission = req.getParameter("delete_permission")==null?"N":req.getParameter("delete_permission");
			print_permission = req.getParameter("print_permission")==null?"N":req.getParameter("print_permission");
			approve_permission = req.getParameter("approve_permission")==null?"N":req.getParameter("approve_permission");
			audit_permission = req.getParameter("audit_permission")==null?"N":req.getParameter("audit_permission");
			
			form_id = req.getParameter("form_id")==null?"0":req.getParameter("form_id");
			form_nm = req.getParameter("form_nm")==null?"":req.getParameter("form_nm");
			modCd = req.getParameter("modCd")==null?"":req.getParameter("modCd");
			formId = req.getParameter("formId")==null?"":req.getParameter("formId");
			subModUrl = req.getParameter("subModUrl")==null?"":req.getParameter("subModUrl");
			modUrl = req.getParameter("modUrl")==null?"":req.getParameter("modUrl");
//			System.out.println("In Frm_MgmtV2 ");
			
			if(option.equalsIgnoreCase("submitCustomerNom"))
			{
				submitCustomerNom(req,res); 
			} else if(option.equalsIgnoreCase("submitCustomerSch")) {
				submitCustomerSch(req,res);
			} else if(option.equalsIgnoreCase("submitTruckLoad")) {
				submitTruckLoad(req,res);
			}else if(option.equalsIgnoreCase("submitCustomerWeeklyNom")) {
				submitCustomerWeeklyNom(req,res);//SUJIT
			}else if(option.equalsIgnoreCase("submitCustomerWeeklySch")) {
				submitCustomerWeeklySch(req,res); //SUJIT
			} else if(option.equalsIgnoreCase("submitAutoLoadTruckData")) {
				submitAutoLoadTruckData(req,res);
			}
			else if(option.equalsIgnoreCase("SELLER_CUSTOMER"))
			{
				submitDailySellerCustomer(req,res); //Defined By Achal On 12th Oct., 2010 ...
			}else if(option.equalsIgnoreCase("SELLER_TRANSPORTER"))
			{
				submitDailySellerTransporter(req); //Defined By Achal On 12th Oct., 2010 ...
			}
			
		}		   
		catch(Exception e)
		{
			e.printStackTrace();
			url="../home/ExceptionHandler.jsp?excp="+e.getMessage()+"&modulename=contract_mgmt&formname="+form_name;
		}
		finally
		{
			if(rset != null)
			{
				try
				{
					rset.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(rset1 != null)
			{
				try
				{
					rset1.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(rset2 != null)
			{
				try
				{
					rset2.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(rset3 != null)
			{
				try
				{
					rset3.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(rset4 != null)
			{
				try
				{
					rset4.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(stmt != null)
			{
				try
				{
					stmt.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				} 
			}
			if(stmt1 != null)
			{
				try
				{
					stmt1.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(stmt2 != null)
			{
				try
				{
					stmt2.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(stmt3 != null)
			{
				try
				{
					stmt3.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(stmt4 != null)
			{
				try
				{
					stmt4.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(stmt5 != null)
			{
				try
				{
					stmt5.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(dbcon != null)
			{
				try
				{
					dbcon.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		//HA20181221 res.sendRedirect(url);
//		System.out.println("url***"+url);
		//HA20181221 res.sendRedirect(url);
		System.out.println("url***"+url);
		if(!url.equalsIgnoreCase("")) {
			res.sendRedirect(url);
		}
		
		/*
		 * RequestDispatcher rd=req.getRequestDispatcher(url); try { rd.forward(req,
		 * res); } catch (ServletException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
	}
	
	private void submitDailySellerTransporter(HttpServletRequest request) {
		
		String gas_date = request.getParameter("gas_date")==null?"":request.getParameter("gas_date");
		String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
		String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
		String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
		String modUrl = request.getParameter("modUrl")==null?"":request.getParameter("modUrl");
		String selIndx = request.getParameter("selIndx")==null?"":request.getParameter("selIndx");
		
		String trans_cd[] = request.getParameterValues("trans_cd");
		String remarks[] = request.getParameterValues("remarks");	
		
		try {
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
						
			queryString = "select GAS_DT from DLNG_SELLER_DAILYNOM_REMARK " +
			  "where GAS_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY')  ";
			
			
			//System.out.println("Query For Checking Existance Of gen_dt,gas_dt,customer_cd,transporter_cd = "+queryString);
			rset1 = stmt1.executeQuery(queryString);
			if(rset1.next())
			{
				queryString1 = "delete from DLNG_SELLER_DAILYNOM_REMARK where " +
						"GAS_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') ";
				//System.out.println("Query For Deleting Gas Date related Record = "+queryString1);
				stmt.executeUpdate(queryString1);
			}
			
			for(int i = 0 ; i < trans_cd.length; i++ ) {
				
				if(i == Integer.parseInt(selIndx+"")) {
					int cnt = 0;
					
					String selCnt = "select count(*) from DLNG_TRANS_DAILYNOM_REMARK"
							+ " where TRANS_CD = '"+trans_cd[i]+"'"
							+ " and GAS_DATE = to_date('"+gas_date+"','dd/mm/yyyy')";
					rset = stmt.executeQuery(selCnt);
					if(rset.next()) {
						cnt = rset.getInt(1);
					}
					
					if(cnt == 0) {
						
						String insSql = "insert into DLNG_TRANS_DAILYNOM_REMARK (ENTER_BY,ENTER_DT,FLAG,GAS_DATE,REMARKS,TRANS_CD)"
								+ " values ('"+user_cd+"',sysdate,'Y',to_date('"+gas_date+"','dd/mm/yyyy'),'"+remarks[i]+"','"+trans_cd[i]+"') ";
						
						stmt.executeUpdate(insSql);
						msg = "Seller Nomination's Comment for Transporter Successfully Inserted For GAS DAY: "+gas_date+" !!!";
						dbcon.commit();
						
					}else {
						
						String insSql = "UPDATE DLNG_TRANS_DAILYNOM_REMARK Set "
								+ " UPDATE_BY = '"+user_cd+"',REMARKS = '"+remarks[i]+"',UPDATE_DATE = SYSDATE"
								+ " WHERE TRANS_CD = '"+trans_cd[i]+"' and GAS_DATE = to_date('"+gas_date+"','dd/mm/yyyy')";
						
						stmt.executeUpdate(insSql);
						msg = "Seller Nomination's Comment for Transporter Successfully Updated For GAS DAY: "+gas_date+" !!!";
						dbcon.commit();
					}
				}
			}
			url = modUrl+"?msg="+msg+"&gas_date="+gas_date+"&modUrl="+modUrl+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			try {
				dbcon.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			msg = "Seller Nomination's Comment for Customer Failed !!!";
			e.printStackTrace();
			url = modUrl+"?msg="+msg+"&gas_date="+gas_date+"&modUrl="+modUrl+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
		}
		
	}

	private void submitDailySellerCustomer(HttpServletRequest request, HttpServletResponse res)throws SQLException,IOException  {

		methodName = "submitDailySellerCustomer()";
		form_name = "rpt_seller_nom_select.jsp";
		
		String gas_date = request.getParameter("gas_date")==null?"":request.getParameter("gas_date");
		String gen_date = request.getParameter("gen_date")==null?"":request.getParameter("gen_date");
		
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
						
			String [] remarks = request.getParameterValues("remarks");			
			String [] customer_cd = request.getParameterValues("customer_cd");
			String [] fgsa_no = request.getParameterValues("fgsa_no");
			String [] sn_no = request.getParameterValues("sn_no");
			String [] cont_type = request.getParameterValues("cont_type");
			String [] plant_seq_no = request.getParameterValues("plant_seq_no");
			String [] MAPPING_ID = request.getParameterValues("mapping_id");
			
			queryString = "select GAS_DT from DLNG_SELLER_DAILYNOM_REMARK " +
			  "where GAS_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY')  ";
			
			
			//System.out.println("Query For Checking Existance Of gen_dt,gas_dt,customer_cd,transporter_cd = "+queryString);
			rset1 = stmt1.executeQuery(queryString);
			if(rset1.next())
			{
				queryString1 = "delete from DLNG_SELLER_DAILYNOM_REMARK where " +
						"GAS_DT=TO_DATE('"+gas_date+"','DD/MM/YYYY') ";
				//System.out.println("Query For Deleting Gas Date related Record = "+queryString1);
				stmt.executeUpdate(queryString1);
			}
			
			for(int i=0;i<customer_cd.length;i++)
			{
				remarks[i] = remarks[i]==null?"":obj.replaceSingleQuotes(remarks[i]);				
				
				if(cont_type[i].equalsIgnoreCase("T") || cont_type[i].equalsIgnoreCase("C"))
				{
					queryString = "INSERT INTO DLNG_SELLER_DAILYNOM_REMARK (GAS_DT,CUST_CD,CONTRACT_TYPE,REMARKS,emp_cd,ENT_DT,flag,FLSA_NO,SN_NO,PLANT_SEQ_NO,MAPPING_ID) " +
					  "VALUES (TO_DATE('"+gas_date+"','DD/MM/YYYY'),"+customer_cd[i]+",'"+cont_type[i]+"'," +
					  "'"+remarks[i].trim()+"',"+user_cd+",sysdate,'Y','"+fgsa_no[i]+"','"+sn_no[i]+"','"+plant_seq_no[i]+"','"+MAPPING_ID[i]+"')";					
	
				}
				else
				{
					queryString = "INSERT INTO DLNG_SELLER_DAILYNOM_REMARK (GAS_DT,CUST_CD,CONTRACT_TYPE,REMARKS,emp_cd,ENT_DT,flag,FLSA_NO,SN_NO,PLANT_SEQ_NO) " +
					  "VALUES (TO_DATE('"+gas_date+"','DD/MM/YYYY'),"+customer_cd[i]+",'"+cont_type[i]+"'," +
					  "'"+remarks[i].trim()+"',"+user_cd+",sysdate,'Y','"+fgsa_no[i]+"','"+sn_no[i]+"','"+plant_seq_no[i]+"')";					
	
				}
				
				
				
				//System.out.println("Query For Inserting Each Daily Seller Customer Record = "+queryString);
				stmt.executeUpdate(queryString);
				msg = "Seller Nomination's Comment for Customer Successfully Inserted For GAS DAY: "+gas_date+" !!!";
							
			}						
			url = "../reports/rpt_master.jsp?msg="+msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			msg = "Seller Nomination's Comment for Customer Failed !!!";
			e.printStackTrace();
			//System.out.println("Exception in "+methodName+" Method of "+servletName+" Servlet ...\n"+e.getMessage());
			url="../contract_mgmt/rpt_seller_nom_select.jsp?msg="+msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;	
		}
	
		
	}

	private void submitAutoLoadTruckData(HttpServletRequest request, HttpServletResponse response) 
	{
		methodName = "submitAutoLoadTruckData()";
		form_name = "frm_tlu_data_cap_json.jsp";
		DecimalFormat df = new DecimalFormat("###");
	
//		System.out.println("methodName "+methodName);
		try {
				HttpSession session = request.getSession();
				String [] nomQtyDate= request.getParameterValues("nomQtyDate");
				String [] nomQtyTime= request.getParameterValues("nomQtyTime");
				String [] nomQtyValue= request.getParameterValues("nomQtyValue");
				
//				System.out.println("nomQtyDate : "+Arrays.toString(nomQtyDate));
//				System.out.println("nomQtyTime : "+Arrays.toString(nomQtyTime));
//				System.out.println("nomQtyValue : "+Arrays.toString(nomQtyValue));
				
				/*String nomQtyDate = request.getParameter("nomQtyDate")==null?"":request.getParameter("nomQtyDate");
				String nomQtyTime = request.getParameter("nomQtyTime")==null?"":request.getParameter("nomQtyTime");
				String nomQtyValue = request.getParameter("nomQtyValue")==null?"":request.getParameter("nomQtyValue");
				
				System.out.println("nomQtyDate : "+nomQtyDate);
				System.out.println("nomQtyTime : "+nomQtyTime);
				System.out.println("nomQtyValue : "+nomQtyValue);*/
				
				
				
				
				msg = "Customer TLU Data Capturing Successfully Done  !!!";
				url = "../master/frm_mst_truckLoading.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&btnClickid=savebtn";
				dbcon.commit();
				
				try
				{
					new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
				}
				catch(Exception infoLogger)
				{
					infoLogger.printStackTrace();
				}
		}
		catch(Exception e)
		{
			msg = "Customer TLU Data Capturing Failed !!!";
			e.printStackTrace();
			url = "../master/frm_mst_truckLoading.jsp?msg="+msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}		
		
	}

	private void submitCustomerWeeklySch(HttpServletRequest request, HttpServletResponse responseH) throws SQLException,IOException 
	{
		methodName = "submitCustomerWeeklySch()";
		form_name = "frm_cust_weekly_sch.jsp";
		DecimalFormat df = new DecimalFormat("###");
		String gas_date = request.getParameter("gas_date")==null?"":request.getParameter("gas_date");
		String gen_date = request.getParameter("gen_date")==null?"":request.getParameter("gen_date");
		String gcv = request.getParameter("gcv")==null?"":request.getParameter("gcv");
		String ncv = request.getParameter("ncv")==null?"":request.getParameter("ncv");
		String subType = request.getParameter("subType")==null?"":request.getParameter("subType");//HA20200205
		String modUrl = request.getParameter("modUrl")==null?"":request.getParameter("modUrl");//Hiren_20210607
		
		try {
			
			HttpSession session = request.getSession();
//			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			
			String [] chk_flag = request.getParameterValues("chk_flag");
			String [] buyer_cd = request.getParameterValues("buyer_cd");
			String [] buyer_plant_seq_no = request.getParameterValues("buyer_plant_seq_no");
			String [] qty_mmbtu = request.getParameterValues("qty_mmbtu");
			String [] qty_scm = request.getParameterValues("qty_scm");
			String [] buyer_mapping_id = request.getParameterValues("buyer_mapping_id");
			String [] buyer_sn_no = request.getParameterValues("buyer_sn_no");
			
			
			String [] chekboxs= request.getParameterValues("chekboxs");
			String [] scheduleCheckFlag= request.getParameterValues("checkFlag");
			String [] trucknm= request.getParameterValues("weeklyTruckNm");
			String [] schedule_mmbtu= request.getParameterValues("weeklyTruckSchVol");
			String [] schedule_ton= request.getParameterValues("weeklyTruckNm");
			String [] weeklySchdate= request.getParameterValues("weeklySchdate");
			String [] schedule_time= request.getParameterValues("weeklyTruckASchTime");
			String [] remark= request.getParameterValues("weeklyTruckRemark");
			String Vtrans_cd [] = request.getParameterValues("VWeekly_Trans_cd"); //Hiren_20200329
			String next_avail_days [] = request.getParameterValues("next_avail_days"); //Hiren_20201103
			
			String selectCustChkVal = request.getParameter("selectCustChkVal")==null?"":request.getParameter("selectCustChkVal");
			String schId = "0-0-0-0-0-0-0";
			
			double multiplying_factor = 0.252*1000000; 
			int deviding_factor = 1;
			double tot_truck_mmbtus =0;
			double tot_truck_ene = 0;
			
			
			String custCd  = request.getParameter("custCd")==null?"":request.getParameter("custCd");
			
			String MappId = "";
			String plan_seq_no = "";
			String sn_no = "";
			String contract_type = "";
			String cust_arr [];
			String cust_cd="0";
			
			if(custCd.contains("----")) {
				
				String tempArr[] = custCd.split("----");
				MappId = tempArr[0];
				plan_seq_no = tempArr[2];
				sn_no = tempArr[3];
				schId = MappId+"-"+sn_no+"-"+plan_seq_no;    //map_id-sn_no-plant_seq_no
				contract_type = tempArr[4].substring(0,1);
				cust_arr = MappId.split("-");
				cust_cd = cust_arr[0];
			}
			
//			System.out.println("subType***"+subType);
			if(!subType.equals("noTruck")) {
				for(int i=0;i<scheduleCheckFlag.length;i++) {
					
					if(scheduleCheckFlag[i].equals("Y")) {
						tot_truck_mmbtus+=Double.parseDouble(schedule_mmbtu[i]);
						tot_truck_ene+=Math.round(Double.parseDouble(schedule_mmbtu[i])* multiplying_factor / Double.parseDouble(gcv)*deviding_factor);
					}
				}
			}
			df.format(tot_truck_mmbtus);
			df.format(tot_truck_ene);
			
			int cnt = 0 ;
			String prevDt="";
			double tot_mmbtu_vol=0;
			String prev=weeklySchdate[0];
			Vector nomVolList = new Vector();
			Vector nomDtList = new Vector();
			
			for (int i = 0; i <= weeklySchdate.length-1; i++) {
				
				 if(prev.equals(weeklySchdate[i])) {
					 tot_mmbtu_vol+= Double.parseDouble(schedule_mmbtu[i]);
					 
					 if(i ==  weeklySchdate.length-1) {
						 nomVolList.add(tot_mmbtu_vol);
						 nomDtList.add(prev);
					 }
				 }else {
					 
//						 System.out.println("weeklySchdate[i]***"+weeklySchdate[i]);
					 nomVolList.add(tot_mmbtu_vol);
					 nomDtList.add(prev);
					 tot_mmbtu_vol=0;
					 tot_mmbtu_vol=Double.parseDouble(schedule_mmbtu[i]);
					 
					 if(i ==  weeklySchdate.length-1) {
						 nomVolList.add(tot_mmbtu_vol);
						 nomDtList.add(weeklySchdate[i]);
					 }
				 }
				 prev = weeklySchdate[i];
			}
			
//			System.out.println("nomVolList***"+nomVolList);
//			System.out.println("nomDtList***"+nomDtList);
		 
			 
			double tot_sch_mmbtu=0;
			Vector Vtot_sch_mmbtu = new Vector();
			Set date = new HashSet<>();
			
			for(int i=0;i<scheduleCheckFlag.length;i++)
			{
				int rev_no = 0;
				if(scheduleCheckFlag[i].equalsIgnoreCase("Y"))
				{	
					if(subType.equals("noTruck")) {
						
						tot_truck_mmbtus = Double.parseDouble(qty_mmbtu[i].toString());
						tot_truck_ene = Double.parseDouble(qty_scm[i].toString());
					}else{
						
							if(scheduleCheckFlag[i].equalsIgnoreCase("Y")) 
							{
								queryString = "SELECT NVL(MAX(REV_NO),'0') FROM DLNG_WEEKLY_SCH WHERE "
										+ " MAPPING_ID='"+MappId+"' AND "
										+ " SCH_ID='"+schId+"' AND SCH_DT = TO_DATE('"+weeklySchdate[i]+"','DD/MM/YYYY') AND "
										+ "PARTY_CD='"+cust_cd+"' ";
//								System.out.println("queryString------>"+queryString);
								rset = stmt.executeQuery(queryString);
								if(rset.next())
								{
									rev_no = Integer.parseInt(rset.getString(1));
									rev_no = rev_no + 1;
									
								}
								else
								{
									rev_no = rev_no + 1;
								}
								
								double truck_ene = Math.round(Double.parseDouble(schedule_mmbtu[i]) * multiplying_factor / Double.parseDouble(gcv) * deviding_factor);
								df.format(truck_ene);
								
								if(!schedule_mmbtu[i].equals("0")) //SUJIT12SEP2020
								{
									queryString1 ="INSERT INTO DLNG_WEEKLY_TRUCK_SCH_DTL (MAPPING_ID,SCH_ID,SCH_DT,REV_NO,"
											  + "SCH_TIME,TRUCK_NM,TRUCK_VOL,UNIT_VOL,"
											  + "TRUCK_ENE,UNIT_ENE,REMARKS,TRANS_CD,CONTRACT_TYPE,NEXT_AVAIL_DAYS)VALUES('"+MappId+"',"
											  + " '"+schId+"',"
											  + " TO_DATE('"+weeklySchdate[i]+"','DD/MM/YYYY'),"
											  + " '"+rev_no+"',"
											  + " '"+schedule_time[i]+"',"
											  + " '"+trucknm[i]+"',"
											  + " '"+schedule_mmbtu[i]+"','MMBTU',"
											  + " '"+truck_ene+"', 'SCM', "
											  + " '"+remark[i]+"','"+Vtrans_cd[i]+"','"+contract_type+"','"+next_avail_days[i]+"')";
//									System.out.println("queryString1 : "+ queryString1);
									//System.out.println("Vtrans_cd[j]*****"+Vtrans_cd[j]);
									stmt5.executeUpdate(queryString1);
									
									double sumOfTruckEne = Math.round(Double.parseDouble(schedule_mmbtu[i]) * multiplying_factor / Double.parseDouble(gcv) * deviding_factor);
									
									//count++;
									
									date.add(weeklySchdate[i]);	
								}
								else
								{
								}
								
							}
					}
				}
			}// for 
			
			for (int i = 0; i < nomDtList.size(); i++) {
				
				if(date.contains(nomDtList.elementAt(i))) {
					if(Double.parseDouble(nomVolList.elementAt(i)+"") > 0) {
						double sumOfTruckEne = Math.round(Double.parseDouble(nomVolList.elementAt(i)+"") * multiplying_factor / Double.parseDouble(gcv) * deviding_factor);
						int rev_no = 0;
						queryString = "SELECT NVL(MAX(REV_NO),'0') FROM DLNG_WEEKLY_SCH WHERE MAPPING_ID='"+MappId+"' AND "
								+ "SCH_ID='"+schId+"' AND SCH_DT = TO_DATE('"+nomDtList.elementAt(i)+"','DD/MM/YYYY') ";
//								+ "PARTY_CD='"++"' ";
//						System.out.println("queryString_revNo : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							rev_no = Integer.parseInt(rset.getString(1));
							rev_no = rev_no + 1;
							
						}
						else
						{
							rev_no = rev_no + 1;
						}
//						System.out.println("rev_no*********"+rev_no);
						
						queryString = "INSERT INTO DLNG_WEEKLY_SCH (MAPPING_ID,"
								  +"SCH_ID,"
								  +"REV_NO,"
								  +"PARTY_CD,"
							      +"SUP_TRN_CD,"
							      +"SCH_DT,"
							      +"GEN_DT,"
							      +"NHV,"
							      +"GHV,"
							      +"DAY_VOL,"
							      +"UNIT_VOL,"
							      +"TOT_ENE,"
							      +"UNIT_ENE,CONTRACT_TYPE) "
							      +"VALUES ('"+MappId+"',"
							      +"'"+schId+"',"
							      +"'"+rev_no+"',"
							      +"'"+cust_cd+"',"
							      + "'0',"
							      +"TO_DATE('"+nomDtList.elementAt(i)+"','DD/MM/YYYY'),"
							      +"TO_DATE('"+gen_date+"','DD/MM/YYYY'),"
							      +"'"+ncv+"',"
							      +"'"+gcv+"',"
							      +"'"+nomVolList.elementAt(i)+"','MMBTU',"
							      +"'"+sumOfTruckEne+"','SCM','"+contract_type+"') ";
//						System.out.println("--Insert Query--"+queryString);
						stmt.executeUpdate(queryString);
					}
				 }
				}
			
			
			msg = "Customer Weekly Seller Nomination Successfully Done For The Selected Buyers For Weekly Schedule "+gas_date+"  To  "+weeklySchdate[weeklySchdate.length-1]+ ".  !!!";
			url = modUrl+"?msg="+msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&gcv="+gcv+"&ncv="+ncv+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&selectChkVal="+selectCustChkVal+"&gas_date="+gas_date+"&schId="+schId;
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			dbcon.rollback();
			msg = "Customer Scheduling Failed !!!";
			e.printStackTrace();
			//HA20181221 url="../hcas/frm_cust_sch.jsp?msg="+msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&gcv="+gcv+"&ncv="+ncv+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+form_id+"&FormName="+form_nm;
			url=modUrl+"?msg="+msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&gcv="+gcv+"&ncv="+ncv+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}		
	}
	
	@SuppressWarnings("null")
	private void submitCustomerWeeklyNom(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		methodName = "submitCustomerWeeklyNom()";
		form_name = "frm_cust_weekly_nom.jsp";
		
		DecimalFormat df = new DecimalFormat("###");
		
		String gas_date = request.getParameter("gas_date")==null?"":request.getParameter("gas_date");
		String gen_date = request.getParameter("gen_date")==null?"":request.getParameter("gen_date");
		String gcv = request.getParameter("gcv")==null?"":request.getParameter("gcv");
		String ncv = request.getParameter("ncv")==null?"":request.getParameter("ncv");
		String subType = request.getParameter("subType")==null?"":request.getParameter("subType");
		String contTyp = request.getParameter("contTyp")==null?"":request.getParameter("contTyp");
		String indx = request.getParameter("indx")==null?"":request.getParameter("indx");
		
		try {
			
			HttpSession session = request.getSession();
			
			String [] chk_flag = request.getParameterValues("chk_flag");
			
//			System.out.println("chk_flag****"+chk_flag.length);
			String chkBox [] = request.getParameterValues("chkBox");
			String chkLoadFlag [] = request.getParameterValues("chkLoadFlag");
			
			String [] buyer_cd = request.getParameterValues("buyer_cd");
			String [] buyer_plant_seq_no = request.getParameterValues("buyer_plant_seq_no");
			String [] buyer_mapping_id = request.getParameterValues("buyer_mapping_id");
			String [] buyer_sn_no = request.getParameterValues("buyer_sn_no");
			
			String load_mmbtu [] = request.getParameterValues("weeklyTruckVol");
			String arrival_time [] = request.getParameterValues("weeklyTruckArvTime");
			String weeklyNomdate [] = request.getParameterValues("weeklyTruckArvDt");
			String loadtrucknm [] = request.getParameterValues("weeklyTruckNm");
			String trans_cd [] = request.getParameterValues("VWeekly_Trans_cd");
			String remark [] = request.getParameterValues("weeklyTruckRemark");
			String selCheckcustCd = request.getParameter("selCheckcustCd")==null?"":request.getParameter("selCheckcustCd");
			
			String [] qty_mmbtu = request.getParameterValues("qty_mmbtu");
			String [] qty_scm = request.getParameterValues("qty_scm");
			String weeklyDate[] = request.getParameterValues("weeklyDate");
			String next_avail_days[] = request.getParameterValues("next_avail_days"); //HA20201111
			
			String nomId = "0-0-0-0-0-0-0";
			
//			daily_Buyer_Nom_Mapping_Id.elementAt(j)%>-<%=j%>-<%=(String) daily_Buyer_Nom_Plant_Cd.elementAt(j)%>
			
			String custCd  = request.getParameter("custCd")==null?"":request.getParameter("custCd");
			String MappId = "";
			String plan_seq_no = "";
			String sn_no = "";
			String contract_type = "";
			String cust_arr [];
			String cust_cd="0";
			
			if(custCd.contains("----")) {
				String tempArr[] = custCd.split("----");
				MappId = tempArr[0];
				plan_seq_no = tempArr[2];
				sn_no = tempArr[3];
				nomId = MappId+"-"+sn_no+"-"+plan_seq_no;    //map_id-sn_no-plant_seq_no
				contract_type = tempArr[4].substring(0,1);
				cust_arr = MappId.split("-");
				cust_cd = cust_arr[0];

			}
			
			double tot_mmbtu_vol=0;
			Vector nomDtList = new Vector();
			Vector nomVolList = new Vector();
			Set date = new HashSet<>();
			
			String prev="";
			
			/*for (int i = 0; i <= weeklyDate.length-1; i++) {
				if(chkLoadFlag[i].equals("Y")) 
				{
					if(cnt == 0) {
						prev=weeklyDate[i];
					}
					cnt++;
					 if(prev.equals(weeklyDate[i])) {
						 tot_mmbtu_vol+= Double.parseDouble(load_mmbtu[i]);
						 
						 if(i ==  weeklyDate.length-1) {
							 nomVolList.add(tot_mmbtu_vol);
							 nomDtList.add(prev);
						 }
					 }else {
						 
						 System.out.println(prev+"*******"+weeklyDate[i]);
						 nomVolList.add(tot_mmbtu_vol);
						 nomDtList.add(prev);
						 tot_mmbtu_vol=0;
						 tot_mmbtu_vol+=Double.parseDouble(load_mmbtu[i]);
					 }
					 prev = weeklyDate[i];
				}
			}*/
			
			prev=weeklyDate[0];
			for (int i = 0; i <= weeklyDate.length-1; i++) {
				
					 if(prev.equals(weeklyDate[i])) {
						 tot_mmbtu_vol+= Double.parseDouble(load_mmbtu[i]);
						 
						 if(i ==  weeklyDate.length-1) {
							 nomVolList.add(tot_mmbtu_vol);
							 nomDtList.add(prev);
						 }
					 }else {
						 
//						 System.out.println(prev+"*******"+weeklyDate[i]);
						 nomVolList.add(tot_mmbtu_vol);
						 nomDtList.add(prev);
						 tot_mmbtu_vol=0;
						 tot_mmbtu_vol+=Double.parseDouble(load_mmbtu[i]);
					 }
					 prev = weeklyDate[i];
				}
			/* System.out.println("nomVolList***"+nomVolList);
			 System.out.println("nomDtList***"+nomDtList);*/
			 
			 
			 
			double multiplying_factor = 0.252*1000000; 
			int deviding_factor = 1;
			
			double tot_truck_mmbtu =0;
			double tot_truck_ene = 0;
			int rev_no=0;  
			if(!subType.equals("noTruck")) { // if any truck selected
				for (int i = 0 ; i < chkLoadFlag.length ; i++) {
					if(chkLoadFlag[i].equals("Y")) {
						//System.out.println("chkLoadFlag[i]****"+chkLoadFlag[i]+"****"+load_mmbtu [i]);
						tot_truck_mmbtu+= Double.parseDouble(load_mmbtu [i]);
						tot_truck_ene+=  Math.round(Double.parseDouble(load_mmbtu[i]) * multiplying_factor / Double.parseDouble(gcv) * deviding_factor);
					}
				}
				df.format(tot_truck_mmbtu);
				df.format(tot_truck_ene);
		    }
			
			
						
			for (int j = 0 ; j < chkLoadFlag.length ; j++) 
			{
//				System.out.println("chkLoadFlag[j].equals(\"Y\")****"+chkLoadFlag[j]);
				if(chkLoadFlag[j].equals("Y")) 
				{
					
					double truck_ene =  Math.round(Double.parseDouble(load_mmbtu[j]) * multiplying_factor / Double.parseDouble(gcv) * deviding_factor);
					df.format(truck_ene);
					
					nomId = nomId;
					
					
					if(!load_mmbtu[j].equals("0")) //SUJIT12SEP2020 
					{
						
						queryString = "SELECT NVL(MAX(REV_NO),'0') FROM DLNG_WEEKLY_NOM WHERE MAPPING_ID='"+MappId+"' AND "
								+ "NOM_ID='"+nomId+"' AND NOM_DT = TO_DATE('"+weeklyNomdate[j]+"','DD/MM/YYYY') and CONTRACT_TYPE='"+contract_type+"'";
//								+ "PARTY_CD='"++"' ";
//						System.out.println("queryString_revNo : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							rev_no = Integer.parseInt(rset.getString(1));
							rev_no = rev_no + 1;
							
						}
						else
						{
							rev_no = rev_no + 1;
						}
						
						String truck_dtl = "INSERT INTO DLNG_WEEKLY_TRUCK_NOM_DTL"
								+ " (MAPPING_ID,NOM_ID,NOM_DT,REV_NO,ARRIVAL_DT,ARRIVAL_TIME,"
								+ "TRUCK_NM,TRUCK_VOL,UNIT_VOL,TRUCK_ENE,UNIT_ENE,REMARKS,TRANS_CD,CONTRACT_TYPE,NEXT_AVAIL_DAYS)"
								+ " VALUES ('"+MappId+"','"+nomId+"',TO_DATE('"+weeklyNomdate[j]+"','DD/MM/YYYY'),'"+rev_no+"',TO_DATE('"+weeklyNomdate[j]+"','DD/MM/YYYY'),'"+arrival_time[j]+"','"+loadtrucknm[j]+"','"+load_mmbtu[j]+"','MMBTU','"+truck_ene+"','SCM','"+remark[j]+"','"+trans_cd[j]+"','"+contTyp+"','"+next_avail_days[j]+"')";
//						System.out.println("Insert Query DLNG_WEEKLY_TRUCK_NOM_DTL*****"+truck_dtl);
						stmt.executeUpdate(truck_dtl);
						
						double sumOfTruckEne = Math.round(Double.parseDouble(load_mmbtu[j]) * multiplying_factor / Double.parseDouble(gcv) * deviding_factor);
						
						date.add(weeklyNomdate[j]);
						
					}
					else
					{
//						System.out.println("if....0....load_mmbtu : "+load_mmbtu[j]+"...."+loadtrucknm[j]+"...."+weeklyNomdate[j]);
					}
					
					
				}
			}//
//			System.out.println("date****"+date);
			for (int i = 0; i < nomDtList.size(); i++) {
				
				if(date.contains(nomDtList.elementAt(i))) {
					if(Double.parseDouble(nomVolList.elementAt(i)+"") > 0) {
						double sumOfTruckEne = Math.round(Double.parseDouble(nomVolList.elementAt(i)+"") * multiplying_factor / Double.parseDouble(gcv) * deviding_factor);
						
						queryString = "SELECT NVL(MAX(REV_NO),'0') FROM DLNG_WEEKLY_NOM WHERE MAPPING_ID='"+MappId+"' AND "
								+ "NOM_ID='"+nomId+"' AND NOM_DT = TO_DATE('"+nomDtList.elementAt(i)+"','DD/MM/YYYY') and CONTRACT_TYPE='"+contract_type+"' ";
//								+ "PARTY_CD='"++"' ";
//						System.out.println("queryString_revNo : "+queryString);
						rset = stmt.executeQuery(queryString);
						if(rset.next())
						{
							rev_no = Integer.parseInt(rset.getString(1));
							rev_no = rev_no + 1;
							
						}
						else
						{
							rev_no = rev_no + 1;
						}
						
						queryString = "INSERT INTO DLNG_WEEKLY_NOM (MAPPING_ID,NOM_ID,REV_NO,PARTY_CD,SUP_TRN_CD,"
								+ "NOM_DT,GEN_DT,NHV,GHV,DAY_VOL,UNIT_VOL,TOT_ENE,UNIT_ENE,CONTRACT_TYPE) "
								+ "VALUES ('"+MappId+"','"+nomId+"','"+rev_no+"',"
								+ "'"+cust_cd+"','0',TO_DATE('"+nomDtList.elementAt(i)+"','DD/MM/YYYY'),TO_DATE('"+gen_date+"','DD/MM/YYYY'),"
								+ "'"+ncv+"','"+gcv+"','"+nomVolList.elementAt(i)+"','MMBTU','"+sumOfTruckEne+"','SCM','"+contTyp+"') ";
//						System.out.println("--Insert Query DLNG_WEEKLY_NOM--"+queryString);
						stmt.executeUpdate(queryString);
					}
				 }
				}
			msg = "Customer WEEKLY Nomination: Successfully Done For The Selected Customers For Weekly Nomination  "+gas_date+"  To   "+weeklyNomdate[weeklyNomdate.length-1]+"!!!";
			//HA20181221 url = "../hcas/frm_cust_nom.jsp?msg="+msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&gcv="+gcv+"&ncv="+ncv+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+form_id+"&FormName="+form_nm;
			url = "../master/frm_mst_buyerNomination.jsp?msg="+msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&gcv="+gcv+"&ncv="+ncv+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&selCheckcustCd="+selCheckcustCd+"&nomId="+nomId+"&contract_type="+contract_type+"&buyer_mapping_id="+MappId+"&indx="+indx;
			
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			dbcon.rollback();
			msg = "Customer Nomination Submission Failed !!!";
			e.printStackTrace();
			//HA20181221 url="../hcas/frm_cust_nom.jsp?msg="+msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&gcv="+gcv+"&ncv="+ncv+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+form_id+"&FormName="+form_nm;
			url="../master/frm_mst_buyerNomination.jsp?msg="+msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&gcv="+gcv+"&ncv="+ncv+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		}		
		
	}
	
	public void submitTruckLoad(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		methodName = "submitTruckLoad()";
		form_name = "frm_tank_truck_load.jsp";
		
		String gas_date = request.getParameter("gas_date")==null?"":request.getParameter("gas_date");
		String gen_date = request.getParameter("gen_date")==null?"":request.getParameter("gen_date");
		String gcv = request.getParameter("gcv")==null?"":request.getParameter("gcv");
		String ncv = request.getParameter("ncv")==null?"":request.getParameter("ncv");
		String prepareInv = request.getParameter("prepareInv")==null?"":request.getParameter("prepareInv");
		try {
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			
			String int_tank_id = request.getParameter("int_tank_id")==null?"0":request.getParameter("int_tank_id");
//HA20181224			String int_tank_eff_vol = request.getParameter("int_tank_eff_vol")==null?"0":request.getParameter("int_tank_eff_vol");
			String int_tank_eff_vol = request.getParameter("int_tank_vol")==null?"0":request.getParameter("int_tank_vol");
			String int_tank_vol = request.getParameter("int_tank_vol")==null?"0":request.getParameter("int_tank_vol");
			String index_count = request.getParameter("index_count")==null?"0":request.getParameter("index_count");
			String [] buyer_plant_seq_no = request.getParameterValues("buyer_plant_seq_no");
			String [] buyer_mapping_id = request.getParameterValues("buyer_mapping_id");
			String [] buyer_sn_no = request.getParameterValues("buyer_sn_no");
			String [] nom_rev_no = request.getParameterValues("nom_rev_no");//Hiren_20200305
			String [] contract_type = request.getParameterValues("contract_type");//HA20200227
			String [] buyer_cd = request.getParameterValues("buyer_cd");
			String transfer_tank_flag = request.getParameter("transfer_tank_flag")==null?"N":request.getParameter("transfer_tank_flag");
			String transfer_tank_vol = request.getParameter("transfer_tank_vol")==null?"0":request.getParameter("transfer_tank_vol");
			String [] buyer_sch_mapping_id = request.getParameterValues("buyer_sch_mapping_id");
			
			
			int count = 0;
//			System.out.println("index_count****"+index_count);
			for(int i=0;i<Integer.parseInt(index_count);i++)
			{
				String chkFlag[] = request.getParameterValues("chkFlag"+i);
				String modifyFlag [] = request.getParameterValues("modifyFlag"+i);
//				System.out.println("chkFlag.length****"+chkFlag.length);
				
				if(chkFlag!=null) { 
					for(int k=0;k<chkFlag.length;k++) {
						if(chkFlag[k].equalsIgnoreCase("Y") && !modifyFlag[k].equals("Y"))
						{
							count++;
						}
					}
				}
			}
			int data_avail = 0;
			if(count>0) {
				
			}
			
			String sch_qty [] = request.getParameterValues("sch_qty");
			int seq_no = 0;
			
			
			double tank_vol_avl = Double.parseDouble(int_tank_eff_vol);
//			System.out.println("index_count*******"+index_count);
			for(int i=0;i<Integer.parseInt(index_count);i++)
			{
				
				int tempCnt = 0;
				String chkFlag[] = request.getParameterValues("chkFlag"+i);
//			System.out.println("chkFlag&&&&&&&&&"+chkFlag.length);
			if(chkFlag!=null ) {
					
				String load_st_day [] = request.getParameterValues("load_st_day"+i);
				String load_st_time [] = request.getParameterValues("load_st_time"+i);
				String loaded_vol [] = request.getParameterValues("loaded_vol_ton"+i);
				String loaded_vol_scm [] = request.getParameterValues("loaded_vol_scm"+i);
				String loaded_vol_mmbtu [] = request.getParameterValues("loaded_vol_mmbtu"+i); //SB20181219
//				System.out.println("loaded_vol_mmbtu********"+loaded_vol_mmbtu[i]);
				/*String unloaded_vol [] = request.getParameterValues("unloaded_vol"+i); //SB20181219
*/				String unloaded_vol_scm [] = request.getParameterValues("unloaded_vol_scm"+i);
				String truck_id [] = request.getParameterValues("truck_id"+i);
				String modifyFlag [] = request.getParameterValues("modifyFlag"+i);
				String load_end_time [] = request.getParameterValues("load_end_time"+i);
				String load_end_day [] = request.getParameterValues("load_end_day"+i);
				String truck_nm [] = request.getParameterValues("truck_nm"+i);
				String truck_trans_cd [] = request.getParameterValues("truck_trans_cd"+i);
				String [] next_avail_days = request.getParameterValues("next_avail_days"+i);
				String gcv_per_mmbtu []= request.getParameterValues("gcv_per_mmbtu"+i);
//				System.out.println("chkFlag.length&&&&"+chkFlag.length);
				String mapp_id[] =request.getParameterValues("plant_no"+i);
				String prev_mapp_id="";
//				System.out.println("chkFlag.length****"+chkFlag.length);
				for(int k=0;k<chkFlag.length;k++) {
					int alloc_seq_no = 0; String flag="";
//					System.out.println("chkFlag****"+chkFlag[k]);
						if(chkFlag[k].equalsIgnoreCase("Y"))
						{
	//						System.out.println("innnnnnnnnnnnnnnnnnnnn");
	//						if(modifyFlag[k].equals("Y")) {
				/////////////////SB2018122/////////////
								String map_id=buyer_mapping_id[i];
								String tempmap_id[]=map_id.split("-");
								String temp_Aagreement_no=tempmap_id[1];
								String temp_Aagreement_rev_no=tempmap_id[2];
								String temp_sn_no=tempmap_id[3];
								String temp_sn_rev_no=tempmap_id[4];
								String temp_cust_cd=tempmap_id[0];
	//							String rev_no=temp_Aagreement_rev_no;
								double sales_rate=11.0; double exchg_val=70.0;
								double cost = sales_rate*exchg_val*Double.parseDouble(loaded_vol_mmbtu[k]);
								String fgsa_no = temp_Aagreement_no;
								String qty_scm = loaded_vol_scm[k];
	//									System.out.println("qty_scm***"+qty_scm);
	//									String contract_type = "S";
								String transporter_cd = truck_trans_cd[k];
								int cnt=0;
								
								int truck_cnt=0;
								String alloc_cnt = "SELECT COUNT(*) FROM DLNG_ALLOC_MST WHERE MAPPING_ID='"+buyer_mapping_id[i]+"' "
										+ " AND ALLOC_ID='"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"' "
										+ " AND NOM_REV_NO='"+nom_rev_no[i]+"'"
										+ " AND PARTY_CD='"+buyer_cd[i]+"'"
										+ " AND ALLOC_DT=TO_DATE('"+load_end_day[k]+"','DD/MM/YYYY')"
										+ " AND SUP_TRN_CD = '"+truck_id[k]+"'";
//								System.out.println("alloc_cnt*****"+alloc_cnt);
								rset1 = stmt1.executeQuery(alloc_cnt);
								if(rset1.next()) {
									truck_cnt= rset1.getInt(1);
								}else {
									truck_cnt=0;
								}
//								System.out.println("truck_cnt***"+truck_cnt+"****"+tempCnt+"****"+buyer_cd[i]+"**gcv_per_mmbtu[k]**"+gcv_per_mmbtu[k]);
								if(truck_cnt > 0){
									
									queryString = "SELECT MAX(SEQ_NO+1) FROM DLNG_TANK_VOL_DTL  ";
									rset = stmt.executeQuery(queryString);
									if(rset.next()) {
										seq_no = rset.getInt(1);
									}
									queryString = "update DLNG_ALLOC_MST set ENTRY_ALLOC_VOL = '"+loaded_vol[k]+"',EXIT_ALLOC_VOL = '"+loaded_vol[k]+"',"
											+ " ENTRY_TOT_ENE = '"+loaded_vol_mmbtu[k]+"' ,EXIT_TOT_ENE = '"+loaded_vol_mmbtu[k]+"',NCV = '"+ncv+"',GCV = '"+gcv+"',NEXT_AVAIL_DAYS = '"+next_avail_days[k]+"' "
											+ " where MAPPING_ID = '"+buyer_mapping_id[i]+"' "
											+ " and ALLOC_ID = '"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"' "
											+ " and NOM_REV_NO = '"+nom_rev_no[i]+"' "
											+ " and ALLOC_DT = TO_DATE('"+load_end_day[k]+"','DD/MM/YYYY') "
											+ " and PARTY_CD = '"+buyer_cd[i]+"'"
											+ " and SUP_TRN_CD = '"+truck_id[k]+"'"
											+ " and CONTRACT_TYPE = '"+contract_type[i]+"' "; 
//									System.out.println("queryString***"+queryString);
									int updCnt = stmt.executeUpdate(queryString);
//									System.out.println("updCnt****"+updCnt);
									/*queryString = "INSERT INTO DLNG_ALLOC_MST (MAPPING_ID,ALLOC_ID,PARTY_CD,SUP_TRN_CD,NOM_REV_NO,ALLOC_DT,ENTRY_ALLOC_VOL,EXIT_ALLOC_VOL,"
											+ "ENTRY_TOT_ENE,EXIT_TOT_ENE,GAS_DT,TRANS_CD,NCV,GCV,CONTRACT_TYPE,NEXT_AVAIL_DAYS) VALUES ('"+buyer_mapping_id[i]+"','"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"',"
											+ "'"+buyer_cd[i]+"','"+truck_id[k]+"','"+nom_rev_no[i]+"',TO_DATE('"+load_end_day[k]+"','DD/MM/YYYY'),'"+loaded_vol[k]+"','"+loaded_vol[k]+"','"+loaded_vol_mmbtu[k]+"',"
											+ " '"+loaded_vol_mmbtu[k]+"',TO_DATE('"+gas_date+"','DD/MM/YYYY'),'"+transporter_cd+"','"+ncv+"','"+gcv+"','"+contract_type[i]+"','"+next_avail_days[k]+"') ";
									 */
//										System.out.println("other than first**INserting allocation Data..."+queryString);
										double eff_vol_avl = tank_vol_avl - Double.parseDouble(loaded_vol[k]);
										
										if(eff_vol_avl<0){
											eff_vol_avl+=250;
										}
										
										queryString = "INSERT INTO DLNG_TANK_VOL_DTL (TANK_ID,EFF_DT,SEQ_NO,TANK_VOL_AVL,TRN_CD,TRN_NM,LOAD_START_TM,SCH_LOAD_VOL,LOAD_END_TM,"
												+ "LOADED_VOL,LOADED_ENE,EFF_VOL_AVL,ENT_CD,ENT_DT,FLAG,SCH_ID,LOADED_SCM,UNLOADED_ENE,UNLOADED_SCM,TRANS_CD,GCV_PER_MMBTU) "
												+ " VALUES ('"+int_tank_id+"',TO_DATE('"+gas_date+"','DD/MM/YYYY'),'"+seq_no+"','"+tank_vol_avl+"','"+truck_id[k]+"',"
												+ " '"+truck_nm[k]+"',TO_DATE('"+load_st_day[k]+" "+load_st_time[k]+"','DD/MM/YYYY HH24:MI'),'"+sch_qty[i]+"',"
												+ "TO_DATE('"+load_end_day[k]+" "+load_end_time[k]+"','DD/MM/YYYY HH24:MI'),'"+loaded_vol[k]+"','"+loaded_vol_mmbtu[k]+"','"+eff_vol_avl+"','"+user_cd+"',SYSDATE,'"+flag+"','"+buyer_sch_mapping_id[i]+"',"
												+ " '"+loaded_vol_scm[k]+"','"+loaded_vol_mmbtu[k]+"','"+unloaded_vol_scm[k]+"','"+transporter_cd+"','"+gcv_per_mmbtu[k]+"') ";
//										System.out.println("other than first**DLNG_TANK_VOL_DTL****"+queryString);
										int insCnt = stmt1.executeUpdate(queryString);
//										System.out.println("insCnt****"+insCnt);
									/*if(tempCnt==0) {
										String delete = "DELETE  FROM DLNG_ALLOC_MST WHERE MAPPING_ID='"+buyer_mapping_id[i]+"' "
												+ " AND ALLOC_ID='"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"' "
	//											+ " AND NOM_REV_NO='"+nom_rev_no[i]+"'"
												+ " AND PARTY_CD='"+buyer_cd[i]+"'"
												+ " AND ALLOC_DT=TO_DATE('"+load_end_day[k]+"','DD/MM/YYYY') ";
										System.out.println("delete*****"+delete);
										stmt2.executeUpdate(delete);
										
										String updateTank = "DELETE FROM DLNG_TANK_VOL_DTL "
												+ " WHERE EFF_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY')  AND SCH_ID='"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"'  ";
											System.out.println("updateTank*****"+updateTank);
										stmt.executeUpdate(updateTank);
									}*/
								}else {
									//first time entry
									queryString = "SELECT max(seq_no+1) FROM DLNG_TANK_VOL_DTL  ";
									rset = stmt.executeQuery(queryString);
									
									if(rset.next()) {
										seq_no = rset.getInt(1);
									}
									int temp_cnt1=0;
									String noNomRevNo = "SELECT COUNT(*) FROM DLNG_ALLOC_MST WHERE MAPPING_ID='"+buyer_mapping_id[i]+"' "
											+ " AND ALLOC_ID='"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"' "
	//										+ " AND NOM_REV_NO='"+nom_rev_no[i]+"'"
											+ " AND PARTY_CD='"+buyer_cd[i]+"'"
											+ " AND ALLOC_DT=TO_DATE('"+load_end_day[k]+"','DD/MM/YYYY') ";
//									System.out.println("noNomRevNo*****"+noNomRevNo);
									rset1 = stmt1.executeQuery(noNomRevNo);
									if(rset1.next()) {
										temp_cnt1 = rset1.getInt(1);
									}
									
									if(temp_cnt1 > 0) {
										if(tempCnt==0) {
											String delete = "DELETE  FROM DLNG_ALLOC_MST WHERE MAPPING_ID='"+buyer_mapping_id[i]+"' "
													+ " AND ALLOC_ID='"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"' "
	//												+ " AND NOM_REV_NO='"+nom_rev_no[i]+"'"
													+ " AND PARTY_CD='"+buyer_cd[i]+"'"
													+ " AND ALLOC_DT=TO_DATE('"+load_end_day[k]+"','DD/MM/YYYY') ";
//											System.out.println("delete*****"+delete);
//											stmt2.executeUpdate(delete); //Hiren20210318
											
											String updateTank = "DELETE FROM DLNG_TANK_VOL_DTL "
													+ " WHERE EFF_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY')  AND SCH_ID='"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"'  ";
//												System.out.println("updateTank*****"+updateTank);
//											stmt.executeUpdate(updateTank);  //Hiren20210318
										}
									}
	//								else {
										queryString = "INSERT INTO DLNG_ALLOC_MST (MAPPING_ID,ALLOC_ID,PARTY_CD,SUP_TRN_CD,NOM_REV_NO,ALLOC_DT,ENTRY_ALLOC_VOL,EXIT_ALLOC_VOL,"
												+ "ENTRY_TOT_ENE,EXIT_TOT_ENE,GAS_DT,TRANS_CD,NCV,GCV,CONTRACT_TYPE,NEXT_AVAIL_DAYS) VALUES ('"+buyer_mapping_id[i]+"','"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"',"
												+ "'"+buyer_cd[i]+"','"+truck_id[k]+"','"+nom_rev_no[i]+"',TO_DATE('"+load_end_day[k]+"','DD/MM/YYYY'),'"+loaded_vol[k]+"','"+loaded_vol[k]+"','"+loaded_vol_mmbtu[k]+"',"
												+ " '"+loaded_vol_mmbtu[k]+"',TO_DATE('"+gas_date+"','DD/MM/YYYY'),'"+transporter_cd+"','"+ncv+"','"+gcv+"','"+contract_type[i]+"','"+next_avail_days[k]+"') ";
	
//											System.out.println("first**INserting allocation Data..."+queryString);
											stmt.executeUpdate(queryString);
											
											double eff_vol_avl = tank_vol_avl - Double.parseDouble(loaded_vol[k]);
											
											if(eff_vol_avl<0){
												eff_vol_avl+=250;
											}
											
											queryString = "INSERT INTO DLNG_TANK_VOL_DTL (TANK_ID,EFF_DT,SEQ_NO,TANK_VOL_AVL,TRN_CD,TRN_NM,LOAD_START_TM,SCH_LOAD_VOL,LOAD_END_TM,"
													+ "LOADED_VOL,LOADED_ENE,EFF_VOL_AVL,ENT_CD,ENT_DT,FLAG,SCH_ID,LOADED_SCM,UNLOADED_ENE,UNLOADED_SCM,TRANS_CD,GCV_PER_MMBTU) "
													+ " VALUES ('"+int_tank_id+"',TO_DATE('"+gas_date+"','DD/MM/YYYY'),'"+seq_no+"','"+tank_vol_avl+"','"+truck_id[k]+"',"
													+ " '"+truck_nm[k]+"',TO_DATE('"+load_st_day[k]+" "+load_st_time[k]+"','DD/MM/YYYY HH24:MI'),'"+sch_qty[i]+"',"
													+ "TO_DATE('"+load_end_day[k]+" "+load_end_time[k]+"','DD/MM/YYYY HH24:MI'),'"+loaded_vol[k]+"','"+loaded_vol_mmbtu[k]+"','"+eff_vol_avl+"','"+user_cd+"',SYSDATE,'"+flag+"','"+buyer_sch_mapping_id[i]+"',"
													+ " '"+loaded_vol_scm[k]+"','"+loaded_vol_mmbtu[k]+"','"+unloaded_vol_scm[k]+"','"+transporter_cd+"','"+gcv_per_mmbtu[k]+"') ";
//											System.out.println("first**DLNG_TANK_VOL_DTL****"+queryString);
											stmt.executeUpdate(queryString);
	//								}
									
								}
								tempCnt++;
								}
							}
						}
					}

			msg="Truck Loading successfully done for the selected customer(s)";
			if(prepareInv.equals("Y")) {
				String mod_cd="5"; // for invoicing
				String form_cd = "";
				String subModUrl="../sales_invoice/frm_sales_invoice_preparation.jsp";
				String modCdSql = "SELECT MODULE_CD FROM DLNG_MODULE_MST WHERE DEF_PATH='../sales_invoice/frm_mst_prepareInvoice.jsp' ";
				rset = stmt.executeQuery(modCdSql);
				if(rset.next()) {
					mod_cd = rset.getString(1);
					String formIdSql = "SELECT FORM_CD FROM DLNG_FORM_MST WHERE CLASSPATH='../sales_invoice/frm_sales_invoice_preparation.jsp' AND MODULE_CD='"+mod_cd+"'";
					rset1 = stmt.executeQuery(formIdSql);
					if(rset1.next()) {
						form_cd = rset1.getString(1);
					}
				}
				
				url = "../sales_invoice/frm_mst_prepareInvoice.jsp?modCd="+mod_cd+"&formId="+form_cd+"&subModUrl="+subModUrl+"&msg="+msg+"&bill_cycle="+gas_date+"&bill_cycle="+gen_date;
			}else {
				url = "../master/frm_mst_truckLoading.jsp?msg="+msg+"&bill_cycle="+gas_date+"&bill_cycle="+gen_date+"&gcv="+gcv+"&ncv="+ncv+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
			}
			//System.out.println("url******"+url);
			dbcon.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
			
		} catch(Exception e) {
			dbcon.rollback();
			error_msg = "Failed to Submit Truck Loading Data !!!";
			url = "../master/frm_mst_truckLoading.jsp?error_msg="+error_msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&gcv="+gcv+"&ncv="+ncv+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
			e.printStackTrace();
		}
	}
	
	public void submitCustomerSch(HttpServletRequest request,HttpServletResponse response)  throws Exception {

		methodName = "submitCustomerSch()";
		form_name = "frm_cust_sch.jsp";
		DecimalFormat df = new DecimalFormat("###");
		String gas_date = request.getParameter("gas_date")==null?"":request.getParameter("gas_date");
		String gen_date = request.getParameter("gen_date")==null?"":request.getParameter("gen_date");
		String gcv = request.getParameter("gcv")==null?"":request.getParameter("gcv");
		String ncv = request.getParameter("ncv")==null?"":request.getParameter("ncv");
		String subType = request.getParameter("subType")==null?"":request.getParameter("subType");//HA20200205
		String modUrl = request.getParameter("modUrl")==null?"":request.getParameter("modUrl");//Hiren_20210607
		try
		{
			HttpSession session = request.getSession();
//			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			
			String [] chk_flag = request.getParameterValues("chk_flag");
			String [] buyer_cd = request.getParameterValues("buyer_cd");
			String hh_mm = request.getParameter("hh_mm")==null?"":request.getParameter("hh_mm");
			String [] buyer_plant_seq_no = request.getParameterValues("buyer_plant_seq_no");
			String [] qty_mmbtu = request.getParameterValues("qty_mmbtu");
			String [] qty_scm = request.getParameterValues("qty_scm");
			String [] buyer_mapping_id = request.getParameterValues("buyer_mapping_id");
			String [] buyer_sn_no = request.getParameterValues("buyer_sn_no");
			String [] daily_buyer_rev_no = request.getParameterValues("daily_buyer_rev_no");
			
			
			String [] chekboxs= request.getParameterValues("chekboxs");
			String [] scheduleCheckFlag= request.getParameterValues("checkFlag");
			String [] trucknm= request.getParameterValues("trucknm");
			String [] schedule_mmbtu= request.getParameterValues("schedule_mmbtu");
			String [] schedule_ton= request.getParameterValues("schedule_ton");
			String [] schedul_date= request.getParameterValues("schedul_date");
			String [] schedule_time= request.getParameterValues("schedule_time");
			String [] remark= request.getParameterValues("remark");
			String Vtrans_cd [] = request.getParameterValues("Vtrans_cd"); //Hiren_20200329
			String next_avail_days [] = request.getParameterValues("next_avail_days"); //Hiren_20201103
			String contract_type[] = request.getParameterValues("contract_type"); //HA20201121
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");

			double multiplying_factor = 0.252*1000000; 
			int deviding_factor = 1;
			double tot_truck_mmbtus =0;
			double tot_truck_ene = 0;
			int insDtlCnt = 0 ;
			int insCnt = 0 ;
//			System.out.println("subType***"+subType);
			if(!subType.equals("noTruck")) {
				for(int i=0;i<scheduleCheckFlag.length;i++) {
					
					if(scheduleCheckFlag[i].equals("Y")) {
						tot_truck_mmbtus+=Double.parseDouble(schedule_mmbtu[i]);
						tot_truck_ene+=Math.round(Double.parseDouble(schedule_mmbtu[i])* multiplying_factor / Double.parseDouble(gcv)*deviding_factor);
					}
				}
			}
			df.format(tot_truck_mmbtus);
			df.format(tot_truck_ene);
			
			for(int i=0;i<chk_flag.length;i++)
			{
				int rev_no = 0;
				String prev_rev_no = "";
				if(chk_flag[i].equalsIgnoreCase("Y"))
				{
					queryString = "SELECT NVL(MAX(REV_NO),'0') FROM DLNG_DAILY_SCH WHERE MAPPING_ID='"+buyer_mapping_id[i]+"' AND "
							+ "SCH_ID='"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"' AND SCH_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY') AND "
							+ "PARTY_CD='"+buyer_cd[i]+"' and CONTRACT_TYPE='"+contract_type[i]+"' ";
//					System.out.println("queryString------>"+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						rev_no = Integer.parseInt(rset.getString(1));
						prev_rev_no = rev_no+"";
						rev_no = rev_no + 1;
					}
					else
					{
						prev_rev_no = rev_no+"";
						rev_no = rev_no + 1;
					}
					int truckCnt = 0;
					if(subType.equals("noTruck")) {
						
						tot_truck_mmbtus = Double.parseDouble(qty_mmbtu[i].toString());
						tot_truck_ene = Double.parseDouble(qty_scm[i].toString());
						
						int schCnt = 0 ;
						String cntSql = "select count(*) from DLNG_DAILY_TRUCK_SCH_DTL A"
								+ " where A.SCH_ID = '"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"'"
								+ " and A.SCH_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY')"
								+ " AND A.REV_NO= (select max(B.rev_no) from DLNG_DAILY_TRUCK_SCH_DTL B"
									+ " where B.SCH_ID = A.SCH_ID  AND B.SCH_DT = A.SCH_DT AND B.TRUCK_NM = A.TRUCK_NM"
									+ " and B.CONTRACT_TYPE=A.CONTRACT_TYPE "
									+ " and B.ent_dt = (select max(C.ent_dt) from DLNG_DAILY_TRUCK_SCH_DTL C where C.SCH_ID = B.SCH_ID  AND C.SCH_DT = B.SCH_DT AND C.TRUCK_NM = B.TRUCK_NM and C.CONTRACT_TYPE=B.CONTRACT_TYPE))";
						rset = stmt.executeQuery(cntSql);
						if(rset.next()) {
							schCnt = rset.getInt(1);
						}
						String dtlSql = "";
						
						if(schCnt > 0) {
							dtlSql = "select distinct(TRUCK_NM) from DLNG_DAILY_TRUCK_SCH_DTL A"
									+ " where A.SCH_ID = '"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"'"
									+ " and A.SCH_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY')"
//									+ " and REV_NO = '"+prev_rev_no+"'";
									+ " AND A.REV_NO= (select max(B.rev_no) from DLNG_DAILY_TRUCK_SCH_DTL B"
									+ " where B.SCH_ID = A.SCH_ID  AND B.SCH_DT = A.SCH_DT AND B.TRUCK_NM = A.TRUCK_NM"
									+ " and B.CONTRACT_TYPE=A.CONTRACT_TYPE "
									+ " and B.ENT_DT = (select max(C.ent_dt) from DLNG_DAILY_TRUCK_SCH_DTL C where C.SCH_ID = B.SCH_ID  AND C.SCH_DT = B.SCH_DT AND C.TRUCK_NM = B.TRUCK_NM and C.CONTRACT_TYPE=B.CONTRACT_TYPE))";
						}else {
							dtlSql = "select distinct(TRUCK_NM) from DLNG_DAILY_TRUCK_NOM_DTL A"
									+ " where NOM_ID = '"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"'"
									+ " and NOM_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY')"
//									+ " and REV_NO = '"+daily_buyer_rev_no[i]+"'";
									+ " AND A.REV_NO= (select max(B.rev_no) from DLNG_DAILY_TRUCK_NOM_DTL B"
									+ " where B.NOM_ID = A.NOM_ID  AND B.NOM_DT = A.NOM_DT AND B.TRUCK_NM = A.TRUCK_NM"
									+ " and B.CONTRACT_TYPE=A.CONTRACT_TYPE "
									+ " and B.ENT_DT = (select max(C.ent_dt) from DLNG_DAILY_TRUCK_NOM_DTL C where C.NOM_ID = B.NOM_ID  AND C.NOM_DT = B.NOM_DT AND C.TRUCK_NM = B.TRUCK_NM and C.CONTRACT_TYPE=B.CONTRACT_TYPE))";
						}
						System.out.println("dtlSql**"+dtlSql);
						rset = stmt.executeQuery(dtlSql);
						while (rset.next()) {
							truckCnt++;
							String truck_name = rset.getString(1)==null?"":rset.getString(1);
							
							//Hiren_20230221 filtering TLU & Invoiced Trucks
							int alloc_cnt = 0 ;
							String allocCnt = "SELECT count(*) FROM DLNG_TANK_VOL_DTL WHERE "
									+ " SCH_ID like '"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"'"
									+ " AND TRN_NM='"+truck_name+"'"
									+ " AND EFF_DT = TO_DATE ('"+gas_date+"','DD/MM/YYYY')";
							rset1 = stmt1.executeQuery(allocCnt);
							if(rset1.next()) {
								alloc_cnt = rset1.getInt(1);
							}
							if(alloc_cnt == 0 ) {
								String queryString1 = "SELECT  nvl(A.TRANS_CD,'0'),TRUCK_ID FROM DLNG_TANK_TRUCK_MST  A, DLNG_TRANS_MST B "
										+ " WHERE A.TRANS_CD=B.TRANS_CD AND A.STATUS ='Y' AND ( CUSTOMER_CD ='"+buyer_cd[i]+"' OR CUSTOMER_CD ='0' )"
										+ " AND TRUCK_NM = '"+truck_name+"'"
										+ " AND A.TRANS_CD IN "
										+ " (SELECT TRANS_CD FROM DLNG_CUST_TRANS_DTL WHERE"
											+ " CUST_CD ='"+buyer_cd[i]+"'"
											+ " AND FLAG='Y' "
											+ " AND TRUCK_NM = '"+truck_name+"')";
	//							System.out.println("iqueryString1**"+queryString1);
								rset1 = stmt1.executeQuery(queryString1);
								if(rset1.next()) {
									
									String transCd = rset1.getString(1)==null?"":rset1.getString(1);
									String truck_ene = "0",truck_vol = "0";
									
									String allocDtl = "select UNLOADED_ENE,LOADED_VOL from DLNG_TANK_VOL_DTL"
											+ " where EFF_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY')"
											+ " and SCH_ID = '"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"'"
											+ " and TRN_NM = '"+truck_name+"' ";
									/*rset2 = stmt2.executeQuery(allocDtl);
									if(rset2.next()) {
										truck_ene = rset2.getString(1)==null?"0":rset2.getString(1);
										truck_vol = rset2.getString(2)==null?"0":rset2.getString(2); 
									}*/
									
									String truck_dtl = "INSERT INTO DLNG_DAILY_TRUCK_SCH_DTL (MAPPING_ID,SCH_ID,SCH_DT,REV_NO,TRUCK_NM,TRANS_CD,CONTRACT_TYPE,TRUCK_ENE,TRUCK_VOL,SCH_TIME,REMARKS,ENT_DT,ENT_BY)"
											+ " VALUES ('"+buyer_mapping_id[i]+"','"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"',TO_DATE('"+gas_date+"','DD/MM/YYYY'),'"+rev_no+"','"+truck_name+"','"+transCd+"','"+contract_type[i]+"','"+truck_ene+"','"+truck_vol+"','N.A','Cancelled',sysdate,'"+user_cd+"')";
	//								System.out.println("insert truck_dtl ***11**"+truck_dtl);
									insDtlCnt+= stmt2.executeUpdate(truck_dtl);
								}
							}
						}
					}else {
						for(int j=0;j<scheduleCheckFlag.length;j++) {
							if(scheduleCheckFlag[j].equalsIgnoreCase("Y")) {
								double truck_ene = Math.round(Double.parseDouble(schedule_mmbtu[j]) * multiplying_factor / Double.parseDouble(gcv) * deviding_factor);
								df.format(truck_ene);
//								System.out.println("contract_type[i]****"+contract_type[i]);
								queryString1 ="INSERT INTO DLNG_DAILY_TRUCK_SCH_DTL (MAPPING_ID,SCH_ID,SCH_DT,REV_NO,"
											  + "SCH_TIME,TRUCK_NM,TRUCK_VOL,UNIT_VOL,"
											  + "TRUCK_ENE,UNIT_ENE,REMARKS,TRANS_CD,NEXT_AVAIL_DAYS,CONTRACT_TYPE,ENT_DT,ENT_BY)VALUES('"+buyer_mapping_id[i]+"',"
											  + " '"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"',"
											  + " TO_DATE('"+gas_date+"','DD/MM/YYYY'),"
											  + " '"+rev_no+"',"
											  + " '"+schedule_time[j]+"',"
											  + " '"+trucknm[j]+"',"
											  + " '"+schedule_mmbtu[j]+"','MMBTU',"
											  + " '"+truck_ene+"', 'SCM', "
											  + " '"+remark[j]+"','"+Vtrans_cd[j]+"','"+next_avail_days[j]+"','"+contract_type[i]+"',sysdate,'"+user_cd+"')";
//								System.out.println("queryString1 : "+ queryString1);
//								System.out.println("Vtrans_cd[j]*****"+Vtrans_cd[j]);
								insDtlCnt+=stmt5.executeUpdate(queryString1);
							}
						}
					}
					//count++;
					
					queryString = "INSERT INTO DLNG_DAILY_SCH (MAPPING_ID,"
								  +"SCH_ID,"
								  +"REV_NO,"
								  +"PARTY_CD,"
							      +"SUP_TRN_CD,"
							      +"SCH_DT,"
							      +"GEN_DT,"
							      +"TIME_ST_DAY,"
							      +"NHV,"
							      +"GHV,"
							      +"DAY_VOL,"
							      +"UNIT_VOL,"
							      +"TOT_ENE,"
							      +"UNIT_ENE,CONTRACT_TYPE) "
							      +"VALUES ('"+buyer_mapping_id[i]+"',"
							      +"'"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"',"
							      +"'"+rev_no+"',"
							      +"'"+buyer_cd[i]+"',"
							      + "'0',"
							      +"TO_DATE('"+gas_date+"','DD/MM/YYYY'),"
							      +"TO_DATE('"+gen_date+"','DD/MM/YYYY'),"
							      +"'"+hh_mm+"',"
							      +"'"+ncv+"',"
							      +"'"+gcv+"',"
							      +"'"+tot_truck_mmbtus+"','MMBTU',"
							      +"'"+tot_truck_ene+"','SCM','"+contract_type[i]+"') ";
//					System.out.println("--Insert Query--"+queryString);
						insCnt+= stmt.executeUpdate(queryString);
				}
			}
			System.out.println("insCnt***"+insCnt+"**insDtlCnt**"+insDtlCnt);
			if(insCnt > 0 && insDtlCnt > 0 ) {
				dbcon.commit();
				msg = "Customer Seller Nomination Successfully Done For The Selected Buyers For Schedule DAY: "+gas_date+" !!!";
			}else {
				msg = "No Record Found of The Selected Customers For Seller Nomination DAY: "+gas_date+" !!!";
			}
			
//			msg = "Customer Seller Nomination Successfully Done For The Selected Buyers For Schedule DAY: "+gas_date+" !!!";
			//HA20181221 url = "../hcas/frm_cust_sch.jsp?msg="+msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&gcv="+gcv+"&ncv="+ncv+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+form_id+"&FormName="+form_nm;
			url = modUrl+"?msg="+msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&gcv="+gcv+"&ncv="+ncv+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			dbcon.rollback();
			msg = "Customer Scheduling Failed !!!";
			e.printStackTrace();
			//HA20181221 url="../hcas/frm_cust_sch.jsp?msg="+msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&gcv="+gcv+"&ncv="+ncv+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+form_id+"&FormName="+form_nm;
			url=modUrl+"?msg="+msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&gcv="+gcv+"&ncv="+ncv+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
		}		
	}
	
	public void submitCustomerNom(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		methodName = "submitCustomerNom()";
		form_name = "frm_cust_nom.jsp";
		
		DecimalFormat df = new DecimalFormat("###");
		
		String gas_date = request.getParameter("gas_date")==null?"":request.getParameter("gas_date");
		String gen_date = request.getParameter("gen_date")==null?"":request.getParameter("gen_date");
		String gcv = request.getParameter("gcv")==null?"":request.getParameter("gcv");
		String ncv = request.getParameter("ncv")==null?"":request.getParameter("ncv");
		String subType = request.getParameter("subType")==null?"":request.getParameter("subType");//HA20200205
//		System.out.println("subType***"+subType);
		try
		{
			HttpSession session = request.getSession();
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");

			String [] chk_flag = request.getParameterValues("chk_flag");
			String [] buyer_cd = request.getParameterValues("buyer_cd");
			String hh_mm = request.getParameter("hh_mm")==null?"":request.getParameter("hh_mm");
			String [] buyer_plant_seq_no = request.getParameterValues("buyer_plant_seq_no");
			String [] qty_mmbtu = request.getParameterValues("qty_mmbtu");
			String [] qty_scm = request.getParameterValues("qty_scm");
			String [] buyer_mapping_id = request.getParameterValues("buyer_mapping_id");
			String [] buyer_sn_no = request.getParameterValues("buyer_sn_no");
			
			/* ********** HA20191227 to load total mmbtu of truck ************ */
			String chkBox [] = request.getParameterValues("chkBox");
			String chkLoadFlag [] = request.getParameterValues("chkLoadFlag");
			String load_mmbtu [] = request.getParameterValues("load_mmbtu");
			String arrival_time [] = request.getParameterValues("arrival_time");
			String arrival_date [] = request.getParameterValues("arrival_date");
			String loadtrucknm [] = request.getParameterValues("loadtrucknm");
			String remark [] = request.getParameterValues("remark");
			String Vtrans_cd [] = request.getParameterValues("Vtrans_cd");
			String next_avail_days[] = request.getParameterValues("next_avail_days"); //HA20201103
			String contract_type[] = request.getParameterValues("contract_type"); //HA20201121
			int insDtlCnt = 0 ;
			int insCnt = 0 ;
			double multiplying_factor = 0.252*1000000; 
			int deviding_factor = 1;
			
			double tot_truck_mmbtu =0;
			double tot_truck_ene = 0;
//			System.out.println("chkLoadFlag.length----"+chkLoadFlag.length+"----load_mmbtu--"+load_mmbtu.length);
			if(!subType.equals("noTruck")) { // if any truck selected
				for (int i = 0 ; i < chkLoadFlag.length ; i++) {
					if(chkLoadFlag[i].equals("Y")) {
//						System.out.println("chkLoadFlag[i]****"+chkLoadFlag[i]+"****"+load_mmbtu [i]);
						tot_truck_mmbtu+= Double.parseDouble(load_mmbtu [i]);
						tot_truck_ene+=  Math.round(Double.parseDouble(load_mmbtu[i]) * multiplying_factor / Double.parseDouble(gcv) * deviding_factor);
					}
				}
			df.format(tot_truck_mmbtu);
			df.format(tot_truck_ene);
		}
			/* *************************** */
			for(int i=0;i<chk_flag.length;i++)
			{
				int rev_no = 0;
				String prev_rev_no = "";
				
				if(chk_flag[i].equalsIgnoreCase("Y"))
				{
//					System.out.println("qty_mmbtu[i]*****"+qty_mmbtu[i]);
					queryString = "SELECT NVL(MAX(REV_NO),'0') FROM DLNG_DAILY_NOM WHERE MAPPING_ID='"+buyer_mapping_id[i]+"' AND "
							+ "NOM_ID='"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"' AND NOM_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY') AND "
							+ "PARTY_CD='"+buyer_cd[i]+"' AND CONTRACT_TYPE='"+contract_type[i]+"' ";
					System.out.println("queryString****"+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						rev_no = Integer.parseInt(rset.getString(1));
						prev_rev_no = rev_no+"";
						//System.out.println("DB rev_no : "+ rev_no);
						//System.out.println(rev_no +"="+ rev_no+"+"+ 1);
						rev_no = rev_no + 1;
						//System.out.println("rev_no : "+ rev_no);
					}
					else
					{
						prev_rev_no = rev_no+"";
						rev_no = rev_no + 1;
					}	
					if(subType.equals("noTruck")) {
						
						tot_truck_mmbtu = 0; // Double.parseDouble(qty_mmbtu[i].toString());
						tot_truck_ene = 0; // Double.parseDouble(qty_scm[i].toString());
						
						String dtlSql = "select distinct(TRUCK_NM) from DLNG_DAILY_TRUCK_NOM_DTL"
								+ " where NOM_ID = '"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"'"
								+ " and NOM_DT = TO_DATE('"+gas_date+"','DD/MM/YYYY')"
								+ " and REV_NO = '"+prev_rev_no+"'";
						System.out.println("dtlSql**"+dtlSql);
						rset = stmt.executeQuery(dtlSql);
						while (rset.next()) {
							
							String truck_name = rset.getString(1)==null?"":rset.getString(1);
							
							//Hiren_20230221 filtering TLU & Invoiced Trucks
							int alloc_cnt = 0 ;
							String allocCnt = "SELECT count(*) FROM DLNG_TANK_VOL_DTL WHERE "
									+ " SCH_ID like '"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"'"
									+ " AND TRN_NM='"+truck_name+"'"
									+ " AND EFF_DT = TO_DATE ('"+gas_date+"','DD/MM/YYYY')";
							rset1 = stmt1.executeQuery(allocCnt);
							if(rset1.next()) {
								alloc_cnt = rset1.getInt(1);
							}
							if(alloc_cnt == 0 ) {
								String queryString1 = "SELECT  nvl(A.TRANS_CD,'0') FROM DLNG_TANK_TRUCK_MST  A, DLNG_TRANS_MST B "
										+ " WHERE A.TRANS_CD=B.TRANS_CD AND A.STATUS ='Y' AND ( CUSTOMER_CD ='"+buyer_cd[i]+"' OR CUSTOMER_CD ='0' )"
										+ " AND TRUCK_NM = '"+truck_name+"'"
										+ " AND A.TRANS_CD IN "
										+ " (SELECT TRANS_CD FROM DLNG_CUST_TRANS_DTL WHERE"
											+ " CUST_CD ='"+buyer_cd[i]+"'"
											+ " AND FLAG='Y' "
											+ " AND TRUCK_NM = '"+truck_name+"')";
	//							System.out.println("iqueryString1**"+queryString1);
								rset1 = stmt1.executeQuery(queryString1);
								if(rset1.next()) {
									
									String transCd = rset1.getString(1)==null?"":rset1.getString(1);
									
									String truck_dtl = "INSERT INTO DLNG_DAILY_TRUCK_NOM_DTL (MAPPING_ID,NOM_ID,NOM_DT,REV_NO,TRUCK_NM,TRANS_CD,CONTRACT_TYPE,TRUCK_ENE,TRUCK_VOL,ENT_DT,ENT_BY)"
											+ " VALUES ('"+buyer_mapping_id[i]+"','"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"',TO_DATE('"+gas_date+"','DD/MM/YYYY'),'"+rev_no+"','"+truck_name+"','"+transCd+"','"+contract_type[i]+"','0','0',sysdate,'"+user_cd+"')";
//									System.out.println("insert truck_dtl ***11**"+truck_dtl);
									insDtlCnt+=stmt2.executeUpdate(truck_dtl);
								}
							}
						}
					}else {
//						System.out.println("chkLoadFlag.length****"+chkLoadFlag.length);
						for (int j = 0 ; j < chkLoadFlag.length ; j++) {
							
//							System.out.println("chkLoadFlag[j]****"+chkLoadFlag[j]);
							if(chkLoadFlag[j].equals("Y")) {
								
								double truck_ene =  Math.round(Double.parseDouble(load_mmbtu[j]) * multiplying_factor / Double.parseDouble(gcv) * deviding_factor);
								df.format(truck_ene);
								
								String truck_dtl = "INSERT INTO DLNG_DAILY_TRUCK_NOM_DTL (MAPPING_ID,NOM_ID,NOM_DT,REV_NO,ARRIVAL_DT,ARRIVAL_TIME,TRUCK_NM,TRUCK_VOL,UNIT_VOL,TRUCK_ENE,UNIT_ENE,REMARKS,TRANS_CD,NEXT_AVAIL_DAYS,CONTRACT_TYPE,ENT_DT,ENT_BY)"
										+ " VALUES ('"+buyer_mapping_id[i]+"','"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"',TO_DATE('"+gas_date+"','DD/MM/YYYY'),'"+rev_no+"',TO_DATE('"+arrival_date[j]+"','DD/MM/YYYY'),'"+arrival_time[j]+"','"+loadtrucknm[j]+"','"+load_mmbtu[j]+"','MMBTU','"+truck_ene+"','SCM','"+remark[j]+"','"+Vtrans_cd[j]+"','"+next_avail_days[j]+"','"+contract_type[i]+"',sysdate,'"+user_cd+"')";
//								System.out.println("insert truck_dtl *****"+truck_dtl);
								insDtlCnt+=stmt.executeUpdate(truck_dtl);
							}
						}
					}
//						System.out.println("contract_type[i]***"+contract_type[i]);
					queryString = "INSERT INTO DLNG_DAILY_NOM (MAPPING_ID,NOM_ID,REV_NO,PARTY_CD,SUP_TRN_CD,"
							+ "NOM_DT,GEN_DT,TIME_ST_DAY,NHV,GHV,DAY_VOL,UNIT_VOL,TOT_ENE,UNIT_ENE,CONTRACT_TYPE) "
							+ "VALUES ('"+buyer_mapping_id[i]+"','"+buyer_mapping_id[i]+"-"+buyer_sn_no[i]+"-"+buyer_plant_seq_no[i]+"','"+rev_no+"',"
							+ "'"+buyer_cd[i]+"','0',TO_DATE('"+gas_date+"','DD/MM/YYYY'),TO_DATE('"+gen_date+"','DD/MM/YYYY'),"
							+ "'"+hh_mm+"','"+ncv+"','"+gcv+"','"+tot_truck_mmbtu+"','MMBTU','"+tot_truck_ene+"','SCM','"+contract_type[i]+"') ";
							System.out.println("--Insert Query--"+queryString);
							insCnt+=stmt.executeUpdate(queryString);
					
				}
			}
			if(insCnt > 0 && insDtlCnt > 0) {
				dbcon.commit();
				msg = "Customer Nomination Successfully Done For The Selected Customers For Nomination DAY: "+gas_date+" !!!";
			}else {
				msg = "No Record Found of The Selected Customers For Nomination DAY: "+gas_date+" !!!";
			}
			url = "../master/frm_mst_buyerNomination.jsp?msg="+msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&gcv="+gcv+"&ncv="+ncv+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			dbcon.rollback();
			msg = "Customer Nomination Submission Failed !!!";
			e.printStackTrace();
			//HA20181221 url="../hcas/frm_cust_nom.jsp?msg="+msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&gcv="+gcv+"&ncv="+ncv+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+form_id+"&FormName="+form_nm;
			url="../master/frm_mst_buyerNomination.jsp?msg="+msg+"&gas_date="+gas_date+"&gen_date="+gen_date+"&gcv="+gcv+"&ncv="+ncv+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
		}		
	}//End Of Method submitDailyBuyerNomDetails() ...
}
