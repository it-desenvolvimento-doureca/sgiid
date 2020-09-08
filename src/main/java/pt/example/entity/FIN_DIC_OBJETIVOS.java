package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FIN_DIC_OBJETIVOS")
public class FIN_DIC_OBJETIVOS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_OBJETIVO")
	private Integer ID_OBJETIVO;
	@Column(name = "MES")
	private Integer MES;
	@Column(name = "ANO")
	private Integer ANO;
	@Column(name = "VALOR_OBJETIVO")
	private Float VALOR_OBJETIVO;
	@Column(name = "N_DIAS_UTEIS")
	private Integer N_DIAS_UTEIS;

	public Integer getID_OBJETIVO() {
		return ID_OBJETIVO;
	}

	public Integer getMES() {
		return MES;
	}

	public Integer getANO() {
		return ANO;
	}

	public Float getVALOR_OBJETIVO() {
		return VALOR_OBJETIVO;
	}

	public Integer getN_DIAS_UTEIS() {
		return N_DIAS_UTEIS;
	}

	public void setID_OBJETIVO(Integer iD_OBJETIVO) {
		ID_OBJETIVO = iD_OBJETIVO;
	}

	public void setMES(Integer mES) {
		MES = mES;
	}

	public void setANO(Integer aNO) {
		ANO = aNO;
	}

	public void setVALOR_OBJETIVO(Float vALOR_OBJETIVO) {
		VALOR_OBJETIVO = vALOR_OBJETIVO;
	}

	public void setN_DIAS_UTEIS(Integer n_DIAS_UTEIS) {
		N_DIAS_UTEIS = n_DIAS_UTEIS;
	}

}
