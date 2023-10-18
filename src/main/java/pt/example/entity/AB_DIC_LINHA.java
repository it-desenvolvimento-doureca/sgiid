package pt.example.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_DIC_LINHA")
public class AB_DIC_LINHA {

	private Integer ID_LINHA;
	private String NOME_LINHA;
	private String COR;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;
	private String SECCAO;
	private String SUBSECCAO;
	private String REF_COMPOSTO;
	private String PASTA;
	private String UTILIZADOR;
	private String SENHA;
	private String DOMINIO;
	private Boolean DESATIVA;
	private Date DATA_INICIO;
	private Date DATA_FIM;

	@Column(name = "SECCAO")
	public String getSECCAO() {
		return SECCAO;
	}

	@Column(name = "SUBSECCAO")
	public String getSUBSECCAO() {
		return SUBSECCAO;
	}

	@Column(name = "REF_COMPOSTO")
	public String getREF_COMPOSTO() {
		return REF_COMPOSTO;
	}

	public void setSECCAO(String sECCAO) {
		SECCAO = sECCAO;
	}

	public void setSUBSECCAO(String sUBSECCAO) {
		SUBSECCAO = sUBSECCAO;
	}

	public void setREF_COMPOSTO(String rEF_COMPOSTO) {
		REF_COMPOSTO = rEF_COMPOSTO;
	}

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
	@Column(name = "ID_LINHA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_LINHA() {
		return ID_LINHA;
	}

	@Column(name = "NOME_LINHA")
	public String getNOME_LINHA() {
		return NOME_LINHA;
	}

	@Column(name = "COR")
	public String getCOR() {
		return COR;
	}

	public void setID_LINHA(Integer iD_LINHA) {
		ID_LINHA = iD_LINHA;
	}

	public void setNOME_LINHA(String nOME_LINHA) {
		NOME_LINHA = nOME_LINHA;
	}

	public void setCOR(String cOR) {
		COR = cOR;
	}

	@Column(name = "PASTA")
	public String getPASTA() {
		return PASTA;
	}

	@Column(name = "UTILIZADOR")
	public String getUTILIZADOR() {
		return UTILIZADOR;
	}

	@Column(name = "SENHA")
	public String getSENHA() {
		return SENHA;
	}

	@Column(name = "DOMINIO")
	public String getDOMINIO() {
		return DOMINIO;
	}

	public void setPASTA(String pASTA) {
		PASTA = pASTA;
	}

	public void setUTILIZADOR(String uTILIZADOR) {
		UTILIZADOR = uTILIZADOR;
	}

	public void setSENHA(String sENHA) {
		SENHA = sENHA;
	}

	public void setDOMINIO(String dOMINIO) {
		DOMINIO = dOMINIO;
	}

	@Column(name = "DESATIVA")
	public Boolean getDESATIVA() {
		return DESATIVA;
	}

	public void setDESATIVA(Boolean dESATIVA) {
		DESATIVA = dESATIVA;
	}

	@Column(name = "DATA_INICIO")
	public Date getDATA_INICIO() {
		return DATA_INICIO;
	}

	public void setDATA_INICIO(Date dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}

	@Column(name = "DATA_FIM")
	public Date getDATA_FIM() {
		return DATA_FIM;
	}

	public void setDATA_FIM(Date dATA_FIM) {
		DATA_FIM = dATA_FIM;
	}

}
