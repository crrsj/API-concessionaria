package br.com.concessionaria.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.concessionaria.entidade.Veiculo;

public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long>{
		
	  @Query("SELECT v FROM Veiculo v WHERE v.placa LIKE CONCAT('%', :placa, '%')")
	  Optional<Veiculo>findByPlaca(@Param("placa") String placa);

	   boolean existsByPlaca(String placa);

}
