package com.java.repositorio;


import java.util.ArrayList;
import com.java.classebasica.Conta;

/**
 * Classe de repositório de contas utilizando ArrayList
 */
public class RepositorioDeContaArrayList implements RepositorioDeContaIF {
    ArrayList <Conta> contas;
	
	/**
	 * Métodos de inicializar uma conta, adicionar, remover e pesquisar uma conta
	 */
	public RepositorioDeContaArrayList() {
		contas = new ArrayList<Conta>();
	}
	
	public void adicionaConta(Conta conta){
		contas.add(conta);
		
	}
	
	public void removeConta(Conta conta){
		contas.remove(conta);
		
	}
	
	public Conta pesquisaConta(String numeroDeConta){
		for(Conta c : contas){
			if(c.getNumero().equals(numeroDeConta)){
				return c;
			}
		}
		
		return null;
	}

	public ArrayList<Conta> getContas() {
		return contas;
	}

	public void setContas(ArrayList<Conta> contas) {
		this.contas = contas;
	}
}
