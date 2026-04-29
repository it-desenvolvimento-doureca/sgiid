package pt.example.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUA_MC_MOV_VERIF_MAQUINA")
public class QUA_MC_MOV_VERIF_MAQUINA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VERIF_MAQUINA")
	private Integer ID_VERIF_MAQUINA;
	@Column(name = "ID_MAQUINA")
	private Integer ID_MAQUINA;
	@Column(name = "NUM")
	private Integer NUM;
	@Column(name = "DATA_VERIFICACAO")
	private Date DATA_VERIFICACAO;
	@Column(name = "ID_TIPO_VERIFICACAO")
	private Integer ID_TIPO_VERIFICACAO;
	@Column(name = "NUM_VERIFICACAO")
	private String NUM_VERIFICACAO;
	@Column(name = "REL_VERIFICACAO")
	private String REL_VERIFICACAO;
	@Column(name = "FREQUENCIA")
	private Integer FREQUENCIA;
	@Column(name = "ID_RESP_VERIFICACAO")
	private Integer ID_RESP_VERIFICACAO;
	@Column(name = "ID_TIPO_ACEITACAO")
	private Integer ID_TIPO_ACEITACAO;
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

	public Integer getID_VERIF_MAQUINA() { return ID_VERIF_MAQUINA; }
	public void setID_VERIF_MAQUINA(Integer v) { ID_VERIF_MAQUINA = v; }
	public Integer getID_MAQUINA() { return ID_MAQUINA; }
	public void setID_MAQUINA(Integer v) { ID_MAQUINA = v; }
	public Integer getNUM() { return NUM; }
	public void setNUM(Integer v) { NUM = v; }
	public Date getDATA_VERIFICACAO() { return DATA_VERIFICACAO; }
	public void setDATA_VERIFICACAO(Date v) { DATA_VERIFICACAO = v; }
	public Integer getID_TIPO_VERIFICACAO() { return ID_TIPO_VERIFICACAO; }
	public void setID_TIPO_VERIFICACAO(Integer v) { ID_TIPO_VERIFICACAO = v; }
	public String getNUM_VERIFICACAO() { return NUM_VERIFICACAO; }
	public void setNUM_VERIFICACAO(String v) { NUM_VERIFICACAO = v; }
	public String getREL_VERIFICACAO() { return REL_VERIFICACAO; }
	public void setREL_VERIFICACAO(String v) { REL_VERIFICACAO = v; }
	public Integer getFREQUENCIA() { return FREQUENCIA; }
	public void setFREQUENCIA(Integer v) { FREQUENCIA = v; }
	public Integer getID_RESP_VERIFICACAO() { return ID_RESP_VERIFICACAO; }
	public void setID_RESP_VERIFICACAO(Integer v) { ID_RESP_VERIFICACAO = v; }
	public Integer getID_TIPO_ACEITACAO() { return ID_TIPO_ACEITACAO; }
	public void setID_TIPO_ACEITACAO(Integer v) { ID_TIPO_ACEITACAO = v; }
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
