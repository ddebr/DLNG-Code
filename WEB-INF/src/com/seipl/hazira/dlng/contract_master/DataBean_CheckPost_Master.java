package com.seipl.hazira.dlng.contract_master;

public class DataBean_CheckPost_Master {
	
	private String cust_code = "";
	private String cont_no = "";
	private String cont_rev_no ="";
	private String plant_nm = "";
	private String agr_no = "";
	private String agr_rev_no = "";
	private String plant_no = "";
	private String cust_abbr = "";
	private String mapping_id = "";
	private String cont_nm = "";
	private String chkpost_cd = "";
	private String chkpost_name = "";
	private String view_chkpost_cd = "";
	private String view_eff_dt = "";
	private String view_status = "" ;
	private String view_chkpost_name = "";
	private String view_mapping_id = "";
	private String msg = "";
	
	public String getCust_code() {
		return cust_code;
	}
	public void setCust_code(String cust_code) {
		this.cust_code = cust_code;
	}
	public String getCont_no() {
		return cont_no;
	}
	public void setCont_no(String cont_no) {
		this.cont_no = cont_no;
	}
	public String getCont_rev_no() {
		return cont_rev_no;
	}
	public void setCont_rev_no(String cont_rev_no) {
		this.cont_rev_no = cont_rev_no;
	}
	public String getPlant_nm() {
		return plant_nm;
	}
	public void setPlant_nm(String plant_nm) {
		this.plant_nm = plant_nm;
	}
	public String getAgr_no() {
		return agr_no;
	}
	public void setAgr_no(String agr_no) {
		this.agr_no = agr_no;
	}
	public String getAgr_rev_no() {
		return agr_rev_no;
	}
	public void setAgr_rev_no(String agr_rev_no) {
		this.agr_rev_no = agr_rev_no;
	}
	public String getPlant_no() {
		return plant_no;
	}
	public void setPlant_no(String plant_no) {
		this.plant_no = plant_no;
	}
	public String getCust_abbr() {
		return cust_abbr;
	}
	public void setCust_abbr(String cust_abbr) {
		this.cust_abbr = cust_abbr;
	}
	public String getMapping_id() {
		return mapping_id;
	}
	public void setMapping_id(String mapping_id) {
		this.mapping_id = mapping_id;
	}
	
	
	public DataBean_CheckPost_Master(String cust_code, String cont_no, String cont_rev_no, String plant_nm,
			String agr_no, String agr_rev_no, String plant_no, String cust_abbr, String mapping_id, String cont_nm) {
		super();
		this.cust_code = cust_code;
		this.cont_no = cont_no;
		this.cont_rev_no = cont_rev_no;
		this.plant_nm = plant_nm;
		this.agr_no = agr_no;
		this.agr_rev_no = agr_rev_no;
		this.plant_no = plant_no;
		this.cust_abbr = cust_abbr;
		this.mapping_id = mapping_id;
		this.cont_nm = cont_nm;
	}
	public DataBean_CheckPost_Master(String chkpost_cd, String chkpost_name) {
		super();
		this.chkpost_cd = chkpost_cd;
		this.chkpost_name = chkpost_name;
	}
	public String getCont_nm() {
		return cont_nm;
	}
	public void setCont_nm(String cont_nm) {
		this.cont_nm = cont_nm;
	}
	public String getChkpost_cd() {
		return chkpost_cd;
	}
	public void setChkpost_cd(String chkpost_cd) {
		this.chkpost_cd = chkpost_cd;
	}
	public String getChkpost_name() {
		return chkpost_name;
	}
	public void setChkpost_name(String chkpost_name) {
		this.chkpost_name = chkpost_name;
	}
	public DataBean_CheckPost_Master(String view_chkpost_cd, String view_eff_dt, String view_status,
			String view_chkpost_name,String msg,String view_mapping_id) {
		super();
		this.view_chkpost_cd = view_chkpost_cd;
		this.view_eff_dt = view_eff_dt;
		this.view_status = view_status;
		this.view_chkpost_name = view_chkpost_name;
		this.msg = msg ;
		this.view_mapping_id = view_mapping_id;
	}
	public String getView_chkpost_cd() {
		return view_chkpost_cd;
	}
	public void setView_chkpost_cd(String view_chkpost_cd) {
		this.view_chkpost_cd = view_chkpost_cd;
	}
	public String getView_eff_dt() {
		return view_eff_dt;
	}
	public void setView_eff_dt(String view_eff_dt) {
		this.view_eff_dt = view_eff_dt;
	}
	public String getView_status() {
		return view_status;
	}
	public void setView_status(String view_status) {
		this.view_status = view_status;
	}
	public String getView_chkpost_name() {
		return view_chkpost_name;
	}
	public void setView_chkpost_name(String view_chkpost_name) {
		this.view_chkpost_name = view_chkpost_name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getView_mapping_id() {
		return view_mapping_id;
	}
	public void setView_mapping_id(String view_mapping_id) {
		this.view_mapping_id = view_mapping_id;
	}
	
}
