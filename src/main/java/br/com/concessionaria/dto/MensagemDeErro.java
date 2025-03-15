package br.com.concessionaria.dto;

import org.springframework.http.HttpStatus;

public record MensagemDeErro(HttpStatus status, String mensagem) {

}
