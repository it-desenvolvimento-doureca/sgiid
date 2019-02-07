package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RC_MOV_RECLAMACAO_FICHEIROS")
public class RC_MOV_RECLAMACAO_FICHEIROS {
	private Integer ID;
	private String DESCRICAO;
	private Float TAMANHO;
	private String CAMINHO;
	private String NOME;
	private String TIPO;
	private Integer ID_RECLAMACAO;
	private Integer ID_FICHEIRO;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_ULT_MODIF;
	private Integer UTZ_ULT_MODIF;
	private Boolean CHECKED;
	private String DATATYPE;
	private Integer ID_TAREFA;
	private String FICHEIRO;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	@Column(name = "DESCRICAO")
	public String getDESCRICAO() {
		return DESCRICAO;
	}

	@Column(name = "TAMANHO")
	public Float getTAMANHO() {
		return TAMANHO;
	}

	@Column(name = "CAMINHO")
	public String getCAMINHO() {
		return CAMINHO;
	}

	@Column(name = "NOME")
	public String getNOME() {
		return NOME;
	}

	@Column(name = "TIPO")
	public String getTIPO() {
		return TIPO;
	}

	@Column(name = "ID_RECLAMACAO")
	public Integer getID_RECLAMACAO() {
		return ID_RECLAMACAO;
	}

	@Column(name = "ID_FICHEIRO")
	public Integer getID_FICHEIRO() {
		return ID_FICHEIRO;
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

	@Column(name = "CHECKED")
	public Boolean getCHECKED() {
		return CHECKED;
	}

	@Column(name = "DATATYPE")
	public String getDATATYPE() {
		return DATATYPE;
	}

	@Column(name = "ID_TAREFA")
	public Integer getID_TAREFA() {
		return ID_TAREFA;
	}

	public void setID_TAREFA(Integer iD_TAREFA) {
		ID_TAREFA = iD_TAREFA;
	}

	public void setDATATYPE(String dATATYPE) {
		DATATYPE = dATATYPE;
	}

	public void setCHECKED(Boolean cHECKED) {
		CHECKED = cHECKED;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setTAMANHO(Float tAMANHO) {
		TAMANHO = tAMANHO;
	}

	public void setCAMINHO(String cAMINHO) {
		CAMINHO = cAMINHO;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}

	public void setID_RECLAMACAO(Integer iD_RECLAMACAO) {
		ID_RECLAMACAO = iD_RECLAMACAO;
	}

	public void setID_FICHEIRO(Integer iD_FICHEIRO) {
		ID_FICHEIRO = iD_FICHEIRO;
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

	@Column(name = "FICHEIRO")
	public String getFICHEIRO() {
		return FICHEIRO;
	}

	public void setFICHEIRO(String fICHEIRO) {
		FICHEIRO = fICHEIRO;
	}

}
