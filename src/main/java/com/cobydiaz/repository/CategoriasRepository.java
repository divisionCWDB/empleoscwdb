package com.cobydiaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobydiaz.model.Categoria;


//public interface CategoriasRepository extends CrudRepository<Categoria, Integer> {
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {

}
