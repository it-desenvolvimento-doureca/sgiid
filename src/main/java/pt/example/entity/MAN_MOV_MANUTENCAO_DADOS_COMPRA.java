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
@Table(name = "MAN_MOV_MANUTENCAO_DADOS_COMPRA")
public class MAN_MOV_MANUTENCAO_DADOS_COMPRA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_MANUTENCAO")
	private Integer ID_MANUTENCAO;
	@JsonProperty("FORNECEDOR")
	private String FORNECEDOR;
	@JsonProperty("ANEXO")
	private String ANEXO;
	@JsonProperty("N_DOCUMENTO")
	private String N_DOCUMENTO;

	public Integer getID() {
		return ID;
	}

	public Integer getID_MANUTENCAO() {
		return ID_MANUTENCAO;
	}

	public String getFORNECEDOR() {
		return FORNECEDOR;
	}

	public String getANEXO() {
		return ANEXO;
	}

	public String getN_DOCUMENTO() {
		return N_DOCUMENTO;
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

	public void setANEXO(String aNEXO) {
		ANEXO = aNEXO;
	}

	public void setN_DOCUMENTO(String n_DOCUMENTO) {
		N_DOCUMENTO = n_DOCUMENTO;
	}

}
