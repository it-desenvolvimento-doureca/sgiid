package pt.example.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "REU_REUNIOES")
public class REU_REUNIOES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_REUNIAO")
	private Integer ID_REUNIAO;
	@Column(name = "ID_AMBITO")
	private Integer ID_AMBITO;
	@Column(name = "ASSUNTO")
	private String ASSUNTO;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "DURACAO")
	private String DURACAO;
	@Column(name = "LOCAL")
	private Integer LOCAL;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "UTZ_MODIF")
	private Integer UTZ_MODIF;
	@Column(name = "UTZ_ANULA")
	private Integer UTZ_ANULA;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "DATA_MODIF")
	private Timestamp DATA_MODIF;
	@Column(name = "DATA_ANULA")
	private Timestamp DATA_ANULA;
	@Column(name = "INATIVO")
	private Boolean INATIVO;
	@Column(name = "HORA_REUNIAO")
	private String HORA_REUNIAO;
	@Column(name = "DATA_REUNIAO")
	private Timestamp DATA_REUNIAO;
	@Column(name = "RESPONSAVEL")
	private Integer RESPONSAVEL;

	public Integer getID_REUNIAO() {
		return ID_REUNIAO;
	}

	public Integer getID_AMBITO() {
		return ID_AMBITO;
	}

	public String getASSUNTO() {
		return ASSUNTO;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public String getDURACAO() {
		return DURACAO;
	}

	public Integer getLOCAL() {
		return LOCAL;
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

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public Timestamp getDATA_ANULA() {
		return DATA_ANULA;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public void setID_REUNIAO(Integer iD_REUNIAO) {
		ID_REUNIAO = iD_REUNIAO;
	}

	public void setID_AMBITO(Integer iD_AMBITO) {
		ID_AMBITO = iD_AMBITO;
	}

	public void setASSUNTO(String aSSUNTO) {
		ASSUNTO = aSSUNTO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setDURACAO(String dURACAO) {
		DURACAO = dURACAO;
	}

	public void setLOCAL(Integer lOCAL) {
		LOCAL = lOCAL;
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

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setDATA_ANULA(Timestamp dATA_ANULA) {
		DATA_ANULA = dATA_ANULA;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public String getHORA_REUNIAO() {
		return HORA_REUNIAO;
	}

	public Timestamp getDATA_REUNIAO() {
		return DATA_REUNIAO;
	}

	public void setHORA_REUNIAO(String hORA_REUNIAO) {
		HORA_REUNIAO = hORA_REUNIAO;
	}

	public void setDATA_REUNIAO(Timestamp dATA_REUNIAO) {
		DATA_REUNIAO = dATA_REUNIAO;
	}

	public Integer getRESPONSAVEL() {
		return RESPONSAVEL;
	}

	public void setRESPONSAVEL(Integer rESPONSAVEL) {
		RESPONSAVEL = rESPONSAVEL;
	}

}
