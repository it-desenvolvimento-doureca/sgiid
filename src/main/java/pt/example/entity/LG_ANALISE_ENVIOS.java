package pt.example.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LG_ANALISE_ENVIOS")
public class LG_ANALISE_ENVIOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "CONSIGN_NO")
	private String CONSIGN_NO;
	@Column(name = "ACCOUNT_KEY")
	private String ACCOUNT_KEY;
	@Column(name = "CUSTOMER_REF")
	private String CUSTOMER_REF;
	@Column(name = "CON_CREATE_DATE")
	private Date CON_CREATE_DATE;
	@Column(name = "CON_DELIVERED_DATE")
	private Date CON_DELIVERED_DATE;
	@Column(name = "STATUS_DESC")
	private String STATUS_DESC;
	@Column(name = "SUMMARY_STAT")
	private String SUMMARY_STAT;
	@Column(name = "STATUS_CODE")
	private String STATUS_CODE;
	@Column(name = "SIGNED_BY")
	private String SIGNED_BY;
	@Column(name = "EVENT_DATE_LOCAL")
	private Timestamp EVENT_DATE_LOCAL;
	@Column(name = "EVENT_TIME_LOCAL")
	private String EVENT_TIME_LOCAL;
	@Column(name = "EVENT_DATE_GMT")
	private Date EVENT_DATE_GMT;
	@Column(name = "EVENT_TIME_GMT")
	private String EVENT_TIME_GMT;
	@Column(name = "SENDER_NAME")
	private String SENDER_NAME;
	@Column(name = "RECEIVER_NAME")
	private String RECEIVER_NAME;
	@Column(name = "RECEIVER_CITY")
	private String RECEIVER_CITY;
	@Column(name = "RECEIVER_POSTCODE")
	private String RECEIVER_POSTCODE;
	@Column(name = "RECEIVER_CNTRYCDE")
	private String RECEIVER_CNTRYCDE;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;

	public Integer getID() {
		return ID;
	}

	public String getCONSIGN_NO() {
		return CONSIGN_NO;
	}

	public String getACCOUNT_KEY() {
		return ACCOUNT_KEY;
	}

	public String getCUSTOMER_REF() {
		return CUSTOMER_REF;
	}

	public Date getCON_CREATE_DATE() {
		return CON_CREATE_DATE;
	}

	public Date getCON_DELIVERED_DATE() {
		return CON_DELIVERED_DATE;
	}

	public String getSTATUS_DESC() {
		return STATUS_DESC;
	}

	public String getSUMMARY_STAT() {
		return SUMMARY_STAT;
	}

	public String getSTATUS_CODE() {
		return STATUS_CODE;
	}

	public String getSIGNED_BY() {
		return SIGNED_BY;
	}

	public Timestamp getEVENT_DATE_LOCAL() {
		return EVENT_DATE_LOCAL;
	}

	public String getEVENT_TIME_LOCAL() {
		return EVENT_TIME_LOCAL;
	}

	public Date getEVENT_DATE_GMT() {
		return EVENT_DATE_GMT;
	}

	public String getEVENT_TIME_GMT() {
		return EVENT_TIME_GMT;
	}

	public String getSENDER_NAME() {
		return SENDER_NAME;
	}

	public String getRECEIVER_NAME() {
		return RECEIVER_NAME;
	}

	public String getRECEIVER_CITY() {
		return RECEIVER_CITY;
	}

	public String getRECEIVER_POSTCODE() {
		return RECEIVER_POSTCODE;
	}

	public String getRECEIVER_CNTRYCDE() {
		return RECEIVER_CNTRYCDE;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setCONSIGN_NO(String cONSIGN_NO) {
		CONSIGN_NO = cONSIGN_NO;
	}

	public void setACCOUNT_KEY(String aCCOUNT_KEY) {
		ACCOUNT_KEY = aCCOUNT_KEY;
	}

	public void setCUSTOMER_REF(String cUSTOMER_REF) {
		CUSTOMER_REF = cUSTOMER_REF;
	}

	public void setCON_CREATE_DATE(Date cON_CREATE_DATE) {
		CON_CREATE_DATE = cON_CREATE_DATE;
	}

	public void setCON_DELIVERED_DATE(Date cON_DELIVERED_DATE) {
		CON_DELIVERED_DATE = cON_DELIVERED_DATE;
	}

	public void setSTATUS_DESC(String sTATUS_DESC) {
		STATUS_DESC = sTATUS_DESC;
	}

	public void setSUMMARY_STAT(String sUMMARY_STAT) {
		SUMMARY_STAT = sUMMARY_STAT;
	}

	public void setSTATUS_CODE(String sTATUS_CODE) {
		STATUS_CODE = sTATUS_CODE;
	}

	public void setSIGNED_BY(String sIGNED_BY) {
		SIGNED_BY = sIGNED_BY;
	}

	public void setEVENT_DATE_LOCAL(Timestamp eVENT_DATE_LOCAL) {
		EVENT_DATE_LOCAL = eVENT_DATE_LOCAL;
	}

	public void setEVENT_TIME_LOCAL(String eVENT_TIME_LOCAL) {
		EVENT_TIME_LOCAL = eVENT_TIME_LOCAL;
	}

	public void setEVENT_DATE_GMT(Date eVENT_DATE_GMT) {
		EVENT_DATE_GMT = eVENT_DATE_GMT;
	}

	public void setEVENT_TIME_GMT(String eVENT_TIME_GMT) {
		EVENT_TIME_GMT = eVENT_TIME_GMT;
	}

	public void setSENDER_NAME(String sENDER_NAME) {
		SENDER_NAME = sENDER_NAME;
	}

	public void setRECEIVER_NAME(String rECEIVER_NAME) {
		RECEIVER_NAME = rECEIVER_NAME;
	}

	public void setRECEIVER_CITY(String rECEIVER_CITY) {
		RECEIVER_CITY = rECEIVER_CITY;
	}

	public void setRECEIVER_POSTCODE(String rECEIVER_POSTCODE) {
		RECEIVER_POSTCODE = rECEIVER_POSTCODE;
	}

	public void setRECEIVER_CNTRYCDE(String rECEIVER_CNTRYCDE) {
		RECEIVER_CNTRYCDE = rECEIVER_CNTRYCDE;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

}
