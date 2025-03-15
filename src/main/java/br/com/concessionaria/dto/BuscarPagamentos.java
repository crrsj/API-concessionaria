package br.com.concessionaria.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.concessionaria.entidade.Veiculo;
import br.com.concessionaria.enums.FormaPagamento;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class BuscarPagamentos {
	private Long id;
	private String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	private FormaPagamento formaPagamento;
	private Integer parcelas;
	private String financiadora;
	private Integer diaPagamento;
	private Double valorEntrada;
	private Double total;
	private Veiculo veiculo;
}
