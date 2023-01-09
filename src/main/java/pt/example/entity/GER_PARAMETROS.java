package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Time;

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
	private Integer TEMPO_MAX_PLANEADAS;
	private Boolean CARTELAS_ATIVO;
	private Integer TEMPO_SINCRO_CARTELAS;

	private String CAMINHO_LOGS_SILVER;
	private Integer TEMPO_SINCRO_LOGS_SILVER;
	private Boolean LOGS_SILVER_ATIVO;
	private String UTILIZADOR_LOGS_SILVER;
	private String DOMINIO_LOGS_SILVER;
	private String SENHA_LOGS_SILVER;
	private String PASTA_DESTINO_ERRO;

	private Boolean ATUALIZACAO_SILVER_BI_ATIVO;
	private Time TEMPO_PAUSA_TURNOS_CONTINUOS;

	private String MODELO_REPORT_PRODUCAO;

	private String SECTORES_ABSENTISMO;
	private Float TAXA_ABSENTISMO;
	
	private Float TAXA_REJEICAO;
	private BigDecimal OBJETIVO_AUDITORIAS;
	private Integer NUMERO_MANUTENCOES_INICIAR;
	private BigDecimal PRECO_HORA_MANUTENCAO;

	@Column(name = "TAXA_REJEICAO")
	public Float getTAXA_REJEICAO() {
		return TAXA_REJEICAO;
	}

	public void setTAXA_REJEICAO(Float tAXA_REJEICAO) {
		TAXA_REJEICAO = tAXA_REJEICAO;
	}

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

	@Column(name = "TEMPO_MAX_PLANEADAS")
	public Integer getTEMPO_MAX_PLANEADAS() {
		return TEMPO_MAX_PLANEADAS;
	}

	public void setTEMPO_MAX_PLANEADAS(Integer tEMPO_MAX_PLANEADAS) {
		TEMPO_MAX_PLANEADAS = tEMPO_MAX_PLANEADAS;
	}

	@Column(name = "CAMINHO_LOGS_SILVER")
	public String getCAMINHO_LOGS_SILVER() {
		return CAMINHO_LOGS_SILVER;
	}

	@Column(name = "TEMPO_SINCRO_LOGS_SILVER")
	public Integer getTEMPO_SINCRO_LOGS_SILVER() {
		return TEMPO_SINCRO_LOGS_SILVER;
	}

	@Column(name = "LOGS_SILVER_ATIVO")

	public Boolean getLOGS_SILVER_ATIVO() {
		return LOGS_SILVER_ATIVO;
	}

	@Column(name = "UTILIZADOR_LOGS_SILVER")
	public String getUTILIZADOR_LOGS_SILVER() {
		return UTILIZADOR_LOGS_SILVER;
	}

	@Column(name = "DOMINIO_LOGS_SILVER")
	public String getDOMINIO_LOGS_SILVER() {
		return DOMINIO_LOGS_SILVER;
	}

	@Column(name = "SENHA_LOGS_SILVER")
	public String getSENHA_LOGS_SILVER() {
		return SENHA_LOGS_SILVER;
	}

	public void setCAMINHO_LOGS_SILVER(String cAMINHO_LOGS_SILVER) {
		CAMINHO_LOGS_SILVER = cAMINHO_LOGS_SILVER;
	}

	public void setTEMPO_SINCRO_LOGS_SILVER(Integer tEMPO_SINCRO_LOGS_SILVER) {
		TEMPO_SINCRO_LOGS_SILVER = tEMPO_SINCRO_LOGS_SILVER;
	}

	public void setLOGS_SILVER_ATIVO(Boolean lOGS_SILVER_ATIVO) {
		LOGS_SILVER_ATIVO = lOGS_SILVER_ATIVO;
	}

	public void setUTILIZADOR_LOGS_SILVER(String uTILIZADOR_LOGS_SILVER) {
		UTILIZADOR_LOGS_SILVER = uTILIZADOR_LOGS_SILVER;
	}

	public void setDOMINIO_LOGS_SILVER(String dOMINIO_LOGS_SILVER) {
		DOMINIO_LOGS_SILVER = dOMINIO_LOGS_SILVER;
	}

	public void setSENHA_LOGS_SILVER(String sENHA_LOGS_SILVER) {
		SENHA_LOGS_SILVER = sENHA_LOGS_SILVER;
	}

	@Column(name = "PASTA_DESTINO_ERRO")
	public String getPASTA_DESTINO_ERRO() {
		return PASTA_DESTINO_ERRO;
	}

	public void setPASTA_DESTINO_ERRO(String pASTA_DESTINO_ERRO) {
		PASTA_DESTINO_ERRO = pASTA_DESTINO_ERRO;
	}

	@Column(name = "ATUALIZACAO_SILVER_BI_ATIVO")
	public Boolean getATUALIZACAO_SILVER_BI_ATIVO() {
		return ATUALIZACAO_SILVER_BI_ATIVO;
	}

	public void setATUALIZACAO_SILVER_BI_ATIVO(Boolean aTUALIZACAO_SILVER_BI_ATIVO) {
		ATUALIZACAO_SILVER_BI_ATIVO = aTUALIZACAO_SILVER_BI_ATIVO;
	}

	@Column(name = "TEMPO_PAUSA_TURNOS_CONTINUOS")
	public Time getTEMPO_PAUSA_TURNOS_CONTINUOS() {
		return TEMPO_PAUSA_TURNOS_CONTINUOS;
	}

	public void setTEMPO_PAUSA_TURNOS_CONTINUOS(Time tEMPO_PAUSA_TURNOS_CONTINUOS) {
		TEMPO_PAUSA_TURNOS_CONTINUOS = tEMPO_PAUSA_TURNOS_CONTINUOS;
	}

	@Column(name = "MODELO_REPORT_PRODUCAO")
	public String getMODELO_REPORT_PRODUCAO() {
		return MODELO_REPORT_PRODUCAO;
	}

	public void setMODELO_REPORT_PRODUCAO(String mODELO_REPORT_PRODUCAO) {
		MODELO_REPORT_PRODUCAO = mODELO_REPORT_PRODUCAO;
	}

	@Column(name = "SECTORES_ABSENTISMO")
	public String getSECTORES_ABSENTISMO() {
		return SECTORES_ABSENTISMO;
	}

	@Column(name = "TAXA_ABSENTISMO")
	public Float getTAXA_ABSENTISMO() {
		return TAXA_ABSENTISMO;
	}

	public void setSECTORES_ABSENTISMO(String sECTORES_ABSENTISMO) {
		SECTORES_ABSENTISMO = sECTORES_ABSENTISMO;
	}

	public void setTAXA_ABSENTISMO(Float tAXA_ABSENTISMO) {
		TAXA_ABSENTISMO = tAXA_ABSENTISMO;
	}

	@Column(name = "OBJETIVO_AUDITORIAS")
	public BigDecimal getOBJETIVO_AUDITORIAS() {
		return OBJETIVO_AUDITORIAS;
	}

	public void setOBJETIVO_AUDITORIAS(BigDecimal oBJETIVO_AUDITORIAS) {
		OBJETIVO_AUDITORIAS = oBJETIVO_AUDITORIAS;
	}

	@Column(name = "NUMERO_MANUTENCOES_INICIAR")
	public Integer getNUMERO_MANUTENCOES_INICIAR() {
		return NUMERO_MANUTENCOES_INICIAR;
	}

	public void setNUMERO_MANUTENCOES_INICIAR(Integer nUMERO_MANUTENCOES_INICIAR) {
		NUMERO_MANUTENCOES_INICIAR = nUMERO_MANUTENCOES_INICIAR;
	}

	@Column(name = "PRECO_HORA_MANUTENCAO")
	public BigDecimal getPRECO_HORA_MANUTENCAO() {
		return PRECO_HORA_MANUTENCAO;
	}

	public void setPRECO_HORA_MANUTENCAO(BigDecimal pRECO_HORA_MANUTENCAO) {
		PRECO_HORA_MANUTENCAO = pRECO_HORA_MANUTENCAO;
	}

}
