package pt.example.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUA_MC_MAQUINAS")
public class QUA_MC_MAQUINAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MAQUINA")
	private Integer ID_MAQUINA;
	@Column(name = "DESIGNACAO")
	private String DESIGNACAO;
	@Column(name = "NUM_MAQUINA")
	private String NUM_MAQUINA;
	@Column(name = "ID_SECCAO")
	private Integer ID_SECCAO;
	@Column(name = "PARAMETROS")
	private String PARAMETROS;
	@Column(name = "TOLERANCIA")
	private String TOLERANCIA;
	@Column(name = "FREQ_VERIFICACAO")
	private Integer FREQ_VERIFICACAO;
	@Column(name = "REL_ANEXO")
	private String REL_ANEXO;
	@Column(name = "VALOR_EXIGIDO")
	private String VALOR_EXIGIDO;
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

	public Integer getID_MAQUINA() { return ID_MAQUINA; }
	public void setID_MAQUINA(Integer v) { ID_MAQUINA = v; }
	public String getDESIGNACAO() { return DESIGNACAO; }
	public void setDESIGNACAO(String v) { DESIGNACAO = v; }
	public String getNUM_MAQUINA() { return NUM_MAQUINA; }
	public void setNUM_MAQUINA(String v) { NUM_MAQUINA = v; }
	public Integer getID_SECCAO() { return ID_SECCAO; }
	public void setID_SECCAO(Integer v) { ID_SECCAO = v; }
	public String getPARAMETROS() { return PARAMETROS; }
	public void setPARAMETROS(String v) { PARAMETROS = v; }
	public String getTOLERANCIA() { return TOLERANCIA; }
	public void setTOLERANCIA(String v) { TOLERANCIA = v; }
	public Integer getFREQ_VERIFICACAO() { return FREQ_VERIFICACAO; }
	public void setFREQ_VERIFICACAO(Integer v) { FREQ_VERIFICACAO = v; }
	public String getREL_ANEXO() { return REL_ANEXO; }
	public void setREL_ANEXO(String v) { REL_ANEXO = v; }
	public String getVALOR_EXIGIDO() { return VALOR_EXIGIDO; }
	public void setVALOR_EXIGIDO(String v) { VALOR_EXIGIDO = v; }
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
