package com.java.classebasica;

import com.java.exceptions.SaldoInsuficienteException;
import com.java.exceptions.ValorNegativoException;

public class Conta {
    
    /**
	 * atributos da classe conta
	 */
	private String numero;
	private String agencia;
	private double saldo;
	private Cliente cliente;
	private static int proximoNumero = 1;

    /** 
	 * Construtor de conta
	 * */
	public Conta(String agencia, double saldo, Cliente cliente){
		this.numero = "" + proximoNumero++;
		this.agencia = agencia;
		this.saldo = saldo;
		this.cliente = cliente;
	}
	
	/**
	 * Sets e gets da classe conta
	 */
	
	public static void setProximoNumero(int proximoNumero){
		Conta.proximoNumero = proximoNumero;
	} 
	
	public static int getProximoNumero(){
		return proximoNumero;
	}

	/**	 
	 * inserir um novo valor de numero
	 **/
	public void setNumero(String numero){
		this.numero = numero;
	}
	/**	
	 *  pega o numero do cliente 
	 * */
	public String getNumero(){
		return numero;
	}
	/**
	 * 	inserir nome da agencia
	 * */
	public void setAgencia(String agencia){
		this.agencia = agencia;
	}
	/**	
	 * pega nome da agencia
	 * */
	public String getAgencia(){
		return agencia;
	}
	/**	
	 * inserir saldo do cliente
	 * */ 
	public void setSaldo(double saldo){
		this.saldo = saldo;
	}
	/**	
	 * pega saldo do cliente
	 * */
	public double getSaldo(){
		return saldo;
	}
	/**	
	 *  inserir um novo nome
	 * */
	public void setCliente(Cliente cliente){
		this.cliente = cliente;
	}
	/**	
	 *  pega o nome do cliente
	 * */
	public Cliente getCliente(){
		return cliente;
	}

	/**
	 * metodo creditar da classe conta
	 * @throws ValorNegativoException 
	 * */
	public void creditar(double valor) throws ValorNegativoException{
		
		if(valor < 0){
			throw new ValorNegativoException("Valor incorreto para operação");
		}else{
			saldo = saldo + valor;
		}
	}
     
	/** 
	 * metodo debitar da classe conta
	 * @throws SaldoInsuficienteException 
	 * */
	public void debitar(double valor) throws SaldoInsuficienteException, ValorNegativoException{
		
		if(valor < 0){
			throw new ValorNegativoException("Valor incorreto para operação");
		}else{
			if (saldo > 0 && saldo >= valor && valor > 0){
				saldo = saldo - valor;
			}else{
				throw new SaldoInsuficienteException("Saldo insuficiente");
			}
		}
	}

	/** 
	 * metodo transferir da classe conta
	 * @throws SaldoInsuficienteException 
	 * @throws ValorNegativoException 
	 * */
	public void transferir(double valor, Conta destino) throws SaldoInsuficienteException, ValorNegativoException{
		debitar(valor);
		destino.creditar(valor);
	}
	
}
