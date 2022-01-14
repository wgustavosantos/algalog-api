package com.algaworks.algalog.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algalog.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	/*Suporte Spring Data JPA - Query Method */
	/* É necessario seguir um método de nomenclatura prefixo "find" delimitador "by" expressao "Nome" pois tem a propriedade do tipo
	 * nome na classe Cliente */
	List<Cliente> findByNome (String nome);
	List<Cliente> findByNomeContaining(String nome); /*NomeContaining  */

}
