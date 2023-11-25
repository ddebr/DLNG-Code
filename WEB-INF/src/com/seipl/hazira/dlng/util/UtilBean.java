//SB20091209:SCR10001
package com.seipl.hazira.dlng.util;

import javax.naming.*;
import javax.sql.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.seipl.hazira.dlng.util.RuntimeConf;


public class UtilBean {

    Connection conn;
	Statement stmt,stmt1,stmt2;
	ResultSet rst,rset,rset1;

	public String date_tomorrow; //tomorrows date in dd/mm/yy format
	public String gen_dt,date_first;
	public String  gen_dt1;
	public String yest_dt;
	public String yest_dt1;
	public String time_gen;
	public String get_yr;
	String gen_month = "";
	public String getGen_month() {
		return gen_month;
	}
	//public String frmnm; //now
    public String date_yesterday="";
	public String[] party_id_Array;
	public String[] party_name_Array;
	public String[] party_ref_Array;
	
	public String getDate_tomorrow() { return date_tomorrow; }
    public String getGen_dt() { return gen_dt; }
    public String getDate_first() {return date_first; }
    public String getYest_dt() { return yest_dt; }
    public String getTime_gen() { return time_gen;}

	public String[] getParty_id_Array() { return party_id_Array;}
	public String[] getParty_name_Array(){ return party_name_Array;}
	public String[] getParty_ref_Array(){ return party_ref_Array;}
	//public String getFormname(){ return frmnm; }  //now
	public Vector empPhone = new Vector();
	public Vector empCd = new Vector();
	public Vector empName = new Vector();
	public Vector empEmail = new Vector();
	
	public Vector empEmailTo = new Vector();
	public Vector empEmailCC = new Vector();
	public Vector empEmailBCC = new Vector();
	
	
	public Vector PDFempPhone = new Vector();
	public Vector PDFempCd = new Vector();
	public Vector PDFempName = new Vector();
	public Vector PDFempEmail = new Vector();
	
	public Vector PDFempEmailTo = new Vector();
	public Vector PDFempEmailCC = new Vector();
	public Vector PDFempEmailBCC = new Vector();
	
	public Vector PDFPerson_priority = new Vector();
	
	public int partycd = 0;
	
	int anxr_cd=0;
	int emp_cd=0;
	public String send_file_permission;
	
	public String sign_file_name;
	public String designation; //Added Laterly For Desgn for Pdf Reports
	

	public void setDate_tomorrow(String date_tomorrow) { this.date_tomorrow=date_tomorrow; }
	public void setGen_dt(String gen_dt) {this.gen_dt = gen_dt; }
	public void setTime_gen(String time_gen) { this.time_gen = time_gen;}
	
	public static int noofdays[]={31,30,31,30,31,30,31,31, 30,31,30,31};
	public static String monthname[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	
	public Vector cmpName = new Vector();
	public Vector cmpAdd1 = new Vector();
	public Vector cmpAdd2 = new Vector();
	public Vector cmpAdd3 = new Vector();
	public Vector cmpAdd4 = new Vector();
	public Vector cmpPh = new Vector();
	public Vector cmpFax = new Vector();
	public Vector cmpAddress = new Vector();

	String input_date = "";
	String formatted_Date= "";
	
	  public static String formatDate (String date, String initDateFormat, String endDateFormat) throws ParseException {
			String parsedDate = "";
			try {
//				System.out.println("date--------"+date);	
		    java.util.Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
		    SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
		    parsedDate = formatter.format(initDate);

//		    System.out.println("parsedDate--------"+parsedDate);
			} catch (Exception e) {
				
			}
			return parsedDate;
		}
	  
	public void fetchformatteddates()
	{	
		try
		{
				String query="select to_char(to_date('"+input_date+"','DD/MM/YYYY'),'DD-Mon-YY') from dual";
//				System.out.println("query----"+query);
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					formatted_Date=rset.getString(1)==null?"":rset.getString(1);
				}
		}
		catch(Exception e)
		{
				e.printStackTrace();
		}
	}
	
	 private void find_gen_dt_and_time_gen() throws SQLException{
			rst = stmt.executeQuery(
                  "select to_char(sysdate,'dd/mm/yyyy') gen_dt,'01/'||to_char(sysdate,'mm/yyyy') " +
                  "date_first, to_char(sysdate-1,'dd/mm/yyyy') yest_dt ,to_char(sysdate,'hh24:mi') " +
                  "time_gen,to_char(sysdate-1,'dd/mm/yyyy') yest_dt1,to_char(sysdate,'dd/mm/yyyy') gen_dt1,to_char(sysdate,'mm') month  from dual");
            if(rst.next()) {
              gen_dt=rst.getString("gen_dt");
			  date_first = rst.getString("date_first");
              time_gen=rst.getString("time_gen");
			  yest_dt=rst.getString("yest_dt");
			  yest_dt1=rst.getString("yest_dt1");
			  gen_dt1 = rst.getString("gen_dt1");
			  gen_month = rst.getString("month");
//			  System.out.println("month---"+rst.getString("month"));
            }
  }
	 
  public void getCurrentYear()
  {
	  String url="Select to_char(sysdate,'yyyy') from dual";
	  try
	  {
	   rst=stmt.executeQuery(url);
	   if(rst.next())
	   {
		   get_yr=rst.getString(1);
	   }
	  }
	  catch(Exception e)
	  {
		System.out.println("Exception:UtilBean-->getCurrentYear()-->DUAL: "+e);  
	  }
  }

	public void set_date_tomorrow() throws SQLException{
		rst = stmt.executeQuery(
                  "select to_char(sysdate + 1,'dd/mm/yyyy') date_tomorrow from dual");
            if(rst.next()) {
             date_tomorrow=rst.getString("date_tomorrow");
            }
	}
   //	used to calculate the number of days in a month. Send the month and year
   //   Example for Month: Jan Year: 2006   SEND: getDays(1,2006): It will return 31;	 
	public int getDays(int month,int year)
	{
	   //System.out.println("YEAR: "+year);
		GregorianCalendar cal = new GregorianCalendar();
	   if(month == 2)
	   {   
	     if(cal.isLeapYear(year))
	        return 29;   
	     else
	      	 return 28;
	   } 
	   else
	   {	    
		   return noofdays[month-1];
	   }	   
	}
			 public void get_date_yesterday() throws SQLException{
					rst = stmt.executeQuery("select to_char(sysdate - 1,'dd/mm/yyyy') date_tomorrow from dual");
			          if(rst.next()) {
			           date_yesterday=rst.getString("date_tomorrow");
			  }
			 }		 
			 
	  
	public void getEmployeeEmailsPDF(){
		
		int pers_comm_id=0;
		String query1="";
		String query="select email_office,person_nm,phoneoffice,comm_person_id,comm_dtl_id,comm_id,flag from fms_comm_person_dtl where comm_id='1' and comm_dtl_id='"+partycd +"' and Flag='Y'";
		try{
			rst = stmt.executeQuery(query);
			while (rst.next())
			{
				PDFempEmail.add(rst.getString(1)==null?"":rst.getString(1));
				PDFempName.add(rst.getString(2)==null?"":rst.getString(2));
				PDFempPhone.add(rst.getString(3)==null?"":rst.getString(3));
				pers_comm_id=(rst.getInt(4)==0?0:rst.getInt(4));
				query1="select * from FMS_COMM_ANXR_PERSON_DTL where comm_id=1 and comm_dtl_id='"+partycd +"' and anxr_cd='"+anxr_cd+"' and comm_person_id='"+pers_comm_id+"'";
				rset =stmt1.executeQuery(query1);
				if(rset.next()){
					PDFempEmailTo.add(rset.getString(5)==null?"":rset.getString(5));
					PDFempEmailCC.add(rset.getString(6)==null?"":rset.getString(6));
					PDFempEmailBCC.add(rset.getString(7)==null?"":rset.getString(7));
					PDFPerson_priority.add(rset.getString(11)==null?"":rset.getString(11));
				}else{
					PDFempEmailTo.add("N");
					PDFempEmailCC.add("N");
					PDFempEmailBCC.add("N");
					PDFPerson_priority.add("N");
				}
			}
									
		}catch(Exception e){
			System.out.println("Exception in UtilBean -- getEmployeeDetails1 -- >" + e);
			e.printStackTrace();
		}
	}
	public void getEmployeeEmails(){
		
		int pers_comm_id=0;
		String query1="";
		String query="select email_office,person_nm,phoneoffice,comm_person_id,comm_dtl_id,comm_id,flag from fms_comm_person_dtl where comm_id='1' and comm_dtl_id='"+partycd +"' and Flag='Y'";
		try{
			rst = stmt.executeQuery(query);
			while (rst.next())
			{
				empEmail.add(rst.getString(1)==null?"":rst.getString(1));
				empName.add(rst.getString(2)==null?"":rst.getString(2));
				empPhone.add(rst.getString(3)==null?"":rst.getString(3));
				pers_comm_id=(rst.getInt(4)==0?0:rst.getInt(4));
				query1="select * from FMS_COMM_ANXR_PERSON_DTL where comm_id=1 and comm_dtl_id='"+partycd +"' and anxr_cd='"+anxr_cd+"' and comm_person_id='"+pers_comm_id+"'";
				rset =stmt1.executeQuery(query1);
				if(rset.next()){
					empEmailTo.add(rset.getString(5)==null?"":rset.getString(5));
					empEmailCC.add(rset.getString(6)==null?"":rset.getString(6));
					empEmailBCC.add(rset.getString(7)==null?"":rset.getString(7));
					
					
				}else{
					empEmailTo.add("N");
					empEmailCC.add("N");
					empEmailBCC.add("N");
				}
			}
//			This code fetch Internal(4) Email ID
			query1="select * from FMS_COMM_ANXR_PERSON_DTL where comm_id=4 and anxr_cd='"+anxr_cd+"'";		 
			//System.out.println("Internal Rights : "+ query1 );
			rset =stmt1.executeQuery(query1);
			int comm_per_id=0;
			int com_int_per=0;
			while(rset.next()){
				comm_per_id=rset.getInt(3);
				if(comm_per_id!=emp_cd){
					
					empEmailTo.add(rset.getString(5)==null?"":rset.getString(5));
					empEmailCC.add(rset.getString(6)==null?"":rset.getString(6));
					empEmailBCC.add(rset.getString(7)==null?"":rset.getString(7));
					com_int_per=rset.getInt(3);
					
					query="  select email_office from FMS_COMM_person_dtl where comm_person_id='"+com_int_per+"' and comm_id=4";
					rset1 =stmt2.executeQuery(query);
					if(rset1.next()){
						empEmail.add(rset1.getString(1)==null?"":rset1.getString(1));
					}	
				}
			}
			
		}catch(Exception e){
			System.out.println("Exception in UtilBean--getEmployeeDetails1-->" + e);
			e.printStackTrace();
		}
	}
	public void getFile_Permission(){
		String query="SELECT INT_SEND_RIGHT FROM FMS_COMM_ANXR_PERSON_DTL WHERE COMM_PERSON_ID='"+emp_cd+"' AND ANXR_CD='"+anxr_cd+"' AND COMM_ID='4'";
		
		try{
			rset =stmt.executeQuery(query);
			if(rset.next()){
				send_file_permission=(rset.getString(1)==null?"":rset.getString(1));
			}
		}catch(Exception e){
			System.out.println("Exception in UtilBean--getFile_Permission-->" + e);
			e.printStackTrace();
		}
	}
	public void getCompanyDetails(){
	cmpName.clear();
	cmpAdd1.clear();
	cmpAdd2.clear();
	cmpAdd3.clear();
	cmpAdd4.clear();
	cmpPh.clear();
	cmpFax.clear();
	cmpAddress.clear();
	
	int i=0;
	String query="select COMP_NM,COMP_ADR1,COMP_ADR2,COMP_ADR3,COMP_ADR4,COMP_PH_NO1,COMP_FAX_NO FROM FMS_COMPANY_MST";
	  try{
		rst = stmt.executeQuery(query);
		if (rst.next())
	    {
			cmpName.add((rst.getString(1)==null?"":rst.getString(1)));
			cmpAdd1.add((rst.getString(2)==null?"":rst.getString(2)));
			cmpAdd2.add((rst.getString(3)==null?"":rst.getString(3)));
			cmpAdd3.add((rst.getString(4)==null?"":rst.getString(4)));
			cmpAdd4.add((rst.getString(5)==null?"":rst.getString(5)));
			cmpPh.add((rst.getString(6)==null?"":rst.getString(6)));
			cmpFax.add((rst.getString(7)==null?"":rst.getString(7)));
			cmpAddress.add(rst.getString(2)+","+rst.getString(3)+","+rst.getString(4)+"-"+rst.getString(5)+",Ph:"+rst.getString(6)+",Fax:"+rst.getString(7));
			
			i++;
		}
	  }catch(Exception e){
	       System.out.println("Exception in UtilBean ==> getCompanyDetails " + e);
	       e.printStackTrace();
	  }
	}
	public void getSignature(){
		try{
			String query="select signature,DESIGNATION from FMS_COMM_PERSON_DTL  where comm_id>=4 and comm_person_id='"+emp_cd+"'";
			rset = stmt.executeQuery(query);
			if(rset.next()){
				sign_file_name=(rset.getString(1)==null?"":rset.getString(1));
				designation=(rset.getString(2)==null?"":rset.getString(2));
			}else{
				sign_file_name="";
				designation="";
			}
	}catch(Exception e){
	       System.out.println("Exception in UtilBean ==> getSignature " + e);
	       e.printStackTrace();
	  }
	}
	public void init() {
    try{

	  Context initContext = new InitialContext();
      if(initContext == null ) 
          throw new Exception("Boom - No Context");
	  Context envContext  = (Context)initContext.lookup("java:/comp/env");
	  //20091221	  DataSource ds = (DataSource)envContext.lookup(RuntimeConf.penom_database);
	  DataSource ds = (DataSource)envContext.lookup(RuntimeConf.security_database);
      if (ds != null) {
         conn = ds.getConnection();       
         if(conn != null)  {
           stmt = conn.createStatement();
           stmt1 = conn.createStatement();
           stmt2 = conn.createStatement();
           
		   set_date_tomorrow();
		   find_gen_dt_and_time_gen();
		   getCurrentYear();	
		   //getCompanyDetails();
		   //getEmployeeEmails();
		   //getEmployeeEmailsPDF();
		   //getFile_Permission();
		   //getSignature();
		   get_date_yesterday();
		   fetchformatteddates();
		   conn.close();
        }
      }
    }catch(Exception e) {
      e.printStackTrace();
    }
 }
	public String getGet_yr() {
		return get_yr;
	}
	public Vector getEmpCd() {
		return empCd;
	}
	public Vector getEmpEmail() {
		return empEmail;
	}
	public Vector getEmpName() {
		return empName;
	}
	public void setPartycd(int partycd) {
		this.partycd = partycd;
	}
	public Vector getEmpPhone() {
		return empPhone;
	}
	public String getYest_dt1() {
		return yest_dt1;
	}
	public String getGen_dt1() {
		return gen_dt1;
	}
	public String getDate_yesterday() {
		return date_yesterday;
	}
	public Vector getCmpAddress() {
		return cmpAddress;
	}
	public Vector getCmpName() {
		return cmpName;
	}
	public Vector getEmpEmailBCC() {
		return empEmailBCC;
	}
	public Vector getEmpEmailCC() {
		return empEmailCC;
	}
	public Vector getEmpEmailTo() {
		return empEmailTo;
	}
	public void setAnxr_cd(int anxr_cd) {
		this.anxr_cd = anxr_cd;
	}
	public Vector getPDFempCd() {
		return PDFempCd;
	}
	public Vector getPDFempEmail() {
		return PDFempEmail;
	}
	public Vector getPDFempEmailBCC() {
		return PDFempEmailBCC;
	}
	public Vector getPDFempEmailCC() {
		return PDFempEmailCC;
	}
	public Vector getPDFempEmailTo() {
		return PDFempEmailTo;
	}
	public Vector getPDFempName() {
		return PDFempName;
	}
	public Vector getPDFempPhone() {
		return PDFempPhone;
	}
	public Vector getPDFPerson_priority() {
		return PDFPerson_priority;
	}
	public void setEmp_cd(int emp_cd) {
		this.emp_cd = emp_cd;
	}
	public String getSend_file_permission() {
		return send_file_permission;
	}
	public String getSign_file_name() {
		return sign_file_name;
	}
	public String getDesignation() {
		return designation;
	}
	public String getInput_date() {
		return input_date;
	}
	public String getFormatted_Date() {
		return formatted_Date;
	}
	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}
	public void setFormatted_Date(String formatted_Date) {
		this.formatted_Date = formatted_Date;
	}
}
