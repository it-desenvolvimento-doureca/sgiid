package pt.example.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "QUA_MC_EQUIPAMENTOS_FICHEIROS")
public class QUA_MC_EQUIPAMENTOS_FICHEIROS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_EQUIPAMENTO")
	private Integer ID_EQUIPAMENTO;
	@JsonProperty("DESCRICAO")
	private String DESCRICAO;
	@JsonProperty("NOME")
	private String NOME;
	@JsonProperty("CAMINHO")
	private String CAMINHO;
	@JsonProperty("TIPO")
	private String TIPO;
	@JsonProperty("DATATYPE")
	private String DATATYPE;
	@JsonProperty("TAMANHO")
	private Integer TAMANHO;
	@JsonProperty("FICHEIRO_1")
	private String FICHEIRO_1;
	@JsonProperty("FICHEIRO_2")
	private String FICHEIRO_2;
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

	public Integer getID() { return ID; }
	public void setID(Integer v) { ID = v; }
	public Integer getID_EQUIPAMENTO() { return ID_EQUIPAMENTO; }
	public void setID_EQUIPAMENTO(Integer v) { ID_EQUIPAMENTO = v; }
	public String getDESCRICAO() { return DESCRICAO; }
	public void setDESCRICAO(String v) { DESCRICAO = v; }
	public String getNOME() { return NOME; }
	public void setNOME(String v) { NOME = v; }
	public String getCAMINHO() { return CAMINHO; }
	public void setCAMINHO(String v) { CAMINHO = v; }
	public String getTIPO() { return TIPO; }
	public void setTIPO(String v) { TIPO = v; }
	public String getDATATYPE() { return DATATYPE; }
	public void setDATATYPE(String v) { DATATYPE = v; }
	public Integer getTAMANHO() { return TAMANHO; }
	public void setTAMANHO(Integer v) { TAMANHO = v; }
	public String getFICHEIRO_1() { return FICHEIRO_1; }
	public void setFICHEIRO_1(String v) { FICHEIRO_1 = v; }
	public String getFICHEIRO_2() { return FICHEIRO_2; }
	public void setFICHEIRO_2(String v) { FICHEIRO_2 = v; }
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
