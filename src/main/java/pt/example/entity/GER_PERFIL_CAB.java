package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_PERFIL_CAB")
public class GER_PERFIL_CAB {

	private Integer ID_PERFIL_CAB;
	private Integer ID_MODULO;
	private String NOME_PERFIL;
	private Boolean INATIVO;

	@Id
	@Column(name = "ID_PERFIL_CAB")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_PERFIL_CAB() {
		return ID_PERFIL_CAB;
	}

	@Column(name = "NOME_PERFIL")
	public String getNOME_PERFIL() {
		return NOME_PERFIL;
	}

	@Column(name = "ID_MODULO")
	public Integer getID_MODULO() {
		return ID_MODULO;
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

	public void setID_PERFIL_CAB(Integer iD_PERFIL_CAB) {
		ID_PERFIL_CAB = iD_PERFIL_CAB;
	}

	public void setNOME_PERFIL(String nOME_PERFIL) {
		NOME_PERFIL = nOME_PERFIL;
	}

}
