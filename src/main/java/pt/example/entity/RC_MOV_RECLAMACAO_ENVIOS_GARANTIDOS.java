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
@Table(name = "RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS")
public class RC_MOV_RECLAMACAO_ENVIOS_GARANTIDOS {
	private Integer ID;
	private Integer ORDEM;
	private Integer ID_RECLAMACAO;
	private Integer QUANTIDADE;
	private String NUMERO_GUIA;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private Boolean ENVIO;
	private String CLIENTE;
	private String MORADA;
	private Date DATA_ENTREGA;
	private Date DATA_ENVIO;
	private String PROREF;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	@Column(name = "ORDEM")
	public Integer getORDEM() {
		return ORDEM;
	}

	@Column(name = "ID_RECLAMACAO")
	public Integer getID_RECLAMACAO() {
		return ID_RECLAMACAO;
	}

	@Column(name = "QUANTIDADE")
	public Integer getQUANTIDADE() {
		return QUANTIDADE;
	}

	@Column(name = "NUMERO_GUIA")
	public String getNUMERO_GUIA() {
		return NUMERO_GUIA;
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

	@Column(name = "ENVIO")
	public Boolean getENVIO() {
		return ENVIO;
	}

	@Column(name = "CLIENTE")
	public String getCLIENTE() {
		return CLIENTE;
	}

	@Column(name = "MORADA")
	public String getMORADA() {
		return MORADA;
	}

	@Column(name = "DATA_ENTREGA")
	public Date getDATA_ENTREGA() {
		return DATA_ENTREGA;
	}

	@Column(name = "DATA_ENVIO")
	public Date getDATA_ENVIO() {
		return DATA_ENVIO;
	}

	public void setENVIO(Boolean eNVIO) {
		ENVIO = eNVIO;
	}

	public void setCLIENTE(String cLIENTE) {
		CLIENTE = cLIENTE;
	}

	public void setMORADA(String mORADA) {
		MORADA = mORADA;
	}

	public void setDATA_ENTREGA(Date dATA_ENTREGA) {
		DATA_ENTREGA = dATA_ENTREGA;
	}

	public void setDATA_ENVIO(Date dATA_ENVIO) {
		DATA_ENVIO = dATA_ENVIO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setORDEM(Integer oRDEM) {
		ORDEM = oRDEM;
	}

	public void setID_RECLAMACAO(Integer iD_RECLAMACAO) {
		ID_RECLAMACAO = iD_RECLAMACAO;
	}

	public void setQUANTIDADE(Integer qUANTIDADE) {
		QUANTIDADE = qUANTIDADE;
	}

	public void setNUMERO_GUIA(String nUMERO_GUIA) {
		NUMERO_GUIA = nUMERO_GUIA;
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

	@Column(name = "PROREF")
	public String getPROREF() {
		return PROREF;
	}

	public void setPROREF(String pROREF) {
		PROREF = pROREF;
	}

}
