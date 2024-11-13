package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_DIC_BANHO_ADITIVO")
public class AB_DIC_BANHO_ADITIVO {

	private Integer ID_BANHO_ADITIVO;
	private Integer ID_BANHO;
	private Integer ID_ADITIVO;
	private Integer ID_UNIDADE1;
	private Integer ID_UNIDADE2;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private Boolean MANUTENCAONAOPROGRAMADA;
	private Float VALOR_DOSE1;
	private Float VALOR_DOSE2;
	private Float VALOR_DOSE3;
	private Float VALOR_DOSE4;
	private Float VALOR_DOSE5;
	private Float VALOR_DOSE6;
	private Float QUANTIDADE_DEFEITO;
	private String OBSERVACOES;

	@Id
	@Column(name = "ID_BANHO_ADITIVO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_BANHO_ADITIVO() {
		return ID_BANHO_ADITIVO;
	}

	@Column(name = "ID_BANHO")
	public Integer getID_BANHO() {
		return ID_BANHO;
	}

	@Column(name = "ID_UNIDADE1")
	public Integer getID_UNIDADE1() {
		return ID_UNIDADE1;
	}

	@Column(name = "ID_UNIDADE2")
	public Integer getID_UNIDADE2() {
		return ID_UNIDADE2;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "UTZ_CRIA")
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	@Column(name = "DATA_ULT_MODIF")
	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	@Column(name = "UTZ_ULT_MODIF")
	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	@Column(name = "ID_ADITIVO")
	public Integer getID_ADITIVO() {
		return ID_ADITIVO;
	}

	public void setID_ADITIVO(Integer iD_ADITIVO) {
		ID_ADITIVO = iD_ADITIVO;
	}

	public void setID_BANHO_ADITIVO(Integer iD_BANHO_ADITIVO) {
		ID_BANHO_ADITIVO = iD_BANHO_ADITIVO;
	}

	public void setID_BANHO(Integer iD_BANHO) {
		ID_BANHO = iD_BANHO;
	}

	public void setID_UNIDADE1(Integer iD_UNIDADE1) {
		ID_UNIDADE1 = iD_UNIDADE1;
	}

	public void setID_UNIDADE2(Integer iD_UNIDADE2) {
		ID_UNIDADE2 = iD_UNIDADE2;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	@Column(name = "MANUTENCAONAOPROGRAMADA")
	public Boolean getMANUTENCAONAOPROGRAMADA() {
		return MANUTENCAONAOPROGRAMADA;
	}

	public void setMANUTENCAONAOPROGRAMADA(Boolean mANUTENCAONAOPROGRAMADA) {
		MANUTENCAONAOPROGRAMADA = mANUTENCAONAOPROGRAMADA;
	}

	@Column(name = "VALOR_DOSE1")
	public Float getVALOR_DOSE1() {
		return VALOR_DOSE1;
	}

	@Column(name = "VALOR_DOSE2")
	public Float getVALOR_DOSE2() {
		return VALOR_DOSE2;
	}

	@Column(name = "VALOR_DOSE3")
	public Float getVALOR_DOSE3() {
		return VALOR_DOSE3;
	}

	@Column(name = "VALOR_DOSE4")
	public Float getVALOR_DOSE4() {
		return VALOR_DOSE4;
	}

	@Column(name = "VALOR_DOSE5")
	public Float getVALOR_DOSE5() {
		return VALOR_DOSE5;
	}

	public void setVALOR_DOSE1(Float vALOR_DOSE1) {
		VALOR_DOSE1 = vALOR_DOSE1;
	}

	public void setVALOR_DOSE2(Float vALOR_DOSE2) {
		VALOR_DOSE2 = vALOR_DOSE2;
	}

	public void setVALOR_DOSE3(Float vALOR_DOSE3) {
		VALOR_DOSE3 = vALOR_DOSE3;
	}

	public void setVALOR_DOSE4(Float vALOR_DOSE4) {
		VALOR_DOSE4 = vALOR_DOSE4;
	}

	public void setVALOR_DOSE5(Float vALOR_DOSE5) {
		VALOR_DOSE5 = vALOR_DOSE5;
	}
	
	@Column(name = "QUANTIDADE_DEFEITO")
	public Float getQUANTIDADE_DEFEITO() {
		return QUANTIDADE_DEFEITO;
	}

	public void setQUANTIDADE_DEFEITO(Float qUANTIDADE_DEFEITO) {
		QUANTIDADE_DEFEITO = qUANTIDADE_DEFEITO;
	}

	@Column(name = "OBSERVACOES")
	public String getOBSERVACOES() {
		return OBSERVACOES;
	}

	public void setOBSERVACOES(String oBSERVACOES) {
		OBSERVACOES = oBSERVACOES;
	}

	@Column(name = "VALOR_DOSE6")
	public Float getVALOR_DOSE6() {
		return VALOR_DOSE6;
	}

	public void setVALOR_DOSE6(Float vALOR_DOSE6) {
		VALOR_DOSE6 = vALOR_DOSE6;
	}

}
