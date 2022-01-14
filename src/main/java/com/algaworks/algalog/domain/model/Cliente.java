package com.algaworks.algalog.domain.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity 
@Table(name = "cliente") /* Se nao utilizar essa notacao, como padrao eh o proprio nome da classe */
public class Cliente {

	@EqualsAndHashCode.Include
	@Id /* Define a chave primaria*/
	@GeneratedValue(strategy = GenerationType.IDENTITY) /*estrategia de gerecao da chave*/
	private Long id;									// propriedade strategy definindo propriedade 											
														// auto increment / IDENTITY -> forma nativa do BD 	
	@NotBlank //nao so nulo, como tambem nao seja vazio
	@Size(max = 60)//lax 60 caracteres
	private String nome;
	
	@NotBlank
	@Email
	@Size(max = 255)
	private String email;
	
	@NotBlank
	@Size(max = 20)
	@Column(name = "fone") /* Notacao @Column com propriedade name definindo */
	private String telefone;	// o nome da coluna no BD. Caso não seja especificada
								// o padrao eh o nome da classe 

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
