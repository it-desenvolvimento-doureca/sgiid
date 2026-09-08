package pt.example.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Um bloco de teste da tab "Pautas de Ensaio", com as suas leituras.
 *
 * Nao e uma entidade JPA - e a forma como o ecra envia um teste, que espelha
 * o que o utilizador ve: o equipamento e escolhido uma vez por teste, mas o
 * peso, a cavidade e o resultado sao por leitura.
 */
public class QUA_CR_ENSAIO_TESTE_DTO {
	@JsonProperty("TESTE")
	private QUA_CR_MOV_ENSAIO_TESTE TESTE;
	@JsonProperty("LEITURAS")
	private List<QUA_CR_MOV_ENSAIO_LEITURA> LEITURAS;

	public QUA_CR_MOV_ENSAIO_TESTE getTESTE() { return TESTE; }
	public void setTESTE(QUA_CR_MOV_ENSAIO_TESTE v) { TESTE = v; }
	public List<QUA_CR_MOV_ENSAIO_LEITURA> getLEITURAS() { return LEITURAS; }
	public void setLEITURAS(List<QUA_CR_MOV_ENSAIO_LEITURA> v) { LEITURAS = v; }
}
