package pt.example.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * EPIs atribuídos a cada funcionário.
 * Uma linha por família aplicável ao sector do funcionário, com o EPI e
 * tamanho escolhidos para essa pessoa. Alimenta as linhas do pedido.
 */
@Entity
@Table(name = "QUA_EPI_FUNC")
public class QUA_EPI_FUNC {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	// FK -> RH_FUNCIONARIOS.COD_FUNCIONARIO
	@JsonProperty("COD_FUNCIONARIO")
	private Integer COD_FUNCIONARIO;
	@JsonProperty("ID_FAMILIA")
	private Integer ID_FAMILIA;
	@JsonProperty("ID_EPI")
	private Integer ID_EPI;
	@JsonProperty("TAMANHO")
	private String TAMANHO;
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

	public Integer getID() { return ID; }
	public void setID(Integer v) { ID = v; }
	public Integer getCOD_FUNCIONARIO() { return COD_FUNCIONARIO; }
	public void setCOD_FUNCIONARIO(Integer v) { COD_FUNCIONARIO = v; }
	public Integer getID_FAMILIA() { return ID_FAMILIA; }
	public void setID_FAMILIA(Integer v) { ID_FAMILIA = v; }
	public Integer getID_EPI() { return ID_EPI; }
	public void setID_EPI(Integer v) { ID_EPI = v; }
	public String getTAMANHO() { return TAMANHO; }
	public void setTAMANHO(String v) { TAMANHO = v; }
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


