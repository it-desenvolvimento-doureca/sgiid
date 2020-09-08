package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PR_DIC_CAPACIDADE_RACKS")
public class PR_DIC_CAPACIDADE_RACKS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CAPACIDADE_RACKS")
	private Integer ID_CAPACIDADE_RACKS;
	@Column(name = "N_VOLTAS_DIA")
	private Integer N_VOLTAS_DIA;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;

	public Integer getID_CAPACIDADE_RACKS() {
		return ID_CAPACIDADE_RACKS;
	}

	public Integer getN_VOLTAS_DIA() {
		return N_VOLTAS_DIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setID_CAPACIDADE_RACKS(Integer iD_CAPACIDADE_RACKS) {
		ID_CAPACIDADE_RACKS = iD_CAPACIDADE_RACKS;
	}

	public void setN_VOLTAS_DIA(Integer n_VOLTAS_DIA) {
		N_VOLTAS_DIA = n_VOLTAS_DIA;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}
}