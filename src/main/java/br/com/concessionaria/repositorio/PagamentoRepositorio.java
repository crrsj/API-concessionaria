package br.com.concessionaria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.concessionaria.entidade.Pagamento;

public interface PagamentoRepositorio extends JpaRepository<Pagamento, Long>{

}
