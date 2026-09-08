package pt.example.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "QUA_CR_MOV_CORROSAO_LEITURA")
public class QUA_CR_MOV_CORROSAO_LEITURA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_CORROSAO_LEITURA")
	private Integer ID_CORROSAO_LEITURA;
	@JsonProperty("ID_CORROSAO_AMOSTRA")
	private Integer ID_CORROSAO_AMOSTRA;
	@JsonProperty("ID_PATAMAR")
	private Integer ID_PATAMAR;
	@JsonProperty("RESULTADO")
	private String RESULTADO;
	@JsonProperty("CLASSIFICACAO")
	private String CLASSIFICACAO;
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

	public Integer getID_CORROSAO_LEITURA() { return ID_CORROSAO_LEITURA; }
	public void setID_CORROSAO_LEITURA(Integer v) { ID_CORROSAO_LEITURA = v; }
	public Integer getID_CORROSAO_AMOSTRA() { return ID_CORROSAO_AMOSTRA; }
	public void setID_CORROSAO_AMOSTRA(Integer v) { ID_CORROSAO_AMOSTRA = v; }
	public Integer getID_PATAMAR() { return ID_PATAMAR; }
	public void setID_PATAMAR(Integer v) { ID_PATAMAR = v; }
	public String getRESULTADO() { return RESULTADO; }
	public void setRESULTADO(String v) { RESULTADO = v; }
	public String getCLASSIFICACAO() { return CLASSIFICACAO; }
	public void setCLASSIFICACAO(String v) { CLASSIFICACAO = v; }
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