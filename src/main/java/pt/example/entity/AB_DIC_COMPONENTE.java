package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_DIC_COMPONENTE")
public class AB_DIC_COMPONENTE {

	private Integer ID_COMPONENTE;
	private String COD_REF;
	private String NOME_REF;
	private String NOME_COMPONENTE;
	private String ID_UNIDADE_COMPONENTE;
	private String OBS;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;
	private Integer ID_UNIDADE_ADITIVO;
	private Integer ID_FORNECEDOR;
	private String TIPO;
	private String OBRIGATORIO;
	private Float FACTOR_MULTIPLICACAO_AGUA;

	@Column(name = "DATA_ANULACAO")
	public Timestamp getDATA_ANULACAO() {
		return DATA_ANULACAO;
	}

	@Column(name = "UTZ_ANULACAO")
	public Integer getUTZ_ANULACAO() {
		return UTZ_ANULACAO;
	}

	@Column(name = "INATIVO")
	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	@Id
	@Column(name = "ID_COMPONENTE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_COMPONENTE() {
		return ID_COMPONENTE;
	}

	@Column(name = "NOME_COMPONENTE")
	public String getNOME_COMPONENTE() {
		return NOME_COMPONENTE;
	}

	@Column(name = "ID_UNIDADE_COMPONENTE")
	public String getID_UNIDADE_COMPONENTE() {
		return ID_UNIDADE_COMPONENTE;
	}

	@Column(name = "OBS")
	public String getOBS() {
		return OBS;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "UTZ_CRIA")
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	@Column(name = "DATA_ULT_MODIF")
	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	@Column(name = "UTZ_ULT_MODIF")
	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	@Column(name = "COD_REF")
	public String getCOD_REF() {
		return COD_REF;
	}

	@Column(name = "NOME_REF")
	public String getNOME_REF() {
		return NOME_REF;
	}

	@Column(name = "ID_UNIDADE_ADITIVO")
	public Integer getID_UNIDADE_ADITIVO() {
		return ID_UNIDADE_ADITIVO;
	}

	@Column(name = "ID_FORNECEDOR")
	public Integer getID_FORNECEDOR() {
		return ID_FORNECEDOR;
	}

	@Column(name = "TIPO")
	public String getTIPO() {
		return TIPO;
	}

	@Column(name = "OBRIGATORIO")
	public String getOBRIGATORIO() {
		return OBRIGATORIO;
	}
	
	@Column(name = "FACTOR_MULTIPLICACAO_AGUA")
	public Float getFACTOR_MULTIPLICACAO_AGUA() {
		return FACTOR_MULTIPLICACAO_AGUA;
	}

	public void setFACTOR_MULTIPLICACAO_AGUA(Float fACTOR_MULTIPLICACAO_AGUA) {
		FACTOR_MULTIPLICACAO_AGUA = fACTOR_MULTIPLICACAO_AGUA;
	}

	public void setOBRIGATORIO(String oBRIGATORIO) {
		OBRIGATORIO = oBRIGATORIO;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}

	public void setID_UNIDADE_ADITIVO(Integer iD_UNIDADE_ADITIVO) {
		ID_UNIDADE_ADITIVO = iD_UNIDADE_ADITIVO;
	}

	public void setID_FORNECEDOR(Integer iD_FORNECEDOR) {
		ID_FORNECEDOR = iD_FORNECEDOR;
	}

	public void setCOD_REF(String cOD_REF) {
		COD_REF = cOD_REF;
	}

	public void setNOME_REF(String nOME_REF) {
		NOME_REF = nOME_REF;
	}

	public void setID_COMPONENTE(Integer iD_COMPONENTE) {
		ID_COMPONENTE = iD_COMPONENTE;
	}

	public void setNOME_COMPONENTE(String nOME_COMPONENTE) {
		NOME_COMPONENTE = nOME_COMPONENTE;
	}

	public void setID_UNIDADE_COMPONENTE(String iD_UNIDADE_COMPONENTE) {
		ID_UNIDADE_COMPONENTE = iD_UNIDADE_COMPONENTE;
	}

	public void setOBS(String oBS) {
		OBS = oBS;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

}
