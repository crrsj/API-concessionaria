package br.com.concessionaria.servico;


import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.concessionaria.dto.AtualizarCliente;
import br.com.concessionaria.dto.BuscarCliente;
import br.com.concessionaria.dto.CadastroCliente;
import br.com.concessionaria.entidade.Cliente;
import br.com.concessionaria.repositorio.ClienteRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServico {

	private final ClienteRepositorio clienteRepositorio;
	private final ModelMapper modelMapper;
	
	public Cliente cadastrarCliente(CadastroCliente cadastroCliente) {		
		return  clienteRepositorio.save(modelMapper.map(cadastroCliente, Cliente.class));
		
	}
	
	public Page<BuscarCliente> buscarClientes(Pageable pageable) {
	    return clienteRepositorio.findAll(pageable)
	            .map(cliente -> modelMapper.map(cliente, BuscarCliente.class));
	}
	
	public Cliente buscarPorId(Long id) {
		Optional<Cliente>buscar = clienteRepositorio.findById(id);
		return buscar.orElseThrow();
	}
	
	public Cliente buscarPorCpf(String cpf) {
	   Optional<Cliente> buscar =  clienteRepositorio.findByCpf(cpf);
	   return buscar.orElseThrow();
	  	
	}
	
	
    
	public Cliente atualizarClientes(AtualizarCliente atualizarCliente,Long id) {
		 atualizarCliente.setId(id);     
		return clienteRepositorio.save(modelMapper.map(atualizarCliente,Cliente.class));
	}
	
	public void excluirCliente(Long id) {
		buscarPorId(id);
		clienteRepositorio.deleteById(id);
	}
}
