package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * As referencias que um molde produz, e com quantas cavidades cada uma.
 *
 * E ISTO que multiplica os ciclos para dar pecas:
 *
 *     pecas da referencia = ciclos x CAVIDADES
 *
 * Um molde de 3 cavidades com duas referencias pode ter 2 numa e 1 na outra —
 * por isso as cavidades ficam na LINHA e nao no molde.
 *
 * O MOLDE NAO E UMA TABELA NOSSA: liga-se pela referencia dele no Silver
 * (REF_MOLDE). A lista de moldes le-se do Silver no momento — uma copia
 * desactualiza-se.
 */
@Entity
@Table(name = "INJ_DIC_MOLDE_REF")
public class INJ_DIC_MOLDE_REF {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
	private Integer ID;
	/** SDTGOO.PROREF: a referencia do MOLDE no Silver. Nao ha tabela de moldes. */
	@JsonProperty("REF_MOLDE")
	private String REF_MOLDE;
	/** SDTPRA.PROREF da PECA, nao do molde. */
	@JsonProperty("REF_NUM")
	private String REF_NUM;
	@JsonProperty("REF_DES")
	private String REF_DES;
	/** Cavidades EM USO para esta referencia. */
	@JsonProperty("CAVIDADES")
	private Integer CAVIDADES;
	@JsonProperty("NOTA")
	private String NOTA;
	@JsonProperty("ATIVO")
	private Boolean ATIVO;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;

	public Integer getID() { return ID; }
	public void setID(Integer v) { ID = v; }
	public String getREF_MOLDE() { return REF_MOLDE; }
	public void setREF_MOLDE(String v) { REF_MOLDE = v; }
	public String getREF_NUM() { return REF_NUM; }
	public void setREF_NUM(String v) { REF_NUM = v; }
	public String getREF_DES() { return REF_DES; }
	public void setREF_DES(String v) { REF_DES = v; }
	public Integer getCAVIDADES() { return CAVIDADES; }
	public void setCAVIDADES(Integer v) { CAVIDADES = v; }
	public String getNOTA() { return NOTA; }
	public void setNOTA(String v) { NOTA = v; }
	public Boolean getATIVO() { return ATIVO; }
	public void setATIVO(Boolean v) { ATIVO = v; }
	public Timestamp getDATA_CRIA() { return DATA_CRIA; }
	public void setDATA_CRIA(Timestamp v) { DATA_CRIA = v; }
	public Integer getUTZ_CRIA() { return UTZ_CRIA; }
	public void setUTZ_CRIA(Integer v) { UTZ_CRIA = v; }
	public Timestamp getDATA_MODIF() { return DATA_MODIF; }
	public void setDATA_MODIF(Timestamp v) { DATA_MODIF = v; }
	public Integer getUTZ_MODIF() { return UTZ_MODIF; }
	public void setUTZ_MODIF(Integer v) { UTZ_MODIF = v; }
}
