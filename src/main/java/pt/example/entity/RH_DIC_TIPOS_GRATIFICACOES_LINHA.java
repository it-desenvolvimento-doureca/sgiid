package pt.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "RH_DIC_TIPOS_GRATIFICACOES_LINHA")
public class RH_DIC_TIPOS_GRATIFICACOES_LINHA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("COD_GRATIFICACAO")
	private String COD_GRATIFICACAO;

	@JsonProperty("DESC_GRATIFICACAO")
	private String DESC_GRATIFICACAO;

	public Integer getID() {
		return ID;
	}

	public String getCOD_GRATIFICACAO() {
		return COD_GRATIFICACAO;
	}

	public String getDESC_GRATIFICACAO() {
		return DESC_GRATIFICACAO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setCOD_GRATIFICACAO(String cOD_GRATIFICACAO) {
		COD_GRATIFICACAO = cOD_GRATIFICACAO;
	}

	public void setDESC_GRATIFICACAO(String dESC_GRATIFICACAO) {
		DESC_GRATIFICACAO = dESC_GRATIFICACAO;
	}

}