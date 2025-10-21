package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "RH_DIC_AVALIACAO_CATEGORIA")
public class RH_DIC_AVALIACAO_CATEGORIA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
	private Integer ID;
	
	@JsonProperty("VALOR_AVALIACAO_DESEMPENHO")
	private BigDecimal VALOR_AVALIACAO_DESEMPENHO;

	@JsonProperty("VALOR_ASSIDUIDADE")
	private BigDecimal VALOR_ASSIDUIDADE;

	@JsonProperty("VALOR_PONTUALIDADE")
	private BigDecimal VALOR_PONTUALIDADE;

	@JsonProperty("VALOR_POLIVALENCIA")
	private BigDecimal VALOR_POLIVALENCIA;

	@JsonProperty("VALOR_QUALIDADE")
	private BigDecimal VALOR_QUALIDADE;

	@JsonProperty("VALOR_PLANOFERIAS")
	private BigDecimal VALOR_PLANOFERIAS;

	@JsonProperty("VALOR_HORASEXTRA")
	private BigDecimal VALOR_HORASEXTRA;

	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;

	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA")
	private RH_DIC_CATEGORIAS_PROFISSIONAIS categoria;

	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;

	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;

	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;

	@JsonProperty("DATA_ANULA")
	private Timestamp DATA_ANULA;

	@JsonProperty("UTZ_ANULA")
	private Integer UTZ_ANULA;

	@JsonProperty("ATIVO")
	private Boolean ATIVO;

	// Setter auxiliar para aceitar ID direto no JSON
	@JsonProperty("ID_CATEGORIA")
	public void setID_CATEGORIA(Integer id) {
		this.categoria = new RH_DIC_CATEGORIAS_PROFISSIONAIS();
		this.categoria.setID(id);
	}

	// Getter opcional se quiseres expor o ID no JSON de saída
	@JsonProperty("ID_CATEGORIA")
	public Integer getID_CATEGORIA() {
		return categoria != null ? categoria.getID() : null;
	}

	public Integer getID() {
		return ID;
	}

	public BigDecimal getVALOR_ASSIDUIDADE() {
		return VALOR_ASSIDUIDADE;
	}

	public BigDecimal getVALOR_PONTUALIDADE() {
		return VALOR_PONTUALIDADE;
	}

	public BigDecimal getVALOR_POLIVALENCIA() {
		return VALOR_POLIVALENCIA;
	}

	public BigDecimal getVALOR_QUALIDADE() {
		return VALOR_QUALIDADE;
	}

	public BigDecimal getVALOR_PLANOFERIAS() {
		return VALOR_PLANOFERIAS;
	}

	public BigDecimal getVALOR_HORASEXTRA() {
		return VALOR_HORASEXTRA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public RH_DIC_CATEGORIAS_PROFISSIONAIS getCategoria() {
		return categoria;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public Timestamp getDATA_ANULA() {
		return DATA_ANULA;
	}

	public Integer getUTZ_ANULA() {
		return UTZ_ANULA;
	}

	public Boolean getATIVO() {
		return ATIVO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setVALOR_ASSIDUIDADE(BigDecimal vALOR_ASSIDUIDADE) {
		VALOR_ASSIDUIDADE = vALOR_ASSIDUIDADE;
	}

	public void setVALOR_PONTUALIDADE(BigDecimal vALOR_PONTUALIDADE) {
		VALOR_PONTUALIDADE = vALOR_PONTUALIDADE;
	}

	public void setVALOR_POLIVALENCIA(BigDecimal vALOR_POLIVALENCIA) {
		VALOR_POLIVALENCIA = vALOR_POLIVALENCIA;
	}

	public void setVALOR_QUALIDADE(BigDecimal vALOR_QUALIDADE) {
		VALOR_QUALIDADE = vALOR_QUALIDADE;
	}

	public void setVALOR_PLANOFERIAS(BigDecimal vALOR_PLANOFERIAS) {
		VALOR_PLANOFERIAS = vALOR_PLANOFERIAS;
	}

	public void setVALOR_HORASEXTRA(BigDecimal vALOR_HORASEXTRA) {
		VALOR_HORASEXTRA = vALOR_HORASEXTRA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setCategoria(RH_DIC_CATEGORIAS_PROFISSIONAIS categoria) {
		this.categoria = categoria;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public void setDATA_ANULA(Timestamp dATA_ANULA) {
		DATA_ANULA = dATA_ANULA;
	}

	public void setUTZ_ANULA(Integer uTZ_ANULA) {
		UTZ_ANULA = uTZ_ANULA;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public BigDecimal getVALOR_AVALIACAO_DESEMPENHO() {
		return VALOR_AVALIACAO_DESEMPENHO;
	}

	public void setVALOR_AVALIACAO_DESEMPENHO(BigDecimal vALOR_AVALIACAO_DESEMPENHO) {
		VALOR_AVALIACAO_DESEMPENHO = vALOR_AVALIACAO_DESEMPENHO;
	}
	
	

}