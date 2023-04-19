package br.fiap.app.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.fiap.app.demo.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
	
}
