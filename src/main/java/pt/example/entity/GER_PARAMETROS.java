package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_PARAMETROS")
public class GER_PARAMETROS {

	private Integer ID;
	private String PASTA_FICHEIRO;
	private String URL_SILVER;
	private String URL_JASPER;
	private String PASTA_ETIQUETAS;
	private String MODELO_REPORT;
	private String PASTA_JASPERREPORT;
	private Integer TEMPO_PLANEADAS;
	private Boolean CARTELAS_ATIVO;
	private Integer TEMPO_SINCRO_CARTELAS;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	@Column(name = "PASTA_FICHEIRO")
	public String getPASTA_FICHEIRO() {
		return PASTA_FICHEIRO;
	}

	public void setPASTA_FICHEIRO(String pASTA_FICHEIRO) {
		PASTA_FICHEIRO = pASTA_FICHEIRO;
	}

	@Column(name = "URL_SILVER")
	public String getURL_SILVER() {
		return URL_SILVER;
	}

	public void setURL_SILVER(String uRL_SILVER) {
		URL_SILVER = uRL_SILVER;
	}

	@Column(name = "URL_JASPER")
	public String getURL_JASPER() {
		return URL_JASPER;
	}

	public void setURL_JASPER(String uRL_JASPER) {
		URL_JASPER = uRL_JASPER;
	}

	@Column(name = "PASTA_ETIQUETAS")
	public String getPASTA_ETIQUETAS() {
		return PASTA_ETIQUETAS;
	}

	public void setPASTA_ETIQUETAS(String pASTA_ETIQUETAS) {
		PASTA_ETIQUETAS = pASTA_ETIQUETAS;
	}

	@Column(name = "MODELO_REPORT")
	public String getMODELO_REPORT() {
		return MODELO_REPORT;
	}

	public void setMODELO_REPORT(String mODELO_REPORT) {
		MODELO_REPORT = mODELO_REPORT;
	}

	@Column(name = "PASTA_JASPERREPORT")
	public String getPASTA_JASPERREPORT() {
		return PASTA_JASPERREPORT;
	}

	public void setPASTA_JASPERREPORT(String pASTA_JASPERREPORT) {
		PASTA_JASPERREPORT = pASTA_JASPERREPORT;
	}

	@Column(name = "TEMPO_PLANEADAS")
	public Integer getTEMPO_PLANEADAS() {
		return TEMPO_PLANEADAS;
	}

	public void setTEMPO_PLANEADAS(Integer tEMPO_PLANEADAS) {
		TEMPO_PLANEADAS = tEMPO_PLANEADAS;
	}

	@Column(name = "CARTELAS_ATIVO")
	public Boolean getCARTELAS_ATIVO() {
		return CARTELAS_ATIVO;
	}

	@Column(name = "TEMPO_SINCRO_CARTELAS")
	public Integer getTEMPO_SINCRO_CARTELAS() {
		return TEMPO_SINCRO_CARTELAS;
	}

	public void setCARTELAS_ATIVO(Boolean cARTELAS_ATIVO) {
		CARTELAS_ATIVO = cARTELAS_ATIVO;
	}

	public void setTEMPO_SINCRO_CARTELAS(Integer tEMPO_SINCRO_CARTELAS) {
		TEMPO_SINCRO_CARTELAS = tEMPO_SINCRO_CARTELAS;
	}

}
