package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "RH_REGISTO_ACOES")
public class RH_REGISTO_ACOES {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_ACAO")
	private Integer ID_ACAO;
	@JsonProperty("ID_FUNCIONARIO")
	private String ID_FUNCIONARIO;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("CONTACTO")
	private String CONTACTO;
	@JsonProperty("GERAR_ALERTA")
	private Timestamp GERAR_ALERTA;
	@JsonProperty("ALERTA_GERADO")
	private Boolean ALERTA_GERADO;
	@JsonProperty("ORIGEM")
	private String ORIGEM;
	@JsonProperty("DESCRICAO")
	private String DESCRICAO;
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

	public Integer getID_ACAO() {
		return ID_ACAO;
	}

	public String getID_FUNCIONARIO() {
		return ID_FUNCIONARIO;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public String getCONTACTO() {
		return CONTACTO;
	}

	public Timestamp getGERAR_ALERTA() {
		return GERAR_ALERTA;
	}

	public Boolean getALERTA_GERADO() {
		return ALERTA_GERADO;
	}

	public String getORIGEM() {
		return ORIGEM;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
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

	public void setID_ACAO(Integer iD_ACAO) {
		ID_ACAO = iD_ACAO;
	}

	public void setID_FUNCIONARIO(String iD_FUNCIONARIO) {
		ID_FUNCIONARIO = iD_FUNCIONARIO;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setCONTACTO(String cONTACTO) {
		CONTACTO = cONTACTO;
	}

	public void setGERAR_ALERTA(Timestamp gERAR_ALERTA) {
		GERAR_ALERTA = gERAR_ALERTA;
	}

	public void setALERTA_GERADO(Boolean aLERTA_GERADO) {
		ALERTA_GERADO = aLERTA_GERADO;
	}

	public void setORIGEM(String oRIGEM) {
		ORIGEM = oRIGEM;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
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

}
