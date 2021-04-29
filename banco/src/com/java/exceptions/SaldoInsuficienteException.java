package com.java.exceptions;

/**
 * Classe para indicar a exceção de que o saldo é insuficiente para fazer a operação
 */
public class SaldoInsuficienteException extends Exception{
    
    public SaldoInsuficienteException(String mensagem){
		super(mensagem);
	}
}
