package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUA_MC_MOV_VERIF_INT_GABARITO")
public class QUA_MC_MOV_VERIF_INT_GABARITO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VERIF_INT_GABARITO")
	private Integer ID_VERIF_INT_GABARITO;
	@Column(name = "ID_VERIF_GABARITO")
	private Integer ID_VERIF_GABARITO;
	@Column(name = "NUM_MEDIDA")
	private Integer NUM_MEDIDA;
	@Column(name = "TIPO_MEDIDA")
	private Integer TIPO_MEDIDA;
	@Column(name = "VALOR")
	private BigDecimal VALOR;
	@Column(name = "UNIDADES")
	private String UNIDADES;
	@Column(name = "TOLERANCIA")
	private BigDecimal TOLERANCIA;
	@Column(name = "REF_SUBFORM")
	private BigDecimal REF_SUBFORM;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "DATA_CRIA")
	private Date DATA_CRIA;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "DATA_MODIF")
	private Date DATA_MODIF;
	@Column(name = "UTZ_ANULA")
	private Integer UTZ_ANULA;
	@Column(name = "DATA_ANULA")
	private Date DATA_ANULA;
	@Column(name = "ATIVO")
	private Boolean ATIVO;

	public Integer getID_VERIF_INT_GABARITO() { return ID_VERIF_INT_GABARITO; }
	public void setID_VERIF_INT_GABARITO(Integer v) { ID_VERIF_INT_GABARITO = v; }
	public Integer getID_VERIF_GABARITO() { return ID_VERIF_GABARITO; }
	public void setID_VERIF_GABARITO(Integer v) { ID_VERIF_GABARITO = v; }
	public Integer getNUM_MEDIDA() { return NUM_MEDIDA; }
	public void setNUM_MEDIDA(Integer v) { NUM_MEDIDA = v; }
	public Integer getTIPO_MEDIDA() { return TIPO_MEDIDA; }
	public void setTIPO_MEDIDA(Integer v) { TIPO_MEDIDA = v; }
	public BigDecimal getVALOR() { return VALOR; }
	public void setVALOR(BigDecimal v) { VALOR = v; }
	public String getUNIDADES() { return UNIDADES; }
	public void setUNIDADES(String v) { UNIDADES = v; }
	public BigDecimal getTOLERANCIA() { return TOLERANCIA; }
	public void setTOLERANCIA(BigDecimal v) { TOLERANCIA = v; }
	public BigDecimal getREF_SUBFORM() { return REF_SUBFORM; }
	public void setREF_SUBFORM(BigDecimal v) { REF_SUBFORM = v; }
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
