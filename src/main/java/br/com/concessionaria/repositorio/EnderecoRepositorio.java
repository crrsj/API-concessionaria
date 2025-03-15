package br.com.concessionaria.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.concessionaria.entidade.Endereco;

public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {

}
