package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "MAN_MOV_PEDIDOS")
public class MAN_MOV_PEDIDOS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty( "ID_PEDIDO")
	private Integer ID_PEDIDO;
	@JsonProperty( "ID_RESPONSAVEL")
	private Integer ID_RESPONSAVEL;
	@JsonProperty( "DATA_HORA_PEDIDO")
	private Timestamp DATA_HORA_PEDIDO;
	@JsonProperty( "EQUIPAMENTO")
	private Integer EQUIPAMENTO;
	@JsonProperty( "COMPONENTE")
	private Integer COMPONENTE;
	@JsonProperty( "LOCALIZACAO")
	private Integer LOCALIZACAO;
	@JsonProperty( "TIPO_LOCALIZACAO")
	private String TIPO_LOCALIZACAO;
	@JsonProperty( "DESCRICAO_PEDIDO")
	private String DESCRICAO_PEDIDO;
	@JsonProperty( "ESTADO")
	private String ESTADO;
	@JsonProperty( "UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty( "DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty( "UTZ_ULT_MODIF")
	private Integer UTZ_ULT_MODIF;
	@JsonProperty( "DATA_ULT_MODIF")
	private Timestamp DATA_ULT_MODIF;
	@JsonProperty( "ATIVO")
	private Boolean ATIVO;
	@JsonProperty( "TIPO_RESPONSAVEL")
	private String TIPO_RESPONSAVEL;
	@JsonProperty( "UTILIZADOR")
	private Integer UTILIZADOR;
	@JsonProperty( "ID_EQUIPA")
	private Integer ID_EQUIPA;
	@JsonProperty( "STATUS_MAQUINA")
	private String STATUS_MAQUINA;
	@JsonProperty( "NOTAS_PLANEAMENTO")
	private String NOTAS_PLANEAMENTO;
	@JsonProperty( "CLASSIFICACAO")
	private String CLASSIFICACAO;
	@JsonProperty( "COD_FORNECEDOR")
	private String COD_FORNECEDOR;
	@JsonProperty( "NOME_FORNECEDOR")
	private String NOME_FORNECEDOR;
	@JsonProperty( "EMAIL_FORNECEDOR")
	private String EMAIL_FORNECEDOR;
	@JsonProperty( "AMBITO_MANUTENCAO")
	private Integer AMBITO_MANUTENCAO;

	// --- Campos de Classificação de Pedido IT (Controlos Segurança Informação) ---
	@JsonProperty("TIPO_CLASSIFICACAO_PEDIDO")
	private String TIPO_CLASSIFICACAO_PEDIDO;         // 'IN', 'PI', 'PM', 'EV'
	@JsonProperty("PRIORIDADE_INTERVENCAO")
	private Integer PRIORIDADE_INTERVENCAO;            // Para PI: 1=Alto, 2=Médio, 3=Baixo
	@JsonProperty("NIVEL_IMPACTO")
	private Integer NIVEL_IMPACTO;                     // Para IN: 1=Alto, 2=Médio, 3=Baixo
	@JsonProperty("NIVEL_URGENCIA")
	private Integer NIVEL_URGENCIA;                    // Para IN: 1=Alto, 2=Médio, 3=Baixo
	@JsonProperty("PRIORIDADE_INCIDENTE")
	private Integer PRIORIDADE_INCIDENTE;              // Auto-calculada: NIVEL_IMPACTO + NIVEL_URGENCIA - 1
	@JsonProperty("TEMPO_RESPOSTA_HORAS")
	private Integer TEMPO_RESPOSTA_HORAS;              // SLA resposta (horas úteis)
	@JsonProperty("TEMPO_RESOLUCAO_HORAS")
	private Integer TEMPO_RESOLUCAO_HORAS;             // SLA resolução (horas úteis)
	@JsonProperty("DATA_HORA_RESPOSTA")
	private Timestamp DATA_HORA_RESPOSTA;              // Quando DTSI respondeu
	@JsonProperty("DATA_HORA_RESOLUCAO_REAL")
	private Timestamp DATA_HORA_RESOLUCAO_REAL;        // Quando efetivamente resolvido

	public String getSTATUS_MAQUINA() {
		return STATUS_MAQUINA;
	}

	public void setSTATUS_MAQUINA(String sTATUS_MAQUINA) {
		STATUS_MAQUINA = sTATUS_MAQUINA;
	}

	public Integer getID_PEDIDO() {
		return ID_PEDIDO;
	}

	public Integer getID_RESPONSAVEL() {
		return ID_RESPONSAVEL;
	}

	public Timestamp getDATA_HORA_PEDIDO() {
		return DATA_HORA_PEDIDO;
	}

	public Integer getEQUIPAMENTO() {
		return EQUIPAMENTO;
	}

	public Integer getCOMPONENTE() {
		return COMPONENTE;
	}

	public Integer getLOCALIZACAO() {
		return LOCALIZACAO;
	}

	public String getDESCRICAO_PEDIDO() {
		return DESCRICAO_PEDIDO;
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

	public Boolean getATIVO() {
		return ATIVO;
	}

	public void setID_PEDIDO(Integer iD_PEDIDO) {
		ID_PEDIDO = iD_PEDIDO;
	}

	public void setID_RESPONSAVEL(Integer iD_RESPONSAVEL) {
		ID_RESPONSAVEL = iD_RESPONSAVEL;
	}

	public void setDATA_HORA_PEDIDO(Timestamp dATA_HORA_PEDIDO) {
		DATA_HORA_PEDIDO = dATA_HORA_PEDIDO;
	}

	public void setEQUIPAMENTO(Integer eQUIPAMENTO) {
		EQUIPAMENTO = eQUIPAMENTO;
	}

	public void setCOMPONENTE(Integer cOMPONENTE) {
		COMPONENTE = cOMPONENTE;
	}

	public void setLOCALIZACAO(Integer lOCALIZACAO) {
		LOCALIZACAO = lOCALIZACAO;
	}

	public void setDESCRICAO_PEDIDO(String dESCRICAO_PEDIDO) {
		DESCRICAO_PEDIDO = dESCRICAO_PEDIDO;
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

	public void setATIVO(Boolean aTIVO) {
		ATIVO = aTIVO;
	}

	public String getTIPO_LOCALIZACAO() {
		return TIPO_LOCALIZACAO;
	}

	public void setTIPO_LOCALIZACAO(String tIPO_LOCALIZACAO) {
		TIPO_LOCALIZACAO = tIPO_LOCALIZACAO;
	}

	public String getTIPO_RESPONSAVEL() {
		return TIPO_RESPONSAVEL;
	}

	public Integer getUTILIZADOR() {
		return UTILIZADOR;
	}

	public Integer getID_EQUIPA() {
		return ID_EQUIPA;
	}

	public void setTIPO_RESPONSAVEL(String tIPO_RESPONSAVEL) {
		TIPO_RESPONSAVEL = tIPO_RESPONSAVEL;
	}

	public void setUTILIZADOR(Integer uTILIZADOR) {
		UTILIZADOR = uTILIZADOR;
	}

	public void setID_EQUIPA(Integer iD_EQUIPA) {
		ID_EQUIPA = iD_EQUIPA;
	}

	public String getNOTAS_PLANEAMENTO() {
		return NOTAS_PLANEAMENTO;
	}

	public String getCLASSIFICACAO() {
		return CLASSIFICACAO;
	}

	public void setNOTAS_PLANEAMENTO(String nOTAS_PLANEAMENTO) {
		NOTAS_PLANEAMENTO = nOTAS_PLANEAMENTO;
	}

	public void setCLASSIFICACAO(String cLASSIFICACAO) {
		CLASSIFICACAO = cLASSIFICACAO;
	}

	public String getCOD_FORNECEDOR() {
		return COD_FORNECEDOR;
	}

	public String getNOME_FORNECEDOR() {
		return NOME_FORNECEDOR;
	}

	public String getEMAIL_FORNECEDOR() {
		return EMAIL_FORNECEDOR;
	}

	public void setCOD_FORNECEDOR(String cOD_FORNECEDOR) {
		COD_FORNECEDOR = cOD_FORNECEDOR;
	}

	public void setNOME_FORNECEDOR(String nOME_FORNECEDOR) {
		NOME_FORNECEDOR = nOME_FORNECEDOR;
	}

	public void setEMAIL_FORNECEDOR(String eMAIL_FORNECEDOR) {
		EMAIL_FORNECEDOR = eMAIL_FORNECEDOR;
	}

	public Integer getAMBITO_MANUTENCAO() {
		return AMBITO_MANUTENCAO;
	}

	public void setAMBITO_MANUTENCAO(Integer aMBITO_MANUTENCAO) {
		AMBITO_MANUTENCAO = aMBITO_MANUTENCAO;
	}

	public String getTIPO_CLASSIFICACAO_PEDIDO() { return TIPO_CLASSIFICACAO_PEDIDO; }
	public void setTIPO_CLASSIFICACAO_PEDIDO(String v) { TIPO_CLASSIFICACAO_PEDIDO = v; }

	public Integer getPRIORIDADE_INTERVENCAO() { return PRIORIDADE_INTERVENCAO; }
	public void setPRIORIDADE_INTERVENCAO(Integer v) { PRIORIDADE_INTERVENCAO = v; }

	public Integer getNIVEL_IMPACTO() { return NIVEL_IMPACTO; }
	public void setNIVEL_IMPACTO(Integer v) { NIVEL_IMPACTO = v; }

	public Integer getNIVEL_URGENCIA() { return NIVEL_URGENCIA; }
	public void setNIVEL_URGENCIA(Integer v) { NIVEL_URGENCIA = v; }

	public Integer getPRIORIDADE_INCIDENTE() { return PRIORIDADE_INCIDENTE; }
	public void setPRIORIDADE_INCIDENTE(Integer v) { PRIORIDADE_INCIDENTE = v; }

	public Integer getTEMPO_RESPOSTA_HORAS() { return TEMPO_RESPOSTA_HORAS; }
	public void setTEMPO_RESPOSTA_HORAS(Integer v) { TEMPO_RESPOSTA_HORAS = v; }

	public Integer getTEMPO_RESOLUCAO_HORAS() { return TEMPO_RESOLUCAO_HORAS; }
	public void setTEMPO_RESOLUCAO_HORAS(Integer v) { TEMPO_RESOLUCAO_HORAS = v; }

	public Timestamp getDATA_HORA_RESPOSTA() { return DATA_HORA_RESPOSTA; }
	public void setDATA_HORA_RESPOSTA(Timestamp v) { DATA_HORA_RESPOSTA = v; }

	public Timestamp getDATA_HORA_RESOLUCAO_REAL() { return DATA_HORA_RESOLUCAO_REAL; }
	public void setDATA_HORA_RESOLUCAO_REAL(Timestamp v) { DATA_HORA_RESOLUCAO_REAL = v; }

}
