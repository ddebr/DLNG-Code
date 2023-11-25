package com.seipl.hazira.dlng.contract_mgmt;

import java.io.Serializable;
import java.util.Vector;
public class DataBean_DailyBuyer_NominationV2 implements Serializable {

	public Vector VTransporter_Name = new Vector();
	private Vector Vdaily_Buyer_Nom_Name = new Vector();
	private Vector master_Transporter_Cd = new Vector();
	private Vector master_Transporter_Abbr = new Vector();
	private Vector master_Transporter_Count = new Vector();
	private Vector master_Transporter_Dcq = new Vector();
	private Vector daily_Buyer_Nom_Fgsa_No = new Vector();
	private Vector daily_Buyer_Nom_Mapping_Id = new Vector();
	private Vector daily_Buyer_Nom_Fgsa_Rev_No = new Vector();
	private Vector daily_Buyer_Nom_Sn_No = new Vector();
	private Vector daily_Buyer_Nom_Sn_Rev_No = new Vector();
	private Vector daily_Buyer_Nom_Cd = new Vector();
	private Vector daily_Buyer_Nom_Abbr = new Vector();
	private Vector daily_Buyer_Nom_Dcq = new Vector();
	private Vector daily_Buyer_Nom_Plant_Cd = new Vector();
	private Vector daily_Buyer_Nom_Plant_Abbr = new Vector();
	private Vector daily_Transporter_Nom_Cd = new Vector();
	private Vector daily_Transporter_Nom_Abbr = new Vector();
	private Vector daily_Buyer_Nom_Balance_Qty = new Vector(); //Introduced By Samik Shah On 23rd August, 2010 ...
	private Vector Buyer_Contracted_Qty = new Vector(); //SB20110924
	private Vector Buyer_Allocated_Qty = new Vector(); //SB20110924
	private Vector Buyer_Nominated_Qty = new Vector(); //SB20110924
	private Vector daily_Buyer_Nom_Mdcq_Qty = new Vector(); //Introduced By Samik Shah On 25th August, 2010 ...
	private Vector daily_Buyer_Nom_LC_ADV_Flag = new Vector(); //Introduced By Samik Shah On 22nd June, 2011 ...
	private Vector daily_Buyer_Nom_Current_Balance_Amt = new Vector(); //Introduced By Samik Shah On 22nd June, 2011 ...
	private String daily_Total_Dcq = "";
	private Vector CUST_CD = new Vector();	

	private Vector daily_Buyer_regas_cargo_boe_no=new Vector();
	private Vector daily_Buyer_regas_cargo_boe_dt=new Vector();
	private Vector PRE_APPROVAL=new Vector();
	private Vector COMM_PRE_APPROVAL=new Vector();
	
	private Vector daily_tax_struct_dtl = new Vector();
	
	private Vector daily_Buyer_Gen_Day_With_Rev_No = new Vector();
	private Vector daily_Buyer_Gen_Day_Time = new Vector();
	private Vector daily_Buyer_Nom_Plant_Seq_No = new Vector();
	private Vector daily_Buyer_Nom_Plant_Seq_Abbr = new Vector();
	private Vector daily_Buyer_Nom_Qty_Mmbtu = new Vector();
	private Vector daily_Buyer_Nom_Qty_Scm = new Vector();
	private Vector master_Transporter_Mmbtu = new Vector();
	private Vector master_Transporter_Scm = new Vector();
	private String daily_Total_Mmbtu = "";
	private String daily_Total_Scm = "";
	private Vector qty_nomination = new Vector();
	private Vector ALLOCATED_QTYV = new Vector();
	private Vector sn_ref_no = new Vector();
	Vector temp=new Vector();
	
	private Vector daily_Buyer_Nom_Type = new Vector(); //This Vector Stores Whether Buyer Is SN Based Or LOA Based Or RE-GAS Based ...
	
	private Vector daily_Buyer_Nom_Contract_Type = new Vector(); 

	public void setVTransporter_Name(Vector vTransporter_Name) {
		this.VTransporter_Name = vTransporter_Name;
		temp=vTransporter_Name;
	}
	public Vector getVTransporter_Name() {
		return VTransporter_Name;
	}
	
	public Vector getVdaily_Buyer_Nom_Name() {
		return Vdaily_Buyer_Nom_Name;
	}
	public void setVdaily_Buyer_Nom_Name(Vector vdaily_Buyer_Nom_Name) {
		Vdaily_Buyer_Nom_Name = vdaily_Buyer_Nom_Name;
	}
	public Vector getMaster_Transporter_Cd() {
		return master_Transporter_Cd;
	}
	public void setMaster_Transporter_Cd(Vector master_Transporter_Cd) {
		this.master_Transporter_Cd = master_Transporter_Cd;
	}
	public Vector getMaster_Transporter_Abbr() {
		return master_Transporter_Abbr;
	}
	public void setMaster_Transporter_Abbr(Vector master_Transporter_Abbr) {
		this.master_Transporter_Abbr = master_Transporter_Abbr;
	}
	public Vector getMaster_Transporter_Count() {
		return master_Transporter_Count;
	}
	public void setMaster_Transporter_Count(Vector master_Transporter_Count) {
		this.master_Transporter_Count = master_Transporter_Count;
	}
	public Vector getMaster_Transporter_Dcq() {
		return master_Transporter_Dcq;
	}
	public void setMaster_Transporter_Dcq(Vector master_Transporter_Dcq) {
		this.master_Transporter_Dcq = master_Transporter_Dcq;
	}
	public Vector getDaily_Buyer_Nom_Fgsa_No() {
		return daily_Buyer_Nom_Fgsa_No;
	}
	public void setDaily_Buyer_Nom_Fgsa_No(Vector daily_Buyer_Nom_Fgsa_No) {
		this.daily_Buyer_Nom_Fgsa_No = daily_Buyer_Nom_Fgsa_No;
	}
	public Vector getDaily_Buyer_Nom_Mapping_Id() {
		return daily_Buyer_Nom_Mapping_Id;
	}
	public void setDaily_Buyer_Nom_Mapping_Id(Vector daily_Buyer_Nom_Mapping_Id) {
		this.daily_Buyer_Nom_Mapping_Id = daily_Buyer_Nom_Mapping_Id;
	}
	public Vector getDaily_Buyer_Nom_Fgsa_Rev_No() {
		return daily_Buyer_Nom_Fgsa_Rev_No;
	}
	public void setDaily_Buyer_Nom_Fgsa_Rev_No(Vector daily_Buyer_Nom_Fgsa_Rev_No) {
		this.daily_Buyer_Nom_Fgsa_Rev_No = daily_Buyer_Nom_Fgsa_Rev_No;
	}
	public Vector getDaily_Buyer_Nom_Sn_No() {
		return daily_Buyer_Nom_Sn_No;
	}
	public void setDaily_Buyer_Nom_Sn_No(Vector daily_Buyer_Nom_Sn_No) {
		this.daily_Buyer_Nom_Sn_No = daily_Buyer_Nom_Sn_No;
	}
	public Vector getDaily_Buyer_Nom_Sn_Rev_No() {
		return daily_Buyer_Nom_Sn_Rev_No;
	}
	public void setDaily_Buyer_Nom_Sn_Rev_No(Vector daily_Buyer_Nom_Sn_Rev_No) {
		this.daily_Buyer_Nom_Sn_Rev_No = daily_Buyer_Nom_Sn_Rev_No;
	}
	public Vector getDaily_Buyer_Nom_Cd() {
		return daily_Buyer_Nom_Cd;
	}
	public void setDaily_Buyer_Nom_Cd(Vector daily_Buyer_Nom_Cd) {
		this.daily_Buyer_Nom_Cd = daily_Buyer_Nom_Cd;
	}
	public Vector getDaily_Buyer_Nom_Abbr() {
		return daily_Buyer_Nom_Abbr;
	}
	public void setDaily_Buyer_Nom_Abbr(Vector daily_Buyer_Nom_Abbr) {
		this.daily_Buyer_Nom_Abbr = daily_Buyer_Nom_Abbr;
	}
	public Vector getDaily_Buyer_Nom_Dcq() {
		return daily_Buyer_Nom_Dcq;
	}
	public void setDaily_Buyer_Nom_Dcq(Vector daily_Buyer_Nom_Dcq) {
		this.daily_Buyer_Nom_Dcq = daily_Buyer_Nom_Dcq;
	}
	public Vector getDaily_Buyer_Nom_Plant_Cd() {
		return daily_Buyer_Nom_Plant_Cd;
	}
	public void setDaily_Buyer_Nom_Plant_Cd(Vector daily_Buyer_Nom_Plant_Cd) {
		this.daily_Buyer_Nom_Plant_Cd = daily_Buyer_Nom_Plant_Cd;
	}
	public Vector getDaily_Buyer_Nom_Plant_Abbr() {
		return daily_Buyer_Nom_Plant_Abbr;
	}
	public void setDaily_Buyer_Nom_Plant_Abbr(Vector daily_Buyer_Nom_Plant_Abbr) {
		this.daily_Buyer_Nom_Plant_Abbr = daily_Buyer_Nom_Plant_Abbr;
	}
	public Vector getDaily_Transporter_Nom_Cd() {
		return daily_Transporter_Nom_Cd;
	}
	public void setDaily_Transporter_Nom_Cd(Vector daily_Transporter_Nom_Cd) {
		this.daily_Transporter_Nom_Cd = daily_Transporter_Nom_Cd;
	}
	public Vector getDaily_Transporter_Nom_Abbr() {
		return daily_Transporter_Nom_Abbr;
	}
	public void setDaily_Transporter_Nom_Abbr(Vector daily_Transporter_Nom_Abbr) {
		this.daily_Transporter_Nom_Abbr = daily_Transporter_Nom_Abbr;
	}
	public Vector getDaily_Buyer_Nom_Balance_Qty() {
		return daily_Buyer_Nom_Balance_Qty;
	}
	public void setDaily_Buyer_Nom_Balance_Qty(Vector daily_Buyer_Nom_Balance_Qty) {
		this.daily_Buyer_Nom_Balance_Qty = daily_Buyer_Nom_Balance_Qty;
	}
	public Vector getBuyer_Contracted_Qty() {
		return Buyer_Contracted_Qty;
	}
	public void setBuyer_Contracted_Qty(Vector buyer_Contracted_Qty) {
		Buyer_Contracted_Qty = buyer_Contracted_Qty;
	}
	public Vector getBuyer_Allocated_Qty() {
		return Buyer_Allocated_Qty;
	}
	public void setBuyer_Allocated_Qty(Vector buyer_Allocated_Qty) {
		Buyer_Allocated_Qty = buyer_Allocated_Qty;
	}
	public Vector getBuyer_Nominated_Qty() {
		return Buyer_Nominated_Qty;
	}
	public void setBuyer_Nominated_Qty(Vector buyer_Nominated_Qty) {
		Buyer_Nominated_Qty = buyer_Nominated_Qty;
	}
	public Vector getDaily_Buyer_Nom_Mdcq_Qty() {
		return daily_Buyer_Nom_Mdcq_Qty;
	}
	public void setDaily_Buyer_Nom_Mdcq_Qty(Vector daily_Buyer_Nom_Mdcq_Qty) {
		this.daily_Buyer_Nom_Mdcq_Qty = daily_Buyer_Nom_Mdcq_Qty;
	}
	public Vector getDaily_Buyer_Nom_LC_ADV_Flag() {
		return daily_Buyer_Nom_LC_ADV_Flag;
	}
	public void setDaily_Buyer_Nom_LC_ADV_Flag(Vector daily_Buyer_Nom_LC_ADV_Flag) {
		this.daily_Buyer_Nom_LC_ADV_Flag = daily_Buyer_Nom_LC_ADV_Flag;
	}
	public Vector getDaily_Buyer_Nom_Current_Balance_Amt() {
		return daily_Buyer_Nom_Current_Balance_Amt;
	}
	public void setDaily_Buyer_Nom_Current_Balance_Amt(Vector daily_Buyer_Nom_Current_Balance_Amt) {
		this.daily_Buyer_Nom_Current_Balance_Amt = daily_Buyer_Nom_Current_Balance_Amt;
	}
	public String getDaily_Total_Dcq() {
		return daily_Total_Dcq;
	}
	public void setDaily_Total_Dcq(String daily_Total_Dcq) {
		this.daily_Total_Dcq = daily_Total_Dcq;
	}
	public Vector getDaily_Buyer_regas_cargo_boe_no() {
		return daily_Buyer_regas_cargo_boe_no;
	}
	public void setDaily_Buyer_regas_cargo_boe_no(Vector daily_Buyer_regas_cargo_boe_no) {
		this.daily_Buyer_regas_cargo_boe_no = daily_Buyer_regas_cargo_boe_no;
	}
	public Vector getDaily_Buyer_regas_cargo_boe_dt() {
		return daily_Buyer_regas_cargo_boe_dt;
	}
	public void setDaily_Buyer_regas_cargo_boe_dt(Vector daily_Buyer_regas_cargo_boe_dt) {
		this.daily_Buyer_regas_cargo_boe_dt = daily_Buyer_regas_cargo_boe_dt;
	}
	public Vector getPRE_APPROVAL() {
		return PRE_APPROVAL;
	}
	public void setPRE_APPROVAL(Vector pRE_APPROVAL) {
		PRE_APPROVAL = pRE_APPROVAL;
	}
	public Vector getCOMM_PRE_APPROVAL() {
		return COMM_PRE_APPROVAL;
	}
	public void setCOMM_PRE_APPROVAL(Vector cOMM_PRE_APPROVAL) {
		COMM_PRE_APPROVAL = cOMM_PRE_APPROVAL;
	}
	public Vector getDaily_tax_struct_dtl() {
		return daily_tax_struct_dtl;
	}
	public void setDaily_tax_struct_dtl(Vector daily_tax_struct_dtl) {
		this.daily_tax_struct_dtl = daily_tax_struct_dtl;
	}
	public Vector getDaily_Buyer_Gen_Day_With_Rev_No() {
		return daily_Buyer_Gen_Day_With_Rev_No;
	}
	public void setDaily_Buyer_Gen_Day_With_Rev_No(Vector daily_Buyer_Gen_Day_With_Rev_No) {
		this.daily_Buyer_Gen_Day_With_Rev_No = daily_Buyer_Gen_Day_With_Rev_No;
	}
	public Vector getDaily_Buyer_Gen_Day_Time() {
		return daily_Buyer_Gen_Day_Time;
	}
	public void setDaily_Buyer_Gen_Day_Time(Vector daily_Buyer_Gen_Day_Time) {
		this.daily_Buyer_Gen_Day_Time = daily_Buyer_Gen_Day_Time;
	}
	public Vector getDaily_Buyer_Nom_Plant_Seq_No() {
		return daily_Buyer_Nom_Plant_Seq_No;
	}
	public void setDaily_Buyer_Nom_Plant_Seq_No(Vector daily_Buyer_Nom_Plant_Seq_No) {
		this.daily_Buyer_Nom_Plant_Seq_No = daily_Buyer_Nom_Plant_Seq_No;
	}
	public Vector getDaily_Buyer_Nom_Plant_Seq_Abbr() {
		return daily_Buyer_Nom_Plant_Seq_Abbr;
	}
	public void setDaily_Buyer_Nom_Plant_Seq_Abbr(Vector daily_Buyer_Nom_Plant_Seq_Abbr) {
		this.daily_Buyer_Nom_Plant_Seq_Abbr = daily_Buyer_Nom_Plant_Seq_Abbr;
	}
	public Vector getDaily_Buyer_Nom_Qty_Mmbtu() {
		return daily_Buyer_Nom_Qty_Mmbtu;
	}
	public void setDaily_Buyer_Nom_Qty_Mmbtu(Vector daily_Buyer_Nom_Qty_Mmbtu) {
		this.daily_Buyer_Nom_Qty_Mmbtu = daily_Buyer_Nom_Qty_Mmbtu;
	}
	public Vector getDaily_Buyer_Nom_Qty_Scm() {
		return daily_Buyer_Nom_Qty_Scm;
	}
	public void setDaily_Buyer_Nom_Qty_Scm(Vector daily_Buyer_Nom_Qty_Scm) {
		this.daily_Buyer_Nom_Qty_Scm = daily_Buyer_Nom_Qty_Scm;
	}
	public Vector getMaster_Transporter_Mmbtu() {
		return master_Transporter_Mmbtu;
	}
	public void setMaster_Transporter_Mmbtu(Vector master_Transporter_Mmbtu) {
		this.master_Transporter_Mmbtu = master_Transporter_Mmbtu;
	}
	public Vector getMaster_Transporter_Scm() {
		return master_Transporter_Scm;
	}
	public void setMaster_Transporter_Scm(Vector master_Transporter_Scm) {
		this.master_Transporter_Scm = master_Transporter_Scm;
	}
	public String getDaily_Total_Mmbtu() {
		return daily_Total_Mmbtu;
	}
	public void setDaily_Total_Mmbtu(String daily_Total_Mmbtu) {
		this.daily_Total_Mmbtu = daily_Total_Mmbtu;
	}
	public String getDaily_Total_Scm() {
		return daily_Total_Scm;
	}
	public void setDaily_Total_Scm(String daily_Total_Scm) {
		this.daily_Total_Scm = daily_Total_Scm;
	}
	public Vector getDaily_Buyer_Nom_Type() {
		return daily_Buyer_Nom_Type;
	}
	public void setDaily_Buyer_Nom_Type(Vector daily_Buyer_Nom_Type) {
		this.daily_Buyer_Nom_Type = daily_Buyer_Nom_Type;
	}
	public Vector getDaily_Buyer_Nom_Contract_Type() {
		return daily_Buyer_Nom_Contract_Type;
	}
	public void setDaily_Buyer_Nom_Contract_Type(Vector daily_Buyer_Nom_Contract_Type) {
		this.daily_Buyer_Nom_Contract_Type = daily_Buyer_Nom_Contract_Type;
	}
	public Vector getQty_nomination() {
		return qty_nomination;
	}
	public void setQty_nomination(Vector qty_nomination) {
		this.qty_nomination = qty_nomination;
	}
	public Vector getALLOCATED_QTYV() {
		return ALLOCATED_QTYV;
	}
	public void setALLOCATED_QTYV(Vector aLLOCATED_QTYV) {
		ALLOCATED_QTYV = aLLOCATED_QTYV;
	}
	public Vector getCUST_CD() {
		return CUST_CD;
	}
	public void setCUST_CD(Vector cUST_CD) {
		CUST_CD = cUST_CD;
	}
	public Vector getSn_ref_no() {
		return sn_ref_no;
	}
	public void setSn_ref_no(Vector sn_ref_no) {
		this.sn_ref_no = sn_ref_no;
	}
	

	
}
