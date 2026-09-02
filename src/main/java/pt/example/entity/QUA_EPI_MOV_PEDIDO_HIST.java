package pt.example.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Histórico de mudanças de estado de um pedido (aceitação, rejeição, agendamento). */
@Entity
@Table(name = "QUA_EPI_MOV_PEDIDO_HIST")
public class QUA_EPI_MOV_PEDIDO_HIST {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_PEDIDO")
	private Integer ID_PEDIDO;
	@JsonProperty("DATA_HORA")
	private Timestamp DATA_HORA;
	@JsonProperty("ID_UTILIZADOR")
	private Integer ID_UTILIZADOR;
	@JsonProperty("ESTADO_ANTERIOR")
	private String ESTADO_ANTERIOR;
	@JsonProperty("ESTADO_NOVO")
	private String ESTADO_NOVO;
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

	public Integer getID() { return ID; }
	public void setID(Integer v) { ID = v; }
	public Integer getID_PEDIDO() { return ID_PEDIDO; }
	public void setID_PEDIDO(Integer v) { ID_PEDIDO = v; }
	public Timestamp getDATA_HORA() { return DATA_HORA; }
	public void setDATA_HORA(Timestamp v) { DATA_HORA = v; }
	public Integer getID_UTILIZADOR() { return ID_UTILIZADOR; }
	public void setID_UTILIZADOR(Integer v) { ID_UTILIZADOR = v; }
	public String getESTADO_ANTERIOR() { return ESTADO_ANTERIOR; }
	public void setESTADO_ANTERIOR(String v) { ESTADO_ANTERIOR = v; }
	public String getESTADO_NOVO() { return ESTADO_NOVO; }
	public void setESTADO_NOVO(String v) { ESTADO_NOVO = v; }
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


