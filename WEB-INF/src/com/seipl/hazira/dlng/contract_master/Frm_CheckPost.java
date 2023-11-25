package com.seipl.hazira.dlng.contract_master;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.seipl.hazira.dlng.util.RuntimeConf;

@WebServlet("/Frm_CheckPost")
public class Frm_CheckPost extends  HttpServlet{
	
	private Connection conn = null;
	private PreparedStatement ps1 = null;
	private PreparedStatement ps2 = null;
	private PreparedStatement ps3 = null;
	private PreparedStatement ps4 = null;
	private ResultSet rset = null;
	private ResultSet rset1 = null;
	private ResultSet rset2 = null;
	private ResultSet rset3 = null;
	
	private String option = "";
	private String msg = "";
	
	JSONObject json = new JSONObject();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			
			Context initContext = new InitialContext();
			if(initContext == null)
				throw new Exception("Boom - No Context");
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup(RuntimeConf.security_database);
			if(ds != null)
			{
				conn = ds.getConnection();
				conn.setAutoCommit(false);
				
				if(conn != null)
				{
					String status =  request.getParameter("status")==null?"":request.getParameter("status");
					option = request.getParameter("option")==null?"":request.getParameter("option");
					
					if(option.equalsIgnoreCase("fetchCust")) {
						
						response.setContentType("application/json");
						
						JSONObject custList = fetchCustomerDtl(request);
						String listJson = new Gson().toJson(custList);
//						System.out.println("json.toString()----"+listJson);
						response.getWriter().write(listJson);
						
					}else if(option.equalsIgnoreCase("fetchCheckPost")) {
						
						response.setContentType("application/json");
						
						JSONObject custList = fetchCustomerCheckPostDtl(request,"");
						String listJson = new Gson().toJson(custList);
//						System.out.println("json.toString()--1--"+listJson);
						response.getWriter().write(listJson);
						
					}else if(option.equalsIgnoreCase("saveCheckPostDtl")) {
						
						String msg =  saveCheckPostDtl(request);
						JSONObject custList = fetchCustomerCheckPostDtl(request,msg);
						String listJson = new Gson().toJson(custList);
//						System.out.println("json.toString()----"+listJson);
						response.getWriter().write(listJson);
					}else if(option.equalsIgnoreCase("updateCheckPostDtl")) {
//						System.out.println("********* in updateCheckPostDtl *********");
						String msg =  updateCheckPostDtl(request);
						JSONObject custList = fetchCustomerCheckPostDtl(request,msg);
						String listJson = new Gson().toJson(custList);
//						System.out.println("json.toString()----"+listJson);
						response.getWriter().write(listJson);
						
					}else if(option.equalsIgnoreCase("unlinkFromInvoiceLevel")) {
						System.out.println("-------unlinkFromInvoiceLevel----");
						
						response.setContentType("application/json");
						
						JSONObject checkPostList = unlinkFromInvoiceLevel(request);
						String listJson = new Gson().toJson(checkPostList);
//						System.out.println("json.toString()----"+listJson);
						response.getWriter().write(listJson);
					}
				}
			}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {

				if(rset != null)
				{
					try
					{
						rset.close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
				}
				if(rset1!=null)
				{
					try
					{
						rset1.close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
				}
				if(ps1!=null)
				{
					try
					{
						ps1.close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
					ps1 = null;
				}
				if(ps2!=null)
				{
					try
					{
						ps2.close();
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
					ps2 = null;
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
					}
					conn = null;
				}
			
			}
		}

	private JSONObject unlinkFromInvoiceLevel(HttpServletRequest request)throws SQLException,IOException {
		
		JSONObject json = new JSONObject();
		List<DataBean_CheckPost_Master> checkpostList = new ArrayList<>();
		
		String sn_no = request.getParameter("sn_no")==null?"":request.getParameter("sn_no");
		String fgsa_no = request.getParameter("fgsa_no")==null?"":request.getParameter("fgsa_no");
		String fgsa_rev_no = request.getParameter("fgsa_rev_no")==null?"":request.getParameter("fgsa_rev_no");
		String contract_type = request.getParameter("contract_type")==null?"":request.getParameter("contract_type");
		String customer_plant_seq_no = request.getParameter("customer_plant_seq_no")==null?"":request.getParameter("customer_plant_seq_no");
		String customer_cd = request.getParameter("customer_cd")==null?"":request.getParameter("customer_cd");
		int updCnt = 0;
		try {
			System.out.println("customer_cd--"+customer_cd+"-fgsa_no-"+fgsa_no+"-fgsa_rev_no-"+fgsa_rev_no+"-sn_no-"+sn_no+"-contract_type-"+contract_type+"-customer_plant_seq_no-"+customer_plant_seq_no);

			if(!sn_no.equalsIgnoreCase("") && !fgsa_no.equalsIgnoreCase("") && !fgsa_rev_no.equalsIgnoreCase("") && !customer_plant_seq_no.equalsIgnoreCase("")  && !contract_type.equalsIgnoreCase("")) {
				
				int cnt = 0 ; 
				String cntSql = "select count(*) from DLNG_LINK_CUST_CHECKPOST where "
						+ " CUSTOMER_CD = ?"
						+ " AND AGR_NO = ?"
						+ " AND AGR_REV_NO = ?"
						+ " AND CONT_NO = ?"
						+ " AND CONT_TYPE = ?"
						+ " AND PLANT_CD = ?"
						+ " AND STATUS = ?";
				ps1 = conn.prepareStatement(cntSql);
				ps1.setString(1, customer_cd);
				ps1.setString(2, fgsa_no);
				ps1.setString(3, fgsa_rev_no);
				ps1.setString(4, sn_no);
				ps1.setString(5, contract_type);
				ps1.setString(6, customer_plant_seq_no);
				ps1.setString(7, "Y");
				rset = ps1.executeQuery();
				if(rset.next()) {
					cnt = rset.getInt(1);
				}
//				System.out.println("cnt---"+cnt);
				if(cnt > 0) {
					
					String updSql = "update DLNG_LINK_CUST_CHECKPOST  set STATUS = ? "
							+ " where "
							+ " CUSTOMER_CD = ?"
							+ " AND AGR_NO = ?"
							+ " AND AGR_REV_NO = ?"
							+ " AND CONT_NO = ?"
							+ " AND CONT_TYPE = ?"
							+ " AND PLANT_CD = ?"
							+ " AND STATUS = ?";
					ps1 = conn.prepareStatement(updSql);
					ps1.setString(1, "N");
					ps1.setString(2, customer_cd);
					ps1.setString(3, fgsa_no);
					ps1.setString(4, fgsa_rev_no);
					ps1.setString(5, sn_no);
					ps1.setString(6, contract_type);
					ps1.setString(7, customer_plant_seq_no);
					ps1.setString(8, "Y");
//					System.out.println("ps1---"+updSql);
					updCnt = ps1.executeUpdate();
					if(updCnt > 0) {
						conn.commit();
						msg = "Success: All the checkpost are unlinked successfully";
					}else {
						msg = "Prevented: Failed to Unlinking the Checkpost";
					}
				}else {
					msg = "Prevented: No Record found for the Checkpost Unlinking";
				}
			}
		}catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			msg = "Prevented: Exception occured while Unlinking the Checkpost";
		}
//		System.out.println("updCnt----"+updCnt);
//		if(updCnt > 0) {
			checkpostList = fetchingAllCheckPost(msg);
//		}
		json.put("checkpostList", checkpostList);
		
		return json;
	}
	
	private List<DataBean_CheckPost_Master> fetchingAllCheckPost(String msg)throws SQLException,IOException{
		List<DataBean_CheckPost_Master> chkList = new ArrayList<DataBean_CheckPost_Master>();
		try {
			String fetchChkPostSql = "select CHKPOST_CD,CHKPOST_NAME from DLNG_CHECKPOST_MST order by CHKPOST_NAME";
			ps2 = conn.prepareCall(fetchChkPostSql);
			rset1 = ps2.executeQuery();
			while(rset1.next()) {
				
				String chkpost_cd = rset1.getString(1)==null?"":rset1.getString(1);
				String chkpost_name = rset1.getString(2)==null?"":rset1.getString(2);
				
				DataBean_CheckPost_Master dcm = new DataBean_CheckPost_Master(chkpost_cd, "", "",chkpost_name,msg,"");
				chkList.add(dcm);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return chkList;
	}
	private String updateCheckPostDtl(HttpServletRequest request)throws SQLException,IOException {
		
		String view_mapping_id = request.getParameter("cust_id")==null?"":request.getParameter("cust_id");
		String view_chkpost_cd = request.getParameter("view_chkpost_cd")==null?"":request.getParameter("view_chkpost_cd");
		String view_eff_dt = request.getParameter("eff_dt")==null?"":request.getParameter("eff_dt");
		String view_status = request.getParameter("view_status")==null?"":request.getParameter("view_status");
		String view_chkpost_name = request.getParameter("view_chkpost_name")==null?"":request.getParameter("view_chkpost_name");
		try {
			if(view_mapping_id.contains("-")) {
				
				String cust_dtl[] = view_mapping_id.split("-");
				String cust_cd = cust_dtl[0];
				String agr_no = cust_dtl[1];
				String agr_rev_no = cust_dtl[2]; 
				String cont_no =  cust_dtl[3];
				String cont_rev_no =  cust_dtl[4];
				String plant_no =  cust_dtl[5];
				String cont_type =  cust_dtl[6];
				
				String convt_eff_dt = com.seipl.hazira.dlng.util.UtilBean.formatDate(view_eff_dt,"dd/mm/yyyy","yyyy-mm-dd");
				String updStatus = "";
				if(view_status.equals("Y")) {
					updStatus = "N";
				}else if(view_status.equals("N")){
					updStatus = "Y";
				}
					String updSql = "update DLNG_LINK_CUST_CHECKPOST set status = ? where"
						+ " CUSTOMER_CD = ?"
						+ " AND EFF_DT = ?"
						+ " AND AGR_NO = ?"
						+ " AND AGR_REV_NO = ?"
						+ " AND CHKPOST_CD = ?"
						+ " AND CONT_NO = ?"
						+ " AND CONT_TYPE = ?"
						+ " AND PLANT_CD = ?";
					ps1 = conn.prepareStatement(updSql);
					ps1.setString(1, updStatus);
					ps1.setString(2, cust_cd);
					ps1.setDate(3, java.sql.Date.valueOf(convt_eff_dt));
					ps1.setString(4, agr_no);
					ps1.setString(5, agr_rev_no);
					ps1.setString(6, view_chkpost_cd);
					ps1.setString(7, cont_no);
					ps1.setString(8, cont_type);
					ps1.setString(9, plant_no);
					
					int updCnt = ps1.executeUpdate();
					if(updCnt > 0) {
						
						msg = "Success: Stauts updated for the checkpost :"+view_chkpost_name;
						conn.commit();
					}else {
						msg = "Prevented: No record found for the checkpost :"+view_chkpost_name+" to update" ;
					}
			}else {
				msg = "Prevented: Failed to update status for the checkpost :"+view_chkpost_name;
			}
			
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			msg = "Prevented: Exception while updating status for the checkpost :"+view_chkpost_name;
		}
		return msg;
	}

	private String saveCheckPostDtl(HttpServletRequest request)throws SQLException,IOException {
		
		String eff_dt = request.getParameter("eff_dt")==null?"":request.getParameter("eff_dt");
		String cust_id = request.getParameter("cust_id")==null?"":request.getParameter("cust_id");
		String checkPost = request.getParameter("checkPost")==null?"":request.getParameter("checkPost");
		String user_cd = request.getParameter("user_cd")==null?"":request.getParameter("user_cd");
		String status = request.getParameter("status")==null?"":request.getParameter("status");
		
		try {
			if(!eff_dt.equalsIgnoreCase("") && !cust_id.equalsIgnoreCase("") && cust_id.contains("-") && !checkPost.equals("")) {
				
				String cust_dtl[] = cust_id.split("-");
				String cust_cd = cust_dtl[0];
				String agr_no = cust_dtl[1];
				String agr_rev_no = cust_dtl[2]; 
				String cont_no =  cust_dtl[3];
				String cont_rev_no =  cust_dtl[4];
				String plant_no =  cust_dtl[5];
				String cont_type =  cust_dtl[6];
				int availCnt = 0;
				String convt_eff_dt = com.seipl.hazira.dlng.util.UtilBean.formatDate(eff_dt,"dd/mm/yyyy","yyyy-mm-dd");

				String chkAvailSql = "select count(*) from DLNG_LINK_CUST_CHECKPOST where "
						+ " CUSTOMER_CD = ?"
						+ " AND EFF_DT = ?"
						+ " AND AGR_NO = ?"
						+ " AND AGR_REV_NO = ?"
						+ " AND CHKPOST_CD = ?"
						+ " AND CONT_NO = ?"
						+ " AND CONT_TYPE = ?"
						+ " AND PLANT_CD = ?";
//						+ " AND STATUS = ?";
//				System.out.println("chkAvailSql----"+chkAvailSql);
					ps1 = conn.prepareStatement(chkAvailSql);
					ps1.setString(1, cust_cd);
					ps1.setDate(2, java.sql.Date.valueOf(convt_eff_dt));
					ps1.setString(3, agr_no);
					ps1.setString(4, agr_rev_no);
					ps1.setString(5, checkPost);
					ps1.setString(6, cont_no);
					ps1.setString(7, cont_type);
					ps1.setString(8, plant_no);
//					ps1.setString(9, status);
					rset = ps1.executeQuery();
					if(rset.next()) {
						availCnt = rset.getInt(1);
					}
//					System.out.println("availCnt-1---"+availCnt);
				if(availCnt == 0) {
					
					String insSql = "insert into DLNG_LINK_CUST_CHECKPOST (AGR_NO,AGR_REV_NO,CHKPOST_CD,CONT_NO,CONT_REV_NO,"
							+ " CONT_TYPE,CUSTOMER_CD,EFF_DT,ENT_BY,PLANT_CD,STATUS,ENT_DT)"
							+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,sysdate) ";
					ps2 = conn.prepareStatement(insSql);
					ps2.setString(1, agr_no);
					ps2.setString(2, agr_rev_no);
					ps2.setString(3, checkPost);
					ps2.setString(4, cont_no);
					ps2.setString(5, cont_rev_no);
					ps2.setString(6, cont_type);
					ps2.setString(7, cust_cd);
					ps2.setDate(8, java.sql.Date.valueOf(convt_eff_dt));
					ps2.setString(9, user_cd);
					ps2.setString(10, plant_no);
					ps2.setString(11, status);

					int insCnt = ps2.executeUpdate();
//					System.out.println("insCnt---"+insCnt);
					if(insCnt > 0) {
						conn.commit();
						msg = "Success: Selected Checkpost Successfully linked with the Customer :"+cust_id;
					}
				}else {
					String updSql = "update DLNG_LINK_CUST_CHECKPOST set status = ? where"
							+ " CUSTOMER_CD = ?"
							+ " AND EFF_DT = ?"
							+ " AND AGR_NO = ?"
							+ " AND AGR_REV_NO = ?"
							+ " AND CHKPOST_CD = ?"
							+ " AND CONT_NO = ?"
							+ " AND CONT_TYPE = ?"
							+ " AND PLANT_CD = ?";
						ps1 = conn.prepareStatement(updSql);
						ps1.setString(1, status);
						ps1.setString(2, cust_cd);
						ps1.setDate(3, java.sql.Date.valueOf(convt_eff_dt));
						ps1.setString(4, agr_no);
						ps1.setString(5, agr_rev_no);
						ps1.setString(6, checkPost);
						ps1.setString(7, cont_no);
						ps1.setString(8, cont_type);
						ps1.setString(9, plant_no);
						int updCnt = ps1.executeUpdate();
						if(updCnt > 0) {
							msg = "Success: Selected Checkpost Successfully linked with the Customer :"+cust_id;
							conn.commit();
						}else {
							msg = "Prevented: Failed to link Checkpost";
						}
				}
			}
		}catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			msg = "Prevented: Failed to link Checkpost";
		}
		return msg;
	}

	private JSONObject fetchCustomerCheckPostDtl(HttpServletRequest request,String msg)throws SQLException,IOException {
		
		JSONObject json = new JSONObject();
//		List<DataBean_CheckPost_Master> custList = new ArrayList<>();
		List<DataBean_CheckPost_Master> checkpostList = new ArrayList<>();
		List<DataBean_CheckPost_Master> availChkpostList = new ArrayList<>();
		
		String eff_dt = request.getParameter("eff_dt")==null?"":request.getParameter("eff_dt");
		String cust_id = request.getParameter("cust_id")==null?"":request.getParameter("cust_id");
		
		try {
			if(!eff_dt.equalsIgnoreCase("") && !cust_id.equalsIgnoreCase("") && cust_id.contains("-")) {
//				System.out.println("eff_dt-inn--"+eff_dt);
				/* fetching checkpost details */
				String cust_dtl[] = cust_id.split("-");
				String cust_cd = cust_dtl[0];
				String agr_no = cust_dtl[1];
				String agr_rev_no = cust_dtl[2]; 
				String cont_no =  cust_dtl[3];
				String plant_no =  cust_dtl[5];
				String cont_type =  cust_dtl[6];
				
				int stsCnt = 0;
				
				String fetchChkPostSql = "select CHKPOST_CD,CHKPOST_NAME from DLNG_CHECKPOST_MST order by CHKPOST_NAME";
				ps2 = conn.prepareCall(fetchChkPostSql);
				rset1 = ps2.executeQuery();
				while(rset1.next()) {
					
					String chkpost_cd = rset1.getString(1)==null?"":rset1.getString(1);
					String chkpost_name = rset1.getString(2)==null?"":rset1.getString(2);
					
					int availCnt = 0 ;
					String convt_eff_dt = com.seipl.hazira.dlng.util.UtilBean.formatDate(eff_dt,"dd/mm/yyyy","yyyy-mm-dd");
					
					String chkAvailSql = "select count(*) from DLNG_LINK_CUST_CHECKPOST where "
							+ " CUSTOMER_CD = '"+cust_cd+"'"
//							+ " AND EFF_DT = ?"
							+ " AND AGR_NO = '"+agr_no+"' "
							+ " AND AGR_REV_NO = '"+agr_rev_no+"'"
							+ " AND CHKPOST_CD = '"+chkpost_cd+"'"
							+ " AND CONT_NO = '"+cont_no+"'"
							+ " AND CONT_TYPE = '"+cont_type+"'"
							+ " AND PLANT_CD = '"+plant_no+"'" 
							+ " AND STATUS = 'Y'";
//					System.out.println("chkAvailSql----"+chkAvailSql);
					ps1 = conn.prepareStatement(chkAvailSql);
					rset = ps1.executeQuery();
					if(rset.next()) {
						availCnt = rset.getInt(1);
					}
//					System.out.println("availCnt----"+availCnt);
					if(availCnt == 0) {
						
						DataBean_CheckPost_Master dcm = new DataBean_CheckPost_Master(chkpost_cd, chkpost_name);
						checkpostList.add(dcm);
						
					}else {
						
						String fetchChkDtlSql = "select A.CHKPOST_CD,to_char(A.EFF_DT,'dd/mm/yyyy'),A.STATUS,B.CHKPOST_NAME "
								+ " from DLNG_LINK_CUST_CHECKPOST A,DLNG_CHECKPOST_MST B where "
								+ " A.CUSTOMER_CD = ?"
								+ " AND A.AGR_NO = ?"
								+ " AND A.AGR_REV_NO = ?"
								+ " AND A.CHKPOST_CD = ?"
								+ " AND A.CONT_NO = ?"
								+ " AND A.CONT_TYPE = ?"
								+ " AND A.PLANT_CD = ?"
								+ " AND A.STATUS = ?"
								+ " AND A.CHKPOST_CD = B.CHKPOST_CD";
						ps1 = conn.prepareStatement(fetchChkDtlSql);
						ps1.setString(1, cust_cd);
						ps1.setString(2, agr_no);
						ps1.setString(3, agr_rev_no);
						ps1.setString(4, chkpost_cd);
						ps1.setString(5, cont_no);
						ps1.setString(6, cont_type);
						ps1.setString(7, plant_no);
						ps1.setString(8, "Y");
						rset = ps1.executeQuery();
						while(rset.next()) {
//							stsCnt ++;
							String 	view_chkpost_cd = rset.getString(1)==null?"":rset.getString(1);
							String 	view_eff_dt = rset.getString(2)==null?"":rset.getString(2);
							String 	view_status = rset.getString(3)==null?"":rset.getString(3);
							String 	view_chkpost_name = rset.getString(4)==null?"":rset.getString(4);
							
							DataBean_CheckPost_Master dcm = new DataBean_CheckPost_Master(view_chkpost_cd, view_eff_dt, view_status, view_chkpost_name,msg,cust_id);
							availChkpostList.add(dcm);
						}
					}
				}
				System.out.println("checkpostList----"+checkpostList);
				/*
				 * if(stsCnt == 0) { DataBean_CheckPost_Master dcm = new
				 * DataBean_CheckPost_Master("", "", "", "",msg,""); availChkpostList.add(dcm);
				 * }
				 */
				json.clear();
				json.put("checkPostJson", checkpostList);
				json.put("viewChkPostJson", availChkpostList);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	private JSONObject fetchCustomerDtl(HttpServletRequest request)throws SQLException,IOException {
	
		String eff_dt = request.getParameter("eff_dt")==null?"":request.getParameter("eff_dt");
		List<DataBean_CheckPost_Master> custList = new ArrayList<>();
		List<DataBean_CheckPost_Master> checkpostList = new ArrayList<>();
		
		JSONObject json = new JSONObject();
		
		try {
			
			if(!eff_dt.equalsIgnoreCase("")) {
				
				/* for SN */
				String custSql = "SELECT CUSTOMER_CD,FLSA_NO,FLSA_REV_NO,SN_NO,SN_REV_NO,FCC_FLAG, SN_BASE FROM DLNG_SN_MST A "
						+ " WHERE "
						+ " A.start_dt<=TO_DATE('"+eff_dt+"','DD/MM/YYYY') "
						+ " AND A.end_dt>=TO_DATE('"+eff_dt+"','DD/MM/YYYY') "
						+ " AND A.status='Y'  "
						+ " AND A.sn_rev_no=(SELECT MAX(B.sn_rev_no) FROM DLNG_SN_MST B WHERE A.flsa_no=B.flsa_no AND A.sn_no=B.sn_no AND A.customer_cd=B.customer_cd AND"
						+ " B.start_dt<=TO_DATE('"+eff_dt+"','DD/MM/YYYY') "
						+ " AND B.end_dt>=TO_DATE('"+eff_dt+"','DD/MM/YYYY') AND B.status='Y')"
						+ " ORDER BY CUSTOMER_CD";
				System.out.println("custSql----"+custSql);
				ps1 = conn.prepareStatement(custSql);
				rset = ps1.executeQuery();
				while (rset.next()) {
					
					String custPlantSql = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_SN_PLANT_MST A " +
							   "WHERE A.customer_cd="+rset.getString(1)+" AND A.flsa_no="+rset.getString(2)+" " +
							   "AND A.sn_no="+rset.getString(4)+"";
					ps2 = conn.prepareStatement(custPlantSql);
					rset1 = ps2.executeQuery();
					while (rset1.next()) {
						
						String plantNmSql = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
								   "WHERE A.customer_cd="+rset.getString(1)+" AND A.seq_no="+rset1.getString(1)+" " +
								   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
								   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
								   "AND B.eff_dt<=TO_DATE('"+eff_dt+"','DD/MM/YYYY'))";
						ps3 = conn.prepareStatement(plantNmSql);
						rset2 = ps3.executeQuery();
						if (rset2.next()) {
							
							String cust_code = rset.getString(1)==null?"":rset.getString(1);
							String cont_no = rset.getString(4)==null?"":rset.getString(4);
							String cont_rev_no = rset.getString(5)==null?"":rset.getString(5);
							String plant_nm = rset2.getString(1)==null?"":rset2.getString(1);
							String agr_no = rset.getString(2)==null?"":rset.getString(2);
							String agr_rev_no = rset.getString(3)==null?"":rset.getString(3);
							String plant_no = rset1.getString(1)==null?"":rset1.getString(1);
							String cust_abbr = "";
							String mapping_id = cust_code+"-"+agr_no+"-"+agr_rev_no+"-"+cont_no+"-"+cont_rev_no+"-"+plant_no+"-S";
							String cont_nm = "SN";
							
							String custAbbrSql = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A "
									+ " WHERE A.customer_cd='"+cust_code+"' "
									+ " AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
									+ " CUSTOMER_CD='"+cust_code+"' AND EFF_DT<=TO_DATE('"+eff_dt+"','DD/MM/YYYY')) ";
							ps4 = conn.prepareStatement(custAbbrSql);
							rset3 = ps4.executeQuery();
							if(rset3.next()) {
								cust_abbr = rset3.getString(1)==null?"":rset3.getString(1);
							}
							
							DataBean_CheckPost_Master dcm = new DataBean_CheckPost_Master(cust_code, cont_no, cont_rev_no, plant_nm, agr_no, agr_rev_no, plant_no, cust_abbr, mapping_id,cont_nm);
							custList.add(dcm);
						}
					}
				}
				/* for LOA */
				String custLoASql = "SELECT CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO,A.FCC_FLAG,"
						+ " LOA_BASE FROM DLNG_LOA_MST A WHERE "
						+ " A.LOA_REV_NO=(SELECT MAX(B.LOA_REV_NO) FROM DLNG_LOA_MST B WHERE A.TENDER_NO=B.TENDER_NO AND A.LOA_NO=B.LOA_NO AND A.customer_cd=B.customer_cd)"
						+ " AND A.STATUS='Y'"
						+ " AND A.start_dt<=TO_DATE('"+eff_dt+"','DD/MM/YYYY') AND A.end_dt>=TO_DATE('"+eff_dt+"','DD/MM/YYYY')"
						+ " AND (A.LOA_CLOSURE_DT >= TO_DATE('"+eff_dt+"','DD/MM/YYYY') or A.LOA_CLOSURE_DT is null) "
						+ " ORDER BY CUSTOMER_CD,TENDER_NO,LOA_NO,LOA_REV_NO";
				ps1 = conn.prepareStatement(custLoASql);
				rset = ps1.executeQuery();
				while (rset.next()) {
					
					String custPlantSql = "SELECT DISTINCT(NVL(A.plant_seq_no,'1')) FROM DLNG_LOA_PLANT_MST A " +
							   "WHERE A.customer_cd="+rset.getString(1)+" AND A.TENDER_NO="+rset.getString(2)+" " +
							   "AND A.LOA_NO="+rset.getString(3)+" AND LOA_REV_NO = '"+rset.getString(4)+"'";
					ps2 = conn.prepareStatement(custPlantSql);
					rset1 = ps2.executeQuery();
					while (rset1.next()) {
						
						String plantNmSql = "SELECT NVL(A.plant_name,'PLANT1') FROM FMS7_CUSTOMER_PLANT_DTL A " +
								   "WHERE A.customer_cd="+rset.getString(1)+" AND A.seq_no="+rset1.getString(1)+" " +
								   "AND A.eff_dt=(SELECT MAX(B.eff_dt) FROM FMS7_CUSTOMER_PLANT_DTL B " +
								   "WHERE A.customer_cd=B.customer_cd AND A.seq_no=B.seq_no " +
								   "AND B.eff_dt<=TO_DATE('"+eff_dt+"','DD/MM/YYYY'))";
						ps3 = conn.prepareStatement(plantNmSql);
						rset2 = ps3.executeQuery();
						if (rset2.next()) {
							
							String cust_code = rset.getString(1)==null?"":rset.getString(1);
							String cont_no = rset.getString(3)==null?"":rset.getString(3);
							String cont_rev_no = rset.getString(4)==null?"":rset.getString(4);
							String plant_nm = rset2.getString(1)==null?"":rset2.getString(1);
							String agr_no = rset.getString(2)==null?"":rset.getString(2);
							String agr_rev_no = "0";
							String plant_no = rset1.getString(1)==null?"":rset1.getString(1);
							String cust_abbr = "";
							String mapping_id = cust_code+"-"+agr_no+"-"+agr_rev_no+"-"+cont_no+"-"+cont_rev_no+"-"+plant_no+"-L";
							String cont_nm = "LoA";
							
							String custAbbrSql = "SELECT NVL(A.customer_abbr,' ') FROM FMS7_CUSTOMER_MST A "
									+ " WHERE A.customer_cd='"+cust_code+"' "
									+ " AND EFF_DT=(SELECT MAX(EFF_DT) FROM FMS7_CUSTOMER_MST WHERE "
									+ " CUSTOMER_CD='"+cust_code+"' AND EFF_DT<=TO_DATE('"+eff_dt+"','DD/MM/YYYY')) ";
							ps4 = conn.prepareStatement(custAbbrSql);
							rset3 = ps4.executeQuery();
							if(rset3.next()) {
								cust_abbr = rset3.getString(1)==null?"":rset3.getString(1);
							}
							
							DataBean_CheckPost_Master dcm = new DataBean_CheckPost_Master(cust_code, cont_no, cont_rev_no, plant_nm, agr_no, agr_rev_no, plant_no, cust_abbr, mapping_id,cont_nm);
							custList.add(dcm);
						}
					}
				}
				json.put("custList", custList);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
