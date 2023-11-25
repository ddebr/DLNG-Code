//SS20100913:ADMIN:B1001
package com.hazira.hlpl.tlu.admin;

import java.io.PrintStream;
import java.sql.*;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//Code Reviewed by	: Mr. Samik Shah
//CR Date			: 20th September 2010
//CR Status  		: Consistent with BRS ... 

public class DataBean_Admin
{
	Connection conn;
    Statement stmt,stmt1,stmt2,stmt3;
    ResultSet rset,rset1,rset2,rset3;
    public String queryString;
    public String queryString1;
    public String queryString2;
    public String queryString3;
    public String method_name;
    public int u_min = 0;
	public int u_max = 0;
	public int p_min = 0;
	public int p_max = 0;
	
	Vector VmoduleCd=new Vector();
    Vector VmoduleName=new Vector();
	Vector Vpriority=new Vector();
	Vector VmodPath=new Vector();
	
	
	Vector Vlog_date=new Vector();
	Vector Vlog_time=new Vector();
	Vector Vlog_mach_id=new Vector();
	Vector Vremarks=new Vector();
	
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
	
	String write_permission ="";
	String delete_permission ="";
	String print_permission ="";
	String approve_permission ="";
	String audit_permission ="";
	String check_permission ="";
	String update_permission ="";
	String view_permission ="";
	
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
	
	
	public DataBean_Admin()
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
    public String getSet_module_id() {
		return set_module_id;
	}
    private String src="";

	public String set_group_id="0";
   //sra1-14/aug/09
    public String username;
    public String ALW_ADD;
    public String ALW_DEL;
    public String ALW_UPD;
    public String ALW_PRINT;
    public String emp_nmae="";
    
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
	String selModule="";
	String head_tab="";
	
		
	public String[] group_cd1,group_nm1,group_cd3,group_nm3,from_date,to_date;
	public String[] emp_id,emp_nm,emp_abr,emp_code,emp_name,emp_abbr;
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
	public Vector V_emp_abr = new Vector();
	public Vector V_email_id = new Vector();  //SB20100310
	public Vector V_status = new Vector();
	Vector V_emp_sts=new Vector();
	
	public Vector getV_emp_sts() {
		return V_emp_sts;
	}


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
	
	private Vector group_cd = new Vector();
	private Vector group_nm = new Vector();
	private Vector emp_cd = new Vector();
	private Vector emp_nme = new Vector();
	private Vector emp_flag = new Vector(); //Hiren_20210521
	private Vector emp_cnt = new Vector();
	
	String server_ip="";
	String up_path="";
	String down_path="";
	String domain="";
	String log_id="";
	String log_pass="";
	
	public void fetchServerSetting() throws SQLException
	{
		try
        {
        	method_name = "fetchServerSetting()";
           	queryString = "select SERVER_IP,UPLOAD_PATH,DOWNLOAD_PATH,DOMAIN_NM,LOGIN_ID," +
           			"LOGIN_PASSWORD from FMS7_FTP_SERVER_SETTING "; 
//           	System.out.println("SELECT FMS7_FTP_SERVER_SETTING : DATABEAN_ADMIN.java : "+queryString);
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
        }
        catch(Exception e)
        {
            System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... " + e);
            System.out.println("Query = "+queryString);
            e.printStackTrace();
        }
	}
	
	public void loadDefaultValues() throws SQLException
    {
        try
        {
        	method_name = "loadDefaultValues()";
            			
           	queryString = "select userid_min,userid_max,password_min,password_max from fms_password_life where eff_dt=(select max(eff_dt) from fms_password_life where eff_dt<=sysdate)"; 
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
            System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... " + e);
            System.out.println("Query = "+queryString);
            e.printStackTrace();
        }
    }//end of method loadDefaultValues() ...
    
    
	public void getFormDetails() throws SQLException
    {
    	try
        {
//    		System.out.println("ADMIN-MSG0001 :USER NAME :" +username  +"  ADMIN-MSG0002 :FORM ID :" +formid);
//    		System.out.println("ADMIN-MSG0002 :Form id :" + formid);
        	method_name = "getFormDetails()";
	    	
        	queryString = "select FORM_NAME from DLNG_FORM_MST where FORM_CD ='" + formid + "'";
//        	System.out.println("ADMIN-QRY0002 :SELECT :SEC_FORM_MST : DATABEAN_ADMIN.java : "+queryString);
        	
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
        }
    	catch(Exception e)
        {
            System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... " + e);
            System.out.println("Query = "+queryString);
   		 	e.printStackTrace();
        }
	}//end of method getFormDetails() ...
    

	public void getAllFormDetails() throws SQLException
    {
    	try
        {
    		method_name = "getAllFormDetails()";
	    	
        	queryString = "select DISTINCT(FORM_CD), FORM_NAME from DLNG_FORM_MST " +
        				  "where DOC_TYPE='F' ORDER BY FORM_NAME";
//        	System.out.println("ADMIN-QRY0002 :SELECT :SEC_FORM_MST : DATABEAN_ADMIN.java : "+queryString);
        	
        	rset = stmt.executeQuery(queryString);
			while(rset.next())
		    {
				FORMCODE.add(rset.getString(1)==null?"":rset.getString(1));
				FORMNAME.add(rset.getString(2)==null?"":rset.getString(2));
		    }
        }
    	catch(Exception e)
        {
            System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... " + e);
            System.out.println("Query = "+queryString);
   		 	e.printStackTrace();
        }
	}//end of method getAllFormDetails() ...

	
    public void fetchAddModuleDetails() throws SQLException 
    {
    	try
    	{
    		queryString="SELECT MODULE_CD,MODULE_NAME,MODULE_PRIORITY,DEF_PATH FROM DLNG_MODULE_MST ORDER BY MODULE_CD";
//    		System.out.println("ADMIN-QRY0035 :SELECT :SEC_FORM_MST, SEC_MODULE_MST : DATABEAN_ADMIN.java : "+queryString);
    		rset=stmt.executeQuery(queryString);
    		while(rset.next())
    		{
    			VmoduleCd.add(rset.getString(1));
    			VmoduleName.add(rset.getString(2));
    			Vpriority.add(rset.getString(3));
    			VmodPath.add(rset.getString(4)==null?"":rset.getString(4));
    		}
    	}
    	catch(Exception e)
    	{
    		System.out.println("EXCEPTION:DataBean_Admin-->fetchAddModuleDetails()--> "+e);
    	}
    }
    public void fetchAddGroupDetails() throws SQLException
    {
    	 try
    	 {
    		 method_name = "fetchAddGroupDetails()";
    		 queryString = " select  max(GROUP_CD)+1 from DLNG_ACCESS_GROUP_MST ";
//    		 System.out.println("ADMIN-QRY0020 :SELECT :DLNG_ACCESS_GROUP_MST : DATABEAN_ADMIN.java : "+queryString);
             rset = stmt.executeQuery(queryString);
             if(rset.next())
             {
            	 cnt=rset.getInt(1);
			 	 if(rset.getInt(1)==0)
			 	 {
			 		 cnt=1;
			 	 }
                 
             }
			 
            queryString = "select count(*) from SEC_EMP_PASSWORDS";
//            System.out.println("ADMIN-QRY0021 :SELECT :DLNG_EMP_PASSWORDS : DATABEAN_ADMIN.java : "+queryString);
            rset = stmt.executeQuery(queryString);
            if(rset.next())
            {
                 count1 = rset.getInt(1);
	        }
		    
            emp_id = new String[count1];
		    emp_nm = new String[count1];
		    count1 = 0;
   
		    queryString = "select x.EMP_CD, y.EMP_NM from SEC_EMP_PASSWORDS x,HR_EMP_MST y "+
            			  " where (DEL_FLAG='N' OR DEL_FLAG IS NULL) and x.EMP_CD = y.EMP_CD ";
//		    System.out.println("ADMIN-QRY0022 :SELECT :SEC_EMP_PASSWORDS, HR_EMP_MST : DATABEAN_ADMIN.java : "+queryString);
		    rset = stmt.executeQuery(queryString);
		    while(rset.next())
		    {
		    	emp_id[count1] = rset.getString(1);
				emp_nm[count1] = rset.getString(2);
				count1++;
		    }
		    count1=0;
   
		    queryString ="select count(*) from DLNG_MODULE_MST";
//		    System.out.println("ADMIN-QRY0023 :SELECT :DLNG_MODULE_MST : DATABEAN_ADMIN.java : "+queryString);
		    rset = stmt.executeQuery(queryString);
		    if(rset.next())
		    {
		    	count1 = rset.getInt(1);
		    }
            
		    module_cd = new String[count1];
			module_name = new String[count1];
	
			queryString = "select MODULE_CD,MODULE_NAME from DLNG_MODULE_MST";
//			 System.out.println("ADMIN-QRY0024 :SELECT :DLNG_MODULE_MST : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
			count1=0;
			
			while(rset.next())
			{
				module_cd[count1] = rset.getString(1);
				module_name[count1] = rset.getString(2);
				count1++;
			}
			count1 =0;
	
			queryString = "select count(*) from DLNG_ACCESS_GROUP_MST";
//			System.out.println("ADMIN-QRY0025 :SELECT :DLNG_ACCESS_GROUP_MST : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				count1 = rset.getInt(1);
			}
			
			group_id = new String[count1];
			group_name = new String[count1];
			count1 =0;
			
			queryString = "select GROUP_CD,GROUP_NAME from DLNG_ACCESS_GROUP_MST order by GROUP_CD";
//			System.out.println("ADMIN-QRY0026 :SELECT :DLNG_ACCESS_GROUP_MST : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				group_id[count1] = rset.getString(1);
				group_name[count1] = rset.getString(2);
				count1++;
			}
		 }
    	 catch(Exception e)
    	 {
    		 System.out.println("Exception in DataBean_Admin : "+method_name+" Method... "+e);
    		 System.out.println("Query = "+queryString);
    		 e.printStackTrace();
    	 }
    	 
	}//end of method fetchAddGroupDetails() ...

	public void fetchDetailsForGroupAllocation() throws SQLException
	{
		try
		{
			method_name = "fetchDetailsForGroupAllocation()";
			queryString = " select count(*)+1 from DLNG_ACCESS_GROUP_MST ";
//			System.out.println("ADMIN-QRY0044 :SELECT :DLNG_ACCESS_GROUP_MST : DATABEAN_ADMIN.java : "+queryString);
            rset = stmt.executeQuery(queryString);
            if(rset.next())
            {
            	count=rset.getString(1);
            }
			
            if(count.length()==1)
            	count = "00"+count;
			else if(count.length()==2)
				count = "0"+count;
			else
				count = count;
	
            queryString = "select count(*) from DLNG_ACCESS_GROUP_MST  ";
//            System.out.println("ADMIN-QRY0045 :SELECT :DLNG_ACCESS_GROUP_MST : DATABEAN_ADMIN.java : "+queryString);
            rset = stmt.executeQuery(queryString);
            if(rset.next())
            {
            	count1 = Integer.parseInt(rset.getString(1));
            }
			
            group_cd1 = new String[count1];
 			group_nm1 = new String[count1];
			count1=0;
	
			queryString = "select GROUP_CD,group_name from DLNG_ACCESS_GROUP_MST";
//			System.out.println("ADMIN-QRY0046 :SELECT :DLNG_ACCESS_GROUP_MST : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				group_cd1[count1]=rset.getString(1);
				group_nm1[count1]=rset.getString(2);
				count1++;
            }
			
			count1 = 0;
			
			queryString = "select count(*) from HR_EMP_MST where DEL_FLAG='N' OR DEL_FLAG IS NULL";//Hiren_20200423
//			System.out.println("ADMIN-QRY0047 :SELECT :SEC_EMP_PASSWORDS : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				count1 = rset.getInt(1);
			}
		   
			emp_id = new String[count1];
			emp_nm = new String[count1];
			
			count1 = 0;
   
			queryString = "select x.EMP_CD, y.EMP_NM from SEC_EMP_PASSWORDS x,HR_EMP_MST y "+
                 		  " where (y.DEL_FLAG='N' OR y.DEL_FLAG IS NULL) AND  x.EMP_CD = y.EMP_CD ORDER BY y.EMP_NM";	//MD20130514
//			System.out.println("ADMIN-QRY0048 :SELECT :SEC_EMP_PASSWORDS, HR_EMP_MST  : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				emp_id[count1] = rset.getString(1);
				emp_nm[count1] = rset.getString(2);
				count1++;
			}
          
			count1=0;
   
			queryString ="select count(*) from DLNG_MODULE_MST";
//			System.out.println("ADMIN-QRY0049 :SELECT :SEC_MODULE_MST  : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				count1 = rset.getInt(1);
			}
            
			module_cd = new String[count1];
			module_name = new String[count1];
	
			queryString = "select MODULE_CD, MODULE_NAME from DLNG_MODULE_MST";
//			System.out.println("ADMIN-QRY0050 :SELECT :SEC_MODULE_MST  : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
	
			count1=0;
	
			while(rset.next())
			{
				module_cd[count1] = rset.getString(1);
				module_name[count1] = rset.getString(2);
				count1++;
			}
	
			count1 =0;
	
			queryString = "select count(*) from DLNG_EMP_GROUP_DTL where  emp_cd ='"+set_emp_cd+"' ";
//			System.out.println("ADMIN-QRY0051 :SELECT :DLNG_ACCESS_GROUP_MST  : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				count1 = rset.getInt(1);
			}
	
			group_id = new String[count1];
			group_name = new String[count1];
	
			count1 =0;
			
			queryString = "select distinct(a.GROUP_CD),b.group_name from DLNG_EMP_GROUP_DTL a,DLNG_ACCESS_GROUP_MST b" +
					"  where  a.emp_cd ='"+set_emp_cd+"' and  b.group_Cd =a.group_cd ";
			System.out.println("ADMIN-QRY0052 :SELECT :DLNG_ACCESS_GROUP_MST  : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				group_id[count1] = rset.getString(1);
				group_name[count1] = rset.getString(2);
				count1++;
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchDetailsForGroupAllocation() ...

	Vector Vgroup_cd = new Vector();
	Vector Vgroup_nm = new Vector();
	public void fetchEmpGroupsAlloted() throws SQLException
	{
		try
		{
			method_name = "fetchEmpGroupsAlloted()";
			int i = 0;
			Vector group_cd= new Vector();
			
			queryString = "select distinct(GROUP_CD) from DLNG_EMP_GROUP_DTL where EMP_CD='"+set_emp_cd+"'";
//			System.out.println("queryString****"+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				count2++; 
				group_cd.add(rset.getString(1)==null?"":rset.getString(1));
			}
   
			if(count2==0)
			{
				
			}
			else
			{
				group_cd3 = new String[group_cd.size()];
				group_nm3 = new String[group_cd.size()];
				from_date = new String[group_cd.size()];
				to_date = new String[group_cd.size()];
				
				for(int j = 0 ; j < group_cd.size() ; j++) {
					queryString = "select x.GROUP_CD,y.group_name,TO_CHAR(x.FROM_DT,'dd/mm/yyyy'), " +
								  "TO_CHAR(x.TO_DT,'dd/mm/yyyy') from DLNG_EMP_GROUP_DTL x, DLNG_ACCESS_GROUP_MST y "+
	                 			  "where x.GROUP_CD=y.GROUP_CD and x.EMP_CD='"+set_emp_cd+"' and x.GROUP_CD='"+group_cd.elementAt(j)+"' " +
//	                 			  "ORDER BY x.FROM_DT,y.group_name";
								"ORDER BY x.TO_DT desc";
					System.out.println("SAMIK --> Group-Allocation Query = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						group_nm3[j] = rset.getString(2);
						group_cd3[j] = rset.getString(1);
						from_date[j] = rset.getString(3);
						to_date[j] = rset.getString(4);
						System.out.println(rset.getString(3)+"***"+rset.getString(4));
					}
				}
			}
   	 	}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchEmpGroupsAlloted() ...

	  public void fetchContract_new ()  
		{
				try
				{
//					System.out.println("Inside fetch contract");
					 queryString = "select a.mapping_id, b.party_abr,e.party_abr,c.rec_pt_abr,d.delv_pt_abr,a.ene_base,a.st_dt,a.end_dt,b.party_name,e.party_name from fms_cont_mst a,"
		                    +" fms_party_mst b,fms_party_mst e,fms_recpt_mst c,fms_delv_mst d where a.party_cd=b.party_cd and a.trans_cd=e.party_cd "
		                    +" and a.entry_pt_cd=c.rec_pt_cd and a.exit_pt_cd=d.delv_pt_cd  and to_date('"+set_date+"','dd/mm/yy') between cont_st_dt and cont_end_dt  ";
//					 System.out.println("QUERYSTRING: "+queryString);
					 rset=stmt.executeQuery(queryString);
					 int cnt = 0;
					 while(rset.next())
					  {
						 Vmapp_id.add(rset.getString(1));
						// Vparty_cd.add(rset.getString());
						  String sup=rset.getString(2);
						  String tran=rset.getString(3);
						  String entry=rset.getString(4);
						  String exit=rset.getString(5);
						  Vparty_abr.add(sup+"-"+tran+"-"+entry+"-"+exit);
						 // Vparty_abr_tran.add(tran+"-"+sup+"-"+entry+"-"+exit);
						 // ene_base=rset.getString(6);
						 // Vst_dt.add(rset.getString(7));
						 // Vend_dt.add(rset.getString(8));
						 // rec_pt_nm=entry;
						 // del_pt_nm=exit;
						 // supp_party_nm=rset.getString(9);
						 // trans_party_nm=rset.getString(10);
						  cnt++;
					  }
				}
				catch(Exception e)
				{
					System.out.println("EXCEPTION:DataBean_gasnom_entry-->fetchContract()-->FMS_CONTRACT_MST: "+e);
				}
		 }
	 
	public void fetchContract()
	{
		try
		{
			method_name = "fetchContract()";
						 
			queryString = "SELECT a.party_abr,a.party_name,a.party_cd,b.cont_no,TO_CHAR(b.st_dt,'yy'), "+
			"TO_CHAR(b.end_dt,'yy'),b.rec_pt_cd, c.rec_pt_abr  FROM  FMS_PARTY_MST a, FMS_CONTRACT_MST b, FMS_RECPT_MST c "+ 
			"WHERE TO_DATE('"+set_date+"','dd/mm/yy')>=TO_DATE(b.st_dt,'dd/mm/yy')   AND "+ 
			"TO_DATE('"+set_date+"','dd/mm/yy')<=TO_DATE(b.end_dt,'dd/mm/yy') AND a.party_cd=b.party_cd " +
            "AND b.rec_pt_cd=c.rec_pt_cd and a.type='S'";
//			System.out.println("ADMIN-QRY0072 :SELECT :FMS_PARTY_MST, FMS_CONTRACT_MST, FMS_RECPT_MST   : DATABEAN_ADMIN.java : "+queryString);
				
			//System.out.println("CONTRACTS VALID: "+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				V_cont_party_abr.add(rset.getString(1));	
				V_party_nm.add(rset.getString(2));
				V_party_cd.add(rset.getString(3));
				V_cont_no.add(rset.getString(4));//contract number
				V_cont_yy.add(rset.getString(5));//st year 
				V_cont_type.add("S");
				V_rec_pt_id.add(rset.getString(7));
				V_rec_pt_abr.add(rset.getString(8));
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchContract() ...
	
	public void fetchContractDtl()
	{
		try
		{
			method_name = "fetchContractDtl()";
			queryString = "SELECT a.cont_base,a.cont_type,b.party_name, "+
			"to_char(to_date(a.cont_st_dt,'dd/mm/yy'),'dd/mm/yy'),to_char(to_date(a.cont_end_dt,'dd/mm/yy'),'dd/mm/yy') "+
			",a.ene_unit,a.ene_base,a.nhv,a.ghv,a.dcq,a.mdq,tot_ene,tot_ene1 "+ 
			"FROM FMS_CONT_MST a,FMS_PARTY_MST b "+ 
			"where a.party_cd=b.party_cd and mapping_id = '"+set_party_cd+"' ";

//			System.out.println(queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
					cont_base=rset.getString(1);	
					cont_type=rset.getString(2);
					party_name=rset.getString(3);
					fm_dt=rset.getString(4);
					to_dt=rset.getString(5);
					eng_unit=rset.getString(6);
					eng_base=rset.getString(7);
					cnhv=rset.getString(8);
					cghv=rset.getString(9);
					dcq=rset.getString(10);
					mdq=rset.getString(11);
					entry_totene=rset.getString(12);
					exit_totene=rset.getString(13);
			}			
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchContractDtl() ...
	public void fetchRprSchList()
	{
		try
		{
			method_name = "fetchRprNomList()";
			queryString = " SELECT rev_no,nhv,ghv,schedule_id,entry_tot_ene,exit_tot_ene, to_char(gen_dt,'dd/mm/yy') " +
			"FROM FMS_DAILY_SCHEDULE WHERE mapping_id ='"+set_party_cd+"' and "+
			"schedule_dt=to_date('"+set_date+"','dd/mm/yy') order by rev_no";
			
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				V_rptnrev_no.add(rset.getString(1));
				V_rptnnhv.add(rset.getString(2));
				V_rptnghv.add(rset.getString(3));
				V_rptnnom_id.add(rset.getString(4));
				V_rptnday_tot_ene.add(rset.getString(5)==null?"0":rset.getString(5) );
				V_rptnday_tot_ene1.add(rset.getString(6)==null?"0":rset.getString(6));
				V_rptngen_dt.add(rset.getString(7));
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchRprNomList() ...
	
	public void fetchRprAllocList()
	{
		try
		{
			method_name = "fetchRprNomList()";
			 
			queryString = " SELECT alloc_id,entry_tot_ene,exit_tot_ene, imbalance " +
			" FROM fms_alloc_mst WHERE mapping_id ='"+set_party_cd+"' and "+
			" alloc_dt=to_date('"+set_date+"','dd/mm/yy')";
//			System.out.println(queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				//V_rptnrev_no.add(rset.getString(1));
				//V_rptnnhv.add(rset.getString(2));
				//V_rptnghv.add(rset.getString(3));
				V_rptnnom_id.add(rset.getString(1));
				V_rptnday_tot_ene.add(rset.getString(2)==null?"0":rset.getString(2) );
				V_rptnday_tot_ene1.add(rset.getString(3)==null?"0":rset.getString(3));
				V_imbalance.add(rset.getString(4)==null?"0":rset.getString(4));
				//V_rptngen_dt.add(rset.getString(7));
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchAllocNomList() ...
	
	public void fetchRprNomList()
	{
		try
		{
			method_name = "fetchRprNomList()";
			 
			queryString = " SELECT rev_no,nhv,ghv,nom_id,TOT_ENE,exit_tot_ene, to_char(gen_dt,'dd/mm/yy') " +
			"FROM FMS_DAILY_NOM WHERE mapping_id ='"+set_party_cd+"' and "+
			"nom_dt=to_date('"+set_date+"','dd/mm/yy') order by rev_no";
			
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				V_rptnrev_no.add(rset.getString(1));
				V_rptnnhv.add(rset.getString(2));
				V_rptnghv.add(rset.getString(3));
				V_rptnnom_id.add(rset.getString(4));
				V_rptnday_tot_ene.add(rset.getString(5)==null?"0":rset.getString(5) );
				V_rptnday_tot_ene1.add(rset.getString(6)==null?"0":rset.getString(6));
				V_rptngen_dt.add(rset.getString(7));
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchRprNomList() ...
	
	public void fetchContract2()
	{
		try
		{
			method_name = "fetchContract2()";
			
			queryString = "SELECT unique(a.party_cd),a.party_name  FROM  FMS_PARTY_MST a,  FMS_CONTRACT_MST b "+ 
			 "WHERE TO_DATE('"+set_date+"','dd/mm/yy')>=TO_DATE(b.st_dt,'dd/mm/yy')  AND "+
			  "TO_DATE('"+set_date+"','dd/mm/yy')<=TO_DATE(b.end_dt,'dd/mm/yy') AND a.party_cd=b.party_cd " +
			  		"and a.TYPE = 'T'";
			
			//System.out.println("fetchContract1- "+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
					V_cont_party_abr.add(rset.getString(1));	
					V_party_nm.add(rset.getString(2));
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchContract2() ...
	
	public void fetchContractDtl2()
	{
		try
		{
			method_name = "fetchContractDtl2()";
			
			queryString=" SELECT cont_no,ene_base,TO_CHAR(st_dt,'yy'),"+
				"TO_CHAR(end_dt,'yy'),rec_pt_cd, MDQ  FROM  FMS_CONTRACT_MST  "   +
				" WHERE TO_DATE('"+set_date+"','dd/mm/yy')>=TO_DATE(st_dt,'dd/mm/yy')   AND "   +
				" TO_DATE('"+set_date+"','dd/mm/yy')<=TO_DATE(end_dt,'dd/mm/yy') AND party_cd= '"+set_party_cd+"'";
				
			//System.out.println("fetchContract1- "+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				V_cont_no.add(rset.getString(1));
				V_ene_base_v.add(rset.getString(2));
				V_cont_yy.add(rset.getString(3));	
				V_cont_type.add("T");
				V_rec_pt_id.add(rset.getString(5));
				V_sch_dcq.add(rset.getString(6));
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchContractDtl2() ...
	
	public void fetchRprNomList2()
	{
		try
		{
			method_name = "fetchRprNomList2()";
			
			String pcno="";
			String pabr=set_party_cd;
			
			queryString =" SELECT CONT_NO  FROM   FMS_CONTRACT_MST  "   +
			" WHERE TO_DATE('"+set_date+"','dd/mm/yy')>=TO_DATE(st_dt,'dd/mm/yy')   AND "   +
			" TO_DATE('"+set_date+"','dd/mm/yy')<=TO_DATE(end_dt,'dd/mm/yy') AND party_cd= '"+set_party_cd+"'";
			
			//System.out.println("fetchContract1- "+queryString);
			rset=stmt.executeQuery(queryString);
			if(rset.next())
			{
				pcno=rset.getString(1);
			}
			
			queryString = " SELECT rev_no,nhv,nom_id,TOT_ENE,to_char(gen_dt,'dd/mm/yy') " +
			"FROM FMS_DAILY_NOM WHERE PARTY_CD='"+pabr+"' AND cont_no='"+pcno+"' and "+
			"nom_dt=to_date('"+set_date+"','dd/mm/yy') order by rev_no"  ;
			
			//System.out.println(queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
					V_rptnrev_no.add(rset.getString(1));
					V_rptnnhv.add(rset.getString(2));
					V_rptnnom_id.add(rset.getString(3));
					V_rptnday_tot_ene.add(rset.getString(4));
					V_rptngen_dt.add(rset.getString(5));
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchRprNomList2() ...
	
	public void fetchScheduleList()
	{
		try
		{
			method_name = "fetchScheduleList()";
			
			queryString = "select unique(PARTY_CD) from FMS_CONTRACT_MST ";
			
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				V_party_lst.add(rset.getString(1));
			}
			
			for(int i=0;i<V_party_lst.size();i++)
			{
				queryString = "select PARTY_NAME from FMS_PARTY_MST where PARTY_CD = '"+
				(String)V_party_lst.elementAt(i)+"' ";
				
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					V_party_nm_lst.add(rset.getString(1));
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchScheduleList() ...
	
	public void fetchSupplierTicketList()
	{
		try
		{
			method_name = "fetchSupplierTicketList()";
			
			count2 = 0;
			
            queryString = "select count(distinct(a.party_cd)) from " +
            				"FMS_PARTY_MST a, FMS_CONTRACT_MST b "+
            				"where a.party_cd=b.party_cd order by a.party_cd ";
            
            //System.out.println("COUNT QUERY: "+queryString);
            //Change made to remove parties who have made no contracts
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				count2=rset.getInt(1);
				
			}
			
            party_code = new String[count2];  
            party_desc = new String[count2];  
            
			queryString="select distinct(a.party_cd),a.party_name from " +
 			"FMS_PARTY_MST a, FMS_CONTRACT_MST b "+
 			"where a.party_cd=b.party_cd order by a.party_cd ";
			//System.out.println("FETCH REC QUERY: "+queryString);
			rset = stmt.executeQuery(queryString);
            int i=0;
            
			while(rset.next())
			{
				party_code[i] = rset.getString(1);
				party_desc[i] = rset.getString(2);
                i++;
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchSupplierTicketList() ...
	
	public void fetchRprPeriodicNomList()
	{
		try
		{
			method_name = "fetchRprPeriodicNomList()";
			
			Vector party=new Vector();
			StringTokenizer cd=new StringTokenizer(set_party_cd,"*");
			
			while(cd.hasMoreTokens())
			{
				party.add(cd.nextToken());
			}
			
			String pcd=""+party.elementAt(0);
			
			queryString = " SELECT rev_no,nhv,nom_id,to_char(gen_dt,'dd/mm/yy') " +
			"FROM FMS_PERIODICAL_NOM WHERE PARTY_CD='"+pcd+"' AND "+
			"nom_period=to_date('"+set_date+"','dd/mm/yy') order by nom_type,rev_no";
			
			//System.out.println(queryString);
			
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				V_rptnrev_no.add(rset.getString(1));
				V_rptnnhv.add(rset.getString(2));
				V_rptnnom_id.add(rset.getString(3));
				V_rptngen_dt.add(rset.getString(4));
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchRprPeriodicNomList() ...
	
	public void fetchUserStatusList()
	{
		try
		{
			method_name = "fetchUserStatusList()";
			
			queryString = " SELECT emp_cd,emp_nm,DECODE(del_flag,'Y','Disabled','Enabled'), EMP_ABR, EMAIL_ID " +
					" FROM HR_EMP_MST " +
					" ORDER BY EMP_NM ASC";
			//System.out.println("ADMIN-QRY0061 :SELECT :HR_EMP_MST : DATABEAN_ADMIN.java : "+queryString);
			//System.out.println(" - Samik writes query = "+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				V_emp_id.add(rset.getString(1)==null?"":rset.getString(1));
				V_emp_nm.add(rset.getString(2));
				V_status.add(rset.getString(3));
				V_emp_abr.add(rset.getString(4)==null?"":rset.getString(4));
				V_email_id.add(rset.getString(5)==null?"":rset.getString(5));
			}			
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchUserStatusList() ...
	
	public void fetchUserStatus()
	{
		try
		{
			method_name = "fetchUserStatus()";
			
			queryString=" SELECT emp_nm,DECODE(del_flag,'Y','Disabled','Enabled') FROM HR_EMP_MST where emp_cd='"+set_emp_cd+"'";
			System.out.println("ADMIN-QRY0062 :SELECT :HR_EMP_MST : DATABEAN_ADMIN.java : "+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
					f_emp_nm = rset.getString(1);
					f_status = rset.getString(2);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchUserStatus() ...
	
	
	public void fetchPasswordLifeDtl()
	{
		try
		{
			method_name = "fetchPasswordLifeDtl()";
			
			queryString = "select no_days,no_password,reminder_days,password_type,userid_min,userid_max,password_min,password_max,to_char(eff_dt,'dd/mm/yyyy') " +
						  "from FMS_PASSWORD_LIFE where eff_dt=(select max(eff_dt) from FMS_PASSWORD_LIFE where eff_dt<=sysdate)";
			System.out.println("ADMIN-QRY0053 :SELECT :FMS_PASSWORD_LIFE : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				no_days = rset.getString(1);
				no_password = rset.getString(2);
				reminder_days = rset.getString(3);
				password_type = rset.getString(4);
				userid_min = rset.getString(5);
				userid_max = rset.getString(6);
				password_min = rset.getString(7);
				password_max = rset.getString(8);
				eff_dt = rset.getString(9);
				pass_flag = "YES";
			}
			else
			{
				pass_flag = "NO";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchPasswordLifeDtl() ...
	
	public void fetchLockInfo() throws SQLException
	{
		try
        {
			method_name = "fetchLockInfo()";
			int i = 0;
			
            queryString = " SELECT COUNT(*) FROM SEC_EMP_PASSWORDS WHERE locked_flag='Y'";
            System.out.println("ADMIN-QRY0057 :SELECT :SEC_EMP_PASSWORDS : DATABEAN_ADMIN.java : "+queryString);
            //sravan
            // String querytest ="select alw_add from sec_group_form where form_cd=57";
            // rset = stmt.executeQuery(queryString);
            // if(rset.next())
            //// {
            //  all_add=rset.getString(1);
            // }
            // System.out.println(all_add);
        	//System.out.println(queryString);
            rset = stmt.executeQuery(queryString);
            if(rset.next())
            {
            	i = Integer.parseInt(rset.getString(1));
            }
			if(i>0)
			{
				emp_nm = new String[i];
				emp_id = new String[i];
				emp_abr = new String[i];
				
				i = 0;
				
				queryString = " SELECT A.emp_cd,emp_nm,emp_abr FROM HR_EMP_MST A,SEC_EMP_PASSWORDS B "+
				              " WHERE A.emp_cd=B.emp_cd AND locked_flag='Y'";
				System.out.println("ADMIN-QRY0058 :SELECT :HR_EMP_MST, SEC_EMP_PASSWORDS : DATABEAN_ADMIN.java : "+queryString);
				rset = stmt.executeQuery(queryString);
				
				while(rset.next())
				{
					emp_id[i] = rset.getString(1);
					emp_nm[i] = rset.getString(2);
					emp_abr[i] = rset.getString(3);
					i++;
				}
				cnt = i;
       //         System.out.println("nm"+emp_nm.toString());
       //         System.out.println("id"+emp_id.toString());
       //         System.out.println("ct"+cnt);
			}
			
			
			//Introduced By Samik Shah On 17th September, 2010 ...
			
			int count = 0;
			queryString = "SELECT COUNT(A.emp_cd) FROM HR_EMP_MST A";
			System.out.println("ADMIN-QRY0058 :SELECT :HR_EMP_MST, SEC_EMP_PASSWORDS : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
			if(rset.next())
			{
				count = rset.getInt(1);
			}
			
			if(count>0)
			{
				emp_code = new String[count];
				emp_name = new String[count];
				emp_abbr = new String[count];
				
				i = 0;
				
				queryString = " SELECT A.emp_cd,A.emp_nm,A.emp_abr FROM HR_EMP_MST A";
				System.out.println("EmployeeMaster No of User Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				
				while(rset.next())
				{
					emp_code[i] = rset.getString(1);
					emp_name[i] = rset.getString(2);
					emp_abbr[i] = rset.getString(3);
					++i;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchLockInfo() ...
	
	public void fetchMenuListDtl() throws SQLException
	{
		try
        {
			method_name = "fetchMenuListDtl()";
			
			queryString = " select FORM_CD,FORM_NAME,MODULE_CD from DLNG_FORM_MST order by form_name,MODULE_CD ";
//			System.out.println("ADMIN-QRY0016 :SELECT :DLNG_FORM_MST : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				V_sub_menu_id.add(rset.getString(1).trim());
				V_sub_menu_nm.add(rset.getString(2).trim());
			}
			
			queryString = " select MODULE_CD,MODULE_NAME from DLNG_MODULE_MST order by MODULE_CD ";
//			System.out.println("ADMIN-QRY0017 :SELECT :DLNG_MODULE_MST : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				V_module_id.add(rset.getString(1).trim());
				V_module_nm.add(rset.getString(2).trim());
			}
			
			if(!set_sub_menu_id.equals("0"))
			{
				queryString = " select FORM_NAME,MODULE_CD,CLASSPATH,NVL(DOC_TYPE,'F'),grp_seq_no,grp_cd,grp_nm from DLNG_FORM_MST "+
							" where FORM_CD='"+set_sub_menu_id+"' ";
//				System.out.println("ADMIN-QRY0018 :SELECT :DLNG_FORM_MST : DATABEAN_ADMIN.java : "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					sub_menu_nm = rset.getString(1).trim();
					module_id = rset.getString(2).trim();
					sub_menu_path = rset.getString(3).trim();
					doc_type = rset.getString(4).trim();
					if(rset.getString(5) == null)
					grp_seq_no ="0";
					else
					grp_seq_no = rset.getString(5).trim();
					
					if(rset.getString(6) == null)
					grp_cd ="0";
					else
					grp_cd = rset.getString(6).trim();
					
					if(rset.getString(7) == null)
					grp_nm ="0";
					else
					grp_nm  = rset.getString(7).trim();
				}
			}
			queryString1="";
			queryString1 = "select distinct(GRP_CD),GRP_NM from DLNG_FORM_MST where module_cd = '"+module_id+"'  order by grp_cd"; // FOR SUM MODULE GROUPS
//			System.out.println("ADMIN-QRYSY004 :SELECT :DLNG_MODULE_MST : DATABEAN_ADMIN.java : "+queryString1);
			rset = stmt.executeQuery(queryString1);
			while(rset.next())
			{
				if(rset.getString(1) != null)
				{
					V_mod_group_id.add(rset.getString(1)==null?"":rset.getString(1));
					V_mod_group_nm.add(rset.getString(2)==null?"":rset.getString(2));
				}
			}
			queryString = " select grp_seq_no , doc_type from DLNG_FORM_MST where module_cd = '"+module_id+"' and grp_cd = '"+mod_group_id+"' order by grp_seq_no ";
//			System.out.println("ADMIN-QRYSY003 :SELECT :DLNG_MODULE_MST : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				if(rset.getString(1)!=null)
				{
					V_form_seq_no.add(rset.getString(1).trim());
					V_frm_rpt_flag.add(rset.getString(2).trim());
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchMenuListDtl() ...
	

    public void fetchModuleListDtl() throws SQLException
        {
            try
            {
                method_name = "fetchModuleListDtl()";
                
                queryString = " select MODULE_CD,MODULE_NAME from DLNG_MODULE_MST order by MODULE_NAME ";
//                System.out.println("ADMIN-QRY0004 :SELECT :SEC_MODULE_MST : DATABEAN_ADMIN.java : "+queryString);
                rset = stmt.executeQuery(queryString);
                while(rset.next())
                {
                    V_module_id.add(rset.getString(1).trim());
                    V_module_nm.add(rset.getString(2).trim());
                }
                if(!set_module_id.equalsIgnoreCase("0")){
                if(!set_module_id.equalsIgnoreCase("")){
                queryString = " select to_number(max( nvl(FORM_CD+1,'0'))) from DLNG_FORM_MST ";//Hiren_20200421 where module_cd='"+set_module_id+"'";
//                System.out.println("ADMIN-QRY0005 :SELECT :SEC_FORM_MST : DATABEAN_ADMIN.java : "+queryString);
                rset = stmt.executeQuery(queryString);
                
                if(rset.next())
                {
                    sub_menu_id = rset.getString(1);
                    if(sub_menu_id!=null){
                   /* String no = sub_menu_id.substring(set_module_id.length());
                    int id = Integer.parseInt(no) + 1;
                        sub_menu_id = id+"" */;
                    }else{
                        sub_menu_id = "1";
                    }
                }
//                System.out.println("sub_menu_id***"+sub_menu_id);
               }    
            }   
                queryString1="";
    			queryString1 = "select distinct(GRP_CD),GRP_NM from DLNG_FORM_MST where module_cd = '"+set_module_id+"' order by grp_cd"; // FOR SUM MODULE GROUPS
    			System.out.println("ADMIN-QRYSY0096 :SELECT :DLNG_ACCESS_GROUP_MST : DATABEAN_ADMIN.java : "+queryString1);
    			rset = stmt.executeQuery(queryString1);
    			while(rset.next())
    			{
    				if(rset.getString(1) != null)
    				{
    					V_mod_group_id.add(rset.getString(1)==null?"":rset.getString(1));
    					V_mod_group_nm.add(rset.getString(2)==null?"":rset.getString(2));
    				}
    			}
    			
                queryString = "select GROUP_CD,GROUP_NAME from DLNG_ACCESS_GROUP_MST";
//                System.out.println("ADMIN-QRY0010 :SELECT :DLNG_ACCESS_GROUP_MST : DATABEAN_ADMIN.java : "+queryString);
                rset = stmt.executeQuery(queryString);
                while(rset.next())
                {
                    V_group_id.add(rset.getString(1)==null?"":rset.getString(1));
                    V_group_nm.add(rset.getString(2)==null?"":rset.getString(2));
                }
            }
            catch(Exception e)
            {
                System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
                System.out.println("Query = "+queryString);
                e.printStackTrace();
            }
        }//end of method fetchModuleListDtl() ...
	
	public void fetchAccessRightsList() throws SQLException
	{
		try
        {
			method_name = "fetchAccessRightsList()";
			String module_name = "";
			int count = 0;
			
			if(!set_module_id.equals("All"))
			{
                rset = stmt.executeQuery("select count(*) from DLNG_FORM_MST where MODULE_CD='"+set_module_id+"'");
//                System.out.println("ADMIN-QRY0004 :SELECT :SEC_FORM_MST : DATABEAN_ADMIN.java : ");
				if(rset.next())
				{
					queryString = " select form_cd,form_name,module_name,a.module_cd "+
				           " from DLNG_FORM_MST a,DLNG_MODULE_MST b "+
						   " where a.module_cd = b.module_cd and b.module_cd = '"+set_module_id+"' order by module_name,doc_type,form_name";
//					System.out.println("ADMIN-QRY0011 :SELECT :SEC_FORM_MST, SEC_MODULE_MST : DATABEAN_ADMIN.java : "+queryString);
				}
			}
			else
			{
				rset = stmt.executeQuery("select count(*) from DLNG_FORM_MST");
				if(rset.next())
				{
					queryString = " select form_cd,form_name,module_name,a.module_cd "+
					           " from DLNG_FORM_MST a,DLNG_MODULE_MST b "+
							   " where a.module_cd = b.module_cd order by module_name,doc_type,form_name"; 
//					System.out.println("ADMIN-QRY0012 :SELECT :SEC_FORM_MST, SEC_MODULE_MST : DATABEAN_ADMIN.java : "+queryString);
				}
			}
			
			rset = stmt.executeQuery(queryString);
			while(rset.next())
            {
				V_sub_menu_id.add(rset.getString(1)==null?"":rset.getString(1));
				V_sub_menu_nm.add(rset.getString(2)==null?"":rset.getString(2));
				if(!module_name.equals(rset.getString(3)==null?"":rset.getString(3)))
				{
					V_module_nm.add(rset.getString(3)==null?"":rset.getString(3));
					V_module_start_index.add(""+count);
					if(count>0)
					{
						V_module_end_index.add(""+(count-1));
					}
				}
				else
				{
					V_module_nm.add(" ");
				}
				module_name = rset.getString(3).trim();
				++count;
				V_module_id.add(rset.getString(4).trim());
			}
			
			int end_sz = V_module_end_index.size();
			
			for(int i=end_sz; i<V_module_start_index.size(); i++)
			{
				V_module_end_index.add(""+(count-1));
			}
			
			for(int i=0;i<V_sub_menu_id.size();i++)
			{
				queryString = "SELECT NVL(alw_view,'N'), NVL(alw_grant,'N'), NVL(alw_del,'N'), " +
                			"NVL(alw_add,'N'), NVL(alw_check,'N'), NVL(alw_authorize,'N'), NVL(alw_upd,'N'), NVL(alw_print,'N') "+
                			"FROM DLNG_GROUP_FORM "+
                			"WHERE group_cd='"+set_group_id+"' AND " +
                			"form_cd='"+V_sub_menu_id.elementAt(i)+"'";
//				System.out.println("ADMIN-QRY0013 :SELECT :SEC_GROUP_FORM : DATABEAN_ADMIN.java : "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					if(rset.getString(1).equals("Y"))
					{
						V_view_print_perm.add("checked");
						V_view_print_perm_value.add("Y");
					}
					else
					{
						V_view_print_perm.add(" ");
						V_view_print_perm_value.add("N");
					}
					
					if(rset.getString(2).equals("Y"))
					{
						V_grant_perm.add("checked");
						V_grant_perm_value.add("Y");
					}
					else
					{
						V_grant_perm.add(" ");
						V_grant_perm_value.add("N");
					}
					
					if(rset.getString(3).equals("Y"))
					{
						V_delete_perm.add("checked");
						V_delete_perm_value.add("Y");
					}
					else
					{
						V_delete_perm.add(" ");
						V_delete_perm_value.add("N");
					}
					
					if(rset.getString(4).equals("Y"))
					{
						V_add_perm.add("checked");
						V_add_perm_value.add("Y");
					}
					else
					{
						V_add_perm.add(" ");
						V_add_perm_value.add("N");
					}
					
					if(rset.getString(5).equals("Y"))
					{
						V_check_perm.add("checked");
						V_check_perm_value.add("Y");
					}
					else
					{
						V_check_perm.add(" ");
						V_check_perm_value.add("N");
					}
					
					if(rset.getString(6).equals("Y"))
					{
						V_authorize_perm.add("checked");
						V_authorize_perm_value.add("Y");
					}
					else
					{
						V_authorize_perm.add(" ");
						V_authorize_perm_value.add("N");
					}
					
					if(rset.getString(7).equals("Y"))
					{
						V_update_perm.add("checked");
						V_update_perm_value.add("Y");
					}
					else
					{
						V_update_perm.add(" ");
						V_update_perm_value.add("N");
					}
					
					if(rset.getString(8).equals("Y"))
					{
						V_print_perm.add("checked");
						V_print_perm_value.add("Y");
					}
					else
					{
						V_print_perm.add(" ");
						V_print_perm_value.add("N");
					}
				}
				else
				{
					V_view_print_perm.add(" ");
					V_view_print_perm_value.add("N");
					
					V_grant_perm.add(" ");
					V_grant_perm_value.add("N");
					
					V_delete_perm.add(" ");
					V_delete_perm_value.add("N");
					
					V_add_perm.add(" ");
					V_add_perm_value.add("N");
					
					V_check_perm.add(" ");
					V_check_perm_value.add("N");
					
					V_authorize_perm.add(" ");
					V_authorize_perm_value.add("N");
                 
					V_update_perm.add(" ");
					V_update_perm_value.add("N");
					
					V_print_perm.add(" ");
					V_print_perm_value.add("N");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchAccessRightsList() ...
	
	
	public void fetchAuditDetails() throws SQLException
	{
		//System.out.println("fetchAuditDetails()");
        int sz = 0;
		
		queryString = "SELECT count(distinct(TO_CHAR(LOG_DT,'DD/MM/YYYY'))) FROM DLNG_LOG_DETAILS WHERE " +
					  "LOG_DT BETWEEN TO_DATE('"+set_date+"','dd/mm/yyyy') AND TO_DATE('"+set_to_date+"','dd/mm/yyyy') AND " +
					  "EMP_CD='"+set_emp_cd+"' order by LOG_DT";
//		System.out.println("ADMIN-QRY0073 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
        rset=stmt.executeQuery(queryString);
		if(rset.next())
		{
			sz = rset.getInt(1);
		}
		
		log_date = new String[sz];
		mach_id = new String[sz][10];
		
		queryString = "SELECT distinct(TO_CHAR(LOG_DT,'DD/MM/YYYY')) FROM DLNG_LOG_DETAILS WHERE " +
					  "LOG_DT BETWEEN TO_DATE('"+set_date+"','dd/mm/yyyy') AND TO_DATE('"+set_to_date+"','dd/mm/yyyy') AND " +
					  "EMP_CD='"+set_emp_cd+"' order by TO_CHAR(LOG_DT,'DD/MM/YYYY')";
//		System.out.println("ADMIN-QRY0074 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
        rset = stmt.executeQuery(queryString);
		int g = 0;
		
		while(rset.next())
		{
			//Vlog_date.add(rset.getString(1));
			log_date[g] = rset.getString(1);
			++g;
		}
	
//		System.out.println("log_date.length = "+log_date.length+"\n");
		for(int i=0; i<log_date.length; i++)
		{
//			System.out.println("log_date["+i+"] = "+log_date[i]);
			if(log_date[i]!=null)
			{
				int j=0;
			
				queryString="SELECT DISTINCT(LOG_MACH_ID) FROM DLNG_LOG_DETAILS WHERE " +
							"LOG_DT=TO_DATE('"+log_date[i]+"','dd/mm/yyyy') AND EMP_CD='"+set_emp_cd+"'";
//				System.out.println("ADMIN-QRY0075 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
		
				rset=stmt.executeQuery(queryString);
				
				while(rset.next())
				{
					mach_id[i][j] = rset.getString(1);
					++j;
					if(j>=10)
					{
						break;
					}
				}
			}
		}
	
	    log_time = new String[sz][10][10000];
	    form_name = new String[sz][10][10000];
		remarks = new String[sz][10][10000];
		tot_login = new String[sz][10];
		tot_logout = new String[sz][10];
		login_time = new String[sz][10];
		logout_time = new String[sz][10];
		first_login = new String[sz][10];
		last_logout = new String[sz][10];
		time_consumed = new String[sz][10];
		
		for(int i=0; i<sz; i++)
		{
			String dt=log_date[i];
		
			for(int j=0; j<mach_id[i].length; j++)
			{
				int k = 0;
				String mac_id = mach_id[i][j];
				
				if(mac_id!=null)
				{
					queryString = "SELECT TO_CHAR(LOG_DT,'DD/MM/YYYY'),LOG_TIME,REMARKS,FORM_NAME,LOG_MACH_ID " +
								  "FROM DLNG_LOG_DETAILS WHERE " +
								  "LOG_DT=TO_DATE('"+dt+"','DD/MM/YYYY') AND " +
								  "EMP_CD='"+set_emp_cd+"' AND " +
								  "LOG_MACH_ID='"+mac_id+"' " +
								  "ORDER BY LOG_MACH_ID,LOG_TIME";
//					System.out.println("ADMIN-QRY0076 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
                    rset=stmt.executeQuery(queryString);
                    
					while(rset.next())
					{
						log_time[i][j][k] = rset.getString(2);
						remarks[i][j][k] = rset.getString(3);
						form_name[i][j][k] = rset.getString(4);
						k++;
					}
				}
			}	
		}
		
		for(int i=0;i<sz;i++)
		{
			String dt=log_date[i];
			for(int j=0; j<mach_id[i].length; j++)
			{
			    String mch_id=mach_id[i][j];
				//System.out.println("CHECK: "+mch_id);
				if(mch_id!=null)
				{
					try
					{
						queryString = "SELECT TO_CHAR(LOG_DT,'DD/MM/YYYY'),LOG_TIME,REMARKS,LOG_MACH_ID " +
									  "FROM DLNG_LOG_DETAILS WHERE " +
									  "LOG_DT=TO_DATE('"+dt+"','DD/MM/YYYY') AND REMARKS='Login' AND " +
									  "EMP_CD='"+set_emp_cd+"' AND LOG_MACH_ID='"+mch_id+"' " +
									  "ORDER BY LOG_TIME";
//						System.out.println("ADMIN-QRY0077 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
						rset=stmt.executeQuery(queryString);
						int x=0;
						if(rset.next())
						{
							first_login[i][j]=rset.getString(2);
					    }
						else
						{
							first_login[i][j]="00:00:00";
						}
						
						queryString = "SELECT TO_CHAR(LOG_DT,'DD/MM/YYYY'),LOG_TIME,REMARKS,LOG_MACH_ID " +
									  "FROM DLNG_LOG_DETAILS WHERE " +
									  "LOG_DT=TO_DATE('"+dt+"','DD/MM/YYYY') AND REMARKS='Logout' AND " +
									  "EMP_CD='"+set_emp_cd+"' AND LOG_MACH_ID='"+mch_id+"' " +
									  "ORDER BY LOG_TIME DESC";
//						System.out.println("ADMIN-QRY0078 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
						rset=stmt.executeQuery(queryString);
						x=0;
						if(rset.next())
						{
							last_logout[i][j]=rset.getString(2);
						}
						else
						{
							last_logout[i][j]="12:00:00";
						}
							
						queryString="select round(((to_date('"+last_logout[i][j]+"','HH24:MI:SS') - to_date('"+first_login[i][j]+"','HH24:MI:SS')))*24*60) from dual";
//						System.out.println("ADMIN-QRY0079 :SELECT :DUAL : DATABEAN_ADMIN.java : "+queryString);
						rset=stmt.executeQuery(queryString);
						if(rset.next())
						{
							time_consumed[i][j]=rset.getString(1);
						}
						
						queryString = "SELECT count(*) FROM DLNG_LOG_DETAILS WHERE " +
									  "LOG_DT=TO_DATE('"+dt+"','DD/MM/YYYY') AND REMARKS='Login' AND " +
									  "LOG_MACH_ID='"+mch_id+"' AND EMP_CD='"+set_emp_cd+"'";
//						System.out.println("ADMIN-QRY0080 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
						rset=stmt.executeQuery(queryString);
						if(rset.next())
						{
						   tot_login[i][j]=rset.getString(1);
						}
						else
						{
						   tot_login[i][j]="0";
						}
						
						queryString = "SELECT count(*) FROM DLNG_LOG_DETAILS WHERE " +
									  "LOG_DT=TO_DATE('"+dt+"','DD/MM/YYYY') AND REMARKS='Logout' AND " +
									  "LOG_MACH_ID='"+mch_id+"' AND EMP_CD='"+set_emp_cd+"'";
//						System.out.println("ADMIN-QRY0081 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
						rset=stmt.executeQuery(queryString);
						if(rset.next())
						{
							tot_logout[i][j]=rset.getString(1);
						}
						else
						{
							tot_logout[i][j]="0";
						}
                    }
					catch(Exception e)
					{
							System.out.println("EXCEPTION:DataBean_Admin-->fetchAuditDetails()-->SEC_LOG_DETAILS C: "+e);
					}
				}
			}
		}
		//System.out.println("end");
	}
	
	
	public void fetchAuditDetails1() throws SQLException
	{
		//System.out.println("fetchAuditDetails1()");
        int sz = 0;
		
		queryString = "SELECT count(distinct(TO_CHAR(LOG_DT,'DD/MM/YYYY'))) FROM DLNG_LOG_DETAILS WHERE " +
					  "LOG_DT BETWEEN TO_DATE('"+set_date+"','dd/mm/yyyy') AND TO_DATE('"+set_to_date+"','dd/mm/yyyy') AND " +
					  "FORM_CD='"+set_emp_cd+"' order by LOG_DT";
//		System.out.println("ADMIN-QRY0073 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
        rset=stmt.executeQuery(queryString);
		if(rset.next())
		{
			sz = rset.getInt(1);
		}
		
		log_date = new String[sz];
		mach_id = new String[sz][20];
		user_nm = new String[sz][20];
		
		queryString = "SELECT distinct(TO_CHAR(LOG_DT,'DD/MM/YYYY')) FROM DLNG_LOG_DETAILS WHERE " +
					  "LOG_DT BETWEEN TO_DATE('"+set_date+"','dd/mm/yyyy') AND TO_DATE('"+set_to_date+"','dd/mm/yyyy') AND " +
					  "FORM_CD='"+set_emp_cd+"' order by TO_CHAR(LOG_DT,'DD/MM/YYYY')";
//		System.out.println("ADMIN-QRY0074 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
        rset = stmt.executeQuery(queryString);
		int g = 0;
		
		while(rset.next())
		{
			//Vlog_date.add(rset.getString(1));
			log_date[g] = rset.getString(1);
			++g;
		}
	
//		System.out.println("log_date.length = "+log_date.length+"\n");
		for(int i=0; i<log_date.length; i++)
		{
//			System.out.println("log_date["+i+"] = "+log_date[i]);
			if(log_date[i]!=null)
			{
				int j=0;
			
				queryString="SELECT DISTINCT(EMP_CD) FROM DLNG_LOG_DETAILS WHERE " +
							"LOG_DT=TO_DATE('"+log_date[i]+"','dd/mm/yyyy') AND FORM_CD='"+set_emp_cd+"'";
//				System.out.println("ADMIN-QRY0075 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
		
				rset=stmt.executeQuery(queryString);
				
				while(rset.next())
				{
					mach_id[i][j] = rset.getString(1);
					String empcd = mach_id[i][j];
					if(empcd!=null)
					{
						queryString1 = "SELECT A.emp_nm,A.emp_abr FROM HR_EMP_MST A WHERE A.emp_cd='"+empcd+"'";
//						System.out.println("EmployeeMaster Fetch Query For Getting User Name = "+queryString1);
						rset1 = stmt1.executeQuery(queryString1);
						if(rset1.next())
						{
							user_nm[i][j] = rset1.getString(1);
						}
					}
					else
					{
						user_nm[i][j] = null;
					}
					
					++j;
					if(j>=20)
					{
						break;
					}
				}
			}
		}
	
	    log_time = new String[sz][20][10000];
	    form_name = new String[sz][20][10000];
		remarks = new String[sz][20][10000];
		tot_login = new String[sz][20];
		tot_logout = new String[sz][20];
		login_time = new String[sz][20];
		logout_time = new String[sz][20];
		first_login = new String[sz][20];
		last_logout = new String[sz][20];
		time_consumed = new String[sz][20];
		
		for(int i=0; i<sz; i++)
		{
			String dt=log_date[i];
		
			for(int j=0; j<mach_id[i].length; j++)
			{
				int k = 0;
				String mac_id = mach_id[i][j];
				
				if(mac_id!=null)
				{
					queryString = "SELECT TO_CHAR(LOG_DT,'DD/MM/YYYY'),LOG_TIME,REMARKS,LOG_MACH_ID " +
								  "FROM DLNG_LOG_DETAILS WHERE " +
								  "LOG_DT=TO_DATE('"+dt+"','DD/MM/YYYY') AND " +
								  "FORM_CD='"+set_emp_cd+"' AND " +
								  "EMP_CD='"+mac_id+"' " +
								  "ORDER BY LOG_MACH_ID,LOG_TIME";
//					System.out.println("ADMIN-QRY0076 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
                    rset=stmt.executeQuery(queryString);
                    
					while(rset.next())
					{
						log_time[i][j][k] = rset.getString(2);
						remarks[i][j][k] = rset.getString(3);
						form_name[i][j][k] = rset.getString(4);
						k++;
					}
				}
			}	
		}
		
		for(int i=0;i<sz;i++)
		{
			String dt=log_date[i];
			for(int j=0; j<mach_id[i].length; j++)
			{
			    String mch_id=mach_id[i][j];
				//System.out.println("CHECK: "+mch_id);
				if(mch_id!=null)
				{
					try
					{
						queryString = "SELECT TO_CHAR(LOG_DT,'DD/MM/YYYY'),LOG_TIME,REMARKS,LOG_MACH_ID " +
									  "FROM DLNG_LOG_DETAILS WHERE " +
									  "LOG_DT=TO_DATE('"+dt+"','DD/MM/YYYY') AND REMARKS='Login' AND " +
									  "FORM_CD='"+set_emp_cd+"' AND EMP_CD='"+mch_id+"' " +
									  "ORDER BY LOG_TIME";
//						System.out.println("ADMIN-QRY0077 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
						rset=stmt.executeQuery(queryString);
						int x=0;
						if(rset.next())
						{
							first_login[i][j]=rset.getString(2);
					    }
						else
						{
							first_login[i][j]="00:00:00";
						}
						
						queryString = "SELECT TO_CHAR(LOG_DT,'DD/MM/YYYY'),LOG_TIME,REMARKS,LOG_MACH_ID " +
									  "FROM DLNG_LOG_DETAILS WHERE " +
									  "LOG_DT=TO_DATE('"+dt+"','DD/MM/YYYY') AND REMARKS='Logout' AND " +
									  "FORM_CD='"+set_emp_cd+"' AND EMP_CD='"+mch_id+"' " +
									  "ORDER BY LOG_TIME DESC";
//						System.out.println("ADMIN-QRY0078 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
						rset=stmt.executeQuery(queryString);
						x=0;
						if(rset.next())
						{
							last_logout[i][j]=rset.getString(2);
						}
						else
						{
							last_logout[i][j]="12:00:00";
						}
							
						queryString="select round(((to_date('"+last_logout[i][j]+"','HH24:MI:SS') - to_date('"+first_login[i][j]+"','HH24:MI:SS')))*24*60) from dual";
//						System.out.println("ADMIN-QRY0079 :SELECT :DUAL : DATABEAN_ADMIN.java : "+queryString);
						rset=stmt.executeQuery(queryString);
						if(rset.next())
						{
							time_consumed[i][j]=rset.getString(1);
						}
						
						queryString = "SELECT count(*) FROM DLNG_LOG_DETAILS WHERE " +
									  "LOG_DT=TO_DATE('"+dt+"','DD/MM/YYYY') AND REMARKS='Login' AND " +
									  "EMP_CD='"+mch_id+"' AND FORM_CD='"+set_emp_cd+"'";
//						System.out.println("ADMIN-QRY0080 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
						rset=stmt.executeQuery(queryString);
						if(rset.next())
						{
						   tot_login[i][j]=rset.getString(1);
						}
						else
						{
						   tot_login[i][j]="0";
						}
						
						queryString = "SELECT count(*) FROM DLNG_LOG_DETAILS WHERE " +
									  "LOG_DT=TO_DATE('"+dt+"','DD/MM/YYYY') AND REMARKS='Logout' AND " +
									  "EMP_CD='"+mch_id+"' AND FORM_CD='"+set_emp_cd+"'";
//						System.out.println("ADMIN-QRY0081 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
						rset=stmt.executeQuery(queryString);
						if(rset.next())
						{
							tot_logout[i][j]=rset.getString(1);
						}
						else
						{
							tot_logout[i][j]="0";
						}
                    }
					catch(Exception e)
					{
							System.out.println("EXCEPTION:DataBean_Admin-->fetchAuditDetails()-->SEC_LOG_DETAILS C: "+e);
					}
				}
			}
		}
		//System.out.println("end");
	}
	
	
	public void fetchUserName()
	{
	//	System.out.println("fetchUserName()");
        queryString="Select emp_cd,emp_nm from hr_emp_mst where EMP_STATUS='Y' order by emp_nm";
//        System.out.println("ADMIN-QRY0072 :SELECT :HR_EMP_MST : DATABEAN_ADMIN.java : "+queryString);
      
		try
		{
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				V_emp_id.add(rset.getString(1));
				V_emp_nm.add(rset.getString(2));
			}
    //        System.out.println(V_emp_id);
    //        System.out.println(V_emp_nm);
		}catch(Exception e){
			System.out.println("EXCEPTION:DataBean_Admin-->fetchUserName()-->HR_EMP_MST: "+e);
		}
	}
	
	
	public void fetchActivityReminder()
	{			
		try
		{
			//if(!(user_cd.trim().equals("")) && user_cd!=null &&  !(activity_cd.trim().equals("")) && activity_cd!=null )
			//{
				queryString="Select reminder_days,remark from FMS7_REMINDER_PERMISSION_MST where user_cd='"+user_cd+"' and activity_cd='"+activity_cd+"' ";
//		        	System.out.println("reminder_days,remark  "+queryString);      
				rset=stmt.executeQuery(queryString);
				while(rset.next())
				{
					reminder_days = rset.getString(1);
					remark = rset.getString(2);
				}
			//}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("EXCEPTION:DataBean_Admin-->fetchActivityReminder()-->FMS7_REMINDER_PERMISSION_MST: "+e);
		}
	}
	
	
	public void fetchReminderAlert()
	{
		try
		{
			queryString = "Select activity_cd from FMS7_REMINDER_PERMISSION_MST where user_cd = '"+user_cd+"' ";
//			System.out.println("activity_cd" + queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				V_activity_cd.add(rset.getString(1).trim());
				if(rset.getString(1).trim().equalsIgnoreCase("SN Closure Request"))
				{
					queryString1="select A.FGSA_NO,A.SN_NO,A.CUSTOMER_CD,to_char(A.SIGNING_DT,'FMMonth DD, YYYY')," +
							"to_char(A.END_DT,'FMMonth DD, YYYY'),A.TCQ,A.RATE,B.CUSTOMER_NAME,B.CUSTOMER_ABBR " +
							"from FMS7_SN_MST A, FMS7_CUSTOMER_MST B where (sysdate >= A.END_DT) AND " +
							"(A.SN_CLOSURE_REQUEST IS NULL OR A.SN_CLOSURE_REQUEST='N') AND (A.CUSTOMER_CD = B.CUSTOMER_CD) AND " +
							"A.SN_REV_NO = (SELECT MAX(C.SN_REV_NO) FROM FMS7_SN_MST C WHERE A.FGSA_NO = C.FGSA_NO  AND " +
							"A.FGSA_REV_NO = C.FGSA_REV_NO  AND A.SN_NO = C.SN_NO AND A.CUSTOMER_CD = C.CUSTOMER_CD)";
//					System.out.println("A.FGSA_NO,A.SN_NO,A.CUSTOMER_CD,A.SIGNING_DT ==== " + queryString1);
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						//String fgsa_no = rset1.getString(1);
						String sn_no = rset1.getString(2);
						//String customer_cd = rset1.getString(3);
						//String signing_dt = rset1.getString(4);
						String end_dt = rset1.getString(5);
						//String tcq = rset1.getString(6);
						//String rate = rset1.getString(7);
						String customer_nm = rset1.getString(8);
						//String customer_abbr = rset1.getString(9);
						//String reminder="SN No: <b>"+sn_no+"</b> under FGSA No: <b>"+fgsa_no+"</b> of Customer: <b>"+customer_nm+"</b> signed on <b>"+signing_dt+"</b> having TCQ=<b>"+tcq+" MMBTU</b> and Rate=<b>"+rate+" ($/MMBTU)</b> should be closed on <b>"+end_dt+"</b>";
						String reminder="SN No: <b>"+sn_no+"</b> of Customer: <b>"+customer_nm+"</b>, Request for Closure should be generated on <b>"+end_dt+"</b>";
						V_sn_closure_request_rem.add(reminder);
					}
				}
				if(rset.getString(1).trim().equalsIgnoreCase("SN Closure Notice"))
				{
					queryString1="select A.FGSA_NO,A.SN_NO,A.CUSTOMER_CD,to_char(A.SIGNING_DT,'FMMonth DD, YYYY')," +
							"to_char(A.END_DT,'FMMonth DD, YYYY'),A.TCQ,A.RATE,B.CUSTOMER_NAME,B.CUSTOMER_ABBR " +
							"from FMS7_SN_MST A, FMS7_CUSTOMER_MST B where (sysdate >= A.END_DT) AND " +
							"((A.SN_CLOSURE_CLOSE IS NULL OR A.SN_CLOSURE_CLOSE='N') AND A.SN_CLOSURE_REQUEST = 'Y') AND (A.CUSTOMER_CD = B.CUSTOMER_CD) AND " +
							"A.SN_REV_NO = (SELECT MAX(C.SN_REV_NO) FROM FMS7_SN_MST C WHERE A.FGSA_NO = C.FGSA_NO  AND " +
							"A.FGSA_REV_NO = C.FGSA_REV_NO  AND A.SN_NO = C.SN_NO AND A.CUSTOMER_CD = C.CUSTOMER_CD)";
//					System.out.println("A.FGSA_NO,A.SN_NO,A.CUSTOMER_CD,A.SIGNING_DT  =  " + queryString1);
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						//String fgsa_no = rset1.getString(1);
						String sn_no = rset1.getString(2);
						//String customer_cd = rset1.getString(3);
						//String signing_dt = rset1.getString(4);
						String end_dt = rset1.getString(5);
						//String tcq = rset1.getString(6);
						//String rate = rset1.getString(7);
						String customer_nm = rset1.getString(8);
						//String customer_abbr = rset1.getString(9);
						//String reminder="SN No: <b>"+sn_no+"</b> under FGSA No: <b>"+fgsa_no+"</b> of Customer: <b>"+customer_nm+"</b> signed on <b>"+signing_dt+"</b> having TCQ=<b>"+tcq+" MMBTU</b> and Rate=<b>"+rate+" ($/MMBTU)</b> should be closed on <b>"+end_dt+"</b>";
						String reminder="SN No: <b>"+sn_no+"</b> of Customer: <b>"+customer_nm+"</b> should be closed on <b>"+end_dt+"</b>";
						V_sn_closure_rem.add(reminder);
					}
				}				
				if(rset.getString(1).trim().equalsIgnoreCase("LOA Closure Request"))
				{
					queryString1 = "select A.TENDER_NO,A.LOA_NO,A.CUSTOMER_CD,to_char(A.SIGNING_DT,'FMMonth DD, YYYY')," +
								   "to_char(A.END_DT,'FMMonth DD, YYYY'),B.CUSTOMER_NAME,B.CUSTOMER_ABBR " +
								   "from FMS7_LOA_MST A, FMS7_CUSTOMER_MST B where (sysdate >= A.END_DT) AND " +
								   "(A.LOA_CLOSURE_REQUEST IS NULL OR A.LOA_CLOSURE_REQUEST='N') AND (A.CUSTOMER_CD = B.CUSTOMER_CD) AND " +
								   "A.LOA_REV_NO = (SELECT MAX(C.LOA_REV_NO) FROM FMS7_LOA_MST C WHERE A.TENDER_NO = C.TENDER_NO  AND " +
								   "A.LOA_NO = C.LOA_NO AND A.CUSTOMER_CD = C.CUSTOMER_CD)";
//					System.out.println("A.TENDER_NO,A.LOA_NO,A.CUSTOMER_CD,A.SIGNING_DT  =  " + queryString1);
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						String tender_no = rset1.getString(1);
						String loa_no = rset1.getString(2);
						//String customer_cd = rset1.getString(3);
						//String signing_dt = rset1.getString(4);
						String end_dt = rset1.getString(5);
						String customer_nm = rset1.getString(6);
						//String customer_abbr = rset1.getString(7);
						//String reminder="SN No: <b>"+sn_no+"</b> under FGSA No: <b>"+fgsa_no+"</b> of Customer: <b>"+customer_nm+"</b> signed on <b>"+signing_dt+"</b> having TCQ=<b>"+tcq+" MMBTU</b> and Rate=<b>"+rate+" ($/MMBTU)</b> should be closed on <b>"+end_dt+"</b>";
						String reminder="LOA No: <b>"+loa_no+"</b> of Customer: <b>"+customer_nm+"</b>, Request for Closure should be generated on <b>"+end_dt+"</b>";
						V_LOA_closure_request_rem.add(reminder);
					}
				}
				if(rset.getString(1).trim().equalsIgnoreCase("LOA Closure Notice"))
				{
					queryString1 = "select A.TENDER_NO,A.LOA_NO,A.CUSTOMER_CD,to_char(A.SIGNING_DT,'FMMonth DD, YYYY')," +
								   "to_char(A.END_DT,'FMMonth DD, YYYY'),B.CUSTOMER_NAME,B.CUSTOMER_ABBR " +
								   "from FMS7_LOA_MST A, FMS7_CUSTOMER_MST B where (sysdate >= A.END_DT) AND " +
								   "((A.LOA_CLOSURE_CLOSE IS NULL OR A.LOA_CLOSURE_CLOSE='N') AND A.LOA_CLOSURE_REQUEST = 'Y') AND (A.CUSTOMER_CD = B.CUSTOMER_CD) AND " +
								   "A.LOA_REV_NO = (SELECT MAX(C.LOA_REV_NO) FROM FMS7_LOA_MST C WHERE A.TENDER_NO = C.TENDER_NO  AND " +
								   "A.LOA_NO = C.LOA_NO AND A.CUSTOMER_CD = C.CUSTOMER_CD)";
//					System.out.println("A.TENDER_NO,A.LOA_NO,A.CUSTOMER_CD,A.SIGNING_DT  =  " + queryString1);
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						String tender_no = rset1.getString(1);
						String loa_no = rset1.getString(2);
						//String customer_cd = rset1.getString(3);
						//String signing_dt = rset1.getString(4);
						String end_dt = rset1.getString(5);
						String customer_nm = rset1.getString(6);
						//String customer_abbr = rset1.getString(7);
						//String reminder="SN No: <b>"+sn_no+"</b> under FGSA No: <b>"+fgsa_no+"</b> of Customer: <b>"+customer_nm+"</b> signed on <b>"+signing_dt+"</b> having TCQ=<b>"+tcq+" MMBTU</b> and Rate=<b>"+rate+" ($/MMBTU)</b> should be closed on <b>"+end_dt+"</b>";
						String reminder = "LOA No: <b>"+loa_no+"</b> of Customer: <b>"+customer_nm+"</b> should be closed on <b>"+end_dt+"</b>";
						V_LOA_closure_rem.add(reminder);
					}
				}

				if(rset.getString(1).trim().equalsIgnoreCase("FGSA Renewal"))
				{
					queryString1="select A.FGSA_NO,A.REV_NO,A.CUSTOMER_CD,to_char(A.SIGNING_DT,'FMMonth DD, YYYY')," +
							"to_char(A.RENEWAL_DT,'FMMonth DD, YYYY'),B.CUSTOMER_NAME,B.CUSTOMER_ABBR " +
							"from FMS7_FGSA_MST A, FMS7_CUSTOMER_MST B where (sysdate >= A.RENEWAL_DT) AND (A.CUSTOMER_CD = B.CUSTOMER_CD) AND " +
							"A.REV_NO = (SELECT MAX(C.REV_NO) FROM FMS7_FGSA_MST C WHERE A.FGSA_NO = C.FGSA_NO  AND " +
							"A.CUSTOMER_CD = C.CUSTOMER_CD)";
//					System.out.println("A.FGSA_NO,A.SN_NO,A.CUSTOMER_CD,A.SIGNING_DT  =  " + queryString1);
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						String fgsa_no = rset1.getString(1);
						String rev_no = rset1.getString(2);
						//String customer_cd = rset1.getString(3);
						String signing_dt = rset1.getString(4);
						String renewal_dt = rset1.getString(5);
						String customer_nm = rset1.getString(6);
						//String customer_abbr = rset1.getString(7);
						String reminder="FGSA No: <b>"+fgsa_no+"</b> of Customer: <b>"+customer_nm+"</b> signed on <b>"+signing_dt+"</b> with Renewal Date as <b>"+renewal_dt+"</b>";								
						V_fgsa_renewal_rem.add(reminder);
					}
				}
				if(rset.getString(1).trim().equalsIgnoreCase("Cargo Reconciliation"))
				{
					queryString1="select A.CARGO_REF_NO,A.VESSEL_NM,A.QQ_NO,to_char(A.QQ_DT,'FMMonth DD, YYYY')," +
								  "A.DENSITY,A.QTY_MMBTU,A.QTY_MT,A.QTY_SCM " +
						         "from FMS7_CARGO_QQ_DTL A WHERE (sysdate>=A.QQ_DT) ORDER BY A.CARGO_REF_NO,A.QQ_NO ";
//					System.out.println("A.CARGO_REF_NO,A.VESSEL_NM,A.QQ_NO  =  " + queryString1);
					rset1=stmt1.executeQuery(queryString1);
					while(rset1.next())
					{
						String cargo_ref_no = rset1.getString(1);
						String vessel_nm = rset1.getString(2)==null?"":rset1.getString(2);
						//String customer_cd = rset1.getString(3);
						String qq_no = rset1.getString(3)==null?"":rset1.getString(3);
						String qq_dt = rset1.getString(4)==null?"":rset1.getString(4);
						//String end_dt = rset1.getString(5);
						//String tcq = rset1.getString(6);
						//String rate = rset1.getString(7);
						//String customer_nm = rset1.getString(8);
						//String customer_abbr = rset1.getString(9);
						String reminder="Cargo: <b>"+cargo_ref_no+"</b> with Vessel: <b>"+vessel_nm+"</b> and QQ No: <b>"+qq_no+"</b> having QQ Date: <b>"+qq_dt+"</b>";								
						V_cargo_reconciliation_rem.add(reminder);
					}
				}
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("EXCEPTION:DataBean_Admin-->fetchReminderAlert()-->FMS7_REMINDER_PERMISSION_MST: "+e);
		}
	}


	
	public void new_fetchUserStatusList()
	{
		try
		{
			int emp_nmae_cnt=0;
			method_name = "new_fetchUserStatusList()";
			
			queryString = "select count(*) from DLNG_ACCESS_GROUP_MST";
           // System.out.println("ADMIN-QRY0045 :SELECT :DLNG_ACCESS_GROUP_MST : DATABEAN_ADMIN.java : "+queryString);
            rset = stmt.executeQuery(queryString);
            if(rset.next())
            {
            	count1 = Integer.parseInt(rset.getString(1));
            }
			
            group_cd1 = new String[count1];
 			group_nm1 = new String[count1];
			count1=0;
	
			queryString = "select GROUP_CD,group_name from DLNG_ACCESS_GROUP_MST";
			//System.out.println("ADMIN-QRY0046 :SELECT :DLNG_ACCESS_GROUP_MST : DATABEAN_ADMIN.java : "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				group_cd1[count1]=rset.getString(1);
				group_nm1[count1]=rset.getString(2);
				count1++;
            }
			
			count1 = 0;
			
			queryString = " SELECT emp_cd,emp_nm,DECODE(del_flag,'Y','Disabled','Enabled'), EMP_ABR, EMAIL_ID,"
					+ "EMP_STATUS " +  //RG20170508
					" FROM HR_EMP_MST  where (lower(emp_nm) LIKE '%"+emp_nmae+"%' or upper(emp_nm) LIKE '%"+emp_nmae+"%' )  " +
					" ORDER BY EMP_NM ASC";
			//System.out.println("ADMIN-QRY0061 :SELECT :HR_EMP_MST : DATABEAN_ADMIN.java : "+queryString);
//			System.out.println(" - Samik writes query =--- "+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				V_emp_id.add(rset.getString(1));
				V_emp_nm.add(rset.getString(2)==null?"":rset.getString(2));
				V_status.add(rset.getString(3)==null?"":rset.getString(3));
				V_emp_abr.add(rset.getString(4)==null?"":rset.getString(4));
				V_email_id.add(rset.getString(5)==null?"":rset.getString(5));
				V_emp_sts.add(rset.getString(6)==null?"":rset.getString(6));  //RG20170508
			}
			
			for(int i=0; i<V_emp_id.size(); i++)
			{
				queryString = "SELECT NVL(LOCKED_FLAG,'N') FROM SEC_EMP_PASSWORDS " +
				 			  "WHERE EMP_CD = '"+V_emp_id.elementAt(i)+"' ";
				//System.out.println("ADMIN-QRY0057 :SELECT :SEC_EMP_PASSWORDS : DATABEAN_ADMIN.java : "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					V_lock_status.add(rset.getString(1));
				}
				else
				{
					V_lock_status.add("N");
				}
				
				int count = 0;
				String group_nm = "";
				
				queryString = "SELECT B.GROUP_NAME FROM SEC_EMP_GROUP_DTL A, " +
							  "DLNG_ACCESS_GROUP_MST B WHERE A.GROUP_CD=B.GROUP_CD " +
							  "AND A.EMP_CD = '"+V_emp_id.elementAt(i)+"' AND " +
							  "sysdate>=A.FROM_DT AND sysdate<=A.TO_DT ";
				//System.out.println("SELECT GROUP_NAME FROM DLNG_ACCESS_GROUP_MST ====> "+queryString);
				rset = stmt.executeQuery(queryString);				
				while(rset.next())
				{
					group_nm += rset.getString(1)+", ";
				}
				
				if(group_nm.length()>3)
				{
					group_nm = group_nm.substring(0,group_nm.length()-2);
				}
				
				V_group_nm.add(group_nm);				
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method new_fetchUserStatusList() ...

	
	public void fetchUserDetails()
	{
		try
		{
			method_name = "fetchUserDetails()";
			
			queryString = " SELECT emp_cd,emp_nm,DECODE(del_flag,'Y','Disabled','Enabled'), EMP_ABR, EMAIL_ID " +
					" FROM HR_EMP_MST where (lower(emp_nm) LIKE '%"+emp_nmae+"%' or upper(emp_nm) LIKE '%"+emp_nmae+"%' )  " +
					" ORDER BY EMP_NM ASC";
			//System.out.println("ADMIN-QRY0061 :SELECT :HR_EMP_MST : DATABEAN_ADMIN.java : "+queryString);
			//System.out.println(" - Samik writes query = "+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				V_emp_id.add(rset.getString(1));
				V_emp_nm.add(rset.getString(2));
				V_status.add(rset.getString(3));
				V_emp_abr.add(rset.getString(4));
				V_email_id.add(rset.getString(5));
			}
			
			for(int i=0; i<V_emp_id.size(); i++)
			{
				String group_nm = "";
//				System.out.println("select_date"+select_date);
				
				queryString = "SELECT B.GROUP_NAME FROM SEC_EMP_GROUP_DTL A, " +
							  "DLNG_ACCESS_GROUP_MST B WHERE A.GROUP_CD=B.GROUP_CD " +
							  "AND A.EMP_CD = '"+V_emp_id.elementAt(i)+"' AND " +
							  "A.FROM_DT<=to_date('"+select_date+"','dd/mm/yyyy') AND " +
							  "A.TO_DT>=to_date('"+select_date+"','dd/mm/yyyy') ";
//				System.out.println("SELECT GROUP_NAME FROM DLNG_ACCESS_GROUP_MST ====> "+queryString);
				rset = stmt.executeQuery(queryString);				
				while(rset.next())
				{
					group_nm += rset.getString(1)+", ";
				}
				
				if(group_nm.length()>3)
				{
					group_nm = group_nm.substring(0,group_nm.length()-2);
				}
				
				V_group_nm.add(group_nm);				
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//	end of method fetchUserDetails() ...
	
	public void fetchUserLoggedInDetails()
	{
		try
		{
			method_name = "fetchUserLoggedInDetails()";
			
			queryString = "SELECT to_char(A.LOG_DT,'DD/MM/YYYY'),A.LOG_TIME,A.LOG_UID, A.EMP_CD " +
						  "FROM SEC_LOG_DETAILS A WHERE A.REMARKS='Login' AND A.LOG_TIME =(SELECT MAX(B.LOG_TIME) " +
						  "FROM SEC_LOG_DETAILS B WHERE B.LOG_DT = to_date(to_char(sysdate,'dd/mm/yyyy'),'dd/mm/yyyy') " +
						  "AND A.EMP_CD = B.EMP_CD AND A.REMARKS= B.REMARKS) ORDER BY A.LOG_TIME";
//			System.out.println("ADMIN-QRY0061 :SELECT :SEC_LOG_DETAILS : DATABEAN_ADMIN.java : "+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				V_log_dt.add(rset.getString(1));
				V_log_time.add(rset.getString(2));
				V_log_uid.add(rset.getString(3));
				V_emp_cd.add(rset.getString(4));
			}
			
			for(int i=0; i<V_emp_cd.size(); i++)
			{
				queryString = "SELECT EMP_NM FROM HR_EMP_MST WHERE EMP_CD = '"+V_emp_cd.elementAt(i)+"' ";
//				System.out.println("SELECT EMP_NM FROM HR_EMP_MST ====> "+queryString);
				rset = stmt.executeQuery(queryString);				
				if(rset.next())
				{
					V_emp_nm.add(rset.getString(1));
				}
				else
				{
					V_emp_nm.add("");
				}
				
				queryString1 =  "SELECT LOG_DT FROM SEC_LOG_DETAILS WHERE " +
								"REMARKS = 'Logout' AND EMP_CD = '"+V_emp_cd.elementAt(i)+"' " +
								"AND LOG_TIME >= '"+V_log_time.elementAt(i)+"' " +
								"AND LOG_DT = to_date('"+V_log_dt.elementAt(i)+"','dd/mm/yyyy') ";
//				System.out.println("SELECT LOGOUT FROM SEC_LOG_DETAILS   =   "+queryString1);
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next())
				{
					V_flag.add("Y");
				}
				else
				{
					V_flag.add("N");
				}
//				System.out.println("V_flag   =   "+V_flag);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method fetchUserLoggedInDetails() ...
	
	
	public void generateAccessRightsMatrix()
	{
		try
		{
			method_name = "generateAccessRightsMatrix()";
			
			queryString = "SELECT DISTINCT A.group_cd, B.group_name " +
						  "FROM DLNG_EMP_GROUP_DTL A, DLNG_ACCESS_GROUP_MST B " +
						  "WHERE A.group_cd=B.group_cd AND " +
						  "A.emp_cd IS NOT NULL AND A.emp_cd!=0 AND " +
						  "(sysdate BETWEEN A.from_dt AND A.to_dt) " +
						  "ORDER BY B.group_name";
//			System.out.println("Distinct Live Group List Query = "+queryString);
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
							  "FROM DLNG_EMP_GROUP_DTL A, HR_EMP_MST B " +
							  "WHERE A.emp_cd=B.emp_cd AND " +
							  "A.group_cd="+V_Group_cd.elementAt(i)+" AND " +
							  "(sysdate BETWEEN A.from_dt AND A.to_dt) " +
							  "ORDER BY B.emp_nm";
//				System.out.println("SELECT EMP_NM FROM HR_EMP_MST ====> "+queryString);
				rset = stmt.executeQuery(queryString);				
				while(rset.next())
				{
					user_cd += (rset.getString(1)==null?"0":rset.getString(1))+",";
					user_nm += (rset.getString(2)==null?" ":rset.getString(2))+",";
				}
				
				V_User_cd.add(user_cd.substring(0,user_cd.length()-1));
				V_User_nm.add(user_nm.substring(0,user_nm.length()-1));
				
//				System.out.println("V_Group_nm.elementAt("+i+") = "+V_Group_nm.elementAt(i));
//				System.out.println("V_User_nm.elementAt("+i+") = "+V_User_nm.elementAt(i));
			}
			
			queryString = "SELECT DISTINCT A.form_cd, A.form_name, A.module_cd, " +
						  "B.module_name, B.module_priority, A.grp_cd, A.grp_seq_no " +
						  "FROM DLNG_FORM_MST A, DLNG_MODULE_MST B " +
						  "WHERE A.module_cd=B.module_cd AND " +
						  "A.form_name IS NOT NULL " +
						  "ORDER BY B.module_priority, A.grp_cd, A.grp_seq_no";
//			System.out.println("Distinct Form Name List Query = "+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				V_Form_cd.add(rset.getString(1)==null?"0":rset.getString(1));
				V_Form_nm.add(rset.getString(2)==null?" ":rset.getString(2));
				V_Module_cd.add(rset.getString(3)==null?"0":rset.getString(3));
				V_Module_nm.add(rset.getString(4)==null?" ":rset.getString(4));
			}
			
			for(int i=0; i<V_Form_cd.size(); i++)
			{
				Vector access_rights = new Vector();
				//System.out.println("V_Module_nm.elementAt("+i+") = "+V_Module_nm.elementAt(i));
				//System.out.println("V_Form_nm.elementAt("+i+") = "+V_Form_nm.elementAt(i));
				for(int j=0; j<V_Group_cd.size(); j++)
				{
					queryString = "SELECT NVL(A.alw_view,'N'), NVL(A.alw_add,'N'), NVL(A.alw_upd,'N') " +
								  "FROM DLNG_GROUP_FORM A " +
								  "WHERE A.group_cd="+V_Group_cd.elementAt(j)+" AND " +
								  "A.form_cd="+V_Form_cd.elementAt(i)+"";
//					System.out.println("Access Rights List Query = "+queryString);
					rset=stmt.executeQuery(queryString);
					if(rset.next())
					{
						if(rset.getString(1).trim().equalsIgnoreCase("Y"))
						{
							access_rights.add("YES");
						}
						else
						{
							access_rights.add("NO");
						}
						
						if(rset.getString(2).trim().equalsIgnoreCase("Y"))
						{
							access_rights.add("YES");
						}
						else
						{
							access_rights.add("NO");
						}
						
						if(rset.getString(3).trim().equalsIgnoreCase("Y"))
						{
							access_rights.add("YES");
						}
						else
						{
							access_rights.add("NO");
						}
					}
					else
					{
						access_rights.add("NO");
						access_rights.add("NO");
						access_rights.add("NO");
					}
				}
				
				V_Access_rights.add(access_rights);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Admin : "+method_name+" Method ... "+e);
			System.out.println("Query = "+queryString);
			e.printStackTrace();
		}
	}//end of method generateAccessRightsMatrix() ...
	
	
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
                    
                    if(callFlag.equalsIgnoreCase("Add_User"))  //SB20091210
					{
                    	loadDefaultValues();  //SB20091210
                    	getFormDetails();  //SB20091210
                    	fetchUserStatusList();  //SB20100302
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
                    else if(callFlag.equalsIgnoreCase("Add_Group"))  //SB20091210
					{
                    	loadDefaultValues();  //SB20091210
                    	getFormDetails();  //SB20091210
                    	fetchAddGroupDetails();  //SB20091210
                  	}
                    else if(callFlag.equalsIgnoreCase("Add_Module"))  //SB20091210
                    {
                    	getFormDetails();  //SB20091210
                    	fetchAddModuleDetails();  //SB20091210
                    }
                    else if(callFlag.equalsIgnoreCase("Allocate_Group"))  //SB20091210
					{
                    	loadDefaultValues();  //SB20091210
                    	getFormDetails();  //SB20091210
                    	fetchDetailsForGroupAllocation();  //SB20091210
//                	    System.out.println("set_emp_cd***"+set_emp_cd);
                    	if(set_emp_cd!=null && !set_emp_cd.equals("0") && !set_emp_cd.trim().equals(""))
                	    {
                	    	fetchEmpGroupsAlloted();
                	    }
                    	fetchGroupWiseEmp();
					}
                    else if(callFlag.equals("Rpr_Nom_List"))
    				{	
                    	loadDefaultValues();
                    	getFormDetails();
    					fetchContract_new();
    					
    					if(!set_party_cd.equals("select"))
    					{
    						fetchContractDtl();        
    						fetchRprNomList();
    					}
    				}
                    else if(callFlag.equals("Rpr_Nom_List_Trans"))
    				{	
                    	loadDefaultValues();
                    	getFormDetails();
    					fetchContract2();
    					
    					if(!set_party_cd.equals("select"))
    					{
    						fetchContractDtl2();  
    						fetchRprNomList2();
    					}
    				}
                    else if(callFlag.equals("Schedule_List"))
    				{	
                    	loadDefaultValues();
                    	getFormDetails();
                    	fetchContract_new();
                    	//fetchScheduleList();
                    	if(!set_party_cd.equals("select"))
    					{
    						fetchContractDtl();        
    						fetchRprSchList();
    					}
    				}
                    else if(callFlag.equals("Supplier_Ticket"))
    				{	
                    	loadDefaultValues();
                    	getFormDetails();
                    	//fetchSupplierTicketList();
                    	fetchContract_new();
                    	if(!set_party_cd.equals("select"))
    					{
    						fetchContractDtl();        
    						fetchRprAllocList();
    					}
    				}
                    else if(callFlag.equals("Rpr_Periodic_Nom_List"))  //SB20091210
    				{	
                    	loadDefaultValues();  //SB20091210
                    	getFormDetails();  //SB20091210
    					fetchContract();  //SB20091210
    					
    					if(!set_party_cd.equals("select"))
    					{
    						fetchContractDtl();        
    						fetchRprPeriodicNomList();
    					}
    				}
                    else if(callFlag.equals("User_Status_List"))  //SB20091210
    				{	
                    	loadDefaultValues();  //SB20091210
                    	getFormDetails();  //SB20091210
                    	fetchUserStatusList();  //SB20091210
    				}
                    else if(callFlag.equals("User_Status"))  //SB20091210
    				{	
                    	loadDefaultValues();  //SB20091210
                    	getFormDetails();  //SB20091210
                    	fetchUserStatus();  //SB20091210
    				}
                    else if(callFlag.equals("Password_Life_Dtl"))  //SB20091210
                    {
                    	loadDefaultValues();  //SB20091210
                    	getFormDetails();  //SB20091210
                    	fetchPasswordLifeDtl();  //SB20091210
                    }
                    else if(callFlag.equals("Lock_Info"))  //SB20091210
                    {
                    	loadDefaultValues();  //SB20091210
                    	getFormDetails();  //SB20091210
                    	fetchLockInfo();  //SB20091210
                    }
                    else if(callFlag.equals("Menu_List_Dtl"))  //SB20091210
                    {
                    	loadDefaultValues();  //SB20091210
                    	getFormDetails();  //SB20091210
                    	fetchMenuListDtl();  //SB20091210
                    }
                    else if(callFlag.equals("Module_List_Dtl"))  //SB20091210
                    {
                    	loadDefaultValues();  //SB20091210
                    	getFormDetails();  //SB20091210
                    	fetchModuleListDtl();  //SB20091210
                    }
                    else if(callFlag.equals("Access_Rights_List"))  //SB20091210
                    {
                    	loadDefaultValues();//SB20091210
                    	getFormDetails();//SB20091210
                    	fetchAccessRightsList();//SB20091210
                    }
                    else if(callFlag.equalsIgnoreCase("AuditTrails"))  //SB20091210
                    {
                    	getFormDetails();//SB20091210
                    	//fetchUserName();
                    	fetchInfoLogUser();
                    	if(!set_to_date.equalsIgnoreCase("") && !set_to_date.equalsIgnoreCase(" ") && !set_to_date.equalsIgnoreCase(null))
                    	{
                    		fetchAuditDetails();//Modified By Samik Shah On 1st February, 2011 ...
                    	}
                    }
                    else if(callFlag.equalsIgnoreCase("AuditTrails1"))  //Introduced By Samik Shah On 1st February, 2011 ...
                    {
                    	// System.out.println(" --------   AuditTrails1 ");
                    	getFormDetails();//SB20091210
                    	getAllFormDetails(); //Introduced By Samik Shah On 1st February, 2011 ...
                    	if(!set_to_date.equalsIgnoreCase("") && !set_to_date.equalsIgnoreCase(" ") && !set_to_date.equalsIgnoreCase(null))
                    	{
                    		fetchAuditDetails1(); //Introduced By Samik Shah On 1st February, 2011 ...
                    	}
                    }
                    else if(callFlag.equalsIgnoreCase("access_rights"))  //SB20091210
					{
                    	getFormDetails();  //SB20091210
					}
                    else if(callFlag.equalsIgnoreCase("Activity_Reminder"))  //Priyanka 20110110
					{
                    	fetchUserName();  //Priyanka 20110110
                    	fetchActivityReminder(); //Priyanka 20110110
					}
                    else if(callFlag.equalsIgnoreCase("Reminders_Alert"))  //Priyanka 20110110
					{
                    	// fetchReminderAlert();  // BY SM 08-09-2012 //Priyanka 20110110
					}
                    else if(callFlag.equalsIgnoreCase("Add_User_New"))  //PS11042011
					{
                    	loadDefaultValues();  	//PS11042011
                    	getFormDetails();  		//PS11042011
                    	new_fetchUserStatusList();  //PS11042011
                    } 
                    else if(callFlag.equalsIgnoreCase("USER DETAILS"))  //PS13042011
					{
                    	fetchUserDetails();  	//PS13042011
                    }
                    else if(callFlag.equalsIgnoreCase("USER LOGGED IN DETAILS"))  //PS13042011
					{
                    	fetchUserLoggedInDetails();  	//PS13042011
                    }
                    else if(callFlag.equalsIgnoreCase("GENERATE_ACCESS_RIGHTS_MATRIX"))
                    {
                    	generateAccessRightsMatrix();
                    }
                    conn.close();
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("EXCEPTION:DataBean_Admin-->init()-->: " +e);
        }
    }

	private void fetchGroupWiseEmp()throws SQLException,Exception {
		try {
			
			queryString="select group_cd,group_name from DLNG_ACCESS_GROUP_MST order by group_cd";
			rset = stmt.executeQuery(queryString);
			while (rset.next()) {
				group_cd.add(rset.getString(1)==null?"":rset.getString(1));
				group_nm.add(rset.getString(2)==null?"":rset.getString(2));
			}
			
			for(int i = 0 ; i < group_cd.size() ; i++) {
				int cnt=0;
				queryString1="select emp_cd from DLNG_EMP_GROUP_DTL where group_cd='"+group_cd.elementAt(i)+"'";
				rset1 = stmt1.executeQuery(queryString1);
				while (rset1.next()) {
					cnt++;
					emp_cd.add(rset1.getString(1)==null?"":rset1.getString(1));
					queryString="select emp_nm,del_flag from hr_emp_mst where emp_cd='"+rset1.getString(1)+"' ";
					rset2 = stmt2.executeQuery(queryString);
					if(rset2.next()) {
						emp_nme.add(rset2.getString(1)==null?"":rset2.getString(1));
						emp_flag.add(rset2.getString(2)==null?"":rset2.getString(2)); //Hiren_20210521
					}
				}
				emp_cnt.add(cnt);
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fetchInfoLogUser() {
		try {
			queryString = "select distinct(emp_cd) from DLNG_LOG_DETAILS order by emp_cd";
//			System.out.println(queryString);
			rset = stmt.executeQuery(queryString);
			while (rset.next()) {
				V_emp_id.add(rset.getString(1)==null?"":rset.getString(1));
				queryString1="select emp_nm from hr_emp_mst where emp_cd='"+rset.getString(1)+"' ";
				rset1 = stmt1.executeQuery(queryString1);
				if(rset1.next()) {
					V_emp_nm.add(rset1.getString(1)==null?"":rset1.getString(1));
				}else {
					V_emp_nm.add("");
				}
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

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

    public String getEmp_nmae() {
		return emp_nmae;
	}

	public void setEmp_nmae(String emp_nmae) {
		this.emp_nmae = emp_nmae;
	}
	//Following (9) Vector Getter Methods has been defined By Samik Shah On 9th June, 2011 ...
	public Vector getV_Group_cd() {return V_Group_cd;}
	public Vector getV_Group_nm() {return V_Group_nm;}
	public Vector getV_User_cd() {return V_User_cd;}
	public Vector getV_User_nm() {return V_User_nm;}
	public Vector getV_Module_cd() {return V_Module_cd;}
	public Vector getV_Module_nm() {return V_Module_nm;}
	public Vector getV_Form_cd() {return V_Form_cd;}
	public Vector getV_Form_nm() {return V_Form_nm;}
	public Vector getV_Access_rights() {return V_Access_rights;}

	public String getSelModule() {
		return selModule;
	}

	public void setSelModule(String selModule) {
		this.selModule = selModule;
	}

	public String getHead_tab() {
		return head_tab;
	}

	public void setHead_tab(String head_tab) {
		this.head_tab = head_tab;
	}

	public String getWrite_permission() {
		return write_permission;
	}

	public void setWrite_permission(String write_permission) {
		this.write_permission = write_permission;
	}

	public String getDelete_permission() {
		return delete_permission;
	}

	public void setDelete_permission(String delete_permission) {
		this.delete_permission = delete_permission;
	}

	public String getPrint_permission() {
		return print_permission;
	}

	public void setPrint_permission(String print_permission) {
		this.print_permission = print_permission;
	}

	public String getApprove_permission() {
		return approve_permission;
	}

	public void setApprove_permission(String approve_permission) {
		this.approve_permission = approve_permission;
	}

	public String getAudit_permission() {
		return audit_permission;
	}

	public void setAudit_permission(String audit_permission) {
		this.audit_permission = audit_permission;
	}

	public String getUpdate_permission() {
		return update_permission;
	}

	public void setUpdate_permission(String update_permission) {
		this.update_permission = update_permission;
	}

	public String getView_permission() {
		return view_permission;
	}

	public void setView_permission(String view_permission) {
		this.view_permission = view_permission;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public Vector getVmodPath() {
		return VmodPath;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Vector getGroup_cd() {
		return group_cd;
	}

	public Vector getGroup_nm() {
		return group_nm;
	}

	public Vector getEmp_cd() {
		return emp_cd;
	}

	public Vector getEmp_nme() {
		return emp_nme;
	}

	public Vector getEmp_cnt() {
		return emp_cnt;
	}
	public Vector getEmp_flag() {
		return emp_flag;
	}

	
}//End Of Class DataBean_Admin ...
