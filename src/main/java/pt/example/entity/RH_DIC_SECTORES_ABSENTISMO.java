package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RH_DIC_SECTORES_ABSENTISMO")
public class RH_DIC_SECTORES_ABSENTISMO
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SECTOR_ABSENTISMO")
    private Integer ID_SECTOR_ABSENTISMO;
	@Column(name = "NOME")
    private String NOME;
	@Column(name = "LOCAL")
    private Integer LOCAL;
	@Column(name = "POSICAO")
    private Integer POSICAO;
	@Column(name = "DATA_CRIA")
    private Timestamp DATA_CRIA;
	@Column(name = "UTZ_CRIA")
    private Integer UTZ_CRIA;
	@Column(name = "DATA_MODIF")
    private Timestamp DATA_MODIF;
	@Column(name = "UTZ_MODIF")
    private Integer UTZ_MODIF;
	@Column(name = "DATA_ANULA")
    private Timestamp DATA_ANULA;
	@Column(name = "UTZ_ANULA")
    private Integer UTZ_ANULA;
	@Column(name = "ATIVO")
    private Boolean ATIVO;
	public Integer getID_SECTOR_ABSENTISMO() {
		return ID_SECTOR_ABSENTISMO;
	}
	public String getNOME() {
		return NOME;
	}
	public Integer getLOCAL() {
		return LOCAL;
	}
	public Integer getPOSICAO() {
		return POSICAO;
	}
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}
	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}
	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}
	public Timestamp getDATA_ANULA() {
		return DATA_ANULA;
	}
	public Integer getUTZ_ANULA() {
		return UTZ_ANULA;
	}
	public Boolean getATIVO() {
		return ATIVO;
	}
	public void setID_SECTOR_ABSENTISMO(Integer iD_SECTOR_ABSENTISMO) {
		ID_SECTOR_ABSENTISMO = iD_SECTOR_ABSENTISMO;
	}
	public void setNOME(String nOME) {
		NOME = nOME;
	}
	public void setLOCAL(Integer lOCAL) {
		LOCAL = lOCAL;
	}
	public void setPOSICAO(Integer pOSICAO) {
		POSICAO = pOSICAO;
	}
	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}
	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}
	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}
	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}
	public void setDATA_ANULA(Timestamp dATA_ANULA) {
		DATA_ANULA = dATA_ANULA;
	}
	public void setUTZ_ANULA(Integer uTZ_ANULA) {
		UTZ_ANULA = uTZ_ANULA;
	}
	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}
	
	
}