package pt.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Uma referencia de peca, como vem do Silver (SDTPRA).
 *
 * NAO E UMA ENTIDADE: nao ha tabela de referencias do nosso lado. Serve so
 * para o ecra pesquisar em vez de se escrever a referencia a mao — uma
 * referencia mal escrita nao da erro nenhum, so faz a contagem automatica
 * nunca encontrar a configuracao.
 */
public class ReferenciaSilver {

	@JsonProperty("REF_NUM")
	private String REF_NUM;

	@JsonProperty("REF_DES")
	private String REF_DES;

	public String getREF_NUM() { return REF_NUM; }
	public void setREF_NUM(String v) { REF_NUM = v; }
	public String getREF_DES() { return REF_DES; }
	public void setREF_DES(String v) { REF_DES = v; }
}
