package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AT_ENTREVISTAS")
public class AT_ENTREVISTAS {

	public Integer ID_ENTREVISTA;
	public Integer ID_OCORRENCIA;
	public String NOME;
	public String FUNCAO;

	@Id
	@Column(name = "ID_ENTREVISTA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_ENTREVISTA() {
		return ID_ENTREVISTA;
	}

	@Column(name = "ID_OCORRENCIA")
	public Integer getID_OCORRENCIA() {
		return ID_OCORRENCIA;
	}

	@Column(name = "NOME")
	public String getNOME() {
		return NOME;
	}

	@Column(name = "FUNCAO")
	public String getFUNCAO() {
		return FUNCAO;
	}

	public void setID_ENTREVISTA(Integer iD_ENTREVISTA) {
		ID_ENTREVISTA = iD_ENTREVISTA;
	}

	public void setID_OCORRENCIA(Integer iD_OCORRENCIA) {
		ID_OCORRENCIA = iD_OCORRENCIA;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public void setFUNCAO(String fUNCAO) {
		FUNCAO = fUNCAO;
	}

}
