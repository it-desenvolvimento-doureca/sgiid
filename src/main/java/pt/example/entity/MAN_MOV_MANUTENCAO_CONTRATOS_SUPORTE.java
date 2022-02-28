package pt.example.entity;

import java.sql.Date;
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
@Table(name = "MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE")
public class MAN_MOV_MANUTENCAO_CONTRATOS_SUPORTE {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty( "ID")
	private Integer ID;
	@JsonProperty( "ID_MANUTENCAO")
	private Integer ID_MANUTENCAO;
	@JsonProperty( "FORNECEDOR")
	private String FORNECEDOR;
	@JsonProperty( "CONTRATO_SUPORTE")
	private Boolean CONTRATO_SUPORTE;
	@JsonProperty( "PERIOCIDADE")
	private Integer PERIOCIDADE;
	@JsonProperty( "DATA_INICIO")
	private Date DATA_INICIO;
	@JsonProperty( "DATA_FIM")
	private Date DATA_FIM;
	@JsonProperty( "ANEXO")
	private String ANEXO;

	public Integer getID() {
		return ID;
	}

	public Integer getID_MANUTENCAO() {
		return ID_MANUTENCAO;
	}

	public String getFORNECEDOR() {
		return FORNECEDOR;
	}

	public Boolean getCONTRATO_SUPORTE() {
		return CONTRATO_SUPORTE;
	}

	public Integer getPERIOCIDADE() {
		return PERIOCIDADE;
	}

	public Date getDATA_INICIO() {
		return DATA_INICIO;
	}

	public Date getDATA_FIM() {
		return DATA_FIM;
	}

	public String getANEXO() {
		return ANEXO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_MANUTENCAO(Integer iD_MANUTENCAO) {
		ID_MANUTENCAO = iD_MANUTENCAO;
	}

	public void setFORNECEDOR(String fORNECEDOR) {
		FORNECEDOR = fORNECEDOR;
	}

	public void setCONTRATO_SUPORTE(Boolean cONTRATO_SUPORTE) {
		CONTRATO_SUPORTE = cONTRATO_SUPORTE;
	}

	public void setPERIOCIDADE(Integer pERIOCIDADE) {
		PERIOCIDADE = pERIOCIDADE;
	}

	public void setDATA_INICIO(Date dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	public void setDATA_FIM(Date dATA_FIM) {
		DATA_FIM = dATA_FIM;
	}

	public void setANEXO(String aNEXO) {
		ANEXO = aNEXO;
	}

}
