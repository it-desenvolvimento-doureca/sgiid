package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIPOS_OCORRENCIA")
public class TIPOS_OCORRENCIA {
	public Integer ID_TIPO;
	public String DESCRICAO;
	public String COR;
	public Integer GRAU;
	public String CODIGO;
	public Timestamp DATA_MODIF;
	public Integer UTZ_MODIF;

	@Id
	@Column(name = "ID_TIPO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_TIPO() {
		return ID_TIPO;
	}

	@Column(name = "DESCRICAO")
	public String getDESCRICAO() {
		return DESCRICAO;
	}

	@Column(name = "COR")
	public String getCOR() {
		return COR;
	}

	@Column(name = "GRAU")
	public Integer getGRAU() {
		return GRAU;
	}

	@Column(name = "CODIGO")
	public String getCODIGO() {
		return CODIGO;
	}

	@Column(name = "DATA_MODIF")
	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	@Column(name = "UTZ_MODIF")
	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setID_TIPO(Integer iD_TIPO) {
		ID_TIPO = iD_TIPO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setCOR(String cOR) {
		COR = cOR;
	}

	public void setGRAU(Integer gRAU) {
		GRAU = gRAU;
	}

	public void setCODIGO(String cODIGO) {
		CODIGO = cODIGO;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

}
