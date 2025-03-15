package br.com.concessionaria.dto;

import br.com.concessionaria.entidade.Cliente;
import br.com.concessionaria.enums.Marca;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AtualizarVeiculos {

	private Long id;
	private Marca marca;
	@NotBlank( message = "não pode estar em branco !")
	private String modelo;
	@NotBlank( message = "não pode estar em branco !")
	private String motor;
	@NotNull(message = "não pode ser nulo !")
	private Integer anofab;
	@NotNull(message = "não pode ser nulo !")
	private Integer anoMod;
	@NotNull(message = "não pode ser nulo !")
	private Integer anosGarantia;	
	@NotBlank( message = "não pode estar em branco !")
	private String placa;
	@NotBlank( message = "não pode estar em branco !")
	private String acessorios;
	@NotNull(message = "não pode ser nulo !")
	private Double valorFipe; 	
	private Cliente cliente;
}
