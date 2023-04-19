package br.fiap.app.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.fiap.app.demo.models.Fonecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fonecedor, Integer> {

	 @Query(value = "select * from fornecedor WHERE id = ?1", nativeQuery = true)
	 Fonecedor findByIdProduto(Integer id);
}
