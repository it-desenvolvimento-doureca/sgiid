package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_UTILIZADORES")
public class GER_UTILIZADORES {

	private Integer ID_UTILIZADOR;
	private String LOGIN;
	private String NOME_UTILIZADOR;
	private String PASSWORD;
	private String EMAIL;
	private String COD_UTZ;
	private Timestamp DATA_CRIA;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;
	private Boolean ADMIN;
	private String USER_JASPER;
	private String PASS_JASPER;

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
	@Column(name = "ID_UTILIZADOR")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_UTILIZADOR() {
		return ID_UTILIZADOR;
	}

	@Column(name = "LOGIN")
	public String getLOGIN() {
		return LOGIN;
	}

	@Column(name = "NOME_UTILIZADOR")
	public String getNOME_UTILIZADOR() {
		return NOME_UTILIZADOR;
	}

	@Column(name = "PASSWORD")
	public String getPASSWORD() {
		return PASSWORD;
	}

	@Column(name = "EMAIL")
	public String getEMAIL() {
		return EMAIL;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "ADMIN")
	public Boolean getADMIN() {
		return ADMIN;
	}

	@Column(name = "COD_UTZ")
	public String getCOD_UTZ() {
		return COD_UTZ;
	}

	public void setCOD_UTZ(String cOD_UTZ) {
		COD_UTZ = cOD_UTZ;
	}

	public void setADMIN(Boolean aDMIN) {
		ADMIN = aDMIN;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setID_UTILIZADOR(Integer iD_UTILIZADOR) {
		ID_UTILIZADOR = iD_UTILIZADOR;
	}

	public void setLOGIN(String lOGIN) {
		LOGIN = lOGIN;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public void setNOME_UTILIZADOR(String nOME_UTILIZADOR) {
		NOME_UTILIZADOR = nOME_UTILIZADOR;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	@Column(name = "USER_JASPER")
	public String getUSER_JASPER() {
		return USER_JASPER;
	}

	public void setUSER_JASPER(String uSER_JASPER) {
		USER_JASPER = uSER_JASPER;
	}

	@Column(name = "PASS_JASPER")
	public String getPASS_JASPER() {
		return PASS_JASPER;
	}

	public void setPASS_JASPER(String pASS_JASPER) {
		PASS_JASPER = pASS_JASPER;
	}

}
