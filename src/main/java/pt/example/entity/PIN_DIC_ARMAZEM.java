package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PIN_DIC_ARMAZEM")
public class PIN_DIC_ARMAZEM {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_ARMAZEM")
    private Integer ID_ARMAZEM;
	@JsonProperty("COD_ARMAZEM")
    private String COD_ARMAZEM;
	@JsonProperty("NOME_ARMAZEM")
    private String NOME_ARMAZEM;
	public Integer getID_ARMAZEM() {
		return ID_ARMAZEM;
	}
	public void setID_ARMAZEM(Integer iD_ARMAZEM) {
		ID_ARMAZEM = iD_ARMAZEM;
	}
	public String getCOD_ARMAZEM() {
		return COD_ARMAZEM;
	}
	public void setCOD_ARMAZEM(String cOD_ARMAZEM) {
		COD_ARMAZEM = cOD_ARMAZEM;
	}
	public String getNOME_ARMAZEM() {
		return NOME_ARMAZEM;
	}
	public void setNOME_ARMAZEM(String nOME_ARMAZEM) {
		NOME_ARMAZEM = nOME_ARMAZEM;
	}
	
	
}
