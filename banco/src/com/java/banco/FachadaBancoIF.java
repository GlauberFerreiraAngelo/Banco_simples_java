package com.java.banco;

import java.util.ArrayList;

import com.java.classebasica.Cliente;
import com.java.classebasica.Conta;
import com.java.exceptions.ClienteInexistenteException;
import com.java.exceptions.ClienteJaExistenteException;
import com.java.exceptions.ContaInexistenteException;
import com.java.exceptions.ContaJaExistenteException;
import com.java.exceptions.SaldoInsuficienteException;
import com.java.exceptions.TipoContaInvalidaException;
import com.java.exceptions.ValorNegativoException;
import com.java.repositorio.RepositorioDeClienteArrayList;
import com.java.repositorio.RepositorioDeContaArrayList;

public interface FachadaBancoIF {
    
    /**
	 * 
	 * 	Assinaturas da FachadaBanco - interface entre AppBanco e FachadaBanco
	 * @throws ClienteInexistenteException 
	 * 
	 * */
	public Cliente pesquisaCliente(String cpf) throws ClienteInexistenteException;
	public Conta pesquisaConta(String numeroDeConta) throws ContaInexistenteException;
	public void adicionaCliente(Cliente cliente) throws ClienteJaExistenteException;
	public void adicionaConta(Conta conta) throws ContaJaExistenteException;
	public void removerCliente(String cpf) throws ClienteInexistenteException;
	public void removerConta(String numeroDaConta) throws ContaInexistenteException;
	public void creditar(String numeroConta, double valor) throws ContaInexistenteException, ValorNegativoException;
	public void debitar(String numeroConta, double valor) throws ContaInexistenteException, SaldoInsuficienteException, ValorNegativoException;
	public void transferir( String numeroContaOrigem, String numeroContaDestino, double valor ) throws ContaInexistenteException, SaldoInsuficienteException, ValorNegativoException;
	public void setRepConta(RepositorioDeContaArrayList repConta);
	public void setRepCliente(RepositorioDeClienteArrayList repCliente) ;
	public void renderJuros(String numero, double taxa ) throws ContaInexistenteException, TipoContaInvalidaException, ValorNegativoException;
	public void renderBonus(String numero) throws ContaInexistenteException, TipoContaInvalidaException, ValorNegativoException;
	public ArrayList<Conta> listaContas();

}
