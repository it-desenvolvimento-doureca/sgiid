package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RH_DIC_SECTORES_ABSENTISMO_LINHA")
public class RH_DIC_SECTORES_ABSENTISMO_LINHA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SECTOR_ABSENTISMO_LINHA")
	private Integer ID_SECTOR_ABSENTISMO_LINHA;
	@Column(name = "ID_SECTOR_ABSENTISMO")
	private Integer ID_SECTOR_ABSENTISMO;
	@Column(name = "COD_SECTOR")
	private Integer COD_SECTOR;

	public Integer getID_SECTOR_ABSENTISMO_LINHA() {
		return ID_SECTOR_ABSENTISMO_LINHA;
	}

	public Integer getID_SECTOR_ABSENTISMO() {
		return ID_SECTOR_ABSENTISMO;
	}

	public Integer getCOD_SECTOR() {
		return COD_SECTOR;
	}

	public void setID_SECTOR_ABSENTISMO_LINHA(Integer iD_SECTOR_ABSENTISMO_LINHA) {
		ID_SECTOR_ABSENTISMO_LINHA = iD_SECTOR_ABSENTISMO_LINHA;
	}

	public void setID_SECTOR_ABSENTISMO(Integer iD_SECTOR_ABSENTISMO) {
		ID_SECTOR_ABSENTISMO = iD_SECTOR_ABSENTISMO;
	}

	public void setCOD_SECTOR(Integer cOD_SECTOR) {
		COD_SECTOR = cOD_SECTOR;
	}

}