package pt.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PEDIDOS_APP")
public class PEDIDOS_APP {
	public Integer ID_PEDIDO;
	public String ASSUNTO;
	public Integer PRIORIDADE;
	public String DESCRICAO;
	public Timestamp DATA_HORA_PEDIDO;
	public Integer UTZ_PEDIDO;
	public Timestamp DATA_FECHO;
	public Integer UTZ_FECHO;
	public String ESTADO;
	public String TIPO_PEDIDO;

	public String EMAIL_CONTACTO;
	public String TELEFONE_CONTACTO;

	@Id
	@Column(name = "ID_PEDIDO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_PEDIDO() {
		return ID_PEDIDO;
	}

	@Column(name = "ASSUNTO")
	public String getASSUNTO() {
		return ASSUNTO;
	}

	@Column(name = "PRIORIDADE")
	public Integer getPRIORIDADE() {
		return PRIORIDADE;
	}

	@Column(name = "DESCRICAO")
	public String getDESCRICAO() {
		return DESCRICAO;
	}

	@Column(name = "DATA_HORA_PEDIDO")
	public Timestamp getDATA_HORA_PEDIDO() {
		return DATA_HORA_PEDIDO;
	}

	@Column(name = "UTZ_PEDIDO")
	public Integer getUTZ_PEDIDO() {
		return UTZ_PEDIDO;
	}

	@Column(name = "DATA_FECHO")
	public Timestamp getDATA_FECHO() {
		return DATA_FECHO;
	}

	@Column(name = "UTZ_FECHO")
	public Integer getUTZ_FECHO() {
		return UTZ_FECHO;
	}

	@Column(name = "ESTADO")
	public String getESTADO() {
		return ESTADO;
	}

	public void setID_PEDIDO(Integer iD_PEDIDO) {
		ID_PEDIDO = iD_PEDIDO;
	}

	public void setASSUNTO(String aSSUNTO) {
		ASSUNTO = aSSUNTO;
	}

	public void setPRIORIDADE(Integer pRIORIDADE) {
		PRIORIDADE = pRIORIDADE;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public void setDATA_HORA_PEDIDO(Timestamp dATA_HORA_PEDIDO) {
		DATA_HORA_PEDIDO = dATA_HORA_PEDIDO;
	}

	public void setUTZ_PEDIDO(Integer uTZ_PEDIDO) {
		UTZ_PEDIDO = uTZ_PEDIDO;
	}

	public void setDATA_FECHO(Timestamp dATA_FECHO) {
		DATA_FECHO = dATA_FECHO;
	}

	public void setUTZ_FECHO(Integer uTZ_FECHO) {
		UTZ_FECHO = uTZ_FECHO;
	}

	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}

	@Column(name = "TIPO_PEDIDO")
	public String getTIPO_PEDIDO() {
		return TIPO_PEDIDO;
	}

	public void setTIPO_PEDIDO(String tIPO_PEDIDO) {
		TIPO_PEDIDO = tIPO_PEDIDO;
	}

	@Column(name = "EMAIL_CONTACTO")
	public String getEMAIL_CONTACTO() {
		return EMAIL_CONTACTO;
	}

	@Column(name = "TELEFONE_CONTACTO")
	public String getTELEFONE_CONTACTO() {
		return TELEFONE_CONTACTO;
	}

	public void setEMAIL_CONTACTO(String eMAIL_CONTACTO) {
		EMAIL_CONTACTO = eMAIL_CONTACTO;
	}

	public void setTELEFONE_CONTACTO(String tELEFONE_CONTACTO) {
		TELEFONE_CONTACTO = tELEFONE_CONTACTO;
	}

}
