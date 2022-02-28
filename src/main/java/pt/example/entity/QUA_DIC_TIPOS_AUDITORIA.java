package pt.example.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUA_DIC_TIPOS_AUDITORIA")
public class QUA_DIC_TIPOS_AUDITORIA
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TIPO_AUDITORIA")
    private Integer ID_TIPO_AUDITORIA;
	@Column(name = "DESCRICAO")
    private String DESCRICAO;
	@Column(name = "DATA_CRIA")
    private Date DATA_CRIA;
	@Column(name = "DATA_MODIF")
    private Date DATA_MODIF;
	@Column(name = "DATA_ANULA")
    private Date DATA_ANULA;
	@Column(name = "UTZ_CRIA")
    private Integer UTZ_CRIA;
	@Column(name = "UTZ_MODIF")
    private Integer UTZ_MODIF;
	@Column(name = "UTZ_ANULA")
    private Integer UTZ_ANULA;
	@Column(name = "ATIVO")
    private Boolean ATIVO;
	
	public Integer getID_TIPO_AUDITORIA() {
		return ID_TIPO_AUDITORIA;
	}
	public String getDESCRICAO() {
		return DESCRICAO;
	}
	public Date getDATA_CRIA() {
		return DATA_CRIA;
	}
	public Date getDATA_MODIF() {
		return DATA_MODIF;
	}
	public Date getDATA_ANULA() {
		return DATA_ANULA;
	}
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}
	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}
	public Integer getUTZ_ANULA() {
		return UTZ_ANULA;
	}
	public Boolean getATIVO() {
		return ATIVO;
	}
	public void setID_TIPO_AUDITORIA(Integer iD_TIPO_AUDITORIA) {
		ID_TIPO_AUDITORIA = iD_TIPO_AUDITORIA;
	}
	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}
	public void setDATA_CRIA(Date dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}
	public void setDATA_MODIF(Date dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}
	public void setDATA_ANULA(Date dATA_ANULA) {
		DATA_ANULA = dATA_ANULA;
	}
	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}
	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}
	public void setUTZ_ANULA(Integer uTZ_ANULA) {
		UTZ_ANULA = uTZ_ANULA;
	}
	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}
	
	
	
}