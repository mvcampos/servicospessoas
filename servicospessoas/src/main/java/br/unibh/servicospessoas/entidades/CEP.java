package br.unibh.servicospessoas.entidades;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CEP {
	
	@Override
	public String toString() {
		return "CEP [cep=" + cep + ", endereco=" + endereco + ", cidade=" + cidade + "]";
	}
	
	private Long cep;
	private String endereco;
	private String cidade;
	
	
	public CEP(){}
	
	
	public CEP(Long cep, String endereco, String cidade) {
		super();
		this.cep = cep;
		this.endereco = endereco;
		this.cidade = cidade;
	}
	
	public Long getCep() {
		return cep;
	}
	
	public void setCep(Long cep) {
		this.cep = cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	

}
