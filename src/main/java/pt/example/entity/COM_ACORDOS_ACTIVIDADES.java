package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "COM_ACORDOS_ACTIVIDADES")
public class COM_ACORDOS_ACTIVIDADES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_ACORDO")
	private Integer ID_ACORDO;
	@JsonProperty("DATA_ATIVIDADE")
	private Timestamp DATA_ATIVIDADE;
	@JsonProperty("UTILIZADOR")
	private Integer UTILIZADOR;
	@JsonProperty("DESCRICAO")
	private String DESCRICAO;
	@JsonProperty("OBSERVACAO")
	private String OBSERVACAO;
	@JsonProperty("PRECO")
	private BigDecimal PRECO;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty("TAMANHO_FICHEIRO")
	private double TAMANHO_FICHEIRO;
	@JsonProperty("NOME_FICHEIRO")
	private String NOME_FICHEIRO;
	@JsonProperty("DATATYPE_FICHEIRO")
	private String DATATYPE_FICHEIRO;
	@JsonProperty("FICHEIRO")
	private String FICHEIRO;
	@JsonProperty("FICHEIRO_2")
	private String FICHEIRO_2;
	@JsonProperty("TYPE_FICHEIRO")
	private String TYPE_FICHEIRO;
	@JsonProperty("GERAR_ALERTA")
	private Timestamp GERAR_ALERTA;
	@JsonProperty("ALERTA_GERADO")
	private Boolean ALERTA_GERADO;
	@JsonProperty("VERSAO")
	private Integer VERSAO;
	
	public Integer getID() {
		return ID;
	}

	public Integer getID_ACORDO() {
		return ID_ACORDO;
	}

	public Timestamp getDATA_ATIVIDADE() {
		return DATA_ATIVIDADE;
	}

	public Integer getUTILIZADOR() {
		return UTILIZADOR;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public String getOBSERVACAO() {
		return OBSERVACAO;
	}

	public BigDecimal getPRECO() {
		return PRECO;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public double getTAMANHO_FICHEIRO() {
		return TAMANHO_FICHEIRO;
	}

	public String getNOME_FICHEIRO() {
		return NOME_FICHEIRO;
	}

	public String getDATATYPE_FICHEIRO() {
		return DATATYPE_FICHEIRO;
	}

	public String getFICHEIRO() {
		return FICHEIRO;
	}

	public String getFICHEIRO_2() {
		return FICHEIRO_2;
	}

	public String getTYPE_FICHEIRO() {
		return TYPE_FICHEIRO;
	}

	public Timestamp getGERAR_ALERTA() {
		return GERAR_ALERTA;
	}

	public Boolean getALERTA_GERADO() {
		return ALERTA_GERADO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_ACORDO(Integer iD_ACORDO) {
		ID_ACORDO = iD_ACORDO;
	}

	public void setDATA_ATIVIDADE(Timestamp dATA_ATIVIDADE) {
		DATA_ATIVIDADE = dATA_ATIVIDADE;
	}

	public void setUTILIZADOR(Integer uTILIZADOR) {
		UTILIZADOR = uTILIZADOR;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setOBSERVACAO(String oBSERVACAO) {
		OBSERVACAO = oBSERVACAO;
	}

	public void setPRECO(BigDecimal pRECO) {
		PRECO = pRECO;
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

	public void setTAMANHO_FICHEIRO(double tAMANHO_FICHEIRO) {
		TAMANHO_FICHEIRO = tAMANHO_FICHEIRO;
	}

	public void setNOME_FICHEIRO(String nOME_FICHEIRO) {
		NOME_FICHEIRO = nOME_FICHEIRO;
	}

	public void setDATATYPE_FICHEIRO(String dATATYPE_FICHEIRO) {
		DATATYPE_FICHEIRO = dATATYPE_FICHEIRO;
	}

	public void setFICHEIRO(String fICHEIRO) {
		FICHEIRO = fICHEIRO;
	}

	public void setFICHEIRO_2(String fICHEIRO_2) {
		FICHEIRO_2 = fICHEIRO_2;
	}

	public void setTYPE_FICHEIRO(String tYPE_FICHEIRO) {
		TYPE_FICHEIRO = tYPE_FICHEIRO;
	}

	public void setGERAR_ALERTA(Timestamp gERAR_ALERTA) {
		GERAR_ALERTA = gERAR_ALERTA;
	}

	public void setALERTA_GERADO(Boolean aLERTA_GERADO) {
		ALERTA_GERADO = aLERTA_GERADO;
	}

	public Integer getVERSAO() {
		return VERSAO;
	}

	public void setVERSAO(Integer vERSAO) {
		VERSAO = vERSAO;
	}

}
