package com.java.exceptions;

/**
 * Classe para indicar a exceção de que a conta é inválida para 
 * efetuar a operação
 */
public class TipoContaInvalidaException extends Exception{
    
    public TipoContaInvalidaException(String messagem){
		super(messagem);
	}
}
