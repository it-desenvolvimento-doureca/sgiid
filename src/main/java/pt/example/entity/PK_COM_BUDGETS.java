package pt.example.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class PK_COM_BUDGETS implements Serializable {

	private Integer ID;
	private Integer VERSAO;

	public PK_COM_BUDGETS() {
	}

	public PK_COM_BUDGETS(Integer iD, Integer vERSAO) {
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
		PK_COM_BUDGETS other = (PK_COM_BUDGETS) obj;
		return Objects.equals(ID, other.ID) && Objects.equals(VERSAO, other.VERSAO);
	}
}
