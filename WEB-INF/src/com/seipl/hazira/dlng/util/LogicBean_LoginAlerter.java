//Last Modified By Samik Shah On 21st March, 2011 ...
package com.seipl.hazira.dlng.util; 

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;
import java.util.HashMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
public class LogicBean_LoginAlerter
{
	HttpServletRequest request;
	Connection conn;
    Statement stmt,stmt1, stmt2, stmt3;
    ResultSet rset;
    public int numberofreminders;
    public String query;
    public String userName;
    public String userEmail;
    public String userMobNo;
    public String uname;
    public String userId;
    public String password;
    public String reset_flg; //Hiren_20201223
    public String passwordMain;
    public String methodName;
    public boolean userExist;
    public boolean validuser;
    public String today;
    public String new_string;
    public String todaytime;
    public String locked_flg;
    public String inactive_flg;
    public String user_cd;
    public String pdf_path="";
    public String msg="";
    public String msg2="";
    Vector reportListVec;
    Properties accessRightprop;
    HashMap hm = new HashMap();
    Properties propertyLock;
    
    ServletRequest req; //bp
    public String server_name = ""; //bp
    public String server_port = ""; //bp
    
    String MailMsg = "";	//MILAN20130516
    String MialID = "";		//MILAN20130516
	public LogicBean_LoginAlerter()
    {
        today = "";
        new_string = "";
        todaytime = "";
        locked_flg = "N";
        inactive_flg = "N";
        user_cd = "";
        pdf_path = "";
        reset_flg = "N";
        propertyLock = new Properties();
    }

    public void clear_variables()
    {
        numberofreminders = 0;
        query = "";
        userName = "";
        userId = "";
        password = "";
        passwordMain = "";
        methodName = "";
        pdf_path = "";
        validuser = false;
        userExist = false;
        reportListVec = new Vector(5, 2);
        accessRightprop = new Properties();
    }

    
    private void validateUser() throws SQLException
    {  
        methodName = "validateUser()";
        
        String current_date = "";
        String expiry_date = "";
        String reminder_date = "";
        String usercode ="";
        String nxt_date="";
        
        query = "SELECT TO_CHAR(sysdate,'DD/MM/YYYY') FROM DUAL";
        rset = stmt.executeQuery(query);
        if(rset.next())
        {
        	current_date = rset.getString(1);
        }
        //HA20190603 to get next year 
        String queryString1  = "select to_char(sysdate+365,'dd/mm/yyyy') from dual";
//        System.out.println("to get next year****"+queryString1);
        rset = stmt1.executeQuery(queryString1);
        if(rset.next())
        {
        	nxt_date = rset.getString(1);
        }
        
        
        int password_life_reminder_days = 0;
    	
    	String queryString = "select reminder_days from fms_password_life where eff_dt=(select max(eff_dt) from fms_password_life where eff_dt<=sysdate)"; 
    //   	System.out.println("Fetching No of Days for Password Life from FMS_PASSWORD_LIFE Table = "+queryString);
       	rset = stmt.executeQuery(queryString);
        if(rset.next())
        {
        	password_life_reminder_days = Integer.parseInt(rset.getString(1));						
        }
        
        //query = "select EMPLPASS_PASSWORD,EMPNAME,emplpass_locked_flag from empl_passwords,employee  where EMPLPASS_EMPID='" + userId + "' and EMPID=EMPLPASS_EMPID  MINUS  SELECT EMPLPASS_PASSWORD,EMPNAME,emplpass_locked_flag FROM EMPL_PASSWORDS,EMPLOYEE  WHERE EMPLPASS_EMPID = '" + userId + "' AND EMPID=EMPLPASS_EMPID AND del_flg = 'Y' ";
        query = "select A.PASSWORD, B.EMP_NM, A.LOCKED_FLAG, B.Email_id, B.Mob_no, A.EMP_CD, B.DEL_FLAG, TO_CHAR(A.EXPIRY_DATE,'DD/MM/YYYY'), RESET_FLAG " +
        	    "from SEC_EMP_PASSWORDS A,HR_EMP_MST B "+  
        	    "where B.EMP_ABR='"+userId+"' and A.EMP_CD=B.EMP_CD MINUS "+  
        	    "SELECT A.PASSWORD, B.EMP_NM, A.LOCKED_FLAG, B.Email_id, B.Mob_no, A.EMP_CD, B.DEL_FLAG, TO_CHAR(A.EXPIRY_DATE,'DD/MM/YYYY'), RESET_FLAG "+ 
        	    "FROM SEC_EMP_PASSWORDS A, HR_EMP_MST B WHERE "+ 
        	    "B.EMP_ABR='"+userId+"' AND A.EMP_CD=B.EMP_CD AND A.LOCKED_FLAG='Y'";
        
//        System.out.println("SAMIK --> QRY0004 :SELECT :HR_EMP_MST, SEC_EMP_PASSWORDS: LogicBean_LoginAlerter.java : "+query);
        rset = stmt.executeQuery(query);               
        
        if(rset.next())
        {
        	passwordMain = rset.getString(1);
            userExist = true;
            userName = rset.getString(2)==null?"":rset.getString(2);
            locked_flg = rset.getString(3)==null?"N":rset.getString(3);
            usercode = rset.getString(6)==null?"":rset.getString(6);
            if(usercode.trim().equals("") || usercode.trim().equals("0"))
            {
            	locked_flg = "Y";
            }
            userEmail = rset.getString(4)==null?"":rset.getString(4);
            userMobNo = rset.getString(5)==null?"":rset.getString(5);
            inactive_flg = rset.getString(7)==null?"N":rset.getString(7);
            expiry_date = rset.getString(8)==null?"":rset.getString(8);
            reset_flg = rset.getString(9)==null?"N":rset.getString(9);//Hiren_20201223
        }
        else
        {
            userEmail = "";
            userMobNo = "";
            userName = uname;
            inactive_flg = "N";
            
            query = "select A.LOCKED_FLAG,A.PASSWORD,RESET_FLAG " +
	    			"from SEC_EMP_PASSWORDS A,HR_EMP_MST B "+  
	    			"where B.EMP_ABR='"+userId+"' and " +
	    			"A.EMP_CD=B.EMP_CD";
            rset = stmt.executeQuery(query);               
    
            if(rset.next())
            {
            	locked_flg = rset.getString(1)==null?"N":rset.getString(1);
            	reset_flg = rset.getString(3)==null?"N":rset.getString(3);//Hiren_20201223 
            	if(locked_flg.trim().equalsIgnoreCase("Y"))
            	{
            		userExist = true;
            		passwordMain = rset.getString(2);
            	}
            	else
            	{
            		userExist = false;
            		passwordMain = "";
            	}
            }
            else
            {
            	locked_flg = "N";
            	userExist = false;
            	passwordMain = "";
            }
        }
        
        if(!expiry_date.trim().equals(""))
        {
        	query = "SELECT TO_CHAR(TO_DATE('"+expiry_date+"','DD/MM/YYYY')-"+password_life_reminder_days+",'DD/MM/YYYY') FROM DUAL";
            rset = stmt.executeQuery(query);
            if(rset.next())
            {
            	reminder_date = rset.getString(1);
            }
        }
        
        passwordMain = passwordMain != null ? passwordMain : "";
        StringBuffer stringbuffer = new StringBuffer();
        if(!passwordMain.equals(""))
        {
            StringBuffer stringbuffer1 = (new EncryptTest()).decrypt(passwordMain);
            new_string = stringbuffer1.toString();
		}
        else
        {
            new_string = "";
        }
     //  System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVV : " + password+" "+new_string);
        if(password.equals(new_string) && userExist)
        {
        	
            //userName = rset.getString(2);
            //userEmail= rset.getString(4)==null?"":rset.getString(4);
            //userMobNo = rset.getString(5)==null?"":rset.getString(5);
            userExist = true;
            if(inactive_flg.trim().equalsIgnoreCase("Y"))
            {
            	validuser = false;
            	msg = "In-Active User Not Permitted to Log-In into the System !!!";
            }
            else if(locked_flg.trim().equalsIgnoreCase("Y"))
            {
            	validuser = false;
            	msg = "Your Login has been locked, Kindly contact your Administrator !!!";
            }
            else if(password.equals(userId) || reset_flg.equals("Y"))
            {
            	validuser = false;
            	msg = "You need to change your PASSWORD compulsary, As your password has been RESET ...";
            }
            else if(!current_date.equals("") && !expiry_date.equals("") && current_date.equals(expiry_date))
            {
            	validuser = false;
            	msg = "You need to change your PASSWORD compulsary, As your password has EXPIRED ...";
            }
            else
            {
            	validuser = true;
            	msg = "Login";
            	// HA20190603 to allocate default group
            	queryString1="select count(*) from DLNG_EMP_GROUP_DTL where emp_cd='"+usercode+"' and group_cd='2' ";
//            	System.out.println(queryString1);
            	rset = stmt.executeQuery(queryString1);
            	if(rset.next()) {
            		
            		if(rset.getInt(1) == 0 ) {
	            		queryString = "insert into DLNG_EMP_GROUP_DTL (emp_cd,group_cd,from_dt,to_dt)"
	                			+ " values('"+usercode+"','2',to_date('"+current_date+"','dd/mm/yyyy'),to_date('"+nxt_date+"','dd/mm/yyyy'))";
	                	System.out.println("herere0333***"+queryString);
	                	stmt1.executeUpdate(queryString);
            		}
            	}
            	if(!current_date.equals("") && !reminder_date.equals("") && current_date.equals(reminder_date))
            	{
            		msg2 = "Your Password will Expire on "+expiry_date+", So change your Password before Expiry Date !!!";
            	}
            }
        }
        else if(userExist && !password.equals(new_string))
        {
            //userName = rset.getString(2);
        	//userEmail= rset.getString(4)==null?"":rset.getString(4);
        	//userMobNo = rset.getString(5)==null?"":rset.getString(5);
        	msg = "Wrong Password";
        }
        else
        {
            msg = "";
        }
        
     //   System.out.println("SAMIK --> writes MSG = "+msg);
    }

    
    private void setParameter() throws SQLException
    {
    	
        methodName = "setParameter()";
        query = "select to_char(sysdate,'dd/mm/yy')today, to_char(sysdate,'hh24:mi')todaytime from dual";
    //    System.out.println("QRY0005 :SELECT :dual: LogicBean_LoginAlerter.java : "+query);
        rset = stmt.executeQuery(query);
        if(rset.next())
        {
            today = rset.getString(1);
            todaytime = rset.getString(2);
        }
        
        query = " SELECT emp_cd, Email_id FROM HR_EMP_MST WHERE emp_abr='"+userId+"'";
    //    System.out.println("QRY0006 :SELECT :HR_EMP_MST: LogicBean_LoginAlerter.java : "+query);
        rset = stmt.executeQuery(query);
        if(rset.next())
        {
        	user_cd = rset.getString(1);
        	MialID = rset.getString(2);
        	//System.out.println("MILLLLLLLLLLLLLLLLL  "+ rset.getString(2)+" "+userId+" "+rset.getString(1));
        }
        
        //HttpSession session = request.getSession();
        //pdf_path=(String)session.getAttribute("pdf_path");
        //System.out.println("PDF Path in LogicBean_LoginAlerter = "+pdf_path);
    //    pdf_path = "D:\\FMS6\\fms\\pdf_reports";
    }

   
    private void getUserInfo() throws SQLException
    {
        methodName = "getUserInfo()";
        //query = " select GROUPFORM_FORM_ID,GROUPFORM_ALW_VIEW, GROUPFORM_ALW_DEL, GROUPFORM_ALW_UPD, GROUPFORM_ALW_GRNT,  GROUPFORM_ALW_PRINT, GROUPFORM_ALW_ADD  from GROUP_FORM x, EMPL_GROUP_DTL y  where x.GROUPFORM_GROUP_ID = y.EMPL_GROUP_ID and EMPL_EMPID='" + userId + "'";
        try
        {
	        query="select FORM_CD, ALW_VIEW, ALW_DEL, ALW_UPD, ALW_GRANT, ALW_PRINT, ALW_ADD,ALW_AUTHORIZE "+
	        	"from SEC_GROUP_FORM X, DLNG_EMP_GROUP_DTL Y,HR_EMP_MST Z,HR_EMP_MST A  " +
	        	" where X.GROUP_CD = Y.GROUP_CD AND  Y.EMP_CD=Z.EMP_CD AND Z.EMP_CD=A.EMP_CD AND A.EMP_ABR='"+userId+"' ";
//	        System.out.println("QRY0006 :SELECT :SEC_GROUP_FORM, DLNG_EMP_GROUP_DTL, HR_EMP_MST : LogicBean_LoginAlerter.java : "+query);
	        stmt = conn.createStatement();
	        stmt1 = conn.createStatement();
	        rset = stmt.executeQuery(query);
	        Vector vector = new Vector(5, 2);
	        String s = "";
	        String s2 = "";
	        String s1;
	        String s3;
	        for(; rset.next(); accessRightprop.setProperty(s1, s3))
	        {
	            s1 = rset.getString(1).trim();
	            s3 = rset.getString(2).trim();
	            s3 = s3 + rset.getString(3).trim();
	            s3 = s3 + rset.getString(4).trim();
	            s3 = s3 + rset.getString(5).trim();
	            s3 = s3 + rset.getString(6).trim();
	            s3 = s3 + rset.getString(7).trim();
	             
	        }
	
	        //query = " select FORMMAST_FORM_ID, FORMMAST_FORM_NAME, MODULEMAST_MODULE_NAME, FORMMAST_CLASS_PATH,FORMMAST_DOC_TYPE,FORMMAST_MODULE_CD  from FORMMAST,MODULEMAST where FORMMAST_MODULE_CD=MODULEMAST_MODULE_CD  order by FORMMAST_MODULE_CD,FORMMAST_DOC_TYPE,FORMMAST_FORM_ID";
	        query="select FORM_CD,FORM_NAME,MODULE_NAME, "+
	        "CLASSPATH,DOC_TYPE,A.MODULE_CD "+ 
	        "from SEC_FORM_MST A, SEC_MODULE_MST B where "+ 
	        "A.MODULE_CD=B.MODULE_CD "+ 
	        "order by A.MODULE_CD,DOC_TYPE,FORM_CD ";
	        //System.out.println("QRY0007 :SELECT :SEC_FORM_MST, SEC_MODULE_MST : LogicBean_LoginAlerter.java : "+query);
	        Properties properties;
	        
	        for(rset=stmt.executeQuery(query); rset.next(); reportListVec.add(properties))
	        {
	            String s4 = rset.getString(1);
	            String s5 = rset.getString(2);
	            String s7 = rset.getString(3);
	            String s8 = rset.getString(4);
	            String s9 = rset.getString(5);
	            String s6 = rset.getString(6);
	            properties = new Properties();
	            properties.setProperty("formId", s4);
	            properties.setProperty("reportName", s5);
	            properties.setProperty("moduleCode", s6);
	            properties.setProperty("moduleName", s7);
	            properties.setProperty("classPath", s8);
	            properties.setProperty("docType", s9);
	        }	        
        }
        catch(Exception e)
        {
        	System.out.println("Exception In "+methodName+" Method of LogicBean_LoginAlerter Databean:\n"+e.getMessage());
        	e.printStackTrace();
        }
    }
    
    
    public void init()
    {
        try
        {
            InitialContext initialcontext = new InitialContext();
            if(initialcontext == null)
                throw new Exception("Boom - No Context");
            Context context = (Context)initialcontext.lookup("java:/comp/env");
            //SB20091221 DataSource datasource = (DataSource)context.lookup(RuntimeConf.penom_database);
            DataSource datasource = (DataSource)context.lookup(RuntimeConf.security_database);
            if(datasource != null)
            {
                conn = datasource.getConnection();
                if(conn != null)
                {
                    stmt = conn.createStatement();
                    stmt1 = conn.createStatement();
                    stmt2 = conn.createStatement();
                    stmt3 = conn.createStatement();
                    validateUser();
					setParameter();
					getUserInfo();
					//sendFeedbackReminderMailEmployee(); // bp121016  rEMOVE COMMENT WHILE SENDING TO HLPL
					
					//sendFeedbackREminderMailSupervisor_new(); rEMOVE COMMENT WHILE SENDING TO HLPL
					conn.close();
                }
            }
        }
        catch(Exception exception)
        {
            System.out.println(" Exception in LogicBean_LoginAlerter In " + methodName + " \nquery " + query + " \nException :" + exception);
        }
    }
//  *******************bp121016*****************************
    public void sendFeedbackReminderMailEmployee(){ //bp121016
    	
    	String queryString="",currentDate="";
    	String empCdStr="",toStr="",cln_ref_no_str_emp="",remarkStr="",remarklink="";
    	String from="jayashree@barodainformatics.com",pwd="Bipl_123";
    	//String from="VISHAL.SONETA@SHELL.COM",pwd="khushi1";
    //	String from="vishal.malhotra@shell.com",pwd="khushi1";
    	String subject = "Feedback for Training : ";
    	
    	ArrayList cln_ref_no_temp_emp = new ArrayList();    	
    	ArrayList emp_cd_temp = new ArrayList();    	
    	ArrayList remark_temp=new ArrayList();    
    	ArrayList remark_asmt=new ArrayList();
    	ArrayList cln_ref_no_emp=new ArrayList();    	
    	ArrayList emp_cd=new ArrayList();    	
    	ArrayList remark=new ArrayList();
    	ArrayList remark_asmt_link=new ArrayList();
    	ArrayList email_id = new ArrayList();
    	ArrayList max_sent_date = new ArrayList();
    	int countEmpAdmin=0;
    	
    	String email_message = 
    	"\nHello, \n\n"+
    	"Please take a few minutes to complete this survey by following the link below. Your time and opinions will be appreciated. \n\n"+
    	"This message was sent by Hazira HR and is not a phishing mail. \n\n"+ 
    	"Steps for Training Feedback :- \n\n"+
    	"(1) Please click on enable link to activate the link.\n"+
    	"(2) \"Training Feedback Form\" with Name of Trainee and relevant Training details will be displayed.\n"+
    	"(3) Click on respective radio buttons to fill your feedback and 'Submit' your feedback form.\n\n"+
    	"Note :- Training feedback once submitted, cannot be modified.\n\n"+
    	"Thanks,\n";    	
    	
    	/*String email_message = "" +
    			"<HTML><head></head><body> first line <br>" +
    			"second line</body></HTML>";*/
		try {
			
			
			queryString = "SELECT COUNT(EMP_CD) FROM DLNG_EMP_GROUP_DTL A WHERE A.EMP_CD='"+user_cd+"' AND GROUP_CD='1' ";
		//	System.out.println("Checking for Admin Login....."+queryString);
			rset=stmt.executeQuery(queryString);
			if(rset.next())
			{
				countEmpAdmin=rset.getInt(1);
			}
			if(countEmpAdmin!=0)
			{
			queryString = "SELECT TO_CHAR(SYSDATE ,'DD/MM/YYYY')FROM DUAL";					
			ResultSet rs=null;
			
			rs = stmt.executeQuery(queryString);
			if (rs.next())
			{
				currentDate = rs.getString(1);
			}
			rs.close();			
	    	/*queryString="select cln_ref_no,emp_cd,remark from tims_email_dtl " +
	    			" where to_char(email_dt_next,'DD/MM/YYYY')='"+currentDate+"' and" +
	    					" flag='F' ";
	    */	
			
			//////////////////////////RG 23-04-2014 //////////////////
			
			/*int count=-1;
    		queryString = "select count(*) from asmt_email_dtl where cln_ref_no='"+cln_ref_no_temp_emp.get(i)+"'" +
    				" and email_dt_sent=to_date('"+currentDate+"','DD/MM/YYYY') and emp_cd="+emp_cd_temp.get(i)+" ";
    		ResultSet r1=stmt.executeQuery(queryString);
    		if(r1.next())
    			count=r1.getInt(1);
    		
    		if(count!=-1)
    		{
    			queryString = "select a.cln_ref_no,a.emp_cd,b.remark " +
   				" from tims_email_dtl a ,asmt_email_dtl b where a.emp_cd ="+emp_cd_temp.get(i)+
   				" and a.cln_ref_no = '"+cln_ref_no_temp_emp.get(i)+"' " +
   				"and  a.email_dt_sent =to_date('"+currentDate+"','DD/MM/YYYY') and a.cln_ref_no=b.cln_ref_no " +
   				" and a.emp_cd=b.emp_cd and a.email_dt_sent=b.email_dt_sent ";
    		}*/
			
	    	
	    	//System.out.println(queryString);
	    	
	    	
	    	queryString="select a.cln_ref_no,a.emp_cd,a.remark,c.remark from tims_email_dtl a,asmt_email_dtl c " +
			"where to_char(a.email_dt_next,'DD/MM/YYYY')='"+currentDate+"' and " +
			"a.flag='F' and a.emp_cd not in (select distinct(emp_cd) from hr_trn_fb_dtl b " +
			"where b.ref_no=a.cln_ref_no) and a.cln_ref_no=c.cln_ref_no and a.emp_cd=c.emp_cd ";
	    	rs = stmt.executeQuery(queryString);
	    	
	    	while(rs.next())
	    	{
	    		//MILAN20130701
	    		query = "SELECT COUNT(*) FROM  " +
				"(select min(email_dt_sent) as DT1 from tims_email_dtl where cln_ref_no = '"+rs.getString(1).trim()+"' and emp_cd = '"+rs.getString(2).trim()+"' and flag='F') A , " +
				"(select max(email_dt_sent) AS DT1 from tims_email_dtl where cln_ref_no = '"+rs.getString(1).trim()+"' and emp_cd = '"+rs.getString(2).trim()+"' and flag='F') B " +
				"WHERE TO_DATE(A.DT1, 'DD/MM/YYYY')+90 >= TO_DATE(B.DT1, 'DD/MM/YYYY')";
	    		ResultSet rs1 = stmt1.executeQuery(query);
	    		if(rs1.next() && rs1.getInt(1)>0)
	    		{
	    			query = "select COUNT(*) from tims_email_dtl where cln_ref_no = '"+rs.getString(1).trim()+"' and emp_cd = '"+rs.getString(2).trim()+"' " +
					"and flag='F' and (remark is null or remark like '')";
	    			ResultSet rs2 = stmt2.executeQuery(query);
					if(rs2.next() && rs2.getInt(1) == 0)
					{
						cln_ref_no_temp_emp.add(rs.getString(1));
			    		emp_cd_temp.add(rs.getString(2));	    
			    		remark_temp.add(rs.getString(3));
			    		remark_asmt.add(rs.getString(4));
					}
		    		rs2.close();
	    		}
	    		rs1.close();
    			//MILAN20130701
	    	}
	    	
	    	//	System.out.println("in else if======================");
	    		queryString="select a.cln_ref_no,a.emp_cd,a.remark from tims_email_dtl a " +
				"where to_char(email_dt_next,'DD/MM/YYYY')='"+currentDate+"' and " +
				"flag='F' and a.emp_cd not in (select distinct(emp_cd) from hr_trn_fb_dtl b " +
				"where b.ref_no=a.cln_ref_no)  ";
	    	//	System.out.println("fetching list===="+queryString);
	    		rs=stmt.executeQuery(queryString);
	    		while(rs.next())
		    	{
	    			int avil=0;
		    		//MILAN20130701
		    		query = "SELECT COUNT(*) FROM  " +
					"(select min(email_dt_sent) as DT1 from tims_email_dtl where cln_ref_no = '"+rs.getString(1).trim()+"' and emp_cd = '"+rs.getString(2).trim()+"' and flag='F') A , " +
					"(select max(email_dt_sent) AS DT1 from tims_email_dtl where cln_ref_no = '"+rs.getString(1).trim()+"' and emp_cd = '"+rs.getString(2).trim()+"' and flag='F') B " +
					"WHERE TO_DATE(A.DT1, 'DD/MM/YYYY')+90 >= TO_DATE(B.DT1, 'DD/MM/YYYY')";
		    		ResultSet rs1 = stmt1.executeQuery(query);
		    		if(rs1.next() && rs1.getInt(1)>0)
		    		{
		    			query = "select COUNT(*) from tims_email_dtl where cln_ref_no = '"+rs.getString(1).trim()+"' and emp_cd = '"+rs.getString(2).trim()+"' " +
						"and flag='F' and (remark is null or remark like '')";
		    			ResultSet rs2 = stmt2.executeQuery(query);
						if(rs2.next() && rs2.getInt(1) == 0)
						{
							
							for(int j=0;j<cln_ref_no_temp_emp.size();j++)
							{
								if(cln_ref_no_temp_emp.get(j).equals(rs.getString(1))) 
								{
									if(emp_cd_temp.get(j).equals(rs.getString(2)))
									{
										avil++;
										break;
									}
								}
							}
					//		System.out.println("value of avail====="+avil+"and"+rs.getString(1)+"amnd"+rs.getString(2));
							if(avil==0)
							{
								cln_ref_no_temp_emp.add(rs.getString(1));
					    		emp_cd_temp.add(rs.getString(2));	    
					    		remark_temp.add(rs.getString(3));
					    		remark_asmt.add(rs.getString(3));
							}
							
						}
			    		rs2.close();
		    		}
		    	}
	    	
	    	
	    	for (int i=0;i<emp_cd_temp.size();i++)
	    	{
	    		
		    	queryString = "select cln_ref_no,emp_cd,remark " +
			   				" from tims_email_dtl where emp_cd ="+emp_cd_temp.get(i)+" "+
			   				" and cln_ref_no = '"+cln_ref_no_temp_emp.get(i)+"' " +
			   				" and  email_dt_sent =to_date('"+currentDate+"','DD/MM/YYYY') " ;
			   
	    	//	System.out.println("queryString for final emp list:"+queryString);
	    		rs = stmt.executeQuery(queryString);
	    		if(!(rs.next()))
	    		{ 
	    		// 	System.out.println("first time should come here");	    			
	    			cln_ref_no_emp.add(cln_ref_no_temp_emp.get(i));
	    			emp_cd.add(emp_cd_temp.get(i));
	    			remark.add(remark_temp.get(i));
	    			remark_asmt_link.add(remark_asmt.get(i));
	    		}	    		
	    	}    	   	
	    	for(int i=0;i<emp_cd.size();i++)
	    	{
	    		queryString="select email_id from hr_emp_mst" +
    			" where emp_cd="+emp_cd.get(i);
	    		//System.out.println("query for fetching email id:"+queryString);
	    		rs = stmt.executeQuery(queryString);
		    	if(rs.next())
		    	{
		    		email_id.add(rs.getString(1));
		    	}
	    	}

	    	for (int i=0;i<emp_cd.size();i++)
	    	{
	    		if(i==0)
	    		{
	    			empCdStr = (String)emp_cd.get(i);
	    			toStr = (String)email_id.get(i); 
	    			cln_ref_no_str_emp = (String)cln_ref_no_emp.get(i);
	    			remarkStr = (String)remark.get(i);
	    			remarklink = (String)remark_asmt_link.get(i);
	    		}
	    		else
	    		{
	    			empCdStr = empCdStr +","+emp_cd.get(i);
		    		toStr = toStr +","+email_id.get(i); 		
		    		cln_ref_no_str_emp = cln_ref_no_str_emp +","+cln_ref_no_emp.get(i);
		    		remarkStr = remarkStr +","+remark.get(i);
		    		remarklink = remarklink +","+remark_asmt_link.get(i);
	    		}
	    	}
	    	//toStr = "milan@barodainformatics.com";
	   // 	System.out.println("empCdStr:"+empCdStr);
	   // 	System.out.println("toStr:"+toStr);
	   // 	System.out.println("cln_ref_no_str:"+cln_ref_no_str_emp);
	   // 	System.out.println("remarkStr:"+remarkStr);
	    	//need to fetch training name for setting in subject line    	 
	  //  	System.out.println("vlaue of reamrks str"+remarklink);
	   // 	System.out.println("==========="+remarkStr);
		} 
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		if(countEmpAdmin!=0)
		{
		URL url;
		try 
		{			
				url =url = new URL("http://"+server_name+":"+server_port+"/TIMS/servlet/Frm_Mail_Send");;
				URLConnection conn = url.openConnection();
			   	conn.setDoOutput(true);			  
			   	String data = URLEncoder.encode("link_a", "UTF-8") + "=" + URLEncoder.encode(remarkStr, "UTF-8");
			   	data += "&" + URLEncoder.encode("link_aa","UTF-8") + "=" + URLEncoder.encode(remarklink, "UTF-8");
			    data += "&" + URLEncoder.encode("emp_cd", "UTF-8") + "=" + URLEncoder.encode(empCdStr, "UTF-8");
			    data += "&" + URLEncoder.encode("feedback", "UTF-8") + "=" + URLEncoder.encode("feedback", "UTF-8");
			    data += "&" + URLEncoder.encode("email_message", "UTF-8") + "=" + URLEncoder.encode(email_message, "UTF-8");
			    data += "&" + URLEncoder.encode("cln_ref_no", "UTF-8") + "=" + URLEncoder.encode(cln_ref_no_str_emp, "UTF-8");
			    data += "&" + URLEncoder.encode("subject", "UTF-8") + "=" + URLEncoder.encode(subject, "UTF-8");
			    data += "&" + URLEncoder.encode("To", "UTF-8") + "=" + URLEncoder.encode(toStr, "UTF-8");
			    data += "&" + URLEncoder.encode("cc", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
			    data += "&" + URLEncoder.encode("bcc", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
			    data += "&" + URLEncoder.encode("From", "UTF-8") + "=" + URLEncoder.encode(from, "UTF-8");
			    data += "&" + URLEncoder.encode("pwd", "UTF-8") + "=" + URLEncoder.encode(pwd, "UTF-8");
			    data += "&" + URLEncoder.encode("remindflg", "UTF-8") + "=" + URLEncoder.encode("R","UTF-8");
			    
			    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			    wr.write(data);
			    wr.flush();
			    
			    // Get the response
			    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			    String line;
			    while ((line = rd.readLine()) != null) 
			    {
			    	//System.out.println("result:"+line);
					if(MialID.trim().equalsIgnoreCase(from))
					{
						MailMsg = line;
					}
					else
					{
						MailMsg = "";
					}
			    }
			    wr.close();
			    rd.close();
			 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 	
		}
    }

    public void sendFeedbackREminderMailSupervisor()
    {
   // 	System.out.println("Entered in sendFeedbackReminderMailSupervisor function.");
    	String queryString="";
    	String currentDate ="";
    	ArrayList cln_ref_no_temp_sup = new ArrayList();
    	ArrayList sup_cd_temp = new ArrayList();
    	
    	ArrayList emp_nm_temp = new ArrayList();
    	ArrayList cln_ref_no_sup = new ArrayList();
    	ArrayList sup_cd = new ArrayList();
    	ArrayList emp_nm = new ArrayList();
    	
    	ArrayList sup_nm = new ArrayList();
    	ArrayList email_id= new ArrayList();
    	ArrayList trng_nm = new ArrayList();
    	ArrayList subject = new ArrayList();
    	ArrayList ref_no = new ArrayList();
    	ArrayList trng_dt = new ArrayList();
    	ArrayList emp = new ArrayList();
    	String email_message = "";
    	String temp_sprv = "";
    	String toStr = "",supCdStr="";
    	//String from="jayashree@barodainformatics.com",pwd="bar123";    	
    	//String from="VISHAL.SONETA@SHELL.COM",pwd="khushi1";
    	String from="vishal.malhotra@shell.com",pwd="khushi1";
    	try {
    		queryString = "SELECT TO_CHAR(SYSDATE ,'DD/MM/YYYY')FROM DUAL";					
    		ResultSet rs=null;
    		rs = stmt.executeQuery(queryString);
    		
    		if (rs.next()){
    			currentDate = rs.getString(1);
    		}
    		rs.close();			
    		System.out.println("currentDate:"+currentDate);	
    		
    		queryString = "select distinct cln_ref_no from tims_email_dtl " +
    				" where email_dt_next=to_date('"+currentDate+"','dd/mm/yyyy') and flag='S'";
    		
    		System.out.println("query for today's cln_ref_no"+queryString);
    		rs = stmt.executeQuery(queryString);
    		while(rs.next()){
    			ref_no.add(rs.getString(1));
    		}
    		rs.close();  
    		//System.out.println("ref_no length:"+ref_no.size());
    		
    		email_message =
    			"\nFollowing employees of your team have undergone training on (training name)in the month of (month)"+  
    			"\nYou are requested to fill up the attached form and send back in soft copy.";
    				
    		
    		for (int a=0;a<ref_no.size();a++){
    			queryString="select cln_ref_no,sup_cd,remark from tims_email_dtl " +
    			" where email_dt_next=to_date('"+currentDate+"','dd/mm/yyyy') and" +
    			" flag='S'";
		    	//System.out.println("queryString email_detail supervisor:"+queryString);
		    	
		    	rs = stmt.executeQuery(queryString);
		    	while(rs.next()){
		    		cln_ref_no_temp_sup.add(rs.getString(1));
		    		sup_cd_temp.add(rs.getString(2));
		    		emp_nm_temp.add(rs.getString(3));
		    		
		    		//System.out.println("emp_nm_temp:"+emp_nm_temp);
		    	}
		    	rs.close();
		    	
		    	//check whether email is already sent to supervisor today 
		    	for (int i=0;i<sup_cd_temp.size();i++){
		    		queryString = "select cln_ref_no,sup_cd" +
		    				" from tims_email_dtl where sup_cd ="+sup_cd_temp.get(i)+
		    				" and cln_ref_no = '"+cln_ref_no_temp_sup.get(i)+"' " +
		    				"and  email_dt_sent =to_date('"+currentDate+"','DD/MM/YYYY')";
		    		//System.out.println("queryString for final emp list:"+queryString);
		    		rs = stmt.executeQuery(queryString);
		    		if(!(rs.next())){ 
		    		//	System.out.println("first time should come here");	    			
		    			cln_ref_no_sup.add(cln_ref_no_temp_sup.get(i));
		    			sup_cd.add(sup_cd_temp.get(i));
		    			emp_nm.add(emp_nm_temp.get(i));
		    			
		    			//System.out.println("emp_nm:"+emp_nm);
		    		}	
		    		rs.close();
		    	}       	
		    	
	    		email_message += "\n\nThanks,";
		    	
		    	//fetching sup name and email id
		    	for(int i=0;i<sup_cd.size();i++){
		    		
		    		queryString="select emp_nm,email_id from hr_emp_mst" +
					" where sup_cd="+sup_cd.get(i);
		    		//System.out.println("query for fetching email id sup:"+queryString);
		    		rs = stmt.executeQuery(queryString);
			    	if(rs.next()){ 
			    		sup_nm.add(rs.getString(1));
			    		email_id.add(rs.getString(2));		    		
			    	}
			    	rs.close();
		    	}
		   // 	System.out.println("email_id size:"+email_id.size());	
		    	
		    	//fetching tranning name and creating subject line
		    	for(int i=0;i<cln_ref_no_sup.size();i++){
		    		queryString = "select trng_nm from hr_trang_mst where trng_cd=" +
		    				"(select trng_cd from tims_trng_conduct where cln_ref_no='"+cln_ref_no_sup.get(i)+"')";
		    		rs = stmt.executeQuery(queryString);
		    		if (rs.next()){
		    			trng_nm.add(rs.getString(1));
		    			subject.add("Training Effectiveness - "+rs.getString(1));
		    		}
		    		else{
		    			trng_nm.add("-");
		    			subject.add("Training Effectiveness - ");
		    		}
		    		rs.close();
		    	}
		    
		    	//fetching training date
		    	for(int i=0;i<cln_ref_no_sup.size();i++){
		    		queryString = "select to_char(from_dt,'Month') from tims_trng_conduct where cln_ref_no ='"+cln_ref_no_sup.get(i)+"'";
		    	//	System.out.println("fetching training date:"+queryString);
		    		rs = stmt.executeQuery(queryString);
		    		if (rs.next()){
		    			trng_dt.add(rs.getString(1));
		    		//	System.out.println("trng_dt:"+rs.getString(1));
		    		}
		    		rs.close();
		    	}	    	
		    	
		    	temp_sprv = "";
		    	supCdStr= "";
		    	for(int i=0;i<cln_ref_no_sup.size();i++){
		    		if (temp_sprv.equals("")){
		    			temp_sprv = sup_nm.get(i).toString()+"$"+emp_nm.get(i).toString();
		    		}
		    		else{
		    			temp_sprv = temp_sprv + "~" + sup_nm.get(i).toString()+"$"+emp_nm.get(i).toString();
		    		}
		    	}
		    	
		    	//creating string for email address
		    	for (int i=0;i<email_id.size();i++){
		    		if (toStr.equals("")){
		    			toStr = email_id.get(i).toString();
		    			supCdStr = sup_cd.get(i).toString();
		    		}
		    		else{
		    			toStr = toStr+","+email_id.get(i).toString();
		    			supCdStr = supCdStr + ","+sup_cd.get(i).toString();
		    		}
		    	}
    	
    		}        	
        	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		URL url;
		try {			
				url = new URL("http://"+server_name+":"+server_port+"/TIMS/servlet/Frm_Mail_Send_Mail");
				//System.out.println("url == "+url);
				URLConnection conn = url.openConnection();
			   	//System.out.println("URLConnection:"+conn);
			   	conn.setDoOutput(true);	
			   	for (int i=0;i<ref_no.size();i++){
			   		
			   	  	String data = URLEncoder.encode("cln_ref_no", "UTF-8") + "=" + URLEncoder.encode(ref_no.get(i).toString(), "UTF-8");
			   	  	data += "&" + URLEncoder.encode("suprv", "UTF-8") + "=" + URLEncoder.encode(temp_sprv, "UTF-8");
			   	  	data += "&" + URLEncoder.encode("To", "UTF-8") + "=" + URLEncoder.encode(toStr, "UTF-8");
			   	    data += "&" + URLEncoder.encode("cc", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
				    data += "&" + URLEncoder.encode("bcc", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
				    data += "&" + URLEncoder.encode("From", "UTF-8") + "=" + URLEncoder.encode(from, "UTF-8");
				    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pwd, "UTF-8");
				    data += "&" + URLEncoder.encode("email_message", "UTF-8") + "=" + URLEncoder.encode(email_message, "UTF-8");			    
				    data += "&" + URLEncoder.encode("subject", "UTF-8") + "=" + URLEncoder.encode(subject.get(i).toString(), "UTF-8");
				    data += "&" + URLEncoder.encode("sup_cd", "UTF-8") + "=" + URLEncoder.encode(supCdStr, "UTF-8");
				    data += "&" + URLEncoder.encode("trng", "UTF-8") + "=" + URLEncoder.encode(trng_nm.get(i).toString(), "UTF-8");
				    data += "&" + URLEncoder.encode("Smonth", "UTF-8") + "=" + URLEncoder.encode(trng_dt.get(i).toString(), "UTF-8");
				    data += "&" + URLEncoder.encode("reminder", "UTF-8") + "=" + URLEncoder.encode("reminder", "UTF-8");
				   
				    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
				    wr.write(data);
				    wr.flush();
				    
				    // Get the response
				    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				    String line;
				    while ((line = rd.readLine()) != null) {
				    	// System.out.println("result from Frm_Mail_Send_Mail class..:"+line);
				     }
				     
					 wr.close();
				     rd.close();
			   	}			 
		   	 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
    }
//  *******************bp121016*****************************     
    public void sendFeedbackREminderMailSupervisor_new(){
   // 	System.out.println("Entered in sendFeedbackReminderMailSupervisor functionnew.");
    	//MILAN20130516
    	String email_message = "";    	
    	String supCdStr="";
    //	String from="jayashree@barodainformatics.com",pwd="Bipl_123";    	
    	String from="vishal.malhotra@shell.com",pwd="khushi1";
    	String queryString="";
    	String currentDate ="";
    	int countEmpAdmin=0;
    	
    	Vector v_ref_no = new Vector();    	    	
    	try
    	{
    		
    		queryString = "SELECT COUNT(EMP_CD) FROM DLNG_EMP_GROUP_DTL A WHERE A.EMP_CD='"+user_cd+"' AND GROUP_CD='1' ";
		//	System.out.println("Checking for Admin Login....."+queryString);
			rset=stmt.executeQuery(queryString);
			if(rset.next())
			{
				countEmpAdmin=rset.getInt(1);
			}
			
			if(countEmpAdmin!=0)
			{
		//		System.out.println("Entered in sendFeedbackReminderMailSupervisor functionnew.....");
    		queryString = "SELECT TO_CHAR(SYSDATE ,'DD/MM/YYYY')FROM DUAL";					
    		ResultSet rs=null;
    		rs = stmt.executeQuery(queryString);
    		
    		if (rs.next()){
    			currentDate = rs.getString(1);
    		}
    		rs.close();			
    		queryString = "select distinct cln_ref_no from tims_email_dtl " +
    		" where email_dt_next=to_date('"+currentDate+"','dd/mm/yyyy') and flag='S'";
    		//System.out.println("query for today's cln_ref_no::"+queryString);
    		rs = stmt.executeQuery(queryString);
    		while(rs.next()){
    			v_ref_no.add(rs.getString(1));
    		}
    		rs.close();  
    		for (int a=0;a<v_ref_no.size();a++){
    			String subject = "";
    			String trng_nm = "";   	
    			String trng_dt = "";    	
    			String remark_str = "";
    			String emp_cd_str="";
    			String emp_dept_cd_str="";
    			String suprv = "";
    			String To = "";
    			Vector v_sup_cd_temp = new Vector();
    			Vector v_emp_cd_temp = new Vector();
    			
    			queryString="select DISTINCT sup_cd from tims_email_dtl"+
    			" where email_dt_next=to_date('"+currentDate+"','dd/mm/yyyy') and"+
    			" flag='S' and cln_ref_no = '"+v_ref_no.elementAt(a)+"' ";
    			//System.out.println("email_detail supervisor temp:"+queryString);
    			rs = stmt.executeQuery(queryString);
    			while(rs.next())
    			{		    		
    				Vector temp_emp_cd = new Vector();
    				String query = "select DISTINCT a.emp_cd from tims_email_dtl a"+
    				" where a.email_dt_next=to_date('"+currentDate+"','dd/mm/yyyy')"+
    				" and a.flag='S' and a.cln_ref_no = '"+v_ref_no.elementAt(a)+"' " +
    				" and a.sup_cd = "+rs.getString(1)+" and a.emp_cd not in (select distinct(emp_cd) from hr_trn_fb_dtl b " +
    				" where b.ref_no=a.cln_ref_no ) ";
    				
    				ResultSet rs1 = stmt1.executeQuery(query);
    				while(rs1.next())
    				{
    					query = "select COUNT(*) from tims_email_dtl where cln_ref_no = '"+v_ref_no.elementAt(a)+"' " +
    					"and emp_cd = '"+rs1.getString(1).trim()+"' " +
    					"and flag='S' and (remark is null or remark like '')";
    					ResultSet rs2 = stmt2.executeQuery(query);
    					if(rs2.next() && rs2.getInt(1) == 0)
    					{
    						/*query = "select DISTINCT emp_cd from tims_email_dtl"+
    						 " where email_dt_next=to_date('"+currentDate+"','dd/mm/yyyy')"+
    						 " and flag='S' and cln_ref_no = '"+v_ref_no.elementAt(a)+"' " +
    						 " and sup_cd = "+rs.getString(1);
    						 
    						 ResultSet rs3 = stmt3.executeQuery(query);
    						 while(rs3.next()){
    						 temp_emp_cd.add(rs3.getString(1));
    						 }	*/	    
    						temp_emp_cd.add(rs1.getString(1));
    						v_sup_cd_temp.add(rs.getString(1));		    		
    						v_emp_cd_temp.add(temp_emp_cd);
    					}
    					rs2.close();	
    				}
    				rs1.close();	
    			}		    	
    			rs.close();	
    			
    			Vector v_sup_cd = new Vector();
    			
//  			check whether email is already sent to supervisor today 
    			for(int j=0;j<v_sup_cd_temp.size();j++)
    			{
    				boolean flag = false;
    				for(int b=0;b<((Vector)v_emp_cd_temp.elementAt(j)).size();b++)
    				{
    					queryString = "select distinct cln_ref_no,sup_cd" +
    					" from tims_email_dtl " +
    					" where sup_cd ="+v_sup_cd_temp.elementAt(j)+
    					" and cln_ref_no = '"+v_ref_no.elementAt(a)+"' " +
    					"and  email_dt_sent =to_date('"+currentDate+"','DD/MM/YYYY')" +
    					" and emp_cd="+((Vector)v_emp_cd_temp.elementAt(j)).elementAt(b);
    					
    					System.out.println("final emp list:"+queryString);
    					
    					rs = stmt.executeQuery(queryString);
    					//System.out.println("checking:"+queryString);
    					
    					if(!(rs.next())){ 
    						flag = true;		    			
    					}	
    					rs.close();
    				}
    				if(flag)
    				{
    					v_sup_cd.add(v_sup_cd_temp.elementAt(j));	
    				}
    			}
    			
    			for(int j=0;j<v_sup_cd.size();j++){
    				
    				if(j>0){		    			
    					emp_cd_str += "~";
    					emp_dept_cd_str += "~";
    				}	    		
    				
    				queryString="select emp_nm,email_id from hr_emp_mst" +
    				" where emp_cd="+v_sup_cd.elementAt(j);
    				
    				//System.out.println("fetching email id sup:"+queryString);
    				rs = stmt.executeQuery(queryString);
    				if(rs.next()){ 
    					
    					if(suprv.equals("")){
    						suprv = rs.getString(1).replaceAll(","," ");
    					}else{
    						suprv += "~"+rs.getString(1).replaceAll(","," ");
    					}
    					
    					if(To.equals("")){
    						To = rs.getString(2);	
    						supCdStr = v_sup_cd.elementAt(j).toString();
    					}else{
    						To += ","+rs.getString(2);
    						supCdStr += "," + v_sup_cd.elementAt(j).toString();
    					}			    			    		
    				}
    				//To = "milan@barodainformatics.com";
    				rs.close();       			
    				
    				queryString = "select emp_cd, remark from tims_email_dtl"+
    				"	WHERE cln_ref_no = '"+v_ref_no.elementAt(a)+"' "+
    				"	and email_dt_next = to_date('"+currentDate+"','DD/MM/YYYY')"+
    				"	and sup_cd = '"+v_sup_cd.elementAt(j)+"'";
    				
    				// System.out.println("query : : "+queryString);
    				rs = stmt.executeQuery(queryString);
    				while(rs.next()){
    					
    					//String sql = "select emp_nm from hr_emp_mst where emp_cd="+rs.getString(1);
    					String sql = "select em.emp_nm , rm.dept_cd "+
    					" from hr_emp_mst em, hr_emp_rel_mst rm"+ 
    					" where em.emp_cd = rm.emp_cd  "+
    					" AND em.emp_cd="+rs.getString(1);
    					
    					ResultSet empNm =  stmt1.executeQuery(sql);
    					if(empNm.next()){		    	    		
    						suprv += "$"+empNm.getString(1);
    						emp_dept_cd_str += ","+empNm.getString(2);
    					}
    					emp_cd_str += ","+rs.getString(1).replaceAll(","," ");
    					remark_str += "~"+rs.getString(2);
    				}		    	    
    				rs.close();    		
    				
    			}
    	//		System.out.println("ref: "+v_ref_no.elementAt(a));
    	//		System.out.println("sup: "+v_sup_cd);
    	//		System.out.println("emp_cd_str: "+emp_cd_str); 
    	//		System.out.println("emp_dept_cd_str: "+emp_dept_cd_str);
    	//		System.out.println("suprv: "+suprv);
    	//		System.out.println("To: "+To);
    			
    			queryString = "select trng_nm from hr_trang_mst where trng_cd=" +
    			"(select trng_cd from tims_trng_conduct where cln_ref_no='"+v_ref_no.elementAt(a)+"')";
    			
    			rs = stmt.executeQuery(queryString);
    			if (rs.next()){
    				trng_nm = rs.getString(1);
    				subject = "Training Effectiveness - "+rs.getString(1);
    			}
    			else{
    				trng_nm = "-";
    				subject = "Training Effectiveness - ";
    			}
    			rs.close();			
    			
    //			System.out.println("value of servaer name an dport==="+server_port+"abd"+server_name);
    //			System.out.println("About to move on frm_mail_send_mail.java=============");
    			
    			URL url;
    			url = new URL("http://"+server_name+":"+server_port+"/TIMS/servlet/Frm_Mail_Send_Mail");
    			//System.out.println("url == "+url);
    			URLConnection conn = url.openConnection();
    			//System.out.println("URLConnection:"+conn);
    			conn.setDoOutput(true);	
    			
    			String data = URLEncoder.encode("cln_ref_no", "UTF-8") + "=" + URLEncoder.encode(v_ref_no.elementAt(a).toString(), "UTF-8");
    			data += "&" + URLEncoder.encode("suprv", "UTF-8") + "=" + URLEncoder.encode(suprv, "UTF-8");
    			data += "&" + URLEncoder.encode("To", "UTF-8") + "=" + URLEncoder.encode(To, "UTF-8");
    			data += "&" + URLEncoder.encode("cc", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
    			data += "&" + URLEncoder.encode("bcc", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
    			data += "&" + URLEncoder.encode("From", "UTF-8") + "=" + URLEncoder.encode(from, "UTF-8");
    			data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pwd, "UTF-8");
    			data += "&" + URLEncoder.encode("email_message", "UTF-8") + "=" + URLEncoder.encode(email_message, "UTF-8");			    
    			data += "&" + URLEncoder.encode("subject", "UTF-8") + "=" + URLEncoder.encode(subject, "UTF-8");
    			data += "&" + URLEncoder.encode("sup_cd", "UTF-8") + "=" + URLEncoder.encode(supCdStr, "UTF-8");
    			data += "&" + URLEncoder.encode("trng", "UTF-8") + "=" + URLEncoder.encode(trng_nm, "UTF-8");
    			data += "&" + URLEncoder.encode("Smonth", "UTF-8") + "=" + URLEncoder.encode(trng_dt, "UTF-8");
    			data += "&" + URLEncoder.encode("reminder", "UTF-8") + "=" + URLEncoder.encode("reminder", "UTF-8");
    			data += "&" + URLEncoder.encode("emp_cd_str", "UTF-8") + "=" + URLEncoder.encode(emp_cd_str, "UTF-8");
    			data += "&" + URLEncoder.encode("emp_dept_cd_str", "UTF-8") + "=" + URLEncoder.encode(emp_dept_cd_str, "UTF-8");
    			
    			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
    			wr.write(data);
    			wr.flush();
    			
    			// Get the response
    			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    			String line;
    			while ((line = rd.readLine()) != null) {
    				System.out.println("MILAN ======================= :"+line);
    			}
    			
    			wr.close();
    			rd.close();
    			
    		}
			}
    	}
    	/*catch (At e) 
    	{
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}*/
    	catch (SQLException e) 
    	{
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	URL url;
    	try {/*			
    	url = new URL("http://"+server_name+":"+server_port+"/TIMS/servlet/Frm_Mail_Send_Mail");
    	//System.out.println("url == "+url);
    	 URLConnection conn = url.openConnection();
    	 //System.out.println("URLConnection:"+conn);
    	  conn.setDoOutput(true);	
    	  for (int i=0;i<ref_no.size();i++){
    	  
    	  String data = URLEncoder.encode("cln_ref_no", "UTF-8") + "=" + URLEncoder.encode(ref_no.get(i).toString(), "UTF-8");
    	  data += "&" + URLEncoder.encode("suprv", "UTF-8") + "=" + URLEncoder.encode(temp_sprv, "UTF-8");
    	  data += "&" + URLEncoder.encode("To", "UTF-8") + "=" + URLEncoder.encode(toStr, "UTF-8");
    	  data += "&" + URLEncoder.encode("cc", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
    	  data += "&" + URLEncoder.encode("bcc", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8");
    	  data += "&" + URLEncoder.encode("From", "UTF-8") + "=" + URLEncoder.encode(from, "UTF-8");
    	  data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pwd, "UTF-8");
    	  data += "&" + URLEncoder.encode("email_message", "UTF-8") + "=" + URLEncoder.encode(email_message, "UTF-8");			    
    	  data += "&" + URLEncoder.encode("subject", "UTF-8") + "=" + URLEncoder.encode(subject.get(i).toString(), "UTF-8");
    	  data += "&" + URLEncoder.encode("sup_cd", "UTF-8") + "=" + URLEncoder.encode(supCdStr, "UTF-8");
    	  data += "&" + URLEncoder.encode("trng", "UTF-8") + "=" + URLEncoder.encode(trng_nm.get(i).toString(), "UTF-8");
    	  data += "&" + URLEncoder.encode("Smonth", "UTF-8") + "=" + URLEncoder.encode(trng_dt.get(i).toString(), "UTF-8");
    	  data += "&" + URLEncoder.encode("reminder", "UTF-8") + "=" + URLEncoder.encode("reminder", "UTF-8");
    	  
    	  OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
    	  wr.write(data);
    	  wr.flush();
    	  
    	  // Get the response
    	   BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    	   String line;
    	   while ((line = rd.readLine()) != null) {
    	   // System.out.println("result from Frm_Mail_Send_Mail class..:"+line);
    	    }
    	    
    	    wr.close();
    	    rd.close();
    	    }			 
    	    
    	    */} catch (Exception e) {
    	    	// TODO Auto-generated catch block
    	    	e.printStackTrace();
    	    } 
    	    
    	    
    }
     
    
    
    public void MakeMailDirectory()
    {
    	try
    	{	
    		String e = request.getRealPath("/mail");
    		//System.out.println("EEEEEEE ::: "+ e);
    		if(e==null)
    		{
    			File directory = new File(e);
    			if(!directory.exists())
    			{
    				//System.out.println("Directory Created With name \"mail\"");
    				directory.mkdir();
    			}
    		}	
    	}
    	catch(Exception e1)
    	{
    		e1.printStackTrace();
    	} 
    }   
    
    
    /*public int getNumberofreminders()
    {
        return numberofreminders;
    }

    public void setNumberofreminders(int i)
    {
        numberofreminders = i;
    }
*/
    public String getToday()
    {
        return today;
    }

    public String getUserId()
    {
        return userId;
    }

    public String getUser_CD()
    {
        return user_cd;
    }
    
    public String getPassword()
    {
        return password;
    }

    public String getTodaytime()
    {
        return todaytime;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getNewString()
    {
        return new_string;
    }

    public boolean getUserExist()
    {
        return userExist;
    }

    public boolean getValiduser()
    {
        return validuser;
    }

    public Vector getReportListVec()
    {
        return reportListVec;
    }

    public Properties getAccessRightprop()
    {
        return accessRightprop;
    }

    public String getLocked_flg()
    {
        return locked_flg;
    }

    public String getPdf_path()
    {
        return pdf_path;
    }
    
    public void setUserName(String s)
    {
        userName = s;
    }
    
    public void setUserId(String s)
    {
        userId = s;
    }

    public void setUname(String s)
    {
        uname = s;
    }

    public void setPassword(String s)
    {
        password = s;
    }

    public void setRequest(HttpServletRequest req)
    {
    	this.request=req;
    }
    
    

	public String getUserEmail() {
		return userEmail;
	}

	public String getUserMobNo() {
		return userMobNo;
	}

	public String getMsg() {
		return msg;
	}
	
	public String getMsg2() {
		return msg2;
	}

	public String getInactive_flg() {
		return inactive_flg;
	}

	public void setReq(ServletRequest req) {
		this.req = req;
	}

	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}

	public void setServer_port(String server_port) {
		this.server_port = server_port;
	}

	public String getMailMsg() {
		return MailMsg;
	}

	public String getMialID() {
		return MialID;
	}

	public void setMialID(String mialID) {
		MialID = mialID;
	}
}