package pt.example.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "PIN_MOV_CONSUMO_TINTAS_LINHAS")
public class PIN_MOV_CONSUMO_TINTAS_LINHAS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_CONSUMO_TINTAS_LINHA")
	private Integer ID_CONSUMO_TINTAS_LINHA;

	@JsonProperty("ID_CONSUMO_TINTAS")
	private Integer ID_CONSUMO_TINTAS;

	@JsonProperty("CABINE")
	private String CABINE;

	@JsonProperty("MISTURA")
	private String MISTURA;

	@JsonProperty("COD_REF")
	private String COD_REF;

	@JsonProperty("DESC_REF")
	private String DESC_REF;

	@JsonProperty("CONSUMO_COR_A_RACK")
	private BigDecimal CONSUMO_COR_A_RACK;

	@JsonProperty("CONSUMO_COR_B_RACK")
	private BigDecimal CONSUMO_COR_B_RACK;

	@JsonProperty("CONSUMO_CARGA_COR_A")
	private BigDecimal CONSUMO_CARGA_COR_A;

	@JsonProperty("CONSUMO_CARGA_CATALIZADOR_B")
	private BigDecimal CONSUMO_CARGA_CATALIZADOR_B;

	@JsonProperty("NUM_RACKS")
	private Integer NUM_RACKS;

	@JsonProperty("CONSUMO_MISTURA")
	private BigDecimal CONSUMO_MISTURA;

	@JsonProperty("QUANT_EXISTENTE_COR_A")
	private BigDecimal QUANT_EXISTENTE_COR_A;

	@JsonProperty("CONSUMO_TOTAL_COR_A")
	private BigDecimal CONSUMO_TOTAL_COR_A;

	@JsonProperty("CONSUMO_TOTAL_CATALIZADOR_B")
	private BigDecimal CONSUMO_TOTAL_CATALIZADOR_B;

	@JsonProperty("QUANTIDADE_ADICIONAL")
	private BigDecimal QUANTIDADE_ADICIONAL;

	@JsonProperty("QUANT_NECESSARIA")
	private BigDecimal QUANT_NECESSARIA;

	@JsonProperty("QUANTIDADE_CARGAS")
	private Integer QUANTIDADE_CARGAS;

	@JsonProperty("PERC_DILUICAO")
	private BigDecimal PERC_DILUICAO;

	@JsonProperty("ORDEM")
	private Integer ORDEM;

	public Integer getID_CONSUMO_TINTAS_LINHA() { return ID_CONSUMO_TINTAS_LINHA; }
	public void setID_CONSUMO_TINTAS_LINHA(Integer id) { this.ID_CONSUMO_TINTAS_LINHA = id; }

	public Integer getID_CONSUMO_TINTAS() { return ID_CONSUMO_TINTAS; }
	public void setID_CONSUMO_TINTAS(Integer id) { this.ID_CONSUMO_TINTAS = id; }

	public String getCABINE() { return CABINE; }
	public void setCABINE(String cabine) { this.CABINE = cabine; }

	public String getMISTURA() { return MISTURA; }
	public void setMISTURA(String mistura) { this.MISTURA = mistura; }

	public String getCOD_REF() { return COD_REF; }
	public void setCOD_REF(String v) { this.COD_REF = v; }

	public String getDESC_REF() { return DESC_REF; }
	public void setDESC_REF(String v) { this.DESC_REF = v; }

	public BigDecimal getCONSUMO_COR_A_RACK() { return CONSUMO_COR_A_RACK; }
	public void setCONSUMO_COR_A_RACK(BigDecimal v) { this.CONSUMO_COR_A_RACK = v; }

	public BigDecimal getCONSUMO_COR_B_RACK() { return CONSUMO_COR_B_RACK; }
	public void setCONSUMO_COR_B_RACK(BigDecimal v) { this.CONSUMO_COR_B_RACK = v; }

	public BigDecimal getCONSUMO_CARGA_COR_A() { return CONSUMO_CARGA_COR_A; }
	public void setCONSUMO_CARGA_COR_A(BigDecimal v) { this.CONSUMO_CARGA_COR_A = v; }

	public BigDecimal getCONSUMO_CARGA_CATALIZADOR_B() { return CONSUMO_CARGA_CATALIZADOR_B; }
	public void setCONSUMO_CARGA_CATALIZADOR_B(BigDecimal v) { this.CONSUMO_CARGA_CATALIZADOR_B = v; }

	public Integer getNUM_RACKS() { return NUM_RACKS; }
	public void setNUM_RACKS(Integer v) { this.NUM_RACKS = v; }

	public BigDecimal getCONSUMO_MISTURA() { return CONSUMO_MISTURA; }
	public void setCONSUMO_MISTURA(BigDecimal v) { this.CONSUMO_MISTURA = v; }

	public BigDecimal getQUANT_EXISTENTE_COR_A() { return QUANT_EXISTENTE_COR_A; }
	public void setQUANT_EXISTENTE_COR_A(BigDecimal v) { this.QUANT_EXISTENTE_COR_A = v; }

	public BigDecimal getCONSUMO_TOTAL_COR_A() { return CONSUMO_TOTAL_COR_A; }
	public void setCONSUMO_TOTAL_COR_A(BigDecimal v) { this.CONSUMO_TOTAL_COR_A = v; }

	public BigDecimal getCONSUMO_TOTAL_CATALIZADOR_B() { return CONSUMO_TOTAL_CATALIZADOR_B; }
	public void setCONSUMO_TOTAL_CATALIZADOR_B(BigDecimal v) { this.CONSUMO_TOTAL_CATALIZADOR_B = v; }

	public BigDecimal getQUANTIDADE_ADICIONAL() { return QUANTIDADE_ADICIONAL; }
	public void setQUANTIDADE_ADICIONAL(BigDecimal v) { this.QUANTIDADE_ADICIONAL = v; }

	public BigDecimal getQUANT_NECESSARIA() { return QUANT_NECESSARIA; }
	public void setQUANT_NECESSARIA(BigDecimal v) { this.QUANT_NECESSARIA = v; }

	public Integer getQUANTIDADE_CARGAS() { return QUANTIDADE_CARGAS; }
	public void setQUANTIDADE_CARGAS(Integer v) { this.QUANTIDADE_CARGAS = v; }

	public BigDecimal getPERC_DILUICAO() { return PERC_DILUICAO; }
	public void setPERC_DILUICAO(BigDecimal v) { this.PERC_DILUICAO = v; }

	public Integer getORDEM() { return ORDEM; }
	public void setORDEM(Integer ordem) { this.ORDEM = ordem; }
}
