package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AT_ACCOES")
public class AT_ACCOES {

	public Integer ID_ACCAO;
	public String DESCRICAO_ACCAO;
	public String RESPONSAVEL;
	public String DATA_IMPLEMENTACAO;
	public String RECURSOS;
	public Integer ID_OCORRENCIA;

	@Id
	@Column(name = "ID_ACCAO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_ACCAO() {
		return ID_ACCAO;
	}
	
	@Column(name = "ID_OCORRENCIA")
	public Integer getID_OCORRENCIA() {
		return ID_OCORRENCIA;
	}


	@Column(name = "DESCRICAO_ACCAO")
	public String getDESCRICAO_ACCAO() {
		return DESCRICAO_ACCAO;
	}

	@Column(name = "RESPONSAVEL")
	public String getRESPONSAVEL() {
		return RESPONSAVEL;
	}

	@Column(name = "DATA_IMPLEMENTACAO")
	public String getDATA_IMPLEMENTACAO() {
		return DATA_IMPLEMENTACAO;
	}

	@Column(name = "RECURSOS")
	public String getRECURSOS() {
		return RECURSOS;
	}

	public void setID_ACCAO(Integer iD_ACCAO) {
		ID_ACCAO = iD_ACCAO;
	}

	public void setDESCRICAO_ACCAO(String dESCRICAO_ACCAO) {
		DESCRICAO_ACCAO = dESCRICAO_ACCAO;
	}

	public void setRESPONSAVEL(String rESPONSAVEL) {
		RESPONSAVEL = rESPONSAVEL;
	}

	public void setDATA_IMPLEMENTACAO(String dATA_IMPLEMENTACAO) {
		DATA_IMPLEMENTACAO = dATA_IMPLEMENTACAO;
	}

	public void setRECURSOS(String rECURSOS) {
		RECURSOS = rECURSOS;
	}
	

	public void setID_OCORRENCIA(Integer iD_OCORRENCIA) {
		ID_OCORRENCIA = iD_OCORRENCIA;
	}


}
