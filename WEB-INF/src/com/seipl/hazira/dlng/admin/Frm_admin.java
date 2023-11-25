package com.seipl.hazira.dlng.admin;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.seipl.hazira.dlng.util.EncryptTest;
import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.escapeSingleQuotes;


//

@WebServlet("/servlet/Frm_admin")
public class Frm_admin extends HttpServlet
{
	public String servletName = "Frm_admin";
	public String methodName = "";
	
	
	
	public static Connection dbcon;
	public escapeSingleQuotes obj = new escapeSingleQuotes();
	public 
    int rcnt;
    public String url = "";
    private static String query = null;
    private static String query1 = null;
    private static Statement stmt = null;
    private static Statement stmt1 = null;
    private ResultSet rset = null;
    public String user;
    public String password;
    public String cf_password;
    public String new_password;
    public String username;
    public String password_name;
    int flag;
    int i=0;
    public String[] group_cd;
	public String[] group_name;	
	public String group_nameA;
    int cnt=0;
    String status="";
    String msg="";
    String error_msg="";
    
    public String write_permission = "N";
	public String delete_permission = "N";
	public String print_permission = "N";
	public String check_permission = "N";
	public String authorize_permission = "N";
	public String approve_permission = "N";
	public String audit_permission = "N";
    
	public String formId = "0";
	public String form_nm = "";
	String head_tab ="";
	String modCd = "";
	
	public  String  tablelistfile = ""; //NB20140718
	int f=0;
	int e=0;
	
    protected void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse) throws ServletException, IOException
    {
        try
        {
        	java.io.PrintWriter printwriter = httpservletresponse.getWriter();
            escapeSingleQuotes escapesinglequotes = new escapeSingleQuotes();
            String option = httpservletrequest.getParameter("option");           
            
			InitialContext initialcontext = new InitialContext();
            if(initialcontext == null)
            	throw new Exception("Boom - No Context");
            
            Context context = (Context)initialcontext.lookup("java:/comp/env");
            //SB20091221  DataSource datasource = (DataSource)context.lookup(RuntimeConf.invoice_database);
            DataSource datasource = (DataSource)context.lookup(RuntimeConf.security_database);
            
            if(datasource != null)
            {
                dbcon = datasource.getConnection();
                
                if(dbcon != null)
                {
                	dbcon.setAutoCommit(false);
                	stmt = dbcon.createStatement();
                	stmt1 = dbcon.createStatement();
                	
                	write_permission = httpservletrequest.getParameter("write_permission")==null?"N":httpservletrequest.getParameter("write_permission");
        			delete_permission = httpservletrequest.getParameter("delete_permission")==null?"N":httpservletrequest.getParameter("delete_permission");
        			print_permission = httpservletrequest.getParameter("print_permission")==null?"N":httpservletrequest.getParameter("print_permission");
        			check_permission = httpservletrequest.getParameter("check_permission")==null?"N":httpservletrequest.getParameter("check_permission");
        			authorize_permission = httpservletrequest.getParameter("authorize_permission")==null?"N":httpservletrequest.getParameter("authorize_permission");
        			approve_permission = httpservletrequest.getParameter("approve_permission")==null?"N":httpservletrequest.getParameter("approve_permission");
        			audit_permission = httpservletrequest.getParameter("audit_permission")==null?"N":httpservletrequest.getParameter("audit_permission");
                	
        			formId = httpservletrequest.getParameter("formId")==null?"0":httpservletrequest.getParameter("formId");
        			form_nm = httpservletrequest.getParameter("form_nm")==null?"":httpservletrequest.getParameter("form_nm");
        			
        			head_tab = httpservletrequest.getParameter("head_tab")==null?"":httpservletrequest.getParameter("head_tab");
        			modCd = httpservletrequest.getParameter("module_cd")==null?"":httpservletrequest.getParameter("module_cd");
//        			System.out.println("JAVA option: "+option);
        			if(option.equals("leave_assign"))//HS20151208
	           		{          
                    	leave_assgn(httpservletrequest,httpservletresponse);
	           		}
        			else if(option.equals("Add_User"))  //SS20110321
        			{ 
                    	//Add_User(httpservletrequest);  //SS20110321      
                		Add_User_New(httpservletrequest); 		//PS11042011
                    }
                	else if(option.equals("Server_Setting"))  //SS20110321
        			{                     	
                		Server_Setting(httpservletrequest,httpservletresponse); 		//PS11042011
                    }
                    else if(option.equals("Change_Password"))  //SS20101212
        			{ 
                    	Change_Password(httpservletrequest,httpservletresponse); //SS20101212
        			}
                    else if(option.equals("Change_Password2"))  //SS20110318
        			{ 
                    	Change_Password2(httpservletrequest,httpservletresponse); //SS20110318
        			}
                    else if(option.equals("Add_Group"))  //SB20091210
        			{ 
                    	Add_Group(httpservletrequest);   //SB20091210 
        			}
                    else if(option.equalsIgnoreCase("Add_Module"))  //SB20091210
                    {
                    	Add_Module(httpservletrequest);  //SB20091210
                    }
                    else if(option.equals("Add_Emp_Group_Allocation"))
                    {
                    	Add_Emp_Group_Allocation(httpservletrequest);
                    }
                    else if(option.equals("Delete_Emp_Group_Allocation"))
                    {
                    	Delete_Emp_Group_Allocation(httpservletrequest);
                    }
                    else if(option.equals("Delete_Schedule_Details"))
                    {
                    	Delete_Schedule_Details(httpservletrequest);
                    }
                    else if(option.equals("Delete_Ticket_Details"))
                    {
                    	Delete_Ticket_Details(httpservletrequest);
                    }
                    else if(option.equals("Delete_Daily_Nomination_Transporter"))
                    {
                    	Delete_Daily_Nomination_Transporter(httpservletrequest);
                    }
                    else if(option.equals("Delete_Daily_Nomination"))
                    {
                    	Delete_Daily_Nomination(httpservletrequest);
                    }
                    else if(option.equals("Delete_Periodic_Nomination"))  //SB20091210
                    {
                    	Delete_Periodic_Nomination(httpservletrequest);  //SB20091210
                    }
                    else if(option.equals("Enable_Disable_User_Status"))  //SB20091210
                    {
                    	Enable_Disable_User_Status(httpservletrequest);  //SB20091210
                    }
                    else if(option.equals("Add_Modify_Password_Life_Span"))  //SB20091210
                    {
                    	Add_Modify_Password_Life_Span(httpservletrequest);  //SB20091210
                    }
                    else if(option.equals("User_Release_Lock"))  //SB20091210
                    {
                    	User_Release_Lock(httpservletrequest);  //SB20091210
                    }
                    else if(option.equalsIgnoreCase("User_Reset_Password"))  //Introduced By Samik Shah On 17th September, 2010 ...
                    {
                    	User_Reset_Password(httpservletrequest);  //Introduced By Samik Shah On 17th September, 2010 ...
                    }
                    else if(option.equals("Modify_Sub_Menu_Dtl"))  //SB20091210
                    {
                    	Modify_Sub_Menu_Dtl(httpservletrequest);  //SB20091210
                    }
                    else if(option.equals("Insert_Sub_Menu_Dtl")) //SB20091210
                    {
                    	Insert_Sub_Menu_Dtl(httpservletrequest); //SB20091210
                    }
                    else if(option.equals("Insert_Access_Rights_Dtl"))  //SB20091210
                    {
                    	Insert_Access_Rights_Dtl(httpservletrequest);  //Modified By Samik Shah On 13th January, 2011 ...
                    }
                    else if(option.equals("Add_Activity_Reminder"))  //Priyanka 20110110
        			{ 
                    	Add_Activity_Reminder(httpservletrequest,httpservletresponse);    //Priyanka 20110110
        			}
                    else if(option.equals("Enable_Disable"))  
        			{ 
                    	     
                		Enable_Disable_User(httpservletrequest); 		//PS11042011
                    }
                    else if(option.equals("Release_Lock"))  
        			{ 
                    	     
                    	Release_Lock(httpservletrequest); 		//PS11042011
                    } 
                    else if(option.equals("Reset_Password"))  
        			{ 
                    	     
                    	Reset_Password(httpservletrequest); 		//PS12042011
                    } 
                    else if(option.equals("Data_BackUp"))  
        			{ 
//                    	dataBackUp(httpservletrequest,httpservletresponse); 		//NB20140718
                    }
                    else if(option.equals("submit_leave")) //pp20151012
        			{
        				submit_leave(httpservletrequest,httpservletresponse);
        			}  
                    else if(option.equals("submit_sickLeave")) //pp20151012
        			{
                    	submit_sickLeave(httpservletrequest,httpservletresponse);
        			} 
                    else if(option.equals("assignment1"))
	           		{          
                    	SaveDutyTime(httpservletrequest,httpservletresponse);
	           		}
                    else if(option.equals("assignment2"))
	           		{          
                    	SaveDutyAssignment(httpservletrequest,httpservletresponse);
	           		}
                    else if(option.equals("duty_flag")) //HS20151030
	           		{          
                    	SaveDuty_grp_Allocate(httpservletrequest,httpservletresponse); 
	           		}
                    else if(option.equals("mon_shift"))//HS20151115
	           		{          
                    	monshft_grp_Allocate(httpservletrequest,httpservletresponse);
	           		}
                    
                	if(rset != null) 
					{ 
						try
						{
							rset.close();
						}
						catch(SQLException e) 
						{
							//System.out.println("Exception in Frm_admin "+e);
						}
						rset = null;
					}
					if(stmt != null) 
					{ 
						try
						{
							stmt.close(); 
						}
						catch(SQLException e)
						{
							//System.out.println("Exception in Frm_admin "+e);
						}
						stmt = null;
					}
					if(stmt1 != null) 
					{ 
						try
						{
							stmt1.close(); 
						}
						catch(SQLException e)
						{
							//System.out.println("Exception in Frm_admin "+e);
						}
						stmt1= null;
					}
					//dbcon.close();
					//dbcon=null;
                }
            }
            else
            {
                //System.out.println("Data Source Not Found - Admin Module Error : Exception In Frm_admin");
            }
            
            httpservletresponse.sendRedirect(url);
        }
        catch(Exception exception)
        {
            //System.out.println("Exception In Frm_admin And Exception Is :: "+exception);
            exception.printStackTrace();
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
					//System.out.println("Exception in Frm_admin "+e);
				}
				rset = null;
			}
			if(stmt1 != null) 
			{ 
				try
				{
					stmt1.close(); 
				}
				catch(SQLException e)
				{
					//System.out.println("Exception in Frm_admin "+e);
				}
				stmt1= null;
			}
			if(stmt != null) 
			{	 
				try
				{
					stmt.close(); 
				}
				catch(SQLException e) 
				{
					//System.out.println("Exception in Frm_admin "+e);
				}
				stmt = null;
			}
			if(dbcon != null) 
			{ 
				try
				{
					dbcon.close(); 
				}
				catch(SQLException e)
				{
					//System.out.println("Exception in Frm_admin "+e);
				}
				dbcon = null;
			}
		}
    }//End Of doPost() Method ...
    
    
    
    private void leave_assgn(HttpServletRequest req,HttpServletResponse response) throws SQLException
	{
		System.out.println("here");
		try
		{
			String comp_cdd = req.getParameter("comp_cd")==null?"103":req.getParameter("comp_cd");
			String[] emp_cddd = req.getParameterValues("emp_cdd1");
			String[] eff_dt = req.getParameterValues("eff_dt");
			String[] el = req.getParameterValues("el");
			String[] el_cf = req.getParameterValues("el_cf");
			String[] el_co = req.getParameterValues("el_co");
			String[] el_co_effdt = req.getParameterValues("el_co_effdt");
			String[] sl = req.getParameterValues("sl");
			
//			System.out.println(comp_cdd+"--emp_cddd---"+emp_cddd.length);
//			System.out.println("JAVA--eff_dt---"+eff_dt.length);
//			System.out.println("JAVA--eff_dt[0]---"+el);
//			System.out.println("JAVA--el_cf[1]---"+el_cf);
//			System.out.println("JAVA--el_cf[2]---"+el_co);
//			System.out.println("JAVA--el_co_effdt[2]---"+el_co_effdt);
//			System.out.println("JAVA--sl[2]---"+sl);
			
			String query1="delete from la_leave_mst ";
			stmt1.executeUpdate(query1);
//			System.out.println("delete==== ");
			String query="";
			
			
//			System.out.println("emp_cddd.length "+emp_cddd.length);
			for(int i=0;i<emp_cddd.length;i++)
			{
//				String mon_sjhft_grp=req.getParameter("span-"+i) == null?"":req.getParameter("span-"+i);
				//System.out.println("el[i]:"+el[i]+":");
				if(!el[i].equalsIgnoreCase("null") && !el[i].equalsIgnoreCase(""))
				{
				 query="insert into la_leave_mst (comp_cd,emp_cd,EL,SL,EL_CF,EL_CO,EFF_DT,EL_CO_EFF_DT)"
					+ " values ('"+comp_cdd+"','"+emp_cddd[i]+"','"+el[i]+"','"+sl[i]+"','"+el_cf[i]+"'"
					+ ",'"+el_co[i]+"',to_date('"+eff_dt[i]+"','dd/mm/yyyy'),to_date('"+el_co_effdt[i]+"','dd/mm/yyyy'))";
//				 System.out.println("query-IN IF---"+query);
				 f++;
				}
				else
				{
					query="insert into la_leave_mst (comp_cd,emp_cd,EL,SL,EL_CF,EL_CO,EFF_DT,EL_CO_EFF_DT)"
							+ " values ('"+comp_cdd+"','"+emp_cddd[i]+"','0','0','0'"
									+ ",'0',to_date('00/00/0000','dd/mm/yyyy'),to_date('00/00/0000','dd/mm/yyyy'))";
//					System.out.println("query-IN ELSE---"+query);
					e++;
				}
				stmt1.executeUpdate(query);
				dbcon.commit();
			}
			
//			System.out.println("JAVA f=== "+f);
//			System.out.println("JAVA e=== "+e);
			status = "Successfully Submitted!!";
			url="../leave/frm_leave_alloc.jsp?message="+status;
		}
		catch (Exception e) 
		{
			dbcon.rollback();
			
		}
	}
    
  //HS 20150412--Grp allocation
    String grp="";
	Map leave_off_tot=new HashMap();
    public void monshft_grp_Allocate(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException,SQLException
	{
    	String month = req.getParameter("month")==null?"0":req.getParameter("month");
		String year = req.getParameter("year")==null?"0":req.getParameter("year");
		String branch_cd=req.getParameter("branch_cd")==null?"0":req.getParameter("branch_cd");
		String last_date=req.getParameter("last_date")==null?"0":req.getParameter("last_date");
		try
		{
			String size1 = req.getParameter("size1")==null?"0":req.getParameter("size1");
			String size2 = req.getParameter("size2")==null?"0":req.getParameter("size2");
			String fm_eff_dt = "01/"+month+"/"+year;
			
			query = "delete from hr_emp_duty_grp_dtl where fm_eff_dt = to_date('"+fm_eff_dt+"','dd/mm/yyyy') ";
//			System.out.println("..."+query);
			stmt.executeUpdate(query);
			
			for(int i=0;i<Integer.parseInt(size1);i++)
			{
				for(int j=0;j<Integer.parseInt(size2);j++)
				{
					String mon_sjhft_grp=req.getParameter("span-"+i+"-"+j) == null?"":req.getParameter("span-"+i+"-"+j);
					String employee_cd=req.getParameter("employee_cd-"+i+"-"+j) == null?"":req.getParameter("employee_cd-"+i+"-"+j);
					String grp_cd="",plus_grp_cd="";
					String to_eff_dt = (j+1)+"/"+month+"/"+year;
//					System.out.println(" mon_sjhft_grp--------"+ mon_sjhft_grp);
					if(mon_sjhft_grp.contains("-"))
					{
						String arr[] = mon_sjhft_grp.split("-");
						grp_cd = arr[0];
						plus_grp_cd = arr[1];
					}
					else
					{
						grp_cd = mon_sjhft_grp;
					}
					
					if(!grp_cd.equals(""))
					{
//						System.out.println("...herefdfvfsdfsdf1111");
						query = "insert into hr_emp_duty_grp_dtl(emp_cd,group_cd,plus_group_cd,fm_eff_dt,to_eff_dt,flag) "
								+ "values('"+employee_cd+"','"+grp_cd+"','"+plus_grp_cd+"',to_date('"+fm_eff_dt+"','dd/mm/yyyy'),to_date('"+to_eff_dt+"','dd/mm/yyyy'),'Y') ";
//						System.out.println("..."+query);
						stmt.executeUpdate(query);
					}
					
					
					
				}
			}
			
			dbcon.commit();
			status = "Successfully Submitted!!";
			url="../leave/frm_emp_time_assgn.jsp?message="+status+"&branch_cd="+branch_cd+"&month="+month+"&sysyear="+year;
		}
		catch(Exception e)
		{
			dbcon.rollback();
			status = "Successfully Not Submitted!!";
			url="../leave/frm_emp_time_assgn.jsp?message="+status+"&branch_cd="+branch_cd+"&month="+month+"&sysyear="+year;
			System.out.println("Exception in Frm_Admin  : doPost() Method ... "+e);
			e.printStackTrace();
		}
	}
    
    //HS 20150412--Duty allocation
     private void   SaveDuty_grp_Allocate(HttpServletRequest request,HttpServletResponse response) throws SQLException
    {
    	try
    	{
    		String grp_cd[]=request.getParameterValues("grp_cd");
    		String emp_cd[]=request.getParameterValues("emp_cd");
    		String branch_cd= request.getParameter("branch_cd") == null?"":request.getParameter("branch_cd");
    		String desg_cd=request.getParameter("desig_cd")==null?"0":request.getParameter("desig_cd");
    		
    	for(int i=0;i<emp_cd.length;i++)
    	{
    		if(grp_cd[i].equals("") || grp_cd[i].equals(null))
    		{
    			
    		}
    		else
    		{
    		String query1= "select to_char(EFF_DT,'dd/mm/yyyy'),BRANCH_CD FROM HR_EMP_REL_MST where emp_cd= '"+emp_cd[i]+"'";
    		System.out.println("query1"+query1);
        	rset=stmt.executeQuery(query1);
        	
        	if(rset.next())
        	{
	    	String query="insert into hr_emp_grp_dtl(grp_cd,eff_dt,emp_cd,branch_cd,remark,flag)"
	    			+ " values('"+grp_cd[i]+"',to_date('"+rset.getString(1)+"','dd/mm/yyyy'),'"+emp_cd[i]+"','"+rset.getString(2)+"','-','Y')";
//	    	System.out.println("sdklfalf"+query);
	    	stmt.executeUpdate(query);
        	}
        	else 
        	{
        		
        	}
    		}
    	}
    	
    	String msg="Data Submitted Successfully!!!!";
		url="../leave/frm_duty_grp_allocation.jsp?msg="+msg+"&branch_cd="+branch_cd+"&desg_cd="+desg_cd;
		
//		System.out.println(url);
		
    	dbcon.commit();
    	}
    	catch(Exception e)
    	{
    		dbcon.rollback();
    		e.printStackTrace();
    	}
    }
    
    public void dataBackUp(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{/*
		try
		{
			
			String logfile = DbManagerConstants.rootfilepath_LOG + DbManagerConstants.logfilename 
			+ DbManagerConstants.getCurrentDate(dbcon) + DbManagerConstants.fileExt;
			SimpleDateFormat gmtdateFormat = new SimpleDateFormat("HH:mm:ss");
			SimpleDateFormat istdateFormat = new SimpleDateFormat("HH:mm:ss");
			gmtdateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
			
			
			File file = new File(logfile);
			Writer output = new BufferedWriter(new FileWriter(file,true));
						
			String type=req.getParameter("type")==null?"":req.getParameter("type");
			String status = "";
			if(type.equals("BackUp"))
			{
				long start = System.currentTimeMillis();
				String startTime = istdateFormat.format(new Date(start));
				//output.write("\r\n");
				output.write("Back Up Process started at - ");
				output.write(startTime);
				output.write("\r\n");
								
				CreateTableListFile(req,output);
				
				DbBackUp obj = new DbBackUp();
				String[] tablenames = obj.getTableList(dbcon,output);
				obj.backUpTableDetails(tablenames,dbcon,output);
				status = "Back Up Process completed successfully.";
				
				long end = System.currentTimeMillis();
				String endTime = istdateFormat.format(new Date(end));
				
				long totaltimetaken = end - start;
					
				output.write("\r\n");
				output.write("Back Up Process completed at - " + endTime);
				output.write("\r\n");
				output.write("\r\n");
				output.write("Total time taken - " + gmtdateFormat.format(new Date(totaltimetaken)));
				output.write("\r\n");
				output.write("\r\n");
				output.write(status);
				output.close();
				
		//		RequestDispatcher rd=req.getRequestDispatcher("../DbManager/Welcome.jsp?message="+status);
		//		rd.forward(req,res);
				
				url="../DbManager/Welcome.jsp?message="+status;
			}
			else if(type.equals("Restore"))
			{
				output.write("\r\n");
				output.write("Operation selected - Db Restore.");
				output.write("\r\n");
				
				long start = System.currentTimeMillis();
				String startTime = istdateFormat.format(new Date(start));
				output.write("\r\n");
				output.write("Restoration Process started at - " + startTime);
				output.write("\r\n");
				
				DbRestore obj = new DbRestore();
				String message = obj.RestoreDbTables(dbcon,output);
				status = "Restoration completed successfully.";
				if(message != "")
				{
					status = status + message;
				}
				
				long end = System.currentTimeMillis();
				String endTime = istdateFormat.format(new Date(end));
				long totaltimetaken = end - start;
				output.write("\r\n");
				output.write("Restoration Process completed at - " + endTime);
				output.write("\r\n");
				output.write("\r\n");
				output.write("Total time taken - " + gmtdateFormat.format(new Date(totaltimetaken)));
				output.write("\r\n");
				output.write("\r\n");
				output.write(status);
				output.close();
				
			//	RequestDispatcher rd=req.getRequestDispatcher("../DbManager/Welcome.jsp?message="+status);
			//	rd.forward(req,res);
				url="../DbManager/Welcome.jsp?message="+status;
			}
			
		}
		catch(Exception e)
		{
			//System.out.println("Exception in Frm_Admin  : doPost() Method ... "+e);
			e.printStackTrace();
		}
	*/}
	
    public void submit_leaveold_20151026(HttpServletRequest request,HttpServletResponse res) throws SQLException
	{
		
		
		HttpSession sess = request.getSession();
		
		String user_cd = (String)sess.getAttribute("user_cd")==null?"":(String)sess.getAttribute("user_cd");
		String dates = request.getParameter("dates")==null?"0":request.getParameter("dates");
		String co = request.getParameter("co3")==null?"0":request.getParameter("co3");
		String cf = request.getParameter("cf3")==null?"0":request.getParameter("cf3");
		String el = request.getParameter("el3")==null?"0":request.getParameter("el3");
		try
		{
			String cur_yr="";
		
		
			String sysdtquery="select to_char(sysdate,'YYYY') from dual";
			rset=stmt.executeQuery(sysdtquery);
			if(rset.next())
			{
				cur_yr=rset.getString(1);
			}
			query=" delete from la_leave_dtl where emp_cd='"+user_cd+"' and to_char(from_dt,'yyyy')='"+cur_yr+"' and lower(type)='el'";	
			//System.out.println("query==="+query);
			stmt.executeUpdate(query);
			if(!dates.equalsIgnoreCase("")){
				
			String dates2[]=dates.split(",");
			
			Vector leave_date_sort1=new Vector();
			
			for(int i=0;i<dates2.length;i++)
			{
			String temp2[]= dates2[i].split("/");
			String temp1=temp2[2]+temp2[1]+temp2[0];
			
			leave_date_sort1.add(temp1);
			}
		
			java.util.List v = leave_date_sort1;
			java.util.Collections.sort(v);
			////System.out.println("v==="+v);
		Vector frm_dt=new Vector();
		Vector to_dt=new Vector();
		String frm_dt1="";
		String to_dt1="";
		if(v.size()>0){frm_dt.add(v.get(0).toString().substring(6,8)+"/"+v.get(0).toString().substring(4,6)+"/"+v.get(0).toString().substring(0,4));}
			for(int i=0;i<v.size()-1;i++)
			{
				frm_dt1=v.get(i).toString().substring(6,8)+"/"+v.get(i).toString().substring(4,6)+"/"+v.get(i).toString().substring(0,4);
				
				to_dt1=v.get(i+1).toString().substring(6,8)+"/"+v.get(i+1).toString().substring(4,6)+"/"+v.get(i+1).toString().substring(0,4);
				
				//System.out.println(frm_dt1);
				//System.out.println(to_dt1);
				int count=0;
				int count1=0;
				query="select to_date('"+to_dt1+"','dd/mm/yyyy')-to_date('"+frm_dt1+"','dd/mm/yyyy') from dual";
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					count=rset.getInt(1);
				}
				if(count==1){}else{
					query="select count(holiday_date) from birthday_holiday_mst where holiday_date BETWEEN to_date('"+frm_dt1+"','dd/mm/yyyy') and to_date('"+to_dt1+"','dd/mm/yyyy') order by holiday_date";
					//System.out.println("quer===="+query);
					rset = stmt.executeQuery(query);
					if(rset.next())
					{
						count1=rset.getInt(1);
					}
					//System.out.println("count=="+count);
					//System.out.println("count1=="+count1);
					if((count-1)==count1)
					{}else{
						to_dt.add(frm_dt1);
						frm_dt.add(to_dt1);
					
					}
				}
			}
			
			if(v.size()>0){to_dt.add(v.get(v.size()-1).toString().substring(6,8)+"/"+v.get(v.size()-1).toString().substring(4,6)+"/"+v.get(v.size()-1).toString().substring(0,4));}
			//System.out.println("frm_dt===="+frm_dt);
			//System.out.println("to_dt===="+to_dt);
			String comp_cd="";
			query="select comp_cd from hr_emp_mst where emp_cd='"+user_cd+"'";
			//System.out.println("quer===="+query);
			rset = stmt.executeQuery(query);
			if(rset.next())
			{
				comp_cd=rset.getString(1);
			}
			for(int i=0;i<frm_dt.size();i++)
			{
		//	query="insert into TIMS_LEAVE_DTL(emp_cd,leave_frm_dt,leave_to_dt,cf,co,el) values("+user_cd+",to_date('"+frm_dt.elementAt(i)+"','dd/mm/yyyy'),to_date('"+to_dt.elementAt(i)+"','dd/mm/yyyy'),"+cf+","+co+","+el+")";	
				query="select to_char(to_date('"+to_dt.elementAt(i)+"','dd/mm/yyyy')+1,'dd/mm/yyyy') from  dual";
				//System.out.println("quer===="+query);
				rset = stmt.executeQuery(query);
				String rpt_dt="";
				if(rset.next())
				{
					rpt_dt=rset.getString(1);
				}
				query="select to_char(to_date('"+frm_dt.elementAt(i)+"','dd/mm/yyyy')-7,'dd/mm/yyyy') from  dual";
				//System.out.println("quer===="+query);
				rset = stmt.executeQuery(query);
				String app_dt="";
				if(rset.next())
				{
					app_dt=rset.getString(1);
				}
				int total_day=0;
				int holiday_day=0;
				query="select to_date('"+to_dt.elementAt(i)+"','dd/mm/yyyy')-to_date('"+frm_dt.elementAt(i)+"','dd/mm/yyyy') from dual";
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					total_day=rset.getInt(1);
				}
				query="select count(holiday_date) from birthday_holiday_mst where holiday_date BETWEEN to_date('"+frm_dt.elementAt(i)+"','dd/mm/yyyy') and to_date('"+to_dt.elementAt(i)+"','dd/mm/yyyy') order by holiday_date";
				//System.out.println("quer===="+query);
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					holiday_day=rset.getInt(1);
				}
				int days=(total_day+1)-holiday_day;
			query="insert into la_leave_dtl(comp_cd,emp_cd,from_dt,fr_fg,to_dt,to_fg,appl_dt,report_dt,days,type,mail_flg) values("+comp_cd+",'"+user_cd+"',to_date('"+frm_dt.elementAt(i)+"','dd/mm/yyyy'),'F',to_date('"+to_dt.elementAt(i)+"','dd/mm/yyyy'),'A',to_date('"+app_dt+"','dd/mm/yyyy'),to_date('"+rpt_dt+"','dd/mm/yyyy'),"+days+",'EL','N')";
			//System.out.println("query==="+query);
			stmt.executeUpdate(query);
			}
			}
			String msg="submitted successfully...!!!";
			
			
			url="../leave/leave_plan.jsp?message="+msg+"&CO="+co+"&CF="+cf+"&EL="+el;
			dbcon.commit();
	}
	catch(Exception e)
	{
		String msg=" Not Submitted Successfully !!!";
		e.printStackTrace();
		
		url="../leave/leave_plan.jsp?message="+msg;			
	}
}
   
    public void submit_leave(HttpServletRequest request,HttpServletResponse res) throws SQLException
	{
		
		
		HttpSession sess = request.getSession();
		
		String user_cd = (String)sess.getAttribute("user_cd")==null?"":(String)sess.getAttribute("user_cd");
		String dates = request.getParameter("dates")==null?"0":request.getParameter("dates");
		String co = request.getParameter("co3")==null?"0":request.getParameter("co3");
		String cf = request.getParameter("cf3")==null?"0":request.getParameter("cf3");
		String el = request.getParameter("el3")==null?"0":request.getParameter("el3");
		String btn=request.getParameter("indication")==null?"":request.getParameter("indication");
		String leave_frm_dt=request.getParameter("leave_frm_dt")==null?"":request.getParameter("leave_frm_dt");
		String leave_to_dt=request.getParameter("leave_to_dt")==null?"":request.getParameter("leave_to_dt");
		String email_id=request.getParameter("email_id")==null?"":request.getParameter("email_id");
		String email=request.getParameter("email")==null?"":request.getParameter("email");
		//System.out.println("btn===="+btn);
		//System.out.println("leave_frm_dt------"+leave_frm_dt);
		String year = request.getParameter("sel_year")==null?"":request.getParameter("sel_year");
		String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
		String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
		String indication = request.getParameter("indication")==null?"":request.getParameter("indication");
//		System.out.println("indication in frm---"+cf);
		try
		{
			String cur_yr="";
		
		
			String sysdtquery="select to_char(sysdate,'YYYY') from dual";
			rset=stmt.executeQuery(sysdtquery);
			if(rset.next())
			{
				cur_yr=rset.getString(1);
			}
			String comp_cd="";
			query="select comp_cd from hr_emp_mst where emp_cd='"+user_cd+"'";
			//	//System.out.println("quer===="+query);
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					comp_cd=rset.getString(1);
				}
			if(btn.equalsIgnoreCase("cancel") && !btn.equalsIgnoreCase(""))
			{
				
				String cancel_date = request.getParameter("cancel_date")==null?"":request.getParameter("cancel_date");	
				//System.out.println("in frm  cancel_date==="+cancel_date);
				
				String cancel_date1[]=cancel_date.split(",");
				for(int i=0;i<cancel_date1.length;i++)
				{
					
					query="delete from la_leave_dtl  where emp_cd='"+user_cd+"' and  (select  to_date('"+cancel_date1[i]+"','dd/mm/yyyy') from dual)  BETWEEN from_dt and to_dt and lower(type)='el' and comp_cd='"+comp_cd+"'";	
					//System.out.println("query==="+query);
					stmt.executeUpdate(query);
					
				}
				
				
				
			}//cancel end
			else{
				dates=request.getParameter("add_date")==null?"":request.getParameter("add_date");
			if(!dates.equalsIgnoreCase("")){
				
			String dates2[]=dates.split(",");
			
			Vector leave_date_sort1=new Vector();
			
			for(int i=0;i<dates2.length;i++)
			{
			String temp2[]= dates2[i].split("/");
			String temp1=temp2[2]+temp2[1]+temp2[0];
			
			leave_date_sort1.add(temp1);
			}
		
			java.util.List v = leave_date_sort1;
			java.util.Collections.sort(v);
			////System.out.println("v==="+v);
		Vector frm_dt=new Vector();
		Vector to_dt=new Vector();
		String frm_dt1="";
		String to_dt1="";
		if(v.size()>0){frm_dt.add(v.get(0).toString().substring(6,8)+"/"+v.get(0).toString().substring(4,6)+"/"+v.get(0).toString().substring(0,4));}
			for(int i=0;i<v.size()-1;i++)
			{
				frm_dt1=v.get(i).toString().substring(6,8)+"/"+v.get(i).toString().substring(4,6)+"/"+v.get(i).toString().substring(0,4);
				
				to_dt1=v.get(i+1).toString().substring(6,8)+"/"+v.get(i+1).toString().substring(4,6)+"/"+v.get(i+1).toString().substring(0,4);
				
				//System.out.println(frm_dt1);
				//System.out.println(to_dt1);
				int count=0;
				int count1=0;
				query="select to_date('"+to_dt1+"','dd/mm/yyyy')-to_date('"+frm_dt1+"','dd/mm/yyyy') from dual";
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					count=rset.getInt(1);
				}
				
				if(count==1){}else{
					query="select count(holiday_date) from birthday_holiday_mst where holiday_date BETWEEN to_date('"+frm_dt1+"','dd/mm/yyyy') and to_date('"+to_dt1+"','dd/mm/yyyy') order by holiday_date";
//					System.out.println("quer===="+query);
					rset = stmt.executeQuery(query);
					if(rset.next())
					{
						count1=rset.getInt(1);
					}
					////System.out.println("count=="+count);
					////System.out.println("count1=="+count1);
					if((count-1)==count1)
					{}else{
						to_dt.add(frm_dt1);
						frm_dt.add(to_dt1);
					
					}
//					System.out.println("to_dt====into java"+count1);
//					System.out.println("frm_dt===into java"+frm_dt);
				}
			}
			
			if(v.size()>0){to_dt.add(v.get(v.size()-1).toString().substring(6,8)+"/"+v.get(v.size()-1).toString().substring(4,6)+"/"+v.get(v.size()-1).toString().substring(0,4));}
			////System.out.println("frm_dt===="+frm_dt);
			////System.out.println("to_dt===="+to_dt);
			
			
			for(int i=0;i<frm_dt.size();i++)
			{
		//	query="insert into TIMS_LEAVE_DTL(emp_cd,leave_frm_dt,leave_to_dt,cf,co,el) values("+user_cd+",to_date('"+frm_dt.elementAt(i)+"','dd/mm/yyyy'),to_date('"+to_dt.elementAt(i)+"','dd/mm/yyyy'),"+cf+","+co+","+el+")";	
				query="select to_char(to_date('"+to_dt.elementAt(i)+"','dd/mm/yyyy')+1,'dd/mm/yyyy') from  dual";
				//System.out.println("quer===="+query);
				rset = stmt.executeQuery(query);
				String rpt_dt="";
				if(rset.next())
				{
					rpt_dt=rset.getString(1);
				}
				//query="select to_char(to_date('"+frm_dt.elementAt(i)+"','dd/mm/yyyy')-7,'dd/mm/yyyy') from  dual";
				query="select to_char(sysdate,'dd/mm/yyyy') from dual";
				//System.out.println("quer===="+query);
				rset = stmt.executeQuery(query);
				String app_dt="";
				if(rset.next())
				{
					app_dt=rset.getString(1);
				}
				int total_day=0;
				int holiday_day=0;
				query="select to_date('"+to_dt.elementAt(i)+"','dd/mm/yyyy')-to_date('"+frm_dt.elementAt(i)+"','dd/mm/yyyy') from dual";
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					total_day=rset.getInt(1);
				}
				query="select count(holiday_date) from birthday_holiday_mst where holiday_date BETWEEN to_date('"+frm_dt.elementAt(i)+"','dd/mm/yyyy') and to_date('"+to_dt.elementAt(i)+"','dd/mm/yyyy') order by holiday_date";
				//System.out.println("quer===="+query);
				rset = stmt.executeQuery(query);
				if(rset.next())
				{
					holiday_day=rset.getInt(1);
				}
				int days=(total_day+1)-holiday_day;
			query="insert into la_leave_dtl(comp_cd,emp_cd,from_dt,fr_fg,to_dt,to_fg,appl_dt,report_dt,days,type,aprv_status,mail_flg) values("+comp_cd+",'"+user_cd+"',to_date('"+frm_dt.elementAt(i)+"','dd/mm/yyyy'),'F',to_date('"+to_dt.elementAt(i)+"','dd/mm/yyyy'),'A',to_date('"+app_dt+"','dd/mm/yyyy'),to_date('"+rpt_dt+"','dd/mm/yyyy'),"+days+",'EL','-','N')";
			//System.out.println("insertquery==="+query);
			stmt.executeUpdate(query);
			}
			}
			String msg="submitted successfully...!!!";
			
			}
			
			
			  
//			url="../leave/leave_plan.jsp?message="+msg+"&CO="+co+"&CF="+cf+"&EL="+el;
//			url="../mail/mail_leaveplan.jsp?email_id="+email_id+"&email="+email;
			url="../leave/leave_plan.jsp?leave_frm_dt="+leave_frm_dt+"&leave_to_dt="+leave_to_dt+"&email_id="+email_id+"&write_permission="+write_permission+"&approve_permission="+approve_permission+"&email="+email+"&year="+year+"&indication="+indication+"&mflag=Y";
			//System.out.println("===FRM url=== "+url);
			dbcon.commit();
			
	}
	catch(Exception e)
	{
		String msg=" Not Submitted Successfully !!!";
		e.printStackTrace();
		
		url="../leave/leave_plan.jsp?message="+msg;			
	}
}
   
    
    public void submit_sickLeave(HttpServletRequest request,HttpServletResponse res) throws SQLException
	{
		
		
		HttpSession sess = request.getSession();
		
		
		try
		{
			
			String comp_cd="";
			String user_cd = (String)sess.getAttribute("user_cd")==null?"":(String)sess.getAttribute("user_cd");
			
			String frm_dt = request.getParameter("frm_dt")==null?"":request.getParameter("frm_dt");	
			String frm_day = request.getParameter("frm_day")==null?"F":request.getParameter("frm_day");	
			String to_dt = request.getParameter("to_dt")==null?"":request.getParameter("to_dt");	
			String to_day = request.getParameter("to_day")==null?"A":request.getParameter("to_day");
			String appl_dt = request.getParameter("appl_dt")==null?"":request.getParameter("appl_dt");	
			String report_dt = request.getParameter("report_dt")==null?"":request.getParameter("report_dt");	
			String no_day = request.getParameter("no_day")==null?"0":request.getParameter("no_day");	
			String reason = request.getParameter("reason")==null?"":request.getParameter("reason");	
			String med_certi = request.getParameter("med_certi")==null?"N":request.getParameter("med_certi");	
			String add1 = request.getParameter("add1")==null?"":request.getParameter("add1");	
			String add2 = request.getParameter("add2")==null?"":request.getParameter("add2");	
			String remark = request.getParameter("remark")==null?"":request.getParameter("remark");	
			String phno = request.getParameter("phno")==null?"":request.getParameter("phno");
			String city = request.getParameter("city")==null?"":request.getParameter("city");
			String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
			String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
			////System.out.println("frm_dt"+frm_dt);
			
//			System.out.println("here 1");
//			System.out.println("med_certi==="+med_certi);
			query="select comp_cd from hr_emp_mst where emp_cd='"+user_cd+"'";
//			System.out.println("quer===="+query);
			rset = stmt.executeQuery(query);
			if(rset.next())
			{
				comp_cd=rset.getString(1);
			}
//			System.out.println("here 2");
			String query="insert into la_leave_dtl(comp_cd,emp_cd,from_dt,fr_fg,to_dt,to_fg,appl_dt," +
					"report_dt,days,type, reason,med_cert,addr1,addr2,city,phone,remark,aprv_status)" +
					" values("+comp_cd+",'"+user_cd+"',to_date('"+frm_dt+"','dd/mm/yyyy'),'"+frm_day+"'," +
					"to_date('"+to_dt+"','dd/mm/yyyy'),'"+to_day+"',to_date('"+appl_dt+"','dd/mm/yyyy')," +
					"to_date('"+report_dt+"','dd/mm/yyyy'),"+no_day+",'SL','"+reason+"','"+med_certi+"'," +
					"'"+add1+"','"+add2+"','"+city+"','"+phno+"','"+remark+"','Y')";
//			System.out.println("query--insert-----"+query);
			stmt1.executeUpdate(query);
		
		String msg="Sick Leave is submitted successfully...!!!";
			String sub="1";
		url="../leave/frm_sickLeave_mst.jsp?message="+msg+"&sub="+sub+"&write_permission="+write_permission;
		dbcon.commit();
}
catch(Exception e)
{
	String msg="Sick Leave is  Not Submitted Successfully !!!";
	e.printStackTrace();
	String sub="1";
	url="../leave/frm_sickLeave_mst.jsp?message="+msg+"&sub="+sub;			
}
}
	public void CreateTableListFile(HttpServletRequest req, Writer logoutput)
	{/*
		try
		{
			Writer output = null;
			
	//		//System.out.println("In CreateTableListFile()");
	//		//System.out.println();
			logoutput.write("\r\n");
			logoutput.write("In CreateTableListFile()");
			
			logoutput.write("\r\n");
			
			tablelistfile = DbManagerConstants.rootfilepath + DbManagerConstants.tablelistfile + DbManagerConstants.fileExt;
			
			
		    File file = new File(tablelistfile);
		    output = new BufferedWriter(new FileWriter(file));
		    String table[] = req.getParameterValues("table");
					
	//		//System.out.println("check values");
	//		//System.out.println("Write to file");
			
			logoutput.write("\r\n");
			logoutput.write("Writing list of tables to be backed up to file.");
			logoutput.write("\r\n");
			
			for(int j=0;j<table.length;j++)
			{
				logoutput.write("\r\n");
				logoutput.write("Writing table name - " + table[j] + " to file.");
				
	//			//System.out.println("value == "+table[j]);
				output.write(table[j]);
				if(j<table.length - 1)
				{
					output.write(",");
				}
			}
	//		//System.out.println();
	//		//System.out.println("Writing complete.");
	//		//System.out.println();
			
			logoutput.write("\r\n");
			logoutput.write("\r\n");
			logoutput.write("Writing table list complete.");
						
			output.close();  
		//	//System.out.println("End of CreateTableListFile()");
			
			logoutput.write("\r\n");
			logoutput.write("\r\n");
			logoutput.write("End of CreateTableListFile()");
		}
		catch(Exception e)
		{
			//System.out.println("Exception in WriteTableList : CreateTableListFile() Method ... "+e);
			e.printStackTrace();
		}
	*/}

    
    private void Server_Setting(HttpServletRequest request,HttpServletResponse httpservletresponse) throws IOException, ServletException ,SQLException
    {   
    	methodName = "Server_Setting()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	String write_permission = request.getParameter("write_permission")==null?"N":request.getParameter("write_permission");
    	String delete_permission = request.getParameter("delete_permission")==null?"N":request.getParameter("delete_permission");
    	String print_permission = request.getParameter("print_permission")==null?"N":request.getParameter("print_permission");
    	String approve_permission = request.getParameter("approve_permission")==null?"N":request.getParameter("approve_permission");
    	String audit_permission = request.getParameter("audit_permission")==null?"N":request.getParameter("audit_permission");
    	try
    	{
    		String server_ip = request.getParameter("server_ip");
	        String up_path = request.getParameter("up_path");
	        String domain = request.getParameter("domain");	       
			String log_id = request.getParameter("log_id");
			String log_pass = request.getParameter("log_pass");
			String down_path = request.getParameter("down_path");
			String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
			query = "select * from FMS7_FTP_SERVER_SETTING ";
			//System.out.println("FMS7_FTP_SERVER_SETTING Frm_admin.java : "+query);
		    rset = stmt.executeQuery(query);
		    if(rset.next())		    	
		    {
		    	query = "delete from FMS7_FTP_SERVER_SETTING";
		    	//System.out.println("Delete Frm FMS7_FTP_SERVER_SETTING : "+query);
		    	stmt.executeUpdate(query);		       
		    } 
		    query1 = "insert into FMS7_FTP_SERVER_SETTING(SERVER_IP,UPLOAD_PATH,DOWNLOAD_PATH,DOMAIN_NM,LOGIN_ID," +
		    		"LOGIN_PASSWORD,EMP_CD,ENT_DT) " +
		    		"values('"+server_ip+"','"+up_path+"','"+down_path+"','"+domain+"','"+log_id+"','"+log_pass+"'," +
		    				"'"+user_cd+"',sysdate) ";
	        //System.out.println("INSERT FMS7_FTP_SERVER_SETTING : "+query1);	        
	        stmt.executeUpdate(query1);
	        
		    dbcon.commit();
		    msg = "User successfully added !!!";
		    url = "../admin/frm_ftp_server_set.jsp?msg="+msg+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;		    
		    try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		msg = "Failed To Insert FTP Server Setting  ...";
    		url = "../admin/frm_ftp_server_set.jsp?msg="+msg+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println(exception);
    		exception.printStackTrace();
    	}
    }//End Of Method Server_Setting() ...
    
    
    private void Reset_Password(HttpServletRequest request) throws SQLException 
    {	    
    	methodName = "Reset_Password(()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	//Introduce By Milan Dalsaniya 20110921
    	String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
    	
    	try
    	{
    		escapeSingleQuotes obj = new escapeSingleQuotes();
                            	
            String emp_id = request.getParameter("emp_cd")==null?"0":request.getParameter("emp_cd");
            String emp_nm = request.getParameter("emp_nm")==null?" ":request.getParameter("emp_nm");
            String login_id = request.getParameter("login_id")==null?"N":request.getParameter("login_id");
                        
            StringBuffer stringbuffer = (new EncryptTest()).encrypt(login_id);
            String s2 = stringbuffer.toString();
            query = " UPDATE SEC_EMP_PASSWORDS SET password6=password5,password5=password4,password4=password3,password3=password2," +
    				" password2=password1,password1=password,password='"+s2+"',"+"reset_flag='Y' " +
    				" WHERE emp_cd='"+emp_id+"' ";
            //System.out.println("ADMIN-QRY0059 :UPDATE :SEC_EMP_PASSWORDS : Frm_admin.java : "+query);
            stmt.executeUpdate(query);
    		
    		dbcon.commit();
    		
    		status = "Passord Reset Done Successfully For The User : "+emp_nm+" !!!";
    		msg = status+" ("+remark+")";
    		url = "../admin/frm_admin_empAdd_new.jsp?status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		
    		try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		status = "Failed To Reset The Password For The Selected User !!!";
    		url = "../admin/frm_admin_empAdd_new.jsp?status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println("Exception in Frm_admin Servlet : Reset_Password() Method... "+exception);
    		exception.printStackTrace();
    	}  
    }//End Of Method Reset_Password() ...
    
    private void Release_Lock(HttpServletRequest request) throws SQLException 
    {	    
    	methodName = "Release_Lock()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	//Introduce By Milan Dalsaniya 20110921
    	String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
    	
    	try
    	{
    		escapeSingleQuotes obj = new escapeSingleQuotes();
                            	
            String emp_id = request.getParameter("emp_cd")==null?"0":request.getParameter("emp_cd");
            String emp_nm = request.getParameter("emp_nm")==null?"0":request.getParameter("emp_nm");
            
            query = " UPDATE SEC_EMP_PASSWORDS SET locked_flag ='' WHERE emp_cd='"+emp_id+"' ";
            //System.out.println("ADMIN-QRY0060 :UPDATE :SEC_EMP_PASSWORDS : Frm_admin.java : "+query);
            stmt.executeUpdate(query);
    		
    		dbcon.commit();
    		
    		status = "Lock Released Successfully For The User:"+emp_nm+"!!!";
    		msg = status+" ("+remark+")";
    		url = "../admin/frm_admin_empAdd_new.jsp?formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		
    		try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		status = "Failed To Release The Lock For The Selected User !!!";
    		url = "../admin/frm_admin_empAdd_new.jsp?formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println("Exception in Frm_admin Servlet : Release_Lock() Method... "+exception);
    		exception.printStackTrace();
    	}  
    }//End Of Method Release_Lock() ...
    
        
    private void Enable_Disable_User(HttpServletRequest request) throws SQLException 
    {	 
    	methodName = "Enable_Disable_User()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId") == null?"":request.getParameter("formId");
    	//String remark = request.getParameter("remark") == null?"":request.getParameter("remark");
        String empid = request.getParameter("emp_cd") == null?"":request.getParameter("emp_cd");
        String emp_nm = request.getParameter("emp_nm") == null?"":request.getParameter("emp_nm");
   	 	String update_empid = request.getParameter("update_empid") == null?"":request.getParameter("update_empid");
   	 	String u_status = request.getParameter("u_status") == null?"Enabled":request.getParameter("u_status");
   	 	String user_status = request.getParameter("user_status") == null?"N":request.getParameter("user_status");
   	 	String stat = "Disabled";
//   	Introduce By Milan Dalsaniya 20110921
   	 	String remark = request.getParameter("remark") == null?"":request.getParameter("remark");
   	 	
   	 	   	 
   	 	
   	 	if(u_status.equals("Disabled"))
		{
			stat = "Enabled";
		}
   	 	
   	 	try
    	{
   	 		escapeSingleQuotes obj = new escapeSingleQuotes();
         	//remark = obj.replaceSingleQuotes(remark);

    		query = " INSERT INTO FMS_LOGIN_PERM_STAT(emp_cd,update_by,status,update_dt) VALUES('"+empid+"','"+update_empid+"','"+user_status+"',sysdate) ";
    		//System.out.println("ADMIN-QRY0063 :INSERT :FMS_LOGIN_PERM_STAT : Frm_admin.java : "+query);
    		stmt.executeUpdate(query);
    	
    		query = " UPDATE HR_EMP_MST SET del_flag='"+user_status+"' WHERE emp_cd='"+empid+"' ";
    		//System.out.println("ADMIN-QRY0064 :UPDATE :HR_EMP_MST : Frm_admin.java : "+query);
    		////System.out.println("Samik writes query 2 = "+query);
    		stmt.executeUpdate(query);
    		    		  
    		dbcon.commit();
    		
    		status = "Successfully Changed The Status Of "+emp_nm+" From "+u_status+" To "+stat+" ...";
    		msg = status+" ("+remark+")";
    	    url = "../admin/frm_admin_empAdd_new.jsp?formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    	    
    	    try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	  }
   	 	  catch(Exception exception)
    	  {
   	 		status = "Failed To "+stat+" : "+emp_nm+" ...";
    		url = "../admin/frm_admin_empAdd_new.jsp?formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println("Exception in Frm_admin Servlet : Enable_Disable_User() Method... "+exception);
    		exception.printStackTrace();
    	  }  
    }//End Of Method Enable_Disable_User_Status() ...
    
    
    private void Add_User_New(HttpServletRequest request) throws IOException, ServletException ,SQLException
    {
    	methodName = "Add_User_New()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	//introduce By Milan Dalsaniya 20110921
    	String remark = request.getParameter("remark")==null?"":request.getParameter("remark");
    	String emp_cd = request.getParameter("emp_cd") == null?"":request.getParameter("emp_cd");
    	String s1 = "";
    	//sra1-10/08/09
    	String auditcheck = request.getParameter("auditcheck")==null?"":request.getParameter("auditcheck");
    	HttpSession tracksess=request.getSession();
    	tracksess.removeAttribute("auditcheck");
    	tracksess.setAttribute("auditcheck",auditcheck);
    	//
    	
    	try
    	{
    		String s2 = request.getParameter("emp_id");
	        String s3 = request.getParameter("emp_pass");
	        String s4 = request.getParameter("emp_name");
	       
			int emp_no = 1;
			HttpSession httpsession = request.getSession(true);
	        String s8 = (String)httpsession.getAttribute("ip");
	        String s9 = (String)httpsession.getAttribute("logindate");
	        String s10 = (String)httpsession.getAttribute("logintime");
			String email = request.getParameter("email");
			
			String group_cd = request.getParameter("group_cd");
	        String from_dt = request.getParameter("from_dt");
	        String to_dt = request.getParameter("to_dt");
	
		
	        query = "select EMP_ABR from HR_EMP_MST where EMP_CD='"+emp_cd+"' ";
	        //System.out.println("ADMIN-QRY0030 :SELECT :HR_EMP_MST : Frm_admin.java : "+query);
		    rset = stmt.executeQuery(query);
		    if(rset.next())
		    {
		    	query =" update HR_EMP_MST set EMP_ABR ='"+s2+"' where EMP_CD ='"+emp_cd+"' ";
		    	
		     	stmt.executeQuery(query);
		    	
		        StringBuffer stringbuffer = (new EncryptTest()).encrypt(s3);
		        
		        query1 = "insert into SEC_EMP_PASSWORDS(EMP_CD,PASSWORD,LOGIN_STATUS, LAST_CHANGE, RESET_FLAG) " +
	        		 "values('" + emp_cd + "','" + stringbuffer + "','" + s8 + "'||' on '||to_char(to_date('" + s9 + "','dd/mm/yy'),'Dy , Monthdd , yyyy ')||'" + s10 + "',sysdate,'Y') ";
				     //System.out.println("ADMIN-QRY0033 :INSERT :SEC_EMP_PASSWORDS : Frm_admin.java : "+query1);
				     stmt.executeUpdate(query1);
		        
		      	query = "insert into SEC_EMP_GROUP_DTL" +
	    			"(EMP_CD,GROUP_CD,FROM_DT,TO_DT) "+
			       	"values('"+emp_cd+"','"+group_cd+"'," +
			       	"TO_DATE('"+from_dt+"','dd/mm/yyyy')," +
			       	"TO_DATE('"+to_dt+"','dd/mm/yyyy'))";
			    	//System.out.println("Group Allocation Insert Query = "+query);
				    stmt.executeUpdate(query);
                s1 = "User successfully added !!!";
		    } 
		    else
		    {
		    	s1 = " User Record already exists !!!";
		    }
		    
		    dbcon.commit();
		    msg = s1+" ("+remark+")";
		    url = "../admin/frm_admin_empAdd_new.jsp?status="+s1+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		    
		    try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		s1 = "Failed To Insert User ...";
    		url = "../admin/frm_admin_empAdd_new.jsp?status="+s1+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println(exception);
    		exception.printStackTrace();
    	}
    }//End Of Method Add_new_user() ...
    
   
    private void Add_Module(HttpServletRequest request)	throws IOException, ServletException ,SQLException
    { 
    	methodName = "Add_Module()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
    	String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
    	try
    	{
    		
    		String[] module_cd=request.getParameterValues("modulecd");
    		String[] module_name=request.getParameterValues("modulename");
    		String[] module_priority=request.getParameterValues("priority");
    		String[] module_path=request.getParameterValues("modUrl");
    		
    		String new_module_cd[]=request.getParameterValues("newModCd");
    		String new_module_name[]=request.getParameterValues("newModName");
    		String[] new_priority=request.getParameterValues("newPriority");
    		String[] new_module_path=request.getParameterValues("newModPath");
    		
//    		System.out.println("new_module_cd***"+new_module_cd.length);
//    		System.out.println("new_module_name***"+new_module_name.length);
//    		System.out.println("new_priority***"+new_priority.length);
//    		System.out.println("new_module_path***"+new_module_path.length);
    		
    		if(module_cd != null)
    		{
    			for(int i=0; i < module_cd.length ; i++)
    			{
    				try
    				{
    					query=" update DLNG_MODULE_MST  set module_name = '"+module_name[i]+"' ,module_priority='"+module_priority[i]+"',DEF_PATH='"+module_path[i]+"' "+
				              " where MODULE_CD = '"+module_cd[i]+"'";
    					////System.out.println("ADMIN-QRY0036 :UPDATE :SEC_MODULE_MST : Frm_admin.java : "+query);
    					stmt.executeUpdate(query);
    				}
    				catch(Exception e)
    				{
    					error_msg="Failed To Add/Modify";
    					e.printStackTrace();
    					//System.out.println("In the Frm_MODULE_mst...."+e+" "+query);
    				}
    			}
    			msg="Module Name Updated Successfully ";
    		}
    		if(new_module_cd!=null)
    		{
    			int count=0;
		       	try
		       	{
		       		for(int i = 0 ; i < new_module_cd.length ; i++){
			       		query="Select max(MODULE_CD)+1 from DLNG_MODULE_MST";
//			       		System.out.println("ADMIN-QRY0037 :SELECT :SEC_MODULE_MST : Frm_admin.java : "+query);
			   	    	rset=stmt.executeQuery(query);
			   	    	if(rset.next())
			   	    	{
			   	    		count=rset.getInt(1);
			   	    	    if(rset.getInt(1)==0)
			   	    	    {
			   	    	    	count=1;
				 			}
			   	    	}
			   	    	query="Insert into DLNG_MODULE_MST (MODULE_CD,MODULE_NAME,MODULE_PRIORITY,DEF_PATH) values('"+count+"','"+new_module_name[i]+"','"+new_priority[i]+"','"+new_module_path[i]+"')";				 	   	    	  
//			   	    	System.out.println("ADMIN-QRY0038 :INSERT :SEC_MODULE_MST : Frm_admin.java : "+query);
			   	    	stmt.executeUpdate(query);
		                status="Module Name Added Successfully ";
			       	}
		       	}
		       	catch(Exception e)
		       	{
		       		error_msg="Failed To Add/Modify";
		       		e.printStackTrace();
		       		//System.out.println("In the Frm_group_mst...."+e+" "+query);
		       	}
    		}				         
    		dbcon.commit();
    		
    		  url = "../admin/frm_mst_administrator.jsp?msg="+msg+"&error_msg="+error_msg+
      				"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+
      				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
    		try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception e)
    	{
    		error_msg="Failed To Add/Modify";
    		//url="../admin/frm_admin_addGroup.jsp?status="+status+"&idlog="+group_cd+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		url = "../admin/frm_mst_administrator.jsp?status="+status+"&error_msg="+error_msg+
      				"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+
      				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
    		e.printStackTrace();
    		//System.out.println("In the Frm_admin...."+e+" "+query);
    	}    	
    }
    
    
    private void Add_User(HttpServletRequest request) throws IOException, ServletException ,SQLException
    {   
    	methodName = "Add_User()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	String s1 = "";
    	//sra1-10/08/09
    	String auditcheck = request.getParameter("auditcheck")==null?"":request.getParameter("auditcheck");
    	HttpSession tracksess=request.getSession();
    	tracksess.removeAttribute("auditcheck");
    	tracksess.setAttribute("auditcheck",auditcheck);
    	//
    	
    	try
    	{
    		String s2 = request.getParameter("emp_id");
	        String s3 = request.getParameter("emp_pass");
	        String s4 = request.getParameter("emp_name");
	       
			int emp_no = 1;
			HttpSession httpsession = request.getSession(true);
	        String s8 = (String)httpsession.getAttribute("ip");
	        String s9 = (String)httpsession.getAttribute("logindate");
	        String s10 = (String)httpsession.getAttribute("logintime");
			String email = request.getParameter("email");
	
			query = "select count(*) from HR_EMP_MST where EMP_ABR='"+s2+"'";
			//System.out.println("ADMIN-QRY0030 :SELECT :HR_EMP_MST : Frm_admin.java : "+query);
		    rset = stmt.executeQuery(query);
		    if(rset.next())
		    	rcnt = Integer.parseInt(rset.getString(1));
		    if(rcnt == 0)
		    {
		    	query = "select max(EMP_CD)+1 from HR_EMP_MST";
		    	//System.out.println("ADMIN-QRY0031 :SELECT :HR_EMP_MST : Frm_admin.java : "+query);
		        rset = stmt.executeQuery(query);
		        if(rset.next())
		        	emp_no = Integer.parseInt(rset.getString(1));
		    	
		        query = "insert into HR_EMP_MST(EMP_CD, EMP_ABR, EMP_NM, EMAIL_ID,JOIN_DT) values('" + emp_no + "','" + s2 + "','" + s4 + "','" + email + "',sysdate)";
		        //System.out.println("ADMIN-QRY0032 :INSERT :HR_EMP_MST : Frm_admin.java : "+query);
		        StringBuffer stringbuffer = (new EncryptTest()).encrypt(s3);
		        
		        query1 = "insert into SEC_EMP_PASSWORDS(EMP_CD,PASSWORD,LOGIN_STATUS, LAST_CHANGE, RESET_FLAG) values('" + emp_no + "','" + stringbuffer + "','" + s8 + "'||' on '||to_char(to_date('" + s9 + "','dd/mm/yy'),'Dy , Monthdd , yyyy ')||'" + s10 + "',sysdate,'Y') ";
		        //System.out.println("ADMIN-QRY0033 :INSERT :SEC_EMP_PASSWORDS : Frm_admin.java : "+query1);
		        stmt.executeUpdate(query);
		        stmt.executeUpdate(query1);
		        
                s1 = "User successfully added !!!";
		    } 
		    else
		    {
		    	s1 = "User Record already exists !!!";
		    }
		    
		    dbcon.commit();
		    msg = s1;
		    url = "../admin/frm_admin_empAdd.jsp?status="+s1+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		    
		    try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		s1 = "Failed To Insert User ...";
    		url = "../admin/frm_admin_empAdd.jsp?status="+s1+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println(exception);
    		exception.printStackTrace();
    	}
    }//End Of Method Add_user() ...
    
    
    private void Change_Password(HttpServletRequest request,HttpServletResponse httpservletresponse) throws IOException, ServletException ,SQLException
    {   
    	methodName = "Change_Password()";
//    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
	    String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
	    String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
	    
    	HttpSession session = request.getSession();
    	try
    	{
    		user = request.getParameter("user_id");
		    password = request.getParameter("passwd");
		    StringBuffer stringbuffer = (new EncryptTest()).encrypt(password);
		    String s5 = stringbuffer.toString();
		    cf_password = request.getParameter("cf_passwd");
		    new_password = request.getParameter("new_passwd");
		    StringBuffer stringbuffer1 = (new EncryptTest()).encrypt(new_password);
		    query = "select EMP_CD from HR_EMP_MST where EMP_ABR = '"+user+"'";
		    //System.out.println("ADMIN-QRY0039 :SELECT :HR_EMP_MST : Frm_admin.java : "+query);
		    rset = stmt.executeQuery(query);
		    if(rset.next())
		    {
		    	user = rset.getString(1);
		    }
		    
		    query = "select EMP_CD, PASSWORD from SEC_EMP_PASSWORDS  where EMP_CD='"+user+"' and PASSWORD='"+stringbuffer+"'";
		    //System.out.println("ADMIN-QRY0040 :SELECT :SEC_EMP_PASSWORDS : Frm_admin.java : "+query);
		   
		    rset = stmt.executeQuery(query);
		    if(rset.next())
		    {
		        username = rset.getString(1);
		        password_name = rset.getString(2);
		    }
		    else
		    {
		        username = "";
		        password_name = "";
		    }
		    rset.close();
		    
		    if(username.equals(user) && password_name.equals(s5))
		    {
				int no_password = 0;
				query = "select no_password from fms_password_life where eff_dt in (select max(eff_dt) from fms_password_life where eff_dt<=sysdate)";
				//System.out.println("ADMIN-QRY0041 :SELECT :FMS_PASSWORD_LIFE : Frm_admin.java : "+query);
				rset = stmt.executeQuery(query);
				if(rset.next())
		           no_password = Integer.parseInt(rset.getString(1));
				
				no_password++;
		
		        query = "select count(*) from SEC_EMP_PASSWORDS where EMP_CD='"+user+"' and (PASSWORD='"+stringbuffer1+"' or  PASSWORD1='"+stringbuffer1+"'";
		        //System.out.println("ADMIN-QRY0042 :SELECT :SEC_EMP_PASSWORDS : Frm_admin.java : "+query);
		        for (int i=2; i<no_password ;i++)
				{
					query = query+" or PASSWORD"+i+"='"+stringbuffer1+"'";
				}
				query = query + ")";
		        ////System.out.println("Change Password: -"+query);
				rset = stmt.executeQuery(query);
				
		        if(rset.next())
		        	i = Integer.parseInt(rset.getString(1));
		        if(i < 1)
		        {
		        	int password_life_in_days = 30;
		        	
		        	String queryString = "select no_days from fms_password_life where eff_dt=(select max(eff_dt) from fms_password_life where eff_dt<=sysdate)"; 
		           	//System.out.println("Fetching No of Days for Password Life from FMS_PASSWORD_LIFE Table = "+queryString);
		           	rset = stmt.executeQuery(queryString);
		            if(rset.next())
		            {
		            	password_life_in_days = Integer.parseInt(rset.getString(1));						
		            }
		            		        	
		        	query = "update SEC_EMP_PASSWORDS set PASSWORD10=PASSWORD9,PASSWORD9=PASSWORD8,PASSWORD8=PASSWORD7,PASSWORD7=PASSWORD6,PASSWORD6=PASSWORD5,PASSWORD5=PASSWORD4,PASSWORD4=PASSWORD3,PASSWORD3=PASSWORD2,PASSWORD2=PASSWORD1,PASSWORD1=PASSWORD,PASSWORD='"+stringbuffer1+"',"+"LAST_CHANGE=sysdate,RESET_FLAG=null,EXPIRY_DATE=sysdate+"+password_life_in_days+" where EMP_CD='"+user+"' and "+"PASSWORD='"+stringbuffer+"'";
		            //System.out.println("ADMIN-QRY0043 :UPDATE :SEC_EMP_PASSWORDS : Frm_admin.java : "+query);
		            stmt.executeUpdate(query);
		            String s2 = "Your Password Has Been Changed Successfully !!!";
		            msg = s2;
		            url ="../admin/frm_mst_administrator.jsp?msg=" + s2 + "&idlog=" + user + "&formId="+formId+"&status="+s2+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
		            System.out.println("msg*********"+msg);
		            try
					{
						new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
					}
					catch(Exception infoLogger)
					{
						//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
						infoLogger.printStackTrace();
					}
		        }
		        else
		        {
		            no_password--;
					error_msg = "You Can Not Repeat Last "+no_password+" Passwords !!!";
					url ="../admin/frm_mst_administrator.jsp?error_msg=" + error_msg + "&idlog=" + user + "&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
		        }
		    }
		    else
		    {
		    	error_msg = "Please Enter the correct Password !!!";
		    	url ="../admin/frm_mst_administrator.jsp?error_msg=" + error_msg + "&idlog=" + user + "&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
		    }
		    dbcon.commit();
		    stringbuffer.append(" ");
		    stringbuffer1.append(" ");   
    	}
    	catch(Exception exception)
    	{
    		error_msg = "Failed To Changed The Password !!!";
    		url="../login.jsp?loginerror=yes&errortype=characters in UserName"+"&formId="+formId+"&error_msg="+error_msg+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&modCd="+modCd+"&formId="+formId+"&subModUrl="+subModUrl;
    		//System.out.println(exception);
    		exception.printStackTrace();
    	}
    }//End Of Method Change_Password() ...
    
    
    private void Change_Password2(HttpServletRequest request,HttpServletResponse httpservletresponse) throws IOException, ServletException ,SQLException
    {   
    	methodName = "Change_Password2()";
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	HttpSession session = request.getSession();
    	try
    	{
//    		user = request.getParameter("user_id");
		    password = request.getParameter("passwd");
		    System.out.println("password*******"+password);
		    StringBuffer stringbuffer = (new EncryptTest()).encrypt(password);
		    String s5 = stringbuffer.toString();
		    cf_password = request.getParameter("cf_passwd");
		    new_password = request.getParameter("new_passwd");
		    StringBuffer stringbuffer1 = (new EncryptTest()).encrypt(new_password);
		    String user_id = request.getParameter("user_id");
		    
		    query = "select EMP_CD from HR_EMP_MST where EMP_ABR = '"+user_id+"'";
		    //System.out.println("ADMIN-QRY0039 :SELECT :HR_EMP_MST : Frm_admin.java : "+query);
		    rset = stmt.executeQuery(query);
		    if(rset.next())
		    {
		    	user = rset.getString(1);
		    }
		    
		    query = "select EMP_CD, PASSWORD from SEC_EMP_PASSWORDS  where EMP_CD='"+user+"' and PASSWORD='"+stringbuffer+"'";
//		    System.out.println("ADMIN-QRY0040 :SELECT :SEC_EMP_PASSWORDS : Frm_admin.java : "+query);
		   
		    rset = stmt.executeQuery(query);
		    if(rset.next())
		    {
		        username = rset.getString(1);
		        password_name = rset.getString(2);
		    }
		    else
		    {
		        username = "";
		        password_name = "";
		    }
		    rset.close();
		    System.out.println("username***"+username+"**user**"+user);
		    System.out.println("password_name***"+password_name+"**s5**"+s5);
		    
		    if(username.equals(user) && password_name.equals(s5))
//		    if(username.equals(user)) //Hiren_20210125 there is no default password as Username
		    {
				int no_password = 0;
				query = "select no_password from fms_password_life where eff_dt in (select max(eff_dt) from fms_password_life where eff_dt<=sysdate)";
//				System.out.println("ADMIN-QRY0041 :SELECT :FMS_PASSWORD_LIFE : Frm_admin.java : "+query);
				rset = stmt.executeQuery(query);
				if(rset.next())
		           no_password = Integer.parseInt(rset.getString(1));
				
				no_password++;
		
		        query = "select count(*) from SEC_EMP_PASSWORDS where EMP_CD='"+user+"' and (PASSWORD='"+stringbuffer1+"' or  PASSWORD1='"+stringbuffer1+"'";
//		        System.out.println("ADMIN-QRY0042 :SELECT :SEC_EMP_PASSWORDS : Frm_admin.java : "+query);
		        for (int i=2; i<no_password ;i++)
				{
					query = query+" or PASSWORD"+i+"='"+stringbuffer1+"'";
				}
		        
				query = query + ")";
		        System.out.println("Change Password: -"+query);
				rset = stmt.executeQuery(query);
				
		        if(rset.next())
		        	i = Integer.parseInt(rset.getString(1));
		        if(i < 1)
		        {
		        	int password_life_in_days = 30;
		        	
		        	String queryString = "select no_days from fms_password_life where eff_dt=(select max(eff_dt) from fms_password_life where eff_dt<=sysdate)"; 
		           	//System.out.println("Fetching No of Days for Password Life from FMS_PASSWORD_LIFE Table = "+queryString);
		           	rset = stmt.executeQuery(queryString);
		            if(rset.next())
		            {
		            	password_life_in_days = Integer.parseInt(rset.getString(1));						
		            }
		            
		        	query = " update SEC_EMP_PASSWORDS set PASSWORD10=PASSWORD9,PASSWORD9=PASSWORD8,PASSWORD8=PASSWORD7,PASSWORD7=PASSWORD6,PASSWORD6=PASSWORD5,PASSWORD5=PASSWORD4,PASSWORD4=PASSWORD3,PASSWORD3=PASSWORD2,PASSWORD2=PASSWORD1,PASSWORD1=PASSWORD,PASSWORD='"+stringbuffer1+"',"+"LAST_CHANGE=sysdate,RESET_FLAG=null,EXPIRY_DATE=sysdate+"+password_life_in_days+" where EMP_CD='"+user+"' and "+"PASSWORD='"+stringbuffer+"'";
		            //System.out.println("ADMIN-QRY0043 :UPDATE :SEC_EMP_PASSWORDS : Frm_admin.java : "+query);
		            stmt.executeUpdate(query);
		            String s2 = "Password Has Been Changed Successfully For User ID: "+user_id+", Now Login with your new Password !!!";
		            msg = s2;
		            //url ="../home/index1.jsp?sessmsg="+s2;
		            url ="../home/frm_admin_pass.jsp?status=" + s2 + "&idlog=" + user + "&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		            
		            try
					{
						new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
					}
					catch(Exception infoLogger)
					{
						//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
						infoLogger.printStackTrace();
					}
		        }
		        else
		        {
		            no_password--;
					String s3 = "You Can Not Repeat Last "+no_password+" Passwords !!!";
					url ="../home/frm_admin_pass.jsp?status=" + s3 + "&idlog=" + user + "&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		        }
		    }
		    else
		    {
		    	String s4 = "Please Enter the correct Password !!!";
		    	url ="../home/frm_admin_pass.jsp?status=" + s4 + "&idlog=" + user + "&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
		    }
		    dbcon.commit();
		    stringbuffer.append(" ");
		    stringbuffer1.append(" ");   
    	}
    	catch(Exception exception)
    	{
    		status = "Failed To Changed The Password !!!";
    		url ="../home/frm_admin_pass.jsp?status=" + status + "&idlog=" + user + "&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println(exception);
    		exception.printStackTrace();
    	}
    }//End Of Method Change_Password2() ...
    
    
    private void Add_Group(HttpServletRequest request)	throws IOException, ServletException ,SQLException
    { 
    	methodName = "Add_Group()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
    	String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
    	//sra1-10/08/09
//    	String track=request.getParameter("track");
    	String option=request.getParameter("option")==null?"":request.getParameter("option");
    	String flag=request.getParameter("flag")==null?"":request.getParameter("flag");
    	String head_tab=request.getParameter("head_tab")==null?"":request.getParameter("head_tab");
    	String module_cd=request.getParameter("module_cd")==null?"":request.getParameter("module_cd");
    	
    	//System.out.println(track);
    	//System.out.println("#####"+option);
    	//System.out.println("#####"+formId);
    	//System.out.println(flag);
    	
    	HttpSession tracksess=request.getSession();
    	//tracksess.removeAttribute("track");
//    	tracksess.setAttribute("track",track);    	
        	
    	try
    	{
    		group_cd=request.getParameterValues("groupcd");
    		group_name=request.getParameterValues("groupname");
    		
    		String new_group_nm[]=request.getParameterValues("newGroupName");
    		String new_group_cd[]=request.getParameterValues("newGroupCd");
    		
    		group_nameA=request.getParameter("groupnameA");
    		
    		if(group_cd !=null)
    		{
    			for(int i=0; i < group_cd.length ; i++)
    			{
    				try
    				{
    				
    					query=" update DLNG_ACCESS_GROUP_MST set group_name = '"+group_name[i]+"'  "+
				              " where GROUP_CD = '"+group_cd[i]+"'";
    					//System.out.println("ADMIN-QRY0027 :UPDATE :SEC_ACCESS_GROUP_MST : Frm_admin.java : "+query);
    					stmt.executeUpdate(query);
    					msg="User Group Name Updated Successfully ";
    				}
    				catch(Exception e)
    				{
    					e.printStackTrace();
    					error_msg="Failed User Group Name Updation";
    					//System.out.println("In the Frm_group_mst...."+e+" "+query);
    				}
    			}
    			
    		}
    		if(new_group_cd!=null)
    		{
    			int count=0;
		       	try
		       	{
		       		for(int i=0 ; i < new_group_cd.length ; i++){
			       		query="Select max(GROUP_CD)+1 from DLNG_ACCESS_GROUP_MST";
			       		//System.out.println("ADMIN-QRY0028 :SELECT :SEC_ACCESS_GROUP_MST : Frm_admin.java : "+query);
			       		rset=stmt.executeQuery(query);
			   	    	if(rset.next())
			   	    	{
			   	    		count=rset.getInt(1);
			   	    	    if(rset.getInt(1)==0)
			   	    	    {
			   	    	    	count=1;
				 			}
			   	    	   
			   	    	}
			   	    	
			   	    	query="Insert into DLNG_ACCESS_GROUP_MST (GROUP_CD,group_name) values('"+count+"','"+new_group_nm[i]+"')";
			   	    	//System.out.println("ADMIN-QRY0029 :INSERT :SEC_ACCESS_GROUP_MST : Frm_admin.java : "+query);
		                stmt.executeUpdate(query);
		                msg=" User Group Name Added Successfully ";
			       	}
		       	}	
		       	catch(Exception e)
		       	{
		       		e.printStackTrace();
		       		error_msg="Failed New User Group Name Insertion";
		       	}
    		}				         
    		
    		dbcon.commit();
    		
//    		url="../admin/frm_admin_addGroup.jsp?status="+status+"&idlog="+group_cd+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		url="../admin/frm_mst_administrator.jsp?msg="+msg+"&error_msg="+error_msg+"&formId="+formId+
    				"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+
    				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&alw_view="+check_permission+
    				"&alw_upd="+authorize_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
    		try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
				url="../admin/frm_mst_administrator.jsp?msg="+msg+"&error_msg="+error_msg+"&formId="+formId+
	    				"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+
	    				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&alw_view="+check_permission+
	    				"&alw_upd="+authorize_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
			}
    	}
    	catch(Exception e)
    	{
    		error_msg="Failed To Add/Modify";
//    		url="../admin/frm_admin_addGroup.jsp?status="+status+"&idlog="+group_cd+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		e.printStackTrace();
    		url="../admin/frm_mst_administrator.jsp?msg="+msg+"&error_msg="+error_msg+"&formId="+formId+
    				"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+
    				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&alw_view="+check_permission+
    				"&alw_upd="+authorize_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
    	}
    }//End Of Method Add_Group() ...
    
    
    private void Add_Emp_Group_Allocation(HttpServletRequest request) throws IOException, ServletException ,SQLException
    {  
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
    	String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
    	
    	methodName = "Add_Emp_Group_Allocation()";
    	HttpSession session = request.getSession();
    	String emp_cd = request.getParameter("emp_cd");
        String group_cd = request.getParameter("group_cd");
        String from_dt = request.getParameter("from_dt");
        String to_dt = request.getParameter("to_dt");
        String from_date = request.getParameter("from_date")==null?"":request.getParameter("from_date");
        String emp_name = request.getParameter("emp_name");
        String group_name = request.getParameter("group_name");
        String audittrack = emp_name+" added to "+group_name+" group successfully !!!";
        HttpSession groupalloc_sess=request.getSession();
        groupalloc_sess.setAttribute("audittrack",audittrack);
        
        /*HttpSession sess=request.getSession();
    	String ALW_ADD1=(String)sess.getAttribute("ALW_ADD1");
    	String ALW_DEL1=(String)sess.getAttribute("ALW_DEL1");
    	String ALW_UDP1=(String)sess.getAttribute("ALW_UDP1");
    	String ALW_VIEW1=(String)sess.getAttribute("ALW_VIEW1");
    	String ALW_PRINT1=(String)sess.getAttribute("ALW_PRINT1");*/
    	
        //
    	try
    	{
    		if(!from_date.trim().equals(""))
    		{
	    		query = "select EMP_CD from DLNG_EMP_GROUP_DTL where " +
	    				" EMP_CD='"+emp_cd+"' AND GROUP_CD='"+group_cd+"' ";
//	    				+ " AND FROM_DT=TO_DATE('"+from_date+"','dd/mm/yyyy')";
//	    		System.out.println("Query for checking Group Existance of selected User = "+query);
	    		rset = stmt.executeQuery(query);
	    		if(rset.next())
	    		{
	    			query = "delete from DLNG_EMP_GROUP_DTL where " +
							"EMP_CD='"+emp_cd+"' AND GROUP_CD='"+group_cd+"' ";
//							+ " AND FROM_DT=TO_DATE('"+from_date+"','dd/mm/yyyy')";
//	    			System.out.println("delete**"+query);
	    			stmt.executeUpdate(query);
	    		}
    		}
    		
	    	query = "insert into DLNG_EMP_GROUP_DTL" +
	    			"(EMP_CD,GROUP_CD,FROM_DT,TO_DT) "+
			       	"values('"+emp_cd+"','"+group_cd+"'," +
			       	"TO_DATE('"+from_dt+"','dd/mm/yyyy')," +
			       	"TO_DATE('"+to_dt+"','dd/mm/yyyy'))";
//	    	System.out.println("Group Allocation Insert Query = "+query);
		    stmt.executeUpdate(query);
    		
    		msg = "User Group Allocation Successfully Done ";
	        
	        url = "../admin/frm_mst_administrator.jsp?msg="+msg+
      				"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+
      				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&emp_cd="+emp_cd;
	        
	        form_nm = "Group Allocation";
	        String remark = msg+" for EMP_CD :"+emp_cd+" & GROUP_CD:"+group_cd+" & FROM_DT:"+from_dt+" & TO_DT:"+to_dt;
		    try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+remark);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		    
		    dbcon.commit();
    	}
    	catch(Exception exception)
    	{
    		error_msg="User Group Allocation Not Done ";
    		
    		   url = "../admin/frm_mst_administrator.jsp?error_msg="+error_msg+
         				"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+
         				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&emp_cd="+emp_cd;
   	        
    		//System.out.println(exception);
    		exception.printStackTrace();
    	}
    }//End Of Method Add_Emp_Group_Allocation() ...
    
    
    private void Delete_Emp_Group_Allocation(HttpServletRequest request) throws IOException, ServletException ,SQLException
    {  
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
    	String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
    	
    	methodName = "Delete_Emp_Group_Allocation()";
    	HttpSession session = request.getSession();
    	String emp_cd = request.getParameter("emp_cd");
        String group_cd = request.getParameter("group_cd");
        String from_dt = request.getParameter("from_dt")==null?"":request.getParameter("from_dt");
        String to_dt = request.getParameter("to_dt")==null?"":request.getParameter("to_dt");
        String emp_name = request.getParameter("emp_name");
        String group_name = request.getParameter("group_name");
        String audittrack=emp_name+" deleted from "+group_name+"group";
        HttpSession groupalloc_sess=request.getSession();
        groupalloc_sess.setAttribute("audittrack",audittrack);
/*        HttpSession sess=request.getSession();
    	String ALW_ADD1=(String)sess.getAttribute("ALW_ADD1");
    	String ALW_DEL1=(String)sess.getAttribute("ALW_DEL1");
    	String ALW_UDP1=(String)sess.getAttribute("ALW_UDP1");
    	String ALW_VIEW1=(String)sess.getAttribute("ALW_VIEW1");
    	String ALW_PRINT1=(String)sess.getAttribute("ALW_PRINT1");*/
    	//System.out.println("add"+ALW_ADD1+"update "+ALW_UDP1+"del"+ALW_DEL1+"print"+ALW_PRINT1+"view"+ALW_VIEW1);		  
        //
    	try
    	{
    		if(!from_dt.trim().equals("") && !to_dt.trim().equals(""))
    		{
    			query = "select EMP_CD from DLNG_EMP_GROUP_DTL where " +
						"EMP_CD='"+emp_cd+"' AND GROUP_CD='"+group_cd+"' AND " +
						"FROM_DT=TO_DATE('"+from_dt+"','dd/mm/yyyy') AND " +
						"TO_DT=TO_DATE('"+to_dt+"','dd/mm/yyyy')";
    			System.out.println("Query for checking Group Existance of selected User = "+query);
    			rset = stmt.executeQuery(query);
    			if(rset.next())
    			{
    				query = "delete from DLNG_EMP_GROUP_DTL where " +
							"EMP_CD='"+emp_cd+"' AND GROUP_CD='"+group_cd+"' AND " +
							"FROM_DT=TO_DATE('"+from_dt+"','dd/mm/yyyy') AND " +
							"TO_DT=TO_DATE('"+to_dt+"','dd/mm/yyyy')";
    				System.out.println("Query for checking Group Existance of selected User = "+query);
    				stmt.executeUpdate(query);	    		
    				status="User Group Allocation Successfully Deleted ";
    				dbcon.commit();
    			}
    			else
    			{
    				status="Record Does Not Exist For Deletion Of User Group Allocation ";
    			}
	    		msg = status;
	    		
	    		url = "../admin/frm_mst_administrator.jsp?msg="+msg+"&error_msg="+error_msg+"&status="+status+
	      				"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+
	      				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
	    		
    		}    		
    		
    		try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		error_msg="User Group Allocation Not Deleted !!!";
    		url = "../admin/frm_mst_administrator.jsp?error_msg="+error_msg+
      				"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+
      				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
    		//System.out.println(exception);
    		exception.printStackTrace();
    	}
    }//End Of Method Delete_Emp_Group_Allocation() ...
    
    
    private void Delete_Schedule_Details(HttpServletRequest request) throws IOException, ServletException ,SQLException
    {
    	methodName = "Delete_Schedule_Details()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	String schdelid = request.getParameter("schdelid") == null?"":request.getParameter("schdelid");
    	String schedule_dt = request.getParameter("date");
        String party_id = request.getParameter("party_id");
    	String Revno = request.getParameter("Revno") == null?"":request.getParameter("Revno");
    	
        try
    	{
       	 	if(!Ticket_Present(party_id,schedule_dt,schdelid))
       	 	{
           		query = "delete from FMS_DAILY_SCHEDULE  where MAPPING_ID ='"+party_id
           				+"' and SCHEDULE_DT= TO_DATE('"+schedule_dt+"','DD/MM/YY') AND SCHEDULE_ID = '"+schdelid+"' and rev_no = '"+Revno+"'";
           		
           		stmt.executeUpdate(query);
           		
           		query = "delete from fms_daily_sch_dtl where schedule_id = '"+schdelid+"'";
           		stmt.executeUpdate(query);
           		
           		status = "Selected Schedule Successfully Deleted ... ";               		
           	}
           	else
           	{
           		status = " Allocation Related To Selected Schedule Still Exist ... ";
           	}            
             
            dbcon.commit();
            msg = status;
            url = "../admin/frm_admin_erase_module.jsp?opt=5&status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
            
            try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		status = "Schedule Still Exist ... ";
    		url = "../admin/frm_admin_erase_module.jsp?opt=5&status="+status+"&formId="+formId+"&party_cd="+party_id+"&date="+schedule_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println("Exception in Frm_admin Servlet : Delete_Schedule_Details() Method... "+exception);
    		exception.printStackTrace();
    	}
    }//End Of Method Delete_Schedule_Details() ...
    
    
    public boolean Ticket_Present(String party_id, String schedule_dt,String schid) throws IOException, ServletException ,SQLException
    {
    	try
        {
    		boolean ret = false;
			int cnt1 = 0;
			
			query1 = "select count(*) from FMS_ALLOC_MST where MAPPING_ID  = '"+
					party_id+"' and ALLOC_DT = to_date('"+schedule_dt+"','dd/mm/yy')  and  alloc_id =  '"+schid+"' ";
			 
			rset = stmt.executeQuery(query1);
	        if(rset.next())
			{	
	         	cnt1 = Integer.parseInt(rset.getString(1).trim());
			}
	        if(cnt1>0)
	        {
	        	ret = true;
	        }
			return ret;
        }
    	catch(Exception exception)
    	{
			//System.out.println("Exception in Frm_admin Servlet : Ticket_Present() Method... "+exception);
			exception.printStackTrace();
			return true;
		}
	}//End Of Method Ticket_Present() ...
    
    
    private void Delete_Ticket_Details(HttpServletRequest request) throws IOException, ServletException ,SQLException
    {
    	methodName = "Delete_Ticket_Details()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	String ticket_dt = request.getParameter("date");
        String party_id = request.getParameter("party_id");
        String allocdelid = request.getParameter("allocdelid") == null?"":request.getParameter("allocdelid");
    	
        try
    	{
    		
           	 	if(!Invoice_Present(party_id,ticket_dt))
           	 	{
               		query = "delete from FMS_ALLOC_MST  where MAPPING_ID='"+party_id
               				+"' and ALLOC_DT =TO_DATE('"+ticket_dt+"','DD/MM/YY') AND ALLOC_ID = '"+allocdelid+"'";
               		stmt.executeUpdate(query);
               		
               		query = "delete from FMS_ALLOC_DTL  where MAPPING_ID='"+party_id
       				+"' AND ALLOC_ID = '"+allocdelid+"'";
       	         	stmt.executeUpdate(query);
       		
               		status = "Selected Allocation Successfully Deleted ... ";               		
               	}
               	else
               	{
               		status = "Allocation Still Exist ... ";
               	}
          
            dbcon.commit();
            msg = status;
            url = "../admin/frm_admin_erase_module.jsp?opt=4&status="+status+"&formId="+formId+"&party_cd="+party_id+"&date="+ticket_dt+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
            
            try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		status = "Allocation Still Exist ... ";
    		url = "../admin/frm_admin_erase_module.jsp?opt=4&status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println("Exception in Frm_admin Servlet : Delete_Ticket_Details() Method... "+exception);
    		exception.printStackTrace();
    	}
    }//End Of Method Delete_Ticket_Details() ...
    
    
    public boolean Invoice_Present(String party_id, String ticket_dt) throws IOException, ServletException ,SQLException
    {
    	try
        {
    		boolean ret = false;
			/*int cnt1 = 0;
			
			query1 = "select count(*) from FMS_TICKET_REC where PARTY_CD = '"+
					party_id+"' and to_char(TICKET_DT,'dd/mm/yy') ='"+ticket_dt+"'";
			 
			rset = stmt.executeQuery(query1);
	        if(rset.next())
			{	
	         	cnt1 = Integer.parseInt(rset.getString(1).trim());
			}
	        if(cnt1>0)
	        {
	        	ret = true;
	        }*/
			return ret;
        }
    	catch(Exception exception)
    	{
			//System.out.println("Exception in Frm_admin Servlet : Invoice_Present() Method... "+exception);
			exception.printStackTrace();
			return true;
		}
	}//End Of Method Invoice_Present() ...
    
    
    private void Delete_Daily_Nomination_Transporter(HttpServletRequest request) throws SQLException 
	{	 
    	methodName = "Delete_Daily_Nomination_Transporter()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	
    	try
		{
    		String nomdelid = request.getParameter("nomdelid") == null?"":request.getParameter("nomdelid");
    		String nomdate = request.getParameter("date") == null?"":request.getParameter("date");
    		String party_id = request.getParameter("party_id") == null?"":request.getParameter("party_id");
    		//String rev_no = request.getParameter("rev_no") == null?"0":request.getParameter("rev_no");
    		String cont_no = "";
    		String rec_pt = "";
	    
	     
    		query = " select CONT_NO, REC_PT_CD from FMS_CONTRACT_MST where PARTY_CD ='"+party_id+"' and " + 
					" to_date(ST_DT, 'dd/mm/yy')<= to_date('"+nomdate+"','dd/mm/yy') and "+
					" to_date(END_DT, 'dd/mm/yy')>= to_date('"+nomdate+"','dd/mm/yy')";
			
    		////System.out.println("QueryString: -" +queryString);
    		rset = stmt.executeQuery(query);
    		if(rset.next())
    		{
    			cont_no=rset.getString(1);
				rec_pt=rset.getString(2);
			}
    		else
    		{
				cont_no="";
				rec_pt="";
    		}
			
    		int count=0;
    		query = " select count(*) from FMS_TICKET_REC where ticket_dt=to_date('"+nomdate+"','dd/mm/yy') " +
					"and party_cd='"+party_id+"' and cont_no='"+cont_no+"' "  ;
					
    		////System.out.println(" -samik count 1 ** "+queryString);
    		rset=stmt.executeQuery(query);
    		if(rset.next())
    		{
    			count=rset.getInt(1);
			}
		
    		if(count == 0)
    		{
	     	   	query = "delete from FMS_DAILY_NOM where nom_id='"+nomdelid+"'";
				stmt.executeQuery(query);
				query = "delete from FMS_DAILY_NOM_DTL where nom_id='"+nomdelid+"'";
				stmt.executeQuery(query);
				status = "Selected Daily Nomination For Transporter Deleted Successfully ...";
    		}
    		else
    		{
    			status = "Deleting Failed : Ticketing Already Done For Atleast One Of The Contracts ...";
    		}
			
    		dbcon.commit();
    		msg = status;
    		url = "../admin/frm_admin_erase_module.jsp?opt=3&status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		
    		try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception exception)
		{
			status = "Deleting Failed : Daily Nomination For Transporter ...";
    		url = "../admin/frm_admin_erase_module.jsp?opt=3&status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println("Exception in Frm_admin Servlet : Delete_Daily_Nomination_Transporter() Method... "+exception);
    		exception.printStackTrace();
		}
	}//End Of Method Delete_Daily_Nomination_Transporter() ...
    
    
    private void Delete_Daily_Nomination(HttpServletRequest request) throws SQLException 
    {	  
    	methodName = "Delete_Daily_Nomination()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	
    	try
    	{
    		String nomdelid = request.getParameter("nomdelid") == null?"":request.getParameter("nomdelid");
    		String nomdate = request.getParameter("date") == null?"":request.getParameter("date");
    		String party_id = request.getParameter("party_id") == null?"":request.getParameter("party_id");
    		String Revno = request.getParameter("Revno") == null?"":request.getParameter("Revno");
    		 
    		
    		int count=0;
    		query = " select count(*) from FMS_ALLOC_MST where alloc_dt=to_date('"+nomdate+"','dd/mm/yy') " +
    				"and mapping_id ='"+party_id+"' and alloc_id='"+nomdelid+"' "  ;
    		
    		////System.out.println(" -samik count 1 ** "+queryString);
    		rset=stmt.executeQuery(query);
    		if(rset.next())
    		{
    			count=rset.getInt(1);
    		}
    		
    		if(count==0)
    		{
    			query = "delete from FMS_DAILY_NOM where nom_id='"+nomdelid+"' and mapping_id = '"+party_id+"' and  nom_dt=to_date('"+nomdate+"','dd/mm/yy') and rev_no = '"+Revno+"'";
    			stmt.executeQuery(query);
    			query = "delete from FMS_DAILY_NOM_DTL where nom_id='"+nomdelid+"' ";
    			stmt.executeQuery(query);
    			status = "Selected Daily Nomination Deleted Successfully ...";
    		}
    		else
    		{
    			status = "Deleting Failed : Allocation Already Done ...";
    		}

    		dbcon.commit();
    		msg = status;
    		url = "../admin/frm_admin_erase_module.jsp?opt=2&status="+status+"&formId="+formId+"&party_cd="+party_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		
    		try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		status = "Deleting Failed : Daily Nomination ...";
    		url = "../admin/frm_admin_erase_module.jsp?opt=2&status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println("Exception in Frm_admin Servlet : Delete_Daily_Nomination() Method... "+exception);
    		exception.printStackTrace();
    	} 
    }//End Of Method Delete_Daily_Nomination() ...
    
    
    private void Delete_Periodic_Nomination(HttpServletRequest request) throws SQLException 
    {	    
    	methodName = "Delete_Periodic_Nomination()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	
    	try
    	{
    		String nomdelid = request.getParameter("nomdelid") == null?"":request.getParameter("nomdelid");
    		String nomdate = request.getParameter("date") == null?"":request.getParameter("date");
    		String party_id = request.getParameter("party_id") == null?"":request.getParameter("party_id");
    	 
        	Vector party=new Vector();
        	StringTokenizer cd=new StringTokenizer(party_id,"*");
        	
    		while(cd.hasMoreTokens())
    		{
    			party.add(cd.nextToken());
    		}
    		
    		String pcd=""+party.elementAt(0);
    		String pcno=""+party.elementAt(1);
    		String psup=""+party.elementAt(2);
    		String prec=""+party.elementAt(3);
    		
    		int count=0;
    		/*query = " select count(*) from FMS_TICKET_REC where ticket_dt=to_date('"+nomdate+"','dd/mm/yy') " +
    				"and party_cd='"+pcd+"' and cont_no='"+pcno+"' "  ;
    		
    		////System.out.println(" -samik count 1 ** "+queryString);
    		rset=stmt.executeQuery(query);
    		if(rset.next())
    		{
    			count=rset.getInt(1);
    		}
    		
    		if(count==0)
    		{*/
    			query = "delete from FMS_PERIODICAL_NOM where nom_id='"+nomdelid+"'";
    			//System.out.println("ADMIN-QRY0073 :****DELETE**** :FMS_PERIODICAL_NOM : Frm_admin.java : "+query);
    			stmt.executeQuery(query);
    			query = "delete from FMS_PERIODICAL_NOM_DTL where nom_id='"+nomdelid+"'";
    			//System.out.println("ADMIN-QRY0074 :****DELETE**** :FMS_PERIODICAL_NOM_DTL : Frm_admin.java : "+query);
    			stmt.executeQuery(query);
    			status = "Selected Periodic Nomination Deleted Successfully ...";
    		/*}
    		else
    		{
    			status = "Deleting Failed : Ticketing Already Done ...";
    		}*/

    		dbcon.commit();
    		msg = status;
    		url = "../admin/frm_admin_erase_module.jsp?opt=1&status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		
    		try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		status = "Deleting Failed : Periodic Nomination ...";
    		url = "../admin/frm_admin_erase_module.jsp?opt=1&status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println("Exception in Frm_admin Servlet : Delete_Periodic_Nomination() Method... "+exception);
    		exception.printStackTrace();
    	} 
    }//End Of Method Delete_Periodic_Nomination() ...
    

    private void Enable_Disable_User_Status(HttpServletRequest request) throws SQLException 
    {	 
    	methodName = "Enable_Disable_User_Status()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId") == null?"":request.getParameter("formId");
    	String remark = request.getParameter("remark") == null?"":request.getParameter("remark");
        String empid = request.getParameter("empid") == null?"":request.getParameter("empid");
        String emp_nm = request.getParameter("emp_nm") == null?"":request.getParameter("emp_nm");
   	 	String update_empid = request.getParameter("update_empid") == null?"":request.getParameter("update_empid");
   	 	String u_status = request.getParameter("u_status") == null?"Enabled":request.getParameter("u_status");
   	 	String user_status = request.getParameter("user_status") == null?"N":request.getParameter("user_status");
   	 	String stat = "Disabled";
   	 	
   	 	if(u_status.equals("Disabled"))
		{
			stat = "Enabled";
		}
   	 	
   	 	try
    	{
   	 		escapeSingleQuotes obj = new escapeSingleQuotes();
         	remark = obj.replaceSingleQuotes(remark);

    		query = " INSERT INTO FMS_LOGIN_PERM_STAT(emp_cd,update_by,status,remarks,update_dt) VALUES('"+empid+"','"+update_empid+"','"+user_status+"','"+remark+"',sysdate) ";
    		//System.out.println("ADMIN-QRY0063 :INSERT :FMS_LOGIN_PERM_STAT : Frm_admin.java : "+query);
    		stmt.executeUpdate(query);
    	
    		query = " UPDATE HR_EMP_MST SET del_flag='"+user_status+"' WHERE emp_cd='"+empid+"' ";
    		//System.out.println("ADMIN-QRY0064 :UPDATE :HR_EMP_MST : Frm_admin.java : "+query);
    		////System.out.println("Samik writes query 2 = "+query);
    		stmt.executeUpdate(query);
    		    		  
    		dbcon.commit();
    		
    		status = "Successfully Changed The Status Of "+emp_nm+" From "+u_status+" To "+stat+" ...";
    		msg = status;
    	    url = "../admin/frm_admin_enable_disable_user.jsp?emp_id="+empid+"&status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    	    
    	    try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	  }
   	 	  catch(Exception exception)
    	  {
   	 		status = "Failed To "+stat+" : "+emp_nm+" ...";
    		url = "../admin/frm_admin_enable_disable_user.jsp?emp_id="+empid+"&status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println("Exception in Frm_admin Servlet : Enable_Disable_User_Status() Method... "+exception);
    		exception.printStackTrace();
    	  }  
    }//End Of Method Enable_Disable_User_Status() ...
    
    
    private void Add_Modify_Password_Life_Span(HttpServletRequest request) throws SQLException 
    {	    
    	methodName = "Add_Modify_Password_Life_Span()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	
    	try
    	{
    		escapeSingleQuotes obj = new escapeSingleQuotes();
                            	
	        String no_days = request.getParameter("no_days") == null?"":request.getParameter("no_days");
	    	String no_password = request.getParameter("no_password") == null?"":request.getParameter("no_password");
	    	String reminder_days = request.getParameter("reminder_days") == null?"":request.getParameter("reminder_days");
	    	String password_type = request.getParameter("password_type") == null?"0":request.getParameter("password_type");
	    	String userid_min = request.getParameter("userid_min") == null?"":request.getParameter("userid_min");
	    	String userid_max = request.getParameter("userid_max") == null?"":request.getParameter("userid_max");
	    	String password_min = request.getParameter("password_min") == null?"":request.getParameter("password_min");
	    	String password_max = request.getParameter("password_max") == null?"":request.getParameter("password_max");
	    	String eff_dt = request.getParameter("eff_dt") == null?"":request.getParameter("eff_dt");
	    	    	 
	    	int count=0;
    	 
	    	query = " select count(*) from FMS_PASSWORD_LIFE where eff_dt=to_date('"+eff_dt+"','dd/mm/yyyy')";
	    	//System.out.println("ADMIN-QRY0054 :SELECT :FMS_PASSWORD_LIFE : Frm_admin.java : "+query);
	    	rset=stmt.executeQuery(query);
    		if(rset.next())
    		{
    			count=rset.getInt(1);
    		}
    		
    		if(count==0)
    		{
    			query = "insert into FMS_PASSWORD_LIFE (no_days,no_password,reminder_days,password_type,userid_min,userid_max,password_min,password_max,eff_dt) " +
    					"values ('"+no_days+"','"+no_password+"','"+reminder_days+"','"+password_type+"','"+userid_min+"','"+userid_max+"','"+password_min+"'," +
    					"'"+password_max+"',to_date('"+eff_dt+"','dd/mm/yyyy'))";
    			//System.out.println("ADMIN-QRY0055 :INSERT :FMS_PASSWORD_LIFE : Frm_admin.java : "+query);
    			stmt.executeUpdate(query);
    		}
    		else
    		{
    			query = "update FMS_PASSWORD_LIFE set no_days='"+no_days+"', no_password='"+no_password+"', reminder_days='"+reminder_days+"', password_type='"+password_type+"', " +
    					"userid_min='"+userid_min+"',userid_max='"+userid_max+"',password_min='"+password_min+"',password_max='"+password_max+"' " +
    					"where eff_dt=to_date('"+eff_dt+"','dd/mm/yyyy')";
    			//System.out.println("ADMIN-QRY0056 :UPDATE :FMS_PASSWORD_LIFE : Frm_admin.java : "+query);
    			stmt.executeUpdate(query);
    		}
    		
    		dbcon.commit();
    		
    		status = "Password/User ID details successfully Updated !!!";
    		msg = status;
    		url = "../admin/frm_admin_password_life_dtl.jsp?status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		
    		try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		status = "Failed To Add/Modify The Password Life Span ...";
    		url = "../admin/frm_admin_password_life_dtl.jsp?status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println("Exception in Frm_admin Servlet : Add_Modify_Password_Life_Span() Method... "+exception);
    		exception.printStackTrace();
    	}  
    }//End Of Method Add_Modify_Password_Life_Span() ...
    
        
    private void User_Release_Lock(HttpServletRequest request) throws SQLException 
    {	    
    	methodName = "User_Release_Lock(()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	
    	try
    	{
    		escapeSingleQuotes obj = new escapeSingleQuotes();
                            	
            String emp_id = request.getParameter("emp_id")==null?"0":request.getParameter("emp_id");
            String emp_pass = request.getParameter("emp_pass")==null?" ":request.getParameter("emp_pass");
            String chk = request.getParameter("chk")==null?"N":request.getParameter("chk");
                        
            if(chk.equals("Y"))
            {
                StringBuffer stringbuffer = (new EncryptTest()).encrypt(emp_pass);
                String s2 = stringbuffer.toString();
                query = " UPDATE SEC_EMP_PASSWORDS SET locked_flag ='',password6=password5,password5=password4,password4=password3,password3=password2," +
                		" password2=password1,password1=password,password='"+s2+"',"+"reset_flag='Y' " +
                		" WHERE emp_cd='"+emp_id+"' ";
                //System.out.println("ADMIN-QRY0059 :UPDATE :SEC_EMP_PASSWORDS : Frm_admin.java : "+query);
            }
            else
            {
                query = " UPDATE SEC_EMP_PASSWORDS SET locked_flag ='' WHERE emp_cd='"+emp_id+"' ";
                //System.out.println("ADMIN-QRY0060 :UPDATE :SEC_EMP_PASSWORDS : Frm_admin.java : "+query);
            }
            stmt.executeUpdate(query);
    		
    		dbcon.commit();
    		
    		status = "Lock Released Successfully For The Selected User !!!";
    		msg = status;
    		url = "../admin/frm_admin_release_lock.jsp?status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		
    		try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		status = "Failed To Release The Lock For The Selected User !!!";
    		url = "../admin/frm_admin_release_lock.jsp?status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println("Exception in Frm_admin Servlet : User_Release_Lock() Method... "+exception);
    		exception.printStackTrace();
    	}  
    }//End Of Method User_Release_Lock() ...
    
    
    private void User_Reset_Password(HttpServletRequest request) throws SQLException 
    {	    
    	methodName = "User_Reset_Password()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	
    	try
    	{
    		escapeSingleQuotes obj = new escapeSingleQuotes();
                            	
            String emp_code = request.getParameter("user_code")==null?"0":request.getParameter("user_code");
            String change_passwd = request.getParameter("change_passwd")==null?"":request.getParameter("change_passwd");
            
            if(!change_passwd.trim().equals("") && !emp_code.trim().equals("") && !emp_code.trim().equals("0"))
            {
	            EncryptTest EncrObj = new EncryptTest();
	            StringBuffer stringbuffer = EncrObj.encrypt(change_passwd);
	            String s2 = stringbuffer.toString();
	            query = "UPDATE SEC_EMP_PASSWORDS SET password6=password5,password5=password4,password4=password3,password3=password2," +
	              		"password2=password1,password1=password,password='"+s2+"',reset_flag='Y' " +
	                	"WHERE emp_cd='"+emp_code.trim()+"'";
	            //System.out.println("Reset Password Query = "+query);
	            stmt.executeUpdate(query);
	    		
	    		dbcon.commit();    		
	    		status = "Password Reset Successfully For The Selected User !!!";
            }
            else
            {
            	status = "Nothing has been doneunder Password Reset activity, As User is Not selected Properly !!!";
            }
            msg = status;
    		url = "../admin/frm_admin_release_lock.jsp?status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		
    		try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		status = "Failed To Reset Password for the Selected User !!!";
    		url = "../admin/frm_admin_release_lock.jsp?status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println("Exception in Frm_admin Servlet : User_Reset_Password() Method... "+exception);
    		exception.printStackTrace();
    	}  
    }//End Of Method User_Reset_Password() ...
    
    
    private void Modify_Sub_Menu_Dtl(HttpServletRequest request) throws SQLException 
    {	    
    	methodName = "Modify_Sub_Menu_Dtl()";
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
    	String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
    	
    	HttpSession session = request.getSession();
    	String sub_menu_id = request.getParameter("sub_menu_id")==null?"0":request.getParameter("sub_menu_id");
    	//sra1-12/AUG/09
    	String old_submenu = request.getParameter("old_submenu")==null?"":request.getParameter("old_submenu");
    	String new_submenu = request.getParameter("new_submenu")==null?"":request.getParameter("new_submenu");
    	String audittrack="[updated->"+old_submenu+" to "+new_submenu+"]";
    	HttpSession menu_modify_sess=request.getSession();
    	menu_modify_sess.setAttribute("audittrack",audittrack);
    	//
    	try
    	{
    		escapeSingleQuotes obj = new escapeSingleQuotes();
                        
            String sub_menu_nm = request.getParameter("sub_menu_nm")==null?"":request.getParameter("sub_menu_nm");
            String sub_menu_path = request.getParameter("sub_menu_path")==null?"":request.getParameter("sub_menu_path");
            String module_id = request.getParameter("module_id")==null?"0":request.getParameter("module_id");
            String menu_type = request.getParameter("menu_type")==null?"F":request.getParameter("menu_type");
            String priority_id = request.getParameter("priority_id")==null?"0":request.getParameter("priority_id");
            String priority_old_id = request.getParameter("priority_old_id")==null?"0":request.getParameter("priority_old_id");
            String grp_id = request.getParameter("grp_id")==null?"0":request.getParameter("grp_id");
            //System.out.println("pri"+priority_id);
            //System.out.println("pold"+priority_old_id);
                      //System.out.println("<MODULE ID::> "+ module_id);  
            query = "UPDATE DLNG_FORM_MST SET form_name='"+sub_menu_nm+"',module_cd='"+module_id+"',classpath='"+sub_menu_path+"',doc_type='"+menu_type+"',deploy_dt=sysdate  , grp_seq_no = '"+priority_id+"' " +
            		" WHERE form_cd='"+sub_menu_id+"' ";
//        System.out.println("ADMIN-QRY0019 :UPDATE :SEC_FORM_MST : Frm_admin.java : "+query);
            stmt.executeUpdate(query);
            
            if(Integer.parseInt(priority_id) > Integer.parseInt(priority_old_id))
            {
            	query = "UPDATE DLNG_FORM_MST SET  grp_seq_no = grp_seq_no-1 " +
         				" WHERE module_cd =  '"+module_id+"' and grp_cd = '"+grp_id+"' and form_cd != '"+sub_menu_id+"' and grp_seq_no is not null and grp_seq_no between "+priority_old_id+"   and "+priority_id+"  ";
//            	System.out.println("ADMIN-QRYSY0095 :UPDATE :SEC_FORM_MST : Frm_admin.java : "+query);
            	stmt.executeUpdate(query);
            }
            else if(Integer.parseInt(priority_id) < Integer.parseInt(priority_old_id))
            {	
            		 query = "UPDATE DLNG_FORM_MST SET  grp_seq_no = grp_seq_no+1 " +
             				 " WHERE module_cd =  '"+module_id+"' and grp_cd = '"+grp_id+"' and form_cd != '"+sub_menu_id+"' and grp_seq_no is not null and grp_seq_no between "+priority_id+"   and "+priority_old_id+"  ";
            		 //System.out.println("ADMIN-QRYSY0094 :UPDATE :SEC_FORM_MST : Frm_admin.java : "+query);
            		 stmt.executeUpdate(query);
            }
            
    		
    		dbcon.commit();
    		
    		msg = "Sub Menu Details Modified Successfully For The Selected Sub Menu";
    		
    		 url = "../admin/frm_mst_administrator.jsp?msg="+msg+"&error_msg="+error_msg+
       				"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+
       				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
    		
    		try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		error_msg = "Sub Menu Details Failed To Modify";
    		 url = "../admin/frm_mst_administrator.jsp?msg="+msg+"&error_msg="+error_msg+
       				"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+
       				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
    		
    		//System.out.println("Exception in Frm_admin Servlet : Modify_Sub_Menu_Dtl() Method... "+exception);
    		exception.printStackTrace();
    	}  
    }//End Of Method Modify_Sub_Menu_Dtl() ...
    
    
    private void Insert_Sub_Menu_Dtl(HttpServletRequest request) throws SQLException 
    {	
    	System.out.println("*************** in Insert_Sub_Menu_Dtl()**************** ");
    	methodName = "Insert_Sub_Menu_Dtl()";
    	HttpSession session = request.getSession();
    	////System.out.println("test1");
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
    	String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
    	
    	String sub_menu_id = request.getParameter("sub_menu_id")==null?"0":request.getParameter("sub_menu_id");
    	String sub_menu_nm = request.getParameter("sub_menu_nm")==null?"":request.getParameter("sub_menu_nm");
        String sub_menu_path = request.getParameter("sub_menu_path")==null?"":request.getParameter("sub_menu_path");
        String module_id = request.getParameter("module_id")==null?"11":request.getParameter("module_id");
        String menu_type = request.getParameter("menu_type")==null?"F":request.getParameter("menu_type");
//        System.out.println("In setvelt  MODULE ID :" + module_id);
//        System.out.println("In setvelt  Menu id :" + sub_menu_id);
//        sub_menu_id = module_id +sub_menu_id;
        String grpNm = request.getParameter("grpNm")==null?"":request.getParameter("grpNm");
        String new_grp_nm = request.getParameter("new_grp_nm")==null?"":request.getParameter("new_grp_nm");
        String grp_id = request.getParameter("grp_id")==null?"":request.getParameter("grp_id");
        
        String grp_seq_no="";
       
    	try
    	{
    		if(grp_id.equalsIgnoreCase("other"))
	        {
	        	 String q1="select max(grp_cd)+1 from DLNG_FORM_MST where module_cd = '"+module_id+"' ";
	        	 //System.out.println("ADMIN-QRYSY099 :SELECT :SEC_FORM_MST : Frm_admin.java : "+q1);
	        	 rset = stmt.executeQuery(q1);
	             if(rset.next())
	             {
    	             grp_id = rset.getString(1);
    	             grpNm =  new_grp_nm;
	             }
	             if (grp_id==null)
	             { 
	             grp_id = module_id+"1";
	             }
	        }
    	//        	//System.out.println("What's the group id"+grp_id);
    	//        	//System.out.println("What's the value new_grp_nm"+new_grp_nm);
    	//        	//System.out.println("What's the value grpNm"+grpNm);
    	        	
    	         String q2="select max(grp_seq_no)+1 from DLNG_FORM_MST where module_cd = '"+module_id+"' and grp_cd = '"+grp_id+"' ";
    	         //System.out.println("ADMIN-QRYSY0100 :SELECT :SEC_FORM_MST : Frm_admin.java : "+q2);
   	        	 rset = stmt.executeQuery(q2);
   	             if(rset.next())
   	             {
   	            	 grp_seq_no = rset.getString(1);
   	            	 
   	            	 if(rset.getString(1) == null)
   	            	 {
   	            		grp_seq_no = "1";
   	            	 }
   	             }
   	             else
   	            	 grp_seq_no = "1";
    	        	
    		escapeSingleQuotes obj = new escapeSingleQuotes();
                        
            int recordExist = 0; 
            ////System.out.println("test3");
            query = " SELECT form_cd FROM DLNG_FORM_MST WHERE LOWER(form_name)=LOWER('"+sub_menu_nm+"') AND module_cd='"+module_id+"' AND doc_type='"+menu_type+"' ";
//            System.out.println("ADMIN-QRY0007 :SELECT :SEC_FORM_MST : Frm_admin.java : "+query);
            rset = stmt.executeQuery(query);
            if(rset.next())
            {
            	//System.out.println("test3-4");
            	recordExist = 1;
            	error_msg = "DUPLICATE Sub Menu Name - Details Not Inserted";
            }
            
            if(recordExist==0)
            {
            	////System.out.println("test4");
	            query = " INSERT INTO DLNG_FORM_MST(form_cd,form_name,module_cd,classpath,doc_type,deploy_dt,flag ,grp_cd,grp_nm,grp_seq_no)  VALUES('"+sub_menu_id+"','"+sub_menu_nm+"','"+module_id+"','"+sub_menu_path+"','"+menu_type+"',sysdate,'Y', '"+grp_id+"' , '"+grpNm+"' , '"+grp_seq_no+"') ";
//	            System.out.println("ADMIN-QRY0007 :INSERT :SEC_FORM_MST : Frm_admin.java : "+query);
                int  insCount = stmt.executeUpdate(query);
	            //System.out.println("test5");
	            String rights = "";
	            String groups = "";
	            String addRight = "N";
				String viewRight = "N";
				String deleteRight = "N";
				String printRight = "N";
				String grantRight = "N";
				String updateRight = "N";				
				//
				if(insCount>0)
				{
					////System.out.println("test5");
					addRight = request.getParameter("Add")!=null?"Y":"N";
					viewRight =	request.getParameter("View")!=null?"Y":"N";
					deleteRight = request.getParameter("Delete")!=null?"Y":"N";
					printRight = request.getParameter("Print")!=null?"Y":"N";
					//sra1-17/aug/09
					updateRight = request.getParameter("Update")!=null?"Y":"N";
					grantRight = request.getParameter("Grant")!=null?"Y":"N";
					//Following Code Has Been Improved By Samik Shah On 31st August, 2010 ...
					if(viewRight.equals("Y"))
					{
						rights += "Read, ";
					}
					if(addRight.equals("Y"))
					{
						rights += "Write, ";
					}
					if(deleteRight.equals("Y"))
					{
						rights += "Delete, ";
					}					
					if(printRight.equals("Y"))
					{
						rights += "Print, ";
					}
					if(rights.length()>3)
					{
						rights = rights.substring(0,rights.length()-2);
					}
					if(rights.length()<3)
					{
						rights = "No Rights Has Been Given";
					}
					
					insCount = 0;
					
					String groupList[] = request.getParameterValues("group_id");
					//EMPLOYEE_FORM_GRANT_BY										
	
					for(int i=0;i<groupList.length;i++)
					{		
						if(groupList[i].equals("00000") || groupList[i].equals("0"))
							continue;
						
						query = " SELECT group_name FROM DLNG_ACCESS_GROUP_MST WHERE group_cd='"+groupList[i]+"' ";
						//System.out.println("ADMIN-QRY0008 :SELECT :SEC_ACCESS_GROUP_MST : Frm_admin.java : "+query);
						rset = stmt.executeQuery(query);
			            if(rset.next())
			            {
			            	groups += rset.getString(1)+", ";
			            }
			            if(groups.length()>3)
						{
			            	groups = groups.substring(0,groups.length()-2);
						}
			            
						query = " insert into DLNG_GROUP_FORM("+
								" GROUP_CD,"+       
								" FORM_CD,"+       
								" ALW_VIEW,"+ 
								" ALW_DEL,"+      
								" ALW_UPD,"+ 
								" ALW_GRANT,"+
								" ALW_PRINT,"+
								" ALW_ADD)" +  
								" values('"+groupList[i]+"','"+sub_menu_id+"','"+viewRight+"',"+
								" '"+deleteRight+"','"+updateRight+"','"+grantRight+"','"+printRight+"','"+addRight+"')";
						insCount += stmt.executeUpdate(query);
						//System.out.println("ADMIN-QRY0009 :INSERT :SEC_GROUP_FORM : Frm_admin.java : "+query);
					}
				}else {
					
				}
				if(insCount>0 && !rights.equals("No Rights Has Been Given"))
				{
					msg = "Details For "+sub_menu_nm+" Inserted Successfully   For  "+rights+"  Rights To  "+groups+"  Group(s) ";
				}
				else
				{
					msg = "Details For "+sub_menu_nm+" Inserted Successfully For  "+rights+" To Any Group(s)";
				}
			}
            
    		dbcon.commit();
    		 url = "../admin/frm_mst_administrator.jsp?msg="+msg+"&error_msg="+error_msg+
       				"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+
       				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
    		
//    		System.out.println("url***"+url);
    		try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		 url = "../admin/frm_mst_administrator.jsp?msg="+msg+"&error_msg="+error_msg+
       				"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+
       				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl;
    		//System.out.println("Exception in Frm_admin Servlet : Insert_Sub_Menu_Dtl() Method... "+exception);
    		exception.printStackTrace();
    	}  
    }//End Of Method Insert_Sub_Menu_Dtl() ...
    
    
    private void Insert_Access_Rights_Dtl(HttpServletRequest request) throws SQLException 
    {
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd");
    	String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl");
    	methodName = "Insert_Access_Rights_Dtl()";
    	HttpSession session = request.getSession();
    	String module_id = request.getParameter("module_id")==null?"All":request.getParameter("module_id");
        String group_id = request.getParameter("group_id")==null?"0":request.getParameter("group_id");
        String group_nm = request.getParameter("group_nm")==null?"":request.getParameter("group_nm");
        
        String subMenuId[] = request.getParameterValues("sub_menu_id");
        String addPerm[] = request.getParameterValues("add_perm");
        String deletePerm[] = request.getParameterValues("delete_perm");
        String viewPrintPerm[] = request.getParameterValues("view_print_perm");
        String grantPerm[] = request.getParameterValues("grant_perm");
        String checkPerm[]=request.getParameterValues("Check_perm");
        String authorizePerm[]=request.getParameterValues("Authorize_perm");
        String updatePerm[]=request.getParameterValues("Update_perm");
        String printPerm[]=request.getParameterValues("print_perm");
       
    	try
    	{
    		escapeSingleQuotes obj = new escapeSingleQuotes();
			/*
			 * query = "DELETE FROM DLNG_GROUP_FORM WHERE group_cd='"+group_id+"' ";
			 * System.out.
			 * println("ADMIN-QRY0014 :**DELETE** :SEC_GROUP_FORM : Frm_admin.java : "+query
			 * ); stmt.executeUpdate(query);
			 */  
            for(int i=0;i<subMenuId.length;i++)
			{
            	 query ="DELETE FROM DLNG_GROUP_FORM WHERE group_cd='"+group_id+"' and FORM_CD = '"+subMenuId[i]+"'";
//            	 System.out.println("ADMIN-QRY0014 :**DELETE** :SEC_GROUP_FORM : Frm_admin.java : "+query); 
            	 stmt.executeUpdate(query);
				if(addPerm[i].equals("Y") || deletePerm[i].equals("Y") || viewPrintPerm[i].equals("Y") || grantPerm[i].equals("Y") || printPerm[i].equals("Y") || updatePerm[i].equals("Y") || checkPerm[i].equals("Y") || authorizePerm[i].equals("Y"))
				{
					query = "INSERT into DLNG_GROUP_FORM("+ 
					" GROUP_CD,"+       
					" FORM_CD,"+       
					" ALW_VIEW,"+ 
					" ALW_DEL,"+      
					" ALW_UPD,"+ 
					" ALW_GRANT,"+
					" ALW_PRINT,"+
					" ALW_ADD," +
					" ALW_CHECK," +
					" ALW_AUTHORIZE)" +  
					" values('"+group_id+"',"+
					" '"+subMenuId[i]+"',"+
					" '"+viewPrintPerm[i]+"',"+
					" '"+deletePerm[i]+"',"+
					" '"+updatePerm[i]+"',"+
					" '"+grantPerm[i]+"',"+
					" '"+printPerm[i]+"',"+
					" '"+addPerm[i]+"'," +
					" '"+checkPerm[i]+"'," +
					" '"+authorizePerm[i]+"')";
//					System.out.println("ADMIN-QRY0015 :INSERT :SEC_GROUP_FORM : Frm_admin.java :-- "+query);
					stmt.executeUpdate(query);
				}
			}
			msg = "Access Rights Details Inserted For Group "+group_nm+"";
                
    		dbcon.commit();
    		
    		/*url = "../admin/frm_admin_access_rights_selection.jsp?msg="+msg+"&error_msg="+error_msg+"&formId="+formId+"&group_id="+group_id+
    				"&group_nm="+group_nm+"&module_id="+module_id+"&write_permission="+write_permission+
    				"&delete_permission="+delete_permission+"&print_permission="+print_permission+
    				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&head_tab="+head_tab+"&modCd="+modCd;*/
    		
    		url = "../admin/frm_mst_administrator.jsp?msg="+msg+"&error_msg="+error_msg+"&group_id="+group_id+"&group_nm="+group_nm+"&module_id="+module_id+
       				"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+
       				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&show_rights=Y";
//    		System.out.println("url*****"+url);
//    		System.out.println("session*****"+session.getAttribute("username")+"***"+session.getAttribute("ip"));
    		try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		error_msg = "Access Rights Details Not Inserted ...";
//    		url = "../admin/frm_admin_access_rights_selection.jsp?msg="+msg+"&error_msg="+error_msg+"&formId="+formId+"&group_id="+group_id+"&group_nm="+group_nm+"&module_id="+module_id+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&head_tab="+head_tab+"&modCd="+modCd;
    		url = "../admin/frm_mst_administrator.jsp?msg="+msg+"&error_msg="+error_msg+"&group_id="+group_id+"&group_nm="+group_nm+"&module_id="+module_id+
       				"&alw_add="+write_permission+"&alw_del="+delete_permission+"&alw_print="+print_permission+
       				"&approve_permission="+approve_permission+"&audit_permission="+audit_permission+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&show_rights=Y";
    		//System.out.println("Exception in Frm_admin Servlet : Insert_Access_Rights_Dtl() Method... "+exception);
    		exception.printStackTrace();
    	}  
    }//End Of Method Insert_Access_Rights_Dtl() ...
    
    
    public void Add_Activity_Reminder(HttpServletRequest request,HttpServletResponse response)
    {
    	methodName = "Add_Activity_Reminder()";
    	HttpSession session = request.getSession();
    	String activity_cd[] = request.getParameterValues("activity_cd");//==null?"0":request.getParameter("activity_cd");
		String user_cd[] = request.getParameterValues("user_cd");
		String reminder_days = request.getParameter("reminder_days")==null?"0":request.getParameter("reminder_days");
		String emp_cd = request.getParameter("emp_cd")==null?"0":request.getParameter("emp_cd");
		String remark = request.getParameter("remark")==null?"":request.getParameter("remark");		
		remark = obj.replaceSingleQuotes(remark);		
		try
    	{
			if(user_cd.length > 0)
			{
				//int count=0;
				//String rem_days="";
				for(int i=0; i<user_cd.length; i++)
				{
					if(activity_cd.length > 0)
					{
						for(int j=0; j<activity_cd.length; j++) 
						{
							if(!(activity_cd[j].trim().equals("")) && !(user_cd[i].trim().equals("0")))
							{
								query = "select REMINDER_DAYS,REMARK from FMS7_REMINDER_PERMISSION_MST where user_cd="+user_cd[i]+" and activity_cd='"+activity_cd[j]+"'";							
								//System.out.println(" outer if ** "+query);
								rset=stmt.executeQuery(query);
								if(rset.next())
								{								
									query = "delete from FMS7_REMINDER_PERMISSION_MST where user_cd="+user_cd[i]+" and activity_cd='"+activity_cd[j]+"'";
									//System.out.println("DELETE FMS7_REMINDER_PERMISSION_MST: "+query);
									stmt.executeUpdate(query);
								}
														
								query = "insert into FMS7_REMINDER_PERMISSION_MST(ACTIVITY_CD,USER_CD,REMINDER_DAYS,REMARK,EMP_CD,ENT_DT,FLAG) values('"+activity_cd[j]+"',"+user_cd[i]+","+reminder_days+",'"+remark+"',"+emp_cd+",sysdate,'Y')";
								//System.out.println("INSERT FMS7_REMINDER_PERMISSION_MST: "+query);
								stmt.executeUpdate(query);
								msg ="Activity Reminder Successfully Submitted !!!";
								//System.out.println(" activity ** "+activity_cd[j]+" ,user_cd = "+user_cd[i]);
							}
						}
					}
				}
				url = "../admin/frm_admin_activity_reminder.jsp?msg="+msg+"&reminder_days="+reminder_days+"&remark="+remark;
				dbcon.commit();
				
				try
				{
					new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@"+form_nm+"~: "+msg);
				}
				catch(Exception infoLogger)
				{
					//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
					infoLogger.printStackTrace();
				}
			}
    	}    	
    	catch(Exception e)
    	{
    		msg ="Activity Reminder Failed!!!";
    		e.printStackTrace();
    		url = "../admin/frm_admin_activity_reminder.jsp?msg="+msg+"&reminder_days="+reminder_days+"&remark="+remark;
    		//System.out.println("Exception in Frm_admin Servlet : Modify_Sub_Menu_Dtl() Method... "+ e);
    		
    	}  
    }
//SB20151030////////////////////////
    private void   SaveDutyTime(HttpServletRequest request,HttpServletResponse response)
    {	
    	HttpSession sess = request.getSession();
    	String queryString="";	
      try
    	{
          methodName = "SaveData1()";
          escapeSingleQuotes obj = new escapeSingleQuotes();
          
    	String message="";
    			     
          HttpSession session = request.getSession();
        	String comp_cd = request.getParameter("comp_cd") == null?"56":request.getParameter("comp_cd");
        	String group_cd=request.getParameter("group_nm") == null?"":request.getParameter("group_nm");
        	String new_group_nm=request.getParameter("new_group_nm") == null?"":request.getParameter("new_group_nm");
        	String fst=request.getParameter("fst") == null?"":request.getParameter("fst");
        	String lst=request.getParameter("lst") == null?"":request.getParameter("lst");
        	String brk_ent=request.getParameter("brk_ent") == null?"":request.getParameter("brk_ent");
        	String brk_exit=request.getParameter("brk_exit") == null?"":request.getParameter("brk_exit");
        	String grace=request.getParameter("grace") == null?"":request.getParameter("grace");
        	String tot=request.getParameter("tot") == null?"":request.getParameter("tot");
    			String addl_grace=request.getParameter("addl_grace") == null?"":request.getParameter("addl_grace");
    			String grace_limit=request.getParameter("grace_limit") == null?"":request.getParameter("grace_limit");
    			String tot_week=request.getParameter("tot_week") == null?"":request.getParameter("tot_week");
    			String wkly_wrk_days=request.getParameter("wkly_wrk_days") == null?"":request.getParameter("wkly_wrk_days");
        	String wkly_off=request.getParameter("wkly_off") == null?"":request.getParameter("wkly_off");
        	
        	if (!new_group_nm.equals(""))//insert new group
        	{
        		String max="0";
        		queryString="Select nvl(max(group_cd),'0') from hr_duty_timing_mst";
        		rset=stmt.executeQuery(queryString);
        		if (rset.next())
        		{
        			max=""+(Integer.parseInt(rset.getString(1))+1);
        		}
        		queryString="Insert into hr_duty_timing_mst(comp_cd,group_cd,group_nm,fst,lst,brk_ent,brk_exit,grace,tot,addl_grace,grace_limit,tot_week,wkly_wrk_days,wkly_off) values('"+comp_cd+"','"+max+"','"+new_group_nm+"','"+fst+"','"+lst+"','"+brk_ent+"','"+brk_exit+"','"+grace+"','"+tot+"','"+addl_grace+"','"+grace_limit+"','"+tot_week+"','"+wkly_wrk_days+"','"+wkly_off+"')";
        		stmt.executeUpdate(queryString);    		
        		message="Status: New Group Inserted";
        	}
        	else
        	{
        		queryString="update hr_duty_timing_mst set fst='"+fst+"',lst='"+lst+"',brk_ent='"+brk_ent+"',brk_exit='"+brk_exit+"',grace='"+grace+"',tot='"+tot+"',addl_grace='"+addl_grace+"',grace_limit='"+grace_limit+"',tot_week='"+tot_week+"',wkly_wrk_days='"+wkly_wrk_days+"', wkly_off='"+wkly_off+"' where comp_cd='"+comp_cd+"' and group_cd='"+group_cd+"'";
        		stmt.executeUpdate(queryString);
        		message="Status: Group Updated";
        	}
        	String msg="Duty Timing is submitted successfully...!!!";
			String sub="1";
			url="../leave/frm_daily_time_mst.jsp?gp_cd="+group_cd+"&message="+msg+"&sub="+sub;
			dbcon.commit();
    	  }
    		catch(Exception e)
    	  {
    			e.printStackTrace();
    			String msg="Duty Timing is submitted successfully...!!!";
    			e.printStackTrace();
    			String sub="1";
    			url="../leave/frm_daily_time_mst.jsp?gp_cd="+group_cd+"&message="+msg+"&sub="+sub;	
    	  }  
     }

    String chkDate(String dt)
    {
    	String queryString="";	
    	try{
    			String dtsign="";
       	queryString="select trunc(sysdate)-to_date('"+dt+"','dd/mm/yyyy') from dual";
       	//System.out.println(queryString);
       	rset=stmt.executeQuery(queryString);
       	if (rset.next())
       	{
       		dtsign=rset.getString(1);		
       	} 		
       	if (Float.parseFloat(dtsign)>0)
       	{
       		return "false";
       	}
       	else
       		return "true";
   	}catch(Exception e)
   	{
   		e.printStackTrace();
   		return "false";
   	}
    }
    
    private void   SaveDutyAssignment(HttpServletRequest request,HttpServletResponse response)
    {	  
    	HttpSession sess = request.getSession();
    	String queryString="";	
      try
    	{
          methodName = "SaveData2()";
          escapeSingleQuotes obj = new escapeSingleQuotes();
         
          HttpSession session = request.getSession();
        	String comp_cd 	= request.getParameter("comp_cd") == null?"56":request.getParameter("comp_cd");
        	String branch_cd= request.getParameter("branch_cd") == null?"":request.getParameter("branch_cd");
        	String emp_cd[]		= request.getParameterValues("emp_cd");
        	String group_cd	=	request.getParameter("group_cd")== null?"0":request.getParameter("group_cd") ;
        	String desig_cd	=	request.getParameter("desig_cd")== null?"":request.getParameter("desig_cd") ;
        	String fm_eff_dt[]= request.getParameterValues("fm_eff_dt");
        	String to_eff_dt[]= request.getParameterValues("to_eff_dt");
        	String upd_flg[]= request.getParameterValues("upd_flg");
        	String grp_flg[]= request.getParameterValues("grp_flg");
        	String prevdt="false";
        	
        	String message="";
        	if (!group_cd.equals("0"))
        	{
        		for(int i=0;i<emp_cd.length;i++)
        		{
        	
        			if (grp_flg[i].equals("t"))//the rec has been checked
        			{
    	    			if (upd_flg[i].equals("N") )//new record
    	    			{
    	    				prevdt=chkDate(fm_eff_dt[i]);
    	    				if (prevdt.equals("true"))
    	    				{	    				
    		    				queryString="insert into hr_emp_duty_rel_mst(comp_cd,emp_cd,group_cd,fm_eff_dt,to_eff_dt) values('"+comp_cd+"','"+emp_cd[i]+"','"+group_cd+"',to_date('"+fm_eff_dt[i]+"','dd/mm/yyyy'),to_date('"+to_eff_dt[i]+"','dd/mm/yyyy'))";
    		    				stmt.executeUpdate(queryString);
    		    				message="Status: Records Updated!!!";
    	    				}
    	    				else
    	    				{
    	    					throw new Exception("One of the dates was less than the current date");
    	    				}
    	    				
    	    			}
    	    			
    	    			else if (upd_flg[i].equals("R") &&grp_flg[i].equals("t"))//update record
    	    			{
    	    				queryString="update hr_emp_duty_rel_mst set to_eff_dt=to_date('"+to_eff_dt[i]+"','dd/mm/yyyy') where comp_cd='"+comp_cd+"' and emp_cd='"+emp_cd[i]+"' and group_cd='"+group_cd+"' and fm_eff_dt=to_date('"+fm_eff_dt[i]+"','dd/mm/yyyy')";
    	    				stmt.executeUpdate(queryString);
    	    				message="Status: Records Updated";
    	    			}
    	    			
    	    		}
    	    		else if (upd_flg[i].equals("R")&&grp_flg[i].equals("f"))//remove and put the user back into normal group
    	    			{
    	    				String today="";
    	    				queryString="select decode(sign((sysdate-1)-(to_date('"+fm_eff_dt[i]+"','dd/mm/yyyy'))),-1,'del','upd') from dual";
    	    				rset=stmt.executeQuery(queryString);
    	    				if (rset.next())
    	    				{
    	    					today=rset.getString(1);
    	    				}
    	    				if (today.equals("del"))
    	    				{
    	    					queryString="delete from hr_emp_duty_rel_mst where comp_cd='"+comp_cd+"'  and emp_cd='"+emp_cd[i]+"' and group_cd='"+group_cd+"' and fm_eff_dt=to_date('"+fm_eff_dt[i]+"','dd/mm/yyyy')";
    	    				}
    	    				else
    	    				{
    	    					queryString="update hr_emp_duty_rel_mst set to_eff_dt=trunc(sysdate-1) where comp_cd='"+comp_cd+"'  and emp_cd='"+emp_cd[i]+"' and group_cd='"+group_cd+"' and fm_eff_dt=to_date('"+fm_eff_dt[i]+"','dd/mm/yyyy')";
    	    					
    	    					
    	    				}
    	    				stmt.executeUpdate(queryString);
    	    				message="Status: Records Updated";
    	    			}
    	    		
    	    		//
    	    	}//end of for
   	    	
        	}
        	
        	String msg="Duty Timing is submitted successfully...!!!";
			String sub="1";
			url="../leave/frm_emp_time_assgn.jsp?gp_cd="+group_cd+"&branch_cd="+branch_cd+"&desg_cd="+desig_cd+"&message="+msg+"&sub="+sub;
			dbcon.commit();
    	  }
    		catch(Exception e)
    	  {
    			e.printStackTrace();
    			String msg="Duty Timing is submitted successfully...!!!";
    			e.printStackTrace();
    			String sub="1";
    			url="../leave/frm_emp_time_assgn.jsp?gp_cd="+group_cd+"&message="+msg+"&sub="+sub;
    	  }  	
     }
///////////////////////////////////    
}//End Of Class Frm_admin ...