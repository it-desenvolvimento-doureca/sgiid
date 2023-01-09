package pt.example.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REU_REUNIOES_PLANOS_ACCOES")
public class REU_REUNIOES_PLANOS_ACCOES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "RESPONSAVEL")
	private Integer RESPONSAVEL;
	@Column(name = "DATA_PREVISTA")
	private Date DATA_PREVISTA;
	@Column(name = "DATA_REAL")
	private Date DATA_REAL;
	@Column(name = "ID_ACCAO")
	private Integer ID_ACCAO;
	@Column(name = "ID_REUNIAO")
	private Integer ID_REUNIAO;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@Column(name = "DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@Column(name = "TIPO")
	private String TIPO;
	@Column(name = "TIPO_RESPONSAVEL")
	private String TIPO_RESPONSAVEL;
	@Column(name = "CONCLUIDO_UTZ")
	private Integer CONCLUIDO_UTZ;
	@Column(name = "CONCLUIDO_DATA")
	private Timestamp CONCLUIDO_DATA;
	@Column(name = "ORDEM")
	private Integer ORDEM;
	@Column(name = "OBSERVACOES")
	private String OBSERVACOES;
	@Column(name = "ESTADO")
	private String ESTADO;
	@Column(name = "ID_TAREFA")
	private Integer ID_TAREFA;
	@Column(name = "OBRIGA_EVIDENCIAS")
	private Boolean OBRIGA_EVIDENCIAS;
	@Column(name = "AREA")
	private String AREA;
	@Column(name = "TIPO_ACAO")
	private Integer TIPO_ACAO;
	@Column(name = "ITEM")
	private String ITEM;

	public Integer getID() {
		return ID;
	}

	public Integer getRESPONSAVEL() {
		return RESPONSAVEL;
	}

	public Date getDATA_PREVISTA() {
		return DATA_PREVISTA;
	}

	public Date getDATA_REAL() {
		return DATA_REAL;
	}

	public Integer getID_ACCAO() {
		return ID_ACCAO;
	}

	public Integer getID_REUNIAO() {
		return ID_REUNIAO;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public String getTIPO() {
		return TIPO;
	}

	public String getTIPO_RESPONSAVEL() {
		return TIPO_RESPONSAVEL;
	}

	public Integer getCONCLUIDO_UTZ() {
		return CONCLUIDO_UTZ;
	}

	public Timestamp getCONCLUIDO_DATA() {
		return CONCLUIDO_DATA;
	}

	public Integer getORDEM() {
		return ORDEM;
	}

	public String getOBSERVACOES() {
		return OBSERVACOES;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public Integer getID_TAREFA() {
		return ID_TAREFA;
	}

	public Boolean getOBRIGA_EVIDENCIAS() {
		return OBRIGA_EVIDENCIAS;
	}

	public String getAREA() {
		return AREA;
	}

	public void setID(Integer iD) {
		ID = iD;
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

	public void setID_ACCAO(Integer iD_ACCAO) {
		ID_ACCAO = iD_ACCAO;
	}

	public void setID_REUNIAO(Integer iD_REUNIAO) {
		ID_REUNIAO = iD_REUNIAO;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}

	public void setTIPO_RESPONSAVEL(String tIPO_RESPONSAVEL) {
		TIPO_RESPONSAVEL = tIPO_RESPONSAVEL;
	}

	public void setCONCLUIDO_UTZ(Integer cONCLUIDO_UTZ) {
		CONCLUIDO_UTZ = cONCLUIDO_UTZ;
	}

	public void setCONCLUIDO_DATA(Timestamp cONCLUIDO_DATA) {
		CONCLUIDO_DATA = cONCLUIDO_DATA;
	}

	public void setORDEM(Integer oRDEM) {
		ORDEM = oRDEM;
	}

	public void setOBSERVACOES(String oBSERVACOES) {
		OBSERVACOES = oBSERVACOES;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setID_TAREFA(Integer iD_TAREFA) {
		ID_TAREFA = iD_TAREFA;
	}

	public void setOBRIGA_EVIDENCIAS(Boolean oBRIGA_EVIDENCIAS) {
		OBRIGA_EVIDENCIAS = oBRIGA_EVIDENCIAS;
	}

	public void setAREA(String aREA) {
		AREA = aREA;
	}

	public Integer getTIPO_ACAO() {
		return TIPO_ACAO;
	}

	public String getITEM() {
		return ITEM;
	}

	public void setTIPO_ACAO(Integer tIPO_ACAO) {
		TIPO_ACAO = tIPO_ACAO;
	}

	public void setITEM(String iTEM) {
		ITEM = iTEM;
	}

}
