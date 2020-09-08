package pt.example.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RH_PAUSAS")
public class RH_PAUSAS {

	public Integer ID;
	public String ID_PAUSA_SILVER;
	public String DESCRICAO_PAUSA;
	public Integer ID_TIPO_PAUSA;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	@Column(name = "ID_PAUSA_SILVER")
	public String getID_PAUSA_SILVER() {
		return ID_PAUSA_SILVER;
	}

	@Column(name = "DESCRICAO_PAUSA")
	public String getDESCRICAO_PAUSA() {
		return DESCRICAO_PAUSA;
	}

	@Column(name = "ID_TIPO_PAUSA")
	public Integer getID_TIPO_PAUSA() {
		return ID_TIPO_PAUSA;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_PAUSA_SILVER(String iD_PAUSA_SILVER) {
		ID_PAUSA_SILVER = iD_PAUSA_SILVER;
	}

	public void setDESCRICAO_PAUSA(String dESCRICAO_PAUSA) {
		DESCRICAO_PAUSA = dESCRICAO_PAUSA;
	}

	public void setID_TIPO_PAUSA(Integer iD_TIPO_PAUSA) {
		ID_TIPO_PAUSA = iD_TIPO_PAUSA;
	}

}