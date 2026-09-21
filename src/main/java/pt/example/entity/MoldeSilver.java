package pt.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Um molde, como vem do Silver.
 *
 * NAO E UMA ENTIDADE: nao tem @Entity nem @Table porque nao ha tabela nenhuma
 * do nosso lado. E so o que a leitura do Silver devolve ao ecra.
 *
 * A identidade e a REF_MOLDE (SDTGOO.PROREF) — e por ela que a configuracao
 * de cavidades (INJ_DIC_MOLDE_REF) se liga ao molde.
 */
public class MoldeSilver {

	@JsonProperty("REF_MOLDE")
	private String REF_MOLDE;

	@JsonProperty("DESCRICAO")
	private String DESCRICAO;

	public String getREF_MOLDE() { return REF_MOLDE; }
	public void setREF_MOLDE(String v) { REF_MOLDE = v; }
	public String getDESCRICAO() { return DESCRICAO; }
	public void setDESCRICAO(String v) { DESCRICAO = v; }
}
