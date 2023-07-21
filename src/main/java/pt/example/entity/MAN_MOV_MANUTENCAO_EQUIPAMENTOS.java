package pt.example.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "MAN_MOV_MANUTENCAO_EQUIPAMENTOS")
public class MAN_MOV_MANUTENCAO_EQUIPAMENTOS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_MANUTENCAO")
	private Integer ID_MANUTENCAO;
	@JsonProperty("NOME")
	private String NOME;
	@JsonProperty("COD_EQUIPAMENTO_PRINCIPAL")
	private String COD_EQUIPAMENTO_PRINCIPAL;
	@JsonProperty("LOCALIZACAO")
	private Integer LOCALIZACAO;
	@JsonProperty("EQUIPA")
	private Integer EQUIPA;
	@JsonProperty("GARANTIA")
	private Boolean GARANTIA;
	@JsonProperty("DATA_VALIDADE")
	private Date DATA_VALIDADE;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty("ATIVO")
	private Boolean ATIVO;
	@JsonProperty("TIPO_LOCALIZACAO")
	private String TIPO_LOCALIZACAO;
	@JsonProperty("TIPO_EQUIPA")
	private String TIPO_EQUIPA;
	@JsonProperty("UTILIZADOR")
	private Integer UTILIZADOR;
	@JsonProperty("DESCRICAO_MANUTENCAO")
	private String DESCRICAO_MANUTENCAO;
	@JsonProperty("NIVEL_CRITICIDADE")
	private Integer NIVEL_CRITICIDADE;	
	@JsonProperty("AMBITO_MANUTENCAO")
	private Integer AMBITO_MANUTENCAO;
	@JsonProperty( "COD_FORNECEDOR")
	private String COD_FORNECEDOR;
	@JsonProperty( "NOME_FORNECEDOR")
	private String NOME_FORNECEDOR;
	@JsonProperty( "EMAIL_FORNECEDOR")
	private String EMAIL_FORNECEDOR;
	
	@JsonProperty("UTZ_OBSOLETO")
	private Integer UTZ_OBSOLETO;
	@JsonProperty("DATA_OBSOLETO")
	private Timestamp DATA_OBSOLETO;
	@JsonProperty("OBSOLETO")
	private Boolean OBSOLETO;
	@JsonProperty( "COD_MAQUINA")
	private String COD_MAQUINA;
	@JsonProperty( "DESC_MAQUINA")
	private String DESC_MAQUINA;
	@JsonProperty( "FABRICANTE")
	private String FABRICANTE;
	@JsonProperty( "MODELO")
	private String MODELO;
	@JsonProperty( "ANO")
	private String ANO;
	@JsonProperty( "N_SERIE")
	private String N_SERIE;
	@JsonProperty("MAQUINA_PRODUTIVA")
	private Boolean MAQUINA_PRODUTIVA;
	
	
	public String getCOD_FORNECEDOR() {
		return COD_FORNECEDOR;
	}

	public String getNOME_FORNECEDOR() {
		return NOME_FORNECEDOR;
	}

	public String getEMAIL_FORNECEDOR() {
		return EMAIL_FORNECEDOR;
	}

	public void setCOD_FORNECEDOR(String cOD_FORNECEDOR) {
		COD_FORNECEDOR = cOD_FORNECEDOR;
	}

	public void setNOME_FORNECEDOR(String nOME_FORNECEDOR) {
		NOME_FORNECEDOR = nOME_FORNECEDOR;
	}

	public void setEMAIL_FORNECEDOR(String eMAIL_FORNECEDOR) {
		EMAIL_FORNECEDOR = eMAIL_FORNECEDOR;
	}

	public Integer getID_MANUTENCAO() {
		return ID_MANUTENCAO;
	}

	public String getNOME() {
		return NOME;
	}

	public String getCOD_EQUIPAMENTO_PRINCIPAL() {
		return COD_EQUIPAMENTO_PRINCIPAL;
	}

	public Integer getLOCALIZACAO() {
		return LOCALIZACAO;
	}

	public Integer getEQUIPA() {
		return EQUIPA;
	}

	public Boolean getGARANTIA() {
		return GARANTIA;
	}

	public Date getDATA_VALIDADE() {
		return DATA_VALIDADE;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public Boolean getATIVO() {
		return ATIVO;
	}

	public void setID_MANUTENCAO(Integer iD_MANUTENCAO) {
		ID_MANUTENCAO = iD_MANUTENCAO;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public void setCOD_EQUIPAMENTO_PRINCIPAL(String cOD_EQUIPAMENTO_PRINCIPAL) {
		COD_EQUIPAMENTO_PRINCIPAL = cOD_EQUIPAMENTO_PRINCIPAL;
	}

	public void setLOCALIZACAO(Integer lOCALIZACAO) {
		LOCALIZACAO = lOCALIZACAO;
	}

	public void setEQUIPA(Integer eQUIPA) {
		EQUIPA = eQUIPA;
	}

	public void setGARANTIA(Boolean gARANTIA) {
		GARANTIA = gARANTIA;
	}

	public void setDATA_VALIDADE(Date dATA_VALIDADE) {
		DATA_VALIDADE = dATA_VALIDADE;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public String getTIPO_LOCALIZACAO() {
		return TIPO_LOCALIZACAO;
	}

	public void setTIPO_LOCALIZACAO(String tIPO_LOCALIZACAO) {
		TIPO_LOCALIZACAO = tIPO_LOCALIZACAO;
	}

	public String getTIPO_EQUIPA() {
		return TIPO_EQUIPA;
	}

	public Integer getUTILIZADOR() {
		return UTILIZADOR;
	}

	public void setTIPO_EQUIPA(String tIPO_EQUIPA) {
		TIPO_EQUIPA = tIPO_EQUIPA;
	}

	public void setUTILIZADOR(Integer uTILIZADOR) {
		UTILIZADOR = uTILIZADOR;
	}

	public String getDESCRICAO_MANUTENCAO() {
		return DESCRICAO_MANUTENCAO;
	}

	public void setDESCRICAO_MANUTENCAO(String dESCRICAO_MANUTENCAO) {
		DESCRICAO_MANUTENCAO = dESCRICAO_MANUTENCAO;
	}

	public Integer getNIVEL_CRITICIDADE() {
		return NIVEL_CRITICIDADE;
	}

	public Integer getAMBITO_MANUTENCAO() {
		return AMBITO_MANUTENCAO;
	}

	public void setNIVEL_CRITICIDADE(Integer nIVEL_CRITICIDADE) {
		NIVEL_CRITICIDADE = nIVEL_CRITICIDADE;
	}

	public void setAMBITO_MANUTENCAO(Integer aMBITO_MANUTENCAO) {
		AMBITO_MANUTENCAO = aMBITO_MANUTENCAO;
	}

	public Integer getUTZ_OBSOLETO() {
		return UTZ_OBSOLETO;
	}

	public Timestamp getDATA_OBSOLETO() {
		return DATA_OBSOLETO;
	}

	public Boolean getOBSOLETO() {
		return OBSOLETO;
	}

	public void setUTZ_OBSOLETO(Integer uTZ_OBSOLETO) {
		UTZ_OBSOLETO = uTZ_OBSOLETO;
	}

	public void setDATA_OBSOLETO(Timestamp dATA_OBSOLETO) {
		DATA_OBSOLETO = dATA_OBSOLETO;
	}

	public void setOBSOLETO(Boolean oBSOLETO) {
		OBSOLETO = oBSOLETO;
	}

	public String getCOD_MAQUINA() {
		return COD_MAQUINA;
	}

	public void setCOD_MAQUINA(String cOD_MAQUINA) {
		COD_MAQUINA = cOD_MAQUINA;
	}

	public String getDESC_MAQUINA() {
		return DESC_MAQUINA;
	}

	public void setDESC_MAQUINA(String dESC_MAQUINA) {
		DESC_MAQUINA = dESC_MAQUINA;
	}

	public String getFABRICANTE() {
		return FABRICANTE;
	}

	public void setFABRICANTE(String fABRICANTE) {
		FABRICANTE = fABRICANTE;
	}

	public String getMODELO() {
		return MODELO;
	}

	public void setMODELO(String mODELO) {
		MODELO = mODELO;
	}

	public String getANO() {
		return ANO;
	}

	public void setANO(String aNO) {
		ANO = aNO;
	}

	public String getN_SERIE() {
		return N_SERIE;
	}

	public void setN_SERIE(String n_SERIE) {
		N_SERIE = n_SERIE;
	}

	public Boolean getMAQUINA_PRODUTIVA() {
		return MAQUINA_PRODUTIVA;
	}

	public void setMAQUINA_PRODUTIVA(Boolean mAQUINA_PRODUTIVA) {
		MAQUINA_PRODUTIVA = mAQUINA_PRODUTIVA;
	}

}
