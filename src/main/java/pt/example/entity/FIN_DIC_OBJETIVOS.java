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
	@Column(name = "VALOR_OBJETIVO_ANUAL")
	private Float VALOR_OBJETIVO_ANUAL;
	@Column(name = "VALOR_OBJETIVO_TOOLS")
	private Float VALOR_OBJETIVO_TOOLS;

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

	public Float getVALOR_OBJETIVO_ANUAL() {
		return VALOR_OBJETIVO_ANUAL;
	}

	public void setVALOR_OBJETIVO_ANUAL(Float vALOR_OBJETIVO_ANUAL) {
		VALOR_OBJETIVO_ANUAL = vALOR_OBJETIVO_ANUAL;
	}

	public Float getVALOR_OBJETIVO_TOOLS() {
		return VALOR_OBJETIVO_TOOLS;
	}

	public void setVALOR_OBJETIVO_TOOLS(Float vALOR_OBJETIVO_TOOLS) {
		VALOR_OBJETIVO_TOOLS = vALOR_OBJETIVO_TOOLS;
	}

}
