package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_CONF_CONSUMOS_SILVER")
public class GER_CONF_CONSUMOS_SILVER {

	private Integer ID_CONF;
	private String SECCAO_MANUTENCAO;
	private String SUBSECCAO_MANUTENCAO;
	private String REF_COMPOSTO_MANUTENCAO;

	@Id
	@Column(name = "ID_CONF")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_CONF() {
		return ID_CONF;
	}

	@Column(name = "SECCAO_MANUTENCAO")
	public String getSECCAO_MANUTENCAO() {
		return SECCAO_MANUTENCAO;
	}

	@Column(name = "SUBSECCAO_MANUTENCAO")
	public String getSUBSECCAO_MANUTENCAO() {
		return SUBSECCAO_MANUTENCAO;
	}

	@Column(name = "REF_COMPOSTO_MANUTENCAO")
	public String getREF_COMPOSTO_MANUTENCAO() {
		return REF_COMPOSTO_MANUTENCAO;
	}

	public void setID_CONF(Integer iD_CONF) {
		ID_CONF = iD_CONF;
	}

	public void setSECCAO_MANUTENCAO(String sECCAO_MANUTENCAO) {
		SECCAO_MANUTENCAO = sECCAO_MANUTENCAO;
	}

	public void setSUBSECCAO_MANUTENCAO(String sUBSECCAO_MANUTENCAO) {
		SUBSECCAO_MANUTENCAO = sUBSECCAO_MANUTENCAO;
	}

	public void setREF_COMPOSTO_MANUTENCAO(String rEF_COMPOSTO_MANUTENCAO) {
		REF_COMPOSTO_MANUTENCAO = rEF_COMPOSTO_MANUTENCAO;
	}

}
