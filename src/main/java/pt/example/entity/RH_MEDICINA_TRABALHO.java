package pt.example.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "RH_MEDICINA_TRABALHO")
public class RH_MEDICINA_TRABALHO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("TIPO_CONSULTA")
	private Integer TIPO_CONSULTA;
	@JsonProperty("ID_ENTIDADE_MEDICA")
	private Integer ID_ENTIDADE_MEDICA;
	@JsonProperty("ID_LOCAL")
	private Integer ID_LOCAL;
	@JsonProperty("EXAMES")
	private String EXAMES;
	@JsonProperty("DATA")
	private Date DATA;
	@JsonProperty("ESTADO")
	private Integer ESTADO;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;
	@JsonProperty("UTZ_ANULA")
	private Integer UTZ_ANULA;
	@JsonProperty("DATA_ANULA")
	private Timestamp DATA_ANULA;
	@JsonProperty("ATIVO")
	private Boolean ATIVO;
	@JsonProperty("ID_FUNCIONARIO")
	private String ID_FUNCIONARIO;
	@JsonProperty("OBSERVACOES")
	private String OBSERVACOES;
	@JsonProperty("NOTIFICACAO_ENVIADA")
	private Boolean NOTIFICACAO_ENVIADA;

	public Integer getID() {
		return ID;
	}

	public Integer getTIPO_CONSULTA() {
		return TIPO_CONSULTA;
	}

	public Integer getID_ENTIDADE_MEDICA() {
		return ID_ENTIDADE_MEDICA;
	}

	public Integer getID_LOCAL() {
		return ID_LOCAL;
	}

	public String getEXAMES() {
		return EXAMES;
	}

	public Date getDATA() {
		return DATA;
	}

	public Integer getESTADO() {
		return ESTADO;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public Integer getUTZ_ANULA() {
		return UTZ_ANULA;
	}

	public Timestamp getDATA_ANULA() {
		return DATA_ANULA;
	}

	public Boolean getATIVO() {
		return ATIVO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setTIPO_CONSULTA(Integer tIPO_CONSULTA) {
		TIPO_CONSULTA = tIPO_CONSULTA;
	}

	public void setID_ENTIDADE_MEDICA(Integer iD_ENTIDADE_MEDICA) {
		ID_ENTIDADE_MEDICA = iD_ENTIDADE_MEDICA;
	}

	public void setID_LOCAL(Integer iD_LOCAL) {
		ID_LOCAL = iD_LOCAL;
	}

	public void setEXAMES(String eXAMES) {
		EXAMES = eXAMES;
	}

	public void setDATA(Date dATA) {
		DATA = dATA;
	}

	public void setESTADO(Integer eSTADO) {
		ESTADO = eSTADO;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public void setUTZ_ANULA(Integer uTZ_ANULA) {
		UTZ_ANULA = uTZ_ANULA;
	}

	public void setDATA_ANULA(Timestamp dATA_ANULA) {
		DATA_ANULA = dATA_ANULA;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public String getID_FUNCIONARIO() {
		return ID_FUNCIONARIO;
	}

	public void setID_FUNCIONARIO(String iD_FUNCIONARIO) {
		ID_FUNCIONARIO = iD_FUNCIONARIO;
	}

	public String getOBSERVACOES() {
		return OBSERVACOES;
	}

	public void setOBSERVACOES(String oBSERVACOES) {
		OBSERVACOES = oBSERVACOES;
	}

	public Boolean getNOTIFICACAO_ENVIADA() {
		return NOTIFICACAO_ENVIADA;
	}

	public void setNOTIFICACAO_ENVIADA(Boolean nOTIFICACAO_ENVIADA) {
		NOTIFICACAO_ENVIADA = nOTIFICACAO_ENVIADA;
	}
	

}