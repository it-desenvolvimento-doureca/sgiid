package pt.example.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Linha de pedido de EPI.
 * MOTIVO_ATRASO é obrigatório quando a duração de uso da família já foi
 * ultrapassada desde a última entrega ao destinatário.
 */
@Entity
@Table(name = "QUA_EPI_MOV_PEDIDO_LIN")
public class QUA_EPI_MOV_PEDIDO_LIN {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_LINHA")
	private Integer ID_LINHA;
	@JsonProperty("ID_PEDIDO")
	private Integer ID_PEDIDO;
	@JsonProperty("ID_EPI")
	private Integer ID_EPI;
	@JsonProperty("TAMANHO")
	private String TAMANHO;
	@JsonProperty("QTD_PEDIDA")
	private Integer QTD_PEDIDA;
	@JsonProperty("MOTIVO_ATRASO")
	private String MOTIVO_ATRASO;
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

	public Integer getID_LINHA() { return ID_LINHA; }
	public void setID_LINHA(Integer v) { ID_LINHA = v; }
	public Integer getID_PEDIDO() { return ID_PEDIDO; }
	public void setID_PEDIDO(Integer v) { ID_PEDIDO = v; }
	public Integer getID_EPI() { return ID_EPI; }
	public void setID_EPI(Integer v) { ID_EPI = v; }
	public String getTAMANHO() { return TAMANHO; }
	public void setTAMANHO(String v) { TAMANHO = v; }
	public Integer getQTD_PEDIDA() { return QTD_PEDIDA; }
	public void setQTD_PEDIDA(Integer v) { QTD_PEDIDA = v; }
	public String getMOTIVO_ATRASO() { return MOTIVO_ATRASO; }
	public void setMOTIVO_ATRASO(String v) { MOTIVO_ATRASO = v; }
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
