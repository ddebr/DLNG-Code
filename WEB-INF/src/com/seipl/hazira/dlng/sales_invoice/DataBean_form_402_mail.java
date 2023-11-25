package com.seipl.hazira.dlng.sales_invoice;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.seipl.hazira.dlng.util.RuntimeConf;

public class DataBean_form_402_mail {
	Connection conn; 
	 Statement stmt;
	 Statement stmt1;
	 Statement stmt2;
	 ResultSet rset;
	 ResultSet rset1;
	 ResultSet rset2;
	 String queryString="";
	 String callFlag = "";
	 public HttpServletRequest request=null;
	 public Vector Main_File_List = new Vector();
	 public String browseTextField = "";
	 public static String SRC = "";
	 public static String DEST = "";
	 public Vector signed_files_nm=new Vector();
	 public Vector temp_signed_files_nm=new Vector();
	 public Vector signed_Main_File_List = new Vector();
	 public String signed_browseTextField = "";
	 Vector pdf=new Vector();
	 Vector signed_by=new Vector(); 
	 Vector pdf_sign_dt=new Vector();
	 String from_dt="";
	 String to_dt="";
	 String from_mail="";
	 String pdfpath="",pdfsignedpath="";
	 String xls_path="/Form402_Xls";
	 String seller_pdf__path="/pdf_reports/daily_nomination/pdf_files";
	 String inv_path="";
	 String inv_flag="";
	 String contact_person_cd="",customer_cd="",invoice_dt="",email_to="",email_to_otherinv="";
	 String sysdate="";
	 String temp="";String email_cc="",temp1="",temp_due_dt=""; 
	 String cont_type="";
	 String cust_nm="",sub_inv_dt="",sub_dr_cr_dt="";
	 Vector email_for_email_body=new Vector();
	 String customer_plant_seq_no=""; 
	 Vector cc_email=new Vector(); 
	 String due_dt="";
	 String supplier_cd="1";
	 String supplier_nm="";
	 String supplier_pan_no="";
	 String supplier_abbr="";
	 String supplier_addr="";
	 String supplier_city="";
	 String supplier_phone_no="";
	 String supplier_pin_code="";
	 String supplier_cst_no="";
	 String supplier_cst_dt="";
	 String supplier_gst_no="";
	 String supplier_gst_dt="";
	 String dr_cr_dt="";
	 String FINANCE_grp="FINANCE";
	 String finance_emailids="";
	 String invNo ="";
	 String truck_no ="";
			 
	 public void init() {
		 try
		    {
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
		    			stmt2 = conn.createStatement();
		    			
		    			if(callFlag.equalsIgnoreCase("fetch_excel")){
		    				sendAll1(); 
		    				fetchExcelfile();
		    			}
		    			
		    			if(callFlag.equalsIgnoreCase("fetch_seller_pdf")){
		    				sendAll1(); 
		    				fetchSellerPdffile();
		    			}
		    			
		    			conn.close();
		    			conn = null;
		    		}
		    	}
		    }
		    catch(Exception e)
		    {
		    	System.out.println("Exception In :- (init()): "+e.getMessage());
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
		
	 }
	 String fileName="";
	 String gas_date="",cust_abbr="",plant_nm="",sn_ref_no="",fgsa_no="";
	 String to_cntct = "";
	 private void fetchSellerPdffile()throws SQLException,IOException{
		try {
			fileName="";
			if(cont_type.trim().equalsIgnoreCase("S"))
			{
				fileName = "CUSTOMER_SELLER_NOM-"+gas_date.trim().substring(0,2)+gas_date.trim().substring(3,5)+gas_date.trim().substring(6)+"-"+cust_abbr+"-"+plant_nm+"-FG"+fgsa_no+"-SN"+sn_ref_no+".pdf";
			}
			else if(cont_type.trim().equalsIgnoreCase("L"))
			{
				fileName = "CUSTOMER_SELLER_NOM-"+gas_date.trim().substring(0,2)+gas_date.trim().substring(3,5)+gas_date.trim().substring(6)+"-"+cust_abbr+"-"+plant_nm+"-TN"+fgsa_no+"-LOA"+sn_ref_no+".pdf";
			}
			
			String f1="";
			String f2="";
			File pdf_file;
			String path=request.getRealPath(seller_pdf__path);
			f1=fileName;
			f1=path+"/"+f1;
			f2=seller_pdf__path+"/"+fileName;
			pdf_file=new File(f1);
//			System.out.println("pdf_file.exists()****"+pdf_file.exists());
			
			if(pdf_file.exists())
			{
				xls_file = fileName;
				path=path+"//"+pdf_file;
	            String context_nm = request.getContextPath();
				String server_nm = request.getServerName();
				String server_port = ""+request.getServerPort();
				String url_start = "http://"+server_nm+":"+server_port+context_nm;
				pdfpath = path;
				pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
				pdfpath = url_start+"/"+f2;
//				System.out.println("pdfpath****"+pdfpath);
				pdfsignedpath=pdfpath;
			}
			else{
				pdfsignedpath="";
				xls_file ="Attachment Not Available!!";
			}
			
			
				
				String temp_plant_no = "P"+customer_plant_seq_no.trim();
				/*
				 * queryString = "SELECT email FROM FMS7_CUSTOMER_CONTACT_MST A " +
				 * "WHERE A.customer_cd='"+customer_cd+"' AND " +
				 * "A.active_flag='Y' and DEF_NOM_FLAG='Y' AND (A.addr_flag='B' OR A.addr_flag='R' OR "
				 * + "A.addr_flag='C' OR A.addr_flag='"+temp_plant_no+"') AND " +
				 * "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
				 * "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND " +
				 * "B.eff_dt<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
				 */
				queryString = "SELECT email FROM FMS7_CUSTOMER_CONTACT_MST A " +
						  " WHERE A.CUSTOMER_CD='"+customer_cd+"'  AND A.ACTIVE_FLAG='Y' AND DEF_NOM_FLAG='Y' AND " +
						  " A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_CUSTOMER_CONTACT_MST B WHERE B.CUSTOMER_CD=A.CUSTOMER_CD AND " +
						  " A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))"
						  + " AND email is not null";
				System.out.println("Customer Contact Person Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					email_to+= rset.getString(1)==null?"":rset.getString(1)+",";
				}
				if(email_to.contains(",") && email_to.length() > 0) {
					email_to = email_to.substring(0,email_to.length()-1);
				}
				
				email_cc="GX-HAZIRA-DLNGOPS@shell.com"; //Hiren_20200202 Fix as discussed with Mahesh Sir.
				
			
			queryString="SELECT SUPPLIER_NAME,GST_TIN_NO,TO_CHAR(GST_TIN_DT,'DD/MM/YYYY'),CST_TIN_NO,TO_CHAR(CST_TIN_DT,'DD/MM/YYYY'),PAN_NO,SUPPLIER_ABBR FROM FMS7_SUPPLIER_MST WHERE SUPPLIER_CD='"+supplier_cd+"' ";
			rset=stmt.executeQuery(queryString);
			if(rset.next()){
				supplier_nm=rset.getString(1)==null?"":rset.getString(1);
				supplier_gst_no=rset.getString(2)==null?"":rset.getString(2);
				supplier_gst_dt=rset.getString(3)==null?"":rset.getString(3);
				supplier_cst_no=rset.getString(4)==null?"":rset.getString(4);
				supplier_cst_dt=rset.getString(5)==null?"":rset.getString(5);
				supplier_pan_no=rset.getString(6)==null?"":rset.getString(6);
				supplier_abbr=rset.getString(7)==null?"":rset.getString(7);
			}
			queryString="SELECT ADDR,CITY,PIN,PHONE FROM FMS7_SUPPLIER_ADDRESS_MST  A WHERE SUPPLIER_CD='"+supplier_cd+"'"
					+ " AND ADDRESS_TYPE='B' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_SUPPLIER_ADDRESS_MST  B "
					+ " WHERE SUPPLIER_CD='"+supplier_cd+"'  AND ADDRESS_TYPE='B') ";
			
			rset=stmt.executeQuery(queryString);
			if(rset.next()){
				supplier_addr=rset.getString(1)==null?"":rset.getString(1);
				supplier_city=rset.getString(2)==null?"":rset.getString(2);
				supplier_pin_code=rset.getString(3)==null?"":rset.getString(3);
				supplier_phone_no=rset.getString(4)==null?"":rset.getString(4);
			}
			
			queryString="SELECT CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A WHERE CUSTOMER_CD='"+customer_cd+"' AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM "
					+ "FMS7_CUSTOMER_MST B WHERE A.CUSTOMER_CD=B.CUSTOMER_CD AND " +
					  "B.EFF_DT<=TO_DATE('"+gas_date+"','DD/MM/YYYY'))";
			rset=stmt.executeQuery(queryString);
			//System.out.println("queryString--"+queryString);
			if(rset.next()){
				cust_nm=rset.getString(1)==null?"":rset.getString(1);
			}
			/*queryString="SELECT email FROM FMS7_SUPPLIER_CONTACT_MST A WHERE (upper(CONTACT_DESIG) not in ('"+FINANCE_grp.toUpperCase()+"') OR CONTACT_DESIG is null) AND A.inv_flag='Y' and active_flag='Y' AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM "
						+ "FMS7_SUPPLIER_CONTACT_MST B " +
						  "WHERE A.supplier_cd=B.supplier_cd AND A.seq_no=B.seq_no AND " +
						  "B.eff_dt<=TO_DATE('"+sysdate+"','DD/MM/YYYY'))";
			System.out.println("STEP-1A.4:FMS7_CUSTOMER_CONTACT_MST: for BCCC part "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				temp+= rset.getString(1)==null?"":rset.getString(1)+",";
				//email_to_finance.add(rset.getString(1)==null?"":rset.getString(1));
			}
			if(!temp.equals("")){
				temp=temp.substring(0,temp.length()-1);
			}
			
			queryString="SELECT email FROM FMS7_SUPPLIER_CONTACT_MST A WHERE"
					+ " upper(CONTACT_DESIG) like '%"+FINANCE_grp.toUpperCase()+"%' AND A.inv_flag='Y' and active_flag='Y' AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM "
					+ "FMS7_SUPPLIER_CONTACT_MST B " +
					  "WHERE A.supplier_cd=B.supplier_cd AND A.seq_no=B.seq_no AND " +
					  "B.eff_dt<=TO_DATE('"+sysdate+"','DD/MM/YYYY'))";
			System.out.println("STEP-1A.4:FMS7_CUSTOMER_CONTACT_MST: "+queryString);
			rset = stmt.executeQuery(queryString);
			while(rset.next())
			{
				finance_emailids+= rset.getString(1)==null?"":rset.getString(1)+",";
				//email_to_finance.add(rset.getString(1)==null?"":rset.getString(1));
			}
			if(!finance_emailids.equals("")){
				finance_emailids=finance_emailids.substring(0,finance_emailids.length()-1);
			}
			System.out.println("---finance_emailids--"+finance_emailids);
			
			if(cont_type.equalsIgnoreCase("S") || cont_type.equalsIgnoreCase("L")){
				queryString="SELECT CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A WHERE CUSTOMER_CD='"+customer_cd+"' AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM "
							+ "FMS7_CUSTOMER_MST B WHERE A.CUSTOMER_CD=B.CUSTOMER_CD AND " +
							  "B.EFF_DT<=TO_DATE('"+invoice_dt+"','DD/MM/YYYY'))";
				rset=stmt.executeQuery(queryString);
				//System.out.println("queryString--"+queryString);
				if(rset.next()){
					cust_nm=rset.getString(1)==null?"":rset.getString(1);
				}
			}
			
			queryString="SELECT TO_CHAR(TO_DATE('"+invoice_dt+"','DD/MM/YYYY'),'DD-MM-YYYY') FROM DUAL";
			rset=stmt.executeQuery(queryString);
			if(rset.next()){
				sub_inv_dt=rset.getString(1)==null?"":rset.getString(1);
			}
			
			//queryString="SELECT CONTACT_PERSON,EMAIL FROM FMS7_SUPPLIER_CONTACT_MST A WHERE CONTACT_DESIG IN ('Marketing and Commercial Support','Commercial Operation Coordinator') "
			queryString="SELECT CONTACT_PERSON,EMAIL,CONTACT_DESIG FROM FMS7_SUPPLIER_CONTACT_MST A WHERE CONTACT_DESIG IN ('Commercial Operations Advisor','Commercial Operations Manager') "
						+ "AND ACTIVE_FLAG='Y' AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_SUPPLIER_CONTACT_MST B " +
						  "WHERE A.SUPPLIER_CD=B.SUPPLIER_CD AND A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+sysdate+"','DD/MM/YYYY'))";
			rset=stmt.executeQuery(queryString);
//			System.out.println("queryString--in this-"+queryString);
			while(rset.next()){
				String temp=rset.getString(1)==null?"":rset.getString(1);
				String temp_email=rset.getString(2)==null?"":rset.getString(2);
				String desig=rset.getString(3)==null?"":rset.getString(3);
				//email_for_email_body.add(temp+" at "+temp_email+" ("+desig+")"); //RG20200113 commented for removing designation
				email_for_email_body.add(temp+" at "+temp_email);
			}*/
//			System.out.println("fileName****"+fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private synchronized void sendAll1()throws Exception 
		{
			try
				{
					String strline = "";
				    
				    File fsetup=new File(request.getRealPath("/sales_invoice/Setup.txt"));
					String mail_list_path=fsetup.getAbsolutePath();
					FileInputStream f1 = new FileInputStream(mail_list_path);
					DataInputStream in = new DataInputStream(f1);
					BufferedReader br = new BufferedReader(new InputStreamReader(in));
					
					while((strline = br.readLine())!=null)
					{
						if(strline.startsWith("from"))
						{
							String  tmp[]=strline.split("from:");
							from_mail = tmp[1].toString();
						}
					}
				}	
		catch (Exception e) 
		{
			e.printStackTrace();
		}
			
	}
	 
	 String xls_file = "",bcc_mail="";
	private void fetchExcelfile()throws SQLException,IOException {
		try {
			String tem=invNo.replace("/", "_");
			String fileName= tem+""+truck_no+".xls";
//			System.out.println("fileName****"+fileName); 
			
			 queryString="SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM DUAL";
				rset=stmt.executeQuery(queryString);
				if(rset.next()){
					sysdate=rset.getString(1);
				}
				String f1="";
				String f2="";
				File pdf_file;
				String path=request.getRealPath(xls_path);
				f1=fileName;
				f1=path+"/"+f1;
				f2=xls_path+"/"+fileName;
				pdf_file=new File(f1);
//				System.out.println("pdf_file.exists()****"+pdf_file.exists());
				
				if(pdf_file.exists())
				{
					xls_file = fileName;
					path=path+"//"+pdf_file;
		            String context_nm = request.getContextPath();
					String server_nm = request.getServerName();
					String server_port = ""+request.getServerPort();
					String url_start = "http://"+server_nm+":"+server_port+context_nm;
					pdfpath = path;
					pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
					pdfpath = url_start+"/"+f2;
					System.out.println("pdfpath****"+pdfpath);
					pdfsignedpath=pdfpath;
				}
				else{
					pdfsignedpath="";
					xls_file ="Attachment Not Available!!";
				}
				
				/*Hiren_20210608 from new Master */
				String supEmail = "select A.seq_no,form402_to,form402_cc,form402_bcc,B.email"
						+ " from DLNG_MAIL_SETUP_MST A,FMS7_SUPPLIER_CONTACT_MST B"
						+ " where B.DEF_INV_FLAG = 'Y' and B.ACTIVE_FLAG = 'Y' and COMMODITY_TYPE = 'DLNG'"
						+ " and A.seq_no = B.seq_no ";	
				System.out.println("supEmail----"+supEmail);
				rset = stmt.executeQuery(supEmail);
				while(rset.next())
				{
					if(rset.getString(2).equalsIgnoreCase("Y")) {
						email_to+=rset.getString(5)+",";
					}
					if(rset.getString(3).equalsIgnoreCase("Y")) {
						email_cc+=rset.getString(5)+",";
					}
					if(rset.getString(4).equalsIgnoreCase("Y")) {
						bcc_mail+=rset.getString(5)+",";
					}
				}
				if(!email_to.equals("")){
					email_to=email_to.substring(0,email_to.length()-1);
				}
				
				if(!email_cc.equals("")){
					email_cc=email_cc.substring(0,email_cc.length()-1);
				}
				
				if(!bcc_mail.equals("")){
					bcc_mail=bcc_mail.substring(0,bcc_mail.length()-1);
				}
				
				if(!inv_flag.equalsIgnoreCase("OTHER_")){
					
					
					if(!inv_flag.equalsIgnoreCase("CREDIT_")){
						queryString="SELECT TO_char(TO_date('"+due_dt+"','DD/MM/YYYY'),'DD-MM-YYYY') FROM DUAL";
						rset=stmt.executeQuery(queryString);
						//System.out.println("queryString--"+queryString);
						if(rset.next()){
							temp_due_dt=rset.getString(1)==null?"":rset.getString(1);
						}
					}
				}
				
				queryString="SELECT SUPPLIER_NAME,GST_TIN_NO,TO_CHAR(GST_TIN_DT,'DD/MM/YYYY'),CST_TIN_NO,TO_CHAR(CST_TIN_DT,'DD/MM/YYYY'),PAN_NO,SUPPLIER_ABBR FROM FMS7_SUPPLIER_MST WHERE SUPPLIER_CD='"+supplier_cd+"' ";
				rset=stmt.executeQuery(queryString);
				if(rset.next()){
					supplier_nm=rset.getString(1)==null?"":rset.getString(1);
					supplier_gst_no=rset.getString(2)==null?"":rset.getString(2);
					supplier_gst_dt=rset.getString(3)==null?"":rset.getString(3);
					supplier_cst_no=rset.getString(4)==null?"":rset.getString(4);
					supplier_cst_dt=rset.getString(5)==null?"":rset.getString(5);
					supplier_pan_no=rset.getString(6)==null?"":rset.getString(6);
					supplier_abbr=rset.getString(7)==null?"":rset.getString(7);
				}
				queryString="SELECT ADDR,CITY,PIN,PHONE FROM FMS7_SUPPLIER_ADDRESS_MST  A WHERE SUPPLIER_CD='"+supplier_cd+"'"
						+ " AND ADDRESS_TYPE='B' AND EFF_DT = (SELECT MAX(EFF_DT) FROM FMS7_SUPPLIER_ADDRESS_MST  B "
						+ " WHERE SUPPLIER_CD='"+supplier_cd+"'  AND ADDRESS_TYPE='B') ";
				
				rset=stmt.executeQuery(queryString);
				if(rset.next()){
					supplier_addr=rset.getString(1)==null?"":rset.getString(1);
					supplier_city=rset.getString(2)==null?"":rset.getString(2);
					supplier_pin_code=rset.getString(3)==null?"":rset.getString(3);
					supplier_phone_no=rset.getString(4)==null?"":rset.getString(4);
				}
				
				
				if(cont_type.equalsIgnoreCase("S") || cont_type.equalsIgnoreCase("L")){
					queryString="SELECT CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A WHERE CUSTOMER_CD='"+customer_cd+"' AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM "
								+ "FMS7_CUSTOMER_MST B WHERE A.CUSTOMER_CD=B.CUSTOMER_CD AND " +
								  "B.EFF_DT<=TO_DATE('"+invoice_dt+"','DD/MM/YYYY'))";
					rset=stmt.executeQuery(queryString);
					//System.out.println("queryString--"+queryString);
					if(rset.next()){
						cust_nm=rset.getString(1)==null?"":rset.getString(1);
					}
				}
				
				queryString="SELECT TO_CHAR(TO_DATE('"+invoice_dt+"','DD/MM/YYYY'),'DD-MM-YYYY') FROM DUAL";
				rset=stmt.executeQuery(queryString);
				if(rset.next()){
					sub_inv_dt=rset.getString(1)==null?"":rset.getString(1);
				}
				
				//queryString="SELECT CONTACT_PERSON,EMAIL FROM FMS7_SUPPLIER_CONTACT_MST A WHERE CONTACT_DESIG IN ('Marketing and Commercial Support','Commercial Operation Coordinator') "
				queryString="SELECT CONTACT_PERSON,EMAIL,CONTACT_DESIG FROM FMS7_SUPPLIER_CONTACT_MST A WHERE CONTACT_DESIG IN ('Commercial Operations Advisor','Commercial Operations Manager') "
							+ "AND ACTIVE_FLAG='Y' AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_SUPPLIER_CONTACT_MST B " +
							  "WHERE A.SUPPLIER_CD=B.SUPPLIER_CD AND A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+sysdate+"','DD/MM/YYYY'))";
				rset=stmt.executeQuery(queryString);
//				System.out.println("queryString--in this-"+queryString);
				while(rset.next()){
					String temp=rset.getString(1)==null?"":rset.getString(1);
					String temp_email=rset.getString(2)==null?"":rset.getString(2);
					String desig=rset.getString(3)==null?"":rset.getString(3);
					//email_for_email_body.add(temp+" at "+temp_email+" ("+desig+")"); //RG20200113 commented for removing designation
					email_for_email_body.add(temp+" at "+temp_email);
				}
				//System.out.println("---"+email_for_email_body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Vector getMain_File_List() {
		return Main_File_List;
	}

	public String getBrowseTextField() {
		return browseTextField;
	}

	public Vector getSigned_Main_File_List() {
		return signed_Main_File_List;
	}

	public String getSigned_browseTextField() {
		return signed_browseTextField;
	}
	public Vector getSigned_files_nm() {
		return signed_files_nm;
	}
	public void setSigned_files_nm(Vector signed_files_nm) {
		this.signed_files_nm = signed_files_nm;
	}
	public Vector getPdf() {
		return pdf;
	}
	public void setPdf(Vector pdf) {
		this.pdf = pdf;
	}
	public Vector getPdf_sign_dt() {
		return pdf_sign_dt;
	}
	public void setPdf_sign_dt(Vector pdf_sign_dt) {
		this.pdf_sign_dt = pdf_sign_dt;
	}
	public String getFrom_dt() {
		return from_dt;
	}
	public void setFrom_dt(String from_dt) {
		this.from_dt = from_dt;
	}
	public String getTo_dt() {
		return to_dt;
	}
	public void setTo_dt(String to_dt) {
		this.to_dt = to_dt;
	}
	public Vector getTemp_signed_files_nm() {
		return temp_signed_files_nm;
	}
	public void setTemp_signed_files_nm(Vector temp_signed_files_nm) {
		this.temp_signed_files_nm = temp_signed_files_nm;
	}
	public Vector getSigned_by() {
		return signed_by;
	}
	public void setSigned_by(Vector signed_by) {
		this.signed_by = signed_by;
	}
	public String getCallFlag() {
		return callFlag;
	}
	public void setCallFlag(String callFlag) {
		this.callFlag = callFlag;
	}
	public String getFrom_mail() {
		return from_mail;
	}
	public void setFrom_mail(String from_mail) {
		this.from_mail = from_mail;
	}
	public String getContact_person_cd() {
		return contact_person_cd;
	}
	public void setContact_person_cd(String contact_person_cd) {
		this.contact_person_cd = contact_person_cd;
	}
	public String getCustomer_cd() {
		return customer_cd;
	}
	public void setCustomer_cd(String customer_cd) {
		this.customer_cd = customer_cd;
	}
	public String getInvoice_dt() {
		return invoice_dt;
	}
	public void setInvoice_dt(String invoice_dt) {
		this.invoice_dt = invoice_dt;
	}
	public String getEmail_to() {
		return email_to;
	}
	public void setEmail_to(String email_to) {
		this.email_to = email_to;
	}
	public String getPdfsignedpath() {
		return pdfsignedpath;
	}
	public void setPdfsignedpath(String pdfsignedpath) {
		this.pdfsignedpath = pdfsignedpath;
	}
	public String getInv_path() {
		return inv_path;
	}
	public void setInv_path(String inv_path) {
		this.inv_path = inv_path;
	}
	public String getInv_flag() {
		return inv_flag;
	}
	public void setInv_flag(String inv_flag) {
		this.inv_flag = inv_flag;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getCont_type() {
		return cont_type;
	}
	public void setCont_type(String cont_type) {
		this.cont_type = cont_type;
	}
	public String getEmail_to_otherinv() {
		return email_to_otherinv;
	}
	public void setEmail_to_otherinv(String email_to_otherinv) {
		this.email_to_otherinv = email_to_otherinv;
	}
	public String getCust_nm() {
		return cust_nm;
	}
	public void setCust_nm(String cust_nm) {
		this.cust_nm = cust_nm;
	}
	public String getSub_inv_dt() {
		return sub_inv_dt;
	}
	public void setSub_inv_dt(String sub_inv_dt) {
		this.sub_inv_dt = sub_inv_dt;
	}
	public Vector getEmail_for_email_body() {
		return email_for_email_body;
	}
	public void setEmail_for_email_body(Vector email_for_email_body) {
		this.email_for_email_body = email_for_email_body;
	}
	public String getCustomer_plant_seq_no() {
		return customer_plant_seq_no;
	}
	public void setCustomer_plant_seq_no(String customer_plant_seq_no) {
		this.customer_plant_seq_no = customer_plant_seq_no;
	}
	public String getEmail_cc() {
		return email_cc;
	}
	public void setEmail_cc(String email_cc) {
		this.email_cc = email_cc;
	}
	public String getDue_dt() {
		return due_dt;
	}
	public void setDue_dt(String due_dt) {
		this.due_dt = due_dt;
	}
	public String getTemp_due_dt() {
		return temp_due_dt;
	}
	public void setTemp_due_dt(String temp_due_dt) {
		this.temp_due_dt = temp_due_dt;
	}
	public String getSupplier_nm() {
		return supplier_nm;
	}
	public void setSupplier_nm(String supplier_nm) {
		this.supplier_nm = supplier_nm;
	}
	public String getSupplier_pan_no() {
		return supplier_pan_no;
	}
	public void setSupplier_pan_no(String supplier_pan_no) {
		this.supplier_pan_no = supplier_pan_no;
	}
	public String getSupplier_abbr() {
		return supplier_abbr;
	}
	public void setSupplier_abbr(String supplier_abbr) {
		this.supplier_abbr = supplier_abbr;
	}
	public String getSupplier_cst_no() {
		return supplier_cst_no;
	}
	public void setSupplier_cst_no(String supplier_cst_no) {
		this.supplier_cst_no = supplier_cst_no;
	}
	public String getSupplier_cst_dt() {
		return supplier_cst_dt;
	}
	public void setSupplier_cst_dt(String supplier_cst_dt) {
		this.supplier_cst_dt = supplier_cst_dt;
	}
	public String getSupplier_gst_no() {
		return supplier_gst_no;
	}
	public void setSupplier_gst_no(String supplier_gst_no) {
		this.supplier_gst_no = supplier_gst_no;
	}
	public String getSupplier_gst_dt() {
		return supplier_gst_dt;
	}
	public void setSupplier_gst_dt(String supplier_gst_dt) {
		this.supplier_gst_dt = supplier_gst_dt;
	}
	public String getSupplier_addr() {
		return supplier_addr;
	}
	public void setSupplier_addr(String supplier_addr) {
		this.supplier_addr = supplier_addr;
	}
	public String getSupplier_city() {
		return supplier_city;
	}
	public void setSupplier_city(String supplier_city) {
		this.supplier_city = supplier_city;
	}
	public String getSupplier_phone_no() {
		return supplier_phone_no;
	}
	public void setSupplier_phone_no(String supplier_phone_no) {
		this.supplier_phone_no = supplier_phone_no;
	}
	public String getSupplier_pin_code() {
		return supplier_pin_code;
	}
	public void setSupplier_pin_code(String supplier_pin_code) {
		this.supplier_pin_code = supplier_pin_code;
	}
	public String getDr_cr_dt() {
		return dr_cr_dt;
	}
	public void setDr_cr_dt(String dr_cr_dt) {
		this.dr_cr_dt = dr_cr_dt;
	}
	public String getSub_dr_cr_dt() {
		return sub_dr_cr_dt;
	}
	public void setSub_dr_cr_dt(String sub_dr_cr_dt) {
		this.sub_dr_cr_dt = sub_dr_cr_dt;
	}
	public String getFinance_emailids() {
		return finance_emailids;
	}
	public void setFinance_emailids(String finance_emailids) {
		this.finance_emailids = finance_emailids;
	}
	public String getInvNo() {
		return invNo;
	}
	public String getTruck_no() {
		return truck_no;
	}
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	public void setTruck_no(String truck_no) {
		this.truck_no = truck_no;
	}

	public String getXls_file() {
		return xls_file;
	}

	public String getGas_date() {
		return gas_date;
	}

	public void setGas_date(String gas_date) {
		this.gas_date = gas_date;
	}

	public String getCust_abbr() {
		return cust_abbr;
	}

	public String getPlant_nm() {
		return plant_nm;
	}

	public String getSn_ref_no() {
		return sn_ref_no;
	}

	public String getFgsa_no() {
		return fgsa_no;
	}

	public void setCust_abbr(String cust_abbr) {
		this.cust_abbr = cust_abbr;
	}

	public void setPlant_nm(String plant_nm) {
		this.plant_nm = plant_nm;
	}

	public void setSn_ref_no(String sn_ref_no) {
		this.sn_ref_no = sn_ref_no;
	}

	public void setFgsa_no(String fgsa_no) {
		this.fgsa_no = fgsa_no;
	}

	public String getBcc_mail() {
		return bcc_mail;
	}

	public void setTo_cntct(String to_cntct) {
		this.to_cntct = to_cntct;
	}
}
