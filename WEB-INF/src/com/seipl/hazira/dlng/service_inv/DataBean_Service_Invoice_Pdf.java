package com.seipl.hazira.dlng.service_inv;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.google.gson.annotations.Since;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfAnnotation;
import com.lowagie.text.pdf.PdfFormField;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.events.FieldPositioningEvents;
import com.seipl.hazira.dlng.util.RuntimeConf;

public class DataBean_Service_Invoice_Pdf {
	 Connection conn; 
		Statement stmt;
		Statement stmt1;
		Statement stmt2;
		ResultSet rset;
		ResultSet rset1;
		ResultSet rset2;
		String queryString = "";
		String queryString1 = "";
		String queryString2 = "";
		String tempcompnm="";
		String callFlag = "";
		public String fgsa_no = "";
		public String fgsa_rev_no = "";
		public String sn_no = "";
		public String sn_rev_no = "";
		public String contract_type = "";
		public String customer_plant_nm = "";
		public String customer_plant_seq_no = "";
		public String bill_period_start_dt = "";
		public String bill_period_end_dt = "";
		public String due_dt = "";
		public String month = "0";
		public String to_month="0";
		public String year = "0";
		public String to_year = "0";
		public String bill_cycle = "0";
		public String exchg_rate_cd = "0";
		public String exchg_rate_cal_method = "0";
		public String invoice_date = "";
		public String customer_abbr = "";
		public String particular_date = "";
		public String rbi_ref_cd = "1";
		public String sbi_tt_selling_cd = "2";
		public String sbi_tt_buying_cd = "3";
		public String sbi_avg_tt_selling_buying_cd = "6";
		public String hlpl_inv_seq_no = "";
		String fileName = "";
		String f_nm="";
		String inv_type_pdf="";
		public String inv_financial_year = "";
		String invoice_bench_date="10/03/2015";
		public String hlpl_inv_no = "";
		public String hlpl_drcr_docNo = ""; 
		public String hlpl_drcr_dt = ""; 
		public String customer_inv_no = "";
		public String invoice_title = "ORIGINAL";
		String from_dt = "";
		String to_dt ="";
		String activity="";
		String operation ="";
		String pay_cd = "";
		String methodName = "";
		String databeanName = "DataBean_Service_Invoice_Pdf";
		public String url_start = "";
		public HttpServletRequest request = null;
		public String pdf_path = ""; 
		public String invoice_pdf_path = ""; 
		public String invoice_att1_pdf_path = "";
		
		NumberFormat nf7 = new DecimalFormat("###,###,###,##0.00"); 
		NumberFormat nf5 = new DecimalFormat("##########0");
		NumberFormat nf = new DecimalFormat("###########0.00");
		boolean flag_DCB = false;
		HttpSession session = null;
		String emp_name ="";
		public String customer_Invoice_FGSA_Dt = "";
		public String contact_Customer_Name = "";
		public String contact_Suppl_Name = "";
		public String contact_Suppl_Person_Address = "";
		public String contact_Suppl_Person_City = "";
		public String contact_Suppl_Person_Pin = "";
		public String contact_Suppl_Person_Phone = "";
		public String contact_Suppl_Person_Fax = "";
		public String contact_Suppl_Person_State = "";
		public String contact_Suppl_Person_Country = "";
		public String contact_Suppl_GST_NO = "";
		public String contact_Suppl_CST_NO = "";
		public String contact_Suppl_GST_DT = "";
		public String contact_Suppl_CST_DT = "";
		public String contact_Suppl_Service_Tax_NO = "";
		public String contact_Suppl_Service_Tax_DT = "";
		
		public String contact_Suppl_PAN_NO = "";		//BK20160211
		public String contact_Suppl_PAN_DT = "";		//BK20160211
		
		String contact_Suppl_GSTIN_NO = "";
		String contact_Suppl_GSTIN_DT = "";
		String contact_Suppl_State_Code = "";
		String contact_Suppl_State = "";
		String contact_customer_State = "";
		String contact_customer_State_Code = "";
		String Rule_remark = "";
		String sac_code="";
		String sac_name="";
		String sac_desc = "";
		public String contact_Person_Name_And_Designation = "";
		public String contact_Person_Name = "";
		public String contact_Person_Desg = "";
		public String contact_Customer_Person_Address = "";
		public String contact_Customer_Person_City = "";
		public String contact_Customer_Person_Pin = "";
		public String contact_Customer_GST_NO = "";
		public String contact_Customer_CST_NO = "";
		public String contact_Customer_GST_DT = "";
		public String contact_Customer_CST_DT = "";
		public String contact_Customer_Service_Tax_NO = "";
		public String contact_Customer_Service_Tax_DT = "";
		public String contact_Customer_GVAT_NO = "";
		public String contact_Customer_GVAT_DT = "";
		String tempcompname="";
		
		public Vector vSTAT_CD = new Vector();
		public Vector vSTAT_NM = new Vector();
		public Vector vSTAT_NO = new Vector();
		public Vector vSTAT_EFF_DT = new Vector();
		String new_inv_seq_no = "";
		String formated_inv_dt = "";
		public String customer_Invoice_DT = "";
		public String customer_Invoice_Due_DT = "";
		public String customer_Invoice_Start_DT = "";
		public String customer_Invoice_End_DT = "";
		String second_due_dt="";
		String dr_cr_due_dt="";
		
		Vector Vsac_cd = new Vector();
		Vector Vitem_desc = new Vector();
		Vector Vqty = new Vector();
		Vector Vrate = new Vector();
		Vector Vamt = new Vector();
		
		Vector View_calc_bs = new Vector();
		
		String tax_size [] = null;
		String taxnm_str = "";
		String grossAmt = "";
		String tax_structure = "";
		String totalTax = "";
		String netAmt = "";
		String remark_1 = "";
		String remark_2 = "";
		String calcBase = "";
		String qty_unit = "";
		String rate_unit = "";
		String drcr_criteria = "";
		
		Vector View_amount  = new Vector();
		Vector View_invoice_qty = new Vector();
		Vector View_dr_cr_qty = new Vector();
		Vector View_diff_qty = new Vector();
		
		
		Vector View_km =  new Vector();;
		Vector View_rate =  new Vector();
		Vector View_service_inv_dt =  new Vector();
		Vector View_truck_inv_dt =  new Vector();
		Vector View_truck_inv_no =  new Vector();
		Vector View_truck_nm = new Vector();
		Vector inv_tax_perc =  new Vector();
		Vector drcr_tax_perc =  new Vector();
		Vector diff_tax_perc =  new Vector();
		Vector drcr_tax_amt = new Vector();
		Vector tax_nm = new Vector();
		
		double total_qty = 0; 
		double total_amt = 0;
		public static final String SIGNAME = "Signature1";
		String irn_no = "";
		String irn_flag = "";
		String qr_code = "";
		String signing_dt = "";
		String trans_cont_no = "";
		double tot_inv_qty = 0;
		double tot_dr_cr_qty = 0 ;
		double tot_diff_qty = 0 ;
		double inv_rate = 0 ;
		double dr_cr_rate =  0 ;
		double diff_rate = 0 ;
		String drcr_gross_amt_inr = "0";
		double drcr_tax_samt = 0 ;
		double drcr_net_amt_inr = 0 ;
		double gross_amt_inr = 0 ;
		
		String drcr = "";
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
		    			
		    			session = request.getSession();
						emp_name =(String)session.getAttribute("username")==null?"":(String)session.getAttribute("username");
						
						String url_DCB = request.getContextPath(); //request.getRequestURL().toString();
		    		    if(url_DCB.contains("TLU"))
		    		    {
		    		    	flag_DCB=true;
		    		    }
		    		    else
		    		    {
		    		    	flag_DCB=false;
		    		    }
		    		    
		    		    
		    			if(callFlag.equalsIgnoreCase("Pdf_For_Service_Invoice_Dtl")){
		    				invoice_pdf_path = createPdfFileForInvoice();
		    				printPdfFileForInvoice_SignPDF();		    				
		    				printPdfFileForInvoice();
		    				updatetableForPdfDtlEntry();
		    			
		    			}else if(callFlag.equalsIgnoreCase("Pdf_For_Service_Invoice_DR_CR")) {
		    				
		    				invoice_pdf_path = createPdfFileForInvoice();
		    				printPdfFileForDR_CR_SignPDF();
		    				printPdfFileForDR_CR();
		    				
		    				updatetableForPdfDtlEntry();
		    				
		    			}
		    			conn.close();
		    			conn = null;
		    		}
		    	}
		    }
		    catch(Exception e)
		    {
		    	//System.out.println("Exception In : ("+databeanName+") - (init()): "+e.getMessage());
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
						//System.out.println("rset is not close "+e);
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
						//System.out.println("rset1 is not close "+e);
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
						//System.out.println("rset2 is not close "+e);
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
						//System.out.println("stmt is not close "+e);
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
						//System.out.println("stmt1 is not close "+e);
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
						//System.out.println("stmt2 is not close "+e);
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
						//System.out.println("conn is not close "+e);
					}
				}
		    }
		}
		private void printPdfFileForDR_CR_SignPDF() throws Exception {

			
			Rectangle pageSize = new Rectangle(595, 842);
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
			 Document document = new Document(pageSize);
			 try
				{
					PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(createPdfFileForInvoice_SignPDF()));
					document.addTitle("Invoice Details");
					document.addSubject("Invoice Details For Customer");
		            document.addKeywords("iText, Invoice Details, Step 2, metadata");
		            document.addCreator("Invoice Details Generation using iText");
		            document.addAuthor("FMS8@BIPL");
		            document.open();
					document.setPageSize(pageSize);
		            document.newPage();

		            Font very_small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		            Font small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		            Font small_black_normal2 = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		            Font small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
		            Font black_bold = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new Color(0x00, 0x00, 0x00));
		            Font big_black_bold_2 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
		            Font big_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new Color(0x00, 0x00, 0x00));
		            
		            String context_nm = request.getContextPath();
					String server_nm = request.getServerName();
					String server_port = ""+request.getServerPort();
					  
					url_start = "http://"+server_nm+":"+server_port+context_nm;
					
		           // Image hlpl_logo = Image.getInstance(url_start+"//images//LOGO//HLPL-Symbol.jpg");
					 Image hlpl_logo = Image.getInstance(url_start+"/images/logo/company_Logo.png");
					 /*RG20191128 added for adding logo for sign pdf*/
					 	hlpl_logo.setBorder(Rectangle.NO_BORDER);
			            hlpl_logo.scaleAbsolute(48,45);
			            PdfPCell hlpl_logo_cell1 = new PdfPCell(hlpl_logo,false);
			            hlpl_logo_cell1.setBorder(Rectangle.NO_BORDER);
			            float[] hlpl_logo_Widths1 = {0.45f};
			            PdfPTable hlpl_logo_table1 = new PdfPTable(hlpl_logo_Widths1);
			            hlpl_logo_table1.setWidthPercentage(100);
			            hlpl_logo_table1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            hlpl_logo_table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			            hlpl_logo_table1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
			            hlpl_logo_table1.addCell(hlpl_logo_cell1);
					  /* ended adding logo*/ 
					           
					String inv_nm = "TAX INVOICE\nTAX Invoice issued under Rule 46 of the Central Goods and Services Tax Rules, 2017";
					String inv_desc = "Shell Energy India Private Limited";
					String inv_note = "In respect of Transport Management Service Agreement ("+trans_cont_no+") executed on "+signing_dt+" \nbetween "+contact_Suppl_Name+" and "+contact_Customer_Name;
					
					String addr_supl = "Registered Office:";
					Paragraph pp=new Paragraph();
					pp.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
		            
		            if(!contact_Suppl_Name.trim().equals(""))
		            {
		            	pp.add(new Phrase(new Chunk("\n"+contact_Suppl_Name,small_black_normal)));
		            }
		            
		            if(!contact_Suppl_Person_Address.trim().equals(""))
		            {
		            	//addr_supl += "\n"+contact_Suppl_Person_Address;
		            	addr_supl = "\n"+contact_Suppl_Person_Address;
		            }
		            if(!contact_Suppl_Person_City.trim().equals(""))
		            {
		            	addr_supl += "\n"+contact_Suppl_Person_City;     	
		            }
		            if(!contact_Suppl_Person_Pin.trim().equals(""))
		            {
		            	addr_supl += " "+"-"+" "+contact_Suppl_Person_Pin;     	
		            }
		            pp.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
					
					String addr_customer = "";
		            
					if(contact_Person_Name_And_Designation.trim().equalsIgnoreCase(contact_Customer_Name.trim()))
					{
						if(!contact_Customer_Name.trim().equals(""))
			            {
			            	addr_customer += contact_Customer_Name;    	
			            }
					}
					else
					{
			            if(!contact_Person_Name_And_Designation.trim().equals(""))
			            {
			            	addr_customer += contact_Person_Name_And_Designation;     	
			            }
			            if(!contact_Customer_Name.trim().equals(""))
			            {
			            	addr_customer += "\n"+contact_Customer_Name;     	
			            }
					}
		            if(!contact_Customer_Person_Address.trim().equals(""))
		            {
		            	addr_customer += "\n"+contact_Customer_Person_Address;     	
		            }
		            if(!contact_Customer_Person_City.trim().equals(""))
		            {
		            	addr_customer += "\n"+contact_Customer_Person_City;     	
		            }
		            if(!contact_Customer_Person_Pin.trim().equals(""))
		            {
		            	addr_customer += " "+"-"+" "+contact_Customer_Person_Pin;     	
		            }
		            
		            PdfPTable InvoiceTitleTable = new PdfPTable(1);
		            InvoiceTitleTable.setWidthPercentage(100);
		            InvoiceTitleTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            InvoiceTitleTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		          //SB20160601  InvoiceTitleTable.addCell(new Phrase(new Chunk(invoice_title,black_bold)));
		            
		            if(invoice_title.contains("CREDIT") || invoice_title.contains("DEBIT")  ) //SB20160601
		            {
		            	String inv_tit = invoice_title.substring(invoice_title.length()-1,invoice_title.length());
//		            	System.out.println("inv_tit******"+inv_tit);
		            	if(inv_tit.equals("O")) {
		            		inv_tit = "ORIGINAL";
		            	} else if (inv_tit.equals("D")){
		            		inv_tit = "DUPLICATE";
		            	}else {
		            		inv_tit = "TRIPLICATE";
		            	}
		            	InvoiceTitleTable.addCell(new Phrase(new Chunk(inv_tit,black_bold)));
		            }
		            PdfPTable InvoiceDescTable = new PdfPTable(1);
		            InvoiceDescTable.setWidthPercentage(100);
		            InvoiceDescTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            InvoiceDescTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            InvoiceDescTable.addCell(new Phrase(new Chunk(inv_desc,big_black_bold)));
					
		            PdfPTable InvoiceDescTable2 = new PdfPTable(1);
		            InvoiceDescTable2.setWidthPercentage(100);
		            InvoiceDescTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            InvoiceDescTable2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            if(invoice_title.contains("CREDIT") ) {
		            	if(contract_type.equals("S")  || contract_type.equals("L")){
		            		InvoiceDescTable2.addCell(new Phrase(new Chunk("CREDIT NOTE",big_black_bold_2)));
		            	}else{
		            		InvoiceDescTable2.addCell(new Phrase(new Chunk("CREDIT NOTE\nCredit Note issued under Rule 53 of the Central Goods and Services Tax Rules, 2017",big_black_bold_2)));
		            	}
		            }else if(invoice_title.contains("Sup")){
		            		InvoiceDescTable2.addCell(new Phrase(new Chunk("DEBIT NOTE\nDebit Note issued under Rule 53 of the Central Goods and Services Tax Rules, 2017",big_black_bold_2)));
		            }else if(invoice_title.contains("DEBIT")){
		            	//	InvoiceDescTable2.addCell(new Phrase(new Chunk("DEBIT NOTE\nDebit Note issued under Rule 53 of the Central Goods and Services Tax Rules, 2017",big_black_bold_2)));
		            		InvoiceDescTable2.addCell(new Phrase(new Chunk("DEBIT NOTE",big_black_bold_2)));
		            }else{
		            	InvoiceDescTable2.addCell(new Phrase(new Chunk(inv_nm,big_black_bold_2)));
		            }
		            
		            PdfPTable InvoiceNoteTable = new PdfPTable(1);
		            InvoiceNoteTable.setWidthPercentage(100);
		            InvoiceNoteTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            InvoiceNoteTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            InvoiceNoteTable.addCell(new Phrase(new Chunk(inv_note,small_black_normal)));
					
		            float[] ContactAddrWidths = {0.50f, 0.10f, 0.40f};
		            PdfPTable contact_addr_table = new PdfPTable(ContactAddrWidths);
		            contact_addr_table.setWidthPercentage(100);
		            contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		         //   contact_addr_table.addCell(new Phrase(new Chunk(addr_supl,small_black_normal)));
		            contact_addr_table.addCell(pp);
		            contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            contact_addr_table.addCell(new Phrase(new Chunk("To:",small_black_bold)));
		            contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            contact_addr_table.addCell(new Phrase(new Chunk(addr_customer,small_black_normal)));
					
		            String supl_gst_cst_info = "";
		            String customer_gst_cst_info = "";
		            
		          //  //System.out.println("-----INSIDE PRINTALL 2---"+vSTAT_CD.size());
		            
		            if(contract_type.equalsIgnoreCase("V")){

		            	if(!contact_Suppl_State.trim().equals("")) {
		            		supl_gst_cst_info = "State : "+contact_Suppl_State;
		            	}
		            	if(!contact_Suppl_State_Code.trim().equals("")) {
		            		supl_gst_cst_info += "\nState Code : "+contact_Suppl_State_Code;
		            	}
		            	if(!contact_Suppl_GSTIN_NO.trim().equals(""))
		            	{
		            		supl_gst_cst_info += "\nGSTIN No. : "+contact_Suppl_GSTIN_NO;
		            		if(!contact_Suppl_GST_DT.equals("")) {
		            			supl_gst_cst_info+=" DT. "+contact_Suppl_GST_DT;
		            		}
		            	}
		            	if(!contact_Suppl_PAN_NO.trim().equals(""))
		            	{
		            		supl_gst_cst_info += " \nPAN No.: "+contact_Suppl_PAN_NO+" ";
		            	}
		            	if(!sac_code.trim().equals(""))
		            	{
		            		supl_gst_cst_info += " \nSAC No. : "+sac_code+" ";
		            	}
		            	if(!sac_desc.trim().equals(""))
		            	{
		            		supl_gst_cst_info += " \nDescription of Service : "+sac_desc+" ";
		            	}
		            	if(!contact_customer_State.trim().equals(""))
		            	{
		            		supl_gst_cst_info += " \nPlace of Supply : "+contact_customer_State+" ";
		            	}
		    		}
		            if(!contact_customer_State.trim().equals(""))
	            	{
		            	customer_gst_cst_info = "State : "+contact_customer_State+" ";
	            	}
		            if(!contact_customer_State_Code.trim().equals(""))
	            	{
		            	customer_gst_cst_info += "\nState Code : "+contact_customer_State_Code+" ";
	            	}
		            
		            if(vSTAT_CD.size()>0)
		    		{	
		    			if(contract_type.equalsIgnoreCase("V"))
		    			{
		    				for(int i=0; i<vSTAT_CD.size(); i++)
							{
	    						if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) 
	    						{
	    							customer_gst_cst_info += "\nPAN No. : "+vSTAT_NO.elementAt(i)+" ";
	    						}
	    						else
	    						{
	    							if(vSTAT_NM.elementAt(i).toString().contains("GSTIN")) 
		    						{
	    								customer_gst_cst_info += "\nGSTIN No. : "+vSTAT_NO.elementAt(i);
	    								if(!vSTAT_EFF_DT.elementAt(i).equals("")) {
	    									customer_gst_cst_info+=" DT. "+vSTAT_EFF_DT.elementAt(i);
	    			            		}
		    						}
	    						}
							}
		    			}
		    		}
		           
		            float[] GstCstInfoWidths = {0.50f, 0.10f, 0.40f};
		            PdfPTable GstCstInfoTable = new PdfPTable(GstCstInfoWidths);
		            GstCstInfoTable.setWidthPercentage(100);
		            GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            GstCstInfoTable.addCell(new Phrase(new Chunk("\n"+supl_gst_cst_info,small_black_normal)));
		            GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            GstCstInfoTable.addCell(new Phrase(new Chunk("\n ",small_black_normal)));
		            GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            GstCstInfoTable.addCell(new Phrase(new Chunk("\n"+customer_gst_cst_info,small_black_normal)));
		            
		            String invno = "";
//		            System.out.println("hlpl_drcr_docNo**"+hlpl_drcr_docNo+"**"+hlpl_inv_no);
		            if(!hlpl_drcr_docNo.equals("")) {
		            	invno = hlpl_drcr_docNo;
		            } else {
		            	if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		            	{
		            		if(hlpl_inv_no.length()>13)
		            		{
		            			invno = hlpl_inv_no.substring(0,10)+""+hlpl_inv_no.substring(12);
		            		}      		
		            	}
		            	else
		            	{
		            		if(hlpl_inv_no.length()>13)
		            		{
		            			invno = hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
		            		}       		
		            	}
		            }
		            System.out.println("invno**"+invno);
		            String inv_no_info = "";
					String invoiceType = "";
					String inv_dt_Header = "Invoice Date ";
					String Inv_Ref_Dtl = ""; //SB20160611  
					 
					String inv_due_dt_Header = "Payment Due Date ";
					if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT")) //SB20160530
					{             
				         Inv_Ref_Dtl = " as per Invoice ref "+invno+" dated "+customer_Invoice_DT;
				        if(invoice_title.contains("CREDIT")){    
						invoiceType = "Credit Note No ";
				        }else if(invoice_title.contains("Sup")){
				        	invoiceType = "Debit Note No ";
				        }else {
				        	invoiceType = "Debit Note No ";
				        }
				//SB*		inv_dt_Header = "Invoice  Ref No: "+invno;
						inv_dt_Header = "";
						//customer_Invoice_DT =invno+":"+customer_Invoice_DT;
						if(invoice_title.contains("CREDIT")){    
						inv_due_dt_Header = "Credit Note Date ";
						}else if(invoice_title.contains("Sup")){
							inv_due_dt_Header = "Debit Note Date ";
						}else {
							inv_due_dt_Header = "Debit Note Date ";
						}
						customer_Invoice_Due_DT = hlpl_drcr_dt;
					}
					else
					{
						invoiceType = "SEIPL TAX Invoice Seq No ";
					}
					
					if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT")){ //SB20160530
						if(invoice_title.contains("CREDIT")){
							//RG20190325 For Company change*/inv_no_info = "HLPL Credit Note No:";
							inv_no_info = "SEIPL Credit Note No ";
						}else if(invoice_title.contains("Sup")){
							//RG20190325 For Company change*/inv_no_info = "HLPL Debit Note No:"; 
							inv_no_info = "SEIPL Debit Note No "; 
						}else {
						//RG20190325 For Company change*/	inv_no_info = "HLPL Debit Note No:"; 
							inv_no_info = "SEIPL Debit Note No ";
						}
					}
					else{
						inv_no_info = "SEIPL TAX Invoice Seq No ";
					}
					
					PdfPTable InvoiceDateInfoTable; 
					PdfPTable InvoiceDueDateInfoTable;
					PdfPTable InvoiceNOInfoTable;
					
					 float[] InvoiceDateInfoWidths_qr = {0.40f, 0.20f, 0.60f};
					 PdfPTable InvoiceDateInfoTable_qr = new PdfPTable(InvoiceDateInfoWidths_qr);
		           if(!irn_no.equals("") && (!qr_code.equals(""))){
		        	   
//						float[] InvoiceDateInfoWidths = {0.60f, 0.25f, 0.15f};
						float[] InvoiceDateInfoWidths = {0.20f, 0.45f, 0.25f};
			            InvoiceDateInfoTable = new PdfPTable(InvoiceDateInfoWidths);
			            InvoiceDateInfoTable.setWidthPercentage(100);
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			        //SB20160531    InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT")) //SB20160530
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_dt_Header,small_black_bold)));
			            else
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT"))//SB20160530
			            {
				            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
				            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
				            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            }
			            else
			            {
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk("Invoice Date ",small_black_bold)));
			            	InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_DT,small_black_bold)));
			            }
			            float[] InvoiceDueDateInfoWidths = {0.60f, 0.25f, 0.15f};
			            InvoiceDueDateInfoTable = new PdfPTable(InvoiceDueDateInfoWidths);
			            
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
		                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
		                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_due_dt_Header,small_black_bold)));
		                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
		                if(!second_due_dt.equals("") && (!invoice_title.contains("Sup")) && (!invoice_title.contains("DEBIT")) && (!invoice_title.contains("CREDIT"))){
		                	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(second_due_dt,small_black_bold)));
		                }else{
		                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_Due_DT,small_black_bold)));
		                }
			            
			            if(invoice_title.contains("Sup") || invoice_title.contains("DEBIT")) {
			            	
			            	InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk("Payment Due Date ",small_black_bold)));
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(dr_cr_due_dt,small_black_bold)));
			              }
			                float[] InvoiceNOInfoWidths = {0.60f, 0.25f, 0.15f};
			                InvoiceNOInfoTable = new PdfPTable(InvoiceNOInfoWidths);
			                
			                
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_no_info,small_black_bold)));
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(invno,small_black_bold)));            
			                
			                
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(3);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk("",small_black_bold)));
			                
			                //code for Irn & qrcode starts now
			                url_start = "http://"+server_nm+":"+server_port+context_nm;
			                    String qrCodeText = qr_code;
			                    String filePath =  request.getRealPath("/pdf_reports/xls_reports/qrcode");
			          		  filePath = filePath+"//"+invno.replace("/","_")+".png";
			          		  int size = 45;//125 original
			          		  String fileType = "png";
			          		  File qrFile = new File(filePath);
			          		  com.seipl.hazira.dlng.util.DataBean_QRCode_Generator.createQRImage(qrFile, qrCodeText, size, fileType);
			                    
			                  Image qr_codeimg = Image.getInstance(url_start+"//pdf_reports//xls_reports//qrcode//"+invno.replace("/","_")+".png");			  
			                  qr_codeimg.setBorder(Rectangle.NO_BORDER);
			                  qr_codeimg.setAlignment(Element.ALIGN_LEFT);
			                 // qr_codeimg.scaleAbsolute(75,75);
			                //  qr_codeimg.scaleAbsolute(50,50);
			                  PdfPCell qrcode_cell = new PdfPCell(qr_codeimg,false);
			                  qrcode_cell.setBorder(Rectangle.NO_BORDER);
			                  qrcode_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			                      
//			                    String irn_no=irn_no;
			                    String char_16= irn_no.substring(0,16);
			                    String char_32= irn_no.substring(16,32);
			                    String char_48= irn_no.substring(32,48);
			                    String char_64= irn_no.substring(48,irn_no.length());
			                    String final_irn_no=char_16+"\n"+char_32+"\n"+char_48+"\n"+char_64;
			                    
			                    PdfPTable InvoiceDateInfoTable_qr1 = new PdfPTable(1);
			        	        InvoiceDateInfoTable_qr1.setWidthPercentage(100);
//			        	        InvoiceDateInfoTable_qr1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//			        	        InvoiceDateInfoTable_qr1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//			        	        InvoiceDateInfoTable_qr1.addCell(new Phrase(new Chunk("QR Code",small_black_bold)));
			        	        InvoiceDateInfoTable_qr1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	        InvoiceDateInfoTable_qr1.getDefaultCell().setBorder(Rectangle.BOX);
			        	        InvoiceDateInfoTable_qr1.addCell(qrcode_cell);
			        	        
			        	        PdfPTable InvoiceDateInfoTable_qr11 = new PdfPTable(1);
			        	        InvoiceDateInfoTable_qr11.setWidthPercentage(100);
			        	        InvoiceDateInfoTable_qr11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	        InvoiceDateInfoTable_qr11.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			        	        InvoiceDateInfoTable_qr11.addCell(new Phrase(new Chunk("IRN no.",small_black_bold)));
			        	        InvoiceDateInfoTable_qr11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	        InvoiceDateInfoTable_qr11.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			        	        InvoiceDateInfoTable_qr11.addCell(new Phrase(new Chunk(final_irn_no,small_black_normal)));
			                    
			        			
			        	        InvoiceDateInfoTable_qr.setWidthPercentage(100);
			        	               
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			        	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable_qr1);
			        	        
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.BOX);
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable_qr11);
			        	        
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			        	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable);
		           }else{
			        	   
						float[] InvoiceDateInfoWidths = {0.60f, 0.25f, 0.15f};
			            InvoiceDateInfoTable = new PdfPTable(InvoiceDateInfoWidths);
			            InvoiceDateInfoTable.setWidthPercentage(100);
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			        //SB20160531    InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            if(invoice_title.contains("CREDIT") || invoice_title.contains("DEBIT")) //SB20160530
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_dt_Header,small_black_bold)));
			            else
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            if(invoice_title.contains("CREDIT") ||  invoice_title.contains("DEBIT"))//SB20160530
			            {
				            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
				            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
				            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            }
			            
			            float[] InvoiceDueDateInfoWidths = {0.60f, 0.25f, 0.15f};
			            InvoiceDueDateInfoTable = new PdfPTable(InvoiceDueDateInfoWidths);
			            InvoiceDueDateInfoTable.setWidthPercentage(100);
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(inv_due_dt_Header,small_black_bold)));
			                    
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_DT,small_black_bold)));
			            
			            if(invoice_title.contains("DEBIT")) {
			            	
			            	InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDueDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDueDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk("Payment Due Date ",small_black_bold)));
			                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDueDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(dr_cr_due_dt,small_black_bold)));
			              }
			                float[] InvoiceNOInfoWidths = {0.60f, 0.25f, 0.15f};
			                InvoiceNOInfoTable = new PdfPTable(InvoiceNOInfoWidths);
			                InvoiceNOInfoTable.setWidthPercentage(100);
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(inv_no_info,small_black_bold)));
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(invno,small_black_bold)));
			           }
		           
			            float[] BillingFieldsInfoWidths = {0.06f, 0.40f,0.10f, 0.15f, 0.15f, 0.15f};
		                PdfPTable BillingFieldsInfoTable = new PdfPTable(BillingFieldsInfoWidths);
		                BillingFieldsInfoTable.setWidthPercentage(100);
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Sr. No.",small_black_bold)));
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Item",small_black_bold)));
		               
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Currency",small_black_bold)));
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Quantity\n"+qty_unit,small_black_bold)));
		                
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Rate\n"+rate_unit,small_black_bold)));
		                
		                
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Amount",small_black_bold)));
		                
		                
		                String field_1 = "";
		                String field_2 = "";
		                String field_3 = "";
		                String field_4 = "";
		                String field_5 = "";
		                String field_6 = "";
			           
		                int sr_no = 0;
		            	/* for field 1 */
		                field_1+="\n";
						field_1+=++sr_no+"\n\n\n";
							if(drcr_criteria.contains("DIFF-LUMPSUM")) {
								field_1+=++sr_no+"\n\n";
								field_1+=++sr_no+"\n\n";
								field_1+=++sr_no+"\n\n";
							}
							if(drcr_criteria.contains("DIFF-KM")) {
								field_1+=++sr_no+"\n\n";
								field_1+=++sr_no+"\n\n";
							}
							if(drcr_criteria.contains("DIFF-INRKM")) {
								field_1+=++sr_no+"\n\n";
								field_1+=++sr_no+"\n\n";
								field_1+=++sr_no+"\n\n";
							}
							if(drcr_criteria.contains("DIFF-QTY")) {
								field_1+=++sr_no+"\n\n";
								field_1+=++sr_no+"\n\n";
							} 
							if(drcr_criteria.contains("DIFF-INRMMBTU")) {
								field_1+=++sr_no+"\n\n";
								field_1+=++sr_no+"\n\n";
								field_1+=++sr_no+"\n\n";
							}
							
//						for total GROSS AMOUNT -->
							field_1+=++sr_no+"\n\n";
							if(drcr_criteria.contains("DIFF-TAX")) {
								field_1+=++sr_no+"\n";
								for(int i = 0 ; i < tax_nm.size(); i++){
									if( i  != (tax_nm.size()-1)){ 
										field_1+="\n";
									}
								}
								field_1+="\n\n";
								field_1+=++sr_no+"\n";
								for(int i = 0 ; i < tax_nm.size(); i++){
									if( i  != (tax_nm.size()-1)){
										field_1+="\n";
									} 
								} 
								
								field_1+="\n\n";
								field_1+=++sr_no+"\n";
								
								for(int i = 0 ; i < tax_nm.size(); i++){
									if( i  != (tax_nm.size()-1)){
										field_1+="\n";
									}
								} 
								field_1+="\n\n";
							}else{
								field_1+=++sr_no+"\n";
								for(int i = 0 ; i < tax_nm.size(); i++){
									if( i  != (tax_nm.size()-1)){
										field_1+="\n";
									}
								} 
								field_1+="\n\n";
							} 
							field_1+=++sr_no+"\n\n";
							
						/* field_2 */
						field_2+="\n";
						field_2+="Transport Management Services Charge \nas per invoice ref. "+new_inv_seq_no+" dated "+formated_inv_dt+"\n\n";
							if(drcr_criteria.contains("DIFF-LUMPSUM")) {
								field_2+="Invoice Invoice INR/Lumpsum\n\n";
								field_2+="Applicable INR/Lumpsum\n\n";
								field_2+="Difference in INR/Lumpsum\n\n";
							}
							if(drcr_criteria.contains("DIFF-KM")) {
								field_2+="Applicable KM\n\n";
								field_2+="Difference in KM\n\n";
							}
							if(drcr_criteria.contains("DIFF-INRKM")) {
								field_2+="Invoice INR/KM\n\n";
								field_2+="Applicable INR/KM\n\n";
								field_2+="Difference in INR/KM\n\n";
							}
							if(drcr_criteria.contains("DIFF-QTY")) {
								field_2+="Applicable Quantity\n\n";
								field_2+="Difference Quantity\n\n";
							} 
							if(drcr_criteria.contains("DIFF-INRMMBTU")) {
								field_2+="Invoice INR/MMBtu\n\n";
								field_2+="Applicable INR/MMBtu\n\n";
								field_2+="Difference INR/MMBtu\n\n";
							}
//						for total GROSS AMOUNT -->
							field_2+="Gross Amount\n\n";
							
							if(drcr_criteria.contains("DIFF-TAX")) {
								field_2+="Invoice TAX\n";
								for(int i = 0 ; i < tax_nm.size(); i++){
									field_2+=tax_nm.elementAt(i);
									if( i  != (tax_nm.size()-1)){ 
										field_2+="\n";
									}
								}
								field_2+="\n\n";
								field_2+="Applicable TAX (%)\n";
								for(int i = 0 ; i < tax_nm.size(); i++){
									field_2+=tax_nm.elementAt(i);
									if( i  != (tax_nm.size()-1)){
										field_2+="\n";
									} 
								} 
								
								field_2+="\n\n";
								field_2+="Difference TAX (%)\n";
								
								for(int i = 0 ; i < tax_nm.size(); i++){
									field_2+=tax_nm.elementAt(i);
									if( i  != (tax_nm.size()-1)){
										field_2+="\n";
									}
								} 
								field_2+="\n\n";
							}else{
								field_2+="Invoice TAX (%) \n";
								for(int i = 0 ; i < tax_nm.size(); i++){ 
									field_2+=tax_nm.elementAt(i);
									if( i  != (tax_nm.size()-1)){ 
										field_2+="\n";
									}
								} 
								field_2+="\n\n";
							} 
							field_2+="Total GST\n\n";
						
						/* end of field_2 */
							
							/* field_3 */
							field_3+="\n";
							field_3+="\n\n";
								if(drcr_criteria.contains("DIFF-LUMPSUM")) {
									field_3+="Rupees\n\n";
									field_3+="Rupees\n\n";
									field_3+="Rupees\n\n";
								}
								if(drcr_criteria.contains("DIFF-KM")) {
									field_3+="\n\n";
									field_3+="\n\n";
								}
								if(drcr_criteria.contains("DIFF-INRKM")) {
									field_3+="Rupees\n\n";
									field_3+="Rupees\n\n";
									field_3+="Rupees\n\n";
								}
								if(drcr_criteria.contains("DIFF-QTY")) {
									field_3+="\n\n";
									field_3+="\n\n";
								} 
								if(drcr_criteria.contains("DIFF-INRMMBTU")) {
									field_3+="Rupees\n\n";
									field_3+="Rupees\n\n";
									field_3+="Rupees\n\n";
								}
//							for total GROSS AMOUNT -->
								field_3+="Rupees\n\n";
								
								if(drcr_criteria.contains("DIFF-TAX")) {
									field_3+="\n";
									for(int i = 0 ; i < tax_nm.size(); i++){
										field_3+="Rupees";
										if( i  != (tax_nm.size()-1)){ 
											field_3+="\n";
										}
									}
									field_3+="\n\n";
									field_3+="\n";
									for(int i = 0 ; i < tax_nm.size(); i++){
										field_3+="Rupees";
										if( i  != (tax_nm.size()-1)){
											field_3+="\n";
										} 
									} 
									
									field_3+="\n\n";
									field_3+="\n";
									
									for(int i = 0 ; i < tax_nm.size(); i++){
										field_3+="Rupees";
										if( i  != (tax_nm.size()-1)){
											field_3+="\n";
										}
									} 
									field_3+="\n\n";
								}else{
									field_3+="\n";
									for(int i = 0 ; i < tax_nm.size(); i++){ 
										field_3+="Rupees";
										if( i  != (tax_nm.size()-1)){ 
											field_3+="\n";
										}
									} 
									field_3+="\n\n";
								} 
								field_3+="Rupees\n\n";
							
							/* end of field_3 */
								
								/* field_4 */
								field_4+="\n";
								if(drcr_criteria.contains("DIFF-LUMPSUM")) {
									field_4+=nf.format(View_truck_inv_no.size())+"\n\n\n";
									field_4+="\n\n";
									field_4+="\n\n";
									field_4+="\n\n";
								}else {
									field_4+=nf.format(tot_inv_qty)+"\n\n\n";
								}
								if(drcr_criteria.contains("DIFF-KM")) {
									field_4+=nf.format(tot_dr_cr_qty)+"\n\n";
									field_4+=nf.format(tot_diff_qty)+"\n\n";
								}
								
								if(drcr_criteria.contains("DIFF-INRKM")) {
									field_4+="\n\n";
									field_4+="\n\n";
									field_4+="\n\n";
								}
								if(drcr_criteria.contains("DIFF-QTY")) {
									field_4+=nf.format(tot_dr_cr_qty)+"\n\n";
									field_4+=nf.format(tot_diff_qty)+"\n\n";
								} 
								if(drcr_criteria.contains("DIFF-INRMMBTU")) {
									field_4+="\n\n";
									field_4+="\n\n";
									field_4+="\n\n";
								}
//								for total GROSS AMOUNT -->
									field_4+="\n\n";
									
									if(drcr_criteria.contains("DIFF-TAX")) {
										field_4+="\n";
										for(int i = 0 ; i < tax_nm.size(); i++){
											field_4+="";
											if( i  != (tax_nm.size()-1)){ 
												field_4+="\n";
											}
										}
										field_4+="\n\n";
										field_4+="\n";
										for(int i = 0 ; i < tax_nm.size(); i++){
											field_4+="";
											if( i  != (tax_nm.size()-1)){
												field_4+="\n";
											} 
										} 
										
										field_4+="\n\n";
										field_4+="\n";
										
										for(int i = 0 ; i < tax_nm.size(); i++){
											field_4+="";
											if( i  != (tax_nm.size()-1)){
												field_4+="\n";
											}
										} 
										field_4+="\n\n";
									}else{
										field_4+="\n";
										for(int i = 0 ; i < tax_nm.size(); i++){ 
											field_4+="";
											if( i  != (tax_nm.size()-1)){ 
												field_4+="\n";
											}
										} 
										field_4+="\n\n";
									} 
									field_4+="\n\n";
								
								/* end of field_4 */	
						
									/* field_5 */
									if(drcr_criteria.contains("DIFF-LUMPSUM")) {
										field_5+="Att1\n\n\n";
										field_5+=nf.format(gross_amt_inr)+"\n\n";
										field_5+=nf.format(tot_dr_cr_qty)+"\n\n";
										field_5+=nf.format(tot_diff_qty)+"\n\n";
									}else{ 
										field_5+="\n\n";
									} 
									if(drcr_criteria.contains("DIFF-KM")) {
										field_5+="\n\n";
										field_5+="\n\n";
									}
									if(drcr_criteria.contains("DIFF-INRKM")) {
										field_5+=nf.format(inv_rate)+"\n\n";
										field_5+=nf.format(dr_cr_rate)+"\n\n";
										field_5+=nf.format(diff_rate)+"\n\n";
									}
									if(drcr_criteria.contains("DIFF-QTY")) {
										field_5+="\n\n";
										field_5+="\n\n";
									} 
									if(drcr_criteria.contains("DIFF-INRMMBTU")) {
										field_5+=nf.format(inv_rate)+"\n\n";
										field_5+=nf.format(dr_cr_rate)+"\n\n";
										field_5+=nf.format(diff_rate)+"\n\n";
									}
//									for total GROSS AMOUNT -->
									field_5+="\n\n";
									
									if(drcr_criteria.contains("DIFF-TAX")) {
										field_5+="\n";
										for(int i = 0 ; i < tax_nm.size(); i++){
											field_5+=inv_tax_perc.elementAt(i);
											if( i  != (tax_nm.size()-1)){ 
												field_5+="\n";
											}
										}
										field_5+="\n\n";
										field_5+="\n";
										for(int i = 0 ; i < tax_nm.size(); i++){
											field_5+=drcr_tax_perc.elementAt(i);
											if( i  != (tax_nm.size()-1)){
												field_5+="\n";
											} 
										} 
										
										field_5+="\n\n";
										field_5+="\n";
										
										for(int i = 0 ; i < tax_nm.size(); i++){
											field_5+=diff_tax_perc.elementAt(i);
											if( i  != (tax_nm.size()-1)){
												field_5+="\n";
											}
										} 
										field_5+="\n\n";
									}else{
										field_5+="\n";
										for(int i = 0 ; i < tax_nm.size(); i++){ 
											field_5+=inv_tax_perc.elementAt(i);
											if( i  != (tax_nm.size()-1)){ 
												field_5+="\n";
											}
										} 
										field_5+="\n\n";
									} 
									field_5+="\n\n";
								
								/* end of field_5 */
									
								/* field_6 */
								
								field_6+="\n\n\n";
								if(drcr_criteria.contains("DIFF-LUMPSUM")) {
									field_6+="\n\n";
									field_6+="\n\n";
									field_6+="\n\n";
								}
								
								if(drcr_criteria.contains("DIFF-KM")) {
									field_6+="\n\n";
									field_6+="\n\n";
								}
								if(drcr_criteria.contains("DIFF-INRKM")) {
									field_6+="\n\n";
									field_6+="\n\n";
									field_6+="\n\n";
								}
								if(drcr_criteria.contains("DIFF-QTY")) {
									field_6+="\n\n";
									field_6+="\n\n";
								} 
								if(drcr_criteria.contains("DIFF-INRMMBTU")) {
									field_6+="\n\n";
									field_6+="\n\n";
									field_6+="\n\n";
								}
//									for total GROSS AMOUNT -->
								field_6+=nf7.format(Double.parseDouble(drcr_gross_amt_inr+""))+"\n\n";
								
								if(drcr_criteria.contains("DIFF-TAX")) {
									field_6+="\n";
									for(int i = 0 ; i < tax_nm.size(); i++){
										field_6+="";
										if( i  != (tax_nm.size()-1)){ 
											field_6+="\n";
										}
									}
									field_6+="\n\n";
									field_6+="\n";
									for(int i = 0 ; i < tax_nm.size(); i++){
										field_6+="";
										if( i  != (tax_nm.size()-1)){
											field_6+="\n";
										} 
									} 
									
									field_6+="\n\n";
									field_6+="\n";
									
									for(int i = 0 ; i < tax_nm.size(); i++){
										field_6+="";
										if( i  != (tax_nm.size()-1)){
											field_6+="\n";
										}
									} 
									field_6+="\n\n";
								}else{
									field_6+="\n";
									for(int i = 0 ; i < tax_nm.size(); i++){ 
										field_6+=drcr_tax_amt.elementAt(i);
										if( i  != (tax_nm.size()-1)){ 
											field_6+="\n";
										}
									} 
									field_6+="\n\n";
								} 
								field_6+=nf.format(drcr_tax_samt)+"\n\n";
							
							/* end of field_6 */
								
							
							 float[] BillingFieldsDetailsWidths_2 = {0.06f, 0.40f,0.10f, 0.15f, 0.15f, 0.15f};
				                PdfPTable BillingFieldsDetailsTable_2 = new PdfPTable(BillingFieldsDetailsWidths_2);
				                BillingFieldsDetailsTable_2.setWidthPercentage(100);
				                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_1,small_black_normal)));
				                
				                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_2,small_black_normal)));
				                
				                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_3,small_black_normal)));
				                
				                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_4,small_black_normal)));
				                
				                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_5,small_black_normal)));
				                
				                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_6,small_black_normal)));
				                
//				                for net amount
				                
				                float[] BillingFieldsDetailsWidths_3 = {0.06f, 0.40f,0.10f, 0.15f, 0.15f, 0.15f};
				                PdfPTable BillingFieldsDetailsTable_3 = new PdfPTable(BillingFieldsDetailsWidths_3);
				                BillingFieldsDetailsTable_3.setWidthPercentage(100);
				                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(""+(++sr_no)+" ",small_black_bold)));
				                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				    				BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(" Net Amount Payable",small_black_bold)));
				                
				    			/*BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(" ",small_black_bold)));
				                */
				                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk("Rupees",small_black_bold)));
				                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(" ",small_black_bold)));
				                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(" ",small_black_bold)));
				                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                
				                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(nf7.format(Double.parseDouble(drcr_net_amt_inr+""))+" ",small_black_bold)));
				                
				                PdfPTable RemarkTable = new PdfPTable(1);
					            RemarkTable.setWidthPercentage(100);
					            RemarkTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					            RemarkTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					            RemarkTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
					            RemarkTable.addCell(new Phrase(new Chunk(remark_1,small_black_normal)));
					            
					            PdfPTable RemarkTable2 = new PdfPTable(1);
					            RemarkTable.setWidthPercentage(100);
					            RemarkTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					            RemarkTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					            RemarkTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
					            RemarkTable.addCell(new Phrase(new Chunk(remark_2,small_black_normal)));
				                
					            PdfPTable SignatureInfoTable = new PdfPTable(1);
								SignatureInfoTable.setWidthPercentage(100);
								SignatureInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
								SignatureInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
								SignatureInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
								// For Company change*/SignatureInfoTable.addCell(new Phrase(new Chunk("For Hazira LNG Private Limited\n\n\n\n\nAuthorised Signatory",black_bold)));
								SignatureInfoTable.addCell(new Phrase(new Chunk("For Shell Energy India Private Limited",black_bold)));
								                       
				//////////////////////////For Digital Signature/////////////
								   // create a signature form field 
					            PdfPTable BillingFieldsInfoTable81 = new PdfPTable(1);
					            BillingFieldsInfoTable81.setWidthPercentage(20);
					            BillingFieldsInfoTable81.setHorizontalAlignment(Element.ALIGN_LEFT);
					            BillingFieldsInfoTable81.getDefaultCell().setBorder(Rectangle.BOX);
					            BillingFieldsInfoTable81.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					            BillingFieldsInfoTable81.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
					           
					            
					            PdfFormField field = PdfFormField.createSignature(writer);
					            field.setFieldName(SIGNAME);
					            // set the widget properties
					            field.setPage();
					            field.setWidget(new Rectangle(72, 732, 144, 780), PdfAnnotation.HIGHLIGHT_NONE);
					            field.setFlags(PdfAnnotation.FLAGS_PRINT);
					            writer.addAnnotation(field);
					            
					            PdfPCell sigCell = new PdfPCell();
					            FieldPositioningEvents events = new FieldPositioningEvents(writer, field);
					            sigCell.setCellEvent(events);
					            sigCell.setFixedHeight(50f);
					            BillingFieldsInfoTable81.addCell(sigCell);
					            // add it as an annotation
					            ///////////////////////////////////////////
					            
					            PdfPTable SignatureInfoTable1 = new PdfPTable(1);
								SignatureInfoTable1.setWidthPercentage(100);
								SignatureInfoTable1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
								SignatureInfoTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
								SignatureInfoTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
								//RG20190325 For Company change*/SignatureInfoTable.addCell(new Phrase(new Chunk("For Hazira LNG Private Limited\n\n\n\n\nAuthorised Signatory",black_bold)));
								SignatureInfoTable1.addCell(new Phrase(new Chunk("Authorised Signatory",black_bold)));
								                 
								
								PdfPTable GenerationInfoTable = new PdfPTable(1);
					            GenerationInfoTable.setWidthPercentage(100);
					            GenerationInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					            GenerationInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
					            GenerationInfoTable.addCell(new Phrase(new Chunk("FMS7",small_black_normal)));
					            
					            PdfPTable GenerationInfoTable2 = new PdfPTable(1);
					            GenerationInfoTable2.setWidthPercentage(100);
					            GenerationInfoTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					            GenerationInfoTable2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					            GenerationInfoTable2.addCell(new Phrase(new Chunk("This is computer generated INVOICE and hence does not require Authorised Signatory.",small_black_normal)));
					            
					            
		            document.add(hlpl_logo_table1);
	    			document.add(InvoiceTitleTable);
	    			document.add(InvoiceDescTable);
	    			document.add(InvoiceDescTable2);
	    			if(contract_type.equalsIgnoreCase("V"))
	    			{
	    				document.add(InvoiceNoteTable);
	    			}
	    			
	    			document.add(new Paragraph("              "));
	    			document.add(contact_addr_table);                        
	                document.add(GstCstInfoTable);            
	                document.add(new Paragraph("              "));
	                
	                if(!irn_no.equals("") && (!qr_code.equals(""))){
	                	document.add(InvoiceDateInfoTable_qr);
	                }else{
	    	            document.add(InvoiceDateInfoTable);
	    	            document.add(InvoiceDueDateInfoTable);
	    	            document.add(InvoiceNOInfoTable);
	                }
	                document.add(new Paragraph("              "));
	                document.add(BillingFieldsInfoTable);
	                document.add(BillingFieldsDetailsTable_2);
	                document.add(BillingFieldsDetailsTable_3);
	                document.add(new Paragraph("              "));
	                document.add(RemarkTable);
	                document.add(new Paragraph("              "));
	                document.add(RemarkTable2);
	                document.add(new Paragraph("              "));
	                document.add(SignatureInfoTable);          
	                document.add(BillingFieldsInfoTable81);
	                document.add(SignatureInfoTable1);
	                
 if(drcr_criteria.contains("DIFF-LUMPSUM")) {
	                	
	                    // ********* Following Coding is for Showing Attachment-1 Sheet on the Next (2nd Page) of the Same PDF Document *********** //
		                document.setPageSize(pageSize1);
		                document.newPage();
		          //      document.open();
		                very_small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 5, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                Font small_black_normal_new = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                Font small_black_bold2 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                Font small_black_bold2_new = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                big_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                black_bold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                if(invoice_title.contains("CREDIT") ) //HS20160615-----font
		    			{
		                	very_small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                	small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                	small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                	black_bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                	big_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 15, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                	 small_black_normal_new = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                	 small_black_bold2 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                	 small_black_bold2_new = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new Color(0x00, 0x00, 0x00));
		    			}		  
		    			url_start = "http://"+server_nm+":"+server_port+context_nm;
		    						  
		                hlpl_logo.setBorder(Rectangle.NO_BORDER);
		                //hlpl_logo.scaleAbsolute(75,75);
		                hlpl_logo.scaleAbsolute(48,45);
		                PdfPCell hlpl_logo_cell = new PdfPCell(hlpl_logo,false);
		                hlpl_logo_cell.setBorder(Rectangle.NO_BORDER);
		    			
		                
		               // float[] hlpl_logo_Widths = {0.45f, 0.51f, 0.08f};
		                float[] hlpl_logo_Widths = {0.45f};
		                PdfPTable hlpl_logo_table = new PdfPTable(hlpl_logo_Widths);
		                hlpl_logo_table.setWidthPercentage(100);
		                hlpl_logo_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                hlpl_logo_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                hlpl_logo_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                hlpl_logo_table.addCell(hlpl_logo_cell);
		                
		    			addr_supl = "Registered Office:";
		    			Paragraph pp1=new Paragraph();
		    			pp1.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
		                
		                if(!contact_Suppl_Name.trim().equals(""))
		                {
		                	//addr_supl += "\n"+contact_Suppl_Name;    
		                	pp1.add(new Phrase(new Chunk("\n"+contact_Suppl_Name,small_black_normal)));
		                }
		                if(!tempcompname.trim().equals(""))
		                {
		                	//addr_supl += "\n"+contact_Suppl_Name;    
		                	pp1.add(new Phrase(new Chunk("\n"+tempcompname,small_black_normal2)));
		                }
		                
//		                if(!contact_Suppl_Name.trim().equals(""))
//		                {
//		                	addr_supl += "\n"+contact_Suppl_Name;     	
//		                }
		                if(!contact_Suppl_Person_Address.trim().equals(""))
		                {
		                	//addr_supl += "\n"+contact_Suppl_Person_Address;
		                	addr_supl = "\n"+contact_Suppl_Person_Address;
		                }
		                if(!contact_Suppl_Person_City.trim().equals(""))
		                {
		                	addr_supl += "\n"+contact_Suppl_Person_City;     	
		                }
		                if(!contact_Suppl_Person_Pin.trim().equals(""))
		                {
		                	addr_supl += " "+"-"+" "+contact_Suppl_Person_Pin;     	
		                }
		                pp1.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
		    			
		    			addr_customer = "";
		                
		    			if(contact_Person_Name_And_Designation.trim().equalsIgnoreCase(contact_Customer_Name.trim()))
		    			{
		    				if(!contact_Customer_Name.trim().equals(""))
		    	            {
		    	            	addr_customer += contact_Customer_Name;    	
		    	            }
		    			}
		    			else
		    			{
		    	            if(!contact_Person_Name_And_Designation.trim().equals(""))
		    	            {
		    	            	addr_customer += contact_Person_Name_And_Designation;     	
		    	            }
		    	            if(!contact_Customer_Name.trim().equals(""))
		    	            {
		    	            	addr_customer += "\n"+contact_Customer_Name;     	
		    	            }
		    			}
		                if(!contact_Customer_Person_Address.trim().equals(""))
		                {
		                	addr_customer += "\n"+contact_Customer_Person_Address;     	
		                }
		                if(!contact_Customer_Person_City.trim().equals(""))
		                {
		                	addr_customer += "\n"+contact_Customer_Person_City;     	
		                }
		                if(!contact_Customer_Person_Pin.trim().equals(""))
		                {
		                	addr_customer += " "+"-"+" "+contact_Customer_Person_Pin;     	
		                }
		                            
		                          
		                float[] ContactAddrWidths2 = {0.25f, 0.55f, 0.20f};
		                contact_addr_table = new PdfPTable(ContactAddrWidths2);
		                contact_addr_table.setWidthPercentage(100);
		                
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk(contact_Suppl_Name,black_bold)));
		                
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk(" ",black_bold)));
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk("To:",black_bold)));
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                //contact_addr_table.addCell(new Phrase(new Chunk(addr_supl,small_black_normal)));
		                contact_addr_table.addCell(pp1);
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk(addr_customer,small_black_normal)));
		                String title_note="ATTACHMENT 1 - Applicable Lumpsum";
		                
		                
		                PdfPTable title_note_table = new PdfPTable(1);
		                title_note_table.setWidthPercentage(100);
		                title_note_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                title_note_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                title_note_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                title_note_table.addCell(new Phrase(new Chunk(title_note,big_black_bold)));
		                
		            
		                String rate_unit = "";
		                if(View_calc_bs.contains("1")) {
							rate_unit = "(INR/MMBtu)";
						}else if(View_calc_bs.contains("2")){ 
							rate_unit = "(INR/KM)";
						}else if(View_calc_bs.contains("3")){
							rate_unit = "(INR/TRUCK)";
						}
		                
		                float[] attach_dtl_table_width = {0.05f,0.10f,0.10f,0.10f,0.10f,0.20f,0.20f,0.10f};
		                PdfPTable attach_dtl_table = new PdfPTable(attach_dtl_table_width);
		                attach_dtl_table.setWidthPercentage(100);
		                
		                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
		                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.addCell(new Phrase(new Chunk("Sr. No.",small_black_normal)));
		                
		                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
		                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.addCell(new Phrase(new Chunk("Supply Date",small_black_normal)));
		                
		                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
		                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.addCell(new Phrase(new Chunk("Truck Number",small_black_normal)));
		                
		                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
		                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.addCell(new Phrase(new Chunk("Truck Quantity (MMBtu)",small_black_normal)));
		                
		                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
		                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.addCell(new Phrase(new Chunk("Rate \n"+rate_unit,small_black_normal)));
		                
		                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
		                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.addCell(new Phrase(new Chunk(drcr+" Rate",small_black_normal)));
		                
		                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
		                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.addCell(new Phrase(new Chunk("Diff. "+drcr+" Rate",small_black_normal)));
		                
		                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
		                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.addCell(new Phrase(new Chunk("Amount (INR)",small_black_normal)));
		                
//		                System.out.println("View_truck_inv_no**"+View_truck_inv_no.size());
		                for(int i = 0 ; i < View_truck_inv_no.size() ; i++){
		                	int srNo = i+1 ;
		                	String amt = "" ;
		                	String diff_qty = "";
		                	if(View_calc_bs.elementAt(i).toString().equalsIgnoreCase("3")){ 
		                		amt = View_amount.elementAt(i)+"";
		                		diff_qty = nf7.format(Double.parseDouble(View_diff_qty.elementAt(i)+""));
							}else{ 
								amt = View_rate.elementAt(i)+"";
								diff_qty = nf7.format(Double.parseDouble(View_amount.elementAt(i)+""));
							} 
		                	
		                	
		                	attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
			                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.addCell(new Phrase(new Chunk(srNo+"",small_black_normal)));
			                
			                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
			                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.addCell(new Phrase(new Chunk(View_truck_inv_dt.elementAt(i)+"",small_black_normal)));
			                
			                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
			                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.addCell(new Phrase(new Chunk(View_truck_nm.elementAt(i)+"",small_black_normal)));
			                
			                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
			                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.addCell(new Phrase(new Chunk(View_invoice_qty.elementAt(i)+"",small_black_normal)));
			                
			                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
			                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.addCell(new Phrase(new Chunk(amt,small_black_normal)));
			                
			                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
			                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.addCell(new Phrase(new Chunk(nf.format(Double.parseDouble(View_dr_cr_qty.elementAt(i)+"")),small_black_normal)));
			                
			                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
			                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.addCell(new Phrase(new Chunk(nf.format(Double.parseDouble(View_diff_qty.elementAt(i)+"")),small_black_normal)));
			                
			                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
			                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.addCell(new Phrase(new Chunk(diff_qty,small_black_normal)));
		                	
		                }
		                
		                document.add(new Paragraph("              "));
						//document.add(hlpl_logo_table);//RG20190516
						document.add(new Paragraph("              "));
						document.add(contact_addr_table);                        
			            document.add(new Paragraph("              "));
			            document.add(title_note_table);
			            document.add(new Paragraph("              "));
			            document.add(InvoiceDateInfoTable);
			            document.add(InvoiceDueDateInfoTable);
			            document.add(InvoiceNOInfoTable);
			            document.add(new Paragraph("              "));
			            document.add(new Paragraph("              "));
			            document.add(attach_dtl_table);
			            
			            document.add(new Paragraph("              "));
			            document.add(new Paragraph("              "));
	                }
		            
				}catch(DocumentException de)
				{
					System.err.println("DocumentException in printAllPdfFileForInvoice() Method :\n"+de.getMessage());
					de.printStackTrace();
				}
				catch(IOException ioe)
				{
					System.err.println("IOException in printAllPdfFileForInvoice() Method :\n"+ioe.getMessage());
					ioe.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}
				finally
				{
					document.close();
				}
			
		}
		private void printPdfFileForDR_CR() throws Exception {
			
			Rectangle pageSize = new Rectangle(595, 842);
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
			 Document document = new Document(pageSize);
			 try
				{
					PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(createPdfFileForInvoice()));
					document.addTitle("Invoice Details");
					document.addSubject("Invoice Details For Customer");
		            document.addKeywords("iText, Invoice Details, Step 2, metadata");
		            document.addCreator("Invoice Details Generation using iText");
		            document.addAuthor("FMS8@BIPL");
		            document.open();
					document.setPageSize(pageSize);
		            document.newPage();

		            Font very_small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		            Font small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		            Font small_black_normal2 = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		            Font small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
		            Font black_bold = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new Color(0x00, 0x00, 0x00));
		            Font big_black_bold_2 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
		            Font big_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new Color(0x00, 0x00, 0x00));
		            
		            String context_nm = request.getContextPath();
					String server_nm = request.getServerName();
					String server_port = ""+request.getServerPort();
					  
					url_start = "http://"+server_nm+":"+server_port+context_nm;
					
		           // Image hlpl_logo = Image.getInstance(url_start+"//images//LOGO//HLPL-Symbol.jpg");
					 Image hlpl_logo = Image.getInstance(url_start+"/images/logo/company_Logo.png");
					 /*RG20191128 added for adding logo for sign pdf*/
					 	hlpl_logo.setBorder(Rectangle.NO_BORDER);
			            hlpl_logo.scaleAbsolute(48,45);
			            PdfPCell hlpl_logo_cell1 = new PdfPCell(hlpl_logo,false);
			            hlpl_logo_cell1.setBorder(Rectangle.NO_BORDER);
			            float[] hlpl_logo_Widths1 = {0.45f};
			            PdfPTable hlpl_logo_table1 = new PdfPTable(hlpl_logo_Widths1);
			            hlpl_logo_table1.setWidthPercentage(100);
			            hlpl_logo_table1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            hlpl_logo_table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			            hlpl_logo_table1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
			            hlpl_logo_table1.addCell(hlpl_logo_cell1);
					  /* ended adding logo*/ 
					           
					String inv_nm = "TAX INVOICE\nTAX Invoice issued under Rule 46 of the Central Goods and Services Tax Rules, 2017";
					String inv_desc = "Shell Energy India Private Limited";
					String inv_note = "In respect of Transport Management Service Agreement ("+trans_cont_no+") executed on "+signing_dt+" \nbetween "+contact_Suppl_Name+" and "+contact_Customer_Name;
					
					String addr_supl = "Registered Office:";
					Paragraph pp=new Paragraph();
					pp.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
		            
		            if(!contact_Suppl_Name.trim().equals(""))
		            {
		            	pp.add(new Phrase(new Chunk("\n"+contact_Suppl_Name,small_black_normal)));
		            }
		            
		            if(!contact_Suppl_Person_Address.trim().equals(""))
		            {
		            	//addr_supl += "\n"+contact_Suppl_Person_Address;
		            	addr_supl = "\n"+contact_Suppl_Person_Address;
		            }
		            if(!contact_Suppl_Person_City.trim().equals(""))
		            {
		            	addr_supl += "\n"+contact_Suppl_Person_City;     	
		            }
		            if(!contact_Suppl_Person_Pin.trim().equals(""))
		            {
		            	addr_supl += " "+"-"+" "+contact_Suppl_Person_Pin;     	
		            }
		            pp.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
					
					String addr_customer = "";
		            
					if(contact_Person_Name_And_Designation.trim().equalsIgnoreCase(contact_Customer_Name.trim()))
					{
						if(!contact_Customer_Name.trim().equals(""))
			            {
			            	addr_customer += contact_Customer_Name;    	
			            }
					}
					else
					{
			            if(!contact_Person_Name_And_Designation.trim().equals(""))
			            {
			            	addr_customer += contact_Person_Name_And_Designation;     	
			            }
			            if(!contact_Customer_Name.trim().equals(""))
			            {
			            	addr_customer += "\n"+contact_Customer_Name;     	
			            }
					}
		            if(!contact_Customer_Person_Address.trim().equals(""))
		            {
		            	addr_customer += "\n"+contact_Customer_Person_Address;     	
		            }
		            if(!contact_Customer_Person_City.trim().equals(""))
		            {
		            	addr_customer += "\n"+contact_Customer_Person_City;     	
		            }
		            if(!contact_Customer_Person_Pin.trim().equals(""))
		            {
		            	addr_customer += " "+"-"+" "+contact_Customer_Person_Pin;     	
		            }
		            
		            PdfPTable InvoiceTitleTable = new PdfPTable(1);
		            InvoiceTitleTable.setWidthPercentage(100);
		            InvoiceTitleTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            InvoiceTitleTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		          //SB20160601  InvoiceTitleTable.addCell(new Phrase(new Chunk(invoice_title,black_bold)));
		            
		            if(invoice_title.contains("CREDIT") || invoice_title.contains("DEBIT")  ) //SB20160601
		            {
		            	String inv_tit = invoice_title.substring(invoice_title.length()-1,invoice_title.length());
//		            	System.out.println("inv_tit******"+inv_tit);
		            	if(inv_tit.equals("O")) {
		            		inv_tit = "ORIGINAL";
		            	} else if (inv_tit.equals("D")){
		            		inv_tit = "DUPLICATE";
		            	}else {
		            		inv_tit = "TRIPLICATE";
		            	}
		            	InvoiceTitleTable.addCell(new Phrase(new Chunk(inv_tit,black_bold)));
		            }
		            PdfPTable InvoiceDescTable = new PdfPTable(1);
		            InvoiceDescTable.setWidthPercentage(100);
		            InvoiceDescTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            InvoiceDescTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            InvoiceDescTable.addCell(new Phrase(new Chunk(inv_desc,big_black_bold)));
					
		            PdfPTable InvoiceDescTable2 = new PdfPTable(1);
		            InvoiceDescTable2.setWidthPercentage(100);
		            InvoiceDescTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            InvoiceDescTable2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            if(invoice_title.contains("CREDIT") ) {
		            	if(contract_type.equals("S")  || contract_type.equals("L")){
		            		InvoiceDescTable2.addCell(new Phrase(new Chunk("CREDIT NOTE",big_black_bold_2)));
		            	}else{
		            		InvoiceDescTable2.addCell(new Phrase(new Chunk("CREDIT NOTE\nCredit Note issued under Rule 53 of the Central Goods and Services Tax Rules, 2017",big_black_bold_2)));
		            	}
		            }else if(invoice_title.contains("Sup")){
		            		InvoiceDescTable2.addCell(new Phrase(new Chunk("DEBIT NOTE\nDebit Note issued under Rule 53 of the Central Goods and Services Tax Rules, 2017",big_black_bold_2)));
		            }else if(invoice_title.contains("DEBIT")){
		            	//	InvoiceDescTable2.addCell(new Phrase(new Chunk("DEBIT NOTE\nDebit Note issued under Rule 53 of the Central Goods and Services Tax Rules, 2017",big_black_bold_2)));
		            		InvoiceDescTable2.addCell(new Phrase(new Chunk("DEBIT NOTE",big_black_bold_2)));
		            }else{
		            	InvoiceDescTable2.addCell(new Phrase(new Chunk(inv_nm,big_black_bold_2)));
		            }
		            
		            PdfPTable InvoiceNoteTable = new PdfPTable(1);
		            InvoiceNoteTable.setWidthPercentage(100);
		            InvoiceNoteTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            InvoiceNoteTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            InvoiceNoteTable.addCell(new Phrase(new Chunk(inv_note,small_black_normal)));
					
		            float[] ContactAddrWidths = {0.50f, 0.10f, 0.40f};
		            PdfPTable contact_addr_table = new PdfPTable(ContactAddrWidths);
		            contact_addr_table.setWidthPercentage(100);
		            contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		         //   contact_addr_table.addCell(new Phrase(new Chunk(addr_supl,small_black_normal)));
		            contact_addr_table.addCell(pp);
		            contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            contact_addr_table.addCell(new Phrase(new Chunk("To:",small_black_bold)));
		            contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            contact_addr_table.addCell(new Phrase(new Chunk(addr_customer,small_black_normal)));
					
		            String supl_gst_cst_info = "";
		            String customer_gst_cst_info = "";
		            
		          //  //System.out.println("-----INSIDE PRINTALL 2---"+vSTAT_CD.size());
		            
		            if(contract_type.equalsIgnoreCase("V")){

		            	if(!contact_Suppl_State.trim().equals("")) {
		            		supl_gst_cst_info = "State : "+contact_Suppl_State;
		            	}
		            	if(!contact_Suppl_State_Code.trim().equals("")) {
		            		supl_gst_cst_info += "\nState Code : "+contact_Suppl_State_Code;
		            	}
		            	if(!contact_Suppl_GSTIN_NO.trim().equals(""))
		            	{
		            		supl_gst_cst_info += "\nGSTIN No. : "+contact_Suppl_GSTIN_NO;
		            		if(!contact_Suppl_GST_DT.equals("")) {
		            			supl_gst_cst_info+=" DT. "+contact_Suppl_GST_DT;
		            		}
		            	}
		            	if(!contact_Suppl_PAN_NO.trim().equals(""))
		            	{
		            		supl_gst_cst_info += " \nPAN No.: "+contact_Suppl_PAN_NO+" ";
		            	}
		            	if(!sac_code.trim().equals(""))
		            	{
		            		supl_gst_cst_info += " \nSAC No. : "+sac_code+" ";
		            	}
		            	if(!sac_desc.trim().equals(""))
		            	{
		            		supl_gst_cst_info += " \nDescription of Service : "+sac_desc+" ";
		            	}
		            	if(!contact_customer_State.trim().equals(""))
		            	{
		            		supl_gst_cst_info += " \nPlace of Supply : "+contact_customer_State+" ";
		            	}
		    		}
		            if(!contact_customer_State.trim().equals(""))
	            	{
		            	customer_gst_cst_info = "State : "+contact_customer_State+" ";
	            	}
		            if(!contact_customer_State_Code.trim().equals(""))
	            	{
		            	customer_gst_cst_info += "\nState Code : "+contact_customer_State_Code+" ";
	            	}
		            
		            if(vSTAT_CD.size()>0)
		    		{	
		    			if(contract_type.equalsIgnoreCase("V"))
		    			{
		    				for(int i=0; i<vSTAT_CD.size(); i++)
							{
	    						if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) 
	    						{
	    							customer_gst_cst_info += "\nPAN No. : "+vSTAT_NO.elementAt(i)+" ";
	    						}
	    						else
	    						{
	    							if(vSTAT_NM.elementAt(i).toString().contains("GSTIN")) 
		    						{
	    								customer_gst_cst_info += "\nGSTIN No. : "+vSTAT_NO.elementAt(i);
	    								if(!vSTAT_EFF_DT.elementAt(i).equals("")) {
	    									customer_gst_cst_info+=" DT. "+vSTAT_EFF_DT.elementAt(i);
	    			            		}
		    						}
	    						}
							}
		    			}
		    		}
		           
		            float[] GstCstInfoWidths = {0.50f, 0.10f, 0.40f};
		            PdfPTable GstCstInfoTable = new PdfPTable(GstCstInfoWidths);
		            GstCstInfoTable.setWidthPercentage(100);
		            GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            GstCstInfoTable.addCell(new Phrase(new Chunk("\n"+supl_gst_cst_info,small_black_normal)));
		            GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            GstCstInfoTable.addCell(new Phrase(new Chunk("\n ",small_black_normal)));
		            GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            GstCstInfoTable.addCell(new Phrase(new Chunk("\n"+customer_gst_cst_info,small_black_normal)));
		            
		            String invno = "";
//		            System.out.println("hlpl_drcr_docNo**"+hlpl_drcr_docNo+"**"+hlpl_inv_no);
		            if(!hlpl_drcr_docNo.equals("")) {
		            	invno = hlpl_drcr_docNo;
		            } else {
		            	if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		            	{
		            		if(hlpl_inv_no.length()>13)
		            		{
		            			invno = hlpl_inv_no.substring(0,10)+""+hlpl_inv_no.substring(12);
		            		}      		
		            	}
		            	else
		            	{
		            		if(hlpl_inv_no.length()>13)
		            		{
		            			invno = hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
		            		}       		
		            	}
		            }
//		            System.out.println("invno**"+invno);
		            String inv_no_info = "";
					String invoiceType = "";
					String inv_dt_Header = "Invoice Date ";
					String Inv_Ref_Dtl = ""; //SB20160611  
					 
					String inv_due_dt_Header = "Payment Due Date ";
					if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT")) //SB20160530
					{             
				         Inv_Ref_Dtl = " as per Invoice ref "+invno+" dated "+customer_Invoice_DT;
				        if(invoice_title.contains("CREDIT")){    
						invoiceType = "Credit Note No ";
				        }else if(invoice_title.contains("Sup")){
				        	invoiceType = "Debit Note No ";
				        }else {
				        	invoiceType = "Debit Note No ";
				        }
				//SB*		inv_dt_Header = "Invoice  Ref No: "+invno;
						inv_dt_Header = "";
						//customer_Invoice_DT =invno+":"+customer_Invoice_DT;
						if(invoice_title.contains("CREDIT")){    
						inv_due_dt_Header = "Credit Note Date ";
						}else if(invoice_title.contains("Sup")){
							inv_due_dt_Header = "Debit Note Date ";
						}else {
							inv_due_dt_Header = "Debit Note Date ";
						}
						customer_Invoice_Due_DT = hlpl_drcr_dt;
					}
					else
					{
						invoiceType = "SEIPL TAX Invoice Seq No ";
					}
					
					if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT")){ //SB20160530
						if(invoice_title.contains("CREDIT")){
							//RG20190325 For Company change*/inv_no_info = "HLPL Credit Note No:";
							inv_no_info = "SEIPL Credit Note No ";
						}else if(invoice_title.contains("Sup")){
							//RG20190325 For Company change*/inv_no_info = "HLPL Debit Note No:"; 
							inv_no_info = "SEIPL Debit Note No "; 
						}else {
						//RG20190325 For Company change*/	inv_no_info = "HLPL Debit Note No:"; 
							inv_no_info = "SEIPL Debit Note No ";
						}
					}
					else{
						inv_no_info = "SEIPL TAX Invoice Seq No ";
					}
					
					PdfPTable InvoiceDateInfoTable; 
					PdfPTable InvoiceDueDateInfoTable;
					PdfPTable InvoiceNOInfoTable;
					PdfPTable BillingPeriodInfoTable;
					
					 float[] InvoiceDateInfoWidths_qr = {0.40f, 0.20f, 0.60f};
					 PdfPTable InvoiceDateInfoTable_qr = new PdfPTable(InvoiceDateInfoWidths_qr);
		           if(!irn_no.equals("") && (!qr_code.equals(""))){
		        	   
//						float[] InvoiceDateInfoWidths = {0.60f, 0.25f, 0.15f};
						float[] InvoiceDateInfoWidths = {0.20f, 0.45f, 0.25f};
			            InvoiceDateInfoTable = new PdfPTable(InvoiceDateInfoWidths);
			            InvoiceDateInfoTable.setWidthPercentage(100);
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			        //SB20160531    InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT")) //SB20160530
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_dt_Header,small_black_bold)));
			            else
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT"))//SB20160530
			            {
				            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
				            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
				            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            }
			            else
			            {
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk("Invoice Date ",small_black_bold)));
			            	InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_DT,small_black_bold)));
			            }
			            float[] InvoiceDueDateInfoWidths = {0.60f, 0.25f, 0.15f};
			            InvoiceDueDateInfoTable = new PdfPTable(InvoiceDueDateInfoWidths);
			            
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
		                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
		                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_due_dt_Header,small_black_bold)));
		                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
		                
		                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_DT,small_black_bold)));
			            
			            if(invoice_title.contains("Sup") || invoice_title.contains("DEBIT")) {
			            	
			            	InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk("Payment Due Date ",small_black_bold)));
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(dr_cr_due_dt,small_black_bold)));
			              }
			                float[] InvoiceNOInfoWidths = {0.60f, 0.25f, 0.15f};
			                InvoiceNOInfoTable = new PdfPTable(InvoiceNOInfoWidths);
			                
			                
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_no_info,small_black_bold)));
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(invno,small_black_bold)));            
			                
			                
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(3);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk("",small_black_bold)));
			                
			                //code for Irn & qrcode starts now
			                url_start = "http://"+server_nm+":"+server_port+context_nm;
			                    String qrCodeText = qr_code;
			                    String filePath =  request.getRealPath("/pdf_reports/xls_reports/qrcode");
			          		  filePath = filePath+"//"+invno.replace("/","_")+".png";
			          		  int size = 45;//125 original
			          		  String fileType = "png";
			          		  File qrFile = new File(filePath);
			          		  com.seipl.hazira.dlng.util.DataBean_QRCode_Generator.createQRImage(qrFile, qrCodeText, size, fileType);
			                    
			                  Image qr_codeimg = Image.getInstance(url_start+"//pdf_reports//xls_reports//qrcode//"+invno.replace("/","_")+".png");			  
			                  qr_codeimg.setBorder(Rectangle.NO_BORDER);
			                  qr_codeimg.setAlignment(Element.ALIGN_LEFT);
			                 // qr_codeimg.scaleAbsolute(75,75);
			                //  qr_codeimg.scaleAbsolute(50,50);
			                  PdfPCell qrcode_cell = new PdfPCell(qr_codeimg,false);
			                  qrcode_cell.setBorder(Rectangle.NO_BORDER);
			                  qrcode_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			                      
//			                    String irn_no=irn_no;
			                    String char_16= irn_no.substring(0,16);
			                    String char_32= irn_no.substring(16,32);
			                    String char_48= irn_no.substring(32,48);
			                    String char_64= irn_no.substring(48,irn_no.length());
			                    String final_irn_no=char_16+"\n"+char_32+"\n"+char_48+"\n"+char_64;
			                    
			                    PdfPTable InvoiceDateInfoTable_qr1 = new PdfPTable(1);
			        	        InvoiceDateInfoTable_qr1.setWidthPercentage(100);
//			        	        InvoiceDateInfoTable_qr1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//			        	        InvoiceDateInfoTable_qr1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//			        	        InvoiceDateInfoTable_qr1.addCell(new Phrase(new Chunk("QR Code",small_black_bold)));
			        	        InvoiceDateInfoTable_qr1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	        InvoiceDateInfoTable_qr1.getDefaultCell().setBorder(Rectangle.BOX);
			        	        InvoiceDateInfoTable_qr1.addCell(qrcode_cell);
			        	        
			        	        PdfPTable InvoiceDateInfoTable_qr11 = new PdfPTable(1);
			        	        InvoiceDateInfoTable_qr11.setWidthPercentage(100);
			        	        InvoiceDateInfoTable_qr11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	        InvoiceDateInfoTable_qr11.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			        	        InvoiceDateInfoTable_qr11.addCell(new Phrase(new Chunk("IRN no.",small_black_bold)));
			        	        InvoiceDateInfoTable_qr11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	        InvoiceDateInfoTable_qr11.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			        	        InvoiceDateInfoTable_qr11.addCell(new Phrase(new Chunk(final_irn_no,small_black_normal)));
			                    
			        			
			        	        InvoiceDateInfoTable_qr.setWidthPercentage(100);
			        	               
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			        	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable_qr1);
			        	        
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.BOX);
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable_qr11);
			        	        
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			        	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable);
		           }else{
			        	   
						float[] InvoiceDateInfoWidths = {0.60f, 0.25f, 0.15f};
			            InvoiceDateInfoTable = new PdfPTable(InvoiceDateInfoWidths);
			            InvoiceDateInfoTable.setWidthPercentage(100);
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			        //SB20160531    InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            if(invoice_title.contains("CREDIT") || invoice_title.contains("DEBIT")) //SB20160530
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_dt_Header,small_black_bold)));
			            else
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            if(invoice_title.contains("CREDIT") ||  invoice_title.contains("DEBIT"))//SB20160530
			            {
				            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
				            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
				            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            }
			            
			            float[] InvoiceDueDateInfoWidths = {0.60f, 0.25f, 0.15f};
			            InvoiceDueDateInfoTable = new PdfPTable(InvoiceDueDateInfoWidths);
			            InvoiceDueDateInfoTable.setWidthPercentage(100);
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(inv_due_dt_Header,small_black_bold)));
			                    
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_DT,small_black_bold)));
			            
			            if(invoice_title.contains("DEBIT")) {
			            	
			            	InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDueDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDueDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk("Payment Due Date ",small_black_bold)));
			                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDueDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(dr_cr_due_dt,small_black_bold)));
			              }
			                float[] InvoiceNOInfoWidths = {0.60f, 0.25f, 0.15f};
			                InvoiceNOInfoTable = new PdfPTable(InvoiceNOInfoWidths);
			                InvoiceNOInfoTable.setWidthPercentage(100);
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(inv_no_info,small_black_bold)));
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(invno,small_black_bold)));
			           }
		           
			            float[] BillingFieldsInfoWidths = {0.06f, 0.40f,0.10f, 0.15f, 0.15f, 0.15f};
		                PdfPTable BillingFieldsInfoTable = new PdfPTable(BillingFieldsInfoWidths);
		                BillingFieldsInfoTable.setWidthPercentage(100);
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Sr. No.",small_black_bold)));
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Item",small_black_bold)));
		               
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Currency",small_black_bold)));
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Quantity\n"+qty_unit,small_black_bold)));
		                
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Rate\n"+rate_unit,small_black_bold)));
		                
		                
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Amount",small_black_bold)));
		                
		                
		                String field_1 = "";
		                String field_2 = "";
		                String field_3 = "";
		                String field_4 = "";
		                String field_5 = "";
		                String field_6 = "";
			           
		                int sr_no = 0;
							field_1+="\n";
							field_1+=++sr_no+"\n\n\n";
								if(drcr_criteria.contains("DIFF-LUMPSUM")) {
									field_1+=++sr_no+"\n\n";
									field_1+=++sr_no+"\n\n";
									field_1+=++sr_no+"\n\n";
								}
								if(drcr_criteria.contains("DIFF-KM")) {
									field_1+=++sr_no+"\n\n";
									field_1+=++sr_no+"\n\n";
								}
								if(drcr_criteria.contains("DIFF-INRKM")) {
									field_1+=++sr_no+"\n\n";
									field_1+=++sr_no+"\n\n";
									field_1+=++sr_no+"\n\n";
								}
								if(drcr_criteria.contains("DIFF-QTY")) {
									field_1+=++sr_no+"\n\n";
									field_1+=++sr_no+"\n\n";
								} 
								if(drcr_criteria.contains("DIFF-INRMMBTU")) {
									field_1+=++sr_no+"\n\n";
									field_1+=++sr_no+"\n\n";
									field_1+=++sr_no+"\n\n";
								}
								
//							for total GROSS AMOUNT -->
								field_1+=++sr_no+"\n\n";
								if(drcr_criteria.contains("DIFF-TAX")) {
									field_1+=++sr_no+"\n";
									for(int i = 0 ; i < tax_nm.size(); i++){
										if( i  != (tax_nm.size()-1)){ 
											field_1+="\n";
										}
									}
									field_1+="\n\n";
									field_1+=++sr_no+"\n";
									for(int i = 0 ; i < tax_nm.size(); i++){
										if( i  != (tax_nm.size()-1)){
											field_1+="\n";
										} 
									} 
									
									field_1+="\n\n";
									field_1+=++sr_no+"\n";
									
									for(int i = 0 ; i < tax_nm.size(); i++){
										if( i  != (tax_nm.size()-1)){
											field_1+="\n";
										}
									} 
									field_1+="\n\n";
								}else{
									field_1+=++sr_no+"\n";
									for(int i = 0 ; i < tax_nm.size(); i++){
										if( i  != (tax_nm.size()-1)){
											field_1+="\n";
										}
									} 
									field_1+="\n\n";
								} 
								field_1+=++sr_no+"\n\n";
								
							/* field_2 */
							field_2+="\n";
							field_2+="Transport Management Services Charge \nas per invoice ref. "+new_inv_seq_no+" dated "+formated_inv_dt+"\n\n";
								if(drcr_criteria.contains("DIFF-LUMPSUM")) {
									field_2+="Invoice Invoice INR/Lumpsum\n\n";
									field_2+="Applicable INR/Lumpsum\n\n";
									field_2+="Difference in INR/Lumpsum\n\n";
								}
								if(drcr_criteria.contains("DIFF-KM")) {
									field_2+="Applicable KM\n\n";
									field_2+="Difference in KM\n\n";
								}
								if(drcr_criteria.contains("DIFF-INRKM")) {
									field_2+="Invoice INR/KM\n\n";
									field_2+="Applicable INR/KM\n\n";
									field_2+="Difference in INR/KM\n\n";
								}
								if(drcr_criteria.contains("DIFF-QTY")) {
									field_2+="Applicable Quantity\n\n";
									field_2+="Difference Quantity\n\n";
								} 
								if(drcr_criteria.contains("DIFF-INRMMBTU")) {
									field_2+="Invoice INR/MMBtu\n\n";
									field_2+="Applicable INR/MMBtu\n\n";
									field_2+="Difference INR/MMBtu\n\n";
								}
//							for total GROSS AMOUNT -->
								field_2+="Gross Amount\n\n";
								
								if(drcr_criteria.contains("DIFF-TAX")) {
									field_2+="Invoice TAX\n";
									for(int i = 0 ; i < tax_nm.size(); i++){
										field_2+=tax_nm.elementAt(i);
										if( i  != (tax_nm.size()-1)){ 
											field_2+="\n";
										}
									}
									field_2+="\n\n";
									field_2+="Applicable TAX (%)\n";
									for(int i = 0 ; i < tax_nm.size(); i++){
										field_2+=tax_nm.elementAt(i);
										if( i  != (tax_nm.size()-1)){
											field_2+="\n";
										} 
									} 
									
									field_2+="\n\n";
									field_2+="Difference TAX (%)\n";
									
									for(int i = 0 ; i < tax_nm.size(); i++){
										field_2+=tax_nm.elementAt(i);
										if( i  != (tax_nm.size()-1)){
											field_2+="\n";
										}
									} 
									field_2+="\n\n";
								}else{
									field_2+="Invoice TAX (%) \n";
									for(int i = 0 ; i < tax_nm.size(); i++){ 
										field_2+=tax_nm.elementAt(i);
										if( i  != (tax_nm.size()-1)){ 
											field_2+="\n";
										}
									} 
									field_2+="\n\n";
								} 
								field_2+="Total GST\n\n";
							
							/* end of field_2 */
								
								/* field_3 */
								field_3+="\n";
								field_3+="\n\n";
									if(drcr_criteria.contains("DIFF-LUMPSUM")) {
										field_3+="Rupees\n\n";
										field_3+="Rupees\n\n";
										field_3+="Rupees\n\n";
									}
									if(drcr_criteria.contains("DIFF-KM")) {
										field_3+="\n\n";
										field_3+="\n\n";
									}
									if(drcr_criteria.contains("DIFF-INRKM")) {
										field_3+="Rupees\n\n";
										field_3+="Rupees\n\n";
										field_3+="Rupees\n\n";
									}
									if(drcr_criteria.contains("DIFF-QTY")) {
										field_3+="\n\n";
										field_3+="\n\n";
									} 
									if(drcr_criteria.contains("DIFF-INRMMBTU")) {
										field_3+="Rupees\n\n";
										field_3+="Rupees\n\n";
										field_3+="Rupees\n\n";
									}
//								for total GROSS AMOUNT -->
									field_3+="Rupees\n\n";
									
									if(drcr_criteria.contains("DIFF-TAX")) {
										field_3+="\n";
										for(int i = 0 ; i < tax_nm.size(); i++){
											field_3+="Rupees";
											if( i  != (tax_nm.size()-1)){ 
												field_3+="\n";
											}
										}
										field_3+="\n\n";
										field_3+="\n";
										for(int i = 0 ; i < tax_nm.size(); i++){
											field_3+="Rupees";
											if( i  != (tax_nm.size()-1)){
												field_3+="\n";
											} 
										} 
										
										field_3+="\n\n";
										field_3+="\n";
										
										for(int i = 0 ; i < tax_nm.size(); i++){
											field_3+="Rupees";
											if( i  != (tax_nm.size()-1)){
												field_3+="\n";
											}
										} 
										field_3+="\n\n";
									}else{
										field_3+="\n";
										for(int i = 0 ; i < tax_nm.size(); i++){ 
											field_3+="Rupees";
											if( i  != (tax_nm.size()-1)){ 
												field_3+="\n";
											}
										} 
										field_3+="\n\n";
									} 
									field_3+="Rupees\n\n";
								
								/* end of field_3 */
									
									/* field_4 */
									field_4+="\n";
									if(drcr_criteria.contains("DIFF-LUMPSUM")) {
										field_4+=nf.format(View_truck_inv_no.size())+"\n\n\n";
										field_4+="\n\n";
										field_4+="\n\n";
										field_4+="\n\n";
									}else {
										field_4+=nf.format(tot_inv_qty)+"\n\n\n";
									}
									if(drcr_criteria.contains("DIFF-KM")) {
										field_4+=nf.format(tot_dr_cr_qty)+"\n\n";
										field_4+=nf.format(tot_diff_qty)+"\n\n";
									}
									
									if(drcr_criteria.contains("DIFF-INRKM")) {
										field_4+="\n\n";
										field_4+="\n\n";
										field_4+="\n\n";
									}
									if(drcr_criteria.contains("DIFF-QTY")) {
										field_4+=nf.format(tot_dr_cr_qty)+"\n\n";
										field_4+=nf.format(tot_diff_qty)+"\n\n";
									} 
									if(drcr_criteria.contains("DIFF-INRMMBTU")) {
										field_4+="\n\n";
										field_4+="\n\n";
										field_4+="\n\n";
									}
//									for total GROSS AMOUNT -->
										field_4+="\n\n";
										
										if(drcr_criteria.contains("DIFF-TAX")) {
											field_4+="\n";
											for(int i = 0 ; i < tax_nm.size(); i++){
												field_4+="";
												if( i  != (tax_nm.size()-1)){ 
													field_4+="\n";
												}
											}
											field_4+="\n\n";
											field_4+="\n";
											for(int i = 0 ; i < tax_nm.size(); i++){
												field_4+="";
												if( i  != (tax_nm.size()-1)){
													field_4+="\n";
												} 
											} 
											
											field_4+="\n\n";
											field_4+="\n";
											
											for(int i = 0 ; i < tax_nm.size(); i++){
												field_4+="";
												if( i  != (tax_nm.size()-1)){
													field_4+="\n";
												}
											} 
											field_4+="\n\n";
										}else{
											field_4+="\n";
											for(int i = 0 ; i < tax_nm.size(); i++){ 
												field_4+="";
												if( i  != (tax_nm.size()-1)){ 
													field_4+="\n";
												}
											} 
											field_4+="\n\n";
										} 
										field_4+="\n\n";
									
									/* end of field_4 */	
							
										/* field_5 */
										if(drcr_criteria.contains("DIFF-LUMPSUM")) {
											field_5+="Att1\n\n\n";
											field_5+=nf.format(gross_amt_inr)+"\n\n";
											field_5+=nf.format(tot_dr_cr_qty)+"\n\n";
											field_5+=nf.format(tot_diff_qty)+"\n\n";
										}else{ 
											field_5+="\n\n";
										} 
										if(drcr_criteria.contains("DIFF-KM")) {
											field_5+="\n\n";
											field_5+="\n\n";
										}
										if(drcr_criteria.contains("DIFF-INRKM")) {
											field_5+=nf.format(inv_rate)+"\n\n";
											field_5+=nf.format(dr_cr_rate)+"\n\n";
											field_5+=nf.format(diff_rate)+"\n\n";
										}
										if(drcr_criteria.contains("DIFF-QTY")) {
											field_5+="\n\n";
											field_5+="\n\n";
										} 
										if(drcr_criteria.contains("DIFF-INRMMBTU")) {
											field_5+=nf.format(inv_rate)+"\n\n";
											field_5+=nf.format(dr_cr_rate)+"\n\n";
											field_5+=nf.format(diff_rate)+"\n\n";
										}
//										for total GROSS AMOUNT -->
										field_5+="\n\n";
										
										if(drcr_criteria.contains("DIFF-TAX")) {
											field_5+="\n";
											for(int i = 0 ; i < tax_nm.size(); i++){
												field_5+=inv_tax_perc.elementAt(i);
												if( i  != (tax_nm.size()-1)){ 
													field_5+="\n";
												}
											}
											field_5+="\n\n";
											field_5+="\n";
											for(int i = 0 ; i < tax_nm.size(); i++){
												field_5+=drcr_tax_perc.elementAt(i);
												if( i  != (tax_nm.size()-1)){
													field_5+="\n";
												} 
											} 
											
											field_5+="\n\n";
											field_5+="\n";
											
											for(int i = 0 ; i < tax_nm.size(); i++){
												field_5+=diff_tax_perc.elementAt(i);
												if( i  != (tax_nm.size()-1)){
													field_5+="\n";
												}
											} 
											field_5+="\n\n";
										}else{
											field_5+="\n";
											for(int i = 0 ; i < tax_nm.size(); i++){ 
												field_5+=inv_tax_perc.elementAt(i);
												if( i  != (tax_nm.size()-1)){ 
													field_5+="\n";
												}
											} 
											field_5+="\n\n";
										} 
										field_5+="\n\n";
									
									/* end of field_5 */
										
									/* field_6 */
									
									field_6+="\n\n\n";
									if(drcr_criteria.contains("DIFF-LUMPSUM")) {
										field_6+="\n\n";
										field_6+="\n\n";
										field_6+="\n\n";
									}
									
									if(drcr_criteria.contains("DIFF-KM")) {
										field_6+="\n\n";
										field_6+="\n\n";
									}
									if(drcr_criteria.contains("DIFF-INRKM")) {
										field_6+="\n\n";
										field_6+="\n\n";
										field_6+="\n\n";
									}
									if(drcr_criteria.contains("DIFF-QTY")) {
										field_6+="\n\n";
										field_6+="\n\n";
									} 
									if(drcr_criteria.contains("DIFF-INRMMBTU")) {
										field_6+="\n\n";
										field_6+="\n\n";
										field_6+="\n\n";
									}
//										for total GROSS AMOUNT -->
									field_6+=nf7.format(Double.parseDouble(drcr_gross_amt_inr+""))+"\n\n";
									
									if(drcr_criteria.contains("DIFF-TAX")) {
										field_6+="\n";
										for(int i = 0 ; i < tax_nm.size(); i++){
											field_6+="";
											if( i  != (tax_nm.size()-1)){ 
												field_6+="\n";
											}
										}
										field_6+="\n\n";
										field_6+="\n";
										for(int i = 0 ; i < tax_nm.size(); i++){
											field_6+="";
											if( i  != (tax_nm.size()-1)){
												field_6+="\n";
											} 
										} 
										
										field_6+="\n\n";
										field_6+="\n";
										
										for(int i = 0 ; i < tax_nm.size(); i++){
											field_6+="";
											if( i  != (tax_nm.size()-1)){
												field_6+="\n";
											}
										} 
										field_6+="\n\n";
									}else{
										field_6+="\n";
										for(int i = 0 ; i < tax_nm.size(); i++){ 
											field_6+=drcr_tax_amt.elementAt(i);
											if( i  != (tax_nm.size()-1)){ 
												field_6+="\n";
											}
										} 
										field_6+="\n\n";
									} 
									field_6+=nf.format(drcr_tax_samt)+"\n\n";
								
								/* end of field_6 */
								
							
							 float[] BillingFieldsDetailsWidths_2 = {0.06f, 0.40f,0.10f, 0.15f, 0.15f, 0.15f};
				                PdfPTable BillingFieldsDetailsTable_2 = new PdfPTable(BillingFieldsDetailsWidths_2);
				                BillingFieldsDetailsTable_2.setWidthPercentage(100);
				                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_1,small_black_normal)));
				                
				                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_2,small_black_normal)));
				                
				                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_3,small_black_normal)));
				                
				                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_4,small_black_normal)));
				                
				                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_5,small_black_normal)));
				                
				                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_6,small_black_normal)));
				                
//				                for net amount
				                
				                float[] BillingFieldsDetailsWidths_3 = {0.06f, 0.40f,0.10f, 0.15f, 0.15f, 0.15f};
				                PdfPTable BillingFieldsDetailsTable_3 = new PdfPTable(BillingFieldsDetailsWidths_3);
				                BillingFieldsDetailsTable_3.setWidthPercentage(100);
				                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(""+(++sr_no)+" ",small_black_bold)));
				                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
				                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				    				BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(" Net Amount Payable",small_black_bold)));
				                
				    			/*BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(" ",small_black_bold)));
				                */
				                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk("Rupees",small_black_bold)));
				                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(" ",small_black_bold)));
				                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(" ",small_black_bold)));
				                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
				                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                
				                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(nf7.format(Double.parseDouble(drcr_net_amt_inr+""))+" ",small_black_bold)));
				                
				                PdfPTable RemarkTable = new PdfPTable(1);
					            RemarkTable.setWidthPercentage(100);
					            RemarkTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					            RemarkTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					            RemarkTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
					            RemarkTable.addCell(new Phrase(new Chunk(remark_1,small_black_normal)));
					            
					            PdfPTable RemarkTable2 = new PdfPTable(1);
					            RemarkTable.setWidthPercentage(100);
					            RemarkTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					            RemarkTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
					            RemarkTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
					            RemarkTable.addCell(new Phrase(new Chunk(remark_2,small_black_normal)));
				                
					            PdfPTable SignatureInfoTable = new PdfPTable(1);
								SignatureInfoTable.setWidthPercentage(100);
								SignatureInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
								SignatureInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
								SignatureInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
								//RG20190325 For Company change*/SignatureInfoTable.addCell(new Phrase(new Chunk("For Hazira LNG Private Limited\n\n\n\n\nAuthorised Signatory",black_bold)));
								SignatureInfoTable.addCell(new Phrase(new Chunk("For Shell Energy India Private Limited\n\n\n\n\nAuthorised Signatory",black_bold)));
								                       
					            
								PdfPTable GenerationInfoTable = new PdfPTable(1);
					            GenerationInfoTable.setWidthPercentage(100);
					            GenerationInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					            GenerationInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
					            GenerationInfoTable.addCell(new Phrase(new Chunk("DLNG",small_black_normal)));
					            
					            PdfPTable GenerationInfoTable2 = new PdfPTable(1);
					            GenerationInfoTable2.setWidthPercentage(100);
					            GenerationInfoTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
					            GenerationInfoTable2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
					            GenerationInfoTable2.addCell(new Phrase(new Chunk("This is computer generated INVOICE and hence does not require Authorised Signatory.",small_black_normal)));
					            
		            document.add(hlpl_logo_table1);
	    			document.add(InvoiceTitleTable);
	    			document.add(InvoiceDescTable);
	    			document.add(InvoiceDescTable2);
	    			if(contract_type.equalsIgnoreCase("V"))
	    			{
	    				document.add(InvoiceNoteTable);
	    			}
	    			
	    			document.add(new Paragraph("              "));
	    			document.add(contact_addr_table);                        
	                document.add(GstCstInfoTable);            
	                document.add(new Paragraph("              "));
	                
	                if(!irn_no.equals("") && (!qr_code.equals(""))){
	                	document.add(InvoiceDateInfoTable_qr);
	                }else{
	    	            document.add(InvoiceDateInfoTable);
	    	            document.add(InvoiceDueDateInfoTable);
	    	            document.add(InvoiceNOInfoTable);
	                }
	                document.add(new Paragraph("              "));
	                document.add(BillingFieldsInfoTable);
	                document.add(BillingFieldsDetailsTable_2);
	                document.add(BillingFieldsDetailsTable_3);
	                document.add(new Paragraph("              "));
	                document.add(RemarkTable);
	                document.add(new Paragraph("              "));
	                document.add(RemarkTable2);
	                document.add(new Paragraph("              "));
	                document.add(SignatureInfoTable);      
	                
//	                System.out.println("drcr_criteria***"+drcr_criteria);
	                if(drcr_criteria.contains("DIFF-LUMPSUM")) {
	                	
	                    // ********* Following Coding is for Showing Attachment-1 Sheet on the Next (2nd Page) of the Same PDF Document *********** //
		                document.setPageSize(pageSize1);
		                document.newPage();
		          //      document.open();
		                very_small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 5, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                Font small_black_normal_new = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                Font small_black_bold2 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                Font small_black_bold2_new = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                big_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                black_bold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                if(invoice_title.contains("CREDIT") ) //HS20160615-----font
		    			{
		                	very_small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                	small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                	small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                	black_bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                	big_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 15, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                	 small_black_normal_new = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                	 small_black_bold2 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                	 small_black_bold2_new = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new Color(0x00, 0x00, 0x00));
		    			}		  
		    			url_start = "http://"+server_nm+":"+server_port+context_nm;
		    						  
		                hlpl_logo.setBorder(Rectangle.NO_BORDER);
		                //hlpl_logo.scaleAbsolute(75,75);
		                hlpl_logo.scaleAbsolute(48,45);
		                PdfPCell hlpl_logo_cell = new PdfPCell(hlpl_logo,false);
		                hlpl_logo_cell.setBorder(Rectangle.NO_BORDER);
		    			
		                
		               // float[] hlpl_logo_Widths = {0.45f, 0.51f, 0.08f};
		                float[] hlpl_logo_Widths = {0.45f};
		                PdfPTable hlpl_logo_table = new PdfPTable(hlpl_logo_Widths);
		                hlpl_logo_table.setWidthPercentage(100);
		                hlpl_logo_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                hlpl_logo_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                hlpl_logo_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                hlpl_logo_table.addCell(hlpl_logo_cell);
		                
		    			addr_supl = "Registered Office:";
		    			Paragraph pp1=new Paragraph();
		    			pp1.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
		                
		                if(!contact_Suppl_Name.trim().equals(""))
		                {
		                	//addr_supl += "\n"+contact_Suppl_Name;    
		                	pp1.add(new Phrase(new Chunk("\n"+contact_Suppl_Name,small_black_normal)));
		                }
		                if(!tempcompname.trim().equals(""))
		                {
		                	//addr_supl += "\n"+contact_Suppl_Name;    
		                	pp1.add(new Phrase(new Chunk("\n"+tempcompname,small_black_normal2)));
		                }
		                
//		                if(!contact_Suppl_Name.trim().equals(""))
//		                {
//		                	addr_supl += "\n"+contact_Suppl_Name;     	
//		                }
		                if(!contact_Suppl_Person_Address.trim().equals(""))
		                {
		                	//addr_supl += "\n"+contact_Suppl_Person_Address;
		                	addr_supl = "\n"+contact_Suppl_Person_Address;
		                }
		                if(!contact_Suppl_Person_City.trim().equals(""))
		                {
		                	addr_supl += "\n"+contact_Suppl_Person_City;     	
		                }
		                if(!contact_Suppl_Person_Pin.trim().equals(""))
		                {
		                	addr_supl += " "+"-"+" "+contact_Suppl_Person_Pin;     	
		                }
		                pp1.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
		    			
		    			addr_customer = "";
		                
		    			if(contact_Person_Name_And_Designation.trim().equalsIgnoreCase(contact_Customer_Name.trim()))
		    			{
		    				if(!contact_Customer_Name.trim().equals(""))
		    	            {
		    	            	addr_customer += contact_Customer_Name;    	
		    	            }
		    			}
		    			else
		    			{
		    	            if(!contact_Person_Name_And_Designation.trim().equals(""))
		    	            {
		    	            	addr_customer += contact_Person_Name_And_Designation;     	
		    	            }
		    	            if(!contact_Customer_Name.trim().equals(""))
		    	            {
		    	            	addr_customer += "\n"+contact_Customer_Name;     	
		    	            }
		    			}
		                if(!contact_Customer_Person_Address.trim().equals(""))
		                {
		                	addr_customer += "\n"+contact_Customer_Person_Address;     	
		                }
		                if(!contact_Customer_Person_City.trim().equals(""))
		                {
		                	addr_customer += "\n"+contact_Customer_Person_City;     	
		                }
		                if(!contact_Customer_Person_Pin.trim().equals(""))
		                {
		                	addr_customer += " "+"-"+" "+contact_Customer_Person_Pin;     	
		                }
		                            
		                          
		                float[] ContactAddrWidths2 = {0.25f, 0.55f, 0.20f};
		                contact_addr_table = new PdfPTable(ContactAddrWidths2);
		                contact_addr_table.setWidthPercentage(100);
		                
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk(contact_Suppl_Name,black_bold)));
		                
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk(" ",black_bold)));
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk("To:",black_bold)));
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                //contact_addr_table.addCell(new Phrase(new Chunk(addr_supl,small_black_normal)));
		                contact_addr_table.addCell(pp1);
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk(addr_customer,small_black_normal)));
		                String title_note="ATTACHMENT 1 - Applicable Lumpsum";
		                
		                
		                PdfPTable title_note_table = new PdfPTable(1);
		                title_note_table.setWidthPercentage(100);
		                title_note_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                title_note_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                title_note_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                title_note_table.addCell(new Phrase(new Chunk(title_note,big_black_bold)));
		                
		            
		                String rate_unit = "";
		                if(View_calc_bs.contains("1")) {
							rate_unit = "(INR/MMBtu)";
						}else if(View_calc_bs.contains("2")){ 
							rate_unit = "(INR/KM)";
						}else if(View_calc_bs.contains("3")){
							rate_unit = "(INR/TRUCK)";
						}
		                
		                float[] attach_dtl_table_width = {0.05f,0.10f,0.10f,0.10f,0.10f,0.20f,0.20f,0.10f};
		                PdfPTable attach_dtl_table = new PdfPTable(attach_dtl_table_width);
		                attach_dtl_table.setWidthPercentage(100);
		                
		                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
		                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.addCell(new Phrase(new Chunk("Sr. No.",small_black_normal)));
		                
		                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
		                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.addCell(new Phrase(new Chunk("Supply Date",small_black_normal)));
		                
		                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
		                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.addCell(new Phrase(new Chunk("Truck Number",small_black_normal)));
		                
		                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
		                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.addCell(new Phrase(new Chunk("Truck Quantity (MMBtu)",small_black_normal)));
		                
		                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
		                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.addCell(new Phrase(new Chunk("Rate \n"+rate_unit,small_black_normal)));
		                
		                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
		                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.addCell(new Phrase(new Chunk(drcr+" Rate",small_black_normal)));
		                
		                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
		                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.addCell(new Phrase(new Chunk("Diff. "+drcr+" Rate",small_black_normal)));
		                
		                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
		                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                attach_dtl_table.addCell(new Phrase(new Chunk("Amount (INR)",small_black_normal)));
		                
//		                System.out.println("View_truck_inv_no**"+View_truck_inv_no.size());
		                for(int i = 0 ; i < View_truck_inv_no.size() ; i++){
		                	int srNo = i+1 ;
		                	String amt = "" ;
		                	String diff_qty = "";
		                	if(View_calc_bs.elementAt(i).toString().equalsIgnoreCase("3")){ 
		                		amt = View_amount.elementAt(i)+"";
		                		diff_qty = nf7.format(Double.parseDouble(View_diff_qty.elementAt(i)+""));
							}else{ 
								amt = View_rate.elementAt(i)+"";
								diff_qty = nf7.format(Double.parseDouble(View_amount.elementAt(i)+""));
							} 
		                	
		                	
		                	attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
			                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.addCell(new Phrase(new Chunk(srNo+"",small_black_normal)));
			                
			                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
			                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.addCell(new Phrase(new Chunk(View_truck_inv_dt.elementAt(i)+"",small_black_normal)));
			                
			                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
			                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.addCell(new Phrase(new Chunk(View_truck_nm.elementAt(i)+"",small_black_normal)));
			                
			                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
			                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.addCell(new Phrase(new Chunk(View_invoice_qty.elementAt(i)+"",small_black_normal)));
			                
			                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
			                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.addCell(new Phrase(new Chunk(amt,small_black_normal)));
			                
			                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
			                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.addCell(new Phrase(new Chunk(nf.format(Double.parseDouble(View_dr_cr_qty.elementAt(i)+"")),small_black_normal)));
			                
			                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
			                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.addCell(new Phrase(new Chunk(nf.format(Double.parseDouble(View_diff_qty.elementAt(i)+"")),small_black_normal)));
			                
			                attach_dtl_table.getDefaultCell().setBorder(Rectangle.BOX);
			                attach_dtl_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                attach_dtl_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
			                attach_dtl_table.addCell(new Phrase(new Chunk(diff_qty,small_black_normal)));
		                	
		                }
		                
		                document.add(new Paragraph("              "));
						//document.add(hlpl_logo_table);//RG20190516
						document.add(new Paragraph("              "));
						document.add(contact_addr_table);                        
			            document.add(new Paragraph("              "));
			            document.add(title_note_table);
			            document.add(new Paragraph("              "));
			            document.add(InvoiceDateInfoTable);
			            document.add(InvoiceDueDateInfoTable);
			            document.add(InvoiceNOInfoTable);
			            document.add(new Paragraph("              "));
			            document.add(new Paragraph("              "));
			            document.add(attach_dtl_table);
			            
			            document.add(new Paragraph("              "));
			            document.add(new Paragraph("              "));
	                }
		            
				}catch(DocumentException de)
				{
					System.err.println("DocumentException in printAllPdfFileForInvoice() Method :\n"+de.getMessage());
					de.printStackTrace();
				}
				catch(IOException ioe)
				{
					System.err.println("IOException in printAllPdfFileForInvoice() Method :\n"+ioe.getMessage());
					ioe.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}
				finally
				{
					document.close();
				}
		}
		public String createPdfFileForInvoice_SignPDF()
		{
			try
			{
				HttpSession sess = request.getSession();
				if(invoice_title.equalsIgnoreCase("CREDITO") || invoice_title.equalsIgnoreCase("CREDITD") || invoice_title.equalsIgnoreCase("CREDITT") || invoice_title.equalsIgnoreCase("SupO") || invoice_title.equalsIgnoreCase("SupD") || invoice_title.equalsIgnoreCase("DEBITO") || invoice_title.equalsIgnoreCase("DEBITD")  || invoice_title.equalsIgnoreCase("DEBITT")){
					invoice_pdf_path = request.getRealPath("/unsigned_pdf/credit_debit_invoice");
				}else{
					if(contract_type.equalsIgnoreCase("C") || contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T")){
						invoice_pdf_path = request.getRealPath("/unsigned_pdf/ltcora_invoice");
					}else if(contract_type.equalsIgnoreCase("S")){
						invoice_pdf_path = request.getRealPath("/unsigned_pdf/sales_invoice");
					}else if(contract_type.equalsIgnoreCase("L")){
						invoice_pdf_path = request.getRealPath("/unsigned_pdf/loa_invoice");
					}else if(contract_type.equalsIgnoreCase("V")){
						invoice_pdf_path = request.getRealPath("/unsigned_pdf/service_invoice");
					}
				}
				////System.out.println(""+);
				String inv_title="";
				////System.out.println("invoice_title---------"+invoice_title);
				if(invoice_title.equalsIgnoreCase("ORIGINAL"))
				{
					inv_title="O";
				}
				else if(invoice_title.equalsIgnoreCase("DUPLICATE"))
				{
					inv_title="D";
				}
				else if(invoice_title.equalsIgnoreCase("TRIPLICATE"))
				{
					inv_title="T";
				}
				else if(invoice_title.equalsIgnoreCase("QUADRICATE"))
				{
					inv_title="Q";
				}
				else if(invoice_title.equalsIgnoreCase("CREDITO") )
				{
					inv_title="C";
				}
				//else if(invoice_title.equalsIgnoreCase("Sup") )
				else if(invoice_title.equalsIgnoreCase("SupO") )
				{
					inv_title="S";
				}
				//else if(invoice_title.equalsIgnoreCase("DEBIT") )
				else if(invoice_title.equalsIgnoreCase("DEBITO") )
				{
					inv_title="d";
				}
				else if(invoice_title.equalsIgnoreCase("CREDITD") )
				{
					inv_title="1";
				}
				else if(invoice_title.equalsIgnoreCase("CREDITT") )
				{
					inv_title="2";
				}
				else if(invoice_title.equalsIgnoreCase("SupD") )
				{
					inv_title="1";
				}
				else if(invoice_title.equalsIgnoreCase("DEBITD") )
				{
					inv_title="1";
				}
				else if(invoice_title.equalsIgnoreCase("DEBITT") )
				{
					inv_title="2";
				}
				String curr_date="";
				String q="select to_char(invoice_dt,'dd/mm/yyyy') from DLNG_INVOICE_MST where contract_type='"+contract_type+"' " +
				"and financial_year='"+inv_financial_year+"' and hlpl_inv_seq_no='"+hlpl_inv_seq_no+"' AND FLAG!='A'";
//				System.out.println("q******"+q);
				rset=stmt.executeQuery(q);
				if(rset.next())
				{
					curr_date=rset.getString(1);
				}
				String tempD1[]=invoice_bench_date.split("/");
				String d1=tempD1[2]+tempD1[1]+tempD1[0];
				String tempD2[]=curr_date.split("/");
				String d2=tempD2[2]+tempD2[1]+tempD2[0];
				
					//fileName = "INVOICE-"+invoice_date.trim().substring(0,2)+invoice_date.trim().substring(3,5)+invoice_date.trim().substring(6)+"-"+customer_abbr+"-"+customer_plant_nm+"-"+contract_type+"-"+hlpl_inv_seq_no+"-"+inv_title+"-"+time_temp+".pdf";//HS20160603
				if(invoice_title.equalsIgnoreCase("CREDITO") || invoice_title.equalsIgnoreCase("CREDITD") || invoice_title.equalsIgnoreCase("CREDITT")){
					fileName = "CREDIT_INVOICE-"+invoice_date.trim().substring(0,2)+invoice_date.trim().substring(3,5)+invoice_date.trim().substring(6)+"-"+customer_abbr+"-"+customer_plant_nm+"-"+contract_type+"-"+hlpl_inv_seq_no+"-"+inv_title+".pdf";
				}else if( invoice_title.equalsIgnoreCase("SupO") || invoice_title.equalsIgnoreCase("SupD") || invoice_title.equalsIgnoreCase("DEBITO") || invoice_title.equalsIgnoreCase("DEBITD") || invoice_title.equalsIgnoreCase("DEBITT")){
					fileName = "DEBIT_INVOICE-"+invoice_date.trim().substring(0,2)+invoice_date.trim().substring(3,5)+invoice_date.trim().substring(6)+"-"+customer_abbr+"-"+customer_plant_nm+"-"+contract_type+"-"+hlpl_inv_seq_no+"-"+inv_title+".pdf";
				}
				else{
					if(contract_type.equalsIgnoreCase("C") || contract_type.equalsIgnoreCase("R") || contract_type.equalsIgnoreCase("T")){
						fileName = "LTCORA_INVOICE-"+invoice_date.trim().substring(0,2)+invoice_date.trim().substring(3,5)+invoice_date.trim().substring(6)+"-"+customer_abbr+"-"+customer_plant_nm+"-"+contract_type+"-"+hlpl_inv_seq_no+"-"+inv_title+".pdf";
					}else if(contract_type.equalsIgnoreCase("S")){
						fileName = "SALES_INVOICE-"+invoice_date.trim().substring(0,2)+invoice_date.trim().substring(3,5)+invoice_date.trim().substring(6)+"-"+customer_abbr+"-"+customer_plant_nm+"-"+contract_type+"-"+hlpl_inv_seq_no+"-"+inv_title+".pdf";
					}else if(contract_type.equalsIgnoreCase("L")){
						fileName = "LOA_INVOICE-"+invoice_date.trim().substring(0,2)+invoice_date.trim().substring(3,5)+invoice_date.trim().substring(6)+"-"+customer_abbr+"-"+customer_plant_nm+"-"+contract_type+"-"+hlpl_inv_seq_no+"-"+inv_title+".pdf";
					}else if(contract_type.equalsIgnoreCase("V")){
						fileName = "SERVICE_INVOICE-"+invoice_date.trim().substring(0,2)+invoice_date.trim().substring(3,5)+invoice_date.trim().substring(6)+"-"+customer_abbr+"-"+customer_plant_nm+"-"+contract_type+"-"+hlpl_inv_seq_no+"-"+inv_title+".pdf";
					}
				}
					f_nm="INVOICE-"+invoice_date.trim().substring(0,2)+invoice_date.trim().substring(3,5)+invoice_date.trim().substring(6)+"-"+customer_abbr+"-"+customer_plant_nm+"-"+contract_type+"-"+hlpl_inv_seq_no;
					inv_type_pdf=inv_title;
					invoice_pdf_path = invoice_pdf_path+"//"+fileName;
					System.out.println("--Signed invoice_pdf_path---"+invoice_pdf_path);
			}
			catch(Exception e)
			{
				//System.out.println("Exception in createPdfFileForInvoice() Method :\n"+e.getMessage());
				e.printStackTrace();
			}
			return invoice_pdf_path;
		
		}
		private void printPdfFileForInvoice_SignPDF()throws Exception {
			
			Rectangle pageSize = new Rectangle(595, 842);
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
			 Document document = new Document(pageSize);
			 try
				{
					PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(createPdfFileForInvoice_SignPDF()));
					document.addTitle("Invoice Details");
					document.addSubject("Invoice Details For Customer");
		            document.addKeywords("iText, Invoice Details, Step 2, metadata");
		            document.addCreator("Invoice Details Generation using iText");
		            document.addAuthor("FMS8@BIPL");
		            document.open();
					document.setPageSize(pageSize);
		            document.newPage();

		            Font very_small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		            Font small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		            Font small_black_normal2 = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		            Font small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
		            Font black_bold = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new Color(0x00, 0x00, 0x00));
		            Font big_black_bold_2 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
		            Font big_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new Color(0x00, 0x00, 0x00));
		            
		            String context_nm = request.getContextPath();
					String server_nm = request.getServerName();
					String server_port = ""+request.getServerPort();
					  
					url_start = "http://"+server_nm+":"+server_port+context_nm;
					
		           // Image hlpl_logo = Image.getInstance(url_start+"//images//LOGO//HLPL-Symbol.jpg");
					 Image hlpl_logo = Image.getInstance(url_start+"/images/logo/company_Logo.png");
					 /*RG20191128 added for adding logo for sign pdf*/
					 	hlpl_logo.setBorder(Rectangle.NO_BORDER);
			            hlpl_logo.scaleAbsolute(48,45);
			            PdfPCell hlpl_logo_cell1 = new PdfPCell(hlpl_logo,false);
			            hlpl_logo_cell1.setBorder(Rectangle.NO_BORDER);
			            float[] hlpl_logo_Widths1 = {0.45f};
			            PdfPTable hlpl_logo_table1 = new PdfPTable(hlpl_logo_Widths1);
			            hlpl_logo_table1.setWidthPercentage(100);
			            hlpl_logo_table1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            hlpl_logo_table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			            hlpl_logo_table1.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
			            hlpl_logo_table1.addCell(hlpl_logo_cell1);
					  /* ended adding logo*/ 
					           
					String inv_nm = "TAX INVOICE\nTAX Invoice issued under Rule 46 of the Central Goods and Services Tax Rules, 2017";
					String inv_desc = "Shell Energy India Private Limited";
					String inv_note = "In respect of Transport Management Service Agreement ("+trans_cont_no+") executed on "+signing_dt+" \nbetween "+contact_Suppl_Name+" and "+contact_Customer_Name;
					
					String addr_supl = "Registered Office:";
					Paragraph pp=new Paragraph();
					pp.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
		            
		            if(!contact_Suppl_Name.trim().equals(""))
		            {
		            	//addr_supl += "\n"+contact_Suppl_Name;    
		            	pp.add(new Phrase(new Chunk("\n"+contact_Suppl_Name,small_black_normal)));
		            }
		           /* if(!tempcompname.trim().equals(""))
		            {
		            	//addr_supl += "\n"+contact_Suppl_Name;    
		            	pp.add(new Phrase(new Chunk("\n"+tempcompname,small_black_normal2)));
//		            	hiren
		            }*/
		            
		            if(!contact_Suppl_Person_Address.trim().equals(""))
		            {
		            	//addr_supl += "\n"+contact_Suppl_Person_Address;
		            	addr_supl = "\n"+contact_Suppl_Person_Address;
		            }
		            if(!contact_Suppl_Person_City.trim().equals(""))
		            {
		            	addr_supl += "\n"+contact_Suppl_Person_City;     	
		            }
		            if(!contact_Suppl_Person_Pin.trim().equals(""))
		            {
		            	addr_supl += " "+"-"+" "+contact_Suppl_Person_Pin;     	
		            }
		            pp.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
					
					String addr_customer = "";
		            
					if(contact_Person_Name_And_Designation.trim().equalsIgnoreCase(contact_Customer_Name.trim()))
					{
						if(!contact_Customer_Name.trim().equals(""))
			            {
			            	addr_customer += contact_Customer_Name;    	
			            }
					}
					else
					{
			            if(!contact_Person_Name_And_Designation.trim().equals(""))
			            {
			            	addr_customer += contact_Person_Name_And_Designation;     	
			            }
			            if(!contact_Customer_Name.trim().equals(""))
			            {
			            	addr_customer += "\n"+contact_Customer_Name;     	
			            }
					}
		            if(!contact_Customer_Person_Address.trim().equals(""))
		            {
		            	addr_customer += "\n"+contact_Customer_Person_Address;     	
		            }
		            if(!contact_Customer_Person_City.trim().equals(""))
		            {
		            	addr_customer += "\n"+contact_Customer_Person_City;     	
		            }
		            if(!contact_Customer_Person_Pin.trim().equals(""))
		            {
		            	addr_customer += " "+"-"+" "+contact_Customer_Person_Pin;     	
		            }
		            
		            PdfPTable InvoiceTitleTable = new PdfPTable(1);
		            InvoiceTitleTable.setWidthPercentage(100);
		            InvoiceTitleTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            InvoiceTitleTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		          //SB20160601  InvoiceTitleTable.addCell(new Phrase(new Chunk(invoice_title,black_bold)));
		            
		            if(invoice_title.contains("CREDIT") ||invoice_title.contains("Sup")|| invoice_title.contains("DEBIT")  ) //SB20160601
		            {
		            	String inv_tit = invoice_title.substring(invoice_title.length()-1,invoice_title.length());
//		            	System.out.println("inv_tit******"+inv_tit);
		            	if(inv_tit.equals("O")) {
		            		inv_tit = "ORIGINAL FOR RECIPIENT";
		            	} else if (inv_tit.equals("D")){
		            		inv_tit = "DUPLICATE FOR RECIPIENT";
		            	}else {
		            		inv_tit = "TRIPLICATE";
		            	}
		            	
		            	
		            	InvoiceTitleTable.addCell(new Phrase(new Chunk(inv_tit,black_bold)));
		            }
		            else
		            {
		            	String inv_tit = invoice_title;
		            	if(contract_type.equals("V")) {
			            	if(invoice_title.equalsIgnoreCase("ORIGINAL")) {
			            		inv_tit += " FOR RECIPIENT"; 
			                } else if(invoice_title.equalsIgnoreCase("DUPLICATE")) {
			                	inv_tit += " FOR SUPPLIER";
			                } 
		            	}
		            	InvoiceTitleTable.addCell(new Phrase(new Chunk(inv_tit,black_bold)));
		            }
		            
		            PdfPTable InvoiceDescTable = new PdfPTable(1);
		            InvoiceDescTable.setWidthPercentage(100);
		            InvoiceDescTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            InvoiceDescTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            InvoiceDescTable.addCell(new Phrase(new Chunk(inv_desc,big_black_bold)));
					
		            PdfPTable InvoiceDescTable2 = new PdfPTable(1);
		            InvoiceDescTable2.setWidthPercentage(100);
		            InvoiceDescTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            InvoiceDescTable2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            if(invoice_title.contains("CREDIT") ) {
		            	if(contract_type.equals("S")  || contract_type.equals("L")){
		            		InvoiceDescTable2.addCell(new Phrase(new Chunk("CREDIT NOTE",big_black_bold_2)));
		            	}else{
		            		InvoiceDescTable2.addCell(new Phrase(new Chunk("CREDIT NOTE\nCredit Note issued under Rule 53 of the Central Goods and Services Tax Rules, 2017",big_black_bold_2)));
		            	}
		            }else if(invoice_title.contains("Sup")){
		            		InvoiceDescTable2.addCell(new Phrase(new Chunk("DEBIT NOTE\nDebit Note issued under Rule 53 of the Central Goods and Services Tax Rules, 2017",big_black_bold_2)));
		            }else if(invoice_title.contains("DEBIT")){
		            	//	InvoiceDescTable2.addCell(new Phrase(new Chunk("DEBIT NOTE\nDebit Note issued under Rule 53 of the Central Goods and Services Tax Rules, 2017",big_black_bold_2)));
		            		InvoiceDescTable2.addCell(new Phrase(new Chunk("DEBIT NOTE",big_black_bold_2)));
		            }else{
		            	InvoiceDescTable2.addCell(new Phrase(new Chunk(inv_nm,big_black_bold_2)));
		            }
		            
		            PdfPTable InvoiceNoteTable = new PdfPTable(1);
		            InvoiceNoteTable.setWidthPercentage(100);
		            InvoiceNoteTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            InvoiceNoteTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            InvoiceNoteTable.addCell(new Phrase(new Chunk(inv_note,small_black_normal)));
					
		            float[] ContactAddrWidths = {0.50f, 0.10f, 0.40f};
		            PdfPTable contact_addr_table = new PdfPTable(ContactAddrWidths);
		            contact_addr_table.setWidthPercentage(100);
		            contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		         //   contact_addr_table.addCell(new Phrase(new Chunk(addr_supl,small_black_normal)));
		            contact_addr_table.addCell(pp);
		            contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            contact_addr_table.addCell(new Phrase(new Chunk("To:",small_black_bold)));
		            contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            contact_addr_table.addCell(new Phrase(new Chunk(addr_customer,small_black_normal)));
					
		            String supl_gst_cst_info = "";
		            String customer_gst_cst_info = "";
		            
		          //  //System.out.println("-----INSIDE PRINTALL 2---"+vSTAT_CD.size());
		            
		            if(contract_type.equalsIgnoreCase("V")){

		            	if(!contact_Suppl_State.trim().equals("")) {
		            		supl_gst_cst_info = "State : "+contact_Suppl_State;
		            	}
		            	if(!contact_Suppl_State_Code.trim().equals("")) {
		            		supl_gst_cst_info += "\nState Code : "+contact_Suppl_State_Code;
		            	}
		            	if(!contact_Suppl_GSTIN_NO.trim().equals(""))
		            	{
		            		supl_gst_cst_info += "\nGSTIN No. : "+contact_Suppl_GSTIN_NO;
		            		if(!contact_Suppl_GST_DT.equals("")) {
		            			supl_gst_cst_info+=" DT. "+contact_Suppl_GST_DT;
		            		}
		            	}
		            	if(!contact_Suppl_PAN_NO.trim().equals(""))
		            	{
		            		supl_gst_cst_info += " \nPAN No.: "+contact_Suppl_PAN_NO+" ";
		            	}
		            	if(!sac_code.trim().equals(""))
		            	{
		            		supl_gst_cst_info += " \nSAC No. : "+sac_code+" ";
		            	}
		            	if(!sac_desc.trim().equals(""))
		            	{
		            		supl_gst_cst_info += " \nDescription of Service : "+sac_desc+" ";
		            	}
		            	if(!contact_customer_State.trim().equals(""))
		            	{
		            		supl_gst_cst_info += " \nPlace of Supply : "+contact_customer_State+" ";
		            	}
		            	
		    		}
		            
		            if(!contact_customer_State.trim().equals(""))
	            	{
		            	customer_gst_cst_info = "State : "+contact_customer_State+" ";
	            	}
		            if(!contact_customer_State_Code.trim().equals(""))
	            	{
		            	customer_gst_cst_info += "\nState Code : "+contact_customer_State_Code+" ";
	            	}
		            
		            if(vSTAT_CD.size()>0)
		    		{	
		    			if(contract_type.equalsIgnoreCase("V"))
		    			{
		    				for(int i=0; i<vSTAT_CD.size(); i++)
							{
	    						if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) 
	    						{
	    							customer_gst_cst_info += "\nPAN No. : "+vSTAT_NO.elementAt(i)+" ";
	    						}
	    						else
	    						{
	    							if(vSTAT_NM.elementAt(i).toString().contains("GSTIN")) 
		    						{
	    								customer_gst_cst_info += "\nGSTIN No. : "+vSTAT_NO.elementAt(i);
	    								if(!vSTAT_EFF_DT.elementAt(i).equals("")) {
	    									customer_gst_cst_info+=" DT. "+vSTAT_EFF_DT.elementAt(i);
	    			            		}
		    						}
	    						}
							}
		    			}
		    		}
		            else
		            {/*
		            	if(contract_type.equalsIgnoreCase("V"))
		        		{
		            		if(!contact_Customer_GST_NO.trim().equals(""))
		                	{
		                		customer_gst_cst_info = "GST TIN No. : "+contact_Customer_GST_NO+" DT. "+contact_Customer_GST_DT;
		                	}
		                	else
		                	{
		                		customer_gst_cst_info = "";
		                	}
		                	
		                	if(!contact_Customer_CST_NO.trim().equals(""))
		                	{
		                		customer_gst_cst_info += "\nCST TIN No. : "+contact_Customer_CST_NO+" DT. "+contact_Customer_CST_DT;
		                	}
		                	else
		                	{
		                		customer_gst_cst_info += "\n";
		                	}
		                	
		                	if(!contact_Customer_GVAT_NO.trim().equals(""))
		                	{
		                		customer_gst_cst_info += "\nGVAT TIN No. : "+contact_Customer_GVAT_NO+" DT. "+contact_Customer_GVAT_DT;
		                	}
		                	else
		                	{
		                		customer_gst_cst_info += "\n";
		                	}
		        		}
		            */}
		            
		            float[] GstCstInfoWidths = {0.50f, 0.10f, 0.40f};
		            PdfPTable GstCstInfoTable = new PdfPTable(GstCstInfoWidths);
		            GstCstInfoTable.setWidthPercentage(100);
		            GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            GstCstInfoTable.addCell(new Phrase(new Chunk("\n"+supl_gst_cst_info,small_black_normal)));
		            GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            GstCstInfoTable.addCell(new Phrase(new Chunk("\n ",small_black_normal)));
		            GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            GstCstInfoTable.addCell(new Phrase(new Chunk("\n"+customer_gst_cst_info,small_black_normal)));
		            
		            String invno = "";
		            
		            if(!new_inv_seq_no.equals("")) {
		            	invno = new_inv_seq_no;
		            } else {
		            	if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		            	{
		            		if(hlpl_inv_no.length()>13)
		            		{
		            			invno = hlpl_inv_no.substring(0,10)+""+hlpl_inv_no.substring(12);
		            		}      		
		            	}
		            	else
		            	{
		            		if(hlpl_inv_no.length()>13)
		            		{
		            			invno = hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
		            		}       		
		            	}
		            }
		            
		            String inv_no_info = "";
					String invoiceType = "";
					String inv_dt_Header = "Invoice Date ";
					String Inv_Ref_Dtl = ""; //SB20160611  
					 
					String inv_due_dt_Header = "Payment Due Date ";
					if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT")) //SB20160530
					{             
				         Inv_Ref_Dtl = " as per Invoice ref "+invno+" dated "+customer_Invoice_DT;
				        if(invoice_title.contains("CREDIT")){    
						invoiceType = "Credit Note No ";
				        }else if(invoice_title.contains("Sup")){
				        	invoiceType = "Debit Note No ";
				        }else {
				        	invoiceType = "Debit Note No ";
				        }
				//SB*		inv_dt_Header = "Invoice  Ref No: "+invno;
						inv_dt_Header = "";
						//customer_Invoice_DT =invno+":"+customer_Invoice_DT;
						invno = hlpl_drcr_docNo;
						if(invoice_title.contains("CREDIT")){    
						inv_due_dt_Header = "Credit Note Date ";
						}else if(invoice_title.contains("Sup")){
							inv_due_dt_Header = "Debit Note Date ";
						}else {
							inv_due_dt_Header = "Debit Note Date ";
						}
						customer_Invoice_Due_DT =hlpl_drcr_dt;
					}
					else
					{
						invoiceType = "SEIPL TAX Invoice Seq No ";
					}
					
					if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT")){ //SB20160530
						if(invoice_title.contains("CREDIT")){
							//RG20190325 For Company change*/inv_no_info = "HLPL Credit Note No:";
							inv_no_info = "SEIPL Credit Note No ";
						}else if(invoice_title.contains("Sup")){
							//RG20190325 For Company change*/inv_no_info = "HLPL Debit Note No:"; 
							inv_no_info = "SEIPL Debit Note No "; 
						}else {
						//RG20190325 For Company change*/	inv_no_info = "HLPL Debit Note No:"; 
							inv_no_info = "SEIPL Debit Note No ";
						}
					}
					else{
						inv_no_info = "SEIPL TAX Invoice Seq No ";
					}
					
					PdfPTable InvoiceDateInfoTable; 
					PdfPTable InvoiceDueDateInfoTable;
					PdfPTable InvoiceNOInfoTable;
					PdfPTable BillingPeriodInfoTable;
					
					 float[] InvoiceDateInfoWidths_qr = {0.40f, 0.20f, 0.60f};
					 PdfPTable InvoiceDateInfoTable_qr = new PdfPTable(InvoiceDateInfoWidths_qr);
		           if(!irn_no.equals("") && (!qr_code.equals(""))){
		        	   
//						float[] InvoiceDateInfoWidths = {0.60f, 0.25f, 0.15f};
						float[] InvoiceDateInfoWidths = {0.20f, 0.45f, 0.25f};
			            InvoiceDateInfoTable = new PdfPTable(InvoiceDateInfoWidths);
			            InvoiceDateInfoTable.setWidthPercentage(100);
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			        //SB20160531    InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT")) //SB20160530
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_dt_Header,small_black_bold)));
			            else
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT"))//SB20160530
			            {
				            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
				            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
				            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            }
			            else
			            {
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk("Invoice Date ",small_black_bold)));
			            	InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_DT,small_black_bold)));
			            }
			            float[] InvoiceDueDateInfoWidths = {0.60f, 0.25f, 0.15f};
			            InvoiceDueDateInfoTable = new PdfPTable(InvoiceDueDateInfoWidths);
			            
			           /* InvoiceDueDateInfoTable.setWidthPercentage(100);
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(inv_due_dt_Header,small_black_bold)));
			                    
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            if(!second_due_dt.equals("") && (!invoice_title.contains("Sup")) && (!invoice_title.contains("DEBIT")) && (!invoice_title.contains("CREDIT"))){
			            	InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(second_due_dt,small_black_bold)));
			            }else{
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_Due_DT,small_black_bold)));
			            }*/
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
		                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
		                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_due_dt_Header,small_black_bold)));
		                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
		                if(!second_due_dt.equals("") && (!invoice_title.contains("Sup")) && (!invoice_title.contains("DEBIT")) && (!invoice_title.contains("CREDIT"))){
		                	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(second_due_dt,small_black_bold)));
		                }else{
		                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_Due_DT,small_black_bold)));
		                }
			            
			            if(invoice_title.contains("Sup") || invoice_title.contains("DEBIT")) {
			            	
			            	InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk("Payment Due Date ",small_black_bold)));
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(dr_cr_due_dt,small_black_bold)));
			              }
			                float[] InvoiceNOInfoWidths = {0.60f, 0.25f, 0.15f};
			                InvoiceNOInfoTable = new PdfPTable(InvoiceNOInfoWidths);
			                
			                /*InvoiceNOInfoTable.setWidthPercentage(100);
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(inv_no_info,small_black_bold)));
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(invno,small_black_bold)));*/
			                
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_no_info,small_black_bold)));
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(invno,small_black_bold)));            
			                
			                /*float[] BillingPeriodInfoWidths = {0.55f,0.15f, 0.10f, 0.15f};
			                BillingPeriodInfoTable = new PdfPTable(BillingPeriodInfoWidths);
			                BillingPeriodInfoTable.setWidthPercentage(100);
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk("For the Billing Period***",small_black_bold)));
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_Start_DT,small_black_bold)));
			                
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk("to",small_black_bold)));
			                
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_End_DT,small_black_bold)));
			           
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(3);
			                InvoiceDateInfoTable.addCell(BillingPeriodInfoTable);
			                
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(3);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk("",small_black_bold)));*/
			                
			                float[] BillingPeriodInfoWidths = {0.55f, 0.05f, 0.20f, 0.10f, 0.20f};
			                BillingPeriodInfoTable = new PdfPTable(BillingPeriodInfoWidths);
			                BillingPeriodInfoTable.setWidthPercentage(100);
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk("For the Billing Period ",small_black_bold)));
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(bill_period_start_dt,small_black_bold)));
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk("to",small_black_bold)));
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_End_DT,small_black_bold)));
			                
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(3);
			                InvoiceDateInfoTable.addCell(BillingPeriodInfoTable);
			                
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(3);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk("",small_black_bold)));
			                
			                //code for Irn & qrcode starts now
			                url_start = "http://"+server_nm+":"+server_port+context_nm;
			                    String qrCodeText = qr_code;
			                    String filePath =  request.getRealPath("/pdf_reports/xls_reports/qrcode");
			          		  filePath = filePath+"//"+invno.replace("/","_")+".png";
			          		  int size = 45;//125 original
			          		  String fileType = "png";
			          		  File qrFile = new File(filePath);
			          		  com.seipl.hazira.dlng.util.DataBean_QRCode_Generator.createQRImage(qrFile, qrCodeText, size, fileType);
			          		  System.out.println("DONE");
			          		  
			                    
			                  Image qr_codeimg = Image.getInstance(url_start+"//pdf_reports//xls_reports//qrcode//"+invno.replace("/","_")+".png");			  
			                  qr_codeimg.setBorder(Rectangle.NO_BORDER);
			                  qr_codeimg.setAlignment(Element.ALIGN_LEFT);
			                 // qr_codeimg.scaleAbsolute(75,75);
			                //  qr_codeimg.scaleAbsolute(50,50);
			                  PdfPCell qrcode_cell = new PdfPCell(qr_codeimg,false);
			                  qrcode_cell.setBorder(Rectangle.NO_BORDER);
			                  qrcode_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			                      
//			                    String irn_no=irn_no;
			                    String char_16= irn_no.substring(0,16);
			                    String char_32= irn_no.substring(16,32);
			                    String char_48= irn_no.substring(32,48);
			                    String char_64= irn_no.substring(48,irn_no.length());
			                    String final_irn_no=char_16+"\n"+char_32+"\n"+char_48+"\n"+char_64;
			                    
			                    PdfPTable InvoiceDateInfoTable_qr1 = new PdfPTable(1);
			        	        InvoiceDateInfoTable_qr1.setWidthPercentage(100);
//			        	        InvoiceDateInfoTable_qr1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//			        	        InvoiceDateInfoTable_qr1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//			        	        InvoiceDateInfoTable_qr1.addCell(new Phrase(new Chunk("QR Code",small_black_bold)));
			        	        InvoiceDateInfoTable_qr1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	        InvoiceDateInfoTable_qr1.getDefaultCell().setBorder(Rectangle.BOX);
			        	        InvoiceDateInfoTable_qr1.addCell(qrcode_cell);
			        	        
			        	        PdfPTable InvoiceDateInfoTable_qr11 = new PdfPTable(1);
			        	        InvoiceDateInfoTable_qr11.setWidthPercentage(100);
			        	        InvoiceDateInfoTable_qr11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	        InvoiceDateInfoTable_qr11.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			        	        InvoiceDateInfoTable_qr11.addCell(new Phrase(new Chunk("IRN no.",small_black_bold)));
			        	        InvoiceDateInfoTable_qr11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	        InvoiceDateInfoTable_qr11.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			        	        InvoiceDateInfoTable_qr11.addCell(new Phrase(new Chunk(final_irn_no,small_black_normal)));
			                    
			        			
			        	        InvoiceDateInfoTable_qr.setWidthPercentage(100);
			        	               
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			        	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable_qr1);
			        	        
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.BOX);
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable_qr11);
			        	        
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			        	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable);
		           }else{
			        	   
						float[] InvoiceDateInfoWidths = {0.60f, 0.25f, 0.15f};
			            InvoiceDateInfoTable = new PdfPTable(InvoiceDateInfoWidths);
			            InvoiceDateInfoTable.setWidthPercentage(100);
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			        //SB20160531    InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT")) //SB20160530
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_dt_Header,small_black_bold)));
			            else
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT"))//SB20160530
			            {
				            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
				            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
				            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            }
			            else
			            {
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk("Invoice Date ",small_black_bold)));
			            	InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_DT,small_black_bold)));
			            }
			            float[] InvoiceDueDateInfoWidths = {0.60f, 0.25f, 0.15f};
			            InvoiceDueDateInfoTable = new PdfPTable(InvoiceDueDateInfoWidths);
			            InvoiceDueDateInfoTable.setWidthPercentage(100);
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(inv_due_dt_Header,small_black_bold)));
			                    
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            if(!second_due_dt.equals("") && (!invoice_title.contains("Sup")) && (!invoice_title.contains("DEBIT")) && (!invoice_title.contains("CREDIT"))){
			            	InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(second_due_dt,small_black_bold)));
			            }else{
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_Due_DT,small_black_bold)));
			            }
			            
			            if(invoice_title.contains("Sup") || invoice_title.contains("DEBIT")) {
			            	
			            	InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDueDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDueDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk("Payment Due Date ",small_black_bold)));
			                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDueDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(dr_cr_due_dt,small_black_bold)));
			              }
			                float[] InvoiceNOInfoWidths = {0.60f, 0.25f, 0.15f};
			                InvoiceNOInfoTable = new PdfPTable(InvoiceNOInfoWidths);
			                InvoiceNOInfoTable.setWidthPercentage(100);
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(inv_no_info,small_black_bold)));
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(invno,small_black_bold)));
			                            
			                
			                float[] BillingPeriodInfoWidths = {0.55f,0.15f, 0.10f, 0.15f};
			                BillingPeriodInfoTable = new PdfPTable(BillingPeriodInfoWidths);
			                BillingPeriodInfoTable.setWidthPercentage(100);
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk("For the Billing Period ",small_black_bold)));
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_Start_DT,small_black_bold)));
			                
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk("to",small_black_bold)));
			                
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_End_DT,small_black_bold)));
			           }
					
					
					
					
		                float[] BillingFieldsInfoWidths = {0.06f, 0.40f,0.10f, 0.15f, 0.15f, 0.15f};
		                PdfPTable BillingFieldsInfoTable = new PdfPTable(BillingFieldsInfoWidths);
		                BillingFieldsInfoTable.setWidthPercentage(100);
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Sr. No.",small_black_bold)));
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Item",small_black_bold)));
		               
		               /* BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Attachment\nRef.",small_black_bold)));
		               */
		                String inr_base="",rateFlg="";
		                if(calcBase.equalsIgnoreCase("1")) {
		                	inr_base = "(MMBtu)";
		                	rateFlg = "(INR/MMBtu)";
		                }else if(calcBase.equalsIgnoreCase("2")){
		                	inr_base = "(KM)";
		                	rateFlg = "(INR/KM)";
		                }else if(calcBase.equalsIgnoreCase("3")) {
		                	inr_base = "(No.of Trucks)";
		                	rateFlg = "(INR)";
		                }
		                
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Currency",small_black_bold)));
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Quantity\n"+inr_base,small_black_bold)));
		                
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Rate\n"+rateFlg,small_black_bold)));
		                
		                
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Amount",small_black_bold)));
		                
		                
		                String field_1 = "";
		                String field_2 = "";
//		                String field_3 = "";
		                String field_4 = "";
		                String field_5 = "";
		                String field_6 = "";
		                String field_7 = "";
		                
		                /*for field 1*/
		                int sr_no = 0;
		                for (int j = 0; j < Vsac_cd.size() ; j++) {
		                	
		                	++sr_no;
		                	field_1+=sr_no+"\n\n";
		                	field_2+=Vitem_desc.elementAt(j)+"\n\n";
		                	field_4+="Rupees\n\n";
		                	if(calcBase.equals("3") && j == 0) {
		                		field_5+=View_truck_inv_no.size()+"\n\n";
		                		field_6+="Att1\n\n";
		                	}else {
		                		field_5+=Vqty.elementAt(j)+"\n\n";
		                		field_6+=Vrate.elementAt(j)+"\n\n";
		                	}
		                	
		                	field_7+=nf7.format(Double.parseDouble(Vamt.elementAt(j)+""))+"\n\n";
						}
		                
		                ++sr_no;
	                	field_1+=sr_no+"\n\n";
	                	field_2+="Gross Amount\n\n";
	                	field_4+="Rupees\n\n";
	                	field_5+="\n\n";
	                	field_6+="\n\n";
	                	field_7+=nf7.format(Double.parseDouble(grossAmt+""))+"\n\n";
	                	
	                	String taxArr[] = taxnm_str.split("@@");
		                for(int j = 0; j < tax_size.length ; j++) {
		                	
		                	++sr_no;
		                	field_1+=sr_no+"\n";
		                	field_2+=taxArr[j]+"\n";
		                	field_4+="Rupees\n";
		                	field_5+="\n";
		                	field_6+=tax_structure+" %\n";
		                	field_7+=nf7.format(Math.round(Double.parseDouble(grossAmt) * Double.parseDouble(tax_structure)/100))+"\n";
		                }
		                
	                	++sr_no;
	                	field_1+=sr_no+"\n\n";
	                	field_2+="Total GST\n\n";
	                	field_4+="Rupees\n\n";
	                	field_5+="\n\n";
	                	field_6+="\n\n";
	                	field_7+=nf7.format(Double.parseDouble(totalTax+""))+"\n\n";
	                	
	                	++sr_no;
	                	field_1+=sr_no+"\n\n";
	                	field_2+="Invoice Amount\n\n";
	                	field_4+="Rupees\n\n";
	                	field_5+="\n\n";
	                	field_6+="\n\n";
	                	field_7+=nf7.format(Double.parseDouble(netAmt+""))+"\n\n";
		                
		                float[] BillingFieldsDetailsWidths_2 = {0.06f, 0.40f,0.10f, 0.15f, 0.15f, 0.15f};
		                PdfPTable BillingFieldsDetailsTable_2 = new PdfPTable(BillingFieldsDetailsWidths_2);
		                BillingFieldsDetailsTable_2.setWidthPercentage(100);
		                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_1,small_black_normal)));
		                
		                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_2,small_black_normal)));
		                
		                /*BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                //BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_3,small_black_normal)));
		                //BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_3,small_black_normal).setAction(new PdfAction(new URL(invoice_att2_file_name)))));
		                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_3,small_black_bold)));
		                */
		                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_4,small_black_normal)));
		                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
		                
		                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_5,small_black_normal)));
		                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
		                
		                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_6,small_black_normal)));
		                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_7,small_black_normal)));
		                
		                float[] BillingFieldsDetailsWidths_3 = {0.06f, 0.40f,0.10f, 0.15f, 0.15f, 0.15f};
		                PdfPTable BillingFieldsDetailsTable_3 = new PdfPTable(BillingFieldsDetailsWidths_3);
		                BillingFieldsDetailsTable_3.setWidthPercentage(100);
		                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(""+(++sr_no)+" ",small_black_bold)));
		                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		    				BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(" Net Amount Payable",small_black_bold)));
		                
		    			/*BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                */
		                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk("Rupees",small_black_bold)));
		                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                
		                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(nf7.format(Double.parseDouble(netAmt+""))+" ",small_black_bold)));
		                
		                PdfPTable RemarkTable = new PdfPTable(1);
			            RemarkTable.setWidthPercentage(100);
			            RemarkTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            RemarkTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			            RemarkTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            RemarkTable.addCell(new Phrase(new Chunk(remark_1,small_black_normal)));
			            
			            PdfPTable RemarkTable2 = new PdfPTable(1);
			            RemarkTable.setWidthPercentage(100);
			            RemarkTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            RemarkTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			            RemarkTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            RemarkTable.addCell(new Phrase(new Chunk(remark_2,small_black_normal)));
		                
			            PdfPTable SignatureInfoTable = new PdfPTable(1);
						SignatureInfoTable.setWidthPercentage(100);
						SignatureInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
						SignatureInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
						SignatureInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
						// For Company change*/SignatureInfoTable.addCell(new Phrase(new Chunk("For Hazira LNG Private Limited\n\n\n\n\nAuthorised Signatory",black_bold)));
						SignatureInfoTable.addCell(new Phrase(new Chunk("For Shell Energy India Private Limited",black_bold)));
						                       
		//////////////////////////For Digital Signature/////////////
						   // create a signature form field 
			            PdfPTable BillingFieldsInfoTable81 = new PdfPTable(1);
			            BillingFieldsInfoTable81.setWidthPercentage(20);
			            BillingFieldsInfoTable81.setHorizontalAlignment(Element.ALIGN_LEFT);
			            BillingFieldsInfoTable81.getDefaultCell().setBorder(Rectangle.BOX);
			            BillingFieldsInfoTable81.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			            BillingFieldsInfoTable81.getDefaultCell().setVerticalAlignment(Element.ALIGN_LEFT);
			           
			            
			            PdfFormField field = PdfFormField.createSignature(writer);
			            field.setFieldName(SIGNAME);
			            // set the widget properties
			            field.setPage();
			            field.setWidget(new Rectangle(72, 732, 144, 780), PdfAnnotation.HIGHLIGHT_NONE);
			            field.setFlags(PdfAnnotation.FLAGS_PRINT);
			            writer.addAnnotation(field);
			            
			            PdfPCell sigCell = new PdfPCell();
			            FieldPositioningEvents events = new FieldPositioningEvents(writer, field);
			            sigCell.setCellEvent(events);
			            sigCell.setFixedHeight(50f);
			            BillingFieldsInfoTable81.addCell(sigCell);
			            // add it as an annotation
			            ///////////////////////////////////////////
			            
			            PdfPTable SignatureInfoTable1 = new PdfPTable(1);
						SignatureInfoTable1.setWidthPercentage(100);
						SignatureInfoTable1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
						SignatureInfoTable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
						SignatureInfoTable1.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
						//RG20190325 For Company change*/SignatureInfoTable.addCell(new Phrase(new Chunk("For Hazira LNG Private Limited\n\n\n\n\nAuthorised Signatory",black_bold)));
						SignatureInfoTable1.addCell(new Phrase(new Chunk("Authorised Signatory",black_bold)));
						                 
						
						PdfPTable GenerationInfoTable = new PdfPTable(1);
			            GenerationInfoTable.setWidthPercentage(100);
			            GenerationInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            GenerationInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            GenerationInfoTable.addCell(new Phrase(new Chunk("FMS7",small_black_normal)));
			            
			            PdfPTable GenerationInfoTable2 = new PdfPTable(1);
			            GenerationInfoTable2.setWidthPercentage(100);
			            GenerationInfoTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            GenerationInfoTable2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			            GenerationInfoTable2.addCell(new Phrase(new Chunk("This is computer generated INVOICE and hence does not require Authorised Signatory.",small_black_normal)));
			            
			            
		              //Adding All Cells To PDF Document - One By One ...
		               /* document.add(new Paragraph("              "));
		                document.add(new Paragraph("              "));
		                document.add(new Paragraph("              "));
		                document.add(new Paragraph("              "));*/
			            document.add(hlpl_logo_table1);
//		    			document.add(InvoiceTitleTable);
		    			document.add(InvoiceDescTable);
		    			document.add(InvoiceDescTable2);
		    			if(contract_type.equalsIgnoreCase("V"))
		    			{
		    				document.add(InvoiceNoteTable);
		    			}
		    			
		    			document.add(new Paragraph("              "));
		    			document.add(contact_addr_table);                        
		                document.add(GstCstInfoTable);            
		                document.add(new Paragraph("              "));
		                
		                if(!irn_no.equals("") && (!qr_code.equals(""))){
		                	document.add(InvoiceDateInfoTable_qr);
		                }else{
		    	            document.add(InvoiceDateInfoTable);
		    	            document.add(InvoiceDueDateInfoTable);
		    	            document.add(InvoiceNOInfoTable);
//		    	            document.add(BillingPeriodInfoTable);
		                }
		                document.add(new Paragraph("              "));
		                document.add(BillingFieldsInfoTable);
		                document.add(BillingFieldsDetailsTable_2);
		                document.add(BillingFieldsDetailsTable_3);
		                document.add(new Paragraph("              "));
		                document.add(RemarkTable);
		                document.add(new Paragraph("              "));
		                document.add(RemarkTable2);
		                document.add(new Paragraph("              "));
		                document.add(SignatureInfoTable);          
		                document.add(BillingFieldsInfoTable81);
		                document.add(SignatureInfoTable1);
		                
		                // ********* Following Coding is for Showing Attachment-1 Sheet on the Next (2nd Page) of the Same PDF Document *********** //
		                document.setPageSize(pageSize1);
		                document.newPage();
		          //      document.open();
		                very_small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 5, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                Font small_black_normal_new = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                Font small_black_bold2 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                Font small_black_bold2_new = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                big_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                black_bold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                if(invoice_title.contains("CREDIT") ) //HS20160615-----font
		    			{
		                	very_small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                	small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                	small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                	black_bold = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                	big_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 15, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                	 small_black_normal_new = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                	 small_black_bold2 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                	 small_black_bold2_new = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new Color(0x00, 0x00, 0x00));
		    			}			  
		    			url_start = "http://"+server_nm+":"+server_port+context_nm;
		    						  
		                hlpl_logo.setBorder(Rectangle.NO_BORDER);
		                //hlpl_logo.scaleAbsolute(75,75);
		                hlpl_logo.scaleAbsolute(48,45);
		                PdfPCell hlpl_logo_cell = new PdfPCell(hlpl_logo,false);
		                hlpl_logo_cell.setBorder(Rectangle.NO_BORDER);
		    			
		                
		               // float[] hlpl_logo_Widths = {0.45f, 0.51f, 0.08f};
		                float[] hlpl_logo_Widths = {0.45f};
		                PdfPTable hlpl_logo_table = new PdfPTable(hlpl_logo_Widths);
		                hlpl_logo_table.setWidthPercentage(100);
//		                hlpl_logo_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//		                hlpl_logo_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
//		                hlpl_logo_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
//		                hlpl_logo_table.addCell(new Phrase(new Chunk(" ",small_black_bold)));
//		                hlpl_logo_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//		                hlpl_logo_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
//		                hlpl_logo_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
//		                hlpl_logo_table.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                hlpl_logo_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                hlpl_logo_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                hlpl_logo_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                hlpl_logo_table.addCell(hlpl_logo_cell);
		                
		    			addr_supl = "Registered Office:";
		    			Paragraph pp1=new Paragraph();
		    			pp1.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
		                
		                if(!contact_Suppl_Name.trim().equals(""))
		                {
		                	//addr_supl += "\n"+contact_Suppl_Name;    
		                	pp1.add(new Phrase(new Chunk("\n"+contact_Suppl_Name,small_black_normal)));
		                }
		                if(!tempcompname.trim().equals(""))
		                {
		                	//addr_supl += "\n"+contact_Suppl_Name;    
		                	pp1.add(new Phrase(new Chunk("\n"+tempcompname,small_black_normal2)));
		                }
		                
//		                if(!contact_Suppl_Name.trim().equals(""))
//		                {
//		                	addr_supl += "\n"+contact_Suppl_Name;     	
//		                }
		                if(!contact_Suppl_Person_Address.trim().equals(""))
		                {
		                	//addr_supl += "\n"+contact_Suppl_Person_Address;
		                	addr_supl = "\n"+contact_Suppl_Person_Address;
		                }
		                if(!contact_Suppl_Person_City.trim().equals(""))
		                {
		                	addr_supl += "\n"+contact_Suppl_Person_City;     	
		                }
		                if(!contact_Suppl_Person_Pin.trim().equals(""))
		                {
		                	addr_supl += " "+"-"+" "+contact_Suppl_Person_Pin;     	
		                }
		                pp1.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
		    			
		    			addr_customer = "";
		                
		    			if(contact_Person_Name_And_Designation.trim().equalsIgnoreCase(contact_Customer_Name.trim()))
		    			{
		    				if(!contact_Customer_Name.trim().equals(""))
		    	            {
		    	            	addr_customer += contact_Customer_Name;    	
		    	            }
		    			}
		    			else
		    			{
		    	            if(!contact_Person_Name_And_Designation.trim().equals(""))
		    	            {
		    	            	addr_customer += contact_Person_Name_And_Designation;     	
		    	            }
		    	            if(!contact_Customer_Name.trim().equals(""))
		    	            {
		    	            	addr_customer += "\n"+contact_Customer_Name;     	
		    	            }
		    			}
		                if(!contact_Customer_Person_Address.trim().equals(""))
		                {
		                	addr_customer += "\n"+contact_Customer_Person_Address;     	
		                }
		                if(!contact_Customer_Person_City.trim().equals(""))
		                {
		                	addr_customer += "\n"+contact_Customer_Person_City;     	
		                }
		                if(!contact_Customer_Person_Pin.trim().equals(""))
		                {
		                	addr_customer += " "+"-"+" "+contact_Customer_Person_Pin;     	
		                }
		                            
		                          
		                float[] ContactAddrWidths2 = {0.25f, 0.55f, 0.20f};
		                contact_addr_table = new PdfPTable(ContactAddrWidths2);
		                contact_addr_table.setWidthPercentage(100);
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk(contact_Suppl_Name,black_bold)));
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk(" ",black_bold)));
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk("To:",black_bold)));
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                //contact_addr_table.addCell(new Phrase(new Chunk(addr_supl,small_black_normal)));
		                contact_addr_table.addCell(pp1);
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk(addr_customer,small_black_normal)));
		                String title_note="";
		                if(!invoice_title.contains("CREDIT")){
		                	 title_note = "ATTACHMENT 1 - Service Invoice Details"; 
		                }else{
		                    title_note = "ATTACHMENT 1 - Service Invoice Details";
		                }
		                
		                PdfPTable title_note_table = new PdfPTable(1);
		                title_note_table.setWidthPercentage(100);
		                title_note_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                title_note_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                title_note_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                title_note_table.addCell(new Phrase(new Chunk(title_note,big_black_bold)));
		                
		                invno = "";
		                if(!new_inv_seq_no.equals("")) {
		                	invno = new_inv_seq_no;
		                } else {
		                	 if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		                 	{
		                 		if(hlpl_inv_no.length()>13)
		                 		{
		                 			invno = hlpl_inv_no.substring(0,10)+""+hlpl_inv_no.substring(12);
		                 		}      		
		                 	}
		                 	else
		                 	{
		                 		if(hlpl_inv_no.length()>13)
		                 		{
		                 			invno = hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
		                 		}       		
		                 	}
		                }
		               
		                
		    			inv_no_info = "";
		    			invoiceType = "";
		    			inv_dt_Header = "Invoice Date:";
		    			if(invoice_title.contains("CREDIT") ) //SB20160530
		    			{
		    				invoiceType = "Credit Note No:";
		    				inv_dt_Header = "";
		    				invno = hlpl_drcr_docNo;
		    				inv_due_dt_Header = "Credit Note Date:";
		    				customer_Invoice_Due_DT =hlpl_drcr_dt;
		    			}
		    			else
		    				invoiceType = "Invoice Seq No:";
		    			
		    			if(invoice_title.contains("CREDIT")){ //HS20160616
		    				inv_no_info = "SEIPL Credit Note No:";
		    			}else{
		    					inv_no_info = "SEIPL TAX Invoice Seq No:";
		    			}
		    			
//		    			float[] InvoiceDateInfoWidths3 = {0.45f, 0.21f, 0.13f, 0.08f, 0.13f};
		    			  float[] InvoiceDateInfoWidths3 = {0.25f, 0.55f, 0.20f};
		                InvoiceDateInfoTable = new PdfPTable(InvoiceDateInfoWidths3);
		                InvoiceDateInfoTable.setWidthPercentage(100);
		                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                if(invoice_title.contains("CREDIT")) //SB20160530
		                	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_dt_Header,small_black_bold)));
		                else
		                	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                if(invoice_title.contains("CREDIT")|| invoice_title.contains("Sup")|| invoice_title.contains("DEBIT")   ) //SB20160611
		                {
		                	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                	InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		    	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		    	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		    	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                }
		                else
		                {
		                	InvoiceDateInfoTable.addCell(new Phrase(new Chunk("Invoice Date:",small_black_bold)));
		    	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		    	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		    	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		    	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_DT,small_black_normal)));
		                }
		                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                
		                float[] InvoiceDueDateInfoWidths3 = {0.25f, 0.55f, 0.20f};
		                InvoiceDueDateInfoTable = new PdfPTable(InvoiceDueDateInfoWidths3);
		                InvoiceDueDateInfoTable.setWidthPercentage(100);
		                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		         //SB20160601       InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk("Payment Due Date:",small_black_bold)));
		                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(inv_due_dt_Header,small_black_bold)));
		                
		                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_Due_DT,small_black_normal)));
		                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                
		               
		                String invno1 = "";
		                if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		            	{
		            		if(hlpl_inv_no.length()>13)
		            		{
		            			////System.out.println("hlpl_inv_no---"+hlpl_inv_no+"----"+hlpl_inv_no.substring(0,5)+"---"+hlpl_inv_no.substring(7,10));
		            			if(!new_inv_seq_no.equals("")) {
		            				invno1 = new_inv_seq_no;
		            			} else {
		            				invno1 = hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
		            			}
		            		}
		            		
		            	}
		            	else
		            	{
		            		if(hlpl_inv_no.length()>13)
		            		{
		            			if(!new_inv_seq_no.equals("")) {
		            				invno1 = new_inv_seq_no;
		            			} else {
		            				invno1 = hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
		            			}
		            		}
		            	}
		                
		                float[] InvoiceNOInfoWidths3 = {0.25f, 0.55f, 0.20f};
		                InvoiceNOInfoTable = new PdfPTable(InvoiceNOInfoWidths3);
		                InvoiceNOInfoTable.setWidthPercentage(100);
		                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(inv_no_info,small_black_bold)));
		                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(invno,small_black_normal)));
		                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                            
		                
		                float[] BillingPeriodInfoWidths3 = {0.45f, 0.21f, 0.13f, 0.08f, 0.13f};
		                BillingPeriodInfoTable = new PdfPTable(BillingPeriodInfoWidths3);
		                BillingPeriodInfoTable.setWidthPercentage(100);
		                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                BillingPeriodInfoTable.addCell(new Phrase(new Chunk("For the Billing Period",small_black_bold)));
		                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(bill_period_start_dt,small_black_normal)));
		                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                BillingPeriodInfoTable.addCell(new Phrase(new Chunk("to",small_black_normal)));
		                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_End_DT,small_black_normal)));
		               
		             
		                float[] AttachFieldsInfoWidths = null;
		                if(calcBase.equals("1") || calcBase.equals("3")){
		                	AttachFieldsInfoWidths = new float[] {0.10f, 0.10f,0.10f, 0.10f, 0.10f, 0.10f};
		                }else if(calcBase.equals("2")){
		                	AttachFieldsInfoWidths = new float[] {0.10f, 0.10f,0.10f, 0.10f, 0.10f, 0.10f, 0.10f};
		                }
		                
		                PdfPTable AttachFieldsInfoTable = new PdfPTable(AttachFieldsInfoWidths);
		                AttachFieldsInfoTable.setWidthPercentage(100);
		                
		                AttachFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                AttachFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                AttachFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                AttachFieldsInfoTable.addCell(new Phrase(new Chunk("Sr. No.",small_black_bold)));
		                
		                AttachFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                AttachFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                AttachFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                AttachFieldsInfoTable.addCell(new Phrase(new Chunk("Supply Date",small_black_bold)));
		                
		                AttachFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                AttachFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                AttachFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                AttachFieldsInfoTable.addCell(new Phrase(new Chunk("Truck No.",small_black_bold)));
		                
		                AttachFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                AttachFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                AttachFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                AttachFieldsInfoTable.addCell(new Phrase(new Chunk("Truck Quantity\n(MMBtu)",small_black_bold)));
		                
		                String curr_base="";
		                if(calcBase.equalsIgnoreCase("1")) {
		                	curr_base = "INR/MMBtu";
		                }else if(calcBase.equalsIgnoreCase("2")){
		                	curr_base = "INR/KM";
		                }else if(calcBase.equalsIgnoreCase("3")){
		                	curr_base = "INR/TRUCK";
		                }
		                
		                AttachFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                AttachFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                AttachFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                AttachFieldsInfoTable.addCell(new Phrase(new Chunk("Rate\n("+curr_base+")",small_black_bold)));
		                
		                if(calcBase.equals("2")) {
		                	
		                	AttachFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                AttachFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			                AttachFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
			                AttachFieldsInfoTable.addCell(new Phrase(new Chunk("Kilometer(s)",small_black_bold)));
		                }
		                
		                AttachFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                AttachFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                AttachFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                AttachFieldsInfoTable.addCell(new Phrase(new Chunk("Amount \n(INR)",small_black_bold)));
		                
//		                float[] AttachDataInfoWidths = {0.10f, 0.10f,0.10f, 0.10f, 0.10f, 0.10f};
//		                PdfPTable AttachDataInfoTable = new PdfPTable(AttachDataInfoWidths);
//		                AttachDataInfoTable.setWidthPercentage(100);
		                
		                float[] AttachDataInfoWidths = null;
		                if(calcBase.equals("1") || calcBase.equals("3")){
		                	AttachDataInfoWidths = new float[] {0.10f, 0.10f,0.10f, 0.10f, 0.10f, 0.10f};
		                }else if(calcBase.equals("2")){
		                	AttachDataInfoWidths = new float[] {0.10f, 0.10f,0.10f, 0.10f, 0.10f, 0.10f, 0.10f};
		                }
		                
		                PdfPTable AttachDataInfoTable = new PdfPTable(AttachDataInfoWidths);
		                AttachDataInfoTable.setWidthPercentage(100);
		                
		                for(int i = 0 ; i < View_truck_inv_no.size() ; i++){ 
		                	
		                	 int ind= i+1;
		                	 String truck_inv_dt = View_truck_inv_dt.elementAt(i)+"";
		                	 String truck_truck_nm = View_truck_nm.elementAt(i)+"";
		                	 String inv_qty = View_invoice_qty.elementAt(i)+"";
		                	 String rate = "";
		                	 if(calcBase.equals("3")){
		                		 rate = View_amount.elementAt(i)+"";
		                	 }else {
		                		 rate = View_rate.elementAt(i)+"";
		                	 }
		                	 
		                	 String km = View_km.elementAt(i)+"";
		                	 String amt = View_amount.elementAt(i)+"";
		                	 
		                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk(ind+"",small_black_bold)));
		                	
		                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk(truck_inv_dt,small_black_bold)));
		                	
		                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk(truck_truck_nm,small_black_bold)));
		                	
		                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk(inv_qty,small_black_bold)));
		                	
		                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk(rate,small_black_bold)));
		                	
		                	 if(calcBase.equals("2")) {
		                		 
		                		 	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
				                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                	AttachDataInfoTable.addCell(new Phrase(new Chunk(km,small_black_bold)));
		                	 }
		                	 
		                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk(amt,small_black_bold)));
		                }
		                
		                AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	                	AttachDataInfoTable.getDefaultCell().setColspan(3);
	                	AttachDataInfoTable.addCell(new Phrase(new Chunk("Total",small_black_bold)));
		                
	                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	                	AttachDataInfoTable.getDefaultCell().setColspan(1);
	                	AttachDataInfoTable.addCell(new Phrase(new Chunk(nf5.format(total_qty)+"",small_black_bold)));
	                	
	                	if(calcBase.equalsIgnoreCase("2")){
	                		
	                		AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                	AttachDataInfoTable.getDefaultCell().setColspan(2);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk("",small_black_bold)));
		                	
		                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
//		                	AttachDataInfoTable.getDefaultCell().setColspan(2);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk(nf7.format(total_amt)+"",small_black_bold)));
	                	
	                	}else {
	                		
	                		AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                	AttachDataInfoTable.getDefaultCell().setColspan(1);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk("",small_black_bold)));
		                	
		                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
//		                	AttachDataInfoTable.getDefaultCell().setColspan(2);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk(nf7.format(total_amt)+"",small_black_bold)));
	                		
	                	}
		                
		                
		                document.add(new Paragraph("              "));
						document.add(hlpl_logo_table);//RG20190516
						document.add(new Paragraph("              "));
						document.add(contact_addr_table);                        
			            document.add(new Paragraph("              "));
			            document.add(title_note_table);
			            document.add(new Paragraph("              "));
			            document.add(InvoiceDateInfoTable);
			            document.add(InvoiceDueDateInfoTable);
			            document.add(InvoiceNOInfoTable);
			            document.add(new Paragraph("              "));
			            document.add(BillingPeriodInfoTable);
			            document.add(new Paragraph("              "));
			            document.add(AttachFieldsInfoTable);
			            document.add(AttachDataInfoTable);
			            
			           /* document.add(ExchgRateDetailsTable);
			            document.add(new Paragraph("              "));
			            document.add(ExchgRateApplicableTable);
			            document.add(new Paragraph("              "));
			            document.add(BankSourceTable);
			            document.add(new Paragraph("              "));*/
			            document.add(new Paragraph("              "));
			            document.add(new Paragraph("              "));
		                
				}catch(DocumentException de)
				{
					System.err.println("DocumentException in printAllPdfFileForInvoice() Method :\n"+de.getMessage());
					de.printStackTrace();
				}
				catch(IOException ioe)
				{
					System.err.println("IOException in printAllPdfFileForInvoice() Method :\n"+ioe.getMessage());
					ioe.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}
				finally
				{
					document.close();
				}
		
		}

		private void printPdfFileForInvoice()throws Exception{

			
			Rectangle pageSize = new Rectangle(595, 842);
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
			 Document document = new Document(pageSize);
			 try
				{
					PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(createPdfFileForInvoice()));
					document.addTitle("Invoice Details");
					document.addSubject("Invoice Details For Customer");
		            document.addKeywords("iText, Invoice Details, Step 2, metadata");
		            document.addCreator("Invoice Details Generation using iText");
		            document.addAuthor("DLNG@BIPL");
		            document.open();
					document.setPageSize(pageSize);
		            document.newPage();

		            Font very_small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		            Font small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		            Font small_black_normal2 = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		            Font small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
		            Font black_bold = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new Color(0x00, 0x00, 0x00));
		            Font big_black_bold_2 = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
		            Font big_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new Color(0x00, 0x00, 0x00));
		            
		            String context_nm = request.getContextPath();
					String server_nm = request.getServerName();
					String server_port = ""+request.getServerPort();
					  
					url_start = "http://"+server_nm+":"+server_port+context_nm;
					
		           // Image hlpl_logo = Image.getInstance(url_start+"//images//LOGO//HLPL-Symbol.jpg");
					 Image hlpl_logo = Image.getInstance(url_start+"/images/logo/company_Logo.png");
					           
					String inv_nm = "TAX INVOICE\nTAX Invoice issued under Rule 46 of the Central Goods and Services Tax Rules, 2017";
					String inv_desc = "Shell Energy India Private Limited";
					String inv_note = "In respect of Transport Management Service Agreement ("+trans_cont_no+") executed on "+signing_dt+" \nbetween "+contact_Suppl_Name+" and "+contact_Customer_Name;
					
					String addr_supl = "Registered Office:";
					Paragraph pp=new Paragraph();
					pp.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
		            
		            if(!contact_Suppl_Name.trim().equals(""))
		            {
		            	//addr_supl += "\n"+contact_Suppl_Name;    
		            	pp.add(new Phrase(new Chunk("\n"+contact_Suppl_Name,small_black_normal)));
		            }
		            if(!tempcompname.trim().equals(""))
		            {
		            	//addr_supl += "\n"+contact_Suppl_Name;    
		            	pp.add(new Phrase(new Chunk("\n"+tempcompname,small_black_normal2)));
//		            	hiren
		            }
		            
		            if(!contact_Suppl_Person_Address.trim().equals(""))
		            {
		            	//addr_supl += "\n"+contact_Suppl_Person_Address;
		            	addr_supl = "\n"+contact_Suppl_Person_Address;
		            }
		            if(!contact_Suppl_Person_City.trim().equals(""))
		            {
		            	addr_supl += "\n"+contact_Suppl_Person_City;     	
		            }
		            if(!contact_Suppl_Person_Pin.trim().equals(""))
		            {
		            	addr_supl += " "+"-"+" "+contact_Suppl_Person_Pin;     	
		            }
		            pp.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
					
					String addr_customer = "";
		            
					if(contact_Person_Name_And_Designation.trim().equalsIgnoreCase(contact_Customer_Name.trim()))
					{
						if(!contact_Customer_Name.trim().equals(""))
			            {
			            	addr_customer += contact_Customer_Name;    	
			            }
					}
					else
					{
			            if(!contact_Person_Name_And_Designation.trim().equals(""))
			            {
			            	addr_customer += contact_Person_Name_And_Designation;     	
			            }
			            if(!contact_Customer_Name.trim().equals(""))
			            {
			            	addr_customer += "\n"+contact_Customer_Name;     	
			            }
					}
		            if(!contact_Customer_Person_Address.trim().equals(""))
		            {
		            	addr_customer += "\n"+contact_Customer_Person_Address;     	
		            }
		            if(!contact_Customer_Person_City.trim().equals(""))
		            {
		            	addr_customer += "\n"+contact_Customer_Person_City;     	
		            }
		            if(!contact_Customer_Person_Pin.trim().equals(""))
		            {
		            	addr_customer += " "+"-"+" "+contact_Customer_Person_Pin;     	
		            }
		            
		            PdfPTable InvoiceTitleTable = new PdfPTable(1);
		            InvoiceTitleTable.setWidthPercentage(100);
		            InvoiceTitleTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            InvoiceTitleTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		          //SB20160601  InvoiceTitleTable.addCell(new Phrase(new Chunk(invoice_title,black_bold)));
		            
		            if(invoice_title.contains("CREDIT") ||invoice_title.contains("Sup")|| invoice_title.contains("DEBIT")  ) //SB20160601
		            {
		            	String inv_tit = invoice_title.substring(invoice_title.length()-1,invoice_title.length());
//		            	System.out.println("inv_tit******"+inv_tit);
		            	if(inv_tit.equals("O")) {
		            		inv_tit = "ORIGINAL FOR RECIPIENT";
		            	} else if (inv_tit.equals("D")){
		            		inv_tit = "DUPLICATE FOR RECIPIENT";
		            	}else {
		            		inv_tit = "TRIPLICATE";
		            	}
		            	
		            	
		            	InvoiceTitleTable.addCell(new Phrase(new Chunk(inv_tit,black_bold)));
		            }
		            else
		            {
		            	String inv_tit = invoice_title;
		            	if(contract_type.equals("V")) {
			            	if(invoice_title.equalsIgnoreCase("ORIGINAL")) {
			            		inv_tit += " FOR RECIPIENT"; 
			                } else if(invoice_title.equalsIgnoreCase("DUPLICATE")) {
			                	inv_tit += " FOR SUPPLIER";
			                } 
		            	}
		            	InvoiceTitleTable.addCell(new Phrase(new Chunk(inv_tit,black_bold)));
		            }
		            
		            PdfPTable InvoiceDescTable = new PdfPTable(1);
		            InvoiceDescTable.setWidthPercentage(100);
		            InvoiceDescTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            InvoiceDescTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            InvoiceDescTable.addCell(new Phrase(new Chunk(inv_desc,big_black_bold)));
					
		            PdfPTable InvoiceDescTable2 = new PdfPTable(1);
		            InvoiceDescTable2.setWidthPercentage(100);
		            InvoiceDescTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            InvoiceDescTable2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            if(invoice_title.contains("CREDIT") ) {
		            	if(contract_type.equals("S")  || contract_type.equals("L")){
		            		InvoiceDescTable2.addCell(new Phrase(new Chunk("CREDIT NOTE",big_black_bold_2)));
		            	}else{
		            		InvoiceDescTable2.addCell(new Phrase(new Chunk("CREDIT NOTE\nCredit Note issued under Rule 53 of the Central Goods and Services Tax Rules, 2017",big_black_bold_2)));
		            	}
		            }else if(invoice_title.contains("Sup")){
		            		InvoiceDescTable2.addCell(new Phrase(new Chunk("DEBIT NOTE\nDebit Note issued under Rule 53 of the Central Goods and Services Tax Rules, 2017",big_black_bold_2)));
		            }else if(invoice_title.contains("DEBIT")){
		            	//	InvoiceDescTable2.addCell(new Phrase(new Chunk("DEBIT NOTE\nDebit Note issued under Rule 53 of the Central Goods and Services Tax Rules, 2017",big_black_bold_2)));
		            		InvoiceDescTable2.addCell(new Phrase(new Chunk("DEBIT NOTE",big_black_bold_2)));
		            }else{
		            	InvoiceDescTable2.addCell(new Phrase(new Chunk(inv_nm,big_black_bold_2)));
		            }
		            
		            PdfPTable InvoiceNoteTable = new PdfPTable(1);
		            InvoiceNoteTable.setWidthPercentage(100);
		            InvoiceNoteTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            InvoiceNoteTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		            InvoiceNoteTable.addCell(new Phrase(new Chunk(inv_note,small_black_normal)));
					
		            float[] ContactAddrWidths = {0.50f, 0.10f, 0.40f};
		            PdfPTable contact_addr_table = new PdfPTable(ContactAddrWidths);
		            contact_addr_table.setWidthPercentage(100);
		            contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		         //   contact_addr_table.addCell(new Phrase(new Chunk(addr_supl,small_black_normal)));
		            contact_addr_table.addCell(pp);
		            contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            contact_addr_table.addCell(new Phrase(new Chunk("To:",small_black_bold)));
		            contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            contact_addr_table.addCell(new Phrase(new Chunk(addr_customer,small_black_normal)));
					
		            String supl_gst_cst_info = "";
		            String customer_gst_cst_info = "";
		            
		          //  //System.out.println("-----INSIDE PRINTALL 2---"+vSTAT_CD.size());
		            
		            if(contract_type.equalsIgnoreCase("V")){

		            	if(!contact_Suppl_State.trim().equals("")) {
		            		supl_gst_cst_info = "State : "+contact_Suppl_State;
		            	}
		            	if(!contact_Suppl_State_Code.trim().equals("")) {
		            		supl_gst_cst_info += "\nState Code : "+contact_Suppl_State_Code;
		            	}
		            	if(!contact_Suppl_GSTIN_NO.trim().equals(""))
		            	{
		            		supl_gst_cst_info += "\nGSTIN No. : "+contact_Suppl_GSTIN_NO;
		            		if(!contact_Suppl_GST_DT.equals("")) {
		            			supl_gst_cst_info+=" DT. "+contact_Suppl_GST_DT;
		            		}
		            	}
		            	if(!contact_Suppl_PAN_NO.trim().equals(""))
		            	{
		            		supl_gst_cst_info += " \nPAN No.: "+contact_Suppl_PAN_NO+" ";
		            	}
		            	if(!sac_code.trim().equals(""))
		            	{
		            		supl_gst_cst_info += " \nSAC No. : "+sac_code+" ";
		            	}
		            	if(!sac_desc.trim().equals(""))
		            	{
		            		supl_gst_cst_info += " \nDescription of Service : "+sac_desc+" ";
		            	}
		            	if(!contact_customer_State.trim().equals(""))
		            	{
		            		supl_gst_cst_info += " \nPlace of Supply : "+contact_customer_State+" ";
		            	}
		            	
		    		}
		            
		            if(!contact_customer_State.trim().equals(""))
	            	{
		            	customer_gst_cst_info = "State : "+contact_customer_State+" ";
	            	}
		            if(!contact_customer_State_Code.trim().equals(""))
	            	{
		            	customer_gst_cst_info += "\nState Code : "+contact_customer_State_Code+" ";
	            	}
		            
		            if(vSTAT_CD.size()>0)
		    		{	
		    			if(contract_type.equalsIgnoreCase("V"))
		    			{
		    				for(int i=0; i<vSTAT_CD.size(); i++)
							{
	    						if(vSTAT_NM.elementAt(i).toString().equalsIgnoreCase("PAN No.")) 
	    						{
	    							customer_gst_cst_info += "\nPAN No. : "+vSTAT_NO.elementAt(i)+" ";
	    						}
	    						else
	    						{
	    							if(vSTAT_NM.elementAt(i).toString().contains("GSTIN")) 
		    						{
	    								customer_gst_cst_info += "\nGSTIN No. : "+vSTAT_NO.elementAt(i);
	    								if(!vSTAT_EFF_DT.elementAt(i).equals("")) {
	    									customer_gst_cst_info+=" DT. "+vSTAT_EFF_DT.elementAt(i);
	    			            		}
		    						}
	    						}
							}
		    			}
		    		}
		            else
		            {/*
		            	if(contract_type.equalsIgnoreCase("V"))
		        		{
		            		if(!contact_Customer_GST_NO.trim().equals(""))
		                	{
		                		customer_gst_cst_info = "GST TIN No. : "+contact_Customer_GST_NO+" DT. "+contact_Customer_GST_DT;
		                	}
		                	else
		                	{
		                		customer_gst_cst_info = "";
		                	}
		                	
		                	if(!contact_Customer_CST_NO.trim().equals(""))
		                	{
		                		customer_gst_cst_info += "\nCST TIN No. : "+contact_Customer_CST_NO+" DT. "+contact_Customer_CST_DT;
		                	}
		                	else
		                	{
		                		customer_gst_cst_info += "\n";
		                	}
		                	
		                	if(!contact_Customer_GVAT_NO.trim().equals(""))
		                	{
		                		customer_gst_cst_info += "\nGVAT TIN No. : "+contact_Customer_GVAT_NO+" DT. "+contact_Customer_GVAT_DT;
		                	}
		                	else
		                	{
		                		customer_gst_cst_info += "\n";
		                	}
		        		}
		            */}
		            
		            float[] GstCstInfoWidths = {0.50f, 0.10f, 0.40f};
		            PdfPTable GstCstInfoTable = new PdfPTable(GstCstInfoWidths);
		            GstCstInfoTable.setWidthPercentage(100);
		            GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            GstCstInfoTable.addCell(new Phrase(new Chunk("\n"+supl_gst_cst_info,small_black_normal)));
		            GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            GstCstInfoTable.addCell(new Phrase(new Chunk("\n ",small_black_normal)));
		            GstCstInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		            GstCstInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		            GstCstInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		            GstCstInfoTable.addCell(new Phrase(new Chunk("\n"+customer_gst_cst_info,small_black_normal)));
		            
		            String invno = "";
		            
		            if(!new_inv_seq_no.equals("")) {
		            	invno = new_inv_seq_no;
		            } else {
		            	if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		            	{
		            		if(hlpl_inv_no.length()>13)
		            		{
		            			invno = hlpl_inv_no.substring(0,10)+""+hlpl_inv_no.substring(12);
		            		}      		
		            	}
		            	else
		            	{
		            		if(hlpl_inv_no.length()>13)
		            		{
		            			invno = hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
		            		}       		
		            	}
		            }
		            
		            String inv_no_info = "";
					String invoiceType = "";
					String inv_dt_Header = "Invoice Date ";
					String Inv_Ref_Dtl = ""; //SB20160611  
					 
					String inv_due_dt_Header = "Payment Due Date ";
					if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT")) //SB20160530
					{             
				         Inv_Ref_Dtl = " as per Invoice ref "+invno+" dated "+customer_Invoice_DT;
				        if(invoice_title.contains("CREDIT")){    
						invoiceType = "Credit Note No ";
				        }else if(invoice_title.contains("Sup")){
				        	invoiceType = "Debit Note No ";
				        }else {
				        	invoiceType = "Debit Note No ";
				        }
				//SB*		inv_dt_Header = "Invoice  Ref No: "+invno;
						inv_dt_Header = "";
						//customer_Invoice_DT =invno+":"+customer_Invoice_DT;
						invno = hlpl_drcr_docNo;
						if(invoice_title.contains("CREDIT")){    
						inv_due_dt_Header = "Credit Note Date ";
						}else if(invoice_title.contains("Sup")){
							inv_due_dt_Header = "Debit Note Date ";
						}else {
							inv_due_dt_Header = "Debit Note Date ";
						}
						customer_Invoice_Due_DT =hlpl_drcr_dt;
					}
					else
					{
						invoiceType = "SEIPL TAX Invoice Seq No ";
					}
					
					if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT")){ //SB20160530
						if(invoice_title.contains("CREDIT")){
							//RG20190325 For Company change*/inv_no_info = "HLPL Credit Note No:";
							inv_no_info = "SEIPL Credit Note No ";
						}else if(invoice_title.contains("Sup")){
							//RG20190325 For Company change*/inv_no_info = "HLPL Debit Note No:"; 
							inv_no_info = "SEIPL Debit Note No "; 
						}else {
						//RG20190325 For Company change*/	inv_no_info = "HLPL Debit Note No:"; 
							inv_no_info = "SEIPL Debit Note No ";
						}
					}
					else{
						inv_no_info = "SEIPL TAX Invoice Seq No ";
					}
					
					PdfPTable InvoiceDateInfoTable; 
					PdfPTable InvoiceDueDateInfoTable;
					PdfPTable InvoiceNOInfoTable;
					PdfPTable BillingPeriodInfoTable;
					
					 float[] InvoiceDateInfoWidths_qr = {0.40f, 0.20f, 0.60f};
					 PdfPTable InvoiceDateInfoTable_qr = new PdfPTable(InvoiceDateInfoWidths_qr);
		           if(!irn_no.equals("") && (!qr_code.equals(""))){
		        	   
//						float[] InvoiceDateInfoWidths = {0.60f, 0.25f, 0.15f};
						float[] InvoiceDateInfoWidths = {0.20f, 0.45f, 0.25f};
			            InvoiceDateInfoTable = new PdfPTable(InvoiceDateInfoWidths);
			            InvoiceDateInfoTable.setWidthPercentage(100);
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			        //SB20160531    InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT")) //SB20160530
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_dt_Header,small_black_bold)));
			            else
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT"))//SB20160530
			            {
				            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
				            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
				            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            }
			            else
			            {
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk("Invoice Date ",small_black_bold)));
			            	InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_DT,small_black_bold)));
			            }
			            float[] InvoiceDueDateInfoWidths = {0.60f, 0.25f, 0.15f};
			            InvoiceDueDateInfoTable = new PdfPTable(InvoiceDueDateInfoWidths);
			            
			           /* InvoiceDueDateInfoTable.setWidthPercentage(100);
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(inv_due_dt_Header,small_black_bold)));
			                    
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            if(!second_due_dt.equals("") && (!invoice_title.contains("Sup")) && (!invoice_title.contains("DEBIT")) && (!invoice_title.contains("CREDIT"))){
			            	InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(second_due_dt,small_black_bold)));
			            }else{
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_Due_DT,small_black_bold)));
			            }*/
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
		                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
		                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_due_dt_Header,small_black_bold)));
		                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
		                if(!second_due_dt.equals("") && (!invoice_title.contains("Sup")) && (!invoice_title.contains("DEBIT")) && (!invoice_title.contains("CREDIT"))){
		                	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(second_due_dt,small_black_bold)));
		                }else{
		                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_Due_DT,small_black_bold)));
		                }
			            
			            if(invoice_title.contains("Sup") || invoice_title.contains("DEBIT")) {
			            	
			            	InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk("Payment Due Date ",small_black_bold)));
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(dr_cr_due_dt,small_black_bold)));
			              }
			                float[] InvoiceNOInfoWidths = {0.60f, 0.25f, 0.15f};
			                InvoiceNOInfoTable = new PdfPTable(InvoiceNOInfoWidths);
			                
			                /*InvoiceNOInfoTable.setWidthPercentage(100);
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(inv_no_info,small_black_bold)));
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(invno,small_black_bold)));*/
			                
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_no_info,small_black_bold)));
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(invno,small_black_bold)));            
			                
			                /*float[] BillingPeriodInfoWidths = {0.55f,0.15f, 0.10f, 0.15f};
			                BillingPeriodInfoTable = new PdfPTable(BillingPeriodInfoWidths);
			                BillingPeriodInfoTable.setWidthPercentage(100);
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk("For the Billing Period***",small_black_bold)));
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_Start_DT,small_black_bold)));
			                
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk("to",small_black_bold)));
			                
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_End_DT,small_black_bold)));
			           
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(3);
			                InvoiceDateInfoTable.addCell(BillingPeriodInfoTable);
			                
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(3);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk("",small_black_bold)));*/
			                
			                float[] BillingPeriodInfoWidths = {0.55f, 0.05f, 0.20f, 0.10f, 0.20f};
			                BillingPeriodInfoTable = new PdfPTable(BillingPeriodInfoWidths);
			                BillingPeriodInfoTable.setWidthPercentage(100);
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk("For the Billing Period ",small_black_bold)));
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(bill_period_start_dt,small_black_bold)));
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk("to",small_black_bold)));
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_End_DT,small_black_bold)));
			                
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(3);
			                InvoiceDateInfoTable.addCell(BillingPeriodInfoTable);
			                
			                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDateInfoTable.getDefaultCell().setColspan(3);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk("",small_black_bold)));
			                
			                //code for Irn & qrcode starts now
			                url_start = "http://"+server_nm+":"+server_port+context_nm;
			                    String qrCodeText = qr_code;
			                    String filePath =  request.getRealPath("/pdf_reports/xls_reports/qrcode");
			          		  filePath = filePath+"//"+invno.replace("/","_")+".png";
			          		  int size = 45;//125 original
			          		  String fileType = "png";
			          		  File qrFile = new File(filePath);
			          		  com.seipl.hazira.dlng.util.DataBean_QRCode_Generator.createQRImage(qrFile, qrCodeText, size, fileType);
			          		  System.out.println("DONE");
			          		  
			                    
			                  Image qr_codeimg = Image.getInstance(url_start+"//pdf_reports//xls_reports//qrcode//"+invno.replace("/","_")+".png");			  
			                  qr_codeimg.setBorder(Rectangle.NO_BORDER);
			                  qr_codeimg.setAlignment(Element.ALIGN_LEFT);
			                 // qr_codeimg.scaleAbsolute(75,75);
			                //  qr_codeimg.scaleAbsolute(50,50);
			                  PdfPCell qrcode_cell = new PdfPCell(qr_codeimg,false);
			                  qrcode_cell.setBorder(Rectangle.NO_BORDER);
			                  qrcode_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			                      
//			                    String irn_no=irn_no;
			                    String char_16= irn_no.substring(0,16);
			                    String char_32= irn_no.substring(16,32);
			                    String char_48= irn_no.substring(32,48);
			                    String char_64= irn_no.substring(48,irn_no.length());
			                    String final_irn_no=char_16+"\n"+char_32+"\n"+char_48+"\n"+char_64;
			                    
			                    PdfPTable InvoiceDateInfoTable_qr1 = new PdfPTable(1);
			        	        InvoiceDateInfoTable_qr1.setWidthPercentage(100);
//			        	        InvoiceDateInfoTable_qr1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//			        	        InvoiceDateInfoTable_qr1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//			        	        InvoiceDateInfoTable_qr1.addCell(new Phrase(new Chunk("QR Code",small_black_bold)));
			        	        InvoiceDateInfoTable_qr1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	        InvoiceDateInfoTable_qr1.getDefaultCell().setBorder(Rectangle.BOX);
			        	        InvoiceDateInfoTable_qr1.addCell(qrcode_cell);
			        	        
			        	        PdfPTable InvoiceDateInfoTable_qr11 = new PdfPTable(1);
			        	        InvoiceDateInfoTable_qr11.setWidthPercentage(100);
			        	        InvoiceDateInfoTable_qr11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	        InvoiceDateInfoTable_qr11.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			        	        InvoiceDateInfoTable_qr11.addCell(new Phrase(new Chunk("IRN no.",small_black_bold)));
			        	        InvoiceDateInfoTable_qr11.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	        InvoiceDateInfoTable_qr11.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			        	        InvoiceDateInfoTable_qr11.addCell(new Phrase(new Chunk(final_irn_no,small_black_normal)));
			                    
			        			
			        	        InvoiceDateInfoTable_qr.setWidthPercentage(100);
			        	               
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			        	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable_qr1);
			        	        
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.BOX);
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			        	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable_qr11);
			        	        
			        	        InvoiceDateInfoTable_qr.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			        	        InvoiceDateInfoTable_qr.addCell(InvoiceDateInfoTable);
		           }else{
			        	   
						float[] InvoiceDateInfoWidths = {0.60f, 0.25f, 0.15f};
			            InvoiceDateInfoTable = new PdfPTable(InvoiceDateInfoWidths);
			            InvoiceDateInfoTable.setWidthPercentage(100);
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			        //SB20160531    InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT")) //SB20160530
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_dt_Header,small_black_bold)));
			            else
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            if(invoice_title.contains("CREDIT") || invoice_title.contains("Sup") || invoice_title.contains("DEBIT"))//SB20160530
			            {
				            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
				            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
				            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            }
			            else
			            {
			            	InvoiceDateInfoTable.addCell(new Phrase(new Chunk("Invoice Date ",small_black_bold)));
			            	InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_DT,small_black_bold)));
			            }
			            float[] InvoiceDueDateInfoWidths = {0.60f, 0.25f, 0.15f};
			            InvoiceDueDateInfoTable = new PdfPTable(InvoiceDueDateInfoWidths);
			            InvoiceDueDateInfoTable.setWidthPercentage(100);
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(inv_due_dt_Header,small_black_bold)));
			                    
			            InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			            InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            if(!second_due_dt.equals("") && (!invoice_title.contains("Sup")) && (!invoice_title.contains("DEBIT")) && (!invoice_title.contains("CREDIT"))){
			            	InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(second_due_dt,small_black_bold)));
			            }else{
			            InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_Due_DT,small_black_bold)));
			            }
			            
			            if(invoice_title.contains("Sup") || invoice_title.contains("DEBIT")) {
			            	
			            	InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDueDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDueDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk("Payment Due Date ",small_black_bold)));
			                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceDueDateInfoTable.getDefaultCell().setColspan(1);
			                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(dr_cr_due_dt,small_black_bold)));
			              }
			                float[] InvoiceNOInfoWidths = {0.60f, 0.25f, 0.15f};
			                InvoiceNOInfoTable = new PdfPTable(InvoiceNOInfoWidths);
			                InvoiceNOInfoTable.setWidthPercentage(100);
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(inv_no_info,small_black_bold)));
			                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(invno,small_black_bold)));
			                            
			                
			                float[] BillingPeriodInfoWidths = {0.55f,0.15f, 0.10f, 0.15f};
			                BillingPeriodInfoTable = new PdfPTable(BillingPeriodInfoWidths);
			                BillingPeriodInfoTable.setWidthPercentage(100);
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk("For the Billing Period ",small_black_bold)));
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_Start_DT,small_black_bold)));
			                
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk("to",small_black_bold)));
			                
			                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_End_DT,small_black_bold)));
			           }
		                float[] BillingFieldsInfoWidths = {0.06f, 0.40f,0.10f, 0.15f, 0.15f, 0.15f};
		                PdfPTable BillingFieldsInfoTable = new PdfPTable(BillingFieldsInfoWidths);
		                BillingFieldsInfoTable.setWidthPercentage(100);
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Sr. No.",small_black_bold)));
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Item",small_black_bold)));
		               
		               /* BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Attachment\nRef.",small_black_bold)));
		               */
		                String inr_base="",rateFlg="";
		                if(calcBase.equalsIgnoreCase("1")) {
		                	inr_base = "(MMBtu)";
		                	rateFlg = "(INR/MMBtu)";
		                }else if(calcBase.equalsIgnoreCase("2")){
		                	inr_base = "(KM)";
		                	rateFlg = "(INR/KM)";
		                }else if(calcBase.equalsIgnoreCase("3")) {
		                	inr_base = "(No.of Trucks)";
		                	rateFlg = "(INR)";
		                }
		                
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Currency",small_black_bold)));
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Quantity\n"+inr_base,small_black_bold)));
		                
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Rate\n"+rateFlg,small_black_bold)));
		                
		                
		                BillingFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsInfoTable.addCell(new Phrase(new Chunk("Amount",small_black_bold)));
		                
		                
		                String field_1 = "";
		                String field_2 = "";
//		                String field_3 = "";
		                String field_4 = "";
		                String field_5 = "";
		                String field_6 = "";
		                String field_7 = "";
		                
		                /*for field 1*/
		                int sr_no = 0;
		                for (int j = 0; j < Vsac_cd.size() ; j++) {
		                	
		                	++sr_no;
		                	field_1+=sr_no+"\n\n";
		                	field_2+=Vitem_desc.elementAt(j)+"\n\n";
		                	field_4+="Rupees\n\n";
		                	if(calcBase.equals("3") && j == 0) {
		                		field_5+=View_truck_inv_no.size()+"\n\n";
		                		field_6+="Att1\n\n";
		                	}else {
		                		field_5+=Vqty.elementAt(j)+"\n\n";
		                		field_6+=Vrate.elementAt(j)+"\n\n";
		                	}
		                	field_7+=nf7.format(Double.parseDouble(Vamt.elementAt(j)+""))+"\n\n";
						}
		                
		                ++sr_no;
	                	field_1+=sr_no+"\n\n";
	                	field_2+="Gross Amount\n\n";
	                	field_4+="Rupees\n\n";
	                	field_5+="\n\n";
	                	field_6+="\n\n";
	                	field_7+=nf7.format(Double.parseDouble(grossAmt+""))+"\n\n";
	                	
	                	String taxArr[] = taxnm_str.split("@@");
		                for(int j = 0; j < tax_size.length ; j++) {
		                	
		                	++sr_no;
		                	field_1+=sr_no+"\n";
		                	field_2+=taxArr[j]+"\n";
		                	field_4+="Rupees\n";
		                	field_5+="\n";
		                	field_6+=tax_structure+" %\n";
		                	field_7+=nf7.format(Math.round(Double.parseDouble(grossAmt) * Double.parseDouble(tax_structure)/100))+"\n";
		                }
		                
	                	++sr_no;
	                	field_1+=sr_no+"\n\n";
	                	field_2+="Total GST\n\n";
	                	field_4+="Rupees\n\n";
	                	field_5+="\n\n";
	                	field_6+="\n\n";
	                	field_7+=nf7.format(Double.parseDouble(totalTax+""))+"\n\n";
	                	
	                	++sr_no;
	                	field_1+=sr_no+"\n\n";
	                	field_2+="Invoice Amount\n\n";
	                	field_4+="Rupees\n\n";
	                	field_5+="\n\n";
	                	field_6+="\n\n";
	                	field_7+=nf7.format(Double.parseDouble(netAmt+""))+"\n\n";
		                
		                float[] BillingFieldsDetailsWidths_2 = {0.06f, 0.40f,0.10f, 0.15f, 0.15f, 0.15f};
		                PdfPTable BillingFieldsDetailsTable_2 = new PdfPTable(BillingFieldsDetailsWidths_2);
		                BillingFieldsDetailsTable_2.setWidthPercentage(100);
		                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_1,small_black_normal)));
		                
		                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_2,small_black_normal)));
		                
		                /*BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                //BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_3,small_black_normal)));
		                //BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_3,small_black_normal).setAction(new PdfAction(new URL(invoice_att2_file_name)))));
		                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_3,small_black_bold)));
		                */
		                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_4,small_black_normal)));
		                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
		                
		                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_5,small_black_normal)));
		                
		                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_6,small_black_normal)));
		                
		                BillingFieldsDetailsTable_2.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingFieldsDetailsTable_2.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_2.addCell(new Phrase(new Chunk(field_7,small_black_normal)));
		                
		                float[] BillingFieldsDetailsWidths_3 = {0.06f, 0.40f,0.10f, 0.15f, 0.15f, 0.15f};
		                PdfPTable BillingFieldsDetailsTable_3 = new PdfPTable(BillingFieldsDetailsWidths_3);
		                BillingFieldsDetailsTable_3.setWidthPercentage(100);
		                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(""+(++sr_no)+" ",small_black_bold)));
		                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		    				BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(" Net Amount Payable",small_black_bold)));
		                
		    			/*BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                */
		                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk("Rupees",small_black_bold)));
		                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                BillingFieldsDetailsTable_3.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingFieldsDetailsTable_3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingFieldsDetailsTable_3.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                
		                BillingFieldsDetailsTable_3.addCell(new Phrase(new Chunk(nf7.format(Double.parseDouble(netAmt+""))+" ",small_black_bold)));
		                
		                PdfPTable RemarkTable = new PdfPTable(1);
			            RemarkTable.setWidthPercentage(100);
			            RemarkTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            RemarkTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			            RemarkTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            RemarkTable.addCell(new Phrase(new Chunk(remark_1,small_black_normal)));
			            
			            PdfPTable RemarkTable2 = new PdfPTable(1);
			            RemarkTable.setWidthPercentage(100);
			            RemarkTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            RemarkTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			            RemarkTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
			            RemarkTable.addCell(new Phrase(new Chunk(remark_2,small_black_normal)));
		                
			            PdfPTable SignatureInfoTable = new PdfPTable(1);
						SignatureInfoTable.setWidthPercentage(100);
						SignatureInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
						SignatureInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
						SignatureInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
						//RG20190325 For Company change*/SignatureInfoTable.addCell(new Phrase(new Chunk("For Hazira LNG Private Limited\n\n\n\n\nAuthorised Signatory",black_bold)));
						SignatureInfoTable.addCell(new Phrase(new Chunk("For Shell Energy India Private Limited\n\n\n\n\nAuthorised Signatory",black_bold)));
						                       
			            
						PdfPTable GenerationInfoTable = new PdfPTable(1);
			            GenerationInfoTable.setWidthPercentage(100);
			            GenerationInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            GenerationInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			            GenerationInfoTable.addCell(new Phrase(new Chunk("FMS7",small_black_normal)));
			            
			            PdfPTable GenerationInfoTable2 = new PdfPTable(1);
			            GenerationInfoTable2.setWidthPercentage(100);
			            GenerationInfoTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			            GenerationInfoTable2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			            GenerationInfoTable2.addCell(new Phrase(new Chunk("This is computer generated INVOICE and hence does not require Authorised Signatory.",small_black_normal)));
			            
			            
		              //Adding All Cells To PDF Document - One By One ...
		                document.add(new Paragraph("              "));
		                document.add(new Paragraph("              "));
		                document.add(new Paragraph("              "));
		                document.add(new Paragraph("              "));
//		    			document.add(InvoiceTitleTable);
		    			document.add(InvoiceDescTable);
		    			document.add(InvoiceDescTable2);
		    			if(contract_type.equalsIgnoreCase("V"))
		    			{
		    				document.add(InvoiceNoteTable);
		    			}
		    			
		    			document.add(new Paragraph("              "));
		    			document.add(contact_addr_table);                        
		                document.add(GstCstInfoTable);            
		                document.add(new Paragraph("              "));
		                if(!irn_no.equals("") && (!qr_code.equals(""))){
		                	document.add(InvoiceDateInfoTable_qr);
		                }else{
		    	            document.add(InvoiceDateInfoTable);
		    	            document.add(InvoiceDueDateInfoTable);
		    	            document.add(InvoiceNOInfoTable);
//		    	            document.add(BillingPeriodInfoTable);
		                }
		                document.add(new Paragraph("              "));
		                document.add(BillingFieldsInfoTable);
		                document.add(BillingFieldsDetailsTable_2);
		                document.add(BillingFieldsDetailsTable_3);
		                document.add(new Paragraph("              "));
		                document.add(RemarkTable);
		                document.add(new Paragraph("              "));
		                document.add(RemarkTable2);
		                document.add(new Paragraph("              "));
		                document.add(SignatureInfoTable);          
		                
		                
		                // ********* Following Coding is for Showing Attachment-1 Sheet on the Next (2nd Page) of the Same PDF Document *********** //
		                document.setPageSize(pageSize1);
		                document.newPage();
		          //      document.open();
		                very_small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 5, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                Font small_black_normal_new = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                Font small_black_bold2 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                Font small_black_bold2_new = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                big_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                black_bold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                if(invoice_title.contains("CREDIT") ) //HS20160615-----font
		    			{
		                	very_small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                	small_black_normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                	small_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                	black_bold = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                	big_black_bold = FontFactory.getFont(FontFactory.HELVETICA, 15, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                	 small_black_normal_new = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL, new Color(0x00, 0x00, 0x00));
		                	 small_black_bold2 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD, new Color(0x00, 0x00, 0x00));
		                	 small_black_bold2_new = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.BOLD, new Color(0x00, 0x00, 0x00));
		    			}			  
		    			url_start = "http://"+server_nm+":"+server_port+context_nm;
		    						  
		                hlpl_logo.setBorder(Rectangle.NO_BORDER);
		                //hlpl_logo.scaleAbsolute(75,75);
		                hlpl_logo.scaleAbsolute(48,45);
		                PdfPCell hlpl_logo_cell = new PdfPCell(hlpl_logo,false);
		                hlpl_logo_cell.setBorder(Rectangle.NO_BORDER);
		    			
		                
		               // float[] hlpl_logo_Widths = {0.45f, 0.51f, 0.08f};
		                float[] hlpl_logo_Widths = {0.45f};
		                PdfPTable hlpl_logo_table = new PdfPTable(hlpl_logo_Widths);
		                hlpl_logo_table.setWidthPercentage(100);
//		                hlpl_logo_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//		                hlpl_logo_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
//		                hlpl_logo_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
//		                hlpl_logo_table.addCell(new Phrase(new Chunk(" ",small_black_bold)));
//		                hlpl_logo_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//		                hlpl_logo_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
//		                hlpl_logo_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
//		                hlpl_logo_table.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                hlpl_logo_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                hlpl_logo_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                hlpl_logo_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                hlpl_logo_table.addCell(hlpl_logo_cell);
		                
		    			addr_supl = "Registered Office:";
		    			Paragraph pp1=new Paragraph();
		    			pp1.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
		                
		                if(!contact_Suppl_Name.trim().equals(""))
		                {
		                	//addr_supl += "\n"+contact_Suppl_Name;    
		                	pp1.add(new Phrase(new Chunk("\n"+contact_Suppl_Name,small_black_normal)));
		                }
		                if(!tempcompname.trim().equals(""))
		                {
		                	//addr_supl += "\n"+contact_Suppl_Name;    
		                	pp1.add(new Phrase(new Chunk("\n"+tempcompname,small_black_normal2)));
		                }
		                
//		                if(!contact_Suppl_Name.trim().equals(""))
//		                {
//		                	addr_supl += "\n"+contact_Suppl_Name;     	
//		                }
		                if(!contact_Suppl_Person_Address.trim().equals(""))
		                {
		                	//addr_supl += "\n"+contact_Suppl_Person_Address;
		                	addr_supl = "\n"+contact_Suppl_Person_Address;
		                }
		                if(!contact_Suppl_Person_City.trim().equals(""))
		                {
		                	addr_supl += "\n"+contact_Suppl_Person_City;     	
		                }
		                if(!contact_Suppl_Person_Pin.trim().equals(""))
		                {
		                	addr_supl += " "+"-"+" "+contact_Suppl_Person_Pin;     	
		                }
		                pp1.add(new Phrase(new Chunk(addr_supl,small_black_normal)));
		    			
		    			addr_customer = "";
		                
		    			if(contact_Person_Name_And_Designation.trim().equalsIgnoreCase(contact_Customer_Name.trim()))
		    			{
		    				if(!contact_Customer_Name.trim().equals(""))
		    	            {
		    	            	addr_customer += contact_Customer_Name;    	
		    	            }
		    			}
		    			else
		    			{
		    	            if(!contact_Person_Name_And_Designation.trim().equals(""))
		    	            {
		    	            	addr_customer += contact_Person_Name_And_Designation;     	
		    	            }
		    	            if(!contact_Customer_Name.trim().equals(""))
		    	            {
		    	            	addr_customer += "\n"+contact_Customer_Name;     	
		    	            }
		    			}
		                if(!contact_Customer_Person_Address.trim().equals(""))
		                {
		                	addr_customer += "\n"+contact_Customer_Person_Address;     	
		                }
		                if(!contact_Customer_Person_City.trim().equals(""))
		                {
		                	addr_customer += "\n"+contact_Customer_Person_City;     	
		                }
		                if(!contact_Customer_Person_Pin.trim().equals(""))
		                {
		                	addr_customer += " "+"-"+" "+contact_Customer_Person_Pin;     	
		                }
		                            
		                          
		                float[] ContactAddrWidths2 = {0.25f, 0.55f, 0.20f};
		                contact_addr_table = new PdfPTable(ContactAddrWidths2);
		                contact_addr_table.setWidthPercentage(100);
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk(contact_Suppl_Name,black_bold)));
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk(" ",black_bold)));
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk("To:",black_bold)));
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                //contact_addr_table.addCell(new Phrase(new Chunk(addr_supl,small_black_normal)));
		                contact_addr_table.addCell(pp1);
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                contact_addr_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                contact_addr_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		                contact_addr_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                contact_addr_table.addCell(new Phrase(new Chunk(addr_customer,small_black_normal)));
		                String title_note="";
		                if(!invoice_title.contains("CREDIT")){
		                	 title_note = "ATTACHMENT 1 - Service Invoice Details"; 
		                }else{
		                    title_note = "ATTACHMENT 1 - Service Invoice Details";
		                }
		                
		                PdfPTable title_note_table = new PdfPTable(1);
		                title_note_table.setWidthPercentage(100);
		                title_note_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                title_note_table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                title_note_table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		                title_note_table.addCell(new Phrase(new Chunk(title_note,big_black_bold)));
		                
		                invno = "";
		                if(!new_inv_seq_no.equals("")) {
		                	invno = new_inv_seq_no;
		                } else {
		                	 if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		                 	{
		                 		if(hlpl_inv_no.length()>13)
		                 		{
		                 			invno = hlpl_inv_no.substring(0,10)+""+hlpl_inv_no.substring(12);
		                 		}      		
		                 	}
		                 	else
		                 	{
		                 		if(hlpl_inv_no.length()>13)
		                 		{
		                 			invno = hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
		                 		}       		
		                 	}
		                }
		               
		                
		    			inv_no_info = "";
		    			invoiceType = "";
		    			inv_dt_Header = "Invoice Date:";
		    			if(invoice_title.contains("CREDIT") ) //SB20160530
		    			{
		    				invoiceType = "Credit Note No:";
		    				inv_dt_Header = "";
		    				invno = hlpl_drcr_docNo;
		    				inv_due_dt_Header = "Credit Note Date:";
		    				customer_Invoice_Due_DT =hlpl_drcr_dt;
		    			}
		    			else
		    				invoiceType = "Invoice Seq No:";
		    			
		    			if(invoice_title.contains("CREDIT")){ //HS20160616
		    				inv_no_info = "SEIPL Credit Note No:";
		    			}else{
		    					inv_no_info = "SEIPL TAX Invoice Seq No:";
		    			}
		    			
//		    			float[] InvoiceDateInfoWidths3 = {0.45f, 0.21f, 0.13f, 0.08f, 0.13f};
		    			  float[] InvoiceDateInfoWidths3 = {0.25f, 0.55f, 0.20f};
		                InvoiceDateInfoTable = new PdfPTable(InvoiceDateInfoWidths3);
		                InvoiceDateInfoTable.setWidthPercentage(100);
		                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                if(invoice_title.contains("CREDIT")) //SB20160530
		                	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(inv_dt_Header,small_black_bold)));
		                else
		                	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                if(invoice_title.contains("CREDIT")|| invoice_title.contains("Sup")|| invoice_title.contains("DEBIT")   ) //SB20160611
		                {
		                	InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                	InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		    	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		    	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		    	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                }
		                else
		                {
		                	InvoiceDateInfoTable.addCell(new Phrase(new Chunk("Invoice Date:",small_black_bold)));
		    	            InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		    	            InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		    	            InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		    	            InvoiceDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_DT,small_black_normal)));
		                }
		                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                InvoiceDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                
		                float[] InvoiceDueDateInfoWidths3 = {0.25f, 0.55f, 0.20f};
		                InvoiceDueDateInfoTable = new PdfPTable(InvoiceDueDateInfoWidths3);
		                InvoiceDueDateInfoTable.setWidthPercentage(100);
		                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		         //SB20160601       InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk("Payment Due Date:",small_black_bold)));
		                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(inv_due_dt_Header,small_black_bold)));
		                
		                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_Due_DT,small_black_normal)));
		                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                InvoiceDueDateInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceDueDateInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceDueDateInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceDueDateInfoTable.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                
		               
		                String invno1 = "";
		                if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("C"))
		            	{
		            		if(hlpl_inv_no.length()>13)
		            		{
		            			////System.out.println("hlpl_inv_no---"+hlpl_inv_no+"----"+hlpl_inv_no.substring(0,5)+"---"+hlpl_inv_no.substring(7,10));
		            			if(!new_inv_seq_no.equals("")) {
		            				invno1 = new_inv_seq_no;
		            			} else {
		            				invno1 = hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
		            			}
		            		}
		            		
		            	}
		            	else
		            	{
		            		if(hlpl_inv_no.length()>13)
		            		{
		            			if(!new_inv_seq_no.equals("")) {
		            				invno1 = new_inv_seq_no;
		            			} else {
		            				invno1 = hlpl_inv_no.substring(0,5)+""+hlpl_inv_no.substring(7,10)+""+hlpl_inv_no.substring(12);
		            			}
		            		}
		            	}
		                
		                float[] InvoiceNOInfoWidths3 = {0.25f, 0.55f, 0.20f};
		                InvoiceNOInfoTable = new PdfPTable(InvoiceNOInfoWidths3);
		                InvoiceNOInfoTable.setWidthPercentage(100);
		                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(inv_no_info,small_black_bold)));
		                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(invno,small_black_normal)));
		                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                InvoiceNOInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                InvoiceNOInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                InvoiceNOInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                InvoiceNOInfoTable.addCell(new Phrase(new Chunk(" ",small_black_normal)));
		                            
		                
		                float[] BillingPeriodInfoWidths3 = {0.45f, 0.21f, 0.13f, 0.08f, 0.13f};
		                BillingPeriodInfoTable = new PdfPTable(BillingPeriodInfoWidths3);
		                BillingPeriodInfoTable.setWidthPercentage(100);
		                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(" ",small_black_bold)));
		                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                BillingPeriodInfoTable.addCell(new Phrase(new Chunk("For the Billing Period",small_black_bold)));
		                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(bill_period_start_dt,small_black_normal)));
		                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                BillingPeriodInfoTable.addCell(new Phrase(new Chunk("to",small_black_normal)));
		                BillingPeriodInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                BillingPeriodInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                BillingPeriodInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
		                BillingPeriodInfoTable.addCell(new Phrase(new Chunk(customer_Invoice_End_DT,small_black_normal)));
		               
		             
		                float[] AttachFieldsInfoWidths = null;
		                if(calcBase.equals("1") || calcBase.equals("3")){
		                	AttachFieldsInfoWidths = new float[] {0.10f, 0.10f,0.10f, 0.10f, 0.10f, 0.10f};
		                }else if(calcBase.equals("2")){
		                	AttachFieldsInfoWidths = new float[] {0.10f, 0.10f,0.10f, 0.10f, 0.10f, 0.10f, 0.10f};
		                }
		                
		                PdfPTable AttachFieldsInfoTable = new PdfPTable(AttachFieldsInfoWidths);
		                AttachFieldsInfoTable.setWidthPercentage(100);
		                
		                AttachFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                AttachFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                AttachFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                AttachFieldsInfoTable.addCell(new Phrase(new Chunk("Sr. No.",small_black_bold)));
		                
		                AttachFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                AttachFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                AttachFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                AttachFieldsInfoTable.addCell(new Phrase(new Chunk("Supply Date",small_black_bold)));
		                
		                AttachFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                AttachFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                AttachFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                AttachFieldsInfoTable.addCell(new Phrase(new Chunk("Truck No.",small_black_bold)));
		                
		                AttachFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                AttachFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                AttachFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                AttachFieldsInfoTable.addCell(new Phrase(new Chunk("Truck Quantity\n(MMBtu)",small_black_bold)));
		                
		                String curr_base="";
		                if(calcBase.equalsIgnoreCase("1")) {
		                	curr_base = "INR/MMBtu";
		                }else if(calcBase.equalsIgnoreCase("2")){
		                	curr_base = "INR/KM";
		                }else if(calcBase.equalsIgnoreCase("3")){
		                	curr_base = "INR/TRUCK";
		                }
		                
		                AttachFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                AttachFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                AttachFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                AttachFieldsInfoTable.addCell(new Phrase(new Chunk("Rate\n("+curr_base+")",small_black_bold)));
		                
		                if(calcBase.equals("2")) {
		                	
		                	AttachFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
			                AttachFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			                AttachFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
			                AttachFieldsInfoTable.addCell(new Phrase(new Chunk("Kilometer(s)",small_black_bold)));
		                }
		                
		                AttachFieldsInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                AttachFieldsInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                AttachFieldsInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                AttachFieldsInfoTable.addCell(new Phrase(new Chunk("Amount \n(INR)",small_black_bold)));
		                
//		                float[] AttachDataInfoWidths = {0.10f, 0.10f,0.10f, 0.10f, 0.10f, 0.10f};
//		                PdfPTable AttachDataInfoTable = new PdfPTable(AttachDataInfoWidths);
//		                AttachDataInfoTable.setWidthPercentage(100);
		                
		                float[] AttachDataInfoWidths = null;
		                if(calcBase.equals("1")  || calcBase.equals("3") ){
		                	AttachDataInfoWidths = new float[] {0.10f, 0.10f,0.10f, 0.10f, 0.10f, 0.10f};
		                }else if(calcBase.equals("2")){
		                	AttachDataInfoWidths = new float[] {0.10f, 0.10f,0.10f, 0.10f, 0.10f, 0.10f, 0.10f};
		                }
		                
		                PdfPTable AttachDataInfoTable = new PdfPTable(AttachDataInfoWidths);
		                AttachDataInfoTable.setWidthPercentage(100);
		                
		                for(int i = 0 ; i < View_truck_inv_no.size() ; i++){ 
		                	
		                	 int ind= i+1;
		                	 String truck_inv_dt = View_truck_inv_dt.elementAt(i)+"";
		                	 String truck_truck_nm = View_truck_nm.elementAt(i)+"";
		                	 String inv_qty = View_invoice_qty.elementAt(i)+"";
		                	 String rate = "";
		                	 if(calcBase.equals("3")){
		                		 rate = View_amount.elementAt(i)+"";
		                	 }else {
		                		 rate = View_rate.elementAt(i)+"";
		                	 }
		                	 String km = View_km.elementAt(i)+"";
		                	 String amt = View_amount.elementAt(i)+"";
		                	 
		                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk(ind+"",small_black_bold)));
		                	
		                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk(truck_inv_dt,small_black_bold)));
		                	
		                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk(truck_truck_nm,small_black_bold)));
		                	
		                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk(inv_qty,small_black_bold)));
		                	
		                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk(rate,small_black_bold)));
		                	
		                	 if(calcBase.equals("2")) {
		                		 
		                		 	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
				                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
				                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
				                	AttachDataInfoTable.addCell(new Phrase(new Chunk(km,small_black_bold)));
		                	 }
		                	 
		                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk(amt,small_black_bold)));
		                }
		                
		                AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	                	AttachDataInfoTable.getDefaultCell().setColspan(3);
	                	AttachDataInfoTable.addCell(new Phrase(new Chunk("Total",small_black_bold)));
		                
	                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
	                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
	                	AttachDataInfoTable.getDefaultCell().setColspan(1);
	                	AttachDataInfoTable.addCell(new Phrase(new Chunk(nf5.format(total_qty)+"",small_black_bold)));
	                	
	                	if(calcBase.equalsIgnoreCase("2")){
	                		
	                		AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                	AttachDataInfoTable.getDefaultCell().setColspan(2);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk("",small_black_bold)));
		                	
		                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
//		                	AttachDataInfoTable.getDefaultCell().setColspan(2);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk(nf7.format(total_amt)+"",small_black_bold)));
	                	
	                	}else {
	                		
	                		AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
		                	AttachDataInfoTable.getDefaultCell().setColspan(1);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk("",small_black_bold)));
		                	
		                	AttachDataInfoTable.getDefaultCell().setBorder(Rectangle.BOX);
		                	AttachDataInfoTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		                	AttachDataInfoTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
//		                	AttachDataInfoTable.getDefaultCell().setColspan(2);
		                	AttachDataInfoTable.addCell(new Phrase(new Chunk(nf7.format(total_amt)+"",small_black_bold)));
	                		
	                	}
		                
		                
		                document.add(new Paragraph("              "));
						//document.add(hlpl_logo_table);//RG20190516
						document.add(new Paragraph("              "));
						document.add(contact_addr_table);                        
			            document.add(new Paragraph("              "));
			            document.add(title_note_table);
			            document.add(new Paragraph("              "));
			            document.add(InvoiceDateInfoTable);
			            document.add(InvoiceDueDateInfoTable);
			            document.add(InvoiceNOInfoTable);
			            document.add(new Paragraph("              "));
			            document.add(BillingPeriodInfoTable);
			            document.add(new Paragraph("              "));
			            document.add(AttachFieldsInfoTable);
			            document.add(AttachDataInfoTable);
			            
			           /* document.add(ExchgRateDetailsTable);
			            document.add(new Paragraph("              "));
			            document.add(ExchgRateApplicableTable);
			            document.add(new Paragraph("              "));
			            document.add(BankSourceTable);
			            document.add(new Paragraph("              "));*/
			            document.add(new Paragraph("              "));
			            document.add(new Paragraph("              "));
		                
				}catch(DocumentException de)
				{
					System.err.println("DocumentException in printAllPdfFileForInvoice() Method :\n"+de.getMessage());
					de.printStackTrace();
				}
				catch(IOException ioe)
				{
					System.err.println("IOException in printAllPdfFileForInvoice() Method :\n"+ioe.getMessage());
					ioe.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
				}
				finally
				{
					document.close();
				}
		
		}

		private String createPdfFileForInvoice() {
			try {
			////System.out.println("START of PDF FILE NAME----------->>>>>>> "+invoice_pdf_path);
			HttpSession sess = request.getSession();
			invoice_pdf_path = sess.getAttribute("invoice_pdf_path").toString();
			System.out.println("invoice_title****"+invoice_title);
			String inv_title="";
			////System.out.println("invoice_title---------"+invoice_title);
			if(invoice_title.equalsIgnoreCase("ORIGINAL"))
			{
				inv_title="O";
			}
			else if(invoice_title.equalsIgnoreCase("DUPLICATE"))
			{
				inv_title="D";
			}
			else if(invoice_title.equalsIgnoreCase("TRIPLICATE"))
			{
				inv_title="T";
			}
			else if(invoice_title.equalsIgnoreCase("QUADRICATE"))
			{
				inv_title="Q";
			}
			else if(invoice_title.equalsIgnoreCase("CREDITO") )
			{
				inv_title="C";
			}
			else if(invoice_title.equalsIgnoreCase("SupO") )
			{
				inv_title="S";
			}
			//else if(invoice_title.equalsIgnoreCase("DEBIT") )
			else if(invoice_title.equalsIgnoreCase("DEBITO") )
			{
				inv_title="d";
			}
			else if(invoice_title.equalsIgnoreCase("CREDITD") )
			{
				inv_title="1";
			}
			else if(invoice_title.equalsIgnoreCase("CREDITT") )
			{
				inv_title="2";
			}
			else if(invoice_title.equalsIgnoreCase("SupD") )
			{
				inv_title="1";
			}
			else if(invoice_title.equalsIgnoreCase("DEBITD") )
			{
				inv_title="1";
			}
			else if(invoice_title.equalsIgnoreCase("DEBITT") )
			{
				inv_title="2";
			}
			
			fileName = "INVOICE-"+invoice_date.trim().substring(0,2)+invoice_date.trim().substring(3,5)+invoice_date.trim().substring(6)+"-"+customer_abbr+"-"+customer_plant_nm+"-"+contract_type+"-"+hlpl_inv_seq_no+"-"+inv_title+".pdf";
			f_nm="INVOICE-"+invoice_date.trim().substring(0,2)+invoice_date.trim().substring(3,5)+invoice_date.trim().substring(6)+"-"+customer_abbr+"-"+customer_plant_nm+"-"+contract_type+"-"+hlpl_inv_seq_no;
			inv_type_pdf=inv_title;
			invoice_pdf_path = invoice_pdf_path+"//"+fileName;
			System.out.println("--file invoice_pdf_path---"+invoice_pdf_path);
		
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return invoice_pdf_path;
		}
		
		public void updatetableForPdfDtlEntry()	throws SQLException	//BK20160303
		{
			PreparedStatement pstmt=null;
			try
			{
				////System.out.println("----------HS-------------");
				String pdf_inv_dtl="";
				String curr_date="";
				String inv_title="";
				
				////System.out.println("PDF-GEN: invoice_title: STARTED--------->>"+invoice_title);
				
				if(invoice_title.equalsIgnoreCase("ORIGINAL"))
				{
					inv_title="O";
				}
				else if(invoice_title.equalsIgnoreCase("DUPLICATE"))
				{
					inv_title="D";
				}
				else if(invoice_title.equalsIgnoreCase("TRIPLICATE"))
				{
					inv_title="T";
				}
				else if(invoice_title.equalsIgnoreCase("QUADRICATE")) //SB20160402
				{
					inv_title="Q";
				}
				else if(invoice_title.equalsIgnoreCase("CREDITO")) 
				{
					inv_title="C";
				}
				else if(invoice_title.equalsIgnoreCase("CREDITD")) 
				{
					inv_title="1";
				}
				else if(invoice_title.equalsIgnoreCase("CREDITT")) 
				{
					inv_title="2";
				}
				else if(invoice_title.equalsIgnoreCase("SupO") ) 
				{
					inv_title="S";
				}
				else if(invoice_title.equalsIgnoreCase("SupD") ) 
				{
					inv_title="1";
				}
				else if(invoice_title.equalsIgnoreCase("DEBITO") ) 
				{
					inv_title="d";
				}
				else if(invoice_title.equalsIgnoreCase("DEBITD") ) 
				{
					inv_title="1";
				}
				else if(invoice_title.equalsIgnoreCase("DEBITT") ) 
				{
					inv_title="2";
				}
				////System.out.println("PDF: INV-TITLE: "+inv_title);
				
				String user_cd = (String)session.getAttribute("user_cd")==null?"0":(String)session.getAttribute("user_cd");
				
				////System.out.println("---user_cd---"+user_cd);
				
				String invinvoicedt="";
				String q="select to_char(invoice_dt,'dd/mm/yyyy') from DLNG_INVOICE_MST where contract_type='"+contract_type+"' " +
							"and financial_year='"+inv_financial_year+"' and hlpl_inv_seq_no='"+hlpl_inv_seq_no+"'  and flag!='A'";
				System.out.println("---q---"+q);
				rset=stmt.executeQuery(q);
				if(rset.next())
				{
					curr_date=rset.getString(1);
					invinvoicedt=rset.getString(1);
				}
				
				//Date d1=new Date(invoice_bench_date);
				//Date d2=new Date(curr_date);
				
				String tempD1[]=invoice_bench_date.split("/");
				String d1=tempD1[2]+tempD1[1]+tempD1[0];
				
				String tempD2[]=curr_date.split("/");
				String d2=tempD2[2]+tempD2[1]+tempD2[0];
				
				String q1="";
				
				if(Integer.parseInt(d2)>Integer.parseInt(d1))
				{
					String query="select pdf_inv_dtl from DLNG_INVOICE_MST where contract_type='"+contract_type+"' " +
							"and financial_year='"+inv_financial_year+"' and hlpl_inv_seq_no='"+hlpl_inv_seq_no+"' and flag!='A'";
					System.out.println("---pdf_inv_dtl query--- "+query);
					rset=stmt.executeQuery(query);
					if(rset.next())
					{
						pdf_inv_dtl=rset.getString(1)==null?"":rset.getString(1);
					}
					if(pdf_inv_dtl.equalsIgnoreCase(""))
					{
						if(inv_title.equalsIgnoreCase("O"))
						{
							q1="update DLNG_INVOICE_MST set pdf_inv_dtl='"+inv_title+"',print_by_ori='"+user_cd+"',print_dt_ori=to_date(sysdate,'dd/mm/yy HH24:MI') where contract_type='"+contract_type+"' " +
									"and financial_year='"+inv_financial_year+"' and hlpl_inv_seq_no='"+hlpl_inv_seq_no+"' and flag!='A'";
						}
						else if(inv_title.equalsIgnoreCase("D"))
						{
							q1="update DLNG_INVOICE_MST set pdf_inv_dtl='"+inv_title+"',print_by_dup='"+user_cd+"',print_dt_dup=to_date(sysdate,'dd/mm/yy HH24:MI') where contract_type='"+contract_type+"' " +
									"and financial_year='"+inv_financial_year+"' and hlpl_inv_seq_no='"+hlpl_inv_seq_no+"' and flag!='A'";
						}
						else if(inv_title.equalsIgnoreCase("T"))
						{
							q1="update DLNG_INVOICE_MST set pdf_inv_dtl='"+inv_title+"',print_by_tri='"+user_cd+"',print_dt_tri=to_date(sysdate,'dd/mm/yy HH24:MI') where contract_type='"+contract_type+"' " +
									"and financial_year='"+inv_financial_year+"' and hlpl_inv_seq_no='"+hlpl_inv_seq_no+"' and flag!='A'";
						}
					else if(inv_title.equalsIgnoreCase("Q") || inv_title.equalsIgnoreCase("C") ||inv_title.equalsIgnoreCase("S") ||inv_title.equalsIgnoreCase("d") || inv_title.equalsIgnoreCase("1")  || inv_title.equalsIgnoreCase("2")) //SB20160402
						{
							q1="update DLNG_INVOICE_MST set pdf_inv_dtl='"+inv_title+"' where contract_type='"+contract_type+"' " +
								"and financial_year='"+inv_financial_year+"' and hlpl_inv_seq_no='"+hlpl_inv_seq_no+"' and flag!='A'";
						}
					/*SB20160616	if(contract_type.equalsIgnoreCase("T") || contract_type.equalsIgnoreCase("0"))
						{
							q1+=" and mapping_id='"+customer_inv_mapping_id+"'";
						}
						*/
						System.out.println("---update DLNG_INVOICE_MST query--- "+q1);
						stmt.executeUpdate(q1);
					}
					else
					{
						if(pdf_inv_dtl.contains(inv_title))
						{
							////System.out.println(inv_title+" :---IN IF---Already Exists");
						}
						else
						{
							pdf_inv_dtl+=inv_title;
							if(inv_title.equalsIgnoreCase("O"))
							{
								q1="update DLNG_INVOICE_MST set pdf_inv_dtl='"+pdf_inv_dtl+"',print_by_ori='"+user_cd+"',print_dt_ori=to_date(sysdate,'dd/mm/yy HH24:MI') where contract_type='"+contract_type+"' " +
									"and financial_year='"+inv_financial_year+"' and hlpl_inv_seq_no='"+hlpl_inv_seq_no+"' and flag!='A'";
							}
							else if(inv_title.equalsIgnoreCase("D"))
							{
								q1="update DLNG_INVOICE_MST set pdf_inv_dtl='"+pdf_inv_dtl+"',print_by_dup='"+user_cd+"',print_dt_dup=to_date(sysdate,'dd/mm/yy HH24:MI') where contract_type='"+contract_type+"' " +
									"and financial_year='"+inv_financial_year+"' and hlpl_inv_seq_no='"+hlpl_inv_seq_no+"' and flag!='A'";
							}
							else if(inv_title.equalsIgnoreCase("T"))
							{
								q1="update DLNG_INVOICE_MST set pdf_inv_dtl='"+pdf_inv_dtl+"',print_by_tri='"+user_cd+"',print_dt_tri=to_date(sysdate,'dd/mm/yy HH24:MI') where contract_type='"+contract_type+"' " +
									"and financial_year='"+inv_financial_year+"' and hlpl_inv_seq_no='"+hlpl_inv_seq_no+"' and flag!='A'";
							}
							else if(inv_title.equalsIgnoreCase("Q") || inv_title.equalsIgnoreCase("C") ||inv_title.equalsIgnoreCase("S") ||inv_title.equalsIgnoreCase("d") || inv_title.equalsIgnoreCase("1") || inv_title.equalsIgnoreCase("2")) //SB20160402
							{
								q1="update DLNG_INVOICE_MST set pdf_inv_dtl='"+pdf_inv_dtl+"' where contract_type='"+contract_type+"' " +
									"and financial_year='"+inv_financial_year+"' and hlpl_inv_seq_no='"+hlpl_inv_seq_no+"' and flag!='A'";
							}
							stmt.executeUpdate(q1);
						}
						
					}
				}
				
				int cnt=0;
				String Mapping_seq_no=contract_type+":"+inv_financial_year+":"+hlpl_inv_seq_no+":"+invinvoicedt;
				String query="select inv_name from LOG_FMS7_INV_FILE_DTL where INV_SEQ_NO='"+Mapping_seq_no+"' and inv_title='"+invoice_title+"'";
				rset=stmt.executeQuery(query);
				if(rset.next())
				{
					query="DELETE from LOG_FMS7_INV_FILE_DTL where INV_SEQ_NO='"+Mapping_seq_no+"' and inv_title='"+invoice_title+"'";
					stmt.executeUpdate(query);
				}
				
				
				File f=new File(invoice_pdf_path);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String file_date=sdf.format(f.lastModified());
				SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
				String file_time=sdf1.format(f.lastModified());
				query="INSERT INTO LOG_FMS7_INV_FILE_DTL (INV_SEQ_NO,INV_TITLE,INV_DT,INV_NAME,INV_SIZE,INV_TIME)" +
						" VALUES ('"+Mapping_seq_no+"','"+invoice_title+"',to_date('"+file_date+"','dd/mm/yyyy'),'"+invoice_pdf_path+"','"+f.length()+"','"+file_time+"')";
				////System.out.println("query.... "+query);
				stmt.executeUpdate(query);
				
				if(!f_nm.equalsIgnoreCase("")){
					
					String quer="select count(*) from dlng_inv_pdf_dtl where"
							+ " PDF_INV_NM='"+f_nm.trim()+"' and INV_TYPE='"+inv_type_pdf.trim()+"'";
					rset=stmt.executeQuery(quer);
					if(rset.next()){//HS20160618
						if(rset.getInt(1)>0){
							 pstmt = conn.prepareStatement("update dlng_inv_pdf_dtl   "
									+ " set  created_dt=sysdate,flag='Y' where PDF_INV_NM='"+f_nm.trim()+"' and  INV_TYPE='"+inv_type_pdf.trim()+"'");
							File blob = new File(invoice_pdf_path);
							pstmt.executeUpdate();
						}else{//HS20160618
							//String f_nm=f_nm;
							 pstmt = conn.prepareStatement("insert into dlng_inv_pdf_dtl  "
									+ "  (PDF_INV_NM,created_dt,INV_TYPE,flag) values ('"+f_nm.trim()+"',sysdate,'"+inv_type_pdf.trim()+"','Y') ");
							
							File blob = new File(invoice_pdf_path);
							pstmt.executeUpdate();
							
						}
					}
							
				}
				conn.commit();	
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally{
				if (pstmt != null) { 
		    	     pstmt.close(); 
		    	    }
			}
		}
		
		public void setInvoice_pdf_path(String invoice_pdf_path) {
			this.invoice_pdf_path = invoice_pdf_path;
		}
		public void setInvoice_title(String invoice_title) {
			this.invoice_title = invoice_title;
		}
		public void setInvoice_date(String invoice_date) {
			this.invoice_date = invoice_date;
		}
		public void setCustomer_abbr(String customer_abbr) {
			this.customer_abbr = customer_abbr;
		}

		public void setCustomer_plant_nm(String customer_plant_nm) {
			this.customer_plant_nm = customer_plant_nm;
		}

		public void setContract_type(String contract_type) {
			this.contract_type = contract_type;
		}

		public void setHlpl_inv_seq_no(String hlpl_inv_seq_no) {
			this.hlpl_inv_seq_no = hlpl_inv_seq_no;
		}

		public void setCallFlag(String callFlag) {
			this.callFlag = callFlag;
		}

		public void setRequest(HttpServletRequest request) {
			this.request = request;
		}

		public void setCustomer_Invoice_FGSA_Dt(String customer_Invoice_FGSA_Dt) {
			this.customer_Invoice_FGSA_Dt = customer_Invoice_FGSA_Dt;
		}

		public void setContact_Suppl_Name(String contact_Suppl_Name) {
			this.contact_Suppl_Name = contact_Suppl_Name;
		}

		public void setContact_Customer_Name(String contact_Customer_Name) {
			this.contact_Customer_Name = contact_Customer_Name;
		}

		public void setContact_Suppl_Person_Address(String contact_Suppl_Person_Address) {
			this.contact_Suppl_Person_Address = contact_Suppl_Person_Address;
		}

		public void setContact_Suppl_Person_City(String contact_Suppl_Person_City) {
			this.contact_Suppl_Person_City = contact_Suppl_Person_City;
		}

		public void setContact_Suppl_Person_Pin(String contact_Suppl_Person_Pin) {
			this.contact_Suppl_Person_Pin = contact_Suppl_Person_Pin;
		}

		public void setContact_customer_State(String contact_customer_State) {
			this.contact_customer_State = contact_customer_State;
		}

		public void setContact_customer_State_Code(String contact_customer_State_Code) {
			this.contact_customer_State_Code = contact_customer_State_Code;
		}

		public void setContact_Person_Name_And_Designation(String contact_Person_Name_And_Designation) {
			this.contact_Person_Name_And_Designation = contact_Person_Name_And_Designation;
		}

		public void setContact_Person_Name(String contact_Person_Name) {
			this.contact_Person_Name = contact_Person_Name;
		}

		public void setContact_Person_Desg(String contact_Person_Desg) {
			this.contact_Person_Desg = contact_Person_Desg;
		}

		public void setContact_Customer_Person_Address(String contact_Customer_Person_Address) {
			this.contact_Customer_Person_Address = contact_Customer_Person_Address;
		}

		public void setContact_Customer_Person_City(String contact_Customer_Person_City) {
			this.contact_Customer_Person_City = contact_Customer_Person_City;
		}

		public void setContact_Customer_Person_Pin(String contact_Customer_Person_Pin) {
			this.contact_Customer_Person_Pin = contact_Customer_Person_Pin;
		}

		public void setTempcompname(String tempcompname) {
			this.tempcompname = tempcompname;
		}

		public void setvSTAT_CD(Vector vSTAT_CD) {
			this.vSTAT_CD = vSTAT_CD;
		}

		public void setvSTAT_NM(Vector vSTAT_NM) {
			this.vSTAT_NM = vSTAT_NM;
		}

		public void setvSTAT_NO(Vector vSTAT_NO) {
			this.vSTAT_NO = vSTAT_NO;
		}

		public void setvSTAT_EFF_DT(Vector vSTAT_EFF_DT) {
			this.vSTAT_EFF_DT = vSTAT_EFF_DT;
		}

		public void setContact_Customer_GST_NO(String contact_Customer_GST_NO) {
			this.contact_Customer_GST_NO = contact_Customer_GST_NO;
		}

		public void setContact_Customer_CST_NO(String contact_Customer_CST_NO) {
			this.contact_Customer_CST_NO = contact_Customer_CST_NO;
		}

		public void setContact_Customer_GST_DT(String contact_Customer_GST_DT) {
			this.contact_Customer_GST_DT = contact_Customer_GST_DT;
		}

		public void setContact_Customer_CST_DT(String contact_Customer_CST_DT) {
			this.contact_Customer_CST_DT = contact_Customer_CST_DT;
		}

		public void setContact_Customer_GVAT_NO(String contact_Customer_GVAT_NO) {
			this.contact_Customer_GVAT_NO = contact_Customer_GVAT_NO;
		}

		public void setContact_Customer_GVAT_DT(String contact_Customer_GVAT_DT) {
			this.contact_Customer_GVAT_DT = contact_Customer_GVAT_DT;
		}

		public void setNew_inv_seq_no(String new_inv_seq_no) {
			this.new_inv_seq_no = new_inv_seq_no;
		}

		public void setCustomer_Invoice_DT(String customer_Invoice_DT) {
			this.customer_Invoice_DT = customer_Invoice_DT;
		}

		public void setCustomer_Invoice_Due_DT(String customer_Invoice_Due_DT) {
			this.customer_Invoice_Due_DT = customer_Invoice_Due_DT;
		}

		public void setCustomer_Invoice_Start_DT(String customer_Invoice_Start_DT) {
			this.customer_Invoice_Start_DT = customer_Invoice_Start_DT;
		}

		public void setCustomer_Invoice_End_DT(String customer_Invoice_End_DT) {
			this.customer_Invoice_End_DT = customer_Invoice_End_DT;
		}

		public void setContact_Suppl_Person_Phone(String contact_Suppl_Person_Phone) {
			this.contact_Suppl_Person_Phone = contact_Suppl_Person_Phone;
		}

		public void setContact_Suppl_Person_Fax(String contact_Suppl_Person_Fax) {
			this.contact_Suppl_Person_Fax = contact_Suppl_Person_Fax;
		}

		public void setContact_Suppl_Person_State(String contact_Suppl_Person_State) {
			this.contact_Suppl_Person_State = contact_Suppl_Person_State;
		}

		public void setContact_Suppl_Person_Country(String contact_Suppl_Person_Country) {
			this.contact_Suppl_Person_Country = contact_Suppl_Person_Country;
		}

		public void setContact_Suppl_GST_NO(String contact_Suppl_GST_NO) {
			this.contact_Suppl_GST_NO = contact_Suppl_GST_NO;
		}

		public void setContact_Suppl_Service_Tax_NO(String contact_Suppl_Service_Tax_NO) {
			this.contact_Suppl_Service_Tax_NO = contact_Suppl_Service_Tax_NO;
		}

		public void setContact_Suppl_Service_Tax_DT(String contact_Suppl_Service_Tax_DT) {
			this.contact_Suppl_Service_Tax_DT = contact_Suppl_Service_Tax_DT;
		}

		public void setContact_Suppl_PAN_NO(String contact_Suppl_PAN_NO) {
			this.contact_Suppl_PAN_NO = contact_Suppl_PAN_NO;
		}

		public void setContact_Suppl_PAN_DT(String contact_Suppl_PAN_DT) {
			this.contact_Suppl_PAN_DT = contact_Suppl_PAN_DT;
		}

		public void setContact_Suppl_GSTIN_NO(String contact_Suppl_GSTIN_NO) {
			this.contact_Suppl_GSTIN_NO = contact_Suppl_GSTIN_NO;
		}

		public void setContact_Suppl_GSTIN_DT(String contact_Suppl_GSTIN_DT) {
			this.contact_Suppl_GSTIN_DT = contact_Suppl_GSTIN_DT;
		}

		public void setContact_Suppl_State_Code(String contact_Suppl_State_Code) {
			this.contact_Suppl_State_Code = contact_Suppl_State_Code;
		}

		public void setContact_Suppl_State(String contact_Suppl_State) {
			this.contact_Suppl_State = contact_Suppl_State;
		}

		public void setSac_code(String sac_code) {
			this.sac_code = sac_code;
		}

		public void setSac_name(String sac_name) {
			this.sac_name = sac_name;
		}

		public void setSac_desc(String sac_desc) {
			this.sac_desc = sac_desc;
		}

		public void setVsac_cd(Vector vsac_cd) {
			Vsac_cd = vsac_cd;
		}

		public void setVitem_desc(Vector vitem_desc) {
			Vitem_desc = vitem_desc;
		}

		public void setVqty(Vector vqty) {
			Vqty = vqty;
		}

		public void setVrate(Vector vrate) {
			Vrate = vrate;
		}

		public void setVamt(Vector vamt) {
			Vamt = vamt;
		}

		public void setTax_size(String[] tax_size) {
			this.tax_size = tax_size;
		}

		public void setTaxnm_str(String taxnm_str) {
			this.taxnm_str = taxnm_str;
		}

		public void setGrossAmt(String grossAmt) {
			this.grossAmt = grossAmt;
		}

		public void setTax_structure(String tax_structure) {
			this.tax_structure = tax_structure;
		}

		public void setTotalTax(String totalTax) {
			this.totalTax = totalTax;
		}

		public void setNetAmt(String netAmt) {
			this.netAmt = netAmt;
		}

		public void setRemark_1(String remark_1) {
			this.remark_1 = remark_1;
		}

		public void setRemark_2(String remark_2) {
			this.remark_2 = remark_2;
		}

		public void setCalcBase(String calcBase) {
			this.calcBase = calcBase;
		}

		public void setBill_period_start_dt(String bill_period_start_dt) {
			this.bill_period_start_dt = bill_period_start_dt;
		}

		public void setBill_period_end_dt(String bill_period_end_dt) {
			this.bill_period_end_dt = bill_period_end_dt;
		}

		public void setView_amount(Vector view_amount) {
			View_amount = view_amount;
		}

		public void setView_invoice_qty(Vector view_invoice_qty) {
			View_invoice_qty = view_invoice_qty;
		}

		public void setView_km(Vector view_km) {
			View_km = view_km;
		}

		public void setView_rate(Vector view_rate) {
			View_rate = view_rate;
		}

		public void setView_service_inv_dt(Vector view_service_inv_dt) {
			View_service_inv_dt = view_service_inv_dt;
		}

		public void setView_truck_inv_dt(Vector view_truck_inv_dt) {
			View_truck_inv_dt = view_truck_inv_dt;
		}

		public void setView_truck_inv_no(Vector view_truck_inv_no) {
			View_truck_inv_no = view_truck_inv_no;
		}

		public void setView_truck_nm(Vector view_truck_nm) {
			View_truck_nm = view_truck_nm;
		}

		public void setTotal_qty(double total_qty) {
			this.total_qty = total_qty;
		}

		public void setTotal_amt(double total_amt) {
			this.total_amt = total_amt;
		}

		public String getInvoice_pdf_path() {
			return invoice_pdf_path;
		}

		public String getUrl_start() {
			return url_start;
		}

		public void setUrl_start(String url_start) {
			this.url_start = url_start;
		}

		public void setInv_financial_year(String inv_financial_year) {
			this.inv_financial_year = inv_financial_year;
		}
		public void setIrn_no(String irn_no) {
			this.irn_no = irn_no;
		}
		public void setIrn_flag(String irn_flag) {
			this.irn_flag = irn_flag;
		}
		public void setQr_code(String qr_code) {
			this.qr_code = qr_code;
		}
		public void setSigning_dt(String signing_dt) {
			this.signing_dt = signing_dt;
		}
		public void setTrans_cont_no(String trans_cont_no) {
			this.trans_cont_no = trans_cont_no;
		}
		public void setHlpl_drcr_docNo(String hlpl_drcr_docNo) {
			this.hlpl_drcr_docNo = hlpl_drcr_docNo;
		}
		public void setDr_cr_due_dt(String dr_cr_due_dt) {
			this.dr_cr_due_dt = dr_cr_due_dt;
		}
		public void setQty_unit(String qty_unit) {
			this.qty_unit = qty_unit;
		}
		public void setRate_unit(String rate_unit) {
			this.rate_unit = rate_unit;
		}
		public void setDrcr_criteria(String drcr_criteria) {
			this.drcr_criteria = drcr_criteria;
		}
		public void setTax_nm(Vector tax_nm) {
			this.tax_nm = tax_nm;
		}
		public void setTot_inv_qty(double tot_inv_qty) {
			this.tot_inv_qty = tot_inv_qty;
		}
		public void setTot_dr_cr_qty(double tot_dr_cr_qty) {
			this.tot_dr_cr_qty = tot_dr_cr_qty;
		}
		public void setTot_diff_qty(double tot_diff_qty) {
			this.tot_diff_qty = tot_diff_qty;
		}
		public void setInv_rate(double inv_rate) {
			this.inv_rate = inv_rate;
		}
		public void setDr_cr_rate(double dr_cr_rate) {
			this.dr_cr_rate = dr_cr_rate;
		}
		public void setDiff_rate(double diff_rate) {
			this.diff_rate = diff_rate;
		}
		public void setInv_tax_perc(Vector inv_tax_perc) {
			this.inv_tax_perc = inv_tax_perc;
		}
		public void setDrcr_tax_perc(Vector drcr_tax_perc) {
			this.drcr_tax_perc = drcr_tax_perc;
		}
		public void setDiff_tax_perc(Vector diff_tax_perc) {
			this.diff_tax_perc = diff_tax_perc;
		}
		public void setDrcr_gross_amt_inr(String drcr_gross_amt_inr) {
			this.drcr_gross_amt_inr = drcr_gross_amt_inr;
		}
		public void setDrcr_tax_amt(Vector drcr_tax_amt) {
			this.drcr_tax_amt = drcr_tax_amt;
		}
		public void setDrcr_tax_samt(double drcr_tax_samt) {
			this.drcr_tax_samt = drcr_tax_samt;
		}
		public void setDrcr_net_amt_inr(double drcr_net_amt_inr) {
			this.drcr_net_amt_inr = drcr_net_amt_inr;
		}
		public void setView_calc_bs(Vector view_calc_bs) {
			View_calc_bs = view_calc_bs;
		}
		public void setDrcr(String drcr) {
			this.drcr = drcr;
		}
		public void setView_dr_cr_qty(Vector view_dr_cr_qty) {
			View_dr_cr_qty = view_dr_cr_qty;
		}
		public void setView_diff_qty(Vector view_diff_qty) {
			View_diff_qty = view_diff_qty;
		}
		public void setGross_amt_inr(double gross_amt_inr) {
			this.gross_amt_inr = gross_amt_inr;
		}
		public void setFormated_inv_dt(String formated_inv_dt) {
			this.formated_inv_dt = formated_inv_dt;
		}
}
