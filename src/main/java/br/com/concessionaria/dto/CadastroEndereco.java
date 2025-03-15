package br.com.concessionaria.dto;

import br.com.concessionaria.entidade.Cliente;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CadastroEndereco {
	
	@NotBlank(message=" não pode estar em branco !")
	private String rua;
	@NotBlank(message="campo não pode estar em branco !")
	private String numero;
	@NotBlank(message="campo não pode estar em branco !")
	private String bairro;
	private String complemento;
	@NotBlank(message="campo não pode estar em branco !")
	private String cidade;
	@NotBlank(message="não pode estar em branco !")
	private String estado;
	@NotBlank(message=" não pode estar em branco !")
	private String cep;
	private Cliente cliente;
}
