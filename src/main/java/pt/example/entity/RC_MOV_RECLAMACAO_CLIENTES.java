package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RC_MOV_RECLAMACAO_CLIENTES")
public class RC_MOV_RECLAMACAO_CLIENTES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "NUMERO_CLIENTE")
	private Integer NUMERO_CLIENTE;
	@Column(name = "NOME_CLIENTE")
	private String NOME_CLIENTE;
	@Column(name = "ID_RECLAMACAO")
	private Integer ID_RECLAMACAO;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@Column(name = "DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;

	public Integer getID() {
		return ID;
	}

	public Integer getNUMERO_CLIENTE() {
		return NUMERO_CLIENTE;
	}

	public String getNOME_CLIENTE() {
		return NOME_CLIENTE;
	}

	public Integer getID_RECLAMACAO() {
		return ID_RECLAMACAO;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setNUMERO_CLIENTE(Integer nUMERO_CLIENTE) {
		NUMERO_CLIENTE = nUMERO_CLIENTE;
	}

	public void setNOME_CLIENTE(String nOME_CLIENTE) {
		NOME_CLIENTE = nOME_CLIENTE;
	}

	public void setID_RECLAMACAO(Integer iD_RECLAMACAO) {
		ID_RECLAMACAO = iD_RECLAMACAO;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}
}