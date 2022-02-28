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

}
