package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GT_DIC_TAREFAS")
public class GT_DIC_TAREFAS {
	private Integer ID;
	private String DESCRICAO_PT;
	private String DESCRICAO_ENG;
	private String DESCRICAO_FR;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private String TIPO_TAREFA;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	@Column(name = "DESCRICAO_PT")
	public String getDESCRICAO_PT() {
		return DESCRICAO_PT;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "UTZ_CRIA")
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	@Column(name = "DATA_ULT_MODIF")
	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	@Column(name = "UTZ_ULT_MODIF")
	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	@Column(name = "DESCRICAO_ENG")
	public String getDESCRICAO_ENG() {
		return DESCRICAO_ENG;
	}

	@Column(name = "DESCRICAO_FR")
	public String getDESCRICAO_FR() {
		return DESCRICAO_FR;
	}

	public void setDESCRICAO_FR(String dESCRICAO_FR) {
		DESCRICAO_FR = dESCRICAO_FR;
	}

	public void setDESCRICAO_ENG(String dESCRICAO_ENG) {
		DESCRICAO_ENG = dESCRICAO_ENG;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setDESCRICAO_PT(String dESCRICAO) {
		DESCRICAO_PT = dESCRICAO;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	@Column(name = "TIPO_TAREFA")
	public String getTIPO_TAREFA() {
		return TIPO_TAREFA;
	}

	public void setTIPO_TAREFA(String tIPO_TAREFA) {
		TIPO_TAREFA = tIPO_TAREFA;
	}
}
