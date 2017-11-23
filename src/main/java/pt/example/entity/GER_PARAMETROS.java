package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GER_PARAMETROS")
public class GER_PARAMETROS {

	private Integer ID;
	private String PASTA_FICHEIRO;
	private String URL_SILVER;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	@Column(name = "PASTA_FICHEIRO")
	public String getPASTA_FICHEIRO() {
		return PASTA_FICHEIRO;
	}

	public void setPASTA_FICHEIRO(String pASTA_FICHEIRO) {
		PASTA_FICHEIRO = pASTA_FICHEIRO;
	}

	@Column(name = "URL_SILVER")
	public String getURL_SILVER() {
		return URL_SILVER;
	}

	public void setURL_SILVER(String uRL_SILVER) {
		URL_SILVER = uRL_SILVER;
	}

}
