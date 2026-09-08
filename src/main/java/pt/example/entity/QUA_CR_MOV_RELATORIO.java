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
@Table(name = "QUA_CR_MOV_RELATORIO")
public class QUA_CR_MOV_RELATORIO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_RELATORIO")
	private Integer ID_RELATORIO;
	@JsonProperty("N_RELAT_CR")
	private String N_RELAT_CR;
	@JsonProperty("ANO")
	private Integer ANO;
	@JsonProperty("NUM_SEQ")
	private Integer NUM_SEQ;
	@JsonProperty("ID_REFERENCIA")
	private Integer ID_REFERENCIA;
	@JsonProperty("ID_TIPO_ENSAIO")
	private Integer ID_TIPO_ENSAIO;
	@JsonProperty("ID_LINHA")
	private Integer ID_LINHA;
	@JsonProperty("ID_LOCAL_PRODUCAO")
	private Integer ID_LOCAL_PRODUCAO;
	@JsonProperty("LOTE")
	private String LOTE;
	@JsonProperty("DATA_HORA_PRODUCAO")
	private Timestamp DATA_HORA_PRODUCAO;
	@JsonProperty("DATA_REGISTO")
	private Timestamp DATA_REGISTO;
	@JsonProperty("DATA_ENTRADA_LAB")
	private Date DATA_ENTRADA_LAB;
	@JsonProperty("DATA_RECECAO")
	private Date DATA_RECECAO;
	@JsonProperty("FAZ_ESPESSURAS")
	private Boolean FAZ_ESPESSURAS;
	@JsonProperty("FAZ_PAUTAS")
	private Boolean FAZ_PAUTAS;
	@JsonProperty("FAZ_CORROSAO")
	private Boolean FAZ_CORROSAO;
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

	public Integer getID_RELATORIO() { return ID_RELATORIO; }
	public void setID_RELATORIO(Integer v) { ID_RELATORIO = v; }
	public String getN_RELAT_CR() { return N_RELAT_CR; }
	public void setN_RELAT_CR(String v) { N_RELAT_CR = v; }
	public Integer getANO() { return ANO; }
	public void setANO(Integer v) { ANO = v; }
	public Integer getNUM_SEQ() { return NUM_SEQ; }
	public void setNUM_SEQ(Integer v) { NUM_SEQ = v; }
	public Integer getID_REFERENCIA() { return ID_REFERENCIA; }
	public void setID_REFERENCIA(Integer v) { ID_REFERENCIA = v; }
	public Integer getID_TIPO_ENSAIO() { return ID_TIPO_ENSAIO; }
	public void setID_TIPO_ENSAIO(Integer v) { ID_TIPO_ENSAIO = v; }
	public Integer getID_LINHA() { return ID_LINHA; }
	public void setID_LINHA(Integer v) { ID_LINHA = v; }
	public Integer getID_LOCAL_PRODUCAO() { return ID_LOCAL_PRODUCAO; }
	public void setID_LOCAL_PRODUCAO(Integer v) { ID_LOCAL_PRODUCAO = v; }
	public String getLOTE() { return LOTE; }
	public void setLOTE(String v) { LOTE = v; }
	public Timestamp getDATA_HORA_PRODUCAO() { return DATA_HORA_PRODUCAO; }
	public void setDATA_HORA_PRODUCAO(Timestamp v) { DATA_HORA_PRODUCAO = v; }
	public Timestamp getDATA_REGISTO() { return DATA_REGISTO; }
	public void setDATA_REGISTO(Timestamp v) { DATA_REGISTO = v; }
	public Date getDATA_ENTRADA_LAB() { return DATA_ENTRADA_LAB; }
	public void setDATA_ENTRADA_LAB(Date v) { DATA_ENTRADA_LAB = v; }
	public Date getDATA_RECECAO() { return DATA_RECECAO; }
	public void setDATA_RECECAO(Date v) { DATA_RECECAO = v; }
	public Boolean getFAZ_ESPESSURAS() { return FAZ_ESPESSURAS; }
	public void setFAZ_ESPESSURAS(Boolean v) { FAZ_ESPESSURAS = v; }
	public Boolean getFAZ_PAUTAS() { return FAZ_PAUTAS; }
	public void setFAZ_PAUTAS(Boolean v) { FAZ_PAUTAS = v; }
	public Boolean getFAZ_CORROSAO() { return FAZ_CORROSAO; }
	public void setFAZ_CORROSAO(Boolean v) { FAZ_CORROSAO = v; }
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