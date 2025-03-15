package br.com.concessionaria.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AtualizarCliente {
	private Long id;
	private String dataCadastro = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	@NotBlank(message=" não pode estar em branco !")
    private String nome;
	@CPF
	private String cpf;
	@NotBlank(message="não pode estar em branco !")
	private String telefone;
	@Email
	private String email;
	@NotBlank(message="não pode estar em branco !")
	private String profissao;
	@NotNull(message= "não pode estar em branco !")
	private Double olerite;
}
