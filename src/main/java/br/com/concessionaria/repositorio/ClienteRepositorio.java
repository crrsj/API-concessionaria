package br.com.concessionaria.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import br.com.concessionaria.entidade.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long>{

	@Query("SELECT c FROM Cliente c WHERE c.cpf LIKE CONCAT('%', :cpf, '%')")
	Optional<Cliente> findByCpf(@Param("cpf") String cpf);
	

}
