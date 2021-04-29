package com.java.exceptions;

/**
 * Classe para indicar a exceção de que o cliente não existe
 */
public class ClienteInexistenteException extends Exception{
    
    public ClienteInexistenteException(String mensagem){
		super(mensagem);
	}
}
