package br.com.concessionaria.entidade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_clientes")
@Data
@NoArgsConstructor
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String dataCadastro = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	private String nome;	
	private String cpf;
	private String telefone;	
	private String email;
	private String profissao;
	private Double olerite;
	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL,orphanRemoval = true)	
	private List<Veiculo>veiculo;
	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL,orphanRemoval = true)	
	private List<Endereco>endereco;
	
	
	}
	
	


