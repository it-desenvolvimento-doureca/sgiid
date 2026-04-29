package pt.example.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUA_MC_EQUIPAMENTOS")
public class QUA_MC_EQUIPAMENTOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EQUIPAMENTO")
	private Integer ID_EQUIPAMENTO;
	@Column(name = "DESIGNACAO")
	private String DESIGNACAO;
	@Column(name = "COD_INTERNO")
	private String COD_INTERNO;
	@Column(name = "N_SERIE")
	private String N_SERIE;
	@Column(name = "MODELO")
	private String MODELO;
	@Column(name = "FABRICANTE")
	private String FABRICANTE;
	@Column(name = "FORNECEDOR")
	private String FORNECEDOR;
	@Column(name = "FUNCAO")
	private String FUNCAO;
	@Column(name = "DATA_AQUISICAO")
	private Date DATA_AQUISICAO;
	@Column(name = "ID_SECCAO")
	private Integer ID_SECCAO;
	@Column(name = "ID_RESP_ENTRADA")
	private Integer ID_RESP_ENTRADA;
	@Column(name = "CALIBRACAO_SIM")
	private Boolean CALIBRACAO_SIM;
	@Column(name = "CALIBRACAO_NAO")
	private Boolean CALIBRACAO_NAO;
	@Column(name = "CALIBR_INTERNA")
	private Boolean CALIBR_INTERNA;
	@Column(name = "CALIBR_EXTERNA")
	private Boolean CALIBR_EXTERNA;
	@Column(name = "INTERCALIB_INT_MESES")
	private Integer INTERCALIB_INT_MESES;
	@Column(name = "INTERCALIB_EXT_MESES")
	private Integer INTERCALIB_EXT_MESES;
	@Column(name = "ESTUDOS_R_R")
	private Boolean ESTUDOS_R_R;
	@Column(name = "FREQ_ESTUDO_R_R")
	private Integer FREQ_ESTUDO_R_R;
	@Column(name = "EM_UTILIZACAO")
	private Boolean EM_UTILIZACAO;
	@Column(name = "OBSOLETO")
	private Boolean OBSOLETO;
	@Column(name = "OBSERVACOES")
	private String OBSERVACOES;
	@Column(name = "ERRO")
	private String ERRO;
	@Column(name = "GAMA_UTILIZACAO")
	private String GAMA_UTILIZACAO;
	@Column(name = "NORMAS")
	private String NORMAS;
	@Column(name = "IMAGEM_IT_OPERACIONAL")
	private String IMAGEM_IT_OPERACIONAL;
	@Column(name = "FOTO")
	private String FOTO;
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

	public Integer getID_EQUIPAMENTO() { return ID_EQUIPAMENTO; }
	public void setID_EQUIPAMENTO(Integer v) { ID_EQUIPAMENTO = v; }
	public String getDESIGNACAO() { return DESIGNACAO; }
	public void setDESIGNACAO(String v) { DESIGNACAO = v; }
	public String getCOD_INTERNO() { return COD_INTERNO; }
	public void setCOD_INTERNO(String v) { COD_INTERNO = v; }
	public String getN_SERIE() { return N_SERIE; }
	public void setN_SERIE(String v) { N_SERIE = v; }
	public String getMODELO() { return MODELO; }
	public void setMODELO(String v) { MODELO = v; }
	public String getFABRICANTE() { return FABRICANTE; }
	public void setFABRICANTE(String v) { FABRICANTE = v; }
	public String getFORNECEDOR() { return FORNECEDOR; }
	public void setFORNECEDOR(String v) { FORNECEDOR = v; }
	public String getFUNCAO() { return FUNCAO; }
	public void setFUNCAO(String v) { FUNCAO = v; }
	public Date getDATA_AQUISICAO() { return DATA_AQUISICAO; }
	public void setDATA_AQUISICAO(Date v) { DATA_AQUISICAO = v; }
	public Integer getID_SECCAO() { return ID_SECCAO; }
	public void setID_SECCAO(Integer v) { ID_SECCAO = v; }
	public Integer getID_RESP_ENTRADA() { return ID_RESP_ENTRADA; }
	public void setID_RESP_ENTRADA(Integer v) { ID_RESP_ENTRADA = v; }
	public Boolean getCALIBRACAO_SIM() { return CALIBRACAO_SIM; }
	public void setCALIBRACAO_SIM(Boolean v) { CALIBRACAO_SIM = v; }
	public Boolean getCALIBRACAO_NAO() { return CALIBRACAO_NAO; }
	public void setCALIBRACAO_NAO(Boolean v) { CALIBRACAO_NAO = v; }
	public Boolean getCALIBR_INTERNA() { return CALIBR_INTERNA; }
	public void setCALIBR_INTERNA(Boolean v) { CALIBR_INTERNA = v; }
	public Boolean getCALIBR_EXTERNA() { return CALIBR_EXTERNA; }
	public void setCALIBR_EXTERNA(Boolean v) { CALIBR_EXTERNA = v; }
	public Integer getINTERCALIB_INT_MESES() { return INTERCALIB_INT_MESES; }
	public void setINTERCALIB_INT_MESES(Integer v) { INTERCALIB_INT_MESES = v; }
	public Integer getINTERCALIB_EXT_MESES() { return INTERCALIB_EXT_MESES; }
	public void setINTERCALIB_EXT_MESES(Integer v) { INTERCALIB_EXT_MESES = v; }
	public Boolean getESTUDOS_R_R() { return ESTUDOS_R_R; }
	public void setESTUDOS_R_R(Boolean v) { ESTUDOS_R_R = v; }
	public Integer getFREQ_ESTUDO_R_R() { return FREQ_ESTUDO_R_R; }
	public void setFREQ_ESTUDO_R_R(Integer v) { FREQ_ESTUDO_R_R = v; }
	public Boolean getEM_UTILIZACAO() { return EM_UTILIZACAO; }
	public void setEM_UTILIZACAO(Boolean v) { EM_UTILIZACAO = v; }
	public Boolean getOBSOLETO() { return OBSOLETO; }
	public void setOBSOLETO(Boolean v) { OBSOLETO = v; }
	public String getOBSERVACOES() { return OBSERVACOES; }
	public void setOBSERVACOES(String v) { OBSERVACOES = v; }
	public String getERRO() { return ERRO; }
	public void setERRO(String v) { ERRO = v; }
	public String getGAMA_UTILIZACAO() { return GAMA_UTILIZACAO; }
	public void setGAMA_UTILIZACAO(String v) { GAMA_UTILIZACAO = v; }
	public String getNORMAS() { return NORMAS; }
	public void setNORMAS(String v) { NORMAS = v; }
	public String getIMAGEM_IT_OPERACIONAL() { return IMAGEM_IT_OPERACIONAL; }
	public void setIMAGEM_IT_OPERACIONAL(String v) { IMAGEM_IT_OPERACIONAL = v; }
	public String getFOTO() { return FOTO; }
	public void setFOTO(String v) { FOTO = v; }
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
