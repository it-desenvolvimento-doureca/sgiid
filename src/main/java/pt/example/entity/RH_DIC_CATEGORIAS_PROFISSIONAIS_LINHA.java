package pt.example.entity;

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
@Table(name = "RH_DIC_CATEGORIAS_PROFISSIONAIS_LINHA")
public class RH_DIC_CATEGORIAS_PROFISSIONAIS_LINHA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA_PROFISSIONAL", nullable = false)
	@JsonProperty("CATEGORIA_PROFISSIONAIS")
	private RH_DIC_CATEGORIAS_PROFISSIONAIS grupoCategorias;

	@JsonProperty("ID_CATEGORIA")
	private String ID_CATEGORIA;

	public Integer getID() {
		return ID;
	}

	public RH_DIC_CATEGORIAS_PROFISSIONAIS getGrupoCategorias() {
		return grupoCategorias;
	}

	public String getID_CATEGORIA() {
		return ID_CATEGORIA;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setGCategoriasores(RH_DIC_CATEGORIAS_PROFISSIONAIS grupoCategorias) {
		this.grupoCategorias = grupoCategorias;
	}

	public void setID_CATEGORIA(String iD_CATEGORIA) {
		ID_CATEGORIA = iD_CATEGORIA;
	}

	// Setter auxiliar para aceitar ID direto no JSON
	@JsonProperty("ID_CATEGORIA_PROFISSIONAL")
	public void setID_CATEGORIA_PROFISSIONAL(Integer id) {
		this.grupoCategorias = new RH_DIC_CATEGORIAS_PROFISSIONAIS();
		this.grupoCategorias.setID(id);
	}

	// Getter opcional se quiseres expor o ID no JSON de saída
	@JsonProperty("ID_CATEGORIA_PROFISSIONAL")
	public Integer getID_CATEGORIA_PROFISSIONAL() {
		return grupoCategorias != null ? grupoCategorias.getID() : null;
	}

}