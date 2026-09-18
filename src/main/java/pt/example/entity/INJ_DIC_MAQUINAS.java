package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Maquinas de injecao de plastico, lidas pelo coletor do tablet de injecao.
 *
 * Mesma forma do PR_DIC_MAQUINAS_MATRIX: as credenciais de acesso a maquina
 * ficam na base de dados, para se configurar uma maquina nova sem tocar no
 * servidor nem reiniciar nada.
 *
 * ATENCAO ao PASSWORD: quem le esta tabela fica com acesso as maquinas. O ecra
 * nunca devolve a password ao browser - ver INJ_DIC_MAQUINASDao.getall().
 *
 * MAQUINA_SILVER e a subseccao do Silver (SDTSEC.SSECOD), por exemplo "13510".
 * E o que liga esta maquina as OFs: o chao de fabrica escreve "135.10", o
 * Silver guarda "13510". Sem isto o tablet nao consegue validar que a OF lida
 * pertence a esta maquina.
 */
@Entity
@Table(name = "INJ_DIC_MAQUINAS")
public class INJ_DIC_MAQUINAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	@JsonProperty("NOME_MAQUINA")
	private String NOME_MAQUINA;
	@JsonProperty("DESCRICAO")
	private String DESCRICAO;
	@JsonProperty("IP_MAQUINA")
	private String IP_MAQUINA;
	@JsonProperty("PORTA")
	private Integer PORTA;
	/** OPCUA | DEVADMIN | SIGMATEK */
	@JsonProperty("TIPO_MAQUINA")
	private String TIPO_MAQUINA;
	@JsonProperty("USERNAME")
	private String USERNAME;
	@JsonProperty("PASSWORD")
	private String PASSWORD;
	@JsonProperty("USA_TLS")
	private Boolean USA_TLS;
	@JsonProperty("PATH_LOGIN")
	private String PATH_LOGIN;
	@JsonProperty("INTERVALO_MS")
	private Integer INTERVALO_MS;
	@JsonProperty("INTERVALO_LENTO_MS")
	private Integer INTERVALO_LENTO_MS;
	/** Subseccao do Silver (SDTSEC.SSECOD), ex. "13510". Liga a maquina as OFs. */
	@JsonProperty("MAQUINA_SILVER")
	private String MAQUINA_SILVER;
	@JsonProperty("MAQ_NUM")
	private String MAQ_NUM;
	@JsonProperty("ATIVO")
	private Boolean ATIVO;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getNOME_MAQUINA() {
		return NOME_MAQUINA;
	}

	public void setNOME_MAQUINA(String nOME_MAQUINA) {
		NOME_MAQUINA = nOME_MAQUINA;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public String getIP_MAQUINA() {
		return IP_MAQUINA;
	}

	public void setIP_MAQUINA(String iP_MAQUINA) {
		IP_MAQUINA = iP_MAQUINA;
	}

	public Integer getPORTA() {
		return PORTA;
	}

	public void setPORTA(Integer pORTA) {
		PORTA = pORTA;
	}

	public String getTIPO_MAQUINA() {
		return TIPO_MAQUINA;
	}

	public void setTIPO_MAQUINA(String tIPO_MAQUINA) {
		TIPO_MAQUINA = tIPO_MAQUINA;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public Boolean getUSA_TLS() {
		return USA_TLS;
	}

	public void setUSA_TLS(Boolean uSA_TLS) {
		USA_TLS = uSA_TLS;
	}

	public String getPATH_LOGIN() {
		return PATH_LOGIN;
	}

	public void setPATH_LOGIN(String pATH_LOGIN) {
		PATH_LOGIN = pATH_LOGIN;
	}

	public Integer getINTERVALO_MS() {
		return INTERVALO_MS;
	}

	public void setINTERVALO_MS(Integer iNTERVALO_MS) {
		INTERVALO_MS = iNTERVALO_MS;
	}

	public Integer getINTERVALO_LENTO_MS() {
		return INTERVALO_LENTO_MS;
	}

	public void setINTERVALO_LENTO_MS(Integer iNTERVALO_LENTO_MS) {
		INTERVALO_LENTO_MS = iNTERVALO_LENTO_MS;
	}

	public String getMAQUINA_SILVER() {
		return MAQUINA_SILVER;
	}

	public void setMAQUINA_SILVER(String mAQUINA_SILVER) {
		MAQUINA_SILVER = mAQUINA_SILVER;
	}

	public String getMAQ_NUM() {
		return MAQ_NUM;
	}

	public void setMAQ_NUM(String mAQ_NUM) {
		MAQ_NUM = mAQ_NUM;
	}

	public Boolean getATIVO() {
		return ATIVO;
	}

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}
}
