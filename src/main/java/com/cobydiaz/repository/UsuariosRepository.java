package com.cobydiaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobydiaz.model.Usuario;


public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
