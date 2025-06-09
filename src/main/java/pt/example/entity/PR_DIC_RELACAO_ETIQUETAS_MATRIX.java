package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PR_DIC_RELACAO_ETIQUETAS_MATRIX")
public class PR_DIC_RELACAO_ETIQUETAS_MATRIX {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("PROREF_ETIQUETA")
	private String PROREF_ETIQUETA;
	@JsonProperty("PROREF_SILVER")
	private String PROREF_SILVER;
	@JsonProperty("ID_MAQUINA")
	private Integer ID_MAQUINA;
	@JsonProperty("PROGRAMA")
	private String PROGRAMA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;

	public Integer getID() {
		return ID;
	}

	public String getPROREF_SILVER() {
		return PROREF_SILVER;
	}

	public Integer getID_MAQUINA() {
		return ID_MAQUINA;
	}

	public String getPROGRAMA() {
		return PROGRAMA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
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

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setPROREF_SILVER(String pROREF_SILVER) {
		PROREF_SILVER = pROREF_SILVER;
	}

	public void setID_MAQUINA(Integer iD_MAQUINA) {
		ID_MAQUINA = iD_MAQUINA;
	}

	public void setPROGRAMA(String pROGRAMA) {
		PROGRAMA = pROGRAMA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
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

	public String getPROREF_ETIQUETA() {
		return PROREF_ETIQUETA;
	}

	public void setPROREF_ETIQUETA(String pROREF_ETIQUETA) {
		PROREF_ETIQUETA = pROREF_ETIQUETA;
	}

}
