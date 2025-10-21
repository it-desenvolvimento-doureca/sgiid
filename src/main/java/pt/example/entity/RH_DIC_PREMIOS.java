package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "RH_DIC_PREMIOS")
public class RH_DIC_PREMIOS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("ANO")
	private Integer ANO;

	@JsonProperty("VALOR")
	private BigDecimal VALOR;

	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;

	@ManyToOne
	@JoinColumn(name = "ID_GRUPO", nullable = false)
	@JsonProperty("GRUPO")
	private RH_DIC_GRUPOS_COLABORADORES grupo;

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

	public Integer getID() {
		return ID;
	}

	public Integer getANO() {
		return ANO;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public RH_DIC_GRUPOS_COLABORADORES getGrupo() {
		return grupo;
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

	public void setANO(Integer aNO) {
		ANO = aNO;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setGrupo(RH_DIC_GRUPOS_COLABORADORES grupo) {
		this.grupo = grupo;
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

	public BigDecimal getVALOR() {
		return VALOR;
	}

	public void setVALOR(BigDecimal vALOR) {
		VALOR = vALOR;
	}
	
	// Setter auxiliar para aceitar ID direto no JSON
		@JsonProperty("ID_GRUPO")
		public void setID_GRUPO(Integer id) {
			this.grupo = new RH_DIC_GRUPOS_COLABORADORES();
			this.grupo.setID(id);
		}

		// Getter opcional se quiseres expor o ID no JSON de saída
		@JsonProperty("ID_GRUPO")
		public Integer getID_GRUPO() {
			return grupo != null ? grupo.getID() : null;
		}
}