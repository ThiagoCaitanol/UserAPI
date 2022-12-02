package br.com.esales.user.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_ENDERECO")
public class Address {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CEP")
	private String cep;
	
	@Column(name = "ENDERECO")
	private String address;
	
	@Column(name = "NUMERO")
	private String number;
		
	@Column(name = "COMPLEMENTO")
	private String complement;
	
	@Column(name = "BAIRRO")
	private String district;
	
	@Column(name = "CIDADE")
	private String city;
	
	@Column(name = "UF")
	private String uf;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USUARIO_ID")
	private User user;
}
