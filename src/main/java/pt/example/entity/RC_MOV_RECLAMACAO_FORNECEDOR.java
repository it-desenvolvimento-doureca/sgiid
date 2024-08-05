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
@Table(name = "RC_MOV_RECLAMACAO_FORNECEDOR")
public class RC_MOV_RECLAMACAO_FORNECEDOR {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RECLAMACAO")
	private Integer ID_RECLAMACAO;
	@Column(name = "TITULO")
	private String TITULO;
	@Column(name = "NUMERO_RECLAMACAO_FORNECEDOR")
	private String NUMERO_RECLAMACAO_FORNECEDOR;
	@Column(name = "DATA_RECLAMACAO")
	private Timestamp DATA_RECLAMACAO;
	@Column(name = "CLASSIFICACAO")
	private Integer CLASSIFICACAO;
	@Column(name = "TIPOLOGIA")
	private Integer TIPOLOGIA;
	@Column(name = "GRAU_IMPORTANCIA")
	private Integer GRAU_IMPORTANCIA;
	@Column(name = "UTZ_RESPONSAVEL")
	private Integer UTZ_RESPONSAVEL;
	@Column(name = "ID_FORNECEDOR")
	private Integer ID_FORNECEDOR;
	@Column(name = "NOME_FORNECEDOR")
	private String NOME_FORNECEDOR;
	@Column(name = "MORADA_FORNECEDOR")
	private String MORADA_FORNECEDOR;
	@Column(name = "CONTATO_FORNECEDOR")
	private String CONTATO_FORNECEDOR;
	@Column(name = "EMAIL_FORNECEDOR")
	private String EMAIL_FORNECEDOR;
	@Column(name = "TELEFONE_FORNECEDOR")
	private String TELEFONE_FORNECEDOR;
	@Column(name = "REFERENCIA")
	private String REFERENCIA;
	@Column(name = "DESIGNACAO_REF")
	private String DESIGNACAO_REF;
	@Column(name = "LOTE")
	private String LOTE;
	@Column(name = "QTD")
	private Integer QTD;
	@Column(name = "PROBLEMA_REPETIDO")
	private Boolean PROBLEMA_REPETIDO;
	@Column(name = "NUMERO_RECLAMACAO_REPETIDA")
	private Integer NUMERO_RECLAMACAO_REPETIDA;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;
	@Column(name = "OBSERVACOES")
	private String OBSERVACOES;
	@Column(name = "DECISAO_DOURECA")
	private String DECISAO_DOURECA;
	@Column(name = "ACCAO_1")
	private Boolean ACCAO_1;
	@Column(name = "ACCAO_2")
	private Boolean ACCAO_2;
	@Column(name = "ACCAO_3")
	private Boolean ACCAO_3;
	@Column(name = "DECISAO_FORNECEDOR")
	private String DECISAO_FORNECEDOR;
	@Column(name = "DECISAO_FORNECEDOR_OUTRO")
	private String DECISAO_FORNECEDOR_OUTRO;
	@Column(name = "DATA_VERIFICACAO")
	private Date DATA_VERIFICACAO;
	@Column(name = "RESPONSAVEL_QUALIDADE")
	private Integer RESPONSAVEL_QUALIDADE;
	@Column(name = "INATIVO")
	private Boolean INATIVO;
	@Column(name = "ESTADO")
	private String ESTADO;
	@Column(name = "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@Column(name = "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@Column(name = "UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@Column(name = "DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@Column(name = "DATA_ANULACAO")
	private Timestamp DATA_ANULACAO;
	@Column(name = "UTZ_ANULACAO")
	private Integer UTZ_ANULACAO;
	@Column(name = "ETSNUM")
	private String ETSNUM;
	@Column(name = "DATA_RECEPCAO")
	private Date DATA_RECEPCAO;
	@Column(name = "DATA_FECHA")
	private Timestamp DATA_FECHA;
	@Column(name = "UTZ_FECHA")
	private Integer UTZ_FECHA;

	@Column(name = "DATA_OBJETIVO1")
	private Date DATA_OBJETIVO1;
	@Column(name = "DATA_OBJETIVO2")
	private Date DATA_OBJETIVO2;
	@Column(name = "DATA_OBJETIVO3")
	private Date DATA_OBJETIVO3;

	@Column(name = "DATA_REAL1")
	private Date DATA_REAL1;
	@Column(name = "DATA_REAL2")
	private Date DATA_REAL2;
	@Column(name = "DATA_REAL3")
	private Date DATA_REAL3;

	public Integer getID_RECLAMACAO() {
		return ID_RECLAMACAO;
	}

	public String getTITULO() {
		return TITULO;
	}

	public String getNUMERO_RECLAMACAO_FORNECEDOR() {
		return NUMERO_RECLAMACAO_FORNECEDOR;
	}

	public Timestamp getDATA_RECLAMACAO() {
		return DATA_RECLAMACAO;
	}

	public Integer getCLASSIFICACAO() {
		return CLASSIFICACAO;
	}

	public Integer getTIPOLOGIA() {
		return TIPOLOGIA;
	}

	public Integer getGRAU_IMPORTANCIA() {
		return GRAU_IMPORTANCIA;
	}

	public Integer getUTZ_RESPONSAVEL() {
		return UTZ_RESPONSAVEL;
	}

	public Integer getID_FORNECEDOR() {
		return ID_FORNECEDOR;
	}

	public String getNOME_FORNECEDOR() {
		return NOME_FORNECEDOR;
	}

	public String getMORADA_FORNECEDOR() {
		return MORADA_FORNECEDOR;
	}

	public String getCONTATO_FORNECEDOR() {
		return CONTATO_FORNECEDOR;
	}

	public String getEMAIL_FORNECEDOR() {
		return EMAIL_FORNECEDOR;
	}

	public String getTELEFONE_FORNECEDOR() {
		return TELEFONE_FORNECEDOR;
	}

	public String getREFERENCIA() {
		return REFERENCIA;
	}

	public String getDESIGNACAO_REF() {
		return DESIGNACAO_REF;
	}

	public String getLOTE() {
		return LOTE;
	}

	public Integer getQTD() {
		return QTD;
	}

	public Boolean getPROBLEMA_REPETIDO() {
		return PROBLEMA_REPETIDO;
	}

	public Integer getNUMERO_RECLAMACAO_REPETIDA() {
		return NUMERO_RECLAMACAO_REPETIDA;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public String getOBSERVACOES() {
		return OBSERVACOES;
	}

	public String getDECISAO_DOURECA() {
		return DECISAO_DOURECA;
	}

	public Boolean getACCAO_1() {
		return ACCAO_1;
	}

	public Boolean getACCAO_2() {
		return ACCAO_2;
	}

	public Boolean getACCAO_3() {
		return ACCAO_3;
	}

	public String getDECISAO_FORNECEDOR() {
		return DECISAO_FORNECEDOR;
	}

	public String getDECISAO_FORNECEDOR_OUTRO() {
		return DECISAO_FORNECEDOR_OUTRO;
	}

	public Date getDATA_VERIFICACAO() {
		return DATA_VERIFICACAO;
	}

	public Integer getRESPONSAVEL_QUALIDADE() {
		return RESPONSAVEL_QUALIDADE;
	}

	public Boolean getINATIVO() {
		return INATIVO;
	}

	public String getESTADO() {
		return ESTADO;
	}

	public Integer getUTZ_CRIA() {
		return UTZ_CRIA;
	}

	public Timestamp getDATA_CRIA() {
		return DATA_CRIA;
	}

	public Integer getUTZ_ULT_MODIF() {
		return UTZ_ULT_MODIF;
	}

	public Timestamp getDATA_ULT_MODIF() {
		return DATA_ULT_MODIF;
	}

	public Timestamp getDATA_ANULACAO() {
		return DATA_ANULACAO;
	}

	public Integer getUTZ_ANULACAO() {
		return UTZ_ANULACAO;
	}

	public void setID_RECLAMACAO(Integer iD_RECLAMACAO) {
		ID_RECLAMACAO = iD_RECLAMACAO;
	}

	public void setTITULO(String tITULO) {
		TITULO = tITULO;
	}

	public void setNUMERO_RECLAMACAO_FORNECEDOR(String nUMERO_RECLAMACAO_FORNECEDOR) {
		NUMERO_RECLAMACAO_FORNECEDOR = nUMERO_RECLAMACAO_FORNECEDOR;
	}

	public void setDATA_RECLAMACAO(Timestamp dATA_RECLAMACAO) {
		DATA_RECLAMACAO = dATA_RECLAMACAO;
	}

	public void setCLASSIFICACAO(Integer cLASSIFICACAO) {
		CLASSIFICACAO = cLASSIFICACAO;
	}

	public void setTIPOLOGIA(Integer tIPOLOGIA) {
		TIPOLOGIA = tIPOLOGIA;
	}

	public void setGRAU_IMPORTANCIA(Integer gRAU_IMPORTANCIA) {
		GRAU_IMPORTANCIA = gRAU_IMPORTANCIA;
	}

	public void setUTZ_RESPONSAVEL(Integer uTZ_RESPONSAVEL) {
		UTZ_RESPONSAVEL = uTZ_RESPONSAVEL;
	}

	public void setID_FORNECEDOR(Integer iD_FORNECEDOR) {
		ID_FORNECEDOR = iD_FORNECEDOR;
	}

	public void setNOME_FORNECEDOR(String nOME_FORNECEDOR) {
		NOME_FORNECEDOR = nOME_FORNECEDOR;
	}

	public void setMORADA_FORNECEDOR(String mORADA_FORNECEDOR) {
		MORADA_FORNECEDOR = mORADA_FORNECEDOR;
	}

	public void setCONTATO_FORNECEDOR(String cONTATO_FORNECEDOR) {
		CONTATO_FORNECEDOR = cONTATO_FORNECEDOR;
	}

	public void setEMAIL_FORNECEDOR(String eMAIL_FORNECEDOR) {
		EMAIL_FORNECEDOR = eMAIL_FORNECEDOR;
	}

	public void setTELEFONE_FORNECEDOR(String tELEFONE_FORNECEDOR) {
		TELEFONE_FORNECEDOR = tELEFONE_FORNECEDOR;
	}

	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	public void setDESIGNACAO_REF(String dESIGNACAO_REF) {
		DESIGNACAO_REF = dESIGNACAO_REF;
	}

	public void setLOTE(String lOTE) {
		LOTE = lOTE;
	}

	public void setQTD(Integer qTD) {
		QTD = qTD;
	}

	public void setPROBLEMA_REPETIDO(Boolean pROBLEMA_REPETIDO) {
		PROBLEMA_REPETIDO = pROBLEMA_REPETIDO;
	}

	public void setNUMERO_RECLAMACAO_REPETIDA(Integer nUMERO_RECLAMACAO_REPETIDA) {
		NUMERO_RECLAMACAO_REPETIDA = nUMERO_RECLAMACAO_REPETIDA;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setOBSERVACOES(String oBSERVACOES) {
		OBSERVACOES = oBSERVACOES;
	}

	public void setDECISAO_DOURECA(String dECISAO_DOURECA) {
		DECISAO_DOURECA = dECISAO_DOURECA;
	}

	public void setACCAO_1(Boolean aCCAO_1) {
		ACCAO_1 = aCCAO_1;
	}

	public void setACCAO_2(Boolean aCCAO_2) {
		ACCAO_2 = aCCAO_2;
	}

	public void setACCAO_3(Boolean aCCAO_3) {
		ACCAO_3 = aCCAO_3;
	}

	public void setDECISAO_FORNECEDOR(String dECISAO_FORNECEDOR) {
		DECISAO_FORNECEDOR = dECISAO_FORNECEDOR;
	}

	public void setDECISAO_FORNECEDOR_OUTRO(String dECISAO_FORNECEDOR_OUTRO) {
		DECISAO_FORNECEDOR_OUTRO = dECISAO_FORNECEDOR_OUTRO;
	}

	public void setDATA_VERIFICACAO(Date dATA_VERIFICACAO) {
		DATA_VERIFICACAO = dATA_VERIFICACAO;
	}

	public void setRESPONSAVEL_QUALIDADE(Integer rESPONSAVEL_QUALIDADE) {
		RESPONSAVEL_QUALIDADE = rESPONSAVEL_QUALIDADE;
	}

	public void setINATIVO(Boolean iNATIVO) {
		INATIVO = iNATIVO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	public void setUTZ_CRIA(Integer uTZ_CRIA) {
		UTZ_CRIA = uTZ_CRIA;
	}

	public void setDATA_CRIA(Timestamp dATA_CRIA) {
		DATA_CRIA = dATA_CRIA;
	}

	public void setUTZ_ULT_MODIF(Integer uTZ_ULT_MODIF) {
		UTZ_ULT_MODIF = uTZ_ULT_MODIF;
	}

	public void setDATA_ULT_MODIF(Timestamp dATA_ULT_MODIF) {
		DATA_ULT_MODIF = dATA_ULT_MODIF;
	}

	public void setDATA_ANULACAO(Timestamp dATA_ANULACAO) {
		DATA_ANULACAO = dATA_ANULACAO;
	}

	public void setUTZ_ANULACAO(Integer uTZ_ANULACAO) {
		UTZ_ANULACAO = uTZ_ANULACAO;
	}

	public String getETSNUM() {
		return ETSNUM;
	}

	public void setETSNUM(String eTSNUM) {
		ETSNUM = eTSNUM;
	}

	public Date getDATA_RECEPCAO() {
		return DATA_RECEPCAO;
	}

	public void setDATA_RECEPCAO(Date dATA_RECEPCAO) {
		DATA_RECEPCAO = dATA_RECEPCAO;
	}

	public Timestamp getDATA_FECHA() {
		return DATA_FECHA;
	}

	public Integer getUTZ_FECHA() {
		return UTZ_FECHA;
	}

	public void setDATA_FECHA(Timestamp dATA_FECHA) {
		DATA_FECHA = dATA_FECHA;
	}

	public void setUTZ_FECHA(Integer uTZ_FECHA) {
		UTZ_FECHA = uTZ_FECHA;
	}

	public Date getDATA_OBJETIVO1() {
		return DATA_OBJETIVO1;
	}

	public Date getDATA_OBJETIVO2() {
		return DATA_OBJETIVO2;
	}

	public Date getDATA_OBJETIVO3() {
		return DATA_OBJETIVO3;
	}

	public void setDATA_OBJETIVO1(Date dATA_OBJETIVO1) {
		DATA_OBJETIVO1 = dATA_OBJETIVO1;
	}

	public void setDATA_OBJETIVO2(Date dATA_OBJETIVO2) {
		DATA_OBJETIVO2 = dATA_OBJETIVO2;
	}

	public void setDATA_OBJETIVO3(Date dATA_OBJETIVO3) {
		DATA_OBJETIVO3 = dATA_OBJETIVO3;
	}

	public Date getDATA_REAL1() {
		return DATA_REAL1;
	}

	public Date getDATA_REAL2() {
		return DATA_REAL2;
	}

	public Date getDATA_REAL3() {
		return DATA_REAL3;
	}

	public void setDATA_REAL1(Date dATA_REAL1) {
		DATA_REAL1 = dATA_REAL1;
	}

	public void setDATA_REAL2(Date dATA_REAL2) {
		DATA_REAL2 = dATA_REAL2;
	}

	public void setDATA_REAL3(Date dATA_REAL3) {
		DATA_REAL3 = dATA_REAL3;
	}

}
