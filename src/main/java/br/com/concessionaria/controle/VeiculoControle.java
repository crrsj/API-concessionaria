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

import br.com.concessionaria.dto.AtualizarVeiculos;
import br.com.concessionaria.dto.BuscarVeiculo;
import br.com.concessionaria.dto.CadastroVeiculo;
import br.com.concessionaria.servico.VeiculoServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/veiculo")
@RequiredArgsConstructor
public class VeiculoControle {

	
	private final VeiculoServico veiculoServico;
	private final ModelMapper modelMapper;
	
	
	@PostMapping("/{clienteId}")
	@Operation(summary = "Endpoint responsável por cadastrar cliente paelo id do cliente.") 
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<CadastroVeiculo>cadastrarVeiculo(@RequestBody  @Valid CadastroVeiculo cadastroVeiculo,
			                                               @PathVariable Long clienteId){
		var cadastrar = veiculoServico.cadastrarVeiculo(cadastroVeiculo, clienteId);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cadastrar.getId()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.map(cadastrar, CadastroVeiculo.class));
	}
	
	@GetMapping
	@Operation(summary = "Endpoint responsável por buscar veículos,contém paginação.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<List<BuscarVeiculo>>buscarVeiculos(
			@RequestParam(defaultValue = "0")int pagina,
			@RequestParam(defaultValue = "5")int tamanho){
	    var paginacao = PageRequest.of(pagina, tamanho);
        List<BuscarVeiculo> listas = veiculoServico.buscarVeiculos(paginacao).getContent();
        return ResponseEntity.ok(listas);
		
	}
	
	
	@GetMapping("/buscarPlaca")
	@Operation(summary = "Endpoint responsável por buscar o veículo pela placa.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<BuscarVeiculo>buscarPorPlaca(@RequestParam("placa")String placa){
		var buscar = veiculoServico.buscarPorPlaca(placa);
		return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarVeiculo.class));
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Endpoint responsável por excluir veículo.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Void>excluirVeiculo(@PathVariable Long id){
		veiculoServico.excluirVeiculo(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Endpoint responsável por buscar veiculo pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<BuscarVeiculo>buscarPorId(@PathVariable Long id){
		var buscar = veiculoServico.buscarPorId(id);
		return ResponseEntity.ok().body(modelMapper.map(buscar, BuscarVeiculo.class));
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Endpoint responsável por atualizar veiculo pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<AtualizarVeiculos>atualizarVeiculo(@RequestBody @Valid AtualizarVeiculos atualizarVeiculos,
			                                                  @PathVariable Long id){
		var atualizar = veiculoServico.atualizarVeiculo(atualizarVeiculos, id);
		return ResponseEntity.ok(modelMapper.map(atualizar, AtualizarVeiculos.class));
	}
}
