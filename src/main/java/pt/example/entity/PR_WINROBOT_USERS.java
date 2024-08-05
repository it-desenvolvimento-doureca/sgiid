package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PR_WINROBOT_USERS")
public class PR_WINROBOT_USERS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
    private Integer ID;
	@JsonProperty("ID_CAB")
    private Integer ID_CAB;
	@JsonProperty("ID_UTZ")
    private String ID_UTZ;
	@JsonProperty("NOME_UTZ")
    private String NOME_UTZ;
	@JsonProperty("DATA_HORA_INICIO")
    private Timestamp DATA_HORA_INICIO;
	@JsonProperty("DATA_HORA_FIM")
    private Timestamp DATA_HORA_FIM;
	@JsonProperty("UTZ_CRIA")
    private String UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
    private Timestamp DATA_CRIA;
	@JsonProperty("DATA_MODIF")
    private Timestamp DATA_MODIF;
	@JsonProperty("ESTADO")
    private String ESTADO;
	@JsonProperty("UTZ_MODIF")
    private String UTZ_MODIF;
	@JsonProperty("INDEX_ORIGEM")
    private Integer INDEX_ORIGEM;
	@JsonProperty("DATA_INICIO_PREP")
    private Timestamp DATA_INICIO_PREP;
	@JsonProperty("DATA_FIM_PREP")
    private Timestamp DATA_FIM_PREP;
	@JsonProperty("DATA_INICIO_EXEC")
    private Timestamp DATA_INICIO_EXEC;
	@JsonProperty("DATA_FIM_EXEC")
    private Timestamp DATA_FIM_EXEC;
	@JsonProperty("TEMP_EXEC")
    private String TEMP_EXEC;
	@JsonProperty("TEMP_TOTAL")
    private String TEMP_TOTAL;
	@JsonProperty("TEMP_PAUSA")
    private String TEMP_PAUSA;
	@JsonProperty("TEMP_PREP")
    private String TEMP_PREP;
	public Integer getID() {
		return ID;
	}
	public Integer getID_CAB() {
		return ID_CAB;
	}
	public String getID_UTZ() {
		return ID_UTZ;
	}
	public String getNOME_UTZ() {
		return NOME_UTZ;
	}
	public Timestamp getDATA_HORA_INICIO() {
		return DATA_HORA_INICIO;
	}
	public Timestamp getDATA_HORA_FIM() {
		return DATA_HORA_FIM;
	}
	public String getUTZ_CRIA() {
		return UTZ_CRIA;
	}
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}
	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}
	public String getESTADO() {
		return ESTADO;
	}
	public String getUTZ_MODIF() {
		return UTZ_MODIF;
	}
	public Integer getINDEX_ORIGEM() {
		return INDEX_ORIGEM;
	}
	public Timestamp getDATA_INICIO_PREP() {
		return DATA_INICIO_PREP;
	}
	public Timestamp getDATA_FIM_PREP() {
		return DATA_FIM_PREP;
	}
	public Timestamp getDATA_INICIO_EXEC() {
		return DATA_INICIO_EXEC;
	}
	public Timestamp getDATA_FIM_EXEC() {
		return DATA_FIM_EXEC;
	}
	public String getTEMP_EXEC() {
		return TEMP_EXEC;
	}
	public String getTEMP_TOTAL() {
		return TEMP_TOTAL;
	}
	public String getTEMP_PAUSA() {
		return TEMP_PAUSA;
	}
	public String getTEMP_PREP() {
		return TEMP_PREP;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public void setID_CAB(Integer iD_CAB) {
		ID_CAB = iD_CAB;
	}
	public void setID_UTZ(String iD_UTZ) {
		ID_UTZ = iD_UTZ;
	}
	public void setNOME_UTZ(String nOME_UTZ) {
		NOME_UTZ = nOME_UTZ;
	}
	public void setDATA_HORA_INICIO(Timestamp dATA_HORA_INICIO) {
		DATA_HORA_INICIO = dATA_HORA_INICIO;
	}
	public void setDATA_HORA_FIM(Timestamp dATA_HORA_FIM) {
		DATA_HORA_FIM = dATA_HORA_FIM;
	}
	public void setUTZ_CRIA(String uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}
	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}
	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}
	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}
	public void setUTZ_MODIF(String uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}
	public void setINDEX_ORIGEM(Integer iNDEX_ORIGEM) {
		INDEX_ORIGEM = iNDEX_ORIGEM;
	}
	public void setDATA_INICIO_PREP(Timestamp dATA_INICIO_PREP) {
		DATA_INICIO_PREP = dATA_INICIO_PREP;
	}
	public void setDATA_FIM_PREP(Timestamp dATA_FIM_PREP) {
		DATA_FIM_PREP = dATA_FIM_PREP;
	}
	public void setDATA_INICIO_EXEC(Timestamp dATA_INICIO_EXEC) {
		DATA_INICIO_EXEC = dATA_INICIO_EXEC;
	}
	public void setDATA_FIM_EXEC(Timestamp dATA_FIM_EXEC) {
		DATA_FIM_EXEC = dATA_FIM_EXEC;
	}
	public void setTEMP_EXEC(String tEMP_EXEC) {
		TEMP_EXEC = tEMP_EXEC;
	}
	public void setTEMP_TOTAL(String tEMP_TOTAL) {
		TEMP_TOTAL = tEMP_TOTAL;
	}
	public void setTEMP_PAUSA(String tEMP_PAUSA) {
		TEMP_PAUSA = tEMP_PAUSA;
	}
	public void setTEMP_PREP(String tEMP_PREP) {
		TEMP_PREP = tEMP_PREP;
	}
	
	
	

}