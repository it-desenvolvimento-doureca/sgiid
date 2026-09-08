package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "QUA_CR_MOV_ENSAIO_LEITURA")
public class QUA_CR_MOV_ENSAIO_LEITURA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_ENSAIO_LEITURA")
	private Integer ID_ENSAIO_LEITURA;
	@JsonProperty("ID_ENSAIO_TESTE")
	private Integer ID_ENSAIO_TESTE;
	@JsonProperty("NUM_LEITURA")
	private Integer NUM_LEITURA;
	@JsonProperty("CAVIDADE")
	private String CAVIDADE;
	@JsonProperty("PESO")
	private BigDecimal PESO;
	@JsonProperty("ID_RESULTADO")
	private Integer ID_RESULTADO;
	@JsonProperty("OBSERVACOES")
	private String OBSERVACOES;
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

	public Integer getID_ENSAIO_LEITURA() { return ID_ENSAIO_LEITURA; }
	public void setID_ENSAIO_LEITURA(Integer v) { ID_ENSAIO_LEITURA = v; }
	public Integer getID_ENSAIO_TESTE() { return ID_ENSAIO_TESTE; }
	public void setID_ENSAIO_TESTE(Integer v) { ID_ENSAIO_TESTE = v; }
	public Integer getNUM_LEITURA() { return NUM_LEITURA; }
	public void setNUM_LEITURA(Integer v) { NUM_LEITURA = v; }
	public String getCAVIDADE() { return CAVIDADE; }
	public void setCAVIDADE(String v) { CAVIDADE = v; }
	public BigDecimal getPESO() { return PESO; }
	public void setPESO(BigDecimal v) { PESO = v; }
	public Integer getID_RESULTADO() { return ID_RESULTADO; }
	public void setID_RESULTADO(Integer v) { ID_RESULTADO = v; }
	public String getOBSERVACOES() { return OBSERVACOES; }
	public void setOBSERVACOES(String v) { OBSERVACOES = v; }
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