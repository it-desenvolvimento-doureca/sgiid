package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FICHEIROS_PAGINAS")
public class FICHEIROS_PAGINAS {

	public Integer ID_FICHEIRO;
	public Float TAMANHO;
	public String NOME;
	public Integer ID_UTZ_CRIA;
	public Timestamp DATA_CRIA;
	public String DATATYPE;
	public String FICHEIRO;
	public Integer ID_PAGINA;
	public String PAGINA;
	public String DESCRICAO;
	public String ID_CONTRATO;
	public String FICHEIRO_2;
	
	@Column(name = "ID_CONTRATO")
	public String getID_CONTRATO() {
		return ID_CONTRATO;
	}

	public void setID_CONTRATO(String iD_CONTRATO) {
		ID_CONTRATO = iD_CONTRATO;
	}

	@Column(name = "DESCRICAO")
	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	@Column(name = "PAGINA")
	public String getPAGINA() {
		return PAGINA;
	}

	public void setPAGINA(String pAGINA) {
		PAGINA = pAGINA;
	}

	@Id
	@Column(name = "ID_FICHEIRO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_FICHEIRO() {
		return ID_FICHEIRO;
	}

	@Column(name = "TAMANHO")
	public Float getTAMANHO() {
		return TAMANHO;
	}

	@Column(name = "NOME")
	public String getNOME() {
		return NOME;
	}

	@Column(name = "ID_UTZ_CRIA")
	public Integer getID_UTZ_CRIA() {
		return ID_UTZ_CRIA;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "DATATYPE")
	public String getDATATYPE() {
		return DATATYPE;
	}

	@Column(name = "FICHEIRO")
	public String getFICHEIRO() {
		return FICHEIRO;
	}

	@Column(name = "ID_PAGINA")
	public Integer getID_PAGINA() {
		return ID_PAGINA;
	}

	public void setID_FICHEIRO(Integer iD_FICHEIRO) {
		ID_FICHEIRO = iD_FICHEIRO;
	}

	public void setTAMANHO(Float tAMANHO) {
		TAMANHO = tAMANHO;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public void setID_UTZ_CRIA(Integer iD_UTZ_CRIA) {
		ID_UTZ_CRIA = iD_UTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setDATATYPE(String dATATYPE) {
		DATATYPE = dATATYPE;
	}

	public void setFICHEIRO(String fICHEIRO) {
		FICHEIRO = fICHEIRO;
	}

	public void setID_PAGINA(Integer iD_PAGINA) {
		ID_PAGINA = iD_PAGINA;
	}

	public String getFICHEIRO_2() {
		return FICHEIRO_2;
	}

	public void setFICHEIRO_2(String fICHEIRO_2) {
		FICHEIRO_2 = fICHEIRO_2;
	}

}