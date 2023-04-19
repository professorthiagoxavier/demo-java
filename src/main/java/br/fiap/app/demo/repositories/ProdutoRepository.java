package br.fiap.app.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.fiap.app.demo.models.Categoria;
import br.fiap.app.demo.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	@Query(value = "select * from produto where id_categoria = ?1", nativeQuery = true)
	List<Produto> findByIdCategoria(Integer id);
}
