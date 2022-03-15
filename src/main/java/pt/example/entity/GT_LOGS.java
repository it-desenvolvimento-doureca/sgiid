package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GT_LOGS")
public class GT_LOGS {

	private Integer ID;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private String DESCRICAO;
	private Integer ID_TAREFA;
	private String JUSTIFICACAO;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "UTZ_CRIA")
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	@Column(name = "DESCRICAO")
	public String getDESCRICAO() {
		return DESCRICAO;
	}

	@Column(name = "ID_TAREFA")
	public Integer getID_TAREFA() {
		return ID_TAREFA;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setID_TAREFA(Integer iD_TAREFA) {
		ID_TAREFA = iD_TAREFA;
	}

	@Column(name = "JUSTIFICACAO")
	public String getJUSTIFICACAO() {
		return JUSTIFICACAO;
	}

	public void setJUSTIFICACAO(String jUSTIFICACAO) {
		JUSTIFICACAO = jUSTIFICACAO;
	}

}
