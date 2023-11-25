package com.seipl.hazira.dlng.util;
import java.sql.SQLException;

import javax.naming.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.*;

import com.seipl.hazira.dlng.util.EncryptTest;
import com.seipl.hazira.dlng.util.RuntimeConf;

import java.util.HashMap;
import java.util.Vector;
import java.io.File;
import java.net.InetAddress;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class DataBean_Annexure
{
	Connection conn;
	Statement stmt,stmt1,stmt2,stmt3,stmt4;
	ResultSet rset,rset1,rset2,rset3,rset4;
	String flag="";
	private String queryString ="";
	//RG20170207
	
	String emp_nm1="";
	String cd_user="";
	String dept_cd1="";
	String desg_nm1="";
	String dept_nm1="";
	String desg_cd1="";
	String line_mgr="";
	String comp_cd1="";
	String Semp_nm1="";
	Vector res_sup=new Vector();
	Vector res_sup_emp_cd=new Vector();
	String title_cd="";
	String Ssup_actdt="";
	String EXEC_LEAD_FLAG="";
	String COMM_ACTUAL_SAVINGS="";
	String EXEC_ACTUAL_SAVINGS="";
	String EXEC_BENEFICIAL_AMT="";
	String EXEC_BENEFICIAL_FLAG="";
	public String getEXEC_LEAD_FLAG() {
		return EXEC_LEAD_FLAG;
	}



	public void setEXEC_LEAD_FLAG(String eXEC_LEAD_FLAG) {
		EXEC_LEAD_FLAG = eXEC_LEAD_FLAG;
	}
	String impl_timeline="",target_close_dt="",actual_close_dt="";
	String title_nm="";
	String impl_by_days="";
	String impl_cost="";
	String impl_time_dt="";
	String sugg_desc="";
	String sugg_benefit="";
	String flag_sup="N";
	String hr_emp_cd="";
	String sugg_source="";
	String curr_sys_sts="";
	String sugg_desc_rtn="";
	String curr_sys_sts_rtn="";
	String sugg_brf_bene_rtn="";
	String sugg_brief="";
	String res_supervisor1="";
	String Ssupemp_nm1=""; 
	String line_mgrcd="";
	String flag_committee="";
	public String getFlag_committee() {
		return flag_committee;
	}



	public void setFlag_committee(String flag_committee) {
		this.flag_committee = flag_committee;
	}
	String user_cd="";
	String emp_nm="";
	String comp_cd="";
	String rmk_by_sup="";
	String act_by_sup="";
	String count_submit="";
	String count_impl="";String curyr="";
	String count_sup_rtn="";
	String count_assgn_hr="";
	String count_committee="";
	String cur_dt="";
	String st_dt1="";String end_dt1="";
	String mm="";
	String count_hr_impl="";
	String count_sup_pen="";
	String count_nonimpl="";
	String count_committee_pen="";
	String count_ET_Implementable="";
	String count_ET_pen="";
	String curdt="";
	String flag1="";
	String suggby="";
	String titcd="";
	String titnm="";
	String sup_cd="";
	String sup_nm="";
	Vector cnt_line_mgr_flag=new Vector();
	String set_dept_cd="";
	String set_desg_cd="";
	String  user_assign="";
	String emp_nm_assgn_user="";
	String res_supervisor="";
	String sugg_by_prnt="";String sysdt="";
	String venue="";String meeting_dt="";
	public HttpServletRequest request = null;
	String flag_track="";
	String flag1_rtn="";
	String flag_track1="";
	String search_char="";
	String Ssup_cd="";
	String pdfpath="";
	Vector alphabets=new Vector();
	String Search_char1="";
	String sysdt1="";
	String month="";
	String year="";
	String end_dt="";
	String st_dt="";
	String minyear="";
	String maxyear="";
	String cur_month="";
	String tit_cd_assgn_user="";
	String flag_respond="";
	String flag_respond1="";
	String flag_respond2="";
	int cnt=0;
	int days=0;String dt="";
	String enddt="";String categry_cd="";
	String pdfpath_hr="";
	String Ssugg_by="";
	int days1=0;
	String sugg_brf_bene="";
	String sup_sts="";
	String rev_flag="";
	String cost_reduce="";
	String estimated_save="";String categry_cd1="";
	String emp_alias_cd="";String emp_alias_cd1="";
	String tit_nm1="";
	String title_cd_upd="";
	String flag_upd="N";
	String title_nm_upd="";
	String sugg_by_upd="";
	Vector title_cd21=new Vector();
	Vector title_nm11=new Vector();
	Vector sugg_dt1=new Vector();
	Vector impl_by_days11=new Vector();
	Vector impl_time_dt11=new Vector();
	Vector sugg_by1=new Vector();
	Vector emp_nm_under_sup=new Vector();
	Vector action_sts=new Vector();
	Vector sts=new Vector();
	Vector action_sts_dt=new Vector();
	Vector action_sts1=new Vector();
	Vector status_sup=new Vector();
	Vector sup_action_dt=new Vector();
	Vector emp_cd=new Vector();
	Vector emp_nm2=new Vector();
	Vector title_cd2=new Vector();
	Vector title_nm1=new Vector();
	Vector sugg_dt=new Vector();
	Vector impl_by_days1=new Vector();
	Vector impl_time_dt1=new Vector();
	Vector sugg_by=new Vector();
	Vector sugg_nm=new Vector();
	Vector title_cd21_hr=new Vector();
	Vector title_nm11_hr=new Vector();
	Vector sugg_dt1_hr=new Vector();
	Vector impl_by_days11_hr=new Vector();
	Vector impl_time_dt11_hr=new Vector();
	Vector sugg_by1_hr=new Vector();
	Vector emp_nm_under_sup_hr=new Vector();
	Vector action_sts1_hr=new Vector();
	Vector meet_venue=new Vector();
	Vector status_sup_hr=new Vector();
	Vector status_hr=new Vector();
	Vector sel_sup_cd=new Vector();
	Vector dept_nm11=new Vector();
	Vector dept_cd11=new Vector();
	Vector desg_cd11=new Vector();
	Vector desg_nm11=new Vector();
	Vector emp_cd_hr=new Vector();
	Vector emp_nm_hr=new Vector();
	Vector emp_cd_assgn_by_hr=new Vector();
	Vector emp_nm_assgn_by_hr=new Vector();
	Vector title_cd_track=new Vector();
	Vector title_nm_track=new Vector();
	Vector sugg_dt1_track=new Vector();
	Vector sugg_by1_track=new Vector();
	Vector status_sup_track=new Vector();
	Vector status_sup_dt=new Vector();
	Vector status_hr_dt=new Vector();
	Vector status_hr_track=new Vector();
	Vector hr_act_dt=new Vector();
	Vector sup_act_dt=new Vector();
	
	Vector title_cd_for_pdf=new Vector();
	Vector title_nm_for_pdf=new Vector();
	Vector sugg_by_for_pdf=new Vector();
	Vector sup_action=new Vector();
	Vector sup_act_dt_pdf=new Vector();
	Vector hr_actsts_for_pdf=new Vector();
	Vector hr_dt_pdf=new Vector();
	Vector hr_act_pdf=new Vector();
	Vector voutcome_regd1=new Vector();
	Vector voutcome_regd_dt1=new Vector();
	Vector title_cd_rpt=new Vector();
	Vector sup_status=new Vector();
	public Vector getSup_status() {
		return sup_status;
	}



	public void setSup_status(Vector sup_status) {
		this.sup_status = sup_status;
	}
	Vector title_nm_rpt=new Vector();
	Vector sugg_by_rpt=new Vector();
	Vector sugg_bynm_rpt=new Vector();
	Vector sup_action_dt_rpt=new Vector();
	Vector hr_action_dt_rpt=new Vector();
	Vector committee_action_dt=new Vector();
	Vector sugg_dt_rpt=new Vector();
	Vector sup_cd_rpt=new Vector();
	Vector line_mgr_cd=new Vector();
	Vector committee_response=new Vector();
	
	Vector flg_sup=new Vector();
	Vector flg_hr=new Vector();
	Vector flg_cmt=new Vector();
	
	Vector empcd=new Vector();
	Vector empnm=new Vector();
	
	Vector Vtitle_cd=new Vector();
	Vector Vtitle_nm=new Vector();
	Vector Vsugg_dt=new Vector();
	Vector Vsugg_by_nm=new Vector();
	Vector Vcomp_cd=new Vector();
	Vector Vdept_cd=new Vector();
	Vector Vdesg_cd=new Vector();
	Vector Vsup_sts=new Vector();
	Vector Vsup_act=new Vector();
	Vector Vsup_actdt=new Vector();
	
	Vector Vhr_act=new Vector();
	Vector Vhr_actdt=new Vector();
	Vector Vcommittee=new Vector();
	Vector Vmonetary_save=new Vector();
	Vector Vimpl_timeline=new Vector();
	Vector Vtarget_close_dt=new Vector();
	Vector Vactual_close_dt=new Vector();
	Vector Vaward=new Vector();
	Vector VCommittee_descion_dt=new Vector();
	
	Vector action_hr=new Vector();
	Vector hr_action_dt=new Vector();
	Vector hr_action_sts_reason=new Vector();
	Vector OUTCOME_REGD=new Vector();
	Vector OUTCOME_REGD_DT=new Vector();
	Vector ACTION_DTL=new Vector(); 
	Vector ACTION_DT=new Vector();  
	Vector VERIFY_FLAG =new Vector();
	Vector VERIFY_DT=new Vector();
	//Vector Vactual_close_dt=new Vector();
	Vector REV_flag=new Vector();
	Vector pdf_hr=new Vector();
	Vector Vhr_emp_cd=new Vector();
	Vector Vcat_cd=new Vector();
	String exec_lead_dt="";
	Vector Vdesg_nm1=new Vector();
	Vector Vdept_nm1=new Vector(); 
	Vector VSup_by_nm=new Vector();
	Vector VSugg_by=new Vector();
	Vector VSup_cd=new Vector();
	Vector flg_w=new Vector();
	Vector flg_m=new Vector();
	Vector flg_f=new Vector();
	Vector flg_mm=new Vector();
	Vector flg_p=new Vector();
	Vector flg_w1=new Vector();
	Vector flg_m1=new Vector();
	Vector flg_f1=new Vector();
	Vector flg_mm1=new Vector();
	Vector flg_p1=new Vector();
	Vector flg_p2=new Vector();
	Vector flg_w2=new Vector();
	Vector flg_m2=new Vector();
	Vector flg_f2=new Vector();
	Vector flg_mm2=new Vector();
	
	//
	Vector flg_w11=new Vector();
	Vector flg_m11=new Vector();
	Vector flg_f11=new Vector();
	Vector flg_mm11=new Vector();
	
	Vector flg_w111=new Vector();
	Vector flg_m111=new Vector();
	Vector flg_f111=new Vector();
	Vector flg_mm111=new Vector();
	
	Vector flg_w1111=new Vector();
	Vector flg_m1111=new Vector();
	Vector flg_f1111=new Vector();
	Vector flg_mm1111=new Vector();
	
	Vector Vsuggby_als_cd=new Vector();
	String hr_emp_cd1="";
	Vector Vsugg=new Vector();
	
	Vector title_cd_assign=new Vector();
	Vector title_flg_committee=new Vector();
	Vector pdf=new Vector();
	Vector verify_flg=new Vector();
	/////////////////For Report of Suggestion Summary/////////////////
	Vector VSUGG_BENEFIT=new Vector();
	Vector VCOST_REDUCTION  =new Vector();
	Vector VESTIMATED_SAVING=new Vector();
	Vector VSUGG_BRIEF_BENEFIT =new Vector();
	Vector Vimpl_cost=new Vector();
	/////////////////////////////////////////////////
	//Committee
	Vector title_cd_COMM=new Vector();
	Vector title_NM_COMM=new Vector();
	Vector sugg_bynm_COMM=new Vector();
	Vector sugg_bycd_COMM=new Vector();
	Vector sugg_DT_COMM=new Vector();
	Vector sugg_commempcd_COMM=new Vector();
	Vector sugg_commempnm_COMM=new Vector();
	//Coordinator
	Vector title_cd_CO=new Vector();
	Vector title_NM_CO=new Vector();
	Vector sugg_bynm_CO=new Vector();
	Vector sugg_DT_CO=new Vector();
	Vector sugg_bycd_CO=new Vector();
	Vector sugg_commempcd_CO=new Vector();
	Vector sugg_commempnm_CO=new Vector();
	//Requester
	Vector title_cd_req=new Vector();
	Vector title_NM_req=new Vector();
	Vector sugg_bynm_req=new Vector();
	Vector sugg_DT_req=new Vector();
	Vector sugg_bycd_req=new Vector();
	Vector sugg_commempcd_req=new Vector();
	Vector sugg_commempnm_req=new Vector();
	//Line manger
	Vector title_cd_lm=new Vector();
	Vector title_NM_lm=new Vector();
	Vector sugg_bynm_lm=new Vector();
	Vector sugg_bycd_lm=new Vector();
	Vector sugg_DT_lm=new Vector();
	Vector sugg_commempcd_lm=new Vector();
	Vector sugg_commempnm_lm=new Vector();
	//Implementation lead
	Vector title_cd_lead=new Vector();
	Vector title_NM_lead=new Vector();
	Vector sugg_bynm_lead=new Vector();
	Vector sugg_dt_lead=new Vector();
	Vector sugg_bycd_lead=new Vector();
	Vector sugg_commempcd_lead=new Vector();
	Vector sugg_commempnm_lead=new Vector();
	
	
	public void init()
	 {
		//System.out.println(".............HERE...........");
		
		 try
			{
			 	try
				{
					Context initContext = new InitialContext();
					if(initContext == null ) 
						  throw new Exception("Boom - No Context");
					Context envContext  = (Context)initContext.lookup("java:/comp/env");
					DataSource ds = (DataSource)envContext.lookup(RuntimeConf.hr_database);
					if (ds != null)
					{
						 conn = ds.getConnection();       
					}
					else
					{
					  System.out.println("Data Source Not Found - Invoice Module Error");
					}
				}
				catch(Exception e)
				{
					System.out.println("Exception in DataBean_Training_Assessment.java..."+e);
					e.printStackTrace();
				}
				if(conn != null)
				{
					stmt = conn.createStatement();
					stmt1 = conn.createStatement();
					stmt2= conn.createStatement();
					stmt3=conn.createStatement();
					stmt4=conn.createStatement();
					if(flag.equals("AnnexureA"))       //RG20170207
					{
						AnnexureA();
					}
					else if(flag.equals("Sup_Annexure"))       //RG20170207
					{
					//SB	Sup_Annexure();
						Sup_AnnexureAll();
					}
					else if(flag.equals("Employee_list"))       //RG20170207
					{
						Employee_list();
					}
					else if(flag.equals("Employee_list_For_ExecutionLead"))       //RG20170908
					{
						Employee_list_For_ExecutionLead();
					}
					else if(flag.equals("Track_sugg"))       //RG20170207
					{
						Track_sugg();
					}
					else if(flag.equals("RequesterAction"))       //RG20170207
					{
						RequesterAction(); 
					}
					else if(flag.equals("CommitteeAction"))       //RG20170207
					{
						CommitteeAction();
					}
					else if(flag.equals("Print_sugg"))       //RG20170207
					{
						Print_sugg();
					}
					else if(flag.equals("Sugg_Moc"))       //RG20170207
					{
						Sugg_Moc();
					}
					else if(flag.equals("Add_Update_Supervisor"))       //RG20170207
					{
						Add_Update_Supervisor();
					}
					else if(flag.equals("Sugg_report"))       //RG20170207
					{
						Sugg_report();
					}
					else if(flag.equals("Sugg_report2"))       //RG20170207
					{
						Sugg_report2();
					}
					else if(flag.equals("AgingReport_Details"))       //RG20170207
					{
						AgingReport_Details();
					}
					else if(flag.equals("Empwise_Summary"))       //RG20170207
					{
						Empwise_Summary();
					}
					else if(flag.equals("Savings"))       //RG20170207
					{
						Savings();
					}
					else if(flag.equals("Suggestion_Dtls_For_ExecutionLead"))       //RG20170207
					{
						Suggestion_Dtls_For_ExecutionLead();
					}
					else if(flag.equals("Suggestion_Dtls_Summary"))       //RG20170207
					{
						Suggestion_Dtls_Summary();
					}
					else if(flag.equals("Archived_Suggestions"))
					{
						Archived_Suggestions();
					}
					else if(flag.equals("Fetch_Pending_Sugg_Data"))
					{
						Fetch_Pending_Sugg_Data();
					}
					
					
				conn.close();
				conn = null;
				}
		}
		catch(Exception e)
		{
				System.out.println("Exception whilst closing the connection in the DataBean_Annexure.java..."+e);
				e.printStackTrace();
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
						System.out.println("rset is not close "+e);
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
						System.out.println("rset1 is not close "+e);
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
						System.out.println("rset2 is not close "+e);
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
						System.out.println("rset3 is not close "+e);
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
						System.out.println("rset4 is not close "+e);
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
						System.out.println("stmt is not close "+e);
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
						System.out.println("stmt1 is not close "+e);
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
						System.out.println("stmt2 is not close "+e);
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
						System.out.println("stmt3 is not close "+e);
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
						System.out.println("stmt4 is not close "+e);
					}
				}
				if(conn != null)
				{
					try
					{
						conn.close();
					}
					catch(SQLException e)
					{
						System.out.println("conn is not close "+e);
					}
				}
		    }
   	  }//END OF INIT()
	
	
	
//	RG20160207 for annexure 
	
	public void AnnexureA() throws SQLException
	{
		try
		{
			//System.out.println("hereeeeee");
			String branch_cd="1";
			
			String q112="select title_nm from sugg_title_mst where title_cd='"+titcd+"'";
			//System.out.println("q1--"+q112);
			rset=stmt.executeQuery(q112);
            if(rset.next())
			{
            	tit_nm1=rset.getString(1)==null?"":rset.getString(1);
			}
			
			String q1="select nvl(emp_nm,''),nvl(comp_cd,''),emp_alias_cd from hr_emp_mst where emp_cd='"+cd_user+"'";
			//System.out.println("q1--"+q1);
			rset=stmt.executeQuery(q1);
            if(rset.next())
			{
            	emp_nm1=rset.getString(1)==null?"":rset.getString(1);
            	comp_cd1=rset.getString(2)==null?"":rset.getString(2);
            	emp_alias_cd=rset.getString(3)==null?"":rset.getString(3);
			}
            
            String query110 ="select a.reporting from hr_emp_rel_mst a where "+
            		//+ "a.comp_cd='"+comp_cd1+"' and " +
    		" a.emp_cd='"+cd_user+"' order by reporting    ";
			    //System.out.println("query11--"+query110);
			rset=stmt.executeQuery(query110);
			while (rset.next())
			{
				//dept_nm1=rset.getString(1);
				//dept_cd1=rset.getString(2);
				line_mgr=rset.getString(1)==null?"":rset.getString(1);
			}
            
            String query11 ="select distinct(b.dept_nm),a.dept_cd from hr_emp_rel_mst a, " +
            		"hr_dept_mst b where a.dept_cd=b.dept_cd " +
            	//	+ "and b.comp_cd='"+comp_cd1+"' " +
            		"and a.emp_cd='"+cd_user+"' order by dept_nm    ";
           // System.out.println("query11--"+query11);
		rset=stmt.executeQuery(query11);
		while (rset.next())
		{
			dept_nm1=rset.getString(1)==null?"":rset.getString(1);
			dept_cd1=rset.getString(2)==null?"":rset.getString(2);
			//line_mgr=rset.getString(3);
		}
		
		String qury="select distinct(d.desg_cd),d.desg_nm from hr_emp_rel_mst a," +
				"hr_dept_mst b, hr_desg_mst d,hr_emp_mst e " +
				"where a.dept_cd=b.dept_cd and  b.dept_cd='"+dept_cd1+"' and " +
				" a.desg_cd=d.desg_cd and e.emp_cd='"+cd_user+"' "+ 
				//and b.comp_cd='"+comp_cd1+"' " +
				"and a.emp_cd=e.emp_cd  order by desg_nm";
		//System.out.println("qury000---"+qury);
		rset=stmt.executeQuery(qury);
		while (rset.next())
		{
			desg_cd1=rset.getString(1)==null?"":rset.getString(1);
			desg_nm1=rset.getString(2)==null?"":rset.getString(2);
		
		}
		String query101="  select emptyp_cd from hr_emptyp_mst where (lower(emptyp_nm) LIKE '%sahyog supervisor%'" +
				" or lower(emptyp_nm) LIKE 'sahayog supervisor%' ) ";
//		System.out.println("----qq------"+query101);
		rset=stmt.executeQuery(query101);
		if(rset.next())
		{
			categry_cd1=rset.getString(1)==null?"":rset.getString(1);
		}
		String query102="SELECT A.EMP_CD,A.CATEGRY_CD,C.EMP_NM FROM TIMS_EMP_CATG_DTL A,HR_EMP_MST C WHERE " +
				"A.EFF_DT = (SELECT MAX(B.EFF_DT) FROM TIMS_EMP_CATG_DTL B WHERE " +
				" A.EMP_CD=B.EMP_CD AND A.CATEGRY_CD=B.CATEGRY_CD AND A.COMP_CD=B.COMP_CD ) AND C.EMP_STATUS!='N' AND A.EMP_CD=C.EMP_CD AND A.CATEGRY_CD='"+categry_cd1+"' order by A.EMP_CD DESC ";
		System.out.println("-qq-"+query102);
		rset=stmt.executeQuery(query102);
		while(rset.next())
		{
			res_sup_emp_cd.add(rset.getString(1)==null?"":rset.getString(1));
			res_sup.add(rset.getString(3)==null?"":rset.getString(3));
		}
		/*for(int r=0;r<res_sup_emp_cd.size();r++){
			String query103="select nvl(emp_nm,'') from hr_emp_mst  where emp_cd='"+res_sup_emp_cd.elementAt(r)+"' order by emp_nm ";
			System.out.println("-qq-"+query103);
			rset=stmt.executeQuery(query103);
			if(rset.next())
			{
				res_sup.add(rset.getString(1)==null?"":rset.getString(1));
			}
			else{
				res_sup.add("");
			}
		}*/
		
		//System.out.println("res_sup--"+res_sup.size()+"--"+res_sup);
			String query3="select a.title_cd, a.title_nm, b.impl_by_days, b.impl_cost," +
					" to_char(a.impl_time_dt,'dd/mm/yyyy'), b.sugg_desc, b.sugg_benefit,b.sugg_brief_benefit  from  sugg_title_mst a ," +
					"sugg_title_dtl b where a.title_cd= b.title_cd and b.flag='Y' and a.sugg_by='"+cd_user+"'  ";
			rset=stmt.executeQuery(query3);
//			System.out.println("query3- here-"+query3);
			while (rset.next())
			{
				title_cd=rset.getString(1);
				title_nm=rset.getString(2)==null?"":rset.getString(2);
				impl_by_days=rset.getString(3)==null?"":rset.getString(3);
				impl_cost=rset.getString(4)==null?"":rset.getString(4);
				impl_time_dt=rset.getString(5)==null?"":rset.getString(5);
				sugg_desc=rset.getString(6)==null?"":rset.getString(6);
				sugg_benefit=rset.getString(7)==null?"":rset.getString(7);
				sugg_brief=rset.getString(8)==null?"":rset.getString(8);
			}
			
			if(flag1.equalsIgnoreCase("V")){
				//System.out.println("inside if");
				String q11="select nvl(emp_nm,''),comp_cd,emp_alias_cd from hr_emp_mst where emp_cd='"+suggby+"'";
				//System.out.println("inside "+q11);
				rset=stmt.executeQuery(q11);
	            if(rset.next())
				{
	            	emp_nm1=rset.getString(1)==null?"":rset.getString(1);
	            	comp_cd1=rset.getString(2)==null?"":rset.getString(2);
	            	emp_alias_cd1=rset.getString(3)==null?"":rset.getString(3);
				}
	            String query112 ="select a.reporting from hr_emp_rel_mst a where "
//	            		+ "a.comp_cd='"+comp_cd1+"' and "
	            				+ "a.emp_cd='"+suggby+"' order by reporting    ";
				    //System.out.println("query11--"+query112);
				rset=stmt.executeQuery(query112);
				while (rset.next())
				{
					//dept_nm1=rset.getString(1);
					//dept_cd1=rset.getString(2);
					line_mgr=rset.getString(1)==null?"":rset.getString(1);
				}
				dept_nm1="";dept_cd1="";desg_cd1="";desg_nm1="";
	            String query12 ="select distinct(b.dept_nm),a.dept_cd from hr_emp_rel_mst a, " +
	            		"hr_dept_mst b where a.dept_cd=b.dept_cd  "+
	            		//and b.comp_cd='"+comp_cd1+"' " +
	            		"and a.emp_cd='"+suggby+"' order by dept_nm    ";
	            //System.out.println("query11--"+query12);
			rset=stmt.executeQuery(query12);
			while (rset.next())
			{
				dept_nm1=rset.getString(1)==null?"":rset.getString(1);
				dept_cd1=rset.getString(2)==null?"":rset.getString(2);
			//	line_mgr=rset.getString(3);
			}
			
			String qury2="select distinct(d.desg_cd),d.desg_nm from hr_emp_rel_mst a," +
					"hr_dept_mst b, hr_desg_mst d,hr_emp_mst e " +
					"where a.dept_cd=b.dept_cd and  b.dept_cd='"+dept_cd1+"' and " +
					" a.desg_cd=d.desg_cd and e.emp_cd='"+suggby+"' "+
					//and b.comp_cd='"+comp_cd1+"' " +
					"and a.emp_cd=e.emp_cd  order by desg_nm";
			//System.out.println("qury000---"+qury2);
			rset=stmt.executeQuery(qury2);
			while (rset.next())
			{
				desg_cd1=rset.getString(1)==null?"":rset.getString(1);
				desg_nm1=rset.getString(2)==null?"":rset.getString(2);
			
			}
				if(!flag_track.equalsIgnoreCase("T")){
					String query4="select a.title_cd, a.title_nm, b.impl_by_days, b.impl_cost," +
					" to_char(a.impl_time_dt,'dd/mm/yyyy'), b.sugg_desc, b.sugg_benefit,a.supervisor_cd,a.remark_by_supervisor,"
					+ "a.action_by_supervisor ,b.sugg_brief_benefit,a.status,b.rev_flag,b.cost_reduction,b.estimated_saving,"
					+ "hr_action_status_reason,OUTCOME_REGD, to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss'),"
					+ "to_char(a.committee_decision_dt,'dd/mm/yyyy hh24:mi:ss') ,BENEFICIAL_AMOUNT,BENEFICIAL_FLAG,comm_reco,ACTION_FLAG,"
					+ "VERIFY_FLAG,a.impl_cost,EXEC_LEAD,moc_flag,moc_rmk,to_char(a.action_dt,'dd/mm/yyyy hh24:mi:ss'),action_dtl from  sugg_title_mst a ," +
					"sugg_title_dtl b where a.title_cd= b.title_cd and b.flag='Y' and a.sugg_by='"+suggby+"' and a.title_cd='"+titcd+"'  ";
					rset=stmt.executeQuery(query4);
					//System.out.println("query4- here-"+query4);
				}
				else {
					String query4="select a.title_cd, a.title_nm, b.impl_by_days, b.impl_cost," +
					" to_char(a.impl_time_dt,'dd/mm/yyyy'), b.sugg_desc, b.sugg_benefit,a.supervisor_cd,a.remark_by_supervisor,"
					+ "a.action_by_supervisor,b.sugg_brief_benefit,a.status,b.rev_flag,b.cost_reduction,b.estimated_saving"
					+ ",hr_action_status_reason,OUTCOME_REGD, to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss'),"
					+ "to_char(a.committee_decision_dt,'dd/mm/yyyy hh24:mi:ss') ,BENEFICIAL_AMOUNT,BENEFICIAL_FLAG,"
					+ "comm_reco,ACTION_FLAG,VERIFY_FLAG,a.impl_cost,EXEC_LEAD,moc_flag,moc_rmk,to_char(a.action_dt,'dd/mm/yyyy hh24:mi:ss'),action_dtl from  sugg_title_mst a ," +
					"sugg_title_dtl b where a.title_cd= b.title_cd and b.flag='Y' and a.sugg_by='"+suggby+"' and a.title_cd='"+titcd+"' ";
					rset=stmt.executeQuery(query4);
					//System.out.println("query4- here-"+query4);
				}
				while (rset.next())
				{
					title_cd=rset.getString(1)==null?"":rset.getString(1);
					title_nm=rset.getString(2)==null?"":rset.getString(2);
					impl_by_days=rset.getString(3)==null?"":rset.getString(3);
					impl_cost=rset.getString(4)==null?"":rset.getString(4);
					impl_time_dt=rset.getString(5)==null?"":rset.getString(5);
					sugg_desc=rset.getString(6)==null?"":rset.getString(6);
					sugg_benefit=rset.getString(7)==null?"":rset.getString(7);
					sup_cd=rset.getString(8)==null?"":rset.getString(8);
					rmk_by_sup=rset.getString(9)==null?"":rset.getString(9);
					act_by_sup=rset.getString(10)==null?"":rset.getString(10);
					sugg_brf_bene=rset.getString(11)==null?"":rset.getString(11);
					sup_sts=rset.getString(12)==null?"":rset.getString(12);
					rev_flag=rset.getString(13)==null?"":rset.getString(13);
					cost_reduce=rset.getString(14)==null?"":rset.getString(14);
					estimated_save=rset.getString(15)==null?"":rset.getString(15);
					hr_action_status=rset.getString(16)==null?"":rset.getString(16);
					outcome_regd=rset.getString(17)==null?"":rset.getString(17);
					outcome_regd_dt=rset.getString(18)==null?"":rset.getString(18);
					COMMITTEE_DECIDE_DT=rset.getString(19)==null?"":rset.getString(19);
					ben_amt=rset.getString(20)==null?"":rset.getString(20);
					bene_flg=rset.getString(21)==null?"":rset.getString(21);
					committee_reco=rset.getString(22)==null?"":rset.getString(22);
					act_flg=rset.getString(23)==null?"":rset.getString(23);
					Ver_flg=rset.getString(24)==null?"":rset.getString(24);
					awd_amt=rset.getString(25)==null?"":rset.getString(25);
					EXEC_LEAD_CD=rset.getString(26)==null?"":rset.getString(26);
					moc_flag=rset.getString(27)==null?"":rset.getString(27);
					moc_rmk=rset.getString(28)==null?"":rset.getString(28);
					Actdt=rset.getString(29)==null?"":rset.getString(29);
					act_rmk=rset.getString(30)==null?"":rset.getString(30);
					
				}
				String query5="Select nvl(emp_nm,'') from hr_emp_mst where emp_cd='"+sup_cd+"' ";
//				System.out.println("---"+query5);
				rset=stmt.executeQuery(query5);
				if(rset.next())
				{
					sup_nm=rset.getString(1)==null?"":rset.getString(1);
				}
				  String q3="select nvl(emp_nm,'') from hr_emp_mst where emp_cd='"+EXEC_LEAD_CD+"'";
			        rset=stmt.executeQuery(q3);
			        if(rset.next())
					{
			        	Exec_lead_emp_nm=rset.getString(1)==null?"":rset.getString(1);
					}
				  String AssignedUser="";
				     String  query="select A.EMP_CD, B.EMP_NM from SUGG_ASSIGN_DTL A, HR_EMP_MST B where A.TITLE_CD='"+titcd+"' AND A.EMP_CD=B.EMP_CD order by A.emp_cd ";
				        //System.out.println("ASSIGNED MEMBERS: "+query);
						rset=stmt.executeQuery(query);
				       while(rset.next())
						{
				    	   queryString="select emp_cd from sugg_email_dtl where title_cd='"+titcd+"' and emp_cd='"+rset.getString(1)+"'";
				    	   rset1=stmt1.executeQuery(queryString);
				    	   if(rset1.next()){
				    		 	AssignedUser +="<font color='blue' title='Mail Sent for committee response'>"+rset.getString(2)+"</font>, ";
				    	   }else{
				    		 	AssignedUser +=rset.getString(2)+", ";
				    	   }
				      
						}
				       committee_members1 = AssignedUser;
				       
				       ////////////////// PDF File Creation////////////////////
						String f1="";
						String f2="";
						String path=request.getRealPath("/PDF");
						f2="SUGGESTION-"+titcd+".pdf";
						f1="SUGGESTION-"+titcd;
						f1=path+"/"+f1;
						File pdf_file=new File(f1+".pdf");
						if(pdf_file.exists())
						{
								path=path+"//"+pdf_file;
					            String context_nm = request.getContextPath();
								String server_nm = request.getServerName();
								String server_port = ""+request.getServerPort();
								String url_start = "http://"+server_nm+":"+server_port+context_nm;
								 pdfpath = path;
								pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
								pdfpath = url_start+"/PDF/"+f2;
						}
				       
			}
		
			
			
			//System.out.println("curr_sys_sts_rtn--"+curr_sys_sts_rtn);
 	}
		catch(SQLException e)
		{
			System.err.println("Error"+e);
			System.err.println("..DataBean_TIMS.java.in Query :.."+queryString);
			e.printStackTrace();
		}
	     
	}//end of //RG20170207
	String count_sup_recvd="",count_sup_potential="",count_ET_rej="";
	



	public void Sugg_report() throws SQLException
	{
		try
		{
			
			String query="select TO_CHAR(sysdate,'YYYY'),to_char(sysdate,'dd/mm/yyyy') from DUAL ";
			rset=stmt.executeQuery(query);
				if(rset.next())
				{
					curyr=rset.getString(1);
					curdt=rset.getString(2);
				}
				if(!curyr.equalsIgnoreCase("")){
				st_dt="01/01/"+curyr;
				end_dt=curdt;
				
//				queryString="Select count(*) from sugg_title_mst where status='RETURN' and (sugg_dt >=" +
//				"to_date('"+st_dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+end_dt+"','dd/mm/yyyy')+1) ";
				
				queryString="Select count(*) from sugg_title_mst where status='RETURN' and (sugg_dt <= to_date('"+end_dt+"','dd/mm/yyyy')+1) ";
				System.out.println("query2--------"+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					count_sup_rtn=rset.getString(1);
				}
				
				queryString="Select count(*) from sugg_title_mst where sugg_dt is not null and (sugg_dt <= to_date('"+end_dt+"','dd/mm/yyyy')+1)  ";
				System.out.println("query2--------"+queryString);
					rset=stmt.executeQuery(queryString);
					if(rset.next())
					{
						count_sup_recvd=rset.getString(1);
					}
				
//				queryString="Select count(*) from sugg_title_mst where action_by_supervisor='Implementable' "
//							+ " and (sugg_dt >=to_date('"+st_dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+end_dt+"','dd/mm/yyyy')+1) ";
					queryString="Select count(*) from sugg_title_mst where action_by_supervisor='Implementable' and status='FORWARD' "
							+ " and (sugg_dt <= to_date('"+end_dt+"','dd/mm/yyyy')+1) ";
				System.out.println("queryString--------"+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					count_sup_potential=rset.getString(1);
				}
				
//				queryString="Select count(*) from sugg_title_mst where (action_by_supervisor is null or action_by_supervisor='' or status is null or status='') and (sugg_dt >=" +
//				"to_date('"+st_dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+end_dt+"','dd/mm/yyyy')+1) ";
////				System.out.println("query2--------"+queryString);
//				rset=stmt.executeQuery(queryString);
//				if(rset.next())
//				{
//					count_sup_pen=rset.getString(1);
//				}

				
	//			String query4="Select count(*) from sugg_title_mst where outcome_regd='IMPLEMENTABLE' and to_char(outcome_regd_dt,'dd-mon-yy') = to_date('"+cur_dt+"','dd-mon-yy')  ";
//				queryString="Select count(*) from sugg_title_mst where outcome_regd='IMPLEMENTABLE' and status='FORWARD' "
//							+ "and HR_ACTION_STATUS='ASSIGNED' and (sugg_dt >=" +
//							"to_date('"+st_dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+end_dt+"','dd/mm/yyyy')+1)   ";
				queryString="Select count(*) from sugg_title_mst where outcome_regd='IMPLEMENTABLE' and status='FORWARD' "
						+ "and HR_ACTION_STATUS='ASSIGNED' and (sugg_dt <= to_date('"+end_dt+"','dd/mm/yyyy')+1)   ";
//				System.out.println("queryString---"+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					count_hr_impl=rset.getString(1);
				}
//				queryString="Select count(*) from sugg_title_mst where outcome_regd='NONIMPLEMENTABLE' and status='FORWARD'"
//							+ " and HR_ACTION_STATUS='ASSIGNED' and (sugg_dt >=" +
//				"to_date('"+st_dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+end_dt+"','dd/mm/yyyy')+1)   ";
				queryString="Select count(*) from sugg_title_mst where outcome_regd='NONIMPLEMENTABLE' and status='FORWARD'"
						+ " and HR_ACTION_STATUS='ASSIGNED' and (sugg_dt <= to_date('"+end_dt+"','dd/mm/yyyy')+1)   ";
//				System.out.println("queryString---"+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					count_nonimpl=rset.getString(1);
				}
//				queryString="Select count(*) from sugg_title_mst where (outcome_regd='' or outcome_regd is null) and  (sugg_dt >=" +
//				"to_date('"+st_dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+end_dt+"','dd/mm/yyyy')+1) and status='FORWARD'  and HR_ACTION_STATUS='ASSIGNED' ";
				queryString="Select count(*) from sugg_title_mst where (outcome_regd='' or outcome_regd is null) and  (sugg_dt <= to_date('"+end_dt+"','dd/mm/yyyy')+1) and status='FORWARD'  and HR_ACTION_STATUS='ASSIGNED' ";
				//System.out.println("queryString---"+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					count_committee_pen=rset.getString(1);
				}
//				queryString="Select count(*) from sugg_title_mst where actual_closer_dt is not null and (outcome_regd='IMPLEMENTABLE' and exec_lead is not null) and  (sugg_dt >=" +
//							"to_date('"+st_dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+end_dt+"','dd/mm/yyyy')+1)";
				queryString="Select count(*) from sugg_title_mst where actual_closer_dt is not null and impl_timeline is not null or exec_lead_decision_flag='I' and (outcome_regd='IMPLEMENTABLE' and exec_lead is not null) and  (sugg_dt <= to_date('"+end_dt+"','dd/mm/yyyy')+1)";
				System.out.println("query4---"+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					count_ET_Implementable=rset.getString(1);
				}
//				queryString="Select count(*) from sugg_title_mst where actual_closer_dt is null and (outcome_regd='IMPLEMENTABLE' and exec_lead is not null) and  (sugg_dt >=" +
//						"to_date('"+st_dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+end_dt+"','dd/mm/yyyy')+1) ";
				queryString="Select count(*) from sugg_title_mst where actual_closer_dt is null and (outcome_regd='IMPLEMENTABLE' and exec_lead is not null) and  (sugg_dt <= to_date('"+end_dt+"','dd/mm/yyyy')+1) ";
//				System.out.println("query4---"+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					count_ET_pen=rset.getString(1);
				}
//				queryString="Select count(*) from sugg_title_mst where actual_closer_dt is not null and exec_lead_decision_flag='R' and (outcome_regd='IMPLEMENTABLE' and exec_lead is not null) and  (sugg_dt >=" +
//						"to_date('"+st_dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+end_dt+"','dd/mm/yyyy')+1) ";
				queryString="Select count(*) from sugg_title_mst where actual_closer_dt is not null and exec_lead_decision_flag='R' and (outcome_regd='IMPLEMENTABLE' and exec_lead is not null) and  (sugg_dt <= to_date('"+end_dt+"','dd/mm/yyyy')+1) ";
//				System.out.println("query4---"+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					count_ET_rej=rset.getString(1);
				}
				
			}
		}
		catch(SQLException e)
		{
			System.err.println("Error"+e);
			System.err.println("..DataBean_TIMS.java.in Query :.."+queryString);
			e.printStackTrace();
		}
	     
	}//end of //RG20170207
	String est_saving="",requester_pending=""; String actual_saving="",actual_saving_closed="",LEAD_pending="";
	
	String diff;NumberFormat nf = new DecimalFormat("###########0.00");
	Vector Vsugg_by=new Vector();Vector Vest_save_empwise=new Vector();Vector Vact_save_empwise=new Vector();Vector Vsugg_bynm=new Vector();
	Vector Vsugg_byid=new Vector();double diff_saving=0;Vector Vdept_nm11=new Vector();Vector Vdesg_nm11=new Vector();Vector Vdept_cd1=new Vector();
	Vector Vdesg_cd1=new Vector();Vector Vcomp_cd1=new Vector();
	Vector titlecdsz=new Vector();Vector implsz=new Vector();Vector pendingsz=new Vector();public Vector getTitlecdsz() {
		return titlecdsz;
	}
	public void setTitlecdsz(Vector titlecdsz) {
		this.titlecdsz = titlecdsz;
	}
	public Vector getImplsz() {
		return implsz;
	}
	public void setImplsz(Vector implsz) {
		this.implsz = implsz;
	}
	public Vector getNonimplsz() {
		return nonimplsz;
	}
	public void setNonimplsz(Vector nonimplsz) {
		this.nonimplsz = nonimplsz;
	}
	public Vector getPendingsz() {
		return pendingsz;
	}



	Vector nonimplsz=new Vector();
	public Vector getVsugg_byid() {
		return Vsugg_byid;
	}
	public void setVsugg_byid(Vector vsugg_byid) {
		Vsugg_byid = vsugg_byid;
	}



	String difference=""; Vector diff1=new Vector();
	public Vector getDiff1() {
		return diff1;
	}
	int q=0;int tot=0;public int getQ() {
		return q;
	}
	public void setQ(int q) {
		this.q = q;
	}
	public int getNt() {
		return nt;
	}
	public void setNt(int nt) {
		this.nt = nt;
	}
	public int getTot() {
		return tot;
	}



	int nt=0;
	public void Savings() throws SQLException
	{
		try
		{
			
//			String query="select TO_CHAR(sysdate,'YYYY'),to_char(sysdate,'dd/mm/yyyy') from DUAL ";
//			rset=stmt.executeQuery(query);
//				if(rset.next())
//				{
//					curyr=rset.getString(1);
//					curdt=rset.getString(2);
//				}
//				if(!curyr.equalsIgnoreCase("")){
//				st_dt="01/01/"+curyr;
//				end_dt=curdt;
			
			String query10=" select min(to_char(sugg_dt,'yyyy')),max(to_char(sugg_dt,'yyyy')) from sugg_title_mst ";
			rset=stmt.executeQuery(query10);
				if(rset.next())
				{
					minyear=rset.getString(1);
					maxyear=rset.getString(2);
				}
				
			if(!year.equalsIgnoreCase("0")){
			st_dt="01/01/"+year;
			String query="select TO_CHAR(sysdate,'DD/MM/YYYY'),TO_CHAR(sysdate,'YYYY') from DUAL ";
			rset=stmt.executeQuery(query);
				if(rset.next())
				{
					end_dt=rset.getString(1);
					curyr=rset.getString(2);
				}
				if(dt.equals("")) {
//					dt = st_dt;
//					enddt = end_dt;
					
					if(year.equals(curyr)){
						//System.out.println("-sdsd-if-");
						st_dt="01/01/"+curyr;
						dt = st_dt;
						enddt = end_dt;
					}else{
						//System.out.println("-sdsd-else-");
						end_dt="31/12/"+year;
						dt = st_dt;
						enddt = end_dt;
					}
				}
				
				String query212="Select sum(a.estimated_saving) from sugg_title_dtl a,sugg_title_mst b where (b.sugg_dt >=" +
				"to_date('"+dt+"','dd/mm/yyyy') AND b.sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1) and a.flag='Y' and a.title_cd=b.title_cd order by a.title_cd  ";
//				System.out.println("query2--------"+query212);
				rset=stmt.executeQuery(query212);
				if(rset.next())
				{
					est_saving=rset.getString(1)==null?"":rset.getString(1);
				}
				String query2121="Select sum(beneficial_amount) from sugg_title_mst  where (sugg_dt >=" +
				"to_date('"+dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1) and outcome_regd='IMPLEMENTABLE' ";
//				System.out.println("query2--------"+query2121);
				rset=stmt.executeQuery(query2121);
				if(rset.next())
				{
					actual_saving=rset.getString(1)==null?"":rset.getString(1);
				}
				if(!est_saving.equalsIgnoreCase("") && (!actual_saving.equalsIgnoreCase(""))) {
					diff=nf.format((Double.parseDouble(est_saving)- Double.parseDouble(actual_saving)));
				}
				else{
					if(!est_saving.equalsIgnoreCase("") && (actual_saving.equalsIgnoreCase("")))
					{
						diff=nf.format(Double.parseDouble(est_saving));
					}
					else{
						diff="0";
					}
				}
				//System.out.println("--diff--"+diff);
			 
				String query2="Select distinct(sugg_by),b.emp_nm from sugg_title_mst a,hr_emp_mst b where (sugg_dt >=  " +
					"to_date('"+dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1) and a.sugg_by=b.emp_cd order by b.emp_nm ";	
				//System.out.println("query2---"+query2);
				rset=stmt.executeQuery(query2);
				while(rset.next())
				{
					Vsugg_by.add(rset.getString(1)==null?"":rset.getString(1));
				}
				for(int i=0;i<Vsugg_by.size();i++){
					String query11="select emp_nm,emp_alias_cd,comp_cd from hr_emp_mst where emp_cd='"+Vsugg_by.elementAt(i)+"' order by emp_nm ";
					rset=stmt.executeQuery(query11);
					if(rset.next())
					{
						Vsugg_bynm.add(rset.getString(1)==null?"":rset.getString(1));
						Vsugg_byid.add(rset.getString(2)==null?"":rset.getString(2));
						Vcomp_cd1.add(rset.getString(3)==null?"":rset.getString(3));
					}else{
						Vsugg_bynm.add("");
						Vsugg_byid.add("");
						Vcomp_cd1.add("");
					}
					
					 String query111 ="select distinct(b.dept_nm),a.dept_cd from hr_emp_rel_mst a, " +
			            		"hr_dept_mst b where a.dept_cd=b.dept_cd "+
			            	//	+ "and b.comp_cd='"+Vcomp_cd1.elementAt(i)+"' " +
			            		"and a.emp_cd='"+Vsugg_by.elementAt(i)+"' order by dept_nm    ";
			           // System.out.println("query11--"+query111);
					rset=stmt.executeQuery(query111);
					if (rset.next())
					{
						Vdept_nm11.add(rset.getString(1)==null?"-":rset.getString(1));
						Vdept_cd1.add(rset.getString(2)==null?"":rset.getString(2));
					}
					else{
						Vdept_nm11.add("-");
						Vdept_cd1.add("");
					}
					
					String qury="select distinct(d.desg_cd),d.desg_nm from hr_emp_rel_mst a," +
							"hr_dept_mst b, hr_desg_mst d,hr_emp_mst e " +
							"where a.dept_cd=b.dept_cd and  b.dept_cd='"+Vdept_cd1.elementAt(i)+"' and " +
							" a.desg_cd=d.desg_cd and e.emp_cd='"+Vsugg_by.elementAt(i)+"'  " +
							"and a.emp_cd=e.emp_cd  order by desg_nm";
					//System.out.println("qury000---"+qury);
					rset=stmt.executeQuery(qury);
					if (rset.next())
					{
						Vdesg_nm11.add(rset.getString(2)==null?"-":rset.getString(2));
					
					}
					else{
						Vdesg_nm11.add("-");
					}
					
					String query3="Select sum(a.estimated_saving) from sugg_title_dtl a , sugg_title_mst b " +
							"where a.title_cd=b.title_cd and " +
							"b.sugg_by='"+Vsugg_by.elementAt(i)+"' and (b.sugg_dt >=  " +
							"to_date('"+dt+"','dd/mm/yyyy') AND b.sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1) and a.flag='Y' ";
					//System.out.println("---query3----"+query3);
					rset=stmt.executeQuery(query3);
					if(rset.next())
					{
						Vest_save_empwise.add(rset.getString(1)==null?"-":rset.getString(1));
					}
					else{
						Vest_save_empwise.add("-");
					}
					//System.out.println("-----Vest_save_empwise--"+Vest_save_empwise);
					
					String query31="Select sum(b.beneficial_amount) from sugg_title_mst b where  " +
									"b.sugg_by='"+Vsugg_by.elementAt(i)+"' and b.outcome_regd='IMPLEMENTABLE' and (b.sugg_dt >=  " +
									"to_date('"+dt+"','dd/mm/yyyy') AND b.sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1) ";
//					System.out.println("---query31----"+query31);
					rset=stmt.executeQuery(query31);
					if(rset.next())
					{
						Vact_save_empwise.add(rset.getString(1)==null?"-":rset.getString(1));
					}
					else{
						Vact_save_empwise.add("-");
					}
					 difference="";
					//System.out.println("-----Vact_save_empwise--"+Vest_save_empwise.elementAt(i)+"--"+Vact_save_empwise.elementAt(i));
					if( (!Vact_save_empwise.elementAt(i).toString().equalsIgnoreCase("-")) ){
						difference=nf.format((Double.parseDouble(Vest_save_empwise.elementAt(i)+"")- Double.parseDouble(Vact_save_empwise.elementAt(i)+"")));
						//diff_saving+=""+(nf.format((Double.parseDouble(difference)));
					}else{
						difference=nf.format((Double.parseDouble(Vest_save_empwise.elementAt(i)+"")));
						
					}
					diff1.add(difference);
					
					String queryd11="select count(title_cd) from sugg_title_mst where sugg_by='"+Vsugg_by.elementAt(i)+"' and (sugg_dt >=  " +
									"to_date('"+dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1) ";
					rset=stmt.executeQuery(queryd11);
					if(rset.next())
					{
						titlecdsz.add(rset.getString(1)==null?"-":rset.getString(1));
						tot+=Integer.parseInt(rset.getString(1));
					}else{
						titlecdsz.add("");
					}
					String queryd12="select count(title_cd) from sugg_title_mst where sugg_by='"+Vsugg_by.elementAt(i)+"' and outcome_regd='IMPLEMENTABLE' and (sugg_dt >=  " +
									"to_date('"+dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1)";
					rset=stmt.executeQuery(queryd12);
					if(rset.next())
					{
						implsz.add(rset.getString(1)==null?"-":rset.getString(1));
						q+=Integer.parseInt(rset.getString(1));
					}
					else{
						implsz.add("");
					}
					//System.out.println("---q--"+q);

					String queryd14="select count(title_cd) from sugg_title_mst where sugg_by='"+Vsugg_by.elementAt(i)+"' and outcome_regd='NONIMPLEMENTABLE' and (sugg_dt >=  " +
									"to_date('"+dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1)   ";
					rset=stmt.executeQuery(queryd14);
					if(rset.next())
					{
						nonimplsz.add(rset.getString(1)==null?"-":rset.getString(1));
						nt+=Integer.parseInt(rset.getString(1));
					}else{
						nonimplsz.add("");
					}
					
				}
				
//				query212="Select a.estimated_saving from sugg_title_dtl a,sugg_title_mst b where (b.sugg_dt >=" +
//						"to_date('"+dt+"','dd/mm/yyyy') AND b.sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1) and a.flag='Y' and a.title_cd=b.title_cd and status is null order by a.title_cd  ";
//				query212="Select sum(a.estimated_saving) from sugg_title_dtl a,sugg_title_mst b where (b.sugg_dt >=to_date('"+dt+"','dd/mm/yyyy') AND"
//						+ " b.sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1) and a.flag='Y' and a.title_cd=b.title_cd and  ((status  is null) "
//								+ " or (status='FORWARD' and hr_action_status is null)  or "
//								+ " (hr_action_status='ASSIGNED' and outcome_regd is null)  or (outcome_regd='IMPLEMENTABLE' and action_flag is null)  or  (action_flag='Y' and ACTUAL_CLOSER_DT is null)) order by a.title_cd   ";
////						System.out.println("query2--------"+query212);
//						rset=stmt.executeQuery(query212);
//						if(rset.next())
//						{
//							est_saving_pending=rset.getString(1)==null?"":rset.getString(1);
//						}
				query212="Select sum(beneficial_amount) from sugg_title_mst where (sugg_dt >=" +
						"to_date('"+dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1) and ACTUAL_CLOSER_DT is not null and impl_timeline is not null and beneficial_flag='Y' ";
//						System.out.println("query2--------"+query212);
						rset=stmt.executeQuery(query212);
						if(rset.next())
						{
							actual_saving_closed=rset.getString(1)==null?"0":rset.getString(1);
						}
						query212="Select sum(beneficial_amount) from sugg_title_mst where (sugg_dt >=" +
								"to_date('"+dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1) and outcome_regd='IMPLEMENTABLE' and action_flag is null and beneficial_flag='Y' ";
//								System.out.println("query2--------"+query212);
						rset=stmt.executeQuery(query212);
						if(rset.next())
						{
							requester_pending=rset.getString(1)==null?"0":rset.getString(1);
						}
						
						query212="Select sum(beneficial_amount) from sugg_title_mst where (sugg_dt >=" +
								"to_date('"+dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1) and beneficial_flag='Y' and  action_flag='Y' and exec_lead is not null and ACTUAL_CLOSER_DT is null ";
//								System.out.println("query2--------"+query212);
						rset=stmt.executeQuery(query212);
						if(rset.next())
						{
							LEAD_pending=rset.getString(1)==null?"0":rset.getString(1);
						}
						
				
				
				
			}
			
		}
		catch(SQLException e)
		{
			System.err.println("Error"+e);
			System.err.println("..DataBean_TIMS.java.in Query :.."+queryString);
			e.printStackTrace();
		}
	     
	}//end of //RG20170424
	
	
	public double getDiff_saving() {
		return diff_saving;
	}
	public void setDiff_saving(double diff_saving) {
		this.diff_saving = diff_saving;
	}



	Vector sup_nm_p=new Vector();Vector sup=new Vector();Vector hr_nm_p=new Vector();String hr_emp_nm="";Vector hr=new Vector();
	Vector act_flg_rpt=new Vector();Vector impl_or_nonimpl=new Vector();Vector sts_rpt=new Vector(); Vector flag_sts_rpt=new Vector();
	Vector revflg_rpt=new Vector();Vector Vhrsts_rpt=new Vector();int days2=0;String flag_respond_R="";Vector flg_m_R=new Vector();
	Vector revdt_rpt=new Vector();public Vector getRevdt_rpt() {
		return revdt_rpt;
	}
	public void setRevdt_rpt(Vector revdt_rpt) {
		this.revdt_rpt = revdt_rpt;
	}
	public Vector getRevno_rpt() {
		return revno_rpt;
	}
	public void setRevno_rpt(Vector revno_rpt) {
		this.revno_rpt = revno_rpt;
	}



	Vector revno_rpt=new Vector();
	Vector sup_effdt_rpt=new Vector();
	
	public void Sugg_report2() throws SQLException
	{
		try
		{
			String query10=" select min(to_char(sugg_dt,'yyyy')) from sugg_title_mst ";
			rset=stmt.executeQuery(query10);
				if(rset.next())
				{
					minyear=rset.getString(1);
				}
				
			if(!year.equalsIgnoreCase("0")){
			st_dt="01/01/"+year;
			String query="select TO_CHAR(sysdate,'DD/MM/YYYY'),TO_CHAR(sysdate,'YYYY')  from DUAL ";
			rset=stmt.executeQuery(query);
				if(rset.next())
				{
					end_dt=rset.getString(1);
					curyr=rset.getString(2);
				}
				if(dt.equals("")) {
//					dt = st_dt;
//					enddt = end_dt;
					
					if(year.equals(curyr)){
						//System.out.println("-sdsd-if-");
						st_dt="01/01/"+curyr;
						dt = st_dt;
						enddt = end_dt;
					}else{
						//System.out.println("-sdsd-else-");
						end_dt="31/12/"+year;
						dt = st_dt;
						enddt = end_dt;
					}
					
					
				}
				String query1="Select title_cd,title_nm,sugg_by from sugg_title_mst where (sugg_dt >= " +
						"to_date('"+dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1) order by sugg_dt DESC ";
//				System.out.println("query11-"+query1);
				rset=stmt.executeQuery(query1);
				while(rset.next())
				{
					title_cd_rpt.add(rset.getString(1)==null?"":rset.getString(1));
					title_nm_rpt.add(rset.getString(2)==null?"":rset.getString(2));
					sugg_by_rpt.add(rset.getString(3)==null?"":rset.getString(3));
				}
				
				for(int i=0;i<title_cd_rpt.size();i++){
					String query11="Select nvl(emp_nm,'') from hr_emp_mst where emp_cd='"+sugg_by_rpt.elementAt(i)+"' ";
					rset=stmt.executeQuery(query11);
					if(rset.next())
					{
						sugg_bynm_rpt.add(rset.getString(1)==null?"":rset.getString(1));
					} 
					else{
						sugg_bynm_rpt.add("");
					}
					
					//System.out.println("--sugg_by_rpt--"+sugg_by_rpt.size()+"-----"+title_cd_rpt.size()+"---"+sugg_bynm_rpt.size());		
					String query2="Select to_char(supervisor_action_dt,'dd/mm/yyyy'),to_char(hr_action_dt,'dd/mm/yyyy'),to_char(outcome_regd_dt,'dd/mm/yyyy')," +
							"to_char(sugg_dt,'dd/mm/yyyy'),supervisor_cd,hr_emp_cd,outcome_regd from sugg_title_mst where title_cd='"+title_cd_rpt.elementAt(i)+"' " +
							" and (sugg_dt >= " +
						"to_date('"+dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1) order by sugg_dt DESC ";
//					System.out.println("query2---"+query2);
					rset=stmt.executeQuery(query2);
					while(rset.next())
					{
						sup_action_dt_rpt.add(rset.getString(1)==null?"":rset.getString(1));
						hr_action_dt_rpt.add(rset.getString(2)==null?"":rset.getString(2));
						committee_action_dt.add(rset.getString(3)==null?"":rset.getString(3));
						sugg_dt_rpt.add(rset.getString(4)==null?"":rset.getString(4));
						sup_cd_rpt.add(rset.getString(5)==null?"":rset.getString(5));
						committee_response.add(rset.getString(7)==null?"":rset.getString(7));
						//hr_empcd.add(rset.getString(6)==null?"":rset.getString(6));
					}
					//for(int k=0;k<sup_cd_rpt.size();k++){
						String query111="Select emp_nm from hr_emp_mst where emp_cd='"+sup_cd_rpt.elementAt(i)+"'";
						rset=stmt.executeQuery(query111);
						if(rset.next())
						{
							sup_nm_p.add(rset.getString(1)==null?"":rset.getString(1));
						}else{
							sup_nm_p.add("");
						}
						
					//}
//					System.out.println("sup_nm_p---"+sup_nm_p.size());
					String flag_pen="";String flag_pen1="";
					if(!sup_action_dt_rpt.elementAt(i).toString().equalsIgnoreCase("")){
//						System.out.println("in if");
						String queryString="select to_date('"+sup_action_dt_rpt.elementAt(i)+"','dd/mm/yyyy') - to_date('"+sugg_dt_rpt.elementAt(i)+"','dd/mm/yyyy') from dual ";
//						System.out.println("queryString--"+queryString);
						rset=stmt.executeQuery(queryString);
						if(rset.next()){
							days=rset.getInt(1);
							if(days<=7)
							{
								flag_respond="W";
								flg_w.add(flag_respond);
								flg_w11.add(flag_respond);
							}else if(days>=8 && days<=15)
							{
								flag_respond="F";
								flg_f.add(flag_respond);
								flg_f11.add(flag_respond);
							}
							else if(days>=16 && days<=30)
							{
								flag_respond="M";
								flg_m.add(flag_respond);
								flg_m11.add(flag_respond);
							}
							else if(days>=31)
							{
								flag_respond="MM";
								flg_mm.add(flag_respond);
								flg_mm11.add(flag_respond);
							}
						}else{
							flag_respond="";
						}	
						
								
				}else{
					
					String queryString2="select to_char(sysdate,'dd/mm/yyyy') from dual";
					rset=stmt.executeQuery(queryString2);
					if(rset.next()){
						sysdt1=rset.getString(1);
					}
					
					String queryString3="select to_date('"+sysdt1+"','dd/mm/yyyy') - to_date('"+sugg_dt_rpt.elementAt(i)+"','dd/mm/yyyy') from dual ";
//					System.out.println("queryString--"+queryString3);
					rset=stmt.executeQuery(queryString3);
					if(rset.next()){
						days1=rset.getInt(1);
						if(days1<=7){
							flag_respond="P";
							flg_w.add(flag_respond);
							flag_pen="Y";
							
						}else if(days1>=8 && days1<=15)
						{
							flag_respond="P1";
							flg_f.add(flag_respond);
							flag_pen="Y";
							
						}
						else if(days1>=16 && days1<=30)
						{
							flag_respond="P2";
							flg_m.add(flag_respond);
							flag_pen="Y";
							
						}
						else if(days1>=31)
						{
							flag_respond="P3";
							flg_mm.add(flag_respond);
							flag_pen="Y";
							
						}
//						else{
//							flag_respond="";
//						}
					}else{
					flag_respond="";
					}
					
				}	
						//System.out.println("flg_w--"+flg_w.size()+"---"+flg_f.size());
						flg_sup.add(flag_respond);
						
//						System.out.println("flg_sup--"+flg_sup+"--"+flg_sup);
					
					if(!hr_action_dt_rpt.elementAt(i).toString().equalsIgnoreCase("")){
//						System.out.println("in if");
						String queryString="select to_date('"+hr_action_dt_rpt.elementAt(i)+"','dd/mm/yyyy') - to_date('"+sup_action_dt_rpt.elementAt(i)+"','dd/mm/yyyy') from dual ";
//						System.out.println("queryString--"+queryString);
						rset=stmt.executeQuery(queryString);
						if(rset.next()){
							days=rset.getInt(1);
							
						}
						String queryString5="select hr_emp_cd from sugg_title_mst where title_cd='"+title_cd_rpt.elementAt(i)+"' ";
//						System.out.println("queryString--"+queryString);
						rset=stmt.executeQuery(queryString5);
						if(rset.next()){
							hr_emp_cd=rset.getString(1)==null?"":rset.getString(1);
							
						}
						String queryString6="select emp_nm from hr_emp_mst where emp_cd='"+hr_emp_cd+"' ";
//						System.out.println("queryString--"+queryString6);
						rset=stmt.executeQuery(queryString6);
						if(rset.next()){
							hr_emp_nm=rset.getString(1);
							if(days<=7)
							{
								flag_respond1="W";
								flg_w1.add(flag_respond1);
								flg_w111.add(flag_respond1);
							}else if(days>=8 && days<=15)
							{
								flag_respond1="F";
								flg_f1.add(flag_respond1);
								flg_f111.add(flag_respond1);
							}
							else if(days>=16 && days<=30)
							{
								flag_respond1="M";
								flg_m1.add(flag_respond1);
								flg_m111.add(flag_respond1);
							}
							else if(days>=31)
							{
								flag_respond1="MM";
								flg_mm1.add(flag_respond1);
								flg_mm111.add(flag_respond1);
							}
						}else{
							flag_respond1="";
						}
									
				}else{
					if(flag_pen.equalsIgnoreCase("")){
					String queryString2="select to_char(sysdate,'dd/mm/yyyy') from dual";
					rset=stmt.executeQuery(queryString2);
					if(rset.next()){
						sysdt1=rset.getString(1);
					}
					
					String queryString3="select to_date('"+sysdt1+"','dd/mm/yyyy') - to_date('"+sup_action_dt_rpt.elementAt(i)+"','dd/mm/yyyy') from dual ";
//					System.out.println("queryString--"+queryString3);
					rset=stmt.executeQuery(queryString3);
					if(rset.next()){
						days1=rset.getInt(1);
						if(days1<=7){
							flag_respond1="P";
							flg_w1.add(flag_respond1);
							flag_pen1="Y";
							
						}else if(days1>=8 && days1<=15)
						{
							flag_respond1="P1";
							flg_f1.add(flag_respond1);
							flag_pen1="Y";
							
						}
						else if(days1>=16 && days1<=30)
						{
							flag_respond1="P2";
							flg_m1.add(flag_respond1);
							flag_pen1="Y";
							
						}
						else if(days1>=31)
						{
							flag_respond1="P3";
							flg_mm1.add(flag_respond1);
							flag_pen1="Y";
							
						}
//						else{
//							flag_respond1="";
//						}
					}else{
					flag_respond1="";
					}
				}else{
					flag_respond1="";
				}
				}	
					
						flg_hr.add(flag_respond1);
						if(!hr_emp_nm.equalsIgnoreCase("")){
						hr.add(hr_emp_nm);
						}else{
							hr.add("");
						}
						
						if((!committee_action_dt.elementAt(i).toString().equalsIgnoreCase("")) && (!committee_response.elementAt(i).toString().equalsIgnoreCase(""))){
							//System.out.println("---i----"+i);
							//System.out.println("in if");
							String queryString="select to_date('"+committee_action_dt.elementAt(i)+"','dd/mm/yyyy') - to_date('"+hr_action_dt_rpt.elementAt(i)+"','dd/mm/yyyy') from dual ";
//							System.out.println("queryString--"+queryString);
							rset=stmt.executeQuery(queryString);
							if(rset.next()){
								days=rset.getInt(1);
								if(days<=7)
								{
									flag_respond2="W";
									flg_w2.add(flag_respond2);
									flg_w1111.add(flag_respond2);
								}else if(days>=8 && days<=15)
								{
									flag_respond2="F";
									flg_f2.add(flag_respond2);
									flg_f1111.add(flag_respond2);
								}
								else if(days>=16 && days<=30)
								{
									flag_respond2="M";
									flg_m2.add(flag_respond2);
									flg_m1111.add(flag_respond2);
								}
								else if(days>=31)
								{
									flag_respond2="MM";
									flg_mm2.add(flag_respond2);
									flg_mm1111.add(flag_respond2);
								}

								
							}else{
								flag_respond2="";
							}	
									
					}else{
						if(flag_pen.equalsIgnoreCase("") && flag_pen1.equalsIgnoreCase("")){
						String queryString2="select to_char(sysdate,'dd/mm/yyyy') from dual";
						rset=stmt.executeQuery(queryString2);
						if(rset.next()){
							sysdt1=rset.getString(1);
						}
						
						String queryString3="select to_date('"+sysdt1+"','dd/mm/yyyy') - to_date('"+hr_action_dt_rpt.elementAt(i)+"','dd/mm/yyyy') from dual ";
//						System.out.println("queryString--"+queryString3);
						rset=stmt.executeQuery(queryString3);
						if(rset.next()){
							days1=rset.getInt(1);
							if(days1<=7){
								flag_respond2="P";
								flg_w2.add(flag_respond2);
								
							}else if(days1>=8 && days1<=15)
							{
								flag_respond2="P1";
								flg_f2.add(flag_respond2);
								
							}
							else if(days1>=16 && days1<=30)
							{
								flag_respond2="P2";
								flg_m2.add(flag_respond2);
								
							}
							else if(days1>=31)
							{
								flag_respond2="P3";
								flg_mm2.add(flag_respond2);
								
							}
//								else{
//								flag_respond2="";
//							}
						}else{
						flag_respond2="";
						}
					}else{
						flag_respond2="";
					}
					}	
							flg_cmt.add(flag_respond2);
							
							if((!hr_action_dt_rpt.elementAt(i).toString().equalsIgnoreCase("")) && (!committee_action_dt.elementAt(i).toString().equalsIgnoreCase(""))){
								String queryString1="select action_flag,outcome_regd from sugg_title_mst where title_cd='"+title_cd_rpt.elementAt(i)+"' ";
		//						System.out.println("queryString--"+queryString1);
								rset=stmt.executeQuery(queryString1);
								if(rset.next()){
									act_flg_rpt.add(rset.getString(1)==null?"":rset.getString(1));
									impl_or_nonimpl.add(rset.getString(2)==null?"":rset.getString(2));
								}else{
									act_flg_rpt.add("");
									impl_or_nonimpl.add("");
								}		
						}else{
							act_flg_rpt.add("");
							impl_or_nonimpl.add("");
						}
							
							///////For Return////////////////////////
							
							if((!sup_action_dt_rpt.elementAt(i).toString().equalsIgnoreCase(""))){
								String queryString1="select a.status,b.rev_flag from sugg_title_mst a,sugg_title_dtl b  where a.title_cd='"+title_cd_rpt.elementAt(i)+"' and b.flag='Y' and a.title_cd=b.title_cd order by a.title_cd ";
//											System.out.println("queryString--"+queryString1);
											rset=stmt.executeQuery(queryString1);
											if(rset.next()){
												sts_rpt.add(rset.getString(1)==null?"":rset.getString(1));
												revflg_rpt.add(rset.getString(2)==null?"":rset.getString(2));
												
											}else{
												sts_rpt.add("");
												revflg_rpt.add("");
											}
										}else{
											sts_rpt.add("");
											revflg_rpt.add("");
										}
										if(!sts_rpt.elementAt(i).toString().equalsIgnoreCase("") ){
											if(sts_rpt.elementAt(i).toString().equalsIgnoreCase("RETURN") && revflg_rpt.elementAt(i).toString().equalsIgnoreCase("N"))
											{
													flag_sts_rpt.add("N");
													revno_rpt.add("");
													revdt_rpt.add("");
												
											}
											else{
												
												if(sts_rpt.elementAt(i).toString().equalsIgnoreCase("RETURN") && revflg_rpt.elementAt(i).toString().equalsIgnoreCase("Y")){
													flag_sts_rpt.add("Y");
													revno_rpt.add("");
													revdt_rpt.add("");
													
													
													
													
												}else{
													//System.out.println("in elsesseseses");
													flag_sts_rpt.add("");
													String queryString="Select rev_no,to_char(rev_dt,'dd/mm/yyyy') from sugg_title_dtl where title_cd='"+title_cd_rpt.elementAt(i)+"' and flag='Y' and rev_flag='Y' ";
													//System.out.println("query888888888"+queryString);
													rset=stmt.executeQuery(queryString);
													if(rset.next())
													{
														revno_rpt.add(rset.getString(1)==null?"":rset.getString(1));
														revdt_rpt.add(rset.getString(2)==null?"":rset.getString(2));
													}else{
														revno_rpt.add("");
														revdt_rpt.add("");
													}
													
												}
											}
									 }else{
										 flag_sts_rpt.add("");
										 revno_rpt.add("");
											revdt_rpt.add("");
									 }
										//System.out.println("flg_m_R--------"+revno_rpt.size());
								//////For Hold//////////////////////////////
										if((!hr_action_dt_rpt.elementAt(i).toString().equalsIgnoreCase(""))){
											String queryString1="select hr_action_status from sugg_title_mst where title_cd='"+title_cd_rpt.elementAt(i)+"' order by title_cd ";
//														System.out.println("queryString--"+queryString1);
														rset=stmt.executeQuery(queryString1);
														if(rset.next()){
															Vhrsts_rpt.add(rset.getString(1)==null?"":rset.getString(1));
															
														}else{
															Vhrsts_rpt.add("");
														}
													}else{
														Vhrsts_rpt.add("");
													}
										
										
										//////////////////////////////////
										
									//	System.out.println("sts_rpt---"+sts_rpt.size());
										
							}	
						}
					}
		catch(SQLException e)
		{
			System.err.println("Error"+e);
			System.err.println("..DataBean_TIMS.java.in Query :.."+queryString);
			e.printStackTrace();
		}
	}  //end of method 20170407
	public void Sugg_Moc() throws SQLException
	{
		try
		{
			
			String q1="select emp_nm,comp_cd,emp_alias_cd from hr_emp_mst where emp_cd='"+cd_user+"'";
			rset=stmt.executeQuery(q1);
            if(rset.next())
			{
            	emp_nm1=rset.getString(1)==null?"":rset.getString(1);
            	comp_cd1=rset.getString(2)==null?"":rset.getString(2);
            	emp_alias_cd=rset.getString(3)==null?"":rset.getString(3);
			}
            String query112 ="select a.reporting from hr_emp_rel_mst a where "+
            		//+ "a.comp_cd='"+comp_cd1+"' " +and 
            		"a.emp_cd='"+cd_user+"' order by reporting    ";
    		   // System.out.println("query11--"+query112);
    		rset=stmt.executeQuery(query112);
    		while (rset.next())
    		{
    			//dept_nm1=rset.getString(1);
    			//dept_cd1=rset.getString(2);
    			line_mgr=rset.getString(1)==null?"":rset.getString(1);
    		}
    		dept_nm1="";dept_cd1="";desg_cd1="";desg_nm1="";
            
            String query11 ="select distinct(b.dept_nm),a.dept_cd from hr_emp_rel_mst a, " +
            		"hr_dept_mst b where a.dept_cd=b.dept_cd "+
            		//and b.comp_cd='"+comp_cd1+"' " +
            		"and a.emp_cd='"+cd_user+"' order by dept_nm    ";
            //System.out.println("query11--"+query11);
		rset=stmt.executeQuery(query11);
		while (rset.next())
		{
			dept_nm1=rset.getString(1)==null?"":rset.getString(1);
			dept_cd1=rset.getString(2)==null?"":rset.getString(2);
			//line_mgr=rset.getString(3);
		}
		
		String qury="select distinct(d.desg_cd),d.desg_nm from hr_emp_rel_mst a," +
				"hr_dept_mst b, hr_desg_mst d,hr_emp_mst e " +
				"where a.dept_cd=b.dept_cd and  b.dept_cd='"+dept_cd1+"' and " +
				" a.desg_cd=d.desg_cd and e.emp_cd='"+cd_user+"'  "+
				//and b.comp_cd='"+comp_cd1+"' " +
				"and a.emp_cd=e.emp_cd  order by desg_nm";
		//System.out.println("qury000---"+qury);
		rset=stmt.executeQuery(qury);
		while (rset.next())
		{
			desg_cd1=rset.getString(1)==null?"":rset.getString(1);
			desg_nm1=rset.getString(2)==null?"":rset.getString(2);
		}
//		String query2="Select distinct(reporting) from hr_emp_rel_mst  where comp_cd='"+comp_cd1+"' and branch_cd='"+branch_cd+"' order by reporting ";
//		rset=stmt.executeQuery(query2);
////		System.out.println("query2--"+query2);
//		while (rset.next())
//		{
//			res_sup.add(rset.getString(1)==null?"":rset.getString(1));
//		}
		
				String query4="select a.title_cd, a.title_nm, b.impl_by_days, b.impl_cost," +
				" to_char(a.impl_time_dt,'dd/mm/yyyy'), b.sugg_desc, b.sugg_benefit,a.supervisor_cd,a.remark_by_supervisor,a.action_by_supervisor,b.sugg_brief_benefit,b.estimated_saving,b.cost_reduction  from  sugg_title_mst a ," +
				"sugg_title_dtl b where a.title_cd= b.title_cd and b.flag='Y' and a.title_cd='"+titcd+"'  ";
				rset=stmt.executeQuery(query4);
				//System.out.println("query4- here else -"+query4);
				
			
			while (rset.next())
			{
				title_cd=rset.getString(1)==null?"":rset.getString(1);
				title_nm=rset.getString(2)==null?"":rset.getString(2);
				impl_by_days=rset.getString(3)==null?"":rset.getString(3);
				impl_cost=rset.getString(4)==null?"":rset.getString(4);
				impl_time_dt=rset.getString(5)==null?"":rset.getString(5);
				sugg_desc_rtn=rset.getString(6)==null?"":rset.getString(6);
				sugg_benefit=rset.getString(7)==null?"":rset.getString(7);
				sup_cd=rset.getString(8)==null?"":rset.getString(8);
				rmk_by_sup=rset.getString(9)==null?"":rset.getString(9);
				act_by_sup=rset.getString(10)==null?"":rset.getString(10);
				sugg_brf_bene_rtn=rset.getString(11)==null?"":rset.getString(11);
				estimated_save=rset.getString(12)==null?"":rset.getString(12);
				cost_reduce=rset.getString(13)==null?"":rset.getString(13);
			}
			String query5="Select nvl(emp_nm,'') from hr_emp_mst where emp_cd='"+sup_cd+"' ";
//			System.out.println("---"+query5);
			rset=stmt.executeQuery(query5);
			if(rset.next())
			{
				sup_nm=rset.getString(1)==null?"":rset.getString(1);
			}
			else{
				sup_nm="";
			}
			
 	}
		catch(SQLException e)
		{
			System.err.println("Error"+e);
			System.err.println("..DataBean_TIMS.java.in Query :.."+queryString);
			e.printStackTrace();
		}
	     
	}//end of //RG20170207
	
	public void Sup_Annexure() throws SQLException
	{
		try
		{
			String branch_cd="1";
			String query="select emp_cd from hr_emp_rel_mst where desg_cd='191' and dept_cd='138' and branch_cd='1' ";
			//System.out.println("---"+query);
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				hr_emp_cd=rset.getString(1);
			}
			if(!user_cd.equals(hr_emp_cd)){
			String query6="Select supervisor_cd from sugg_title_mst where supervisor_cd='"+user_cd+"' ";
			//System.out.println("---"+query6);
			rset=stmt.executeQuery(query6);
			if(rset.next())
			{
				res_supervisor=rset.getString(1);
				flag_sup="Y";
			}else{
				flag_sup="N";
			}
			if(flag_sup.equalsIgnoreCase("N")){
//				System.out.println("in supervisor");
			String q1="select emp_nm,comp_cd from hr_emp_mst where emp_cd='"+user_cd+"' ";
			//System.out.println("---"+q1);
			rset=stmt.executeQuery(q1);
            if(rset.next())
			{
            	emp_nm=rset.getString(1);
            	comp_cd=rset.getString(2);
			}
            /*String query1="select b.sugg_by from sugg_title_mst b,sugg_title_dtl c " +
				" where  b.line_mgr='"+user_cd+"'" +
				" and b.title_cd=c.title_cd order by sugg_dt desc "; 
          rset=stmt.executeQuery(query1);
           while(rset.next())
			{
	          	emp_cd.add(rset.getString(1));
			}*/
           //for(int i=0;i<emp_cd.size();i++){
           String query3="select a.title_cd,a.title_nm, a.impl_by_days, to_char(a.impl_time_dt,'dd/mm/yyyy')," +
           		"to_char(a.sugg_dt,'dd/mm/yyyy'),a.sugg_by,b.emp_nm,a.supervisor_cd,a.action_by_supervisor,a.status,to_char(a.SUPERVISOR_ACTION_DT,'dd/mm/yyyy hh24:mi:ss') from " +
           		" sugg_title_mst a,hr_emp_mst b where  a.line_mgr='"+user_cd+"'  " +
           				" and a.sugg_by=b.emp_cd order by a.title_nm  ";
			rset=stmt.executeQuery(query3);
//			System.out.println("query3-line manager-"+query3);
			while (rset.next())
			{
				title_cd2.add(rset.getString(1));
				title_nm1.add(rset.getString(2));
				impl_by_days1.add(rset.getString(3));
				impl_time_dt1.add(rset.getString(4));
				sugg_dt.add(rset.getString(5));
				sugg_by.add(rset.getString(6));
				sugg_nm.add(rset.getString(7));
				action_sts.add(rset.getString(9)==null?"Pending":rset.getString(9));
				sts.add(rset.getString(10)==null?"":rset.getString(10));
				action_sts_dt.add(rset.getString(11)==null?"":rset.getString(11));
			}
           //}
			}else{
				 String query3="select a.title_cd,a.title_nm, a.impl_by_days, to_char(a.impl_time_dt,'dd/mm/yyyy')," +
				 		"to_char(a.sugg_dt,'dd/mm/yyyy'),a.sugg_by,a.supervisor_cd,a.action_by_supervisor,a.status,to_char(a.SUPERVISOR_ACTION_DT,'dd/mm/yyyy hh24:mi:ss')  from " +
				 		"sugg_title_mst a where a.supervisor_cd='"+user_cd+"'   " +
				 		"order by a.title_nm ";
				rset=stmt.executeQuery(query3);
				//System.out.println("query3else--"+query3);
				while (rset.next())
				{
					title_cd21.add(rset.getString(1));
					title_nm11.add(rset.getString(2));
					impl_by_days11.add(rset.getString(3));
					impl_time_dt11.add(rset.getString(4));
					sugg_dt1.add(rset.getString(5));
					sugg_by1.add(rset.getString(6));
					action_sts1.add(rset.getString(8)==null?"Pending":rset.getString(8));
					status_sup.add(rset.getString(9)==null?"":rset.getString(9));
					sup_action_dt.add(rset.getString(10)==null?"":rset.getString(10));
				}
				for(int k=0;k<sugg_by1.size();k++){
					String q2="select emp_nm from hr_emp_mst where emp_cd='"+sugg_by1.elementAt(k)+"' ";
					//System.out.println("---"+q2);
					rset=stmt.executeQuery(q2);
		            while(rset.next())
					{
		            	emp_nm_under_sup.add(rset.getString(1));
					}
				}
			}
			}else{
				//System.out.println("hello");
				 String query3="select a.title_cd,a.title_nm, a.impl_by_days, to_char(a.impl_time_dt,'dd/mm/yyyy')," +
			 		"to_char(a.sugg_dt,'dd/mm/yyyy'),a.sugg_by,a.supervisor_cd,a.action_by_supervisor,a.status,a.hr_action_status,a.hr_emp_cd, " +
			 		"to_char(a.hr_action_dt,'dd/mm/yyyy hh24:mi:ss'),to_char(a.SUPERVISOR_ACTION_DT,'dd/mm/yyyy') from  " +
			 		"sugg_title_mst a  where (a.status='FORWARD' OR a.line_mgr='"+hr_emp_cd+"' OR a.supervisor_cd='"+hr_emp_cd+"')  " +
			 		" order by a.title_nm ";
			rset=stmt.executeQuery(query3);
//			System.out.println("query3--"+query3);
			while (rset.next())
			{
				title_cd21_hr.add(rset.getString(1));
				title_nm11_hr.add(rset.getString(2));
				impl_by_days11_hr.add(rset.getString(3));
				impl_time_dt11_hr.add(rset.getString(4));
				sugg_dt1_hr.add(rset.getString(5));
				sugg_by1_hr.add(rset.getString(6));
				sel_sup_cd.add(rset.getString(7));
				action_sts1_hr.add(rset.getString(8)==null?"Pending":rset.getString(8));
				status_sup_hr.add(rset.getString(9)==null?"":rset.getString(9));
				status_hr.add(rset.getString(10)==null?"":rset.getString(10));
				user_assign=rset.getString(11)==null?"":rset.getString(11);
				hr_act_dt.add(rset.getString(12)==null?"-":rset.getString(12));
				sup_act_dt.add(rset.getString(13)==null?"":rset.getString(13));
			}
			for(int k=0;k<sugg_by1_hr.size();k++){
				String q2="select emp_nm from hr_emp_mst where emp_cd='"+sugg_by1_hr.elementAt(k)+"' ";
				//System.out.println("---"+q2);
				rset=stmt.executeQuery(q2);
				while(rset.next())
				{
					emp_nm_under_sup_hr.add(rset.getString(1));
				}
				
			}
			
			}
		}
            
 	
		catch(SQLException e)
		{
			System.err.println("Error"+e);
			System.err.println("..DataBean_TIMS.java.in Query :.."+queryString);
			e.printStackTrace();
		}
	     
	}		//end of //RG20170207
	
	public void Employee_list() throws SQLException
	{
		try
		{
		
			//System.out.println("in if");
		//	if(!search_char.equalsIgnoreCase("")){
				queryString="select e.emp_cd,e.emp_nm "
						+ " from hr_emp_mst e  where  e.emp_status='Y' AND " +
						"(lower(e.emp_nm) LIKE '"+search_char+"%' or upper(e.emp_nm) LIKE '"+search_char+"%' ) order by emp_nm ";
				System.out.println("queryString---"+queryString);
				//System.out.println("DUTY-TIME-1--: "+queryString);
				rset=stmt.executeQuery(queryString);
				while (rset.next())
				{
					emp_cd_hr.add(rset.getString(1)==null?"":rset.getString(1));
					emp_nm_hr.add(rset.getString(2)==null?"":rset.getString(2));
				}
	//		}
		//System.out.println("res_sup_emp_cd--"+emp_nm_hr.size()+"--"+emp_nm_hr);
		if(!tit_cd_assgn_user.equalsIgnoreCase("")){
		String query6="select emp_cd from sugg_assign_dtl where title_cd='"+tit_cd_assgn_user+"' ";
			//System.out.println("DUTY-TIME-1--: "+queryString1);
			rset=stmt.executeQuery(query6);
			while (rset.next())
			{
				emp_cd_assgn_by_hr.add(rset.getString(1)==null?"":rset.getString(1));
			}
			for(int i=0;i<emp_cd_assgn_by_hr.size();i++){
			String query7="select nvl(emp_nm,'') from hr_emp_mst where emp_cd='"+emp_cd_assgn_by_hr.elementAt(i)+"' order by emp_nm ";
			//System.out.println("DUTY-TIME-1--: "+queryString1);
			rset=stmt.executeQuery(query7);
			if (rset.next())
			{
				emp_nm_assgn_by_hr.add(rset.getString(1)==null?"":rset.getString(1));
			}else{
				emp_nm_assgn_by_hr.add("");
			}
			}
		}
 	}
		catch(SQLException e)
		{
			System.err.println("Error"+e);
			System.err.println("..DataBean_TIMS.java.in Query.."+queryString);
			e.printStackTrace();
		}
	     
	}			
	//end of //RG20170207
	Vector Vvemp_nm=new Vector();
	Vector Vvemp_emailid=new Vector();
	public Vector getVvemp_emailid() {
		return Vvemp_emailid;
	}



	public void setVvemp_emailid(Vector vvemp_emailid) {
		Vvemp_emailid = vvemp_emailid;
	}
	Vector Vemptyp_cd=new Vector();
	Map all_employees=new HashMap();
	Vector Vdept_nm=new Vector();
	Vector Vvdept_cd=new Vector();
	Vector emp_act_cnt=new Vector();
	
	public void Employee_list_For_ExecutionLead() throws SQLException
	{
		try
		{
			Vector all_alpha=new Vector();
			String arr[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
			
			Map emp_alpha_order=new HashMap();
				for(int k=0;k<arr.length;k++){
				queryString="SELECT EMP_NM,A.EMP_CD,B.EMPTYP_CD FROM HR_EMP_MST A,HR_EMP_REL_MST B WHERE A.EMP_CD=B.EMP_CD AND B.EMPTYP_CD!='9' AND  UPPER(EMP_NM) LIKE '"+arr[k]+"%' ORDER BY A.EMP_NM " ;
			//System.out.println("--queryString----"+queryString);
				rset=stmt.executeQuery(queryString);
				while(rset.next()){
					if(!alphabets.contains(arr[k])){
						alphabets.add(arr[k]);
					}
				}
			}
//				if(!search_char.equals("")){
					if(search_char.equals("All"))
						search_char="";
					
					String queryString1="select distinct(b.dept_cd),c.dept_nm from HR_EMP_MST a,HR_EMP_REL_MST b,hr_dept_mst c where (lower(a.emp_nm) "
							+ " LIKE '"+search_char+"%' or upper(a.emp_nm) LIKE '"+search_char+"%' ) and b.dept_cd=c.dept_cd and a.emp_cd=b.emp_cd and b.emptyp_cd!='9' and a.emp_status='Y' order by c.dept_nm";
					//System.out.println("queryString1-"+queryString1);
					rset=stmt.executeQuery(queryString1);
					while(rset.next())
					{
						Vvdept_cd.add(rset.getString(1)==null?"":rset.getString(1));
						Vdept_nm.add(rset.getString(2)==null?"":rset.getString(2));
					}
					int m=0;
					
					for(int i=0;i<Vvdept_cd.size();i++){
						m=0;
						 queryString="select a.emp_cd,a.emp_nm,a.email_id "
									+ "from HR_EMP_MST a,HR_EMP_REL_MST b where a.emp_cd=b.emp_cd "
									+ "  AND (lower(a.emp_nm) "
									+ " LIKE '"+search_char+"%' or upper(a.emp_nm) LIKE '"+search_char+"%' ) and b.dept_cd='"+Vvdept_cd.elementAt(i)+"' and a.emp_status='Y' and b.emptyp_cd!='9' order by a.emp_nm";
////						 System.out.println("queryString---"+queryString);
						 rset=stmt.executeQuery(queryString);
						while(rset.next())
						{
							Vemp_cd.add(rset.getString(1)==null?"":rset.getString(1));
							Vvemp_nm.add(rset.getString(2)==null?"":rset.getString(2));
							Vvemp_emailid.add(rset.getString(3)==null?"":rset.getString(3));
							m++;
						}
						emp_act_cnt.add(""+m);   
					}
					
//				}
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	     
	}			
	//end of //RG20170207
	String form_flag="";
	public void Suggestion_Dtls_For_ExecutionLead() throws SQLException
	{
		try
		{
			String q11="select nvl(emp_nm,''),comp_cd,emp_alias_cd from hr_emp_mst where emp_cd='"+suggby+"'";
			//System.out.println("inside "+q11);
			rset=stmt.executeQuery(q11);
            if(rset.next())
			{
            	emp_nm1=rset.getString(1)==null?"":rset.getString(1);
            	comp_cd1=rset.getString(2)==null?"":rset.getString(2);
            	emp_alias_cd1=rset.getString(3)==null?"":rset.getString(3);
			}
            String query112 ="select a.reporting from hr_emp_rel_mst a where "
//            		+ "a.comp_cd='"+comp_cd1+"' and "
            				+ "a.emp_cd='"+suggby+"' order by reporting    ";
			    //System.out.println("query11--"+query112);
			rset=stmt.executeQuery(query112);
			while (rset.next())
			{
				//dept_nm1=rset.getString(1);
				//dept_cd1=rset.getString(2);
				line_mgr=rset.getString(1)==null?"":rset.getString(1);
			}
			dept_nm1="";dept_cd1="";desg_cd1="";desg_nm1="";
            String query12 ="select distinct(b.dept_nm),a.dept_cd from hr_emp_rel_mst a, " +
            		"hr_dept_mst b where a.dept_cd=b.dept_cd  "+
            		//and b.comp_cd='"+comp_cd1+"' " +
            		"and a.emp_cd='"+suggby+"' order by dept_nm    ";
            //System.out.println("query11--"+query12);
		rset=stmt.executeQuery(query12);
		while (rset.next())
		{
			dept_nm1=rset.getString(1)==null?"":rset.getString(1);
			dept_cd1=rset.getString(2)==null?"":rset.getString(2);
		//	line_mgr=rset.getString(3);
		}
		
		String qury2="select distinct(d.desg_cd),d.desg_nm from hr_emp_rel_mst a," +
				"hr_dept_mst b, hr_desg_mst d,hr_emp_mst e " +
				"where a.dept_cd=b.dept_cd and  b.dept_cd='"+dept_cd1+"' and " +
				" a.desg_cd=d.desg_cd and e.emp_cd='"+suggby+"' "+
				//and b.comp_cd='"+comp_cd1+"' " +
				"and a.emp_cd=e.emp_cd  order by desg_nm";
		//System.out.println("qury000---"+qury2);
		rset=stmt.executeQuery(qury2);
		while (rset.next())
		{
			desg_cd1=rset.getString(1)==null?"":rset.getString(1);
			desg_nm1=rset.getString(2)==null?"":rset.getString(2);
		
		}
			
				String query4="select a.title_cd, a.title_nm, b.impl_by_days, b.impl_cost," +
					" to_char(a.impl_time_dt,'dd/mm/yyyy'), b.sugg_desc, b.sugg_benefit,a.supervisor_cd,"
					+ "a.remark_by_supervisor,a.action_by_supervisor,b.sugg_brief_benefit,a.status,"
					+ "b.rev_flag,b.cost_reduction,b.estimated_saving,a.impl_timeline,to_char(a.target_close_dt,'dd/mm/yyyy'),"
					+ "to_char(actual_closer_dt,'dd/mm/yyyy'),OUTCOME_REGD, to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss') " +
					", MEETING_VENUE, COMM_RECO, to_char(COMM_MEETING_DT,'dd/mm/yyyy'),beneficial_flag,"
					+ "beneficial_amount,to_char(a.COMMITTEE_DECISION_DT,'dd/mm/yyyy'),EXEC_LEAD_DECISION_FLAG,COMM_ACTUAL_SAVINGS,"
					+ "EXEC_LEAD_ACTUAL_SAVINGS,EXEC_LEAD_BENEFICIAL_FLAG ,EXEC_LEAD_BENEFICIAL_AMOUNT from sugg_title_mst a ," +
					"sugg_title_dtl b where a.title_cd= b.title_cd and b.flag='Y' and a.title_cd='"+titcd+"' ";
					rset=stmt.executeQuery(query4);
					//System.out.println("query4-- -"+query4);
				while (rset.next())
				{
					title_cd=rset.getString(1)==null?"":rset.getString(1);
					title_nm=rset.getString(2)==null?"":rset.getString(2);
					impl_by_days=rset.getString(3)==null?"":rset.getString(3);
					impl_cost=rset.getString(4)==null?"":rset.getString(4);
					impl_time_dt=rset.getString(5)==null?"":rset.getString(5);
					sugg_desc=rset.getString(6)==null?"":rset.getString(6);
					sugg_benefit=rset.getString(7)==null?"":rset.getString(7);
					sup_cd=rset.getString(8)==null?"":rset.getString(8);
					rmk_by_sup=rset.getString(9)==null?"":rset.getString(9);
					act_by_sup=rset.getString(10)==null?"":rset.getString(10);
					sugg_brf_bene=rset.getString(11)==null?"":rset.getString(11);
					sup_sts=rset.getString(12)==null?"":rset.getString(12);
					rev_flag=rset.getString(13)==null?"":rset.getString(13);
					cost_reduce=rset.getString(14)==null?"":rset.getString(14);
					estimated_save=rset.getString(15)==null?"":rset.getString(15);
					impl_timeline=rset.getString(16)==null?"":rset.getString(16);
					target_close_dt=rset.getString(17)==null?"":rset.getString(17);
					actual_close_dt=rset.getString(18)==null?"":rset.getString(18);
					
					
					outcome_regd=rset.getString(19)==null?"":rset.getString(19);
					outcome_regd_dt=rset.getString(20)==null?"":rset.getString(20);
					committee_venue=rset.getString(21)==null?"":rset.getString(21);
					committee_reco=rset.getString(22)==null?"":rset.getString(22);
					committee_meeting_dt=rset.getString(23)==null?"":rset.getString(23);
					bene_flg=rset.getString(24)==null?"":rset.getString(24);
					ben_amt=rset.getString(25)==null?"":rset.getString(25);
					COMMITTEE_DECIDE_DT=rset.getString(26)==null?"":rset.getString(26);
					EXEC_LEAD_FLAG=rset.getString(27)==null?"":rset.getString(27);
					COMM_ACTUAL_SAVINGS=rset.getString(28)==null?"":rset.getString(28);//RG20190805
					EXEC_ACTUAL_SAVINGS=rset.getString(29)==null?"":rset.getString(29);//RG20190805
					EXEC_BENEFICIAL_FLAG=rset.getString(30)==null?"":rset.getString(30);//RG20190805
					EXEC_BENEFICIAL_AMT=rset.getString(31)==null?"":rset.getString(31);//RG20190805
					
				}
				
				
				String AssignedUser="";
			     String  query="select A.EMP_CD, B.EMP_NM from SUGG_ASSIGN_DTL A, HR_EMP_MST B where A.TITLE_CD='"+title_cd+"' AND A.EMP_CD=B.EMP_CD order by A.EMP_CD ";
			       // System.out.println("ASSIGNED MEMBERS: "+query);
					rset=stmt.executeQuery(query);
			       while(rset.next())
					{
			       	AssignedUser +=" ,"+rset.getString(2);
					}
			       if(!AssignedUser.equals("")){
			    	   committee_members1 = AssignedUser.substring(2,AssignedUser.length());
			       }else{
			    	   committee_members1 = AssignedUser;
			       }
			      // System.out.println("committee_members1--"+committee_members1);
				
				
				String query5="Select nvl(emp_nm,'') from hr_emp_mst where emp_cd='"+sup_cd+"' ";
//				System.out.println("---"+query5);
				rset=stmt.executeQuery(query5);
				if(rset.next())
				{
					sup_nm=rset.getString(1)==null?"":rset.getString(1);
				}
				
				  ////////////////// PDF File Creation////////////////////
				String f1="";
				String f2="";
				String path=request.getRealPath("/PDF");
				f2="SUGGESTION-"+title_cd+".pdf";
				f1="SUGGESTION-"+title_cd;
				f1=path+"/"+f1;
				File pdf_file=new File(f1+".pdf");
				if(pdf_file.exists())
				{
						path=path+"//"+pdf_file;
			            String context_nm = request.getContextPath();
						String server_nm = request.getServerName();
						String server_port = ""+request.getServerPort();
						String url_start = "http://"+server_nm+":"+server_port+context_nm;
						 pdfpath = path;
						pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
						pdfpath = url_start+"/PDF/"+f2;
				}
				
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	     
	}	
	Map temp_map=new HashMap();
	public void Suggestion_Dtls_Summary() throws SQLException
	{
		try
		{
			String query10=" select min(to_char(sugg_dt,'yyyy')),max(to_char(sugg_dt,'yyyy')) from sugg_title_mst ";
			rset=stmt.executeQuery(query10);
				if(rset.next())
				{
					minyear=rset.getString(1);
					maxyear=rset.getString(2);
				}
				
			if(!year.equalsIgnoreCase("0")){
			st_dt="01/"+cur_month+"/"+year;
			String query="select TO_CHAR(sysdate,'dd/mm/yyyy'),to_char(sysdate,'yyyy') from DUAL ";
			
			rset=stmt.executeQuery(query);
				if(rset.next())
				{
					end_dt=rset.getString(1);
					curyr=rset.getString(2);
				}
				if(dt.equals("")) {
//					dt = st_dt;
//					enddt = end_dt;
					
					if(year.equals(curyr)){
						//System.out.println("-sdsd-if-");
						st_dt="01/01/"+curyr;
						dt = st_dt;
						enddt = end_dt;
					}else{
						//System.out.println("-sdsd-else-");
						st_dt="01/01/"+year;
						end_dt="31/12/"+year;
						dt = st_dt;
						enddt = end_dt;
					}
				}
				
				int m=0;
					m=0;
					query="SELECT A.TITLE_CD,A.TITLE_NM,A.SUGG_BY,B.EMP_NM,C.SUGG_BENEFIT,C.IMPL_COST,C.IMPL_BY_DAYS,"
							+ "C.COST_REDUCTION,C.ESTIMATED_SAVING,C.SUGG_BRIEF_BENEFIT,A.SUPERVISOR_CD,to_char(A.sugg_dt,'dd/mm/yyyy')  FROM SUGG_TITLE_MST A,"
							+ "HR_EMP_MST B,SUGG_TITLE_DTL C WHERE (SUGG_DT>= TO_DATE('"+dt+"','dd/mm/yyyy') AND "
							+ "SUGG_DT < TO_DATE('"+enddt+"','dd/mm/yyyy')+1) AND A.SUGG_BY=B.EMP_CD AND A.TITLE_CD= C.TITLE_CD and C.FLAG='Y'   ORDER BY B.EMP_NM,title_cd ";
					System.out.println("query--"+query);
					rset=stmt.executeQuery(query);
					while(rset.next())
					{
						
						Vtitle_cd.add(rset.getString(1));
						Vtitle_nm.add(rset.getString(2)==null?"":rset.getString(2));
						sugg_by_rpt.add(rset.getString(3)==null?"":rset.getString(3));
						sugg_bynm_rpt.add(rset.getString(4)==null?"":rset.getString(4));
						VSUGG_BENEFIT.add(rset.getString(5)==null?"":rset.getString(5));
						Vimpl_cost.add(rset.getString(6)==null?"":rset.getString(6));
						impl_by_days1.add(rset.getString(7)==null?"":rset.getString(7));
						VCOST_REDUCTION.add(rset.getString(8)==null?"":rset.getString(8));
						VESTIMATED_SAVING.add(rset.getString(9)==null?"":rset.getString(9));
						VSUGG_BRIEF_BENEFIT.add(rset.getString(10)==null?"":rset.getString(10));
						res_sup_emp_cd.add(rset.getString(11)==null?"":rset.getString(11));
						Vsugg_dt.add(rset.getString(12)==null?"":rset.getString(12));
						
						String queryString1="SELECT NVL(EMP_NM,'') FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(11)+"'";
						rset2=stmt2.executeQuery(queryString1);
						if(rset2.next()){
							res_sup.add(rset2.getString(1)==null?"":rset2.getString(1));
						}else{
							res_sup.add("");
						}
						m++;
						
						 queryString="SELECT COUNT(SUGG_BY) FROM SUGG_TITLE_MST A "
							 		+ "WHERE (SUGG_DT>= TO_DATE('"+dt+"','dd/mm/yyyy') AND SUGG_DT < "
					 				+ "TO_DATE('"+enddt+"','dd/mm/yyyy')+1) AND SUGG_BY='"+rset.getString(3)+"' ";
							//System.out.println("query11-"+queryString);
							rset1=stmt1.executeQuery(queryString);
						if(rset1.next())
						{
							temp_map.put(rset.getString(3), rset1.getInt(1));
							
						}
						
					}
					emp_act_cnt.add(""+m);  
				
			}
			
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	     
	}	
	
	
public void Print_sugg() throws SQLException
{
	try
	{
		
		String query3="select a.title_cd, a.title_nm, b.impl_by_days, b.impl_cost," +
				"  b.sugg_desc, b.sugg_benefit,b.sugg_brief_benefit  " +
				",a.supervisor_cd,a.sugg_by,MEETING_VENUE,TO_char(comm_meeting_dt,'dd/mm/yyyy'),b.cost_reduction,b.estimated_saving,hr_action_status_reason "+
				" from  sugg_title_mst a, sugg_title_dtl b where a.title_cd= b.title_cd and b.flag='Y' and a.title_cd='"+titcd+"' ";
		rset=stmt.executeQuery(query3);
		//System.out.println("query3- here---"+query3);
		while (rset.next())
		{
			title_cd=rset.getString(1)==null?"":rset.getString(1);
			title_nm=rset.getString(2)==null?"":rset.getString(2);
			impl_by_days=rset.getString(3)==null?"":rset.getString(3);
			impl_cost=rset.getString(4)==null?"":rset.getString(4);
			sugg_desc=rset.getString(5)==null?"":rset.getString(5);
			sugg_benefit=rset.getString(6)==null?"":rset.getString(6);
			sugg_brief=rset.getString(7)==null?"":rset.getString(7);
			res_supervisor1=rset.getString(8)==null?"":rset.getString(8);
			sugg_by_prnt=rset.getString(9)==null?"":rset.getString(9);
			venue=rset.getString(10)==null?"":rset.getString(10);
			meeting_dt=rset.getString(11)==null?"":rset.getString(11);
			cost_reduce=rset.getString(12)==null?"":rset.getString(12);
			estimated_save=rset.getString(13)==null?"":rset.getString(13);
			hr_action_status=rset.getString(14)==null?"":rset.getString(14);
			
			
		}
		String q2="select nvl(emp_nm,''),comp_cd from hr_emp_mst where emp_cd='"+res_supervisor1+"'";
		rset=stmt.executeQuery(q2);
        if(rset.next())
		{
        	Ssupemp_nm1=rset.getString(1)==null?"":rset.getString(1);
		}
        String q3="select nvl(emp_nm,''),comp_cd from hr_emp_mst where emp_cd='"+sugg_by_prnt+"'";
		rset=stmt.executeQuery(q3);
        if(rset.next())
		{
        	emp_nm1=rset.getString(1)==null?"":rset.getString(1);
		}
        
        String query="select to_char(sysdate,'dd-mm-yyyy') from dual ";
		rset=stmt.executeQuery(query);
		if(rset.next())
		{
			sysdt=rset.getString(1);
		}
		String AssignedUser="";
        query="select A.EMP_CD, B.EMP_NM from SUGG_ASSIGN_DTL A, HR_EMP_MST B where A.TITLE_CD='"+titcd+"' AND A.EMP_CD=B.EMP_CD ";
       // System.out.println("ASSIGNED MEMBERS: "+query);
		rset=stmt.executeQuery(query);
       while(rset.next())
		{
       	AssignedUser +=","+rset.getString(2);
		}
       committee_members1 = AssignedUser;
		
		//////////////////////////////////////////////////////
		
	}
	catch(SQLException e)
	{
		System.err.println("Error"+e);
		System.err.println("..DataBean_TIMS.java.in Query :.."+queryString);
		e.printStackTrace();
	}
     
}//end of //RG20170207
	
	public void Track_sugg() throws SQLException
	{
		try
		{
			String query11="select a.title_cd,a.title_nm,a.sugg_by " +
			"from sugg_title_mst a where a.sugg_by='"+cd_user+"' and (sugg_dt is null or sugg_dt='')  ";
			//System.out.println("SUGG-sugg_title_mst: "+query11);
			rset=stmt.executeQuery(query11);
			if (rset.next())
			{
				title_cd_upd=rset.getString(1)==null?"":rset.getString(1);
				//title_nm_upd=rset.getString(2)==null?"":rset.getString(2);
				sugg_by_upd=rset.getString(3)==null?"":rset.getString(3);
				flag_upd="Y";
			}
			else{
				title_cd_upd="";
				//title_nm_upd="";
				sugg_by_upd="";
				flag_upd="N";
			}
			//System.out.println("sugg_by_upd---"+sugg_by_upd);
			String query="select a.title_cd,nvl(a.title_nm,''),to_char(a.sugg_dt,'dd/mm/yyyy hh24:mi:ss'),nvl(a.sugg_by,''),a.status,"
					+ "to_char(a.SUPERVISOR_ACTION_DT,'dd/mm/yyyy hh24:mi:ss'),to_char(a.hr_action_dt,'dd/mm/yyyy hh24:mi:ss'),a.HR_ACTION_STATUS " +
			" , OUTCOME_REGD, to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss'), ACTION_DTL,  to_char(a.ACTION_DT,'dd/mm/yyyy hh24:mi:ss'),  VERIFY_FLAG,"
			+ "  to_char(a.VERIFY_DT,'dd/mm/yyyy hh24:mi:ss'),b.REV_FLAG,a.hr_action_status_reason from sugg_title_mst a,sugg_title_dtl b where b.flag='Y'"
			+ " and a.sugg_by='"+cd_user+"' and a.title_cd=b.title_cd order by a.title_cd DESC ";
			//System.out.println("SUGG-sugg_title_mst: "+query);
			rset=stmt.executeQuery(query);
			while (rset.next())
			{
				title_cd_track.add(rset.getString(1)==null?"":rset.getString(1));
				title_nm_track.add(rset.getString(2)==null?"":rset.getString(2));
				sugg_dt1_track.add(rset.getString(3)==null?"":rset.getString(3));
				sugg_by1_track.add(rset.getString(4)==null?"":rset.getString(4));
				status_sup_track.add(rset.getString(5)==null?"PENDING":rset.getString(5));
				status_sup_dt.add(rset.getString(6)==null?"":rset.getString(6));
				status_hr_dt.add(rset.getString(7)==null?"":rset.getString(7));
				status_hr_track.add(rset.getString(8)==null?"PENDING":rset.getString(8));
				Voutcome_regd.add(rset.getString(9)==null?"-":rset.getString(9));
				Voutcome_regd_dt.add(rset.getString(10)==null?"-":rset.getString(10));
				Vaction_dtl.add(rset.getString(11)==null?"-":rset.getString(11));
				Vaction_dt.add(rset.getString(12)==null?"-":rset.getString(12));
				Vverify_flag.add(rset.getString(13)==null?"-":rset.getString(13));
				Vverify_dt.add(rset.getString(14)==null?"-":rset.getString(14));
				REV_flag.add(rset.getString(15)==null?"-":rset.getString(15));
				hr_action_sts_reason.add(rset.getString(16)==null?"":rset.getString(16));
			}
			//System.out.println("SUGG-Voutcome_regd: "+Voutcome_regd);
			String query1="select title_cd from sugg_assign_dtl where emp_cd='"+cd_user+"' and flag='Y' order by title_cd DESC ";
			rset=stmt.executeQuery(query1);
			while (rset.next())
			{
				title_cd_assign.add(rset.getString(1));
				queryString ="select emp_cd from sugg_email_dtl where title_cd='"+rset.getString(1)+"' and emp_cd='"+cd_user+"'";
				System.out.println("query---"+queryString);
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next()){
					title_flg_committee.add("Y");
				}else{
					title_flg_committee.add("N");
				}
				
			}
			
			query1="select title_cd from sugg_title_mst where exec_lead='"+cd_user+"' ";
			rset=stmt.executeQuery(query1);
			while (rset.next())
			{
				if(!title_cd_assign.contains(rset.getString(1))){
					title_cd_assign.add(rset.getString(1));
					queryString ="select emp_cd from sugg_email_dtl where title_cd='"+rset.getString(1)+"' and emp_cd='"+cd_user+"'";
					System.out.println("query--in lead-"+queryString);
					rset1=stmt1.executeQuery(queryString);
					if(rset1.next()){
						title_flg_committee.add("Y");
					}else{
						title_flg_committee.add("N");
					}
				}
			}
			//System.out.println("-title_flg_committee--"+title_flg_committee);
		for(int k=0;k<title_cd_assign.size();k++){
				String query2="select a.title_cd,nvl(a.title_nm,''),nvl(a.sugg_by,''),a.STATUS,to_char(a.supervisor_action_dt,'dd/mm/yyyy hh24:mi:ss'),"
						+ "a.HR_ACTION_STATUS,to_char(a.HR_ACTION_DT,'dd/mm/yyyy hh24:mi:ss'),to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss')" +
								",outcome_regd,verify_flag,hr_action_status_reason,exec_lead ,exec_lead_decision_flag,to_char(actual_closer_dt,'dd/mm/yyyy'),impl_timeline" + 
							" from sugg_title_mst a where a.title_cd='"+title_cd_assign.elementAt(k)+"' order by a.title_cd DESC ";
				//System.out.println("query2----"+query2);
				rset=stmt.executeQuery(query2);
				while (rset.next())
				{
					title_cd_for_pdf.add(rset.getString(1)==null?"":rset.getString(1));
					title_nm_for_pdf.add(rset.getString(2)==null?"":rset.getString(2));
					sugg_by_for_pdf.add(rset.getString(3)==null?"":rset.getString(3));
					sup_action.add(rset.getString(4)==null?"":rset.getString(4));
					sup_act_dt_pdf.add(rset.getString(5)==null?"":rset.getString(5));
					hr_act_pdf.add(rset.getString(6)==null?"":rset.getString(6));
					hr_dt_pdf.add(rset.getString(7)==null?"":rset.getString(7));
					
					voutcome_regd_dt1.add(rset.getString(8)==null?"":rset.getString(8));
					voutcome_regd1.add(rset.getString(9)==null?"-":rset.getString(9));
					verify_flg.add(rset.getString(10)==null?"-":rset.getString(10));
					hr_actsts_for_pdf.add(rset.getString(11)==null?"":rset.getString(11));
					VEXEC_LEAD_CD.add(rset.getString(12)==null?"":rset.getString(12));
					exec_lead_flag.add(rset.getString(13)==null?"":rset.getString(13));
					act_closer_dt.add(rset.getString(14)==null?"":rset.getString(14));
					impl_lead_timeline_flag.add(rset.getString(15)==null?"":rset.getString(15));
				}
		    }
		for(int k=0;k<title_cd_for_pdf.size();k++)
		{
			
			String f1="";
			String f2="";
			String path=request.getRealPath("/PDF");
			f2="SUGGESTION-"+title_cd_for_pdf.elementAt(k)+".pdf";
			f1="SUGGESTION-"+title_cd_for_pdf.elementAt(k);
			f1=path+"/"+f1;
			File pdf_file=new File(f1+".pdf");
			
			if(pdf_file.exists())
			{
					path=path+"//"+pdf_file;
		            String context_nm = request.getContextPath();
					String server_nm = request.getServerName();
					String server_port = ""+request.getServerPort();
					String url_start = "http://"+server_nm+":"+server_port+context_nm;
					 pdfpath = path;
					pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
					
					pdfpath = url_start+"/PDF/"+f2;
					pdf.add(pdfpath);
			}
			else{
				pdf.add("");
			}
			
		}
		}
		catch(SQLException e)
		{
			System.err.println("Error"+e);
			System.err.println("..DataBean_TIMS.java.in Query :.."+queryString);
			e.printStackTrace();
		}
	     
	}	
public String getFlag() { return flag; }
public void setFlag(String flag) { 	this.flag = flag; }

public String getDept_cd1() {
	return dept_cd1;
}

public String getDept_nm1() {
	return dept_nm1;
}


public String getDesg_nm1() {
	return desg_nm1;
}

public void setCd_user(String cd_user) {
	this.cd_user = cd_user;
}

public String getEmp_nm1() {
	return emp_nm1;
}

public String getDesg_cd1() {
	return desg_cd1;
}

public String getLine_mgr() {
	return line_mgr;
}

public Vector getRes_sup_emp_cd() {
	return res_sup_emp_cd;
}

public Vector getRes_sup() {
	return res_sup;
}

public void setUser_cd(String user_cd) {
	this.user_cd = user_cd;
}

public String getTitle_cd() {
	return title_cd;
}

public String getImpl_by_days() {
	return impl_by_days;
}

public void setImpl_by_days(String impl_by_days) {
	this.impl_by_days = impl_by_days;
}

public String getImpl_cost() {
	return impl_cost;
}

public void setImpl_cost(String impl_cost) {
	this.impl_cost = impl_cost;
}

public String getImpl_time_dt() {
	return impl_time_dt;
}

public void setImpl_time_dt(String impl_time_dt) {
	this.impl_time_dt = impl_time_dt;
}

public String getSugg_benefit() {
	return sugg_benefit;
}

public void setSugg_benefit(String sugg_benefit) {
	this.sugg_benefit = sugg_benefit;
}

public String getSugg_desc() {
	return sugg_desc;
}

public void setSugg_desc(String sugg_desc) {
	this.sugg_desc = sugg_desc;
}

public String getSugg_source() {
	return sugg_source;
}

public void setSugg_source(String sugg_source) {
	this.sugg_source = sugg_source;
}

public String getTitle_nm() {
	return title_nm;
}

public String getCurr_sys_sts() {
	return curr_sys_sts;
}

public Vector getEmp_cd() {
	return emp_cd;
}

public void setEmp_cd(Vector emp_cd) {
	this.emp_cd = emp_cd;
}

public Vector getEmp_nm2() {
	return emp_nm2;
}

public Vector getImpl_by_days1() {
	return impl_by_days1;
}

public void setImpl_by_days1(Vector impl_by_days1) {
	this.impl_by_days1 = impl_by_days1;
}

public Vector getImpl_time_dt1() {
	return impl_time_dt1;
}

public void setImpl_time_dt1(Vector impl_time_dt1) {
	this.impl_time_dt1 = impl_time_dt1;
}

public Vector getSugg_dt() {
	return sugg_dt;
}

public void setSugg_dt(Vector sugg_dt) {
	this.sugg_dt = sugg_dt;
}

public Vector getTitle_nm1() {
	return title_nm1;
}

public void setTitle_nm1(Vector title_nm1) {
	this.title_nm1 = title_nm1;
}

public Vector getTitle_cd2() {
	return title_cd2;
}

public Vector getSugg_nm() {
	return sugg_nm;
}

public Vector getSugg_by() {
	return sugg_by;
}

public void setFlag1(String flag1) {
	this.flag1 = flag1;
}

public String getSuggby() {
	return suggby;
}

public void setSuggby(String suggby) {
	this.suggby = suggby;
}

public String getTitcd() {
	return titcd;
}

public void setTitcd(String titcd) {
	this.titcd = titcd;
}

public void setTitnm(String titnm) {
	this.titnm = titnm;
}

public String getSup_nm() {
	return sup_nm;
}

public String getFlag_sup() {
	return flag_sup;
}

public Vector getImpl_by_days11() {
	return impl_by_days11;
}

public void setImpl_by_days11(Vector impl_by_days11) {
	this.impl_by_days11 = impl_by_days11;
}

public Vector getImpl_time_dt11() {
	return impl_time_dt11;
}

public void setImpl_time_dt11(Vector impl_time_dt11) {
	this.impl_time_dt11 = impl_time_dt11;
}

public Vector getSugg_dt1() {
	return sugg_dt1;
}

public void setSugg_dt1(Vector sugg_dt1) {
	this.sugg_dt1 = sugg_dt1;
}

public Vector getTitle_cd21() {
	return title_cd21;
}

public void setTitle_cd21(Vector title_cd21) {
	this.title_cd21 = title_cd21;
}

public Vector getTitle_nm11() {
	return title_nm11;
}

public void setTitle_nm11(Vector title_nm11) {
	this.title_nm11 = title_nm11;
}

public Vector getSugg_by1() {
	return sugg_by1;
}

public Vector getEmp_nm_under_sup() {
	return emp_nm_under_sup;
}

public Vector getAction_sts() {
	return action_sts;
}

public void setAction_sts(Vector action_sts) {
	this.action_sts = action_sts;
}

public Vector getAction_sts1() {
	return action_sts1;
}

public Vector getStatus_sup() {
	return status_sup;
}

public String getRmk_by_sup() {
	return rmk_by_sup;
}

public String getAct_by_sup() {
	return act_by_sup;
}

public Vector getAction_sts1_hr() {
	return action_sts1_hr;
}

public void setAction_sts1_hr(Vector action_sts1_hr) {
	this.action_sts1_hr = action_sts1_hr;
}

public Vector getEmp_nm_under_sup_hr() {
	return emp_nm_under_sup_hr;
}

public void setEmp_nm_under_sup_hr(Vector emp_nm_under_sup_hr) {
	this.emp_nm_under_sup_hr = emp_nm_under_sup_hr;
}

public Vector getImpl_by_days11_hr() {
	return impl_by_days11_hr;
}

public void setImpl_by_days11_hr(Vector impl_by_days11_hr) {
	this.impl_by_days11_hr = impl_by_days11_hr;
}

public Vector getImpl_time_dt11_hr() {
	return impl_time_dt11_hr;
}

public void setImpl_time_dt11_hr(Vector impl_time_dt11_hr) {
	this.impl_time_dt11_hr = impl_time_dt11_hr;
}

public Vector getStatus_sup_hr() {
	return status_sup_hr;
}

public void setStatus_sup_hr(Vector status_sup_hr) {
	this.status_sup_hr = status_sup_hr;
}

public Vector getSugg_by1_hr() {
	return sugg_by1_hr;
}

public void setSugg_by1_hr(Vector sugg_by1_hr) {
	this.sugg_by1_hr = sugg_by1_hr;
}

public Vector getSugg_dt1_hr() {
	return sugg_dt1_hr;
}

public void setSugg_dt1_hr(Vector sugg_dt1_hr) {
	this.sugg_dt1_hr = sugg_dt1_hr;
}

public Vector getTitle_nm11_hr() {
	return title_nm11_hr;
}

public void setTitle_nm11_hr(Vector title_nm11_hr) {
	this.title_nm11_hr = title_nm11_hr;
}

public Vector getTitle_cd21_hr() {
	return title_cd21_hr;
}

public String getSet_desg_cd() {
	return set_desg_cd;
}

public void setSet_desg_cd(String set_desg_cd) {
	this.set_desg_cd = set_desg_cd;
}

public void setSet_dept_cd(String set_dept_cd) {
	this.set_dept_cd = set_dept_cd;
}

public Vector getDept_cd11() {
	return dept_cd11;
}

public void setDept_cd11(Vector dept_cd11) {
	this.dept_cd11 = dept_cd11;
}

public Vector getDesg_cd11() {
	return desg_cd11;
}

public void setDesg_cd11(Vector desg_cd11) {
	this.desg_cd11 = desg_cd11;
}

public Vector getDesg_nm11() {
	return desg_nm11;
}

public void setDesg_nm11(Vector desg_nm11) {
	this.desg_nm11 = desg_nm11;
}

public Vector getEmp_cd_hr() {
	return emp_cd_hr;
}

public void setEmp_cd_hr(Vector emp_cd_hr) {
	this.emp_cd_hr = emp_cd_hr;
}

public Vector getEmp_nm_hr() {
	return emp_nm_hr;
}

public void setEmp_nm_hr(Vector emp_nm_hr) {
	this.emp_nm_hr = emp_nm_hr;
}

public Vector getDept_nm11() {
	return dept_nm11;
}

public Vector getStatus_hr() {
	return status_hr;
}

public String getEmp_nm_assgn_user() {
	return emp_nm_assgn_user;
}

public String getHr_emp_cd() {
	return hr_emp_cd;
}
public String getHr_emp_cd1() {
	return hr_emp_cd1;
}
public Vector getSel_sup_cd() {
	return sel_sup_cd;
}
public String getSup_cd() {
	return sup_cd;
}

public void setTit_cd_assgn_user(String tit_cd_assgn_user) {
	this.tit_cd_assgn_user = tit_cd_assgn_user;
}

public Vector getEmp_cd_assgn_by_hr() {
	return emp_cd_assgn_by_hr;
}

public Vector getEmp_nm_assgn_by_hr() {
	return emp_nm_assgn_by_hr;
}

public Vector getStatus_hr_track() {
	return status_hr_track;
}

public void setStatus_hr_track(Vector status_hr_track) {
	this.status_hr_track = status_hr_track;
}

public Vector getStatus_sup_track() {
	return status_sup_track;
}

public void setStatus_sup_track(Vector status_sup_track) {
	this.status_sup_track = status_sup_track;
}

public Vector getSugg_by1_track() {
	return sugg_by1_track;
}

public void setSugg_by1_track(Vector sugg_by1_track) {
	this.sugg_by1_track = sugg_by1_track;
}

public Vector getSugg_dt1_track() {
	return sugg_dt1_track;
}

public void setSugg_dt1_track(Vector sugg_dt1_track) {
	this.sugg_dt1_track = sugg_dt1_track;
}

public Vector getTitle_nm_track() {
	return title_nm_track;
}

public void setTitle_nm_track(Vector title_nm_track) {
	this.title_nm_track = title_nm_track;
}

public Vector getTitle_cd_track() {
	return title_cd_track;
}

public Vector getStatus_sup_dt() {
	return status_sup_dt;
}

public Vector getStatus_hr_dt() {
	return status_hr_dt;
}

public Vector getHr_act_dt() {
	return hr_act_dt;
}

public void setHr_act_dt(Vector hr_act_dt) {
	this.hr_act_dt = hr_act_dt;
}

public Vector getSup_act_dt() {
	return sup_act_dt;
}

public void setFlag_track(String flag_track) {
	this.flag_track = flag_track;
}

public Vector getSup_action_dt() {
	return sup_action_dt;
}

public Vector getSugg_by_for_pdf() {
	return sugg_by_for_pdf;
}

public void setSugg_by_for_pdf(Vector sugg_by_for_pdf) {
	this.sugg_by_for_pdf = sugg_by_for_pdf;
}

public Vector getTitle_nm_for_pdf() {
	return title_nm_for_pdf;
}

public void setTitle_nm_for_pdf(Vector title_nm_for_pdf) {
	this.title_nm_for_pdf = title_nm_for_pdf;
}

public Vector getTitle_cd_for_pdf() {
	return title_cd_for_pdf;
}

public String getSugg_brief() {
	return sugg_brief;
}

public Vector getVsugg() {
	return Vsugg;
}

public String getSugg_brf_bene() {
	return sugg_brf_bene;
}

public void setSearch_char(String search_char) {
	this.search_char = search_char;
}

//////////SB20170216//////////////

public void CommitteeAction() throws SQLException
{
	try
	{
		String q1="select emp_nm,comp_cd,emp_alias_cd from hr_emp_mst where emp_cd='"+suggby+"'";
	//	System.out.println("---q1--"+q1);
		rset=stmt.executeQuery(q1);
        if(rset.next())
		{
        	emp_nm1=rset.getString(1)==null?"":rset.getString(1);
        	comp_cd1=rset.getString(2)==null?"":rset.getString(2);
        	emp_alias_cd=rset.getString(3)==null?"":rset.getString(3);
		}
        
        String query112 ="select a.reporting from hr_emp_rel_mst a where "
        	//	+ "a.comp_cd='"+comp_cd1+"' and "
        				+ "a.emp_cd='"+suggby+"' order by reporting    ";
		   // System.out.println("query11--"+query112);
		rset=stmt.executeQuery(query112);
		while (rset.next())
		{
			//dept_nm1=rset.getString(1);
			//dept_cd1=rset.getString(2);
			line_mgr=rset.getString(1)==null?"":rset.getString(1);
		}
		dept_nm1="";dept_cd1="";desg_cd1="";desg_nm1="";
        String query11 ="select distinct(b.dept_nm),a.dept_cd from hr_emp_rel_mst a, " +
        		"hr_dept_mst b where a.dept_cd=b.dept_cd "+
        	//	+ "and b.comp_cd='"+comp_cd1+"' " +
        		"and a.emp_cd='"+suggby+"' order by dept_nm    ";
        //System.out.println("query11--"+query11);
	rset=stmt.executeQuery(query11);
	while (rset.next())
	{
		dept_nm1=rset.getString(1)==null?"":rset.getString(1);
		dept_cd1=rset.getString(2)==null?"":rset.getString(2);
		//line_mgr=rset.getString(3);
	}
	
	String qury="select distinct(d.desg_cd),d.desg_nm from hr_emp_rel_mst a," +
			"hr_dept_mst b, hr_desg_mst d,hr_emp_mst e " +
			"where a.dept_cd=b.dept_cd and  b.dept_cd='"+dept_cd1+"' and " +
			" a.desg_cd=d.desg_cd and e.emp_cd='"+suggby+"' "+
			//and b.comp_cd='"+comp_cd1+"' " +
			"and a.emp_cd=e.emp_cd  order by desg_nm";
	//System.out.println("qury000---"+qury);
	rset=stmt.executeQuery(qury);
	while (rset.next())
	{
		desg_cd1=rset.getString(1)==null?"":rset.getString(1);
		desg_nm1=rset.getString(2)==null?"":rset.getString(2);
	
	}
//	String query2="Select distinct(reporting) from hr_emp_rel_mst  where comp_cd='"+comp_cd1+"' and branch_cd='"+branch_cd+"' order by reporting ";
//	rset=stmt.executeQuery(query2);
//	//System.out.println("query2--"+query2);
//	while (rset.next())
//	{
//		res_sup.add(rset.getString(1)==null?"":rset.getString(1));
//	}
	
		String query_cordiantor="  select emptyp_cd from hr_emptyp_mst where (lower(emptyp_nm) LIKE '%sahyog co-ordinator%'" +
				"or lower(emptyp_nm) LIKE '%sahayog coordinator%' " +
				"or lower(emptyp_nm) LIKE 'sahayog co-ordinator%'" +
				" or lower(emptyp_nm) LIKE 'sahyog co-ordinator%' )  ";
	//	System.out.println("----qq------"+query_cordiantor);
		rset=stmt.executeQuery(query_cordiantor);
		if(rset.next())
		{
			categry_cd=rset.getString(1);
		}
		String query1="SELECT A.EMP_CD,A.CATEGRY_CD FROM TIMS_EMP_CATG_DTL A WHERE A.EFF_DT = " +
				"(SELECT MAX(B.EFF_DT) FROM TIMS_EMP_CATG_DTL B WHERE A.EMP_CD=B.EMP_CD AND A.CATEGRY_CD=B.CATEGRY_CD " +
				"AND A.COMP_CD=B.COMP_CD ) AND A.CATEGRY_CD='"+categry_cd+"'  ";
		//System.out.println("Query For Fetching categry cd 27 employeees---"+query1);
		rset=stmt.executeQuery(query1);
		while(rset.next())
		{
			Vhr_emp_cd.add(rset.getString(1)==null?"":rset.getString(1));
//			Vcat_cd.add(rset.getString(2)==null?"":rset.getString(2));
		}
		//System.out.println("Vhr_emp_cd--"+Vhr_emp_cd);
	
		String query3="select a.title_cd, a.title_nm, b.impl_by_days, b.impl_cost," +
				" to_char(a.impl_time_dt,'dd/mm/yyyy'), b.sugg_desc, b.sugg_benefit,b.sugg_brief_benefit  " +
				", OUTCOME_REGD, to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss'),a.supervisor_cd " +
				", MEETING_VENUE, COMM_RECO, to_char(COMM_MEETING_DT,'dd/mm/yyyy'),hr_action_status_reason,"
				+ "b.cost_reduction,b.estimated_saving " +
				" , HR_ACTION_STATUS,ACTION_DTL,ACTION_FLAG,VERIFY_FLAG,a.impl_cost,beneficial_flag,"
				+ "beneficial_amount,to_char(a.action_dt,'dd/mm/yyyy hh24:mi:ss'),to_char(a.COMMITTEE_DECISION_DT,'dd/mm/yyyy'),EXEC_LEAD,moc_flag,"
				+ "moc_rmk,to_char(supervisor_action_dt,'dd/mm/yyyy hh24:mi:ss'),COMM_ACTUAL_SAVINGS " +
				"from  sugg_title_mst a, sugg_title_dtl b where a.title_cd= b.title_cd and  b.flag='Y' and a.title_cd='"+titcd+"'  ";
		rset=stmt.executeQuery(query3);
		//System.out.println("query3- here-"+query3);
		while (rset.next())
		{
			title_cd=rset.getString(1)==null?"":rset.getString(1);
			title_nm=rset.getString(2)==null?"":rset.getString(2);
			impl_by_days=rset.getString(3)==null?"":rset.getString(3);
			impl_cost=rset.getString(4)==null?"":rset.getString(4);
			impl_time_dt=rset.getString(5)==null?"":rset.getString(5);
			sugg_desc=rset.getString(6)==null?"":rset.getString(6);
			sugg_benefit=rset.getString(7)==null?"":rset.getString(7);
			sugg_brief=rset.getString(8)==null?"":rset.getString(8);
			outcome_regd=rset.getString(9)==null?"":rset.getString(9);
			outcome_regd_dt=rset.getString(10)==null?"":rset.getString(10);
			res_supervisor1=rset.getString(11)==null?"":rset.getString(11);
			committee_venue=rset.getString(12)==null?"":rset.getString(12);
			committee_reco=rset.getString(13)==null?"":rset.getString(13);
			committee_meeting_dt=rset.getString(14)==null?"":rset.getString(14);
			hr_hld_reason=rset.getString(15)==null?"":rset.getString(15);
			cost_reduce=rset.getString(16)==null?"":rset.getString(16);
			estimated_save=rset.getString(17)==null?"":rset.getString(17);
			hr_action_status=rset.getString(18)==null?"":rset.getString(18);
			act_rmk=rset.getString(19)==null?"":rset.getString(19);
			act_flg=rset.getString(20)==null?"":rset.getString(20);
			Ver_flg=rset.getString(21)==null?"":rset.getString(21);
			awd_amt=rset.getString(22)==null?"":rset.getString(22);
			bene_flg=rset.getString(23)==null?"":rset.getString(23);
			ben_amt=rset.getString(24)==null?"":rset.getString(24);
			Actdt=rset.getString(25)==null?"":rset.getString(25);
			COMMITTEE_DECIDE_DT=rset.getString(26)==null?"":rset.getString(26);
			EXEC_LEAD_CD=rset.getString(27)==null?"":rset.getString(27);
			moc_flag=rset.getString(28)==null?"":rset.getString(28);
			moc_rmk=rset.getString(29)==null?"":rset.getString(29);
			Ssup_actdt=rset.getString(30)==null?"":rset.getString(30);
			COMM_ACTUAL_SAVINGS=rset.getString(31)==null?"":rset.getString(31);
		}
		String q2="select nvl(emp_nm,''),comp_cd from hr_emp_mst where emp_cd='"+res_supervisor1+"'";
		rset=stmt.executeQuery(q2);
        if(rset.next())
		{
        	Ssupemp_nm1=rset.getString(1)==null?"":rset.getString(1);
		}
        
        q2="select emp_cd from hr_emp_mst where emp_nm='"+line_mgr.trim()+"'";
		rset=stmt.executeQuery(q2);
        if(rset.next())
		{
        	line_mgrcd=rset.getString(1)==null?"":rset.getString(1);
		}
        ///////////////////////
        String q3="select nvl(emp_nm,'') from hr_emp_mst where emp_cd='"+EXEC_LEAD_CD+"'";
        rset=stmt.executeQuery(q3);
        if(rset.next())
		{
        	Exec_lead_emp_nm=rset.getString(1)==null?"":rset.getString(1);
		}
        /////////////////////////
        flag_committee="";
        queryString="select title_cd from sugg_email_dtl where title_cd='"+titcd+"'";
        rset=stmt.executeQuery(queryString);
        if(rset.next()){
        	flag_committee="Y";
        }
        
        
        String AssignedUser="";
     String  query="select A.EMP_CD, B.EMP_NM from SUGG_ASSIGN_DTL A, HR_EMP_MST B where A.TITLE_CD='"+titcd+"' AND A.EMP_CD=B.EMP_CD order by A.emp_cd ";
        //System.out.println("ASSIGNED MEMBERS: "+query);
		rset=stmt.executeQuery(query);
       while(rset.next())
		{
    	   queryString="select emp_cd from sugg_email_dtl where title_cd='"+titcd+"' and emp_cd='"+rset.getString(1)+"'";
    	   rset1=stmt1.executeQuery(queryString);
    	   if(rset1.next()){
    		 	AssignedUser +="<font color='blue' title='Mail Sent for committee response'>"+rset.getString(2)+"</font>, ";
    	   }else{
    		 	AssignedUser +=rset.getString(2)+", ";
    	   }
      
		}
       committee_members1 = AssignedUser;
	
       ////////////////// PDF File Creation////////////////////
			String f1="";
			String f2="";
			String path=request.getRealPath("/PDF");
			f2="SUGGESTION-"+titcd+".pdf";
			f1="SUGGESTION-"+titcd;
			f1=path+"/"+f1;
			File pdf_file=new File(f1+".pdf");
			if(pdf_file.exists())
			{
					path=path+"//"+pdf_file;
		            String context_nm = request.getContextPath();
					String server_nm = request.getServerName();
					String server_port = ""+request.getServerPort();
					String url_start = "http://"+server_nm+":"+server_port+context_nm;
					 pdfpath = path;
					pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
					pdfpath = url_start+"/PDF/"+f2;
			}
       
	}
	catch(SQLException e)
	{
		System.err.println("Error"+e);
		System.err.println("..DataBean_TIMS.java.in Query :.."+queryString);
		e.printStackTrace();
	}
     
}//end of //RG20170207


/////////////////////: New Function://////////////////
String hr_hld_reason="";String act_rmk="";String act_flg="";String Ver_flg="";String awd_amt="";String bene_flg="";String ben_amt="";
String EXEC_LEAD_CD="";String Exec_lead_emp_nm="";Vector VEXEC_LEAD_CD=new Vector();String moc_flag="";String moc_rmk="";
public void RequesterAction() throws SQLException
{
	try
	{
//		String branch_cd="1";
//		String query="select emp_cd from hr_emp_rel_mst where desg_cd='191' and dept_cd='138' and branch_cd='"+branch_cd+"' ";
////		System.out.println("---"+query);
//		rset=stmt.executeQuery(query);
//		if(rset.next())
//		{
//			hr_emp_cd1=rset.getString(1);
//		}
		String q1="select emp_nm,comp_cd,emp_alias_cd from hr_emp_mst where emp_cd='"+suggby+"'";
//		System.out.println("---q1--"+q1);
		rset=stmt.executeQuery(q1);
        if(rset.next())
		{
        	emp_nm1=rset.getString(1)==null?"":rset.getString(1);
        	comp_cd1=rset.getString(2)==null?"":rset.getString(2);
        	emp_alias_cd=rset.getString(3)==null?"":rset.getString(3);
		}
        String query112 ="select a.reporting from hr_emp_rel_mst a where "
        		//+ "a.comp_cd='"+comp_cd1+"' and "
				+ "a.emp_cd='"+suggby+"' order by reporting    ";
		   // System.out.println("query11--"+query112);
		rset=stmt.executeQuery(query112);
		while (rset.next())
		{
			//dept_nm1=rset.getString(1);
			//dept_cd1=rset.getString(2);
			line_mgr=rset.getString(1)==null?"":rset.getString(1);
		}
		dept_nm1="";dept_cd1="";desg_cd1="";desg_nm1="";
        
        String query11 ="select distinct(b.dept_nm),a.dept_cd from hr_emp_rel_mst a, " +
        		"hr_dept_mst b where a.dept_cd=b.dept_cd "+
        		//+ "and b.comp_cd='"+comp_cd1+"' " +
        		"and a.emp_cd='"+suggby+"' order by dept_nm    ";
//        System.out.println("query11--"+query11);
	rset=stmt.executeQuery(query11);
	while (rset.next())
	{
		dept_nm1=rset.getString(1)==null?"":rset.getString(1);
		dept_cd1=rset.getString(2)==null?"":rset.getString(2);
		//line_mgr=rset.getString(3);
	}
	
	String qury="select distinct(d.desg_cd),d.desg_nm from hr_emp_rel_mst a," +
			"hr_dept_mst b, hr_desg_mst d,hr_emp_mst e " +
			"where a.dept_cd=b.dept_cd and  b.dept_cd='"+dept_cd1+"' and " +
			" a.desg_cd=d.desg_cd and e.emp_cd='"+suggby+"'  "+
					//+ "and b.comp_cd='"+comp_cd1+"' " +
			"and a.emp_cd=e.emp_cd  order by desg_nm";
	//System.out.println("qury000---"+qury);
	rset=stmt.executeQuery(qury);
	while (rset.next())
	{
		desg_cd1=rset.getString(1)==null?"":rset.getString(1);
		desg_nm1=rset.getString(2)==null?"":rset.getString(2);
	
	}
//	String query2="Select distinct(reporting) from hr_emp_rel_mst  where comp_cd='"+comp_cd1+"' and branch_cd='"+branch_cd+"' order by reporting ";
//	rset=stmt.executeQuery(query2);
////	System.out.println("query2--"+query2);
//	while (rset.next())
//	{
//		res_sup.add(rset.getString(1)==null?"":rset.getString(1));
//	}
	
	//System.out.println("res_sup--"+res_sup.size());
		String query3="select a.title_cd, a.title_nm, b.impl_by_days, b.impl_cost," +
				" to_char(a.impl_time_dt,'dd/mm/yyyy'), b.sugg_desc, b.sugg_benefit,b.sugg_brief_benefit  " +
				", OUTCOME_REGD, to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss'),a.supervisor_cd " +
				", MEETING_VENUE, COMM_RECO, to_char(COMM_MEETING_DT,'dd/mm/yyyy'),hr_action_status_reason,b.cost_reduction,b.estimated_saving " +
				" , HR_ACTION_STATUS,ACTION_DTL,ACTION_FLAG,a.IMPL_COST,VERIFY_FLAG,BENEFICIAL_AMOUNT,BENEFICIAL_FLAG,MOC_FLAG,MOC_RMK "
				+ ",to_char(committee_decision_dt,'dd/mm/yyyy'),to_char(action_dt,'dd/mm/yyyy  hh24:mi:ss'),COMM_ACTUAL_SAVINGS " +
				"from  sugg_title_mst a, sugg_title_dtl b where a.title_cd= b.title_cd and  b.flag='Y' and a.title_cd='"+titcd+"'  ";
		rset=stmt.executeQuery(query3);
//		System.out.println("query3- here-"+query3);
		while (rset.next())
		{
			title_cd=rset.getString(1)==null?"":rset.getString(1);
			title_nm=rset.getString(2)==null?"":rset.getString(2);
			impl_by_days=rset.getString(3)==null?"":rset.getString(3);
			impl_cost=rset.getString(4)==null?"":rset.getString(4);
			impl_time_dt=rset.getString(5)==null?"":rset.getString(5);
			sugg_desc=rset.getString(6)==null?"":rset.getString(6);
			sugg_benefit=rset.getString(7)==null?"":rset.getString(7);
			sugg_brief=rset.getString(8)==null?"":rset.getString(8);
			outcome_regd=rset.getString(9)==null?"":rset.getString(9);
			outcome_regd_dt=rset.getString(10)==null?"":rset.getString(10);
			res_supervisor1=rset.getString(11)==null?"":rset.getString(11);
			committee_venue=rset.getString(12)==null?"":rset.getString(12);
			committee_reco=rset.getString(13)==null?"":rset.getString(13);
			committee_meeting_dt=rset.getString(14)==null?"":rset.getString(14);
			hr_hld_reason=rset.getString(15)==null?"":rset.getString(15);
			cost_reduce=rset.getString(16)==null?"":rset.getString(16);
			estimated_save=rset.getString(17)==null?"":rset.getString(17);
			hr_action_status=rset.getString(18)==null?"":rset.getString(18);
			act_rmk=rset.getString(19)==null?"":rset.getString(19);
			act_flg=rset.getString(20)==null?"":rset.getString(20);
			awd_amt=rset.getString(21)==null?"":rset.getString(21);
			Ver_flg=rset.getString(22)==null?"":rset.getString(22);
			ben_amt=rset.getString(23)==null?"":rset.getString(23);
			bene_flg=rset.getString(24)==null?"":rset.getString(24);
			moc_flag=rset.getString(25)==null?"":rset.getString(25);
			moc_rmk=rset.getString(26)==null?"":rset.getString(26);
			COMMITTEE_DECIDE_DT=rset.getString(27)==null?"":rset.getString(27);
			action_dt=rset.getString(28)==null?"":rset.getString(28);
			COMM_ACTUAL_SAVINGS=rset.getString(29)==null?"":rset.getString(29);
		}
		String q2="select nvl(emp_nm,''),nvl(comp_cd,'') from hr_emp_mst where emp_cd='"+res_supervisor1+"'";
		rset=stmt.executeQuery(q2);
        if(rset.next())
		{
        	Ssupemp_nm1=rset.getString(1)==null?"":rset.getString(1);
		}
        /*q2="select emp_nm,comp_cd from hr_emp_mst where emp_cd='"+verify_cd+"'";
		rset=stmt.executeQuery(q2);
        if(rset.next())
		{
        	verify_nm=rset.getString(1);
		}*/
        String AssignedUser="";
       String query="select A.EMP_CD, B.EMP_NM from SUGG_ASSIGN_DTL A, HR_EMP_MST B where A.TITLE_CD='"+titcd+"' AND A.EMP_CD=B.EMP_CD ";
//        System.out.println("ASSIGNED MEMBERS: "+query);
		rset=stmt.executeQuery(query);
       while(rset.next())
		{
       	AssignedUser +=rset.getString(2)+", ";
		}
       committee_members1 = AssignedUser;
		//String dt=sugg_brief.substring(48,remark.length()-1);
		
       ///////////////////////////////////////////////////////////////////
       
	}
	catch(SQLException e)
	{
		System.err.println("Error"+e);
		System.err.println("..DataBean_TIMS.java.in Query :.."+queryString);
		e.printStackTrace();
	}
     
}//end of //RG20170207

public void Archived_Suggestions() throws SQLException
{
	try
	{
	
//		String query3="select a.title_cd, a.title_nm, b.impl_by_days, b.impl_cost," +
//				" to_char(a.impl_time_dt,'dd/mm/yyyy'), b.sugg_desc, b.sugg_benefit,b.sugg_brief_benefit  " +
//				", OUTCOME_REGD, to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss'),a.supervisor_cd " +
//				", MEETING_VENUE, COMM_RECO, to_char(COMM_MEETING_DT,'dd/mm/yyyy'),hr_action_status_reason,b.cost_reduction,b.estimated_saving " +
//				" , HR_ACTION_STATUS,ACTION_DTL,ACTION_FLAG,a.IMPL_COST,VERIFY_FLAG,BENEFICIAL_AMOUNT," +
//				"BENEFICIAL_FLAG,a.remark_by_supervisor,a.action_by_supervisor,a.status," +
//				"to_char(a.supervisor_action_dt,'dd/mm/yyyy hh24:mi:ss'),to_char(action_dt,'dd/mm/yyyy hh24:mi:ss'),to_char(a.hr_action_dt,'dd/mm/yyyy hh24:mi:ss')  from sugg_title_mst where status='RETURN' OR outcome_regd='NONIMPLEMENTABLE' OR actual_closer_dt is not null order by title_cd desc";
//		rset=stmt.executeQuery(query3);
////		System.out.println("query3- here-"+query3);
//		while (rset.next())
//		{
//			title_cd_rpt.add(rset.getString(1)==null?"":rset.getString(1));
//			title_nm_rpt.add(rset.getString(2)==null?"":rset.getString(2));
//		}
		 String query3="select a.title_cd,nvl(a.title_nm,''),nvl(to_char(a.sugg_dt,'dd/mm/yyyy'),''),nvl(a.sugg_by,''),a.action_by_supervisor,a.status,to_char(a.SUPERVISOR_ACTION_DT,'dd/mm/yyyy hh24:mi:ss'),"
		 		+ "a.hr_action_status,to_char(a.hr_action_dt,'dd/mm/yyyy hh24:mi:ss'), " +
			 		"OUTCOME_REGD,to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss') " +
			 		"from sugg_title_mst a " +
			 		" where (status='RETURN' OR outcome_regd='NONIMPLEMENTABLE' OR actual_closer_dt is not null) "
			 		+ "order by title_cd DESC ";
			rset=stmt.executeQuery(query3);
			System.out.println("query3-For Fetching Hr data-"+query3);
			
			while (rset.next())
			{
				title_cd_rpt.add(rset.getString(1)==null?"":rset.getString(1));
				title_nm_rpt.add(rset.getString(2)==null?"":rset.getString(2));
				sugg_dt.add(rset.getString(3)==null?"":rset.getString(3));
				sugg_by1_hr.add(rset.getString(4)==null?"":rset.getString(4));
				sup_action.add(rset.getString(5)==null?"":rset.getString(5));
				sup_status.add(rset.getString(6)==null?"":rset.getString(6));
				sup_action_dt.add(rset.getString(7)==null?"":rset.getString(7));
				action_sts.add(rset.getString(8)==null?"-":rset.getString(8));
				hr_act_dt.add(rset.getString(9)==null?"-":rset.getString(9));
				OUTCOME_REGD.add(rset.getString(10)==null?"-":rset.getString(10));
				OUTCOME_REGD_DT.add(rset.getString(11)==null?"-":rset.getString(11));
			}
			
			for(int k=0;k<sugg_by1_hr.size();k++){
				String q2="select emp_nm from hr_emp_mst where emp_cd='"+sugg_by1_hr.elementAt(k)+"' ";
				//System.out.println("---"+q2);
				rset=stmt.executeQuery(q2);
				if(rset.next())
				{
					emp_nm_under_sup_hr.add(rset.getString(1)==null?"":rset.getString(1));
				}else{
					emp_nm_under_sup_hr.add("");
				}		
			}
	
       
	}
	catch(SQLException e)
	{
		System.err.println("Error"+e);
		System.err.println("..DataBean_TIMS.java.in Query :.."+queryString);
		e.printStackTrace();
	}
     
}//end of //RG20170207
public void Fetch_Pending_Sugg_Data() throws SQLException
{
	try
	{
		//FOR SUPERVISOR LEVEL PENDING//
		
			String query="SELECT TITLE_CD ,TITLE_NM,SUGG_BY,SUPERVISOR_CD,TO_CHAR(SUGG_DT,'DD/MM/YYYY') FROM SUGG_TITLE_MST WHERE (ACTION_BY_SUPERVISOR IS NULL "
		 			+ "OR ACTION_BY_SUPERVISOR='') AND SUGG_DT IS NOT NULL ORDER BY TITLE_CD ";
			rset=stmt.executeQuery(query);
			//System.out.println("query3-sup-"+query);
			while (rset.next())
			{
				title_cd_rpt.add(rset.getString(1)==null?"":rset.getString(1));
				title_nm_rpt.add(rset.getString(2)==null?"":rset.getString(2));
				sugg_dt_rpt.add(rset.getString(5)==null?"":rset.getString(5));
//				sugg_by_rpt.add(rset.getString(3)==null?"":rset.getString(3));
//				sup.add(rset.getString(4)==null?"":rset.getString(4));
				
				queryString="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(4)+"' ";
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next()){
					sup_nm_p.add(rset1.getString(1)==null?"":rset1.getString(1));
				}else{
					sup_nm_p.add("");
				}
				
				queryString="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(3)+"' ";
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next()){
					sugg_by.add(rset1.getString(1)==null?"":rset1.getString(1));
				}else{
					sugg_by.add("");
				}
			}
			
			/*query="SELECT TITLE_CD ,TITLE_NM,SUGG_BY,SUPERVISOR_CD FROM SUGG_TITLE_MST WHERE (ACTION_BY_SUPERVISOR IS NULL "
		 			+ "OR ACTION_BY_SUPERVISOR='') AND SUGG_DT IS NOT NULL ORDER BY TITLE_CD ";
			rset=stmt.executeQuery(query);
			System.out.println("query3--"+query);
			while (rset.next())
			{
				title_cd_rpt.add(rset.getString(1)==null?"":rset.getString(1));
				title_nm_rpt.add(rset.getString(2)==null?"":rset.getString(2));
				sugg_by_rpt.add(rset.getString(3)==null?"":rset.getString(3));
				sup.add(rset.getString(4)==null?"":rset.getString(4));
				
				queryString="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(4)+"' ";
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next()){
					sup_nm_p.add(rset1.getString(1)==null?"":rset1.getString(1));
				}else{
					sup_nm_p.add("");
				}
				
				queryString="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(3)+"' ";
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next()){
					sugg_by.add(rset1.getString(1)==null?"":rset1.getString(1));
				}else{
					sugg_by.add("");
				}
			}*/
			//FOR COMMITTEE//
			query="SELECT A.TITLE_CD ,TITLE_NM,SUGG_BY,A.EMP_CD,TO_CHAR(SUGG_DT,'DD/MM/YYYY') FROM SUGG_EMAIL_DTL A,SUGG_TITLE_MST B WHERE (COMMITTEE_DECISION_FLAG IS NULL OR"
					+ " COMMITTEE_DECISION_FLAG='') AND A.TITLE_CD=B.TITLE_CD AND HR_ACTION_STATUS='ASSIGNED' AND (OUTCOME_REGD IS NULL OR OUTCOME_REGD='') ORDER BY TITLE_CD";
			rset=stmt.executeQuery(query);
			//System.out.println("query3--"+query);
			while (rset.next())
			{
				title_cd_COMM.add(rset.getString(1)==null?"":rset.getString(1));
				title_NM_COMM.add(rset.getString(2)==null?"":rset.getString(2));
				sugg_DT_COMM.add(rset.getString(5)==null?"":rset.getString(5));
//				sugg_bycd_COMM.add(rset.getString(3)==null?"":rset.getString(3));
//				sugg_commempcd_COMM.add(rset.getString(4)==null?"":rset.getString(4));
				
				queryString="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(4)+"' ";
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next()){
					sugg_commempnm_COMM.add(rset1.getString(1)==null?"":rset1.getString(1));
				}else{
					sugg_commempnm_COMM.add("");
				}
				
				queryString="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(3)+"' ";
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next()){
					sugg_bynm_COMM.add(rset1.getString(1)==null?"":rset1.getString(1));
				}else{
					sugg_bynm_COMM.add("");
				}
			}
			
			//FOR COMMITTTEE END
			
			//FOR Requester//
			query="SELECT TITLE_CD ,TITLE_NM,SUGG_BY,TO_CHAR(SUGG_DT,'DD/MM/YYYY') FROM SUGG_TITLE_MST WHERE OUTCOME_REGD ='IMPLEMENTABLE' AND"
					+ " (ACTION_FLAG IS NULL OR ACTION_FLAG ='')  ORDER BY TITLE_CD";
			rset=stmt.executeQuery(query);
			//System.out.println("query3--"+query);
			while (rset.next())
			{
				title_cd_req.add(rset.getString(1)==null?"":rset.getString(1));
				title_NM_req.add(rset.getString(2)==null?"":rset.getString(2));
				sugg_DT_req.add(rset.getString(4)==null?"":rset.getString(4));
				//sugg_bycd_req.add(rset.getString(3)==null?"":rset.getString(3));
//				sugg_commempcd_req.add(rset.getString(4)==null?"":rset.getString(4));
				
				queryString="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(3)+"' ";
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next()){
					sugg_commempnm_req.add(rset1.getString(1)==null?"":rset1.getString(1));
					sugg_bynm_req.add(rset1.getString(1)==null?"":rset1.getString(1));
				}else{
					sugg_commempnm_req.add("");
					sugg_bynm_req.add("");
				}
				
				/*queryString="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(3)+"' ";
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next()){
					sugg_bynm_req.add(rset1.getString(1)==null?"":rset1.getString(1));
				}else{
					sugg_bynm_req.add("");
				}*/
			}
			
			//FOR Requester END
			
			//FOR Line manager//
			query="SELECT TITLE_CD ,TITLE_NM,SUGG_BY,TO_CHAR(SUGG_DT,'DD/MM/YYYY') FROM SUGG_TITLE_MST WHERE HR_ACTION_STATUS_REASON='Y' AND ACTION_FLAG IS NOT NULL AND"
					+ " (EXEC_LEAD IS NULL OR EXEC_LEAD='')  ORDER BY TITLE_CD";
			rset=stmt.executeQuery(query);
			//System.out.println("query3--"+query);
			while (rset.next())
			{
				title_cd_lm.add(rset.getString(1)==null?"":rset.getString(1));
				title_NM_lm.add(rset.getString(2)==null?"":rset.getString(2));
				sugg_DT_lm.add(rset.getString(4)==null?"":rset.getString(4));
			//	sugg_bycd_lm.add(rset.getString(3)==null?"":rset.getString(3));
				
				queryString="SELECT REPORTING FROM HR_EMP_REL_MST WHERE EMP_CD='"+rset.getString(3)+"' ";
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next()){
					sugg_commempnm_lm.add(rset1.getString(1)==null?"-":rset1.getString(1));
				}else{
					sugg_commempnm_lm.add("");
				}
				
				queryString="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(3)+"' ";
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next()){
					sugg_bynm_lm.add(rset1.getString(1)==null?"":rset1.getString(1));
				}else{
					sugg_bynm_lm.add("");
				}
			}
			
			//FOR Line manager END
			
			//FOR ET//
			query="SELECT TITLE_CD ,TITLE_NM,SUGG_BY,EXEC_LEAD,TO_CHAR(SUGG_DT,'DD/MM/YYYY') FROM SUGG_TITLE_MST WHERE EXEC_LEAD IS NOT NULL AND (ACTUAL_CLOSER_DT IS NULL OR "
					+ "ACTUAL_CLOSER_DT='')  ORDER BY TITLE_CD";
			rset=stmt.executeQuery(query);
			//System.out.println("query3-lead-"+query);
			while (rset.next())
			{
				title_cd_lead.add(rset.getString(1)==null?"":rset.getString(1));
				title_NM_lead.add(rset.getString(2)==null?"":rset.getString(2));
				sugg_dt_lead.add(rset.getString(5)==null?"":rset.getString(5));
				//sugg_bycd_lead.add(rset.getString(3)==null?"":rset.getString(3));
				//sugg_commempcd_lead.add(rset.getString(4)==null?"":rset.getString(4));
				
				queryString="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(4)+"' ";
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next()){
					sugg_commempnm_lead.add(rset1.getString(1)==null?"":rset1.getString(1));
				}else{
					sugg_commempnm_lead.add("");
				}
				
				queryString="SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD='"+rset.getString(3)+"' ";
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next()){
					sugg_bynm_lead.add(rset1.getString(1)==null?"":rset1.getString(1));
				}else{
					sugg_bynm_lead.add("");
				}
			}
			//System.out.println(""+title_cd_lead.size()+"-title_NM_lead--"+title_NM_lead.size()+"--sugg_bycd_lead--"+sugg_bycd_lead.size()+"-sugg_commempnm_lead--"+sugg_commempnm_lead.size()+"-sugg_bynm_lead--"+sugg_bynm_lead.size());
			//FOR ET END
			
			
			
       
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
     
}//end of //RG20170207
String Ssup_rmk="";String Ssup_act="";String SSts="";String Sdt="";String Actdt="";String HrActdt="";String COMMITTEE_DECIDE_DT="";
public void AgingReport_Details() throws SQLException
{
	try
	{
		//String branch_cd="1";
		
		String q1="select emp_nm,comp_cd,emp_alias_cd from hr_emp_mst where emp_cd='"+suggby+"'";
//		System.out.println("---q1--"+q1);
		rset=stmt.executeQuery(q1);
        if(rset.next())
		{
        	emp_nm1=rset.getString(1)==null?"":rset.getString(1);
        	comp_cd1=rset.getString(2)==null?"":rset.getString(2);
        	emp_alias_cd=rset.getString(3)==null?"":rset.getString(3);
		}
        
        String query112 ="select a.reporting from hr_emp_rel_mst a where "
        		//+ "a.comp_cd='"+comp_cd1+"' and "
        				+ "a.emp_cd='"+suggby+"' order by reporting    ";
		   // System.out.println("query11--"+query112);
		rset=stmt.executeQuery(query112);
		while (rset.next())
		{
			//dept_nm1=rset.getString(1);
			//dept_cd1=rset.getString(2);
			line_mgr=rset.getString(1)==null?"":rset.getString(1);
		}
		dept_nm1="";dept_cd1="";desg_cd1="";desg_nm1="";
		
        String query11 ="select distinct(b.dept_nm),a.dept_cd from hr_emp_rel_mst a, " +
        		"hr_dept_mst b where a.dept_cd=b.dept_cd "+
        		//and b.comp_cd='"+comp_cd1+"' " +
        		"and a.emp_cd='"+suggby+"' order by dept_nm    ";
//        System.out.println("query11--"+query11);
	rset=stmt.executeQuery(query11);
	while (rset.next())
	{
		dept_nm1=rset.getString(1)==null?"":rset.getString(1);
		dept_cd1=rset.getString(2)==null?"":rset.getString(2);
		//line_mgr=rset.getString(3);
	}
	
	String qury="select distinct(d.desg_cd),d.desg_nm from hr_emp_rel_mst a," +
			"hr_dept_mst b, hr_desg_mst d,hr_emp_mst e " +
			"where a.dept_cd=b.dept_cd and  b.dept_cd='"+dept_cd1+"' and " +
			" a.desg_cd=d.desg_cd and e.emp_cd='"+suggby+"'  "+
			//and b.comp_cd='"+comp_cd1+"' " +
			"and a.emp_cd=e.emp_cd  order by desg_nm";
	//System.out.println("qury000---"+qury);
	rset=stmt.executeQuery(qury);
	while (rset.next())
	{
		desg_cd1=rset.getString(1)==null?"":rset.getString(1);
		desg_nm1=rset.getString(2)==null?"":rset.getString(2);
	
	}
//	String query2="Select distinct(reporting) from hr_emp_rel_mst  where comp_cd='"+comp_cd1+"' and branch_cd='"+branch_cd+"' order by reporting ";
//	rset=stmt.executeQuery(query2);
////	System.out.println("query2--"+query2);
//	while (rset.next())
//	{
//		res_sup.add(rset.getString(1)==null?"":rset.getString(1));
//	}
	
	//System.out.println("res_sup--"+res_sup.size());
		String query3="select a.title_cd, a.title_nm, b.impl_by_days, b.impl_cost," +
				" to_char(a.impl_time_dt,'dd/mm/yyyy'), b.sugg_desc, b.sugg_benefit,b.sugg_brief_benefit  " +
				", OUTCOME_REGD, to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss'),a.supervisor_cd " +
				", MEETING_VENUE, COMM_RECO, to_char(COMM_MEETING_DT,'dd/mm/yyyy'),hr_action_status_reason,b.cost_reduction,b.estimated_saving " +
				" , HR_ACTION_STATUS,ACTION_DTL,ACTION_FLAG,a.IMPL_COST,VERIFY_FLAG,BENEFICIAL_AMOUNT," +
				"BENEFICIAL_FLAG,a.remark_by_supervisor,a.action_by_supervisor,a.status," +
				"to_char(a.supervisor_action_dt,'dd/mm/yyyy hh24:mi:ss'),to_char(action_dt,'dd/mm/yyyy hh24:mi:ss')"
				+ ",to_char(a.hr_action_dt,'dd/mm/yyyy hh24:mi:ss'),EXEC_LEAD_DECISION_FLAG,impl_timeline,to_char(target_close_dt,'dd/mm/yyyy'),"
				+ "to_char(actual_closer_dt,'dd/mm/yyyy'),exec_lead ,to_char(exec_lead_decision_dt,'dd/mm/yyyy')" +
				"from  sugg_title_mst a, sugg_title_dtl b where a.title_cd= b.title_cd and  b.flag='Y' and a.title_cd='"+titcd+"'  ";
		rset=stmt.executeQuery(query3);
//		System.out.println("query3- here-"+query3);
		while (rset.next())
		{
			title_cd=rset.getString(1);
			title_nm=rset.getString(2)==null?"":rset.getString(2);
			impl_by_days=rset.getString(3)==null?"":rset.getString(3);
			impl_cost=rset.getString(4)==null?"":rset.getString(4);
			impl_time_dt=rset.getString(5)==null?"":rset.getString(5);
			sugg_desc=rset.getString(6)==null?"":rset.getString(6);
			sugg_benefit=rset.getString(7)==null?"":rset.getString(7);
			sugg_brief=rset.getString(8)==null?"":rset.getString(8);
			outcome_regd=rset.getString(9)==null?"":rset.getString(9);
			outcome_regd_dt=rset.getString(10)==null?"":rset.getString(10);
			res_supervisor1=rset.getString(11)==null?"":rset.getString(11);
			committee_venue=rset.getString(12)==null?"":rset.getString(12);
			committee_reco=rset.getString(13)==null?"":rset.getString(13);
			committee_meeting_dt=rset.getString(14)==null?"":rset.getString(14);
			hr_hld_reason=rset.getString(15)==null?"":rset.getString(15);
			cost_reduce=rset.getString(16)==null?"":rset.getString(16);
			estimated_save=rset.getString(17)==null?"":rset.getString(17);
			hr_action_status=rset.getString(18)==null?"":rset.getString(18);
			act_rmk=rset.getString(19)==null?"":rset.getString(19);
			act_flg=rset.getString(20)==null?"":rset.getString(20);
			awd_amt=rset.getString(21)==null?"":rset.getString(21);
			Ver_flg=rset.getString(22)==null?"":rset.getString(22);
			ben_amt=rset.getString(23)==null?"":rset.getString(23);
			bene_flg=rset.getString(24)==null?"":rset.getString(24);
			Ssup_rmk=rset.getString(25)==null?"":rset.getString(25);
			Ssup_act=rset.getString(26)==null?"":rset.getString(26);
			SSts=rset.getString(27)==null?"":rset.getString(27);
			Sdt=rset.getString(28)==null?"":rset.getString(28);
			Actdt=rset.getString(29)==null?"":rset.getString(29);
			HrActdt=rset.getString(30)==null?"":rset.getString(30);
			EXEC_LEAD_FLAG=rset.getString(31)==null?"":rset.getString(31);
			impl_timeline=rset.getString(32)==null?"":rset.getString(32);
			target_close_dt=rset.getString(33)==null?"":rset.getString(33);
			actual_close_dt=rset.getString(34)==null?"":rset.getString(34);
			EXEC_LEAD_CD=rset.getString(35)==null?"":rset.getString(35);
			exec_lead_dt=rset.getString(36)==null?"":rset.getString(36);
		}
		String q2="select emp_nm,comp_cd from hr_emp_mst where emp_cd='"+res_supervisor1+"'";
		rset=stmt.executeQuery(q2);
        if(rset.next())
		{
        	Ssupemp_nm1=rset.getString(1)==null?"":rset.getString(1);
		}
        else{
        	Ssupemp_nm1="";
        }
        
        q2="select emp_nm,comp_cd from hr_emp_mst where emp_cd='"+EXEC_LEAD_CD+"'";
		rset=stmt.executeQuery(q2);
        if(rset.next())
		{
        	Exec_lead_emp_nm=rset.getString(1)==null?"":rset.getString(1);
		}
        else{
        	Exec_lead_emp_nm="";
        }
        
        String AssignedUser="";
       String query="select A.EMP_CD, B.EMP_NM from SUGG_ASSIGN_DTL A, HR_EMP_MST B where A.TITLE_CD='"+titcd+"' AND A.EMP_CD=B.EMP_CD ";
//        System.out.println("ASSIGNED MEMBERS: "+query);
		rset=stmt.executeQuery(query);
       while(rset.next())
		{
       	AssignedUser +=rset.getString(2)+", ";
		}
       committee_members1 = AssignedUser;
		//String dt=sugg_brief.substring(48,remark.length()-1);
		
       ///////////////////////////////////////////////////////////////////
       
	}
	catch(SQLException e)
	{
		System.err.println("Error"+e);
		System.err.println("..DataBean_TIMS.java.in Query :.."+queryString);
		e.printStackTrace();
	}
     
}//end of //RG20170207
String dept=""; String desg="";
public String getDept() {
	return dept;
}
public void setDept(String dept) {
	this.dept = dept;
}
public String getDesg() {
	return desg;
}
public void setDesg(String desg) {
	this.desg = desg;
}
Vector Cdeptcd=new Vector();
public Vector getCdeptcd() {
	return Cdeptcd;
}
public void setCdeptcd(Vector cdeptcd) {
	Cdeptcd = cdeptcd;
}
public Vector getCdeptnm() {
	return Cdeptnm;
}
public void setCdeptnm(Vector cdeptnm) {
	Cdeptnm = cdeptnm;
}
public Vector getCdesgcd() {
	return Cdesgcd;
}
public void setCdesgcd(Vector cdesgcd) {
	Cdesgcd = cdesgcd;
}
public Vector getCdesgnm() {
	return Cdesgnm;
}
public void setCdesgnm(Vector cdesgnm) {
	Cdesgnm = cdesgnm;
}



Vector Cdeptnm=new Vector();
Vector Cdesgcd=new Vector();
Vector Cdesgnm=new Vector();
String flg="";

String  Sdptnm="";
String  Sdsgnm="";

Vector exec_lead_flag=new Vector();
Vector act_closer_dt=new Vector();
Vector impl_lead_timeline_flag=new Vector();
Vector exec_lead_ben_flag=new Vector();
Vector exec_lead_ben_amt=new Vector();
Vector comm_Actual_savings=new Vector();
Vector exec_lead_actual_savings=new Vector();



public void Empwise_Summary() throws SQLException
{
	try
	{
		String query10=" select min(to_char(sugg_dt,'yyyy')) from sugg_title_mst ";
		rset=stmt.executeQuery(query10);
			if(rset.next())
			{
				minyear=rset.getString(1);
			}
//			System.out.println("--year-"+year);
		if(!year.equalsIgnoreCase("0")){
		st_dt="01/01/"+year;
		String query="select TO_CHAR(sysdate,'DD/MM/YYYY'),TO_CHAR(sysdate,'YYYY')  from DUAL ";
		rset=stmt.executeQuery(query);
			if(rset.next())
			{
				end_dt=rset.getString(1);
				curyr=rset.getString(2);
			}
		//	System.out.println("--sd-"+dt);
			if(dt.equals("")) {
				//System.out.println("-sdsd--"+dt);
				if(year.equals(curyr)){
					//System.out.println("-sdsd-if-");
					st_dt="01/01/"+curyr;
					dt = st_dt;
					enddt = end_dt;
				}else{
					//System.out.println("-sdsd-else-");
					end_dt="31/12/"+year;
					dt = st_dt;
					enddt = end_dt;
				}
				
			}
			//System.out.println("------------"+desg);
			String q3="";
			if(!dept.equals("0") && (!dept.equals("")))
			{
				q3=" and b.dept_CD='"+dept+"' ";
			}
			if(!desg.equals("0") && (!desg.equals("")))
			{
				q3+=" and d.desg_CD='"+desg+"' ";
			}
			
			Vector Vdeptcd=new Vector();
			
		if(!flag1.equalsIgnoreCase("Y")){
			queryString="select e.emp_cd from hr_emp_mst e,hr_emp_rel_mst c,hr_dept_mst b,hr_desg_mst d"
					+ " where  c.emp_cd=e.emp_cd and c.dept_cd=b.dept_cd and c.desg_cd=d.desg_cd and  (lower(e.emp_nm) LIKE '"+search_char+"%' or upper(e.emp_nm) LIKE '"+search_char+"%'  ) "
					+ "and e.emp_cd in (select distinct(sugg_by) from sugg_title_mst ) "+q3
					+ " order by e.emp_nm    ";
			
//			System.out.println("queryString-ruchi--"+queryString);
			//System.out.println("DUTY-TIME-1--: "+queryString);
			rset1=stmt1.executeQuery(queryString);
			while (rset1.next())
			{
				//System.out.println("in while loop");
				emp_cd_hr.add(rset1.getString(1)==null?"":rset1.getString(1));
			}
			
			//String query="Select distinct(a.dept_cd),a.dept_nm from hr_dept_mst a,hr_emp_rel_mst b where a.dept_cd=b.dept_cd and b.emp_cd in(Select distinct(sugg_by from sugg_title_mst)) ";
				
				 String query111 ="select distinct(b.dept_nm),a.dept_cd from hr_emp_rel_mst a, " +
		            		"hr_dept_mst b where a.dept_cd=b.dept_cd and a.emp_cd in (select distinct (sugg_by) from sugg_title_mst )  " +
		            	//	"and a.emp_cd='"+emp_cd_hr.elementAt(k)+"'" 
		            				 " order by b.dept_nm    ";
		            //System.out.println("query11--"+query111);
				rset=stmt.executeQuery(query111);
				while (rset.next())
				{
//					if(Cdeptcd.contains(rset.getString(2))){
//						
//					}else{
						Cdeptnm.add(rset.getString(1)==null?"":rset.getString(1));
						Cdeptcd.add(rset.getString(2));
					//}
						
				}
				
				
//				System.out.println("--"+Cdeptcd.size()+"--"+Cdeptnm.size());
				for(int k=0;k<Cdeptcd.size();k++){
					String qury="select distinct(d.desg_cd),d.desg_nm from hr_emp_rel_mst a," +
							"hr_dept_mst b, hr_desg_mst d,hr_emp_mst e " +
							"where a.dept_cd=b.dept_cd and  b.dept_cd='"+Cdeptcd.elementAt(k)+"' and " +
							" a.desg_cd=d.desg_cd and a.emp_cd in (select distinct (sugg_by) from sugg_title_mst ) " +
						//	+ "and e.emp_cd='"+emp_cd_hr.elementAt(k)+"'  " +
							"and a.emp_cd=e.emp_cd  order by d.desg_nm";
//					System.out.println("qury000---"+qury);
					rset=stmt.executeQuery(qury);
					while (rset.next())
					{
//						if(Cdesgcd.contains(rset.getString(1))){
//							
//						}else{
							Cdesgnm.add(rset.getString(2)==null?"":rset.getString(2));
							Cdesgcd.add(rset.getString(1));
						//}
					
					}
				
			}
			
			if(emp_cd_hr.size()==0){
//				System.out.println("in if");
				String query1="Select title_cd,sugg_by,supervisor_cd,title_nm,action_by_supervisor," +
						"to_char(sugg_dt,'dd/mm/yyyy'),dept_cd,desg_cd,status,to_char(supervisor_action_dt,'dd/mm/yyyy')" +
						",hr_action_status,to_char(hr_action_dt,'dd/mm/yyyy'),outcome_regd,beneficial_amount," +
						"impl_cost,to_char(COMMITTEE_DECISION_DT,'dd/mm/yyyy'),EXEC_LEAD,IMPL_TIMELINE,"
						+ "TO_CHAR(TARGET_CLOSE_DT,'DD/MM/YYYY'),TO_CHAR(ACTUAL_CLOSER_DT,'DD/MM/YYYY'),TO_CHAR(VERIFY_DT,'DD/MM/YYYY'),exec_lead_decision_flag,"
						+ "comm_actual_savings,exec_lead_actual_savings,exec_lead_beneficial_flag,exec_lead_beneficial_amount "//RG20190814 For adding monetary savings as requested by Mr. Vishal Malhotra
						+ "from sugg_title_mst where (sugg_dt >= " +
						"to_date('"+dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1) order by title_cd desc";
				rset=stmt.executeQuery(query1);
				
				while(rset.next())
				{
					Vtitle_cd.add(rset.getString(1)==null?"":rset.getString(1));
					VSugg_by.add(rset.getString(2)==null?"":rset.getString(2));
					VSup_cd.add(rset.getString(3)==null?"":rset.getString(3));
					Vtitle_nm.add(rset.getString(4)==null?"":rset.getString(4));
					Vsup_act.add(rset.getString(5)==null?"-":rset.getString(5));
					Vsugg_dt.add(rset.getString(6)==null?"-":rset.getString(6));
					//Vdept_cd.add(rset.getString(7)==null?"":rset.getString(7));
					//Vdesg_cd.add(rset.getString(8)==null?"":rset.getString(8));
					Vsup_sts.add(rset.getString(9)==null?"-":rset.getString(9));
					Vsup_actdt.add(rset.getString(10)==null?"-":rset.getString(10));
					
					Vhr_act.add(rset.getString(11)==null?"-":rset.getString(11));
					Vhr_actdt.add(rset.getString(12)==null?"-":rset.getString(12));
					Vcommittee.add(rset.getString(13)==null?"-":rset.getString(13));
					Vmonetary_save.add(rset.getString(14)==null?"-":rset.getString(14));
					Vaward.add(rset.getString(15)==null?"-":rset.getString(15));
					VCommittee_descion_dt.add(rset.getString(16)==null?"-":rset.getString(16));
					VEXEC_LEAD_CD.add(rset.getString(17)==null?"":rset.getString(17));
					Vimpl_timeline.add(rset.getString(18)==null?"":rset.getString(18));
					Vtarget_close_dt.add(rset.getString(19)==null?"":rset.getString(19));
					Vactual_close_dt.add(rset.getString(20)==null?"":rset.getString(20));
					VERIFY_DT.add(rset.getString(21)==null?"":rset.getString(21));
					exec_lead_flag.add(rset.getString(22)==null?"":rset.getString(22));
					comm_Actual_savings.add(rset.getString(23)==null?"":rset.getString(23));
					exec_lead_actual_savings.add(rset.getString(24)==null?"":rset.getString(24));
					exec_lead_ben_flag.add(rset.getString(25)==null?"":rset.getString(25));
					exec_lead_ben_amt.add(rset.getString(26)==null?"":rset.getString(26));
				
				}
			}else{
				for(int k=0;k<emp_cd_hr.size();k++){
					String query1="Select title_cd,sugg_by,supervisor_cd,title_nm,action_by_supervisor," +
					"to_char(sugg_dt,'dd/mm/yyyy'),dept_cd,desg_cd,status,to_char(supervisor_action_dt,'dd/mm/yyyy')" +
					",hr_action_status,to_char(hr_action_dt,'dd/mm/yyyy'),outcome_regd,beneficial_amount," +
					"impl_cost,to_char(COMMITTEE_DECISION_DT,'dd/mm/yyyy'),EXEC_LEAD,IMPL_TIMELINE,TO_CHAR(TARGET_CLOSE_DT,'DD/MM/YYYY'),"
					+ "TO_CHAR(ACTUAL_CLOSER_DT,'DD/MM/YYYY') ,TO_CHAR(VERIFY_DT,'DD/MM/YYYY'),exec_lead_decision_flag, "
					+ "comm_actual_savings,exec_lead_actual_savings,exec_lead_beneficial_flag,exec_lead_beneficial_amount "//RG20190814 For adding monetary savings as requested by Mr. Vishal Malhotra
					+ "from sugg_title_mst where (sugg_dt >= " +
					"to_date('"+dt+"','dd/mm/yyyy') AND sugg_dt < to_date('"+enddt+"','dd/mm/yyyy')+1) and sugg_by='"+emp_cd_hr.elementAt(k)+"' order by title_cd desc";
//						System.out.println("qqq---"+query1);
					rset=stmt.executeQuery(query1);
					
					while(rset.next())
					{
						Vtitle_cd.add(rset.getString(1)==null?"":rset.getString(1));
						VSugg_by.add(rset.getString(2)==null?"":rset.getString(2));
						VSup_cd.add(rset.getString(3)==null?"":rset.getString(3));
						Vtitle_nm.add(rset.getString(4)==null?"":rset.getString(4));
						Vsup_act.add(rset.getString(5)==null?"-":rset.getString(5));
						Vsugg_dt.add(rset.getString(6)==null?"-":rset.getString(6));
					//	Vdept_cd.add(rset.getString(7)==null?"":rset.getString(7));
						//Vdesg_cd.add(rset.getString(8)==null?"":rset.getString(8));
						Vsup_sts.add(rset.getString(9)==null?"-":rset.getString(9));
						Vsup_actdt.add(rset.getString(10)==null?"-":rset.getString(10));
						
						Vhr_act.add(rset.getString(11)==null?"-":rset.getString(11));
						Vhr_actdt.add(rset.getString(12)==null?"-":rset.getString(12));
						Vcommittee.add(rset.getString(13)==null?"-":rset.getString(13));
						Vmonetary_save.add(rset.getString(14)==null?"-":rset.getString(14));
						Vaward.add(rset.getString(15)==null?"-":rset.getString(15));
						VCommittee_descion_dt.add(rset.getString(16)==null?"-":rset.getString(16));
						VEXEC_LEAD_CD.add(rset.getString(17)==null?"-":rset.getString(17));
						Vimpl_timeline.add(rset.getString(18)==null?"-":rset.getString(18));
						Vtarget_close_dt.add(rset.getString(19)==null?"":rset.getString(19));
						Vactual_close_dt.add(rset.getString(20)==null?"":rset.getString(20));
						VERIFY_DT.add(rset.getString(21)==null?"":rset.getString(21));
						exec_lead_flag.add(rset.getString(22)==null?"":rset.getString(22));
						comm_Actual_savings.add(rset.getString(23)==null?"":rset.getString(23));
						exec_lead_actual_savings.add(rset.getString(24)==null?"":rset.getString(24));
						exec_lead_ben_flag.add(rset.getString(25)==null?"":rset.getString(25));
						exec_lead_ben_amt.add(rset.getString(26)==null?"":rset.getString(26));
					}
				}
				
			}
			//System.out.println("---->"+Vtitle_cd.size());
			for(int i=0;i<VSugg_by.size();i++)
			{
				String query12="Select emp_nm,emp_alias_cd,comp_cd from hr_emp_mst where emp_cd='"+VSugg_by.elementAt(i)+"' order by emp_nm ";
				rset=stmt.executeQuery(query12);
				if(rset.next())
				{
					Vsugg_by_nm.add(rset.getString(1)==null?"":rset.getString(1));
					Vsuggby_als_cd.add(rset.getString(2)==null?"":rset.getString(2));
					Vcomp_cd.add(rset.getString(3)==null?"":rset.getString(3));
				}
				else{
					Vsugg_by_nm.add("");
					Vsuggby_als_cd.add("");
				}
				String query13="Select emp_nm from hr_emp_mst where emp_cd='"+VSup_cd.elementAt(i)+"' order by emp_nm ";
				rset=stmt.executeQuery(query13);
				if(rset.next())
				{
					VSup_by_nm.add(rset.getString(1)==null?"":rset.getString(1));
				}
				else{
					VSup_by_nm.add("");
				}
				
				 String query11 ="select distinct(b.dept_nm),a.dept_cd from hr_emp_rel_mst a, " +
		            		"hr_dept_mst b where a.dept_cd=b.dept_cd  " +
		            		"and a.emp_cd='"+VSugg_by.elementAt(i)+"' order by b.dept_nm    ";
		           // System.out.println("query11--"+query11);
				rset=stmt.executeQuery(query11);
				if (rset.next())
				{
					Vdept_nm1.add(rset.getString(1)==null?"-":rset.getString(1));
					Vdept_cd.add(rset.getString(2)==null?"":rset.getString(2));
						
				}
				else{
					Vdept_nm1.add("-");
					Vdept_cd.add("");
				}
				
				//System.out.println("--"+Cdeptcd.size()+"--"+Cdeptnm.size());
				String qury="select distinct(d.desg_cd),d.desg_nm from hr_emp_rel_mst a," +
						"hr_dept_mst b, hr_desg_mst d,hr_emp_mst e " +
						"where a.dept_cd=b.dept_cd and  b.dept_cd='"+Vdept_cd.elementAt(i)+"' and " +
						" a.desg_cd=d.desg_cd and e.emp_cd='"+VSugg_by.elementAt(i)+"'  " +
						"and a.emp_cd=e.emp_cd  order by d.desg_nm";
				//System.out.println("qury000---"+qury);
				rset=stmt.executeQuery(qury);
				if (rset.next())
				{
					Vdesg_nm1.add(rset.getString(2)==null?"-":rset.getString(2));
				}
				else{
					Vdesg_nm1.add("-");
				}
	  }
			}else{
			
				String query3="select a.title_cd, a.title_nm, b.impl_by_days, b.impl_cost," +
				" to_char(a.impl_time_dt,'dd/mm/yyyy'), b.sugg_desc, b.sugg_benefit,b.sugg_brief_benefit  " +
				", OUTCOME_REGD, to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss'),a.supervisor_cd " +
				", MEETING_VENUE, COMM_RECO, to_char(COMM_MEETING_DT,'dd/mm/yyyy'),b.cost_reduction,b.estimated_saving"
				+ ",a.sugg_by ,EXEC_LEAD,IMPL_TIMELINE,TO_CHAR(TARGET_CLOSE_DT,'DD/MM/YYYY'),TO_CHAR(ACTUAL_CLOSER_DT,'DD/MM/YYYY'),exec_lead_decision_flag " +
				"from  sugg_title_mst a, sugg_title_dtl b where a.title_cd= b.title_cd and  b.flag='Y' and a.title_cd='"+titcd+"'  ";
					rset=stmt.executeQuery(query3);
				//	System.out.println("query3- here-"+query3);
					while (rset.next())
					{
						title_cd=rset.getString(1);
						title_nm=rset.getString(2)==null?"":rset.getString(2);
						impl_by_days=rset.getString(3)==null?"":rset.getString(3);
						impl_cost=rset.getString(4)==null?"":rset.getString(4);
						impl_time_dt=rset.getString(5)==null?"":rset.getString(5);
						sugg_desc=rset.getString(6)==null?"":rset.getString(6);
						sugg_benefit=rset.getString(7)==null?"":rset.getString(7);
						sugg_brief=rset.getString(8)==null?"":rset.getString(8);
						outcome_regd=rset.getString(9)==null?"":rset.getString(9);
						outcome_regd_dt=rset.getString(10)==null?"":rset.getString(10);
						res_supervisor1=rset.getString(11)==null?"":rset.getString(11);
						committee_venue=rset.getString(12)==null?"":rset.getString(12);
						committee_reco=rset.getString(13)==null?"":rset.getString(13);
						committee_meeting_dt=rset.getString(14)==null?"":rset.getString(14);
						cost_reduce=rset.getString(15)==null?"":rset.getString(15);
						estimated_save=rset.getString(16)==null?"":rset.getString(16);
						Ssugg_by=rset.getString(17)==null?"":rset.getString(17);
						EXEC_LEAD_CD=rset.getString(18)==null?"":rset.getString(18);
						impl_timeline=rset.getString(19)==null?"":rset.getString(19);
						target_close_dt=rset.getString(20)==null?"":rset.getString(20);
						actual_close_dt=rset.getString(21)==null?"":rset.getString(21);
						EXEC_LEAD_FLAG=rset.getString(22)==null?"":rset.getString(22);
						
					}
					String q1="select emp_nm,comp_cd,emp_alias_cd from hr_emp_mst where emp_cd='"+Ssugg_by+"'";
	//				System.out.println("---q1--"+q1);
					rset=stmt.executeQuery(q1);
			        if(rset.next())
					{
			        	emp_nm1=rset.getString(1)==null?"":rset.getString(1);
			        	comp_cd1=rset.getString(2)==null?"":rset.getString(2);
			        	emp_alias_cd=rset.getString(3)==null?"":rset.getString(3);
					}
			        
			        String query112 ="select a.reporting from hr_emp_rel_mst a where "
			        		//+ "a.comp_cd='"+comp_cd1+"' and "
			        				+ "a.emp_cd='"+Ssugg_by+"' order by reporting    ";
					   // System.out.println("query11--"+query112);
					rset=stmt.executeQuery(query112);
					while (rset.next())
					{
						//dept_nm1=rset.getString(1);
						//dept_cd1=rset.getString(2);
						line_mgr=rset.getString(1)==null?"":rset.getString(1);
					}
					dept_nm1="";dept_cd1="";desg_cd1="";desg_nm1="";
					
			        String query11 ="select distinct(b.dept_nm),a.dept_cd from hr_emp_rel_mst a, " +
			        		"hr_dept_mst b where a.dept_cd=b.dept_cd "+
			        		//and b.comp_cd='"+comp_cd1+"' " +
			        		"and a.emp_cd='"+Ssugg_by+"' order by dept_nm    ";
	//		        System.out.println("query11--"+query11);
				rset=stmt.executeQuery(query11);
				while (rset.next())
				{
					dept_nm1=rset.getString(1)==null?"":rset.getString(1);
					dept_cd1=rset.getString(2)==null?"":rset.getString(2);
					//line_mgr=rset.getString(3);
				}
				
				String qury="select distinct(d.desg_cd),d.desg_nm from hr_emp_rel_mst a," +
						"hr_dept_mst b, hr_desg_mst d,hr_emp_mst e " +
						"where a.dept_cd=b.dept_cd and  b.dept_cd='"+dept_cd1+"' and " +
						" a.desg_cd=d.desg_cd and e.emp_cd='"+Ssugg_by+"'  "+
						//and b.comp_cd='"+comp_cd1+"' " +
						"and a.emp_cd=e.emp_cd  order by desg_nm";
				//System.out.println("qury000---"+qury);
				rset=stmt.executeQuery(qury);
				while (rset.next())
				{
					desg_cd1=rset.getString(1)==null?"":rset.getString(1);
					desg_nm1=rset.getString(2)==null?"":rset.getString(2);
				
				}
				String q2="select emp_nm,comp_cd from hr_emp_mst where emp_cd='"+res_supervisor1+"'";
				rset=stmt.executeQuery(q2);
		        if(rset.next())
				{
		        	Ssupemp_nm1=rset.getString(1)==null?"":rset.getString(1);
				}
		        else{
		        	Ssupemp_nm1="";
		        }
			
			}
		if(flg.equalsIgnoreCase("Y")){
			if(!dept.equalsIgnoreCase("") && (!dept.equalsIgnoreCase("0"))){
				String queryString="Select dept_nm from hr_dept_mst where dept_cd='"+dept+"' ";
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					 Sdptnm=rset.getString(1)==null?"":rset.getString(1);
				}else{}
			}else{}
			if(!desg.equalsIgnoreCase("") && (!desg.equalsIgnoreCase("0"))){
				String queryString="Select desg_nm from hr_desg_mst where desg_cd='"+desg+"' ";
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					 Sdsgnm=rset.getString(1)==null?"":rset.getString(1);
				}else{}
			}else{}
			
		}else{}
			
		}
	}
	catch(SQLException e)
	{
		System.err.println("Error"+e);
		System.err.println("..DataBean_TIMS.java.in Query :.."+queryString);
		e.printStackTrace();
	}
     
}//end of //RG20170207

public void createTable() throws SQLException
{
	try
	{
		int count=0;
		String query="SELECT COUNT(*) FROM TABS WHERE TABLE_NAME LIKE 'SUGG_EMAIL_DTL' ";
		rset=stmt.executeQuery(query);
		if(rset.next())
		{
			count=rset.getInt(1);
		}
		if(count==0)
		{
			query="CREATE TABLE SUGG_EMAIL_DTL" +
			   "(EMP_CD NUMBER(6,0) NOT NULL ENABLE, "+
				"TITLE_CD NUMBER(6,0) NOT NULL ENABLE,"+ 
				"COMMITTEE_MEETING_DT DATE NOT NULL ENABLE, "+
				"COMMITTEE_DECISION_FLAG CHAR(1 BYTE), "+
				"EMAIL_SENT_DT_NXT_DAY DATE, "+
				"EMAIL_SENT_DT_10_DAYS DATE, "+
				"EMAIL_SENT_DT_20_DAYS DATE, "+
				"REMARK VARCHAR2(1024 BYTE), "+
				"LAST_REMINDER_DT DATE, "+
				"COMMITTEE_DECISION_DT DATE, "+
				"REMARK1 VARCHAR2(1024 BYTE), "+
				"FLAG CHAR(1 BYTE), "+
				"ENTER_BY NUMBER(6,0), "+
				"ENTER_DT DATE, "+
				"EMAIL_SENT_DT DATE, "+
				"CONSTRAINT SUGG_EMIAL_DTL_PK PRIMARY KEY (EMP_CD, TITLE_CD))";
			stmt.executeUpdate(query);
			conn.commit();
		}
		int count1=0;
		 query="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'SUGG_TITLE_MST' "
				+ "AND UPPER(COLUMN_NAME) LIKE 'EXEC_LEAD_DECISION_FLAG' ";
		rset=stmt.executeQuery(query);
		if(rset.next())
		{
			count1=rset.getInt(1);
		}
		if(count1==0)
		{
			query="alter table SUGG_TITLE_MST add EXEC_LEAD_DECISION_FLAG CHAR(1)";
			stmt.executeUpdate(query);
			query="alter table SUGG_TITLE_MST add EXEC_LEAD_DECISION_DT DATE";
			stmt.executeUpdate(query);
			
			conn.commit();
		}
		
		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}

public void Sup_AnnexureAll() throws SQLException
{
	try
	{
		createTable();
		
		String branch_cd="1";
		
		String query="  select emptyp_cd from hr_emptyp_mst where (lower(emptyp_nm) LIKE '%sahyog co-ordinator%'" +
				"or lower(emptyp_nm) LIKE '%sahayog coordinator%' " +
				"or lower(emptyp_nm) LIKE 'sahayog co-ordinator%'" +
				" or lower(emptyp_nm) LIKE 'sahyog co-ordinator%' )  ";
//		System.out.println("----qq------"+query);
		rset=stmt.executeQuery(query);
		if(rset.next())
		{
			categry_cd=rset.getString(1);
		}
		String query1="SELECT A.EMP_CD,A.CATEGRY_CD FROM TIMS_EMP_CATG_DTL A WHERE A.EFF_DT = " +
				"(SELECT MAX(B.EFF_DT) FROM TIMS_EMP_CATG_DTL B WHERE A.EMP_CD=B.EMP_CD AND A.CATEGRY_CD=B.CATEGRY_CD " +
				"AND A.COMP_CD=B.COMP_CD ) AND A.CATEGRY_CD='"+categry_cd+"'  ";
		//System.out.println("Query For Fetching categry cd 27 employeees---"+query1);
		rset=stmt.executeQuery(query1);
		while(rset.next())
		{
			Vhr_emp_cd.add(rset.getString(1)==null?"":rset.getString(1));
			Vcat_cd.add(rset.getString(2)==null?"":rset.getString(2));
		}
		String query101="  select emptyp_cd from hr_emptyp_mst where (lower(emptyp_nm) LIKE '%sahyog supervisor%'" +
		" or lower(emptyp_nm) LIKE 'sahayog supervisor%' ) ";
		//System.out.println("Query For Fetching categry cd 26-"+query101);
		rset=stmt.executeQuery(query101);
		if(rset.next())
		{
			categry_cd1=rset.getString(1)==null?"":rset.getString(1);
		}
		String query102="SELECT A.EMP_CD,A.CATEGRY_CD FROM TIMS_EMP_CATG_DTL A WHERE " +
				"A.EFF_DT = (SELECT MAX(B.EFF_DT) FROM TIMS_EMP_CATG_DTL B WHERE " +
				" A.EMP_CD=B.EMP_CD AND A.CATEGRY_CD=B.CATEGRY_CD AND A.COMP_CD=B.COMP_CD ) AND A.CATEGRY_CD='"+categry_cd1+"' ";
		//System.out.println("Query For Fetching categry cd 26 employeees-"+query102);
		rset=stmt.executeQuery(query102);
		while(rset.next())
		{
			res_sup_emp_cd.add(rset.getString(1)==null?"":rset.getString(1));
//			if(!Vhr_emp_cd.contains(rset.getString(1))){
				Vcat_cd.add(rset.getString(2)==null?"":rset.getString(2));
				Vhr_emp_cd.add(rset.getString(1)==null?"":rset.getString(1));
//			}
		}
		//System.out.println("Vhr_emp_cd---"+Vhr_emp_cd+"----"+Vcat_cd);
		if(Vhr_emp_cd.contains(user_cd)) {
		for(int j=0;j<Vhr_emp_cd.size();j++){
			//System.out.println("---"+Vcat_cd.elementAt(j)+"----------"+categry_cd1);
		if(user_cd.equals(Vhr_emp_cd.elementAt(j))){
		
			if(Vcat_cd.elementAt(j).equals(categry_cd1)){
//				System.out.println("hello");
		//String query6="Select nvl(supervisor_cd,'') from sugg_title_mst where supervisor_cd='"+user_cd+"' ";
				String query6="SELECT A.EMP_CD,A.CATEGRY_CD FROM TIMS_EMP_CATG_DTL A WHERE " +
						"A.EFF_DT = (SELECT MAX(B.EFF_DT) FROM TIMS_EMP_CATG_DTL B WHERE " +
						" A.EMP_CD=B.EMP_CD AND A.CATEGRY_CD=B.CATEGRY_CD AND A.COMP_CD=B.COMP_CD ) AND A.CATEGRY_CD='"+categry_cd1+"' AND EMP_CD='"+user_cd+"' ";
				//System.out.println("Query For Fetchingabbbbb categry cd 26 employeees-"+query6);
		//System.out.println("---"+query6);
		rset=stmt.executeQuery(query6);
		if(rset.next())
		{
			res_supervisor=rset.getString(1)==null?"":rset.getString(1);
			flag_sup="Y";
		}else{
			flag_sup="N";
		}
		if(flag_sup.equalsIgnoreCase("N")){
//			System.out.println("in supervisor");
		String q1="select nvl(emp_nm,''),nvl(comp_cd,'') from hr_emp_mst where emp_cd='"+user_cd+"' ";
		//System.out.println("---"+q1);
		rset=stmt.executeQuery(q1);
        if(rset.next())
		{
        	emp_nm=rset.getString(1)==null?"":rset.getString(1);
        	comp_cd=rset.getString(2)==null?"":rset.getString(2);
		}
       
       //for(int i=0;i<emp_cd.size();i++){
       String query3="select a.title_cd,nvl(a.title_nm,''), nvl(c.impl_by_days,''), nvl(to_char(a.impl_time_dt,'dd/mm/yyyy'),'')," +
       		"to_char(a.sugg_dt,'dd/mm/yyyy'),nvl(a.sugg_by,''),nvl(b.emp_nm,''),nvl(a.supervisor_cd,''),a.action_by_supervisor,"
       		+ "a.status,to_char(a.SUPERVISOR_ACTION_DT,'dd/mm/yyyy hh24:mi:ss')" +
       		",a.HR_ACTION_STATUS,to_char(a.HR_ACTION_DT,'dd/mm/yyyy hh24:mi:ss'),OUTCOME_REGD,"
       		+ "to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss'),"
       		+ "ACTION_DTL,to_char(a.ACTION_DT,'dd/mm/yyyy hh24:mi:ss'),VERIFY_FLAG,"
       		+ "to_char(a.VERIFY_DT,'dd/mm/yyyy hh24:mi:ss'),nvl(to_char(actual_closer_dt,'dd/mm/yyyy'),''),hr_action_status_reason,MEETING_VENUE " + //RG20190706 for new flow
       		" from " +
       		" sugg_title_mst a,hr_emp_mst b,sugg_title_dtl c where  a.line_mgr='"+user_cd+"'  " +
       		" and a.sugg_by=b.emp_cd and c.flag='Y' and a.title_cd=c.title_cd order by a.title_cd DESC ";
		rset=stmt.executeQuery(query3);
//		System.out.println("query3 forline manager data--"+query3);
		while (rset.next())
		{
			String temp_sts=rset.getString(10)==null?"":rset.getString(10);
			String temp_out=rset.getString(14)==null?"":rset.getString(14);
			String temp_dt=rset.getString(20)==null?"":rset.getString(20);
			
		      if(!temp_sts.equals("RETURN")){  
		    	  if(!temp_out.equals("NONIMPLEMENTABLE")){
		    		  if(temp_dt.equals("")){  
						title_cd2.add(rset.getString(1)==null?"":rset.getString(1));
						title_nm1.add(rset.getString(2)==null?"":rset.getString(2));
						impl_by_days1.add(rset.getString(3)==null?"":rset.getString(3));
						impl_time_dt1.add(rset.getString(4)==null?"":rset.getString(4));
						sugg_dt.add(rset.getString(5)==null?"":rset.getString(5));
						sugg_by.add(rset.getString(6)==null?"":rset.getString(6));
						sugg_nm.add(rset.getString(7)==null?"":rset.getString(7));
						action_sts.add(rset.getString(9)==null?"Pending":rset.getString(9));
						sts.add(rset.getString(10)==null?"":rset.getString(10));
						action_sts_dt.add(rset.getString(11)==null?"":rset.getString(11));
						action_hr.add(rset.getString(12)==null?"-":rset.getString(12));
						hr_action_dt.add(rset.getString(13)==null?"-":rset.getString(13));
						OUTCOME_REGD.add(rset.getString(14)==null?"-":rset.getString(14));
						OUTCOME_REGD_DT.add(rset.getString(15)==null?"-":rset.getString(15));
						ACTION_DTL.add(rset.getString(16)==null?"-":rset.getString(16));
						ACTION_DT.add(rset.getString(17)==null?"-":rset.getString(17));
						VERIFY_FLAG.add(rset.getString(18)==null?"-":rset.getString(18));
						VERIFY_DT.add(rset.getString(19)==null?"-":rset.getString(19));
						Vactual_close_dt.add(rset.getString(20)==null?"-":rset.getString(20));
						hr_action_sts_reason.add(rset.getString(21)==null?"":rset.getString(21));
						meet_venue.add(rset.getString(22)==null?"":rset.getString(22));
		    		  }}}
			
		}
		
       //}
		}else{
			 String query3="select a.title_cd,a.title_nm, nvl(b.impl_by_days,''), nvl(to_char(a.impl_time_dt,'dd/mm/yyyy')"
			 		+ ",'')," +
			 		"nvl(to_char(a.sugg_dt,'dd/mm/yyyy'),''),nvl(a.sugg_by,''),nvl(a.supervisor_cd,''),"
			 		+ "a.action_by_supervisor,a.status,to_char(a.SUPERVISOR_ACTION_DT,'dd/mm/yyyy hh24:mi:ss') " +
			 		",a.HR_ACTION_STATUS,to_char(a.HR_ACTION_DT,'dd/mm/yyyy hh24:mi:ss'),OUTCOME_REGD,"
			 		+ "to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss'),ACTION_DTL,to_char(a.ACTION_DT,'dd/mm/yyyy hh24:mi:ss'),"
			 		+ "VERIFY_FLAG,to_char(a.VERIFY_DT,'dd/mm/yyyy hh24:mi:ss'),b.rev_flag,"
			 		+ "nvl(to_char(actual_closer_dt,'dd/mm/yyyy'),''),hr_action_status_reason,line_mgr,MEETING_VENUE from " +
			 		"sugg_title_mst a,sugg_title_dtl b where (a.supervisor_cd='"+user_cd+"' OR a.line_mgr='"+user_cd+"') and b.flag='Y'" //RG20190711 for adding line manager and supervisor
	 				+ " and a.title_cd=b.title_cd  " +
			 		"order by a.title_cd DESC ";
			rset=stmt.executeQuery(query3);
			//System.out.println("query3 for supervisor data--"+query3);
			while (rset.next())
			{
				String temp_sts=rset.getString(9)==null?"":rset.getString(9);
				String temp_out=rset.getString(13)==null?"":rset.getString(13);
				String temp_dt=rset.getString(20)==null?"":rset.getString(20);
				
			      if(!temp_sts.equals("RETURN")){  
			    	  if(!temp_out.equals("NONIMPLEMENTABLE")){
			    		  if(temp_dt.equals("")){  
							title_cd21.add(rset.getString(1)==null?"":rset.getString(1));
							title_nm11.add(rset.getString(2)==null?"":rset.getString(2));
							impl_by_days11.add(rset.getString(3)==null?"":rset.getString(3));
							impl_time_dt11.add(rset.getString(4)==null?"":rset.getString(4));
							sugg_dt1.add(rset.getString(5)==null?"":rset.getString(5));
							sugg_by1.add(rset.getString(6)==null?"":rset.getString(6));
							sup_cd_rpt.add(rset.getString(7)==null?"":rset.getString(7));
							action_sts1.add(rset.getString(8)==null?"Pending":rset.getString(8));
							status_sup.add(rset.getString(9)==null?"":rset.getString(9));
							sup_action_dt.add(rset.getString(10)==null?"":rset.getString(10));
							
							action_hr.add(rset.getString(11)==null?"-":rset.getString(11));
							hr_action_dt.add(rset.getString(12)==null?"-":rset.getString(12));
							OUTCOME_REGD.add(rset.getString(13)==null?"-":rset.getString(13));
							OUTCOME_REGD_DT.add(rset.getString(14)==null?"-":rset.getString(14));
							ACTION_DTL.add(rset.getString(15)==null?"-":rset.getString(15));
							ACTION_DT.add(rset.getString(16)==null?"-":rset.getString(16));
							VERIFY_FLAG.add(rset.getString(17)==null?"-":rset.getString(17));
							VERIFY_DT.add(rset.getString(18)==null?"-":rset.getString(18));
							REV_flag.add(rset.getString(19)==null?"-":rset.getString(19));
							Vactual_close_dt.add(rset.getString(20)==null?"-":rset.getString(20));
							hr_action_sts_reason.add(rset.getString(21)==null?"":rset.getString(21));
							line_mgr_cd.add(rset.getString(22)==null?"":rset.getString(22));
							meet_venue.add(rset.getString(23)==null?"":rset.getString(23));
							
			    		  }}}
			}
			for(int k=0;k<sugg_by1.size();k++){
				String q2="select nvl(emp_nm,'') from hr_emp_mst where emp_cd='"+sugg_by1.elementAt(k)+"' ";
				//System.out.println("---"+q2);
				rset=stmt.executeQuery(q2);
	            if(rset.next())
				{
	            	emp_nm_under_sup.add(rset.getString(1)==null?"":rset.getString(1));
				}
	            else{
	            	emp_nm_under_sup.add("");
	            }
			}
			queryString="select nvl(emp_nm,'') from hr_emp_mst where emp_cd='"+user_cd+"' ";
			rset=stmt.executeQuery(queryString);
            if(rset.next())
			{
            	sup_nm=rset.getString(1)==null?"":rset.getString(1);
			}
            
			queryString="select a.emp_cd from hr_emp_rel_mst a,hr_emp_mst b where a.emp_cd=b.emp_cd and a.reporting like '%"+sup_nm.trim()+"%'";
			rset=stmt.executeQuery(queryString);
			while(rset.next()){
				cnt_line_mgr_flag.add(rset.getString(1)==null?"":rset.getString(1));
			}
		}
		break;}else{

		//	System.out.println("hello"+j);
			if(user_cd.equals(Vhr_emp_cd.elementAt(j))){
				if(Vcat_cd.elementAt(j).equals(categry_cd)) {
			 String query3="select a.title_cd,nvl(a.title_nm,''),nvl(b.impl_by_days,''),nvl(to_char(a.impl_time_dt,'dd/mm/yyyy'),'')," +
		 		"nvl(to_char(a.sugg_dt,'dd/mm/yyyy'),''),nvl(a.sugg_by,''),nvl(a.supervisor_cd,''),a.action_by_supervisor,a.status,a.hr_action_status,nvl(a.hr_emp_cd,''), " +
		 		"to_char(a.hr_action_dt,'dd/mm/yyyy hh24:mi:ss'),to_char(a.SUPERVISOR_ACTION_DT,'dd/mm/yyyy hh24:mi:ss') " +
		 		",OUTCOME_REGD,to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss'),ACTION_DTL,"
		 		+ "to_char(a.ACTION_DT,'dd/mm/yyyy hh24:mi:ss'),VERIFY_FLAG,to_char(a.VERIFY_DT,'dd/mm/yyyy hh24:mi:ss'),"
		 		+ "b.rev_flag,nvl(to_char(a.ACTUAL_CLOSER_DT,'dd/mm/yyyy hh24:mi:ss'),''),hr_action_status_reason,MEETING_VENUE " +
		 		"from sugg_title_mst a ,sugg_title_dtl b " +
		 		" where (a.status='FORWARD' OR a.line_mgr='"+user_cd+"' OR supervisor_cd='"+user_cd+"') and a.title_cd=b.title_cd and b.flag='Y' " +
		 		" order by a.title_cd DESC";
		rset=stmt.executeQuery(query3);
		//System.out.println("query3-For Fetching Hr data-"+query3);
			
		
		while (rset.next())
		{
			String temp_sts=rset.getString(9)==null?"":rset.getString(9);
			String temp_out=rset.getString(14)==null?"":rset.getString(14);
			String temp_dt=rset.getString(21)==null?"":rset.getString(21);
			
		      if(!temp_sts.equals("RETURN")){  
		    	  if(!temp_out.equals("NONIMPLEMENTABLE")){
		    		  if(temp_dt.equals("")){  
						title_cd21_hr.add(rset.getString(1)==null?"":rset.getString(1));
						title_nm11_hr.add(rset.getString(2)==null?"":rset.getString(2));
						impl_by_days11_hr.add(rset.getString(3)==null?"":rset.getString(3));
						impl_time_dt11_hr.add(rset.getString(4)==null?"":rset.getString(4));
						sugg_dt1_hr.add(rset.getString(5)==null?"":rset.getString(5));
						sugg_by1_hr.add(rset.getString(6)==null?"":rset.getString(6));
						sel_sup_cd.add(rset.getString(7)==null?"":rset.getString(7));
						action_sts1_hr.add(rset.getString(8)==null?"Pending":rset.getString(8));
						status_sup_hr.add(rset.getString(9)==null?"":rset.getString(9));
						status_hr.add(rset.getString(10)==null?"":rset.getString(10));
						user_assign=rset.getString(11)==null?"":rset.getString(11);
						hr_act_dt.add(rset.getString(12)==null?"-":rset.getString(12));
						sup_act_dt.add(rset.getString(13)==null?"":rset.getString(13));
						Voutcome_regd.add(rset.getString(14)==null?"-":rset.getString(14));
						Voutcome_regd_dt.add(rset.getString(15)==null?"-":rset.getString(15));
						Vaction_dtl.add(rset.getString(16)==null?"-":rset.getString(16));
						Vaction_dt.add(rset.getString(17)==null?"-":rset.getString(17));
						Vverify_flag.add(rset.getString(18)==null?"-":rset.getString(18));
						Vverify_dt.add(rset.getString(19)==null?"-":rset.getString(19));
						REV_flag.add(rset.getString(20)==null?"-":rset.getString(20));
						Vactual_close_dt.add(rset.getString(21)==null?"-":rset.getString(21));
						hr_action_sts_reason.add(rset.getString(22)==null?"":rset.getString(22));
						meet_venue.add(rset.getString(23)==null?"":rset.getString(23));
		    		  }}}
		}
			
			
		for(int k=0;k<sugg_by1_hr.size();k++){
			String q2="select emp_nm from hr_emp_mst where emp_cd='"+sugg_by1_hr.elementAt(k)+"' ";
			//System.out.println("---"+q2);
			rset=stmt.executeQuery(q2);
			if(rset.next())
			{
				emp_nm_under_sup_hr.add(rset.getString(1)==null?"":rset.getString(1));
			}else{
				emp_nm_under_sup_hr.add("");
			}		
		}
		if(title_cd21_hr.size()>0){
		for(int k=0;k<title_cd21_hr.size();k++)
		{
			String f1="";
			String f2="";
			String path=request.getRealPath("/PDF");
			f2="SUGGESTION-"+title_cd21_hr.elementAt(k)+".pdf";
			f1="SUGGESTION-"+title_cd21_hr.elementAt(k);
			f1=path+"/"+f1;
			//System.out.println("rrrf1---"+f1);
			File pdf_file=new File(f1+".pdf");
			//System.out.println("inv_file---"+pdf_file);
			
			if(pdf_file.exists())
			{
				
				//System.out.println("inside  rrr");
					path=path+"//"+pdf_file;
					//System.out.println("invoice_pdf_path1----"+invoice_pdf_path1);
		            String context_nm = request.getContextPath();
					String server_nm = request.getServerName();
					String server_port = ""+request.getServerPort();
					  //system.out.println("invoice_pdf_path--"+invoice_pdf_path1);
					String url_start = "http://"+server_nm+":"+server_port+context_nm;
					
					pdfpath_hr = path;
					pdfpath_hr = pdfpath_hr.substring(pdfpath_hr.indexOf("//"));
					
					pdfpath_hr = url_start+"/PDF/"+f2;
				//	System.out.println("pdfpath---> "+pdfpath);
					pdf_hr.add(pdfpath_hr);
			}
			else{
				pdf_hr.add("-");
			}
			
		}}
				
		break;}}
		}
			}
		else{
			String query6="SELECT A.EMP_CD,A.CATEGRY_CD FROM TIMS_EMP_CATG_DTL A WHERE " +
					"A.EFF_DT = (SELECT MAX(B.EFF_DT) FROM TIMS_EMP_CATG_DTL B WHERE " +
					" A.EMP_CD=B.EMP_CD AND A.CATEGRY_CD=B.CATEGRY_CD AND A.COMP_CD=B.COMP_CD ) AND A.CATEGRY_CD='"+categry_cd1+"' AND EMP_CD='"+user_cd+"' ";
			//System.out.println("Query For Fetchingabbbbb categry cd 26 employeees-"+query6);
		//System.out.println("---"+query6);
		rset=stmt.executeQuery(query6);
		if(rset.next())
		{
			res_supervisor=rset.getString(1)==null?"":rset.getString(1);
			flag_sup="Y";
		}else{
			flag_sup="N";
		}
			//System.out.println("hello"+flag_sup);
			if(user_cd.equals(Vhr_emp_cd.elementAt(j))){
				//System.out.println("into coordinator in 11111");
				if(Vcat_cd.elementAt(j).equals(categry_cd)) {
				//System.out.println("into coordinator");
			 String query3="select a.title_cd,nvl(a.title_nm,''),nvl(b.impl_by_days,''),nvl(to_char(a.impl_time_dt,'dd/mm/yyyy'),'')," +
		 		"nvl(to_char(a.sugg_dt,'dd/mm/yyyy'),''),nvl(a.sugg_by,''),nvl(a.supervisor_cd,''),a.action_by_supervisor,a.status,a.hr_action_status,nvl(a.hr_emp_cd,''), " +
		 		"to_char(a.hr_action_dt,'dd/mm/yyyy hh24:mi:ss'),to_char(a.SUPERVISOR_ACTION_DT,'dd/mm/yyyy hh24:mi:ss') " +
		 		",OUTCOME_REGD,to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss'),ACTION_DTL,to_char(a.ACTION_DT,'dd/mm/yyyy hh24:mi:ss'),VERIFY_FLAG,to_char(a.VERIFY_DT,'dd/mm/yyyy hh24:mi:ss'),b.rev_flag"
		 		+ ",nvl(to_char(ACTUAL_CLOSER_DT,'dd/mm/yyyy'),''),hr_action_status_reason,MEETING_VENUE " +
		 		"from sugg_title_mst a ,sugg_title_dtl b " +
		 		" where (a.status='FORWARD' OR a.line_mgr='"+user_cd+"' OR supervisor_cd='"+user_cd+"') and a.title_cd=b.title_cd and b.flag='Y' " +
		 		" order by a.title_cd DESC";
		rset=stmt.executeQuery(query3);
		//System.out.println("query3-For Fetching Hr data-"+query3);
			
		
		while (rset.next())
		{
			String temp_sts=rset.getString(9)==null?"":rset.getString(9);
			String temp_out=rset.getString(14)==null?"":rset.getString(14);
			String temp_dt=rset.getString(21)==null?"":rset.getString(21);
			
		      if(!temp_sts.equals("RETURN")){  
		    	  if(!temp_out.equals("NONIMPLEMENTABLE")){
		    		  if(temp_dt.equals("")){
							title_cd21_hr.add(rset.getString(1)==null?"":rset.getString(1));
							title_nm11_hr.add(rset.getString(2)==null?"":rset.getString(2));
							impl_by_days11_hr.add(rset.getString(3)==null?"":rset.getString(3));
							impl_time_dt11_hr.add(rset.getString(4)==null?"":rset.getString(4));
							sugg_dt1_hr.add(rset.getString(5)==null?"":rset.getString(5));
							sugg_by1_hr.add(rset.getString(6)==null?"":rset.getString(6));
							sel_sup_cd.add(rset.getString(7)==null?"":rset.getString(7));
							action_sts1_hr.add(rset.getString(8)==null?"Pending":rset.getString(8));
							status_sup_hr.add(rset.getString(9)==null?"":rset.getString(9));
							status_hr.add(rset.getString(10)==null?"":rset.getString(10));
							user_assign=rset.getString(11)==null?"":rset.getString(11);
							hr_act_dt.add(rset.getString(12)==null?"-":rset.getString(12));
							sup_act_dt.add(rset.getString(13)==null?"":rset.getString(13));
							Voutcome_regd.add(rset.getString(14)==null?"-":rset.getString(14));
							Voutcome_regd_dt.add(rset.getString(15)==null?"-":rset.getString(15));
							Vaction_dtl.add(rset.getString(16)==null?"-":rset.getString(16));
							Vaction_dt.add(rset.getString(17)==null?"-":rset.getString(17));
							Vverify_flag.add(rset.getString(18)==null?"-":rset.getString(18));
							Vverify_dt.add(rset.getString(19)==null?"-":rset.getString(19));
							REV_flag.add(rset.getString(20)==null?"-":rset.getString(20));
							Vactual_close_dt.add(rset.getString(21)==null?"-":rset.getString(21));
							hr_action_sts_reason.add(rset.getString(22)==null?"":rset.getString(22));
							meet_venue.add(rset.getString(23)==null?"":rset.getString(23));
		    		  }}}
		}
			
			
		for(int k=0;k<sugg_by1_hr.size();k++){
			String q2="select emp_nm from hr_emp_mst where emp_cd='"+sugg_by1_hr.elementAt(k)+"' ";
			//System.out.println("---"+q2);
			rset=stmt.executeQuery(q2);
			if(rset.next())
			{
				emp_nm_under_sup_hr.add(rset.getString(1)==null?"":rset.getString(1));
			}else{
				emp_nm_under_sup_hr.add("");
			}		
		}
			if(title_cd21_hr.size()>0){
				for(int k=0;k<title_cd21_hr.size();k++)
				{
					String f1="";
					String f2="";
					String path=request.getRealPath("/PDF");
					f2="SUGGESTION-"+title_cd21_hr.elementAt(k)+".pdf";
					f1="SUGGESTION-"+title_cd21_hr.elementAt(k);
					f1=path+"/"+f1;
					//System.out.println("rrrf1---"+f1);
					File pdf_file=new File(f1+".pdf");
					//System.out.println("inv_file---"+pdf_file);
					
					if(pdf_file.exists())
					{
						
							path=path+"//"+pdf_file;
							
				            String context_nm = request.getContextPath();
							String server_nm = request.getServerName();
							String server_port = ""+request.getServerPort();
							  //system.out.println("invoice_pdf_path--"+invoice_pdf_path1);
							String url_start = "http://"+server_nm+":"+server_port+context_nm;
							
							pdfpath_hr = path;
							pdfpath_hr = pdfpath_hr.substring(pdfpath_hr.indexOf("//"));
							
							pdfpath_hr = url_start+"/PDF/"+f2;
						//	System.out.println("pdfpath---> "+pdfpath);
							pdf_hr.add(pdfpath_hr);
					}
					else{
						pdf_hr.add("-");
					}
					
			}}
				
		break;
			}
				}
			}  //else for hr
		}  //main for 
		}else{

			
			if(flag_sup.equalsIgnoreCase("N")){
			System.out.println("in line manager");
		String q1="select nvl(emp_nm,''),nvl(comp_cd,'') from hr_emp_mst where emp_cd='"+user_cd+"' ";
		//System.out.println("---"+q1);
		rset=stmt.executeQuery(q1);
        if(rset.next())
		{
        	emp_nm=rset.getString(1)==null?"":rset.getString(1);
        	comp_cd=rset.getString(2)==null?"":rset.getString(2);
		}
       
       //for(int i=0;i<emp_cd.size();i++){
//       String query3="select a.title_cd,nvl(a.title_nm,''), nvl(c.impl_by_days,''), nvl(to_char(a.impl_time_dt,'dd/mm/yyyy'),'')," +
//       		"to_char(a.sugg_dt,'dd/mm/yyyy'),nvl(a.sugg_by,''),nvl(b.emp_nm,''),nvl(a.supervisor_cd,''),a.action_by_supervisor,a.status,to_char(a.SUPERVISOR_ACTION_DT,'dd/mm/yyyy hh24:mi:ss')" +
//       		",a.HR_ACTION_STATUS,to_char(a.HR_ACTION_DT,'dd/mm/yyyy hh24:mi:ss'),OUTCOME_REGD,to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss'),ACTION_DTL,to_char(a.ACTION_DT,'dd/mm/yyyy hh24:mi:ss'),VERIFY_FLAG,to_char(a.VERIFY_DT,'dd/mm/yyyy hh24:mi:ss') " +
//       		" from " +
//       		" sugg_title_mst a,hr_emp_mst b,sugg_title_dtl c where  a.line_mgr='"+user_cd+"'  " +
//       				" and a.sugg_by=b.emp_cd and c.flag='Y' and a.title_cd=c.title_cd  order by a.title_cd DESC ";
      
        String query3="select a.title_cd,nvl(a.title_nm,''), nvl(c.impl_by_days,''), nvl(to_char(a.impl_time_dt,'dd/mm/yyyy'),'')," +
           		"to_char(a.sugg_dt,'dd/mm/yyyy'),nvl(a.sugg_by,''),nvl(b.emp_nm,''),nvl(a.supervisor_cd,''),a.action_by_supervisor,"
           		+ "a.status,to_char(a.SUPERVISOR_ACTION_DT,'dd/mm/yyyy hh24:mi:ss')" +
           		",a.HR_ACTION_STATUS,to_char(a.HR_ACTION_DT,'dd/mm/yyyy hh24:mi:ss'),OUTCOME_REGD,"
           		+ "to_char(a.OUTCOME_REGD_DT,'dd/mm/yyyy hh24:mi:ss'),ACTION_DTL,to_char(a.ACTION_DT,'dd/mm/yyyy hh24:mi:ss'),"
           		+ "VERIFY_FLAG,to_char(a.VERIFY_DT,'dd/mm/yyyy hh24:mi:ss'),to_char(ACTUAL_CLOSER_DT,'dd/mm/yyyy'),hr_action_status_reason,MEETING_VENUE " +
           		" from " +
           		" sugg_title_mst a,hr_emp_mst b,sugg_title_dtl c where  a.line_mgr='"+user_cd+"'  " +
   				" and a.sugg_by=b.emp_cd and c.flag='Y' and a.title_cd=c.title_cd order by a.title_cd DESC ";
		rset=stmt.executeQuery(query3);
//		System.out.println("query3 forline manager data--"+query3);
		while (rset.next())
		{
			String temp_sts=rset.getString(10)==null?"":rset.getString(10);
			String temp_out=rset.getString(14)==null?"":rset.getString(14);
			String temp_dt=rset.getString(20)==null?"":rset.getString(20);
			
		      if(!temp_sts.equals("RETURN")){  
		    	  if(!temp_out.equals("NONIMPLEMENTABLE")){
		    		  if(temp_dt.equals("")){
						title_cd2.add(rset.getString(1)==null?"":rset.getString(1));
						title_nm1.add(rset.getString(2)==null?"":rset.getString(2));
						impl_by_days1.add(rset.getString(3)==null?"":rset.getString(3));
						impl_time_dt1.add(rset.getString(4)==null?"":rset.getString(4));
						sugg_dt.add(rset.getString(5)==null?"":rset.getString(5));
						sugg_by.add(rset.getString(6)==null?"":rset.getString(6));
						sugg_nm.add(rset.getString(7)==null?"":rset.getString(7));
						action_sts.add(rset.getString(9)==null?"Pending":rset.getString(9));
						sts.add(rset.getString(10)==null?"":rset.getString(10));
						action_sts_dt.add(rset.getString(11)==null?"":rset.getString(11));
						action_hr.add(rset.getString(12)==null?"-":rset.getString(12));
						hr_action_dt.add(rset.getString(13)==null?"-":rset.getString(13));
						OUTCOME_REGD.add(rset.getString(14)==null?"-":rset.getString(14));
						OUTCOME_REGD_DT.add(rset.getString(15)==null?"-":rset.getString(15));
						ACTION_DTL.add(rset.getString(16)==null?"-":rset.getString(16));
						ACTION_DT.add(rset.getString(17)==null?"-":rset.getString(17));
						VERIFY_FLAG.add(rset.getString(18)==null?"-":rset.getString(18));
						VERIFY_DT.add(rset.getString(19)==null?"-":rset.getString(19));
						Vactual_close_dt.add(rset.getString(20)==null?"-":rset.getString(20));
						hr_action_sts_reason.add(rset.getString(21)==null?"":rset.getString(21));
						meet_venue.add(rset.getString(22)==null?"":rset.getString(22));
		      }}}
			
		}
       //}
		
		}
		}
		
	}
	catch(SQLException e)
	{
		System.err.println("Error"+e);
		System.err.println("..DataBean_TIMS.java.in Query :.."+queryString);
		e.printStackTrace();
	}    
}
//end of //RG20170207
Vector Vemp_cd=new Vector();
Vector Vemp_nm=new Vector();
Vector Vemp_sts=new Vector();
Vector emp_cd_sel=new Vector();
Vector emp_nm_sel=new Vector();
String flag_emp="";

public void Add_Update_Supervisor() throws SQLException
{
	try
	{ 
		String query="Select emp_cd,emp_nm,emp_super_role from sugg_emp_mst where emp_super_role='Y' order by emp_nm ";
		rset1=stmt1.executeQuery(query);
		while(rset1.next())
		{
			Vemp_cd.add(rset1.getString(1));
			Vemp_nm.add(rset1.getString(2));
			Vemp_sts.add(rset1.getString(3));
		}
		if(flag_emp.equalsIgnoreCase("Y")){
			queryString="select distinct(b.dept_nm),e.emp_cd,e.emp_nm,a.dept_cd"
				+ " from hr_emp_rel_mst a, hr_dept_mst b,hr_branch_mst c,"
				+ " hr_desg_mst d,hr_emp_mst e  where a.dept_cd=b.dept_cd and a.branch_cd='1'"
				+ " and a.branch_cd=c.branch_cd  and a.desg_cd=d.desg_cd  "
				+ "and a.emp_cd=e.emp_cd and e.emp_status='Y' AND " +
				"(lower(e.emp_nm) LIKE '"+Search_char1+"%' or upper(e.emp_nm) LIKE '"+Search_char1+"%' ) order by emp_nm ";
		
//			System.out.println("queryString-ruchi--"+queryString);
			//System.out.println("DUTY-TIME-1--: "+queryString);
			rset=stmt.executeQuery(queryString);
			while (rset.next())
			{
				emp_cd_sel.add(rset.getString(2));
				emp_nm_sel.add(rset.getString(3));
			}
		}
	}
	catch(SQLException e)
	{
		System.err.println("Error"+e);
		System.err.println("..DataBean_TIMS.java.in Query :.."+queryString);
		e.printStackTrace();
	}
     
}//end of //RG20170207
/////////////////////SB20170216////////////////////////////
String action_dtl="";
String action_dt="";
String outcome_regd="";
String outcome_regd_dt="";
String committee_venue="";
String committee_meeting_dt="";
String committee_reco="";
String committee_members="";
String committee_members1="";

String verify_flag="";
String verify_dt="";
String verify_cd="";
String verify_nm="";
String hr_action_status="";
Vector Voutcome_regd=new Vector();
Vector Voutcome_regd_dt=new Vector();
Vector Vaction_dtl=new Vector();
Vector Vaction_dt=new Vector();
Vector Vverify_flag=new Vector();
Vector Vverify_dt=new Vector();

public Vector getVoutcome_regd() {return Voutcome_regd;}
public Vector getVoutcome_regd_dt() {return Voutcome_regd_dt;}
public Vector getVaction_dtl() {return Vaction_dtl;}
public Vector getVaction_dt() {return Vaction_dt;}
public Vector getVverify_flag() {return Vverify_flag;}
public Vector getVverify_dt() {return Vverify_dt;}

public String getAction_dtl() {return action_dtl;}
public String getAction_dt() {return action_dt;}
public String getOutcome_regd() {return outcome_regd;}
public String getOutcome_regd_dt() {return outcome_regd_dt;}
public String getCommittee_venue() {return committee_venue;}
public String getCommittee_meeting_dt() {return committee_meeting_dt;}
public String getCommittee_members() {return committee_members;}
public String getCommittee_reco() {return committee_reco;}
public String getVerify_flag() {return verify_flag;}
public String getVerify_dt() {return verify_dt;}
public String getVerify_cd() {return verify_cd;}
public String getVerify_nm() {return verify_nm;}
public String getHr_action_status() {return hr_action_status;}

public Vector getSts() {
	return sts;
}

public Vector getAction_sts_dt() {
	return action_sts_dt;
}

public String getSsup_cd() {
	return Ssup_cd;
}

public String getSemp_nm1() {
	return Semp_nm1;
}

public String getRes_supervisor() {
	return res_supervisor;
}

public String getSsupemp_nm1() {
	return Ssupemp_nm1;
}

public String getCommittee_members1() {
	return committee_members1;
}

public Vector getACTION_DT() {
	return ACTION_DT;
}

public void setACTION_DT(Vector action_dt) {
	ACTION_DT = action_dt;
}

public Vector getACTION_DTL() {
	return ACTION_DTL;
}

public void setACTION_DTL(Vector action_dtl) {
	ACTION_DTL = action_dtl;
}

public Vector getHr_action_dt() {
	return hr_action_dt;
}

public void setHr_action_dt(Vector hr_action_dt) {
	this.hr_action_dt = hr_action_dt;
}

public Vector getOUTCOME_REGD() {
	return OUTCOME_REGD;
}

public void setOUTCOME_REGD(Vector outcome_regd) {
	OUTCOME_REGD = outcome_regd;
}

public Vector getOUTCOME_REGD_DT() {
	return OUTCOME_REGD_DT;
}

public void setOUTCOME_REGD_DT(Vector outcome_regd_dt) {
	OUTCOME_REGD_DT = outcome_regd_dt;
}

public Vector getVERIFY_DT() {
	return VERIFY_DT;
}

public void setVERIFY_DT(Vector verify_dt) {
	VERIFY_DT = verify_dt;
}

public Vector getVERIFY_FLAG() {
	return VERIFY_FLAG;
}

public void setVERIFY_FLAG(Vector verify_flag) {
	VERIFY_FLAG = verify_flag;
}

public Vector getAction_hr() {
	return action_hr;
}

public Vector getHr_act_pdf() {
	return hr_act_pdf;
}

public void setHr_act_pdf(Vector hr_act_pdf) {
	this.hr_act_pdf = hr_act_pdf;
}

public Vector getHr_dt_pdf() {
	return hr_dt_pdf;
}

public void setHr_dt_pdf(Vector hr_dt_pdf) {
	this.hr_dt_pdf = hr_dt_pdf;
}

public Vector getSup_action() {
	return sup_action;
}

public void setSup_action(Vector sup_action) {
	this.sup_action = sup_action;
}

public Vector getVoutcome_regd_dt1() {
	return voutcome_regd_dt1;
}

public void setVoutcome_regd_dt1(Vector voutcome_regd_dt1) {
	this.voutcome_regd_dt1 = voutcome_regd_dt1;
}

public Vector getVoutcome_regd1() {
	return voutcome_regd1;
}

public void setVoutcome_regd1(Vector voutcome_regd1) {
	this.voutcome_regd1 = voutcome_regd1;
}

public Vector getSup_act_dt_pdf() {
	return sup_act_dt_pdf;
}

public String getSysdt() {
	return sysdt;
}

public void setSysdt(String sysdt) {
	this.sysdt = sysdt;
}

public String getHr_hld_reason() {
	return hr_hld_reason;
}

public void setFlag_track1(String flag_track1) {
	this.flag_track1 = flag_track1;
}

public void setFlag1_rtn(String flag1_rtn) {
	this.flag1_rtn = flag1_rtn;
}

public String getCurr_sys_sts_rtn() {
	return curr_sys_sts_rtn;
}

public void setCurr_sys_sts_rtn(String curr_sys_sts_rtn) {
	this.curr_sys_sts_rtn = curr_sys_sts_rtn;
}

public String getSugg_brf_bene_rtn() {
	return sugg_brf_bene_rtn;
}

public void setSugg_brf_bene_rtn(String sugg_brf_bene_rtn) {
	this.sugg_brf_bene_rtn = sugg_brf_bene_rtn;
}

public String getSugg_desc_rtn() {
	return sugg_desc_rtn;
}
public Vector getREV_flag() {
	return REV_flag;
}
public String getMeeting_dt() {
	return meeting_dt;
}
public void setMeeting_dt(String meeting_dt) {
	this.meeting_dt = meeting_dt;
}
public String getVenue() {
	return venue;
}
public void setRequest(HttpServletRequest request) {
	this.request = request;
}
public String getPdfpath() {
	return pdfpath;
}
public void setPdfpath(String pdfpath) {
	this.pdfpath = pdfpath;
}

public String getSup_sts() {
	return sup_sts;
}
public String getRev_flag() {
	return rev_flag;
}
public String getEstimated_save() {
	return estimated_save;
}
public void setEstimated_save(String estimated_save) {
	this.estimated_save = estimated_save;
}
public String getCost_reduce() {
	return cost_reduce;
}
public Vector getPdf() {
	return pdf;
}
public Vector getPdf_hr() {
	return pdf_hr;
}
public Vector getVhr_emp_cd() {
	return Vhr_emp_cd;
}
public Vector getVemp_nm() {
	return Vemp_nm;
}
public void setVemp_nm(Vector vemp_nm) {
	Vemp_nm = vemp_nm;
}
public Vector getVemp_cd() {
	return Vemp_cd;
}
public Vector getVemp_sts() {
	return Vemp_sts;
}
public Vector getEmp_nm_sel() {
	return emp_nm_sel;
}
public void setEmp_nm_sel(Vector emp_nm_sel) {
	this.emp_nm_sel = emp_nm_sel;
}
public Vector getEmp_cd_sel() {
	return emp_cd_sel;
}
public void setFlag_emp(String flag_emp) {
	this.flag_emp = flag_emp;
}
public void setSearch_char1(String search_char1) {
	Search_char1 = search_char1;
}
public String getCount_assgn_hr() {
	return count_assgn_hr;
}
public void setCount_assgn_hr(String count_assgn_hr) {
	this.count_assgn_hr = count_assgn_hr;
}
public String getCount_impl() {
	return count_impl;
}
public void setCount_impl(String count_impl) {
	this.count_impl = count_impl;
}
public String getCount_submit() {
	return count_submit;
}
public String getCount_committee() {
	return count_committee;
}

public String getCur_dt() {
	return cur_dt;
}
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
public void setMonth(String month) {
	this.month = month;
}
public String getSt_dt() {
	return st_dt;
}
public void setSt_dt(String st_dt) {
	this.st_dt = st_dt;
}
public String getEnd_dt() {
	return end_dt;
}
public Vector getFlg_cmt() {
	return flg_cmt;
}
public void setFlg_cmt(Vector flg_cmt) {
	this.flg_cmt = flg_cmt;
}

public Vector getFlg_hr() {
	return flg_hr;
}
public void setFlg_hr(Vector flg_hr) {
	this.flg_hr = flg_hr;
}

public Vector getFlg_sup() {
	return flg_sup;
}
public Vector getTitle_cd_rpt() {
	return title_cd_rpt;
}
public Vector getTitle_nm_rpt() {
	return title_nm_rpt;
}
public Vector getSugg_dt_rpt() {
	return sugg_dt_rpt;
}
public Vector getSugg_bynm_rpt() {
	return sugg_bynm_rpt;
}
public String getEnddt() {
	return enddt;
}
public void setEnddt(String enddt) {
	this.enddt = enddt;
}
public void setDt(String dt) {
	this.dt = dt;
}
public Vector getFlg_f() {
	return flg_f;
}
public void setFlg_f(Vector flg_f) {
	this.flg_f = flg_f;
}
public Vector getFlg_f1() {
	return flg_f1;
}
public void setFlg_f1(Vector flg_f1) {
	this.flg_f1 = flg_f1;
}
public Vector getFlg_f2() {
	return flg_f2;
}
public void setFlg_f2(Vector flg_f2) {
	this.flg_f2 = flg_f2;
}
public Vector getFlg_m() {
	return flg_m;
}
public void setFlg_m(Vector flg_m) {
	this.flg_m = flg_m;
}
public Vector getFlg_m1() {
	return flg_m1;
}
public void setFlg_m1(Vector flg_m1) {
	this.flg_m1 = flg_m1;
}
public Vector getFlg_m2() {
	return flg_m2;
}
public void setFlg_m2(Vector flg_m2) {
	this.flg_m2 = flg_m2;
}
public Vector getFlg_mm() {
	return flg_mm;
}
public void setFlg_mm(Vector flg_mm) {
	this.flg_mm = flg_mm;
}
public Vector getFlg_mm1() {
	return flg_mm1;
}
public void setFlg_mm1(Vector flg_mm1) {
	this.flg_mm1 = flg_mm1;
}
public Vector getFlg_mm2() {
	return flg_mm2;
}
public void setFlg_mm2(Vector flg_mm2) {
	this.flg_mm2 = flg_mm2;
}
public Vector getFlg_w1() {
	return flg_w1;
}
public void setFlg_w1(Vector flg_w1) {
	this.flg_w1 = flg_w1;
}
public Vector getFlg_w2() {
	return flg_w2;
}
public void setFlg_w2(Vector flg_w2) {
	this.flg_w2 = flg_w2;
}
public Vector getFlg_w() {
	return flg_w;
}
public Vector getFlg_p() {
	return flg_p;
}
public void setFlg_p(Vector flg_p) {
	this.flg_p = flg_p;
}
public Vector getFlg_p1() {
	return flg_p1;
}
public void setFlg_p1(Vector flg_p1) {
	this.flg_p1 = flg_p1;
}
public Vector getFlg_p2() {
	return flg_p2;
}
public String getMinyear() {
	return minyear;
}

public Vector getFlg_f11() {
	return flg_f11;
}
public void setFlg_f11(Vector flg_f11) {
	this.flg_f11 = flg_f11;
}
public Vector getFlg_m11() {
	return flg_m11;
}
public void setFlg_m11(Vector flg_m11) {
	this.flg_m11 = flg_m11;
}
public Vector getFlg_w11() {
	return flg_w11;
}
public void setFlg_w11(Vector flg_w11) {
	this.flg_w11 = flg_w11;
}
public Vector getFlg_mm11() {
	return flg_mm11;
}
public Vector getFlg_f111() {
	return flg_f111;
}
public void setFlg_f111(Vector flg_f111) {
	this.flg_f111 = flg_f111;
}
public Vector getFlg_f1111() {
	return flg_f1111;
}
public void setFlg_f1111(Vector flg_f1111) {
	this.flg_f1111 = flg_f1111;
}
public Vector getFlg_m111() {
	return flg_m111;
}
public void setFlg_m111(Vector flg_m111) {
	this.flg_m111 = flg_m111;
}
public Vector getFlg_m1111() {
	return flg_m1111;
}
public void setFlg_m1111(Vector flg_m1111) {
	this.flg_m1111 = flg_m1111;
}
public Vector getFlg_mm111() {
	return flg_mm111;
}
public void setFlg_mm111(Vector flg_mm111) {
	this.flg_mm111 = flg_mm111;
}
public Vector getFlg_mm1111() {
	return flg_mm1111;
}
public void setFlg_mm1111(Vector flg_mm1111) {
	this.flg_mm1111 = flg_mm1111;
}
public Vector getFlg_w1111() {
	return flg_w1111;
}
public void setFlg_w1111(Vector flg_w1111) {
	this.flg_w1111 = flg_w1111;
}
public Vector getFlg_w111() {
	return flg_w111;
}
public void setFlg_mm11(Vector flg_mm11) {
	this.flg_mm11 = flg_mm11;
}
public String getEmp_alias_cd() {
	return emp_alias_cd;
}
public String getEmp_alias_cd1() {
	return emp_alias_cd1;
}
public Vector getCommittee_action_dt() {
	return committee_action_dt;
}
public void setCommittee_action_dt(Vector committee_action_dt) {
	this.committee_action_dt = committee_action_dt;
}
public Vector getHr_action_dt_rpt() {
	return hr_action_dt_rpt;
}
public void setHr_action_dt_rpt(Vector hr_action_dt_rpt) {
	this.hr_action_dt_rpt = hr_action_dt_rpt;
}
public Vector getSup_action_dt_rpt() {
	return sup_action_dt_rpt;
}
public Vector getHr_nm_p() {
	return hr_nm_p;
}
public void setHr_nm_p(Vector hr_nm_p) {
	this.hr_nm_p = hr_nm_p;
}
public Vector getSup_nm_p() {
	return sup_nm_p;
}
public Vector getVcat_cd() {
	return Vcat_cd;
}
public String getCategry_cd() {
	return categry_cd;
}
public String getCategry_cd1() {
	return categry_cd1;
}
public Vector getHr() {
	return hr;
}
public String getAct_rmk() {
	return act_rmk;
}
public void setAct_rmk(String act_rmk) {
	this.act_rmk = act_rmk;
}
public String getAct_flg() {
	return act_flg;
}
public String getVer_flg() {
	return Ver_flg;
}
public String getAwd_amt() {
	return awd_amt;
}
public Vector getVerify_flg() {
	return verify_flg;
}
public String getBen_amt() {
	return ben_amt;
}
public void setBen_amt(String ben_amt) {
	this.ben_amt = ben_amt;
}
public String getBene_flg() {
	return bene_flg;
}
public String getEnd_dt1() {
	return end_dt1;
}
public void setEnd_dt1(String end_dt1) {
	this.end_dt1 = end_dt1;
}
public void setSt_dt1(String st_dt1) {
	this.st_dt1 = st_dt1;
}
public Vector getSugg_by_rpt() {
	return sugg_by_rpt;
}
public String getSsup_act() {
	return Ssup_act;
}
public void setSsup_act(String ssup_act) {
	Ssup_act = ssup_act;
}
public String getSsup_rmk() {
	return Ssup_rmk;
}
public String getSSts() {
	return SSts;
}
public String getSdt() {
	return Sdt;
}
public String getActdt() {
	return Actdt;
}
public String getHrActdt() {
	return HrActdt;
}
public String getCount_committee_pen() {
	return count_committee_pen;
}
public void setCount_committee_pen(String count_committee_pen) {
	this.count_committee_pen = count_committee_pen;
}
public String getCount_nonimpl() {
	return count_nonimpl;
}
public void setCount_nonimpl(String count_nonimpl) {
	this.count_nonimpl = count_nonimpl;
}
public String getCount_sup_pen() {
	return count_sup_pen;
}
public void setCount_sup_pen(String count_sup_pen) {
	this.count_sup_pen = count_sup_pen;
}
public String getCount_sup_rtn() {
	return count_sup_rtn;
}
public void setCount_sup_rtn(String count_sup_rtn) {
	this.count_sup_rtn = count_sup_rtn;
}
public String getCount_hr_impl() {
	return count_hr_impl;
}
public String getTitle_cd_upd() {
	return title_cd_upd;
}
public String getFlag_upd() {
	return flag_upd;
}
public String getTitle_nm_upd() {
	return title_nm_upd;
}
public String getSugg_by_upd() {
	return sugg_by_upd;
}
public String getTit_nm1() {
	return tit_nm1;
}
public String getCurdt() {
	return curdt;
}
public Vector getImpl_or_nonimpl() {
	return impl_or_nonimpl;
}
public void setImpl_or_nonimpl(Vector impl_or_nonimpl) {
	this.impl_or_nonimpl = impl_or_nonimpl;
}
public Vector getAct_flg_rpt() {
	return act_flg_rpt;
}
public Vector getFlag_sts_rpt() {
	return flag_sts_rpt;
}
public void setFlag_sts_rpt(Vector flag_sts_rpt) {
	this.flag_sts_rpt = flag_sts_rpt;
}
public Vector getRevflg_rpt() {
	return revflg_rpt;
}
public void setRevflg_rpt(Vector revflg_rpt) {
	this.revflg_rpt = revflg_rpt;
}
public Vector getSts_rpt() {
	return sts_rpt;
}
public Vector getVhrsts_rpt() {
	return Vhrsts_rpt;
}
public Vector getFlg_m_R() {
	return flg_m_R;
}
public Vector getVSugg_by() {
	return VSugg_by;
}
public void setVSugg_by(Vector sugg_by) {
	VSugg_by = sugg_by;
}
public Vector getVtitle_cd() {
	return Vtitle_cd;
}
public void setVtitle_cd(Vector vtitle_cd) {
	Vtitle_cd = vtitle_cd;
}
public Vector getVSup_cd() {
	return VSup_cd;
}
public Vector getVtitle_nm() {
	return Vtitle_nm;
}
public Vector getVsugg_by_nm() {
	return Vsugg_by_nm;
}
public void setVsugg_by_nm(Vector vsugg_by_nm) {
	Vsugg_by_nm = vsugg_by_nm;
}
public Vector getVSup_by_nm() {
	return VSup_by_nm;
}
public Vector getVsugg_dt() {
	return Vsugg_dt;
}
public Vector getVdept_nm1() {
	return Vdept_nm1;
}
public void setVdept_nm1(Vector vdept_nm1) {
	Vdept_nm1 = vdept_nm1;
}
public Vector getVdesg_nm1() {
	return Vdesg_nm1;
}
public Vector getVaward() {
	return Vaward;
}
public void setVaward(Vector vaward) {
	Vaward = vaward;
}
public Vector getVcommittee() {
	return Vcommittee;
}
public void setVcommittee(Vector vcommittee) {
	Vcommittee = vcommittee;
}
public Vector getVhr_act() {
	return Vhr_act;
}
public void setVhr_act(Vector vhr_act) {
	Vhr_act = vhr_act;
}
public Vector getVhr_actdt() {
	return Vhr_actdt;
}
public void setVhr_actdt(Vector vhr_actdt) {
	Vhr_actdt = vhr_actdt;
}
public Vector getVsup_actdt() {
	return Vsup_actdt;
}
public void setVsup_actdt(Vector vsup_actdt) {
	Vsup_actdt = vsup_actdt;
}
public Vector getVsup_sts() {
	return Vsup_sts;
}
public void setVsup_sts(Vector vsup_sts) {
	Vsup_sts = vsup_sts;
}
public Vector getVsup_act() {
	return Vsup_act;
}
public Vector getVmonetary_save() {
	return Vmonetary_save;
}
public void setVmonetary_save(Vector vmonetary_save) {
	Vmonetary_save = vmonetary_save;
}
public Vector getVsuggby_als_cd() {
	return Vsuggby_als_cd;
}
public String getActual_saving() {
	return actual_saving;
}
public void setActual_saving(String actual_saving) {
	this.actual_saving = actual_saving;
}
public String getEst_saving() {
	return est_saving;
}
public void setEst_saving(String est_saving) {
	this.est_saving = est_saving;
}
public String getDiff() {
	return diff;
}
public Vector getVest_save_empwise() {
	return Vest_save_empwise;
}
public void setVest_save_empwise(Vector vest_save_empwise) {
	Vest_save_empwise = vest_save_empwise;
}
public Vector getVsugg_by() {
	return Vsugg_by;
}
public void setVsugg_by(Vector vsugg_by) {
	Vsugg_by = vsugg_by;
}
public Vector getVact_save_empwise() {
	return Vact_save_empwise;
}
public Vector getVsugg_bynm() {
	return Vsugg_bynm;
}
public Vector getVdesg_nm11() {
	return Vdesg_nm11;
}
public void setVdesg_nm11(Vector vdesg_nm11) {
	Vdesg_nm11 = vdesg_nm11;
}
public Vector getVdept_nm11() {
	return Vdept_nm11;
}
public String getCOMMITTEE_DECIDE_DT() {
	return COMMITTEE_DECIDE_DT;
}
public void setCOMMITTEE_DECIDE_DT(String cOMMITTEE_DECIDE_DT) {
	COMMITTEE_DECIDE_DT = cOMMITTEE_DECIDE_DT;
}
public Vector getVCommittee_descion_dt() {
	return VCommittee_descion_dt;
}
public void setVCommittee_descion_dt(Vector vCommittee_descion_dt) {
	VCommittee_descion_dt = vCommittee_descion_dt;
}



public Vector getAlphabets() {
	return alphabets;
}



public void setAlphabets(Vector alphabets) {
	this.alphabets = alphabets;
}



public Vector getVvemp_nm() {
	return Vvemp_nm;
}



public void setVvemp_nm(Vector vvemp_nm) {
	Vvemp_nm = vvemp_nm;
}



public Vector getVdept_nm() {
	return Vdept_nm;
}



public void setVdept_nm(Vector vdept_nm) {
	Vdept_nm = vdept_nm;
}



public Map getAll_employees() {
	return all_employees;
}



public void setAll_employees(Map all_employees) {
	this.all_employees = all_employees;
}



public Vector getEmp_act_cnt() {
	return emp_act_cnt;
}



public void setEmp_act_cnt(Vector emp_act_cnt) {
	this.emp_act_cnt = emp_act_cnt;
}



public Vector getVvdept_cd() {
	return Vvdept_cd;
}



public void setVvdept_cd(Vector vvdept_cd) {
	Vvdept_cd = vvdept_cd;
}



public String getImpl_timeline() {
	return impl_timeline;
}



public void setImpl_timeline(String impl_timeline) {
	this.impl_timeline = impl_timeline;
}



public String getTarget_close_dt() {
	return target_close_dt;
}



public void setTarget_close_dt(String target_close_dt) {
	this.target_close_dt = target_close_dt;
}



public String getActual_close_dt() {
	return actual_close_dt;
}



public void setActual_close_dt(String actual_close_dt) {
	this.actual_close_dt = actual_close_dt;
}



public String getExec_lead_emp_nm() {
	return Exec_lead_emp_nm;
}



public void setExec_lead_emp_nm(String exec_lead_emp_nm) {
	Exec_lead_emp_nm = exec_lead_emp_nm;
}



public String getEXEC_LEAD_CD() {
	return EXEC_LEAD_CD;
}



public void setEXEC_LEAD_CD(String eXEC_LEAD_CD) {
	EXEC_LEAD_CD = eXEC_LEAD_CD;
}



public Vector getVimpl_timeline() {
	return Vimpl_timeline;
}



public void setVimpl_timeline(Vector vimpl_timeline) {
	Vimpl_timeline = vimpl_timeline;
}



public Vector getVtarget_close_dt() {
	return Vtarget_close_dt;
}



public void setVtarget_close_dt(Vector vtarget_close_dt) {
	Vtarget_close_dt = vtarget_close_dt;
}



public Vector getVactual_close_dt() {
	return Vactual_close_dt;
}



public void setVactual_close_dt(Vector vactual_close_dt) {
	Vactual_close_dt = vactual_close_dt;
}

public Vector getVEXEC_LEAD_CD() {
	return VEXEC_LEAD_CD;
}

public void setVEXEC_LEAD_CD(Vector vEXEC_LEAD_CD) {
	VEXEC_LEAD_CD = vEXEC_LEAD_CD;
}

public String getCur_month() {
	return cur_month;
}

public void setCur_month(String cur_month) {
	this.cur_month = cur_month;
}

public Map getTemp_map() {
	return temp_map;
}

public void setTemp_map(Map temp_map) {
	this.temp_map = temp_map;
}

public Vector getVSUGG_BENEFIT() {
	return VSUGG_BENEFIT;
}

public void setVSUGG_BENEFIT(Vector vSUGG_BENEFIT) {
	VSUGG_BENEFIT = vSUGG_BENEFIT;
}

public Vector getVCOST_REDUCTION() {
	return VCOST_REDUCTION;
}

public void setVCOST_REDUCTION(Vector vCOST_REDUCTION) {
	VCOST_REDUCTION = vCOST_REDUCTION;
}

public Vector getVESTIMATED_SAVING() {
	return VESTIMATED_SAVING;
}

public void setVESTIMATED_SAVING(Vector vESTIMATED_SAVING) {
	VESTIMATED_SAVING = vESTIMATED_SAVING;
}

public Vector getVSUGG_BRIEF_BENEFIT() {
	return VSUGG_BRIEF_BENEFIT;
}

public void setVSUGG_BRIEF_BENEFIT(Vector vSUGG_BRIEF_BENEFIT) {
	VSUGG_BRIEF_BENEFIT = vSUGG_BRIEF_BENEFIT;
}

public Vector getVimpl_cost() {
	return Vimpl_cost;
}

public void setVimpl_cost(Vector vimpl_cost) {
	Vimpl_cost = vimpl_cost;
}

public String getMaxyear() {
	return maxyear;
}

public void setMaxyear(String maxyear) {
	this.maxyear = maxyear;
}

public String getCount_sup_recvd() {
	return count_sup_recvd;
}

public void setCount_sup_recvd(String count_sup_recvd) {
	this.count_sup_recvd = count_sup_recvd;
}

public String getCount_sup_potential() {
	return count_sup_potential;
}

public void setCount_sup_potential(String count_sup_potential) {
	this.count_sup_potential = count_sup_potential;
}

public String getCount_ET_Implementable() {
	return count_ET_Implementable;
}

public void setCount_ET_Implementable(String count_ET_Implementable) {
	this.count_ET_Implementable = count_ET_Implementable;
}

public String getCount_ET_pen() {
	return count_ET_pen;
}

public void setCount_ET_pen(String count_ET_pen) {
	this.count_ET_pen = count_ET_pen;
}

public String getForm_flag() {
	return form_flag;
}

public void setForm_flag(String form_flag) {
	this.form_flag = form_flag;
}
public String getCount_ET_rej() {
	return count_ET_rej;
}

public void setCount_ET_rej(String count_ET_rej) {
	this.count_ET_rej = count_ET_rej;
}
public String getFlg() {
	return flg;
}
public void setFlg(String flg) {
	this.flg = flg;
}
public String getSdptnm() {
	return Sdptnm;
}
public void setSdptnm(String sdptnm) {
	Sdptnm = sdptnm;
}
public String getSdsgnm() {
	return Sdsgnm;
}
public Vector getExec_lead_flag() {
	return exec_lead_flag;
}

public void setExec_lead_flag(Vector exec_lead_flag) {
	this.exec_lead_flag = exec_lead_flag;
}



public String getMoc_flag() {
	return moc_flag;
}



public void setMoc_flag(String moc_flag) {
	this.moc_flag = moc_flag;
}



public String getMoc_rmk() {
	return moc_rmk;
}



public void setMoc_rmk(String moc_rmk) {
	this.moc_rmk = moc_rmk;
}

public String getActual_saving_closed() {
	return actual_saving_closed;
}



public void setActual_saving_closed(String actual_saving_closed) {
	this.actual_saving_closed = actual_saving_closed;
}



public String getRequester_pending() {
	return requester_pending;
}



public void setRequester_pending(String requester_pending) {
	this.requester_pending = requester_pending;
}



public String getLEAD_pending() {
	return LEAD_pending;
}



public void setLEAD_pending(String lEAD_pending) {
	LEAD_pending = lEAD_pending;
}



public Vector getHr_action_sts_reason() {
	return hr_action_sts_reason;
}



public void setHr_action_sts_reason(Vector hr_action_sts_reason) {
	this.hr_action_sts_reason = hr_action_sts_reason;
}



public Vector getHr_actsts_for_pdf() {
	return hr_actsts_for_pdf;
}



public void setHr_actsts_for_pdf(Vector hr_actsts_for_pdf) {
	this.hr_actsts_for_pdf = hr_actsts_for_pdf;
}



public String getSsup_actdt() {
	return Ssup_actdt;
}



public void setSsup_actdt(String ssup_actdt) {
	Ssup_actdt = ssup_actdt;
}



public Vector getCnt_line_mgr_flag() {
	return cnt_line_mgr_flag;
}



public void setCnt_line_mgr_flag(Vector cnt_line_mgr_flag) {
	this.cnt_line_mgr_flag = cnt_line_mgr_flag;
}



public Vector getLine_mgr_cd() {
	return line_mgr_cd;
}



public void setLine_mgr_cd(Vector line_mgr_cd) {
	this.line_mgr_cd = line_mgr_cd;
}



public Vector getSup_cd_rpt() {
	return sup_cd_rpt;
}



public void setSup_cd_rpt(Vector sup_cd_rpt) {
	this.sup_cd_rpt = sup_cd_rpt;
}



public String getExec_lead_dt() {
	return exec_lead_dt;
}



public void setExec_lead_dt(String exec_lead_dt) {
	this.exec_lead_dt = exec_lead_dt;
}



public String getLine_mgrcd() {
	return line_mgrcd;
}



public void setLine_mgrcd(String line_mgrcd) {
	this.line_mgrcd = line_mgrcd;
}



public Vector getMeet_venue() {
	return meet_venue;
}



public void setMeet_venue(Vector meet_venue) {
	this.meet_venue = meet_venue;
}



public Vector getTitle_flg_committee() {
	return title_flg_committee;
}



public void setTitle_flg_committee(Vector title_flg_committee) {
	this.title_flg_committee = title_flg_committee;
}



public Vector getAct_closer_dt() {
	return act_closer_dt;
}



public void setAct_closer_dt(Vector act_closer_dt) {
	this.act_closer_dt = act_closer_dt;
}



public Vector getImpl_lead_timeline_flag() {
	return impl_lead_timeline_flag;
}



public void setImpl_lead_timeline_flag(Vector impl_lead_timeline_flag) {
	this.impl_lead_timeline_flag = impl_lead_timeline_flag;
}



public String getCOMM_ACTUAL_SAVINGS() {
	return COMM_ACTUAL_SAVINGS;
}



public void setCOMM_ACTUAL_SAVINGS(String cOMM_ACTUAL_SAVINGS) {
	COMM_ACTUAL_SAVINGS = cOMM_ACTUAL_SAVINGS;
}



public String getEXEC_ACTUAL_SAVINGS() {
	return EXEC_ACTUAL_SAVINGS;
}



public void setEXEC_ACTUAL_SAVINGS(String eXEC_ACTUAL_SAVINGS) {
	EXEC_ACTUAL_SAVINGS = eXEC_ACTUAL_SAVINGS;
}



public String getEXEC_BENEFICIAL_AMT() {
	return EXEC_BENEFICIAL_AMT;
}



public void setEXEC_BENEFICIAL_AMT(String eXEC_BENEFICIAL_AMT) {
	EXEC_BENEFICIAL_AMT = eXEC_BENEFICIAL_AMT;
}



public String getEXEC_BENEFICIAL_FLAG() {
	return EXEC_BENEFICIAL_FLAG;
}



public void setEXEC_BENEFICIAL_FLAG(String eXEC_BENEFICIAL_FLAG) {
	EXEC_BENEFICIAL_FLAG = eXEC_BENEFICIAL_FLAG;
}



public Vector getTitle_cd_COMM() {
	return title_cd_COMM;
}



public void setTitle_cd_COMM(Vector title_cd_COMM) {
	this.title_cd_COMM = title_cd_COMM;
}



public Vector getTitle_NM_COMM() {
	return title_NM_COMM;
}



public void setTitle_NM_COMM(Vector title_NM_COMM) {
	this.title_NM_COMM = title_NM_COMM;
}



public Vector getSugg_bynm_COMM() {
	return sugg_bynm_COMM;
}



public void setSugg_bynm_COMM(Vector sugg_bynm_COMM) {
	this.sugg_bynm_COMM = sugg_bynm_COMM;
}



public Vector getSugg_bycd_COMM() {
	return sugg_bycd_COMM;
}



public void setSugg_bycd_COMM(Vector sugg_bycd_COMM) {
	this.sugg_bycd_COMM = sugg_bycd_COMM;
}



public Vector getSugg_commempcd_COMM() {
	return sugg_commempcd_COMM;
}



public void setSugg_commempcd_COMM(Vector sugg_commempcd_COMM) {
	this.sugg_commempcd_COMM = sugg_commempcd_COMM;
}



public Vector getSugg_commempnm_COMM() {
	return sugg_commempnm_COMM;
}



public void setSugg_commempnm_COMM(Vector sugg_commempnm_COMM) {
	this.sugg_commempnm_COMM = sugg_commempnm_COMM;
}



public Vector getTitle_cd_req() {
	return title_cd_req;
}



public void setTitle_cd_req(Vector title_cd_req) {
	this.title_cd_req = title_cd_req;
}



public Vector getTitle_NM_req() {
	return title_NM_req;
}



public void setTitle_NM_req(Vector title_NM_req) {
	this.title_NM_req = title_NM_req;
}



public Vector getSugg_bynm_req() {
	return sugg_bynm_req;
}



public void setSugg_bynm_req(Vector sugg_bynm_req) {
	this.sugg_bynm_req = sugg_bynm_req;
}



public Vector getSugg_bycd_req() {
	return sugg_bycd_req;
}



public void setSugg_bycd_req(Vector sugg_bycd_req) {
	this.sugg_bycd_req = sugg_bycd_req;
}



public Vector getSugg_commempcd_req() {
	return sugg_commempcd_req;
}



public void setSugg_commempcd_req(Vector sugg_commempcd_req) {
	this.sugg_commempcd_req = sugg_commempcd_req;
}



public Vector getSugg_commempnm_req() {
	return sugg_commempnm_req;
}



public void setSugg_commempnm_req(Vector sugg_commempnm_req) {
	this.sugg_commempnm_req = sugg_commempnm_req;
}



public Vector getTitle_cd_lm() {
	return title_cd_lm;
}



public void setTitle_cd_lm(Vector title_cd_lm) {
	this.title_cd_lm = title_cd_lm;
}



public Vector getTitle_NM_lm() {
	return title_NM_lm;
}



public void setTitle_NM_lm(Vector title_NM_lm) {
	this.title_NM_lm = title_NM_lm;
}



public Vector getSugg_bynm_lm() {
	return sugg_bynm_lm;
}



public void setSugg_bynm_lm(Vector sugg_bynm_lm) {
	this.sugg_bynm_lm = sugg_bynm_lm;
}



public Vector getSugg_bycd_lm() {
	return sugg_bycd_lm;
}



public void setSugg_bycd_lm(Vector sugg_bycd_lm) {
	this.sugg_bycd_lm = sugg_bycd_lm;
}



public Vector getSugg_commempcd_lm() {
	return sugg_commempcd_lm;
}



public void setSugg_commempcd_lm(Vector sugg_commempcd_lm) {
	this.sugg_commempcd_lm = sugg_commempcd_lm;
}



public Vector getSugg_commempnm_lm() {
	return sugg_commempnm_lm;
}



public void setSugg_commempnm_lm(Vector sugg_commempnm_lm) {
	this.sugg_commempnm_lm = sugg_commempnm_lm;
}



public Vector getTitle_cd_lead() {
	return title_cd_lead;
}



public void setTitle_cd_lead(Vector title_cd_lead) {
	this.title_cd_lead = title_cd_lead;
}



public Vector getTitle_NM_lead() {
	return title_NM_lead;
}



public void setTitle_NM_lead(Vector title_NM_lead) {
	this.title_NM_lead = title_NM_lead;
}



public Vector getSugg_bynm_lead() {
	return sugg_bynm_lead;
}



public void setSugg_bynm_lead(Vector sugg_bynm_lead) {
	this.sugg_bynm_lead = sugg_bynm_lead;
}



public Vector getSugg_bycd_lead() {
	return sugg_bycd_lead;
}



public void setSugg_bycd_lead(Vector sugg_bycd_lead) {
	this.sugg_bycd_lead = sugg_bycd_lead;
}



public Vector getSugg_commempcd_lead() {
	return sugg_commempcd_lead;
}



public void setSugg_commempcd_lead(Vector sugg_commempcd_lead) {
	this.sugg_commempcd_lead = sugg_commempcd_lead;
}



public Vector getSugg_commempnm_lead() {
	return sugg_commempnm_lead;
}



public void setSugg_commempnm_lead(Vector sugg_commempnm_lead) {
	this.sugg_commempnm_lead = sugg_commempnm_lead;
}



public Vector getExec_lead_ben_flag() {
	return exec_lead_ben_flag;
}



public void setExec_lead_ben_flag(Vector exec_lead_ben_flag) {
	this.exec_lead_ben_flag = exec_lead_ben_flag;
}



public Vector getExec_lead_ben_amt() {
	return exec_lead_ben_amt;
}



public void setExec_lead_ben_amt(Vector exec_lead_ben_amt) {
	this.exec_lead_ben_amt = exec_lead_ben_amt;
}



public Vector getComm_Actual_savings() {
	return comm_Actual_savings;
}



public void setComm_Actual_savings(Vector comm_Actual_savings) {
	this.comm_Actual_savings = comm_Actual_savings;
}



public Vector getExec_lead_actual_savings() {
	return exec_lead_actual_savings;
}



public void setExec_lead_actual_savings(Vector exec_lead_actual_savings) {
	this.exec_lead_actual_savings = exec_lead_actual_savings;
}



public Vector getSugg_DT_COMM() {
	return sugg_DT_COMM;
}



public void setSugg_DT_COMM(Vector sugg_DT_COMM) {
	this.sugg_DT_COMM = sugg_DT_COMM;
}



public Vector getSugg_DT_CO() {
	return sugg_DT_CO;
}



public void setSugg_DT_CO(Vector sugg_DT_CO) {
	this.sugg_DT_CO = sugg_DT_CO;
}



public Vector getSugg_DT_req() {
	return sugg_DT_req;
}



public void setSugg_DT_req(Vector sugg_DT_req) {
	this.sugg_DT_req = sugg_DT_req;
}



public Vector getSugg_DT_lm() {
	return sugg_DT_lm;
}



public void setSugg_DT_lm(Vector sugg_DT_lm) {
	this.sugg_DT_lm = sugg_DT_lm;
}



public Vector getSugg_dt_lead() {
	return sugg_dt_lead;
}



public void setSugg_dt_lead(Vector sugg_dt_lead) {
	this.sugg_dt_lead = sugg_dt_lead;
}


}
////////^SB20170216/////////////////////////////////////////



