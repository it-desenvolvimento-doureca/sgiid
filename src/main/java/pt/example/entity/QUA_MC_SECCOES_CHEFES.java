package pt.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import pt.example.entity.PK.QUA_MC_SECCOES_CHEFES_PK;

@Entity
@Table(name = "QUA_MC_SECCOES_CHEFES")
@IdClass(QUA_MC_SECCOES_CHEFES_PK.class)
public class QUA_MC_SECCOES_CHEFES {

	@Id
	@Column(name = "ID_SECCAO")
	@JsonProperty("ID_SECCAO")
	private Integer ID_SECCAO;

	@Id
	@Column(name = "ID_UTILIZADOR")
	@JsonProperty("ID_UTILIZADOR")
	private Integer ID_UTILIZADOR;

	public QUA_MC_SECCOES_CHEFES() { }

	public Integer getID_SECCAO() { return ID_SECCAO; }
	public void setID_SECCAO(Integer iD_SECCAO) { ID_SECCAO = iD_SECCAO; }

	public Integer getID_UTILIZADOR() { return ID_UTILIZADOR; }
	public void setID_UTILIZADOR(Integer iD_UTILIZADOR) { ID_UTILIZADOR = iD_UTILIZADOR; }
}
