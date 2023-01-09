package pt.example.entity;

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
@Table(name = "MAN_MOV_MANUTENCAO_COMPONENTES")
public class MAN_MOV_MANUTENCAO_COMPONENTES {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty( "ID")
	private Integer ID;
	@JsonProperty( "ID_MANUTENCAO")
	private Integer ID_MANUTENCAO;
	@JsonProperty( "REFERENCIA")
	private String REFERENCIA;
	@JsonProperty( "DESC_REFERENCIA")
	private String DESC_REFERENCIA;
	@JsonProperty( "QUANTIDADE")
	private Integer QUANTIDADE;
	@JsonProperty("ANEXO")
	private String ANEXO;
	@JsonProperty( "LOCALIZACAO")
	private Integer LOCALIZACAO;
	@JsonProperty("TIPO_LOCALIZACAO")
	private String TIPO_LOCALIZACAO;
	@JsonProperty("DATA_CRIA")
    private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
    private Integer UTZ_CRIA;
	@JsonProperty("ESTADO")
    private String ESTADO;
	@JsonProperty("DATA_VALIDADO")
    private Timestamp DATA_VALIDADO;
	@JsonProperty("UTZ_VALIDA")
    private Integer UTZ_VALIDA;
	
	public Integer getID() {
		return ID;
	}

	public Integer getID_MANUTENCAO() {
		return ID_MANUTENCAO;
	}

	public String getREFERENCIA() {
		return REFERENCIA;
	}

	public String getDESC_REFERENCIA() {
		return DESC_REFERENCIA;
	}

	public Integer getQUANTIDADE() {
		return QUANTIDADE;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_MANUTENCAO(Integer iD_MANUTENCAO) {
		ID_MANUTENCAO = iD_MANUTENCAO;
	}

	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	public void setDESC_REFERENCIA(String dESC_REFERENCIA) {
		DESC_REFERENCIA = dESC_REFERENCIA;
	}

	public void setQUANTIDADE(Integer qUANTIDADE) {
		QUANTIDADE = qUANTIDADE;
	}

	public String getANEXO() {
		return ANEXO;
	}

	public void setANEXO(String aNEXO) {
		ANEXO = aNEXO;
	}

	public Integer getLOCALIZACAO() {
		return LOCALIZACAO;
	}

	public String getTIPO_LOCALIZACAO() {
		return TIPO_LOCALIZACAO;
	}

	public void setLOCALIZACAO(Integer lOCALIZACAO) {
		LOCALIZACAO = lOCALIZACAO;
	}

	public void setTIPO_LOCALIZACAO(String tIPO_LOCALIZACAO) {
		TIPO_LOCALIZACAO = tIPO_LOCALIZACAO;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public Timestamp getDATA_VALIDADO() {
		return DATA_VALIDADO;
	}

	public Integer getUTZ_VALIDA() {
		return UTZ_VALIDA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setDATA_VALIDADO(Timestamp dATA_VALIDADO) {
		DATA_VALIDADO = dATA_VALIDADO;
	}

	public void setUTZ_VALIDA(Integer uTZ_VALIDA) {
		UTZ_VALIDA = uTZ_VALIDA;
	}

}
