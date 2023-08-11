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
	private Integer ID_POTE;
	@JsonProperty("ID_POTE_CATALISADOR")
	private Integer ID_POTE_CATALISADOR;
	@JsonProperty("REFERENCIA_A")
	private String REFERENCIA_A;
	@JsonProperty("REFERENCIA_B")
	private String REFERENCIA_B;
	@JsonProperty("REFERENCIA_C")
	private String REFERENCIA_C;
	@JsonProperty("VALVULA_POTE")
	private String VALVULA_POTE;
	@JsonProperty("VALVULA_CATALISADOR")
	private String VALVULA_CATALISADOR;
	@JsonProperty("COR_A")
	private String COR_A;
	@JsonProperty("DILUENTE_C")
	private String DILUENTE_C;
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

	public Integer getID_POTE() {
		return ID_POTE;
	}

	public void setID_POTE(Integer iD_POTE) {
		ID_POTE = iD_POTE;
	}

	public Integer getID_POTE_CATALISADOR() {
		return ID_POTE_CATALISADOR;
	}

	public void setID_POTE_CATALISADOR(Integer iD_POTE_CATALISADOR) {
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

}
