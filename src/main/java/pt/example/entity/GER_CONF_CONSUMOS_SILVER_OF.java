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
@Table(name = "GER_CONF_CONSUMOS_SILVER_OF")
public class GER_CONF_CONSUMOS_SILVER_OF {

	private Integer ID_CONF_OF;
	private Integer ID_CONF;
	private String OF_NUM;
	private String TIPO;
	private Date DATA;

	@Id
	@Column(name = "ID_CONF_OF")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_CONF_OF() {
		return ID_CONF_OF;
	}

	public void setID_CONF_OF(Integer iD_CONF_OF) {
		ID_CONF_OF = iD_CONF_OF;
	}

	@Column(name = "ID_CONF")
	public Integer getID_CONF() {
		return ID_CONF;
	}

	@Column(name = "OF_NUM")
	public String getOF_NUM() {
		return OF_NUM;
	}

	public void setID_CONF(Integer iD_CONF) {
		ID_CONF = iD_CONF;
	}

	public void setOF_NUM(String oF_NUM) {
		OF_NUM = oF_NUM;
	}

	@Column(name = "DATA")
	public Date getDATA() {
		return DATA;
	}

	@Column(name = "TIPO")
	public String getTIPO() {
		return TIPO;
	}

	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}

	public void setDATA(Date dATA) {
		DATA = dATA;
	}

}
