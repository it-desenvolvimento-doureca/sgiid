package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AT_DIC_CAUSAS_ACIDENTE")
public class AT_DIC_CAUSAS_ACIDENTE {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CAUSAS_ACIDENTE")
	private Integer ID_CAUSAS_ACIDENTE;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	/* Melhorias 2026-08 - Diagrama de Ishikawa.
	 * CATEGORIA e a espinha do diagrama: PESSOAS, METODOS, ORGANIZACAO,
	 * MAQUINAS, AMBIENTE, MATERIAIS. Causas anteriores ao diagrama tem
	 * CATEGORIA a null e INATIVO a 1 (mantidas so para historico). */
	@Column(name = "CATEGORIA")
	private String CATEGORIA;
	@Column(name = "ORDEM")
	private Integer ORDEM;
	@Column(name = "INATIVO")
	private Boolean INATIVO;
	@Column(name = "PERMITE_TEXTO")
	private Boolean PERMITE_TEXTO;

	public Integer getID_CAUSAS_ACIDENTE() {
		return ID_CAUSAS_ACIDENTE;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setID_CAUSAS_ACIDENTE(Integer iD_CAUSAS_ACIDENTE) {
		ID_CAUSAS_ACIDENTE = iD_CAUSAS_ACIDENTE;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	/* Melhorias 2026-08 */

	public String getCATEGORIA() {
		return CATEGORIA;
	}

	public void setCATEGORIA(String cATEGORIA) {
		CATEGORIA = cATEGORIA;
	}

	public Integer getORDEM() {
		return ORDEM;
	}

	public void setORDEM(Integer oRDEM) {
		ORDEM = oRDEM;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public Boolean getPERMITE_TEXTO() {
		return PERMITE_TEXTO;
	}

	public void setPERMITE_TEXTO(Boolean pERMITE_TEXTO) {
		PERMITE_TEXTO = pERMITE_TEXTO;
	}

}
