package pt.example.entity;

import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "QUA_CR_MOV_CORROSAO_CAB")
public class QUA_CR_MOV_CORROSAO_CAB {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID_CORROSAO_CAB")
	private Integer ID_CORROSAO_CAB;
	@JsonProperty("ID_RELATORIO")
	private Integer ID_RELATORIO;
	@JsonProperty("ID_OPERADOR")
	private Integer ID_OPERADOR;
	@JsonProperty("DATA_CACL2")
	private Date DATA_CACL2;
	@JsonProperty("CACL2_RENAULT")
	private Boolean CACL2_RENAULT;
	@JsonProperty("CACL2_PEUGEOT")
	private Boolean CACL2_PEUGEOT;
	@JsonProperty("CACL2_JAPAO")
	private Boolean CACL2_JAPAO;
	@JsonProperty("ID_CONJ_EQUIP_CACL2")
	private Integer ID_CONJ_EQUIP_CACL2;
	@JsonProperty("ID_CONJ_EQUIP_NSS")
	private Integer ID_CONJ_EQUIP_NSS;
	@JsonProperty("RESULTADO_TOTAL_CACL2")
	private String RESULTADO_TOTAL_CACL2;
	@JsonProperty("RESULTADO_TOTAL_NSS")
	private String RESULTADO_TOTAL_NSS;
	@JsonProperty("OBSERVACOES")
	private String OBSERVACOES;
	@JsonProperty("ID_ACCESS_LEGADO")
	private Integer ID_ACCESS_LEGADO;
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

	public Integer getID_CORROSAO_CAB() { return ID_CORROSAO_CAB; }
	public void setID_CORROSAO_CAB(Integer v) { ID_CORROSAO_CAB = v; }
	public Integer getID_RELATORIO() { return ID_RELATORIO; }
	public void setID_RELATORIO(Integer v) { ID_RELATORIO = v; }
	public Integer getID_OPERADOR() { return ID_OPERADOR; }
	public void setID_OPERADOR(Integer v) { ID_OPERADOR = v; }
	public Date getDATA_CACL2() { return DATA_CACL2; }
	public void setDATA_CACL2(Date v) { DATA_CACL2 = v; }
	public Boolean getCACL2_RENAULT() { return CACL2_RENAULT; }
	public void setCACL2_RENAULT(Boolean v) { CACL2_RENAULT = v; }
	public Boolean getCACL2_PEUGEOT() { return CACL2_PEUGEOT; }
	public void setCACL2_PEUGEOT(Boolean v) { CACL2_PEUGEOT = v; }
	public Boolean getCACL2_JAPAO() { return CACL2_JAPAO; }
	public void setCACL2_JAPAO(Boolean v) { CACL2_JAPAO = v; }
	public Integer getID_CONJ_EQUIP_CACL2() { return ID_CONJ_EQUIP_CACL2; }
	public void setID_CONJ_EQUIP_CACL2(Integer v) { ID_CONJ_EQUIP_CACL2 = v; }
	public Integer getID_CONJ_EQUIP_NSS() { return ID_CONJ_EQUIP_NSS; }
	public void setID_CONJ_EQUIP_NSS(Integer v) { ID_CONJ_EQUIP_NSS = v; }
	public String getRESULTADO_TOTAL_CACL2() { return RESULTADO_TOTAL_CACL2; }
	public void setRESULTADO_TOTAL_CACL2(String v) { RESULTADO_TOTAL_CACL2 = v; }
	public String getRESULTADO_TOTAL_NSS() { return RESULTADO_TOTAL_NSS; }
	public void setRESULTADO_TOTAL_NSS(String v) { RESULTADO_TOTAL_NSS = v; }
	public String getOBSERVACOES() { return OBSERVACOES; }
	public void setOBSERVACOES(String v) { OBSERVACOES = v; }
	public Integer getID_ACCESS_LEGADO() { return ID_ACCESS_LEGADO; }
	public void setID_ACCESS_LEGADO(Integer v) { ID_ACCESS_LEGADO = v; }
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