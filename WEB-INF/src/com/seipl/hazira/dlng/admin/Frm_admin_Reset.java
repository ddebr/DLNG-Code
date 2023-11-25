package com.seipl.hazira.dlng.admin;

import com.seipl.hazira.dlng.mail.mail;
import com.seipl.hazira.dlng.util.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.*;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;


//Code Reviewed by	: Mr. Samik Shah
//CR Date			: 12th January 2011
//CR Status  		: Consistent with reference to BRS ... 
@WebServlet("/servlet/Frm_admin_Reset")
public class Frm_admin_Reset extends HttpServlet
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
    private ResultSet rset = null, rset1 = null;
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
	
    public String write_permission = "N";
	public String delete_permission = "N";
	public String print_permission = "N";
	public String check_permission = "N";
	public String authorize_permission = "N";
	public String approve_permission = "N";
	public String audit_permission = "N";
    
	public String form_id = "0";
	public String form_nm = "";
	
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
                	String del_flag = httpservletrequest.getParameter("del_flag")==null?"":httpservletrequest.getParameter("del_flag");
                	write_permission = httpservletrequest.getParameter("write_permission")==null?"N":httpservletrequest.getParameter("write_permission");
        			delete_permission = httpservletrequest.getParameter("delete_permission")==null?"N":httpservletrequest.getParameter("delete_permission");
        			print_permission = httpservletrequest.getParameter("print_permission")==null?"N":httpservletrequest.getParameter("print_permission");
        			check_permission = httpservletrequest.getParameter("check_permission")==null?"N":httpservletrequest.getParameter("check_permission");
        			authorize_permission = httpservletrequest.getParameter("authorize_permission")==null?"N":httpservletrequest.getParameter("authorize_permission");
        			approve_permission = httpservletrequest.getParameter("approve_permission")==null?"N":httpservletrequest.getParameter("approve_permission");
        			audit_permission = httpservletrequest.getParameter("audit_permission")==null?"N":httpservletrequest.getParameter("audit_permission");
                	
        			form_id = httpservletrequest.getParameter("form_id")==null?"0":httpservletrequest.getParameter("form_id");
        			form_nm = httpservletrequest.getParameter("form_nm")==null?"":httpservletrequest.getParameter("form_nm");
        			
                	if(option.equals("User_Release_Lock_self"))  //SB20200406
                    {
                    	User_Release_Lock_self(httpservletrequest);  //SB20091210
                    }
                   
                	
                }
            }
            httpservletresponse.sendRedirect(url);
        }
        catch(Exception exception)
        {
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
					e.printStackTrace();
				}
				rset = null;
			}
			if(rset1 != null) 
			{ 
				try
				{
					rset.close();
				}
				catch(SQLException e) 
				{
					e.printStackTrace();
				}
				rset1 = null;
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
					e.printStackTrace();
				}
				dbcon = null;
			}
		}
    }//End Of doPost() Method ...
    


    /////////////////////////////////////////////////////////////////////////////
    private void User_Release_Lock_self(HttpServletRequest request) throws SQLException 
    {	    
    	methodName = "User_Release_Lock2(()";
    	HttpSession session = request.getSession();
    	String formId = request.getParameter("formId")==null?"":request.getParameter("formId");
    	String login = request.getParameter("login")==null?"":request.getParameter("login");
    	String email = request.getParameter("email")==null?"":request.getParameter("email");
    	System.out.println("Release Lock/Passwd for login: "+login);
    	try
    	{
    		escapeSingleQuotes obj = new escapeSingleQuotes();
                            	
            String emp_id = "0";//request.getParameter("emp_id")==null?"0":request.getParameter("emp_id");
            String emp_pass = request.getParameter("emp_pass")==null?" ":request.getParameter("emp_pass");
            String chk = request.getParameter("chk")==null?"N":request.getParameter("chk");
         //   emp_pass = "Bipl@123";  
            //emp_id = login;
            int count=0;
            String queryString = "select count(emp_cd) from hr_emp_mst where email_id='"+login+"' and del_flag !='Y' ";
	  //		System.out.println(queryString);
	        rset = stmt.executeQuery(queryString);
	        if(rset.next())
	        {
	        	count = rset.getInt(1);
	        }
	        if(count==1)
		    {
            queryString = "select emp_cd from hr_emp_mst where (emp_abr='"+login+"' OR email_id='"+login+"') and (del_flag !='Y' OR del_flag is null) ";
			  		//System.out.println(queryString);
			        rset = stmt.executeQuery(queryString);
			        if(rset.next())
			        {
			        	emp_id = rset.getString(1);
			        }
		    }
	        else
	        {
	        	queryString = "select emp_cd from hr_emp_mst where emp_abr='"+login+"' and (del_flag !='Y' OR del_flag is null) ";
		  	//	System.out.println(queryString);
		        rset = stmt.executeQuery(queryString);
		        if(rset.next())
		        {
		        	emp_id = rset.getString(1);
		        }
	        }
	        System.out.println("emp_id: "+emp_id);
			//////////////////////////////////////////////////////
	        String emp_nm=""; String emp_abr=""; String emp_email="";
			if(Integer.parseInt(emp_id)>0)
		    {
			
			queryString = " SELECT A.emp_cd,emp_nm,emp_abr,email_id, B.locked_flag FROM HR_EMP_MST A,SEC_EMP_PASSWORDS B "+
			" WHERE A.emp_cd=B.emp_cd AND  B.emp_cd='"+emp_id+"'";
			//System.out.println("ADMIN-QRY0058 :SELECT :HR_EMP_MST, SEC_EMP_PASSWORDS : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
			emp_nm = rset.getString(2);
			emp_abr = rset.getString(3);
			emp_email = rset.getString(4);
			}
			/////////////////////////////////////////////////////
            if(chk.equals("Y"))
            {
            	query = " select to_char(sysdate,'mmhhssmi') from dual ";
               // System.out.println("ADMIN-QRY001 :SELECT :dual : "+query);
    			rset = stmt.executeQuery(query);
    			while(rset.next())
    			{
    				emp_pass = rset.getString(1);
    		//	emp_abr = rset.getString(3);
    			}
                StringBuffer stringbuffer = (new EncryptTest()).encrypt(emp_pass);
                String s2 = stringbuffer.toString();
           /*ORI     query = " UPDATE SEC_EMP_PASSWORDS SET locked_flag ='',password6=password5,password5=password4,password4=password3,password3=password2," +
                		" password2=password1,password1=password,password='"+s2+"',"+"reset_flag='Y' " +
                		" WHERE emp_cd='"+emp_id+"' ";
                		*/
                
                query = " UPDATE SEC_EMP_PASSWORDS SET locked_flag ='',password='"+s2+"',"+"reset_flag='Y', LAST_CHANGE=SYSDATE " +
                		" WHERE emp_cd='"+emp_id+"' ";
           //     System.out.println("ADMIN-QRY0059 :UPDATE :SEC_EMP_PASSWORDS : Frm_admin.java : "+query);
             //   status = "<FONT COLOR=GREEN size=3>Hello "+emp_nm+"<BR>Password Reset Successfull. <BR>Check your registered Email for New Password. !!!</FONT";
                status = "Hello "+emp_nm+" Password Reset Successfull. Check your registered Email for New Password. !!!";
            }
            else
            {
                query = " UPDATE SEC_EMP_PASSWORDS SET locked_flag ='' WHERE emp_cd='"+emp_id+"' ";
            //    System.out.println("ADMIN-QRY0060 :UPDATE :SEC_EMP_PASSWORDS : Frm_admin.java : "+query);
               // status = "<FONT COLOR=BLUE size=3>Lock Released Successfully !!!</FONT>";
                status = "Lock Released Successfully !!!";
            }
            stmt.executeUpdate(query);
    		dbcon.commit();
    		System.out.println("Completed Release Lock/Passwd for login: "+login);
    		////////////////////////EMAIL FaCILITY/////////////////////
    		String mail_list_path="";
			
		//	File fsetup=new File(request.getRealPath("WEB-INF//classes//com//hlpl//hazira//fms7//sales_invoice//InvoiceSetup.txt"));
			File fsetup=new File(request.getRealPath("WEB-INF//classes//com//seipl//hazira//dlng//admin//ResetMailSetup.txt"));
			mail_list_path=fsetup.getAbsolutePath();
			
			FileInputStream f1 = new FileInputStream(mail_list_path);
			DataInputStream in = new DataInputStream(f1);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String strline="",host="",from_mail="",dbline="",username="",encrypted="",password="";
		
			while((strline = br.readLine())!=null)
			{
				if(strline.startsWith("host"))
				{
					String  tmp[]=strline.split("host:");
					host = tmp[1].toString();
				}
				if(strline.startsWith("from"))
				{
					String  tmp[]=strline.split("from:");
					from_mail = tmp[1].toString();
				}
				if(strline.startsWith("pwd"))
				{
 					String  tmp[]=strline.split("pwd:");
                    encrypted = tmp[1].toString();
                    password=encrypted;
				}
				if(strline.startsWith("dbline"))
				{
					String  tmp[]=strline.split("dbline:");
					dbline = tmp[1].toString();
				}
				if(strline.startsWith("username"))
				{
					String  tmp[]=strline.split("username:");
					username = tmp[1].toString();
				}
				if(strline.startsWith("password"))
				{
                    String  tmp[]=strline.split("password:");
                    encrypted = tmp[1].toString();
                  
				}
			}
			///////////////////////
		if(chk.equals("Y"))
	       {
			mail m1=new mail();
			
			boolean actual_rec_flag=false;
			String MailBody="";
			int x=1;
			boolean flg=false;
				
				MailBody="<h4>Dear User,</h4><br>We have received Password Reset Request. This is your new Password.<br><br><table align='center' bordercolor='blue' width='100%' style='font-size: x-small;' border='1' cellpadding='0' cellspacing='0'><tr bgcolor='green'>";
								
				MailBody+="<th align='center' width='13%'><font size='2' color='white'>User Name</font></th>" +
						  "<th align='center' width='15%'><font size='2' color='white'>Login Id</font></th>";	
							MailBody+="<th align='center' width='10%'><font size='2' color='white'>Temp. Password</font></th>";			
				MailBody+="</tr>";
				String bg="";
				int xx=0;
				if(xx%2!=0) 
					bg="#E0E0E0";
				else 
					bg="white";
				MailBody+="<tr bgcolor="+bg+">";
				MailBody+="<td align='center' width='13%'><font size='2' color='black'><b>"+emp_nm+"</b></font></td>";		//INVOICE NUMBER
				MailBody+="<td align='center' width='8%'><font size='2' color='black'><b>"+emp_abr+"</b></font></td>";	
				MailBody+="<td align='center' width='8%'><font size='2' color='black'><b>"+emp_pass+"</b></font></td>";
				MailBody+="</tr>";
				flg=true;
				MailBody+="</table><br><br>";
				MailBody+="Please maintain confidentiality.<br> NOTE: This notification has been auto-generated - PLEASE DO NOT REPLY. <br>If you have any queries contact System Administrator.";
				MailBody+="<br><br><font style='font-size: x-small'>Thanks</font><br><font style='font-size: xx-small; color: gray'>- This is System Generated Email.</font>";
				
				x *=m1.sendMail1("FMS9: Password Reset Confirmation",MailBody,emp_nm,emp_email,from_mail,from_mail,"",host,from_mail,password,false);

			if(x==1 && flg) 
			{
				// dbcon.commit(); //HS20160809
				 msg=" Mail Sent Successfully..!";
				 status=status+msg;
			} 
			else
			{	
				dbcon.rollback();
				if(x==2 && flg) 
					msg="ERR in Sending Email..!";
				else
					msg="ERR in Submission..!";
			}	
	       }
    		///////////////////////////////////////////////////////////
		    }
			else
			{
				if(count>1)
					status = "Try with Login ID  !!!";
				else
					status = "Invalid Login Id / email Id  !!!";
			}
			System.out.println("Mail Sending Done for Release Lock/Passwd for login: "+login);
    		msg = status;
    		url = "../admin/frm_admin_release_lock2.jsp?status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		
    		try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+emp_nm+"/"+session.getAttribute("ip")+"]: "+form_id+"@"+form_nm+"~: "+msg);
			}
			catch(Exception infoLogger)
			{
				//System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
    	}
    	catch(Exception exception)
    	{
    		dbcon.rollback();
    		status = "Failed To Release The Lock For The Selected User !!!";
    		url = "../admin/frm_admin_release_lock2.jsp?status="+status+"&formId="+formId+"&write_permission="+write_permission+"&delete_permission="+delete_permission+"&print_permission="+print_permission+"&approve_permission="+approve_permission+"&audit_permission="+audit_permission;
    		//System.out.println("Exception in Frm_admin Servlet : User_Release_Lock() Method... "+exception);
    		exception.printStackTrace();
    	}  
    }//End Of Method User_Release_Lock() ...
    //////////////////////////////////////////////////////////////
  

    
}//End Of Class Frm_admin ...