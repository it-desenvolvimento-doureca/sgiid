package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_DIC_PRODUTOS_RELACIONADOS")
public class PIN_DIC_PRODUTOS_RELACIONADOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("ID_PRODUTO")
	private Integer ID_PRODUTO;
	@JsonProperty("COD_REF")
	private String COD_REF;
	@JsonProperty("NOME_REF")
	private String NOME_REF;
	@JsonProperty("OBS")
	private String OBS;
	@JsonProperty("PERC_DILUICAO")
	private BigDecimal PERC_DILUICAO;
	@JsonProperty("ID_REF")
	private Integer ID_REF;
	
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getID_PRODUTO() {
		return ID_PRODUTO;
	}

	public void setID_PRODUTO(Integer iD_PRODUTO) {
		ID_PRODUTO = iD_PRODUTO;
	}

	public String getCOD_REF() {
		return COD_REF;
	}

	public void setCOD_REF(String cOD_REF) {
		COD_REF = cOD_REF;
	}

	public String getNOME_REF() {
		return NOME_REF;
	}

	public void setNOME_REF(String nOME_REF) {
		NOME_REF = nOME_REF;
	}

	public String getOBS() {
		return OBS;
	}

	public void setOBS(String oBS) {
		OBS = oBS;
	}

	public BigDecimal getPERC_DILUICAO() {
		return PERC_DILUICAO;
	}

	public void setPERC_DILUICAO(BigDecimal pERC_DILUICAO) {
		PERC_DILUICAO = pERC_DILUICAO;
	}

	public Integer getID_REF() {
		return ID_REF;
	}

	public void setID_REF(Integer iD_REF) {
		ID_REF = iD_REF;
	}

}
