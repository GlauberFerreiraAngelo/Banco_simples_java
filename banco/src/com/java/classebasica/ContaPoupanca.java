package com.java.classebasica;

import com.java.exceptions.ValorNegativoException;

/**
 * Classe Conta Poupança extendida da Classe Conta, com a diferença 
 * do método render juros
 */
public class ContaPoupanca extends Conta{
    
    /**
	 * Construtor da conta poupança
	 */
	public ContaPoupanca(String agencia, double saldo, Cliente cliente){
			super(agencia,saldo,cliente);
	}
	
    /**
	 *Método render juros 
	 * @throws ValorNegativoException 
	 */
	public void renderJuros(double taxa) throws ValorNegativoException{
		double saldo = this.getSaldo();
		this.creditar(saldo*taxa);
	}
}
