package com.seipl.hazira.dlng.truck_master;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.seipl.hazira.dlng.util.RuntimeConf;
import com.seipl.hazira.dlng.util.escapeSingleQuotes;

@WebServlet("/servlet/Frm_TransporterMasterV2")
public class Frm_Transporter_MasterV2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	Statement stmt = null;
	Statement stmt1 = null;
	Statement stmt2 = null;
	ResultSet rset1 = null;
	ResultSet rset = null;
	public String url = "";
	public String msg = "";
	public String erro_msg = "";
	public String queryString ="";
	public String queryString1 ="";
	public String queryString2 = "";
	public Vector frm_truck_name = new Vector();
	public Vector frm_truck_type = new Vector();
	public Vector frm_effective_date = new Vector();
	public Vector frm_tank_volume = new Vector();
	public Vector frm_status = new Vector();
	public Vector truck_name = new Vector();
	
	public Frm_Transporter_MasterV2() { super();}
	public String FLAG = "T";	
	
	public static escapeSingleQuotes escObj = new escapeSingleQuotes();
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Context intialCont = new InitialContext();
			if(intialCont == null) {
				throw new Exception("Boom - No Context");
			}
			
			Context envCont = (Context) intialCont.lookup("java:/comp/env");
			DataSource ds = (DataSource) envCont.lookup(RuntimeConf.security_database);
			if(ds != null)
			{
				conn = ds.getConnection();
				if(conn != null) 
				{
					stmt = conn.createStatement();
					stmt1 = conn.createStatement();
					stmt2 = conn.createStatement();
					String flag = request.getParameter("btnflag")==null?"":request.getParameter("btnflag");
					String btn = request.getParameter("modifiers")==null?"":request.getParameter("modifiers");
					
					if(flag.equalsIgnoreCase("SAVE_TRANS_CONT_PERSON")){
						
						Insert_update_Entity_Contacts(request);
					}else {
						
						if(flag.equals("Y"))
						{
							if(btn.equals("delete")) 
							{
								System.out.println("Delete btn : "+btn);
								removeTransporter(request,response);
							}
							else
							{
								System.out.println("Update btn : "+btn);
								modifyTransporter(request,response);
							}
						}
						else
						{
							storTransporter(request,response);
						}
					}	
					conn.close();
					conn = null;
				}
			}
		} catch (Exception e) {
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
				rset = null;
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
				rset1 = null;
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
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				System.out.println("ServletException..."+e);
			}catch (IOException e) {
				System.out.println("IOException..."+e);
			}
	}

	public void Insert_update_Entity_Contacts(HttpServletRequest request) throws SQLException
	{
		String modCd = request.getParameter("modCd")==null?"":request.getParameter("modCd").toString();
		String formId = request.getParameter("formId")==null?"":request.getParameter("formId").toString();
		String subModUrl = request.getParameter("subModUrl")==null?"":request.getParameter("subModUrl").toString();
		String modUrl = request.getParameter("modUrl")==null?"":request.getParameter("modUrl").toString();
		
		String methodName = "Insert_update_Entity_Contacts()";
		HttpSession session = request.getSession();
		String EntityFlag = request.getParameter("EntityFlag")==null?"":request.getParameter("EntityFlag");
		String activity= request.getParameter("flg")==null?"":request.getParameter("flg");
		String Entity_Id= request.getParameter("trans_cd")==null?"":request.getParameter("trans_cd");
		String emp_cd= request.getParameter("emp_cd")==null?"":request.getParameter("emp_cd");
		String[] seq = request.getParameterValues("seq");
		String[] contact_nm = request.getParameterValues("contact_nm");
		String[] contact_desg = request.getParameterValues("contact_desg");
		String[] contact_phone = request.getParameterValues("contact_phone");
		String[] contact_mobile = request.getParameterValues("contact_mobile");
		String[] contact_fax1 = request.getParameterValues("contact_fax1");
		String[] contact_fax2 = request.getParameterValues("contact_fax2");
		String[] effctive_dt = request.getParameterValues("effective_dt");
		String[] NOM = request.getParameterValues("NOMT");
		String[] INV = request.getParameterValues("INVT");
		String[] FM = request.getParameterValues("FMT");
		String[] PM = request.getParameterValues("PMT");
		String[] JT = request.getParameterValues("JTT");
		String[] Active = request.getParameterValues("ActiveT");
		String[] Other = request.getParameterValues("OtherT");
					
		String[] addr = request.getParameterValues("addr");
		String[] user_add = request.getParameterValues("user_add");
		String[] contact_email = request.getParameterValues("contact_email");
		String entname = request.getParameter("transName")==null?"":request.getParameter("transName");
		String Name="";
		String perform = "";
		String query = "";
		
		/*if(activity.equalsIgnoreCase("insert"))
		{
			perform = "Submitted";
		}
		else
		{
			perform = "Updated";
		}*/
		perform = "Submitted";
		try
		{
			if(seq!=null)
			{
				if(EntityFlag.equalsIgnoreCase("tran"))
				{
					Name = "Transporter";
					for(int i=0;i<seq.length;i++)
					{
						query = "select count(*) from DLNG_TRANSPORTER_CONTACT_MST " +
								"where TRANS_CD='"+Entity_Id+"' AND " +
								"SEQ_NO='"+seq[i]+"' and EFF_DT=TO_DATE('"+effctive_dt[i]+"','dd/mm/yyyy')";
						rset = stmt.executeQuery(query);
						int count = 0;
						
						if(rset.next())
						{
							count = rset.getInt(1);
						}
						
						if(count>0)
						{
							query = "DELETE FROM DLNG_TRANSPORTER_CONTACT_MST WHERE TRANS_CD='"+Entity_Id+"' AND " +
									"SEQ_NO='"+seq[i]+"' and EFF_DT=TO_DATE('"+effctive_dt[i]+"','dd/mm/yyyy') ";
							stmt.executeUpdate(query);
						}
						
						String contact_person = escObj.replaceSingleQuotes(contact_nm[i]);
						String contact_designation = escObj.replaceSingleQuotes(contact_desg[i]);
						String user_address = escObj.replaceSingleQuotes(user_add[i]);
						
						query = "INSERT INTO DLNG_TRANSPORTER_CONTACT_MST (TRANS_CD, SEQ_NO," +
						 		" EFF_DT, CONTACT_PERSON, CONTACT_DESIG,PHONE, MOBILE," +
						 		" FAX_1, FAX_2, EMAIL, ADDR_FLAG," +
						 		" ADDL_ADDR_LINE,DEF_NOM_FLAG, " +
						 		" DEF_INV_FLAG, DEF_FM_FLAG, DEF_PM_FLAG, DEF_JT_FLAG, DEF_OTHER_FLAG,ACTIVE_FLAG,EMP_CD," +
						 		" ENT_DT, FLAG) VALUES(" +
						 		" '"+Entity_Id+"', '"+seq[i]+"', TO_DATE('"+effctive_dt[i]+"','dd/mm/yyyy'), '"+contact_person+"','"+contact_designation+"','"+contact_phone[i]+"'," +
						 		"'"+contact_mobile[i]+"','"+contact_fax1[i]+"','"+contact_fax2[i]+"','"+contact_email[i]+"'," +
						 		"'"+addr[i]+"','"+user_address+"','"+NOM[i]+"','"+INV[i]+"'," +
						 		"'"+FM[i]+"','"+PM[i]+"','"+JT[i]+"','"+Other[i]+"','"+Active[i]+"','"+emp_cd+"',sysdate,'"+FLAG+"') ";
						
						System.out.println("Transporter Contact Details Insertion Query = "+query);
						stmt.executeUpdate(query);
					}
				}
			}
			String msg = Name+" : "+entname+"'s Contact Details Successfully "+perform+" !!!";
			url= modUrl+"?msg="+msg+"&activity=update&Entity_Id="+Entity_Id+"&EntityFlag="+EntityFlag+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl;
			conn.commit();
			
			try
			{
				new com.seipl.hazira.dlng.util.InfoLogger().writelog("["+session.getAttribute("username")+"/"+session.getAttribute("ip")+"]: "+formId+"@~: "+msg);
			}
			catch(Exception infoLogger)
			{
				////System.out.println("Exception While Writing into InfoLogger Servlet From "+servletName+" Servlet -->\nUnder "+methodName+" Method -->\n"+infoLogger.getMessage());
				infoLogger.printStackTrace();
			}
		}
		catch(Exception e)
		{
			conn.rollback();
			msg = "Error: "+Name+ " : "+ entname +"'s Contact Details NOT "+perform+" !!!";
			////System.out.println("Exception In Frm_Entity Servlet --> Under Insert_update_Entity_Contacts() Method -->\n"+e.getMessage());
			e.printStackTrace();
			url= modUrl+"?msg="+msg+"&formId="+formId+"&modCd="+modCd+"&subModUrl="+subModUrl+"&modUrl="+modUrl;
		}
		System.out.println("url******"+url);
	}
	private void removeTransporter(HttpServletRequest request, HttpServletResponse response) {

		String flags=request.getParameter("btnflag")==null?"":request.getParameter("btnflag");
		String hid_trans_cd = request.getParameter("hid_trans_cd")==null?"":request.getParameter("hid_trans_cd");

		
		try {
			queryString2 = "UPDATE DLNG_TRANS_MST SET DEL_FLAG='Y' WHERE "
					+ " TRANS_CD='"+hid_trans_cd+"' ";
			System.out.print("***Query String 2**"+queryString2+"\n");
			stmt2.executeUpdate(queryString2);
			msg = "Deletion done for the selected Transportater";
			url = "../master/frm_mst_transportation.jsp?msg="+msg+"&flag="+flags;		
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			erro_msg = "Transportater details deletion failed..!";
			url = "../master/frm_mst_transportation.jsp?erro_msg="+erro_msg;
			
		}
		
		
	}

	private void modifyTransporter(HttpServletRequest request, HttpServletResponse response) {
		String transporter_name = request.getParameter("transporter_name")==null?"":request.getParameter("transporter_name");
		String effect_date = request.getParameter("eff_date")==null?"":request.getParameter("eff_date");
		String pan_no = request.getParameter("pan_no")==null?"":request.getParameter("pan_no");
		String pan_issue_date = request.getParameter("pan_issue_date")==null?"":request.getParameter("pan_issue_date");
		String address = request.getParameter("address")==null?"":request.getParameter("address");
		String state = request.getParameter("state")==null?"":request.getParameter("state");
		
		String cst_tin_no = request.getParameter("cst_tin_no")==null?"":request.getParameter("cst_tin_no");
		String cst_tin_dt = request.getParameter("cst_tin_dt")==null?"":request.getParameter("cst_tin_dt");
		String gst_tin_no = request.getParameter("gst_tin_no")==null?"":request.getParameter("gst_tin_no");
		String gst_tin_dt = request.getParameter("gst_tin_dt")==null?"":request.getParameter("gst_tin_dt");
		String tan_no = request.getParameter("tan_no")==null?"":request.getParameter("tan_no");
		String tan_dt = request.getParameter("tan_dt")==null?"":request.getParameter("tan_dt");
		String gstin_no = request.getParameter("gstin_no")==null?"":request.getParameter("gstin_no");
		String gstin_dt = request.getParameter("gstin_dt")==null?"":request.getParameter("gstin_dt");

		String status = request.getParameter("status_flag")==null?"":request.getParameter("status_flag");		
		String flags=request.getParameter("btnflag")==null?"":request.getParameter("btnflag");
		String hid_trans_cd = request.getParameter("hid_trans_cd")==null?"":request.getParameter("hid_trans_cd");
		
		try {
			
			queryString = "UPDATE DLNG_TRANS_MST SET "
						+ "EFF_DT=TO_DATE('"+effect_date+"','dd-MM-YYYY'),PAN_NO='"+pan_no+"',"
						+ "PAN_ISSUE_DT=TO_DATE('"+pan_issue_date+"','dd-MM-YYYY'),WEB_ADDR='"+address+"',"
						+ "GST_TIN_NO='"+gst_tin_no+"',GST_TIN_DT=TO_DATE('"+gst_tin_dt+"','dd-MM-YYYY'),"
						+ "CST_TIN_NO='"+cst_tin_no+"',CST_TIN_DT=TO_DATE('"+cst_tin_dt+"','dd-MM-YYYY'),"
						+ "TAN_NO='"+tan_no+"',TAN_ISSUE_DT=TO_DATE('"+tan_dt+"','dd-MM-YYYY'),"
						+ "GSTIN_NO='"+gstin_no+"',GSTIN_DT=TO_DATE('"+gstin_dt+"','dd-MM-YYYY'),FLAG='"+status+"' "
						+ "WHERE TRANS_CD='"+hid_trans_cd+"'  ";
			
			System.out.print("***Query String**"+queryString+"\n");
			stmt1.executeUpdate(queryString);
			msg = "Transporter details updation done successfully..!";
			url = "../master/frm_mst_transportation.jsp?msg="+msg+"&flag="+flags;
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			erro_msg = "Transporter details modification failed..!";
			url = "../master/frm_mst_transportation.jsp?erro_msg="+erro_msg;
		}
		
	}

	private void storTransporter(HttpServletRequest request, HttpServletResponse response) {
		
		String transporter_name = request.getParameter("transporter_name")==null?"":request.getParameter("transporter_name");
		String effect_date = request.getParameter("eff_date")==null?"":request.getParameter("eff_date");
		String pan_no = request.getParameter("pan_no")==null?"":request.getParameter("pan_no");
		String pan_issue_date = request.getParameter("pan_issue_date")==null?"":request.getParameter("pan_issue_date");
		String address = request.getParameter("address")==null?"":request.getParameter("address");
		String state = request.getParameter("state")==null?"":request.getParameter("state");
		String status = request.getParameter("status_flag")==null?"":request.getParameter("status_flag");
		
		String cst_tin_no = request.getParameter("cst_tin_no")==null?"":request.getParameter("cst_tin_no");
		String cst_tin_dt = request.getParameter("cst_tin_dt")==null?"":request.getParameter("cst_tin_dt");
		String gst_tin_no = request.getParameter("gst_tin_no")==null?"":request.getParameter("gst_tin_no");
		String gst_tin_dt = request.getParameter("gst_tin_dt")==null?"":request.getParameter("gst_tin_dt");
		String tan_no = request.getParameter("tan_no")==null?"":request.getParameter("tan_no");
		String tan_dt = request.getParameter("tan_dt")==null?"":request.getParameter("tan_dt");
		String gstin_no = request.getParameter("gstin_no")==null?"":request.getParameter("gstin_no");
		String gstin_dt = request.getParameter("gstin_dt")==null?"":request.getParameter("gstin_dt");

		try {
				int transporter_id = 1;
				
				String maxTrans_id = "select max(TRANS_CD) from DLNG_TRANS_MST";
				rset1 = stmt.executeQuery(maxTrans_id);
				if(rset1.next()) {
					transporter_id=rset1.getInt(1)+1;
				}
				
				
				String transporter_abbr = ""; 
		    	boolean v = true;
		    	for (int i = 0; i < transporter_name.length(); i++)  
		        {   
		            if (transporter_name.charAt(i) == ' ')  
		            { 
		                v = true; 
		            }    
		            else if (transporter_name.charAt(i) != ' ' && v == true)  
		            { 
		            	transporter_abbr += (transporter_name.charAt(i)); 
		                v = false; 
		            } 
		        } 
		    	
		    	System.out.println("transporter_abbr : "+transporter_abbr+"\n\n");
				
				
//				System.out.print("***transporter_id**"+transporter_id+"\n");
				
				  queryString = "INSERT INTO DLNG_TRANS_MST(TRANS_CD,TRANS_NAME,TRANS_ABBR,EFF_DT,PAN_NO,PAN_ISSUE_DT,WEB_ADDR,"
				  		      + "GST_TIN_NO,GST_TIN_DT,CST_TIN_NO,CST_TIN_DT,TAN_NO,TAN_ISSUE_DT,GSTIN_NO,GSTIN_DT,FLAG,DEL_FLAG)"
				  		      + "VALUES('"+transporter_id+"','"+ transporter_name+"','"+ transporter_abbr +"',"
				  		      + "TO_DATE('"+effect_date+"','dd-MM-YYYY'),'"+ pan_no +"',TO_DATE('"+pan_issue_date+"','dd-MM-YYYY'),"
				  		      + "'"+address+"',"+ "'"+gst_tin_no+"',TO_DATE('"+gst_tin_dt+"','dd-MM-YYYY'),"
				  		      + "'"+cst_tin_no+"',TO_DATE('"+cst_tin_dt+"','dd-MM-YYYY'),'"+tan_no+"',"
				  		      + " TO_DATE('"+tan_dt+"','dd-MM-YYYY'),'"+gstin_no+"',TO_DATE('"+gstin_dt+"','dd-MM-YYYY'),'"+status+"','N')";
				  
//				  System.out.print("***Query String**"+queryString+"\n");
				  stmt.executeUpdate(queryString);
				  msg = "Transportation details inserterd successfully "+transporter_name;
				  url = "../master/frm_mst_transportation.jsp?msg="+msg+"&flag=I";
				  conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				erro_msg = "Transportation details insertion failed..:"+transporter_name;
				url = "../master/frm_mst_transportation.jsp?erro_msg="+erro_msg;
			}
	}
}