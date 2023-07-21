package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "COM_BUDGETS_LINHAS")
public class COM_BUDGETS_LINHAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_BUDGET")
	private Integer ID_BUDGET;
	@JsonProperty("REFERENCE_SALES")
	private String REFERENCE_SALES;
	@JsonProperty("CUSTOMER_GROUP")
	private Integer CUSTOMER_GROUP;
	@JsonProperty("CUSTOMER_ID")
	private String CUSTOMER_ID;
	@JsonProperty("CUSTOMER_NAME")
	private String CUSTOMER_NAME;
	@JsonProperty("LOCATION_REFERENCE")
	private String LOCATION_REFERENCE;
	@JsonProperty("LOCATION_NAME")
	private String LOCATION_NAME;
	@JsonProperty("REFERENCE_INTERNAL")
	private String REFERENCE_INTERNAL;
	@JsonProperty("REFERENCE_CUSTOMER")
	private String REFERENCE_CUSTOMER;
	@JsonProperty("REFERENCE_NAME_SALES")
	private String REFERENCE_NAME_SALES;
	@JsonProperty("REFERENCE_NAME_INTERNAL")
	private String REFERENCE_NAME_INTERNAL;
	@JsonProperty("BUDGET_YEAR")
	private BigDecimal BUDGET_YEAR;
	@JsonProperty("TOTAL_CONTRACT_YEAR")
	private BigDecimal TOTAL_CONTRACT_YEAR;
	@JsonProperty("QUANTITY_YEAR")
	private BigDecimal QUANTITY_YEAR;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;
	@JsonProperty("SOP_DATE")
	private Timestamp SOP_DATE;
	@JsonProperty("VERSAO_BUDGET")
	private Integer VERSAO_BUDGET;
	

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getREFERENCE_SALES() {
		return REFERENCE_SALES;
	}

	public void setREFERENCE_SALES(String rEFERENCE_SALES) {
		REFERENCE_SALES = rEFERENCE_SALES;
	}

	public Integer getCUSTOMER_GROUP() {
		return CUSTOMER_GROUP;
	}

	public void setCUSTOMER_GROUP(Integer cUSTOMER_GROUP) {
		CUSTOMER_GROUP = cUSTOMER_GROUP;
	}

	public String getCUSTOMER_ID() {
		return CUSTOMER_ID;
	}

	public void setCUSTOMER_ID(String cUSTOMER_ID) {
		CUSTOMER_ID = cUSTOMER_ID;
	}

	public String getCUSTOMER_NAME() {
		return CUSTOMER_NAME;
	}

	public void setCUSTOMER_NAME(String cUSTOMER_NAME) {
		CUSTOMER_NAME = cUSTOMER_NAME;
	}

	public String getLOCATION_REFERENCE() {
		return LOCATION_REFERENCE;
	}

	public void setLOCATION_REFERENCE(String lOCATION_REFERENCE) {
		LOCATION_REFERENCE = lOCATION_REFERENCE;
	}

	public String getLOCATION_NAME() {
		return LOCATION_NAME;
	}

	public void setLOCATION_NAME(String lOCATION_NAME) {
		LOCATION_NAME = lOCATION_NAME;
	}

	public String getREFERENCE_INTERNAL() {
		return REFERENCE_INTERNAL;
	}

	public void setREFERENCE_INTERNAL(String rEFERENCE_INTERNAL) {
		REFERENCE_INTERNAL = rEFERENCE_INTERNAL;
	}

	public String getREFERENCE_CUSTOMER() {
		return REFERENCE_CUSTOMER;
	}

	public void setREFERENCE_CUSTOMER(String rEFERENCE_CUSTOMER) {
		REFERENCE_CUSTOMER = rEFERENCE_CUSTOMER;
	}

	public String getREFERENCE_NAME_SALES() {
		return REFERENCE_NAME_SALES;
	}

	public void setREFERENCE_NAME_SALES(String rEFERENCE_NAME_SALES) {
		REFERENCE_NAME_SALES = rEFERENCE_NAME_SALES;
	}

	public String getREFERENCE_NAME_INTERNAL() {
		return REFERENCE_NAME_INTERNAL;
	}

	public void setREFERENCE_NAME_INTERNAL(String rEFERENCE_NAME_INTERNAL) {
		REFERENCE_NAME_INTERNAL = rEFERENCE_NAME_INTERNAL;
	}

	public BigDecimal getBUDGET_YEAR() {
		return BUDGET_YEAR;
	}

	public void setBUDGET_YEAR(BigDecimal bUDGET_YEAR) {
		BUDGET_YEAR = bUDGET_YEAR;
	}

	public BigDecimal getTOTAL_CONTRACT_YEAR() {
		return TOTAL_CONTRACT_YEAR;
	}

	public void setTOTAL_CONTRACT_YEAR(BigDecimal tOTAL_CONTRACT_YEAR) {
		TOTAL_CONTRACT_YEAR = tOTAL_CONTRACT_YEAR;
	}

	public BigDecimal getQUANTITY_YEAR() {
		return QUANTITY_YEAR;
	}

	public void setQUANTITY_YEAR(BigDecimal qUANTITY_YEAR) {
		QUANTITY_YEAR = qUANTITY_YEAR;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public Integer getID_BUDGET() {
		return ID_BUDGET;
	}

	public void setID_BUDGET(Integer iD_BUDGET) {
		ID_BUDGET = iD_BUDGET;
	}

	public Timestamp getSOP_DATE() {
		return SOP_DATE;
	}

	public void setSOP_DATE(Timestamp sOP_DATE) {
		SOP_DATE = sOP_DATE;
	}

	public Integer getVERSAO_BUDGET() {
		return VERSAO_BUDGET;
	}

	public void setVERSAO_BUDGET(Integer vERSAO_BUDGET) {
		VERSAO_BUDGET = vERSAO_BUDGET;
	}

	 

}
