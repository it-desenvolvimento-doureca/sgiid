package pt.example.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entrega (levantamento) de um pedido de EPI.
 * As assinaturas são guardadas em base64, só o payload (sem o prefixo
 * data:image/png;base64,), em colunas VARCHAR(MAX).
 */
@Entity
@Table(name = "QUA_EPI_MOV_ENTREGA")
public class QUA_EPI_MOV_ENTREGA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_ENTREGA")
	private Integer ID_ENTREGA;
	@JsonProperty("ID_PEDIDO")
	private Integer ID_PEDIDO;
	// FK -> QUA_EPI_LOCAL.ID
	@JsonProperty("ID_LOCAL")
	private Integer ID_LOCAL;
	@JsonProperty("DATA_HORA_ENTREGA")
	private Timestamp DATA_HORA_ENTREGA;
	@JsonProperty("ID_FUNC_ENTREGA")
	private Integer ID_FUNC_ENTREGA;
	@JsonProperty("ASSINATURA_REQUERENTE")
	private String ASSINATURA_REQUERENTE;
	@JsonProperty("ASSINATURA_ENTREGA")
	private String ASSINATURA_ENTREGA;
	@JsonProperty("DATA_SINCRO_SILVER")
	private Timestamp DATA_SINCRO_SILVER;
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

	// RASCUNHO | CONCLUIDA
	@JsonProperty("ESTADO")
	private String ESTADO;

	public String getESTADO() { return ESTADO; }
	public void setESTADO(String v) { ESTADO = v; }

	public Integer getID_ENTREGA() { return ID_ENTREGA; }
	public void setID_ENTREGA(Integer v) { ID_ENTREGA = v; }
	public Integer getID_PEDIDO() { return ID_PEDIDO; }
	public void setID_PEDIDO(Integer v) { ID_PEDIDO = v; }
	public Integer getID_LOCAL() { return ID_LOCAL; }
	public void setID_LOCAL(Integer v) { ID_LOCAL = v; }
	public Timestamp getDATA_HORA_ENTREGA() { return DATA_HORA_ENTREGA; }
	public void setDATA_HORA_ENTREGA(Timestamp v) { DATA_HORA_ENTREGA = v; }
	public Integer getID_FUNC_ENTREGA() { return ID_FUNC_ENTREGA; }
	public void setID_FUNC_ENTREGA(Integer v) { ID_FUNC_ENTREGA = v; }
	public String getASSINATURA_REQUERENTE() { return ASSINATURA_REQUERENTE; }
	public void setASSINATURA_REQUERENTE(String v) { ASSINATURA_REQUERENTE = v; }
	public String getASSINATURA_ENTREGA() { return ASSINATURA_ENTREGA; }
	public void setASSINATURA_ENTREGA(String v) { ASSINATURA_ENTREGA = v; }
	public Timestamp getDATA_SINCRO_SILVER() { return DATA_SINCRO_SILVER; }
	public void setDATA_SINCRO_SILVER(Timestamp v) { DATA_SINCRO_SILVER = v; }
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


