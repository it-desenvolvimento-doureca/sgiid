package pt.example.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLANEAMENTO_LINHAS")
public class PLANEAMENTO_LINHAS {
	public Integer ID_LINHA;
	public Integer ID_PLANEAMENTO;
	public Date DATA;
	public Integer PLANEADAS_TURNO_1;
	public Integer PLANEADAS_TURNO_2;
	public Integer PLANEADAS_TURNO_3;
	public Integer PROD_PLANEADAS_TURNO_1;
	public Integer PROD_PLANEADAS_TURNO_2;
	public Integer PROD_PLANEADAS_TURNO_3;
	public Integer BARRAS_PROD_TURNO_1;
	public Integer BARRAS_PROD_TURNO_2;
	public Integer BARRAS_PROD_TURNO_3;
	public Integer BARRAS_INTROD_TURNO_1;
	public Integer BARRAS_INTROD_TURNO_2;
	public Integer BARRAS_INTROD_TURNO_3;

	@Id
	@Column(name = "ID_LINHA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_LINHA() {
		return ID_LINHA;
	}

	@Column(name = "ID_PLANEAMENTO")
	public Integer getID_PLANEAMENTO() {
		return ID_PLANEAMENTO;
	}

	@Column(name = "DATA")
	public Date getDATA() {
		return DATA;
	}

	@Column(name = "PLANEADAS_TURNO_1")
	public Integer getPLANEADAS_TURNO_1() {
		return PLANEADAS_TURNO_1;
	}

	@Column(name = "PLANEADAS_TURNO_2")
	public Integer getPLANEADAS_TURNO_2() {
		return PLANEADAS_TURNO_2;
	}

	@Column(name = "PLANEADAS_TURNO_3")
	public Integer getPLANEADAS_TURNO_3() {
		return PLANEADAS_TURNO_3;
	}

	@Column(name = "PROD_PLANEADAS_TURNO_1")
	public Integer getPROD_PLANEADAS_TURNO_1() {
		return PROD_PLANEADAS_TURNO_1;
	}

	@Column(name = "PROD_PLANEADAS_TURNO_2")
	public Integer getPROD_PLANEADAS_TURNO_2() {
		return PROD_PLANEADAS_TURNO_2;
	}

	@Column(name = "PROD_PLANEADAS_TURNO_3")
	public Integer getPROD_PLANEADAS_TURNO_3() {
		return PROD_PLANEADAS_TURNO_3;
	}

	@Column(name = "BARRAS_PROD_TURNO_1")
	public Integer getBARRAS_PROD_TURNO_1() {
		return BARRAS_PROD_TURNO_1;
	}

	@Column(name = "BARRAS_PROD_TURNO_2")
	public Integer getBARRAS_PROD_TURNO_2() {
		return BARRAS_PROD_TURNO_2;
	}

	@Column(name = "BARRAS_PROD_TURNO_3")
	public Integer getBARRAS_PROD_TURNO_3() {
		return BARRAS_PROD_TURNO_3;
	}

	@Column(name = "BARRAS_INTROD_TURNO_1")
	public Integer getBARRAS_INTROD_TURNO_1() {
		return BARRAS_INTROD_TURNO_1;
	}

	@Column(name = "BARRAS_INTROD_TURNO_2")
	public Integer getBARRAS_INTROD_TURNO_2() {
		return BARRAS_INTROD_TURNO_2;
	}

	@Column(name = "BARRAS_INTROD_TURNO_3")
	public Integer getBARRAS_INTROD_TURNO_3() {
		return BARRAS_INTROD_TURNO_3;
	}

	public void setID_LINHA(Integer iD_LINHA) {
		ID_LINHA = iD_LINHA;
	}

	public void setID_PLANEAMENTO(Integer iD_PLANEAMENTO) {
		ID_PLANEAMENTO = iD_PLANEAMENTO;
	}

	public void setDATA(Date dATA) {
		DATA = dATA;
	}

	public void setPLANEADAS_TURNO_1(Integer pLANEADAS_TURNO_1) {
		PLANEADAS_TURNO_1 = pLANEADAS_TURNO_1;
	}

	public void setPLANEADAS_TURNO_2(Integer pLANEADAS_TURNO_2) {
		PLANEADAS_TURNO_2 = pLANEADAS_TURNO_2;
	}

	public void setPLANEADAS_TURNO_3(Integer pLANEADAS_TURNO_3) {
		PLANEADAS_TURNO_3 = pLANEADAS_TURNO_3;
	}

	public void setPROD_PLANEADAS_TURNO_1(Integer pROD_PLANEADAS_TURNO_1) {
		PROD_PLANEADAS_TURNO_1 = pROD_PLANEADAS_TURNO_1;
	}

	public void setPROD_PLANEADAS_TURNO_2(Integer pROD_PLANEADAS_TURNO_2) {
		PROD_PLANEADAS_TURNO_2 = pROD_PLANEADAS_TURNO_2;
	}

	public void setPROD_PLANEADAS_TURNO_3(Integer pROD_PLANEADAS_TURNO_3) {
		PROD_PLANEADAS_TURNO_3 = pROD_PLANEADAS_TURNO_3;
	}

	public void setBARRAS_PROD_TURNO_1(Integer bARRAS_PROD_TURNO_1) {
		BARRAS_PROD_TURNO_1 = bARRAS_PROD_TURNO_1;
	}

	public void setBARRAS_PROD_TURNO_2(Integer bARRAS_PROD_TURNO_2) {
		BARRAS_PROD_TURNO_2 = bARRAS_PROD_TURNO_2;
	}

	public void setBARRAS_PROD_TURNO_3(Integer bARRAS_PROD_TURNO_3) {
		BARRAS_PROD_TURNO_3 = bARRAS_PROD_TURNO_3;
	}

	public void setBARRAS_INTROD_TURNO_1(Integer bARRAS_INTROD_TURNO_1) {
		BARRAS_INTROD_TURNO_1 = bARRAS_INTROD_TURNO_1;
	}

	public void setBARRAS_INTROD_TURNO_2(Integer bARRAS_INTROD_TURNO_2) {
		BARRAS_INTROD_TURNO_2 = bARRAS_INTROD_TURNO_2;
	}

	public void setBARRAS_INTROD_TURNO_3(Integer bARRAS_INTROD_TURNO_3) {
		BARRAS_INTROD_TURNO_3 = bARRAS_INTROD_TURNO_3;
	}

}
