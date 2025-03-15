package br.com.concessionaria.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AtualizarEnderecos {
	private Long id;
	@NotBlank(message = "não pode estar em branco !")
	private String rua;
	@NotBlank(message = "não pode estar em branco !")
	private String numero;
	@NotBlank(message = "não pode estar em branco !")
	private String bairro;
	@NotBlank(message = "não pode estar em branco !")
	private String complemento;
	@NotBlank(message = "não pode estar em branco !")
	private String cidade;
	@NotBlank(message = "não pode estar em branco !")
	private String estado;
	@NotBlank(message = "não pode estar em branco !")
	private String cep;
}
