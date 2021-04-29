package com.java.exceptions;

/**
 * Classe para indicar a exceção de que a conta não existe
 */
public class ContaInexistenteException extends Exception{
    
    public ContaInexistenteException(String mensagem){
		super(mensagem);
	} 
}
