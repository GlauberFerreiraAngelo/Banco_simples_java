package com.java.repositorio;

import java.util.ArrayList;
import com.java.classebasica.Cliente;

/**
 * Classe de repositório de clientes, utilizando ArrayList
 */
public class RepositorioDeClienteArrayList implements RepositorioDeClienteIF{
    ArrayList <Cliente> cli;
	
	/**
	 * Métodos inicializar o array, adicionar cliente, remover cliente e pesquisar cliente
	 */
	public RepositorioDeClienteArrayList(){
		cli = new ArrayList<Cliente>(); 
	}

	public void adicionaCliente(Cliente cliente){
		cli.add(cliente);
	}

	public void removeCliente(Cliente cliente){
		cli.remove(cliente);
	}

	public Cliente pesquisaCliente(String cpf){
		for(Cliente c : cli){
			if(c.getCpf().equals(cpf)){
				return c;
			}
		}
		return null;
	}
}