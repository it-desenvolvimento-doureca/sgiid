package pt.example.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PR_DIC_OBJETIVOS_PLANOS")
public class PR_DIC_OBJETIVOS_PLANOS {
	private Integer ID;
	private Float LIMITE_INFERIOR_CUMPRIMENTO;
	private Float LIMITE_INFERIOR_OCUPACAO;
	private Float LIMITE_INFERIOR_OCUPACAO_TOTAL;
	private Float LIMITE_SUPERIOR_OCUPACAO_TOTAL;
	private Float LIMITE_CROMAGEM_MENOR_10;
	private Float LIMITE_CROMAGEM_MAIOR_10;
	private Float LIMITE_INJECAO_MENOR_10;
	private Float LIMITE_INJECAO_MAIOR_10;
	private Float LIMITE_INFERIOR_INDICE_PRODUTIVIDADE;
	private Float LIMITE_SUPERIOR_INDICE_PRODUTIVIDADE;
	private Timestamp DATA_MODIF;
	private Integer UTZ_MODIF;
	private Float OBJETIVO_REJEICAO_GLOBAL;

	private Float LIMITE_PINTURA_MENOR_10;
	private Float LIMITE_PINTURA_MAIOR_10;
	private Float LIMITE_INJECAO_PINTURA_MENOR_10;
	private Float LIMITE_INJECAO_PINTURA_MAIOR_10;
	private Float OBJETIVO_REJEICAO_GLOBAL_PINTURA;

	private Float OBJETIVO_REJEICAO_GLOBAL_INJECAO;
	private Float LIMITE_INJECAO_INJECAO_MENOR_10;
	private Float LIMITE_INJECAO_INJECAO_MAIOR_10;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	@Column(name = "LIMITE_INFERIOR_CUMPRIMENTO")
	public Float getLIMITE_INFERIOR_CUMPRIMENTO() {
		return LIMITE_INFERIOR_CUMPRIMENTO;
	}

	@Column(name = "LIMITE_INFERIOR_OCUPACAO")
	public Float getLIMITE_INFERIOR_OCUPACAO() {
		return LIMITE_INFERIOR_OCUPACAO;
	}

	@Column(name = "LIMITE_INFERIOR_OCUPACAO_TOTAL")
	public Float getLIMITE_INFERIOR_OCUPACAO_TOTAL() {
		return LIMITE_INFERIOR_OCUPACAO_TOTAL;
	}

	@Column(name = "LIMITE_SUPERIOR_OCUPACAO_TOTAL")
	public Float getLIMITE_SUPERIOR_OCUPACAO_TOTAL() {
		return LIMITE_SUPERIOR_OCUPACAO_TOTAL;
	}

	@Column(name = "DATA_MODIF")
	public Timestamp getDATA_MODIF() {
		return DATA_MODIF;
	}

	@Column(name = "UTZ_MODIF")
	public Integer getUTZ_MODIF() {
		return UTZ_MODIF;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void setLIMITE_INFERIOR_CUMPRIMENTO(Float lIMITE_INFERIOR_CUMPRIMENTO) {
		LIMITE_INFERIOR_CUMPRIMENTO = lIMITE_INFERIOR_CUMPRIMENTO;
	}

	public void setLIMITE_INFERIOR_OCUPACAO(Float lIMITE_INFERIOR_OCUPACAO) {
		LIMITE_INFERIOR_OCUPACAO = lIMITE_INFERIOR_OCUPACAO;
	}

	public void setLIMITE_INFERIOR_OCUPACAO_TOTAL(Float lIMITE_INFERIOR_OCUPACAO_TOTAL) {
		LIMITE_INFERIOR_OCUPACAO_TOTAL = lIMITE_INFERIOR_OCUPACAO_TOTAL;
	}

	public void setLIMITE_SUPERIOR_OCUPACAO_TOTAL(Float lIMITE_SUPERIOR_OCUPACAO_TOTAL) {
		LIMITE_SUPERIOR_OCUPACAO_TOTAL = lIMITE_SUPERIOR_OCUPACAO_TOTAL;
	}

	public void setDATA_MODIF(Timestamp dATA_MODIF) {
		DATA_MODIF = dATA_MODIF;
	}

	public void setUTZ_MODIF(Integer uTZ_MODIF) {
		UTZ_MODIF = uTZ_MODIF;
	}

	@Column(name = "LIMITE_CROMAGEM_MENOR_10")
	public Float getLIMITE_CROMAGEM_MENOR_10() {
		return LIMITE_CROMAGEM_MENOR_10;
	}

	@Column(name = "LIMITE_CROMAGEM_MAIOR_10")
	public Float getLIMITE_CROMAGEM_MAIOR_10() {
		return LIMITE_CROMAGEM_MAIOR_10;
	}

	@Column(name = "LIMITE_INJECAO_MENOR_10")
	public Float getLIMITE_INJECAO_MENOR_10() {
		return LIMITE_INJECAO_MENOR_10;
	}

	@Column(name = "LIMITE_INJECAO_MAIOR_10")
	public Float getLIMITE_INJECAO_MAIOR_10() {
		return LIMITE_INJECAO_MAIOR_10;
	}

	public void setLIMITE_CROMAGEM_MENOR_10(Float lIMITE_CROMAGEM_MENOR_10) {
		LIMITE_CROMAGEM_MENOR_10 = lIMITE_CROMAGEM_MENOR_10;
	}

	public void setLIMITE_CROMAGEM_MAIOR_10(Float lIMITE_CROMAGEM_MAIOR_10) {
		LIMITE_CROMAGEM_MAIOR_10 = lIMITE_CROMAGEM_MAIOR_10;
	}

	public void setLIMITE_INJECAO_MENOR_10(Float lIMITE_INJECAO_MENOR_10) {
		LIMITE_INJECAO_MENOR_10 = lIMITE_INJECAO_MENOR_10;
	}

	public void setLIMITE_INJECAO_MAIOR_10(Float lIMITE_INJECAO_MAIOR_10) {
		LIMITE_INJECAO_MAIOR_10 = lIMITE_INJECAO_MAIOR_10;
	}

	@Column(name = "LIMITE_INFERIOR_INDICE_PRODUTIVIDADE")
	public Float getLIMITE_INFERIOR_INDICE_PRODUTIVIDADE() {
		return LIMITE_INFERIOR_INDICE_PRODUTIVIDADE;
	}

	@Column(name = "LIMITE_SUPERIOR_INDICE_PRODUTIVIDADE")
	public Float getLIMITE_SUPERIOR_INDICE_PRODUTIVIDADE() {
		return LIMITE_SUPERIOR_INDICE_PRODUTIVIDADE;
	}

	public void setLIMITE_INFERIOR_INDICE_PRODUTIVIDADE(Float lIMITE_INFERIOR_INDICE_PRODUTIVIDADE) {
		LIMITE_INFERIOR_INDICE_PRODUTIVIDADE = lIMITE_INFERIOR_INDICE_PRODUTIVIDADE;
	}

	public void setLIMITE_SUPERIOR_INDICE_PRODUTIVIDADE(Float lIMITE_SUPERIOR_INDICE_PRODUTIVIDADE) {
		LIMITE_SUPERIOR_INDICE_PRODUTIVIDADE = lIMITE_SUPERIOR_INDICE_PRODUTIVIDADE;
	}

	@Column(name = "OBJETIVO_REJEICAO_GLOBAL")
	public Float getOBJETIVO_REJEICAO_GLOBAL() {
		return OBJETIVO_REJEICAO_GLOBAL;
	}

	public void setOBJETIVO_REJEICAO_GLOBAL(Float oBJETIVO_REJEICAO_GLOBAL) {
		OBJETIVO_REJEICAO_GLOBAL = oBJETIVO_REJEICAO_GLOBAL;
	}

	@Column(name = "LIMITE_PINTURA_MENOR_10")
	public Float getLIMITE_PINTURA_MENOR_10() {
		return LIMITE_PINTURA_MENOR_10;
	}

	@Column(name = "LIMITE_PINTURA_MAIOR_10")
	public Float getLIMITE_PINTURA_MAIOR_10() {
		return LIMITE_PINTURA_MAIOR_10;
	}

	@Column(name = "LIMITE_INJECAO_PINTURA_MENOR_10")
	public Float getLIMITE_INJECAO_PINTURA_MENOR_10() {
		return LIMITE_INJECAO_PINTURA_MENOR_10;
	}

	@Column(name = "LIMITE_INJECAO_PINTURA_MAIOR_10")
	public Float getLIMITE_INJECAO_PINTURA_MAIOR_10() {
		return LIMITE_INJECAO_PINTURA_MAIOR_10;
	}

	@Column(name = "OBJETIVO_REJEICAO_GLOBAL_PINTURA")
	public Float getOBJETIVO_REJEICAO_GLOBAL_PINTURA() {
		return OBJETIVO_REJEICAO_GLOBAL_PINTURA;
	}

	public void setLIMITE_PINTURA_MENOR_10(Float lIMITE_PINTURA_MENOR_10) {
		LIMITE_PINTURA_MENOR_10 = lIMITE_PINTURA_MENOR_10;
	}

	public void setLIMITE_PINTURA_MAIOR_10(Float lIMITE_PINTURA_MAIOR_10) {
		LIMITE_PINTURA_MAIOR_10 = lIMITE_PINTURA_MAIOR_10;
	}

	public void setLIMITE_INJECAO_PINTURA_MENOR_10(Float lIMITE_INJECAO_PINTURA_MENOR_10) {
		LIMITE_INJECAO_PINTURA_MENOR_10 = lIMITE_INJECAO_PINTURA_MENOR_10;
	}

	public void setLIMITE_INJECAO_PINTURA_MAIOR_10(Float lIMITE_INJECAO_PINTURA_MAIOR_10) {
		LIMITE_INJECAO_PINTURA_MAIOR_10 = lIMITE_INJECAO_PINTURA_MAIOR_10;
	}

	public void setOBJETIVO_REJEICAO_GLOBAL_PINTURA(Float oBJETIVO_REJEICAO_GLOBAL_PINTURA) {
		OBJETIVO_REJEICAO_GLOBAL_PINTURA = oBJETIVO_REJEICAO_GLOBAL_PINTURA;
	}

	@Column(name = "OBJETIVO_REJEICAO_GLOBAL_INJECAO")
	public Float getOBJETIVO_REJEICAO_GLOBAL_INJECAO() {
		return OBJETIVO_REJEICAO_GLOBAL_INJECAO;
	}

	@Column(name = "LIMITE_INJECAO_INJECAO_MENOR_10")
	public Float getLIMITE_INJECAO_INJECAO_MENOR_10() {
		return LIMITE_INJECAO_INJECAO_MENOR_10;
	}

	@Column(name = "LIMITE_INJECAO_INJECAO_MAIOR_10")
	public Float getLIMITE_INJECAO_INJECAO_MAIOR_10() {
		return LIMITE_INJECAO_INJECAO_MAIOR_10;
	}

	public void setOBJETIVO_REJEICAO_GLOBAL_INJECAO(Float oBJETIVO_REJEICAO_GLOBAL_INJECAO) {
		OBJETIVO_REJEICAO_GLOBAL_INJECAO = oBJETIVO_REJEICAO_GLOBAL_INJECAO;
	}

	public void setLIMITE_INJECAO_INJECAO_MENOR_10(Float lIMITE_INJECAO_INJECAO_MENOR_10) {
		LIMITE_INJECAO_INJECAO_MENOR_10 = lIMITE_INJECAO_INJECAO_MENOR_10;
	}

	public void setLIMITE_INJECAO_INJECAO_MAIOR_10(Float lIMITE_INJECAO_INJECAO_MAIOR_10) {
		LIMITE_INJECAO_INJECAO_MAIOR_10 = lIMITE_INJECAO_INJECAO_MAIOR_10;
	}

}
