package br.com.concessionaria.entidade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.concessionaria.dto.AtualizarPGTO;
import br.com.concessionaria.enums.FormaPagamento;
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
@Table(name = "tb_pagamentos")
@Data
@NoArgsConstructor
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	private FormaPagamento formaPagamento;
	private Integer parcelas;
	private String financiadora;
	private Integer diaPagamento;
	private Double valorEntrada;
	private Double total;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "veiculo_id")
	private Veiculo veiculo;
	
	public void atualizando(AtualizarPGTO atualizarPGTO) {
		if(atualizarPGTO.getFormaPagamento()!= null) {
			this.formaPagamento = atualizarPGTO.getFormaPagamento();
		}
		
		if(atualizarPGTO.getParcelas()!= null) {
			this.parcelas = atualizarPGTO.getParcelas();
		}
		
		if(atualizarPGTO.getFinanciadora()!= null) {
			this.financiadora = atualizarPGTO.getFinanciadora();
		}
		
		if(atualizarPGTO.getDiaPagamento()!= null) {
			this.diaPagamento = atualizarPGTO.getDiaPagamento();
		}
		
		if(atualizarPGTO.getValorEntrada()!= null) {
			this.valorEntrada = atualizarPGTO.getValorEntrada();
			
		}
		
		if(atualizarPGTO.getTotal()!= null) {
			this.total = atualizarPGTO.getTotal();
		}
	}
	
}
