package br.com.concessionaria.dto;

import java.util.List;

import br.com.concessionaria.entidade.Pagamento;
import br.com.concessionaria.enums.Marca;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuscarVeiculo {

	private Long id;
	private Marca marca;
	private String modelo;
	private String motor;
	private Integer anoFab;
	private Integer anoMod;
	private Integer anosGarantia;	
	private String placa;
	private String acessorios;
	private Double valorFipe;
	private List<Pagamento>pagamento;
	
}
