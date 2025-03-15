package br.com.concessionaria.dto;

import br.com.concessionaria.entidade.Cliente;
import br.com.concessionaria.enums.Marca;
import br.com.concessionaria.erros.TratarErros.PlacaUnica;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CadastroVeiculo {
	
	private Marca marca;
	@NotBlank(message=" não pode estar em branco !")
	private String modelo;
	@NotBlank(message=" não pode estar em branco !")
	private String motor;
	@NotNull(message="não pode ser nulo!")
	private Integer anoFab;
	@NotNull(message="não pode ser nulo!")
	private Integer anoMod;
	private Integer anosGarantia;	
	@NotBlank(message = "A placa é obrigatória")
    @Pattern(
        regexp = "^[A-Z]{3}-?[0-9]{4}$|^[A-Z]{3}[0-9][A-Z][0-9]{2}$",
        message = "Placa inválida! Use o formato 'AAA-1234' ou 'AAA1B23'"
    )	
    @PlacaUnica
	private String placa;
	@NotBlank(message="não pode estar em branco !")
	private String acessorios;
	@NotNull(message="não pode ser nulo!")
	private Double valorFipe;
	private Cliente cliente;

}
