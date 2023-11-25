package com.seipl.hazira.dlng.master;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.UtilBean;

public class DataBean_Email_Setup {

	Connection conn; 
	Statement stmt;
	Statement stmt1;
	ResultSet rset;
	ResultSet rset1;
	String queryString="";
	String queryString1="";
	String emp_cd = "";
	public String callFlag="";
	String radFlg = "";
	String plant_cd = "";
	
		public void init()
		{
		    try
		    {
		    	//System.out.println("callFlag (1) = "+callFlag);
		    	
		    	Context initContext = new InitialContext();
		    	if(initContext == null)
		    	{
		    		throw new Exception("Boom - No Context");
		    	}
			  
		    	Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    	DataSource ds = (DataSource)envContext.lookup(RuntimeConf.security_database);
		    	if(ds != null) 
		    	{
		    		conn = ds.getConnection();       
		    		if(conn != null)  
		    		{
		    			stmt = conn.createStatement();
		    			stmt1 = conn.createStatement();
		    			
		    			addColumn();
		    			
		    			if(callFlag.equals("fetchUserDtl")) {
		    				
		    				if(radFlg.equalsIgnoreCase("sup")) {
			    				fetchUserDtl();
			    				fetchEmailDtl(emp_cd);
			    				
		    				}else if(radFlg.equalsIgnoreCase("cust")) {
		    					
		    					fetchCustDtl();
		    					if(!cust_cd.equalsIgnoreCase("")) {
		    						fetchCustPlantDtl(cust_cd);
//		    						System.out.println("plant_cd--------"+plant_cd);
//		    						if(!plant_cd.equalsIgnoreCase("")) {
		    							fetchSelPlantDtl(plant_cd);
//		    						}
		    					}
		    				}else if(radFlg.equalsIgnoreCase("trans")) {
		    					fetchTransDtl();
		    				}
		    			}
		    		}
		    	}
		    }
		    
		    catch(Exception e)
		    {
		    	//System.out.println("Exception:(DataBean_Entity)-(init()): "+e);
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
						e.printStackTrace();
						//System.out.println("rset is not close " + e);
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
						//System.out.println("rset is not close " + e);
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
						//System.out.println("stmt is not close " + e);
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
						//System.out.println("stmt1 is not close " + e);
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
						e.printStackTrace();
						//System.out.println("conn is not close " + e);
					}
				}
		    }
		}
		private void addColumn()throws SQLException,IOException {
			try {
				int count=0;
				String s="SELECT COUNT(COLUMN_NAME) FROM COLS WHERE UPPER(TABLE_NAME) LIKE 'DLNG_TRANSPORTER_CONTACT_MST' "+
						" AND UPPER(COLUMN_NAME) LIKE 'DEF_NOM_TO_FLAG'";
//				//System.out.println("s****"+s);
				rset=stmt.executeQuery(s);
				if(rset.next())
				{
					count=rset.getInt(1);
				}
				if(count==0)
				{
					s="ALTER TABLE DLNG_TRANSPORTER_CONTACT_MST ADD DEF_NOM_TO_FLAG CHAR(1 BYTE)";
					stmt.executeUpdate(s);
					conn.commit();
				}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		Vector trans_cd = new Vector();
		Vector trans_nm = new Vector();
		String strans_cd = "",addrs = "";
		private void fetchTransDtl()throws SQLException,IOException {
			try {
				
				String transSql = "select TRANS_CD,TRANS_NAME from DLNG_TRANS_MST order by TRANS_NAME ";
				rset = stmt.executeQuery(transSql);
				while (rset.next()) {
					
					trans_cd.add(rset.getString(1)==null?"":rset.getString(1));
					trans_nm.add(rset.getString(2)==null?"":rset.getString(2));
					
				}
				
				if(!strans_cd.equals("")) {
					String wrCond = "";
					if(!addrs.equals("")) {
						wrCond = " and ADDR_FLAG = '"+addrs+"' ";
					}else {
						wrCond = " and ADDR_FLAG like '%' ";
					}
					
					String transDtlSql = "select SEQ_NO,CONTACT_PERSON,EMAIL,DEF_NOM_TO_FLAG "
							+ " from DLNG_TRANSPORTER_CONTACT_MST "
							+ " where TRANS_CD = '"+strans_cd+"'"
							+ " "+wrCond+"  order by CONTACT_PERSON";
//					System.out.println("transDtlSql--"+transDtlSql);
					rset = stmt.executeQuery(transDtlSql);
					while (rset.next()) {
						
						Vseq_no.add(rset.getString(1)==null?"":rset.getString(1));
						Vcontact_person.add(rset.getString(2)==null?"":rset.getString(2));
						Vemail.add(rset.getString(3)==null?"":rset.getString(3));
						
						String def_nom_to_flag = rset.getString(4)==null?"":rset.getString(4);
						
						if(def_nom_to_flag.equalsIgnoreCase("Y")){
							
							def_inv_to_flag.add(def_nom_to_flag);
							def_inv_cc_flag.add("N");
							to_cnt++;
						}else {
							def_inv_to_flag.add("N");
							def_inv_cc_flag.add("Y");
						}
					}
				}
						
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		Vector Vseq_no = new Vector();
		Vector Vcontact_person = new Vector();
		Vector Vemail = new Vector();
		Vector def_inv_to_flag = new Vector();
		Vector def_inv_cc_flag = new Vector();
		int to_cnt = 0 ;
		
		Vector Vavail_seq_no = new Vector();
		Vector Vavail_cont_pers = new Vector();
		Vector Vavail_plant_nm = new Vector();
		
		private void fetchSelPlantDtl(String plant_cd)throws SQLException,IOException{
			try {
				
				
				String custDtlSql = "select distinct(seq_no),contact_person,email from FMS7_CUSTOMER_CONTACT_MST"
						+ " where customer_cd = '"+cust_cd+"' and addr_flag='"+plant_cd+"' and def_inv_flag = 'Y'  "
						+ " and active_flag = 'Y' ";
//				System.out.println("custDtlSql-------"+custDtlSql);
				rset = stmt.executeQuery(custDtlSql);
				while (rset.next()) {
					
					Vseq_no.add(rset.getString(1)==null?"":rset.getString(1));
					Vcontact_person.add(rset.getString(2)==null?"":rset.getString(2));
					Vemail.add(rset.getString(3)==null?"":rset.getString(3));
					
					String custToSql = "select DEF_INV_TO_FLAG from FMS7_CUSTOMER_CONTACT_MST"
							+ " where customer_cd = '"+cust_cd+"' "
							+ " and addr_flag='"+plant_cd+"' "
							+ " and def_inv_flag = 'Y' "
							+ " and def_inv_to_flag = 'Y' "
							+ " and seq_no = '"+rset.getString(1)+"' "
							+ " and active_flag = 'Y' ";
					
//					System.out.println("custToSql-------"+custToSql);
					rset1 = stmt1.executeQuery(custToSql);
					if(rset1.next()) {
						
						def_inv_to_flag.add(rset1.getString(1)==null?"":rset1.getString(1));
						def_inv_cc_flag.add("N");
						to_cnt++;
					}else {
						def_inv_to_flag.add("N");
						def_inv_cc_flag.add("Y");
					}
				}
				
				/*for display all the configured dtl*/
				if(plant_cd.equals("")) {
					plant_cd = "%";
				}
				String allDtl = "select CONTACT_PERSON,SEQ_NO,ADDR_FLAG from FMS7_CUSTOMER_CONTACT_MST"
						+ " where customer_cd = '"+cust_cd+"' "
						+ " and addr_flag like '"+plant_cd+"' "
						+ " and def_inv_flag = 'Y' "
						+ " and def_inv_to_flag = 'Y' "
						+ " and active_flag = 'Y' ";
//				System.out.println("allDtl-------"+allDtl);
				rset1 = stmt1.executeQuery(allDtl);
				while (rset1.next()) {
					
					Vavail_cont_pers.add(rset1.getString(1) == null?"":rset1.getString(1));
					Vavail_seq_no.add(rset1.getString(2) == null?"":rset1.getString(2));


					 queryString = "Select  A.PLANT_NAME  " +
							 " from FMS7_CUSTOMER_PLANT_DTL A " +
							 " WHERE A.CUSTOMER_CD='"+cust_cd+"' and A.FLAG='T'"
							+ " and plant_type='"+rset1.getString(3)+"' and " +
							 " A.EFF_DT = (SELECT MAX(B.EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B WHERE " +
							 " B.CUSTOMER_CD='"+cust_cd+"' and plant_type='"+rset1.getString(3)+"' and A.SEQ_NO=B.SEQ_NO and A.FLAG=B.FLAG)";
//					 System.out.println("queryString----"+queryString);
					 rset = stmt.executeQuery(queryString);
					 if (rset.next()) {
						 
						 Vavail_plant_nm.add(rset.getString(1)==null?"":rset.getString(1));
					 }else{
						 Vavail_plant_nm.add("-");
					 }
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		Vector Vplant_seq_no = new Vector();
		Vector Vplant_type = new Vector();
		Vector Vplant_name = new Vector();
		
		private void fetchCustPlantDtl(String cust_cd)throws SQLException,IOException {
			try {
			
				Vplant_name.add("Registered");
				Vplant_name.add("Correspondence");
				Vplant_name.add("Billing");
				
				Vplant_type.add("R");
				Vplant_type.add("C");
				Vplant_type.add("B");
				
			 queryString = "Select DISTINCT(A.SEQ_NO), A.PLANT_TYPE, A.PLANT_NAME  " +
					 " from FMS7_CUSTOMER_PLANT_DTL A " +
					 " WHERE A.CUSTOMER_CD='"+cust_cd+"' and A.FLAG='T' and  " +
					 " A.EFF_DT = (SELECT MAX(B.EFF_DT) FROM FMS7_CUSTOMER_PLANT_DTL B WHERE " +
					 " B.CUSTOMER_CD='"+cust_cd+"' and A.SEQ_NO=B.SEQ_NO and A.FLAG=B.FLAG)" +
					 " ORDER BY A.SEQ_NO";
//			 System.out.println("queryString----"+queryString);
			 rset = stmt.executeQuery(queryString);
			 while (rset.next()) {
				 
//				 Vplant_seq_no.add(rset.getString(1)==null?"":rset.getString(1));
				 Vplant_type.add(rset.getString(2)==null?"":rset.getString(2));
				 Vplant_name.add(rset.getString(3)==null?"":rset.getString(3));
			 }
			 
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		Vector Vcust_cd = new Vector();
		Vector Vcust_nm = new Vector();
		String cust_cd = "";
		private void fetchCustDtl()throws SQLException,IOException  {
			try {
				
				String custSql = "select distinct(A.CUSTOMER_CD),B.CUSTOMER_NAME "
						+ " from FMS7_CUSTOMER_CONTACT_MST A, FMS7_CUSTOMER_MST B"
						+ " where A.DEF_INV_FLAG = 'Y' "
						+ " and A.active_flag = 'Y' "
						+ "and  A.CUSTOMER_CD = B.CUSTOMER_CD order by CUSTOMER_NAME ";
//				System.out.println("custSql-----"+custSql);
				rset = stmt.executeQuery(custSql);
				while (rset.next()) {
					
					Vcust_cd.add(rset.getString(1)==null?"":rset.getString(1));
					Vcust_nm.add(rset.getString(2)==null?"":rset.getString(2));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		String form402_bcc = "",form402_cc = "",form402_to = "",d_inv_bcc = "",d_inv_cc = "",eff_dt = ""
				,o_inv_bcc = "",o_inv_cc = "",t_inv_bcc = "",t_inv_cc = "",d_inv_to="",t_inv_to="";
		
		String avail_flg = "N";
		
		Vector Vform402_bcc = new Vector();
		Vector Vform402_cc =  new Vector();
		Vector Vform402_to =  new Vector();
		Vector Vd_inv_bcc =  new Vector();
		Vector Vd_inv_cc =  new Vector();
		Vector Vo_inv_bcc =  new Vector();
		Vector Vo_inv_cc =  new Vector();
		Vector Vt_inv_bcc =  new Vector();
		Vector Vt_inv_cc =  new Vector();
		Vector VContact_pers =  new Vector();
		Vector Vd_inv_to = new Vector();
		Vector Vt_inv_to = new Vector();
		Vector Vactive_flg = new Vector();
		
		private void fetchEmailDtl(String emp_cd)throws SQLException  {
			try {
				
				if(!emp_cd.equalsIgnoreCase("")) {
					
					String emailDtl = "select form402_bcc,form402_cc,form402_to,d_inv_bcc,d_inv_cc"
							+ " ,o_inv_bcc,o_inv_cc,t_inv_bcc,t_inv_cc,d_inv_to,t_inv_to "
							+ " from DLNG_MAIL_SETUP_MST where"
							+ " seq_no = '"+emp_cd+"' and COMMODITY_TYPE = 'DLNG' ";
					System.out.println("emailDtl------"+emailDtl);
					rset = stmt.executeQuery(emailDtl);
					if(rset.next()) {
						avail_flg = "Y";
						
						form402_bcc = rset.getString(1)==null?"":rset.getString(1);
						form402_cc = rset.getString(2)==null?"":rset.getString(2);
						form402_to = rset.getString(3)==null?"":rset.getString(3);
						d_inv_bcc = rset.getString(4)==null?"":rset.getString(4);
						d_inv_cc = rset.getString(5)==null?"":rset.getString(5);
						o_inv_bcc = rset.getString(6)==null?"":rset.getString(6);
						o_inv_cc = rset.getString(7)==null?"":rset.getString(7);
						t_inv_bcc = rset.getString(8)==null?"":rset.getString(8);
						t_inv_cc = rset.getString(9)==null?"":rset.getString(9);
//						d_inv_to = rset.getString(10)==null?"":rset.getString(10);
						t_inv_to = rset.getString(11)==null?"":rset.getString(11);
					}
				}
				
				String seq_no = "%";
				if(!emp_cd.equalsIgnoreCase("")) {
					seq_no = emp_cd;
				}
				
				String AllConfDtl = "select distinct(A.seq_no),form402_bcc,form402_cc,form402_to,d_inv_bcc,d_inv_cc,"
						+ " o_inv_bcc,o_inv_cc,"
						+ " t_inv_bcc,t_inv_cc,A.d_inv_to,A.t_inv_to,B.contact_person "
						+ " from DLNG_MAIL_SETUP_MST A,FMS7_SUPPLIER_CONTACT_MST B where"
						+ " A.seq_no like '"+seq_no+"' and COMMODITY_TYPE = 'DLNG' "
						+ " AND A.seq_no = B.seq_no";
//				System.out.println("AllConfDtl------"+AllConfDtl);
				rset1 = stmt1.executeQuery(AllConfDtl);
				while(rset1.next()) {
					
					String chkAvail = "select SEQ_NO from FMS7_SUPPLIER_CONTACT_MST A"
							+ " where seq_no = '"+rset1.getString(1)+"'"
							+ " AND A.ACTIVE_FLAG = 'Y' and A.DEF_INV_FLAG = 'Y'"
							+ " and A.eff_dt = (select max(B.eff_dt) from FMS7_SUPPLIER_CONTACT_MST B "
								+ "	where seq_no = '"+rset1.getString(1)+"' "
								+ " AND B.ACTIVE_FLAG = 'Y' and B.DEF_INV_FLAG = 'Y')";
					rset = stmt.executeQuery(chkAvail);
					if(rset.next()) {
						Vactive_flg.add("Y");
					}else {
						Vactive_flg.add("N");
					}
					
					Vform402_bcc.add(rset1.getString(2)==null?"":rset1.getString(2));
					Vform402_cc.add(rset1.getString(3)==null?"":rset1.getString(3));
					Vform402_to.add(rset1.getString(4)==null?"":rset1.getString(4));
					Vd_inv_bcc.add(rset1.getString(5)==null?"":rset1.getString(5));
					Vd_inv_cc.add(rset1.getString(6)==null?"":rset1.getString(6));
					Vo_inv_bcc.add(rset1.getString(7)==null?"":rset1.getString(7));
					Vo_inv_cc.add(rset1.getString(8)==null?"":rset1.getString(8));
					Vt_inv_bcc.add(rset1.getString(9)==null?"":rset1.getString(9));
					Vt_inv_cc.add(rset1.getString(10)==null?"":rset1.getString(10));
//					Vd_inv_to.add(rset1.getString(11)==null?"":rset1.getString(11));
					Vt_inv_to.add(rset1.getString(12)==null?"":rset1.getString(12));
					VContact_pers.add(rset1.getString(13)==null?"":rset1.getString(13));
					
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		Vector Vemp_nm = new Vector();
		Vector Vemp_cd = new Vector();
		String email_id = "";
		private void fetchUserDtl()throws Exception {
			try {
				
				
				String userDtl = "select distinct(SEQ_NO),CONTACT_PERSON from FMS7_SUPPLIER_CONTACT_MST A"
						+ " where A.ACTIVE_FLAG = 'Y' and A.DEF_INV_FLAG = 'Y'"
						+ " and A.eff_dt = (select max(B.eff_dt) from FMS7_SUPPLIER_CONTACT_MST B "
						+ "	where B.ACTIVE_FLAG = 'Y' and B.DEF_INV_FLAG = 'Y' and B.SEQ_NO = A.SEQ_NO)"
						+ " Order By CONTACT_PERSON ";
//				System.out.println("userDtl---"+userDtl);
				rset = stmt.executeQuery(userDtl);
				while (rset.next()) {
					Vemp_cd.add(rset.getString(1)==null?"":rset.getString(1));
					Vemp_nm.add(rset.getString(2)==null?"":rset.getString(2));
				}
				
				if(!emp_cd.equalsIgnoreCase("")) {
					String selUserDtl = "select email from FMS7_SUPPLIER_CONTACT_MST"
							+ " where SEQ_NO = '"+emp_cd+"' AND  ACTIVE_FLAG = 'Y' and DEF_INV_FLAG = 'Y'  ";
					rset = stmt.executeQuery(selUserDtl);
					if(rset.next()) {
						email_id = rset.getString(1)==null?"":rset.getString(1);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void setCallFlag(String callFlag) {
			this.callFlag = callFlag;
		}


		public Vector getVemp_nm() {
			return Vemp_nm;
		}


		public Vector getVemp_cd() {
			return Vemp_cd;
		}


		public String getEmp_cd() {
			return emp_cd;
		}


		public void setEmp_cd(String emp_cd) {
			this.emp_cd = emp_cd;
		}

		public String getD_inv_bcc() {
			return d_inv_bcc;
		}


		public String getD_inv_cc() {
			return d_inv_cc;
		}


		public String getEff_dt() {
			return eff_dt;
		}
		public String getO_inv_bcc() {
			return o_inv_bcc;
		}


		public String getO_inv_cc() {
			return o_inv_cc;
		}
		public String getT_inv_bcc() {
			return t_inv_bcc;
		}


		public String getT_inv_cc() {
			return t_inv_cc;
		}

		public String getAvail_flg() {
			return avail_flg;
		}


		public String getRadFlg() {
			return radFlg;
		}


		public void setRadFlg(String radFlg) {
			this.radFlg = radFlg;
		}


		public Vector getVcust_cd() {
			return Vcust_cd;
		}


		public Vector getVcust_nm() {
			return Vcust_nm;
		}


		public String getCust_cd() {
			return cust_cd;
		}


		public void setCust_cd(String cust_cd) {
			this.cust_cd = cust_cd;
		}


		public Vector getVplant_seq_no() {
			return Vplant_seq_no;
		}


		public Vector getVplant_type() {
			return Vplant_type;
		}


		public Vector getVplant_name() {
			return Vplant_name;
		}


		public String getPlant_cd() {
			return plant_cd;
		}


		public void setPlant_cd(String plant_cd) {
			this.plant_cd = plant_cd;
		}


		public Vector getVseq_no() {
			return Vseq_no;
		}


		public Vector getVcontact_person() {
			return Vcontact_person;
		}


		public Vector getVemail() {
			return Vemail;
		}


		public Vector getDef_inv_to_flag() {
			return def_inv_to_flag;
		}


		public Vector getDef_inv_cc_flag() {
			return def_inv_cc_flag;
		}


		public int getTo_cnt() {
			return to_cnt;
		}


		public Vector getVavail_seq_no() {
			return Vavail_seq_no;
		}


		public Vector getVavail_cont_pers() {
			return Vavail_cont_pers;
		}


		public Vector getVavail_plant_nm() {
			return Vavail_plant_nm;
		}

		public String getEmail_id() {
			return email_id;
		}

		public void setEmail_id(String email_id) {
			this.email_id = email_id;
		}

		public Vector getVd_inv_bcc() {
			return Vd_inv_bcc;
		}

		public Vector getVd_inv_cc() {
			return Vd_inv_cc;
		}

		public Vector getVo_inv_bcc() {
			return Vo_inv_bcc;
		}

		public Vector getVo_inv_cc() {
			return Vo_inv_cc;
		}

		public Vector getVt_inv_bcc() {
			return Vt_inv_bcc;
		}

		public Vector getVt_inv_cc() {
			return Vt_inv_cc;
		}

		public Vector getVContact_pers() {
			return VContact_pers;
		}

		public String getForm402_bcc() {
			return form402_bcc;
		}

		public String getForm402_cc() {
			return form402_cc;
		}

		public String getForm402_to() {
			return form402_to;
		}

		public Vector getVform402_bcc() {
			return Vform402_bcc;
		}

		public Vector getVform402_cc() {
			return Vform402_cc;
		}

		public Vector getVform402_to() {
			return Vform402_to;
		}

		public String getD_inv_to() {
			return d_inv_to;
		}

		public String getT_inv_to() {
			return t_inv_to;
		}

		public Vector getVd_inv_to() {
			return Vd_inv_to;
		}

		public Vector getVt_inv_to() {
			return Vt_inv_to;
		}

		public Vector getTrans_cd() {
			return trans_cd;
		}

		public Vector getTrans_nm() {
			return trans_nm;
		}

		public String getStrans_cd() {
			return strans_cd;
		}

		public String getAddrs() {
			return addrs;
		}

		public void setStrans_cd(String strans_cd) {
			this.strans_cd = strans_cd;
		}

		public void setAddrs(String addrs) {
			this.addrs = addrs;
		}
		public Vector getVactive_flg() {
			return Vactive_flg;
		}
}
