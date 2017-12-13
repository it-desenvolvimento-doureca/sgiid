package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_MODULO")
public class GER_MODULO {

	private Integer ID_MODULO;
	private String NOME_MODULO;
	private Boolean INATIVO;

	@Id
	@Column(name = "ID_MODULO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_MODULO() {
		return ID_MODULO;
	}

	@Column(name = "NOME_MODULO")
	public String getNOME_MODULO() {
		return NOME_MODULO;
	}

	@Column(name = "INATIVO")
	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public void setID_MODULO(Integer iD_MODULO) {
		ID_MODULO = iD_MODULO;
	}

	public void setNOME_MODULO(String nOME_MODULO) {
		NOME_MODULO = nOME_MODULO;
	}

}
