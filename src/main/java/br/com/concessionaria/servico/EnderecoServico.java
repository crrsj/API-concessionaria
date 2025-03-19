package br.com.concessionaria.servico;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.concessionaria.dto.AtualizarEnderecos;
import br.com.concessionaria.dto.CadastroEndereco;
import br.com.concessionaria.entidade.Endereco;
import br.com.concessionaria.repositorio.ClienteRepositorio;
import br.com.concessionaria.repositorio.EnderecoRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoServico {
	
	private final EnderecoRepositorio enderecoRepositorio;
	private final ModelMapper modelMapper;
	private final ClienteRepositorio clienteRepositorio;
	
	public Endereco cadastrarEndereco (CadastroEndereco endereco,Long clienteId) {			
		var cliente = clienteRepositorio.findById(clienteId).orElseThrow();
		var  cadastro = modelMapper.map(endereco, Endereco.class);
		cadastro.setCliente(cliente);
		return enderecoRepositorio.save(cadastro);
	}
	
	
	
	public Endereco atualizarEndereco(AtualizarEnderecos enderecos,Long id) {		
	    enderecos.setId(id);
		return enderecoRepositorio.save(modelMapper.map(enderecos, Endereco.class));
	}

}
