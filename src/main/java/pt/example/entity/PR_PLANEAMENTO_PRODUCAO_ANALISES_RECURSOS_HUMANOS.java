package pt.example.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS")
public class PR_PLANEAMENTO_PRODUCAO_ANALISES_RECURSOS_HUMANOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "ID_PLANEAMENTO_PRODUCAO_ANALISES")
	private Integer ID_PLANEAMENTO_PRODUCAO_ANALISES;
	@Column(name = "ID_SECTOR_AGREGADOR")
	private Integer ID_SECTOR_AGREGADOR;
	@Column(name = "NOME")
	private String NOME;
	@Column(name = "COD_SECTOR")
	private Integer COD_SECTOR;
	@Column(name = "DES_SECTOR")
	private String DES_SECTOR;
	@Column(name = "LINHA")
	private Integer LINHA;
	@Column(name = "ANO")
	private Integer ANO;
	@Column(name = "SEMANA")
	private Integer SEMANA;
	@Column(name = "OPERARIOS_DISPONIVEIS")
	private Integer OPERARIOS_DISPONIVEIS;
	@Column(name = "OPERARIOS_NECESSARIOS")
	private Integer OPERARIOS_NECESSARIOS;

	public Integer getID() {
		return ID;
	}

	public Integer getID_PLANEAMENTO_PRODUCAO_ANALISES() {
		return ID_PLANEAMENTO_PRODUCAO_ANALISES;
	}

	public Integer getID_SECTOR_AGREGADOR() {
		return ID_SECTOR_AGREGADOR;
	}

	public String getNOME() {
		return NOME;
	}

	public Integer getCOD_SECTOR() {
		return COD_SECTOR;
	}

	public String getDES_SECTOR() {
		return DES_SECTOR;
	}

	public Integer getLINHA() {
		return LINHA;
	}

	public Integer getANO() {
		return ANO;
	}

	public Integer getSEMANA() {
		return SEMANA;
	}

	public Integer getOPERARIOS_DISPONIVEIS() {
		return OPERARIOS_DISPONIVEIS;
	}

	public Integer getOPERARIOS_NECESSARIOS() {
		return OPERARIOS_NECESSARIOS;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setID_PLANEAMENTO_PRODUCAO_ANALISES(Integer iD_PLANEAMENTO_PRODUCAO_ANALISES) {
		ID_PLANEAMENTO_PRODUCAO_ANALISES = iD_PLANEAMENTO_PRODUCAO_ANALISES;
	}

	public void setID_SECTOR_AGREGADOR(Integer iD_SECTOR_AGREGADOR) {
		ID_SECTOR_AGREGADOR = iD_SECTOR_AGREGADOR;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public void setCOD_SECTOR(Integer cOD_SECTOR) {
		COD_SECTOR = cOD_SECTOR;
	}

	public void setDES_SECTOR(String dES_SECTOR) {
		DES_SECTOR = dES_SECTOR;
	}

	public void setLINHA(Integer lINHA) {
		LINHA = lINHA;
	}

	public void setANO(Integer aNO) {
		ANO = aNO;
	}

	public void setSEMANA(Integer sEMANA) {
		SEMANA = sEMANA;
	}

	public void setOPERARIOS_DISPONIVEIS(Integer oPERARIOS_DISPONIVEIS) {
		OPERARIOS_DISPONIVEIS = oPERARIOS_DISPONIVEIS;
	}

	public void setOPERARIOS_NECESSARIOS(Integer oPERARIOS_NECESSARIOS) {
		OPERARIOS_NECESSARIOS = oPERARIOS_NECESSARIOS;
	}

}