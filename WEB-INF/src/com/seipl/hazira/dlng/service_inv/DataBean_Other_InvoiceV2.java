package com.seipl.hazira.dlng.service_inv;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.UtilBean;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class DataBean_Other_InvoiceV2
{
    Connection conn; 
	Statement stmt;
	Statement stmt1;
	Statement stmt2;
	Statement stmt3;
	Statement stmt4;
	Statement stmt5;
	Statement stmt6;
	ResultSet rset;
	ResultSet rset1;
	ResultSet rset2;
	ResultSet rset3;
	ResultSet rset4;
	ResultSet rset5;
	ResultSet rset6;
	String queryString = "";
	String queryString1 = "";
	String queryString2 = "";
	String queryString3 = "";
	String queryString4 = "";
	String queryString5 = "";
	String queryString6 = "";
	
	String callFlag = "";
	String month = "";
	String year = "";
	String from_dt = "";
	String to_dt ="";
	String activity="";
	String operation ="";
	String pay_cd = "";
	
	String billCycle = "0";
	String methodName = "";
	String databeanName = "DataBean_other_Invoice";
	public String url_start = "";
	public HttpServletRequest request = null;
	public String pdf_path = ""; //By Achal Pathak ...
	String fileName = "";
	 boolean flag_DCB = false;
	
	
	//Following NumberFormat Object is defined by Samik Shah ... On 20th May, 2010 ...
	NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf2 = new DecimalFormat("##0.0000"); //For Currency Purpose ... Defined By Samik Shah On 1st June, 2010 ...
	NumberFormat nf3 = new DecimalFormat("###,###,###,##0.00");	
	
	NumberFormat nf1= new DecimalFormat("##,##,##,##0.00");  //SB20170901 //For Indian Currency System
	NumberFormat nf4 = new DecimalFormat("##0.00");
	NumberFormat nf5 = new DecimalFormat("##########0");
//SB20170901	NumberFormat nf6 = new DecimalFormat("###,###,###,##0");
//SB20170901	NumberFormat nf7 = new DecimalFormat("###,###,###,##0.0000"); //For Currency Purpose .
	NumberFormat nf6 = new DecimalFormat("#,##,##,##,##,##0"); //For Indian Currency System
	NumberFormat nf7 = new DecimalFormat("#,##,##,##,##,##0.0000"); //For Indian Currency System
	
	
	//Following util Object is defined by Samik Shah ... On 20th May, 2010 ...
	UtilBean util = new UtilBean();
	
	//Following Defined For Other Invoices////RG20170619//////////////////
	String contact_Suppl_Service_tax_no="";
	 String contact_Suppl_Name = "";
	 String contact_Suppl_PAN_NO = "";
	 String contact_Suppl_PAN_DT = "";
	 String contact_Suppl_GST_NO = "";
	 String contact_Suppl_CST_NO = "";
	 String contact_Suppl_GST_DT = "";
	 String contact_Suppl_CST_DT = "";
	 String flag="";
	 Vector contact_Customer_pAN_NO=new Vector();
	Vector contact_Customer_Name=new Vector();
	Vector contact_Customer_cd=new Vector();
	String Supp_stat_nm="";
	String Cust_gstin_no11="";
	String pacer_no="";
	String Custcountry="";
	String Vendor_no="";
	String sale_no="";
	String flag_sac="";
	String Supp_stat_CD="";
	String supplier_cd1="";
	String supplier_nm1="";
	String supplier_abbr="";
	String Cust_stat_cd="";
	String Cust_stat_nm="";
	String contact_cust_Name="";
	Vector Vvessel_item=new Vector();
	Vector Vinv_seq_no=new Vector();
	Vector Vinv_dt=new Vector();
	Vector Vhlpl_inv_seq_no=new Vector();
	Vector Vnew_seq_no=new Vector();
	Vector Vgross_inr=new Vector();
	Vector Vgross_usd=new Vector();
	Vector Vtax_inr=new Vector();
	Vector Vnet_inr=new Vector();
	Vector VCust_nm=new Vector();
	Vector VvCust_nm=new Vector();
	Vector Vseq_not=new Vector();
	Vector Vreason=new Vector();
	Vector Vcriteria=new Vector();
	Vector Vfinnan_yr=new Vector();Vector Vcont_type=new Vector();
	String  criteria="";
	String Hlpl_seq_no="";
	String Sdr_cr="";
	String VFin_yr="";
	String Scriteria="";
	Vector Vfin_yr=new Vector();
	Vector Vcust_nm=new Vector();
	Vector Checked=new Vector();
	Vector approved=new Vector();
	Vector Vpdf_dtl=new Vector();
	String pdfpath="";
	Vector pdf=new Vector();
	Vector pdf1=new Vector();
	Vector pdf2=new Vector();
	Vector VSupp_cd=new Vector();
	Vector VCust_cd=new Vector(); //SB20171025
	Vector Vvendor_abr=new Vector(); //SB20171025
	Vector Vvendor_nm=new Vector(); //SB20171025
	Vector Vflag_sac=new Vector();
	Vector Vvessel_cd=new Vector();
	Vector Vvessel_nm=new Vector();
	Vector Vvessel_flg=new Vector();
	Vector CUST_ADDRESS=new Vector();
	Vector CUST_state=new Vector();
	Vector cust_state_cd=new Vector();
	String supp_cd_hid="";
	Vector VSupp_state_nm=new Vector();
	Vector VSupp_state_cd=new Vector();
	String Cust_address_nm="";
	String contact_cust_PAN_NO="";
	String contact_cust_gstin_no="";
	String cust_cd1="";
	String supp_nm="";
	String cust_gstin_no="";
	String supp_cd_set="";
	String supp_CD="";
	String cust_sts_cd="";
	Vector Vcargo_ref_no=new Vector();
	Vector Vcargo_dt=new Vector();
	Vector Vcargo_typ=new Vector();
	Vector Vqty=new Vector();
	Vector exchg_cd=new Vector();
	Vector exchg_nm=new Vector();
	Vector Exchg_val=new Vector();
	String sysdate="";
	Vector Exchg_eff_dt=new Vector();
	Vector Vship_cd=new Vector();
	String supplier_cd_cust="";
	String supplier_nm_cust="";
	String state_cust="";
	String user_dt="";
	Vector state_cd=new Vector();
	Vector state_nm=new Vector();
	Vector Tax_Name=new Vector();
	String amount="";
	Vector Tax_nm=new Vector();
	double gross_amt=0;
	Vector grossamt=new Vector();
	double gross_amt1=0;
	double net_amt_inr=0;
	String tax_amt="";
	String tax_dtls="";
	String Stax_str="";
	Vector taxstr=new Vector();
	String Stax_str1="";
	String contact_Suppl_gstin_no="";
	String inv_type="";
	String Addr="";
	String city="";
	String state="";
	String bill_stat_CD="";
	String st_dt="";
	String end_dt="";
	String fin_yr="";
	String st_yr="";
	String cust_seq_no="";
	String inv_seq_no="";
	String new_inv_no="";
	String scountry="";
	String GST_INVOICE_SEQ_NO="";
	String dt="";
	String ad="";
	String cust_seq_no1="";
	String inv_seq_no1="";
	String new_inv_no1="";
	String GST_INVOICE_SEQ_NO1="";
	String supp_cd="";
	//String Sgross_amt_usd="";
	String flag_act="";
	String Snew_inv_sq_no="";
	String currency="";String exch_rt="";String exch_dt="";
	String gross_amt_usd="";
	String custom_cd="";
	String Snew_inv_no="";String Sinv_no="";
	String amt="";
	String amt_chk="";
	String Sgross_amt="";
	String tax_amt_inr="";
	String snet_amt_inr="";
	String Supp_addr_nm="";
	String pin="";
	String Supp_addr_city="";
	String cust_nm="";
	String cust_cd=""; //SB20171104
	String cust_addr="";
	String cust_st_cd="";
	String cust_city="";
	String cust_pin="";
	String tax_cd="";
	String cgst="";
	String sgst="";String igst="";
	String Sdr_cr_no="";
	String Sdr_cr_doc_no="";
	String Sdr_cr_dt="";
	String Sdiff_qty="";
	String Sdiff_rate="";
	String Snet_amount="";
	String Stax_amount="";
	String Sgrs_amount="";
	String Sgrs_amount_inr="";
	String Sberth_hsr="";
	String Sslots_berth="";
	String Sdr_cr_fin_yr="";
	String item_desc="";
	String sac_cd="";
	String  hasn_cd="";
	String cust_state_cd1="";
	String pur_no="";
	String ref_no="";
	Vector Vcargorate=new Vector();
	Vector Vcargodt=new Vector();
	Vector Vcargoqty=new Vector();
	Vector Vcargo_amt=new Vector();
	Vector Vcargo_cd=new Vector();
	String cargo_typ="";
	 Vector qtyabc=new Vector();
	 Vector rtabc=new Vector();
	Vector abc=new Vector();
	Vector Vship_nm1=new Vector(); 
	String exch_cd="";
	String exch_val="";
	double fin_tax=0;
	Vector Ttax_amt_inr_chk=new Vector();	
	String tax="";
	String tax1="";
	Vector taxstr1=new Vector();
	String svitem="";
	String shrs="";
	String sslots="";	
	String fax_R="";
	String phone_R="";
	String pin_R="";
	String Supp_addr_city_R="";
	String Supp_addr_nm_R="";
	String fax_C="";
	String phone_C="";
	String pin_C="";
	String Supp_addr_city_C="";
	String Supp_addr_nm_C="";
	String Sgross_amt_inr_chk="";
	String tax_amt_inr_chk="";
	String snet_amt_inr_chk="";	
	String Ssupp_nm="";
	String Ssupp_cd="";
	String cntry_R="";
	String cntry_C="";
	String Sgross_amt_usd_chk="";
	String result="";
	String supplier_nm="";
	String supplier_cd="";
	String country="";
	Vector tt1_rate=new Vector();
	Vector Vsac_cd=new Vector();
	Vector Vdesc=new Vector();
	Vector Vsac_hsn_perc=new Vector(); //SB20180215
	Vector VCqty=new Vector();
	Vector Vvsac_cd=new Vector();
	Vector Vuam_no=new Vector();
	Vector Vvsac_desc=new Vector();
	Vector Vhsn_cd=new Vector();
	Vector VCamt=new Vector();
	Vector VCrate=new Vector();
	String pdf_flag="";int count=0;
	String pdf_inv_dtl="";
	Vector Vitm=new Vector();
	String sac_descr="";
	 String gate_no="";
	 Vector camt=new Vector();
	 Vector crt=new Vector();
	 String yr="";String Mont=""; 
	 String mt=""; 
	 String rule_rmk="";
	 String cst_gstin_no="";
	 String SUPP_state_NM="";
	 Vector form_rate=new Vector();
	 Vector Vendor_cd=new Vector();
	Vector Vendor_abbr=new Vector();
	String seq_no="";
	String Sdue_dt="";
	String Sinv_dt="";
	String Sgross_amt_usd="";
	String Stax_Amt="";
	String SNet_Amt="";
	String ScURR="";
	String Cust_name="";
	String Srate="";
	String SUPP_ST_CD="";
	String Sqty="";
	String Sgrt="";
	String sitem_desc="";
	String sCust_addr="";
	String sitem="";
	String sVflag="";
	String svessel_ag="";
	String svessel_cd="";
	String sberthing_hrs="";
	String sCust_pan_no="";
	String simporter="";
	String sCust_state="";
	String ship_nm="";
	String sstate_nm="";
	String end_dt1="";String dt1="";
	Vector Vvcust_cd=new Vector();
	Vector Vdr_cr_doc_no=new Vector();
	Vector Vdr_cr_no=new Vector();
	Vector Vdr_cr_flag=new Vector();
	Vector Vset_duedt=new Vector();
	Vector Vset_invdt=new Vector();
	String Sgross_amt_inr="";
	Vector Ttax_amt_inr=new Vector();
	String remark="";
	String remark1="";
	String cargo_type="";
	String	state_nm1="";
	String remark2="";
	String remark3="";
	String Custpin_R="";
	String Cust_addr_city_R="";
	 String formatted_rate="";
	Vector Vendor_nm=new Vector();
	Vector Vendor_pan_no=new Vector();
	Vector Vendor_gstin_no=new Vector();
	Vector Vendor_addr=new Vector();
	Vector Vendor_city=new Vector();
	Vector Vendor_pin=new Vector();
	Vector Vendor_country=new Vector();
	Vector Vendor_state=new Vector();
	Vector Vstate_cd=new Vector();
	String sac_desc="";
	String SVendor_nm="";
	String SVendor_abr=""; //SB20171104
	String Vendor_addr1="";
	Vector Vsupp_cd=new Vector();
	Vector Vsupp_nm=new Vector();
	Vector Vsupp_abbr=new Vector();
	Vector Vsupp_gstin_no=new Vector();
	Vector Vsupp_pan_no=new Vector();
	String SuppAbr=""; //SB20180222
	////////////////////SB20171107////////////
	String TemplateType="0";
	//////////////////////////////////////////
	public void init()
	{
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
	    			stmt3 = conn.createStatement();
	    			stmt4 = conn.createStatement();
	    			stmt5 = conn.createStatement();
	    			stmt6 = conn.createStatement();
	    			
//	    			clearVectorAndStringVariables();
	    			 if(callFlag.equalsIgnoreCase("FETCH_INVOICE_DTL")) //SB20171108
						{	 
//	    				 	Supl_Inv_MST();//SB20180319
//	    				    FETCH_INVOICE_DTL2();  //SB20180207
						}
	    			if(callFlag.equalsIgnoreCase("INVOICE_DTL_Z")) //SB20171108
						{	 
	    			//////SB20180407 TemplatePDF(); //SB20161212
	    			/////////////////////////////////	 TemplateTableList(); //SB20180402
	    				 INVOICE_DTL_Z();  //RG20170720
	    	/*			 if(flag_act.equalsIgnoreCase("P")) 
	 						{
	    					 	printPdfFileFor_Z();	//System.out.println(flag_act+" :*******  Call pdf_textNew: "+Snew_inv_sq_no);
	 							pdf_textNew(SVendor_abr, FileHeaderInvType,Snew_inv_sq_no,SubHeader, SubHeader2, SubHeader3,SubjectLeft,SubjectRight, Body, Signature, PaymentRemark, rule_rmk,"", Sinv_dt, Sdue_dt, 5, pdf_flag);
	 	 					}
	    				 Update_Table_Entry_PDF2(); //SB20180207
	    				 GetPDF_Template(); //Get PDF Template
	    				 */
						}
						if(callFlag.equalsIgnoreCase("CheckApprove_INVOICE_DTL")) //SB20180310
						{	 
	    				 CheckApprove_INVOICE_DTL();  //RG20170720
	    				 if(flag_act.equalsIgnoreCase("P")) 
	 						{
	    					 	TemplatePDF(); //SB20161212
	    					 	printPdfFileFor_Z();	//System.out.println(flag_act+" :*******  Call pdf_textNew: "+Snew_inv_sq_no);
	 							pdf_textNew(SVendor_abr, FileHeaderInvType,Snew_inv_sq_no,SubHeader, SubHeader2, SubHeader3,SubjectLeft,SubjectRight, Body, Signature, PaymentRemark, rule_rmk,"", Sinv_dt, Sdue_dt, 5, pdf_flag);
	 							Update_Table_Entry_PDF2(); //SB20180207
	 		    				GetPDF_Template(); //Get PDF Template
	 	 					}
						}
	    			else if(callFlag.equalsIgnoreCase("Customer_Dtl_Rpt")) 
	    			{
	    				Customer_DTL_Rpt();
	    				Fetch_Invoice_data();
	    			}
	    			else if(callFlag.equals("GST_REGISTER")) {
	    				fetch_gst_register();
	    			}
	    			else if(callFlag.equals("GST1_Return")) {
	    				fetch_GST1_Return();
	    			}
	    			else if(callFlag.equals("GST3B_REGISTER")) {
	    				fetch_GST3B_register();
	    			}
	    			conn.close();
	    			conn = null;
	    		}
	    	}
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Exception In : ("+databeanName+") - (init()): "+e.getMessage());
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
	    	if(rset3 != null)
	    	{
				try
				{
					rset3.close();
				}
				catch(SQLException e)
				{
					System.out.println("rset3 is not close "+e);
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
					System.out.println("rset4 is not close "+e);
				}
			}
	    	if(rset5 != null)
	    	{
				try
				{
					rset5.close();
				}
				catch(SQLException e)
				{
					System.out.println("rset5 is not close "+e);
				}
			}
	    	if(rset6 != null)
	    	{
				try
				{
					rset6.close();
				}
				catch(SQLException e)
				{
					System.out.println("rset6 is not close "+e);
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
			if(stmt3 != null)
			{
				try
				{
					stmt3.close();
				}
				catch(SQLException e)
				{
					System.out.println("stmt3 is not close "+e);
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
					System.out.println("stmt4 is not close "+e);
				}
			}
			if(stmt5 != null)
			{
				try
				{
					stmt5.close();
				}
				catch(SQLException e)
				{
					System.out.println("stmt5 is not close "+e);
				}
			}
			if(stmt6 != null)
			{
				try
				{
					stmt6.close();
				}
				catch(SQLException e)
				{
					System.out.println("stmt6 is not close "+e);
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
	
	String supp_cd_x = "0";


	public String createPdfFileFor_Z()
	{
		try
		{
			
			HttpSession sess = request.getSession();
		///SB	pdf_path = request.getRealPath("/pdf_reports/other_invoices/pdf_files");
			pdf_path = request.getRealPath("/pdf_reports/other_invoices/BIPL");
			//System.out.println("pdf_path--"+pdf_path);
			fileName ="INVOICE-"+inv_type+"-"+VFin_yr+"-"+Hlpl_seq_no+"-"+supp_cd+"-"+pdf_flag+".pdf";
			//System.out.println("hellooo------"+title_cd+"--"+sys_dt);
			pdf_path = pdf_path+"//"+fileName;
			System.out.println("fileName = "+fileName+", final_custom_pdf_path = "+pdf_path);
		}
		catch(Exception e)
		{
			System.out.println("Exception in createPdfFileForFINAL_CUSTOM_DUTY() Method :\n"+e.getMessage());
			e.printStackTrace();
		}
		return pdf_path;
	}
	
	public void Update_Table_Entry_PDF() throws Exception
	{
		try
		{
			//CODE ADDED FOR SUPPLIER CD BY RS 04082017 FOR UNIQUE ENTRY FOR CONTRACT TYPE X AGAINST SUPPLIER CD 1,2
			String supp_cd = "";
			if(inv_type.equalsIgnoreCase("1") || inv_type.equalsIgnoreCase("2")) {
				supp_cd =supplier_cd1;
			}else{
				if(inv_type.equalsIgnoreCase("X")){
					supp_cd = Ssupp_cd;
				}else{
					supp_cd = supplier_cd;
				}
			}
			
			String query="select pdf_inv_dtl from DLNG_SERVICE_INVOICE_MST where contract_type='"+inv_type+"' " +
					"and financial_year='"+VFin_yr+"' and inv_seq_no='"+Hlpl_seq_no+"' and supplier_cd='"+supp_cd+"' ";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				pdf_inv_dtl=rset.getString(1)==null?"":rset.getString(1);
			}
			
			if(pdf_inv_dtl.equalsIgnoreCase(""))
			{
				String q1="update DLNG_SERVICE_INVOICE_MST set pdf_inv_dtl='"+pdf_flag+"' where contract_type='"+inv_type+"' " +
					"and financial_year='"+VFin_yr+"' and inv_seq_no='"+Hlpl_seq_no+"' and supplier_cd='"+supp_cd+"' ";
				stmt.executeUpdate(q1);
			}
			else
			{
				if(pdf_inv_dtl.contains(pdf_flag))
				{
					
				}
				else
				{
					pdf_inv_dtl+=pdf_flag;
					String q1="update DLNG_SERVICE_INVOICE_MST set pdf_inv_dtl='"+pdf_inv_dtl+"' where contract_type='"+inv_type+"' " +
					"and financial_year='"+VFin_yr+"' and inv_seq_no='"+Hlpl_seq_no+"' and supplier_cd='"+supp_cd+"' ";
					stmt.executeUpdate(q1);
				}
			}
			if(inv_type.equalsIgnoreCase("1") || inv_type.equalsIgnoreCase("2")) {
			
				fileName ="INVOICE-"+inv_type+"-"+VFin_yr+"-"+Hlpl_seq_no+"-"+supplier_cd1+".pdf";
			}else{
				//fileName ="INVOICE-"+inv_type+"-"+VFin_yr+"-"+Hlpl_seq_no+"-"+supplier_cd+".pdf";
				if(inv_type.equalsIgnoreCase("X")){
					fileName ="INVOICE-"+inv_type+"-"+VFin_yr+"-"+Hlpl_seq_no+"-"+Ssupp_cd+".pdf";
				}else{
					fileName ="INVOICE-"+inv_type+"-"+VFin_yr+"-"+Hlpl_seq_no+"-"+supplier_cd+".pdf";
				}
			}
			String query1="SELECT COUNT(*) FROM DLNG_INV_PDF_DTL WHERE PDF_INV_NM='"+fileName.trim()+"' "
					+ "AND INV_TYPE='"+pdf_flag.trim()+"' ";
			rset=stmt.executeQuery(query1);
			if(rset.next()) {
				count=rset.getInt(1);
			}else{
				count=0;
			}
			if(count==0) {
				String queryString2="INSERT INTO DLNG_INV_PDF_DTL (pdf_inv_nm,INV_TYPE,FLAG,CREATED_DT) VALUES ('"+fileName.trim()+"','"+pdf_flag.trim()+"','Y',sysdate)  ";
	//						System.out.println("----qhkdhf----"+queryString2);
				stmt.executeUpdate(queryString2);
			}else{
				String queryString2="UPDATE DLNG_INV_PDF_DTL SET pdf_inv_nm='"+fileName.trim()+"',INV_TYPE='"+pdf_flag.trim()+"',CREATED_DT= sysdate where INV_TYPE='"+pdf_flag.trim()+"' and pdf_inv_nm='"+fileName.trim()+"'  ";
//							System.out.println("----qhkdhf----"+queryString2);
				stmt.executeUpdate(queryString2);
			}
			
			conn.commit();
		}
		catch(Exception e)
		{
			conn.rollback();
//			System.out.println("Exception in Databean_other_innvoice() Method :\n"+e.getMessage());
			e.printStackTrace();
		}
		
	}
	public void Update_Table_Entry_PDF2() throws Exception
	{
		try
		{
			//CODE ADDED FOR SUPPLIER CD BY RS 04082017 FOR UNIQUE ENTRY FOR CONTRACT TYPE X AGAINST SUPPLIER CD 1,2
			String supp_cd = "";
			if(inv_type.equalsIgnoreCase("1") || inv_type.equalsIgnoreCase("2")) {
				supp_cd =supplier_cd1;
			}else{
				if(inv_type.equalsIgnoreCase("X")){
					supp_cd = Ssupp_cd;
				}else{
					supp_cd = supplier_cd;
				}
			}
			
			String query="select pdf_inv_dtl from DLNG_AC_INVOICE_MST where contract_type='"+inv_type+"' " +
					"and financial_year='"+VFin_yr+"' and inv_seq_no='"+Hlpl_seq_no+"' and supplier_cd='"+supp_cd+"' ";
			rset=stmt.executeQuery(query);
			if(rset.next())
			{
				pdf_inv_dtl=rset.getString(1)==null?"":rset.getString(1);
			}
			
			if(pdf_inv_dtl.equalsIgnoreCase(""))
			{
				String q1="update DLNG_AC_INVOICE_MST set pdf_inv_dtl='"+pdf_flag+"' where contract_type='"+inv_type+"' " +
					"and financial_year='"+VFin_yr+"' and inv_seq_no='"+Hlpl_seq_no+"' and supplier_cd='"+supp_cd+"' ";
				stmt.executeUpdate(q1);
			}
			else
			{
				if(pdf_inv_dtl.contains(pdf_flag))
				{
					
				}
				else
				{
					pdf_inv_dtl+=pdf_flag;
					String q1="update DLNG_AC_INVOICE_MST set pdf_inv_dtl='"+pdf_inv_dtl+"' where contract_type='"+inv_type+"' " +
					"and financial_year='"+VFin_yr+"' and inv_seq_no='"+Hlpl_seq_no+"' and supplier_cd='"+supp_cd+"' ";
					stmt.executeUpdate(q1);
				}
			}
			if(inv_type.equalsIgnoreCase("1") || inv_type.equalsIgnoreCase("2")) {
			
				fileName ="INVOICE-"+inv_type+"-"+VFin_yr+"-"+Hlpl_seq_no+"-"+supplier_cd1+".pdf";
			}else{
				//fileName ="INVOICE-"+inv_type+"-"+VFin_yr+"-"+Hlpl_seq_no+"-"+supplier_cd+".pdf";
				if(inv_type.equalsIgnoreCase("X")){
					fileName ="INVOICE-"+inv_type+"-"+VFin_yr+"-"+Hlpl_seq_no+"-"+Ssupp_cd+".pdf";
				}else{
					fileName ="INVOICE-"+inv_type+"-"+VFin_yr+"-"+Hlpl_seq_no+"-"+supplier_cd+".pdf";
				}
			}
			if(!pdf_flag.trim().equals(""))
			{
				String query1="SELECT COUNT(*) FROM DLNG_INV_PDF_DTL WHERE PDF_INV_NM='"+fileName.trim()+"' "
						+ "AND INV_TYPE='"+pdf_flag.trim()+"' ";
				System.out.println("----DLNG_INV_PDF_DTL---: "+query1);
				rset=stmt.executeQuery(query1);
				if(rset.next()) 
				{
					count=rset.getInt(1);
				}
				else
				{
					count=0;
				}
				if(count==0) 
				{
					String queryString2="INSERT INTO DLNG_INV_PDF_DTL (pdf_inv_nm,INV_TYPE,FLAG,CREATED_DT) VALUES ('"+fileName.trim()+"','"+pdf_flag.trim()+"','Y',sysdate)  ";
					System.out.println("----DLNG_INV_PDF_DTL---: "+queryString2);
					stmt.executeUpdate(queryString2);
				}
				else
				{
					String queryString2="UPDATE DLNG_INV_PDF_DTL SET pdf_inv_nm='"+fileName.trim()+"',INV_TYPE='"+pdf_flag.trim()+"',CREATED_DT= sysdate where INV_TYPE='"+pdf_flag.trim()+"' and pdf_inv_nm='"+fileName.trim()+"'  ";
								System.out.println("----DLNG_INV_PDF_DTL---: "+queryString2);
					stmt.executeUpdate(queryString2);
				}
			}
			conn.commit();
		}
		catch(Exception e)
		{
			conn.rollback();
//			System.out.println("Exception in Databean_other_innvoice() Method :\n"+e.getMessage());
			e.printStackTrace();
		}
		
	}
	public void printPdfFileFor_Z()  throws Exception
	{
		System.out.println("--PRINT PDF START >>>>>");
		Rectangle pageSize = new Rectangle(595, 842);
		if(flag_DCB==true)
		{
			pageSize.setBackgroundColor(new java.awt.Color(0xe3f8c0)); 
		}
		else
		{
			pageSize.setBackgroundColor(new java.awt.Color(0xffffff)); 
		}
		 Document document = new Document(pageSize);
		
        try
		{
			// Step 2: We create a writer that listens to the document, and directs a PDF-stream to a file ...
			PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(createPdfFileFor_Z()));			
	//		writer.setEncryption(PdfWriter.STRENGTH128BITS, "", "FINAL OF CARGO & CUSTOM DUTY", PdfWriter.AllowCopy | PdfWriter.AllowPrinting);
			document.addTitle("FINAL");
            document.addSubject("FINAL");
            document.addKeywords("iText, FINAL, Step 2, metadata");
            document.addCreator("FINAL using iText");	
            document.addAuthor("SB@BIPL");		
            //Step 3: We open the document for PDF writing ...
			document.open();
			//Step 4: We create Page Size, Font Size , and Font Type ... Then add some paragraphs to the document ...
			document.setPageSize(pageSize);
            document.newPage();
            
            Font small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new Color(0x00, 0x00, 0x00));
            Font small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
            Font small_black_normal1 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0x00, 0x00, 0x00));
            Font small_black_bold1 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new Color(0x00, 0x00, 0x00));
            Font black_bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
            Font black_italic = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.ITALIC, new Color(0x00, 0x00, 0x00));
            //Logic for Server Name       
            String context_nm = request.getContextPath();
			String server_nm = request.getServerName();
			String server_port = ""+request.getServerPort();
			url_start = "http://"+server_nm+":"+server_port+context_nm;
			// Phrase phf = new Phrase("ruchi");
			 //HeaderFooter foot = new HeaderFooter(phf,true);
			//System.out.println("this is the pic path  "+url_start+"\\images\\LOGO\\HLPL_Logo.jpg");
			//Logic for  Image hlpl_logo
            Image hlpl_logo = Image.getInstance(url_start+"//images//logo.png");             
            hlpl_logo.setBorder(Rectangle.NO_BORDER);
            hlpl_logo.scaleAbsolute(70,45);
            PdfPCell hlpl_logo_cell = new PdfPCell(hlpl_logo,false);
            hlpl_logo_cell.setBorder(Rectangle.NO_BORDER);        
            PdfPTable HlplLogoTable = new PdfPTable(1);
            HlplLogoTable.setWidthPercentage(40);
            HlplLogoTable.setHorizontalAlignment(Element.ALIGN_LEFT);
            HlplLogoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            HlplLogoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            HlplLogoTable.addCell(hlpl_logo_cell);
            
            String pdf_print="";
            if(flag_sac.equalsIgnoreCase("P")) {
	            if(pdf_flag.equalsIgnoreCase("O")) {
	            	pdf_print="ORIGINAL FOR RECIPIENT";
	            }else if(pdf_flag.equalsIgnoreCase("D")) {
	            	pdf_print="DUPLICATE FOR TRANSPORTER";
	            }else if(pdf_flag.equalsIgnoreCase("T")) {
	            	pdf_print="TRIPLICATE FOR SUPPLIER";
	            }
            }else{
            	 if(pdf_flag.equalsIgnoreCase("O")) {
 	            	pdf_print="ORIGINAL FOR RECIPIENT";
 	            }else if(pdf_flag.equalsIgnoreCase("D")) {
 	            	pdf_print="DUPLICATE FOR SUPPLIER";
 	            }
            }
            SubHeader = pdf_print; //SB20170816
            if(!supplier_nm.equals(""))
            	//SB20170829 SubHeader +="~"+supplier_nm; //SB20170816
            	SubHeader +="~\n"; //SB20170816
            if(inv_type.equals("Z")) //SB20171024
            	{
            	FileHeaderInvType="INVOICE";
            	SubHeader +="~"+"TAX INVOICE "; //SB20170816
            	}
            else if(inv_type.equals("A")) //SB20171024
            	{
            	FileHeaderInvType="RECEIPT";
            	SubHeader +="~"+"RECEIPT VOUCHER "; //SB20170816
            	}
            if(!rule_rmk.equals(""))
            	SubHeader +="~"+rule_rmk; //SB20170816
            PdfPTable BillingFieldsInfoTable0 = new PdfPTable(1);
            BillingFieldsInfoTable0.setWidthPercentage(90);
            BillingFieldsInfoTable0.setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsInfoTable0.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            BillingFieldsInfoTable0.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsInfoTable0.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsInfoTable0.addCell(new Phrase(new Chunk(pdf_print,black_bold)));
            
            PdfPTable BillingFieldsInfoTable = new PdfPTable(1);
            BillingFieldsInfoTable.setWidthPercentage(90);
            BillingFieldsInfoTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsInfoTable.addCell(new Phrase(new Chunk("TAX INVOICE ",black_bold)));
            
            PdfPTable BillingFieldsInfoTable1 = new PdfPTable(1);
            BillingFieldsInfoTable1.setWidthPercentage(90);
            BillingFieldsInfoTable1.setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsInfoTable1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            BillingFieldsInfoTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsInfoTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsInfoTable1.addCell(new Phrase(new Chunk(rule_rmk,small_black_normal)));
            
            PdfPTable BillingFieldsInfoTable3 = new PdfPTable(1);
            BillingFieldsInfoTable3.setWidthPercentage(90);
            BillingFieldsInfoTable3.setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsInfoTable3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            BillingFieldsInfoTable3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsInfoTable3.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsInfoTable3.addCell(new Phrase(new Chunk(supplier_nm,black_bold)));

            Paragraph p1=new Paragraph();
            p1.add(new Chunk("Registered Office :\n",small_black_normal1));
            p1.add(new Chunk(supplier_nm+"\n",small_black_bold1));
            p1.add(new Chunk(Supp_addr_nm_R+"\n"+Supp_addr_city_R+" - "+pin_R+"\n\nState : "+state+"\nState Code : "+Supp_stat_CD+"\nGSTIN : "+contact_Suppl_gstin_no+"\nPAN : "+contact_Suppl_PAN_NO+"\nPlace of Supply : "+state_nm1,small_black_normal1));
            SubHeader2="Registered Office :\n";
            //SB20170830  SubHeader2 +=supplier_nm+"\n"+Supp_addr_nm_R+"\n"+Supp_addr_city_R+" - "+pin_R+"\n\nState : "+state+"\nState Code : "+Supp_stat_CD+"\nGSTIN : "+contact_Suppl_gstin_no+"\nPAN : "+contact_Suppl_PAN_NO+"\nSAC : "+Vsac_cd.elementAt(0)+"\nDescription of Service : "+Vdesc.elementAt(0)+"\nPlace of Supply : "+state_nm1;
                 SubHeader2 +=supplier_nm+"\n"+Supp_addr_nm_R+"\n"+Supp_addr_city_R+" - "+pin_R+"\n\nState : "+state+"\nState Code : "+Supp_stat_CD+"\nGSTIN : "+contact_Suppl_gstin_no+"\nPAN : "+contact_Suppl_PAN_NO+"\nPlace of Supply : "+state_nm1;
              ////   SacHsn +="\nSAC : 998313\nDescription of Service : Information Technology(IT) and Support Service";
            
            PdfPTable BillingFieldsInfoTable4 = new PdfPTable(1);
            BillingFieldsInfoTable4.setWidthPercentage(100);
            BillingFieldsInfoTable4.setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsInfoTable4.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            BillingFieldsInfoTable4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsInfoTable4.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsInfoTable4.addCell(p1);
            
            PdfPTable BillingFieldsInfoTable41 = new PdfPTable(1);
            BillingFieldsInfoTable41.setWidthPercentage(100);
            BillingFieldsInfoTable41.setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsInfoTable41.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            BillingFieldsInfoTable41.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsInfoTable41.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsInfoTable41.addCell(new Phrase(new Chunk("",small_black_normal)));
            String ab_1="";
            String Gap="             ";
            Paragraph p2=new Paragraph();
            if(!TemplateType.equals("2")) //SB20180607
            {
	            p2.add(new Chunk("To :\n",small_black_normal1));
	            p2.add(new Chunk(cust_nm+"\n",small_black_bold1));
	           
	            SubHeader3=Gap+"To :\n";
	            SubHeader3 +=Gap+cust_nm+"\n";
	            if(!cust_pin.equalsIgnoreCase("")) {
	            	 ab_1+=cust_addr+"\n"+cust_city+" - "+cust_pin+"\n\nState : "+state_nm1+"\nState Code : "+cust_state_cd1+"\n";
	            }else{
	            	ab_1+=cust_addr+"\n"+cust_city+"\n\nState : "+state_nm1+"\nState Code : "+cust_state_cd1+"\n";
	            }
	            SubHeader3 +=Gap+cust_addr+"\n";
	            SubHeader3 +=Gap+cust_city+"\n";
	            SubHeader3 +=Gap+"PIN - "+cust_pin+"\n\n";
	            SubHeader3 +=Gap+"State : "+state_nm1+"\n";
	            SubHeader3 +=Gap+"State Code : "+cust_state_cd1+"\n";
	
	            
	            if(!Cust_gstin_no11.equalsIgnoreCase("")) {
	            	ab_1+="GSTIN : "+Cust_gstin_no11+"\n";
	            }else{
	            	ab_1+="\n";
	            }
	            SubHeader3 +=Gap+"GSTIN : "+Cust_gstin_no11+"\n";
	            if(!sCust_pan_no.equalsIgnoreCase("")) {
	            	ab_1+="PAN : "+sCust_pan_no;
	            }
	            p2.add(new Chunk(ab_1,small_black_normal1));
	            SubHeader3 +=Gap+"PAN : "+sCust_pan_no;
            }
            
            PdfPTable BillingFieldsInfoTable21 = new PdfPTable(1);
            BillingFieldsInfoTable21.setWidthPercentage(100);
            BillingFieldsInfoTable21.setHorizontalAlignment(Element.ALIGN_RIGHT);
            BillingFieldsInfoTable21.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            BillingFieldsInfoTable21.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsInfoTable21.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
   
            	BillingFieldsInfoTable21.addCell(p2);
          
            
            	PdfPTable BillingFieldsInfoTable42 = new PdfPTable(3);
                BillingFieldsInfoTable42.setWidthPercentage(100);
                BillingFieldsInfoTable42.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                BillingFieldsInfoTable42.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                BillingFieldsInfoTable42.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                BillingFieldsInfoTable42.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
            	BillingFieldsInfoTable42.addCell(BillingFieldsInfoTable4);
            	
            	BillingFieldsInfoTable42.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                BillingFieldsInfoTable42.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                BillingFieldsInfoTable42.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
            	BillingFieldsInfoTable42.addCell(BillingFieldsInfoTable41);
            	
		    	  BillingFieldsInfoTable42.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		          BillingFieldsInfoTable42.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		          BillingFieldsInfoTable42.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
	          	BillingFieldsInfoTable42.addCell(BillingFieldsInfoTable21);

			float[] BillingFieldsDetailsWidths_3 = {0.05f,0.05f};
			PdfPTable BillingFieldsDetailsTable_3 = new PdfPTable(BillingFieldsDetailsWidths_3);
			BillingFieldsDetailsTable_3.setWidthPercentage(40);
			BillingFieldsDetailsTable_3.setHorizontalAlignment(Element.ALIGN_RIGHT);
			
			BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk("Order No.",small_black_bold1)));
            
            BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(sale_no,small_black_bold1))); 
			
            
            BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk("Order Date",small_black_bold1)));
            
            BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(gate_no,small_black_bold1)));  
            
            
            
            
            float[] BillingFieldsDetailsWidths_30 = {0.05f,0.05f};
			PdfPTable BillingFieldsDetailsTable_30 = new PdfPTable(BillingFieldsDetailsWidths_30);
			BillingFieldsDetailsTable_30.setWidthPercentage(40);
			BillingFieldsDetailsTable_30.setHorizontalAlignment(Element.ALIGN_RIGHT);
			 BillingFieldsDetailsTable_30.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_30.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsDetailsTable_30.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            if(inv_type.equals("Z")) //SB20171025
            	BillingFieldsDetailsTable_30.addCell(new Phrase(new Chunk("Invoice No.",small_black_bold1)));
            else if(inv_type.equals("A")) //SB20171025
            	BillingFieldsDetailsTable_30.addCell(new Phrase(new Chunk("Receipt Voucher No.",small_black_bold1)));
            
            BillingFieldsDetailsTable_30.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_30.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_30.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_30.addCell(new Phrase(new Chunk(seq_no,small_black_bold1)));  
			
			
			BillingFieldsDetailsTable_30.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_30.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsDetailsTable_30.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            if(inv_type.equals("Z")) //SB20171025
            	BillingFieldsDetailsTable_30.addCell(new Phrase(new Chunk("Invoice Date",small_black_bold1)));	
            else if(inv_type.equals("A")) //SB20171025
            	BillingFieldsDetailsTable_30.addCell(new Phrase(new Chunk("Receipt Voucher Date",small_black_bold1)));	
            
            BillingFieldsDetailsTable_30.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_30.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_30.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_30.addCell(new Phrase(new Chunk(Sinv_dt,small_black_bold1))); 
            
            SubjectLeft +="Order No.  : "+sale_no+"\nOrder Date: "+gate_no;
            if(inv_type.equals("Z")) //SB20171025
            	SubjectRight +="Invoice No.  :  "+seq_no+"\nInvoice Date:               "+Sinv_dt;
            else if(inv_type.equals("A")) //SB20171025
            	SubjectRight +="Receipt Voucher No.  :  "+seq_no+"\nReceipt Voucher Date:               "+Sinv_dt;
            
            
            
            
            float[] BillingFieldsDetailsWidths_420 = {0.30f,0.17f,0.30f};
			PdfPTable BillingFieldsDetailsTable_420 = new PdfPTable(BillingFieldsDetailsWidths_420);
			BillingFieldsDetailsTable_420.setWidthPercentage(100);
			//BillingFieldsDetailsTable_420.setB;
			BillingFieldsDetailsTable_420.setHorizontalAlignment(Element.ALIGN_CENTER);
            
			BillingFieldsDetailsTable_420.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			BillingFieldsDetailsTable_420.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			BillingFieldsDetailsTable_420.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
			BillingFieldsDetailsTable_420.addCell(BillingFieldsDetailsTable_3);
        	
			BillingFieldsDetailsTable_420.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			BillingFieldsDetailsTable_420.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			BillingFieldsDetailsTable_420.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
			BillingFieldsDetailsTable_420.addCell(new Phrase(new Chunk("",small_black_bold)));
        	
        	
        	
			BillingFieldsDetailsTable_420.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			BillingFieldsDetailsTable_420.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			BillingFieldsDetailsTable_420.getDefaultCell().setVerticalAlignment(Element.ALIGN_RIGHT);
			BillingFieldsDetailsTable_420.addCell(BillingFieldsDetailsTable_30);
            
            
            

           
            
            float[] BillingFieldsDetailsWidths_5 = {0.10f,0.10f,0.30f,0.10f,0.10f,0.10f,0.10f};
			PdfPTable BillingFieldsDetailsTable_5 = new PdfPTable(BillingFieldsDetailsWidths_5);
			BillingFieldsDetailsTable_5.setWidthPercentage(100);
			BillingFieldsDetailsTable_5.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			  BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//            BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("Sr No.",small_black_bold)));
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//            BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
            if(flag_sac.equalsIgnoreCase("P")) {
            	BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("HSN Code",small_black_bold)));
            }else{
            	BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("SAC Code",small_black_bold)));
            }
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//            BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("Full Particulars",small_black_bold)));
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//            BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("UOM",small_black_bold)));
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("Quantity",small_black_bold)));
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//            BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("Rate Per Qty",small_black_bold)));
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//            BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("Amount (Rs)",small_black_bold)));
            int sr_no=0;
            //SB20180303 
            for(int k=0;k<Vitm.size();k++) { 
           //SB20190217 for(int k=0;k<1;k++) { 
        	   sr_no=k+1;
        	   BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
               BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
               BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//               BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
               BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk(sr_no+"",small_black_normal1)));
               
               BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
               BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
               BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//               BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
               BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk(Vvsac_cd.elementAt(k)+"",small_black_normal1)));
               
               BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
               BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
               BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//               BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
               BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk(Vitm.elementAt(k)+"",small_black_normal1)));
               
               BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
               BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
               BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//               BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
               BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk(Vuam_no.elementAt(k)+"",small_black_normal1)));
               
               BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
               BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
               BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
               BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk(VCqty.elementAt(k)+"",small_black_normal1)));
               
               BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
               BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
               BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//               BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
               BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk(crt.elementAt(k)+"",small_black_normal1)));
               
               BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
               BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
               BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//               BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
               BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk(camt.elementAt(k)+"",small_black_normal1)));
               /*SB20171109 if(flag_sac.equalsIgnoreCase("P")) {
            	   Body +=sr_no+"~"+Vvsac_cd.elementAt(k)+"~"+Vitm.elementAt(k)+"~"+Vuam_no.elementAt(k)+"~"+VCqty.elementAt(k)+"~"+crt.elementAt(k)+"~"+camt.elementAt(k)+"\n";
               }
               else if(flag_sac.equalsIgnoreCase("S"))
               {
            	   Body +=sr_no+"~"+Vvsac_cd.elementAt(k)+"~"+Vitm.elementAt(k)+"~"+camt.elementAt(k)+"\n";  
               }
               */
            //   for(int i=0;i<20;i++) { 
     //SB20180303       for(int i=0;i<BodyDataTotalRowCount;i++) { 
               Body +=sr_no+"~"+Vvsac_cd.elementAt(k)+"~"+Vitm.elementAt(k)+"~"+Vuam_no.elementAt(k)+"~"+VCqty.elementAt(k)+"~"+crt.elementAt(k)+"~"+camt.elementAt(k)+"\n";
//SB20171205: To repeat line for Testing            Body +=(i+1)+"~"+Vvsac_cd.elementAt(k)+"~"+Vitm.elementAt(k)+"~"+Vuam_no.elementAt(k)+"~"+VCqty.elementAt(k)+"~"+crt.elementAt(k)+"~"+camt.elementAt(k)+"\n";
          ///     }
               
              
       /*SB20180303        
            System.out.println("SAC-HSN: "+Vvsac_cd);
               if(Vvsac_cd.elementAt(k).equals("998313"))
            	   SacHsn +="\nSAC : 998313\nDescription of Service : Information Technology(IT) and Support Services";
               else if(Vvsac_cd.elementAt(k).equals("998314"))
            	   SacHsn +="\nSAC : 998314\nDescription of Service : Information Technology(IT) Design and Development Services";
               else 
            	   SacHsn +="\nSAC/HSN : "+Vvsac_cd.elementAt(k)+"\n";
            	   */
          }
           ////////////////SB20180303////////////////////////////
           String PrevSacHsnCode="";
      //     System.out.println("Vvsac_cd: "+Vvsac_cd);
           for(int k=0;k<Vvsac_cd.size();k++) 
           { 
         	  if(!Vvsac_cd.elementAt(k).equals(PrevSacHsnCode)) 
         	  {
         		  String queryStringdt1="SELECT  description from GST_SERVICE_MST where sac_code='"+Vvsac_cd.elementAt(k)+"' and supplier_cd='"+supplier_cd+"' "; 
   				System.out.println("GST-INV:Type=Z:QRY-005: "+queryStringdt1);
   				rset=stmt.executeQuery(queryStringdt1);
   				if(rset.next())
   				{
   					 sac_desc=rset.getString(1)==null?"":rset.getString(1);
   					 SacHsn +="\nSAC : "+Vvsac_cd.elementAt(k)+"\nDescription of Service : "+sac_desc;
   				}
   				 else  if(!Vvsac_cd.elementAt(k).equals("")) //SB20180505
   	            	   SacHsn +="\nSAC/HSN : "+Vvsac_cd.elementAt(k)+"\nDescription of Service : ";
         	  }
         	  PrevSacHsnCode=""+Vvsac_cd.elementAt(k);
           }
           //////////////////////////////////////////////////////
           BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
           BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
           BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//           BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
           BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_normal)));
           
           BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
           BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
           BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//           BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
           BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_normal)));
           
           BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
           BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
           BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//           BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
           BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("Gross Amount",small_black_bold1)));
           
           BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
           BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
           BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//           BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
           BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_normal)));
           
           BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
           BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
           BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
           BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_normal)));
           
           BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
           BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
           BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//           BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
           BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_normal)));
           
           BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
           BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
           BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//           BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
           BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk(Sgross_amt_inr_chk,small_black_bold1)));
          /*SB20171109 if(flag_sac.equalsIgnoreCase("P")) {
        	   Body +=""+"~"+""+"~"+"Gross Amount"+"~"+""+"~"+""+"~"+""+"~"+Sgross_amt_inr_chk+"\n";
           }
           else if(flag_sac.equalsIgnoreCase("S"))
           {
        	   Body +=""+"~"+""+"~"+"Gross Amount"+"~"+Sgross_amt_inr_chk+"\n";  
           }
           */
           sr_no++;
           Body +=sr_no+"~"+""+"~"+"Gross Amount"+"~"+""+"~"+""+"~"+""+"~"+Sgross_amt_inr_chk+"\n";
            for(int k=0;k<Tax_nm.size();k++) {
            	
            	String tt=taxstr.elementAt(k)+"";
            	String tt1=nf4.format(Double.parseDouble(tt));
//            	String tt3=Ttax_amt_inr.elementAt(k)+"";
//            	String tt2=nf3.format(Double.parseDouble(tt3));
            	  BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
	               BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	               BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//	               BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
	               BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_normal1)));
	               
	               BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
	               BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	               BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//	               BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
	               BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_normal1)));
	               
	            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
	            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk(Tax_nm.elementAt(k)+" @ "+tt1+"%",small_black_normal1)));
	            
	            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
	               BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	               BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//	               BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
	               BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_bold)));
	            
	            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
	            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_normal)));
	            
	            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
	            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
	            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_normal)));
	            
	            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
	               BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	               BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
//	               BillingFieldsDetailsTable_5.getDefaultCell().setColspan(2);
	               BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk(Ttax_amt_inr_chk.elementAt(k)+"",small_black_normal1)));
/*SB20171109
		               if(flag_sac.equalsIgnoreCase("P")) {
		            	   Body +=""+"~"+""+"~"+Tax_nm.elementAt(k)+" @"+tt1+"%"+"~"+""+"~"+""+"~"+""+"~"+Ttax_amt_inr_chk.elementAt(k)+"\n";   
		               }
		               else if(flag_sac.equalsIgnoreCase("S"))
		               {
		            	   Body +=""+"~"+""+"~"+Tax_nm.elementAt(k)+" @"+tt1+"%"+"~"+Ttax_amt_inr_chk.elementAt(k)+"\n"; 
		               }
		               */
	               sr_no++;
		         //SB20180313      Body +=sr_no+"~"+""+"~"+Tax_nm.elementAt(k)+" @"+tt1+"%"+"~"+""+"~"+""+"~"+""+"~"+Ttax_amt_inr_chk.elementAt(k)+"\n";   
		               Body +=sr_no+"~"+""+"~"+Tax_nm.elementAt(k)+"~"+""+"~"+""+"~"+""+"~"+Ttax_amt_inr_chk.elementAt(k)+"\n";
            }
            /////////////////////////////
            
//         
//            
//            /////////////////////
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_bold)));
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_bold)));
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("Total GST ",small_black_bold1)));
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_bold)));
            
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_bold)));
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_bold)));
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk(tax_amt_inr_chk,small_black_bold1)));
	        /*SB20171109    if(flag_sac.equalsIgnoreCase("P")) {
	            	Body +=""+"~"+""+"~"+"Total GST"+"~"+""+"~"+""+"~"+""+"~"+tax_amt_inr_chk+"\n";   
	            }
	            else if(flag_sac.equalsIgnoreCase("S"))
	            {
	            	Body +=""+"~"+""+"~"+"Total GST"+"~"+tax_amt_inr_chk+"\n";   
	            }
	            */
            sr_no++;
            Body +=sr_no+"~"+""+"~"+"Total GST"+"~"+""+"~"+""+"~"+""+"~"+tax_amt_inr_chk+"\n";   
//////////////////////////////////////////////////////////////////////////////////////
	            for(int k=1;k<Vitm.size();k++) { 
	            	sr_no++;
	            for(int i=1;i<BodyDataTotalRowCount;i++) { 
	                Body +=sr_no+"~"+Vvsac_cd.elementAt(k)+"~"+Vitm.elementAt(k)+"~"+Vuam_no.elementAt(k)+"~"+VCqty.elementAt(k)+"~"+crt.elementAt(k)+"~"+camt.elementAt(k)+"\n";
//	 SB20171205: To repeat line for Testing            Body +=(i+1)+"~"+Vvsac_cd.elementAt(k)+"~"+Vitm.elementAt(k)+"~"+Vuam_no.elementAt(k)+"~"+VCqty.elementAt(k)+"~"+crt.elementAt(k)+"~"+camt.elementAt(k)+"\n";
	                }
	            }
///////////////////////////////////////////////////////////////////////////////////////
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_bold)));
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_bold)));
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("Total Invoice Amount in INR ",small_black_bold1)));
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_bold)));
            
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_bold)));
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk("",small_black_bold)));
            
            BillingFieldsDetailsTable_5.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsDetailsTable_5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            BillingFieldsDetailsTable_5.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            BillingFieldsDetailsTable_5.addCell(new Phrase(new Chunk(snet_amt_inr_chk,small_black_bold1)));
            if(inv_type.equals("Z")) //SB20171025
            {
	          /*SB20171109  if(flag_sac.equalsIgnoreCase("P")) {
	            	Body +=""+"~"+""+"~"+"Total Invoice Amount in INR"+"~"+""+"~"+""+"~"+""+"~"+snet_amt_inr_chk+"\n"; 
	            }
	            else if(flag_sac.equalsIgnoreCase("S"))
	            {
	            	 Body +=""+"~"+""+"~"+"Total Invoice Amount in INR"+"~"+snet_amt_inr_chk+"\n"; 
	            }
	            */
            	if(BodyDataTotalRowCount==1)
            		Body +=""+"~"+""+"~"+"Total Invoice Amount in INR"+"~"+""+"~"+""+"~"+""+"~"+snet_amt_inr_chk+"\n"; 
            	else
            		Body +=""+"~"+""+"~"+"Net Amount Payable in INR"+"~"+""+"~"+""+"~"+""+"~"+snet_amt_inr_chk+"\n"; 
            }
            else  if(inv_type.equals("A")) //SB20171025
            {
            /*SB20171109	if(flag_sac.equalsIgnoreCase("P")) {
	            	Body +=""+"~"+""+"~"+"Net Amount Payable in INR"+"~"+""+"~"+""+"~"+""+"~"+snet_amt_inr_chk+"\n"; 
	            }
	            else if(flag_sac.equalsIgnoreCase("S"))
	            {
	            	 Body +=""+"~"+""+"~"+"Net Amount Payable in INR"+"~"+snet_amt_inr_chk+"\n"; 
	            }
	            */
            	Body +=""+"~"+""+"~"+"Net Amount Payable in INR"+"~"+""+"~"+""+"~"+""+"~"+snet_amt_inr_chk+"\n"; 
            }
            Paragraph ab=new Paragraph();
            ab.add(new Chunk("Amount in Words  | ",small_black_normal1));
            ab.add(new Chunk("Rupees "+result+" Only",small_black_bold1));
            
            
            PdfPTable BillingFieldsInfoTable7 = new PdfPTable(1);
            BillingFieldsInfoTable7.setWidthPercentage(100);
            BillingFieldsInfoTable7.setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsInfoTable7.getDefaultCell().setBorder(Rectangle.BOX);
            BillingFieldsInfoTable7.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsInfoTable7.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
            BillingFieldsInfoTable7.addCell(ab);
            
            
            PdfPTable BillingFieldsInfoTable8 = new PdfPTable(1);
            BillingFieldsInfoTable8.setWidthPercentage(90);
            BillingFieldsInfoTable8.setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsInfoTable8.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            BillingFieldsInfoTable8.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsInfoTable8.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
            BillingFieldsInfoTable8.addCell(new Phrase(new Chunk("For "+supplier_nm,small_black_bold)));
            
            PdfPTable BillingFieldsInfoTable9 = new PdfPTable(1);
            BillingFieldsInfoTable9.setWidthPercentage(90);
            BillingFieldsInfoTable9.setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsInfoTable9.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            BillingFieldsInfoTable9.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsInfoTable9.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
            BillingFieldsInfoTable9.addCell(new Phrase(new Chunk("Authorised Signatory",small_black_bold)));
            
            PdfPTable BillingFieldsInfoTable10 = new PdfPTable(1);
            BillingFieldsInfoTable10.setWidthPercentage(90);
            BillingFieldsInfoTable10.setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsInfoTable10.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            BillingFieldsInfoTable10.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            BillingFieldsInfoTable10.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
            BillingFieldsInfoTable10.addCell(new Phrase(new Chunk("Notes: \n"+remark+"\n\n"+remark1,small_black_normal)));
        
   //SB20180607         String BiplPayRemark=Gap+"Payment is to be made in favour of Baroda Informatics Pvt. Ltd. by Cheque or by NEFT/RTGS\n";
   //SB20180607         BiplPayRemark +=Gap+"Bank Details: Bank of India, Vadodara, A/C No.:250020110000085, IFSC: BKID0002500";
            String BiplPayRemark=Gap+PaymentRemarkLine1+"\n"; //SB20180607
            BiplPayRemark +=Gap+PaymentRemarkLine2+"";
            PaymentRemark +="Remarks: \n"+BiplPayRemark+"\n";
            
            Signature +="For "+supplier_nm+"\n\n\n\n\n";
            Signature +="Authorised Signatory";
            
        
            
         
				
            
            //--------------------------------------------------------------------------            
			//Adding All Cells To PDF Document - One By One ...
//			document.add(HlplLogoTable);
            document.add(new Paragraph("   "));
            document.add(new Paragraph("   "));
            document.add(new Paragraph("   "));
            document.add(new Paragraph("   "));
            document.add(new Paragraph("   "));
            document.add(new Paragraph("   "));
            
            document.add(BillingFieldsInfoTable0);
            document.add(BillingFieldsInfoTable3);
            document.add(BillingFieldsInfoTable);
			document.add(BillingFieldsInfoTable1);
			document.add(new Paragraph("   "));
			
			//document.add(BillingFieldsInfoTable4);
			document.add(BillingFieldsInfoTable42);
			document.add(new Paragraph("   "));
			
			///document.add(BillingFieldsDetailsTable_3);
			document.add(BillingFieldsDetailsTable_420);
			document.add(new Paragraph("   "));
			document.add(BillingFieldsDetailsTable_5);
			document.add(new Paragraph("   "));
			document.add(BillingFieldsInfoTable7);
			document.add(new Paragraph("   "));
			document.add(BillingFieldsInfoTable8);
			document.add(new Paragraph("   "));
			document.add(new Paragraph("   "));
			document.add(BillingFieldsInfoTable9);
			document.add(new Paragraph("   "));

		//SB	Update_Table_Entry_PDF();
                       
           // document.add(GenerationInfoTable);
        }
		catch(DocumentException de)
		{
			System.err.println("DocumentException in printPdfFileForFINAL_CUTOM_DUTY() Method :\n"+de.getMessage());
			de.printStackTrace();
		}
		catch(IOException ioe)
		{
			System.err.println("IOException in printPdfFileForFINAL_CUSTOM_DUTY() Method :\n"+ioe.getMessage());
			ioe.printStackTrace();
		}
		finally
		{
			document.close();
		}
	}

	////////////////////////////SB20170811: /////////////////////
	public void Supl_Inv_MST() //SB20171108
	{
		try
		{
			queryString = "Select distinct(SUPPLIER_CD), SUPPLIER_NAME, SUPPLIER_ABBR "+
				" from FMS7_SUPPLIER_MST  " +
				" ORDER BY SUPPLIER_CD ";
				System.out.println("SUPPL_CD: "+queryString);
				rset=stmt.executeQuery(queryString);
				while(rset.next())
				{
					VSUPP_CD.add(rset.getString(1)==null?"":rset.getString(1));
					VSUPP_NM.add(rset.getString(2)==null?"":rset.getString(2));
					VSUPP_ABR.add(rset.getString(3)==null?"":rset.getString(3));
				}
				String queryString1="SELECT TO_CHAR(SYSDATE,'YYYY') FROM DUAL";
				System.out.println("GST-INV:FirstInvYrinSystem: "+queryString1);
				rset=stmt.executeQuery(queryString1);
				if(rset.next())
				{
					Min_FY=rset.getString(1);
				}
				queryString = "SELECT MIN(TO_CHAR(INVOICE_DT,'YYYY')) FROM DLNG_AC_INVOICE_MST ";
				System.out.println("FY: "+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					Min_FY=rset.getString(1)==null?Min_FY:rset.getString(1);
				}	
				System.out.println("Min_FY: "+Min_FY);
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void INVOICE_DTL_Z() //SB20171108
	{
		try
		{
			
			//String supplier_cd="1";
			if(!month.equalsIgnoreCase("") && (!year.equalsIgnoreCase(""))){
			 dt="01/"+month+"/"+year;
			
			String queryString1="select TO_CHAR(LAST_DAY(TO_DATE('"+dt+"','dd/mm/yyyy')),'DD/MM/YYYY') from DUAL";
//			System.out.println("GST-INV:Type=Z:QRY-001: "+queryString1);
			rset=stmt.executeQuery(queryString1);
			if(rset.next())
			{
				end_dt=rset.getString(1);
			}
			String queryString2="SELECT STATE_CODE,STATE_NM FROM STATE_MST ORDER BY STATE_NM ";
//			System.out.println("GST-INV:Type=Z:QRY-002: "+queryString2);
			rset=stmt.executeQuery(queryString2);
			while(rset.next())
			{
				state_cd.add(rset.getString(1)==null?"":rset.getString(1));
				state_nm.add(rset.getString(2)==null?"":rset.getString(2));
			}
			///////////////////////SB20170811: SAC/HSN Code/////////////////
			if(Integer.parseInt(month)<4){
				fin_yr=(Integer.parseInt(year)-1)+"-"+year;
			}else{
				fin_yr=year+"-"+(Integer.parseInt(year)+1);
			}
		//	if(!set_SacHsn.equals(""))
			{
				queryString="SELECT supplier_cd,SAC_CODE,DESCRIPTION, TAX_PERC " +
					" FROM GST_SERVICE_MST WHERE SUPPLIER_CD='"+supp_cd_set+"' AND FLAG IN ('S','P') ORDER BY SAC_CODE ASC";
			}			
//			System.out.println("GST-INV:Type=Z:QRY-002A: "+queryString);
			rset=stmt.executeQuery(queryString);
			while(rset.next())
			{
				Vsac_cd.add(rset.getString(2)==null?"":rset.getString(2));
				Vdesc.add(rset.getString(3)==null?"":rset.getString(3));
				Vsac_hsn_perc.add(rset.getString(4)==null?"0":rset.getString(4));
			}
			int MaxSeqNo=0;
	//SB20180207		queryString = "SELECT NVL(MAX(INV_SEQ_NO),'0') FROM FMS7_INVOICE_MST " +
			queryString = "SELECT NVL(MAX(INV_SEQ_NO),'0') FROM DLNG_AC_INVOICE_MST " +
//SB20171024		  	  "WHERE financial_year='"+fin_yr+"' AND contract_type in ('1','2','Z') and supplier_cd='"+supp_cd_set+"' and flag!='A' ";
			 "WHERE financial_year='"+fin_yr+"' AND contract_type ='"+inv_type+"' and supplier_cd='"+supp_cd_set+"' and flag!='A' ";  //SB20171024
//			System.out.println("------"+queryString);
			rset=stmt.executeQuery(queryString);
			if(rset.next())
			{
				MaxSeqNo =rset.getInt(1)+1;
			}
			else
			{
				MaxSeqNo = 0;
			}
			NewSeqNo=""+MaxSeqNo;
			String temp[]=fin_yr.split("/");
			String temp1=NewSeqNo;
			if(temp1.length()==1) {
				temp1 = "0000"+temp1;
			} else if(temp1.length()==2) {
				temp1 = "000"+temp1;
			} else if(temp1.length()==3) {
				temp1 = "00"+temp1;
			} else if(temp1.length()==4) {
				temp1 = "0"+temp1;
			} else if(temp1.length()==5) {
				temp1 = temp1;
			}			
			NewSeqNo=temp1+"/"+fin_yr.substring(2,5)+fin_yr.substring(7,9);
			
			//////////////////////////////////////////////////////
			
			///	NewSeqNo=""+MaxSeqNo;
//			System.out.println("**************: "+flag_act);
			if(flag_act.equalsIgnoreCase("INSERT")) 
			{
				queryString = "SELECT supplier_name,PAN_NO, gstin_no,SUPPLIER_CD, SUPPLIER_ABBR  " +
								" FROM FMS7_SUPPLIER_MST A "+ 
								" WHERE A.supplier_cd='"+supp_cd_set+"'  AND A.eff_dt=(SELECT MAX(B.eff_dt) " +
								" FROM FMS7_SUPPLIER_MST B WHERE A.supplier_cd=B.supplier_cd  AND B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY'))";
//				System.out.println("GST-INV:Type=Z:QRY-004: "+queryString);
				rset = stmt.executeQuery(queryString);
				if(rset.next())
				{
					supplier_nm=rset.getString(1)==null?"":rset.getString(1);
					contact_Suppl_PAN_NO = rset.getString(2)==null?"":rset.getString(2);	
					contact_Suppl_gstin_no = rset.getString(3)==null?"":rset.getString(3);
					///supp_cd = rset.getString(4)==null?"":rset.getString(4);
					supplier_cd=rset.getString(4)==null?"":rset.getString(4);
					SuppAbr=rset.getString(5)==null?"BIPL":rset.getString(5); //SB20180222
				}
				
				queryString="SELECT ADDR, CITY, PIN, STATE, STATE_CODE " +
						" FROM FMS7_SUPPLIER_ADDRESS_MST A, STATE_MST B " +
						" WHERE SUPPLIER_CD='"+supp_cd_set+"' AND A.STATE=B.STATE_NM AND (A.FLAG='Y' OR  A.FLAG='T')" +
						" AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B "+ 
						" WHERE A.supplier_cd=B.supplier_cd  AND  B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY'))";
//				System.out.println("GST-INV:Type=Z:QRY-004A: "+queryString);
				rset=stmt.executeQuery(queryString);
				while(rset.next())
				{
					supp_addr=rset.getString(1)==null?"":rset.getString(1);
					supp_city=rset.getString(2)==null?"":rset.getString(2);
					supp_pin=rset.getString(3)==null?"":rset.getString(3);
					supp_state=rset.getString(4)==null?"":rset.getString(4);
					supp_state_cd=rset.getString(5)==null?"":rset.getString(5);
				}	
//				System.out.println("GST-SUPP_State_CD: "+supp_state_cd);System.out.println("GST-CUST_State_CD: "+cust_sts_cd);
					if(!cust_sts_cd.equalsIgnoreCase("") ) 
					{
						if(!supp_state_cd.equals(cust_sts_cd))
						{
							Tax_nm.add("IGST");
						//SB20180215	taxstr.add("18");	
							taxstr.add("0");	
						}
						else if(supp_state_cd.equals(cust_sts_cd))
						{
							Tax_nm.add("CGST");
							Tax_nm.add("SGST");
						/*SB20180215	taxstr.add("9");
							taxstr.add("9");*/
							taxstr.add("0");
							taxstr.add("0");
						}						
					}
					String queryStringdt1="SELECT  description from GST_SERVICE_MST where sac_code='"+set_sac_cd+"' and supplier_cd='"+supplier_cd+"' "; 
//					System.out.println("GST-INV:Type=Z:QRY-005: "+queryStringdt1);
					rset=stmt.executeQuery(queryStringdt1);
					if(rset.next())
					{
						 sac_desc=rset.getString(1)==null?"":rset.getString(1);
					}
					
			///////////////////////////Vendor Dtl ////////////////////////////////////////////////////
					if(!CustomerType.startsWith("B"))
					{
					String queryStringdt11="Select Vendor_cd,vendor_abbr,vendor_name "
							+ "from  FMS8_VENDOR_MST where Vendor_cd='"+Svendor_cd+"' order by vendor_name ";
//					System.out.println("GST-INV:Type=Z:QRY-006: "+queryStringdt11);
					rset=stmt.executeQuery(queryStringdt11);
					if(rset.next())
					{
						SVendor_nm=rset.getString(3)==null?"":rset.getString(3);
						SVendor_abr=rset.getString(2)==null?"*":rset.getString(2); //SB20171104
					}
				
					String queryString331="SELECT addr FROM FMS8_VENDOR_ADDRESS_MST A"
							+ " WHERE A.VENDOR_CD='"+Svendor_cd+"'  AND A.address_type='B' AND " +
					  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS8_VENDOR_ADDRESS_MST B " +
					  "WHERE A.VENDOR_CD=B.VENDOR_CD AND B.address_type='B' AND " +
					  "B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY'))   ";
//					System.out.println("GST-INV:Type=Z:QRY-007: "+queryString331);
					rset=stmt.executeQuery(queryString331);
					if(rset.next()) {
						Vendor_addr1=rset.getString(1)==null?"":rset.getString(1);
					}else{
						String queryString332="SELECT addr FROM FMS8_VENDOR_ADDRESS_MST A"
								+ " WHERE A.VENDOR_CD='"+Svendor_cd+"'  AND A.address_type='C' AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS8_VENDOR_ADDRESS_MST B " +
						  "WHERE A.VENDOR_CD=B.VENDOR_CD AND B.address_type='C' AND " +
						  "B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY'))   ";
//						System.out.println("GST-INV:Type=Z:QRY-007A: "+queryString332);
						rset=stmt.executeQuery(queryString332);
						if(rset.next()) {
							Vendor_addr1=rset.getString(1)==null?"":rset.getString(1);
						}else{
							String queryString333="SELECT addr FROM FMS8_VENDOR_ADDRESS_MST A"
									+ " WHERE A.VENDOR_CD='"+Svendor_cd+"'  AND A.address_type='R' AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS8_VENDOR_ADDRESS_MST B " +
							  "WHERE A.VENDOR_CD=B.VENDOR_CD AND B.address_type='R' AND " +
							  "B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY'))   ";
//							System.out.println("GST-INV:Type=Z:QRY-007B: "+queryString333);
							rset=stmt.executeQuery(queryString333);
							if(rset.next()) {
								Vendor_addr1=rset.getString(1)==null?"":rset.getString(1);
							}
						}
					}
					///////////////Get Voucher Data for Invoice generation //////////////
//					System.out.println(LineItem+" :: "+Integer.parseInt(""+LineItem));
					for (int i=0; i<Integer.parseInt(LineItem); i++)
					{
						Vitm.add("Enter Item description");
						VCrate.add("0");
						VCamt.add("0");
						VCqty.add("1");
						Vvsac_cd.add("0");
						Vuam_no.add("KM");
						
						VCGST_Perc.add("0");	
						VSGST_Perc.add("0");	
						//if(rset1.getDouble(9)==0)
						//	VIGST_Perc.add(nf.format(rset1.getDouble(7)+rset1.getDouble(8)));		
					//	else
							VIGST_Perc.add("0");									
						VIGST_Amt.add("0");
						VCGST_Amt.add("0");
						VSGST_Amt.add("0");
						VHSN_CD.add("0");
						VSAC_CD.add("0");	
						VCESS_Perc.add("0");
						VCESS_Amt.add("0");					
					}
					/////////////////////////////////////////////////////////////////////
				}
			else
			{
				String queryStringdt11="Select CUSTOMER_CD,CUSTOMER_ABBR,CUSTOMER_NAME "
							+ "from  FMS7_CUSTOMER_MST where CUSTOMER_CD='"+Svendor_cd+"' order by CUSTOMER_NAME ";
//				System.out.println("GST-INV:Type=Z:QRY-006: "+queryStringdt11);
					rset=stmt.executeQuery(queryStringdt11);
					if(rset.next())
					{
						SVendor_nm=rset.getString(3)==null?"":rset.getString(3);
						SVendor_abr=rset.getString(2)==null?"*":rset.getString(2); //SB20171104
					}
				
					String queryString331="SELECT addr FROM FMS7_CUSTOMER_ADDRESS_MST A"
							+ " WHERE A.CUSTOMER_CD='"+Svendor_cd+"'  AND A.address_type='B' AND " +
					  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
					  "WHERE A.CUSTOMER_CD=B.CUSTOMER_CD AND B.address_type='B' AND " +
					  "B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY'))   ";
//					System.out.println("GST-INV:Type=Z:QRY-007: "+queryString331);
					rset=stmt.executeQuery(queryString331);
					if(rset.next()) {
						Vendor_addr1=rset.getString(1)==null?"":rset.getString(1);
					}else{
						String queryString332="SELECT addr FROM FMS7_CUSTOMER_ADDRESS_MST A"
								+ " WHERE A.CUSTOMER_CD='"+Svendor_cd+"'  AND A.address_type='C' AND " +
						  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
						  "WHERE A.CUSTOMER_CD=B.CUSTOMER_CD AND B.address_type='C' AND " +
						  "B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY'))   ";
//						System.out.println("GST-INV:Type=Z:QRY-007A: "+queryString332);
						rset=stmt.executeQuery(queryString332);
						if(rset.next()) {
							Vendor_addr1=rset.getString(1)==null?"":rset.getString(1);
						}else{
							String queryString333="SELECT addr FROM FMS7_CUSTOMER_ADDRESS_MST A"
									+ " WHERE A.CUSTOMER_CD='"+Svendor_cd+"'  AND A.address_type='R' AND " +
							  "A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_ADDRESS_MST B " +
							  "WHERE A.CUSTOMER_CD=B.CUSTOMER_CD AND B.address_type='R' AND " +
							  "B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY'))   ";
//							System.out.println("GST-INV:Type=Z:QRY-007B: "+queryString333);
							rset=stmt.executeQuery(queryString333);
							if(rset.next()) {
								Vendor_addr1=rset.getString(1)==null?"":rset.getString(1);
							}
						}
					}
					///////////////Get Voucher Data for Invoice generation //////////////
					String queryString212ab="SELECT RLEVY,RSTALL_FEE, TO_CHAR(RACE_DT,'DD/MM/YYYY') "+ //SB20180301
				//SB20180207	" from fms8_other_invoice_dtl " +
					 " from RIS_BM_TRANS_DTL "+
					" where BM_CD='"+Svendor_cd+"' AND RACE_DT  BETWEEN TO_DATE('"+dt+"','DD/MM/YYYY') AND TO_DATE('"+end_dt+"','DD/MM/YYYY') " +
					" ORDER BY RACE_DT DESC";
//					System.out.println("GST-INV:Insert:QRY-001A: "+queryString212ab);
					rset=stmt.executeQuery(queryString212ab);
					while(rset.next())
					{
						Vitm.add("Race Dt:"+rset.getString(3));
						VCrate.add(rset.getString(1)==null?"":rset.getString(1));
						VCamt.add(rset.getString(2)==null?"":rset.getString(2));
						VCqty.add("1");
						Vvsac_cd.add("");
						Vuam_no.add("KM");
						
						VCGST_Perc.add("0");	
						VSGST_Perc.add("0");	
						//if(rset1.getDouble(9)==0)
						//	VIGST_Perc.add(nf.format(rset1.getDouble(7)+rset1.getDouble(8)));		
					//	else
							VIGST_Perc.add("0");									
						VIGST_Amt.add("0");
						VCGST_Amt.add("0");
						VSGST_Amt.add("0");
						VHSN_CD.add("0");
						VSAC_CD.add("0");	
						VCESS_Perc.add("0");
						VCESS_Amt.add("0");					
					}
					/////////////////////////////////////////////////////////////////////
					///////////////For additional Line Item //////////////
//					System.out.println(LineItem+" :: "+Integer.parseInt(""+LineItem));
					if(Integer.parseInt(""+LineItem)>Vitm.size())
					{
						for (int i=0; i<Integer.parseInt(LineItem)-Vitm.size(); i++)
						{
							Vitm.add("Enter Item description");
							VCrate.add("0");
							VCamt.add("0");
							VCqty.add("1");
							Vvsac_cd.add("0");
							Vuam_no.add("KM");
							
							VCGST_Perc.add("0");	
							VSGST_Perc.add("0");	
							//if(rset1.getDouble(9)==0)
							//	VIGST_Perc.add(nf.format(rset1.getDouble(7)+rset1.getDouble(8)));		
						//	else
								VIGST_Perc.add("0");									
							VIGST_Amt.add("0");
							VCGST_Amt.add("0");
							VSGST_Amt.add("0");
							VHSN_CD.add("0");
							VSAC_CD.add("0");	
							VCESS_Perc.add("0");
							VCESS_Amt.add("0");					
						}
					}
					/////////////////////////////////////////////////////////////////////
				}
			}
			else  //Modify Invoice Dtl
			{
				GST_INVOICE_SEQ_NO=seq_no;
				String queryString="SELECT TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),TO_CHAR(DUE_DT,'DD/MM/YYYY'),"
						+ "NEW_INV_SEQ_NO,GROSS_AMT_INR,NET_AMT_INR,CUSTOMER_CD,TAX_AMT_INR,supplier_cd " +
						" ,CUSTOMER_NM,CUSTOMER_ADDR,CUSTOMER_STATE_CD,CUST_CITY,CUST_PIN_CODE, CUSTOMER_PAN_NO,supplier_state_cd,gate_pass_no,cust_gstin_no,sale_no" +
					//SB20180207	" FROM FMS7_INVOICE_MST "+
						" FROM DLNG_AC_INVOICE_MST "+
						" WHERE INV_SEQ_NO='"+Hlpl_seq_no+"' "+
						" AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"' ";
//				System.out.println("GST-INV:Modify:QRY-008A: "+queryString);
				rset=stmt.executeQuery(queryString);
				while(rset.next())
				{
					Sinv_dt=rset.getString(1)==null?"":rset.getString(1);
					Sdue_dt=rset.getString(2)==null?"":rset.getString(2);
					Snew_inv_sq_no=rset.getString(3)==null?"":rset.getString(3);
					Sgross_amt_inr=rset.getString(4)==null?"":rset.getString(4);
					snet_amt_inr=rset.getString(5)==null?"":rset.getString(5);
					custom_cd=rset.getString(6)==null?"":rset.getString(6);
					tax_amt_inr=rset.getString(7)==null?"":rset.getString(7);
					supp_cd=rset.getString(8)==null?"":rset.getString(8);
				////	supp_cd=rset.getString(9)==null?"":rset.getString(9);
					
					cust_nm=rset.getString(9)==null?"":rset.getString(9);
					cust_addr=rset.getString(10)==null?"":rset.getString(10);
					cust_state_cd1=rset.getString(11)==null?"":rset.getString(11);
					cust_city=rset.getString(12)==null?"":rset.getString(12);
					cust_pin=rset.getString(13)==null?"":rset.getString(13);
					sCust_pan_no=rset.getString(14)==null?"":rset.getString(14);
					Supp_stat_CD=rset.getString(15)==null?"":rset.getString(15);
					gate_no=rset.getString(16)==null?"":rset.getString(16);
					Cust_gstin_no11=rset.getString(17)==null?"":rset.getString(17);
					sale_no=rset.getString(18)==null?"":rset.getString(18);				
				}
				
				if(!Sgross_amt_inr.equalsIgnoreCase("")) {
					Sgross_amt_inr_chk=nf1.format(Math.round(Double.parseDouble(Sgross_amt_inr)));
				}
				if(!tax_amt_inr.equalsIgnoreCase("")) {
					tax_amt_inr_chk=nf1.format(Math.round(Double.parseDouble(tax_amt_inr)));
				}
				if(!snet_amt_inr.equalsIgnoreCase("")) {
					System.out.println("*** snet_amt_inr = "+snet_amt_inr);
					//snet_amt_inr_chk=nf3.format(Math.round(Double.parseDouble(snet_amt_inr))); System.out.println("snet_amt_inr_chk = "+snet_amt_inr_chk);
					snet_amt_inr_chk=nf1.format(Math.round(Double.parseDouble(snet_amt_inr)));
					System.out.println("snet_amt_inr_chk = "+snet_amt_inr_chk);
				}
				////////////////////////////////MODIFY: SUpplier Dtl//////////////////////////////////////////////
				queryString = "SELECT supplier_name,PAN_NO, gstin_no,SUPPLIER_CD, SUPPLIER_ABBR  " +
						" FROM FMS7_SUPPLIER_MST A "+ 
						" WHERE A.supplier_cd='"+supp_cd+"'  AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_MST B "+
						" WHERE A.supplier_cd=B.supplier_cd  AND B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY'))";
//				System.out.println("GST-INV:Modify:QRY-008B: "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					supplier_nm=rset.getString(1)==null?"":rset.getString(1);
					contact_Suppl_PAN_NO = rset.getString(2)==null?"":rset.getString(2);	
					contact_Suppl_gstin_no = rset.getString(3)==null?"":rset.getString(3);
					supplier_cd = rset.getString(4)==null?"":rset.getString(4);
					SuppAbr=rset.getString(5)==null?"BIPL":rset.getString(5); //SB20180222
				}
				queryString="SELECT ADDR, CITY, PIN, STATE, STATE_CODE " +
				" FROM FMS7_SUPPLIER_ADDRESS_MST A, STATE_MST B " +
				" WHERE SUPPLIER_CD='"+supp_cd+"' AND A.STATE=B.STATE_NM AND (A.FLAG='Y' OR  A.FLAG='T')  " +
				" AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B "+ 
				" WHERE A.supplier_cd=B.supplier_cd  AND  B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY'))";
//				System.out.println("GST-INV:Type=Z:QRY-004A: "+queryString);
				rset=stmt.executeQuery(queryString);
				while(rset.next())
				{
					supp_addr=rset.getString(1)==null?"":rset.getString(1);
					supp_city=rset.getString(2)==null?"":rset.getString(2);
					supp_pin=rset.getString(3)==null?"":rset.getString(3);
					supp_state=rset.getString(4)==null?"":rset.getString(4);
					supp_state_cd=rset.getString(5)==null?"":rset.getString(5);
				}	
				
				String queryString31="SELECT Addr,city,pin " +
							" FROM FMS7_SUPPLIER_ADDRESS_MST A WHERE A.SUPPLIER_CD='"+supp_cd+"'  AND A.address_type='B' AND " +
							" A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B WHERE A.supplier_cd=B.supplier_cd AND B.address_type='B' AND " +
							" B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY'))   ";
//							System.out.println("GST-INV:Modify:QRY-008C: "+queryString31);			
							rset=stmt.executeQuery(queryString31);
							if(rset.next())
							{
								Supp_addr_nm=rset.getString(1)==null?"":rset.getString(1);
								Supp_addr_city=rset.getString(2)==null?"":rset.getString(2);
								pin=rset.getString(3)==null?"":rset.getString(3);
							}
							else
							{
							String queryString21="SELECT addr,city,pin " +
								" FROM FMS7_SUPPLIER_ADDRESS_MST A WHERE SUPPLIER_CD='"+supp_cd+"'  AND A.address_type='C' AND " +
								" A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B " +
								" WHERE A.supplier_cd=B.supplier_cd AND B.address_type='C' AND " +
								" B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY')) ";
								rset=stmt.executeQuery(queryString21);
								if(rset.next())
								{
									Supp_addr_nm=rset.getString(1)==null?"":rset.getString(1);
									Supp_addr_city=rset.getString(2)==null?"":rset.getString(2);
									pin=rset.getString(3)==null?"":rset.getString(3);
									
								}
								else
								{
								String queryString22="SELECT addr,city,pin " +
									" FROM FMS7_SUPPLIER_ADDRESS_MST A WHERE SUPPLIER_CD='"+supp_cd+"'  AND A.address_type='R' AND " +
									" A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B WHERE A.supplier_cd=B.supplier_cd AND B.address_type='R' AND " +
									" B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY')) ";
									rset=stmt.executeQuery(queryString22);
									if(rset.next())
									{
										Supp_addr_nm=rset.getString(1)==null?"":rset.getString(1);
										Supp_addr_city=rset.getString(2)==null?"":rset.getString(2);
										pin=rset.getString(3)==null?"":rset.getString(3);
									}
								}
							}
				/////////////////MODIFY: Customer Address///////////////////////////////			
				/*SB	String queryString211="SELECT CUSTOMER_NM,CUSTOMER_ADDR,CUSTOMER_STATE_CD,CUST_CITY,"+
							 " CUST_PIN_CODE,TAX_CD,sac_description,sac_code,CUSTOMER_PAN_NO,supplier_state_cd,gate_pass_no,cust_gstin_no,sale_no "+
						//SB20180207	 " from fms8_other_invoice_dtl where "+
							 " from DLNG_SERVICE_INVOICE_DTL where "+
							 " INV_SEQ_NO='"+Hlpl_seq_no+"' AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"'" +
							 "AND LINE_NO='1' " + //SB20180319
							 " ORDER BY LINE_NO DESC"; //SB20180213
					System.out.println("GST-INV:Modify:QRY-008D: "+queryString211);			
					rset=stmt.executeQuery(queryString211);
					if(rset.next())
					{
						cust_nm=rset.getString(1)==null?"":rset.getString(1);
						cust_addr=rset.getString(2)==null?"":rset.getString(2);
						cust_state_cd1=rset.getString(3)==null?"":rset.getString(3);
						cust_city=rset.getString(4)==null?"":rset.getString(4);
						cust_pin=rset.getString(5)==null?"":rset.getString(5);
						tax_cd=rset.getString(6)==null?"":rset.getString(6);
						//item_desc=rset.getString(7)==null?"":rset.getString(7);
//						sac_descr=rset.getString(7)==null?"":rset.getString(7);
//						sac_cd=rset.getString(8)==null?"":rset.getString(8);
						sCust_pan_no=rset.getString(9)==null?"":rset.getString(9);
						
						Supp_stat_CD=rset.getString(10)==null?"":rset.getString(10);
						gate_no=rset.getString(11)==null?"":rset.getString(11);
						Cust_gstin_no11=rset.getString(12)==null?"":rset.getString(12);
						sale_no=rset.getString(13)==null?"":rset.getString(13);
						//flag_sac=rset.getString(13)==null?"":rset.getString(13);						
					}
				*/
					String queryString212="SELECT STATE_NM FROM STATE_MST WHERE STATE_CoDe='"+cust_state_cd1+"' ";
					rset=stmt.executeQuery(queryString212);
					if(rset.next())
					{
						state_nm1=rset.getString(1)==null?"":rset.getString(1);
					}
					
					String queryString212a="SELECT STATE_NM FROM STATE_MST WHERE STATE_CoDe='"+Supp_stat_CD+"' ";
					rset=stmt.executeQuery(queryString212a);
					if(rset.next())
					{
						state=rset.getString(1)==null?"":rset.getString(1);
					}
					//System.out.println("sdsdsd---"+state);
					String queryString212ab1="SELECT Distinct (flag_sac), TAX_CD "+
						//SB20180207	" from fms8_other_invoice_dtl " +
							 " from DLNG_AC_INVOICE_DTL "+
							" where INV_SEQ_NO='"+Hlpl_seq_no+"' AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"'";
//					System.out.println("GST-INV:Modify:QRY-008E: "+queryString212ab1);			
					rset=stmt.executeQuery(queryString212ab1);
					if(rset.next()){
						flag_sac=rset.getString(1)==null?"":rset.getString(1);
						tax_cd=rset.getString(2)==null?"":rset.getString(2);
					}
				
					if(flag_sac.equalsIgnoreCase("P")) 
					{
						String queryString212ab="SELECT ITEM_description,rate,cargo_amount,quantity,hsn_code,UAM_NO " +
								", RATE_CGST, RATE_SGST, RATE_IGST, IGST_AMT, CGST_AMT, SGST_AMT, HSN_CODE, SAC_CODE, CESS_RATE, CESS_AMOUNT "+ //SB20180301
							//SB20180207	" from fms8_other_invoice_dtl " +
								 " from DLNG_AC_INVOICE_DTL "+
								" where INV_SEQ_NO='"+Hlpl_seq_no+"' AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"'" +
								" ORDER BY LINE_NO ";
//						System.out.println("GST-INV:Modify:QRY-008F: "+queryString212ab);
						rset=stmt.executeQuery(queryString212ab);
						while(rset.next())
						{
							Vitm.add(rset.getString(1)==null?"":rset.getString(1));
							VCrate.add(rset.getString(2)==null?"":rset.getString(2));
							VCamt.add(rset.getString(3)==null?"":rset.getString(3));
							VCqty.add(rset.getString(4)==null?"":rset.getString(4));
							Vvsac_cd.add(rset.getString(5)==null?"":rset.getString(5));
							Vuam_no.add(rset.getString(6)==null?"":rset.getString(6));
							
							VCGST_Perc.add(nf.format(rset.getDouble(7)));	
							VSGST_Perc.add(nf.format(rset.getDouble(8)));	
							//if(rset1.getDouble(9)==0)
							//	VIGST_Perc.add(nf.format(rset1.getDouble(7)+rset1.getDouble(8)));		
						//	else
								VIGST_Perc.add(nf.format(rset.getDouble(9)));									
							VIGST_Amt.add(nf.format(rset.getDouble(10)));
							VCGST_Amt.add(nf.format(rset.getDouble(11)));
							VSGST_Amt.add(nf.format(rset.getDouble(12)));
							VHSN_CD.add(rset.getString(13)==null?"":rset.getString(13));
							VSAC_CD.add(rset.getString(14)==null?"":rset.getString(14));	
							VCESS_Perc.add(nf.format(rset.getDouble(15)));
							VCESS_Amt.add(nf.format(rset.getDouble(16)));					
						}
					}
					else
					{						
						String queryString212ab="SELECT ITEM_description,rate,cargo_amount,quantity,sac_code,UAM_NO "+
						", RATE_CGST, RATE_SGST, RATE_IGST, IGST_AMT, CGST_AMT, SGST_AMT, HSN_CODE, SAC_CODE, CESS_RATE, CESS_AMOUNT "+ //SB20180301
							//SB20180207	" from fms8_other_invoice_dtl " +
								 " from DLNG_AC_INVOICE_DTL "+
								" where INV_SEQ_NO='"+Hlpl_seq_no+"' AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"'" +
								" ORDER BY LINE_NO "; //SB20180213
//						System.out.println("GST-INV:Modify:QRY-008G: "+queryString212ab);
						rset=stmt.executeQuery(queryString212ab);
						while(rset.next())
						{
							Vitm.add(rset.getString(1)==null?"":rset.getString(1));
							VCrate.add(rset.getString(2)==null?"":rset.getString(2));
							VCamt.add(rset.getString(3)==null?"":rset.getString(3));
							VCqty.add(rset.getString(4)==null?"":rset.getString(4));
							Vvsac_cd.add(rset.getString(5)==null?"":rset.getString(5));
							Vuam_no.add(rset.getString(6)==null?"":rset.getString(6));	
							
							VCGST_Perc.add(nf.format(rset.getDouble(7)));	
							VSGST_Perc.add(nf.format(rset.getDouble(8)));	
							//if(rset1.getDouble(9)==0)
							//	VIGST_Perc.add(nf.format(rset1.getDouble(7)+rset1.getDouble(8)));		
						//	else
								VIGST_Perc.add(nf.format(rset.getDouble(9)));									
							VIGST_Amt.add(nf.format(rset.getDouble(10)));
							VCGST_Amt.add(nf.format(rset.getDouble(11)));
							VSGST_Amt.add(nf.format(rset.getDouble(12)));
							VHSN_CD.add(rset.getString(13)==null?"":rset.getString(13));
					/////		VSAC_CD.add(rset.getString(14)==null?"":rset.getString(14));	
							VSAC_CD.add(rset.getString(14)+"-"+rset.getString(9));	
							VCESS_Perc.add(nf.format(rset.getDouble(15)));
							VCESS_Amt.add(nf.format(rset.getDouble(16)));		
						}
					}
					
					for(int i=0;i<VCamt.size();i++) 
					{
						String tt=VCamt.elementAt(i)+"";
						if(!tt.equals("") && (!tt.equals("null"))) 
						{
							String tt1=	 nf1.format(Double.parseDouble(tt));
							camt.add(tt1);
						}
						String tt2=VCrate.elementAt(i)+"";
						if(!tt2.equals("") && (!tt2.equals("null"))) 
						{
							//SB20171114 String tt3=	nf1.format(Double.parseDouble(tt2)); //Four decomal
							String tt3=	nf1.format(Double.parseDouble(tt2)); //Two decomal
							crt.add(tt3);
						}						
					}
//					System.out.println("-tax_cd: "+tax_cd);
		//////////////////////TAX CODE //////////////////////////////////
				if(tax_cd.equalsIgnoreCase("C"))
				{						
						Tax_nm.add("CGST");
						Tax_nm.add("SGST");
						//taxstr.add("9");
						//taxstr.add("9");
						String query1="SELECT RATE_CGST,RATE_SGST " +
								 " FROM DLNG_AC_INVOICE_DTL "+
								" WHERE INV_SEQ_NO='"+Hlpl_seq_no+"' "
								+ "AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"' AND RATE_CGST >0 ";
//						System.out.println("GST-INV:Modify:QRY-008G1: "+query1);
						rset=stmt.executeQuery(query1);
						while(rset.next())
						{
							taxstr.add(rset.getString(1)==null?"":rset.getString(1));
							taxstr.add(rset.getString(2)==null?"":rset.getString(2));
						}
//						System.out.println("MODIFY-taxstr: "+taxstr);
						for(int i=0;i<taxstr.size();i++)
						{
							String tt=taxstr.elementAt(i)+"";
							//	taxstr1.add(taxstss);
								fin_tax=(Double.parseDouble(Sgross_amt_inr) * Double.parseDouble(taxstr.elementAt(i)+""))/100;
								 tax=nf5.format(Math.round(fin_tax));
								 Ttax_amt_inr.add(tax);
								 tax1=nf1.format(Math.round(fin_tax));
								 Ttax_amt_inr_chk.add(tax1);
								 tt1_rate.add(nf4.format(Double.parseDouble(tt)));
						}
						System.out.println("MODIFY-Ttax_amt_inr: "+Ttax_amt_inr);	
						
				}
				else if(tax_cd.equalsIgnoreCase("I"))
				{
						Tax_nm.add("IGST");
						//taxstr.add("18");
						String query1="SELECT RATE_IGST " +
								 " FROM DLNG_AC_INVOICE_DTL "+
								 " WHERE INV_SEQ_NO='"+Hlpl_seq_no+"' "
								+" AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"' AND RATE_IGST >0 ";
//						System.out.println("GST-INV:Modify:QRY-008G2: "+query1);
						rset=stmt.executeQuery(query1);
						if(rset.next())
						{
							taxstr.add(rset.getString(1)==null?"":rset.getString(1));
						}
//						System.out.println("IGST-taxstr: "+taxstr);
						for(int i=0;i<taxstr.size();i++)
						{
							String tt=taxstr.elementAt(i)+"";
							//taxstr1.add(taxstss);
							fin_tax=(Double.parseDouble(Sgross_amt_inr) * Double.parseDouble(taxstr.elementAt(i)+""))/100;
							 tax=nf5.format(Math.round(fin_tax));
							 Ttax_amt_inr.add(tax);
							 tax1=nf1.format(Math.round(fin_tax));
							 Ttax_amt_inr_chk.add(tax1);
							 tt1_rate.add(nf4.format(Double.parseDouble(tt)));
						}												
				}
//				System.out.println("MODIFY-Tax_nm: "+Tax_nm);
////////////////////////////////TAX CODE///////////////////
				String queryString22="SELECT addr,city,pin,phone,fax_1,country " +
						  " FROM FMS7_SUPPLIER_ADDRESS_MST A WHERE SUPPLIER_CD='"+supplier_cd+"'  AND A.address_type='R' AND " +
						  " A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B " +
						  " WHERE A.supplier_cd=B.supplier_cd AND B.address_type='R' AND B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY')) ";
//				System.out.println("GST-INV:Modify:QRY-008H: "+queryString22);	
				rset=stmt.executeQuery(queryString22);
							if(rset.next())
							{
								Supp_addr_nm_R=rset.getString(1)==null?"":rset.getString(1);
								Supp_addr_city_R=rset.getString(2)==null?"":rset.getString(2);
								pin_R=rset.getString(3)==null?"":rset.getString(3);
								phone_R=rset.getString(4)==null?"":rset.getString(4);
								fax_R=rset.getString(5)==null?"":rset.getString(5);
								cntry_R=rset.getString(6)==null?"":rset.getString(6);
						}
			/*SB20180209	String queryString221="SELECT A.RULE_REMARK " +
						" FROM FMS7_LNG_SALES_MAPPING A " +
						" WHERE A.SUPPLIER_CD='"+supp_cd+"' AND A.CONTRACT_TYPE='"+inv_type+"' and "+
						" A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_LNG_SALES_MAPPING B WHERE A.supplier_cd=B.supplier_cd AND B.contract_type='"+inv_type+"' and " +
						" B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY')) ";
						System.out.println("dsdsd---"+queryString221);
							rset=stmt.executeQuery(queryString221);
							if(rset.next())
							{
								rule_rmk=rset.getString(1)==null?"":rset.getString(1);
							}			
				*/
					if(!snet_amt_inr.equalsIgnoreCase("")) 
					{	
						snet_amt_inr=nf5.format(Math.round(Double.parseDouble(snet_amt_inr))); //SB20180330
						/*SB20170831: International	EnglishNumberToWords es=new EnglishNumberToWords();
						es.convert(Double.parseDouble(snet_amt_inr));
						*/					
						NumWord es=new NumWord();
						es.convertNumToWord(Integer.parseInt(snet_amt_inr));
						result=es.convertNumToWord(Integer.parseInt(snet_amt_inr));
					}
					///////////////////////SB20111104: VENDOR/Customer ABR /////////////////
					String queryStringdt11="Select Vendor_cd,vendor_abbr,vendor_name "
						+ "from  GST_VENDOR_MST where Vendor_cd='"+cust_cd+"'  ";
					System.out.println("GST-INV:Type=Z:QRY-006: "+queryStringdt11);
					rset=stmt.executeQuery(queryStringdt11);
					if(rset.next())
					{
						SVendor_nm=rset.getString(3)==null?"":rset.getString(3);
						SVendor_abr=rset.getString(2)==null?"*":rset.getString(2); //SB20171104
					}
					////////////////////////////////////////////////////////////////////////
			}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	String enddt="";
	
	String CR_DR="";

	
	String set_sac_cd="";String Svendor_cd="";
	
	 public void createTableFor_OtherInvoice()
		{
			try
			{
				int count=0;
				String query="select count(*) from tab where lower(tname)='fms8_other_invoice_dtl' ";
				//System.out.println("FEtching whether table exists.."+query);
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					count=rset.getInt(1);
				}
				if(count==0)
				{
				String create="CREATE TABLE FMS8_OTHER_INVOICE_DTL"
						+ "("
						+ " INV_SEQ_NO NUMBER(6) NOT NULL," +
						" ITEM_DESCRIPTION VARCHAR2(500 BYTE) NULL," +
						" QUANTITY NUMBER(11,2) NULL," +
						" RATE NUMBER(7,4) NULL," +
						" FLAG CHAR(1) NULL," +
						" ENTER_BY NUMBER(6) NULL," +
						" ENTER_DT DATE NULL," +
						" TAX_DETAILS VARCHAR2(100 BYTE) NULL," +
						" FINANCIAL_YEAR VARCHAR2(9 BYTE) NOT NULL," +
						" CONTRACT_TYPE CHAR(1 BYTE) NOT NULL," +
						" EFF_DT DATE NOT NULL," +
						" AMOUNT_IN_WORDS VARCHAR2(500 BYTE) NULL,  "
						+ "HSN_CODE VARCHAR2(100 BYTE) NULL,  "
						+ "SAC_CODE VARCHAR2(100 BYTE) NULL,  " 
						+ "REMARK VARCHAR2(1024 BYTE) NULL,  " 
						+ "REMARK1 VARCHAR2(1024 BYTE) NULL,  " 
						+ "REMARK2 VARCHAR2(1024 BYTE) NULL,  " 
						+ "REMARK3 VARCHAR2(1024 BYTE) NULL,  " 
						+ "PURCHASE_NO VARCHAR2(50 BYTE) NULL,  " 
						+ "REFERENCE_NO VARCHAR2(50 BYTE) NULL,  " 
						+ "VESSEL_CD NUMBER(6,0) NULL,  "
						+ "VESSEL_ITEM VARCHAR2(25 BYTE) NULL,  "
						+ "GRT NUMBER(11,2) NULL,  "
						+ "TIME_SLOTS_BERTHING NUMBER(4) NULL,  "
						+ "HRS_BERTHING NUMBER(6) NULL,  "
						+ "CUSTOMER_PAN_NO VARCHAR2(20) NULL,  "
						+ "CUSTOMER_NM VARCHAR2(100) NULL,  "
						+ "CUSTOMER_ADDR VARCHAR2(300) NULL,  "
						+ "CUSTOMER_STATE_CD VARCHAR2(5) NULL,  "
						+ "CUSTOMER_COUNTRY_NM VARCHAR2(50) NULL,  "
						+ "SUPPLIER_STATE_CD VARCHAR2(5) NULL,  "
						+ "VESSEL_AGENT VARCHAR2(200) NULL,  "
						+ "IMPORTER VARCHAR2(200) NULL,  "
						+ "VESSEL_FLAG VARCHAR2(40 BYTE) NULL,  " +
							"CARGO_DT     DATE, "+
							"CARGO_AMOUNT    NUMBER(12,2) "+
						")";
				
				
				
			//	System.out.println("create...query..."+create);
				stmt.executeUpdate(create);
			//	conn.commit();
				}
			}
			catch(Exception e)
			{
				System.out.println("EXception in......"+e.getMessage());
				e.printStackTrace();
			}
			
		}
	
	public void FETCH_INVOICE_DTL() //SB20171107
	{
		try
		{
			if(!month.equalsIgnoreCase("") && (!year.equalsIgnoreCase(""))){
				
				String queryString2a="SELECT SUPPLIER_CD,SUPPLIER_NAME,SUPPLIER_ABBR FROM FMS7_SUPPLIER_MST WHERE SUPPLIER_CD='"+supp_cd_set+"' ";
		//		System.out.println("INV-LIST: "+queryString2a);
				rset=stmt.executeQuery(queryString2a);
				if(rset.next())
				{
					supplier_abbr=rset.getString(3)==null?"":rset.getString(3);
				}
				 dt="01/"+month+"/"+year;
				 String queryString1="select TO_CHAR(LAST_DAY(TO_DATE('"+dt+"','dd/mm/yyyy')),'DD/MM/YYYY') from DUAL";
					//System.out.println("----query-----"+queryString1);
					rset=stmt.executeQuery(queryString1);
					if(rset.next())
					{
						end_dt=rset.getString(1);
					}
					
					if(Integer.parseInt(month)<4){
						fin_yr=(Integer.parseInt(year)-1)+"-"+year;
					}else{
						fin_yr=year+"-"+(Integer.parseInt(year)+1);
					}
					
				queryString = "select new_inv_seq_no,to_char(invoice_dt,'dd/mm/yyyy'),INV_SEQ_NO,financial_year,"
						+ "checked_flag,approved_flag,pdf_inv_dtl,supplier_cd, CUSTOMER_CD " +
						" from fms7_invoice_mst " +
						" where contract_type='"+inv_type+"' and financial_year='"+fin_yr+"' and (invoice_dt between to_date('"+dt+"','dd/mm/yyyy') "
						+ "and to_date('"+end_dt+"','dd/mm/yyyy'))  order by INV_SEQ_NO DESC";
		//		System.out.println("Customer Details Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
					{
						Vinv_seq_no.add(rset.getString(1)==null?"":rset.getString(1));
						Vinv_dt.add(rset.getString(2)==null?"":rset.getString(2));
						Vhlpl_inv_seq_no.add(rset.getString(3)==null?"":rset.getString(3));
						Vfin_yr.add(rset.getString(4)==null?"":rset.getString(4));
						Checked.add(rset.getString(5)==null?"":rset.getString(5));
						approved.add(rset.getString(6)==null?"":rset.getString(6));
						Vpdf_dtl.add(rset.getString(7)==null?"":rset.getString(7));
						VSupp_cd.add(rset.getString(8)==null?"":rset.getString(8));
						VCust_cd.add(rset.getString(9)==null?"":rset.getString(9));
					}
				System.out.println("<<<<<<<<<----->>>>>>>>>>>"+Vpdf_dtl);
				for(int k=0;k<Vhlpl_inv_seq_no.size();k++) 
				{
					queryString = "select Customer_nm from fms8_other_invoice_dtl " +
							" where contract_type='"+inv_type+"' and financial_year='"+fin_yr+"' and inv_seq_no='"+Vhlpl_inv_seq_no.elementAt(k)+"' AND SUPPLIER_CD='"+VSupp_cd.elementAt(k)+"' ";
				//	System.out.println("Customer Details Fetch Query 1 = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
						{
							Vcust_nm.add(rset.getString(1)==null?"":rset.getString(1));
							//Vflag_sac.add(rset.getString(2)==null?"":rset.getString(2));
							
						} else {
							Vcust_nm.add("");
						}
					/////////////////////////////////////////////////////////////////////////////////////////////////////////////
					queryString = "select VENDOR_ABBR, VENDOR_NAME from FMS8_VENDOR_MST " +
					" where VENDOR_CD='"+VCust_cd.elementAt(k)+"' ";
			//		System.out.println("Customer Details Fetch Query 1 = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
						{
							Vvendor_abr.add(rset.getString(1)==null?"":rset.getString(1));
							Vvendor_nm.add(rset.getString(2)==null?"":rset.getString(2));
						} 
					else 
						{
							Vvendor_abr.add("");
							Vvendor_nm.add("");
						}
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////
					queryString1 = "select distinct(flag_sac) from fms8_other_invoice_dtl where contract_type='"+inv_type+"' and financial_year='"+fin_yr+"' and inv_seq_no='"+Vhlpl_inv_seq_no.elementAt(k)+"' and supplier_cd='"+VSupp_cd.elementAt(k)+"'";
							//	System.out.println("Customer Details Fetch Query 2  = "+queryString1);
								rset = stmt.executeQuery(queryString1);
							if(rset.next())
							{
								Vflag_sac.add(rset.getString(1)==null?"":rset.getString(1));
								
							} else {
								Vflag_sac.add("");
							}
				}
//				System.out.println("vflag_sac"+Vflag_sac);
				//////////////////////SB20171104: PDF from BIPl dir///////////////////
				String InvType="";
				if(inv_type.equals("Z"))
					 InvType="INVOICE";
				else
					 InvType="RECEIPT";
				
				for(int k=0;k<Vhlpl_inv_seq_no.size();k++)
				{					
					String f1="";
					String f2="";				
					String path=request.getRealPath("/pdf_reports/other_invoices/"+supplier_abbr);//SB20171104				
						f1=InvType+"-"+Vvendor_abr.elementAt(k)+"-"+Vinv_seq_no.elementAt(k)+"-O";
						f2=InvType+"-"+Vvendor_abr.elementAt(k)+"-"+Vinv_seq_no.elementAt(k)+"-O.pdf";						
					f1=path+"/"+f1;			
					File pdf_file=new File(f1+".pdf");
			//		System.out.println("inv_file: "+pdf_file);
					
					if(pdf_file.exists())
					{						
						//System.out.println("inside  rrr");
							path=path+"//"+pdf_file;
							//System.out.println("invoice_pdf_path1----"+invoice_pdf_path1);
				            String context_nm = request.getContextPath();
							String server_nm = request.getServerName();
							String server_port = ""+request.getServerPort();
							  //system.out.println("invoice_pdf_path--"+invoice_pdf_path1);
							String url_start = "http://"+server_nm+":"+server_port+context_nm;
							
							 pdfpath = path;
							pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
							
						//SB20171104	pdfpath = url_start+"/pdf_reports/other_invoices/pdf_files/"+f2;
							pdfpath = url_start+"/pdf_reports/other_invoices/"+supplier_abbr+"/"+f2; //SB20171104
							
							pdf.add(pdfpath);
					}
					else{
						pdf.add("");
					}					
				}
			//	System.out.println("pdf---> "+pdf);
				for(int k=0;k<Vhlpl_inv_seq_no.size();k++)
				{					
					String f1="";
					String f2="";				
					String path=request.getRealPath("/pdf_reports/other_invoices/"+supplier_abbr);//SB20171104				
						f1=InvType+"-"+Vvendor_abr.elementAt(k)+"-"+Vinv_seq_no.elementAt(k)+"-D";
						f2=InvType+"-"+Vvendor_abr.elementAt(k)+"-"+Vinv_seq_no.elementAt(k)+"-D.pdf";						
					f1=path+"/"+f1;			
					File pdf_file=new File(f1+".pdf");
			//		System.out.println("inv_file: "+pdf_file);
					
					if(pdf_file.exists())
					{						
						//System.out.println("inside  rrr");
							path=path+"//"+pdf_file;
							//System.out.println("invoice_pdf_path1----"+invoice_pdf_path1);
				            String context_nm = request.getContextPath();
							String server_nm = request.getServerName();
							String server_port = ""+request.getServerPort();
							  //system.out.println("invoice_pdf_path--"+invoice_pdf_path1);
							String url_start = "http://"+server_nm+":"+server_port+context_nm;
							
							 pdfpath = path;
							pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
							
						//SB20171104	pdfpath = url_start+"/pdf_reports/other_invoices/pdf_files/"+f2;
							pdfpath = url_start+"/pdf_reports/other_invoices/"+supplier_abbr+"/"+f2; //SB20171104
							
							pdf1.add(pdfpath);
					}
					else{
						pdf1.add("");
					}					
				}
			//	System.out.println("pdf1---> "+pdf1);
				for(int k=0;k<Vhlpl_inv_seq_no.size();k++)
				{					
					String f1="";
					String f2="";				
					String path=request.getRealPath("/pdf_reports/other_invoices/"+supplier_abbr);//SB20171104				
						f1=InvType+"-"+Vvendor_abr.elementAt(k)+"-"+Vinv_seq_no.elementAt(k)+"-T";
						f2=InvType+"-"+Vvendor_abr.elementAt(k)+"-"+Vinv_seq_no.elementAt(k)+"-T.pdf";						
					f1=path+"/"+f1;			
					File pdf_file=new File(f1+".pdf");
			//		System.out.println("inv_file: "+pdf_file);
					
					if(pdf_file.exists())
					{						
						//System.out.println("inside  rrr");
							path=path+"//"+pdf_file;
							//System.out.println("invoice_pdf_path1----"+invoice_pdf_path1);
				            String context_nm = request.getContextPath();
							String server_nm = request.getServerName();
							String server_port = ""+request.getServerPort();
							  //system.out.println("invoice_pdf_path--"+invoice_pdf_path1);
							String url_start = "http://"+server_nm+":"+server_port+context_nm;
							
							 pdfpath = path;
							pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
							
						//SB20171104	pdfpath = url_start+"/pdf_reports/other_invoices/pdf_files/"+f2;
							pdfpath = url_start+"/pdf_reports/other_invoices/"+supplier_abbr+"/"+f2; //SB20171104
							
							pdf2.add(pdfpath);
					}
					else{
						pdf2.add("");
					}					
				}
		//		System.out.println("pdf2---> "+pdf2);
				//////////////////////////////////////////////////////////////////////

				//////////////////////////////////////
				int PrevInvCountOtheThanAdv=0;
				queryString = "SELECT COUNT(INVOICE_DT)  FROM FMS7_INVOICE_MST " +
				"WHERE financial_year='"+fin_yr+"' AND contract_type='"+inv_type+"' AND INVOICE_DT > TO_DATE('"+end_dt+"','DD/MM/YYYY') "; //SB20171025
			//	System.out.println("MAX-INV-COUNT: "+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					PrevInvCountOtheThanAdv=rset.getInt(1);
				//	LastInvGeneratedDt=rset.getString(2);
				}
				queryString = "SELECT TO_CHAR(MAX(TO_DATE(INVOICE_DT,'DD/MM/YYYY')),'DD/MM/YYYY')  FROM FMS7_INVOICE_MST " +
				"WHERE financial_year='"+fin_yr+"' AND contract_type='"+inv_type+"'  "; //SB20171025
		//		System.out.println("MAX-INV-GEN-DT: "+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					LastInvGeneratedDt=rset.getString(1);
				}
				NoInvGeneratedBeyond=""+PrevInvCountOtheThanAdv;
			    //////////////////////////////////////////////For  Billed  Invice///////////////////////////////////////////
				for(int k=0;k<Vhlpl_inv_seq_no.size();k++) 
				{
				//	System.out.println("Customer Details Fetch Query 1 = "+queryString);
					queryString = "SELECT NVL(INV_FORMAT_TYPE,'0') FROM DLNG_SERVICE_INVOICE_MST " +
			  		"WHERE CONTRACT_TYPE='"+inv_type+"'  and supplier_cd='"+VSupp_cd.elementAt(k)+"' and inv_seq_no='"+Vhlpl_inv_seq_no.elementAt(k)+"' " +
			  		" AND FINANCIAL_YEAR='"+fin_yr+"'  "; //SB20171025
				//	System.out.println("PDF-Template: "+queryString);
					rset=stmt.executeQuery(queryString);
					if(rset.next())
						VPDF_Template_Allocated.add(rset.getString(1)==null?"0":rset.getString(1));
					else
						VPDF_Template_Allocated.add("0");
				}
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public String getCallFlag() {
		return callFlag;
	}

	public void setCallFlag(String callFlag) {
		this.callFlag = callFlag;
	}

	public String getContact_Suppl_Service_tax_no() {
		return contact_Suppl_Service_tax_no;
	}
	public String getContact_Suppl_PAN_NO() {return contact_Suppl_PAN_NO;}
	public void setContact_Suppl_PAN_NO(String contact_Suppl_PAN_NO) {
		this.contact_Suppl_PAN_NO = contact_Suppl_PAN_NO;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Vector getContact_Customer_pAN_NO() {
		return contact_Customer_pAN_NO;
	}

	public void setContact_Customer_pAN_NO(Vector contact_Customer_pAN_NO) {
		this.contact_Customer_pAN_NO = contact_Customer_pAN_NO;
	}

	public Vector getContact_Customer_Name() {
		return contact_Customer_Name;
	}

	public Vector getContact_Customer_cd() {
		return contact_Customer_cd;
	}

	public String getCust_sts_cd() {
		return cust_sts_cd;
	}

	public void setCust_sts_cd(String cust_sts_cd) {
		this.cust_sts_cd = cust_sts_cd;
	}

	public Vector getState_nm() {
		return state_nm;
	}

	public void setState_nm(Vector state_nm) {
		this.state_nm = state_nm;
	}

	public Vector getState_cd() {
		return state_cd;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Vector getTax_Name() {
		return Tax_Name;
	}

	public void setTax_Name(Vector tax_Name) {
		Tax_Name = tax_Name;
	}

	public Vector getGrossamt() {
		return grossamt;
	}

	public double getNet_amt_inr() {
		return net_amt_inr;
	}

	
	public Vector getTax_nm() {
		return Tax_nm;
	}

	public void setTax_nm(Vector tax_nm) {
		Tax_nm = tax_nm;
	}

	public String getTax_amt() {
		return tax_amt;
	}

	public Vector getTaxstr() {
		return taxstr;
	}

	public void setTaxstr(Vector taxstr) {
		this.taxstr = taxstr;
	}

	public String getSupp_stat_CD() {
		return Supp_stat_CD;
	}

	public void setSupp_stat_CD(String supp_stat_CD) {
		Supp_stat_CD = supp_stat_CD;
	}
	public String getContact_Suppl_gstin_no() {	return contact_Suppl_gstin_no;}
	public void setContact_Suppl_gstin_no(String contact_Suppl_gstin_no) {
		this.contact_Suppl_gstin_no = contact_Suppl_gstin_no;
	}
	public void setInv_type(String inv_type) {
		this.inv_type = inv_type;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddr() {
		return Addr;
	}
	public String getFin_yr() {
		return fin_yr;
	}
	public void setFin_yr(String fin_yr) {
		this.fin_yr = fin_yr;
	}
	public String getCust_seq_no() {
		return cust_seq_no;
	}
	public void setCust_seq_no(String cust_seq_no) {
		this.cust_seq_no = cust_seq_no;
	}
	public String getInv_seq_no() {
		return inv_seq_no;
	}
	public void setInv_seq_no(String inv_seq_no) {
		this.inv_seq_no = inv_seq_no;
	}
	public String getGST_INVOICE_SEQ_NO() {
		return GST_INVOICE_SEQ_NO;
	}
	public void setGST_INVOICE_SEQ_NO(String gST_INVOICE_SEQ_NO) {
		GST_INVOICE_SEQ_NO = gST_INVOICE_SEQ_NO;
	}
	public String getEnd_dt() {
		return end_dt;
	}
	public void setEnd_dt(String end_dt) {
		this.end_dt = end_dt;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getSupp_nm() {
		return supp_nm;
	}
	public String getBill_stat_CD() {
		return bill_stat_CD;
	}
	public void setBill_stat_CD(String bill_stat_CD) {
		this.bill_stat_CD = bill_stat_CD;
	}
	public String getSupp_CD() {
		return supp_CD;
	}
	public void setSupp_CD(String supp_CD) {
		this.supp_CD = supp_CD;
	}

	public Vector getVinv_dt() {
		return Vinv_dt;
	}

	public void setVinv_dt(Vector vinv_dt) {
		Vinv_dt = vinv_dt;
	}

	public Vector getVinv_seq_no() {
		return Vinv_seq_no;
	}
	public Vector getVvessel_item() {
		return Vvessel_item;
	}
	public void setVvessel_item(Vector vvessel_item) {
		Vvessel_item = vvessel_item;
	}
	public Vector getVvessel_cd() {
		return Vvessel_cd;
	}
	public void setVvessel_cd(Vector vvessel_cd) {
		Vvessel_cd = vvessel_cd;
	}
	public Vector getVvessel_nm() {
		return Vvessel_nm;
	}
	public void setVvessel_nm(Vector vvessel_nm) {
		Vvessel_nm = vvessel_nm;
	}
	public Vector getVvessel_flg() {
		return Vvessel_flg;
	}
	public void setVvessel_flg(Vector vvessel_flg) {
		Vvessel_flg = vvessel_flg;
	}
	public String getSupp_cd() {
		return supp_cd;
	}
	public void setSupp_cd(String supp_cd) {
		this.supp_cd = supp_cd;
	}
	public String getStax_str() {
		return Stax_str;
	}
	public void setStax_str(String stax_str) {
		Stax_str = stax_str;
	}
	public String getStax_str1() {
		return Stax_str1;
	}
	public void setStax_str1(String stax_str1) {
		Stax_str1 = stax_str1;
	}
	public String getCust_stat_cd() {
		return Cust_stat_cd;
	}
	public void setCust_stat_cd(String cust_stat_cd) {
		Cust_stat_cd = cust_stat_cd;
	}
	public String getCust_stat_nm() {
		return Cust_stat_nm;
	}
	public void setCust_stat_nm(String cust_stat_nm) {
		Cust_stat_nm = cust_stat_nm;
	}
	public String getContact_cust_Name() {
		return contact_cust_Name;
	}
	public void setContact_cust_Name(String contact_cust_Name) {
		this.contact_cust_Name = contact_cust_Name;
	}
	public String getCust_address_nm() {
		return Cust_address_nm;
	}
	public void setCust_address_nm(String cust_address_nm) {
		Cust_address_nm = cust_address_nm;
	}
	public String getContact_cust_PAN_NO() {
		return contact_cust_PAN_NO;
	}
	public void setContact_cust_PAN_NO(String contact_cust_PAN_NO) {
		this.contact_cust_PAN_NO = contact_cust_PAN_NO;
	}
	public String getContact_cust_gstin_no() {
		return contact_cust_gstin_no;
	}
	public void setContact_cust_gstin_no(String contact_cust_gstin_no) {
		this.contact_cust_gstin_no = contact_cust_gstin_no;
	}
	public Vector getCUST_ADDRESS() {
		return CUST_ADDRESS;
	}
	public void setCUST_ADDRESS(Vector cUST_ADDRESS) {
		CUST_ADDRESS = cUST_ADDRESS;
	}
	public Vector getCUST_state() {
		return CUST_state;
	}
	public void setCUST_state(Vector cUST_state) {
		CUST_state = cUST_state;
	}
	public Vector getCust_state_cd() {
		return cust_state_cd;
	}
	public void setCust_state_cd(Vector cust_state_cd) {
		this.cust_state_cd = cust_state_cd;
	}
	public Vector getVcargo_ref_no() {
		return Vcargo_ref_no;
	}
	public void setVcargo_ref_no(Vector vcargo_ref_no) {
		Vcargo_ref_no = vcargo_ref_no;
	}
	public Vector getVcargo_dt() {
		return Vcargo_dt;
	}
	public void setVcargo_dt(Vector vcargo_dt) {
		Vcargo_dt = vcargo_dt;
	}
	public Vector getVqty() {
		return Vqty;
	}
	public void setVqty(Vector vqty) {
		Vqty = vqty;
	}
	public Vector getExchg_cd() {
		return exchg_cd;
	}
	public void setExchg_cd(Vector exchg_cd) {
		this.exchg_cd = exchg_cd;
	}
	public Vector getExchg_nm() {
		return exchg_nm;
	}
	public void setExchg_nm(Vector exchg_nm) {
		this.exchg_nm = exchg_nm;
	}
	public Vector getExchg_val() {
		return Exchg_val;
	}
	public void setExchg_val(Vector exchg_val) {
		Exchg_val = exchg_val;
	}
	public Vector getExchg_eff_dt() {
		return Exchg_eff_dt;
	}
	public void setExchg_eff_dt(Vector exchg_eff_dt) {
		Exchg_eff_dt = exchg_eff_dt;
	}
	public String getCust_cd1() {
		return cust_cd1;
	}
	public void setCust_cd1(String cust_cd1) {
		this.cust_cd1 = cust_cd1;
	}
	public Vector getVship_cd() {
		return Vship_cd;
	}
	public void setVship_cd(Vector vship_cd) {
		Vship_cd = vship_cd;
	}
	public String getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(String seq_no) {
		this.seq_no = seq_no;
	}
	public Vector getVhlpl_inv_seq_no() {
		return Vhlpl_inv_seq_no;
	}
	public void setVhlpl_inv_seq_no(Vector vhlpl_inv_seq_no) {
		Vhlpl_inv_seq_no = vhlpl_inv_seq_no;
	}
	public Vector getVfin_yr() {
		return Vfin_yr;
	}
	public void setVfin_yr(Vector vfin_yr) {
		Vfin_yr = vfin_yr;
	}
	public String getHlpl_seq_no() {
		return Hlpl_seq_no;
	}
	public void setHlpl_seq_no(String hlpl_seq_no) {
		Hlpl_seq_no = hlpl_seq_no;
	}
	public String getVFin_yr() {
		return VFin_yr;
	}
	public void setVFin_yr(String vFin_yr) {
		VFin_yr = vFin_yr;
	}
	public String getSdue_dt() {
		return Sdue_dt;
	}
	public void setSdue_dt(String sdue_dt) {
		Sdue_dt = sdue_dt;
	}
	public String getSinv_dt() {
		return Sinv_dt;
	}
	public void setSinv_dt(String sinv_dt) {
		Sinv_dt = sinv_dt;
	}
	public String getSgross_amt_usd() {
		return Sgross_amt_usd;
	}
	public void setSgross_amt_usd(String sgross_amt_usd) {
		Sgross_amt_usd = sgross_amt_usd;
	}
	public String getSgrt() {
		return Sgrt;
	}
	public void setSgrt(String sgrt) {
		Sgrt = sgrt;
	}
	public String getSitem_desc() {
		return sitem_desc;
	}
	public void setSitem_desc(String sitem_desc) {
		this.sitem_desc = sitem_desc;
	}
	public String getsCust_addr() {
		return sCust_addr;
	}
	public void setsCust_addr(String sCust_addr) {
		this.sCust_addr = sCust_addr;
	}
	public String getSitem() {
		return sitem;
	}
	public void setSitem(String sitem) {
		this.sitem = sitem;
	}
	public String getsVflag() {
		return sVflag;
	}
	public void setsVflag(String sVflag) {
		this.sVflag = sVflag;
	}
	public String getSvessel_ag() {
		return svessel_ag;
	}
	public void setSvessel_ag(String svessel_ag) {
		this.svessel_ag = svessel_ag;
	}
	public String getSvessel_cd() {
		return svessel_cd;
	}
	public void setSvessel_cd(String svessel_cd) {
		this.svessel_cd = svessel_cd;
	}
	public String getSberthing_hrs() {
		return sberthing_hrs;
	}
	public void setSberthing_hrs(String sberthing_hrs) {
		this.sberthing_hrs = sberthing_hrs;
	}
	public String getsCust_pan_no() {
		return sCust_pan_no;
	}
	public void setsCust_pan_no(String sCust_pan_no) {
		this.sCust_pan_no = sCust_pan_no;
	}
	public String getSimporter() {
		return simporter;
	}
	public void setSimporter(String simporter) {
		this.simporter = simporter;
	}
	public String getsCust_state() {
		return sCust_state;
	}
	public void setsCust_state(String sCust_state) {
		this.sCust_state = sCust_state;
	}
	public String getCust_name() {
		return Cust_name;
	}
	public void setCust_name(String cust_name) {
		Cust_name = cust_name;
	}
	public String getShip_nm() {
		return ship_nm;
	}
	public void setShip_nm(String ship_nm) {
		this.ship_nm = ship_nm;
	}
	public String getSstate_nm() {
		return sstate_nm;
	}
	public void setSstate_nm(String sstate_nm) {
		this.sstate_nm = sstate_nm;
	}
	public String getSrate() {
		return Srate;
	}
	public void setSrate(String srate) {
		Srate = srate;
	}
	public String getFlag_act() {
		return flag_act;
	}
	public void setFlag_act(String flag_act) {
		this.flag_act = flag_act;
	}
	public String getCust_nm() {
		return cust_nm;
	}
	public void setCust_nm(String cust_nm) {
		this.cust_nm = cust_nm;
	}
	public String getCust_addr() {
		return cust_addr;
	}
	public void setCust_addr(String cust_addr) {
		this.cust_addr = cust_addr;
	}
	public String getCust_st_cd() {
		return cust_st_cd;
	}
	public void setCust_st_cd(String cust_st_cd) {
		this.cust_st_cd = cust_st_cd;
	}
	public String getCust_city() {
		return cust_city;
	}
	public void setCust_city(String cust_city) {
		this.cust_city = cust_city;
	}
	public String getCust_pin() {
		return cust_pin;
	}
	public void setCust_pin(String cust_pin) {
		this.cust_pin = cust_pin;
	}
	public String getTax_cd() {
		return tax_cd;
	}
	public void setTax_cd(String tax_cd) {
		this.tax_cd = tax_cd;
	}
	public String getSac_cd() {
		return sac_cd;
	}
	public void setSac_cd(String sac_cd) {
		this.sac_cd = sac_cd;
	}
	public String getHasn_cd() {
		return hasn_cd;
	}
	public void setHasn_cd(String hasn_cd) {
		this.hasn_cd = hasn_cd;
	}
	public String getItem_desc() {
		return item_desc;
	}
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
	public String getSgross_amt_inr() {
		return Sgross_amt_inr;
	}
	public void setSgross_amt_inr(String sgross_amt_inr) {
		Sgross_amt_inr = sgross_amt_inr;
	}
	public String getTax_amt_inr() {
		return tax_amt_inr;
	}
	public void setTax_amt_inr(String tax_amt_inr) {
		this.tax_amt_inr = tax_amt_inr;
	}
	public String getSnet_amt_inr() {
		return snet_amt_inr;
	}
	public void setSnet_amt_inr(String snet_amt_inr) {
		this.snet_amt_inr = snet_amt_inr;
	}
	public Vector getTtax_amt_inr() {
		return Ttax_amt_inr;
	}
	public void setTtax_amt_inr(Vector ttax_amt_inr) {
		Ttax_amt_inr = ttax_amt_inr;
	}
	public String getCust_state_cd1() {
		return cust_state_cd1;
	}
	public void setCust_state_cd1(String cust_state_cd1) {
		this.cust_state_cd1 = cust_state_cd1;
	}
	public String getCust_gstin_no() {
		return cust_gstin_no;
	}
	public void setCust_gstin_no(String cust_gstin_no) {
		this.cust_gstin_no = cust_gstin_no;
	}
	public String getPur_no() {
		return pur_no;
	}
	public void setPur_no(String pur_no) {
		this.pur_no = pur_no;
	}
	public String getRef_no() {
		return ref_no;
	}
	public void setRef_no(String ref_no) {
		this.ref_no = ref_no;
	}
	public String getSvitem() {
		return svitem;
	}
	public void setSvitem(String svitem) {
		this.svitem = svitem;
	}
	public String getShrs() {
		return shrs;
	}
	public void setShrs(String shrs) {
		this.shrs = shrs;
	}
	public String getSslots() {
		return sslots;
	}
	public void setSslots(String sslots) {
		this.sslots = sslots;
	}
	public String getSqty() {
		return Sqty;
	}
	public void setSqty(String sqty) {
		Sqty = sqty;
	}
	public Vector getVcargorate() {
		return Vcargorate;
	}
	public void setVcargorate(Vector vcargorate) {
		Vcargorate = vcargorate;
	}
	public Vector getVcargodt() {
		return Vcargodt;
	}
	public void setVcargodt(Vector vcargodt) {
		Vcargodt = vcargodt;
	}
	public Vector getVcargoqty() {
		return Vcargoqty;
	}
	public void setVcargoqty(Vector vcargoqty) {
		Vcargoqty = vcargoqty;
	}
	public Vector getVcargo_amt() {
		return Vcargo_amt;
	}
	public void setVcargo_amt(Vector vcargo_amt) {
		Vcargo_amt = vcargo_amt;
	}
	public Vector getVcargo_cd() {
		return Vcargo_cd;
	}
	public void setVcargo_cd(Vector vcargo_cd) {
		Vcargo_cd = vcargo_cd;
	}
	
	public Vector getVship_nm1() {
		return Vship_nm1;
	}
	public void setVship_nm1(Vector vship_nm1) {
		Vship_nm1 = vship_nm1;
	}
	public String getExch_cd() {
		return exch_cd;
	}
	public void setExch_cd(String exch_cd) {
		this.exch_cd = exch_cd;
	}
	public String getExch_val() {
		return exch_val;
	}
	public void setExch_val(String exch_val) {
		this.exch_val = exch_val;
	}
	public Vector getVcust_nm() {
		return Vcust_nm;
	}
	public void setVcust_nm(Vector vcust_nm) {
		Vcust_nm = vcust_nm;
	}
	public Vector getTtax_amt_inr_chk() {
		return Ttax_amt_inr_chk;
	}
	public void setTtax_amt_inr_chk(Vector ttax_amt_inr_chk) {
		Ttax_amt_inr_chk = ttax_amt_inr_chk;
	}
	public String getTax1() {
		return tax1;
	}
	public void setTax1(String tax1) {
		this.tax1 = tax1;
	}
	public Vector getTaxstr1() {
		return taxstr1;
	}
	public void setTaxstr1(Vector taxstr1) {
		this.taxstr1 = taxstr1;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getState_nm1() {
		return state_nm1;
	}
	public void setState_nm1(String state_nm1) {
		this.state_nm1 = state_nm1;
	}
	public String getSgross_amt_inr_chk() {
		return Sgross_amt_inr_chk;
	}
	public void setSgross_amt_inr_chk(String sgross_amt_inr_chk) {
		Sgross_amt_inr_chk = sgross_amt_inr_chk;
	}
	public String getTax_amt_inr_chk() {
		return tax_amt_inr_chk;
	}
	public void setTax_amt_inr_chk(String tax_amt_inr_chk) {
		this.tax_amt_inr_chk = tax_amt_inr_chk;
	}
	public String getSnet_amt_inr_chk() {
		return snet_amt_inr_chk;
	}
	public void setSnet_amt_inr_chk(String snet_amt_inr_chk) {
		this.snet_amt_inr_chk = snet_amt_inr_chk;
	}
	public String getFax_R() {
		return fax_R;
	}
	public void setFax_R(String fax_R) {
		this.fax_R = fax_R;
	}
	public String getPhone_R() {
		return phone_R;
	}
	public void setPhone_R(String phone_R) {
		this.phone_R = phone_R;
	}
	public String getPin_R() {
		return pin_R;
	}
	public void setPin_R(String pin_R) {
		this.pin_R = pin_R;
	}
	public String getSupp_addr_city_R() {
		return Supp_addr_city_R;
	}
	public void setSupp_addr_city_R(String supp_addr_city_R) {
		Supp_addr_city_R = supp_addr_city_R;
	}
	public String getSupp_addr_nm_R() {
		return Supp_addr_nm_R;
	}
	public void setSupp_addr_nm_R(String supp_addr_nm_R) {
		Supp_addr_nm_R = supp_addr_nm_R;
	}
	public String getCntry_R() {
		return cntry_R;
	}
	public void setCntry_R(String cntry_R) {
		this.cntry_R = cntry_R;
	}
	public Vector getChecked() {
		return Checked;
	}
	public void setChecked(Vector checked) {
		Checked = checked;
	}
	public Vector getApproved() {
		return approved;
	}
	public void setApproved(Vector approved) {
		this.approved = approved;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public String getRemark3() {
		return remark3;
	}
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	public Vector getAbc() {
		return abc;
	}
	public void setAbc(Vector abc) {
		this.abc = abc;
	}
	public Vector getQtyabc() {
		return qtyabc;
	}
	public void setQtyabc(Vector qtyabc) {
		this.qtyabc = qtyabc;
	}
	public Vector getRtabc() {
		return rtabc;
	}
	public void setRtabc(Vector rtabc) {
		this.rtabc = rtabc;
	}
	public String getSgross_amt_usd_chk() {
		return Sgross_amt_usd_chk;
	}
	public void setSgross_amt_usd_chk(String sgross_amt_usd_chk) {
		Sgross_amt_usd_chk = sgross_amt_usd_chk;
	}
	public String getCustpin_R() {
		return Custpin_R;
	}
	public void setCustpin_R(String custpin_R) {
		Custpin_R = custpin_R;
	}
	public String getCust_addr_city_R() {
		return Cust_addr_city_R;
	}
	public void setCust_addr_city_R(String cust_addr_city_R) {
		Cust_addr_city_R = cust_addr_city_R;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getPdf_path() {
		return pdf_path;
	}
	public void setPdf_path(String pdf_path) {
		this.pdf_path = pdf_path;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getUrl_start() {
		return url_start;
	}
	public void setUrl_start(String url_start) {
		this.url_start = url_start;
	}
	public String getSupplier_nm() {
		return supplier_nm;
	}
	public void setSupplier_nm(String supplier_nm) {
		this.supplier_nm = supplier_nm;
	}
	public String getSupplier_cd() {
		return supplier_cd;
	}
	public void setSupplier_cd(String supplier_cd) {
		this.supplier_cd = supplier_cd;
	}
	public Vector getTt1_rate() {
		return tt1_rate;
	}
	public void setTt1_rate(Vector tt1_rate) {
		this.tt1_rate = tt1_rate;
	}
	public Vector getVsac_cd() {return Vsac_cd;	}
	public void setVsac_cd(Vector vsac_cd) {Vsac_cd = vsac_cd;}
	public Vector getVsac_hsn_perc() {return Vsac_hsn_perc;	} //SB20180215
	public Vector getVdesc() {return Vdesc;}
	public void setVdesc(Vector vdesc) {
		Vdesc = vdesc;
	}
	public String getSupp_cd_hid() {
		return supp_cd_hid;
	}
	public void setSupp_cd_hid(String supp_cd_hid) {
		this.supp_cd_hid = supp_cd_hid;
	}
	public String getSac_descr() {
		return sac_descr;
	}
	public void setSac_descr(String sac_descr) {
		this.sac_descr = sac_descr;
	}
	public String getSupplier_cd1() {
		return supplier_cd1;
	}
	public void setSupplier_cd1(String supplier_cd1) {
		this.supplier_cd1 = supplier_cd1;
	}
	public String getSupplier_nm1() {
		return supplier_nm1;
	}
	public void setSupplier_nm1(String supplier_nm1) {
		this.supplier_nm1 = supplier_nm1;
	}
	public Vector getVCqty() {
		return VCqty;
	}
	public void setVCqty(Vector vCqty) {
		VCqty = vCqty;
	}
	public Vector getVCamt() {
		return VCamt;
	}
	public void setVCamt(Vector vCamt) {
		VCamt = vCamt;
	}
	public Vector getVCrate() {
		return VCrate;
	}
	public void setVCrate(Vector vCrate) {
		VCrate = vCrate;
	}
	public Vector getVitm() {
		return Vitm;
	}
	public void setVitm(Vector vitm) {
		Vitm = vitm;
	}
	public String getGate_no() {
		return gate_no;
	}
	public void setGate_no(String gate_no) {
		this.gate_no = gate_no;
	}
	public Vector getCamt() {
		return camt;
	}
	public void setCamt(Vector camt) {
		this.camt = camt;
	}
	public Vector getCrt() {
		return crt;
	}
	public void setCrt(Vector crt) {
		this.crt = crt;
	}
	public String getYr() {
		return yr;
	}
	public void setYr(String yr) {
		this.yr = yr;
	}
	public String getMont() {
		return Mont;
	}
	public void setMont(String mont) {
		Mont = mont;
	}
	public String getMt() {
		return mt;
	}
	public void setMt(String mt) {
		this.mt = mt;
	}
	public String getPdf_flag() {
		return pdf_flag;
	}
	public void setPdf_flag(String pdf_flag) {
		this.pdf_flag = pdf_flag;
	}
	public Vector getVpdf_dtl() {
		return Vpdf_dtl;
	}
	public void setVpdf_dtl(Vector vpdf_dtl) {
		Vpdf_dtl = vpdf_dtl;
	}
	public Vector getPdf() {
		return pdf;
	}
	public void setPdf(Vector pdf) {
		this.pdf = pdf;
	}
	public Vector getPdf1() {
		return pdf1;
	}
	public void setPdf1(Vector pdf1) {
		this.pdf1 = pdf1;
	}
	public String getRule_rmk() {
		return rule_rmk;
	}
	public void setRule_rmk(String rule_rmk) {
		this.rule_rmk = rule_rmk;
	}
	public String getFormatted_rate() {
		return formatted_rate;
	}
	public void setFormatted_rate(String formatted_rate) {
		this.formatted_rate = formatted_rate;
	}
	public String getCst_gstin_no() {
		return cst_gstin_no;
	}
	public void setCst_gstin_no(String cst_gstin_no) {
		this.cst_gstin_no = cst_gstin_no;
	}
	public String getSUPP_state_NM() {
		return SUPP_state_NM;
	}
	public void setSUPP_state_NM(String sUPP_state_NM) {
		SUPP_state_NM = sUPP_state_NM;
	}
	public Vector getForm_rate() {
		return form_rate;
	}
	public void setForm_rate(Vector form_rate) {
		this.form_rate = form_rate;
	}
	public String getState_cust() {
		return state_cust;
	}
	public void setState_cust(String state_cust) {
		this.state_cust = state_cust;
	}
	public Vector getVendor_cd() {
		return Vendor_cd;
	}
	public void setVendor_cd(Vector vendor_cd) {
		Vendor_cd = vendor_cd;
	}
	public Vector getVendor_abbr() {
		return Vendor_abbr;
	}
	public void setVendor_abbr(Vector vendor_abbr) {
		Vendor_abbr = vendor_abbr;
	}
	public Vector getVendor_nm() {
		return Vendor_nm;
	}
	public void setVendor_nm(Vector vendor_nm) {
		Vendor_nm = vendor_nm;
	}
	public Vector getVendor_pan_no() {
		return Vendor_pan_no;
	}
	public void setVendor_pan_no(Vector vendor_pan_no) {
		Vendor_pan_no = vendor_pan_no;
	}
	public Vector getVendor_gstin_no() {
		return Vendor_gstin_no;
	}
	public void setVendor_gstin_no(Vector vendor_gstin_no) {
		Vendor_gstin_no = vendor_gstin_no;
	}
	public Vector getVendor_addr() {
		return Vendor_addr;
	}
	public void setVendor_addr(Vector vendor_addr) {
		Vendor_addr = vendor_addr;
	}
	public Vector getVendor_city() {
		return Vendor_city;
	}
	public void setVendor_city(Vector vendor_city) {
		Vendor_city = vendor_city;
	}
	public Vector getVendor_pin() {
		return Vendor_pin;
	}
	public void setVendor_pin(Vector vendor_pin) {
		Vendor_pin = vendor_pin;
	}
	public Vector getVendor_country() {
		return Vendor_country;
	}
	public void setVendor_country(Vector vendor_country) {
		Vendor_country = vendor_country;
	}
	public Vector getVendor_state() {
		return Vendor_state;
	}
	public void setVendor_state(Vector vendor_state) {
		Vendor_state = vendor_state;
	}
	public Vector getVstate_cd() {
		return Vstate_cd;
	}
	public void setVstate_cd(Vector vstate_cd) {
		Vstate_cd = vstate_cd;
	}
	public String getCust_gstin_no11() {
		return Cust_gstin_no11;
	}
	public void setCust_gstin_no11(String cust_gstin_no11) {
		Cust_gstin_no11 = cust_gstin_no11;
	}
	public String getCustom_cd() {
		return custom_cd;
	}
	public void setCustom_cd(String custom_cd) {
		this.custom_cd = custom_cd;
	}
	public String getSupplier_abbr() {
		return supplier_abbr;
	}
	public void setSupplier_abbr(String supplier_abbr) {
		this.supplier_abbr = supplier_abbr;
	}
	public String getUser_dt() {
		return user_dt;
	}
	public void setUser_dt(String user_dt) {
		this.user_dt = user_dt;
	}
	public String getSet_sac_cd() {
		return set_sac_cd;
	}
	public void setSet_sac_cd(String set_sac_cd) {
		this.set_sac_cd = set_sac_cd;
	}
	public String getSac_desc() {
		return sac_desc;
	}
	public void setSac_desc(String sac_desc) {
		this.sac_desc = sac_desc;
	}
	public Vector getVsupp_cd() {
		return Vsupp_cd;
	}
	public void setVsupp_cd(Vector vsupp_cd) {
		Vsupp_cd = vsupp_cd;
	}
	public Vector getVsupp_nm() {
		return Vsupp_nm;
	}
	public void setVsupp_nm(Vector vsupp_nm) {
		Vsupp_nm = vsupp_nm;
	}
	public Vector getVsupp_abbr() {
		return Vsupp_abbr;
	}
	public void setVsupp_abbr(Vector vsupp_abbr) {
		Vsupp_abbr = vsupp_abbr;
	}
	public Vector getVSupp_state_cd() {
		return VSupp_state_cd;
	}
	public void setVSupp_state_cd(Vector vSupp_state_cd) {
		VSupp_state_cd = vSupp_state_cd;
	}
	public Vector getVsupp_gstin_no() {
		return Vsupp_gstin_no;
	}
	public void setVsupp_gstin_no(Vector vsupp_gstin_no) {
		Vsupp_gstin_no = vsupp_gstin_no;
	}
	public Vector getVsupp_pan_no() {
		return Vsupp_pan_no;
	}
	public void setVsupp_pan_no(Vector vsupp_pan_no) {
		Vsupp_pan_no = vsupp_pan_no;
	}
	public Vector getVvsac_cd() {
		return Vvsac_cd;
	}
	public void setVvsac_cd(Vector vvsac_cd) {
		Vvsac_cd = vvsac_cd;
	}
	public Vector getVvsac_desc() {
		return Vvsac_desc;
	}
	public void setVvsac_desc(Vector vvsac_desc) {
		Vvsac_desc = vvsac_desc;
	}
	public String getFlag_sac() {
		return flag_sac;
	}
	public void setFlag_sac(String flag_sac) {
		this.flag_sac = flag_sac;
	}
	public String getSupp_cd_set() {
		return supp_cd_set;
	}
	public void setSupp_cd_set(String supp_cd_set) {
		this.supp_cd_set = supp_cd_set;
	}
	public String getSale_no() {
		return sale_no;
	}
	public void setSale_no(String sale_no) {
		this.sale_no = sale_no;
	}
	public String getPacer_no() {
		return pacer_no;
	}
	public void setPacer_no(String pacer_no) {
		this.pacer_no = pacer_no;
	}
	public String getVendor_no() {
		return Vendor_no;
	}
	public void setVendor_no(String vendor_no) {
		Vendor_no = vendor_no;
	}
	public Vector getVuam_no() {
		return Vuam_no;
	}
	public void setVuam_no(Vector vuam_no) {
		Vuam_no = vuam_no;
	}
	public String getGST_INVOICE_SEQ_NO1() {
		return GST_INVOICE_SEQ_NO1;
	}
	public void setGST_INVOICE_SEQ_NO1(String gST_INVOICE_SEQ_NO1) {
		GST_INVOICE_SEQ_NO1 = gST_INVOICE_SEQ_NO1;
	}
	public String getCust_seq_no1() {
		return cust_seq_no1;
	}
	public void setCust_seq_no1(String cust_seq_no1) {
		this.cust_seq_no1 = cust_seq_no1;
	}
	public String getInv_seq_no1() {
		return inv_seq_no1;
	}
	public void setInv_seq_no1(String inv_seq_no1) {
		this.inv_seq_no1 = inv_seq_no1;
	}
	public Vector getVflag_sac() {
		return Vflag_sac;
	}
	public void setVflag_sac(Vector vflag_sac) {
		Vflag_sac = vflag_sac;
	}
	public Vector getPdf2() {
		return pdf2;
	}
	public void setPdf2(Vector pdf2) {
		this.pdf2 = pdf2;
	}


	public String getCargo_typ() {
		return cargo_typ;
	}


	public void setCargo_typ(String cargo_typ) {
		this.cargo_typ = cargo_typ;
	}


	public Vector getVcargo_typ() {
		return Vcargo_typ;
	}


	public void setVcargo_typ(Vector vcargo_typ) {
		Vcargo_typ = vcargo_typ;
	}


	public String getCargo_type() {
		return cargo_type;
	}


	public void setCargo_type(String cargo_type) {
		this.cargo_type = cargo_type;
	}


	public String getSvendor_cd() {
		return Svendor_cd;
	}


	public void setSvendor_cd(String svendor_cd) {
		Svendor_cd = svendor_cd;
	}


	public String getSVendor_nm() {
		return SVendor_nm;
	}


	public void setSVendor_nm(String sVendor_nm) {
		SVendor_nm = sVendor_nm;
	}


	public String getVendor_addr1() {
		return Vendor_addr1;
	}


	public void setVendor_addr1(String vendor_addr1) {
		Vendor_addr1 = vendor_addr1;
	}


	public String getEnddt() {
		return enddt;
	}


	public void setEnddt(String enddt) {
		this.enddt = enddt;
	}


	public String getEnd_dt1() {
		return end_dt1;
	}


	public void setEnd_dt1(String end_dt1) {
		this.end_dt1 = end_dt1;
	}


	public String getDt1() {
		return dt1;
	}


	public void setDt1(String dt1) {
		this.dt1 = dt1;
	}


	public String getSt_dt() {
		return st_dt;
	}


	public void setSt_dt(String st_dt) {
		this.st_dt = st_dt;
	}


	public Vector getVnew_seq_no() {
		return Vnew_seq_no;
	}


	public void setVnew_seq_no(Vector vnew_seq_no) {
		Vnew_seq_no = vnew_seq_no;
	}


	public Vector getVgross_inr() {
		return Vgross_inr;
	}


	public void setVgross_inr(Vector vgross_inr) {
		Vgross_inr = vgross_inr;
	}


	public Vector getVgross_usd() {
		return Vgross_usd;
	}


	public void setVgross_usd(Vector vgross_usd) {
		Vgross_usd = vgross_usd;
	}


	public Vector getVtax_inr() {
		return Vtax_inr;
	}


	public void setVtax_inr(Vector vtax_inr) {
		Vtax_inr = vtax_inr;
	}


	public Vector getVnet_inr() {
		return Vnet_inr;
	}


	public void setVnet_inr(Vector vnet_inr) {
		Vnet_inr = vnet_inr;
	}


	public Vector getVhsn_cd() {
		return Vhsn_cd;
	}


	public void setVhsn_cd(Vector vhsn_cd) {
		Vhsn_cd = vhsn_cd;
	}


	public Vector getVset_duedt() {
		return Vset_duedt;
	}


	public void setVset_duedt(Vector vset_duedt) {
		Vset_duedt = vset_duedt;
	}


	public Vector getVset_invdt() {
		return Vset_invdt;
	}


	public void setVset_invdt(Vector vset_invdt) {
		Vset_invdt = vset_invdt;
	}


	public void setVinv_seq_no(Vector vinv_seq_no) {
		Vinv_seq_no = vinv_seq_no;
	}


	public Vector getVCust_nm() {
		return VCust_nm;
	}


	public void setVCust_nm(Vector vCust_nm) {
		VCust_nm = vCust_nm;
	}


	public Vector getVvCust_nm() {
		return VvCust_nm;
	}


	public void setVvCust_nm(Vector vvCust_nm) {
		VvCust_nm = vvCust_nm;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getExch_rt() {
		return exch_rt;
	}


	public void setExch_rt(String exch_rt) {
		this.exch_rt = exch_rt;
	}


	public String getExch_dt() {
		return exch_dt;
	}


	public void setExch_dt(String exch_dt) {
		this.exch_dt = exch_dt;
	}


	public String getAmt() {
		return amt;
	}


	public void setAmt(String amt) {
		this.amt = amt;
	}


	public String getAmt_chk() {
		return amt_chk;
	}


	public void setAmt_chk(String amt_chk) {
		this.amt_chk = amt_chk;
	}


	public String getCustcountry() {
		return Custcountry;
	}


	public void setCustcountry(String custcountry) {
		Custcountry = custcountry;
	}


	public Vector getVvcust_cd() {
		return Vvcust_cd;
	}


	public void setVvcust_cd(Vector vvcust_cd) {
		Vvcust_cd = vvcust_cd;
	}


	public String getScountry() {
		return scountry;
	}


	public void setScountry(String scountry) {
		this.scountry = scountry;
	}


	public Vector getVcriteria() {
		return Vcriteria;
	}


	public void setVcriteria(Vector vcriteria) {
		Vcriteria = vcriteria;
	}


	public Vector getVreason() {
		return Vreason;
	}


	public void setVreason(Vector vreason) {
		Vreason = vreason;
	}


	public Vector getVfinnan_yr() {
		return Vfinnan_yr;
	}


	public void setVfinnan_yr(Vector vfinnan_yr) {
		Vfinnan_yr = vfinnan_yr;
	}


	public Vector getVcont_type() {
		return Vcont_type;
	}


	public void setVcont_type(Vector vcont_type) {
		Vcont_type = vcont_type;
	}


	public String getSnew_inv_sq_no() {
		return Snew_inv_sq_no;
	}


	public void setSnew_inv_sq_no(String snew_inv_sq_no) {
		Snew_inv_sq_no = snew_inv_sq_no;
	}


	public String getSnew_inv_no() {
		return Snew_inv_no;
	}


	public void setSnew_inv_no(String snew_inv_no) {
		Snew_inv_no = snew_inv_no;
	}


	public String getSinv_no() {
		return Sinv_no;
	}


	public void setSinv_no(String sinv_no) {
		Sinv_no = sinv_no;
	}


	public String getCgst() {
		return cgst;
	}


	public void setCgst(String cgst) {
		this.cgst = cgst;
	}


	public String getSgst() {
		return sgst;
	}


	public void setSgst(String sgst) {
		this.sgst = sgst;
	}


	public String getIgst() {
		return igst;
	}


	public void setIgst(String igst) {
		this.igst = igst;
	}


	public Vector getVdr_cr_doc_no() {
		return Vdr_cr_doc_no;
	}


	public void setVdr_cr_doc_no(Vector vdr_cr_doc_no) {
		Vdr_cr_doc_no = vdr_cr_doc_no;
	}


	public String getSdr_cr() {
		return Sdr_cr;
	}


	public void setSdr_cr(String sdr_cr) {
		Sdr_cr = sdr_cr;
	}


	public String getScriteria() {
		return Scriteria;
	}


	public void setScriteria(String scriteria) {
		Scriteria = scriteria;
	}


	public String getSgross_amt() {
		return Sgross_amt;
	}


	public void setSgross_amt(String sgross_amt) {
		Sgross_amt = sgross_amt;
	}


	public String getSdr_cr_no() {
		return Sdr_cr_no;
	}


	public void setSdr_cr_no(String sdr_cr_no) {
		Sdr_cr_no = sdr_cr_no;
	}


	public String getSdr_cr_doc_no() {
		return Sdr_cr_doc_no;
	}


	public void setSdr_cr_doc_no(String sdr_cr_doc_no) {
		Sdr_cr_doc_no = sdr_cr_doc_no;
	}


	public String getSdr_cr_dt() {
		return Sdr_cr_dt;
	}


	public void setSdr_cr_dt(String sdr_cr_dt) {
		Sdr_cr_dt = sdr_cr_dt;
	}


	public String getSdiff_qty() {
		return Sdiff_qty;
	}


	public void setSdiff_qty(String sdiff_qty) {
		Sdiff_qty = sdiff_qty;
	}


	public String getSdiff_rate() {
		return Sdiff_rate;
	}


	public void setSdiff_rate(String sdiff_rate) {
		Sdiff_rate = sdiff_rate;
	}


	public String getSnet_amount() {
		return Snet_amount;
	}


	public void setSnet_amount(String snet_amount) {
		Snet_amount = snet_amount;
	}


	public String getStax_amount() {
		return Stax_amount;
	}


	public void setStax_amount(String stax_amount) {
		Stax_amount = stax_amount;
	}


	public String getSgrs_amount() {
		return Sgrs_amount;
	}


	public void setSgrs_amount(String sgrs_amount) {
		Sgrs_amount = sgrs_amount;
	}


	public String getSgrs_amount_inr() {
		return Sgrs_amount_inr;
	}


	public void setSgrs_amount_inr(String sgrs_amount_inr) {
		Sgrs_amount_inr = sgrs_amount_inr;
	}


	public String getSberth_hsr() {
		return Sberth_hsr;
	}


	public void setSberth_hsr(String sberth_hsr) {
		Sberth_hsr = sberth_hsr;
	}


	public String getSslots_berth() {
		return Sslots_berth;
	}


	public void setSslots_berth(String sslots_berth) {
		Sslots_berth = sslots_berth;
	}


	public String getCR_DR() {
		return CR_DR;
	}


	public void setCR_DR(String cR_DR) {
		CR_DR = cR_DR;
	}


	public Vector getVdr_cr_flag() {
		return Vdr_cr_flag;
	}


	public void setVdr_cr_flag(Vector vdr_cr_flag) {
		Vdr_cr_flag = vdr_cr_flag;
	}


	public String getSdr_cr_fin_yr() {
		return Sdr_cr_fin_yr;
	}


	public void setSdr_cr_fin_yr(String sdr_cr_fin_yr) {
		Sdr_cr_fin_yr = sdr_cr_fin_yr;
	}


	public String getSsupp_nm() {
		return Ssupp_nm;
	}


	public void setSsupp_nm(String ssupp_nm) {
		Ssupp_nm = ssupp_nm;
	}


	public String getSsupp_cd() {
		return Ssupp_cd;
	}


	public void setSsupp_cd(String ssupp_cd) {
		Ssupp_cd = ssupp_cd;
	}


	public Vector getVSupp_cd() {
		return VSupp_cd;
	}


	public void setVSupp_cd(Vector vSupp_cd) {
		VSupp_cd = vSupp_cd;
	}
	public String getSupp_cd_x() {
		return supp_cd_x;
	}
	public void setSupp_cd_x(String supp_cd_x) {
		this.supp_cd_x = supp_cd_x;
	}
//////////////////////SB20161212: Get PDF Template/////////////////////
	Vector Vtable_nm=new Vector();
	Vector Vtable_col_nm=new Vector();
	Vector Vtable_where_col=new Vector();
	public Vector getVtable_nm() {return Vtable_nm;}
	public Vector getVtable_col_nm() {return Vtable_col_nm;}
	public Vector getVtable_where_col() {return Vtable_where_col;}
	String Flag=""; 
	public void TemplateTableList()
	{
		try
		{
			String TemplateType="0";
			String path="";
			System.out.println("READ TableList PARAMETER FILES: ------>>>>>>> "+TemplateType);
			 path = request.getRealPath("/dtbase/");
			 System.out.println(" For LINUX/Windows read files: "+path+"/"); 
			 String docpath ="TableDetail.txt";
			 if(TemplateType.equals("0"))
				 docpath ="TableDetail.txt";
			 else
				 docpath ="TableDetail"+TemplateType+".txt";
		//	 System.out.println("1. For WINDOWS read files: "+path+"\\"+docpath);
			 System.out.println("2. For LINUX/Windows read files: "+path+"/"+docpath); //SB20171107//SB20161129
	//SB20161129: For Windows:		 FileInputStream fstream = new FileInputStream(path+"\\"+docpath);
			 FileInputStream fstream = new FileInputStream(path+"/"+docpath); //SB20161129: For LINUX
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  while ((strLine = br.readLine()) != null) 
			  {
				  String[] st1=strLine.split("=");
				  if(st1[0].equalsIgnoreCase("TABLE")) { Vtable_nm.add(st1[1]);}
				  if(st1[0].equalsIgnoreCase("COLUMN")) { Vtable_col_nm.add(st1[1]);}
				  if(st1[0].equalsIgnoreCase("WHERE")) { Vtable_where_col.add(st1[1]);}
				  ///////////////////Next Table///////////////// 
			  }
			  br.close();
			  in.close();
			  System.out.println("1. TABLE LIST: "+Vtable_nm);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
/////////////////////////////SB20180402/////////////////////
//////////////////////SB20161212: Get PDF Template/////////////////////
	String PageXsize="";String PageYsize="";String PageRow="";String PageColumn=""; String PageCharSize=""; 
	String HeaderColPerc=""; String HeaderCol2Perc=""; String HeaderCol3Perc="";String HeaderNoRow=""; String HeaderBorder=""; String HeaderBorderLine="";
	String FooterColPerc="";String FooterCol2Perc="";String FooterNoRow=""; String FooterBorder=""; String FooterBorderLine="";String PageNoFlag="";
	String BodyStartRow=""; String BodyEndRow=""; String BodyNoCol=""; String BodyColPerc="";  String BodyHeader="";  String BodyDataTotalRow="";
	Integer BodyDataTotalRowCount=0;
	String SubHeaderData="";String SubBody1NoRow=""; String SubBody1Col1Perc="", SubBody1Col2Perc=""; String SubBody2NoRow=""; String SubBody2Col1Perc=""; String SubBody2Col2Perc=""; //SB20170816
	String SubjectNoRow="", SubjectCol1Border="", SubjectCol1Perc="", SubjectCol2Border="", SubjectCol2Perc="";
	String TradeName=""; String TradeNameColor=""; String TradeNameFont="";
	String Header=""; String HeaderAddress=""; String HeaderLogo=""; 
	String SignatureLeft=""; String BlankLineBeforeSign=""; String BlankLineAfterSign=""; String Footer=""; String FooterLogo="";
	String FooterNote1=""; String FooterNote2="";  String Note="";
	String NextPageHeaderBorderLine="";  String NextPageHeader=""; String NextPageHeaderNoRow="";
	String SignatureFlag=""; String SubBody1Flag=""; String SubBody2Flag=""; String SubjectFlag=""; String PriceInWordFlag=""; String RemarkFlag="";
	String PaymentRemarkLine1=""; String PaymentRemarkLine2="";
	public void TemplatePDF()
	{
		try
		{
			//String TemplateType="0";
			String path="";
			System.out.println("READ PDF PARAMETER FILES: ------>>>>>>> "+TemplateType);
			 path = request.getRealPath("/dtbase/");
		
			 String docpath ="TemplatePDF.txt";
			 if(TemplateType.equals("0"))
				 docpath ="TemplatePDF.txt";
			 else
				 docpath ="TemplatePDF"+TemplateType+".txt";
			 System.out.println("1. For WINDOWS read files: "+path+"\\"+docpath);
			 System.out.println("2. For LINUX/Windows read files: "+path+"/"+docpath); //SB20171107//SB20161129
	//SB20161129: For Windows:		 FileInputStream fstream = new FileInputStream(path+"\\"+docpath);
			 FileInputStream fstream = new FileInputStream(path+"/"+docpath); //SB20161129: For LINUX
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  while ((strLine = br.readLine()) != null) 
			  {
				  String[] st1=strLine.split("=");
				  if(st1[0].equalsIgnoreCase("PageXsize")) { PageXsize=st1[1];}
				  if(st1[0].equalsIgnoreCase("PageYsize")) { PageYsize=st1[1];}
				  if(st1[0].equalsIgnoreCase("PageRow")) {  PageRow=st1[1]; }
				  if(st1[0].equalsIgnoreCase("PageColumn")) { PageColumn=st1[1];}
				  if(st1[0].equalsIgnoreCase("PageCharSize")) { PageCharSize=st1[1];}
				  
				  if(st1[0].equalsIgnoreCase("TradeName")) {  TradeName=st1[1]; } //SB20160628
				  if(st1[0].equalsIgnoreCase("TradeNameColor")) {  TradeNameColor=st1[1]; } //SB20160628
				  if(st1[0].equalsIgnoreCase("TradeNameFont")) {  TradeNameFont=st1[1]; } //SB20160628
				  if(st1[0].equalsIgnoreCase("Header")) {  Header=st1[1]; } //SB20160628
				  if(st1[0].equalsIgnoreCase("HeaderAddress")) {  HeaderAddress=st1[1]; } //SB20160628
				  if(st1[0].equalsIgnoreCase("HeaderLogo")) {  HeaderLogo=st1[1]; } //SB20160628
				  if(st1[0].equalsIgnoreCase("HeaderColPerc")){ HeaderColPerc=st1[1];}
				  if(st1[0].equalsIgnoreCase("HeaderCol2Perc")){ HeaderCol2Perc=st1[1];}
				  if(st1[0].equalsIgnoreCase("HeaderCol3Perc")){ HeaderCol3Perc=st1[1];}
				  if(st1[0].equalsIgnoreCase("HeaderNoRow")) {HeaderNoRow=st1[1]; }
				  if(st1[0].equalsIgnoreCase("HeaderBorder")) {HeaderBorder=st1[1]; }
				  if(st1[0].equalsIgnoreCase("HeaderBorderLine")) {HeaderBorderLine=st1[1]; } 	  
				  
				  if(st1[0].equalsIgnoreCase("BodyStartRow")) {	  BodyStartRow=st1[1]; }
				  if(st1[0].equalsIgnoreCase("BodyEndRow")) {  BodyEndRow=st1[1]; } 
				  if(st1[0].equalsIgnoreCase("BodyNoCol")) {  BodyNoCol=st1[1]; } 
				  if(st1[0].equalsIgnoreCase("BodyHeader")) {	  BodyHeader=st1[1]; }
				  if(st1[0].equalsIgnoreCase("BodyDataTotalRow")) 
					  {	  
						  	BodyDataTotalRow=st1[1]; 
					  		BodyDataTotalRowCount=Integer.parseInt(BodyDataTotalRow);
					  }
				  if(st1[0].equalsIgnoreCase("SubBody1Flag")) {  SubBody1Flag=st1[1]; }  //SB20170816 
				  if(st1[0].equalsIgnoreCase("SubHeaderData")) {  SubHeaderData=st1[1]; }  //SB20170816 
				  if(st1[0].equalsIgnoreCase("SubBody1NoRow")) {  SubBody1NoRow=st1[1]; }  //SB20170816
				  if(st1[0].equalsIgnoreCase("SubBody1Col1Perc")) {  SubBody1Col1Perc=st1[1]; }  //SB20170816
				  if(st1[0].equalsIgnoreCase("SubBody1Col2Perc")) {  SubBody1Col2Perc=st1[1]; }  //SB20170816
				  if(st1[0].equalsIgnoreCase("SubBody2Flag")) {  SubBody2Flag=st1[1]; }  //SB20170816 
				  if(st1[0].equalsIgnoreCase("SubBody2NoRow")) {  SubBody2NoRow=st1[1]; }  //SB20170816
				  if(st1[0].equalsIgnoreCase("SubBody2Col1Perc")) {  SubBody2Col1Perc=st1[1]; }  //SB20170816
				  if(st1[0].equalsIgnoreCase("SubBody2Col2Perc")) {  SubBody2Col2Perc=st1[1]; }  //SB20170816
				  if(st1[0].equalsIgnoreCase("SubjectFlag")) {  SubjectFlag=st1[1]; }  //SB20170816 
				  if(st1[0].equalsIgnoreCase("SubjectNoRow")) {  SubjectNoRow=st1[1]; }  //SB20170816 
				  if(st1[0].equalsIgnoreCase("SubjectCol1Border")) {  SubjectCol1Border=st1[1]; }  //SB20170816
				  if(st1[0].equalsIgnoreCase("SubjectCol1Perc")) {  SubjectCol1Perc=st1[1]; }  //SB20170816
				  if(st1[0].equalsIgnoreCase("SubjectCol2Border")) {  SubjectCol2Border=st1[1]; }  //SB20170816
				  if(st1[0].equalsIgnoreCase("SubjectCol2Perc")) {  SubjectCol2Perc=st1[1]; }  //SB20170816  
				  
				  if(st1[0].equalsIgnoreCase("PriceInWordFlag")) {  PriceInWordFlag=st1[1]; }  //SB20170816 
				  if(st1[0].equalsIgnoreCase("RemarkFlag")) {  RemarkFlag=st1[1]; }  //SB20170816 
				  if(st1[0].equalsIgnoreCase("PaymentRemarkLine1")) {  PaymentRemarkLine1=st1[1]; }  //SB20170816 
				  if(st1[0].equalsIgnoreCase("PaymentRemarkLine2")) {  PaymentRemarkLine2=st1[1]; }  //SB20170816 
				  if(st1[0].equalsIgnoreCase("SignatureFlag")) {  SignatureFlag=st1[1]; }  //SB20170816 
				  if(st1[0].equalsIgnoreCase("SignatureLeft")) {  SignatureLeft=st1[1]; } //SB20160628 
				  if(st1[0].equalsIgnoreCase("BlankLineBeforeSign")) {  BlankLineBeforeSign=st1[1]; } //SB20160628
				  if(st1[0].equalsIgnoreCase("BlankLineAfterSign")) {  BlankLineAfterSign=st1[1]; } //SB20160628
				  if(st1[0].equalsIgnoreCase("Note")) {  Note=st1[1]; } //SB20160628
				  if(st1[0].equalsIgnoreCase("Footer")) {  Footer=st1[1]; } //SB20160628
				  if(st1[0].equalsIgnoreCase("FooterNote1")) {  FooterNote1=st1[1]; } //SB20171106
				  if(st1[0].equalsIgnoreCase("FooterNote2")) {  FooterNote2=st1[1]; } //SB20171106
				  if(st1[0].equalsIgnoreCase("FooterLogo")) {  FooterLogo=st1[1]; } //SB20160628
				  if(st1[0].equalsIgnoreCase("FooterColPerc")) {  FooterColPerc=st1[1];}
				  if(st1[0].equalsIgnoreCase("FooterCol2Perc")) {  FooterCol2Perc=st1[1];}
				  if(st1[0].equalsIgnoreCase("FooterNoRow")) {  FooterNoRow=st1[1]; }
				  if(st1[0].equalsIgnoreCase("FooterBorder")) {FooterBorder=st1[1]; }
				  if(st1[0].equalsIgnoreCase("FooterBorderLine")) {FooterBorderLine=st1[1]; } 
				  if(st1[0].equalsIgnoreCase("PageNoFlag")) {  PageNoFlag=st1[1]; } //SB20160628
				  
				  ///////////////////Next Page/////////////////
				  if(st1[0].equalsIgnoreCase("NextPageHeader")) {NextPageHeader=st1[1]; }
				  if(st1[0].equalsIgnoreCase("NextPageHeaderNoRow")) {NextPageHeaderNoRow=st1[1]; } 
				  if(st1[0].equalsIgnoreCase("NextPageHeaderBorderLine")) {NextPageHeaderBorderLine=st1[1]; } 
			
			  }
			  br.close();
			  in.close();
		}
		catch (Exception e) {
			
		}
		
	}
/////////////////////////////SB20161212/////////////////////
	String name=new String();
	String address=new String();
	String path="",txtFile="",txtPath="";
	String fileDate=new String();
	//String BuyerAbr="RCTC";
	
	public String getName() {return name;	}
	public void setName(String name) {	this.name = name;	}
	public String getAddress() {return address;	}
	public void setAddress(String address) {this.address = address;	}
	public String getPath() {return path;	}
	public void setPath(String path) {	this.path = path;}
	
	String createFile(String BuyerAbr, String typeofnotice,String InvoiceNo, String FileNmExt) throws IOException
	{
		try 
		{
			HttpSession sess = request.getSession();
			path = request.getSession().getServletContext().getRealPath("/pdf_reports/other_invoices").toString(); //SB20161209
			String datesplit[]=fileDate.split("/");
			String gendate="",lastnoticedt="";
			for(int i=0;i<datesplit.length;i++)
			{
				if(gendate.equalsIgnoreCase(""))
				{
					gendate=datesplit[i];
				}
				else
				{
					gendate=gendate+datesplit[i];
				}
			}
			System.out.println(SuppAbr+" 1. READ Path FILES: ------>>>>>>>"+path);
			File lst_qtr= new File(path+"/"+SuppAbr); //SB20161209: LINUX+WINDOWS
			if(!(lst_qtr.exists()))
			{
				lst_qtr.mkdirs();
			}
			FileNmExt=FileNmExt+".pdf";
		///	String fileName = BuyerAbr+"_"+year+"_"+month+"_"+flag+"_"+gendate+".pdf";
			String fileName = typeofnotice+"-"+BuyerAbr+"-"+InvoiceNo+"-"+FileNmExt; //SB20171025
	
			System.out.println("READ Path FILES: ------>>>>>>>"+path);
			path = lst_qtr+"/"+ fileName; //SB20161203: For Linux
			String p=request.getRealPath("/print/printown_acfiles"); //SB20161209
//			SB20161203 File lst_qtr1 = new File(p+"//"+owncd);
			File lst_qtr1 = new File(p+"/"+SuppAbr);
			if(!(lst_qtr1.exists()))
			{
				lst_qtr1.mkdirs();
			}
			FileNmExt=FileNmExt+".txt";
		///	 txtFile=BuyerAbr+"_"+year+"_"+month+"_"+flag+"_"+gendate+".txt";
			 txtFile=typeofnotice+"-"+BuyerAbr+"-"+InvoiceNo+"-"+FileNmExt; //SB20171025
			 txtPath=lst_qtr1 +"/" + txtFile; //SB20161209
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in DataBean_Ban_Notice_Format_Pdf-->createFile()"+e.getMessage());
			e.printStackTrace();
		}
		return path;
	}
	
	/////////////////////////////////////////////
	public void pdf_textNew(String BuyerAbr, String noticename,String InvNo, String SubHeader,String SubHeader2, String SubHeader3, String SubjectLeft, String SubjectRight, String Body,String Signature,String PaymentRemark,String Rule,String notice,String sent_notice_dt,String last_notice, Integer Lines, String InvFileNmExt)
	{
		System.out.println("1. Page :"+PageXsize);
		System.out.println("1a. Page :"+PageYsize);
		System.out.println("2. PageRow :"+PageRow);
		System.out.println("3. PageColumn :"+PageColumn);
		System.out.println("3a. PageCharSize :"+PageCharSize);
		
	
		System.out.println("8. BodyStartRow :"+BodyStartRow);
		System.out.println("8a. SubBody1NoRow :"+SubBody1NoRow); //SB20170816
		System.out.println("8b. SubBody1Col1Perc :"+SubBody1Col1Perc); //SB20170816
		System.out.println("8b1. SubBody1Col2Perc :"+SubBody1Col2Perc); //SB20170816
		System.out.println("8c. SubBody2NoRow :"+SubBody2NoRow); //SB20170816
		System.out.println("8d. SubBody2Col1Perc :"+SubBody2Col1Perc); //SB20170816
		System.out.println("8e. SubBody2Col2Perc :"+SubBody2Col2Perc); //SB20170816
		System.out.println("8f. SubjectNoRow :"+SubjectNoRow); //SB20170816
		System.out.println("8g. SubjectCol1Perc :"+SubjectCol1Perc); //SB20170816
		System.out.println("8h. SubjectCol2Perc :"+SubjectCol2Perc); //SB20170816
		
		System.out.println("9. BodyEndRow :"+BodyEndRow);
		System.out.println("10. HEADER :"+Header);
		System.out.println("10. HEADER :"+HeaderAddress);
	
		
		Document document = new Document(PageSize.A4,4, 4, 4, 4);
			try
			{
				//System.out.println(BuyerAbr+" :InvNo :"+InvNo);System.out.println(" :InvFileNmExt :"+InvFileNmExt);
			String fileName = createFile(BuyerAbr, noticename,InvNo, InvFileNmExt); System.out.println("File Name :"+fileName);
			PdfPCell cell, cellPage, cellHeader;
			PdfPTable BlankLines, LogoTableHeader,SubTableHeader1,SubTableHeader2,SubjectTableLeft,SubjectTableRight,BodyTable,BodyTable4,BodyTableDtl,BodyTableDtl4ColNextPage, BodyTableDtl6ColNextPage, BodyTableDtl4,PriceInWord,Payment,Sign,LogoTableFooter,SAC_HSN,table,tablePageNo, NextPageHeaderTable;
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
			//////////////SB//////////////////////////////
			 System.out.println("1. TradeNameFont :"+TradeNameFont);  
			 System.out.println("2. TradeNameColor :"+TradeNameColor);
			
			Font small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, Integer.parseInt(PageCharSize), Font.NORMAL, new Color(0x00, 0x00, 0x00));
			Font small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, Integer.parseInt(PageCharSize), Font.BOLD, new Color(0x00, 0x00, 0x00));
			Font big_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(0x00, 0x00, 0x00));
			Font big_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new Color(0x00, 0x00, 0x00));
			Font big24_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 24, Font.BOLD, new Color(0x00, 0x00, 0x00));
			Font verysmall_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0x00, 0x00, 0x00));
			Font font24 = FontFactory.getFont(TradeNameFont,24,Font.BOLDITALIC,Color.getColor(TradeNameColor));
		//	Font small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new Color(0xCC, 0x00, 0x00));
         //   Font small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
            Integer NoBlankLines=0;
            Integer PageX=Integer.parseInt(PageXsize);
            Integer PageY=Integer.parseInt(PageYsize);
            Integer MaxLines=Integer.parseInt(PageRow);
            float WidthPerc = Float.parseFloat(PageColumn);
            Integer NoOfHeaderLines=Integer.parseInt(HeaderNoRow);//System.out.println("1)  NoOfHeaderLines :"+NoOfHeaderLines);//System.out.println("11. HeaderNoRow :"+HeaderNoRow);	System.out.println("12. FooterNoRow :"+FooterNoRow);
            Integer NoOfFooterLines=Integer.parseInt(FooterNoRow); 
            float HeaderColFloat = Float.parseFloat(HeaderColPerc)/100;
            float HeaderCol2Float = Float.parseFloat(HeaderCol2Perc)/100;
            float HeaderCol3Float = Float.parseFloat(HeaderCol3Perc)/100;
            
        //	System.out.println("12. FooterNoRow :"+FooterNoRow);
            
            Integer BodyStartLine=Integer.parseInt(BodyStartRow);
       /////     Integer BodyDataTotalRowCount=Integer.parseInt(BodyDataTotalRow);
            Integer SubBody1NoRowCount=Integer.parseInt(SubBody1NoRow); //SB20170816
            Integer SubBody2NoRowCount=Integer.parseInt(SubBody2NoRow); //SB20170816
            float SubBody1Col1Float = Float.parseFloat(SubBody1Col1Perc)/100; //SB20170816
            float SubBody1Col2Float = Float.parseFloat(SubBody1Col2Perc)/100; //SB20170816
            float SubBody2Col1Float = Float.parseFloat(SubBody2Col1Perc)/100; //SB20170816
            float SubBody2Col2Float = Float.parseFloat(SubBody2Col2Perc)/100; //SB20170816
            Integer SubjectNoRowCount=Integer.parseInt(SubjectNoRow); //SB20170816
            float SubjectCol1Float = Float.parseFloat(SubjectCol1Perc)/100; //SB20170816
            float SubjectCol2Float = Float.parseFloat(SubjectCol2Perc)/100; //SB20170816 
            Integer BodyEndLine=Integer.parseInt(BodyEndRow);
            Integer BlankLineBeforeSignCount=Integer.parseInt(BlankLineBeforeSign);
            Integer BlankLineAfterSignCount=Integer.parseInt(BlankLineAfterSign);
            Integer BodyColSize=Integer.parseInt(BodyNoCol); 
            Integer NextPageHeaderNoRowCount=Integer.parseInt(NextPageHeaderNoRow);
            Integer CurrentLines=0; 
			boolean flag_DCB=true;
			Rectangle pageSize = new Rectangle(PageX, PageY);
			Rectangle pageSize1=new Rectangle(842,595);
			if(flag_DCB==true)
			{
				pageSize.setBackgroundColor(new java.awt.Color(0xe3f8c0)); 
				pageSize1.setBackgroundColor(new java.awt.Color(0xe3f8c0)); 
			}
			else
			{
				pageSize.setBackgroundColor(new java.awt.Color(0xffffff)); 
				pageSize1.setBackgroundColor(new java.awt.Color(0xffffff)); 
			}
			
			 	PdfPTable headingTable = new PdfPTable(1);
	            headingTable.setWidthPercentage(WidthPerc);
	            headingTable.getDefaultCell().setBorder(Rectangle.BOX);
	            headingTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	            headingTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	            //////////////////////////////////////////////////////////////////////
				document.addTitle("DLNG");
				document.addSubject("Generate PDF reports");
				document.addCreator("BIPL");
				document.addAuthor("DLNG@BIPL");
				document.open();
//				SB	document.setPageSize(PageSize.A4);
				document.setPageSize(pageSize);
				document.newPage();

			
	///////1. Header with LOGO/////////////////
			String context_nm = request.getContextPath();
			String server_nm = request.getServerName();
			String server_port = ""+request.getServerPort();
			String url_start = "";
			url_start = "http://"+server_nm+":"+server_port+context_nm;
			//System.out.println("this is the pic path  "+url_start+"\\images\\LOGO\\HLPL_Logo.jpg");
            Image logo_header = Image.getInstance(url_start+"//images//logo.png");
            logo_header.scaleAbsolute(80,60);
            PdfPCell logo_header_cell = new PdfPCell(logo_header,false);
            logo_header_cell.setBorder(Rectangle.NO_BORDER);
            
       /*     System.out.println("A. Start of HeaderLines : "+CurrentLines);
            System.out.println("A.1. HeaderColPerc :"+HeaderColPerc);
    		System.out.println("A.2. HeaderCol2Perc :"+HeaderCol2Perc);
    		System.out.println("A.3. HeaderCol3Perc :"+HeaderCol3Perc);
    		System.out.println("A.4. HeaderNoRow :"+HeaderNoRow);
    		System.out.println("A.5. HeaderBorder :"+HeaderBorder);
    		System.out.println("A.6. HeaderBorderLine :"+HeaderBorderLine);
    	*/	
            if(HeaderColFloat<1)
            {
              float[] FieldsWidthsHeader = {HeaderColFloat, HeaderCol2Float, HeaderCol3Float};
	          LogoTableHeader = new PdfPTable(FieldsWidthsHeader);
            }
            else
            {
            	 float[] FieldsWidthsHeader = {HeaderColFloat};
     	         LogoTableHeader = new PdfPTable(FieldsWidthsHeader);
            }
	          	 if(HeaderBorder.equals("Y"))
	          	 {
	             	LogoTableHeader.getDefaultCell().setBorder(Rectangle.BOX);
	             	logo_header_cell.setBorder(Rectangle.BOX);
	          	 }
	             else
	             {
	             	LogoTableHeader.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	             	logo_header_cell.setBorder(Rectangle.NO_BORDER);
	             }
	        if(HeaderColFloat==1)
	            {      	 
	            LogoTableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            LogoTableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            LogoTableHeader.addCell(new Phrase(new Chunk(Header,small_black_normal)));
	            CurrentLines++;
	            }
	        else if(HeaderColFloat<1)
	        {	        	          
	           
	            LogoTableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	            LogoTableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);		
	            if(HeaderLogo.equals("Y"))
	            	LogoTableHeader.addCell(logo_header_cell);
	            else
	            	LogoTableHeader.addCell(new Phrase(new Chunk("")));
	            
	            LogoTableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		        LogoTableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		        LogoTableHeader.addCell(new Phrase(new Chunk(Header,font24)));
		        
		        LogoTableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		        LogoTableHeader.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		        LogoTableHeader.addCell(new Phrase(new Chunk(HeaderAddress,small_black_normal)));
		         
	            CurrentLines++;
            }
			System.out.println(NoOfHeaderLines+" : 1. HeaderLines : "+CurrentLines);
			
			/////////////SB20171122 Sub-Header: Initialize/Get-Data //////////////////
			if(!SubHeaderData.equals("") ) 
				  SubHeader=SubHeaderData;
			/////////////////////////////////////////////////////////////
			
			table = new PdfPTable(1);
			table.setWidthPercentage(WidthPerc);
			table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			
			int NoSubHeaderLine=0;
			String SubHeaderLine[]=SubHeader.split("~");
			if(SubHeaderLine.length>0) {
				NoSubHeaderLine=SubHeaderLine.length;
			}
			
			///////////////////SubHeader1: ////////////////////////////////
			  if(SubBody1Col1Float<1)
	            {
	              float[] FieldsWidthsHeader = {SubBody1Col1Float, SubBody1Col2Float};
	              SubTableHeader1 = new PdfPTable(FieldsWidthsHeader);
	            }
	            else
	            {
	            	 float[] FieldsWidthsHeader = {SubBody1Col1Float};
	            	 SubTableHeader1 = new PdfPTable(FieldsWidthsHeader);
	            }
			  SubTableHeader1.setWidthPercentage(WidthPerc);
			  SubTableHeader1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			  /////////////////////////////////////////////////////////////////////////
	//		  System.out.println(NoOfHeaderLines+" : 2. HeaderLines : "+CurrentLines);
			  if(HeaderBorderLine.equals("Y") )
				 {
							cellHeader=new PdfPCell(new Paragraph("\n" ,small_black_normal));
							cellHeader.setBorder(1);
							cellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
							cellHeader.setVerticalAlignment(Element.ALIGN_LEFT);
							SubTableHeader1.addCell(cellHeader);
							CurrentLines++;
				 }
			  int PageHeaderBlankLine=0;
			  PageHeaderBlankLine =NoOfHeaderLines-CurrentLines;
			  System.out.println(NoOfHeaderLines+" : 3. HeaderLines : "+CurrentLines);
				while(PageHeaderBlankLine>0)
				{
		//			cell=new PdfPCell(new Paragraph(CurrentLines+"\n" ,small_black_normal));
					cellHeader=new PdfPCell(new Paragraph("\n" ,small_black_normal));
					cellHeader.setBorder(0);
					cellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
					cellHeader.setVerticalAlignment(Element.ALIGN_LEFT);
					LogoTableHeader.addCell(cellHeader);
					PageHeaderBlankLine--;
					CurrentLines++;
				}
				////////////////////////////////////////////////////////////
		
				int SubBody1Count=SubBody1NoRowCount;
			  while(SubBody1Count>0)
				{
				  		
					for(int i=0; i<NoSubHeaderLine; i++)
					{
						if(SubBody1Col1Float==1)
			            {      	 
						
			        	SubTableHeader1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	SubTableHeader1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			        	SubTableHeader1.addCell(new Phrase(new Chunk(SubHeaderLine[i],big_black_bold)));
			            CurrentLines +=3;
			            }
			        else if(SubBody1Col1Float<1)
			        {
			        	
			        	SubTableHeader1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	SubTableHeader1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			        	SubTableHeader1.addCell(new Phrase(new Chunk(SubHeaderLine[i],big_black_normal)));
				            
			        	SubTableHeader1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			        	SubTableHeader1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);		
			        	SubTableHeader1.addCell(new Phrase(new Chunk("DATE:",small_black_normal)));
			            CurrentLines +=3;
		            }
					
						SubBody1Count--;
					CurrentLines++;
					}
					NoSubHeaderLine=0;
					
				}    
		//	  System.out.println("C. END of SubHeader : "+CurrentLines);  
		     //////////////////////GAP - 2Lines /////////////////////////////////////  
			  cell=new PdfPCell(new Paragraph("\n\n" ,font1));
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_LEFT);
				SubTableHeader1.addCell(cell);
				NoBlankLines +=2;
				CurrentLines +=2;
			///////////////////SubHeader2: ////////////////////////////////
			  if(SubBody2Col1Float<1)
	            {
	              float[] FieldsWidthsHeader = {SubBody2Col1Float, SubBody2Col2Float};
		          SubTableHeader2 = new PdfPTable(FieldsWidthsHeader);
	            }
	            else
	            {
	            	 float[] FieldsWidthsHeader = {SubBody2Col1Float};
	            	 SubTableHeader2 = new PdfPTable(FieldsWidthsHeader);
	            }
			  SubTableHeader2.setWidthPercentage(WidthPerc);
			  SubTableHeader2.getDefaultCell().setBorder(Rectangle.NO_BORDER);  
				
		        if(SubBody2Col1Float==1)
		            {      	 
		        	SubTableHeader2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		        	SubTableHeader2.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		        	SubTableHeader2.addCell(new Phrase(new Chunk(SubHeader2,small_black_normal)));
		            CurrentLines +=10;
		            }
		        else if(SubBody2Col1Float<1)
		        {
		        	SubTableHeader2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		        	SubTableHeader2.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		        	SubTableHeader2.addCell(new Phrase(new Chunk(SubHeader2,small_black_normal)));
			            
		        	SubTableHeader2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		        	SubTableHeader2.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);		
		        	SubTableHeader2.addCell(new Phrase(new Chunk(SubHeader3,small_black_normal)));
		            CurrentLines +=10;
	            }
		        //////////////////////GAP - 2Lines /////////////////////////////////////  
				  cell=new PdfPCell(new Paragraph("\n\n" ,font1));
					cell.setBorder(0);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setVerticalAlignment(Element.ALIGN_LEFT);
					SubTableHeader2.addCell(cell);
					NoBlankLines +=2;
					CurrentLines +=2;
		     ///////////////////////////////////////////////////////////  
		 //       System.out.println("D. START of SubHeader2 : "+CurrentLines);  
		        ///////////////////SubjectLeft: ////////////////////////////////
		   
		        int NoSubjectLineLeft=0;  int NoSubjectLineRight=0;
				String SubjectLineLeft[]=SubjectLeft.split("\n");
				if(SubjectLineLeft.length>0) {
					NoSubjectLineLeft=SubjectLineLeft.length;
				}
				String SubjectLineRight[]=SubjectRight.split("\n");
				if(SubjectLineRight.length>0) {
					NoSubjectLineRight=SubjectLineRight.length;
				}

				  if(SubjectCol1Float!=0 && SubjectCol1Float<1)
		            {
		              float[] FieldsWidthsHeader = {SubjectCol1Float-0.2f,0.2f, SubjectCol2Float};
		              SubjectTableLeft = new PdfPTable(FieldsWidthsHeader);
		             // SubjectTableLeft.setWidthPercentage(40);
		              for(int i=0; i<NoSubjectLineLeft; i++)
						{		            	  
		            	  	if(SubjectCol1Border.equals("Y") )
		            	  		SubjectTableLeft.getDefaultCell().setBorder(Rectangle.BOX);
		            	  	else
		            	  		SubjectTableLeft.getDefaultCell().setBorder(Rectangle.NO_BORDER);
							SubjectTableLeft.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					        SubjectTableLeft.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
					        SubjectTableLeft.addCell(new Phrase(new Chunk(SubjectLineLeft[i],small_black_normal)));
					       
					        SubjectTableLeft.getDefaultCell().setBorder(Rectangle.NO_BORDER);
							SubjectTableLeft.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					        SubjectTableLeft.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
					        SubjectTableLeft.addCell(new Phrase(new Chunk("",small_black_normal)));
					        
					        if(SubjectCol2Border.equals("Y") )
					        	SubjectTableLeft.getDefaultCell().setBorder(Rectangle.BOX);
					        else
					        	SubjectTableLeft.getDefaultCell().setBorder(Rectangle.NO_BORDER);
							SubjectTableLeft.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
							SubjectTableLeft.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);		
							SubjectTableLeft.addCell(new Phrase(new Chunk(SubjectLineRight[i],small_black_normal)));
						}
		             // SubjectTableRight = new PdfPTable(FieldsWidthsHeader);
			
		              CurrentLines +=2;
		            }
		            else if(SubjectCol1Float==1)
		            {
		            	 float[] FieldsWidthsHeader = {SubjectCol1Float};
		            	 SubjectTableLeft = new PdfPTable(FieldsWidthsHeader);
		            		for(int i=0; i<NoSubjectLineLeft; i++)
							{
		            			if(SubjectCol1Border.equals("Y") )
			            	  		SubjectTableLeft.getDefaultCell().setBorder(Rectangle.BOX);
			            	  	else
			            	  		SubjectTableLeft.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            		///	SubjectTableLeft.getDefaultCell().setBorder(Rectangle.BOX);
								SubjectTableLeft.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
								SubjectTableLeft.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
								SubjectTableLeft.addCell(new Phrase(new Chunk(SubjectLineLeft[i],small_black_normal)));
								
								SubjectTableLeft.getDefaultCell().setBorder(Rectangle.NO_BORDER);
								SubjectTableLeft.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
							    SubjectTableLeft.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
							    SubjectTableLeft.addCell(new Phrase(new Chunk("",small_black_normal)));
								
							}
		            		 CurrentLines +=2;
		            }
		            else if(SubjectCol2Float==1)
		            {
		            	/// float[] FieldsWidthsHeader = {SubjectCol2Float};
		            	 float[] FieldsWidthsHeader = {0.70f,0.30f};
		            	 SubjectTableLeft = new PdfPTable(FieldsWidthsHeader);
		            		for(int i=0; i<NoSubjectLineRight; i++)
							{
		            			SubjectTableLeft.getDefaultCell().setBorder(Rectangle.NO_BORDER);
								SubjectTableLeft.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
						        SubjectTableLeft.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
						        SubjectTableLeft.addCell(new Phrase(new Chunk("",small_black_normal)));
						        
						        if(SubjectCol2Border.equals("Y") )
						        	SubjectTableLeft.getDefaultCell().setBorder(Rectangle.BOX);
						        else
						        	SubjectTableLeft.getDefaultCell().setBorder(Rectangle.NO_BORDER);
						        ///SubjectTableLeft.getDefaultCell().setBorder(Rectangle.BOX);
								SubjectTableLeft.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
								SubjectTableLeft.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
								SubjectTableLeft.addCell(new Phrase(new Chunk(SubjectLineRight[i],small_black_normal)));
							}
		            		 CurrentLines +=2;
		            }
		            else 
		            {
		            	 float[] FieldsWidthsHeader = {SubjectCol1Float};
		            	 SubjectTableLeft = new PdfPTable(FieldsWidthsHeader);
		            		for(int i=0; i<NoSubjectLineLeft; i++)
							{
		            			if(SubjectCol1Border.equals("Y") )
			            	  		SubjectTableLeft.getDefaultCell().setBorder(Rectangle.BOX);
			            	  	else
			            	  		SubjectTableLeft.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            			///SubjectTableLeft.getDefaultCell().setBorder(Rectangle.BOX);
								SubjectTableLeft.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
								SubjectTableLeft.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
								SubjectTableLeft.addCell(new Phrase(new Chunk(SubjectLineLeft[i],small_black_normal)));
								
								SubjectTableLeft.getDefaultCell().setBorder(Rectangle.NO_BORDER);
								SubjectTableLeft.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
							    SubjectTableLeft.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
							    SubjectTableLeft.addCell(new Phrase(new Chunk("",small_black_normal)));
								
							}
		            		 CurrentLines +=2;
		            }
			//	  System.out.println("END of SubHeader2 : "+CurrentLines);  
				  //////////////////////////////For 6-Col Body Header/////////////////////////////  
				  CurrentLines=6;
				  if(SubBody1Flag.equals("Y"))
					{		
					  CurrentLines +=SubBody1NoRowCount; 
						CurrentLines +=2;
					}
					if(SubBody2Flag.equals("Y"))
					{
						CurrentLines +=(SubBody2NoRowCount/2);   
						CurrentLines +=1;
						if(PriceInWordFlag.equals("Y"))
						{
							CurrentLines +=1;
						}
					}
					if(SubjectFlag.equals("Y"))
					{
						CurrentLines +=(SubjectNoRowCount/2);
						CurrentLines +=2; 
					}
		//BODY ******* //////////////////////////////////////////////////////////////////////
		System.out.println("****** START of Body Line : "+CurrentLines);  
		/////////////SB20171122 -BodyHeader: Initialize/Get-Data //////////////////
		float[] BillingFieldsDetailsWidths_7 = {0.10f,0.10f,0.30f,0.10f,0.10f,0.10f,0.10f};		 
		BodyTable = new PdfPTable(BillingFieldsDetailsWidths_7);
		BodyTable.setWidthPercentage(WidthPerc); 
		BodyTable.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		float[] BillingFieldsDetailsWidths_4 = {0.10f,0.10f,0.55f,0.15f};		 
		BodyTable4 = new PdfPTable(BillingFieldsDetailsWidths_4);
		BodyTable4.setWidthPercentage(WidthPerc); 
		BodyTable4.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		if((BodyColSize)==7) 
	 	{
			
			
			System.out.println("B.     BodyHeader :"+BodyHeader);
			if(!BodyHeader.equals("") ) 
			{
				int NoBodyHeaderLine=0;
				String BodyHeaderLine[]=BodyHeader.split("~");
				if(BodyHeaderLine.length>0) 
				{
					NoBodyHeaderLine=BodyHeaderLine.length;
				}
				for(int i=0; i<NoBodyHeaderLine; i++)
				{	
					BodyTable.getDefaultCell().setBorder(Rectangle.BOX);
		            BodyTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            BodyTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		            BodyTable.addCell(new Phrase(new Chunk(BodyHeaderLine[i],small_black_bold)));
		        //    System.out.println(i+") B.     BodyHeader : "+BodyHeaderLine[i]);
		            CurrentLines +=2;  
				}
			}
		/////////////////////////////////////	
			else if(BodyHeader.equals("") ) 
			{	
						  	BodyTable.getDefaultCell().setBorder(Rectangle.BOX);
				            BodyTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				            BodyTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
				            BodyTable.addCell(new Phrase(new Chunk("Sr No.",small_black_bold)));
				            
				            BodyTable.getDefaultCell().setBorder(Rectangle.BOX);
				            BodyTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				            BodyTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			            if(flag_sac.equalsIgnoreCase("P")) {
			            	BodyTable.addCell(new Phrase(new Chunk("HSN Code",small_black_bold)));
			            }else{
			            	BodyTable.addCell(new Phrase(new Chunk("SAC Code",small_black_bold)));
			            }
			            if(flag_sac.equalsIgnoreCase("P") || flag_sac.equalsIgnoreCase("S")) 
			            {
				            BodyTable.getDefaultCell().setBorder(Rectangle.BOX);
				            BodyTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				            BodyTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
				            BodyTable.addCell(new Phrase(new Chunk("Full Particulars",small_black_bold)));
				            
				            BodyTable.getDefaultCell().setBorder(Rectangle.BOX);
				            BodyTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				            BodyTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
				            BodyTable.addCell(new Phrase(new Chunk("UOM",small_black_bold)));
				            
				            BodyTable.getDefaultCell().setBorder(Rectangle.BOX);
				            BodyTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				            BodyTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
				            BodyTable.addCell(new Phrase(new Chunk("Quantity",small_black_bold)));
				            
				            BodyTable.getDefaultCell().setBorder(Rectangle.BOX);
				            BodyTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				            BodyTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
				            BodyTable.addCell(new Phrase(new Chunk("Rate Per Qty",small_black_bold)));
				            
				            BodyTable.getDefaultCell().setBorder(Rectangle.BOX);
				            BodyTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				            BodyTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
				            BodyTable.addCell(new Phrase(new Chunk("Amount (Rs)",small_black_bold)));
				            CurrentLines +=2;
			            }
			}
	 	} 
		else if((BodyColSize)==4) 
	 	{
		            //////////////////////For 4-col Body///////////////////////////////////////////////
		            	
							System.out.println("C.     BodyHeader :"+BodyHeader);
					if(!BodyHeader.equals("") ) 
					{
								int NoBodyHeaderLine=0;
								String BodyHeaderLine[]=BodyHeader.split("~");
								if(BodyHeaderLine.length>0) 
								{
									NoBodyHeaderLine=BodyHeaderLine.length;
								}
								for(int i=0; i<NoBodyHeaderLine; i++)
								{	
									BodyTable4.getDefaultCell().setBorder(Rectangle.BOX);
									BodyTable4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
									BodyTable4.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
									BodyTable4.addCell(new Phrase(new Chunk(BodyHeaderLine[i],small_black_bold)));
						        //    System.out.println(i+") B.     BodyHeader : "+BodyHeaderLine[i]);
						            CurrentLines +=2;  
								}
					}
					else if(BodyHeader.equals("") ) 
					{				
							BodyTable4.getDefaultCell().setBorder(Rectangle.BOX);
				            BodyTable4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				            BodyTable4.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
				            BodyTable4.addCell(new Phrase(new Chunk("Sr No.",small_black_bold)));
				            
				            BodyTable4.getDefaultCell().setBorder(Rectangle.BOX);
				            BodyTable4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				            BodyTable4.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
				            if(flag_sac.equalsIgnoreCase("P")) {
				            	BodyTable4.addCell(new Phrase(new Chunk("HSN Code",small_black_bold)));
				            }else{
				            	BodyTable4.addCell(new Phrase(new Chunk("SAC Code",small_black_bold)));
				            }
				            if(flag_sac.equalsIgnoreCase("S") || flag_sac.equalsIgnoreCase("P") ) 
				            {
				            	BodyTable4.getDefaultCell().setBorder(Rectangle.BOX);
					            BodyTable4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					            BodyTable4.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
					            BodyTable4.addCell(new Phrase(new Chunk("Particulars",small_black_bold)));
					            
					            
					            
					            BodyTable4.getDefaultCell().setBorder(Rectangle.BOX);
					            BodyTable4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					            BodyTable4.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
					            BodyTable4.addCell(new Phrase(new Chunk("Amount (Rs)",small_black_bold)));
					            CurrentLines +=2;
				            }
					}
	 	}
				   //////////////////////^For 4-col Body///////////////////////////////////////////////
////////////////////Body Data --7 cols///////////////////////
		            int NoBodyLine=0;   int NoBodyTemp=0; int NoOfNextPage=1; int NoOfBodyLinesInNextPage=0; int NextPageHeaderLines=0;
		            	
		            float[] FieldsWidthsNextPageHeader = {HeaderColFloat};
	       	       
		            String BodyTemp[]=Body.split("\n");
					if(BodyTemp.length>0) {
						NoBodyTemp=BodyTemp.length;
					}	
					int TotalNoOfPage = 1+(NoBodyTemp)/(MaxLines-10); 
					
					 System.out.println(BodyEndLine+" STEP-1: Start of Body Line-7 : "+(CurrentLines));
										
					 float[] BillingFieldsDetailsWidths = {0.10f,0.10f,0.30f,0.10f,0.10f,0.10f,0.10f};
					 BodyTableDtl = new PdfPTable(BillingFieldsDetailsWidths);
					 int  NoOfBodyLinesCounted=CurrentLines+SubBody1NoRowCount+NoOfFooterLines;
					 NoOfBodyLinesInNextPage = NoOfBodyLinesCounted;
					 int kk=0;
					 for(int k=0; k<NoBodyTemp; k++)
					 {
						 String BodyLine[]=BodyTemp[k].split("~");
							if(BodyLine.length>0) {
								NoBodyLine=BodyLine.length;
							}
		              for(int i=0; i<NoBodyLine; i++)
						{
		          //  	  System.out.println(NoBodyLine+" E1. Start of Body : "+BodyLine[i]); 
		            	  if(i<2)
		            		  BodyTableDtl.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            	  else if(i==2)
		            		  BodyTableDtl.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            	  else if(i==3 || i==4)
		            		  BodyTableDtl.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            	  else
		            		  BodyTableDtl.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		            	  BodyTableDtl.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            	  if(BodyLine[i].endsWith("Gross Amount") || BodyLine[i].endsWith("Total GST") || BodyLine[i].endsWith("Total Invoice Amount in INR") || BodyLine[i].endsWith("Net Amount Payable in INR")) 
		            	  {
		            		  BodyTableDtl.addCell(new Phrase(new Chunk(BodyLine[i],small_black_bold)));//System.out.println(NoBodyLine+" BOLD : "+BodyLine[i]); 
		            	  }
		            	  else
		            	  {
		            		  BodyTableDtl.addCell(new Phrase(new Chunk(BodyLine[i],small_black_normal))); //System.out.println(NoBodyLine+" NORMAL : "+BodyLine[i]); 
		            	  }					    
						}
		              CurrentLines +=1; kk++;
		              NoOfBodyLinesCounted++;
		            
		              if((NoOfBodyLinesCounted)>=(BodyEndLine))
		              {
		            	  //////////////////Next Page-Header/////////////////////////		      			 		
		      			 System.out.println(kk+" : Break in Body-Lines : "+(CurrentLines));
		      			NoOfNextPage = 2;
		      			///////////////////////////////////////////////////////////
			            	  break;
		              }		             
					 }
					  System.out.println(NoOfBodyLinesCounted+" 7- Max BODY-LINES-Completed : "+(CurrentLines));
////////////////////	Body Data --5 cols///////////////////////	
					
					 float[] BillingFieldsDetailsWidths4 = {0.10f,0.10f,0.55f,0.15f};
					 BodyTableDtl4 = new PdfPTable(BillingFieldsDetailsWidths4);
				//	 NoOfBodyLinesCounted =NoOfBodyLinesCounted - NoBodyTemp; 
					 NoOfBodyLinesCounted=NoOfBodyLinesInNextPage;
					 int kk4=0;
					 for(int k=0; k<NoBodyTemp; k++)
					 {
						 String BodyLine[]=BodyTemp[k].split("~");
							if(BodyLine.length>0) {
								NoBodyLine=BodyLine.length;
							}
		              for(int i=0; i<NoBodyLine; i++)
						{			            	  
		            	  if(i<2)
		            		  BodyTableDtl4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            	  else if(i==2)
		            		  BodyTableDtl4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            	  else
		            		  BodyTableDtl4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		            	  
		            	  BodyTableDtl4.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            	  if(i<3 || i>5) 
		            	  {
			            	  if(BodyLine[i].endsWith("Gross Amount") || BodyLine[i].endsWith("Total GST") || BodyLine[i].endsWith("Total Invoice Amount in INR") || BodyLine[i].endsWith("Net Amount Payable in INR")) 
			            	  {
			            		  BodyTableDtl4.addCell(new Phrase(new Chunk(BodyLine[i],small_black_bold)));//System.out.println(NoBodyLine+" BOLD : "+BodyLine[i]); 
			            	  }
			            	  else
			            	  {
			            		  BodyTableDtl4.addCell(new Phrase(new Chunk(BodyLine[i],small_black_normal))); //System.out.println(NoBodyLine+" NORMAL : "+BodyLine[i]); 
			            	  }
		            	  }					    
						}
		              CurrentLines +=1; kk4++;
		              NoOfBodyLinesCounted++;
		              if((BodyColSize)==4) kk=kk4;
		              if((NoOfBodyLinesCounted)>=(BodyEndLine))
		              {
		            	  //////////////////Next Page-Header/////////////////////////		      			 		
		      			NoOfNextPage = 2;
		      			///////////////////////////////////////////////////////////
			            	  break;
		              }		             
					 }	
					 
					 System.out.println(NoOfBodyLinesCounted+" 5- Max BODY-LINES-Completed : "+(CurrentLines));
					 
////////////////////////////////////PRICING//////////////////////////////////////////////////
			            PriceInWord = new PdfPTable(1); 
			            PriceInWord.setWidthPercentage(WidthPerc); 
			            PriceInWord.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			   
			            cell=new PdfPCell(new Paragraph("\n" ,font1));
						//	cell=new PdfPCell(new Paragraph(CurrentLines+"\n" ,small_black_normal));
						cell.setBorder(0);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setVerticalAlignment(Element.ALIGN_LEFT);
						PriceInWord.addCell(cell);
						NoBlankLines--;
						CurrentLines++;
							
			            PriceInWord.setHorizontalAlignment(Element.ALIGN_CENTER);
			            PriceInWord.getDefaultCell().setBorder(Rectangle.BOX);
			            PriceInWord.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			            PriceInWord.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
			            PriceInWord.addCell(new Phrase(new Chunk("Amount in Words  | Rupees "+result+" Only",small_black_bold)));
			////////////////////////BLANK LINES=2///////////////////////////////////////
			            BlankLines = new PdfPTable(1); 
						BlankLines.setWidthPercentage(WidthPerc);
						BlankLines.getDefaultCell().setBorder(Rectangle.NO_BORDER);
						BlankLines.setHorizontalAlignment(Element.ALIGN_LEFT);
						BlankLines.addCell(new Phrase(new Chunk(" ",small_black_normal)));	
						NoBlankLines +=2;
						
			    float[] PaymentWidths = {0.90f};
			    Payment = new PdfPTable(PaymentWidths); 
			    Payment.setHorizontalAlignment(Element.ALIGN_CENTER);
	            
			    Payment.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			    Payment.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			    Payment.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
			    Payment.addCell(new Phrase(new Chunk(PaymentRemark,small_black_normal)));
		//	    System.out.println("G. End of Price Line: "+CurrentLines);
			  
	            Sign = new PdfPTable(1); 
	            Sign.setWidthPercentage(WidthPerc); 
	            Sign.setHorizontalAlignment(Element.ALIGN_CENTER);
	     //       System.out.println("3. Final Lines before Sign: "+CurrentLines);
	            if(SignatureLeft.equals("Y") )
	            {
				 	Sign.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				 	Sign.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				 	Sign.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
				 	Sign.addCell(new Phrase(new Chunk(Signature,big_black_bold)));
	            }
	            else
	            {
	            	Sign.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				 	Sign.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				 	Sign.getDefaultCell().setVerticalAlignment(Element.ALIGN_RIGHT);
				 	Sign.addCell(new Phrase(new Chunk(Signature,big_black_bold)));
	            }
			 	CurrentLines +=9;
	/*SB		 	NoBlankLines =(MaxLines - NoOfFooterLines) -(CurrentLines);
		//	 	System.out.println(" No of Blank Lines: "+NoBlankLines);
				 while(NoBlankLines>0)
					{
						cell=new PdfPCell(new Paragraph("\n" ,font1));
					//	cell=new PdfPCell(new Paragraph(CurrentLines+"\n" ,small_black_normal));
						cell.setBorder(0);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setVerticalAlignment(Element.ALIGN_LEFT);
						table.addCell(cell);
						NoBlankLines--;
						CurrentLines++;
					}
	*/
			
///////3.Footer with LOGO/////////////////
			//float x1=0.25f; float x2=0.25f; float x3=0.50f;
		/*	System.out.println("1. FOOTER :"+Footer); System.out.println("1. FOOTER-Note1 :"+FooterNote1);  System.out.println("1. FOOTER-Not2 :"+FooterNote2);
			System.out.println("2. FooterColPerc :"+FooterColPerc);
			System.out.println("3. FooterCol2Perc :"+FooterCol2Perc);
			System.out.println("4. FooterNoRow :"+FooterNoRow);
			System.out.println("5. FooterBorder :"+FooterBorder);
			System.out.println("6. FooterBorderLine :"+FooterBorderLine);
			*/
			float FooterColFloat = Float.parseFloat(FooterColPerc)/100;
            float FooterCol2Float = Float.parseFloat(FooterCol2Perc)/100;
            float FooterCol3Float = 1-(FooterColFloat+FooterCol2Float);
            
			url_start = "http://"+server_nm+":"+server_port+context_nm;
			//System.out.println("this is the pic path  "+url_start+"\\images\\LOGO\\HLPL_Logo.jpg");
          //  Image logo_footer = Image.getInstance(url_start+"//images//LOGO//logo.png");
            Image logo_footer = Image.getInstance(url_start+"//images//logo//Footer-Logo.jpg");
            int PageNo=1; 
            if(FooterBorder.equals("Y"))
            	logo_footer.setBorder(Rectangle.BOX);
            else
            	logo_footer.setBorder(Rectangle.NO_BORDER);
            logo_footer.scaleAbsolute(100,50);
            PdfPCell logo_footer_cell = new PdfPCell(logo_footer,false);
            logo_footer_cell.setBorder(Rectangle.NO_BORDER);
            
            if((FooterCol3Float)>0)
            { 
            	float[] FieldsWidthsFooter = {FooterColFloat, FooterCol2Float, FooterCol3Float};
                LogoTableFooter = new PdfPTable(FieldsWidthsFooter);
            }
            else if(FooterColFloat<1)
            {
              float[] FieldsWidthsFooter = {FooterColFloat, FooterCol2Float};
              LogoTableFooter = new PdfPTable(FieldsWidthsFooter);
            }
            else
            {
            	 float[] FieldsWidthsFooter = {FooterColFloat};
            	 LogoTableFooter = new PdfPTable(FieldsWidthsFooter);
            }
      
            LogoTableFooter.setWidthPercentage(WidthPerc);
            
            if(FooterBorderLine.equals("Y") && FooterColFloat==1)
   		 	{
            	cellHeader=new PdfPCell(new Paragraph("\n" ,small_black_normal));
            //SB	cellHeader=new PdfPCell(new Paragraph("  Page-"+PageNo+"\n" ,small_black_normal));
            	cellHeader.setBorder(1);
            	cellHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
            	cellHeader.setVerticalAlignment(Element.ALIGN_LEFT);
   					LogoTableFooter.addCell(cellHeader);
   				//	CurrentLines++;
   		 	}
            
            if(FooterColFloat==1 && FooterLogo.equals("N")) //Print Full Line with footerInfo
            { 
	            LogoTableFooter.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            LogoTableFooter.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	            LogoTableFooter.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            LogoTableFooter.addCell(new Phrase(new Chunk(Footer,small_black_normal)));
            }
            else  if(FooterColFloat==1 && FooterLogo.equals("Y")) //Print Full Line with footerInfo
            { 
            	LogoTableFooter.getDefaultCell().setBorder(Rectangle.NO_BORDER);
 	            LogoTableFooter.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
 	            LogoTableFooter.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
 	            LogoTableFooter.addCell(logo_footer_cell);
            }
            else if(FooterColFloat<1 )
            { 
	            LogoTableFooter.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            LogoTableFooter.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            LogoTableFooter.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            LogoTableFooter.addCell(new Phrase(new Chunk(FooterNote1,small_black_normal)));
	            
	            LogoTableFooter.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            LogoTableFooter.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            LogoTableFooter.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            LogoTableFooter.addCell(new Phrase(new Chunk(FooterNote2,small_black_bold)));
            }           
            if((FooterCol3Float)>0 ) //For LOGO at bottom(if Col1+Col2<100%)
            { 
	            LogoTableFooter.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	            LogoTableFooter.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            LogoTableFooter.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            if(FooterLogo.equals("Y"))
	            	LogoTableFooter.addCell(logo_footer_cell);
	            else
	            	LogoTableFooter.addCell(new Phrase(new Chunk(Footer,small_black_bold)));
            }

			//////////////SAC CODE/////////////////
			 SAC_HSN = new PdfPTable(1); 
			 SAC_HSN.setWidthPercentage(WidthPerc); 
			 SAC_HSN.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	          
			 SAC_HSN.setHorizontalAlignment(Element.ALIGN_CENTER);
			 SAC_HSN.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			 SAC_HSN.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
			 SAC_HSN.addCell(new Phrase(new Chunk(SacHsn,verysmall_black_normal)));
			/////////////////Line Calculation////////////////////////
	/*		 System.out.println("1  NoRow :HeaderNoRow=6: "+NoOfHeaderLines);
			 System.out.println("2  NoRow :SubBody1NoRow=3 "+SubBody1NoRowCount);
			 System.out.println("3  NoRow :SubBody2NoRow=10 "+SubBody2NoRowCount);
			 System.out.println("4  NoRow :SubjectNoRow=2 "+SubjectNoRowCount); 
			 System.out.println("5  NoRow :BodyEndRow=73 "+BodyEndLine);
			 System.out.println("5.1  NoRow :BlankLineBeforeSign=2 "+BlankLineBeforeSign);
			 System.out.println("5.1  NoRow :BlankLineAfterSign=2 "+BlankLineAfterSign);
			 System.out.println("4  NoRow :FooterNoRow=3 "+NoOfFooterLines);
			 System.out.println("4  NoRow :NextPageHeaderNoRow=6 "+NextPageHeaderNoRowCount);
			 ////////////////////////////////////////
			 System.out.println(" :SubBody1Flag=Y: "+SubBody1Flag);
			 System.out.println(" :SubBody2Flag=Y: "+SubBody2Flag);
			 System.out.println(" :SubjectFlag=Y: "+SubjectFlag);
			 System.out.println(" :SignatureFlag=Y: "+SignatureFlag);
			 System.out.println(" :PriceInWordFlag=Y: "+PriceInWordFlag);
			 System.out.println(" :RemarkFlag=Y: "+RemarkFlag);
			 */
////////////  start of Drawing Page /////////////////////////////////////////////
			int  NoOfLinesCounted=0;
			
			document.add(LogoTableHeader);
			NoOfLinesCounted +=NoOfHeaderLines+1;  //System.out.println(NoOfLinesCounted+":1 LogoTableHeader : "+NoOfLinesCounted);
			if(SubBody1Flag.equals("Y"))
			{
				if(HeaderBorderLine.equals("Y") ) 
					 NoOfLinesCounted +=2;
				document.add(SubTableHeader1);
				NoOfLinesCounted +=SubBody1NoRowCount; 
				NoOfLinesCounted +=3;
				//System.out.println(NoOfLinesCounted+":2 SubTableHeader1 : "+NoOfLinesCounted);
			}
			if(SubBody2Flag.equals("Y"))
			{
				document.add(SubTableHeader2);
				NoOfLinesCounted +=(SubBody2NoRowCount/2); //System.out.println(NoOfLinesCounted+":3 SubTableHeader2 : "+NoOfLinesCounted);
			//	NoOfLinesCounted +=2;
//				Gap Line at SAC_HSN lines
				if(PriceInWordFlag.equals("Y"))
				{
					document.add(SAC_HSN);
					NoOfLinesCounted +=1; //System.out.println(NoOfLinesCounted+":4 SAC_HSN : "+NoOfLinesCounted);
				///	NoOfLinesCounted +=3; System.out.println(NoOfLinesCounted+":4a SAC_HSN : "+NoOfLinesCounted);
				}
				
				document.add(BlankLines);
				NoOfLinesCounted +=2;
		///		NoBlankLines +=1; 
				//System.out.println(NoOfLinesCounted+":5 BlankLines : "+NoOfLinesCounted);
			}
			
			if(SubjectFlag.equals("Y"))
			{
				document.add(SubjectTableLeft);
				NoOfLinesCounted +=(SubjectNoRowCount);
		//		NoOfLinesCounted +=NoSubjectLineLeft; 
				//System.out.println(NoOfLinesCounted+":6 SubjectTableLeft : "+NoOfLinesCounted);
				document.add(BlankLines);
				NoOfLinesCounted +=1; //System.out.println(NoOfLinesCounted+":7 BlankLines : "+NoOfLinesCounted);
			//	NoBlankLines +=1;
			}
			//document.add(SubjectTableRight);
				
		//	System.out.println(" :Start of Body ******** : "+NoOfLinesCounted);
			/// if(flag_sac.equalsIgnoreCase("P")) 
			if((BodyColSize)==7) 
			 	{
				 	document.add(BodyTable);
				 	NoOfLinesCounted +=1; //System.out.println(NoOfLinesCounted+":8-6 BodyTable : "+NoOfLinesCounted);
					document.add(BodyTableDtl);
				////	NoOfLinesCounted +=NoBodyTemp;
					NoOfLinesCounted +=kk;
			 	}
			else if((BodyColSize)==4) 
				{
					document.add(BodyTable4);
					NoOfLinesCounted +=1; //System.out.println(NoOfLinesCounted+":8-4 BodyTable4 : "+NoOfLinesCounted);
					document.add(BodyTableDtl4);
				///	NoOfLinesCounted +=NoBodyTemp;
					NoOfLinesCounted +=kk;
				}
	
				System.out.println("\n\nPage-"+NoOfNextPage+" : Single Page >>>>>Body Completed>>>>>>: "+NoOfLinesCounted);
			
			
			//////////////////Incase of Multiple Page/////////////
			if(NoOfNextPage>1)
			{  	System.out.println("Multiple Page >>>>>>>>>>>>>: "+NoOfNextPage);
			/////////////Add Continue Line at the end of Body//////////////////////////////////
			
				NextPageHeaderTable = new PdfPTable(FieldsWidthsNextPageHeader); 
				if(NextPageHeaderBorderLine.equals("Y") )
	 			 {
					NoBlankLines =NextPageHeaderNoRowCount;System.out.println("Page-"+NoOfNextPage+" Next: No of Blank Lines CONTINUE......: "+NoBlankLines);
					 while(NoBlankLines>0)
						{
						 cellHeader=new PdfPCell(new Paragraph("\n" ,small_black_normal));
							cell.setBorder(0);
							cell.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell.setVerticalAlignment(Element.ALIGN_LEFT);
							table.addCell(cell);
							NoBlankLines--;
							NoOfLinesCounted++;
						}
					 document.add(table); //Added blank Line
					 
					PageNo++;
	 				NextPageHeaderTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	 				NextPageHeaderTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	 				NextPageHeaderTable.addCell(new Phrase(new Chunk(NextPageHeader+"  Page-"+PageNo,small_black_normal)));	
	 				document.add(NextPageHeaderTable); ///System.out.println(" 2:Line Counted >>>>>>>>>>>>>: "+NoOfLinesCounted);
					NoOfLinesCounted++;
					NoOfLinesCounted +=(NextPageHeaderNoRowCount); 								
	 			 } 
					System.out.println(kk+" :Lines in 1st Page at  CLOSE =: "+NoOfLinesCounted);
			////////////////////////////////////////////////////
					/////////////PAGE-NO///////////////////// 
						tablePageNo = new PdfPTable(1);
						tablePageNo.setWidthPercentage(WidthPerc);
						tablePageNo.getDefaultCell().setBorder(Rectangle.NO_BORDER);
						if(PageNoFlag.equals("Y"))
							cellPage=new PdfPCell(new Paragraph("Page-"+(PageNo-1)+"/"+TotalNoOfPage,font1));		
						else
							cellPage=new PdfPCell(new Paragraph(" ",font1));	
						cellPage.setBorder(0);
						cellPage.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cellPage.setVerticalAlignment(Element.ALIGN_RIGHT);
						tablePageNo.addCell(cellPage);
						NoOfLinesCounted++;
						//////////////////////////////////////////////////	//	
				 NoBlankLines =(MaxLines) - (NoOfLinesCounted);
			//	 System.out.println("Page-"+NoOfNextPage+" Next: No of Blank Lines before FOOTER: "+NoBlankLines);
						 while(NoBlankLines>0)
							{
								cell=new PdfPCell(new Paragraph("\n" ,font1));
							//	cell=new PdfPCell(new Paragraph(CurrentLines+"\n" ,small_black_normal));
								cell.setBorder(0);
								cell.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell.setVerticalAlignment(Element.ALIGN_LEFT);
								table.addCell(cell);
								NoBlankLines--;
								NoOfLinesCounted++;
							}
				document.add(table); //Added blank Line
				///////////////////////////////////							 
				document.add(tablePageNo);
				/////////////////////////////////////////
				document.add(LogoTableFooter);
			////	document.add(NextPageHeaderTable);
			}
			//////////////////////////////////////////////////////
			System.out.println(NoOfNextPage+": 1st PAGE  completed : "+NoOfLinesCounted);
			
			int NoLineBlankInNextPage=0; int SinglePageMaxLine=MaxLines;
		//	System.out.println((NoBodyTemp-kk)+": STATUS of BODY : "+MaxLines);
			NoOfNextPage += (NoBodyTemp-kk)/(MaxLines-10); 	
			
			System.out.println(":Total Page >>>>>>>>>>>>>: "+NoOfNextPage);
			////////////////////////////////////Pre-Calculation//////////////////////
			if(NoOfNextPage==1)
			{ 
			int NoOfLinesCountedPage1=0;							 
				if(PriceInWordFlag.equals("Y"))
				{		
					NoOfLinesCountedPage1 +=2; //System.out.println(NoOfLinesCounted+":9 PriceInWord : "+NoOfLinesCounted);		
				}
				if(RemarkFlag.equals("Y"))
				{
					NoOfLinesCountedPage1 +=Integer.parseInt(BlankLineAfterSign);
					NoOfLinesCountedPage1 +=3;   //System.out.println(NoOfLinesCounted+":11 Payment : "+NoOfLinesCounted);
				}
				if(SignatureFlag.equals("Y"))
				{
					NoOfLinesCountedPage1 +=BlankLineBeforeSignCount;
					NoOfLinesCountedPage1 +=4; //System.out.println(NoOfLinesCounted+": Sign : "+NoOfLinesCounted);
					NoOfLinesCountedPage1 +=BlankLineAfterSignCount;  //System.out.println(NoOfLinesCounted+":15 BlankLines : "+NoOfLinesCounted);
				}			
		/*		 if(PriceInWordFlag.equals("Y") && RemarkFlag.equals("Y") && SignatureFlag.equals("Y"))
					 NoOfLinesCountedPage1 +=2;    
					if(PriceInWordFlag.equals("N") && RemarkFlag.equals("Y") && SignatureFlag.equals("Y"))
						NoOfLinesCountedPage1 +=1;  
					if(PriceInWordFlag.equals("Y") && RemarkFlag.equals("N") && SignatureFlag.equals("Y"))
						NoOfLinesCountedPage1 +=1;
					if(PriceInWordFlag.equals("Y") && RemarkFlag.equals("Y") && SignatureFlag.equals("N"))
						NoOfLinesCountedPage1 +=1;
					if(PriceInWordFlag.equals("N") && RemarkFlag.equals("N") && SignatureFlag.equals("N"))
						NoOfLinesCountedPage1 -=1;
					*/ 
					NoOfLinesCountedPage1 +=NoOfFooterLines;  //System.out.println(NoOfLinesCounted+":16 NoOfFooterLines : "+NoOfLinesCounted); //Add Footer Lines
					NoLineBlankInNextPage=(MaxLines- (NoOfLinesCounted+NoOfLinesCountedPage1));
					System.out.println(NoOfNextPage+" : Calculated Page : "+NoLineBlankInNextPage);
			}
			
//////////////////SB20171121//////////
			if(NoLineBlankInNextPage<0)
			{
				System.out.println(" 1:Line Counted >>>>>>>>>>>>>: "+NoOfLinesCounted);
				NextPageHeaderTable = new PdfPTable(FieldsWidthsNextPageHeader);
	    	    //    NoOfBodyLinesInNextPage +=1; //AddFor Body Header Line
						if(NextPageHeaderBorderLine.equals("Y") )
			 			 {
							NoBlankLines =NextPageHeaderNoRowCount;System.out.println("Page-Next: No of Blank Lines before FOOTER: "+NoBlankLines);
							 while(NoBlankLines>0)
								{
								 cellHeader=new PdfPCell(new Paragraph("\n" ,small_black_normal));
									cell.setBorder(0);
									cell.setHorizontalAlignment(Element.ALIGN_LEFT);
									cell.setVerticalAlignment(Element.ALIGN_LEFT);
									table.addCell(cell);
									NoBlankLines--;
									NoOfLinesCounted++;
								}
							 document.add(table); //Added blank Line
							 
							PageNo++;
			 				NextPageHeaderTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			 				NextPageHeaderTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			 				NextPageHeaderTable.addCell(new Phrase(new Chunk(NextPageHeader+"  Page-"+PageNo,small_black_normal)));	
			 				document.add(NextPageHeaderTable); System.out.println(" 2:Line Counted >>>>>>>>>>>>>: "+NoOfLinesCounted);
							NoOfLinesCounted++;
							NoOfLinesCounted +=(NextPageHeaderNoRowCount); 								
			 			 } 
				
					SinglePageMaxLine=MaxLines;
					
					////////////////////////////////////////////////////
					NoOfLinesCounted +=(NoOfFooterLines); 		
					 NoBlankLines =(MaxLines) - (NoOfLinesCounted);System.out.println("Page-Next: No of Blank Lines before FOOTER: "+NoBlankLines);
				 while(NoBlankLines>0)
					{
						cell=new PdfPCell(new Paragraph(" \n" ,font1));
						cell.setBorder(0);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						cell.setVerticalAlignment(Element.ALIGN_LEFT);
						table.addCell(cell);
						NoBlankLines--;
					///	NoOfLinesCounted++;
					}
				 document.add(table); //Added blank Line
				 document.add(LogoTableFooter);System.out.println("Page-Next: No of Lines AFTER FOOTER: "+NoOfLinesCounted);
				 NoOfNextPage++;				
			}
//////////////////
			/////////////////////////////////////////////////////////////////////////
			  String BodyTemp2[]=Body.split("\n");
					
			while(NoOfNextPage>1)
			{  
				NoOfLinesCounted=0;
				NoOfBodyLinesInNextPage=0;
				CurrentLines=0; 
				NoOfBodyLinesCounted=0;
				System.out.println("PAGE-"+NoOfNextPage+"  : <<<Next PAGE>>> : "+NoOfLinesCounted);
				document.add(LogoTableHeader);
				NoOfLinesCounted +=NoOfHeaderLines;   System.out.println("PAGE-"+NoOfNextPage+" : Header Count: "+NoOfLinesCounted);
			//	System.out.println(kk+" :Next Page BODY: "+BodyTemp2.length);
				BodyTableDtl6ColNextPage = new PdfPTable(BillingFieldsDetailsWidths);				
				 for(int k=kk; k<BodyTemp2.length; k++)
				 { 
					String BodyLine[]=BodyTemp2[k].split("~"); //System.out.println(k+" : BODY: "+BodyTemp2[k]);
					if(BodyLine.length>0) 	NoBodyLine=BodyLine.length;
					for(int i=0; i<NoBodyLine; i++)
					{ //System.out.println(i+" : BODY-LINE: "+BodyLine[i]);
	            	  if(i<2)
	            		  BodyTableDtl6ColNextPage.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	            	  else if(i==2)
	            		  BodyTableDtl6ColNextPage.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
	            	  else if(i==3 || i==4)
	            		  BodyTableDtl6ColNextPage.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	            	  else
	            		  BodyTableDtl6ColNextPage.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	            	  BodyTableDtl6ColNextPage.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
	            	  if(BodyLine[i].endsWith("Gross Amount") || BodyLine[i].endsWith("Total GST") || BodyLine[i].endsWith("Total Invoice Amount in INR") || BodyLine[i].endsWith("Net Amount Payable in INR")) 
	            		  BodyTableDtl6ColNextPage.addCell(new Phrase(new Chunk(BodyLine[i],small_black_bold)));//System.out.println(NoBodyLine+" BOLD : "+BodyLine[i]); 
	            	  else
	            		  BodyTableDtl6ColNextPage.addCell(new Phrase(new Chunk(BodyLine[i],small_black_normal))); //System.out.println(NoBodyLine+" NORMAL : "+BodyLine[i]); 					    
					}
					////////////////////////////To break/////////////////////////
		              kk++;
		              NoOfBodyLinesCounted +=1;
		         // 	System.out.println(NoOfBodyLinesCounted+": @@@@@@@@CCCCCC@@@@@@@@ : "+BodyEndLine);
		              ////if((NoOfBodyLinesCounted)>=(BodyEndLine))
		            if((NoOfBodyLinesCounted)>=(MaxLines-15))
		              {
		      			 System.out.println("PAGE-"+NoOfNextPage+" : Break after Body Line: "+(NoOfBodyLinesCounted));
			            	  break;
		              }	
				 }
			//	 System.out.println(NoOfNextPage+": Line in BODY: "+(NoOfBodyLinesCounted));
				
				/////////////////////////////////////////////
			
					document.add(BodyTable);
					NoOfLinesCounted +=1;				  //System.out.println(" :Line Counted >>>>>>>>>>>>>: "+NoOfLinesCounted);
				//	document.add(LogoTableFooter);	
					document.add(BodyTableDtl6ColNextPage);
					NoOfLinesCounted +=NoOfBodyLinesCounted; 
					if(NoOfNextPage>2)
					{
					///	NoOfLinesCounted +=(NoOfBodyLinesCounted+1); //System.out.println(" :Line Counted >>>>>>>>>>>>>: "+NoOfLinesCounted);
					 System.out.println("PAGE-"+NoOfNextPage+" : After BODY: "+(NoOfLinesCounted));
////////////////////////////////////////////////////
						
						NextPageHeaderTable = new PdfPTable(FieldsWidthsNextPageHeader); 
						if(NextPageHeaderBorderLine.equals("Y") )
			 			 {
							table = new PdfPTable(1);
							table.setWidthPercentage(WidthPerc);
							table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
							NoBlankLines =NextPageHeaderNoRowCount;  //System.out.println("Page-"+NoOfNextPage+" Next: No of Blank Lines CONTINUE......: "+NoBlankLines);
							 while(NoBlankLines>0)
								{
								 cellHeader=new PdfPCell(new Paragraph("\n" ,small_black_normal));
									cell.setBorder(0);
									cell.setHorizontalAlignment(Element.ALIGN_LEFT);
									cell.setVerticalAlignment(Element.ALIGN_LEFT);
									table.addCell(cell);
									NoBlankLines--;
									NoOfLinesCounted++;
								}
							 document.add(table); //Added blank Line
							 
							PageNo++;
			 				NextPageHeaderTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			 				NextPageHeaderTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			 				NextPageHeaderTable.addCell(new Phrase(new Chunk(NextPageHeader+"  Page-"+PageNo,small_black_normal)));	
			 				document.add(NextPageHeaderTable); ///System.out.println(" 2:Line Counted >>>>>>>>>>>>>: "+NoOfLinesCounted);
						//	NoOfLinesCounted++;
						///	NoOfLinesCounted +=(NextPageHeaderNoRowCount); 								
			 			 }
						 System.out.println("PAGE-"+NoOfNextPage+" : After Continue: "+(NoOfLinesCounted));
						/////////////FOOTER///////////////////
						NoOfLinesCounted +=(NoOfFooterLines); 		
						NoBlankLines =(MaxLines) - (NoOfLinesCounted);System.out.println("PAGE-"+NoOfNextPage+" Page-Next: No of Blank Lines before FOOTER: "+NoBlankLines);
						table = new PdfPTable(1);
						table.setWidthPercentage(WidthPerc);
						table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
						while(NoBlankLines>0)
							{
								cell=new PdfPCell(new Paragraph("\n" ,font1));								
								cell.setBorder(0);
								cell.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell.setVerticalAlignment(Element.ALIGN_LEFT);
								table.addCell(cell);
								NoBlankLines--;
								NoOfLinesCounted++;
							}
						 document.add(table); //Added blank Line
						/////////////PAGE-NO///////////////////// 
						tablePageNo = new PdfPTable(1);
						tablePageNo.setWidthPercentage(WidthPerc);
						tablePageNo.getDefaultCell().setBorder(Rectangle.NO_BORDER);
						if(PageNoFlag.equals("Y"))
							cellPage=new PdfPCell(new Paragraph("Page-"+(PageNo-1)+"/"+TotalNoOfPage,font1));		
						else
							cellPage=new PdfPCell(new Paragraph(" ",font1));
													
						cellPage.setBorder(0);
						cellPage.setHorizontalAlignment(Element.ALIGN_RIGHT);
						cellPage.setVerticalAlignment(Element.ALIGN_RIGHT);
						tablePageNo.addCell(cellPage);
						NoOfLinesCounted++;
						document.add(tablePageNo);
						/////////////////////////////////////////
						 document.add(LogoTableFooter);				
						/////////////////////////////////////
					} //ENd of PAGES
						System.out.println("Page-"+NoOfNextPage+" :Lines in Page at  CLOSE =: "+NoOfLinesCounted);
				 NoOfNextPage--;
			}
		//	System.out.println(" :Out of While: "+NoOfLinesCounted);
			if(NoOfNextPage==1)
			{  				
				System.out.println("\n\nPAGE-"+NoOfNextPage+" : Last PAGE : "+NoOfLinesCounted);
				if(PriceInWordFlag.equals("Y"))
				{
					document.add(PriceInWord);
					NoOfLinesCounted +=2; //System.out.println(NoOfLinesCounted+":9 PriceInWord : "+NoOfLinesCounted);
				//	System.out.println(NoOfLinesCounted+" : PriceInWord : "+NoOfLinesCounted);
				}
				if(RemarkFlag.equals("Y"))
				{
					int NoLineBlank=Integer.parseInt(BlankLineAfterSign);
					NoOfLinesCounted +=Integer.parseInt(BlankLineAfterSign);
					while(NoLineBlank>0)
					{
						document.add(BlankLines);
						NoOfLinesCounted +=1;  //System.out.println(NoOfLinesCounted+":15 BlankLines : "+NoOfLinesCounted);
						NoLineBlank -=1;
					}	
					document.add(Payment);
					NoOfLinesCounted +=3;   //System.out.println(NoOfLinesCounted+":11 Payment : "+NoOfLinesCounted);
				///	System.out.println(NoOfLinesCounted+" : Payment : "+NoOfLinesCounted);
				}
		/*		if(PriceInWordFlag.equals("Y") && RemarkFlag.equals("Y") && SignatureFlag.equals("Y"))
					NoOfLinesCounted +=3;    
				else if(PriceInWordFlag.equals("N") && RemarkFlag.equals("Y") && SignatureFlag.equals("Y"))
					NoOfLinesCounted +=1;  
				else if(PriceInWordFlag.equals("Y") && RemarkFlag.equals("N") && SignatureFlag.equals("Y"))
					NoOfLinesCounted +=1;
				else if(PriceInWordFlag.equals("Y") && RemarkFlag.equals("Y") && SignatureFlag.equals("N"))
					NoOfLinesCounted +=3;
				else if(PriceInWordFlag.equals("Y") && RemarkFlag.equals("N") && SignatureFlag.equals("N"))
					{ 
					NoOfLinesCounted +=2; //NoOfLinesCounted -=1; //NoOfLinesCounted +=5; 
					}
		*/
				if(SignatureFlag.equals("Y"))
				{
					int NoLineBlank=BlankLineBeforeSignCount;
					while(NoLineBlank>0)
					{
						document.add(BlankLines);
						NoOfLinesCounted +=1;  //System.out.println(NoOfLinesCounted+":15 BlankLines : "+NoOfLinesCounted);
						NoLineBlank -=1;
					}	
					document.add(Sign);
					NoOfLinesCounted +=4; 
					NoLineBlank=Integer.parseInt(BlankLineAfterSign);
					while(NoLineBlank>0)
					{
						document.add(BlankLines);
						NoOfLinesCounted +=1;  //System.out.println(NoOfLinesCounted+":15 BlankLines : "+NoOfLinesCounted);
						NoLineBlank -=1;
					}	
					
			//		System.out.println(NoOfLinesCounted+" : Sign : "+NoOfLinesCounted);
				}
			
				 NoOfLinesCounted +=NoOfFooterLines;  System.out.println(NoOfLinesCounted+":16 NoOfFooterLines : "+NoOfLinesCounted); //Add Footer Lines
/////////////PAGE-NO///////////////////// 
				 PageNo++;
					tablePageNo = new PdfPTable(1);
					tablePageNo.setWidthPercentage(WidthPerc);
					tablePageNo.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					if(!Note.equals(""))
					{	
						cellPage=new PdfPCell(new Paragraph(Note,font1));		
						cellPage.setBorder(0);
						cellPage.setHorizontalAlignment(Element.ALIGN_LEFT);
						cellPage.setVerticalAlignment(Element.ALIGN_LEFT);
						if(BodyDataTotalRowCount>1)
							tablePageNo.addCell(cellPage);
						NoOfLinesCounted++; 
					}
					
					if(PageNoFlag.equals("Y"))
						cellPage=new PdfPCell(new Paragraph("Page-"+(PageNo-1)+"/"+TotalNoOfPage,font1));		
					else
						cellPage=new PdfPCell(new Paragraph(" ",font1));	
					
					cellPage.setBorder(0);
					cellPage.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cellPage.setVerticalAlignment(Element.ALIGN_RIGHT);
					tablePageNo.addCell(cellPage);
					NoOfLinesCounted++; 				
					
////////////////////////////////////////////////////
				//	System.out.println(MaxLines+":No of Line after Continue in Next Page: "+NoOfLinesCounted);
					NoBlankLines =(MaxLines) - (NoOfLinesCounted);System.out.println("PAGE-"+NoOfNextPage+" Page-Next: No of Blank Lines before FOOTER: "+NoBlankLines);
							 while(NoBlankLines>0)
								{
									cell=new PdfPCell(new Paragraph("\n" ,font1));
									cell.setBorder(0);
									cell.setHorizontalAlignment(Element.ALIGN_LEFT);
									cell.setVerticalAlignment(Element.ALIGN_LEFT);
									table.addCell(cell);
									NoBlankLines--;
									NoOfLinesCounted++;
								}
							 document.add(table); //Added blank Line
					///////////////////////////////////							 
							document.add(tablePageNo);
					/////////////////////////////////////////
				    document.add(LogoTableFooter);				
			}
			 /// System.out.println(NoOfBodyLinesCounted+": Body-LINE and Other-Lines : "+NoOfLinesCounted);
			  System.out.println("PAGE-"+NoOfNextPage+" FINAL: Total Line End of FooterLines : "+(NoOfLinesCounted));
			}
			catch(Exception e)
			{
				System.out.println("Exception in DataBean_Ban_Notice_Format_Pdf-->pdf_text()"+e.getMessage());
				e.printStackTrace();
			}
			finally
			{
				document.close();System.out.println("PDF Document closed ");
			}
	}
	public void GetPDF_Template()
	{
		try
		{
		queryString = "SELECT NVL(INV_FORMAT_TYPE,'0') FROM DLNG_AC_INVOICE_MST " +
		  		"WHERE CONTRACT_TYPE='"+inv_type+"'  and supplier_cd='"+supp_cd_set+"' AND CUSTOMER_CD='"+Svendor_cd+"' " +
		  		" AND INVOICE_DT = (SELECT MAX(INVOICE_DT) FROM DLNG_SERVICE_INVOICE_MST " +
		  		" WHERE CONTRACT_TYPE='"+inv_type+"'  and supplier_cd='"+supp_cd_set+"' AND CUSTOMER_CD='"+Svendor_cd+"' )"; //SB20171025
		System.out.println("PDF-Template: "+queryString);
		rset=stmt.executeQuery(queryString);
		if(rset.next())
			{
			LastPDF_Template =rset.getString(1);
			}
		else
			{
			LastPDF_Template = "0";
			}
		System.out.println("PDF-Template: "+LastPDF_Template);
		}
		catch(Exception e)
		{
			System.out.println("Exception in DataBean_Ban_Notice_Format_Pdf-->GetPDF_Template()"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	///////////////////////////////Font used in PDF////////////////////////////////////////
	String credits="0";
	Paragraph p = new Paragraph();
	String path_env = ""+System.getenv("WINDIR");
	Font font = FontFactory.getFont(path_env+"/Fonts/BKANT.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 0.8f, Font.NORMAL, Color.BLACK);
	BaseFont baseFont = font.getBaseFont();
	
	Font font3 = FontFactory.getFont("Times New Roman", 12, Font.BOLD,Color.BLACK);
	Font font1 = FontFactory.getFont("Times New Roman", 10, Font.NORMAL,Color.BLACK);
	Font font3_1 = FontFactory.getFont("Times New Roman", 10, Font.BOLD,Color.BLACK);
	Font font2 = FontFactory.getFont("Times New Roman",3,Font.BOLDITALIC,Color.BLACK);
	
	Font font_book = new Font(baseFont, 12, Font.BOLD,Color.BLACK);
	Font font_book_1 = new Font(baseFont, 10, Font.NORMAL,Color.BLACK);
	Font font_book_2 = new Font(baseFont, 3, Font.BOLD,Color.BLACK);
	Font font_book_3 = new Font(baseFont, 10, Font.BOLD,Color.BLACK);
	/////////////////////////////////^^^^^////////////////////////////////////////////////////
////////////SB20180207: New function////////////////////////////////////////////////////////
	public void FETCH_INVOICE_DTL2() //SB20171107
	{
		try
		{
			if(!month.equalsIgnoreCase("") && (!year.equalsIgnoreCase(""))){
				
				String queryString2a="SELECT SUPPLIER_CD,SUPPLIER_NAME,SUPPLIER_ABBR FROM FMS7_SUPPLIER_MST WHERE SUPPLIER_CD='"+supp_cd_set+"' ";
//				System.out.println("INV-LIST: "+queryString2a);
				rset=stmt.executeQuery(queryString2a);
				if(rset.next())
				{
					supplier_abbr=rset.getString(3)==null?"":rset.getString(3);
				}
				 dt="01/"+month+"/"+year;
				 String queryString1="select TO_CHAR(LAST_DAY(TO_DATE('"+dt+"','dd/mm/yyyy')),'DD/MM/YYYY') from DUAL";
					//System.out.println("----query-----"+queryString1);
					rset=stmt.executeQuery(queryString1);
					if(rset.next())
					{
						end_dt=rset.getString(1);
					}
					
					if(Integer.parseInt(month)<4){
						fin_yr=(Integer.parseInt(year)-1)+"-"+year;
					}else{
						fin_yr=year+"-"+(Integer.parseInt(year)+1);
					}
					
		/*SB20180207		queryString = "select new_inv_seq_no,to_char(invoice_dt,'dd/mm/yyyy'),INV_SEQ_NO,financial_year,"
						+ "checked_flag,approved_flag,pdf_inv_dtl,supplier_cd, CUSTOMER_CD " +
						" from fms7_invoice_mst " +
						" where contract_type='"+inv_type+"' and financial_year='"+fin_yr+"' and (invoice_dt between to_date('"+dt+"','dd/mm/yyyy') "
						+ "and to_date('"+end_dt+"','dd/mm/yyyy'))  order by INV_SEQ_NO DESC";
						*/
		//SB20200505		queryString = "select NEW_INV_SEQ_NO,to_char(INVOICE_DT,'dd/mm/yyyy'),INV_SEQ_NO,FINANCIAL_YEAR,"
					queryString = "select NEW_INV_SEQ_NO,to_char(INVOICE_DT,'dd/mm/yyyy'),INV_SEQ_NO,FINANCIAL_YEAR,"
					+ "CHECKED_FLAG,APPROVED_FLAG,PDF_INV_DTL,SUPPLIER_CD, CUSTOMER_CD " +
					" from DLNG_AC_INVOICE_MST " +
					" where CONTRACT_TYPE='"+inv_type+"' and FINANCIAL_YEAR='"+fin_yr+"' and (INVOICE_DT between to_date('"+dt+"','dd/mm/yyyy') "
					+ "and to_date('"+end_dt+"','dd/mm/yyyy'))  " +
					" AND SUPPLIER_CD='"+supp_cd_set+"' " + //SB20180319
					" order by INV_SEQ_NO DESC";
//				System.out.println("Customer Details Fetch Query = "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
					{
						Vinv_seq_no.add(rset.getString(1)==null?"":rset.getString(1));
						Vinv_dt.add(rset.getString(2)==null?"":rset.getString(2));
						Vhlpl_inv_seq_no.add(rset.getString(3)==null?"":rset.getString(3));
						Vfin_yr.add(rset.getString(4)==null?"":rset.getString(4));
						Checked.add(rset.getString(5)==null?"":rset.getString(5));
						approved.add(rset.getString(6)==null?"":rset.getString(6));
						Vpdf_dtl.add(rset.getString(7)==null?"":rset.getString(7));
						VSupp_cd.add(rset.getString(8)==null?"":rset.getString(8));
						VCust_cd.add(rset.getString(9)==null?"":rset.getString(9));
//						Vcust_nm.add(rset.getString(10)==null?"":rset.getString(10));
//						Vvendor_abr.add(rset.getString(11)==null?"GEN":rset.getString(11));
//						Vcust_nm.add("TRN");
//						Vvendor_abr.add("GEN");
					}
				for(int k=0;k<Vhlpl_inv_seq_no.size();k++) 
				{
					queryString = "select TRANS_ABBR, TRANS_NAME from DLNG_TRANS_MST " +
					" where TRANS_CD='"+VCust_cd.elementAt(k)+"' ";
//					System.out.println("Customer Details Fetch Query 1 = "+queryString);
					rset = stmt.executeQuery(queryString);
					if(rset.next())
						{
							Vvendor_abr.add(rset.getString(1)==null?"":rset.getString(1));
							Vcust_nm.add(rset.getString(2)==null?"":rset.getString(2));
						} 
					else 
						{
							Vvendor_abr.add("");
							Vcust_nm.add("");
						}
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////
					queryString1 = "select distinct(flag_sac) from DLNG_AC_INVOICE_DTL where contract_type='"+inv_type+"' and financial_year='"+fin_yr+"' and inv_seq_no='"+Vhlpl_inv_seq_no.elementAt(k)+"' and supplier_cd='"+VSupp_cd.elementAt(k)+"'";
//								System.out.println("Customer Details Fetch Query 2  = "+queryString1);
								rset = stmt.executeQuery(queryString1);
							if(rset.next())
							{
								Vflag_sac.add(rset.getString(1)==null?"":rset.getString(1));
								
							} else {
								Vflag_sac.add("");
							}
				}
//				System.out.println("Vvendor_nm"+Vvendor_nm.size());
				//////////////////////SB20171104: PDF from BIPl dir///////////////////
				String InvType="";
				if(inv_type.equals("Z"))
					 InvType="INVOICE";
				else
					 InvType="RECEIPT";
				for(int k=0;k<Vhlpl_inv_seq_no.size();k++)
				{			
					String f1="";
					String f2="";				
					String path=request.getRealPath("/pdf_reports/other_invoices/"+supplier_abbr);//SB20171104				
						f1=InvType+"-"+Vvendor_abr.elementAt(k)+"-"+Vinv_seq_no.elementAt(k)+"-O";
						f2=InvType+"-"+Vvendor_abr.elementAt(k)+"-"+Vinv_seq_no.elementAt(k)+"-O.pdf";						
					f1=path+"/"+f1;			
					File pdf_file=new File(f1+".pdf");
//					System.out.println("inv_file: "+pdf_file);
					
					if(pdf_file.exists())
					{						
						//System.out.println("inside  rrr");
							path=path+"//"+pdf_file;
							//System.out.println("invoice_pdf_path1----"+invoice_pdf_path1);
				            String context_nm = request.getContextPath();
							String server_nm = request.getServerName();
							String server_port = ""+request.getServerPort();
							  //system.out.println("invoice_pdf_path--"+invoice_pdf_path1);
							String url_start = "http://"+server_nm+":"+server_port+context_nm;
							
							 pdfpath = path;
							pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
							
						//SB20171104	pdfpath = url_start+"/pdf_reports/other_invoices/pdf_files/"+f2;
							pdfpath = url_start+"/pdf_reports/other_invoices/"+supplier_abbr+"/"+f2; //SB20171104
							
							pdf.add(pdfpath);
					}
					else{
						pdf.add("");
					}					
				}
//				System.out.println("pdf---> "+pdf);
				for(int k=0;k<Vhlpl_inv_seq_no.size();k++)
				{					
					String f1="";
					String f2="";				
					String path=request.getRealPath("/pdf_reports/other_invoices/"+supplier_abbr);//SB20171104				
						f1=InvType+"-"+Vvendor_abr.elementAt(k)+"-"+Vinv_seq_no.elementAt(k)+"-D";
						f2=InvType+"-"+Vvendor_abr.elementAt(k)+"-"+Vinv_seq_no.elementAt(k)+"-D.pdf";						
					f1=path+"/"+f1;			
					File pdf_file=new File(f1+".pdf");
			//		System.out.println("inv_file: "+pdf_file);
					
					if(pdf_file.exists())
					{						
						//System.out.println("inside  rrr");
							path=path+"//"+pdf_file;
							//System.out.println("invoice_pdf_path1----"+invoice_pdf_path1);
				            String context_nm = request.getContextPath();
							String server_nm = request.getServerName();
							String server_port = ""+request.getServerPort();
							  //system.out.println("invoice_pdf_path--"+invoice_pdf_path1);
							String url_start = "http://"+server_nm+":"+server_port+context_nm;
							
							 pdfpath = path;
							pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
							
						//SB20171104	pdfpath = url_start+"/pdf_reports/other_invoices/pdf_files/"+f2;
							pdfpath = url_start+"/pdf_reports/other_invoices/"+supplier_abbr+"/"+f2; //SB20171104
							
							pdf1.add(pdfpath);
					}
					else{
						pdf1.add("");
					}					
				}
			//	System.out.println("pdf1---> "+pdf1);
				for(int k=0;k<Vhlpl_inv_seq_no.size();k++)
				{					
					String f1="";
					String f2="";				
					String path=request.getRealPath("/pdf_reports/other_invoices/"+supplier_abbr);//SB20171104				
						f1=InvType+"-"+Vvendor_abr.elementAt(k)+"-"+Vinv_seq_no.elementAt(k)+"-T";
						f2=InvType+"-"+Vvendor_abr.elementAt(k)+"-"+Vinv_seq_no.elementAt(k)+"-T.pdf";						
					f1=path+"/"+f1;			
					File pdf_file=new File(f1+".pdf");
			//		System.out.println("inv_file: "+pdf_file);
					
					if(pdf_file.exists())
					{						
						//System.out.println("inside  rrr");
							path=path+"//"+pdf_file;
							//System.out.println("invoice_pdf_path1----"+invoice_pdf_path1);
				            String context_nm = request.getContextPath();
							String server_nm = request.getServerName();
							String server_port = ""+request.getServerPort();
							  //system.out.println("invoice_pdf_path--"+invoice_pdf_path1);
							String url_start = "http://"+server_nm+":"+server_port+context_nm;
							
							 pdfpath = path;
							pdfpath = pdfpath.substring(pdfpath.indexOf("//"));
							
						//SB20171104	pdfpath = url_start+"/pdf_reports/other_invoices/pdf_files/"+f2;
							pdfpath = url_start+"/pdf_reports/other_invoices/"+supplier_abbr+"/"+f2; //SB20171104
							
							pdf2.add(pdfpath);
					}
					else{
						pdf2.add("");
					}					
				}
		//		System.out.println("pdf2---> "+pdf2);
				//////////////////////////////////////////////////////////////////////

				//////////////////////////////////////
				int PrevInvCountOtheThanAdv=0;
				queryString = "SELECT COUNT(INVOICE_DT)  FROM DLNG_AC_INVOICE_MST " +
				"WHERE financial_year='"+fin_yr+"' AND contract_type='"+inv_type+"' AND INVOICE_DT > TO_DATE('"+end_dt+"','DD/MM/YYYY') "; //SB20171025
			//	System.out.println("MAX-INV-COUNT: "+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					PrevInvCountOtheThanAdv=rset.getInt(1);
				//	LastInvGeneratedDt=rset.getString(2);
				}
				queryString = "SELECT TO_CHAR(MAX(TO_DATE(INVOICE_DT,'DD/MM/YYYY')),'DD/MM/YYYY')  FROM DLNG_AC_INVOICE_MST " +
				"WHERE financial_year='"+fin_yr+"' AND contract_type='"+inv_type+"'  "; //SB20171025
		//		System.out.println("MAX-INV-GEN-DT: "+queryString);
				rset=stmt.executeQuery(queryString);
				if(rset.next())
				{
					LastInvGeneratedDt=rset.getString(1);
				}
				NoInvGeneratedBeyond=""+PrevInvCountOtheThanAdv;
			    //////////////////////////////////////////////For  Billed  Invice///////////////////////////////////////////
				for(int k=0;k<Vhlpl_inv_seq_no.size();k++) 
				{
				//	System.out.println("Customer Details Fetch Query 1 = "+queryString);
					queryString = "SELECT NVL(INV_FORMAT_TYPE,'0') FROM DLNG_AC_INVOICE_MST " +
			  		"WHERE CONTRACT_TYPE='"+inv_type+"'  and supplier_cd='"+VSupp_cd.elementAt(k)+"' and inv_seq_no='"+Vhlpl_inv_seq_no.elementAt(k)+"' " +
			  		" AND FINANCIAL_YEAR='"+fin_yr+"'  "; //SB20171025
				//	System.out.println("PDF-Template: "+queryString);
					rset=stmt.executeQuery(queryString);
					if(rset.next())
						VPDF_Template_Allocated.add(rset.getString(1)==null?"0":rset.getString(1));
					else
						VPDF_Template_Allocated.add("0");
				}
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////GST Register//////////////SB20180217///////////
	public void Customer_DTL_Rpt()
	{
		try
		{
			queryString = "SELECT DISTINCT CUSTOMER_CD, VENDOR_NAME FROM DLNG_SERVICE_INVOICE_MST A, GST_VENDOR_MST B "+
						" WHERE CUSTOMER_CD=B.VENDOR_CD ORDER BY VENDOR_NAME ";
			//System.out.println("FMS7_CUSTOMER_MST Fetch Query = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{
				CUST_CD.add(rset.getString(1)==null?"":rset.getString(1));	
				CUST_NM.add(rset.getString(2)==null?"":rset.getString(2));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void Fetch_Invoice_data() throws Exception 
	{
		try {
			if(!invoice_type_rpt.equals("0")) {
				if(invoice_type_rpt.equals("AA")) {
					
				} else if(invoice_type_rpt.equals("C")) {
					
				} else if(invoice_type_rpt.equals("A")) {
					
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
///////////////////Invoice : check Approve and Print ////////////////////
	public void CheckApprove_INVOICE_DTL() //SB20171108
	{
		try
		{
			
			//String supplier_cd="1";
			
			 dt="01/"+month+"/"+year;
			
			String queryString1="select TO_CHAR(LAST_DAY(TO_DATE('"+dt+"','dd/mm/yyyy')),'DD/MM/YYYY') from DUAL";
			System.out.println("GST-INV:Type=Z:QRY-001: "+queryString1);
			rset=stmt.executeQuery(queryString1);
			if(rset.next())
			{
				end_dt=rset.getString(1);
			}
			String queryString2="SELECT STATE_CODE,STATE_NM FROM STATE_MST ORDER BY STATE_NM ";
			System.out.println("GST-INV:Type=Z:QRY-002: "+queryString2);
			rset=stmt.executeQuery(queryString2);
			while(rset.next())
			{
				state_cd.add(rset.getString(1)==null?"":rset.getString(1));
				state_nm.add(rset.getString(2)==null?"":rset.getString(2));
			}
			///////////////////////SB20170811: SAC/HSN Code/////////////////
			if(Integer.parseInt(month)<4){
				fin_yr=(Integer.parseInt(year)-1)+"-"+year;
			}else{
				fin_yr=year+"-"+(Integer.parseInt(year)+1);
			}
		
			//////////////////////////////////////////////////////
			
			///	NewSeqNo=""+MaxSeqNo;
			System.out.println("**************: "+flag_act);
				GST_INVOICE_SEQ_NO=seq_no;
				String queryString="SELECT TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),TO_CHAR(DUE_DT,'DD/MM/YYYY'),"
						+ "NEW_INV_SEQ_NO,GROSS_AMT_INR,NET_AMT_INR,CUSTOMER_CD,TAX_AMT_INR,supplier_cd " +
						" ,CUSTOMER_NM,CUSTOMER_ADDR,CUSTOMER_STATE_CD,CUST_CITY,CUST_PIN_CODE, CUSTOMER_PAN_NO," +
						" supplier_state_cd,gate_pass_no,cust_gstin_no,sale_no,CUSTOMER_ABR" +
						" FROM DLNG_AC_INVOICE_MST "+
						" WHERE INV_SEQ_NO='"+Hlpl_seq_no+"' "+
						" AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"' " +
						" AND SUPPLIER_CD='"+supp_cd_set+"'"; //SB20180319
				System.out.println("GST-INV:Modify:QRY-008A: "+queryString);
				rset=stmt.executeQuery(queryString);
				while(rset.next())
				{
					Sinv_dt=rset.getString(1)==null?"":rset.getString(1);
					Sdue_dt=rset.getString(2)==null?"":rset.getString(2);
					Snew_inv_sq_no=rset.getString(3)==null?"":rset.getString(3);
					Sgross_amt_inr=rset.getString(4)==null?"":rset.getString(4);
					snet_amt_inr=rset.getString(5)==null?"":rset.getString(5);
					custom_cd=rset.getString(6)==null?"":rset.getString(6);
					tax_amt_inr=rset.getString(7)==null?"":rset.getString(7);
					supp_cd=rset.getString(8)==null?"":rset.getString(8);
				////	supp_cd=rset.getString(9)==null?"":rset.getString(9);
					cust_nm=rset.getString(9)==null?"":rset.getString(9);
					cust_addr=rset.getString(10)==null?"":rset.getString(10);
					cust_state_cd1=rset.getString(11)==null?"":rset.getString(11);
					cust_city=rset.getString(12)==null?"":rset.getString(12);
					cust_pin=rset.getString(13)==null?"":rset.getString(13);
					sCust_pan_no=rset.getString(14)==null?"":rset.getString(14);
					Supp_stat_CD=rset.getString(15)==null?"":rset.getString(15);
					gate_no=rset.getString(16)==null?"":rset.getString(16);
					Cust_gstin_no11=rset.getString(17)==null?"":rset.getString(17);
					sale_no=rset.getString(18)==null?"":rset.getString(18);
					SVendor_abr=rset.getString(19)==null?"":rset.getString(19); //SB20180406
				}
				
				if(!Sgross_amt_inr.equalsIgnoreCase("")) {
					Sgross_amt_inr_chk=nf1.format(Math.round(Double.parseDouble(Sgross_amt_inr)));
				}
				if(!tax_amt_inr.equalsIgnoreCase("")) {
					tax_amt_inr_chk=nf1.format(Math.round(Double.parseDouble(tax_amt_inr)));
				}
				if(!snet_amt_inr.equalsIgnoreCase("")) {
					System.out.println("*** snet_amt_inr = "+snet_amt_inr);
					//snet_amt_inr_chk=nf3.format(Math.round(Double.parseDouble(snet_amt_inr))); System.out.println("snet_amt_inr_chk = "+snet_amt_inr_chk);
					snet_amt_inr_chk=nf1.format(Math.round(Double.parseDouble(snet_amt_inr)));
					System.out.println("snet_amt_inr_chk = "+snet_amt_inr_chk);
				}
				////////////////////////////////MODIFY: SUpplier Dtl//////////////////////////////////////////////
				queryString = "SELECT supplier_name,PAN_NO, gstin_no,SUPPLIER_CD, SUPPLIER_ABBR  " +
						" FROM FMS7_SUPPLIER_MST A "+ 
						" WHERE A.supplier_cd='"+supp_cd+"'  AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_MST B "+
						" WHERE A.supplier_cd=B.supplier_cd  AND B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY'))";
				System.out.println("GST-INV:Modify:QRY-008B: "+queryString);
				rset = stmt.executeQuery(queryString);
				while(rset.next())
				{
					supplier_nm=rset.getString(1)==null?"":rset.getString(1);
					contact_Suppl_PAN_NO = rset.getString(2)==null?"":rset.getString(2);	
					contact_Suppl_gstin_no = rset.getString(3)==null?"":rset.getString(3);
					supplier_cd = rset.getString(4)==null?"":rset.getString(4);
					SuppAbr=rset.getString(5)==null?"BIPL":rset.getString(5); //SB20180222
				}
				queryString="SELECT ADDR, CITY, PIN, STATE, STATE_CODE " +
				" FROM FMS7_SUPPLIER_ADDRESS_MST A, STATE_MST B " +
				" WHERE SUPPLIER_CD='"+supp_cd+"' AND A.STATE=B.STATE_NM AND (A.FLAG='Y' OR  A.FLAG='T')  " +
				" AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B "+ 
				" WHERE A.supplier_cd=B.supplier_cd  AND  B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY'))";
				System.out.println("GST-INV:Type=Z:QRY-004A: "+queryString);
				rset=stmt.executeQuery(queryString);
				while(rset.next())
				{
					supp_addr=rset.getString(1)==null?"":rset.getString(1);
					supp_city=rset.getString(2)==null?"":rset.getString(2);
					supp_pin=rset.getString(3)==null?"":rset.getString(3);
					supp_state=rset.getString(4)==null?"":rset.getString(4);
					supp_state_cd=rset.getString(5)==null?"":rset.getString(5);
				}	
				
				String queryString31="SELECT Addr,city,pin " +
							" FROM FMS7_SUPPLIER_ADDRESS_MST A WHERE A.SUPPLIER_CD='"+supp_cd+"'  AND A.address_type='B' AND " +
							" A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B WHERE A.supplier_cd=B.supplier_cd AND B.address_type='B' AND " +
							" B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY'))   ";
							System.out.println("GST-INV:Modify:QRY-008C: "+queryString31);			
							rset=stmt.executeQuery(queryString31);
							if(rset.next())
							{
								Supp_addr_nm=rset.getString(1)==null?"":rset.getString(1);
								Supp_addr_city=rset.getString(2)==null?"":rset.getString(2);
								pin=rset.getString(3)==null?"":rset.getString(3);
							}
							else
							{
							String queryString21="SELECT addr,city,pin " +
								" FROM FMS7_SUPPLIER_ADDRESS_MST A WHERE SUPPLIER_CD='"+supp_cd+"'  AND A.address_type='C' AND " +
								" A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B " +
								" WHERE A.supplier_cd=B.supplier_cd AND B.address_type='C' AND " +
								" B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY')) ";
								rset=stmt.executeQuery(queryString21);
								if(rset.next())
								{
									Supp_addr_nm=rset.getString(1)==null?"":rset.getString(1);
									Supp_addr_city=rset.getString(2)==null?"":rset.getString(2);
									pin=rset.getString(3)==null?"":rset.getString(3);
									
								}
								else
								{
								String queryString22="SELECT addr,city,pin " +
									" FROM FMS7_SUPPLIER_ADDRESS_MST A WHERE SUPPLIER_CD='"+supp_cd+"'  AND A.address_type='R' AND " +
									" A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B WHERE A.supplier_cd=B.supplier_cd AND B.address_type='R' AND " +
									" B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY')) ";
									rset=stmt.executeQuery(queryString22);
									if(rset.next())
									{
										Supp_addr_nm=rset.getString(1)==null?"":rset.getString(1);
										Supp_addr_city=rset.getString(2)==null?"":rset.getString(2);
										pin=rset.getString(3)==null?"":rset.getString(3);
									}
								}
							}
				/////////////////MODIFY: Customer Address///////////////////////////////			
				/*SB	String queryString211="SELECT CUSTOMER_NM,CUSTOMER_ADDR,CUSTOMER_STATE_CD,CUST_CITY,"+
							 " CUST_PIN_CODE,TAX_CD,sac_description,sac_code,CUSTOMER_PAN_NO,supplier_state_cd,gate_pass_no,cust_gstin_no,sale_no "+
						//SB20180207	 " from fms8_other_invoice_dtl where "+
							 " from DLNG_SERVICE_INVOICE_DTL where "+
							 " INV_SEQ_NO='"+Hlpl_seq_no+"' AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"'" +
							 " ORDER BY LINE_NO DESC"; //SB20180213
					System.out.println("GST-INV:Modify:QRY-008D: "+queryString211);			
					rset=stmt.executeQuery(queryString211);
					if(rset.next())
					{
						cust_nm=rset.getString(1)==null?"":rset.getString(1);
						cust_addr=rset.getString(2)==null?"":rset.getString(2);
						cust_state_cd1=rset.getString(3)==null?"":rset.getString(3);
						cust_city=rset.getString(4)==null?"":rset.getString(4);
						cust_pin=rset.getString(5)==null?"":rset.getString(5);
						tax_cd=rset.getString(6)==null?"":rset.getString(6);
						//item_desc=rset.getString(7)==null?"":rset.getString(7);
//						sac_descr=rset.getString(7)==null?"":rset.getString(7);
//						sac_cd=rset.getString(8)==null?"":rset.getString(8);
						sCust_pan_no=rset.getString(9)==null?"":rset.getString(9);
						
						Supp_stat_CD=rset.getString(10)==null?"":rset.getString(10);
						gate_no=rset.getString(11)==null?"":rset.getString(11);
						Cust_gstin_no11=rset.getString(12)==null?"":rset.getString(12);
						sale_no=rset.getString(13)==null?"":rset.getString(13);
						//flag_sac=rset.getString(13)==null?"":rset.getString(13);						
					}
				*/
					String queryString212="SELECT STATE_NM FROM STATE_MST WHERE STATE_CoDe='"+cust_state_cd1+"' ";
					rset=stmt.executeQuery(queryString212);
					if(rset.next())
					{
						state_nm1=rset.getString(1)==null?"":rset.getString(1);
					}
					
					String queryString212a="SELECT STATE_NM FROM STATE_MST WHERE STATE_CoDe='"+Supp_stat_CD+"' ";
					rset=stmt.executeQuery(queryString212a);
					if(rset.next())
					{
						state=rset.getString(1)==null?"":rset.getString(1);
					}
					//System.out.println("sdsdsd---"+state);
					String queryString212ab1="SELECT Distinct (flag_sac),  TAX_CD "+
						//SB20180207	" from fms8_other_invoice_dtl " +
							 " from DLNG_AC_INVOICE_DTL "+
							" where INV_SEQ_NO='"+Hlpl_seq_no+"' AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"'";
					System.out.println("GST-INV:Modify:QRY-008E: "+queryString212ab1);			
					rset=stmt.executeQuery(queryString212ab1);
					if(rset.next()){
						flag_sac=rset.getString(1)==null?"":rset.getString(1);
						tax_cd=rset.getString(2)==null?"":rset.getString(2);
					}
				
					if(flag_sac.equalsIgnoreCase("P")) 
					{
						String queryString212ab="SELECT ITEM_description,rate,cargo_amount,quantity,hsn_code,UAM_NO " +
						///		", RATE_CGST, RATE_SGST, RATE_IGST, IGST_AMT, CGST_AMT, SGST_AMT, HSN_CODE, SAC_CODE, CESS_RATE, CESS_AMOUNT "+ //SB20180301
							//SB20180207	" from fms8_other_invoice_dtl " +
								 " from DLNG_AC_INVOICE_DTL "+
								" where INV_SEQ_NO='"+Hlpl_seq_no+"' AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"'" +
								" ORDER BY LINE_NO ";
						System.out.println("GST-INV:Modify:QRY-008F: "+queryString212ab);
						rset=stmt.executeQuery(queryString212ab);
						while(rset.next())
						{
							Vitm.add(rset.getString(1)==null?"":rset.getString(1));
							VCrate.add(rset.getString(2)==null?"":rset.getString(2));
							VCamt.add(rset.getString(3)==null?"":rset.getString(3));
							VCqty.add(rset.getString(4)==null?"":rset.getString(4));
							Vvsac_cd.add(rset.getString(5)==null?"":rset.getString(5));
							Vuam_no.add(rset.getString(6)==null?"":rset.getString(6));
							
					///		VCGST_Perc.add(nf.format(rset.getDouble(7)));	
					///		VSGST_Perc.add(nf.format(rset.getDouble(8)));	
							//if(rset1.getDouble(9)==0)
							//	VIGST_Perc.add(nf.format(rset1.getDouble(7)+rset1.getDouble(8)));		
						//	else
					///			VIGST_Perc.add(nf.format(rset.getDouble(9)));									
					///		VIGST_Amt.add(nf.format(rset.getDouble(10)));
					///		VCGST_Amt.add(nf.format(rset.getDouble(11)));
					///		VSGST_Amt.add(nf.format(rset.getDouble(12)));
					///		VHSN_CD.add(rset.getString(13)==null?"":rset.getString(13));
					///		VSAC_CD.add(rset.getString(14)==null?"":rset.getString(14));	
					///		VCESS_Perc.add(nf.format(rset.getDouble(15)));
					///		VCESS_Amt.add(nf.format(rset.getDouble(16)));					
						}
					}
					else
					{						
						String queryString212ab="SELECT ITEM_description,rate,cargo_amount,quantity,sac_code,UAM_NO "+
				///		", RATE_CGST, RATE_SGST, RATE_IGST, IGST_AMT, CGST_AMT, SGST_AMT, HSN_CODE, SAC_CODE, CESS_RATE, CESS_AMOUNT "+ //SB20180301
							//SB20180207	" from fms8_other_invoice_dtl " +
								 " from DLNG_AC_INVOICE_DTL "+
								" where INV_SEQ_NO='"+Hlpl_seq_no+"' AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"'" +
								" ORDER BY LINE_NO "; //SB20180213
						System.out.println("GST-INV:Modify:QRY-008G: "+queryString212ab);
						rset=stmt.executeQuery(queryString212ab);
						while(rset.next())
						{
							Vitm.add(rset.getString(1)==null?"":rset.getString(1));
							VCrate.add(rset.getString(2)==null?"":rset.getString(2));
							VCamt.add(rset.getString(3)==null?"":rset.getString(3));
							VCqty.add(rset.getString(4)==null?"":rset.getString(4));
							Vvsac_cd.add(rset.getString(5)==null?"":rset.getString(5));
							Vuam_no.add(rset.getString(6)==null?"":rset.getString(6));	
							camt.add(nf1.format(Double.parseDouble(rset.getString(3))));
							crt.add(nf1.format(Double.parseDouble(rset.getString(2))));
					///		VCGST_Perc.add(nf.format(rset.getDouble(7)));	
					///		VSGST_Perc.add(nf.format(rset.getDouble(8)));	
							//if(rset1.getDouble(9)==0)
							//	VIGST_Perc.add(nf.format(rset1.getDouble(7)+rset1.getDouble(8)));		
						//	else
					////			VIGST_Perc.add(nf.format(rset.getDouble(9)));									
					////		VIGST_Amt.add(nf.format(rset.getDouble(10)));
					////		VCGST_Amt.add(nf.format(rset.getDouble(11)));
					////		VSGST_Amt.add(nf.format(rset.getDouble(12)));
					///		VHSN_CD.add(rset.getString(13)==null?"":rset.getString(13));
					/////		VSAC_CD.add(rset.getString(14)==null?"":rset.getString(14));	
					////		VSAC_CD.add(rset.getString(14)+"-"+rset.getString(9));	
					///		VCESS_Perc.add(nf.format(rset.getDouble(15)));
					///		VCESS_Amt.add(nf.format(rset.getDouble(16)));		
						}
					}
					
					
					System.out.println("-tax_cd: "+tax_cd);
		//////////////////////TAX CODE //////////////////////////////////
				if(tax_cd.equalsIgnoreCase("C"))
				{						
				///		String query1="SELECT DISTINCT RATE_CGST,RATE_SGST, HSN_CODE, SAC_CODE " +
						String query1="SELECT DISTINCT RATE_CGST,RATE_SGST " +
								 " FROM DLNG_AC_INVOICE_DTL "+
								" WHERE INV_SEQ_NO='"+Hlpl_seq_no+"' AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"' " +
								" ORDER BY RATE_CGST";
						System.out.println("GST-INV:Modify:QRY-008G1: "+query1);
						rset=stmt.executeQuery(query1);
						while(rset.next())
						{
							taxstr.add(rset.getString(1)==null?"":rset.getString(1));
							taxstr.add(rset.getString(2)==null?"":rset.getString(2));
							VCGST_Perc.add(nf.format(rset.getDouble(1)));	
							VSGST_Perc.add(nf.format(rset.getDouble(2)));	
					///		VHSN_CD.add(rset.getString(3));
					///		VSAC_CD.add(rset.getString(4));
						}
						System.out.println("MODIFY-taxstr: "+taxstr);
						for(int i=0;i<VCGST_Perc.size();i++)
						{
							query1="SELECT SUM(CGST_AMT), SUM(SGST_AMT) " +
							 " FROM DLNG_AC_INVOICE_DTL "+
							" WHERE INV_SEQ_NO='"+Hlpl_seq_no+"' "
							+ "AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"' AND RATE_CGST='"+VCGST_Perc.elementAt(i)+"' ";
						rset=stmt.executeQuery(query1);
						if(rset.next())
						{	
							Tax_nm.add("CGST@"+(nf4.format(Double.parseDouble(""+VCGST_Perc.elementAt(i)))+"%"));	
							Tax_nm.add("SGST@"+(nf4.format(Double.parseDouble(""+VSGST_Perc.elementAt(i)))+"%"));	
							VCGST_Amt.add(nf4.format(rset.getDouble(1)));
							VSGST_Amt.add(nf4.format(rset.getDouble(2)));
							 Ttax_amt_inr.add(nf4.format(rset.getDouble(1)));
							 Ttax_amt_inr.add(nf4.format(rset.getDouble(2)));
							 Ttax_amt_inr_chk.add(nf4.format(rset.getDouble(1)));
							 Ttax_amt_inr_chk.add(nf4.format(rset.getDouble(2)));
						}
						}
						System.out.println("MODIFY-Ttax_amt_inr: "+Ttax_amt_inr);	
						
				}
				else if(tax_cd.equalsIgnoreCase("I"))
				{
				///		String query1="SELECT DISTINCT RATE_IGST, HSN_CODE, SAC_CODE "+ //SB20180301
						String query1="SELECT DISTINCT RATE_IGST "+ //SB20180301
								" FROM DLNG_AC_INVOICE_DTL "+
								" WHERE INV_SEQ_NO='"+Hlpl_seq_no+"' "+
								" AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"'  AND RATE_IGST >0 " +
								" ORDER BY RATE_IGST";
						System.out.println("GST-INV:Modify:QRY-008G2: "+query1);
						rset=stmt.executeQuery(query1);
						while(rset.next())
						{
							taxstr.add(rset.getString(1)==null?"":rset.getString(1));
							VIGST_Perc.add(nf.format(rset.getDouble(1)));									
						///	VHSN_CD.add(rset.getString(2));
						///	VSAC_CD.add(rset.getString(3));
						}
						System.out.println("IGST-taxstr: "+taxstr);
						 
						for(int i=0;i<VIGST_Perc.size();i++)
						{
							query1="SELECT SUM(IGST_AMT) " +
								 " FROM DLNG_AC_INVOICE_DTL "+
								" WHERE INV_SEQ_NO='"+Hlpl_seq_no+"' "
								+ "AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"' AND RATE_IGST='"+VIGST_Perc.elementAt(i)+"' ";
							rset=stmt.executeQuery(query1);
							if(rset.next())
							{	
								////Tax_nm.add("IGST@"+(nf4.format(Double.parseDouble(""+VIGST_Perc.elementAt(i)))+"%"+VSAC_CD.elementAt(i)));	
								Tax_nm.add("IGST@"+(nf4.format(Double.parseDouble(""+VIGST_Perc.elementAt(i)))+"%"));	
								VIGST_Amt.add(nf4.format(rset.getDouble(1)));
								 Ttax_amt_inr.add(nf4.format(rset.getDouble(1)));
								 Ttax_amt_inr_chk.add(nf4.format(rset.getDouble(1)));
							}
						}												
				}
				System.out.println("MODIFY-Tax_nm: "+Tax_nm);
////////////////////////////////TAX CODE///////////////////
				String queryString22="SELECT addr,city,pin,phone,fax_1,country " +
						  " FROM FMS7_SUPPLIER_ADDRESS_MST A WHERE SUPPLIER_CD='"+supplier_cd+"'  AND A.address_type='R' AND " +
						  " A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_SUPPLIER_ADDRESS_MST B " +
						  " WHERE A.supplier_cd=B.supplier_cd AND B.address_type='R' AND B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY')) ";
				System.out.println("GST-INV:Modify:QRY-008H: "+queryString22);	
				rset=stmt.executeQuery(queryString22);
							if(rset.next())
							{
								Supp_addr_nm_R=rset.getString(1)==null?"":rset.getString(1);
								Supp_addr_city_R=rset.getString(2)==null?"":rset.getString(2);
								pin_R=rset.getString(3)==null?"":rset.getString(3);
								phone_R=rset.getString(4)==null?"":rset.getString(4);
								fax_R=rset.getString(5)==null?"":rset.getString(5);
								cntry_R=rset.getString(6)==null?"":rset.getString(6);
						}
			/*SB20180209	String queryString221="SELECT A.RULE_REMARK " +
						" FROM FMS7_LNG_SALES_MAPPING A " +
						" WHERE A.SUPPLIER_CD='"+supp_cd+"' AND A.CONTRACT_TYPE='"+inv_type+"' and "+
						" A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_LNG_SALES_MAPPING B WHERE A.supplier_cd=B.supplier_cd AND B.contract_type='"+inv_type+"' and " +
						" B.eff_dt<=TO_DATE('"+dt+"','DD/MM/YYYY')) ";
						System.out.println("dsdsd---"+queryString221);
							rset=stmt.executeQuery(queryString221);
							if(rset.next())
							{
								rule_rmk=rset.getString(1)==null?"":rset.getString(1);
							}			
				*/
					if(!snet_amt_inr.equalsIgnoreCase("")) 
					{	
						snet_amt_inr=nf5.format(Math.round(Double.parseDouble(snet_amt_inr)));
						/*SB20170831: International	EnglishNumberToWords es=new EnglishNumberToWords();
						es.convert(Double.parseDouble(snet_amt_inr));
						*/					
						NumWord es=new NumWord();
						es.convertNumToWord(Integer.parseInt(snet_amt_inr));
						result=es.convertNumToWord(Integer.parseInt(snet_amt_inr));
					}
					///////////////////////SB20111104: VENDOR/Customer ABR /////////////////
					String queryStringdt11="Select trans_cd,trans_abbr,trans_name "
						+ "from  DLNG_TRANS_MST where trans_cd='"+cust_cd+"'  ";
					System.out.println("GST-INV:Type=Z:QRY-006: "+queryStringdt11);
					rset=stmt.executeQuery(queryStringdt11);
					if(rset.next())
					{
						SVendor_nm=rset.getString(3)==null?"":rset.getString(3);
						SVendor_abr=rset.getString(2)==null?"*":rset.getString(2); //SB20171104
					}
					/////////////////////SAC/HSN details://///////////////////////////
					String query1="SELECT DISTINCT SAC_CODE, HSN_CODE "+ //SB20180301
					" FROM DLNG_AC_INVOICE_DTL "+
					" WHERE INV_SEQ_NO='"+Hlpl_seq_no+"' "+
					" AND FINANCIAL_YEAR='"+VFin_yr+"' AND CONTRACT_TYPE='"+inv_type+"'  AND RATE_IGST >0 " +
					" ORDER BY SAC_CODE, HSN_CODE ";
					System.out.println("GST-INV:Modify:QRY-008G2: "+query1);
					rset=stmt.executeQuery(query1);
					while(rset.next())
					{				
						VSAC_CD.add(rset.getString(1)==null?"0":rset.getString(1));
						VHSN_CD.add(rset.getString(2)==null?"0":rset.getString(2));
					}
					for(int i=0;i<VSAC_CD.size();i++)
					{
						queryString="SELECT supplier_cd,SAC_CODE,DESCRIPTION, TAX_PERC " +
							" FROM GST_SERVICE_MST WHERE (SAC_CODE='"+VSAC_CD.elementAt(i)+"' OR SAC_CODE='"+VHSN_CD.elementAt(i)+"') ";		
						System.out.println("GST-INV:Type=Z:QRY-002A: "+queryString);
						rset=stmt.executeQuery(queryString);
						while(rset.next())
						{
							Vsac_cd.add(rset.getString(2)==null?"":rset.getString(2));
							Vdesc.add(rset.getString(3)==null?"":rset.getString(3));
							Vsac_hsn_perc.add(rset.getString(4)==null?"0":rset.getString(4));
						}
					}
					System.out.println("GST-INV:Vdesc: "+Vdesc);
					////////////////////////////////////////////////////////////////////////
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
/////////////////////////////////////////////////////////////////////////
	public void fetch_gst_register() throws Exception 
	{
		try 
		{
			from_dt="01/"+month+"/"+year;
			queryString = "Select To_char(Last_Day(to_date('"+to_month+"/"+to_year+"','mm/yyyy')),'dd/mm/yyyy') from dual";
			rset = stmt.executeQuery(queryString);			
			if(rset.next())
			{
				to_dt = rset.getString(1)==null?"0":rset.getString(1);
			}
			String MappId="";
			String igstPerc=""; String cgstPerc=""; String sgstPerc=""; String cessPerc=""; int CountFlag=0; String codeHSN=""; String codeSAC="";
			double Total_IGST_Amt=0;double Total_SGST_Amt=0;double Total_CGST_Amt=0;
			
			queryString = "Select INV_SEQ_NO, MAPPING_ID,NEW_INV_SEQ_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),financial_year " +
					", GROSS_AMT_INR, NET_AMT_INR, TAX_AMT_INR , VENDOR_NAME, CUST_GSTIN_NO, CONTRACT_TYPE "+ 
					" FROM DLNG_SERVICE_INVOICE_MST A, GST_VENDOR_MST B "+
					" WHERE A.CUSTOMER_CD=B.VENDOR_CD AND INVOICE_DT between (to_date('"+from_dt+"','dd/mm/yyyy')) and (to_date('"+to_dt+"','dd/mm/yyyy')) " +
					" ORDER BY INVOICE_DT, INV_SEQ_NO ";
			System.out.println("DLNG_SERVICE_INVOICE_MST  = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{
				VInv_SeqNo.add(rset.getString(1)==null?"":rset.getString(1));	
				VMapping_Id.add(rset.getString(2)==null?"":rset.getString(2));
				VNew_Inv_SeqNo.add(rset.getString(3)==null?"":rset.getString(3));	
				VInv_Dt.add(rset.getString(4)==null?"":rset.getString(4));	
				VFinancial_Yr.add(rset.getString(5)==null?"":rset.getString(5));	
				VGross_Amt_Inr.add(rset.getString(6)==null?"":rset.getString(6));	
				VNet_Amt_Inr.add(nf.format(rset.getDouble(7)));	
				VTax_Amt_Inr.add(nf.format(rset.getDouble(8)));	
				VTaxable_Amt_Inr.add(nf.format(rset.getDouble(7)-rset.getDouble(8)));	
				VCust_Nm.add(rset.getString(9)==null?"":rset.getString(9));	
				VCust_GSTIN_No.add(rset.getString(10)==null?"":rset.getString(10));	
				if(rset.getString(11).equals("Z"))
					VInv_Type.add("Regular");
				else if(rset.getString(11).equals("A"))
					VInv_Type.add("Advance");
				else
					VInv_Type.add("");
				VReverse_Charge.add("N");	
				MappId=rset.getString(2);
				String StateCode[]=MappId.split("-");
				queryString="SELECT STATE_NM FROM STATE_MST WHERE STATE_CoDe='"+StateCode[6]+"' ";
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next())
				{
					state=rset1.getString(1)==null?"":rset1.getString(1);
					VPlace_of_Supply.add(StateCode[6]+"-"+state);
				}
				igstPerc="";  cgstPerc="";  sgstPerc="";  cessPerc="";  CountFlag=0;  codeHSN="";  codeSAC="";
				queryString = "SELECT  SAC_CODE, HSN_CODE, RATE_CGST,RATE_SGST, NVL(RATE_IGST,'0'), CESS_RATE  "+
				" FROM DLNG_SERVICE_INVOICE_DTL  "+ 
				" WHERE INV_SEQ_NO ='"+rset.getString(1)+"' AND FINANCIAL_YEAR='"+rset.getString(5)+"' AND MAPPING_ID = '"+rset.getString(2)+"' AND (SAC_CODE IS NOT NULL OR HSN_CODE IS NOT NULL) ";
		//		System.out.println("DLNG_SERVICE_INVOICE_MST  = "+queryString);
				rset1 = stmt1.executeQuery(queryString);				
				while(rset1.next())
				{
					if(CountFlag==0)
						codeSAC =rset1.getString(1)==null?"":rset1.getString(1);
					else if(CountFlag>0)
						codeSAC+="*";
					if(CountFlag==0)
						codeHSN =rset1.getString(2)==null?" ":rset1.getString(2);
					else if(CountFlag>0)
						codeHSN+="*";
					
					if(rset1.getDouble(3)>=0 && CountFlag==0)
						cgstPerc +=nf.format(rset1.getDouble(3));
					else if(rset1.getDouble(3)>0 && CountFlag>0)
						cgstPerc+="*";
					
					if(rset1.getDouble(4)>=0 && CountFlag==0)
						sgstPerc +=nf.format(rset1.getDouble(4));
					else if(rset1.getDouble(4)>0 && CountFlag>0)
						sgstPerc+="*";
					
					if(rset1.getDouble(5)>=0 && CountFlag==0)
						igstPerc +=nf.format(rset1.getDouble(5));
					else if(rset1.getDouble(5)>0 && CountFlag>0)
						igstPerc+="*";
					
					if(rset1.getDouble(6)>=0 && CountFlag==0)
						cessPerc +=nf.format(rset1.getDouble(6));
					else if(rset1.getDouble(6)>0 && CountFlag>0)
						cessPerc+="*";
					
					CountFlag++;
				}
				VSAC_CD.add(codeSAC);	
				VHSN_CD.add(codeHSN);	
				VCGST_Perc.add(cgstPerc);	
				VSGST_Perc.add(sgstPerc);	
				VIGST_Perc.add(igstPerc);		
				VCESS_Perc.add(cessPerc);
				queryString = "SELECT  SUM(CESS_AMOUNT), SUM(CARGO_AMOUNT) " +
				", SUM(IGST_AMT), SUM(CGST_AMT), SUM(SGST_AMT) "+
				" FROM DLNG_SERVICE_INVOICE_DTL  "+ 
				" WHERE INV_SEQ_NO ='"+rset.getString(1)+"' AND FINANCIAL_YEAR='"+rset.getString(5)+"' AND MAPPING_ID = '"+rset.getString(2)+"' " +
				" AND (SAC_CODE IS NOT NULL OR HSN_CODE IS NOT NULL) ";
			//	System.out.println("DLNG_SERVICE_INVOICE_MST  = "+queryString);
				rset1 = stmt1.executeQuery(queryString);				
				while(rset1.next())
				{					
					VIGST_Amt.add(nf.format(rset1.getDouble(3)));
					VCGST_Amt.add(nf.format(rset1.getDouble(4)));
					VSGST_Amt.add(nf.format(rset1.getDouble(5)));
					Total_IGST_Amt+=rset1.getDouble(3);
					Total_CGST_Amt+=rset1.getDouble(4);
					Total_SGST_Amt+=rset1.getDouble(5);
				}
			}
			Sum_IGST_Amt=nf.format(Total_IGST_Amt);
			Sum_CGST_Amt=nf.format(Total_CGST_Amt);
			Sum_SGST_Amt=nf.format(Total_SGST_Amt);
//			SB	queryString=" SELECT SUM(NET_AMT_INR), SUM(NET_AMT_INR-TAX_AMT_INR) FROM DLNG_SERVICE_INVOICE_MST  "+
			queryString=" SELECT SUM(NET_AMT_INR), SUM(GROSS_AMT_INR), SUM(TAX_AMT_INR) FROM DLNG_SERVICE_INVOICE_MST  "+
				" WHERE  INVOICE_DT between (to_date('"+from_dt+"','dd/mm/yyyy')) and (to_date('"+to_dt+"','dd/mm/yyyy')) " +
				" ORDER BY INVOICE_DT "; 
			rset1=stmt1.executeQuery(queryString);
			if(rset1.next())
			{
				Sum_Net_Amt=nf.format(rset1.getDouble(1));
				Sum_Taxable_Amt=nf.format(rset1.getDouble(2));
				Sum_Tax_Amt=nf.format(rset1.getDouble(3));
			}
			queryString=" SELECT COUNT(DISTINCT(CUSTOMER_CD)) FROM DLNG_SERVICE_INVOICE_MST  "+
			" WHERE  INVOICE_DT between (TO_DATE('"+from_dt+"','dd/mm/yyyy')) and (TO_DATE('"+to_dt+"','dd/mm/yyyy')) " +
			"  "; 
			rset1=stmt1.executeQuery(queryString);
			if(rset1.next())
			{
				No_of_Customer=rset1.getString(1)==null?"0":rset1.getString(1);
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
}
	public void fetch_GST1_Return() throws Exception 
	{
		try 
		{
			from_dt="01/"+month+"/"+year;
			queryString = "Select To_char(Last_Day(to_date('"+to_month+"/"+to_year+"','mm/yyyy')),'dd/mm/yyyy') from dual";
			rset = stmt.executeQuery(queryString);			
			if(rset.next())
			{
				to_dt = rset.getString(1)==null?"0":rset.getString(1);
			}
			String MappId="";
			String igstPerc=""; String cgstPerc=""; String sgstPerc=""; String cessPerc=""; int CountFlag=0; String codeHSN=""; String codeSAC="";
			queryString = "Select INV_SEQ_NO, MAPPING_ID,NEW_INV_SEQ_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),financial_year " +
					", GROSS_AMT_INR, NET_AMT_INR, TAX_AMT_INR , VENDOR_NAME, CUST_GSTIN_NO, CONTRACT_TYPE "+ 
					" FROM DLNG_SERVICE_INVOICE_MST A, GST_VENDOR_MST B "+
					" WHERE A.CUSTOMER_CD=B.VENDOR_CD AND INVOICE_DT between (to_date('"+from_dt+"','dd/mm/yyyy')) and (to_date('"+to_dt+"','dd/mm/yyyy')) " +
					" ORDER BY INVOICE_DT, INV_SEQ_NO ";
		//	System.out.println("DLNG_SERVICE_INVOICE_MST  = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{
				VInv_SeqNo.add(rset.getString(1)==null?"":rset.getString(1));	
				VMapping_Id.add(rset.getString(2)==null?"":rset.getString(2));
				VNew_Inv_SeqNo.add(rset.getString(3)==null?"":rset.getString(3));	
				VInv_Dt.add(rset.getString(4)==null?"":rset.getString(4));	
				VFinancial_Yr.add(rset.getString(5)==null?"":rset.getString(5));	
				VGross_Amt_Inr.add(rset.getString(6)==null?"":rset.getString(6));	
				VNet_Amt_Inr.add(nf.format(rset.getDouble(7)));	
				VTax_Amt_Inr.add(nf.format(rset.getDouble(8)));	
				VTaxable_Amt_Inr.add(nf.format(rset.getDouble(7)-rset.getDouble(8)));	
				VCust_Nm.add(rset.getString(9)==null?"":rset.getString(9));	
				VCust_GSTIN_No.add(rset.getString(10)==null?"":rset.getString(10));	
				if(rset.getString(11).equals("Z"))
					VInv_Type.add("Regular");
				else if(rset.getString(11).equals("A"))
					VInv_Type.add("Advance");
				else
					VInv_Type.add("");
				VReverse_Charge.add("N");	
				MappId=rset.getString(2);
				String StateCode[]=MappId.split("-");
				queryString="SELECT STATE_NM FROM STATE_MST WHERE STATE_CoDe='"+StateCode[6]+"' ";
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next())
				{
					state=rset1.getString(1)==null?"":rset1.getString(1);
					VPlace_of_Supply.add(StateCode[6]+"-"+state);
				}
				igstPerc=""; cgstPerc=""; sgstPerc=""; cessPerc=""; CountFlag=0; codeHSN=""; codeSAC="";
				queryString = "SELECT  SAC_CODE, HSN_CODE, RATE_CGST,RATE_SGST, NVL(RATE_IGST,'0'), CESS_RATE "+
				" FROM DLNG_SERVICE_INVOICE_DTL  "+ 
				" WHERE INV_SEQ_NO ='"+rset.getString(1)+"' AND FINANCIAL_YEAR='"+rset.getString(5)+"' AND MAPPING_ID = '"+rset.getString(2)+"' AND (SAC_CODE IS NOT NULL OR HSN_CODE IS NOT NULL) ";
			//	System.out.println("DLNG_SERVICE_INVOICE_MST  = "+queryString);
				rset1 = stmt1.executeQuery(queryString);				
				while(rset1.next())
				{					
					if(rset1.getDouble(5)>=0 && CountFlag==0)
					{
						if(rset1.getDouble(5)==0)
							igstPerc +=nf.format(rset1.getDouble(3)+rset1.getDouble(4));		
						else
							igstPerc +=nf.format(rset1.getDouble(5));
					}
					else if(rset1.getDouble(5)>0 && CountFlag>0)
						igstPerc+="*";
					CountFlag++;
				}
				VSAC_CD.add(codeSAC);	
				VHSN_CD.add(codeHSN);	
				VCGST_Perc.add(cgstPerc);	
				VSGST_Perc.add(sgstPerc);	
				VIGST_Perc.add(igstPerc);		
				VCESS_Perc.add(cessPerc);
				queryString = "SELECT  SUM(CESS_AMOUNT), SUM(CARGO_AMOUNT) " +
				", SUM(IGST_AMT), SUM(CGST_AMT), SUM(SGST_AMT) "+
				" FROM DLNG_SERVICE_INVOICE_DTL  "+ 
				" WHERE INV_SEQ_NO ='"+rset.getString(1)+"' AND FINANCIAL_YEAR='"+rset.getString(5)+"' AND MAPPING_ID = '"+rset.getString(2)+"' " +
				" AND (SAC_CODE IS NOT NULL OR HSN_CODE IS NOT NULL) ";
			//	System.out.println("DLNG_SERVICE_INVOICE_MST  = "+queryString);
				rset1 = stmt1.executeQuery(queryString);				
				while(rset1.next())
				{					
					VIGST_Amt.add(nf.format(rset1.getDouble(3)));
					VCGST_Amt.add(nf.format(rset1.getDouble(4)));
					VSGST_Amt.add(nf.format(rset1.getDouble(5)));
				}
			}
			queryString=" SELECT COUNT(DISTINCT(CUSTOMER_CD)) FROM DLNG_SERVICE_INVOICE_MST  "+
			" WHERE  INVOICE_DT between (to_date('"+from_dt+"','dd/mm/yyyy')) and (to_date('"+to_dt+"','dd/mm/yyyy')) " +
			"  "; 
			rset1=stmt1.executeQuery(queryString);
			if(rset1.next())
			{
				No_of_Customer=rset1.getString(1)==null?"0":rset1.getString(1);
			}
			//SB queryString=" SELECT SUM(NET_AMT_INR), SUM(NET_AMT_INR-TAX_AMT_INR) FROM DLNG_SERVICE_INVOICE_MST  "+
			queryString=" SELECT SUM(NET_AMT_INR), SUM(GROSS_AMT_INR), SUM(TAX_AMT_INR) FROM DLNG_SERVICE_INVOICE_MST  "+
				" WHERE  INVOICE_DT between (to_date('"+from_dt+"','dd/mm/yyyy')) and (to_date('"+to_dt+"','dd/mm/yyyy')) " +
				" ORDER BY INVOICE_DT "; 
			rset1=stmt1.executeQuery(queryString);
			if(rset1.next())
			{
				Sum_Net_Amt=nf.format(rset1.getDouble(1));
				Sum_Taxable_Amt=nf.format(rset1.getDouble(2));
				Sum_Tax_Amt=nf.format(rset1.getDouble(3));
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
}
	public void fetch_GST3B_register() throws Exception 
	{
		try 
		{
			from_dt="01/"+month+"/"+year;
			queryString = "Select To_char(Last_Day(to_date('"+to_month+"/"+to_year+"','mm/yyyy')),'dd/mm/yyyy') from dual";
			rset = stmt.executeQuery(queryString);			
			if(rset.next())
			{
				to_dt = rset.getString(1)==null?"0":rset.getString(1);
			}
			String MappId="";
			queryString = "Select INV_SEQ_NO, MAPPING_ID,NEW_INV_SEQ_NO,TO_CHAR(INVOICE_DT,'DD/MM/YYYY'),financial_year " +
					", GROSS_AMT_INR, NET_AMT_INR, TAX_AMT_INR , VENDOR_NAME, CUST_GSTIN_NO, CONTRACT_TYPE "+ 
					" FROM DLNG_SERVICE_INVOICE_MST A, GST_VENDOR_MST B "+
					" WHERE A.CUSTOMER_CD=B.VENDOR_CD AND INVOICE_DT between (to_date('"+from_dt+"','dd/mm/yyyy')) and (to_date('"+to_dt+"','dd/mm/yyyy')) " +
				//			" AND CONTRACT_TYPE='"++"' " +
					" ORDER BY INVOICE_DT, INV_SEQ_NO ";
			System.out.println("DLNG_SERVICE_INVOICE_MST  = "+queryString);
			rset = stmt.executeQuery(queryString);				
			while(rset.next())
			{
				VInv_SeqNo.add(rset.getString(1)==null?"":rset.getString(1));	
				VMapping_Id.add(rset.getString(2)==null?"":rset.getString(2));
				VNew_Inv_SeqNo.add(rset.getString(3)==null?"":rset.getString(3));	
				VInv_Dt.add(rset.getString(4)==null?"":rset.getString(4));	
				VFinancial_Yr.add(rset.getString(5)==null?"":rset.getString(5));	
				VGross_Amt_Inr.add(rset.getString(6)==null?"":rset.getString(6));	
				VNet_Amt_Inr.add(nf.format(rset.getDouble(7)));	
				VTax_Amt_Inr.add(nf.format(rset.getDouble(8)));	
				VTaxable_Amt_Inr.add(nf.format(rset.getDouble(7)-rset.getDouble(8)));	
				VCust_Nm.add(rset.getString(9)==null?"":rset.getString(9));	
				VCust_GSTIN_No.add(rset.getString(10)==null?"":rset.getString(10));	
				if(rset.getString(11).equals("Z"))
					VInv_Type.add("Regular");
				else if(rset.getString(11).equals("A"))
					VInv_Type.add("Advance");
				else
					VInv_Type.add("");
				VReverse_Charge.add("N");	
				MappId=rset.getString(2);
				String StateCode[]=MappId.split("-");
				queryString="SELECT STATE_NM FROM STATE_MST WHERE STATE_CoDe='"+StateCode[6]+"' ";
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next())
				{
					state=rset1.getString(1)==null?"":rset1.getString(1);
					VPlace_of_Supply.add(StateCode[6]+"-"+state);
				}
				queryString=" SELECT COUNT(DISTINCT(CUSTOMER_CD)) FROM DLNG_SERVICE_INVOICE_MST  "+
				" WHERE  INVOICE_DT between (to_date('"+from_dt+"','dd/mm/yyyy')) and (to_date('"+to_dt+"','dd/mm/yyyy')) " +
				"  "; 
			rset1=stmt1.executeQuery(queryString);
			if(rset1.next())
			{
				No_of_Customer=rset1.getString(1)==null?"0":rset1.getString(1);
			}
			}
			double SumTaxableAmt=0; double SumIGSTAmt=0; double SumCGSTAmt=0; double SumSGSTAmt=0; double SumCESSAmt=0;
			String igstPerc=""; String cgstPerc=""; String sgstPerc=""; String cessPerc=""; int CountFlag=0; String codeHSN=""; String codeSAC="";
			for(int i=0; i<VInv_SeqNo.size(); i++)
			{
				
				queryString=" SELECT NVL(SUM(CARGO_AMOUNT),0), NVL(SUM(IGST_AMT),0), NVL(SUM(CGST_AMT),0), NVL(SUM(SGST_AMT),0), NVL(SUM(CESS_AMOUNT),0) " +
						" FROM DLNG_SERVICE_INVOICE_DTL  "+
						" WHERE INV_SEQ_NO ='"+VInv_SeqNo.elementAt(i)+"' AND FINANCIAL_YEAR='"+VFinancial_Yr.elementAt(i)+"' AND MAPPING_ID = '"+VMapping_Id.elementAt(i)+"' AND (SAC_CODE IS NOT NULL OR HSN_CODE IS NOT NULL) ";
		//		System.out.println("DLNG_SERVICE_INVOICE_DTL  = "+queryString);
				rset1=stmt1.executeQuery(queryString);
				if(rset1.next())
				{
					SumTaxableAmt+=Double.parseDouble(nf5.format(rset1.getDouble(1)));
					SumIGSTAmt +=Double.parseDouble(nf5.format(rset1.getDouble(2)));
					SumCGSTAmt +=Double.parseDouble(nf5.format(rset1.getDouble(3)));
					SumSGSTAmt +=Double.parseDouble(nf5.format(rset1.getDouble(4)));
					SumCESSAmt +=Double.parseDouble(nf5.format(rset1.getDouble(5)));					
				}
				igstPerc=""; cgstPerc=""; sgstPerc=""; cessPerc=""; CountFlag=0; codeHSN=""; codeSAC="";
				queryString = "SELECT  SAC_CODE, HSN_CODE, NVL(RATE_CGST,'0'),NVL(RATE_SGST,'0'), NVL(RATE_IGST,'0'), NVL(CESS_RATE,'0') "+
				" FROM DLNG_SERVICE_INVOICE_DTL  "+ 
				" WHERE INV_SEQ_NO ='"+VInv_SeqNo.elementAt(i)+"' AND FINANCIAL_YEAR='"+VFinancial_Yr.elementAt(i)+"' AND MAPPING_ID = '"+VMapping_Id.elementAt(i)+"' AND (SAC_CODE IS NOT NULL OR HSN_CODE IS NOT NULL) ";
			//	System.out.println("DLNG_SERVICE_INVOICE_MST  = "+queryString);
				rset1 = stmt1.executeQuery(queryString);				
				while(rset1.next())
				{
					if(CountFlag==0)
						codeSAC =rset1.getString(1)==null?"":rset1.getString(1);
					else if(CountFlag>0)
						codeSAC+="*";
					if(CountFlag==0)
						codeHSN =rset1.getString(2)==null?" ":rset1.getString(2);
					else if(CountFlag>0)
						codeHSN+="*";
					
					if(rset1.getDouble(3)>=0 && CountFlag==0)
						cgstPerc +=nf.format(rset1.getDouble(3));
					else if(rset1.getDouble(3)>0 && CountFlag>0)
						cgstPerc+="*";
					
					if(rset1.getDouble(4)>=0 && CountFlag==0)
						sgstPerc +=nf.format(rset1.getDouble(4));
					else if(rset1.getDouble(4)>0 && CountFlag>0)
						sgstPerc+="*";
					
					if(rset1.getDouble(5)>=0 && CountFlag==0)
						igstPerc +=nf.format(rset1.getDouble(5));
					else if(rset1.getDouble(5)>0 && CountFlag>0)
						igstPerc+="*";
					
					if(rset1.getDouble(6)>=0 && CountFlag==0)
						cessPerc +=nf.format(rset1.getDouble(6));
					else if(rset1.getDouble(6)>0 && CountFlag>0)
						cessPerc+="*";
					
					CountFlag++;
				}
				VSAC_CD.add(codeSAC);	
				VHSN_CD.add(codeHSN);	
				VCGST_Perc.add(cgstPerc);	
				VSGST_Perc.add(sgstPerc);	
				VIGST_Perc.add(igstPerc);		
				VCESS_Perc.add(cessPerc);	
				
				queryString = "SELECT  SUM(CESS_AMOUNT), SUM(CARGO_AMOUNT) " +
				", SUM(IGST_AMT), SUM(CGST_AMT), SUM(SGST_AMT) "+
				" FROM DLNG_SERVICE_INVOICE_DTL  "+ 
				" WHERE INV_SEQ_NO ='"+VInv_SeqNo.elementAt(i)+"' AND FINANCIAL_YEAR='"+VFinancial_Yr.elementAt(i)+"' AND MAPPING_ID = '"+VMapping_Id.elementAt(i)+"' " +
				" AND (SAC_CODE IS NOT NULL OR HSN_CODE IS NOT NULL) ";
			//	System.out.println("DLNG_SERVICE_INVOICE_MST  = "+queryString);
				rset1 = stmt1.executeQuery(queryString);				
				while(rset1.next())
				{					
					VIGST_Amt.add(nf.format(rset1.getDouble(3)));
					VCGST_Amt.add(nf.format(rset1.getDouble(4)));
					VSGST_Amt.add(nf.format(rset1.getDouble(5)));
				}
			}
			//////////////////SB20180425//////////////////////////////////
			double SumTaxableAmtNonZero=0; double SumIGSTAmtNonZero=0; double SumCGSTAmtNonZero=0; double SumSGSTAmtNonZero=0; double SumCESSAmtNonZero=0;
			double SumTaxableAmtZero=0; double SumIGSTAmtZero=0; double SumCGSTAmtZero=0; double SumSGSTAmtZero=0; double SumCESSAmtZero=0;
			double SumTaxableAmtNilRate=0; double SumIGSTAmtNilRate=0; double SumCGSTAmtNilRate=0; double SumSGSTAmtNilRate=0; double SumCESSAmtNilRate=0;
			double SumTaxableAmtInward=0; double SumIGSTAmtInward=0; double SumCGSTAmtInward=0; double SumSGSTAmtInward=0; double SumCESSAmtInward=0;
			double SumTaxableAmtNonGST=0; double SumIGSTAmtNonGST=0; double SumCGSTAmtNonGST=0; double SumSGSTAmtNonGST=0; double SumCESSAmtNonGST=0;

			for(int i=0; i<VInv_SeqNo.size(); i++)
			{
				
				queryString=" SELECT NVL(CARGO_AMOUNT,0), NVL(IGST_AMT,0), NVL(CGST_AMT,0), NVL(SGST_AMT,0), NVL(CESS_AMOUNT,0) " +
						" , (RATE_IGST+RATE_CGST+RATE_SGST)" +
						" FROM DLNG_SERVICE_INVOICE_DTL  "+
						" WHERE INV_SEQ_NO ='"+VInv_SeqNo.elementAt(i)+"' AND FINANCIAL_YEAR='"+VFinancial_Yr.elementAt(i)+"' AND MAPPING_ID = '"+VMapping_Id.elementAt(i)+"' " ;
						//		"AND (SAC_CODE IS NOT NULL OR HSN_CODE IS NOT NULL) ";
				//System.out.println("DLNG_SERVICE_INVOICE_DTL  = "+queryString);
				rset1=stmt1.executeQuery(queryString);
				while(rset1.next())
				{
				//	System.out.println(rset1.getDouble(6));
					if(rset1.getDouble(6)>0 && !VCust_GSTIN_No.elementAt(i).equals(""))
					{//System.out.println(">0 "+rset1.getDouble(6));
					SumTaxableAmtNonZero+=Double.parseDouble(nf5.format(rset1.getDouble(1)));
					SumIGSTAmtNonZero +=Double.parseDouble(nf5.format(rset1.getDouble(2)));
					SumCGSTAmtNonZero +=Double.parseDouble(nf5.format(rset1.getDouble(3)));
					SumSGSTAmtNonZero +=Double.parseDouble(nf5.format(rset1.getDouble(4)));
					SumCESSAmtNonZero +=Double.parseDouble(nf5.format(rset1.getDouble(5)));
					}
					else  if(rset1.getDouble(6)>0 && VCust_GSTIN_No.elementAt(i).equals(""))
					{//System.out.println(">0 NoGST: "+rset1.getDouble(6));
					SumTaxableAmtNonGST+=Double.parseDouble(nf5.format(rset1.getDouble(1)));
					SumIGSTAmtNonGST +=Double.parseDouble(nf5.format(rset1.getDouble(2)));
					SumCGSTAmtNonGST +=Double.parseDouble(nf5.format(rset1.getDouble(3)));
					SumSGSTAmtNonGST +=Double.parseDouble(nf5.format(rset1.getDouble(4)));
					SumCESSAmtNonGST +=Double.parseDouble(nf5.format(rset1.getDouble(5)));
					}
					else if(rset1.getDouble(6)==0)
					{//System.out.println("=0 "+rset1.getDouble(6));					
					SumTaxableAmtZero+=Double.parseDouble(nf5.format(rset1.getDouble(1)));
					SumIGSTAmtZero +=Double.parseDouble(nf5.format(rset1.getDouble(2)));
					SumCGSTAmtZero +=Double.parseDouble(nf5.format(rset1.getDouble(3)));
					SumSGSTAmtZero +=Double.parseDouble(nf5.format(rset1.getDouble(4)));
					SumCESSAmtZero +=Double.parseDouble(nf5.format(rset1.getDouble(5)));
					}
					else 
					{//System.out.println("ELSE "+rset1.getDouble(6));					
					SumTaxableAmtNilRate+=Double.parseDouble(nf5.format(rset1.getDouble(1)));
					SumIGSTAmtNilRate +=Double.parseDouble(nf5.format(rset1.getDouble(2)));
					SumCGSTAmtNilRate +=Double.parseDouble(nf5.format(rset1.getDouble(3)));
					SumSGSTAmtNilRate +=Double.parseDouble(nf5.format(rset1.getDouble(4)));
					SumCESSAmtNilRate +=Double.parseDouble(nf5.format(rset1.getDouble(5)));
					
					SumTaxableAmtInward+=Double.parseDouble(nf5.format(rset1.getDouble(1)));
					SumIGSTAmtInward +=Double.parseDouble(nf5.format(rset1.getDouble(2)));
					SumCGSTAmtInward +=Double.parseDouble(nf5.format(rset1.getDouble(3)));
					SumSGSTAmtInward +=Double.parseDouble(nf5.format(rset1.getDouble(4)));
					SumCESSAmtInward +=Double.parseDouble(nf5.format(rset1.getDouble(5)));
					}
				}
			}
		//	System.out.println("Sum-Tax>0 "+SumTaxableAmtNonZero);
		//	System.out.println("Sum-Tax==0 "+SumTaxableAmtZero);
			
				VParticular.add("Supply Taxable");
				VSum_Taxable_Amt.add(nf.format(SumTaxableAmtNonZero));
				VSum_IGST_Amt.add(nf.format(SumIGSTAmtNonZero));
				VSum_CGST_Amt.add(nf.format(SumCGSTAmtNonZero));
				VSum_SGST_Amt.add(nf.format(SumSGSTAmtNonZero));
				VSum_CESS_Amt.add(nf.format(SumCESSAmtNonZero));
				
				VParticular.add("Supply - Zero Rated");
				VSum_Taxable_Amt.add(nf.format(SumTaxableAmtZero));
				VSum_IGST_Amt.add(nf.format(SumIGSTAmtZero));
				VSum_CGST_Amt.add(nf.format(SumCGSTAmtZero));
				VSum_SGST_Amt.add(nf.format(SumSGSTAmtZero));
				VSum_CESS_Amt.add(nf.format(SumCESSAmtZero));
				////////////////////////////////////////////////////////////////////	//

			/////////////Need the following to be re-developed for dymic data/////////////////////////
				VParticular.add("Supply - NIL RATED/ EXEMPTED");
				VSum_Taxable_Amt.add(nf.format(SumTaxableAmtNilRate));
				VSum_IGST_Amt.add(nf.format(SumIGSTAmtNilRate));
				VSum_CGST_Amt.add(nf.format(SumCGSTAmtNilRate));
				VSum_SGST_Amt.add(nf.format(SumSGSTAmtNilRate));
				VSum_CESS_Amt.add(nf.format(SumCESSAmtNilRate));
				VParticular.add("INWARD SUPPLY - RCM");
				VSum_Taxable_Amt.add(nf.format(SumTaxableAmtInward));
				VSum_IGST_Amt.add(nf.format(SumIGSTAmtInward));
				VSum_CGST_Amt.add(nf.format(SumCGSTAmtInward));
				VSum_SGST_Amt.add(nf.format(SumSGSTAmtInward));
				VSum_CESS_Amt.add(nf.format(SumCESSAmtInward));
//////////////////////////////////////////////////////////////////////				
				VParticular.add("NON - GST SUPPLY");
				VSum_Taxable_Amt.add(nf.format(SumTaxableAmtNonGST));
				VSum_IGST_Amt.add(nf.format(SumIGSTAmtNonGST));
				VSum_CGST_Amt.add(nf.format(SumCGSTAmtNonGST));
				VSum_SGST_Amt.add(nf.format(SumSGSTAmtNonGST));
				VSum_CESS_Amt.add(nf.format(SumCESSAmtNonGST));
		
	/*SB20180607		for(int j=2; j<VParticular.size(); j++)
			{
				
				VSum_Taxable_Amt.add(nf.format(0));
				VSum_IGST_Amt.add(nf.format(0));
				VSum_CGST_Amt.add(nf.format(0));
				VSum_SGST_Amt.add(nf.format(0));
				VSum_CESS_Amt.add(nf.format(0));
			}
			*//////////////////////////////////////////////////////////////////////////////////////////
			VParticular.add("Total:");
			VSum_Taxable_Amt.add(nf.format(SumTaxableAmt));
			VSum_IGST_Amt.add(nf.format(SumIGSTAmt));
			VSum_CGST_Amt.add(nf.format(SumCGSTAmt));
			VSum_SGST_Amt.add(nf.format(SumSGSTAmt));
			VSum_CESS_Amt.add(nf.format(SumCESSAmt));
			
/////////////ITC  - Particulars/////////////////////////
			VITCParticular.add("(a) ITC AVAILALE (FULL/PART)");
			VITCSum_Taxable_Amt.add("");
			VITCSum_IGST_Amt.add("");
			VITCSum_CGST_Amt.add("");
			VITCSum_SGST_Amt.add("");
			VITCSum_CESS_Amt.add("");
			VITCParticular.add("IMPORT of  GOODS");
			VITCParticular.add("IMPORT of  SERVICES");
			VITCParticular.add("RCM on Inward supplies(Other than above)");
			VITCParticular.add("INWARD SUPPLIES FORM ISD");
			VITCParticular.add("ALL OTHER ITC");
		
			for(int j=1; j<VITCParticular.size(); j++)
			{				
				VITCSum_Taxable_Amt.add(nf.format(0));
				VITCSum_IGST_Amt.add(nf.format(0));
				VITCSum_CGST_Amt.add(nf.format(0));
				VITCSum_SGST_Amt.add(nf.format(0));
				VITCSum_CESS_Amt.add(nf.format(0));
			}
			VITCParticular.add("Total:");
			VITCSum_Taxable_Amt.add(nf.format(0));
			VITCSum_IGST_Amt.add(nf.format(0));
			VITCSum_CGST_Amt.add(nf.format(0));
			VITCSum_SGST_Amt.add(nf.format(0));
			VITCSum_CESS_Amt.add(nf.format(0));
			
			VITCParticular.add("(b) ITC REVERSED");
			VITCSum_Taxable_Amt.add("");
			VITCSum_IGST_Amt.add("");
			VITCSum_CGST_Amt.add("");
			VITCSum_SGST_Amt.add("");
			VITCSum_CESS_Amt.add("");
			VITCParticular.add("AS PER RULES 42 & 43 of CGST");
			VITCSum_Taxable_Amt.add(nf.format(0));
			VITCSum_IGST_Amt.add(nf.format(0));
			VITCSum_CGST_Amt.add(nf.format(0));
			VITCSum_SGST_Amt.add(nf.format(0));
			VITCSum_CESS_Amt.add(nf.format(0));
			VITCParticular.add("OTHERs");
			VITCSum_Taxable_Amt.add(nf.format(0));
			VITCSum_IGST_Amt.add(nf.format(0));
			VITCSum_CGST_Amt.add(nf.format(0));
			VITCSum_SGST_Amt.add(nf.format(0));
			VITCSum_CESS_Amt.add(nf.format(0));
			VITCParticular.add("Total:");
			VITCSum_Taxable_Amt.add(nf.format(0));
			VITCSum_IGST_Amt.add(nf.format(0));
			VITCSum_CGST_Amt.add(nf.format(0));
			VITCSum_SGST_Amt.add(nf.format(0));
			VITCSum_CESS_Amt.add(nf.format(0));
			VITCParticular.add("(c) NET ITC AVAILABLE (a-b)");
			VITCSum_Taxable_Amt.add(nf.format(0));
			VITCSum_IGST_Amt.add(nf.format(0));
			VITCSum_CGST_Amt.add(nf.format(0));
			VITCSum_SGST_Amt.add(nf.format(0));
			VITCSum_CESS_Amt.add(nf.format(0));
			
			VITCParticular.add("(d) INELIGIBLE ITC"); 
			VITCSum_Taxable_Amt.add("");
			VITCSum_IGST_Amt.add("");
			VITCSum_CGST_Amt.add("");
			VITCSum_SGST_Amt.add("");
			VITCSum_CESS_Amt.add("");
			VITCParticular.add("AS PER SECTION 17(5)"); 
			VITCSum_Taxable_Amt.add(nf.format(0));
			VITCSum_IGST_Amt.add(nf.format(0));
			VITCSum_CGST_Amt.add(nf.format(0));
			VITCSum_SGST_Amt.add(nf.format(0));
			VITCSum_CESS_Amt.add(nf.format(0));
			VITCParticular.add("OTHERS"); 
			VITCSum_Taxable_Amt.add(nf.format(0));
			VITCSum_IGST_Amt.add(nf.format(0));
			VITCSum_CGST_Amt.add(nf.format(0));
			VITCSum_SGST_Amt.add(nf.format(0));
			VITCSum_CESS_Amt.add(nf.format(0));
			VITCParticular.add("Net ITC (c-d)"); 
			VITCSum_Taxable_Amt.add(nf.format(0));
			VITCSum_IGST_Amt.add(nf.format(0));
			VITCSum_CGST_Amt.add(nf.format(0));
			VITCSum_SGST_Amt.add(nf.format(0));
			VITCSum_CESS_Amt.add(nf.format(0));
//////////////////////////////////////////////////////////////////////////////////////////
			
/////////////GST  - Payable/////////////////////////
			VPaybleParticular.add("GST PAYABLE (A)");
			VPaybleSum_Taxable_Amt.add(nf.format(SumTaxableAmt));
			VPaybleSum_IGST_Amt.add(nf.format(SumIGSTAmt));
			VPaybleSum_CGST_Amt.add(nf.format(SumCGSTAmt));
			VPaybleSum_SGST_Amt.add(nf.format(SumSGSTAmt));
			VPaybleSum_CESS_Amt.add(nf.format(SumCESSAmt));
			
			VPaybleParticular.add("ITC (B)");
			VPaybleSum_Taxable_Amt.add(nf.format(0));
			VPaybleSum_IGST_Amt.add(nf.format(0));
			VPaybleSum_CGST_Amt.add(nf.format(0));
			VPaybleSum_SGST_Amt.add(nf.format(0));
			VPaybleSum_CESS_Amt.add(nf.format(0));
			VPaybleParticular.add("NET GST PAYABLE (A-B )");
			VPaybleSum_Taxable_Amt.add(nf.format(SumTaxableAmt));
			VPaybleSum_IGST_Amt.add(nf.format(SumIGSTAmt));
			VPaybleSum_CGST_Amt.add(nf.format(SumCGSTAmt));
			VPaybleSum_SGST_Amt.add(nf.format(SumSGSTAmt));
			VPaybleSum_CESS_Amt.add(nf.format(SumCESSAmt));
			VPaybleParticular.add("PAID ON");			
			VPaybleSum_Taxable_Amt.add("");
			VPaybleSum_IGST_Amt.add("");
			VPaybleSum_CGST_Amt.add("");
			VPaybleSum_SGST_Amt.add("");
			VPaybleSum_CESS_Amt.add("");
		
//////////////////////////////////////////////////////////////////////////////////////////
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
}
	//////////////////////
	String cust_nm_rpt = "";
	String year_rpt = "";
	String to_year_rpt = "";
	String to_year = "";
	String month_rpt = "";
	String to_month_rpt = "";
	String to_month = "";
	String invoice_type_rpt = "";
	public String customer_cd="";	
	String invoice_type = "";
	public String For1 = "";
	
	String No_of_Customer="";
	String Sum_Net_Amt = "";
	String Sum_Taxable_Amt = "";
	String Sum_Tax_Amt = "";
	String Sum_Cess_Amt = "";
	String Sum_IGST_Amt = "";
	String Sum_CGST_Amt = "";
	String Sum_SGST_Amt = "";
	public String getNo_of_Customer() {return No_of_Customer;}
	public String getSum_Net_Amt() {return Sum_Net_Amt;}
	public String getSum_Taxable_Amt() {return Sum_Taxable_Amt;}
	public String getSum_Tax_Amt() {return Sum_Tax_Amt;}
	public String getSum_Cess_Amt() {return Sum_Cess_Amt;}
	public String getSum_IGST_Amt() {return Sum_IGST_Amt;}
	public String getSum_CGST_Amt() {return Sum_CGST_Amt;}
	public String getSum_SGST_Amt() {return Sum_SGST_Amt;}
	String Min_FY = ""; //SB20180319
	public String getMin_FY() {return Min_FY;}//SB20180319
	public Vector VSUPP_CD = new Vector();
	public Vector VSUPP_NM = new Vector();
	public Vector VSUPP_ABR = new Vector();
	public Vector getSUPP_CD() {return VSUPP_CD;}
	public Vector getSUPP_NM() {return VSUPP_NM;}
	public Vector getSUPP_ABR() {return VSUPP_ABR;}
	
	public Vector CUST_CD = new Vector();
	public Vector CUST_NM = new Vector();
	public Vector getCUST_CD() {return CUST_CD;}
	public Vector getCUST_NM() {return CUST_NM;}
	
	public Vector VInv_SeqNo = new Vector();
	public Vector VMapping_Id = new Vector();
	public Vector VNew_Inv_SeqNo = new Vector();
	public Vector VInv_Dt = new Vector();
	public Vector VFinancial_Yr = new Vector();
	public Vector VGross_Amt_Inr = new Vector();
	public Vector VNet_Amt_Inr = new Vector();
	public Vector VTaxable_Amt_Inr = new Vector();
	public Vector VTax_Amt_Inr = new Vector();
	public Vector VCust_Nm = new Vector();
	public Vector VCust_GSTIN_No = new Vector();
	public Vector VPlace_of_Supply = new Vector();
	public Vector VInv_Type = new Vector();
	public Vector VReverse_Charge = new Vector();
	public Vector getVInv_SeqNo() {return VInv_SeqNo;}
	public Vector getVMapping_Id() {return VMapping_Id;}
	public Vector getVNew_Inv_SeqNo() {return VNew_Inv_SeqNo;}
	public Vector getVInv_Dt() {return VInv_Dt;}
	public Vector getVFinancial_Yr() {return VFinancial_Yr;}
	public Vector getVGross_Amt_Inr() {return VGross_Amt_Inr;}
	public Vector getVNet_Amt_Inr() {return VNet_Amt_Inr;}
	public Vector getVTaxable_Amt_Inr() {return VTaxable_Amt_Inr;}
	public Vector getVTax_Amt_Inr() {return VTax_Amt_Inr;}
	public Vector getVCust_Nm() {return VCust_Nm;}
	public Vector getVCust_GSTIN_No() {return VCust_GSTIN_No;}
	public Vector getVPlace_of_Supply() {return VPlace_of_Supply;}
	public Vector getVInv_Type() {return VInv_Type;}
	public Vector getVReverse_Charge() {return VReverse_Charge;}
	
	public Vector VSAC_CD = new Vector();
	public Vector VHSN_CD = new Vector();
	public Vector VCGST_Perc = new Vector();	
	public Vector VSGST_Perc = new Vector();
	public Vector VIGST_Perc = new Vector();		
	public Vector VCESS_Perc = new Vector();	
	public Vector VCGST_Amt = new Vector();	
	public Vector VSGST_Amt = new Vector();
	public Vector VIGST_Amt = new Vector();		
	public Vector VCESS_Amt = new Vector();		
	public Vector getVSAC_CD() {return VSAC_CD;}
	public Vector getVHSN_CD() {return VHSN_CD;}
	public Vector getVCGST_Perc() {return VCGST_Perc;}
	public Vector getVSGST_Perc() {return VSGST_Perc;}
	public Vector getVIGST_Perc() {return VIGST_Perc;}
	public Vector getVCESS_Perc() {return VCESS_Perc;}
	public Vector getVIGST_Amt() {return VIGST_Amt;}
	public Vector getVCGST_Amt() {return VCGST_Amt;}
	public Vector getVSGST_Amt() {return VSGST_Amt;}
	public Vector getVCESS_Amt() {return VCESS_Amt;}
	
	/////////////GST3B///////////////////
	public Vector VParticular = new Vector();
	public Vector VSum_Taxable_Amt = new Vector();
	public Vector VSum_IGST_Amt = new Vector();
	public Vector VSum_CGST_Amt = new Vector();
	public Vector VSum_SGST_Amt = new Vector();
	public Vector VSum_CESS_Amt = new Vector();
	public Vector getVParticular() {return VParticular;}
	public Vector getVSum_Taxable_Amt() {return VSum_Taxable_Amt;}
	public Vector getVSum_IGST_Amt() {return VSum_IGST_Amt;}
	public Vector getVSum_CGST_Amt() {return VSum_CGST_Amt;}
	public Vector getVSum_SGST_Amt() {return VSum_SGST_Amt;}
	public Vector getVSum_CESS_Amt() {return VSum_CESS_Amt;}
	
	public Vector VITCParticular = new Vector();
	public Vector VITCSum_Taxable_Amt = new Vector();
	public Vector VITCSum_IGST_Amt = new Vector();
	public Vector VITCSum_CGST_Amt = new Vector();
	public Vector VITCSum_SGST_Amt = new Vector();
	public Vector VITCSum_CESS_Amt = new Vector();
	public Vector getVITCParticular() {return VITCParticular;}
	public Vector getVITCSum_Taxable_Amt() {return VITCSum_Taxable_Amt;}
	public Vector getVITCSum_IGST_Amt() {return VITCSum_IGST_Amt;}
	public Vector getVITCSum_CGST_Amt() {return VITCSum_CGST_Amt;}
	public Vector getVITCSum_SGST_Amt() {return VITCSum_SGST_Amt;}
	public Vector getVITCSum_CESS_Amt() {return VITCSum_CESS_Amt;}
	
	public Vector VPaybleParticular = new Vector();
	public Vector VPaybleSum_Taxable_Amt = new Vector();
	public Vector VPaybleSum_IGST_Amt = new Vector();
	public Vector VPaybleSum_CGST_Amt = new Vector();
	public Vector VPaybleSum_SGST_Amt = new Vector();
	public Vector VPaybleSum_CESS_Amt = new Vector();
	public Vector getVPaybleParticular() {return VPaybleParticular;}
	public Vector getVPaybleSum_Taxable_Amt() {return VPaybleSum_Taxable_Amt;}
	public Vector getVPaybleSum_IGST_Amt() {return VPaybleSum_IGST_Amt;}
	public Vector getVPaybleSum_CGST_Amt() {return VPaybleSum_CGST_Amt;}
	public Vector getVPaybleSum_SGST_Amt() {return VPaybleSum_SGST_Amt;}
	public Vector getVPaybleSum_CESS_Amt() {return VPaybleSum_CESS_Amt;}
	/////////////////////////////////////
	
	public void setCust_nm_rpt(String cust_nm_rpt) {this.cust_nm_rpt = cust_nm_rpt;}
	public void setTo_month(String to_month) {this.to_month = to_month;}
	public void setTo_year(String to_year) {this.to_year = to_year;}
	public void setCustomer_cd(String customer_cd) {this.customer_cd = customer_cd;}	
	public void setInvoice_type(String invoice_type) {this.invoice_type = invoice_type;}
	public void setFor1(String for1) {For1 = for1;}
	String set_SacHsn="";
	public void setSet_SacHsn(String set_sac_cd) {this.set_SacHsn = set_SacHsn;}
	String CustomerType="";
	public void setCustomerType(String CustomerType) {this.CustomerType = CustomerType;}
	String LineItem="";
	public void setLineItem(String LineItem) {this.LineItem = LineItem;}
/////////////////////////////////////////////////////////////////////////////
/////////////////SB20170814/////////////
	String FileHeaderInvType="";
	String NewSeqNo="";
	String supp_addr="";
	String supp_city="";
	String supp_pin="";
	String supp_state="";
	String supp_state_cd="";
	String SubHeader="";
	String SacHsn="";
	String SubHeader2="";
	String SubHeader3="";
	String SubjectLeft="";
	String SubjectRight="";
	String Body="";
	String Signature="";
	String PaymentRemark="";
	String NoInvGeneratedBeyond="";
	String LastInvGeneratedDt="";
	String LastPDF_Template="";
	Vector VPDF_Template_Allocated=new Vector();

	//Vector Vdesc=new Vector();
	public void setCust_Cd(String cust_cd) {this.cust_cd = cust_cd;}
	public void setTemplate_Type(String Template_Type) {this.TemplateType = Template_Type;}
	public String getNewSeqNo() {return NewSeqNo;}
	public String getSupp_addr() {return supp_addr;}
	public String getSuppAbr() {return SuppAbr;} //SB20180316
	public String getSVendor_abr() {return SVendor_abr;} //SB20180406
	public String getSupp_city() {return supp_city;}
	public String getSupp_pin() {return supp_pin;}
	public String getSupp_state() {return supp_state;}
	public String getSupp_state_cd() {return supp_state_cd;}
	public Vector getVvendor_nm() {return Vvendor_nm;}
	public Vector getVvendor_abr() {return Vvendor_abr;}
	public Vector getVCust_cd() {return VCust_cd;} //SB20171104 
	public String getNoInvGeneratedBeyond() {return NoInvGeneratedBeyond;}
	public String getLastInvGeneratedDt() {return LastInvGeneratedDt;}
	public String getLastPDF_Template() {return LastPDF_Template;}
	public Vector getVPDF_Template_Allocated() {return VPDF_Template_Allocated;} //SB20171104 
}//End Of Class DataBean_Sales_Invoice .