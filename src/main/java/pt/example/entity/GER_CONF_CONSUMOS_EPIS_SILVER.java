package pt.example.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Configuração dos consumos de EPI para o SILVER.
 *
 * As entregas de EPI não têm OF, tal como a manutenção. A pintura usa a OF
 * real; aqui os valores são fixos e vêm desta tabela.
 *
 * Sem SECCAO/SUBSECCAO/REF_COMPOSTO preenchidos o ficheiro não é gerado: a
 * entrega grava e o stock é debitado na mesma.
 */
@Entity
@Table(name = "GER_CONF_CONSUMOS_EPIS_SILVER")
public class GER_CONF_CONSUMOS_EPIS_SILVER {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_CONF")
	private Integer ID_CONF;
	@JsonProperty("SECCAO_EPI")
	private String SECCAO_EPI;
	@JsonProperty("SUBSECCAO_EPI")
	private String SUBSECCAO_EPI;
	@JsonProperty("REF_COMPOSTO_EPI")
	private String REF_COMPOSTO_EPI;
	@JsonProperty("OF_EPI")
	private String OF_EPI;
	@JsonProperty("PASTA_FICHEIRO")
	private String PASTA_FICHEIRO;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
	private Date DATA_CRIA;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;
	@JsonProperty("DATA_MODIF")
	private Date DATA_MODIF;
	@JsonProperty("UTZ_ANULA")
	private Integer UTZ_ANULA;
	@JsonProperty("DATA_ANULA")
	private Date DATA_ANULA;
	@JsonProperty("ATIVO")
	private Boolean ATIVO;

	public Integer getID_CONF() { return ID_CONF; }
	public void setID_CONF(Integer v) { ID_CONF = v; }
	public String getSECCAO_EPI() { return SECCAO_EPI; }
	public void setSECCAO_EPI(String v) { SECCAO_EPI = v; }
	public String getSUBSECCAO_EPI() { return SUBSECCAO_EPI; }
	public void setSUBSECCAO_EPI(String v) { SUBSECCAO_EPI = v; }
	public String getREF_COMPOSTO_EPI() { return REF_COMPOSTO_EPI; }
	public void setREF_COMPOSTO_EPI(String v) { REF_COMPOSTO_EPI = v; }
	public String getOF_EPI() { return OF_EPI; }
	public void setOF_EPI(String v) { OF_EPI = v; }
	public String getPASTA_FICHEIRO() { return PASTA_FICHEIRO; }
	public void setPASTA_FICHEIRO(String v) { PASTA_FICHEIRO = v; }
	public Integer getUTZ_CRIA() { return UTZ_CRIA; }
	public void setUTZ_CRIA(Integer v) { UTZ_CRIA = v; }
	public Date getDATA_CRIA() { return DATA_CRIA; }
	public void setDATA_CRIA(Date v) { DATA_CRIA = v; }
	public Integer getUTZ_MODIF() { return UTZ_MODIF; }
	public void setUTZ_MODIF(Integer v) { UTZ_MODIF = v; }
	public Date getDATA_MODIF() { return DATA_MODIF; }
	public void setDATA_MODIF(Date v) { DATA_MODIF = v; }
	public Integer getUTZ_ANULA() { return UTZ_ANULA; }
	public void setUTZ_ANULA(Integer v) { UTZ_ANULA = v; }
	public Date getDATA_ANULA() { return DATA_ANULA; }
	public void setDATA_ANULA(Date v) { DATA_ANULA = v; }
	public Boolean getATIVO() { return ATIVO; }
	public void setATIVO(Boolean v) { ATIVO = v; }
}
