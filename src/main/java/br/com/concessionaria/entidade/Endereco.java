package br.com.concessionaria.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.concessionaria.dto.AtualizarEnderecos;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name  = "tb_enderecos")
@NoArgsConstructor
@Data
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rua;
	private String numero;
	private String bairro;
	private String complemento;
	private String cidade;
	private String estado;
	private String cep;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	public void atualizando(AtualizarEnderecos enderecos) {
		
	if(enderecos.getRua()!= null) {
		this.rua = enderecos.getRua();
	}
	
	if(enderecos.getNumero()!= null) {
		this.numero = enderecos.getNumero();
	}
	
	if(enderecos.getBairro()!= null) {
		this.bairro = enderecos.getBairro();
	}
	
	if(enderecos.getComplemento()!= null) {
		this.complemento = enderecos.getComplemento();
	}
	
	if(enderecos.getCidade()!= null) {
		this.cidade = enderecos.getCidade();
	}
		
	if(enderecos.getEstado()!= null) {
		this.estado = enderecos.getEstado();
	}
	
	if(enderecos.getCep()!= null) {
		this.cep = enderecos.getCep();
	}
	}
	
	
	

}
