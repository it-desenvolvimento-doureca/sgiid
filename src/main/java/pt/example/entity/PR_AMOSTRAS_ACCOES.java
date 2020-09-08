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
@Table(name = "PR_AMOSTRAS_ACCOES")
public class PR_AMOSTRAS_ACCOES {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_AMOSTRA_ACCAO")
	private Integer ID_AMOSTRA_ACCAO;
	@Column(name = "ID_ACCAO")
	private Integer ID_ACCAO;
	@Column(name = "ID_AMOSTRA")
	private Integer ID_AMOSTRA;
	@Column(name = "RESPONSAVEL")
	private Integer RESPONSAVEL;
	@Column(name = "TIPO_RESPONSAVEL")
	private String TIPO_RESPONSAVEL;
	@Column(name = "DATA_ACCAO")
	private Date DATA_ACCAO;
	@Column(name = "HORA_ACCAO")
	private Time HORA_ACCAO;
	public Integer getID_AMOSTRA_ACCAO() {
		return ID_AMOSTRA_ACCAO;
	}
	public Integer getID_ACCAO() {
		return ID_ACCAO;
	}
	public Integer getID_AMOSTRA() {
		return ID_AMOSTRA;
	}
	public Integer getRESPONSAVEL() {
		return RESPONSAVEL;
	}
	public String getTIPO_RESPONSAVEL() {
		return TIPO_RESPONSAVEL;
	}
	public Date getDATA_ACCAO() {
		return DATA_ACCAO;
	}
	public Time getHORA_ACCAO() {
		return HORA_ACCAO;
	}
	public void setID_AMOSTRA_ACCAO(Integer iD_AMOSTRA_ACCAO) {
		ID_AMOSTRA_ACCAO = iD_AMOSTRA_ACCAO;
	}
	public void setID_ACCAO(Integer iD_ACCAO) {
		ID_ACCAO = iD_ACCAO;
	}
	public void setID_AMOSTRA(Integer iD_AMOSTRA) {
		ID_AMOSTRA = iD_AMOSTRA;
	}
	public void setRESPONSAVEL(Integer rESPONSAVEL) {
		RESPONSAVEL = rESPONSAVEL;
	}
	public void setTIPO_RESPONSAVEL(String tIPO_RESPONSAVEL) {
		TIPO_RESPONSAVEL = tIPO_RESPONSAVEL;
	}
	public void setDATA_ACCAO(Date dATA_ACCAO) {
		DATA_ACCAO = dATA_ACCAO;
	}
	public void setHORA_ACCAO(Time hORA_ACCAO) {
		HORA_ACCAO = hORA_ACCAO;
	}
	
	
}
