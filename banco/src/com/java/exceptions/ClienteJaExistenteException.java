package com.java.exceptions;

/**
 * Classe para indicar a exceção de que o cliente já existe
 */
public class ClienteJaExistenteException extends Exception{

    public ClienteJaExistenteException(String mensagem){
		super(mensagem);
	}
}
