package pt.example.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PA_MOV_SEGUIR_LINHA")
public class PA_MOV_SEGUIR_LINHA
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ID")
    private Integer ID;
	@JsonProperty("ID_PLANO_LINHA")
    private Integer ID_PLANO_LINHA;
	@JsonProperty("UTILIZADOR")
    private Integer UTILIZADOR;
	@JsonProperty("DATA_CRIA")
    private Timestamp DATA_CRIA;
}