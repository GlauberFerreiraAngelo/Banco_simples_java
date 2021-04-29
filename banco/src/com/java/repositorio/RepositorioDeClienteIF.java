package com.java.repositorio;

import com.java.classebasica.Cliente;

public interface RepositorioDeClienteIF {
    
    /**
	 * Interface com as assinaturas do RepositorioDeClienteArrayList que faz a 
     * ligação com o repositório de clientes e a fachada banco
	 * */
	public void adicionaCliente(Cliente cliente);
	public void removeCliente(Cliente cliente);
	public Cliente pesquisaCliente(String cpf);
}
