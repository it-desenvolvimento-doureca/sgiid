package pt.example.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "QUA_CR_MOV_ENSAIO_TESTE")
public class QUA_CR_MOV_ENSAIO_TESTE {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_ENSAIO_TESTE")
	private Integer ID_ENSAIO_TESTE;
	@JsonProperty("ID_ENSAIO_CAB")
	private Integer ID_ENSAIO_CAB;
	@JsonProperty("NUM_TESTE")
	private Integer NUM_TESTE;
	@JsonProperty("ID_REF_TESTE")
	private Integer ID_REF_TESTE;
	@JsonProperty("DESIGNACAO")
	private String DESIGNACAO;
	@JsonProperty("ID_CONJ_EQUIP")
	private Integer ID_CONJ_EQUIP;
	@JsonProperty("UTZ_CRIA")
	private Integer UTZ_CRIA;
	@JsonProperty("DATA_CRIA")
	private Timestamp DATA_CRIA;
	@JsonProperty("UTZ_MODIF")
	private Integer UTZ_MODIF;
	@JsonProperty("DATA_MODIF")
	private Timestamp DATA_MODIF;
	@JsonProperty("UTZ_ANULA")
	private Integer UTZ_ANULA;
	@JsonProperty("DATA_ANULA")
	private Timestamp DATA_ANULA;
	@JsonProperty("ATIVO")
	private Boolean ATIVO;

	public Integer getID_ENSAIO_TESTE() { return ID_ENSAIO_TESTE; }
	public void setID_ENSAIO_TESTE(Integer v) { ID_ENSAIO_TESTE = v; }
	public Integer getID_ENSAIO_CAB() { return ID_ENSAIO_CAB; }
	public void setID_ENSAIO_CAB(Integer v) { ID_ENSAIO_CAB = v; }
	public Integer getNUM_TESTE() { return NUM_TESTE; }
	public void setNUM_TESTE(Integer v) { NUM_TESTE = v; }
	public Integer getID_REF_TESTE() { return ID_REF_TESTE; }
	public void setID_REF_TESTE(Integer v) { ID_REF_TESTE = v; }
	public String getDESIGNACAO() { return DESIGNACAO; }
	public void setDESIGNACAO(String v) { DESIGNACAO = v; }
	public Integer getID_CONJ_EQUIP() { return ID_CONJ_EQUIP; }
	public void setID_CONJ_EQUIP(Integer v) { ID_CONJ_EQUIP = v; }
	public Integer getUTZ_CRIA() { return UTZ_CRIA; }
	public void setUTZ_CRIA(Integer v) { UTZ_CRIA = v; }
	public Timestamp getDATA_CRIA() { return DATA_CRIA; }
	public void setDATA_CRIA(Timestamp v) { DATA_CRIA = v; }
	public Integer getUTZ_MODIF() { return UTZ_MODIF; }
	public void setUTZ_MODIF(Integer v) { UTZ_MODIF = v; }
	public Timestamp getDATA_MODIF() { return DATA_MODIF; }
	public void setDATA_MODIF(Timestamp v) { DATA_MODIF = v; }
	public Integer getUTZ_ANULA() { return UTZ_ANULA; }
	public void setUTZ_ANULA(Integer v) { UTZ_ANULA = v; }
	public Timestamp getDATA_ANULA() { return DATA_ANULA; }
	public void setDATA_ANULA(Timestamp v) { DATA_ANULA = v; }
	public Boolean getATIVO() { return ATIVO; }
	public void setATIVO(Boolean v) { ATIVO = v; }
}