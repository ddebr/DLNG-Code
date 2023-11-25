//SS20100913:ADMIN:B1001
package com.seipl.hazira.dlng.admin;

import java.io.PrintStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

//Code Reviewed by	: Mr. Samik Shah
//CR Date			: 20th September 2010
//CR Status  		: Consistent with BRS ... 

public class DataBean_Admin_Reset
{
	Connection conn;
    Statement stmt,stmt1,stmt2,stmt3,stmt4;
    ResultSet rset,rset1,rset2,rset3,rset4;
    public String queryString;
    public String queryString1;
    public String queryString2;
    public String queryString3;
    public String method_name;
    public int u_min = 0;
	public int u_max = 0;
	public int p_min = 0;
	public int p_max = 0;
	
	Vector V_module_nm_get = new Vector();
	Vector V_module_id_get = new Vector();
	Map m1 = new HashMap();
	
	Vector VmoduleCd=new Vector();
    Vector VmoduleName=new Vector();
	Vector Vpriority=new Vector();
	
	Vector Vlog_date=new Vector();
	Vector Vlog_time=new Vector();
	Vector Vlog_mach_id=new Vector();
	Vector Vremarks=new Vector();
	
	String from_date_get = "";
	String to_date_get = "";
	
	String[] log_date;
	String[][][] log_time;
	String[][][] form_name;
	String[][][] remarks;
	String[][] machine_id;
	
	String[][] tot_login;
	String[][] tot_logout;
	String[][] login_time;
	String[][] logout_time;
	String[][] first_login;
	String[][] last_logout;
	String[][] time_consumed;
	String[][] mach_id;
	String[][] user_nm;
	//sravan
	String all_add;
	String all_dai;
	
	public String user_cd ="";
	public String activity_cd ="" ;
	public String remark ="" ;
	Vector V_activity_cd = new Vector();
	Vector V_sn_closure_rem = new Vector();
	Vector V_cargo_reconciliation_rem = new Vector();

	//Following (9) Vectors has been defined By Samik Shah On 9th June, 2011 ...
	public Vector V_Group_cd = new Vector();
	public Vector V_Group_nm = new Vector();
	public Vector V_User_cd = new Vector();
	public Vector V_User_nm = new Vector();
	public Vector V_Module_cd = new Vector();
	public Vector V_Module_nm = new Vector();
	public Vector V_Form_cd = new Vector();
	public Vector V_Form_nm = new Vector();
	public Vector V_Access_rights = new Vector();
	//RG20190612
	Vector Activity_cd1=new Vector();
	Vector emp_cd=new Vector();
	Vector emp_act_cnt=new Vector();
	Vector reminder_dys=new Vector();
	Vector rmk=new Vector();
	Vector emp_name1=new Vector();
	String from_dt="";
	String set_to_dt="";
	String actflag="";
	String sel_month="";
	String sel_year="";
	//RG20190612
	
	public String stat=""; //JHP20120312
	
	public DataBean_Admin_Reset()
    {
        userid = "";
        logintime = "";
        logindate = "";
        ip = "";
        login_status = "";
        emp_pass = "";
        set_emp_cd = "0";
		u_min = 0;
        u_max = 0;
		p_min = 0;
		p_max = 0;		
	}

    public void clear_variables()
    {
        queryString = "";
        queryString1 = "";
        queryString2 = "";
        queryString3 = "";
        userid = "";
        logintime = "";
        logindate = "";
        ip = "";
        login_status = "";
        set_emp_cd = "0";
        method_name = "";
        Vlog_date.clear();
        Vlog_time.clear();
        Vlog_mach_id.clear();
        Vremarks.clear();
    }
    
    
    //Setter Method(s) Variables List :
	public String userid;
    public String logintime;
    public String logindate;
    public String ip;
    public String login_status;
    public String emp_pass;
    public String set_emp_cd="0";
    public String set_date="";
    public String set_to_date="";
    public String set_party_cd="";
    public String set_sub_menu_id="0";
    public String set_module_id="0";
    public String set_group_id="0";
   //sra1-14/aug/09
    public String username;
    public String ALW_ADD;
    public String ALW_DEL;
    public String ALW_UPD;
    public String ALW_PRINT;
    
    
    
    //Setter Method(s) List :
    public void setCallFlag(String callFlag){this.callFlag = callFlag;}
    public void setUser(String s){this.userid = s;}
    //sra1-14/aug/09
    public void setUsername(String s1){this.username = s1;}
    //
    public void setIp(String s){this.ip = s;}
    public void setLogindate(String s){this.logindate = s;}
    public void setLogintime(String s){this.logintime = s;}   
	public void setFormID(String formid){this.formid = formid;}
	public void setEmpCD(String emp_cd){this.set_emp_cd = emp_cd;}
	public void setDate(String date){this.set_date = date;}
	public void setSubMenuId(String sub_menu_id){this.set_sub_menu_id = sub_menu_id;}
	public void setModuleId(String module_id){this.set_module_id = module_id;}
	public void setGroupId(String group_id){this.set_group_id = group_id;}
	public void setPartyCd(String party_cd){this.set_party_cd = party_cd;}
	
	
	//Getter Method(s) Variables List :
	int count1 = 0;
	int count2 = 0;
	int cnt = 0;
	
	public String callFlag = "";
	public String frmnm = "";
	public String formid = "";
	public String count = "";
		
	public String[] group_cd1,group_nm1,group_cd3,group_nm3,from_date,to_date;
	public String[] emp_id,emp_nm,emp_abr,emp_code,emp_name,emp_abbr;
	public String emp_lock; //SB20200407
	String status = "";//HA20200420
	public String getStatus() {
		return status;
	}


	public String[] empl_group;
	public String[] module_cd;
	public String[] module_name;
	public String[] group_id;
	public String[] group_name;
		
	public Vector V_party_cd = new Vector();
	public Vector V_party_nm = new Vector();
	public Vector V_cont_party_abr = new Vector();
	public Vector V_cont_no = new Vector();
	public Vector V_cont_yy = new Vector();
	public Vector V_rec_pt_id = new Vector();
	public Vector V_rec_pt_abr = new Vector();
	public Vector V_cont_type = new Vector();
	
	public Vector V_ene_base_v = new Vector();
	public Vector V_sch_dcq = new Vector();
	
	public String party_type = "";
	public String party_nm_final = "";
	public String nom_flag = "";
	public String sch_flag = "";
	public String def_flag = "";
	public String eng_unit = "";
	public String eng_base = "";
	public String location = "";
	public String cnhv = "";
	public String cghv = "";
	public String fm_dt = "";
	public String to_dt = "";
	public String time_st_day = "";
	public String avail = "";
	public String count_tkt = "";
	public String count_nom = "";
	
	public Vector V_rptnrev_no = new Vector();
	public Vector V_rptnnhv = new Vector();
	public Vector V_rptnghv = new Vector();
	
	public Vector V_rptnnom_id = new Vector();
	public Vector V_rptnday_tot_ene = new Vector();
	public Vector V_rptnday_tot_ene1 = new Vector();
	public Vector V_rptngen_dt = new Vector();
	
	public Vector V_party_lst = new Vector();
	public Vector V_party_nm_lst = new Vector();
	
	public String[] party_code,party_desc;
	
	public Vector V_emp_id = new Vector();
	public Vector V_emp_nm = new Vector();
	public Vector V_emp_sts= new Vector();
	public Vector V_emp_abr = new Vector();
	public Vector V_email_id = new Vector();  //SB20100310
	public Vector JOIN_RESIGN_DT = new Vector();  //RG20190619
	public Vector V_status = new Vector();
	String rd_flag="";
	
	public String f_emp_nm = "";
	public String f_status = "";
	
	public String no_days="";
	public String no_password="";
	public String reminder_days = "";
	public String password_type = "";
	public String userid_min = "";
	public String userid_max = "";
	public String password_min = "";
	public String password_max = "";
	public String eff_dt = "";
	public String pass_flag = "NO";
	
	public Vector V_sub_menu_id = new Vector();
	public Vector V_sub_menu_nm = new Vector();
	public Vector V_module_id = new Vector();
	public Vector V_form_type = new Vector();
	public Vector V_module_nm = new Vector();
	
	//Following (2) Vector Variables Has Been Defined By Samik Shah On 31st August, 2010 ...
	public Vector V_module_start_index = new Vector();
	public Vector V_module_end_index = new Vector();
	
	public String sub_menu_id = "0";
	public String sub_menu_nm = "";
	public String module_id = "0";
	public String sub_menu_path = "";
	public String doc_type = "F";
	public String grp_seq_no ="";
	public String grp_cd ="";
	public String grp_nm ="";
	
	public Vector V_group_id = new Vector();
	public Vector V_group_nm = new Vector();
	
	public Vector V_view_print_perm = new Vector();
	public Vector V_delete_perm = new Vector();
	public Vector V_add_perm = new Vector();
	public Vector V_grant_perm = new Vector();
	public Vector V_check_perm = new Vector();
	public Vector V_authorize_perm = new Vector();
	public Vector V_print_perm = new Vector();
	public Vector V_update_perm = new Vector();
	
	
	
	public Vector V_view_print_perm_value = new Vector();
	public Vector V_delete_perm_value = new Vector();
	public Vector V_add_perm_value = new Vector();
	public Vector V_grant_perm_value = new Vector();
	public Vector V_check_perm_value = new Vector();
	public Vector V_authorize_perm_value = new Vector();
	public Vector V_print_perm_value = new Vector();
	public Vector V_update_perm_value = new Vector();
		
	
	//Getter Method(s) List :
	public int getUmin(){return u_min;}
	public int getUmax(){return u_max;}
	public int getPmin(){return p_min;}
	public int getPmax(){return p_max;}
		
	public int getCount(){return cnt;}
	public int getGroup_Count(){return count1;}
	
	public String getFormName(){return frmnm;}
	
	public String[] getGroup_nm3(){return group_nm3;}
	
	public String[] getGroup_cd1(){return group_cd1;}
	public String[] getGroup_cd3(){return group_cd3;}
	public String[] getGroup_nm1(){return group_nm1;}
	public String[] getEmp_id(){return emp_id;}
	public String[] getEmp_nm(){return emp_nm;}
	public String[] getEmp_abr(){return emp_abr;}
	public String getEmp_lock(){return emp_lock;} //SB20200407
	public String[] getModule_cd(){return module_cd;}
	public String[] getModule_name(){return module_name;}
	public String[] getGroup_id(){return group_id;}
	public String[] getGroup_name(){return group_name;}

	public Vector getPartyCd(){return V_party_cd;}
	public Vector getPartyNm(){return V_party_nm;}
	public Vector getContPartyAbr(){return V_cont_party_abr;}
	public Vector getContContNo(){return V_cont_no;}
	public Vector getContYy(){return V_cont_yy;}
	public Vector getRecPtId() {return V_rec_pt_id;}
	public Vector getRecPtAbr() {return V_rec_pt_abr;}
	public Vector getContType(){return V_cont_type;}
	
	public Vector getEneBaseV(){return V_ene_base_v;}
	public Vector getSchDcq(){return V_sch_dcq;}
	
	public String getPartyType(){return party_type;}
	public String getPartyNmFinal(){return party_nm_final;}
	public String getNomFlag(){return nom_flag;}
	public String getSchFlag(){return sch_flag;}
	public String getDefFlag(){return def_flag;}
	public String getEngUnit(){return eng_unit;}
	public String getEngBase(){return eng_base;}
	public String getLocation(){return location;}
	public String getCnhv(){return cnhv;}
	public String getCghv(){return cghv;}
	public String getToDt(){return to_dt;}
	public String getFmDt(){return fm_dt;}
	public String getTimeStDay(){return time_st_day;}
	public String getAvail(){return avail;}
	public String getCountTkt(){return count_tkt;}
	public String getCountNom(){return count_nom;}
	
	public Vector getRptNrevNo(){return V_rptnrev_no;}
	public Vector getRptNnhv(){return V_rptnnhv;}
	public Vector getRptNnomId(){return V_rptnnom_id;}
	public Vector getRptNdayTotEne(){return V_rptnday_tot_ene;}
	public Vector getRptNgenDt(){return V_rptngen_dt;}
	
	public Vector getPartyList(){return V_party_lst;}
	public Vector getPartyNameList(){return V_party_nm_lst;}
	
	public String[] getPartyCode(){return party_code;}
	public String[] getPartyDesc(){return party_desc;}
	
	public Vector getEmpId(){return V_emp_id;}
	public Vector getEmpNm(){return V_emp_nm;}
	public Vector getEmpAbr(){return V_emp_abr;}
	public Vector getEmailId(){return V_email_id;}
	public Vector getUserListStatus(){return V_status;}
	
	public String getFEmpNm(){return f_emp_nm;}
	public String getFStatus(){return f_status;}
		
	public String getNoDays(){return no_days;}
	public String getNoPassword(){return no_password;}
	public String getReminderDays(){return reminder_days;}
	public String getPasswordType(){return password_type;}
	public String getUseridMin(){return userid_min;}
	public String getUseridMax(){return userid_max;}
	public String getPasswordMin(){return password_min;}
	public String getPasswordMax(){return password_max;}
	public String getEffDt(){return eff_dt;}
	public String getPassFlag(){return pass_flag;}
	
	public Vector getSubMenuIds(){return V_sub_menu_id;}
	public Vector getSubMenuNms(){return V_sub_menu_nm;}
	public Vector getModuleIds(){return V_module_id;}
	public Vector getModuleNms(){return V_module_nm;}
	
	public String getSubMenuId(){return sub_menu_id;}
	public String getSubMenuNm(){return sub_menu_nm;}
	public String getModuleId(){return module_id;}
	public String getSubMenuPath(){return sub_menu_path;}
	public String getDocType(){return doc_type;}
	
	public Vector getGroupIds(){return V_group_id;}
	public Vector getGroupNms(){return V_group_nm;}
	
	public Vector getViewPrintPerm(){return V_view_print_perm;}
	public Vector getDeletePerm(){return V_delete_perm;}
	public Vector getAddPerm(){return V_add_perm;}
	public Vector getGrantPerm(){return V_grant_perm;}
	public Vector getCheckPerm(){return V_check_perm;}
	public Vector getAuthorizePerm(){return V_authorize_perm;}
	public Vector getUpdatePerm(){return V_update_perm;}
	public Vector getPrintPerm(){return V_print_perm;}
	
	public Vector getViewPrintPermValue(){return V_view_print_perm_value;}
	public Vector getDeletePermValue(){return V_delete_perm_value;}
	public Vector getAddPermValue(){return V_add_perm_value;}
	public Vector getGrantPermValue(){return V_grant_perm_value;}
	public Vector getCheckPermValue(){return V_check_perm_value;}
	public Vector getAuthorizePermValue(){return V_authorize_perm_value;}
	public Vector getUpdatePermValue(){return V_update_perm_value;}
	public Vector getPrintPermValue(){return V_print_perm_value;}
	
	public String getAll_add(){return  all_add;}
	
    Vector Vparty_abr    =new Vector();
	Vector Vparty_cd       =new Vector();
	Vector vtransabr      =new Vector();
	Vector vtranscd       =new Vector();
	Vector ventryptcd       =new Vector();
	Vector ventryabr       =new Vector();
	Vector vmappingid      =new Vector();
	Vector vcontstdt       =new Vector();
    Vector vexistcd       =new Vector();
    Vector vexistabr       =new Vector();
    Vector Vmapp_id       =new Vector(); 
    Vector V_imbalance = new Vector();
    String cont_base="";	
    String cont_type="";
    String party_name="";
    String dcq="";
    String mdq="";
    String entry_totene="";
    String exit_totene="";
    
	//Various Data Fetch Functions Definitions : 
    
    // SY 10/02/2010
    public Vector V_mod_group_id = new Vector();
	public Vector V_mod_group_nm = new Vector();
	public Vector V_form_seq_no = new Vector();
	public Vector V_frm_rpt_flag = new Vector();
	String mod_group_id="";	
	//
	
//	Following Vector Has Been Defined By Milan DAlsaniya  On 09st Nov, 2011 MD20111109 ...
	public Vector V_provisional_seller_payment = new Vector();
	public Vector V_final_seller_payment = new Vector();
	public Vector V_provisional_custome_duty_payment = new Vector();
	public Vector V_final_custome_duty_payment = new Vector();
	public Vector V_approv_invoice = new Vector();
//	 
	//Following Vector Has Been Defined By Priyanka On 21st January, 2011 ...
	public Vector V_fgsa_renewal_rem = new Vector();
	//Following Vector Getter Method Has Been Defined By Priyanka On 21st January, 2011 ...
	public Vector getV_fgsa_renewal_rem() {return V_fgsa_renewal_rem;}
	
	//Following (2) Vectors Has Been Introduced By Samik Shah On 1st February, 2011 ...
	public Vector FORMCODE = new Vector();
	public Vector FORMNAME = new Vector();
	
	//Following (2) Vector Getter Methods Has Been Introduced By Samik Shah On 1st February, 2011 ...
	public Vector getFORMCODE() {return FORMCODE;}
	public Vector getFORMNAME() {return FORMNAME;}
	
	//Following (3) Vectors Has Been Defined By Priyanka Sharma On 7th April, 2011 ...
	public Vector V_sn_closure_request_rem = new Vector();
	public Vector V_LOA_closure_rem = new Vector();
	public Vector V_LOA_closure_request_rem = new Vector();
	
	//Following Vector Has Been Defined By Priyanka Sharma On 11th April, 2011 ...
	public Vector V_lock_status = new Vector();
	
	//Following String Variable Has Been Defined By Priyanka Sharma On 13th April, 2011 ...
	public String select_date = "";
	
	//Following (5) Vectors Has Been Defined By Priyanka Sharma On 14th April, 2011 ...
	public Vector V_log_dt = new Vector();
	public Vector V_log_time = new Vector();
	public Vector V_log_uid = new Vector();
	public Vector V_emp_cd = new Vector();
	public Vector V_flag = new Vector();
	
	String server_ip="";
	String up_path="";
	String down_path="";
	String domain="";
	String log_id="";
	String log_pass="";
	
	
	String cd=""; 
	String month="";
	String year="";
	String date_after="";
	String sysdate="";
	String last_date="";
	Vector V_rem_days=new Vector();
	
	
	public void fetchServerSetting() throws SQLException
	{
		try
        {
        	method_name = "fetchServerSetting()";
           	queryString = "select SERVER_IP,UPLOAD_PATH,DOWNLOAD_PATH,DOMAIN_NM,LOGIN_ID," +
           			"LOGIN_PASSWORD from FMS7_FTP_SERVER_SETTING "; 
           	rset = stmt.executeQuery(queryString);
            if(rset.next())
            {
				server_ip = rset.getString(1)==null?"":rset.getString(1);
				up_path = rset.getString(2)==null?"":rset.getString(2);
				down_path = rset.getString(3)==null?"":rset.getString(3);
				domain = rset.getString(4)==null?"":rset.getString(4);
				log_id = rset.getString(5)==null?"":rset.getString(5);
				log_pass = rset.getString(6)==null?"":rset.getString(6);
            }
            
            queryString = "SELECT DISTINCT A.group_cd, B.group_name " +
						  "FROM SEC_EMP_GROUP_DTL A, SEC_ACCESS_GROUP_MST B " +
						  "WHERE A.group_cd=B.group_cd AND " +
						  "A.emp_cd IS NOT NULL AND A.emp_cd!=0 AND " +
						  "(sysdate BETWEEN A.from_dt AND A.to_dt) " +
						  "ORDER BY B.group_name";
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				V_Group_cd.add(rset.getString(1)==null?"0":rset.getString(1));
				V_Group_nm.add(rset.getString(2)==null?" ":rset.getString(2));
			}
			
			for(int i=0; i<V_Group_cd.size(); i++)
			{
				String user_cd = "";
				String user_nm = "";
				
				queryString = "SELECT DISTINCT A.emp_cd, B.emp_nm " +
							  "FROM SEC_EMP_GROUP_DTL A, HR_EMP_MST B " +
							  "WHERE A.emp_cd=B.emp_cd AND " +
							  "A.group_cd="+V_Group_cd.elementAt(i)+" AND " +
							  "(sysdate BETWEEN A.from_dt AND A.to_dt) " +
							  "ORDER BY B.emp_nm";
			//	//System.out.println("SELECT EMP_NM FROM HR_EMP_MST ====> "+queryString);
				rset = stmt.executeQuery(queryString);				
				while(rset.next())
				{
					user_cd += (rset.getString(1)==null?"0":rset.getString(1))+",";
					user_nm += (rset.getString(2)==null?" ":rset.getString(2))+",";
				}
				
				V_User_cd.add(user_cd.substring(0,user_cd.length()-1));
				V_User_nm.add(user_nm.substring(0,user_nm.length()-1));
				
			//.out.println("V_Group_nm.elementAt("+i+") = "+V_Group_nm.elementAt(i));
			//	//System.out.println("V_User_nm.elementAt("+i+") = "+V_User_nm.elementAt(i));
			}
        }
        catch(Exception e)
        {
//            //System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... " + e);
//            //System.out.println("Query = "+queryString);
            e.printStackTrace();
        }
	}
	
	public void loadDefaultValues() throws SQLException
    {
        try
        {
        	method_name = "loadDefaultValues()";
            			
           	queryString = "select userid_min,userid_max,password_min,password_max " +
           			      " from fms_password_life where eff_dt=(select max(eff_dt) " +
           			      " from fms_password_life where eff_dt<=sysdate)"; 
//           	System.out.println("ADMIN-QRY0001 :SELECT :FMS_PASSWORD_LIFE : DATABEAN_ADMIN.java : "+queryString);
           	rset = stmt.executeQuery(queryString);
            if(rset.next())
            {
                u_min = Integer.parseInt(rset.getString(1));
				u_max = Integer.parseInt(rset.getString(2));
				p_min = Integer.parseInt(rset.getString(3));
				p_max = Integer.parseInt(rset.getString(4));
            }
            else
			{
				u_min = 0;
				u_max = 0;
				p_min = 0;
				p_max = 0;
			}
        }
        catch(Exception e)
        {
//            //System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... " + e);
//            //System.out.println("Query = "+queryString);
            e.printStackTrace();
        }
    }//end of method loadDefaultValues() ...
    
    
	public void getFormDetails() throws SQLException
    {
    	try
        {
    //		//System.out.println("ADMIN-MSG0001 :USER NAME :" +username  +"  ADMIN-MSG0002 :FORM ID :" +formid);
    		////System.out.println("ADMIN-MSG0002 :Form id :" + formid);
        	method_name = "getFormDetails()";
	    	
        	queryString = "select FORM_NAME from SEC_FORM_MST where FORM_CD ='" + formid + "'";
//     System.out.println("ADMIN-QRY0002 :SELECT :SEC_FORM_MST : DATABEAN_ADMIN.java : "+queryString);
        	
        	if(formid!=null && !formid.equals("null") && !formid.equals("") && !formid.equals(""))
        	{
	        	rset = stmt.executeQuery(queryString);
			    if(rset.next())
		        {
		        	frmnm=rset.getString("FORM_NAME");        
		        }
        	}
        	else
        	{
        		frmnm="";
        	}
        	
        	String emp_cd = "";
        	String group_cd = "";
        	
        	//ADDED BY DCB 03/11/2014
        	queryString = "select emp_cd from hr_emp_mst where emp_abr='"+username+"'";
//	        System.out.println(queryString);
	        rset = stmt.executeQuery(queryString);
	        if(rset.next())
	        {
	        	emp_cd = rset.getString(1);
	        }
	        
	        queryString = "select group_cd from sec_emp_group_dtl where emp_cd='"+emp_cd+"'";
//	        System.out.println(queryString);
	        rset = stmt.executeQuery(queryString);
	        if(rset.next())
	        {
	        	group_cd = rset.getString(1);
	        }

	        queryString2="select ALW_ADD,ALW_UPD,ALW_DEL,ALW_PRINT from sec_group_form where" +
	        		     "  FORM_CD ='" + formid + "' and group_cd='"+group_cd+"'";
//	        System.out.println(queryString2);
	        rset = stmt.executeQuery(queryString2);
	        if(rset.next())
	        {
	        	
	        	   ALW_ADD=rset.getString(1);
	        	   ALW_UPD=rset.getString(2);
	        	   ALW_DEL=rset.getString(3);
	        	   ALW_PRINT=rset.getString(4);     
	        }
	        //COMPLETED BY DCB 03/11/2014
	        
       // 	//System.out.println("..........frmnm........"+frmnm);
        	
        }
    	catch(Exception e)
        {
//            //System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... " + e);
//            //System.out.println("Query = "+queryString);
   		 	e.printStackTrace();
        }
	}//end of method getFormDetails() ...
    

	///////////////////////SB20200406/////////////////////////////////////////
	public void fetchLockInfoSelf() throws SQLException
	{
		try
        {
			method_name = "fetchLockInfoSelf()";
			String emp_cd="0";
			if(username.equals("")) 
				username="0";
			queryString = "select emp_cd from hr_emp_mst where emp_abr='"+username+"'";
//			System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			  {
			     emp_cd = rset.getString(1);
			  }
			emp_lock ="N";
			queryString = " SELECT A.emp_cd,emp_nm,emp_abr, B.locked_flag FROM HR_EMP_MST A,SEC_EMP_PASSWORDS B "+
				              " WHERE A.emp_cd=B.emp_cd AND  B.emp_cd='"+emp_cd+"'";
//				System.out.println("ADMIN-QRY0058 :SELECT :HR_EMP_MST, SEC_EMP_PASSWORDS : DATABEAN_ADMIN.java : "+queryString);
				rset = stmt.executeQuery(queryString);		
				if(rset.next())
				{		
					emp_lock = rset.getString(4)==null?"N":rset.getString(4);			
				}else {
					status = "Invalid Userid/Email Id";
				}
				
		
//			System.out.println("emp_lock: "+emp_lock);
			
		}
		catch(Exception e)
		{
//			//System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
//			//System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchLockInfo() ...
///////////////////////////////////////////////////////////////	
    public void init()
    {
        try
        {
            InitialContext initialcontext = new InitialContext();
            
            if(initialcontext == null)
                throw new Exception("Boom - No Context");
            
            Context context = (Context)initialcontext.lookup("java:/comp/env");
            DataSource datasource = (DataSource)context.lookup(com.seipl.hazira.dlng.util.RuntimeConf.security_database);
            
            if(datasource != null)
            {
                conn = datasource.getConnection();
                if(conn != null)
                {
                    stmt = conn.createStatement();
                    stmt1 = conn.createStatement();
                    stmt2 = conn.createStatement();
                    stmt3 = conn.createStatement();
                    stmt4 = conn.createStatement();
                    if(callFlag.equalsIgnoreCase("Party_list_dtl"))  //SB20091210
					{
                    	loadDefaultValues();  //SS20110317
                    	fetchServerSetting();
                    }
                    else if(callFlag.equalsIgnoreCase("Server_Settings"))  //SB20091210
					{
                    	loadDefaultValues();  //SS20110317
                    	fetchServerSetting();
                    }
                    else if(callFlag.equalsIgnoreCase("Change_Password"))  //SB20091210
					{
                    	loadDefaultValues();  //SS20110110
                    	getFormDetails();  //SS20110110
                    }
                    else if(callFlag.equalsIgnoreCase("Change_Password2"))  //SB20091210
					{
                    	loadDefaultValues();  //SS20110317
                    }
                   
                    else if(callFlag.equals("Lock_Info_self"))  //SB20200406
                    {
                    	loadDefaultValues();  //SB20091210
                    	getFormDetails();  //SB20091210
                    	fetchLockInfoSelf();  //SB20091210
                    }
                    
                    
//                    conn.close();
//                    conn = null;
                }
            }
        }
        catch(Exception e)
        {
//            //System.out.println("EXCEPTION:DataBean_Admin-->init()-->: " +e);
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
//					//System.out.println("rset is not close " + e);
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
//					//System.out.println("rset1 is not close " + e);
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
//					//System.out.println("rset2 is not close " + e);
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
//					//System.out.println("rset3 is not close " + e);
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
//					//System.out.println("rse4t is not close " + e);
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
//					//System.out.println("stmt is not close " + e);
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
//					//System.out.println("stmt1 is not close " + e);
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
					////System.out.println("stmt2 is not close " + e);
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
					////System.out.println("stmt3 is not close " + e);
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
					////System.out.println("stmt4 is not close " + e);
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
					////System.out.println("conn is not close " + e);
				}
			}
        }
    }
///////////////////////////////////////////////////////////////	
	
	

	public void setSet_to_date(String set_to_date) {
		this.set_to_date = set_to_date;
	}

	public Vector getVlog_date() {
		return Vlog_date;
	}

	public Vector getVlog_mach_id() {
		return Vlog_mach_id;
	}

	public Vector getVlog_time() {
		return Vlog_time;
	}

	public Vector getVremarks() {
		return Vremarks;
	}

	public String[][][] getLog_time() {
		return log_time;
	}

	public String[][] getMachine_id() {
		return machine_id;
	}

	public String[][][] getRemarks() {
		return remarks;
	}

	public String[][][] getForm_name() {
		return form_name;
	}
	
	public String[][] getTot_login() {
		return tot_login;
	}

	public String[][] getTot_logout() {
		return tot_logout;
	}

	public String[][] getLogin_time() {
		return login_time;
	}

	public String[][] getLogout_time() {
		return logout_time;
	}

	public String[][] getFirst_login() {
		return first_login;
	}

	public String[][] getLast_logout() {
		return last_logout;
	}

	public String[][] getTime_consumed() {
		return time_consumed;
	}

	public String[] getLog_date() {
		return log_date;
	}

	public String[][] getMach_id() {
		return mach_id;
	}

	public String[][] getUser_nm() {
		return user_nm;
	}
	
	public Vector getVmoduleCd() {
		return VmoduleCd;
	}

	public Vector getVmoduleName() {
		return VmoduleName;
	}

	public void setSet_module_id(String set_module_id) {
		this.set_module_id = set_module_id;
	}

	public Vector getVmapp_id() {
		return Vmapp_id;
	}

	public Vector getVparty_abr() {
		return Vparty_abr;
	}

	public Vector getV_rptnghv() {
		return V_rptnghv;
	}

	public Vector getV_rptnday_tot_ene1() {
		return V_rptnday_tot_ene1;
	}

	public String getCont_base() {
		return cont_base;
	}

	public String getCont_type() {
		return cont_type;
	}

	public String getDcq() {
		return dcq;
	}

	public String getEntry_totene() {
		return entry_totene;
	}

	public String getExit_totene() {
		return exit_totene;
	}

	public String getMdq() {
		return mdq;
	}

	public String getParty_name() {
		return party_name;
	}

	public Vector getV_imbalance() {
		return V_imbalance;
	}

	public Vector getVpriority() {
		return Vpriority;
	}

	public int getP_max() {
		return p_max;
	}

	public int getP_min() {
		return p_min;
	}

	public int getU_max() {
		return u_max;
	}

	public int getU_min() {
		return u_min;
	}
//	sra1-14/aug/09
	public String getALW_ADD() {
		return ALW_ADD;
	}

	public void setALW_ADD(String alw_add) {
		ALW_ADD = alw_add;
	}

	public String getALW_DEL() {
		return ALW_DEL;
	}

	public void setALW_DEL(String alw_del) {
		ALW_DEL = alw_del;
	}

	public String getALW_PRINT() {
		return ALW_PRINT;
	}

	public void setALW_PRINT(String alw_print) {
		ALW_PRINT = alw_print;
	}

	public String getALW_UPD() {
		return ALW_UPD;
	}

	public void setALW_UPD(String alw_upd) {
		ALW_UPD = alw_upd;
	}
    //

	public Vector getV_mod_group_id() {
		return V_mod_group_id;
	}

	public Vector getV_mod_group_nm() {
		return V_mod_group_nm;
	}

	public Vector getV_form_seq_no() {
		return V_form_seq_no;
	}

	public String getGrp_seq_no() {
		return grp_seq_no;
	}

	public String getGrp_cd() {
		return grp_cd;
	}

	public String getGrp_nm() {
		return grp_nm;
	}

	public void setGrp_cd(String grp_cd) {
		this.grp_cd = grp_cd;
	}

	public void setMod_group_id(String mod_group_id) {
		this.mod_group_id = mod_group_id;
	}

	public Vector getV_frm_rpt_flag() {
		return V_frm_rpt_flag;
	}

	//Following (2) Vector Getter Methods Has Been Defined By Samik Shah On 31st August, 2010 ...
	public Vector getV_module_start_index() {return V_module_start_index;}
	public Vector getV_module_end_index() {return V_module_end_index;}

	//Following (3) String Array Getter Methods Has Been Defined By Samik Shah On 17th September, 2010 ...
	public String[] getEmp_code() {return emp_code;}
	public String[] getEmp_name() {return emp_name;}
	public String[] getEmp_abbr() {return emp_abbr;}

	public String getUser_cd() {return user_cd;}
	public String getActivity_cd() {return activity_cd;}
	public String getRemark() {return remark;}

	public void setUser_cd(String user_cd) {this.user_cd = user_cd;}
	public void setActivity_cd(String activity_cd) {this.activity_cd = activity_cd;}

	public Vector getV_emp_id() {return V_emp_id;}
	public Vector getV_emp_nm() {return V_emp_nm;}

	public Vector getV_activity_cd() {
		return V_activity_cd;
	}

	public Vector getV_sn_closure_rem() {
		return V_sn_closure_rem;
	}

	public Vector getV_cargo_reconciliation_rem() {
		return V_cargo_reconciliation_rem;
	}

	//Following (2) String Array Getter Methods Has Been Defined By Samik Shah On 16th March, 2011 ...
	public String[] getFrom_date() {return from_date;}
	public String[] getTo_date() {return to_date;}
	
	Vector frm_dt_get = new Vector();
	Vector to_dt_get = new Vector();

	//Following (3) Vector Getter Methods Has Been Defined By Priyanka Sharma On 7th April, 2011 ...
	public Vector getV_sn_closure_request_rem() {return V_sn_closure_request_rem;}
	public Vector getV_LOA_closure_rem() {return V_LOA_closure_rem;}
	public Vector getV_LOA_closure_request_rem() {return V_LOA_closure_request_rem;}

	public Vector getV_lock_status() {
		return V_lock_status;
	}

	public Vector getV_group_nm() {
		return V_group_nm;
	}

	public void setSelect_date(String select_date) {
		this.select_date = select_date;
	}

	public Vector getV_log_dt() {
		return V_log_dt;
	}

	public Vector getV_log_time() {
		return V_log_time;
	}

	public Vector getV_emp_cd() {
		return V_emp_cd;
	}

	public Vector getV_log_uid() {
		return V_log_uid;
	}

	public Vector getV_flag() {
		return V_flag;
	}

	public String getServer_ip() {
		return server_ip;
	}

	public String getUp_path() {
		return up_path;
	}

	public String getDomain() {
		return domain;
	}

	public String getDown_path() {
		return down_path;
	}

	public String getLog_id() {
		return log_id;
	}

	public String getLog_pass() {
		return log_pass;
	}

	//Following (9) Vector Getter Methods has been defined By Samik Shah On 9th June, 2011 ...
	
	Vector emp_cds = new Vector();
	Vector emp_abrs = new Vector();
	Vector emp_nms = new Vector();
	Vector group_nms = new Vector();
	Vector group_cds = new Vector();
	public String group_nm4 = "";
	
	public Vector getV_Group_cd() {return V_Group_cd;}
	public Vector getV_Group_nm() {return V_Group_nm;}
	public Vector getV_User_cd() {return V_User_cd;}
	public Vector getV_User_nm() {return V_User_nm;}
	public Vector getV_Module_cd() {return V_Module_cd;}
	public Vector getV_Module_nm() {return V_Module_nm;}
	public Vector getV_Form_cd() {return V_Form_cd;}
	public Vector getV_Form_nm() {return V_Form_nm;}
	public Vector getV_Access_rights() {return V_Access_rights;}

	public Vector getV_provisional_seller_payment() {
		return V_provisional_seller_payment;
	}

	public Vector getV_final_seller_payment() {
		return V_final_seller_payment;
	}

	public Vector getV_provisional_custome_duty_payment() {
		return V_provisional_custome_duty_payment;
	}

	public Vector getV_final_custome_duty_payment() {
		return V_final_custome_duty_payment;
	}

	public Vector getV_approv_invoice() {
		return V_approv_invoice;
	}

	public void setStat(String stat) {this.stat = stat;} //JHP20120312

	public Map getM1() {
		return m1;
	}

	public Vector getEmp_cds() {
		return emp_cds;
	}

	public Vector getEmp_abrs() {
		return emp_abrs;
	}

	public Vector getEmp_nms() {
		return emp_nms;
	}

	public Vector getGroup_nms() {
		return group_nms;
	}

	public Vector getGroup_cds() {
		return group_cds;
	}

	public String getGroup_nm4() {
		return group_nm4;
	}

	public String getFrom_date_get() {
		return from_date_get;
	}

	public String getTo_date_get() {
		return to_date_get;
	}

	public Vector getFrm_dt_get() {
		return frm_dt_get;
	}

	public Vector getTo_dt_get() {
		return to_dt_get;
	}

	public Vector getV_module_nm_get() {
		return V_module_nm_get;
	}

	public Vector getV_module_id_get() {
		return V_module_id_get;
	}

	
	public void setMonth(String month) {
		this.month = month;
	}

	public void setYear(String year) {
		this.year = year;
	}

	
	public void setCd(String cd) {
		this.cd = cd;
	}

	

	public Vector getV_form_type() {
		return V_form_type;
	}

	public void setV_form_type(Vector v_form_type) {
		V_form_type = v_form_type;
	}

	public Vector getActivity_cd1() {
		return Activity_cd1;
	}

	public void setActivity_cd1(Vector activity_cd1) {
		Activity_cd1 = activity_cd1;
	}

	public Vector getEmp_cd() {
		return emp_cd;
	}

	public void setEmp_cd(Vector emp_cd) {
		this.emp_cd = emp_cd;
	}

	public Vector getEmp_act_cnt() {
		return emp_act_cnt;
	}

	public void setEmp_act_cnt(Vector emp_act_cnt) {
		this.emp_act_cnt = emp_act_cnt;
	}

	public Vector getReminder_dys() {
		return reminder_dys;
	}

	public void setReminder_dys(Vector reminder_dys) {
		this.reminder_dys = reminder_dys;
	}

	public Vector getRmk() {
		return rmk;
	}

	public void setRmk(Vector rmk) {
		this.rmk = rmk;
	}

	public Vector getEmp_name1() {
		return emp_name1;
	}

	public void setEmp_name1(Vector emp_name1) {
		this.emp_name1 = emp_name1;
	}

	public String getFrom_dt() {
		return from_dt;
	}

	public void setFrom_dt(String from_dt) {
		this.from_dt = from_dt;
	}

	public String getSet_to_dt() {
		return set_to_dt;
	}

	public void setSet_to_dt(String set_to_dt) {
		this.set_to_dt = set_to_dt;
	}

	public Vector getV_emp_sts() {
		return V_emp_sts;
	}

	public void setV_emp_sts(Vector v_emp_sts) {
		V_emp_sts = v_emp_sts;
	}

	public String getRd_flag() {
		return rd_flag;
	}

	public void setRd_flag(String rd_flag) {
		this.rd_flag = rd_flag;
	}

	public Vector getJOIN_RESIGN_DT() {
		return JOIN_RESIGN_DT;
	}

	public void setJOIN_RESIGN_DT(Vector jOIN_RESIGN_DT) {
		JOIN_RESIGN_DT = jOIN_RESIGN_DT;
	}

	public String getActflag() {
		return actflag;
	}

	public void setActflag(String actflag) {
		this.actflag = actflag;
	}

	public String getSel_month() {
		return sel_month;
	}

	public void setSel_month(String sel_month) {
		this.sel_month = sel_month;
	}

	public String getSel_year() {
		return sel_year;
	}

	public void setSel_year(String sel_year) {
		this.sel_year = sel_year;
	}
}//End Of Class DataBean_Admin ...