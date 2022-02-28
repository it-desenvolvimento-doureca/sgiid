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
@Table(name = "MAN_MOV_PEDIDOS")
public class MAN_MOV_PEDIDOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty( "ID_PEDIDO")
	private Integer ID_PEDIDO;
	@JsonProperty( "ID_RESPONSAVEL")
	private Integer ID_RESPONSAVEL;
	@JsonProperty( "DATA_HORA_PEDIDO")
	private Timestamp DATA_HORA_PEDIDO;
	@JsonProperty( "EQUIPAMENTO")
	private Integer EQUIPAMENTO;
	@JsonProperty( "COMPONENTE")
	private Integer COMPONENTE;
	@JsonProperty( "LOCALIZACAO")
	private Integer LOCALIZACAO;
	@JsonProperty( "TIPO_LOCALIZACAO")
	private String TIPO_LOCALIZACAO;
	@JsonProperty( "DESCRICAO_PEDIDO")
	private String DESCRICAO_PEDIDO;
	@JsonProperty( "ESTADO")
	private String ESTADO;
	@JsonProperty( "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty( "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty( "UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty( "DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty( "ATIVO")
	private Boolean ATIVO;
	@JsonProperty( "TIPO_RESPONSAVEL")
	private String TIPO_RESPONSAVEL;
	@JsonProperty( "UTILIZADOR")
	private Integer UTILIZADOR;
	@JsonProperty( "ID_EQUIPA")
	private Integer ID_EQUIPA;

	public Integer getID_PEDIDO() {
		return ID_PEDIDO;
	}

	public Integer getID_RESPONSAVEL() {
		return ID_RESPONSAVEL;
	}

	public Timestamp getDATA_HORA_PEDIDO() {
		return DATA_HORA_PEDIDO;
	}

	public Integer getEQUIPAMENTO() {
		return EQUIPAMENTO;
	}

	public Integer getCOMPONENTE() {
		return COMPONENTE;
	}

	public Integer getLOCALIZACAO() {
		return LOCALIZACAO;
	}

	public String getDESCRICAO_PEDIDO() {
		return DESCRICAO_PEDIDO;
	}

	public String getESTADO() {
		return ESTADO;
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

	public Boolean getATIVO() {
		return ATIVO;
	}

	public void setID_PEDIDO(Integer iD_PEDIDO) {
		ID_PEDIDO = iD_PEDIDO;
	}

	public void setID_RESPONSAVEL(Integer iD_RESPONSAVEL) {
		ID_RESPONSAVEL = iD_RESPONSAVEL;
	}

	public void setDATA_HORA_PEDIDO(Timestamp dATA_HORA_PEDIDO) {
		DATA_HORA_PEDIDO = dATA_HORA_PEDIDO;
	}

	public void setEQUIPAMENTO(Integer eQUIPAMENTO) {
		EQUIPAMENTO = eQUIPAMENTO;
	}

	public void setCOMPONENTE(Integer cOMPONENTE) {
		COMPONENTE = cOMPONENTE;
	}

	public void setLOCALIZACAO(Integer lOCALIZACAO) {
		LOCALIZACAO = lOCALIZACAO;
	}

	public void setDESCRICAO_PEDIDO(String dESCRICAO_PEDIDO) {
		DESCRICAO_PEDIDO = dESCRICAO_PEDIDO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
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

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public String getTIPO_LOCALIZACAO() {
		return TIPO_LOCALIZACAO;
	}

	public void setTIPO_LOCALIZACAO(String tIPO_LOCALIZACAO) {
		TIPO_LOCALIZACAO = tIPO_LOCALIZACAO;
	}

	public String getTIPO_RESPONSAVEL() {
		return TIPO_RESPONSAVEL;
	}

	public Integer getUTILIZADOR() {
		return UTILIZADOR;
	}

	public Integer getID_EQUIPA() {
		return ID_EQUIPA;
	}

	public void setTIPO_RESPONSAVEL(String tIPO_RESPONSAVEL) {
		TIPO_RESPONSAVEL = tIPO_RESPONSAVEL;
	}

	public void setUTILIZADOR(Integer uTILIZADOR) {
		UTILIZADOR = uTILIZADOR;
	}

	public void setID_EQUIPA(Integer iD_EQUIPA) {
		ID_EQUIPA = iD_EQUIPA;
	}

}
