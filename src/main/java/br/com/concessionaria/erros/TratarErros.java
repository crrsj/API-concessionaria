package br.com.concessionaria.erros;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.NoSuchElementException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.concessionaria.dto.MensagemDeErro;
import br.com.concessionaria.infra.PlacaUnicaValidacao;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@ControllerAdvice
public class TratarErros {

	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<MensagemDeErro>idNaoEncontrado(){
		var erros = new MensagemDeErro(HttpStatus.NOT_FOUND,"Objeto não encontrado");
		return new ResponseEntity<>(erros,HttpStatus.NOT_FOUND);
	}
	 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>validarCampos(MethodArgumentNotValidException ex){
		var validar = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(validar.stream().map(ValidandoCampos::new));
	}
	
	@Constraint(validatedBy = PlacaUnicaValidacao.class)
	@Target({ElementType.FIELD})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface PlacaUnica {
	    String message() default "A placa já está cadastrada!";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
	}
}
