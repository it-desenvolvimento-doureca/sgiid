package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_DIC_PRE_SET")
public class PIN_DIC_PRE_SET {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_TIPO_ACABAMENTO")
	private Integer ID_TIPO_ACABAMENTO;
	@JsonProperty("NOME")
	private String NOME;
	@JsonProperty("CODIGO")
	private String CODIGO;
	@JsonProperty("CAUDAL")
	private String CAUDAL;
	@JsonProperty("PRESSAO_ATOMIZACAO")
	private String PRESSAO_ATOMIZACAO;
	@JsonProperty("PRESSAO_LEQUE")
	private String PRESSAO_LEQUE;
	@JsonProperty("VELOCIDADE")
	private String VELOCIDADE;
	@JsonProperty("TAMANHO_BICO")
	private String TAMANHO_BICO;
	@JsonProperty("VOLTAS_PISTOLA")
	private String VOLTAS_PISTOLA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty("UTZ_ANULA")
	private Integer UTZ_ANULA;
	@JsonProperty("DATA_ANULA")
	private Timestamp DATA_ANULA;
	@JsonProperty("ATIVO")
	private Boolean ATIVO;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getID_TIPO_ACABAMENTO() {
		return ID_TIPO_ACABAMENTO;
	}

	public void setID_TIPO_ACABAMENTO(Integer iD_TIPO_ACABAMENTO) {
		ID_TIPO_ACABAMENTO = iD_TIPO_ACABAMENTO;
	}

	public String getCODIGO() {
		return CODIGO;
	}

	public void setCODIGO(String cODIGO) {
		CODIGO = cODIGO;
	}

	public String getCAUDAL() {
		return CAUDAL;
	}

	public void setCAUDAL(String cAUDAL) {
		CAUDAL = cAUDAL;
	}

	public String getPRESSAO_ATOMIZACAO() {
		return PRESSAO_ATOMIZACAO;
	}

	public void setPRESSAO_ATOMIZACAO(String pRESSAO_ATOMIZACAO) {
		PRESSAO_ATOMIZACAO = pRESSAO_ATOMIZACAO;
	}

	public String getPRESSAO_LEQUE() {
		return PRESSAO_LEQUE;
	}

	public void setPRESSAO_LEQUE(String pRESSAO_LEQUE) {
		PRESSAO_LEQUE = pRESSAO_LEQUE;
	}

	public String getVELOCIDADE() {
		return VELOCIDADE;
	}

	public void setVELOCIDADE(String vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

	public String getTAMANHO_BICO() {
		return TAMANHO_BICO;
	}

	public void setTAMANHO_BICO(String tAMANHO_BICO) {
		TAMANHO_BICO = tAMANHO_BICO;
	}

	public String getVOLTAS_PISTOLA() {
		return VOLTAS_PISTOLA;
	}

	public void setVOLTAS_PISTOLA(String vOLTAS_PISTOLA) {
		VOLTAS_PISTOLA = vOLTAS_PISTOLA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public Integer getUTZ_ANULA() {
		return UTZ_ANULA;
	}

	public void setUTZ_ANULA(Integer uTZ_ANULA) {
		UTZ_ANULA = uTZ_ANULA;
	}

	public Timestamp getDATA_ANULA() {
		return DATA_ANULA;
	}

	public void setDATA_ANULA(Timestamp dATA_ANULA) {
		DATA_ANULA = dATA_ANULA;
	}

	public Boolean getATIVO() {
		return ATIVO;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

}
