package pt.example.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Payload de gravacao de um boletim de ensaio de cromagem completo:
 * cabecalho + as quatro tabs do ecra.
 *
 * Nao e uma entidade JPA - e o corpo que o ecra envia para
 * createQUA_CR_RELATORIO_COMPLETO, para que tudo seja gravado numa so
 * chamada transaccional.
 *
 * Porque em bloco e nao chamada a chamada: um boletim e um documento unico,
 * que vai ser impresso e assinado. O padrao dominante no frontend (N chamadas
 * HTTP independentes, uma por linha) permite que um boletim fique meio
 * gravado se a rede falhar a meio - com 5 amostras, 7 testes de 4 leituras e
 * 4 amostras de corrosao em 7 patamares, sao dezenas de chamadas por
 * gravacao. Aqui e uma, dentro da transaccao JTA do @Stateless.
 *
 * Os blocos vem a null quando o teste respetivo nao esta previsto (as
 * checkboxes "Testes previstos" do cabecalho).
 */
public class QUA_CR_RELATORIO_DTO {
	@JsonProperty("RELATORIO")
	private QUA_CR_MOV_RELATORIO RELATORIO;

	// Tab Condicoes
	@JsonProperty("CONDICOES_CAB")
	private QUA_CR_MOV_CONDICOES_CAB CONDICOES_CAB;
	@JsonProperty("CONDICOES")
	private List<QUA_CR_MOV_CONDICOES> CONDICOES;

	// Tab Espessuras / Poros / Fissuras
	@JsonProperty("ESPESSURA_CAB")
	private QUA_CR_MOV_ESPESSURA_CAB ESPESSURA_CAB;
	@JsonProperty("ESPESSURAS")
	private List<QUA_CR_MOV_ESPESSURA> ESPESSURAS;

	// Tab Pautas de Ensaio
	@JsonProperty("ENSAIO_CAB")
	private QUA_CR_MOV_ENSAIO_CAB ENSAIO_CAB;
	@JsonProperty("TESTES")
	private List<QUA_CR_ENSAIO_TESTE_DTO> TESTES;

	// Tab NSS / CaCl2
	@JsonProperty("CORROSAO_CAB")
	private QUA_CR_MOV_CORROSAO_CAB CORROSAO_CAB;
	@JsonProperty("AMOSTRAS_CORROSAO")
	private List<QUA_CR_CORROSAO_AMOSTRA_DTO> AMOSTRAS_CORROSAO;

	public QUA_CR_MOV_RELATORIO getRELATORIO() { return RELATORIO; }
	public void setRELATORIO(QUA_CR_MOV_RELATORIO v) { RELATORIO = v; }

	public QUA_CR_MOV_CONDICOES_CAB getCONDICOES_CAB() { return CONDICOES_CAB; }
	public void setCONDICOES_CAB(QUA_CR_MOV_CONDICOES_CAB v) { CONDICOES_CAB = v; }
	public List<QUA_CR_MOV_CONDICOES> getCONDICOES() { return CONDICOES; }
	public void setCONDICOES(List<QUA_CR_MOV_CONDICOES> v) { CONDICOES = v; }

	public QUA_CR_MOV_ESPESSURA_CAB getESPESSURA_CAB() { return ESPESSURA_CAB; }
	public void setESPESSURA_CAB(QUA_CR_MOV_ESPESSURA_CAB v) { ESPESSURA_CAB = v; }
	public List<QUA_CR_MOV_ESPESSURA> getESPESSURAS() { return ESPESSURAS; }
	public void setESPESSURAS(List<QUA_CR_MOV_ESPESSURA> v) { ESPESSURAS = v; }

	public QUA_CR_MOV_ENSAIO_CAB getENSAIO_CAB() { return ENSAIO_CAB; }
	public void setENSAIO_CAB(QUA_CR_MOV_ENSAIO_CAB v) { ENSAIO_CAB = v; }
	public List<QUA_CR_ENSAIO_TESTE_DTO> getTESTES() { return TESTES; }
	public void setTESTES(List<QUA_CR_ENSAIO_TESTE_DTO> v) { TESTES = v; }

	public QUA_CR_MOV_CORROSAO_CAB getCORROSAO_CAB() { return CORROSAO_CAB; }
	public void setCORROSAO_CAB(QUA_CR_MOV_CORROSAO_CAB v) { CORROSAO_CAB = v; }
	public List<QUA_CR_CORROSAO_AMOSTRA_DTO> getAMOSTRAS_CORROSAO() { return AMOSTRAS_CORROSAO; }
	public void setAMOSTRAS_CORROSAO(List<QUA_CR_CORROSAO_AMOSTRA_DTO> v) { AMOSTRAS_CORROSAO = v; }
}
