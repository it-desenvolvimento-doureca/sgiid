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
@Table(name = "RC_MOV_RECLAMACAO_PLANO_ACCOES_IMEDIATAS")
public class RC_MOV_RECLAMACAO_PLANO_ACCOES_IMEDIATAS {

	private Integer ID;
	private Integer ORDEM;
	private String DESCRICAO;
	private Integer ID_RECLAMACAO;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private Integer RESPONSAVEL;
	private Date DATA_PREVISTA;
	private Date DATA_REAL;
	private String TIPO_RESPONSAVEL;

	private Integer CONCLUIDO_UTZ;
	private Timestamp CONCLUIDO_DATA;

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

	@Column(name = "DESCRICAO")
	public String getDESCRICAO() {
		return DESCRICAO;
	}

	@Column(name = "ID_RECLAMACAO")
	public Integer getID_RECLAMACAO() {
		return ID_RECLAMACAO;
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

	@Column(name = "RESPONSAVEL")
	public Integer getRESPONSAVEL() {
		return RESPONSAVEL;
	}

	@Column(name = "DATA_PREVISTA")
	public Date getDATA_PREVISTA() {
		return DATA_PREVISTA;
	}

	@Column(name = "DATA_REAL")
	public Date getDATA_REAL() {
		return DATA_REAL;
	}

	@Column(name = "TIPO_RESPONSAVEL")
	public String getTIPO_RESPONSAVEL() {
		return TIPO_RESPONSAVEL;
	}

	@Column(name = "CONCLUIDO_UTZ")
	public Integer getCONCLUIDO_UTZ() {
		return CONCLUIDO_UTZ;
	}

	@Column(name = "CONCLUIDO_DATA")
	public Timestamp getCONCLUIDO_DATA() {
		return CONCLUIDO_DATA;
	}

	public void setCONCLUIDO_UTZ(Integer cONCLUIDO_UTZ) {
		CONCLUIDO_UTZ = cONCLUIDO_UTZ;
	}

	public void setCONCLUIDO_DATA(Timestamp cONCLUIDO_DATA) {
		CONCLUIDO_DATA = cONCLUIDO_DATA;
	}

	public void setTIPO_RESPONSAVEL(String tIPO_RESPONSAVEL) {
		TIPO_RESPONSAVEL = tIPO_RESPONSAVEL;
	}

	public void setRESPONSAVEL(Integer rESPONSAVEL) {
		RESPONSAVEL = rESPONSAVEL;
	}

	public void setDATA_PREVISTA(Date dATA_PREVISTA) {
		DATA_PREVISTA = dATA_PREVISTA;
	}

	public void setDATA_REAL(Date dATA_REAL) {
		DATA_REAL = dATA_REAL;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setORDEM(Integer oRDEM) {
		ORDEM = oRDEM;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setID_RECLAMACAO(Integer iD_RECLAMACAO) {
		ID_RECLAMACAO = iD_RECLAMACAO;
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
