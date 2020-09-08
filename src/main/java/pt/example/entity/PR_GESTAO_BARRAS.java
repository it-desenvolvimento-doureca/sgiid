package pt.example.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PR_GESTAO_BARRAS")
public class PR_GESTAO_BARRAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_GESTAO_BARRAS")
	private Integer ID_GESTAO_BARRAS;
	@Column(name = "REF_BASTIDOR")
	private String REF_BASTIDOR;
	@Column(name = "ID_LINHA")
	private Integer ID_LINHA;
	@Column(name = "PLANO_BASTIDOR")
	private String PLANO_BASTIDOR;
	@Column(name = "QUANT_TOTAL_BAST")
	private Integer QUANT_TOTAL_BAST;
	@Column(name = "QUANT_BAST_MONTADOS")
	private Integer QUANT_BAST_MONTADOS;
	@Column(name = "QUANT_BAST_DESMONTADOS")
	private Integer QUANT_BAST_DESMONTADOS;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "ID_ORIGEM")
	private Integer ID_ORIGEM;

	public Integer getID_ORIGEM() {
		return ID_ORIGEM;
	}

	public void setID_ORIGEM(Integer iD_ORIGEM) {
		ID_ORIGEM = iD_ORIGEM;
	}

	public Integer getID_GESTAO_BARRAS() {
		return ID_GESTAO_BARRAS;
	}

	public String getREF_BASTIDOR() {
		return REF_BASTIDOR;
	}

	public Integer getID_LINHA() {
		return ID_LINHA;
	}

	public String getPLANO_BASTIDOR() {
		return PLANO_BASTIDOR;
	}

	public Integer getQUANT_TOTAL_BAST() {
		return QUANT_TOTAL_BAST;
	}

	public Integer getQUANT_BAST_MONTADOS() {
		return QUANT_BAST_MONTADOS;
	}

	public Integer getQUANT_BAST_DESMONTADOS() {
		return QUANT_BAST_DESMONTADOS;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setID_GESTAO_BARRAS(Integer iD_GESTAO_BARRAS) {
		ID_GESTAO_BARRAS = iD_GESTAO_BARRAS;
	}

	public void setREF_BASTIDOR(String rEF_BASTIDOR) {
		REF_BASTIDOR = rEF_BASTIDOR;
	}

	public void setID_LINHA(Integer iD_LINHA) {
		ID_LINHA = iD_LINHA;
	}

	public void setPLANO_BASTIDOR(String pLANO_BASTIDOR) {
		PLANO_BASTIDOR = pLANO_BASTIDOR;
	}

	public void setQUANT_TOTAL_BAST(Integer qUANT_TOTAL_BAST) {
		QUANT_TOTAL_BAST = qUANT_TOTAL_BAST;
	}

	public void setQUANT_BAST_MONTADOS(Integer qUANT_BAST_MONTADOS) {
		QUANT_BAST_MONTADOS = qUANT_BAST_MONTADOS;
	}

	public void setQUANT_BAST_DESMONTADOS(Integer qUANT_BAST_DESMONTADOS) {
		QUANT_BAST_DESMONTADOS = qUANT_BAST_DESMONTADOS;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

}