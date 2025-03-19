package br.com.concessionaria.servico;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.concessionaria.dto.AtualizarPGTO;
import br.com.concessionaria.dto.BuscarPagamentos;
import br.com.concessionaria.dto.CadastroPagamento;
import br.com.concessionaria.entidade.Pagamento;
import br.com.concessionaria.repositorio.PagamentoRepositorio;
import br.com.concessionaria.repositorio.VeiculoRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagamentoServico {
	
	private  final ModelMapper modelMapper;
	private final VeiculoRepositorio veiculoRepositorio;
	private final PagamentoRepositorio pagamentoRepositorio;
 
	public Pagamento cadastrarPagamento(CadastroPagamento pagamentos,Long veiculoId) {
		var veiculo = veiculoRepositorio.findById(veiculoId).orElseThrow();
		var pagamento = modelMapper.map(pagamentos, Pagamento.class);
		pagamento.setVeiculo(veiculo);
		return pagamentoRepositorio.save(pagamento);
	}
	
	public Page<BuscarPagamentos> buscarPagamentos(Pageable pageable){
		return pagamentoRepositorio.findAll(pageable)
			    .map(buscar -> modelMapper
				.map(buscar, BuscarPagamentos.class));
	}
	
	public Pagamento buscarPorId(Long id) {
		Optional<Pagamento>buscar = pagamentoRepositorio.findById(id);
		return buscar.orElseThrow();
	}
	
	public Pagamento atualizarPagamento(AtualizarPGTO atualizarPGTO,Long id) {
		atualizarPGTO.setId(id);
		 return pagamentoRepositorio.save(modelMapper.map(atualizarPGTO, Pagamento.class));
		
	}
	
	public void excluirPagamento(Long id) {
		buscarPorId(id);
	}
	
}
