package br.com.concessionaria.infra;

import org.springframework.stereotype.Component;

import br.com.concessionaria.erros.TratarErros.PlacaUnica;
import br.com.concessionaria.repositorio.VeiculoRepositorio;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PlacaUnicaValidacao implements ConstraintValidator<PlacaUnica, String> {
	
	private final VeiculoRepositorio veiculoRepositorio;

	@Override
	public boolean isValid(String placa, ConstraintValidatorContext context) {
		 if (placa == null || placa.trim().isEmpty()) {
	            return true; 
	        }
		return !veiculoRepositorio.existsByPlaca(placa);
	}

}
