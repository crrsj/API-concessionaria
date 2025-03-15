package br.com.concessionaria.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.concessionaria.entidade.Endereco;
import br.com.concessionaria.entidade.Veiculo;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BuscarCliente {
	private Long id;
	private String dataCadastro = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	private String nome;	
	private String cpf;
	private String telefone;
	private String email;
	private String profissao;
	private Double olerite;
	private List<Veiculo>veiculo;
	private List<Endereco>endereco;
	
	}
