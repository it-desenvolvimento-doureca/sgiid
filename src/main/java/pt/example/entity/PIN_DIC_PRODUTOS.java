package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_DIC_PRODUTOS")
public class PIN_DIC_PRODUTOS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("COD_REF")
	private String COD_REF;
	@JsonProperty("NOME_REF")
	private String NOME_REF;
	@JsonProperty("EMPCOD")
	private String EMPCOD;
	@JsonProperty("LIECOD")
	private String LIECOD;
	@JsonProperty("ID_UNIDADE")
	private Integer ID_UNIDADE;
	@JsonProperty("ID_TIPO_ACABAMENTO")
	private Integer ID_TIPO_ACABAMENTO;
	@JsonProperty("ID_FORNECEDOR")
	private String ID_FORNECEDOR;
	@JsonProperty("OBS")
	private String OBS;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("INATIVO")
	private Boolean INATIVO;
	@JsonProperty("DATA_ANULACAO")
	private Timestamp DATA_ANULACAO;
	@JsonProperty("UTZ_ANULACAO")
	private Integer UTZ_ANULACAO;
	@JsonProperty("FACTOR_CONVERSAO")
	private BigDecimal FACTOR_CONVERSAO;
	@JsonProperty("UNISTO")
	private String UNISTO;
	@JsonProperty("CISTERNA")
	private Boolean CISTERNA;
	@JsonProperty("COD_REF_SUBSTITUTA")
	private String COD_REF_SUBSTITUTA;
	@JsonProperty("NOME_REF_SUBSTITUTA")
	private String NOME_REF_SUBSTITUTA;
	@JsonProperty("TAXA_MISTURA")
	private BigDecimal TAXA_MISTURA;

	public BigDecimal getTAXA_MISTURA() {
		return TAXA_MISTURA;
	}

	public void setTAXA_MISTURA(BigDecimal tAXA_MISTURA) {
		TAXA_MISTURA = tAXA_MISTURA;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getCOD_REF() {
		return COD_REF;
	}

	public void setCOD_REF(String cOD_REF) {
		COD_REF = cOD_REF;
	}

	public String getNOME_REF() {
		return NOME_REF;
	}

	public void setNOME_REF(String nOME_REF) {
		NOME_REF = nOME_REF;
	}

	public String getEMPCOD() {
		return EMPCOD;
	}

	public void setEMPCOD(String eMPCOD) {
		EMPCOD = eMPCOD;
	}

	public String getLIECOD() {
		return LIECOD;
	}

	public void setLIECOD(String lIECOD) {
		LIECOD = lIECOD;
	}

	public Integer getID_UNIDADE() {
		return ID_UNIDADE;
	}

	public void setID_UNIDADE(Integer iD_UNIDADE) {
		ID_UNIDADE = iD_UNIDADE;
	}

	public Integer getID_TIPO_ACABAMENTO() {
		return ID_TIPO_ACABAMENTO;
	}

	public void setID_TIPO_ACABAMENTO(Integer iD_TIPO_ACABAMENTO) {
		ID_TIPO_ACABAMENTO = iD_TIPO_ACABAMENTO;
	}

	public String getID_FORNECEDOR() {
		return ID_FORNECEDOR;
	}

	public void setID_FORNECEDOR(String iD_FORNECEDOR) {
		ID_FORNECEDOR = iD_FORNECEDOR;
	}

	public String getOBS() {
		return OBS;
	}

	public void setOBS(String oBS) {
		OBS = oBS;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public Timestamp getDATA_ANULACAO() {
		return DATA_ANULACAO;
	}

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public Integer getUTZ_ANULACAO() {
		return UTZ_ANULACAO;
	}

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public BigDecimal getFACTOR_CONVERSAO() {
		return FACTOR_CONVERSAO;
	}

	public void setFACTOR_CONVERSAO(BigDecimal fACTOR_CONVERSAO) {
		FACTOR_CONVERSAO = fACTOR_CONVERSAO;
	}

	public String getUNISTO() {
		return UNISTO;
	}

	public void setUNISTO(String uNISTO) {
		UNISTO = uNISTO;
	}

	public Boolean getCISTERNA() {
		return CISTERNA;
	}

	public void setCISTERNA(Boolean cISTERNA) {
		CISTERNA = cISTERNA;
	}

	public String getCOD_REF_SUBSTITUTA() {
		return COD_REF_SUBSTITUTA;
	}

	public void setCOD_REF_SUBSTITUTA(String cOD_REF_SUBSTITUTA) {
		COD_REF_SUBSTITUTA = cOD_REF_SUBSTITUTA;
	}

	public String getNOME_REF_SUBSTITUTA() {
		return NOME_REF_SUBSTITUTA;
	}

	public void setNOME_REF_SUBSTITUTA(String nOME_REF_SUBSTITUTA) {
		NOME_REF_SUBSTITUTA = nOME_REF_SUBSTITUTA;
	}

}
