package org.sid.SpringMvc.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Patient {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min=3,max=15) // on peut ajouter l'attribut message pour le personnaliser
	private String name;
	@Temporal(TemporalType.DATE) // JPA et non pas Spring
	@DateTimeFormat(pattern= "yyyy-MM-dd") 
	private Date dateNaissance;
	private boolean malade;
	@DecimalMin("4")
	private int score;
	
}
