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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.esales.user.core.validation.Groups;
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

	@NotBlank(groups = Groups.RegisterTelephone.class)
	@Column(name = "DDD")
	private String ddd;
	
	@NotBlank(groups = Groups.RegisterTelephone.class)
	@Column(name = "NUMERO")
	private String telephoneNumber;
	
	@Valid
	@NotNull(groups = Groups.RegisterTelephone.class)
	@ManyToOne
	@JoinColumn(name = "USUARIO_ID")
	private User user;
}
