package pt.example.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUA_MC_MOV_CALIB_EQUIP")
public class QUA_MC_MOV_CALIB_EQUIP {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CALIB_EQUIP")
	private Integer ID_CALIB_EQUIP;
	@Column(name = "ID_EQUIPAMENTO")
	private Integer ID_EQUIPAMENTO;
	@Column(name = "IT_METODO_OPERACIONAL")
	private String IT_METODO_OPERACIONAL;
	@Column(name = "IT_CALIBRACAO")
	private String IT_CALIBRACAO;
	@Column(name = "IT_VERIFICACAO_INTERNA")
	private String IT_VERIFICACAO_INTERNA;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "DATA_CRIA")
	private Date DATA_CRIA;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "DATA_MODIF")
	private Date DATA_MODIF;
	@Column(name = "UTZ_ANULA")
	private Integer UTZ_ANULA;
	@Column(name = "DATA_ANULA")
	private Date DATA_ANULA;
	@Column(name = "ATIVO")
	private Boolean ATIVO;

	public Integer getID_CALIB_EQUIP() { return ID_CALIB_EQUIP; }
	public void setID_CALIB_EQUIP(Integer v) { ID_CALIB_EQUIP = v; }
	public Integer getID_EQUIPAMENTO() { return ID_EQUIPAMENTO; }
	public void setID_EQUIPAMENTO(Integer v) { ID_EQUIPAMENTO = v; }
	public String getIT_METODO_OPERACIONAL() { return IT_METODO_OPERACIONAL; }
	public void setIT_METODO_OPERACIONAL(String v) { IT_METODO_OPERACIONAL = v; }
	public String getIT_CALIBRACAO() { return IT_CALIBRACAO; }
	public void setIT_CALIBRACAO(String v) { IT_CALIBRACAO = v; }
	public String getIT_VERIFICACAO_INTERNA() { return IT_VERIFICACAO_INTERNA; }
	public void setIT_VERIFICACAO_INTERNA(String v) { IT_VERIFICACAO_INTERNA = v; }
	public Integer getUTZ_CRIA() { return UTZ_CRIA; }
	public void setUTZ_CRIA(Integer v) { UTZ_CRIA = v; }
	public Date getDATA_CRIA() { return DATA_CRIA; }
	public void setDATA_CRIA(Date v) { DATA_CRIA = v; }
	public Integer getUTZ_MODIF() { return UTZ_MODIF; }
	public void setUTZ_MODIF(Integer v) { UTZ_MODIF = v; }
	public Date getDATA_MODIF() { return DATA_MODIF; }
	public void setDATA_MODIF(Date v) { DATA_MODIF = v; }
	public Integer getUTZ_ANULA() { return UTZ_ANULA; }
	public void setUTZ_ANULA(Integer v) { UTZ_ANULA = v; }
	public Date getDATA_ANULA() { return DATA_ANULA; }
	public void setDATA_ANULA(Date v) { DATA_ANULA = v; }
	public Boolean getATIVO() { return ATIVO; }
	public void setATIVO(Boolean v) { ATIVO = v; }
}
