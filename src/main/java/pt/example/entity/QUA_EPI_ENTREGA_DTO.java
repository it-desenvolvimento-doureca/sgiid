package pt.example.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Payload do fecho de uma entrega de EPI.
 *
 * Não é uma entidade JPA - é só o corpo que o ecrã de levantamento (e a app
 * de tablet) enviam para createQUA_EPI_ENTREGA_COMPLETA, para que a gravação,
 * o débito no SILVER e a mudança de estado do pedido aconteçam numa só
 * chamada transaccional.
 */
public class QUA_EPI_ENTREGA_DTO {
	@JsonProperty("ENTREGA")
	private QUA_EPI_MOV_ENTREGA ENTREGA;
	@JsonProperty("ETIQUETAS")
	private List<QUA_EPI_MOV_ENTREGA_ETIQ> ETIQUETAS;
	/**
	 * false = gravar rascunho (não debita o SILVER nem fecha o pedido);
	 * true  = concluir a entrega.
	 */
	@JsonProperty("CONCLUIR")
	private Boolean CONCLUIR;

	public Boolean getCONCLUIR() { return CONCLUIR; }
	public void setCONCLUIR(Boolean v) { CONCLUIR = v; }

	public QUA_EPI_MOV_ENTREGA getENTREGA() { return ENTREGA; }
	public void setENTREGA(QUA_EPI_MOV_ENTREGA v) { ENTREGA = v; }
	public List<QUA_EPI_MOV_ENTREGA_ETIQ> getETIQUETAS() { return ETIQUETAS; }
	public void setETIQUETAS(List<QUA_EPI_MOV_ENTREGA_ETIQ> v) { ETIQUETAS = v; }
}
