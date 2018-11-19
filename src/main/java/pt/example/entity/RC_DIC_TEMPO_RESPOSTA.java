package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RC_DIC_TEMPO_RESPOSTA")
public class RC_DIC_TEMPO_RESPOSTA {

	private Integer ID;
	private Integer TEMPO_RESPOSTA_STEP1;
	private Integer TEMPO_RESPOSTA_STEP2;
	private Integer TEMPO_RESPOSTA_STEP3;
	private Integer TEMPO_RESPOSTA_STEP4;
	private Integer TEMPO_RESPOSTA_STEP5;
	private Integer TEMPO_RESPOSTA_STEP6;
	private Integer TEMPO_RESPOSTA_STEP7;
	private Integer TEMPO_RESPOSTA_STEP8;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private Integer TEMPO_REVISAO;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	@Column(name = "TEMPO_RESPOSTA_STEP1")
	public Integer getTEMPO_RESPOSTA_STEP1() {
		return TEMPO_RESPOSTA_STEP1;
	}

	@Column(name = "TEMPO_RESPOSTA_STEP2")
	public Integer getTEMPO_RESPOSTA_STEP2() {
		return TEMPO_RESPOSTA_STEP2;
	}

	@Column(name = "TEMPO_RESPOSTA_STEP3")
	public Integer getTEMPO_RESPOSTA_STEP3() {
		return TEMPO_RESPOSTA_STEP3;
	}

	@Column(name = "TEMPO_RESPOSTA_STEP4")
	public Integer getTEMPO_RESPOSTA_STEP4() {
		return TEMPO_RESPOSTA_STEP4;
	}

	@Column(name = "TEMPO_RESPOSTA_STEP5")
	public Integer getTEMPO_RESPOSTA_STEP5() {
		return TEMPO_RESPOSTA_STEP5;
	}

	@Column(name = "TEMPO_RESPOSTA_STEP6")
	public Integer getTEMPO_RESPOSTA_STEP6() {
		return TEMPO_RESPOSTA_STEP6;
	}

	@Column(name = "TEMPO_RESPOSTA_STEP7")
	public Integer getTEMPO_RESPOSTA_STEP7() {
		return TEMPO_RESPOSTA_STEP7;
	}

	@Column(name = "TEMPO_RESPOSTA_STEP8")
	public Integer getTEMPO_RESPOSTA_STEP8() {
		return TEMPO_RESPOSTA_STEP8;
	}

	@Column(name = "DATA_ULT_MODIF")
	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	@Column(name = "UTZ_ULT_MODIF")
	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	@Column(name = "TEMPO_REVISAO")
	public Integer getTEMPO_REVISAO() {
		return TEMPO_REVISAO;
	}

	public void setTEMPO_REVISAO(Integer tEMPO_REVISAO) {
		TEMPO_REVISAO = tEMPO_REVISAO;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public void setTEMPO_RESPOSTA_STEP1(Integer tEMPO_RESPOSTA_STEP1) {
		TEMPO_RESPOSTA_STEP1 = tEMPO_RESPOSTA_STEP1;
	}

	public void setTEMPO_RESPOSTA_STEP2(Integer tEMPO_RESPOSTA_STEP2) {
		TEMPO_RESPOSTA_STEP2 = tEMPO_RESPOSTA_STEP2;
	}

	public void setTEMPO_RESPOSTA_STEP3(Integer tEMPO_RESPOSTA_STEP3) {
		TEMPO_RESPOSTA_STEP3 = tEMPO_RESPOSTA_STEP3;
	}

	public void setTEMPO_RESPOSTA_STEP4(Integer tEMPO_RESPOSTA_STEP4) {
		TEMPO_RESPOSTA_STEP4 = tEMPO_RESPOSTA_STEP4;
	}

	public void setTEMPO_RESPOSTA_STEP5(Integer tEMPO_RESPOSTA_STEP5) {
		TEMPO_RESPOSTA_STEP5 = tEMPO_RESPOSTA_STEP5;
	}

	public void setTEMPO_RESPOSTA_STEP6(Integer tEMPO_RESPOSTA_STEP6) {
		TEMPO_RESPOSTA_STEP6 = tEMPO_RESPOSTA_STEP6;
	}

	public void setTEMPO_RESPOSTA_STEP7(Integer tEMPO_RESPOSTA_STEP7) {
		TEMPO_RESPOSTA_STEP7 = tEMPO_RESPOSTA_STEP7;
	}

	public void setTEMPO_RESPOSTA_STEP8(Integer tEMPO_RESPOSTA_STEP8) {
		TEMPO_RESPOSTA_STEP8 = tEMPO_RESPOSTA_STEP8;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

}
