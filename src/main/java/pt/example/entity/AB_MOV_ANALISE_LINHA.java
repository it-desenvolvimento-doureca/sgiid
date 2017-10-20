package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AB_MOV_ANALISE_LINHA")
public class AB_MOV_ANALISE_LINHA {

	private Integer ID_ANALISE_LIN;
	private Integer ID_ANALISE;
	private Integer ID_COMPONENTE;
	private Float RESULTADO;
	private Float CALCULO;
	private String SINAL;

	@Id
	@Column(name = "ID_ANALISE_LIN")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_ANALISE_LIN() {
		return ID_ANALISE_LIN;
	}

	@Column(name = "ID_ANALISE")
	public Integer getID_ANALISE() {
		return ID_ANALISE;
	}

	@Column(name = "RESULTADO")
	public Float getRESULTADO() {
		return RESULTADO;
	}

	@Column(name = "CALCULO")
	public Float getCALCULO() {
		return CALCULO;
	}

	@Column(name = "ID_COMPONENTE")
	public Integer getID_COMPONENTE() {
		return ID_COMPONENTE;
	}

	@Column(name = "SINAL")
	public String getSINAL() {
		return SINAL;
	}

	public void setSINAL(String sINAL) {
		SINAL = sINAL;
	}

	public void setID_COMPONENTE(Integer iD_COMPONENTE) {
		ID_COMPONENTE = iD_COMPONENTE;
	}

	public void setID_ANALISE_LIN(Integer iD_ANALISE_LIN) {
		ID_ANALISE_LIN = iD_ANALISE_LIN;
	}

	public void setID_ANALISE(Integer iD_ANALISE) {
		ID_ANALISE = iD_ANALISE;
	}

	public void setRESULTADO(Float rESULTADO) {
		RESULTADO = rESULTADO;
	}

	public void setCALCULO(Float cALCULO) {
		CALCULO = cALCULO;
	}

}
