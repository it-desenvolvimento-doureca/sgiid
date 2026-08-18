package pt.example.entity;

import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Pedido de EPI.
 *
 * ID_REQUERENTE   -> GER_UTILIZADORES.ID_UTILIZADOR (quem faz login)
 * ID_DESTINATARIO -> RH_FUNCIONARIOS.COD_FUNCIONARIO (quem recebe)
 * A conversão entre os dois faz-se por
 *   GER_UTILIZADORES.COD_UTZ = RH_FUNCIONARIOS.COD_FUNC_ORIGEM
 */
@Entity
@Table(name = "QUA_EPI_MOV_PEDIDO")
public class QUA_EPI_MOV_PEDIDO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_PEDIDO")
	private Integer ID_PEDIDO;
	@JsonProperty("ID_REQUERENTE")
	private Integer ID_REQUERENTE;
	@JsonProperty("COD_SECTOR")
	private Integer COD_SECTOR;
	@JsonProperty("ID_DESTINATARIO")
	private Integer ID_DESTINATARIO;
	@JsonProperty("COD_TURNO")
	private Integer COD_TURNO;
	@JsonProperty("DESCRICAO")
	private String DESCRICAO;
	@JsonProperty("DATA_PEDIDO")
	private Timestamp DATA_PEDIDO;
	// SUBMETIDO | ACEITE | REJEITADO | ENTREGUE
	@JsonProperty("ESTADO")
	private String ESTADO;
	@JsonProperty("DATA_ENTREGA_AGENDADA")
	private Date DATA_ENTREGA_AGENDADA;
	@JsonProperty("HORA_ENTREGA_AGENDADA")
	private String HORA_ENTREGA_AGENDADA;
	// FK -> QUA_EPI_LOCAL.ID (local EPI), definido na aceitação
	@JsonProperty("ID_LOCAL_ENTREGA")
	private Integer ID_LOCAL_ENTREGA;
	@JsonProperty("MOTIVO_REJEICAO")
	private String MOTIVO_REJEICAO;
	@JsonProperty("UTZ_ANALISE")
	private Integer UTZ_ANALISE;
	@JsonProperty("DATA_ANALISE")
	private Timestamp DATA_ANALISE;
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

	public Integer getID_PEDIDO() { return ID_PEDIDO; }
	public void setID_PEDIDO(Integer v) { ID_PEDIDO = v; }
	public Integer getID_REQUERENTE() { return ID_REQUERENTE; }
	public void setID_REQUERENTE(Integer v) { ID_REQUERENTE = v; }
	public Integer getCOD_SECTOR() { return COD_SECTOR; }
	public void setCOD_SECTOR(Integer v) { COD_SECTOR = v; }
	public Integer getID_DESTINATARIO() { return ID_DESTINATARIO; }
	public void setID_DESTINATARIO(Integer v) { ID_DESTINATARIO = v; }
	public Integer getCOD_TURNO() { return COD_TURNO; }
	public void setCOD_TURNO(Integer v) { COD_TURNO = v; }
	public String getDESCRICAO() { return DESCRICAO; }
	public void setDESCRICAO(String v) { DESCRICAO = v; }
	public Timestamp getDATA_PEDIDO() { return DATA_PEDIDO; }
	public void setDATA_PEDIDO(Timestamp v) { DATA_PEDIDO = v; }
	public String getESTADO() { return ESTADO; }
	public void setESTADO(String v) { ESTADO = v; }
	public Date getDATA_ENTREGA_AGENDADA() { return DATA_ENTREGA_AGENDADA; }
	public void setDATA_ENTREGA_AGENDADA(Date v) { DATA_ENTREGA_AGENDADA = v; }
	public String getHORA_ENTREGA_AGENDADA() { return HORA_ENTREGA_AGENDADA; }
	public void setHORA_ENTREGA_AGENDADA(String v) { HORA_ENTREGA_AGENDADA = v; }
	public Integer getID_LOCAL_ENTREGA() { return ID_LOCAL_ENTREGA; }
	public void setID_LOCAL_ENTREGA(Integer v) { ID_LOCAL_ENTREGA = v; }
	public String getMOTIVO_REJEICAO() { return MOTIVO_REJEICAO; }
	public void setMOTIVO_REJEICAO(String v) { MOTIVO_REJEICAO = v; }
	public Integer getUTZ_ANALISE() { return UTZ_ANALISE; }
	public void setUTZ_ANALISE(Integer v) { UTZ_ANALISE = v; }
	public Timestamp getDATA_ANALISE() { return DATA_ANALISE; }
	public void setDATA_ANALISE(Timestamp v) { DATA_ANALISE = v; }
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
