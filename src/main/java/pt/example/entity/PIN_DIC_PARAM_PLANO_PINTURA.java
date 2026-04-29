package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_DIC_PARAM_PLANO_PINTURA")
public class PIN_DIC_PARAM_PLANO_PINTURA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;

	@JsonProperty("RACKS_LIMPEZA")
	private Integer RACKS_LIMPEZA;

	@JsonProperty("TEMPO_RACKS_LIMPEZA")
	private Integer TEMPO_RACKS_LIMPEZA;

	@JsonProperty("TEMPO_FORNO")
	private Integer TEMPO_FORNO;

	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;

	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;

	@JsonProperty("UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;

	@JsonProperty("DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;

	@JsonProperty("ATIVO")
	private Boolean ATIVO;

	public Integer getID() { return ID; }
	public void setID(Integer iD) { ID = iD; }

	public Integer getRACKS_LIMPEZA() { return RACKS_LIMPEZA; }
	public void setRACKS_LIMPEZA(Integer rACKS_LIMPEZA) { RACKS_LIMPEZA = rACKS_LIMPEZA; }

	public Integer getTEMPO_RACKS_LIMPEZA() { return TEMPO_RACKS_LIMPEZA; }
	public void setTEMPO_RACKS_LIMPEZA(Integer tEMPO_RACKS_LIMPEZA) { TEMPO_RACKS_LIMPEZA = tEMPO_RACKS_LIMPEZA; }

	public Integer getTEMPO_FORNO() { return TEMPO_FORNO; }
	public void setTEMPO_FORNO(Integer tEMPO_FORNO) { TEMPO_FORNO = tEMPO_FORNO; }

	public Integer getUTZ_CRIA() { return UTZ_CRIA; }
	public void setUTZ_CRIA(Integer uTZ_CRIA) { UTZ_CRIA = uTZ_CRIA; }

	public Timestamp getDATA_CRIA() { return DATA_CRIA; }
	public void setDATA_CRIA(Timestamp dATA_CRIA) { DATA_CRIA = dATA_CRIA; }

	public Integer getUTZ_ULT_MODIF() { return UTZ_ULT_MODIF; }
	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) { UTZ_ULT_MODIF = uTZ_ULT_MODIF; }

	public Timestamp getDATA_ULT_MODIF() { return DATA_ULT_MODIF; }
	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) { DATA_ULT_MODIF = dATA_ULT_MODIF; }

	public Boolean getATIVO() { return ATIVO; }
	public void setATIVO(Boolean aTIVO) { ATIVO = aTIVO; }
}
