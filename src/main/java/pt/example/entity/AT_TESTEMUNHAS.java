package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AT_TESTEMUNHAS")
public class AT_TESTEMUNHAS {
	public Integer ID_TESTEMUNHA;
	public Integer ID_OCORRENCIA;
	public String NOME;
	public String NUMERO;

	@Id
	@Column(name = "ID_TESTEMUNHA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_TESTEMUNHA() {
		return ID_TESTEMUNHA;
	}

	@Column(name = "ID_OCORRENCIA")
	public Integer getID_OCORRENCIA() {
		return ID_OCORRENCIA;
	}

	@Column(name = "NOME")
	public String getNOME() {
		return NOME;
	}

	@Column(name = "NUMERO")
	public String getNUMERO() {
		return NUMERO;
	}

	public void setID_TESTEMUNHA(Integer iD_TESTEMUNHA) {
		ID_TESTEMUNHA = iD_TESTEMUNHA;
	}

	public void setID_OCORRENCIA(Integer iD_OCORRENCIA) {
		ID_OCORRENCIA = iD_OCORRENCIA;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public void setNUMERO(String nUMERO) {
		NUMERO = nUMERO;
	}

}
