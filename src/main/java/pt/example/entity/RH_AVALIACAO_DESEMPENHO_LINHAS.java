package pt.example.entity;

import java.math.BigDecimal;
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
@Table(name = "RH_AVALIACAO_DESEMPENHO_LINHAS")
public class RH_AVALIACAO_DESEMPENHO_LINHAS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("ID_AVALIACAO_DESEMPENHO")
	private Integer ID_AVALIACAO_DESEMPENHO;

	@JsonProperty("ID_CATEGORIA_PROFISSIONAL")
	private String ID_CATEGORIA_PROFISSIONAL;

	@JsonProperty("GRUPO_COLABORADORES")
	private String GRUPO_COLABORADORES;

	@JsonProperty("NUMERO_FUNCIONARIO")
	private String NUMERO_FUNCIONARIO;

	@JsonProperty("NOME")
	private String NOME;

	@JsonProperty("SECCAO")
	private String SECCAO;

	@JsonProperty("CHEFIA_DIRETA")
	private String CHEFIA_DIRETA;

	@JsonProperty("LOCAL")
	private String LOCAL;

	@JsonProperty("CATEGORIA_PROFISSIONAL")
	private String CATEGORIA_PROFISSIONAL;

	@JsonProperty("DATA_ADMISSAO")
	private Date DATA_ADMISSAO;

	@JsonProperty("DATA_CESSACAO")
	private Date DATA_CESSACAO;

	@JsonProperty("ESTADO")
	private String ESTADO;

	@JsonProperty("VENCIMENTO_ANUAL")
	private BigDecimal VENCIMENTO_ANUAL;

	@JsonProperty("PERCENTUAL_VENCIMENTO_ANUAL")
	private BigDecimal PERCENTUAL_VENCIMENTO_ANUAL;

	@JsonProperty("ID_VENCIMENTO_ANUAL")
	private Integer ID_VENCIMENTO_ANUAL;

	@JsonProperty("AVALIACAO_DESEMPENHO")
	private BigDecimal AVALIACAO_DESEMPENHO;

	@JsonProperty("PERCENTUAL_AVALIACAO_DESEMPENHO")
	private BigDecimal PERCENTUAL_AVALIACAO_DESEMPENHO;

	@JsonProperty("ID_AVALIACAO_DESEMPENHO_CRIT")
	private Integer ID_AVALIACAO_DESEMPENHO_CRIT;

	@JsonProperty("ASSIDUIDADE")
	private BigDecimal ASSIDUIDADE;

	@JsonProperty("PERCENTUAL_ASSIDUIDADE")
	private BigDecimal PERCENTUAL_ASSIDUIDADE;

	@JsonProperty("ID_ASSIDUIDADE")
	private Integer ID_ASSIDUIDADE;

	@JsonProperty("PONTUALIDADE")
	private BigDecimal PONTUALIDADE;

	@JsonProperty("PERCENTUAL_PONTUALIDADE")
	private BigDecimal PERCENTUAL_PONTUALIDADE;

	@JsonProperty("ID_PONTUALIDADE")
	private Integer ID_PONTUALIDADE;

	@JsonProperty("POLIVALENCIA")
	private BigDecimal POLIVALENCIA;

	@JsonProperty("PERCENTUAL_POLIVALENCIA")
	private BigDecimal PERCENTUAL_POLIVALENCIA;

	@JsonProperty("ID_POLIVALENCIA")
	private Integer ID_POLIVALENCIA;

	@JsonProperty("QUALIDADE_INCIDENTES")
	private BigDecimal QUALIDADE_INCIDENTES;

	@JsonProperty("PERCENTUAL_QUALIDADE_INCIDENTES")
	private BigDecimal PERCENTUAL_QUALIDADE_INCIDENTES;

	@JsonProperty("ID_QUALIDADE_INCIDENTES")
	private Integer ID_QUALIDADE_INCIDENTES;

	@JsonProperty("PLANO_FERIAS")
	private BigDecimal PLANO_FERIAS;

	@JsonProperty("PERCENTUAL_PLANO_FERIAS")
	private BigDecimal PERCENTUAL_PLANO_FERIAS;

	@JsonProperty("ID_PLANO_FERIAS")
	private Integer ID_PLANO_FERIAS;

	@JsonProperty("HORAS_EXTRA")
	private BigDecimal HORAS_EXTRA;

	@JsonProperty("PERCENTUAL_HORAS_EXTRA")
	private BigDecimal PERCENTUAL_HORAS_EXTRA;

	@JsonProperty("ID_HORAS_EXTRA")
	private Integer ID_HORAS_EXTRA;

	@JsonProperty("PONTUACAO_FINAL")
	private BigDecimal PONTUACAO_FINAL;

	@JsonProperty("LIMITE_MAXIMO_PREMIO_PCT")
	private BigDecimal LIMITE_MAXIMO_PREMIO_PCT;

	@JsonProperty("VALOR_MAXIMO_PREMIO")
	private BigDecimal VALOR_MAXIMO_PREMIO;

	@JsonProperty("MESES_TRABALHADOS")
	private Integer MESES_TRABALHADOS;

	@JsonProperty("PREMIO_PCT")
	private BigDecimal PREMIO_PCT;

	@JsonProperty("VALOR_PREMIO")
	private BigDecimal VALOR_PREMIO;

	@JsonProperty("VALOR_PREMIO_GRUPO")
	private BigDecimal VALOR_PREMIO_GRUPO;

	public Integer getID() {
		return ID;
	}

	public Integer getID_AVALIACAO_DESEMPENHO() {
		return ID_AVALIACAO_DESEMPENHO;
	}

	public String getID_CATEGORIA_PROFISSIONAL() {
		return ID_CATEGORIA_PROFISSIONAL;
	}

	public String getGRUPO_COLABORADORES() {
		return GRUPO_COLABORADORES;
	}

	public String getNUMERO_FUNCIONARIO() {
		return NUMERO_FUNCIONARIO;
	}

	public String getNOME() {
		return NOME;
	}

	public String getSECCAO() {
		return SECCAO;
	}

	public String getCHEFIA_DIRETA() {
		return CHEFIA_DIRETA;
	}

	public String getLOCAL() {
		return LOCAL;
	}

	public String getCATEGORIA_PROFISSIONAL() {
		return CATEGORIA_PROFISSIONAL;
	}

	public Date getDATA_ADMISSAO() {
		return DATA_ADMISSAO;
	}

	public Date getDATA_CESSACAO() {
		return DATA_CESSACAO;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public BigDecimal getVENCIMENTO_ANUAL() {
		return VENCIMENTO_ANUAL;
	}

	public BigDecimal getPERCENTUAL_VENCIMENTO_ANUAL() {
		return PERCENTUAL_VENCIMENTO_ANUAL;
	}

	public Integer getID_VENCIMENTO_ANUAL() {
		return ID_VENCIMENTO_ANUAL;
	}

	public BigDecimal getAVALIACAO_DESEMPENHO() {
		return AVALIACAO_DESEMPENHO;
	}

	public BigDecimal getPERCENTUAL_AVALIACAO_DESEMPENHO() {
		return PERCENTUAL_AVALIACAO_DESEMPENHO;
	}

	public Integer getID_AVALIACAO_DESEMPENHO_CRIT() {
		return ID_AVALIACAO_DESEMPENHO_CRIT;
	}

	public BigDecimal getASSIDUIDADE() {
		return ASSIDUIDADE;
	}

	public BigDecimal getPERCENTUAL_ASSIDUIDADE() {
		return PERCENTUAL_ASSIDUIDADE;
	}

	public Integer getID_ASSIDUIDADE() {
		return ID_ASSIDUIDADE;
	}

	public BigDecimal getPONTUALIDADE() {
		return PONTUALIDADE;
	}

	public BigDecimal getPERCENTUAL_PONTUALIDADE() {
		return PERCENTUAL_PONTUALIDADE;
	}

	public Integer getID_PONTUALIDADE() {
		return ID_PONTUALIDADE;
	}

	public BigDecimal getPOLIVALENCIA() {
		return POLIVALENCIA;
	}

	public BigDecimal getPERCENTUAL_POLIVALENCIA() {
		return PERCENTUAL_POLIVALENCIA;
	}

	public Integer getID_POLIVALENCIA() {
		return ID_POLIVALENCIA;
	}

	public BigDecimal getQUALIDADE_INCIDENTES() {
		return QUALIDADE_INCIDENTES;
	}

	public BigDecimal getPERCENTUAL_QUALIDADE_INCIDENTES() {
		return PERCENTUAL_QUALIDADE_INCIDENTES;
	}

	public Integer getID_QUALIDADE_INCIDENTES() {
		return ID_QUALIDADE_INCIDENTES;
	}

	public BigDecimal getPLANO_FERIAS() {
		return PLANO_FERIAS;
	}

	public BigDecimal getPERCENTUAL_PLANO_FERIAS() {
		return PERCENTUAL_PLANO_FERIAS;
	}

	public Integer getID_PLANO_FERIAS() {
		return ID_PLANO_FERIAS;
	}

	public BigDecimal getHORAS_EXTRA() {
		return HORAS_EXTRA;
	}

	public BigDecimal getPERCENTUAL_HORAS_EXTRA() {
		return PERCENTUAL_HORAS_EXTRA;
	}

	public Integer getID_HORAS_EXTRA() {
		return ID_HORAS_EXTRA;
	}

	public BigDecimal getPONTUACAO_FINAL() {
		return PONTUACAO_FINAL;
	}

	public BigDecimal getLIMITE_MAXIMO_PREMIO_PCT() {
		return LIMITE_MAXIMO_PREMIO_PCT;
	}

	public BigDecimal getVALOR_MAXIMO_PREMIO() {
		return VALOR_MAXIMO_PREMIO;
	}

	public Integer getMESES_TRABALHADOS() {
		return MESES_TRABALHADOS;
	}

	public BigDecimal getPREMIO_PCT() {
		return PREMIO_PCT;
	}

	public BigDecimal getVALOR_PREMIO() {
		return VALOR_PREMIO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_AVALIACAO_DESEMPENHO(Integer iD_AVALIACAO_DESEMPENHO) {
		ID_AVALIACAO_DESEMPENHO = iD_AVALIACAO_DESEMPENHO;
	}

	public void setID_CATEGORIA_PROFISSIONAL(String iD_CATEGORIA_PROFISSIONAL) {
		ID_CATEGORIA_PROFISSIONAL = iD_CATEGORIA_PROFISSIONAL;
	}

	public void setGRUPO_COLABORADORES(String gRUPO_COLABORADORES) {
		GRUPO_COLABORADORES = gRUPO_COLABORADORES;
	}

	public void setNUMERO_FUNCIONARIO(String nUMERO_FUNCIONARIO) {
		NUMERO_FUNCIONARIO = nUMERO_FUNCIONARIO;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public void setSECCAO(String sECCAO) {
		SECCAO = sECCAO;
	}

	public void setCHEFIA_DIRETA(String cHEFIA_DIRETA) {
		CHEFIA_DIRETA = cHEFIA_DIRETA;
	}

	public void setLOCAL(String lOCAL) {
		LOCAL = lOCAL;
	}

	public void setCATEGORIA_PROFISSIONAL(String cATEGORIA_PROFISSIONAL) {
		CATEGORIA_PROFISSIONAL = cATEGORIA_PROFISSIONAL;
	}

	public void setDATA_ADMISSAO(Date dATA_ADMISSAO) {
		DATA_ADMISSAO = dATA_ADMISSAO;
	}

	public void setDATA_CESSACAO(Date dATA_CESSACAO) {
		DATA_CESSACAO = dATA_CESSACAO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setVENCIMENTO_ANUAL(BigDecimal vENCIMENTO_ANUAL) {
		VENCIMENTO_ANUAL = vENCIMENTO_ANUAL;
	}

	public void setPERCENTUAL_VENCIMENTO_ANUAL(BigDecimal pERCENTUAL_VENCIMENTO_ANUAL) {
		PERCENTUAL_VENCIMENTO_ANUAL = pERCENTUAL_VENCIMENTO_ANUAL;
	}

	public void setID_VENCIMENTO_ANUAL(Integer iD_VENCIMENTO_ANUAL) {
		ID_VENCIMENTO_ANUAL = iD_VENCIMENTO_ANUAL;
	}

	public void setAVALIACAO_DESEMPENHO(BigDecimal aVALIACAO_DESEMPENHO) {
		AVALIACAO_DESEMPENHO = aVALIACAO_DESEMPENHO;
	}

	public void setPERCENTUAL_AVALIACAO_DESEMPENHO(BigDecimal pERCENTUAL_AVALIACAO_DESEMPENHO) {
		PERCENTUAL_AVALIACAO_DESEMPENHO = pERCENTUAL_AVALIACAO_DESEMPENHO;
	}

	public void setID_AVALIACAO_DESEMPENHO_CRIT(Integer iD_AVALIACAO_DESEMPENHO_CRIT) {
		ID_AVALIACAO_DESEMPENHO_CRIT = iD_AVALIACAO_DESEMPENHO_CRIT;
	}

	public void setASSIDUIDADE(BigDecimal aSSIDUIDADE) {
		ASSIDUIDADE = aSSIDUIDADE;
	}

	public void setPERCENTUAL_ASSIDUIDADE(BigDecimal pERCENTUAL_ASSIDUIDADE) {
		PERCENTUAL_ASSIDUIDADE = pERCENTUAL_ASSIDUIDADE;
	}

	public void setID_ASSIDUIDADE(Integer iD_ASSIDUIDADE) {
		ID_ASSIDUIDADE = iD_ASSIDUIDADE;
	}

	public void setPONTUALIDADE(BigDecimal pONTUALIDADE) {
		PONTUALIDADE = pONTUALIDADE;
	}

	public void setPERCENTUAL_PONTUALIDADE(BigDecimal pERCENTUAL_PONTUALIDADE) {
		PERCENTUAL_PONTUALIDADE = pERCENTUAL_PONTUALIDADE;
	}

	public void setID_PONTUALIDADE(Integer iD_PONTUALIDADE) {
		ID_PONTUALIDADE = iD_PONTUALIDADE;
	}

	public void setPOLIVALENCIA(BigDecimal pOLIVALENCIA) {
		POLIVALENCIA = pOLIVALENCIA;
	}

	public void setPERCENTUAL_POLIVALENCIA(BigDecimal pERCENTUAL_POLIVALENCIA) {
		PERCENTUAL_POLIVALENCIA = pERCENTUAL_POLIVALENCIA;
	}

	public void setID_POLIVALENCIA(Integer iD_POLIVALENCIA) {
		ID_POLIVALENCIA = iD_POLIVALENCIA;
	}

	public void setQUALIDADE_INCIDENTES(BigDecimal qUALIDADE_INCIDENTES) {
		QUALIDADE_INCIDENTES = qUALIDADE_INCIDENTES;
	}

	public void setPERCENTUAL_QUALIDADE_INCIDENTES(BigDecimal pERCENTUAL_QUALIDADE_INCIDENTES) {
		PERCENTUAL_QUALIDADE_INCIDENTES = pERCENTUAL_QUALIDADE_INCIDENTES;
	}

	public void setID_QUALIDADE_INCIDENTES(Integer iD_QUALIDADE_INCIDENTES) {
		ID_QUALIDADE_INCIDENTES = iD_QUALIDADE_INCIDENTES;
	}

	public void setPLANO_FERIAS(BigDecimal pLANO_FERIAS) {
		PLANO_FERIAS = pLANO_FERIAS;
	}

	public void setPERCENTUAL_PLANO_FERIAS(BigDecimal pERCENTUAL_PLANO_FERIAS) {
		PERCENTUAL_PLANO_FERIAS = pERCENTUAL_PLANO_FERIAS;
	}

	public void setID_PLANO_FERIAS(Integer iD_PLANO_FERIAS) {
		ID_PLANO_FERIAS = iD_PLANO_FERIAS;
	}

	public void setHORAS_EXTRA(BigDecimal hORAS_EXTRA) {
		HORAS_EXTRA = hORAS_EXTRA;
	}

	public void setPERCENTUAL_HORAS_EXTRA(BigDecimal pERCENTUAL_HORAS_EXTRA) {
		PERCENTUAL_HORAS_EXTRA = pERCENTUAL_HORAS_EXTRA;
	}

	public void setID_HORAS_EXTRA(Integer iD_HORAS_EXTRA) {
		ID_HORAS_EXTRA = iD_HORAS_EXTRA;
	}

	public void setPONTUACAO_FINAL(BigDecimal pONTUACAO_FINAL) {
		PONTUACAO_FINAL = pONTUACAO_FINAL;
	}

	public void setLIMITE_MAXIMO_PREMIO_PCT(BigDecimal lIMITE_MAXIMO_PREMIO_PCT) {
		LIMITE_MAXIMO_PREMIO_PCT = lIMITE_MAXIMO_PREMIO_PCT;
	}

	public void setVALOR_MAXIMO_PREMIO(BigDecimal vALOR_MAXIMO_PREMIO) {
		VALOR_MAXIMO_PREMIO = vALOR_MAXIMO_PREMIO;
	}

	public void setMESES_TRABALHADOS(Integer mESES_TRABALHADOS) {
		MESES_TRABALHADOS = mESES_TRABALHADOS;
	}

	public void setPREMIO_PCT(BigDecimal pREMIO_PCT) {
		PREMIO_PCT = pREMIO_PCT;
	}

	public void setVALOR_PREMIO(BigDecimal vALOR_PREMIO) {
		VALOR_PREMIO = vALOR_PREMIO;
	}

	public BigDecimal getVALOR_PREMIO_GRUPO() {
		return VALOR_PREMIO_GRUPO;
	}

	public void setVALOR_PREMIO_GRUPO(BigDecimal vALOR_PREMIO_GRUPO) {
		VALOR_PREMIO_GRUPO = vALOR_PREMIO_GRUPO;
	}

}
