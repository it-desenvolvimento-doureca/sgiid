package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_ARMAZEM")
public class GER_ARMAZEM {

	private Integer ID_ARMAZEM;
	private String COD_ARMAZEM;
	private String NOME_ARMAZEM;

	@Id
	@Column(name = "ID_ARMAZEM")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_ARMAZEM() {
		return ID_ARMAZEM;
	}

	@Column(name = "COD_ARMAZEM")
	public String getCOD_ARMAZEM() {
		return COD_ARMAZEM;
	}

	@Column(name = "NOME_ARMAZEM")
	public String getNOME_ARMAZEM() {
		return NOME_ARMAZEM;
	}

	public void setID_ARMAZEM(Integer iD_ARMAZEM) {
		ID_ARMAZEM = iD_ARMAZEM;
	}

	public void setCOD_ARMAZEM(String cOD_ARMAZEM) {
		COD_ARMAZEM = cOD_ARMAZEM;
	}

	public void setNOME_ARMAZEM(String nOME_ARMAZEM) {
		NOME_ARMAZEM = nOME_ARMAZEM;
	}
}
