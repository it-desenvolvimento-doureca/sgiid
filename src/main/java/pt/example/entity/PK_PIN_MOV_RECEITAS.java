package pt.example.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class PK_PIN_MOV_RECEITAS implements Serializable {

	private Integer ID;
	private Integer VERSAO;

	public PK_PIN_MOV_RECEITAS() {
	}

	public PK_PIN_MOV_RECEITAS(Integer iD, Integer vERSAO) {
		this.ID = iD;
		this.VERSAO = vERSAO;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID, VERSAO);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PK_PIN_MOV_RECEITAS other = (PK_PIN_MOV_RECEITAS) obj;
		return Objects.equals(ID, other.ID) && Objects.equals(VERSAO, other.VERSAO);
	}
}
