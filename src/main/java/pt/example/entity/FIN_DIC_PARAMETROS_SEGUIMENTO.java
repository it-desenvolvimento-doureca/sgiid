package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FIN_DIC_PARAMETROS_SEGUIMENTO")
public class FIN_DIC_PARAMETROS_SEGUIMENTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ANALISE")
	private Integer ID_ANALISE;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "TIPOS_DOCUMENTO")
	private String TIPOS_DOCUMENTO;
	@Column(name = "TIPO_ARTIGO")
	private String TIPO_ARTIGO;

	public Integer getID_ANALISE() {
		return ID_ANALISE;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public String getTIPOS_DOCUMENTO() {
		return TIPOS_DOCUMENTO;
	}

	public String getTIPO_ARTIGO() {
		return TIPO_ARTIGO;
	}

	public void setID_ANALISE(Integer iD_ANALISE) {
		ID_ANALISE = iD_ANALISE;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setTIPOS_DOCUMENTO(String tIPOS_DOCUMENTO) {
		TIPOS_DOCUMENTO = tIPOS_DOCUMENTO;
	}

	public void setTIPO_ARTIGO(String tIPO_ARTIGO) {
		TIPO_ARTIGO = tIPO_ARTIGO;
	}

}
