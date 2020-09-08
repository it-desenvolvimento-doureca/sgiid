package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_DEPARTAMENTOS_SECTORES")
public class GER_DEPARTAMENTOS_SECTORES {

	private Integer ID_DEPARTAMENTOS_SECTORES;
	private Integer ID_DEPARTAMENTO;
	private Integer COD_SECTOR;

	@Id
	@Column(name = "ID_DEPARTAMENTOS_SECTORES")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_DEPARTAMENTOS_SECTORES() {
		return ID_DEPARTAMENTOS_SECTORES;
	}

	@Column(name = "ID_DEPARTAMENTO")
	public Integer getID_DEPARTAMENTO() {
		return ID_DEPARTAMENTO;
	}

	@Column(name = "COD_SECTOR")
	public Integer getCOD_SECTOR() {
		return COD_SECTOR;
	}

	public void setID_DEPARTAMENTOS_SECTORES(Integer iD_DEPARTAMENTOS_SECTORES) {
		ID_DEPARTAMENTOS_SECTORES = iD_DEPARTAMENTOS_SECTORES;
	}

	public void setID_DEPARTAMENTO(Integer iD_DEPARTAMENTO) {
		ID_DEPARTAMENTO = iD_DEPARTAMENTO;
	}

	public void setCOD_SECTOR(Integer cOD_SECTOR) {
		COD_SECTOR = cOD_SECTOR;
	}

}