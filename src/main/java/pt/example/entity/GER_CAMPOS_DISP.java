package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_CAMPOS_DISP")
public class GER_CAMPOS_DISP {

	private Integer ID;
	private Integer ID_EVENTO_CONF;
	private String DESCRICAO_CAMPO;
	private String NOME_CAMPO;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	@Column(name = "ID_EVENTO_CONF")
	public Integer getID_EVENTO_CONF() {
		return ID_EVENTO_CONF;
	}

	@Column(name = "DESCRICAO_CAMPO")
	public String getDESCRICAO_CAMPO() {
		return DESCRICAO_CAMPO;
	}

	@Column(name = "NOME_CAMPO")
	public String getNOME_CAMPO() {
		return NOME_CAMPO;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_EVENTO_CONF(Integer iD_EVENTO_CONF) {
		ID_EVENTO_CONF = iD_EVENTO_CONF;
	}

	public void setDESCRICAO_CAMPO(String dESCRICAO_CAMPO) {
		DESCRICAO_CAMPO = dESCRICAO_CAMPO;
	}

	public void setNOME_CAMPO(String nOME_CAMPO) {
		NOME_CAMPO = nOME_CAMPO;
	}

}
