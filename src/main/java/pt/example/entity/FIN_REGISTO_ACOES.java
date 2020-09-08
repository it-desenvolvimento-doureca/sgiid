package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FIN_REGISTO_ACOES")
public class FIN_REGISTO_ACOES {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ACAO")
	private Integer ID_ACAO;
	@Column(name = "ID_CLIENTE")
	private String ID_CLIENTE;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "CONTACTO")
	private String CONTACTO;
	@Column(name = "GERAR_ALERTA")
	private Timestamp GERAR_ALERTA;
	@Column(name = "ALERTA_GERADO")
	private Boolean ALERTA_GERADO;
	@Column(name = "ORIGEM")
	private String ORIGEM;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "TAMANHO_FICHEIRO")
	private double TAMANHO_FICHEIRO;
	@Column(name = "NOME_FICHEIRO")
	private String NOME_FICHEIRO;
	@Column(name = "DATATYPE_FICHEIRO")
	private String DATATYPE_FICHEIRO;
	@Column(name = "TYPE_FICHEIRO")
	private String TYPE_FICHEIRO;
	@Column(name = "FICHEIRO")
	private String FICHEIRO;
	@Column(name = "FICHEIRO_2")
	private String FICHEIRO_2;

	public Integer getID_ACAO() {
		return ID_ACAO;
	}

	public String getID_CLIENTE() {
		return ID_CLIENTE;
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

	public void setID_ACAO(Integer iD_ACAO) {
		ID_ACAO = iD_ACAO;
	}

	public void setID_CLIENTE(String iD_CLIENTE) {
		ID_CLIENTE = iD_CLIENTE;
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

	public String getTYPE_FICHEIRO() {
		return TYPE_FICHEIRO;
	}

	public void setTYPE_FICHEIRO(String tYPE_FICHEIRO) {
		TYPE_FICHEIRO = tYPE_FICHEIRO;
	}

}
