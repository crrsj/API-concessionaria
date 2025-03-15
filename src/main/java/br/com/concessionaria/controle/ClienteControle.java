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

import br.com.concessionaria.dto.AtualizarCliente;
import br.com.concessionaria.dto.BuscarCliente;
import br.com.concessionaria.dto.CadastroCliente;
import br.com.concessionaria.servico.ClienteServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteControle {

	public final ClienteServico clienteServico;
	private final ModelMapper modelMapper;
	
	
	@PostMapping
	@Operation(summary = "Endpoint responsável por cadastrar cliente.") 
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<CadastroCliente>cadastrarCliente(@RequestBody @Valid CadastroCliente cadastroCliente){
		var cliente = clienteServico.cadastrarCliente(cadastroCliente);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.map(cliente, CadastroCliente.class));
	}
	
	
	@PutMapping("/{id}")
	@Operation(summary = "Endpoint responsável por atualizar cliente pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<AtualizarCliente>atualizarClientes(@RequestBody @Valid AtualizarCliente atualizarCliente,
			                                                 @PathVariable Long id){
		var atualizar = clienteServico.atualizarClientes(atualizarCliente, id);
		return ResponseEntity.ok().body(modelMapper.map(atualizar, AtualizarCliente.class));
	}
	
	@GetMapping
	@Operation(summary = "Endpoint responsável por buscar clientes, contém paginação.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<List<BuscarCliente>> buscarClientes(
	        @RequestParam(defaultValue = "0") int pagina,
	        @RequestParam(defaultValue = "5") int tamanho) {
	        var paginacao = PageRequest.of(pagina, tamanho);
	        List<BuscarCliente> clientes = clienteServico.buscarClientes(paginacao).getContent();
	        return ResponseEntity.ok(clientes);
	}
	
	
	@GetMapping("busca/{id}")
	@Operation(summary = "Endpoint responsável por buscar cliente pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<BuscarCliente>buscarPorId(@PathVariable Long id){
		var busca = clienteServico.buscarPorId(id);
		return ResponseEntity.ok().body(modelMapper.map(busca, BuscarCliente.class));
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Endpoint responsável por deletar cliente pelo id.") 
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Void>excluirCliente(@PathVariable Long id){
		clienteServico.excluirCliente(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	@GetMapping("/buscaCpf")
	@Operation(summary = "Endpoint responsável por buscar cliente pelo cpf.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<BuscarCliente>buscarPorCpf(@RequestParam(name="cpf")String cpf){		
	var buscar = clienteServico.buscarPorCpf(cpf);
		 return ResponseEntity.ok(modelMapper.map(buscar, BuscarCliente.class));
	}
	
}
