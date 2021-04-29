package com.java.exceptions;

/**
 * Classe para indicar a exceção de que a conta já existe
 */
public class ContaJaExistenteException extends Exception{
    
    public ContaJaExistenteException(String mensagem){
		super(mensagem);
	}
}
