package com.java.banco;

import java.util.ArrayList;

import com.java.classebasica.Cliente;
import com.java.classebasica.Conta;
import com.java.classebasica.ContaBonificada;
import com.java.classebasica.ContaPoupanca;
import com.java.exceptions.ClienteInexistenteException;
import com.java.exceptions.ClienteJaExistenteException;
import com.java.exceptions.ContaInexistenteException;
import com.java.exceptions.ContaJaExistenteException;
import com.java.exceptions.SaldoInsuficienteException;
import com.java.exceptions.TipoContaInvalidaException;
import com.java.exceptions.ValorNegativoException;
import com.java.repositorio.RepositorioDeClienteIF;
import com.java.repositorio.RepositorioDeContaIF;
import com.java.repositorio.RepositorioDeClienteArrayList;
import com.java.repositorio.RepositorioDeContaArrayList;

/**
 * classe fachada banco onde se encotra as regras de negócios
 */
public class FachadaBanco implements FachadaBancoIF{
    
    private RepositorioDeContaIF repConta;
	private RepositorioDeClienteIF repCliente;
	
	/**
	 * Construtor da fachadaBanco
	 */
	public FachadaBanco(RepositorioDeContaIF repConta,RepositorioDeClienteIF repCliente){
		this.repConta = repConta;
		this.repCliente = repCliente;
	}
	
	/**
	 * 	Metodos pesquisa cliente e conta
	 * */	
	/**
	 * Método que pesquisa se um cliente existe informando o número do cpf ou retorna uma exceção
	 */
	public Cliente pesquisaCliente(String cpf) throws ClienteInexistenteException{
		Cliente cliente = repCliente.pesquisaCliente(cpf);
		if(cliente == null){
			throw new ClienteInexistenteException("Cliente não existe");
		}else{
			return cliente;
		}
	}
	
	/**
	 * Método que pesquisa uma conta dada pelo cliente que retorna a conta ou retorna uma exceção 
	 */
	
	public Conta pesquisaConta(String numeroDeConta) throws ContaInexistenteException{
		Conta conta = repConta.pesquisaConta(numeroDeConta); 
		if(conta == null){
			throw new ContaInexistenteException("Conta não existente");
		}else{
			return conta;
		}
	}
	
	/**
	 * 	metodos adicionaCliente e adicionaConta
	 * @throws ClienteJaExistenteException 
	 * */
	
	/**
	 * Método que adiciona clientes, mas antes verifica se o cliente já existe fazendo uma pesquisa pelo cpf, 
	 * e se já estiver cadastrado retorna uma exceção
	 */
	public void adicionaCliente(Cliente cliente) throws ClienteJaExistenteException{
		try {
			pesquisaCliente(cliente.getCpf());
			throw new ClienteJaExistenteException("Cliente ja existe");
		} catch (ClienteInexistenteException e) {
			repCliente.adicionaCliente(cliente);
		}
		
			
	}
	/**
	 * Método que adiciona uma conta, mas antes verifica se a conta já existe fazendo uma pesquisa pelo numero,
	 * e se já existir, retorna uma exceção
	 */
	public void adicionaConta(Conta conta) throws ContaJaExistenteException{
		try{
			pesquisaConta(conta.getNumero());
			throw new ContaJaExistenteException("Conta ja existe");
		}catch (ContaInexistenteException e){
			repConta.adicionaConta(conta);
		}
	}
	
	/**
	 * metodos de remover conta e cliente
	 * @throws ClienteInexistenteException 
	 * */
	
	public void removerCliente(String cpf) throws ClienteInexistenteException{
		
		Cliente cliente = pesquisaCliente(cpf);		
		repCliente.removeCliente(cliente);
	
	}
	
	public void removerConta(String numeroDaConta) throws ContaInexistenteException{
		
		Conta conta = pesquisaConta(numeroDaConta);
		repConta.removeConta(conta);
	
	}
	
	/**
	 * metodos de creditar, debitar e transferir
	 * */
	
	/**
	 * Método para creditar recebendo o número da conta e o valor a ser creditado, e se não houver a conta,
	 * relança uma exceção
	 * @throws ValorNegativoException 
	 * @throws ValorNegativoException 
	 */
	public void creditar(String numeroConta, double valor) throws ContaInexistenteException, ValorNegativoException{
			
			Conta conta = pesquisaConta(numeroConta);
			conta.creditar(valor);
		
	}
	
	/**
	 * Método para debitar recebendo o número da conta e o valor a ser debitado, e se não houver a conta ou 
	 * existir a conta, mas o saldo for insuficiente, relança uma exceção
	 * @throws ValorNegativoException 
	 */
	public void debitar(String numeroConta, double valor) throws ContaInexistenteException, SaldoInsuficienteException, ValorNegativoException{		
	
		Conta conta = pesquisaConta(numeroConta);
		conta.debitar(valor);
		
	}
	
	/**
	 * Método para transferir um valor recebendo o numero da conta de origem, o valor a ser transferido e
	 * o número da conta de destino, que utiliza os métodos pesquisar se as contas existem e se é possível
	 * transferir o valor desejado, senão relança uma exceção
	 * @throws ValorNegativoException 
	 */
	
	public void transferir( String numeroContaOrigem, String numeroContaDestino, double valor ) throws ContaInexistenteException, SaldoInsuficienteException, ValorNegativoException{
			
			Conta contaOrigem = pesquisaConta(numeroContaOrigem);
			Conta contaDestino = pesquisaConta(numeroContaDestino);
			contaOrigem.transferir(valor, contaDestino);
		
	}

	/**
	 * Get e set para utilização do metodo listarContas
	 * */
	

	public void setRepConta(RepositorioDeContaArrayList repConta) {
		this.repConta = repConta;
	}

	

	public void setRepCliente(RepositorioDeClienteArrayList repCliente) {
		this.repCliente = repCliente;
	}
	/**
	 * 		Métodos para verificar se as contas são pouança ou bonificada para
	 *      utilização das chamadas de juros e bonos das respectivas contas
	 * @throws ContaInexistenteException 
	 * @throws TipoContaInvalidaException 
	 * @throws ValorNegativoException 
	 *    
	 * */
	public void renderJuros(String numero, double taxa ) throws ContaInexistenteException, TipoContaInvalidaException, ValorNegativoException{
		
		Conta conta =  pesquisaConta(numero);
		
		if(conta instanceof ContaPoupanca){
			ContaPoupanca c = (ContaPoupanca)conta;
			c.renderJuros(taxa);
		}else{			
			throw new TipoContaInvalidaException("Essa conta não eh conta poupança");
		}
	}
	
	public void renderBonus(String numero) throws ContaInexistenteException, TipoContaInvalidaException, ValorNegativoException{
		Conta conta = pesquisaConta(numero);
	
			if(conta instanceof ContaBonificada){
				ContaBonificada cb = (ContaBonificada) conta;
				cb.renderBonus();
				
			}else{
				throw new TipoContaInvalidaException("Essa conta não eh conta bonificada");
			}	
		
	}
	
	public ArrayList<Conta> listaContas(){
		return repConta.getContas();
	}
}