package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_DIC_TIPO_MANUTENCAO")
public class AB_DIC_TIPO_MANUTENCAO {

	private Integer ID_TIPO_MANUTENCAO;
	private String NOME_TIPO_MANUTENCAO;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;

	@Column(name = "DATA_ANULACAO")
	public Timestamp getDATA_ANULACAO() {
		return DATA_ANULACAO;
	}

	@Column(name = "UTZ_ANULACAO")
	public Integer getUTZ_ANULACAO() {
		return UTZ_ANULACAO;
	}

	@Column(name = "INATIVO")
	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	@Id
	@Column(name = "ID_TIPO_MANUTENCAO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_TIPO_MANUTENCAO() {
		return ID_TIPO_MANUTENCAO;
	}

	@Column(name = "NOME_TIPO_MANUTENCAO")
	public String getNOME_TIPO_MANUTENCAO() {
		return NOME_TIPO_MANUTENCAO;
	}

	public void setID_TIPO_MANUTENCAO(Integer iD_TIPO_MANUTENCAO) {
		ID_TIPO_MANUTENCAO = iD_TIPO_MANUTENCAO;
	}

	public void setNOME_TIPO_MANUTENCAO(String nOME_TIPO_MANUTENCAO) {
		NOME_TIPO_MANUTENCAO = nOME_TIPO_MANUTENCAO;
	}

}
