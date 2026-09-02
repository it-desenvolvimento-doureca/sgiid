package pt.example.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Dicionário de EPIs do módulo de Qualidade.
 *
 * Separado de RH_DIC_EPI de propósito: aquela serve o lookup dos Acidentes
 * de Trabalho e não deve ganhar os campos deste módulo.
 */
@Entity
@Table(name = "QUA_EPI_DIC_EPI")
public class QUA_EPI_DIC_EPI {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_EPI")
	private Integer ID_EPI;
	@JsonProperty("DESCRICAO")
	private String DESCRICAO;
	@JsonProperty("ID_FAMILIA")
	private Integer ID_FAMILIA;
	@JsonProperty("OBRIGA_DEVOLUCAO")
	private Boolean OBRIGA_DEVOLUCAO;
	// Artigo no SILVER (SDTPRA.PROREF) e respetiva descrição (PRODES1)
	@JsonProperty("PROREF")
	private String PROREF;
	@JsonProperty("PROREF_DESCRICAO")
	private String PROREF_DESCRICAO;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_ANULA")
	private Integer UTZ_ANULA;
	@JsonProperty("DATA_ANULA")
	private Timestamp DATA_ANULA;
	@JsonProperty("ATIVO")
	private Boolean ATIVO;

	public Integer getID_EPI() { return ID_EPI; }
	public void setID_EPI(Integer v) { ID_EPI = v; }
	public String getDESCRICAO() { return DESCRICAO; }
	public void setDESCRICAO(String v) { DESCRICAO = v; }
	public Integer getID_FAMILIA() { return ID_FAMILIA; }
	public void setID_FAMILIA(Integer v) { ID_FAMILIA = v; }
	public Boolean getOBRIGA_DEVOLUCAO() { return OBRIGA_DEVOLUCAO; }
	public void setOBRIGA_DEVOLUCAO(Boolean v) { OBRIGA_DEVOLUCAO = v; }
	public String getPROREF() { return PROREF; }
	public void setPROREF(String v) { PROREF = v; }
	public String getPROREF_DESCRICAO() { return PROREF_DESCRICAO; }
	public void setPROREF_DESCRICAO(String v) { PROREF_DESCRICAO = v; }
	public Integer getUTZ_CRIA() { return UTZ_CRIA; }
	public void setUTZ_CRIA(Integer v) { UTZ_CRIA = v; }
	public Timestamp getDATA_CRIA() { return DATA_CRIA; }
	public void setDATA_CRIA(Timestamp v) { DATA_CRIA = v; }
	public Integer getUTZ_MODIF() { return UTZ_MODIF; }
	public void setUTZ_MODIF(Integer v) { UTZ_MODIF = v; }
	public Timestamp getDATA_MODIF() { return DATA_MODIF; }
	public void setDATA_MODIF(Timestamp v) { DATA_MODIF = v; }
	public Integer getUTZ_ANULA() { return UTZ_ANULA; }
	public void setUTZ_ANULA(Integer v) { UTZ_ANULA = v; }
	public Timestamp getDATA_ANULA() { return DATA_ANULA; }
	public void setDATA_ANULA(Timestamp v) { DATA_ANULA = v; }
	public Boolean getATIVO() { return ATIVO; }
	public void setATIVO(Boolean v) { ATIVO = v; }
}


