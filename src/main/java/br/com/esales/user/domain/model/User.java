package br.com.esales.user.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.esales.user.core.validation.Groups;
import br.com.esales.user.domain.service.CnpjGroup;
import br.com.esales.user.domain.service.CpfGroup;
import br.com.esales.user.domain.service.PersonType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_USUARIO")

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull(groups = Groups.RegisterTelephone.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name = "NOME")
	private String name;
	 
	@NotBlank
	@Email
	@Column(name = "EMAIL")
	private String email;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_PESSOA")
	private PersonType personType;
	
	@NotBlank
	@CPF(groups = CpfGroup.class)
	@CNPJ(groups = CnpjGroup.class)
	@Column(name = "DOCUMENTO")
	private String cpfCnpj;
	
	@Nullable
	@Column(name = "STATUS")
	private Boolean status;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Telephone> telephones = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Address> address = new ArrayList<>();

}
