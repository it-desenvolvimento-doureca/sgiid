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
@Table(name = "AB_DIC_BANHO_COMPONENTE")
public class AB_DIC_BANHO_COMPONENTE {

	private Integer ID_BANHO_COMP;
	private Integer ID_BANHO;
	private Integer ID_COMPONENTE;
	private Date DATA_INICIO;
	private Date DATA_FIM;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;
	private Float LIMITE_AMARELO_INF;
	private Float LIMITE_AMARELO_SUP;
	private Float LIMITE_VERDE_INF;
	private Float LIMITE_VERDE_SUP;
	private Integer QUANTIDADE;
	private Integer ID_FORNECEDOR;
	private String OBS;
	private Integer ID_UNIDADE_COMPONENTE;

	@Column(name = "DATA_ANULACAO")
	public Timestamp getDATA_ANULACAO() {
		return DATA_ANULACAO;
	}

	@Column(name = "UTZ_ANULACAO")
	public Integer getUTZ_ANULACAO() {
		return UTZ_ANULACAO;
	}

	@Column(name = "INATIVO")
	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	@Id
	@Column(name = "ID_BANHO_COMP")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_BANHO_COMP() {
		return ID_BANHO_COMP;
	}

	@Column(name = "ID_BANHO")
	public Integer getID_BANHO() {
		return ID_BANHO;
	}

	@Column(name = "ID_COMPONENTE")
	public Integer getID_COMPONENTE() {
		return ID_COMPONENTE;
	}

	@Column(name = "DATA_INICIO")
	public Date getDATA_INICIO() {
		return DATA_INICIO;
	}

	@Column(name = "DATA_FIM")
	public Date getDATA_FIM() {
		return DATA_FIM;
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

	@Column(name = "LIMITE_AMARELO_INF")
	public Float getLIMITE_AMARELO_INF() {
		return LIMITE_AMARELO_INF;
	}

	@Column(name = "LIMITE_AMARELO_SUP")
	public Float getLIMITE_AMARELO_SUP() {
		return LIMITE_AMARELO_SUP;
	}

	@Column(name = "LIMITE_VERDE_INF")
	public Float getLIMITE_VERDE_INF() {
		return LIMITE_VERDE_INF;
	}

	@Column(name = "LIMITE_VERDE_SUP")
	public Float getLIMITE_VERDE_SUP() {
		return LIMITE_VERDE_SUP;
	}

	@Column(name = "QUANTIDADE")
	public Integer getQUANTIDADE() {
		return QUANTIDADE;
	}

	@Column(name = "ID_FORNECEDOR")
	public Integer getID_FORNECEDOR() {
		return ID_FORNECEDOR;
	}

	@Column(name = "OBS")
	public String getOBS() {
		return OBS;
	}

	@Column(name = "ID_UNIDADE_COMPONENTE")
	public Integer getID_UNIDADE_COMPONENTE() {
		return ID_UNIDADE_COMPONENTE;
	}

	public void setID_UNIDADE_COMPONENTE(Integer iD_UNIDADE_COMPONENTE) {
		ID_UNIDADE_COMPONENTE = iD_UNIDADE_COMPONENTE;
	}

	public void setLIMITE_AMARELO_INF(Float lIMITE_AMARELO_INF) {
		LIMITE_AMARELO_INF = lIMITE_AMARELO_INF;
	}

	public void setLIMITE_AMARELO_SUP(Float lIMITE_AMARELO_SUP) {
		LIMITE_AMARELO_SUP = lIMITE_AMARELO_SUP;
	}

	public void setLIMITE_VERDE_INF(Float lIMITE_VERDE_INF) {
		LIMITE_VERDE_INF = lIMITE_VERDE_INF;
	}

	public void setLIMITE_VERDE_SUP(Float lIMITE_VERDE_SUP) {
		LIMITE_VERDE_SUP = lIMITE_VERDE_SUP;
	}

	public void setQUANTIDADE(Integer qUANTIDADE) {
		QUANTIDADE = qUANTIDADE;
	}

	public void setID_FORNECEDOR(Integer iD_FORNECEDOR) {
		ID_FORNECEDOR = iD_FORNECEDOR;
	}

	public void setOBS(String oBS) {
		OBS = oBS;
	}

	public void setID_BANHO_COMP(Integer iD_BANHO_COMP) {
		ID_BANHO_COMP = iD_BANHO_COMP;
	}

	public void setID_BANHO(Integer iD_BANHO) {
		ID_BANHO = iD_BANHO;
	}

	public void setID_COMPONENTE(Integer iD_COMPONENTE) {
		ID_COMPONENTE = iD_COMPONENTE;
	}

	public void setDATA_INICIO(Date dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	public void setDATA_FIM(Date dATA_FIM) {
		DATA_FIM = dATA_FIM;
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

}
