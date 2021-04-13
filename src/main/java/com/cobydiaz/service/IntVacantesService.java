package com.cobydiaz.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cobydiaz.model.Vacante;

public interface IntVacantesService {
	//Metodos abstractos 
	public List<Vacante> obtenerTodas();
	public Vacante buscarPorId(Integer id);
	public void guardar(Vacante vacante);
	public void eliminar(Integer id);
	public  Page<Vacante> buscarTodas(Pageable page);
}
