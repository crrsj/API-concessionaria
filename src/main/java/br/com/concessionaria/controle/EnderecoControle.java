package br.com.concessionaria.controle;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.concessionaria.dto.AtualizarEnderecos;
import br.com.concessionaria.dto.CadastroEndereco;
import br.com.concessionaria.servico.EnderecoServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class EnderecoControle {

	private final EnderecoServico enderecoServico;
	private final ModelMapper modelMapper;
	
	
	@PostMapping("/{clienteId}")
	@Operation(summary = "Endpoint responsável por buscar endereço passanso o id do cliente.") 
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<CadastroEndereco>cadastrarEndereco(@RequestBody @Valid  CadastroEndereco cadastroEndereco,
			                                                 @PathVariable Long clienteId ){
		var cadastrar = enderecoServico.cadastrarEndereco(cadastroEndereco, clienteId);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cadastrar.getId()).toUri();
		return ResponseEntity.created(uri).body(modelMapper.map(cadastrar, CadastroEndereco.class));
	}
	
	
	@PutMapping("/{id}")
	@Operation(summary = "Endpoint responsável por atualizar endereço pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<AtualizarEnderecos>atualizarEndereco(@RequestBody @Valid AtualizarEnderecos atualizarEnderecos,
			                                                   @PathVariable Long id){
		var atualizar = enderecoServico.atualizarEndereco(atualizarEnderecos, id);
			return ResponseEntity.ok(modelMapper.map(atualizar, AtualizarEnderecos.class));
		}
	
}
