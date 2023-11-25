package com.seipl.hazira.dlng.sales_invoice;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.MakeSignature.CryptoStandard;
import com.itextpdf.text.pdf.security.PrivateKeySignature;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class Databean_digital_sign  {
	
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
	 String sign_path="/unsigned_pdf/signed";
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
		    			
		    			if(callFlag.equalsIgnoreCase("fetch_all_signed_pdf")){
		    				fetch_all_signed_pdf();
		    			}
		    			else if(callFlag.equals("fetch_signed_pdf_details")){
		    				sendAll1(); 
		    				fetch_signed_pdf_details();
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
	 String inv_type = "",cc_mail = "",bcc_mail = "";
	 private void fetch_signed_pdf_details() throws Exception {
		 try{
			 queryString="SELECT TO_CHAR(SYSDATE,'DD/MM/YYYY') FROM DUAL";
				rset=stmt.executeQuery(queryString);
				if(rset.next()){
					sysdate=rset.getString(1);
				}
				String f1="";
				String f2="";
				File pdf_file;
				String path=request.getRealPath(sign_path);
				if(cont_type.equalsIgnoreCase("X") || cont_type.equalsIgnoreCase("Y") || cont_type.equalsIgnoreCase("Z") || cont_type.equalsIgnoreCase("1") || cont_type.equalsIgnoreCase("2") || cont_type.equalsIgnoreCase("N")){
					f2=inv_flag+inv_path+"";
				}else{
					f2=inv_flag+inv_path+".pdf";
				}
					f1=inv_flag+inv_path;
//					System.out.println("inv_flag---"+inv_flag);
//					System.out.println("inv_path---"+inv_path);
				f1=path+"/"+f1;
				if(cont_type.equalsIgnoreCase("X") || cont_type.equalsIgnoreCase("Y") || cont_type.equalsIgnoreCase("Z") || cont_type.equalsIgnoreCase("1") || cont_type.equalsIgnoreCase("2") || cont_type.equalsIgnoreCase("N")){
					pdf_file=new File(f1);
				}else{
					pdf_file=new File(f1+".pdf");
				}
//				System.out.println("f1--------"+f1);
				if(pdf_file.exists())
				{
					path=path+"//"+pdf_file;
		            String context_nm = request.getContextPath();
					String server_nm = request.getServerName();
					String server_port = ""+request.getServerPort();
					String url_start = "http://"+server_nm+":"+server_port+context_nm;
					pdfpath = path;
					pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
					pdfpath = url_start+"/"+sign_path+"/"+f2;
//					System.out.println("pdfpath****"+pdfpath);
					pdfsignedpath=pdfpath;
					
				}
				else{
					pdfsignedpath="";
				}
//				System.out.println("inv_type---"+inv_type);
				//For SALES & LTCORA Invoice
				if(inv_type.equalsIgnoreCase("O") || inv_type.equalsIgnoreCase("D")
					|| inv_type.equalsIgnoreCase("CR_signO") || inv_type.equalsIgnoreCase("CR_signD")
					|| inv_type.equalsIgnoreCase("DE_signO") || inv_type.equalsIgnoreCase("DE_signD")) {
					
					queryString="SELECT email FROM FMS7_CUSTOMER_CONTACT_MST A WHERE A.customer_cd='"+customer_cd+"'"
							+ " AND A.def_inv_flag='Y' "
							+ " AND A.active_flag='Y' "
							+ " AND def_inv_to_flag = 'Y' "
							+ " AND A.seq_no='"+contact_person_cd+"' AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
								  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND B.seq_no='"+contact_person_cd+"' AND " +
								  "B.eff_dt<=TO_DATE('"+invoice_dt+"','DD/MM/YYYY'))";
					System.out.println("STEP-1A.4:FMS7_CUSTOMER_CONTACT_MST: "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
					{
						email_to=rset.getString(1)==null?"":rset.getString(1);
					}else {
						
					}
					if(!inv_flag.equalsIgnoreCase("OTHER_")){
						String temp_plant_no = "P"+customer_plant_seq_no.trim();
						queryString = "SELECT email FROM FMS7_CUSTOMER_CONTACT_MST A " +
									  "WHERE A.customer_cd='"+customer_cd+"' AND A.def_inv_flag='Y' AND " +
									  "A.active_flag='Y' AND (A.addr_flag='B' OR A.addr_flag='R' OR " +
									  "A.addr_flag='C' OR A.addr_flag='"+temp_plant_no+"') AND " +
									  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_CONTACT_MST B " +
									  "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no AND " +
									  "B.eff_dt<=TO_DATE('"+invoice_dt+"','DD/MM/YYYY'))";
//						System.out.println("Customer Contact Person Fetch Query = "+queryString);
						rset = stmt.executeQuery(queryString);
						while(rset.next())
						{
							String temp1 = rset.getString(1)==null?"":rset.getString(1);
								if(!email_to.trim().equalsIgnoreCase(temp1)){
									//	cc_email.add(rset.getString(1)==null?"":rset.getString(1));
									email_cc+= rset.getString(1)==null?"":rset.getString(1)+",";
								}
						}
					}
//					System.out.println("inv_flag------"+inv_flag);
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
				//
				
				/*Hiren_20210607 from new Master */
				String userDtl = "select distinct(SEQ_NO) from FMS7_SUPPLIER_CONTACT_MST A"
						+ " where A.ACTIVE_FLAG = 'Y' and A.DEF_INV_FLAG = 'Y'"
						+ " and A.eff_dt = (select max(B.eff_dt) from FMS7_SUPPLIER_CONTACT_MST B "
						+ "	where B.ACTIVE_FLAG = 'Y' and B.DEF_INV_FLAG = 'Y' and B.SEQ_NO = A.SEQ_NO)";
//				System.out.println("userDtl---"+userDtl);
				rset1 = stmt1.executeQuery(userDtl);
				while (rset1.next()) {
				
					String supEmail = "select A.seq_no,nvl(o_inv_cc,'N'),nvl(o_inv_bcc,'N'),nvl(d_inv_cc,'N'),nvl(d_inv_bcc,'N')"
							+ " ,nvl(t_inv_cc,'N'),nvl(t_inv_bcc,'N'),nvl(B.email,'N'),nvl(d_inv_to,'N'),nvl(t_inv_to,'N')"
							+ " from DLNG_MAIL_SETUP_MST A,FMS7_SUPPLIER_CONTACT_MST B"
							+ " where A.seq_no = '"+rset1.getString(1)+"' "
							+ " AND B.DEF_INV_FLAG = 'Y' and B.ACTIVE_FLAG = 'Y' and COMMODITY_TYPE = 'DLNG' "
							+ " AND B.eff_dt = (select max(B.eff_dt) from FMS7_SUPPLIER_CONTACT_MST C "
								+ "	where A.seq_no = '"+rset1.getString(1)+"' AND "
								+ " C.ACTIVE_FLAG = 'Y' and C.DEF_INV_FLAG = 'Y' )"
							+ " and A.seq_no = B.seq_no ";	
//					System.out.println("supEmail----"+supEmail);
					rset = stmt.executeQuery(supEmail);
					if(rset.next())
					{
						if(inv_type.equalsIgnoreCase("O") || inv_type.equalsIgnoreCase("CR_signO") || inv_type.equalsIgnoreCase("DE_signO")) {
							if(rset.getString(2).toString().equalsIgnoreCase("Y")) {
								email_cc+=rset.getString(8)+",";
							}
							if(rset.getString(3).equalsIgnoreCase("Y")) {
								bcc_mail+=rset.getString(8)+",";
							}
						}
						if(inv_type.equalsIgnoreCase("D")  || inv_type.equalsIgnoreCase("CR_signD") || inv_type.equalsIgnoreCase("DE_signD")) {
							if(rset.getString(4).equalsIgnoreCase("Y")) {
								email_cc+=rset.getString(8)+",";
							}
							if(rset.getString(5).equalsIgnoreCase("Y")) {
								bcc_mail+=rset.getString(8)+",";
							}
						}
						if(inv_type.equalsIgnoreCase("T")  || inv_type.equalsIgnoreCase("CR_signT") || inv_type.equalsIgnoreCase("DE_signT")) {
							if(rset.getString(6).equalsIgnoreCase("Y")) {
								email_cc+=rset.getString(8)+",";
							}
							if(rset.getString(7).equalsIgnoreCase("Y")) {
								bcc_mail+=rset.getString(8)+",";
							}
							if(rset.getString(10).equalsIgnoreCase("Y")) {
								email_to+=rset.getString(8)+",";
							}
						}
					}
				}
				if(!email_cc.equals("")){
					email_cc=email_cc.substring(0,email_cc.length()-1);
				}
				
				if(!bcc_mail.equals("")){
					bcc_mail=bcc_mail.substring(0,bcc_mail.length()-1);
				}
				if(inv_type.equalsIgnoreCase("T")   || inv_type.equalsIgnoreCase("CR_signT") || inv_type.equalsIgnoreCase("DE_signT")) {
					if(!email_to.equals("")){
						email_to=email_to.substring(0,email_to.length()-1);
					}
				}
				 
				
				///////////////////
				/*
				 * Hiren_20210607
				 * queryString="SELECT email FROM FMS7_SUPPLIER_CONTACT_MST A WHERE (upper(CONTACT_DESIG) not in ('"+FINANCE_grp.toUpperCase()+"') OR CONTACT_DESIG is null) AND A.def_inv_flag='Y' and active_flag='Y' AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM "
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
				
				queryString="SELECT email FROM FMS7_SUPPLIER_CONTACT_MST A WHERE upper(CONTACT_DESIG) like '%"+FINANCE_grp.toUpperCase()+"%' AND A.def_inv_flag='Y' and active_flag='Y' AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM "
						+ "FMS7_SUPPLIER_CONTACT_MST B " +
						  "WHERE A.supplier_cd=B.supplier_cd AND A.seq_no=B.seq_no AND " +
						  "B.eff_dt<=TO_DATE('"+sysdate+"','DD/MM/YYYY'))";
//				System.out.println("STEP-1A.4:FMS7_CUSTOMER_CONTACT_MST: "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					finance_emailids+= rset.getString(1)==null?"":rset.getString(1)+",";
					//email_to_finance.add(rset.getString(1)==null?"":rset.getString(1));
				}
				
				
				if(!finance_emailids.equals("")){
					finance_emailids+="Kritika.Thaker@shell.com,";
					finance_emailids=finance_emailids.substring(0,finance_emailids.length()-1);
				}*/
				//System.out.println("---finance_emailids--"+finance_emailids);
				
				if(cont_type.equalsIgnoreCase("R") || cont_type.equalsIgnoreCase("T") || cont_type.equalsIgnoreCase("C") || cont_type.equalsIgnoreCase("S") || cont_type.equalsIgnoreCase("L") || cont_type.equalsIgnoreCase("M") || cont_type.equalsIgnoreCase("V")){
					
					queryString="SELECT CUSTOMER_NAME FROM FMS7_CUSTOMER_MST A WHERE CUSTOMER_CD='"+customer_cd+"' AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM "
								+ "FMS7_CUSTOMER_MST B WHERE A.CUSTOMER_CD=B.CUSTOMER_CD AND " +
								  "B.EFF_DT<=TO_DATE('"+invoice_dt+"','DD/MM/YYYY'))";
					rset=stmt.executeQuery(queryString);
//					System.out.println("queryString--"+queryString);
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
/*
 * Hiren_20201228 As per customer req.
 * 				queryString="SELECT CONTACT_PERSON,EMAIL,CONTACT_DESIG FROM FMS7_SUPPLIER_CONTACT_MST A WHERE CONTACT_DESIG IN ('Commercial Operations Advisor','Commercial Operations Manager') "
							+ "AND ACTIVE_FLAG='Y' AND A.EFF_DT=(SELECT MAX(B.EFF_DT) FROM FMS7_SUPPLIER_CONTACT_MST B " +
							  "WHERE A.SUPPLIER_CD=B.SUPPLIER_CD AND A.SEQ_NO=B.SEQ_NO AND B.EFF_DT<=TO_DATE('"+sysdate+"','DD/MM/YYYY'))";*/
				
				queryString="SELECT CONTACT_PERSON,EMAIL,CONTACT_DESIG FROM FMS7_SUPPLIER_CONTACT_MST A WHERE CONTACT_DESIG IN ('Commercial Operations Advisor') "
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
				if(!dr_cr_dt.equals("")){
					queryString="SELECT TO_CHAR(TO_DATE('"+dr_cr_dt+"','DD-Mon-YY'),'DD-MM-YYYY') FROM DUAL";
					rset=stmt.executeQuery(queryString);
					if(rset.next()){
						sub_dr_cr_dt=rset.getString(1)==null?"":rset.getString(1);
					}
				}
				//System.out.println("---"+email_for_email_body);
				
				
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
	 private void fetch_all_signed_pdf() throws Exception {
		 try{
		 	/*String context_nm = request.getContextPath();
			String server_nm = request.getServerName();
			String server_port = ""+request.getServerPort();
			String url_start = "http://"+server_nm+":"+server_port+context_nm;
			String pdfpath="",pdf_type="",pdf_inv_nm="";
			String path=request.getRealPath("/unsigned_pdf/signed/");
			File signed_temp_folder = new File(path);
		    String[] signed_file_bunch_qtr = null;
		    signed_file_bunch_qtr = signed_temp_folder.list();
	        for (int j = 0; j < signed_file_bunch_qtr.length; j++) {
	             String signed_file = signed_file_bunch_qtr[j];
	             File signed_temp_f = new File(signed_temp_folder.getAbsolutePath() + "\\" + signed_file);
	             signed_files_nm.add(signed_file);
	             pdfpath = url_start+"/unsigned_pdf/signed/"+signed_file;
	             pdf.add(pdfpath);
	             if(signed_file.contains(".pdf")){
                	 String temp1[]=signed_file.split(".pdf");
                	 pdf_type=(temp1[0].toString().substring(temp1[0].toString().length()-1,temp1[0].toString().length()));
		             String temp[]=signed_file.split("_");
		             pdf_inv_nm=temp[1].substring(0,temp[1].length()-6);
	             }
	             queryString="select to_char(signed_dt,'dd/mm/yyyy hh24:mi:ss') from fms8_inv_pdf_dtl where pdf_inv_nm like '"+pdf_inv_nm+"%' and inv_type='"+pdf_type+"' and pdf_signed_flag='Y'";
	             rset=stmt.executeQuery(queryString);
	             System.out.println("queryString--"+queryString);
	             if(rset.next()){
	            	 pdf_sign_dt.add(rset.getString(1)==null?"":rset.getString(1));
	             }else{
	            	 pdf_sign_dt.add("-");
	             }
	             
	         }*/
			 String context_nm = request.getContextPath();
				String server_nm = request.getServerName();
				String server_port = ""+request.getServerPort();
				String url_start = "http://"+server_nm+":"+server_port+context_nm;
				String pdfpath="",pdf_type="",pdf_inv_nm="";
				String path=request.getRealPath("/unsigned_pdf/signed/");
				File signed_temp_folder = new File(path);
			    String[] signed_file_bunch_qtr = null;
			    signed_file_bunch_qtr = signed_temp_folder.list();
		        for (int j = 0; j < signed_file_bunch_qtr.length; j++) {
		             String signed_file = signed_file_bunch_qtr[j];
		             File signed_temp_f = new File(signed_temp_folder.getAbsolutePath() + "\\" + signed_file);
		             signed_files_nm.add(signed_file);
		         }
		        if(!from_dt.equals("") && (!to_dt.equals(""))){
		        	 queryString="select to_char(signed_dt,'dd/mm/yyyy hh24:mi:ss'),pdf_inv_nm,inv_type,signed_by from fms8_inv_pdf_dtl where pdf_signed_flag='Y' and"
		        	 		+ " (signed_dt >= to_date('"+from_dt+"','dd/mm/yyyy') and signed_dt< to_date('"+to_dt+"','dd/mm/yyyy')+1)";
		             rset=stmt.executeQuery(queryString);
		             //System.out.println("queryString--"+queryString);
		             while(rset.next()){
		            	
		            	 String pdf_nm=rset.getString(2)+"-"+rset.getString(3)+".pdf";
		            	 if(signed_files_nm.contains("Advance_"+pdf_nm)){
		            		 pdfpath = url_start+"/unsigned_pdf/signed/Advance_"+pdf_nm;
		            		 pdf.add(pdfpath);
		            		 temp_signed_files_nm.add("Advance_"+pdf_nm);
		            		 signed_by.add(rset.getString(4)==null?"-":rset.getString(4));
		            		 pdf_sign_dt.add(rset.getString(1)==null?"":rset.getString(1));
		            	 }
		            	 else if(signed_files_nm.contains("SUG_"+rset.getString(2)+".pdf")){
		            		 pdf_nm=rset.getString(2)+".pdf";
		            		 pdfpath = url_start+"/unsigned_pdf/signed/SUG_"+pdf_nm;
		            		 pdf.add(pdfpath);
		            		 temp_signed_files_nm.add("SUG_"+pdf_nm);
		            		 signed_by.add(rset.getString(4)==null?"-":rset.getString(4));
		            		 pdf_sign_dt.add(rset.getString(1)==null?"":rset.getString(1));
		            	 }
		            	 else if(signed_files_nm.contains("SALES_"+pdf_nm)){
		            		 pdfpath = url_start+"/unsigned_pdf/signed/SALES_"+pdf_nm;
		            		 pdf.add(pdfpath);
		            		 temp_signed_files_nm.add("SALES_"+pdf_nm);
		            		 signed_by.add(rset.getString(4)==null?"-":rset.getString(4));
		            		 pdf_sign_dt.add(rset.getString(1)==null?"":rset.getString(1));
		            	 } else if(signed_files_nm.contains("LOA_"+pdf_nm)){
		            		 pdfpath = url_start+"/unsigned_pdf/signed/LOA_"+pdf_nm;
		            		 pdf.add(pdfpath);
		            		 temp_signed_files_nm.add("LOA_"+pdf_nm);
		            		 signed_by.add(rset.getString(4)==null?"-":rset.getString(4));
		            		 pdf_sign_dt.add(rset.getString(1)==null?"":rset.getString(1));
		            	 }else if(signed_files_nm.contains("LTCORA_"+pdf_nm)){
		            		 pdfpath = url_start+"/unsigned_pdf/signed/LTCORA_"+pdf_nm;
		            		 pdf.add(pdfpath);
		            		 temp_signed_files_nm.add("LTCORA_"+pdf_nm);
		            		 signed_by.add(rset.getString(4)==null?"-":rset.getString(4));
		            		 pdf_sign_dt.add(rset.getString(1)==null?"":rset.getString(1));
		            	 }else{
		            		 pdf_nm=rset.getString(2);
		            		 String temp_pdf_nm="";
		            		 if(pdf_nm.contains(".pdf")){
		            			 String temp_pdf[]=pdf_nm.split(".pdf");
		            			 temp_pdf_nm=temp_pdf[0]+"-"+rset.getString(3)+".pdf";
		            			 if(signed_files_nm.contains("OTHER_"+temp_pdf_nm)){
				            		 pdfpath = url_start+"/unsigned_pdf/signed/OTHER_"+temp_pdf_nm;
				            		 pdf.add(pdfpath);
				            		 temp_signed_files_nm.add("OTHER_"+temp_pdf_nm);
				            		 signed_by.add(rset.getString(4)==null?"-":rset.getString(4));
				            		 pdf_sign_dt.add(rset.getString(1)==null?"":rset.getString(1));
				            	 }
		            		 }
		            	 }
		             }
		        }
		 }catch(Exception e){
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
	public void setInv_type(String inv_type) {
		this.inv_type = inv_type;
	}
	public Vector getCc_email() {
		return cc_email;
	}
	public String getCc_mail() {
		return cc_mail;
	}
	public String getBcc_mail() {
		return bcc_mail;
	}
	
}
