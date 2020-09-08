package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_DIC_OEM")
public class GER_DIC_OEM {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_OEM")
	private Integer ID_OEM;
	@Column(name = "NOME")
	private String NOME;

	public Integer getID_OEM() {
		return ID_OEM;
	}

	public String getNOME() {
		return NOME;
	}

	public void setID_OEM(Integer iD_OEM) {
		ID_OEM = iD_OEM;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

}
