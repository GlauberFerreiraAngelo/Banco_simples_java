package com.java.classebasica;

import com.java.exceptions.ValorNegativoException;

/**
 * Classe Contabonificada extendida da classe conta com a diferença 
 * de inserir bonus a esta conta
 */

public class ContaBonificada extends Conta{
    
    private double bonus;

    /**
	 * Construtor da conta bonificada
	 */
	public ContaBonificada(String agencia, double saldo, Cliente cliente){
		super(agencia,saldo,cliente);
	}
	
	/**
	 * Método creditar com bonus
	 * @throws ValorNegativoException 
	 */
	public void creditar(double valor) throws ValorNegativoException{
		bonus = bonus+(valor*0.01);
		super.creditar(valor);
	}
	
	/**
	 * Método render bonus
	 * @throws ValorNegativoException 
	 */
	public void renderBonus() throws ValorNegativoException{
		super.creditar(bonus);
		bonus = 0;
	}
}
