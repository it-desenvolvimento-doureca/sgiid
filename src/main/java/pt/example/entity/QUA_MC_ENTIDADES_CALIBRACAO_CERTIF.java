package pt.example.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUA_MC_ENTIDADES_CALIBRACAO_CERTIF")
public class QUA_MC_ENTIDADES_CALIBRACAO_CERTIF {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CERTIF")
	private Integer ID_CERTIF;
	@Column(name = "ID_ENTIDADE_CALIBRACAO")
	private Integer ID_ENTIDADE_CALIBRACAO;
	@Column(name = "NUM")
	private Integer NUM;
	@Column(name = "DATA_CERTIF")
	private Date DATA_CERTIF;
	@Column(name = "NUM_CERTIF")
	private String NUM_CERTIF;
	@Column(name = "CERTIFICADO")
	private String CERTIFICADO;
	@Column(name = "TIPO_ENSAIO")
	private String TIPO_ENSAIO;
	@Column(name = "VALIDADE")
	private Date VALIDADE;
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

	public Integer getID_CERTIF() { return ID_CERTIF; }
	public void setID_CERTIF(Integer v) { ID_CERTIF = v; }
	public Integer getID_ENTIDADE_CALIBRACAO() { return ID_ENTIDADE_CALIBRACAO; }
	public void setID_ENTIDADE_CALIBRACAO(Integer v) { ID_ENTIDADE_CALIBRACAO = v; }
	public Integer getNUM() { return NUM; }
	public void setNUM(Integer v) { NUM = v; }
	public Date getDATA_CERTIF() { return DATA_CERTIF; }
	public void setDATA_CERTIF(Date v) { DATA_CERTIF = v; }
	public String getNUM_CERTIF() { return NUM_CERTIF; }
	public void setNUM_CERTIF(String v) { NUM_CERTIF = v; }
	public String getCERTIFICADO() { return CERTIFICADO; }
	public void setCERTIFICADO(String v) { CERTIFICADO = v; }
	public String getTIPO_ENSAIO() { return TIPO_ENSAIO; }
	public void setTIPO_ENSAIO(String v) { TIPO_ENSAIO = v; }
	public Date getVALIDADE() { return VALIDADE; }
	public void setVALIDADE(Date v) { VALIDADE = v; }
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
