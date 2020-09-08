package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_EVENTOS_CONF")
public class GER_EVENTOS_CONF {

	private Integer ID_EVENTO;
	private Integer MODULO;
	private String MOMENTO;
	private String PAGINA;
	private Boolean ESTADO;
	private String EMAIL_PARA;
	private String EMAIL_DE;
	private Boolean EMAIL_ANEXO;
	private String EMAIL_ASSUNTO;
	private String EMAIL_MENSAGEM;
	private String OBS;
	private Integer UTZ_ULT_MODIF;
	private Timestamp DATA_ULT_MODIF;

	private String EMAIL_ASSUNTO_ENG;
	private String EMAIL_MENSAGEM_ENG;
	private String EMAIL_ASSUNTO_FR;
	private String EMAIL_MENSAGEM_FR;

	@Id
	@Column(name = "ID_EVENTO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_EVENTO() {
		return ID_EVENTO;
	}

	@Column(name = "MODULO")
	public Integer getMODULO() {
		return MODULO;
	}

	@Column(name = "MOMENTO")
	public String getMOMENTO() {
		return MOMENTO;
	}

	@Column(name = "PAGINA")
	public String getPAGINA() {
		return PAGINA;
	}

	@Column(name = "ESTADO")
	public Boolean getESTADO() {
		return ESTADO;
	}

	@Column(name = "EMAIL_PARA")
	public String getEMAIL_PARA() {
		return EMAIL_PARA;
	}

	@Column(name = "EMAIL_DE")
	public String getEMAIL_DE() {
		return EMAIL_DE;
	}

	@Column(name = "EMAIL_ANEXO")
	public Boolean getEMAIL_ANEXO() {
		return EMAIL_ANEXO;
	}

	@Column(name = "EMAIL_ASSUNTO")
	public String getEMAIL_ASSUNTO() {
		return EMAIL_ASSUNTO;
	}

	@Column(name = "EMAIL_MENSAGEM")
	public String getEMAIL_MENSAGEM() {
		return EMAIL_MENSAGEM;
	}

	@Column(name = "UTZ_ULT_MODIF")
	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	@Column(name = "DATA_ULT_MODIF")
	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	@Column(name = "OBS")
	public String getOBS() {
		return OBS;
	}

	public void setOBS(String oBS) {
		OBS = oBS;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public void setID_EVENTO(Integer iD_EVENTO) {
		ID_EVENTO = iD_EVENTO;
	}

	public void setMODULO(Integer mODULO) {
		MODULO = mODULO;
	}

	public void setMOMENTO(String mOMENTO) {
		MOMENTO = mOMENTO;
	}

	public void setPAGINA(String pAGINA) {
		PAGINA = pAGINA;
	}

	public void setESTADO(Boolean eSTADO) {
		ESTADO = eSTADO;
	}

	public void setEMAIL_PARA(String eMAIL_PARA) {
		EMAIL_PARA = eMAIL_PARA;
	}

	public void setEMAIL_DE(String eMAIL_DE) {
		EMAIL_DE = eMAIL_DE;
	}

	public void setEMAIL_ANEXO(Boolean eMAIL_ANEXO) {
		EMAIL_ANEXO = eMAIL_ANEXO;
	}

	public void setEMAIL_ASSUNTO(String eMAIL_ASSUNTO) {
		EMAIL_ASSUNTO = eMAIL_ASSUNTO;
	}

	public void setEMAIL_MENSAGEM(String eMAIL_MENSAGEM) {
		EMAIL_MENSAGEM = eMAIL_MENSAGEM;
	}

	@Column(name = "EMAIL_ASSUNTO_ENG")
	public String getEMAIL_ASSUNTO_ENG() {
		return EMAIL_ASSUNTO_ENG;
	}

	@Column(name = "EMAIL_MENSAGEM_ENG")
	public String getEMAIL_MENSAGEM_ENG() {
		return EMAIL_MENSAGEM_ENG;
	}

	@Column(name = "EMAIL_ASSUNTO_FR")
	public String getEMAIL_ASSUNTO_FR() {
		return EMAIL_ASSUNTO_FR;
	}

	@Column(name = "EMAIL_MENSAGEM_FR")
	public String getEMAIL_MENSAGEM_FR() {
		return EMAIL_MENSAGEM_FR;
	}

	public void setEMAIL_ASSUNTO_ENG(String eMAIL_ASSUNTO_ENG) {
		EMAIL_ASSUNTO_ENG = eMAIL_ASSUNTO_ENG;
	}

	public void setEMAIL_MENSAGEM_ENG(String eMAIL_MENSAGEM_ENG) {
		EMAIL_MENSAGEM_ENG = eMAIL_MENSAGEM_ENG;
	}

	public void setEMAIL_ASSUNTO_FR(String eMAIL_ASSUNTO_FR) {
		EMAIL_ASSUNTO_FR = eMAIL_ASSUNTO_FR;
	}

	public void setEMAIL_MENSAGEM_FR(String eMAIL_MENSAGEM_FR) {
		EMAIL_MENSAGEM_FR = eMAIL_MENSAGEM_FR;
	}

}
