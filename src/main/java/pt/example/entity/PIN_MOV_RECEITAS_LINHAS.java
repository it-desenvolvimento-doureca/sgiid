package pt.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_MOV_RECEITAS_LINHAS")
public class PIN_MOV_RECEITAS_LINHAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_RECEITA")
	private Integer ID_RECEITA;
	@JsonProperty("ID_TIPO_ACABAMENTO")
	private Integer ID_TIPO_ACABAMENTO;
	@JsonProperty("UNIDADE")
	private Integer UNIDADE;
	@JsonProperty("ID_POTE")
	private String ID_POTE;
	@JsonProperty("ID_POTE_CATALISADOR")
	private String ID_POTE_CATALISADOR;
	@JsonProperty("REFERENCIA_A")
	private String REFERENCIA_A;
	@JsonProperty("REFERENCIA_B")
	private String REFERENCIA_B;
	@JsonProperty("REFERENCIA_C")
	private String REFERENCIA_C;
	@JsonProperty("REFERENCIA2_C")
	private String REFERENCIA2_C;
	@JsonProperty("REFERENCIA3_C")
	private String REFERENCIA3_C;
	@JsonProperty("REFERENCIA4_C")
	private String REFERENCIA4_C;
	@JsonProperty("REFERENCIA5_C")
	private String REFERENCIA5_C;
	@JsonProperty("VALVULA_POTE")
	private String VALVULA_POTE;
	@JsonProperty("VALVULA_CATALISADOR")
	private String VALVULA_CATALISADOR;
	@JsonProperty("COR_A")
	private String COR_A;
	@JsonProperty("DILUENTE_C")
	private String DILUENTE_C;
	@JsonProperty("DILUENTE2_C")
	private String DILUENTE2_C;
	@JsonProperty("DILUENTE3_C")
	private String DILUENTE3_C;
	@JsonProperty("DILUENTE4_C")
	private String DILUENTE4_C;
	@JsonProperty("DILUENTE5_C")
	private String DILUENTE5_C;
	
	@JsonProperty("ADITIVO")
	private String ADITIVO;
	@JsonProperty("TAXA_MISTURA")
	private String TAXA_MISTURA;
	@JsonProperty("PADRAO_VISCOSIDADE")
	private String PADRAO_VISCOSIDADE;
	@JsonProperty("POT_LIFE")
	private String POT_LIFE;
	@JsonProperty("ARMAZEM_A")
	private String ARMAZEM_A;
	@JsonProperty("ARMAZEM_B")
	private String ARMAZEM_B;
	@JsonProperty("ARMAZEM_C")
	private String ARMAZEM_C;
	@JsonProperty("ARMAZEM_ADITIVO")
	private String ARMAZEM_ADITIVO;
	@JsonProperty("CODIGO_COR")
	private String CODIGO_COR;
	@JsonProperty("ID_REFERENCIA_A")
	private Integer ID_REFERENCIA_A;
	@JsonProperty("ID_REFERENCIA_B")
	private Integer ID_REFERENCIA_B;
	@JsonProperty("ID_REFERENCIA_C")
	private Integer ID_REFERENCIA_C;
	@JsonProperty("ID_REFERENCIA2_C")
	private Integer ID_REFERENCIA2_C;
	@JsonProperty("ID_REFERENCIA3_C")
	private Integer ID_REFERENCIA3_C;
	@JsonProperty("ID_REFERENCIA4_C")
	private Integer ID_REFERENCIA4_C;
	@JsonProperty("ID_REFERENCIA5_C")
	private Integer ID_REFERENCIA5_C;
	@JsonProperty("ESPESSURA")
	private String ESPESSURA;
	@JsonProperty("BRILHO")
	private String BRILHO;
	@JsonProperty("ID_PRE_SET")
	private Integer ID_PRE_SET;
	@JsonProperty("CAUDAL")
	private String CAUDAL;
	@JsonProperty("PRESSAO_AUTOMATICA")
	private String PRESSAO_AUTOMATICA;
	@JsonProperty("PRESSAO_LEQUE")
	private String PRESSAO_LEQUE;
	@JsonProperty("VELOCIDADE")
	private String VELOCIDADE;
	@JsonProperty("TAMANHO_BICO")
	private String TAMANHO_BICO;
	@JsonProperty("VOLTAS_PISTOLA")
	private String VOLTAS_PISTOLA;
	@JsonProperty("VERSAO")
	private Integer VERSAO;

    @JsonProperty("CONSUMO_RACK_COR")
    private Float CONSUMO_RACK_COR;
    @JsonProperty("CONSUMO_RACK_DILUENTE")
    private Float CONSUMO_RACK_DILUENTE;
    @JsonProperty("QTD_RACK_REFERENCIA_A")
    private Float QTD_RACK_REFERENCIA_A;
    @JsonProperty("QTD_RACK_REFERENCIA_B")
    private Float QTD_RACK_REFERENCIA_B;
    @JsonProperty("QTD_RACK_REFERENCIA_C")
    private Float QTD_RACK_REFERENCIA_C;
    @JsonProperty("QTD_RACK_REFERENCIA2_C")
    private Float QTD_RACK_REFERENCIA2_C;
    @JsonProperty("QTD_RACK_REFERENCIA3_C")
    private Float QTD_RACK_REFERENCIA3_C;
    @JsonProperty("QTD_RACK_REFERENCIA4_C")
    private Float QTD_RACK_REFERENCIA4_C;
    @JsonProperty("QTD_RACK_REFERENCIA5_C")
    private Float QTD_RACK_REFERENCIA5_C;
    
    @JsonProperty("NECESSIDADES_RACK_REFERENCIA_A")
    private Float NECESSIDADES_RACK_REFERENCIA_A;
    @JsonProperty("NECESSIDADES_RACK_REFERENCIA_B")
    private Float NECESSIDADES_RACK_REFERENCIA_B;
    @JsonProperty("NECESSIDADES_RACK_REFERENCIA_C")
    private Float NECESSIDADES_RACK_REFERENCIA_C; 
    @JsonProperty("NECESSIDADES_RACK_REFERENCIA2_C")
    private Float NECESSIDADES_RACK_REFERENCIA2_C; 
    @JsonProperty("NECESSIDADES_RACK_REFERENCIA3_C")
    private Float NECESSIDADES_RACK_REFERENCIA3_C; 
    @JsonProperty("NECESSIDADES_RACK_REFERENCIA4_C")
    private Float NECESSIDADES_RACK_REFERENCIA4_C; 
    @JsonProperty("NECESSIDADES_RACK_REFERENCIA5_C")
    private Float NECESSIDADES_RACK_REFERENCIA5_C; 


	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getID_RECEITA() {
		return ID_RECEITA;
	}

	public void setID_RECEITA(Integer iD_RECEITA) {
		ID_RECEITA = iD_RECEITA;
	}

	public Integer getID_TIPO_ACABAMENTO() {
		return ID_TIPO_ACABAMENTO;
	}

	public void setID_TIPO_ACABAMENTO(Integer iD_TIPO_ACABAMENTO) {
		ID_TIPO_ACABAMENTO = iD_TIPO_ACABAMENTO;
	}

	public Integer getUNIDADE() {
		return UNIDADE;
	}

	public void setUNIDADE(Integer uNIDADE) {
		UNIDADE = uNIDADE;
	}

	public String getID_POTE() {
		return ID_POTE;
	}

	public void setID_POTE(String iD_POTE) {
		ID_POTE = iD_POTE;
	}

	public String getID_POTE_CATALISADOR() {
		return ID_POTE_CATALISADOR;
	}

	public void setID_POTE_CATALISADOR(String iD_POTE_CATALISADOR) {
		ID_POTE_CATALISADOR = iD_POTE_CATALISADOR;
	}

	public String getREFERENCIA_A() {
		return REFERENCIA_A;
	}

	public void setREFERENCIA_A(String rEFERENCIA_A) {
		REFERENCIA_A = rEFERENCIA_A;
	}

	public String getREFERENCIA_B() {
		return REFERENCIA_B;
	}

	public void setREFERENCIA_B(String rEFERENCIA_B) {
		REFERENCIA_B = rEFERENCIA_B;
	}

	public String getREFERENCIA_C() {
		return REFERENCIA_C;
	}

	public void setREFERENCIA_C(String rEFERENCIA_C) {
		REFERENCIA_C = rEFERENCIA_C;
	}

	public String getVALVULA_POTE() {
		return VALVULA_POTE;
	}

	public void setVALVULA_POTE(String vALVULA_POTE) {
		VALVULA_POTE = vALVULA_POTE;
	}

	public String getVALVULA_CATALISADOR() {
		return VALVULA_CATALISADOR;
	}

	public void setVALVULA_CATALISADOR(String vALVULA_CATALISADOR) {
		VALVULA_CATALISADOR = vALVULA_CATALISADOR;
	}

	public String getCOR_A() {
		return COR_A;
	}

	public void setCOR_A(String cOR_A) {
		COR_A = cOR_A;
	}

	public String getDILUENTE_C() {
		return DILUENTE_C;
	}

	public void setDILUENTE_C(String dILUENTE_C) {
		DILUENTE_C = dILUENTE_C;
	}

	public String getADITIVO() {
		return ADITIVO;
	}

	public void setADITIVO(String aDITIVO) {
		ADITIVO = aDITIVO;
	}

	public String getTAXA_MISTURA() {
		return TAXA_MISTURA;
	}

	public void setTAXA_MISTURA(String tAXA_MISTURA) {
		TAXA_MISTURA = tAXA_MISTURA;
	}

	public String getPADRAO_VISCOSIDADE() {
		return PADRAO_VISCOSIDADE;
	}

	public void setPADRAO_VISCOSIDADE(String pADRAO_VISCOSIDADE) {
		PADRAO_VISCOSIDADE = pADRAO_VISCOSIDADE;
	}

	public String getPOT_LIFE() {
		return POT_LIFE;
	}

	public void setPOT_LIFE(String pOT_LIFE) {
		POT_LIFE = pOT_LIFE;
	}

	public String getARMAZEM_A() {
		return ARMAZEM_A;
	}

	public void setARMAZEM_A(String aRMAZEM_A) {
		ARMAZEM_A = aRMAZEM_A;
	}

	public String getARMAZEM_B() {
		return ARMAZEM_B;
	}

	public void setARMAZEM_B(String aRMAZEM_B) {
		ARMAZEM_B = aRMAZEM_B;
	}

	public String getARMAZEM_C() {
		return ARMAZEM_C;
	}

	public void setARMAZEM_C(String aRMAZEM_C) {
		ARMAZEM_C = aRMAZEM_C;
	}

	public String getARMAZEM_ADITIVO() {
		return ARMAZEM_ADITIVO;
	}

	public void setARMAZEM_ADITIVO(String aRMAZEM_ADITIVO) {
		ARMAZEM_ADITIVO = aRMAZEM_ADITIVO;
	}

	public String getCODIGO_COR() {
		return CODIGO_COR;
	}

	public void setCODIGO_COR(String cODIGO_COR) {
		CODIGO_COR = cODIGO_COR;
	}

	public Integer getID_REFERENCIA_A() {
		return ID_REFERENCIA_A;
	}

	public void setID_REFERENCIA_A(Integer iD_REFERENCIA_A) {
		ID_REFERENCIA_A = iD_REFERENCIA_A;
	}

	public Integer getID_REFERENCIA_B() {
		return ID_REFERENCIA_B;
	}

	public void setID_REFERENCIA_B(Integer iD_REFERENCIA_B) {
		ID_REFERENCIA_B = iD_REFERENCIA_B;
	}

	public Integer getID_REFERENCIA_C() {
		return ID_REFERENCIA_C;
	}

	public void setID_REFERENCIA_C(Integer iD_REFERENCIA_C) {
		ID_REFERENCIA_C = iD_REFERENCIA_C;
	}

	public String getESPESSURA() {
		return ESPESSURA;
	}

	public void setESPESSURA(String eSPESSURA) {
		ESPESSURA = eSPESSURA;
	}

	public String getBRILHO() {
		return BRILHO;
	}

	public void setBRILHO(String bRILHO) {
		BRILHO = bRILHO;
	}

	public Integer getID_PRE_SET() {
		return ID_PRE_SET;
	}

	public void setID_PRE_SET(Integer iD_PRE_SET) {
		ID_PRE_SET = iD_PRE_SET;
	}

	public String getCAUDAL() {
		return CAUDAL;
	}

	public void setCAUDAL(String cAUDAL) {
		CAUDAL = cAUDAL;
	}

	public String getPRESSAO_AUTOMATICA() {
		return PRESSAO_AUTOMATICA;
	}

	public void setPRESSAO_AUTOMATICA(String pRESSAO_AUTOMATICA) {
		PRESSAO_AUTOMATICA = pRESSAO_AUTOMATICA;
	}

	public String getPRESSAO_LEQUE() {
		return PRESSAO_LEQUE;
	}

	public void setPRESSAO_LEQUE(String pRESSAO_LEQUE) {
		PRESSAO_LEQUE = pRESSAO_LEQUE;
	}

	public String getVELOCIDADE() {
		return VELOCIDADE;
	}

	public void setVELOCIDADE(String vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

	public String getTAMANHO_BICO() {
		return TAMANHO_BICO;
	}

	public void setTAMANHO_BICO(String tAMANHO_BICO) {
		TAMANHO_BICO = tAMANHO_BICO;
	}

	public String getVOLTAS_PISTOLA() {
		return VOLTAS_PISTOLA;
	}

	public void setVOLTAS_PISTOLA(String vOLTAS_PISTOLA) {
		VOLTAS_PISTOLA = vOLTAS_PISTOLA;
	}

	public Integer getVERSAO() {
		return VERSAO;
	}

	public void setVERSAO(Integer vERSAO) {
		VERSAO = vERSAO;
	}

	public Float getCONSUMO_RACK_COR() {
		return CONSUMO_RACK_COR;
	}

	public Float getCONSUMO_RACK_DILUENTE() {
		return CONSUMO_RACK_DILUENTE;
	}

	public Float getQTD_RACK_REFERENCIA_A() {
		return QTD_RACK_REFERENCIA_A;
	}

	public Float getQTD_RACK_REFERENCIA_B() {
		return QTD_RACK_REFERENCIA_B;
	}

	public Float getQTD_RACK_REFERENCIA_C() {
		return QTD_RACK_REFERENCIA_C;
	}

	public Float getNECESSIDADES_RACK_REFERENCIA_A() {
		return NECESSIDADES_RACK_REFERENCIA_A;
	}

	public Float getNECESSIDADES_RACK_REFERENCIA_B() {
		return NECESSIDADES_RACK_REFERENCIA_B;
	}

	public Float getNECESSIDADES_RACK_REFERENCIA_C() {
		return NECESSIDADES_RACK_REFERENCIA_C;
	}

	public void setCONSUMO_RACK_COR(Float cONSUMO_RACK_COR) {
		CONSUMO_RACK_COR = cONSUMO_RACK_COR;
	}

	public void setCONSUMO_RACK_DILUENTE(Float cONSUMO_RACK_DILUENTE) {
		CONSUMO_RACK_DILUENTE = cONSUMO_RACK_DILUENTE;
	}

	public void setQTD_RACK_REFERENCIA_A(Float qTD_RACK_REFERENCIA_A) {
		QTD_RACK_REFERENCIA_A = qTD_RACK_REFERENCIA_A;
	}

	public void setQTD_RACK_REFERENCIA_B(Float qTD_RACK_REFERENCIA_B) {
		QTD_RACK_REFERENCIA_B = qTD_RACK_REFERENCIA_B;
	}

	public void setQTD_RACK_REFERENCIA_C(Float qTD_RACK_REFERENCIA_C) {
		QTD_RACK_REFERENCIA_C = qTD_RACK_REFERENCIA_C;
	}

	public void setNECESSIDADES_RACK_REFERENCIA_A(Float nECESSIDADES_RACK_REFERENCIA_A) {
		NECESSIDADES_RACK_REFERENCIA_A = nECESSIDADES_RACK_REFERENCIA_A;
	}

	public void setNECESSIDADES_RACK_REFERENCIA_B(Float nECESSIDADES_RACK_REFERENCIA_B) {
		NECESSIDADES_RACK_REFERENCIA_B = nECESSIDADES_RACK_REFERENCIA_B;
	}

	public void setNECESSIDADES_RACK_REFERENCIA_C(Float nECESSIDADES_RACK_REFERENCIA_C) {
		NECESSIDADES_RACK_REFERENCIA_C = nECESSIDADES_RACK_REFERENCIA_C;
	}

	public String getREFERENCIA2_C() {
		return REFERENCIA2_C;
	}

	public String getREFERENCIA3_C() {
		return REFERENCIA3_C;
	}

	public String getREFERENCIA4_C() {
		return REFERENCIA4_C;
	}

	public String getREFERENCIA5_C() {
		return REFERENCIA5_C;
	}

	public Integer getID_REFERENCIA2_C() {
		return ID_REFERENCIA2_C;
	}

	public Integer getID_REFERENCIA3_C() {
		return ID_REFERENCIA3_C;
	}

	public Integer getID_REFERENCIA4_C() {
		return ID_REFERENCIA4_C;
	}

	public Integer getID_REFERENCIA5_C() {
		return ID_REFERENCIA5_C;
	}

	public void setREFERENCIA2_C(String rEFERENCIA2_C) {
		REFERENCIA2_C = rEFERENCIA2_C;
	}

	public void setREFERENCIA3_C(String rEFERENCIA3_C) {
		REFERENCIA3_C = rEFERENCIA3_C;
	}

	public void setREFERENCIA4_C(String rEFERENCIA4_C) {
		REFERENCIA4_C = rEFERENCIA4_C;
	}

	public void setREFERENCIA5_C(String rEFERENCIA5_C) {
		REFERENCIA5_C = rEFERENCIA5_C;
	}

	public void setID_REFERENCIA2_C(Integer iD_REFERENCIA2_C) {
		ID_REFERENCIA2_C = iD_REFERENCIA2_C;
	}

	public void setID_REFERENCIA3_C(Integer iD_REFERENCIA3_C) {
		ID_REFERENCIA3_C = iD_REFERENCIA3_C;
	}

	public void setID_REFERENCIA4_C(Integer iD_REFERENCIA4_C) {
		ID_REFERENCIA4_C = iD_REFERENCIA4_C;
	}

	public void setID_REFERENCIA5_C(Integer iD_REFERENCIA5_C) {
		ID_REFERENCIA5_C = iD_REFERENCIA5_C;
	}

}
