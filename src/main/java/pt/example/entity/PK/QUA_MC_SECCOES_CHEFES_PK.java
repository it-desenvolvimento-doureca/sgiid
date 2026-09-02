package pt.example.entity.PK;

import java.io.Serializable;

public class QUA_MC_SECCOES_CHEFES_PK implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer ID_SECCAO;
	private Integer ID_UTILIZADOR;

	public QUA_MC_SECCOES_CHEFES_PK() { }

	public QUA_MC_SECCOES_CHEFES_PK(Integer ID_SECCAO, Integer ID_UTILIZADOR) {
		this.ID_SECCAO = ID_SECCAO;
		this.ID_UTILIZADOR = ID_UTILIZADOR;
	}

	@Override
	public int hashCode() {
		return (ID_SECCAO != null ? ID_SECCAO.hashCode() : 0) ^ (ID_UTILIZADOR != null ? ID_UTILIZADOR.hashCode() : 0);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof QUA_MC_SECCOES_CHEFES_PK)) return false;
		QUA_MC_SECCOES_CHEFES_PK pk = (QUA_MC_SECCOES_CHEFES_PK) obj;
		return (ID_SECCAO != null ? ID_SECCAO.equals(pk.ID_SECCAO) : pk.ID_SECCAO == null)
			&& (ID_UTILIZADOR != null ? ID_UTILIZADOR.equals(pk.ID_UTILIZADOR) : pk.ID_UTILIZADOR == null);
	}

	public Integer getID_SECCAO() { return ID_SECCAO; }
	public void setID_SECCAO(Integer ID_SECCAO) { this.ID_SECCAO = ID_SECCAO; }

	public Integer getID_UTILIZADOR() { return ID_UTILIZADOR; }
	public void setID_UTILIZADOR(Integer ID_UTILIZADOR) { this.ID_UTILIZADOR = ID_UTILIZADOR; }
}
