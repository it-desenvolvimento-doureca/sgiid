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
@Table(name = "AB_DIC_LINHA_OF")
public class AB_DIC_LINHA_OF {

	private Integer ID_LINHA_OF;
	private Integer ID_LINHA;
	private String OF_NUM;
	private Date DATA; 

	@Id
	@Column(name = "ID_LINHA_OF")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID_LINHA_OF() {
		return ID_LINHA_OF;
	}

	public void setID_LINHA_OF(Integer iD_LINHA_OF) {
		ID_LINHA_OF = iD_LINHA_OF;
	}

	@Column(name = "ID_LINHA")
	public Integer getID_LINHA() {
		return ID_LINHA;
	}

	@Column(name = "OF_NUM")
	public String getOF_NUM() {
		return OF_NUM;
	}

	public void setID_LINHA(Integer iD_LINHA) {
		ID_LINHA = iD_LINHA;
	}

	public void setOF_NUM(String oF_NUM) {
		OF_NUM = oF_NUM;
	}

	@Column(name = "DATA")
	public Date getDATA() {
		return DATA;
	}

	public void setDATA(Timestamp dATA) {
		DATA = dATA;
	}

}
