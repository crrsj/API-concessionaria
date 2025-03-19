package br.com.concessionaria.entidade;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.concessionaria.dto.AtualizarVeiculos;
import br.com.concessionaria.enums.Marca;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_veiculos")
@Data
@NoArgsConstructor
public class Veiculo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private Marca marca;
	private String modelo;
	private String motor;
	private Integer anofab;
	private Integer anoMod;
	private Integer anosGarantia;	
	private String placa;
	private String acessorios;
	private Double valorFipe;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	@OneToMany(mappedBy = "veiculo",cascade = CascadeType.ALL,orphanRemoval = true)	
	private List<Pagamento>pagamento;
	
	public void atualizando(AtualizarVeiculos atualizarDto) {
	
		if(atualizarDto.getMarca()!= null) {
			this.marca = atualizarDto.getMarca();
		}
		
		if(atualizarDto.getModelo()!= null) {
			this.modelo = atualizarDto.getModelo();
		}
		
		if(atualizarDto.getMotor()!= null) {
			this.motor  = atualizarDto.getMotor();
		}
		
		if(atualizarDto.getAnofab()!= null) {
			this.anofab = atualizarDto.getAnofab();
		}
		if(atualizarDto.getPlaca()!= null) {
			this.placa   = atualizarDto.getPlaca();
		}
		
		if(atualizarDto.getAnoMod()!= null) {
			this.anoMod = atualizarDto.getAnoMod();
		}
		
		if(atualizarDto.getAnosGarantia()!= null) {
			this.anosGarantia = atualizarDto.getAnosGarantia();
		}
		
		if (atualizarDto.getAcessorios()!= null) {
			this.acessorios = atualizarDto.getAcessorios();
		}
		
		if(atualizarDto.getValorFipe()!= null) {
			this.valorFipe  = atualizarDto.getValorFipe();
		}
	}

}
