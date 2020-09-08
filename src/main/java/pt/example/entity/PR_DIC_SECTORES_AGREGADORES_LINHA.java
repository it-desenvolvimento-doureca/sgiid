package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PR_DIC_SECTORES_AGREGADORES_LINHA")
public class PR_DIC_SECTORES_AGREGADORES_LINHA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SECTOR_AGREGADOR_LINHA")
	private Integer ID_SECTOR_AGREGADOR_LINHA;
	@Column(name = "ID_SECTOR_AGREGADOR")
	private Integer ID_SECTOR_AGREGADOR;
	@Column(name = "LINHA")
	private Integer LINHA;
	@Column(name = "COD_SECTOR")
	private Integer COD_SECTOR;

	public Integer getID_SECTOR_AGREGADOR_LINHA() {
		return ID_SECTOR_AGREGADOR_LINHA;
	}

	public Integer getID_SECTOR_AGREGADOR() {
		return ID_SECTOR_AGREGADOR;
	}

	public Integer getLINHA() {
		return LINHA;
	}

	public Integer getCOD_SECTOR() {
		return COD_SECTOR;
	}

	public void setID_SECTOR_AGREGADOR_LINHA(Integer iD_SECTOR_AGREGADOR_LINHA) {
		ID_SECTOR_AGREGADOR_LINHA = iD_SECTOR_AGREGADOR_LINHA;
	}

	public void setID_SECTOR_AGREGADOR(Integer iD_SECTOR_AGREGADOR) {
		ID_SECTOR_AGREGADOR = iD_SECTOR_AGREGADOR;
	}

	public void setLINHA(Integer lINHA) {
		LINHA = lINHA;
	}

	public void setCOD_SECTOR(Integer cOD_SECTOR) {
		COD_SECTOR = cOD_SECTOR;
	}

}
