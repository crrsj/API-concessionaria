package br.com.concessionaria.servico;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import br.com.concessionaria.dto.AtualizarVeiculos;
import br.com.concessionaria.dto.BuscarVeiculo;
import br.com.concessionaria.dto.CadastroVeiculo;
import br.com.concessionaria.entidade.Veiculo;
import br.com.concessionaria.repositorio.ClienteRepositorio;
import br.com.concessionaria.repositorio.VeiculoRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VeiculoServico {
	
	private final VeiculoRepositorio veiculoRepositorio;
	private final ClienteRepositorio clienteRepositorio;
	private final ModelMapper modelMapper;

	public Veiculo cadastrarVeiculo(CadastroVeiculo veiculoDto,Long clienteId) {
		var cliente = clienteRepositorio.findById(clienteId).orElseThrow();
		var veiculo = modelMapper.map(veiculoDto, Veiculo.class);
		veiculo.setCliente(cliente);
		return veiculoRepositorio.save(veiculo);
	}
	
	public Page<BuscarVeiculo>buscarVeiculos(Pageable pageable) {
		return veiculoRepositorio.findAll(pageable)
				.map(listar -> modelMapper.map(listar, BuscarVeiculo.class));
	}
	
	public Veiculo buscarPorId(Long id) {
		Optional<Veiculo>buscar = veiculoRepositorio.findById(id);
		return buscar.orElseThrow();
	}
	
	public Veiculo atualizarVeiculo(AtualizarVeiculos atualizarDto,Long id) {
		var atualizar = buscarPorId(id);
		atualizar.atualizando(atualizarDto);
		return veiculoRepositorio.save(modelMapper.map(atualizar, Veiculo.class));
		
	}
	public Veiculo buscarPorPlaca(String placa) {
		Optional<Veiculo>buscar = veiculoRepositorio.findByPlaca(placa);
		return buscar.orElseThrow();
	}
	
	public void excluirVeiculo(Long id) {
		buscarPorId(id);
		veiculoRepositorio.deleteById(id);
	}
}
