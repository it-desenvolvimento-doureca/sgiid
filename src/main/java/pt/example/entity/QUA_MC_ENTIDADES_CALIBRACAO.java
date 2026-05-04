package pt.example.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "QUA_MC_ENTIDADES_CALIBRACAO")
public class QUA_MC_ENTIDADES_CALIBRACAO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_ENTIDADE_CALIBRACAO")
	private Integer ID_ENTIDADE_CALIBRACAO;
	@JsonProperty("NOME_ENTIDADE")
	private String NOME_ENTIDADE;
	@JsonProperty("ABREVIATURA")
	private String ABREVIATURA;
	@JsonProperty("MORADA")
	private String MORADA;
	@JsonProperty("TELEFONE")
	private String TELEFONE;
	@JsonProperty("FAX")
	private String FAX;
	@JsonProperty("EMAIL")
	private String EMAIL;
	@JsonProperty("TECNICO")
	private String TECNICO;
	@JsonProperty("OBSERVACOES")
	private String OBSERVACOES;
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

	public Integer getID_ENTIDADE_CALIBRACAO() { return ID_ENTIDADE_CALIBRACAO; }
	public void setID_ENTIDADE_CALIBRACAO(Integer v) { ID_ENTIDADE_CALIBRACAO = v; }
	public String getNOME_ENTIDADE() { return NOME_ENTIDADE; }
	public void setNOME_ENTIDADE(String v) { NOME_ENTIDADE = v; }
	public String getABREVIATURA() { return ABREVIATURA; }
	public void setABREVIATURA(String v) { ABREVIATURA = v; }
	public String getMORADA() { return MORADA; }
	public void setMORADA(String v) { MORADA = v; }
	public String getTELEFONE() { return TELEFONE; }
	public void setTELEFONE(String v) { TELEFONE = v; }
	public String getFAX() { return FAX; }
	public void setFAX(String v) { FAX = v; }
	public String getEMAIL() { return EMAIL; }
	public void setEMAIL(String v) { EMAIL = v; }
	public String getTECNICO() { return TECNICO; }
	public void setTECNICO(String v) { TECNICO = v; }
	public String getOBSERVACOES() { return OBSERVACOES; }
	public void setOBSERVACOES(String v) { OBSERVACOES = v; }
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
