package br.fiap.app.demo.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Produto {
	
	@Id
  	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id; 
	
	@NotBlank(message =  "Favor preencher o nome")
	private String nome;
	
	@Min(value=1, message = "Categoria inválida!" )
	@NotNull(message = "Favor preencher a categoria")
	private Integer idCategoria;
	
	@Min(1)
	private Integer idFornecedor; 
	
	//Pattern
	//@Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
	
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Integer getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Produto create(Integer id, String nome) {
		Produto produto = new Produto(); //create a new instance 
		produto.setId(id);
		produto.setNome(nome);
		return produto;
	}
}
