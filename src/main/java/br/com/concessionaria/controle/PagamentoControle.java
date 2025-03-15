package br.com.concessionaria.controle;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.concessionaria.dto.AtualizarPGTO;
import br.com.concessionaria.dto.BuscarPagamentos;
import br.com.concessionaria.dto.CadastroPagamento;
import br.com.concessionaria.servico.PagamentoServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pagamento")
@RequiredArgsConstructor
public class PagamentoControle {
	
	private final PagamentoServico pagamentoServico;
	private final ModelMapper modelMapper;
	
	
	
	@PostMapping("/{veiculoId}")
	@Operation(summary = "Endpoint responsável por cadastrar pagamento pelo id do veículo.") 
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<CadastroPagamento>cadastrarPagamento(@RequestBody @Valid CadastroPagamento cadastroPagamento,
			                                                    @PathVariable Long veiculoId){
		var cadastrar= pagamentoServico.cadastrarPagamento(cadastroPagamento, veiculoId);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cadastrar.getId()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.map(cadastrar, CadastroPagamento.class));
	}
	
	@GetMapping
	@Operation(summary = "Endpoint responsável buscar pagamentos, contém paginação .") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<List<BuscarPagamentos>>buscarPagamentos(
			@RequestParam(defaultValue = "0")int pagina,
			@RequestParam(defaultValue = "5")int tamanho){
	    var paginar = PageRequest.of(pagina, tamanho);
        List<BuscarPagamentos> paginacao = pagamentoServico.buscarPagamentos(paginar).getContent();
        return ResponseEntity.ok(paginacao);
   }
	
	@GetMapping("/{id}")
	@Operation(summary = "Endpoint responsável por buscar pagamento pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<BuscarPagamentos>buscarPorId(@PathVariable Long id){
		var buscar = pagamentoServico.buscarPorId(id);
		return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarPagamentos.class));
	}
	
	
	@PutMapping("/{id}")
	@Operation(summary = "Endpoint responsável por atualizar pagamentopelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<AtualizarPGTO>atualizarPagamentos(@RequestBody @Valid AtualizarPGTO atualizarPgto,
			                                                @PathVariable Long id){
		var atualizar = pagamentoServico.atualizarPagamento(atualizarPgto, id);
		return ResponseEntity.ok(modelMapper.map(atualizar, AtualizarPGTO.class));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Endpoint responsável por deletaar pagamento pelo id.") 
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Void>excluirPagamentos(@PathVariable Long id){
		 pagamentoServico.excluirPagamento(id);
		 return ResponseEntity.noContent().build();
		
	}
	
	
}
