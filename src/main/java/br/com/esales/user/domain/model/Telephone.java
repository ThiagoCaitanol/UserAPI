package br.com.esales.user.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "TB_TELEFONE")
public class Telephone {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DDD")
	private String ddd;
	
	@Column(name = "NUMERO")
	private String telephoneNumber;
	
//	@Valid
	@ManyToOne
	@JoinColumn(name = "USUARIO_ID")
	private User user;
}
