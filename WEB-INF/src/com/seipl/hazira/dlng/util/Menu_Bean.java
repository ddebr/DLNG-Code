//SB20091209:SCR10001
package com.seipl.hazira.dlng.util;
import java.lang.*;
import java.util.*;
//import com.basic.random;
import javax.naming.*;
import javax.sql.*;
import java.sql.*;


import java.io.*;




public class Menu_Bean
{

	Connection conn;
	Statement stmt,stmt1;
	ResultSet rset,rset1;
	String query = "";
	String query1="";

	
	public String empl_GrpId="";
	public String op_cd="";
	public String moduleNmMotherPg="";

	
	
	public String emp_nm = "";
	public String option="";
	public String module="";
	public String module_cd="";
	String file_path ="";
	String selForm_id="";
	String head_tab = "";
//	Sravan
	public Vector ALW_ADD=new Vector();
	public  Vector ALW_DEL=new Vector();
	public Vector ALW_UPD=new Vector();
	public Vector ALW_PRINT=new Vector();
	public Vector ALW_CHECK=new Vector();// Defined By Samik Shah On 17th January, 2011 ...
	public Vector ALW_AUTHORIZE=new Vector();// Defined By Samik Shah On 17th January, 2011 ...
	public Vector ALW_GRANT=new Vector();
	public Vector ALW_VIEW=new Vector();
	
	public Vector FormId=new Vector();
	public Vector appl_path = new Vector();//IN USE(APR 11)
	public Vector FormName=new Vector();
	public Vector FormType=new Vector();
	public Vector ModuleName = new Vector();
	public Vector modUrl = new Vector();//HA20190506
	public Vector modCd = new Vector();//HA20190506
	String alw_forward = ""; //HA20190518
	
	// new intoduced
	public Vector groupId=new Vector();
	public Vector groupName=new Vector();
	
	public Vector groupIdF=new Vector();
	public Vector groupNameF=new Vector();
	
	//Following (2) Vectors Has Been Introduced By Samik Shah On 28th January, 2011 ...
	public Vector FORM_CD = new Vector();
	public Vector FORM_NAME = new Vector();
	
	//Following (2) Vector Getter Methods Has Been Introduced By Samik Shah On 28th January, 2011 ...
	public Vector getFORM_CD() {return FORM_CD;}
	public Vector getFORM_NAME() {return FORM_NAME;}
	
	
	public void setOption(String opt)
	{
		
		this.option=opt;}
	public void setemp_nm(String empnm){this.emp_nm=empnm;}
	public void setModule(String mdl){this.module=mdl;}
	//sravan
	public Vector getALW_ADD() {
		return ALW_ADD;
	}
	
	public Vector getALW_DEL() {
		return ALW_DEL;
	}
	
	public Vector getALW_GRANT() {
		return ALW_GRANT;
	}
	
	public Vector getALW_PRINT() {
		return ALW_PRINT;
	}
	
	public Vector getALW_CHECK() {return ALW_CHECK;}// Defined By Samik Shah On 17th January, 2011 ...
	public Vector getALW_AUTHORIZE() {return ALW_AUTHORIZE;}// Defined By Samik Shah On 17th January, 2011 ...
	
	public Vector getALW_UPD() {
		return ALW_UPD;
	}
	
	public Vector getALW_VIEW() {
		return ALW_VIEW;
	}
	
	public Vector getFormId() {
		return FormId;
	}
	public Vector getApplicationPath(){
		return appl_path;
	}
	public Vector getFormName() {
		return FormName;
	}
	public Vector getFormType() {
		return FormType;
	}
	public Vector getModuleName() {
		return ModuleName;
	}
	
	
	public void fetchMenuAttr1()
	{
		try
		{
			query = "select distinct(d.MODULE_NAME), d.module_priority,d.def_path,d.module_cd "+ 
					"from DLNG_EMP_GROUP_DTL a, DLNG_GROUP_FORM b,DLNG_FORM_MST c, DLNG_MODULE_MST d, HR_EMP_MST e "+ 
					"where "+
					"d.MODULE_CD=c.MODULE_CD AND "+ 
					"c.FORM_CD=b.FORM_CD AND "+ 
					"b.GROUP_CD = a.GROUP_CD AND "+
					"a.EMP_CD=e.EMP_CD AND "+
					"(b.ALW_VIEW='Y' OR b.ALW_GRANT='Y') and "+
					"e.EMP_ABR='"+emp_nm+"' AND (sysdate between a.FROM_DT AND a.TO_DT) " +
					"order by d.module_priority, d.module_name";
			System.out.println("All Module: Menu_Bean.java : "+query);
			rset=stmt.executeQuery(query);
			while(rset.next())
			{
				ModuleName.add(rset.getString(1));
				modUrl.add(rset.getString(3)==null?"":rset.getString(3));
				modCd.add(rset.getString(4)==null?"":rset.getString(4));
			}
		//System.out.println(".........."+ModuleName);
		}
		catch(Exception e)
		{
			System.out.println("Exception Generated: "+e);
		}
	}
	
	String temp_form_cd="";
	public void fetchSubModule()
	{
		int cnt =1;
		
		query1 = "SELECT distinct(c.FORM_NAME), c.FORM_CD, c.CLASSPATH, c.DOC_TYPE, " +
		 		 "b.ALW_ADD, b.ALW_UPD, b.ALW_DEL, b.ALW_GRANT, b.ALW_PRINT, b.ALW_VIEW, " +
		 		 "c.GRP_CD, c.GRP_NM, c.GRP_SEQ_NO, b.ALW_CHECK, b.ALW_AUTHORIZE "+  
		 		 "FROM DLNG_EMP_GROUP_DTL a, DLNG_GROUP_FORM b, DLNG_FORM_MST c, DLNG_MODULE_MST d, HR_EMP_MST e "+ 
		 		 "WHERE d.MODULE_CD=c.MODULE_CD AND "+ 
		 		 "c.FORM_CD=b.FORM_CD AND "+ 
		 		 "b.GROUP_CD=a.GROUP_CD AND "+ 
		 		 "a.EMP_CD=e.EMP_CD AND "+ 
		 		 "d.MODULE_CD='"+module_cd+"' AND "+ 
		 		 "(b.ALW_VIEW='Y' OR b.ALW_GRANT='Y') AND " +
		 		 "e.EMP_ABR='"+emp_nm+"' AND (sysdate between a.FROM_DT AND a.TO_DT) " +
		 		 "ORDER BY c.DOC_TYPE, c.grp_cd, c.grp_seq_no";
		System.out.println("sub module : "+query1);
		try
		{
			rset=stmt.executeQuery(query1);
			while(rset.next())
			{
				if(!temp_form_cd.equals(rset.getString(2))) {
					
					FormName.add(rset.getString(1));
					FormId.add(rset.getString(2));
					appl_path.add(rset.getString(3));
					FormType.add(rset.getString(4));
					/*ALW_ADD.add(rset.getString(5));
					ALW_UPD.add(rset.getString(6));
					ALW_DEL.add(rset.getString(7));
					ALW_GRANT.add(rset.getString(8));
					ALW_PRINT.add(rset.getString(9));
					ALW_VIEW.add(rset.getString(10));*/
					groupId.add(rset.getString(11)==null?"-":rset.getString(11));
					groupName.add(rset.getString(12)==null?"-":rset.getString(12));
					/*ALW_CHECK.add(rset.getString(14));
					ALW_AUTHORIZE.add(rset.getString(15));*/
	//				System.out.println("in bean**"+ALW_AUTHORIZE);  
					if(cnt == 1)
				    {
						String query = "select distinct(c.GRP_CD), c.GRP_NM "+  
										"from DLNG_EMP_GROUP_DTL a, DLNG_GROUP_FORM b,DLNG_FORM_MST c, DLNG_MODULE_MST d, HR_EMP_MST e "+ 
										"where d.MODULE_CD=c.MODULE_CD AND "+ 
										"c.FORM_CD=b.FORM_CD AND "+ 
										"b.GROUP_CD = a.GROUP_CD AND "+ 
										"a.EMP_CD=e.EMP_CD AND "+ 
										"d.MODULE_CD='"+module_cd+"' AND "+ 
										"(b.ALW_VIEW='Y' OR b.ALW_GRANT='Y') AND " +
										"E.EMP_ABR='"+emp_nm+"' AND (sysdate between a.FROM_DT AND a.TO_DT) " +
										"ORDER BY C.GRP_CD";
						rset1=stmt1.executeQuery(query);
//						System.out.println("MENU-QRYSY0098 :SELECT :DLNG_EMP_GROUP_DTL,DLNG_GROUP_FORM,DLNG_FORM_MST,DLNG_MODULE_MST,HR_EMP_MST : Menu_Bean.java : "+querya);
						while(rset1.next())
						{
							if(rset1.getString(1) != null)
							{
								groupIdF.add(rset1.getString(1)==null?"-":rset1.getString(1));
								groupNameF.add(rset1.getString(2)==null?"-":rset1.getString(2));
							}
						}
				    }
				    cnt++;
				    
				    temp_form_cd = rset.getString(2);
				} 
			}
			
			String emp_cd="";
			Vector group_cd = new Vector();
			
			query="select emp_cd from hr_emp_mst where emp_abr='"+emp_nm+"' ";
//			System.out.println("query****"+query);
			rset  = stmt.executeQuery(query);
			if(rset.next()) {
				emp_cd=rset.getString(1)==null?"":rset.getString(1);
				
				query1="select group_cd from DLNG_EMP_GROUP_DTL where emp_cd='"+emp_cd+"' ";
//				System.out.println("query1****"+query1);
				rset1 = stmt1.executeQuery(query1);
				while(rset1.next()) {
					group_cd.add(rset1.getString(1)==null?"":rset1.getString(1));
				}
			}
			
			Vector Vadd_cnt=new Vector();
			Vector Vupd_cnt=new Vector();Vector Vdel_cnt=new Vector();Vector Vgrant_cnt=new Vector();
			Vector Vprint_cnt=new Vector();Vector Vview_cnt=new Vector();Vector Vauth_cnt=new Vector();
			int add_cnt=0;int upd_cnt=0;int del_cnt=0;int grant_cnt=0;int print_cnt=0;int view_cnt=0;int auth_cnt=0;int check_cnt=0;
			
//			System.out.println("FormId****"+FormId.size());
			for(int j = 0 ; j < FormId.size() ; j++) {
				add_cnt=0;upd_cnt=0;del_cnt=0;grant_cnt=0;print_cnt=0; view_cnt=0; auth_cnt=0;check_cnt=0;
				for(int i = 0 ; i < group_cd.size() ; i ++) {
					query = "select nvl(ALW_ADD,'N'),nvl(ALW_UPD,'N'),nvl(ALW_DEL,'N'),nvl(ALW_GRANT,'N'),nvl(ALW_PRINT,'N'),nvl(ALW_VIEW,'N'),"
							+ "nvl(ALW_CHECK,'N'),nvl(ALW_AUTHORIZE,'N')  from DLNG_GROUP_FORM where group_cd='"+group_cd.elementAt(i)+"'"
							+ " and FORM_CD='"+FormId.elementAt(j)+"'";
//					System.out.println("query****"+query);
					rset = stmt.executeQuery(query);
					if(rset.next()) {
						
						if(rset.getString(1).toString().equals("Y")) {
//							System.out.println("innnnnnnnnnnnnnnn");
							add_cnt++;
						}
						if(rset.getString(2).equals("Y")) {
							upd_cnt++;
						}
						if(rset.getString(3).equals("Y")) {
							del_cnt++;
						}
						if(rset.getString(4).equals("Y")) {
							grant_cnt++;
						}
						if(rset.getString(5).equals("Y")) {
							print_cnt++;
						}
						if(rset.getString(6).equals("Y")) {
							view_cnt++;
						}
//						System.out.println("rset.getString(2)****"+rset.getString(7));
						if(rset.getString(7).equals("Y")) {
							check_cnt++;
						}
						if(rset.getString(8).equals("Y")) {
							auth_cnt++;
						}
					}
					
				}
				
//				System.out.println("add_cnt***"+add_cnt);
				if(add_cnt>0) {
					ALW_ADD.add("Y");
				}else {
					ALW_ADD.add("N");
				}
				
				if(upd_cnt >0) {
					ALW_UPD.add("Y");
				}else {
					ALW_UPD.add("N");
				}
				if(del_cnt>0) {
					ALW_DEL.add("Y");
				}else {
					ALW_DEL.add("N");
				}
				
				if(grant_cnt>0) {
					ALW_GRANT.add("Y");
				}else {
					ALW_GRANT.add("N");
				}
				
				if(print_cnt>0) {
					ALW_PRINT.add("Y");
				}else {
					ALW_PRINT.add("N");
				}
				
				if(view_cnt>0) {
					ALW_VIEW.add("Y");
				}else {
					ALW_VIEW.add("N");
				}
				
				if(check_cnt>0) {
					ALW_CHECK.add("Y");
				}else {
					ALW_CHECK.add("N");
				}
				if(auth_cnt>0) {
					ALW_AUTHORIZE.add("Y");
				}else {
					ALW_AUTHORIZE.add("N");
				}
				
//				Vadd_cnt.add(add_cnt);
//				Vupd_cnt.add(upd_cnt);
//				Vdel_cnt.add(del_cnt);
//				Vgrant_cnt.add(grant_cnt);
//				Vprint_cnt.add(print_cnt);
//				Vview_cnt.add(view_cnt);
//				Vauth_cnt.add(auth_cnt);
			}
			
			/*System.out.println("ALW_ADD***"+ALW_ADD);
			System.out.println("ALW_UPD***"+ALW_UPD);
			System.out.println("ALW_DEL***"+ALW_DEL);
			System.out.println("ALW_GRANT***"+ALW_GRANT);
			System.out.println("ALW_PRINT***"+ALW_PRINT);
			System.out.println("ALW_VIEW***"+ALW_VIEW);
			System.out.println("ALW_CHECK***"+ALW_CHECK);
			System.out.println("ALW_AUTHORIZE***"+ALW_AUTHORIZE);*/
			
			
		}
		catch(SQLException e)
		{
			System.out.println("SUBMODULE Exception: "+e);
		}
	}
	
	
	public void fetchAllMenuNameAndID()
	{
		query1 = "SELECT DISTINCT(c.FORM_CD), c.FORM_NAME FROM DLNG_FORM_MST c ORDER BY c.FORM_NAME";
		System.out.println("Fetching Form CD & Name : Menu_Bean.java = "+query1);
		try
		{
			rset=stmt.executeQuery(query1);
			while(rset.next())
			{
				FORM_CD.add(rset.getString(1));
				FORM_NAME.add(rset.getString(2));
			}		   
			
			//HA20190518 to get access rights for Forward Action
			String mod_cd="";
			String query="select module_cd from DLNG_FORM_MST where classpath='../master/frm_show_my_actions.jsp' ";
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				mod_cd = rset.getString(1)==null?"":rset.getString(1);
			}
			query1 = "SELECT  b.ALW_AUTHORIZE "+  
			 		 "FROM DLNG_EMP_GROUP_DTL a, DLNG_GROUP_FORM b, DLNG_FORM_MST c, DLNG_MODULE_MST d, HR_EMP_MST e "+ 
			 		 "WHERE d.MODULE_CD=c.MODULE_CD AND "+ 
			 		 "c.FORM_CD=b.FORM_CD AND "+ 
			 		 "b.GROUP_CD=a.GROUP_CD AND "+ 
			 		 "a.EMP_CD=e.EMP_CD AND "+ 
			 		 "d.MODULE_CD='"+mod_cd+"' AND "+ 
			 		 "(b.ALW_VIEW='Y' OR b.ALW_GRANT='Y') AND " +
			 		 "e.EMP_ABR='"+emp_nm+"' AND (sysdate between a.FROM_DT AND a.TO_DT) " +
			 		 "ORDER BY c.DOC_TYPE, c.grp_cd, c.grp_seq_no";
			
			System.out.println("ALW_AUTHORIZE : "+query1);
			rset1=stmt.executeQuery(query1);
			if(rset1.next())
			{
				alw_forward = rset1.getString(1)==null?"":rset1.getString(1);
			}
		}
		
		catch(SQLException e)
		{
			System.out.println("Exception In fetchAllMenuNameAndID() Method of Menu_Bean Class:\n "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void clearVectors()
	{
		FormName.clear();
		FormId.clear();
		appl_path.clear();
		FormType.clear();
		groupId.clear();
		groupName.clear();
		groupIdF.clear();
		groupNameF.clear();
		//sra1-07/08/09
		 ALW_ADD.clear();
		 ALW_DEL.clear();
		 ALW_UPD.clear();
		 ALW_PRINT.clear();
		 ALW_CHECK.clear();
		 ALW_AUTHORIZE.clear();
		 ALW_GRANT.clear();
		 ALW_VIEW.clear();
		 //
	}
	
	public void init(){
		try{

			Context initContext = new InitialContext();
			 if(initContext == null ) 
			  throw new Exception("Boom - No Context");
			    Context envContext  = (Context)initContext.lookup("java:/comp/env");
			 //	SB20091221			DataSource ds = (DataSource)envContext.lookup(RuntimeConf.invoice_database);
				DataSource ds = (DataSource)envContext.lookup(RuntimeConf.security_database);
			if (ds != null) 
			  {
				conn = ds.getConnection();  
					
				if(conn != null) 
					{
						stmt = conn.createStatement();
						stmt1 = conn.createStatement();
						if(option.equalsIgnoreCase("MOD"))
						{
							fetchMenuAttr1();
							fetchAllMenuNameAndID();
							  
						}  
						if(option.equalsIgnoreCase("SUBMOD"))
						{
						    fetchSubModule();
						    
						}
				
						
						 if (rset != null)
						 { 
								try {rset.close(); 
							  }
							  catch (SQLException e)
							  { System.out.println("Exception in Menu_Bean  "+e);
							  }
							  rset = null;
					     }
						if (stmt != null)
						{ 
								try
								{stmt.close();
								} 
								catch (SQLException e) 
								{ System.out.println("Exception in Menu_Bean  "+e);
								}
							       stmt = null;
						}
						if (rset1 != null)
						 { 
								try {rset1.close(); 
							  }
							  catch (SQLException e)
							  { System.out.println("Exception in Menu_Bean  "+e);
							  }
							  rset1 = null;
					     }
						if (stmt1 != null)
						{ 
								try
								{stmt1.close();
								} 
								catch (SQLException e) 
								{ System.out.println("Exception in Menu_Bean  "+e);
								}
							       stmt1 = null;
						}
							conn.close();
							conn =null;
			}
		  }
		}
		
		catch(Exception e) 
		{
					//conn.close();
			  System.out.println(" Menu_Bean  \n Exception occured "+e);
		}
		finally
		{
		  if (rset != null) { 
		   try {rset.close(); } catch (SQLException e) { System.out.println("Exception in Menu_Bean  "+e);}
		   rset = null;
		  }
		  if (stmt != null) { 
		   try {stmt.close(); } catch (SQLException e) { System.out.println("Exception in Menu_Bean  "+e);}
		   stmt = null;
		  }
		  if (conn != null) { 
		   try {conn.close(); } catch (SQLException e) { System.out.println("Exception in Menu_Bean  "+e);}
		   conn = null;
		  }
		}
	}
	public Vector getGroupId() {
		return groupId;
	}
	public Vector getGroupName() {
		return groupName;
	}
	public Vector getGroupIdF() {
		return groupIdF;
	}
	public Vector getGroupNameF() {
		return groupNameF;
	}
	public Vector getModUrl() {
		return modUrl;
	}
	public Vector getModCd() {
		return modCd;
	}
	public String getModule_cd() {
		return module_cd;
	}
	public void setModule_cd(String module_cd) {
		this.module_cd = module_cd;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getSelForm_id() {
		return selForm_id;
	}
	public void setSelForm_id(String selForm_id) {
		this.selForm_id = selForm_id;
	}
	public String getHead_tab() {
		return head_tab;
	}
	public void setHead_tab(String head_tab) {
		this.head_tab = head_tab;
	}
	public String getAlw_forward() {
		return alw_forward;
	}
	public void setAlw_forward(String alw_forward) {
		this.alw_forward = alw_forward;
	}
}//End Of Class Menu_Bean ...
