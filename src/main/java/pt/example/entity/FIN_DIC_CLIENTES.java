package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FIN_DIC_CLIENTES")
public class FIN_DIC_CLIENTES {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "ID_CLIENTE")
	private String ID_CLIENTE;
	@Column(name = "FUNCAO_1")
	private String FUNCAO_1;
	@Column(name = "NOME_1")
	private String NOME_1;
	@Column(name = "EMAIL_1")
	private String EMAIL_1;
	@Column(name = "TLM_1")
	private String TLM_1;
	@Column(name = "FUNCAO_2")
	private String FUNCAO_2;
	@Column(name = "NOME_2")
	private String NOME_2;
	@Column(name = "EMAIL_2")
	private String EMAIL_2;
	@Column(name = "TLM_2")
	private String TLM_2;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "BLOQUEADO")
	private Boolean BLOQUEADO;

	public Integer getID() {
		return ID;
	}

	public String getID_CLIENTE() {
		return ID_CLIENTE;
	}

	public String getFUNCAO_1() {
		return FUNCAO_1;
	}

	public String getNOME_1() {
		return NOME_1;
	}

	public String getEMAIL_1() {
		return EMAIL_1;
	}

	public String getTLM_1() {
		return TLM_1;
	}

	public String getFUNCAO_2() {
		return FUNCAO_2;
	}

	public String getNOME_2() {
		return NOME_2;
	}

	public String getEMAIL_2() {
		return EMAIL_2;
	}

	public String getTLM_2() {
		return TLM_2;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_CLIENTE(String iD_CLIENTE) {
		ID_CLIENTE = iD_CLIENTE;
	}

	public void setFUNCAO_1(String fUNCAO_1) {
		FUNCAO_1 = fUNCAO_1;
	}

	public void setNOME_1(String nOME_1) {
		NOME_1 = nOME_1;
	}

	public void setEMAIL_1(String eMAIL_1) {
		EMAIL_1 = eMAIL_1;
	}

	public void setTLM_1(String tLM_1) {
		TLM_1 = tLM_1;
	}

	public void setFUNCAO_2(String fUNCAO_2) {
		FUNCAO_2 = fUNCAO_2;
	}

	public void setNOME_2(String nOME_2) {
		NOME_2 = nOME_2;
	}

	public void setEMAIL_2(String eMAIL_2) {
		EMAIL_2 = eMAIL_2;
	}

	public void setTLM_2(String tLM_2) {
		TLM_2 = tLM_2;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public Boolean getBLOQUEADO() {
		return BLOQUEADO;
	}

	public void setBLOQUEADO(Boolean bLOQUEADO) {
		BLOQUEADO = bLOQUEADO;
	}

}
