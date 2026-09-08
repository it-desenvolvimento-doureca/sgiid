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
@Table(name = "QUA_CR_MOV_ENSAIO_CAB")
public class QUA_CR_MOV_ENSAIO_CAB {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_ENSAIO_CAB")
	private Integer ID_ENSAIO_CAB;
	@JsonProperty("ID_RELATORIO")
	private Integer ID_RELATORIO;
	@JsonProperty("DATA_TESTES")
	private Date DATA_TESTES;
	@JsonProperty("ID_OPERADOR")
	private Integer ID_OPERADOR;
	@JsonProperty("RESULTADO_TOTAL")
	private String RESULTADO_TOTAL;
	@JsonProperty("OBSERVACOES")
	private String OBSERVACOES;
	@JsonProperty("ID_ACCESS_LEGADO")
	private Integer ID_ACCESS_LEGADO;
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

	public Integer getID_ENSAIO_CAB() { return ID_ENSAIO_CAB; }
	public void setID_ENSAIO_CAB(Integer v) { ID_ENSAIO_CAB = v; }
	public Integer getID_RELATORIO() { return ID_RELATORIO; }
	public void setID_RELATORIO(Integer v) { ID_RELATORIO = v; }
	public Date getDATA_TESTES() { return DATA_TESTES; }
	public void setDATA_TESTES(Date v) { DATA_TESTES = v; }
	public Integer getID_OPERADOR() { return ID_OPERADOR; }
	public void setID_OPERADOR(Integer v) { ID_OPERADOR = v; }
	public String getRESULTADO_TOTAL() { return RESULTADO_TOTAL; }
	public void setRESULTADO_TOTAL(String v) { RESULTADO_TOTAL = v; }
	public String getOBSERVACOES() { return OBSERVACOES; }
	public void setOBSERVACOES(String v) { OBSERVACOES = v; }
	public Integer getID_ACCESS_LEGADO() { return ID_ACCESS_LEGADO; }
	public void setID_ACCESS_LEGADO(Integer v) { ID_ACCESS_LEGADO = v; }
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