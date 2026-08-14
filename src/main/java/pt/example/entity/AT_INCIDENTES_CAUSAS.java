package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Melhorias 2026-08 - Analise das Causas de um incidente.
 *
 * Linhas livres, que o utilizador acrescenta e remove. Substitui as 5 linhas
 * fixas "Porque" do formulario em papel, que o cliente riscou.
 * NAO usa o Diagrama de Ishikawa: esse e so dos acidentes de trabalho.
 */
@Entity
@Table(name = "AT_INCIDENTES_CAUSAS")
public class AT_INCIDENTES_CAUSAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	@Column(name = "ID_INCIDENTE")
	private Integer ID_INCIDENTE;
	@Column(name = "ORDEM")
	private Integer ORDEM;
	@Column(name = "DESCRICAO")
	private String DESCRICAO;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getID_INCIDENTE() {
		return ID_INCIDENTE;
	}

	public void setID_INCIDENTE(Integer iD_INCIDENTE) {
		ID_INCIDENTE = iD_INCIDENTE;
	}

	public Integer getORDEM() {
		return ORDEM;
	}

	public void setORDEM(Integer oRDEM) {
		ORDEM = oRDEM;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

}
