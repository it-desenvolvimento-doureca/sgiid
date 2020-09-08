package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_MOV_REG_PARAM_OPERACAO")
public class AB_MOV_REG_PARAM_OPERACAO {

	private Integer ID_REG_PARAM_OPERA;
	private Integer ID_MANUTENCAO_CAB;
	private String DECISAO;
	private String PARAM_INCORRETO;
	private String VALOR_OTIMO;
	private String CAUSA;
	private String EFEITO;
	private String ACAO_CORRETIVA;
	private String ACAO_PREVENTIVA;
	private String SEGUIMENTO;
	private String OBS;
	private String DISTRIBUICAO;
	private String DISTRIBUICAO_OUTROS;
	private Timestamp DATA_CRIA;
	private Integer UTZ_CRIA;
	private Timestamp DATA_VALIDA;
	private Integer UTZ_VALIDA;
	private Boolean IMPRESSO;
	private Timestamp DATA_ULT_IMPRES;
	private Integer UTZ_ULT_IMPRES;
	private Timestamp DATA_ANULACAO;
	private Integer UTZ_ANULACAO;
	private Boolean INATIVO;
	private String PARAM_INCORRETO_2;
	private String VALOR_OTIMO_2;
	private String PARAM_INCORRETO_3;
	private String VALOR_OTIMO_3;

	@Id
	@Column(name = "ID_REG_PARAM_OPERA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_REG_PARAM_OPERA() {
		return ID_REG_PARAM_OPERA;
	}

	@Column(name = "ID_MANUTENCAO_CAB")
	public Integer getID_MANUTENCAO_CAB() {
		return ID_MANUTENCAO_CAB;
	}

	@Column(name = "DECISAO")
	public String getDECISAO() {
		return DECISAO;
	}

	@Column(name = "PARAM_INCORRETO")
	public String getPARAM_INCORRETO() {
		return PARAM_INCORRETO;
	}

	@Column(name = "VALOR_OTIMO")
	public String getVALOR_OTIMO() {
		return VALOR_OTIMO;
	}

	@Column(name = "CAUSA")
	public String getCAUSA() {
		return CAUSA;
	}

	@Column(name = "ACAO_CORRETIVA")
	public String getACAO_CORRETIVA() {
		return ACAO_CORRETIVA;
	}

	@Column(name = "ACAO_PREVENTIVA")
	public String getACAO_PREVENTIVA() {
		return ACAO_PREVENTIVA;
	}

	@Column(name = "SEGUIMENTO")
	public String getSEGUIMENTO() {
		return SEGUIMENTO;
	}

	@Column(name = "OBS")
	public String getOBS() {
		return OBS;
	}

	@Column(name = "DISTRIBUICAO")
	public String getDISTRIBUICAO() {
		return DISTRIBUICAO;
	}

	@Column(name = "DISTRIBUICAO_OUTROS")
	public String getDISTRIBUICAO_OUTROS() {
		return DISTRIBUICAO_OUTROS;
	}

	@Column(name = "DATA_CRIA")
	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	@Column(name = "UTZ_CRIA")
	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	@Column(name = "DATA_VALIDA")
	public Timestamp getDATA_VALIDA() {
		return DATA_VALIDA;
	}

	@Column(name = "UTZ_VALIDA")
	public Integer getUTZ_VALIDA() {
		return UTZ_VALIDA;
	}

	@Column(name = "IMPRESSO")
	public Boolean getIMPRESSO() {
		return IMPRESSO;
	}

	@Column(name = "DATA_ULT_IMPRES")
	public Timestamp getDATA_ULT_IMPRES() {
		return DATA_ULT_IMPRES;
	}

	@Column(name = "UTZ_ULT_IMPRES")
	public Integer getUTZ_ULT_IMPRES() {
		return UTZ_ULT_IMPRES;
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

	@Column(name = "EFEITO")
	public String getEFEITO() {
		return EFEITO;
	}

	public void setEFEITO(String eFEITO) {
		EFEITO = eFEITO;
	}

	public void setID_REG_PARAM_OPERA(Integer iD_REG_PARAM_OPERA) {
		ID_REG_PARAM_OPERA = iD_REG_PARAM_OPERA;
	}

	public void setID_MANUTENCAO_CAB(Integer iD_MANUTENCAO_CAB) {
		ID_MANUTENCAO_CAB = iD_MANUTENCAO_CAB;
	}

	public void setDECISAO(String dECISAO) {
		DECISAO = dECISAO;
	}

	public void setPARAM_INCORRETO(String pARAM_INCORRETO) {
		PARAM_INCORRETO = pARAM_INCORRETO;
	}

	public void setVALOR_OTIMO(String vALOR_OTIMO) {
		VALOR_OTIMO = vALOR_OTIMO;
	}

	public void setCAUSA(String cAUSA) {
		CAUSA = cAUSA;
	}

	public void setACAO_CORRETIVA(String aCAO_CORRETIVA) {
		ACAO_CORRETIVA = aCAO_CORRETIVA;
	}

	public void setACAO_PREVENTIVA(String aCAO_PREVENTIVA) {
		ACAO_PREVENTIVA = aCAO_PREVENTIVA;
	}

	public void setSEGUIMENTO(String sEGUIMENTO) {
		SEGUIMENTO = sEGUIMENTO;
	}

	public void setOBS(String oBS) {
		OBS = oBS;
	}

	public void setDISTRIBUICAO(String dISTRIBUICAO) {
		DISTRIBUICAO = dISTRIBUICAO;
	}

	public void setDISTRIBUICAO_OUTROS(String dISTRIBUICAO_OUTROS) {
		DISTRIBUICAO_OUTROS = dISTRIBUICAO_OUTROS;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_VALIDA(Timestamp dATA_VALIDA) {
		DATA_VALIDA = dATA_VALIDA;
	}

	public void setUTZ_VALIDA(Integer uTZ_VALIDA) {
		UTZ_VALIDA = uTZ_VALIDA;
	}

	public void setIMPRESSO(Boolean iMPRESSO) {
		IMPRESSO = iMPRESSO;
	}

	public void setDATA_ULT_IMPRES(Timestamp dATA_ULT_IMPRES) {
		DATA_ULT_IMPRES = dATA_ULT_IMPRES;
	}

	public void setUTZ_ULT_IMPRES(Integer uTZ_ULT_IMPRES) {
		UTZ_ULT_IMPRES = uTZ_ULT_IMPRES;
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

	@Column(name = "PARAM_INCORRETO_2")
	public String getPARAM_INCORRETO_2() {
		return PARAM_INCORRETO_2;
	}

	@Column(name = "VALOR_OTIMO_2")
	public String getVALOR_OTIMO_2() {
		return VALOR_OTIMO_2;
	}

	@Column(name = "PARAM_INCORRETO_3")
	public String getPARAM_INCORRETO_3() {
		return PARAM_INCORRETO_3;
	}

	@Column(name = "VALOR_OTIMO_3")
	public String getVALOR_OTIMO_3() {
		return VALOR_OTIMO_3;
	}

	public void setPARAM_INCORRETO_2(String pARAM_INCORRETO_2) {
		PARAM_INCORRETO_2 = pARAM_INCORRETO_2;
	}

	public void setVALOR_OTIMO_2(String vALOR_OTIMO_2) {
		VALOR_OTIMO_2 = vALOR_OTIMO_2;
	}

	public void setPARAM_INCORRETO_3(String pARAM_INCORRETO_3) {
		PARAM_INCORRETO_3 = pARAM_INCORRETO_3;
	}

	public void setVALOR_OTIMO_3(String vALOR_OTIMO_3) {
		VALOR_OTIMO_3 = vALOR_OTIMO_3;
	}

}
