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
@Table(name = "MAN_MOV_MANUTENCAO_CAB")
public class MAN_MOV_MANUTENCAO_CAB {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_MANUTENCAO_CAB") 
	private Integer ID_MANUTENCAO_CAB;
	@JsonProperty( "NUMERO_PEDIDO")
	private String NUMERO_PEDIDO;
	@JsonProperty( "RESPONSAVEL_PEDIDO")
	private Integer RESPONSAVEL_PEDIDO;
	@JsonProperty( "DATA_HORA_PEDIDO")
	private Timestamp DATA_HORA_PEDIDO;
	@JsonProperty( "LOCALIZACAO")
	private Integer LOCALIZACAO;
	@JsonProperty( "EQUIPAMENTO")
	private Integer EQUIPAMENTO;
	@JsonProperty( "COMPONENTE")
	private Integer COMPONENTE;
	@JsonProperty( "DESCRICAO_PEDIDO")
	private String DESCRICAO_PEDIDO;
	@JsonProperty( "OBSERVACOES")
	private String OBSERVACOES;
	@JsonProperty( "DATA_INICIO")
	private Timestamp DATA_INICIO;
	@JsonProperty( "DATA_FIM")
	private Timestamp DATA_FIM;
	@JsonProperty( "ESTADO")
	private String ESTADO;
	@JsonProperty("TIPO_MANUTENCAO")
	private String TIPO_MANUTENCAO;
	@JsonProperty( "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty( "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty( "UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty( "DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty("TIPO_LOCALIZACAO")
	private String TIPO_LOCALIZACAO;
	
	public Integer getID_MANUTENCAO_CAB() {
		return ID_MANUTENCAO_CAB;
	}

	public String getNUMERO_PEDIDO() {
		return NUMERO_PEDIDO;
	}

	public Integer getRESPONSAVEL_PEDIDO() {
		return RESPONSAVEL_PEDIDO;
	}

	public Timestamp getDATA_HORA_PEDIDO() {
		return DATA_HORA_PEDIDO;
	}

	public Integer getLOCALIZACAO() {
		return LOCALIZACAO;
	}

	public Integer getEQUIPAMENTO() {
		return EQUIPAMENTO;
	}

	public Integer getCOMPONENTE() {
		return COMPONENTE;
	}

	public String getDESCRICAO_PEDIDO() {
		return DESCRICAO_PEDIDO;
	}

	public String getOBSERVACOES() {
		return OBSERVACOES;
	}

	public Timestamp getDATA_INICIO() {
		return DATA_INICIO;
	}

	public Timestamp getDATA_FIM() {
		return DATA_FIM;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public String getTIPO_MANUTENCAO() {
		return TIPO_MANUTENCAO;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public void setID_MANUTENCAO_CAB(Integer iD_MANUTENCAO_CAB) {
		ID_MANUTENCAO_CAB = iD_MANUTENCAO_CAB;
	}

	public void setNUMERO_PEDIDO(String nUMERO_PEDIDO) {
		NUMERO_PEDIDO = nUMERO_PEDIDO;
	}

	public void setRESPONSAVEL_PEDIDO(Integer rESPONSAVEL_PEDIDO) {
		RESPONSAVEL_PEDIDO = rESPONSAVEL_PEDIDO;
	}

	public void setDATA_HORA_PEDIDO(Timestamp dATA_HORA_PEDIDO) {
		DATA_HORA_PEDIDO = dATA_HORA_PEDIDO;
	}

	public void setLOCALIZACAO(Integer lOCALIZACAO) {
		LOCALIZACAO = lOCALIZACAO;
	}

	public void setEQUIPAMENTO(Integer eQUIPAMENTO) {
		EQUIPAMENTO = eQUIPAMENTO;
	}

	public void setCOMPONENTE(Integer cOMPONENTE) {
		COMPONENTE = cOMPONENTE;
	}

	public void setDESCRICAO_PEDIDO(String dESCRICAO_PEDIDO) {
		DESCRICAO_PEDIDO = dESCRICAO_PEDIDO;
	}

	public void setOBSERVACOES(String oBSERVACOES) {
		OBSERVACOES = oBSERVACOES;
	}

	public void setDATA_INICIO(Timestamp dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	public void setDATA_FIM(Timestamp dATA_FIM) {
		DATA_FIM = dATA_FIM;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setTIPO_MANUTENCAO(String tIPO_MANUTENCAO) {
		TIPO_MANUTENCAO = tIPO_MANUTENCAO;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public String getTIPO_LOCALIZACAO() {
		return TIPO_LOCALIZACAO;
	}

	public void setTIPO_LOCALIZACAO(String tIPO_LOCALIZACAO) {
		TIPO_LOCALIZACAO = tIPO_LOCALIZACAO;
	}

}
