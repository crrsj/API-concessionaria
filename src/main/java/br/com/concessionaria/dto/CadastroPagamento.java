package br.com.concessionaria.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.concessionaria.entidade.Veiculo;
import br.com.concessionaria.enums.FormaPagamento;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CadastroPagamento {
	private String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	private FormaPagamento formaPagamento;
	@NotNull(message="não pode ser nulo")
	private Integer parcelas;
	private String financiadora;
	@NotNull(message="não pode ser nulo")
	private Integer diaPagamento;	
	private Double valorEntrada;
	@NotNull(message="não pode ser nulo")
	private Double total;
	private Veiculo veiculo;
}
