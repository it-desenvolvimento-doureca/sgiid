package pt.example.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Uma amostra de ensaio de corrosao (NSS ou CaCl2) com as leituras dos seus
 * patamares.
 *
 * Nao e uma entidade JPA. Mesma razao do QUA_CR_ENSAIO_TESTE_DTO: no ecra o
 * peso e a cavidade sao da amostra (uma vez por linha da grelha) e o
 * resultado e de cada patamar (uma coluna por patamar).
 */
public class QUA_CR_CORROSAO_AMOSTRA_DTO {
	@JsonProperty("AMOSTRA")
	private QUA_CR_MOV_CORROSAO_AMOSTRA AMOSTRA;
	@JsonProperty("LEITURAS")
	private List<QUA_CR_MOV_CORROSAO_LEITURA> LEITURAS;

	public QUA_CR_MOV_CORROSAO_AMOSTRA getAMOSTRA() { return AMOSTRA; }
	public void setAMOSTRA(QUA_CR_MOV_CORROSAO_AMOSTRA v) { AMOSTRA = v; }
	public List<QUA_CR_MOV_CORROSAO_LEITURA> getLEITURAS() { return LEITURAS; }
	public void setLEITURAS(List<QUA_CR_MOV_CORROSAO_LEITURA> v) { LEITURAS = v; }
}
