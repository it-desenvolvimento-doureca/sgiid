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
@Table(name = "RH_DIC_GRUPOS_COLABORADORES_LINHA")
public class RH_DIC_GRUPOS_COLABORADORES_LINHA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@ManyToOne
	@JoinColumn(name = "ID_GRUPO_COLABORADORES", nullable = false)
	@JsonProperty("GRUPO_COLABORADORES")
	private RH_DIC_GRUPOS_COLABORADORES grupoColaboradores;

	@JsonProperty("COD_FUNC")
	private String COD_FUNC;

	public Integer getID() {
		return ID;
	}

	public RH_DIC_GRUPOS_COLABORADORES getGrupoColaboradores() {
		return grupoColaboradores;
	}

	public String getCOD_FUNC() {
		return COD_FUNC;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setGrupoColaboradores(RH_DIC_GRUPOS_COLABORADORES grupoColaboradores) {
		this.grupoColaboradores = grupoColaboradores;
	}

	public void setCOD_FUNC(String cOD_FUNC) {
		COD_FUNC = cOD_FUNC;
	}

	// Setter auxiliar para aceitar ID direto no JSON
	@JsonProperty("ID_GRUPO_COLABORADORES")
	public void setID_GRUPO_COLABORADORES(Integer id) {
		this.grupoColaboradores = new RH_DIC_GRUPOS_COLABORADORES();
		this.grupoColaboradores.setID(id);
	}

	// Getter opcional se quiseres expor o ID no JSON de saída
	@JsonProperty("ID_GRUPO_COLABORADORES")
	public Integer getID_GRUPO_COLABORADORES() {
		return grupoColaboradores != null ? grupoColaboradores.getID() : null;
	}

}