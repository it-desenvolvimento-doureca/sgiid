package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Etiqueta entregue. Espelha PIN_MOV_PREPARACAO_ETIQ (consumo da pintura).
 * ETQNUM = SETQDE.ETQNUM no SILVER; LIECOD = armazém; EMPCOD = localização.
 */
@Entity
@Table(name = "QUA_EPI_MOV_ENTREGA_ETIQ")
public class QUA_EPI_MOV_ENTREGA_ETIQ {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_LINHA")
	private Integer ID_LINHA;
	@JsonProperty("ID_ENTREGA")
	private Integer ID_ENTREGA;
	@JsonProperty("ID_PEDIDO_LIN")
	private Integer ID_PEDIDO_LIN;
	@JsonProperty("ID_EPI")
	private Integer ID_EPI;
	@JsonProperty("ETQNUM")
	private String ETQNUM;
	@JsonProperty("PROREF")
	private String PROREF;
	@JsonProperty("LIECOD")
	private String LIECOD;
	@JsonProperty("EMPCOD")
	private String EMPCOD;
	@JsonProperty("ETQORILOT1")
	private String ETQORILOT1;
	@JsonProperty("LOTNUMENR")
	private String LOTNUMENR;
	@JsonProperty("UNISTO")
	private String UNISTO;
	// Quantidade na etiqueta ao ler
	@JsonProperty("QUANT")
	private BigDecimal QUANT;
	// Quantidade entregue
	@JsonProperty("CONSUMIR")
	private BigDecimal CONSUMIR;
	@JsonProperty("QUANT_FINAL")
	private BigDecimal QUANT_FINAL;
	@JsonProperty("OBRIGA_DEVOLUCAO")
	private Boolean OBRIGA_DEVOLUCAO;
	@JsonProperty("DEVOLVIDO")
	private Boolean DEVOLVIDO;
	@JsonProperty("DATA_DEVOLUCAO")
	private Timestamp DATA_DEVOLUCAO;
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

	public Integer getID_LINHA() { return ID_LINHA; }
	public void setID_LINHA(Integer v) { ID_LINHA = v; }
	public Integer getID_ENTREGA() { return ID_ENTREGA; }
	public void setID_ENTREGA(Integer v) { ID_ENTREGA = v; }
	public Integer getID_PEDIDO_LIN() { return ID_PEDIDO_LIN; }
	public void setID_PEDIDO_LIN(Integer v) { ID_PEDIDO_LIN = v; }
	public Integer getID_EPI() { return ID_EPI; }
	public void setID_EPI(Integer v) { ID_EPI = v; }
	public String getETQNUM() { return ETQNUM; }
	public void setETQNUM(String v) { ETQNUM = v; }
	public String getPROREF() { return PROREF; }
	public void setPROREF(String v) { PROREF = v; }
	public String getLIECOD() { return LIECOD; }
	public void setLIECOD(String v) { LIECOD = v; }
	public String getEMPCOD() { return EMPCOD; }
	public void setEMPCOD(String v) { EMPCOD = v; }
	public String getETQORILOT1() { return ETQORILOT1; }
	public void setETQORILOT1(String v) { ETQORILOT1 = v; }
	public String getLOTNUMENR() { return LOTNUMENR; }
	public void setLOTNUMENR(String v) { LOTNUMENR = v; }
	public String getUNISTO() { return UNISTO; }
	public void setUNISTO(String v) { UNISTO = v; }
	public BigDecimal getQUANT() { return QUANT; }
	public void setQUANT(BigDecimal v) { QUANT = v; }
	public BigDecimal getCONSUMIR() { return CONSUMIR; }
	public void setCONSUMIR(BigDecimal v) { CONSUMIR = v; }
	public BigDecimal getQUANT_FINAL() { return QUANT_FINAL; }
	public void setQUANT_FINAL(BigDecimal v) { QUANT_FINAL = v; }
	public Boolean getOBRIGA_DEVOLUCAO() { return OBRIGA_DEVOLUCAO; }
	public void setOBRIGA_DEVOLUCAO(Boolean v) { OBRIGA_DEVOLUCAO = v; }
	public Boolean getDEVOLVIDO() { return DEVOLVIDO; }
	public void setDEVOLVIDO(Boolean v) { DEVOLVIDO = v; }
	public Timestamp getDATA_DEVOLUCAO() { return DATA_DEVOLUCAO; }
	public void setDATA_DEVOLUCAO(Timestamp v) { DATA_DEVOLUCAO = v; }
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


