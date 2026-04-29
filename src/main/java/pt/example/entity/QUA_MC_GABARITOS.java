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
@Table(name = "QUA_MC_GABARITOS")
public class QUA_MC_GABARITOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_GABARITO")
	private Integer ID_GABARITO;
	@Column(name = "NOME_GABARITO")
	private String NOME_GABARITO;
	@Column(name = "CODIGO_GABARITO")
	private String CODIGO_GABARITO;
	@Column(name = "COD_DESENHO_TECNICO")
	private String COD_DESENHO_TECNICO;
	@Column(name = "REFERENCIA")
	private Integer REFERENCIA;
	@Column(name = "ID_SECCAO")
	private Integer ID_SECCAO;
	@Column(name = "DATA_ENTRADA")
	private Date DATA_ENTRADA;
	@Column(name = "ID_RESP_ENTRADA")
	private Integer ID_RESP_ENTRADA;
	@Column(name = "EM_UTILIZACAO")
	private Boolean EM_UTILIZACAO;
	@Column(name = "OBSOLETO")
	private Boolean OBSOLETO;
	@Column(name = "OBSERVACOES")
	private String OBSERVACOES;
	@Column(name = "FOTOGRAFIA")
	private String FOTOGRAFIA;
	@Column(name = "G_DIMENSIONAL")
	private Boolean G_DIMENSIONAL;
	@Column(name = "G_CONTROLO")
	private Boolean G_CONTROLO;
	@Column(name = "G_ISOSTATICO")
	private Boolean G_ISOSTATICO;
	@Column(name = "FREQ_VERIFICACAO_MESES")
	private Integer FREQ_VERIFICACAO_MESES;
	@Column(name = "TOLERANCIA")
	private BigDecimal TOLERANCIA;
	@Column(name = "UNID_MEDIDA_TOLERANCIA")
	private String UNID_MEDIDA_TOLERANCIA;
	@Column(name = "JAN") private Boolean JAN;
	@Column(name = "FEV") private Boolean FEV;
	@Column(name = "MAR") private Boolean MAR;
	@Column(name = "ABR") private Boolean ABR;
	@Column(name = "MAI") private Boolean MAI;
	@Column(name = "JUN") private Boolean JUN;
	@Column(name = "JUL") private Boolean JUL;
	@Column(name = "AGO") private Boolean AGO;
	@Column(name = "SET") private Boolean SET;
	@Column(name = "OUT") private Boolean OUT;
	@Column(name = "NOV") private Boolean NOV;
	@Column(name = "DEZ") private Boolean DEZ;
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

	public Integer getID_GABARITO() { return ID_GABARITO; }
	public void setID_GABARITO(Integer v) { ID_GABARITO = v; }
	public String getNOME_GABARITO() { return NOME_GABARITO; }
	public void setNOME_GABARITO(String v) { NOME_GABARITO = v; }
	public String getCODIGO_GABARITO() { return CODIGO_GABARITO; }
	public void setCODIGO_GABARITO(String v) { CODIGO_GABARITO = v; }
	public String getCOD_DESENHO_TECNICO() { return COD_DESENHO_TECNICO; }
	public void setCOD_DESENHO_TECNICO(String v) { COD_DESENHO_TECNICO = v; }
	public Integer getREFERENCIA() { return REFERENCIA; }
	public void setREFERENCIA(Integer v) { REFERENCIA = v; }
	public Integer getID_SECCAO() { return ID_SECCAO; }
	public void setID_SECCAO(Integer v) { ID_SECCAO = v; }
	public Date getDATA_ENTRADA() { return DATA_ENTRADA; }
	public void setDATA_ENTRADA(Date v) { DATA_ENTRADA = v; }
	public Integer getID_RESP_ENTRADA() { return ID_RESP_ENTRADA; }
	public void setID_RESP_ENTRADA(Integer v) { ID_RESP_ENTRADA = v; }
	public Boolean getEM_UTILIZACAO() { return EM_UTILIZACAO; }
	public void setEM_UTILIZACAO(Boolean v) { EM_UTILIZACAO = v; }
	public Boolean getOBSOLETO() { return OBSOLETO; }
	public void setOBSOLETO(Boolean v) { OBSOLETO = v; }
	public String getOBSERVACOES() { return OBSERVACOES; }
	public void setOBSERVACOES(String v) { OBSERVACOES = v; }
	public String getFOTOGRAFIA() { return FOTOGRAFIA; }
	public void setFOTOGRAFIA(String v) { FOTOGRAFIA = v; }
	public Boolean getG_DIMENSIONAL() { return G_DIMENSIONAL; }
	public void setG_DIMENSIONAL(Boolean v) { G_DIMENSIONAL = v; }
	public Boolean getG_CONTROLO() { return G_CONTROLO; }
	public void setG_CONTROLO(Boolean v) { G_CONTROLO = v; }
	public Boolean getG_ISOSTATICO() { return G_ISOSTATICO; }
	public void setG_ISOSTATICO(Boolean v) { G_ISOSTATICO = v; }
	public Integer getFREQ_VERIFICACAO_MESES() { return FREQ_VERIFICACAO_MESES; }
	public void setFREQ_VERIFICACAO_MESES(Integer v) { FREQ_VERIFICACAO_MESES = v; }
	public BigDecimal getTOLERANCIA() { return TOLERANCIA; }
	public void setTOLERANCIA(BigDecimal v) { TOLERANCIA = v; }
	public String getUNID_MEDIDA_TOLERANCIA() { return UNID_MEDIDA_TOLERANCIA; }
	public void setUNID_MEDIDA_TOLERANCIA(String v) { UNID_MEDIDA_TOLERANCIA = v; }
	public Boolean getJAN() { return JAN; } public void setJAN(Boolean v) { JAN = v; }
	public Boolean getFEV() { return FEV; } public void setFEV(Boolean v) { FEV = v; }
	public Boolean getMAR() { return MAR; } public void setMAR(Boolean v) { MAR = v; }
	public Boolean getABR() { return ABR; } public void setABR(Boolean v) { ABR = v; }
	public Boolean getMAI() { return MAI; } public void setMAI(Boolean v) { MAI = v; }
	public Boolean getJUN() { return JUN; } public void setJUN(Boolean v) { JUN = v; }
	public Boolean getJUL() { return JUL; } public void setJUL(Boolean v) { JUL = v; }
	public Boolean getAGO() { return AGO; } public void setAGO(Boolean v) { AGO = v; }
	public Boolean getSET() { return SET; } public void setSET(Boolean v) { SET = v; }
	public Boolean getOUT() { return OUT; } public void setOUT(Boolean v) { OUT = v; }
	public Boolean getNOV() { return NOV; } public void setNOV(Boolean v) { NOV = v; }
	public Boolean getDEZ() { return DEZ; } public void setDEZ(Boolean v) { DEZ = v; }
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
