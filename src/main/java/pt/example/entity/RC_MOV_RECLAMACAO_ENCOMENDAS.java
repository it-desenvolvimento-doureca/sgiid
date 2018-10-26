package pt.example.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RC_MOV_RECLAMACAO_ENCOMENDAS")
public class RC_MOV_RECLAMACAO_ENCOMENDAS {
	private Integer ID;
	private Integer CDDCHRONO;
	private String ADRNOM;
	private String ETSNUM;
	private String ADRLIB1;
	private String ADRLIB2;
	private Integer CLICOD;
	private Float QUANTIDADE;
	private Date CDDDATBES;
	private Integer ID_LINHA_ARTIGO_SIMILAR;

	private Integer ID_RECLAMACAO;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;

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

	@Column(name = "CDDCHRONO")
	public Integer getCDDCHRONO() {
		return CDDCHRONO;
	}

	public void setCDDCHRONO(Integer cDDCHRONO) {
		CDDCHRONO = cDDCHRONO;
	}

	@Column(name = "ADRNOM")
	public String getADRNOM() {
		return ADRNOM;
	}

	public void setADRNOM(String aDRNOM) {
		ADRNOM = aDRNOM;
	}

	@Column(name = "ETSNUM")
	public String getETSNUM() {
		return ETSNUM;
	}

	public void setETSNUM(String eTSNUM) {
		ETSNUM = eTSNUM;
	}

	@Column(name = "ADRLIB1")
	public String getADRLIB1() {
		return ADRLIB1;
	}

	public void setADRLIB1(String aDRLIB1) {
		ADRLIB1 = aDRLIB1;
	}

	@Column(name = "ADRLIB2")
	public String getADRLIB2() {
		return ADRLIB2;
	}

	public void setADRLIB2(String aDRLIB2) {
		ADRLIB2 = aDRLIB2;
	}

	@Column(name = "CLICOD")
	public Integer getCLICOD() {
		return CLICOD;
	}

	public void setCLICOD(Integer cLICOD) {
		CLICOD = cLICOD;
	}

	@Column(name = "QUANTIDADE")
	public Float getQUANTIDADE() {
		return QUANTIDADE;
	}

	public void setQUANTIDADE(Float qUANTIDADE) {
		QUANTIDADE = qUANTIDADE;
	}

	@Column(name = "CDDDATBES")
	public Date getCDDDATBES() {
		return CDDDATBES;
	}

	public void setCDDDATBES(Date cDDDATBES) {
		CDDDATBES = cDDDATBES;
	}

	@Column(name = "ID_RECLAMACAO")
	public Integer getID_RECLAMACAO() {
		return ID_RECLAMACAO;
	}

	@Column(name = "ID_LINHA_ARTIGO_SIMILAR")
	public Integer getID_LINHA_ARTIGO_SIMILAR() {
		return ID_LINHA_ARTIGO_SIMILAR;
	}

	public void setID_LINHA_ARTIGO_SIMILAR(Integer iD_LINHA_ARTIGO_SIMILAR) {
		ID_LINHA_ARTIGO_SIMILAR = iD_LINHA_ARTIGO_SIMILAR;
	}

	public void setID_RECLAMACAO(Integer iD_RECLAMACAO) {
		ID_RECLAMACAO = iD_RECLAMACAO;
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

}
