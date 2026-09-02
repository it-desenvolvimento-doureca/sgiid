package pt.example.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "QUA_EPI_DIC_FAMILIA")
public class QUA_EPI_DIC_FAMILIA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_FAMILIA")
	private Integer ID_FAMILIA;
	@JsonProperty("DESCRICAO")
	private String DESCRICAO;
	// 0 = duração infinita (nunca alerta)
	@JsonProperty("DURACAO_USO_DIAS")
	private Integer DURACAO_USO_DIAS;
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

	public Integer getID_FAMILIA() { return ID_FAMILIA; }
	public void setID_FAMILIA(Integer v) { ID_FAMILIA = v; }
	public String getDESCRICAO() { return DESCRICAO; }
	public void setDESCRICAO(String v) { DESCRICAO = v; }
	public Integer getDURACAO_USO_DIAS() { return DURACAO_USO_DIAS; }
	public void setDURACAO_USO_DIAS(Integer v) { DURACAO_USO_DIAS = v; }
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


