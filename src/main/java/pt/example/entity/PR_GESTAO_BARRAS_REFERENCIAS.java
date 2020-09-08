package pt.example.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PR_GESTAO_BARRAS_REFERENCIAS")
public class PR_GESTAO_BARRAS_REFERENCIAS {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_GESTAO_BARRAS_REFERENCIAS")
	private Integer ID_GESTAO_BARRAS_REFERENCIAS;
	@Column(name = "ID_GESTAO_BARRAS")
	private Integer ID_GESTAO_BARRAS;
	@Column(name = "N_PECAS_BASTIDOR")
	private String N_PECAS_BASTIDOR;
	@Column(name = "N_PECAS_RACK")
	private String N_PECAS_RACK;
	@Column(name = "ROUBA_CORRENTES")
	private Boolean ROUBA_CORRENTES;
	@Column(name = "BASTIDOR_LINHA_NOVA")
	private Boolean BASTIDOR_LINHA_NOVA;
	@Column(name = "PECA_TIPO")
	private Boolean PECA_TIPO;
	@Column(name = "REFERENCIA")
	private String REFERENCIA;
	@Column(name = "DESIGN_REFERENCIA")
	private String DESIGN_REFERENCIA;
	@Column(name = "OBSERVACOES")
	private String OBSERVACOES;
	@Column(name = "VALIDACAO_BASTIDOR")
	private Integer VALIDACAO_BASTIDOR;

	public Integer getID_GESTAO_BARRAS_REFERENCIAS() {
		return ID_GESTAO_BARRAS_REFERENCIAS;
	}

	public Integer getID_GESTAO_BARRAS() {
		return ID_GESTAO_BARRAS;
	}

	public String getN_PECAS_BASTIDOR() {
		return N_PECAS_BASTIDOR;
	}

	public String getN_PECAS_RACK() {
		return N_PECAS_RACK;
	}

	public Boolean getROUBA_CORRENTES() {
		return ROUBA_CORRENTES;
	}

	public Boolean getBASTIDOR_LINHA_NOVA() {
		return BASTIDOR_LINHA_NOVA;
	}

	public Boolean getPECA_TIPO() {
		return PECA_TIPO;
	}

	public String getREFERENCIA() {
		return REFERENCIA;
	}

	public String getDESIGN_REFERENCIA() {
		return DESIGN_REFERENCIA;
	}

	public String getOBSERVACOES() {
		return OBSERVACOES;
	}

	public Integer getVALIDACAO_BASTIDOR() {
		return VALIDACAO_BASTIDOR;
	}

	public void setID_GESTAO_BARRAS_REFERENCIAS(Integer iD_GESTAO_BARRAS_REFERENCIAS) {
		ID_GESTAO_BARRAS_REFERENCIAS = iD_GESTAO_BARRAS_REFERENCIAS;
	}

	public void setID_GESTAO_BARRAS(Integer iD_GESTAO_BARRAS) {
		ID_GESTAO_BARRAS = iD_GESTAO_BARRAS;
	}

	public void setN_PECAS_BASTIDOR(String n_PECAS_BASTIDOR) {
		N_PECAS_BASTIDOR = n_PECAS_BASTIDOR;
	}

	public void setN_PECAS_RACK(String n_PECAS_RACK) {
		N_PECAS_RACK = n_PECAS_RACK;
	}

	public void setROUBA_CORRENTES(Boolean rOUBA_CORRENTES) {
		ROUBA_CORRENTES = rOUBA_CORRENTES;
	}

	public void setBASTIDOR_LINHA_NOVA(Boolean bASTIDOR_LINHA_NOVA) {
		BASTIDOR_LINHA_NOVA = bASTIDOR_LINHA_NOVA;
	}

	public void setPECA_TIPO(Boolean pECA_TIPO) {
		PECA_TIPO = pECA_TIPO;
	}

	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	public void setDESIGN_REFERENCIA(String dESIGN_REFERENCIA) {
		DESIGN_REFERENCIA = dESIGN_REFERENCIA;
	}

	public void setOBSERVACOES(String oBSERVACOES) {
		OBSERVACOES = oBSERVACOES;
	}

	public void setVALIDACAO_BASTIDOR(Integer vALIDACAO_BASTIDOR) {
		VALIDACAO_BASTIDOR = vALIDACAO_BASTIDOR;
	}

}
