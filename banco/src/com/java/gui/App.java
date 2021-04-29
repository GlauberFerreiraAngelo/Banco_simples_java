package com.java.gui;

import java.util.Scanner;
import com.java.banco.FachadaBancoIF;
import com.java.banco.FachadaBanco;
import com.java.classebasica.Cliente;
import com.java.classebasica.Conta;
import com.java.classebasica.ContaPoupanca;
import com.java.classebasica.ContaBonificada;
import com.java.exceptions.ClienteInexistenteException;
import com.java.exceptions.ClienteJaExistenteException;
import com.java.exceptions.ContaInexistenteException;
import com.java.exceptions.ContaJaExistenteException;
import com.java.exceptions.SaldoInsuficienteException;
import com.java.exceptions.TipoContaInvalidaException;
import com.java.exceptions.ValorNegativoException;
import com.java.repositorio.RepositorioDeClienteArrayList;
import com.java.repositorio.RepositorioDeContaArrayList;

public class App {
    /**
	 * Inicializa a Interface de fachadaBanco
	 */
	static FachadaBancoIF banco = new FachadaBanco(new RepositorioDeContaArrayList(), new RepositorioDeClienteArrayList());
	
	/**
	 * Main - a parte inicial do programa onde o usuário interage com o sistema
	 */
	public static void main(String [] args){
		
		int opcao = 0;       //opcao para escolha no switch
		
		Cliente cliente;
		Conta conta;
		
		Scanner sc = new Scanner(System.in); // scanner para receber dados
		sc.useDelimiter(System.getProperty("line.separator"));
		
		do{
		
			do{
			
			System.out.println("==============================================");
			System.out.println("=                                            =");
			System.out.println("=       Digite [1] para abrir conta          =");
			System.out.println("=       Digite [2] para fecha conta          =");
			System.out.println("=       Digite [3] para transferir           =");
			System.out.println("=       Digite [4] para creditar             =");
			System.out.println("=       Digite [5] para debitar              =");
			System.out.println("=       Digite [6] para cadastrar cliente    =");
			System.out.println("=       Digite [7] dados do cliente          =");
			System.out.println("=       Digite [8] operaçoes com conta       =");
			System.out.println("=       Digite [9] para sai                  =");
			System.out.println("=                                            =");
			System.out.println("==============================================");
			opcao = sc.nextInt();
			
			if((opcao>9)||(opcao<1)){
			     System.out.println("OPÇÃO INVÁLIDA! TENTE NOVAMENTE!");}
			
			}while((opcao>9)||(opcao<1));
			
			switch(opcao){	/**switch para escolha da opcao*/ 
				case 1: 	
							/** abrir conta*/
					
					        /**
					         * Digita os valores a serem atribuidos a abertura da conta
					         */
							String teste = String.valueOf(Conta.getProximoNumero());	//banco.proximaPosicaoLivre()
							System.out.println("O numero da conta é: " + teste);
							
							System.out.println("Digite agencia do cliente: ");
							String agencia = sc.next();
														
							System.out.println("Digite valor: ");
							double saldo = sc.nextDouble();
							
							System.out.println("Digite cpf: ");
							String cpf = sc.next();
							
							if(saldo > 0 ){
								try {
									/*Pesquisa se o cliente já esta cadastrado*/
									cliente = banco.pesquisaCliente(cpf);
								
									/*Escolhe o tipo de conta que deseja abrir*/			
									System.out.println("Qual tipo de conta o cliente esta interesado?");
									System.out.println("[1] - Conta corrente");
									System.out.println("[2] - Conta poupança");
									System.out.println("[3] - Conta bonificada");
									int opcao2 = sc.nextInt();
								
									/*Cria a conta de acordo com o tipo desejado*/
									if(opcao2 == 1){
										conta = new Conta(agencia,saldo,cliente);
										banco.adicionaConta(conta);
										System.err.println("conta criada");
									}else if(opcao2 == 2){
										conta = new ContaPoupanca(agencia,saldo,cliente);
										banco.adicionaConta(conta);
										System.err.println("conta poupança criada");
									}else if(opcao2 == 3){
											conta = new ContaBonificada(agencia,saldo,cliente);
											banco.adicionaConta(conta);
											System.err.println("conta bonificada criada");
										}else{
										System.out.println("Opçao invalida");
									
									}
							
									/*Lança as exceções, caso dê algum erro na criação das contas*/
								} catch (ClienteInexistenteException e) {
									System.err.println(e.getMessage());
								} catch (ContaJaExistenteException e) {
									System.err.println(e.getMessage());
								}  
								}else{
									System.out.println("Valor incorreto para operação");
							}// fim do if
					break;
					
				case 2:
							/**
							 * fechar conta
							 * */
					
							System.out.println("Para fecha a conta digite numero da conta: ");
							String desfazeConta = sc.next();
							
                            /*Remove a conta ou lança um exceção se houver algum problema*/
							try{
								banco.removerConta(desfazeConta);
								System.err.println("Conta removida do banco com sucesso");
							}catch (ContaInexistenteException e){
								System.err.println(e.getMessage() );
							}							
					break;
				
				case 3:				
							/**
							 * transferir 
							 * */
						
					        /*Incere os dados necessários para a transferência*/
							System.out.println("Digite a conta de origem: ");
							String numeroContaOrigem = sc.next();
							System.out.println("Digite a conta de destino: ");
							String numeroContaDestino = sc.next();
							System.out.println("Digite o valor da transferecia: ");
							double valorTransferido = sc.nextDouble();
				            
							/*Verifica as contas e o valor a ser transferido e transfere, senão lança exceção*/
							try {
								Conta contaOrigem = banco.pesquisaConta(numeroContaOrigem);
								Conta contaDestino = banco.pesquisaConta(numeroContaDestino);
						
								banco.transferir(numeroContaOrigem,numeroContaDestino, valorTransferido);
											
									System.err.println("Valor da conta dos clientes: ");
									System.err.println("o novo saldo da conta de origem eh: " + contaOrigem.getSaldo());
									System.err.println("o novo saldo da conta de destino eh: " + contaDestino.getSaldo());
							
							} catch (ContaInexistenteException e2) {
								System.err.println(e2.getMessage());	
							}
							catch (SaldoInsuficienteException e) {
								System.err.println(e.getMessage());
								System.err.println("Valor não foi transferido");
								System.err.println("Valor não correspodente ao saldo");
								
							} catch (ValorNegativoException e) {
								System.err.println(e.getMessage());
							}
									
							
					break;
				
				case 4:		
							/**
							 * creditar
				 			 */
					        /*Incere os dados necessários para creditar*/
							System.out.println("Digite o numero da conta:");
							String numeroDaConta = sc.next(); 
							
							System.out.println("Digite o valor a ser creditado:");
							double valorCreditor = sc.nextDouble(); 
							
							/*Utiliza a regra de negócio e verifica se poderá ser debitado, mas se a conta não existir, lança uma exceção*/
							try {
								banco.creditar(numeroDaConta, valorCreditor);
								System.err.println("Valor cretidato com sucesso ");
								conta = banco.pesquisaConta(numeroDaConta);
								System.err.println("o novo saldo da conta eh: " + conta.getSaldo());
							} catch (ContaInexistenteException e1) {
								System.err.println(e1.getMessage());
							} catch (ValorNegativoException e) {
								System.err.println(e.getMessage());
							}
							
							
					
					break;
					
				case 5:
							/**
							 * debitar
							 * */
					        /*Incere os dados necessários para creditar*/
							System.out.println("Digite o numero da conta:");
							String numeroDaConta1 = sc.next(); 
							System.out.println("Digite o valor a ser debitado:");
							double valorDebitor = sc.nextDouble(); 
						
							Conta contaC;
							
							/*Utiliza a regra de negócio e verifica se poderá debitar, mas se a conta não existir ou o saldo por insuficiênte, lança uma exceção*/
							try {
								contaC = banco.pesquisaConta(numeroDaConta1);
								banco.debitar(numeroDaConta1, valorDebitor);
							
									System.out.println("Valor debitado com sucesso ");
									System.out.println("o novo saldo da conta eh: " + contaC.getSaldo());
															
							} catch (ContaInexistenteException e1) {
								System.err.println(e1.getMessage());
							} catch (SaldoInsuficienteException e) {
								System.err.println("Valor não foi debitado");
								System.err.println("Valor acima do saldo atual");
							} catch (ValorNegativoException e) {
								System.err.println(e.getMessage());							}			
					
					break;
				case 6:
								/**
								 * cadastrar cliente
								 * */   
					            /*Incere os dados necessários para cadastrar cliente*/
								System.out.println("Digite nome do cliente: ");
								String nome = sc.next();
							
								System.out.println("Digite o cpf: ");
								String cpf2 = sc.next();
														
								System.out.println("Digite o rg: ");
								String rg = sc.next();
												
								System.out.println("Digite endereço do cliente: ");
								String endereco = sc.next();
												
								System.out.println("Digite data de nascimento: ");
								String dataDeNascimento = sc.next();
							
								/* construtor receber os dados acima*/
								cliente = new Cliente(nome,cpf2,rg,endereco,dataDeNascimento); 
								
								/*Adiciona o cliente na lista ou lança uma axceção indicando que o cliente já existe*/
								try {
									banco.adicionaCliente(cliente);
								} catch (ClienteJaExistenteException e) {
									System.err.println(e.getMessage());
								}
					break;
					
				case 7: 
					/** 
					 *  dados do cliente
					 *  este case monstrar os dados da classe cliente e conta
					 * */
						System.out.println(" Os clientes são: ");				
										
						ListarContas();
					
					break;
				
				case 8:{
					/*Irá inserir o tipo de operação que irá fazer com a conta*/
					System.out.println("Digite numero para operaçoes bancarias:");
					System.out.println("[1] - para render juros na conta poupança");
					System.out.println("[2] - para da uma bonificação a conta bonificada");
					int numeroADigita = sc.nextInt();
					
					if(numeroADigita<3 && numeroADigita>0){
						System.out.println("Digite o numero da conta");
						String numeroDaContaBancaria = sc.next();
					
						if(numeroADigita == 1){
							/*Opção 1, Utilização do método render juros*/
						
							System.out.println("Digite a taxa");
							double taxa = sc.nextDouble(); 
							taxa = taxa /100;
												
							try {
								/*Utiliza a operação render juros ou então irá ser lançada uma exceção*/
								banco.renderJuros(numeroDaContaBancaria, taxa);
								System.err.println("operação completa");
							} catch (ContaInexistenteException e) {
								System.err.println(e.getMessage());
							} catch (TipoContaInvalidaException e) {
								System.err.println(e.getMessage());
							} catch (ValorNegativoException e) {
								System.err.println(e.getMessage());
							}
						
						}else{
							if(numeroADigita == 2){
								/*Opção 2, utiliza o método para bonificar a conta*/
													
								try {
									/*Utiliza a operação render bonus ou então irá ser lançada uma exceção*/
									banco.renderBonus(numeroDaContaBancaria);
									System.err.println("operação completa");
								} catch (ContaInexistenteException e) {
									System.err.println(e.getMessage());
								} catch (TipoContaInvalidaException e) {
									System.err.println(e.getMessage());
								} catch (ValorNegativoException e) {
									System.err.println(e.getMessage());
								}
								
								
								
							}else{
								System.err.println("numero invalido");
							}
						}
					}else{
						System.out.println("Opção Inválida!");
					}
					break;
				}
						
				case 9:
			}// fim do switch
		
		}while(opcao != 9);
		
		System.err.println("Fim do programa tenha um bom dia");
	}// fim do main
	
	public static void ListarContas(){ 
		
		for(Conta c:banco.listaContas()){
			
				System.out.println("------------------------------------------------");
				System.out.println("Nome do cliente é: "+ c.getCliente().getNome());
				System.out.println("cpf do cliente é: "+ c.getCliente().getCpf());
				System.out.println("rg do cliente é: "+ c.getCliente().getRg());
				System.out.println("endereço do cliente é: "+ c.getCliente().getEndereco());
				System.out.println("data de nascimento do cliente é: "+ c.getCliente().getDataDeNascimento());
		
				System.out.println("numero da conta é: "+ c.getNumero());
				System.out.println("Agencia do cliente é: "+ c.getAgencia());
				System.out.println("Saldo do cliente é: "+ c.getSaldo());
				System.out.println("------------------------------------------------");
			
		}
	}// fim do metodo listarcontas
}
