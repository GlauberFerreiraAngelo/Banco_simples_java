package com.java.classebasica;

public class Cliente{
    /** 
     * atributos da classe cliente
     */
    private String nome;
    private String cpf;
    private String rg;
    private String endereco;
    private String dataDeNascimento;

    /**
	 * construtor da classe cliente
	 */
	public Cliente(String nome, String cpf, String rg, String endereco, String dataDeNascimento){
		super();
        this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
		this.dataDeNascimento = dataDeNascimento;
	}
	
    //construtor da cliente para inicializa
	public Cliente(){
		
	}


    /**
     * Gets e sets da classe cliente  
     */

    public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
	//	inserir cpf do cliente
	public void setCpf(String cpf){
		this.cpf = cpf;
	}
	//	pega o cpf do cliente
	public String getCpf(){
		return cpf;
	}
	//	inserir o rg do cliente
	public void setRg(String rg){
		this.rg = rg;
	}
	//	pega o rg do cliente
	public String getRg(){
		return rg;
	}
	//	inserir o endereco do cliente
	public void setEndereco(String endereco){
		this.endereco = endereco;
	}
	//	pega o endereco do cliente
	public String getEndereco(){
		return endereco;
	}
	// inserir data de nascimento do cliente
	public void setDataDeNascimento(String dataDeNascimento){
		this.dataDeNascimento = dataDeNascimento;
	}
	//	pega a data de nascimento do cliente
	public String getDataDeNascimento(){
		return dataDeNascimento;
	}

}