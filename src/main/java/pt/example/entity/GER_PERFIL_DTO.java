package pt.example.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Payload da gravacao de um perfil de acessos.
 *
 * Nao e uma entidade JPA - e so o corpo que o ecra de permissoes envia para
 * gravarGER_PERFIL, para que o cabecalho (criar ou actualizar) e todas as
 * linhas de acessos sejam gravados numa so chamada transaccional.
 */
public class GER_PERFIL_DTO {
	@JsonProperty("PERFIL")
	private GER_PERFIL_CAB PERFIL;
	@JsonProperty("LINHAS")
	private List<GER_PERFIL_LIN> LINHAS;

	public GER_PERFIL_CAB getPERFIL() { return PERFIL; }
	public void setPERFIL(GER_PERFIL_CAB v) { PERFIL = v; }
	public List<GER_PERFIL_LIN> getLINHAS() { return LINHAS; }
	public void setLINHAS(List<GER_PERFIL_LIN> v) { LINHAS = v; }
}
