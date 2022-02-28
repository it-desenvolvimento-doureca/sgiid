package pt.example.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA")
public class QUA_DIC_TIPOS_AUDITORIA_QTD_PREVISTA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "ID_TIPO_AUDITORIA")
	private Integer ID_TIPO_AUDITORIA;
	@Column(name = "ANO")
	private Integer ANO;
	@Column(name = "QUANTIDADE_MES_1")
	private Integer QUANTIDADE_MES_1;
	@Column(name = "QUANTIDADE_MES_2")
	private Integer QUANTIDADE_MES_2;
	@Column(name = "QUANTIDADE_MES_3")
	private Integer QUANTIDADE_MES_3;
	@Column(name = "QUANTIDADE_MES_4")
	private Integer QUANTIDADE_MES_4;
	@Column(name = "QUANTIDADE_MES_5")
	private Integer QUANTIDADE_MES_5;
	@Column(name = "QUANTIDADE_MES_6")
	private Integer QUANTIDADE_MES_6;
	@Column(name = "QUANTIDADE_MES_7")
	private Integer QUANTIDADE_MES_7;
	@Column(name = "QUANTIDADE_MES_8")
	private Integer QUANTIDADE_MES_8;
	@Column(name = "QUANTIDADE_MES_9")
	private Integer QUANTIDADE_MES_9;
	@Column(name = "QUANTIDADE_MES_10")
	private Integer QUANTIDADE_MES_10;
	@Column(name = "QUANTIDADE_MES_11")
	private Integer QUANTIDADE_MES_11;
	@Column(name = "QUANTIDADE_MES_12")
	private Integer QUANTIDADE_MES_12;

	public Integer getID() {
		return ID;
	}

	public Integer getID_TIPO_AUDITORIA() {
		return ID_TIPO_AUDITORIA;
	}

	public Integer getANO() {
		return ANO;
	}

	public Integer getQUANTIDADE_MES_1() {
		return QUANTIDADE_MES_1;
	}

	public Integer getQUANTIDADE_MES_2() {
		return QUANTIDADE_MES_2;
	}

	public Integer getQUANTIDADE_MES_3() {
		return QUANTIDADE_MES_3;
	}

	public Integer getQUANTIDADE_MES_4() {
		return QUANTIDADE_MES_4;
	}

	public Integer getQUANTIDADE_MES_5() {
		return QUANTIDADE_MES_5;
	}

	public Integer getQUANTIDADE_MES_6() {
		return QUANTIDADE_MES_6;
	}

	public Integer getQUANTIDADE_MES_7() {
		return QUANTIDADE_MES_7;
	}

	public Integer getQUANTIDADE_MES_8() {
		return QUANTIDADE_MES_8;
	}

	public Integer getQUANTIDADE_MES_9() {
		return QUANTIDADE_MES_9;
	}

	public Integer getQUANTIDADE_MES_10() {
		return QUANTIDADE_MES_10;
	}

	public Integer getQUANTIDADE_MES_11() {
		return QUANTIDADE_MES_11;
	}

	public Integer getQUANTIDADE_MES_12() {
		return QUANTIDADE_MES_12;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_TIPO_AUDITORIA(Integer iD_TIPO_AUDITORIA) {
		ID_TIPO_AUDITORIA = iD_TIPO_AUDITORIA;
	}

	public void setANO(Integer aNO) {
		ANO = aNO;
	}

	public void setQUANTIDADE_MES_1(Integer qUANTIDADE_MES_1) {
		QUANTIDADE_MES_1 = qUANTIDADE_MES_1;
	}

	public void setQUANTIDADE_MES_2(Integer qUANTIDADE_MES_2) {
		QUANTIDADE_MES_2 = qUANTIDADE_MES_2;
	}

	public void setQUANTIDADE_MES_3(Integer qUANTIDADE_MES_3) {
		QUANTIDADE_MES_3 = qUANTIDADE_MES_3;
	}

	public void setQUANTIDADE_MES_4(Integer qUANTIDADE_MES_4) {
		QUANTIDADE_MES_4 = qUANTIDADE_MES_4;
	}

	public void setQUANTIDADE_MES_5(Integer qUANTIDADE_MES_5) {
		QUANTIDADE_MES_5 = qUANTIDADE_MES_5;
	}

	public void setQUANTIDADE_MES_6(Integer qUANTIDADE_MES_6) {
		QUANTIDADE_MES_6 = qUANTIDADE_MES_6;
	}

	public void setQUANTIDADE_MES_7(Integer qUANTIDADE_MES_7) {
		QUANTIDADE_MES_7 = qUANTIDADE_MES_7;
	}

	public void setQUANTIDADE_MES_8(Integer qUANTIDADE_MES_8) {
		QUANTIDADE_MES_8 = qUANTIDADE_MES_8;
	}

	public void setQUANTIDADE_MES_9(Integer qUANTIDADE_MES_9) {
		QUANTIDADE_MES_9 = qUANTIDADE_MES_9;
	}

	public void setQUANTIDADE_MES_10(Integer qUANTIDADE_MES_10) {
		QUANTIDADE_MES_10 = qUANTIDADE_MES_10;
	}

	public void setQUANTIDADE_MES_11(Integer qUANTIDADE_MES_11) {
		QUANTIDADE_MES_11 = qUANTIDADE_MES_11;
	}

	public void setQUANTIDADE_MES_12(Integer qUANTIDADE_MES_12) {
		QUANTIDADE_MES_12 = qUANTIDADE_MES_12;
	}

}
