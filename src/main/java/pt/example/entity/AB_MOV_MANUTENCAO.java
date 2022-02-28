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
@Table(name = "AB_MOV_MANUTENCAO")
public class AB_MOV_MANUTENCAO {

	private Integer ID_MANUTENCAO;
	private Integer ID_TIPO_MANUTENCAO;
	private Integer ID_LINHA;
	private Integer ID_TURNO;
	private Date DATA_PLANEAMENTO;
	private String HORA_PLANEAMENTO;
	private Integer UTZ_PLANEAMENTO;
	private String ESTADO;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;
	private Timestamp DATA_ULT_IMPRES;
	private Integer UTZ_ULT_IMPRES;
	private Boolean IMPRESSO;
	private String CLASSIF;
	private Integer ID_TIPO_TIPOLOGIA_DOSIFICADORES;

	@Column(name = "DATA_ULT_IMPRES")
	public Timestamp getDATA_ULT_IMPRES() {
		return DATA_ULT_IMPRES;
	}

	@Column(name = "UTZ_ULT_IMPRES")
	public Integer getUTZ_ULT_IMPRES() {
		return UTZ_ULT_IMPRES;
	}

	@Column(name = "IMPRESSO")
	public Boolean getIMPRESSO() {
		return IMPRESSO;
	}

	public void setDATA_ULT_IMPRES(Timestamp dATA_ULT_IMPRES) {
		DATA_ULT_IMPRES = dATA_ULT_IMPRES;
	}

	public void setUTZ_ULT_IMPRES(Integer uTZ_ULT_IMPRES) {
		UTZ_ULT_IMPRES = uTZ_ULT_IMPRES;
	}

	public void setIMPRESSO(Boolean iMPRESSO) {
		IMPRESSO = iMPRESSO;
	}

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
	@Column(name = "ID_MANUTENCAO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_MANUTENCAO() {
		return ID_MANUTENCAO;
	}

	@Column(name = "ID_LINHA")
	public Integer getID_LINHA() {
		return ID_LINHA;
	}

	public void setID_LINHA(Integer iD_LINHA) {
		ID_LINHA = iD_LINHA;
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

	public void setID_MANUTENCAO(Integer iD_MANUTENCAO) {
		ID_MANUTENCAO = iD_MANUTENCAO;
	}

	@Column(name = "ID_TIPO_MANUTENCAO")
	public Integer getID_TIPO_MANUTENCAO() {
		return ID_TIPO_MANUTENCAO;
	}

	@Column(name = "ID_TURNO")
	public Integer getID_TURNO() {
		return ID_TURNO;
	}

	@Column(name = "DATA_PLANEAMENTO")
	public Date getDATA_PLANEAMENTO() {
		return DATA_PLANEAMENTO;
	}

	@Column(name = "HORA_PLANEAMENTO")
	public String getHORA_PLANEAMENTO() {
		return HORA_PLANEAMENTO;
	}

	@Column(name = "UTZ_PLANEAMENTO")
	public Integer getUTZ_PLANEAMENTO() {
		return UTZ_PLANEAMENTO;
	}

	@Column(name = "ESTADO")
	public String getESTADO() {
		return ESTADO;
	}

	public void setID_TIPO_MANUTENCAO(Integer iD_TIPO_MANUTENCAO) {
		ID_TIPO_MANUTENCAO = iD_TIPO_MANUTENCAO;
	}

	public void setID_TURNO(Integer iD_TURNO) {
		ID_TURNO = iD_TURNO;
	}

	public void setDATA_PLANEAMENTO(Date dATA_PLANEAMENTO) {
		DATA_PLANEAMENTO = dATA_PLANEAMENTO;
	}

	public void setHORA_PLANEAMENTO(String hORA_PLANEAMENTO) {
		HORA_PLANEAMENTO = hORA_PLANEAMENTO;
	}

	public void setUTZ_PLANEAMENTO(Integer uTZ_PLANEAMENTO) {
		UTZ_PLANEAMENTO = uTZ_PLANEAMENTO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
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

	@Column(name = "CLASSIF")
	public String getCLASSIF() {
		return CLASSIF;
	}

	public void setCLASSIF(String cLASSIF) {
		CLASSIF = cLASSIF;
	}

	@Column(name = "ID_TIPO_TIPOLOGIA_DOSIFICADORES")
	public Integer getID_TIPO_TIPOLOGIA_DOSIFICADORES() {
		return ID_TIPO_TIPOLOGIA_DOSIFICADORES;
	}

	public void setID_TIPO_TIPOLOGIA_DOSIFICADORES(Integer iD_TIPO_TIPOLOGIA_DOSIFICADORES) {
		ID_TIPO_TIPOLOGIA_DOSIFICADORES = iD_TIPO_TIPOLOGIA_DOSIFICADORES;
	}

}
