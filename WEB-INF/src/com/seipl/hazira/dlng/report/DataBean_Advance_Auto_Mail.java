package com.seipl.hazira.dlng.report;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class DataBean_Advance_Auto_Mail {

	public static String array_arg[] = {};
	public static void main(String args[])
	{
		DataBeanAdvanceReport dar=new DataBeanAdvanceReport();
		array_arg = args;
		dar.getmail_list();
		dar.init();
	}
}

class DataBeanAdvanceReport{
	
	Connection conn;
    Statement stmt,stmt1,stmt2,stmt3;
    ResultSet rset,rset1,rset2,rset3;
    String queryString="",queryString2="",queryString3="",queryString1="";
    String filePath="Reports/";      //For Linux
    //String logPath="C:\Excel Report\LogFiles/";    //For Windows
    String logPath="LogFiles/";      //For Linux
    String host="webmail.barodainformatics.com";
    String email_to = "";
//    String smtpPort = "587"; //For BIPL only
    String smtpPort = "25"; //For SEIPL only
    String from_mail="";
    String from_pwd="";
    String dbline="";
    String username="";
    String password="";
    String encrypted="";
    String user="";
    String pass="";
    String msg="";
    String Message_body = "";
	public boolean flag_1 = false, flag_2 = false;
	public void init()
	{
        try
        {
             Class.forName("oracle.jdbc.driver.OracleDriver");
             conn=DriverManager.getConnection(dbline,username,password);
                        
             if(conn != null)
             {
            	 conn.setAutoCommit(false);
                 stmt = conn.createStatement();
                 stmt1 = conn.createStatement();
                 stmt2 = conn.createStatement();
                 stmt3 = conn.createStatement();
                
                 int len = DataBean_Advance_Auto_Mail.array_arg.length;
//                System.out.println("len---"+len);
                 if(len > 0) {
                	 for(int i=0;i<len;i++) {
                    	 switch (Integer.parseInt(DataBean_Advance_Auto_Mail.array_arg[i])) {
						case 1:
							flag_1 = true;
//							send_mail_cumulative_report(UserCdEmail,UserNmEmail,UserEmailEmail,finalexclfile,finallogfile);
							break;
						case 2: 
							flag_2 = true;
//							send_mail_cumulative_report(UserCdEmail,UserNmEmail,UserEmailEmail,finalexclfile,finallogfile);
							break;
						case 3:
//							send_mail_cumulative_report(UserCdEmail,UserNmEmail,UserEmailEmail,finalexclfile,finallogfile);
							break;
						default:
//							send_mail_cumulative_report(UserCdEmail,UserNmEmail,UserEmailEmail,finalexclfile,finallogfile);
							break;
						}
                     }
                 } else {
                	 
                	 fetchInvCustDtl();
					 send_mail_cumulative_report("","","");
                 }
             }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        finally
        {
        	try
        	{
        		if(conn!=null)
            	{
            		conn.close();
            	}
            	if(stmt!=null)
            	{
            		stmt.close();
            	}
            	if(stmt1!=null)
            	{
            		stmt1.close();
            	}
            	if(stmt2!=null)
            	{
            		stmt2.close();
            	}
            	if(stmt3!=null)
            	{
            		stmt3.close();
            	}
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        }
    }
    public void send_mail_cumulative_report(String usercdEmail,String usernmEmail,String userEmailEmail) throws IOException, SQLException
    {   
    	try
    	{
//    		String from="hiren@barodainformatics.com";
//    		String pwd="Bipl@123";
    		    
		    String from=from_mail;
		    String pwd=from_pwd;
		    	
        	String subject="Advance Trigger Report";
        	
        	String bcc="";
        	String cc="";
        	String message=" Hello, \n Please find today's Advance Trigger Report herewith.";
        	
        	String excelfile=""; 
        	String excelfile1=""; 
        	
        	user=from;
        	pass=pwd;
        	
        	boolean flag1  = (boolean) postMailReport(email_to,subject,message,from,cc,bcc);
        	
	 	}
    	catch(Exception exception)
    	{
    		msg = "Email Sending Failed  ...";
    		//url = "../Invoice_xls_report/frm_cumulative_excel_reprt.jsp?msg="+msg;
//    		System.out.println(exception+"send_mail_cumulative_report");
    		exception.printStackTrace();
    	}
    }
    
    public boolean postMailReport(String recipientsTo, String subject, String message, String from,String recipientsCC,String recipientsBCC)
			throws MessagingException ,AuthenticationFailedException {
		Properties props = System.getProperties(); 	// OR Properties props = new Properties();
		props.setProperty("mail.smtp.host",host);
		props.setProperty("mail.smtp.auth","true");
		props.setProperty("mail.smtp.port", smtpPort);
	
		try 
		{
			Session session = Session.getInstance(props,new SMTPAuthenticator());
			MimeMessage msg = new MimeMessage(session);
			InternetAddress addressFrom = new InternetAddress(from);
			msg.setFrom(addressFrom);
			String	addressTo= recipientsTo;
			String	addressCC = recipientsCC;
			String addressBCC = recipientsBCC;
			
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			msg.setRecipients(Message.RecipientType.CC, addressCC);
			msg.setRecipients(Message.RecipientType.BCC, addressBCC);
			msg.setSubject(subject);
			Multipart multipart = new MimeMultipart();
			BodyPart mbp_file=new MimeBodyPart();
			
			mbp_file.setContent(message+"<br><br><br>"+Message_body, "text/html"); //ADDED FOR DAILY BALANCE QTY CALCULATION
			msg.saveChanges();
			multipart.addBodyPart(mbp_file);
			
			msg.setContent(multipart);
			msg.setSentDate(new Date());
			msg.saveChanges();
			Transport.send(msg);
		
		}		
		catch (Exception ex) 
		{			
			ex.printStackTrace();
			return false;
		}
		return true;
	}
    
    class SMTPAuthenticator extends Authenticator {
    	public PasswordAuthentication getPasswordAuthentication() {
    				return new PasswordAuthentication(user,pass);
    	}
    }
    
    
	Vector Vcust_cd = new Vector();
	Vector Vcust_nm = new Vector();
	Vector mapping_id = new Vector();
	Vector Vcust_abbr = new Vector();
	Vector transDate = new Vector();
	Vector Vcredit = new Vector();
	Vector Vdebit = new Vector();
	Vector Vclosing_bal = new Vector();
	Vector Vadv_flag = new Vector();
	
	
	String selMapId = "";
	String sysdate = "";
	NumberFormat nf = new DecimalFormat("###########0.00");
	NumberFormat nf2 = new DecimalFormat("###,###,###,##0.00");
	
	public void fetchInvCustDtl()throws SQLException,IOException {
		try {
			
			String sysDtSql = "select to_char(sysdate,'dd/mm/yyyy') from dual";
			rset = stmt.executeQuery(sysDtSql);
			if(rset.next()) {
				sysdate = rset.getString(1)==null?"":rset.getString(1);
			}
			String custCdSql="SELECT DISTINCT(CUSTOMER_CD),CUSTOMER_ABBR FROM FMS7_CUSTOMER_MST ORDER BY CUSTOMER_CD";
//			System.out.println("custCdSql***"+custCdSql);
			rset = stmt.executeQuery(custCdSql);
			while (rset.next()) {
				
				/*queryString1 = "SELECT FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
						+ " ,A.FCC_FLAG, SN_BASE,TO_CHAR(START_DT,'dd/mm/yyyy'),TO_CHAR(END_DT,'dd/mm/yyyy') "
						+ " FROM DLNG_SN_MST A WHERE "
						+ " CUSTOMER_CD = '"+rset.getString(1)+"'"
						+ " AND A.start_dt<= SYSDATE  "
						+ " AND A.end_dt>=SYSDATE AND A.status='Y' "
						+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND"
						+ " A.customer_cd=B.customer_cd AND  B.start_dt<=SYSDATE AND B.end_dt>=SYSDATE AND B.status='Y') ORDER BY CUSTOMER_CD";
						System.out.println("Fetching SN COntracts.."+queryString1);*/
				queryString1 = "SELECT FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),"
						+ " NVL(A.DCQ_MT,'0')  ,A.FCC_FLAG, SN_BASE,TO_CHAR(START_DT,'dd/mm/yyyy'),TO_CHAR(END_DT,'dd/mm/yyyy'),ADVANCE_COLLECTION_FLAG"
						+ " FROM DLNG_SN_MST A WHERE"
						+ " A.SN_REV_NO=(SELECT MAX(B.SN_REV_NO) FROM DLNG_SN_MST B WHERE A.SN_NO=B.SN_NO"
						+ " AND A.FLSA_NO=B.FLSA_NO AND A.FLSA_REV_NO=B.FLSA_REV_NO AND B.FLAG='T' AND A.CUSTOMER_CD=B.CUSTOMER_CD )"
						+ "	AND A.CUSTOMER_CD='"+rset.getString(1)+"'"
						+ " AND A.STATUS='Y' "
						+ " AND to_date(END_DT,'dd/mm/yyyy') >= to_date(sysdate,'dd/mm/yyyy') "
						+ " ORDER BY A.customer_cd,A.flsa_no,A.flsa_rev_no,A.sn_no";
//				System.out.println("queryString1-------"+queryString1);
				rset2 = stmt2.executeQuery(queryString1);
				while(rset2.next())
				{
					mapping_id.add("S-"+rset.getString(1)+"-"+rset2.getString(1)+"-"+rset2.getString(2)+"-"+rset2.getString(3)+"-"+rset2.getString(4)+"-"+rset2.getString(12)+"-"+rset2.getString(13));
					Vcust_abbr.add(rset.getString(2)+" "+"SN-"+rset2.getString(3));
					Vcust_nm.add(rset.getString(2)==null?"":rset.getString(2));
					Vadv_flag.add(rset2.getString(14)==null?"":rset2.getString(14));
					
				}
				/*queryString2 = "SELECT TENDER_NO,LOA_NO,LOA_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),NVL(A.DCQ_MT,'0') "
						+ " ,A.FCC_FLAG, LOA_BASE,TO_CHAR(START_DT,'dd/mm/yyyy'),TO_CHAR(END_DT,'dd/mm/yyyy') "
						+ " FROM DLNG_LOA_MST A WHERE"
						+ " CUSTOMER_CD = '"+rset.getString(1)+"'"
						+ " AND A.start_dt<=SYSDATE"
						+ " AND A.end_dt>=SYSDATE AND A.status='Y' "
						+ " AND A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND"
						+ " A.customer_cd=B.customer_cd AND  B.start_dt<=SYSDATE AND B.end_dt>=SYSDATE AND B.status='Y') ORDER BY CUSTOMER_CD";
							System.out.println("Fetching LoA COntracts.."+queryString2);*/
				
				queryString2 = "SELECT TENDER_NO,LOA_NO,LOA_REV_NO,NVL(A.dcq,'0'),NVL(A.mdcq_percentage,'100'),NVL(A.tcq,'0'),NVL(A.variation_qty,'0'),"
						+ " NVL(A.DCQ_MT,'0')  ,A.FCC_FLAG, LOA_BASE,TO_CHAR(START_DT,'dd/mm/yyyy'),TO_CHAR(END_DT,'dd/mm/yyyy'),ADVANCE_COLLECTION_FLAG"
						+ " FROM DLNG_LOA_MST A WHERE"
						+ " A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND A.customer_cd=B.customer_cd)"
						+ " AND A.CUSTOMER_CD='"+rset.getString(1)+"'"
						+ " AND A.STATUS='Y'"
						+ " AND to_date(END_DT,'dd/mm/yyyy') >= to_date(sysdate,'dd/mm/yyyy') "
						+ " ORDER BY CUSTOMER_CD";
				rset2 = stmt2.executeQuery(queryString2);
				while(rset2.next())
				{
					mapping_id.add("L-"+rset.getString(1)+"-"+rset2.getString(1)+"-0-"+rset2.getString(2)+"-"+rset2.getString(3)+"-"+rset2.getString(11)+"-"+rset2.getString(12));
					Vcust_abbr.add(rset.getString(2)+" "+"LoA-"+rset2.getString(2));
					Vcust_nm.add(rset.getString(2)==null?"":rset.getString(2));
					Vadv_flag.add(rset2.getString(13)==null?"":rset2.getString(13));
				}
			}
//			System.out.println("mapping_id----"+mapping_id);
			int k = 0;
			for(int i = 0; i < mapping_id.size() ; i++) {
				k=0;
				if(Vadv_flag.elementAt(i).equals("Y")){
					String temp_arr[] = mapping_id.elementAt(i).toString().split("-");
	//				System.out.println("mapping_id----"+mapping_id.elementAt(i));
					 transDate.clear();
					 Vcredit.clear();
					 Vdebit.clear();
					String contTyp = temp_arr[0];
					String custCd =  temp_arr[1];
					String flsaCd =  temp_arr[2];
					String flsaRev =  temp_arr[3];
					String snCd = temp_arr[4];
					String snRev = temp_arr[5];
					String start_dt = temp_arr[6];
					String end_dt = temp_arr[7];
	//				System.out.println("end_dt------"+end_dt);
					if(!end_dt.equals("") && !start_dt.equals("")) {
						
						String compEndDt = "select * from dual where TO_DATE('"+end_dt+"','DD/MM/YYYY') > sysdate ";
	//					System.out.println("compEndDt----"+compEndDt);
						rset = stmt.executeQuery(compEndDt);
						if(rset.next()) {
							end_dt = sysdate ; 	
						}
						
	//					System.out.println("end_dt------"+end_dt);
						String dateList = "SELECT to_char(to_date('"+start_dt+"', 'dd/mm/yyyy') + LEVEL - 1,'dd/mm/yyyy')"
								+ " FROM DUAL"
								+ " CONNECT BY LEVEL <= to_date('"+end_dt+"', 'dd/mm/yyyy') + 1 - to_date('"+start_dt+"', 'dd/mm/yyyy')";
	//					System.out.println("dateList******"+dateList);
						rset = stmt.executeQuery(dateList);
						while (rset.next()) {
							
							String selDate = rset.getString(1)==null?"":rset.getString(1);
							if(!selDate.equals("")) {
								
								if(k == 0) {
									/*checking for Advance for credit*/
									String checkAdvSql = "select nvl(sum(PAY_AMT),0) from DLNG_ADVC_PAY_MST"
											+ " where "
	//										+ " PAY_DT = to_date('"+selDate+"','dd/mm/yyyy')"
											+ " CUSTOMER_CD = '"+custCd+"'"
											+ " AND FLSA_NO = '"+flsaCd+"'"
											+ " AND SN_NO = '"+snCd+"'"
											+ " AND CONTRACT_TYPE = '"+contTyp+"' "
											+ " AND APPROVED_FLAG = 'Y' "
											+ " AND (DR_CR_FLAG = 'C' or DR_CR_FLAG is null)";
	//								System.out.println("checkAdvSql*********"+checkAdvSql);
									rset1 = stmt1.executeQuery(checkAdvSql);
									if (rset1.next()) {
										Vcredit.add(rset1.getString(1)==null?"0":rset1.getString(1));
										Vdebit.add("0.00");
										transDate.add(selDate);
//										System.out.println("rset1.getDouble(1)----"+rset1.getString(1));
									}
									
									/*checking for Advance for Debit*/
									String checkDebAdvSql = "select nvl(sum(PAY_AMT),0) from DLNG_ADVC_PAY_MST"
											+ " where "
	//										+ " PAY_DT = to_date('"+selDate+"','dd/mm/yyyy')"
											+ " CUSTOMER_CD = '"+custCd+"'"
											+ " AND FLSA_NO = '"+flsaCd+"'"
											+ " AND SN_NO = '"+snCd+"'"
											+ " AND CONTRACT_TYPE = '"+contTyp+"' "
											+ " AND APPROVED_FLAG = 'Y' "
											+ " AND DR_CR_FLAG = 'D' ";
	//								System.out.println("checkAdvSql*********"+checkAdvSql);
									rset1 = stmt1.executeQuery(checkDebAdvSql);
									if (rset1.next()) {
										Vcredit.add("0.00");
										Vdebit.add(rset1.getString(1)==null?"0":rset1.getString(1));
										transDate.add(selDate);
									}
								}
								/*checking for invoice*/
								String checkInvSql = "select NET_AMT_INR,NEW_INV_SEQ_NO,MAPPING_ID from DLNG_INVOICE_MST"
										+ " where  INVOICE_DT = to_date('"+selDate+"','dd/mm/yyyy')"
										+ " AND CUSTOMER_CD = '"+custCd+"'"
										+ " AND FGSA_NO = '"+flsaCd+"'"
										+ " AND SN_NO = '"+snCd+"'"
										+ " AND CONTRACT_TYPE = '"+contTyp+"' ";
	//							System.out.println("checkInvSql-------"+checkInvSql);
								rset1 = stmt1.executeQuery(checkInvSql);
								while (rset1.next()) {
									
									Vcredit.add("0.00");
									Vdebit.add(rset1.getString(1)==null?"0.00":rset1.getString(1));
									transDate.add(selDate);
									
									/*checking for Invoice HOLD Amount*/
									String map_id = rset1.getString(3)==null?"":rset1.getString(3);
									String temp_arr1[];
									String temp_mapId="";
									if(map_id.contains("-")) {
										
										temp_arr1 = map_id.split("-");
										temp_mapId = temp_arr1[0]+"-"+temp_arr1[1]+"-"+temp_arr1[2]+"-"+temp_arr1[3]+"-"+temp_arr1[4]+"-"+contTyp;
									}else {
										temp_mapId = "%-%-%-%-%-"+contTyp;
									}
	//								System.out.println("temp_mapId-------"+temp_mapId);
									String holdAmtSql = "select nvl(HOLD_AMOUNT,0),to_char(PAY_RECV_DT,'dd/mm/yyyy') from FMS8_PAY_RECV_DTL where NEW_INV_SEQ_NO = '"+rset1.getString(2)+"'"
											+ " and MAPPING_ID like '"+temp_mapId+"' "
											+ " and COMMODITY_TYPE='DLNG' "
											+ " and REV_NO = (select max(REV_NO) from FMS8_PAY_RECV_DTL where"
												+ " NEW_INV_SEQ_NO = '"+rset1.getString(2)+"' "
												+ " and MAPPING_ID like '"+temp_mapId+"'  and COMMODITY_TYPE='DLNG' )";
	//								System.out.println("holdAmtSql---Invoice---"+holdAmtSql);
									rset2 = stmt2.executeQuery(holdAmtSql);
									if(rset2.next()) {
										if(Double.parseDouble(rset2.getString(1)+"") > 0) {
											
											Vcredit.add("0.00");
											Vdebit.add(rset2.getString(1)==null?"0.00":rset2.getString(1));
											transDate.add(rset2.getString(2));
											
											/*checking for C-Form*/
											String CFormSql = "select A.CFORM_NO,to_char(B.CFORM_DT,'dd/mm/yyyy') "
													+ " from FMS7_CFORM_DTL A,FMS7_CFORM_MST B WHERE INVOICE_NO = '"+rset1.getString(2)+"' "
													+ " and A.CFORM_NO = B.CFORM_NO";
	//										System.out.println("CFormSql----"+CFormSql);
											rset3 = stmt3.executeQuery(CFormSql);
											if(rset3.next()) {
												
												Vcredit.add(rset2.getString(1)==null?"0.00":rset2.getString(1));
												Vdebit.add("0.00");
												transDate.add(rset3.getString(2)==null?"":rset3.getString(2));
											}
										}
									}
								}
								/*checking for debit/credit note*/
								String checkDrCrSql = "select DR_CR_NET_AMT_INR,DR_CR_DOC_NO,DR_CR_FLAG from "
										+ " DLNG_DR_CR_NOTE where"
										+ " DR_CR_DT = to_date('"+selDate+"','dd/mm/yyyy')"
										+ " AND CUSTOMER_CD = '"+custCd+"'"
										+ " AND FGSA_NO = '"+flsaCd+"'"
										+ " AND SN_NO = '"+snCd+"'"
										+ " AND CONTRACT_TYPE = '"+contTyp+"'"
										+ " AND APRV_BY > 0  ";
	//							System.out.pritln("checkDrCrSql----"+checkDrCrSql);
								rset1 = stmt1.executeQuery(checkDrCrSql);
								while (rset1.next()) {
									String drcr = "";
									if(rset1.getString(3).equalsIgnoreCase("dr")) {
										Vcredit.add("0.00");
										Vdebit.add(rset1.getString(1)==null?"0.00":rset1.getString(1));
										drcr = "Debit Note :";
									}else {
										Vdebit.add("0.00");
										Vcredit.add(rset1.getString(1)==null?"0.00":rset1.getString(1));
										drcr = "Credit Note :";
									}
									transDate.add(selDate);
									
									/*checking for Dr/Cr HOLD Amount*/
									
									String holdAmtSql = "select nvl(HOLD_AMOUNT,0),to_char(PAY_RECV_DT,'dd/mm/yyyy') from FMS8_PAY_RECV_DTL where "
											+ " NEW_INV_SEQ_NO = '"+rset1.getString(2)+"'  and COMMODITY_TYPE='DLNG' "
											+ " and REV_NO = (select max(REV_NO) from FMS8_PAY_RECV_DTL where"
												+ " NEW_INV_SEQ_NO = '"+rset1.getString(2)+"'  and COMMODITY_TYPE='DLNG' )";
	//								System.out.println("holdAmtSql--DR/CR----"+holdAmtSql);
									rset2 = stmt2.executeQuery(holdAmtSql);
									if(rset2.next()) {
										if(Double.parseDouble(rset2.getString(1)+"") > 0) {
											Vcredit.add("0.00");
											Vdebit.add(rset2.getString(1)==null?"0.00":rset2.getString(1));
											transDate.add(rset2.getString(2));
										}
									}
								}
							}
					k++;}
						double last_inv_amt = 0, mini_req_amt = 0;
						/*
						 * String fetchLastInv = "select nvl(NET_AMT_INR,0) from DLNG_INVOICE_MST where"
						 * + " CUSTOMER_CD = '"+custCd+"'" + " and FGSA_NO = '"+flsaCd+"'" +
						 * " and FGSA_REV_NO = '"+flsaRev+"'" + " and SN_NO = '"+snCd+"'" // +
						 * " and SN_REV_NO = '"+snRev+"'" +
						 * " and HLPL_INV_SEQ_NO = (select max(HLPL_INV_SEQ_NO) from DLNG_INVOICE_MST where"
						 * + "	CUSTOMER_CD = '"+custCd+"'" + " and FGSA_NO = '"+flsaCd+"'" +
						 * " and FGSA_REV_NO = '"+flsaRev+"'" + " and SN_NO = '"+snCd+"' " // +
						 * " and SN_REV_NO = '"+snRev+"'" + " )"; //
						 * System.out.println("fetchLastInv-----"+fetchLastInv); rset =
						 * stmt.executeQuery(fetchLastInv); if(rset.next()) { last_inv_amt =
						 * rset.getDouble(1); }
						 */
						double tax_perc = 0; 
						String fetchLastInv = "select nvl(TOTAL_QTY,'0'),TAX_STRUCT_CD,nvl(NET_AMT_INR,0),PLANT_SEQ_NO from DLNG_INVOICE_MST where"
								+ " CUSTOMER_CD = '"+custCd+"'"
								+ " and FGSA_NO = '"+flsaCd+"'"
								+ " and FGSA_REV_NO = '"+flsaRev+"'"
								+ " and SN_NO = '"+snCd+"'"
	//							+ " and SN_REV_NO = '"+snRev+"'"
								+ " AND CONTRACT_TYPE = '"+contTyp+"'"
								+ " and HLPL_INV_SEQ_NO = (select max(HLPL_INV_SEQ_NO) from DLNG_INVOICE_MST where"
									+ "	CUSTOMER_CD = '"+custCd+"'"
									+ " and FGSA_NO = '"+flsaCd+"'"
									+ " and FGSA_REV_NO = '"+flsaRev+"'"
									+ " and SN_NO = '"+snCd+"' "
									+ " AND CONTRACT_TYPE = '"+contTyp+"'"
	//								+ " and SN_REV_NO = '"+snRev+"'"
									+ " )";
//						System.out.println("fetchLastInv-----"+fetchLastInv);
						rset = stmt.executeQuery(fetchLastInv);
						if(rset.next()) {
							
							String tax_struct_cd = rset.getString(2)==null?"":rset.getString(2);
							String last_inv_qty = rset.getString(1)==null?"":rset.getString(1);
							last_inv_amt = rset.getDouble(3);
							String plant_seq_no = rset.getString(4)==null?"":rset.getString(4);
							
							 queryString = "SELECT NVL(A.tax_code,'0'), NVL(A.factor,'0.00'), NVL(A.tax_on,'1'), NVL(A.tax_on_cd,'0'), " +
									  "TO_CHAR(A.app_date,'DD/MM/YYYY') FROM FMS7_TAX_STRUCTURE_DTL A WHERE A.tax_str_cd="+tax_struct_cd+" AND " +
									  "A.app_date=(SELECT MAX(B.app_date) FROM FMS7_TAX_STRUCTURE_DTL B WHERE B.tax_str_cd="+tax_struct_cd+" AND " +
									  "B.app_date<=sysdate) ORDER BY A.tax_code";
		//						 System.out.println("queryString----"+queryString);
							rset1=stmt1.executeQuery(queryString);
							while(rset1.next())
							{
								tax_perc+=rset1.getDouble(2);  
							}
							
							/*for sales Rate*/
							String rateSql = "",sales_rate = "0";
							double var_sales_rate = 0; double ori_sale_price = 0;  
							
							queryString = "SELECT DISTINCT NEW_SALE_PRICE, ORI_SALE_PRICE FROM DLNG_CARGO_ALLOC_REVISED_DTL WHERE FLSA_NO="+flsaCd+" AND SN_NO="+snCd+" "
									  + " AND  CUSTOMER_CD="+custCd+" AND FLAG='A' AND MODIFICATION_SEQ_NO = (SELECT MAX(MODIFICATION_SEQ_NO) FROM DLNG_CARGO_ALLOC_REVISED_DTL WHERE FLSA_NO="+flsaCd+" AND SN_NO="+snCd+" "
									  + " AND  CUSTOMER_CD="+custCd+" AND FLAG='A' AND NEW_PRICE_EFF_DT <= SYSDATE)"
									  + " AND NEW_PRICE_EFF_DT <= SYSDATE ";
	//						System.out.println("QRY-01: Variable Sales Rate: "+queryString);
							rset1 = stmt1.executeQuery(queryString);
							if(rset1.next())
							{
								var_sales_rate = rset1.getDouble(1);
								ori_sale_price =  rset1.getDouble(2);
								sales_rate =""+var_sales_rate; 
							}
							if(var_sales_rate==0){
								
								queryString = "SELECT DISTINCT NEW_SALE_PRICE, ORI_SALE_PRICE FROM DLNG_CARGO_ALLOC_REVISED_DTL WHERE FLSA_NO="+flsaCd+" AND SN_NO="+snCd+" "
										  + " AND  CUSTOMER_CD="+custCd+" AND FLAG='A' AND MODIFICATION_SEQ_NO = (SELECT MAX(MODIFICATION_SEQ_NO) FROM DLNG_CARGO_ALLOC_REVISED_DTL WHERE FLSA_NO="+flsaCd+" AND SN_NO="+snCd+" "
										  + " AND  CUSTOMER_CD="+custCd+" AND FLAG='A' AND NEW_PRICE_EFF_DT >= SYSDATE )"
										  + " AND NEW_PRICE_EFF_DT >= SYSDATE ";
	//							System.out.println("QRY-02: Variable Sales Rate: "+queryString);
								rset1 = stmt1.executeQuery(queryString);
								if(rset1.next())
								{
									var_sales_rate = rset1.getDouble(1);
									ori_sale_price =  rset1.getDouble(2);
									sales_rate =""+ori_sale_price; 
								}
							}
							if(var_sales_rate==0){
								if(contTyp.equalsIgnoreCase("S")) {
									
									rateSql = "select nvl(RATE,0),nvl(DCQ,0) FROM DLNG_SN_MST WHERE"
											+ " CUSTOMER_CD='"+custCd+"' "
											+ " AND FLSA_NO='"+flsaCd+"' AND FLSA_REV_NO = '"+flsaRev+"'"
											+ " AND SN_NO='"+snCd+"' AND SN_REV_NO='"+snRev+"' ";
									
								}else if(contTyp.equalsIgnoreCase("L")) {
									
									rateSql = "select nvl(RATE,0),nvl(DCQ,0) FROM DLNG_LOA_MST WHERE"
											+ " CUSTOMER_CD='"+custCd+"' "
											+ " AND TENDER_NO='"+flsaCd+"' "
											+ " AND LOA_NO='"+snCd+"' AND LOA_REV_NO='"+snRev+"' ";
								}
							}
							if(!rateSql.equals("")) {
								rset1 = stmt1.executeQuery(rateSql);
								if(rset1.next()) {
									sales_rate = rset1.getString(1)==null?"0":rset1.getString(1);
	//								dcq = rset.getString(2)==null?"0":rset.getString(2);
								}
							}
							
							/*for exchange Rate*/
							String agrRevNo=flsaRev,exgContType="",exchg_rate_cd="",Previous_Available_Exchg_Date="",Previous_Exchg_Rate_Value="";
							if(contTyp.equals("L")) {
								
								agrRevNo = "0";
								exgContType = "T";
								
							}else if(contTyp.equals("S")){
								
								exgContType = "F";
							}
							
							queryString1 = "SELECT EXCHNG_RATE_CD FROM DLNG_SN_BILLING_DTL A, FMS7_CONT_EXCHG_RATE_MST B " +
									   "WHERE B.EXC_RATE_CD=A.EXCHNG_RATE_CD and cont_type='"+contTyp+"' AND customer_cd='"+custCd+"' AND " +
									   "flsa_no='"+flsaCd+"' AND flsa_rev_no='"+agrRevNo+"' AND " +
									   "sn_no='"+snCd+"' AND sn_rev_no='"+snRev+"' ";
							//System.out.println("Query To FindOut SN Exchange Rate Note = "+queryString1);
							rset1 = stmt1.executeQuery(queryString1);
							
							if(rset1.next())
							{
								exchg_rate_cd = rset1.getString(1)==null?"0":rset1.getString(1);
							}
							else
							{
								queryString2 = "SELECT EXCHNG_RATE_CD FROM DLNG_FLSA_BILLING_DTL A, FMS7_CONT_EXCHG_RATE_MST B " +
											   "WHERE B.EXC_RATE_CD=A.EXCHNG_RATE_CD and cont_type='"+exgContType+"' AND customer_cd='"+custCd+"' AND " +
											   "flsa_no='"+flsaCd+"' AND flsa_rev_no='"+agrRevNo+"' ";
					//			////System.out.println("Query To FindOut SN Exchange Rate Note = "+queryString2);
								rset2 = stmt2.executeQuery(queryString2);
								
								if(rset2.next())
								{
									exchg_rate_cd = rset2.getString(1)==null?"0":rset2.getString(1);
								}
							}
							
							queryString = "SELECT TO_CHAR(A.eff_dt,'DD/MM/YYYY') FROM FMS7_EXCHG_RATE_ENTRY A " +
							  "WHERE A.exchg_rate_cd="+exchg_rate_cd+" " +
							  "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_EXCHG_RATE_ENTRY B " +
							  "WHERE A.exchg_rate_cd=B.exchg_rate_cd AND " +
							  "B.eff_dt<= sysdate)";
	//						System.out.println("Query For Fetching Invoice Previous Available Exchange Day From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);				
							rset1 = stmt1.executeQuery(queryString);
							if(rset1.next())
							{
								Previous_Available_Exchg_Date = rset1.getString(1);
							}
							if(Previous_Available_Exchg_Date!=null && !Previous_Available_Exchg_Date.equals("") && !Previous_Available_Exchg_Date.equals(" "))
							{
								queryString = "SELECT NVL(A.exchg_val,'0') FROM FMS7_EXCHG_RATE_ENTRY A WHERE A.exchg_rate_cd="+exchg_rate_cd+" " +
								  			  "AND A.eff_dt=TO_DATE('"+Previous_Available_Exchg_Date+"','DD/MM/YYYY')";
	//							System.out.println("Query For Fetching Exchange Rate For Invoicing From FMS7_EXCHG_RATE_ENTRY Table = "+queryString);					
								rset1 = stmt1.executeQuery(queryString);
								if(rset1.next())
								{
									Previous_Exchg_Rate_Value = rset1.getString(1)==null?"":rset1.getString(1);
								}
							}
							double gross_amt = Double.parseDouble((last_inv_qty+"")) * Double.parseDouble(sales_rate+"") * Double.parseDouble(Previous_Exchg_Rate_Value+"");
//							System.out.println(last_inv_qty+"---"+sales_rate+"-----"+Previous_Exchg_Rate_Value+"---"+tax_perc);
							double tax_amt = (gross_amt * tax_perc) / 100;
							
							double maxPerc = 15;
							double diffTax = maxPerc - tax_perc;
							double hold_amt = 0;
							String cFormSql = "select CFORM_FLAG from FMS7_CFORM_CONTRACT_DTL where "
									+ " CUSTOMER_CD = '"+custCd+"'"
									+ " and AGR_NO = '"+flsaCd+"' and AGR_REV_NO = '"+flsaRev+"'"
									+ " and CONTRACT_NO = '"+snCd+"' and CONTRACT_REV_NO = '"+snRev+"'"
									+ " and COMMODITY_TYPE = 'DLNG' and CONTRACT_TYPE = '"+contTyp+"' "
									+ " and PLANT_SEQ_NO = '"+plant_seq_no+"'";
//							System.out.println("cFormSql----"+cFormSql);
							rset = stmt.executeQuery(cFormSql); 
							if(rset.next()) {
								String cFlg = rset.getString(1)==null?"":rset.getString(1);
								if(cFlg.equalsIgnoreCase("Y")) {
									if(diffTax > 0) { //calculate HOLD amount from Gross amount for this condition
										hold_amt = (gross_amt*diffTax) / 100;
			//							System.out.println("hold_amt--------"+hold_amt);
									}
								}
							}
							mini_req_amt = gross_amt + tax_amt + hold_amt;
						}
//						System.out.println("last_inv_amt****"+last_inv_amt+"--mini_req_amt--"+mini_req_amt);
						
						double closing_bal = 0,minimum_req_adv = 0,total_req_adv = 0;
						for(int j = 0 ; j < transDate.size() ; j++ ) {
							closing_bal+= Double.parseDouble(Vcredit.elementAt(j)+"");
							closing_bal-= Double.parseDouble(Vdebit.elementAt(j)+"");
						}
						double bal_perc = 0;
						if(last_inv_amt > 0) {
							bal_perc = (closing_bal*100)/last_inv_amt;
						}
						
						/*for mail body*/
						double top_up = 0;
						String bgColor = "";
						if(bal_perc > 100) {
							bgColor = "#32CD32";//lime green
							
						}else if(bal_perc >=5 && bal_perc <= 100 ) {
							
							bgColor = "#FFA500";//orange
							top_up = (mini_req_amt*10)/100;
							minimum_req_adv = mini_req_amt+top_up;
							
						}else if(bal_perc < 5) {
							
							bgColor = "#FF0000";//Red
							
							top_up = (mini_req_amt*10)/100;
							minimum_req_adv = mini_req_amt+top_up;
						}
						
						if(bal_perc <= 100) {
							if(closing_bal > minimum_req_adv) { 
								
								  total_req_adv = closing_bal - minimum_req_adv;
							  
							}else if(closing_bal < minimum_req_adv) { 
								
								  total_req_adv = minimum_req_adv - closing_bal;
							}
						}
						String temp_bal = "";
						String tem_req_amt = "";
						if(last_inv_amt <= 0 ) {
							bgColor = "#32CD32";//lime green
							temp_bal = "N/A";
							tem_req_amt = "N/A";
						}
//						System.out.println("closing_bal-mail--"+nf.format(closing_bal)+"**bal_perc**"+bal_perc+"**total_req_adv**"+total_req_adv);
//						System.out.println("temp_bal***"+temp_bal+"*****tem_req_amt***"+tem_req_amt);
						
						if(i==0) {
							
					        Message_body = "<br><table border='1' width='70%' style='font-size: x-small;' cellpadding='0' cellspacing='0'>";
					        Message_body += "<tr> <td colspan = '5'> <center ><u><b>Daily Advance Trigger Report</b></u></center></h4><br> </td></tr>";
					        Message_body += "<tr style='background-color: #E0EEE0'>";
						        Message_body += "<td  align='center'><b>Customer Name</b></td>";
						        Message_body += "<td  align='center'><b>Balance Amount(INR)</b></td>";
						        Message_body += "<td  align='center'><b>Balance (%)</b></td>";
						        Message_body += "<td  align='center'><b>Traffic Light</b></td>";
						        Message_body += "<td  align='center'><b>Minimum Required Advance (INR)</b></td>";
					        Message_body += "</tr>";
						}
						
						Message_body += "<tr style='background-color: white;font-size: x-small;' align='center'>";
							Message_body += "<td align='left'>&nbsp;"+Vcust_abbr.elementAt(i)+"</td>";
							Message_body += "<td align='right'>"+nf2.format(closing_bal)+"&nbsp;</td>";
							if(last_inv_amt <= 0 ) {
								Message_body += "<td align='right'>"+temp_bal+"&nbsp;%&nbsp;</td>";
								Message_body += "<td style='background-color:"+bgColor+"'>&nbsp; </td>";
								Message_body += "<td align='right'> "+tem_req_amt+"&nbsp;</td>";
							}else {
								Message_body += "<td align='right'>"+nf.format(bal_perc)+"&nbsp;%&nbsp;</td>";
								Message_body += "<td style='background-color:"+bgColor+"'>&nbsp; </td>";
								Message_body += "<td align='right'> "+nf2.format(total_req_adv)+"&nbsp;</td>";
							}
						Message_body += "</tr>";
						
						if(i == mapping_id.size()-1) {
							 Message_body += "<tr style='background-color: #E0EEE0'> <td colspan = '5' align='center'> <b>Legend</b></h4></td></tr>";
							Message_body += "<tr  style='background-color: white;font-size: x-small;' >";
								Message_body += "<td align='left' colspan = '5' style='background-color:#32CD32;color:white;'> Balance >= 100% : No call for advance before next truck. </td>";
							Message_body += "</tr>";
							
							Message_body += "<tr  style='background-color: white;font-size: x-small;' align='center'>";
								Message_body += "<td align='left' colspan = '5' style='background-color:#FFA500;color:white;'> 5% <= Balance < 100% : Call for advance before next truck.</td>";
							Message_body += "</tr>";
							
							Message_body += "<tr  style='background-color: white;font-size: x-small;' align='center'>";
								Message_body += "<td align='left' colspan = '5' style='background-color:#FF0000;color:white;'>Balance < 5% : Call for advance before next truck.</td>";
							Message_body += "</tr>";
							
							Message_body +=	"</table>";	
						}
						////
	//					System.out.println(Vcust_abbr.elementAt(i)+"*closing_bal****"+nf.format(closing_bal)+"***bal_perc**"+nf.format(bal_perc));
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getmail_list() {
		try
		{
			String strline = "";
			
			File fsetup=new File("sales_invoice/Setup.txt");
			String mail_list_path=fsetup.getAbsolutePath();
//			System.out.println("mail_list_path----"+mail_list_path);
			// mail_list_path = request.getRealPath("/amc/SETUP.txt");
			FileInputStream f1 = new FileInputStream(mail_list_path);
			DataInputStream in = new DataInputStream(f1);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
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
//                   CipherEncrypter encrypter = new CipherEncrypter("CompEncryptedDataSourceFactory");
//                   from_pwd = encrypter.decrypt(encrypted);

					from_pwd = tmp[1].toString();
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
//                     CipherEncrypter encrypter = new CipherEncrypter("CompEncryptedDataSourceFactory");
//                     password = encrypter.decrypt(encrypted);

					password = tmp[1].toString();
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("E.getmassage..."+e.getMessage());
			e.printStackTrace();
		}
	}
} 