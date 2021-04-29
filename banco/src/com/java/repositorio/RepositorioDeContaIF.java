package com.java.repositorio;

import java.util.ArrayList;
import com.java.classebasica.Conta;

public interface RepositorioDeContaIF {
    
    /**
	 * 
	 * 	Interface com as assinaturas do RepositorioDeContaArrayList 
     *  que faz a ligação do repositório de contas com a fachada banco.
	 * */
	public void adicionaConta(Conta conta);
	public void removeConta(Conta conta);
	public Conta pesquisaConta(String numeroDeConta);
	public ArrayList<Conta> getContas() ;
}
